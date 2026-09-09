package com.typheye.wgpro.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.typheye.wgpro.R;

/** 通知列表头像右上角的红色数字未读徽标。 */
public final class UnreadBadgeFactory {
    private static final String TAG = "wgpro_unread_badge";

    private UnreadBadgeFactory() { }

    public static void bind(Context context, FrameLayout avatarBox, int count) {
        if (context == null || avatarBox == null) return;
        clear(avatarBox);
        if (count <= 0) return;

        avatarBox.setClipChildren(false);
        avatarBox.setClipToOutline(false);
        TextView badge = new TextView(context);
        badge.setTag(TAG);
        badge.setGravity(Gravity.CENTER);
        badge.setTextColor(Color.WHITE);
        badge.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        badge.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        badge.setIncludeFontPadding(false);
        badge.setMinWidth(dp(context, 18));
        badge.setMinHeight(dp(context, 18));
        badge.setPadding(dp(context, 5), 0, dp(context, 5), 0);
        badge.setText(count > 99 ? "99+" : String.valueOf(count));

        GradientDrawable background = new GradientDrawable();
        background.setShape(GradientDrawable.OVAL);
        background.setColor(context.getColor(R.color.status_danger));
        badge.setBackground(background);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT, dp(context, 18),
                Gravity.TOP | Gravity.END);
        avatarBox.addView(badge, params);
    }

    public static void clear(FrameLayout avatarBox) {
        if (avatarBox == null) return;
        for (int i = avatarBox.getChildCount() - 1; i >= 0; i--) {
            View child = avatarBox.getChildAt(i);
            if (TAG.equals(child.getTag())) avatarBox.removeViewAt(i);
        }
    }

    private static int dp(Context context, int value) {
        return Math.round(value * context.getResources().getDisplayMetrics().density);
    }
}
