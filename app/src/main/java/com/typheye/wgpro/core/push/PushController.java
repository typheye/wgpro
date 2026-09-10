package com.typheye.wgpro.core.push;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.typheye.wgpro.core.state.AppState;
import com.typheye.wgpro.core.AppLifecycle;
import com.typheye.wgpro.core.BackgroundHealth;
import com.typheye.wgpro.utils.InboxNotificationHelper;
import com.typheye.wgpro.utils.tAccUtils;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

/**
 * 推送通道控制器。
 *
 * 优先使用云端 WebSocket（服务端通过 realtime_config2 下发开关与地址），
 * 失败或未开启时自动回落到原来的 HTTP 轮询，保证任何情况下消息都不会丢。
 *
 * 两条铁律：
 *  1) 事件只负责"尽快提醒"，本地缓存与未读计数最终由一次 HTTP 校准收敛；
 *  2) 只有服务端明确说会话失效（4401）才让上层退出登录，网络抖动一律重连。
 */
public final class PushController {
    private static final String TAG = "WGPro.Push";

    public interface Listener {
        void onInbox(int total, int notificationUnread, int messageUnread);

        void onSessionRevoked();

        void onLog(String line);
    }

    private static final long CATCH_UP_DEBOUNCE_MS = 2_000L;
    private static final long WS_RETRY_AFTER_MS = 5 * 60_000L;
    private static final long[] BACKOFF_MS = {1_000L, 2_000L, 5_000L, 15_000L, 30_000L, 60_000L};

    private final Context context;
    private final Listener listener;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final tAccUtils account;
    private final OkHttpClient client;

    private RealtimeConfig config = RealtimeConfig.disabled();
    private WebSocket socket;
    private boolean running;
    private boolean wsConnected;
    private boolean catchUpScheduled;
    private int wsFailures;
    private int backoffIndex;
    private long wsBlockedUntil;
    private long pollIntervalMs = 15_000L;
    private boolean pollInFlight;

    private final Runnable pollTick = new Runnable() {
        @Override
        public void run() {
            if (!running) return;
            if (!wsConnected) {
                catchUpNow();
            }
            handler.postDelayed(this, pollIntervalMs);
        }
    };

    private final Runnable catchUpTask = new Runnable() {
        @Override
        public void run() {
            catchUpScheduled = false;
            if (running) catchUpNow();
        }
    };

    public PushController(@NonNull Context context, @NonNull Listener listener) {
        this.context = context.getApplicationContext();
        this.listener = listener;
        this.account = new tAccUtils(this.context);
        this.client = this.account.getClient().newBuilder()
                .pingInterval(25, TimeUnit.SECONDS)
                .build();
    }

    public void start() {
        if (running) return;
        running = true;
        wsFailures = 0;
        backoffIndex = 0;
        Log.d(TAG, "controller start");
        loadConfig();
        handler.removeCallbacks(pollTick);
        handler.post(pollTick);
    }

    public void stop() {
        Log.d(TAG, "controller stop");
        running = false;
        wsConnected = false;
        handler.removeCallbacks(pollTick);
        handler.removeCallbacks(catchUpTask);
        closeSocket(1000, "service stopped");
    }

    /** 页面下拉/回前台时主动校准一次。 */
    public void refreshNow() {
        handler.removeCallbacks(pollTick);
        handler.post(pollTick);
        catchUpNow();
    }

    /** 网络恢复：重置退避并重新连接。 */
    public void onNetworkAvailable() {
        if (!running) return;
        Log.d(TAG, "network available -> reconnect");
        wsFailures = 0;
        backoffIndex = 0;
        wsBlockedUntil = 0L;
        closeSocket(1000, "network change");
        handler.postDelayed(this::openSocketIfPossible, 500L);
    }

