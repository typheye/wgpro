package com.typheye.wgpro.ui.function.community;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.typheye.wgpro.ui.function.WebActivity;

/**
 * 社区网页入口。
 *
 * <p>发布动态、发布资源、举报等原本在客户端内实现的页面已下线，统一改为
 * 在 Typheye 服务器上用网页完成，客户端只负责用内置浏览器打开对应地址。
 * 网页依赖内置浏览器兑换出来的 {@code TypheyeWebSession} Cookie 完成登录，
 * 因此这里只传业务参数，不传任何凭据。</p>
 */
public final class CommunityWeb {
    /** 站点与参数约定必须与服务器上的 {@code site/community/*}、{@code site/report/create/} 保持一致。 */
    private static final String HOST = "https://service.typheye.cn";
    private static final String DYNAMIC_PATH = "/site/community/dynamic/";
    private static final String RESOURCE_PATH = "/site/community/resource/";
    private static final String CREATOR_PATH = "/site/creator/";
    private static final String IDENTITY_PATH = "/site/identity/";
    private static final String REPORT_PATH = "/site/report/create/";

    private CommunityWeb() {
    }

    /** 打开“发布动态”网页。 */
    public static void openDynamic(Context context) {
        openUrl(context, HOST + DYNAMIC_PATH);
    }

    /** 打开“发布资源”网页。 */
    public static void openResource(Context context) {
        openUrl(context, HOST + RESOURCE_PATH);
    }

    /** 打开“创作中心”网页：管理自己的草稿、资源与动态。 */
    public static void openCreatorCenter(Context context) {
        openUrl(context, HOST + CREATOR_PATH);
    }

    /** 打开“社区身份”网页：身份、认证徽章与社区成长清单。 */
    public static void openIdentity(Context context) {
        openUrl(context, HOST + IDENTITY_PATH);
    }

    /**
     * 打开举报网页。
     *
     * @param type  举报对象类型：{@code user|dynamic|comment|app|resource|message}
     * @param key   举报对象标识
     * @param title 举报对象展示名，仅用于网页回显
     */
    public static void openReport(Context context, String type, String key, String title) {
        Uri.Builder builder = Uri.parse(HOST + REPORT_PATH).buildUpon()
                .appendQueryParameter("target_type", safe(type))
                .appendQueryParameter("target_key", safe(key));
        String label = safe(title);
        if (!label.isEmpty()) {
            builder.appendQueryParameter("target_title", limit(label, 80));
        }
        openUrl(context, builder.build().toString());
    }

    private static void openUrl(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("URL", url);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    private static String safe(String value) {
        return value == null ? "" : value.trim();
    }

    private static String limit(String value, int max) {
        return value.length() <= max ? value : value.substring(0, max);
    }
}
