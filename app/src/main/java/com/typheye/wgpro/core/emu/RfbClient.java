package com.typheye.wgpro.core.emu;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;

/**
 * 极简 RFB(VNC) 客户端：只为了把模拟引擎的画面拉回手机显示。
 *
 * <p>为什么是 VNC：bes-qemu 的显示桥是接到 QEMU 图形控制台上的，QEMU 自带 VNC 输出
 * （实测 {@code -vnc 127.0.0.1:1} → 横幅 {@code RFB 003.008}、安全类型 {@code [1]=None}、
 * ServerInit {@code 128x128}）。所以 Android 端只需要一个最小 RFB 客户端，
 * **不需要自己写显示桥**。</p>
 *
 * <p>只实现：3.8 握手（None 安全类型，仍需读 4 字节 SecurityResult）→ SetPixelFormat
 * （32bpp/24depth、true-colour、R<<16 G<<8 B<<0、little-endian）→ SetEncodings(Raw)
 * → 循环 FramebufferUpdateRequest/Raw 帧回调。DesktopSize 伪编码会重建缓冲。</p>
 */
public final class RfbClient {

    public interface Listener {
        /** 服务端分辨率确定（或变化）后回调，主线程。 */
        default void onSize(int width, int height) { }

        /** 一帧 ARGB 像素（长度 = width*height），主线程。 */
        default void onFrame(@NonNull int[] pixels, int width, int height) { }

        /** 出错或断开，主线程。 */
        default void onClosed(@Nullable String reason) { }
    }

    private static final int SEC_NONE = 1;
    private static final int ENC_RAW = 0;
    private static final int ENC_DESKTOP_SIZE = -223;

    private final Handler main = new Handler(Looper.getMainLooper());
    private final Listener listener;

    @Nullable private volatile Socket socket;
    @Nullable private Thread thread;
    private volatile boolean running;
    private int width;
    private int height;
    private int[] pixels = new int[0];

    public RfbClient(@NonNull Listener listener) {
        this.listener = listener;
    }

