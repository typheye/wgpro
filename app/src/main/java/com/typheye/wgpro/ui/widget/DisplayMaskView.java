package com.typheye.wgpro.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 显示遮罩：**全屏**铺一层不透明色，并把"显示分辨率大小和形状"的洞挖出来。
 *
 * <p>作用：让底层的模拟器画面只在洞内可见（方角被裁掉），洞的形状由调用方给出
 * （圆形/圆角矩形），与客机显示区域严格等大。</p>
 */
public class DisplayMaskView extends View {

    private final RectF hole = new RectF();
    private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float radiusRatio = 0.5f;
    private boolean holeSet;

    public DisplayMaskView(Context context) {
        this(context, null);
    }

    public DisplayMaskView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    /** 设置洞的位置尺寸与圆角比例（0.5 = 圆形）。 */
    public void setHole(@NonNull RectF rect, float ratio) {
        hole.set(rect);
        radiusRatio = ratio;
        holeSet = true;
        invalidate();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if (!holeSet) {
            return;
        }
        int save = canvas.saveLayer(null, null);
        paint.setXfermode(null);
        paint.setColor(0xFF000000);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        if (radiusRatio >= 0.5f) {
            canvas.drawCircle(hole.centerX(), hole.centerY(), hole.width() / 2f, paint);
        } else {
            float r = hole.width() * radiusRatio;
            canvas.drawRoundRect(hole, r, r, paint);
        }
        paint.setXfermode(null);
        canvas.restoreToCount(save);
    }
}
