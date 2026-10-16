package com.typheye.wgpro.core.emu;

import androidx.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;

import io.grpc.CallOptions;
import io.grpc.ManagedChannel;
import io.grpc.MethodDescriptor;
import io.grpc.okhttp.OkHttpChannelBuilder;
import io.grpc.stub.ClientCalls;

/**
 * Vela 引擎的取帧 / 注入客户端。
 *
 * <p>引擎（Android 模拟器体系）通过 gRPC 暴露 {@code EmulatorController}：
 * <ul>
 *   <li>{@code getScreenshot} → 返回 {@code Image}，其中 <b>field 4 是 PNG 字节</b>；</li>
 *   <li>{@code sendTouch} → 注入触摸（{@code TouchEvent{touches=[Touch{x,y,id,pressure}]}}）。</li>
 * </ul>
 * 这里用"原始字节 marshaller"直接调用，**不需要 protoc 生成代码**（实测有效）。</p>
 */
public final class VelaFrames {

    private static final MethodDescriptor.Marshaller<byte[]> RAW =
            new MethodDescriptor.Marshaller<byte[]>() {
                @Override
                public java.io.InputStream stream(byte[] value) {
                    return new java.io.ByteArrayInputStream(value);
                }

                @Override
                public byte[] parse(java.io.InputStream stream) {
                    try {
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        byte[] buf = new byte[1 << 16];
                        int n;
                        while ((n = stream.read(buf)) > 0) {
                            out.write(buf, 0, n);
                        }
                        return out.toByteArray();
                    } catch (Exception error) {
                        throw new RuntimeException(error);
                    }
                }
            };

    private static MethodDescriptor<byte[], byte[]> method(@NonNull String name) {
        return MethodDescriptor.<byte[], byte[]>newBuilder()
                .setType(MethodDescriptor.MethodType.UNARY)
                .setFullMethodName("android.emulation.control.EmulatorController/" + name)
                .setRequestMarshaller(RAW)
                .setResponseMarshaller(RAW)
                .build();
    }

    private static final MethodDescriptor<byte[], byte[]> GET_SCREENSHOT = method("getScreenshot");
    private static final MethodDescriptor<byte[], byte[]> SEND_TOUCH = method("sendTouch");
    private static final MethodDescriptor<byte[], byte[]> SEND_MOUSE = method("sendMouse");
    private static final MethodDescriptor<byte[], byte[]> STREAM_SCREENSHOT = method("streamScreenshot");
    private static final MethodDescriptor<byte[], byte[]> SEND_KEY = method("sendKey");

    /** ImageFormat{format=RGBA8888}: field1 varint = 1（免 PNG 编解码，性能关键）。 */
    private static final byte[] RGBA_REQUEST = {0x08, 0x01};

    private final ManagedChannel channel;

    /** 只对前几帧打一次尺寸诊断（排查黑屏用）。 */
    private int diagnostics;

    public VelaFrames(int port) {
        channel = OkHttpChannelBuilder.forAddress("127.0.0.1", port)
                .usePlaintext()
                .build();
    }

    /**
     * 帧回调：直接给**原始 RGBA 像素**，由上层复用同一张 Bitmap 填入，
     * 避免每帧 new Bitmap（60fps 下会产生大量分配与拷贝，是主要的 CPU 开销）。
     */
    public interface FrameListener {
        void onPixels(int width, int height, @NonNull byte[] rgba);
    }

