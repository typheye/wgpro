/**
 ******************************************************************************
 * @file    l4.java
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

public class l4 extends iActivity {

    public final l4 lei = this, 类 = this;
    public java.lang.String um = "";
    public java.lang.String osArch;
    public java.io.File f;
    public open.cn.awg.pro.e1 e1 = new e1(_APPINFO);
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i74ce6319f3 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i74ce6319f3(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i0b66a667b1 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i0b66a667b1(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i8f51a35692 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i8f51a35692(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i171ea34f1a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i171ea34f1a(vw);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i63f06183a1 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i63f06183a1(vw, me);
        }

    };
    private final android.widget.AdapterView.OnItemSelectedListener $_on_setOnItemSelectedListener_fef1635c8d = new android.widget.AdapterView.OnItemSelectedListener() {

        public void onItemSelected(android.widget.AdapterView vw, android.view.View view, int pn, long id) {
            $_onItemSelected_fef1635c8d(vw, view, pn, id);
        }

        public void onNothingSelected(android.widget.AdapterView vw) {
            $_onNothingSelected_fef1635c8d(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d26d5d7080(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.l4);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.e6.getInstance().addActivity(lei);

        if (e1.isInstallX5()) {

            show(2);

            java.lang.String path2 = "/storage/emulated/0/Android/data/open.cn.awg.pro/files/Download/x5install.zip";

            java.lang.String path1 = "/storage/emulated/0/Download/x5install.zip";

            if (wj.cz(path1)) {

                um = path1;

            } else if (wj.cz(path2)) {

                um = path2;

            }

        } else {

            gj.xc(new java.lang.Thread() {

                public void run() {

                    show(4);
                    osArch = System.getProperty("os.arch");

                    java.lang.String path3_32 = "@resource/2c951a41d7c917f0a76ae46ed499d2ff";

                    java.lang.String path3_64 = "@resource/e0a8f35f2676b288f562b8aaf29de9d1";

                    java.lang.String path2 = "/storage/emulated/0/Android/data/open.cn.awg.pro/files/Download/x5install.zip";

                    java.lang.String path1 = "/storage/emulated/0/Download/x5install.zip";

                    if (wj.cz(path1) || wj.cz(path2) || wj.cz(path3_32) || wj.cz(path3_64)) {

                        if (wj.cz(path1)) {

                            um = path1;

                        } else if (wj.cz(path2)) {

                            um = path2;

                        } else {

                            if (zf.cz(osArch, "64")) {

                                wj.fz(path3_64, path2, true);
                                um = path2;

                            } else {

                                wj.fz(path3_32, path2, true);
                                um = path2;

                            }

                        }
                        show(1);

                    } else {

                        i.runlibrary.app.v.xlcd xlcd2 = st.xlcd(open.cn.awg.pro.R.id.xlcd2);

                        java.lang.String[] sz1 = new java.lang.String[]{"源1:X5内核32位[81.1M]", "源2:X5内核32位[81.1M]", "自定义下载源"};

                        java.lang.String[] sz2 = new java.lang.String[]{"源1:X5内核64位[92.2M]", "源2:X5内核64位[92.2M]", "自定义下载源"};

                        if (zf.cz(osArch, "64")) {

                            xlcd2.csh(sz2);

                        } else {

                            xlcd2.csh(sz1);

                        }
                        show(0);

                    }

                }

            });

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

            st.xdbj(open.cn.awg.pro.R.id.l4_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.l4_v).shxtck(false);

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

        java.lang.String[] sz = new java.lang.String[]{"使用系统下载器下载", "使用软件下载器下载"};
        xlcd1.csh(sz);

    }

    public void c1() {
        gj.xc(new java.lang.Thread() {

            public void run() {

                show(4);

                final int y = wj.sc(um);

                if (y == 1) {

                    e1.tsk("提示", "已清除");

                } else {

                    e1.tsk("提示", "内核包不存在");

                }
                show(2);

            }

        });

    }

    public void c2() {
        gj.xc(new java.lang.Thread() {

            public void run() {

                show(4);

                java.lang.String xx = "内核删除执行结果如下，应用将在2秒后重启以完成卸载！\n\n";

                java.lang.String[] d = new java.lang.String[]{"/data/user/0/open.cn.awg.pro/app_tbs", "/data/user/0/open.cn.awg.pro/app_tbs_64", "/data/user/0/open.cn.awg.pro/app_tbs_common_share", "/data/user/0/open.cn.awg.pro/shared_prefs"};

                for (int i = 0; i < 4; i++) {

                    java.lang.String m = "rm -rf \"" + d[i] + "\"";

                    java.lang.String[] aa = new java.lang.String[]{"", ""};
                    aa = com.demo.e3.cmd(lei, m, false);

                    if (zf.dy(aa[0], "") && !zf.dy(aa[1], "")) {

                        xx = xx + "[文件路径]\n" + d[i] + "\n[执行状态]\n删除失败：" + aa[1] + "\n\n";

                    } else if (!zf.dy(aa[0], "") && zf.dy(aa[1], "")) {

                        xx = xx + "[文件路径]\n" + d[i] + "\n[执行状态]\n删除成功\n\n";

                    } else {

                        xx = xx + "[文件路径]\n" + d[i] + "\n[执行状态]\n删除成功\n\n";

                    }

                }
                gj.zt(2000);
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        java.lang.String[] name = new java.lang.String[]{"Msg"};

                        java.lang.String[] value = new java.lang.String[]{"卸载成功 点击重启"};
                        gj.tz(a0.class, name, value);

                    }

                });

            }

        });

    }

    public void show(int i_) {

        final int i = i_;
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                i.runlibrary.app.v.xxbj x1 = st.xxbj(open.cn.awg.pro.R.id.xxbj2);

                i.runlibrary.app.v.xxbj x2 = st.xxbj(open.cn.awg.pro.R.id.xxbj4);

                i.runlibrary.app.v.xxbj x3 = st.xxbj(open.cn.awg.pro.R.id.xxbj5);

                i.runlibrary.app.v.xxbj x4 = st.xxbj(open.cn.awg.pro.R.id.xxbj6);

                if (i == 0) {

                    x1.kjd(0);
                    x2.kjd(8);
                    x3.kjd(8);
                    x4.kjd(8);

                } else if (i == 1) {

                    x1.kjd(8);
                    x2.kjd(0);
                    x3.kjd(8);
                    x4.kjd(8);

                } else if (i == 2) {

                    x1.kjd(8);
                    x2.kjd(8);
                    x3.kjd(0);
                    x4.kjd(8);

                } else if (i == 4) {

                    x1.kjd(8);
                    x2.kjd(8);
                    x3.kjd(8);
                    x4.kjd(0);

                }

            }

        });

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

    private void $_onItemSelected_fef1635c8d(android.widget.AdapterView vw, android.view.View view, int pn, long id) {

        if (pn == 2) {

            st.bjk(open.cn.awg.pro.R.id.bjk2).kjd(0);

        } else {

            st.bjk(open.cn.awg.pro.R.id.bjk2).kjd(8);

        }

    }

    private void $_onNothingSelected_fef1635c8d(android.widget.AdapterView vw) {

    }

    private boolean $_onTouch_i63f06183a1(android.view.View vw, android.view.MotionEvent me) {

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

        java.lang.String bjk2 = st.bjk(open.cn.awg.pro.R.id.bjk2).zf();
        bjk2 = zf.qctwkg(bjk2);

        i.runlibrary.app.v.xlcd xlcd1 = st.xlcd(open.cn.awg.pro.R.id.xlcd1);

        i.runlibrary.app.v.xlcd xlcd2 = st.xlcd(open.cn.awg.pro.R.id.xlcd2);

        java.lang.String url = "";

        java.lang.String path = "";

        java.lang.String path2 = "/storage/emulated/0/Android/data/open.cn.awg.pro/files/Download/";

        java.lang.String path1 = "/storage/emulated/0/Download/";

        java.lang.String add = "x5install.zip";

        int sdk = xt.sbxx().sdk;

        if (sdk < 30) {

            path = path1 + add;

        } else {

            path = path2 + add;

        }

        if (zf.cz(osArch, "64")) {

            if (xlcd2.dqzsxmxh() == 0) {

                url = "https://service.typheye.cn/app/open.cn.awg.pro/download/x5s/x5install_64bit.zip";

            } else if (xlcd2.dqzsxmxh() == 1) {

                url = "https://service.typheye.cn/app/open.cn.awg.pro/download/x5s/x5install_64bit.zip";

            } else if (xlcd2.dqzsxmxh() == 2) {

                url = bjk2;

            }

        } else {

            if (xlcd2.dqzsxmxh() == 0) {

                url = "https://service.typheye.cn/app/open.cn.awg.pro/download/x5s/x5install_32bit.zip";

            } else if (xlcd2.dqzsxmxh() == 1) {

                url = "https://service.typheye.cn/app/open.cn.awg.pro/download/x5s/x5install_32bit.zip";

            } else if (xlcd2.dqzsxmxh() == 2) {

                url = bjk2;

            }

        }

        if (zf.dy(e1.urlLockString(url), "0")) {

            e1.tsk("提示", "下载源无效");

        } else {

            i.runlibrary.app.xt$qxgl a = xt.qxgl();

            if (true) {

                if (xlcd1.dqzsxmxh() == 0) {

                    f = wj.wj(path);
                    gl.xz.download(url, this, 1, f);
                    e1.tsk("提示", "已在后台开始下载\n请在下载完成后重新进入本页面,进行下一步的安装");
                    gj.gb();

                } else if (xlcd1.dqzsxmxh() == 1) {

                    java.lang.String[] name = new java.lang.String[]{"url", "path"};

                    java.lang.String[] value = new java.lang.String[]{url, path};
                    gj.tz(a5.class, name, value);
                    e1.tsk("提示", "已开始下载\n请在下载完成后重新进入本页面,进行下一步的安装");
                    gj.gb();

                }

            } else {

                e1.tsk("提示", "您未授权应用\"修改或删除您共享存储空间中的内容\"，请先前往设置授权后再使用");

            }

        }

    }

    private void $_onClick_i8f51a35692(android.view.View vw) {
        show(4);
        gj.xc(new java.lang.Thread() {

            public void run() {

                java.lang.String nwj = "/data/user/0/open.cn.awg.pro/cache/x5/";
                wj.jy(um, nwj, true);

                java.lang.String nrs = wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/c1");

                final java.lang.String[] name = new java.lang.String[]{"sh", "auto"};

                final java.lang.String[] value = new java.lang.String[]{nrs, "enable"};
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        gj.tz(j4.class, name, value);

                    }

                });
                gj.gb();

            }

        });

    }

    private void $_onClick_i0b66a667b1(android.view.View vw) {
        c1();

    }

    private void $_onClick_i74ce6319f3(android.view.View vw) {
        c2();

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

        android.widget.Spinner fef1635c8d = (android.widget.Spinner) findViewById(ay, vw, open.cn.awg.pro.R.id.xlcd2);
        fef1635c8d.setOnItemSelectedListener($_on_setOnItemSelectedListener_fef1635c8d);

        android.widget.EditText i63f06183a1 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjk2);
        i63f06183a1.setOnTouchListener($_on_setOnTouchListener_i63f06183a1);

        android.widget.Spinner i004a739c93 = (android.widget.Spinner) findViewById(ay, vw, open.cn.awg.pro.R.id.xlcd1);

        android.widget.Button i171ea34f1a = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an1);
        i171ea34f1a.setOnClickListener($_on_setOnClickListener_i171ea34f1a);

        android.widget.Button i8f51a35692 = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an3);
        i8f51a35692.setOnClickListener($_on_setOnClickListener_i8f51a35692);

        android.widget.Button i0b66a667b1 = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an2);
        i0b66a667b1.setOnClickListener($_on_setOnClickListener_i0b66a667b1);

        android.widget.Button i74ce6319f3 = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an4);
        i74ce6319f3.setOnClickListener($_on_setOnClickListener_i74ce6319f3);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        __layoutIsLoaded(ay, vw);
    }
}
