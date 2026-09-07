package com.typheye.wgpro.data;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;

import androidx.annotation.Nullable;

public final class DeviceDatabase extends SQLiteOpenHelper {
    public static final String DB = "devices.db";
    private static final int VERSION = 2;

    public DeviceDatabase(Context c) {
        super(c, DB, null, VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE devices(id TEXT PRIMARY KEY, model TEXT NOT NULL, type TEXT NOT NULL, "
                + "version TEXT, connected INTEGER, last_seen INTEGER, note TEXT, created_at INTEGER, "
                + "connected_at INTEGER, disconnected_at INTEGER)");
    }

    public void onUpgrade(SQLiteDatabase db, int o, int n) {
        if (o < 2) {
            db.execSQL("ALTER TABLE devices ADD COLUMN created_at INTEGER");
            db.execSQL("ALTER TABLE devices ADD COLUMN connected_at INTEGER");
            db.execSQL("ALTER TABLE devices ADD COLUMN disconnected_at INTEGER");
            db.execSQL("UPDATE devices SET created_at=last_seen WHERE created_at IS NULL");
            db.execSQL("UPDATE devices SET connected_at=last_seen WHERE connected<>0 AND connected_at IS NULL");
            db.execSQL("UPDATE devices SET disconnected_at=last_seen WHERE connected=0 AND disconnected_at IS NULL");
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("UPDATE devices SET version='UnKnown Version' "
                + "WHERE version IS NULL OR TRIM(version)='' "
                + "OR version IN ('HyperOS 2.0','Unknow Version','Unknown Version')");
    }

    public boolean exists(String id) {
        Cursor c = getReadableDatabase().rawQuery("SELECT 1 FROM devices WHERE id=?", new String[]{id});
        boolean x = c.moveToFirst();
        c.close();
        return x;
    }

    public void upsert(String id, String model, String type, boolean connected) {
        long now = System.currentTimeMillis();
        ContentValues v = new ContentValues();
        v.put("id", id);
        v.put("model", model);
        v.put("type", type);
        v.put("version", "UnKnown Version");
        v.put("connected", connected ? 1 : 0);
        v.put("last_seen", now);
        v.put("created_at", now);
        if (connected) v.put("connected_at", now); else v.put("disconnected_at", now);
        getWritableDatabase().insertWithOnConflict("devices", null, v, SQLiteDatabase.CONFLICT_IGNORE);
    }

    public void remove(String id) {
        getWritableDatabase().delete("devices", "id=?", new String[]{id});
    }

    public void updateNote(String id, String note) {
        ContentValues v = new ContentValues();
        v.put("note", note);
        getWritableDatabase().update("devices", v, "id=?", new String[]{id});
    }

    public int updateConnection(String id, boolean connected) {
        long now = System.currentTimeMillis();
        ContentValues v = new ContentValues();
        v.put("connected", connected ? 1 : 0);
        v.put("last_seen", now);
        v.put(connected ? "connected_at" : "disconnected_at", now);
        return getWritableDatabase().update("devices", v, "id=? AND connected<>?",
                new String[]{id, connected ? "1" : "0"});
    }

    public int markConnectedDevicesOffline(String type) {
        long now = System.currentTimeMillis();
        ContentValues values = new ContentValues();
        values.put("connected", 0);
        values.put("last_seen", now);
        values.put("disconnected_at", now);
        return getWritableDatabase().update("devices", values,
                "type=? AND connected<>0", new String[]{type});
    }

    public int markOtherConnectedDevicesOffline(String type, String connectedId) {
        long now = System.currentTimeMillis();
        ContentValues values = new ContentValues();
        values.put("connected", 0);
        values.put("last_seen", now);
        values.put("disconnected_at", now);
        return getWritableDatabase().update("devices", values,
                "type=? AND id<>? AND connected<>0", new String[]{type, connectedId});
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
