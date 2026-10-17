package open.cn.awg.pro.core;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 新版应用偏好存储：使用 Android 原生 {@link SharedPreferences}（落 XML 到
 * {@code /data/data/<pkg>/shared_prefs/wgpro_prefs.xml}），替代旧版 iapp 那套
 * 写 {@code settings/*.inf} 的存储方式。
 *
 * <p>目前承载两类数据：
 * <ul>
 *     <li>屏幕形状：首次引导让用户手选，作为<b>第一优先</b>；未选时由
 *     {@link ScreenShape#isRound(Context)} 自动探测兜底。</li>
 *     <li>应用初始化标志：设备配置完成、引导完成等。</li>
 * </ul>
 */
public final class AppPrefs {

    private static final String FILE = "wgpro_prefs";

    /** 屏幕形状：自动（未手选）。 */
    public static final int SHAPE_AUTO = 0;
    /** 圆屏手表。 */
    public static final int SHAPE_ROUND = 1;
    /** 方屏手表。 */
    public static final int SHAPE_SQUARE = 2;
    /** 鸿蒙手表（按圆屏处理）。 */
    public static final int SHAPE_HARMONY = 3;
    /** 其他设备（按自动处理）。 */
    public static final int SHAPE_OTHER = 4;

    private static final String KEY_SCREEN_SHAPE = "screen_shape";
    private static final String KEY_DEVICE_SETUP_DONE = "device_setup_done";
    private static final String KEY_ONBOARDING_DONE = "onboarding_done";

    private AppPrefs() {
    }

    private static SharedPreferences prefs(Context context) {
        return context.getApplicationContext()
                .getSharedPreferences(FILE, Context.MODE_PRIVATE);
    }

    // ---- 屏幕形状（第一优先） ----

    /** 用户手选的屏幕形状，未选返回 {@link #SHAPE_AUTO}。 */
    public static int getScreenShape(Context context) {
        return prefs(context).getInt(KEY_SCREEN_SHAPE, SHAPE_AUTO);
    }

    public static void setScreenShape(Context context, int shape) {
        prefs(context).edit().putInt(KEY_SCREEN_SHAPE, shape).apply();
    }

    // ---- 应用初始化标志 ----

    /** 设备配置（选形状那一步）是否完成。 */
    public static boolean isDeviceSetupDone(Context context) {
        return prefs(context).getBoolean(KEY_DEVICE_SETUP_DONE, false);
    }

    public static void setDeviceSetupDone(Context context, boolean done) {
        prefs(context).edit().putBoolean(KEY_DEVICE_SETUP_DONE, done).apply();
    }

    /** 首次引导是否走完。 */
    public static boolean isOnboardingDone(Context context) {
        return prefs(context).getBoolean(KEY_ONBOARDING_DONE, false);
    }

    public static void setOnboardingDone(Context context, boolean done) {
        prefs(context).edit().putBoolean(KEY_ONBOARDING_DONE, done).apply();
    }
}
