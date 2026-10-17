/**
 ******************************************************************************
 * @file    TextViewerActivity.java
 * @author  Typheye
 * @brief   Text file viewer screen.
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
package open.cn.awg.pro.viewer;


import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.core.GlobalRuntimeState;
import open.cn.awg.pro.R;
import open.cn.awg.pro.settings.TextViewerSettingsActivity;
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;

public class TextViewerActivity extends BaseAwgActivity {

    public final TextViewerActivity lei = this, 类 = this;
    public String qjurl = "";
    public String code = "";
    private final View.OnLongClickListener wb1LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onWb1LongClick(vw);
        }

    };
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnClickListener tx4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx4Click(vw);
        }

    };
    private final View.OnClickListener tx3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx3Click(vw);
        }

    };
    private final View.OnClickListener xxbj2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj2Click(vw);
        }

    };
    private final View.OnClickListener xxbj1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj1Click(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.text_viewer);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        AppRuntimeBridge e1 = new AppRuntimeBridge(TextViewerActivity.this);

        try {

            String url = TextViewerActivity.this.getIntent().getStringExtra(String.valueOf("url"));

            String bt = TextViewerActivity.this.getIntent().getStringExtra(String.valueOf("title"));

            String bts = "";

            if (!java.util.Objects.equals(bt, null) && !java.util.Objects.equals(bt, "")) {

                bts = bt;

            } else {

                String d1_rootpaths;
                d1_rootpaths = GlobalRuntimeState.d1_rootpath;

                String[] fhValue = e1.fileinfo(d1_rootpaths, url);
                bts = fhValue[3];

            }

            if (open.cn.awg.pro.core.NativeFiles.exists(TextViewerActivity.this, (String) (url))) {

                qjurl = url;

                if (open.cn.awg.pro.core.NativeFiles.size(TextViewerActivity.this, (String) (url)) > 102400) {

                    e1.tsk("提示", "文本长度过大,无法进行浏览");
                    open.cn.awg.pro.core.NativeActions.finish(TextViewerActivity.this);

                } else {

                    String t = open.cn.awg.pro.core.NativeFiles.readText(TextViewerActivity.this, (String) (url));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.text)).setText(String.valueOf(t));

                }

            } else {

                e1.tsk("提示", "文件不存在");
                open.cn.awg.pro.core.NativeActions.finish(TextViewerActivity.this);

            }

        } catch (Throwable e) {

            e1.upload_error(e, "d2.*");
            open.cn.awg.pro.core.NativeActions.finish(TextViewerActivity.this);

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        

        applyStandardScreenMode(R.id.text_viewer_root, R.id.xdbj1, R.id.title_bar);
        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(TextViewerActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(TextViewerActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(TextViewerActivity.this, (String) (a2));

        if (java.util.Objects.equals(a, "4")) {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.text_viewer_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(true));

            String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTab);
            setStatusBarColor(clr);

        } else {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.text_viewer_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

        }

        if (java.util.Objects.equals(a, "1")) {

            android.widget.TextView te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.text));
            open.cn.awg.pro.core.NativeViewValues.margin(te, 1, 10);
            open.cn.awg.pro.core.NativeViewValues.margin(te, 3, 200);
            open.cn.awg.pro.core.NativeViewValues.margin(te, 0, 50);
            open.cn.awg.pro.core.NativeViewValues.margin(te, 2, 50);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.title_bar));
            // 应用栏接管标题排版，移除旧运行时 padding
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "2")) {

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (open.cn.awg.pro.core.NativeFiles.exists(TextViewerActivity.this, (String) (a001)) || java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(TextViewerActivity.this, (String) (a001)), "true")) {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.title_bar));
                // 应用栏接管标题排版，移除旧运行时 padding
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            } else {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.title_bar));
                // 应用栏接管标题排版，移除旧运行时 padding
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            }

            android.widget.TextView te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.text));
            open.cn.awg.pro.core.NativeViewValues.margin(te, 1, 10);
            open.cn.awg.pro.core.NativeViewValues.margin(te, 3, 200);
            open.cn.awg.pro.core.NativeViewValues.margin(te, 0, 40);
            open.cn.awg.pro.core.NativeViewValues.margin(te, 2, 40);

        } else if (java.util.Objects.equals(a, "3")) {

            android.widget.TextView te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.text));
            open.cn.awg.pro.core.NativeViewValues.margin(te, 1, 10);
            open.cn.awg.pro.core.NativeViewValues.margin(te, 3, 200);
            open.cn.awg.pro.core.NativeViewValues.margin(te, 0, 50);
            open.cn.awg.pro.core.NativeViewValues.margin(te, 2, 50);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.title_bar));
            // 应用栏接管标题排版，移除旧运行时 padding
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "4")) {

            android.widget.TextView te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.text));
            open.cn.awg.pro.core.NativeViewValues.margin(te, 1, 10);
            open.cn.awg.pro.core.NativeViewValues.margin(te, 3, 200);
            open.cn.awg.pro.core.NativeViewValues.margin(te, 0, 40);
            open.cn.awg.pro.core.NativeViewValues.margin(te, 2, 40);

        }
        gxh();

        String set = AppPaths.appPath("settings/set3.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(TextViewerActivity.this, (String) (set)), "true")) {

            String te = open.cn.awg.pro.core.NativeFiles.readText(TextViewerActivity.this, (String) (AppPaths.appPath("data/assets/a5")));

            String[] name = new String[]{"title", "text"};

            String[] value = new String[]{"新手教程", te};
            open.cn.awg.pro.core.NativeActions.launch(TextViewerActivity.this, (Class<?>) (TextQrDisplayActivity.class), (String[]) (name), (Object[]) (value));

        }

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
            applyWindowModeFromSettings();

    }

    public void onRestart() {
        super.onRestart();
            applyWindowModeFromSettings();
        gxh();

    }

    public void onStart() {
        super.onStart();
            applyWindowModeFromSettings();
        gxh();

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.qtgd1));

        qtgd1.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View vw, MotionEvent me) {
                if (me.getAction() == MotionEvent.ACTION_SCROLL && me.isFromSource(InputDeviceCompat.SOURCE_ROTARY_ENCODER)) {

                    float delta = -me.getAxisValue(MotionEventCompat.AXIS_SCROLL) *
                            ViewConfigurationCompat.getScaledVerticalScrollFactor(
                                    ViewConfiguration.get(lei), lei
                            );

                    vw.scrollBy(0, Math.round(delta));
                    return true;
                }
                return false;
            }
        });

        qtgd1.requestFocus();

    }

    public void onResume() {
        super.onResume();
            applyWindowModeFromSettings();
        gxh();

    }

    public void gxh() {
        code = TextViewerActivity.this.getIntent().getStringExtra(String.valueOf("code"));

        String set2 = AppPaths.appPath("settings/d2/set2.inf");

        int iss = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(TextViewerActivity.this, (String) (set2)));

        android.widget.TextView text = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.text));
        text.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(iss, -1));

        String set3 = AppPaths.appPath("settings/d2/set3.inf");

        String set4 = AppPaths.appPath("settings/d2/set4.inf");

        final String color1 = open.cn.awg.pro.core.NativeFiles.readText(TextViewerActivity.this, (String) (set3));

        final String color2 = open.cn.awg.pro.core.NativeFiles.readText(TextViewerActivity.this, (String) (set4));
        text.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color1));
        open.cn.awg.pro.core.NativeViewValues.typeface(text, "@resource/a7e484743f6796f092cd859819cb5fc8");
        open.cn.awg.pro.core.NativeViewValues.background(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.xdbj1)), color2);

        int psd = 0;

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(TextViewerActivity.this, (String) (a2));

        if (java.util.Objects.equals(a, "1") || java.util.Objects.equals(a, "3")) {

            psd = 2 * open.cn.awg.pro.core.DisplayValues.pxToDp(TextViewerActivity.this, 50);

        } else {

            psd = 2 * open.cn.awg.pro.core.DisplayValues.pxToDp(TextViewerActivity.this, 40);

        }

        String set5 = AppPaths.appPath("settings/d2/set5.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(TextViewerActivity.this, (String) (set5)), "true") || java.util.Objects.equals(code, "super1")) {

            

            int i = open.cn.awg.pro.core.DisplayValues.pxToDp(TextViewerActivity.this, TextViewerActivity.this.getResources().getDisplayMetrics().widthPixels) - psd;
            open.cn.awg.pro.core.NativeViewValues.width(text, i + "dp");

        } else {

            open.cn.awg.pro.core.NativeViewValues.width(text, -1);

        }

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

        }
        return false;

    }

    private void onTitleBarClick(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "文本浏览")) {

            open.cn.awg.pro.core.NativeActions.finish(TextViewerActivity.this);

        }

    }

    private void onXxbj1Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("文本浏览"));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.wb1)).setVisibility(android.view.View.VISIBLE);

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("菜单"));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.wb1)).setVisibility(android.view.View.GONE);

        }

    }

    private void onXxbj2Click(View vw) {

        int i;

    }

    private void onTx3Click(View vw) {

        String[] name = new String[]{"url"};

        String[] value = new String[]{qjurl};
        open.cn.awg.pro.core.NativeActions.launch(TextViewerActivity.this, (Class<?>) (TextEditorActivity.class), (String[]) (name), (Object[]) (value));

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("文本浏览"));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.wb1)).setVisibility(android.view.View.VISIBLE);

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("菜单"));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.wb1)).setVisibility(android.view.View.GONE);

        }
        open.cn.awg.pro.core.NativeActions.finish(TextViewerActivity.this);

    }

    private void onTx4Click(View vw) {

        if (java.util.Objects.equals(code, "super1")) {

            String[] a = new String[]{"code"};

            String[] b = new String[]{"super1"};
            open.cn.awg.pro.core.NativeActions.launch(TextViewerActivity.this, (Class<?>) (TextViewerSettingsActivity.class), (String[]) (a), (Object[]) (b));

        } else {

            open.cn.awg.pro.core.NativeActions.launch(TextViewerActivity.this, (Class<?>) (TextViewerSettingsActivity.class));

        }

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("文本浏览"));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.wb1)).setVisibility(android.view.View.VISIBLE);

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("菜单"));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.wb1)).setVisibility(android.view.View.GONE);

        }

    }

    private void onTx1Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("文本浏览"));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.wb1)).setVisibility(android.view.View.VISIBLE);

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("菜单"));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.wb1)).setVisibility(android.view.View.GONE);

        }
        open.cn.awg.pro.core.NativeActions.finish(TextViewerActivity.this);

    }

    private void onTx2Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("文本浏览"));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.wb1)).setVisibility(android.view.View.VISIBLE);

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("菜单"));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.wb1)).setVisibility(android.view.View.GONE);

        }

    }

    private boolean onWb1LongClick(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("文本浏览"));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.wb1)).setVisibility(android.view.View.VISIBLE);

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("菜单"));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerActivity.this.getWindow().getDecorView(), R.id.wb1)).setVisibility(android.view.View.GONE);

        }
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        LinearLayout e1433e26d6 = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj1);
        e1433e26d6.setOnClickListener(xxbj1ClickListener);

        LinearLayout dc0a1b897b = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj2);
        dc0a1b897b.setOnClickListener(xxbj2ClickListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i11aeec1890 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx4);
        i11aeec1890.setOnClickListener(tx4ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        TextView ff56a8e7f6 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb1);
        ff56a8e7f6.setOnLongClickListener(wb1LongClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
