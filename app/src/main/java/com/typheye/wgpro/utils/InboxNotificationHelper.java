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
import com.typheye.wgpro.ui.function.community.NotificationActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/** 使用主循环轮询未读通知和私信，并通过“Typheye账户”通知渠道提醒用户。 */
public final class InboxNotificationHelper {
    private static final String CHANNEL_ID = "account_channel";
    private static final String PREFS = "inbox_notification_state";
    private static final String KEY_INITIALIZED = "initialized";
    private static final String KEY_LAST_NOTIFICATION_ID = "last_notification_id";
    private static final String KEY_LAST_MESSAGE_ID = "last_message_id";

    private InboxNotificationHelper() { }

    public interface Callback {
        void onUnreadCount(int count);
    }

    public static void poll(@NonNull Context context, @Nullable Callback callback) {
        Context app = context.getApplicationContext();
        tAccUtils account = new tAccUtils(app);
        if (!account.isLogin()) {
            if (callback != null) callback.onUnreadCount(0);
            return;
        }
        ensureChannel(app);

        final String selfUid = account.getUid();
        final String accountSuffix = "_" + selfUid;
        SharedPreferences prefs = app.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        final boolean[] initialized = {
                prefs.getBoolean(KEY_INITIALIZED + accountSuffix, false)
        };
        final long[] lastNotificationId = {
                prefs.getLong(KEY_LAST_NOTIFICATION_ID + accountSuffix, 0L)
        };
        final long[] lastMessageId = {
                prefs.getLong(KEY_LAST_MESSAGE_ID + accountSuffix, 0L)
        };
        final int[] unreadCount = {0};
        final boolean[] notificationOk = {false};
        final boolean[] messageOk = {false};
        final boolean[] doNotDisturb = {isDoNotDisturb(app)};
        final AtomicInteger pending = new AtomicInteger(3);

        Runnable finish = () -> {
            if (pending.decrementAndGet() != 0) return;
            SharedPreferences.Editor editor = prefs.edit()
                    .putLong(KEY_LAST_NOTIFICATION_ID + accountSuffix, lastNotificationId[0])
                    .putLong(KEY_LAST_MESSAGE_ID + accountSuffix, lastMessageId[0]);
            if (!initialized[0] && notificationOk[0] && messageOk[0]) {
                editor.putBoolean(KEY_INITIALIZED + accountSuffix, true);
            }
            editor.apply();
            if (callback != null) callback.onUnreadCount(
                    doNotDisturb[0] ? 0 : unreadCount[0]);
        };

        account.getV2JsonFresh("inbox_unread_count2", new LinkedHashMap<>(),
                true, new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        unreadCount[0] = json.optInt("unread_count", 0);
                        finish.run();
                    }

                    @Override public void onError(int code, @NonNull String message) {
                        finish.run();
                    }
                });

        Map<String, String> notificationQuery = new LinkedHashMap<>();
        notificationQuery.put("page", "1");
        notificationQuery.put("size", "50");
        if (lastNotificationId[0] > 0) {
            notificationQuery.put("since_id", String.valueOf(lastNotificationId[0]));
        }
        account.getV2JsonFresh("notifications2", notificationQuery, true,
                new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        JSONArray items = json.optJSONArray("items");
                        long maxId = lastNotificationId[0];
                        if (items != null) {
                            for (int i = 0; i < items.length(); i++) {
                                JSONObject item = items.optJSONObject(i);
                                if (item == null) continue;
                                long id = item.optLong("id", 0L);
                                if (id > maxId) maxId = id;
                                if (initialized[0] && !doNotDisturb[0]
                                        && id > lastNotificationId[0]) {
                                    postSystemMessage(app, item);
                                }
                            }
                        }
                        lastNotificationId[0] = maxId;
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
        if (lastMessageId[0] > 0) {
            messageQuery.put("since_id", String.valueOf(lastMessageId[0]));
        }
        account.getV2JsonFresh("messages2", messageQuery, true,
                new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        JSONArray items = json.optJSONArray("items");
                        long maxId = lastMessageId[0];
                        if (items != null) {
                            for (int i = 0; i < items.length(); i++) {
                                JSONObject item = items.optJSONObject(i);
                                if (item == null) continue;
                                long id = item.optLong("id", 0L);
                                if (id > maxId) maxId = id;
                                boolean incoming = selfUid.equals(item.optString("recipient_uid"))
                                        && !selfUid.equals(item.optString("sender_uid"));
                                if (initialized[0] && !doNotDisturb[0]
                                        && incoming && id > lastMessageId[0]) {
                                    postPrivateMessage(app, item);
                                }
                            }
                        }
                        lastMessageId[0] = maxId;
                        messageOk[0] = true;
                        finish.run();
                    }

                    @Override public void onError(int code, @NonNull String message) {
                        finish.run();
                    }
                });
    }

    private static void postSystemMessage(Context context, JSONObject item) {
        String title = item.optString("title", "系统消息");
        String content = item.optString("content", "");
        Intent intent = new Intent(context, NotificationActivity.class)
                .putExtra(NotificationActivity.EXTRA_OPEN_SYSTEM_MESSAGES, true)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        post(context, (int) (2_000_000L + item.optLong("id", 0L) % 100_000_000L),
                title, content, intent, NotificationCompat.CATEGORY_MESSAGE);
    }

    private static void postPrivateMessage(Context context, JSONObject item) {
        String peerUid = item.optString("sender_uid", "");
        if (peerUid.isEmpty()) return;
        String peerName = item.optString("sender_nick", "用户");
        String content = item.optString("content", "");
        Intent intent = new Intent(context, ChatActivity.class)
                .putExtra(ChatActivity.EXTRA_PEER_UID, peerUid)
                .putExtra(ChatActivity.EXTRA_PEER_NAME, peerName)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TOP
                        | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        post(context, (int) (3_000_000L + item.optLong("id", 0L) % 100_000_000L),
                peerName, content, intent, NotificationCompat.CATEGORY_MESSAGE);
    }

    private static void post(Context context, int id, String title, String content,
                             Intent intent, String category) {
        PendingIntent pendingIntent = PendingIntent.getActivity(context, id, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications_vector)
                .setContentTitle(title == null || title.trim().isEmpty() ? "Typheye账户" : title)
                .setContentText(content)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(content))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(category)
                .setGroup("typheye_inbox")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManager manager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(id, builder.build());
    }

    private static void ensureChannel(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return;
        NotificationManager manager = context.getSystemService(NotificationManager.class);
        if (manager.getNotificationChannel(CHANNEL_ID) != null) return;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Typheye账户",
                NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription("与您账户相关的通知");
        channel.setShowBadge(true);
        channel.setLockscreenVisibility(android.app.Notification.VISIBILITY_PUBLIC);
        manager.createNotificationChannel(channel);
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
