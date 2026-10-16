package com.typheye.wgpro.core.emu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import io.grpc.CallOptions;
import io.grpc.ManagedChannel;
import io.grpc.MethodDescriptor;
import io.grpc.StatusRuntimeException;
import io.grpc.okhttp.OkHttpChannelBuilder;
import io.grpc.stub.ClientCalls;

/**
 * Vela 引擎的**设备控制**客户端（电池 / GPS / 传感器 / 剪贴板 / 虚拟机状态 / 亮度 / 按键）。
 *
 * <p>与 {@link VelaFrames} 同一套路：用「原始字节 marshaller」直接调 gRPC，
 * 按 {@code emulator_controller.proto} 的字段号手写 protobuf，**不需要 protoc 生成代码**。</p>
 *
 * <p><b>能力矩阵是本机实测的</b>（x86_64 试验台 + 与 App 包同源镜像，见
 * {@code _guide/vela-multi-device-goal.md} §3.5）：Vela 引擎实现了
 * battery / gps / sensor / clipboard / vmState / brightness / sendKey / sendMouse；
 * <b>没有</b>实现 injectWheel（UNIMPLEMENTED）、setPosture（FAILED_PRECONDITION）、
 * setDisplayConfigurations / getDisplayMode（multi-display、resizable 均不可用），
 * getLogcat / streamLogcat 返回空。所以调用方要对失败做降级，不要假定一定成功。</p>
 */
public final class VelaControl {

    private static final String SERVICE = "android.emulation.control.EmulatorController/";

    // ---------------------------------------------------------------- 常量表（proto 枚举）
    /** {@code SensorValue.SensorType}。 */
    public static final int SENSOR_ACCELERATION = 0;
    public static final int SENSOR_GYROSCOPE = 1;
    public static final int SENSOR_MAGNETIC_FIELD = 2;
    public static final int SENSOR_ORIENTATION = 3;
    public static final int SENSOR_TEMPERATURE = 4;
    public static final int SENSOR_PROXIMITY = 5;
    public static final int SENSOR_LIGHT = 6;
    public static final int SENSOR_PRESSURE = 7;
    public static final int SENSOR_HUMIDITY = 8;
    public static final int SENSOR_HEART_RATE = 14;
    public static final int SENSOR_RGBC_LIGHT = 15;

    /** {@code VmRunState.RunState}。 */
    public static final int VM_UNKNOWN = 0;
    public static final int VM_RUNNING = 1;
    public static final int VM_PAUSED = 3;
    public static final int VM_SHUTDOWN = 5;
    public static final int VM_TERMINATE = 7;
    public static final int VM_RESET = 9;

    /** {@code KeyboardEvent.KeyCodeType}。 */
    public static final int KEYCODE_USB = 0;
    public static final int KEYCODE_EVDEV = 1;
    /** {@code KeyboardEvent.KeyEventType}。 */
    public static final int KEY_KEYDOWN = 0;
    public static final int KEY_KEYUP = 1;
    public static final int KEY_KEYPRESS = 2;

    /** 引擎返回「该能力没实现 / 不可用」时统一给一句人话。 */
    public static final String UNSUPPORTED = "该能力在当前引擎上不可用";

    private final ManagedChannel channel;

    public VelaControl(int grpcPort) {
        channel = OkHttpChannelBuilder.forAddress("127.0.0.1", grpcPort)
                .usePlaintext()
                .build();
    }

    // ---------------------------------------------------------------- 电池

    /** 电池状态；引擎不支持时返回 null。 */
    @Nullable
    public Battery battery() {
        Reply reply = call("getBattery", new byte[0], 2000);
        if (!reply.ok) {
            return null;
        }
        Battery state = new Battery();
        state.hasBattery = fieldVarint(reply.body, 1) != 0;
        state.present = fieldVarint(reply.body, 2) != 0;
        state.charger = (int) fieldVarint(reply.body, 3);
        state.level = (int) fieldVarint(reply.body, 4);
        state.health = (int) fieldVarint(reply.body, 5);
        state.status = (int) fieldVarint(reply.body, 6);
        return state;
    }

    /** 设置电量（0-100）与是否充电（对齐 IDE「扩展控制里的电池」）。 */
    public boolean setBattery(int level, boolean charging) {
        int percent = Math.max(0, Math.min(100, level));
        byte[] payload = proto(
                vint(1, 1),                                   // hasBattery
                vint(2, 1),                                   // isPresent
                vint(3, charging ? 1 : 0),                    // charger: AC / NONE
                vint(4, percent),                             // chargeLevel
                vint(5, 0),                                   // health: GOOD
                vint(6, charging ? 1 : 2));                   // status: CHARGING / DISCHARGING
        return call("setBattery", payload, 2000).ok;
    }

