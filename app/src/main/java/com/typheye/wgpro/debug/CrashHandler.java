package com.typheye.wgpro.debug;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;

import androidx.annotation.NonNull;

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
    private final Thread.UncaughtExceptionHandler defaultHandler;
    private volatile boolean handlingCrash;

    public CrashHandler(Context context) {
        this.context = context;
        this.myApp = (tApplication) context.getApplicationContext();
        this.defaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
        if (handlingCrash) {
            delegateOrExit(thread, throwable);
            return;
        }
        handlingCrash = true;
        try {
            saveCrashLog(throwable);
            Intent intent = new Intent(context, CrashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK
                    | Intent.FLAG_ACTIVITY_NO_ANIMATION);
            PendingIntent restart = PendingIntent.getActivity(context, 9041, intent,
                    PendingIntent.FLAG_CANCEL_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarms = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            if (alarms == null) throw new IllegalStateException("AlarmManager unavailable");
            alarms.set(AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime() + 350L, restart);
        } catch (Exception e) {
            Log.e("CrashHandler", "Failed to handle crash", e);
            delegateOrExit(thread, throwable);
            return;
        }
        Process.killProcess(Process.myPid());
        System.exit(10);
    }

    private void delegateOrExit(Thread thread, Throwable throwable) {
        if (defaultHandler != null && defaultHandler != this) {
            defaultHandler.uncaughtException(thread, throwable);
        } else {
            Process.killProcess(Process.myPid());
            System.exit(10);
        }
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
                "Version: " + getVersionName() + "\n" +
                "Device: " + Build.MANUFACTURER + " " + Build.MODEL + "\n" +
                "Android: " + Build.VERSION.RELEASE + " (API " + Build.VERSION.SDK_INT + ")\n" +
                "Time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\n" +
                "\n=== STACK TRACE ===\n" +
                writer +
                "\n==================\n";
    }

    private String getVersionName() {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0)
                    .versionName;
        } catch (Exception ignored) {
            return "unknown";
        }
    }
}
