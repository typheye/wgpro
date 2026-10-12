package com.typheye.wgpro.ui.widget;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.typheye.wgpro.utils.tPointsUtils;

import org.json.JSONObject;

/**
 * 积分数据源（pay.typheye.cn）。
 *
 * <p><b>显示规则（三态）</b>：</p>
 * <ul>
 *     <li>{@code -}：还没加载出来（首次进入、请求进行中）——不显示假数据；</li>
 *     <li>数字：接口回来后有数据；</li>
 *     <li>{@code 0}：接口回来但没数据、或请求失败。</li>
 * </ul>
 *
 * <p>名词：{@link #balance} = 可用积分（兑换码/管理员发放得来），{@link #gift} = 赠送（新人赠送等，
 * 消费时**优先扣赠送**）。两者都由服务端计算，客户端只做展示缓存。</p>
 */
public final class Points {
    /** 未加载：界面显示 {@code -}。 */
    public static final int LOADING = -1;

    /** 可用积分。 */
    public static volatile int balance = LOADING;
    /** 赠送积分。 */
    public static volatile int gift = LOADING;
    /** 是否已经拿到过接口结果（成功或失败都算）。false → 显示 {@code -}。 */
    public static volatile boolean loaded = false;
    /** 累计合计（可用积分 + 赠送积分），由服务端返回。 */
    public static volatile int total = LOADING;
    /** 最近一次刷新是否成功（失败时不要把旧数字当成最新的）。 */
    public static volatile boolean lastRefreshOk = false;
    /** 网页单点令牌（积分明细 / 商城 / 福利 三个网页用），拿不到就为空。 */
    public static volatile String webToken = "";

    private static volatile long lastRefreshAt = 0L;
    private static final long REFRESH_INTERVAL_MS = 30_000L;

    /** 本地缓存：进页面先用缓存渲染，网络回来再自动刷新（避免闪一下占位符）。 */
    private static final String PREFS = "wgpro_points_cache";
    private static final String KEY_BALANCE = "balance";
    private static final String KEY_GIFT = "gift";
    private static volatile boolean cacheLoaded = false;

    private Points() { }

    /** 加载中的占位符：与「动态 / 关注 / 粉丝」卡一致，用两个短横。 */
    public static final String PLACEHOLDER = "--";

    /**
     * 界面展示：加载中显示 {@code --}（对齐「动态 / 关注 / 粉丝」卡的占位样式）；
     * 加载完成后无数据/失败显示 {@code 0}。
     */
    public static String display(int value) {
        if (!loaded) return PLACEHOLDER;
        return String.valueOf(Math.max(0, value));
    }

    /** 首次使用前把上次的积分读进内存（同步、极快）。 */
    public static void warm(@NonNull Context context) {
        if (cacheLoaded) return;
        synchronized (Points.class) {
            if (cacheLoaded) return;
            SharedPreferences prefs = context.getApplicationContext()
                    .getSharedPreferences(PREFS, Context.MODE_PRIVATE);
            int cachedBalance = prefs.getInt(KEY_BALANCE, LOADING);
            int cachedGift = prefs.getInt(KEY_GIFT, LOADING);
            if (cachedBalance >= 0 && cachedGift >= 0 && !loaded) {
                balance = cachedBalance;
                gift = cachedGift;
                total = cachedBalance + cachedGift;
                loaded = true;
            }
            cacheLoaded = true;
        }
    }

    private static void persist(@NonNull Context context) {
        context.getApplicationContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit()
                .putInt(KEY_BALANCE, Math.max(0, balance))
                .putInt(KEY_GIFT, Math.max(0, gift))
                .apply();
    }

    /** 手上有没有可用的数字（未加载完成时界面显示 {@code -}）。 */
    public static boolean hasData() {
        return loaded && balance >= 0 && gift >= 0;
    }

    /** 接口回调写入（服务端是唯一事实来源）。 */
    public static void apply(int balanceValue, int giftValue) {
        balance = Math.max(0, balanceValue);
        gift = Math.max(0, giftValue);
        total = balance + gift;
        loaded = true;
        cacheLoaded = true;
        lastRefreshOk = true;
        lastRefreshAt = System.currentTimeMillis();
    }

    /**
     * 接口失败：**保留已有/缓存数据**继续显示，只标记这次刷新失败；
     * 只有在完全没有数据时才回落到 0。
     */
    public static void applyFailure() {
        if (!hasData()) {
            balance = 0;
            gift = 0;
            total = 0;
            loaded = true;
        }
        lastRefreshOk = false;
        lastRefreshAt = System.currentTimeMillis();
    }

    public interface Callback {
        void onDone(boolean ok);
    }

    /**
     * 刷新积分：成功写入 {@link #balance}/{@link #gift} 并回调 true；
     * 失败按 {@link #applyFailure()} 处理并回调 false。
     */
    public static void refresh(@NonNull Context context, @Nullable Callback callback) {
        refresh(context, false, callback);
    }

    /** @param force true = 忽略 30s 节流，强制拉一次 */
    public static void refresh(@NonNull Context context, boolean force, @Nullable Callback callback) {
        warm(context);
        if (!force && loaded && System.currentTimeMillis() - lastRefreshAt < REFRESH_INTERVAL_MS) {
            if (callback != null) callback.onDone(lastRefreshOk);
            return;
        }
        tPointsUtils.balance(context, new tPointsUtils.JsonCallback() {
            @Override
            public void onSuccess(@NonNull JSONObject info) {
                apply(info.optInt("balance", 0), info.optInt("gift", 0));
                persist(context);
                if (callback != null) callback.onDone(true);
            }

            @Override
            public void onError(int status, @NonNull String message, @Nullable JSONObject info) {
                if (status == 401) {
                    // 未登录：不显示假数字，回退到"未加载"态
                    reset();
                } else {
                    applyFailure();
                }
                if (callback != null) callback.onDone(false);
            }
        });
    }

    /** 刷新网页单点令牌（打开积分网页前调用一次即可）。 */
    public static void refreshWebToken(@NonNull Context context) {
        tPointsUtils.webToken(context, new tPointsUtils.JsonCallback() {
            @Override
            public void onSuccess(@NonNull JSONObject info) {
                webToken = info.optString("wt", "");
            }

            @Override
            public void onError(int status, @NonNull String message, @Nullable JSONObject info) {
                webToken = "";
            }
        });
    }

    /** 退出登录时调用。 */
    public static void reset() {
        webToken = "";
        cacheLoaded = false;
        balance = LOADING;
        gift = LOADING;
        total = LOADING;
        loaded = false;
        lastRefreshOk = false;
        lastRefreshAt = 0L;
    }
}
