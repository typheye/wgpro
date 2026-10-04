/**
 ******************************************************************************
 * @file    DeviceScanPlaceholderActivity.java
 * @author  Typheye
 * @brief   Placeholder screen for nearby or peripheral device scanning.
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
public class DeviceScanPlaceholderActivity extends BaseAwgActivity {

    public static android.widget.TextView cmd;
    public static android.widget.TextView jzt;
    public final DeviceScanPlaceholderActivity lei = this, 类 = this;
    private final View.OnTouchListener xdbj1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXdbj1Touch(vw, me);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.device_scan_placeholder);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        cmd = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeviceScanPlaceholderActivity.this.getWindow().getDecorView(), R.id.wb1));
        jzt = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeviceScanPlaceholderActivity.this.getWindow().getDecorView(), R.id.wb2));
        csh();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(DeviceScanPlaceholderActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(DeviceScanPlaceholderActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

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

        final androidx.core.widget.NestedScrollView qtgd = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(DeviceScanPlaceholderActivity.this.getWindow().getDecorView(), R.id.qtgd1));
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        cmd.setText(String.valueOf("开始扫描周围设备...\n"));
                        jzt.setText(String.valueOf("扫描中"));

                    }

                });
                open.cn.awg.pro.core.NativeActions.sleep(500);
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        cmd.append("扫描结束,开始安装");
                        cmd.append("\n");
                        qtgd.fullScroll(View.FOCUS_DOWN);
                        jzt.setText(String.valueOf("安装中"));

                    }

                });
                open.cn.awg.pro.core.NativeActions.sleep(500);
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        cmd.append("安装完成,结束任务");
                        cmd.append("\n");
                        qtgd.fullScroll(View.FOCUS_DOWN);
                        jzt.setText(String.valueOf("安装完成"));

                    }

                });
                open.cn.awg.pro.core.NativeActions.sleep(500);
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        open.cn.awg.pro.core.NativeActions.finish(DeviceScanPlaceholderActivity.this);

                        AppRuntimeBridge e1 = new AppRuntimeBridge(DeviceScanPlaceholderActivity.this);
                        e1.tsk("提示", "该功能正在开发中...");

                    }

                });

            }

        });

    }

    public void print(Object text_) {

        final Object text = text_;
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                cmd.append(((CharSequence) (text)));
                cmd.append("\n");

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
