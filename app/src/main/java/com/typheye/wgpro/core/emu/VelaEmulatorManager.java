package com.typheye.wgpro.core.emu;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.typheye.wgpro.data.DeviceDatabase;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 模拟设备（Vela 模拟器）实例管理器。
 *
 * <p>引擎进程**不属于任何页面**：离开模拟设备页后继续运行，卡片状态据此显示「运行中/已停止」。
 * 页面进来时是「附着」到已有实例，不会重复启动。</p>
 *
 * <p>注意（已知取舍）：这里不做前台 Service，引擎是应用进程的子进程，
 * **应用进程被系统回收时引擎随之结束**，卡片状态由冷启动时的 {@link #resetStaleState} 纠正。</p>
 */
public final class VelaEmulatorManager {

    private static final String TAG = "VelaManager";

    /** 已适配机型（model 同时作为 AVD id）。 */
    public static final String MODEL_MIWEAR_WATCH = "vela-miwear-watch-5.0";

    /** 单实例占用较大，低于该可用内存阈值时不再新起实例。 */
    private static final long MIN_FREE_BYTES = 1200L * 1024 * 1024;

    /**
     * 同时最多运行的模拟设备实例数。
     *
     * <p>实测两台并发不可靠：第二台引擎能执行客机代码，但 gRPC 端口常常绑定不上/取不到帧
     * （控制台/adb 端口也会互抢），页面只会一直转圈。按用户拍板：**同一时间只跑一个实例**；
     * 不同设备的实例之间仍然**完全独立、互不串扰**（各自运行目录 / AVD / 数据分区）。
     * 再启动另一台时由 {@link #start} 自动停掉当前那台（见 SWITCH_* 逻辑）。</p>
     */
    public static final int MAX_INSTANCES = 1;

    private static final int PORT_BASE = 8554;
    private static final int PORT_STEP = 2;
    private static final int LOG_MAX = 400;

    private static final VelaEmulatorManager INSTANCE = new VelaEmulatorManager();

    @NonNull
    public static VelaEmulatorManager get() {
        return INSTANCE;
    }

    /** 一个运行中的模拟设备实例。 */
    public static final class Instance {
        public final String model;
        public final VelaEmulator emulator;
        public final int port;
        final ArrayDeque<String> log = new ArrayDeque<>();
        final CopyOnWriteArrayList<VelaEmulator.LineListener> listeners =
                new CopyOnWriteArrayList<>();
        volatile boolean running;

        Instance(@NonNull String model, @NonNull VelaEmulator emulator, int port) {
            this.model = model;
            this.emulator = emulator;
            this.port = port;
        }
    }

    private final Map<String, Instance> instances = new ConcurrentHashMap<>();
    /**
     * 实例**还没创建**时先登记的监听者。
     *
     * <p>页面是先注册监听、之后实例才启动；若此处直接丢弃，页面就永远收不到实时日志
     * （只有退出重进时靠回放环形缓冲才"完整"）—— 踩过。</p>
     */
    private final Map<String, CopyOnWriteArrayList<VelaEmulator.LineListener>> pendingListeners =
            new ConcurrentHashMap<>();
    @Nullable
    private Context appContext;

    /** 运行态变化监听（设备列表据此**立刻**刷新，不必等 1s ticker）。 */
    public interface StateListener {
        void onStateChanged();
    }

    private final CopyOnWriteArrayList<StateListener> stateListeners = new CopyOnWriteArrayList<>();

    public void addStateListener(@NonNull StateListener listener) {
        if (!stateListeners.contains(listener)) {
            stateListeners.add(listener);
        }
    }

    public void removeStateListener(@NonNull StateListener listener) {
        stateListeners.remove(listener);
    }

    /** 通知所有监听者"运行态可能变了"（引擎启动/停止/退出都会调）。 */
    private void notifyStateChanged() {
        for (StateListener listener : stateListeners) {
            try {
                listener.onStateChanged();
            } catch (Throwable error) {
                Log.w(TAG, "通知状态变化失败", error);
            }
        }
    }

    /** 进程内的"启动对账"只做一次。 */
    private boolean reconciled;

    private VelaEmulatorManager() {
    }

    public void init(@NonNull Context context) {
        appContext = context.getApplicationContext();
        reconcileOnProcessStart();
    }

    /**
     * 进程刚起来时内存里没有任何实例 → 此时**还挂着的守护通知一定是残留**
     * （引擎已随进程结束，或已无法附着），把它撤掉，并把设备库里"在线"的行改回离线。
     * 否则会出现"通知栏说运行中、设备页说已停止"的不一致。
     */
    private void reconcileOnProcessStart() {
        if (reconciled || !instances.isEmpty() || appContext == null) {
            return;
        }
        reconciled = true;
        try {
            // 只撤掉残留通知，**不要**在这里 stopService：
            // 它会和 startForegroundService() 抢跑 → 服务来不及 startForeground →
            // 系统抛 ForegroundServiceDidNotStartInTime 直接杀 App（踩过）。
            VelaGuardService.cancelNotification(appContext);
        } catch (Exception ignored) {
        }
        try (DeviceDatabase db = new DeviceDatabase(appContext)) {
            db.markConnectedDevicesOffline(DeviceDatabase.TYPE_VIRTUAL);
        } catch (Exception error) {
            Log.w(TAG, "启动对账：重置模拟设备在线状态失败", error);
        }
    }

    /** 设备库主键。 */
    @NonNull
    public static String deviceId(@NonNull String model) {
        return DeviceDatabase.TYPE_VIRTUAL + ":" + model;
    }

    public boolean isRunning(@Nullable String model) {
        if (model == null) {
            return false;
        }
        Instance instance = instances.get(model);
        if (instance == null) {
            // 内存里没有这个实例键（例如 App 重启过、或实例是旧版本用"机型"键启动的）
            // → 回退查这一行的状态，保证卡片/弹窗与通知栏一致；真有实例时下面会实时校正。
            return persistedConnected(appContext, model);
        }
        // 以真实进程为准：进程已退出就不算运行中
        Process process = instance.emulator.process();
        boolean alive = process != null;
        if (alive) {
            try {
                process.exitValue();
                alive = false;
            } catch (IllegalThreadStateException stillRunning) {
                alive = true;
            }
        }
        instance.running = alive;
        return alive;
    }

    public int port(@Nullable String model) {
        Instance instance = model == null ? null : instances.get(model);
        return instance == null ? PORT_BASE : instance.port;
    }

    /** 已捕获的引擎输出（页面重新附着时先补齐历史）。 */
    @NonNull
    public List<String> log(@Nullable String model) {
        Instance instance = model == null ? null : instances.get(model);
        if (instance == null) {
            return new ArrayList<>();
        }
        synchronized (instance.log) {
            return new ArrayList<>(instance.log);
        }
    }

    public void addLineListener(@Nullable String model, @NonNull VelaEmulator.LineListener listener) {
        if (model == null) {
            return;
        }
        Instance instance = instances.get(model);
        if (instance != null) {
            if (!instance.listeners.contains(listener)) {
                instance.listeners.add(listener);
            }
        } else {
            CopyOnWriteArrayList<VelaEmulator.LineListener> list =
                    pendingListeners.computeIfAbsent(model, k -> new CopyOnWriteArrayList<>());
            if (!list.contains(listener)) {
                list.add(listener);
            }
        }
    }

    public void removeLineListener(@Nullable String model, @NonNull VelaEmulator.LineListener listener) {
        if (model == null) {
            return;
        }
        Instance instance = instances.get(model);
        if (instance != null) {
            instance.listeners.remove(listener);
        }
        CopyOnWriteArrayList<VelaEmulator.LineListener> list = pendingListeners.get(model);
        if (list != null) {
            list.remove(listener);
        }
    }

    /** 冷启动纠正：应用进程重启后，此前记录的「运行中」一律清零。 */
    public void resetStaleState(@NonNull Context context) {
        try (DeviceDatabase db = new DeviceDatabase(context)) {
            db.markConnectedDevicesOffline(DeviceDatabase.TYPE_VIRTUAL);
        } catch (Exception error) {
            Log.w(TAG, "重置模拟设备状态失败", error);
        }
    }

    /**
     * 启动（或附着到）某机型的实例。
     *
     * @return 运行中的实例
     */
    @NonNull
    /** 兼容重载：只给机型时按"机型"当键（老调用/深链用）。 */
    public Instance start(@NonNull Context context, @NonNull String model) throws Exception {
        return start(context, model, deviceId(model));
    }

    /**
     * 启动一台模拟设备实例。**键是设备 id**（不是机型）——
     * 同一个镜像可以创建多台设备，各自独立运行、独立沙盒（AVD 按设备）。
     */
    public Instance start(@NonNull Context context, @NonNull String model,
                          @NonNull String deviceId) throws Exception {
        init(context);
        Instance existing = instances.get(deviceId);
        if (existing != null && isRunning(deviceId)) {
            return existing;   // 已在运行：直接附着，不重复启动
        }
        // 旧版本用"机型"当键启动、进程还在跑 → 认领到这台设备名下（避免重复起第二台）
        String legacyKey = deviceId(model);
        Instance legacy = instances.get(legacyKey);
        if (legacy != null && isRunning(legacyKey)) {
            instances.remove(legacyKey);
            instances.put(deviceId, legacy);
            setConnected(context, deviceId, true);
            return legacy;
        }
        // 只允许一个实例：先停掉当前运行的那台（每个设备目录互相独立，数据不会串）
        if (MAX_INSTANCES <= 1) {
            for (String key : new ArrayList<>(instances.keySet())) {
                if (!key.equals(deviceId)) {
                    Log.i(TAG, "已有实例运行，切换到新设备前先停止: " + key);
                    stopByKey(context, key);
                }
            }
        } else if (instances.size() >= MAX_INSTANCES) {
            throw new IllegalStateException("最多同时运行 " + MAX_INSTANCES
                    + " 台模拟设备，请先停止一台再试");
        }
        if (!hasEnoughMemory(context) && runningCount() > 0) {
            throw new IllegalStateException("可用内存不足，请先停止其它模拟设备");
        }
        // 端口按"当前被占用的 grpcPort 集合"取第一个空闲的，而不是按 instances.size() 猜：
        // 之前用 size 算，进程退出后留下的旧实例会让两台引擎拿到同一个端口（踩过）。
        int port = PORT_BASE;
        java.util.Set<Integer> used = new java.util.HashSet<>();
        for (Instance item : instances.values()) {
            used.add(item.port);
        }
        while (used.contains(port) && port + PORT_STEP <= PORT_BASE + 60) {
            port += PORT_STEP;
        }
        // 每台设备一套完整独立目录；缺就地从镜像包补（同机型多台互不共享）
        try {
            VelaProvision.ensureRuntime(context, model, deviceId);
        } catch (Throwable error) {
            // 补目录只是"锦上添花"，失败绝不能把启动打挂（引擎自己会报缺什么）
            Log.w(TAG, "补运行目录失败（继续尝试启动）", error);
        }
        VelaEmulator emulator = new VelaEmulator(context, model, deviceId);
        final Instance instance = new Instance(model, emulator, port);
        emulator.setLineListener(line -> {
            synchronized (instance.log) {
                while (instance.log.size() >= LOG_MAX) {
                    instance.log.removeFirst();
                }
                instance.log.addLast(line);
            }
            for (VelaEmulator.LineListener listener : instance.listeners) {
                listener.onLine(line);
            }
            CopyOnWriteArrayList<VelaEmulator.LineListener> waiting = pendingListeners.get(deviceId);
            if (waiting != null) {
                for (VelaEmulator.LineListener listener : waiting) {
                    listener.onLine(line);
                }
            }
        });
        // 引擎结束（正常退出/崩溃/被系统杀）→ **实时**把库里这行置为已停止、撤掉实例，
        // 设备页的每秒对账/卡片、通知栏据此显示真实状态。
        emulator.setExitListener(() -> {
            instance.running = false;
            // 注意：切换设备时旧引擎的退出回调可能晚于新实例的启动，
            // 这时**不能**撤掉新设备刚发的通知（否则新设备通知栏缺失）。
            boolean stillCurrent = instances.get(deviceId) == instance;
            if (stillCurrent) {
                instances.remove(deviceId);
            }
            if (appContext != null) {
                setConnected(appContext, deviceId, false);
                if (stillCurrent && runningCount() == 0) {
                    VelaGuardService.cancelNotification(appContext);
                }
            }
            Log.i(TAG, "引擎已结束，状态已置为已停止: " + deviceId);
            notifyStateChanged();
        });
        // 运行目录按机型隔离后，早期创建的设备在新目录下没有运行时 → 给一句人话提示
        if (!emulator.isRuntimeReady()) {
            throw new IllegalStateException("运行目录缺失，请在设备详情页重新创建该设备");
        }
        emulator.ensureAssets((name, percent) -> { });
        emulator.installExecLinks();
        // AVD 按**设备**：可写数据 userdata-qemu.img 落在各自 AVD → 沙盒天然隔离
        // 每次启动都会重写 config.ini：**先把设备原有的皮肤读回来**，
        // 否则会把用户创建时选的皮肤覆盖成默认值（表现：建的是手环，启动的却是手表）。
        emulator.setSkin(emulator.skinOfAvd(deviceId));
        emulator.authorAvd(deviceId, emulator.imagesDir());
        emulator.start(deviceId, port);
        // 引擎跑起来 → 起前台服务守护（熄屏/后台不被回收）
        VelaGuardService.postNotification(context, deviceDisplayName(context, deviceId, model),
                deviceId);
        instance.running = true;
        instances.put(deviceId, instance);
        CopyOnWriteArrayList<VelaEmulator.LineListener> waiting = pendingListeners.remove(deviceId);
        if (waiting != null) {
            instance.listeners.addAllAbsent(waiting);
        }
        setConnected(context, deviceId, true);
        notifyStateChanged();
        return instance;
    }

    /**
     * 停止实例（兼容入口：老调用/深链可能只传**机型**）。
     * 以前直接 `stopByKey(model)`：键是设备 id，传机型就找不到实例、静默不生效（踩过）。
     * 现在先按设备 id 直查；不是 id 再按机型找**最匹配的在跑实例**（优先 devices 库里 id 属于该机型的那台）。
     */
    public void stop(@NonNull Context context, @NonNull String modelOrDeviceId) {
        String key = modelOrDeviceId;
        if (!key.startsWith(DeviceDatabase.TYPE_VIRTUAL) || !instances.containsKey(key)) {
            // 设备 id 但库里能对上 → 用库里的精确 id
            if (key.startsWith(DeviceDatabase.TYPE_VIRTUAL)) {
                key = resolveDeviceIdByModel(context, key);
            } else {
                // 传的是机型 → 在实例里找同机型的那台
                String picked = null;
                for (Instance item : instances.values()) {
                    // 实例键是设备 id，机型要从 id 解析；item.model 也存的是机型标识，两者都认
                    if (key.equals(item.model)
                            || key.equals(VelaProvision.modelIdOf(item.emulator.deviceId()))) {
                        picked = item.emulator.deviceId();
                        break;
                    }
                }
                if (picked != null) {
                    key = picked;
                } else {
                    key = resolveDeviceIdByModel(context, key);
                }
            }
        }
        stopByKey(context, key);
    }

    /** 按机型在设备库里找一台设备 id（优先运行中，其次最近活跃）；找不到返回原值。 */
    @NonNull
    private String resolveDeviceIdByModel(@NonNull Context context, @NonNull String model) {
        String fallback = model;
        try (DeviceDatabase db = new DeviceDatabase(context);
             android.database.Cursor cursor = db.all(DeviceDatabase.TYPE_VIRTUAL)) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                if (!model.equals(VelaProvision.modelIdOf(id))) {
                    continue;
                }
                boolean connected = cursor.getInt(cursor.getColumnIndexOrThrow("connected")) != 0;
                if (connected) {
                    return id;
                }
                fallback = id;
            }
        } catch (Exception ignored) {
        }
        return fallback;
    }

    /** 按**设备 id**停止一台实例（页面/卡片都用这个）。 */
    public void stopByKey(@NonNull Context context, @NonNull String key) {
        Instance instance = instances.get(key);
        if (instance == null) {
            setConnected(context, key, false);
            notifyStateChanged();
            return;
        }
        instance.running = false;
        try {
            instance.emulator.stop();
        } catch (Exception error) {
            Log.w(TAG, "停止实例失败", error);
        }
        setConnected(context, key, false);
        if (runningCount() == 0) {
            VelaGuardService.cancelNotification(context);
        }
        notifyStateChanged();
    }

    public void stopAll(@NonNull Context context) {
        for (String key : new ArrayList<>(instances.keySet())) {
            stopByKey(context, key);
        }
    }

    /** 读设备库里这一行的在线状态（查不到就 false）。 */
    private boolean persistedConnected(@Nullable Context context, @NonNull String key) {
        if (context == null || !key.startsWith(DeviceDatabase.TYPE_VIRTUAL)) {
            return false;
        }
        try (DeviceDatabase db = new DeviceDatabase(context);
             android.database.Cursor cursor = db.all(DeviceDatabase.TYPE_VIRTUAL)) {
            while (cursor.moveToNext()) {
                if (key.equals(cursor.getString(cursor.getColumnIndexOrThrow("id")))) {
                    return cursor.getInt(cursor.getColumnIndexOrThrow("connected")) != 0;
                }
            }
        } catch (Exception ignored) {
        }
        return false;
    }

    /**
     * 当前是否已有实例在运行（任意设备）。用于进入模拟页前提示"会结束旧实例"。
     * 只认**内存里有实例且进程还活着**——避免库里残留的 connected=1 误报。
     */
    public boolean hasRunningInstance() {
        for (String key : instances.keySet()) {
            if (isRunning(key)) {
                return true;
            }
        }
        return false;
    }

    /** 某台设备此刻是否真在运行（同样只认活着的进程，不看库里的残留标志）。 */
    public boolean isRunningDevice(@Nullable String deviceId) {
        return deviceId != null && isRunning(deviceId);
    }

    /** 设备显示名（通知/弹窗文案用）。 */
    @Nullable
    public String displayName(@NonNull Context context, @NonNull String deviceId,
                              @NonNull String model) {
        return deviceDisplayName(context, deviceId, model);
    }

    private int runningCount() {
        int count = 0;
        for (String key : instances.keySet()) {
            if (isRunning(key)) {
                count++;
            }
        }
        return count;
    }

    /** 可用内存是否够再起一台（用户内存情况而定）。 */
    public boolean hasEnoughMemory(@NonNull Context context) {
        try {
            ActivityManager manager =
                    (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            if (manager == null) {
                return true;
            }
            ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
            manager.getMemoryInfo(info);
            return !info.lowMemory && info.availMem > MIN_FREE_BYTES;
        } catch (Exception error) {
            return true;
        }
    }

    /**
     * 设备显示名：note（改名备注）优先，其次 model 列（已是"设备名称 XXXX"），最后机型标识。
     *
     * <p><b>必须按设备 id 精确命中并立即返回</b>：以前的写法把"同机型"的行也一起纳入循环，
     * 而同一机型可以有 8 台设备 → fallback 被最后一行覆盖 → 通知栏/标题显示**别的设备**的名字
     * （表现：列表说 7QXN 在跑、通知说 9354 在跑）——踩过。</p>
     */
    @Nullable
    private static String deviceDisplayName(@NonNull Context context, @NonNull String deviceId,
                                            @NonNull String model) {
        try (DeviceDatabase db = new DeviceDatabase(context);
             android.database.Cursor cursor = db.all(DeviceDatabase.TYPE_VIRTUAL)) {
            String modelFallback = null;
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                String note = cursor.getString(cursor.getColumnIndexOrThrow("note"));
                String rowModel = cursor.getString(cursor.getColumnIndexOrThrow("model"));
                String name = (note != null && !note.trim().isEmpty()) ? note.trim()
                        : (rowModel == null ? "" : rowModel.trim());
                if (deviceId.equals(id)) {
                    // 精确命中本设备：只认这一行，绝不看别的同机型设备
                    return name.isEmpty() ? model : name;
                }
                // 老入口只给机型（没有设备 id）时，用第一台同机型设备兜底
                if (modelFallback == null && VelaProvision.isSameModel(id, model)) {
                    modelFallback = name;
                }
            }
            return modelFallback == null || modelFallback.isEmpty() ? model : modelFallback;
        } catch (Exception error) {
            return model;
        }
    }

    private void setConnected(@NonNull Context context, @NonNull String key, boolean connected) {
        try (DeviceDatabase db = new DeviceDatabase(context)) {
            // key 是**设备 id**（一行一台）→ 只更新那一行；
            // 不是设备 id（老调用/深链传机型）→ 退回"同机型一起更新"的老行为
            boolean exact = false;
            try (android.database.Cursor cursor = db.all(DeviceDatabase.TYPE_VIRTUAL)) {
                while (cursor.moveToNext()) {
                    if (key.equals(cursor.getString(cursor.getColumnIndexOrThrow("id")))) {
                        exact = true;
                        break;
                    }
                }
            }
            if (exact) {
                db.updateConnection(key, connected);
                return;
            }
            // 键对不上任何一行就什么都不做：
            // 以前这里会"同机型一起更新"，导致一台运行 → 同机型所有设备都显示运行中（踩过）。
        } catch (Exception error) {
            Log.w(TAG, "更新模拟设备状态失败", error);
        }
    }
}
