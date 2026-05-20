/**
 ******************************************************************************
 * @file    l2.java
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

public class l2 extends iActivity {

    public final l2 lei = this, 类 = this;
    public open.cn.awg.pro.e1 e1 = new e1(_APPINFO);
    public java.io.File f;
    public java.lang.String ty = "";
    public java.lang.String appid = "";
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i171ea34f1a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i171ea34f1a(vw);
        }

    };
    private final android.widget.AdapterView.OnItemSelectedListener $_on_setOnItemSelectedListener_i004a739c93 = new android.widget.AdapterView.OnItemSelectedListener() {

        public void onItemSelected(android.widget.AdapterView vw, android.view.View view, int pn, long id) {
            $_onItemSelected_i004a739c93(vw, view, pn, id);
        }

        public void onNothingSelected(android.widget.AdapterView vw) {
            $_onNothingSelected_i004a739c93(vw);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i32ee139ba1 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i32ee139ba1(vw, me);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i74ce6319f3 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i74ce6319f3(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i8f51a35692 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i8f51a35692(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i0b66a667b1 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i0b66a667b1(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d26d5d7080(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.l2);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.e6.getInstance().addActivity(lei);

        java.lang.String shs = sj.hqtz("url");
        shs = zf.qctwkg(shs);
        ty = sj.hqtz("type");
        appid = sj.hqtz("appid");

        if (!zf.dy(shs, null) && !zf.dy(shs, "")) {

            st.bjk(open.cn.awg.pro.R.id.bjk1).zf(shs);
            st.bjk(open.cn.awg.pro.R.id.bjk1).kjd(8);

        } else {

            st.bjk(open.cn.awg.pro.R.id.bjk1).kjd(0);

        }

        if (zf.dy(ty, "app")) {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("< 应用下载");
            st.an(open.cn.awg.pro.R.id.an2).kjd(8);
            st.an(open.cn.awg.pro.R.id.an3).kjd(8);

            java.lang.String path = "/storage/emulated/0/Android/data/open.cn.awg.pro/files/app/download/" + appid + "/base.apk";

            if (wj.cz(path)) {

                st.an(open.cn.awg.pro.R.id.an4).kjd(0);

            }

        }

        i.runlibrary.app.v.xlcd xlcd1 = st.xlcd(open.cn.awg.pro.R.id.xlcd1);

        if (zf.dy(ty, "app")) {

            java.lang.String[] sz = new java.lang.String[]{"使用系统下载器", "使用软件下载器"};
            xlcd1.csh(sz);

        } else {

            java.lang.String[] sz = new java.lang.String[]{"使用系统下载器", "使用系统下载器[自定义]", "使用软件下载器"};
            xlcd1.csh(sz);

        }

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

            st.xdbj(open.cn.awg.pro.R.id.l2_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.l2_v).shxtck(false);

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

    private void $_onClick_i0b66a667b1(android.view.View vw) {

        java.lang.String ftitle = "/data/user/0/open.cn.awg.pro/cache/file/title";
        wj.xrwb(ftitle, "< 下载目录");

        java.lang.String path = "/storage/emulated/0/Android/data/open.cn.awg.pro/files/Download/";

        final java.lang.String[] name = new java.lang.String[]{"url", "root"};

        final java.lang.String[] value = new java.lang.String[]{path, path};
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                gj.tz(d1.class, name, value);

            }

        });

    }

    private void $_onClick_i8f51a35692(android.view.View vw) {

        java.lang.String ftitle = "/data/user/0/open.cn.awg.pro/cache/file/title";
        wj.xrwb(ftitle, "< 下载目录");

        java.lang.String path = "/storage/emulated/0/Download/";

        final java.lang.String[] name = new java.lang.String[]{"url", "root"};

        final java.lang.String[] value = new java.lang.String[]{path, path};
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                gj.tz(d1.class, name, value);

            }

        });

    }

    private void $_onClick_i74ce6319f3(android.view.View vw) {

        java.lang.String ftitle = "/data/user/0/open.cn.awg.pro/cache/file/title";
        wj.xrwb(ftitle, "< 下载目录");

        java.lang.String path = "/storage/emulated/0/Android/data/open.cn.awg.pro/files/app/download/" + appid + "/";
        wj.xrwb(path + ".nomedia", "");
        wj.sc(path + ".nomedia");

        final java.lang.String[] name = new java.lang.String[]{"url", "root"};

        final java.lang.String[] value = new java.lang.String[]{path, path};
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                gj.tz(d1.class, name, value);

            }

        });

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

    private void $_onItemSelected_i004a739c93(android.widget.AdapterView vw, android.view.View view, int pn, long id) {

        if (zf.dy(ty, "app")) {

            st.bjk(open.cn.awg.pro.R.id.bjk2).kjd(8);

        } else {

            if (pn == 1 || pn == 2) {

                java.lang.String path2 = "/storage/emulated/0/Android/data/open.cn.awg.pro/files/Download/";

                java.lang.String path1 = "/storage/emulated/0/Download/";

                java.lang.String add = ".file";

                int sdk = xt.sbxx().sdk;

                if (sdk < 30) {

                    st.bjk(open.cn.awg.pro.R.id.bjk2).zf(path1 + xt.sj(4) + add);

                } else {

                    st.bjk(open.cn.awg.pro.R.id.bjk2).zf(path2 + xt.sj(4) + add);

                }
                st.bjk(open.cn.awg.pro.R.id.bjk2).kjd(0);

            } else {

                st.bjk(open.cn.awg.pro.R.id.bjk2).kjd(8);

            }

        }

    }

    private void $_onNothingSelected_i004a739c93(android.widget.AdapterView vw) {

    }

    private void $_onClick_i171ea34f1a(android.view.View vw) {

        java.lang.String bjk1 = st.bjk(open.cn.awg.pro.R.id.bjk1).zf();
        bjk1 = zf.qctwkg(bjk1);

        java.lang.String bjk2 = st.bjk(open.cn.awg.pro.R.id.bjk2).zf();
        bjk2 = zf.qctwkg(bjk2);

        i.runlibrary.app.v.xlcd xlcd1 = st.xlcd(open.cn.awg.pro.R.id.xlcd1);

        if (zf.dy(bjk1, "")) {

            e1.tsk("提示", "文件下载链接不能为空");

        } else {

            if (zf.dy(e1.urlLockString(bjk1), "0")) {

                e1.tsk("提示", "文件下载链接无效");

            } else {

                i.runlibrary.app.xt$qxgl a = xt.qxgl();

                if (zf.dy(ty, "app")) {

                    if (xlcd1.dqzsxmxh() == 0) {

                        java.lang.String path = "/storage/emulated/0/Android/data/open.cn.awg.pro/files/app/download/" + appid + "/base.apk";
                        f = wj.wj(path);
                        gl.xz.download(bjk1, this, 1, f);
                        e1.tsk("提示", "已在后台开始下载");
                        st.xlcd(open.cn.awg.pro.R.id.xlcd1).kjd(8);
                        st.an(open.cn.awg.pro.R.id.an1).kjd(8);
                        st.an(open.cn.awg.pro.R.id.an4).kjd(0);

                    } else if (xlcd1.dqzsxmxh() == 1) {

                        bjk2 = "/storage/emulated/0/Android/data/open.cn.awg.pro/files/app/download/" + appid + "/base.apk";

                        java.lang.String[] name = new java.lang.String[]{"url", "path"};

                        java.lang.String[] value = new java.lang.String[]{bjk1, bjk2};
                        gj.tz(a5.class, name, value);
                        st.xlcd(open.cn.awg.pro.R.id.xlcd1).kjd(8);
                        st.an(open.cn.awg.pro.R.id.an1).kjd(8);
                        st.an(open.cn.awg.pro.R.id.an4).kjd(0);

                    }

                } else {

                    if (xlcd1.dqzsxmxh() == 0) {

                        gl.xz.download(bjk1, this, 0, f);
                        e1.tsk("提示", "已在后台开始下载");
                        gj.gb();

                    } else if (xlcd1.dqzsxmxh() == 1) {

                        if (zf.dy(bjk2, "")) {

                            e1.tsk("提示", "文件保存路径不能为空");

                        } else {

                            f = wj.wj(bjk2);
                            gl.xz.download(bjk1, this, 1, f);
                            e1.tsk("提示", "已在后台开始下载");
                            gj.gb();

                        }

                    } else if (xlcd1.dqzsxmxh() == 2) {

                        if (zf.dy(bjk2, "")) {

                            e1.tsk("提示", "文件保存路径不能为空");

                        } else {

                            java.lang.String[] name = new java.lang.String[]{"url", "path"};

                            java.lang.String[] value = new java.lang.String[]{bjk1, bjk2};
                            gj.tz(a5.class, name, value);
                            gj.gb();

                        }

                    }

                }

            }

        }

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

        android.widget.Button i0b66a667b1 = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an2);
        i0b66a667b1.setOnClickListener($_on_setOnClickListener_i0b66a667b1);

        android.widget.Button i8f51a35692 = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an3);
        i8f51a35692.setOnClickListener($_on_setOnClickListener_i8f51a35692);

        android.widget.Button i74ce6319f3 = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an4);
        i74ce6319f3.setOnClickListener($_on_setOnClickListener_i74ce6319f3);

        android.widget.EditText i32ee139ba1 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjk1);
        i32ee139ba1.setOnTouchListener($_on_setOnTouchListener_i32ee139ba1);

        android.widget.Spinner i004a739c93 = (android.widget.Spinner) findViewById(ay, vw, open.cn.awg.pro.R.id.xlcd1);
        i004a739c93.setOnItemSelectedListener($_on_setOnItemSelectedListener_i004a739c93);

        android.widget.Button i171ea34f1a = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an1);
        i171ea34f1a.setOnClickListener($_on_setOnClickListener_i171ea34f1a);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        __layoutIsLoaded(ay, vw);
    }
}
