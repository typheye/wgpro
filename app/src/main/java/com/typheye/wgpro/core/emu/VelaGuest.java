package com.typheye.wgpro.core.emu;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * **客机（Vela 模拟器内部系统）能力门面**：一条链路收敛「按设备 id 拿到 adb 端口 →
 * 附着/启动实例 → 执行客机指令」。
 *
 * <p>这里逐项对齐 AIoT IDE（{@code vela.aiot-emulator}）的客机操作，命令都取自 IDE 源码
 * 实测有效的写法，而不是猜的：</p>
 *
 * <table>
 *   <tr><th>能力</th><th>客机命令（IDE 原文）</th></tr>
 *   <tr><td>安装 rpk</td><td>{@code pm install <rpk 路径>}（PRE 档是 {@code mkdir} + {@code unzip}）</td></tr>
 *   <tr><td>卸载</td><td>{@code pm uninstall <pkg>}（PRE 档是 {@code rm -r <appDir>/<pkg>}）</td></tr>
 *   <tr><td>启动</td><td>{@code am start <pkg>}（PRE 档是 {@code vapp app/<pkg> &}）</td></tr>
 *   <tr><td>关闭</td><td>{@code am stop <pkg>}</td></tr>
 *   <tr><td>重载</td><td>{@code am stop} → 500ms → {@code am start}（PRE 档是 reboot + start）</td></tr>
 *   <tr><td>应用列表</td><td>{@code ls <appDir>}，只取以 {@code /} 结尾的目录名</td></tr>
 *   <tr><td>重启/关机</td><td>{@code reboot} / {@code poweroff}</td></tr>
 *   <tr><td>解压</td><td>{@code unzip -o <zip> -d <dir>}</td></tr>
 *   <tr><td>调试配置</td><td>推 {@code /data/debugger_ip.cfg}（{@code 10.0.2.15:101}）与
 *       {@code /tmp/quickapp_debug_cfg.json}（{@code {mqttHost,mqttPort,device}}）</td></tr>
 * </table>
 *
 * <p><b>硬约束</b>：通道走 {@link VelaAdbClient}（microADB 的 byte-sum 校验和 + WRTE 分帧），
 * adb 端口 = 控制台端口 + 1（见 {@link #adbPortOf}），不能用宿主 adb 端口去连。</p>
 */
public final class VelaGuest {

    /** 控制台端口基数（引擎 {@code -port} 传的就是控制台端口）。 */
    private static final int CONSOLE_BASE = 5560;

    /** REL / watch5 档的应用目录（IDE {@code VelaMiwear5.appDir}）。 */
    public static final String APP_DIR_REL = "/data/app";
    /** PRE / DEV 档的应用目录（IDE {@code PreInstance.appDir}）。 */
    public static final String APP_DIR_PRE = "/data/quickapp/app";

    /** 客机 JS 调试器地址（IDE {@code static/debugger_ip.cfg} 原文）。 */
    public static final String DEBUGGER_IP = "10.0.2.15:101";

    /** 镜像档：PRE（4.0 quickapp 框架；appDir=/data/quickapp/app + vapp）。 */
    public static final String IMAGE_PRE = "vela-pre-4.0";
    /** 镜像档：miwear watch 5.0 —— **带表盘、不可自定义尺寸**（只适用它自己的 466×466）。 */
    public static final String IMAGE_MIWEAR_WATCH5 = "vela-miwear-watch-5.0";
    /** 镜像档：vela watch 5.0 —— **不带表盘、可自定义尺寸**（手环 / 方屏表用这套）。 */
    public static final String IMAGE_VELA_WATCH5 = "vela-watch-5.0";
    /** 镜像档：openvela 音箱。 */
    public static final String IMAGE_SMARTSPEAKER = "openvela-smartspeaker-5.0";

    private final Context context;
    private final String model;
    private final String deviceId;
    private final VelaEmulatorManager.Instance instance;
    private final int adbPort;
    /** 镜像档（{@code ide.image.type}）；决定客机命令方言（PRE 用 vapp）。 */
    private String imageType;
    /** 客机应用目录（首次使用时按客机实际目录探测；见 {@link #appDir()}）。 */
    private String appDir;

    private VelaGuest(@Nullable Context context, @NonNull String model, @NonNull String deviceId,
                      @NonNull VelaEmulatorManager.Instance instance, int adbPort) {
        this.context = context == null ? null : context.getApplicationContext();
        this.model = model;
        this.deviceId = deviceId;
        this.instance = instance;
        this.adbPort = adbPort;
        this.imageType = instance.emulator.imageType();
    }

    /** 覆盖镜像档（调用方从 AVD/清单里读出来时用）。 */
    public void setImageType(@Nullable String type) {
        this.imageType = type == null ? "" : type.trim();
        this.appDir = null;   // 换档后应用目录要重新解析
    }

    @NonNull
    public String imageType() {
        return imageType;
    }

    // ------------------------------------------------------------------ 构造 / 端口

    /** 该实例的**控制台**端口（引擎 {@code -port} 值）：5560 + 2n。 */
    public static int consolePortOf(@NonNull VelaEmulatorManager.Instance instance) {
        return CONSOLE_BASE + Math.max(0, instance.port - 8554);
    }

    /**
     * 该实例的**客机 adb 端口** = 控制台端口 + 1。
     *
     * <p>实测：连控制台会收到 ASCII 报文（「非法报文长度」）；连 +1 才是 adbd。</p>
     */
    public static int adbPortOf(@NonNull VelaEmulatorManager.Instance instance) {
        return consolePortOf(instance) + 1;
    }

    /** 附着到一个**已在运行**的实例（不启动引擎）。 */
    @NonNull
    public static VelaGuest of(@NonNull VelaEmulatorManager.Instance instance) {
        return new VelaGuest(null, instance.model, instance.emulator.deviceId(), instance,
                adbPortOf(instance));
    }

    /** 附着到已经运行、或按需启动的实例；返回时**客机 adbd 已就绪**。 */
    @NonNull
    public static VelaGuest attach(@NonNull Context context, @NonNull String model,
                                   @NonNull String deviceId) throws Exception {
        VelaEmulatorManager manager = VelaEmulatorManager.get();
        manager.init(context);
        VelaEmulatorManager.Instance instance = manager.start(context, model, deviceId);
        VelaGuest guest = new VelaGuest(context, model, deviceId, instance, adbPortOf(instance));
        guest.awaitReady(40000L);
        return guest;
    }

    /** 等客机 adbd 就绪；未就绪抛异常。 */
    @NonNull
    public VelaGuest awaitReady(long timeoutMs) throws Exception {
        if (!VelaAdbClient.waitFor(adbPort, timeoutMs)) {
            throw new IllegalStateException("客机 adb 端口 " + adbPort
                    + " 在 " + (timeoutMs / 1000) + " 秒内未就绪（引擎可能没起来）");
        }
        return this;
    }

    public int adbPort() {
        return adbPort;
    }

    @NonNull
    public String deviceId() {
        return deviceId;
    }

    @NonNull
    public String model() {
        return model;
    }

    @NonNull
    public VelaEmulatorManager.Instance instance() {
        return instance;
    }

    // ------------------------------------------------------------------ 基础能力

    /** 执行一条客机 shell 指令，返回合并输出。 */
    @NonNull
    public String shell(@NonNull String command) throws Exception {
        return shell(command, 20000L);
    }

    @NonNull
    public String shell(@NonNull String command, long timeoutMs) throws Exception {
        String output = VelaAdbClient.shell("::1", adbPort, command, timeoutMs);
        return output == null ? "" : output;
    }

    /** 推本地文件到客机（adb sync，走 WRTE 分帧）。 */
    public void push(@NonNull File local, @NonNull String remote) throws Exception {
        VelaAdbClient.push("::1", adbPort, local, remote, 180000L);
    }

    /**
     * 客机端解压：{@code unzip -o <zip> -d <dir>}（IDE 同款）。
     *
     * <p>实测：客机的 miniunz **不会自建目标目录**，目录不存在会
     * {@code Error changing into <dir>, aborting}（解压 0 个文件）。所以先 {@code mkdir}。</p>
     */
    @NonNull
    public String unzip(@NonNull String zip, @NonNull String dir) throws Exception {
        shell("mkdir " + dir, 8000L);
        return shell("unzip -o " + zip + " -d " + dir);
    }

    /** 连通性：客机 adbd 是否可直连（免认证）。 */
    public boolean isConnected() {
        return VelaAdbClient.probe("::1", adbPort) || VelaAdbClient.probe("127.0.0.1", adbPort);
    }

    // ------------------------------------------------------------------ 应用目录 / 列表

    /**
     * 客机应用目录。
     *
     * <p>不按机型硬编码：先问客机 {@code ls /data/app}，取不到条目再退回 PRE 档的
     * {@code /data/quickapp/app}（两档在 IDE 里的差异就是这个目录）。</p>
     */
    @NonNull
    public String appDir() {
        if (appDir != null) {
            return appDir;
        }
        // 镜像档已知时直接用（两款镜像都可能同时存在 /data/app 与 /data/quickapp/app，
        // 只靠目录探测会把 PRE 档判成表档）
        if (imageType != null && (imageType.startsWith("vela-pre") || imageType.startsWith("vela-dev"))) {
            appDir = APP_DIR_PRE;
            return appDir;
        }
        try {
            if (hasEntries(shell("ls " + APP_DIR_REL, 8000L))) {
                appDir = APP_DIR_REL;
                return appDir;
            }
        } catch (Exception ignored) {
        }
        try {
            if (hasEntries(shell("ls " + APP_DIR_PRE, 8000L))) {
                appDir = APP_DIR_PRE;
                return appDir;
            }
        } catch (Exception ignored) {
        }
        appDir = APP_DIR_REL;
        return appDir;
    }

    /**
     * 是不是 PRE 档（quickapp 框架：{@code /data/quickapp/app} + {@code vapp}）。
     *
     * <p>优先看镜像档（IDE 的 {@code ide.image.type}）：只有 {@code vela-pre-4.0} / {@code vela-dev-*} 是
     * quickapp 档；表的 watch5、音箱的 smartspeaker 都是 {@code am/pm} 档。清单/AVD 里没写档位时才探测客机目录
     * （**不能只看 {@code /data/quickapp/app} 存不存在**：两款镜像都可能有这个目录）。</p>
     */
    public boolean isPreDialect() {
        if (imageType != null && !imageType.isEmpty()) {
            return imageType.startsWith("vela-pre") || imageType.startsWith("vela-dev");
        }
        try {
            return hasEntries(shell("ls " + APP_DIR_PRE, 8000L))
                    && !hasEntries(shell("ls " + APP_DIR_REL, 8000L));
        } catch (Exception error) {
            return false;
        }
    }

    /** 客机已安装应用（目录名）：{@code ls <appDir>} 只取以 {@code /} 结尾的条目。 */
    @NonNull
    public List<String> appList() throws Exception {
        String dir = appDir();
        String output = shell("ls " + dir);
        List<String> apps = new ArrayList<>();
        for (String raw : output.split("\r?\n")) {
            String line = raw.trim();
            if (line.isEmpty() || line.endsWith(":")) {
                continue;
            }
            if (line.endsWith("/")) {
                apps.add(line.substring(0, line.length() - 1).trim());
            }
        }
        if (apps.isEmpty()) {
            // 某些客机 ls 不标目录斜杠：退回「非注释、非表头」的行
            for (String raw : output.split("\r?\n")) {
                String line = raw.trim();
                if (line.isEmpty() || line.endsWith(":") || line.startsWith("ls:")) {
                    continue;
                }
                apps.add(line);
            }
        }
        return apps;
    }

    // ------------------------------------------------------------------ 安装 / 卸载 / 应用生命周期

    /**
     * 把 rpk 推到位并安装；返回客机上的落地路径。
     *
     * <p>注意：{@code pm install} 的 shell 回显基本是空的，**不能拿回显判成败** ——
     * 成败要看引擎 stdout（{@link VelaInstall} 已实现该判据）。</p>
     */
    @NonNull
    public String installRpk(@NonNull File rpk) throws Exception {
        String name = baseName(rpk);
        String remote = appDir() + "/" + name + ".rpk";
        push(rpk, remote);
        if (isPreDialect()) {
            // IDE PreInstance.install：先建目录再解压
            String into = appDir() + "/" + name;
            shell("mkdir " + into);
            shell("unzip -o " + remote + " -d " + into);
        } else {
            shell("pm install " + remote, 60000L);
        }
        return remote;
    }

    /** 卸载应用；返回客机回显。 */
    @NonNull
    public String uninstall(@NonNull String packageName) throws Exception {
        if (isPreDialect()) {
            return shell("rm -r " + appDir() + "/" + packageName);
        }
        return shell("pm uninstall " + packageName, 30000L);
    }

    /** 启动应用；{@code debug} 时按 IDE 推/删 {@code /data/debugger_ip.cfg}。 */
    @NonNull
    public String startApp(@NonNull String packageName, boolean debug) throws Exception {
        if (isPreDialect()) {
            String command = "vapp " + (debug ? "--jsdebugger=" + DEBUGGER_IP + " " : "")
                    + "app/" + packageName + " &";
            return shell(command);
        }
        applyDebuggerIp(debug);
        return shell("am start " + packageName, 30000L);
    }

    /** 关闭应用（PRE 档没有 stop，IDE 用重启代替）。 */
    @NonNull
    public String closeApp(@NonNull String packageName) throws Exception {
        if (isPreDialect()) {
            return reboot();
        }
        return shell("am stop " + packageName, 20000L);
    }

    /** 重载应用（IDE：REL 是 stop→500ms→start；PRE 是 reboot→start）。 */
    @NonNull
    public String reloadApp(@NonNull String packageName, boolean debug) throws Exception {
        if (isPreDialect()) {
            reboot();
            sleep(1000L);
            return startApp(packageName, debug);
        }
        String stopped = shell("am stop " + packageName, 20000L);
        sleep(500L);
        String started = startApp(packageName, debug);
        return stopped + started;
    }

    // ------------------------------------------------------------------ 电源 / 系统

    /** 重启客机。命令下发后连接会断，所以用**短超时**，不把回显当结果。 */
    @NonNull
    public String reboot() throws Exception {
        return shell("reboot", 5000L);
    }

    /** 关机客机（引擎会跟着退出）。 */
    @NonNull
    public String poweroff() throws Exception {
        return shell("poweroff", 5000L);
    }

    /** 客机 systemd（IDE 的 {@code systemed()}）。 */
    @NonNull
    public String systemed() throws Exception {
        return shell("systemd &", 8000L);
    }

    /**
     * 客机日志。
     *
     * <p>实测：Vela 引擎的 gRPC {@code getLogcat}/{@code streamLogcat} **返回空**，
     * 客机也没有 {@code logcat} 命令；客机内核/应用日志走 {@code dmesg}（环形缓冲，约 1KB），
     * 更完整的串口日志由引擎 stdout 承载（App 侧 {@code VelaEngine} 日志 / 日志面板）。</p>
     */
    @NonNull
    public String guestLog() throws Exception {
        return shell("dmesg", 8000L);
    }

    // ------------------------------------------------------------------ 调试配置

    /**
     * 推 JS 调试器地址到 {@code /data/debugger_ip.cfg}（IDE {@code startApp} 同款）。
     * {@code debug=false} 时按 IDE 删掉它。
     */
    private void applyDebuggerIp(boolean debug) {
        try {
            if (debug) {
                File local = new File(context == null
                        ? instance.emulator.runtimeDir() : context.getCacheDir(),
                        "debugger_ip.cfg");
                writeFile(local, DEBUGGER_IP);
                push(local, "/data/debugger_ip.cfg");
            } else {
                shell("rm /data/debugger_ip.cfg", 8000L);
            }
        } catch (Exception ignored) {
            // 调试配置是"锦上添花"，失败不影响启动应用本身
        }
    }

    /**
     * 推 {@code /tmp/quickapp_debug_cfg.json}（IDE {@code startVvd} 里做的同一件事）。
     *
     * <p>格式取自 IDE：{@code {mqttHost, mqttPort, device}}，其中
     * {@code device = "vela_emulator_<avdName>"}。</p>
     *
     * @param mqttHost MQTT 调试服务地址（客机 user-mode 网络里宿主机是 {@code 10.0.2.2}）
     * @param mqttPort 端口
     */
    public void pushQuickappDebugCfg(@NonNull String mqttHost, int mqttPort,
                                     @NonNull String device) {
        try {
            JSONObject json = new JSONObject();
            json.put("mqttHost", mqttHost);
            json.put("mqttPort", mqttPort);
            json.put("device", device);
            File local = new File(context == null
                    ? instance.emulator.runtimeDir() : context.getCacheDir(),
                    "quickapp_debug_cfg.json");
            writeFile(local, json.toString());
            push(local, "/tmp/quickapp_debug_cfg.json");
        } catch (Exception ignored) {
            // 同上：调试配置失败不影响模拟器使用
        }
    }

    // ------------------------------------------------------------------ 内部工具

    /** {@code ls} 输出里是否有真实条目（排除错误与空目录）。 */
    private static boolean hasEntries(@Nullable String output) {
        if (output == null) {
            return false;
        }
        String text = output.toLowerCase(Locale.ROOT);
        if (text.contains("no such file") || text.contains("not a directory")
                || text.contains("invalid")) {
            return false;
        }
        for (String line : output.split("\r?\n")) {
            String item = line.trim();
            if (item.isEmpty() || item.endsWith(":") || item.startsWith("ls:")) {
                continue;
            }
            return true;
        }
        return false;
    }

    private static void writeFile(@NonNull File file, @NonNull String text) throws Exception {
        File parent = file.getParentFile();
        if (parent != null && !parent.isDirectory()) {
            //noinspection ResultOfMethodCallIgnored
            parent.mkdirs();
        }
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(text.getBytes(StandardCharsets.UTF_8));
        }
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException interrupted) {
            Thread.currentThread().interrupt();
        }
    }

    @NonNull
    private static String baseName(@NonNull File file) {
        String name = file.getName();
        int cut = name.lastIndexOf('.');
        return cut > 0 ? name.substring(0, cut) : name;
    }
}
