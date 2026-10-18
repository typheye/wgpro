package com.typheye.wgpro.core.download;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 进程内下载任务状态源（单例），并做本地持久化。
 *
 * <p>由 {@link DownloadService} 写入，下载管理页/详情页订阅。所有写入都通过
 * {@code postValue} 发布快照，可从任意线程调用；每次变更同步落盘，重启后恢复。
 * 重启时「下载中/排队」的任务会被标成「已暂停」（不自动续传）。</p>
 */
public final class DownloadManager {
    private static final DownloadManager INSTANCE = new DownloadManager();
    private static final String PREFS = "download_tasks";
    private static final String KEY_TASKS = "tasks";

    private final MutableLiveData<List<DownloadTask>> tasks = new MutableLiveData<>(new ArrayList<>());
    private final List<DownloadTask> list = new ArrayList<>();
    private Context appContext;
    private boolean initialized;

    private DownloadManager() {
    }

    public static DownloadManager get() {
        return INSTANCE;
    }

    /** 在 Application 启动时调用一次：读出本地记录。 */
    public synchronized void init(@NonNull Context context) {
        if (initialized) return;
        initialized = true;
        appContext = context.getApplicationContext();
        load();
        publish();
    }

    /**
     * 下拉刷新用：**只重发一遍当前列表**。
     *
     * <p>不能在这里 {@code load()}：{@code load()} 会 clear 后从持久化 JSON 重建任务，
     * 把正在下载任务的实时状态（运行中/进度）冲成持久化时的旧值（例如"等待中"），
     * 看起来像"下拉刷新把下载暂停了"。下载服务与 UI 同进程共用这一个单例，内存即权威。</p>
     */
    public synchronized void reload() {
        publish();
    }

    /** 只从记录里移除某个任务（不碰文件），用于"删除镜像"这类操作。 */
    public synchronized void drop(@NonNull String id) {
        for (int i = list.size() - 1; i >= 0; i--) {
            if (id.equals(list.get(i).id)) {
                list.remove(i);
            }
        }
        persist();
        publish();
    }

    public LiveData<List<DownloadTask>> tasks() {
        return tasks;
    }

    public synchronized List<DownloadTask> snapshot() {
        return new ArrayList<>(list);
    }

    @Nullable
    public synchronized DownloadTask find(String id) {
        for (DownloadTask task : list) {
            if (task.id.equals(id)) return task;
        }
        return null;
    }

    /** 入队并启动下载服务。同包名已有未完成任务时直接复用，避免重复下载。 */
    public void enqueue(@NonNull Context context, @NonNull DownloadTask task) {
        DownloadTask existing;
        synchronized (this) {
            existing = find(task.id);
            if (existing != null && existing.isActive()) {
                existing.status = DownloadTask.STATUS_QUEUED;
                publish();
                startService(context);
                return;
            }
            if (existing != null) list.remove(existing);
            list.add(task);
        }
        publish();
        reportDownload(task.id);
        startService(context);
    }

    /** 上报一次下载（服务端把对应 ini 的下载量 +1）。失败静默，不影响下载。 */
    private static void reportDownload(final String id) {
        if (id == null || id.isEmpty()) return;
        new Thread(() -> {
            try {
                String url = "https://res.typheye.cn/api.php?action=download&package="
                        + java.net.URLEncoder.encode(id, "UTF-8");
                java.net.HttpURLConnection connection =
                        (java.net.HttpURLConnection) new java.net.URL(url).openConnection();
                connection.setConnectTimeout(8000);
                connection.setReadTimeout(8000);
                connection.getResponseCode();
                connection.disconnect();
            } catch (Exception ignored) {
            }
        }, "download-report").start();
    }

