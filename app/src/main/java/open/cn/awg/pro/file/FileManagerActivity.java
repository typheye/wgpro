/**
 ******************************************************************************
 * @file    FileManagerActivity.java
 * @author  Typheye
 * @brief   File manager screen.
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
package open.cn.awg.pro.file;

import android.view.View;
import android.view.Window;

import androidx.recyclerview.widget.RecyclerView;

import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.GlobalRuntimeState;
import open.cn.awg.pro.data.LocalDatabaseBridge;
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;


public class FileManagerActivity extends iActivity {

    public final FileManagerActivity lei = this, 类 = this;
    public open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

    public java.lang.String titles = "";

    public open.cn.awg.pro.data.LocalDatabaseBridge e11 = new LocalDatabaseBridge(_APPINFO);

    public java.lang.String jmroot = "";
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i464e6cc6fb = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i464e6cc6fb(vw, me);
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
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i9926c2f556 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i9926c2f556(vw, me);
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
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i32ee139ba1 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i32ee139ba1(vw, me);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_bd584e50cd = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_bd584e50cd(vw, me);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i84f04cd47e = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i84f04cd47e(vw, me);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i6c4a6b953f = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i6c4a6b953f(vw, me);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_efa568dfe6 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_efa568dfe6(vw, me);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_dc0a1b897b = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_dc0a1b897b(vw, me);
        }

    };
    private final androidx.recyclerview.widget.RecyclerView.OnScrollListener $_on_addOnScrollListener_f085d7c0f8 = new androidx.recyclerview.widget.RecyclerView.OnScrollListener() {

        public void onScrollStateChanged(androidx.recyclerview.widget.RecyclerView vw, int ns) {
            $_onScrollStateChanged_f085d7c0f8(vw, ns);
        }

        public void onScrolled(androidx.recyclerview.widget.RecyclerView vw, int dx, int dy) {
            $_onScrolled_f085d7c0f8(vw, dx, dy);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_d26d5d7080 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_d26d5d7080(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d26d5d7080(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.d1);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.app.AwgProApplication.getInstance().addActivity(lei);

        java.lang.String cache00 = "/data/user/0/open.cn.awg.pro/cache/00";

        java.lang.String ftitle = "/data/user/0/open.cn.awg.pro/cache/file/title";

        if (zf.dy(wj.dqwb(ftitle), null)) {

            wj.xrwb(ftitle, "< 文件管理");

        }
        titles = wj.dqwb(ftitle);
        st.wb(open.cn.awg.pro.R.id.Tab).zf(wj.dqwb(ftitle));
        e1.csh();

        java.lang.String urls = sj.hqtz("url");

        java.lang.String roots = sj.hqtz("root");

        if (zf.dy(urls, "") || zf.dy(urls, null) || zf.dy(roots, "") || zf.dy(roots, null)) {

            roots = wj.hqml("%");
            urls = wj.hqml("%");

            if (zf.cjw(roots, "/")) {

            } else {

                roots = roots + "/";

            }
            jmroot = roots;
            e1.NewFilesLoad(urls, roots);
            wj.xrwb(cache00, roots);
            open.cn.awg.pro.core.GlobalRuntimeState.d1_rootpath = roots;

            int sdk = xt.sbxx().sdk;

            if (sdk >= 30) {

                e1.tsk("提示", "由于使用Android11的设备新增储存分区限制，本文件管理仅能访问\n\"" + roots + "\"");

            }

        } else {

            roots = zf.qctwkg(roots);
            roots = wj.hqml(roots);
            urls = zf.qctwkg(urls);
            urls = wj.hqml(urls);

            if (zf.cjw(roots, "/")) {

            } else {

                roots = roots + "/";

            }
            jmroot = roots;
            e1.NewFilesLoad(urls, roots);
            open.cn.awg.pro.core.GlobalRuntimeState.d1_rootpath = roots;

        }

        java.lang.String set = "/data/user/0/open.cn.awg.pro/settings/set3.inf";

        if (zf.dy(wj.dqwb(set), "true")) {

            java.lang.String te = wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a4");

            java.lang.String[] name = new java.lang.String[]{"title", "text"};

            java.lang.String[] value = new java.lang.String[]{"新手教程", te};
            gj.tz(TextQrDisplayActivity.class, name, value);

        }

    }

    public void ts1(int a) {

        final java.lang.String a041 = "/data/user/0/open.cn.awg.pro/cache/tab";

        if (st.xxbj(open.cn.awg.pro.R.id.xxbj3).kjd() == 8 && st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd() == 8 && st.xxbj(open.cn.awg.pro.R.id.xxbj7).kjd() == 8 && st.xxbj(open.cn.awg.pro.R.id.xxbj5).kjd() == 8) {

            if (a == 1) {

                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        wj.xrwb(a041, "false");

                    }

                });

            } else if (a == -1) {

                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        wj.xrwb(a041, "true");

                    }

                });

            } else {

                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.wb(open.cn.awg.pro.R.id.ts1).g(0);
                        wj.xrwb(a041, "false");

                    }

                });

            }

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

            st.xdbj(open.cn.awg.pro.R.id.d1_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.d1_v).shxtck(false);

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

        if (zf.dy(a, "1")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj2).nbj(u, 0, u, 0);

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
            st.xdbj(open.cn.awg.pro.R.id.xdbj2).nbj(0, 0, 0, 0);

        } else if (zf.dy(a, "3")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj2).nbj(u, 0, u, 0);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "4")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj2).nbj(0, 0, 0, 0);

        }

        final java.lang.String a041 = "/data/user/0/open.cn.awg.pro/cache/tab";
        wj.xrwb(a041, "true");

    }

    public boolean onKeyDown(int kc, android.view.KeyEvent ke) {

        if (kc == 4) {

            java.lang.String url = st.wb(open.cn.awg.pro.R.id.zywblj).zf();

            if (zf.dy(url, jmroot)) {

                gj.gb();

            } else {

                java.lang.String[] fhValue = e1.fileinfo("%", url);
                e1.NewFilesLoad(fhValue[2], jmroot);

            }

        }
        return false;

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

        java.lang.String cache_c = "/data/user/0/open.cn.awg.pro/cache/c";

        java.lang.String cache_es = "/data/user/0/open.cn.awg.pro/cache/es";

        if (wj.cz(cache_c) && wj.cz(cache_es)) {

            e1.sx(wj.dqwb(cache_c), wj.dqwb(cache_es));
            wj.sc(cache_c);
            wj.sc(cache_es);

        }

    }

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.app.AwgProApplication.getInstance().removeActivity(lei);

        java.lang.String cache00 = "/data/user/0/open.cn.awg.pro/cache/00";

        java.lang.String cache1 = "/data/user/0/open.cn.awg.pro/cache/1";

        java.lang.String cache2 = "/data/user/0/open.cn.awg.pro/cache/2";

        java.lang.String cache3 = "/data/user/0/open.cn.awg.pro/cache/3";

        java.lang.String set4 = "/data/user/0/open.cn.awg.pro/settings/d1/set4.inf";

        if (zf.dy(wj.dqwb(set4), "false")) {

            wj.sc(cache1);
            wj.sc(cache2);
            wj.sc(cache3);
            wj.sc(cache00);

        }

        java.lang.String ftitle = "/data/user/0/open.cn.awg.pro/cache/file/title";
        wj.sc(ftitle);

        java.lang.String choose = "/data/user/0/open.cn.awg.pro/cache/file/choose";
        wj.sc(choose);

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

        i.runlibrary.app.v.v7lb v7lb1 = st.v7lb(open.cn.awg.pro.R.id.v7lb1);
        v7lb1.v.requestFocus();

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

    private void $_onClick_d26d5d7080(android.view.View vw) {

        java.lang.String r = titles;

        if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< 文件管理") || zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), r)) {

            java.lang.String url = st.wb(open.cn.awg.pro.R.id.zywblj).zf();

            if (zf.dy(url, jmroot)) {

                gj.gb();

            } else {

                java.lang.String[] fhValue = e1.fileinfo("%", url);
                e1.NewFilesLoad(fhValue[2], jmroot);

            }

        }

    }

    private boolean $_onLongClick_d26d5d7080(android.view.View vw) {
        return true;

    }

    private void $_onScrollStateChanged_f085d7c0f8(androidx.recyclerview.widget.RecyclerView vw, int ns) {

        int lwz = st.v7lb(open.cn.awg.pro.R.id.v7lb1).zhkjxm();

        java.lang.String cpath = st.wb(open.cn.awg.pro.R.id.zywblj).zf();

        if (ns == RecyclerView.SCROLL_STATE_IDLE) {

            if (!vw.canScrollVertically(1)) {

                ts1(1);
                e11.update_filelist(cpath, lwz);

            } else if (!vw.canScrollVertically(-1)) {

                ts1(-1);
                e11.update_filelist(cpath, 0);

            } else {

                ts1(0);
                e11.update_filelist(cpath, lwz);

            }

        }

    }

    private void $_onScrolled_f085d7c0f8(androidx.recyclerview.widget.RecyclerView vw, int dx, int dy) {

    }

    private boolean $_onTouch_dc0a1b897b(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private boolean $_onTouch_efa568dfe6(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private boolean $_onTouch_i6c4a6b953f(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private boolean $_onTouch_i84f04cd47e(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private boolean $_onTouch_bd584e50cd(android.view.View vw, android.view.MotionEvent me) {
        return true;

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

    private void $_onClick_i34dc7bf20a(android.view.View vw) {
        e1.cdx(0, null, null, null);

    }

    private void $_onClick_i4d951cdb0e(android.view.View vw) {

        try {

            java.lang.String bzf = st.bjk(open.cn.awg.pro.R.id.bjk1).zf();

            java.lang.String cache3 = "/data/user/0/open.cn.awg.pro/cache/3";

            java.lang.String cachec1 = "/data/user/0/open.cn.awg.pro/cache/c1";

            java.lang.String cachec2 = "/data/user/0/open.cn.awg.pro/cache/c2";

            java.lang.String cachec3 = "/data/user/0/open.cn.awg.pro/cache/c3";

            java.lang.String wa = wj.dqwb(cache3);

            java.lang.String wb = wj.dqwb(cachec1);

            java.lang.String wc = wj.dqwb(cachec2);

            java.lang.String wd = wj.dqwb(cachec3);

            open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

            java.lang.String[] aa;
            bzf = zf.qctwkg(bzf);

            if (zf.dy(bzf, "") || zf.dy(bzf, null)) {

                final java.lang.String z = "编辑框不能为空";
                gj.ts(z);

            } else {

                java.lang.String we = wd + bzf;

                if (zf.dy(wa, "重命名")) {

                    if (zf.dy(wc, we)) {

                        final java.lang.String z = "重命名失败\n文件(夹)已存在";
                        e1.tsk("提示", z);

                    } else {

                        java.lang.String pb1 = "/data/user/0/open.cn.awg.pro/settings";

                        java.lang.String pb2 = "/data/user/0/open.cn.awg.pro/data";

                        java.lang.String pb3 = "/data/user/0/open.cn.awg.pro/fix";

                        java.lang.String pb4 = "/data/user/0/open.cn.awg.pro/cache";

                        java.lang.String pb5 = "/data/user/0/open.cn.awg.pro/files/cache";

                        java.lang.String pb6 = "/storage/emulated/0/Android/data";

                        java.lang.String pb7 = "/storage/emulated/0/Android/obb";

                        if (zf.ckt(we, pb1) || zf.ckt(we, pb2) || zf.ckt(we, pb3) || zf.ckt(we, pb4) || zf.ckt(we, pb5) || (xt.sbxx().sdk > 29 && zf.ckt(we, pb6)) || (xt.sbxx().sdk > 29 && zf.ckt(we, pb7))) {

                            e1.tsk("提示", "非法操作");

                        } else {

                            java.lang.String m = "mv \"" + wc + "\" \"" + we + "\"";
                            aa = com.demo.e3.cmd(lei, m, false);

                            if (zf.dy(aa[0], "") && !zf.dy(aa[1], "")) {

                                final java.lang.String z = "重命名失败\n" + aa[1];
                                e1.tsk("提示", z);

                            } else if (!zf.dy(aa[0], "") && zf.dy(aa[1], "")) {

                                final java.lang.String z = "重命名成功";
                                e1.tsk("提示", z);
                                e1.sx(wb, wd);

                            } else {

                                final java.lang.String z = "重命名成功";
                                e1.tsk("提示", z);
                                e1.sx(wb, wd);

                            }

                        }

                    }
                    e1.cdx(0, null, null, null);

                } else if (zf.dy(wa, "新建目录")) {

                    java.lang.String pb1 = "/data/user/0/open.cn.awg.pro/settings";

                    java.lang.String pb2 = "/data/user/0/open.cn.awg.pro/data";

                    java.lang.String pb3 = "/data/user/0/open.cn.awg.pro/fix";

                    java.lang.String pb4 = "/data/user/0/open.cn.awg.pro/cache";

                    java.lang.String pb5 = "/data/user/0/open.cn.awg.pro/files/cache";

                    java.lang.String pb6 = "/storage/emulated/0/Android/data";

                    java.lang.String pb7 = "/storage/emulated/0/Android/obb";

                    if (zf.ckt(we, pb1) || zf.ckt(we, pb2) || zf.ckt(we, pb3) || zf.ckt(we, pb4) || zf.ckt(we, pb5) || (xt.sbxx().sdk > 29 && zf.ckt(we, pb6)) || (xt.sbxx().sdk > 29 && zf.ckt(we, pb7))) {

                        e1.tsk("提示", "非法操作");

                    } else {

                        java.lang.String m = "mkdir \"" + we + "\"";
                        aa = com.demo.e3.cmd(lei, m, false);

                        if (zf.dy(aa[0], "") && !zf.dy(aa[1], "")) {

                            final java.lang.String z = "新建目录失败\n" + aa[1];
                            e1.tsk("提示", z);

                        } else if (!zf.dy(aa[0], "") && zf.dy(aa[1], "")) {

                            final java.lang.String z = "新建目录成功";
                            e1.tsk("提示", z);
                            e1.sx(wb, wd);

                        } else {

                            final java.lang.String z = "新建目录成功";
                            e1.tsk("提示", z);
                            e1.sx(wb, wd);

                        }

                    }
                    e1.cdx(0, null, null, null);

                } else if (zf.dy(wa, "新建文件")) {

                    java.lang.String pb1 = "/data/user/0/open.cn.awg.pro/settings";

                    java.lang.String pb2 = "/data/user/0/open.cn.awg.pro/data";

                    java.lang.String pb3 = "/data/user/0/open.cn.awg.pro/fix";

                    java.lang.String pb4 = "/data/user/0/open.cn.awg.pro/cache";

                    java.lang.String pb5 = "/data/user/0/open.cn.awg.pro/files/cache";

                    java.lang.String pb6 = "/storage/emulated/0/Android/data";

                    java.lang.String pb7 = "/storage/emulated/0/Android/obb";

                    if (zf.ckt(we, pb1) || zf.ckt(we, pb2) || zf.ckt(we, pb3) || zf.ckt(we, pb4) || zf.ckt(we, pb5) || (xt.sbxx().sdk > 29 && zf.ckt(we, pb6)) || (xt.sbxx().sdk > 29 && zf.ckt(we, pb7))) {

                        e1.tsk("提示", "非法操作");

                    } else {

                        java.lang.String m = "touch \"" + we + "\"";
                        aa = com.demo.e3.cmd(lei, m, false);

                        if (zf.dy(aa[0], "") && !zf.dy(aa[1], "")) {

                            final java.lang.String z = "新建文件失败\n" + aa[1];
                            e1.tsk("提示", z);

                        } else if (!zf.dy(aa[0], "") && zf.dy(aa[1], "")) {

                            final java.lang.String z = "新建文件成功";
                            e1.tsk("提示", z);
                            e1.sx(wb, wd);

                        } else {

                            final java.lang.String z = "新建文件成功";
                            e1.tsk("提示", z);
                            e1.sx(wb, wd);

                        }

                    }
                    e1.cdx(0, null, null, null);

                } else {

                }

            }

        } catch (java.lang.Throwable e) {

            e1.upload_error(e, "e1.NewFilesLoad(s,s)");
            gj.gb();

        }

    }

    private boolean $_onTouch_i9926c2f556(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private void $_onClick_i8217d42a17(android.view.View vw) {

        if (st.xxbj(open.cn.awg.pro.R.id.xxbj10).kjd() == 0) {

            st.xxbj(open.cn.awg.pro.R.id.xxbj10).kjd(8);

        } else {

            st.xxbj(open.cn.awg.pro.R.id.xxbj10).kjd(0);

        }
        gj.gb();

    }

    private void $_onClick_i11aeec1890(android.view.View vw) {

        if (st.xxbj(open.cn.awg.pro.R.id.xxbj10).kjd() == 0) {

            st.xxbj(open.cn.awg.pro.R.id.xxbj10).kjd(8);

        } else {

            st.xxbj(open.cn.awg.pro.R.id.xxbj10).kjd(0);

        }

    }

    private boolean $_onTouch_i464e6cc6fb(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
        d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);
        d26d5d7080.setOnLongClickListener($_on_setOnLongClickListener_d26d5d7080);

        android.support.v7.widget.RecyclerView f085d7c0f8 = (android.support.v7.widget.RecyclerView) findViewById(ay, vw, open.cn.awg.pro.R.id.v7lb1);
        f085d7c0f8.addOnScrollListener($_on_addOnScrollListener_f085d7c0f8);

        android.widget.LinearLayout dc0a1b897b = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj2);
        dc0a1b897b.setOnTouchListener($_on_setOnTouchListener_dc0a1b897b);

        android.widget.LinearLayout efa568dfe6 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj3);
        efa568dfe6.setOnTouchListener($_on_setOnTouchListener_efa568dfe6);

        android.widget.LinearLayout i6c4a6b953f = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj6);
        i6c4a6b953f.setOnTouchListener($_on_setOnTouchListener_i6c4a6b953f);

        android.widget.LinearLayout i84f04cd47e = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj7);
        i84f04cd47e.setOnTouchListener($_on_setOnTouchListener_i84f04cd47e);

        android.widget.LinearLayout bd584e50cd = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj5);
        bd584e50cd.setOnTouchListener($_on_setOnTouchListener_bd584e50cd);

        android.widget.EditText i32ee139ba1 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjk1);
        i32ee139ba1.setOnTouchListener($_on_setOnTouchListener_i32ee139ba1);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
        i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

        android.widget.LinearLayout i9926c2f556 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj9);
        i9926c2f556.setOnTouchListener($_on_setOnTouchListener_i9926c2f556);

        android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);
        i8217d42a17.setOnClickListener($_on_setOnClickListener_i8217d42a17);

        android.widget.ImageView i11aeec1890 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx4);
        i11aeec1890.setOnClickListener($_on_setOnClickListener_i11aeec1890);

        android.widget.LinearLayout i464e6cc6fb = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj10);
        i464e6cc6fb.setOnTouchListener($_on_setOnTouchListener_i464e6cc6fb);

        android.widget.TextView d0ffa028ae = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.bt);

        android.widget.TextView i203811ac10 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.zywblj);

        android.widget.LinearLayout e1433e26d6 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj1);

        __layoutIsLoaded(ay, vw);
    }
}
