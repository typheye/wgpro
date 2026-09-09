package com.typheye.wgpro.ui.widget;

import android.content.Context;
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
        background.setStroke(Math.max(dp(context, 1), Math.round(size * 0.1f)), ringColor);
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
