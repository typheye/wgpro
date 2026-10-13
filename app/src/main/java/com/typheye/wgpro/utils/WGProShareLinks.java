package com.typheye.wgpro.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONObject;

/**
 * 分享链接的唯一构造处：应用 / Vela 应用 / 资源各走各自的分享页。
 *
 * <p>网页端也按同一套规则分了页：</p>
 * <ul>
 *   <li>{@code share/app} —— 普通 Android 应用</li>
 *   <li>{@code share/velaapp} —— Vela 小程序</li>
 *   <li>{@code share/res} —— 社区资源</li>
 * </ul>
 *
 * <p>优先级：接口给的 {@code share_url}/{@code detail_url}（服务端已经按 kind 分好页）＞
 * 按 {@code kind}/{@code id} 自己拼。**绝不再回退到签名下载地址**——那是绑 uid、
 * 10 分钟就过期的直链，分享给别人只会打不开。</p>
 */
public final class WGProShareLinks {

    public static final String SHARE_BASE = "https://www.typheye.cn/share/";

    private WGProShareLinks() { }

    /** 应用条目（应用详情、应用卡长按菜单）的分享链接。 */
    @Nullable
    public static String appUrl(@Nullable JSONObject app) {
        if (app == null) return null;
        String direct = firstNonEmpty(app.optString("share_url", ""), app.optString("detail_url", ""));
        if (direct != null) return direct;
        String id = firstNonEmpty(app.optString("id", ""), app.optString("package", ""),
                app.optString("appid", ""));
        if (id == null) return null;
        boolean vela = "velaapp".equals(app.optString("kind", ""));
        return SHARE_BASE + (vela ? "velaapp" : "app") + "/?uri=" + android.net.Uri.encode(id);
    }

    /** 资源条目的分享链接。 */
    @Nullable
    public static String resourceUrl(@Nullable JSONObject resource) {
        if (resource == null) return null;
        String direct = firstNonEmpty(resource.optString("share_url", ""),
                resource.optString("detail_url", ""));
        if (direct != null) return direct;
        String id = firstNonEmpty(resource.optString("id", ""), resource.optString("resource_id", ""));
        if (id == null) return null;
        return SHARE_BASE + "res/?uri=" + android.net.Uri.encode(id);
    }

    /** 列表/历史条目：按 kind 自动判断是应用还是资源。 */
    @Nullable
    public static String itemUrl(@Nullable JSONObject item, boolean resource) {
        return resource ? resourceUrl(item) : appUrl(item);
    }

    /** 分享文本：`名称　链接`（链接缺失时只给名称）。 */
    @NonNull
    public static String shareText(@NonNull String name, @Nullable String url) {
        return url == null || url.isEmpty() ? name : name + "　" + url;
    }

    @Nullable
    private static String firstNonEmpty(String... values) {
        for (String value : values) {
            if (value != null && !value.trim().isEmpty()) return value.trim();
        }
        return null;
    }
}
