package com.typheye.wgpro.utils;

import android.content.Context;
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
import androidx.preference.PreferenceManager;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // 允许内容延伸到挖孔区域（关键）
            window.getAttributes().layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        // 状态栏透明（关键：让背景色延伸上去）
        int colorTab = ContextCompat.getColor(context, R.color.tab);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(colorTab); // 完全透明

        // 设置系统UI标志：
        int systemUiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

        // 判断当前是否为浅色模式（需要深色文字）
        boolean isLightMode = isLightMode(context);
        if (isLightMode) {
            systemUiFlags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }

        window.getDecorView().setSystemUiVisibility(systemUiFlags);

    }

    public static void fixScreenCutArea(View view) {
        try {
            ViewCompat.setOnApplyWindowInsetsListener(view, (v, insets) -> {
                // 获取系统栏（状态栏、导航栏）的 insets
                int systemBars = WindowInsetsCompat.Type.systemBars();
                Insets systemBarsInsets = insets.getInsets(systemBars);

                int top = systemBarsInsets.top;
                int bottom = systemBarsInsets.bottom;

                // 设置 padding 来“吸收”系统栏占用的空间
                v.setPadding(0, top, 0, bottom);

                // ✅ 正确做法：返回未处理的 insets（即原始 insets 减去已处理的部分）
                // 这样子视图就不会再收到 systemBars insets，避免重复处理
                return insets.inset(systemBarsInsets);
            });
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
        // 获取 SharedPreferences 对象（必须使用相同的名称和 MODE_PRIVATE）
        SharedPreferences prefs  = context.getSharedPreferences("app", Context.MODE_PRIVATE);
        prefs.edit().putBoolean("appInit", true).apply();
    }
    public static boolean getAppInit(Context context){
        // 获取 SharedPreferences 对象（必须使用相同的名称和 MODE_PRIVATE）
        SharedPreferences prefs = context.getSharedPreferences("app", Context.MODE_PRIVATE);
        return prefs.getBoolean("appInit", false); // 默认值false
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

    /**
     * 检查是否启用自动打开下载页
     * @param context 上下文
     * @return true 表示启用自动打开下载页
     */
    public static boolean isAutoOpenDownPage(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getBoolean("appUpdate_autoOpenDownPage", false); // 默认关闭
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
                        if (isAutoOpenDownPage(context)) {
                            openDownPage(context, downloadUrl);
                        } else {
                            showUpdateDialog(context, updateContext, downloadUrl);
                        }
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
        new Thread(() -> new Handler(Looper.getMainLooper()).post(() -> new MaterialAlertDialogBuilder(context)
                .setTitle("有新版本")
                .setMessage(updateContext)
                .setPositiveButton("立即更新", (dialog, which) -> openDownPage(context, downloadUrl))
                .setNegativeButton("稍后提醒", null)
                .setCancelable(false)
                .show())).start();
    }
}