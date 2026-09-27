package com.typheye.wgpro.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceManager;

import com.typheye.wgpro.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 应用栏 / 导航栏 / 底栏的毛玻璃背景（可直接渲染的稳定版本）。
 *
 * <p>把内容视图按 1/4 缩小软绘成快照位图，交给位于栏下方的 {@link ImageView}
 * 用 {@link RenderEffect} 高斯模糊绘制；滚动/翻页/布局变化时按帧刷新（~60fps）。
 * 仅 API 31+ 启用，低版本隐藏快照层（保留半透明底色降级）。
 *
 * <p>同一页面建议只用一个控制器：{@link #addBackdrop} 追加底栏快照层、
 * {@link #rebindSource} 把快照源换成页面滚动内容（避免把底栏自身画进快照），
 * 与 MainActivity 顶栏+底栏共用一份快照的做法一致。
 *
 * <p>设置页「启用高斯模糊」开关通过 {@link #isBlurEnabled}/{@link #setBlurEnabled}
 * 持久化并实时作用于所有存活的毛玻璃层与已登记的栏（{@link #registerBar}）。
 */
public final class BarBlurController {

    private static final float SNAPSHOT_SCALE = 0.25f;
    private static final float BLUR_RADIUS_DP = 22f;

    /** 设置页「启用高斯模糊」开关的偏好键（默认开）。 */
    public static final String KEY_BLUR_ENABLED = "blur_enabled";

    /** 存活的毛玻璃控制器，供设置页实时开关。 */
    private static final CopyOnWriteArrayList<WeakReference<BarBlurController>> LIVE =
            new CopyOnWriteArrayList<>();

    /** 登记的应用栏 / 导航栏：模糊关闭时改成与页面一致的不透明底色。 */
    private static final CopyOnWriteArrayList<WeakReference<View>> BARS =
            new CopyOnWriteArrayList<>();

    private final Activity activity;
    private final List<ImageView> backdrops = new ArrayList<>();
    private final Matrix matrix = new Matrix();
    private View snapshotSource;

    @Nullable
    private Bitmap snapshot;
    @Nullable
    private Canvas snapshotCanvas;
    @Nullable
    private RenderEffect blurEffect;
    private boolean updateScheduled;
    private boolean attached;
    private boolean prefEnabled = true;
    private int blurRadiusPx;
    private long lastDrawNanos;

    /** 高频屏（120Hz）上没必要每帧软绘整页：给快照刷新设一个 ~80fps 的上限。 */
    private static final long MIN_DRAW_INTERVAL_NANOS = 12_500_000L;

    private final Choreographer choreographer = Choreographer.getInstance();

    /** 按帧回调：滚动时每帧刷新一次快照（~60fps），停下后自然停止。 */
    private final Choreographer.FrameCallback frameCallback = frameTimeNanos -> {
        updateScheduled = false;
        updateNow();
    };

    private final ViewTreeObserver.OnScrollChangedListener scrollListener =
            this::scheduleUpdate;

    private final View.OnLayoutChangeListener layoutListener =
            (v, l, t, r, b, ol, ot, or, ob) -> scheduleUpdate();

    private BarBlurController(@NonNull Activity activity, @NonNull View snapshotSource) {
        this.activity = activity;
        this.snapshotSource = snapshotSource;
    }

    /**
     * 安装毛玻璃背景。API 31 以下或参数不完整时静默不启用。
     */
    @NonNull
    public static BarBlurController install(@NonNull Activity activity,
                                            @NonNull View snapshotSource,
                                            @NonNull ImageView... backdrops) {
        BarBlurController controller = new BarBlurController(activity, snapshotSource);
        for (ImageView backdrop : backdrops) {
            if (backdrop != null) controller.backdrops.add(backdrop);
        }
        controller.attach();
        return controller;
    }

    /** 追加一个快照层（与已有层共用同一份快照，供页面底栏使用）。 */
    public void addBackdrop(@NonNull ImageView backdrop) {
        if (backdrops.contains(backdrop)) return;
        backdrops.add(backdrop);
        if (!attached || blurEffect == null) return;
        backdrop.setRenderEffect(blurEffect);
        backdrop.setVisibility(prefEnabled ? View.VISIBLE : View.GONE);
        scheduleUpdate();
    }

    /** 切换快照源（例如从页面容器换成滚动内容，避免把底栏快照层画进快照里）。 */
    public void rebindSource(@NonNull View source) {
        if (source == snapshotSource) return;
        if (attached) {
            snapshotSource.getViewTreeObserver().removeOnScrollChangedListener(scrollListener);
            snapshotSource.removeOnLayoutChangeListener(layoutListener);
        }
        snapshotSource = source;
        if (snapshot != null) {
            snapshot.recycle();
            snapshot = null;
            snapshotCanvas = null;
        }
        if (attached) {
            source.getViewTreeObserver().addOnScrollChangedListener(scrollListener);
            source.addOnLayoutChangeListener(layoutListener);
            scheduleUpdate();
        }
    }

    /** 高斯模糊开关是否开启（默认开）。 */
    public static boolean isBlurEnabled(@NonNull Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(KEY_BLUR_ENABLED, true);
    }

    /** 实时切换所有存活的毛玻璃层，并持久化开关。 */
    public static void setBlurEnabled(@NonNull Context context, boolean enabled) {
        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putBoolean(KEY_BLUR_ENABLED, enabled).apply();
        for (WeakReference<BarBlurController> reference : LIVE) {
            BarBlurController controller = reference.get();
            if (controller == null) {
                LIVE.remove(reference);
            } else {
                controller.setEnabled(enabled);
            }
        }
        for (WeakReference<View> reference : BARS) {
            View bar = reference.get();
            if (bar == null) {
                BARS.remove(reference);
            } else {
                applyBarBackground(context, bar);
            }
        }
    }

    /**
     * 登记应用栏 / 导航栏：开启模糊时半透明（surface_bar），
     * 关闭模糊时用与 App 背景一致的不透明底色（surface_primary），而不是半透明。
     */
    public static void registerBar(@NonNull Context context, @NonNull View bar) {
        for (WeakReference<View> reference : BARS) {
            if (reference.get() == null) BARS.remove(reference);
        }
        BARS.add(new WeakReference<>(bar));
        applyBarBackground(context, bar);
    }

    private static void applyBarBackground(@NonNull Context context, @NonNull View bar) {
        // 只有在“模糊可用”时才用半透明底色（API 31+ 且有快照层做毛玻璃）；
        // 低版本无法渲染 RenderEffect，半透明会直接透出背后的内容（图片尤其明显），
        // 此时按关闭模糊处理，用与页面一致的不透明主题底色。
        boolean frosted = isBlurEnabled(context) && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S;
        int color = context.getColor(frosted
                ? R.color.surface_bar : R.color.surface_primary);
        bar.setBackgroundColor(color);
        bar.setBackgroundTintList(android.content.res.ColorStateList.valueOf(color));
    }

    private void attach() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) return;
        if (snapshotSource.getWidth() == 0 || snapshotSource.getHeight() == 0) {
            snapshotSource.post(this::attach);
            return;
        }
        float radius = BLUR_RADIUS_DP * activity.getResources().getDisplayMetrics().density;
        blurRadiusPx = Math.round(radius);
        blurEffect = RenderEffect.createBlurEffect(radius, radius,
                Shader.TileMode.CLAMP);
        prefEnabled = isBlurEnabled(activity);
        for (ImageView backdrop : backdrops) {
            backdrop.setRenderEffect(blurEffect);
            backdrop.setVisibility(prefEnabled ? View.VISIBLE : View.GONE);
        }
        attached = true;
        LIVE.add(new WeakReference<>(this));
        snapshotSource.getViewTreeObserver().addOnScrollChangedListener(scrollListener);
        snapshotSource.addOnLayoutChangeListener(layoutListener);
        activity.getWindow().getDecorView().post(this::scheduleUpdate);
    }

    /** 实时开关：隐藏/显示快照层，重新开启时立即刷新一帧。 */
    public void setEnabled(boolean enabled) {
        prefEnabled = enabled;
        if (!attached) return;
        for (ImageView backdrop : backdrops) {
            backdrop.setVisibility(enabled ? View.VISIBLE : View.GONE);
        }
        if (enabled) scheduleUpdate();
    }

    /** 内容滚动/布局变化后按帧刷新快照（滚动时 ~60fps，停下后自动停止）。 */
    public void scheduleUpdate() {
        if (!attached || !prefEnabled || updateScheduled) return;
        updateScheduled = true;
        choreographer.postFrameCallback(frameCallback);
    }

    private void updateNow() {
        updateScheduled = false;
        if (!attached || !prefEnabled) return;
        long now = System.nanoTime();
        if (now - lastDrawNanos < MIN_DRAW_INTERVAL_NANOS) {
            // 排到下一帧再试：既限流又保证停手时最终状态一定会绘制
            updateScheduled = true;
            choreographer.postFrameCallback(frameCallback);
            return;
        }
        lastDrawNanos = now;
        int width = snapshotSource.getWidth();
        int height = snapshotSource.getHeight();
        if (width <= 0 || height <= 0) return;

        int snapshotWidth = Math.max(1, Math.round(width * SNAPSHOT_SCALE));
        int snapshotHeight = Math.max(1, Math.round(height * SNAPSHOT_SCALE));
        if (snapshot == null || snapshot.getWidth() != snapshotWidth
                || snapshot.getHeight() != snapshotHeight) {
            if (snapshot != null) snapshot.recycle();
            snapshot = Bitmap.createBitmap(snapshotWidth, snapshotHeight, Bitmap.Config.ARGB_8888);
            snapshotCanvas = new Canvas(snapshot);
            for (ImageView backdrop : backdrops) {
                backdrop.setImageBitmap(snapshot);
            }
        }

        // 每帧先擦干净画布再重绘。快照源可能没有不透明背景（如私信/动态详情页的滚动内容，
        // 背景在 Fragment 根视图上），不擦除的话历史帧会一层层叠加，滚动后留下拖影/残影。
        snapshot.eraseColor(0);

        int[] sourceLocation = new int[2];
        int[] backdropLocation = new int[2];
        snapshotSource.getLocationInWindow(sourceLocation);

        // 只软绘各快照层覆盖的那几条 + 模糊半径：每帧绘制整页是列表滚动卡顿的主因。
        snapshotCanvas.save();
        snapshotCanvas.scale(SNAPSHOT_SCALE, SNAPSHOT_SCALE);
        Rect clip = null;
        for (ImageView backdrop : backdrops) {
            backdrop.getLocationInWindow(backdropLocation);
            int left = backdropLocation[0] - sourceLocation[0];
            int top = backdropLocation[1] - sourceLocation[1];
            int right = left + backdrop.getWidth();
            int bottom = top + backdrop.getHeight();
            Rect band = new Rect(left - blurRadiusPx, top - blurRadiusPx,
                    right + blurRadiusPx, bottom + blurRadiusPx);
            if (clip == null) {
                clip = band;
            } else {
                clip.union(band);
            }
        }
        if (clip != null) snapshotCanvas.clipRect(clip);
        try {
            snapshotSource.draw(snapshotCanvas);
        } catch (Exception exception) {
            // 个别子视图（如 WebView）在软绘下可能异常，忽略即可
            android.util.Log.w("WGProBlur", "snapshot draw failed", exception);
        }
        snapshotCanvas.restore();

        for (ImageView backdrop : backdrops) {
            backdrop.getLocationInWindow(backdropLocation);
            // 位图是 1/4 尺寸：先放大回原尺寸，再平移，让工具栏所在区域对齐
            matrix.setScale(1f / SNAPSHOT_SCALE, 1f / SNAPSHOT_SCALE);
            matrix.postTranslate(
                    -(backdropLocation[0] - sourceLocation[0]),
                    -(backdropLocation[1] - sourceLocation[1]));
            backdrop.setImageMatrix(matrix);
            backdrop.invalidate();
        }
    }
}
