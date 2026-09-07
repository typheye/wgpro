package com.typheye.wgpro.ui.main;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.provider.Settings;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import com.typheye.wgpro.core.xms.XmsConnectionProbe;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.ScanQRActivity;
import com.typheye.wgpro.ui.function.account.AccountBottomSheets;
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
import com.xiaomi.xms.wearable.node.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_LOGIN_GRANT_REQUEST_ID = "login_grant_request_id";
    private static final String KEY_SELECTED_ITEM = "selected_bottom_nav_item";
    private static final int NOTIFICATION_ID = 1001;
    private static final String CHANNEL_ID = "account_channel";
    private static final long ACCOUNT_POLL_INTERVAL_MS = 15_000L;
    private static final long WEARABLE_POLL_INTERVAL_MS = 3_000L;
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
    private volatile boolean accountPollInFlight;
    private volatile boolean wearablePollInFlight;
    private int consecutiveDisconnectedProbes;
    private String pendingGrantRequestId;
    private boolean grantFlowActive;
    private final Handler accountPollHandler = new Handler(Looper.getMainLooper());
    private final Handler wearablePollHandler = new Handler(Looper.getMainLooper());
    private final Runnable accountPoll = new Runnable() {
        @Override public void run() {
            if (!accountPollInFlight && !grantFlowActive) getAccUtils();
            accountPollHandler.postDelayed(this, ACCOUNT_POLL_INTERVAL_MS);
        }
    };
    private final Runnable wearablePoll = new Runnable() {
        @Override public void run() {
            refreshWearableConnection();
            wearablePollHandler.postDelayed(this, WEARABLE_POLL_INTERVAL_MS);
        }
    };

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
            int target = navIdToPosition(id);
            if (mainPager.getCurrentItem() != target) {
                mainPager.animate().cancel();
                mainPager.animate()
                        .alpha(0.15f)
                        .scaleX(0.98f)
                        .scaleY(0.98f)
                        .setDuration(70L)
                        .withEndAction(() -> {
                            mainPager.setCurrentItem(target, false);
                            mainPager.setAlpha(0.15f);
                            mainPager.setScaleX(0.94f);
                            mainPager.setScaleY(0.94f);
                            mainPager.animate()
                                    .alpha(1f)
                                    .scaleX(1f)
                                    .scaleY(1f)
                                    .setDuration(140L)
                                    .start();
                        })
                        .start();
            }
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
        acceptGrantIntent(getIntent());

    }



    @Override
    protected void onResume() {
        super.onResume();
        rootBackCallback.setEnabled(!PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("predictive_back_enabled", false));
        invalidateOptionsMenu();
        long now = System.currentTimeMillis();
        if (now - lastCloudRefreshAt >= 60_000L) {
            lastCloudRefreshAt = now;
            new Thread(() -> AppUtils.loadServerConfig(this)).start();
        }
        accountPollHandler.removeCallbacks(accountPoll);
        accountPollHandler.post(accountPoll);
        wearablePollHandler.removeCallbacks(wearablePoll);
        wearablePollHandler.post(wearablePoll);
        consumePendingGrantRequest();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        acceptGrantIntent(intent);
    }

    private void acceptGrantIntent(Intent intent) {
        if (intent == null || grantFlowActive) return;
        String requestId = intent.getStringExtra(EXTRA_LOGIN_GRANT_REQUEST_ID);
        intent.removeExtra(EXTRA_LOGIN_GRANT_REQUEST_ID);
        if (requestId != null && !requestId.trim().isEmpty()) {
            pendingGrantRequestId = requestId.trim();
        }
    }

    private void consumePendingGrantRequest() {
        if (grantFlowActive || pendingGrantRequestId == null || isFinishing() || isDestroyed()) return;
        String requestId = pendingGrantRequestId;
        pendingGrantRequestId = null;
        grantFlowActive = true;
        AccountBottomSheets.showGrant(this, requestId, () -> grantFlowActive = false);
    }

    @Override
    protected void onPause() {
        accountPollHandler.removeCallbacks(accountPoll);
        wearablePollHandler.removeCallbacks(wearablePoll);
        super.onPause();
    }

    private void getAccUtils() {
        tAccUtils accUtils = new tAccUtils(this);
        if (!accUtils.isLogin()) {
            accountPollInFlight = false;
            refreshAccountUi();
            return;
        }
        accountPollInFlight = true;

        accUtils.getUserDataUpdateJson(new tAccUtils.UserDataUpdateCallback() {
            @Override
            public void onSuccess(tAccUtils.UserDataUpdateResult result) {
                if (!result.isLoginValid) {
                    sendNotification();
                    accountPollInFlight = false;
                    refreshAccountUi();
                    return;
                }
                accUtils.updateUserData(new tAccUtils.SetCallback() {
                    @Override public void onSuccess() {
                        accountPollInFlight = false;
                        refreshAccountUi(result.v3Changed);
                    }
                    @Override public void onError(String message) {
                        accountPollInFlight = false;
                        refreshAccountUi(result.v3Changed);
                    }
                });
            }

            @Override
            public void onError(String message) {
                accountPollInFlight = false;
                refreshAccountUi();
            }
        });
    }

    private void refreshAccountUi() {
        refreshAccountUi(false);
    }

    private void refreshAccountUi(boolean refreshAvatar) {
        runOnUiThread(() -> {
            invalidateOptionsMenu();
            AccountFragment target = accountFragment;
            Fragment restored = getSupportFragmentManager().findFragmentByTag("f3");
            if (restored instanceof AccountFragment) target = (AccountFragment) restored;
            if (target == null || !target.isAdded()) {
                for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                    if (fragment instanceof AccountFragment && fragment.isAdded()) {
                        target = (AccountFragment) fragment;
                        break;
                    }
                }
            }
            accountFragment = target;
            if (target != null && target.isAdded()) target.refreshAccountUi(refreshAvatar);
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

        refreshWearableConnection();
    }

    private void refreshWearableConnection() {
        if (wearablePollInFlight || nodeApi == null) return;
        if (Settings.Global.getInt(getContentResolver(), Settings.Global.BLUETOOTH_ON, 0) == 0) {
            applyDisconnectedWearableState();
            return;
        }
        wearablePollInFlight = true;

        XmsConnectionProbe.probe(nodeApi, new XmsConnectionProbe.Callback() {
            @Override public void onResult(Node node) {
                wearablePollInFlight = false;
                if (node == null) {
                    consecutiveDisconnectedProbes++;
                    if (consecutiveDisconnectedProbes >= 2) applyDisconnectedWearableState();
                } else {
                    consecutiveDisconnectedProbes = 0;
                    applyConnectedWearableState(node);
                }
            }

            @Override public void onError(@NonNull Exception error) {
                wearablePollInFlight = false;
                logs.add("刷新设备连接失败：" + error.getMessage());
            }
        });
    }

    private void applyConnectedWearableState(Node node) {
        String previousNodeId = connectedNodeId;
        boolean stateChanged = !current_params.connected
                || !node.id.equals(current_params.connected_device_id);
        current_params.connected = true;
        current_params.connected_device_name = node.name;
        current_params.connected_device_id = node.id;
        if (current_params.connected_since == 0L) {
            current_params.connected_since = System.currentTimeMillis();
        }
        com.typheye.wgpro.data.DeviceDatabase deviceDb =
                new com.typheye.wgpro.data.DeviceDatabase(this);
        int databaseChanges = deviceDb.markOtherConnectedDevicesOffline("xiaomi", node.id);
        if (deviceDb.exists(node.id)) databaseChanges += deviceDb.updateConnection(node.id, true);
        deviceDb.close();
        if (stateChanged || databaseChanges > 0) refreshDeviceUi();

        if (stateChanged) logs.add("Connected to device: " + node.name);
        if (node.id.equals(previousNodeId)) return;
        if (messageApi != null && previousNodeId != null && !previousNodeId.isEmpty()) {
            messageApi.removeListener(previousNodeId);
        }
        connectedNodeId = node.id;
        authApi.checkPermission(node.id, Permission.DEVICE_MANAGER)
                .addOnSuccessListener(granted -> {
                    current_params.mifitness_connected = true;
                    logs.add("checkPermission: Permission.DEVICE_MANAGER状态为" + granted);
                    authApi.requestPermission(connectedNodeId,
                                    Permission.DEVICE_MANAGER, Permission.NOTIFY)
                            .addOnSuccessListener(permissions -> {
                                current_params.device_permission = true;
                                logs.add("权限 Permission.DEVICE_MANAGER 申请成功");
                                OnMessageReceivedListener listener = (nodeId, bytes) -> {
                                    logs.add("收到长度为" + bytes.length + "的消息，准备处理");
                                    InterconnectLogic.ProcessMessage(nodeId, new String(bytes));
                                };
                                messageApi.addListener(connectedNodeId, listener)
                                        .addOnSuccessListener(unused -> logs.add("开始监听消息！"))
                                        .addOnFailureListener(error ->
                                                logs.add("监听消息失败！" + error.getMessage()));
                            })
                            .addOnFailureListener(error ->
                                    logs.add("设备权限申请失败：" + error.getMessage()));
                })
                .addOnFailureListener(error -> logs.add("检查权限失败：" + error.getMessage()));
    }

    private void applyDisconnectedWearableState() {
        wearablePollInFlight = false;
        consecutiveDisconnectedProbes = 0;
        boolean stateChanged = current_params.connected;
        String previousNodeId = connectedNodeId;
        if (messageApi != null && previousNodeId != null && !previousNodeId.isEmpty()) {
            messageApi.removeListener(previousNodeId);
        }
        connectedNodeId = "";
        current_params.connected = false;
        current_params.connected_device_name = "未知设备";
        current_params.connected_device_id = "";
        current_params.connected_since = 0L;
        current_params.mifitness_connected = false;
        current_params.device_permission = false;
        int databaseChanges;
        try (com.typheye.wgpro.data.DeviceDatabase deviceDb =
                     new com.typheye.wgpro.data.DeviceDatabase(this)) {
            databaseChanges = deviceDb.markConnectedDevicesOffline("xiaomi");
        }
        if (stateChanged || databaseChanges > 0) {
            logs.add("Wearable disconnected");
            refreshDeviceUi();
        }
    }

    private void refreshDeviceUi() {
        runOnUiThread(() -> {
            DeviceFragment target = deviceFragment;
            Fragment restored = getSupportFragmentManager().findFragmentByTag("f2");
            if (restored instanceof DeviceFragment) target = (DeviceFragment) restored;
            if (target == null || !target.isAdded()) {
                for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                    if (fragment instanceof DeviceFragment && fragment.isAdded()) {
                        target = (DeviceFragment) fragment;
                        break;
                    }
                }
            }
            deviceFragment = target;
            if (target != null && target.isAdded()) target.updateUI(current_params);
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
        MenuItem dashboardEdit = menu.findItem(R.id.action_dashboard_edit);
        if (notification != null) notification.setVisible(home);
        if (compose != null) compose.setVisible(home);
        if (scan != null) scan.setVisible(account && accountLoggedIn);
        if (settings != null) settings.setVisible(account);
        if (deviceAdd != null) deviceAdd.setVisible(selectedPage == R.id.nav_device);
        if (dashboardEdit != null) dashboardEdit.setVisible(selectedPage == R.id.nav_dashboard);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        wearablePollHandler.removeCallbacks(wearablePoll);
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
        } else if (id == R.id.action_dashboard_edit) {
            Fragment restored = getSupportFragmentManager().findFragmentByTag("f1");
            DashboardFragment target = restored instanceof DashboardFragment
                    ? (DashboardFragment) restored : dashboardFragment;
            if (target != null && target.isAdded()) {
                target.showEditor();
            }
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
