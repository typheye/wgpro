package com.typheye.wgpro.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/** 通知中心本地状态库：只保存设备侧清空、移除、已读/未读覆盖，不存云端消息正文。 */
public final class MessageDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "message_local.db";
    private static final int DB_VERSION = 1;

    public MessageDatabase(Context context) {
        super(context.getApplicationContext(), DB_NAME, null, DB_VERSION);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE system_state("
                + "uid TEXT NOT NULL PRIMARY KEY,"
                + "cleared_before_id INTEGER NOT NULL DEFAULT 0,"
                + "unread_override INTEGER NOT NULL DEFAULT 0,"
                + "updated_at INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE conversation_state("
                + "uid TEXT NOT NULL,"
                + "peer_uid TEXT NOT NULL,"
                + "unread_override INTEGER NOT NULL DEFAULT 0,"
                + "removed INTEGER NOT NULL DEFAULT 0,"
                + "updated_at INTEGER NOT NULL,"
                + "PRIMARY KEY(uid,peer_uid))");
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

    public long getSystemClearedBefore(String uid) {
        return queryLong("SELECT cleared_before_id FROM system_state WHERE uid=?",
                new String[]{uid});
    }

    public void setSystemClearedBefore(String uid, long id) {
        ContentValues values = new ContentValues();
        values.put("uid", uid);
        values.put("cleared_before_id", Math.max(0L, id));
        values.put("updated_at", System.currentTimeMillis());
        getWritableDatabase().insertWithOnConflict(
                "system_state", null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public boolean isSystemUnreadOverride(String uid) {
        return queryLong("SELECT unread_override FROM system_state WHERE uid=?",
                new String[]{uid}) > 0;
    }

    public void setSystemUnreadOverride(String uid, boolean enabled) {
        ContentValues values = new ContentValues();
        values.put("uid", uid);
        values.put("unread_override", enabled ? 1 : 0);
        values.put("updated_at", System.currentTimeMillis());
        getWritableDatabase().insertWithOnConflict(
                "system_state", null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public boolean isConversationUnreadOverride(String uid, String peerUid) {
        return queryLong("SELECT unread_override FROM conversation_state "
                        + "WHERE uid=? AND peer_uid=?",
                new String[]{uid, peerUid}) > 0;
    }

    public void setConversationUnreadOverride(String uid, String peerUid, boolean enabled) {
        ContentValues values = new ContentValues();
        values.put("uid", uid);
        values.put("peer_uid", peerUid);
        values.put("unread_override", enabled ? 1 : 0);
        values.put("updated_at", System.currentTimeMillis());
        getWritableDatabase().insertWithOnConflict(
                "conversation_state", null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public boolean isConversationRemoved(String uid, String peerUid) {
        return queryLong("SELECT removed FROM conversation_state "
                        + "WHERE uid=? AND peer_uid=?",
                new String[]{uid, peerUid}) > 0;
    }

    public void setConversationRemoved(String uid, String peerUid, boolean removed) {
        ContentValues values = new ContentValues();
        values.put("uid", uid);
        values.put("peer_uid", peerUid);
        values.put("removed", removed ? 1 : 0);
        values.put("updated_at", System.currentTimeMillis());
        getWritableDatabase().insertWithOnConflict(
                "conversation_state", null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public void clearAll(String uid) {
        getWritableDatabase().delete("system_state", "uid=?", new String[]{uid});
        getWritableDatabase().delete("conversation_state", "uid=?", new String[]{uid});
    }

    private long queryLong(String sql, String[] args) {
        try (Cursor cursor = getReadableDatabase().rawQuery(sql, args)) {
            if (!cursor.moveToFirst() || cursor.isNull(0)) return 0L;
            return cursor.getLong(0);
        }
    }
}
