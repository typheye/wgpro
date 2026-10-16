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

    /** 机型标识：运行目录按机型隔离（不一定是 Vela 设备，所以目录名不叫 vela）。 */
    @NonNull
    private final String modelId;

    /** 设备 id：运行目录按**设备**独立（同机型多台也各一套，引擎/镜像/AVD/数据全独立）。 */
    @NonNull
    private final String deviceId;

    public VelaEmulator(@NonNull Context context) {
        this(context, "", "");
    }

    public VelaEmulator(@NonNull Context context, @NonNull String modelId) {
        this(context, modelId, "");
    }

    public VelaEmulator(@NonNull Context context, @NonNull String modelId,
                        @NonNull String deviceId) {
        this.context = context.getApplicationContext();
        this.modelId = modelId == null ? "" : modelId.trim();
        this.deviceId = deviceId == null ? "" : deviceId.trim();
    }

    /**
     * 设备 id → 引擎能接受的 **AVD 名**。
     *
     * <p>设备 id 形如 {@code virtual:<机型>:<4位随机>}，里面的冒号是设备库主键的语法，
     * 但引擎对 AVD 名做了字符白名单校验：直接拿设备 id 当 AVD 名会得到
     * {@code ERROR | virtual device name contains invalid characters} 然后立刻退出
     * （表现为"进了模拟页但引擎一行日志都没有/秒退"）——**踩过**。
     * 这里与运行目录名用同一套转义规则（非 {@code [A-Za-z0-9._-]} 一律换成下划线）。</p>
     */
    @NonNull
    public static String avdNameOf(@Nullable String raw) {
        String value = raw == null ? "" : raw.trim();
        return value.replaceAll("[^A-Za-z0-9._-]", "_");
    }

    /**
     * 私有运行目录：<files>/runtime/<设备id>（**一台设备一套完整目录**）。
     * 设备 id 缺失（老入口/深链）时退回按机型，保证历史数据还能找到。
     */
    @NonNull
    public File runtimeDir() {
        String raw = deviceId.isEmpty() ? modelId : deviceId;
        String name = raw.isEmpty() ? "_default" : raw.replaceAll("[^A-Za-z0-9._-]", "_");
        return new File(new File(context.getFilesDir(), "runtime"), name);
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

    /** 客机调试端口（127.0.0.1:<port> → 客机 10.0.2.15:101）。 */
    public int debugPort() {
        return debugPort;
    }

    /** 本实例绑定的**设备 id**（实例键；管理器兼容入口要用它）。 */
    @NonNull
    public String deviceId() {
        return deviceId;
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
                    // 改名失败（例如文件正被占用）必须**兜底复制**，否则真身会被下面当"多余副本"删掉，
                    // 只剩一条软链 → 引擎 open 时 ENOENT（踩过）。
                    if (!link.renameTo(backing)) {
                        copyFile(link, backing);
                        //noinspection ResultOfMethodCallIgnored
                        link.delete();
                    }
                } else {
                    //noinspection ResultOfMethodCallIgnored
                    link.delete();
                }
            }
            // 真身不在就别建软链：留个能看出来的"缺文件"，比断链后 ENOENT 好定位
            if (!backing.isFile()) {
                continue;
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
    /** 文件复制（引擎真身改名失败时的兜底）。 */
    private static void copyFile(@NonNull File from, @NonNull File to) throws Exception {
        try (java.io.InputStream in = new java.io.FileInputStream(from);
             java.io.OutputStream out = new java.io.FileOutputStream(to)) {
            byte[] buffer = new byte[256 * 1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        }
    }

    /** 当前设备使用的皮肤：决定"这台模拟器模拟成哪款设备"（镜像包 engine/skins/<name>）。 */
    private String skinName = SKIN;

    /** 该皮肤的品类（layout 的 props.flavor：watch / band / smartspeaker），决定 qemu 参数差异。 */
    private String skinFlavor = FLAVOR;

    /**
     * 镜像档（AIoT IDE 的 {@code ide.image.type}）：决定跑**哪一套系统镜像**。
     *
     * <p>包内把它放在 {@code images/<档>/}（如 {@code images/vela-pre-4.0/}）时就按档指向；
     * 老包只有一套平铺 {@code images/} 时保持原样（全部设备共用，会出现"手环跑手表镜像"）。</p>
     */
    private String imageType = "";

    /**
     * 客机调试端口：{@code 127.0.0.1:<debugPort>} 转发到客机 {@code 10.0.2.15:101}。
     *
     * <p>与 AIoT IDE 同一套：IDE 的 {@code static/debugger_ip.cfg} 就是 {@code 10.0.2.15:101}，
     * 启动时用 {@code -network-user-mode-options hostfwd=tcp:127.0.0.1:<port>-10.0.2.15:101}
     * 把它挂出来，快应用 JS 调试器就接在这个端口上。</p>
     */
    private int debugPort = 10055;

    /** 设置皮肤；传空则保持默认（xiaomi_s4）。 */
    public void setSkin(@Nullable String skin) {
        if (skin != null && !skin.trim().isEmpty()) {
            this.skinName = skin.trim();
        }
    }

    @NonNull
    public String skin() {
        return skinName;
    }

    /** 设置镜像档（如 {@code vela-pre-4.0}）；传空表示"包内默认那一套"。 */
    public void setImageType(@Nullable String type) {
        this.imageType = type == null ? "" : type.trim();
    }

    @NonNull
    public String imageType() {
        return imageType;
    }

    /** 本设备的 AVD 目录（{@code <avdHome>/<转义 id>.vvd}）。 */
    @NonNull
    public File avdDir(@NonNull String avdId) {
        return new File(avdHome(), avdNameOf(avdId) + ".vvd");
    }

    /** 某个镜像档的目录：{@code images/<档>/}。 */
    @NonNull
    public File imageDirFor(@NonNull String type) {
        return new File(imagesDir(), type);
    }

    /** 该镜像档的文件是否真的在（没有就不能声称用这一档）。 */
    public boolean hasImageSet(@Nullable String type) {
        if (type == null || type.trim().isEmpty()) {
            return false;
        }
        File dir = imageDirFor(type.trim());
        return new File(dir, "vela_data.bin").isFile() || new File(dir, "nuttx").isFile();
    }

    /**
     * 读**本设备 AVD** 的 {@code ide.image.type}（取不到返回空串）。
     *
     * <p>老版本写死的 {@code miwear} 视为"没写"——它对应的是平铺 images，需要按皮肤品类重新判定档位。</p>
     */
    @NonNull
    public String imageTypeOfAvd(@NonNull String avdId) {
        File config = new File(new File(avdHome(), avdNameOf(avdId) + ".vvd"), "config.ini");
        try {
            for (String line : java.nio.file.Files.readAllLines(config.toPath(),
                    java.nio.charset.StandardCharsets.UTF_8)) {
                String item = line.trim();
                if (item.startsWith("ide.image.type=")) {
                    String value = item.substring("ide.image.type=".length()).trim();
                    if (!value.isEmpty() && !"miwear".equals(value)) {
                        return value;
                    }
                }
            }
        } catch (Exception ignored) {
        }
        return "";
    }

    /**
     * 皮肤品类 → 默认镜像档（**架构级映射**，不是机型参数；包内 {@code devices[].image} 可覆盖）。
     *
     * <p>对齐 AIoT IDE：手环用 PRE 档（quickapp 框架，能按屏幕自适应出正确的手环界面）、
     * 音箱用 openvela smartspeaker 档；手表用包内默认的 watch5 档。</p>
     */
    @NonNull
    public static String imageTypeForFlavor(@Nullable String flavor) {
        return imageTypeForFlavor(flavor, 0, 0);
    }

    /**
     * 皮肤品类 + 屏幕尺寸 → 默认镜像档（**架构级映射**，包内 {@code devices[].image} 可覆盖）。
     *
     * <p>依据是 AIoT IDE 对镜像的说明原文：{@code vela-miwear-watch-5.0} **带表盘、不可自定义尺寸**，
     * 只能用在它自己的 466×466；其它尺寸（Redmi Watch 432×514、各种手环）必须用
     * {@code vela-watch-5.0}（**不带表盘、可自定义尺寸**），否则就是"方屏里塞圆表盘"的错画面。</p>
     */
    @NonNull
    public static String imageTypeForFlavor(@Nullable String flavor, int screenWidth,
                                            int screenHeight) {
        if (flavor == null) {
            return "";
        }
        switch (flavor) {
            case "band":
                return VelaGuest.IMAGE_VELA_WATCH5;
            case "watch":
                return (screenWidth == 466 && screenHeight == 466)
                        ? VelaGuest.IMAGE_MIWEAR_WATCH5 : VelaGuest.IMAGE_VELA_WATCH5;
            case "smartspeaker":
                return VelaGuest.IMAGE_SMARTSPEAKER;
            default:
                return "";
        }
    }

    /** 镜像包里可用的皮肤：engine/skins/<name>/layout 存在才算（顺序按名字，便于稳定展示）。 */
    @NonNull
    public static java.util.List<String> availableSkins(@NonNull File engineDir) {
        java.util.List<String> result = new java.util.ArrayList<>();
        File[] children = new File(engineDir, "skins").listFiles();
        if (children == null) {
            return result;
        }
        java.util.Arrays.sort(children, (left, right) -> left.getName().compareTo(right.getName()));
        for (File child : children) {
            if (child.isDirectory() && new File(child, "layout").isFile()) {
                result.add(child.getName());
            }
        }
        return result;
    }

    /*
     * 这里原来有一份「皮肤 → 展示名」的硬编码表（skinLabel）。已**整体删除**：
     * 设备展示名是**清单数据**，只能由镜像包的 config.json（devices[].name）提供，
     * App 里不再内置任何机型名（改机型 = 改包，不改码）。
     * 包里没写名字时退回设备代号。
     */

    /**
     * 读**本设备 AVD** 的 config.ini，取当前皮肤名（取不到就返回默认皮肤）。
     * 皮肤是创建时按用户选择写进去的，这里以它为准，不能再用常量。
     */
    @NonNull
    public String skinOfAvd(@NonNull String avdId) {
        File config = new File(new File(avdHome(), avdNameOf(avdId) + ".vvd"), "config.ini");
        try {
            for (String line : java.nio.file.Files.readAllLines(config.toPath(),
                    java.nio.charset.StandardCharsets.UTF_8)) {
                String item = line.trim();
                if (item.startsWith("skin.name=")) {
                    String value = item.substring("skin.name=".length()).trim();
                    if (!value.isEmpty()) return value;
                }
            }
        } catch (Exception ignored) {
        }
        // 取不到就返回空串：**不要退回默认皮肤**（那会把别的设备画成手表外壳），
        // 由调用方按"没有皮肤"处理（只显示画面，不画外壳）。
        return "";
    }

    /**
     * 皮肤几何：读 {@code engine/skins/<skin>/layout}，返回
     * {@code {canvasW, canvasH, screenW, screenH, screenX, screenY, cornerRadius}}。
     *
     * <p>layout 结构（实测）：
     * <pre>
     * parts { device { display { width 466 height 466 corner_radius 233 } } }
     * layouts { portrait { width 601 height 973
     *                      part2 { name device x 65 y 253 } } }
     * </pre>
     * 解析失败退回 xiaomi_s4 的出厂值（保证不崩、不跑偏）。</p>
     */
    @NonNull
    public static int[] skinGeometry(@NonNull File skinDir) {
        // 全 0 = 没有可用的皮肤几何：调用方据此"不画外壳"，不要给 xiaomi_s4 的出厂值兜底
        int[] result = {0, 0, 0, 0, 0, 0, 0};
        if (skinDir == null || !new File(skinDir, "layout").isFile()) {
            return result;
        }
        try {
            String text = new String(java.nio.file.Files.readAllBytes(
                    new File(skinDir, "layout").toPath()),
                    java.nio.charset.StandardCharsets.UTF_8);
            java.util.regex.Matcher canvas = java.util.regex.Pattern
                    .compile("layouts[\\s\\S]*?portrait[\\s\\S]*?width\\s+(\\d+)"
                            + "[\\s\\S]*?height\\s+(\\d+)").matcher(text);
            if (canvas.find()) {
                result[0] = Integer.parseInt(canvas.group(1));
                result[1] = Integer.parseInt(canvas.group(2));
            }
            java.util.regex.Matcher display = java.util.regex.Pattern
                    .compile("display[\\s\\S]*?width\\s+(\\d+)[\\s\\S]*?height\\s+(\\d+)"
                            + "[\\s\\S]*?corner_radius\\s+(\\d+)").matcher(text);
            if (display.find()) {
                result[2] = Integer.parseInt(display.group(1));
                result[3] = Integer.parseInt(display.group(2));
                result[6] = Integer.parseInt(display.group(3));
            }
            java.util.regex.Matcher part = java.util.regex.Pattern
                    .compile("name\\s+device\\s+x\\s+(-?\\d+)\\s+y\\s+(-?\\d+)").matcher(text);
            if (part.find()) {
                result[4] = Integer.parseInt(part.group(1));
                result[5] = Integer.parseInt(part.group(2));
            }
        } catch (Exception ignored) {
        }
        return result;
    }

    /**
     * 皮肤属性：{@code {density, shape, flavor}}（读 layout 的 props 段）。
     *
     * <p>手环的 layout 是 {@code props { shape pill-shaped density 320 flavor band }}，
     * 手表是 {@code shape circle … flavor watch} —— 原来这两项写死成 circle/watch，
     * 建手环也会按手表渲染。</p>
     */
    @NonNull
    public static String[] skinProps(@NonNull File skinDir) {
        String[] result = {String.valueOf(DENSITY), LCD_SHAPE, FLAVOR};
        if (skinDir == null) {
            return result;
        }
        try {
            String text = new String(java.nio.file.Files.readAllBytes(
                    new File(skinDir, "layout").toPath()),
                    java.nio.charset.StandardCharsets.UTF_8);
            java.util.regex.Matcher props = java.util.regex.Pattern.compile(
                    "props\\s*\\{[\\s\\S]*?shape\\s+(\\S+)[\\s\\S]*?"
                            + "density\\s+(\\d+)[\\s\\S]*?flavor\\s+(\\S+)").matcher(text);
            if (props.find()) {
                result[1] = props.group(1);
                result[0] = props.group(2);
                result[2] = props.group(3);
            }
        } catch (Exception ignored) {
        }
        return result;
    }

    /** 读皮肤 layout 的屏幕尺寸（protobuf 文本：display { width N height N }）。 */
    private int[] skinDisplay(@Nullable File skinDir) {
        int[] fallback = {LCD_W, LCD_H};
        if (skinDir == null) {
            return fallback;
        }
        try {
            String text = new String(java.nio.file.Files.readAllBytes(
                    new File(skinDir, "layout").toPath()),
                    java.nio.charset.StandardCharsets.UTF_8);
            java.util.regex.Matcher matcher = java.util.regex.Pattern
                    .compile("width\\s+(\\d+)[\\s\\S]*?height\\s+(\\d+)").matcher(text);
            if (matcher.find()) {
                int width = Integer.parseInt(matcher.group(1));
                int height = Integer.parseInt(matcher.group(2));
                if (width > 0 && height > 0) {
                    return new int[]{width, height};
                }
            }
        } catch (Exception ignored) {
        }
        return fallback;
    }

    public void authorAvd(@NonNull String avdId, @NonNull File imageDir) throws Exception {
        // ★ 引擎只接受字符白名单内的 AVD 名：设备 id 里的冒号必须转义，
        //   否则引擎 "virtual device name contains invalid characters" 直接退出（踩过）。
        final String safeId = avdNameOf(avdId);
        // 顺手清掉"用原始设备 id 当 AVD 名"时留下的坏目录（同目录下带冒号的那份）
        if (!safeId.equals(avdId)) {
            deleteQuietly(new File(avdHome(), avdId + ".vvd"));
            deleteQuietly(new File(avdHome(), avdId + ".ini"));
        }
        File vvd = new File(avdHome(), safeId + ".vvd");
        //noinspection ResultOfMethodCallIgnored
        vvd.mkdirs();
        // 每次启动都重写 config.ini：镜像/数据分区路径必须与当前设备目录一致，
        // 旧配置留着会把引擎指到别的设备目录（串扰）。
        // 镜像直接指向已下载目录，不复制（省 1.1GB 与一次拷贝）。
        File img = imageDir;
        // 分档镜像：包内 images/<档>/ 存在就指向它（IDE 的 image.sysdir.1 就是这么分档的）
        if (!imageType.isEmpty()) {
            File typed = new File(imageDir, imageType);
            if (new File(typed, "vela_data.bin").isFile() || new File(typed, "nuttx").isFile()) {
                img = typed;
            }
        }
        File skinDir = new File(engineDir(), "skins/" + skinName);
        int[] display = skinDisplay(skinDir);
        String[] skinProps = skinProps(skinDir);
        // 记下品类：start() 里音箱档要加 virtio-gpu 设备（对齐 AIoT IDE）
        this.skinFlavor = skinProps[2];
        StringBuilder cfg = new StringBuilder();
        cfg.append("AvdId=").append(safeId).append('\n');
        cfg.append("abi.type=armeabi-v7a\n");
        cfg.append("avd.ini.displayname=").append(safeId).append('\n');
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
        // 密度/形状/品类都按**该皮肤**的 props 写（手环 pill-shaped/band，手表 circle/watch）
        cfg.append("hw.lcd.density=").append(skinProps[0]).append('\n');
        cfg.append("hw.lcd.shape=").append(skinProps[1]).append('\n');
        cfg.append("hw.device.flavor=").append(skinProps[2]).append('\n');
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
        // 数据分区：引擎启动时要 AVD 里的 userdata-qemu.img；没有初始镜像就会
        // "Could not open ... userdata-qemu.img: No such file or directory" 而起不来。
        // 用客机数据镜像 vela_data.bin 做初始镜像，引擎会自己拷出 userdata-qemu.img。
        File dataImg = new File(img, "vela_data.bin");
        if (dataImg.isFile()) {
            cfg.append("disk.dataPartition.initPath=").append(dataImg.getAbsolutePath()).append('\n');
            cfg.append("disk.dataPartition.path=").append(new File(vvd, "userdata-qemu.img")).append('\n');
            cfg.append("disk.dataPartition.size=").append(dataImg.length()).append('\n');
        }
        cfg.append("image.sysdir.2=\n");
        cfg.append("skin.dynamic=yes\n");
        cfg.append("skin.name=").append(skinName).append('\n');
        cfg.append("skin.path=").append(skinDir.getAbsolutePath()).append('\n');
        cfg.append("ide.image.type=").append(imageType.isEmpty() ? "miwear" : imageType).append('\n');
        cfg.append("hw.lcd.width=").append(display[0]).append('\n');
        cfg.append("hw.lcd.height=").append(display[1]).append('\n');
        write(new File(vvd, "config.ini"), cfg.toString());
        write(new File(avdHome(), safeId + ".ini"),
                "path=" + vvd.getAbsolutePath() + "\npath.rel=vvd/" + safeId + ".vvd\n");
        write(new File(vvd, "AVD.conf"), "battery.charge_level=100\nset.pauseAvdWhenMinimized=false\n");
        write(new File(vvd, "emulator-user.ini"), "window.x=0\nwindow.y=0\n");
    }

    /** 启动引擎（无窗口 + gRPC 取帧）。 */
    public void start(@NonNull String avdId, int grpcPort) throws Exception {
        // 模拟器只接受 5554–5584 的**偶数**控制台端口，adb 占用其 +1。
        // 多台同时跑时必须各占一个：否则两台都绑 5560，后启动的那台控制台/adb 通道拿不到
        // （grpcPort 由管理器按实例序号分配：8554、8556、8558 → 5560、5562、5564）。
        int adbPort = 5560 + Math.max(0, grpcPort - 8554);
        stop();
        this.grpcPort = grpcPort;
        // 传进来的可能是设备 id（带冒号）→ 和 authorAvd 用同一套转义，确保找得到那个 AVD
        final String safeId = avdNameOf(avdId);
        File engine = engineDir();
        File launcher = new File(engine, "qemu/linux-aarch64/qemu-system-armel-headless");
        List<String> cmd = new ArrayList<>();
        cmd.add(launcher.getAbsolutePath());
        cmd.add("-vela");
        cmd.add("-avd");
        cmd.add(safeId);
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
        // 客机（NuttX/Vela）自己的日志：默认只在内核/串口里，不会进外壳 stdout。
        // -show-kernel 把客机内核日志直接打到外壳输出，于是现有日志通道即可显示。
        cmd.add("-show-kernel");
        // ★ 对齐 AIoT IDE：开 user-mode 网络，并把宿主 127.0.0.1:<debugPort> 转发到客机 :101
        //   （快应用 JS 调试器；IDE 的 debugger_ip.cfg = 10.0.2.15:101）。
        //   多实例各占一个端口：10055/10056/...
        this.debugPort = 10055 + Math.max(0, grpcPort - 8554);
        cmd.add("-network-user-mode-options");
        cmd.add("hostfwd=tcp:127.0.0.1:" + debugPort + "-10.0.2.15:101");
        cmd.add("-qemu");
        // 声卡/CPU 数：与 IDE 的 getVvdStartCmd() 一致 ——
        //   普通档: -device virtio-snd,bus=virtio-mmio-bus.2 -allow-host-audio -semihosting -smp 2
        //   音箱档: 再挂一块 virtio-gpu-device(xres=480,yres=800) 且 -smp 4
        cmd.add("-device");
        cmd.add("virtio-snd,bus=virtio-mmio-bus.2");
        cmd.add("-allow-host-audio");
        cmd.add("-semihosting");
        cmd.add("-smp");
        if ("smartspeaker".equals(skinFlavor)) {
            cmd.add("4");
            cmd.add("-device");
            cmd.add("virtio-gpu-device,xres=480,yres=800,bus=virtio-mmio-bus.5");
        } else {
            cmd.add("2");
        }
        /* 包内实例清单（config.json 的 launch 段）可以补/覆盖启动参数与环境变量：
         * 不同设备类型（表/环/音箱）的启动差异都写在包里，App 不再写死。没有清单就按上面的默认值。 */
        java.util.List<String> extraArgs = new java.util.ArrayList<>();
        java.util.Map<String, String> extraEnv = new java.util.HashMap<>();
        VelaProvision.manifestLaunch(runtimeDir(), safeId, extraArgs, extraEnv);
        cmd.addAll(extraArgs);
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
        e.putAll(extraEnv);
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

    /** 递归删除，失败不抛（清历史坏目录用）。 */
    private static void deleteQuietly(@Nullable File target) {
        if (target == null || !target.exists()) {
            return;
        }
        if (target.isDirectory()) {
            File[] children = target.listFiles();
            if (children != null) {
                for (File child : children) {
                    deleteQuietly(child);
                }
            }
        }
        //noinspection ResultOfMethodCallIgnored
        target.delete();
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
