package com.typheye.wgpro.core.emu;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 「模拟设备」引擎：把打包进 APK 的 {@code qemu-system-arm} 拉起，跑一个 BES/Vela 客体。
 *
 * <p><b>为什么必须这样加载</b>（实测结论，见 {@code _guide/mock-device-engine.md}）：</p>
 * <ul>
 *   <li>Android 10+ 禁止执行应用数据目录里的可执行文件（{@code error=13 Permission denied}），
 *       所以不能像 Termux 那样把 rootfs 放 files/ 再 exec；</li>
 *   <li>但**打包进 {@code jniLibs} 的二进制可以被执行**（实测 {@code EXEC_OK exit=0}）——
 *       所以 QEMU 以 {@code libqemu-system-arm.so} 的形式随 APK 分发，需要
 *       {@code android:extractNativeLibs="true"} 让它在 {@code nativeLibraryDir} 里落地可执行。</li>
 * </ul>
 *
 * <p><b>显示</b>：QEMU 侧用内置 VNC 输出（{@code -display none -vnc 127.0.0.1:<port>}，只听本地），
 * Android 侧由 RFB(VNC) 客户端连上去把帧画到 SurfaceView（下一步接入）。</p>
 */
public final class MockDeviceEngine {

    public enum State { IDLE, READY, RUNNING, ERROR }

    /** 引擎二进制名（jniLibs 里必须叫 lib*.so 才能被安装到 nativeLibraryDir）。 */
    public static final String ENGINE_LIB = "libqemu-system-arm.so";

    public interface Listener {
        void onState(@NonNull State state, @NonNull String message);
        /** 引擎 stdout 一行（调试用）。 */
        default void onLog(@NonNull String line) { }
    }

    private static final int VNC_PORT = 5900;          /* 首选端口（= VNC display 0） */
    private static final int VNC_PORT_MAX = 5999;      /* QEMU 的 -vnc :d 只支持 0..99 */

    private final Handler main = new Handler(Looper.getMainLooper());
    private final List<Listener> listeners = new ArrayList<>();

    private State state = State.IDLE;
    private String message = "";
    @Nullable private Uri firmwareUri;
    private String firmwareName = "";
    private Process process;
    private Thread pumpThread;
    private final java.util.ArrayDeque<String> recentOutput = new java.util.ArrayDeque<>();
    private static final int RECENT_LIMIT = 20;
    /** 依赖库目录版本：改动随包分发的库时必须 +1，否则老安装会继续用上次解出来的旧库。 */
    private static final int ENGINE_LIBS_VERSION = 2;
    /** 本次启动使用的板级配置（由固件类型决定）。 */
    private String chosenConfigPath = "";   /* 启动前一定会被设为有效路径 */
    private volatile int chosenVncPort = VNC_PORT;

    /** 选一个空闲端口（QEMU 的 -vnc 只接受 5900..5999 这一段的 display 号）。 */
    private static int pickFreePort() {
        for (int port = VNC_PORT; port <= VNC_PORT_MAX; port++) {
            try (java.net.ServerSocket probe = new java.net.ServerSocket(port)) {
                return port;
            } catch (Exception ignored) {
                // 端口被占用，试下一个
            }
        }
        return VNC_PORT;
    }

    /** 启动前清理残留引擎进程（上次异常退出可能仍在运行并占着端口）。 */
    private static void killStaleEngines(@NonNull Context context) {
        try {
            File proc = new File("/proc");
            File[] entries = proc.listFiles();
            if (entries == null) return;
            String self = String.valueOf(android.os.Process.myPid());
            for (File entry : entries) {
                String name = entry.getName();
                if (!name.matches("[0-9]+") || name.equals(self)) continue;
                try {
                    String cmdline = new String(java.nio.file.Files.readAllBytes(
                            new File(entry, "cmdline").toPath()), "UTF-8");
                    if (cmdline.contains("libqemu-system-arm")) {
                        android.os.Process.sendSignal(Integer.parseInt(name), 9);
                        android.util.Log.i("MockDevice", "已清理残留引擎 pid=" + name);
                    }
                } catch (Exception ignored) {
                }
            }
        } catch (Exception ignored) {
        }
    }

