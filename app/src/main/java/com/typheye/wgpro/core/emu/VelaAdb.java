package com.typheye.wgpro.core.emu;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * 客机指令通道：随包携带的**静态 adb**（jniLibs 里的 {@code libwgpro_adb.so}）。
 *
 * <p>模拟器自身在设备上开了 adb 端口（控制台端口 +1，本工程用 5560/5561），
 * 所以直接用自带 adb 连 {@code 127.0.0.1:5561} 就能对**客机内部**下发 shell 指令，
 * 不需要宿主电脑参与。</p>
 */
public final class VelaAdb {

    private final Context context;
    private final int port;
    private volatile boolean connected;

    public VelaAdb(@NonNull Context context, int emulatorAdbPort) {
        this.context = context.getApplicationContext();
        this.port = emulatorAdbPort;
    }

    /** 串口名（adb -s 用）。 */
    @NonNull
    public String serial() {
        return "127.0.0.1:" + port;
    }

    /** 执行一次 adb（返回合并后的输出）。 */
    @NonNull
    public String run(@NonNull String... args) throws Exception {
        File adb = new File(context.getApplicationInfo().nativeLibraryDir, "libwgpro_adb.so");
        if (!adb.isFile()) {
            throw new IllegalStateException("缺少 adb 组件");
        }
        String[] cmd = new String[args.length + 1];
        cmd[0] = adb.getAbsolutePath();
        System.arraycopy(args, 0, cmd, 1, args.length);
        ProcessBuilder builder = new ProcessBuilder(cmd);
        File home = new File(context.getFilesDir(), "adbhome");
        //noinspection ResultOfMethodCallIgnored
        home.mkdirs();
        builder.environment().put("HOME", home.getAbsolutePath());
        builder.environment().put("TMPDIR", context.getCacheDir().getAbsolutePath());
        builder.redirectErrorStream(true);
        Process process = builder.start();
        StringBuilder out = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                process.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line).append('\n');
            }
        }
        process.waitFor();
        return out.toString();
    }

    /** 确保本地 adb 服务与客机连接（幂等）。 */
    public synchronized void ensureConnected() throws Exception {
        if (connected) {
            return;
        }
        run("start-server");
        String result = run("connect", serial());
        connected = result.contains("connected");
    }

    /** 对客机执行一条 shell 指令。 */
    @NonNull
    public String shell(@NonNull String command) throws Exception {
        ensureConnected();
        return run("-s", serial(), "shell", command);
    }
}
