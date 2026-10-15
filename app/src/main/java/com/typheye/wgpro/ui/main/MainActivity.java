package com.typheye.wgpro.ui.main;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.typheye.wgpro.R;
import com.typheye.wgpro.core.CoreApi;
import com.typheye.wgpro.core.CoreService;
import com.typheye.wgpro.core.PushApi;
import com.typheye.wgpro.core.PushService;
import com.typheye.wgpro.core.state.AccountSnapshot;
import com.typheye.wgpro.core.state.AppState;
import com.typheye.wgpro.core.state.InboxSnapshot;
import com.typheye.wgpro.core.xms.UIParams;
import com.typheye.wgpro.ui.function.ScanQRActivity;
import com.typheye.wgpro.ui.function.account.AccountBottomSheets;
import com.typheye.wgpro.ui.function.settings.SettingsActivity;
import com.typheye.wgpro.ui.main.mainFragments.AccountFragment;
import com.typheye.wgpro.ui.main.mainFragments.DeviceFragment;
import com.typheye.wgpro.ui.main.mainFragments.HomeFragment;
import com.typheye.wgpro.utils.AppBarBlur;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.utils.BarBlurController;
import com.typheye.wgpro.utils.tAccUtils;
import com.xiaomi.xms.wearable.auth.AuthApi;
import com.xiaomi.xms.wearable.message.MessageApi;
import com.xiaomi.xms.wearable.node.NodeApi;

import java.util.List;

/**
 * 一级容器页面。
 *
 * 改造后这里只负责界面：账户轮询、XMS 设备探测、未读徽标、Android 通知
 * 全部下沉到 CoreService（.Core）与 PushService（.Push），页面改为订阅 AppState。
 *
 * 为兼容历史代码，current_params / logs / messageApi 等静态字段仍然保留，
 * 但它们是 CoreService 状态的镜像，不再是状态本身。
 */