    private void loadConfig() {
        if (!account.isLogin()) {
            config = RealtimeConfig.disabled();
            listener.onInbox(0, 0, 0);
            Log.d(TAG, "loadConfig: not logged in, polling mode");
            return;
        }
        account.getV2JsonFresh("realtime_config2", new LinkedHashMap<>(), false,
                new tAccUtils.JsonCallback() {
                    @Override
                    public void onSuccess(@NonNull JSONObject json) {
                        config = RealtimeConfig.from(json);
                        pollIntervalMs = Math.max(5, config.pollInterval) * 1000L;
                        Log.d(TAG, "loadConfig ok: enabled=" + config.enabled + " url=" + config.url);
                        listener.onLog("实时通道配置：enabled=" + config.enabled
                                + " url=" + config.url);
                        openSocketIfPossible();
                    }

                    @Override
                    public void onError(int statusCode, @NonNull String message) {
                        config = RealtimeConfig.disabled();
                        Log.w(TAG, "loadConfig failed: " + statusCode + " " + message);
                        listener.onLog("实时通道探测失败，使用 HTTP 轮询：" + message);
                    }
                });
    }

    private void openSocketIfPossible() {
        if (!running) return;
        if (!account.isLogin()) {
            Log.d(TAG, "openSocket: skip (not logged in)");
            return;
        }
        if (wsConnected || socket != null) {
            Log.d(TAG, "openSocket: skip (already connected)");
            return;
        }
        if (!config.enabled || config.url.isEmpty()) {
            Log.d(TAG, "openSocket: skip (disabled by server)");
            return;
        }
        if (System.currentTimeMillis() < wsBlockedUntil) {
            Log.d(TAG, "openSocket: skip (blocked for "
                    + ((wsBlockedUntil - System.currentTimeMillis()) / 1000) + "s)");
            return;
        }
        if (!account.isV2Session()) {
            Log.d(TAG, "openSocket: skip (legacy session)");
            return;
        }
        try {
            String uid = account.getUid();
            String url = config.url + (config.url.contains("?") ? "&" : "?") + "uid=" + uid;
            Request request = account.realtimeRequest(url).build();
            Log.d(TAG, "openSocket: connecting " + url);
            socket = client.newWebSocket(request, new SocketListener());
        } catch (Exception error) {
            Log.w(TAG, "openSocket failed: " + error);
            listener.onLog("WebSocket 建立失败：" + error.getMessage());
            socket = null;
            scheduleReconnect();
        }
    }

    private void closeSocket(int code, String reason) {
        WebSocket current = socket;
        socket = null;
        wsConnected = false;
        if (current != null) {
            try {
                current.close(code, reason);
            } catch (Exception ignored) {
            }
        }
    }

    private void scheduleReconnect() {
        Log.d(TAG, "reconnect in " + BACKOFF_MS[backoffIndex] + "ms");
        handler.postDelayed(this::openSocketIfPossible, BACKOFF_MS[backoffIndex]);
        if (backoffIndex < BACKOFF_MS.length - 1) backoffIndex++;
    }

    private void scheduleCatchUp() {
        if (catchUpScheduled) return;
        catchUpScheduled = true;
        handler.postDelayed(catchUpTask, CATCH_UP_DEBOUNCE_MS);
    }

    private void catchUpNow() {
        if (pollInFlight || !account.isLogin()) {
            if (!account.isLogin()) listener.onInbox(0, 0, 0);
            return;
        }
        pollInFlight = true;
        InboxNotificationHelper.pollDetailed(context, (total, notificationUnread, messageUnread) -> {
            pollInFlight = false;
            if (total > 0 && !InboxNotificationHelper.isInitialized(context)) {
                InboxNotificationHelper.markInitialized(context);
            }
            listener.onInbox(total, notificationUnread, messageUnread);
        });
    }

