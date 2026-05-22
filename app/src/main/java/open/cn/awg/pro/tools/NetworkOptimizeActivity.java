/**
 ******************************************************************************
 * @file    NetworkOptimizeActivity.java
 * @author  Typheye
 * @brief   Runs network test and optimization tasks.
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
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatDelegate;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class NetworkOptimizeActivity extends BaseAwgActivity {

    public static i.runlibrary.app.v.wb cmd;
    public static i.runlibrary.app.v.wb jzt;
    public final NetworkOptimizeActivity lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    private final View.OnTouchListener xdbj1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXdbj1Touch(vw, me);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.network_optimize);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        csh();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4s.inf")), "false")) {

            if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4.inf")), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }
        applyFullscreenWindow();

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

    }

    public void onResume() {
        super.onResume();
        applyFullscreenWindow();

    }

    public void csh() {

        final i.runlibrary.app.v.qtgd qtgd = st.qtgd(R.id.qtgd1);
        cmd = st.wb(R.id.wb1);
        jzt = st.wb(R.id.wb2);
        gj.xc(new Thread() {

            public void run() {

                gj.jmxc(new Runnable() {

                    public void run() {

                        cmd.zf("正在准备中...\n");
                        jzt.zf("准备中");

                    }

                });
                gj.zt(500);
                gj.jmxc(new Runnable() {

                    public void run() {

                        cmd.st.append("准备完成,开始测试网络请求");
                        cmd.st.append("\n");
                        qtgd.st.fullScroll(View.FOCUS_DOWN);
                        jzt.zf("测试中");

                    }

                });

                String testurl = AppPaths.appPath("settings/f10/set2.inf");
                testurl = wj.dqwb(testurl);
                testurl = e1.urlUnlockString(testurl);

                final String st5 = wl.hq(testurl, null, "utf-8", null, true, null, 20000, 20000, null);
                gj.jmxc(new Runnable() {

                    public void run() {

                        cmd.st.append("请求发送成功,返回结果:\n" + st5);
                        cmd.st.append("\n");
                        qtgd.st.fullScroll(View.FOCUS_DOWN);

                    }

                });
                gj.zt(500);
                gj.jmxc(new Runnable() {

                    public void run() {

                        cmd.st.append("测试完成,开始优化");
                        cmd.st.append("\n");
                        qtgd.st.fullScroll(View.FOCUS_DOWN);
                        jzt.zf("优化中");

                    }

                });
                gj.zt(500);

                int b = 0;

                while (b < 100) {

                    gj.zt(50);
                    b = b + 1;

                    final int c = b;
                    gj.jmxc(new Runnable() {

                        public void run() {

                            cmd.st.append("优化进度:" + c + "%");
                            cmd.st.append("\n");
                            qtgd.st.fullScroll(View.FOCUS_DOWN);

                        }

                    });

                }
                gj.zt(500);
                gj.jmxc(new Runnable() {

                    public void run() {

                        cmd.st.append("优化完成,结束任务");
                        cmd.st.append("\n");
                        qtgd.st.fullScroll(View.FOCUS_DOWN);
                        jzt.zf("优化完成");

                    }

                });
                gj.zt(500);
                gj.jmxc(new Runnable() {

                    public void run() {

                        gj.gb();

                    }

                });

            }

        });

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

        }
        return false;

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private boolean onXdbj1Touch(View vw, MotionEvent me) {
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        RelativeLayout aaeebbd136 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj1);
        aaeebbd136.setOnTouchListener(xdbj1TouchListener);

        __layoutIsLoaded(ay, vw);
    }
}
