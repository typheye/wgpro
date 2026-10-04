package open.cn.awg.pro.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.NativeDatabase;

public class LocalDatabaseBridge {
    public static String set17 = AppPaths.appPath("settings/db/talking.db");
    public static String set27 = AppPaths.appPath("settings/db/file.db");
    public static String set47 = AppPaths.appPath("settings/db/plugin.db");

    private final Context context;
    private SQLiteDatabase talkDb;
    private SQLiteDatabase fileDb;
    private SQLiteDatabase pluginDb;

    public LocalDatabaseBridge(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        Context applicationContext = context.getApplicationContext();
        this.context = applicationContext == null ? context : applicationContext;
    }

    public synchronized SQLiteDatabase getTalkDatabase() {
        talkDb = openIfNeeded(talkDb, set17);
        return talkDb;
    }

    public synchronized SQLiteDatabase getFileDatabase() {
        fileDb = openIfNeeded(fileDb, set27);
        return fileDb;
    }

    public synchronized SQLiteDatabase getPluginDatabase() {
        pluginDb = openIfNeeded(pluginDb, set47);
        return pluginDb;
    }

    public void setup_talklist() {
        ensureTable(getTalkDatabase(), "talklist",
                "_id integer primary key,uid interger, name text,utext text");
    }

    public boolean add_talklist(Object uid, Object name, Object text) {
        SQLiteDatabase database = getTalkDatabase();
        ensureTable(database, "talklist", "_id integer primary key,uid interger, name text,utext text");
        if (hasUid(database, "talklist", uid)) {
            return false;
        }
        ContentValues values = new ContentValues();
        values.put("uid", string(uid));
        values.put("name", string(name));
        values.put("utext", string(text));
        try {
            database.insert("talklist", null, values);
        } catch (RuntimeException ignored) {
            // The SDK bridge reported true once the duplicate check passed, even if insert failed.
        }
        return true;
    }

    public boolean update_talklist(Object id, Object column, Object value) {
        SQLiteDatabase database = getTalkDatabase();
        ensureTable(database, "talklist", "_id integer primary key,uid interger, name text,utext text");
        String columnName = checkedColumn(database, "talklist", column);
        if (columnName == null) {
            return false;
        }
        ContentValues values = new ContentValues();
        values.put(columnName, string(value));
        try {
            database.update("talklist", values, "_id=?", new String[]{string(id)});
            return true;
        } catch (RuntimeException ignored) {
            return false;
        }
    }

    public boolean delete_talklist(Object id) {
        SQLiteDatabase database = getTalkDatabase();
        ensureTable(database, "talklist", "_id integer primary key,uid interger, name text,utext text");
        try {
            database.delete("talklist", "_id=?", new String[]{string(id)});
            return true;
        } catch (RuntimeException ignored) {
            return false;
        }
    }

    public int findid_talklist(Object uid) {
        SQLiteDatabase database = getTalkDatabase();
        ensureTable(database, "talklist", "_id integer primary key,uid interger, name text,utext text");
        return findId(database, "talklist", uid);
    }

    public void print_talklist() {
        SQLiteDatabase database = getTalkDatabase();
        ensureTable(database, "talklist", "_id integer primary key,uid interger, name text,utext text");
        try (Cursor cursor = database.query("talklist",
                new String[]{"_id", "uid", "name", "utext"}, null, null, null, null, null)) {
            while (cursor.moveToNext()) {
                String line = ":" + cursor.getString(0) + ", " + cursor.getString(1)
                        + ", " + cursor.getString(2) + ", " + cursor.getString(3);
                System.out.println(line);
                Log.v("iapp", line);
            }
        }
    }

    public void setup_filelist() {
        ensureTable(getFileDatabase(), "filelist", "_id integer primary key, path text,value interger");
    }

    public boolean update_filelist(Object path, int value) {
        SQLiteDatabase database = getFileDatabase();
        ensureTable(database, "filelist", "_id integer primary key, path text,value interger");
        String rowId = null;
        try (Cursor cursor = database.query("filelist", new String[]{"_id", "path"},
                "path=?", new String[]{string(path)}, null, null, null)) {
            if (cursor.moveToFirst()) {
                rowId = cursor.getString(0);
            }
        }
        if (rowId == null) {
            ContentValues values = new ContentValues();
            values.put("path", string(path));
            values.put("value", value);
            return database.insert("filelist", null, values) != -1L;
        }
        ContentValues values = new ContentValues();
        values.put("value", value);
        try {
            database.update("filelist", values, "_id=?", new String[]{rowId});
            return true;
        } catch (RuntimeException ignored) {
            return false;
        }
    }

