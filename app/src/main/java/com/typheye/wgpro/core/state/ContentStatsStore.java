package com.typheye.wgpro.core.state;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.typheye.wgpro.utils.tAccUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 应用/资源卡片的动态统计单一状态源。
 *
 * <p>列表正文可以走长期缓存，但下载量、星标量和当前用户星标状态必须通过批量统计接口校正。
 * 详情页收藏、下载操作也写入这里，首页/列表订阅后原地更新，不重建整页。</p>
 */
public final class ContentStatsStore {

    public static final String TYPE_APP = "app";
    public static final String TYPE_RESOURCE = "resource";
    private static final long REFRESH_THROTTLE_MS = 10_000L;
    private static final int MAX_BATCH = 50;
    private static final ContentStatsStore INSTANCE = new ContentStatsStore();

    public static final class Stats {
        public int downloads;
        public int stars;
        public boolean collected;
        public boolean collectedKnown;
        public String updatedAt = "";
        public long changedAt;
    }

    private final Map<String, Stats> values = new HashMap<>();
    private final Map<String, Long> refreshedAt = new HashMap<>();
    /** 每个内容键当前有效的请求令牌；旧请求返回时就靠它丢弃过期响应。 */
    private final Map<String, Long> inFlight = new HashMap<>();
    private final MutableLiveData<Long> revision = new MutableLiveData<>(0L);
    private final Handler main = new Handler(Looper.getMainLooper());
    @Nullable
    private String accountUid;
    private long nextRequestId;

    private ContentStatsStore() {
    }

    public static ContentStatsStore get() {
        return INSTANCE;
    }

    @NonNull
    public LiveData<Long> revision() {
        return revision;
    }

