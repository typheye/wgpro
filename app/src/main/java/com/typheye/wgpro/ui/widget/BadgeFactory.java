package com.typheye.wgpro.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.typheye.wgpro.R;

import org.json.JSONObject;

/**
 * 统一认证徽标：developer（黄橙 <>）、creator（青绿 √）、honor（粉色奖牌）。
 * 云端只返回一个 badge_type，客户端只显示一个徽标。
 */
public final class BadgeFactory {
    private static final String TAG = "wgpro_badge";
    private static final int TYPE_DEVELOPER = 1;
    private static final int TYPE_CREATOR = 2;
    private static final int TYPE_HONOR = 3;

    private BadgeFactory() { }

    public static void bind(Context context, JSONObject item, FrameLayout avatarBox) {
        bind(context, item, avatarBox, context.getColor(R.color.surface_primary));
    }

    public static void bind(Context context, JSONObject item, FrameLayout avatarBox,
                            int ringColor) {
        if (context == null || item == null || avatarBox == null) return;
        clearBadges(avatarBox);
        int type = type(item);
        if (type == 0) return;
        int avatarSize = avatarSize(avatarBox);
        int size = avatarSize > 0
                ? Math.max(dp(context, 12), Math.round(avatarSize / 3f))
                : dp(context, 18);
        avatarBox.setClipChildren(false);
        avatarBox.setClipToOutline(false);
        View badge = createBadge(context, type, ringColor, size);
        badge.setTag(TAG);
        FrameLayout.LayoutParams params = badgeParams(context, size);
        avatarBox.addView(badge, params);
        if (avatarSize <= 0) {
            avatarBox.post(() -> {
                int actual = Math.min(avatarBox.getWidth(), avatarBox.getHeight());
                if (actual <= 0 || !TAG.equals(badge.getTag())) return;
                int actualSize = Math.max(dp(context, 12), Math.round(actual / 3f));
                avatarBox.removeView(badge);
                View replacement = createBadge(context, type, ringColor, actualSize);
                replacement.setTag(TAG);
                avatarBox.addView(replacement, badgeParams(context, actualSize));
            });
        }
    }

    /**
     * 折叠态用户主页专用：徽标不绘制实线轮廓，头像下方与徽标边缘之间
     * 通过 bitmap 挖空透出背景图，避免黑色轮廓压在头像上。
     */
    public static void bindTransparentRing(Context context, JSONObject item, FrameLayout avatarBox) {
        bind(context, item, avatarBox, 0);
    }

    /**
     * 为折叠态头像生成带透明徽标隔离环的方形 bitmap。
     * 返回原图表示当前用户没有认证徽标。
     */
    public static Bitmap collapsedAvatar(Context context, Bitmap source, JSONObject item,
                                         int viewSizePx) {
        if (context == null || source == null || item == null || type(item) == 0) return source;
        int size = viewSizePx > 0 ? viewSizePx : dp(context, 32);
        Bitmap square = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(square);

        float scale = Math.max(size / (float) source.getWidth(),
                size / (float) source.getHeight());
        int cropWidth = Math.min(source.getWidth(), Math.round(size / scale));
        int cropHeight = Math.min(source.getHeight(), Math.round(size / scale));
        int left = Math.max(0, (source.getWidth() - cropWidth) / 2);
        int top = Math.max(0, (source.getHeight() - cropHeight) / 2);
        Rect src = new Rect(left, top, left + cropWidth, top + cropHeight);
        RectF dst = new RectF(0f, 0f, size, size);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(source, src, dst, paint);

        int badgeSize = Math.max(dp(context, 12), Math.round(size / 3f));
        int inset = dp(context, 1);
        int stroke = Math.max(dp(context, 1), Math.round(badgeSize * 0.1f));
        float centerX = size - inset - badgeSize / 2f;
        float centerY = size - inset - badgeSize / 2f;
        Paint clear = new Paint(Paint.ANTI_ALIAS_FLAG);
        clear.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        canvas.drawCircle(centerX, centerY, badgeSize / 2f + stroke, clear);
        clear.setXfermode(null);
        return square;
    }

