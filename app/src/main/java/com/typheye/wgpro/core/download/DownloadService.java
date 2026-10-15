package com.typheye.wgpro.core.download;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.typheye.wgpro.R;

import org.json.JSONObject;
import com.typheye.wgpro.core.NotificationChannels;
import com.typheye.wgpro.ui.function.download.DownloadManagerActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 应用下载前台服务：串行处理 {@link DownloadManager} 里的排队任务。
 *
 * <p>Google Play 式的全生命周期：排队 / 下载中 / 暂停 / 继续（Range 断点续传）/ 取消 /
 * 完成 / 失败；前台通知展示实时进度，点击进入下载管理页。</p>
 */
public final class DownloadService extends android.app.Service {
    public static final String ACTION_KICK = "com.typheye.wgpro.download.KICK";
    public static final String ACTION_PAUSE = "com.typheye.wgpro.download.PAUSE";
    public static final String ACTION_RESUME = "com.typheye.wgpro.download.RESUME";
    public static final String ACTION_CANCEL = "com.typheye.wgpro.download.CANCEL";
    public static final String ACTION_REMOVE = "com.typheye.wgpro.download.REMOVE";
    public static final String EXTRA_ID = "id";

    private static final int NOTIFICATION_ID = 0x0D10;
    private static final long PROGRESS_THROTTLE_MS = 300L;

    private final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .writeTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build();
    private final ExecutorService worker = Executors.newSingleThreadExecutor();
    private final AtomicBoolean looping = new AtomicBoolean(false);
    private volatile okhttp3.Call currentCall;
    private volatile String currentId;

    @Override
    public void onCreate() {
        super.onCreate();
        NotificationChannels.ensureAll(this);
        startForeground(NOTIFICATION_ID, buildNotification("准备下载", 0, true));
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        String action = intent == null ? ACTION_KICK : intent.getAction();
        String id = intent == null ? null : intent.getStringExtra(EXTRA_ID);
        if (action == null) action = ACTION_KICK;
        switch (action) {
            case ACTION_PAUSE:
                pause(id);
                break;
            case ACTION_RESUME:
                resume(id);
                break;
            case ACTION_CANCEL:
            case ACTION_REMOVE:
                cancel(id, ACTION_REMOVE.equals(action));
                break;
            default:
                pump();
                break;
        }
        return START_NOT_STICKY;
    }

    /** 启动/继续处理队列。 */
    private void pump() {
        if (!looping.compareAndSet(false, true)) return;
        worker.execute(() -> {
            try {
                while (true) {
                    DownloadTask task = nextQueued();
                    if (task == null) break;
                    try {
                        runTask(task);
                    } catch (Throwable error) {
                        // 任何未预期异常都不能让队列卡在"等待中"
                        failTask(task, "下载失败：" + (error.getMessage() == null
                                ? error.getClass().getSimpleName() : error.getMessage()));
                    }
                }
            } finally {
                looping.set(false);
                // 队列空了：有活动任务则保持前台，否则结束服务。
                if (!hasActive()) {
                    stopForegroundCompat(true);
                    stopSelf();
                }
            }
        });
    }

    @Nullable
    private synchronized DownloadTask nextQueued() {
        for (DownloadTask task : DownloadManager.get().snapshot()) {
            if (task.status == DownloadTask.STATUS_QUEUED) return task;
        }
        return null;
    }

    private boolean hasActive() {
        for (DownloadTask task : DownloadManager.get().snapshot()) {
            if (task.isActive()) return true;
        }
        return false;
    }

    /** 下载地址过期/未带鉴权时，最多重新取一次新地址。 */
    private static final int MAX_URL_REFRESH = 1;

