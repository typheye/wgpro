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

import android.view.View;
import android.view.Window;

import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.GlobalRuntimeState;
import open.cn.awg.pro.settings.TextViewerSettingsActivity;
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;


public class TextViewerActivity extends iActivity {

    public final TextViewerActivity lei = this, 类 = this;
    public java.lang.String qjurl = "";
    public java.lang.String code = "";
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_ff56a8e7f6 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_ff56a8e7f6(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i4d951cdb0e = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i4d951cdb0e(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i11aeec1890 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i11aeec1890(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i8217d42a17 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i8217d42a17(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_dc0a1b897b = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_dc0a1b897b(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_e1433e26d6 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_e1433e26d6(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d26d5d7080(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.d2);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.app.AwgProApplication.getInstance().addActivity(lei);

        open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        try {

            java.lang.String url = sj.hqtz("url");

            java.lang.String bt = sj.hqtz("title");

            java.lang.String bts = "";

            if (!zf.dy(bt, null) && !zf.dy(bt, "")) {

                bts = bt;

            } else {

                java.lang.String d1_rootpaths;
                d1_rootpaths = open.cn.awg.pro.core.GlobalRuntimeState.d1_rootpath;

                java.lang.String[] fhValue = e1.fileinfo(d1_rootpaths, url);
                bts = fhValue[3];

            }

            if (wj.cz(url)) {

                qjurl = url;

                if (wj.dx(url) > 102400) {

                    e1.tsk("提示", "文本长度过大,无法进行浏览");
                    gj.gb();

                } else {

                    java.lang.String t = wj.dqwb(url);
                    st.wb(open.cn.awg.pro.R.id.text).zf(t);

                }

            } else {

                e1.tsk("提示", "文件不存在");
                gj.gb();

            }

        } catch (java.lang.Throwable e) {

            e1.upload_error(e, "d2.*");
            gj.gb();

        }

    }

    public void __layoutIsLoaded(android.app.Activity ay, android.view.View vw) {

        i.runlibrary.app.xt$pm pm = xt.pm();

        if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4s.inf"), "false")) {

            if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4.inf"), "true")) {

                getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            st.xdbj(open.cn.awg.pro.R.id.d2_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.d2_v).shxtck(false);

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

        if (zf.dy(a, "1")) {

            i.runlibrary.app.v.wb te = st.wb(open.cn.awg.pro.R.id.text);
            te.wbjsb(10);
            te.wbjxb(200);
            te.wbjzb(50);
            te.wbjyb(50);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "2")) {

            java.lang.String a001 = "/data/user/0/open.cn.awg.pro/settings/dpi.inf";

            if (wj.cz(a001) || zf.dy(wj.dqwb(a001), "true")) {

                i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
                wtab.nbj(0, "3dp", 0, "3dp");
                wtab.dqfs("center");

            } else {

                i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
                wtab.nbj(0, "9dp", 0, "9dp");
                wtab.dqfs("center");

            }

            i.runlibrary.app.v.wb te = st.wb(open.cn.awg.pro.R.id.text);
            te.wbjsb(10);
            te.wbjxb(200);
            te.wbjzb(40);
            te.wbjyb(40);

        } else if (zf.dy(a, "3")) {

            i.runlibrary.app.v.wb te = st.wb(open.cn.awg.pro.R.id.text);
            te.wbjsb(10);
            te.wbjxb(200);
            te.wbjzb(50);
            te.wbjyb(50);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "4")) {

            i.runlibrary.app.v.wb te = st.wb(open.cn.awg.pro.R.id.text);
            te.wbjsb(10);
            te.wbjxb(200);
            te.wbjzb(40);
            te.wbjyb(40);

        }
        gxh();

        java.lang.String set = "/data/user/0/open.cn.awg.pro/settings/set3.inf";

        if (zf.dy(wj.dqwb(set), "true")) {

            java.lang.String te = wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a5");

            java.lang.String[] name = new java.lang.String[]{"title", "text"};

            java.lang.String[] value = new java.lang.String[]{"新手教程", te};
            gj.tz(TextQrDisplayActivity.class, name, value);

        }

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

    }

    public void onRestart() {
        super.onRestart();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }
        gxh();

    }

    public void onStart() {
        super.onStart();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }
        gxh();

        i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(open.cn.awg.pro.R.id.qtgd1);

        qtgd1.v.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View vw, android.view.MotionEvent me) {
                if (me.getAction() == android.view.MotionEvent.ACTION_SCROLL && me.isFromSource(androidx.core.view.InputDeviceCompat.SOURCE_ROTARY_ENCODER)) {

                    float delta = -me.getAxisValue(androidx.core.view.MotionEventCompat.AXIS_SCROLL) *
                            androidx.core.view.ViewConfigurationCompat.getScaledVerticalScrollFactor(
                                    android.view.ViewConfiguration.get(lei), lei
                            );

                    vw.scrollBy(0, java.lang.Math.round(delta));
                    return true;
                }
                return false;
            }
        });

        qtgd1.v.requestFocus();

    }

    public void onResume() {
        super.onResume();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }
        gxh();

    }

    public void gxh() {
        code = sj.hqtz("code");

        java.lang.String set2 = "/data/user/0/open.cn.awg.pro/settings/d2/set2.inf";

        int iss = Integer.parseInt(wj.dqwb(set2));

        i.runlibrary.app.v.wb text = st.wb(open.cn.awg.pro.R.id.text);
        text.ztdx(iss);

        java.lang.String set3 = "/data/user/0/open.cn.awg.pro/settings/d2/set3.inf";

        java.lang.String set4 = "/data/user/0/open.cn.awg.pro/settings/d2/set4.inf";

        final java.lang.String color1 = wj.dqwb(set3);

        final java.lang.String color2 = wj.dqwb(set4);
        text.zfys(color1);
        text.szzt("@resource/a7e484743f6796f092cd859819cb5fc8");
        st.xdbj(open.cn.awg.pro.R.id.xdbj1).bj(color2);

        int psd = 0;

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "1") || zf.dy(a, "3")) {

            psd = 2 * sj.zh().pxzdp(50);

        } else {

            psd = 2 * sj.zh().pxzdp(40);

        }

        java.lang.String set5 = "/data/user/0/open.cn.awg.pro/settings/d2/set5.inf";

        if (zf.dy(wj.dqwb(set5), "true") || zf.dy(code, "super1")) {

            i.runlibrary.app.xt$pm pm = xt.pm();

            int i = sj.zh().pxzdp(pm.k) - psd;
            text.k(i + "dp");

        } else {

            text.k(-1);

        }

    }

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.app.AwgProApplication.getInstance().removeActivity(lei);

    }

    public boolean onKeyDown(int kc, android.view.KeyEvent ke) {

        if (kc == 4) {

        }
        return false;

    }

    private void $_onClick_d26d5d7080(android.view.View vw) {

        if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< 文本浏览")) {

            gj.gb();

        }

    }

    private void $_onClick_e1433e26d6(android.view.View vw) {

        if (st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd() == 0) {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("< 文本浏览");
            st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
            st.wb(open.cn.awg.pro.R.id.wb1).kjd(0);

        } else {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("菜单");
            st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);
            st.wb(open.cn.awg.pro.R.id.wb1).kjd(8);

        }

    }

    private void $_onClick_dc0a1b897b(android.view.View vw) {

        int i;

    }

    private void $_onClick_i8217d42a17(android.view.View vw) {

        java.lang.String[] name = new java.lang.String[]{"url"};

        java.lang.String[] value = new java.lang.String[]{qjurl};
        gj.tz(TextEditorActivity.class, name, value);

        if (st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd() == 0) {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("< 文本浏览");
            st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
            st.wb(open.cn.awg.pro.R.id.wb1).kjd(0);

        } else {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("菜单");
            st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);
            st.wb(open.cn.awg.pro.R.id.wb1).kjd(8);

        }
        gj.gb();

    }

    private void $_onClick_i11aeec1890(android.view.View vw) {

        if (zf.dy(code, "super1")) {

            java.lang.String[] a = new java.lang.String[]{"code"};

            java.lang.String[] b = new java.lang.String[]{"super1"};
            gj.tz(TextViewerSettingsActivity.class, a, b);

        } else {

            gj.tz(TextViewerSettingsActivity.class);

        }

        if (st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd() == 0) {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("< 文本浏览");
            st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
            st.wb(open.cn.awg.pro.R.id.wb1).kjd(0);

        } else {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("菜单");
            st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);
            st.wb(open.cn.awg.pro.R.id.wb1).kjd(8);

        }

    }

    private void $_onClick_i34dc7bf20a(android.view.View vw) {

        if (st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd() == 0) {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("< 文本浏览");
            st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
            st.wb(open.cn.awg.pro.R.id.wb1).kjd(0);

        } else {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("菜单");
            st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);
            st.wb(open.cn.awg.pro.R.id.wb1).kjd(8);

        }
        gj.gb();

    }

    private void $_onClick_i4d951cdb0e(android.view.View vw) {

        if (st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd() == 0) {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("< 文本浏览");
            st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
            st.wb(open.cn.awg.pro.R.id.wb1).kjd(0);

        } else {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("菜单");
            st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);
            st.wb(open.cn.awg.pro.R.id.wb1).kjd(8);

        }

    }

    private boolean $_onLongClick_ff56a8e7f6(android.view.View vw) {

        if (st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd() == 0) {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("< 文本浏览");
            st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
            st.wb(open.cn.awg.pro.R.id.wb1).kjd(0);

        } else {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("菜单");
            st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);
            st.wb(open.cn.awg.pro.R.id.wb1).kjd(8);

        }
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
        d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

        android.widget.LinearLayout e1433e26d6 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj1);
        e1433e26d6.setOnClickListener($_on_setOnClickListener_e1433e26d6);

        android.widget.LinearLayout dc0a1b897b = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj2);
        dc0a1b897b.setOnClickListener($_on_setOnClickListener_dc0a1b897b);

        android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);
        i8217d42a17.setOnClickListener($_on_setOnClickListener_i8217d42a17);

        android.widget.ImageView i11aeec1890 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx4);
        i11aeec1890.setOnClickListener($_on_setOnClickListener_i11aeec1890);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
        i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

        android.widget.TextView ff56a8e7f6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb1);
        ff56a8e7f6.setOnLongClickListener($_on_setOnLongClickListener_ff56a8e7f6);

        __layoutIsLoaded(ay, vw);
    }
}
