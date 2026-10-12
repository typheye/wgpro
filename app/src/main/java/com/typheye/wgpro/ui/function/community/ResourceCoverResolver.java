package com.typheye.wgpro.ui.function.community;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.typheye.wgpro.utils.tAccUtils;
import com.typheye.wgpro.utils.tPointsUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 资源封面地址解析。
 *
 * <p>社区接口（service.typheye.cn 的 resources2）返回的 {@code cover_url} 是
 * {@code https://res.typheye.cn/share/<id>/cover.png} 这种**旧直链**——按新的分发规范
 * 已被 410 永久封禁，所以列表里封面加载不出来（只剩色块）。</p>
 *
 * <p>这里统一改用 res 的**签名地址**：一次拉取 share 列表（10 分钟缓存），
 * 建立 {@code 资源id → cover_url} 映射；渲染时先用缓存填充，未命中就异步补图。</p>
 */
public final class ResourceCoverResolver {

    private static final String LIST_URL =
            "https://res.typheye.cn/api.php?type=share&page=1&size=50";
    private static final long TTL_MS = 10L * 60L * 1000L;

    private static final Map<String, String> COVERS = new HashMap<>();
    private static final List<Runnable> PENDING = new ArrayList<>();
    private static volatile long loadedAt = 0L;
    private static volatile boolean loading = false;
    /** 拉取失败后的退避时间（避免每次渲染都重试）。 */
    private static volatile long retryAfter = 0L;

    private ResourceCoverResolver() { }

    /** 已知的签名封面地址（没有返回 null）。 */
    @Nullable
    public static String cover(@NonNull String id) {
        synchronized (COVERS) {
            return COVERS.get(id);
        }
    }

    /** 把签名封面写回条目（会覆盖旧的失效直链）。 */
    public static void apply(@Nullable JSONArray items) {
        if (items == null) return;
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.optJSONObject(i);
            if (item == null) continue;
            String signed = cover(item.optString("id", ""));
            if (signed == null || signed.isEmpty()) continue;
            try {
                item.put("cover_url", signed);
            } catch (Exception ignored) { }
        }
    }

    /**
     * 渲染前后调用：命中缓存直接返回 true；否则发起一次拉取，
     * 到货后回调 {@code onUpdated}（用于给已经建好的 ImageView 补图）。
     */
    public static boolean fill(@NonNull Context context, @Nullable JSONArray items,
                               @Nullable Runnable onUpdated) {
        apply(items);
        boolean fresh = System.currentTimeMillis() - loadedAt < TTL_MS && !COVERS.isEmpty();
        if (fresh) return true;
        if (onUpdated != null) {
            synchronized (PENDING) {
                PENDING.add(onUpdated);
            }
        }
        fetch(context);
        return false;
    }

    private static void fetch(@NonNull Context context) {
        if (loading || System.currentTimeMillis() < retryAfter) return;
        loading = true;
        final String url = tPointsUtils.withResAuth(context.getApplicationContext(), LIST_URL);
        Request request;
        try {
            request = new Request.Builder().url(url).get().build();
        } catch (Exception e) {
            loading = false;
            return;
        }
        new tAccUtils(context.getApplicationContext()).getClient().newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull java.io.IOException e) {
                        loading = false;
                        retryAfter = System.currentTimeMillis() + 60_000L;
                        notifyPending();
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) {
                        try (Response closed = response) {
                            if (!closed.isSuccessful() || closed.body() == null) return;
                            JSONObject json = new JSONObject(closed.body().string());
                            JSONArray list = json.optJSONArray("info");
                            if (list == null) return;
                            synchronized (COVERS) {
                                for (int i = 0; i < list.length(); i++) {
                                    JSONObject item = list.optJSONObject(i);
                                    if (item == null) continue;
                                    String cover = item.optString("cover_url", "");
                                    if (!cover.isEmpty()) {
                                        COVERS.put(item.optString("id", ""), cover);
                                    }
                                }
                            }
                            loadedAt = System.currentTimeMillis();
                            retryAfter = 0L;
                        } catch (Exception ignored) {
                        } finally {
                            loading = false;
                            notifyPending();
                        }
                    }
                });
    }

    private static void notifyPending() {
        List<Runnable> callbacks;
        synchronized (PENDING) {
            callbacks = new ArrayList<>(PENDING);
            PENDING.clear();
        }
        if (callbacks.isEmpty()) return;
        new android.os.Handler(android.os.Looper.getMainLooper())
                .post(() -> {
                    for (Runnable r : callbacks) {
                        try {
                            r.run();
                        } catch (Exception ignored) { }
                    }
                });
    }
}
