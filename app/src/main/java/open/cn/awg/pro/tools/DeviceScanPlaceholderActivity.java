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

public class DeviceScanPlaceholderActivity extends BaseAwgActivity {

    public static i.runlibrary.app.v.wb cmd;
    public static i.runlibrary.app.v.wb jzt;
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
        cmd = st.wb(R.id.wb1);
        jzt = st.wb(R.id.wb2);
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
        gj.xc(new Thread() {

            public void run() {

                gj.jmxc(new Runnable() {

                    public void run() {

                        cmd.zf("开始扫描周围设备...\n");
                        jzt.zf("扫描中");

                    }

                });
                gj.zt(500);
                gj.jmxc(new Runnable() {

                    public void run() {

                        cmd.st.append("扫描结束,开始安装");
                        cmd.st.append("\n");
                        qtgd.st.fullScroll(View.FOCUS_DOWN);
                        jzt.zf("安装中");

                    }

                });
                gj.zt(500);
                gj.jmxc(new Runnable() {

                    public void run() {

                        cmd.st.append("安装完成,结束任务");
                        cmd.st.append("\n");
                        qtgd.st.fullScroll(View.FOCUS_DOWN);
                        jzt.zf("安装完成");

                    }

                });
                gj.zt(500);
                gj.jmxc(new Runnable() {

                    public void run() {

                        gj.gb();

                        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
                        e1.tsk("提示", "该功能正在开发中...");

                    }

                });

            }

        });

    }

    public void print(Object text_) {

        final Object text = text_;
        gj.jmxc(new Runnable() {

            public void run() {

                cmd.st.append(((CharSequence) (text)));
                cmd.st.append("\n");

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
