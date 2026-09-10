package com.typheye.wgpro.core;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

/**
 * 后台保活体检。
 *
 * 国产 ROM（特别是 MIUI/HyperOS）会在应用退到后台几秒后限制其后台联网/冻结进程，
 * 导致实时连接被掐断——这是应用无法自行绕过的系统策略，只能检测并引导用户去设置。
 */
public final class BackgroundHealth {
    private static final String PREFS = "background_health";
    private static final String KEY_DROPS = "drops_while_background";
    private static final String KEY_WARNED_AT = "warned_at";
    private static final long WINDOW_MS = 24 * 60 * 60 * 1000L;
    private static final long WARN_COOLDOWN_MS = 3 * 24 * 60 * 60 * 1000L;
    private static final int WARN_THRESHOLD = 3;

    private BackgroundHealth() {
    }

    /** 记录一次"在后台被掐断"。 */
    public static void recordBackgroundDrop(Context context) {
        SharedPreferences prefs = context.getApplicationContext()
                .getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        long now = System.currentTimeMillis();
        long windowStart = prefs.getLong("window_start", 0L);
        int drops = prefs.getInt(KEY_DROPS, 0);
        if (now - windowStart > WINDOW_MS) {
            windowStart = now;
            drops = 0;
        }
        prefs.edit()
                .putLong("window_start", windowStart)
                .putInt(KEY_DROPS, drops + 1)
                .apply();
    }

    public static boolean shouldWarn(Context context) {
        SharedPreferences prefs = context.getApplicationContext()
                .getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        if (prefs.getInt(KEY_DROPS, 0) < WARN_THRESHOLD) return false;
        return System.currentTimeMillis() - prefs.getLong(KEY_WARNED_AT, 0L) > WARN_COOLDOWN_MS;
    }

    public static void markWarned(Context context) {
        context.getApplicationContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit().putLong(KEY_WARNED_AT, System.currentTimeMillis())
                .putInt(KEY_DROPS, 0).apply();
    }

    public static void clear(Context context) {
        context.getApplicationContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit().clear().apply();
    }

    /** 打开系统的省电策略/电池优化页面（国产 ROM 会跳转到自家的省电管理）。 */
    public static Intent powerSettingsIntent(Context context) {
        Intent intent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
        } else {
            intent = new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }
}
