package com.typheye.wgpro.core;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 后台保活体检。
 *
 * 国产 ROM（特别是 MIUI/HyperOS）会在应用退到后台几秒后限制其后台联网/冻结进程，
 * 导致实时连接被掐断——这是应用无法自行绕过的系统策略，只能检测并引导用户去设置。
 */
public final class BackgroundHealth {
    private static final String PREFS = "background_health";
    private static final String KEY_DROPS = "drops_while_background";
    private static final long WINDOW_MS = 24 * 60 * 60 * 1000L;

    private BackgroundHealth() {
    }

    /** 记录一次"在后台被掐断"，仅用于诊断；当前版本不向用户弹窗或发通知。 */
    public static synchronized void recordBackgroundDrop(Context context) {
        SharedPreferences prefs = context.getApplicationContext()
                .getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        long now = System.currentTimeMillis();
        long windowStart = prefs.getLong("window_start", 0L);
        int drops = prefs.getInt(KEY_DROPS, 0);
        if (now - windowStart > WINDOW_MS) {
            windowStart = now;
            drops = 0;
        }
        int nextDrops = drops + 1;
        prefs.edit()
                .putLong("window_start", windowStart)
                .putInt(KEY_DROPS, nextDrops)
                .apply();
    }

    public static void clear(Context context) {
        context.getApplicationContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit().clear().apply();
    }
}
