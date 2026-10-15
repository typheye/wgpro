package com.typheye.wgpro.ui.function.device;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.typheye.wgpro.R;
import com.typheye.wgpro.core.CoreService;
import com.typheye.wgpro.core.xms.XmsConnectionProbe;
import com.typheye.wgpro.data.DeviceDatabase;
import com.xiaomi.xms.wearable.Wearable;
import com.xiaomi.xms.wearable.node.Node;
import com.xiaomi.xms.wearable.node.NodeApi;

/**
 * 附近设备（扫描/发现）：原 {@code AddDeviceActivity} 的逻辑，搬进 Fragment 后
 * Handler 与超时都随 {@code viewLifecycleOwner} 走，避免切页后仍在后台空转。
 */
public class DeviceDiscoverFragment extends Fragment {

    private static final long SCAN_DURATION_MS = 400L;
    private static final long ACTIVE_SCAN_TIMEOUT_MS = 10_000L;
    private static final long SCAN_POLL_INTERVAL_MS = 1_500L;

    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final Runnable scanTick = this::queryConnection;

    private NodeApi nodeApi;
    private boolean resumed;
    /** 进入遮罩只在第一次扫描出现，之后（下拉刷新）不再出现。 */
    private boolean firstScan = true;
    private boolean scanInFlight;
    private boolean activeScan;
    private long scanStartedAt;
    private String discoveredNodeId;
    private String discoveredNodeName;
    private View root;

    private final Runnable scanTimeout = () -> {
        if (!activeScan || !resumed || !isAdded()) {
            return;
        }
        activeScan = false;
        scanInFlight = false;
        showEmptyResult();
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle state) {
        return inflater.inflate(R.layout.fragment_device_discover, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle state) {
        super.onViewCreated(view, state);
        root = view;
        nodeApi = Wearable.getNodeApi(requireContext().getApplicationContext());
        com.typheye.wgpro.ui.widget.WGProRefreshLayout refresh =
                view.findViewById(R.id.add_device_refresh);
        refresh.setOnRefreshListener(this::startScan);
    }

    @Override
    public void onResume() {
        super.onResume();
        resumed = true;
        startScan();
    }

    @Override
    public void onPause() {
        resumed = false;
        mainHandler.removeCallbacks(scanTick);
        mainHandler.removeCallbacks(scanTimeout);
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        mainHandler.removeCallbacksAndMessages(null);
        root = null;
        super.onDestroyView();
    }

    /** 遮罩到点自动收起后由宿主调用：重新开始一次扫描（等价于下拉刷新）。 */
    public void restartScan() {
        if (!isAdded()) return;
        startScan();
    }

    private void startScan() {
        mainHandler.removeCallbacks(scanTick);
        mainHandler.removeCallbacks(scanTimeout);
        activeScan = true;
        scanStartedAt = System.currentTimeMillis();
        // 刷新时不动已发现的卡片，避免闪烁；空态也不隐藏（无设备时它本就该在）
        if (firstScan) {
            firstScan = false;
            host().showPageOverlay();
        }
        mainHandler.postDelayed(scanTimeout, ACTIVE_SCAN_TIMEOUT_MS);
        queryConnection();
    }

    private void queryConnection() {
        if (!resumed || !activeScan || scanInFlight || nodeApi == null || root == null) {
            return;
        }
        if (Settings.Global.getInt(requireContext().getContentResolver(),
                Settings.Global.BLUETOOTH_ON, 0) == 0) {
            handleScanResult(null);
            return;
        }
        scanInFlight = true;
        XmsConnectionProbe.probe(nodeApi, new XmsConnectionProbe.Callback() {
            @Override
            public void onResult(Node node) {
                if (!isAdded()) {
                    return;
                }
                requireActivity().runOnUiThread(() -> {
                    scanInFlight = false;
                    handleScanResult(node);
                });
            }

            @Override
            public void onError(@NonNull Exception error) {
                if (!isAdded()) {
                    return;
                }
                requireActivity().runOnUiThread(() -> {
                    scanInFlight = false;
                    if (activeScan && System.currentTimeMillis() - scanStartedAt
                            >= ACTIVE_SCAN_TIMEOUT_MS) {
                        activeScan = false;
                        showEmptyResult();
                    }
                    if (activeScan) {
                        mainHandler.postDelayed(scanTick, SCAN_POLL_INTERVAL_MS);
                    }
                });
            }
        });
    }

    private void handleScanResult(@Nullable Node node) {
        if (!resumed || !activeScan || !isAdded() || root == null) {
            return;
        }
        long elapsed = System.currentTimeMillis() - scanStartedAt;
        if (elapsed < SCAN_DURATION_MS) {
            mainHandler.postDelayed(() -> handleScanResult(node), SCAN_DURATION_MS - elapsed);
            return;
        }
        if (node != null) {
            mainHandler.removeCallbacks(scanTimeout);
            showConnectedNode(node);
            activeScan = false;
            return;
        }
        if (elapsed < ACTIVE_SCAN_TIMEOUT_MS) {
            mainHandler.postDelayed(scanTick, SCAN_POLL_INTERVAL_MS);
            return;
        }
        activeScan = false;
        mainHandler.removeCallbacks(scanTimeout);
        showEmptyResult();
    }

    private void showConnectedNode(@NonNull Node node) {
        stopRefresh();
        TextView title = root.findViewById(R.id.device_option_title);
        TextView initial = root.findViewById(R.id.device_option_avatar);
        View foundCard = root.findViewById(R.id.add_device_found_card);
        View empty = root.findViewById(R.id.add_device_empty);
        boolean exists;
        try (DeviceDatabase db = new DeviceDatabase(requireContext())) {
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
        CoreService.markDeviceCandidate(requireContext(), node.id, deviceName);
        initial.setText(deviceName.substring(0, 1).toUpperCase());
        title.setText(deviceName);
        ((TextView) root.findViewById(R.id.device_option_subtitle))
                .setText("可添加 | 小米运动健康");
        empty.setVisibility(View.GONE);
        foundCard.setVisibility(View.VISIBLE);
        foundCard.setOnClickListener(v -> {
            if (discoveredNodeId == null || discoveredNodeId.isEmpty()) {
                return;
            }
            try (DeviceDatabase db = new DeviceDatabase(requireContext())) {
                db.upsert(discoveredNodeId, discoveredNodeName, "xiaomi", true);
            }
            requireActivity().finish();
        });
    }

    private void showEmptyResult() {
        discoveredNodeId = null;
        discoveredNodeName = null;
        stopRefresh();
        if (root == null) {
            return;
        }
        root.findViewById(R.id.add_device_found_card).setVisibility(View.GONE);
        root.findViewById(R.id.add_device_empty).setVisibility(View.VISIBLE);
    }

    /** 结束下拉刷新（扫描一轮结束就收起指示器与页面遮罩）。 */
    private void stopRefresh() {
        host().hidePageOverlay();
        if (root == null) {
            return;
        }
        com.typheye.wgpro.ui.widget.WGProRefreshLayout refresh =
                root.findViewById(R.id.add_device_refresh);
        if (refresh != null) {
            refresh.setRefreshing(false);
        }
    }

    @NonNull
    private AddDeviceActivity host() {
        return (AddDeviceActivity) requireActivity();
    }
}
