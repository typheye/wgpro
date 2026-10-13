package com.typheye.wgpro.core.download;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.typheye.wgpro.utils.tPointsUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 下载节点测速与优选（服务端说明见 {@code _guide/download-node-probe.md}）。
 *
 * <ul>
 *   <li>{@code GET res.typheye.cn/api.php?action=nodes} 拿"探测哪些节点 + 探测方法"（公开、可缓存 ttl 秒）</li>
 *   <li>应用/资源详情里的 {@code download_mirrors} 拿"同一份内容在各节点的地址"</li>
 * </ul>
 *
 * <p>规则：只在"用户点了下载、即将开始"时测速；每节点最多读 {@code probe.bytes}（默认 512KB）；
 * 并发探测；失败一律回退权威地址 {@code download_url}；最优节点 10 分钟内复用。</p>
 */
public final class DownloadNodes {

    private static final String NODES_URL = "https://res.typheye.cn/api.php?action=nodes";
    /** 速度阈值：低于它视为不可用（文档建议 50 KB/s）。 */
    public static final long MIN_KBPS = 50L;
    /**
     * 测速采样时间窗：读满一个 512KB 分片可能只要 100ms（瞬时速度，不能代表平均速度），
     * 所以在时间窗内**持续读**、算整段的平均速度。Wi-Fi 2.5s，计量网络缩短到 1s（省流量）。
     */
    public static final long WINDOW_MS = 2500L;
    public static final long WINDOW_MS_METERED = 1000L;
    /** 单个节点的探测数据上限，避免极快网络下无限读（Wi-Fi 8MB / 计量 1MB）。 */
    private static final long MAX_BYTES = 8L * 1024L * 1024L;
    private static final long MAX_BYTES_METERED = 1024L * 1024L;
    /** 预热丢弃的字节数（跳过 TCP 慢启动/TTFB，取更"纯"的吞吐）。 */
    private static final long WARMUP_BYTES = 64L * 1024L;
    /** 服务端测速文件大小（1MB），读完要换新 range 续采。 */
    private static final long PROBE_FILE_BYTES = 1024L * 1024L;
    /** 最优节点记忆时长（文档建议 10 分钟）。 */
    private static final long BEST_TTL_MS = 10L * 60L * 1000L;

    /** 探测方法。 */
    public static final class ProbeSpec {
        public final int bytes;
        public final String range;
        public final long timeoutMs;

        ProbeSpec(int bytes, @Nullable String range, long timeoutMs) {
            this.bytes = bytes > 0 ? bytes : 512 * 1024;
            this.range = range == null || range.isEmpty() ? "bytes=0-524287" : range;
            this.timeoutMs = timeoutMs > 0 ? timeoutMs : 8000L;
        }
    }

    /** 一个下载节点。 */
    public static final class Node {
        public final String id;
        public final String name;
        public final String probe;

        Node(@NonNull String id, @NonNull String name, @NonNull String probe) {
            this.id = id;
            this.name = name;
            this.probe = probe;
        }
    }

    /** 单个节点的测速结果。 */
    public static final class ProbeResult {
        public final Node node;
        /** KB/s；0 = 不可用。 */
        public final long kbps;

        ProbeResult(@NonNull Node node, long kbps) {
            this.node = node;
            this.kbps = kbps;
        }
    }

    /** 可取消的测速任务。 */
    public interface ProbeTask {
        void cancel();
    }

    public interface NodesCallback {
        void onResult(@Nullable List<Node> nodes, @Nullable ProbeSpec spec);
    }

    public interface ProbeCallback {
        /** 每完成一个节点回调一次（主线程），用于刷新弹窗；全部完成后 best 非空并收尾。 */
        void onProgress(@NonNull List<ProbeResult> done, int total);

        void onFinished(@Nullable Node best, @NonNull List<ProbeResult> results);
    }

    private static volatile List<Node> cachedNodes;
    private static volatile ProbeSpec cachedSpec;
    private static volatile long nodesAt = 0L;
    private static volatile long nodesTtlMs = 600_000L;
    private static volatile String bestNodeId = "";
    private static volatile long bestAt = 0L;

    private DownloadNodes() { }

    /** 上次记住的最优节点（10 分钟内有效）。 */
    @Nullable
    public static String bestNodeId() {
        if (System.currentTimeMillis() - bestAt > BEST_TTL_MS) return null;
        return bestNodeId.isEmpty() ? null : bestNodeId;
    }

    public static void rememberBest(@Nullable String nodeId) {
        bestNodeId = nodeId == null ? "" : nodeId;
        bestAt = System.currentTimeMillis();
    }

