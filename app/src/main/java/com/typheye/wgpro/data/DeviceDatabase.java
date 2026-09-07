package com.typheye.wgpro.data;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;

import androidx.annotation.Nullable;

public final class DeviceDatabase extends SQLiteOpenHelper {
    public static final String DB = "devices.db";

    public DeviceDatabase(Context c) {
        super(c, DB, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE devices(id TEXT PRIMARY KEY, model TEXT NOT NULL, type TEXT NOT NULL, version TEXT, connected INTEGER, last_seen INTEGER, note TEXT)");
    }

    public void onUpgrade(SQLiteDatabase db, int o, int n) {
    }

    public boolean exists(String id) {
        Cursor c = getReadableDatabase().rawQuery("SELECT 1 FROM devices WHERE id=?", new String[]{id});
        boolean x = c.moveToFirst();
        c.close();
        return x;
    }

    public void upsert(String id, String model, String type, boolean connected) {
        ContentValues v = new ContentValues();
        v.put("id", id);
        v.put("model", model);
        v.put("type", type);
        v.put("version", "HyperOS 2.0");
        v.put("connected", connected ? 1 : 0);
        v.put("last_seen", System.currentTimeMillis());
        getWritableDatabase().insertWithOnConflict("devices", null, v, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public void remove(String id) {
        getWritableDatabase().delete("devices", "id=?", new String[]{id});
    }

    public void updateNote(String id, String note) {
        ContentValues v = new ContentValues();
        v.put("note", note);
        getWritableDatabase().update("devices", v, "id=?", new String[]{id});
    }

    public void updateConnection(String id, boolean connected) {
        ContentValues v = new ContentValues();
        v.put("connected", connected ? 1 : 0);
        v.put("last_seen", System.currentTimeMillis());
        getWritableDatabase().update("devices", v, "id=?", new String[]{id});
    }

    public Cursor get(String id) {
        return getReadableDatabase().rawQuery("SELECT * FROM devices WHERE id=?", new String[]{id});
    }

    public Cursor all(String type) {
        return getReadableDatabase().rawQuery("SELECT * FROM devices WHERE type=? ORDER BY last_seen DESC", new String[]{type});
    }

    public Cursor allExcept(String type) {
        return getReadableDatabase().rawQuery(
                "SELECT * FROM devices WHERE type<>? ORDER BY last_seen DESC", new String[]{type});
    }
}
