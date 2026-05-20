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

import android.net.Uri;
import android.view.Window;

import com.ypz.bangscreentools.BangScreenTools;

import java.io.File;

import i.app.iActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.R;
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;


public class ImageViewerActivity extends iActivity {

    public final ImageViewerActivity lei = this, 类 = this;
    public java.lang.String url;
    public java.lang.String md5;
    public open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i7c3e3c6b5d = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i7c3e3c6b5d(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d26d5d7080(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.d3);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.app.AwgProApplication.getInstance().addActivity(lei);

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

                final java.lang.String pa = "/storage/emulated/0/Android/data/open.cn.awg.pro/files/Chat/Pictures/" + md5;

                if (zf.ckt(url, "http")) {

                    if (wj.cz(pa) && wj.dx(pa) != 0) {

                        url = pa;
                        load();

                    } else {

                        gj.xc(new java.lang.Thread() {

                            public void run() {

                                int zt = wl.xz(url, pa, true, null, "utf-8", null, true, "User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept=*/*||accept-language=zh-CN");

                                if (zt == -1) {

                                    e1.tsk("提示", "缓存图片失败");
                                    gj.gb();

                                } else {

                                    final java.lang.String[] name = new java.lang.String[]{"url", "md5"};

                                    final java.lang.String[] value = new java.lang.String[]{url, md5};
                                    gj.jmxc(new java.lang.Runnable() {

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

        } catch (java.lang.Throwable e) {

            e1.upload_error(e, "d3.*");
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
        xt.sbxm(false);

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        if (zf.dy(a, "1")) {

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

        } else if (zf.dy(a, "3")) {

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "4")) {

            int uih = sj.zh().pxzdp(pm.ztl);
            uih = uih + 9;

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, uih + "dp", 0, "9dp");
            wtab.dqfs("center");

        }

        java.lang.String set = "/data/user/0/open.cn.awg.pro/settings/set3.inf";

        if (zf.dy(wj.dqwb(set), "true")) {

            java.lang.String te = wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a6");

            java.lang.String[] name = new java.lang.String[]{"title", "text"};

            java.lang.String[] value = new java.lang.String[]{"新手教程", te};
            gj.tz(TextQrDisplayActivity.class, name, value);

        }

    }

    public void load() {

        if (wj.cz(url)) {

            java.lang.String[] fhValue = e1.fileinfo("%", url);

            java.lang.String bts = fhValue[3];
            st.xxbj(open.cn.awg.pro.R.id.xxbj2).kjd(8);

            android.widget.LinearLayout xx = findViewById(R.id.xx);
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

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public void onRestart() {
        super.onRestart();

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public void onStart() {
        super.onStart();

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public void onResume() {
        super.onResume();

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

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
        gj.gb();

    }

    private boolean $_onLongClick_i7c3e3c6b5d(android.view.View vw) {

        if (st.wb(open.cn.awg.pro.R.id.Tab).kjd() == 0) {

            st.wb(open.cn.awg.pro.R.id.Tab).kjd(8);

        } else {

            st.wb(open.cn.awg.pro.R.id.Tab).kjd(0);

        }
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
        d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

        android.widget.LinearLayout bfde3e272b = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xx);

        android.widget.RelativeLayout aaeebbd136 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj1);

        android.widget.TextView i7c3e3c6b5d = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb2);
        i7c3e3c6b5d.setOnLongClickListener($_on_setOnLongClickListener_i7c3e3c6b5d);

        __layoutIsLoaded(ay, vw);
    }
}
