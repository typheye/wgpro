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
        wireAppBar();
        enableMarquees();
    }

    /** 设置项的长摘要用 marquee：TextView 必须 selected 才会滚动，布局完成后统一置位。 */
    private void enableMarquees() {
        final View root = getWindow().getDecorView();
        root.post(new Runnable() {
            public void run() {
                enableMarqueeIn(root);
            }
        });
    }

    private void enableMarqueeIn(View v) {
        if (v instanceof TextView) {
            TextView tv = (TextView) v;
            if (tv.getEllipsize() == android.text.TextUtils.TruncateAt.MARQUEE) {
                tv.setSelected(true);
            }
        }
        if (v instanceof android.view.ViewGroup) {
            android.view.ViewGroup group = (android.view.ViewGroup) v;
            for (int i = 0; i < group.getChildCount(); i++) {
                enableMarqueeIn(group.getChildAt(i));
            }
        }
    }

    /** 应用栏统一接线：毛玻璃以滚动内容为源；左 Action 与整个应用栏范围点击都作为返回。 */
    protected void wireAppBar() {
        View blur = findViewById(R.id.blur_bar);
        View source = findViewById(R.id.qtgd1);
        if (source == null) {
            source = findViewById(R.id.xdbj1);
        }
        if (blur instanceof open.cn.awg.pro.ui.widget.BlurBarView && source != null) {
            ((open.cn.awg.pro.ui.widget.BlurBarView) blur).setSource(source);
        }

        final View appBar = findViewById(R.id.app_bar);
        final View actionLeft = findViewById(R.id.appbar_action_left);
        final android.widget.TextView title = findViewById(R.id.title_bar);

        // 菜单态：标题为「菜单」时，隐藏左返回，应用栏点击=退出菜单（不是退出 Activity）
        final boolean[] menuMode = {false};
        if (title != null) {
            title.addTextChangedListener(new android.text.TextWatcher() {
                public void beforeTextChanged(CharSequence s, int a, int b, int c) {
                }

                public void onTextChanged(CharSequence s, int a, int b, int c) {
                }

                public void afterTextChanged(android.text.Editable s) {
                    boolean menu = s != null && "菜单".equals(s.toString().trim());
                    menuMode[0] = menu;
                    if (actionLeft != null) {
                        actionLeft.setVisibility(menu ? View.GONE : View.VISIBLE);
                    }
                }
            });
        }

        // 统一标题排版参数：includeFontPadding 在部分页面会在首次布局后才生效，
        // 导致同一位置上的标题文字比别人低 3px，这里显式置位并强制重排一次。
        if (title != null) {
            title.setIncludeFontPadding(false);
            // 强制重建 Layout：部分页面首次布局用的还是带 font padding 的旧 Layout
            title.setText(title.getText());
            title.requestLayout();
        }

        if (appBar != null) {
            appBar.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (menuMode[0]) {
                        exitAppBarMenu();
                    } else {
                        finish();
                    }
                }
            });
        }
        if (actionLeft != null) {
            actionLeft.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (menuMode[0]) {
                        exitAppBarMenu();
                    } else {
                        finish();
                    }
                }
            });
        }


    }

    /** 菜单态下点应用栏：优先点页面自带的菜单切换控件(tx6)，否则走系统返回。 */
    protected void exitAppBarMenu() {
        View toggle = findViewById(R.id.tx6);
        if (toggle != null && toggle.getVisibility() == View.VISIBLE) {
            toggle.performClick();
        } else {
            onBackPressed();
        }
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
        // Watch-style top time (only present on pages that declare it) must clear the round arc.
        View watchTime = findViewById(R.id.watch_time);
        // 形状改为运行时判定，不再依赖 settings/a3.inf 里手选的设备形状。
        boolean round = ScreenShape.isRound(this);
        boolean withStatusBar = Objects.equals(displayMode, "4");
        if (watchTime != null && !isInAppBar(watchTime)) {
            watchTime.setPadding(0, dp(round ? 12 : 4), 0, 0);
        }
        // 应用栏让位用的上留白有 7 个页面写在内容根上（text_viewer / image_viewer / text_editor /
        // chat_room / x5_browser / chat_gpt / deep_seek）。这里只改左右留白，必须保留已有的上留白，
        // 否则内容会钻到应用栏底下（表现为首行文字被时间/标题压住）。
        int topInset = contentRoot == null ? 0 : contentRoot.getPaddingTop();
        if (withStatusBar) {
            // 状态栏模式：不加内容留白，交给窗口 inset。
            setPadding(contentRoot, 0, topInset, 0, 0);
            return;
        }
        if (round) {
            int horizontal = Math.max(displayMargin, ScreenShape.roundInset(this));
            // 底部不再留白：内容根里的"底部半圆"(负底边距) 要贴到屏幕真实底部，抬高了会露馅
            setPadding(contentRoot, horizontal, topInset, horizontal, 0);
            applyTallTitleBar(titleBarId);
        } else {
            applySquareScreenTitleBar(titleBarId);
            setPadding(contentRoot, 0, topInset, 0, 0);
        }

        // 顶栏左右 Action / 标题按屏幕形状收进安全区
        View appBar = findViewById(R.id.app_bar);
        if (appBar != null) {
            ScreenShape.applyAppBarInsets(this, appBar);
        }
    }

    protected String readDisplayMode() {
        // 新版：屏幕形状存 SharedPreferences（手选优先，未选手自动探测），不再读 settings/a3.inf
        int shape = AppPrefs.getScreenShape(this);
        if (shape == AppPrefs.SHAPE_AUTO) {
            return ScreenShape.isRound(this) ? "1" : "2";
        }
        return String.valueOf(shape);
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
        if (titleBar == null || isInAppBar(titleBar)) {
            return;
        }
        titleBar.setPadding(0, dp(0), 0, dp(2));
        titleBar.setGravity(Gravity.CENTER);
    }

    private void applySquareScreenTitleBar(int titleBarId) {
        TextView titleBar = findViewById(titleBarId);
        if (titleBar == null || isInAppBar(titleBar)) {
            return;
        }
        int verticalPadding = shouldOptimizeDpi() ? dp(3) : dp(9);
        titleBar.setPadding(0, verticalPadding, 0, verticalPadding);
        titleBar.setGravity(Gravity.CENTER);
    }

    /** app_bar 容器内的 watch_time / title_bar 由应用栏自身排版，基类不再干预。 */
    private boolean isInAppBar(View view) {
        android.view.ViewParent parent = view == null ? null : view.getParent();
        while (parent instanceof View) {
            if (((View) parent).getId() == R.id.app_bar) {
                return true;
            }
            parent = parent.getParent();
        }
        return false;
    }
}
