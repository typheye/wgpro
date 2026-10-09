package com.typheye.wgpro.core.download;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

/**
 * 进程内下载任务状态源（单例）。
 *
 * <p>由 {@link DownloadService} 写入，下载管理页/详情页订阅。所有写入都通过
 * {@code postValue} 发布快照，可从任意线程调用。</p>
 */
public final class DownloadManager {
    private static final DownloadManager INSTANCE = new DownloadManager();

    private final MutableLiveData<List<DownloadTask>> tasks = new MutableLiveData<>(new ArrayList<>());
    private final List<DownloadTask> list = new ArrayList<>();

    private DownloadManager() {
    }

    public static DownloadManager get() {
        return INSTANCE;
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
                // 已在下载/排队中，沿用旧任务（仅更新去向）。
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
                String url = "https://res.typheye.cn/app_download.php?package="
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
        tasks.postValue(new ArrayList<>(list));
    }
}
