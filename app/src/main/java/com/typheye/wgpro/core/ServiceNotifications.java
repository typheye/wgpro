package com.typheye.wgpro.core;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.typheye.wgpro.ui.main.MainActivity;

/**
 * 前台服务的常驻通知。
 *
 * Android 要求前台服务必须挂一条通知，无法真正去掉；这里的做法与国内主流方案一致：
 *  - 渠道重要性设为最低（MIN），无声、无震动、不显示角标、锁屏隐藏；
 *  - 通知本身标记为 silent + localOnly；
 *  - 使用系统约定的 {@code group_key_foreground_service} 分组，
 *    国产 ROM（MIUI/HyperOS 等）会把它折叠进"正在运行/后台运行"分组。
 * 用户仍可在系统通知设置里把该渠道关掉：服务继续运行，通知彻底不可见。
 */
public final class ServiceNotifications {
    public static final String CHANNEL_ID = NotificationChannels.SERVICE;
    /** 系统/国产 ROM 用于折叠前台服务通知的分组键。 */
    private static final String FOREGROUND_GROUP = "group_key_foreground_service";

    private ServiceNotifications() {
    }

    static void ensureChannel(Context context) {
        NotificationChannels.ensureAll(context);
    }

    static Notification build(Context context, int smallIcon, String title, String text,
                              int requestCode) {
        return build(context, smallIcon, title, text, requestCode, CHANNEL_ID);
    }

    /**
     * 前台服务通知隐藏：服务已经在"前台服务"状态后，把自己发的那条通知取消掉。
     *
     * 这是国内主流做法（也是腾讯云/掘金那两篇讲的方式）：startForeground 之后再
     * cancel 自己的通知 id，通知从通知栏消失，而服务依然处于前台服务状态。
     * 若不生效（部分 ROM 会重新贴出通知），调用方会在 2 秒后检查并回退到正常通知。
     */
    public static void hideNotification(Context context, int notificationId) {
        try {
            NotificationManager manager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager != null) manager.cancel(notificationId);
        } catch (Exception ignored) {
        }
    }

    /** 当前服务是否仍被系统认定为前台服务。 */
    public static boolean isServiceForeground(Context context, Class<? extends Service> serviceClass) {
        try {
            ActivityManager manager =
                    (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (manager == null) return true;
            for (ActivityManager.RunningServiceInfo info : manager.getRunningServices(80)) {
                if (info.service != null
                        && serviceClass.getName().equals(info.service.getClassName())) {
                    return info.foreground;
                }
            }
        } catch (Exception ignored) {
        }
        return true;   // 查询失败时不误判，保持现状
    }

    private static Notification build(Context context, int smallIcon, String title, String text,
                                      int requestCode, String channelId) {
        Intent intent = new Intent(context, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);
        return new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(smallIcon)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_MIN)
                .setSilent(true)
                .setLocalOnly(true)
                .setOngoing(true)
                .setShowWhen(false)
                .setGroup(FOREGROUND_GROUP)
                .setGroupAlertBehavior(NotificationCompat.GROUP_ALERT_SUMMARY)
                .setContentIntent(pendingIntent)
                .build();
    }
}