    /**
     * 用户是否限制了后台流量（省流量/流量节省程序）：被限制时按文档要求跳过测速。
     */
    public static boolean shouldSkipProbe(@NonNull Context context) {
        try {
            ConnectivityManager manager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager == null) return false;
            if (manager.isActiveNetworkMetered()) {
                // 计量网络下：只在系统明确限制后台流量时跳过（尊重省流量设置）
                return manager.getRestrictBackgroundStatus()
                        == ConnectivityManager.RESTRICT_BACKGROUND_STATUS_ENABLED;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /** 当前是否走计量网络（仅用于展示/日志）。 */
    public static boolean isMetered(@NonNull Context context) {
        try {
            ConnectivityManager manager = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return manager != null && manager.isActiveNetworkMetered();
        } catch (Exception e) {
            return false;
        }
    }

    /** 取节点清单（内存缓存 ttl 秒；失败回调 null，调用方直接下权威地址）。 */
    public static void load(@NonNull Context context, @NonNull NodesCallback callback) {
        List<Node> nodes = cachedNodes;
        ProbeSpec spec = cachedSpec;
        if (nodes != null && !nodes.isEmpty()
                && System.currentTimeMillis() - nodesAt < nodesTtlMs) {
            callback.onResult(nodes, spec);
            return;
        }
        final Context app = context.getApplicationContext();
        final String url = tPointsUtils.withResAuth(app, NODES_URL);
        Request request;
        try {
            request = new Request.Builder().url(url).get().build();
        } catch (Exception e) {
            callback.onResult(null, null);
            return;
        }
        client().newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull java.io.IOException e) {
                main(() -> callback.onResult(cachedNodes, cachedSpec));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                List<Node> parsed = null;
                ProbeSpec parsedSpec = null;
                try (Response closed = response) {
                    if (closed.isSuccessful() && closed.body() != null) {
                        JSONObject json = new JSONObject(closed.body().string());
                        parsed = parseNodes(json.optJSONArray("nodes"));
                        parsedSpec = parseSpec(json.optJSONObject("probe"));
                        long ttl = json.optLong("ttl", 600L);
                        if (ttl > 0) nodesTtlMs = ttl * 1000L;
                        if (parsed != null && !parsed.isEmpty()) {
                            cachedNodes = parsed;
                            cachedSpec = parsedSpec;
                            nodesAt = System.currentTimeMillis();
                        }
                    }
                } catch (Exception ignored) {
                }
                final List<Node> result = parsed == null || parsed.isEmpty() ? cachedNodes : parsed;
                final ProbeSpec spec = parsedSpec == null ? cachedSpec : parsedSpec;
                main(() -> callback.onResult(result, spec));
            }
        });
    }

    /**
     * 并发对每个节点测速（每节点读 {@code spec.bytes} 字节计时），
     * 返回可取消句柄；全部完成后回调最优节点（达不到阈值则为 null）。
     */
    @NonNull
    public static ProbeTask probe(@NonNull Context context, @NonNull List<Node> nodes,
                                  @NonNull ProbeSpec spec, @NonNull ProbeCallback callback) {
        final boolean metered = isMetered(context);
        final List<Call> calls = java.util.Collections.synchronizedList(new ArrayList<>());
        final List<ProbeResult> done = java.util.Collections.synchronizedList(new ArrayList<>());
        final boolean[] cancelled = {false};
        // 连接超时收紧到 3s：不可达节点快速失败，别把弹窗拖到 8s+
        final OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Math.min(3000L, spec.timeoutMs), TimeUnit.MILLISECONDS)
                .readTimeout(spec.timeoutMs, TimeUnit.MILLISECONDS)
                .build();
        final int total = nodes.size();
        new Thread(() -> {
            List<Thread> threads = new ArrayList<>();
            for (Node node : nodes) {
                Thread t = new Thread(() -> {
                    long kbps = measure(client, node, spec, calls, metered);
                    if (cancelled[0]) return;
                    done.add(new ProbeResult(node, kbps));
                    main(() -> {
                        if (!cancelled[0]) callback.onProgress(new ArrayList<>(done), total);
                    });
                });
                t.start();
                threads.add(t);
            }
            // 整体预算 = 采样时间窗 + 收尾余量；到点就取消还在飞的探测（不干等 8s 超时）
            final long budgetMs = (metered ? WINDOW_MS_METERED : WINDOW_MS) + 800L;
            final long deadlineAt = System.currentTimeMillis() + budgetMs;
            for (Thread t : threads) {
                long remain = deadlineAt - System.currentTimeMillis();
                if (remain <= 0L) break;
                try {
                    t.join(remain);
                } catch (InterruptedException ignored) { }
            }
            synchronized (calls) {
                for (Call call : calls) {
                    try {
                        call.cancel();
                    } catch (Exception ignored) { }
                }
            }
            for (Thread t : threads) {
                if (deadlineAt - System.currentTimeMillis() > 0) {
                    try {
                        t.join(200L);
                    } catch (InterruptedException ignored) { }
                }
            }
            if (cancelled[0]) return;
            Node best = null;
            long bestKbps = 0;
            for (ProbeResult r : done) {
                if (r.kbps >= MIN_KBPS && r.kbps > bestKbps) {
                    bestKbps = r.kbps;
                    best = r.node;
                }
            }
            final Node winner = best;
            main(() -> {
                if (!cancelled[0]) callback.onFinished(winner, new ArrayList<>(done));
            });
        }).start();
        return () -> {
            cancelled[0] = true;
            synchronized (calls) {
                for (Call call : calls) {
                    try {
                        call.cancel();
                    } catch (Exception ignored) { }
                }
            }
        };
    }

    /**
     * 单节点测速：在**时间窗内持续读**（一个 1MB 探测文件读完就用新 range 续采），
     * 丢弃开头 {@link #WARMUP_BYTES} 的预热段，返回整段的**平均** KB/s（失败 / 太慢返回 0）。
     */
    private static long measure(@NonNull OkHttpClient client, @NonNull Node node,
                                @NonNull ProbeSpec spec, @NonNull List<Call> calls,
                                boolean metered) {
        final long windowMs = metered ? WINDOW_MS_METERED : WINDOW_MS;
        final long maxBytes = metered ? MAX_BYTES_METERED : MAX_BYTES;
        final long deadline = System.nanoTime() + windowMs * 1_000_000L;
        long totalRead = 0L;      // 含预热
        long timedRead = 0L;      // 预热之后的字节数（真正参与计时的部分）
        long timedStart = 0L;     // 预热结束的时间点
        long nextStart = 0L;      // 下一次续采的起点（探测文件是 1MB，按 512KB 分两片轮着读）
        int pass = 0;
        byte[] buffer = new byte[64 * 1024];

        while (System.nanoTime() < deadline && totalRead < maxBytes) {
            Call call = null;
            try {
                // 注意：spec.range 已经是完整请求头（形如 "bytes=0-524287"），不要再拼 "bytes="
                String rangeHeader;
                if (pass == 0) {
                    rangeHeader = spec.range;
                } else {
                    long slice = Math.max(64L * 1024L, PROBE_FILE_BYTES / 2);
                    rangeHeader = "bytes=" + nextStart + "-" + (nextStart + slice - 1);
                }
                Request request = new Request.Builder()
                        .url(node.probe + (node.probe.contains("?") ? "&" : "?") + "_=" + System.nanoTime())
                        .header("Range", rangeHeader)
                        .get()
                        .build();
                call = client.newCall(request);
                calls.add(call);
                try (Response response = call.execute()) {
                    if (!response.isSuccessful() || response.body() == null) {
                        return timedStart == 0L ? 0L : kbpsOf(timedRead, timedStart);
                    }
                    InputStream in = response.body().byteStream();
                    while (System.nanoTime() < deadline && totalRead < maxBytes) {
                        int n = in.read(buffer, 0, buffer.length);
                        if (n <= 0) break;
                        totalRead += n;
                        if (totalRead > WARMUP_BYTES) {
                            if (timedStart == 0L) timedStart = System.nanoTime();
                            timedRead += n;
                        }
                    }
                }
            } catch (Exception e) {
                // 中途出错（含被取消）：已有采样就用已有采样，否则视为不可用
                break;
            } finally {
                if (call != null) calls.remove(call);
            }
            // 一片读完就换另一片继续采（同一节点、连续测量；query 带随机数绕缓存）
            pass++;
            long slice = Math.max(64L * 1024L, PROBE_FILE_BYTES / 2);
            nextStart = (nextStart == 0L) ? slice : 0L;
        }
        return timedStart == 0L ? 0L : kbpsOf(timedRead, timedStart);
    }

    /** 平均速度（KB/s）：从计时起点到现在。 */
    private static long kbpsOf(long bytes, long timedStartNanos) {
        double seconds = (System.nanoTime() - timedStartNanos) / 1e9;
        if (bytes <= 0 || seconds <= 0) return 0L;
        return Math.round(bytes / 1024.0 / seconds);
    }

    @Nullable
    private static List<Node> parseNodes(@Nullable JSONArray array) {
        if (array == null) return null;
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject item = array.optJSONObject(i);
            if (item == null) continue;
            String id = item.optString("id", "");
            String probe = item.optString("probe", "");
            if (id.isEmpty() || probe.isEmpty()) continue;
            String name = item.optString("name", id);
            nodes.add(new Node(id, name, probe));
        }
        return nodes;
    }

    @Nullable
    private static ProbeSpec parseSpec(@Nullable JSONObject json) {
        if (json == null) return null;
        return new ProbeSpec(json.optInt("bytes", 512 * 1024), json.optString("range", ""),
                json.optLong("timeout_ms", 8000L));
    }

    private static void main(@NonNull Runnable action) {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(action);
    }

    /** 共享 OkHttp（短超时）。 */
    private static volatile OkHttpClient shared;

    private static OkHttpClient client() {
        OkHttpClient local = shared;
        if (local != null) return local;
        synchronized (DownloadNodes.class) {
            if (shared == null) {
                // 清单拉取要"快失败"：拿不到清单就直接用权威地址下，不能让弹窗干等
                shared = new OkHttpClient.Builder()
                        .connectTimeout(3, TimeUnit.SECONDS)
                        .readTimeout(4, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
                        .build();
            }
            return shared;
        }
    }
}
