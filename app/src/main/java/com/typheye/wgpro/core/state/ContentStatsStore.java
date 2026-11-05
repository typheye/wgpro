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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        public String updatedAt = "";
        public long changedAt;
    }

    private final Map<String, Stats> values = new HashMap<>();
    private final Map<String, Long> refreshedAt = new HashMap<>();
    private final Set<String> inFlight = new LinkedHashSet<>();
    private final MutableLiveData<Long> revision = new MutableLiveData<>(0L);
    private final Handler main = new Handler(Looper.getMainLooper());

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
    public synchronized void seed(@NonNull JSONArray items) {
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
                    item.optBoolean("is_collected", item.optBoolean("is_collected", false)),
                    item.optString("updated_at", item.optString("updatedAt", "")), false);
        }
        if (changed) publishLocked();
    }

    /** 把服务端统计结果应用到 JSONArray；返回是否至少有一项变化。 */
    public synchronized boolean apply(@NonNull JSONArray items) {
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
                    || item.optBoolean("is_collected", false) != stats.collected;
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
        final long now = System.currentTimeMillis();
        final List<String> requested = new ArrayList<>();
        synchronized (this) {
            for (String key : keys) {
                if (key == null || key.isEmpty()) continue;
                String id = k(type, key);
                Long last = refreshedAt.get(id);
                if (inFlight.contains(id) || (last != null && now - last < REFRESH_THROTTLE_MS)) {
                    continue;
                }
                requested.add(key);
                inFlight.add(id);
                if (requested.size() >= MAX_BATCH) break;
            }
        }
        if (requested.isEmpty()) return;
        if (TYPE_APP.equals(type)) refreshApps(app, requested);
        else if (TYPE_RESOURCE.equals(type)) refreshResources(app, requested);
        else release(requested, type);
    }

    private void refreshApps(@NonNull Context context, @NonNull List<String> keys) {
        String packages = android.text.TextUtils.join(",", keys);
        String url = com.typheye.wgpro.utils.tPointsUtils.withResAuth(context,
                "https://res.typheye.cn/api.php?action=appstats&packages="
                        + Uri.encode(packages));
        new tAccUtils(context).getPublicJsonUrl(url, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                mergeServerItems(context, TYPE_APP, keys, json.optJSONArray("items"));
            }

            @Override public void onError(int statusCode, @NonNull String message) {
                release(keys, TYPE_APP);
            }
        });
    }

    private void refreshResources(@NonNull Context context, @NonNull List<String> keys) {
        Map<String, String> query = new LinkedHashMap<>();
        query.put("resource_ids", android.text.TextUtils.join(",", keys));
        new tAccUtils(context).getV2JsonFresh("resource_stats2", query, false,
                new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        mergeServerItems(context, TYPE_RESOURCE, keys,
                                json.optJSONArray("items"));
                    }

                    @Override public void onError(int statusCode, @NonNull String message) {
                        release(keys, TYPE_RESOURCE);
                    }
                });
    }

    private void mergeServerItems(@NonNull Context context, @NonNull String expectedType,
                                  @NonNull List<String> requested, @Nullable JSONArray items) {
        synchronized (this) {
            boolean changed = false;
            if (items != null) {
                for (int i = 0; i < items.length(); i++) {
                    JSONObject item = items.optJSONObject(i);
                    if (item == null) continue;
                    String type = item.optString("type", expectedType);
                    String key = item.optString("key", item.optString("package",
                            item.optString("id", "")));
                    if (type.isEmpty()) type = expectedType;
                    changed |= putLocked(type, key,
                            item.optInt("downloads", item.optInt("download_count", 0)),
                            item.optInt("stars", item.optInt("collection_count", 0)),
                            item.optBoolean("is_collected", false),
                            item.optString("updated_at", ""), true);
                }
            }
            long now = System.currentTimeMillis();
            for (String key : requested) refreshedAt.put(k(expectedType, key), now);
            releaseLocked(requested, expectedType);
            if (changed) publishLocked();
        }
    }

    public synchronized void collectionChanged(@NonNull String type, @NonNull String key,
                                               boolean collected, int count) {
        Stats stats = values.get(k(type, key));
        if (stats == null) stats = new Stats();
        stats.collected = collected;
        if (count >= 0) stats.stars = count;
        stats.changedAt = System.currentTimeMillis();
        values.put(k(type, key), stats);
        refreshedAt.put(k(type, key), stats.changedAt);
        publishLocked();
    }

    public synchronized void downloadChanged(@NonNull String type, @NonNull String key,
                                             int count) {
        Stats stats = values.get(k(type, key));
        if (stats == null) stats = new Stats();
        if (count >= 0) stats.downloads = count;
        else stats.downloads = Math.max(0, stats.downloads + 1);
        stats.changedAt = System.currentTimeMillis();
        values.put(k(type, key), stats);
        publishLocked();
    }

    @Nullable
    public synchronized Stats find(@NonNull String type, @NonNull String key) {
        return values.get(k(type, key));
    }

    private boolean mergeStatsFromItem(@NonNull JSONObject item, boolean authoritative) {
        String type = typeOf(item);
        String key = keyOf(item, type);
        if (type == null || key.isEmpty()) return false;
        return putLocked(type, key, item.optInt("downloads", item.optInt("download_count", 0)),
                item.optInt("stars", item.optInt("collection_count", 0)),
                item.optBoolean("is_collected", false),
                item.optString("updated_at", item.optString("updatedAt", "")), authoritative);
    }

    private boolean putLocked(@NonNull String type, @NonNull String key, int downloads,
                              int stars, boolean collected, @NonNull String updatedAt,
                              boolean authoritative) {
        String id = k(type, key);
        Stats old = values.get(id);
        if (old != null && !authoritative
                && old.downloads == downloads && old.stars == stars
                && old.collected == collected && old.updatedAt.equals(updatedAt)) {
            return false;
        }
        Stats stats = old == null ? new Stats() : old;
        boolean changed = old == null || stats.downloads != downloads || stats.stars != stars
                || stats.collected != collected || !stats.updatedAt.equals(updatedAt);
        stats.downloads = downloads;
        stats.stars = stars;
        stats.collected = collected;
        stats.updatedAt = updatedAt;
        if (changed) stats.changedAt = System.currentTimeMillis();
        values.put(id, stats);
        return changed;
    }

    private void release(@NonNull List<String> keys, @NonNull String type) {
        synchronized (this) {
            releaseLocked(keys, type);
        }
    }

    private void releaseLocked(@NonNull List<String> keys, @NonNull String type) {
        for (String key : keys) inFlight.remove(k(type, key));
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
        if (item.has("downloads") || item.has("package") || item.has("version_name")) {
            return TYPE_APP;
        }
        if (item.has("download_count") || item.has("file_name") || item.has("title")) {
            return TYPE_RESOURCE;
        }
        return null;
    }

    @NonNull
    private static String keyOf(@NonNull JSONObject item, @Nullable String type) {
        if (TYPE_APP.equals(type)) {
            return item.optString("package", item.optString("id", ""));
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
        item.put("is_collected", stats.collected);
        if (!stats.updatedAt.isEmpty()) item.put("updated_at", stats.updatedAt);
    }

    @NonNull
    private static String k(@Nullable String type, @NonNull String key) {
        return (type == null ? "" : type) + ':' + key;
    }
}
