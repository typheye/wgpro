package com.typheye.wgpro.core.download;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Locale;

/**
 * 下载管理文件存储约定：最终文件一律是 {@code files/downloads/<sha256>}，没有后缀。
 *
 * <p>下载过程先写 {@code cacheDir/download-files/<sha256(task.id)>}。完成后计算内容
 * SHA-256 并原样改名为最终文件。这里不接受按 URL、任务 id、资源名或 MIME 推断文件名。</p>
 */
public final class DownloadFiles {

    private static final String DOWNLOAD_DIR = "downloads";
    private static final String PARTIAL_DIR = "download-files";

    private DownloadFiles() {
    }

    @NonNull
    public static File managedDirectory(@NonNull Context context) {
        File dir = new File(context.getFilesDir(), DOWNLOAD_DIR);
        if (!dir.isDirectory() && !dir.mkdirs()) {
            throw new IllegalStateException("无法创建下载目录");
        }
        return dir;
    }

    @NonNull
    public static File partialDirectory(@NonNull Context context) {
        File dir = new File(context.getCacheDir(), PARTIAL_DIR);
        if (!dir.isDirectory() && !dir.mkdirs()) {
            throw new IllegalStateException("无法创建下载临时目录");
        }
        return dir;
    }

    /** 续传文件同样用 64 位 SHA-256 命名，无后缀；这里是任务 id 的摘要，不是内容摘要。 */
    @NonNull
    public static File partialFile(@NonNull Context context, @NonNull String taskId) {
        return new File(partialDirectory(context), sha256(taskId));
    }

    /**
     * 根据完整下载内容计算 SHA-256，并把文件移动到最终目录。
     *
     * @return 最终文件 {@code files/downloads/<sha256>}
     */
    @NonNull
    public static File commit(@NonNull Context context, @NonNull File partial) throws IOException {
        return commit(partial, managedDirectory(context));
    }

    @NonNull
    static File commit(@NonNull File partial, @NonNull File managed) throws IOException {
        if (!partial.isFile()) {
            throw new IOException("下载文件不存在");
        }
        if (!managed.isDirectory() && !managed.mkdirs()) {
            throw new IOException("无法创建下载目录");
        }
        String digest = sha256(partial);
        File target = new File(managed, digest);
        if (partial.equals(target)) {
            return target;
        }
        if (target.isFile()) {
            if (!partial.delete()) {
                partial.deleteOnExit();
            }
            return target;
        }
        if (!partial.renameTo(target)) {
            throw new IOException("无法按 sha256 保存下载文件");
        }
        return target;
    }

    /** 仅接受下载目录中的 64 位小写 SHA-256 文件名。 */
    @Nullable
    public static File canonicalFile(@NonNull Context context, @Nullable String digest) {
        if (digest == null || !isSha256Name(digest)) {
            return null;
        }
        File file = new File(managedDirectory(context), digest);
        return file.isFile() ? file : null;
    }

    /**
     * 已完成任务对应的最终文件。只认任务记录的 sha256 或 localPath 中的 SHA-256 文件名；
     * 不再按任务 id、URL 扩展名或文件名前缀做兼容查找。
     */
    @Nullable
    public static File canonicalFile(@NonNull Context context, @NonNull DownloadTask task) {
        File byDigest = canonicalFile(context, task.sha256);
        if (byDigest != null) {
            return byDigest;
        }
        String path = task.localPath;
        if (path == null || path.isEmpty() || path.startsWith("content://")) {
            return null;
        }
        File direct = new File(path);
        File managed = managedDirectory(context);
        String name = direct.getName();
        if (direct.isFile() && managed.equals(direct.getParentFile()) && isSha256Name(name)) {
            task.sha256 = name.toLowerCase(Locale.ROOT);
            return direct;
        }
        return null;
    }

