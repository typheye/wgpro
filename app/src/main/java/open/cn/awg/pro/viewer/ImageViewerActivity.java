/**
 ******************************************************************************
 * @file    ImageViewerActivity.java
 * @author  Typheye
 * @brief   Image preview screen.
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


import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import java.io.File;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;

public class ImageViewerActivity extends BaseAwgActivity {

    public final ImageViewerActivity lei = this, 类 = this;
    public String url;
    public String md5;
    public AppRuntimeBridge e1;
    private final View.OnLongClickListener wb2LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onWb2LongClick(vw);
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
        setContentView(R.layout.image_viewer);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        try {

            md5 = ImageViewerActivity.this.getIntent().getStringExtra(String.valueOf("md5"));
            url = ImageViewerActivity.this.getIntent().getStringExtra(String.valueOf("url"));

            if (java.util.Objects.equals(md5, "")) {

                if (!java.util.Objects.equals(url, "")) {

                    load();

                } else {

                    e1.tsk("提示", "参数不全");
                    open.cn.awg.pro.core.NativeActions.finish(ImageViewerActivity.this);

                }

            } else {

                final String pa = AppPaths.externalFilesPath("Chat/Pictures/") + md5;

                if (open.cn.awg.pro.core.TextValues.startsWith(url, "http")) {

                    if (open.cn.awg.pro.core.NativeFiles.exists(ImageViewerActivity.this, (String) (pa)) && open.cn.awg.pro.core.NativeFiles.size(ImageViewerActivity.this, (String) (pa)) != 0) {

                        url = pa;
                        load();

                    } else {

                        open.cn.awg.pro.core.NativeActions.start(new Thread() {

                            public void run() {

                                int zt = open.cn.awg.pro.core.NativeHttp.download(ImageViewerActivity.this, String.valueOf(url), (String) (pa), java.lang.Boolean.TRUE.equals(true), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), (String) ("User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept=*/*||accept-language=zh-CN"), 0, 0, null);

                                if (zt == -1) {

                                    e1.tsk("提示", "缓存图片失败");
                                    open.cn.awg.pro.core.NativeActions.finish(ImageViewerActivity.this);

                                } else {

                                    final String[] name = new String[]{"url", "md5"};

                                    final String[] value = new String[]{url, md5};
                                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                        public void run() {

                                            open.cn.awg.pro.core.NativeActions.finish(ImageViewerActivity.this);
                                            open.cn.awg.pro.core.NativeActions.launch(ImageViewerActivity.this, (Class<?>) (ImageViewerActivity.class), (String[]) (name), (Object[]) (value));

                                        }

                                    });

                                }

                            }

                        });

                    }

                } else {

                    load();

                }

            }

        } catch (Throwable e) {

            e1.upload_error(e, "d3.*");
            open.cn.awg.pro.core.NativeActions.finish(ImageViewerActivity.this);

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ImageViewerActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ImageViewerActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }
        open.cn.awg.pro.core.NativeDevice.setKeepScreenOn(ImageViewerActivity.this, !(false));

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(ImageViewerActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(ImageViewerActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));
        applyFullscreenWindow();

        if (java.util.Objects.equals(a, "1")) {

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ImageViewerActivity.this.getWindow().getDecorView(), R.id.title_bar));
            open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "15dp", 0, "3dp");
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "2")) {

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (open.cn.awg.pro.core.NativeFiles.exists(ImageViewerActivity.this, (String) (a001)) || java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ImageViewerActivity.this, (String) (a001)), "true")) {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ImageViewerActivity.this.getWindow().getDecorView(), R.id.title_bar));
                open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "3dp", 0, "3dp");
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            } else {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ImageViewerActivity.this.getWindow().getDecorView(), R.id.title_bar));
                open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "9dp", 0, "9dp");
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            }

        } else if (java.util.Objects.equals(a, "3")) {

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ImageViewerActivity.this.getWindow().getDecorView(), R.id.title_bar));
            open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "15dp", 0, "3dp");
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "4")) {

            int uih = open.cn.awg.pro.core.DisplayValues.pxToDp(ImageViewerActivity.this, open.cn.awg.pro.core.DisplayValues.systemBarHeight(ImageViewerActivity.this, "status_bar_height"));
            uih = uih + 9;

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ImageViewerActivity.this.getWindow().getDecorView(), R.id.title_bar));
            open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, uih + "dp", 0, "9dp");
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        }

        String set = AppPaths.appPath("settings/set3.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ImageViewerActivity.this, (String) (set)), "true")) {

            String te = open.cn.awg.pro.core.NativeFiles.readText(ImageViewerActivity.this, (String) (AppPaths.appPath("data/assets/a6")));

            String[] name = new String[]{"title", "text"};

            String[] value = new String[]{"新手教程", te};
            open.cn.awg.pro.core.NativeActions.launch(ImageViewerActivity.this, (Class<?>) (TextQrDisplayActivity.class), (String[]) (name), (Object[]) (value));

        }

    }

    public void load() {

        if (open.cn.awg.pro.core.NativeFiles.exists(ImageViewerActivity.this, (String) (url))) {

            String[] fhValue = e1.fileinfo("%", url);

            String bts = fhValue[3];
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ImageViewerActivity.this.getWindow().getDecorView(), R.id.xxbj2)).setVisibility(android.view.View.GONE);

            LinearLayout xx = findViewById(R.id.xx);
            com.demo.e5 pic = new com.demo.e5(this);
            xx.addView(pic, -1, -1);
            Uri uri = Uri.fromFile(new File(url));
            pic.setImageURI(uri);

        } else {

            e1.tsk("提示", "文件不存在");
            open.cn.awg.pro.core.NativeActions.finish(ImageViewerActivity.this);

        }

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
        applyFullscreenWindow();

    }

    public void onRestart() {
        super.onRestart();
        applyFullscreenWindow();

    }

    public void onStart() {
        super.onStart();
        applyFullscreenWindow();

    }

    public void onResume() {
        super.onResume();
        applyFullscreenWindow();

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
        open.cn.awg.pro.core.NativeActions.finish(ImageViewerActivity.this);

    }

    private boolean onWb2LongClick(View vw) {

        if (((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ImageViewerActivity.this.getWindow().getDecorView(), R.id.title_bar)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ImageViewerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setVisibility(android.view.View.GONE);

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ImageViewerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setVisibility(android.view.View.VISIBLE);

        }
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        LinearLayout bfde3e272b = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xx);

        RelativeLayout aaeebbd136 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj1);

        TextView i7c3e3c6b5d = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb2);
        i7c3e3c6b5d.setOnLongClickListener(wb2LongClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
