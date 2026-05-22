/**
 ******************************************************************************
 * @file    TerminalCompatibilityActivity.java
 * @author  Typheye
 * @brief   Terminal compatibility screen.
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
package open.cn.awg.pro.tools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class TerminalCompatibilityActivity extends BaseAwgActivity {

    public final TerminalCompatibilityActivity lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public String p = "";
    public String q = "";
    public String x = "";
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
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnClickListener an4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn4Click(vw);
        }

    };
    private final View.OnClickListener an3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn3Click(vw);
        }

    };
    private final View.OnClickListener an2sClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn2sClick(vw);
        }

    };
    private final View.OnClickListener an2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn2Click(vw);
        }

    };
    private final View.OnClickListener an1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn1Click(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.terminal_compatibility);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        String a1 = AppPaths.appPath("settings/a3.inf");

        String a001 = AppPaths.appPath("settings/dpi.inf");
        p = wj.dqwb(a1);

        if (wj.cz(a001) && zf.dy(wj.dqwb(a001), "true")) {

            q = "true";

        }

        if (zf.dy(p, "1")) {

            st.an(R.id.an1).kjd(8);

        } else if (zf.dy(p, "2") && zf.dy(q, "")) {

            st.an(R.id.an2).kjd(8);

        } else if (zf.dy(p, "2") && zf.dy(q, "true")) {

            st.an(R.id.an2s).kjd(8);

        } else if (zf.dy(p, "3")) {

            st.an(R.id.an3).kjd(8);

        } else if (zf.dy(p, "4")) {

            st.an(R.id.an4).kjd(8);

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.terminal_compatibility_root, R.id.xdbj1, R.id.title_bar);

    }

    public void show(int i_) {

        final int i = i_;

        final i.runlibrary.app.v.xxbj xxbj1 = st.xxbj(R.id.xxbj1);

        final i.runlibrary.app.v.xxbj xxbj2 = st.xxbj(R.id.xxbj2);

        final i.runlibrary.app.v.xxbj xxbj5 = st.xxbj(R.id.xxbj5);

        final i.runlibrary.app.v.wb tab = st.wb(R.id.title_bar);
        gj.jmxc(new Runnable() {

            public void run() {

                if (i == 0) {

                    tab.zf("< 适配终端");
                    xxbj1.kjd(0);
                    xxbj2.kjd(8);
                    xxbj5.kjd(8);

                } else if (i == 1) {

                    tab.zf("适配终端");
                    xxbj1.kjd(8);
                    xxbj2.kjd(0);
                    xxbj5.kjd(8);

                } else if (i == 2) {

                    tab.zf("适配终端");
                    xxbj1.kjd(8);
                    xxbj2.kjd(8);
                    xxbj5.kjd(0);

                }

            }

        });

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

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

            if (zf.dy(st.wb(R.id.title_bar).zf(), "< 适配终端")) {

                gj.gb();

            }

        }
        return false;

    }

    private void onTitleBarClick(View vw) {

        if (zf.dy(st.wb(R.id.title_bar).zf(), "< 适配终端")) {

            gj.gb();

        }

    }

    private void onAn1Click(View vw) {
        p = "1";
        show(1);

    }

    private void onAn2Click(View vw) {
        p = "2";
        show(1);

    }

    private void onAn2sClick(View vw) {
        p = "2";
        q = "true";
        show(1);

    }

    private void onAn3Click(View vw) {
        p = "3";
        show(1);

    }

    private void onAn4Click(View vw) {
        p = "4";
        show(1);

    }

    private void onTx1Click(View vw) {
        gj.gb();

    }

    private void onTx2Click(View vw) {
        show(0);

    }

    private void onTx3Click(View vw) {
        gj.xc(new Thread() {

            public void run() {

                show(2);

                String a1 = AppPaths.appPath("settings/a3.inf");

                String a001 = AppPaths.appPath("settings/dpi.inf");

                if (zf.dy(p, "1")) {

                    wj.xrwb(a1, "1");
                    wj.sc(a001);

                } else if (zf.dy(p, "2") && zf.dy(q, "")) {

                    wj.xrwb(a1, "2");
                    wj.sc(a001);

                } else if (zf.dy(p, "2") && zf.dy(q, "true")) {

                    wj.xrwb(a1, "2");
                    wj.xrwb(a001, "true");

                } else if (zf.dy(p, "3")) {

                    wj.xrwb(a1, "3");
                    wj.sc(a001);

                } else if (zf.dy(p, "4")) {

                    wj.xrwb(a1, "4");
                    wj.sc(a001);

                }
                gj.zt(2000);
                gj.jmxc(new Runnable() {

                    public void run() {

                        Intent intent = lei.getPackageManager().getLaunchIntentForPackage(lei.getPackageName());
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        lei.startActivity(intent);
                        AwgProApplication.getInstance().exit();

                    }

                });

            }

        });

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        Button i171ea34f1a = (Button) findViewById(ay, vw, R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        Button i0b66a667b1 = (Button) findViewById(ay, vw, R.id.an2);
        i0b66a667b1.setOnClickListener(an2ClickListener);

        Button i0afd0fa55f = (Button) findViewById(ay, vw, R.id.an2s);
        i0afd0fa55f.setOnClickListener(an2sClickListener);

        Button i8f51a35692 = (Button) findViewById(ay, vw, R.id.an3);
        i8f51a35692.setOnClickListener(an3ClickListener);

        Button i74ce6319f3 = (Button) findViewById(ay, vw, R.id.an4);
        i74ce6319f3.setOnClickListener(an4ClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
