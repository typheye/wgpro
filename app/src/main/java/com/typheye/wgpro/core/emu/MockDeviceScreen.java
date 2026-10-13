package com.typheye.wgpro.core.emu;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

/**
 * 把模拟引擎的画面画到 SurfaceView 上。
 *
 * <p>数据来源是 {@link RfbClient}（引擎侧 QEMU 的本地 VNC 输出）：收到一帧 ARGB 像素就
 * 更新 Bitmap 并重绘；未收到帧时显示占位文字。等比缩放铺满可用区域。</p>
 */
public final class MockDeviceScreen implements RfbClient.Listener, SurfaceHolder.Callback {

    private final SurfaceView surface;
    private final View placeholder;
    private final RfbClient client = new RfbClient(this);
    private final Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);

    private Bitmap bitmap;
    private int frameWidth;
    private int frameHeight;
    private boolean haveFrame;

    public MockDeviceScreen(@NonNull SurfaceView surface, @NonNull View placeholder) {
        this.surface = surface;
        this.placeholder = placeholder;
        surface.getHolder().addCallback(this);
    }

    public void start(@NonNull String host, int port) {
        android.util.Log.i("MockDevice", "screen connect " + host + ":" + port);
        stop();
        haveFrame = false;
        placeholder.setVisibility(View.VISIBLE);
        // 引擎启动与 VNC 监听之间有竞态，这里重试等待（最多 ~15 秒）
        client.connectWithRetry(host, port, 30, 500);
    }

    public void stop() {
        client.close();
        haveFrame = false;
    }

    /** 清掉上一帧并回到占位状态（重启引擎时避免残留旧画面）。 */
    public void clearFrame() {
        haveFrame = false;
        bitmap = null;
        frameWidth = 0;
        frameHeight = 0;
        placeholder.setVisibility(View.VISIBLE);
        SurfaceHolder holder = surface.getHolder();
        if (holder.getSurface().isValid()) {
            Canvas canvas = null;
            try {
                canvas = holder.lockCanvas();
                if (canvas != null) canvas.drawColor(0xFF000000);
            } catch (Exception ignored) {
            } finally {
                if (canvas != null) {
                    try {
                        holder.unlockCanvasAndPost(canvas);
                    } catch (Exception ignored) {
                    }
                }
            }
        }
    }

    public boolean hasFrame() { return haveFrame; }

    // ---- RfbClient.Listener ----

    @Override
    public void onSize(int width, int height) {
        if (width <= 0 || height <= 0) return;
        if (bitmap == null || frameWidth != width || frameHeight != height) {
            frameWidth = width;
            frameHeight = height;
            bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        }
    }

    @Override
    public void onFrame(@NonNull int[] pixels, int width, int height) {
        if (bitmap == null || frameWidth != width || frameHeight != height) onSize(width, height);
        if (bitmap == null || pixels.length < width * height) return;
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        haveFrame = true;
        if (placeholder.getVisibility() == View.VISIBLE) placeholder.setVisibility(View.GONE);
        draw();
        android.util.Log.i("MockScreen", "frame " + width + "x" + height);
    }

    @Override
    public void onClosed(String reason) {
        android.util.Log.i("MockScreen", "closed: " + reason);
    }

    // ---- SurfaceHolder.Callback ----

    @Override public void surfaceCreated(@NonNull SurfaceHolder holder) { draw(); }

    @Override public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) { draw(); }

    @Override public void surfaceDestroyed(@NonNull SurfaceHolder holder) { }

    private void draw() {
        if (bitmap == null || !surface.getHolder().getSurface().isValid()) return;
        int viewWidth = surface.getWidth();
        int viewHeight = surface.getHeight();
        if (viewWidth <= 0 || viewHeight <= 0) return;
        float scale = Math.min(viewWidth / (float) frameWidth, viewHeight / (float) frameHeight);
        int drawWidth = Math.max(1, Math.round(frameWidth * scale));
        int drawHeight = Math.max(1, Math.round(frameHeight * scale));
        int left = (viewWidth - drawWidth) / 2;
        int top = (viewHeight - drawHeight) / 2;
        Canvas canvas = null;
        try {
            canvas = surface.getHolder().lockCanvas();
            if (canvas == null) return;
            canvas.drawColor(0xFF000000);
            canvas.drawBitmap(bitmap, null, new Rect(left, top, left + drawWidth, top + drawHeight), paint);
        } catch (Exception ignored) {
        } finally {
            if (canvas != null) {
                try {
                    surface.getHolder().unlockCanvasAndPost(canvas);
                } catch (Exception ignored) {
                }
            }
        }
    }
}
