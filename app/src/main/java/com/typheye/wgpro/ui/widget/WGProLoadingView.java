package com.typheye.wgpro.ui.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.typheye.wgpro.R;

/**
 * 自定义加载动画：一段绕圈奔跑的圆弧（带淡淡底环），弧长做呼吸变化、整体轻微缩放。
 *
 * <p>完全用 {@link Canvas} 绘制，不依赖 Material 的 {@code CircularProgressIndicator}——
 * 该原生控件在部分 ROM 上会出现不转圈 / 绘制异常的问题。默认取品牌色
 * {@code brand_primary}，也可以在 XML 里用 {@code loadingColor} /
 * {@code loadingTrackColor} / {@code loadingStrokeWidth} 覆盖。
 *
 * <p>可见且附着在窗口上时才跑动画，隐藏（如加载遮罩收起）自动停掉，不浪费资源。
 */
public class WGProLoadingView extends View {

    /** 一圈的时长；旋转 720°、弧长呼吸一轮。 */
    private static final long CYCLE_MS = 1500L;
    /** 弧长范围（度）。 */
    private static final float MIN_SWEEP = 38f;
    private static final float MAX_SWEEP = 250f;
    /** 默认底环透明度（未显式指定 loadingTrackColor 时）。 */
    private static final int DEFAULT_TRACK_ALPHA = 0x2E;

    private final Paint trackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint arcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final RectF arcBounds = new RectF();

    private float strokeWidth;
    private float phase;
    @Nullable private ValueAnimator animator;

    public WGProLoadingView(Context context) {
        this(context, null);
    }

    public WGProLoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WGProLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        int color = context.getColor(R.color.brand_primary);
        int trackColor = 0;
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.WGProLoadingView,
                    defStyleAttr, 0);
            color = array.getColor(R.styleable.WGProLoadingView_loadingColor, color);
            trackColor = array.getColor(R.styleable.WGProLoadingView_loadingTrackColor, 0);
            strokeWidth = array.getDimension(R.styleable.WGProLoadingView_loadingStrokeWidth, 0f);
            array.recycle();
        }
        trackPaint.setStyle(Paint.Style.STROKE);
        trackPaint.setStrokeCap(Paint.Cap.ROUND);
        if (trackColor == 0) {
            trackPaint.setColor(color);
            trackPaint.setAlpha(DEFAULT_TRACK_ALPHA);
        } else {
            trackPaint.setColor(trackColor);
        }
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeCap(Paint.Cap.ROUND);
        arcPaint.setColor(color);
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        if (strokeWidth <= 0f) {
            float density = getResources().getDisplayMetrics().density;
            strokeWidth = Math.max(2f * density, size * 0.085f);
        }
        trackPaint.setStrokeWidth(strokeWidth);
        arcPaint.setStrokeWidth(strokeWidth);
    }

    @Override protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        int availableWidth = getWidth() - getPaddingLeft() - getPaddingRight();
        int availableHeight = getHeight() - getPaddingTop() - getPaddingBottom();
        float diameter = Math.min(availableWidth, availableHeight) - strokeWidth;
        if (diameter <= 0f) return;
        float centerX = getPaddingLeft() + availableWidth / 2f;
        float centerY = getPaddingTop() + availableHeight / 2f;

        // 呼吸缩放（与弧长错开一倍频率），让转动显得更柔和
        float breathing = 0.5f - 0.5f * (float) Math.cos(phase * 4f * Math.PI);
        float radius = diameter / 2f * (0.94f + 0.06f * breathing);
        arcBounds.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);

        canvas.drawCircle(centerX, centerY, radius, trackPaint);

        float sweep = MIN_SWEEP + (MAX_SWEEP - MIN_SWEEP)
                * (0.5f - 0.5f * (float) Math.cos(phase * 2f * Math.PI));
        canvas.save();
        canvas.rotate(phase * 720f, centerX, centerY);
        canvas.drawArc(arcBounds, 0f, sweep, false, arcPaint);
        canvas.restore();
    }

    @Override protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        syncAnimation();
    }

    @Override protected void onDetachedFromWindow() {
        if (animator != null) animator.cancel();
        super.onDetachedFromWindow();
    }

    @Override protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        syncAnimation();
    }

    /** 可见且附着在窗口上才跑动画，其它情况停掉。 */
    private void syncAnimation() {
        boolean running = isAttachedToWindow() && getVisibility() == VISIBLE && isShown();
        if (running) {
            if (animator == null) {
                animator = ValueAnimator.ofFloat(0f, 1f);
                animator.setDuration(CYCLE_MS);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setInterpolator(new LinearInterpolator());
                animator.addUpdateListener(animation -> {
                    phase = (float) animation.getAnimatedValue();
                    invalidate();
                });
            }
            if (!animator.isStarted()) animator.start();
        } else if (animator != null && animator.isStarted()) {
            animator.cancel();
        }
    }
}
