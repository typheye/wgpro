package com.typheye.wgpro.ui.function.settings;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Toast;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.core.view.ViewCompat;
import androidx.preference.PreferenceManager;

import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProBottomSheetDialog;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.SplashActivity;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.ui.function.community.CloudListFragment;
import com.typheye.wgpro.ui.function.community.ContactActivity;
import com.typheye.wgpro.ui.function.debug.TestActivity;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.utils.BarBlurController;
import com.typheye.wgpro.utils.SystemBars;
import com.typheye.wgpro.utils.tAccUtils;
import com.typheye.wgpro.debug.TestHandler;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SettingsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private OnBackPressedCallback compatibilityBackCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.useScreenCutArea(getWindow(),this);
        setContentView(R.layout.activity_settings);
        // 固定应用栏改毛玻璃（设置内容滚动时从应用栏下方穿过）；
        // 底部安全区交给页面里的滚动视图预留。
        com.typheye.wgpro.utils.AppBarBlur.install(this, findViewById(R.id.app_bar_layout), findViewById(R.id.settings));
        AppUtils.applyScrollBottomInsets(getSupportFragmentManager(), R.id.settings);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            toolbar.setTitle(getSupportFragmentManager().getBackStackEntryCount() > 0 ? "关于应用" : "设置");
            invalidateOptionsMenu();
        });
        compatibilityBackCallback = new OnBackPressedCallback(true) {
            @Override public void handleOnBackPressed() {
                navigateBack();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, compatibilityBackCallback);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem share = menu.findItem(R.id.action_share_app);
        // 分享入口只在「关于应用」页显示
        if (share != null) {
            share.setVisible(getSupportFragmentManager().getBackStackEntryCount() > 0);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            navigateBack();
            return true;
        }
        if (id == R.id.action_share_app) {
            shareApp();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void shareApp() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "腕管Pro");
        intent.putExtra(Intent.EXTRA_TEXT, "腕管Pro\nhttps://wgpro.typheye.cn");
        startActivity(Intent.createChooser(intent, "分享应用"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        compatibilityBackCallback.setEnabled(!PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("predictive_back_enabled", false));
    }

    private void navigateBack() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    private void setPredictiveBackEnabled(boolean enabled) {
        compatibilityBackCallback.setEnabled(!enabled);
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, @NonNull Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        ViewCompat.requestApplyInsets(findViewById(R.id.container));
    }

    private void openAboutPage() {
        AppUtils.applyFragmentTransition(getSupportFragmentManager().beginTransaction())
                .replace(R.id.settings, new AboutFragment())
                .addToBackStack("about")
                .commit();
    }

    public static class SettingsFragment extends Fragment {
        private final Handler mainHandler = new Handler(Looper.getMainLooper());
        private final ExecutorService cacheExecutor = Executors.newSingleThreadExecutor();
        private tAccUtils accUtils;
        private View accountLabel;
        private View logoutCard;

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_settings_md3, container, false);
            accUtils = new tAccUtils(requireContext());
            accountLabel = view.findViewById(R.id.label_account);
            logoutCard = view.findViewById(R.id.card_logout);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(requireContext());
            MaterialSwitch followSystem = view.findViewById(R.id.switch_follow_system_theme);
            MaterialSwitch darkMode = view.findViewById(R.id.switch_dark_mode);
            MaterialSwitch autoCheck = view.findViewById(R.id.switch_auto_check);
            MaterialSwitch predictiveBack = view.findViewById(R.id.switch_predictive_back);
            View darkModeRow = view.findViewById(R.id.row_dark_mode);

            boolean followsSystemTheme = preferences.getBoolean("theme_follow_system", true);
            followSystem.setChecked(followsSystemTheme);
            darkMode.setChecked(preferences.getBoolean("theme_dark_mode", false));
            darkModeRow.setVisibility(followsSystemTheme ? View.GONE : View.VISIBLE);

            boolean predictiveBackAvailable = Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE;
            if (!predictiveBackAvailable) {
                preferences.edit().putBoolean("predictive_back_enabled", false).apply();
                view.findViewById(R.id.row_predictive_back).setAlpha(0.55f);
                ((TextView) view.findViewById(R.id.text_predictive_back_summary))
                        .setText("需要 Android 14 或更高版本");
            }
            predictiveBack.setEnabled(predictiveBackAvailable);
            predictiveBack.setChecked(predictiveBackAvailable
                    && preferences.getBoolean("predictive_back_enabled", false));
            autoCheck.setChecked(preferences.getBoolean("appUpdate_autoCheck", true));

            MaterialSwitch blur = view.findViewById(R.id.switch_blur);
            blur.setChecked(BarBlurController.isBlurEnabled(requireContext()));
            blur.setOnCheckedChangeListener((button, checked) ->
                    BarBlurController.setBlurEnabled(requireContext(), checked));
            view.findViewById(R.id.row_blur).setOnClickListener(v -> blur.toggle());

            followSystem.setOnCheckedChangeListener((button, checked) -> {
                preferences.edit().putBoolean("theme_follow_system", checked).apply();
                darkModeRow.setVisibility(checked ? View.GONE : View.VISIBLE);
                AppCompatDelegate.setDefaultNightMode(checked
                        ? AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                        : darkMode.isChecked() ? AppCompatDelegate.MODE_NIGHT_YES
                        : AppCompatDelegate.MODE_NIGHT_NO);
            });
            darkMode.setOnCheckedChangeListener((button, checked) -> {
                preferences.edit().putBoolean("theme_dark_mode", checked).apply();
                if (!followSystem.isChecked()) {
                    AppCompatDelegate.setDefaultNightMode(checked
                            ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                }
            });
            view.findViewById(R.id.row_follow_system_theme)
                    .setOnClickListener(v -> followSystem.toggle());
            darkModeRow.setOnClickListener(v -> darkMode.toggle());

            autoCheck.setOnCheckedChangeListener((button, checked) -> {
                preferences.edit().putBoolean("appUpdate_autoCheck", checked).apply();
            });
            predictiveBack.setOnCheckedChangeListener((button, checked) -> {
                preferences.edit().putBoolean("predictive_back_enabled", checked).apply();
                ((SettingsActivity) requireActivity()).setPredictiveBackEnabled(checked);
            });

            view.findViewById(R.id.row_reset).setOnClickListener(v -> appReset());
            view.findViewById(R.id.row_clear_cache).setOnClickListener(v -> showClearCacheSheet());
            view.findViewById(R.id.row_notification_settings)
                    .setOnClickListener(v -> showNotificationSettingsSheet());
            view.findViewById(R.id.row_about).setOnClickListener(v ->
                    ((SettingsActivity) requireActivity()).openAboutPage());
            view.findViewById(R.id.row_blocked_users).setOnClickListener(v ->
                    startActivity(new Intent(requireContext(), ContactActivity.class)
                            .putExtra(ContactActivity.EXTRA_MODE, CloudListFragment.MODE_BLOCKED)));
            View logoutRow = view.findViewById(R.id.row_logout);
            logoutRow.setOnClickListener(v -> showLogoutSheet(logoutRow));
            updateLogoutVisibility();
            return view;
        }

        private void showLogoutSheet(View logoutRow) {
            new WGProAlertDialogBuilder(requireContext())
                    .setTitle("退出登录")
                    .setMessage("确定要退出登录吗？此操作将清除当前设备上的本地账户数据。")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("退出", (dialog, which) -> {
                logoutRow.setEnabled(false);
                accUtils.logoutCurrentSession(() -> mainHandler.post(() -> { if (!isAdded()) return; logoutRow.setEnabled(true); updateLogoutVisibility(); Toast.makeText(requireContext(), "已退出登录", Toast.LENGTH_SHORT).show(); }));
            }).show();
        }

        @Override
        public void onResume() {
            super.onResume();
            if (accUtils != null) updateLogoutVisibility();
        }

        private void updateLogoutVisibility() {
            int visibility = accUtils.isLogin() ? View.VISIBLE : View.GONE;
            accountLabel.setVisibility(visibility);
            logoutCard.setVisibility(visibility);
        }

        private void openWeb(String url) {
            Intent intent = new Intent(requireActivity(), WebActivity.class);
            intent.putExtra("URL", url);
            startActivity(intent);
        }

        private void appReset() {
            new WGProAlertDialogBuilder(requireContext())
                    .setTitle("提示")
                    .setMessage("您确定要重新进入引导页吗？")
                    .setPositiveButton("确定", (dialog, which) -> {
                        AppUtils.appUninit(requireContext());

                        Intent intent = new Intent(requireContext(), SplashActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                        if (getActivity() != null) {
                            getActivity().finishAffinity();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .show();
        }

        private void showClearCacheSheet() {
            new WGProAlertDialogBuilder(requireContext())
                    .setTitle("清除缓存")
                    .setMessage("将清理内置浏览器缓存和应用临时文件。登录状态、设备、设置与下载内容不会受到影响。")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("清除", (dialog, which) -> {
                        if (dialog instanceof WGProBottomSheetDialog) {
                            ((WGProBottomSheetDialog) dialog).dismissForReplacement();
                        }
                        mainHandler.postDelayed(this::showCacheProgress, 40L);
                    })
                    .show();
        }

        /**
         * 通知设置入口。
         *
         * 渠道重要性与"横幅"开关由系统控制，应用只能检测并引导用户去系统页面开启；
         * 后台运行通知同样可以在系统页面里关掉而服务继续运行。
         */
        private void showNotificationSettingsSheet() {
            boolean alerting = com.typheye.wgpro.utils.InboxNotificationHelper
                    .isMessageChannelAlerting(requireContext());
            CharSequence[] items = {
                    alerting ? "消息横幅与提醒（已开启）" : "消息横幅与提醒（未开启）",
                    "账户通知",
                    "后台常驻通知",
            };
            new WGProAlertDialogBuilder(requireContext()).setTitle("通知设置")
                    .setItems(items, (dialog, which) -> {
                        try {
                            if (which == 0) {
                                startActivity(com.typheye.wgpro.utils.InboxNotificationHelper
                                        .channelSettingsIntent(requireContext(),
                                                com.typheye.wgpro.utils.InboxNotificationHelper
                                                        .messageChannelId()));
                            } else if (which == 1) {
                                startActivity(com.typheye.wgpro.utils.InboxNotificationHelper
                                        .channelSettingsIntent(requireContext(),
                                                com.typheye.wgpro.utils.InboxNotificationHelper
                                                        .CHANNEL_ACCOUNT));
                            } else {
                                // MIUI 等系统的「常驻通知」总开关在这个页面里
                                startActivity(com.typheye.wgpro.utils.InboxNotificationHelper
                                        .appNotificationSettingsIntent(requireContext()));
                            }
                        } catch (Exception error) {
                            try {
                                startActivity(com.typheye.wgpro.utils.InboxNotificationHelper
                                        .appNotificationSettingsIntent(requireContext()));
                            } catch (Exception ignored) {
                            }
                        }
                    }).show();
        }

        private void legacyShowClearCacheSheet() {
            new WGProAlertDialogBuilder(requireContext())
                    .setTitle("清除缓存")
                    .setMessage("将清理内置浏览器缓存和应用临时文件。登录状态、设备、设置与下载内容不会受到影响。")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("清除", (dialog, which) -> {
                        if (dialog instanceof WGProBottomSheetDialog) {
                            ((WGProBottomSheetDialog) dialog).dismissForReplacement();
                        }
                        mainHandler.postDelayed(this::showCacheProgress, 40L);
                    })
                    .show();
        }

        private void showCacheProgress() {
            if (!isAdded()) return;
            java.util.concurrent.atomic.AtomicBoolean cancelled = new java.util.concurrent.atomic.AtomicBoolean(false);
            java.util.concurrent.Future<?>[] task = new java.util.concurrent.Future<?>[1];
            View progressView = View.inflate(requireContext(), R.layout.progress_dialog, null);
            ((TextView) progressView.findViewById(android.R.id.message)).setText("正在清理缓存...");
            WGProBottomSheetDialog progressDialog = new WGProAlertDialogBuilder(requireContext())
                    .setTitle("清理中")
                    .setView(progressView)
                    .setCancelable(false)
                    .setNegativeButton("取消", (dialog, which) -> {
                        cancelled.set(true);
                        if (task[0] != null) task[0].cancel(true);
                    })
                    .create();
            progressDialog.show();

            // Let the sheet finish its entrance before initializing the temporary WebView.
            mainHandler.postDelayed(() -> {
                if (!isAdded()) return;
                WebView temporaryWebView = new WebView(requireContext().getApplicationContext());
                temporaryWebView.clearCache(true);
                temporaryWebView.clearFormData();
                temporaryWebView.clearHistory();
                temporaryWebView.destroy();

                File internalCache = requireContext().getCacheDir();
                File externalCache = requireContext().getExternalCacheDir();
                task[0] = cacheExecutor.submit(() -> {
                    boolean success = clearDirectoryContents(internalCache)
                            & clearDirectoryContents(externalCache);
                    mainHandler.post(() -> {
                        if (!isAdded() || cancelled.get()) return;
                        progressDialog.dismissForReplacement();
                        new WGProAlertDialogBuilder(requireContext())
                                .setTitle(success ? "缓存已清除" : "清理未完全完成")
                                .setMessage(success
                                        ? "浏览器缓存和应用临时文件已清理。"
                                        : "部分正在使用的临时文件无法删除，可稍后重试。")
                                .setPositiveButton("完成", null)
                                .show();
                    });
                });
            }, 180L);
        }

        private boolean clearDirectoryContents(File directory) {
            if (directory == null || !directory.exists()) return true;
            File[] children = directory.listFiles();
            if (children == null) return false;
            boolean success = true;
            for (File child : children) success &= deleteRecursively(child);
            return success;
        }

        private boolean deleteRecursively(File file) {
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                if (children == null) return false;
                boolean success = true;
                for (File child : children) success &= deleteRecursively(child);
                if (!success) return false;
            }
            return file.delete() || !file.exists();
        }

        @Override
        public void onDestroy() {
            cacheExecutor.shutdownNow();
            super.onDestroy();
        }
        private void appAbout() {
            // 获取应用版本信息
            String versionName = "";
            int versionCode = 0;

            try {
                PackageInfo packageInfo = requireContext().getPackageManager()
                        .getPackageInfo(requireContext().getPackageName(), 0);
                versionName = packageInfo.versionName;

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                    versionCode = (int) packageInfo.getLongVersionCode();
                } else {
                    versionCode = packageInfo.versionCode;
                }
            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
            }

            // 获取应用大小
            String appSize = getAppSize();

            // 构建应用信息
            @SuppressLint("DefaultLocale") String appInfo = String.format(
                            "应用包名：%s\n" +
                            "版本名称：%s\n" +
                            "版本号码：%d\n" +
                            "应用大小：%s\n" +
                            "编译SDK：%d\n" +
                            "最低SDK：%d\n" +
                            "安装来源：%s\n" +
                            "安装时间：%s\n" +
                            "更新时间：%s\n\n" +
                            "设备品牌：%s\n" +
                            "设备型号：%s\n" +
                            "系统版本：Android %s (API %d)",
                    requireContext().getPackageName(),
                    versionName,
                    versionCode,
                    appSize,
                    android.os.Build.VERSION.SDK_INT,
                    getMinSdkVersion(),
                    getInstallSource(),
                    getFormattedDate(getInstallTime()),
                    getFormattedDate(getLastUpdateTime()),
                    android.os.Build.BRAND,
                    android.os.Build.MODEL,
                    android.os.Build.VERSION.RELEASE,
                    android.os.Build.VERSION.SDK_INT
            );

            new WGProAlertDialogBuilder(requireContext())
                    .setTitle("关于 腕管Pro")
                    .setMessage(appInfo)
                    .setPositiveButton("关闭", (dialog, which) -> dialog.dismiss())
                    .setNegativeButton("开源", (dialog, which) ->{
                        Intent intent = new Intent(requireActivity(), WebActivity.class);
                        intent.putExtra("URL", "https://github.com/typheye/wgpro");
                        startActivity(intent);
                    })
                    .setNeutralButton("分享", (dialog, which) -> shareAppInfo())
                    .show();
        }

        /**
         * 获取应用大小
         */
        private String getAppSize() {
            try {
                ApplicationInfo appInfo = requireContext().getPackageManager()
                        .getApplicationInfo(requireContext().getPackageName(), 0);
                File file = new File(appInfo.sourceDir);
                long size = file.length();
                return formatFileSize(size);
            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
            }
            return "未知";
        }

        /**
         * 格式化文件大小
         */
        private String formatFileSize(long size) {
            if (size <= 0) return "0 B";

            final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
            int digitGroups = (int) (Math.log10(size) / Math.log10(1024));

            return new DecimalFormat("#,##0.##").format(size / Math.pow(1024, digitGroups))
                    + " " + units[digitGroups];
        }

        /**
         * 获取最低SDK版本
         */
        private int getMinSdkVersion() {
            try {
                ApplicationInfo appInfo = requireContext().getPackageManager()
                        .getApplicationInfo(requireContext().getPackageName(), 0);
                return appInfo.minSdkVersion;
            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
            }
            return android.os.Build.VERSION_CODES.LOLLIPOP;
        }

        /**
         * 获取安装来源
         */
        private String getInstallSource() {
            try {
                PackageManager pm = requireContext().getPackageManager();
                String installer = pm.getInstallerPackageName(requireContext().getPackageName());
                if (installer != null) {
                    return installer;
                }
            } catch (Exception e) {
//                e.printStackTrace();
            }
            return "未知来源";
        }

        /**
         * 获取安装时间
         */
        private long getInstallTime() {
            try {
                PackageInfo packageInfo = requireContext().getPackageManager()
                        .getPackageInfo(requireContext().getPackageName(), 0);
                return packageInfo.firstInstallTime;
            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
            }
            return 0;
        }

        /**
         * 获取最后更新时间
         */
        private long getLastUpdateTime() {
            try {
                PackageInfo packageInfo = requireContext().getPackageManager()
                        .getPackageInfo(requireContext().getPackageName(), 0);
                return packageInfo.lastUpdateTime;
            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
            }
            return 0;
        }

        /**
         * 格式化日期
         */
        private String getFormattedDate(long timestamp) {
            if (timestamp <= 0) return "未知";

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
            return sdf.format(new Date(timestamp));
        }

        /**
         * 分享应用信息
         */
        private void shareAppInfo() {
            String shareContent = "我正在使用腕管Pro，推荐给你！\n" +
                            "下载链接：https://wgpro.typheye.cn";

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "腕管Pro 应用推荐");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareContent);
            startActivity(Intent.createChooser(shareIntent, "分享到"));
        }

    }

    public static class AboutFragment extends Fragment {
        private int developerTapCount;
        private long lastDeveloperTapAt;
        private TextView updateText;
        private boolean updateAvailable;
        private String updateUrl = "";
        private String latestVersionName = "";
        private String currentVersionName = "未知";

        /** 开源项目：{名称, 作者/组织, 链接}（当前工程实际依赖、且叫得上名的项目）。 */
        private static final String[][] OPEN_SOURCE = {
                {"AndroidX（Jetpack）", "Google", "https://developer.android.com/jetpack/androidx"},
                {"Material Components for Android", "Google", "https://github.com/material-components/material-components-android"},
                {"OkHttp", "Square", "https://github.com/square/okhttp"},
                {"Gson", "Google", "https://github.com/google/gson"},
                {"ZXing", "ZXing Project", "https://github.com/zxing/zxing"},
                {"ZXing Android Embedded", "journeyapps", "https://github.com/journeyapps/ZXing-android-embedded"},
                {"ConstraintLayout", "AndroidX", "https://github.com/androidx/constraintlayout"},
        };

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_about, container, false);
            bindVersionInfo(view);
            updateText = view.findViewById(R.id.about_update_text);
            view.findViewById(R.id.about_update_card).setOnClickListener(v -> onUpdateCardClick());
            View appIcon = view.findViewById(R.id.about_app_icon);
            appIcon.setOnClickListener(v -> onDeveloperTap());
            // 长按应用图标直接进入调试页（是否记录诊断信息仍由调试页的开关决定）
            appIcon.setOnLongClickListener(v -> {
                startActivity(new Intent(requireContext(), TestActivity.class));
                return true;
            });
            view.findViewById(R.id.about_row_feedback)
                    .setOnClickListener(v -> showContactSheet());
            view.findViewById(R.id.about_row_homepage)
                    .setOnClickListener(v -> openWeb("https://github.com/typheye/wgpro"));
            view.findViewById(R.id.about_row_policies)
                    .setOnClickListener(v -> openWeb("https://www.typheye.cn/policies/"));
            view.findViewById(R.id.about_row_license)
                    .setOnClickListener(v -> openWeb("https://www.typheye.cn/licenses/gpl-3.0.html"));
            buildOpenSourceList(view);
            applyAppBarPadding(view);
            return view;
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            // getView() 在 onCreateView 内还是 null，更新状态必须等视图挂载后再查。
            refreshUpdateStatus();
        }

        private void bindVersionInfo(View view) {
            String versionName = "未知";
            long versionCode = 0;
            try {
                PackageInfo info = requireContext().getPackageManager()
                        .getPackageInfo(requireContext().getPackageName(), 0);
                versionName = info.versionName == null ? "未知" : info.versionName;
                versionCode = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P
                        ? info.getLongVersionCode() : info.versionCode;
            } catch (PackageManager.NameNotFoundException ignored) {
            }
            ((TextView) view.findViewById(R.id.about_version)).setText("版本 " + versionName);
            currentVersionName = versionName;
            ((TextView) view.findViewById(R.id.about_version_code)).setText(String.valueOf(versionCode));
            ((TextView) view.findViewById(R.id.about_android)).setText(
                    String.valueOf(requireContext().getApplicationInfo().targetSdkVersion));
            ((TextView) view.findViewById(R.id.about_channel)).setText(appLevel(versionName));
        }

        /** 从版本名解析发布级别：Alpha / Beta / RC / Release。 */
        private static String appLevel(String versionName) {
            if (versionName == null) return "Release";
            String value = versionName.toLowerCase(java.util.Locale.ROOT);
            if (value.contains("alpha")) return "Alpha";
            if (value.contains("beta")) return "Beta";
            if (value.contains("rc")) return "RC";
            return "Release";
        }

        /**
         * 查询最新版本并刷新「更新状态」单行卡片。
         *
         * <p>只呈现两种结果：`已是最新版本`（主题色底）、`有新版本待更新`（浅绿底）。
         * 检查中 / 网络失败都不显示额外文案；失败按「已是最新」处理，避免打扰。
         */
        private void refreshUpdateStatus() {
            View card = getView() == null ? null : getView().findViewById(R.id.about_update_card);
            if (updateText == null || card == null) return;
            // XML 默认就是「已是最新版本」+ colorSecondaryContainer 底色，不隐藏卡片。
            // 调试页「模拟有更新」开关优先级最高
            if (TestHandler.isUpdateSimulationEnabled(requireContext())) {
                applyUpdateState("", "");
                return;
            }
            AppUtils.checkUpdateStatus(requireContext(),
                    (hasUpdate, latestName, latestCode, url, error) -> {
                        if (!isAdded() || updateText == null) return;
                        if (error != null || !hasUpdate) {
                            applyLatestState();
                        } else {
                            applyUpdateState(latestName, url == null ? "" : url);
                        }
                    });
        }

        private void applyLatestState() {
            updateAvailable = false;
            updateUrl = "";
            latestVersionName = "";
            // 用「非焦点按钮」的配色（colorSecondaryContainer），而不是主按钮的 colorPrimary
            applyChipStyle(
                    themeColor("colorSecondaryContainer", R.color.surface_secondary),
                    themeColor("colorOnSecondaryContainer", R.color.text_primary),
                    "已是最新版本");
        }

        private void applyUpdateState(String latestName, String url) {
            updateAvailable = true;
            updateUrl = url == null ? "" : url;
            latestVersionName = latestName == null ? "" : latestName.trim();
            applyChipStyle(requireContext().getColor(R.color.status_success_soft),
                    requireContext().getColor(R.color.status_success),
                    "有新版本待更新");
        }

        private void onUpdateCardClick() {
            if (updateAvailable) {
                showUpdateSheet();
            } else {
                showLatestSheet();
            }
        }

        private void showLatestSheet() {
            new WGProAlertDialogBuilder(requireContext())
                    .setTitle("已是最新版本")
                    .setMessage("当前版本 " + currentVersionName + " 已是最新，无需更新。")
                    .setPositiveButton("知道了", null)
                    .show();
        }

        private void showUpdateSheet() {
            String message = latestVersionName.isEmpty()
                    ? "检测到有新版本可用，是否前往更新？"
                    : "最新版本 " + latestVersionName + "，是否前往更新？";
            new WGProAlertDialogBuilder(requireContext())
                    .setTitle("发现新版本")
                    .setMessage(message)
                    .setNegativeButton("稍后", null)
                    .setPositiveButton("立即更新", (dialog, which) -> openWeb(
                            updateUrl.isEmpty()
                                    ? "https://www.typheye.cn/share/app/?uri=com.typheye.wgpro"
                                    : updateUrl))
                    .show();
        }

        /** 单行状态卡：只在文字/颜色真的变化时才改视图，避免检查更新回来时无意义地重排/重绘。 */
        private void applyChipStyle(int background, int textColor, String text) {
            View view = getView();
            if (view == null || updateText == null) return;
            if (!text.contentEquals(updateText.getText())) updateText.setText(text);
            if (updateText.getCurrentTextColor() != textColor) updateText.setTextColor(textColor);
            View card = view.findViewById(R.id.about_update_card);
            if (card instanceof com.google.android.material.card.MaterialCardView) {
                com.google.android.material.card.MaterialCardView material =
                        (com.google.android.material.card.MaterialCardView) card;
                if (material.getCardBackgroundColor().getDefaultColor() != background) {
                    material.setCardBackgroundColor(background);
                }
                if (card.getVisibility() != View.VISIBLE) card.setVisibility(View.VISIBLE);
            }
        }

        private int themeColor(String attr, int fallback) {
            return com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder
                    .resolveThemeColor(requireContext(), attr, fallback);
        }

        /**
         * 关于页是独立的可滚动页，顶部留出应用栏高度，滚动时内容从毛玻璃下方穿过。
         * 设置页的 {@code AppBarBlur} 已经会补一次，这里再幂等补一次，
         * 保证 Fragment 刚被替换进来、父容器尚未重新布局时也不会贴到应用栏下面。
         */
        private void applyAppBarPadding(View root) {
            View appBar = requireActivity().findViewById(R.id.app_bar_layout);
            if (appBar == null) return;
            int height = appBar.getHeight();
            if (height > 0) {
                // 应用栏此时已经布局完成，直接同步补留白，避免首帧先贴顶、下一帧再下移的“跳一下”。
                com.typheye.wgpro.utils.AppBarBlur.padScrollableTop(root, height);
                return;
            }
            appBar.post(() -> {
                int h = appBar.getHeight();
                if (h > 0) com.typheye.wgpro.utils.AppBarBlur.padScrollableTop(root, h);
            });
        }

        private void onDeveloperTap() {
            if (TestHandler.isEnabled(requireContext())) return;
            long now = android.os.SystemClock.uptimeMillis();
            if (now - lastDeveloperTapAt > 2_000L) developerTapCount = 0;
            lastDeveloperTapAt = now;
            developerTapCount++;
            int remaining = 7 - developerTapCount;
            if (remaining <= 0) {
                developerTapCount = 0;
                showDeveloperConfirm();
            }
        }

        private void showDeveloperConfirm() {
            new WGProAlertDialogBuilder(requireContext())
                    .setTitle("启用开发模式")
                    .setMessage("启用后可在设置的高级组中进入调试，并记录 API 调用与诊断信息。")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("开启", (dialog, which) -> {
                        TestHandler.setEnabled(requireContext(), true);
                        requireActivity().getSupportFragmentManager().popBackStack();
                    }).show();
        }

        /** 复制 QQ / QQ 群号到剪贴板（这些没有稳定的可打开链接）。 */
        private void copyToClipboard(String label, String value) {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager)
                    requireContext().getSystemService(android.content.Context.CLIPBOARD_SERVICE);
            if (clipboard != null) {
                clipboard.setPrimaryClip(android.content.ClipData.newPlainText(label, value));
            }
            Toast.makeText(requireContext(), label + " " + value + " 已复制",
                    Toast.LENGTH_SHORT).show();
        }

        private void sendEmail() {
            try {
                Intent mail = new Intent(Intent.ACTION_SENDTO,
                        android.net.Uri.parse("mailto:typheye@typheye.com"));
                mail.putExtra(Intent.EXTRA_SUBJECT, "腕管Pro 反馈");
                startActivity(mail);
            } catch (Exception error) {
                copyToClipboard("邮箱", "typheye@typheye.com");
            }
        }

        /** 「联系与反馈」：分组弹窗列出 QQ、QQ 群、邮箱。 */
        private void showContactSheet() {
            CharSequence[] items = {
                    "通过 QQ 联系我们",
                    "通过 Email 联系我们",
            };
            new WGProAlertDialogBuilder(requireContext())
                    .setTitle("联系与反馈")
                    .setItems(items, (dialog, which) -> {
                        if (which == 0) copyToClipboard("QQ 号", "498978473");
                        else sendEmail();
                    })
                    .show();
        }

        /** 开源项目列表：左侧超链接图标 + 名称 + By 作者/组织，点击打开链接。 */
        private void buildOpenSourceList(View view) {
            android.widget.LinearLayout list = view.findViewById(R.id.about_opensource_list);
            if (list == null) return;
            list.removeAllViews();
            for (int i = 0; i < OPEN_SOURCE.length; i++) {
                String[] item = OPEN_SOURCE[i];
                addAboutListRow(list, item[0], "By " + item[1], item[2],
                        i < OPEN_SOURCE.length - 1);
            }
        }

        private void addAboutListRow(android.widget.LinearLayout parent, String title,
                                     String subtitle, String url, boolean showDivider) {
            View row = getLayoutInflater().inflate(R.layout.item_about_row, parent, false);
            ((TextView) row.findViewById(R.id.about_row_title)).setText(title);
            TextView sub = row.findViewById(R.id.about_row_subtitle);
            if (subtitle == null || subtitle.isEmpty()) {
                sub.setVisibility(View.GONE);
            } else {
                sub.setText(subtitle);
            }
            boolean clickable = url != null
                    && (url.startsWith("http://") || url.startsWith("https://"));
            if (clickable) {
                row.setOnClickListener(v -> openWeb(url));
            } else {
                row.setClickable(false);
                row.setFocusable(false);
                row.findViewById(R.id.about_row_chevron).setVisibility(View.INVISIBLE);
            }
            parent.addView(row);
            if (showDivider) {
                View divider = new View(requireContext());
                android.widget.LinearLayout.LayoutParams params =
                        new android.widget.LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT, dp(1));
                params.setMarginStart(dp(20));
                divider.setLayoutParams(params);
                divider.setBackgroundColor(requireContext().getColor(R.color.outline_soft));
                parent.addView(divider);
            }
        }

        private int dp(int value) {
            return Math.round(value * getResources().getDisplayMetrics().density);
        }

        private void openWeb(String url) {
            Intent intent = new Intent(requireContext(), WebActivity.class);
            intent.putExtra("URL", url);
            startActivity(intent);
        }
    }
}
