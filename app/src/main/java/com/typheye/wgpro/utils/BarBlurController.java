package com.typheye.wgpro.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
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

import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 应用栏 / 导航栏的毛玻璃背景（可直接渲染的稳定版本）。
 *
 * <p>把内容视图按 1/4 缩小软绘成快照位图，交给位于栏下方的 {@link ImageView}
 * 用 {@link RenderEffect} 高斯模糊绘制；滚动/翻页/布局变化时按帧刷新（~60fps）。
 * 仅 API 31+ 启用，低版本隐藏快照层（保留半透明底色降级）。
 *
 * <p>设置页「启用高斯模糊」开关通过 {@link #isBlurEnabled}/{@link #setBlurEnabled}
 * 持久化并实时作用于所有存活的毛玻璃层。
 */
public final class BarBlurController {

    private static final float SNAPSHOT_SCALE = 0.25f;
    private static final float BLUR_RADIUS_DP = 22f;

    /** 设置页「启用高斯模糊」开关的偏好键（默认开）。 */
    public static final String KEY_BLUR_ENABLED = "blur_enabled";

    /** 存活的毛玻璃控制器，供设置页实时开关。 */
    private static final CopyOnWriteArrayList<WeakReference<BarBlurController>> LIVE =
            new CopyOnWriteArrayList<>();

    private final Activity activity;
    private final View snapshotSource;
    private final ImageView[] backdrops;
    private final Matrix matrix = new Matrix();

    @Nullable
    private Bitmap snapshot;
    @Nullable
    private Canvas snapshotCanvas;
    private boolean updateScheduled;
    private boolean attached;
    private boolean prefEnabled = true;

    private final Choreographer choreographer = Choreographer.getInstance();

    /** 按帧回调：滚动时每帧刷新一次快照（~60fps），停下后自然停止。 */
    private final Choreographer.FrameCallback frameCallback = frameTimeNanos -> {
        updateScheduled = false;
        updateNow();
    };

    private final ViewTreeObserver.OnScrollChangedListener scrollListener =
            this::scheduleUpdate;

    private BarBlurController(@NonNull Activity activity, @NonNull View snapshotSource,
                              @NonNull ImageView... backdrops) {
        this.activity = activity;
        this.snapshotSource = snapshotSource;
        this.backdrops = backdrops;
    }

    /**
     * 安装毛玻璃背景。API 31 以下或参数不完整时静默不启用。
     */
    @NonNull
    public static BarBlurController install(@NonNull Activity activity,
                                            @NonNull View snapshotSource,
                                            @NonNull ImageView... backdrops) {
        BarBlurController controller =
                new BarBlurController(activity, snapshotSource, backdrops);
        controller.attach();
        return controller;
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
    }

    private void attach() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) return;
        if (snapshotSource.getWidth() == 0 || snapshotSource.getHeight() == 0) {
            snapshotSource.post(this::attach);
            return;
        }
        float radius = BLUR_RADIUS_DP * activity.getResources().getDisplayMetrics().density;
        RenderEffect effect = RenderEffect.createBlurEffect(radius, radius,
                Shader.TileMode.CLAMP);
        prefEnabled = isBlurEnabled(activity);
        for (ImageView backdrop : backdrops) {
            backdrop.setRenderEffect(effect);
            backdrop.setVisibility(prefEnabled ? View.VISIBLE : View.GONE);
        }
        attached = true;
        LIVE.add(new WeakReference<>(this));
        snapshotSource.getViewTreeObserver().addOnScrollChangedListener(scrollListener);
        snapshotSource.addOnLayoutChangeListener((v, l, t, r, b, ol, ot, or, ob) -> scheduleUpdate());
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

        snapshotCanvas.save();
        snapshotCanvas.scale(SNAPSHOT_SCALE, SNAPSHOT_SCALE);
        try {
            snapshotSource.draw(snapshotCanvas);
        } catch (Exception ignored) {
            // 个别子视图（如 WebView）在软绘下可能异常，忽略即可
        }
        snapshotCanvas.restore();

        int[] sourceLocation = new int[2];
        int[] backdropLocation = new int[2];
        snapshotSource.getLocationInWindow(sourceLocation);
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