    public int get_filelist(Object path) {
        SQLiteDatabase database = getFileDatabase();
        ensureTable(database, "filelist", "_id integer primary key, path text,value interger");
        try (Cursor cursor = database.query("filelist", new String[]{"value"},
                "path=?", new String[]{string(path)}, null, null, null, "1")) {
            return cursor.moveToFirst() ? Integer.parseInt(cursor.getString(0)) : 0;
        }
    }

    public void setup_pluginlist() {
        ensureTable(getPluginDatabase(), "pluginlist",
                "_id integer primary key,uid interger, name text,utext text");
    }

    public boolean add_pluginlist(Object uid, Object name, Object text) {
        SQLiteDatabase database = getPluginDatabase();
        ensureTable(database, "pluginlist", "_id integer primary key,uid interger, name text,utext text");
        if (hasUid(database, "pluginlist", uid)) {
            return false;
        }
        ContentValues values = new ContentValues();
        values.put("uid", string(uid));
        values.put("name", string(name));
        values.put("utext", string(text));
        try {
            database.insert("pluginlist", null, values);
        } catch (RuntimeException ignored) {
            // The SDK bridge reported true once the duplicate check passed, even if insert failed.
        }
        return true;
    }

    public boolean update_pluginlist(Object id, Object column, Object value) {
        SQLiteDatabase database = getPluginDatabase();
        ensureTable(database, "pluginlist", "_id integer primary key,uid interger, name text,utext text");
        String columnName = checkedColumn(database, "pluginlist", column);
        if (columnName == null) {
            return false;
        }
        ContentValues values = new ContentValues();
        values.put(columnName, string(value));
        try {
            database.update("pluginlist", values, "_id=?", new String[]{string(id)});
            return true;
        } catch (RuntimeException ignored) {
            return false;
        }
    }

    public boolean delete_pluginlist(Object id) {
        SQLiteDatabase database = getPluginDatabase();
        ensureTable(database, "pluginlist", "_id integer primary key,uid interger, name text,utext text");
        try {
            database.delete("pluginlist", "_id=?", new String[]{string(id)});
            return true;
        } catch (RuntimeException ignored) {
            return false;
        }
    }

    public int findid_pluginlist(Object uid) {
        SQLiteDatabase database = getPluginDatabase();
        ensureTable(database, "pluginlist", "_id integer primary key,uid interger, name text,utext text");
        return findId(database, "pluginlist", uid);
    }

    public void print_pluginlist() {
        SQLiteDatabase database = getPluginDatabase();
        ensureTable(database, "pluginlist", "_id integer primary key,uid interger, name text,utext text");
        try (Cursor cursor = database.query("pluginlist",
                new String[]{"_id", "uid", "name", "utext"}, null, null, null, null, null)) {
            while (cursor.moveToNext()) {
                String line = ":" + cursor.getString(0) + ", " + cursor.getString(1)
                        + ", " + cursor.getString(2) + ", " + cursor.getString(3);
                System.out.println(line);
                Log.v("iapp", line);
            }
        }
    }

    private synchronized SQLiteDatabase openIfNeeded(SQLiteDatabase database, String path) {
        if (database == null || !database.isOpen()) {
            return NativeDatabase.open(context, path);
        }
        return database;
    }

    private static void ensureTable(SQLiteDatabase database, String table, String definition) {
        try (Cursor cursor = database.rawQuery(
                "SELECT 1 FROM sqlite_master WHERE type='table' AND name=?", new String[]{table})) {
            if (!cursor.moveToFirst()) {
                database.execSQL("CREATE TABLE " + table + " (" + definition + ")");
            }
        }
    }

    private static int findId(SQLiteDatabase database, String table, Object uid) {
        try (Cursor cursor = database.query(table, new String[]{"_id"},
                "uid=?", new String[]{string(uid)}, null, null, null, "1")) {
            return cursor.moveToFirst() ? cursor.getInt(0) : 0;
        }
    }

    private static boolean hasUid(SQLiteDatabase database, String table, Object uid) {
        try (Cursor cursor = database.query(table, new String[]{"_id"},
                "uid=?", new String[]{string(uid)}, null, null, null, "1")) {
            return cursor.moveToFirst();
        }
    }

    private static String checkedColumn(SQLiteDatabase database, String table, Object requestedColumn) {
        String column = string(requestedColumn);
        try (Cursor cursor = database.rawQuery("PRAGMA table_info(" + table + ")", null)) {
            while (cursor.moveToNext()) {
                if (column.equals(cursor.getString(cursor.getColumnIndexOrThrow("name")))) {
                    return column;
                }
            }
        }
        return null;
    }

    private static String string(Object value) {
        return String.valueOf(value);
    }
}