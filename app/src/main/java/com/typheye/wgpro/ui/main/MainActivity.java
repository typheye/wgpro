package com.typheye.wgpro.ui.main;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.activity.OnBackPressedCallback;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.typheye.wgpro.core.xms.InterconnectLogic;
import com.typheye.wgpro.core.xms.JSKit;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.ScanQRActivity;
import com.typheye.wgpro.ui.function.settings.SettingsActivity;
import com.typheye.wgpro.core.xms.UIParams;
import com.typheye.wgpro.ui.main.mainFragments.AccountFragment;
import com.typheye.wgpro.ui.main.mainFragments.DeviceFragment;
import com.typheye.wgpro.ui.main.mainFragments.HomeFragment;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.utils.tAccUtils;
import com.xiaomi.xms.wearable.Wearable;
import com.xiaomi.xms.wearable.auth.AuthApi;
import com.xiaomi.xms.wearable.auth.Permission;
import com.xiaomi.xms.wearable.message.MessageApi;
import com.xiaomi.xms.wearable.message.OnMessageReceivedListener;
import com.xiaomi.xms.wearable.node.NodeApi;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_HOME = "home";
    private static final String TAG_DEVICE = "device";
    private static final String TAG_ACCOUNT = "account";
    private static final String KEY_SELECTED_ITEM = "selected_bottom_nav_item";
    private static final int NOTIFICATION_ID = 1001;
    private static final String CHANNEL_ID = "account_channel";
    private Toolbar toolbar;
    private Toolbar toolbar2;
    private BottomNavigationView bottomNavigation;
    private WebView main_webview;
    private HomeFragment homeFragment;
    private DeviceFragment deviceFragment;
    private AccountFragment accountFragment;
    public static UIParams current_params = new UIParams();
    public static List<String> logs = new ArrayList<>(Arrays.asList("wgpro-android Tool V2", "https://github.com/typheye/wgpro-android"));
    public static NodeApi nodeApi = null;
    public static AuthApi authApi = null;
    public static MessageApi messageApi = null;
    public static String connectedNodeId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.useScreenCutArea(getWindow(),this);
        setContentView(R.layout.activity_main);
        AppUtils.fixScreenCutArea(findViewById(R.id.container));

        // 初始化视图
        toolbar = findViewById(R.id.toolbar);
        toolbar2 = findViewById(R.id.toolbar2);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        main_webview = findViewById(R.id.main_webview);

        setSupportActionBar(toolbar);

        toolbar.setVisibility(View.VISIBLE);
        toolbar2.setVisibility(View.GONE);

        // 初始化 Fragment 缓存
        if (savedInstanceState == null) {
            homeFragment = new HomeFragment();
            deviceFragment = new DeviceFragment();
            accountFragment = new AccountFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, homeFragment, TAG_HOME)
                    .commit();
        } else {
            homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(TAG_HOME);
            deviceFragment = (DeviceFragment) getSupportFragmentManager().findFragmentByTag(TAG_DEVICE);
            accountFragment = (AccountFragment) getSupportFragmentManager().findFragmentByTag(TAG_ACCOUNT);
        }

        // 恢复 WebView 状态
        if (savedInstanceState != null) {
            boolean isWebViewVisible = savedInstanceState.getBoolean("webview_visible", false);
            if (isWebViewVisible) {
                showWebView(true);
            }
        }

        // 默认选中首页（如果未恢复状态）
        if (savedInstanceState == null) {
            bottomNavigation.setSelectedItemId(R.id.nav_home);
        } else {
            int savedItemId = savedInstanceState.getInt(KEY_SELECTED_ITEM, R.id.nav_home);
            bottomNavigation.setSelectedItemId(savedItemId);
        }

        // 设置 BottomNavigationView 点击事件
        bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            // 隐藏所有 Fragment
            hideAllFragments(ft);

            if (id == R.id.nav_home) {
                if (!homeFragment.isAdded()) {
                    ft.add(R.id.fragment_container, homeFragment, TAG_HOME);
                }
                ft.show(homeFragment);
                toolbar.setTitle(R.string.app_name);
            } else if (id == R.id.nav_device) {
                if (!deviceFragment.isAdded()) {
                    ft.add(R.id.fragment_container, deviceFragment, TAG_DEVICE);
                }
                ft.show(deviceFragment);
                toolbar.setTitle("设备");
            } else if (id == R.id.nav_account) {
                if (!accountFragment.isAdded()) {
                    ft.add(R.id.fragment_container, accountFragment, TAG_ACCOUNT);
                }
                ft.show(accountFragment);
                toolbar.setTitle("我的");
            }

            ft.commit();
            return true;
        });

        // 返回键处理
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (main_webview != null && main_webview.getVisibility() == View.VISIBLE) {
                    showWebView(false);
                } else {
                    moveTaskToBack(true);
                }
            }
        });

        // 配置 WebView
        setupWebView();

        // 初始化小米 Wearable API
        initWearableApi();

    }



    @Override
    protected void onResume() {
        super.onResume();
        // 使用线程或AsyncTask来执行网络请求
        // 执行网络请求等操作
        // 注意：如果whileUpdate内部有网络请求，这里可能需要调整
        new Thread(this::whileUpdate).start();
    }

    public void whileUpdate(){
        // 加载服务器配置
        AppUtils.loadServerConfig(this);

        getAccUtils();
    }

    private void getAccUtils() {
        tAccUtils accUtils = new tAccUtils(this);

        // 简单调用，不需要处理dialog，因为内部已经处理
        accUtils.getUserDataUpdateJson(new tAccUtils.UserDataUpdateCallback() {
            @Override
            public void onSuccess(tAccUtils.UserDataUpdateResult result) {
                if (!result.isLoginValid) {
                    // 登录状态失效
                    sendNotification();
                }
            }

            @Override
            public void onError(String message) {
                // 处理网络错误
            }
        });

        // 简单调用，不需要处理dialog，因为内部已经处理
        accUtils.updateUserData(new tAccUtils.SetCallback() {
            @Override
            public void onSuccess() {
                // 成功更新数据
            }

            @Override
            public void onError(String message) {
                // 处理网络错误
            }
        });
    }

    private void sendNotification() {
        // 创建 Intent 指向 MainActivity
        Intent intent = new Intent(this, MainActivity.class);

        // 关键标志：确保复用已存在的 Activity 实例
        intent.setFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP |    // 清除目标 Activity 上方的所有 Activity
                        Intent.FLAG_ACTIVITY_SINGLE_TOP |   // 如果已存在，则不新建实例，调用 onNewIntent()
                        Intent.FLAG_ACTIVITY_NEW_TASK      // 确保从通知栏启动时能正确进入任务栈 (重要！)
        );

        // 使用 PendingIntent，注意 Android 12+ 需要 FLAG_IMMUTABLE
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle("Typheye账户")
                .setContentText("检测到账户异常，请立即处理")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_ERROR)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
    private void hideAllFragments(FragmentTransaction ft) {
        if (homeFragment != null && homeFragment.isAdded()) ft.hide(homeFragment);
        if (deviceFragment != null && deviceFragment.isAdded()) ft.hide(deviceFragment);
        if (accountFragment != null && accountFragment.isAdded()) ft.hide(accountFragment);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {
        if (main_webview == null) return;

        main_webview.setVerticalScrollBarEnabled(false);
        main_webview.setHorizontalScrollBarEnabled(false);
        main_webview.setScrollbarFadingEnabled(true);

        WebSettings webSettings = main_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setDomStorageEnabled(true);

        // --- ✅ 关键：设置 WebViewClient 并重写 onPageFinished ---
        main_webview.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url); // 必须调用 super
                // ✅ 在这里获取系统主题并通知前端
                boolean isDarkMode = (getResources().getConfiguration().uiMode
                        & Configuration.UI_MODE_NIGHT_MASK)
                        == Configuration.UI_MODE_NIGHT_YES;
                String jsCode = String.format(
                        "javascript:window.dispatchEvent(" +
                                "  new CustomEvent('systemThemeChange', { detail: { dark: %b } })" +
                                ");",
                        isDarkMode
                );
                // 根据 API 版本选择执行 JS 的方式
                view.evaluateJavascript(jsCode, null);
            }
        });


        main_webview.setWebViewClient(new LocalContentWebViewClient());
        WebView.setWebContentsDebuggingEnabled(true);
        main_webview.addJavascriptInterface(new JSKit(), "androidlib");
        main_webview.loadUrl("file:///android_asset/index.html");
    }

    private void initWearableApi() {
        nodeApi = Wearable.getNodeApi(getApplicationContext());
        authApi = Wearable.getAuthApi(getApplicationContext());
        messageApi = Wearable.getMessageApi(getApplicationContext());

        nodeApi.getConnectedNodes().addOnSuccessListener(nodes -> {
            logs.add("Node Count: " + nodes.size());
            if (!nodes.isEmpty()) {
                current_params.connected = true;
                current_params.connected_device_name = nodes.get(0).name;

                runOnUiThread(() -> {
                    if (deviceFragment != null && deviceFragment.isAdded()) {
                        deviceFragment.updateUI(current_params);
                    }
                });

                logs.add("Connected to device: " + nodes.get(0).name);
                authApi.checkPermission(nodes.get(0).id, Permission.DEVICE_MANAGER)
                        .addOnSuccessListener(aBoolean -> {
                            current_params.mifitness_connected = true;
                            connectedNodeId = nodes.get(0).id;
                            logs.add("checkPermission: Permission.DEVICE_MANAGER状态为" + aBoolean);

                            authApi.requestPermission(connectedNodeId, Permission.DEVICE_MANAGER, Permission.NOTIFY)
                                    .addOnSuccessListener(permissions -> {
                                        current_params.device_permission = true;
                                        logs.add("权限 Permission.DEVICE_MANAGER 申请成功");
                                        logs.add("所有准备工作已完成！开始等待Hello Packet...");

                                        OnMessageReceivedListener listener = (nodeId, bytes) -> {
                                            logs.add("收到长度为" + bytes.length + "的消息，准备处理");
                                            InterconnectLogic.ProcessMessage(nodeId, new String(bytes));
                                        };

                                        messageApi.addListener(connectedNodeId, listener)
                                                .addOnSuccessListener(unused -> logs.add("开始监听消息！"))
                                                .addOnFailureListener(e -> logs.add("监听消息失败！" + e.getMessage()));
                                    })
                                    .addOnFailureListener(e -> logs.add("设备权限申请失败：" + e.getMessage()));
                        })
                        .addOnFailureListener(e -> logs.add("检查权限失败：" + e.getMessage()));
            }
        });
    }

    @Override
    protected void onDestroy() {
        if (messageApi != null && connectedNodeId != null && !connectedNodeId.isEmpty()) {
            messageApi.removeListener(connectedNodeId);
        }
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (main_webview != null) {
            outState.putBoolean("webview_visible", main_webview.getVisibility() == View.VISIBLE);
        }
        outState.putInt(KEY_SELECTED_ITEM, bottomNavigation.getSelectedItemId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (main_webview == null || main_webview.getVisibility() != View.VISIBLE) {
            getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_scanqr) {
            Intent intent = new Intent(MainActivity.this, ScanQRActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == android.R.id.home) {
            showWebView(false);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // 供 Fragment 调用
    public void showWebView(boolean ifShow) {
        if (main_webview != null) {
            if (ifShow) {
                main_webview.setVisibility(View.VISIBLE);
                bottomNavigation.setVisibility(View.GONE);
                toolbar.setVisibility(View.GONE);
                toolbar2.setVisibility(View.VISIBLE);
                setSupportActionBar(toolbar2);
                //invalidateOptionsMenu(); // 刷新菜单
            } else {
                main_webview.setVisibility(View.GONE);
                bottomNavigation.setVisibility(View.VISIBLE);
                toolbar.setVisibility(View.VISIBLE);
                toolbar2.setVisibility(View.GONE);
                setSupportActionBar(toolbar);
                //invalidateOptionsMenu();
            }
        }
    }

    // 自定义 WebViewClient
    private class LocalContentWebViewClient extends WebViewClient {

        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            String url = request.getUrl().toString();
            if (url.startsWith("file:///")) {
                String assetPath = url.substring(8); // 移除 "file:///"
                try {
                    InputStream inputStream = getAssets().open(assetPath);
                    String mimeType = getMimeType(assetPath);
                    return new WebResourceResponse(mimeType, "UTF-8", inputStream);
                } catch (IOException e) {
                    //e.printStackTrace();
                    return null;
                }
            }
            return super.shouldInterceptRequest(view, request);
        }

        private String getMimeType(String url) {
            if (url.endsWith(".html")) return "text/html";
            else if (url.endsWith(".js")) return "application/javascript";
            else if (url.endsWith(".css")) return "text/css";
            else if (url.endsWith(".png")) return "image/png";
            else if (url.endsWith(".jpg") || url.endsWith(".jpeg")) return "image/jpeg";
            else if (url.endsWith(".gif")) return "image/gif";
            else if (url.endsWith(".svg")) return "image/svg+xml";
            else if (url.endsWith(".woff")) return "font/woff";
            else if (url.endsWith(".woff2")) return "font/woff2";
            else if (url.endsWith(".ttf")) return "font/ttf";
            else return "text/plain";
        }
    }
}