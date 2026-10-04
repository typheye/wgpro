/**
 ******************************************************************************
 * @file    TerminalCompatibilityActivity.java
 * @author  Typheye
 * @brief   Terminal compatibility screen.
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
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
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

public class TerminalCompatibilityActivity extends BaseAwgActivity {

    public final TerminalCompatibilityActivity lei = this, 类 = this;
    public AppRuntimeBridge e1;
    public String p = "";
    public String q = "";
    public String x = "";
    private final View.OnClickListener tx3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx3Click(vw);
        }

    };
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnClickListener an4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn4Click(vw);
        }

    };
    private final View.OnClickListener an3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn3Click(vw);
        }

    };
    private final View.OnClickListener an2sClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn2sClick(vw);
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
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        e1 = new AppRuntimeBridge(this);
        setContentView(R.layout.terminal_compatibility);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        String a1 = AppPaths.appPath("settings/a3.inf");

        String a001 = AppPaths.appPath("settings/dpi.inf");
        p = open.cn.awg.pro.core.NativeFiles.readText(TerminalCompatibilityActivity.this, (String) (a1));

        if (open.cn.awg.pro.core.NativeFiles.exists(TerminalCompatibilityActivity.this, (String) (a001)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(TerminalCompatibilityActivity.this, (String) (a001)), "true")) {

            q = "true";

        }

        if (java.util.Objects.equals(p, "1")) {

            ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(TerminalCompatibilityActivity.this.getWindow().getDecorView(), R.id.an1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

        } else if (java.util.Objects.equals(p, "2") && java.util.Objects.equals(q, "")) {

            ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(TerminalCompatibilityActivity.this.getWindow().getDecorView(), R.id.an2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

        } else if (java.util.Objects.equals(p, "2") && java.util.Objects.equals(q, "true")) {

            ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(TerminalCompatibilityActivity.this.getWindow().getDecorView(), R.id.an2s)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

        } else if (java.util.Objects.equals(p, "3")) {

            ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(TerminalCompatibilityActivity.this.getWindow().getDecorView(), R.id.an3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

        } else if (java.util.Objects.equals(p, "4")) {

            ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(TerminalCompatibilityActivity.this.getWindow().getDecorView(), R.id.an4)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.terminal_compatibility_root, R.id.xdbj1, R.id.title_bar);

    }

    public void show(int i_) {

        final int i = i_;

        final android.widget.LinearLayout xxbj1 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TerminalCompatibilityActivity.this.getWindow().getDecorView(), R.id.xxbj1));

        final android.widget.LinearLayout xxbj2 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TerminalCompatibilityActivity.this.getWindow().getDecorView(), R.id.xxbj2));

        final android.widget.LinearLayout xxbj5 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TerminalCompatibilityActivity.this.getWindow().getDecorView(), R.id.xxbj5));

        final android.widget.TextView tab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TerminalCompatibilityActivity.this.getWindow().getDecorView(), R.id.title_bar));
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                if (i == 0) {

                    tab.setText(String.valueOf("< 适配终端"));
                    xxbj1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    xxbj2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    xxbj5.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                } else if (i == 1) {

                    tab.setText(String.valueOf("适配终端"));
                    xxbj1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    xxbj2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    xxbj5.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                } else if (i == 2) {

                    tab.setText(String.valueOf("适配终端"));
                    xxbj1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    xxbj2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    xxbj5.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(TerminalCompatibilityActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

            if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TerminalCompatibilityActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "< 适配终端")) {

                open.cn.awg.pro.core.NativeActions.finish(TerminalCompatibilityActivity.this);

            }

        }
        return false;

    }

    private void onTitleBarClick(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TerminalCompatibilityActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "< 适配终端")) {

            open.cn.awg.pro.core.NativeActions.finish(TerminalCompatibilityActivity.this);

        }

    }

    private void onAn1Click(View vw) {
        p = "1";
        show(1);

    }

    private void onAn2Click(View vw) {
        p = "2";
        show(1);

    }

    private void onAn2sClick(View vw) {
        p = "2";
        q = "true";
        show(1);

    }

    private void onAn3Click(View vw) {
        p = "3";
        show(1);

    }

    private void onAn4Click(View vw) {
        p = "4";
        show(1);

    }

    private void onTx1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(TerminalCompatibilityActivity.this);

    }

    private void onTx2Click(View vw) {
        show(0);

    }

    private void onTx3Click(View vw) {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                show(2);

                String a1 = AppPaths.appPath("settings/a3.inf");

                String a001 = AppPaths.appPath("settings/dpi.inf");

                if (java.util.Objects.equals(p, "1")) {

                    open.cn.awg.pro.core.NativeFiles.writeText(TerminalCompatibilityActivity.this, (String) (a1), (String) ("1"));
                    open.cn.awg.pro.core.NativeFiles.delete(TerminalCompatibilityActivity.this, (String) (a001), false);

                } else if (java.util.Objects.equals(p, "2") && java.util.Objects.equals(q, "")) {

                    open.cn.awg.pro.core.NativeFiles.writeText(TerminalCompatibilityActivity.this, (String) (a1), (String) ("2"));
                    open.cn.awg.pro.core.NativeFiles.delete(TerminalCompatibilityActivity.this, (String) (a001), false);

                } else if (java.util.Objects.equals(p, "2") && java.util.Objects.equals(q, "true")) {

                    open.cn.awg.pro.core.NativeFiles.writeText(TerminalCompatibilityActivity.this, (String) (a1), (String) ("2"));
                    open.cn.awg.pro.core.NativeFiles.writeText(TerminalCompatibilityActivity.this, (String) (a001), (String) ("true"));

                } else if (java.util.Objects.equals(p, "3")) {

                    open.cn.awg.pro.core.NativeFiles.writeText(TerminalCompatibilityActivity.this, (String) (a1), (String) ("3"));
                    open.cn.awg.pro.core.NativeFiles.delete(TerminalCompatibilityActivity.this, (String) (a001), false);

                } else if (java.util.Objects.equals(p, "4")) {

                    open.cn.awg.pro.core.NativeFiles.writeText(TerminalCompatibilityActivity.this, (String) (a1), (String) ("4"));
                    open.cn.awg.pro.core.NativeFiles.delete(TerminalCompatibilityActivity.this, (String) (a001), false);

                }
                open.cn.awg.pro.core.NativeActions.sleep(2000);
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        Intent intent = lei.getPackageManager().getLaunchIntentForPackage(lei.getPackageName());
                        if (intent != null) {
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            lei.startActivity(intent);
                        }
                        AwgProApplication.getInstance().exit();

                    }

                });

            }

        });

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        Button i171ea34f1a = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        Button i0b66a667b1 = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an2);
        i0b66a667b1.setOnClickListener(an2ClickListener);

        Button i0afd0fa55f = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an2s);
        i0afd0fa55f.setOnClickListener(an2sClickListener);

        Button i8f51a35692 = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an3);
        i8f51a35692.setOnClickListener(an3ClickListener);

        Button i74ce6319f3 = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an4);
        i74ce6319f3.setOnClickListener(an4ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
