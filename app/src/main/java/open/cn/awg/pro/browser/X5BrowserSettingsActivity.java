/**
 ******************************************************************************
 * @file    X5BrowserSettingsActivity.java
 * @author  Typheye
 * @brief   X5 browser settings screen.
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
package open.cn.awg.pro.browser;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.widget.SwitchCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class X5BrowserSettingsActivity extends BaseAwgActivity {

    public final X5BrowserSettingsActivity lei = this, 类 = this;
    public int mode = 0;
    public AppRuntimeBridge e1;
    private final View.OnClickListener tx3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx3Click(vw);
        }

    };
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };
    private final View.OnTouchListener bjk1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjk1Touch(vw, me);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj5ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj5Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj3Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj7ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj7Click(vw);
        }

    };
    private final CompoundButton.OnCheckedChangeListener kg3CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onKg3CheckedChanged(vw, ic);
        }

    };
;
    private final View.OnClickListener xdbj6ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj6Click(vw);
        }

    };
    private final CompoundButton.OnCheckedChangeListener kg2CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onKg2CheckedChanged(vw, ic);
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
        setContentView(R.layout.x5_browser_settings);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.x5_browser_settings_root, R.id.xdbj1, R.id.title_bar);

    }

    public void jz() {

        String te1 = "";

        String te2 = "";

        String set1 = AppPaths.appPath("settings/f13/set1.inf");

        String set2 = AppPaths.appPath("settings/f13/set2.inf");

        String set3 = AppPaths.appPath("settings/f13/set3.inf");

        String set4 = AppPaths.appPath("settings/f13/set4.inf");

        String set5 = AppPaths.appPath("settings/f13/set5.inf");

        android.widget.CompoundButton kg2 = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.kg2));

        android.widget.CompoundButton kg3 = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.kg3));

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(X5BrowserSettingsActivity.this, (String) (set1)), "true")) {

        }

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(X5BrowserSettingsActivity.this, (String) (set4)), "true")) {

            kg2.setChecked(java.lang.Boolean.TRUE.equals(true));

        }

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(X5BrowserSettingsActivity.this, (String) (set5)), "true")) {

            kg3.setChecked(java.lang.Boolean.TRUE.equals(true));

        }
        te1 = open.cn.awg.pro.core.NativeFiles.readText(X5BrowserSettingsActivity.this, (String) (set2));
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.wb5)).setText(String.valueOf(te1));
        te2 = open.cn.awg.pro.core.NativeFiles.readText(X5BrowserSettingsActivity.this, (String) (set3));

    }

    public void qh() {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.xxbj1)).getVisibility() == android.view.View.VISIBLE) {

            String z = "";
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(android.view.View.VISIBLE);

            if (mode == 1) {

                z = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.wb5)).getText().toString();
                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("浏览器主页"));
                ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.bjk1)).setHint(String.valueOf("请输入浏览器主页地址"));

            } else if (mode == 2) {

                z = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.wb8)).getText().toString();
                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("启动参数"));
                ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.bjk1)).setHint(String.valueOf("请输入启动参数"));

            }
            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(z));

        } else {

            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(android.view.View.GONE);
            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(""));
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("X5浏览器"));

        }

    }

    public void gx() {

        String set2 = AppPaths.appPath("settings/f13/set2.inf");

        String set3 = AppPaths.appPath("settings/f13/set3.inf");

        String b = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.bjk1)).getText().toString();
        b = open.cn.awg.pro.core.TextValues.trim(b);

        if (mode == 1) {

            String f = e1.urlLockString(b);

            if (java.util.Objects.equals(f, "0")) {

                e1.tsk("提示", "浏览器主页地址不符合规范");

            } else {

                open.cn.awg.pro.core.NativeFiles.writeText(X5BrowserSettingsActivity.this, (String) (set2), (String) (f));
                e1.tsk("提示", "浏览器主页设置成功");

            }

        } else if (mode == 2) {

            if (java.util.Objects.equals(b, "")) {

                e1.tsk("提示", "启动参数不符合规范");

            } else {

                open.cn.awg.pro.core.NativeFiles.writeText(X5BrowserSettingsActivity.this, (String) (set3), (String) (b));
                e1.tsk("提示", "启动参数设置成功");

            }

        }
        jz();
        qh();

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

    private void onTitleBarClick(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "X5浏览器")) {

            open.cn.awg.pro.core.NativeActions.finish(X5BrowserSettingsActivity.this);

        }

    }

    private void onKg2CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/f13/set4.inf");

        if (ic) {

            open.cn.awg.pro.core.NativeFiles.writeText(X5BrowserSettingsActivity.this, (String) (set), (String) ("true"));

        } else {

            open.cn.awg.pro.core.NativeFiles.writeText(X5BrowserSettingsActivity.this, (String) (set), (String) ("false"));

        }

    }

    private void onXdbj6Click(View vw) {

        android.widget.CompoundButton kg = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.kg2));

        if (!kg.isChecked()) {

            kg.setChecked(java.lang.Boolean.TRUE.equals(true));

        } else {

            kg.setChecked(java.lang.Boolean.TRUE.equals(false));

        }

    }



    private void onKg3CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/f13/set5.inf");

        if (ic) {

            open.cn.awg.pro.core.NativeFiles.writeText(X5BrowserSettingsActivity.this, (String) (set), (String) ("true"));

        } else {

            open.cn.awg.pro.core.NativeFiles.writeText(X5BrowserSettingsActivity.this, (String) (set), (String) ("false"));

        }

    }

    private void onXdbj7Click(View vw) {

        android.widget.CompoundButton kg = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.kg3));

        if (!kg.isChecked()) {

            kg.setChecked(java.lang.Boolean.TRUE.equals(true));

        } else {

            kg.setChecked(java.lang.Boolean.TRUE.equals(false));

        }

    }



    private void onXdbj3Click(View vw) {
        mode = 1;
        qh();

    }



    private void onXdbj5Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(X5BrowserSettingsActivity.this, (Class<?>) (X5CoreInstallActivity.class));

    }



    private void onTx1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(X5BrowserSettingsActivity.this);

    }

    private boolean onBjk1Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserSettingsActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private void onTx2Click(View vw) {
        qh();

    }

    private void onTx3Click(View vw) {
        gx();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        SwitchCompat ab7dd47b08 = (SwitchCompat) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.kg2);
        ab7dd47b08.setOnCheckedChangeListener(kg2CheckedChangeListener);

        RelativeLayout i171cedf1eb = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj6);
        i171cedf1eb.setOnClickListener(xdbj6ClickListener);

        SwitchCompat i2e1dda294f = (SwitchCompat) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.kg3);
        i2e1dda294f.setOnCheckedChangeListener(kg3CheckedChangeListener);

        RelativeLayout e585d94488 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj7);
        e585d94488.setOnClickListener(xdbj7ClickListener);

        RelativeLayout i20c2777fab = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);

        RelativeLayout c0d3e00d2b = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj5);
        c0d3e00d2b.setOnClickListener(xdbj5ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        EditText i32ee139ba1 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
