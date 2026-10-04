package open.cn.awg.pro.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;

public final class NativeDatabase {
    private NativeDatabase() {
    }

    public static SQLiteDatabase open(Context context, String path) {
        Context databaseContext = applicationContext(context);
        if (path == null) {
            throw new IllegalArgumentException("database path must not be null");
        }
        if (isBareDatabaseName(path)) {
            return databaseContext.openOrCreateDatabase(path, Context.MODE_PRIVATE, null);
        }
        File databaseFile = NativeFiles.file(databaseContext, path);
        File parent = databaseFile.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs() && !parent.isDirectory()) {
            throw new IllegalStateException("Unable to create database directory: " + parent);
        }
        return SQLiteDatabase.openDatabase(databaseFile.getAbsolutePath(), null,
                SQLiteDatabase.OPEN_READWRITE | SQLiteDatabase.CREATE_IF_NECESSARY);
    }

    public static SQLiteDatabase openReadOnly(Context context, String path) {
        Context databaseContext = applicationContext(context);
        if (path == null) {
            throw new IllegalArgumentException("database path must not be null");
        }
        File databaseFile = isBareDatabaseName(path)
                ? databaseContext.getDatabasePath(path)
                : NativeFiles.file(databaseContext, path);
        return SQLiteDatabase.openDatabase(databaseFile.getAbsolutePath(), null, SQLiteDatabase.OPEN_READONLY);
    }

    private static boolean isBareDatabaseName(String path) {
        return !path.contains("/") && !path.contains("\\")
                && !path.startsWith("@") && !path.startsWith("$") && !path.startsWith("%");
    }

    private static Context applicationContext(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        Context applicationContext = context.getApplicationContext();
        return applicationContext == null ? context : applicationContext;
    }
}