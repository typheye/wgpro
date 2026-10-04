/**
 ******************************************************************************
 * @file    AccessibilityScriptActivity.java
 * @author  Typheye
 * @brief   Runs accessibility service scripts and automation commands.
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
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.service.SystemAccessibilityService;
import open.cn.awg.pro.viewer.TextViewerActivity;

public class AccessibilityScriptActivity extends BaseAwgActivity {

    public final AccessibilityScriptActivity lei = this, 类 = this;
    public String lin;
    public String ujback;
    public AppRuntimeBridge e1;
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };
    private final View.OnClickListener an2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn2Click(vw);
        }

    };
    private final View.OnClickListener an1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn1Click(vw);
        }

    };
    private final View.OnTouchListener bjk2TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjk2Touch(vw, me);
        }

    };
    private final View.OnTouchListener bjk1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjk1Touch(vw, me);
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
        setContentView(R.layout.accessibility_script);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        String cmds = AccessibilityScriptActivity.this.getIntent().getStringExtra(String.valueOf("cmd"));
        cmds = open.cn.awg.pro.core.TextValues.trim(cmds);

        if (!java.util.Objects.equals(cmds, null) && !java.util.Objects.equals(cmds, "")) {

            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AccessibilityScriptActivity.this.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(cmds));
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AccessibilityScriptActivity.this.getWindow().getDecorView(), R.id.wb1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AccessibilityScriptActivity.this.getWindow().getDecorView(), R.id.bjk1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AccessibilityScriptActivity.this.getWindow().getDecorView(), R.id.wb1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AccessibilityScriptActivity.this.getWindow().getDecorView(), R.id.bjk1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

        }
        SystemAccessibilityService.pns = "com.iapp.app";

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.accessibility_script_root, R.id.xdbj1, R.id.title_bar);

    }

    public void load(Object url, Object title) {

        String[] name = new String[]{"url", "title", "code"};

        Object[] value = new Object[]{url, title, "super1"};
        open.cn.awg.pro.core.NativeActions.launch(AccessibilityScriptActivity.this, (Class<?>) (TextViewerActivity.class), (String[]) (name), (Object[]) (value));

    }

    public void run() {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                final android.widget.TextView backinfo = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AccessibilityScriptActivity.this.getWindow().getDecorView(), R.id.backs));
                lin = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AccessibilityScriptActivity.this.getWindow().getDecorView(), R.id.bjk1)).getText().toString();

                String ii = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AccessibilityScriptActivity.this.getWindow().getDecorView(), R.id.bjk2)).getText().toString();
                lin = open.cn.awg.pro.core.TextValues.trim(lin);
                ii = open.cn.awg.pro.core.TextValues.trim(ii);

                if (java.util.Objects.equals(lin, "")) {

                    e1.tsk("提示", "脚本命令不能为空");

                } else {

                    if (!java.util.Objects.equals(ii, "")) {

                        boolean aa;

                        try {

                            aa = SystemAccessibilityService.isAccessibilitySettingsOn(lei, SystemAccessibilityService.class.getName());

                        } catch (Throwable e) {

                            aa = false;

                        }

                        if (!aa) {

                            e1.tsk("提示", "未授权应用无障碍权限\n请授权后重新运行本脚本");

                            try {

                                Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                                lei.startActivity(intent);

                            } catch (Throwable e) {

                                open.cn.awg.pro.core.NativeActions.finish(AccessibilityScriptActivity.this);
                                e1.upload_error(e, "j3.run()");

                            }

                        } else {

                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AccessibilityScriptActivity.this.getWindow().getDecorView(), R.id.wb1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AccessibilityScriptActivity.this.getWindow().getDecorView(), R.id.wb2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                                    ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AccessibilityScriptActivity.this.getWindow().getDecorView(), R.id.bjk1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                                    ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AccessibilityScriptActivity.this.getWindow().getDecorView(), R.id.bjk2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                                    ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(AccessibilityScriptActivity.this.getWindow().getDecorView(), R.id.an1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                                    ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(AccessibilityScriptActivity.this.getWindow().getDecorView(), R.id.an2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                                }

                            });

                            try {

                                int a = 0;

                                int i = Integer.parseInt(ii);

                                while (a < i) {

                                    SystemAccessibilityService.addCmd(lin);
                                    a = a + 1;

                                }

                            } catch (Throwable e) {

                                open.cn.awg.pro.core.NativeActions.finish(AccessibilityScriptActivity.this);
                                e1.upload_error(e, "j3.run()");

                            }

                        }

                    } else {

                        e1.tsk("提示", "执行次数不能为空");

                    }

                }

            }

        });

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(AccessibilityScriptActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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
        open.cn.awg.pro.core.NativeActions.finish(AccessibilityScriptActivity.this);

    }

    private boolean onBjk1Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(AccessibilityScriptActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private boolean onBjk2Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(AccessibilityScriptActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private void onAn1Click(View vw) {
        run();

    }

    private void onAn2Click(View vw) {
        load(AppPaths.appPath("data/assets/a14"), "功能帮助");

    }

    private void onTx2Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(AccessibilityScriptActivity.this);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        EditText i32ee139ba1 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        EditText i63f06183a1 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjk2);
        i63f06183a1.setOnTouchListener(bjk2TouchListener);

        Button i171ea34f1a = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        Button i0b66a667b1 = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an2);
        i0b66a667b1.setOnClickListener(an2ClickListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
