/**
 ******************************************************************************
 * @file    RemoteControlActivity.java
 * @author  Typheye
 * @brief   Remote control plugin screen.
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

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.view.Window;

import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;
import open.cn.awg.pro.account.TypheyeAccountActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.DevicePolicyBridge;
import open.cn.awg.pro.music.TuneFreeMusicBridge;
import open.cn.awg.pro.service.SystemAccessibilityService;


public class RemoteControlActivity extends iActivity {

    public final RemoteControlActivity lei = this, 类 = this;
    public java.lang.String um = "";
    public java.io.File f;
    public open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public open.cn.awg.pro.music.TuneFreeMusicBridge e12 = new TuneFreeMusicBridge(_APPINFO);
    public open.cn.awg.pro.core.DevicePolicyBridge carton = new DevicePolicyBridge(_APPINFO);
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i5a93676674 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i5a93676674(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i5a93676674 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i5a93676674(vw);
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
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_fab146d7a5 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_fab146d7a5(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_fab146d7a5 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_fab146d7a5(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i5b34eede7f = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i5b34eede7f(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i5b34eede7f = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i5b34eede7f(vw);
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
    private final android.widget.CompoundButton.OnCheckedChangeListener $_on_setOnCheckedChangeListener_i1bca89f073 = new android.widget.CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(android.widget.CompoundButton vw, boolean ic) {
            $_onCheckedChanged_i1bca89f073(vw, ic);
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
    private final android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d26d5d7080(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.l5_a7_yuanc_d0625_y2024);
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

            st.xdbj(open.cn.awg.pro.R.id.l5_a7_yuanc_d0625_y2024_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.l5_a7_yuanc_d0625_y2024_v).shxtck(false);

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

    public void simpleSetting(Context context) {
        Intent intent = new Intent();
        try {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            if (Build.VERSION.SDK_INT >= 9) {
                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.fromParts("package", context.getPackageName(), null));
            } else if (Build.VERSION.SDK_INT <= 8) {
                intent.setAction(Intent.ACTION_VIEW);
                intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                intent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
            }
            context.startActivity(intent);
        } catch (Exception e) {
            intent = new Intent(Settings.ACTION_SETTINGS);
            context.startActivity(intent);
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

                    tab.zf("< 腕管远控");
                    x1.kjd(8);
                    x2.kjd(8);
                    x3.kjd(0);

                }

            });

        } else if (i == 1) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    tab.zf("< 扩展权限");
                    x1.kjd(0);
                    x2.kjd(8);
                    x3.kjd(8);

                }

            });

        } else if (i == 2) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    tab.zf("< 腕管远控");
                    x1.kjd(8);
                    x2.kjd(0);
                    x3.kjd(8);

                }

            });

        }

    }

    public void csh() {

        java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/other/0xK9HJ4/Enable";

        java.lang.String user = "/data/user/0/open.cn.awg.pro/settings/account/user";

        i.runlibrary.app.v.kg kg1 = st.kg(open.cn.awg.pro.R.id.kg1);

        i.runlibrary.app.v.wb wb4 = st.wb(open.cn.awg.pro.R.id.wb4);

        i.runlibrary.app.v.wb wb9 = st.wb(open.cn.awg.pro.R.id.wb9);

        if (e1.islogin()) {

            wb9.zf(wj.dqwb(user));

        } else {

            wb9.zf("未登录Typheye账户");

        }

        if (zf.dy(wj.dqwb(set1), "true") && e1.islogin()) {

            kg1.xzzt(true);

        }

        boolean aa;

        try {

            aa = open.cn.awg.pro.service.SystemAccessibilityService.isAccessibilitySettingsOn(lei, open.cn.awg.pro.service.SystemAccessibilityService.class.getName());

        } catch (java.lang.Throwable e) {

            aa = false;

        }

        if (carton.state(lei) && aa) {

            wb4.zf("已授权必要权限");

        } else {

            wb4.zf("存在权限未授权");
            kg1.xzzt(false);

        }

    }

    public void jzpd() {
        show(1);

        i.runlibrary.app.v.wb wb11 = st.wb(open.cn.awg.pro.R.id.wb11);

        if (carton.state(lei)) {

            wb11.zf("此权限已授权");

        } else {

            wb11.zf("此权限未授权");

        }

        boolean aa;

        try {

            aa = open.cn.awg.pro.service.SystemAccessibilityService.isAccessibilitySettingsOn(lei, open.cn.awg.pro.service.SystemAccessibilityService.class.getName());

        } catch (java.lang.Throwable e) {

            aa = false;

        }

        i.runlibrary.app.v.wb wb21 = st.wb(open.cn.awg.pro.R.id.wb21);

        if (aa) {

            wb21.zf("此权限已授权");

        } else {

            wb21.zf("此权限未授权");

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

        if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< 远程锁屏")) {

            csh();

        } else if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< 扩展权限")) {

            jzpd();

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

            if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< 腕管远控")) {

                gj.gb();

            } else if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< 扩展权限")) {

                show(0);
                csh();

            }

        }
        return false;

    }

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.app.AwgProApplication.getInstance().removeActivity(lei);

    }

    private void $_onClick_d26d5d7080(android.view.View vw) {

        if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< 腕管远控")) {

            gj.gb();

        } else if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< 扩展权限")) {

            show(0);
            csh();

        }

    }

    private void $_onClick_i2202ede8ff(android.view.View vw) {

        i.runlibrary.app.v.kg kg = st.kg(open.cn.awg.pro.R.id.kg1);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean $_onLongClick_i2202ede8ff(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb2).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb7).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onCheckedChanged_i1bca89f073(android.widget.CompoundButton vw, boolean ic) {

        java.lang.String set = "/data/user/0/open.cn.awg.pro/settings/other/0xK9HJ4/Enable";

        if (ic) {

            if (e1.islogin()) {

                boolean aa;

                try {

                    aa = open.cn.awg.pro.service.SystemAccessibilityService.isAccessibilitySettingsOn(lei, open.cn.awg.pro.service.SystemAccessibilityService.class.getName());

                } catch (java.lang.Throwable e) {

                    aa = false;

                }

                if (carton.state(lei) && aa) {

                    wj.xrwb(set, "true");

                } else {

                    e1.tsk("提示", "存在必要权限未授权");

                    i.runlibrary.app.v.kg kg1 = st.kg(open.cn.awg.pro.R.id.kg1);
                    kg1.xzzt(false);

                }

            } else {

                wj.xrwb(set, "false");
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        i.runlibrary.app.v.kg kg = st.kg(open.cn.awg.pro.R.id.kg1);
                        kg.xzzt(false);
                        gj.tz(TypheyeAccountActivity.class);
                        e1.tsk("提示", "该操作需要登录Typheye账户");

                    }

                });

            }

        } else {

            wj.xrwb(set, "false");

        }

    }

    private void $_onClick_c0d3e00d2b(android.view.View vw) {
        e1.tsk("扩展身份", st.wb(open.cn.awg.pro.R.id.wb9).zf());

    }

    private boolean $_onLongClick_c0d3e00d2b(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb8).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb9).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

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
        e1.tsk("功能帮助", "此服务由官方提供");

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

    private void $_onClick_i171cedf1eb(android.view.View vw) {
        carton.gets(lei);

    }

    private boolean $_onLongClick_i171cedf1eb(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb10).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb11).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i5b34eede7f(android.view.View vw) {

        boolean aa;

        try {

            aa = open.cn.awg.pro.service.SystemAccessibilityService.isAccessibilitySettingsOn(lei, open.cn.awg.pro.service.SystemAccessibilityService.class.getName());

        } catch (java.lang.Throwable e) {

            aa = false;

        }

        if (!aa) {

            try {

                Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                lei.startActivity(intent);

            } catch (java.lang.Throwable e) {

                gj.gb();
                e1.upload_error(e, "j3.run()");

            }

        } else {

        }

    }

    private boolean $_onLongClick_i5b34eede7f(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb20).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb21).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_fab146d7a5(android.view.View vw) {
        simpleSetting(lei);

    }

    private boolean $_onLongClick_fab146d7a5(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb16).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb17).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_e585d94488(android.view.View vw) {
        simpleSetting(lei);

    }

    private boolean $_onLongClick_e585d94488(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb12).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb13).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i5a93676674(android.view.View vw) {
        simpleSetting(lei);

    }

    private boolean $_onLongClick_i5a93676674(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb18).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb19).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

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

        android.widget.RelativeLayout i2202ede8ff = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj4);
        i2202ede8ff.setOnClickListener($_on_setOnClickListener_i2202ede8ff);
        i2202ede8ff.setOnLongClickListener($_on_setOnLongClickListener_i2202ede8ff);

        androidx.appcompat.widget.SwitchCompat i1bca89f073 = (androidx.appcompat.widget.SwitchCompat) findViewById(ay, vw, open.cn.awg.pro.R.id.kg1);
        i1bca89f073.setOnCheckedChangeListener($_on_setOnCheckedChangeListener_i1bca89f073);

        android.widget.RelativeLayout c0d3e00d2b = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj5);
        c0d3e00d2b.setOnClickListener($_on_setOnClickListener_c0d3e00d2b);
        c0d3e00d2b.setOnLongClickListener($_on_setOnLongClickListener_c0d3e00d2b);

        android.widget.RelativeLayout i3167b45174 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj2);
        i3167b45174.setOnClickListener($_on_setOnClickListener_i3167b45174);
        i3167b45174.setOnLongClickListener($_on_setOnLongClickListener_i3167b45174);

        android.widget.RelativeLayout i20c2777fab = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj3);
        i20c2777fab.setOnClickListener($_on_setOnClickListener_i20c2777fab);
        i20c2777fab.setOnLongClickListener($_on_setOnLongClickListener_i20c2777fab);

        android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
        i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

        android.widget.RelativeLayout i171cedf1eb = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj6);
        i171cedf1eb.setOnClickListener($_on_setOnClickListener_i171cedf1eb);
        i171cedf1eb.setOnLongClickListener($_on_setOnLongClickListener_i171cedf1eb);

        android.widget.RelativeLayout i5b34eede7f = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj10);
        i5b34eede7f.setOnClickListener($_on_setOnClickListener_i5b34eede7f);
        i5b34eede7f.setOnLongClickListener($_on_setOnLongClickListener_i5b34eede7f);

        android.widget.RelativeLayout fab146d7a5 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj8);
        fab146d7a5.setOnClickListener($_on_setOnClickListener_fab146d7a5);
        fab146d7a5.setOnLongClickListener($_on_setOnLongClickListener_fab146d7a5);

        android.widget.RelativeLayout e585d94488 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj7);
        e585d94488.setOnClickListener($_on_setOnClickListener_e585d94488);
        e585d94488.setOnLongClickListener($_on_setOnLongClickListener_e585d94488);

        android.widget.RelativeLayout i5a93676674 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj9);
        i5a93676674.setOnClickListener($_on_setOnClickListener_i5a93676674);
        i5a93676674.setOnLongClickListener($_on_setOnLongClickListener_i5a93676674);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        __layoutIsLoaded(ay, vw);
    }
}
