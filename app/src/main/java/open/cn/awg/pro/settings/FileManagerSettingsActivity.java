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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.SwitchCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import java.lang.reflect.Method;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class FileManagerSettingsActivity extends BaseAwgActivity {

    public final FileManagerSettingsActivity lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
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
    private final CompoundButton.OnCheckedChangeListener kg2CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onKg2CheckedChanged(vw, ic);
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
    private final CompoundButton.OnCheckedChangeListener kg4CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onKg4CheckedChanged(vw, ic);
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
    private final CompoundButton.OnCheckedChangeListener kg3CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onKg3CheckedChanged(vw, ic);
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
    private final CompoundButton.OnCheckedChangeListener kg1CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onKg1CheckedChanged(vw, ic);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.file_manager_settings);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.file_manager_settings_root, R.id.xdbj1, R.id.title_bar);

    }

    public void jz() {

        String set1 = AppPaths.appPath("settings/d1/set1.inf");

        String set2 = AppPaths.appPath("settings/d1/set2.inf");

        String set3 = AppPaths.appPath("settings/d1/set3.inf");

        String set4 = AppPaths.appPath("settings/d1/set4.inf");

        i.runlibrary.app.v.kg kg1 = st.kg(R.id.kg1);

        i.runlibrary.app.v.kg kg2 = st.kg(R.id.kg2);

        i.runlibrary.app.v.kg kg3 = st.kg(R.id.kg3);

        i.runlibrary.app.v.kg kg4 = st.kg(R.id.kg4);

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

        i.runlibrary.app.v.xxbj xxbj4 = st.xxbj(R.id.xxbj4);

        i.runlibrary.app.v.wb wb12 = st.wb(R.id.wb12);

        int sdk = xt.sbxx().sdk;

        if (sdk >= 30) {

            try {

                Class cs = Environment.class;

                Method m = cs.getMethod("isExternalStorageManager");

                Object o = m.invoke(null);

                if (o.equals(true)) {

                    wb12.zf("已授权该权限");

                } else {

                    wb12.zf("未授权该权限");

                }

            } catch (Throwable __$_e__) {

                wb12.zf("未知权限状态");

            }
            xxbj4.kjd(0);

        } else {

            xxbj4.kjd(8);

        }

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
            applyWindowModeFromSettings();
        jz();

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

    }

    private void onTitleBarClick(View vw) {
        gj.gb();

    }

    private void onKg1CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/d1/set1.inf");

        if (ic) {

            wj.xrwb(set, "true");

        } else {

            wj.xrwb(set, "false");

        }

    }

    private void onXdbj2Click(View vw) {

        i.runlibrary.app.v.kg kg = st.kg(R.id.kg1);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean onXdbj2LongClick(View vw) {

        String te = st.wb(R.id.wb2).zf();

        String lj = st.wb(R.id.wb3).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onKg3CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/d1/set3.inf");

        if (ic) {

            wj.xrwb(set, "true");

        } else {

            wj.xrwb(set, "false");

        }

    }

    private void onXdbj4Click(View vw) {

        i.runlibrary.app.v.kg kg = st.kg(R.id.kg3);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean onXdbj4LongClick(View vw) {

        String te = st.wb(R.id.wb6).zf();

        String lj = st.wb(R.id.wb7).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onKg4CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/d1/set4.inf");

        if (ic) {

            wj.xrwb(set, "true");

        } else {

            wj.xrwb(set, "false");

        }

    }

    private void onXdbj5Click(View vw) {

        i.runlibrary.app.v.kg kg = st.kg(R.id.kg4);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean onXdbj5LongClick(View vw) {

        String te = st.wb(R.id.wb8).zf();

        String lj = st.wb(R.id.wb9).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onKg2CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/d1/set2.inf");

        if (ic) {

            wj.xrwb(set, "true");

        } else {

            wj.xrwb(set, "false");

        }

    }

    private void onXdbj3Click(View vw) {

        i.runlibrary.app.v.kg kg = st.kg(R.id.kg2);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean onXdbj3LongClick(View vw) {

        String te = st.wb(R.id.wb4).zf();

        String lj = st.wb(R.id.wb5).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj6Click(View vw) {

        try {

            Class cs = Environment.class;

            Method m = cs.getMethod("isExternalStorageManager");

            Object o = m.invoke(null);

            if (o.equals(true)) {

                e1.tsk("提示", "已授权该权限");

            } else {

                Intent it = new Intent("android.settings.MANAGE_ALL_FILES_ACCESS_PERMISSION");
                lei.startActivity(it);

            }

        } catch (Throwable e) {

            gj.gb();
            e1.upload_error(e, "f6.*");

        }

    }

    private boolean onXdbj6LongClick(View vw) {

        String te = st.wb(R.id.wb11).zf();

        String lj = st.wb(R.id.wb12).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

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

        SwitchCompat i1bca89f073 = (SwitchCompat) findViewById(ay, vw, R.id.kg1);
        i1bca89f073.setOnCheckedChangeListener(kg1CheckedChangeListener);

        RelativeLayout i3167b45174 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);
        i3167b45174.setOnLongClickListener(xdbj2LongClickListener);

        SwitchCompat i2e1dda294f = (SwitchCompat) findViewById(ay, vw, R.id.kg3);
        i2e1dda294f.setOnCheckedChangeListener(kg3CheckedChangeListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) findViewById(ay, vw, R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);
        i2202ede8ff.setOnLongClickListener(xdbj4LongClickListener);

        SwitchCompat c6051f0d45 = (SwitchCompat) findViewById(ay, vw, R.id.kg4);
        c6051f0d45.setOnCheckedChangeListener(kg4CheckedChangeListener);

        RelativeLayout c0d3e00d2b = (RelativeLayout) findViewById(ay, vw, R.id.xdbj5);
        c0d3e00d2b.setOnClickListener(xdbj5ClickListener);
        c0d3e00d2b.setOnLongClickListener(xdbj5LongClickListener);

        SwitchCompat ab7dd47b08 = (SwitchCompat) findViewById(ay, vw, R.id.kg2);
        ab7dd47b08.setOnCheckedChangeListener(kg2CheckedChangeListener);

        RelativeLayout i20c2777fab = (RelativeLayout) findViewById(ay, vw, R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);
        i20c2777fab.setOnLongClickListener(xdbj3LongClickListener);

        RelativeLayout i171cedf1eb = (RelativeLayout) findViewById(ay, vw, R.id.xdbj6);
        i171cedf1eb.setOnClickListener(xdbj6ClickListener);
        i171cedf1eb.setOnLongClickListener(xdbj6LongClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
