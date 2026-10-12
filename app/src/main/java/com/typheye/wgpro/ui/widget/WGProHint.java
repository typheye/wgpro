package com.typheye.wgpro.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.SystemBars;

/**
 * 全应用统一的提示载体（**Toast 已全面退场**）：
 * <ul>
 *     <li>{@link #show} —— 轻提示：沿用底部弹窗的表面（圆角/配色/系统栏处理），
 *         **自动消失、不压暗、不挡操作**，用于「已复制 / 已加入下载 / 网络不可用 / 已删除」这类瞬时反馈。</li>
 *     <li>{@link #showError} —— 错误提示：走 {@link WGProAlertDialogBuilder}（大标题 + 说明 + 整宽按钮），
 *         需要用户看一眼再关掉，用于「打不开 / 加载失败 / 权限被拒」这类。</li>
 * </ul>
 */
public final class WGProHint {
    /** 轻提示的默认停留时间。 */
    public static final long DEFAULT_DURATION_MS = 1500L;

    private static final Handler MAIN = new Handler(Looper.getMainLooper());
    private static java.lang.ref.WeakReference<BottomSheetDialog> CURRENT =
            new java.lang.ref.WeakReference<>(null);

    private WGProHint() { }

    /** 轻提示（自动消失）。 */
    public static void show(@Nullable Context context, @Nullable CharSequence message) {
        show(context, message, DEFAULT_DURATION_MS);
    }

    /** 等活跃底部弹窗退场的轮询步长 / 最长等待（避免轻提示和别的弹窗叠在一起）。 */
    private static final long SHEET_WAIT_STEP_MS = 80L;
    private static final long SHEET_WAIT_MAX_MS = 600L;

    public static void show(@Nullable Context context, @Nullable CharSequence message, long durationMs) {
        if (message == null || message.length() == 0) return;
        Activity activity = activityOf(context);
        if (activity == null) return;
        showAfterActiveSheet(activity, message, Math.max(600L, durationMs), 0L);
    }

    /**
     * 轻提示也要守"同时只有一个底部弹窗"的规矩：当前有活跃弹窗就先等它退场
     * （最多等 {@link #SHEET_WAIT_MAX_MS}，超时照常弹出，绝不吞提示）。
     */
    private static void showAfterActiveSheet(Activity activity, CharSequence message,
                                             long durationMs, long waitedMs) {
        WGProBottomSheetDialog active = WGProBottomSheetDialog.activeFor(activity);
        if (active != null && waitedMs < SHEET_WAIT_MAX_MS) {
            MAIN.postDelayed(() -> showAfterActiveSheet(activity, message, durationMs,
                    waitedMs + SHEET_WAIT_STEP_MS), SHEET_WAIT_STEP_MS);
            return;
        }
        showNow(activity, message, durationMs);
    }

    private static void showNow(Activity activity, CharSequence message, long durationMs) {
        final CharSequence text = message;
        final long duration = durationMs;
        MAIN.post(() -> {
            if (activity.isFinishing() || activity.isDestroyed()) return;
            BottomSheetDialog previous = CURRENT.get();
            if (previous != null && previous.isShowing()) {
                try { previous.dismiss(); } catch (Exception ignored) { }
            }
            final BottomSheetDialog dialog = buildHint(activity, text, duration);
            CURRENT = new java.lang.ref.WeakReference<>(dialog);
            // 兜底：万一倒计时动画没跑起来（视图没完成布局等），也确保会自动关掉
            final Runnable fallback = () -> dismissQuietly(dialog);
            dialog.setOnDismissListener(ignored -> {
                MAIN.removeCallbacks(fallback);
                View decor = dialog.getWindow() == null ? null : dialog.getWindow().getDecorView();
                Object tag = decor == null ? null : decor.getTag();
                if (tag instanceof android.animation.ValueAnimator) {
                    ((android.animation.ValueAnimator) tag).cancel();
                }
                CURRENT = new java.lang.ref.WeakReference<>(null);
            });
            dialog.show();
            MAIN.postDelayed(fallback, duration + 600L);
        });
    }