    private void runTask(DownloadTask task) {
        currentId = task.id;
        task.status = DownloadTask.STATUS_RUNNING;
        DownloadManager.get().publish();

        File target = targetFile(task);
        int urlRefreshes = 0;
        boolean fallbackUsed = false;
        // 历史任务里可能存着"旧直链"（已 410）或过期签名：开下之前先换一次权威地址，
        // 免得先失败一次再弹失败弹窗（用户看到的"莫名失败"）
        if (isLegacyUrl(task.downloadUrl)) {
            String fresh = refreshDownloadUrl(task);
            if (fresh != null && !fresh.isEmpty()) task.downloadUrl = fresh;
        }
        try {
            while (true) {
                long existing = target.exists() ? target.length() : 0L;
                Request.Builder builder = new Request.Builder().url(task.downloadUrl);
                if (existing > 0L) builder.addHeader("Range", "bytes=" + existing + "-");
                okhttp3.Call call = client.newCall(builder.build());
                currentCall = call;
                okhttp3.Response response = call.execute();
                try (Response closed = response) {
                    int code = closed.code();
                    // 410 签名过期 / 403 签名被篡改 / 402 未购（常见于 URL 没带 uid+wt）：
                    // 重新调 res 接口取一次最新地址再试（只重试一次，已下分片保留）
                    if ((code == 410 || code == 403 || code == 402) && urlRefreshes < MAX_URL_REFRESH) {
                        String fresh = refreshDownloadUrl(task);
                        if (fresh != null && !fresh.isEmpty() && !fresh.equals(task.downloadUrl)) {
                            urlRefreshes++;
                            task.downloadUrl = fresh;
                            continue;
                        }
                    }
                    // 镜像节点失败（404/其它非 2xx）：回退到接口给的权威地址，保留已下分片
                    if (code != 416 && !closed.isSuccessful() && !fallbackUsed
                            && task.fallbackUrl != null && !task.fallbackUrl.isEmpty()
                            && !task.fallbackUrl.equals(task.downloadUrl)) {
                        fallbackUsed = true;
                        task.downloadUrl = task.fallbackUrl;
                        continue;
                    }
                    if (code == 402) {
                        failTask(task, "未获得该应用，请先在应用内获取后再下载");
                        return;
                    }
                    if (code == 410 || code == 403) {
                        failTask(task, "下载地址已过期，请重新下载");
                        return;
                    }
                    if (code == 404) {
                        failTask(task, "资源不存在或已下架");
                        return;
                    }
                    if (code == 416) {
                        // 已经完整下载过。
                        finishTask(task, target);
                        return;
                    }
                    if (!closed.isSuccessful()) {
                        failTask(task, "HTTP " + code);
                        return;
                    }
                    long bodyLength = closed.body() == null ? -1L : closed.body().contentLength();
                    task.totalBytes = bodyLength > 0 ? existing + bodyLength : -1L;
                    task.downloadedBytes = existing;
                    try (InputStream in = closed.body() == null ? null : closed.body().byteStream();
                         OutputStream out = new FileOutputStream(target, existing > 0L)) {
                        if (in == null) {
                            failTask(task, "响应为空");
                            return;
                        }
                        byte[] buffer = new byte[16 * 1024];
                        long lastNotify = 0L;
                        int read;
                        while ((read = in.read(buffer)) != -1) {
                            if (task.status == DownloadTask.STATUS_PAUSED) {
                                return; // 保留已下载部分，供继续下载
                            }
                            out.write(buffer, 0, read);
                            task.downloadedBytes += read;
                            long now = SystemClock.uptimeMillis();
                            if (now - lastNotify >= PROGRESS_THROTTLE_MS) {
                                lastNotify = now;
                                DownloadManager.get().publish();
                                updateNotification(task);
                            }
                        }
                        out.flush();
                    }
                    finishTask(task, target);
                    return;
                }
            }
        } catch (IOException e) {
            if (task.status == DownloadTask.STATUS_PAUSED) return;
            // 服务器收尾时可能直接断连：内容其实已经下满，按完成处理而不是失败。
            if (task.totalBytes > 0 && task.downloadedBytes >= task.totalBytes) {
                finishTask(task, target);
            } else {
                failTask(task, friendlyError(e));
            }
        } finally {
            currentCall = null;
            currentId = null;
        }
    }

