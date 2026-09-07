package com.typheye.wgpro.ui.function.device;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.typheye.wgpro.R;
import com.typheye.wgpro.core.xms.UIParams;
import com.typheye.wgpro.data.DeviceDatabase;
import com.typheye.wgpro.ui.main.MainActivity;
import com.typheye.wgpro.utils.AppUtils;

public class AddDeviceActivity extends AppCompatActivity {
    private static final long SCAN_DURATION_MS = 400L;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        AppUtils.useScreenCutArea(getWindow(), this);
        setContentView(R.layout.activity_add_device);
        AppUtils.applyMainWindowInsets(findViewById(R.id.app_bar_layout),
                findViewById(R.id.add_device_content));
        Toolbar toolbar = findViewById(R.id.add_device_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        findViewById(R.id.button_rescan).setOnClickListener(v -> startScan());
        startScan();
    }

    private void startScan() {
        mainHandler.removeCallbacksAndMessages(null);
        findViewById(R.id.add_device_progress).setVisibility(View.VISIBLE);
        findViewById(R.id.button_rescan).setVisibility(View.GONE);
        findViewById(R.id.add_device_found_card).setVisibility(View.GONE);
        findViewById(R.id.add_device_empty).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.add_device_scan_title)).setText("正在扫描附近设备");
        mainHandler.postDelayed(this::showScanResult, SCAN_DURATION_MS);
    }

    private void showScanResult() {
        if (isFinishing() || isDestroyed()) return;
        findViewById(R.id.add_device_progress).setVisibility(View.GONE);
        findViewById(R.id.button_rescan).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.add_device_scan_title)).setText("扫描完成");
        TextView title = findViewById(R.id.add_device_title);
        TextView initial = findViewById(R.id.add_device_initial);
        View foundCard = findViewById(R.id.add_device_found_card);
        View empty = findViewById(R.id.add_device_empty);
        UIParams params = MainActivity.current_params;
        boolean connected = params != null && params.connected
                && params.connected_device_id != null && !params.connected_device_id.isEmpty();
        boolean exists = false;
        if (connected) {
            try (DeviceDatabase db = new DeviceDatabase(this)) {
                exists = db.exists(params.connected_device_id);
            }
        }
        if (!connected || exists) {
            empty.setVisibility(View.VISIBLE);
            return;
        }
        String deviceName = params.connected_device_name == null
                || params.connected_device_name.trim().isEmpty()
                ? "Xiaomi Wearable" : params.connected_device_name.trim();
        initial.setText(deviceName.substring(0, 1).toUpperCase());
        title.setText(deviceName);
        foundCard.setVisibility(View.VISIBLE);
        foundCard.setOnClickListener(v -> {
            try (DeviceDatabase db = new DeviceDatabase(this)) {
                db.upsert(params.connected_device_id, deviceName, "xiaomi", true);
            }
            Toast.makeText(this, "设备已添加", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    @Override
    protected void onDestroy() {
        mainHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
