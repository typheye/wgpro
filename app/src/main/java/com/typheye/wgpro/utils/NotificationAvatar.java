package com.typheye.wgpro.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 通知图标工具。
 *
 * 通知左侧的大图标（主图标）用于展示消息发送者：优先真实头像，
 * 没有头像时用昵称首字生成圆形文字头像；小图标（副图标）保持腕管 Logo。
 */
public final class NotificationAvatar {
    private static final int SIZE = 160;
    private static final int[] COLORS = {
            0xFFE8833A, 0xFF4C8DFF, 0xFF46B47A, 0xFFB15CD6, 0xFFE05C6E, 0xFF3FA9A0,
    };

    private NotificationAvatar() {
    }

    /** 公开头像地址：没有自定义头像时返回 404，加载失败会保持文字头像。 */
    @NonNull
    public static String avatarUrlFor(@Nullable String uid) {
        if (uid == null || uid.trim().isEmpty()) return "";
        return "https://service.typheye.cn/src/pericon/" + uid.trim();
    }

    /** 昵称首字文字头像（圆形）。 */
    @Nullable
    public static Bitmap textAvatar(@Nullable String name) {
        String label = initial(name);
        try {
            Bitmap bitmap = Bitmap.createBitmap(SIZE, SIZE, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(COLORS[Math.abs(label.hashCode()) % COLORS.length]);
            canvas.drawCircle(SIZE / 2f, SIZE / 2f, SIZE / 2f, paint);

            paint.setColor(Color.WHITE);
            paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            paint.setTextSize(SIZE * 0.46f);
            paint.setTextAlign(Paint.Align.CENTER);
            Rect bounds = new Rect();
            paint.getTextBounds(label, 0, label.length(), bounds);
            canvas.drawText(label, SIZE / 2f,
                    SIZE / 2f + (bounds.height() / 2f - bounds.bottom), paint);
            return bitmap;
        } catch (Exception error) {
            return null;
        }
    }

    /** 应用自身图标（腕管 Logo），用于系统消息。 */
    @Nullable
    public static Bitmap appIcon(@NonNull Context context) {
        try {
            Drawable drawable = context.getPackageManager()
                    .getApplicationIcon(context.getPackageName());
            Bitmap bitmap = Bitmap.createBitmap(SIZE, SIZE, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, SIZE, SIZE);
            drawable.draw(canvas);
            return bitmap;
        } catch (Exception error) {
            return null;
        }
    }

    private static String initial(String name) {
        String clean = name == null ? "" : name.trim();
        if (clean.isEmpty()) return "T";
        return clean.substring(0, 1).toUpperCase(java.util.Locale.getDefault());
    }
}
