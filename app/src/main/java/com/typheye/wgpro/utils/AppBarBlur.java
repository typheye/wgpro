package com.typheye.wgpro.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.typheye.wgpro.R;

import java.util.WeakHashMap;

/**
 * 固定应用栏毛玻璃装配，结构与 {@code activity_main.xml} 保持一致：
 *
 * <pre>
 * 布局里显式声明（放在内容之后，保证画在内容之上）：
 *   FrameLayout(app_bar_host)
 *     ├── ImageView(app_bar_blur)   // 毛玻璃快照层，约束/填充到应用栏范围
 *     └── AppBarLayout(surface_bar + 透明 Toolbar)
 * </pre>
 *
 * 本类负责四件事：应用栏吃状态栏内边距、内容里的第一个滚动容器补应用栏高度的顶部留白、
 * 把内容里的下拉刷新指示器压到应用栏下方、把 XML 里的快照层交给 {@link BarBlurController}。
 * 列表滚动时就会从毛玻璃下方穿过。折叠应用栏（CollapsingToolbarLayout）与 WebView 页面不要用。
 */
public final class AppBarBlur {
    private AppBarBlur() { }

    /** 已设置过的下拉刷新指示器位置（键为控件本身）：重复设置会重置指示器、打断下拉手势。 */
    private static final WeakHashMap<View, Integer> REFRESH_OFFSETS = new WeakHashMap<>();

    /** 各滚动视图**原始**的顶部内边距：补应用栏留白时要叠加，不能覆盖（否则列表会贴顶、没 gap）。 */
    private static final WeakHashMap<View, Integer> BASE_TOP_PADDING = new WeakHashMap<>();
    /** 原始底部内边距：同理，补导航栏/底部留白也要叠加。 */
    private static final WeakHashMap<View, Integer> BASE_BOTTOM_PADDING = new WeakHashMap<>();

    /**
     * 给滚动视图补「应用栏高度」的顶部留白，**保留原有 padding 叠加**。
     *
     * <p>注意不能直接 `setPadding(..., top, ...)`：很多列表自己带了顶部 12–16dp 的视觉留白
     * （以前在内容层里，现在移到 RecyclerView 上），覆盖掉就会贴到应用栏下面、没有 gap。
     */
    public static void padScrollableTop(@NonNull View view, int top) {
        Integer base = BASE_TOP_PADDING.get(view);
        if (base == null) {
            base = view.getPaddingTop();
            BASE_TOP_PADDING.put(view, base);
        }
        int desired = base + Math.max(0, top);
        if (view.getPaddingTop() == desired) return;
        view.setPadding(view.getPaddingLeft(), desired, view.getPaddingRight(), view.getPaddingBottom());
    }

