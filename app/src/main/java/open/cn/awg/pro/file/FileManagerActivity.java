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
    public AppRuntimeBridge e1;

    public String titles = "";

    public LocalDatabaseBridge e11;

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
        e1 = new AppRuntimeBridge(this);
        e11 = new LocalDatabaseBridge(this);
        setContentView(R.layout.file_manager);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        String cache00 = AppPaths.appPath("cache/00");

        String ftitle = AppPaths.appPath("cache/file/title");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(FileManagerActivity.this, (String) (ftitle)), null)) {

            open.cn.awg.pro.core.NativeFiles.writeText(FileManagerActivity.this, (String) (ftitle), (String) ("文件管理"));

        }
        titles = open.cn.awg.pro.core.NativeFiles.readText(FileManagerActivity.this, (String) (ftitle));
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf(open.cn.awg.pro.core.NativeFiles.readText(FileManagerActivity.this, (String) (ftitle))));
        e1.csh();

        String urls = FileManagerActivity.this.getIntent().getStringExtra(String.valueOf("url"));

        String roots = FileManagerActivity.this.getIntent().getStringExtra(String.valueOf("root"));

        if (java.util.Objects.equals(urls, "") || java.util.Objects.equals(urls, null) || java.util.Objects.equals(roots, "") || java.util.Objects.equals(roots, null)) {

            roots = open.cn.awg.pro.core.NativeFiles.resolvePath(FileManagerActivity.this, (String) ("%"));
            urls = open.cn.awg.pro.core.NativeFiles.resolvePath(FileManagerActivity.this, (String) ("%"));

            if (open.cn.awg.pro.core.TextValues.endsWith(roots, "/")) {

            } else {

                roots = roots + "/";

            }
            jmroot = roots;
            e1.NewFilesLoad(urls, roots);
            open.cn.awg.pro.core.NativeFiles.writeText(FileManagerActivity.this, (String) (cache00), (String) (roots));
            GlobalRuntimeState.d1_rootpath = roots;

            int sdk = android.os.Build.VERSION.SDK_INT;

            if (sdk >= 30) {

                e1.tsk("提示", "由于使用Android11的设备新增储存分区限制，本文件管理仅能访问\n\"" + roots + "\"");

            }

        } else {

            roots = open.cn.awg.pro.core.TextValues.trim(roots);
            roots = open.cn.awg.pro.core.NativeFiles.resolvePath(FileManagerActivity.this, (String) (roots));
            urls = open.cn.awg.pro.core.TextValues.trim(urls);
            urls = open.cn.awg.pro.core.NativeFiles.resolvePath(FileManagerActivity.this, (String) (urls));

            if (open.cn.awg.pro.core.TextValues.endsWith(roots, "/")) {

            } else {

                roots = roots + "/";

            }
            jmroot = roots;
            e1.NewFilesLoad(urls, roots);
            GlobalRuntimeState.d1_rootpath = roots;

        }

        String set = AppPaths.appPath("settings/set3.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(FileManagerActivity.this, (String) (set)), "true")) {

            String te = open.cn.awg.pro.core.NativeFiles.readText(FileManagerActivity.this, (String) (AppPaths.appPath("data/assets/a4")));

            String[] name = new String[]{"title", "text"};

            String[] value = new String[]{"新手教程", te};
            open.cn.awg.pro.core.NativeActions.launch(FileManagerActivity.this, (Class<?>) (TextQrDisplayActivity.class), (String[]) (name), (Object[]) (value));

        }

    }

    public void ts1(int a) {

        final String a041 = AppPaths.appPath("cache/tab");

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.xxbj3)).getVisibility() == android.view.View.GONE && ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.xxbj6)).getVisibility() == android.view.View.GONE && ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.xxbj7)).getVisibility() == android.view.View.GONE && ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.xxbj5)).getVisibility() == android.view.View.GONE) {

            if (a == 1) {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        open.cn.awg.pro.core.NativeFiles.writeText(FileManagerActivity.this, (String) (a041), (String) ("false"));

                    }

                });

            } else if (a == -1) {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        open.cn.awg.pro.core.NativeFiles.writeText(FileManagerActivity.this, (String) (a041), (String) ("true"));

                    }

                });

            } else {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        open.cn.awg.pro.core.NativeViewValues.height(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.ts1)), 0);
                        open.cn.awg.pro.core.NativeFiles.writeText(FileManagerActivity.this, (String) (a041), (String) ("false"));

                    }

                });

            }

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.file_manager_root, R.id.xdbj2, R.id.title_bar);

        final String a041 = AppPaths.appPath("cache/tab");
        open.cn.awg.pro.core.NativeFiles.writeText(FileManagerActivity.this, (String) (a041), (String) ("true"));

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

            String url = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.zywblj)).getText().toString();

            if (java.util.Objects.equals(url, jmroot)) {

                open.cn.awg.pro.core.NativeActions.finish(FileManagerActivity.this);

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

        if (open.cn.awg.pro.core.NativeFiles.exists(FileManagerActivity.this, (String) (cache_c)) && open.cn.awg.pro.core.NativeFiles.exists(FileManagerActivity.this, (String) (cache_es))) {

            e1.sx(open.cn.awg.pro.core.NativeFiles.readText(FileManagerActivity.this, (String) (cache_c)), open.cn.awg.pro.core.NativeFiles.readText(FileManagerActivity.this, (String) (cache_es)));
            open.cn.awg.pro.core.NativeFiles.delete(FileManagerActivity.this, (String) (cache_c), false);
            open.cn.awg.pro.core.NativeFiles.delete(FileManagerActivity.this, (String) (cache_es), false);

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

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(FileManagerActivity.this, (String) (set4)), "false")) {

            open.cn.awg.pro.core.NativeFiles.delete(FileManagerActivity.this, (String) (cache1), false);
            open.cn.awg.pro.core.NativeFiles.delete(FileManagerActivity.this, (String) (cache2), false);
            open.cn.awg.pro.core.NativeFiles.delete(FileManagerActivity.this, (String) (cache3), false);
            open.cn.awg.pro.core.NativeFiles.delete(FileManagerActivity.this, (String) (cache00), false);

        }

        String ftitle = AppPaths.appPath("cache/file/title");
        open.cn.awg.pro.core.NativeFiles.delete(FileManagerActivity.this, (String) (ftitle), false);

        String choose = AppPaths.appPath("cache/file/choose");
        open.cn.awg.pro.core.NativeFiles.delete(FileManagerActivity.this, (String) (choose), false);

    }

    public void onRestart() {
        super.onRestart();
            applyWindowModeFromSettings();

    }

    public void onStart() {
        super.onStart();
            applyWindowModeFromSettings();

        androidx.recyclerview.widget.RecyclerView v7lb1 = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.v7lb1));
        v7lb1.requestFocus();

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

    }

    public void onResume() {
        super.onResume();
            applyWindowModeFromSettings();

    }

    private void onTitleBarClick(View vw) {

        String r = titles;

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "文件管理") || java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), r)) {

            String url = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.zywblj)).getText().toString();

            if (java.util.Objects.equals(url, jmroot)) {

                open.cn.awg.pro.core.NativeActions.finish(FileManagerActivity.this);

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

        int lwz = open.cn.awg.pro.core.NativeViewValues.lastVisible(((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.v7lb1)));

        String cpath = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.zywblj)).getText().toString();

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

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

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

            String bzf = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.bjk1)).getText().toString();

            String cache3 = AppPaths.appPath("cache/3");

            String cachec1 = AppPaths.appPath("cache/c1");

            String cachec2 = AppPaths.appPath("cache/c2");

            String cachec3 = AppPaths.appPath("cache/c3");

            String wa = open.cn.awg.pro.core.NativeFiles.readText(FileManagerActivity.this, (String) (cache3));

            String wb = open.cn.awg.pro.core.NativeFiles.readText(FileManagerActivity.this, (String) (cachec1));

            String wc = open.cn.awg.pro.core.NativeFiles.readText(FileManagerActivity.this, (String) (cachec2));

            String wd = open.cn.awg.pro.core.NativeFiles.readText(FileManagerActivity.this, (String) (cachec3));

            AppRuntimeBridge e1 = new AppRuntimeBridge(FileManagerActivity.this);

            String[] aa;
            bzf = open.cn.awg.pro.core.TextValues.trim(bzf);

            if (java.util.Objects.equals(bzf, "") || java.util.Objects.equals(bzf, null)) {

                final String z = "编辑框不能为空";
                open.cn.awg.pro.core.NativeActions.toast(FileManagerActivity.this, z);

            } else {

                String we = wd + bzf;

                if (java.util.Objects.equals(wa, "重命名")) {

                    if (java.util.Objects.equals(wc, we)) {

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

                        if (open.cn.awg.pro.core.TextValues.startsWith(we, pb1) || open.cn.awg.pro.core.TextValues.startsWith(we, pb2) || open.cn.awg.pro.core.TextValues.startsWith(we, pb3) || open.cn.awg.pro.core.TextValues.startsWith(we, pb4) || open.cn.awg.pro.core.TextValues.startsWith(we, pb5) || (android.os.Build.VERSION.SDK_INT > 29 && open.cn.awg.pro.core.TextValues.startsWith(we, pb6)) || (android.os.Build.VERSION.SDK_INT > 29 && open.cn.awg.pro.core.TextValues.startsWith(we, pb7))) {

                            e1.tsk("提示", "非法操作");

                        } else {

                            String m = "mv \"" + wc + "\" \"" + we + "\"";
                            aa = com.demo.e3.cmd(lei, m, false);

                            if (java.util.Objects.equals(aa[0], "") && !java.util.Objects.equals(aa[1], "")) {

                                final String z = "重命名失败\n" + aa[1];
                                e1.tsk("提示", z);

                            } else if (!java.util.Objects.equals(aa[0], "") && java.util.Objects.equals(aa[1], "")) {

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

                } else if (java.util.Objects.equals(wa, "新建目录")) {

                    String pb1 = AppPaths.appPath("settings");

                    String pb2 = AppPaths.appPath("data");

                    String pb3 = AppPaths.appPath("fix");

                    String pb4 = AppPaths.appPath("cache");

                    String pb5 = AppPaths.appPath("files/cache");

                    String pb6 = "/storage/emulated/0/Android/data";

                    String pb7 = "/storage/emulated/0/Android/obb";

                    if (open.cn.awg.pro.core.TextValues.startsWith(we, pb1) || open.cn.awg.pro.core.TextValues.startsWith(we, pb2) || open.cn.awg.pro.core.TextValues.startsWith(we, pb3) || open.cn.awg.pro.core.TextValues.startsWith(we, pb4) || open.cn.awg.pro.core.TextValues.startsWith(we, pb5) || (android.os.Build.VERSION.SDK_INT > 29 && open.cn.awg.pro.core.TextValues.startsWith(we, pb6)) || (android.os.Build.VERSION.SDK_INT > 29 && open.cn.awg.pro.core.TextValues.startsWith(we, pb7))) {

                        e1.tsk("提示", "非法操作");

                    } else {

                        String m = "mkdir \"" + we + "\"";
                        aa = com.demo.e3.cmd(lei, m, false);

                        if (java.util.Objects.equals(aa[0], "") && !java.util.Objects.equals(aa[1], "")) {

                            final String z = "新建目录失败\n" + aa[1];
                            e1.tsk("提示", z);

                        } else if (!java.util.Objects.equals(aa[0], "") && java.util.Objects.equals(aa[1], "")) {

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

                } else if (java.util.Objects.equals(wa, "新建文件")) {

                    String pb1 = AppPaths.appPath("settings");

                    String pb2 = AppPaths.appPath("data");

                    String pb3 = AppPaths.appPath("fix");

                    String pb4 = AppPaths.appPath("cache");

                    String pb5 = AppPaths.appPath("files/cache");

                    String pb6 = "/storage/emulated/0/Android/data";

                    String pb7 = "/storage/emulated/0/Android/obb";

                    if (open.cn.awg.pro.core.TextValues.startsWith(we, pb1) || open.cn.awg.pro.core.TextValues.startsWith(we, pb2) || open.cn.awg.pro.core.TextValues.startsWith(we, pb3) || open.cn.awg.pro.core.TextValues.startsWith(we, pb4) || open.cn.awg.pro.core.TextValues.startsWith(we, pb5) || (android.os.Build.VERSION.SDK_INT > 29 && open.cn.awg.pro.core.TextValues.startsWith(we, pb6)) || (android.os.Build.VERSION.SDK_INT > 29 && open.cn.awg.pro.core.TextValues.startsWith(we, pb7))) {

                        e1.tsk("提示", "非法操作");

                    } else {

                        String m = "touch \"" + we + "\"";
                        aa = com.demo.e3.cmd(lei, m, false);

                        if (java.util.Objects.equals(aa[0], "") && !java.util.Objects.equals(aa[1], "")) {

                            final String z = "新建文件失败\n" + aa[1];
                            e1.tsk("提示", z);

                        } else if (!java.util.Objects.equals(aa[0], "") && java.util.Objects.equals(aa[1], "")) {

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
            open.cn.awg.pro.core.NativeActions.finish(FileManagerActivity.this);

        }

    }

    private boolean onXxbj9Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onTx3Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.xxbj10)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.xxbj10)).setVisibility(android.view.View.GONE);

        } else {

            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.xxbj10)).setVisibility(android.view.View.VISIBLE);

        }
        open.cn.awg.pro.core.NativeActions.finish(FileManagerActivity.this);

    }

    private void onTx4Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.xxbj10)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.xxbj10)).setVisibility(android.view.View.GONE);

        } else {

            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(FileManagerActivity.this.getWindow().getDecorView(), R.id.xxbj10)).setVisibility(android.view.View.VISIBLE);

        }

    }

    private boolean onXxbj10Touch(View vw, MotionEvent me) {
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);
        d26d5d7080.setOnLongClickListener(titleBarLongClickListener);

        RecyclerView f085d7c0f8 = (RecyclerView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.v7lb1);
        f085d7c0f8.addOnScrollListener(v7lb1ScrollListener);

        LinearLayout dc0a1b897b = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj2);
        dc0a1b897b.setOnTouchListener(xxbj2TouchListener);

        LinearLayout efa568dfe6 = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj3);
        efa568dfe6.setOnTouchListener(xxbj3TouchListener);

        LinearLayout i6c4a6b953f = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj6);
        i6c4a6b953f.setOnTouchListener(xxbj6TouchListener);

        LinearLayout i84f04cd47e = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj7);
        i84f04cd47e.setOnTouchListener(xxbj7TouchListener);

        LinearLayout bd584e50cd = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj5);
        bd584e50cd.setOnTouchListener(xxbj5TouchListener);

        EditText i32ee139ba1 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        LinearLayout i9926c2f556 = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj9);
        i9926c2f556.setOnTouchListener(xxbj9TouchListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i11aeec1890 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx4);
        i11aeec1890.setOnClickListener(tx4ClickListener);

        LinearLayout i464e6cc6fb = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj10);
        i464e6cc6fb.setOnTouchListener(xxbj10TouchListener);

        TextView d0ffa028ae = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bt);

        TextView i203811ac10 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.zywblj);

        LinearLayout e1433e26d6 = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj1);

        __layoutIsLoaded(ay, vw);
    }
}
