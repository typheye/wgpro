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

    private final ManagedChannel channel;

    public VelaFrames(int port) {
        channel = OkHttpChannelBuilder.forAddress("127.0.0.1", port)
                .usePlaintext()
                .build();
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
