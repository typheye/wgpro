package open.cn.awg.pro.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;

import open.cn.awg.pro.utils.FastBlur;

/**
 * 手表应用栏毛玻璃背景（纯 Java 实现，API 22+ 可用，无 RenderEffect/RenderScript）。
 *
 * <p>把栏下方的内容区域按 {@link #DOWNSCALE} 降采样软绘成小位图，做三次方框模糊后放大绘制，
 * 再叠一层半透明压暗保证文字可读。随内容滚动按 {@link #MIN_INTERVAL_MS} 限帧刷新。
 */
public class BlurBarView extends View {

    private static final int DOWNSCALE = 8;          // 降采样倍数（越大越省）
    private static final int BLUR_RADIUS = 8;        // 小位图上的模糊半径
    private static final long MIN_INTERVAL_MS = 50;  // 限帧 ~20fps
    private static final int SCRIM_TOP = 0xFF000000;    // 顶部纯黑
    private static final int SCRIM_UPPER = 0xF2000000;  // 上部（约 95%）
    private static final int SCRIM_MID = 0xB3000000;    // 中部（约 70%）
    private static final int SCRIM_BOTTOM = 0x00000000; // 底部渐隐（透明）
    private static final float BLUR_HOLD = 0.45f;      // 模糊层在前 45% 高度保持不透明，之后渐隐到 0

    private final Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
    private final Paint scrimPaint = new Paint();
    private final Paint maskPaint = new Paint();
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Rect dst = new Rect();
    @Nullable private android.graphics.LinearGradient scrimGradient;
    @Nullable private android.graphics.LinearGradient maskGradient;
    private int scrimH = -1;

    private WeakReference<View> sourceRef = new WeakReference<>(null);
    @Nullable private Bitmap small;
    @Nullable private Canvas smallCanvas;
    private boolean scheduled;
    private long lastDraw;

    private final Runnable redraw = () -> {
        scheduled = false;
        invalidate();
    };

    private final ViewTreeObserver.OnScrollChangedListener onScroll = this::schedule;
    private final View.OnLayoutChangeListener onLayout =
            (v, l, t, r, b, ol, ot, or, ob) -> schedule();

    public BlurBarView(Context context) { super(context); init(); }

    public BlurBarView(Context context, @Nullable AttributeSet attrs) { super(context, attrs); init(); }

    public BlurBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint.setFilterBitmap(true);
        paint.setDither(true);
        maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    /** 设置要模糊的滚动内容视图（不能是本视图自身或包含本视图的祖先）。 */
    public void setSource(@Nullable View source) {
        View old = sourceRef.get();
        if (old != null) {
            old.getViewTreeObserver().removeOnScrollChangedListener(onScroll);
            old.removeOnLayoutChangeListener(onLayout);
        }
        sourceRef = new WeakReference<>(source);
        if (source != null) {
            source.getViewTreeObserver().addOnScrollChangedListener(onScroll);
            source.addOnLayoutChangeListener(onLayout);
        }
        if (small != null) {
            small.recycle();
            small = null;
            smallCanvas = null;
        }
        schedule();
    }

    private void schedule() {
        if (scheduled) return;
        scheduled = true;
        long now = SystemClock.uptimeMillis();
        long delay = Math.max(0L, MIN_INTERVAL_MS - (now - lastDraw));
        handler.postDelayed(redraw, delay);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        int w = getWidth();
        int h = getHeight();
        if (w <= 0 || h <= 0) {
            return;
        }
        View source = sourceRef.get();
        if (source != null && source.getWidth() > 0 && source.getHeight() > 0) {
            drawBlurredContent(canvas, source, w, h);
        }
        // 压暗层永远绘制：没有模糊源时也能保证应用栏是"深色玻璃"而不是全透明
        drawScrim(canvas, w, h);
    }

    /** 抓取 source 对应区域 → 降采样 → 模糊 → 带竖向 alpha 蒙版绘制。 */
    private void drawBlurredContent(@NonNull Canvas canvas, @NonNull View source, int w, int h) {
        int sw = Math.max(1, w / DOWNSCALE);
        int sh = Math.max(1, h / DOWNSCALE);
        if (small == null || small.getWidth() != sw || small.getHeight() != sh) {
            if (small != null) small.recycle();
            small = Bitmap.createBitmap(sw, sh, Bitmap.Config.ARGB_8888);
            smallCanvas = new Canvas(small);
        }
        small.eraseColor(0);

        int[] loc = new int[2];
        int[] sloc = new int[2];
        getLocationInWindow(loc);
        source.getLocationInWindow(sloc);
        int left = loc[0] - sloc[0];
        int top = loc[1] - sloc[1];

        smallCanvas.save();
        smallCanvas.scale(1f / DOWNSCALE, 1f / DOWNSCALE);
        smallCanvas.clipRect(left, top, left + w, top + h);
        try {
            source.draw(smallCanvas);
        } catch (Exception exception) {
            android.util.Log.w("AWGBlur", "snapshot draw failed", exception);
        }
        smallCanvas.restore();

        FastBlur.blur(small, BLUR_RADIUS);
        lastDraw = SystemClock.uptimeMillis();

        if (scrimGradient == null || scrimH != h) {
            maskGradient = new android.graphics.LinearGradient(
                    0, 0, 0, h,
                    new int[]{0xFF000000, 0xFF000000, 0x00000000},
                    new float[]{0f, BLUR_HOLD, 1f},
                    android.graphics.Shader.TileMode.CLAMP);
            scrimH = h;
            maskPaint.setShader(maskGradient);
        }
        dst.set(0, 0, w, h);
        int layer = canvas.saveLayer(0, 0, w, h, null);
        canvas.drawBitmap(small, null, dst, paint);
        canvas.drawRect(0, 0, w, h, maskPaint);
        canvas.restoreToCount(layer);
    }

    /** 竖向渐变压暗（顶部纯黑 → 底部透明），无论有无模糊源都绘制。 */
    private void drawScrim(@NonNull Canvas canvas, int w, int h) {
        if (scrimGradient == null || scrimH != h) {
            scrimGradient = new android.graphics.LinearGradient(
                    0, 0, 0, h,
                    new int[]{SCRIM_TOP, SCRIM_UPPER, SCRIM_MID, SCRIM_BOTTOM},
                    new float[]{0f, 0.5f, 0.82f, 1f},
                    android.graphics.Shader.TileMode.CLAMP);
            maskGradient = new android.graphics.LinearGradient(
                    0, 0, 0, h,
                    new int[]{0xFF000000, 0xFF000000, 0x00000000},
                    new float[]{0f, BLUR_HOLD, 1f},
                    android.graphics.Shader.TileMode.CLAMP);
            scrimH = h;
            scrimPaint.setShader(scrimGradient);
            maskPaint.setShader(maskGradient);
        }
        canvas.drawRect(0, 0, w, h, scrimPaint);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        View source = sourceRef.get();
        if (source != null) {
            source.getViewTreeObserver().removeOnScrollChangedListener(onScroll);
            source.removeOnLayoutChangeListener(onLayout);
        }
        handler.removeCallbacks(redraw);
    }
}
