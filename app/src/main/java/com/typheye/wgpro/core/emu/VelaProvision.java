package com.typheye.wgpro.core.emu;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.typheye.wgpro.data.DeviceDatabase;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 虚拟设备的"装机"流程：把已下载的镜像包解包到运行目录、铺 AVD、写设备库并起默认名。
 *
 * <p>创建设备页与设备详情页共用，避免两处各写一份。</p>
 */
public final class VelaProvision {

    /** 下载服务把镜像包落在这里：{@code files/downloads/vela-<机型>.apk}。 */
    public static final String PACKAGE_PREFIX = "vela-";

    private VelaProvision() {
    }

    public interface Callback {
        void onResult(boolean success, @Nullable String error);
    }

    /** 镜像包文件（已按 {@code apk} 后缀交给 DownloadService 落盘）。 */
    @NonNull
    public static File packageFile(@NonNull Context context, @NonNull String modelId) {
        return new File(new File(context.getFilesDir(), "downloads"),
                PACKAGE_PREFIX + modelId + ".apk");
    }

    /** 该机型是否已经创建过（设备库里存在即算创建）。 */
    /** 从设备 id 解析机型标识：{@code virtual:<机型>[:<随机>]} → {@code <机型>}。 */
    @NonNull
    public static String modelIdOf(@Nullable String deviceId) {
        if (deviceId == null || !deviceId.startsWith("virtual:")) {
            return "";
        }
        String rest = deviceId.substring("virtual:".length());
        int cut = rest.indexOf(':');
        return cut < 0 ? rest : rest.substring(0, cut);
    }

    /** 设备 id 是否属于该机型（同一机型可以有多个设备）。 */
    public static boolean isSameModel(@Nullable String deviceId, @NonNull String modelId) {
        return modelId.equals(modelIdOf(deviceId));
    }

    /** 该机型是否已经创建过设备（同一机型可以有多个，判断"有没有"即可）。 */
    public static boolean isCreated(@NonNull Context context, @NonNull String modelId) {
        try (DeviceDatabase db = new DeviceDatabase(context);
             android.database.Cursor cursor = db.all(DeviceDatabase.TYPE_VIRTUAL)) {
            while (cursor.moveToNext()) {
                if (isSameModel(cursor.getString(cursor.getColumnIndexOrThrow("id")), modelId)) {
                    return true;
                }
            }
        } catch (Exception error) {
            return false;
        }
        return false;
    }

    /**
     * 删除设备：移除设备库记录 + 已下载的镜像包。
     *
     * <p>运行目录（{@code files/vela}）是**多个设备共用**的引擎+镜像，不在这里删，
     * 否则会把其它设备一起弄坏。</p>
     */
    public static void remove(@NonNull Context context, @NonNull String modelId) {
        try (DeviceDatabase db = new DeviceDatabase(context)) {
            db.remove(VelaEmulatorManager.deviceId(modelId));
        } catch (Exception error) {
            android.util.Log.w("VelaProvision", "删除设备记录失败", error);
        }
        removeImage(context, modelId);
    }

    /**
     * **只删除已下载的镜像**（含下载记录），**不动已经创建的设备**。
     *
     * <p>设备详情页的「删除」就是这个语义：删掉镜像后按钮回到「获取」，模拟设备列表里的设备仍在。</p>
     */
    public static void removeImage(@NonNull Context context, @NonNull String modelId) {
        File zip = packageFile(context, modelId);
        if (zip.isFile()) {
            //noinspection ResultOfMethodCallIgnored
            zip.delete();
        }
        com.typheye.wgpro.core.download.DownloadManager.get()
                .drop(PACKAGE_PREFIX + modelId);
    }

    /** 四位随机大写字母/数字后缀。 */
    @NonNull
    public static String randomSuffix() {
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder suffix = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            suffix.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return suffix.toString();
    }

    /** 默认设备名：{@code 设备名称 XXXX}（设备详情页会用「机型名 XXXX」，这里只是兜底）。 */
    @NonNull
    public static String defaultDeviceName() {
        return "设备名称 " + randomSuffix();
    }

    /**
     * 解包 → 铺 AVD → 写库（默认名）。回调在主线程之外的调用线程上执行，
     * 调用方需要自己切回主线程刷新 UI。
     */
    public static void provision(@NonNull Context context, @NonNull String modelId,
                                 @NonNull Callback callback) {
        provision(context, modelId, null, callback);
    }

    /**
     * @param displayName 设备显示名；为 null / 空则用默认名「设备名称 XXXX」
     */
    public static void provision(@NonNull Context context, @NonNull String modelId,
                                 @Nullable String displayName, @NonNull Callback callback) {
        try {
            File zip = packageFile(context, modelId);
            if (!zip.isFile()) {
                callback.onResult(false, "镜像包不存在，请先下载");
                return;
            }
            VelaEmulator emulator = new VelaEmulator(context);
            unzip(zip, emulator.runtimeDir());
            emulator.installExecLinks();
            emulator.authorAvd(modelId, emulator.imagesDir());
            try (DeviceDatabase db = new DeviceDatabase(context)) {
                // **model 列存的就是设备显示名**：{云端设备名称} + 空格 + 4 位随机
                // （机型标识只从设备 id「virtual:<机型>[:<随机>]」里解析，别再往 model 列塞标识）
                String deviceId = VelaEmulatorManager.deviceId(modelId) + ":" + randomSuffix();
                String name = displayName == null || displayName.trim().isEmpty()
                        ? defaultDeviceName() : displayName.trim();
                db.upsert(deviceId, name, DeviceDatabase.TYPE_VIRTUAL, false);
                db.updateNote(deviceId, "");
            }
            callback.onResult(true, null);
        } catch (Exception error) {
            callback.onResult(false, error.getMessage() == null ? "创建失败" : error.getMessage());
        }
    }

    private static void unzip(@NonNull File zip, @NonNull File targetDir) throws Exception {
        if (!targetDir.exists() && !targetDir.mkdirs()) {
            throw new IllegalStateException("无法创建运行目录");
        }
        String root = targetDir.getCanonicalPath();
        try (InputStream raw = new BufferedInputStream(new FileInputStream(zip));
             ZipInputStream in = new ZipInputStream(raw)) {
            ZipEntry entry;
            while ((entry = in.getNextEntry()) != null) {
                File out = new File(targetDir, entry.getName());
                if (!out.getCanonicalPath().startsWith(root)) {
                    throw new IllegalStateException("压缩包内容非法");
                }
                if (entry.isDirectory()) {
                    //noinspection ResultOfMethodCallIgnored
                    out.mkdirs();
                    continue;
                }
                File parent = out.getParentFile();
                if (parent != null && !parent.exists()) {
                    //noinspection ResultOfMethodCallIgnored
                    parent.mkdirs();
                }
                try (java.io.OutputStream output = new java.io.FileOutputStream(out)) {
                    byte[] buffer = new byte[64 * 1024];
                    int length;
                    while ((length = in.read(buffer)) > 0) {
                        output.write(buffer, 0, length);
                    }
                }
            }
        }
    }
}
