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

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.ui.list.FileRootItemActivity;

public class FileRootPickerActivity extends BaseAwgActivity {

    public final FileRootPickerActivity lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

    public i.runlibrary.app.v.v7lb$UserAdapter lbspq = null;
    private final View.OnTouchListener xxbj10TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj10Touch(vw, me);
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
        setContentView(R.layout.file_root_picker);
        _$_viewAutomaticSettingEvent();
        list();

    }

    public void list() {

        final String spxx = AppPaths.appPath("settings/a3.inf");

        i.runlibrary.app.v.v7lb lb = st.v7lb(R.id.v7lb1);
        lbspq = lb.v7lbspq(FileRootItemActivity.class, R.layout.file_root_item, new i.runlibrary.app.v.v7lb$OnUserAdapterView() {

            public void getView(i.runlibrary.app.v.v7lb$UserAdapter ua, int pn, View vw) {

                i.runlibrary.app.sj$lb lbsj = sj.lb(vw);

                int f = sj.zh(lbsj.lbsj(pn, -1)).zint();

                if (f == -1) {

                    st.xdbj(vw, R.id.xdbj1).kjd(8);
                    st.xdbj(vw, R.id.xdbj1).xydj(false);
                    st.xdbj(vw, R.id.xdbj1).xyca(false);
                    st.wb(vw, R.id.dp20).kjd(0);

                } else {

                    st.xdbj(vw, R.id.xdbj1).kjd(0);
                    st.xdbj(vw, R.id.xdbj1).xydj(true);
                    st.xdbj(vw, R.id.xdbj1).xyca(true);
                    st.wb(vw, R.id.dp20).kjd(8);

                }

            }

        });
        lbspq.sc();

        String rootpath = wj.hqml("%");

        int[] ids2 = new int[]{R.id.wb1, R.id.wb3, R.id.wb2, -1};

        Object[] values2 = new Object[]{"", "", "a1", 0};

        int sdk = xt.sbxx().sdk;

        if (sdk < 30) {

            values2 = new Object[]{"内部存储", rootpath, "a1", 0};
            lbspq.j(ids2, values2);
            values2 = new Object[]{"用户相册", rootpath + "DCIM/", "a1", 0};
            lbspq.j(ids2, values2);
            values2 = new Object[]{"用户文档", rootpath + "Documents/", "a1", 0};
            lbspq.j(ids2, values2);
            values2 = new Object[]{"用户下载", rootpath + "Download/", "a1", 0};
            lbspq.j(ids2, values2);
            values2 = new Object[]{"用户视频", rootpath + "Movies/", "a1", 0};
            lbspq.j(ids2, values2);
            values2 = new Object[]{"用户音乐", rootpath + "Music/", "a1", 0};
            lbspq.j(ids2, values2);
            values2 = new Object[]{"用户图片", rootpath + "Pictures/", "a1", 0};
            lbspq.j(ids2, values2);

        } else {

            values2 = new Object[]{"内部存储", "/storage/emulated/0/", "a2", 0};
            lbspq.j(ids2, values2);
            values2 = new Object[]{"用户相册", "/storage/emulated/0/DCIM/", "a2", 0};
            lbspq.j(ids2, values2);
            values2 = new Object[]{"用户文档", "/storage/emulated/0/Documents/", "a2", 0};
            lbspq.j(ids2, values2);
            values2 = new Object[]{"用户下载", "/storage/emulated/0/Download/", "a2", 0};
            lbspq.j(ids2, values2);
            values2 = new Object[]{"用户视频", "/storage/emulated/0/Movies/", "a2", 0};
            lbspq.j(ids2, values2);
            values2 = new Object[]{"用户音乐", "/storage/emulated/0/Music/", "a2", 0};
            lbspq.j(ids2, values2);
            values2 = new Object[]{"用户图片", "/storage/emulated/0/Pictures/", "a2", 0};
            lbspq.j(ids2, values2);

        }
        values2 = new Object[]{"挂载目录", "/mnt/", "a0", 0};
        lbspq.j(ids2, values2);
        values2 = new Object[]{"系统目录", "/system/", "a0", 0};
        lbspq.j(ids2, values2);
        values2 = new Object[]{"应用目录", AppPaths.externalAppPath("") + "/", "a0", 0};
        lbspq.j(ids2, values2);
        values2 = new Object[]{"应用目录", AppPaths.appPath(""), "a0", 0};
        values2 = new Object[]{"", "", "", -1};
        lbspq.j(ids2, values2);

        String axc = wj.dqwb(spxx);

        if (zf.dy(axc, "1") || zf.dy(axc, "3")) {

            lbspq.j(ids2, values2);
            lbspq.j(ids2, values2);

        }
        lbspq.sx();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.file_root_picker_root, R.id.xdbj2, R.id.title_bar);

        final String a041 = AppPaths.appPath("cache/tab");
        wj.xrwb(a041, "true");

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
            applyWindowModeFromSettings();

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

        String cache1 = AppPaths.appPath("cache/1");

        String cache2 = AppPaths.appPath("cache/2");

        String cache3 = AppPaths.appPath("cache/3");

        String set4 = AppPaths.appPath("settings/d1/set4.inf");

        if (zf.dy(wj.dqwb(set4), "false")) {

            wj.sc(cache1);
            wj.sc(cache2);
            wj.sc(cache3);

        }

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

    }

    public void onResume() {
        super.onResume();
            applyWindowModeFromSettings();

    }

    private void onTitleBarClick(View vw) {
        gj.gb();

    }

    private boolean onTitleBarLongClick(View vw) {
        return true;

    }

    private void onTx1Click(View vw) {

        if (st.xxbj(R.id.xxbj10).kjd() == 0) {

            st.xxbj(R.id.xxbj10).kjd(8);

        } else {

            st.xxbj(R.id.xxbj10).kjd(0);

        }
        gj.gb();

    }

    private void onTx2Click(View vw) {

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

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        LinearLayout i464e6cc6fb = (LinearLayout) findViewById(ay, vw, R.id.xxbj10);
        i464e6cc6fb.setOnTouchListener(xxbj10TouchListener);

        __layoutIsLoaded(ay, vw);
    }
}
