/**
 ******************************************************************************
 * @file    FileManagerSettingsActivity.java
 * @author  Typheye
 * @brief   Configures file manager behavior and storage permissions.
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

import android.view.View;
import android.view.Window;

import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;


public class FileManagerSettingsActivity extends iActivity {

    public final FileManagerSettingsActivity lei = this, 类 = this;
    public open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
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
    private final android.widget.CompoundButton.OnCheckedChangeListener $_on_setOnCheckedChangeListener_ab7dd47b08 = new android.widget.CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(android.widget.CompoundButton vw, boolean ic) {
            $_onCheckedChanged_ab7dd47b08(vw, ic);
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
    private final android.widget.CompoundButton.OnCheckedChangeListener $_on_setOnCheckedChangeListener_c6051f0d45 = new android.widget.CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(android.widget.CompoundButton vw, boolean ic) {
            $_onCheckedChanged_c6051f0d45(vw, ic);
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
    private final android.widget.CompoundButton.OnCheckedChangeListener $_on_setOnCheckedChangeListener_i2e1dda294f = new android.widget.CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(android.widget.CompoundButton vw, boolean ic) {
            $_onCheckedChanged_i2e1dda294f(vw, ic);
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
        setContentView(open.cn.awg.pro.R.layout.f6);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.app.AwgProApplication.getInstance().addActivity(lei);
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

            st.xdbj(open.cn.awg.pro.R.id.f6_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.f6_v).shxtck(false);

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

        java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/d1/set1.inf";

        java.lang.String set2 = "/data/user/0/open.cn.awg.pro/settings/d1/set2.inf";

        java.lang.String set3 = "/data/user/0/open.cn.awg.pro/settings/d1/set3.inf";

        java.lang.String set4 = "/data/user/0/open.cn.awg.pro/settings/d1/set4.inf";

        i.runlibrary.app.v.kg kg1 = st.kg(open.cn.awg.pro.R.id.kg1);

        i.runlibrary.app.v.kg kg2 = st.kg(open.cn.awg.pro.R.id.kg2);

        i.runlibrary.app.v.kg kg3 = st.kg(open.cn.awg.pro.R.id.kg3);

        i.runlibrary.app.v.kg kg4 = st.kg(open.cn.awg.pro.R.id.kg4);

        if (zf.dy(wj.dqwb(set1), "true")) {

            kg1.xzzt(true);

        }

        if (zf.dy(wj.dqwb(set2), "true")) {

            kg2.xzzt(true);

        }

        if (zf.dy(wj.dqwb(set3), "true")) {

            kg3.xzzt(true);

        }

        if (zf.dy(wj.dqwb(set4), "true")) {

            kg4.xzzt(true);

        }

        i.runlibrary.app.v.xxbj xxbj4 = st.xxbj(open.cn.awg.pro.R.id.xxbj4);

        i.runlibrary.app.v.wb wb12 = st.wb(open.cn.awg.pro.R.id.wb12);

        int sdk = xt.sbxx().sdk;

        if (sdk >= 30) {

            try {

                java.lang.Class cs = android.os.Environment.class;

                java.lang.reflect.Method m = cs.getMethod("isExternalStorageManager");

                java.lang.Object o = m.invoke(null);

                if (o.equals(true)) {

                    wb12.zf("已授权该权限");

                } else {

                    wb12.zf("未授权该权限");

                }

            } catch (java.lang.Throwable __$_e__) {

                wb12.zf("未知权限状态");

            }
            xxbj4.kjd(0);

        } else {

            xxbj4.kjd(8);

        }

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
        jz();

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
        open.cn.awg.pro.app.AwgProApplication.getInstance().removeActivity(lei);

    }

    private void $_onClick_d26d5d7080(android.view.View vw) {
        gj.gb();

    }

    private void $_onCheckedChanged_i1bca89f073(android.widget.CompoundButton vw, boolean ic) {

        java.lang.String set = "/data/user/0/open.cn.awg.pro/settings/d1/set1.inf";

        if (ic) {

            wj.xrwb(set, "true");

        } else {

            wj.xrwb(set, "false");

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

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb3).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onCheckedChanged_i2e1dda294f(android.widget.CompoundButton vw, boolean ic) {

        java.lang.String set = "/data/user/0/open.cn.awg.pro/settings/d1/set3.inf";

        if (ic) {

            wj.xrwb(set, "true");

        } else {

            wj.xrwb(set, "false");

        }

    }

    private void $_onClick_i2202ede8ff(android.view.View vw) {

        i.runlibrary.app.v.kg kg = st.kg(open.cn.awg.pro.R.id.kg3);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean $_onLongClick_i2202ede8ff(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb6).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb7).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onCheckedChanged_c6051f0d45(android.widget.CompoundButton vw, boolean ic) {

        java.lang.String set = "/data/user/0/open.cn.awg.pro/settings/d1/set4.inf";

        if (ic) {

            wj.xrwb(set, "true");

        } else {

            wj.xrwb(set, "false");

        }

    }

    private void $_onClick_c0d3e00d2b(android.view.View vw) {

        i.runlibrary.app.v.kg kg = st.kg(open.cn.awg.pro.R.id.kg4);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean $_onLongClick_c0d3e00d2b(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb8).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb9).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onCheckedChanged_ab7dd47b08(android.widget.CompoundButton vw, boolean ic) {

        java.lang.String set = "/data/user/0/open.cn.awg.pro/settings/d1/set2.inf";

        if (ic) {

            wj.xrwb(set, "true");

        } else {

            wj.xrwb(set, "false");

        }

    }

    private void $_onClick_i20c2777fab(android.view.View vw) {

        i.runlibrary.app.v.kg kg = st.kg(open.cn.awg.pro.R.id.kg2);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean $_onLongClick_i20c2777fab(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb4).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb5).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i171cedf1eb(android.view.View vw) {

        try {

            java.lang.Class cs = android.os.Environment.class;

            java.lang.reflect.Method m = cs.getMethod("isExternalStorageManager");

            java.lang.Object o = m.invoke(null);

            if (o.equals(true)) {

                e1.tsk("提示", "已授权该权限");

            } else {

                android.content.Intent it = new android.content.Intent("android.settings.MANAGE_ALL_FILES_ACCESS_PERMISSION");
                lei.startActivity(it);

            }

        } catch (java.lang.Throwable e) {

            gj.gb();
            e1.upload_error(e, "f6.*");

        }

    }

    private boolean $_onLongClick_i171cedf1eb(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb11).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb12).zf();
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

        android.support.v7.widget.SwitchCompat i2e1dda294f = (android.support.v7.widget.SwitchCompat) findViewById(ay, vw, open.cn.awg.pro.R.id.kg3);
        i2e1dda294f.setOnCheckedChangeListener($_on_setOnCheckedChangeListener_i2e1dda294f);

        android.widget.RelativeLayout i2202ede8ff = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj4);
        i2202ede8ff.setOnClickListener($_on_setOnClickListener_i2202ede8ff);
        i2202ede8ff.setOnLongClickListener($_on_setOnLongClickListener_i2202ede8ff);

        android.support.v7.widget.SwitchCompat c6051f0d45 = (android.support.v7.widget.SwitchCompat) findViewById(ay, vw, open.cn.awg.pro.R.id.kg4);
        c6051f0d45.setOnCheckedChangeListener($_on_setOnCheckedChangeListener_c6051f0d45);

        android.widget.RelativeLayout c0d3e00d2b = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj5);
        c0d3e00d2b.setOnClickListener($_on_setOnClickListener_c0d3e00d2b);
        c0d3e00d2b.setOnLongClickListener($_on_setOnLongClickListener_c0d3e00d2b);

        android.support.v7.widget.SwitchCompat ab7dd47b08 = (android.support.v7.widget.SwitchCompat) findViewById(ay, vw, open.cn.awg.pro.R.id.kg2);
        ab7dd47b08.setOnCheckedChangeListener($_on_setOnCheckedChangeListener_ab7dd47b08);

        android.widget.RelativeLayout i20c2777fab = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj3);
        i20c2777fab.setOnClickListener($_on_setOnClickListener_i20c2777fab);
        i20c2777fab.setOnLongClickListener($_on_setOnLongClickListener_i20c2777fab);

        android.widget.RelativeLayout i171cedf1eb = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj6);
        i171cedf1eb.setOnClickListener($_on_setOnClickListener_i171cedf1eb);
        i171cedf1eb.setOnLongClickListener($_on_setOnLongClickListener_i171cedf1eb);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        __layoutIsLoaded(ay, vw);
    }
}
