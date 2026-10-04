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

public class ThemeSettingsActivity extends BaseAwgActivity {

    public final ThemeSettingsActivity lei = this, 类 = this;
    public ComponentName mComponentName;
    public ComponentName mComponentNameDefault;
    public PackageManager mPackageManager;
    public boolean iskg2enable = false;
    public boolean old_state_kg3 = false;
    public String old_state_set1 = "0";
    public AppRuntimeBridge e1;
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
        e1 = new AppRuntimeBridge(this);
        setContentView(R.layout.theme_settings);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ThemeSettingsActivity.this, (String) (AppPaths.appPath("settings/f8/set4.inf"))), "true")) {

        }

        

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ThemeSettingsActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ThemeSettingsActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(ThemeSettingsActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(ThemeSettingsActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));

        if (java.util.Objects.equals(a, "4")) {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.theme_settings_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(true));

            String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTab);
            setStatusBarColor(clr);

        } else {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.theme_settings_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

        }

        if (java.util.Objects.equals(a, "1")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.xdbj1)), u, 0, u, 50);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.title_bar));
            open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "15dp", 0, "3dp");
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));
            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.xdbj5)).setVisibility(android.view.View.VISIBLE);

        } else if (java.util.Objects.equals(a, "2")) {

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (open.cn.awg.pro.core.NativeFiles.exists(ThemeSettingsActivity.this, (String) (a001)) || java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ThemeSettingsActivity.this, (String) (a001)), "true")) {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.title_bar));
                open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "3dp", 0, "3dp");
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            } else {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.title_bar));
                open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "9dp", 0, "9dp");
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            }
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.xdbj1)), 0, 0, 0, 0);
            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.xdbj5)).setVisibility(android.view.View.GONE);

        } else if (java.util.Objects.equals(a, "3")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.xdbj1)), u, 0, u, 50);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.title_bar));
            open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "15dp", 0, "3dp");
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));
            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.xdbj5)).setVisibility(android.view.View.VISIBLE);

        } else if (java.util.Objects.equals(a, "4")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.xdbj1)), 0, 0, 0, 0);
            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.xdbj5)).setVisibility(android.view.View.GONE);

        }

    }

    public void jz() {

        String set1 = AppPaths.appPath("settings/f21/set1.inf");

        String set2 = AppPaths.appPath("settings/f8/set10.inf");

        String set4 = AppPaths.appPath("settings/f8/set4.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ThemeSettingsActivity.this, (String) (set1)), "1")) {

            ((android.widget.RadioButton) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.dxx2)).setChecked(java.lang.Boolean.TRUE.equals(true));

        } else if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ThemeSettingsActivity.this, (String) (set1)), "2")) {

            ((android.widget.RadioButton) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.dxx3)).setChecked(java.lang.Boolean.TRUE.equals(true));

        } else if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ThemeSettingsActivity.this, (String) (set1)), "3")) {

            ((android.widget.RadioButton) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.dxx4)).setChecked(java.lang.Boolean.TRUE.equals(true));

        } else if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ThemeSettingsActivity.this, (String) (set1)), "4")) {

            ((android.widget.RadioButton) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.dxx5)).setChecked(java.lang.Boolean.TRUE.equals(true));

        } else if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ThemeSettingsActivity.this, (String) (set1)), "5")) {

            ((android.widget.RadioButton) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.dxx6)).setChecked(java.lang.Boolean.TRUE.equals(true));

        } else {

            ((android.widget.RadioButton) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.dxx1)).setChecked(java.lang.Boolean.TRUE.equals(true));

        }
        old_state_set1 = open.cn.awg.pro.core.NativeFiles.readText(ThemeSettingsActivity.this, (String) (set1));
        jz2();

        android.widget.CompoundButton kg3 = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.kg3));

        android.widget.CompoundButton kg1 = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.kg1));

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ThemeSettingsActivity.this, (String) (set4)), "true")) {

            old_state_kg3 = true;
            kg3.setChecked(java.lang.Boolean.TRUE.equals(true));

        }

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ThemeSettingsActivity.this, (String) (set2)), "true")) {

            kg1.setChecked(java.lang.Boolean.TRUE.equals(true));

        }

        mComponentName = new ComponentName(getBaseContext(), getPackageName() + ".main2");

        mComponentNameDefault = new ComponentName(getBaseContext(), getPackageName() + ".ui.main.MainActivity");

        mPackageManager = getApplication().getPackageManager();

        iskg2enable = true;

    }

    public void jz2() {

        String set1 = AppPaths.appPath("settings/f21/set1.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ThemeSettingsActivity.this, (String) (set1)), "1")) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.wb18)).setText(String.valueOf("开启软件将进入旧版主页"));

        } else if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ThemeSettingsActivity.this, (String) (set1)), "2")) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.wb18)).setText(String.valueOf("开启软件将进入文件管理"));

        } else if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ThemeSettingsActivity.this, (String) (set1)), "3")) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.wb18)).setText(String.valueOf("开启软件将进入腕上微聊"));

        } else if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ThemeSettingsActivity.this, (String) (set1)), "4")) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.wb18)).setText(String.valueOf("开启软件将进入扩展插件"));

        } else if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ThemeSettingsActivity.this, (String) (set1)), "5")) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.wb18)).setText(String.valueOf("开启软件将进入X5浏览器"));

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.wb18)).setText(String.valueOf("开启软件将进入新版主页"));

        }
        ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.xdbj2)).setVisibility(android.view.View.GONE);

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

        String set1 = AppPaths.appPath("settings/f21/set1.inf");

        String now = open.cn.awg.pro.core.NativeFiles.readText(ThemeSettingsActivity.this, (String) (set1));

        if (!java.util.Objects.equals(now, old_state_set1)) {

            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            String[] name = new String[]{"Msg"};

                            String[] value = new String[]{"应用成功 点击重启"};
                            open.cn.awg.pro.core.NativeActions.launch(ThemeSettingsActivity.this, (Class<?>) (ErrorMessageActivity.class), (String[]) (name), (Object[]) (value));

                        }

                    });

                }

            });

        }

    }

    private void onTitleBarClick(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(ThemeSettingsActivity.this);

    }

    private void onXdbj4Click(View vw) {

        android.widget.CompoundButton kg = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.kg1));

        if (!kg.isChecked()) {

            kg.setChecked(java.lang.Boolean.TRUE.equals(true));

        } else {

            kg.setChecked(java.lang.Boolean.TRUE.equals(false));

        }

    }

    private boolean onXdbj4LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.wb4)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.wb5)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onKg1CheckedChanged(CompoundButton vw, boolean ic) {

        if (iskg2enable) {

            String set = AppPaths.appPath("settings/f8/set10.inf");

            if (ic) {

                open.cn.awg.pro.core.NativeFiles.writeText(ThemeSettingsActivity.this, (String) (set), (String) ("true"));
                icon2(true);

            } else {

                open.cn.awg.pro.core.NativeFiles.writeText(ThemeSettingsActivity.this, (String) (set), (String) ("false"));
                icon2(false);

            }

        }

    }

    private void onXdbj3Click(View vw) {

        android.widget.CompoundButton kg = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.kg3));

        if (!kg.isChecked()) {

            kg.setChecked(java.lang.Boolean.TRUE.equals(true));

        } else {

            kg.setChecked(java.lang.Boolean.TRUE.equals(false));

        }

    }

    private boolean onXdbj3LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.wb2)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.wb3)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onKg3CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/f8/set4.inf");

        if (ic) {

            open.cn.awg.pro.core.NativeFiles.writeText(ThemeSettingsActivity.this, (String) (set), (String) ("true"));

        } else {

            open.cn.awg.pro.core.NativeFiles.writeText(ThemeSettingsActivity.this, (String) (set), (String) ("false"));

        }

        if (ic != old_state_kg3) {

            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.sleep(2000);
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            String[] name = new String[]{"Msg"};

                            String[] value = new String[]{"应用成功 点击重启"};
                            open.cn.awg.pro.core.NativeActions.launch(ThemeSettingsActivity.this, (Class<?>) (ErrorMessageActivity.class), (String[]) (name), (Object[]) (value));

                        }

                    });

                }

            });

        }

    }

    private void onXdbj5Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(ThemeSettingsActivity.this, (Class<?>) (DisplayMarginActivity.class));

    }

    private boolean onXdbj5LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.wb6)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.wb7)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj11Click(View vw) {

        if (((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.xdbj2)).getVisibility() == android.view.View.GONE) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.xdbj2)).setVisibility(android.view.View.VISIBLE);

                }

            });

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.xdbj2)).setVisibility(android.view.View.GONE);

                }

            });

        }

    }

    private boolean onXdbj11LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.wb17)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThemeSettingsActivity.this.getWindow().getDecorView(), R.id.wb18)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onDxx1CheckedChanged(CompoundButton vw, boolean ic) {

        String set1 = AppPaths.appPath("settings/f21/set1.inf");

        String update = "0";

        if (ic) {

            open.cn.awg.pro.core.NativeFiles.writeText(ThemeSettingsActivity.this, (String) (set1), (String) (update));
            jz2();

        }

    }

    private void onDxx2CheckedChanged(CompoundButton vw, boolean ic) {

        String set1 = AppPaths.appPath("settings/f21/set1.inf");

        String update = "1";

        if (ic) {

            open.cn.awg.pro.core.NativeFiles.writeText(ThemeSettingsActivity.this, (String) (set1), (String) (update));
            jz2();

        }

    }

    private void onDxx3CheckedChanged(CompoundButton vw, boolean ic) {

        String set1 = AppPaths.appPath("settings/f21/set1.inf");

        String update = "2";

        if (ic) {

            open.cn.awg.pro.core.NativeFiles.writeText(ThemeSettingsActivity.this, (String) (set1), (String) (update));
            jz2();

        }

    }

    private void onDxx4CheckedChanged(CompoundButton vw, boolean ic) {

        String set1 = AppPaths.appPath("settings/f21/set1.inf");

        String update = "3";

        if (ic) {

            open.cn.awg.pro.core.NativeFiles.writeText(ThemeSettingsActivity.this, (String) (set1), (String) (update));
            jz2();

        }

    }

    private void onDxx5CheckedChanged(CompoundButton vw, boolean ic) {

        String set1 = AppPaths.appPath("settings/f21/set1.inf");

        String update = "4";

        if (ic) {

            open.cn.awg.pro.core.NativeFiles.writeText(ThemeSettingsActivity.this, (String) (set1), (String) (update));
            jz2();

        }

    }

    private void onDxx6CheckedChanged(CompoundButton vw, boolean ic) {

        String set1 = AppPaths.appPath("settings/f21/set1.inf");

        String update = "5";

        if (ic) {

            open.cn.awg.pro.core.NativeFiles.writeText(ThemeSettingsActivity.this, (String) (set1), (String) (update));
            jz2();

        }

    }

    private void onTx1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(ThemeSettingsActivity.this);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);
        i2202ede8ff.setOnLongClickListener(xdbj4LongClickListener);

        SwitchCompat i1bca89f073 = (SwitchCompat) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.kg1);
        i1bca89f073.setOnCheckedChangeListener(kg1CheckedChangeListener);

        RelativeLayout i20c2777fab = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);
        i20c2777fab.setOnLongClickListener(xdbj3LongClickListener);

        SwitchCompat i2e1dda294f = (SwitchCompat) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.kg3);
        i2e1dda294f.setOnCheckedChangeListener(kg3CheckedChangeListener);

        RelativeLayout c0d3e00d2b = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj5);
        c0d3e00d2b.setOnClickListener(xdbj5ClickListener);
        c0d3e00d2b.setOnLongClickListener(xdbj5LongClickListener);

        RelativeLayout i67b461495f = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj11);
        i67b461495f.setOnClickListener(xdbj11ClickListener);
        i67b461495f.setOnLongClickListener(xdbj11LongClickListener);

        RadioButton i0e3cd8b2ec = (RadioButton) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.dxx1);
        i0e3cd8b2ec.setOnCheckedChangeListener(dxx1CheckedChangeListener);

        RadioButton fb96c5d3b3 = (RadioButton) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.dxx2);
        fb96c5d3b3.setOnCheckedChangeListener(dxx2CheckedChangeListener);

        RadioButton i0628140a2a = (RadioButton) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.dxx3);
        i0628140a2a.setOnCheckedChangeListener(dxx3CheckedChangeListener);

        RadioButton i5e048391d3 = (RadioButton) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.dxx4);
        i5e048391d3.setOnCheckedChangeListener(dxx4CheckedChangeListener);

        RadioButton d983960091 = (RadioButton) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.dxx5);
        d983960091.setOnCheckedChangeListener(dxx5CheckedChangeListener);

        RadioButton d01948645a = (RadioButton) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.dxx6);
        d01948645a.setOnCheckedChangeListener(dxx6CheckedChangeListener);

        RelativeLayout i3167b45174 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj2);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
