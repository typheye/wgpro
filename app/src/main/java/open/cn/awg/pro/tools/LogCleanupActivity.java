/**
 ******************************************************************************
 * @file    LogCleanupActivity.java
 * @author  Typheye
 * @brief   Scans and deletes generated log files.
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

import java.io.File;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

@SuppressLint("StaticFieldLeak")
public class LogCleanupActivity extends BaseAwgActivity {

    public static android.widget.TextView cmd;
    public static android.widget.TextView jzt;
    public final LogCleanupActivity lei = this, 类 = this;
    private final View.OnTouchListener xdbj1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXdbj1Touch(vw, me);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.log_cleanup);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        csh();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(LogCleanupActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(LogCleanupActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

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

        final androidx.core.widget.NestedScrollView qtgd = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(LogCleanupActivity.this.getWindow().getDecorView(), R.id.qtgd1));
        cmd = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(LogCleanupActivity.this.getWindow().getDecorView(), R.id.wb1));
        jzt = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(LogCleanupActivity.this.getWindow().getDecorView(), R.id.wb2));
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        cmd.setText(String.valueOf("开始扫描垃圾文件...\n"));
                        jzt.setText(String.valueOf("扫描中"));

                    }

                });

                String[] gl = new String[]{"*.log"};

                File[] lb = open.cn.awg.pro.core.NativeFiles.search(LogCleanupActivity.this, "%", gl);

                for (File c : lb) {

                    open.cn.awg.pro.core.NativeActions.sleep(5);

                    final String f = c.getAbsolutePath();
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            cmd.append("发现日志文件:" + f);
                            cmd.append("\n");
                            qtgd.fullScroll(View.FOCUS_DOWN);

                        }

                    });

                }
                open.cn.awg.pro.core.NativeActions.sleep(500);
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        cmd.append("扫描完成,开始清理");
                        cmd.append("\n");
                        qtgd.fullScroll(View.FOCUS_DOWN);
                        jzt.setText(String.valueOf("清理中"));

                    }

                });
                open.cn.awg.pro.core.NativeActions.sleep(500);

                for (File c : lb) {

                    open.cn.awg.pro.core.NativeActions.sleep(5);

                    final String f = c.getAbsolutePath();
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            cmd.append("清理日志文件:" + f);
                            open.cn.awg.pro.core.NativeFiles.delete(LogCleanupActivity.this, (String) (f), false);
                            cmd.append("\n");
                            qtgd.fullScroll(View.FOCUS_DOWN);

                        }

                    });

                }
                open.cn.awg.pro.core.NativeActions.sleep(500);
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        cmd.append("清理完成,结束任务");
                        cmd.append("\n");
                        qtgd.fullScroll(View.FOCUS_DOWN);
                        jzt.setText(String.valueOf("清理完成"));

                    }

                });
                open.cn.awg.pro.core.NativeActions.sleep(500);
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        open.cn.awg.pro.core.NativeActions.finish(LogCleanupActivity.this);

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
