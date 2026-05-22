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
import android.support.v7.widget.SwitchCompat;
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
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
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
    private final View.OnLongClickListener xdbj5LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj5LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj5ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj5Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj3LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj3LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj3Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj7LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj7LongClick(vw);
        }

    };
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
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
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

        i.runlibrary.app.v.kg kg2 = st.kg(R.id.kg2);

        i.runlibrary.app.v.kg kg3 = st.kg(R.id.kg3);

        if (zf.dy(wj.dqwb(set1), "true")) {

        }

        if (zf.dy(wj.dqwb(set4), "true")) {

            kg2.xzzt(true);

        }

        if (zf.dy(wj.dqwb(set5), "true")) {

            kg3.xzzt(true);

        }
        te1 = wj.dqwb(set2);
        st.wb(R.id.wb5).zf(te1);
        te2 = wj.dqwb(set3);

    }

    public void qh() {

        if (st.xxbj(R.id.xxbj1).kjd() == 0) {

            String z = "";
            st.xxbj(R.id.xxbj1).kjd(8);
            st.xxbj(R.id.xxbj4).kjd(0);

            if (mode == 1) {

                z = st.wb(R.id.wb5).zf();
                st.wb(R.id.title_bar).zf("浏览器主页");
                st.bjk(R.id.bjk1).tszf("请输入浏览器主页地址");

            } else if (mode == 2) {

                z = st.wb(R.id.wb8).zf();
                st.wb(R.id.title_bar).zf("启动参数");
                st.bjk(R.id.bjk1).tszf("请输入启动参数");

            }
            st.bjk(R.id.bjk1).zf(z);

        } else {

            st.xxbj(R.id.xxbj1).kjd(0);
            st.xxbj(R.id.xxbj4).kjd(8);
            st.bjk(R.id.bjk1).zf("");
            st.wb(R.id.title_bar).zf("< X5浏览器");

        }

    }

    public void gx() {

        String set2 = AppPaths.appPath("settings/f13/set2.inf");

        String set3 = AppPaths.appPath("settings/f13/set3.inf");

        String b = st.bjk(R.id.bjk1).zf();
        b = zf.qctwkg(b);

        if (mode == 1) {

            String f = e1.urlLockString(b);

            if (zf.dy(f, "0")) {

                e1.tsk("提示", "浏览器主页地址不符合规范");

            } else {

                wj.xrwb(set2, f);
                e1.tsk("提示", "浏览器主页设置成功");

            }

        } else if (mode == 2) {

            if (zf.dy(b, "")) {

                e1.tsk("提示", "启动参数不符合规范");

            } else {

                wj.xrwb(set3, b);
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

        i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(R.id.qtgd1);

        qtgd1.v.setOnGenericMotionListener(new View.OnGenericMotionListener() {
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

        qtgd1.v.requestFocus();

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

        if (zf.dy(st.wb(R.id.title_bar).zf(), "< X5浏览器")) {

            gj.gb();

        }

    }

    private void onKg2CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/f13/set4.inf");

        if (ic) {

            wj.xrwb(set, "true");

        } else {

            wj.xrwb(set, "false");

        }

    }

    private void onXdbj6Click(View vw) {

        i.runlibrary.app.v.kg kg = st.kg(R.id.kg2);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean onXdbj6LongClick(View vw) {

        String te = st.wb(R.id.wb11).zf();

        String lj = st.wb(R.id.wb12).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onKg3CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/f13/set5.inf");

        if (ic) {

            wj.xrwb(set, "true");

        } else {

            wj.xrwb(set, "false");

        }

    }

    private void onXdbj7Click(View vw) {

        i.runlibrary.app.v.kg kg = st.kg(R.id.kg3);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean onXdbj7LongClick(View vw) {

        String te = st.wb(R.id.wb13).zf();

        String lj = st.wb(R.id.wb14).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj3Click(View vw) {
        mode = 1;
        qh();

    }

    private boolean onXdbj3LongClick(View vw) {

        String te = st.wb(R.id.wb4).zf();

        String lj = st.wb(R.id.wb5).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj5Click(View vw) {
        gj.tz(X5CoreInstallActivity.class);

    }

    private boolean onXdbj5LongClick(View vw) {

        String te = st.wb(R.id.wb9).zf();

        String lj = st.wb(R.id.wb10).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx1Click(View vw) {
        gj.gb();

    }

    private boolean onBjk1Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            gj.jmxc(new Runnable() {

                public void run() {

                    i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(R.id.qtgd1);
                    qtgd1.v.requestFocus();

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

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        SwitchCompat ab7dd47b08 = (SwitchCompat) findViewById(ay, vw, R.id.kg2);
        ab7dd47b08.setOnCheckedChangeListener(kg2CheckedChangeListener);

        RelativeLayout i171cedf1eb = (RelativeLayout) findViewById(ay, vw, R.id.xdbj6);
        i171cedf1eb.setOnClickListener(xdbj6ClickListener);
        i171cedf1eb.setOnLongClickListener(xdbj6LongClickListener);

        SwitchCompat i2e1dda294f = (SwitchCompat) findViewById(ay, vw, R.id.kg3);
        i2e1dda294f.setOnCheckedChangeListener(kg3CheckedChangeListener);

        RelativeLayout e585d94488 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj7);
        e585d94488.setOnClickListener(xdbj7ClickListener);
        e585d94488.setOnLongClickListener(xdbj7LongClickListener);

        RelativeLayout i20c2777fab = (RelativeLayout) findViewById(ay, vw, R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);
        i20c2777fab.setOnLongClickListener(xdbj3LongClickListener);

        RelativeLayout c0d3e00d2b = (RelativeLayout) findViewById(ay, vw, R.id.xdbj5);
        c0d3e00d2b.setOnClickListener(xdbj5ClickListener);
        c0d3e00d2b.setOnLongClickListener(xdbj5LongClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        EditText i32ee139ba1 = (EditText) findViewById(ay, vw, R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
