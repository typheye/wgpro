package com.typheye.wgpro.ui.function.device;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.typheye.wgpro.R;
import com.typheye.wgpro.core.xms.XmsConnectionProbe;
import com.typheye.wgpro.data.DeviceDatabase;
import com.typheye.wgpro.ui.main.MainActivity;
import com.typheye.wgpro.utils.AppUtils;
import com.xiaomi.xms.wearable.Wearable;
import com.xiaomi.xms.wearable.node.Node;
import com.xiaomi.xms.wearable.node.NodeApi;

public class AddDeviceActivity extends AppCompatActivity {
    private static final long SCAN_DURATION_MS = 400L;
    private static final long ACTIVE_SCAN_TIMEOUT_MS = 8_000L;
    private static final long SCAN_POLL_INTERVAL_MS = 1_500L;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final Runnable scanTick = this::queryConnection;
    private NodeApi nodeApi;
    private boolean resumed;
    private boolean scanInFlight;
    private boolean activeScan;
    private long scanStartedAt;
    private String discoveredNodeId;
    private String discoveredNodeName;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        AppUtils.useScreenCutArea(getWindow(), this);
        setContentView(R.layout.activity_add_device);
        AppUtils.applyMainWindowInsets(findViewById(R.id.app_bar_layout),
                findViewById(R.id.add_device_content));
        Toolbar toolbar = findViewById(R.id.add_device_toolbar);
        nodeApi = Wearable.getNodeApi(getApplicationContext());
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        findViewById(R.id.button_rescan).setOnClickListener(v -> startScan());
    }

    private void startScan() {
        mainHandler.removeCallbacks(scanTick);
        activeScan = true;
        scanStartedAt = System.currentTimeMillis();
        findViewById(R.id.add_device_progress).setVisibility(View.VISIBLE);
        findViewById(R.id.button_rescan).setVisibility(View.GONE);
        findViewById(R.id.add_device_found_card).setVisibility(View.GONE);
        findViewById(R.id.add_device_empty).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.add_device_scan_title)).setText("正在扫描附近设备");
        queryConnection();
    }

    private void queryConnection() {
        if (!resumed || scanInFlight || nodeApi == null) return;
        if (Settings.Global.getInt(getContentResolver(), Settings.Global.BLUETOOTH_ON, 0) == 0) {
            handleScanResult(null);
            return;
        }
        scanInFlight = true;
        XmsConnectionProbe.probe(nodeApi, new XmsConnectionProbe.Callback() {
            @Override public void onResult(Node node) {
                runOnUiThread(() -> {
                    scanInFlight = false;
                    handleScanResult(node);
                });
            }

            @Override public void onError(@androidx.annotation.NonNull Exception error) {
                runOnUiThread(() -> {
                    scanInFlight = false;
                    if (activeScan && System.currentTimeMillis() - scanStartedAt
                            >= ACTIVE_SCAN_TIMEOUT_MS) {
                        activeScan = false;
                        showEmptyResult();
                    }
                    scheduleNextProbe();
                });
            }
        });
    }

    private void handleScanResult(Node node) {
        if (!resumed || isFinishing() || isDestroyed()) return;
        long elapsed = System.currentTimeMillis() - scanStartedAt;
        if (activeScan && elapsed < SCAN_DURATION_MS) {
            mainHandler.postDelayed(() -> handleScanResult(node), SCAN_DURATION_MS - elapsed);
            return;
        }
        if (node != null) {
            showConnectedNode(node);
            activeScan = false;
            scheduleNextProbe();
            return;
        }
        if (activeScan && elapsed < ACTIVE_SCAN_TIMEOUT_MS) {
            mainHandler.postDelayed(scanTick, SCAN_POLL_INTERVAL_MS);
            return;
        }
        activeScan = false;
        showEmptyResult();
        scheduleNextProbe();
    }

    private void showConnectedNode(Node node) {
        findViewById(R.id.add_device_progress).setVisibility(View.GONE);
        findViewById(R.id.button_rescan).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.add_device_scan_title)).setText("扫描完成");
        TextView title = findViewById(R.id.add_device_title);
        TextView initial = findViewById(R.id.add_device_initial);
        View foundCard = findViewById(R.id.add_device_found_card);
        View empty = findViewById(R.id.add_device_empty);
        boolean exists;
        try (DeviceDatabase db = new DeviceDatabase(this)) {
            exists = db.exists(node.id);
        }
        if (exists) {
            foundCard.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
            return;
        }
        String deviceName = node.name == null || node.name.trim().isEmpty()
                ? "Xiaomi Wearable" : node.name.trim();
        discoveredNodeId = node.id;
        discoveredNodeName = deviceName;
        MainActivity.current_params.connected = true;
        MainActivity.current_params.connected_device_id = node.id;
        MainActivity.current_params.connected_device_name = deviceName;
        if (MainActivity.current_params.connected_since == 0L) {
            MainActivity.current_params.connected_since = System.currentTimeMillis();
        }
        initial.setText(deviceName.substring(0, 1).toUpperCase());
        title.setText(deviceName);
        ((TextView) findViewById(R.id.add_device_subtitle))
                .setText("UnKnown Version  ·  可添加");
        empty.setVisibility(View.GONE);
        foundCard.setVisibility(View.VISIBLE);
        foundCard.setOnClickListener(v -> {
            if (discoveredNodeId == null || discoveredNodeId.isEmpty()) return;
            try (DeviceDatabase db = new DeviceDatabase(this)) {
                db.upsert(discoveredNodeId, discoveredNodeName, "xiaomi", true);
            }
            Toast.makeText(this, "设备已添加", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void showEmptyResult() {
        discoveredNodeId = null;
        discoveredNodeName = null;
        findViewById(R.id.add_device_progress).setVisibility(View.GONE);
        findViewById(R.id.button_rescan).setVisibility(View.VISIBLE);
        findViewById(R.id.add_device_found_card).setVisibility(View.GONE);
        findViewById(R.id.add_device_empty).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.add_device_scan_title)).setText("扫描完成");
    }

    private void scheduleNextProbe() {
        mainHandler.removeCallbacks(scanTick);
        if (resumed) mainHandler.postDelayed(scanTick, SCAN_POLL_INTERVAL_MS);
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumed = true;
        startScan();
    }

    @Override
    protected void onPause() {
        resumed = false;
        mainHandler.removeCallbacks(scanTick);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mainHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