    public void connect(@NonNull String host, int port) {
        if (running) return;
        running = true;
        thread = new Thread(() -> run(host, port), "rfb-client");
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * 带重试的连接：引擎刚启动时 QEMU 的 VNC 还没开始监听，
     * 直接连会被拒（之前的"连不上就永久放弃"导致画面一直出不来）。
     */
    public void connectWithRetry(@NonNull String host, int port, int attempts, long delayMs) {
        if (running) return;
        running = true;
        thread = new Thread(() -> {
            for (int i = 1; i <= attempts && running; i++) {
                Socket probe = new Socket();
                try {
                    probe.connect(new InetSocketAddress(host, port), 1500);
                    probe.close();
                    android.util.Log.i("MockScreen", "VNC 就绪（第 " + i + " 次尝试）");
                    run(host, port);
                    return;
                } catch (Exception error) {
                    android.util.Log.i("MockScreen", "等待 VNC " + i + "/" + attempts
                            + "：" + error.getMessage());
                    try {
                        probe.close();
                    } catch (Exception ignored) {
                    }
                    try {
                        Thread.sleep(delayMs);
                    } catch (InterruptedException interrupted) {
                        return;
                    }
                }
            }
            if (running) {
                close();
                main.post(() -> listener.onClosed("VNC 端口一直未就绪：" + host + ":" + port));
            }
        }, "rfb-wait");
        thread.setDaemon(true);
        thread.start();
    }

    public void close() {
        running = false;
        Socket current = socket;
        socket = null;
        if (current != null) {
            try {
                current.close();
            } catch (IOException ignored) {
            }
        }
    }

    public boolean isConnected() { return running; }

    private void run(@NonNull String host, int port) {
        String reason = null;
        try {
            Socket s = new Socket();
            s.setTcpNoDelay(true);
            s.connect(new InetSocketAddress(host, port), 5000);
            socket = s;
            DataInputStream in = new DataInputStream(new java.io.BufferedInputStream(s.getInputStream()));
            OutputStream raw = new BufferedOutputStream(s.getOutputStream());

            // 1) 版本握手
            byte[] banner = new byte[12];
            in.readFully(banner);
            String serverVersion = new String(banner, java.nio.charset.StandardCharsets.US_ASCII);
            String version = serverVersion.startsWith("RFB 003.003") ? "RFB 003.003\n" : "RFB 003.008\n";
            raw.write(version.getBytes(java.nio.charset.StandardCharsets.US_ASCII));
            raw.flush();

            // 2) 安全类型：优先 None
            if (version.equals("RFB 003.008\n")) {
                int count = in.readUnsignedByte();
                byte[] types = new byte[count];
                in.readFully(types);
                boolean none = false;
                for (byte type : types) if ((type & 0xFF) == SEC_NONE) none = true;
                if (!none) throw new IOException("服务端不支持无认证 VNC");
                raw.write(SEC_NONE);
                raw.flush();
                // RFB 3.8：选 None 之后仍会发 4 字节 SecurityResult
                int result = in.readInt();
                if (result != 0) throw new IOException("VNC 认证失败：" + result);
            }

            // 3) ClientInit（共享）+ ServerInit
            raw.write(1);
            raw.flush();
            width = in.readUnsignedShort();
            height = in.readUnsignedShort();
            byte[] pixelFormat = new byte[16];
            in.readFully(pixelFormat);
            int nameLength = in.readInt();
            if (nameLength > 0) {
                byte[] name = new byte[nameLength];
                in.readFully(name);
            }
            resize(width, height);
            final int w0 = width;
            final int h0 = height;
            main.post(() -> listener.onSize(w0, h0));

            // 4) SetPixelFormat：32bpp / depth24 / true-colour / 小端 / R<<16 G<<8 B<<0
            byte[] setPixelFormat = new byte[20];
            setPixelFormat[0] = 0;                     // message-type = SetPixelFormat
            setPixelFormat[4] = 32;                    // bits-per-pixel
            setPixelFormat[5] = 24;                    // depth
            setPixelFormat[6] = 0;                     // big-endian-flag = 0（小端）
            setPixelFormat[7] = 1;                     // true-colour-flag
            writeShort(setPixelFormat, 8, 255);        // red-max
            writeShort(setPixelFormat, 10, 255);       // green-max
            writeShort(setPixelFormat, 12, 255);       // blue-max
            setPixelFormat[14] = 16;                   // red-shift
            setPixelFormat[15] = 8;                    // green-shift
            setPixelFormat[16] = 0;                    // blue-shift
            raw.write(setPixelFormat);

            // 5) SetEncodings：Raw + DesktopSize
            byte[] encodings = new byte[4 + 4 * 2];
            encodings[0] = 2;                          // message-type = SetEncodings
            writeShort(encodings, 2, 2);               // number-of-encodings
            writeInt(encodings, 4, ENC_RAW);
            writeInt(encodings, 8, ENC_DESKTOP_SIZE);
            raw.write(encodings);
            raw.flush();

            // 6) 帧循环
            boolean incremental = false;
            while (running) {
                raw.write(updateRequest(incremental, width, height));
                raw.flush();
                incremental = true;

                int messageType = in.readUnsignedByte();
                if (messageType == 0) {
                    in.readUnsignedByte();                       // padding
                    int rects = in.readUnsignedShort();
                    for (int i = 0; i < rects && running; i++) {
                        int x = in.readUnsignedShort();
                        int y = in.readUnsignedShort();
                        int rw = in.readUnsignedShort();
                        int rh = in.readUnsignedShort();
                        int encoding = in.readInt();
                        if (encoding == ENC_RAW) {
                            readRawRect(in, x, y, rw, rh);
                        } else if (encoding == ENC_DESKTOP_SIZE) {
                            resize(rw, rh);
                            final int nw = rw;
                            final int nh = rh;
                            main.post(() -> listener.onSize(nw, nh));
                        } else {
                            throw new IOException("不支持的编码：" + encoding);
                        }
                    }
                } else if (messageType == 1) {                    // SetColourMapEntries
                    in.readUnsignedByte();
                    int count = in.readUnsignedShort();
                    skipFully(in, count * 6L);
                } else if (messageType == 2) {                    // Bell
                    in.readUnsignedByte();
                } else if (messageType == 3) {                    // ServerCutText
                    skipFully(in, 3);
                    long length = in.readInt() & 0xFFFFFFFFL;
                    skipFully(in, length);
                } else {
                    throw new IOException("未知的服务端消息：" + messageType);
                }
            }
        } catch (Exception error) {
            reason = error.getClass().getSimpleName() + ": " + error.getMessage();
            android.util.Log.i("MockScreen", "rfb error running=" + running + " → " + reason);
        } finally {
            close();
            final String text = reason;
            main.post(() -> listener.onClosed(text));
        }
    }

    private void readRawRect(@NonNull DataInputStream in, int x, int y, int rw, int rh)
            throws IOException {
        int[] buffer = new int[Math.max(1, rw * rh)];
        byte[] row = new byte[rw * 4];
        for (int rowIndex = 0; rowIndex < rh; rowIndex++) {
            in.readFully(row);
            int base = rowIndex * rw;
            for (int column = 0; column < rw; column++) {
                int offset = column * 4;
                int b = row[offset] & 0xFF;
                int g = row[offset + 1] & 0xFF;
                int r = row[offset + 2] & 0xFF;
                buffer[base + column] = 0xFF000000 | (r << 16) | (g << 8) | b;
            }
        }
        synchronized (this) {
            if (pixels.length != width * height) resize(width, height);
            for (int rowIndex = 0; rowIndex < rh; rowIndex++) {
                int destY = y + rowIndex;
                if (destY < 0 || destY >= height) continue;
                int copyWidth = Math.min(rw, width - x);
                if (copyWidth <= 0) continue;
                System.arraycopy(buffer, rowIndex * rw, pixels, destY * width + x, copyWidth);
            }
        }
        final int[] snapshot;
        synchronized (this) {
            snapshot = Arrays.copyOf(pixels, pixels.length);
        }
        final int w = width;
        final int h = height;
        main.post(() -> listener.onFrame(snapshot, w, h));
    }

    private synchronized void resize(int newWidth, int newHeight) {
        if (newWidth <= 0 || newHeight <= 0) return;
        width = newWidth;
        height = newHeight;
        if (pixels.length != newWidth * newHeight) pixels = new int[newWidth * newHeight];
    }

    private static byte[] updateRequest(boolean incremental, int width, int height) {
        byte[] request = new byte[10];
        request[0] = 3;
        request[1] = (byte) (incremental ? 1 : 0);
        writeShort(request, 2, 0);
        writeShort(request, 4, 0);
        writeShort(request, 6, width);
        writeShort(request, 8, height);
        return request;
    }

    private static void writeShort(byte[] target, int offset, int value) {
        target[offset] = (byte) ((value >> 8) & 0xFF);
        target[offset + 1] = (byte) (value & 0xFF);
    }

    private static void writeInt(byte[] target, int offset, int value) {
        target[offset] = (byte) ((value >> 24) & 0xFF);
        target[offset + 1] = (byte) ((value >> 16) & 0xFF);
        target[offset + 2] = (byte) ((value >> 8) & 0xFF);
        target[offset + 3] = (byte) (value & 0xFF);
    }

    private static void skipFully(@NonNull DataInputStream in, long bytes) throws IOException {
        long remaining = bytes;
        while (remaining > 0) {
            long skipped = in.skip(remaining);
            if (skipped <= 0) {
                if (in.read() < 0) throw new IOException("流已结束");
                skipped = 1;
            }
            remaining -= skipped;
        }
    }
}
