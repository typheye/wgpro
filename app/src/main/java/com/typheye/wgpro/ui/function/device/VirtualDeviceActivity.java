package com.typheye.wgpro.ui.function.device;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.AppUtils;

/**
 * 虚拟设备页：纯全屏承载 {@link VirtualDeviceFragment}。
 *
 * <p>与 {@code PreviewActivity} 一致：全屏、**隐藏状态栏**，导航条区域由 Fragment 按 insets 内缩；
 * 不使用应用栏，退出用系统返回键。引擎由 {@code VelaEmulatorManager} 持有，退出本页不会停止。</p>
 */
public class VirtualDeviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AppUtils.useScreenCutArea(getWindow(), this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_device);
        // 内容真正铺满整屏：标记沉浸，避免 SystemBars 的兜底内边距给根布局加 padding
        // （漏掉这一步会让加载遮罩上下各留一条黑边）—— 与 PreviewActivity 同款。
        com.typheye.wgpro.utils.SystemBars.markImmersive(findViewById(R.id.virtual_host));
        hideStatusBar();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.virtual_host, VirtualDeviceFragment.newInstance(
                            getIntent() == null ? null
                                    : getIntent().getStringExtra(VirtualDeviceFragment.ARG_AVD)),
                            "virtual")
                    .commitNow();
        }
    }

    /** 只隐藏状态栏，底部导航条保持原样（同 PreviewActivity）。 */
    private void hideStatusBar() {
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        WindowInsetsControllerCompat controller =
                WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        controller.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        controller.hide(WindowInsetsCompat.Type.statusBars());
    }
}
