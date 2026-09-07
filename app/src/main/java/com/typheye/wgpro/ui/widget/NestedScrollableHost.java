package com.typheye.wgpro.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/** Coordinates a horizontal child pager nested inside the horizontal main pager. */
public final class NestedScrollableHost extends FrameLayout {
    private final int touchSlop;
    private float initialX;
    private float initialY;

    public NestedScrollableHost(@NonNull Context context) {
        this(context, null);
    }

    public NestedScrollableHost(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        handleInterceptTouchEvent(event);
        return super.onInterceptTouchEvent(event);
    }

    private void handleInterceptTouchEvent(MotionEvent event) {
        View child = getChildCount() == 0 ? null : getChildAt(0);
        if (child == null) return;
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            initialX = event.getX();
            initialY = event.getY();
            getParent().requestDisallowInterceptTouchEvent(true);
            return;
        }
        if (event.getActionMasked() == MotionEvent.ACTION_UP
                || event.getActionMasked() == MotionEvent.ACTION_CANCEL) {
            getParent().requestDisallowInterceptTouchEvent(false);
            return;
        }
        if (event.getActionMasked() != MotionEvent.ACTION_MOVE) return;
        float dx = event.getX() - initialX;
        float dy = event.getY() - initialY;
        if (Math.abs(dx) <= touchSlop && Math.abs(dy) <= touchSlop) return;
        if (Math.abs(dx) > Math.abs(dy)) {
            int direction = dx > 0 ? -1 : 1;
            getParent().requestDisallowInterceptTouchEvent(
                    child.canScrollHorizontally(direction));
        } else {
            getParent().requestDisallowInterceptTouchEvent(false);
        }
    }
}
