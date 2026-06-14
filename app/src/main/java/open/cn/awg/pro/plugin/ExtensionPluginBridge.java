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
import open.cn.awg.pro.core.IappCompat;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import i.app.iClass;

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
import open.cn.awg.pro.ui.list.ExtensionPluginListItemActivity;
import open.cn.awg.pro.ui.list.ExtensionPluginMenuItemActivity;

@SuppressLint("StaticFieldLeak")
public class ExtensionPluginBridge extends iClass {

    public static i.runlibrary.app.v.v7lb$UserAdapter cjlbcdspq;

    public static i.runlibrary.app.v.v7lb cjlbcdlb;

    public static i.runlibrary.app.v.v7lb$UserAdapter spq2;

    public static i.runlibrary.app.v.v7lb lb2;

    public static Context context;

    public static LinearLayoutManager mLayoutManager;

    public static String set47 = AppPaths.appPath("settings/db/plugin.db");

    public static i.runlibrary.app.sj$sjk sjk;

    public static int bjkmode = 0;
    public final ExtensionPluginBridge lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public LocalDatabaseBridge e11 = new LocalDatabaseBridge(_APPINFO);

    public ExtensionPluginBridge(i.runlibrary.app.AppInfo _APPINFO) {
        super(_APPINFO);

    }

