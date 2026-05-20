/**
 ******************************************************************************
 * @file    l6.java
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

public class l6 extends iActivity {

    public final l6 lei = this, 类 = this;
    public i.runlibrary.app.v.v7lb$UserAdapter spq = null;
    public java.lang.String lin;
    public java.lang.String CacheSize = null;
    public open.cn.awg.pro.e1 e1 = new e1(_APPINFO);
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i3167b45174 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i3167b45174(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i3167b45174 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i3167b45174(vw);
        }

    };
    private final android.widget.CompoundButton.OnCheckedChangeListener $_on_setOnCheckedChangeListener_i1bca89f073 = new android.widget.CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(android.widget.CompoundButton vw, boolean ic) {
            $_onCheckedChanged_i1bca89f073(vw, ic);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d26d5d7080(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.l6);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.e6.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(android.app.Activity ay, android.view.View vw) {

        if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f8/set4.inf"), "true")) {

        }

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

        if (zf.dy(a, "4")) {

            st.xdbj(open.cn.awg.pro.R.id.l6_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.l6_v).shxtck(false);

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

        if (zf.dy(a, "1")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "2")) {

            java.lang.String a001 = "/data/user/0/open.cn.awg.pro/settings/dpi.inf";

            if (wj.cz(a001) || zf.dy(wj.dqwb(a001), "true")) {

                i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
                wtab.nbj(0, "3dp", 0, "3dp");
                wtab.dqfs("center");

            } else {

                i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
                wtab.nbj(0, "9dp", 0, "9dp");
                wtab.dqfs("center");

            }
            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0, 0, 0, 0);

        } else if (zf.dy(a, "3")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "4")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0, 0, 0, 0);

        }

    }

    public void show(int i) {

        if (i == 0) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj2).kjd(8);

                }

            });

        } else if (i == 1) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj2).kjd(0);

                }

            });

        }

    }

    public void jz() {

        boolean issa = false;

        i.runlibrary.app.v.v7lb lb = st.v7lb(open.cn.awg.pro.R.id.v7lb1);
        spq = lb.v7lbspq(c17.class, open.cn.awg.pro.R.layout.c17);

        java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/l6/set1.inf";

        i.runlibrary.app.v.kg kg1 = st.kg(open.cn.awg.pro.R.id.kg1);

        if (zf.dy(wj.dqwb(set1), "true")) {

            kg1.xzzt(true);
            issa = true;

        }

        final int[] kj = new int[]{open.cn.awg.pro.R.id.tx1, open.cn.awg.pro.R.id.wb1, open.cn.awg.pro.R.id.wb3, -1};

        final boolean iss = issa;
        gj.xc(new java.lang.Thread() {

            public void run() {

                show(1);

                try {

                    i.runlibrary.app.xt$yylb[] sz = xt.yylb(iss);

                    for (i.runlibrary.app.xt$yylb app : sz) {

                        java.lang.String bbh = String.valueOf(app.bbh);

                        java.lang.String zbt = app.bb + "[" + bbh + "]";

                        java.lang.Object[] jk = new java.lang.Object[]{app.tb, app.bt, zbt, app.bm};
                        spq.j(kj, jk);

                    }
                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            spq.sx();

                        }

                    });

                } catch (java.lang.Throwable e) {

                    e1.upload_error(e, "l6.*");

                }
                show(0);

            }

        });

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

    }

    public void onRestart() {
        super.onRestart();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

    }

    public void onStart() {
        super.onStart();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

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

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

    }

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.e6.getInstance().removeActivity(lei);

    }

    private void $_onClick_d26d5d7080(android.view.View vw) {
        gj.gb();

    }

    private void $_onCheckedChanged_i1bca89f073(android.widget.CompoundButton vw, boolean ic) {

        java.lang.String set = "/data/user/0/open.cn.awg.pro/settings/l6/set1.inf";

        if (ic) {

            wj.xrwb(set, "true");
            jz();

        } else {

            wj.xrwb(set, "false");
            jz();

        }

    }

    private void $_onClick_i3167b45174(android.view.View vw) {

        i.runlibrary.app.v.kg kg = st.kg(open.cn.awg.pro.R.id.kg1);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean $_onLongClick_i3167b45174(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb2).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb4).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i34dc7bf20a(android.view.View vw) {
        gj.gb();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
        d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

        android.support.v7.widget.SwitchCompat i1bca89f073 = (android.support.v7.widget.SwitchCompat) findViewById(ay, vw, open.cn.awg.pro.R.id.kg1);
        i1bca89f073.setOnCheckedChangeListener($_on_setOnCheckedChangeListener_i1bca89f073);

        android.widget.RelativeLayout i3167b45174 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj2);
        i3167b45174.setOnClickListener($_on_setOnClickListener_i3167b45174);
        i3167b45174.setOnLongClickListener($_on_setOnLongClickListener_i3167b45174);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        __layoutIsLoaded(ay, vw);
    }
}
