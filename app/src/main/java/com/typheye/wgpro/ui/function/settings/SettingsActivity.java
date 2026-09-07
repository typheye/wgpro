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
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.core.view.ViewCompat;
import androidx.preference.PreferenceManager;

import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.SplashActivity;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.utils.tAccUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private OnBackPressedCallback compatibilityBackCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.useScreenCutArea(getWindow(),this);
        setContentView(R.layout.activity_settings);
        AppUtils.applyMainWindowInsets(findViewById(R.id.app_bar_layout), findViewById(R.id.settings));

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportFragmentManager().addOnBackStackChangedListener(() ->
                toolbar.setTitle(getSupportFragmentManager().getBackStackEntryCount() > 0 ? "关于应用" : "设置"));
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            navigateBack();
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        ViewCompat.requestApplyInsets(findViewById(R.id.app_bar_layout));
        ViewCompat.requestApplyInsets(findViewById(R.id.settings));
    }

    private void openAboutPage() {
        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .setCustomAnimations(R.animator.fragment_enter, R.animator.fragment_exit,
                        R.animator.fragment_pop_enter, R.animator.fragment_pop_exit)
                .replace(R.id.settings, new AboutFragment())
                .addToBackStack("about")
                .commit();
    }

    public static class SettingsFragment extends Fragment {
        private final Handler mainHandler = new Handler(Looper.getMainLooper());
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
            view.findViewById(R.id.row_about).setOnClickListener(v ->
                    ((SettingsActivity) requireActivity()).openAboutPage());
            view.findViewById(R.id.row_policies).setOnClickListener(v -> openWeb("https://www.typheye.cn/policies/"));
            view.findViewById(R.id.row_license).setOnClickListener(v -> openWeb("https://www.typheye.cn/licenses/gpl-3.0.html"));
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
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_about, container, false);
            view.setBackgroundColor(requireContext().getColor(R.color.surface_page));
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
            ((android.widget.TextView) view.findViewById(R.id.about_version)).setText("版本 " + versionName);
            ((android.widget.TextView) view.findViewById(R.id.about_version_code)).setText(String.valueOf(versionCode));
            ((android.widget.TextView) view.findViewById(R.id.about_android)).setText(
                    String.valueOf(requireContext().getApplicationInfo().targetSdkVersion));
            view.findViewById(R.id.about_share).setOnClickListener(v -> shareApp());
            return view;
        }

        private void openWeb(String url) {
            Intent intent = new Intent(requireContext(), WebActivity.class);
            intent.putExtra("URL", url);
            startActivity(intent);
        }

        private void shareApp() {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "腕管Pro");
            intent.putExtra(Intent.EXTRA_TEXT, "腕管Pro\nhttps://wgpro.typheye.cn");
            startActivity(Intent.createChooser(intent, "分享应用"));
        }
    }
}
