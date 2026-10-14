package com.typheye.wgpro.core.emu;

import android.content.Context;
import android.system.Os;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Vela 模拟器运行时（自制实现，仅采用公开机制，不复制第三方代码）：
 *
 * <p>要点：{@code untrusted_app} 不能 execve 私有目录里的文件，但可以 exec jniLibs 里的文件；
 * 于是 jniLibs 只放两样东西 —— glibc loader 与一个静态 exec-stub，引擎要 exec 的路径做成
 * **软链接指向 stub**，stub 再用 loader 从私有目录 mmap 起真负载。</p>
 */
public final class VelaEmulator {

    /** 环境变量：告诉 stub 私有运行目录在哪。 */
    public static final String ENV_DIR = "WGPRO_VELA_DIR";

    /** 引擎会按相对路径 exec 的二进制（软链接指向 stub，真身改名成 .elf）。 */
    private static final String[] SHIM_BINARIES = {
        "qemu/linux-aarch64/qemu-system-armel",
        "qemu/linux-aarch64/qemu-system-armel-headless",
        "qemu/linux-aarch64/qemu-system-aarch64",
        "qemu/linux-aarch64/qemu-system-aarch64-headless",
        "bin64/e2fsck",
        "bin64/fsck.ext4",
        "bin64/mkfs.ext4",
        "bin64/resize2fs",
        "bin64/tune2fs",
    };

    /** 需要按需下载的资产：引擎负载包 + 三个系统镜像（都放在自家服务器上）。 */
    private static final String[][] ASSETS = {
        {"payload.tar", "payload.tar"},
        {"nuttx", "nuttx"},
        {"vela_system.bin", "vela_system.bin"},
        {"vela_data.bin", "vela_data.bin"},
    };

    private static final String BASE_URL = "https://res.typheye.cn/vela/";

    private final Context context;
    @Nullable
    private Process process;
    private int grpcPort = 8554;

    public VelaEmulator(@NonNull Context context) {
        this.context = context.getApplicationContext();
    }

    /** 私有运行目录：<files>/vela */
    @NonNull
    public File runtimeDir() {
        return new File(context.getFilesDir(), "vela");
    }

    /** 系统镜像目录（私有目录，避免 /sdcard 的存储权限问题）。 */
    @NonNull
    public File imagesDir() {
        return new File(runtimeDir(), "images");
    }

    @NonNull
    public File engineDir() {
        return new File(runtimeDir(), "engine");
    }

    /** vela 引擎的家目录（我们把它指向私有目录）。 */
    @NonNull
    public File homeDir() {
        return new File(runtimeDir(), "home");
    }

    /** AVD 目录：vela 引擎从 $HOME/.vela/vvd 找（不是 ANDROID_AVD_HOME）。 */
    @NonNull
    public File avdHome() {
        return new File(homeDir(), ".vela/vvd");
    }

    public boolean isRuntimeReady() {
        return new File(engineDir(), "qemu/linux-aarch64/qemu-system-armel-headless").exists()
                || new File(engineDir(), "qemu/linux-aarch64/qemu-system-armel-headless.elf").exists();
    }

    public int grpcPort() {
        return grpcPort;
    }

    @Nullable
    public Process process() {
        return process;
    }

    /** 按需下载并铺好全部资产（引擎负载 + 系统镜像）。 */
    public void ensureAssets(@NonNull Progress progress) throws Exception {
        File dir = runtimeDir();
        //noinspection ResultOfMethodCallIgnored
        dir.mkdirs();
        //noinspection ResultOfMethodCallIgnored
        imagesDir().mkdirs();
        for (String[] pair : ASSETS) {
            String name = pair[1];
            /* 引擎已经解包好 + 镜像已就位时，不需要再碰 payload.tar（避免重复下载）。 */
            if ("payload.tar".equals(name) && isRuntimeReady()) {
                progress.onProgress(name, 100);
                continue;
            }
            File target = "payload.tar".equals(name)
                    ? new File(dir, name) : new File(imagesDir(), name);
            long min = "payload.tar".equals(name) ? 50L * 1024 * 1024 : 100L * 1024 * 1024;
            if (target.isFile() && target.length() > min) {
                progress.onProgress(name, 100);
                continue;
            }
            download(name, target, progress);
        }
        // 解包引擎负载（只做一次）
        if (!isRuntimeReady()) {
            extractTar(new File(dir, "payload.tar"), dir);
        }
    }

