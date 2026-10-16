package com.typheye.wgpro.core.emu;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * 把 vela 应用（{@code .rpk}）装进**指定模拟设备**。
 *
 * <p>通道与顺序对齐 AIoT IDE 的实现（{@code vela.aiot-emulator} 的 VelaMiwear5 档）：
 * <pre>
 * push  &lt;本地 rpk&gt;  →  /data/app/&lt;name&gt;.rpk
 * shell pm install /data/app/&lt;name&gt;.rpk
 * </pre>
 * 注意两点（都踩过）：
 * <ul>
 *   <li>传输必须走 {@link VelaAdbClient} 的 WRTE 帧（sync 服务数据要装进帧里），
 *       不能往 socket 写原始 sync 字节；</li>
 *   <li>成败**不看 {@code pm install} 的 shell 回显**（客机基本不往 stdout 写），
 *       而是看**客机控制台日志**（引擎 stdout，App 侧即 {@code VelaEngine} 日志）：
 *       成功 {@code rpk_install_status: rpk file [<路径>] installed} /
 *       {@code executeInstall: rpk install: <路径> ... success}；
 *       失败 {@code uv_mq_read_cb: install prepare failed} /
 *       {@code rpk_install_status: rpk install failed}；
 *       忙碌 {@code installRpk: installation is running, please wait}。</li>
 * </ul>
 */
public final class VelaInstall {

    /** 客机里放待装 rpk 的目录（AIoT IDE 同款）。 */
    private static final String REMOTE_DIR = "/data/app/";

    /** 客机 rpk 安装器就绪标记。 */
    private static final Pattern READY =
            Pattern.compile("quickapp_rpk_installer_init|rpk installer init done");

    private static final Pattern FAILED = Pattern.compile(
            "uv_mq_read_cb: install prepare failed"
                    + "|rpk_install_status: rpk install failed"
                    + "|invalid rpk file",
            Pattern.CASE_INSENSITIVE);

    private static final Pattern BUSY =
            Pattern.compile("installation is running, please wait", Pattern.CASE_INSENSITIVE);

    private VelaInstall() {
    }

    public interface Callback {
        /** 结果回调（在调用线程上，不切主线程）。 */
        void onResult(boolean success, @NonNull String message);
    }

    /** 该设备实例的客机 adb 端口：控制台 5560+2n 的 **+1**（实测，漏了就收 ASCII 报文）。 */
    public static int adbPortOf(@NonNull VelaEmulatorManager.Instance instance) {
        // 端口口径统一收敛在 VelaGuest（它是客机能力的唯一门面）
        return VelaGuest.adbPortOf(instance);
    }

