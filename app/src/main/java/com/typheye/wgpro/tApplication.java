package com.typheye.wgpro;

import android.app.Application;
import android.app.Activity;
import android.os.Bundle;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

import com.typheye.wgpro.debug.CrashHandler;
import com.typheye.wgpro.debug.TestHandler;

import java.io.File;

public class tApplication extends Application {
    private static tApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // Android 12+ 跟随系统壁纸取色（莫奈）；主题属性仍保留静态色作为低版本回退。
        // 这里**不能**在注册时就读开关：DynamicColors 注册的是一次性的 ActivityLifecycleCallbacks，
        // 一旦按当时的开关决定，之后改设置就必须重启进程才生效。
        // 改为注册「前置条件」，每次创建 Activity 时重新判断，设置页改完 recreate 即刻生效。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            com.google.android.material.color.DynamicColors.applyToActivitiesIfAvailable(this,
                    new com.google.android.material.color.DynamicColorsOptions.Builder()
                            .setPrecondition(new com.google.android.material.color.DynamicColors.Precondition() {
                                @Override
                                public boolean shouldApplyDynamicColors(
                                        android.app.Activity activity, int theme) {
                                    return isDynamicColorEnabled(activity);
                                }
                            })
                            .build());
        }
        boolean followSystem = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("theme_follow_system", true);
        boolean darkMode = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("theme_dark_mode", false);
        AppCompatDelegate.setDefaultNightMode(followSystem
                ? AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                : darkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        registerActivityLifecycleCallbacks(new android.app.Application.ActivityLifecycleCallbacks() {
            @Override public void onActivityCreated(android.app.Activity a, android.os.Bundle b) {
                liveActivities.add(a);
            }
            @Override public void onActivityStarted(android.app.Activity a) { }
            @Override public void onActivityResumed(android.app.Activity a) { }
            @Override public void onActivityPaused(android.app.Activity a) { }
            @Override public void onActivityStopped(android.app.Activity a) { }
            @Override public void onActivitySaveInstanceState(android.app.Activity a, android.os.Bundle b) { }
            @Override public void onActivityDestroyed(android.app.Activity a) {
                liveActivities.remove(a);
            }
        });
        initCrashHandler();
        TestHandler.install(this);
        // 全应用 edge-to-edge / 系统栏统一适配（含底部导航小横条）
        com.typheye.wgpro.utils.SystemBars.install(this);
        // 下载任务本地持久化：启动时恢复（重启后「下载中」标为已暂停）
        com.typheye.wgpro.core.download.DownloadManager.get().init(this);
        trackForegroundState();

        // 确保日志目录存在
        ensureLogDirectory();
    }

    /** 跟踪整个应用的前台/后台状态（用于判断后台连接是否被系统掐断）。 */
    private void trackForegroundState() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            private int started;

            @Override public void onActivityStarted(@NonNull Activity activity) {
                if (++started == 1) {
                    com.typheye.wgpro.core.AppLifecycle.setForeground(true);
                }
            }

            @Override public void onActivityStopped(@NonNull Activity activity) {
                if (started > 0 && --started == 0) {
                    com.typheye.wgpro.core.AppLifecycle.setForeground(false);
                }
            }

            @Override public void onActivityCreated(@NonNull Activity activity, Bundle state) { }

            @Override public void onActivityResumed(@NonNull Activity activity) { }

            @Override public void onActivityPaused(@NonNull Activity activity) { }

            @Override public void onActivitySaveInstanceState(@NonNull Activity activity,
                                                              @NonNull Bundle state) { }

            @Override public void onActivityDestroyed(@NonNull Activity activity) { }
        });
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
    /** 「动态取色」（莫奈）开关的 SharedPreferences key，默认开启。 */
    public static final String PREF_DYNAMIC_COLOR = "theme_dynamic_color";

    /** 还活着的 Activity（用于「动态取色」这类只在创建时生效的设置做全局即时重建）。 */
    private final java.util.List<android.app.Activity> liveActivities =
            java.util.Collections.synchronizedList(new java.util.ArrayList<android.app.Activity>());

    /**
     * 让所有还活着的界面按新设置重建一遍。
     * <p>Java 里没有 Kotlin 那种"全局即时生效"的魔法：Material 的动态取色/主题属性能且只能在
     * Activity 创建时应用，所以全局实时生效 = 把现有 Activity 全部 recreate 一次。</p>
     */
    public void recreateAllActivities() {
        java.util.List<android.app.Activity> snapshot;
        synchronized (liveActivities) {
            snapshot = new java.util.ArrayList<>(liveActivities);
        }
        for (android.app.Activity activity : snapshot) {
            if (activity == null || activity.isFinishing()) continue;
            try {
                activity.recreate();
            } catch (Exception ignored) { }
        }
    }

    /** 系统是否支持动态取色（Android 12+）。 */
    public static boolean supportsDynamicColor() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.S;
    }

    /** 当前是否启用动态取色。 */
    public static boolean isDynamicColorEnabled(android.content.Context context) {
        return supportsDynamicColor() && PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext())
                .getBoolean(PREF_DYNAMIC_COLOR, true);
    }

    public static tApplication getInstance() {
        return instance;
    }

    public File getExternalLogDir() {
        File externalFiles = getExternalFilesDir(null);
        if (externalFiles == null) return new File(getFilesDir(), "log");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return new File(externalFiles, "log");
        }
        return new File(
                Environment.getExternalStorageDirectory(),
                "Android/data/com.typheye.wgpro/files/log"
        );
    }

}
