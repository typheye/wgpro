/**
 ******************************************************************************
 * @file    SystemToolsActivity.java
 * @author  Typheye
 * @brief   Collects system utilities such as cleanup and system settings.
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
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class SystemToolsActivity extends BaseAwgActivity {

    public final SystemToolsActivity lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    private final View.OnClickListener tx5ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx5Click(vw);
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
    private final View.OnClickListener xxbj2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj2Click(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.system_tools);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

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
        applyFullscreenWindow();

        if (zf.dy(a, "1")) {

            st.xdbj(R.id.xdbj1).nbj(u, 0, u, 50);

        } else if (zf.dy(a, "2")) {

            st.xdbj(R.id.xdbj1).nbj(0, 0, 0, 0);

        } else if (zf.dy(a, "3")) {

            st.xdbj(R.id.xdbj1).nbj(u, 0, u, 50);

        } else if (zf.dy(a, "4")) {

            int uih = sj.zh().pxzdp(pm.ztl);
            uih = uih + 40;

            i.runlibrary.app.v.kp kp1 = st.kp(R.id.kp1);
            kp1.wbj(0, uih + "dp", 0, "5dp");

        }

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
        applyFullscreenWindow();

    }

    public void onRestart() {
        super.onRestart();
        applyFullscreenWindow();

    }

    public void onStart() {
        super.onStart();
        applyFullscreenWindow();

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
        applyFullscreenWindow();

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

        }
        return false;

    }

    private void onXxbj2Click(View vw) {

        int i;

    }

    private void onXdbj4Click(View vw) {
        gj.tz(LogCleanupActivity.class);

    }

    private boolean onXdbj4LongClick(View vw) {

        String te = st.wb(R.id.wb5).zf();

        String lj = st.wb(R.id.wb7).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj3Click(View vw) {

        try {

            try {

                gj.jmxc(new Runnable() {

                    public void run() {

                        xt.dkyy("com.android.settings", "com.android.settings.Settings");

                    }

                });

            } catch (Throwable e2) {

                try {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            xt.dkyy("com.google.android.apps.wearable.settings", "com.google.android.clockwork.settings.MainSettingsActivity");

                        }

                    });

                } catch (Throwable e3) {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            xt.dkyy("com.google.android.apps.wearable.settings");

                        }

                    });

                }

            }

        } catch (Throwable e) {

            e1.tsk("提示", "没有可执行该操作的应用");

        }

    }

    private boolean onXdbj3LongClick(View vw) {

        String te = st.wb(R.id.wb2).zf();

        String lj = st.wb(R.id.wb4).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx5Click(View vw) {
        gj.gb();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        LinearLayout dc0a1b897b = (LinearLayout) findViewById(ay, vw, R.id.xxbj2);
        dc0a1b897b.setOnClickListener(xxbj2ClickListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) findViewById(ay, vw, R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);
        i2202ede8ff.setOnLongClickListener(xdbj4LongClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);

        RelativeLayout i20c2777fab = (RelativeLayout) findViewById(ay, vw, R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);
        i20c2777fab.setOnLongClickListener(xdbj3LongClickListener);

        ImageView i4a4248a0f2 = (ImageView) findViewById(ay, vw, R.id.tx5);
        i4a4248a0f2.setOnClickListener(tx5ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
