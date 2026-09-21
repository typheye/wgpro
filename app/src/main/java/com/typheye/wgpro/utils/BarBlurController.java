package com.typheye.wgpro.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 应用栏 / 导航栏的毛玻璃背景（可直接渲染的稳定版本）。
 *
 * <p>把内容视图按 1/4 缩小软绘成快照位图，交给位于栏下方的 {@link ImageView}
 * 用 {@link RenderEffect} 高斯模糊绘制；滚动/翻页/布局变化时限频刷新。
 * 仅 API 31+ 启用，低版本隐藏快照层（保留半透明底色降级）。
 */
public final class BarBlurController {

    private static final float SNAPSHOT_SCALE = 0.25f;
    private static final float BLUR_RADIUS_DP = 22f;
    private static final long UPDATE_INTERVAL_MS = 120L;

    private final Activity activity;
    private final View snapshotSource;
    private final ImageView[] backdrops;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Matrix matrix = new Matrix();

    @Nullable
    private Bitmap snapshot;
    @Nullable
    private Canvas snapshotCanvas;
    private boolean updateScheduled;
    private boolean enabled;

    private final Runnable updater = this::updateNow;

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

    private void attach() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) return;
        if (snapshotSource.getWidth() == 0 || snapshotSource.getHeight() == 0) {
            snapshotSource.post(this::attach);
            return;
        }
        float radius = BLUR_RADIUS_DP * activity.getResources().getDisplayMetrics().density;
        RenderEffect effect = RenderEffect.createBlurEffect(radius, radius,
                Shader.TileMode.CLAMP);
        for (ImageView backdrop : backdrops) {
            backdrop.setVisibility(View.VISIBLE);
            backdrop.setRenderEffect(effect);
        }
        enabled = true;
        snapshotSource.getViewTreeObserver().addOnScrollChangedListener(scrollListener);
        snapshotSource.addOnLayoutChangeListener((v, l, t, r, b, ol, ot, or, ob) -> scheduleUpdate());
        activity.getWindow().getDecorView().post(this::scheduleUpdate);
    }

    /** 内容滚动/布局变化后限频刷新快照。 */
    public void scheduleUpdate() {
        if (!enabled || updateScheduled) return;
        updateScheduled = true;
        handler.postDelayed(updater, UPDATE_INTERVAL_MS);
    }

    private void updateNow() {
        updateScheduled = false;
        if (!enabled) return;
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
