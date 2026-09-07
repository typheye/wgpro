package com.typheye.wgpro.ui.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.WindowCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.typheye.wgpro.R;

/** Alert-like API backed by a real custom Material bottom sheet. */
public final class WGProAlertDialogBuilder {
    private static final int PANEL_BOTTOM_PADDING_DP = 39;
    private final Context context;
    private CharSequence title, message, positiveText, negativeText, neutralText;
    private DialogInterface.OnClickListener positiveListener, negativeListener, neutralListener, itemListener;
    private CharSequence[] items;
    private View customView;
    private boolean cancelable = true;
    private FixedSectionsLayout panel;
    private LinearLayout scrollingContent;
    private NestedScrollView contentScroll;
    private LinearLayout fixedActions;
    private View fixedTitle;

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
        dialog.setDismissWithAnimation(true);
        dialog.setContentView(buildContent(dialog));
        if (containsProgressIndicator(customView)) dialog.setMinimumShowDuration(300L);
        dialog.setWindowConfigurator(() -> configureWindow(dialog));
        return dialog;
    }

    public WGProBottomSheetDialog show() {
        WGProBottomSheetDialog dialog = create();
        dialog.show();
        WGProBottomSheetDialog shown = WGProBottomSheetDialog.activeFor(context);
        return shown == null ? dialog : shown;
    }

    private View buildContent(WGProBottomSheetDialog dialog) {
        panel = new FixedSectionsLayout(context);
        panel.setPadding(dp(24), dp(26), dp(24), dp(PANEL_BOTTOM_PADDING_DP));
        GradientDrawable background = new GradientDrawable();
        background.setColor(context.getColor(R.color.surface_elevated));
        float radius = dp(28);
        background.setCornerRadii(new float[]{radius, radius, radius, radius, 0, 0, 0, 0});
        panel.setBackground(background);
        ViewCompat.setOnApplyWindowInsetsListener(panel, (view, insets) -> {
            int navigationBottom = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
            view.setPadding(dp(24), dp(26), dp(24),
                    dp(PANEL_BOTTOM_PADDING_DP) + navigationBottom);
            return insets;
        });
        if (title != null && title.length() > 0) {
            TextView heading = text(title, 24, R.color.text_primary);
            heading.setTypeface(null, Typeface.BOLD);
            fixedTitle = heading;
            panel.addView(heading, new ViewGroup.LayoutParams(-1, -2));
        }

        contentScroll = new NestedScrollView(context);
        contentScroll.setClipToPadding(false);
        contentScroll.setFillViewport(false);
        contentScroll.setOverScrollMode(View.OVER_SCROLL_IF_CONTENT_SCROLLS);
        scrollingContent = new LinearLayout(context);
        scrollingContent.setOrientation(LinearLayout.VERTICAL);
        if (message != null && message.length() > 0) {
            TextView body = text(message, 16, R.color.text_secondary);
            body.setLineSpacing(dp(3), 1f);
            scrollingContent.addView(body, params(-1, -2, 0));
        }
        if (customView != null) {
            if (customView.getParent() instanceof ViewGroup) ((ViewGroup) customView.getParent()).removeView(customView);
            scrollingContent.addView(customView,
                    params(-1, -2, scrollingContent.getChildCount() == 0 ? 0 : 12));
        }
        if (items != null) {
            MaterialCardView group = new MaterialCardView(context);
            group.setRadius(dp(18));
            group.setCardElevation(0f);
            group.setStrokeWidth(0);
            group.setUseCompatPadding(false);
            group.setPreventCornerOverlap(false);
            group.setCardBackgroundColor(context.getColor(R.color.surface_secondary));
            group.setClipToOutline(true);

            LinearLayout list = new LinearLayout(context);
            list.setOrientation(LinearLayout.VERTICAL);
            for (int i = 0; i < items.length; i++) {
                final int index = i;
                LinearLayout row = new LinearLayout(context);
                row.setOrientation(LinearLayout.HORIZONTAL);
                row.setGravity(Gravity.CENTER_VERTICAL);
                row.setPadding(dp(18), 0, dp(16), 0);
                row.setClickable(true);
                row.setFocusable(true);
                row.setBackground(menuRipple());

                TextView label = text(items[i], 17, R.color.text_primary);
                label.setTypeface(null, Typeface.BOLD);
                row.addView(label, new LinearLayout.LayoutParams(0, -2, 1f));

                ImageView arrow = new ImageView(context);
                arrow.setImageResource(R.drawable.ic_chevron_right_vector);
                arrow.setImageTintList(ColorStateList.valueOf(context.getColor(R.color.text_tertiary)));
                arrow.setContentDescription(null);
                row.addView(arrow, new LinearLayout.LayoutParams(dp(22), dp(22)));

                row.setOnClickListener(v -> {
                    if (!dialog.tryConsumeAction()) return;
                    dialog.dismissForReplacement();
                    if (itemListener != null) itemListener.onClick(dialog, index);
                });
                list.addView(row, new LinearLayout.LayoutParams(-1, dp(64)));

                if (i < items.length - 1) {
                    View divider = new View(context);
                    divider.setBackgroundColor(context.getColor(R.color.outline_soft));
                    list.addView(divider, new LinearLayout.LayoutParams(-1, dp(1)));
                }
            }
            group.addView(list, new MaterialCardView.LayoutParams(-1, -2));
            scrollingContent.addView(group,
                    params(-1, -2, scrollingContent.getChildCount() == 0 ? 0 : 18));
        }
        contentScroll.addView(scrollingContent,
                new NestedScrollView.LayoutParams(-1, -2));
        panel.addView(contentScroll, new ViewGroup.LayoutParams(-1, -2));
        if (neutralText != null || negativeText != null || positiveText != null) {
            fixedActions = new LinearLayout(context);
            fixedActions.setOrientation(LinearLayout.HORIZONTAL);
            fixedActions.setGravity(Gravity.CENTER_VERTICAL);
            int count = (neutralText == null ? 0 : 1) + (negativeText == null ? 0 : 1)
                    + (positiveText == null ? 0 : 1);
            if (neutralText != null) addAction(fixedActions,
                    button(dialog, neutralText, neutralListener, DialogInterface.BUTTON_NEUTRAL, false), count);
            if (negativeText != null) addAction(fixedActions,
                    button(dialog, negativeText, negativeListener, DialogInterface.BUTTON_NEGATIVE, false), count);
            if (positiveText != null) addAction(fixedActions,
                    button(dialog, positiveText, positiveListener, DialogInterface.BUTTON_POSITIVE, true), count);
            panel.addView(fixedActions, new ViewGroup.LayoutParams(-1, -2));
        }
        panel.setSections(fixedTitle, contentScroll, fixedActions);
        return panel;
    }

    private MaterialButton button(WGProBottomSheetDialog dialog, CharSequence label,
                                  @Nullable DialogInterface.OnClickListener listener,
                                  int which, boolean primary) {
        MaterialButton button = new MaterialButton(context);
        button.setText(label);
        button.setTextSize(18);
        button.setTypeface(null, Typeface.BOLD);
        button.setAllCaps(false);
        button.setLetterSpacing(0f);
        button.setMinHeight(0);
        button.setInsetTop(0);
        button.setInsetBottom(0);
        button.setCornerRadius(dp(12));
        button.setStrokeWidth(0);
        button.setTextColor(ColorStateList.valueOf(context.getColor(
                primary ? R.color.white : R.color.text_primary)));
        button.setBackgroundTintList(ColorStateList.valueOf(context.getColor(
                primary ? R.color.brand_primary : R.color.surface_secondary)));
        button.setRippleColor(ColorStateList.valueOf(androidx.core.graphics.ColorUtils.setAlphaComponent(
                context.getColor(primary ? R.color.white : R.color.brand_primary), primary ? 46 : 28)));
        button.setOnClickListener(v -> {
            if (!dialog.tryConsumeAction()) return;
            if (listener != null) listener.onClick(dialog, which);
            dialog.dismiss();
        });
        dialog.registerButton(which, button);
        return button;
    }

    private void addAction(LinearLayout actions, MaterialButton button, int count) {
        LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(0, dp(54), 1f);
        if (actions.getChildCount() > 0) layout.leftMargin = dp(count > 2 ? 8 : 10);
        actions.addView(button, layout);
    }

    private void configureWindow(BottomSheetDialog dialog) {
        Window window = dialog.getWindow();
        if (window == null) return;
        int surface = context.getColor(R.color.surface_elevated);
        WindowCompat.setDecorFitsSystemWindows(window, false);
        window.setNavigationBarColor(surface);
        window.setNavigationBarDividerColor(surface);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        if (android.os.Build.VERSION.SDK_INT >= 29) window.setNavigationBarContrastEnforced(false);
        window.setDimAmount(0.68f);
        View sheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if (sheet != null) {
            sheet.setFitsSystemWindows(false);
            sheet.setBackgroundColor(Color.TRANSPARENT);
            BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(sheet);
            behavior.setSkipCollapsed(true);
            behavior.setDraggable(cancelable);
            behavior.setHideable(cancelable);
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            sheet.post(() -> constrainSheetHeight(window, sheet, behavior));
            ViewCompat.requestApplyInsets(sheet);
        }
    }

    private void constrainSheetHeight(Window window, View sheet,
                                      BottomSheetBehavior<View> behavior) {
        if (panel == null) return;
        int windowHeight = window.getDecorView().getHeight();
        if (windowHeight <= 0) windowHeight = context.getResources().getDisplayMetrics().heightPixels;
        WindowInsetsCompat insets = ViewCompat.getRootWindowInsets(window.getDecorView());
        int statusBarHeight = insets == null ? 0
                : insets.getInsets(WindowInsetsCompat.Type.statusBars()).top;
        android.util.TypedValue actionBar = new android.util.TypedValue();
        int actionBarHeight = context.getTheme().resolveAttribute(
                androidx.appcompat.R.attr.actionBarSize, actionBar, true)
                ? android.util.TypedValue.complexToDimensionPixelSize(
                        actionBar.data, context.getResources().getDisplayMetrics())
                : dp(56);
        int maxHeight = Math.max(dp(220), windowHeight - statusBarHeight - actionBarHeight);
        behavior.setMaxHeight(maxHeight);
        panel.setMaximumHeight(maxHeight);
        sheet.requestLayout();
    }

    private final class FixedSectionsLayout extends ViewGroup {
        private View titleView;
        private View middleView;
        private View actionsView;
        private int maximumHeight = Integer.MAX_VALUE;

        FixedSectionsLayout(Context context) {
            super(context);
        }

        void setSections(View title, View middle, View actions) {
            titleView = title;
            middleView = middle;
            actionsView = actions;
        }

        void setMaximumHeight(int value) {
            maximumHeight = value > 0 ? value : Integer.MAX_VALUE;
            requestLayout();
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int contentWidth = Math.max(0, width - getPaddingLeft() - getPaddingRight());
            int parentLimit = MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED
                    ? Integer.MAX_VALUE : MeasureSpec.getSize(heightMeasureSpec);
            int heightLimit = Math.min(parentLimit, maximumHeight);
            if (heightLimit == Integer.MAX_VALUE) {
                heightLimit = context.getResources().getDisplayMetrics().heightPixels;
            }

            int titleHeight = measureFixed(titleView, contentWidth, heightLimit);
            int actionsHeight = measureFixed(actionsView, contentWidth, heightLimit);
            int titleSpacing = titleView == null ? 0 : dp(16);
            int actionSpacing = actionsView == null ? 0 : dp(20);
            int fixedHeight = getPaddingTop() + getPaddingBottom() + titleHeight
                    + titleSpacing + actionSpacing + actionsHeight;
            int middleLimit = Math.max(0, heightLimit - fixedHeight);
            int middleHeight = 0;
            if (middleView != null) {
                middleView.measure(
                        MeasureSpec.makeMeasureSpec(contentWidth, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(middleLimit, MeasureSpec.AT_MOST));
                middleHeight = middleView.getMeasuredHeight();
            }
            setMeasuredDimension(width, Math.min(heightLimit, fixedHeight + middleHeight));
        }

        private int measureFixed(View view, int width, int heightLimit) {
            if (view == null) return 0;
            view.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
                    MeasureSpec.makeMeasureSpec(heightLimit, MeasureSpec.AT_MOST));
            return view.getMeasuredHeight();
        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            int x = getPaddingLeft();
            int width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
            int y = getPaddingTop();
            if (titleView != null) {
                titleView.layout(x, y, x + width, y + titleView.getMeasuredHeight());
                y += titleView.getMeasuredHeight() + dp(16);
            }
            if (middleView != null) {
                middleView.layout(x, y, x + width, y + middleView.getMeasuredHeight());
                y += middleView.getMeasuredHeight();
            }
            if (actionsView != null) {
                y += dp(20);
                actionsView.layout(x, y, x + width, y + actionsView.getMeasuredHeight());
            }
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
    private RippleDrawable menuRipple() {
        int ripple = androidx.core.graphics.ColorUtils.setAlphaComponent(
                context.getColor(R.color.brand_primary), 28);
        return new RippleDrawable(
                ColorStateList.valueOf(ripple),
                new ColorDrawable(Color.TRANSPARENT),
                new ColorDrawable(Color.WHITE));
    }
    private boolean containsProgressIndicator(View view) {
        if (view == null) return false;
        if (view instanceof ProgressBar) return true;
        if (!(view instanceof ViewGroup)) return false;
        ViewGroup group = (ViewGroup) view;
        for (int i = 0; i < group.getChildCount(); i++) {
            if (containsProgressIndicator(group.getChildAt(i))) return true;
        }
        return false;
    }
    private int dp(int value) { return (int) (value * context.getResources().getDisplayMetrics().density + 0.5f); }
}
