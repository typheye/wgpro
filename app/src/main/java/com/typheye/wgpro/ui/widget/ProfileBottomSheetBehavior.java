package com.typheye.wgpro.ui.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

/**
 * 用户主页抽屉的触摸分工（只做一件明确的事，其余完全交回默认 BottomSheetBehavior）：
 *
 * <ul>
 *   <li>触摸从**顶部条带**（小横条 / 标签栏，即 {@code dragZoneAnchor} 底边以上）开始：
 *       正常拖拽抽屉（上下滑动、fling 全部沿用默认行为）；</li>
 *   <li>触摸从**列表区域**开始：一律不拦截，全部交给列表滚动——
 *       解决"列表滑动不灵敏""刷新下拉把抽屉带跑偏"这类抢手势问题。</li>
 * </ul>
 */
public final class ProfileBottomSheetBehavior<V extends View> extends BottomSheetBehavior<V> {
    @Nullable private View dragZoneAnchor;
    private final Rect zone = new Rect();
    private boolean startedInDragZone;

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
        if (action == MotionEvent.ACTION_DOWN) {
            startedInDragZone = false;
            if (dragZoneAnchor != null && dragZoneAnchor.getGlobalVisibleRect(zone)) {
                startedInDragZone = event.getRawY() <= zone.bottom;
            }
        }
        // 从列表区开始的触摸：完全不拦截，交给列表滚动
        if (!startedInDragZone) return false;
        return super.onInterceptTouchEvent(parent, child, event);
    }
}
