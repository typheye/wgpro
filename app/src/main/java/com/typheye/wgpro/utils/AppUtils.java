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

    /** 整页 edge-to-edge 适配：内容躲开系统栏，系统栏区域使用页面背景。 */
    public static void applyScreenInsets(View view) {
        SystemBars.applyScreenInsets(view);
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
