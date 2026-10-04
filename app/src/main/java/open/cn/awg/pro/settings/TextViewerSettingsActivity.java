/**
 ******************************************************************************
 * @file    TextViewerSettingsActivity.java
 * @author  Typheye
 * @brief   Configures text viewer and editor display options.
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
package open.cn.awg.pro.settings;


import android.app.Activity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.SwitchCompat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class TextViewerSettingsActivity extends BaseAwgActivity {

    public final TextViewerSettingsActivity lei = this, 类 = this;
    public String lin = "";
    public String newcolor = "";
    public int bs = 0;
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
    private final SeekBar.OnSeekBarChangeListener tdt4SeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        public void onProgressChanged(SeekBar vw, int ps, boolean fu) {
            onTdt4ProgressChanged(vw, ps, fu);
        }

        public void onStartTrackingTouch(SeekBar vw) {
            onTdt4StartTrackingTouch(vw);
        }

        public void onStopTrackingTouch(SeekBar vw) {
            onTdt4StopTrackingTouch(vw);
        }

    };
    private final SeekBar.OnSeekBarChangeListener tdt3SeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        public void onProgressChanged(SeekBar vw, int ps, boolean fu) {
            onTdt3ProgressChanged(vw, ps, fu);
        }

        public void onStartTrackingTouch(SeekBar vw) {
            onTdt3StartTrackingTouch(vw);
        }

        public void onStopTrackingTouch(SeekBar vw) {
            onTdt3StopTrackingTouch(vw);
        }

    };
    private final SeekBar.OnSeekBarChangeListener tdt2SeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        public void onProgressChanged(SeekBar vw, int ps, boolean fu) {
            onTdt2ProgressChanged(vw, ps, fu);
        }

        public void onStartTrackingTouch(SeekBar vw) {
            onTdt2StartTrackingTouch(vw);
        }

        public void onStopTrackingTouch(SeekBar vw) {
            onTdt2StopTrackingTouch(vw);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj53LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj53LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj53ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj53Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj52LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj52LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj52ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj52Click(vw);
        }

    };
    private final SeekBar.OnSeekBarChangeListener tdt1SeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        public void onProgressChanged(SeekBar vw, int ps, boolean fu) {
            onTdt1ProgressChanged(vw, ps, fu);
        }

        public void onStartTrackingTouch(SeekBar vw) {
            onTdt1StartTrackingTouch(vw);
        }

        public void onStopTrackingTouch(SeekBar vw) {
            onTdt1StopTrackingTouch(vw);
        }

    };
    private final View.OnLongClickListener xdbj51LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj51LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj51ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj51Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj6LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj6LongClick(vw);
        }

    };
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
    private final View.OnLongClickListener xdbj2LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj2LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj2Click(vw);
        }

    };
    private final CompoundButton.OnCheckedChangeListener kg1CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onKg1CheckedChanged(vw, ic);
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
        setContentView(R.layout.text_viewer_settings);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(TextViewerSettingsActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(TextViewerSettingsActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(TextViewerSettingsActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(TextViewerSettingsActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));

        if (java.util.Objects.equals(a, "4")) {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.text_viewer_settings_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(true));

            String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTab);
            setStatusBarColor(clr);

        } else {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.text_viewer_settings_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

        }

        if (java.util.Objects.equals(a, "1")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.xdbj1)), u, 0, u, 50);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.title_bar));
            open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "15dp", 0, "3dp");
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "2")) {

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (open.cn.awg.pro.core.NativeFiles.exists(TextViewerSettingsActivity.this, (String) (a001)) || java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(TextViewerSettingsActivity.this, (String) (a001)), "true")) {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.title_bar));
                open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "3dp", 0, "3dp");
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            } else {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.title_bar));
                open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "9dp", 0, "9dp");
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            }
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.xdbj1)), 0, 0, 0, 0);

        } else if (java.util.Objects.equals(a, "3")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.xdbj1)), u, 0, u, 50);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.title_bar));
            open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "15dp", 0, "3dp");
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "4")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.xdbj1)), 0, 0, 0, 0);

        }

    }

    public void jz() {

        String set1 = AppPaths.appPath("settings/d2/set1.inf");

        String set2 = AppPaths.appPath("settings/d2/set2.inf");

        String set3 = AppPaths.appPath("settings/d2/set3.inf");

        String set4 = AppPaths.appPath("settings/d2/set4.inf");

        String set5 = AppPaths.appPath("settings/d2/set5.inf");

        android.widget.CompoundButton kg1 = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.kg1));

        android.widget.CompoundButton kg2 = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.kg2));

        androidx.cardview.widget.CardView kp2 = ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.kp2));

        androidx.cardview.widget.CardView kp3 = ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.kp3));

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(TextViewerSettingsActivity.this, (String) (set1)), "true")) {

            kg1.setChecked(java.lang.Boolean.TRUE.equals(true));

        }

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(TextViewerSettingsActivity.this, (String) (set5)), "true")) {

            kg2.setChecked(java.lang.Boolean.TRUE.equals(true));

        }

        int i = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(TextViewerSettingsActivity.this, (String) (set2)));

        android.widget.SeekBar tdt1 = ((android.widget.SeekBar) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.tdt1));
        tdt1.setProgress(open.cn.awg.pro.core.TextValues.intValue(i, -1));
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.wb10)).setText(String.valueOf(i + "sp"));

        String ztys = open.cn.awg.pro.core.NativeFiles.readText(TextViewerSettingsActivity.this, (String) (set3));
        kp2.setCardBackgroundColor(open.cn.awg.pro.core.NativeViewValues.color(ztys));

        String bjys = open.cn.awg.pro.core.NativeFiles.readText(TextViewerSettingsActivity.this, (String) (set4));
        kp3.setCardBackgroundColor(open.cn.awg.pro.core.NativeViewValues.color(bjys));
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.wb5)).setText(String.valueOf(ztys));
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.wb7)).setText(String.valueOf(bjys));

        String code = TextViewerSettingsActivity.this.getIntent().getStringExtra(String.valueOf("code"));

        if (java.util.Objects.equals(code, "super1")) {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.xdbj6)).setVisibility(android.view.View.GONE);

        }

    }

    public void rgbsx() {

        int r = ((android.widget.SeekBar) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.tdt2)).getProgress();

        int g = ((android.widget.SeekBar) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.tdt3)).getProgress();

        int b = ((android.widget.SeekBar) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.tdt4)).getProgress();

        String r1 = "";

        String g1 = "";

        String b1 = "";
        r1 = Integer.toHexString(r);

        if (r1.length() == 1) {

            r1 = "0" + r1;

        }
        g1 = Integer.toHexString(g);

        if (g1.length() == 1) {

            g1 = "0" + g1;

        }
        b1 = Integer.toHexString(b);

        if (b1.length() == 1) {

            b1 = "0" + b1;

        }

        String color = "#" + r1 + g1 + b1;
        newcolor = color;

        androidx.cardview.widget.CardView yst = ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.kp4));
        yst.setCardBackgroundColor(open.cn.awg.pro.core.NativeViewValues.color(color));

    }

    public void rgbcsh(int o) {

        final int tsq = o;

        int r1 = 0;

        int g1 = 0;

        int b1 = 0;

        String set1 = AppPaths.appPath("settings/d2/set3.inf");

        String set2 = AppPaths.appPath("settings/d2/set4.inf");

        final String color1 = open.cn.awg.pro.core.NativeFiles.readText(TextViewerSettingsActivity.this, (String) (set1));

        final String color2 = open.cn.awg.pro.core.NativeFiles.readText(TextViewerSettingsActivity.this, (String) (set2));

        String color = "";

        if (tsq == 1) {

            bs = 1;
            color = color1;

        } else if (tsq == 2) {

            bs = 2;
            color = color2;

        }

        String r = open.cn.awg.pro.core.TextValues.slice(color, 1, 3);

        String g = open.cn.awg.pro.core.TextValues.slice(color, 3, 5);

        String b = open.cn.awg.pro.core.TextValues.slice(color, 5, null);
        r1 = Integer.parseInt(r, 16);
        g1 = Integer.parseInt(g, 16);
        b1 = Integer.parseInt(b, 16);

        final int r2 = r1;

        final int g2 = g1;

        final int b2 = b1;
        ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.kp4)).setCardBackgroundColor(open.cn.awg.pro.core.NativeViewValues.color(color));
        ((android.widget.SeekBar) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.tdt2)).setProgress(open.cn.awg.pro.core.TextValues.intValue(r2, -1));
        ((android.widget.SeekBar) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.tdt3)).setProgress(open.cn.awg.pro.core.TextValues.intValue(g2, -1));
        ((android.widget.SeekBar) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.tdt4)).setProgress(open.cn.awg.pro.core.TextValues.intValue(b2, -1));
        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);
        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(android.view.View.VISIBLE);
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("编辑颜色"));

    }

    public void rgbbc() {

        String set1 = AppPaths.appPath("settings/d2/set3.inf");

        String set2 = AppPaths.appPath("settings/d2/set4.inf");

        if (bs == 1) {

            open.cn.awg.pro.core.NativeFiles.writeText(TextViewerSettingsActivity.this, (String) (set1), (String) (newcolor));

        } else if (bs == 2) {

            open.cn.awg.pro.core.NativeFiles.writeText(TextViewerSettingsActivity.this, (String) (set2), (String) (newcolor));

        }
        jz();
        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(android.view.View.GONE);
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("< 文本浏览"));

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

            if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.xxbj6)).getVisibility() == android.view.View.VISIBLE) {

            } else {

                open.cn.awg.pro.core.NativeActions.finish(TextViewerSettingsActivity.this);

            }

        }
        return false;

    }

    private void onTitleBarClick(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "< 文本浏览")) {

            open.cn.awg.pro.core.NativeActions.finish(TextViewerSettingsActivity.this);

        }

    }

    private void onKg1CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/d2/set1.inf");

        if (ic) {

            open.cn.awg.pro.core.NativeFiles.writeText(TextViewerSettingsActivity.this, (String) (set), (String) ("true"));

        } else {

            open.cn.awg.pro.core.NativeFiles.writeText(TextViewerSettingsActivity.this, (String) (set), (String) ("false"));

        }

    }

    private void onXdbj2Click(View vw) {

        android.widget.CompoundButton kg = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.kg1));

        if (!kg.isChecked()) {

            kg.setChecked(java.lang.Boolean.TRUE.equals(true));

        } else {

            kg.setChecked(java.lang.Boolean.TRUE.equals(false));

        }

    }

    private boolean onXdbj2LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.wb2)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.wb3)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onKg2CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/d2/set5.inf");

        if (ic) {

            open.cn.awg.pro.core.NativeFiles.writeText(TextViewerSettingsActivity.this, (String) (set), (String) ("true"));

        } else {

            open.cn.awg.pro.core.NativeFiles.writeText(TextViewerSettingsActivity.this, (String) (set), (String) ("false"));

        }

    }

    private void onXdbj6Click(View vw) {

        android.widget.CompoundButton kg = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.kg2));

        if (!kg.isChecked()) {

            kg.setChecked(java.lang.Boolean.TRUE.equals(true));

        } else {

            kg.setChecked(java.lang.Boolean.TRUE.equals(false));

        }

    }

    private boolean onXdbj6LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.wb12)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.wb13)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj51Click(View vw) {

        int i;

    }

    private boolean onXdbj51LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.wb9)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.wb10)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTdt1ProgressChanged(SeekBar vw, int ps, boolean fu) {

        String set = AppPaths.appPath("settings/d2/set2.inf");

        android.widget.SeekBar tdt1 = ((android.widget.SeekBar) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.tdt1));

        int i = tdt1.getProgress();
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.wb10)).setText(String.valueOf(i + "sp"));

        String sis = open.cn.awg.pro.core.TextValues.string(i);
        open.cn.awg.pro.core.NativeFiles.writeText(TextViewerSettingsActivity.this, (String) (set), (String) (sis));

    }

    private void onTdt1StartTrackingTouch(SeekBar vw) {

    }

    private void onTdt1StopTrackingTouch(SeekBar vw) {

    }

    private void onXdbj52Click(View vw) {
        rgbcsh(1);

    }

    private boolean onXdbj52LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.wb4)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.wb5)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj53Click(View vw) {
        rgbcsh(2);

    }

    private boolean onXdbj53LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.wb6)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.wb7)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(TextViewerSettingsActivity.this);

    }

    private void onTdt2ProgressChanged(SeekBar vw, int ps, boolean fu) {
        rgbsx();

    }

    private void onTdt2StartTrackingTouch(SeekBar vw) {

    }

    private void onTdt2StopTrackingTouch(SeekBar vw) {

    }

    private void onTdt3ProgressChanged(SeekBar vw, int ps, boolean fu) {
        rgbsx();

    }

    private void onTdt3StartTrackingTouch(SeekBar vw) {

    }

    private void onTdt3StopTrackingTouch(SeekBar vw) {

    }

    private void onTdt4ProgressChanged(SeekBar vw, int ps, boolean fu) {
        rgbsx();

    }

    private void onTdt4StartTrackingTouch(SeekBar vw) {

    }

    private void onTdt4StopTrackingTouch(SeekBar vw) {

    }

    private void onTx2Click(View vw) {
        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(android.view.View.GONE);
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextViewerSettingsActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("< 文本浏览"));

    }

    private void onTx3Click(View vw) {
        rgbbc();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        SwitchCompat i1bca89f073 = (SwitchCompat) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.kg1);
        i1bca89f073.setOnCheckedChangeListener(kg1CheckedChangeListener);

        RelativeLayout i3167b45174 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);
        i3167b45174.setOnLongClickListener(xdbj2LongClickListener);

        SwitchCompat ab7dd47b08 = (SwitchCompat) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.kg2);
        ab7dd47b08.setOnCheckedChangeListener(kg2CheckedChangeListener);

        RelativeLayout i171cedf1eb = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj6);
        i171cedf1eb.setOnClickListener(xdbj6ClickListener);
        i171cedf1eb.setOnLongClickListener(xdbj6LongClickListener);

        RelativeLayout i248435a9e2 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj51);
        i248435a9e2.setOnClickListener(xdbj51ClickListener);
        i248435a9e2.setOnLongClickListener(xdbj51LongClickListener);

        SeekBar d11169f9c0 = (SeekBar) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tdt1);
        d11169f9c0.setOnSeekBarChangeListener(tdt1SeekBarChangeListener);

        RelativeLayout c04397461c = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj52);
        c04397461c.setOnClickListener(xdbj52ClickListener);
        c04397461c.setOnLongClickListener(xdbj52LongClickListener);

        CardView i4746b35274 = (CardView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.kp2);

        RelativeLayout i172cf45127 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj53);
        i172cf45127.setOnClickListener(xdbj53ClickListener);
        i172cf45127.setOnLongClickListener(xdbj53LongClickListener);

        CardView d70d738137 = (CardView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.kp3);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        SeekBar i8c36c72cce = (SeekBar) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tdt2);
        i8c36c72cce.setOnSeekBarChangeListener(tdt2SeekBarChangeListener);

        SeekBar i7843ee147b = (SeekBar) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tdt3);
        i7843ee147b.setOnSeekBarChangeListener(tdt3SeekBarChangeListener);

        SeekBar b753c6bb90 = (SeekBar) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tdt4);
        b753c6bb90.setOnSeekBarChangeListener(tdt4SeekBarChangeListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
