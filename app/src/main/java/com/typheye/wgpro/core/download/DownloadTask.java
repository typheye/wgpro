package com.typheye.wgpro.core.download;

/**
 * 一条应用下载任务。
 *
 * <p>字段大多用 {@code volatile}，因为进度由 {@code DownloadService} 的工作线程写入，
 * 由 UI 线程读取。</p>
 */
public final class DownloadTask {
    public static final int STATUS_QUEUED = 0;
    public static final int STATUS_RUNNING = 1;
    public static final int STATUS_PAUSED = 2;
    public static final int STATUS_COMPLETED = 3;
    public static final int STATUS_FAILED = 4;

    /** 安装去向。 */
    public static final int DEST_DEVICE = 0; // 安装到已连接的穿戴设备（手表端能力待接入）
    public static final int DEST_PHONE = 1;  // 安装到手机
    public static final int DEST_LOCAL = 2;  // 保存到本地 Download 目录

    public final String id;          // 应用包名，作为任务唯一键
    public final String name;
    public final String iconUrl;
    public final String downloadUrl;
    public final String sizeLabel;   // 接口返回的原始大小字符串，如 "39.6M"
    public final int destination;
    /** 详情类型："app" / "resource"，用于长按「查看详情」。 */
    public final String detailType;
    /** 详情页 JSON（点「查看详情」时透传给详情 Activity）。 */
    public final String detailJson;
    public final long createdAt = System.currentTimeMillis();

    public volatile long totalBytes = -1L;
    public volatile long downloadedBytes = 0L;
    public volatile int status = STATUS_QUEUED;
    public volatile String localPath;
    public volatile String error;
    /** 「保存到本地」时由 SAF（系统文件框架）选定的目标 URI；为空则回退到公共 Download 目录。 */
    public volatile String targetUri;

    public DownloadTask(String id, String name, String iconUrl, String downloadUrl,
                        String sizeLabel, int destination) {
        this(id, name, iconUrl, downloadUrl, sizeLabel, destination, "", "");
    }

    public DownloadTask(String id, String name, String iconUrl, String downloadUrl,
                        String sizeLabel, int destination, String detailType, String detailJson) {
        this.id = id;
        this.name = name;
        this.iconUrl = iconUrl;
        this.downloadUrl = downloadUrl;
        this.sizeLabel = sizeLabel == null ? "" : sizeLabel;
        this.destination = destination;
        this.detailType = detailType == null ? "" : detailType;
        this.detailJson = detailJson == null ? "" : detailJson;
    }

    /** 0..100；未知总大小时返回 0（UI 用不确定进度）。 */
    public int progress() {
        if (totalBytes <= 0 || downloadedBytes <= 0) return 0;
        return (int) Math.min(100L, downloadedBytes * 100L / totalBytes);
    }

    public boolean isActive() {
        return status == STATUS_QUEUED || status == STATUS_RUNNING || status == STATUS_PAUSED;
    }

    public boolean isFinished() {
        return status == STATUS_COMPLETED || status == STATUS_FAILED;
    }

    public String statusLabel() {
        switch (status) {
            case STATUS_QUEUED: return "等待中";
            case STATUS_RUNNING: return "下载中";
            case STATUS_PAUSED: return "已暂停";
            case STATUS_COMPLETED: return "已完成";
            case STATUS_FAILED: return error == null || error.isEmpty() ? "失败" : error;
            default: return "";
        }
    }
}
