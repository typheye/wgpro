package com.typheye.wgpro.core.emu;

import androidx.annotation.NonNull;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;

/**
 * 极简 adb 协议客户端：**直连客机的 adbd**（模拟器在设备上监听 127.0.0.1:5561）。
 *
 * <p>为什么不用自带 adb 二进制：它的 prefs 目录被写死成 {@code /sdcard/.android}，
 * 应用域建不了该目录、adb server 起不来。这里直接用 adb 的线协议（CNXN / OPEN / WRTE），
 * 不需要 server、不需要新权限。模拟器的 adbd 通常免认证（{@code ro.adb.secure=0}）。</p>
 *
 * <p>只用 legacy shell 通道：不在 CNXN 里声明 {@code shell_v2}，adbd 就回原始字节流。</p>
 */
public final class VelaAdbClient {

    private static final int A_CNXN = 0x4e584e43;
    private static final int A_AUTH = 0x48545541;
    private static final int A_OPEN = 0x4e45504f;
    private static final int A_OKAY = 0x59414b4f;
    private static final int A_WRTE = 0x45545257;
    private static final int A_CLSE = 0x45534c43;
    private static final int A_VERSION = 0x01000001;
    private static final int MAX_DATA = 1024 * 1024;
    /** 引擎监听在 IPv6 回环 ::1；127.0.0.1 会立刻被关闭，所以优先 ::1。 */
    private static final String[] HOSTS = {"::1", "127.0.0.1"};

    private VelaAdbClient() {
    }

    /** 在客机执行一条 shell 指令，返回合并后的输出。 */
    @NonNull
    public static String shell(@NonNull String host, int port, @NonNull String command,
                               long timeoutMs) throws Exception {
        Exception last = null;
        for (String candidate : HOSTS) {
            try {
                return shellOn(candidate, port, command, timeoutMs);
            } catch (Exception error) {
                last = error;
            }
        }
        throw last == null ? new IllegalStateException("无法连接客机 adbd") : last;
    }