    /** 把列表首次返回的统计值写入状态源；已有状态不会被旧列表覆盖。 */
    public synchronized void seed(@NonNull Context context, @NonNull JSONArray items) {
        String viewerUid = uidOf(context);
        syncAccountLocked(viewerUid);
        boolean changed = false;
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.optJSONObject(i);
            if (item == null) continue;
            String type = typeOf(item);
            String key = keyOf(item, type);
            if (type == null || key.isEmpty() || values.containsKey(k(type, key))) continue;
            changed |= putLocked(type, key, item.optInt("downloads",
                            item.optInt("download_count", 0)),
                    item.optInt("stars", item.optInt("collection_count", 0)),
                    item.optBoolean("is_collected", false),
                    item.has("is_collected")
                            && (viewerUid.isEmpty() || item.optBoolean("personalized", false)),
                    item.optString("updated_at", item.optString("updatedAt", "")), false);
        }
        if (changed) publishLocked();
    }

    /** 把服务端统计结果应用到 JSONArray；返回是否至少有一项变化。 */
    public synchronized boolean apply(@NonNull Context context, @NonNull JSONArray items) {
        syncAccountLocked(uidOf(context));
        boolean changed = false;
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.optJSONObject(i);
            if (item == null) continue;
            String type = typeOf(item);
            String key = keyOf(item, type);
            Stats stats = values.get(k(type, key));
            if (stats == null) continue;
            boolean itemChanged = item.optInt("downloads",
                            item.optInt("download_count", -1)) != stats.downloads
                    || item.optInt("stars", item.optInt("collection_count", -1)) != stats.stars
                    || (stats.collectedKnown
                    && item.optBoolean("is_collected", false) != stats.collected);
            try {
                copyStats(item, stats);
            } catch (Exception ignored) {
            }
            changed |= itemChanged;
        }
        if (changed) publishLocked();
        return changed;
    }

    /** 批量校验一页卡片的动态统计。同一批 10 秒内不会重复请求。 */
    public void refresh(@NonNull Context context, @NonNull String type,
                        @NonNull List<String> keys) {
        final Context app = context.getApplicationContext();
        final long now = android.os.SystemClock.elapsedRealtime();
        final List<String> requested = new ArrayList<>();
        synchronized (this) {
            syncAccountLocked(uidOf(context));
            for (String key : keys) {
                if (key == null || key.isEmpty()) continue;
                String id = k(type, key);
                Long last = refreshedAt.get(id);
                if (inFlight.containsKey(id)
                        || (last != null && now - last < REFRESH_THROTTLE_MS)) {
                    continue;
                }
                requested.add(key);
                if (requested.size() >= MAX_BATCH) break;
            }
            if (!requested.isEmpty()) {
                long token = ++nextRequestId;
                for (String key : requested) inFlight.put(k(type, key), token);
                startRefresh(app, type, requested, token);
            }
        }
    }

    private void startRefresh(@NonNull Context context, @NonNull String type,
                              @NonNull List<String> keys, long token) {
        if (TYPE_APP.equals(type)) refreshApps(context, keys, token);
        else if (TYPE_RESOURCE.equals(type)) refreshResources(context, keys, token);
        else release(keys, type, token);
    }

    private void refreshApps(@NonNull Context context, @NonNull List<String> keys, long token) {
        String packages = android.text.TextUtils.join(",", keys);
        final String baseUrl = "https://res.typheye.cn/api.php?action=appstats&packages="
                + Uri.encode(packages);
        String uid = uidOf(context);
        if (!uid.isEmpty()
                && com.typheye.wgpro.utils.tPointsUtils.cachedWebToken().isEmpty()) {
            // appstats 的 is_collected 依赖 res 的 uid+wt。先取令牌，避免首次请求把
            // “当前用户星标状态未知”当成未星标；取不到仍继续拉公开下载量/星标量。
            com.typheye.wgpro.utils.tPointsUtils.webToken(context,
                    new com.typheye.wgpro.utils.tPointsUtils.JsonCallback() {
                        @Override public void onSuccess(@NonNull JSONObject info) {
                            fetchAppStats(context, baseUrl, keys, token);
                        }

                        @Override public void onError(int status, @NonNull String message,
                                                      @Nullable JSONObject info) {
                            fetchAppStats(context, baseUrl, keys, token);
                        }
                    });
            return;
        }
        fetchAppStats(context, baseUrl, keys, token);
    }

    private void fetchAppStats(@NonNull Context context, @NonNull String baseUrl,
                               @NonNull List<String> keys, long token) {
        String url = com.typheye.wgpro.utils.tPointsUtils.withResAuth(context, baseUrl);
        new tAccUtils(context).getPublicJsonUrl(url, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                mergeServerItems(context, TYPE_APP, keys, json.optJSONArray("items"), token);
            }

            @Override public void onError(int statusCode, @NonNull String message) {
                release(keys, TYPE_APP, token);
            }
        });
    }

    private void refreshResources(@NonNull Context context, @NonNull List<String> keys, long token) {
        Map<String, String> query = new LinkedHashMap<>();
        query.put("resource_ids", android.text.TextUtils.join(",", keys));
        new tAccUtils(context).getV2JsonFresh("resource_stats2", query, false,
                new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        mergeServerItems(context, TYPE_RESOURCE, keys,
                                json.optJSONArray("items"), token);
                    }

                    @Override public void onError(int statusCode, @NonNull String message) {
                        release(keys, TYPE_RESOURCE, token);
                    }
                });
    }

    private void mergeServerItems(@NonNull Context context, @NonNull String expectedType,
                                  @NonNull List<String> requested, @Nullable JSONArray items,
                                  long requestToken) {
        synchronized (this) {
            String viewerUid = uidOf(context);
            syncAccountLocked(viewerUid);
            boolean changed = false;
            if (items != null) {
                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.optJSONObject(i);
                    if (item == null) continue;
                    String type = item.optString("type", expectedType);
                    String key = item.optString("key", item.optString("package",
                            item.optString("id", "")));
                    if (type.isEmpty()) type = expectedType;
                    String id = k(type, key);
                    if (!Long.valueOf(requestToken).equals(inFlight.get(id))) continue;
                    changed |= putLocked(type, key,
                            item.optInt("downloads", item.optInt("download_count", 0)),
                            item.optInt("stars", item.optInt("collection_count", 0)),
                            item.optBoolean("is_collected", false),
                            item.optBoolean("personalized", false) || viewerUid.isEmpty(),
                            item.optString("updated_at", ""), true);
                }
            }
            long now = android.os.SystemClock.elapsedRealtime();
            for (String key : requested) {
                String id = k(expectedType, key);
                if (Long.valueOf(requestToken).equals(inFlight.get(id))) {
                    refreshedAt.put(id, now);
                }
            }
            releaseLocked(requested, expectedType, requestToken);
            if (changed) publishLocked();
        }
    }

    public synchronized void collectionChanged(@NonNull Context context, @NonNull String type,
                                               @NonNull String key, boolean collected, int count) {
        syncAccountLocked(uidOf(context));
        String id = k(type, key);
        Stats stats = values.get(id);
        if (stats == null) stats = new Stats();
        stats.collected = collected;
        stats.collectedKnown = true;
        if (count >= 0) stats.stars = count;
        stats.changedAt = System.currentTimeMillis();
        values.put(id, stats);
        invalidateLocked(id);
        refreshedAt.put(id, android.os.SystemClock.elapsedRealtime());
        publishLocked();
    }

    public synchronized void downloadChanged(@NonNull Context context, @NonNull String type,
                                             @NonNull String key, int count) {
        syncAccountLocked(uidOf(context));
        String id = k(type, key);
        Stats stats = values.get(id);
        if (stats == null && count < 0) {
            // 尚未拿到过真实下载量时不要从 1 开始猜；等服务端批量统计给出基准值。
            invalidateLocked(id);
            refreshedAt.put(id, android.os.SystemClock.elapsedRealtime());
            return;
        }
        if (stats == null) stats = new Stats();
        if (count >= 0) stats.downloads = count;
        else stats.downloads = Math.max(0, stats.downloads + 1);
        stats.changedAt = System.currentTimeMillis();
        values.put(id, stats);
        invalidateLocked(id);
        publishLocked();
    }

    @Nullable
    public synchronized Stats find(@NonNull Context context, @NonNull String type,
                                   @NonNull String key) {
        syncAccountLocked(uidOf(context));
        return values.get(k(type, key));
    }

    private boolean putLocked(@NonNull String type, @NonNull String key, int downloads,
                              int stars, boolean collected, boolean collectedKnown,
                              @NonNull String updatedAt, boolean authoritative) {
        String id = k(type, key);
        Stats old = values.get(id);
        boolean nextCollected = collectedKnown ? collected
                : old != null && old.collectedKnown ? old.collected : false;
        boolean nextCollectedKnown = collectedKnown || old != null && old.collectedKnown;
        if (old != null && !authoritative
                && old.downloads == downloads && old.stars == stars
                && old.collected == nextCollected && old.collectedKnown == nextCollectedKnown
                && old.updatedAt.equals(updatedAt)) {
            return false;
        }
        Stats stats = old == null ? new Stats() : old;
        boolean changed = old == null || stats.downloads != downloads || stats.stars != stars
                || stats.collected != nextCollected || stats.collectedKnown != nextCollectedKnown
                || !stats.updatedAt.equals(updatedAt);
        stats.downloads = downloads;
        stats.stars = stars;
        stats.collected = nextCollected;
        stats.collectedKnown = nextCollectedKnown;
        stats.updatedAt = updatedAt;
        if (changed) stats.changedAt = System.currentTimeMillis();
        values.put(id, stats);
        return changed;
    }

    private void release(@NonNull List<String> keys, @NonNull String type, long token) {
        synchronized (this) {
            releaseLocked(keys, type, token);
        }
    }

    private void releaseLocked(@NonNull List<String> keys, @NonNull String type, long token) {
        for (String key : keys) {
            String id = k(type, key);
            if (Long.valueOf(token).equals(inFlight.get(id))) inFlight.remove(id);
        }
    }

    private void invalidateLocked(@NonNull String id) {
        inFlight.remove(id);
    }

    private void syncAccountLocked(@Nullable String uid) {
        String next = uid == null ? "" : uid;
        if (accountUid == null) {
            accountUid = next;
            return;
        }
        if (accountUid.equals(next)) return;
        // is_collected 是账号私有字段。切号/退出后必须整组丢弃，不能继续用上一个账号的状态。
        values.clear();
        refreshedAt.clear();
        inFlight.clear();
        accountUid = next;
        publishLocked();
    }

    @NonNull
    private static String uidOf(@NonNull Context context) {
        String uid = new tAccUtils(context).getUid();
        return uid == null ? "" : uid;
    }

    private void publishLocked() {
        long next = (revision.getValue() == null ? 0L : revision.getValue()) + 1L;
        main.post(() -> revision.setValue(next));
    }

    @Nullable
    private static String typeOf(@NonNull JSONObject item) {
        String explicit = item.optString("type", "").trim();
        if (TYPE_APP.equals(explicit) || "velaapp".equals(explicit)) return TYPE_APP;
        if (TYPE_RESOURCE.equals(explicit)) return TYPE_RESOURCE;
        String kind = item.optString("kind", "").trim();
        if ("app".equals(kind) || "velaapp".equals(kind)) return TYPE_APP;
        if (item.has("package") || item.has("version_name")) {
            return TYPE_APP;
        }
        if (item.has("download_count") || item.has("file_name") || item.has("title")) {
            return TYPE_RESOURCE;
        }
        if (item.has("downloads")) return TYPE_APP;
        return null;
    }

    @NonNull
    private static String keyOf(@NonNull JSONObject item, @Nullable String type) {
        if (TYPE_APP.equals(type)) {
            return item.optString("id", item.optString("package", ""));
        }
        return item.optString("id", item.optString("resource_id",
                item.optString("target_key", "")));
    }

    private static void copyStats(@NonNull JSONObject item, @NonNull Stats stats)
            throws org.json.JSONException {
        item.put("downloads", stats.downloads);
        item.put("download_count", stats.downloads);
        item.put("stars", stats.stars);
        item.put("collection_count", stats.stars);
        if (stats.collectedKnown) item.put("is_collected", stats.collected);
        if (!stats.updatedAt.isEmpty()) item.put("updated_at", stats.updatedAt);
    }

    @NonNull
    private static String k(@Nullable String type, @NonNull String key) {
        return (type == null ? "" : type) + ':' + key;
    }
}
