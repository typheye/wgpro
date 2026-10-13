package com.typheye.wgpro.core.sandbox;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * proot 沙盒：在 App 私有目录里跑一个完整 Linux 用户态（Alpine），可用来跑 QEMU 或任意任务。
 *
 * <p><b>为什么必须是 proot</b>：Android 10+ 禁止从应用数据目录 exec（实测 {@code error=13}），
 * Termux 能直接跑是因为它 targetSdk 停在 28。我们 targetSdk 36，所以：</p>
 * <ul>
 *   <li><b>可执行文件放 jniLibs</b>（{@code nativeLibraryDir} 允许 exec，已实测）：
 *       {@code libproot.so} + {@code libproot-loader.so}；</li>
 *   <li>proot 通过 <b>ptrace</b> 代执行 rootfs 里的程序，从而绕过 exec 限制；</li>
 *   <li>依赖库与 rootfs 放私有目录（只需 mmap，不需要 exec），用 {@code LD_LIBRARY_PATH} 指过去。</li>
 * </ul>
 *
 * <p>库一律用 <b>Termux 官方预编译产物、保持原始文件名与 ELF 哈希</b>，绝不做 patchelf
 * （patchelf 会破坏 DT_HASH/DT_GNU_HASH，导致 Android 链接器报
 * "empty/missing DT_HASH … new hash type from the future?"）。</p>
 */
public final class SandboxManager {

    public interface Listener {
        void onOutput(@NonNull String line);
        void onExit(int exitCode, @NonNull String summary);
    }

    private static final String MARKER = ".prepared";
    /** 沙盒目录版本：rootfs/依赖变更时必须 +1，强制重新下载解包。 */
    private static final int SANDBOX_VERSION = 4;
    /** rootfs 归档下载地址（首次使用时拉取，避免把 28MB 打进 APK）。 */
    /** rootfs 里已预置 bes-QEMU 引擎（/opt/wgpro/）：沙盒起来就能直接跑模拟器。 */
    private static final String ROOTFS_URL = "https://res.typheye.cn/sandbox/ubuntu-%s.tar.gz";

    /** 进度回调（下载百分比、解包阶段等）。 */
    public interface Progress {
        void onStep(@NonNull String message);
    }

    private SandboxManager() { }

    /** 当前 ABI 对应的 assets 子目录（与 jniLibs/abiFilters 命名一致）。 */
    @NonNull
    public static String abiDir() {
        for (String abi : Build.SUPPORTED_ABIS) {
            if ("arm64-v8a".equals(abi) || "x86_64".equals(abi)) return abi;
        }
        return "arm64-v8a";
    }

    @NonNull
    public static File sandboxDir(@NonNull Context context) {
        return new File(context.getFilesDir(), "sandbox/v" + SANDBOX_VERSION);
    }

    @NonNull
    public static File rootfsDir(@NonNull Context context) {
        return new File(sandboxDir(context), "rootfs");
    }

    @NonNull
    public static File prootBinary(@NonNull Context context) {
        return new File(context.getApplicationInfo().nativeLibraryDir, "libproot.so");
    }

    @NonNull
    public static File prootLoader(@NonNull Context context) {
        return new File(context.getApplicationInfo().nativeLibraryDir, "libproot-loader.so");
    }

    /** GNU tar（Termux 版）也放在 jniLibs：解包 rootfs 需要它处理绝对符号链接与硬链接。 */
    @NonNull
    public static File tarBinary(@NonNull Context context) {
        return new File(context.getApplicationInfo().nativeLibraryDir, "libtar.so");
    }

    /** rootfs 归档的远端 ABI 名称（aarch64 / x86_64）。 */
    @NonNull
    private static String archName() {
        return "arm64-v8a".equals(abiDir()) ? "arm64" : "x86_64";
    }

    public static boolean isPrepared(@NonNull Context context) {
        return new File(rootfsDir(context), MARKER).isFile()
                && engineInRootfs(context).isFile()
                && prootBinary(context).isFile() && prootLoader(context).isFile();
    }

    /** rootfs 内预置的 bes-QEMU 引擎路径。 */
    @NonNull
    public static File engineInRootfs(@NonNull Context context) {
        return new File(rootfsDir(context), "opt/wgpro/qemu-system-arm");
    }

    /** 释放依赖库并解开 rootfs（只需一次）。返回可执行的 proot 路径。 */
    @NonNull
    public static File prepare(@NonNull Context context) throws Exception {
        return prepare(context, message -> { });
    }