    private boolean handleMessage(String text) {
        JSONObject json;
        try {
            json = new JSONObject(text);
        } catch (Exception ignored) {
            return true;
        }
        String type = json.optString("t", "");
        if ("hello_ok".equals(type)) {
            wsConnected = true;
            wsFailures = 0;
            backoffIndex = 0;
            Log.d(TAG, "ws connected (hello_ok)");
            listener.onLog("实时通道已连接");
            // 服务端不保存事件历史：连上就做一次增量校准
            catchUpNow();
            return true;
        }
        if ("ping".equals(type)) {
            WebSocket current = socket;
            if (current != null) {
                try {
                    current.send("{\"t\":\"pong\",\"ts\":" + (System.currentTimeMillis() / 1000) + "}");
                } catch (Exception ignored) {
                }
            }
            return true;
        }
        if ("error".equals(type)) {
            int code = json.optInt("code", 0);
            Log.w(TAG, "ws error type: code=" + code + " " + json.optString("msg", ""));
            listener.onLog("实时通道错误：code=" + code + " " + json.optString("msg", ""));
            if (code == 4401) {
                closeSocket(1000, "session invalid");
                listener.onSessionRevoked();
            }
            return true;
        }
        if (!"event".equals(type)) return true;

        String eventType = json.optString("type", "");
        JSONObject data = json.optJSONObject("data");
        if (data == null) data = new JSONObject();
        if ("signal".equals(eventType)) {
            scheduleCatchUp();
            return true;
        }
        if ("unread.update".equals(eventType)) {
            listener.onInbox(data.optInt("unread_count", 0),
                    data.optInt("notification_unread_count", 0),
                    data.optInt("message_unread_count", 0));
            return true;
        }
        if ("session.revoked".equals(eventType)) {
            listener.onSessionRevoked();
            return true;
        }
        InboxNotificationHelper.handleRealtimeEvent(context, eventType, data);
        // 事件负责"立刻提醒"，缓存与计数稍后由一次 HTTP 校准收敛
        scheduleCatchUp();
        return true;
    }

    private final class SocketListener extends WebSocketListener {
        @Override
        public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
            Log.d(TAG, "ws onOpen code=" + response.code());
            AppState.get().addLog("WebSocket 握手完成");
        }

        @Override
        public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
            Log.d(TAG, "ws onMessage: " + (text.length() > 160 ? text.substring(0, 160) : text));
            try {
                handleMessage(text);
            } catch (Exception error) {
                AppState.get().addLog("处理实时事件失败：" + error.getMessage());
            }
        }

        @Override
        public void onClosing(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
            Log.d(TAG, "ws onClosing code=" + code + " reason=" + reason);
            webSocket.close(1000, null);
        }

        @Override
        public void onClosed(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
            Log.d(TAG, "ws onClosed code=" + code + " reason=" + reason);
            socket = null;
            wsConnected = false;
            if (!running) return;
            if (code == 4401) {
                listener.onSessionRevoked();
                return;
            }
            if (code == 4409) {
                AppState.get().addLog("该会话已在其他地方建立连接，稍后重连");
                handler.postDelayed(PushController.this::openSocketIfPossible, 30_000L);
                return;
            }
            onSocketDown("closed " + code + " " + reason);
        }

        @Override
        public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable error,
                              @Nullable Response response) {
            Log.w(TAG, "ws onFailure: " + error + " response=" + response);
            socket = null;
            wsConnected = false;
            if (!running) return;
            onSocketDown(error.getMessage() == null ? "连接失败" : error.getMessage());
        }
    }

    private void onSocketDown(String reason) {
        Log.w(TAG, "socket down: " + reason);
        if (!AppLifecycle.isForeground()) {
            // 退到后台后被系统掐断：交给"后台保活体检"提示用户去放开限制
            BackgroundHealth.recordBackgroundDrop(context);
        }
        wsFailures++;
        listener.onLog("实时通道断开（第 " + wsFailures + " 次）：" + reason);
        if (wsFailures >= 3) {
            // 连续失败：先退回轮询一段时间，避免在弱网下反复握手
            wsBlockedUntil = System.currentTimeMillis() + WS_RETRY_AFTER_MS;
            wsFailures = 0;
            backoffIndex = 0;
            listener.onLog("实时通道连续失败，暂用 HTTP 轮询，5 分钟后重试");
            catchUpNow();
            return;
        }
        scheduleReconnect();
    }
}
