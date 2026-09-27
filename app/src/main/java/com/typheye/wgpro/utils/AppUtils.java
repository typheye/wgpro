package com.typheye.wgpro.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.preference.PreferenceManager;

import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.google.zxing.client.android.BuildConfig;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.debug.TestHandler;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AppUtils {
    private static final int CURRENT_OOBE_VERSION = 2;
    // 在类中添加 OkHttp 客户端（建议在初始化时创建单例）
    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(TestHandler.networkLogger())
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build();

    /**
     * 使内容可绘制到屏幕挖孔区域（刘海/打孔屏），并确保背景颜色一致
     * @param window 当前 Activity 的 Window
     */
    public static void useScreenCutArea(@NonNull Window window, Context context) {
        if (context instanceof Activity) {
            configureActivityTransitions((Activity) context);
        }
        SystemBars.configureWindow(window, context);
    }

    public static void configureActivityTransitions(@NonNull Activity activity) {
        // Android 14 起系统用 SurfaceControl 合成页面转场，并支持预测式返回。
        // 自定义窗口动画会强制走旧的动画合成路径，反而更卡；这里在 14+ 交给系统处理。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            return;
        }
        // 旧版本保留轻量淡入淡出+微位移，时长压短，避免"慢半拍"的感觉
        activity.getWindow().setWindowAnimations(R.style.ActivityAnimation);
    }

    public static void applyMainWindowInsets(View appBar, View bottomNavigation) {
        SystemBars.applyAppBarInsets(appBar, bottomNavigation);
    }

    public static void fixScreenCutArea(View view) {
        SystemBars.applyTopInsets(view);
    }

    /** 顶部状态栏 + 输入法避让：edge-to-edge 页面需要它等效 adjustResize。 */
    public static void fixScreenCutAreaWithIme(View view) {
        SystemBars.applyTopAndImeInsets(view);
    }

    /** 整页 edge-to-edge 适配：内容躲开系统栏，系统栏区域使用页面背景。 */
    public static void applyScreenInsets(View view) {
        SystemBars.applyScreenInsets(view);
    }

    /**
     * 让页面里的滚动内容自己预留底部系统导航栏高度（内容可以延伸到导航条区域，
     * 末尾仍能完整滚出），而不是给外层容器加内边距导致内容在导航栏上沿被切断。
     *
     * <p>同时覆盖两种情况：新创建的 Fragment 视图，以及 Activity 重建后
     * 由 FragmentManager 直接恢复、不会再回调 onFragmentViewCreated 的 Fragment 视图。
     */
    public static void applyScrollBottomInsets(
            @NonNull androidx.fragment.app.FragmentManager fragmentManager,
            @androidx.annotation.IdRes int containerId) {
        fragmentManager.registerFragmentLifecycleCallbacks(
                new androidx.fragment.app.FragmentManager.FragmentLifecycleCallbacks() {
                    @Override
                    public void onFragmentViewCreated(
                            @NonNull androidx.fragment.app.FragmentManager fm,
                            @NonNull androidx.fragment.app.Fragment fragment,
                            @NonNull View view, android.os.Bundle state) {
                        SystemBars.reserveBottomInsetForScroll(view);
                    }
                }, false);
        androidx.fragment.app.Fragment existing =
                fragmentManager.findFragmentById(containerId);
        if (existing != null && existing.getView() != null) {
            SystemBars.reserveBottomInsetForScroll(existing.getView());
        }
    }

    public static void appInit(Context context){
        SharedPreferences prefs  = context.getSharedPreferences("app", Context.MODE_PRIVATE);
        prefs.edit()
                .putBoolean("appInit", true)
                .putInt("oobeVersion", CURRENT_OOBE_VERSION)
                .apply();
    }
    public static void appUninit(Context context){
        SharedPreferences prefs  = context.getSharedPreferences("app", Context.MODE_PRIVATE);
        prefs.edit()
                .putBoolean("appInit", false)
                .remove("oobeVersion")
                .apply();
    }
    public static boolean getAppInit(Context context){
        SharedPreferences prefs = context.getSharedPreferences("app", Context.MODE_PRIVATE);
        return prefs.getBoolean("appInit", false)
                && prefs.getInt("oobeVersion", 0) >= CURRENT_OOBE_VERSION;
    }

    private static void saveAppNotice(Context context, String appNotice){
        // 获取 SharedPreferences 对象（使用自定义名称，如 "app_settings"，避免与其他应用冲突）
        SharedPreferences prefs = context.getSharedPreferences("app", Context.MODE_PRIVATE);
        prefs.edit().putString("appNotice", appNotice).apply();
    }

    public static String getAppNotice(Context context){
        // 获取 SharedPreferences 对象（必须使用相同的名称和 MODE_PRIVATE）
        SharedPreferences prefs = context.getSharedPreferences("app", Context.MODE_PRIVATE);
        // 读取公告字符串（key 为 "AppNotice"，默认值为空字符串）
        return prefs.getString("appNotice", "腕管Pro是继腕上管家开发的第二代产品，将继承原腕上管家的大部分，并在继续优化，改造，删除不必要的功能，目前仍在持续更新中。支持应用ADB安装，文本、图片浏览，还支持视频播放！");
    }

    /**
     * 检查是否启用自动检查更新提醒
     * @param context 上下文
     * @return true 表示启用
     */
    public static boolean isAutoCheckUpdate(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("appUpdate_autoCheck", true); // 默认开启
    }

    public static int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(),
                    0
            );
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            //e.printStackTrace();
            return -1; // 出错时返回 -1
        }
    }

    // ------------------------------------------------------------ 强制更新状态

    private static final String PREF_FORCE_CODE = "appUpdate_forceCode";
    private static final String PREF_FORCE_TEXT = "appUpdate_forceText";
    private static final String PREF_FORCE_URL = "appUpdate_forceUrl";

    /** 检查完更新后的回调（主线程）：参数表示"此刻是否仍处于强制更新状态"。 */
    public interface ForceUpdateCallback {
        void onResult(boolean forceUpdateRequired);
    }

    /**
     * 服务端下发了强制更新、并且本机版本确实更低时为 true。
     *
     * 判定用的是"服务端要求的最低版本号 > 本机版本号"，所以用户装上包之后
     * 即使没有联网、没有重新拉配置，拦截也会自动失效。
     */
    public static boolean isForceUpdateRequired(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("app", Context.MODE_PRIVATE);
        long required = prefs.getLong(PREF_FORCE_CODE, 0L);
        return required > 0L && required > getVersionCode(context);
    }

    public static String getForceUpdateMessage(Context context) {
        return context.getSharedPreferences("app", Context.MODE_PRIVATE).getString(PREF_FORCE_TEXT, "");
    }

    public static String getForceUpdateUrl(Context context) {
        return context.getSharedPreferences("app", Context.MODE_PRIVATE).getString(PREF_FORCE_URL, "");
    }

    private static void saveForceUpdate(Context context, long versionCode, String message, String url) {
        context.getSharedPreferences("app", Context.MODE_PRIVATE).edit()
                .putLong(PREF_FORCE_CODE, versionCode)
                .putString(PREF_FORCE_TEXT, message == null ? "" : message)
                .putString(PREF_FORCE_URL, url == null ? "" : url)
                .apply();
    }

    private static void clearForceUpdate(Context context) {
        context.getSharedPreferences("app", Context.MODE_PRIVATE).edit()
                .remove(PREF_FORCE_CODE)
                .remove(PREF_FORCE_TEXT)
                .remove(PREF_FORCE_URL)
                .apply();
    }

    /**
     * 拉起"必须更新"页面。应用在后台时系统会忽略这次启动，
     * 用户下次回到应用由 {@link #ensureForceUpdateGate(Context)} 补上。
     */
    public static void openUpdateRequiredPage(Context context) {
        try {
            context.startActivity(new Intent(context,
                    com.typheye.wgpro.ui.function.settings.UpdateRequiredActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_CLEAR_TOP
                            | Intent.FLAG_ACTIVITY_SINGLE_TOP));
        } catch (Exception ignored) {
            // 后台启动 Activity 被系统拒绝是正常情况，交给 MainActivity.onResume 兜底
        }
    }

    /** 页面每次回到前台都调一下：处于强制更新时把用户挡在更新页。 */
    public static void ensureForceUpdateGate(Context context) {
        if (!isForceUpdateRequired(context)) return;
        openUpdateRequiredPage(context);
    }

    /**
     * 检查应用更新。
     *
     * 规则：
     *   1) 不管"有更新时提醒我"开关是否打开，都会拉配置并比较版本；
     *   2) 服务端标了强制更新（UpdateForce）且本机版本更低 → 记录状态 + 拉起不可跳过的更新页；
     *   3) 没标强制、且开关打开 → 才弹普通的"有新版本"提示；开关关闭则完全静默。
     *
     * 网络失败时沿用上一次的强制更新状态，避免"拔网就能绕过更新"。
     */
    public static void loadServerConfig(Context context) {
        loadServerConfig(context, null);
    }

    public static void loadServerConfig(Context context, ForceUpdateCallback onResult) {
        String updateUrl = "https://service.typheye.cn/app/com.typheye.wgpro/config-app.json";

        new Thread(() -> {
            boolean forceRequired = false;
            try {
                String jsonData = getJsonFromUrl(updateUrl);
                if (jsonData.isEmpty()) {
                    throw new IOException("Empty response from server");
                }

                JSONObject json = new JSONObject(jsonData);
                long currentVersionCode = getVersionCode(context);

                String notice = json.optString("AppNotice", "");
                if (!notice.isEmpty()) {
                    saveAppNotice(context, notice);
                }

                String versionName = json.optString("UpdateVersionName", "");
                long latestVersionCode = parseVersionCode(json.opt("UpdateVersionCode"));
                String updateText = json.optString("UpdateText", "");
                String downloadUrl = json.optString("UpdateUrl", "");
                boolean force = parseBoolean(json.opt("UpdateForce"), false)
                        || parseBoolean(json.opt("UpdateForced"), false);

                boolean newer = latestVersionCode > currentVersionCode;
                String updateContext = buildUpdateMessage(versionName, latestVersionCode, updateText);
                Log.e("UpdateChecker", updateContext + " | force=" + force + " current=" + currentVersionCode);

                if (force && newer) {
                    saveForceUpdate(context, latestVersionCode, updateContext, downloadUrl);
                    forceRequired = true;
                    openUpdateRequiredPage(context);
                } else {
                    // 用户已经升级，或运营取消了强制更新：解除拦截
                    clearForceUpdate(context);
                    if (newer && isAutoCheckUpdate(context)) {
                        showUpdateDialog(context, updateContext, downloadUrl);
                    }
                }
            } catch (Exception e) {
                // 拿不到配置时保持原有拦截状态，断网不能当成"已是最新"
                forceRequired = isForceUpdateRequired(context);
                Log.e("UpdateChecker", "Update check failed", e);
            }
            if (onResult != null) {
                final boolean state = forceRequired;
                new Handler(Looper.getMainLooper()).post(() -> onResult.onResult(state));
            }
        }).start();
    }

    private static String buildUpdateMessage(String versionName, long versionCode, String updateText) {
        StringBuilder builder = new StringBuilder();
        builder.append("Ver. ").append(versionName).append(" (").append(versionCode).append(") 现已发布！");
        if (updateText != null && !updateText.trim().isEmpty()) {
            builder.append("\n\n更新日志：\n").append(updateText.trim());
        }
        return builder.toString();
    }

    /** 版本号可能是数字也可能是字符串，两种都要能认。 */
    private static long parseVersionCode(Object raw) {
        if (raw instanceof Number) return ((Number) raw).longValue();
        if (raw == null) return 0L;
        try {
            return Long.parseLong(String.valueOf(raw).trim());
        } catch (NumberFormatException e) {
            return 0L;
        }
    }

    /** 兼容 true / "true" / 1 / "1" / "yes" 这几种写法。 */
    private static boolean parseBoolean(Object raw, boolean fallback) {
        if (raw instanceof Boolean) return (Boolean) raw;
        if (raw instanceof Number) return ((Number) raw).intValue() != 0;
        if (raw == null) return fallback;
        String value = String.valueOf(raw).trim().toLowerCase();
        if (value.isEmpty()) return fallback;
        return value.equals("true") || value.equals("1") || value.equals("yes");
    }

    /**
     * 真实网络请求实现（使用 OkHttp）
     */
    private static String getJsonFromUrl(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "WGPro/Android " + BuildConfig.VERSION_NAME)
                .build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }


    // 其他方法保持不变（openDownPage, showUpdateDialog）
    private static void openDownPage(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("URL", url);
        context.startActivity(intent);
    }

    /**
     * 长按整段复制到剪贴板。
     *
     * <p>用它取代 {@code android:textIsSelectable}：后者长按会进入文本选择模式，
     * 触发焦点变化与滚动定位，导致页面元素抖动/重排。</p>
     */
    public static void longPressToCopy(@NonNull android.widget.TextView view) {
        if (view == null) return;
        view.setOnLongClickListener(v -> {
            CharSequence text = view.getText();
            String value = text == null ? "" : text.toString().trim();
            if (value.isEmpty()) return false;
            android.content.ClipboardManager clipboard =
                    (android.content.ClipboardManager) view.getContext()
                            .getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboard != null) {
                clipboard.setPrimaryClip(android.content.ClipData.newPlainText("text", value));
                android.widget.Toast.makeText(view.getContext(), "已复制",
                        android.widget.Toast.LENGTH_SHORT).show();
            }
            return true;
        });
    }

    private static void showUpdateDialog(Context context, String updateContext, String downloadUrl) {
        new Thread(() -> new Handler(Looper.getMainLooper()).post(() -> new WGProAlertDialogBuilder(context)
                .setTitle("有新版本")
                .setMessage(updateContext)
                .setPositiveButton("立即更新", (dialog, which) -> openDownPage(context, downloadUrl))
                .setNegativeButton("稍后提醒", null)
                .setCancelable(false)
                .show())).start();
    }
}
