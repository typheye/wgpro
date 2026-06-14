/**
 ******************************************************************************
 * @file    FileOperationMenuItemActivity.java
 * @author  Typheye
 * @brief   Handles file operation menu actions such as copy, paste, delete, and refresh.
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


import open.cn.awg.pro.core.IappCompat;
import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.file.DeleteConfirmActivity;
import open.cn.awg.pro.R;

public class FileOperationMenuItemActivity extends BaseAwgActivity {

    public final FileOperationMenuItemActivity lei = this, 类 = this;
    private final View.OnLongClickListener xxbj1LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXxbj1LongClick(vw);
        }

    };
    private final View.OnClickListener xxbj1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj1Click(vw);
        }

    };

    private void onXxbj1Click(View vw) {

        final AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        try {

            final i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) (Object) sj.lb(vw);
            gj.xc(new Thread() {

                public void run() {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            st.wb(R.id.title_bar).zf("提示");
                            st.xxbj(R.id.xxbj9).kjd(0);

                        }

                    });

                    String[] aa;

                    String[] bb;

                    String dbl = "";

                    Object a = lb.lbcfsj(R.id.wb1);

                    Object b = lb.lbcfsj(R.id.wb2);

                    Object c = lb.lbcfsj(R.id.wb3);

                    Object d = lb.lbcfsj(R.id.wb4);

                    String es = st.wb(R.id.zywblj).zf();

                    String cache1 = AppPaths.appPath("cache/1");

                    String cache2 = AppPaths.appPath("cache/2");

                    String cache3 = AppPaths.appPath("cache/3");

                    String cache00 = AppPaths.appPath("cache/00");

                    String cache41 = AppPaths.appPath("cache/41");

                    if (zf.dy(a, "返回")) {

                        e1.cdx(0, null, null, null);

                    } else if (zf.dy(a, "删除")) {

                        final String[] name = new String[]{"b", "c", "d", "es"};

                        final Object[] value = new Object[]{b, c, d, es};
                        gj.jmxc(new Runnable() {

                            public void run() {

                                gj.tz(DeleteConfirmActivity.class, name, value);

                            }

                        });
                        gj.xc(new Thread() {

                            public void run() {

                                gj.zt(500);
                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        e1.cdx(0, null, null, null);

                                    }

                                });

                            }

                        });

                    } else if (zf.dy(a, "刷新")) {

                        e1.sx(String.valueOf(c), es);
                        e1.cdx(0, null, null, null);

                    } else if (zf.dy(a, "复制")) {

                        if (wj.cz(cache1)) {

                            wj.sc(cache1);

                        }
                        wj.xrwb(cache1, d);

                        String z = "已复制,长按粘贴";
                        e1.tsk("提示", z);
                        e1.cdx(0, null, null, null);

                    } else if (zf.dy(a, "剪切")) {

                        if (wj.cz(cache2)) {

                            wj.sc(cache2);

                        }
                        wj.xrwb(cache2, d);

                        String z = "已剪切,长按粘贴";
                        e1.tsk("提示", z);
                        e1.cdx(0, null, null, null);

                    } else if (zf.dy(a, "粘贴到当前目录")) {

                        if (wj.cz(cache1)) {

                            String h1 = wj.dqwb(cache1);
                            bb = e1.fileinfo(wj.dqwb(cache00), h1);

                            if (zf.dy(bb[0], "true")) {

                                dbl = es + bb[3];

                            }

                            String pb1 = AppPaths.appPath("settings");

                            String pb2 = AppPaths.appPath("data");

                            String pb3 = AppPaths.appPath("fix");

                            String pb4 = AppPaths.appPath("cache");

                            String pb5 = AppPaths.appPath("files/cache");

                            String pb6 = "/storage/emulated/0/Android/data";

                            String pb7 = "/storage/emulated/0/Android/obb";

                            if (zf.ckt(dbl, pb1) || zf.ckt(dbl, pb2) || zf.ckt(dbl, pb3) || zf.ckt(dbl, pb4) || zf.ckt(dbl, pb5) || (IappCompat.sdk(xt.sbxx()) > 29 && zf.ckt(dbl, pb6)) || (IappCompat.sdk(xt.sbxx()) > 29 && zf.ckt(dbl, pb7))) {

                                e1.tsk("提示", "非法操作");

                            } else {

                                if (zf.dy(h1, es)) {

                                    final String z = "粘贴失败\n请选择其他目录";
                                    e1.tsk("提示", z);

                                } else {

                                    String m = "cp -r \"" + h1 + "\" \"" + es + "\"";
                                    wj.sc(cache1);
                                    aa = com.demo.e3.cmd(lei, m, false);

                                    if (zf.dy(aa[0], "") && !zf.dy(aa[1], "")) {

                                        final String z = "粘贴失败\n" + aa[1];
                                        e1.tsk("提示", z);

                                    } else if (!zf.dy(aa[0], "") && zf.dy(aa[1], "")) {

                                        final String z = "粘贴成功";
                                        e1.tsk("提示", z);

                                    } else {

                                        final String z = "粘贴成功";
                                        e1.tsk("提示", z);

                                    }

                                }

                            }

                        } else if (wj.cz(cache2)) {

                            String h2 = wj.dqwb(cache2);
                            bb = e1.fileinfo(wj.dqwb(cache00), h2);

                            if (zf.dy(bb[0], "true")) {

                                dbl = es + bb[3];

                            }

                            String pb1 = AppPaths.appPath("settings");

                            String pb2 = AppPaths.appPath("data");

                            String pb3 = AppPaths.appPath("fix");

                            String pb4 = AppPaths.appPath("cache");

                            String pb5 = AppPaths.appPath("files/cache");

                            String pb6 = "/storage/emulated/0/Android/data";

                            String pb7 = "/storage/emulated/0/Android/obb";

                            if (zf.ckt(dbl, pb1) || zf.ckt(dbl, pb2) || zf.ckt(dbl, pb3) || zf.ckt(dbl, pb4) || zf.ckt(dbl, pb5) || (IappCompat.sdk(xt.sbxx()) > 29 && zf.ckt(dbl, pb6)) || (IappCompat.sdk(xt.sbxx()) > 29 && zf.ckt(dbl, pb7))) {

                                e1.tsk("提示", "非法操作");

                            } else {

                                if (zf.dy(h2, es)) {

                                    final String z = "粘贴失败\n请选择其他目录";
                                    e1.tsk("提示", z);

                                } else {

                                    String m = "mv \"" + h2 + "\" \"" + es + "\"";
                                    wj.sc(cache2);
                                    aa = com.demo.e3.cmd(lei, m, false);

                                    if (zf.dy(aa[0], "") && !zf.dy(aa[1], "")) {

                                        final String z = "粘贴失败\n" + aa[1];
                                        e1.tsk("提示", z);

                                    } else if (!zf.dy(aa[0], "") && zf.dy(aa[1], "")) {

                                        final String z = "粘贴成功";
                                        e1.tsk("提示", z);

                                    } else {

                                        final String z = "粘贴成功";
                                        e1.tsk("提示", z);

                                    }

                                }

                            }

                        }
                        e1.sx(String.valueOf(c), es);
                        e1.cdx(0, null, null, null);

                    } else if (zf.dy(a, "取消粘贴")) {

                        wj.sc(cache1);
                        wj.sc(cache2);

                        String z = "已取消";
                        e1.tsk("提示", z);
                        e1.cdx(0, null, null, null);

                    } else if (zf.dy(a, "重命名")) {

                        wj.xrwb(cache3, "重命名");
                        e1.cdx(4, String.valueOf(c), String.valueOf(d), es);

                    } else if (zf.dy(a, "新建目录")) {

                        wj.xrwb(cache3, "新建目录");
                        e1.cdx(4, String.valueOf(c), String.valueOf(d), es);

                    } else if (zf.dy(a, "新建文件")) {

                        wj.xrwb(cache3, "新建文件");
                        e1.cdx(4, String.valueOf(c), String.valueOf(d), es);

                    } else if (zf.dy(a, "打开方式")) {

                        e1.cdx(6, String.valueOf(c), String.valueOf(d), es);

                    } else if (zf.dy(a, "属性")) {

                        if (wj.lx(d) == 2) {

                            String back;

                            String m = "du -sh \"" + d + "\"";
                            aa = com.demo.e3.cmd(lei, m, false);

                            if (zf.dy(aa[0], "") && !zf.dy(aa[1], "")) {

                                back = "获取失败";

                            } else if (!zf.dy(aa[0], "") && zf.dy(aa[1], "")) {

                                back = aa[0];
                                back = zf.qc(back, null, d);
                                back = zf.qctwkg(back);

                            } else {

                                back = "获取失败";

                            }
                            wj.xrwb(cache41, back);

                        } else {

                        }
                        e1.cdx(5, String.valueOf(c), String.valueOf(d), es);

                    } else {

                    }

                }

            });

        } catch (Throwable e) {

            e1.upload_error(e, "c4.*");

        }

    }

    private boolean onXxbj1LongClick(View vw) {

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) (Object) sj.lb(vw);

        Object a = lb.lbcfsj(R.id.wb1);
        e1.tsk("详细信息", a);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        LinearLayout e1433e26d6 = (LinearLayout) findViewById(ay, vw, R.id.xxbj1);
        e1433e26d6.setOnClickListener(xxbj1ClickListener);
        e1433e26d6.setOnLongClickListener(xxbj1LongClickListener);

        TextView ff56a8e7f6 = (TextView) findViewById(ay, vw, R.id.wb1);

        RelativeLayout i1aae26e9a0 = (RelativeLayout) findViewById(ay, vw, R.id.file_operation_menu_item_root);

        __layoutIsLoaded(ay, vw);
    }
}
