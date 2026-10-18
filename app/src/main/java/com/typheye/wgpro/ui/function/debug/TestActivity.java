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
    /** 当前是否正在展示子页面 Fragment（模拟操作等）。 */
    private boolean listShown;
    private Fragment shownFragment;


    /** Release 包（不可调试）里不允许进入调试页。 */
    private boolean isDebuggableBuild() {
        return (getApplicationInfo().flags
                & android.content.pm.ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        // 调试页只属于调试构建：Release 包即使被外部/快捷方式拉起也直接退出
        if (!isDebuggableBuild()) {
            finish();
            return;
        }
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
        // 整行点按也能切换（与设置页各开关行一致）
        findViewById(R.id.row_test_enabled).setOnClickListener(v -> enabled.toggle());
        findViewById(R.id.test_refresh).setOnClickListener(v -> {
            TestHandler.recordOperation(this, "查看内存信息");
            showResult("内存信息", TestHandler.memorySummary());
        });
        findViewById(R.id.test_clear).setOnClickListener(v -> {
            TestHandler.recordOperation(this, "查看日志信息");
            showResult("日志信息", TestHandler.logs());
        });
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
        // 更多：模拟操作单独成页（Fragment 展示，切换动画与毛玻璃反馈沿用统一逻辑）
        findViewById(R.id.test_simulate).setOnClickListener(v ->
                showList(new DebugSimulationFragment(), "模拟操作"));
        update();
    }

    /** 用 fragment 覆盖调试主页展示可用列表，并把毛玻璃快照源切换到列表容器。 */
    private void showList(Fragment fragment, String title) {
        listShown = true;
        shownFragment = fragment;
        contentRoot.setVisibility(View.GONE);
        fragmentHost.setVisibility(View.VISIBLE);
        toolbar.setTitle(title);
        AppUtils.applyFragmentTransition(getSupportFragmentManager().beginTransaction())
                .replace(R.id.test_fragment_host, fragment)
                .commit();
        if (blurController != null) blurController.rebindSource(fragmentHost);
    }

    /** 关闭列表，恢复调试主页与毛玻璃快照源；退场动画播完后再切换可见性。 */
    private void closeList() {
        if (!listShown) return;
        listShown = false;
        Fragment removing = shownFragment;
        shownFragment = null;
        toolbar.setTitle("调试");
        if (removing != null) {
            AppUtils.applyFragmentTransition(getSupportFragmentManager().beginTransaction())
                    .remove(removing)
                    .commit();
        }
        // 立刻隐藏父容器会让退场动画看不到：等动画播完再切回调试主页。
        fragmentHost.postDelayed(() -> {
            if (listShown) return; // 期间又打开了新列表，交给 showList 处理
            fragmentHost.setVisibility(View.GONE);
            contentRoot.setVisibility(View.VISIBLE);
            if (blurController != null) blurController.rebindSource(contentRoot);
        }, 220L);
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
