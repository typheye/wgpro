/**
 ******************************************************************************
 * @file    l3.java
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

public class l3 extends iActivity {

    public final l3 lei = this, 类 = this;
    public open.cn.awg.pro.e1 e1 = new e1(_APPINFO);
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i0b66a667b1 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i0b66a667b1(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_ff56a8e7f6 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_ff56a8e7f6(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i171ea34f1a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i171ea34f1a(vw);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i32ee139ba1 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i32ee139ba1(vw, me);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d26d5d7080(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.l3);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.e6.getInstance().addActivity(lei);

    }

    public void __layoutIsLoaded(android.app.Activity ay, android.view.View vw) {

        if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f8/set4.inf"), "true")) {

        }

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

        final int u = Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));

        if (zf.dy(a, "4")) {

            st.xdbj(open.cn.awg.pro.R.id.l3_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.l3_v).shxtck(false);

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

        if (zf.dy(a, "1")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u, 0, u, 50);

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
            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0, 0, 0, 0);

        } else if (zf.dy(a, "3")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "4")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0, 0, 0, 0);

        }

        i.runlibrary.app.v.xlcd xlcd1 = st.xlcd(open.cn.awg.pro.R.id.xlcd1);

        java.lang.String[] sz = new java.lang.String[]{"自动检测", "中文翻译英语", "中文翻译日语", "中文翻译韩语", "中文翻译法语", "中文翻译俄语", "中文翻译西班牙语", "英语翻译中文", "日语翻译中文", "韩语翻译中文", "法语翻译中文", "俄语翻译中文", "西班牙语翻译中文"};
        xlcd1.csh(sz);

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

    }

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.e6.getInstance().removeActivity(lei);

    }

    private void $_onClick_d26d5d7080(android.view.View vw) {
        gj.gb();

    }

    private boolean $_onTouch_i32ee139ba1(android.view.View vw, android.view.MotionEvent me) {

        if (!vw.isFocused()) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(open.cn.awg.pro.R.id.qtgd1);
                    qtgd1.v.requestFocus();

                }

            });

        }
        return false;

    }

    private void $_onClick_i171ea34f1a(android.view.View vw) {

        i.runlibrary.app.v.xlcd xlcd1 = st.xlcd(open.cn.awg.pro.R.id.xlcd1);

        java.lang.String bjk1 = st.bjk(open.cn.awg.pro.R.id.bjk1).zf();
        bjk1 = zf.qctwkg(bjk1);

        if (zf.dy(bjk1, "")) {

            e1.tsk("提示", "翻译内容不能为空");

        } else {

            java.lang.Object xl = xlcd1.dqxzxm();

            java.lang.String yy = "";

            if (zf.dy(xl, "自动检测")) {

                yy = "AUTO";

            } else if (zf.dy(xl, "中文翻译英语")) {

                yy = "ZH_CN2EN";

            } else if (zf.dy(xl, "中文翻译日语")) {

                yy = "ZH_CN2JA";

            } else if (zf.dy(xl, "中文翻译韩语")) {

                yy = "ZH_CN2KR";

            } else if (zf.dy(xl, "中文翻译法语")) {

                yy = "ZH_CNFR";

            } else if (zf.dy(xl, "中文翻译俄语")) {

                yy = "ZH_CN2RU";

            } else if (zf.dy(xl, "中文翻译西班牙语")) {

                yy = "ZH_CN2SP";

            } else if (zf.dy(xl, "英语翻译中文")) {

                yy = "EN2ZH_CN";

            } else if (zf.dy(xl, "日语翻译中文")) {

                yy = "JA2ZH_CN";

            } else if (zf.dy(xl, "韩语翻译中文")) {

                yy = "KR2ZH_CN";

            } else if (zf.dy(xl, "法语翻译中文")) {

                yy = "FR2ZH_CN";

            } else if (zf.dy(xl, "俄语翻译中文")) {

                yy = "RU2ZH_CN";

            } else if (zf.dy(xl, "西班牙语翻译中文")) {

                yy = "SP2ZH_CN";

            }

            final java.lang.String[] post = new java.lang.String[]{"inputtext=" + bjk1, "type=" + yy};
            gj.xc(new java.lang.Thread() {

                public void run() {

                    java.lang.String url = "http://m.youdao.com/translate";

                    java.lang.String ok = wl.hq(url, post, "utf-8", null, true, "User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept=*/*||accept-language=zh-CN", 20000, 20000, null);

                    if (zf.dy(ok, null)) {

                        e1.tsk("提示", "翻译失败,请检查网络");

                    } else {

                        java.lang.String o = zf.qc(ok, "译文", "</ul>");

                        final java.lang.String oo = zf.qc(o, "<li>", "</li>");
                        gj.jmxc(new java.lang.Runnable() {

                            public void run() {

                                st.wb(open.cn.awg.pro.R.id.wb1).zf("翻译结果:\n" + oo);

                            }

                        });

                    }

                }

            });

        }

    }

    private boolean $_onLongClick_ff56a8e7f6(android.view.View vw) {

        i.runlibrary.app.v.wb wb = st.wb(open.cn.awg.pro.R.id.wb1);

        java.lang.String wb1 = wb.zf();
        wb1 = zf.qc(wb1, "翻译结果:\n", null);
        e1.tsk("详细信息", wb1);
        return true;

    }

    private void $_onClick_i0b66a667b1(android.view.View vw) {
        st.wb(open.cn.awg.pro.R.id.wb1).zf("翻译结果:\n");
        st.bjk(open.cn.awg.pro.R.id.bjk1).zf("");

    }

    private void $_onClick_i34dc7bf20a(android.view.View vw) {
        gj.gb();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
        d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

        android.widget.EditText i32ee139ba1 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjk1);
        i32ee139ba1.setOnTouchListener($_on_setOnTouchListener_i32ee139ba1);

        android.widget.Button i171ea34f1a = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an1);
        i171ea34f1a.setOnClickListener($_on_setOnClickListener_i171ea34f1a);

        android.widget.TextView ff56a8e7f6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb1);
        ff56a8e7f6.setOnLongClickListener($_on_setOnLongClickListener_ff56a8e7f6);

        android.widget.Button i0b66a667b1 = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an2);
        i0b66a667b1.setOnClickListener($_on_setOnClickListener_i0b66a667b1);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        __layoutIsLoaded(ay, vw);
    }
}
