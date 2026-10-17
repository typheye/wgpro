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
import androidx.appcompat.widget.SwitchCompat;
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
    public AppRuntimeBridge e1;
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj6ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj6Click(vw);
        }

    };
;
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
;
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
;
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
;
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
        e1 = new AppRuntimeBridge(this);
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

        android.widget.CompoundButton kg1 = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerSettingsActivity.this.getWindow().getDecorView(), R.id.kg1));

        android.widget.CompoundButton kg2 = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerSettingsActivity.this.getWindow().getDecorView(), R.id.kg2));

        android.widget.CompoundButton kg3 = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerSettingsActivity.this.getWindow().getDecorView(), R.id.kg3));

        android.widget.CompoundButton kg4 = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerSettingsActivity.this.getWindow().getDecorView(), R.id.kg4));

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(FileManagerSettingsActivity.this, (String) (set1)), "true")) {

            kg1.setChecked(java.lang.Boolean.TRUE.equals(true));

        }

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(FileManagerSettingsActivity.this, (String) (set2)), "true")) {

            kg2.setChecked(java.lang.Boolean.TRUE.equals(true));

        }

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(FileManagerSettingsActivity.this, (String) (set3)), "true")) {

            kg3.setChecked(java.lang.Boolean.TRUE.equals(true));

        }

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(FileManagerSettingsActivity.this, (String) (set4)), "true")) {

            kg4.setChecked(java.lang.Boolean.TRUE.equals(true));

        }

        android.widget.LinearLayout xxbj4 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerSettingsActivity.this.getWindow().getDecorView(), R.id.xxbj4));

        android.widget.TextView wb12 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerSettingsActivity.this.getWindow().getDecorView(), R.id.wb12));

        int sdk = android.os.Build.VERSION.SDK_INT;

        if (sdk >= 30) {

            try {

                Class cs = Environment.class;

                Method m = cs.getMethod("isExternalStorageManager");

                Object o = m.invoke(null);

                if (o.equals(true)) {

                    wb12.setText(String.valueOf("已授权该权限"));

                } else {

                    wb12.setText(String.valueOf("未授权该权限"));

                }

            } catch (Throwable __$_e__) {

                wb12.setText(String.valueOf("未知权限状态"));

            }
            xxbj4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

        } else {

            xxbj4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerSettingsActivity.this.getWindow().getDecorView(), R.id.qtgd1));

        qtgd1.setOnGenericMotionListener(new View.OnGenericMotionListener() {
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

        qtgd1.requestFocus();

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
        open.cn.awg.pro.core.NativeActions.finish(FileManagerSettingsActivity.this);

    }

    private void onKg1CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/d1/set1.inf");

        if (ic) {

            open.cn.awg.pro.core.NativeFiles.writeText(FileManagerSettingsActivity.this, (String) (set), (String) ("true"));

        } else {

            open.cn.awg.pro.core.NativeFiles.writeText(FileManagerSettingsActivity.this, (String) (set), (String) ("false"));

        }

    }

    private void onXdbj2Click(View vw) {

        android.widget.CompoundButton kg = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerSettingsActivity.this.getWindow().getDecorView(), R.id.kg1));

        if (!kg.isChecked()) {

            kg.setChecked(java.lang.Boolean.TRUE.equals(true));

        } else {

            kg.setChecked(java.lang.Boolean.TRUE.equals(false));

        }

    }



    private void onKg3CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/d1/set3.inf");

        if (ic) {

            open.cn.awg.pro.core.NativeFiles.writeText(FileManagerSettingsActivity.this, (String) (set), (String) ("true"));

        } else {

            open.cn.awg.pro.core.NativeFiles.writeText(FileManagerSettingsActivity.this, (String) (set), (String) ("false"));

        }

    }

    private void onXdbj4Click(View vw) {

        android.widget.CompoundButton kg = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerSettingsActivity.this.getWindow().getDecorView(), R.id.kg3));

        if (!kg.isChecked()) {

            kg.setChecked(java.lang.Boolean.TRUE.equals(true));

        } else {

            kg.setChecked(java.lang.Boolean.TRUE.equals(false));

        }

    }



    private void onKg4CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/d1/set4.inf");

        if (ic) {

            open.cn.awg.pro.core.NativeFiles.writeText(FileManagerSettingsActivity.this, (String) (set), (String) ("true"));

        } else {

            open.cn.awg.pro.core.NativeFiles.writeText(FileManagerSettingsActivity.this, (String) (set), (String) ("false"));

        }

    }

    private void onXdbj5Click(View vw) {

        android.widget.CompoundButton kg = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerSettingsActivity.this.getWindow().getDecorView(), R.id.kg4));

        if (!kg.isChecked()) {

            kg.setChecked(java.lang.Boolean.TRUE.equals(true));

        } else {

            kg.setChecked(java.lang.Boolean.TRUE.equals(false));

        }

    }



    private void onKg2CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/d1/set2.inf");

        if (ic) {

            open.cn.awg.pro.core.NativeFiles.writeText(FileManagerSettingsActivity.this, (String) (set), (String) ("true"));

        } else {

            open.cn.awg.pro.core.NativeFiles.writeText(FileManagerSettingsActivity.this, (String) (set), (String) ("false"));

        }

    }

    private void onXdbj3Click(View vw) {

        android.widget.CompoundButton kg = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerSettingsActivity.this.getWindow().getDecorView(), R.id.kg2));

        if (!kg.isChecked()) {

            kg.setChecked(java.lang.Boolean.TRUE.equals(true));

        } else {

            kg.setChecked(java.lang.Boolean.TRUE.equals(false));

        }

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

            open.cn.awg.pro.core.NativeActions.finish(FileManagerSettingsActivity.this);
            e1.upload_error(e, "f6.*");

        }

    }



    private void onTx1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(FileManagerSettingsActivity.this);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        SwitchCompat i1bca89f073 = (SwitchCompat) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.kg1);
        i1bca89f073.setOnCheckedChangeListener(kg1CheckedChangeListener);

        RelativeLayout i3167b45174 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);

        SwitchCompat i2e1dda294f = (SwitchCompat) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.kg3);
        i2e1dda294f.setOnCheckedChangeListener(kg3CheckedChangeListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);

        SwitchCompat c6051f0d45 = (SwitchCompat) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.kg4);
        c6051f0d45.setOnCheckedChangeListener(kg4CheckedChangeListener);

        RelativeLayout c0d3e00d2b = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj5);
        c0d3e00d2b.setOnClickListener(xdbj5ClickListener);

        SwitchCompat ab7dd47b08 = (SwitchCompat) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.kg2);
        ab7dd47b08.setOnCheckedChangeListener(kg2CheckedChangeListener);

        RelativeLayout i20c2777fab = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);

        RelativeLayout i171cedf1eb = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj6);
        i171cedf1eb.setOnClickListener(xdbj6ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
