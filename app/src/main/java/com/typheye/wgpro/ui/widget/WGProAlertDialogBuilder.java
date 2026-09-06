package com.typheye.wgpro.ui.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.WindowCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.typheye.wgpro.R;

/** Alert-like API backed by a real custom Material bottom sheet. */
public final class WGProAlertDialogBuilder {
    private final Context context;
    private CharSequence title, message, positiveText, negativeText, neutralText;
    private DialogInterface.OnClickListener positiveListener, negativeListener, neutralListener, itemListener;
    private CharSequence[] items;
    private View customView;
    private boolean cancelable = true;

    public WGProAlertDialogBuilder(@NonNull Context context) { this.context = context; }
    public WGProAlertDialogBuilder setTitle(CharSequence v) { title = v; return this; }
    public WGProAlertDialogBuilder setTitle(int v) { return setTitle(context.getText(v)); }
    public WGProAlertDialogBuilder setMessage(CharSequence v) { message = v; return this; }
    public WGProAlertDialogBuilder setMessage(int v) { return setMessage(context.getText(v)); }
    public WGProAlertDialogBuilder setView(View v) { customView = v; return this; }
    public WGProAlertDialogBuilder setCancelable(boolean v) { cancelable = v; return this; }
    public WGProAlertDialogBuilder setItems(CharSequence[] v, @Nullable DialogInterface.OnClickListener l) { items = v; itemListener = l; return this; }
    public WGProAlertDialogBuilder setPositiveButton(CharSequence v, @Nullable DialogInterface.OnClickListener l) { positiveText = v; positiveListener = l; return this; }
    public WGProAlertDialogBuilder setPositiveButton(int v, @Nullable DialogInterface.OnClickListener l) { return setPositiveButton(context.getText(v), l); }
    public WGProAlertDialogBuilder setNegativeButton(CharSequence v, @Nullable DialogInterface.OnClickListener l) { negativeText = v; negativeListener = l; return this; }
    public WGProAlertDialogBuilder setNegativeButton(int v, @Nullable DialogInterface.OnClickListener l) { return setNegativeButton(context.getText(v), l); }
    public WGProAlertDialogBuilder setNeutralButton(CharSequence v, @Nullable DialogInterface.OnClickListener l) { neutralText = v; neutralListener = l; return this; }
    public WGProAlertDialogBuilder setNeutralButton(int v, @Nullable DialogInterface.OnClickListener l) { return setNeutralButton(context.getText(v), l); }

    public WGProBottomSheetDialog create() {
        WGProBottomSheetDialog dialog = new WGProBottomSheetDialog(context);
        dialog.setCancelable(cancelable);
        dialog.setCanceledOnTouchOutside(cancelable);
        dialog.setContentView(buildContent(dialog));
        dialog.setOnShowListener(ignored -> configureWindow(dialog));
        return dialog;
    }

    public WGProBottomSheetDialog show() {
        WGProBottomSheetDialog dialog = create();
        dialog.show();
        configureWindow(dialog);
        return dialog;
    }

