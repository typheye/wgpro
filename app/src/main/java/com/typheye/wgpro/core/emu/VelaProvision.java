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
 * 模拟设备的"装机"流程：把已下载的镜像包解包到运行目录、铺 AVD、写设备库并起默认名。
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

    /** 设备 id → 运行时目录名（与 {@link VelaEmulator#runtimeDir()} 同一套转义）。 */
    @NonNull
    public static String runtimeDirName(@NonNull String deviceId) {
        if (deviceId.isEmpty()) {
            return "_default";
        }
        return deviceId.replaceAll("[^A-Za-z0-9._-]", "_");
    }

    /**
     * 清掉"设备库里已经不存在"的实例目录（孤儿）：移除失败、卸载重装、老版本切目录都会留下它们，
     * 每个还占着 1.5G 左右。返回清掉的目录数。
     *
     * <p>只认设备库里的 id，**绝不误删正在用的实例**（在用的一定在库里）。</p>
     */
    public static int purgeOrphanRuntimes(@NonNull Context context) {
        int removed = 0;
        try {
            java.util.Set<String> alive = new java.util.HashSet<>();
            // 必须用 all()（全类型）：all(null) 会返回 0 行 → 把所有运行目录（含正在跑的）都当孤儿删掉
            try (DeviceDatabase db = new DeviceDatabase(context);
                 android.database.Cursor cursor = db.all()) {
                if (cursor != null) {
                    // devices 表主键列名就是 "id"（改成 device_id 会让列号对不上、误删实例目录）
                    int idColumn = cursor.getColumnIndex("id");
                    if (idColumn < 0) {
                        idColumn = 0;
                    }
                    while (cursor.moveToNext()) {
                        String id = cursor.getString(idColumn);
                        if (id != null && !id.isEmpty()) {
                            alive.add(runtimeDirName(id));
                        }
                    }
                }
            }
            File[] children = new File(context.getFilesDir(), "runtime").listFiles();
            if (children == null) {
                return 0;
            }
            for (File child : children) {
                if (!child.isDirectory() || alive.contains(child.getName())) {
                    continue;
                }
                deleteRecursively(child);
                removed++;
            }
        } catch (Exception error) {
            android.util.Log.w("VelaProvision", "清理残留模拟器目录失败", error);
        }
        return removed;
    }

    /**
     * 「移除设备」用：删掉该设备的**整个实例目录**（{@code files/runtime/<设备id>}，
     * 里面含引擎、镜像、AVD 与 home）。调用前请先
     * {@link VelaEmulatorManager#stop} 停掉引擎，否则 qemu 会继续占着文件与端口。
     */
    public static void purgeDevice(@NonNull Context context, @NonNull String deviceId) {
        try {
            VelaEmulator emulator = new VelaEmulator(context, modelIdOf(deviceId), deviceId);
            deleteRecursively(emulator.runtimeDir());
        } catch (Exception error) {
            android.util.Log.w("VelaProvision", "清理设备实例目录失败", error);
        }
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
            // 实例/目录都按**设备**独立：先算出设备 id，再往它自己的目录里解包
            final String newDeviceId = VelaEmulatorManager.deviceId(modelId) + ":" + randomSuffix();
            VelaEmulator emulator = unpackRuntime(context, modelId, newDeviceId);
            createDevice(context, emulator, newDeviceId, null, displayName);
            callback.onResult(true, null);
        } catch (Exception error) {
            callback.onResult(false, error.getMessage() == null ? "创建失败" : error.getMessage());
        }
    }

    /**
     * 只做"把镜像包铺进运行时目录"（{@code engine/} + {@code images/}），**不写设备库**。
     *
     * <p>拆出来的原因：铺完盘之后要弹窗让用户选"创建哪种模拟器"（皮肤），选完再落库。</p>
     */
    @NonNull
    public static VelaEmulator unpackRuntime(@NonNull Context context, @NonNull String modelId,
                                             @NonNull String deviceId) throws Exception {
        File zip = packageFile(context, modelId);
        if (!zip.isFile()) {
            throw new IllegalStateException("镜像包不存在，请先下载或从本地导入");
        }
        VelaEmulator emulator = new VelaEmulator(context, modelId, deviceId);
        // 先清掉早期版本的共享运行目录（约 1.5GB）：否则解包时两个目录并存，
        // 空间不够会**静默解包不全**（zip 里最后那个引擎二进制正好丢，表现就是 ENOENT）。
        purgeLegacySharedRuntime(context);
        // **先删 engine/**：上次装机把引擎真身改名成 *.elf、原路径留了软链，
        // 直接解包会穿过软链写到 /data/app（只读）→ 真身永远解不出来（踩过）。
        deleteRecursively(new File(emulator.runtimeDir(), "engine"));
        unzip(zip, emulator.runtimeDir());
        extractManifest(zip, emulator.runtimeDir());
        healEngineIfIncomplete(zip, emulator.runtimeDir());
        if (!hasRealEngine(new File(emulator.runtimeDir(), "engine/qemu/linux-aarch64"))) {
            throw new IllegalStateException("引擎解包不完整（多半是存储空间不足），请清理空间后重试");
        }
        emulator.installExecLinks();
        return emulator;
    }

    /**
     * 把包里的实例清单 {@code config.json}（设备代号/名称/皮肤/启动参数）单独抽到实例目录，
     * 供创建界面与命名使用；取不到就跳过（老包仍按扫描 skins 目录走）。
     */
    private static void extractManifest(@NonNull File zip, @NonNull File runtimeDir) {
        try (java.util.zip.ZipFile file = new java.util.zip.ZipFile(zip)) {
            java.util.zip.ZipEntry entry = file.getEntry("config.json");
            if (entry == null) {
                return;
            }
            File target = new File(runtimeDir, "config.json");
            try (java.io.InputStream in = file.getInputStream(entry);
                 java.io.OutputStream out = new java.io.FileOutputStream(target)) {
                byte[] buffer = new byte[1 << 16];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
            }
        } catch (Exception error) {
            android.util.Log.w("VelaProvision", "读取包内实例清单失败", error);
        }
    }

    /**
     * 读包内清单的 {@code launch} 段：{@code env} 填进 {@code env}、{@code extraArgs} 追加到 {@code args}。
     * 占位符 {@code {runtimeDir}} / {@code {avdId}} 会被替换。没有清单或字段就什么都不做。
     */
    public static void manifestLaunch(@NonNull File runtimeDir, @NonNull String avdId,
                                      @NonNull java.util.List<String> args,
                                      @NonNull java.util.Map<String, String> env) {
        File manifest = new File(runtimeDir, "config.json");
        if (!manifest.isFile()) {
            return;
        }
        try {
            String json = new String(java.nio.file.Files.readAllBytes(manifest.toPath()),
                    java.nio.charset.StandardCharsets.UTF_8);
            org.json.JSONObject launch = new org.json.JSONObject(json).optJSONObject("launch");
            if (launch == null) {
                return;
            }
            org.json.JSONObject envJson = launch.optJSONObject("env");
            if (envJson != null) {
                java.util.Iterator<String> keys = envJson.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    env.put(key, expandPlaceholders(envJson.optString(key, ""), runtimeDir, avdId));
                }
            }
            org.json.JSONArray extra = launch.optJSONArray("extraArgs");
            if (extra != null) {
                for (int index = 0; index < extra.length(); index++) {
                    args.add(expandPlaceholders(extra.optString(index, ""), runtimeDir, avdId));
                }
            }
        } catch (Exception error) {
            android.util.Log.w("VelaProvision", "读取实例启动参数失败", error);
        }
    }

    private static String expandPlaceholders(@NonNull String value, @NonNull File runtimeDir,
                                             @NonNull String avdId) {
        return value.replace("{runtimeDir}", runtimeDir.getAbsolutePath())
                .replace("{avdId}", avdId);
    }

    /**
     * 读实例目录里的清单，返回设备条目：{@code {code, name, skin}}；没有清单时返回空表。
     */
    @NonNull
    public static java.util.List<String[]> manifestDevices(@NonNull File runtimeDir) {
        java.util.List<String[]> result = new java.util.ArrayList<>();
        File manifest = new File(runtimeDir, "config.json");
        if (!manifest.isFile()) {
            return result;
        }
        try {
            String json = new String(java.nio.file.Files.readAllBytes(manifest.toPath()),
                    java.nio.charset.StandardCharsets.UTF_8);
            org.json.JSONObject root = new org.json.JSONObject(json);
            org.json.JSONArray devices = root.optJSONArray("devices");
            if (devices == null) {
                return result;
            }
            for (int index = 0; index < devices.length(); index++) {
                org.json.JSONObject device = devices.optJSONObject(index);
                if (device == null) {
                    continue;
                }
                String code = device.optString("code", "");
                String skin = device.optString("skin", code);
                String name = device.optString("name", "").trim();
                // 包内没写展示名、或直接把代号当名字（当前 v5.0 包就是这样）时，
                // 用皮肤的通用展示名兜底（Xiaomi 手环 10 / Xiaomi Watch S4 …）——
                // 这样"设备名用清单里的名字"成立，又不会出现 xiaomi_band_10 这种代号。
                if (name.isEmpty() || name.equals(code)) {
                    name = VelaEmulator.skinLabel(skin);
                }
                if (!skin.isEmpty()) {
                    // 第 4 位是镜像档（IDE 的 ide.image.type）；包内没写就给空串，由 createDevice 按品类兜底
                    result.add(new String[]{code, name, skin, device.optString("image", "").trim()});
                }
            }
        } catch (Exception error) {
            android.util.Log.w("VelaProvision", "解析实例清单失败", error);
        }
        return result;
    }

    /**
     * 共享镜像仓库：{@code files/images/<镜像档>/}（三件套）。
     *
     * <p>不随任何设备走，多台设备共用；由"内置/下载/外部导入"填充。设备自己的
     * {@code runtime/<设备>/images/<档>/} 优先，缺了才从这里链接进来（硬链接，秒级、不额外占空间）。</p>
     */
    @NonNull
    public static File sharedImageDir(@NonNull Context context, @NonNull String imageType) {
        return new File(new File(context.getFilesDir(), "images"), imageType);
    }

    /**
     * 保证该设备能拿到 {@code images/<档>/} 这套镜像。
     *
     * <p>顺序：设备目录已有 → true；共享仓库有 → 链接进来；都没有 → false（调用方回退到包内默认镜像）。</p>
     */
    public static boolean ensureImage(@NonNull Context context, @NonNull VelaEmulator emulator,
                                      @Nullable String imageType) {
        if (imageType == null || imageType.trim().isEmpty()) {
            return false;
        }
        String type = imageType.trim();
        if (emulator.hasImageSet(type)) {
            return true;
        }
        File shared = sharedImageDir(context, type);
        if (!new File(shared, "vela_data.bin").isFile() && !new File(shared, "nuttx").isFile()) {
            return false;
        }
        try {
            linkTree(shared, emulator.imageDirFor(type));
        } catch (Exception error) {
            android.util.Log.w("VelaProvision", "链接共享镜像失败", error);
            return false;
        }
        return emulator.hasImageSet(type);
    }

    /**
     * 该皮肤该用哪套系统镜像（IDE 的 {@code ide.image.type}）。
     *
     * <p>优先级：包内清单 {@code devices[].image} → 皮肤品类兜底（手环 PRE / 音箱 smartspeaker /
     * 手表用包内默认）。**手环必须用 PRE 档**，否则客机跑手表镜像，表盘会被裁进药丸屏（见 §3.6）。</p>
     */
    @NonNull
    public static String imageTypeOfSkin(@NonNull File runtimeDir, @NonNull String skin) {
        File manifest = new File(runtimeDir, "config.json");
        if (manifest.isFile()) {
            try {
                String json = new String(java.nio.file.Files.readAllBytes(manifest.toPath()),
                        java.nio.charset.StandardCharsets.UTF_8);
                org.json.JSONArray devices =
                        new org.json.JSONObject(json).optJSONArray("devices");
                if (devices != null) {
                    for (int index = 0; index < devices.length(); index++) {
                        org.json.JSONObject device = devices.optJSONObject(index);
                        if (device == null) {
                            continue;
                        }
                        String code = device.optString("code", "");
                        String itemSkin = device.optString("skin", code);
                        if (!skin.equals(itemSkin)) {
                            continue;
                        }
                        String image = device.optString("image", "").trim();
                        if (!image.isEmpty()) {
                            return image;
                        }
                        break;
                    }
                }
            } catch (Exception error) {
                android.util.Log.w("VelaProvision", "解析设备镜像档失败", error);
            }
        }
        File skinDir = new File(new File(runtimeDir, "engine"), "skins/" + skin);
        String[] props = VelaEmulator.skinProps(skinDir);
        return VelaEmulator.imageTypeForFlavor(props[2]);
    }

    /**
     * 铺好运行时的**第二步**：按选定皮肤写 AVD 配置并写设备库。
     *
     * @param skinName 模拟成哪款设备（{@code engine/skins/<name>}）；为空则用默认皮肤
     */
    public static void createDevice(@NonNull Context context, @NonNull VelaEmulator emulator,
                                    @NonNull String deviceId, @Nullable String skinName,
                                    @Nullable String displayName) throws Exception {
        if (skinName != null && !skinName.trim().isEmpty()) {
            emulator.setSkin(skinName);
        } else {
            // 没指定皮肤（老入口/ensureRuntime 路径）：沿用 AVD 里已有的，别覆盖成默认
            emulator.setSkin(emulator.skinOfAvd(deviceId));
        }
        // 镜像档：和皮肤一样按设备持久化到 AVD 的 ide.image.type，下次启动读回来。
        // 设备目录没有该档时，从共享仓库链接进来；还是没有就退回包内默认镜像（宁可不改）。
        String imageType = imageTypeOfSkin(emulator.runtimeDir(), emulator.skin());
        if (!ensureImage(context, emulator, imageType)) {
            imageType = "";
        }
        emulator.setImageType(imageType);
        // AVD 名 = **设备 id 转义**（与 VelaEmulatorManager.start 用的一致）；
        // 用机型当 AVD 名会留下一个引擎永远找不到的"影子 AVD"（踩过）。
        emulator.authorAvd(deviceId, emulator.imagesDir());
        try (DeviceDatabase db = new DeviceDatabase(context)) {
            // **model 列存的就是设备显示名**：{云端设备名称} + 空格 + 4 位随机
            // （机型标识只从设备 id「virtual:<机型>[:<随机>]」里解析，别再往 model 列塞标识）
            String name = displayName == null || displayName.trim().isEmpty()
                    ? defaultDeviceName() : displayName.trim();
            db.upsert(deviceId, name, DeviceDatabase.TYPE_VIRTUAL, false);
            db.updateNote(deviceId, "");
        }
    }

    /**
     * 老版本运行时只解出过 armel 引擎；镜像包刷新后引擎目录可能**缺文件**（如 aarch64 那份），
     * 表现为创建失败 {@code qemu-system-aarch64-headless: open failed: ENOENT}。
     * 这里自检：缺任何一个就清掉 {@code engine/} 用同一个包重解一次（幂等）。
     */
    private static void healEngineIfIncomplete(@NonNull File zip, @NonNull File runtimeDir)
            throws Exception {
        File qemu = new File(runtimeDir, "engine/qemu/linux-aarch64");
        if (hasRealEngine(qemu)) {
            return;
        }
        deleteRecursively(new File(runtimeDir, "engine"));
        unzip(zip, runtimeDir);
    }

    /** 清掉早期版本的共享运行目录 {@code files/vela}（已改为按机型隔离，没人再用了）。 */
    private static void purgeLegacySharedRuntime(@NonNull Context context) {
        File legacy = new File(context.getFilesDir(), "vela");
        if (legacy.isDirectory()) {
            deleteRecursively(legacy);
        }
    }

    /**
     * 引擎真身是否齐（**必须看 .elf 真身，软链不算**）：
     * 装机后原路径是软链、真身在同名 {@code .elf}；只看软链会把"缺文件"误判成正常。
     */
    private static boolean hasRealEngine(@NonNull File qemu) {
        // 解包后是"原始名真身"，installExecLinks 之后才改名成 *.elf 并在原路径留软链。
        // 所以两个名字都要认，但**必须是真身**（软链不算，否则会误判"齐了"）。
        return hasRealBinary(qemu, "qemu-system-armel-headless")
                && hasRealBinary(qemu, "qemu-system-aarch64-headless");
    }

    /** 该二进制是否存在且**不是软链**（真身）。 */
    private static boolean hasRealBinary(@NonNull File dir, @NonNull String name) {
        File plain = new File(dir, name);
        File elf = new File(dir, name + ".elf");
        return (plain.isFile() && !java.nio.file.Files.isSymbolicLink(plain.toPath()))
                || (elf.isFile() && !java.nio.file.Files.isSymbolicLink(elf.toPath()));
    }

    private static void deleteRecursively(@NonNull File target) {
        if (target.isDirectory()) {
            File[] children = target.listFiles();
            if (children != null) {
                for (File child : children) {
                    deleteRecursively(child);
                }
            }
        }
        //noinspection ResultOfMethodCallIgnored
        target.delete();
    }

    /**
     * 按需给某台设备准备**它自己的**运行目录：缺就地从已下载的镜像包解包（不做下载）。
     * 返回 false 表示包不在，需要用户去详情页重新「创建」。
     */
    public static boolean ensureRuntime(@NonNull Context context, @NonNull String modelId,
                                        @NonNull String deviceId) {
        VelaEmulator device = new VelaEmulator(context, modelId, deviceId);
        File target = device.runtimeDir();
        if (hasRealEngine(new File(target, "engine/qemu/linux-aarch64"))) {
            return true;
        }
        // 1) 机型母本目录：只读内容（引擎/系统镜像）在这里铺一次
        VelaEmulator base = new VelaEmulator(context, modelId);
        File baseRuntime = base.runtimeDir();
        if (!hasRealEngine(new File(baseRuntime, "engine/qemu/linux-aarch64"))) {
            File zip = packageFile(context, modelId);
            if (!zip.isFile()) {
                return false;
            }
            try {
                deleteRecursively(new File(baseRuntime, "engine"));
                unzip(zip, baseRuntime);
            } catch (Exception error) {
                android.util.Log.w("VelaProvision", "铺机型母本失败", error);
                return false;
            }
            if (!hasRealEngine(new File(baseRuntime, "engine/qemu/linux-aarch64"))) {
                return false;
            }
        }
        // 2) 把母本**硬链接**到该设备自己的目录：秒级完成、不额外占空间，
        //    而可写数据（AVD 里的 userdata-qemu.img 等）天然留在各自目录 → 沙盒依旧独立。
        try {
            linkTree(baseRuntime, target);
        } catch (Exception error) {
            android.util.Log.w("VelaProvision", "链接运行目录失败", error);
            return false;
        }
        return hasRealEngine(new File(target, "engine/qemu/linux-aarch64"));
    }

    /** 递归把 src 树"链接"到 dst：优先硬链接（同分区秒级），失败则退回复制。 */
    private static void linkTree(@NonNull File src, @NonNull File dst) throws Exception {
        // 软链必须"照样软链"：跟进目标会无限递归（engine/emulator -> engine）→ 直接闪退
        if (java.nio.file.Files.isSymbolicLink(src.toPath())) {
            if (!dst.exists()) {
                File parent = dst.getParentFile();
                if (parent != null) {
                    //noinspection ResultOfMethodCallIgnored
                    parent.mkdirs();
                }
                java.nio.file.Files.createSymbolicLink(dst.toPath(),
                        java.nio.file.Files.readSymbolicLink(src.toPath()));
            }
            return;
        }
        if (src.isDirectory()) {
            //noinspection ResultOfMethodCallIgnored
            dst.mkdirs();
            File[] children = src.listFiles();
            if (children != null) {
                for (File child : children) {
                    linkTree(child, new File(dst, child.getName()));
                }
            }
            return;
        }
        if (dst.exists()) {
            return;
        }
        File parent = dst.getParentFile();
        if (parent != null) {
            //noinspection ResultOfMethodCallIgnored
            parent.mkdirs();
        }
        try {
            java.nio.file.Files.createLink(dst.toPath(), src.toPath());
        } catch (Exception hardLinkFailed) {
            copyFile(src, dst);
        }
    }

    /** 单文件复制（硬链接不可用时的兜底）。 */
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
