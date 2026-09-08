package com.typheye.wgpro.ui.function.debug;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.materialswitch.MaterialSwitch;
import com.typheye.wgpro.R;
import com.typheye.wgpro.debug.TestHandler;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;

public final class TestActivity extends AppCompatActivity {
    private MaterialSwitch enabled;

    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        AppUtils.useScreenCutArea(getWindow(), this);
        setContentView(R.layout.activity_test);
        AppUtils.applyMainWindowInsets(findViewById(R.id.test_app_bar), findViewById(R.id.test_content));
        Toolbar toolbar = findViewById(R.id.test_toolbar);
        toolbar.setNavigationIcon(com.typheye.wgpro.R.drawable.ic_back_vector);
        toolbar.setNavigationOnClickListener(v -> finish());
        enabled = findViewById(R.id.test_enabled);
        enabled.setChecked(TestHandler.isEnabled(this));
        enabled.setOnCheckedChangeListener((button, checked) -> {
            TestHandler.setEnabled(this, checked);
            update();
            if (!checked) finish();
        });
        findViewById(R.id.test_refresh).setOnClickListener(v -> {
            TestHandler.recordOperation(this, "查看内存信息");
            showResult("内存信息", TestHandler.memorySummary());
        });
        findViewById(R.id.test_clear).setOnClickListener(v -> {
            TestHandler.recordOperation(this, "查看日志信息");
            showResult("日志信息", TestHandler.logs());
        });
        MaterialSwitch offline = findViewById(R.id.test_offline_switch);
        MaterialSwitch loggedOut = findViewById(R.id.test_logged_out_switch);
        offline.setChecked(TestHandler.isOfflineSimulationEnabled(this));
        loggedOut.setChecked(TestHandler.isLoggedOutSimulationEnabled(this));
        offline.setOnCheckedChangeListener((button, checked) -> TestHandler.setOfflineSimulation(this, checked));
        loggedOut.setOnCheckedChangeListener((button, checked) -> TestHandler.setLoggedOutSimulation(this, checked));
        findViewById(R.id.test_probe).setOnClickListener(v -> {
            TestHandler.recordOperation(this, "探测云端 API");
            TestHandler.probeApis(this, summary -> runOnUiThread(() -> {
                showResult("云端 API 探测结果", summary);
            }));
        });
        update();
    }

    private void showResult(String title, String message) {
        new WGProAlertDialogBuilder(this).setTitle(title)
                .setMessage(message == null || message.isEmpty() ? "暂无信息" : message)
                .setNegativeButton("关闭", null).show();
    }

    private void update() {
        enabled.setChecked(TestHandler.isEnabled(this));
        ((MaterialSwitch) findViewById(R.id.test_offline_switch)).setChecked(TestHandler.isOfflineSimulationEnabled(this));
        ((MaterialSwitch) findViewById(R.id.test_logged_out_switch)).setChecked(TestHandler.isLoggedOutSimulationEnabled(this));
    }
}
