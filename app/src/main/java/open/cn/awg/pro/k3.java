/**
 ******************************************************************************
 * @file    k3.java
 * @author  Typheye
 * @brief   AWG Pro module source.
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
package open.cn.awg.pro;

import android.view.View;
import android.view.Window;

import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;

public class k3 extends iActivity {

    public static i.runlibrary.app.v.wb cmd;
    public static i.runlibrary.app.v.wb jzt;
    public final k3 lei = this, 类 = this;
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_aaeebbd136 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_aaeebbd136(vw, me);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.k3);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.e6.getInstance().addActivity(lei);
        cmd = st.wb(open.cn.awg.pro.R.id.wb1);
        jzt = st.wb(open.cn.awg.pro.R.id.wb2);
        csh();

    }

    public void __layoutIsLoaded(android.app.Activity ay, android.view.View vw) {

        if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4s.inf"), "false")) {

            if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4.inf"), "true")) {

                getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public void onRestart() {
        super.onRestart();

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public void onStart() {
        super.onStart();

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public void onResume() {
        super.onResume();

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public void csh() {

        final i.runlibrary.app.v.qtgd qtgd = st.qtgd(open.cn.awg.pro.R.id.qtgd1);
        gj.xc(new java.lang.Thread() {

            public void run() {

                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        cmd.zf("开始扫描周围设备...\n");
                        jzt.zf("扫描中");

                    }

                });
                gj.zt(500);
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        cmd.st.append("扫描结束,开始安装");
                        cmd.st.append("\n");
                        qtgd.st.fullScroll(View.FOCUS_DOWN);
                        jzt.zf("安装中");

                    }

                });
                gj.zt(500);
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        cmd.st.append("安装完成,结束任务");
                        cmd.st.append("\n");
                        qtgd.st.fullScroll(View.FOCUS_DOWN);
                        jzt.zf("安装完成");

                    }

                });
                gj.zt(500);
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        gj.gb();

                        open.cn.awg.pro.e1 e1 = new e1(_APPINFO);
                        e1.tsk("提示", "该功能正在开发中...");

                    }

                });

            }

        });

    }

    public void print(java.lang.Object text_) {

        final java.lang.Object text = text_;
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                cmd.st.append(((java.lang.CharSequence) (text)));
                cmd.st.append("\n");

            }

        });

    }

    public boolean onKeyDown(int kc, android.view.KeyEvent ke) {

        if (kc == 4) {

        }
        return false;

    }

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.e6.getInstance().removeActivity(lei);

    }

    private boolean $_onTouch_aaeebbd136(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.RelativeLayout aaeebbd136 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj1);
        aaeebbd136.setOnTouchListener($_on_setOnTouchListener_aaeebbd136);

        __layoutIsLoaded(ay, vw);
    }
}
