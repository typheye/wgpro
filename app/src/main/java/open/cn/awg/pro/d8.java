/**
 ******************************************************************************
 * @file    d8.java
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

public class d8 extends iActivity {

    public final d8 lei = this, 类 = this;
    public java.lang.String b = "";
    public java.lang.String c = "";
    public java.lang.String d = "";
    public java.lang.String es = "";
    public open.cn.awg.pro.e1 e1 = new e1(_APPINFO);
    private final android.view.View.OnClickListener $_on_setOnClickListener_i4d951cdb0e = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i4d951cdb0e(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.d8);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.e6.getInstance().addActivity(lei);
        b = sj.hqtz("b");
        c = sj.hqtz("c");
        d = sj.hqtz("d");
        es = sj.hqtz("es");

        java.lang.String lx = "";

        if (zf.dy(b, "2")) {

            lx = "文件";

        } else {

            lx = "目录";

        }
        st.wb(open.cn.awg.pro.R.id.wb1).zf("您确定要删除“" + c + "”" + lx + "吗？");

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

        if (zf.dy(a, "4")) {

            st.xdbj(open.cn.awg.pro.R.id.d8_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.d8_v).shxtck(false);

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

        if (zf.dy(a, "1")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u, 0, u, 60);

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

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u, 0, u, 60);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "4")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0, 0, 0, 0);

        }

    }

    public void tip() {

        java.lang.String cache_c = "/data/user/0/open.cn.awg.pro/cache/c";

        java.lang.String cache_es = "/data/user/0/open.cn.awg.pro/cache/es";
        wj.xrwb(cache_c, c);
        wj.xrwb(cache_es, es);

    }

    public void no() {
        gj.xc(new java.lang.Thread() {

            public void run() {

                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
                        st.xxbj(open.cn.awg.pro.R.id.xxbj3).kjd(0);

                    }

                });
                tip();
                gj.zt(250);
                gj.gb();

            }

        });

    }

    public void yes() {
        gj.xc(new java.lang.Thread() {

            public void run() {

                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
                        st.xxbj(open.cn.awg.pro.R.id.xxbj3).kjd(0);

                    }

                });
                tip();
                gj.zt(250);

                java.lang.String[] aa;

                java.lang.String pb1 = "/data/user/0/open.cn.awg.pro/settings";

                java.lang.String pb2 = "/data/user/0/open.cn.awg.pro/data";

                java.lang.String pb3 = "/data/user/0/open.cn.awg.pro/fix";

                java.lang.String pb4 = "/data/user/0/open.cn.awg.pro/cache";

                java.lang.String pb5 = "/data/user/0/open.cn.awg.pro/files/cache";

                java.lang.String pb6 = "/storage/emulated/0/Android/data";

                java.lang.String pb7 = "/storage/emulated/0/Android/obb";

                if (zf.ckt(c, pb1) || zf.ckt(c, pb2) || zf.ckt(c, pb3) || zf.ckt(c, pb4) || zf.ckt(c, pb5) || (xt.sbxx().sdk > 29 && zf.ckt(c, pb6)) || (xt.sbxx().sdk > 29 && zf.ckt(c, pb7))) {

                    e1.tsk("提示", "非法操作");

                } else {

                    if (zf.dy(b, "2")) {

                        if (wj.sc(d) == 1) {

                            final java.lang.String z = "删除成功";

                        } else {

                            final java.lang.String z = "删除失败";
                            e1.tsk("提示", z);

                        }

                    } else {

                        java.lang.String m = "rm -rf \"" + d + "\"";
                        aa = com.demo.e3.cmd(lei, m, false);

                        if (zf.dy(aa[0], "") && !zf.dy(aa[1], "")) {

                            final java.lang.String z = "删除失败\n" + aa[1];
                            e1.tsk("提示", z);

                        } else if (!zf.dy(aa[0], "") && zf.dy(aa[1], "")) {

                            final java.lang.String z = "删除成功";

                        } else {

                            final java.lang.String z = "删除成功";

                        }

                    }

                }
                gj.gb();

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
        tip();

    }

    public boolean onKeyDown(int kc, android.view.KeyEvent ke) {

        if (kc == 4) {

        }
        return false;

    }

    private void $_onClick_i34dc7bf20a(android.view.View vw) {
        no();

    }

    private void $_onClick_i4d951cdb0e(android.view.View vw) {
        yes();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
        i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

        __layoutIsLoaded(ay, vw);
    }
}
