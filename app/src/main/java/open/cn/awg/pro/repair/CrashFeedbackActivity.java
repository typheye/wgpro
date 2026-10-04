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
    public WearChatRuntimeBridge e10;
    public AppRuntimeBridge e1;
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
        e10 = new WearChatRuntimeBridge(this);
        e1 = new AppRuntimeBridge(this);
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

        final android.widget.LinearLayout x1 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(CrashFeedbackActivity.this.getWindow().getDecorView(), R.id.xxbj1));

        final android.widget.LinearLayout x2 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(CrashFeedbackActivity.this.getWindow().getDecorView(), R.id.xxbj6));
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                if (i == 0) {

                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                } else if (i == 1) {

                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                }

            }

        });

    }

    public void upload() {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                show(1);

                String urls = "";

                final String set2 = AppPaths.appPath("settings/f11/set2.inf");
                urls = e1.urlUnlockString(open.cn.awg.pro.core.NativeFiles.readText(CrashFeedbackActivity.this, (String) (set2)));

                if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                String ljf = open.cn.awg.pro.core.NativeFiles.readText(CrashFeedbackActivity.this, (String) (AppPaths.appPath("data/assets/a17")));

                String idget = urls + "class/api.php?type=release" + ljf + "id=404";

                if (java.util.Objects.equals(log, "")) {

                    e1.tsk("提示", "异常日志不能为空");
                    show(0);

                } else {

                    String xxk = "[异常定位]\n" + location + "\n[异常日志]\n" + log;

                    final String xx = xxk;

                    final String url = idget;

                    String setback = "";

                    String[] wlhq1 = new String[]{"name=异常上报", "value=" + xx, "code=baseTextUpload"};

                    String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (wlhq1), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                    if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                        setback = "0";

                    } else {

                        if (java.util.Objects.equals(setback, "0")) {

                            setback = "0";

                        } else {

                            setback = back;

                        }

                    }

                    if (java.util.Objects.equals(setback, "0")) {

                        e1.tsk("提示", "反馈失败");
                        show(0);

                    } else {

                        if (java.util.Objects.equals(setback, "true")) {

                            e1.tsk("提示", "反馈成功");
                            open.cn.awg.pro.core.NativeActions.finish(CrashFeedbackActivity.this);

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
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                log = CrashFeedbackActivity.this.getIntent().getStringExtra(String.valueOf("log"));
                location = CrashFeedbackActivity.this.getIntent().getStringExtra(String.valueOf("location"));

                if (!java.util.Objects.equals(log, null) && !java.util.Objects.equals(location, null)) {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(CrashFeedbackActivity.this.getWindow().getDecorView(), R.id.wb1)).setText(String.valueOf("[异常定位]\n" + location + "\n[异常日志]\n" + log));

                } else {

                    e1.tsk("提示", "日志为空,无需反馈");
                    open.cn.awg.pro.core.NativeActions.finish(CrashFeedbackActivity.this);

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(CrashFeedbackActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {
        return true;

    }

    private void onTx2Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(CrashFeedbackActivity.this);

    }

    private void onTx3Click(View vw) {
        upload();

    }

    private void onAn1Click(View vw) {
        upload();

    }

    private void onAn2Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(CrashFeedbackActivity.this);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        Button i171ea34f1a = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        Button i0b66a667b1 = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an2);
        i0b66a667b1.setOnClickListener(an2ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
