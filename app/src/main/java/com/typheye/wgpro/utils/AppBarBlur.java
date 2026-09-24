package com.typheye.wgpro.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.typheye.wgpro.R;

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
 * 本类只负责三件事：应用栏吃状态栏内边距、内容里的第一个滚动容器补应用栏高度的顶部留白、
 * 把 XML 里的快照层交给 {@link BarBlurController}。列表滚动时就会从毛玻璃下方穿过。
 * 折叠应用栏（CollapsingToolbarLayout）与 WebView 页面不要用。
 */
public final class AppBarBlur {
    private AppBarBlur() { }

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
        SystemBars.applyAppBarInsets(appBar, null);
        ViewTreeObserver.OnGlobalLayoutListener listener = () -> {
            int height = appBar.getHeight();
            if (height > 0) padFirstScrollable(content, height);
        };
        content.getViewTreeObserver().addOnGlobalLayoutListener(listener);

        if (backdrop == null) {
            // 兼容旧布局：没有声明快照层时退化为普通半透明底色
            appBar.setBackgroundColor(activity.getColor(R.color.surface_bar));
            return BarBlurController.install(activity, content);
        }
        return BarBlurController.install(activity, content, backdrop);
    }

    /** 只给遇到的第一个滚动容器补顶部内边距，避免嵌套滚动容器被补两次。 */
    private static boolean padFirstScrollable(View view, int top) {
        if (view instanceof NestedScrollView) {
            NestedScrollView scroll = (NestedScrollView) view;
            scroll.setClipToPadding(false);
            scroll.setPadding(scroll.getPaddingLeft(), top,
                    scroll.getPaddingRight(), scroll.getPaddingBottom());
            return true;
        }
        if (view instanceof RecyclerView) {
            RecyclerView recycler = (RecyclerView) view;
            recycler.setClipToPadding(false);
            recycler.setPadding(recycler.getPaddingLeft(), top,
                    recycler.getPaddingRight(), recycler.getPaddingBottom());
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