    /**
     * 启动/附着该模拟设备 → 推 rpk 到 {@code /data/app} → {@code pm install} → 等客机日志回执。
     *
     * @param model    镜像包机型标识（如 {@code vela-miwear-watch-5.0}）
     * @param deviceId 设备库主键（如 {@code virtual:vela-miwear-watch-5.0:7YVZ}）
     * @param rpk      本地已下载的 .rpk 文件
     */
    public static void rpkToDevice(@NonNull Context context, @NonNull String model,
                                   @NonNull String deviceId, @NonNull File rpk,
                                   @NonNull Callback callback) {
        if (!rpk.isFile()) {
            callback.onResult(false, "本地安装包不存在：" + rpk.getAbsolutePath());
            return;
        }
        final StringBuilder console = new StringBuilder();
        final Object lock = new Object();
        final VelaEmulator.LineListener listener = line -> {
            synchronized (lock) {
                console.append(line).append('\n');
                lock.notifyAll();
            }
        };
        VelaEmulatorManager manager = VelaEmulatorManager.get();
        boolean listening = false;
        try {
            manager.init(context);
            VelaEmulatorManager.Instance instance = manager.start(context, model, deviceId);
            int adbPort = adbPortOf(instance);
            // 引擎起来后端口要等一会儿才 LISTEN，立刻连必然 ECONNREFUSED
            if (!VelaAdbClient.waitFor(adbPort, 40000L)) {
                callback.onResult(false, "客机 adb 端口 " + adbPort + " 40 秒内未就绪（引擎可能没起来）");
                return;
            }
            // 先把已经打过的引擎日志灌进来（安装器就绪标记通常已经在里面）
            synchronized (lock) {
                for (String line : manager.log(deviceId)) {
                    console.append(line).append('\n');
                }
            }
            manager.addLineListener(deviceId, listener);
            listening = true;
            // 客机 rpk 安装器就绪（引擎刚起来时要等 "rpk installer init done"）
            waitFor(console, lock, READY, 40000L);

            // 传输/指令统一走 VelaGuest（同一套 WRTE 分帧与端口口径）
            VelaGuest guest = VelaGuest.of(instance);
            if (guest.isPreDialect()) {
                // PRE 档（手环）与表档完全不是一条路：mkdir + unzip + vapp，没有 pm install
                installToPre(guest, rpk, callback);
                return;
            }
            final String remote = REMOTE_DIR + baseName(rpk) + ".rpk";
            guest.push(rpk, remote);
            // 只认"这条命令之后"的日志，避免把开机扫描时的安装日志误判成这次的结果
            final int start;
            synchronized (lock) {
                start = console.length();
            }
            String output = guest.shell("pm install " + remote, 30000L);
            String shellText = output == null ? "" : output.trim();
            if (shellText.toLowerCase(Locale.ROOT).contains("success")) {
                callback.onResult(true, "已安装：" + baseName(rpk));
                return;
            }
            Result result = awaitResult(console, lock, start, remote, 120000L);
            if (result.state < 0) {
                callback.onResult(false, result.detail);
            } else if (result.state > 0) {
                callback.onResult(true, "已安装：" + result.detail);
            } else {
                callback.onResult(true, "已推送并执行安装（客机 120 秒内未回报结果，可看引擎日志）");
            }
        } catch (Throwable error) {
            String message = error.getMessage();
            callback.onResult(false, message == null || message.trim().isEmpty()
                    ? error.toString() : message);
        } finally {
            if (listening) {
                manager.removeLineListener(deviceId, listener);
            }
        }
    }

    /**
     * PRE 档（手环 / quickapp 框架）安装：与 AIoT IDE 的 {@code PreInstance.install} 一致 ——
     * {@code mkdir /data/quickapp/app/<包名>} → {@code unzip -o <rpk> -d <dir>} → {@code vapp app/<包名> &}。
     * 这条路**没有** {@code pm install}，也没有 "rpk installer init done" 那种回执，成败看解压结果。
     */
    private static void installToPre(@NonNull VelaGuest guest, @NonNull File rpk,
                                     @NonNull Callback callback) {
        try {
            String packageName = rpkPackageName(rpk);
            if (packageName.isEmpty()) {
                packageName = baseName(rpk);
            }
            final String into = guest.appDir() + "/" + packageName;
            final String remote = into + ".rpk";
            guest.push(rpk, remote);
            guest.shell("mkdir " + into, 15000L);
            guest.unzip(remote, into);
            String listing = guest.shell("ls " + into, 15000L);
            if (listing == null || !listing.contains("manifest.json")) {
                callback.onResult(false, "解压失败：" + lastLine(listing));
                return;
            }
            guest.startApp(packageName, false);
            callback.onResult(true, "已安装并启动：" + packageName);
        } catch (Throwable error) {
            String message = error.getMessage();
            callback.onResult(false, message == null || message.trim().isEmpty()
                    ? error.toString() : message);
        }
    }

    /** 从 rpk（zip）的 manifest.json 里取包名。 */
    @NonNull
    private static String rpkPackageName(@NonNull File rpk) {
        try (java.util.zip.ZipFile zip = new java.util.zip.ZipFile(rpk)) {
            java.util.zip.ZipEntry entry = zip.getEntry("manifest.json");
            if (entry == null) {
                return "";
            }
            java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
            try (java.io.InputStream in = zip.getInputStream(entry)) {
                byte[] buffer = new byte[8192];
                int count;
                while ((count = in.read(buffer)) > 0) {
                    out.write(buffer, 0, count);
                }
            }
            String json = out.toString("UTF-8");
            return new org.json.JSONObject(json).optString("package", "").trim();
        } catch (Exception error) {
            return "";
        }
    }

