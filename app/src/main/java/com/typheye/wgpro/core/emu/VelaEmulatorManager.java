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
 * 虚拟设备（Vela 模拟器）实例管理器。
 *
 * <p>引擎进程**不属于任何页面**：离开虚拟设备页后继续运行，卡片状态据此显示「运行中/已停止」。
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

    private static final int PORT_BASE = 8554;
    private static final int PORT_STEP = 2;
    private static final int LOG_MAX = 400;

    private static final VelaEmulatorManager INSTANCE = new VelaEmulatorManager();

    @NonNull
    public static VelaEmulatorManager get() {
        return INSTANCE;
    }

    /** 一个运行中的虚拟设备实例。 */
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

    private VelaEmulatorManager() {
    }

    public void init(@NonNull Context context) {
        appContext = context.getApplicationContext();
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
            return false;
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
            Log.w(TAG, "重置虚拟设备状态失败", error);
        }
    }

    /**
     * 启动（或附着到）某机型的实例。
     *
     * @return 运行中的实例
     */
    @NonNull
    public Instance start(@NonNull Context context, @NonNull String model) throws Exception {
        init(context);
        Instance existing = instances.get(model);
        if (existing != null && isRunning(model)) {
            return existing;   // 已在运行：直接附着，不重复启动
        }
        if (!hasEnoughMemory(context) && runningCount() > 0) {
            throw new IllegalStateException("可用内存不足，请先停止其它虚拟设备");
        }
        int port = PORT_BASE + instances.size() * PORT_STEP;
        VelaEmulator emulator = new VelaEmulator(context);
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
            CopyOnWriteArrayList<VelaEmulator.LineListener> waiting = pendingListeners.get(model);
            if (waiting != null) {
                for (VelaEmulator.LineListener listener : waiting) {
                    listener.onLine(line);
                }
            }
        });
        // 引擎结束（正常退出/崩溃/被系统杀）→ 实时更新设备状态，模拟设备页才能看到真实状态
        emulator.setExitListener(() -> {
            instance.running = false;
            if (appContext != null) {
                setConnected(appContext, model, false);
                if (runningCount() == 0) {
                    VelaGuardService.stop(appContext);
                }
            }
            Log.i(TAG, "引擎已结束，state 已置为已停止: " + model);
        });
        emulator.ensureAssets((name, percent) -> { });
        emulator.installExecLinks();
        emulator.authorAvd(model, emulator.imagesDir());
        emulator.start(model, port);
        // 引擎跑起来 → 起前台服务守护（熄屏/后台不被回收）
        VelaGuardService.start(context);
        instance.running = true;
        instances.put(model, instance);
        CopyOnWriteArrayList<VelaEmulator.LineListener> waiting = pendingListeners.remove(model);
        if (waiting != null) {
            instance.listeners.addAllAbsent(waiting);
        }
        setConnected(context, model, true);
        return instance;
    }

    /** 停止实例（页面退出**不会**调用，只有用户显式停止或移除时才调用）。 */
    public void stop(@NonNull Context context, @NonNull String model) {
        Instance instance = instances.get(model);
        if (instance == null) {
            setConnected(context, model, false);
            return;
        }
        instance.running = false;
        try {
            instance.emulator.stop();
        } catch (Exception error) {
            Log.w(TAG, "停止实例失败", error);
        }
        setConnected(context, model, false);
        if (runningCount() == 0) {
            VelaGuardService.stop(context);
        }
    }

    public void stopAll(@NonNull Context context) {
        for (String model : new ArrayList<>(instances.keySet())) {
            stop(context, model);
        }
    }

    private int runningCount() {
        int count = 0;
        for (String model : instances.keySet()) {
            if (isRunning(model)) {
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

    private void setConnected(@NonNull Context context, @NonNull String model, boolean connected) {
        try (DeviceDatabase db = new DeviceDatabase(context)) {
            // 同一机型可能对应**多个**虚拟设备（镜像可重复创建）：按 model 把它们的状态一起更新
            boolean any = false;
            try (android.database.Cursor cursor = db.all(DeviceDatabase.TYPE_VIRTUAL)) {
                while (cursor.moveToNext()) {
                    String rowId = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                    if (VelaProvision.isSameModel(rowId, model)) {
                        db.updateConnection(rowId, connected);
                        any = true;
                    }
                }
            }
            if (!any) {
                db.updateConnection(deviceId(model), connected);
            }
        } catch (Exception error) {
            Log.w(TAG, "更新虚拟设备状态失败", error);
        }
    }
}