    /** 释放依赖库并解开 rootfs（带进度）；rootfs 内必须带上预置引擎，否则重新下载解包。 */
    @NonNull
    public static File prepare(@NonNull Context context, @NonNull Progress progress) throws Exception {
        File abiDir = new File(sandboxDir(context), abiDir());
        //noinspection ResultOfMethodCallIgnored
        abiDir.mkdirs();
        String assetDir = "sandbox/" + abiDir();
        String[] names = context.getAssets().list(assetDir);
        if (names == null) names = new String[0];
        File tarball = null;
        for (String name : names) {
            File out = new File(abiDir, name);
            try (InputStream in = context.getAssets().open(assetDir + "/" + name)) {
                byte[] data = readAll(in);
                if (!out.isFile() || out.length() != data.length) {
                    try (OutputStream os = new java.io.FileOutputStream(out)) {
                        os.write(data);
                    }
                }
            }
            if (name.endsWith(".tar.gz")) tarball = out;
        }
        File rootfs = rootfsDir(context);
        if (new File(rootfs, MARKER).isFile() && !engineInRootfs(context).isFile()) {
            // 旧缓存里没有引擎（rootfs 换过版本）→ 清标记重新下载解包
            progress.onStep("检测到旧 rootfs（缺少引擎），准备重新下载…");
            //noinspection ResultOfMethodCallIgnored
            new File(rootfs, MARKER).delete();
            File stale = new File(sandboxDir(context), "rootfs.tar.gz");
            //noinspection ResultOfMethodCallIgnored
            stale.delete();
        }
        if (!new File(rootfs, MARKER).isFile()) {
            //noinspection ResultOfMethodCallIgnored
            rootfs.mkdirs();
            File archive = new File(sandboxDir(context), "rootfs.tar.gz");
            if (!archive.isFile() || archive.length() < 1024 * 1024) {
                progress.onStep("开始下载 rootfs（约 40MB，含引擎）…");
                download(ROOTFS_URL.replace("%s", archName()), archive, progress);
            } else {
                progress.onStep("复用已下载的 rootfs 归档（"
                        + (archive.length() / 1048576) + " MB）");
            }
            // Android 不允许硬链接 → 必须用 proot --link2symlink 包住 tar（proot-distro 同款做法）
            progress.onStep("解包 rootfs（手机上经由 proot 逐文件进行，可能需要几分钟）…");
            String log = runExtraction(context, archive, rootfs, progress);
            // GNU tar 在 Android 上常对硬链接/特殊文件报 warning 并以非 0 退出，
            // 只要关键文件在位就认为可用（硬链接由 proot --link2symlink 处理）。
            boolean usable = new File(rootfs, "bin/sh").isFile()
                    || new File(rootfs, "usr/bin/env").isFile();
            if (!usable) {
                throw new IllegalStateException("解包 rootfs 失败：" + tail(log));
            }
            //noinspection ResultOfMethodCallIgnored
            new File(rootfs, MARKER).createNewFile();
            if (!log.trim().isEmpty()) android.util.Log.i("Sandbox", "解包日志：\n" + tail(log));
        }
        // 引擎用 Java 直接落盘（不走 tar，杜绝解包中途失败）：
        //   - 引擎二进制：从 jniLibs 的 libqemu-system-arm.so 复制
        //   - 依赖库：来自 assets/engine/（Termux 原版库）
        //   - 板级配置/合成客体：来自 assets/
        writeEngineIntoRootfs(context, progress);
        return prootBinary(context);
    }

    /** 把引擎（二进制 + 依赖库 + 配置 + 客体）写进 rootfs 的 /opt/wgpro。 */
    private static void writeEngineIntoRootfs(@NonNull Context context,
                                              @NonNull Progress progress) throws Exception {
        File engineDir = new File(rootfsDir(context), "opt/wgpro");
        File libDir = new File(engineDir, "lib");
        //noinspection ResultOfMethodCallIgnored
        libDir.mkdirs();
        progress.onStep("写入引擎到 rootfs…");
        // 1) 二进制：jniLibs 里的 libqemu-system-arm.so
        File binary = new File(context.getApplicationInfo().nativeLibraryDir, "libqemu-system-arm.so");
        if (!binary.isFile()) throw new IllegalStateException("缺少引擎二进制（jniLibs）");
        copyFile(binary, new File(engineDir, "qemu-system-arm"), true);
        // 2) 依赖库：assets/engine/
        String[] libs = context.getAssets().list("engine");
        if (libs != null) {
            for (String name : libs) {
                try (InputStream in = context.getAssets().open("engine/" + name)) {
                    byte[] data = readAll(in);
                    writeBytes(new File(libDir, name), data);
                }
            }
        }
        // 3) 板级配置 + 合成客体
        copyAsset(context, "mock_hardware.json", new File(engineDir, "hardware.json"));
        copyAsset(context, "mock_guest.bin", new File(engineDir, "guest.bin"));
        // 4) 便捷启动脚本
        String run = "#!/bin/sh\n"
                + "export LD_LIBRARY_PATH=/opt/wgpro/lib\n"
                + "exec /opt/wgpro/qemu-system-arm"
                + " -M bes1503,hardware-config=/opt/wgpro/hardware.json"
                + " -kernel \"${1:-/opt/wgpro/guest.bin}\""
                + " -vnc 127.0.0.1:0 -serial null\n";
        writeBytes(new File(engineDir, "run.sh"), run.getBytes("UTF-8"));
        //noinspection ResultOfMethodCallIgnored
        new File(engineDir, "run.sh").setExecutable(true, false);
        //noinspection ResultOfMethodCallIgnored
        new File(engineDir, "qemu-system-arm").setExecutable(true, false);
        boolean ok = engineInRootfs(context).isFile();
        progress.onStep(ok ? "引擎就位 ✓" : "⚠ 引擎写入异常");
        if (!ok) {
            throw new IllegalStateException("引擎写入失败："
                    + engineInRootfs(context).getAbsolutePath());
        }
    }

