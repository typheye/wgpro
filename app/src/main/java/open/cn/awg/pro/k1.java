/**
 ******************************************************************************
 * @file    k1.java
 * @author  Typheye
 * @brief   AWG Pro module source.
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
package open.cn.awg.pro;

import android.view.View;
import android.view.Window;

import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;

public class k1 extends iActivity {

    public final k1 lei = this, 类 = this;
    public open.cn.awg.pro.e1 e1 = new e1(_APPINFO);
    private final android.view.View.OnClickListener $_on_setOnClickListener_i4a4248a0f2 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i4a4248a0f2(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i20c2777fab = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i20c2777fab(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i20c2777fab = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i20c2777fab(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i2202ede8ff = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i2202ede8ff(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i2202ede8ff = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i2202ede8ff(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_dc0a1b897b = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_dc0a1b897b(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.k1);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.e6.getInstance().addActivity(lei);

    }

    public void __layoutIsLoaded(android.app.Activity ay, android.view.View vw) {

        i.runlibrary.app.xt$pm pm = xt.pm();

        if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4s.inf"), "false")) {

            if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4.inf"), "true")) {

                getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        if (zf.dy(a, "1")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u, 0, u, 50);

        } else if (zf.dy(a, "2")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0, 0, 0, 0);

        } else if (zf.dy(a, "3")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u, 0, u, 50);

        } else if (zf.dy(a, "4")) {

            int uih = sj.zh().pxzdp(pm.ztl);
            uih = uih + 40;

            i.runlibrary.app.v.kp kp1 = st.kp(open.cn.awg.pro.R.id.kp1);
            kp1.wbj(0, uih + "dp", 0, "5dp");

        }

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public void onRestart() {
        super.onRestart();

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public void onStart() {
        super.onStart();

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(open.cn.awg.pro.R.id.qtgd1);

        qtgd1.v.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View vw, android.view.MotionEvent me) {
                if (me.getAction() == android.view.MotionEvent.ACTION_SCROLL && me.isFromSource(androidx.core.view.InputDeviceCompat.SOURCE_ROTARY_ENCODER)) {

                    float delta = -me.getAxisValue(androidx.core.view.MotionEventCompat.AXIS_SCROLL) *
                            androidx.core.view.ViewConfigurationCompat.getScaledVerticalScrollFactor(
                                    android.view.ViewConfiguration.get(lei), lei
                            );

                    vw.scrollBy(0, java.lang.Math.round(delta));
                    return true;
                }
                return false;
            }
        });

        qtgd1.v.requestFocus();

    }

    public void onResume() {
        super.onResume();

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.e6.getInstance().removeActivity(lei);

    }

    public boolean onKeyDown(int kc, android.view.KeyEvent ke) {

        if (kc == 4) {

        }
        return false;

    }

    private void $_onClick_dc0a1b897b(android.view.View vw) {

        int i;

    }

    private void $_onClick_i2202ede8ff(android.view.View vw) {
        gj.tz(k2.class);

    }

    private boolean $_onLongClick_i2202ede8ff(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb5).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb7).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i20c2777fab(android.view.View vw) {

        try {

            try {

                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        xt.dkyy("com.android.settings", "com.android.settings.Settings");

                    }

                });

            } catch (java.lang.Throwable e2) {

                try {

                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            xt.dkyy("com.google.android.apps.wearable.settings", "com.google.android.clockwork.settings.MainSettingsActivity");

                        }

                    });

                } catch (java.lang.Throwable e3) {

                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            xt.dkyy("com.google.android.apps.wearable.settings");

                        }

                    });

                }

            }

        } catch (java.lang.Throwable e) {

            e1.tsk("提示", "没有可执行该操作的应用");

        }

    }

    private boolean $_onLongClick_i20c2777fab(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb2).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb4).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i4a4248a0f2(android.view.View vw) {
        gj.gb();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.LinearLayout dc0a1b897b = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj2);
        dc0a1b897b.setOnClickListener($_on_setOnClickListener_dc0a1b897b);

        android.widget.RelativeLayout i2202ede8ff = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj4);
        i2202ede8ff.setOnClickListener($_on_setOnClickListener_i2202ede8ff);
        i2202ede8ff.setOnLongClickListener($_on_setOnLongClickListener_i2202ede8ff);

        android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);

        android.widget.RelativeLayout i20c2777fab = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj3);
        i20c2777fab.setOnClickListener($_on_setOnClickListener_i20c2777fab);
        i20c2777fab.setOnLongClickListener($_on_setOnLongClickListener_i20c2777fab);

        android.widget.ImageView i4a4248a0f2 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx5);
        i4a4248a0f2.setOnClickListener($_on_setOnClickListener_i4a4248a0f2);

        __layoutIsLoaded(ay, vw);
    }
}