    private void download(@NonNull String name, @NonNull File target,
                          @NonNull Progress progress) throws Exception {
        java.net.HttpURLConnection conn =
                (java.net.HttpURLConnection) new URL(BASE_URL + name).openConnection();
        conn.setConnectTimeout(15000);
        conn.setReadTimeout(60000);
        long total = conn.getContentLengthLong();
        File part = new File(target.getAbsolutePath() + ".part");
        try (InputStream in = conn.getInputStream(); OutputStream out = new FileOutputStream(part)) {
            byte[] buf = new byte[1 << 16];
            long done = 0;
            int n;
            while ((n = in.read(buf)) > 0) {
                out.write(buf, 0, n);
                done += n;
                if (total > 0) {
                    progress.onProgress(name, (int) (done * 100 / total));
                }
            }
        }
        //noinspection ResultOfMethodCallIgnored
        part.renameTo(target);
    }

    /** 用系统 tar（toybox 自带）解包；失败则抛错，由界面显示。 */
    private void extractTar(@NonNull File tar, @NonNull File into) throws Exception {
        Process p = new ProcessBuilder("/system/bin/tar", "xf", tar.getAbsolutePath(),
                "-C", into.getAbsolutePath()).redirectErrorStream(true).start();
        p.waitFor();
        if (p.exitValue() != 0) {
            throw new IllegalStateException("解包 payload.tar 失败，退出码 " + p.exitValue());
        }
    }

    /**
     * 建立 exec 软链：真身改名 <name>.elf，原路径 -> jniLibs 的 stub。
     */
    public void installExecLinks() throws Exception {
        File nativeDir = new File(context.getApplicationInfo().nativeLibraryDir);
        File stub = new File(nativeDir, "libwgpro_vela_exec_stub.so");
        if (!stub.isFile()) {
            throw new IllegalStateException("缺少 exec stub: " + stub);
        }
        File engine = engineDir();
        /* 引擎按 $ANDROID_SDK_ROOT/emulator/ 找自己的 lib64（含 SwiftShader）。
         * 我们在私有目录里补一个 emulator -> engine 软链，并把 SDK_ROOT 指到 <rt>。 */
        File emuLink = new File(runtimeDir(), "emulator");
        if (!emuLink.exists()) {
            try {
                Os.symlink(engine.getAbsolutePath(), emuLink.getAbsolutePath());
            } catch (Exception ignored) {
            }
        }
        for (String rel : SHIM_BINARIES) {
            File link = new File(engine, rel);
            File backing = new File(engine, rel + ".elf");
            /* 注意：真身第一次就被改名成 <rel>.elf，之后 link 本身不存在 —— 不能因为
             * link 不存在就跳过，否则永远建不出软链接（踩过）。 */
            if (!link.exists() && !backing.exists()) {
                continue;
            }
            if (link.isFile() && !isSymlink(link)) {
                if (!backing.isFile()) {
                    //noinspection ResultOfMethodCallIgnored
                    link.renameTo(backing);
                } else {
                    //noinspection ResultOfMethodCallIgnored
                    link.delete();
                }
            }
            if (isSymlink(link)) {
                //noinspection ResultOfMethodCallIgnored
                link.delete();
            }
            //noinspection ResultOfMethodCallIgnored
            link.getParentFile().mkdirs();
            Os.symlink(stub.getAbsolutePath(), link.getAbsolutePath());
        }
    }

    /**
     * 机型档位（来源：引擎自带 devices.json 的 xiaomi_s4 档，已跑通）。
     * 之前手写 320x480 会让客机按错误分辨率渲染，表现为表盘被放大裁切。
     */
    private static final String SKIN = "xiaomi_s4";
    private static final int LCD_W = 466;
    private static final int LCD_H = 466;
    private static final String LCD_SHAPE = "circle";
    private static final String FLAVOR = "watch";
    private static final int DENSITY = 320;

