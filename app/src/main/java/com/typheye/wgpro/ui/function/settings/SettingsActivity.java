package com.typheye.wgpro.ui.function.settings;

import android.content.Intent;
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
import com.typheye.wgpro.utils.AppUtils;

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
                resetPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                    @Override
                    public boolean onPreferenceClick(@NonNull Preference preference) {
                        appReset();
                        return true;
                    }
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
    }
}