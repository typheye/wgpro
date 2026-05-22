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

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.recyclerview.widget.RecyclerView;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.core.GlobalRuntimeState;
import open.cn.awg.pro.data.LocalDatabaseBridge;
import open.cn.awg.pro.R;
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;

public class FileManagerActivity extends BaseAwgActivity {

    public final FileManagerActivity lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

    public String titles = "";

    public LocalDatabaseBridge e11 = new LocalDatabaseBridge(_APPINFO);

    public String jmroot = "";
    private final View.OnTouchListener xxbj10TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj10Touch(vw, me);
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
    private final View.OnTouchListener xxbj9TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj9Touch(vw, me);
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
    private final View.OnTouchListener bjk1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjk1Touch(vw, me);
        }

    };
    private final View.OnTouchListener xxbj5TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj5Touch(vw, me);
        }

    };
    private final View.OnTouchListener xxbj7TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj7Touch(vw, me);
        }

    };
    private final View.OnTouchListener xxbj6TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj6Touch(vw, me);
        }

    };
    private final View.OnTouchListener xxbj3TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj3Touch(vw, me);
        }

    };
    private final View.OnTouchListener xxbj2TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj2Touch(vw, me);
        }

    };
    private final RecyclerView.OnScrollListener v7lb1ScrollListener = new RecyclerView.OnScrollListener() {

        public void onScrollStateChanged(RecyclerView vw, int ns) {
            onV7lb1ScrollStateChanged(vw, ns);
        }

        public void onScrolled(RecyclerView vw, int dx, int dy) {
            onV7lb1Scrolled(vw, dx, dy);
        }

    };
    private final View.OnLongClickListener titleBarLongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onTitleBarLongClick(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.file_manager);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        String cache00 = AppPaths.appPath("cache/00");

        String ftitle = AppPaths.appPath("cache/file/title");

        if (zf.dy(wj.dqwb(ftitle), null)) {

            wj.xrwb(ftitle, "< 文件管理");

        }
        titles = wj.dqwb(ftitle);
        st.wb(R.id.title_bar).zf(wj.dqwb(ftitle));
        e1.csh();

        String urls = sj.hqtz("url");

        String roots = sj.hqtz("root");

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
            GlobalRuntimeState.d1_rootpath = roots;

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
            GlobalRuntimeState.d1_rootpath = roots;

        }

        String set = AppPaths.appPath("settings/set3.inf");

        if (zf.dy(wj.dqwb(set), "true")) {

            String te = wj.dqwb(AppPaths.appPath("data/assets/a4"));

            String[] name = new String[]{"title", "text"};

            String[] value = new String[]{"新手教程", te};
            gj.tz(TextQrDisplayActivity.class, name, value);

        }

    }

    public void ts1(int a) {

        final String a041 = AppPaths.appPath("cache/tab");

        if (st.xxbj(R.id.xxbj3).kjd() == 8 && st.xxbj(R.id.xxbj6).kjd() == 8 && st.xxbj(R.id.xxbj7).kjd() == 8 && st.xxbj(R.id.xxbj5).kjd() == 8) {

            if (a == 1) {

                gj.jmxc(new Runnable() {

                    public void run() {

                        wj.xrwb(a041, "false");

                    }

                });

            } else if (a == -1) {

                gj.jmxc(new Runnable() {

                    public void run() {

                        wj.xrwb(a041, "true");

                    }

                });

            } else {

                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.ts1).g(0);
                        wj.xrwb(a041, "false");

                    }

                });

            }

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.file_manager_root, R.id.xdbj2, R.id.title_bar);

        final String a041 = AppPaths.appPath("cache/tab");
        wj.xrwb(a041, "true");

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

            String url = st.wb(R.id.zywblj).zf();

            if (zf.dy(url, jmroot)) {

                gj.gb();

            } else {

                String[] fhValue = e1.fileinfo("%", url);
                e1.NewFilesLoad(fhValue[2], jmroot);

            }

        }
        return false;

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
            applyWindowModeFromSettings();

        String cache_c = AppPaths.appPath("cache/c");

        String cache_es = AppPaths.appPath("cache/es");

        if (wj.cz(cache_c) && wj.cz(cache_es)) {

            e1.sx(wj.dqwb(cache_c), wj.dqwb(cache_es));
            wj.sc(cache_c);
            wj.sc(cache_es);

        }

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

        String cache00 = AppPaths.appPath("cache/00");

        String cache1 = AppPaths.appPath("cache/1");

        String cache2 = AppPaths.appPath("cache/2");

        String cache3 = AppPaths.appPath("cache/3");

        String set4 = AppPaths.appPath("settings/d1/set4.inf");

        if (zf.dy(wj.dqwb(set4), "false")) {

            wj.sc(cache1);
            wj.sc(cache2);
            wj.sc(cache3);
            wj.sc(cache00);

        }

        String ftitle = AppPaths.appPath("cache/file/title");
        wj.sc(ftitle);

        String choose = AppPaths.appPath("cache/file/choose");
        wj.sc(choose);

    }

    public void onRestart() {
        super.onRestart();
            applyWindowModeFromSettings();

    }

    public void onStart() {
        super.onStart();
            applyWindowModeFromSettings();

        i.runlibrary.app.v.v7lb v7lb1 = st.v7lb(R.id.v7lb1);
        v7lb1.v.requestFocus();

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

    }

    public void onResume() {
        super.onResume();
            applyWindowModeFromSettings();

    }

    private void onTitleBarClick(View vw) {

        String r = titles;

        if (zf.dy(st.wb(R.id.title_bar).zf(), "< 文件管理") || zf.dy(st.wb(R.id.title_bar).zf(), r)) {

            String url = st.wb(R.id.zywblj).zf();

            if (zf.dy(url, jmroot)) {

                gj.gb();

            } else {

                String[] fhValue = e1.fileinfo("%", url);
                e1.NewFilesLoad(fhValue[2], jmroot);

            }

        }

    }

    private boolean onTitleBarLongClick(View vw) {
        return true;

    }

    private void onV7lb1ScrollStateChanged(RecyclerView vw, int ns) {

        int lwz = st.v7lb(R.id.v7lb1).zhkjxm();

        String cpath = st.wb(R.id.zywblj).zf();

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

    private void onV7lb1Scrolled(RecyclerView vw, int dx, int dy) {

    }

    private boolean onXxbj2Touch(View vw, MotionEvent me) {
        return true;

    }

    private boolean onXxbj3Touch(View vw, MotionEvent me) {
        return true;

    }

    private boolean onXxbj6Touch(View vw, MotionEvent me) {
        return true;

    }

    private boolean onXxbj7Touch(View vw, MotionEvent me) {
        return true;

    }

    private boolean onXxbj5Touch(View vw, MotionEvent me) {
        return true;

    }

    private boolean onBjk1Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            gj.jmxc(new Runnable() {

                public void run() {

                    i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(R.id.qtgd1);
                    qtgd1.v.requestFocus();

                }

            });

        }
        return false;

    }

    private void onTx1Click(View vw) {
        e1.cdx(0, null, null, null);

    }

    private void onTx2Click(View vw) {

        try {

            String bzf = st.bjk(R.id.bjk1).zf();

            String cache3 = AppPaths.appPath("cache/3");

            String cachec1 = AppPaths.appPath("cache/c1");

            String cachec2 = AppPaths.appPath("cache/c2");

            String cachec3 = AppPaths.appPath("cache/c3");

            String wa = wj.dqwb(cache3);

            String wb = wj.dqwb(cachec1);

            String wc = wj.dqwb(cachec2);

            String wd = wj.dqwb(cachec3);

            AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

            String[] aa;
            bzf = zf.qctwkg(bzf);

            if (zf.dy(bzf, "") || zf.dy(bzf, null)) {

                final String z = "编辑框不能为空";
                gj.ts(z);

            } else {

                String we = wd + bzf;

                if (zf.dy(wa, "重命名")) {

                    if (zf.dy(wc, we)) {

                        final String z = "重命名失败\n文件(夹)已存在";
                        e1.tsk("提示", z);

                    } else {

                        String pb1 = AppPaths.appPath("settings");

                        String pb2 = AppPaths.appPath("data");

                        String pb3 = AppPaths.appPath("fix");

                        String pb4 = AppPaths.appPath("cache");

                        String pb5 = AppPaths.appPath("files/cache");

                        String pb6 = "/storage/emulated/0/Android/data";

                        String pb7 = "/storage/emulated/0/Android/obb";

                        if (zf.ckt(we, pb1) || zf.ckt(we, pb2) || zf.ckt(we, pb3) || zf.ckt(we, pb4) || zf.ckt(we, pb5) || (xt.sbxx().sdk > 29 && zf.ckt(we, pb6)) || (xt.sbxx().sdk > 29 && zf.ckt(we, pb7))) {

                            e1.tsk("提示", "非法操作");

                        } else {

                            String m = "mv \"" + wc + "\" \"" + we + "\"";
                            aa = com.demo.e3.cmd(lei, m, false);

                            if (zf.dy(aa[0], "") && !zf.dy(aa[1], "")) {

                                final String z = "重命名失败\n" + aa[1];
                                e1.tsk("提示", z);

                            } else if (!zf.dy(aa[0], "") && zf.dy(aa[1], "")) {

                                final String z = "重命名成功";
                                e1.tsk("提示", z);
                                e1.sx(wb, wd);

                            } else {

                                final String z = "重命名成功";
                                e1.tsk("提示", z);
                                e1.sx(wb, wd);

                            }

                        }

                    }
                    e1.cdx(0, null, null, null);

                } else if (zf.dy(wa, "新建目录")) {

                    String pb1 = AppPaths.appPath("settings");

                    String pb2 = AppPaths.appPath("data");

                    String pb3 = AppPaths.appPath("fix");

                    String pb4 = AppPaths.appPath("cache");

                    String pb5 = AppPaths.appPath("files/cache");

                    String pb6 = "/storage/emulated/0/Android/data";

                    String pb7 = "/storage/emulated/0/Android/obb";

                    if (zf.ckt(we, pb1) || zf.ckt(we, pb2) || zf.ckt(we, pb3) || zf.ckt(we, pb4) || zf.ckt(we, pb5) || (xt.sbxx().sdk > 29 && zf.ckt(we, pb6)) || (xt.sbxx().sdk > 29 && zf.ckt(we, pb7))) {

                        e1.tsk("提示", "非法操作");

                    } else {

                        String m = "mkdir \"" + we + "\"";
                        aa = com.demo.e3.cmd(lei, m, false);

                        if (zf.dy(aa[0], "") && !zf.dy(aa[1], "")) {

                            final String z = "新建目录失败\n" + aa[1];
                            e1.tsk("提示", z);

                        } else if (!zf.dy(aa[0], "") && zf.dy(aa[1], "")) {

                            final String z = "新建目录成功";
                            e1.tsk("提示", z);
                            e1.sx(wb, wd);

                        } else {

                            final String z = "新建目录成功";
                            e1.tsk("提示", z);
                            e1.sx(wb, wd);

                        }

                    }
                    e1.cdx(0, null, null, null);

                } else if (zf.dy(wa, "新建文件")) {

                    String pb1 = AppPaths.appPath("settings");

                    String pb2 = AppPaths.appPath("data");

                    String pb3 = AppPaths.appPath("fix");

                    String pb4 = AppPaths.appPath("cache");

                    String pb5 = AppPaths.appPath("files/cache");

                    String pb6 = "/storage/emulated/0/Android/data";

                    String pb7 = "/storage/emulated/0/Android/obb";

                    if (zf.ckt(we, pb1) || zf.ckt(we, pb2) || zf.ckt(we, pb3) || zf.ckt(we, pb4) || zf.ckt(we, pb5) || (xt.sbxx().sdk > 29 && zf.ckt(we, pb6)) || (xt.sbxx().sdk > 29 && zf.ckt(we, pb7))) {

                        e1.tsk("提示", "非法操作");

                    } else {

                        String m = "touch \"" + we + "\"";
                        aa = com.demo.e3.cmd(lei, m, false);

                        if (zf.dy(aa[0], "") && !zf.dy(aa[1], "")) {

                            final String z = "新建文件失败\n" + aa[1];
                            e1.tsk("提示", z);

                        } else if (!zf.dy(aa[0], "") && zf.dy(aa[1], "")) {

                            final String z = "新建文件成功";
                            e1.tsk("提示", z);
                            e1.sx(wb, wd);

                        } else {

                            final String z = "新建文件成功";
                            e1.tsk("提示", z);
                            e1.sx(wb, wd);

                        }

                    }
                    e1.cdx(0, null, null, null);

                } else {

                }

            }

        } catch (Throwable e) {

            e1.upload_error(e, "e1.NewFilesLoad(s,s)");
            gj.gb();

        }

    }

    private boolean onXxbj9Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onTx3Click(View vw) {

        if (st.xxbj(R.id.xxbj10).kjd() == 0) {

            st.xxbj(R.id.xxbj10).kjd(8);

        } else {

            st.xxbj(R.id.xxbj10).kjd(0);

        }
        gj.gb();

    }

    private void onTx4Click(View vw) {

        if (st.xxbj(R.id.xxbj10).kjd() == 0) {

            st.xxbj(R.id.xxbj10).kjd(8);

        } else {

            st.xxbj(R.id.xxbj10).kjd(0);

        }

    }

    private boolean onXxbj10Touch(View vw, MotionEvent me) {
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);
        d26d5d7080.setOnLongClickListener(titleBarLongClickListener);

        RecyclerView f085d7c0f8 = (RecyclerView) findViewById(ay, vw, R.id.v7lb1);
        f085d7c0f8.addOnScrollListener(v7lb1ScrollListener);

        LinearLayout dc0a1b897b = (LinearLayout) findViewById(ay, vw, R.id.xxbj2);
        dc0a1b897b.setOnTouchListener(xxbj2TouchListener);

        LinearLayout efa568dfe6 = (LinearLayout) findViewById(ay, vw, R.id.xxbj3);
        efa568dfe6.setOnTouchListener(xxbj3TouchListener);

        LinearLayout i6c4a6b953f = (LinearLayout) findViewById(ay, vw, R.id.xxbj6);
        i6c4a6b953f.setOnTouchListener(xxbj6TouchListener);

        LinearLayout i84f04cd47e = (LinearLayout) findViewById(ay, vw, R.id.xxbj7);
        i84f04cd47e.setOnTouchListener(xxbj7TouchListener);

        LinearLayout bd584e50cd = (LinearLayout) findViewById(ay, vw, R.id.xxbj5);
        bd584e50cd.setOnTouchListener(xxbj5TouchListener);

        EditText i32ee139ba1 = (EditText) findViewById(ay, vw, R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        LinearLayout i9926c2f556 = (LinearLayout) findViewById(ay, vw, R.id.xxbj9);
        i9926c2f556.setOnTouchListener(xxbj9TouchListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i11aeec1890 = (ImageView) findViewById(ay, vw, R.id.tx4);
        i11aeec1890.setOnClickListener(tx4ClickListener);

        LinearLayout i464e6cc6fb = (LinearLayout) findViewById(ay, vw, R.id.xxbj10);
        i464e6cc6fb.setOnTouchListener(xxbj10TouchListener);

        TextView d0ffa028ae = (TextView) findViewById(ay, vw, R.id.bt);

        TextView i203811ac10 = (TextView) findViewById(ay, vw, R.id.zywblj);

        LinearLayout e1433e26d6 = (LinearLayout) findViewById(ay, vw, R.id.xxbj1);

        __layoutIsLoaded(ay, vw);
    }
}
