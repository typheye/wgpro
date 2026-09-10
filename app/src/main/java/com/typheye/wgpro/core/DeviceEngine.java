package com.typheye.wgpro.core;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.typheye.wgpro.core.state.AppState;
import com.typheye.wgpro.core.xms.InterconnectLogic;
import com.typheye.wgpro.core.xms.UIParams;
import com.typheye.wgpro.core.xms.XmsConnectionProbe;
import com.typheye.wgpro.data.DeviceDatabase;
import com.typheye.wgpro.ui.main.MainActivity;
import com.xiaomi.xms.wearable.Wearable;
import com.xiaomi.xms.wearable.auth.AuthApi;
import com.xiaomi.xms.wearable.auth.Permission;
import com.xiaomi.xms.wearable.message.MessageApi;
import com.xiaomi.xms.wearable.message.OnMessageReceivedListener;
import com.xiaomi.xms.wearable.node.Node;
import com.xiaomi.xms.wearable.node.NodeApi;

/**
 * 穿戴设备引擎（原 MainActivity 的 XMS 逻辑）。
 *
 * 判定规则保持与改造前完全一致：
 *  - 蓝牙关闭直接判离线；
 *  - 连续 2 次探测失败才判离线（避免瞬时抖动）；
 *  - 只有状态或数据库发生变化时才刷新 UI 与写库。
 */
final class DeviceEngine implements InterconnectLogic.MessageSender {
    private static final long PROBE_INTERVAL_MS = 3_000L;

    private final Context context;
    private final Handler handler = new Handler(Looper.getMainLooper());

    private final UIParams params = new UIParams();
    private NodeApi nodeApi;
    private AuthApi authApi;
    private MessageApi messageApi;
    private String connectedNodeId = "";
    private int consecutiveDisconnectedProbes;
    private boolean inFlight;
    private boolean running;

    private final Runnable tick = new Runnable() {
        @Override
        public void run() {
            if (!running) return;
            probe();
            handler.postDelayed(this, PROBE_INTERVAL_MS);
        }
    };

    DeviceEngine(Context context) {
        this.context = context.getApplicationContext();
    }

    boolean isConnected() {
        return params.connected;
    }

    UIParams snapshot() {
        return params;
    }

    void start() {
        if (running) return;
        running = true;
        nodeApi = Wearable.getNodeApi(context);
        authApi = Wearable.getAuthApi(context);
        messageApi = Wearable.getMessageApi(context);
        // 兼容镜像：InterconnectLogic 与 JSKit 仍在读 MainActivity 的静态字段
        MainActivity.nodeApi = nodeApi;
        MainActivity.authApi = authApi;
        MainActivity.messageApi = messageApi;
        MainActivity.connectedNodeId = connectedNodeId;
        InterconnectLogic.setMessageSender(this);
        handler.removeCallbacks(tick);
        handler.post(tick);
    }

    void stop() {
        running = false;
        inFlight = false;
        handler.removeCallbacks(tick);
        if (messageApi != null && connectedNodeId != null && !connectedNodeId.isEmpty()) {
            try {
                messageApi.removeListener(connectedNodeId);
            } catch (Exception ignored) {
            }
        }
        InterconnectLogic.setMessageSender(null);
    }

    void reconnectNow() {
        if (!running) {
            start();
            return;
        }
        consecutiveDisconnectedProbes = 0;
        handler.removeCallbacks(tick);
        handler.post(tick);
    }

    /** 扫描页发现设备时同步状态（原 AddDeviceActivity 直接改 MainActivity.current_params）。 */
    void markCandidate(String nodeId, String nodeName) {
        if (nodeId == null || nodeId.isEmpty()) return;
        params.connected = true;
        params.connected_device_id = nodeId;
        params.connected_device_name = nodeName == null || nodeName.isEmpty()
                ? "未知设备" : nodeName;
        if (params.connected_since == 0L) {
            params.connected_since = System.currentTimeMillis();
        }
        publish();
    }

    private void probe() {
        if (inFlight || nodeApi == null) return;
        if (Settings.Global.getInt(context.getContentResolver(), Settings.Global.BLUETOOTH_ON, 0) == 0) {
            applyDisconnected();
            return;
        }
        inFlight = true;
        XmsConnectionProbe.probe(nodeApi, new XmsConnectionProbe.Callback() {
            @Override
            public void onResult(Node node) {
                inFlight = false;
                if (node == null) {
                    consecutiveDisconnectedProbes++;
                    if (consecutiveDisconnectedProbes >= 2) {
                        applyDisconnected();
                    }
                } else {
                    consecutiveDisconnectedProbes = 0;
                    applyConnected(node);
                }
            }

            @Override
            public void onError(@NonNull Exception error) {
                inFlight = false;
                AppState.get().addLog("刷新设备连接失败：" + error.getMessage());
            }
        });
    }