    /**
     * 把旧版按后缀保存的下载文件迁移成内容 SHA-256 文件名。
     *
     * <p>这是单向迁移，不保留后缀路径兼容。只处理应用私有下载目录中的文件；用户在
     * SAF/系统下载目录主动选择的位置不会被改写。</p>
     */
    public static boolean normalizeLegacy(@NonNull Context context, @NonNull DownloadTask task) {
        String path = task.localPath;
        File managed = managedDirectory(context);
        File source = path == null || path.isEmpty() || path.startsWith("content://")
                ? null : new File(path);
        if (source == null || !source.isFile() || !managed.equals(source.getParentFile())) {
            source = findLegacyTaskFile(managed, task.id);
        }
        if (source == null) {
            return false;
        }
        if (task.status == DownloadTask.STATUS_COMPLETED) {
            if (isSha256Name(source.getName())) {
                task.sha256 = source.getName().toLowerCase(Locale.ROOT);
                return false;
            }
            try {
                File target = commit(context, source);
                task.sha256 = target.getName();
                task.localPath = target.getAbsolutePath();
                return true;
            } catch (IOException error) {
                android.util.Log.w("DownloadFiles", "迁移下载文件失败", error);
                return false;
            }
        }

        File partial = partialFile(context, task.id);
        if (!source.equals(partial)) {
            if (partial.exists() && !partial.delete()) {
                return false;
            }
            if (!source.renameTo(partial)) {
                return false;
            }
        }
        task.localPath = null;
        task.sha256 = "";
        return true;
    }

    /** 查找旧版 {@code <任务id>.<任意后缀>} 文件；迁移后不再使用这个路径规则。 */
    @Nullable
    private static File findLegacyTaskFile(@NonNull File managed, @Nullable String taskId) {
        if (taskId == null || taskId.isEmpty()) {
            return null;
        }
        File[] files = managed.listFiles();
        if (files == null) {
            return null;
        }
        File newest = null;
        for (File file : files) {
            if (!file.isFile() || !file.getName().startsWith(taskId + ".")) {
                continue;
            }
            if (newest == null || file.lastModified() > newest.lastModified()) {
                newest = file;
            }
        }
        return newest;
    }

    /** 删除任务的临时文件和应用私有最终文件；不改动用户在外部选择的位置。 */
    public static void delete(@NonNull Context context, @NonNull DownloadTask task) {
        File canonical = canonicalFile(context, task);
        if (canonical != null && !canonical.delete()) {
            canonical.deleteOnExit();
        }
        String path = task.localPath;
        if (path != null && !path.startsWith("content://")) {
            File direct = new File(path);
            File managed = managedDirectory(context);
            if (direct.isFile() && managed.equals(direct.getParentFile())
                    && !direct.delete()) {
                direct.deleteOnExit();
            }
        }
        File partial = partialFile(context, task.id);
        if (partial.isFile() && !partial.delete()) {
            partial.deleteOnExit();
        }
    }

    public static boolean isSha256Name(@Nullable String name) {
        return name != null && name.matches("[0-9a-f]{64}");
    }

    /** 根据原文件名确定打开方式；最终落盘文件名不含扩展名。 */
    @NonNull
    public static String mimeTypeFor(@Nullable String originalName,
                                     @NonNull String fallback) {
        if (originalName == null || originalName.isEmpty()) {
            return fallback;
        }
        String lower = originalName.toLowerCase(Locale.ROOT);
        if (lower.endsWith(".apk")) {
            return "application/vnd.android.package-archive";
        }
        int dot = lower.lastIndexOf('.');
        if (dot < 0 || dot == lower.length() - 1) {
            return fallback;
        }
        String mime = android.webkit.MimeTypeMap.getSingleton()
                .getMimeTypeFromExtension(lower.substring(dot + 1));
        return mime == null || mime.isEmpty() ? fallback : mime;
    }

    /** 字符串内容摘要；用于稳定的临时文件名。 */
    @NonNull
    public static String sha256(@NonNull String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return hex(digest.digest(value.getBytes(java.nio.charset.StandardCharsets.UTF_8)));
        } catch (Exception error) {
            throw new IllegalStateException("SHA-256 unavailable", error);
        }
    }

    /** 文件内容摘要。 */
    @NonNull
    public static String sha256(@NonNull File file) throws IOException {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (Exception error) {
            throw new IOException("SHA-256 unavailable", error);
        }
        byte[] buffer = new byte[64 * 1024];
        try (FileInputStream in = new FileInputStream(file)) {
            int read;
            while ((read = in.read(buffer)) != -1) {
                digest.update(buffer, 0, read);
            }
        }
        return hex(digest.digest());
    }

    @NonNull
    private static String hex(@NonNull byte[] bytes) {
        StringBuilder result = new StringBuilder(bytes.length * 2);
        for (byte value : bytes) {
            result.append(String.format(Locale.ROOT, "%02x", value & 0xff));
        }
        return result.toString();
    }
}
