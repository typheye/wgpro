package com.typheye.wgpro.ui.widget;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.typheye.wgpro.R;

/**
 * 应用图标的统一呈现规则。
 *
 * <p>两套图标形态不一样：</p>
 * <ul>
 *   <li><b>Android 应用</b>：方形图标，套圆角矩形裁剪（{@code bg_app_icon_clip}）+ 撑满（CENTER_CROP）。</li>
 *   <li><b>Vela 小程序</b>：图标<b>本身就是圆的</b>，再套圆角矩形底和内边距会出现"圆外套方"的怪样子——
 *       所以直接原样显示（无背景、无内边距、FIT_CENTER 居中）。</li>
 * </ul>
 *
 * <p>应用列表卡片、应用详情页、下载管理三处都走这里，保证一致。</p>
 */
public final class WGProIconStyle {

    private WGProIconStyle() { }

    /** 按是不是 Vela 应用设置图标呈现方式。 */
    public static void apply(@NonNull ImageView icon, boolean velaApp) {
        icon.setPadding(0, 0, 0, 0);
        if (velaApp) {
            icon.setBackground(null);
            icon.setClipToOutline(false);
            icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        } else {
            icon.setBackgroundResource(R.drawable.bg_app_icon_clip);
            icon.setClipToOutline(true);
            icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    /** 是否 Vela 小程序（各类详情 JSON 里的 {@code kind} 字段）。 */
    public static boolean isVelaApp(@NonNull org.json.JSONObject json) {
        return "velaapp".equals(json.optString("kind", ""));
    }
}