    /** 是否是"历史上被 410 封禁"的旧直链形态（/app-download/release/ 或裸 share 路径）。 */
    private static boolean isLegacyUrl(@Nullable String url) {
        if (url == null || url.isEmpty()) return false;
        if (url.contains("media.php") || url.contains("action=dl") || url.contains("/b/")) return false;
        return url.contains("/app-download/release/") || url.contains("/app-image/")
                || (url.contains("res.typheye.cn/") && !url.contains("api.php"));
    }

    /**
     * 重新调 res 接口取一次最新下载地址。
     * <p>应用类带 {@code uid+wt}（付费包体必须绑定用户，否则 402）；资源类不需要鉴权。
     * 客户端不自己拼路径，一切以接口返回为准。</p>
     *
     * @return 新地址；取不到返回 null
     */
    @Nullable
    private String refreshDownloadUrl(DownloadTask task) {
        try {
            String type = "resource".equals(task.detailType) ? "resource" : "app";
            String api = com.typheye.wgpro.utils.tPointsUtils.resApiUrl(this, type, task.id);
            Request request = new Request.Builder().url(api).get().build();
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful() || response.body() == null) return null;
                JSONObject json = new JSONObject(response.body().string());
                JSONObject item = json.optJSONObject("info");
                if (item == null) {
                    org.json.JSONArray array = json.optJSONArray("info");
                    if (array != null && array.length() > 0) item = array.optJSONObject(0);
                }
                if (item == null) return null;
                String fresh = item.optString("download_url", item.optString("file_url", ""));
                return fresh.isEmpty() ? null : fresh;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /** 把底层 IO 异常翻译成用户看得懂的话（避免直接显示 timeout / Connection reset）。 */
    private static String friendlyError(@NonNull IOException e) {
        String raw = e.getMessage() == null ? "" : e.getMessage();
        String lower = raw.toLowerCase(java.util.Locale.ROOT);
        if (e instanceof java.net.SocketTimeoutException || lower.contains("timeout")) {
            return "网络超时，请检查网络后重试";
        }
        if (e instanceof java.net.UnknownHostException || lower.contains("unable to resolve host")) {
            return "无法连接服务器，请检查网络";
        }
        if (e instanceof java.net.ConnectException || lower.contains("failed to connect")) {
            return "连接被拒绝或网络不可达，请稍后重试";
        }
        if (lower.contains("connection reset") || lower.contains("broken pipe")
                || lower.contains("unexpected end of stream")) {
            return "连接被中断，可重试续传";
        }
        if (e instanceof javax.net.ssl.SSLException) return "安全连接失败，请稍后重试";
        return raw.isEmpty() ? "下载失败，请稍后重试" : "下载失败：" + raw;
    }

    private void finishTask(DownloadTask task, File target) {
        task.status = DownloadTask.STATUS_COMPLETED;
        task.localPath = target.getAbsolutePath();
        if (task.totalBytes <= 0) task.downloadedBytes = Math.max(task.downloadedBytes, target.length());
        DownloadManager.get().publish();
        postDownloadAction(task, target);
    }

    private void failTask(DownloadTask task, String message) {
        task.status = DownloadTask.STATUS_FAILED;
        task.error = message;
        DownloadManager.get().publish();
    }

    /**
     * 下载完成后的去向处理：**一律静默**，不自动安装也不自动打开
     * （用户从详情页「使用」自己决定安装/保存/打开）。
     */
    private void postDownloadAction(DownloadTask task, File file) {
        if (task.destination == DownloadTask.DEST_APP_FILE) {
            // 内部资源：文件已在 files/downloads，调用方自行消费
            return;
        }
        if (task.destination == DownloadTask.DEST_LOCAL) {
            // 用户主动选择的"保存到本机"：落到所选位置是这次下载的目的
            saveToPublicDownloads(task, file);
        } else {
            task.error = null;
            DownloadManager.get().publish();
        }
    }

    /** 保存到公共 Download 目录；Android Q 及以上走 MediaStore，以下直接写外部目录。 */
    /** 保存到用户用系统文件框架选定的位置（SAF）；没有目标 URI 时回退到公共 Download 目录。 */
    private void saveToPublicDownloads(DownloadTask task, File file) {
        if (task.targetUri != null && !task.targetUri.isEmpty()) {
            try (OutputStream out = getContentResolver().openOutputStream(
                    android.net.Uri.parse(task.targetUri));
                 InputStream in = new java.io.FileInputStream(file)) {
                if (out == null) throw new IOException("无法写入所选位置");
                copy(in, out);
                task.localPath = task.targetUri;
                DownloadManager.get().publish();
            } catch (Exception e) {
                task.error = "保存到所选位置失败：" + e.getMessage();
                DownloadManager.get().publish();
            }
            return;
        }
        // 「保存到本地」用下载地址里的 **sha256 命名、无后缀**（服务器就是哈希寻址）；
        // 没有哈希才退回"原名 + 真实扩展名"。
        String fileName = localFileName(task);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Downloads.DISPLAY_NAME, fileName);
                values.put(MediaStore.Downloads.MIME_TYPE, "application/vnd.android.package-archive");
                values.put(MediaStore.Downloads.IS_PENDING, 1);
                Uri uri = getContentResolver().insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, values);
                if (uri != null) {
                    try (OutputStream out = getContentResolver().openOutputStream(uri);
                         InputStream in = new java.io.FileInputStream(file)) {
                        if (out != null && in != null) copy(in, out);
                    }
                    values.clear();
                    values.put(MediaStore.Downloads.IS_PENDING, 0);
                    getContentResolver().update(uri, values, null, null);
                    task.localPath = uri.toString();
                }
            } else {
                File dir = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOWNLOADS), "wgpro");
                if (!dir.exists() && !dir.mkdirs()) throw new IOException("无法创建下载目录");
                File out = new File(dir, fileName);
                try (InputStream in = new java.io.FileInputStream(file);
                     OutputStream os = new FileOutputStream(out)) {
                    copy(in, os);
                }
                task.localPath = out.getAbsolutePath();
            }
            DownloadManager.get().publish();
        } catch (Exception e) {
            task.error = "保存到本地失败：" + e.getMessage();
            DownloadManager.get().publish();
        }
    }

    /**
     * 拉起系统安装器安装一个已下载的 APK（「使用」卡片里的"安装到手机"走这里）。
     *
     * @return 是否成功拉起
     */
    public static boolean installFromFile(@NonNull Context context, @NonNull File file) {
        try {
            Uri uri = androidx.core.content.FileProvider.getUriForFile(context,
                    context.getPackageName() + ".fileprovider", file);
            Intent intent = new Intent(Intent.ACTION_VIEW)
                    .setDataAndType(uri, "application/vnd.android.package-archive")
                    .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void pause(String id) {
        DownloadTask task = id == null ? null : DownloadManager.get().find(id);
        if (task == null || task.status != DownloadTask.STATUS_RUNNING) return;
        task.status = DownloadTask.STATUS_PAUSED;
        DownloadManager.get().publish();
        okhttp3.Call call = currentCall;
        if (call != null) call.cancel();
    }

    private void resume(String id) {
        DownloadTask task = id == null ? null : DownloadManager.get().find(id);
        if (task == null) return;
        task.status = DownloadTask.STATUS_QUEUED;
        DownloadManager.get().publish();
        pump();
    }

    private void cancel(String id, boolean remove) {
        DownloadTask task = id == null ? null : DownloadManager.get().find(id);
        if (task == null) return;
        if (id.equals(currentId)) {
            task.status = DownloadTask.STATUS_FAILED; // 让工作线程尽早退出循环
            okhttp3.Call call = currentCall;
            if (call != null) call.cancel();
        }
        if (remove) {
            DownloadManager.get().remove(id);
        } else {
            task.status = DownloadTask.STATUS_FAILED;
            task.error = "已取消";
            DownloadManager.get().publish();
        }
        File target = targetFile(task);
        if (target.exists() && !target.delete()) {
            target.deleteOnExit();
        }
        if (!hasActive()) {
            stopForegroundCompat(true);
            stopSelf();
        }
    }

    private File targetFile(DownloadTask task) {
        File dir = new File(getFilesDir(), "downloads");
        if (!dir.exists()) dir.mkdirs();
        return new File(dir, task.id + "." + extensionOf(task));
    }

    /** 保存到本地用的文件名：sha256（无后缀）优先。 */
    private String localFileName(DownloadTask task) {
        String hash = sha256Of(task);
        if (!hash.isEmpty()) {
            return hash;
        }
        return resolveFileName(task);
    }

    /** 从下载地址里取 64 位 sha256（服务器为 /b/<sha256> 这种哈希寻址）。 */
    private String sha256Of(DownloadTask task) {
        String url = task.downloadUrl == null ? "" : task.downloadUrl;
        java.util.regex.Matcher matcher = java.util.regex.Pattern
                .compile("([0-9a-fA-F]{64})").matcher(url);
        return matcher.find() ? matcher.group(1).toLowerCase(java.util.Locale.ROOT) : "";
    }

    /**
     * 文件名 = 基础名 + **真实扩展名**。
     *
     * <p>原来一律拼 {@code .apk}：镜像 zip、图片、字体等也被存成 apk，系统还会当安装包处理。</p>
     */
    private String resolveFileName(DownloadTask task) {
        String extension = extensionOf(task);
        String base = safeFileName(task);
        return extension.isEmpty() ? base : base + "." + extension;
    }

    /** 取扩展名：先看下载地址（去 query）的尾段，再退回按目的地判断（手机安装包=apk，其余=bin）。 */
    private String extensionOf(DownloadTask task) {
        for (String url : new String[]{task.downloadUrl, task.fallbackUrl}) {
            if (url == null) {
                continue;
            }
            String path = url;
            int query = path.indexOf('?');
            if (query >= 0) {
                path = path.substring(0, query);
            }
            int slash = path.lastIndexOf('/');
            String last = slash >= 0 ? path.substring(slash + 1) : path;
            int dot = last.lastIndexOf('.');
            if (dot > 0 && dot < last.length() - 1) {
                String extension = last.substring(dot + 1).toLowerCase(java.util.Locale.ROOT);
                if (extension.matches("[a-z0-9]{1,5}")) {
                    return extension;
                }
            }
        }
        return task.destination == DownloadTask.DEST_PHONE ? "apk" : "bin";
    }

    private String safeFileName(DownloadTask task) {
        String base = task.name == null ? task.id : task.name;
        return base.replaceAll("[^A-Za-z0-9\\u4e00-\\u9fa5._-]", "_");
    }

    private void updateNotification(DownloadTask task) {
        int progress = task.progress();
        startForeground(NOTIFICATION_ID, buildNotification(task.name, progress, task.totalBytes <= 0));
    }

    private Notification buildNotification(String title, int progress, boolean indeterminate) {
        Intent open = new Intent(this, DownloadManagerActivity.class)
                // SINGLE_TOP：已经在下载管理页时点击通知**不重建、不二次进入**
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent content = PendingIntent.getActivity(this, 0, open,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NotificationChannels.DOWNLOADS)
                .setSmallIcon(R.drawable.ic_download_vector)
                .setContentTitle(title == null || title.isEmpty() ? "下载管理" : title)
                .setContentText(indeterminate ? "正在下载…" : progress + "%")
                .setContentIntent(content)
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setPriority(NotificationCompat.PRIORITY_LOW);
        if (indeterminate) builder.setProgress(0, 0, true);
        else builder.setProgress(100, progress, false);
        return builder.build();
    }

    private void stopForegroundCompat(boolean remove) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            stopForeground(remove ? STOP_FOREGROUND_REMOVE : STOP_FOREGROUND_DETACH);
        } else {
            stopForeground(remove);
        }
    }

    private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[16 * 1024];
        int read;
        while ((read = in.read(buffer)) != -1) out.write(buffer, 0, read);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        worker.shutdownNow();
    }
}