    /** 错误提示：底部弹窗 + 整宽「关闭」（需要用户确认）。 */
    /** 错误提示的兜底标题：不允许出现「无标题」底部弹窗。 */
    private static final String DEFAULT_ERROR_TITLE = "操作失败";

    public static void showError(@Nullable Context context, @Nullable CharSequence message) {
        showError(context, DEFAULT_ERROR_TITLE, message);
    }

    /** 说明类提示：底部弹窗 + 整宽「关闭」（与 {@link #showError} 同形态，只是语义不同）。 */
    public static void showInfo(@Nullable Context context, @Nullable CharSequence title,
                                @Nullable CharSequence message) {
        showError(context, title, message);
    }

    public static void showError(@Nullable Context context, @Nullable CharSequence title,
                                 @Nullable CharSequence message) {
        showError(context, title, message, null);
    }

    /** 错误提示，可带一个「弹窗关掉之后」的回调（例如页面本来就该 finish 的场景）。 */
    public static void showError(@Nullable Context context, @Nullable CharSequence title,
                                 @Nullable CharSequence message, @Nullable Runnable onDismiss) {
        if ((message == null || message.length() == 0) && (title == null || title.length() == 0)) return;
        Activity activity = activityOf(context);
        if (activity == null) return;
        final CharSequence heading = (title == null || title.length() == 0) ? DEFAULT_ERROR_TITLE : title;
        final CharSequence body = message;
        MAIN.post(() -> {
            if (activity.isFinishing() || activity.isDestroyed()) {
                if (onDismiss != null) onDismiss.run();
                return;
            }
            WGProBottomSheetDialog dialog = new WGProAlertDialogBuilder(activity)
                    .setTitle(heading)
                    .setMessage(body)
                    .setNegativeButton("关闭", null)
                    .show();
            if (onDismiss != null) dialog.setOnDismissListener(d -> onDismiss.run());
        });
    }

    /** 从任意 Context 里找前台 Activity（Application context 就退回当前 resumed Activity）。 */
    @Nullable
    public static Activity activityOf(@Nullable Context context) {
        Context current = context;
        while (current instanceof ContextWrapper) {
            if (current instanceof Activity) {
                Activity activity = (Activity) current;
                if (!activity.isFinishing() && !activity.isDestroyed()) return activity;
                break;
            }
            Context base = ((ContextWrapper) current).getBaseContext();
            if (base == null || base == current) break;
            current = base;
        }
        Activity resumed = SystemBars.currentActivity();
        if (resumed != null && !resumed.isFinishing() && !resumed.isDestroyed()) return resumed;
        return null;
    }

