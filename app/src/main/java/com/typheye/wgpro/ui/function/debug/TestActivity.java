package com.typheye.wgpro.ui.function.debug;

import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import android.text.InputType;
import android.content.Intent;
import android.view.View;
import com.typheye.wgpro.ui.function.account.UserDetailActivity;

import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.typheye.wgpro.R;
import com.typheye.wgpro.debug.TestHandler;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.utils.BarBlurController;
import com.typheye.wgpro.utils.SystemBars;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;

public final class TestActivity extends AppCompatActivity {
    private MaterialSwitch enabled;
    private Toolbar toolbar;
    private View contentRoot;
    private View fragmentHost;
    private BarBlurController blurController;
    /** 「界面」分组：当前是否正在展示 Available 列表 fragment。 */
    private boolean listShown;
    private Fragment shownFragment;

    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        AppUtils.useScreenCutArea(getWindow(), this);
        setContentView(R.layout.activity_test);
        contentRoot = findViewById(R.id.test_content_host);
        fragmentHost = findViewById(R.id.test_fragment_host);
        // 快照源用**不滚动**的容器，滚动交给内部 NestedScrollView（结构同 SettingsActivity）；
        // 顶部/底部留白由 AppBarBlur 加在滚动视图上。
        blurController = com.typheye.wgpro.utils.AppBarBlur.installWithScrollContent(this,
                findViewById(R.id.test_app_bar), contentRoot, findViewById(R.id.test_content));
        toolbar = findViewById(R.id.test_toolbar);
        toolbar.setNavigationIcon(com.typheye.wgpro.R.drawable.ic_back_vector);
        // 列表展示中：返回先关列表；否则退出调试页。
        toolbar.setNavigationOnClickListener(v -> {
            if (listShown) closeList(); else finish();
        });
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override public void handleOnBackPressed() {
                if (listShown) closeList(); else finish();
            }
        });
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
        findViewById(R.id.test_open_user).setOnClickListener(v -> {
            View content = getLayoutInflater().inflate(R.layout.dialog_edittext, null, false);
            TextInputLayout inputLayout = content.findViewById(R.id.textInputLayout);
            TextInputEditText input = content.findViewById(R.id.editText);
            inputLayout.setHint("用户 UID");
            input.setSingleLine(true);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            new WGProAlertDialogBuilder(this).setTitle("打开用户主页").setView(content)
                    .setNegativeButton("取消", null).setPositiveButton("打开", (d,w) -> {
                        String uid = input.getText() == null ? "" : input.getText().toString().trim();
                        if (!uid.isEmpty()) startActivity(new Intent(this, UserDetailActivity.class).putExtra(UserDetailActivity.EXTRA_TARGET_UID, uid));
                    }).show();
        });
        // 界面：启动 Activity / 启动底部弹窗（各自用一个 fragment 展示可用列表）
        findViewById(R.id.test_launch_activities).setOnClickListener(v ->
                showList(new DebugActivityListFragment(), "启动 Activity"));
        findViewById(R.id.test_launch_sheets).setOnClickListener(v ->
                showList(new DebugBottomSheetListFragment(), "启动底部弹窗"));
        update();
    }

    /** 用 fragment 覆盖调试主页展示可用列表，并把毛玻璃快照源切换到列表容器。 */
    private void showList(Fragment fragment, String title) {
        listShown = true;
        shownFragment = fragment;
        contentRoot.setVisibility(View.GONE);
        fragmentHost.setVisibility(View.VISIBLE);
        toolbar.setTitle(title);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.test_fragment_host, fragment).commit();
        if (blurController != null) blurController.rebindSource(fragmentHost);
    }

    /** 关闭列表，恢复调试主页与毛玻璃快照源。 */
    private void closeList() {
        if (!listShown) return;
        listShown = false;
        if (shownFragment != null) {
            getSupportFragmentManager().beginTransaction().remove(shownFragment).commit();
            shownFragment = null;
        }
        fragmentHost.setVisibility(View.GONE);
        contentRoot.setVisibility(View.VISIBLE);
        toolbar.setTitle("调试");
        if (blurController != null) blurController.rebindSource(contentRoot);
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