    /**
     * 流式取帧：官方 {@code streamScreenshot} + **RGBA8888 直通**（不做 PNG 编解码）。
     *
     * <p>相比"每 250ms 拉一张 PNG"，这里由模拟器主动推帧，且省掉两条最贵的路径
     * （服务端 PNG 编码、客户端 PNG 解码）—— 这是卡顿的主要来源。</p>
     */
    public void streamFrames(@NonNull FrameListener listener, @NonNull java.util.concurrent.atomic.AtomicBoolean running) {
        try {
            java.util.Iterator<byte[]> frames = ClientCalls.blockingServerStreamingCall(
                    channel, STREAM_SCREENSHOT, CallOptions.DEFAULT, RGBA_REQUEST);
            while (running.get() && frames.hasNext()) {
                byte[] reply = frames.next();
                byte[] pixels = fieldBytes(reply, 4);
                // ★ 尺寸要读 **Image.format.width/height**（顶层 field1 是 ImageFormat，
                //   它的 field3/field4 才是宽高）。顶层的 Image.width/height 已标 deprecated
                //   且实测恒为 0；以前只按"像素数开方"当正方形（表盘）推断 ——
                //   手环/音箱这类**非方屏** sqrt(count) 不是整数 → 每帧都被丢掉 → **黑屏**（就是它）。
                byte[] format = fieldBytes(reply, 1);
                int formatWidth = format == null ? 0 : (int) fieldVarint(format, 3);
                int formatHeight = format == null ? 0 : (int) fieldVarint(format, 4);
                int width = formatWidth;
                int height = formatHeight;
                if (width <= 0 || height <= 0) {
                    // 老引擎兜底：顶层（deprecated）
                    width = (int) fieldVarint(reply, 2);
                    height = (int) fieldVarint(reply, 3);
                }
                if (pixels != null && (width <= 0 || height <= 0)) {
                    // 最后兜底：按像素数开方（仅对方屏有效）
                    int count = pixels.length / 4;
                    int side = (int) Math.round(Math.sqrt(count));
                    if (side > 0 && side * side == count) {
                        width = side;
                        height = side;
                    }
                }
                if (diagnostics < 5) {
                    diagnostics++;
                    android.util.Log.i("VelaFrames", "frame#" + diagnostics
                            + " format=" + formatWidth + "x" + formatHeight
                            + " top=" + fieldVarint(reply, 2) + "x" + fieldVarint(reply, 3)
                            + " pixels=" + (pixels == null ? -1 : pixels.length)
                            + " -> " + width + "x" + height);
                }
                if (width <= 0 || height <= 0 || pixels == null
                        || pixels.length < width * height * 4) {
                    continue;
                }
                listener.onPixels(width, height, pixels);
            }
        } catch (Exception error) {
            if (running.get()) {
                android.util.Log.w("VelaFrames", "streamScreenshot 结束", error);
            }
        }
    }

    /** 发送按键（Evdev 键码；keypress=2）。 */
    public boolean key(int evdevCode) {
        try {
            byte[] event = proto(vint(1, 1 /*Evdev*/), vint(2, 2 /*keypress*/), vint(3, evdevCode));
            ClientCalls.blockingUnaryCall(channel, SEND_KEY,
                    CallOptions.DEFAULT.withDeadlineAfter(2, TimeUnit.SECONDS), event);
            return true;
        } catch (Exception error) {
            android.util.Log.w("VelaFrames", "sendKey 失败 code=" + evdevCode, error);
            return false;
        }
    }

    /** 读一个 varint 字段（用于 Image 的 width/height）。 */
    private static long fieldVarint(@NonNull byte[] data, int wanted) {
        int[] cursor = {0};
        while (cursor[0] < data.length) {
            long key = readVarint(data, cursor);
            int field = (int) (key >>> 3);
            int wire = (int) (key & 7);
            if (wire == 0) {
                long value = readVarint(data, cursor);
                if (field == wanted) {
                    return value;
                }
            } else if (wire == 2) {
                int len = (int) readVarint(data, cursor);
                if (len < 0 || cursor[0] + len > data.length) {
                    return 0;
                }
                cursor[0] += len;
            } else {
                return 0;
            }
        }
        return 0;
    }

    /** 取一帧 PNG；失败返回 null。 */
    public byte[] screenshot() {
        try {
            byte[] reply = ClientCalls.blockingUnaryCall(channel, GET_SCREENSHOT,
                    CallOptions.DEFAULT.withDeadlineAfter(4, TimeUnit.SECONDS), new byte[0]);
            return fieldBytes(reply, 4);
        } catch (Exception error) {
            return null;
        }
    }

