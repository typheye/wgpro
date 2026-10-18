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
import androidx.annotation.Nullable;
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
    private static final int CURRENT_OOBE_VERSION = 4;

    /** 服务端应用配置（公告 / 新版本信息）地址。 */
    private static final String UPDATE_CONFIG_URL =
            "https://service.typheye.cn/app/com.typheye.wgpro/config-app.json";
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
     * 页面内 Fragment 切换的统一动效：淡入 + 轻微缩放，返回时反向。
     *
     * <p>设置页的「关于应用」与调试页（TestActivity）里的列表页共用同一套，
     * 避免两处动画不一致。Android 14+ 的窗口转场不影响 FragmentTransaction 动画。
     */
    public static androidx.fragment.app.FragmentTransaction applyFragmentTransition(
            @NonNull androidx.fragment.app.FragmentTransaction transaction) {
        transaction.setReorderingAllowed(true)
                .setCustomAnimations(R.animator.fragment_enter, R.animator.fragment_exit,
                        R.animator.fragment_pop_enter, R.animator.fragment_pop_exit);
        return transaction;
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

    /**
     * Release 包启动时清理桌面上遗留的调试快捷方式（老版本/调试包写过，会一直留在长按菜单里）。
     * 只删指向 Debug/Test 页或 id 里带 debug 的项，正常功能快捷方式不动。
     */
    public static void purgeDebugShortcuts(Context context) {
        if (context == null) return;
        boolean debuggable = (context.getApplicationInfo().flags
                & android.content.pm.ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        if (debuggable) return;
        try {
            android.content.pm.ShortcutManager manager =
                    (android.content.pm.ShortcutManager) context.getSystemService(Context.SHORTCUT_SERVICE);
            if (manager == null) return;
            java.util.List<String> remove = new java.util.ArrayList<>();
            for (android.content.pm.ShortcutInfo info : manager.getDynamicShortcuts()) {
                String id = info.getId() == null ? "" : info.getId().toLowerCase(java.util.Locale.ROOT);
                String target = "";
                Intent intent = info.getIntent();
                if (intent != null && intent.getComponent() != null) {
                    target = intent.getComponent().getClassName();
                }
                String targetLower = target.toLowerCase(java.util.Locale.ROOT);
                if (id.contains("debug") || targetLower.contains("debug")) {
                    remove.add(info.getId());
                }
            }
            if (!remove.isEmpty()) manager.removeDynamicShortcuts(remove);
        } catch (Throwable ignored) {
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

    /**
     * 检查应用更新。
     *
     * 规则：
     *   1) 不管「有更新时提醒我」开关是否打开，都会拉配置并比较版本；
     *   2) UpdateForce 为真且版本更低 → 弹**不可取消**的强制更新弹窗（无「稍后提醒」，
     *      点外部/返回键都关不掉，只能点「立即更新」）；
     *   3) 非强制且有更新、且开关打开 → 弹普通「有新版本」提示；开关关闭则完全静默。
     *
     * 弹窗一律使用前台 Activity 的 Context（后台线程拿 application context 弹窗会
     * BadTokenException 闪退）；拿不到时缓存，等下一次 onActivityResumed 补弹。
     */
    public static void loadServerConfig(Context context) {
        new Thread(() -> {
            try {
                String jsonData = getJsonFromUrl(UPDATE_CONFIG_URL);
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
                if (newer && force) {
                    showUpdateDialog(context, updateContext, downloadUrl, true);
                } else if (newer && isAutoCheckUpdate(context)) {
                    showUpdateDialog(context, updateContext, downloadUrl, false);
                }
            } catch (Exception e) {
                Log.e("UpdateChecker", "Update check failed", e);
            }
        }).start();
    }

    /** 仅用于「关于应用」卡片的状态查询：不弹任何对话框。 */
    public interface UpdateStatusCallback {
        /**
         * @param hasUpdate  服务端版本是否高于本机
         * @param latestName 服务端版本名（可能为空）
         * @param latestCode 服务端版本号
         * @param url        更新地址（可能为空）
         * @param error      失败信息；成功时为 null
         */
        void onResult(boolean hasUpdate, String latestName, long latestCode,
                      String url, String error);
    }

    /**
     * 查询是否有新版本，供「关于应用」的更新状态卡片使用。
     *
     * <p>与 {@link #loadServerConfig} 的区别：这里不写公告、不记录强制更新状态、
     * 不弹任何对话框，只把比较结果回调给页面。
     */
    public static void checkUpdateStatus(Context context, UpdateStatusCallback callback) {
        new Thread(() -> {
            boolean hasUpdate = false;
            String latestName = "";
            long latestCode = 0L;
            String url = "";
            String error = null;
            try {
                String jsonData = getJsonFromUrl(UPDATE_CONFIG_URL);
                if (jsonData.isEmpty()) {
                    throw new IOException("Empty response from server");
                }
                JSONObject json = new JSONObject(jsonData);
                latestName = json.optString("UpdateVersionName", "");
                latestCode = parseVersionCode(json.opt("UpdateVersionCode"));
                url = json.optString("UpdateUrl", "");
                hasUpdate = latestCode > getVersionCode(context);
            } catch (Exception e) {
                error = e.getMessage() == null ? "无法连接更新服务" : e.getMessage();
            }
            final boolean resultHasUpdate = hasUpdate;
            final String resultName = latestName;
            final long resultCode = latestCode;
            final String resultUrl = url;
            final String resultError = error;
            new Handler(Looper.getMainLooper()).post(() ->
                    callback.onResult(resultHasUpdate, resultName, resultCode,
                            resultUrl, resultError));
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
                com.typheye.wgpro.ui.widget.WGProHint.show(view.getContext(), "已复制");
            }
            return true;
        });
    }

    /** 待弹的更新提示（当时没有前台 Activity 时先缓存，回到前台再弹）。 */
    private static volatile String pendingUpdateMessage;
    private static volatile String pendingUpdateUrl;
    private static volatile boolean pendingUpdateForce;

    /**
     * 弹更新提示。
     *
     * @param force true = 强制更新形态（不可取消、无「稍后提醒」，只能点「立即更新」）
     */
    private static void showUpdateDialog(Context context, String updateContext,
                                         String downloadUrl, boolean force) {
        final android.app.Activity activity = resolveActivity(context);
        if (activity == null) {
            pendingUpdateMessage = updateContext;
            pendingUpdateUrl = downloadUrl;
            pendingUpdateForce = force;
            return;
        }
        showUpdateDialogOn(activity, updateContext, downloadUrl, force);
    }

    /** 回到前台时补弹之前缓存的更新提示（tApplication 的 onActivityResumed 调用）。 */
    public static void flushPendingUpdateDialog(@NonNull android.app.Activity activity) {
        final String message = pendingUpdateMessage;
        if (message == null) return;
        final String url = pendingUpdateUrl;
        final boolean force = pendingUpdateForce;
        pendingUpdateMessage = null;
        pendingUpdateUrl = null;
        pendingUpdateForce = false;
        showUpdateDialogOn(activity, message, url == null ? "" : url, force);
    }

    @Nullable
    private static android.app.Activity resolveActivity(Context context) {
        if (context instanceof android.app.Activity) return (android.app.Activity) context;
        return com.typheye.wgpro.tApplication.topActivity();
    }

    /** 用系统浏览器打开链接（强制更新用：跳出去后应用就该退场了）。 */
    private static void openExternalBrowser(@NonNull Context context, @Nullable String url) {
        if (url == null || url.trim().isEmpty()) return;
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse(url.trim()));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Throwable ignored) {
        }
    }

    private static void showUpdateDialogOn(@NonNull android.app.Activity activity,
                                           String updateContext, String downloadUrl,
                                           boolean force) {
        new Handler(Looper.getMainLooper()).post(() -> {
            if (activity.isFinishing()) return;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1
                    && activity.isDestroyed()) {
                return;
            }
            try {
                WGProAlertDialogBuilder builder = new WGProAlertDialogBuilder(activity)
                        .setTitle(force ? "需要更新" : "有新版本")
                        .setMessage(updateContext);
                if (force) {
                    // 强制更新：跳系统浏览器下载，然后立刻退出应用（不留旧版本可继续用）
                    builder.setPositiveButton("立即更新", (dialog, which) -> {
                        openExternalBrowser(activity, downloadUrl);
                        activity.finishAffinity();
                        new Handler(Looper.getMainLooper()).postDelayed(() ->
                                android.os.Process.killProcess(android.os.Process.myPid()), 250L);
                    });
                } else {
                    builder.setPositiveButton("立即更新",
                            (dialog, which) -> openDownPage(activity, downloadUrl));
                }
                if (!force) {
                    builder.setNegativeButton("稍后提醒", null);
                }
                if (!force) {
                    builder.setCancelable(true).show();
                    return;
                }
                // 强制更新：MIUI 上返回键可能绕开 BottomSheetDialog，这里再兜两层
                final boolean[] consumed = {false};
                builder.setPositiveButton("立即更新", (dialog, which) -> {
                    consumed[0] = true;
                    openExternalBrowser(activity, downloadUrl);
                    activity.finishAffinity();
                    new Handler(Looper.getMainLooper()).postDelayed(() ->
                            android.os.Process.killProcess(android.os.Process.myPid()), 250L);
                });
                final com.typheye.wgpro.ui.widget.WGProBottomSheetDialog sheet =
                        builder.setCancelable(false).create();
                sheet.setOnDismissListener(d -> {
                    if (consumed[0] || activity.isFinishing()) return;
                    // 被返回键/手势关掉：立刻重新弹，用户不更新就用不了
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {
                        if (!activity.isFinishing() && !activity.isDestroyed()) {
                            try { sheet.show(); } catch (Throwable ignored) { }
                        }
                    }, 120L);
                });
                sheet.show();
            } catch (Throwable ignored) {
                // 极端情况下窗口已失效：不弹也不能闪退
            }
        });
    }
}
