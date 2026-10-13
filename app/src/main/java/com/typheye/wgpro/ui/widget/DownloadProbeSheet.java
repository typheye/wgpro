package com.typheye.wgpro.ui.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.typheye.wgpro.R;
import com.typheye.wgpro.core.download.DownloadNodes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 「下载测速」底部弹窗：拿到下载权限后弹出，先把候选节点测速再开始下载，
 * 取代原先的「已加入下载」轻提示（轻提示没有过程、也不能取消）。
 *
 * <p>外形与登录/请求中的加载弹窗完全一致（{@code progress_dialog.xml}：转圈 + 一行文字 + 取消），
 * 文案只有「正在测速 (n/n)」这一行，不展示节点明细。</p>
 *
 * <p>流程见 {@code _guide/download-node-probe.md}：镜像 ≤1 → 直接下权威地址；
 * 否则并发测速选最快（≥50KB/s，2.5s 时间窗平均速度），失败一律回退权威地址。</p>
 */
public final class DownloadProbeSheet {

    /** 结果回调（都在主线程）。 */
    public interface Callback {
        /** 可以开始下载了。 */
        void onReady(@NonNull String url);

        /** 用户在测速过程中取消：本次不下载。 */
        void onCancelled();
    }

    /** 弹窗最短展示时长，避免一闪而过。 */
    private static final long MIN_SHOW_MS = 300L;
    /** 等节点清单的上限：超时就直接用权威地址下载（清单拉取失败不该让用户干等）。 */
    private static final long NODE_WAIT_MS = 4000L;

    private DownloadProbeSheet() { }

    /**
     * 弹出测速弹窗并开始探测。
     *
     * @param itemName    应用/资源名（保留参数，便于后续文案扩展）
     * @param downloadUrl 权威下载地址（兜底，任何失败都用它）
     * @param mirrors     详情里的 download_mirrors（可为 null / 空）
     */
    public static void start(@NonNull Context context, @Nullable String itemName,
                             @NonNull String downloadUrl, @Nullable JSONArray mirrors,
                             @NonNull Callback callback) {
        Map<String, String> mirrorMap = parseMirrors(mirrors);

        View content = View.inflate(context, R.layout.progress_dialog, null);
        TextView message = content.findViewById(android.R.id.message);

        final boolean[] finished = {false};
        final DownloadNodes.ProbeTask[] taskRef = new DownloadNodes.ProbeTask[1];
        final WGProBottomSheetDialog[] dialogRef = new WGProBottomSheetDialog[1];
        final long startedAt = android.os.SystemClock.uptimeMillis();

        WGProBottomSheetDialog dialog = new WGProAlertDialogBuilder(context)
                .setTitle("下载测速")
                .setView(content)
                .setCancelable(false)
                .setNegativeButton("取消", (d, which) -> {
                    if (finished[0]) return;
                    finished[0] = true;
                    if (taskRef[0] != null) taskRef[0].cancel();
                    callback.onCancelled();
                })
                .create();
        dialogRef[0] = dialog;
        dialog.setOnDismissListener(d -> {
            if (finished[0]) return;
            finished[0] = true;
            if (taskRef[0] != null) taskRef[0].cancel();
            callback.onCancelled();
        });
        message.setText("正在测速…");   // 还不知道节点数，先不写 (n/n)
        dialog.show();

        // 收尾：保证最短展示时长，然后回调
        final java.util.function.BiConsumer<String, Boolean> settle = (url, cancelled) -> {
            long remain = MIN_SHOW_MS - (android.os.SystemClock.uptimeMillis() - startedAt);
            Runnable action = () -> {
                if (finished[0]) return;
                finished[0] = true;
                WGProBottomSheetDialog sheet = dialogRef[0];
                // 不判断 isShowing()：弹窗可能还在"排队弹出"，也要让排队的 show 作废（否则会卡住）
                if (sheet != null) sheet.dismissForReplacement();
                if (cancelled) callback.onCancelled(); else callback.onReady(url);
            };
            if (remain > 0) {
                new android.os.Handler(android.os.Looper.getMainLooper()).postDelayed(action, remain);
            } else {
                action.run();
            }
        };

        // 镜像 ≤1：直接开始，不浪费流量（文档 §4）
        if (mirrorMap.size() <= 1) {
            message.setText("正在准备下载…");
            settle.accept(downloadUrl, false);
            return;
        }
        // 用户限制后台流量：跳过测速
        if (DownloadNodes.shouldSkipProbe(context)) {
            message.setText("正在准备下载…");
            settle.accept(downloadUrl, false);
            return;
        }
        // 10 分钟内记住的最优节点：直接复用
        String remembered = DownloadNodes.bestNodeId();
        if (remembered != null && mirrorMap.containsKey(remembered)) {
            message.setText("正在准备下载…");
            settle.accept(mirrorMap.get(remembered), false);
            return;
        }

        // 兜底：清单/测速最多等 NODE_WAIT_MS，超时直接下权威地址，绝不干等
        final Runnable nodesTimeout = () -> {
            if (finished[0]) return;
            message.setText("正在准备下载…");
            settle.accept(downloadUrl, false);
        };
        new android.os.Handler(android.os.Looper.getMainLooper())
                .postDelayed(nodesTimeout, NODE_WAIT_MS);

        DownloadNodes.load(context, (nodes, spec) -> {
            if (finished[0]) return;
            new android.os.Handler(android.os.Looper.getMainLooper()).removeCallbacks(nodesTimeout);
            if (nodes == null || nodes.isEmpty() || spec == null) {
                message.setText("正在准备下载…");
                settle.accept(downloadUrl, false);
                return;
            }
            final int total = nodes.size();
            message.setText("正在测速 (1/" + total + ")");
            taskRef[0] = DownloadNodes.probe(context, nodes, spec, new DownloadNodes.ProbeCallback() {
                @Override
                public void onProgress(@NonNull List<DownloadNodes.ProbeResult> done, int ignored) {
                    if (finished[0]) return;
                    int shown = Math.min(total, Math.max(1, done.size()));
                    message.setText("正在测速 (" + shown + "/" + total + ")");
                }

                @Override
                public void onFinished(@Nullable DownloadNodes.Node best,
                                       @NonNull List<DownloadNodes.ProbeResult> results) {
                    if (finished[0]) return;
                    message.setText("正在测速 (" + total + "/" + total + ")");
                    if (best == null) {
                        settle.accept(downloadUrl, false);
                        return;
                    }
                    String url = mirrorMap.get(best.id);
                    if (url == null || url.isEmpty()) {
                        settle.accept(downloadUrl, false);
                        return;
                    }
                    DownloadNodes.rememberBest(best.id);
                    settle.accept(url, false);
                }
            });
        });
    }

    /** download_mirrors → id:url（顺序保留）。 */
    @NonNull
    private static Map<String, String> parseMirrors(@Nullable JSONArray array) {
        Map<String, String> map = new LinkedHashMap<>();
        if (array == null) return map;
        for (int i = 0; i < array.length(); i++) {
            JSONObject item = array.optJSONObject(i);
            if (item == null) continue;
            String id = item.optString("id", "");
            String url = item.optString("url", "");
            if (!id.isEmpty() && !url.isEmpty()) map.put(id, url);
        }
        return map;
    }
}