    /** 取最后一条非空行（给用户看原因）。 */
    @NonNull
    private static String lastLine(@Nullable String text) {
        if (text == null) {
            return "未知原因";
        }
        String last = "";
        for (String line : text.split("\r?\n")) {
            if (!line.trim().isEmpty()) {
                last = line.trim();
            }
        }
        return last.isEmpty() ? "未知原因" : last;
    }

    /** 安装结果。{@code state}: 1=成功 0=未知 -1=失败。 */
    private static final class Result {
        final int state;
        final String detail;

        Result(int state, @NonNull String detail) {
            this.state = state;
            this.detail = detail;
        }
    }

    /** 客机成功日志里的包名：{@code executeInstall: rpk install: <路径> <包名> success}。 */
    private static final Pattern PACKAGE_IN_SUCCESS =
            Pattern.compile("rpk install: \\S+ ([\\w.]+) success", Pattern.CASE_INSENSITIVE);

    /** 等客机日志里的安装结果（成功/失败标记都只认 {@code from} 之后的部分）。 */
    @NonNull
    private static Result awaitResult(@NonNull StringBuilder console, @NonNull Object lock,
                                      int from, @NonNull String remote, long timeoutMs) {
        String quoted = Pattern.quote(remote);
        Pattern ok = Pattern.compile(
                "executeInstall: rpk install: " + quoted + ".*success"
                        + "|rpk_install_status: rpk file \\[" + quoted + "\\] installed",
                Pattern.CASE_INSENSITIVE);
        long deadline = System.currentTimeMillis() + timeoutMs;
        synchronized (lock) {
            while (System.currentTimeMillis() < deadline) {
                String tail = console.substring(Math.min(from, console.length()));
                java.util.regex.Matcher success = ok.matcher(tail);
                if (success.find()) {
                    java.util.regex.Matcher name = PACKAGE_IN_SUCCESS.matcher(tail);
                    String pkg = name.find() ? name.group(1) : baseNameOf(remote);
                    return new Result(1, pkg);
                }
                if (FAILED.matcher(tail).find()) {
                    return new Result(-1, "客机安装失败：" + lastMatch(tail));
                }
                if (BUSY.matcher(tail).find()) {
                    return new Result(-1, "客机正忙（已有安装在进行），请稍后重试");
                }
                try {
                    lock.wait(1000L);
                } catch (InterruptedException interrupted) {
                    Thread.currentThread().interrupt();
                    return new Result(-1, "安装被中断");
                }
            }
        }
        return new Result(0, "");
    }

    /** 远端路径 → 文件名（去掉 .rpk）。 */
    @NonNull
    private static String baseNameOf(@NonNull String remote) {
        int slash = remote.lastIndexOf('/');
        String name = slash < 0 ? remote : remote.substring(slash + 1);
        return name.endsWith(".rpk") ? name.substring(0, name.length() - 4) : name;
    }

    /** 等到日志里出现某个标记（或超时）。 */
    private static void waitFor(@NonNull StringBuilder console, @NonNull Object lock,
                                @NonNull Pattern pattern, long timeoutMs) {
        long deadline = System.currentTimeMillis() + timeoutMs;
        synchronized (lock) {
            while (System.currentTimeMillis() < deadline) {
                if (pattern.matcher(console).find()) {
                    return;
                }
                try {
                    lock.wait(500L);
                } catch (InterruptedException interrupted) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
    }

    /** 取日志里最后一条非空的失败行（给用户看原因）。 */
    @NonNull
    private static String lastMatch(@NonNull String text) {
        String last = "";
        for (String line : text.split("\n")) {
            if (FAILED.matcher(line).find()) {
                last = line.trim();
            }
        }
        return last.isEmpty() ? "未知原因" : last;
    }

    /** 去掉扩展名的文件名。 */
    @NonNull
    private static String baseName(@NonNull File rpk) {
        String name = rpk.getName();
        int cut = name.lastIndexOf('.');
        return cut > 0 ? name.substring(0, cut) : name;
    }
}
