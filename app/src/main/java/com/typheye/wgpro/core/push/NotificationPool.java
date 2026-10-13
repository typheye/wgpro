package com.typheye.wgpro.core.push;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.typheye.wgpro.utils.tAccUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * 通知池：**所有**本地通知的唯一投放口，专门解决"同一件事反复弹 / 一次弹一堆"的问题。
 *
 * <p>以前通知由轮询、WebSocket 实时事件、首页刷新等多条路径各自判断后直接
 * {@code NotificationManager.notify()}，每条路径都靠一个"最后一条 id"水位线做去重，
 * 于是出现：</p>
 * <ul>
 *   <li>水位线里的那条 id 不在当前页里（被删除/被过滤/换账号）→ 整页 50 条**全部重发**；</li>
 *   <li>多条路径并发判断，同一件事各弹一次；</li>
 *   <li>App 每次打开都把历史积压重新弹一遍。</li>
 * </ul>
 *
 * <p>通知池用四道闸统一兜住：</p>
 * <ol>
 *   <li><b>精确去重</b>：{@code key}（如 {@code like:<动态id>}）持久化记录，同一个 key 永不再弹（7 天过期）；</li>
 *   <li><b>并发去重</b>：同一时刻只有一个线程能通过判定，内存 in-flight 集合兜住并发投递；</li>
 *   <li><b>同类合并</b>：{@code coalesceKey}（如 {@code like:<动态id>:<点赞人>}）在
 *       {@link #COALESCE_WINDOW_MS} 内只提醒一次，点赞/关注刷屏不会连环响；</li>
 *   <li><b>陈旧保护</b>：事件时间早于 {@link #STALE_MS} 的不弹（打开 App 不会把历史积压刷出来）。</li>
 * </ol>
 */
public final class NotificationPool {

    private static final String PREFS = "notification_pool";
    private static final String KEY_SEEN = "seen";
    private static final String KEY_POSTED_AT = "posted_at";

    /** 同一个 key 只提醒一次的保留时长（7 天）。 */
    private static final long SEEN_TTL_MS = 7L * 24L * 60L * 60L * 1000L;
    /** 同类事件合并窗口（点赞/关注抖屏）。 */
    private static final long COALESCE_WINDOW_MS = 60_000L;
    /** 事件比现在早太多就不弹了（避免"每次打开都出现"的历史积压）。 */
    private static final long STALE_MS = 30L * 60L * 1000L;
    /** 去重表上限：超出按时间淘汰最早的。 */
    private static final int MAX_SEEN = 800;

    private static final Object LOCK = new Object();
    /** 进程内同类事件上次投放时间（coalesceKey -> 时间戳）。 */
    private static final Map<String, Long> COALESCED = new LinkedHashMap<>();

    private NotificationPool() { }

    /** 真正的投放动作；{@code alert=false} 表示"合并窗口内"——照常进通知栏，但不要再响一次。 */
    public interface Deliver {
        void run(boolean alert);
    }

    /**
     * 尝试投放一条通知。
     *
     * @param key          唯一键（同一条消息/同一个事件在同一 key 下只弹一次）
     * @param coalesceKey  合并键（可为空；同类刷屏在 60s 内只"静默"投放一次）
     * @param eventTimeMs  事件发生时间（毫秒；0 表示未知，按"现在"处理）
     * @param deliver      真正投放动作（合并窗口内会带 {@code alert=false} 调用）
     * @return true 表示这次真的投放了（可能是静默投放）
     */
    public static boolean offer(@NonNull Context context, @NonNull String key,
                                @Nullable String coalesceKey, long eventTimeMs,
                                @NonNull Deliver deliver) {
        Context app = context.getApplicationContext();
        String uid = uidOf(app);
        long now = System.currentTimeMillis();

        // 闸门 4：陈旧事件不弹
        if (eventTimeMs > 0L && now - eventTimeMs > STALE_MS) return false;

        boolean alert = true;
        synchronized (LOCK) {
            SharedPreferences prefs = app.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
            String suffix = "_" + uid;
            Map<String, Long> seen = parse(prefs.getString(KEY_SEEN + suffix, ""));

            // 闸门 1：精确去重
            Long previous = seen.get(key);
            if (previous != null && now - previous < SEEN_TTL_MS) return false;

            // 闸门 3：同类合并（窗口内只静默投放：内容照进通知栏，不再重复响铃/震动）
            if (coalesceKey != null && !coalesceKey.isEmpty()) {
                Long last = COALESCED.get(coalesceKey);
                if (last != null && now - last < COALESCE_WINDOW_MS) {
                    alert = false;
                } else {
                    COALESCED.put(coalesceKey, now);
                }
            }

            // 闸门 2：先记账再投放（同一时刻只有一个线程能走到这里）
            seen.put(key, now);
            prune(seen, now);
            COALESCED.entrySet().removeIf(entry -> now - entry.getValue() > COALESCE_WINDOW_MS);
            prefs.edit()
                    .putString(KEY_SEEN + suffix, serialize(seen))
                    .putLong(KEY_POSTED_AT + suffix, now)
                    .apply();
        }
        deliver.run(alert);
        return true;
    }

    /** 这个 key 是否已经提醒过（用于需要提前判断的场景）。 */
    public static boolean hasPosted(@NonNull Context context, @NonNull String key) {
        Context app = context.getApplicationContext();
        String uid = uidOf(app);
        boolean alert = true;
        synchronized (LOCK) {
            SharedPreferences prefs = app.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
            Map<String, Long> seen = parse(prefs.getString(KEY_SEEN + "_" + uid, ""));
            Long previous = seen.get(key);
            return previous != null && System.currentTimeMillis() - previous < SEEN_TTL_MS;
        }
    }

    /** 退出登录/切换账号：清掉内存合并状态（持久化的按账号区分，不用动）。 */
    public static void reset() {
        synchronized (LOCK) {
            COALESCED.clear();
        }
    }

    /** 把服务端返回的时间字符串转成毫秒（解析失败返回 0）。 */
    public static long parseTime(@Nullable String value) {
        if (value == null || value.isEmpty()) return 0L;
        String[] patterns = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd HH:mm"};
        for (String pattern : patterns) {
            try {
                SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.ROOT);
                format.setTimeZone(TimeZone.getDefault());
                java.util.Date date = format.parse(value);
                if (date != null) return date.getTime();
            } catch (Exception ignored) {
            }
        }
        return 0L;
    }

    private static String uidOf(Context context) {
        try {
            String uid = new tAccUtils(context).getUid();
            return uid == null || uid.isEmpty() ? "guest" : uid;
        } catch (Exception e) {
            return "guest";
        }
    }

    private static Map<String, Long> parse(@Nullable String raw) {
        Map<String, Long> map = new LinkedHashMap<>();
        if (raw == null || raw.isEmpty()) return map;
        for (String entry : raw.split("\n")) {
            int split = entry.lastIndexOf('|');
            if (split <= 0) continue;
            try {
                map.put(entry.substring(0, split), Long.parseLong(entry.substring(split + 1)));
            } catch (NumberFormatException ignored) {
            }
        }
        return map;
    }

    private static String serialize(Map<String, Long> seen) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Long> entry : seen.entrySet()) {
            if (builder.length() > 0) builder.append('\n');
            builder.append(entry.getKey()).append('|').append(entry.getValue());
        }
        return builder.toString();
    }

    private static void prune(Map<String, Long> seen, long now) {
        List<String> expired = new ArrayList<>();
        for (Map.Entry<String, Long> entry : seen.entrySet()) {
            if (now - entry.getValue() > SEEN_TTL_MS) expired.add(entry.getKey());
        }
        for (String key : expired) seen.remove(key);
        while (seen.size() > MAX_SEEN) {
            String oldest = null;
            long oldestAt = Long.MAX_VALUE;
            for (Map.Entry<String, Long> entry : seen.entrySet()) {
                if (entry.getValue() < oldestAt) {
                    oldestAt = entry.getValue();
                    oldest = entry.getKey();
                }
            }
            if (oldest == null) break;
            seen.remove(oldest);
        }
    }
}
