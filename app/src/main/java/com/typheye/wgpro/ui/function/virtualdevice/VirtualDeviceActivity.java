package com.typheye.wgpro.ui.function.virtualdevice;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.typheye.wgpro.R;

/**
 * 虚拟设备页：纯全屏承载 {@link VirtualDeviceFragment}。
 *
 * <p>与 {@code PreviewActivity} 一致：全屏、**隐藏状态栏**，导航条区域由 Fragment 按 insets 内缩；
 * 不使用应用栏，退出用系统返回键。引擎由 {@code VelaEmulatorManager} 持有，退出本页不会停止。</p>
 */
public class VirtualDeviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_virtual_device);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        WindowInsetsControllerCompat controller =
                WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        controller.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        controller.hide(WindowInsetsCompat.Type.statusBars());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.virtual_host, VirtualDeviceFragment.newInstance(
                            getIntent() == null ? null
                                    : getIntent().getStringExtra(VirtualDeviceFragment.ARG_AVD)),
                            "virtual")
                    .commitNow();
        }
    }
}