    public boolean part_buildNewPlu(Object name, Object id, Object pass) {
        gj.zt(500);

        final String set2 = AppPaths.appPath("settings/f19/set2.inf");
        name = zf.qctwkg(name);
        id = zf.qctwkg(id);

        if (zf.dy(name, "") || zf.dy(id, "") || zf.dy(pass, "")) {

            e1.tsk("提示", "提交失败：请填写完整");

        } else {

            try {

                String url = e1.urlUnlockString(wj.dqwb(set2));

                if (zf.cjw(url, "/")) {

                } else {

                    url = url + "/";

                }

                String idget = url + "class/api.php?type=make";

                String[] pt = new String[]{"title=" + name, "id=" + id, "pass=" + pass};

                String st5 = wl.hq(idget, pt, "utf-8", null, true, null, 20000, 20000, null);

                if (zf.dy(st5, "null") || zf.dy(st5, "") || zf.dy(st5, null)) {

                    e1.tsk("提示", "提交失败：连接服务器异常");

                } else {

                    st5 = zf.qctwkg(st5);
                    st5 = zf.th(st5, "<br>", "  ");
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
        sjk = (i.runlibrary.app.sj$sjk) (Object) sj.sjk(set47);
        cjlbcdlb = st.v7lb(R.id.cjlbv7lb2);
        cjlbcdspq = IappCompat.v7lbAdapter(cjlbcdlb, ExtensionPluginMenuItemActivity.class, R.layout.extension_plugin_menu_item, new IappCompat.V7lbViewBinder() {

            public void bind(Object ua, int pn, View vw) {

                i.runlibrary.app.sj$lb lbsj = (i.runlibrary.app.sj$lb) (Object) sj.lb(vw);

                final int f = IappCompat.zh(sj.zh(lbsj.lbsj(pn, -1))).zint();

                if (f == 1) {

                    String color = zf.zf(R.color.colorTextFalse);
                    st.wb(vw, R.id.wb1).zfys(color);
                    st.xxbj(vw, R.id.xxbj1).kjd(0);
                    st.xxbj(vw, R.id.xxbj1).xydj(true);
                    st.xxbj(vw, R.id.xxbj1).xyca(true);
                    st.wb(vw, R.id.dp20).kjd(8);

                } else if (f == -1) {

                    String color = zf.zf(R.color.colorTextU1);
                    st.wb(vw, R.id.wb1).zfys(color);
                    st.xxbj(vw, R.id.xxbj1).kjd(8);
                    st.xxbj(vw, R.id.xxbj1).xydj(false);
                    st.xxbj(vw, R.id.xxbj1).xyca(false);
                    st.wb(vw, R.id.dp20).kjd(0);

                } else {

                    String color = zf.zf(R.color.colorTextU1);
                    st.wb(vw, R.id.wb1).zfys(color);
                    st.xxbj(vw, R.id.xxbj1).kjd(0);
                    st.xxbj(vw, R.id.xxbj1).xydj(true);
                    st.xxbj(vw, R.id.xxbj1).xyca(true);
                    st.wb(vw, R.id.dp20).kjd(8);

                }

            }

        });
        lb2 = st.v7lb(R.id.cjlbv7lb1);
        spq2 = IappCompat.v7lbAdapter(lb2, ExtensionPluginListItemActivity.class, R.layout.extension_plugin_list_item, new IappCompat.V7lbViewBinder() {

            public void bind(Object ua, int pn, View vw) {

            }

        });

    }

    public void cjlbview(int a) {

        final int i = a;

        final i.runlibrary.app.v.xxbj root = st.xxbj(R.id.xxbj1);

        final i.runlibrary.app.v.xxbj load = st.xxbj(R.id.xxbj3);

        final i.runlibrary.app.v.xxbj cd = st.xxbj(R.id.xxbj2);

        final i.runlibrary.app.v.xxbj add = st.xxbj(R.id.xxbj4);

        final i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(R.id.qtgd1);
        gj.jmxc(new Runnable() {

            public void run() {

                if (i == 0) {

                    root.kjd(0);
                    load.kjd(8);
                    cd.kjd(8);
                    add.kjd(8);

                } else if (i == 1) {

                    root.kjd(8);
                    load.kjd(0);
                    cd.kjd(8);
                    add.kjd(8);

                } else if (i == 2) {

                    root.kjd(8);
                    load.kjd(8);
                    cd.kjd(0);
                    add.kjd(8);

                } else if (i == 3) {

                    root.kjd(8);
                    load.kjd(8);
                    cd.kjd(8);
                    add.kjd(0);

                }
                qtgd1.v.requestFocus();

            }

        });

    }

    public void cjlbcd(int i) {

        if (i == 0) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf("< 扩展插件");
                    cjlbview(0);

                }

            });

        } else if (i == 4) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf("添加扩展");
                    st.bjk(R.id.bjk1).zf("");
                    cjlbview(3);
                    bjkmode = 1;

                }

            });

        } else if (i == 5) {

            String cachehd1 = AppPaths.appPath("data/cjlb/lbx_title");

            final String bz = wj.dqwb(cachehd1);
            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf("扩展备注");
                    st.bjk(R.id.bjk1).zf(bz);
                    cjlbview(3);
                    bjkmode = 2;

                }

            });

        } else {

            cjlbview(2);
            cjlbcdspq.sc();

            int[] kj = new int[]{R.id.wb1, R.id.wb2, R.id.wb3, R.id.wb4, R.id.wb5, -1};

            Object[] jk = new Object[]{"", "", "", "", "", 0};

            if (i == 1) {

                jk = new Object[]{"刷新", "", "", "", "", 0};
                cjlbcdspq.j(kj, jk);
                jk = new Object[]{"添加扩展", "", "", "", "", 0};
                cjlbcdspq.j(kj, jk);
                jk = new Object[]{"新建扩展", "", "", "", "", 0};
                cjlbcdspq.j(kj, jk);
                jk = new Object[]{"退出", "", "", "", "", 0};
                cjlbcdspq.j(kj, jk);

            } else if (i == 2) {

                jk = new Object[]{"备注扩展", "", "", "", "", 0};
                cjlbcdspq.j(kj, jk);
                jk = new Object[]{"移除扩展", "", "", "", "", 0};
                cjlbcdspq.j(kj, jk);
                jk = new Object[]{"清除扩展缓存", "", "", "", "", 0};

            }
            jk = new Object[]{"返回", "back", "", "", "", 1};
            cjlbcdspq.j(kj, jk);

            String spxx = AppPaths.appPath("settings/a3.inf");
            jk = new Object[]{"", "", "", "", "", -1};
            cjlbcdspq.j(kj, jk);

            String axc = wj.dqwb(spxx);

            if (zf.dy(axc, "1") || zf.dy(axc, "3")) {

                jk = new Object[]{"", "", "", "", "", -1};
                cjlbcdspq.j(kj, jk);
                jk = new Object[]{"", "", "", "", "", -1};
                cjlbcdspq.j(kj, jk);

            }
            gj.jmxc(new Runnable() {

                public void run() {

                    cjlbcdspq.sx();
                    st.v7lb(R.id.cjlbv7lb2).xzwz("top");
                    st.wb(R.id.title_bar).zf("菜单");

                }

            });

        }

    }

    public void dj_cjlbcd(Object a, Object b, Object c, Object d) {

        if (zf.dy(a, "返回")) {

            cjlbcd(0);

        } else if (zf.dy(a, "备注扩展")) {

            String cachehd = AppPaths.appPath("data/cjlb/lbx_id");

            String id = wj.dqwb(cachehd);
            st.bjk(R.id.bjk1).zf("");
            st.bjk(R.id.bjk1).tszf("请输入扩展备注");
            st.bjk(R.id.bjk1).wblx("text");
            cjlbcd(5);

        } else if (zf.dy(a, "刷新")) {

            cjlbcd(0);
            cjlbsx();

        } else if (zf.dy(a, "添加扩展")) {

            st.bjk(R.id.bjk1).zf("");
            st.bjk(R.id.bjk1).tszf("请输入扩展ID");
            st.bjk(R.id.bjk1).wblx("number");
            cjlbcd(4);

        } else if (zf.dy(a, "新建扩展")) {

            e1.goplu("20003", "新建扩展", false);
            cjlbcd(0);

        } else if (zf.dy(a, "移除扩展")) {

            cjlbcd(0);

            String nr = "";

            String cachehd = AppPaths.appPath("data/cjlb/lbx_id");

            String id = wj.dqwb(cachehd);

            String sjhc = AppPaths.appPath("data/cjlb/chat/") + id;

            int u = e11.findid_pluginlist(id);

            String uss = String.valueOf(u);

            boolean b1 = e11.delete_pluginlist(uss);
            wj.sc(sjhc);

            if (b1) {

                nr = "移除成功";

            } else {

                nr = "移除失败";

            }
            cjlbcd(0);
            cjlbsx();
            e1.tsk("提示", nr);

        } else if (zf.dy(a, "清除扩展缓存")) {

            cjlbcd(0);

            String nr = "";

            String cachehd = AppPaths.appPath("data/cjlb/lbx_id");

            String id = wj.dqwb(cachehd);

            String sjhc = AppPaths.appPath("data/cjlb/chat/") + id;

            boolean b1 = (wj.sc(sjhc) > 0);

            if (b1) {

                nr = "清除成功";

            } else {

                nr = "清除失败";

            }
            e1.tsk("提示", nr);
            cjlbcd(0);

        } else if (zf.dy(a, "退出")) {

            cjlbcd(0);
            gj.gb();

        } else {

        }

    }

    public void cjlbsx() {

        final String set1 = AppPaths.appPath("settings/f11/set1.inf");
        gj.xc(new Thread() {

            public void run() {

                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.title_bar).zf("< 扩展插件");
                        cjlbview(1);

                    }

                });

                final int[] kj = new int[]{R.id.wb1, R.id.wb2, R.id.wb3};

                String[] jk = new String[]{"", "", ""};
                spq2.sc();

                String ab = AppPaths.appPath("settings/candebug");

                if (wj.cz(ab) && zf.dy(wj.dqwb(ab), "true")) {

                } else {

                    try {

                        e11.delete_pluginlist(e11.findid_pluginlist("404"));

                    } catch (Throwable __$_e__) {

                    }

                }

                String table = "_id,uid,name,utext";

                Object sqlx = null;

                i.runlibrary.app.sj$cxsj sjk15 = (i.runlibrary.app.sj$cxsj) (Object) sjk.cxsj("pluginlist", table, sqlx);

                while (sjk15.xyh()) {

                    jk = new String[]{sjk15.sj(2), sjk15.sj(1), sjk15.sj(3)};
                    spq2.j(kj, jk);

                }
                gj.jmxc(new Runnable() {

                    public void run() {

                        spq2.sx();
                        cjlbview(0);

                    }

                });

            }

        });

    }

    public void bjsj() {

        final String kzsjhc = AppPaths.appPath("data/cjlb/chat/");

        final String set2 = AppPaths.appPath("settings/f19/set2.inf");

        String bjknr = st.bjk(R.id.bjk1).zf();
        bjknr = zf.qctwkg(bjknr);

        final String bjknrs = bjknr;

        if (zf.dy(bjknrs, "")) {

            if (bjkmode == 2) {

                e1.tsk("提示", "扩展备注不能为空");

            } else if (bjkmode == 1) {

                e1.tsk("提示", "扩展ID不能为空");

            }

        } else {

            if (bjkmode == 2) {

                String cachehd = AppPaths.appPath("data/cjlb/lbx_id");

                final String id = wj.dqwb(cachehd);
                gj.xc(new Thread() {

                    public void run() {

                        String nr = "";
                        cjlbview(1);

                        int u = e11.findid_pluginlist(id);

                        String uss = String.valueOf(u);
                        gj.sc(u);

                        boolean b1 = e11.update_pluginlist(uss, "name", bjknrs);
                        gj.sc(b1);

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

                gj.xc(new Thread() {

                    public void run() {

                        cjlbview(1);
                        gj.zt(500);

                        String nr = "";

                        i.runlibrary.app.zf$zzbds zz = (i.runlibrary.app.zf$zzbds) (Object) zf.zzbds(bjknrs, "[0-9]*$", 0);

                        boolean b2 = zz.sfppcg();

                        if (b2) {

                            String b4 = "";

                            boolean can = true;

                            if (zf.dy(bjknrs, "404")) {

                                String ab = AppPaths.appPath("settings/candebug");

                                if (wj.cz(ab) && zf.dy(wj.dqwb(ab), "true")) {

                                    b4 = "App Debug";

                                } else {

                                    can = false;

                                }

                            } else if (zf.dy(bjknrs, "10000")) {

                                b4 = "扩展推荐";

                            } else if (zf.dy(bjknrs, "10001")) {

                                b4 = "文件下载";

                            } else if (zf.dy(bjknrs, "10002")) {

                                b4 = "文本翻译";

                            } else if (zf.dy(bjknrs, "10004")) {

                                b4 = "网络直播";

                            } else if (zf.dy(bjknrs, "10005")) {

                                b4 = "位置信息";

                            } else if (zf.dy(bjknrs, "10007")) {

                                b4 = "应用列表";

                            } else if (zf.dy(bjknrs, "10010")) {

                                b4 = "WearOS工具箱";

                            } else if (zf.dy(bjknrs, "10011")) {

                                b4 = "TuneFree";

                            } else if (zf.dy(bjknrs, "10015")) {

                                b4 = "腕管远控";

                            } else {

                                try {

                                    String url = e1.urlUnlockString(wj.dqwb(set2));

                                    if (zf.cjw(url, "/")) {

                                    } else {

                                        url = url + "/";

                                    }

                                    String idget = url + "project/" + bjknrs + ".json";

                                    String st5 = wl.hq(idget, null, "utf-8", null, true, null, 20000, 20000, null);

                                    if (zf.dy(st5, null)) {

                                        can = false;

                                    } else {

                                        String _b4 = e1.readJson(st5, "room");
                                        _b4 = _b4;
                                        b4 = _b4;
                                        wj.xrwb(kzsjhc + bjknrs, st5);

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
            wj.xrwb(cachehd, a);

            String cachehd2 = AppPaths.appPath("data/ltlb/lbx_title");
            wj.xrwb(cachehd2, b);

            final String[] name = new String[]{"uid"};

            final String[] value = new String[]{a};
            gj.jmxc(new Runnable() {

                public void run() {

                    gj.tz(ChatRoomActivity.class, name, value);

                }

            });

        } else if (i == 10001) {

            gj.jmxc(new Runnable() {

                public void run() {

                    gj.tz(AppDownloadActivity.class);

                }

            });

        } else if (i == 10002) {

            gj.jmxc(new Runnable() {

                public void run() {

                    gj.tz(TextTranslateActivity.class);

                }

            });

        } else if (i == 10003) {

            e11.delete_pluginlist(e11.findid_pluginlist("10003"));
            e1.tsk("提示", "此扩展已废弃,已自动为您移除,请留意扩展推荐");

        } else if (i == 10004) {

            gj.jmxc(new Runnable() {

                public void run() {

                    gj.tz(LiveStreamActivity.class);

                }

            });

        } else if (i == 10005) {

            gj.jmxc(new Runnable() {

                public void run() {

                    gj.tz(LocationInfoActivity.class);

                }

            });

        } else if (i == 10006) {

            e11.delete_pluginlist(e11.findid_pluginlist("10006"));
            e1.tsk("提示", "此扩展已废弃,已自动为您移除,请留意扩展推荐");

        } else if (i == 10007) {

            gj.jmxc(new Runnable() {

                public void run() {

                    gj.tz(InstalledAppListActivity.class);

                }

            });

        } else if (i == 10008) {

            e11.delete_pluginlist(e11.findid_pluginlist("10008"));
            e1.tsk("提示", "此扩展已废弃,已自动为您移除,请留意扩展推荐");

        } else if (i == 10009) {

            e11.delete_pluginlist(e11.findid_pluginlist("10009"));
            e1.tsk("提示", "此扩展已废弃,已自动为您移除,请留意扩展推荐");

        } else if (i == 10010) {

            gj.jmxc(new Runnable() {

                public void run() {

                    gj.tz(WearOsToolboxActivity.class);

                }

            });

        } else if (i == 10011) {

            gj.jmxc(new Runnable() {

                public void run() {

                    gj.tz(TuneFreeActivity.class);

                }

            });

        } else if (i == 10012) {

            e11.delete_pluginlist(e11.findid_pluginlist("10012"));
            e1.tsk("提示", "此扩展已废弃,已自动为您移除,请留意扩展推荐");

        } else if (i == 10015) {

            gj.jmxc(new Runnable() {

                public void run() {

                    gj.tz(RemoteControlActivity.class);

                }

            });

        } else if (i == 404) {

            gj.jmxc(new Runnable() {

                public void run() {

                    gj.tz(AppDebugActivity.class);

                }

            });

        } else {

            isR = true;

        }
        return isR;

    }
}
