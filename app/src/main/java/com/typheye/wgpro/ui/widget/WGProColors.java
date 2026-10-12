package com.typheye.wgpro.ui.widget;

import android.content.Context;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;

import com.typheye.wgpro.R;

/**
 * 静态调色板 → 莫奈主题色的统一出口。
 *
 * <p>本 App 的 {@code values/colors.xml} 是一套固定调色板（浅/深两套值），
 * 只有 {@code ?attr/...} 这类<b>主题属性</b>才会被 Material 动态取色（莫奈）覆盖。
 * 所以代码里凡是直接 {@code com.typheye.wgpro.ui.widget.WGProColors.of(context, R.color.text_primary)} 的地方都不会跟随壁纸取色。</p>
 *
 * <p>这里做一层映射：能用主题色的走主题色（莫奈开则跟随壁纸，关则回落到主题里配置的静态色），
 * 取不到主题属性时回退成原来的颜色资源，行为与改造前一致。</p>
 */
public final class WGProColors {

    private WGProColors() { }

    /** 取色：优先莫奈主题色，失败回退 {@code colorRes}。 */
    public static int of(@NonNull Context context, @ColorRes int colorRes) {
        String attr = attrFor(colorRes);
        if (attr == null) return context.getColor(colorRes);
        return WGProAlertDialogBuilder.resolveThemeColor(context, attr, colorRes);
    }

    /** 静态色 → 主题属性的映射（没有映射的返回 null，表示这是有意固定的语义色）。 */
    private static String attrFor(@ColorRes int colorRes) {
        if (colorRes == R.color.text_primary || colorRes == R.color.text_u1) return "colorOnSurface";
        if (colorRes == R.color.text_secondary || colorRes == R.color.text_u2) return "colorOnSurfaceVariant";
        if (colorRes == R.color.text_tertiary) return "colorOutline";
        // 背景 / 应用栏 / 导航栏：按需求保持**纯黑纯白**（不跟莫奈），所以这里不做映射
        if (colorRes == R.color.surface_page || colorRes == R.color.surface_primary
                || colorRes == R.color.surface_bar || colorRes == R.color.tab
                || colorRes == R.color.profile_detail_sheet) return null;
        // 卡片 / 容器：跟随莫奈
        if (colorRes == R.color.surface_secondary) return "colorSurfaceContainer";
        if (colorRes == R.color.surface_elevated) return "colorSurfaceContainerHigh";
        if (colorRes == R.color.outline_soft) return "colorOutlineVariant";
        if (colorRes == R.color.brand_primary || colorRes == R.color.tab_accent) return "colorPrimary";
        if (colorRes == R.color.brand_soft || colorRes == R.color.tab_accent_th) return "colorPrimaryContainer";
        if (colorRes == R.color.brand_on_soft) return "colorOnPrimaryContainer";
        return null;
    }
}
