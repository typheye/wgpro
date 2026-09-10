package com.typheye.wgpro.core;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.typheye.wgpro.core.push.PushController;
import com.typheye.wgpro.core.state.AppState;
import com.typheye.wgpro.core.state.InboxSnapshot;
import com.typheye.wgpro.ui.main.MainActivity;
import com.typheye.wgpro.utils.InboxNotificationHelper;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * `.Push`：掌管所有通知与私信。
 *
 * 负责 Android 通知、未读计数、免打扰，以及实时通道（WebSocket，失败自动回落 HTTP 轮询）。
 * 本服务在前台运行时，同进程的 CoreService 同样受到保护。
 */
public class PushService extends Service implements PushApi {
    public static final String ACTION_START = "com.typheye.wgpro.push.START";
    public static final String ACTION_STOP = "com.typheye.wgpro.push.STOP";
    public static final String ACTION_REFRESH = "com.typheye.wgpro.push.REFRESH";
    public static final String ACTION_DND_CHANGED = "com.typheye.wgpro.push.DND_CHANGED";

    private static final AtomicBoolean RUNNING = new AtomicBoolean(false);

    private final Binder binder = new LocalBinder();
    private PushController controller;
    private ConnectivityManager.NetworkCallback networkCallback;
    private InboxSnapshot snapshot = InboxSnapshot.empty();

    public final class LocalBinder extends Binder {
        public PushApi api() {
            return PushService.this;
        }
    }

    public static boolean isRunning() {
        return RUNNING.get();
    }

    public static void start(@NonNull Context context) {
        Intent intent = new Intent(context, PushService.class).setAction(ACTION_START);
        try {
            // 不使用前台服务：前台服务通知无法取消（系统拒绝 cancel），
            // 而"先进前台再退出"会在服务重启时让通知闪现，因此直接以普通后台服务运行。
            context.startService(intent);
        } catch (Exception ignored) {
        }
    }

    public static void stop(@NonNull Context context) {
        try {
            context.startService(new Intent(context, PushService.class).setAction(ACTION_STOP));
        } catch (Exception ignored) {
        }
    }

    /** 免打扰开关变更后同步一次快照（红点需要立即消失）。 */
    public static void notifyDoNotDisturbChanged(@NonNull Context context) {
        try {
            context.startService(new Intent(context, PushService.class)
                    .setAction(ACTION_DND_CHANGED));
        } catch (Exception ignored) {
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        RUNNING.set(true);
        // 每次启动都重新声明渠道：名称、声音、震动、重要性（未被用户锁定时会按声明更新）
        NotificationChannels.ensureAll(this);
        controller = new PushController(this, new PushController.Listener() {
            @Override
            public void onInbox(int total, int notificationUnread, int messageUnread) {
                snapshot = new InboxSnapshot(total, notificationUnread, messageUnread,
                        InboxNotificationHelper.isDoNotDisturb(PushService.this),
                        System.currentTimeMillis());
                AppState.get().publishInbox(snapshot);
            }

            @Override
            public void onSessionRevoked() {
                AppState.get().addLog("实时通道报告会话失效");
                CoreService.requestRefresh(PushService.this);
            }

            @Override
            public void onLog(String line) {
                AppState.get().addLog(line);
            }
        });
        registerNetworkCallback();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        String action = intent == null ? ACTION_START : intent.getAction();
        if (ACTION_STOP.equals(action)) {
            shutdown();
            return START_NOT_STICKY;
        }
        if (ACTION_DND_CHANGED.equals(action)) {
            boolean dnd = InboxNotificationHelper.isDoNotDisturb(this);
            snapshot = new InboxSnapshot(snapshot.unreadTotal, snapshot.notificationUnread,
                    snapshot.messageUnread, dnd, System.currentTimeMillis());
            AppState.get().publishInbox(snapshot);
            return START_STICKY;
        }
        if (controller != null) {
            if (ACTION_REFRESH.equals(action)) {
                controller.refreshNow();
            } else {
                controller.start();
            }
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
        if (controller != null) controller.stop();
        unregisterNetworkCallback();
    }

    /* ------------------------------------------------------------ PushApi */

    @Override
    public InboxSnapshot inbox() {
        return snapshot;
    }

    @Override
    public void refreshNow() {
        if (controller != null) controller.refreshNow();
    }

    @Override
    public void setDoNotDisturb(boolean enabled) {
        InboxNotificationHelper.setDoNotDisturb(this, enabled);
        snapshot = new InboxSnapshot(snapshot.unreadTotal, snapshot.notificationUnread,
                snapshot.messageUnread, enabled, System.currentTimeMillis());
        AppState.get().publishInbox(snapshot);
        if (controller != null) controller.refreshNow();
    }

    @Override
    public void cancelPrivateNotifications(String peerUid) {
        InboxNotificationHelper.cancelPrivateNotifications(this, peerUid);
    }

    @Override
    public void cancelSystemNotifications() {
        InboxNotificationHelper.cancelSystemNotifications(this);
    }

    /* ------------------------------------------------------------ 网络与前台 */

    private void registerNetworkCallback() {
        try {
            ConnectivityManager manager =
                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager == null) return;
            networkCallback = new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(@NonNull Network network) {
                    if (controller != null) controller.onNetworkAvailable();
                }
            };
            manager.registerDefaultNetworkCallback(networkCallback);
        } catch (Exception error) {
            AppState.get().addLog("注册网络回调失败：" + error.getMessage());
        }
    }

    private void unregisterNetworkCallback() {
        if (networkCallback == null) return;
        try {
            ConnectivityManager manager =
                    (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager != null) manager.unregisterNetworkCallback(networkCallback);
        } catch (Exception ignored) {
        }
        networkCallback = null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
}