    private void applyConnected(Node node) {
        String previousNodeId = connectedNodeId;
        boolean stateChanged = !params.connected || !node.id.equals(params.connected_device_id);
        params.connected = true;
        params.connected_device_name = node.name;
        params.connected_device_id = node.id;
        if (params.connected_since == 0L) {
            params.connected_since = System.currentTimeMillis();
        }
        int databaseChanges;
        try (DeviceDatabase deviceDb = new DeviceDatabase(context)) {
            databaseChanges = deviceDb.markOtherConnectedDevicesOffline("xiaomi", node.id);
            if (deviceDb.exists(node.id)) {
                databaseChanges += deviceDb.updateConnection(node.id, true);
            }
        }
        if (stateChanged || databaseChanges > 0) {
            publish();
        }
        if (stateChanged) {
            AppState.get().addLog("Connected to device: " + node.name);
        }
        if (node.id.equals(previousNodeId)) return;

        if (messageApi != null && previousNodeId != null && !previousNodeId.isEmpty()) {
            try {
                messageApi.removeListener(previousNodeId);
            } catch (Exception ignored) {
            }
        }
        connectedNodeId = node.id;
        MainActivity.connectedNodeId = connectedNodeId;
        if (authApi == null) return;
        authApi.checkPermission(node.id, Permission.DEVICE_MANAGER)
                .addOnSuccessListener(granted -> {
                    params.mifitness_connected = true;
                    AppState.get().addLog("checkPermission: Permission.DEVICE_MANAGER状态为" + granted);
                    authApi.requestPermission(connectedNodeId,
                                    Permission.DEVICE_MANAGER, Permission.NOTIFY)
                            .addOnSuccessListener(permissions -> {
                                params.device_permission = true;
                                publish();
                                AppState.get().addLog("权限 Permission.DEVICE_MANAGER 申请成功");
                                OnMessageReceivedListener listener = (nodeId, bytes) -> {
                                    AppState.get().addLog("收到长度为" + bytes.length + "的消息，准备处理");
                                    InterconnectLogic.ProcessMessage(nodeId, new String(bytes));
                                };
                                messageApi.addListener(connectedNodeId, listener)
                                        .addOnSuccessListener(unused ->
                                                AppState.get().addLog("开始监听消息！"))
                                        .addOnFailureListener(error ->
                                                AppState.get().addLog("监听消息失败！" + error.getMessage()));
                            })
                            .addOnFailureListener(error ->
                                    AppState.get().addLog("设备权限申请失败：" + error.getMessage()));
                })
                .addOnFailureListener(error -> AppState.get().addLog("检查权限失败：" + error.getMessage()));
    }

    private void applyDisconnected() {
        inFlight = false;
        consecutiveDisconnectedProbes = 0;
        boolean stateChanged = params.connected;
        String previousNodeId = connectedNodeId;
        if (messageApi != null && previousNodeId != null && !previousNodeId.isEmpty()) {
            try {
                messageApi.removeListener(previousNodeId);
            } catch (Exception ignored) {
            }
        }
        connectedNodeId = "";
        MainActivity.connectedNodeId = "";
        params.connected = false;
        params.connected_device_name = "未知设备";
        params.connected_device_id = "";
        params.connected_since = 0L;
        params.mifitness_connected = false;
        params.device_permission = false;
        int databaseChanges;
        try (DeviceDatabase deviceDb = new DeviceDatabase(context)) {
            databaseChanges = deviceDb.markConnectedDevicesOffline("xiaomi");
        }
        if (stateChanged || databaseChanges > 0) {
            AppState.get().addLog("Wearable disconnected");
            publish();
        }
    }

    private void publish() {
        MainActivity.current_params = params;                    // 兼容镜像
        AppState.get().publishDevice(params.copy());             // 观察者快照
    }

    @Override
    public void send(@Nullable String nodeId, @NonNull String payload) {
        if (messageApi == null || nodeId == null || nodeId.isEmpty()) return;
        try {
            messageApi.sendMessage(nodeId, payload.getBytes());
        } catch (Exception error) {
            AppState.get().addLog("发送消息时出错: " + error.getMessage());
        }
    }
}
