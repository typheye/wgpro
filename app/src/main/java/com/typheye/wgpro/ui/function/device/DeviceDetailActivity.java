package com.typheye.wgpro.ui.function.device;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.AppUtils;

public class DeviceDetailActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "device_id", EXTRA_TYPE = "device_type";

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        AppUtils.useScreenCutArea(getWindow(), this);
        setContentView(R.layout.activity_device_detail);
        // 详情内容自己预留底部导航栏高度，内容可延伸到导航条区域。
        com.typheye.wgpro.utils.SystemBars.applyAppBarInsets(
                findViewById(R.id.app_bar_layout), null);
        AppUtils.applyScrollBottomInsets(getSupportFragmentManager(),
                R.id.device_detail_container);
        Toolbar toolbar = findViewById(R.id.device_detail_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        if (state == null) replaceDeviceFragment();
    }

    @Override protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent == null) return;
        setIntent(intent);
        replaceDeviceFragment();
    }

    private void replaceDeviceFragment() {
        Fragment fragment = "xiaomi".equals(getIntent().getStringExtra(EXTRA_TYPE))
                ? new XiaomiDeviceFragment() : new GenericDeviceFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.device_detail_container, fragment).commit();
    }
}
