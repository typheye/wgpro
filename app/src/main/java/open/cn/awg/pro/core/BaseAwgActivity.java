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

import android.view.Window;

import androidx.appcompat.app.AppCompatDelegate;

import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;

import open.cn.awg.pro.R;

public class BaseAwgActivity extends iActivity {

    protected static final String DISPLAY_MODE_FILE = AppPaths.appPath("settings/a3.inf");
    protected static final String DISPLAY_MARGIN_FILE = AppPaths.appPath("settings/f17/set1.inf");
    protected static final String DPI_OPTIMIZE_FILE = AppPaths.appPath("settings/dpi.inf");
    protected static final String NIGHT_MODE_ENABLED_FILE = AppPaths.appPath("settings/set4s.inf");
    protected static final String NIGHT_MODE_VALUE_FILE = AppPaths.appPath("settings/set4.inf");

    /**
     * Applies the common watch screen policy used by most legacy pages.
     */
    protected void applyStandardScreenMode(int windowRootId, int contentRootId, int titleBarId) {
        applyNightModeFromSettings();

        String displayMode = readDisplayMode();
        int displayMargin = readDisplayMargin();

        applyStatusBarMode(windowRootId, displayMode);
        applyContentInsets(contentRootId, titleBarId, displayMode, displayMargin);
    }

    protected void applyNightModeFromSettings() {
        if (!zf.dy(wj.dqwb(NIGHT_MODE_ENABLED_FILE), "false")) {
            return;
        }

        if (zf.dy(wj.dqwb(NIGHT_MODE_VALUE_FILE), "true")) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    /**
     * Keeps status-bar color mode and fullscreen mode consistent with display settings.
     */
    protected void applyStatusBarMode(int windowRootId, String displayMode) {
        if (zf.dy(displayMode, "4")) {
            st.xdbj(windowRootId).shxtck(true);

            String color = zf.zf(R.color.colorTab);
            xt.pm().ztl(color, 0);
            return;
        }

        st.xdbj(windowRootId).shxtck(false);
        applyFullscreenWindow();
    }

    /**
     * Re-applies window flags from lifecycle callbacks after focus/resume changes.
     */
    protected void applyWindowModeFromSettings() {
        if (zf.dy(readDisplayMode(), "4")) {
            String color = zf.zf(R.color.colorTab);
            xt.pm().ztl(color, 0);
        } else {
            applyFullscreenWindow();
        }
    }

    protected void applyFullscreenWindow() {
        Window window = getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, this);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);
    }

    /**
     * Updates content/title padding for round, square, and status-bar display modes.
     */
    protected void applyContentInsets(int contentRootId, int titleBarId, String displayMode, int displayMargin) {
        if (zf.dy(displayMode, "1")) {
            st.xdbj(contentRootId).nbj(displayMargin, 0, displayMargin, 50);
            applyTallTitleBar(titleBarId);
        } else if (zf.dy(displayMode, "2")) {
            applySquareScreenTitleBar(titleBarId);
            st.xdbj(contentRootId).nbj(0, 0, 0, 0);
        } else if (zf.dy(displayMode, "3")) {
            st.xdbj(contentRootId).nbj(displayMargin, 0, displayMargin, 50);
            applyTallTitleBar(titleBarId);
        } else if (zf.dy(displayMode, "4")) {
            st.xdbj(contentRootId).nbj(0, 0, 0, 0);
        }
    }

    protected String readDisplayMode() {
        return wj.dqwb(DISPLAY_MODE_FILE);
    }

    protected int readDisplayMargin() {
        try {
            return Integer.parseInt(wj.dqwb(DISPLAY_MARGIN_FILE));
        } catch (Exception ignored) {
            return 0;
        }
    }

    protected boolean shouldOptimizeDpi() {
        return wj.cz(DPI_OPTIMIZE_FILE) || zf.dy(wj.dqwb(DPI_OPTIMIZE_FILE), "true");
    }

    private void applyTallTitleBar(int titleBarId) {
        i.runlibrary.app.v.wb titleBar = st.wb(titleBarId);
        titleBar.nbj(0, "15dp", 0, "3dp");
        titleBar.dqfs("center");
    }

    private void applySquareScreenTitleBar(int titleBarId) {
        i.runlibrary.app.v.wb titleBar = st.wb(titleBarId);
        if (shouldOptimizeDpi()) {
            titleBar.nbj(0, "3dp", 0, "3dp");
        } else {
            titleBar.nbj(0, "9dp", 0, "9dp");
        }
        titleBar.dqfs("center");
    }
}
