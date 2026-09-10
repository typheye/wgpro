package com.typheye.wgpro.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

/**
 * Single place for all system-bar / edge-to-edge adaptation.
 *
 * <p>Android 15 (API 35) and above enforce edge-to-edge. Bottom-sheet spacing is also centralized
 * here: Material 3 already applies the visible navigation-bar inset to the sheet, so we only add
 * the legacy half-button fallback when no navigation bar / gesture handle was detected.
 */
public final class SystemBars {
    private static final int LEGACY_SHEET_PANEL_BOTTOM_DP = 39;
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
            int freeformTop = isInMultiWindow(view) ? dp(view, 18) : 0;
            int freeformSide = isInMultiWindow(view) ? dp(view, 8) : 0;
            view.setPadding(appBarLeft + statusBars.left + freeformSide,
                    appBarTop + statusBars.top + freeformTop,
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
        applyRootInsets(root, null, includeBottom);
    }

    /**
     * Root-insets variant that can reserve the bottom inset on a child content view. Some
     * CoordinatorLayout children ignore the parent padding, so applying the bottom reserve to
     * both the chrome root and the scrolling content keeps content above the gesture area.
     */
    public static void applyRootInsets(@NonNull View root, @Nullable View bottomContent,
                                       boolean reserveBottom) {
        applyRootInsets(root, bottomContent, reserveBottom, 0, 0);
    }

    /**
     * Root insets with a two-tone chrome background: the status-bar strip uses the app-bar
     * color and the navigation-bar strip uses the page color.
     */
    public static void applyRootInsets(@NonNull View root, @Nullable View bottomContent,
                                       boolean reserveBottom, int chromeColor, int pageColor) {
        int initialLeft = root.getPaddingLeft();
        int initialTop = root.getPaddingTop();
        int initialRight = root.getPaddingRight();
        int initialBottom = root.getPaddingBottom();
        int contentLeft = bottomContent == null ? 0 : bottomContent.getPaddingLeft();
        int contentTop = bottomContent == null ? 0 : bottomContent.getPaddingTop();
        int contentRight = bottomContent == null ? 0 : bottomContent.getPaddingRight();
        int contentBottom = bottomContent == null ? 0 : bottomContent.getPaddingBottom();
        ChromeBackgroundDrawable chromeBackground = chromeColor == 0 && pageColor == 0
                ? null : new ChromeBackgroundDrawable(chromeColor, pageColor);
        if (chromeBackground != null) {
            root.setBackground(chromeBackground);
        }
        ViewCompat.setOnApplyWindowInsetsListener(root, (view, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()
                    | WindowInsetsCompat.Type.displayCutout());
            int bottom = reserveBottom ? bottomInset(insets) : 0;
            if (chromeBackground != null) {
                chromeBackground.setTopHeight(systemBars.top);
            }
            view.setPadding(initialLeft + systemBars.left,
                    initialTop + systemBars.top,
                    initialRight + systemBars.right,
                    initialBottom + (bottomContent == null ? bottom : 0));
            if (bottomContent != null) {
                bottomContent.setPadding(contentLeft, contentTop, contentRight,
                        contentBottom + bottom);
            }
            return WindowInsetsCompat.CONSUMED;
        });
        ViewCompat.requestApplyInsets(root);
    }

    public static int bottomInset(@NonNull WindowInsetsCompat insets) {
        return Math.max(navigationBarBottom(insets),
                insets.getInsets(WindowInsetsCompat.Type.mandatorySystemGestures()).bottom);
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
     * Bottom padding for the custom alert sheets.
     *
     * <p>Material 3's modal bottom-sheet style already applies
     * {@code paddingBottomSystemWindowInsets=true} to the sheet itself. Adding the navigation-bar
     * inset a second time here is what produced the extra gap on Android 14/15/16/17. Only the
     * no-navigation-bar fallback is added here.
     */
    public static int bottomSheetPadding(@NonNull Context context,
                                         @NonNull WindowInsetsCompat insets) {
        if (!isEdgeToEdgeEnforced()) {
            // Android 14 and below already had the correct legacy spacing.
            return dp(context, LEGACY_SHEET_PANEL_BOTTOM_DP)
                    + navigationBarBottom(insets);
        }
        // Android 15+: Material 3 already reserves the navigation-bar inset on the sheet.
        // Only add the legacy half-button gap when there is no navigation bar at all.
        int extra = hasVisibleNavigationBar(insets)
                ? 0
                : dp(context, SHEET_BUTTON_HEIGHT_DP / 2);
        return dp(context, SHEET_BASE_BOTTOM_DP) + extra;
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

    private static final class ChromeBackgroundDrawable extends Drawable {
        private final Paint paint = new Paint();
        private final int chromeColor;
        private final int pageColor;
        private int topHeight;

        ChromeBackgroundDrawable(int chromeColor, int pageColor) {
            this.chromeColor = chromeColor;
            this.pageColor = pageColor;
        }

        void setTopHeight(int value) {
            if (topHeight == value) return;
            topHeight = Math.max(0, value);
            invalidateSelf();
        }

        @Override
        public void draw(@NonNull Canvas canvas) {
            canvas.drawColor(pageColor);
            if (topHeight > 0) {
                paint.setColor(chromeColor);
                canvas.drawRect(getBounds().left, getBounds().top,
                        getBounds().right, getBounds().top + topHeight, paint);
            }
        }

        @Override
        public void setAlpha(int alpha) {
            paint.setAlpha(alpha);
        }

        @Override
        public void setColorFilter(@Nullable ColorFilter colorFilter) {
            paint.setColorFilter(colorFilter);
        }

        @Override
        public int getOpacity() {
            return PixelFormat.OPAQUE;
        }
    }
}
