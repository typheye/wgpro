/**
 ******************************************************************************
 * @file    ExtensionPluginActivity.java
 * @author  Typheye
 * @brief   Lists extension plugins available to the app.
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
package open.cn.awg.pro.plugin;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class ExtensionPluginActivity extends BaseAwgActivity {

    public static String betaurl = "";
    public final ExtensionPluginActivity lei = this, 类 = this;
    public ExtensionPluginBridge e14 = new ExtensionPluginBridge(_APPINFO);

    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    private final View.OnTouchListener xxbj4TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj4Touch(vw, me);
        }

    };
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };
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
    private final View.OnTouchListener xxbj2TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj2Touch(vw, me);
        }

    };
    private final View.OnTouchListener xxbj3TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj3Touch(vw, me);
        }

    };
    private final View.OnTouchListener xxbj1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj1Touch(vw, me);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnClickListener wb1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb1Click(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.extension_plugin);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.extension_plugin_root, R.id.xdbj1, R.id.title_bar);

    }

    public void jz() {

        String set1 = AppPaths.appPath("settings/f7/set1.inf");

        if (zf.dy(wj.dqwb(set1), "true")) {

        }
        e14.csh();
        e14.cjlbview(0);
        e14.cjlbsx();

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

        if (zf.dy(st.wb(R.id.title_bar).zf(), "< 扩展插件")) {

            gj.gb();

        }

    }

    private void onWb1Click(View vw) {
        e14.cjlbcd(1);

    }

    private void onTx1Click(View vw) {
        e14.cjlbcd(1);

    }

    private boolean onXxbj1Touch(View vw, MotionEvent me) {
        return true;

    }

    private boolean onXxbj3Touch(View vw, MotionEvent me) {
        return true;

    }

    private boolean onXxbj2Touch(View vw, MotionEvent me) {
        return true;

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

    private void onTx3Click(View vw) {
        e14.cjlbcd(0);

    }

    private void onTx2Click(View vw) {
        e14.bjsj();

    }

    private boolean onXxbj4Touch(View vw, MotionEvent me) {
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        TextView ff56a8e7f6 = (TextView) findViewById(ay, vw, R.id.wb1);
        ff56a8e7f6.setOnClickListener(wb1ClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        LinearLayout e1433e26d6 = (LinearLayout) findViewById(ay, vw, R.id.xxbj1);
        e1433e26d6.setOnTouchListener(xxbj1TouchListener);

        LinearLayout efa568dfe6 = (LinearLayout) findViewById(ay, vw, R.id.xxbj3);
        efa568dfe6.setOnTouchListener(xxbj3TouchListener);

        LinearLayout dc0a1b897b = (LinearLayout) findViewById(ay, vw, R.id.xxbj2);
        dc0a1b897b.setOnTouchListener(xxbj2TouchListener);

        EditText i32ee139ba1 = (EditText) findViewById(ay, vw, R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        LinearLayout i9068f5d66e = (LinearLayout) findViewById(ay, vw, R.id.xxbj4);
        i9068f5d66e.setOnTouchListener(xxbj4TouchListener);

        __layoutIsLoaded(ay, vw);
    }
}