    /** 铺好 AVD：镜像放进 <avdHome>/<id>.vvd，并写出引擎期望的完整 config.ini / <id>.ini。 */
    public void authorAvd(@NonNull String avdId, @NonNull File imageDir) throws Exception {
        File vvd = new File(avdHome(), avdId + ".vvd");
        //noinspection ResultOfMethodCallIgnored
        vvd.mkdirs();
        // 镜像直接指向已下载目录，不复制（省 1.1GB 与一次拷贝）；同一机型只跑一台实例，可共享。
        File img = imageDir;
        File skinDir = new File(engineDir(), "skins/" + SKIN);
        StringBuilder cfg = new StringBuilder();
        cfg.append("AvdId=").append(avdId).append('\n');
        cfg.append("abi.type=armeabi-v7a\n");
        cfg.append("avd.ini.displayname=").append(avdId).append('\n');
        cfg.append("avd.ini.encoding=UTF-8\n");
        cfg.append("hw.audioInput=yes\n");
        cfg.append("hw.battery=yes\n");
        cfg.append("hw.camera.back=None\n");
        cfg.append("hw.camera.front=None\n");
        cfg.append("hw.cpu.arch=arm\n");
        cfg.append("hw.cpu.ncore=4\n");
        cfg.append("hw.dPad=no\n");
        cfg.append("hw.gps=yes\n");
        cfg.append("hw.gpu.enabled=no\n");
        cfg.append("hw.gpu.mode=host\n");
        cfg.append("hw.initialOrientation=Portrait\n");
        cfg.append("hw.keyboard=yes\n");
        cfg.append("hw.lcd.density=").append(DENSITY).append('\n');
        cfg.append("hw.lcd.shape=").append(LCD_SHAPE).append('\n');
        cfg.append("hw.device.flavor=").append(FLAVOR).append('\n');
        cfg.append("hw.mainKeys=no\n");
        cfg.append("hw.ramSize=2048\n");
        cfg.append("hw.sdCard=no\n");
        cfg.append("hw.sensors.orientation=yes\n");
        cfg.append("hw.sensors.magnetic_field=yes\n");
        cfg.append("hw.sensors.proximity=yes\n");
        cfg.append("hw.sensors.light=yes\n");
        cfg.append("hw.sensors.temperature=yes\n");
        cfg.append("hw.sensors.humidity=yes\n");
        cfg.append("hw.sensors.heart_rate=yes\n");
        cfg.append("hw.accelerometer=yes\n");
        cfg.append("hw.gyroscope=yes\n");
        cfg.append("hw.trackBall=no\n");
        cfg.append("runtime.network.latency=none\n");
        cfg.append("runtime.network.speed=full\n");
        cfg.append("showDeviceFrame=no\n");
        cfg.append("image.sysdir.1=").append(img.getAbsolutePath()).append('\n');
        cfg.append("image.sysdir.2=\n");
        cfg.append("skin.dynamic=yes\n");
        cfg.append("skin.name=").append(SKIN).append('\n');
        cfg.append("skin.path=").append(skinDir.getAbsolutePath()).append('\n');
        cfg.append("ide.image.type=miwear\n");
        cfg.append("hw.lcd.width=").append(LCD_W).append('\n');
        cfg.append("hw.lcd.height=").append(LCD_H).append('\n');
        write(new File(vvd, "config.ini"), cfg.toString());
        write(new File(avdHome(), avdId + ".ini"),
                "path=" + vvd.getAbsolutePath() + "\npath.rel=vvd/" + avdId + ".vvd\n");
        write(new File(vvd, "AVD.conf"), "battery.charge_level=100\nset.pauseAvdWhenMinimized=false\n");
        write(new File(vvd, "emulator-user.ini"), "window.x=0\nwindow.y=0\n");
    }

