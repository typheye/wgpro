package com.typheye.wgpro.ui.main;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

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
    private long lastCloudRefreshAt;
    private int selectedPage = R.id.nav_home;
    private OnBackPressedCallback rootBackCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.useScreenCutArea(getWindow(),this);
        setContentView(R.layout.activity_main);

        // 初始化视图
        toolbar = findViewById(R.id.toolbar);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        ViewPager2 mainPager = findViewById(R.id.fragment_container);
        AppUtils.applyMainWindowInsets(findViewById(R.id.app_bar_layout), bottomNavigation);

        setSupportActionBar(toolbar);

        mainPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull @Override public Fragment createFragment(int position) {
                if (position == 0) return homeFragment = new HomeFragment();
                if (position == 1) return dashboardFragment = new DashboardFragment();
                if (position == 2) return deviceFragment = new DeviceFragment();
                return accountFragment = new AccountFragment();
            }
            @Override public int getItemCount() { return 4; }
        });
        mainPager.setOffscreenPageLimit(1);

        // 默认选中首页（如果未恢复状态）
        if (savedInstanceState == null) {
            bottomNavigation.setSelectedItemId(R.id.nav_home);
        } else {
            int savedItemId = savedInstanceState.getInt(KEY_SELECTED_ITEM, R.id.nav_home);
            bottomNavigation.setSelectedItemId(savedItemId);
        }

        bottomNavigation.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            mainPager.setCurrentItem(navIdToPosition(id), true);
            return true;
        });
        mainPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override public void onPageSelected(int position) {
                int id = positionToNavId(position);
                selectedPage = id;
                bottomNavigation.setSelectedItemId(id);
                toolbar.setTitle(new String[]{getString(R.string.app_name), "发现", "设备", "我的"}[position]);
                invalidateOptionsMenu();
            }
        });

        // 返回键处理
        rootBackCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                moveTaskToBack(true);
            }
        };
        getOnBackPressedDispatcher().addCallback(this, rootBackCallback);

        // 初始化小米 Wearable API
        initWearableApi();

    }



    @Override
    protected void onResume() {
        super.onResume();
        rootBackCallback.setEnabled(!PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("predictive_back_enabled", false));
        long now = System.currentTimeMillis();
        if (now - lastCloudRefreshAt < 60_000L) return;
        lastCloudRefreshAt = now;
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
    private int navIdToPosition(int id) {
        if (id == R.id.nav_dashboard) return 1;
        if (id == R.id.nav_device) return 2;
        if (id == R.id.nav_account) return 3;
        return 0;
    }

    private int positionToNavId(int position) {
        return new int[]{R.id.nav_home, R.id.nav_dashboard, R.id.nav_device, R.id.nav_account}[position];
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
                current_params.connected_device_id = nodes.get(0).id;
                if (current_params.connected_since == 0L) current_params.connected_since = System.currentTimeMillis();
                com.typheye.wgpro.data.DeviceDatabase deviceDb = new com.typheye.wgpro.data.DeviceDatabase(this);
                if (deviceDb.exists(current_params.connected_device_id)) deviceDb.updateConnection(current_params.connected_device_id, true);
                deviceDb.close();

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
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean home = selectedPage == R.id.nav_home;
        boolean account = selectedPage == R.id.nav_account;
        boolean accountLoggedIn = new com.typheye.wgpro.utils.tAccUtils(this).isLogin();
        MenuItem notification = menu.findItem(R.id.action_notifications);
        MenuItem compose = menu.findItem(R.id.action_compose);
        MenuItem scan = menu.findItem(R.id.action_scanqr);
        MenuItem settings = menu.findItem(R.id.action_settings);
        MenuItem deviceAdd = menu.findItem(R.id.action_device_add);
        if (notification != null) notification.setVisible(home);
        if (compose != null) compose.setVisible(home);
        if (scan != null) scan.setVisible(account && accountLoggedIn);
        if (settings != null) settings.setVisible(account && accountLoggedIn);
        if (deviceAdd != null) deviceAdd.setVisible(selectedPage == R.id.nav_device);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        if (messageApi != null && connectedNodeId != null && !connectedNodeId.isEmpty()) {
            messageApi.removeListener(connectedNodeId);
        }
        super.onDestroy();
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, @NonNull Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        ViewCompat.requestApplyInsets(findViewById(R.id.app_bar_layout));
        ViewCompat.requestApplyInsets(findViewById(R.id.bottom_navigation));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_notifications) {
            new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(this)
                    .setTitle("通知").setMessage("暂无新通知").setPositiveButton("完成", null).show();
            return true;
        } else if (id == R.id.action_compose) {
            new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(this)
                    .setTitle("写一篇").setItems(new CharSequence[]{"分享动态", "发布资源"}, null).show();
            return true;
        } else if (id == R.id.action_device_add) {
            startActivity(new Intent(this, com.typheye.wgpro.ui.function.device.AddDeviceActivity.class));
            return true;
        }
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
