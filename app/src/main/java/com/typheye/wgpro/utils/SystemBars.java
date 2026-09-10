package com.typheye.wgpro.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

/**
 * Single place for all system-bar / edge-to-edge adaptation.
 *
 * <p>Android 15 (API 35) and above enforce edge-to-edge. The two bottom-sheet rules are also
 * centralized here: when a navigation bar / gesture handle is present, use its real inset;
 * otherwise add the legacy half-button gap so the actions stay comfortably away from the
 * screen edge.
 */
public final class SystemBars {
    private static final int SHEET_BASE_BOTTOM_DP = 12;
    private static final int SHEET_BUTTON_HEIGHT_DP = 54;

    private SystemBars() {
    }

    public static boolean isEdgeToEdgeEnforced() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM;
    }

    /** Configure a normal Activity window for edge-to-edge drawing. */
    public static void configureWindow(@NonNull Window window, @NonNull Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.getAttributes().layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.getAttributes().layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }

        WindowCompat.setDecorFitsSystemWindows(window, false);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(android.graphics.Color.TRANSPARENT);
        window.setNavigationBarColor(android.graphics.Color.TRANSPARENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.setNavigationBarDividerColor(android.graphics.Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.setStatusBarContrastEnforced(false);
            window.setNavigationBarContrastEnforced(false);
        }

        boolean light = isLightMode(context);
        WindowInsetsControllerCompat controller = new WindowInsetsControllerCompat(
                window, window.getDecorView());
        controller.setAppearanceLightStatusBars(light);
        controller.setAppearanceLightNavigationBars(light);
    }

    /** Legacy app-bar + bottom-area insets used by most section activities. */
    public static void applyWindowInsets(@NonNull View appBar, @NonNull View bottomContent) {
        int appBarLeft = appBar.getPaddingLeft();
        int appBarTop = appBar.getPaddingTop();
        int appBarRight = appBar.getPaddingRight();
        int appBarBottom = appBar.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(appBar, (view, insets) -> {
            Insets statusBars = insets.getInsets(WindowInsetsCompat.Type.statusBars()
                    | WindowInsetsCompat.Type.displayCutout());
            int freeform = isInMultiWindow(view) ? dp(view, 18) : 0;
            int freeformSide = isInMultiWindow(view) ? dp(view, 8) : 0;
            view.setPadding(appBarLeft + statusBars.left + freeformSide,
                    appBarTop + statusBars.top + freeform,
                    appBarRight + statusBars.right + freeformSide,
                    appBarBottom);
            return insets;
        });

        int navLeft = bottomContent.getPaddingLeft();
        int navTop = bottomContent.getPaddingTop();
        int navRight = bottomContent.getPaddingRight();
        int navBottom = bottomContent.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(bottomContent, (view, insets) -> {
            int navigationBottom = navigationBarBottom(insets);
            int freeformBottom = isInMultiWindow(view) ? dp(view, 22) : 0;
            int freeformSide = isInMultiWindow(view) ? dp(view, 8) : 0;
            view.setPadding(navLeft + insets.getInsets(
                            WindowInsetsCompat.Type.navigationBars()).left + freeformSide,
                    navTop,
                    navRight + insets.getInsets(
                            WindowInsetsCompat.Type.navigationBars()).right + freeformSide,
                    navBottom + navigationBottom + freeformBottom);
            return insets;
        });
        ViewCompat.requestApplyInsets(appBar);
        ViewCompat.requestApplyInsets(bottomContent);
    }

    /**
     * Apply system-bar insets to a whole root view. This is the WebActivity style that already
     * works on both Android 14 and Android 16/17, and can be reused by ordinary scrolling pages.
     */
    public static void applyRootInsets(@NonNull View root, boolean includeBottom) {
        int initialLeft = root.getPaddingLeft();
        int initialTop = root.getPaddingTop();
        int initialRight = root.getPaddingRight();
        int initialBottom = root.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(root, (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()
                    | WindowInsetsCompat.Type.displayCutout());
            int freeformTop = isInMultiWindow(view) ? dp(view, 18) : 0;
            int freeformBottom = isInMultiWindow(view) ? dp(view, 22) : 0;
            int freeformSide = isInMultiWindow(view) ? dp(view, 8) : 0;
            view.setPadding(
                    initialLeft + systemBars.left + freeformSide,
                    initialTop + systemBars.top + freeformTop,
                    initialRight + systemBars.right + freeformSide,
                    initialBottom + (includeBottom
                            ? navigationBarBottom(insets) + freeformBottom : 0));
            return WindowInsetsCompat.CONSUMED;
        });
        ViewCompat.requestApplyInsets(root);
    }

    /**
     * Return the safe bottom inset contributed by a visible navigation bar. On Android 15+ the
     * three-button bar is exposed through {@code tappableElement()}, while gesture navigation
     * keeps using {@code navigationBars()}.
     */
    public static int navigationBarBottom(@NonNull WindowInsetsCompat insets) {
        int navigation = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
            int tappable = insets.getInsets(WindowInsetsCompat.Type.tappableElement()).bottom;
            if (tappable > 0) {
                return tappable;
            }
        }
        return navigation;
    }

    public static boolean hasVisibleNavigationBar(@NonNull WindowInsetsCompat insets) {
        return navigationBarBottom(insets) > 0
                || insets.isVisible(WindowInsetsCompat.Type.navigationBars());
    }

    /**
     * Bottom padding for the custom alert sheets. If a navigation bar / gesture handle exists,
     * add its real inset; otherwise add the legacy half-button gap.
     */
    public static int bottomSheetPadding(@NonNull Context context,
                                         @NonNull WindowInsetsCompat insets) {
        int extra = hasVisibleNavigationBar(insets)
                ? navigationBarBottom(insets)
                : dp(context, SHEET_BUTTON_HEIGHT_DP / 2);
        return dp(context, SHEET_BASE_BOTTOM_DP) + Math.max(0, extra);
    }

    public static boolean isInMultiWindow(@NonNull View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return ((Activity) context).isInMultiWindowMode();
            }
            Context base = ((ContextWrapper) context).getBaseContext();
            if (base == null || base == context) {
                break;
            }
            context = base;
        }
        return false;
    }

    private static boolean isLightMode(@NonNull Context context) {
        int nightModeFlags = context.getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags != Configuration.UI_MODE_NIGHT_YES;
    }

    private static int dp(@NonNull View view, int value) {
        return Math.round(value * view.getResources().getDisplayMetrics().density);
    }

    private static int dp(@NonNull Context context, int value) {
        return Math.round(value * context.getResources().getDisplayMetrics().density);
    }
}
