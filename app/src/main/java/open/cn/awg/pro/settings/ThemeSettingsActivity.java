/**
 ******************************************************************************
 * @file    ThemeSettingsActivity.java
 * @author  Typheye
 * @brief   Configures theme, startup target, and icon presentation.
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
import open.cn.awg.pro.repair.ErrorMessageActivity;
import open.cn.awg.pro.ui.main.MainActivity;


public class ThemeSettingsActivity extends iActivity {

    public final ThemeSettingsActivity lei = this, 类 = this;
    public android.content.ComponentName mComponentName;
    public android.content.ComponentName mComponentNameDefault;
    public android.content.pm.PackageManager mPackageManager;
    public boolean iskg2enable = false;
    public boolean old_state_kg3 = false;
    public java.lang.String old_state_set1 = "0";
    public open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
        }

    };
    private final android.widget.CompoundButton.OnCheckedChangeListener $_on_setOnCheckedChangeListener_d01948645a = new android.widget.CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(android.widget.CompoundButton vw, boolean ic) {
            $_onCheckedChanged_d01948645a(vw, ic);
        }

    };
    private final android.widget.CompoundButton.OnCheckedChangeListener $_on_setOnCheckedChangeListener_d983960091 = new android.widget.CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(android.widget.CompoundButton vw, boolean ic) {
            $_onCheckedChanged_d983960091(vw, ic);
        }

    };
    private final android.widget.CompoundButton.OnCheckedChangeListener $_on_setOnCheckedChangeListener_i5e048391d3 = new android.widget.CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(android.widget.CompoundButton vw, boolean ic) {
            $_onCheckedChanged_i5e048391d3(vw, ic);
        }

    };
    private final android.widget.CompoundButton.OnCheckedChangeListener $_on_setOnCheckedChangeListener_i0628140a2a = new android.widget.CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(android.widget.CompoundButton vw, boolean ic) {
            $_onCheckedChanged_i0628140a2a(vw, ic);
        }

    };
    private final android.widget.CompoundButton.OnCheckedChangeListener $_on_setOnCheckedChangeListener_fb96c5d3b3 = new android.widget.CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(android.widget.CompoundButton vw, boolean ic) {
            $_onCheckedChanged_fb96c5d3b3(vw, ic);
        }

    };
    private final android.widget.CompoundButton.OnCheckedChangeListener $_on_setOnCheckedChangeListener_i0e3cd8b2ec = new android.widget.CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(android.widget.CompoundButton vw, boolean ic) {
            $_onCheckedChanged_i0e3cd8b2ec(vw, ic);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i67b461495f = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i67b461495f(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i67b461495f = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i67b461495f(vw);
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
    private final android.widget.CompoundButton.OnCheckedChangeListener $_on_setOnCheckedChangeListener_i2e1dda294f = new android.widget.CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(android.widget.CompoundButton vw, boolean ic) {
            $_onCheckedChanged_i2e1dda294f(vw, ic);
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
        setContentView(open.cn.awg.pro.R.layout.f21);
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

            st.xdbj(open.cn.awg.pro.R.id.f21_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.f21_v).shxtck(false);

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

        if (zf.dy(a, "1")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");
            st.xdbj(open.cn.awg.pro.R.id.xdbj5).kjd(0);

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
            st.xdbj(open.cn.awg.pro.R.id.xdbj5).kjd(8);

        } else if (zf.dy(a, "3")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");
            st.xdbj(open.cn.awg.pro.R.id.xdbj5).kjd(0);

        } else if (zf.dy(a, "4")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0, 0, 0, 0);
            st.xdbj(open.cn.awg.pro.R.id.xdbj5).kjd(8);

        }

    }

    public void jz() {

        java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/f21/set1.inf";

        java.lang.String set2 = "/data/user/0/open.cn.awg.pro/settings/f8/set10.inf";

        java.lang.String set4 = "/data/user/0/open.cn.awg.pro/settings/f8/set4.inf";

        if (zf.dy(wj.dqwb(set1), "1")) {

            st.dxx(open.cn.awg.pro.R.id.dxx2).xzzt(true);

        } else if (zf.dy(wj.dqwb(set1), "2")) {

            st.dxx(open.cn.awg.pro.R.id.dxx3).xzzt(true);

        } else if (zf.dy(wj.dqwb(set1), "3")) {

            st.dxx(open.cn.awg.pro.R.id.dxx4).xzzt(true);

        } else if (zf.dy(wj.dqwb(set1), "4")) {

            st.dxx(open.cn.awg.pro.R.id.dxx5).xzzt(true);

        } else if (zf.dy(wj.dqwb(set1), "5")) {

            st.dxx(open.cn.awg.pro.R.id.dxx6).xzzt(true);

        } else {

            st.dxx(open.cn.awg.pro.R.id.dxx1).xzzt(true);

        }
        old_state_set1 = wj.dqwb(set1);
        jz2();

        i.runlibrary.app.v.kg kg3 = st.kg(open.cn.awg.pro.R.id.kg3);

        i.runlibrary.app.v.kg kg1 = st.kg(open.cn.awg.pro.R.id.kg1);

        if (zf.dy(wj.dqwb(set4), "true")) {

            old_state_kg3 = true;
            kg3.xzzt(true);

        }

        if (zf.dy(wj.dqwb(set2), "true")) {

            kg1.xzzt(true);

        }

        mComponentName = new android.content.ComponentName(getBaseContext(), getPackageName() + ".main2");

        mComponentNameDefault = new android.content.ComponentName(getBaseContext(), getPackageName() + ".ui.main.MainActivity");

        mPackageManager = getApplication().getPackageManager();

        iskg2enable = true;

    }

    public void jz2() {

        java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/f21/set1.inf";

        if (zf.dy(wj.dqwb(set1), "1")) {

            st.wb(open.cn.awg.pro.R.id.wb18).zf("开启软件将进入旧版主页");

        } else if (zf.dy(wj.dqwb(set1), "2")) {

            st.wb(open.cn.awg.pro.R.id.wb18).zf("开启软件将进入文件管理");

        } else if (zf.dy(wj.dqwb(set1), "3")) {

            st.wb(open.cn.awg.pro.R.id.wb18).zf("开启软件将进入腕上微聊");

        } else if (zf.dy(wj.dqwb(set1), "4")) {

            st.wb(open.cn.awg.pro.R.id.wb18).zf("开启软件将进入扩展插件");

        } else if (zf.dy(wj.dqwb(set1), "5")) {

            st.wb(open.cn.awg.pro.R.id.wb18).zf("开启软件将进入X5浏览器");

        } else {

            st.wb(open.cn.awg.pro.R.id.wb18).zf("开启软件将进入新版主页");

        }
        st.xdbj(open.cn.awg.pro.R.id.xdbj2).kjd(8);

    }

    public void icon2(boolean fl) {

        if (fl) {

            mPackageManager.setComponentEnabledSetting(mComponentName, android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_ENABLED, android.content.pm.PackageManager.DONT_KILL_APP);
            mPackageManager.setComponentEnabledSetting(mComponentNameDefault, android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_DISABLED, android.content.pm.PackageManager.DONT_KILL_APP);

        } else {

            mPackageManager.setComponentEnabledSetting(mComponentName, android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_DISABLED, android.content.pm.PackageManager.DONT_KILL_APP);
            mPackageManager.setComponentEnabledSetting(mComponentNameDefault, android.content.pm.PackageManager.COMPONENT_ENABLED_STATE_ENABLED, android.content.pm.PackageManager.DONT_KILL_APP);

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

        java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/f21/set1.inf";

        java.lang.String now = wj.dqwb(set1);

        if (!zf.dy(now, old_state_set1)) {

            gj.xc(new java.lang.Thread() {

                public void run() {

                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            java.lang.String[] name = new java.lang.String[]{"Msg"};

                            java.lang.String[] value = new java.lang.String[]{"应用成功 点击重启"};
                            gj.tz(ErrorMessageActivity.class, name, value);

                        }

                    });

                }

            });

        }

    }

    private void $_onClick_d26d5d7080(android.view.View vw) {
        gj.gb();

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

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb4).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb5).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onCheckedChanged_i1bca89f073(android.widget.CompoundButton vw, boolean ic) {

        if (iskg2enable) {

            java.lang.String set = "/data/user/0/open.cn.awg.pro/settings/f8/set10.inf";

            if (ic) {

                wj.xrwb(set, "true");
                icon2(true);

            } else {

                wj.xrwb(set, "false");
                icon2(false);

            }

        }

    }

    private void $_onClick_i20c2777fab(android.view.View vw) {

        i.runlibrary.app.v.kg kg = st.kg(open.cn.awg.pro.R.id.kg3);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean $_onLongClick_i20c2777fab(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb2).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb3).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onCheckedChanged_i2e1dda294f(android.widget.CompoundButton vw, boolean ic) {

        java.lang.String set = "/data/user/0/open.cn.awg.pro/settings/f8/set4.inf";

        if (ic) {

            wj.xrwb(set, "true");

        } else {

            wj.xrwb(set, "false");

        }

        if (ic != old_state_kg3) {

            gj.xc(new java.lang.Thread() {

                public void run() {

                    gj.zt(2000);
                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            java.lang.String[] name = new java.lang.String[]{"Msg"};

                            java.lang.String[] value = new java.lang.String[]{"应用成功 点击重启"};
                            gj.tz(ErrorMessageActivity.class, name, value);

                        }

                    });

                }

            });

        }

    }

    private void $_onClick_c0d3e00d2b(android.view.View vw) {
        gj.tz(DisplayMarginActivity.class);

    }

    private boolean $_onLongClick_c0d3e00d2b(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb6).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb7).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i67b461495f(android.view.View vw) {

        if (st.xdbj(open.cn.awg.pro.R.id.xdbj2).kjd() == 8) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.xdbj(open.cn.awg.pro.R.id.xdbj2).kjd(0);

                }

            });

        } else {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.xdbj(open.cn.awg.pro.R.id.xdbj2).kjd(8);

                }

            });

        }

    }

    private boolean $_onLongClick_i67b461495f(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb17).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb18).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onCheckedChanged_i0e3cd8b2ec(android.widget.CompoundButton vw, boolean ic) {

        java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/f21/set1.inf";

        java.lang.String update = "0";

        if (ic) {

            wj.xrwb(set1, update);
            jz2();

        }

    }

    private void $_onCheckedChanged_fb96c5d3b3(android.widget.CompoundButton vw, boolean ic) {

        java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/f21/set1.inf";

        java.lang.String update = "1";

        if (ic) {

            wj.xrwb(set1, update);
            jz2();

        }

    }

    private void $_onCheckedChanged_i0628140a2a(android.widget.CompoundButton vw, boolean ic) {

        java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/f21/set1.inf";

        java.lang.String update = "2";

        if (ic) {

            wj.xrwb(set1, update);
            jz2();

        }

    }

    private void $_onCheckedChanged_i5e048391d3(android.widget.CompoundButton vw, boolean ic) {

        java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/f21/set1.inf";

        java.lang.String update = "3";

        if (ic) {

            wj.xrwb(set1, update);
            jz2();

        }

    }

    private void $_onCheckedChanged_d983960091(android.widget.CompoundButton vw, boolean ic) {

        java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/f21/set1.inf";

        java.lang.String update = "4";

        if (ic) {

            wj.xrwb(set1, update);
            jz2();

        }

    }

    private void $_onCheckedChanged_d01948645a(android.widget.CompoundButton vw, boolean ic) {

        java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/f21/set1.inf";

        java.lang.String update = "5";

        if (ic) {

            wj.xrwb(set1, update);
            jz2();

        }

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

        android.widget.RelativeLayout i2202ede8ff = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj4);
        i2202ede8ff.setOnClickListener($_on_setOnClickListener_i2202ede8ff);
        i2202ede8ff.setOnLongClickListener($_on_setOnLongClickListener_i2202ede8ff);

        androidx.appcompat.widget.SwitchCompat i1bca89f073 = (androidx.appcompat.widget.SwitchCompat) findViewById(ay, vw, open.cn.awg.pro.R.id.kg1);
        i1bca89f073.setOnCheckedChangeListener($_on_setOnCheckedChangeListener_i1bca89f073);

        android.widget.RelativeLayout i20c2777fab = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj3);
        i20c2777fab.setOnClickListener($_on_setOnClickListener_i20c2777fab);
        i20c2777fab.setOnLongClickListener($_on_setOnLongClickListener_i20c2777fab);

        androidx.appcompat.widget.SwitchCompat i2e1dda294f = (androidx.appcompat.widget.SwitchCompat) findViewById(ay, vw, open.cn.awg.pro.R.id.kg3);
        i2e1dda294f.setOnCheckedChangeListener($_on_setOnCheckedChangeListener_i2e1dda294f);

        android.widget.RelativeLayout c0d3e00d2b = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj5);
        c0d3e00d2b.setOnClickListener($_on_setOnClickListener_c0d3e00d2b);
        c0d3e00d2b.setOnLongClickListener($_on_setOnLongClickListener_c0d3e00d2b);

        android.widget.RelativeLayout i67b461495f = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj11);
        i67b461495f.setOnClickListener($_on_setOnClickListener_i67b461495f);
        i67b461495f.setOnLongClickListener($_on_setOnLongClickListener_i67b461495f);

        android.widget.RadioButton i0e3cd8b2ec = (android.widget.RadioButton) findViewById(ay, vw, open.cn.awg.pro.R.id.dxx1);
        i0e3cd8b2ec.setOnCheckedChangeListener($_on_setOnCheckedChangeListener_i0e3cd8b2ec);

        android.widget.RadioButton fb96c5d3b3 = (android.widget.RadioButton) findViewById(ay, vw, open.cn.awg.pro.R.id.dxx2);
        fb96c5d3b3.setOnCheckedChangeListener($_on_setOnCheckedChangeListener_fb96c5d3b3);

        android.widget.RadioButton i0628140a2a = (android.widget.RadioButton) findViewById(ay, vw, open.cn.awg.pro.R.id.dxx3);
        i0628140a2a.setOnCheckedChangeListener($_on_setOnCheckedChangeListener_i0628140a2a);

        android.widget.RadioButton i5e048391d3 = (android.widget.RadioButton) findViewById(ay, vw, open.cn.awg.pro.R.id.dxx4);
        i5e048391d3.setOnCheckedChangeListener($_on_setOnCheckedChangeListener_i5e048391d3);

        android.widget.RadioButton d983960091 = (android.widget.RadioButton) findViewById(ay, vw, open.cn.awg.pro.R.id.dxx5);
        d983960091.setOnCheckedChangeListener($_on_setOnCheckedChangeListener_d983960091);

        android.widget.RadioButton d01948645a = (android.widget.RadioButton) findViewById(ay, vw, open.cn.awg.pro.R.id.dxx6);
        d01948645a.setOnCheckedChangeListener($_on_setOnCheckedChangeListener_d01948645a);

        android.widget.RelativeLayout i3167b45174 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj2);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        __layoutIsLoaded(ay, vw);
    }
}
