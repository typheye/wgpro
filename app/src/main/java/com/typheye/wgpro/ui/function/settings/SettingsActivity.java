package com.typheye.wgpro.ui.function.settings;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.SplashActivity;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.utils.AppUtils;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.useScreenCutArea(getWindow(),this);
        setContentView(R.layout.activity_settings);
        AppUtils.fixScreenCutArea(findViewById(R.id.container));

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

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
            // 返回按钮被点击
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            // 获取重新引导的 Preference
            Preference resetPreference = findPreference("app_reset");

            // 设置点击事件
            if (resetPreference != null) {
                resetPreference.setOnPreferenceClickListener(preference -> {
                    appReset();
                    return true;
                });
            }


            // 获取关于应用的 Preference
            Preference aboutPreference = findPreference("app_about");

            // 设置点击事件
            if (aboutPreference != null) {
                aboutPreference.setOnPreferenceClickListener(preference -> {
                    appAbout();
                    return true;
                });
            }

            // 获取隐私政策的 Preference
            Preference policiesPreference = findPreference("app_policies");

            // 设置点击事件
            if (policiesPreference != null) {
                policiesPreference.setOnPreferenceClickListener(preference -> {
                    Intent intent = new Intent(requireActivity(), WebActivity.class);
                    intent.putExtra("URL", "https://www.typheye.cn/policies/");
                    startActivity(intent);
                    return true;
                });
            }


            // 获取开源协议的 Preference
            Preference licensePreference = findPreference("app_license");

            // 设置点击事件
            if (licensePreference != null) {
                licensePreference.setOnPreferenceClickListener(preference -> {
                    Intent intent = new Intent(requireActivity(), WebActivity.class);
                    intent.putExtra("URL", "https://www.typheye.cn/licenses/gpl-3.0.html");
                    startActivity(intent);
                    return true;
                });
            }
        }

        private void appReset() {
            new MaterialAlertDialogBuilder(requireContext())
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

            new MaterialAlertDialogBuilder(requireContext())
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
}