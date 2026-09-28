package com.typheye.wgpro.ui.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

/**
 * 用户主页抽屉的触摸分工：
 *
 * <ul>
 *   <li>触摸从**顶部条带**（小横条 / 标签栏，即 {@code dragZoneAnchor} 底边以上）开始：
 *       正常拖拽抽屉（上下滑动、fling 全部沿用默认行为）；</li>
 *   <li>触摸从**列表区域**开始：默认交给列表滚动（避免"列表滑动不灵敏/下拉刷新把抽屉带跑偏"）；
 *       但**抽屉处于展开态、列表已滚到顶部、且手指向下拖**时，交给抽屉去**收起**——
 *       这是标准 bottom sheet 的手感（展开后从内容区下滑即可收起）。</li>
 * </ul>
 */
public final class ProfileBottomSheetBehavior<V extends View> extends BottomSheetBehavior<V> {
    @Nullable private View dragZoneAnchor;
    private final Rect zone = new Rect();
    private boolean startedInDragZone;
    private float downRawY;
    private boolean listAtTop;
    private boolean downWhileExpanded;

    public ProfileBottomSheetBehavior(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /** 顶部条带的锚点：该视图底边以上算"可拖拽区"（一般传标签栏）。 */
    public void setDragZoneAnchor(@Nullable View anchor) {
        dragZoneAnchor = anchor;
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout parent,
                                         @NonNull V child,
                                         @NonNull MotionEvent event) {
        int action = event.getActionMasked();
        int slop = ViewConfiguration.get(parent.getContext()).getScaledTouchSlop();
        if (action == MotionEvent.ACTION_DOWN) {
            downRawY = event.getRawY();
            startedInDragZone = false;
            downWhileExpanded = getState() == STATE_EXPANDED;
            if (dragZoneAnchor != null && dragZoneAnchor.getGlobalVisibleRect(zone)) {
                startedInDragZone = event.getRawY() <= zone.bottom;
            }
            listAtTop = scrollAreaAtTop(child);
            super.onInterceptTouchEvent(parent, child, event);
            return false;
        }
        if (startedInDragZone) {
            return super.onInterceptTouchEvent(parent, child, event);
        }
        // 列表区开始：展开态 + 列表在顶部 + 向下滑超过阈值 → 直接收起抽屉（滑动结束后生效，避免和列表抢手势）
        if (downWhileExpanded && listAtTop && getState() == STATE_EXPANDED
                && event.getRawY() - downRawY > slop * 2
                && (action == MotionEvent.ACTION_MOVE || action == MotionEvent.ACTION_UP)) {
            if (action == MotionEvent.ACTION_UP) setState(STATE_COLLAPSED);
        }
        // 其余一律不拦截，交给列表滚动
        return false;
    }

    /** 抽屉内部是否已经在顶部：只要没有「已向下滚动过」的竖向列表，就认为在顶部（含空列表）。 */
    private boolean scrollAreaAtTop(@NonNull View view) {
        if (view instanceof NestedScrollView || view instanceof ScrollView) {
            return !view.canScrollVertically(-1);
        }
        if (view instanceof RecyclerView) {
            RecyclerView recycler = (RecyclerView) view;
            RecyclerView.LayoutManager manager = recycler.getLayoutManager();
            // 横向列表（ViewPager2 内部）不参与判断
            if (manager == null || !manager.canScrollVertically()) return true;
            return !recycler.canScrollVertically(-1);
        }
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int i = 0; i < group.getChildCount(); i++) {
                View child = group.getChildAt(i);
                if (child.getVisibility() != View.VISIBLE) continue;
                if (!scrollAreaAtTop(child)) return false;
            }
        }
        return true;
    }
}
