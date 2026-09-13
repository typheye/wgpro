package com.typheye.wgpro.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.community.ChatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 通知中心/私信的落地逻辑：未读计数、Android 通知、免打扰、通知去重与取消。
 *
 * 改造后由 PushService 驱动：
 *  - HTTP 兜底模式：{@link #pollDetailed}（原 15 秒轮询，行为不变）；
 *  - 实时模式：{@link #handleRealtimeEvent}（WebSocket 事件直达，不再等待轮询）。
 * 两种模式共用同一套 last_id 去重键，因此不会重复提醒。
 */
public final class InboxNotificationHelper {
    /** 账户异常渠道（与消息分开，便于分别控制）。 */
    public static final String CHANNEL_ACCOUNT = com.typheye.wgpro.core.NotificationChannels.ACCOUNT;
    private static final String PREFS = "inbox_notification_state";
    private static final String KEY_INITIALIZED = "initialized";
    private static final String KEY_LAST_NOTIFICATION_ID = "last_notification_id";
    private static final String KEY_LAST_MESSAGE_ID = "last_message_id";
    private static final String KEY_SYSTEM_NOTIFICATION_IDS = "system_notification_ids";
    private static final String KEY_PRIVATE_NOTIFICATION_IDS = "private_notification_ids";

    private InboxNotificationHelper() { }

    public interface Callback {
        void onUnreadCount(int count);
    }

    public interface DetailedCallback {
        void onUnread(int total, int notificationUnread, int messageUnread);
    }

    public static void poll(@NonNull Context context, @Nullable Callback callback) {
        pollDetailed(context, callback == null ? null
                : (total, notificationUnread, messageUnread) -> callback.onUnreadCount(total));
    }

    public static void pollDetailed(@NonNull Context context, @Nullable DetailedCallback callback) {
        Context app = context.getApplicationContext();
        tAccUtils account = new tAccUtils(app);
        if (!account.isLogin()) {
            if (callback != null) callback.onUnread(0, 0, 0);
            return;
        }
        ensureChannel(app);

        final String selfUid = account.getUid();
        final String accountSuffix = "_" + selfUid;
        SharedPreferences prefs = app.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        final boolean[] initialized = {
                prefs.getBoolean(KEY_INITIALIZED + accountSuffix, false)
        };
        final String[] lastNotificationId = {
                prefString(prefs, KEY_LAST_NOTIFICATION_ID + accountSuffix, "")
        };
        final String[] lastMessageId = {
                prefString(prefs, KEY_LAST_MESSAGE_ID + accountSuffix, "")
        };
        final int[] unreadCount = {0};
        final int[] notificationUnread = {0};
        final int[] messageUnread = {0};
        final boolean[] notificationOk = {false};
        final boolean[] messageOk = {false};
        final boolean[] doNotDisturb = {isDoNotDisturb(app)};
        final AtomicInteger pending = new AtomicInteger(3);

        Runnable finish = () -> {
            if (pending.decrementAndGet() != 0) return;
            SharedPreferences.Editor editor = prefs.edit()
                    .putString(KEY_LAST_NOTIFICATION_ID + accountSuffix, lastNotificationId[0])
                    .putString(KEY_LAST_MESSAGE_ID + accountSuffix, lastMessageId[0]);
            if (!initialized[0] && notificationOk[0] && messageOk[0]) {
                editor.putBoolean(KEY_INITIALIZED + accountSuffix, true);
            }
            editor.apply();
            if (callback != null) {
                callback.onUnread(
                        doNotDisturb[0] ? 0 : unreadCount[0],
                        notificationUnread[0],
                        messageUnread[0]);
            }
        };

        account.getV2JsonFresh("inbox_unread_count2", new LinkedHashMap<>(),
                true, new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        unreadCount[0] = json.optInt("unread_count", 0);
                        notificationUnread[0] = json.optInt("notification_unread_count", 0);
                        messageUnread[0] = json.optInt("message_unread_count", 0);
                        finish.run();
                    }

                    @Override public void onError(int code, @NonNull String message) {
                        finish.run();
                    }
                });

        Map<String, String> notificationQuery = new LinkedHashMap<>();
        notificationQuery.put("page", "1");
        notificationQuery.put("size", "50");
        account.getV2JsonFresh("notifications2", notificationQuery, true,
                new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        JSONArray items = json.optJSONArray("items");
                        if (items != null) {
                            for (int i = 0; i < items.length(); i++) {
                                JSONObject item = items.optJSONObject(i);
                                if (item == null) continue;
                                String id = item.optString("id", "");
                                if (id.isEmpty() || id.equals(lastNotificationId[0])) break;
                                if (initialized[0] && !doNotDisturb[0]) {
                                    postSystemMessage(app, item);
                                }
                            }
                            JSONObject newest = items.length() > 0 ? items.optJSONObject(0) : null;
                            if (newest != null) {
                                lastNotificationId[0] = newest.optString("id", lastNotificationId[0]);
                            }
                        }
                        notificationOk[0] = true;
                        finish.run();
                    }

                    @Override public void onError(int code, @NonNull String message) {
                        finish.run();
                    }
                });

        Map<String, String> messageQuery = new LinkedHashMap<>();
        messageQuery.put("page", "1");
        messageQuery.put("size", "50");
        account.getV2JsonFresh("messages2", messageQuery, true,
                new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        JSONArray items = json.optJSONArray("items");
                        if (items != null) {
                            for (int i = 0; i < items.length(); i++) {
                                JSONObject item = items.optJSONObject(i);
                                if (item == null) continue;
                                String id = item.optString("id", "");
                                if (id.isEmpty() || id.equals(lastMessageId[0])) break;
                                boolean incoming = selfUid.equals(item.optString("recipient_uid"))
                                        && !selfUid.equals(item.optString("sender_uid"));
                                if (initialized[0] && !doNotDisturb[0] && incoming) {
                                    postPrivateMessage(app, item);
                                }
                            }
                            JSONObject newest = items.length() > 0 ? items.optJSONObject(0) : null;
                            if (newest != null) {
                                lastMessageId[0] = newest.optString("id", lastMessageId[0]);
                            }
                        }
                        messageOk[0] = true;
                        finish.run();
                    }

                    @Override public void onError(int code, @NonNull String message) {
                        finish.run();
                    }
        });
    }

    /**
     * 实时事件入口（WebSocket）。
     *
     * 只有在"初始化快照已完成"之后才提醒，避免首次连接把历史消息全部弹出来；
     * 事件 ID 与轮询共用同一套 last_id，重复下发不会重复提醒。
     */
    public static void handleRealtimeEvent(@NonNull Context context, @NonNull String type,
                                           @NonNull JSONObject data) {
        Context app = context.getApplicationContext();
        tAccUtils account = new tAccUtils(app);
        if (!account.isLogin()) return;
        ensureChannel(app);

        String selfUid = account.getUid();
        String suffix = "_" + selfUid;
        SharedPreferences prefs = app.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        boolean initialized = prefs.getBoolean(KEY_INITIALIZED + suffix, false);
        boolean doNotDisturb = isDoNotDisturb(app);

        if ("message.new".equals(type)) {
            String id = data.optString("id", "");
            String senderUid = data.optString("peer_uid", "");
            if (id.isEmpty() || senderUid.isEmpty() || senderUid.equals(selfUid)) return;
            String lastId = prefString(prefs, KEY_LAST_MESSAGE_ID + suffix, "");
            if (!id.equals(lastId)) {
                prefs.edit().putString(KEY_LAST_MESSAGE_ID + suffix, id).apply();
            }
            if (initialized && !doNotDisturb && !id.equals(lastId)) {
                try {
                    JSONObject item = new JSONObject();
                    item.put("id", id);
                    item.put("sender_uid", senderUid);
                    item.put("recipient_uid", selfUid);
                    item.put("sender_nick", data.optString("nick", "用户"));
                    item.put("content", data.optString("text", ""));
                    item.put("created_at", data.optString("created_at", ""));
                    postPrivateMessage(app, item);
                } catch (Exception ignored) {
                }
            }
            return;
        }

        if ("notification.new".equals(type)) {
            String id = data.optString("id", "");
            if (id.isEmpty()) return;
            String lastId = prefString(prefs, KEY_LAST_NOTIFICATION_ID + suffix, "");
            if (!id.equals(lastId)) {
                prefs.edit().putString(KEY_LAST_NOTIFICATION_ID + suffix, id).apply();
            }
            if (initialized && !doNotDisturb && !id.equals(lastId)) {
                try {
                    JSONObject item = new JSONObject();
                    item.put("id", id);
                    item.put("title", data.optString("title", "系统消息"));
                    item.put("content", data.optString("content", ""));
                    postSystemMessage(app, item);
                } catch (Exception ignored) {
                }
            }
        }
    }

    /** 首次连接时确定"初始快照已完成"，之后才允许弹通知。 */
    public static void markInitialized(@NonNull Context context) {
        Context app = context.getApplicationContext();
        tAccUtils account = new tAccUtils(app);
        String uid = account.getUid();
        if (uid == null || uid.isEmpty()) return;
        app.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit()
                .putBoolean(KEY_INITIALIZED + "_" + uid, true).apply();
    }

    public static boolean isInitialized(@NonNull Context context) {
        Context app = context.getApplicationContext();
        tAccUtils account = new tAccUtils(app);
        String uid = account.getUid();
        if (uid == null || uid.isEmpty()) return false;
        return app.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .getBoolean(KEY_INITIALIZED + "_" + uid, false);
    }

    /**
     * 消息渠道是否仍是"高重要性"。
     *
     * 用户或国产 ROM 的通知管理页面一旦改动过渠道重要性，渠道会带上
     * USER_LOCKED_IMPORTANCE，应用再改也无效，此时不会出现横幅提醒。
     */
    public static boolean isMessageChannelAlerting(@NonNull Context context) {
        return com.typheye.wgpro.core.NotificationChannels.isHighImportance(context,
                com.typheye.wgpro.core.NotificationChannels.MESSAGES);
    }

    /** 打开某个通知渠道的系统设置页。 */
    public static Intent channelSettingsIntent(@NonNull Context context, @NonNull String channelId) {
        return com.typheye.wgpro.core.NotificationChannels.settingsIntent(context, channelId);
    }

    /** 打开应用的通知总设置页。 */
    public static Intent appNotificationSettingsIntent(@NonNull Context context) {
        return com.typheye.wgpro.core.NotificationChannels.appSettingsIntent(context);
    }

    public static String messageChannelId() {
        return com.typheye.wgpro.core.NotificationChannels.MESSAGES;
    }

    /** md5 标识无法当 Android 通知 id，这里取哈希稳定映射到整数。 */
    /** 旧版本把这些键存成 long，读取时兼容类型变化。 */
    private static String prefString(SharedPreferences prefs, String key, String def) {
        try { return prefs.getString(key, def); } catch (ClassCastException e) { return def; }
    }

    private static int notificationIdFor(int base, String id) {
        return base + (Math.abs(id == null ? 0 : id.hashCode()) % 100_000_000);
    }

    private static void postSystemMessage(Context context, JSONObject item) {
        // 用户正在系统消息页：不再打扰
        if (com.typheye.wgpro.core.state.AppState.get().isSystemMessagesVisible()) {
            cancelSystemNotifications(context);
            return;
        }
        // 与站内卡片用同一套规则清洗：旧举报通知标题里的「（编号 #N）」不再出现在 Android 通知上。
        String title = SystemMessageTitle.clean(item.optString("title", "系统消息"));
        if (title.isEmpty()) title = "系统消息";
        String content = item.optString("content", "");
        Intent intent = new Intent(context, ChatActivity.class)
                .putExtra(ChatActivity.EXTRA_MODE, ChatActivity.MODE_SYSTEM_MESSAGES)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        int notificationId = notificationIdFor(2_000_000, item.optString("id", ""));
        post(context, notificationId, title, content, intent,
                NotificationCompat.CATEGORY_MESSAGE,
                NotificationAvatar.appIcon(context), false);
        rememberNotification(context, KEY_SYSTEM_NOTIFICATION_IDS,
                String.valueOf(notificationId));
    }

    private static void postPrivateMessage(Context context, JSONObject item) {
        String peerUid = item.optString("sender_uid", "");
        if (peerUid.isEmpty()) return;
        // 用户正在和这个人聊天：不打扰，同时清掉该会话已有通知
        if (com.typheye.wgpro.core.state.AppState.get().isChatOpenFor(peerUid)) {
            cancelPrivateNotifications(context, peerUid);
            return;
        }
        String peerName = item.optString("sender_nick", "用户");
        String content = item.optString("content", "");
        Intent intent = new Intent(context, ChatActivity.class)
                .putExtra(ChatActivity.EXTRA_PEER_UID, peerUid)
                .putExtra(ChatActivity.EXTRA_PEER_NAME, peerName)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        int notificationId = notificationIdFor(3_000_000, item.optString("id", ""));
        // 主图标先用昵称首字，真实头像异步加载完成后原地更新同一条通知
        post(context, notificationId, peerName, content, intent,
                NotificationCompat.CATEGORY_MESSAGE,
                NotificationAvatar.textAvatar(peerName), false);
        rememberNotification(context, KEY_PRIVATE_NOTIFICATION_IDS,
                peerUid + ":" + notificationId);

        String avatarUrl = item.optString("sender_avatar_url", "");
        if (avatarUrl.isEmpty()) avatarUrl = NotificationAvatar.avatarUrlFor(peerUid);
        final String url = avatarUrl;
        if (!url.isEmpty()) {
            ImageCache.loadBitmap(context, url, bitmap -> {
                if (bitmap == null) return;
                post(context, notificationId, peerName, content, intent,
                        NotificationCompat.CATEGORY_MESSAGE, bitmap, true);
            });
        }
    }

    private static void post(Context context, int id, String title, String content,
                             Intent intent, String category) {
        post(context, id, title, content, intent, category, null, false);
    }

    private static void post(Context context, int id, String title, String content,
                             Intent intent, String category,
                             @androidx.annotation.Nullable android.graphics.Bitmap largeIcon,
                             boolean silentUpdate) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, id, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,
                com.typheye.wgpro.core.NotificationChannels.MESSAGES)
                // 副图标：腕管 Logo（状态栏小图标）
                .setSmallIcon(R.drawable.ic_notifications_vector)
                .setContentTitle(title == null || title.trim().isEmpty() ? "Typheye账户" : title)
                .setContentText(content)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(content))
                // 高优先级 + 默认提醒：渠道已是 IMPORTANCE_HIGH，这里再显式声明一次，
                // 部分国产 ROM 会同时参考 priority 与渠道重要性。
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setCategory(category)
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                .setWhen(System.currentTimeMillis())
                .setOnlyAlertOnce(false)
                // 不设置 group：分组通知在部分 ROM 上会只弹分组摘要，导致没有横幅
                .setContentIntent(pendingIntent)
                .setTicker(title)
                .setAutoCancel(true);
        // 主图标：发送者头像（文字头像或自定义头像）
        if (largeIcon != null) builder.setLargeIcon(largeIcon);
        if (silentUpdate) builder.setOnlyAlertOnce(true);
        NotificationManager manager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) manager.notify(id, builder.build());
    }

    private static void ensureChannel(Context context) {
        // 渠道定义集中在 NotificationChannels：
        // 「消息通知」「Typheye账户」高重要性（横幅/声音/震动），「后台运行状态」最低重要性
        com.typheye.wgpro.core.NotificationChannels.ensureAll(context);
    }

    private static void rememberNotification(Context context, String key, String value) {
        tAccUtils account = new tAccUtils(context.getApplicationContext());
        String uid = account.getUid();
        if (uid == null || uid.isEmpty()) return;
        SharedPreferences prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        java.util.Set<String> values = new java.util.HashSet<>(
                prefs.getStringSet(key + "_" + uid, java.util.Collections.emptySet()));
        values.add(value);
        prefs.edit().putStringSet(key + "_" + uid, values).apply();
    }

    public static void cancelSystemNotifications(@NonNull Context context) {
        cancelNotifications(context, KEY_SYSTEM_NOTIFICATION_IDS, null);
    }

    public static void cancelPrivateNotifications(@NonNull Context context,
                                                  @NonNull String peerUid) {
        cancelNotifications(context, KEY_PRIVATE_NOTIFICATION_IDS, peerUid);
    }

    private static void cancelNotifications(Context context, String key,
                                            @Nullable String peerUid) {
        tAccUtils account = new tAccUtils(context.getApplicationContext());
        String uid = account.getUid();
        if (uid == null || uid.isEmpty()) return;
        SharedPreferences prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        java.util.Set<String> values = new java.util.HashSet<>(
                prefs.getStringSet(key + "_" + uid, java.util.Collections.emptySet()));
        if (values.isEmpty()) return;
        NotificationManager manager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        java.util.Iterator<String> iterator = values.iterator();
        while (iterator.hasNext()) {
            String value = iterator.next();
            if (peerUid != null) {
                String prefix = peerUid + ":";
                if (!value.startsWith(prefix)) continue;
                try { manager.cancel(Integer.parseInt(value.substring(prefix.length()))); }
                catch (Exception ignored) { }
                iterator.remove();
            } else {
                try { manager.cancel(Integer.parseInt(value)); }
                catch (Exception ignored) { }
                iterator.remove();
            }
        }
        prefs.edit().putStringSet(key + "_" + uid, values).apply();
    }

    public static boolean isDoNotDisturb(@NonNull Context context) {
        tAccUtils account = new tAccUtils(context.getApplicationContext());
        String uid = account.getUid();
        if (uid == null || uid.isEmpty()) return false;
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .getBoolean("do_not_disturb_" + uid, false);
    }

    public static void setDoNotDisturb(@NonNull Context context, boolean enabled) {
        tAccUtils account = new tAccUtils(context.getApplicationContext());
        String uid = account.getUid();
        if (uid == null || uid.isEmpty()) return;
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit()
                .putBoolean("do_not_disturb_" + uid, enabled).apply();
    }
}
