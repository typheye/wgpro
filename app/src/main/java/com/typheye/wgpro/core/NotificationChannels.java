package com.typheye.wgpro.core;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.os.Build;

/**
 * 全局通知渠道定义。
 *
 * 三个渠道各司其职：
 *  - {@link #MESSAGES}「消息通知」：系统消息 + 私信，高重要性（横幅/声音/震动）
 *  - {@link #ACCOUNT}「Typheye账户」：账户异常、封号等，高重要性（横幅/声音/震动）
 *  - {@link #SERVICE}「后台运行状态」：前台服务常驻通知，最低重要性，可在系统设置中关闭
 *
 * 渠道一旦被创建，重要性/声音/震动会被系统记住；如果用户没有锁定过这些字段，
 * 再次调用 createNotificationChannel 会按本类声明更新它们，因此每次启动都调用
 * {@link #ensureAll(Context)}。若用户已经锁定（例如在系统设置里手动改成"无"），
 * 系统会保留用户选择——此时由 App 提示用户自行开启，见 {@link #isHighImportance}.
 */
public final class NotificationChannels {
    public static final String MESSAGES = "message_channel_v2";
    public static final String ACCOUNT = "account_channel_v2";
    public static final String SERVICE = "service_status";

    /** 早期版本用过的渠道 id，启动时清理，避免通知设置页面出现重复条目。 */
    private static final String[] LEGACY_IDS = {
            "message_channel", "service_status_channel", "account_channel",
            // 实验渠道：系统不允许把渠道设为 IMPORTANCE_NONE，MIUI 会强制提升为 LOW，
            // 反而比 MIN 更显眼，因此废弃并删除。
            "service_status_hidden"};

    private NotificationChannels() {
    }

    public static void ensureAll(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return;
        NotificationManager manager = context.getSystemService(NotificationManager.class);
        if (manager == null) return;

        ensure(manager, MESSAGES, "消息通知", "系统消息、私信等新消息提醒",
                NotificationManager.IMPORTANCE_HIGH, true, new long[]{0, 180, 120, 180},
                true, Notification.VISIBILITY_PRIVATE);
        ensure(manager, ACCOUNT, "Typheye账户", "账户异常、封号等与账户相关的通知",
                NotificationManager.IMPORTANCE_HIGH, true, new long[]{0, 220, 140, 220},
                true, Notification.VISIBILITY_PRIVATE);
        ensure(manager, SERVICE, "后台运行状态", "保持设备连接与消息同步时显示，可在系统通知设置中关闭",
                NotificationManager.IMPORTANCE_MIN, false, null,
                false, Notification.VISIBILITY_SECRET);

        for (String legacyId : LEGACY_IDS) {
            if (manager.getNotificationChannel(legacyId) != null) {
                manager.deleteNotificationChannel(legacyId);
            }
        }
    }

    private static void ensure(NotificationManager manager, String id, String name, String description,
                               int importance, boolean alerting, long[] vibrationPattern,
                               boolean showBadge, int lockscreenVisibility) {
        NotificationChannel channel = new NotificationChannel(id, name, importance);
        channel.setDescription(description);
        channel.setShowBadge(showBadge);
        channel.setLockscreenVisibility(lockscreenVisibility);
        channel.enableLights(false);
        if (alerting) {
            channel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),
                    new AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .build());
            channel.enableVibration(true);
            if (vibrationPattern != null) {
                channel.setVibrationPattern(vibrationPattern);
            }
        } else {
            channel.setSound(null, null);
            channel.enableVibration(false);
        }
        // 不存在则创建；已存在时更新"用户未锁定"的字段（名称、描述、声音、震动、角标）
        manager.createNotificationChannel(channel);
    }

    /** 渠道当前是否仍保持高重要性（决定是否有横幅/声音/震动）。 */
    public static boolean isHighImportance(Context context, String channelId) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return true;
        NotificationManager manager = context.getSystemService(NotificationManager.class);
        if (manager == null) return true;
        NotificationChannel channel = manager.getNotificationChannel(channelId);
        if (channel == null) return true;
        return channel.getImportance() >= NotificationManager.IMPORTANCE_HIGH;
    }

    public static Intent settingsIntent(Context context, String channelId) {
        Intent intent = new Intent(android.provider.Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        intent.putExtra(android.provider.Settings.EXTRA_APP_PACKAGE, context.getPackageName());
        intent.putExtra(android.provider.Settings.EXTRA_CHANNEL_ID, channelId);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

    public static Intent appSettingsIntent(Context context) {
        Intent intent = new Intent(android.provider.Settings.ACTION_APP_NOTIFICATION_SETTINGS);
        intent.putExtra(android.provider.Settings.EXTRA_APP_PACKAGE, context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }
}
