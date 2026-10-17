/**
 ******************************************************************************
 * @file    AppDebugActivity.java
 * @author  Typheye
 * @brief   Application debug plugin screen.
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
package open.cn.awg.pro.experimental;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import java.io.File;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.music.TuneFreeMusicBridge;
import open.cn.awg.pro.R;
import open.cn.awg.pro.tools.SystemPermissionActivity;

public class AppDebugActivity extends BaseAwgActivity {

    public final AppDebugActivity lei = this, 类 = this;
    public String um = "";
    public File f;
    public AppRuntimeBridge e1;
    public TuneFreeMusicBridge e12;
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
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
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        e1 = new AppRuntimeBridge(this);
        e12 = new TuneFreeMusicBridge(this);
        setContentView(R.layout.app_debug);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        csh();
        show(0);

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.app_debug_root, R.id.xdbj1, R.id.title_bar);

    }

    public void show(int i) {

        final android.widget.TextView tab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.title_bar));

        final android.widget.LinearLayout x1 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.xxbj1));

        final android.widget.LinearLayout x2 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.xxbj6));

        final android.widget.LinearLayout x3 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.xxbj2));

        if (i == 0) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    tab.setText(String.valueOf("App Debug"));
                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                }

            });

        } else if (i == 1) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    tab.setText(String.valueOf("程序测试"));
                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        } else if (i == 2) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    tab.setText(String.valueOf("App Debug"));
                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        }

    }

    public void csh() {

    }

    public void jzpd() {

        String set1 = AppPaths.appPath("settings/set1.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(AppDebugActivity.this, (String) (set1)), "true")) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.wb7)).setText(String.valueOf("已开启实验性功能"));

                }

            });

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.wb7)).setText(String.valueOf("已关闭实验性功能"));

                }

            });

        }
        show(1);

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

            if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "App Debug")) {

                open.cn.awg.pro.core.NativeActions.finish(AppDebugActivity.this);

            } else if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "程序测试")) {

                show(0);

            }

        }
        return false;

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onTitleBarClick(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "App Debug")) {

            open.cn.awg.pro.core.NativeActions.finish(AppDebugActivity.this);

        } else if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "程序测试")) {

            show(0);

        }

    }

    private void onXdbj2Click(View vw) {
        jzpd();

    }

    private boolean onXdbj2LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.wb3)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.wb4)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj3Click(View vw) {
        e1.tsk("功能帮助", "App Debug有助于调试应用程序,帮助您解决问题.");

    }

    private boolean onXdbj3LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.wb5)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.wb6)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx2Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(AppDebugActivity.this);

    }

    private void onXdbj4Click(View vw) {

        String set1 = AppPaths.appPath("settings/set1.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(AppDebugActivity.this, (String) (set1)), "false")) {

            open.cn.awg.pro.core.NativeFiles.writeText(AppDebugActivity.this, (String) (set1), (String) ("true"));
            e1.tsk("提示", "已开启实验性功能");

        } else {

            open.cn.awg.pro.core.NativeFiles.writeText(AppDebugActivity.this, (String) (set1), (String) ("false"));
            e1.tsk("提示", "已关闭实验性功能");

        }
        jzpd();

    }

    private boolean onXdbj4LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.wb2)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.wb7)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj8Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(AppDebugActivity.this, (Class<?>) (SystemPermissionActivity.class));

    }

    private boolean onXdbj8LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.wb14)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.wb15)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj7Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(AppDebugActivity.this, (Class<?>) (DeveloperTestActivity.class));

    }

    private boolean onXdbj7LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.wb12)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppDebugActivity.this.getWindow().getDecorView(), R.id.wb13)).getText().toString();
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

        RelativeLayout i3167b45174 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);
        i3167b45174.setOnLongClickListener(xdbj2LongClickListener);

        RelativeLayout i20c2777fab = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);
        i20c2777fab.setOnLongClickListener(xdbj3LongClickListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);
        i2202ede8ff.setOnLongClickListener(xdbj4LongClickListener);

        RelativeLayout fab146d7a5 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj8);
        fab146d7a5.setOnClickListener(xdbj8ClickListener);
        fab146d7a5.setOnLongClickListener(xdbj8LongClickListener);

        TextView adf370a235 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb13);

        RelativeLayout e585d94488 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj7);
        e585d94488.setOnClickListener(xdbj7ClickListener);
        e585d94488.setOnLongClickListener(xdbj7LongClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
