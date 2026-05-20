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

import i.app.iActivity;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.file.FileManagerActivity;


public class FileRootItemActivity extends iActivity {

    public final FileRootItemActivity lei = this, 类 = this;
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_aaeebbd136 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_aaeebbd136(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_aaeebbd136 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_aaeebbd136(vw);
        }

    };

    public void go(java.lang.Object lj) {

        java.lang.String cache00 = "/data/user/0/open.cn.awg.pro/cache/00";

        final java.lang.String[] name = new java.lang.String[]{"url", "root"};

        final java.lang.Object[] value = new java.lang.Object[]{lj, lj};
        wj.xrwb(cache00, lj);
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                gj.tz(FileManagerActivity.class, name, value);

            }

        });

    }

    private void $_onClick_aaeebbd136(android.view.View vw) {

        final open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        java.lang.Object lj = lb.lbcfsj(open.cn.awg.pro.R.id.wb3);

        java.lang.Object pd = lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

        java.lang.Object title = lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

        java.lang.String ftitle = "/data/user/0/open.cn.awg.pro/cache/file/title";
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

            } catch (java.lang.Throwable e) {

                e1.upload_error(e, "c13.*");

            }

        } else if (zf.dy(pd, "a2")) {

            try {

                i.runlibrary.app.xt$qxgl a = xt.qxgl();

                if (true) {

                    go(lj);

                    try {

                        java.lang.Class cs = android.os.Environment.class;

                        java.lang.reflect.Method m = cs.getMethod("isExternalStorageManager");

                        java.lang.Object o = m.invoke(null);

                        if (o.equals(true)) {

                        } else {

                            java.lang.String set2 = "/data/user/0/open.cn.awg.pro/settings/d1/set2.inf";

                            if (zf.dy(wj.dqwb(set2), "true")) {

                            } else {

                                e1.tsk("提示", "您未授权应用\"所有文件访问权限\"，可能会存在设备兼容问题，请尝试授权。\n您也可前往应用设置>文件管理页面，打开\"忽略兼容提示\"，使本提示不再弹出。");

                            }

                        }

                    } catch (java.lang.Throwable e) {

                    }

                } else {

                    e1.tsk("提示", "您未授权应用\"修改或删除您共享存储空间中的内容\"，请先前往设置授权后再使用");

                }

            } catch (java.lang.Throwable e) {

                e1.upload_error(e, "c13.*");

            }

        }

    }

    private boolean $_onLongClick_aaeebbd136(android.view.View vw) {

        final open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        java.lang.Object te = lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

        java.lang.Object lj = lb.lbcfsj(open.cn.awg.pro.R.id.wb3);
        e1.tsk("详细信息", "[标题]\n" + te + "\n\n[路径]\n" + lj);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.RelativeLayout aaeebbd136 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj1);
        aaeebbd136.setOnClickListener($_on_setOnClickListener_aaeebbd136);
        aaeebbd136.setOnLongClickListener($_on_setOnLongClickListener_aaeebbd136);

        android.widget.RelativeLayout i0e9a1c6629 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.c12_v);

        __layoutIsLoaded(ay, vw);
    }
}
