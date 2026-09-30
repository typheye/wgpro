package com.typheye.wgpro.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/** Persistent last-known-good JSON responses for cloud-backed read screens. */
public final class CloudCacheDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "cloud_cache.db";

    public CloudCacheDatabase(@NonNull Context context) {
        super(context.getApplicationContext(), DB_NAME, null, 2);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE responses(cache_key TEXT PRIMARY KEY, payload TEXT NOT NULL, updated_at INTEGER NOT NULL)");
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 2：服务端标识改为 md5，旧缓存（数字 id）全部作废重建
        db.execSQL("DROP TABLE IF EXISTS responses");
        onCreate(db);
    }

    public void put(@NonNull String key, @NonNull String payload) {
        ContentValues values = new ContentValues();
        values.put("cache_key", key);
        values.put("payload", payload);
        values.put("updated_at", System.currentTimeMillis());
        getWritableDatabase().insertWithOnConflict("responses", null, values,
                SQLiteDatabase.CONFLICT_REPLACE);
    }

    @Nullable public String get(@NonNull String key) {
        try (Cursor cursor = getReadableDatabase().query("responses",
                new String[]{"payload"}, "cache_key=?", new String[]{key},
                null, null, null, "1")) {
            return cursor.moveToFirst() ? cursor.getString(0) : null;
        }
    }

    /**
     * 仅当这条缓存还在 {@code ttlMs} 内才返回。
     *
     * <p>用于「缓存优先」读取：变化很慢的数据（轮播、公告、应用目录、资源列表）
     * 在有效期内直接用本地内容渲染，一次网络往返都不发；过期后照常走网络，
     * 网络失败时仍由 {@link #get(String)} 兜底。</p>
     */
    @Nullable public String getFresh(@NonNull String key, long ttlMs) {
        if (ttlMs <= 0L) return null;
        try (Cursor cursor = getReadableDatabase().query("responses",
                new String[]{"payload", "updated_at"}, "cache_key=?", new String[]{key},
                null, null, null, "1")) {
            if (!cursor.moveToFirst()) return null;
            long age = System.currentTimeMillis() - cursor.getLong(1);
            return age >= 0L && age <= ttlMs ? cursor.getString(0) : null;
        }
    }
}
