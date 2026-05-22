/**
 ******************************************************************************
 * @file    FileRootItemActivity.java
 * @author  Typheye
 * @brief   Opens a selected file root or storage location.
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
package open.cn.awg.pro.ui.list;

import android.app.Activity;
import android.os.Environment;
import android.view.View;
import android.widget.RelativeLayout;

import java.lang.reflect.Method;

import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.file.FileManagerActivity;
import open.cn.awg.pro.R;

public class FileRootItemActivity extends BaseAwgActivity {

    public final FileRootItemActivity lei = this, 类 = this;
    private final View.OnLongClickListener xdbj1LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj1LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj1Click(vw);
        }

    };

    public void go(Object lj) {

        String cache00 = AppPaths.appPath("cache/00");

        final String[] name = new String[]{"url", "root"};

        final Object[] value = new Object[]{lj, lj};
        wj.xrwb(cache00, lj);
        gj.jmxc(new Runnable() {

            public void run() {

                gj.tz(FileManagerActivity.class, name, value);

            }

        });

    }

    private void onXdbj1Click(View vw) {

        final AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        Object lj = lb.lbcfsj(R.id.wb3);

        Object pd = lb.lbcfsj(R.id.wb2);

        Object title = lb.lbcfsj(R.id.wb1);

        String ftitle = AppPaths.appPath("cache/file/title");
        wj.xrwb(ftitle, "< " + title);

        if (zf.dy(pd, "a0")) {

            go(lj);

        } else if (zf.dy(pd, "a1")) {

            try {

                i.runlibrary.app.xt$qxgl a = xt.qxgl();

                if (a.qx("android.permission.WRITE_EXTERNAL_STORAGE")) {

                    go(lj);

                } else {

                    e1.tsk("提示", "您未授权应用\"修改或删除您共享存储空间中的内容\"，请先前往设置授权后再使用");

                }

            } catch (Throwable e) {

                e1.upload_error(e, "c13.*");

            }

        } else if (zf.dy(pd, "a2")) {

            try {

                i.runlibrary.app.xt$qxgl a = xt.qxgl();

                if (true) {

                    go(lj);

                    try {

                        Class cs = Environment.class;

                        Method m = cs.getMethod("isExternalStorageManager");

                        Object o = m.invoke(null);

                        if (o.equals(true)) {

                        } else {

                            String set2 = AppPaths.appPath("settings/d1/set2.inf");

                            if (zf.dy(wj.dqwb(set2), "true")) {

                            } else {

                                e1.tsk("提示", "您未授权应用\"所有文件访问权限\"，可能会存在设备兼容问题，请尝试授权。\n您也可前往应用设置>文件管理页面，打开\"忽略兼容提示\"，使本提示不再弹出。");

                            }

                        }

                    } catch (Throwable e) {

                    }

                } else {

                    e1.tsk("提示", "您未授权应用\"修改或删除您共享存储空间中的内容\"，请先前往设置授权后再使用");

                }

            } catch (Throwable e) {

                e1.upload_error(e, "c13.*");

            }

        }

    }

    private boolean onXdbj1LongClick(View vw) {

        final AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        Object te = lb.lbcfsj(R.id.wb1);

        Object lj = lb.lbcfsj(R.id.wb3);
        e1.tsk("详细信息", "[标题]\n" + te + "\n\n[路径]\n" + lj);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        RelativeLayout aaeebbd136 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj1);
        aaeebbd136.setOnClickListener(xdbj1ClickListener);
        aaeebbd136.setOnLongClickListener(xdbj1LongClickListener);

        RelativeLayout i0e9a1c6629 = (RelativeLayout) findViewById(ay, vw, R.id.file_root_item_root);

        __layoutIsLoaded(ay, vw);
    }
}