    /**
     * 重试前更新下载地址（例如重新测速后换成最快节点、或刷新过期的签名地址）。
     *
     * @param fallback 兜底地址（接口给的权威地址），可为空
     */
    public synchronized void updateUrls(@NonNull String id, @NonNull String url,
                                        @Nullable String fallback) {
        DownloadTask task = find(id);
        if (task == null || url.isEmpty()) return;
        task.downloadUrl = url;
        if (fallback != null && !fallback.isEmpty()) task.fallbackUrl = fallback;
        publish();
    }

    public void startService(@NonNull Context context) {
        Intent intent = new Intent(context, DownloadService.class)
                .setAction(DownloadService.ACTION_KICK);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    /** 通知服务对某任务执行动作（pause / resume / cancel / remove）。 */
    public void control(@NonNull Context context, @NonNull String id, @NonNull String action) {
        Intent intent = new Intent(context, DownloadService.class)
                .setAction(action)
                .putExtra(DownloadService.EXTRA_ID, id);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    public synchronized void remove(String id) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).id.equals(id)) {
                list.remove(i);
                break;
            }
        }
        publish();
    }

    synchronized void publish() {
        persist();
        tasks.postValue(new ArrayList<>(list));
    }

    // ---- 持久化 ----

    private void load() {
        list.clear();
        if (appContext == null) return;
        String raw = appContext.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .getString(KEY_TASKS, "");
        if (raw == null || raw.isEmpty()) return;
        try {
            JSONArray array = new JSONArray(raw);
            for (int i = 0; i < array.length(); i++) {
                JSONObject o = array.optJSONObject(i);
                if (o == null) continue;
                DownloadTask task = fromJson(o);
                if (task != null && !task.id.isEmpty()) list.add(task);
            }
        } catch (Exception ignored) {
        }
    }

    private void persist() {
        if (appContext == null) return;
        try {
            JSONArray array = new JSONArray();
            for (DownloadTask task : list) array.put(toJson(task));
            SharedPreferences.Editor editor = appContext
                    .getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit();
            editor.putString(KEY_TASKS, array.toString()).apply();
        } catch (Exception ignored) {
        }
    }

    private static JSONObject toJson(DownloadTask t) {
        JSONObject o = new JSONObject();
        try {
            o.put("id", t.id);
            o.put("name", t.name);
            o.put("iconUrl", t.iconUrl);
            o.put("downloadUrl", t.downloadUrl);
            o.put("size", t.sizeLabel);
            o.put("dest", t.destination);
            o.put("detailType", t.detailType);
            o.put("detailJson", t.detailJson);
            o.put("status", t.status);
            o.put("total", t.totalBytes);
            o.put("down", t.downloadedBytes);
            o.put("localPath", t.localPath == null ? "" : t.localPath);
            o.put("error", t.error == null ? "" : t.error);
            o.put("targetUri", t.targetUri == null ? "" : t.targetUri);
        } catch (Exception ignored) {
        }
        return o;
    }

    @Nullable
    private static DownloadTask fromJson(JSONObject o) {
        try {
            DownloadTask t = new DownloadTask(
                    o.optString("id"), o.optString("name"), o.optString("iconUrl"),
                    o.optString("downloadUrl"), o.optString("size"), o.optInt("dest"),
                    o.optString("detailType"), o.optString("detailJson"));
            t.totalBytes = o.optLong("total", -1L);
            t.downloadedBytes = o.optLong("down", 0L);
            int status = o.optInt("status", DownloadTask.STATUS_PAUSED);
            // 重启后不自动续传：进行中/排队的标成「已暂停」。
            if (status == DownloadTask.STATUS_RUNNING || status == DownloadTask.STATUS_QUEUED) {
                status = DownloadTask.STATUS_PAUSED;
            }
            t.status = status;
            t.localPath = emptyToNull(o.optString("localPath"));
            t.error = emptyToNull(o.optString("error"));
            t.targetUri = emptyToNull(o.optString("targetUri"));
            return t;
        } catch (Exception e) {
            return null;
        }
    }

    @Nullable
    private static String emptyToNull(String value) {
        return value == null || value.isEmpty() ? null : value;
    }
}
