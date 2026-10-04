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


import android.annotation.SuppressLint;
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

@SuppressLint("StaticFieldLeak")
public class NetworkOptimizeActivity extends BaseAwgActivity {

    public static android.widget.TextView cmd;
    public static android.widget.TextView jzt;
    public final NetworkOptimizeActivity lei = this, 类 = this;
    public AppRuntimeBridge e1;
    private final View.OnTouchListener xdbj1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXdbj1Touch(vw, me);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        e1 = new AppRuntimeBridge(this);
        setContentView(R.layout.network_optimize);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        csh();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(NetworkOptimizeActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(NetworkOptimizeActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

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

        final androidx.core.widget.NestedScrollView qtgd = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(NetworkOptimizeActivity.this.getWindow().getDecorView(), R.id.qtgd1));
        cmd = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(NetworkOptimizeActivity.this.getWindow().getDecorView(), R.id.wb1));
        jzt = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(NetworkOptimizeActivity.this.getWindow().getDecorView(), R.id.wb2));
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        cmd.setText(String.valueOf("正在准备中...\n"));
                        jzt.setText(String.valueOf("准备中"));

                    }

                });
                open.cn.awg.pro.core.NativeActions.sleep(500);
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        cmd.append("准备完成,开始测试网络请求");
                        cmd.append("\n");
                        qtgd.fullScroll(View.FOCUS_DOWN);
                        jzt.setText(String.valueOf("测试中"));

                    }

                });

                String testurl = AppPaths.appPath("settings/f10/set2.inf");
                testurl = open.cn.awg.pro.core.NativeFiles.readText(NetworkOptimizeActivity.this, (String) (testurl));
                testurl = e1.urlUnlockString(testurl);

                final String st5 = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(testurl), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        cmd.append("请求发送成功,返回结果:\n" + st5);
                        cmd.append("\n");
                        qtgd.fullScroll(View.FOCUS_DOWN);

                    }

                });
                open.cn.awg.pro.core.NativeActions.sleep(500);
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        cmd.append("测试完成,开始优化");
                        cmd.append("\n");
                        qtgd.fullScroll(View.FOCUS_DOWN);
                        jzt.setText(String.valueOf("优化中"));

                    }

                });
                open.cn.awg.pro.core.NativeActions.sleep(500);

                int b = 0;

                while (b < 100) {

                    open.cn.awg.pro.core.NativeActions.sleep(50);
                    b = b + 1;

                    final int c = b;
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            cmd.append("优化进度:" + c + "%");
                            cmd.append("\n");
                            qtgd.fullScroll(View.FOCUS_DOWN);

                        }

                    });

                }
                open.cn.awg.pro.core.NativeActions.sleep(500);
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        cmd.append("优化完成,结束任务");
                        cmd.append("\n");
                        qtgd.fullScroll(View.FOCUS_DOWN);
                        jzt.setText(String.valueOf("优化完成"));

                    }

                });
                open.cn.awg.pro.core.NativeActions.sleep(500);
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        open.cn.awg.pro.core.NativeActions.finish(NetworkOptimizeActivity.this);

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

        RelativeLayout aaeebbd136 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj1);
        aaeebbd136.setOnTouchListener(xdbj1TouchListener);

        __layoutIsLoaded(ay, vw);
    }
}
