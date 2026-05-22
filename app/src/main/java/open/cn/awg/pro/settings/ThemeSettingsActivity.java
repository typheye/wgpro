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

import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.repair.ErrorMessageActivity;
import open.cn.awg.pro.ui.main.MainActivity;

public class ThemeSettingsActivity extends BaseAwgActivity {

    public final ThemeSettingsActivity lei = this, 类 = this;
    public ComponentName mComponentName;
    public ComponentName mComponentNameDefault;
    public PackageManager mPackageManager;
    public boolean iskg2enable = false;
    public boolean old_state_kg3 = false;
    public String old_state_set1 = "0";
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final CompoundButton.OnCheckedChangeListener dxx6CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onDxx6CheckedChanged(vw, ic);
        }

    };
    private final CompoundButton.OnCheckedChangeListener dxx5CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onDxx5CheckedChanged(vw, ic);
        }

    };
    private final CompoundButton.OnCheckedChangeListener dxx4CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onDxx4CheckedChanged(vw, ic);
        }

    };
    private final CompoundButton.OnCheckedChangeListener dxx3CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onDxx3CheckedChanged(vw, ic);
        }

    };
    private final CompoundButton.OnCheckedChangeListener dxx2CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onDxx2CheckedChanged(vw, ic);
        }

    };
    private final CompoundButton.OnCheckedChangeListener dxx1CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onDxx1CheckedChanged(vw, ic);
        }

    };
    private final View.OnLongClickListener xdbj11LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj11LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj11ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj11Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj5LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj5LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj5ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj5Click(vw);
        }

    };
    private final CompoundButton.OnCheckedChangeListener kg3CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onKg3CheckedChanged(vw, ic);
        }

    };
    private final View.OnLongClickListener xdbj3LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj3LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj3Click(vw);
        }

    };
    private final CompoundButton.OnCheckedChangeListener kg1CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onKg1CheckedChanged(vw, ic);
        }

    };
    private final View.OnLongClickListener xdbj4LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj4LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj4Click(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.theme_settings);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        if (zf.dy(wj.dqwb(AppPaths.appPath("settings/f8/set4.inf")), "true")) {

        }

        i.runlibrary.app.xt$pm pm = xt.pm();

        if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4s.inf")), "false")) {

            if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4.inf")), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb(AppPaths.appPath("settings/f17/set1.inf")));

        if (zf.dy(a, "4")) {

            st.xdbj(R.id.theme_settings_root).shxtck(true);

            String clr = zf.zf(R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(R.id.theme_settings_root).shxtck(false);
        applyFullscreenWindow();

        }

        if (zf.dy(a, "1")) {

            st.xdbj(R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");
            st.xdbj(R.id.xdbj5).kjd(0);

        } else if (zf.dy(a, "2")) {

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (wj.cz(a001) || zf.dy(wj.dqwb(a001), "true")) {

                i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
                wtab.nbj(0, "3dp", 0, "3dp");
                wtab.dqfs("center");

            } else {

                i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
                wtab.nbj(0, "9dp", 0, "9dp");
                wtab.dqfs("center");

            }
            st.xdbj(R.id.xdbj1).nbj(0, 0, 0, 0);
            st.xdbj(R.id.xdbj5).kjd(8);

        } else if (zf.dy(a, "3")) {

            st.xdbj(R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");
            st.xdbj(R.id.xdbj5).kjd(0);

        } else if (zf.dy(a, "4")) {

            st.xdbj(R.id.xdbj1).nbj(0, 0, 0, 0);
            st.xdbj(R.id.xdbj5).kjd(8);

        }

    }

    public void jz() {

        String set1 = AppPaths.appPath("settings/f21/set1.inf");

        String set2 = AppPaths.appPath("settings/f8/set10.inf");

        String set4 = AppPaths.appPath("settings/f8/set4.inf");

        if (zf.dy(wj.dqwb(set1), "1")) {

            st.dxx(R.id.dxx2).xzzt(true);

        } else if (zf.dy(wj.dqwb(set1), "2")) {

            st.dxx(R.id.dxx3).xzzt(true);

        } else if (zf.dy(wj.dqwb(set1), "3")) {

            st.dxx(R.id.dxx4).xzzt(true);

        } else if (zf.dy(wj.dqwb(set1), "4")) {

            st.dxx(R.id.dxx5).xzzt(true);

        } else if (zf.dy(wj.dqwb(set1), "5")) {

            st.dxx(R.id.dxx6).xzzt(true);

        } else {

            st.dxx(R.id.dxx1).xzzt(true);

        }
        old_state_set1 = wj.dqwb(set1);
        jz2();

        i.runlibrary.app.v.kg kg3 = st.kg(R.id.kg3);

        i.runlibrary.app.v.kg kg1 = st.kg(R.id.kg1);

        if (zf.dy(wj.dqwb(set4), "true")) {

            old_state_kg3 = true;
            kg3.xzzt(true);

        }

        if (zf.dy(wj.dqwb(set2), "true")) {

            kg1.xzzt(true);

        }

        mComponentName = new ComponentName(getBaseContext(), getPackageName() + ".main2");

        mComponentNameDefault = new ComponentName(getBaseContext(), getPackageName() + ".ui.main.MainActivity");

        mPackageManager = getApplication().getPackageManager();

        iskg2enable = true;

    }

    public void jz2() {

        String set1 = AppPaths.appPath("settings/f21/set1.inf");

        if (zf.dy(wj.dqwb(set1), "1")) {

            st.wb(R.id.wb18).zf("开启软件将进入旧版主页");

        } else if (zf.dy(wj.dqwb(set1), "2")) {

            st.wb(R.id.wb18).zf("开启软件将进入文件管理");

        } else if (zf.dy(wj.dqwb(set1), "3")) {

            st.wb(R.id.wb18).zf("开启软件将进入腕上微聊");

        } else if (zf.dy(wj.dqwb(set1), "4")) {

            st.wb(R.id.wb18).zf("开启软件将进入扩展插件");

        } else if (zf.dy(wj.dqwb(set1), "5")) {

            st.wb(R.id.wb18).zf("开启软件将进入X5浏览器");

        } else {

            st.wb(R.id.wb18).zf("开启软件将进入新版主页");

        }
        st.xdbj(R.id.xdbj2).kjd(8);

    }

    public void icon2(boolean fl) {

        if (fl) {

            mPackageManager.setComponentEnabledSetting(mComponentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
            mPackageManager.setComponentEnabledSetting(mComponentNameDefault, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);

        } else {

            mPackageManager.setComponentEnabledSetting(mComponentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
            mPackageManager.setComponentEnabledSetting(mComponentNameDefault, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

        }

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

        String set1 = AppPaths.appPath("settings/f21/set1.inf");

        String now = wj.dqwb(set1);

        if (!zf.dy(now, old_state_set1)) {

            gj.xc(new Thread() {

                public void run() {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            String[] name = new String[]{"Msg"};

                            String[] value = new String[]{"应用成功 点击重启"};
                            gj.tz(ErrorMessageActivity.class, name, value);

                        }

                    });

                }

            });

        }

    }

    private void onTitleBarClick(View vw) {
        gj.gb();

    }

    private void onXdbj4Click(View vw) {

        i.runlibrary.app.v.kg kg = st.kg(R.id.kg1);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean onXdbj4LongClick(View vw) {

        String te = st.wb(R.id.wb4).zf();

        String lj = st.wb(R.id.wb5).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onKg1CheckedChanged(CompoundButton vw, boolean ic) {

        if (iskg2enable) {

            String set = AppPaths.appPath("settings/f8/set10.inf");

            if (ic) {

                wj.xrwb(set, "true");
                icon2(true);

            } else {

                wj.xrwb(set, "false");
                icon2(false);

            }

        }

    }

    private void onXdbj3Click(View vw) {

        i.runlibrary.app.v.kg kg = st.kg(R.id.kg3);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean onXdbj3LongClick(View vw) {

        String te = st.wb(R.id.wb2).zf();

        String lj = st.wb(R.id.wb3).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onKg3CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/f8/set4.inf");

        if (ic) {

            wj.xrwb(set, "true");

        } else {

            wj.xrwb(set, "false");

        }

        if (ic != old_state_kg3) {

            gj.xc(new Thread() {

                public void run() {

                    gj.zt(2000);
                    gj.jmxc(new Runnable() {

                        public void run() {

                            String[] name = new String[]{"Msg"};

                            String[] value = new String[]{"应用成功 点击重启"};
                            gj.tz(ErrorMessageActivity.class, name, value);

                        }

                    });

                }

            });

        }

    }

    private void onXdbj5Click(View vw) {
        gj.tz(DisplayMarginActivity.class);

    }

    private boolean onXdbj5LongClick(View vw) {

        String te = st.wb(R.id.wb6).zf();

        String lj = st.wb(R.id.wb7).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj11Click(View vw) {

        if (st.xdbj(R.id.xdbj2).kjd() == 8) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.xdbj(R.id.xdbj2).kjd(0);

                }

            });

        } else {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.xdbj(R.id.xdbj2).kjd(8);

                }

            });

        }

    }

    private boolean onXdbj11LongClick(View vw) {

        String te = st.wb(R.id.wb17).zf();

        String lj = st.wb(R.id.wb18).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onDxx1CheckedChanged(CompoundButton vw, boolean ic) {

        String set1 = AppPaths.appPath("settings/f21/set1.inf");

        String update = "0";

        if (ic) {

            wj.xrwb(set1, update);
            jz2();

        }

    }

    private void onDxx2CheckedChanged(CompoundButton vw, boolean ic) {

        String set1 = AppPaths.appPath("settings/f21/set1.inf");

        String update = "1";

        if (ic) {

            wj.xrwb(set1, update);
            jz2();

        }

    }

    private void onDxx3CheckedChanged(CompoundButton vw, boolean ic) {

        String set1 = AppPaths.appPath("settings/f21/set1.inf");

        String update = "2";

        if (ic) {

            wj.xrwb(set1, update);
            jz2();

        }

    }

    private void onDxx4CheckedChanged(CompoundButton vw, boolean ic) {

        String set1 = AppPaths.appPath("settings/f21/set1.inf");

        String update = "3";

        if (ic) {

            wj.xrwb(set1, update);
            jz2();

        }

    }

    private void onDxx5CheckedChanged(CompoundButton vw, boolean ic) {

        String set1 = AppPaths.appPath("settings/f21/set1.inf");

        String update = "4";

        if (ic) {

            wj.xrwb(set1, update);
            jz2();

        }

    }

    private void onDxx6CheckedChanged(CompoundButton vw, boolean ic) {

        String set1 = AppPaths.appPath("settings/f21/set1.inf");

        String update = "5";

        if (ic) {

            wj.xrwb(set1, update);
            jz2();

        }

    }

    private void onTx1Click(View vw) {
        gj.gb();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) findViewById(ay, vw, R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);
        i2202ede8ff.setOnLongClickListener(xdbj4LongClickListener);

        SwitchCompat i1bca89f073 = (SwitchCompat) findViewById(ay, vw, R.id.kg1);
        i1bca89f073.setOnCheckedChangeListener(kg1CheckedChangeListener);

        RelativeLayout i20c2777fab = (RelativeLayout) findViewById(ay, vw, R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);
        i20c2777fab.setOnLongClickListener(xdbj3LongClickListener);

        SwitchCompat i2e1dda294f = (SwitchCompat) findViewById(ay, vw, R.id.kg3);
        i2e1dda294f.setOnCheckedChangeListener(kg3CheckedChangeListener);

        RelativeLayout c0d3e00d2b = (RelativeLayout) findViewById(ay, vw, R.id.xdbj5);
        c0d3e00d2b.setOnClickListener(xdbj5ClickListener);
        c0d3e00d2b.setOnLongClickListener(xdbj5LongClickListener);

        RelativeLayout i67b461495f = (RelativeLayout) findViewById(ay, vw, R.id.xdbj11);
        i67b461495f.setOnClickListener(xdbj11ClickListener);
        i67b461495f.setOnLongClickListener(xdbj11LongClickListener);

        RadioButton i0e3cd8b2ec = (RadioButton) findViewById(ay, vw, R.id.dxx1);
        i0e3cd8b2ec.setOnCheckedChangeListener(dxx1CheckedChangeListener);

        RadioButton fb96c5d3b3 = (RadioButton) findViewById(ay, vw, R.id.dxx2);
        fb96c5d3b3.setOnCheckedChangeListener(dxx2CheckedChangeListener);

        RadioButton i0628140a2a = (RadioButton) findViewById(ay, vw, R.id.dxx3);
        i0628140a2a.setOnCheckedChangeListener(dxx3CheckedChangeListener);

        RadioButton i5e048391d3 = (RadioButton) findViewById(ay, vw, R.id.dxx4);
        i5e048391d3.setOnCheckedChangeListener(dxx4CheckedChangeListener);

        RadioButton d983960091 = (RadioButton) findViewById(ay, vw, R.id.dxx5);
        d983960091.setOnCheckedChangeListener(dxx5CheckedChangeListener);

        RadioButton d01948645a = (RadioButton) findViewById(ay, vw, R.id.dxx6);
        d01948645a.setOnCheckedChangeListener(dxx6CheckedChangeListener);

        RelativeLayout i3167b45174 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj2);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
