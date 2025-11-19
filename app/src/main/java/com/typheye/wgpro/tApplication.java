package com.typheye.wgpro;

import android.app.Application;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.typheye.wgpro.debug.CrashHandler;

import java.io.File;

public class tApplication extends Application {
    private static tApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initCrashHandler();

        // 确保日志目录存在
        ensureLogDirectory();
    }

    private void initCrashHandler() {
        CrashHandler crashHandler = new CrashHandler(this);
        Thread.setDefaultUncaughtExceptionHandler(crashHandler);
    }

    private void ensureLogDirectory() {
        try {
            File logDir = getExternalLogDir();
            if (!logDir.exists()) {
                // ✅ 修复：检查 mkdirs() 返回值
                boolean created = logDir.mkdirs();
                if (!created) {
                    Log.e("tApplication", "Failed to create log directory: " + logDir.getAbsolutePath());
                }
            }
        } catch (Exception e) {
            Log.e("tApplication", "Failed to create log directory", e);
        }
    }
    public static tApplication getInstance() {
        return instance;
    }

    public File getExternalLogDir() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return new File(getExternalFilesDir(null), "log");
        }
        return new File(
                Environment.getExternalStorageDirectory(),
                "Android/data/com.typheye.wgpro/files/log"
        );
    }

}