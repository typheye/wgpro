package com.typheye.wgpro.ui.main;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.activity.OnBackPressedCallback;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.typheye.wgpro.core.xms.InterconnectLogic;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.ScanQRActivity;
import com.typheye.wgpro.ui.function.settings.SettingsActivity;
import com.typheye.wgpro.core.xms.UIParams;
import com.typheye.wgpro.ui.main.mainFragments.AccountFragment;
import com.typheye.wgpro.ui.main.mainFragments.DashboardFragment;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_HOME = "home";
    private static final String TAG_DASHBOARD = "dashboard";
    private static final String TAG_DEVICE = "device";
    private static final String TAG_ACCOUNT = "account";
    private static final String KEY_SELECTED_ITEM = "selected_bottom_nav_item";
    private static final int NOTIFICATION_ID = 1001;
    private static final String CHANNEL_ID = "account_channel";
    private Toolbar toolbar;
    private HomeFragment homeFragment;
    private DashboardFragment dashboardFragment;
    private DeviceFragment deviceFragment;
    private AccountFragment accountFragment;
    public static UIParams current_params = new UIParams();
    public static List<String> logs = new ArrayList<>(Arrays.asList("wgpro-android Tool V3", "https://github.com/typheye/wgpro"));
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
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);

        setSupportActionBar(toolbar);

        // 初始化 Fragment 缓存
        if (savedInstanceState == null) {
            homeFragment = new HomeFragment();
            dashboardFragment = new DashboardFragment();
            deviceFragment = new DeviceFragment();
            accountFragment = new AccountFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, homeFragment, TAG_HOME)
                    .commit();
        } else {
            homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(TAG_HOME);
            dashboardFragment = (DashboardFragment) getSupportFragmentManager().findFragmentByTag(TAG_DASHBOARD);
            deviceFragment = (DeviceFragment) getSupportFragmentManager().findFragmentByTag(TAG_DEVICE);
            accountFragment = (AccountFragment) getSupportFragmentManager().findFragmentByTag(TAG_ACCOUNT);
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
            } else if (id == R.id.nav_dashboard) {
                if (!deviceFragment.isAdded()) {
                    ft.add(R.id.fragment_container, dashboardFragment, TAG_DEVICE);
                }
                ft.show(dashboardFragment);
                toolbar.setTitle("导航");
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
                moveTaskToBack(true);
            }
        });

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
        if (dashboardFragment != null && dashboardFragment.isAdded()) ft.hide(dashboardFragment);
        if (deviceFragment != null && deviceFragment.isAdded()) ft.hide(deviceFragment);
        if (accountFragment != null && accountFragment.isAdded()) ft.hide(accountFragment);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        if (messageApi != null && connectedNodeId != null && !connectedNodeId.isEmpty()) {
            messageApi.removeListener(connectedNodeId);
        }
        super.onDestroy();
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
        }
        return super.onOptionsItemSelected(item);
    }

}