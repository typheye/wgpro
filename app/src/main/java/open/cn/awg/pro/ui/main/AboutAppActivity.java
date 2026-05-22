/**
 ******************************************************************************
 * @file    AboutAppActivity.java
 * @author  Typheye
 * @brief   Shows app information, update, activation, backup, and reset entries.
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
package open.cn.awg.pro.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import com.kingqi.zwcj.活动;
import com.kingqi.zwcj.控制台;
import com.kingqi.zwcj.日期工具;
import com.kingqi.zwcj.应用工具;

import open.cn.awg.pro.account.AppActivationActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.repair.ResetAppActivity;
import open.cn.awg.pro.tools.TerminalCompatibilityActivity;
import open.cn.awg.pro.update.AppUpdateActivity;

public class AboutAppActivity extends BaseAwgActivity {

    public final AboutAppActivity lei = this, 类 = this;
    public int versionCode;
    public String versionName;
    public int xhsl = 0;
    public int eggsdj = 0;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
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
    private final View.OnLongClickListener xdbj9LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj9LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj9ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj9Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj5LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj5LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj5ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj5Click(vw);
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
    private final View.OnLongClickListener tx1LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onTx1LongClick(vw);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.about_app);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        int b;
        b = com.demo.e4.getVersionCode(lei);

        int c = b;
        st.wb(R.id.wb3).zf(c);
        yz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.about_app_root, R.id.xdbj1, R.id.title_bar);

    }

    public void eggs() {
        eggsdj = 0;

        String ab = AppPaths.appPath("settings/candebug");
        wj.xrwb(ab, "true");

        活动 a1 = 控制台.当前活动;

        String a2 = 应用工具.获取应用签名(lei, "open.cn.awg.pro");

        String a3 = 日期工具.格式化日期时间(应用工具.获取应用第一次安装日期(lei, "open.cn.awg.pro"));

        String a4 = 日期工具.格式化日期时间(应用工具.获取应用更新日期(lei, "open.cn.awg.pro"));

        String a5 = 应用工具.获取应用的安装市场(lei, "open.cn.awg.pro");

        String a6 = 应用工具.获取应用版本名称(lei, "open.cn.awg.pro");

        int a7 = 应用工具.获取应用版本号(lei, "open.cn.awg.pro");

        String a8 = 应用工具.获取应用名称(lei, "open.cn.awg.pro");

        String sho = "[应用名称]\n" + a8 + "\n\n[编译版本]\n" + a6 + "\n\n[版本号码]\n" + a7 + "\n\n[安装日期]\n" + a3 + "\n\n[更新日期]\n" + a4;
        e1.tsk("应用程序", sho);

    }

    public void yz() {

    }

    public void bshow(boolean a) {

        final boolean i = a;
        gj.jmxc(new Runnable() {

            public void run() {

                st.wb(R.id.wb5).kjd(0);

                if (i) {

                    String color = zf.zf(R.color.colorTextTrue);
                    st.wb(R.id.wb5).zf("官方应用");
                    st.wb(R.id.wb5).bj(color);

                } else {

                    String color = zf.zf(R.color.colorTextFalse);
                    st.wb(R.id.wb5).zf("未知版本");
                    st.wb(R.id.wb5).bj(color);

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

    private void onTitleBarClick(View vw) {
        gj.gb();

    }

    private void onTx1Click(View vw) {
        eggsdj = eggsdj + 1;

        if (eggsdj == 2) {

            eggs();

        }

    }

    private boolean onTx1LongClick(View vw) {
        return true;

    }

    private void onXdbj4Click(View vw) {

        String t1 = wj.dqwb(AppPaths.appPath("data/assets/a2"));
        e1.tsk("应用信息", t1);

    }

    private boolean onXdbj4LongClick(View vw) {

        String te = st.wb(R.id.wb16).zf();

        String lj = st.wb(R.id.wb17).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj3Click(View vw) {
        gj.tz(AppActivationActivity.class);

    }

    private boolean onXdbj3LongClick(View vw) {

        String te = st.wb(R.id.wb12).zf();

        String lj = st.wb(R.id.wb15).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj2Click(View vw) {
        gj.tz(AppUpdateActivity.class);

    }

    private boolean onXdbj2LongClick(View vw) {

        String te = st.wb(R.id.wb4).zf();

        String lj = st.wb(R.id.wb11).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj5Click(View vw) {
        e1.goplu("20006", "应用备份", false);

    }

    private boolean onXdbj5LongClick(View vw) {

        String te = st.wb(R.id.wb1).zf();

        String lj = st.wb(R.id.wb6).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj9Click(View vw) {
        gj.tz(ResetAppActivity.class);

    }

    private boolean onXdbj9LongClick(View vw) {

        String te = st.wb(R.id.wb21).zf();

        String lj = st.wb(R.id.wb22).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj8Click(View vw) {
        gj.tz(TerminalCompatibilityActivity.class);

    }

    private boolean onXdbj8LongClick(View vw) {

        String te = st.wb(R.id.wb19).zf();

        String lj = st.wb(R.id.wb20).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj7Click(View vw) {
        e1.tsk_QR("加入Q群", "群号：372091854\n\n扫描二维码 快一步入群", "https://qm.qq.com/cgi-bin/qm/qr?k=bU0uOl8kFexph1zLQQOJ74lAEM5C6xfU&authKey=FRi9nwu30PuztHBbVmQ7vbmiia5nd43C0yE2fvAKrP9GLEtltWfxeDMN01mO9RXl&noverify=0&personal_qrcode_source=0");

    }

    private boolean onXdbj7LongClick(View vw) {

        String te = st.wb(R.id.wb9).zf();

        String lj = st.wb(R.id.wb10).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx2Click(View vw) {
        gj.gb();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);
        i34dc7bf20a.setOnLongClickListener(tx1LongClickListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) findViewById(ay, vw, R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);
        i2202ede8ff.setOnLongClickListener(xdbj4LongClickListener);

        RelativeLayout i20c2777fab = (RelativeLayout) findViewById(ay, vw, R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);
        i20c2777fab.setOnLongClickListener(xdbj3LongClickListener);

        RelativeLayout i3167b45174 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);
        i3167b45174.setOnLongClickListener(xdbj2LongClickListener);

        RelativeLayout c0d3e00d2b = (RelativeLayout) findViewById(ay, vw, R.id.xdbj5);
        c0d3e00d2b.setOnClickListener(xdbj5ClickListener);
        c0d3e00d2b.setOnLongClickListener(xdbj5LongClickListener);

        RelativeLayout i5a93676674 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj9);
        i5a93676674.setOnClickListener(xdbj9ClickListener);
        i5a93676674.setOnLongClickListener(xdbj9LongClickListener);

        RelativeLayout fab146d7a5 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj8);
        fab146d7a5.setOnClickListener(xdbj8ClickListener);
        fab146d7a5.setOnLongClickListener(xdbj8LongClickListener);

        RelativeLayout e585d94488 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj7);
        e585d94488.setOnClickListener(xdbj7ClickListener);
        e585d94488.setOnLongClickListener(xdbj7LongClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