    public void addListener(@NonNull Listener listener) {
        listeners.add(listener);
        listener.onState(state, message);
    }

    public void removeListener(@NonNull Listener listener) { listeners.remove(listener); }

    public State state() { return state; }
    public String message() { return message; }
    public boolean hasFirmware() { return firmwareUri != null; }
    public String firmwareName() { return firmwareName; }
    /** 本次实际使用的 VNC 端口（每次启动动态挑选，避免端口被残留实例占用）。 */
    public int vncPort() { return chosenVncPort; }

    /** 引擎二进制是否已随包分发（没打进包时给出明确提示，而不是静默失败）。 */
    @Nullable
    public static File engineBinary(@NonNull Context context) {
        File lib = new File(context.getApplicationInfo().nativeLibraryDir, ENGINE_LIB);
        return lib.isFile() ? lib : null;
    }

    public static boolean engineAvailable(@NonNull Context context) {
        return engineBinary(context) != null;
    }

    /** 用户在系统文件选择器里挑的固件（App 不内置、不分发固件）。 */
    public void setFirmware(@Nullable Uri uri, @NonNull String displayName) {
        firmwareUri = uri;
        firmwareName = displayName;
        if (state != State.RUNNING) {
            setState(uri == null ? State.IDLE : State.READY,
                    uri == null ? "未选择固件" : "已选择：" + displayName);
        }
    }

    /** 启动引擎（依赖随包分发，直接 exec；全过程在后台线程，避免 ANR）。 */
    public void start(@NonNull Context context) {
        if (state == State.RUNNING) return;
        final Context app = context.getApplicationContext();
        setState(State.READY, "正在启动引擎…");
        new Thread(() -> startBlocking(app), "mock-engine-start").start();
    }

