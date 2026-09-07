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
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.core.view.WindowCompat;
import androidx.preference.PreferenceManager;

import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.google.zxing.client.android.BuildConfig;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.WebActivity;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // 允许内容延伸到挖孔区域（关键）
            window.getAttributes().layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        WindowCompat.setDecorFitsSystemWindows(window, false);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        int chromeColor = ContextCompat.getColor(context, R.color.surface_primary);
        window.setStatusBarColor(chromeColor);
        window.setNavigationBarColor(android.graphics.Color.TRANSPARENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.setStatusBarContrastEnforced(false);
            window.setNavigationBarContrastEnforced(false);
        }

        boolean isLightMode = isLightMode(context);
        WindowInsetsControllerCompat controller = new WindowInsetsControllerCompat(
                window, window.getDecorView());
        controller.setAppearanceLightStatusBars(isLightMode);
        controller.setAppearanceLightNavigationBars(isLightMode);

    }

    public static void configureActivityTransitions(@NonNull Activity activity) {
        activity.getWindow().setWindowAnimations(R.style.ActivityAnimation);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            activity.overrideActivityTransition(Activity.OVERRIDE_TRANSITION_OPEN,
                    R.anim.activity_open_enter, R.anim.activity_open_exit);
            activity.overrideActivityTransition(Activity.OVERRIDE_TRANSITION_CLOSE,
                    R.anim.activity_close_enter, R.anim.activity_close_exit);
        }
    }

    public static void applyMainWindowInsets(View appBar, View bottomNavigation) {
        int appBarLeft = appBar.getPaddingLeft();
        int appBarTop = appBar.getPaddingTop();
        int appBarRight = appBar.getPaddingRight();
        int appBarBottom = appBar.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(appBar, (view, insets) -> {
            Insets statusBars = insets.getInsets(WindowInsetsCompat.Type.statusBars()
                    | WindowInsetsCompat.Type.displayCutout());
            int freeform = isInMultiWindow(view) ? dp(view, 18) : 0;
            int freeformSide = isInMultiWindow(view) ? dp(view, 8) : 0;
            view.setPadding(appBarLeft + statusBars.left + freeformSide,
                    appBarTop + statusBars.top + freeform,
                    appBarRight + statusBars.right + freeformSide, appBarBottom);
            return insets;
        });

        int navLeft = bottomNavigation.getPaddingLeft();
        int navTop = bottomNavigation.getPaddingTop();
        int navRight = bottomNavigation.getPaddingRight();
        int navBottom = bottomNavigation.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(bottomNavigation, (view, insets) -> {
            Insets navigationBars = insets.getInsets(WindowInsetsCompat.Type.navigationBars());
            int freeformBottom = isInMultiWindow(view) ? dp(view, 22) : 0;
            int freeformSide = isInMultiWindow(view) ? dp(view, 8) : 0;
            view.setPadding(navLeft + navigationBars.left + freeformSide, navTop,
                    navRight + navigationBars.right + freeformSide,
                    navBottom + navigationBars.bottom + freeformBottom);
            return insets;
        });
        ViewCompat.requestApplyInsets(appBar);
        ViewCompat.requestApplyInsets(bottomNavigation);
    }

    private static boolean isInMultiWindow(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) return ((Activity) context).isInMultiWindowMode();
            Context base = ((ContextWrapper) context).getBaseContext();
            if (base == context) break;
            context = base;
        }
        return false;
    }

    private static int dp(View view, int value) {
        return Math.round(value * view.getResources().getDisplayMetrics().density);
    }

    public static void fixScreenCutArea(View view) {
        try {
            int initialLeft = view.getPaddingLeft();
            int initialTop = view.getPaddingTop();
            int initialRight = view.getPaddingRight();
            int initialBottom = view.getPaddingBottom();
            ViewCompat.setOnApplyWindowInsetsListener(view, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(
                        initialLeft + systemBars.left,
                        initialTop + systemBars.top,
                        initialRight + systemBars.right,
                        initialBottom);
                return WindowInsetsCompat.CONSUMED;
            });
            ViewCompat.requestApplyInsets(view);
        } catch (Exception ignored) {
            // 异常捕获保留（虽然通常不需要）
        }
    }

    /**
     * 判断当前是否为浅色模式（即非深色模式）
     * @param context Context
     * @return true 表示浅色模式，false 表示深色模式
     */
    private static boolean isLightMode(Context context) {
        int nightModeFlags = context.getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags != Configuration.UI_MODE_NIGHT_YES;
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
     * 检查是否启用自动检查更新
     * @param context 上下文
     * @return true 表示启用自动检查更新
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
     * 检查应用更新（真实实现）
     */
    public static void loadServerConfig(Context context) {
        String updateUrl = "https://service.typheye.cn/app/com.typheye.wgpro/config-app.json";

        new Thread(() -> {
            try {
                String jsonData = getJsonFromUrl(updateUrl);
                if (jsonData.isEmpty()) {
                    throw new IOException("Empty response from server");
                }

                JSONObject json = new JSONObject(jsonData);
                long currentVersionCode = getVersionCode(context);


                saveAppNotice(context, json.getString("AppNotice"));

                String versionName = json.getString("UpdateVersionName");
                String versionCode = json.getString("UpdateVersionCode");
                long latestVersionCode = Integer.parseInt(versionCode);

                String updateText = json.getString("UpdateText");
                String downloadUrl = json.getString("UpdateUrl");

                String updateContext = "Ver. " + versionName + " - (" + versionCode + ") 现已发布！\n\n更新日志：\n" + updateText;


                if (isAutoCheckUpdate(context)) {
                    if (latestVersionCode > currentVersionCode) {
                        showUpdateDialog(context, updateContext, downloadUrl);
                    }
                }
                Log.e("UpdateChecker", updateContext);
            } catch (Exception e) {
                // 实际项目中应添加日志（如 Timber 或 Log）
                Log.e("UpdateChecker", "Update check failed", e);
            }
        }).start();
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
