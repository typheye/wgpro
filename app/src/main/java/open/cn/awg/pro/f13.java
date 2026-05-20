/**
 ******************************************************************************
 * @file    f13.java
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

public class f13 extends iActivity {

    public final f13 lei = this, 类 = this;
    public int mode = 0;
    public open.cn.awg.pro.e1 e1 = new e1(_APPINFO);
    private final android.view.View.OnClickListener $_on_setOnClickListener_i8217d42a17 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i8217d42a17(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i4d951cdb0e = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i4d951cdb0e(vw);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i32ee139ba1 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i32ee139ba1(vw, me);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_c0d3e00d2b = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_c0d3e00d2b(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_c0d3e00d2b = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_c0d3e00d2b(vw);
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
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_e585d94488 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_e585d94488(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_e585d94488 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_e585d94488(vw);
        }

    };
    private final android.widget.CompoundButton.OnCheckedChangeListener $_on_setOnCheckedChangeListener_i2e1dda294f = new android.widget.CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(android.widget.CompoundButton vw, boolean ic) {
            $_onCheckedChanged_i2e1dda294f(vw, ic);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i171cedf1eb = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i171cedf1eb(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i171cedf1eb = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i171cedf1eb(vw);
        }

    };
    private final android.widget.CompoundButton.OnCheckedChangeListener $_on_setOnCheckedChangeListener_ab7dd47b08 = new android.widget.CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(android.widget.CompoundButton vw, boolean ic) {
            $_onCheckedChanged_ab7dd47b08(vw, ic);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d26d5d7080(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.f13);
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

            st.xdbj(open.cn.awg.pro.R.id.f13_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.f13_v).shxtck(false);

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

    public void jz() {

        java.lang.String te1 = "";

        java.lang.String te2 = "";

        java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/f13/set1.inf";

        java.lang.String set2 = "/data/user/0/open.cn.awg.pro/settings/f13/set2.inf";

        java.lang.String set3 = "/data/user/0/open.cn.awg.pro/settings/f13/set3.inf";

        java.lang.String set4 = "/data/user/0/open.cn.awg.pro/settings/f13/set4.inf";

        java.lang.String set5 = "/data/user/0/open.cn.awg.pro/settings/f13/set5.inf";

        i.runlibrary.app.v.kg kg2 = st.kg(open.cn.awg.pro.R.id.kg2);

        i.runlibrary.app.v.kg kg3 = st.kg(open.cn.awg.pro.R.id.kg3);

        if (zf.dy(wj.dqwb(set1), "true")) {

        }

        if (zf.dy(wj.dqwb(set4), "true")) {

            kg2.xzzt(true);

        }

        if (zf.dy(wj.dqwb(set5), "true")) {

            kg3.xzzt(true);

        }
        te1 = wj.dqwb(set2);
        st.wb(open.cn.awg.pro.R.id.wb5).zf(te1);
        te2 = wj.dqwb(set3);

    }

    public void qh() {

        if (st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd() == 0) {

            java.lang.String z = "";
            st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
            st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(0);

            if (mode == 1) {

                z = st.wb(open.cn.awg.pro.R.id.wb5).zf();
                st.wb(open.cn.awg.pro.R.id.Tab).zf("浏览器主页");
                st.bjk(open.cn.awg.pro.R.id.bjk1).tszf("请输入浏览器主页地址");

            } else if (mode == 2) {

                z = st.wb(open.cn.awg.pro.R.id.wb8).zf();
                st.wb(open.cn.awg.pro.R.id.Tab).zf("启动参数");
                st.bjk(open.cn.awg.pro.R.id.bjk1).tszf("请输入启动参数");

            }
            st.bjk(open.cn.awg.pro.R.id.bjk1).zf(z);

        } else {

            st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);
            st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(8);
            st.bjk(open.cn.awg.pro.R.id.bjk1).zf("");
            st.wb(open.cn.awg.pro.R.id.Tab).zf("< X5浏览器");

        }

    }

    public void gx() {

        java.lang.String set2 = "/data/user/0/open.cn.awg.pro/settings/f13/set2.inf";

        java.lang.String set3 = "/data/user/0/open.cn.awg.pro/settings/f13/set3.inf";

        java.lang.String b = st.bjk(open.cn.awg.pro.R.id.bjk1).zf();
        b = zf.qctwkg(b);

        if (mode == 1) {

            java.lang.String f = e1.urlLockString(b);

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

        if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< X5浏览器")) {

            gj.gb();

        }

    }

    private void $_onCheckedChanged_ab7dd47b08(android.widget.CompoundButton vw, boolean ic) {

        java.lang.String set = "/data/user/0/open.cn.awg.pro/settings/f13/set4.inf";

        if (ic) {

            wj.xrwb(set, "true");

        } else {

            wj.xrwb(set, "false");

        }

    }

    private void $_onClick_i171cedf1eb(android.view.View vw) {

        i.runlibrary.app.v.kg kg = st.kg(open.cn.awg.pro.R.id.kg2);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean $_onLongClick_i171cedf1eb(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb11).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb12).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onCheckedChanged_i2e1dda294f(android.widget.CompoundButton vw, boolean ic) {

        java.lang.String set = "/data/user/0/open.cn.awg.pro/settings/f13/set5.inf";

        if (ic) {

            wj.xrwb(set, "true");

        } else {

            wj.xrwb(set, "false");

        }

    }

    private void $_onClick_e585d94488(android.view.View vw) {

        i.runlibrary.app.v.kg kg = st.kg(open.cn.awg.pro.R.id.kg3);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean $_onLongClick_e585d94488(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb13).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb14).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i20c2777fab(android.view.View vw) {
        mode = 1;
        qh();

    }

    private boolean $_onLongClick_i20c2777fab(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb4).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb5).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_c0d3e00d2b(android.view.View vw) {
        gj.tz(l4.class);

    }

    private boolean $_onLongClick_c0d3e00d2b(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb9).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb10).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i34dc7bf20a(android.view.View vw) {
        gj.gb();

    }

    private boolean $_onTouch_i32ee139ba1(android.view.View vw, android.view.MotionEvent me) {

        if (!vw.isFocused()) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(open.cn.awg.pro.R.id.qtgd1);
                    qtgd1.v.requestFocus();

                }

            });

        }
        return false;

    }

    private void $_onClick_i4d951cdb0e(android.view.View vw) {
        qh();

    }

    private void $_onClick_i8217d42a17(android.view.View vw) {
        gx();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
        d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

        android.support.v7.widget.SwitchCompat ab7dd47b08 = (android.support.v7.widget.SwitchCompat) findViewById(ay, vw, open.cn.awg.pro.R.id.kg2);
        ab7dd47b08.setOnCheckedChangeListener($_on_setOnCheckedChangeListener_ab7dd47b08);

        android.widget.RelativeLayout i171cedf1eb = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj6);
        i171cedf1eb.setOnClickListener($_on_setOnClickListener_i171cedf1eb);
        i171cedf1eb.setOnLongClickListener($_on_setOnLongClickListener_i171cedf1eb);

        android.support.v7.widget.SwitchCompat i2e1dda294f = (android.support.v7.widget.SwitchCompat) findViewById(ay, vw, open.cn.awg.pro.R.id.kg3);
        i2e1dda294f.setOnCheckedChangeListener($_on_setOnCheckedChangeListener_i2e1dda294f);

        android.widget.RelativeLayout e585d94488 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj7);
        e585d94488.setOnClickListener($_on_setOnClickListener_e585d94488);
        e585d94488.setOnLongClickListener($_on_setOnLongClickListener_e585d94488);

        android.widget.RelativeLayout i20c2777fab = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj3);
        i20c2777fab.setOnClickListener($_on_setOnClickListener_i20c2777fab);
        i20c2777fab.setOnLongClickListener($_on_setOnLongClickListener_i20c2777fab);

        android.widget.RelativeLayout c0d3e00d2b = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj5);
        c0d3e00d2b.setOnClickListener($_on_setOnClickListener_c0d3e00d2b);
        c0d3e00d2b.setOnLongClickListener($_on_setOnLongClickListener_c0d3e00d2b);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        android.widget.EditText i32ee139ba1 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjk1);
        i32ee139ba1.setOnTouchListener($_on_setOnTouchListener_i32ee139ba1);

        android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
        i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

        android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);
        i8217d42a17.setOnClickListener($_on_setOnClickListener_i8217d42a17);

        __layoutIsLoaded(ay, vw);
    }
}
