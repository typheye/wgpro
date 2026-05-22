/**
 ******************************************************************************
 * @file    CrashFeedbackActivity.java
 * @author  Typheye
 * @brief   Uploads crash feedback logs and shows repair guidance.
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
package open.cn.awg.pro.repair;

import android.app.Activity;
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
import open.cn.awg.pro.chat.WearChatRuntimeBridge;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class CrashFeedbackActivity extends BaseAwgActivity {

    public final CrashFeedbackActivity lei = this, 类 = this;
    public WearChatRuntimeBridge e10 = new WearChatRuntimeBridge(_APPINFO);
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public String log = "";
    public String location = "";
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

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.crash_feedback);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        csh();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.crash_feedback_root, R.id.xdbj1, R.id.title_bar);

    }

    public void show(int a) {

        final int i = a;

        final i.runlibrary.app.v.xxbj x1 = st.xxbj(R.id.xxbj1);

        final i.runlibrary.app.v.xxbj x2 = st.xxbj(R.id.xxbj6);
        gj.jmxc(new Runnable() {

            public void run() {

                if (i == 0) {

                    x1.kjd(0);
                    x2.kjd(8);

                } else if (i == 1) {

                    x1.kjd(8);
                    x2.kjd(0);

                }

            }

        });

    }

    public void upload() {
        gj.xc(new Thread() {

            public void run() {

                show(1);

                String urls = "";

                final String set2 = AppPaths.appPath("settings/f11/set2.inf");
                urls = e1.urlUnlockString(wj.dqwb(set2));

                if (zf.cjw(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                String ljf = wj.dqwb(AppPaths.appPath("data/assets/a17"));

                String idget = urls + "class/api.php?type=release" + ljf + "id=404";

                if (zf.dy(log, "")) {

                    e1.tsk("提示", "异常日志不能为空");
                    show(0);

                } else {

                    String xxk = "[异常定位]\n" + location + "\n[异常日志]\n" + log;

                    final String xx = xxk;

                    final String url = idget;

                    String setback = "";

                    String[] wlhq1 = new String[]{"name=异常上报", "value=" + xx, "code=baseTextUpload"};

                    String back = wl.hq(url, wlhq1, "utf-8", null, true, null, 20000, 20000, null);

                    if (zf.dy(back, "") || zf.dy(back, null)) {

                        setback = "0";

                    } else {

                        if (zf.dy(setback, "0")) {

                            setback = "0";

                        } else {

                            setback = back;

                        }

                    }

                    if (zf.dy(setback, "0")) {

                        e1.tsk("提示", "反馈失败");
                        show(0);

                    } else {

                        if (zf.dy(setback, "true")) {

                            e1.tsk("提示", "反馈成功");
                            gj.gb();

                        } else {

                            e1.tsk("提示", "反馈失败\n" + setback);
                            show(0);

                        }

                    }

                }

            }

        });

    }

    public void csh() {
        show(0);
        gj.jmxc(new Runnable() {

            public void run() {

                log = sj.hqtz("log");
                location = sj.hqtz("location");

                if (!zf.dy(log, null) && !zf.dy(location, null)) {

                    st.wb(R.id.wb1).zf("[异常定位]\n" + location + "\n[异常日志]\n" + log);

                } else {

                    e1.tsk("提示", "日志为空,无需反馈");
                    gj.gb();

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

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {
        return true;

    }

    private void onTx2Click(View vw) {
        gj.gb();

    }

    private void onTx3Click(View vw) {
        upload();

    }

    private void onAn1Click(View vw) {
        upload();

    }

    private void onAn2Click(View vw) {
        gj.gb();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        Button i171ea34f1a = (Button) findViewById(ay, vw, R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        Button i0b66a667b1 = (Button) findViewById(ay, vw, R.id.an2);
        i0b66a667b1.setOnClickListener(an2ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
