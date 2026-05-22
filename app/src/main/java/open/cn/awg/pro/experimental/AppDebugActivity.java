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
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public TuneFreeMusicBridge e12 = new TuneFreeMusicBridge(_APPINFO);
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

        final i.runlibrary.app.v.wb tab = st.wb(R.id.title_bar);

        final i.runlibrary.app.v.xxbj x1 = st.xxbj(R.id.xxbj1);

        final i.runlibrary.app.v.xxbj x2 = st.xxbj(R.id.xxbj6);

        final i.runlibrary.app.v.xxbj x3 = st.xxbj(R.id.xxbj2);

        if (i == 0) {

            gj.jmxc(new Runnable() {

                public void run() {

                    tab.zf("< App Debug");
                    x1.kjd(8);
                    x2.kjd(8);
                    x3.kjd(0);

                }

            });

        } else if (i == 1) {

            gj.jmxc(new Runnable() {

                public void run() {

                    tab.zf("< 程序测试");
                    x1.kjd(0);
                    x2.kjd(8);
                    x3.kjd(8);

                }

            });

        } else if (i == 2) {

            gj.jmxc(new Runnable() {

                public void run() {

                    tab.zf("< App Debug");
                    x1.kjd(8);
                    x2.kjd(0);
                    x3.kjd(8);

                }

            });

        }

    }

    public void csh() {

    }

    public void jzpd() {

        String set1 = AppPaths.appPath("settings/set1.inf");

        if (zf.dy(wj.dqwb(set1), "true")) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.wb7).zf("已开启实验性功能");

                }

            });

        } else {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.wb7).zf("已关闭实验性功能");

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

            if (zf.dy(st.wb(R.id.title_bar).zf(), "< App Debug")) {

                gj.gb();

            } else if (zf.dy(st.wb(R.id.title_bar).zf(), "< 程序测试")) {

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

        if (zf.dy(st.wb(R.id.title_bar).zf(), "< App Debug")) {

            gj.gb();

        } else if (zf.dy(st.wb(R.id.title_bar).zf(), "< 程序测试")) {

            show(0);

        }

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
        e1.tsk("功能帮助", "App Debug有助于调试应用程序,帮助您解决问题.");

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

    private void onXdbj4Click(View vw) {

        String set1 = AppPaths.appPath("settings/set1.inf");

        if (zf.dy(wj.dqwb(set1), "false")) {

            wj.xrwb(set1, "true");
            e1.tsk("提示", "已开启实验性功能");

        } else {

            wj.xrwb(set1, "false");
            e1.tsk("提示", "已关闭实验性功能");

        }
        jzpd();

    }

    private boolean onXdbj4LongClick(View vw) {

        String te = st.wb(R.id.wb2).zf();

        String lj = st.wb(R.id.wb7).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj8Click(View vw) {
        gj.tz(SystemPermissionActivity.class);

    }

    private boolean onXdbj8LongClick(View vw) {

        String te = st.wb(R.id.wb14).zf();

        String lj = st.wb(R.id.wb15).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj7Click(View vw) {
        gj.tz(DeveloperTestActivity.class);

    }

    private boolean onXdbj7LongClick(View vw) {

        String te = st.wb(R.id.wb12).zf();

        String lj = st.wb(R.id.wb13).zf();
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

        RelativeLayout i3167b45174 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);
        i3167b45174.setOnLongClickListener(xdbj2LongClickListener);

        RelativeLayout i20c2777fab = (RelativeLayout) findViewById(ay, vw, R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);
        i20c2777fab.setOnLongClickListener(xdbj3LongClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) findViewById(ay, vw, R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);
        i2202ede8ff.setOnLongClickListener(xdbj4LongClickListener);

        RelativeLayout fab146d7a5 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj8);
        fab146d7a5.setOnClickListener(xdbj8ClickListener);
        fab146d7a5.setOnLongClickListener(xdbj8LongClickListener);

        TextView adf370a235 = (TextView) findViewById(ay, vw, R.id.wb13);

        RelativeLayout e585d94488 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj7);
        e585d94488.setOnClickListener(xdbj7ClickListener);
        e585d94488.setOnLongClickListener(xdbj7LongClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
