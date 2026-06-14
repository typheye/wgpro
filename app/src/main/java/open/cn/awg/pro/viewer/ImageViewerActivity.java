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
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
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
        setContentView(R.layout.image_viewer);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        try {

            md5 = sj.hqtz("md5");
            url = sj.hqtz("url");

            if (zf.dy(md5, "")) {

                if (!zf.dy(url, "")) {

                    load();

                } else {

                    e1.tsk("提示", "参数不全");
                    gj.gb();

                }

            } else {

                final String pa = AppPaths.externalFilesPath("Chat/Pictures/") + md5;

                if (zf.ckt(url, "http")) {

                    if (wj.cz(pa) && wj.dx(pa) != 0) {

                        url = pa;
                        load();

                    } else {

                        gj.xc(new Thread() {

                            public void run() {

                                int zt = wl.xz(url, pa, true, null, "utf-8", null, true, "User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept=*/*||accept-language=zh-CN");

                                if (zt == -1) {

                                    e1.tsk("提示", "缓存图片失败");
                                    gj.gb();

                                } else {

                                    final String[] name = new String[]{"url", "md5"};

                                    final String[] value = new String[]{url, md5};
                                    gj.jmxc(new Runnable() {

                                        public void run() {

                                            gj.gb();
                                            gj.tz(ImageViewerActivity.class, name, value);

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
            gj.gb();

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        i.runlibrary.app.xt$pm pm = (i.runlibrary.app.xt$pm) xt.pm();

        if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4s.inf")), "false")) {

            if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4.inf")), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }
        xt.sbxm(false);

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb(AppPaths.appPath("settings/f17/set1.inf")));
        applyFullscreenWindow();

        if (zf.dy(a, "1")) {

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

        } else if (zf.dy(a, "3")) {

            i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "4")) {

            int uih = sj.zh().pxzdp(pm.ztl);
            uih = uih + 9;

            i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
            wtab.nbj(0, uih + "dp", 0, "9dp");
            wtab.dqfs("center");

        }

        String set = AppPaths.appPath("settings/set3.inf");

        if (zf.dy(wj.dqwb(set), "true")) {

            String te = wj.dqwb(AppPaths.appPath("data/assets/a6"));

            String[] name = new String[]{"title", "text"};

            String[] value = new String[]{"新手教程", te};
            gj.tz(TextQrDisplayActivity.class, name, value);

        }

    }

    public void load() {

        if (wj.cz(url)) {

            String[] fhValue = e1.fileinfo("%", url);

            String bts = fhValue[3];
            st.xxbj(R.id.xxbj2).kjd(8);

            LinearLayout xx = findViewById(R.id.xx);
            com.demo.e5 pic = new com.demo.e5(this);
            xx.addView(pic, -1, -1);
            Uri uri = Uri.fromFile(new File(url));
            pic.setImageURI(uri);

        } else {

            e1.tsk("提示", "文件不存在");
            gj.gb();

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
        gj.gb();

    }

    private boolean onWb2LongClick(View vw) {

        if (st.wb(R.id.title_bar).kjd() == 0) {

            st.wb(R.id.title_bar).kjd(8);

        } else {

            st.wb(R.id.title_bar).kjd(0);

        }
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        LinearLayout bfde3e272b = (LinearLayout) findViewById(ay, vw, R.id.xx);

        RelativeLayout aaeebbd136 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj1);

        TextView i7c3e3c6b5d = (TextView) findViewById(ay, vw, R.id.wb2);
        i7c3e3c6b5d.setOnLongClickListener(wb2LongClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
