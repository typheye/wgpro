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
        findViewById(R.id.test_offline).setOnClickListener(v -> confirmSimulation("模拟断网",
                "下一次网络请求将返回模拟断网结果，是否执行？", () -> TestHandler.armOffline(this)));
        findViewById(R.id.test_logged_out).setOnClickListener(v -> confirmSimulation("模拟未登录",
                "下一次需要登录的请求将返回未登录结果，是否执行？", () -> TestHandler.armLoggedOut(this)));
        findViewById(R.id.test_probe).setOnClickListener(v -> {
            TestHandler.recordOperation(this, "探测云端 API");
            TestHandler.probeApis(this, summary -> runOnUiThread(() -> {
                showResult("云端 API 探测结果", summary);
            }));
        });
        update();
    }

    private void confirmSimulation(String title, String message, Runnable action) {
        TestHandler.recordOperation(this, "请求" + title);
        new WGProAlertDialogBuilder(this).setTitle(title).setMessage(message)
                .setNegativeButton("取消", null)
                .setPositiveButton("执行", (dialog, which) -> { action.run(); update(); }).show();
    }

    private void showResult(String title, String message) {
        new WGProAlertDialogBuilder(this).setTitle(title)
                .setMessage(message == null || message.isEmpty() ? "暂无信息" : message)
                .setNegativeButton("关闭", null).show();
    }

    private void update() {
        enabled.setChecked(TestHandler.isEnabled(this));
    }
}