public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_LOGIN_GRANT_REQUEST_ID = "login_grant_request_id";
    private static final String KEY_SELECTED_ITEM = "selected_bottom_nav_item";
    private static final String[] HOME_TAB_TITLES = {"动态", "应用", "资源"};
    private static final String[] DEVICE_TAB_TITLES = {"穿戴设备", "模拟设备"};
    private static final long FOREGROUND_REFRESH_MIN_INTERVAL_MS = 5_000L;

    private Toolbar toolbar;
    private View searchBox;
    private ViewPager2 mainPager;
    private BottomNavigationView bottomNavigation;
    private TabLayout pageTabs;
    private TabLayoutMediator pageTabsMediator;
    private ViewPager2 boundTabsPager;
    private HomeFragment homeFragment;
    private DeviceFragment deviceFragment;
    /** 当前应用栏标题缓存：同值不再写入，避免切页/数据回调时标题闪烁。 */
    private String lastTopBarTitle;
    private AccountFragment accountFragment;
    private BarBlurController barBlurController;

    /** 兼容镜像：CoreService 的 DeviceEngine 持有真实状态，并持续更新这个引用。 */
    public static UIParams current_params = new UIParams();
    /** 兼容镜像：真正的环形日志在 AppState 中（线程安全），这里指向设备通信日志。 */
    public static List<String> logs = AppState.get().deviceLog();
    /** 兼容镜像：由 CoreService 的 DeviceEngine 写入。 */
    public static NodeApi nodeApi = null;
    public static AuthApi authApi = null;
    public static MessageApi messageApi = null;
    public static String connectedNodeId = "";

    private int selectedPage = R.id.nav_home;
    private OnBackPressedCallback rootBackCallback;
    private String pendingGrantRequestId;
    private boolean grantFlowActive;
    private int inboxUnreadCount;
    private BadgeDrawable notificationBadge;
    private CoreApi coreApi;
    private PushApi pushApi;
    private boolean servicesStarted;
    private long lastForegroundRefreshAt;

    private final ServiceConnection coreConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (service instanceof CoreService.LocalBinder) {
                coreApi = ((CoreService.LocalBinder) service).api();
                coreApi.requestAccountRefresh();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            coreApi = null;
        }
    };

    private final ServiceConnection pushConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            if (service instanceof PushService.LocalBinder) {
                pushApi = ((PushService.LocalBinder) service).api();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            pushApi = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 模拟设备：应用进程重启后，把上次残留的「运行中」清零（引擎是应用子进程，随进程结束）
        final com.typheye.wgpro.core.emu.VelaEmulatorManager emuManager =
                com.typheye.wgpro.core.emu.VelaEmulatorManager.get();
        emuManager.init(this);
        new Thread(() -> emuManager.resetStaleState(
                getApplicationContext()), "virtual-reset").start();

        // TODO(临时调试入口，适配完成后删除)：一键直达模拟设备页并补齐设备记录。
        if (getIntent() != null && getIntent().getBooleanExtra("wgpro_debug_virtual", false)) {
            final String avd = "vela-miwear-watch-5.0";
            try (com.typheye.wgpro.data.DeviceDatabase db =
                         new com.typheye.wgpro.data.DeviceDatabase(this)) {
                db.upsert("virtual:" + avd, avd, "virtual", false);
                db.updateNote("virtual:" + avd, "我的虚拟手环");
            } catch (Exception ignored) {
            }
            startActivity(new android.content.Intent(this,
                            com.typheye.wgpro.ui.function.device.VirtualDeviceActivity.class)
                    .putExtra(com.typheye.wgpro.ui.function.device.VirtualDeviceFragment.ARG_AVD, avd));
        }
        // 升级/异常退出后可能残留“已登录标记 + 不可用凭据”，清理放到后台线程：
        // 它要读 SharedPreferences + 走 keystore，放主线程会让刚进首页明显卡顿（首帧也被拖住）。
        final android.content.Context appContext = getApplicationContext();
        new Thread(() -> new tAccUtils(appContext).reconcileLoginState(), "login-reconcile").start();

        // 进程启动对账：清掉残留的"模拟设备运行中"常驻通知、把设备库在线状态归零
        com.typheye.wgpro.core.emu.VelaEmulatorManager.get().init(this);
        AppUtils.useScreenCutArea(getWindow(), this);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        searchBox = findViewById(R.id.main_search_box);
        pageTabs = findViewById(R.id.page_tabs);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        mainPager = findViewById(R.id.fragment_container);
        AppUtils.applyMainWindowInsets(findViewById(R.id.app_bar_layout), bottomNavigation);

        // 内容铺满整屏：给每页滚动容器补上应用栏/导航栏高度的内边距，
        // 初始不遮挡、滚动时内容从毛玻璃栏下方穿过
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.container),
                (view, insets) -> {
                    view.post(this::applyContentBarInsets);
                    return insets;
                });
        bottomNavigation.post(this::applyContentBarInsets);

        // 应用栏 / 导航栏毛玻璃背景（快照整个 ViewPager，稳定可渲染版本）
        barBlurController = BarBlurController.install(this, mainPager,
                findViewById(R.id.blur_backdrop_top),
                findViewById(R.id.blur_backdrop_bottom));

        // 应用栏 / 底部导航栏登记：模糊关闭时改成与页面一致的不透明底色
        BarBlurController.registerBar(this, findViewById(R.id.app_bar_layout));
        BarBlurController.registerBar(this, bottomNavigation);

        // 布局稳定后反复校准：每页的栏高度内边距 + 毛玻璃快照
        mainPager.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            mainPager.post(() -> {
                int current = mainPager.getCurrentItem();
                if (pageTabsMediator == null && (current == 0 || current == 2)) {
                    bindPageTabs(current);
                }
                applyContentBarInsets();
                if (barBlurController != null) barBlurController.scheduleUpdate();
            });
        });

        setSupportActionBar(toolbar);
        if (searchBox != null) {
            // 首页应用栏的搜索控件：点击进入搜索页（复制浏览历史页结构：动态/应用/资源）
            searchBox.setOnClickListener(v -> openSearch());
        }

        mainPager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull @Override public Fragment createFragment(int position) {
                if (position == 0) return homeFragment = new HomeFragment();
                if (position == 1) return deviceFragment = new DeviceFragment();
                return accountFragment = new AccountFragment();
            }
            @Override public int getItemCount() { return 3; }
        });
        mainPager.setOffscreenPageLimit(1);

        // 调试入口（**仅 debug 构建**）：adb shell am start ... --es vela_adb_test <设备id> → 自检客机 adb 通道
        // （宿主 adb forward 只能打 127.0.0.1，而引擎监听在 ::1，宿主测不了，只能让 App 自己测）
        boolean debuggable = (getApplicationInfo().flags
                & android.content.pm.ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        if (debuggable && getIntent() != null) {
            final String testDevice = getIntent().getStringExtra("vela_adb_test");
            if (testDevice != null) {
                getIntent().removeExtra("vela_adb_test");
                runVelaAdbTest(testDevice);
            }
            final String createModel = getIntent().getStringExtra("vela_create");
            if (createModel != null) {
                getIntent().removeExtra("vela_create");
                runVelaCreate(createModel);
            }
        }

        // 桌面快捷方式：launcher 是外部进程，只能拉起导出的 MainActivity，这里按 extra 分发
        if (getIntent() != null) {
            final String shortcutId = getIntent().getStringExtra(
                    com.typheye.wgpro.ui.function.settings.DesktopShortcutActions
                            .EXTRA_SHORTCUT_ID);
            if (shortcutId != null) {
                getIntent().removeExtra(com.typheye.wgpro.ui.function.settings
                        .DesktopShortcutActions.EXTRA_SHORTCUT_ID);
                mainPager.post(() -> com.typheye.wgpro.ui.function.settings
                        .DesktopShortcutActions.run(this, shortcutId));
            }
        }

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
                applyTopBarForPage(position);
                invalidateOptionsMenu();
                mainPager.post(() -> bindPageTabs(position));
                mainPager.post(MainActivity.this::applyContentBarInsets);
                // 翻页动画结束后再校准一次，保证新页面的毛玻璃与内边距正确
                mainPager.postDelayed(MainActivity.this::applyContentBarInsets, 350L);
                if (barBlurController != null) barBlurController.scheduleUpdate();
            }
        });
        mainPager.post(() -> bindPageTabs(mainPager.getCurrentItem()));
        mainPager.post(() -> applyTopBarForPage(mainPager.getCurrentItem()));

        rootBackCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                moveTaskToBack(true);
            }
        };
        getOnBackPressedDispatcher().addCallback(this, rootBackCallback);


        observeAppState();
        bindServices();
        startServicesIfNeeded();
        acceptGrantIntent(getIntent());
        maybeOpenLoginFromIntent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 首页返回 = 回后台（不是退出应用）：始终由自己接管，
        // 否则会退化成系统默认的 finish()，按返回直接把应用关掉。
        rootBackCallback.setEnabled(true);
        invalidateOptionsMenu();
        startServicesIfNeeded();
        refreshFromServices();
        maybeWarnNotificationChannel();
        maybeWarnBackgroundRestriction();
        consumePendingGrantRequest();
        tAccUtils.resumeBrowserLoginPolling(this);
        // 服务端要求强制更新且本机版本偏低时，把用户挡在更新页（不能用软件）
        AppUtils.ensureForceUpdateGate(this);
    }

    /** 后台连接反复被系统掐断时，引导用户放开省电/后台联网限制。 */
    private void maybeWarnBackgroundRestriction() {
        if (!new tAccUtils(this).isLogin()) return;
        if (!com.typheye.wgpro.core.BackgroundHealth.shouldWarn(this)) return;
        com.typheye.wgpro.core.BackgroundHealth.markWarned(this);
        new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(this)
                .setTitle("后台连接被系统中断")
                .setMessage("系统在应用退到后台后限制了它的网络，消息可能无法及时送达。"
                        + "请在系统设置中把「腕管Pro」的省电策略改为「无限制」，并允许自启动与后台联网。")
                .setNegativeButton("知道了", null)
                .setPositiveButton("去设置", (dialog, which) -> {
                    try {
                        startActivity(com.typheye.wgpro.core.BackgroundHealth
                                .powerSettingsIntent(this));
                    } catch (Exception error) {
                        try {
                            startActivity(new android.content.Intent(
                                    android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                    android.net.Uri.parse("package:" + getPackageName())));
                        } catch (Exception ignored) {
                        }
                    }
                })
                .show();
    }

    /**
     * 如果「新消息」渠道被系统/用户降级（不会出现横幅），提示用户去开启。
     * 每 7 天最多提醒一次，避免打扰。
     */
    private void maybeWarnNotificationChannel() {
        if (!new tAccUtils(this).isLogin()) return;
        if (com.typheye.wgpro.utils.InboxNotificationHelper.isMessageChannelAlerting(this)) return;
        android.content.SharedPreferences prefs =
                getSharedPreferences("notification_health", MODE_PRIVATE);
        long lastPrompt = prefs.getLong("last_prompt_at", 0L);
        if (System.currentTimeMillis() - lastPrompt < 7L * 24 * 60 * 60 * 1000) return;
        prefs.edit().putLong("last_prompt_at", System.currentTimeMillis()).apply();
        new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(this)
                .setTitle("消息横幅没有开启")
                .setMessage("系统把「新消息」通知降级了，收到私信时不会有横幅提示。"
                        + "点击「去开启」把该通知类别设为高重要性即可。")
                .setNegativeButton("以后再说", null)
                .setPositiveButton("去开启", (dialog, which) -> {
                    try {
                        startActivity(com.typheye.wgpro.utils.InboxNotificationHelper
                                .channelSettingsIntent(this,
                                        com.typheye.wgpro.utils.InboxNotificationHelper
                                                .messageChannelId()));
                    } catch (Exception ignored) {
                    }
                })
                .show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        acceptGrantIntent(intent);
        maybeOpenLoginFromIntent();
        // 关键：应用已在前台时不会再走 onResume，这里必须立即消费扫码授权请求，
        // 否则扫码后"没有任何反应"（授权弹窗永远不出现）。
        consumePendingGrantRequest();
        tAccUtils.resumeBrowserLoginPolling(this);
    }

    /** 其它页面点「去登录」后回到这里：切到「我的」并弹出登录面板。 */
    private void maybeOpenLoginFromIntent() {
        Intent intent = getIntent();
        if (intent == null) return;
        // 「去登录」只切页：关闭来源页面 + 回到「我的」，不弹任何登录面板。
        if (intent.getBooleanExtra(
                com.typheye.wgpro.ui.LoginGate.EXTRA_SELECT_ACCOUNT_TAB, false)) {
            intent.removeExtra(com.typheye.wgpro.ui.LoginGate.EXTRA_SELECT_ACCOUNT_TAB);
            if (mainPager != null) mainPager.setCurrentItem(3, false);
            selectedPage = R.id.nav_account;
            invalidateOptionsMenu();
            return;
        }
        if (!intent.getBooleanExtra(
                com.typheye.wgpro.ui.LoginGate.EXTRA_OPEN_LOGIN, false)) {
            return;
        }
        intent.removeExtra(com.typheye.wgpro.ui.LoginGate.EXTRA_OPEN_LOGIN);
        if (mainPager != null) mainPager.setCurrentItem(3, false);
        selectedPage = R.id.nav_account;
        invalidateOptionsMenu();
        if (new tAccUtils(this).isLogin()) return;
        tAccUtils.startBrowserLogin(this, new tAccUtils.BrowserLoginCallback() {
            @Override
            public void onLoginSucceeded() {
                invalidateOptionsMenu();
                startServicesIfNeeded();
                refreshFromServices();
                refreshAccountFromUser();
            }

            @Override
            public void onLoginFailed(String message) {
            }
        });
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
        // 扫码授权需要当前账户；会话在扫码过程中失效时只提示，不再继续走授权流程。
        if (!new tAccUtils(this).isLogin()) {
            pendingGrantRequestId = null;
            com.typheye.wgpro.ui.LoginGate.require(this, "扫码授权");
            return;
        }
        String requestId = pendingGrantRequestId;
        pendingGrantRequestId = null;
        grantFlowActive = true;
        AccountBottomSheets.showGrant(this, requestId, () -> grantFlowActive = false);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    /** 兼容入口：其它页面仍在调用它触发账户刷新。 */
    public void refreshAccountFromUser() {
        if (grantFlowActive) return;
        // 拉用户资料的同时把积分（可用/赠送）一起刷新
        com.typheye.wgpro.ui.widget.Points.refresh(this, true, null);
        if (coreApi != null) {
            coreApi.requestAccountRefresh();
        } else {
            CoreService.requestRefresh(this);
        }
    }

    /**
     * 需要登录的操作统一入口：未登录时给友好提示并引导登录，而不是让它走网络再报错。
     * 浏览（动态/应用/资源）不需要登录。
     */
    private boolean requireLogin(String actionName) {
        return com.typheye.wgpro.ui.LoginGate.require(this, actionName);
    }

    private void bindServices() {
        try {
            bindService(new Intent(this, CoreService.class), coreConnection, Context.BIND_AUTO_CREATE);
            bindService(new Intent(this, PushService.class), pushConnection, Context.BIND_AUTO_CREATE);
        } catch (Exception error) {
            AppState.get().addAccountLog("绑定服务失败：" + error.getMessage());
        }
    }

    private void startServicesIfNeeded() {
        boolean loggedIn = new tAccUtils(this).isLogin();
        CoreService.start(this);
        if (loggedIn) {
            PushService.start(this);
        } else if (PushService.isRunning()) {
            PushService.stop(this);
        }
        servicesStarted = true;
    }

    /** 回到前台时做一次节流刷新，替代原来的 15 秒 Activity 轮询。 */
    private void refreshFromServices() {
        long now = System.currentTimeMillis();
        if (now - lastForegroundRefreshAt < FOREGROUND_REFRESH_MIN_INTERVAL_MS) return;
        lastForegroundRefreshAt = now;
        if (coreApi != null) coreApi.requestAccountRefresh();
        if (pushApi != null) pushApi.refreshNow();
    }

    private void observeAppState() {
        AppState.get().device().observe(this, this::refreshDeviceUi);
        AppState.get().account().observe(this, snapshot -> {
            refreshAccountUi(snapshot);
            // 页内登录/退出后立即同步服务状态（不必等到下一次 onResume）
            startServicesIfNeeded();
        });
        AppState.get().inbox().observe(this, this::applyInboxState);
        applyInboxState(AppState.get().inboxSnapshot());
    }

    private void applyInboxState(InboxSnapshot snapshot) {
        inboxUnreadCount = snapshot == null ? 0 : snapshot.unreadTotal;
        boolean visible = snapshot != null && snapshot.dotVisible();
        if (notificationBadge == null) return;
        if (!visible) {
            notificationBadge.setVisible(false);
        } else {
            notificationBadge.clearNumber();
            notificationBadge.setVisible(true);
        }
    }

    private void refreshAccountUi(AccountSnapshot snapshot) {
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
            boolean avatarChanged = snapshot != null && snapshot.avatarChanged;
            if (target != null && target.isAdded()) target.refreshAccountUi(avatarChanged);
        });
    }

    private void refreshDeviceUi(UIParams params) {
        if (params == null) return;
        current_params = params;
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
            if (target != null && target.isAdded()) target.updateUI(params);
        });
    }

    private int navIdToPosition(int id) {
        if (id == R.id.nav_device) return 1;
        if (id == R.id.nav_account) return 2;
        return 0;
    }

    private int positionToNavId(int position) {
        return new int[]{R.id.nav_home, R.id.nav_device, R.id.nav_account}[position];
    }

    /**
     * 应用栏顶部：首页只显示搜索控件（隐藏「腕管Pro」标题），其它页恢复标题、隐藏搜索控件。
     */
    private void applyTopBarForPage(int position) {
        boolean home = position == 0;
        // 首页/账户页空标题（首页让给搜索控件）；设备页显示「共 X 台」
        setTopBarTitle(position == 1 ? deviceTitle() : "");
        if (position == 1) {
            scheduleDeviceTitleCatchUp();
        }
        if (searchBox != null) {
            searchBox.setVisibility(home ? View.VISIBLE : View.GONE);
        }
    }

    /** 调试用：adb 触发"解压 + 按皮肤建一台模拟设备"，结果落 logcat(VelaCreateTest)。 */
    private void runVelaCreate(@NonNull final String modelId) {
        new Thread(() -> {
            try {
                android.util.Log.i("VelaCreateTest", "model=" + modelId);
                final String deviceId = com.typheye.wgpro.core.emu.VelaEmulatorManager
                        .deviceId(modelId) + ":" + com.typheye.wgpro.core.emu.VelaProvision
                        .randomSuffix();
                com.typheye.wgpro.core.emu.VelaEmulator emulator =
                        com.typheye.wgpro.core.emu.VelaProvision
                                .unpackRuntime(this, modelId, deviceId);
                android.util.Log.i("VelaCreateTest", "解压完成 deviceId=" + deviceId + " skins="
                        + com.typheye.wgpro.core.emu.VelaEmulator
                                .availableSkins(emulator.engineDir()));
                java.util.List<String[]> manifest = com.typheye.wgpro.core.emu.VelaProvision
                        .manifestDevices(emulator.runtimeDir());
                android.util.Log.i("VelaCreateTest", "清单设备数=" + manifest.size()
                        + (manifest.isEmpty() ? "" : " 首个=" + manifest.get(0)[1]));
                String skin = manifest.isEmpty() ? "xiaomi_s4" : manifest.get(0)[2];
                com.typheye.wgpro.core.emu.VelaProvision.createDevice(this, emulator, deviceId,
                        skin, "调试设备 " + deviceId);
                android.util.Log.i("VelaCreateTest", "创建完成 deviceId=" + deviceId
                        + " skin=" + skin);
            } catch (Throwable error) {
                android.util.Log.i("VelaCreateTest", "异常: " + error);
            }
        }, "vela-create-test").start();
    }

    /**
     * 调试用：从 adb 触发客机 adb 通道自检，结果打到 logcat 的 {@code VelaAdbTest}。
     *
     * <p>宿主 adb forward 只能打到 127.0.0.1，而 vela 引擎监听的是 {@code ::1}（127.0.0.1
     * 会立刻被关闭），所以只能在 App 内用自带的 Java adb 客户端测。逐条把结果打出来，
     * 便于确定客机固件认哪条安装命令。</p>
     */
    private void runVelaAdbTest(@NonNull final String deviceId) {
        new Thread(() -> {
            final String model = com.typheye.wgpro.core.emu.VelaProvision.modelIdOf(deviceId);
            try {
                com.typheye.wgpro.core.emu.VelaEmulatorManager manager =
                        com.typheye.wgpro.core.emu.VelaEmulatorManager.get();
                manager.init(this);
                com.typheye.wgpro.core.emu.VelaEmulatorManager.Instance instance =
                        manager.start(this, model);
                // 5560+ 是控制台，adb = 控制台 + 1
                final int adbPort = 5560 + Math.max(0, instance.port - 8554) + 1;
                android.util.Log.i("VelaAdbTest", "device=" + deviceId + " model=" + model
                        + " grpc=" + instance.port + " adb=" + adbPort);
                android.util.Log.i("VelaAdbTest", "portReady="
                        + com.typheye.wgpro.core.emu.VelaAdbClient.waitFor(adbPort, 30000L));
                for (String command : new String[]{"echo GUEST_OK", "uname -a", "ls /",
                        "install", "install 2>&1", "vela", "app", "pm", "ps"}) {
                    try {
                        String output = com.typheye.wgpro.core.emu.VelaAdbClient
                                .shell("::1", adbPort, command, 8000L);
                        android.util.Log.i("VelaAdbTest", "$ " + command + "\n" + output);
                    } catch (Throwable error) {
                        android.util.Log.i("VelaAdbTest", "$ " + command + " -> 失败: " + error);
                    }
                }
            } catch (Throwable error) {
                android.util.Log.i("VelaAdbTest", "异常: " + error);
            }
        }, "vela-adb-test").start();
    }

    /** 设备页标题：「共 X 台」（没有设备就是「共 0 台」）。 */
    private String deviceTitle() {
        DeviceFragment devices = DeviceFragment.current();
        // 页面刚切进来时设备卡可能还没挂上（数据异步加载），此时用上次统计到的数量兜底
        int count = devices != null && devices.isAdded()
                ? devices.deviceCount()
                : DeviceFragment.lastKnownDeviceCount(this);
        return "共 " + count + " 台";
    }

    /**
     * 设备页数据是异步加载的，切进来那一瞬可能还是「共 0 台」。
     * 这里错开几帧再校准几次，数据到货后标题就能自己跟上，不用离开再回来。
     */
    private void scheduleDeviceTitleCatchUp() {
        for (long delay : new long[]{250L, 700L, 1500L}) {
            toolbar.postDelayed(() -> {
                if (mainPager != null && mainPager.getCurrentItem() == 1) {
                    setTopBarTitle(deviceTitle());
                }
            }, delay);
        }
    }

    /** 设备数据变化后刷新应用栏标题（由 DeviceFragment 加载结束时回调）。 */
    public void refreshDeviceTitle() {
        if (mainPager != null && mainPager.getCurrentItem() == 1) {
            setTopBarTitle(deviceTitle());
        }
    }

    /**
     * 设置应用栏标题，**文字没变就不重设**。
     *
     * <p>切页时 {@code applyTopBarForPage} 会被调用两次（选中回调 + post 校准），设备数据加载完
     * 也会再来一次；重复 setTitle 会让 Toolbar 重新布局，看起来就是"标题闪一下"。
     * 这里用缓存挡掉同值写入，保证只在真正变化时更新。</p>
     */
    private void setTopBarTitle(String title) {
        String next = title == null ? "" : title;
        if (next.equals(lastTopBarTitle)) return;
        lastTopBarTitle = next;
        toolbar.setTitle(next);
    }

    /** 「写一篇」：选择发布动态或资源。 */
    private void openCompose() {
        if (!requireLogin("发布内容")) return;
        new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(this)
                .setTitle("写一篇").setItems(new CharSequence[]{"分享动态", "发布资源"}, (dialog, which) -> {
                    if (which == 0) {
                        com.typheye.wgpro.ui.function.community.CommunityWeb.openDynamic(this);
                    } else {
                        com.typheye.wgpro.ui.function.community.CommunityWeb.openResource(this);
                    }
                }).show();
    }

    private void openSearch() {
        startActivity(new Intent(this,
                com.typheye.wgpro.ui.function.community.SearchActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        if (notificationBadge != null) {
            BadgeUtils.detachBadgeDrawable(notificationBadge, toolbar, R.id.action_notifications);
        }
        notificationBadge = BadgeDrawable.create(this);
        notificationBadge.setBackgroundColor(getColor(R.color.status_danger));
        notificationBadge.setBadgeTextColor(getColor(R.color.white));
        notificationBadge.setMaxCharacterCount(3);
        notificationBadge.setVisible(false);
        BadgeUtils.attachBadgeDrawable(notificationBadge, toolbar, R.id.action_notifications);
        applyInboxState(AppState.get().inboxSnapshot());
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean home = selectedPage == R.id.nav_home;
        boolean account = selectedPage == R.id.nav_account;
        MenuItem notification = menu.findItem(R.id.action_notifications);
        MenuItem compose = menu.findItem(R.id.action_compose);
        MenuItem settings = menu.findItem(R.id.action_settings);
        MenuItem deviceAdd = menu.findItem(R.id.action_device_add);
        if (notification != null) notification.setVisible(home);
        if (compose != null) compose.setVisible(home);
        // 扫码固定在应用栏左侧（导航位）：账户页**始终**显示，不随登录态隐藏。
        // 未登录时进扫码页也无效 —— 无效判定在 ScanQRActivity 里统一拦截，避免别处入口绕过。
        toolbar.setNavigationIcon(account
                ? androidx.appcompat.content.res.AppCompatResources
                        .getDrawable(this, R.drawable.ic_qr_vector)
                : null);
        toolbar.setNavigationContentDescription(account ? "扫描二维码" : null);
        toolbar.setNavigationOnClickListener(account
                ? v -> startActivity(new Intent(MainActivity.this, ScanQRActivity.class))
                : null);
        if (settings != null) settings.setVisible(account);
        if (deviceAdd != null) deviceAdd.setVisible(selectedPage == R.id.nav_device);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onDestroy() {
        try {
            unbindService(coreConnection);
        } catch (Exception ignored) {
        }
        try {
            unbindService(pushConnection);
        } catch (Exception ignored) {
        }
        super.onDestroy();
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, @NonNull Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        ViewCompat.requestApplyInsets(findViewById(R.id.container));
        mainPager.post(this::applyContentBarInsets);
        if (barBlurController != null) barBlurController.scheduleUpdate();
    }

    /**
     * 给四个页面补上应用栏 / 底部导航高度的内边距：
     * 滚动页直接给滚动容器加；容器页（首页/设备）根视图不加，只给内部列表补上下留白，
     * 让列表从应用栏（含标签页）和底部导航栏的毛玻璃下方穿过。
     */
    private void applyContentBarInsets() {
        int top = findViewById(R.id.app_bar_layout).getHeight();
        int bottom = bottomNavigation.getHeight();
        if (top == 0 || bottom == 0) return;
        String[] tags = {"f0", "f1", "f2", "f3"};
        for (String tag : tags) {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
            if (fragment == null || fragment.getView() == null) continue;
            View root = fragment.getView();
            if (root instanceof NestedScrollView) {
                NestedScrollView scroll = (NestedScrollView) root;
                scroll.setClipToPadding(false);
                scroll.setPadding(scroll.getPaddingLeft(), top, scroll.getPaddingRight(), bottom);
            } else {
                root.setPadding(root.getPaddingLeft(), 0, root.getPaddingRight(), 0);
                applyInnerScrollInsets(root, top, bottom);
            }
        }
    }

    /** 容器页里的列表：上下留白加在滚动容器上，内容可从毛玻璃栏下方穿过。 */
    private void applyInnerScrollInsets(View view, int top, int bottom) {
        if (view instanceof SwipeRefreshLayout) {
            // 指示器压到应用栏下方，否则下拉时转圈图标被应用栏盖住、看起来像没触发
            AppBarBlur.offsetRefreshIndicator((SwipeRefreshLayout) view, top);
        }
        if (view instanceof NestedScrollView) {
            NestedScrollView scroll = (NestedScrollView) view;
            scroll.setClipToPadding(false);
            AppBarBlur.padScrollableBottom(scroll, bottom);
            AppBarBlur.padScrollableTop(scroll, top);
            return;
        }
        if (view instanceof androidx.viewpager2.widget.ViewPager2) {
            // ViewPager2 是 RecyclerView 的子类，但它自己**不是**内容滚动容器：
            // 给它加 padding 会把内部整页往下挤，内容永远穿不到应用栏/导航栏后面，
            // 快照里栏覆盖的区域只剩纯色背景 —— 表现就是"毛玻璃消失了"。
            // 要补的是「每一页里面的第一个滚动容器」，与 AppBarBlur.padFirstScrollable 一致。
            androidx.viewpager2.widget.ViewPager2 pager =
                    (androidx.viewpager2.widget.ViewPager2) view;
            for (int index = 0; index < pager.getChildCount(); index++) {
                View child = pager.getChildAt(index);
                if (!(child instanceof ViewGroup)) continue;
                ViewGroup holder = (ViewGroup) child;   // ViewPager2 的内部 RecyclerView
                for (int page = 0; page < holder.getChildCount(); page++) {
                    applyInnerScrollInsets(holder.getChildAt(page), top, bottom);
                }
            }
            return;
        }
        if (view instanceof androidx.recyclerview.widget.RecyclerView) {
            androidx.recyclerview.widget.RecyclerView recycler =
                    (androidx.recyclerview.widget.RecyclerView) view;
            recycler.setClipToPadding(false);
            AppBarBlur.padScrollableBottom(recycler, bottom);
            AppBarBlur.padScrollableTop(recycler, top);
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int index = 0; index < group.getChildCount(); index++) {
                applyInnerScrollInsets(group.getChildAt(index), top, bottom);
            }
        }
    }

    /** 把当前页面的子标签挂到应用栏（首页：动态/应用/资源；设备：穿戴/其他设备）。 */
    private void bindPageTabs(int position) {
        ViewPager2 innerPager = null;
        String[] titles = null;
        if (position == 0) {
            Fragment home = fragmentAt(0);
            if (home != null && home.getView() != null) {
                innerPager = home.getView().findViewById(R.id.home_pager);
                titles = HOME_TAB_TITLES;
            }
        } else if (position == 1) {
            // 去掉「发现」页后设备页是 1（原来写的是 2，漏改会让设备页的子标签行绑不上而消失）
            Fragment device = fragmentAt(1);
            if (device != null && device.getView() != null) {
                innerPager = device.getView().findViewById(R.id.device_pager);
                titles = DEVICE_TAB_TITLES;
            }
        }
        if (innerPager == null || titles == null || innerPager.getAdapter() == null) {
            if (pageTabsMediator != null) {
                pageTabsMediator.detach();
                pageTabsMediator = null;
                boundTabsPager = null;
            }
            pageTabs.setVisibility(View.GONE);
            findViewById(R.id.app_bar_layout).post(this::applyContentBarInsets);
            return;
        }
        if (pageTabsMediator != null && boundTabsPager == innerPager) return;
        if (pageTabsMediator != null) pageTabsMediator.detach();
        final String[] tabTitles = titles;
        pageTabs.setVisibility(View.VISIBLE);
        pageTabsMediator = new TabLayoutMediator(pageTabs, innerPager,
                (tab, tabPosition) -> tab.setText(tabTitles[tabPosition]));
        pageTabsMediator.attach();
        boundTabsPager = innerPager;
        pageTabs.post(this::applyContentBarInsets);
    }

    /**
     * 取指定位置的主页 Fragment。Activity 重建（深浅色切换/旋转）后
     * FragmentStateAdapter 恢复的 Fragment 不会再走 createFragment，
     * 必须按 tag 查找，否则子标签行会一直绑不上而消失。
     */
    @Nullable
    private Fragment fragmentAt(int position) {
        Fragment restored = getSupportFragmentManager().findFragmentByTag("f" + position);
        if (restored != null) return restored;
        if (position == 0) return homeFragment;
        // 去掉「发现」页后：0=首页 1=设备 2=账户（原来设备是 2，此处漏改会让设备页的子标签行绑不上而消失）
        if (position == 1) return deviceFragment;
        return null;
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_notifications) {
            if (!requireLogin("查看通知")) return true;
            startActivity(new Intent(this,
                    com.typheye.wgpro.ui.function.community.NotificationActivity.class));
            return true;
        } else if (id == R.id.action_compose) {
            openCompose();
            return true;
        } else if (id == R.id.action_device_add) {
            // 「模拟设备」页右上角 + → 走创建设备（模拟设备）引导；其余出口仍是添加设备页
            com.typheye.wgpro.ui.main.mainFragments.DeviceFragment devices =
                    com.typheye.wgpro.ui.main.mainFragments.DeviceFragment.current();
            boolean virtualPage = devices != null && devices.isAdded() && devices.isVirtualPage();
            // 「模拟设备」页右上角 + → 创建设备；其余出口仍是添加设备
            // 统一入口：同一个 AddDeviceActivity，按 mode 挂不同 Fragment
            startActivity(new Intent(this,
                    com.typheye.wgpro.ui.function.device.AddDeviceActivity.class)
                    .putExtra(com.typheye.wgpro.ui.function.device.AddDeviceActivity.EXTRA_MODE,
                            virtualPage
                                    ? com.typheye.wgpro.ui.function.device.AddDeviceActivity.MODE_VIRTUAL
                                    : com.typheye.wgpro.ui.function.device.AddDeviceActivity.MODE_WEARABLE));
            return true;
        }
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
