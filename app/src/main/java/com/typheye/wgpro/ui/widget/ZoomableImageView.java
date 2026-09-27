package com.typheye.wgpro.ui.widget;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * 可缩放预览的 ImageView：双指缩放、双击放大/还原、放大后拖动。
 *
 * <p>用 {@link Matrix} 手动控制。未放大时把横向滑动交给父级（ViewPager2 翻页）；
 * 放大后 {@code requestDisallowInterceptTouchEvent(true)}，把拖动留给图片。</p>
 */
public class ZoomableImageView extends AppCompatImageView {

    private static final float MAX_SCALE = 4f;
    private static final float DOUBLE_TAP_SCALE = 2.5f;

    private final Matrix matrix = new Matrix();
    private final Matrix baseMatrix = new Matrix();
    private final float[] values = new float[9];
    private final RectF drawableRect = new RectF();

    private ScaleGestureDetector scaleDetector;
    private GestureDetector gestureDetector;
    private float currentScale = 1f;
    @Nullable private Runnable onTransformChanged;
    @Nullable private Listener listener;

    /** 单击 / 长按回调（预览页用：单击退出、长按弹菜单）。 */
    public interface Listener {
        void onSingleTap();
        void onLongPress();
    }

    public void setListener(@Nullable Listener value) {
        listener = value;
    }

    /** 缩放 / 拖动 / 双击后回调：宿主用它刷新应用栏毛玻璃快照（图片不是滚动容器，不会自动触发）。 */
    public void setOnTransformChanged(@Nullable Runnable callback) {
        onTransformChanged = callback;
    }

    private void notifyTransformChanged() {
        Runnable callback = onTransformChanged;
        if (callback != null) callback.run();
    }

    public ZoomableImageView(Context context) {
        this(context, null);
    }

    public ZoomableImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomableImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setScaleType(ScaleType.MATRIX);
        scaleDetector = new ScaleGestureDetector(context, new ScaleListener());
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    @Override
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        post(this::resetToBase);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        resetToBase();
    }

    /** 按「完整显示（fitCenter）」重建基础矩阵。 */
    private void resetToBase() {
        Drawable drawable = getDrawable();
        if (drawable == null || getWidth() == 0 || getHeight() == 0) return;
        float sourceWidth = drawable.getIntrinsicWidth();
        float sourceHeight = drawable.getIntrinsicHeight();
        if (sourceWidth <= 0 || sourceHeight <= 0) return;
        float scale = Math.min(getWidth() / sourceWidth, getHeight() / sourceHeight);
        float dx = (getWidth() - sourceWidth * scale) / 2f;
        float dy = (getHeight() - sourceHeight * scale) / 2f;
        baseMatrix.reset();
        baseMatrix.postScale(scale, scale);
        baseMatrix.postTranslate(dx, dy);
        matrix.set(baseMatrix);
        currentScale = 1f;
        setImageMatrix(matrix);
    }

    private void applyScale(float factor, float focusX, float focusY) {
        float target = currentScale * factor;
        if (target < 1f) target = 1f;
        if (target > MAX_SCALE) target = MAX_SCALE;
        factor = target / currentScale;
        currentScale = target;
        if (currentScale <= 1.0001f) {
            matrix.set(baseMatrix);
        } else {
            matrix.postScale(factor, factor, focusX, focusY);
            constrain();
        }
        setImageMatrix(matrix);
        notifyTransformChanged();
    }

    /** 把图片限制在视图范围内，避免拖出空白。 */
    private void constrain() {
        Drawable drawable = getDrawable();
        if (drawable == null) return;
        drawableRect.set(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        matrix.mapRect(drawableRect);
        float deltaX = 0f;
        float deltaY = 0f;
        float viewWidth = getWidth();
        float viewHeight = getHeight();
        float width = drawableRect.width();
        float height = drawableRect.height();

        if (width <= viewWidth) {
            deltaX = (viewWidth - width) / 2f - drawableRect.left;
        } else if (drawableRect.left > 0f) {
            deltaX = -drawableRect.left;
        } else if (drawableRect.right < viewWidth) {
            deltaX = viewWidth - drawableRect.right;
        }

        if (height <= viewHeight) {
            deltaY = (viewHeight - height) / 2f - drawableRect.top;
        } else if (drawableRect.top > 0f) {
            deltaY = -drawableRect.top;
        } else if (drawableRect.bottom < viewHeight) {
            deltaY = viewHeight - drawableRect.bottom;
        }

        matrix.postTranslate(deltaX, deltaY);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        ViewParent parent = getParent();
        boolean zoomed = currentScale > 1.0001f;
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(zoomed || event.getPointerCount() > 1);
        }
        scaleDetector.onTouchEvent(event);
        gestureDetector.onTouchEvent(event);
        return true;
    }

    private final class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            applyScale(detector.getScaleFactor(), detector.getFocusX(), detector.getFocusY());
            return true;
        }
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapConfirmed(@NonNull MotionEvent event) {
            if (listener != null) listener.onSingleTap();
            return true;
        }

        @Override
        public void onLongPress(@NonNull MotionEvent event) {
            if (listener != null) listener.onLongPress();
        }

        @Override
        public boolean onDoubleTap(@NonNull MotionEvent event) {
            if (currentScale > 1.0001f) {
                currentScale = 1f;
                matrix.set(baseMatrix);
                setImageMatrix(matrix);
                notifyTransformChanged();
            } else {
                applyScale(DOUBLE_TAP_SCALE, event.getX(), event.getY());
            }
            return true;
        }

        @Override
        public boolean onScroll(@NonNull MotionEvent down, @NonNull MotionEvent current,
                                float distanceX, float distanceY) {
            if (currentScale <= 1.0001f) return false;
            matrix.postTranslate(-distanceX, -distanceY);
            constrain();
            setImageMatrix(matrix);
            notifyTransformChanged();
            return true;
        }
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    /** 供外部读取当前缩放值（测试/调试用）。 */
    public float currentScale() {
        matrix.getValues(values);
        return values[Matrix.MSCALE_X];
    }
}
