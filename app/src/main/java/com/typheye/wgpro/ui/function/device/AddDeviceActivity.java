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
import com.typheye.wgpro.core.CoreService;
import com.typheye.wgpro.core.xms.XmsConnectionProbe;
import com.typheye.wgpro.data.DeviceDatabase;
import com.typheye.wgpro.utils.AppUtils;
import com.xiaomi.xms.wearable.Wearable;
import com.xiaomi.xms.wearable.node.Node;
import com.xiaomi.xms.wearable.node.NodeApi;

public class AddDeviceActivity extends AppCompatActivity {
    private static final long SCAN_DURATION_MS = 400L;
    private static final long ACTIVE_SCAN_TIMEOUT_MS = 10_000L;
    private static final long SCAN_POLL_INTERVAL_MS = 1_500L;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final Runnable scanTick = this::queryConnection;
    private NodeApi nodeApi;
    private boolean resumed;
    /** 进入遮罩只在 Activity 创建后的第一次扫描出现，之后（下拉刷新）不再出现。 */
    private boolean firstScan = true;
    private boolean scanInFlight;
    private boolean activeScan;
    private long scanStartedAt;
    private String discoveredNodeId;
    private String discoveredNodeName;
    private final Runnable scanTimeout = () -> {
        if (!activeScan || !resumed || isFinishing() || isDestroyed()) return;
        activeScan = false;
        scanInFlight = false;
        showEmptyResult();
    };

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        AppUtils.useScreenCutArea(getWindow(), this);
        setContentView(R.layout.activity_add_device);
        // 固定应用栏改毛玻璃：快照源用不滚动的容器，滚动在内部 NestedScrollView 上。
        com.typheye.wgpro.utils.AppBarBlur.installWithScrollContent(this,
                findViewById(R.id.app_bar_layout), findViewById(R.id.add_device_content_host),
                findViewById(R.id.add_device_content));
        Toolbar toolbar = findViewById(R.id.add_device_toolbar);
        nodeApi = Wearable.getNodeApi(getApplicationContext());
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        // 扫描不再用顶部状态卡：下拉刷新即重新扫描（与「创建设备」页的刷新方式一致）
        com.typheye.wgpro.ui.widget.WGProRefreshLayout refresh =
                findViewById(R.id.add_device_refresh);
        refresh.setOnRefreshListener(this::startScan);
    }

    private void startScan() {
        mainHandler.removeCallbacks(scanTick);
        mainHandler.removeCallbacks(scanTimeout);
        activeScan = true;
        scanStartedAt = System.currentTimeMillis();
        // 刷新时不动已发现的卡片，避免闪烁；空态也不隐藏（无设备时它本就该在）
        if (firstScan) {
            firstScan = false;
            setScanOverlay(true);
        }
        mainHandler.postDelayed(scanTimeout, ACTIVE_SCAN_TIMEOUT_MS);
        queryConnection();
    }

    private void queryConnection() {
        if (!resumed || !activeScan || scanInFlight || nodeApi == null) return;
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
                    if (activeScan) mainHandler.postDelayed(scanTick, SCAN_POLL_INTERVAL_MS);
                });
            }
        });
    }

    private void handleScanResult(Node node) {
        if (!resumed || !activeScan || isFinishing() || isDestroyed()) return;
        long elapsed = System.currentTimeMillis() - scanStartedAt;
        if (activeScan && elapsed < SCAN_DURATION_MS) {
            mainHandler.postDelayed(() -> handleScanResult(node), SCAN_DURATION_MS - elapsed);
            return;
        }
        if (node != null) {
            mainHandler.removeCallbacks(scanTimeout);
            showConnectedNode(node);
            activeScan = false;
            return;
        }
        if (activeScan && elapsed < ACTIVE_SCAN_TIMEOUT_MS) {
            mainHandler.postDelayed(scanTick, SCAN_POLL_INTERVAL_MS);
            return;
        }
        activeScan = false;
        mainHandler.removeCallbacks(scanTimeout);
        showEmptyResult();
    }

    private void showConnectedNode(Node node) {
        stopRefresh();
        TextView title = findViewById(R.id.device_option_title);
        TextView initial = findViewById(R.id.device_option_avatar);
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
        // 设备状态由 CoreService 统一持有（页面不再直接改全局状态）
        CoreService.markDeviceCandidate(this, node.id, deviceName);
        initial.setText(deviceName.substring(0, 1).toUpperCase());
        title.setText(deviceName);
        // 第二行：状态 | 设备来源（与「选择设备」卡片同一格式）
        ((TextView) findViewById(R.id.device_option_subtitle))
                .setText("可添加 | 小米运动健康");
        empty.setVisibility(View.GONE);
        foundCard.setVisibility(View.VISIBLE);
        foundCard.setOnClickListener(v -> {
            if (discoveredNodeId == null || discoveredNodeId.isEmpty()) return;
            try (DeviceDatabase db = new DeviceDatabase(this)) {
                db.upsert(discoveredNodeId, discoveredNodeName, "xiaomi", true);
            }
            finish();
        });
    }

    private void showEmptyResult() {
        discoveredNodeId = null;
        discoveredNodeName = null;
        stopRefresh();
        findViewById(R.id.add_device_found_card).setVisibility(View.GONE);
        findViewById(R.id.add_device_empty).setVisibility(View.VISIBLE);
    }

    /** 进入/退出页面级加载遮罩（扫描期间盖住页面，与创建设备页一致）。 */
    private void setScanOverlay(boolean visible) {
        View overlay = findViewById(R.id.add_device_loading_overlay);
        if (overlay != null) {
            overlay.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }

    /** 结束下拉刷新（扫描一轮结束就收起指示器）。 */
    private void stopRefresh() {
        setScanOverlay(false);
        com.typheye.wgpro.ui.widget.WGProRefreshLayout refresh =
                findViewById(R.id.add_device_refresh);
        if (refresh != null) {
            refresh.setRefreshing(false);
        }
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
        mainHandler.removeCallbacks(scanTimeout);
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mainHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
