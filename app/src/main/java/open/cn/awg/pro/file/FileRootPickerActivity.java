/**
 ******************************************************************************
 * @file    FileRootPickerActivity.java
 * @author  Typheye
 * @brief   Lists available file roots and storage locations.
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

import android.view.Window;

import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.ui.list.FileRootItemActivity;


public class FileRootPickerActivity extends iActivity {

    public final FileRootPickerActivity lei = this, 类 = this;
    public open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

    public i.runlibrary.app.v.v7lb$UserAdapter lbspq = null;
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i464e6cc6fb = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i464e6cc6fb(vw, me);
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
        setContentView(open.cn.awg.pro.R.layout.d7);
        _$_viewAutomaticSettingEvent();
        list();

    }

    public void list() {

        final java.lang.String spxx = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        i.runlibrary.app.v.v7lb lb = st.v7lb(open.cn.awg.pro.R.id.v7lb1);
        lbspq = lb.v7lbspq(FileRootItemActivity.class, open.cn.awg.pro.R.layout.c13, new i.runlibrary.app.v.v7lb$OnUserAdapterView() {

            public void getView(i.runlibrary.app.v.v7lb$UserAdapter ua, int pn, android.view.View vw) {

                i.runlibrary.app.sj$lb lbsj = sj.lb(vw);

                int f = sj.zh(lbsj.lbsj(pn, -1)).zint();

                if (f == -1) {

                    st.xdbj(vw, open.cn.awg.pro.R.id.xdbj1).kjd(8);
                    st.xdbj(vw, open.cn.awg.pro.R.id.xdbj1).xydj(false);
                    st.xdbj(vw, open.cn.awg.pro.R.id.xdbj1).xyca(false);
                    st.wb(vw, open.cn.awg.pro.R.id.dp20).kjd(0);

                } else {

                    st.xdbj(vw, open.cn.awg.pro.R.id.xdbj1).kjd(0);
                    st.xdbj(vw, open.cn.awg.pro.R.id.xdbj1).xydj(true);
                    st.xdbj(vw, open.cn.awg.pro.R.id.xdbj1).xyca(true);
                    st.wb(vw, open.cn.awg.pro.R.id.dp20).kjd(8);

                }

            }

        });
        lbspq.sc();

        java.lang.String rootpath = wj.hqml("%");

        int[] ids2 = new int[]{open.cn.awg.pro.R.id.wb1, open.cn.awg.pro.R.id.wb3, open.cn.awg.pro.R.id.wb2, -1};

        java.lang.Object[] values2 = new java.lang.Object[]{"", "", "a1", 0};

        int sdk = xt.sbxx().sdk;

        if (sdk < 30) {

            values2 = new java.lang.Object[]{"内部存储", rootpath, "a1", 0};
            lbspq.j(ids2, values2);
            values2 = new java.lang.Object[]{"用户相册", rootpath + "DCIM/", "a1", 0};
            lbspq.j(ids2, values2);
            values2 = new java.lang.Object[]{"用户文档", rootpath + "Documents/", "a1", 0};
            lbspq.j(ids2, values2);
            values2 = new java.lang.Object[]{"用户下载", rootpath + "Download/", "a1", 0};
            lbspq.j(ids2, values2);
            values2 = new java.lang.Object[]{"用户视频", rootpath + "Movies/", "a1", 0};
            lbspq.j(ids2, values2);
            values2 = new java.lang.Object[]{"用户音乐", rootpath + "Music/", "a1", 0};
            lbspq.j(ids2, values2);
            values2 = new java.lang.Object[]{"用户图片", rootpath + "Pictures/", "a1", 0};
            lbspq.j(ids2, values2);

        } else {

            values2 = new java.lang.Object[]{"内部存储", "/storage/emulated/0/", "a2", 0};
            lbspq.j(ids2, values2);
            values2 = new java.lang.Object[]{"用户相册", "/storage/emulated/0/DCIM/", "a2", 0};
            lbspq.j(ids2, values2);
            values2 = new java.lang.Object[]{"用户文档", "/storage/emulated/0/Documents/", "a2", 0};
            lbspq.j(ids2, values2);
            values2 = new java.lang.Object[]{"用户下载", "/storage/emulated/0/Download/", "a2", 0};
            lbspq.j(ids2, values2);
            values2 = new java.lang.Object[]{"用户视频", "/storage/emulated/0/Movies/", "a2", 0};
            lbspq.j(ids2, values2);
            values2 = new java.lang.Object[]{"用户音乐", "/storage/emulated/0/Music/", "a2", 0};
            lbspq.j(ids2, values2);
            values2 = new java.lang.Object[]{"用户图片", "/storage/emulated/0/Pictures/", "a2", 0};
            lbspq.j(ids2, values2);

        }
        values2 = new java.lang.Object[]{"挂载目录", "/mnt/", "a0", 0};
        lbspq.j(ids2, values2);
        values2 = new java.lang.Object[]{"系统目录", "/system/", "a0", 0};
        lbspq.j(ids2, values2);
        values2 = new java.lang.Object[]{"应用目录", "/storage/emulated/0/Android/data/open.cn.awg.pro/", "a0", 0};
        lbspq.j(ids2, values2);
        values2 = new java.lang.Object[]{"应用目录", "/data/user/0/open.cn.awg.pro/", "a0", 0};
        values2 = new java.lang.Object[]{"", "", "", -1};
        lbspq.j(ids2, values2);

        java.lang.String axc = wj.dqwb(spxx);

        if (zf.dy(axc, "1") || zf.dy(axc, "3")) {

            lbspq.j(ids2, values2);
            lbspq.j(ids2, values2);

        }
        lbspq.sx();

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

            st.xdbj(open.cn.awg.pro.R.id.d7_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.d7_v).shxtck(false);

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

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.app.AwgProApplication.getInstance().removeActivity(lei);

        java.lang.String cache1 = "/data/user/0/open.cn.awg.pro/cache/1";

        java.lang.String cache2 = "/data/user/0/open.cn.awg.pro/cache/2";

        java.lang.String cache3 = "/data/user/0/open.cn.awg.pro/cache/3";

        java.lang.String set4 = "/data/user/0/open.cn.awg.pro/settings/d1/set4.inf";

        if (zf.dy(wj.dqwb(set4), "false")) {

            wj.sc(cache1);
            wj.sc(cache2);
            wj.sc(cache3);

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

        i.runlibrary.app.v.v7lb v7lb1 = st.v7lb(open.cn.awg.pro.R.id.v7lb1);
        v7lb1.v.requestFocus();

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
        gj.gb();

    }

    private boolean $_onLongClick_d26d5d7080(android.view.View vw) {
        return true;

    }

    private void $_onClick_i34dc7bf20a(android.view.View vw) {

        if (st.xxbj(open.cn.awg.pro.R.id.xxbj10).kjd() == 0) {

            st.xxbj(open.cn.awg.pro.R.id.xxbj10).kjd(8);

        } else {

            st.xxbj(open.cn.awg.pro.R.id.xxbj10).kjd(0);

        }
        gj.gb();

    }

    private void $_onClick_i4d951cdb0e(android.view.View vw) {

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

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
        i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

        android.widget.LinearLayout i464e6cc6fb = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj10);
        i464e6cc6fb.setOnTouchListener($_on_setOnTouchListener_i464e6cc6fb);

        __layoutIsLoaded(ay, vw);
    }
}
