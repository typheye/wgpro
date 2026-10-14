package com.typheye.wgpro.core.emu;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.typheye.wgpro.R;
import com.typheye.wgpro.core.NotificationChannels;
import com.typheye.wgpro.ui.function.device.VirtualDeviceActivity;

/**
 * 虚拟设备守护：引擎运行期间常驻**前台服务 + 部分唤醒锁**（Termux 同款思路），
 * 避免熄屏或切后台后被系统回收，让模拟器能一直跑。
 *
 * <p>只在**有实例运行**时存在：引擎起来时启动，最后一个实例结束即撤（通知随之消失）。</p>
 */
public class VelaGuardService extends Service {

    private static final int NOTIFICATION_ID = 0x56454C41;   // "VELA"
    private static final String WAKE_TAG = "wgpro:vela-emulator";

    private PowerManager.WakeLock wakeLock;

    /** 启动守护（幂等，重复调用只是复用已有服务）。 */
    public static void start(@Nullable Context context) {
        if (context == null) {
            return;
        }
        try {
            ContextCompat.startForegroundService(context.getApplicationContext(),
                    new Intent(context.getApplicationContext(), VelaGuardService.class));
        } catch (Exception error) {
            android.util.Log.w("VelaGuard", "启动守护失败", error);
        }
    }

    /** 撤销守护。 */
    public static void stop(@Nullable Context context) {
        if (context == null) {
            return;
        }
        try {
            context.getApplicationContext()
                    .stopService(new Intent(context.getApplicationContext(), VelaGuardService.class));
        } catch (Exception error) {
            android.util.Log.w("VelaGuard", "停止守护失败", error);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationChannels.ensureAll(this);
        startForeground(NOTIFICATION_ID, buildNotification());
        PowerManager power = (PowerManager) getSystemService(POWER_SERVICE);
        if (power != null) {
            wakeLock = power.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, WAKE_TAG);
            wakeLock.setReferenceCounted(false);
            wakeLock.acquire();
        }
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        // 不要粘性重启：进程被杀说明引擎也没了，重启一个空守护没有意义
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        if (wakeLock != null && wakeLock.isHeld()) {
            wakeLock.release();
        }
        wakeLock = null;
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(@Nullable Intent intent) {
        return null;
    }

    private Notification buildNotification() {
        Intent open = new Intent(this, VirtualDeviceActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent content = PendingIntent.getActivity(this, 0, open,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        return new NotificationCompat.Builder(this, NotificationChannels.VIRTUAL_DEVICE)
                .setSmallIcon(R.drawable.ic_watch_vector)
                .setContentTitle("虚拟设备运行中")
                .setContentText("本地模拟器运行中，点按返回")
                .setContentIntent(content)
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();
    }
}
