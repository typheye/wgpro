package com.typheye.wgpro.ui.function.device;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.AppBarBlur;
import com.typheye.wgpro.utils.AppUtils;

/**
 * 添加设备（**统一入口**）：按 {@link #EXTRA_MODE} 挂不同 Fragment。
 *
 * <ul>
 *   <li>{@link #MODE_WEARABLE} → {@link DeviceDiscoverFragment}：附近设备（扫描/发现真机）</li>
 *   <li>{@link #MODE_VIRTUAL} → {@link VirtualDeviceListFragment}：模拟设备机型列表</li>
 * </ul>
 *
 * <p>应用栏毛玻璃在这里统一装：{@code content} 直接给"承载 Fragment 的容器"，
 * {@link AppBarBlur#install} 会递归给里面第一个滚动视图补顶部留白、并把下拉刷新圈压到应用栏下方。</p>
 */
public class AddDeviceActivity extends AppCompatActivity {

    public static final String EXTRA_MODE = "mode";
    public static final String MODE_WEARABLE = "wearable";
    public static final String MODE_VIRTUAL = "virtual";

    private View overlay;

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        AppUtils.useScreenCutArea(getWindow(), this);
        setContentView(R.layout.activity_add_device);

        overlay = findViewById(R.id.add_device_loading_overlay);
        final boolean virtual = MODE_VIRTUAL.equals(modeOf(getIntent()));
        Toolbar toolbar = findViewById(R.id.add_device_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        toolbar.setTitle(virtual ? "创建设备" : "添加设备");

        AppBarBlur.install(this, findViewById(R.id.app_bar_layout),
                findViewById(R.id.add_device_fragment_host));

        if (state == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.add_device_fragment_host,
                            virtual ? new VirtualDeviceListFragment() : new DeviceDiscoverFragment(),
                            "add-device-content")
                    .commit();
        }
    }

    @NonNull
    private static String modeOf(@Nullable Intent intent) {
        String value = intent == null ? null : intent.getStringExtra(EXTRA_MODE);
        return value == null ? MODE_WEARABLE : value;
    }

    /** 页面级加载遮罩：进页面/首次拉取/扫描期间由 Fragment 调起。 */
    public void showPageOverlay() {
        if (overlay != null) {
            overlay.setVisibility(View.VISIBLE);
        }
    }

    public void hidePageOverlay() {
        if (overlay != null) {
            overlay.setVisibility(View.GONE);
        }
    }
}
