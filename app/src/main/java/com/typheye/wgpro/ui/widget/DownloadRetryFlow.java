package com.typheye.wgpro.ui.widget;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.typheye.wgpro.core.download.DownloadManager;
import com.typheye.wgpro.core.download.DownloadService;
import com.typheye.wgpro.core.download.DownloadTask;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * 下载失败/重试的统一处理：
 *
 * <ol>
 *   <li><b>重试要重新测速</b>：先弹「下载测速」弹窗重新挑最快节点（旧节点可能已经变慢/挂了），
 *       选好再带新地址继续下载；用户取消则不下载。</li>
 *   <li><b>失败不能静默</b>：失败时弹出失败信息弹窗（带「重试」/「关闭」），同一条任务同一错误只提示一次。</li>
 * </ol>
 */
public final class DownloadRetryFlow {

    /** 已经提示过的「任务 + 错误」组合，避免 LiveData 多次回调弹一堆弹窗。 */
    private static final Set<String> NOTIFIED = Collections.newSetFromMap(new WeakHashMap<>());
    /** 上一次观察到的状态：只有"本次会话内从非失败转为失败"才提示（恢复出来的历史失败不弹）。 */
    private static final Map<String, Integer> LAST_STATUS = new HashMap<>();
    /** 当前是否已有失败弹窗在显示：同一时间只留一个，避免连环弹。 */
    private static boolean failureDialogShowing = false;

    private DownloadRetryFlow() { }

    /** 用户在详情页/下载管理点「重试」：重新测速后再继续下载。 */
    public static void retry(@NonNull Context context, @NonNull DownloadTask task,
                             @Nullable Runnable onStarted) {
        if (task == null) return;
        // 这次重试如果又失败，要能再次弹提示（清掉上一次的"已提示"记录）
        resetNotification(task);
        final String authoritative = task.fallbackUrl != null && !task.fallbackUrl.isEmpty()
                ? task.fallbackUrl : task.downloadUrl;
        DownloadProbeSheet.start(context, task.name, authoritative, mirrorsOf(task),
                new DownloadProbeSheet.Callback() {
                    @Override
                    public void onReady(@NonNull String url) {
                        DownloadManager.get().updateUrls(task.id, url, authoritative);
                        DownloadManager.get().control(context, task.id, DownloadService.ACTION_RESUME);
                        if (onStarted != null) onStarted.run();
                    }

                    @Override
                    public void onCancelled() {
                        // 用户取消：保持失败态，可稍后再点重试
                    }
                });
    }

    /**
     * 失败提示弹窗——**严格限定**，避免打扰：
     * <ol>
     *   <li>只提示<b>本次会话内</b>由"进行中/排队/完成"转为失败的任务；
     *       冷启动恢复出来的历史失败、页面刚打开时就已经是失败态的，一律不弹。</li>
     *   <li>同一时间只允许一个失败弹窗（前一个没关掉就不再弹）。</li>
     *   <li>同一条任务 + 同一个错误只弹一次（重试会清掉记录）。</li>
     * </ol>
     */
    public static void notifyFailure(@NonNull Context context, @Nullable DownloadTask task,
                                     @Nullable Runnable onRetry) {
        if (task == null) return;
        int previous;
        synchronized (LAST_STATUS) {
            Integer last = LAST_STATUS.put(task.id, task.status);
            previous = last == null ? -1 : last;
        }
        if (task.status != DownloadTask.STATUS_FAILED) return;
        // 本次会话内没有观察过它的非失败状态 → 视为历史失败，不打扰
        if (previous == -1 || previous == DownloadTask.STATUS_FAILED) return;
        String error = friendly(task.error);
        String key = task.id + "|" + error;
        synchronized (NOTIFIED) {
            if (NOTIFIED.contains(key)) return;
            NOTIFIED.add(key);
        }
        synchronized (DownloadRetryFlow.class) {
            if (failureDialogShowing) return;
            failureDialogShowing = true;
        }
        String message = error.isEmpty() ? "下载失败，请稍后重试。" : error;
        WGProAlertDialogBuilder builder = new WGProAlertDialogBuilder(context)
                .setTitle("下载失败")
                .setMessage(message + (task.name == null || task.name.isEmpty() ? "" : "\n（" + task.name + "）"))
                .setNegativeButton("关闭", null);
        if (onRetry != null) {
            builder.setPositiveButton("重试", (dialog, which) -> onRetry.run());
        }
        WGProBottomSheetDialog dialog = builder.create();
        dialog.setOnDismissListener(d -> {
            synchronized (DownloadRetryFlow.class) {
                failureDialogShowing = false;
            }
        });
        dialog.show();
    }

    /** 历史任务里可能存着旧的英文错误（如 timeout），显示前兜一层中文。 */
    @NonNull
    private static String friendly(@Nullable String raw) {
        if (raw == null) return "";
        String value = raw.trim();
        String lower = value.toLowerCase(java.util.Locale.ROOT);
        if (lower.equals("timeout") || lower.contains("timed out") || lower.contains("timeout")) {
            return "网络超时，请检查网络后重试";
        }
        if (lower.contains("unable to resolve host") || lower.contains("unknownhost")) {
            return "无法连接服务器，请检查网络";
        }
        if (lower.contains("failed to connect") || lower.contains("connection refused")) {
            return "连接被拒绝或网络不可达，请稍后重试";
        }
        if (lower.contains("connection reset") || lower.contains("broken pipe")) {
            return "连接被中断，可重试续传";
        }
        return value;
    }

    /** 新状态到来时清掉该任务的提示记录（例如已重试成功），便于下次失败仍能提示。 */
    public static void resetNotification(@NonNull DownloadTask task) {
        synchronized (LAST_STATUS) {
            LAST_STATUS.put(task.id, task.status);
        }
        synchronized (NOTIFIED) {
            String prefix = task.id + "|";
            NOTIFIED.removeIf(key -> key.startsWith(prefix));
        }
    }

    /** 从任务里存的详情 JSON 取 download_mirrors。 */
    @Nullable
    private static JSONArray mirrorsOf(@NonNull DownloadTask task) {
        try {
            if (task.detailJson == null || task.detailJson.isEmpty()) return null;
            JSONObject json = new JSONObject(task.detailJson);
            return json.optJSONArray("download_mirrors");
        } catch (Exception e) {
            return null;
        }
    }
}