    /** 电池状态快照。 */
    public static final class Battery {
        public boolean hasBattery;
        public boolean present;
        /** 0=NONE 1=AC 2=USB 3=WIRELESS。 */
        public int charger;
        /** 0-100。 */
        public int level;
        /** 0=GOOD 1=FAILED 2=DEAD 3=OVERVOLTAGE 4=OVERHEATED。 */
        public int health;
        /** 0=UNKNOWN 1=CHARGING 2=DISCHARGING 3=NOT_CHARGING 4=FULL。 */
        public int status;

        @NonNull
        public String describe() {
            String charging;
            switch (status) {
                case 1: charging = "充电中"; break;
                case 2: charging = "放电中"; break;
                case 3: charging = "未充电"; break;
                case 4: charging = "已充满"; break;
                default: charging = "未知"; break;
            }
            return level + "% · " + charging
                    + " · " + (present ? "在位" : "不在位");
        }
    }

    // ---------------------------------------------------------------- GPS

    /** GPS 状态；引擎不支持时返回 null。 */
    @Nullable
    public Gps gps() {
        Reply reply = call("getGps", new byte[0], 2000);
        if (!reply.ok) {
            return null;
        }
        Gps state = new Gps();
        state.passiveUpdate = fieldVarint(reply.body, 1) != 0;
        state.latitude = fieldDouble(reply.body, 2);
        state.longitude = fieldDouble(reply.body, 3);
        state.speed = fieldDouble(reply.body, 4);
        state.bearing = fieldDouble(reply.body, 5);
        state.altitude = fieldDouble(reply.body, 6);
        state.satellites = (int) fieldVarint(reply.body, 7);
        return state;
    }

    /** 设置 GPS 坐标（度）；altitude 传 0 即可。 */
    public boolean setGps(double latitude, double longitude, double altitude) {
        byte[] payload = proto(
                vint(1, 0),                    // passiveUpdate = false（否则 LocationUI 会覆盖）
                dbl(2, latitude),
                dbl(3, longitude),
                dbl(4, 0d),
                dbl(5, 0d),
                dbl(6, altitude),
                vint(7, 8));                   // satellites
        return call("setGps", payload, 2000).ok;
    }

    /** GPS 状态快照。 */
    public static final class Gps {
        public boolean passiveUpdate;
        public double latitude;
        public double longitude;
        public double speed;
        public double bearing;
        public double altitude;
        public int satellites;

        @NonNull
        public String describe() {
            return String.format(java.util.Locale.US, "%.5f, %.5f · 卫星 %d",
                    latitude, longitude, satellites);
        }
    }

    // ---------------------------------------------------------------- 传感器

    /** 读传感器三轴值；不支持或失败时返回 null。 */
    @Nullable
    public float[] sensor(int type) {
        Reply reply = call("getSensor", vint(1, type), 2000);
        if (!reply.ok) {
            return null;
        }
        byte[] value = fieldBytes(reply.body, 3);
        if (value == null) {
            return null;
        }
        byte[] packed = fieldBytes(value, 1);
        if (packed == null || packed.length < 4) {
            return null;
        }
        int count = packed.length / 4;
        float[] data = new float[count];
        ByteBuffer buffer = ByteBuffer.wrap(packed).order(ByteOrder.LITTLE_ENDIAN);
        for (int i = 0; i < count; i++) {
            data[i] = buffer.getFloat();
        }
        return data;
    }

    /** 写传感器三轴值（例如模拟抬手/倾斜）。 */
    public boolean setSensor(int type, @NonNull float[] data) {
        ByteBuffer packed = ByteBuffer.allocate(data.length * 4).order(ByteOrder.LITTLE_ENDIAN);
        for (float item : data) {
            packed.putFloat(item);
        }
        byte[] payload = proto(vint(1, type), bytes(3, bytes(1, packed.array())));
        return call("setSensor", payload, 2000).ok;
    }

    // ---------------------------------------------------------------- 剪贴板

    /** 读客机剪贴板；不支持时返回 null。 */
    @Nullable
    public String clipboard() {
        Reply reply = call("getClipboard", new byte[0], 2000);
        if (!reply.ok) {
            return null;
        }
        byte[] text = fieldBytes(reply.body, 1);
        return text == null ? "" : new String(text, StandardCharsets.UTF_8);
    }