    /**
     * 注入鼠标事件 —— **Vela 客机真正响应的输入通道**。
     *
     * <p>实测结论（与本项目同源的参考实现验证过）：点按/长按/滑动都要走
     * {@code sendMouse}（{@code MouseEvent{x=1,y=2,buttons=3}}），
     * {@code sendTouch} 对 Vela 客机**完全无效**（帧缓冲变化为 0）。
     * 语义：{@code buttons=1} 按下、{@code 0} 抬手；长按=按住期间重复发送 1。</p>
     */
    public boolean mouse(int x, int y, int buttons) {
        try {
            byte[] event = proto(vint(1, x), vint(2, y), vint(3, buttons));
            ClientCalls.blockingUnaryCall(channel, SEND_MOUSE,
                    CallOptions.DEFAULT.withDeadlineAfter(2, TimeUnit.SECONDS), event);
            return true;
        } catch (Exception error) {
            android.util.Log.w("VelaTouch", "sendMouse 失败 (" + x + "," + y + ") buttons="
                    + buttons, error);
            return false;
        }
    }

    /** 注入一次点击（按下并立即抬手）。 */
    public boolean tap(int x, int y) {
        boolean down = touch(x, y, 1, true);
        boolean up = touch(x, y, 1, false);
        return down && up;
    }

    /**
     * 注入一次触摸事件。
     *
     * <p>按官方 proto：{@code pressure} 必须 > 0 表示按下，**归 0 表示抬手**；
     * {@code identifier} 在同一次手势内保持不变。</p>
     */
    public boolean touch(int x, int y, int identifier, boolean down) {
        try {
            byte[] touch = proto(
                    vint(1, x), vint(2, y), vint(3, identifier),
                    vint(4, down ? 255 : 0), vint(5, 20), vint(6, 20));
            byte[] event = bytes(1, touch);
            ClientCalls.blockingUnaryCall(channel, SEND_TOUCH,
                    CallOptions.DEFAULT.withDeadlineAfter(2, TimeUnit.SECONDS), event);
            return true;
        } catch (Exception error) {
            android.util.Log.w("VelaTouch", "sendTouch 失败 (" + x + "," + y + ") id="
                    + identifier + (down ? " down" : " up"), error);
            return false;
        }
    }

    public void close() {
        try {
            channel.shutdownNow();
        } catch (Exception ignored) {
        }
    }

    // ------------------------------------------------------- 极简 protobuf 读写

    private static byte[] vint(int field, int value) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        writeVarint(out, (field << 3) | 0);
        writeVarint(out, value);
        return out.toByteArray();
    }

    private static byte[] bytes(int field, byte[] data) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        writeVarint(out, (field << 3) | 2);
        writeVarint(out, data.length);
        out.write(data, 0, data.length);
        return out.toByteArray();
    }

    private static byte[] proto(byte[]... parts) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (byte[] part : parts) {
            out.write(part, 0, part.length);
        }
        return out.toByteArray();
    }

    private static void writeVarint(@NonNull ByteArrayOutputStream out, long value) {
        while (true) {
            if ((value & ~0x7FL) == 0) {
                out.write((int) value);
                return;
            }
            out.write((int) ((value & 0x7F) | 0x80));
            value >>>= 7;
        }
    }

    private static long readVarint(@NonNull byte[] data, int[] cursor) {
        long value = 0;
        int shift = 0;
        while (cursor[0] < data.length) {
            int b = data[cursor[0]++] & 0xFF;
            value |= (long) (b & 0x7F) << shift;
            if ((b & 0x80) == 0) {
                break;
            }
            shift += 7;
        }
        return value;
    }

    /** 取出指定 field 的长度分隔内容（不解析嵌套，够用即可）。 */
    private static byte[] fieldBytes(@NonNull byte[] data, int wanted) {
        int[] cursor = {0};
        while (cursor[0] < data.length) {
            long key = readVarint(data, cursor);
            int field = (int) (key >>> 3);
            int wire = (int) (key & 7);
            if (wire == 0) {
                readVarint(data, cursor);
            } else if (wire == 2) {
                int len = (int) readVarint(data, cursor);
                if (len < 0 || cursor[0] + len > data.length) {
                    return null;
                }
                if (field == wanted) {
                    byte[] out = new byte[len];
                    System.arraycopy(data, cursor[0], out, 0, len);
                    return out;
                }
                cursor[0] += len;
            } else {
                return null;
            }
        }
        return null;
    }
}
