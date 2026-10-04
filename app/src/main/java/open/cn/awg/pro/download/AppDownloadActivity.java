/**
 ******************************************************************************
 * @file    AppDownloadActivity.java
 * @author  Typheye
 * @brief   Application download screen.
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
package open.cn.awg.pro.download;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import java.io.File;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.file.FileManagerActivity;
import open.cn.awg.pro.R;

public class AppDownloadActivity extends BaseAwgActivity {

    public final AppDownloadActivity lei = this, 类 = this;
    public AppRuntimeBridge e1;
    public File f;
    public String ty = "";
    public String appid = "";
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnClickListener an1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn1Click(vw);
        }

    };
    private final AdapterView.OnItemSelectedListener xlcd1ItemSelectedListener = new AdapterView.OnItemSelectedListener() {

        public void onItemSelected(AdapterView vw, View view, int pn, long id) {
            onXlcd1ItemSelected(vw, view, pn, id);
        }

        public void onNothingSelected(AdapterView vw) {
            onXlcd1NothingSelected(vw);
        }

    };
    private final View.OnTouchListener bjk1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjk1Touch(vw, me);
        }

    };
    private final View.OnClickListener an4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn4Click(vw);
        }

    };
    private final View.OnClickListener an3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn3Click(vw);
        }

    };
    private final View.OnClickListener an2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn2Click(vw);
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
        setContentView(R.layout.app_download);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        String shs = AppDownloadActivity.this.getIntent().getStringExtra(String.valueOf("url"));
        shs = open.cn.awg.pro.core.TextValues.trim(shs);
        ty = AppDownloadActivity.this.getIntent().getStringExtra(String.valueOf("type"));
        appid = AppDownloadActivity.this.getIntent().getStringExtra(String.valueOf("appid"));

        if (!java.util.Objects.equals(shs, null) && !java.util.Objects.equals(shs, "")) {

            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(shs));
            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.bjk1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

        } else {

            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.bjk1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

        }

        if (java.util.Objects.equals(ty, "app")) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("< 应用下载"));
            ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.an2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
            ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.an3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

            String path = AppPaths.externalFilesPath("app/download/") + appid + "/base.apk";

            if (open.cn.awg.pro.core.NativeFiles.exists(AppDownloadActivity.this, (String) (path))) {

                ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.an4)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

            }

        }

        android.widget.Spinner xlcd1 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.xlcd1));

        if (java.util.Objects.equals(ty, "app")) {

            String[] sz = new String[]{"使用系统下载器", "使用软件下载器"};
            open.cn.awg.pro.core.NativeViewValues.spinner(xlcd1, sz);

        } else {

            String[] sz = new String[]{"使用系统下载器", "使用系统下载器[自定义]", "使用软件下载器"};
            open.cn.awg.pro.core.NativeViewValues.spinner(xlcd1, sz);

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.app_download_root, R.id.xdbj1, R.id.title_bar);

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

    private void onTitleBarClick(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(AppDownloadActivity.this);

    }

    private void onAn2Click(View vw) {

        String ftitle = AppPaths.appPath("cache/file/title");
        open.cn.awg.pro.core.NativeFiles.writeText(AppDownloadActivity.this, (String) (ftitle), (String) ("< 下载目录"));

        String path = AppPaths.externalFilesPath("Download/");

        final String[] name = new String[]{"url", "root"};

        final String[] value = new String[]{path, path};
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.launch(AppDownloadActivity.this, (Class<?>) (FileManagerActivity.class), (String[]) (name), (Object[]) (value));

            }

        });

    }

    private void onAn3Click(View vw) {

        String ftitle = AppPaths.appPath("cache/file/title");
        open.cn.awg.pro.core.NativeFiles.writeText(AppDownloadActivity.this, (String) (ftitle), (String) ("< 下载目录"));

        String path = "/storage/emulated/0/Download/";

        final String[] name = new String[]{"url", "root"};

        final String[] value = new String[]{path, path};
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.launch(AppDownloadActivity.this, (Class<?>) (FileManagerActivity.class), (String[]) (name), (Object[]) (value));

            }

        });

    }

    private void onAn4Click(View vw) {

        String ftitle = AppPaths.appPath("cache/file/title");
        open.cn.awg.pro.core.NativeFiles.writeText(AppDownloadActivity.this, (String) (ftitle), (String) ("< 下载目录"));

        String path = AppPaths.externalFilesPath("app/download/") + appid + "/";
        open.cn.awg.pro.core.NativeFiles.writeText(AppDownloadActivity.this, (String) (path + ".nomedia"), (String) (""));
        open.cn.awg.pro.core.NativeFiles.delete(AppDownloadActivity.this, (String) (path + ".nomedia"), false);

        final String[] name = new String[]{"url", "root"};

        final String[] value = new String[]{path, path};
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.launch(AppDownloadActivity.this, (Class<?>) (FileManagerActivity.class), (String[]) (name), (Object[]) (value));

            }

        });

    }

    private boolean onBjk1Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private void onXlcd1ItemSelected(AdapterView vw, View view, int pn, long id) {

        if (java.util.Objects.equals(ty, "app")) {

            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.bjk2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

        } else {

            if (pn == 1 || pn == 2) {

                String path2 = AppPaths.externalFilesPath("Download/");

                String path1 = "/storage/emulated/0/Download/";

                String add = ".file";

                int sdk = android.os.Build.VERSION.SDK_INT;

                if (sdk < 30) {

                    ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.bjk2)).setText(String.valueOf(path1 + open.cn.awg.pro.core.NativeDevice.formatDate(4) + add));

                } else {

                    ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.bjk2)).setText(String.valueOf(path2 + open.cn.awg.pro.core.NativeDevice.formatDate(4) + add));

                }
                ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.bjk2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

            } else {

                ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.bjk2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

            }

        }

    }

    private void onXlcd1NothingSelected(AdapterView vw) {

    }

    private void onAn1Click(View vw) {

        String bjk1 = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.bjk1)).getText().toString();
        bjk1 = open.cn.awg.pro.core.TextValues.trim(bjk1);

        String bjk2 = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.bjk2)).getText().toString();
        bjk2 = open.cn.awg.pro.core.TextValues.trim(bjk2);

        android.widget.Spinner xlcd1 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.xlcd1));

        if (java.util.Objects.equals(bjk1, "")) {

            e1.tsk("提示", "文件下载链接不能为空");

        } else {

            if (java.util.Objects.equals(e1.urlLockString(bjk1), "0")) {

                e1.tsk("提示", "文件下载链接无效");

            } else {

                

                if (java.util.Objects.equals(ty, "app")) {

                    if (xlcd1.getLastVisiblePosition() == 0) {

                        String path = AppPaths.externalFilesPath("app/download/") + appid + "/base.apk";
                        f = open.cn.awg.pro.core.NativeFiles.file(AppDownloadActivity.this, (String) (path));
                        gl.xz.download(bjk1, this, 1, f);
                        e1.tsk("提示", "已在后台开始下载");
                        ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.xlcd1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                        ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.an1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                        ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.an4)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                    } else if (xlcd1.getLastVisiblePosition() == 1) {

                        bjk2 = AppPaths.externalFilesPath("app/download/") + appid + "/base.apk";

                        String[] name = new String[]{"url", "path"};

                        String[] value = new String[]{bjk1, bjk2};
                        open.cn.awg.pro.core.NativeActions.launch(AppDownloadActivity.this, (Class<?>) (DownloadProgressActivity.class), (String[]) (name), (Object[]) (value));
                        ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.xlcd1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                        ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.an1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                        ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(AppDownloadActivity.this.getWindow().getDecorView(), R.id.an4)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                    }

                } else {

                    if (xlcd1.getLastVisiblePosition() == 0) {

                        gl.xz.download(bjk1, this, 0, f);
                        e1.tsk("提示", "已在后台开始下载");
                        open.cn.awg.pro.core.NativeActions.finish(AppDownloadActivity.this);

                    } else if (xlcd1.getLastVisiblePosition() == 1) {

                        if (java.util.Objects.equals(bjk2, "")) {

                            e1.tsk("提示", "文件保存路径不能为空");

                        } else {

                            f = open.cn.awg.pro.core.NativeFiles.file(AppDownloadActivity.this, (String) (bjk2));
                            gl.xz.download(bjk1, this, 1, f);
                            e1.tsk("提示", "已在后台开始下载");
                            open.cn.awg.pro.core.NativeActions.finish(AppDownloadActivity.this);

                        }

                    } else if (xlcd1.getLastVisiblePosition() == 2) {

                        if (java.util.Objects.equals(bjk2, "")) {

                            e1.tsk("提示", "文件保存路径不能为空");

                        } else {

                            String[] name = new String[]{"url", "path"};

                            String[] value = new String[]{bjk1, bjk2};
                            open.cn.awg.pro.core.NativeActions.launch(AppDownloadActivity.this, (Class<?>) (DownloadProgressActivity.class), (String[]) (name), (Object[]) (value));
                            open.cn.awg.pro.core.NativeActions.finish(AppDownloadActivity.this);

                        }

                    }

                }

            }

        }

    }

    private void onTx1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(AppDownloadActivity.this);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        Button i0b66a667b1 = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an2);
        i0b66a667b1.setOnClickListener(an2ClickListener);

        Button i8f51a35692 = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an3);
        i8f51a35692.setOnClickListener(an3ClickListener);

        Button i74ce6319f3 = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an4);
        i74ce6319f3.setOnClickListener(an4ClickListener);

        EditText i32ee139ba1 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        Spinner i004a739c93 = (Spinner) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xlcd1);
        i004a739c93.setOnItemSelectedListener(xlcd1ItemSelectedListener);

        Button i171ea34f1a = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