    /** 写客机剪贴板（IDE「扩展控制 → 剪贴板」同款）。 */
    public boolean setClipboard(@NonNull String text) {
        return call("setClipboard", bytes(1, text.getBytes(StandardCharsets.UTF_8)), 2000).ok;
    }

    // ---------------------------------------------------------------- 虚拟机状态 / 亮度

    /**
     * 等 gRPC 通道可用（引擎冷启动时端口 LISTEN 要十几秒，**立刻调用会全部 UNAVAILABLE**）。
     *
     * <p>实测踩过：{@code manager.start()} 返回不等 engines 就绪，直接读电池/GPS 会拿到
     * gRPC UNAVAILABLE，看起来像「引擎不支持这些能力」——其实只是没连上。</p>
     */
    public boolean awaitReady(long timeoutMs) {
        long deadline = System.currentTimeMillis() + Math.max(1000L, timeoutMs);
        while (System.currentTimeMillis() < deadline) {
            if (call("getVmState", new byte[0], 1500).ok) {
                return true;
            }
            try {
                Thread.sleep(500L);
            } catch (InterruptedException interrupted) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return false;
    }

    /** 读虚拟机运行状态（{@link #VM_RUNNING} 等）；失败返回 -1。 */
    public int vmState() {
        Reply reply = call("getVmState", new byte[0], 2000);
        return reply.ok ? (int) fieldVarint(reply.body, 1) : -1;
    }

    /** 切虚拟机状态（暂停 / 继续 / 关机 / 重启）。 */
    public boolean setVmState(int state) {
        return call("setVmState", vint(1, state), 3000).ok;
    }

    /** 读背光亮度（0-255）；不支持返回 -1。 */
    public int brightness() {
        Reply reply = call("getBrightness", vint(1, 0), 2000);
        return reply.ok ? (int) fieldVarint(reply.body, 2) : -1;
    }

    /** 设置背光亮度（0-255）。 */
    public boolean setBrightness(int value) {
        int level = Math.max(0, Math.min(255, value));
        return call("setBrightness", proto(vint(1, 0), vint(2, level)), 2000).ok;
    }

    // ---------------------------------------------------------------- 按键

    /** 单次按键事件（默认 Evdev 码）。 */
    public boolean key(int evdevCode) {
        return keyEvent(KEYCODE_EVDEV, KEY_KEYPRESS, evdevCode);
    }

    /** 组合键：按顺序按下所有键，再逆序抬起（gRPC sendKey 的 keydown/keyup）。 */
    public boolean combo(int... evdevCodes) {
        boolean ok = true;
        for (int code : evdevCodes) {
            ok &= keyEvent(KEYCODE_EVDEV, KEY_KEYDOWN, code);
        }
        for (int i = evdevCodes.length - 1; i >= 0; i--) {
            ok &= keyEvent(KEYCODE_EVDEV, KEY_KEYUP, evdevCodes[i]);
        }
        return ok;
    }

    /** 原始 sendKey：codeType 见 {@code KeyboardEvent.KeyCodeType}。 */
    public boolean keyEvent(int codeType, int eventType, int code) {
        byte[] event = proto(vint(1, codeType), vint(2, eventType), vint(3, code));
        return call("sendKey", event, 2000).ok;
    }

    // ---------------------------------------------------------------- 基础设施

    /** gRPC 调用结果：{@code error} 为空即成功。 */
    public static final class Reply {
        public final byte[] body;
        @Nullable public final String error;
        /** {@code true} = 调用成功（{@link #error} 为空）。 */
        public final boolean ok;

        Reply(@NonNull byte[] body, @Nullable String error) {
            this.body = body;
            this.error = error;
            this.ok = error == null;
        }
    }

    /** 把所有失败都收敛成「错误文案」，调用方不需要处理 gRPC 异常。 */
    @NonNull
    public Reply call(@NonNull String method, @NonNull byte[] payload, long timeoutMs) {
        try {
            byte[] reply = ClientCalls.blockingUnaryCall(channel, unary(method),
                    CallOptions.DEFAULT.withDeadlineAfter(timeoutMs, TimeUnit.MILLISECONDS),
                    payload);
            return new Reply(reply == null ? new byte[0] : reply, null);
        } catch (StatusRuntimeException error) {
            String message = error.getStatus().getDescription();
            if (message == null || message.trim().isEmpty()) {
                message = error.getStatus().getCode().name();
            }
            return new Reply(new byte[0], message);
        } catch (Exception error) {
            return new Reply(new byte[0], error.getClass().getSimpleName()
                    + (error.getMessage() == null ? "" : ": " + error.getMessage()));
        }
    }

    public void close() {
        try {
            channel.shutdownNow();
        } catch (Exception ignored) {
        }
    }

    // ------------------------------------------------------- 极简 protobuf 读写

    private static final MethodDescriptor.Marshaller<byte[]> RAW =
            new MethodDescriptor.Marshaller<byte[]>() {
                @Override
                public java.io.InputStream stream(byte[] value) {
                    return new ByteArrayInputStream(value);
                }

                @Override
                public byte[] parse(java.io.InputStream stream) {
                    try {
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        byte[] buffer = new byte[1 << 16];
                        int count;
                        while ((count = stream.read(buffer)) > 0) {
                            out.write(buffer, 0, count);
                        }
                        return out.toByteArray();
                    } catch (Exception error) {
                        throw new RuntimeException(error);
                    }
                }
            };

    private static MethodDescriptor<byte[], byte[]> unary(@NonNull String name) {
        return MethodDescriptor.<byte[], byte[]>newBuilder()
                .setType(MethodDescriptor.MethodType.UNARY)
                .setFullMethodName(SERVICE + name)
                .setRequestMarshaller(RAW)
                .setResponseMarshaller(RAW)
                .build();
    }

    private static byte[] vint(int field, int value) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        writeVarint(out, (field << 3) | 0);
        writeVarint(out, value);
        return out.toByteArray();
    }

