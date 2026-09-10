package com.typheye.wgpro.core;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.typheye.wgpro.R;
import com.typheye.wgpro.core.state.AccountSnapshot;
import com.typheye.wgpro.core.state.AppState;
import com.typheye.wgpro.core.xms.UIParams;
import com.typheye.wgpro.ui.main.MainActivity;
import com.typheye.wgpro.utils.AppUtils;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * `.Core`：掌管账户会话与设备（含 XMS）状态。
 *
 * - 与 PushService 同进程；PushService 在前台时本服务的进程也受保护。
 * - 仅在"未开启消息同步但设备仍然连着"时把自己提升为前台服务（connectedDevice）。
 */
public class CoreService extends Service implements CoreApi {
    public static final String ACTION_START = "com.typheye.wgpro.core.START";
    public static final String ACTION_STOP = "com.typheye.wgpro.core.STOP";
    public static final String ACTION_REFRESH_ACCOUNT = "com.typheye.wgpro.core.REFRESH_ACCOUNT";
    public static final String ACTION_RECONNECT_WEARABLE = "com.typheye.wgpro.core.RECONNECT_WEARABLE";
    public static final String ACTION_REFRESH_CONFIG = "com.typheye.wgpro.core.REFRESH_CONFIG";
    public static final String ACTION_MARK_DEVICE = "com.typheye.wgpro.core.MARK_DEVICE";
    public static final String EXTRA_NODE_ID = "node_id";
    public static final String EXTRA_NODE_NAME = "node_name";

    private static final long CONFIG_INTERVAL_MS = 60 * 60 * 1000L;
    private static final AtomicBoolean RUNNING = new AtomicBoolean(false);

    private final Binder binder = new LocalBinder();
    private AccountEngine accountEngine;
    private DeviceEngine deviceEngine;
    private long lastConfigAt;

    public final class LocalBinder extends Binder {
        public CoreApi api() {
            return CoreService.this;
        }
    }

    public static boolean isRunning() {
        return RUNNING.get();
    }

    public static void start(@NonNull Context context) {
        try {
            context.startService(new Intent(context, CoreService.class).setAction(ACTION_START));
        } catch (Exception ignored) {
        }
    }

    public static void stop(@NonNull Context context) {
        try {
            context.startService(new Intent(context, CoreService.class).setAction(ACTION_STOP));
        } catch (Exception ignored) {
        }
    }

    public static void requestRefresh(@NonNull Context context) {
        try {
            context.startService(new Intent(context, CoreService.class)
                    .setAction(ACTION_REFRESH_ACCOUNT));
        } catch (Exception ignored) {
        }
    }

    /** 扫描页发现设备时同步状态（不再直接改 Activity 的静态字段）。 */
    public static void markDeviceCandidate(@NonNull Context context,
                                           @NonNull String nodeId, @NonNull String nodeName) {
        try {
            context.startService(new Intent(context, CoreService.class)
                    .setAction(ACTION_MARK_DEVICE)
                    .putExtra(EXTRA_NODE_ID, nodeId)
                    .putExtra(EXTRA_NODE_NAME, nodeName));
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RUNNING.set(true);
        NotificationChannels.ensureAll(this);
        deviceEngine = new DeviceEngine(this);
        accountEngine = new AccountEngine(this, new AccountEngine.Listener() {
            @Override
            public void onSessionRevoked() {
                AppState.get().addLog("会话已失效，已清除本地登录状态");
                notifyAccountAbnormal();
                PushService.stop(CoreService.this);
            }

            @Override
            public void onAccountChanged(AccountSnapshot snapshot, boolean avatarChanged) {
                AppState.get().publishAccount(snapshot);
            }
        });
        accountEngine.snapshot(false);
        deviceEngine.start();
        accountEngine.start();
        refreshServerConfig();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        String action = intent == null ? ACTION_START : intent.getAction();
        if (ACTION_STOP.equals(action)) {
            shutdown();
            return START_NOT_STICKY;
        }
        if (ACTION_REFRESH_ACCOUNT.equals(action)) {
            accountEngine.refreshNow();
        } else if (ACTION_RECONNECT_WEARABLE.equals(action)) {
            deviceEngine.reconnectNow();
        } else if (ACTION_REFRESH_CONFIG.equals(action)) {
            refreshServerConfig();
        } else if (ACTION_MARK_DEVICE.equals(action) && intent != null) {
            deviceEngine.markCandidate(intent.getStringExtra(EXTRA_NODE_ID),
                    intent.getStringExtra(EXTRA_NODE_NAME));
        }
        if (!accountEngine.isLoggedIn() && !deviceEngine.isConnected()) {
            // 既没登录也没设备：保留服务以便随时被唤醒，但不做任何事
            AppState.get().addLog("CoreService 待机");
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        shutdown();
        super.onDestroy();
    }

    private void shutdown() {
        RUNNING.set(false);
        if (accountEngine != null) accountEngine.stop();
        if (deviceEngine != null) deviceEngine.stop();
    }

    /* ------------------------------------------------------------ CoreApi */

    @Override
    public boolean isLoggedIn() {
        return accountEngine != null && accountEngine.isLoggedIn();
    }

    @Override
    public String uid() {
        return accountEngine == null ? "" : accountEngine.uid();
    }

    @Override
    public UIParams device() {
        return deviceEngine == null ? new UIParams() : deviceEngine.snapshot();
    }

    @Override
    public void requestAccountRefresh() {
        if (accountEngine != null) accountEngine.refreshNow();
    }

    @Override
    public void reconnectWearable() {
        if (deviceEngine != null) deviceEngine.reconnectNow();
    }

    @Override
    public void markDeviceCandidate(String nodeId, String nodeName) {
        if (deviceEngine != null) deviceEngine.markCandidate(nodeId, nodeName);
    }

    @Override
    public void refreshServerConfig() {
        long now = System.currentTimeMillis();
        if (now - lastConfigAt < CONFIG_INTERVAL_MS) return;
        lastConfigAt = now;
        new Thread(() -> AppUtils.loadServerConfig(getApplicationContext())).start();
    }

    private void notifyAccountAbnormal() {
        NotificationChannels.ensureAll(this);
        Intent intent = new Intent(this, MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP
                        | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,
                com.typheye.wgpro.utils.InboxNotificationHelper.CHANNEL_ACCOUNT)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle("Typheye账户")
                .setContentText("检测到账户异常，请立即处理")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ERROR)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) manager.notify(1001, builder.build());
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
