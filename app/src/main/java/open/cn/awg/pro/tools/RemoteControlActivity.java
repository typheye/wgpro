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
    public AppRuntimeBridge e1;
    public TuneFreeMusicBridge e12;
    public DevicePolicyBridge carton;
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
        e1 = new AppRuntimeBridge(this);
        e12 = new TuneFreeMusicBridge(this);
        carton = new DevicePolicyBridge(this);
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

        final android.widget.TextView tab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.title_bar));

        final android.widget.LinearLayout x1 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.xxbj1));

        final android.widget.LinearLayout x2 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.xxbj6));

        final android.widget.LinearLayout x3 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.xxbj2));

        if (i == 0) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    tab.setText(String.valueOf("腕管远控"));
                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                }

            });

        } else if (i == 1) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    tab.setText(String.valueOf("扩展权限"));
                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        } else if (i == 2) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    tab.setText(String.valueOf("腕管远控"));
                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        }

    }

    public void csh() {

        String set1 = AppPaths.appPath("settings/other/0xK9HJ4/Enable");

        String user = AppPaths.appPath("settings/account/user");

        android.widget.CompoundButton kg1 = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.kg1));

        android.widget.TextView wb4 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb4));

        android.widget.TextView wb9 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb9));

        if (e1.islogin()) {

            wb9.setText(String.valueOf(open.cn.awg.pro.core.NativeFiles.readText(RemoteControlActivity.this, (String) (user))));

        } else {

            wb9.setText(String.valueOf("未登录Typheye账户"));

        }

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(RemoteControlActivity.this, (String) (set1)), "true") && e1.islogin()) {

            kg1.setChecked(java.lang.Boolean.TRUE.equals(true));

        }

        boolean aa;

        try {

            aa = SystemAccessibilityService.isAccessibilitySettingsOn(lei, SystemAccessibilityService.class.getName());

        } catch (Throwable e) {

            aa = false;

        }

        if (carton.state(lei) && aa) {

            wb4.setText(String.valueOf("已授权必要权限"));

        } else {

            wb4.setText(String.valueOf("存在权限未授权"));
            kg1.setChecked(java.lang.Boolean.TRUE.equals(false));

        }

    }

    public void jzpd() {
        show(1);

        android.widget.TextView wb11 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb11));

        if (carton.state(lei)) {

            wb11.setText(String.valueOf("此权限已授权"));

        } else {

            wb11.setText(String.valueOf("此权限未授权"));

        }

        boolean aa;

        try {

            aa = SystemAccessibilityService.isAccessibilitySettingsOn(lei, SystemAccessibilityService.class.getName());

        } catch (Throwable e) {

            aa = false;

        }

        android.widget.TextView wb21 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb21));

        if (aa) {

            wb21.setText(String.valueOf("此权限已授权"));

        } else {

            wb21.setText(String.valueOf("此权限未授权"));

        }

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
            applyWindowModeFromSettings();

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "远程锁屏")) {

            csh();

        } else if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "扩展权限")) {

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

            if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "腕管远控")) {

                open.cn.awg.pro.core.NativeActions.finish(RemoteControlActivity.this);

            } else if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "扩展权限")) {

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

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "腕管远控")) {

            open.cn.awg.pro.core.NativeActions.finish(RemoteControlActivity.this);

        } else if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "扩展权限")) {

            show(0);
            csh();

        }

    }

    private void onXdbj4Click(View vw) {

        android.widget.CompoundButton kg = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.kg1));

        if (!kg.isChecked()) {

            kg.setChecked(java.lang.Boolean.TRUE.equals(true));

        } else {

            kg.setChecked(java.lang.Boolean.TRUE.equals(false));

        }

    }

    private boolean onXdbj4LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb2)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb7)).getText().toString();
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

                    open.cn.awg.pro.core.NativeFiles.writeText(RemoteControlActivity.this, (String) (set), (String) ("true"));

                } else {

                    e1.tsk("提示", "存在必要权限未授权");

                    android.widget.CompoundButton kg1 = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.kg1));
                    kg1.setChecked(java.lang.Boolean.TRUE.equals(false));

                }

            } else {

                open.cn.awg.pro.core.NativeFiles.writeText(RemoteControlActivity.this, (String) (set), (String) ("false"));
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        android.widget.CompoundButton kg = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.kg1));
                        kg.setChecked(java.lang.Boolean.TRUE.equals(false));
                        open.cn.awg.pro.core.NativeActions.launch(RemoteControlActivity.this, (Class<?>) (TypheyeAccountActivity.class));
                        e1.tsk("提示", "该操作需要登录Typheye账户");

                    }

                });

            }

        } else {

            open.cn.awg.pro.core.NativeFiles.writeText(RemoteControlActivity.this, (String) (set), (String) ("false"));

        }

    }

    private void onXdbj5Click(View vw) {
        e1.tsk("扩展身份", ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb9)).getText().toString());

    }

    private boolean onXdbj5LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb8)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb9)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj2Click(View vw) {
        jzpd();

    }

    private boolean onXdbj2LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb3)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb4)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj3Click(View vw) {
        e1.tsk("功能帮助", "此服务由官方提供");

    }

    private boolean onXdbj3LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb5)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb6)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx2Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(RemoteControlActivity.this);

    }

    private void onXdbj6Click(View vw) {
        carton.gets(lei);

    }

    private boolean onXdbj6LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb10)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb11)).getText().toString();
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

                open.cn.awg.pro.core.NativeActions.finish(RemoteControlActivity.this);
                e1.upload_error(e, "j3.run()");

            }

        } else {

        }

    }

    private boolean onXdbj10LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb20)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb21)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj8Click(View vw) {
        simpleSetting(lei);

    }

    private boolean onXdbj8LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb16)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb17)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj7Click(View vw) {
        simpleSetting(lei);

    }

    private boolean onXdbj7LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb12)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb13)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj9Click(View vw) {
        simpleSetting(lei);

    }

    private boolean onXdbj9LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb18)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(RemoteControlActivity.this.getWindow().getDecorView(), R.id.wb19)).getText().toString();
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

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);
        i2202ede8ff.setOnLongClickListener(xdbj4LongClickListener);

        SwitchCompat i1bca89f073 = (SwitchCompat) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.kg1);
        i1bca89f073.setOnCheckedChangeListener(kg1CheckedChangeListener);

        RelativeLayout c0d3e00d2b = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj5);
        c0d3e00d2b.setOnClickListener(xdbj5ClickListener);
        c0d3e00d2b.setOnLongClickListener(xdbj5LongClickListener);

        RelativeLayout i3167b45174 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);
        i3167b45174.setOnLongClickListener(xdbj2LongClickListener);

        RelativeLayout i20c2777fab = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);
        i20c2777fab.setOnLongClickListener(xdbj3LongClickListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        RelativeLayout i171cedf1eb = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj6);
        i171cedf1eb.setOnClickListener(xdbj6ClickListener);
        i171cedf1eb.setOnLongClickListener(xdbj6LongClickListener);

        RelativeLayout i5b34eede7f = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj10);
        i5b34eede7f.setOnClickListener(xdbj10ClickListener);
        i5b34eede7f.setOnLongClickListener(xdbj10LongClickListener);

        RelativeLayout fab146d7a5 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj8);
        fab146d7a5.setOnClickListener(xdbj8ClickListener);
        fab146d7a5.setOnLongClickListener(xdbj8LongClickListener);

        RelativeLayout e585d94488 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj7);
        e585d94488.setOnClickListener(xdbj7ClickListener);
        e585d94488.setOnLongClickListener(xdbj7LongClickListener);

        RelativeLayout i5a93676674 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj9);
        i5a93676674.setOnClickListener(xdbj9ClickListener);
        i5a93676674.setOnLongClickListener(xdbj9LongClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
