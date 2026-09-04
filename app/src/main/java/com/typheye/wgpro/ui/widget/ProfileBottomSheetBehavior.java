package com.typheye.wgpro.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

/** Prevents meaningless downward over-drag when the profile sheet is already collapsed. */
public final class ProfileBottomSheetBehavior<V extends View> extends BottomSheetBehavior<V> {
    private final int touchSlop;
    private float downX;
    private float downY;
    private boolean startedCollapsed;

    public ProfileBottomSheetBehavior(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout parent,
                                         @NonNull V child,
                                         @NonNull MotionEvent event) {
        int action = event.getActionMasked();
        if (action == MotionEvent.ACTION_DOWN) {
            downX = event.getX();
            downY = event.getY();
            startedCollapsed = getState() == STATE_COLLAPSED;
            return super.onInterceptTouchEvent(parent, child, event);
        }

        if (action == MotionEvent.ACTION_MOVE && startedCollapsed) {
            float dx = event.getX() - downX;
            float dy = event.getY() - downY;
            // A collapsed sheet has nowhere lower to go. Leave downward and
            // horizontal-dominant gestures to its children instead of starting
            // ViewDragHelper's over-drag and spring-back animation.
            if (dy > touchSlop) {
                return false;
            }
            if (Math.abs(dx) > touchSlop && Math.abs(dx) > Math.abs(dy)) {
                return false;
            }
        }

        boolean intercepted = super.onInterceptTouchEvent(parent, child, event);
        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
            startedCollapsed = false;
        }
        return intercepted;
    }
}