    private static void copyFile(@NonNull File source, @NonNull File target,
                                 boolean executable) throws Exception {
        try (InputStream in = new java.io.FileInputStream(source)) {
            writeBytes(target, readAll(in));
        }
        if (executable) {
            //noinspection ResultOfMethodCallIgnored
            target.setExecutable(true, false);
        }
    }

    private static void copyAsset(@NonNull Context context, @NonNull String assetName,
                                  @NonNull File target) throws Exception {
        try (InputStream in = context.getAssets().open(assetName)) {
            writeBytes(target, readAll(in));
        }
    }

    private static void writeBytes(@NonNull File target, @NonNull byte[] data) throws Exception {
        File parent = target.getParentFile();
        if (parent != null) {
            //noinspection ResultOfMethodCallIgnored
            parent.mkdirs();
        }
        try (OutputStream out = new java.io.FileOutputStream(target)) {
            out.write(data);
        }
    }

    /** proot 运行所需环境变量（loader 直接从 jniLibs 执行，绕开 Android 的 exec 限制）。 */
    private static void applyProotEnv(@NonNull Context context,
                                      @NonNull java.util.Map<String, String> env) {
        //noinspection ResultOfMethodCallIgnored
        new File(sandboxDir(context), "tmp").mkdirs();
        env.put("PROOT_LOADER", prootLoader(context).getAbsolutePath());
        env.put("PROOT_TMP_DIR", new File(sandboxDir(context), "tmp").getAbsolutePath());
        env.put("PROOT_NO_SECCOMP", "1");
        env.put("LD_LIBRARY_PATH", new File(sandboxDir(context), abiDir()).getAbsolutePath()
                + ":" + context.getApplicationInfo().nativeLibraryDir);
        env.put("HOME", "/root");
        env.put("PATH", "/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin");
        env.put("TERM", "xterm-256color");
    }

    /** 取日志尾部（用于错误提示，限制长度）。 */
    @NonNull
    private static String tail(@NonNull String text) {
        String trimmed = text.trim();
        return trimmed.length() <= 600 ? trimmed : trimmed.substring(trimmed.length() - 600);
    }

    /** 下载文件（带百分比回调）。 */
    private static void download(@NonNull String url, @NonNull File target,
                                 @NonNull Progress progress) throws Exception {
        java.net.HttpURLConnection connection =
                (java.net.HttpURLConnection) new java.net.URL(url).openConnection();
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(60000);
        connection.setInstanceFollowRedirects(true);
        int expected = connection.getContentLength();
        try (InputStream in = connection.getInputStream();
             OutputStream out = new java.io.FileOutputStream(target)) {
            byte[] buffer = new byte[1 << 16];
            int read;
            long total = 0;
            int lastPercent = -1;
            while ((read = in.read(buffer)) > 0) {
                out.write(buffer, 0, read);
                total += read;
                if (expected > 0) {
                    int percent = (int) (total * 100 / expected);
                    if (percent / 5 != lastPercent / 5) {
                        lastPercent = percent;
                        progress.onStep("下载中 " + percent + "%（"
                                + (total / 1048576) + "/" + (expected / 1048576) + " MB）");
                    }
                }
            }
            progress.onStep("下载完成（" + (total / 1048576) + " MB）");
        }
    }

    /** 用 proot --link2symlink + GNU tar 解包 rootfs，返回日志文本。 */
    @NonNull
    private static String runExtraction(@NonNull Context context, @NonNull File tarball,
                                        @NonNull File target, @NonNull Progress progress)
            throws Exception {
        List<String> args = new ArrayList<>();
        args.add(prootBinary(context).getAbsolutePath());
        args.add("--link2symlink");
        args.add("-0");
        args.add("-r");
        args.add("/");
        args.add(tarBinary(context).getAbsolutePath());
        args.add("xzf");
        args.add(tarball.getAbsolutePath());
        args.add("-C");
        args.add(target.getAbsolutePath());
        ProcessBuilder builder = new ProcessBuilder(args).redirectErrorStream(true);
        applyProotEnv(context, builder.environment());
        Process process = builder.start();
        StringBuilder log = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                log.append(line).append('\n');
                if (log.length() > 4000) log.delete(0, log.length() - 2000);
                if (!line.trim().isEmpty()) progress.onStep("解包：" + line.trim());
            }
        }
        int code = process.waitFor();
        android.util.Log.i("Sandbox", "extract exit=" + code + "\n" + log);
        return "exit=" + code + "\n" + log;
    }

    private static byte[] readAll(@NonNull InputStream in) throws Exception {
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int read;
        while ((read = in.read(buffer)) > 0) out.write(buffer, 0, read);
        return out.toByteArray();
    }
}
