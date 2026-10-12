package com.typheye.wgpro.ui.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.typheye.wgpro.R;

/**
 * 全站统一的下拉刷新控件（继承 AndroidX {@code SwipeRefreshLayout}）。
 *
 * <p>为什么要有这个类：AndroidX 的 {@code SwipeRefreshLayout} 圆盘底色是<b>控件里写死的白色</b>，
 * 而且它不读 Material 的 {@code swipeRefreshLayoutProgressSpinnerBackgroundColor}——
 * 深色 / 莫奈取色下会顶着一个白圆（踩过这个坑）。</p>
 *
 * <p>把配色放进构造里，布局直接写这个类即可，<b>不需要每个页面记得调一次设置</b>，
 * 新页面也不会漏：</p>
 * <ul>
 *   <li>转圈弧色 = 主题主色（{@code ?attr/colorPrimary}）</li>
 *   <li>圆盘底色 = 主题容器色（{@code ?attr/colorSurfaceContainerHigh}）</li>
 * </ul>
 *
 * <p>深浅模式与「动态取色」开关都由主题属性决定，这里是唯一出口；
 * 代码里动态创建 {@code SwipeRefreshLayout} 时请用
 * {@link WGProColors#applyRefresh(android.view.View, Context)} 保持一致的配色。</p>
 */
public class WGProRefreshLayout extends androidx.swiperefreshlayout.widget.SwipeRefreshLayout {

    public WGProRefreshLayout(@NonNull Context context) {
        this(context, null);
    }

    public WGProRefreshLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setColorSchemeColors(WGProAlertDialogBuilder.resolveThemeColor(
                context, "colorPrimary", R.color.brand_primary));
        setProgressBackgroundColorSchemeColor(WGProAlertDialogBuilder.resolveThemeColor(
                context, "colorSurfaceContainerHigh", R.color.surface_elevated));
    }
}
