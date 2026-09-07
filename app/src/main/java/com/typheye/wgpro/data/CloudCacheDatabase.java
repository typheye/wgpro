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
        super(context.getApplicationContext(), DB_NAME, null, 1);
    }

    @Override public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE responses(cache_key TEXT PRIMARY KEY, payload TEXT NOT NULL, updated_at INTEGER NOT NULL)");
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }

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
}
