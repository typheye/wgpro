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
import androidx.recyclerview.widget.RecyclerView;
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

public class FileRootPickerActivity extends BaseAwgActivity {

    public final FileRootPickerActivity lei = this, 类 = this;
    public AppRuntimeBridge e1;

    public open.cn.awg.pro.ui.list.NativeListAdapter lbspq = null;
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
        e1 = new AppRuntimeBridge(this);
        setContentView(R.layout.file_root_picker);
        _$_viewAutomaticSettingEvent();
        list();

    }

    public void list() {

        final String spxx = AppPaths.appPath("settings/a3.inf");

        androidx.recyclerview.widget.RecyclerView lb = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(FileRootPickerActivity.this.getWindow().getDecorView(), R.id.v7lb1));
        lbspq = open.cn.awg.pro.ui.list.NativeListAdapter.attach(FileRootPickerActivity.this, lb, R.layout.file_root_item, new open.cn.awg.pro.ui.list.FileRootRowBinder(FileRootPickerActivity.this));
        lbspq.clearRows();

        String rootpath = open.cn.awg.pro.core.NativeFiles.resolvePath(FileRootPickerActivity.this, (String) ("%"));

        int[] ids2 = new int[]{R.id.wb1, R.id.wb3, R.id.wb2, -1};

        Object[] values2 = new Object[]{"", "", "a1", 0};

        int sdk = android.os.Build.VERSION.SDK_INT;

        if (sdk < 30) {

            values2 = new Object[]{"内部存储", rootpath, "a1", 0};
            lbspq.addRow(ids2, values2);
            values2 = new Object[]{"用户相册", rootpath + "DCIM/", "a1", 0};
            lbspq.addRow(ids2, values2);
            values2 = new Object[]{"用户文档", rootpath + "Documents/", "a1", 0};
            lbspq.addRow(ids2, values2);
            values2 = new Object[]{"用户下载", rootpath + "Download/", "a1", 0};
            lbspq.addRow(ids2, values2);
            values2 = new Object[]{"用户视频", rootpath + "Movies/", "a1", 0};
            lbspq.addRow(ids2, values2);
            values2 = new Object[]{"用户音乐", rootpath + "Music/", "a1", 0};
            lbspq.addRow(ids2, values2);
            values2 = new Object[]{"用户图片", rootpath + "Pictures/", "a1", 0};
            lbspq.addRow(ids2, values2);

        } else {

            values2 = new Object[]{"内部存储", "/storage/emulated/0/", "a2", 0};
            lbspq.addRow(ids2, values2);
            values2 = new Object[]{"用户相册", "/storage/emulated/0/DCIM/", "a2", 0};
            lbspq.addRow(ids2, values2);
            values2 = new Object[]{"用户文档", "/storage/emulated/0/Documents/", "a2", 0};
            lbspq.addRow(ids2, values2);
            values2 = new Object[]{"用户下载", "/storage/emulated/0/Download/", "a2", 0};
            lbspq.addRow(ids2, values2);
            values2 = new Object[]{"用户视频", "/storage/emulated/0/Movies/", "a2", 0};
            lbspq.addRow(ids2, values2);
            values2 = new Object[]{"用户音乐", "/storage/emulated/0/Music/", "a2", 0};
            lbspq.addRow(ids2, values2);
            values2 = new Object[]{"用户图片", "/storage/emulated/0/Pictures/", "a2", 0};
            lbspq.addRow(ids2, values2);

        }
        values2 = new Object[]{"挂载目录", "/mnt/", "a0", 0};
        lbspq.addRow(ids2, values2);
        values2 = new Object[]{"系统目录", "/system/", "a0", 0};
        lbspq.addRow(ids2, values2);
        values2 = new Object[]{"应用目录", AppPaths.externalAppPath("") + "/", "a0", 0};
        lbspq.addRow(ids2, values2);
        values2 = new Object[]{"应用目录", AppPaths.appPath(""), "a0", 0};
        values2 = new Object[]{"", "", "", -1};
        lbspq.addRow(ids2, values2);

        String axc = open.cn.awg.pro.core.NativeFiles.readText(FileRootPickerActivity.this, (String) (spxx));

        if (java.util.Objects.equals(axc, "1") || java.util.Objects.equals(axc, "3")) {

            lbspq.addRow(ids2, values2);
            lbspq.addRow(ids2, values2);

        }
        lbspq.refreshRows();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.file_root_picker_root, R.id.xdbj2, R.id.title_bar);

        final String a041 = AppPaths.appPath("cache/tab");
        open.cn.awg.pro.core.NativeFiles.writeText(FileRootPickerActivity.this, (String) (a041), (String) ("true"));

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

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(FileRootPickerActivity.this, (String) (set4)), "false")) {

            open.cn.awg.pro.core.NativeFiles.delete(FileRootPickerActivity.this, (String) (cache1), false);
            open.cn.awg.pro.core.NativeFiles.delete(FileRootPickerActivity.this, (String) (cache2), false);
            open.cn.awg.pro.core.NativeFiles.delete(FileRootPickerActivity.this, (String) (cache3), false);

        }

    }

    public void onRestart() {
        super.onRestart();
            applyWindowModeFromSettings();

    }

    public void onStart() {
        super.onStart();
            applyWindowModeFromSettings();

        androidx.recyclerview.widget.RecyclerView v7lb1 = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(FileRootPickerActivity.this.getWindow().getDecorView(), R.id.v7lb1));
        v7lb1.requestFocus();

    }

    public void onResume() {
        super.onResume();
            applyWindowModeFromSettings();

    }

    private void onTitleBarClick(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(FileRootPickerActivity.this);

    }

    private boolean onTitleBarLongClick(View vw) {
        return true;

    }

    private void onTx1Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(FileRootPickerActivity.this.getWindow().getDecorView(), R.id.xxbj10)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(FileRootPickerActivity.this.getWindow().getDecorView(), R.id.xxbj10)).setVisibility(android.view.View.GONE);

        } else {

            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(FileRootPickerActivity.this.getWindow().getDecorView(), R.id.xxbj10)).setVisibility(android.view.View.VISIBLE);

        }
        open.cn.awg.pro.core.NativeActions.finish(FileRootPickerActivity.this);

    }

    private void onTx2Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(FileRootPickerActivity.this.getWindow().getDecorView(), R.id.xxbj10)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(FileRootPickerActivity.this.getWindow().getDecorView(), R.id.xxbj10)).setVisibility(android.view.View.GONE);

        } else {

            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(FileRootPickerActivity.this.getWindow().getDecorView(), R.id.xxbj10)).setVisibility(android.view.View.VISIBLE);

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

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        LinearLayout i464e6cc6fb = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj10);
        i464e6cc6fb.setOnTouchListener(xxbj10TouchListener);

        __layoutIsLoaded(ay, vw);
    }
}