    /**
     * 给滚动视图补「底部安全区」留白，同样**保留原有 padding 叠加**。
     * （RecyclerView 的 XML 上常有 28dp 底部留白，直接覆盖会让最后一张卡片贴住屏幕底边。）
     */
    public static void padScrollableBottom(@NonNull View view, int bottom) {
        Integer base = BASE_BOTTOM_PADDING.get(view);
        if (base == null) {
            base = view.getPaddingBottom();
            BASE_BOTTOM_PADDING.put(view, base);
        }
        int desired = base + Math.max(0, bottom);
        if (view.getPaddingBottom() == desired) return;
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), desired);
    }

    /**
     * 单个下拉刷新控件：把转圈指示器的位置压到应用栏下方。
     *
     * <p>内容铺到半透明应用栏下面之后，指示器默认位置会被应用栏盖住——下拉时看不到任何反馈，
     * 用户会以为"没有触发"。只在应用栏高度变化时设置一次（幂等），避免重复调用打断手势。
     */
    public static void offsetRefreshIndicator(@NonNull SwipeRefreshLayout refresh, int top) {
        Integer applied = REFRESH_OFFSETS.get(refresh);
        if (applied != null && applied == top) return;
        refresh.setProgressViewOffset(true, top, top + Math.round(
                64f * refresh.getResources().getDisplayMetrics().density));
        REFRESH_OFFSETS.put(refresh, top);
    }

    /** 递归给内容里所有下拉刷新控件设置指示器位置。 */
    public static void offsetRefreshIndicators(@NonNull View view, int top) {
        if (view instanceof SwipeRefreshLayout) {
            offsetRefreshIndicator((SwipeRefreshLayout) view, top);
        }
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int index = 0; index < group.getChildCount(); index++) {
                offsetRefreshIndicators(group.getChildAt(index), top);
            }
        }
    }

    /**
     * @param appBar  固定应用栏（AppBarLayout，位于 app_bar_host 内）
     * @param content 页面内容容器：滚动视图或承载 Fragment 的容器（铺满整屏）
     */
    @NonNull
    public static BarBlurController install(@NonNull Activity activity,
                                            @NonNull View appBar,
                                            @NonNull View content) {
        View host = (View) appBar.getParent();
        ImageView backdrop = host.findViewById(R.id.app_bar_blur);

        // 应用栏吃状态栏内边距；滚动容器顶部留白 = 应用栏高度（幂等，不反复触发布局）
        // 应用栏登记：模糊关闭时改成与页面一致的不透明底色
        BarBlurController.registerBar(activity, appBar);
        SystemBars.applyAppBarInsets(appBar, null);
        ViewTreeObserver.OnGlobalLayoutListener listener = () -> {
            int height = appBar.getHeight();
            if (height > 0) {
                padFirstScrollable(content, height);
                offsetRefreshIndicators(content, height);
            }
        };
        content.getViewTreeObserver().addOnGlobalLayoutListener(listener);

        if (backdrop == null) {
            // 兼容旧布局：没有声明快照层时退化为普通半透明底色
            appBar.setBackgroundColor(activity.getColor(R.color.surface_bar));
            return BarBlurController.install(activity, content);
        }
        return BarBlurController.install(activity, content, backdrop);
    }

    /**
     * 内容结构是「不滚动容器 + 内部滚动视图」（与 {@code SettingsActivity} 一致）时的装配。
     *
     * <p><b>快照源必须是那个不滚动的容器，不能是滚动视图本身</b>：{@code View.drawBackground()}
     * 在视图自身滚动时会按 {@code scrollY} 平移背景，滚动后快照顶部会变成透明，毛玻璃就消失了
     * （表现：内容穿到应用栏下面但一点不糊）。容器自身滚动恒为 0，背景与子内容都能正确画进快照。
     *
     * <p>顶部留白（应用栏高度）与底部留白（系统栏）由同一个 insets 回调加在内部滚动视图上，互不覆盖。
     *
     * @param snapshotContainer 不滚动的内容容器（快照源，需有不透明背景）
     * @param scrollContent     容器内部真正滚动的视图
     */
    @NonNull
    public static BarBlurController installWithScrollContent(@NonNull Activity activity,
                                                             @NonNull View appBar,
                                                             @NonNull View snapshotContainer,
                                                             @NonNull View scrollContent) {
        BarBlurController controller = install(activity, appBar, snapshotContainer);
        if (scrollContent instanceof ViewGroup) {
            ((ViewGroup) scrollContent).setClipToPadding(false);
        }
        final int initialLeft = scrollContent.getPaddingLeft();
        final int initialRight = scrollContent.getPaddingRight();
        final int initialBottom = scrollContent.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(scrollContent, (view, insets) -> {
            int top = appBar.getHeight();
            int bottom = initialBottom + SystemBars.bottomInsetForView(view, insets);
            if (view.getPaddingTop() != top || view.getPaddingBottom() != bottom) {
                view.setPadding(initialLeft, top, initialRight, bottom);
            }
            return insets;
        });
        // 应用栏高度确定后重新派发一次，保证顶部留白正确。
        scrollContent.getViewTreeObserver().addOnGlobalLayoutListener(
                () -> ViewCompat.requestApplyInsets(scrollContent));
        ViewCompat.requestApplyInsets(scrollContent);
        return controller;
    }

    /**
     * 只给遇到的第一个滚动容器补顶部内边距，避免嵌套滚动容器被补两次。
     *
     * <p>遇到 {@link androidx.viewpager2.widget.ViewPager2} 时跳过它自己的内部 RecyclerView，
     * 改去补「每个页面里面」的第一个滚动容器——只补第一个页面的话，其它页面会没有留白、
     * 内容顶到应用栏下面（看起来就是"页面错位"）。
     */
    private static boolean padFirstScrollable(View view, int top) {
        // 隐藏的滚动视图不用补留白（例如关注/粉丝页同时存在 NestedScrollView 与 RecyclerView，
        // 只给当前可见的那个补）。
        if (view.getVisibility() != View.VISIBLE) return false;
        if (view instanceof androidx.viewpager2.widget.ViewPager2) {
            androidx.viewpager2.widget.ViewPager2 pager =
                    (androidx.viewpager2.widget.ViewPager2) view;
            boolean padded = false;
            for (int index = 0; index < pager.getChildCount(); index++) {
                View child = pager.getChildAt(index);
                if (!(child instanceof ViewGroup)) continue;
                ViewGroup holder = (ViewGroup) child;
                for (int page = 0; page < holder.getChildCount(); page++) {
                    // 每一页都尝试一次；某一页还没有内容时不影响其它页
                    if (padFirstScrollable(holder.getChildAt(page), top)) padded = true;
                }
            }
            return padded;
        }
        if (view instanceof NestedScrollView) {
            NestedScrollView scroll = (NestedScrollView) view;
            scroll.setClipToPadding(false);
            padScrollableTop(scroll, top);
            return true;
        }
        if (view instanceof RecyclerView) {
            RecyclerView recycler = (RecyclerView) view;
            recycler.setClipToPadding(false);
            padScrollableTop(recycler, top);
            return true;
        }
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int index = 0; index < group.getChildCount(); index++) {
                if (padFirstScrollable(group.getChildAt(index), top)) return true;
            }
        }
        return false;
    }
}
