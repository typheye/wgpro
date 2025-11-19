package com.typheye.wgpro.debug;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.zxing.client.android.BuildConfig;
import com.typheye.wgpro.tApplication;
import com.typheye.wgpro.ui.function.debug.CrashActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private final Context context;
    private final tApplication myApp;

    public CrashHandler(Context context) {
        this.context = context;
        this.myApp = (tApplication) context.getApplicationContext();
        Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
        try {
            saveCrashLog(throwable);
            Intent intent = new Intent(context, CrashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            context.startActivity(intent);

            // 关键修复：不要调用defaultHandler，否则应用会退出
            // defaultHandler.uncaughtException(thread, throwable);
        } catch (Exception e) {
            Log.e("CrashHandler", "Failed to handle crash", e);
            // 如果启动CrashActivity失败，尝试用Log记录
            Log.e("CrashHandler", "Failed to start CrashActivity", e);
        }

        // 关键：不要调用defaultHandler，否则应用会退出
        // 保留以下代码会导致应用直接退出
        // defaultHandler.uncaughtException(thread, throwable);
    }

    void saveCrashLog(Throwable throwable) {
        try {
            File logDir = myApp.getExternalLogDir();
            if (!logDir.exists() && !logDir.mkdirs()) {
                Log.e("CrashHandler", "Failed to create log directory");
                return;
            }

            @SuppressLint("SimpleDateFormat") String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = "crash_" + timestamp + ".log";
            File logFile = new File(logDir, fileName);

            try (FileOutputStream fos = new FileOutputStream(logFile)) {
                fos.write(getCrashReport(throwable).getBytes());
            }
        } catch (IOException e) {
            Log.e("CrashHandler", "Failed to save crash log", e);
        }
    }

    @SuppressLint("SimpleDateFormat")
    private String getCrashReport(Throwable throwable) {
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        printWriter.close();

        return "===== CRASH REPORT =====\n" +
                "Application: com.typheye.wgpro\n" +
                "Version: " + BuildConfig.VERSION_NAME + "\n" +
                "Device: " + Build.MANUFACTURER + " " + Build.MODEL + "\n" +
                "Android: " + Build.VERSION.RELEASE + " (API " + Build.VERSION.SDK_INT + ")\n" +
                "Time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n" +
                "\n=== STACK TRACE ===\n" +
                writer +
                "\n==================\n";
    }
}