    private static byte[] dbl(int field, double value) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        writeVarint(out, (field << 3) | 1);
        byte[] raw = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN)
                .putDouble(value).array();
        out.write(raw, 0, raw.length);
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

    private static long readVarint(@NonNull byte[] data, @NonNull int[] cursor) {
        long value = 0;
        int shift = 0;
        while (cursor[0] < data.length) {
            int current = data[cursor[0]++] & 0xFF;
            value |= (long) (current & 0x7F) << shift;
            if ((current & 0x80) == 0) {
                break;
            }
            shift += 7;
        }
        return value;
    }

    /** 读一个 varint 字段（找不到返回 0）。 */
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
            } else if (wire == 1) {
                cursor[0] += 8;
            } else if (wire == 2) {
                int length = (int) readVarint(data, cursor);
                if (length < 0 || cursor[0] + length > data.length) {
                    return 0;
                }
                cursor[0] += length;
            } else if (wire == 5) {
                cursor[0] += 4;
            } else {
                return 0;
            }
        }
        return 0;
    }

    /** 读一个 double 字段（找不到返回 0）。 */
    private static double fieldDouble(@NonNull byte[] data, int wanted) {
        int[] cursor = {0};
        while (cursor[0] < data.length) {
            long key = readVarint(data, cursor);
            int field = (int) (key >>> 3);
            int wire = (int) (key & 7);
            if (wire == 1) {
                if (cursor[0] + 8 > data.length) {
                    return 0;
                }
                double value = ByteBuffer.wrap(data, cursor[0], 8)
                        .order(ByteOrder.LITTLE_ENDIAN).getDouble();
                cursor[0] += 8;
                if (field == wanted) {
                    return value;
                }
            } else if (wire == 0) {
                readVarint(data, cursor);
            } else if (wire == 2) {
                int length = (int) readVarint(data, cursor);
                if (length < 0 || cursor[0] + length > data.length) {
                    return 0;
                }
                cursor[0] += length;
            } else if (wire == 5) {
                cursor[0] += 4;
            } else {
                return 0;
            }
        }
        return 0;
    }

    /** 取出指定 field 的长度分隔内容。 */
    @Nullable
    private static byte[] fieldBytes(@NonNull byte[] data, int wanted) {
        int[] cursor = {0};
        while (cursor[0] < data.length) {
            long key = readVarint(data, cursor);
            int field = (int) (key >>> 3);
            int wire = (int) (key & 7);
            if (wire == 0) {
                readVarint(data, cursor);
            } else if (wire == 1) {
                cursor[0] += 8;
            } else if (wire == 2) {
                int length = (int) readVarint(data, cursor);
                if (length < 0 || cursor[0] + length > data.length) {
                    return null;
                }
                if (field == wanted) {
                    byte[] out = new byte[length];
                    System.arraycopy(data, cursor[0], out, 0, length);
                    return out;
                }
                cursor[0] += length;
            } else if (wire == 5) {
                cursor[0] += 4;
            } else {
                return null;
            }
        }
        return null;
    }
}
