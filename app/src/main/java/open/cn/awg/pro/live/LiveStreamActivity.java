/**
 ******************************************************************************
 * @file    LiveStreamActivity.java
 * @author  Typheye
 * @brief   Live stream plugin screen.
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
package open.cn.awg.pro.live;

import android.view.View;
import android.view.Window;

import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.music.TuneFreeMusicBridge;
import open.cn.awg.pro.ui.list.LiveStreamItemActivity;


public class LiveStreamActivity extends iActivity {

    public final LiveStreamActivity lei = this, 类 = this;
    public java.lang.String um = "";
    public java.io.File f;
    public open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public open.cn.awg.pro.music.TuneFreeMusicBridge e12 = new TuneFreeMusicBridge(_APPINFO);
    public i.runlibrary.app.v.v7lb$UserAdapter wlzblbspq = null;
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i4d951cdb0e = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i4d951cdb0e(vw);
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
    private final android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d26d5d7080(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.l5_a3_zhibo_d0420_y2022);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.app.AwgProApplication.getInstance().addActivity(lei);
        csh();
        show(0);

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

            st.xdbj(open.cn.awg.pro.R.id.l5_a3_zhibo_d0420_y2022_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.l5_a3_zhibo_d0420_y2022_v).shxtck(false);

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

        final i.runlibrary.app.v.wb tab = st.wb(open.cn.awg.pro.R.id.Tab);

        final i.runlibrary.app.v.xxbj x1 = st.xxbj(open.cn.awg.pro.R.id.xxbj1);

        final i.runlibrary.app.v.xxbj x2 = st.xxbj(open.cn.awg.pro.R.id.xxbj6);

        final i.runlibrary.app.v.xxbj x3 = st.xxbj(open.cn.awg.pro.R.id.xxbj2);

        if (i == 0) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    tab.zf("< 网络直播");
                    x1.kjd(8);
                    x2.kjd(8);
                    x3.kjd(0);

                }

            });

        } else if (i == 1) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    tab.zf("< 直播通道");
                    x1.kjd(0);
                    x2.kjd(8);
                    x3.kjd(8);

                }

            });

        } else if (i == 2) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    tab.zf("< 网络直播");
                    x1.kjd(8);
                    x2.kjd(0);
                    x3.kjd(8);

                }

            });

        }

    }

    public void csh() {

        i.runlibrary.app.v.v7lb lb = st.v7lb(open.cn.awg.pro.R.id.wlzbv7lb1);
        wlzblbspq = lb.v7lbspq(LiveStreamItemActivity.class, open.cn.awg.pro.R.layout.c16);

    }

    public void jzpd() {
        gj.xc(new java.lang.Thread() {

            public void run() {

                show(2);
                wlzblbspq.sc();

                java.lang.String set33 = "/data/user/0/open.cn.awg.pro/data/assets/a18";

                int[] kj = new int[]{open.cn.awg.pro.R.id.wb1, open.cn.awg.pro.R.id.wb3, open.cn.awg.pro.R.id.wb2, -2};

                java.lang.String[] jk = new java.lang.String[]{"", "", "", ""};

                try {

                    java.lang.String v = wj.dqwb(set33);

                    i.runlibrary.app.zf$json jo = zf.json(v);

                    org.json.JSONObject json = jo.json;

                    java.lang.Object v_code = jo.hq(json, "code");

                    org.json.JSONArray list = jo.dxlb(json, "data");

                    int i = jo.cd(list);

                    int size = 0;
                    gj.sc(size);

                    while (size < i) {

                        org.json.JSONObject dx = jo.dx(list, size);
                        size++;

                        java.lang.String v_url_back = "";

                        try {

                            v_url_back = String.valueOf(jo.hq(dx, "url_back"));

                        } catch (java.lang.Throwable __$_e__) {

                        }

                        if (zf.dy(v_url_back, null) || zf.dy(v_url_back, "null")) {

                            v_url_back = "";

                        }

                        java.lang.Object v_title = jo.hq(dx, "title");

                        java.lang.Object v_url = jo.hq(dx, "url");

                        java.lang.Object v_text = jo.hq(dx, "text");
                        jk = new java.lang.String[]{v_title.toString(), v_text.toString(), v_url.toString(), v_url_back};
                        wlzblbspq.j(kj, jk);

                    }
                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            wlzblbspq.sx();
                            show(1);

                        }

                    });

                } catch (java.lang.Throwable e) {

                    e1.upload_error(e, "l5_a3_zhibo_d0420_y2022.jzpd()");
                    show(0);

                }

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

    public boolean onKeyDown(int kc, android.view.KeyEvent ke) {

        if (kc == 4) {

            if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< 网络直播")) {

                gj.gb();

            } else if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< 直播通道")) {

                show(0);

            }

        }
        return false;

    }

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.app.AwgProApplication.getInstance().removeActivity(lei);

    }

    private void $_onClick_d26d5d7080(android.view.View vw) {

        if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< 网络直播")) {

            gj.gb();

        } else if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< 直播通道")) {

            show(0);

        }

    }

    private void $_onClick_i3167b45174(android.view.View vw) {
        jzpd();

    }

    private boolean $_onLongClick_i3167b45174(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb3).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb4).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i20c2777fab(android.view.View vw) {
        e1.tsk("功能帮助", "此服务由第三方提供,如有侵权,请联系498978473@qq.com");

    }

    private boolean $_onLongClick_i20c2777fab(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb5).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb6).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i4d951cdb0e(android.view.View vw) {
        gj.gb();

    }

    private void $_onClick_i34dc7bf20a(android.view.View vw) {
        show(0);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
        d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

        android.widget.RelativeLayout i3167b45174 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj2);
        i3167b45174.setOnClickListener($_on_setOnClickListener_i3167b45174);
        i3167b45174.setOnLongClickListener($_on_setOnLongClickListener_i3167b45174);

        android.widget.RelativeLayout i20c2777fab = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj3);
        i20c2777fab.setOnClickListener($_on_setOnClickListener_i20c2777fab);
        i20c2777fab.setOnLongClickListener($_on_setOnLongClickListener_i20c2777fab);

        android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
        i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        __layoutIsLoaded(ay, vw);
    }
}
