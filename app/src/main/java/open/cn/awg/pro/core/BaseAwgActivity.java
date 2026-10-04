/**
 ******************************************************************************
 * @file    BaseAwgActivity.java
 * @author  Typheye
 * @brief   Shared Activity base for screen mode, status bar, and DPI adaptation.
 ******************************************************************************
 * @attention
 *
 * Copyright (c) 2021-2026 Typheye. All rights reserved.
 *
 * This software is licensed under terms that can be found in the LICENSE file
 * in the root directory of this software component.
 * If no LICENSE file comes with this software, it is provided AS-IS.
 *
 ******************************************************************************
 */
package open.cn.awg.pro.core;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.util.TypedValue;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;

import com.ypz.bangscreentools.BangScreenTools;

import java.util.Objects;

import open.cn.awg.pro.R;

public class BaseAwgActivity extends AppCompatActivity {

    protected static final String DISPLAY_MODE_FILE = AppPaths.appPath("settings/a3.inf");
    protected static final String DISPLAY_MARGIN_FILE = AppPaths.appPath("settings/f17/set1.inf");
    protected static final String DPI_OPTIMIZE_FILE = AppPaths.appPath("settings/dpi.inf");
    protected static final String NIGHT_MODE_ENABLED_FILE = AppPaths.appPath("settings/set4s.inf");
    protected static final String NIGHT_MODE_VALUE_FILE = AppPaths.appPath("settings/set4.inf");

    /** Applies the common watch screen policy used by most legacy pages. */
    protected void applyStandardScreenMode(int windowRootId, int contentRootId, int titleBarId) {
        applyNightModeFromSettings();

        String displayMode = readDisplayMode();
        int displayMargin = readDisplayMargin();

        applyStatusBarMode(windowRootId, displayMode);
        applyContentInsets(contentRootId, titleBarId, displayMode, displayMargin);
    }

    protected void applyNightModeFromSettings() {
        if (!Objects.equals(NativeFiles.readText(this, NIGHT_MODE_ENABLED_FILE), "false")) {
            return;
        }

        if (Objects.equals(NativeFiles.readText(this, NIGHT_MODE_VALUE_FILE), "true")) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    /** Keeps status-bar color mode and fullscreen mode consistent with display settings. */
    protected void applyStatusBarMode(int windowRootId, String displayMode) {
        View windowRoot = findViewById(windowRootId);
        if (Objects.equals(displayMode, "4")) {
            if (windowRoot != null) {
                windowRoot.setFitsSystemWindows(true);
            }
            setStatusBarColor(ContextCompat.getColor(this, R.color.colorTab));
            return;
        }

        if (windowRoot != null) {
            windowRoot.setFitsSystemWindows(false);
        }
        applyFullscreenWindow();
    }

    /** Re-applies window flags from lifecycle callbacks after focus/resume changes. */
    protected void applyWindowModeFromSettings() {
        if (Objects.equals(readDisplayMode(), "4")) {
            setStatusBarColor(ContextCompat.getColor(this, R.color.colorTab));
        } else {
            applyFullscreenWindow();
        }
    }

    protected void applyFullscreenWindow() {
        Window window = getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, this);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);
    }

    protected void setStatusBarColor(String color) {
        setStatusBarColor(Color.parseColor(color));
    }

    protected void setStatusBarColor(int color) {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        window.setStatusBarColor(color);
    }

    /** Updates content/title padding for round, square, and status-bar display modes. */
    protected void applyContentInsets(int contentRootId, int titleBarId, String displayMode, int displayMargin) {
        View contentRoot = findViewById(contentRootId);
        if (Objects.equals(displayMode, "1")) {
            setPadding(contentRoot, displayMargin, 0, displayMargin, 50);
            applyTallTitleBar(titleBarId);
        } else if (Objects.equals(displayMode, "2")) {
            applySquareScreenTitleBar(titleBarId);
            setPadding(contentRoot, 0, 0, 0, 0);
        } else if (Objects.equals(displayMode, "3")) {
            setPadding(contentRoot, displayMargin, 0, displayMargin, 50);
            applyTallTitleBar(titleBarId);
        } else if (Objects.equals(displayMode, "4")) {
            setPadding(contentRoot, 0, 0, 0, 0);
        }
    }

    protected String readDisplayMode() {
        return NativeFiles.readText(this, DISPLAY_MODE_FILE);
    }

    protected int readDisplayMargin() {
        try {
            return Integer.parseInt(NativeFiles.readText(this, DISPLAY_MARGIN_FILE));
        } catch (RuntimeException ignored) {
            return 0;
        }
    }

    protected boolean shouldOptimizeDpi() {
        return NativeFiles.exists(this, DPI_OPTIMIZE_FILE)
                || Objects.equals(NativeFiles.readText(this, DPI_OPTIMIZE_FILE), "true");
    }

    protected int dp(float dimension) {
        return Math.round(TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dimension, getResources().getDisplayMetrics()));
    }

    private static void setPadding(View view, int left, int top, int right, int bottom) {
        if (view != null) {
            view.setPadding(left, top, right, bottom);
        }
    }

    private void applyTallTitleBar(int titleBarId) {
        TextView titleBar = findViewById(titleBarId);
        if (titleBar == null) {
            return;
        }
        titleBar.setPadding(0, dp(15), 0, dp(3));
        titleBar.setGravity(Gravity.CENTER);
    }

    private void applySquareScreenTitleBar(int titleBarId) {
        TextView titleBar = findViewById(titleBarId);
        if (titleBar == null) {
            return;
        }
        int verticalPadding = shouldOptimizeDpi() ? dp(3) : dp(9);
        titleBar.setPadding(0, verticalPadding, 0, verticalPadding);
        titleBar.setGravity(Gravity.CENTER);
    }
}
