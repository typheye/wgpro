package com.typheye.wgpro.core.emu;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * AIoT IDE 的**系统镜像**目录与下载器。
 *
 * <p>URL 与版本号直接来自 IDE 的实现（{@code vela.aiot-emulator} 的
 * {@code VelaImageVersionList} + {@code getImageDownloadUrl()}）：
 * <pre>
 *   &lt;base&gt;/system-images/&lt;镜像档&gt;/&lt;版本日期&gt;/&lt;镜像档&gt;.zip
 *   例：https://vela-ide.cnbj3-fusion.mi-fds.com/vela-ide/system-images/vela-watch-5.0/20250716/vela-watch-5.0.zip
 * </pre>
 * 解压到**共享镜像仓库** {@code files/images/<档>/}，多台设备硬链接复用。</p>
 *
 * <p><b>为什么必须分档</b>（IDE 的镜像说明原文）：
 * <ul>
 *   <li>{@code vela-miwear-watch-5.0}：带表盘，**不可自定义模拟器尺寸** → 只能用在它自己的 466×466；</li>
 *   <li>{@code vela-watch-5.0}：**不带表盘、可自定义模拟器尺寸** → 手环 / Redmi Watch 这类方屏用这套；</li>
 *   <li>{@code openvela-smartspeaker-5.0} / {@code vela-miwear-minisound-5.0}：音箱。</li>
 * </ul>
 * 用错档就会出现"手环/方屏表里是小米手表的画面"。</p>
 */
public final class VelaImages {

    private static final String TAG = "VelaImages";

    /** IDE 的系统镜像 CDN（{@code HR()} 里的 systemImageBaseUrl）。 */
    public static final String CDN =
            "https://vela-ide.cnbj3-fusion.mi-fds.com/vela-ide/system-images";

    /** 镜像档 → 版本日期（IDE {@code VelaImageVersionList} 原文；日期变了要同步改这里）。 */
    private static final String[][] CATALOG = {
            {"vela-watch-5.0", "20250716"},
            {"vela-miwear-watch-5.0", "20250716"},
            {"vela-miwear-watch-5.0-beta", "20260717"},
            {"vela-release-4.0", "20250526"},
            {"vela-pre-4.0", "20250225"},
            {"vela-miwear-minisound-5.0", "20250318"},
            {"openvela-smartspeaker-5.0", "20260128"},
    };

    /** zip 里只保留这几个文件；{@code coredump.core}（200MB）不要。 */
    private static final Set<String> WANTED = new HashSet<>(Arrays.asList(
            "nuttx", "vela_system.bin", "vela_data.bin", "advancedFeatures.ini"));

    private VelaImages() {
    }

    public interface Progress {
        /** 在下载线程回调；percent 为 -1 表示总大小未知。 */
        void onProgress(@NonNull String type, int percent);
    }

    @NonNull
    public static List<String> allTypes() {
        List<String> types = new ArrayList<>();
        for (String[] entry : CATALOG) {
            types.add(entry[0]);
        }
        return types;
    }

    /** 该镜像档能不能下（目录里有才算）。 */
    public static boolean isAvailable(@Nullable String type) {
        return timeOf(type) != null;
    }

    @Nullable
    public static String timeOf(@Nullable String type) {
        if (type == null) {
            return null;
        }
        for (String[] entry : CATALOG) {
            if (entry[0].equals(type.trim())) {
                return entry[1];
            }
        }
        return null;
    }

    @NonNull
    public static String downloadUrl(@NonNull String type) {
        String time = timeOf(type);
        return CDN + "/" + type + "/" + (time == null ? "" : time) + "/" + type + ".zip";
    }

    /** 共享仓库里是否已有该档的三件套。 */
    public static boolean isInstalled(@NonNull Context context, @NonNull String type) {
        File dir = VelaProvision.sharedImageDir(context, type);
        return new File(dir, "nuttx").isFile() && new File(dir, "vela_system.bin").isFile()
                && new File(dir, "vela_data.bin").isFile();
    }

    /** 下载 + 解压到共享仓库；已存在直接返回 true。**必须在后台线程调用**。 */
    public static boolean download(@NonNull Context context, @NonNull String type) {
        return download(context, type, null);
    }

    public static boolean download(@NonNull Context context, @NonNull String type,
                                   @Nullable Progress progress) {
        String version = timeOf(type);
        if (version == null) {
            Log.w(TAG, "没有这个镜像档：" + type);
            return false;
        }
        File dir = VelaProvision.sharedImageDir(context, type);
        if (isInstalled(context, type)) {
            return true;
        }
        File zip = new File(context.getCacheDir(), "vela-image-" + type + ".zip");
        File part = new File(zip.getAbsolutePath() + ".part");
        HttpURLConnection connection = null;
        try {
            URL url = new URL(downloadUrl(type));
            connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(180000);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("User-Agent", "wgpro-vela/1.0");
            long total = connection.getContentLengthLong();
            Log.i(TAG, "开始下载 " + type + " (" + (total / 1024 / 1024) + "MB) " + url);
            try (InputStream in = connection.getInputStream();
                 OutputStream out = new FileOutputStream(part)) {
                byte[] buffer = new byte[1 << 16];
                long read = 0;
                int count;
                while ((count = in.read(buffer)) > 0) {
                    out.write(buffer, 0, count);
                    read += count;
                    if (progress != null) {
                        progress.onProgress(type, total > 0 ? (int) (read * 100 / total) : -1);
                    }
                }
            }
            //noinspection ResultOfMethodCallIgnored
            dir.mkdirs();
            int files = extract(part, dir);
            Log.i(TAG, "解压完成 " + type + "，落地 " + files + " 个文件 → " + dir);
            return isInstalled(context, type);
        } catch (Throwable error) {
            Log.w(TAG, "下载镜像失败 " + type, error);
            return false;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            //noinspection ResultOfMethodCallIgnored
            part.delete();
            //noinspection ResultOfMethodCallIgnored
            zip.delete();
        }
    }

    /**
     * 解压 zip：**拍平目录**（zip 里可能带一层同名目录），只保留 {@link #WANTED} 里的文件。
     * 返回落地的文件数。
     */
    private static int extract(@NonNull File zip, @NonNull File into) throws Exception {
        int files = 0;
        try (ZipInputStream in = new ZipInputStream(new java.io.BufferedInputStream(
                new java.io.FileInputStream(zip)))) {
            ZipEntry entry;
            while ((entry = in.getNextEntry()) != null) {
                if (entry.isDirectory()) {
                    continue;
                }
                String name = entry.getName();
                int slash = name.lastIndexOf('/');
                String base = slash < 0 ? name : name.substring(slash + 1);
                if (!WANTED.contains(base)) {
                    continue;
                }
                File target = new File(into, base);
                try (OutputStream out = new FileOutputStream(target)) {
                    byte[] buffer = new byte[1 << 16];
                    int count;
                    while ((count = in.read(buffer)) > 0) {
                        out.write(buffer, 0, count);
                    }
                }
                files++;
            }
        }
        return files;
    }
}
