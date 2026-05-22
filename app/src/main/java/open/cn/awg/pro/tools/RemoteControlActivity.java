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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import java.io.File;

import open.cn.awg.pro.account.TypheyeAccountActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.core.DevicePolicyBridge;
import open.cn.awg.pro.music.TuneFreeMusicBridge;
import open.cn.awg.pro.R;
import open.cn.awg.pro.service.SystemAccessibilityService;

public class RemoteControlActivity extends BaseAwgActivity {

    public final RemoteControlActivity lei = this, 类 = this;
    public String um = "";
    public File f;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public TuneFreeMusicBridge e12 = new TuneFreeMusicBridge(_APPINFO);
    public DevicePolicyBridge carton = new DevicePolicyBridge(_APPINFO);
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj9LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj9LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj9ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj9Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj7LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj7LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj7ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj7Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj8LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj8LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj8ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj8Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj10LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj10LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj10ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj10Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj6LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj6LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj6ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj6Click(vw);
        }

    };
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
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
    private final View.OnLongClickListener xdbj2LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj2LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj2Click(vw);
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
        setContentView(R.layout.remote_control);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        csh();
        show(0);

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.remote_control_root, R.id.xdbj1, R.id.title_bar);

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

        final i.runlibrary.app.v.wb tab = st.wb(R.id.title_bar);

        final i.runlibrary.app.v.xxbj x1 = st.xxbj(R.id.xxbj1);

        final i.runlibrary.app.v.xxbj x2 = st.xxbj(R.id.xxbj6);

        final i.runlibrary.app.v.xxbj x3 = st.xxbj(R.id.xxbj2);

        if (i == 0) {

            gj.jmxc(new Runnable() {

                public void run() {

                    tab.zf("< 腕管远控");
                    x1.kjd(8);
                    x2.kjd(8);
                    x3.kjd(0);

                }

            });

        } else if (i == 1) {

            gj.jmxc(new Runnable() {

                public void run() {

                    tab.zf("< 扩展权限");
                    x1.kjd(0);
                    x2.kjd(8);
                    x3.kjd(8);

                }

            });

        } else if (i == 2) {

            gj.jmxc(new Runnable() {

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

        String set1 = AppPaths.appPath("settings/other/0xK9HJ4/Enable");

        String user = AppPaths.appPath("settings/account/user");

        i.runlibrary.app.v.kg kg1 = st.kg(R.id.kg1);

        i.runlibrary.app.v.wb wb4 = st.wb(R.id.wb4);

        i.runlibrary.app.v.wb wb9 = st.wb(R.id.wb9);

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

            aa = SystemAccessibilityService.isAccessibilitySettingsOn(lei, SystemAccessibilityService.class.getName());

        } catch (Throwable e) {

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

        i.runlibrary.app.v.wb wb11 = st.wb(R.id.wb11);

        if (carton.state(lei)) {

            wb11.zf("此权限已授权");

        } else {

            wb11.zf("此权限未授权");

        }

        boolean aa;

        try {

            aa = SystemAccessibilityService.isAccessibilitySettingsOn(lei, SystemAccessibilityService.class.getName());

        } catch (Throwable e) {

            aa = false;

        }

        i.runlibrary.app.v.wb wb21 = st.wb(R.id.wb21);

        if (aa) {

            wb21.zf("此权限已授权");

        } else {

            wb21.zf("此权限未授权");

        }

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
            applyWindowModeFromSettings();

        if (zf.dy(st.wb(R.id.title_bar).zf(), "< 远程锁屏")) {

            csh();

        } else if (zf.dy(st.wb(R.id.title_bar).zf(), "< 扩展权限")) {

            jzpd();

        }

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

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

            if (zf.dy(st.wb(R.id.title_bar).zf(), "< 腕管远控")) {

                gj.gb();

            } else if (zf.dy(st.wb(R.id.title_bar).zf(), "< 扩展权限")) {

                show(0);
                csh();

            }

        }
        return false;

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onTitleBarClick(View vw) {

        if (zf.dy(st.wb(R.id.title_bar).zf(), "< 腕管远控")) {

            gj.gb();

        } else if (zf.dy(st.wb(R.id.title_bar).zf(), "< 扩展权限")) {

            show(0);
            csh();

        }

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

        String te = st.wb(R.id.wb2).zf();

        String lj = st.wb(R.id.wb7).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onKg1CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/other/0xK9HJ4/Enable");

        if (ic) {

            if (e1.islogin()) {

                boolean aa;

                try {

                    aa = SystemAccessibilityService.isAccessibilitySettingsOn(lei, SystemAccessibilityService.class.getName());

                } catch (Throwable e) {

                    aa = false;

                }

                if (carton.state(lei) && aa) {

                    wj.xrwb(set, "true");

                } else {

                    e1.tsk("提示", "存在必要权限未授权");

                    i.runlibrary.app.v.kg kg1 = st.kg(R.id.kg1);
                    kg1.xzzt(false);

                }

            } else {

                wj.xrwb(set, "false");
                gj.jmxc(new Runnable() {

                    public void run() {

                        i.runlibrary.app.v.kg kg = st.kg(R.id.kg1);
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

    private void onXdbj5Click(View vw) {
        e1.tsk("扩展身份", st.wb(R.id.wb9).zf());

    }

    private boolean onXdbj5LongClick(View vw) {

        String te = st.wb(R.id.wb8).zf();

        String lj = st.wb(R.id.wb9).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj2Click(View vw) {
        jzpd();

    }

    private boolean onXdbj2LongClick(View vw) {

        String te = st.wb(R.id.wb3).zf();

        String lj = st.wb(R.id.wb4).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj3Click(View vw) {
        e1.tsk("功能帮助", "此服务由官方提供");

    }

    private boolean onXdbj3LongClick(View vw) {

        String te = st.wb(R.id.wb5).zf();

        String lj = st.wb(R.id.wb6).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx2Click(View vw) {
        gj.gb();

    }

    private void onXdbj6Click(View vw) {
        carton.gets(lei);

    }

    private boolean onXdbj6LongClick(View vw) {

        String te = st.wb(R.id.wb10).zf();

        String lj = st.wb(R.id.wb11).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj10Click(View vw) {

        boolean aa;

        try {

            aa = SystemAccessibilityService.isAccessibilitySettingsOn(lei, SystemAccessibilityService.class.getName());

        } catch (Throwable e) {

            aa = false;

        }

        if (!aa) {

            try {

                Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                lei.startActivity(intent);

            } catch (Throwable e) {

                gj.gb();
                e1.upload_error(e, "j3.run()");

            }

        } else {

        }

    }

    private boolean onXdbj10LongClick(View vw) {

        String te = st.wb(R.id.wb20).zf();

        String lj = st.wb(R.id.wb21).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj8Click(View vw) {
        simpleSetting(lei);

    }

    private boolean onXdbj8LongClick(View vw) {

        String te = st.wb(R.id.wb16).zf();

        String lj = st.wb(R.id.wb17).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj7Click(View vw) {
        simpleSetting(lei);

    }

    private boolean onXdbj7LongClick(View vw) {

        String te = st.wb(R.id.wb12).zf();

        String lj = st.wb(R.id.wb13).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj9Click(View vw) {
        simpleSetting(lei);

    }

    private boolean onXdbj9LongClick(View vw) {

        String te = st.wb(R.id.wb18).zf();

        String lj = st.wb(R.id.wb19).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx1Click(View vw) {
        show(0);

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

        RelativeLayout c0d3e00d2b = (RelativeLayout) findViewById(ay, vw, R.id.xdbj5);
        c0d3e00d2b.setOnClickListener(xdbj5ClickListener);
        c0d3e00d2b.setOnLongClickListener(xdbj5LongClickListener);

        RelativeLayout i3167b45174 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);
        i3167b45174.setOnLongClickListener(xdbj2LongClickListener);

        RelativeLayout i20c2777fab = (RelativeLayout) findViewById(ay, vw, R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);
        i20c2777fab.setOnLongClickListener(xdbj3LongClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        RelativeLayout i171cedf1eb = (RelativeLayout) findViewById(ay, vw, R.id.xdbj6);
        i171cedf1eb.setOnClickListener(xdbj6ClickListener);
        i171cedf1eb.setOnLongClickListener(xdbj6LongClickListener);

        RelativeLayout i5b34eede7f = (RelativeLayout) findViewById(ay, vw, R.id.xdbj10);
        i5b34eede7f.setOnClickListener(xdbj10ClickListener);
        i5b34eede7f.setOnLongClickListener(xdbj10LongClickListener);

        RelativeLayout fab146d7a5 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj8);
        fab146d7a5.setOnClickListener(xdbj8ClickListener);
        fab146d7a5.setOnLongClickListener(xdbj8LongClickListener);

        RelativeLayout e585d94488 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj7);
        e585d94488.setOnClickListener(xdbj7ClickListener);
        e585d94488.setOnLongClickListener(xdbj7LongClickListener);

        RelativeLayout i5a93676674 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj9);
        i5a93676674.setOnClickListener(xdbj9ClickListener);
        i5a93676674.setOnLongClickListener(xdbj9LongClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