    private static BottomSheetDialog buildHint(Activity activity, CharSequence message, long durationMs) {
        BottomSheetDialog dialog = new BottomSheetDialog(
                activity, R.style.ThemeOverlay_WGPro_BottomSheetDialog);
        LinearLayout content = new LinearLayout(activity);
        content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(dp(activity, 24), dp(activity, 26), dp(activity, 24), dp(activity, 18));

        GradientDrawable background = new GradientDrawable();
        boolean night = (activity.getResources().getConfiguration().uiMode
                & android.content.res.Configuration.UI_MODE_NIGHT_MASK)
                == android.content.res.Configuration.UI_MODE_NIGHT_YES;
        background.setColor(night
                ? WGProAlertDialogBuilder.resolveThemeColor(
                        activity, "colorSurfaceContainerHigh", R.color.surface_elevated)
                : com.typheye.wgpro.ui.widget.WGProColors.of(activity, R.color.surface_primary));
        background.setCornerRadii(new float[]{
                dp(activity, 28), dp(activity, 28), dp(activity, 28), dp(activity, 28), 0, 0, 0, 0});
        content.setBackground(background);

        // 固定标题「提示」（与 app 其它底部弹窗同规格：24sp 粗体）
        TextView heading = new TextView(activity);
        heading.setText("提示");
        heading.setTextColor(WGProAlertDialogBuilder.resolveThemeColor(
                activity, "colorOnSurface", R.color.text_primary));
        heading.setTextSize(24);
        heading.setTypeface(null, android.graphics.Typeface.BOLD);
        content.addView(heading, new LinearLayout.LayoutParams(-1, -2));

        TextView text = new TextView(activity);
        text.setText(message);
        text.setTextColor(WGProAlertDialogBuilder.resolveThemeColor(
                activity, "colorOnSurfaceVariant", R.color.text_secondary));
        text.setTextSize(16);
        text.setLineSpacing(dp(activity, 3), 1f);
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(-1, -2);
        textParams.topMargin = dp(activity, 14);
        content.addView(text, textParams);

        // 底部倒计时：Material 3 原生 LinearProgressIndicator（确定态），
        // 进度从满到 0 往回走，走完自动关窗。
        com.google.android.material.progressindicator.LinearProgressIndicator bar =
                new com.google.android.material.progressindicator.LinearProgressIndicator(activity);
        bar.setIndeterminate(false);
        bar.setMax(100);
        bar.setProgressCompat(100, false);
        bar.setTrackThickness(dp(activity, 6));
        bar.setTrackCornerRadius(dp(activity, 3));
        bar.setTrackStopIndicatorSize(0);
        bar.setIndicatorColor(WGProAlertDialogBuilder.resolveThemeColor(
                activity, "colorPrimary", R.color.brand_primary));
        bar.setTrackColor(WGProAlertDialogBuilder.resolveThemeColor(
                activity, "colorSecondaryContainer", R.color.surface_secondary));
        LinearLayout.LayoutParams barParams = new LinearLayout.LayoutParams(-1, -2);
        barParams.topMargin = dp(activity, 18);
        content.addView(bar, barParams);

        dialog.setContentView(content);
        dialog.setOnShowListener(ignored -> {
            configure(dialog, content);
            startCountdown(bar, durationMs, dialog);
        });
        return dialog;
    }

    /** 倒计时进度：从满到 0 线性回退，归零关窗；提前关窗则取消动画。 */
    private static void startCountdown(com.google.android.material.progressindicator.LinearProgressIndicator bar,
                                       long durationMs, BottomSheetDialog dialog) {
        bar.post(() -> {
            if (!dialog.isShowing()) {
                dismissQuietly(dialog);
                return;
            }
            android.animation.ValueAnimator animator = android.animation.ValueAnimator.ofInt(100, 0);
            animator.setDuration(Math.max(600L, durationMs));
            animator.setInterpolator(new android.view.animation.LinearInterpolator());
            animator.addUpdateListener(a -> bar.setProgressCompat((int) a.getAnimatedValue(), false));
            animator.addListener(new android.animation.AnimatorListenerAdapter() {
                @Override public void onAnimationEnd(android.animation.Animator animation) {
                    dismissQuietly(dialog);
                }
            });
            View decor = dialog.getWindow() == null ? null : dialog.getWindow().getDecorView();
            if (decor != null) decor.setTag(animator);
            animator.start();
        });
    }

    private static void dismissQuietly(BottomSheetDialog dialog) {
        try { dialog.dismiss(); } catch (Exception ignored) { }
    }

    private static void configure(BottomSheetDialog dialog, View content) {
        Window window = dialog.getWindow();
        if (window == null) return;
        SystemBars.configureWindow(window, content.getContext());
        window.getDecorView().setBackgroundColor(Color.TRANSPARENT);
        // 与应用内其它底部弹窗同一套压暗，保证提示在页面内容上方看得清
        window.setDimAmount(0.68f);
        ViewCompat.setOnApplyWindowInsetsListener(content, (v, insets) -> {
            int bottom = SystemBars.bottomSheetPadding(v, insets);
            v.setPadding(v.getPaddingLeft(), v.getPaddingTop(), v.getPaddingRight(), bottom);
            return insets;
        });
        ViewCompat.requestApplyInsets(content);
        View sheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if (sheet != null) {
            sheet.setBackgroundColor(Color.TRANSPARENT);
            SystemBars.ownSheetInsets(sheet);
            BottomSheetBehavior<?> behavior = BottomSheetBehavior.from(sheet);
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            behavior.setSkipCollapsed(true);
        }
    }

    private static int dp(Context context, int value) {
        return (int) (value * context.getResources().getDisplayMetrics().density + 0.5f);
    }
}