    /** 启动引擎（无窗口 + gRPC 取帧）。 */
    public void start(@NonNull String avdId, int grpcPort) throws Exception {
        // 模拟器只接受 5554–5584 的**偶数**控制台端口，adb 占用其 +1。
        int adbPort = 5560;
        stop();
        this.grpcPort = grpcPort;
        File engine = engineDir();
        File launcher = new File(engine, "qemu/linux-aarch64/qemu-system-armel-headless");
        List<String> cmd = new ArrayList<>();
        cmd.add(launcher.getAbsolutePath());
        cmd.add("-vela");
        cmd.add("-avd");
        cmd.add(avdId);
        cmd.add("-grpc");
        cmd.add(String.valueOf(grpcPort));
        // 与 AIoT IDE 一样开 adb/控制台端口（5560/5561）：宿主用
        //   adb forward tcp:5561 tcp:5561 && adb connect 127.0.0.1:5561
        // 就能对客机下发 shell 指令。
        cmd.add("-port");
        cmd.add(String.valueOf(adbPort));
        cmd.add("-no-window");
        cmd.add("-gpu");
        cmd.add("swiftshader_indirect");
        cmd.add("-no-audio");
        // 客机（NuttX/Vela）自己的日志：默认只在内核/串口里，不会进外壳 stdout。
        // -show-kernel 把客机内核日志直接打到外壳输出，于是现有日志通道即可显示。
        cmd.add("-show-kernel");
        cmd.add("-qemu");
        cmd.add("-smp");
        cmd.add("2");
        cmd.add("-semihosting");
        ProcessBuilder pb = new ProcessBuilder(cmd);
        pb.directory(engine);
        pb.redirectErrorStream(true);
        File home = homeDir();
        File tmp = new File(home, "tmp");
        //noinspection ResultOfMethodCallIgnored
        home.mkdirs();
        //noinspection ResultOfMethodCallIgnored
        tmp.mkdirs();
        //noinspection ResultOfMethodCallIgnored
        avdHome().mkdirs();
        /* 引擎定位 vvd / 临时目录依赖这些环境变量（缺一个就报 unknown virtual device name）。 */
        java.util.Map<String, String> e = pb.environment();
        e.put(ENV_DIR, runtimeDir().getAbsolutePath());
        e.put("HOME", home.getAbsolutePath());
        e.put("ANDROID_SDK_ROOT", runtimeDir().getAbsolutePath());
        e.put("ANDROID_EMULATOR_HOME", runtimeDir().getAbsolutePath());
        e.put("ANDROID_AVD_HOME", avdHome().getAbsolutePath());
        e.put("ANDROID_HOME", runtimeDir().getAbsolutePath());
        e.put("VELA_HOME", new File(home, ".vela").getAbsolutePath());
        e.put("TMPDIR", tmp.getAbsolutePath());
        e.put("ANDROID_TMP", tmp.getAbsolutePath());
        e.put("QEMU_DISABLE_SNAPSHOT", "1");
        e.put("ANDROID_EMULATOR_SKIP_ADB_CHECKS", "1");
        /* 关键：不设这个，引擎会在 GL 初始化阶段 fatal（OpenGLES emulation failed）。 */
        e.put("EMULATOR_DISABLE_GPU", "1");
        /* 关键：引擎靠这个环境变量认自己的安装目录（否则报
         * "Invalid executable directory: <unknown-launcher-dir>"，
         * 进而找不到 lib64/gles_swiftshader → OpenGLES 初始化失败）。 */
        e.put("ANDROID_EMULATOR_LAUNCHER_DIR", engineDir().getAbsolutePath());
        /* libOpenglRender.so 需要 X11 符号（XOpenDisplay 等），负载里自带了桩
         * libStubXlib.so，用 LD_PRELOAD 先挂上（否则 symbol lookup error）。 */
        File xstub = new File(engineDir(), "lib64/libStubXlib.so");
        if (xstub.isFile()) {
            e.put("LD_PRELOAD", xstub.getAbsolutePath());
        }
        e.put("LC_ALL", "C");
        process = pb.start();
        final Process started = process;
        /* 必须持续消费引擎的 stdout/stderr：否则管道写满会把引擎阻塞住（踩过），
         * 同时日志丢了就没法排错。 */
        Thread pump = new Thread(() -> {
            try (java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(started.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    android.util.Log.i("VelaEngine", line);
                    LineListener l = lineListener;
                    if (l != null) {
                        l.onLine(line);
                    }
                }
            } catch (Exception ignored) {
            } finally {
                try {
                    int code = started.waitFor();
                    android.util.Log.i("VelaEngine", "引擎退出，code=" + code);
                } catch (Exception ignored) {
                }
                Runnable callback = exitListener;
                if (callback != null) {
                    callback.run();
                }
            }
        }, "vela-engine-output");
        pump.setDaemon(true);
        pump.start();
    }

    public void stop() {
        Process p = process;
        process = null;
        if (p != null) {
            p.destroy();
        }
    }

    // ---------------------------------------------------------------- helpers

    private static boolean isSymlink(@NonNull File file) {
        try {
            return !file.getCanonicalPath().equals(file.getAbsolutePath());
        } catch (Exception error) {
            return false;
        }
    }

    private static void copy(@NonNull File src, @NonNull File dst) throws Exception {
        try (InputStream in = new java.io.FileInputStream(src);
             OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[1 << 16];
            int n;
            while ((n = in.read(buf)) > 0) {
                out.write(buf, 0, n);
            }
        }
    }

    private static void write(@NonNull File file, @NonNull String text) throws Exception {
        try (OutputStream out = new FileOutputStream(file)) {
            out.write(text.getBytes("UTF-8"));
        }
    }

    /** 引擎输出行回调（界面实时显示）。 */
    public interface LineListener {
        void onLine(@NonNull String line);
    }

    @Nullable
    private LineListener lineListener;
    @Nullable
    private Runnable exitListener;

    /** 引擎进程结束（正常退出或被系统杀掉）时回调，用于实时更新设备状态。 */
    public void setExitListener(@Nullable Runnable listener) {
        exitListener = listener;
    }

    public void setLineListener(@Nullable LineListener listener) {
        lineListener = listener;
    }

    /** 下载进度回调。 */
    public interface Progress {
        void onProgress(@NonNull String name, int percent);
    }
}
