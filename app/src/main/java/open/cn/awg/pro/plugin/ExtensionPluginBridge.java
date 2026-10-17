/**
 ******************************************************************************
 * @file    ExtensionPluginBridge.java
 * @author  Typheye
 * @brief   Extension plugin runtime bridge.
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
package open.cn.awg.pro.plugin;



import android.annotation.SuppressLint;
import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;

import android.app.Activity;
import android.content.Context;

import open.cn.awg.pro.apps.InstalledAppListActivity;
import open.cn.awg.pro.chat.ChatRoomActivity;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.data.LocalDatabaseBridge;
import open.cn.awg.pro.download.AppDownloadActivity;
import open.cn.awg.pro.experimental.AppDebugActivity;
import open.cn.awg.pro.live.LiveStreamActivity;
import open.cn.awg.pro.music.TuneFreeActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.tools.LocationInfoActivity;
import open.cn.awg.pro.tools.RemoteControlActivity;
import open.cn.awg.pro.tools.TextTranslateActivity;
import open.cn.awg.pro.tools.WearOsToolboxActivity;

@SuppressLint("StaticFieldLeak")
public class ExtensionPluginBridge {
    private final Context context;
    private final Activity owner;

    public static open.cn.awg.pro.ui.list.NativeListAdapter cjlbcdspq;

    public static androidx.recyclerview.widget.RecyclerView cjlbcdlb;

    public static open.cn.awg.pro.ui.list.NativeListAdapter spq2;

    public static androidx.recyclerview.widget.RecyclerView lb2;

public static LinearLayoutManager mLayoutManager;

    public static String set47 = AppPaths.appPath("settings/db/plugin.db");

    public static android.database.sqlite.SQLiteDatabase sjk;

    public static int bjkmode = 0;
    public final ExtensionPluginBridge lei = this, 类 = this;
    public AppRuntimeBridge e1;
    public LocalDatabaseBridge e11;

    public ExtensionPluginBridge(Context context) {
        this.context = java.util.Objects.requireNonNull(context);
        this.owner = context instanceof Activity ? (Activity) context : null;
        e1 = new AppRuntimeBridge(context);
        e11 = new LocalDatabaseBridge(context);
    }

    public boolean part_buildNewPlu(Object name, Object id, Object pass) {
        open.cn.awg.pro.core.NativeActions.sleep(500);

        final String set2 = AppPaths.appPath("settings/f19/set2.inf");
        name = open.cn.awg.pro.core.TextValues.trim(name);
        id = open.cn.awg.pro.core.TextValues.trim(id);

        if (java.util.Objects.equals(name, "") || java.util.Objects.equals(id, "") || java.util.Objects.equals(pass, "")) {

            e1.tsk("提示", "提交失败：请填写完整");

        } else {

            try {

                String url = e1.urlUnlockString(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)));

                if (open.cn.awg.pro.core.TextValues.endsWith(url, "/")) {

                } else {

                    url = url + "/";

                }

                String idget = url + "class/api.php?type=make";

                String[] pt = new String[]{"title=" + name, "id=" + id, "pass=" + pass};

                String st5 = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(idget), (String[]) (pt), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                if (java.util.Objects.equals(st5, "null") || java.util.Objects.equals(st5, "") || java.util.Objects.equals(st5, null)) {

                    e1.tsk("提示", "提交失败：连接服务器异常");

                } else {

                    st5 = open.cn.awg.pro.core.TextValues.trim(st5);
                    st5 = open.cn.awg.pro.core.TextValues.replace(st5, "<br>", "  ");
                    e1.tsk("提示", st5);

                }

            } catch (Throwable __$_e__) {

                e1.tsk("提示", "提交失败：出现异常");

            }

        }
        return true;

    }

    public void csh() {
        e11.setup_pluginlist();
        sjk = (android.database.sqlite.SQLiteDatabase) (Object) open.cn.awg.pro.core.NativeDatabase.open(context, String.valueOf(set47));
        cjlbcdlb = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.cjlbv7lb2));
        cjlbcdspq = open.cn.awg.pro.ui.list.NativeListAdapter.attach(owner, cjlbcdlb, R.layout.extension_plugin_menu_item, new open.cn.awg.pro.ui.list.ExtensionPluginMenuRowBinder(owner), new open.cn.awg.pro.ui.list.NativeListAdapter.RowBinder() {

            public void bind(View vw, java.util.Map<Integer, Object> row, int pn) {

                

                final int f = open.cn.awg.pro.core.TextValues.intValue(row.get(-1), -1);

                if (f == 1) {

                    String color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextFalse);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setLongClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.dp20)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                } else if (f == -1) {

                    String color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setClickable(java.lang.Boolean.TRUE.equals(false));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setLongClickable(java.lang.Boolean.TRUE.equals(false));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.dp20)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                } else {

                    String color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setLongClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.dp20)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            }

        });
        lb2 = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.cjlbv7lb1));
        spq2 = open.cn.awg.pro.ui.list.NativeListAdapter.attach(owner, lb2, R.layout.extension_plugin_list_item, new open.cn.awg.pro.ui.list.ExtensionPluginRowBinder(owner), new open.cn.awg.pro.ui.list.NativeListAdapter.RowBinder() {

            public void bind(View vw, java.util.Map<Integer, Object> row, int pn) {

            }

        });

    }

    public void cjlbview(int a) {

        final int i = a;

        final android.widget.LinearLayout root = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj1));

        final android.widget.LinearLayout load = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj3));

        final android.widget.LinearLayout cd = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj2));

        final android.widget.LinearLayout add = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj4));

        final androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.qtgd1));
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                if (i == 0) {

                    root.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    load.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    cd.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    add.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                } else if (i == 1) {

                    root.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    load.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    cd.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    add.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                } else if (i == 2) {

                    root.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    load.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    cd.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    add.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                } else if (i == 3) {

                    root.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    load.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    cd.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    add.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                }
                qtgd1.requestFocus();

            }

        });

    }

    public void cjlbcd(int i) {

        if (i == 0) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("扩展插件"));
                    cjlbview(0);

                }

            });

        } else if (i == 4) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("添加扩展"));
                    ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(""));
                    cjlbview(3);
                    bjkmode = 1;

                }

            });

        } else if (i == 5) {

            String cachehd1 = AppPaths.appPath("data/cjlb/lbx_title");

            final String bz = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cachehd1));
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("扩展备注"));
                    ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(bz));
                    cjlbview(3);
                    bjkmode = 2;

                }

            });

        } else {

            cjlbview(2);
            cjlbcdspq.clearRows();

            int[] kj = new int[]{R.id.wb1, R.id.wb2, R.id.wb3, R.id.wb4, R.id.wb5, -1};

            Object[] jk = new Object[]{"", "", "", "", "", 0};

            if (i == 1) {

                jk = new Object[]{"刷新", "", "", "", "", 0};
                cjlbcdspq.addRow(kj, jk);
                jk = new Object[]{"添加扩展", "", "", "", "", 0};
                cjlbcdspq.addRow(kj, jk);
                jk = new Object[]{"新建扩展", "", "", "", "", 0};
                cjlbcdspq.addRow(kj, jk);
                jk = new Object[]{"退出", "", "", "", "", 0};
                cjlbcdspq.addRow(kj, jk);

            } else if (i == 2) {

                jk = new Object[]{"备注扩展", "", "", "", "", 0};
                cjlbcdspq.addRow(kj, jk);
                jk = new Object[]{"移除扩展", "", "", "", "", 0};
                cjlbcdspq.addRow(kj, jk);
                jk = new Object[]{"清除扩展缓存", "", "", "", "", 0};

            }
            jk = new Object[]{"返回", "back", "", "", "", 1};
            cjlbcdspq.addRow(kj, jk);

            String spxx = AppPaths.appPath("settings/a3.inf");
            jk = new Object[]{"", "", "", "", "", -1};
            cjlbcdspq.addRow(kj, jk);

            String axc = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (spxx));

            if (java.util.Objects.equals(axc, "1") || java.util.Objects.equals(axc, "3")) {

                jk = new Object[]{"", "", "", "", "", -1};
                cjlbcdspq.addRow(kj, jk);
                jk = new Object[]{"", "", "", "", "", -1};
                cjlbcdspq.addRow(kj, jk);

            }
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    cjlbcdspq.refreshRows();
                    open.cn.awg.pro.core.NativeViewValues.scrollPosition(((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.cjlbv7lb2)), "top");
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("菜单"));

                }

            });

        }

    }

    public void dj_cjlbcd(Object a, Object b, Object c, Object d) {

        if (java.util.Objects.equals(a, "返回")) {

            cjlbcd(0);

        } else if (java.util.Objects.equals(a, "备注扩展")) {

            String cachehd = AppPaths.appPath("data/cjlb/lbx_id");

            String id = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cachehd));
            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(""));
            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setHint(String.valueOf("请输入扩展备注"));
            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setInputType(open.cn.awg.pro.core.NativeViewValues.inputType("text"));
            cjlbcd(5);

        } else if (java.util.Objects.equals(a, "刷新")) {

            cjlbcd(0);
            cjlbsx();

        } else if (java.util.Objects.equals(a, "添加扩展")) {

            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(""));
            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setHint(String.valueOf("请输入扩展ID"));
            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setInputType(open.cn.awg.pro.core.NativeViewValues.inputType("number"));
            cjlbcd(4);

        } else if (java.util.Objects.equals(a, "新建扩展")) {

            e1.goplu("20003", "新建扩展", false);
            cjlbcd(0);

        } else if (java.util.Objects.equals(a, "移除扩展")) {

            cjlbcd(0);

            String nr = "";

            String cachehd = AppPaths.appPath("data/cjlb/lbx_id");

            String id = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cachehd));

            String sjhc = AppPaths.appPath("data/cjlb/chat/") + id;

            int u = e11.findid_pluginlist(id);

            String uss = String.valueOf(u);

            boolean b1 = e11.delete_pluginlist(uss);
            open.cn.awg.pro.core.NativeFiles.delete(context, (String) (sjhc), false);

            if (b1) {

                nr = "移除成功";

            } else {

                nr = "移除失败";

            }
            cjlbcd(0);
            cjlbsx();
            e1.tsk("提示", nr);

        } else if (java.util.Objects.equals(a, "清除扩展缓存")) {

            cjlbcd(0);

            String nr = "";

            String cachehd = AppPaths.appPath("data/cjlb/lbx_id");

            String id = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cachehd));

            String sjhc = AppPaths.appPath("data/cjlb/chat/") + id;

            boolean b1 = (open.cn.awg.pro.core.NativeFiles.delete(context, (String) (sjhc), false) > 0);

            if (b1) {

                nr = "清除成功";

            } else {

                nr = "清除失败";

            }
            e1.tsk("提示", nr);
            cjlbcd(0);

        } else if (java.util.Objects.equals(a, "退出")) {

            cjlbcd(0);
            open.cn.awg.pro.core.NativeActions.finish(context);

        } else {

        }

    }

    public void cjlbsx() {

        final String set1 = AppPaths.appPath("settings/f11/set1.inf");
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("扩展插件"));
                        cjlbview(1);

                    }

                });

                final int[] kj = new int[]{R.id.wb1, R.id.wb2, R.id.wb3};

                String[] jk = new String[]{"", "", ""};
                spq2.clearRows();

                String ab = AppPaths.appPath("settings/candebug");

                if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (ab)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (ab)), "true")) {

                } else {

                    try {

                        e11.delete_pluginlist(e11.findid_pluginlist("404"));

                    } catch (Throwable __$_e__) {

                    }

                }

                String table = "_id,uid,name,utext";

                Object sqlx = null;

                try (android.database.Cursor sjk15 = (android.database.Cursor) (Object) sjk.rawQuery("SELECT " + table + " from " + "pluginlist" + (sqlx == null ? "" : " WHERE " + sqlx), null)) {
                    while (sjk15.moveToNext()) {

                        jk = new String[]{sjk15.getString(open.cn.awg.pro.core.TextValues.intValue(2, -1)), sjk15.getString(open.cn.awg.pro.core.TextValues.intValue(1, -1)), sjk15.getString(open.cn.awg.pro.core.TextValues.intValue(3, -1))};
                        spq2.addRow(kj, jk);

                    }
                }
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        spq2.refreshRows();
                        cjlbview(0);

                    }

                });

            }

        });

    }

    public void bjsj() {

        final String kzsjhc = AppPaths.appPath("data/cjlb/chat/");

        final String set2 = AppPaths.appPath("settings/f19/set2.inf");

        String bjknr = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).getText().toString();
        bjknr = open.cn.awg.pro.core.TextValues.trim(bjknr);

        final String bjknrs = bjknr;

        if (java.util.Objects.equals(bjknrs, "")) {

            if (bjkmode == 2) {

                e1.tsk("提示", "扩展备注不能为空");

            } else if (bjkmode == 1) {

                e1.tsk("提示", "扩展ID不能为空");

            }

        } else {

            if (bjkmode == 2) {

                String cachehd = AppPaths.appPath("data/cjlb/lbx_id");

                final String id = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cachehd));
                open.cn.awg.pro.core.NativeActions.start(new Thread() {

                    public void run() {

                        String nr = "";
                        cjlbview(1);

                        int u = e11.findid_pluginlist(id);

                        String uss = String.valueOf(u);
                        open.cn.awg.pro.core.NativeActions.log(u);

                        boolean b1 = e11.update_pluginlist(uss, "name", bjknrs);
                        open.cn.awg.pro.core.NativeActions.log(b1);

                        if (b1) {

                            nr = "扩展备注修改成功";

                        } else {

                            nr = "扩展备注修改失败";

                        }
                        cjlbsx();
                        e1.tsk("提示", nr);

                    }

                });

            } else if (bjkmode == 1) {

                open.cn.awg.pro.core.NativeActions.start(new Thread() {

                    public void run() {

                        cjlbview(1);
                        open.cn.awg.pro.core.NativeActions.sleep(500);

                        String nr = "";

                        java.util.regex.Matcher zz = (java.util.regex.Matcher) (Object) open.cn.awg.pro.core.TextValues.matcher(bjknrs, "[0-9]*$", 0);

                        boolean b2 = zz.matches();

                        if (b2) {

                            String b4 = "";

                            boolean can = true;

                            if (java.util.Objects.equals(bjknrs, "404")) {

                                String ab = AppPaths.appPath("settings/candebug");

                                if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (ab)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (ab)), "true")) {

                                    b4 = "App Debug";

                                } else {

                                    can = false;

                                }

                            } else if (java.util.Objects.equals(bjknrs, "10000")) {

                                b4 = "扩展推荐";

                            } else if (java.util.Objects.equals(bjknrs, "10001")) {

                                b4 = "文件下载";

                            } else if (java.util.Objects.equals(bjknrs, "10002")) {

                                b4 = "文本翻译";

                            } else if (java.util.Objects.equals(bjknrs, "10004")) {

                                b4 = "网络直播";

                            } else if (java.util.Objects.equals(bjknrs, "10005")) {

                                b4 = "位置信息";

                            } else if (java.util.Objects.equals(bjknrs, "10007")) {

                                b4 = "应用列表";

                            } else if (java.util.Objects.equals(bjknrs, "10010")) {

                                b4 = "WearOS工具箱";

                            } else if (java.util.Objects.equals(bjknrs, "10011")) {

                                b4 = "TuneFree";

                            } else if (java.util.Objects.equals(bjknrs, "10015")) {

                                b4 = "腕管远控";

                            } else {

                                try {

                                    String url = e1.urlUnlockString(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)));

                                    if (open.cn.awg.pro.core.TextValues.endsWith(url, "/")) {

                                    } else {

                                        url = url + "/";

                                    }

                                    String idget = url + "project/" + bjknrs + ".json";

                                    String st5 = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(idget), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                                    if (java.util.Objects.equals(st5, null)) {

                                        can = false;

                                    } else {

                                        String _b4 = e1.readJson(st5, "room");
                                        b4 = _b4;
                                        open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (kzsjhc + bjknrs), (String) (st5));

                                    }

                                } catch (Throwable e) {

                                    can = false;

                                }

                            }

                            if (can) {

                                boolean b3 = e11.add_pluginlist(bjknrs, b4, bjknrs + "-" + b4);

                                if (b3) {

                                    nr = "添加成功";

                                } else {

                                    nr = "添加失败";

                                }

                            } else {

                                nr = "添加失败";

                            }
                            cjlbview(0);
                            cjlbsx();
                            e1.tsk("提示", nr);

                        } else {

                            cjlbview(3);
                            e1.tsk("提示", "扩展ID仅为纯数字");

                        }

                    }

                });

            } else {

                cjlbcd(0);

            }

        }

    }

    public boolean newG(int ii) {

        boolean isR = false;

        final int i = ii;
        isR = false;

        if (i == 10000) {

            String a = "400";

            String b = "扩展推荐";

            if (e11.findid_talklist(a) == 0) {

                e11.setup_talklist();
                e11.add_talklist(a, b, a + "-" + b);

            }

            String cachehd = AppPaths.appPath("data/ltlb/lbx_id");
            open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (cachehd), (String) (a));

            String cachehd2 = AppPaths.appPath("data/ltlb/lbx_title");
            open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (cachehd2), (String) (b));

            final String[] name = new String[]{"uid"};

            final String[] value = new String[]{a};
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (ChatRoomActivity.class), (String[]) (name), (Object[]) (value));

                }

            });

        } else if (i == 10001) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (AppDownloadActivity.class));

                }

            });

        } else if (i == 10002) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (TextTranslateActivity.class));

                }

            });

        } else if (i == 10003) {

            e11.delete_pluginlist(e11.findid_pluginlist("10003"));
            e1.tsk("提示", "此扩展已废弃,已自动为您移除,请留意扩展推荐");

        } else if (i == 10004) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (LiveStreamActivity.class));

                }

            });

        } else if (i == 10005) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (LocationInfoActivity.class));

                }

            });

        } else if (i == 10006) {

            e11.delete_pluginlist(e11.findid_pluginlist("10006"));
            e1.tsk("提示", "此扩展已废弃,已自动为您移除,请留意扩展推荐");

        } else if (i == 10007) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (InstalledAppListActivity.class));

                }

            });

        } else if (i == 10008) {

            e11.delete_pluginlist(e11.findid_pluginlist("10008"));
            e1.tsk("提示", "此扩展已废弃,已自动为您移除,请留意扩展推荐");

        } else if (i == 10009) {

            e11.delete_pluginlist(e11.findid_pluginlist("10009"));
            e1.tsk("提示", "此扩展已废弃,已自动为您移除,请留意扩展推荐");

        } else if (i == 10010) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (WearOsToolboxActivity.class));

                }

            });

        } else if (i == 10011) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (TuneFreeActivity.class));

                }

            });

        } else if (i == 10012) {

            e11.delete_pluginlist(e11.findid_pluginlist("10012"));
            e1.tsk("提示", "此扩展已废弃,已自动为您移除,请留意扩展推荐");

        } else if (i == 10015) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (RemoteControlActivity.class));

                }

            });

        } else if (i == 404) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (AppDebugActivity.class));

                }

            });

        } else {

            isR = true;

        }
        return isR;

    }

    public void cjlbcd(Activity activity, int item) { new ExtensionPluginBridge(activity).cjlbcd(item); }
    public void dj_cjlbcd(Activity activity, Object a, Object b, Object c, Object d) { new ExtensionPluginBridge(activity).dj_cjlbcd(a, b, c, d); }
}
