package com.typheye.wgpro.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONObject;

/**
 * 积分接口客户端（pay.typheye.cn/api/v3/points.php）。
 *
 * <p>与订单接口 {@code order2.php} 同一套鉴权：{@code X-Typheye-Session-Id} + Bearer token，
 * 复用 {@link tAccUtils} 里已登录的 V2 会话。</p>
 *
 * <p>网络策略：超时短（连接 6s / 读 10s）+ 失败自动重试 1 次——到服务器的链路会偶发超时，
 * 重试能显著降低失败率；仍失败则回调错误，由界面决定"稍后再试"。</p>
 */
public final class tPointsUtils {

    private static final String BASE_URL = "https://pay.typheye.cn/api/v3/points.php";
    private static final int MAX_RETRY = 1;

    private static volatile OkHttpClient client;
    /** 回调统一回到主线程：调用方多是 UI 代码，避免各自再 post 一遍。 */
    private static final Handler MAIN = new Handler(Looper.getMainLooper());

    private tPointsUtils() { }

    private static void postMain(@NonNull Runnable action) {
        if (Looper.myLooper() == Looper.getMainLooper()) action.run();
        else MAIN.post(action);
    }

    private static OkHttpClient client() {
        OkHttpClient cached = client;
        if (cached != null) return cached;
        synchronized (tPointsUtils.class) {
            if (client == null) {
                client = new OkHttpClient.Builder()
                        .connectTimeout(6, TimeUnit.SECONDS)
                        .readTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(10, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
                        .build();
            }
            return client;
        }
    }

    /** 回调：成功带 info 对象；失败带人类可读原因与 HTTP 状态。 */
    public interface JsonCallback {
        void onSuccess(@NonNull JSONObject info);

        /** @param status HTTP 状态（0 = 网络层失败） */
        void onError(int status, @NonNull String message, @Nullable JSONObject info);
    }

    /** 业务结果包一层：区分"成功"与"可预期的失败"（如积分不足）。 */
    public static final class Result {
        public final boolean ok;
        public final int status;
        public final String message;
        @Nullable public final JSONObject info;

        Result(boolean ok, int status, String message, @Nullable JSONObject info) {
            this.ok = ok;
            this.status = status;
            this.message = message;
            this.info = info;
        }

        public boolean isInsufficient() {
            return status == 402;
        }

        public boolean isUnauthorized() {
            return status == 401;
        }

        public int optInt(String key, int fallback) {
            return info == null ? fallback : info.optInt(key, fallback);
        }

        public boolean optBoolean(String key, boolean fallback) {
            return info == null ? fallback : info.optBoolean(key, fallback);
        }
    }

    // ------------------------------------------------------------------ 动作

    public static void balance(@NonNull Context context, @NonNull JsonCallback callback) {
        getAction(context, "balance", callback);
    }

    public static void quote(@NonNull Context context, @NonNull String targetType,
                             @NonNull String targetKey, @NonNull JsonCallback callback) {
        HttpUrl.Builder url = baseBuilder("quote");
        if (url == null) { callback.onError(0, "未登录", null); return; }
        url.addQueryParameter("target_type", targetType);
        url.addQueryParameter("target_key", targetKey);
        get(context, url, callback);
    }

    /** 两步交易①：建交易 + 下发验证码（积分支付）。 */
    public static void verifySend(@NonNull Context context, @NonNull String targetType,
                                  @NonNull String targetKey, @NonNull JsonCallback callback) {
        verifySend(context, targetType, targetKey, "balance", "", callback);
    }

    /**
     * 两步交易①：建交易 + 下发验证码。
     *
     * @param payMethod  "balance"=积分支付；"redeem"=兑换代码支付
     * @param redeemCode payMethod=redeem 时必填
     */
    public static void verifySend(@NonNull Context context, @NonNull String targetType,
                                  @NonNull String targetKey, @NonNull String payMethod,
                                  @Nullable String redeemCode, @NonNull JsonCallback callback) {
        HttpUrl.Builder url = baseBuilder("verify.send");
        if (url == null) { callback.onError(0, "未登录", null); return; }
        url.addQueryParameter("target_type", targetType);
        url.addQueryParameter("target_key", targetKey);
        url.addQueryParameter("pay_method", payMethod);
        if (redeemCode != null && !redeemCode.isEmpty()) {
            url.addQueryParameter("redeem_code", redeemCode);
        }
        get(context, url, callback);
    }

    /** 两步交易②：凭验证码完成扣费。 */
    public static void purchase(@NonNull Context context, @NonNull String txnNo,
                                @NonNull String code, @Nullable String idempotencyKey,
                                @NonNull JsonCallback callback) {
        HttpUrl.Builder url = baseBuilder("purchase");
        if (url == null) { callback.onError(0, "未登录", null); return; }
        url.addQueryParameter("txn_no", txnNo);
        url.addQueryParameter("code", code);
        if (idempotencyKey != null && !idempotencyKey.isEmpty()) {
            url.addQueryParameter("idempotency_key", idempotencyKey);
        }
        get(context, url, callback);
    }

    /** 网页单点令牌（积分明细 / 商城 / 福利 三个网页用）。 */
    public static void webToken(@NonNull Context context, @NonNull JsonCallback callback) {
        getAction(context, "web_token", callback);
    }

    /** 拼网页地址：https://pay.typheye.cn/points/<path>/?uid=..&wt=.. */
    @NonNull
    public static String webUrl(@NonNull String path, @NonNull String uid, @Nullable String wt) {
        StringBuilder url = new StringBuilder("https://pay.typheye.cn/points/")
                .append(path).append("/?uid=").append(Uri.encode(uid));
        if (wt != null && !wt.isEmpty()) {
            url.append("&wt=").append(Uri.encode(wt));
        }
        return url.toString();
    }

    public static void redeem(@NonNull Context context, @NonNull String code,
                              @NonNull JsonCallback callback) {
        HttpUrl.Builder url = baseBuilder("redeem");
        if (url == null) { callback.onError(0, "未登录", null); return; }
        url.addQueryParameter("code", code);
        get(context, url, callback);
    }

    public static void ledger(@NonNull Context context, int page, int size,
                              @NonNull JsonCallback callback) {
        HttpUrl.Builder url = baseBuilder("ledger");
        if (url == null) { callback.onError(0, "未登录", null); return; }
        url.addQueryParameter("page", String.valueOf(page));
        url.addQueryParameter("size", String.valueOf(size));
        get(context, url, callback);
    }

    /**
     * 同步版（后台线程用）：报价。失败返回 null。
     * 供下载/获取流程做"要不要弹窗"的判断。
     */
    @Nullable
    public static Result quoteBlocking(@NonNull Context context, @NonNull String targetType,
                                       @NonNull String targetKey) {
        return requestBlocking(context, buildUrl("quote", targetType, targetKey, null));
    }

    /** 同步版：购买。 */
    @Nullable
    public static Result purchaseBlocking(@NonNull Context context, @NonNull String targetType,
                                          @NonNull String targetKey, @Nullable String idem) {
        HttpUrl url = buildUrl("purchase", targetType, targetKey, idem);
        return requestBlocking(context, url);
    }

    /** 同步版：积分。 */
    @Nullable
    public static Result balanceBlocking(@NonNull Context context) {
        HttpUrl.Builder builder = baseBuilder("balance");
        return builder == null ? null : requestBlocking(context, builder.build());
    }

    // ------------------------------------------------------------------ 内部

    private static void getAction(@NonNull Context context, @NonNull String action,
                                  @NonNull JsonCallback callback) {
        HttpUrl.Builder builder = baseBuilder(action);
        if (builder == null) { callback.onError(0, "未登录", null); return; }
        get(context, builder, callback);
    }

    private static void get(@NonNull Context context, @NonNull HttpUrl.Builder builder,
                            @NonNull JsonCallback callback) {
        send(context, builder.build(), 0, callback);
    }

    private static void send(@NonNull Context context, @NonNull HttpUrl url, int attempt,
                             @NonNull JsonCallback callback) {
        tAccUtils acc = new tAccUtils(context.getApplicationContext());
        HttpUrl signed = url.newBuilder()
                .addQueryParameter("uid", acc.getUid()).build();
        Request request = new Request.Builder()
                .url(signed)
                .header("Authorization", "Bearer " + acc.peekSessionToken())
                .header("X-Typheye-Session-Id", acc.peekSessionId())
                .header("X-Typheye-Platform", "android")
                .header("Accept", "application/json")
                .get()
                .build();
        client().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (attempt < MAX_RETRY) {
                    send(context, url, attempt + 1, callback);
                } else {
                    postMain(() -> callback.onError(0, "网络不可用，请稍后再试", null));
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                String body;
                try {
                    body = response.body() == null ? "" : response.body().string();
                } catch (IOException e) {
                    body = "";
                }
                int status = response.code();
                response.close();
                // 5xx / 429 重试一次
                if ((status >= 500 || status == 429) && attempt < MAX_RETRY) {
                    send(context, url, attempt + 1, callback);
                    return;
                }
                JSONObject json = null;
                try {
                    json = new JSONObject(body);
                } catch (Exception ignored) { }
                final JSONObject parsed = json;
                if (status == 200 && parsed != null && parsed.optInt("code", 0) == 200) {
                    final JSONObject info = parsed.optJSONObject("info") != null
                            ? parsed.optJSONObject("info") : new JSONObject();
                    postMain(() -> callback.onSuccess(info));
                } else {
                    final String message = parsed == null ? "服务返回异常"
                            : parsed.optString("msg", "服务返回异常");
                    final int code = status;
                    postMain(() -> callback.onError(code, message,
                            parsed == null ? null : parsed.optJSONObject("info")));
                }
            }
        });
    }

    @Nullable
    private static Result requestBlocking(@NonNull Context context, @Nullable HttpUrl url) {
        if (url == null) return null;
        tAccUtils acc = new tAccUtils(context.getApplicationContext());
        HttpUrl signed = url.newBuilder()
                .addQueryParameter("uid", acc.getUid()).build();
        Request request = new Request.Builder()
                .url(signed)
                .header("Authorization", "Bearer " + acc.peekSessionToken())
                .header("X-Typheye-Session-Id", acc.peekSessionId())
                .header("X-Typheye-Platform", "android")
                .header("Accept", "application/json")
                .get()
                .build();
        for (int attempt = 0; attempt <= MAX_RETRY; attempt++) {
            try (Response response = client().newCall(request).execute()) {
                String body = response.body() == null ? "" : response.body().string();
                int status = response.code();
                if ((status >= 500 || status == 429) && attempt < MAX_RETRY) continue;
                JSONObject json = null;
                try {
                    json = new JSONObject(body);
                } catch (Exception ignored) { }
                JSONObject info = json == null ? null : json.optJSONObject("info");
                if (status == 200 && json != null && json.optInt("code", 0) == 200) {
                    return new Result(true, 200, json.optString("msg", "ok"), info);
                }
                String message = json == null ? "服务返回异常" : json.optString("msg", "服务返回异常");
                return new Result(false, status, message, info);
            } catch (IOException e) {
                if (attempt >= MAX_RETRY) {
                    return new Result(false, 0, "网络不可用，请稍后再试", null);
                }
            }
        }
        return new Result(false, 0, "网络不可用，请稍后再试", null);
    }

    @Nullable
    private static HttpUrl.Builder baseBuilder(@NonNull String action) {
        HttpUrl base = HttpUrl.parse(BASE_URL);
        if (base == null) return null;
        return base.newBuilder().addQueryParameter("action", action);
    }

    @Nullable
    private static HttpUrl buildUrl(@NonNull String action, @NonNull String targetType,
                                    @NonNull String targetKey, @Nullable String idem) {
        HttpUrl.Builder url = baseBuilder(action);
        if (url == null) return null;
        url.addQueryParameter("target_type", targetType);
        url.addQueryParameter("target_key", targetKey);
        if (idem != null && !idem.isEmpty()) {
            url.addQueryParameter("idempotency_key", idem);
        }
        return url.build();
    }

    // ------------------------------------------------------------- 本地缓存

    private static final String PREFS = "wgpro_points_cache";

    /** 价格缓存的保鲜期：超过就当作"不知道价格"，避免运营刚设价却按旧的免费缓存直接放行。 */
    private static final long PRICE_CACHE_TTL_MS = 10 * 60 * 1000L;

    /** 记一次成功的报价（带时间戳），仅用于极短时间的离线兜底与按钮先渲染。 */
    public static void cachePrice(@NonNull Context context, @NonNull String targetType,
                                  @NonNull String targetKey, int price, boolean owned) {
        SharedPreferences prefs = context.getApplicationContext()
                .getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        prefs.edit()
                .putInt("price:" + targetType + ":" + targetKey, price)
                .putBoolean("owned:" + targetType + ":" + targetKey, owned)
                .putLong("at:" + targetType + ":" + targetKey, System.currentTimeMillis())
                .apply();
    }

    /** 上次已知价格；没缓存过或**已过期**返回 -1。 */
    public static int cachedPrice(@NonNull Context context, @NonNull String targetType,
                                 @NonNull String targetKey) {
        SharedPreferences prefs = context.getApplicationContext()
                .getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        long at = prefs.getLong("at:" + targetType + ":" + targetKey, 0L);
        if (at <= 0L || System.currentTimeMillis() - at > PRICE_CACHE_TTL_MS) return -1;
        return prefs.getInt("price:" + targetType + ":" + targetKey, -1);
    }

    public static boolean cachedOwned(@NonNull Context context, @NonNull String targetType,
                                      @NonNull String targetKey) {
        if (cachedPrice(context, targetType, targetKey) < 0) return false;   // 缓存过期则不算已拥有
        SharedPreferences prefs = context.getApplicationContext()
                .getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        return prefs.getBoolean("owned:" + targetType + ":" + targetKey, false);
    }
}
