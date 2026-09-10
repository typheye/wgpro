package com.typheye.wgpro.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/** 通知中心本地状态库：只保存设备侧清空、移除、已读/未读覆盖，不存云端消息正文。 */
public final class MessageDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "message_local.db";
    private static final int DB_VERSION = 4;

    public MessageDatabase(Context context) {
        super(context.getApplicationContext(), DB_NAME, null, DB_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE system_state("
                + "uid TEXT NOT NULL PRIMARY KEY,"
                + "cleared_before_id INTEGER NOT NULL DEFAULT 0,"
                + "unread_override INTEGER NOT NULL DEFAULT 0,"
                + "removed INTEGER NOT NULL DEFAULT 0,"
                + "updated_at INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE conversation_state("
                + "uid TEXT NOT NULL,"
                + "peer_uid TEXT NOT NULL,"
                + "unread_override INTEGER NOT NULL DEFAULT 0,"
                + "cleared_before_id INTEGER NOT NULL DEFAULT 0,"
                + "removed INTEGER NOT NULL DEFAULT 0,"
                + "updated_at INTEGER NOT NULL,"
                + "PRIMARY KEY(uid,peer_uid))");
        db.execSQL("CREATE TABLE conversation_messages("
                + "uid TEXT NOT NULL,"
                + "peer_uid TEXT NOT NULL,"
                + "last_message_id INTEGER NOT NULL DEFAULT 0,"
                + "last_message TEXT NOT NULL DEFAULT '',"
                + "last_message_at TEXT NOT NULL DEFAULT '',"
                + "updated_at INTEGER NOT NULL,"
                + "PRIMARY KEY(uid,peer_uid))");
        db.execSQL("CREATE TABLE system_messages("
                + "uid TEXT NOT NULL,"
                + "notification_id INTEGER NOT NULL,"
                + "title TEXT NOT NULL DEFAULT '',"
                + "content TEXT NOT NULL DEFAULT '',"
                + "metadata TEXT NOT NULL DEFAULT '{}',"
                + "target_type TEXT NOT NULL DEFAULT '',"
                + "target_key TEXT NOT NULL DEFAULT '',"
                + "created_at TEXT NOT NULL DEFAULT '',"
                + "is_read INTEGER NOT NULL DEFAULT 0,"
                + "deleted INTEGER NOT NULL DEFAULT 0,"
                + "updated_at INTEGER NOT NULL,"
                + "PRIMARY KEY(uid,notification_id))");
    }

    /**
     * 开发阶段直接重建缓存表：系统消息缓存是服务端数据的副本，可以随时重新拉取。
     * （4：新增 target_type / target_key，报告类系统消息的动作行依赖它们。）
     */
    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 4) {
            db.execSQL("DROP TABLE IF EXISTS system_messages");
            db.execSQL("CREATE TABLE system_messages("
                    + "uid TEXT NOT NULL,"
                    + "notification_id INTEGER NOT NULL,"
                    + "title TEXT NOT NULL DEFAULT '',"
                    + "content TEXT NOT NULL DEFAULT '',"
                    + "metadata TEXT NOT NULL DEFAULT '{}',"
                    + "target_type TEXT NOT NULL DEFAULT '',"
                    + "target_key TEXT NOT NULL DEFAULT '',"
                    + "created_at TEXT NOT NULL DEFAULT '',"
                    + "is_read INTEGER NOT NULL DEFAULT 0,"
                    + "deleted INTEGER NOT NULL DEFAULT 0,"
                    + "updated_at INTEGER NOT NULL,"
                    + "PRIMARY KEY(uid,notification_id))");
        }
    }

    public long getSystemClearedBefore(String uid) {
        return queryLong("SELECT cleared_before_id FROM system_state WHERE uid=?",
                new String[]{uid});
    }

    public void setSystemClearedBefore(String uid, long id) {
        ContentValues values = new ContentValues();
        values.put("cleared_before_id", Math.max(0L, id));
        upsertSystemState(uid, values);
    }

    public boolean isSystemUnreadOverride(String uid) {
        return queryLong("SELECT unread_override FROM system_state WHERE uid=?",
                new String[]{uid}) > 0;
    }

    public void setSystemUnreadOverride(String uid, boolean enabled) {
        ContentValues values = new ContentValues();
        values.put("unread_override", enabled ? 1 : 0);
        upsertSystemState(uid, values);
    }

    public boolean isSystemRemoved(String uid) {
        return queryLong("SELECT removed FROM system_state WHERE uid=?",
                new String[]{uid}) > 0;
    }

    public void setSystemRemoved(String uid, boolean removed) {
        ContentValues values = new ContentValues();
        values.put("removed", removed ? 1 : 0);
        upsertSystemState(uid, values);
    }

    private void upsertSystemState(String uid, ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        values.put("updated_at", System.currentTimeMillis());
        int updated = db.update("system_state", values, "uid=?", new String[]{uid});
        if (updated > 0) return;
        ContentValues insert = new ContentValues(values);
        insert.put("uid", uid);
        db.insertWithOnConflict("system_state", null, insert,
                SQLiteDatabase.CONFLICT_IGNORE);
    }

    public boolean isConversationUnreadOverride(String uid, String peerUid) {
        return queryLong("SELECT unread_override FROM conversation_state "
                        + "WHERE uid=? AND peer_uid=?",
                new String[]{uid, peerUid}) > 0;
    }

    public void setConversationUnreadOverride(String uid, String peerUid, boolean enabled) {
        ContentValues values = new ContentValues();
        values.put("unread_override", enabled ? 1 : 0);
        upsertConversationState(uid, peerUid, values);
    }

    public long getConversationClearedBefore(String uid, String peerUid) {
        return queryLong("SELECT cleared_before_id FROM conversation_state "
                        + "WHERE uid=? AND peer_uid=?",
                new String[]{uid, peerUid});
    }

    public void setConversationClearedBefore(String uid, String peerUid, long id) {
        ContentValues values = new ContentValues();
        values.put("cleared_before_id", Math.max(0L, id));
        upsertConversationState(uid, peerUid, values);
    }

    public boolean isConversationRemoved(String uid, String peerUid) {
        return queryLong("SELECT removed FROM conversation_state "
                        + "WHERE uid=? AND peer_uid=?",
                new String[]{uid, peerUid}) > 0;
    }

    public void setConversationRemoved(String uid, String peerUid, boolean removed) {
        ContentValues values = new ContentValues();
        values.put("removed", removed ? 1 : 0);
        upsertConversationState(uid, peerUid, values);
    }

    private void upsertConversationState(String uid, String peerUid, ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        values.put("updated_at", System.currentTimeMillis());
        int updated = db.update("conversation_state", values,
                "uid=? AND peer_uid=?", new String[]{uid, peerUid});
        if (updated > 0) return;
        ContentValues insert = new ContentValues(values);
        insert.put("uid", uid);
        insert.put("peer_uid", peerUid);
        db.insertWithOnConflict("conversation_state", null, insert,
                SQLiteDatabase.CONFLICT_IGNORE);
    }

    public void clearAll(String uid) {
        getWritableDatabase().delete("system_state", "uid=?", new String[]{uid});
        getWritableDatabase().delete("conversation_state", "uid=?", new String[]{uid});
        getWritableDatabase().delete("conversation_messages", "uid=?", new String[]{uid});
        getWritableDatabase().delete("system_messages", "uid=?", new String[]{uid});
    }

    public void upsertConversationPreview(String uid, String peerUid, long messageId,
                                          String message, String createdAt) {
        ContentValues values = new ContentValues();
        values.put("uid", uid);
        values.put("peer_uid", peerUid);
        values.put("last_message_id", messageId);
        values.put("last_message", message == null ? "" : message);
        values.put("last_message_at", createdAt == null ? "" : createdAt);
        values.put("updated_at", System.currentTimeMillis());
        getWritableDatabase().insertWithOnConflict(
                "conversation_messages", null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public ConversationPreview getConversationPreview(String uid, String peerUid) {
        try (Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT last_message_id,last_message,last_message_at FROM conversation_messages "
                        + "WHERE uid=? AND peer_uid=?",
                new String[]{uid, peerUid})) {
            if (!cursor.moveToFirst()) return null;
            ConversationPreview preview = new ConversationPreview();
            preview.messageId = cursor.getLong(0);
            preview.message = cursor.getString(1);
            preview.createdAt = cursor.getString(2);
            return preview;
        }
    }

    public void clearConversationPreview(String uid, String peerUid) {
        getWritableDatabase().delete("conversation_messages", "uid=? AND peer_uid=?",
                new String[]{uid, peerUid});
    }

    public void replaceSystemMessages(String uid, List<SystemMessage> messages) {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues stale = new ContentValues();
            stale.put("deleted", 1);
            stale.put("updated_at", System.currentTimeMillis());
            db.update("system_messages", stale, "uid=?", new String[]{uid});
            for (SystemMessage message : messages) {
                ContentValues values = new ContentValues();
                values.put("uid", uid);
                values.put("notification_id", message.id);
                values.put("title", message.title);
                values.put("content", message.content);
                values.put("metadata", message.metadata);
                values.put("target_type", message.targetType);
                values.put("target_key", message.targetKey);
                values.put("created_at", message.createdAt);
                values.put("is_read", message.isRead ? 1 : 0);
                values.put("deleted", 0);
                values.put("updated_at", System.currentTimeMillis());
                db.insertWithOnConflict("system_messages", null, values,
                        SQLiteDatabase.CONFLICT_REPLACE);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public List<SystemMessage> getSystemMessages(String uid) {
        List<SystemMessage> messages = new ArrayList<>();
        try (Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT notification_id,title,content,metadata,created_at,is_read,"
                        + "target_type,target_key "
                        + "FROM system_messages WHERE uid=? AND deleted=0 "
                        + "ORDER BY notification_id DESC",
                new String[]{uid})) {
            while (cursor.moveToNext()) {
                SystemMessage message = new SystemMessage();
                message.id = cursor.getLong(0);
                message.title = cursor.getString(1);
                message.content = cursor.getString(2);
                message.metadata = cursor.getString(3);
                message.createdAt = cursor.getString(4);
                message.isRead = cursor.getInt(5) != 0;
                message.targetType = cursor.getString(6);
                message.targetKey = cursor.getString(7);
                messages.add(message);
            }
        }
        return messages;
    }

    public boolean hasSystemMessageHistory(String uid) {
        return queryLong("SELECT COUNT(*) FROM system_messages WHERE uid=?",
                new String[]{uid}) > 0;
    }

    public void markSystemMessageRead(String uid, long id, boolean read) {
        ContentValues values = new ContentValues();
        values.put("is_read", read ? 1 : 0);
        values.put("updated_at", System.currentTimeMillis());
        getWritableDatabase().update("system_messages", values,
                "uid=? AND notification_id=?", new String[]{uid, String.valueOf(id)});
    }

    public void deleteSystemMessage(String uid, long id) {
        ContentValues values = new ContentValues();
        values.put("deleted", 1);
        values.put("updated_at", System.currentTimeMillis());
        getWritableDatabase().update("system_messages", values,
                "uid=? AND notification_id=?", new String[]{uid, String.valueOf(id)});
    }

    private long queryLong(String sql, String[] args) {
        try (Cursor cursor = getReadableDatabase().rawQuery(sql, args)) {
            if (!cursor.moveToFirst() || cursor.isNull(0)) return 0L;
            return cursor.getLong(0);
        }
    }

    public static final class ConversationPreview {
        public long messageId;
        public String message = "";
        public String createdAt = "";
    }

    public static final class SystemMessage {
        public long id;
        public String title = "";
        public String content = "";
        public String metadata = "{}";
        public String targetType = "";
        public String targetKey = "";
        public String createdAt = "";
        public boolean isRead;
    }
}
