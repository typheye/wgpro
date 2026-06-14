/**
 ******************************************************************************
 * @file    TextViewerActivity.java
 * @author  Typheye
 * @brief   Text file viewer screen.
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
package open.cn.awg.pro.viewer;


import open.cn.awg.pro.core.IappCompat;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.core.GlobalRuntimeState;
import open.cn.awg.pro.R;
import open.cn.awg.pro.settings.TextViewerSettingsActivity;
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;

public class TextViewerActivity extends BaseAwgActivity {

    public final TextViewerActivity lei = this, 类 = this;
    public String qjurl = "";
    public String code = "";
    private final View.OnLongClickListener wb1LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onWb1LongClick(vw);
        }

    };
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnClickListener tx4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx4Click(vw);
        }

    };
    private final View.OnClickListener tx3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx3Click(vw);
        }

    };
    private final View.OnClickListener xxbj2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj2Click(vw);
        }

    };
    private final View.OnClickListener xxbj1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj1Click(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.text_viewer);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        try {

            String url = sj.hqtz("url");

            String bt = sj.hqtz("title");

            String bts = "";

            if (!zf.dy(bt, null) && !zf.dy(bt, "")) {

                bts = bt;

            } else {

                String d1_rootpaths;
                d1_rootpaths = GlobalRuntimeState.d1_rootpath;

                String[] fhValue = e1.fileinfo(d1_rootpaths, url);
                bts = fhValue[3];

            }

            if (wj.cz(url)) {

                qjurl = url;

                if (wj.dx(url) > 102400) {

                    e1.tsk("提示", "文本长度过大,无法进行浏览");
                    gj.gb();

                } else {

                    String t = wj.dqwb(url);
                    st.wb(R.id.text).zf(t);

                }

            } else {

                e1.tsk("提示", "文件不存在");
                gj.gb();

            }

        } catch (Throwable e) {

            e1.upload_error(e, "d2.*");
            gj.gb();

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        i.runlibrary.app.xt$pm pm = (i.runlibrary.app.xt$pm) (Object) xt.pm();

        if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4s.inf")), "false")) {

            if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4.inf")), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            st.xdbj(R.id.text_viewer_root).shxtck(true);

            String clr = zf.zf(R.color.colorTab);
            setStatusBarColor(clr);

        } else {

            st.xdbj(R.id.text_viewer_root).shxtck(false);
        applyFullscreenWindow();

        }

        if (zf.dy(a, "1")) {

            i.runlibrary.app.v.wb te = st.wb(R.id.text);
            te.wbjsb(10);
            te.wbjxb(200);
            te.wbjzb(50);
            te.wbjyb(50);

            i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "2")) {

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (wj.cz(a001) || zf.dy(wj.dqwb(a001), "true")) {

                i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
                wtab.nbj(0, "3dp", 0, "3dp");
                wtab.dqfs("center");

            } else {

                i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
                wtab.nbj(0, "9dp", 0, "9dp");
                wtab.dqfs("center");

            }

            i.runlibrary.app.v.wb te = st.wb(R.id.text);
            te.wbjsb(10);
            te.wbjxb(200);
            te.wbjzb(40);
            te.wbjyb(40);

        } else if (zf.dy(a, "3")) {

            i.runlibrary.app.v.wb te = st.wb(R.id.text);
            te.wbjsb(10);
            te.wbjxb(200);
            te.wbjzb(50);
            te.wbjyb(50);

            i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "4")) {

            i.runlibrary.app.v.wb te = st.wb(R.id.text);
            te.wbjsb(10);
            te.wbjxb(200);
            te.wbjzb(40);
            te.wbjyb(40);

        }
        gxh();

        String set = AppPaths.appPath("settings/set3.inf");

        if (zf.dy(wj.dqwb(set), "true")) {

            String te = wj.dqwb(AppPaths.appPath("data/assets/a5"));

            String[] name = new String[]{"title", "text"};

            String[] value = new String[]{"新手教程", te};
            gj.tz(TextQrDisplayActivity.class, name, value);

        }

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
            applyWindowModeFromSettings();

    }

    public void onRestart() {
        super.onRestart();
            applyWindowModeFromSettings();
        gxh();

    }

    public void onStart() {
        super.onStart();
            applyWindowModeFromSettings();
        gxh();

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
        gxh();

    }

    public void gxh() {
        code = sj.hqtz("code");

        String set2 = AppPaths.appPath("settings/d2/set2.inf");

        int iss = Integer.parseInt(wj.dqwb(set2));

        i.runlibrary.app.v.wb text = st.wb(R.id.text);
        text.ztdx(iss);

        String set3 = AppPaths.appPath("settings/d2/set3.inf");

        String set4 = AppPaths.appPath("settings/d2/set4.inf");

        final String color1 = wj.dqwb(set3);

        final String color2 = wj.dqwb(set4);
        text.zfys(color1);
        text.szzt("@resource/a7e484743f6796f092cd859819cb5fc8");
        st.xdbj(R.id.xdbj1).bj(color2);

        int psd = 0;

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = wj.dqwb(a2);

        if (zf.dy(a, "1") || zf.dy(a, "3")) {

            psd = 2 * IappCompat.zh(sj.zh()).pxzdp(50);

        } else {

            psd = 2 * IappCompat.zh(sj.zh()).pxzdp(40);

        }

        String set5 = AppPaths.appPath("settings/d2/set5.inf");

        if (zf.dy(wj.dqwb(set5), "true") || zf.dy(code, "super1")) {

            i.runlibrary.app.xt$pm pm = (i.runlibrary.app.xt$pm) (Object) xt.pm();

            int i = IappCompat.zh(sj.zh()).pxzdp(pm.k) - psd;
            text.k(i + "dp");

        } else {

            text.k(-1);

        }

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

    private void onTitleBarClick(View vw) {

        if (zf.dy(st.wb(R.id.title_bar).zf(), "< 文本浏览")) {

            gj.gb();

        }

    }

    private void onXxbj1Click(View vw) {

        if (st.xxbj(R.id.xxbj1).kjd() == 0) {

            st.wb(R.id.title_bar).zf("< 文本浏览");
            st.xxbj(R.id.xxbj1).kjd(8);
            st.wb(R.id.wb1).kjd(0);

        } else {

            st.wb(R.id.title_bar).zf("菜单");
            st.xxbj(R.id.xxbj1).kjd(0);
            st.wb(R.id.wb1).kjd(8);

        }

    }

    private void onXxbj2Click(View vw) {

        int i;

    }

    private void onTx3Click(View vw) {

        String[] name = new String[]{"url"};

        String[] value = new String[]{qjurl};
        gj.tz(TextEditorActivity.class, name, value);

        if (st.xxbj(R.id.xxbj1).kjd() == 0) {

            st.wb(R.id.title_bar).zf("< 文本浏览");
            st.xxbj(R.id.xxbj1).kjd(8);
            st.wb(R.id.wb1).kjd(0);

        } else {

            st.wb(R.id.title_bar).zf("菜单");
            st.xxbj(R.id.xxbj1).kjd(0);
            st.wb(R.id.wb1).kjd(8);

        }
        gj.gb();

    }

    private void onTx4Click(View vw) {

        if (zf.dy(code, "super1")) {

            String[] a = new String[]{"code"};

            String[] b = new String[]{"super1"};
            gj.tz(TextViewerSettingsActivity.class, a, b);

        } else {

            gj.tz(TextViewerSettingsActivity.class);

        }

        if (st.xxbj(R.id.xxbj1).kjd() == 0) {

            st.wb(R.id.title_bar).zf("< 文本浏览");
            st.xxbj(R.id.xxbj1).kjd(8);
            st.wb(R.id.wb1).kjd(0);

        } else {

            st.wb(R.id.title_bar).zf("菜单");
            st.xxbj(R.id.xxbj1).kjd(0);
            st.wb(R.id.wb1).kjd(8);

        }

    }

    private void onTx1Click(View vw) {

        if (st.xxbj(R.id.xxbj1).kjd() == 0) {

            st.wb(R.id.title_bar).zf("< 文本浏览");
            st.xxbj(R.id.xxbj1).kjd(8);
            st.wb(R.id.wb1).kjd(0);

        } else {

            st.wb(R.id.title_bar).zf("菜单");
            st.xxbj(R.id.xxbj1).kjd(0);
            st.wb(R.id.wb1).kjd(8);

        }
        gj.gb();

    }

    private void onTx2Click(View vw) {

        if (st.xxbj(R.id.xxbj1).kjd() == 0) {

            st.wb(R.id.title_bar).zf("< 文本浏览");
            st.xxbj(R.id.xxbj1).kjd(8);
            st.wb(R.id.wb1).kjd(0);

        } else {

            st.wb(R.id.title_bar).zf("菜单");
            st.xxbj(R.id.xxbj1).kjd(0);
            st.wb(R.id.wb1).kjd(8);

        }

    }

    private boolean onWb1LongClick(View vw) {

        if (st.xxbj(R.id.xxbj1).kjd() == 0) {

            st.wb(R.id.title_bar).zf("< 文本浏览");
            st.xxbj(R.id.xxbj1).kjd(8);
            st.wb(R.id.wb1).kjd(0);

        } else {

            st.wb(R.id.title_bar).zf("菜单");
            st.xxbj(R.id.xxbj1).kjd(0);
            st.wb(R.id.wb1).kjd(8);

        }
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        LinearLayout e1433e26d6 = (LinearLayout) findViewById(ay, vw, R.id.xxbj1);
        e1433e26d6.setOnClickListener(xxbj1ClickListener);

        LinearLayout dc0a1b897b = (LinearLayout) findViewById(ay, vw, R.id.xxbj2);
        dc0a1b897b.setOnClickListener(xxbj2ClickListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i11aeec1890 = (ImageView) findViewById(ay, vw, R.id.tx4);
        i11aeec1890.setOnClickListener(tx4ClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        TextView ff56a8e7f6 = (TextView) findViewById(ay, vw, R.id.wb1);
        ff56a8e7f6.setOnLongClickListener(wb1LongClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