    private void startBlocking(@NonNull Context context) {
        if (state == State.RUNNING) return;
        // 默认用随包的合成配置；若选择了 boot_struct 固件，configForFirmware 会覆盖它
        chosenConfigPath = configPath(context);
        File binary = engineBinary(context);
        if (binary == null) {
            setState(State.ERROR, "模拟引擎未安装：把 qemu-system-arm 作为 " + ENGINE_LIB
                    + " 放进 jniLibs 后即可运行");
            return;
        }
        // 固件来源优先级：SAF 选择的固件 > 投放在私有目录的 firmware.bin > 内置合成客体
        final String kernelPath;
        try {
            File dropped = new File(context.getFilesDir(), "firmware.bin");
            if (firmwareUri != null) {
                kernelPath = firmwarePath(context);
            } else if (dropped.isFile() && dropped.length() > 0) {
                kernelPath = dropped.getAbsolutePath();
                final String note = "使用私有目录投放的固件 firmware.bin（"
                        + (dropped.length() / 1024) + " KB）";
                android.util.Log.i("MockDevice", note);
                main.post(() -> {
                    for (Listener listener : new ArrayList<>(listeners)) listener.onLog(note);
                });
            } else {
                kernelPath = extractAsset(context, "mock_guest.bin", "guest-synthetic.bin");
            }
            extractAsset(context, "mock_hardware.json", "hardware.json");
            // 固件类型决定启动配置：真机 boot_struct 镜像（magic 0xBE57EC1C）代码在头部 +16 处，
            // 用合成客体的 boot_pc(0x0c000001) 会立刻崩，必须用 0x0c000011。
            if (firmwareUri != null || new File(context.getFilesDir(), "firmware.bin").isFile()) {
                configForFirmware(context, kernelPath);
            }
        } catch (Exception error) {
            setState(State.ERROR, "释放内置资源失败：" + error.getMessage());
            return;
        }
        try {
            killStaleEngines(context);
            chosenVncPort = pickFreePort();
            final int port = chosenVncPort;
            main.post(() -> {
                for (Listener listener : new ArrayList<>(listeners)) {
                    listener.onLog("使用 VNC 端口 " + port);
                }
            });
            // activeConfigPath 由 configForFirmware 决定（boot_struct 固件用专门配置）
            final String activeConfigPath = chosenConfigPath;
            // 直接 exec：依赖库全部随包放进 jniLibs，安装后与二进制同在 nativeLibraryDir，
            // Android 链接器在 exec 时会搜索该目录（不依赖 LD_LIBRARY_PATH）。
            List<String> command = new ArrayList<>();
            command.add(binary.getAbsolutePath());
            command.add("-M");
            String machine = "bes1503,hardware-config=" + activeConfigPath;
            android.content.SharedPreferences debugPrefs =
                    android.preference.PreferenceManager.getDefaultSharedPreferences(context);
            int vectorOffset = debugPrefs.getInt("mock_vector_offset", -1);
            String debugPc = debugPrefs.getString("mock_boot_pc", null);
            if (debugPc != null && !debugPc.isEmpty()) {
                machine += ",boot-pc=" + debugPc;
            }
            if (vectorOffset > 0) {
                machine += ",vector-offset=0x" + Integer.toHexString(vectorOffset);
            }
            command.add(machine);
            command.add("-L");
            command.add(qemuDataDir(context).getAbsolutePath());
            command.add("-kernel");
            command.add(kernelPath);
            // 注意：不要加 -display none —— 那会关掉显示管线，headless 下 dpy_gfx_update 被丢弃
            command.add("-vnc");
            command.add("127.0.0.1:" + (chosenVncPort - 5900));
            // 串口写文件：真固件的控制台输出会落盘，便于诊断"固件跑到哪一步"
            command.add("-serial");
            command.add("file:" + new File(context.getFilesDir(), "serial.log").getAbsolutePath());
            ProcessBuilder builder = new ProcessBuilder(command);
            builder.redirectErrorStream(true);
            java.util.Map<String, String> env = builder.environment();
            env.put("LD_LIBRARY_PATH", context.getApplicationInfo().nativeLibraryDir);
            builder.directory(context.getFilesDir());
            process = builder.start();
            pumpThread = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(
                        new InputStreamReader(process.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        final String text = line;
                        main.post(() -> {
                            synchronized (recentOutput) {
                                recentOutput.addLast(text);
                                while (recentOutput.size() > RECENT_LIMIT) recentOutput.removeFirst();
                            }
                            for (Listener listener : new ArrayList<>(listeners)) listener.onLog(text);
                        });
                    }
                } catch (Exception ignored) {
                }
                final int exitCode;
                try {
                    exitCode = process.waitFor();
                    // 等输出线程把剩余行读完，否则 fatal 行可能还没进来
                    Thread reader = pumpThread;
                    if (reader != null && reader != Thread.currentThread()) {
                        reader.join(800);
                    }
                } catch (Exception ignored) {
                    return;
                }
                main.post(() -> {
                    String tail = lastOutputLine();
                    setState(State.ERROR, "引擎已退出（code " + exitCode + "）"
                            + (tail.isEmpty() ? "" : "：" + tail));
                });
            }, "mock-engine-out");
            pumpThread.setDaemon(true);
            pumpThread.start();
            String source = "运行中";
            if (firmwareUri != null) {
                source = "运行中（已选固件）";
            } else if (new File(context.getFilesDir(), "firmware.bin").isFile()) {
                source = "运行中（固件 firmware.bin）";
            } else {
                source = "运行中（内置合成客体）";
            }
            setState(State.RUNNING, source + " · VNC 127.0.0.1:" + chosenVncPort);
        } catch (Exception error) {
            setState(State.ERROR, "启动失败：" + error.getMessage());
        }
    }

    public void stop() {
        Process current = process;
        process = null;
        if (current != null) {
            try {
                current.destroy();
            } catch (Exception ignored) {
            }
        }
        setState(hasFirmware() ? State.READY : State.IDLE, "已停止");
    }

    /**
     * 依据固件类型准备板级配置：
     * <ul>
     *   <li><b>boot_struct 镜像</b>（magic {@code 0xBE57EC1C}，如真机第二级引导）：
     *       代码在头部 +16，入口 {@code 0x0c000011}，栈用 RAM 顶 {@code 0x20160000}；</li>
     *   <li>其它（裸合成客体等）：沿用随包的合成配置。</li>
     * </ul>
     */
    private void configForFirmware(@NonNull Context context, @NonNull String kernelPath) {
        chosenConfigPath = configPath(context);
        try {
            byte[] head = new byte[4];
            try (java.io.InputStream in = new java.io.FileInputStream(kernelPath)) {
                if (in.read(head) != 4) return;
            }
            long magic = (head[0] & 0xFFL) | ((head[1] & 0xFFL) << 8)
                    | ((head[2] & 0xFFL) << 16) | ((head[3] & 0xFFL) << 24);
            if (magic != 0xBE57EC1CL) {
                android.util.Log.i("MockDevice", "非 boot_struct 固件（magic=0x"
                        + Long.toHexString(magic) + "），沿用合成配置");
                return;
            }
            long size = new java.io.File(kernelPath).length();
            long norSize = (size + 0xFFF) & ~0xFFFL;
            String json = readAssetText(context, "mock_hardware.json");
            json = json.replaceAll("\"boot_pc\"\\s*:\\s*[0-9]+", "\"boot_pc\": " + 0x0c000011);
            json = json.replaceAll("\"boot_sp\"\\s*:\\s*[0-9]+", "\"boot_sp\": " + 0x20160000);
            json = json.replaceAll("\"nor_size\"\\s*:\\s*[0-9]+", "\"nor_size\": " + norSize);
            json = json.replaceAll("\"nor_address\"\\s*:\\s*[0-9]+", "\"nor_address\": 0");
            json = json.replaceAll("\"nor_image_offset\"\\s*:\\s*-?[0-9]+",
                    "\"nor_image_offset\": 0");
            json = json.replaceAll("\"flash_offset\"\\s*:\\s*[0-9]+", "\"flash_offset\": 0");
            File target = new File(context.getFilesDir(), "firmware-bootstruct.json");
            try (java.io.OutputStream out = new java.io.FileOutputStream(target)) {
                out.write(json.getBytes("UTF-8"));
            }
            chosenConfigPath = target.getAbsolutePath();
            final String note = "已识别真机 boot_struct 固件，使用匹配配置启动";
            android.util.Log.i("MockDevice", note);
            main.post(() -> {
                for (Listener listener : new ArrayList<>(listeners)) listener.onLog(note);
            });
        } catch (Exception error) {
            android.util.Log.i("MockDevice", "准备固件配置失败: " + error);
        }
    }

    @NonNull
    private static String readAssetText(@NonNull Context context, @NonNull String name)
            throws Exception {
        try (java.io.InputStream in = context.getAssets().open(name)) {
            return new String(readAll(in), "UTF-8");
        }
    }

    /** QEMU 数据目录（keymaps 等）：QEMU 的 -vnc 需要 keymap 文件，缺失会报 could not read keymap。 */
    @NonNull
    private static File qemuDataDir(@NonNull Context context) {
        File dir = new File(context.getFilesDir(), "qemu");
        File keymaps = new File(dir, "keymaps");
        if (!new File(keymaps, "en-us").isFile()) {
            //noinspection ResultOfMethodCallIgnored
            keymaps.mkdirs();
            try {
                String[] names = context.getAssets().list("qemu/keymaps");
                if (names != null) {
                    for (String name : names) {
                        try (java.io.InputStream in = context.getAssets().open("qemu/keymaps/" + name)) {
                            byte[] data = readAll(in);
                            try (java.io.OutputStream out = new java.io.FileOutputStream(
                                    new File(keymaps, name))) {
                                out.write(data);
                            }
                        }
                    }
                }
            } catch (Exception error) {
                android.util.Log.i("MockDevice", "释放 keymaps 失败: " + error);
            }
        }
        return dir;
    }

    /** 板级配置：随包放在 assets，首次启动复制到 files（QEMU 只读它，不执行）。 */
    private static String configPath(@NonNull Context context) {
        return new File(context.getFilesDir(), "hardware.json").getAbsolutePath();
    }

    /** 把 assets 里的文件释放到私有目录（已存在且大小一致就跳过），返回目标绝对路径。 */
    private static String extractAsset(@NonNull Context context, @NonNull String assetName,
                                       @NonNull String targetName) throws Exception {
        File target = new File(context.getFilesDir(), targetName);
        try (java.io.InputStream in = context.getAssets().open(assetName)) {
            byte[] data = readAll(in);
            boolean same = target.isFile() && target.length() == data.length;
            if (!same) {
                try (java.io.OutputStream out = new java.io.FileOutputStream(target)) {
                    out.write(data);
                }
            }
        }
        return target.getAbsolutePath();
    }

    /** 当前 ABI 对应的 assets 子目录名（Termux 库的归档名）。 */
    @NonNull
    private static String abiDir() {
        for (String abi : android.os.Build.SUPPORTED_ABIS) {
            if ("arm64-v8a".equals(abi)) return "aarch64";
            if ("x86_64".equals(abi)) return "x86_64";
        }
        return "aarch64";
    }

    /** 把 assets/engine/&lt;abi&gt; 下的依赖库解到私有目录（已解过就复用），返回该目录。 */
    @NonNull
    private static File extractEngineLibs(@NonNull Context context) throws Exception {
        File target = new File(context.getFilesDir(),
                "engine/v" + ENGINE_LIBS_VERSION + "/" + abiDir());
        String assetDir = "engine/" + abiDir();
        String[] names = context.getAssets().list(assetDir);
        if (names == null) names = new String[0];
        boolean complete = target.isDirectory() && target.list() != null
                && target.list().length >= names.length && names.length > 0;
        if (!complete) {
            //noinspection ResultOfMethodCallIgnored
            target.mkdirs();
            for (String name : names) {
                File out = new File(target, name);
                try (java.io.InputStream in = context.getAssets().open(assetDir + "/" + name)) {
                    byte[] data = readAll(in);
                    if (!out.isFile() || out.length() != data.length) {
                        try (java.io.OutputStream os = new java.io.FileOutputStream(out)) {
                            os.write(data);
                        }
                    }
                }
            }
        }
        return target;
    }

    private static byte[] readAll(@NonNull java.io.InputStream in) throws Exception {
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int read;
        while ((read = in.read(buffer)) > 0) out.write(buffer, 0, read);
        return out.toByteArray();
    }

    /** 固件：用户通过 SAF 选定，复制到 files 后交给 QEMU 当 -kernel。 */
    private static String firmwarePath(@NonNull Context context) {
        return new File(context.getFilesDir(), "guest-firmware.bin").getAbsolutePath();
    }

    /**
     * 退出原因：优先取含 fatal/Lockup/error/CANNOT 的那一行（QEMU 崩溃时会打印整段寄存器 dump，
     * 最后一行往往是无用的 "FPSCR: ..."）。
     */
    @NonNull
    public String lastOutputLine() {
        synchronized (recentOutput) {
            String fallback = "";
            for (String line : recentOutput) {
                if (line == null || line.trim().isEmpty()) continue;
                String text = line.trim();
                fallback = text;
                String lower = text.toLowerCase(java.util.Locale.ROOT);
                if (lower.contains("fatal") || lower.contains("lockup")
                        || lower.contains("cannot") || lower.contains("error")
                        || lower.contains("failed") || lower.contains("could not")) {
                    return text;
                }
            }
            return fallback;
        }
    }

    private void setState(@NonNull State next, @NonNull String text) {
        state = next;
        message = text;
        main.post(() -> {
            for (Listener listener : new ArrayList<>(listeners)) listener.onState(next, text);
        });
    }
}
