package com.typheye.wgpro.ui.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.typheye.wgpro.R;

/** A full-width, bottom-aligned MD3 surface used for transient app actions. */
public final class WGProBottomSheet {
    private WGProBottomSheet() { }

    public static BottomSheetDialog showMessage(Context context, String title, String message,
                                                String positive, @Nullable View.OnClickListener listener) {
        BottomSheetDialog dialog = new BottomSheetDialog(context);
        LinearLayout content = new LinearLayout(context);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(dp(context, 24), dp(context, 26), dp(context, 24), dp(context, 12));
        GradientDrawable background = new GradientDrawable();
        background.setColor(context.getColor(R.color.surface_elevated));
        background.setCornerRadii(new float[]{dp(context, 28), dp(context, 28), dp(context, 28), dp(context, 28), 0, 0, 0, 0});
        content.setBackground(background);

        TextView heading = new TextView(context);
        heading.setText(title);
        heading.setTextColor(context.getColor(R.color.text_primary));
        heading.setTextSize(24);
        heading.setTypeface(null, android.graphics.Typeface.BOLD);
        content.addView(heading, new LinearLayout.LayoutParams(-1, -2));

        TextView body = new TextView(context);
        body.setText(message);
        body.setTextColor(context.getColor(R.color.text_secondary));
        body.setTextSize(16);
        body.setLineSpacing(dp(context, 3), 1f);
        LinearLayout.LayoutParams bodyParams = new LinearLayout.LayoutParams(-1, -2);
        bodyParams.topMargin = dp(context, 14);
        content.addView(body, bodyParams);

        MaterialButton action = new MaterialButton(context, null, com.google.android.material.R.attr.materialButtonOutlinedStyle);
        action.setText(positive);
        action.setTextColor(ColorStateList.valueOf(context.getColor(R.color.brand_primary)));
        action.setOnClickListener(v -> {
            if (listener != null) listener.onClick(v);
            dialog.dismiss();
        });
        LinearLayout.LayoutParams actionParams = new LinearLayout.LayoutParams(-2, -2);
        actionParams.gravity = Gravity.END;
        actionParams.topMargin = dp(context, 18);
        content.addView(action, actionParams);
        dialog.setContentView(content);
        dialog.setOnShowListener(ignored -> configure(dialog, content));
        dialog.show();
        configure(dialog, content);
        return dialog;
    }

    private static void configure(BottomSheetDialog dialog, View content) {
        Window window = dialog.getWindow();
        if (window == null) return;
        WindowCompat.setDecorFitsSystemWindows(window, false);
        // Match the sheet surface behind the gesture handle; transparent bars are
        // rendered black by some Android 15/16 window managers.
        window.setNavigationBarColor(content.getContext().getColor(R.color.surface_elevated));
        window.getDecorView().setBackgroundColor(content.getContext().getColor(R.color.surface_elevated));
        if (android.os.Build.VERSION.SDK_INT >= 29) window.setNavigationBarContrastEnforced(false);
        window.getDecorView().setSystemUiVisibility(window.getDecorView().getSystemUiVisibility()
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        window.setNavigationBarDividerColor(Color.TRANSPARENT);
        window.setDimAmount(0.68f);
        ViewCompat.setOnApplyWindowInsetsListener(content, (v, insets) -> {
            int bottom = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
            v.setPadding(v.getPaddingLeft(), v.getPaddingTop(), v.getPaddingRight(), bottom + dp(v.getContext(), 12));
            return insets;
        });
        ViewCompat.requestApplyInsets(content);
        View sheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if (sheet != null) {
            sheet.setFitsSystemWindows(false);
            sheet.setBackgroundColor(Color.TRANSPARENT);
            BottomSheetBehavior<?> behavior = BottomSheetBehavior.from(sheet);
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            behavior.setSkipCollapsed(true);
        }
    }

    private static int dp(Context context, int value) {
        return (int) (value * context.getResources().getDisplayMetrics().density + 0.5f);
    }
}
