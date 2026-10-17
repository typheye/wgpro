/**
 ******************************************************************************
 * @file    TextEditorActivity.java
 * @author  Typheye
 * @brief   Text file editor screen.
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
import android.widget.EditText;
import android.widget.ImageView;
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
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;

public class TextEditorActivity extends BaseAwgActivity {

    public final TextEditorActivity lei = this, 类 = this;
    public AppRuntimeBridge e1;
    public String url = "";
    private final View.OnClickListener tx3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx3Click(vw);
        }

    };
    private final View.OnTouchListener bjk1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjk1Touch(vw, me);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        e1 = new AppRuntimeBridge(this);
        setContentView(R.layout.text_editor);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        AppRuntimeBridge e1 = new AppRuntimeBridge(TextEditorActivity.this);

        try {

            url = TextEditorActivity.this.getIntent().getStringExtra(String.valueOf("url"));

            String d1_rootpaths;
            d1_rootpaths = GlobalRuntimeState.d1_rootpath;

            String[] fhValue = e1.fileinfo(d1_rootpaths, url);

            String bts = "";

            if (java.util.Objects.equals(fhValue[3], "")) {

                bts = "文本编辑器";

            } else {

                bts = fhValue[3];

            }

            if (open.cn.awg.pro.core.NativeFiles.exists(TextEditorActivity.this, (String) (url))) {

                if (open.cn.awg.pro.core.NativeFiles.size(TextEditorActivity.this, (String) (url)) > 102400) {

                    e1.tsk("提示", "文本长度过大,无法进行编辑");
                    open.cn.awg.pro.core.NativeActions.finish(TextEditorActivity.this);

                } else {

                    String t = open.cn.awg.pro.core.NativeFiles.readText(TextEditorActivity.this, (String) (url));

                    android.widget.EditText text = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(TextEditorActivity.this.getWindow().getDecorView(), R.id.bjk1));
                    open.cn.awg.pro.core.NativeViewValues.typeface(text, "@resource/a7e484743f6796f092cd859819cb5fc8");
                    text.setText(String.valueOf(t));

                }

            } else {

                e1.tsk("提示", "文件不存在");
                open.cn.awg.pro.core.NativeActions.finish(TextEditorActivity.this);

            }

        } catch (Throwable e) {

            e1.upload_error(e, "d5.*");
            open.cn.awg.pro.core.NativeActions.finish(TextEditorActivity.this);

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        

        applyStandardScreenMode(R.id.text_editor_root, R.id.xdbj1, R.id.title_bar);
        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(TextEditorActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(TextEditorActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(TextEditorActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(TextEditorActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));

        if (java.util.Objects.equals(a, "4")) {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextEditorActivity.this.getWindow().getDecorView(), R.id.text_editor_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(true));

            String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTab);
            setStatusBarColor(clr);

        } else {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextEditorActivity.this.getWindow().getDecorView(), R.id.text_editor_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

        }

        if (java.util.Objects.equals(a, "1")) {

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextEditorActivity.this.getWindow().getDecorView(), R.id.title_bar));
            // 应用栏接管标题排版，移除旧运行时 padding
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextEditorActivity.this.getWindow().getDecorView(), R.id.xxbj2)), u, 0, u, 50);

        } else if (java.util.Objects.equals(a, "2")) {

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (open.cn.awg.pro.core.NativeFiles.exists(TextEditorActivity.this, (String) (a001)) || java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(TextEditorActivity.this, (String) (a001)), "true")) {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextEditorActivity.this.getWindow().getDecorView(), R.id.title_bar));
                // 应用栏接管标题排版，移除旧运行时 padding
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            } else {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextEditorActivity.this.getWindow().getDecorView(), R.id.title_bar));
                // 应用栏接管标题排版，移除旧运行时 padding
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            }
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextEditorActivity.this.getWindow().getDecorView(), R.id.xxbj2)), 0, 0, 0, 0);

        } else if (java.util.Objects.equals(a, "3")) {

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextEditorActivity.this.getWindow().getDecorView(), R.id.title_bar));
            // 应用栏接管标题排版，移除旧运行时 padding
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextEditorActivity.this.getWindow().getDecorView(), R.id.xxbj2)), u, 0, u, 50);

        } else if (java.util.Objects.equals(a, "4")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextEditorActivity.this.getWindow().getDecorView(), R.id.xxbj2)), 0, 0, 0, 0);

        }

        String set2 = AppPaths.appPath("settings/d2/set2.inf");

        int iss = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(TextEditorActivity.this, (String) (set2)));

        android.widget.EditText text = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(TextEditorActivity.this.getWindow().getDecorView(), R.id.bjk1));

        String set3 = AppPaths.appPath("settings/d2/set3.inf");

        String set4 = AppPaths.appPath("settings/d2/set4.inf");

        final String color1 = open.cn.awg.pro.core.NativeFiles.readText(TextEditorActivity.this, (String) (set3));

        final String color2 = open.cn.awg.pro.core.NativeFiles.readText(TextEditorActivity.this, (String) (set4));

        String set = AppPaths.appPath("settings/set3.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(TextEditorActivity.this, (String) (set)), "true")) {

            String te = open.cn.awg.pro.core.NativeFiles.readText(TextEditorActivity.this, (String) (AppPaths.appPath("data/assets/a15")));

            String[] name = new String[]{"title", "text"};

            String[] value = new String[]{"新手教程", te};
            open.cn.awg.pro.core.NativeActions.launch(TextEditorActivity.this, (Class<?>) (TextQrDisplayActivity.class), (String[]) (name), (Object[]) (value));

        }

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
            applyWindowModeFromSettings();

    }

    public void onRestart() {
        super.onRestart();
            applyWindowModeFromSettings();

    }

    public void onStart() {
        super.onStart();
            applyWindowModeFromSettings();

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(TextEditorActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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
        open.cn.awg.pro.core.NativeActions.finish(TextEditorActivity.this);

    }

    private boolean onBjk1Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(TextEditorActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private void onTx3Click(View vw) {

        String te = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(TextEditorActivity.this.getWindow().getDecorView(), R.id.bjk1)).getText().toString();

        if (open.cn.awg.pro.core.NativeFiles.writeText(TextEditorActivity.this, (String) (url), (String) (te))) {

            e1.tsk("提示", "保存成功");

        } else {

            e1.tsk("提示", "保存失败");

        }

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        EditText i32ee139ba1 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