    @NonNull
    private static String shellOn(@NonNull String host, int port, @NonNull String command,
                                  long timeoutMs) throws Exception {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), 4000);
            socket.setSoTimeout((int) timeoutMs);
            OutputStream out = socket.getOutputStream();
            DataInputStream in = new DataInputStream(socket.getInputStream());

            // 1) CNXN：不声明 shell_v2 → adbd 走原始流
            send(out, A_CNXN, A_VERSION, MAX_DATA, "host::\0".getBytes(StandardCharsets.UTF_8));
            Message reply = read(in);
            if (reply == null) {
                throw new IllegalStateException("客机 adbd 无响应");
            }
            if (reply.command == A_AUTH) {
                throw new IllegalStateException("客机 adbd 需要认证（本模拟器未开放免认证）");
            }
            if (reply.command != A_CNXN) {
                throw new IllegalStateException("握手失败: " + Integer.toHexString(reply.command));
            }

            // 2) OPEN("shell:<cmd>")
            int localId = 1;
            String service = "shell:" + command + "\0";
            send(out, A_OPEN, localId, 0, service.getBytes(StandardCharsets.UTF_8));

            // 3) 收流：OKAY → WRTE* → CLSE（每条 WRTE 都要回 OKAY 做流控）
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            int remoteId = -1;
            long deadline = System.currentTimeMillis() + timeoutMs;
            while (System.currentTimeMillis() < deadline) {
                Message message = read(in);
                if (message == null) {
                    break;
                }
                if (message.command == A_OKAY) {
                    if (remoteId < 0) {
                        remoteId = message.arg0;
                    }
                    continue;
                }
                if (message.command == A_WRTE) {
                    if (message.payload != null && message.payload.length > 0) {
                        result.write(message.payload);
                    }
                    send(out, A_OKAY, localId, message.arg0, new byte[0]);
                    continue;
                }
                if (message.command == A_CLSE) {
                    break;
                }
            }
            send(out, A_CLSE, localId, Math.max(remoteId, 0), new byte[0]);
            return result.toString("UTF-8");
        }
    }

    /**
     * 把本地文件推到客机（adb 的 {@code sync} 服务）。
     *
     * <p>协议：{@code OPEN("sync:")} → {@code SEND <远端路径>,<权限>} → 若干
     * {@code DATA <len> + 数据} → {@code DONE <mtime>} → 服务端回 {@code OKAY} 或
     * {@code FAIL+长度+信息}（所有整数小端）。</p>
     */
    public static void push(@NonNull String host, int port, @NonNull File local,
                            @NonNull String remotePath, long timeoutMs) throws Exception {
        Exception last = null;
        for (String candidate : HOSTS) {
            try {
                pushOn(candidate, port, local, remotePath, timeoutMs);
                return;
            } catch (Exception error) {
                last = error;
            }
        }
        throw last == null ? new IllegalStateException("无法连接客机 adbd") : last;
    }

    private static void pushOn(@NonNull String host, int port, @NonNull File local,
                               @NonNull String remotePath, long timeoutMs) throws Exception {
        final long budget = Math.max(4000L, timeoutMs);
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), 4000);
            socket.setSoTimeout((int) Math.min(budget, 120000L));
            OutputStream out = new BufferedOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            send(out, A_CNXN, A_VERSION, MAX_DATA, "host::\0".getBytes(StandardCharsets.UTF_8));
            Message reply = read(in);
            if (reply == null || reply.command != A_CNXN) {
                throw new IllegalStateException("客机 adbd 握手失败");
            }
            int localId = 1;
            send(out, A_OPEN, localId, 0, "sync:\0".getBytes(StandardCharsets.UTF_8));
            int remoteId = -1;
            long deadline = System.currentTimeMillis() + budget;
            while (System.currentTimeMillis() < deadline) {
                Message message = read(in);
                if (message == null) {
                    throw new IllegalStateException("客机 adbd 断开");
                }
                if (message.command == A_OKAY) {
                    remoteId = message.arg0;
                    break;
                }
                if (message.command == A_CLSE) {
                    throw new IllegalStateException("客机拒绝 sync 服务");
                }
            }
            if (remoteId < 0) {
                throw new IllegalStateException("客机 sync 服务未就绪");
            }

            byte[] spec = (remotePath + ",33188").getBytes(StandardCharsets.UTF_8);
            writeSyncHeader(out, "SEND", spec.length);
            out.write(spec);
            try (InputStream file = new FileInputStream(local)) {
                byte[] buffer = new byte[64 * 1024];
                int read;
                while ((read = file.read(buffer)) > 0) {
                    writeSyncHeader(out, "DATA", read);
                    out.write(buffer, 0, read);
                }
            }
            writeSyncHeader(out, "DONE", (int) (local.lastModified() / 1000L));
            out.flush();

            byte[] status = new byte[4];
            in.readFully(status);
            String text = new String(status, StandardCharsets.US_ASCII);
            if (!"OKAY".equals(text)) {
                if ("FAIL".equals(text)) {
                    int length = Integer.reverseBytes(in.readInt());
                    byte[] message = new byte[Math.max(0, Math.min(length, 4096))];
                    in.readFully(message);
                    throw new IllegalStateException("推送失败："
                            + new String(message, StandardCharsets.UTF_8));
                }
                throw new IllegalStateException("推送失败：" + text);
            }
            send(out, A_CLSE, localId, Math.max(remoteId, 0), new byte[0]);
        }
    }

    /** sync 协议的 8 字节头：4 字节 ASCII 指令 + 4 字节小端整数。 */
    private static void writeSyncHeader(@NonNull OutputStream out, @NonNull String id, int value)
            throws Exception {
        out.write(id.getBytes(StandardCharsets.US_ASCII));
        out.write(value & 0xFF);
        out.write((value >> 8) & 0xFF);
        out.write((value >> 16) & 0xFF);
        out.write((value >> 24) & 0xFF);
    }

    // ------------------------------------------------------------------ 线协议

    private static final class Message {
        int command;
        int arg0;
        int arg1;
        byte[] payload;
    }

    private static void send(@NonNull OutputStream out, int command, int arg0, int arg1,
                             @NonNull byte[] payload) throws Exception {
        ByteBuffer header = ByteBuffer.allocate(24).order(ByteOrder.LITTLE_ENDIAN);
        int crc = 0;
        if (payload.length > 0) {
            CRC32 c = new CRC32();
            c.update(payload);
            crc = (int) c.getValue();
        }
        header.putInt(command).putInt(arg0).putInt(arg1)
                .putInt(payload.length).putInt(crc).putInt(command ^ 0xFFFFFFFF);
        out.write(header.array());
        out.write(payload);
        out.flush();
    }

    private static Message read(@NonNull DataInputStream in) throws Exception {
        byte[] head = new byte[24];
        try {
            in.readFully(head);
        } catch (Exception eof) {
            return null;
        }
        ByteBuffer header = ByteBuffer.wrap(head).order(ByteOrder.LITTLE_ENDIAN);
        Message message = new Message();
        message.command = header.getInt();
        message.arg0 = header.getInt();
        message.arg1 = header.getInt();
        int length = header.getInt();
        header.getInt();   // crc（不校验）
        header.getInt();   // magic
        if (length < 0 || length > 4 * 1024 * 1024) {
            throw new IllegalStateException("非法报文长度 " + length);
        }
        if (length > 0) {
            message.payload = new byte[length];
            in.readFully(message.payload);
        } else {
            message.payload = new byte[0];
        }
        return message;
    }

    /**
     * 等客机 adbd 端口就绪。
     *
     * <p>引擎刚启动时还没 bind 控制台/ adb 端口，立刻连会 **ECONNREFUSED**（实测：
     * 端口随后确实在 127.0.0.1/::1 上 LISTEN，只是慢了一拍）。这里轮询到就绪为止。</p>
     */
    public static boolean waitFor(int port, long timeoutMs) {
        long deadline = System.currentTimeMillis() + Math.max(1000L, timeoutMs);
        while (System.currentTimeMillis() < deadline) {
            for (String host : HOSTS) {
                if (probe(host, port)) {
                    return true;
                }
            }
            try {
                Thread.sleep(500L);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return false;
    }

    /** 探活：客机 adbd 是否可直连（免认证）。 */
    public static boolean probe(@NonNull String host, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), 1500);
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    /** 未使用的占位，避免 import 被优化掉（InputStream 供后续扩展）。 */
    static InputStream unused() {
        return null;
    }
}