    private View buildContent(WGProBottomSheetDialog dialog) {
        LinearLayout panel = new LinearLayout(context);
        panel.setOrientation(LinearLayout.VERTICAL);
        panel.setPadding(dp(24), dp(26), dp(24), dp(12));
        GradientDrawable background = new GradientDrawable();
        background.setColor(context.getColor(R.color.surface_elevated));
        float radius = dp(28);
        background.setCornerRadii(new float[]{radius, radius, radius, radius, 0, 0, 0, 0});
        panel.setBackground(background);
        ViewCompat.setOnApplyWindowInsetsListener(panel, (view, insets) -> {
            int navigationBottom = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
            view.setPadding(dp(24), dp(26), dp(24), dp(12) + navigationBottom);
            return insets;
        });
        if (title != null && title.length() > 0) {
            TextView heading = text(title, 24, R.color.text_primary);
            heading.setTypeface(null, Typeface.BOLD);
            panel.addView(heading, params(-1, -2, 0));
        }
        if (message != null && message.length() > 0) {
            TextView body = text(message, 16, R.color.text_secondary);
            body.setLineSpacing(dp(3), 1f);
            panel.addView(body, params(-1, -2, 14));
        }
        if (customView != null) {
            if (customView.getParent() instanceof ViewGroup) ((ViewGroup) customView.getParent()).removeView(customView);
            panel.addView(customView, params(-1, -2, 12));
        }
        if (items != null) {
            LinearLayout list = new LinearLayout(context);
            list.setOrientation(LinearLayout.VERTICAL);
            for (int i = 0; i < items.length; i++) {
                final int index = i;
                TextView row = text(items[i], 17, R.color.text_primary);
                row.setGravity(Gravity.CENTER_VERTICAL);
                row.setMinHeight(dp(54));
                int selectable = resolveSelectableBackground();
                if (selectable != 0) row.setBackgroundResource(selectable);
                row.setOnClickListener(v -> { if (itemListener != null) itemListener.onClick(dialog, index); dialog.dismiss(); });
                list.addView(row, new LinearLayout.LayoutParams(-1, dp(54)));
            }
            ScrollView scroll = new ScrollView(context);
            scroll.addView(list);
            panel.addView(scroll, params(-1, -2, 10));
        }
        if (neutralText != null || negativeText != null || positiveText != null) {
            LinearLayout actions = new LinearLayout(context);
            actions.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
            if (neutralText != null) actions.addView(button(dialog, neutralText, neutralListener, DialogInterface.BUTTON_NEUTRAL));
            if (negativeText != null) actions.addView(button(dialog, negativeText, negativeListener, DialogInterface.BUTTON_NEGATIVE));
            if (positiveText != null) actions.addView(button(dialog, positiveText, positiveListener, DialogInterface.BUTTON_POSITIVE));
            panel.addView(actions, params(-1, -2, 18));
        }
        return panel;
    }

    private MaterialButton button(WGProBottomSheetDialog dialog, CharSequence label, @Nullable DialogInterface.OnClickListener listener, int which) {
        MaterialButton button = new MaterialButton(context, null, android.R.attr.borderlessButtonStyle);
        button.setText(label);
        button.setTextColor(ColorStateList.valueOf(context.getColor(R.color.brand_primary)));
        button.setOnClickListener(v -> { if (listener != null) listener.onClick(dialog, which); dialog.dismiss(); });
        dialog.registerButton(which, button);
        return button;
    }

    private void configureWindow(BottomSheetDialog dialog) {
        Window window = dialog.getWindow();
        if (window == null) return;
        int surface = context.getColor(R.color.surface_elevated);
        WindowCompat.setDecorFitsSystemWindows(window, false);
        window.setNavigationBarColor(surface);
        window.setNavigationBarDividerColor(surface);
        if (android.os.Build.VERSION.SDK_INT >= 29) window.setNavigationBarContrastEnforced(false);
        window.setDimAmount(0.68f);
        View sheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if (sheet != null) {
            sheet.setFitsSystemWindows(false);
            sheet.setBackgroundColor(Color.TRANSPARENT);
            BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(sheet);
            behavior.setSkipCollapsed(true);
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            ViewCompat.requestApplyInsets(sheet);
        }
    }

    private TextView text(CharSequence value, int size, int color) {
        TextView view = new TextView(context);
        view.setText(value);
        view.setTextSize(size);
        view.setTextColor(context.getColor(color));
        return view;
    }
    private LinearLayout.LayoutParams params(int width, int height, int top) {
        LinearLayout.LayoutParams value = new LinearLayout.LayoutParams(width, height);
        value.topMargin = dp(top);
        return value;
    }
    private int resolveSelectableBackground() {
        android.util.TypedValue value = new android.util.TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.selectableItemBackground, value, true);
        return value.resourceId;
    }
    private int dp(int value) { return (int) (value * context.getResources().getDisplayMetrics().density + 0.5f); }
}