    public static void bindProfileRow(Context context, JSONObject item,
                                      LinearLayout icons, TextView text, View row,
                                      int ringColor) {
        if (context == null || item == null || icons == null || text == null || row == null) return;
        int type = type(item);
        icons.removeAllViews();
        if (type == 0) {
            row.setVisibility(View.GONE);
            return;
        }
        row.setVisibility(View.VISIBLE);
        int size = dp(context, 18);
        icons.addView(createBadge(context, type, ringColor, size),
                new LinearLayout.LayoutParams(size, size));
        String description = item.optString("verification_description", "").trim();
        text.setText(description.isEmpty() ? label(type) : description);
    }

    /** 返回当前徽标的背景色，用于用户详情页展开态的小徽标轮廓。 */
    public static int badgeColor(JSONObject item) {
        int type = type(item);
        return type == 0 ? 0xFF9E9E9E : colorFor(type);
    }

    private static FrameLayout.LayoutParams badgeParams(Context context, int size) {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                size, size, Gravity.END | Gravity.BOTTOM);
        int inset = dp(context, 1);
        params.setMargins(0, 0, inset, inset);
        return params;
    }

    private static void clearBadges(FrameLayout avatarBox) {
        for (int i = avatarBox.getChildCount() - 1; i >= 0; i--) {
            View child = avatarBox.getChildAt(i);
            if (TAG.equals(child.getTag())) avatarBox.removeViewAt(i);
        }
    }

    private static int type(JSONObject item) {
        String badge = item.optString("badge_type", "").trim();
        if ("developer".equalsIgnoreCase(badge)) return TYPE_DEVELOPER;
        if ("creator".equalsIgnoreCase(badge)) return TYPE_CREATOR;
        if ("honor".equalsIgnoreCase(badge)) return TYPE_HONOR;
        return 0;
    }

    private static View createBadge(Context context, int type, int ringColor, int size) {
        FrameLayout badge = new FrameLayout(context);
        GradientDrawable background = new GradientDrawable();
        background.setShape(GradientDrawable.OVAL);
        background.setColor(colorFor(type));
        if (ringColor != 0) {
            background.setStroke(Math.max(dp(context, 1), Math.round(size * 0.1f)), ringColor);
        }
        badge.setBackground(background);

        ImageView icon = new ImageView(context);
        icon.setImageResource(iconFor(type));
        icon.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        int iconSize = Math.max(dp(context, 8), Math.round(size * 0.58f));
        badge.addView(icon, new FrameLayout.LayoutParams(iconSize, iconSize, Gravity.CENTER));
        badge.setLayoutParams(new FrameLayout.LayoutParams(size, size));
        return badge;
    }

    private static int avatarSize(FrameLayout avatarBox) {
        ViewGroup.LayoutParams params = avatarBox.getLayoutParams();
        if (params != null && params.width > 0 && params.height > 0) {
            return Math.min(params.width, params.height);
        }
        if (avatarBox.getWidth() > 0 && avatarBox.getHeight() > 0) {
            return Math.min(avatarBox.getWidth(), avatarBox.getHeight());
        }
        return 0;
    }

    private static int colorFor(int type) {
        if (type == TYPE_DEVELOPER) return 0xFFF5A623;
        if (type == TYPE_CREATOR) return 0xFF20B8A6;
        return 0xFFF06292;
    }

    private static int iconFor(int type) {
        if (type == TYPE_DEVELOPER) return R.drawable.ic_badge_developer_white;
        if (type == TYPE_CREATOR) return R.drawable.ic_badge_creator_white;
        return R.drawable.ic_badge_honor_white;
    }

    private static String label(int type) {
        if (type == TYPE_DEVELOPER) return "开发者认证";
        if (type == TYPE_CREATOR) return "创作者认证";
        return "荣誉认证";
    }

    private static int dp(Context context, int value) {
        return Math.round(value * context.getResources().getDisplayMetrics().density);
    }
}
