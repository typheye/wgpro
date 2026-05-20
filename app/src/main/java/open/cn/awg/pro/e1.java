/**
 ******************************************************************************
 * @file    e1.java
 * @author  Typheye
 * @brief   Shared utility bridge for UI, dialogs, and error reporting.
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
package open.cn.awg.pro;

import com.file.Xor.xor;

import i.app.iClass;

public class e1 extends iClass {

    public static i.runlibrary.app.v.v7lb$UserAdapter spq = null;

    public static i.runlibrary.app.v.v7lb lb = null;

    public static i.runlibrary.app.v.v7lb$UserAdapter cdspq = null;

    public static i.runlibrary.app.v.v7lb cdlb = null;

    public static i.runlibrary.app.v.v7lb$UserAdapter sxspq = null;

    public static i.runlibrary.app.v.v7lb sxlb = null;

    public static i.runlibrary.app.v.v7lb$UserAdapter dkspq = null;

    public static i.runlibrary.app.v.v7lb dklb = null;

    public static android.content.Context context;

    public static java.lang.String d1_rootpaths;
    public final e1 lei = this, 类 = this;
    public open.cn.awg.pro.e11 e11 = new e11(_APPINFO);

    public e1(i.runlibrary.app.AppInfo _APPINFO) {
        super(_APPINFO);

    }

    public void getlic() {

        final java.lang.String auid = "/data/user/0/open.cn.awg.pro/settings/account/user";

        final java.lang.String sec_license = "/data/user/0/open.cn.awg.pro/data/sec/license";

        java.lang.String set2e = "/data/user/0/open.cn.awg.pro/settings/f10/set2.inf";
        set2e = wj.dqwb(set2e);
        set2e = urlUnlockString(set2e);

        if (zf.cjw(set2e, "/")) {

        } else {

            set2e = set2e + "/";

        }
        set2e = set2e + "config_v2.json";

        final java.lang.String url = set2e;
        gj.xc(new java.lang.Thread() {

            public void run() {

                java.lang.String setback = "";

                java.lang.String version = "";

                java.lang.String allow = "";

                java.lang.String back = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                if (zf.dy(back, "") || zf.dy(back, null)) {

                    setback = "0";

                } else {

                    setback = htmlToText(back);

                    if (zf.dy(setback, "0")) {

                        setback = "0";

                    } else {

                        version = readJson(setback, "UpdateVersion");
                        allow = readJson(setback, "UpdateAllow");
                        setback = String.valueOf(1);

                    }

                }

                if (zf.dy(setback, "0")) {

                } else {

                    if ((zf.dy(allow, "0") || zf.cz(allow, wj.dqwb(auid))) && islogin()) {

                        wj.xrwb(sec_license, sj.md5(setback));

                    } else {

                        wj.sc(sec_license);

                    }

                }

            }

        });

    }

    public void update_v2(int a11, java.lang.String vn_) {

        final java.lang.String auid = "/data/user/0/open.cn.awg.pro/settings/account/user";

        final java.lang.String sec_updater = "/data/user/0/open.cn.awg.pro/data/sec/updater";

        final int myappversion = a11;

        final java.lang.String vn = vn_;

        java.lang.String set2e = "/data/user/0/open.cn.awg.pro/settings/f10/set2.inf";
        set2e = wj.dqwb(set2e);
        set2e = urlUnlockString(set2e);

        if (zf.cjw(set2e, "/")) {

        } else {

            set2e = set2e + "/";

        }
        set2e = set2e + "config_v2.json";

        final java.lang.String url = set2e;
        gj.xc(new java.lang.Thread() {

            public void run() {

                if (yz()) {

                    java.lang.String setback = "";

                    java.lang.String version = "";

                    java.lang.String versionname = "";

                    java.lang.String allow = "";

                    java.lang.String back = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                    if (zf.dy(back, "") || zf.dy(back, null)) {

                        setback = "0";

                    } else {

                        setback = htmlToText(back);

                        if (zf.dy(setback, "0")) {

                            setback = "0";

                        } else {

                            version = readJson(setback, "UpdateVersion");
                            versionname = readJson(setback, "UpdateVersionName");
                            allow = readJson(setback, "UpdateAllow");
                            setback = String.valueOf(1);

                        }

                    }

                    if (zf.dy(setback, "0")) {

                    } else {

                        if ((zf.dy(allow, "0") || zf.cz(allow, wj.dqwb(auid))) && islogin()) {

                            wj.xrwb(sec_updater, setback);

                            java.lang.String ek = "";

                            final int b1 = Integer.parseInt(version);

                            if (myappversion < b1 && b1 != 9999999) {

                                final java.lang.String eks = ek;
                                gj.jmxc(new java.lang.Runnable() {

                                    public void run() {

                                        gj.tz(f22.class);

                                    }

                                });

                            } else {

                                int ccc = versionname.compareTo(vn);

                                if (!zf.dy(versionname, vn) && ccc > 0) {

                                    final java.lang.String eks = ek;
                                    gj.jmxc(new java.lang.Runnable() {

                                        public void run() {

                                            gj.tz(f22.class);

                                        }

                                    });

                                } else {

                                    wj.sc(sec_updater);

                                }

                            }

                        } else {

                            wj.sc(sec_updater);

                        }

                    }

                } else {

                }

            }

        });

    }

    public void app_backup() {

        if (islogin()) {

            java.lang.String a = "/data/user/0/open.cn.awg.pro/settings/account/user";

            java.lang.String tmppath = "/data/user/0/open.cn.awg.pro/data/backup/tmp.awgbak";

            java.lang.String tmp2path = "/data/user/0/open.cn.awg.pro/data/backup/.nomedia";
            a = sj.md5(wj.dqwb(a));

            java.lang.String filename = xt.sj(4) + ".awgbak";

            java.lang.String backpath = "/data/user/0/open.cn.awg.pro/settings";

            java.lang.String mkdirpath = "/storage/emulated/0/Android/data/open.cn.awg.pro/files/backup/.nomedia";

            java.lang.String outpath = "/storage/emulated/0/Android/data/open.cn.awg.pro/files/backup/" + filename;

            java.lang.String out2path = "/storage/emulated/0/Download/" + filename;
            wj.xrwb(mkdirpath, "");
            wj.xrwb(tmp2path, "");

            int i = wj.ys(backpath, tmppath);
            xor.FileXor(tmppath, outpath, a);
            wj.sc(tmppath, true);
            wj.sc(mkdirpath, true);
            wj.sc(tmp2path, true);

            if (i > 0) {

                if (wj.fz(outpath, out2path, true)) {

                    wj.sc(outpath, true);
                    tsk("提示", "导出备份成功\n请勿修改备份文件名，否则将无法导入！\n\n[文件路径]\n" + out2path);

                } else {

                    tsk("提示", "导出备份成功\n请勿修改备份文件名，否则将无法导入！\n\n[文件路径]\n" + outpath);

                }

            } else {

                tsk("提示", "导出备份失败");

            }

        } else {

            gj.tz(n1.class);
            tsk("提示", "该操作需要登录Typheye账户");

        }

    }

    public boolean app_backup2() {

        boolean bk = false;

        if (islogin()) {

            java.lang.String a = "/data/user/0/open.cn.awg.pro/settings/account/user";

            java.lang.String tmppath = "/data/user/0/open.cn.awg.pro/data/backup/tmp.awgbak";

            java.lang.String tmp2path = "/data/user/0/open.cn.awg.pro/data/backup/.nomedia";
            a = sj.md5(wj.dqwb(a));

            java.lang.String filename = "last.awgbak.zip";

            java.lang.String backpath = "/data/user/0/open.cn.awg.pro/settings";

            java.lang.String mkdirpath = "/data/user/0/open.cn.awg.pro/data/backup/yun/.nomedia";

            java.lang.String outpath = "/data/user/0/open.cn.awg.pro/data/backup/yun/" + filename;

            if (wj.cz(outpath)) {

                wj.sc(outpath);

            }
            wj.xrwb(mkdirpath, "");
            wj.xrwb(tmp2path, "");

            int i = wj.ys(backpath, tmppath);
            xor.FileXor(tmppath, outpath, a);
            wj.sc(tmppath, true);
            wj.sc(tmp2path, true);
            wj.sc(mkdirpath, true);

            if (i > 0) {

                bk = true;

            } else {

                tsk("提示", "导出备份失败");

            }

        } else {

            tsk("提示", "该操作需要登录Typheye账户");

        }
        return bk;

    }

    public boolean app_recovery(java.lang.String path) {

        java.lang.String tmp2path = "/data/user/0/open.cn.awg.pro/data/backup/.nomedia";

        java.lang.String tmppath = "/data/user/0/open.cn.awg.pro/data/backup/tmp.awgbak";

        java.lang.String a = "/data/user/0/open.cn.awg.pro/settings/account/user";
        a = sj.md5(wj.dqwb(a));

        java.lang.String backpath = "/data/user/0/open.cn.awg.pro/settings/";
        wj.xrwb(tmp2path, "");
        wj.fz(path, tmppath, true);
        xor.FileXor(tmppath, tmppath + "_", a);

        int i = wj.jy(tmppath + "_", backpath, true);
        wj.sc(tmppath, true);
        wj.sc(tmppath + "_", true);
        wj.sc(tmp2path, true);

        if (i > 0) {

            gj.zt(1000);
            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    java.lang.String[] name = new java.lang.String[]{"Msg"};

                    java.lang.String[] value = new java.lang.String[]{"导入成功 点击重启"};
                    gj.tz(a0.class, name, value);

                }

            });
            return true;

        } else {

            tsk("提示", "导入备份失败，请参考\"重要说明\"");

        }
        return false;

    }

    public boolean app_gorecovery() {

        i.runlibrary.app.xt$qxgl a = xt.qxgl();

        if (a.qx("android.permission.WRITE_EXTERNAL_STORAGE")) {

            if (islogin()) {

                java.lang.String lists = "";

                java.lang.String[] gl = new java.lang.String[]{"*.awgbak"};

                java.io.File[] lb = wj.czwj("/storage/emulated/0/", gl);

                for (java.io.File c : lb) {

                    if (zf.dy(lists, "")) {

                        lists = c.getAbsolutePath();

                    } else {

                        lists = lists + "\n" + c.getAbsolutePath();

                    }

                }

                long test0 = 100000000;

                java.lang.String endpath = "";

                java.lang.String[] d = zf.fg(lists, "\n", true);

                if (d.length > 0) {

                    for (java.lang.String e : d) {

                        java.lang.String[] bb;
                        bb = fileinfo("/storage/emulated/0/", e);

                        if (zf.dy(bb[0], "true")) {

                            java.lang.String sts = zf.qc(bb[3], null, ".awgbak");

                            long test1 = sj.zh().zlong(sts, 0);

                            if (test1 > test0) {

                                test0 = test1;
                                endpath = e;

                            }

                        }

                    }

                    if (zf.dy(endpath, "")) {

                        tsk("提示", "未在设备存储中找到备份文件");

                    } else {

                        return app_recovery(endpath);

                    }

                } else {

                    tsk("提示", "未在设备存储中找到备份文件");

                }

            } else {

                gj.gb();
                gj.tz(n1.class);
                tsk("提示", "该操作需要登录腕管Pro账户");

            }

        } else {

            tsk("提示", "您未授权应用\"修改或删除您共享存储空间中的内容\"，请先前往设置授权后再操作");

        }
        return false;

    }

    public void goplu(java.lang.Object pluid, java.lang.Object pluname, boolean p) {

        if (p) {

            e11.add_pluginlist(pluid, pluname, pluid + "-" + pluname);

        }

        java.lang.String cachehd = "/data/user/0/open.cn.awg.pro/data/cjlb/lbx_id";
        wj.xrwb(cachehd, pluid);

        java.lang.String cachehd2 = "/data/user/0/open.cn.awg.pro/data/cjlb/lbx_title";
        wj.xrwb(cachehd2, pluname);

        open.cn.awg.pro.e14 e14 = new e14(_APPINFO);

        java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/f19/set1.inf";

        if (zf.dy(wj.dqwb(set1), "true")) {

            if (e14.newG(Integer.parseInt(String.valueOf(pluid)))) {

                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        gj.tz(m2.class);

                    }

                });

            } else {

                tsk("提示", "没有可执行此操作的扩展");

            }

        } else {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    gj.tz(m2.class);

                }

            });

        }

    }

    public java.lang.String awgwl_getuid() {

        java.lang.String a = "/data/user/0/open.cn.awg.pro/settings/account/user";

        java.lang.String b = wj.dqwb(a);

        if (zf.dy(b, null) || zf.dy(b, "") || zf.dy(b, "null")) {

            b = "0";

        }
        return b;

    }

    public boolean islogin() {

        java.lang.String a = "/data/user/0/open.cn.awg.pro/settings/account/user";

        java.lang.String b = "/data/user/0/open.cn.awg.pro/settings/account/pass";

        return wj.cz(a) && wj.cz(b);

    }

    public boolean awgwl_state() {

        java.lang.String ic = "/data/user/0/open.cn.awg.pro/settings/f11/true";

        return wj.cz(ic) && zf.dy(wj.dqwb(ic), "1");

    }

    public boolean awgwl_true() {

        java.lang.String ic = "/data/user/0/open.cn.awg.pro/settings/f11/true";

        boolean f = wj.xrwb(ic, "1");

        return f;

    }

    public void awgwl_false() {

        java.lang.String ic = "/data/user/0/open.cn.awg.pro/settings/f11/true";
        wj.sc(ic);

    }

    public int getNowUserId() {

        int b = -1;

        try {

            b = android.os.Process.myUid() / 100000;

        } catch (java.lang.Throwable __$_e__) {

            b = -1;

        }
        return b;

    }

    public boolean isJhs() {

        boolean zt = false;

        java.lang.String a = "/data/user/0/open.cn.awg.pro/settings/f7/Enable2";

        if (wj.cz(a) && !zf.dy(wj.dqwb(a), "")) {

            zt = true;

        } else {

            gj.tz(f7.class);

        }
        return zt;

    }

    public void zfsb(java.lang.Object id) {

        final java.lang.Object i = id;
        gj.xc(new java.lang.Thread() {

            public void run() {

                gj.zt(1000);

                if (zf.dy(i, "a1")) {

                }

            }

        });

    }

    public void zfwc(java.lang.Object id) {

        final java.lang.Object i = id;
        gj.xc(new java.lang.Thread() {

            public void run() {

                gj.zt(1000);

                if (zf.dy(i, "a1")) {

                    jh();
                    tsk("提示", "应用激活成功");

                } else if (zf.dy(i, "a0")) {

                    tsk("提示", "捐赠成功，感谢您的支持！");

                }

            }

        });

    }

    public void jh() {

        java.lang.String a = String.valueOf(zf.sjs(1, 9));

        int i = 14;

        while (i > 0) {

            java.lang.String b = String.valueOf(zf.sjs(0, 9));
            a = a + b;
            i = i - 1;

        }

        java.lang.String j = "/data/user/0/open.cn.awg.pro/settings/f7/Enable2";
        wj.xrwb(j, a);

    }

    public boolean yz() {
//  java.lang.String sec_isunlock="/data/user/0/open.cn.awg.pro/data/sec/unlock";
//   wj.xrwb(sec_isunlock,"true");
//  return true;
        context = open.cn.awg.pro.e6.getContext();

        java.lang.String sec_isunlock = "/data/user/0/open.cn.awg.pro/data/sec/unlock";

        java.lang.String urls = "/data/user/0/open.cn.awg.pro/settings/f10/set2.inf";
        urls = wj.dqwb(urls);
        urls = urlUnlockString(urls);

        if (zf.cjw(urls, "/")) {

        } else {

            urls = urls + "/";

        }

        java.lang.String ljf = wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a17");

        java.lang.String a2 = com.kingqi.zwcj.应用工具.获取应用签名(context, "open.cn.awg.pro");

        java.lang.String path = com.kingqi.zwcj.应用工具.获取应用apk文件(context, "open.cn.awg.pro");

        java.io.File mfile;
        mfile = new java.io.File(path);

        java.lang.String aa = sj.md5(mfile);

        int a11;

        java.lang.String vn;
        a11 = com.demo.e4.getVersionCode(context);
        vn = com.demo.e4.getVersionName(context);

        java.lang.String bb = String.valueOf(a11);

        java.lang.String url = urls + "x.php?token=" + a2 + ljf + "id=" + aa + ljf + "ver=" + bb + ljf + "vername=" + vn;

        java.lang.String st5 = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

        if (zf.dy(st5, null)) {

            return false;

        } else {

            wj.xrwb(sec_isunlock, st5);

            return zf.dy(st5, "true");

        }

    }

    public boolean isJh() {

        boolean zt = false;

        java.lang.String a = "/data/user/0/open.cn.awg.pro/settings/f7/Enable2";

        if (wj.cz(a) && !zf.dy(wj.dqwb(a), "")) {

            zt = true;

        }
        return zt;

    }

    public void csh() {

        java.lang.String cachehd = "/data/user/0/open.cn.awg.pro/cache/hd";
        wj.xrwb(cachehd, "");
        context = open.cn.awg.pro.e6.getContext();
        lb = st.v7lb(open.cn.awg.pro.R.id.v7lb1);
        spq = lb.v7lbspq(c2.class, open.cn.awg.pro.R.layout.c2, new i.runlibrary.app.v.v7lb$OnUserAdapterView() {

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
        cdlb = st.v7lb(open.cn.awg.pro.R.id.v7lb2);
        cdspq = cdlb.v7lbspq(c4.class, open.cn.awg.pro.R.layout.c4, new i.runlibrary.app.v.v7lb$OnUserAdapterView() {

            public void getView(i.runlibrary.app.v.v7lb$UserAdapter ua, int pn, android.view.View vw) {

                i.runlibrary.app.sj$lb lbsj = sj.lb(vw);

                int f = sj.zh(lbsj.lbsj(pn, -1)).zint();

                if (f == 1) {

                    java.lang.String color = zf.zf(open.cn.awg.pro.R.color.colorTextFalse);
                    st.wb(vw, open.cn.awg.pro.R.id.wb1).zfys(color);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).kjd(0);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xydj(true);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xyca(true);
                    st.wb(vw, open.cn.awg.pro.R.id.dp20).kjd(8);

                } else if (f == -1) {

                    java.lang.String color = zf.zf(open.cn.awg.pro.R.color.colorTextU1);
                    st.wb(vw, open.cn.awg.pro.R.id.wb1).zfys(color);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).kjd(8);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xydj(false);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xyca(false);
                    st.wb(vw, open.cn.awg.pro.R.id.dp20).kjd(0);

                } else {

                    java.lang.String color = zf.zf(open.cn.awg.pro.R.color.colorTextU1);
                    st.wb(vw, open.cn.awg.pro.R.id.wb1).zfys(color);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).kjd(0);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xydj(true);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xyca(true);
                    st.wb(vw, open.cn.awg.pro.R.id.dp20).kjd(8);

                }

            }

        });
        sxlb = st.v7lb(open.cn.awg.pro.R.id.v7lb3);
        sxspq = sxlb.v7lbspq(c5.class, open.cn.awg.pro.R.layout.c5, new i.runlibrary.app.v.v7lb$OnUserAdapterView() {

            public void getView(i.runlibrary.app.v.v7lb$UserAdapter ua, int pn, android.view.View vw) {

                i.runlibrary.app.sj$lb lbsj = sj.lb(vw);

                int f = sj.zh(lbsj.lbsj(pn, -1)).zint();

                if (f == 1) {

                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).dqfs("center");
                    st.wb(vw, open.cn.awg.pro.R.id.wb2).kjd(8);

                    java.lang.String color = zf.zf(open.cn.awg.pro.R.color.colorTextFalse);
                    st.wb(vw, open.cn.awg.pro.R.id.wb1).zfys(color);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).kjd(0);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xydj(true);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xyca(true);
                    st.wb(vw, open.cn.awg.pro.R.id.dp20).kjd(8);

                } else if (f == -1) {

                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).dqfs("left");
                    st.wb(vw, open.cn.awg.pro.R.id.wb2).kjd(0);

                    java.lang.String color = zf.zf(open.cn.awg.pro.R.color.colorTextU1);
                    st.wb(vw, open.cn.awg.pro.R.id.wb1).zfys(color);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).kjd(8);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xydj(false);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xyca(false);
                    st.wb(vw, open.cn.awg.pro.R.id.dp20).kjd(0);

                } else {

                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).dqfs("left");
                    st.wb(vw, open.cn.awg.pro.R.id.wb2).kjd(0);

                    java.lang.String color = zf.zf(open.cn.awg.pro.R.color.colorTextU1);
                    st.wb(vw, open.cn.awg.pro.R.id.wb1).zfys(color);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).kjd(0);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xydj(true);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xyca(true);
                    st.wb(vw, open.cn.awg.pro.R.id.dp20).kjd(8);

                }

            }

        });
        dklb = st.v7lb(open.cn.awg.pro.R.id.v7lb4);
        dkspq = dklb.v7lbspq(c6.class, open.cn.awg.pro.R.layout.c6, new i.runlibrary.app.v.v7lb$OnUserAdapterView() {

            public void getView(i.runlibrary.app.v.v7lb$UserAdapter ua, int pn, android.view.View vw) {

                i.runlibrary.app.sj$lb lbsj = sj.lb(vw);

                int f = sj.zh(lbsj.lbsj(pn, -1)).zint();

                if (f == 1) {

                    java.lang.String color = zf.zf(open.cn.awg.pro.R.color.colorTextFalse);
                    st.wb(vw, open.cn.awg.pro.R.id.wb1).zfys(color);
                    st.wb(vw, open.cn.awg.pro.R.id.wb1).ztdx(13);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).kjd(0);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xydj(true);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xyca(true);
                    st.wb(vw, open.cn.awg.pro.R.id.dp20).kjd(8);

                } else if (f == -1) {

                    java.lang.String color = zf.zf(open.cn.awg.pro.R.color.colorTextU1);
                    st.wb(vw, open.cn.awg.pro.R.id.wb1).zfys(color);
                    st.wb(vw, open.cn.awg.pro.R.id.wb1).ztdx(13);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).kjd(8);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xydj(false);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xyca(false);
                    st.wb(vw, open.cn.awg.pro.R.id.dp20).kjd(0);

                } else if (f == 0) {

                    java.lang.String color = zf.zf(open.cn.awg.pro.R.color.colorTextU1);
                    st.wb(vw, open.cn.awg.pro.R.id.wb1).zfys(color);
                    st.wb(vw, open.cn.awg.pro.R.id.wb1).ztdx(13);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).kjd(0);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xydj(true);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xyca(true);
                    st.wb(vw, open.cn.awg.pro.R.id.dp20).kjd(8);

                } else if (f == -2) {

                    java.lang.String color = zf.zf(open.cn.awg.pro.R.color.colorTextTrue);
                    st.wb(vw, open.cn.awg.pro.R.id.wb1).zfys(color);
                    st.wb(vw, open.cn.awg.pro.R.id.wb1).ztdx(13);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).kjd(0);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xydj(true);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xyca(true);
                    st.wb(vw, open.cn.awg.pro.R.id.dp20).kjd(8);

                } else {

                    java.lang.String color = zf.zf(open.cn.awg.pro.R.color.colorTextU1);
                    st.wb(vw, open.cn.awg.pro.R.id.wb1).zfys(color);
                    st.wb(vw, open.cn.awg.pro.R.id.wb1).ztdx(13);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).kjd(0);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xydj(true);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).xyca(true);
                    st.wb(vw, open.cn.awg.pro.R.id.dp20).kjd(8);

                }

            }

        });
        e11.setup_filelist();

    }

    public java.lang.String[] fileinfo(java.lang.Object root, java.lang.Object url) {

        java.lang.String Root = wj.hqml(url);

        java.io.File mfile;

        java.lang.String Runs = "true";

        java.lang.String FileUrl = "";

        java.lang.String FileFatherUrl = "";

        java.lang.String UrlName = "";

        try {

            mfile = new java.io.File(Root);
            UrlName = mfile.getName();
            FileFatherUrl = mfile.getParent();
            mfile.hashCode();
            FileUrl = mfile.getPath();

        } catch (java.lang.Throwable e) {

            Runs = "false";

        }

        java.lang.String[] Back = new java.lang.String[]{Runs, FileUrl, FileFatherUrl, UrlName};
        return Back;

    }

    public void NewFilesLoad(java.lang.Object url, java.lang.Object roots) {

        final java.lang.String set2 = "/data/user/0/open.cn.awg.pro/settings/f8/set1.inf";

        final java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/d1/set1.inf";

        final java.lang.String spxx = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        final java.lang.String set3 = "/data/user/0/open.cn.awg.pro/settings/d1/set3.inf";

        java.lang.String root = wj.hqml(roots);

        java.lang.String srcs = "";

        try {

            java.lang.String FileUrl = wj.hqml(url);

            if (zf.cjw(FileUrl, "/")) {

                FileUrl = FileUrl;

            } else {

                FileUrl = FileUrl + "/";

            }
            spq.sc();

            int[] kj = new int[]{open.cn.awg.pro.R.id.tx1, open.cn.awg.pro.R.id.wb1, open.cn.awg.pro.R.id.wb2, open.cn.awg.pro.R.id.wb3, -1};

            java.lang.Object[] jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b5, "...", FileUrl, "返回上层目录", 0};

            if (zf.dy(wj.dqwb(set2), "true")) {

                srcs = getMipmap("b5");

                if (zf.dy(srcs, "0")) {

                    jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b5, "...", FileUrl, "返回上层目录", 0};

                } else {

                    jk = new java.lang.Object[]{srcs, "...", FileUrl, "返回上层目录", 0};

                }

            } else {

                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b5, "...", FileUrl, "返回上层目录", 0};

            }
            spq.j(kj, jk);

            java.lang.String[] FileXH1 = wj.lb(url, true);

            if (zf.dy(wj.dqwb(set3), "true")) {

                java.util.Arrays.sort(FileXH1);

            }

            for (java.lang.String c : FileXH1) {

                java.lang.String urlName = "";

                java.lang.String[] fhValue = fileinfo(root, c);

                if (zf.dy(fhValue[0], "true")) {

                    urlName = fhValue[3];

                } else {

                    urlName = "Error";

                }

                java.lang.String Two = "目录";

                if (zf.dy(wj.dqwb(set2), "true")) {

                    srcs = getMipmap("b5");

                    if (zf.dy(srcs, "0")) {

                        jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b5, urlName, c, Two, 0};

                    } else {

                        jk = new java.lang.Object[]{srcs, urlName, c, Two, 0};

                    }

                } else {

                    jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b5, urlName, c, Two, 0};

                }

                java.lang.String pb1 = "/data/user/0/open.cn.awg.pro/settings";

                java.lang.String pb2 = "/data/user/0/open.cn.awg.pro/data";

                java.lang.String pb3 = "/data/user/0/open.cn.awg.pro/fix";

                java.lang.String pb4 = "/data/user/0/open.cn.awg.pro/cache";

                java.lang.String pb5 = "/data/user/0/open.cn.awg.pro/files/cache";

                java.lang.String pb6 = "/storage/emulated/0/Android/data";

                java.lang.String pb7 = "/storage/emulated/0/Android/obb";

                if (zf.dy(c, pb1) || zf.dy(c, pb2) || zf.dy(c, pb3) || zf.dy(c, pb4) || zf.dy(c, pb5) || (xt.sbxx().sdk > 29 && zf.dy(c, pb6)) || (xt.sbxx().sdk > 29 && zf.dy(c, pb7))) {

                } else {

                    spq.j(kj, jk);

                }

            }

            java.lang.String[] FileXH2 = wj.lb(url, false);

            if (zf.dy(wj.dqwb(set3), "true")) {

                java.util.Arrays.sort(FileXH2);

            }

            for (java.lang.String c : FileXH2) {

                java.lang.String bt = "";

                java.lang.String[] fhValue = fileinfo(root, c);

                if (zf.dy(fhValue[0], "true")) {

                    bt = fhValue[3];

                } else {

                    bt = "Error";

                }

                double dx = ((double) (wj.dx(c)));

                double d1 = 0;

                java.lang.String d0;

                java.lang.String cc = zf.zhxx(c);

                java.lang.String wjdx = "";

                if (dx >= 0 && dx < 1024) {

                    d1 = dx;
                    d0 = com.demo.e4.doubleToString(d1);
                    wjdx = d0 + "B";

                } else if (dx >= 1024 && dx < 1048576) {

                    d1 = dx / 1024;
                    d0 = com.demo.e4.doubleToString(d1);
                    wjdx = d0 + "K";

                } else if (dx >= 1048576 && dx < 1073741824) {

                    d1 = dx / 1024 / 1024;
                    d0 = com.demo.e4.doubleToString(d1);
                    wjdx = d0 + "M";

                } else {

                    d1 = dx / 1024 / 1024 / 1024;
                    d0 = com.demo.e4.doubleToString(d1);
                    wjdx = d0 + "G";

                }

                if (zf.cjw(cc, ".apk") || zf.cjw(cc, ".xapk") || zf.cjw(cc, ".apks")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b1");

                        if (zf.dy(srcs, "0")) {

                            jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b1, bt, c, wjdx, 0};

                        } else {

                            jk = new java.lang.Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b1, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".png") || zf.cjw(cc, "jpg")) {

                    if (zf.dy(wj.dqwb(set1), "true")) {

                        jk = new java.lang.Object[]{c, bt, c, wjdx, 0};

                    } else {

                        if (zf.dy(wj.dqwb(set2), "true")) {

                            srcs = getMipmap("b11");

                            if (zf.dy(srcs, "0")) {

                                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b11, bt, c, wjdx, 0};

                            } else {

                                jk = new java.lang.Object[]{srcs, bt, c, wjdx, 0};

                            }

                        } else {

                            jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b11, bt, c, wjdx, 0};

                        }

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".mp3") || zf.cjw(cc, ".flac") || zf.cjw(cc, ".ogg")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b9");

                        if (zf.dy(srcs, "0")) {

                            jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b9, bt, c, wjdx, 0};

                        } else {

                            jk = new java.lang.Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b9, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".mp4") || zf.cjw(cc, ".mkv") || zf.cjw(cc, ".webm") || zf.cjw(cc, ".m3u8") || zf.cjw(cc, ".avi") || zf.cjw(cc, ".mts")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b8");

                        if (zf.dy(srcs, "0")) {

                            jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b8, bt, c, wjdx, 0};

                        } else {

                            jk = new java.lang.Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b8, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".txt") || zf.cjw(cc, ".xml") || zf.cjw(cc, ".java")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b13");

                        if (zf.dy(srcs, "0")) {

                            jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b13, bt, c, wjdx, 0};

                        } else {

                            jk = new java.lang.Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b13, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".zip") || zf.cjw(cc, ".rar") || zf.cjw(cc, "7z")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b16");

                        if (zf.dy(srcs, "0")) {

                            jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b16, bt, c, wjdx, 0};

                        } else {

                            jk = new java.lang.Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b16, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".html")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b6");

                        if (zf.dy(srcs, "0")) {

                            jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b6, bt, c, wjdx, 0};

                        } else {

                            jk = new java.lang.Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b6, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".pdf")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b10");

                        if (zf.dy(srcs, "0")) {

                            jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b10, bt, c, wjdx, 0};

                        } else {

                            jk = new java.lang.Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b10, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".doc") || zf.cjw(cc, ".docx")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b15");

                        if (zf.dy(srcs, "0")) {

                            jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b15, bt, c, wjdx, 0};

                        } else {

                            jk = new java.lang.Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b15, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".xls") || zf.cjw(cc, ".xlsx")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b2");

                        if (zf.dy(srcs, "0")) {

                            jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b2, bt, c, wjdx, 0};

                        } else {

                            jk = new java.lang.Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b2, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".ppt") || zf.cjw(cc, ".pptx")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b12");

                        if (zf.dy(srcs, "0")) {

                            jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b12, bt, c, wjdx, 0};

                        } else {

                            jk = new java.lang.Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b12, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".awgtheme")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b17");

                        if (zf.dy(srcs, "0")) {

                            jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b17, bt, c, wjdx, 0};

                        } else {

                            jk = new java.lang.Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b17, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".awgsash")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b18");

                        if (zf.dy(srcs, "0")) {

                            jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b18, bt, c, wjdx, 0};

                        } else {

                            jk = new java.lang.Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b18, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".sh")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b19");

                        if (zf.dy(srcs, "0")) {

                            jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b19, bt, c, wjdx, 0};

                        } else {

                            jk = new java.lang.Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b19, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".awgbak")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b20");

                        if (zf.dy(srcs, "0")) {

                            jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b20, bt, c, wjdx, 0};

                        } else {

                            jk = new java.lang.Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b20, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b14");

                        if (zf.dy(srcs, "0")) {

                            jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b14, bt, c, wjdx, 0};

                        } else {

                            jk = new java.lang.Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.b14, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                }

            }
            jk = new java.lang.Object[]{"", "", "", "", -1};
            spq.j(kj, jk);

            java.lang.String axc = wj.dqwb(spxx);

            if (zf.dy(axc, "1") || zf.dy(axc, "3")) {

                jk = new java.lang.Object[]{"", "", "", "", -1};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{"", "", "", "", -1};
                spq.j(kj, jk);

            }

            final java.lang.String ok = FileUrl;
            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    spq.sx();
                    lb.xzwz(e11.get_filelist(ok));
                    st.wb(open.cn.awg.pro.R.id.zywblj).zf(ok);

                }

            });

        } catch (java.lang.Throwable e) {

            upload_error(e, "e1.NewFilesLoad(s,s)");
            gj.gb();

        }

    }

    public void fileTouch(java.lang.Object text, java.lang.Object url) {
        d1_rootpaths = open.cn.awg.pro.abc.d1_rootpath;

        java.lang.String root = d1_rootpaths;

        if (zf.dy(text, "返回上层目录")) {

            if (zf.dy(url, root)) {

                gj.gb();

            } else {

                java.lang.String[] fhValue = fileinfo("%", url);
                NewFilesLoad(fhValue[2], root);

            }

        } else {

            if (wj.lx(url) == 2) {

                NewFilesLoad(url, root);

            } else {

                java.lang.String[] fhValue = fileinfo(root, url);

                java.lang.String bts = fhValue[3];

                java.lang.String lj = fhValue[1];

                java.lang.String ljs = fhValue[2];

                java.lang.String c = lj;

                java.lang.String cc = zf.zhxx(c);

                if (zf.cjw(cc, ".png") || zf.cjw(cc, "jpg")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("b", c, 1);

                } else if (zf.cjw(cc, ".txt") || zf.cjw(cc, ".xml") || zf.cjw(cc, ".java")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("a", c, 1);

                } else if (zf.cjw(cc, ".zip") || zf.cjw(cc, ".rar") || zf.cjw(cc, "7z")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("e", c, 1);

                } else if (zf.cjw(cc, ".html")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("a", c, 1);

                } else if (zf.cjw(cc, ".pdf")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("g", c, 1);

                } else if (zf.cjw(cc, ".doc") || zf.cjw(cc, ".docx")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("g", c, 1);

                } else if (zf.cjw(cc, ".xls") || zf.cjw(cc, ".xlsx")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("g", c, 1);

                } else if (zf.cjw(cc, ".ppt") || zf.cjw(cc, ".pptx")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("g", c, 1);

                } else if (zf.cjw(cc, ".apk") || zf.cjw(cc, ".xapk") || zf.cjw(cc, ".apks")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("f", c, 1);

                } else if (zf.cjw(cc, ".mp3") || zf.cjw(cc, ".flac") || zf.cjw(cc, ".ogg")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("c", c, 1);

                } else if (zf.cjw(cc, ".mp4") || zf.cjw(cc, ".mkv") || zf.cjw(cc, ".webm") || zf.cjw(cc, ".m3u8") || zf.cjw(cc, ".avi") || zf.cjw(cc, ".mts")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("d", c, 1);

                } else if (zf.cjw(cc, ".awgtheme")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("g", c, 1);

                } else if (zf.cjw(cc, ".awgsash")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("g", c, 1);

                } else if (zf.cjw(cc, ".sh")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("g", c, 1);

                } else if (zf.cjw(cc, ".awgbak")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("g", c, 1);

                } else {

                    cdx(6, bts, lj, ljs);

                }

            }

        }

    }

    public void dkfs(java.lang.Object z, java.lang.Object b, int e) {

        final java.lang.String choose = "/data/user/0/open.cn.awg.pro/cache/file/choose";

        final java.lang.String choose2 = "/data/user/0/open.cn.awg.pro/cache/file/choose2";

        java.lang.String xx = "";
        dkspq.sc();

        int[] kj = new int[]{open.cn.awg.pro.R.id.wb1, open.cn.awg.pro.R.id.wb2, open.cn.awg.pro.R.id.wb3, -1};

        java.lang.Object[] jk = new java.lang.Object[]{"", "", "", 0};

        if (zf.dy(z, "a")) {

            xx = "文本";

            if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h1")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h2")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h3")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h4")) {

            } else {

                jk = new java.lang.Object[]{"腕管Pro：文本浏览", "a1", b, 0};
                dkspq.j(kj, jk);
                jk = new java.lang.Object[]{"腕管Pro：文本编辑", "a2", b, 0};
                dkspq.j(kj, jk);

            }

        } else if (zf.dy(z, "b")) {

            xx = "图片";

            if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h1")) {

                jk = new java.lang.Object[]{"腕上微聊：选择附件", "h1", b, 0};
                dkspq.j(kj, jk);
                jk = new java.lang.Object[]{"PinchImageView：图片浏览", "b1", b, 0};
                dkspq.j(kj, jk);

            } else if (wj.cz(choose2) && zf.dy(wj.dqwb(choose2), "h1")) {

                jk = new java.lang.Object[]{"账户头像：选择附件", "i1", b, 0};
                dkspq.j(kj, jk);
                jk = new java.lang.Object[]{"PinchImageView：图片浏览", "b1", b, 0};
                dkspq.j(kj, jk);

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h2")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h3")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h4")) {

            } else {

                jk = new java.lang.Object[]{"PinchImageView：图片浏览", "b1", b, 0};
                dkspq.j(kj, jk);

            }

        } else if (zf.dy(z, "c")) {

            xx = "音频";

            if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h1")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h2")) {

                jk = new java.lang.Object[]{"腕上微聊：选择附件", "h2", b, 0};
                dkspq.j(kj, jk);
                jk = new java.lang.Object[]{"DKPlayer：媒体浏览", "c2", b, 0};
                dkspq.j(kj, jk);

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h3")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h4")) {

            } else {

                jk = new java.lang.Object[]{"DKPlayer：媒体浏览", "c2", b, 0};
                dkspq.j(kj, jk);
                jk = new java.lang.Object[]{"WearMusic：音频浏览", "c1", b, -2};

                boolean apppd1;
                apppd1 = com.demo.e8.app(context, "cn.wearbbs.music");

                if (apppd1) {

                    dkspq.j(kj, jk);

                }
                jk = new java.lang.Object[]{"抬腕视频：音频浏览", "c3", b, -2};

                boolean apppd2;
                apppd2 = com.demo.e8.app(context, "com.hankmi.wearmusic");

                if (apppd2) {

                    dkspq.j(kj, jk);

                }

            }

        } else if (zf.dy(z, "d")) {

            xx = "视频";

            if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h1")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h2")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h3")) {

                jk = new java.lang.Object[]{"腕上微聊：选择附件", "h3", b, 0};
                dkspq.j(kj, jk);
                jk = new java.lang.Object[]{"DKPlayer：媒体浏览", "d2", b, 0};
                dkspq.j(kj, jk);

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h4")) {

            } else {

                jk = new java.lang.Object[]{"DKPlayer：媒体浏览", "d2", b, 0};
                dkspq.j(kj, jk);
                jk = new java.lang.Object[]{"腕上视频：视频浏览", "d3", b, -2};

                boolean apppd1;
                apppd1 = com.demo.e8.app(context, "cn.luern0313.wristvideoplayer");

                if (apppd1) {

                    dkspq.j(kj, jk);

                }
                jk = new java.lang.Object[]{"抬腕视频：视频浏览", "d4", b, -2};

                boolean apppd2;
                apppd2 = com.demo.e8.app(context, "com.hankmi.media");

                if (apppd2) {

                    dkspq.j(kj, jk);

                }

            }

        } else if (zf.dy(z, "e")) {

            xx = "压缩";

            if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h1")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h2")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h3")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h4")) {

                jk = new java.lang.Object[]{"腕上微聊：选择附件", "h4", b, 0};
                dkspq.j(kj, jk);

            } else {

                jk = new java.lang.Object[]{"腕管Pro：解压到当前目录", "e1", b, 0};
                dkspq.j(kj, jk);

            }

        } else if (zf.dy(z, "f")) {

            xx = "应用";

            if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h1")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h2")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h3")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h4")) {

            } else {

                jk = new java.lang.Object[]{"腕管Pro：应用安装", "f1", b, 0};
                dkspq.j(kj, jk);
                jk = new java.lang.Object[]{"系统：打包安装程序", "f2", b, 0};
                dkspq.j(kj, jk);
                jk = new java.lang.Object[]{"系统：打包安装程序(备用)", "f3", b, 0};
                dkspq.j(kj, jk);

            }

        } else if (zf.dy(z, "g")) {

            xx = "其他";

            if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h1")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h2")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h3")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h4")) {

            } else {

                jk = new java.lang.Object[]{"腕管Pro：系统打开", "g1", b, 0};
                dkspq.j(kj, jk);
                jk = new java.lang.Object[]{"腕管Pro：关于主题文件", "g2", b, 0};

                if (zf.cjw(b, ".awgtheme")) {

                    dkspq.j(kj, jk);

                }
                jk = new java.lang.Object[]{"腕管Pro：无障碍脚本执行", "g3", b, 0};

                if (zf.cjw(b, ".awgsash")) {

                    dkspq.j(kj, jk);

                }
                jk = new java.lang.Object[]{"腕管Pro：Shell脚本执行", "g4", b, 0};

                if (zf.cjw(b, ".sh")) {

                    dkspq.j(kj, jk);

                }
                jk = new java.lang.Object[]{"腕管Pro：关于备份文件", "g5", b, 0};

                if (zf.cjw(b, ".awgbak")) {

                    dkspq.j(kj, jk);

                }

            }

        }

        if (e == 0) {

            jk = new java.lang.Object[]{"返回", "0", "", 1};
            dkspq.j(kj, jk);

        } else if (e == 1) {

            jk = new java.lang.Object[]{"返回", "-1", "", 1};
            dkspq.j(kj, jk);

        }

        java.lang.String spxx = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String axc = wj.dqwb(spxx);

        if (zf.dy(axc, "1") || zf.dy(axc, "3")) {

            jk = new java.lang.Object[]{"", "", "", -1};
            dkspq.j(kj, jk);
            jk = new java.lang.Object[]{"", "", "", -1};
            dkspq.j(kj, jk);

        }

        final java.lang.String xxs = xx;
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                dkspq.sx();
                st.v7lb(open.cn.awg.pro.R.id.v7lb4).xzwz("top");
                st.wb(open.cn.awg.pro.R.id.Tab).zf(xxs);

            }

        });

    }

    public void cdx(int i, java.lang.String a, java.lang.String b, java.lang.String c) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(0);

            }

        });

        final java.lang.String choose = "/data/user/0/open.cn.awg.pro/cache/file/choose";

        final java.lang.String ftitle = "/data/user/0/open.cn.awg.pro/cache/file/title";

        java.lang.String cache1 = "/data/user/0/open.cn.awg.pro/cache/1";

        java.lang.String cache2 = "/data/user/0/open.cn.awg.pro/cache/2";

        java.lang.String cache3 = "/data/user/0/open.cn.awg.pro/cache/3";

        java.lang.String cachec1 = "/data/user/0/open.cn.awg.pro/cache/c1";

        java.lang.String cachec2 = "/data/user/0/open.cn.awg.pro/cache/c2";

        java.lang.String cachec3 = "/data/user/0/open.cn.awg.pro/cache/c3";

        java.lang.String cache41 = "/data/user/0/open.cn.awg.pro/cache/41";

        java.lang.String cachehd = "/data/user/0/open.cn.awg.pro/cache/hd";

        if (i == 0) {

            wj.sc(cache3);
            wj.sc(cachec1);
            wj.sc(cachec2);
            wj.sc(cachec3);
            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.wb(open.cn.awg.pro.R.id.Tab).kjd(0);
                    st.wb(open.cn.awg.pro.R.id.Tab).zf(wj.dqwb(ftitle));
                    st.xxbj(open.cn.awg.pro.R.id.xxbj3).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj5).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj7).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
                    lb.v.requestFocus();

                }

            });

        } else if (i == 1 || i == 2 || i == 3) {

            cdspq.sc();

            int[] kj = new int[]{open.cn.awg.pro.R.id.wb1, open.cn.awg.pro.R.id.wb2, open.cn.awg.pro.R.id.wb3, open.cn.awg.pro.R.id.wb4, open.cn.awg.pro.R.id.wb5, -1};

            java.lang.Object[] jk = new java.lang.Object[]{"", "", "", "", "", 0};
            jk = new java.lang.Object[]{"刷新", "", a, b, c, 0};
            cdspq.j(kj, jk);

            if (wj.cz(cache1) || wj.cz(cache2)) {

                jk = new java.lang.Object[]{"粘贴到当前目录", "", a, b, c, 0};
                cdspq.j(kj, jk);
                jk = new java.lang.Object[]{"取消粘贴", "", a, b, c, 0};
                cdspq.j(kj, jk);

            }

            if (!wj.cz(cache1) && !wj.cz(cache2)) {

                jk = new java.lang.Object[]{"新建目录", "", a, b, c, 0};
                cdspq.j(kj, jk);
                jk = new java.lang.Object[]{"新建文件", "", a, b, c, 0};
                cdspq.j(kj, jk);

                if (i == 1) {

                    jk = new java.lang.Object[]{"复制", "1", a, b, c, 0};
                    cdspq.j(kj, jk);
                    jk = new java.lang.Object[]{"剪切", "1", a, b, c, 0};
                    cdspq.j(kj, jk);
                    jk = new java.lang.Object[]{"删除", "1", a, b, c, 0};
                    cdspq.j(kj, jk);
                    jk = new java.lang.Object[]{"重命名", "1", a, b, c, 0};
                    cdspq.j(kj, jk);
                    jk = new java.lang.Object[]{"属性", "1", a, b, c, 0};
                    cdspq.j(kj, jk);

                } else if (i == 2) {

                    jk = new java.lang.Object[]{"打开方式", "0", a, b, c, 0};
                    cdspq.j(kj, jk);
                    jk = new java.lang.Object[]{"复制", "2", a, b, c, 0};
                    cdspq.j(kj, jk);
                    jk = new java.lang.Object[]{"剪切", "2", a, b, c, 0};
                    cdspq.j(kj, jk);
                    jk = new java.lang.Object[]{"删除", "2", a, b, c, 0};
                    cdspq.j(kj, jk);
                    jk = new java.lang.Object[]{"重命名", "2", a, b, c, 0};
                    cdspq.j(kj, jk);
                    jk = new java.lang.Object[]{"属性", "2", a, b, c, 0};
                    cdspq.j(kj, jk);

                } else if (i == 3) {

                }

            }
            jk = new java.lang.Object[]{"返回", "back", "", "", "", 1};
            cdspq.j(kj, jk);

            java.lang.String spxx = "/data/user/0/open.cn.awg.pro/settings/a3.inf";
            jk = new java.lang.Object[]{"", "", "", "", "", -1};
            cdspq.j(kj, jk);

            java.lang.String axc = wj.dqwb(spxx);

            if (zf.dy(axc, "1") || zf.dy(axc, "3")) {

                jk = new java.lang.Object[]{"", "", "", "", "", -1};
                cdspq.j(kj, jk);
                jk = new java.lang.Object[]{"", "", "", "", "", -1};
                cdspq.j(kj, jk);

            }
            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    cdspq.sx();
                    cdlb.v.requestFocus();
                    st.v7lb(open.cn.awg.pro.R.id.v7lb2).xzwz("top");
                    st.wb(open.cn.awg.pro.R.id.Tab).kjd(0);
                    st.wb(open.cn.awg.pro.R.id.Tab).zf("菜单");
                    st.xxbj(open.cn.awg.pro.R.id.xxbj3).kjd(0);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj5).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj7).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);

                }

            });

        } else if (i == 4) {

            if (wj.cz(cache3)) {

                java.lang.String w3 = wj.dqwb(cache3);

                if (zf.dy(w3, "重命名")) {

                    final java.lang.String nr = a;
                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            st.bjk(open.cn.awg.pro.R.id.bjk1).zf(nr);
                            st.bjk(open.cn.awg.pro.R.id.bjk1).tszf("请重命名文件");

                        }

                    });

                } else {

                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            st.bjk(open.cn.awg.pro.R.id.bjk1).zf("");
                            st.bjk(open.cn.awg.pro.R.id.bjk1).tszf("请输入目录/文件名");

                        }

                    });

                }

                final java.lang.String xs = wj.dqwb(cache3);
                wj.xrwb(cachec1, a);
                wj.xrwb(cachec2, b);
                wj.xrwb(cachec3, c);
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.wb(open.cn.awg.pro.R.id.Tab).kjd(0);
                        st.wb(open.cn.awg.pro.R.id.Tab).zf(xs);
                        st.bjk(open.cn.awg.pro.R.id.bjk1).tszf(xs);
                        st.xxbj(open.cn.awg.pro.R.id.xxbj3).kjd(8);
                        st.xxbj(open.cn.awg.pro.R.id.xxbj5).kjd(0);
                        st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(8);
                        st.xxbj(open.cn.awg.pro.R.id.xxbj7).kjd(8);
                        st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
                        st.qtgd(open.cn.awg.pro.R.id.qtgd1).v.requestFocus();

                    }

                });

            } else {

                cdx(0, null, null, null);

            }

        } else if (i == 5) {

            sxspq.sc();

            int[] kj = new int[]{open.cn.awg.pro.R.id.wb1, open.cn.awg.pro.R.id.wb2, -1};

            java.lang.Object[] jk = new java.lang.Object[]{"", "", 0};
            jk = new java.lang.Object[]{"名称", a, 0};
            sxspq.j(kj, jk);
            jk = new java.lang.Object[]{"目录", c, 0};
            sxspq.j(kj, jk);

            if (wj.lx(b) == 1) {

                java.lang.String dx = hqwjdx(b);
                jk = new java.lang.Object[]{"大小", dx, 0};
                sxspq.j(kj, jk);

            } else {

                java.lang.String dxs = wj.dqwb(cache41);
                jk = new java.lang.Object[]{"大小", dxs, 0};
                sxspq.j(kj, jk);

            }
            jk = new java.lang.Object[]{"返回", "", 1};
            sxspq.j(kj, jk);

            java.lang.String spxx = "/data/user/0/open.cn.awg.pro/settings/a3.inf";
            jk = new java.lang.Object[]{"", "", -1};
            sxspq.j(kj, jk);

            java.lang.String axc = wj.dqwb(spxx);

            if (zf.dy(axc, "1") || zf.dy(axc, "3")) {

                jk = new java.lang.Object[]{"", "", -1};
                sxspq.j(kj, jk);
                jk = new java.lang.Object[]{"", "", -1};
                sxspq.j(kj, jk);

            }
            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    sxspq.sx();
                    sxlb.v.requestFocus();
                    st.v7lb(open.cn.awg.pro.R.id.v7lb3).xzwz("top");
                    st.wb(open.cn.awg.pro.R.id.Tab).kjd(0);
                    st.wb(open.cn.awg.pro.R.id.Tab).zf("属性");
                    st.xxbj(open.cn.awg.pro.R.id.xxbj3).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj5).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(0);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj7).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);

                }

            });

        } else if (i == 6) {

            dkspq.sc();

            int[] kj = new int[]{open.cn.awg.pro.R.id.wb1, open.cn.awg.pro.R.id.wb2, open.cn.awg.pro.R.id.wb3, -1};

            java.lang.Object[] jk = new java.lang.Object[]{"", "", "", 0};
            jk = new java.lang.Object[]{"文本", "a", b, 2};
            dkspq.j(kj, jk);
            jk = new java.lang.Object[]{"图片", "b", b, 2};
            dkspq.j(kj, jk);
            jk = new java.lang.Object[]{"音频", "c", b, 2};
            dkspq.j(kj, jk);
            jk = new java.lang.Object[]{"视频", "d", b, 2};
            dkspq.j(kj, jk);
            jk = new java.lang.Object[]{"压缩", "e", b, 2};
            dkspq.j(kj, jk);
            jk = new java.lang.Object[]{"应用", "f", b, 2};
            dkspq.j(kj, jk);
            jk = new java.lang.Object[]{"其他", "g", b, 2};
            dkspq.j(kj, jk);
            jk = new java.lang.Object[]{"返回", "-1", "", 1};
            dkspq.j(kj, jk);

            java.lang.String spxx = "/data/user/0/open.cn.awg.pro/settings/a3.inf";
            jk = new java.lang.Object[]{"", "", "", -1};
            dkspq.j(kj, jk);

            java.lang.String axc = wj.dqwb(spxx);

            if (zf.dy(axc, "1") || zf.dy(axc, "3")) {

                jk = new java.lang.Object[]{"", "", "", -1};
                dkspq.j(kj, jk);
                jk = new java.lang.Object[]{"", "", "", -1};
                dkspq.j(kj, jk);

            }
            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    dkspq.sx();
                    dklb.v.requestFocus();
                    st.v7lb(open.cn.awg.pro.R.id.v7lb4).xzwz("top");
                    st.wb(open.cn.awg.pro.R.id.Tab).kjd(0);
                    st.wb(open.cn.awg.pro.R.id.Tab).zf("打开方式");
                    st.xxbj(open.cn.awg.pro.R.id.xxbj3).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj5).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj7).kjd(0);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);

                }

            });

        } else {

        }

    }

    public void sx(java.lang.String na, java.lang.String lj) {
        d1_rootpaths = open.cn.awg.pro.abc.d1_rootpath;

        java.lang.String root = d1_rootpaths;
        NewFilesLoad(lj, root);

    }

    public java.lang.String hqwjdx(java.lang.Object url) {

        double dx = ((double) (wj.dx(url)));

        double d1 = 0;

        java.lang.String d0;

        java.lang.String wjdx = "";

        if (dx >= 0 && dx < 1024) {

            d1 = dx;
            d0 = com.demo.e4.doubleToString(d1);
            wjdx = d0 + "B";

        } else if (dx >= 1024 && dx < 1048576) {

            d1 = dx / 1024;
            d0 = com.demo.e4.doubleToString(d1);
            wjdx = d0 + "K";

        } else if (dx >= 1048576 && dx < 1073741824) {

            d1 = dx / 1024 / 1024;
            d0 = com.demo.e4.doubleToString(d1);
            wjdx = d0 + "M";

        } else {

            d1 = dx / 1024 / 1024 / 1024;
            d0 = com.demo.e4.doubleToString(d1);
            wjdx = d0 + "G";

        }
        return wjdx;

    }

    public void tsk(java.lang.Object a, java.lang.Object b) {

        final java.lang.String[] name = new java.lang.String[]{"title", "text"};

        final java.lang.Object[] value = new java.lang.Object[]{a, b};
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                gj.tz(a3.class, name, value);

            }

        });

    }

    public void tsk_QR(java.lang.Object a, java.lang.Object b, java.lang.Object c) {

        final java.lang.String[] name = new java.lang.String[]{"title", "text", "QR-Code"};

        final java.lang.Object[] value = new java.lang.Object[]{a, b, c};
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                gj.tz(a3.class, name, value);

            }

        });

    }

    public void upload_error(java.lang.Object a, java.lang.Object b) {

        final java.lang.String[] name = new java.lang.String[]{"log", "location"};

        final java.lang.Object[] value = new java.lang.Object[]{a, b};
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                gj.tz(b1.class, name, value);

            }

        });

    }

    public java.lang.String readJson(java.lang.String text, java.lang.String value) {

        java.lang.String back = "";

        try {

            i.runlibrary.app.zf$json jo = zf.json(text);

            org.json.JSONObject json = jo.json;

            java.lang.Object sjs = jo.hq(json, value);
            back = sjs.toString();

        } catch (java.lang.Throwable e) {

            back = "0";

        }
        return back;

    }

    public java.lang.String getMipmap(java.lang.String v) {

        java.lang.String back = "";

        java.lang.String mipmaprooturl = "/data/user/0/open.cn.awg.pro/res/mipmap/";

        java.lang.String m = mipmaprooturl + v + ".png";

        try {

            if (wj.cz(m)) {

                back = m;

            } else {

                back = "0";

            }

        } catch (java.lang.Throwable e) {

            back = "0";

        }
        return back;

    }

    public void loadTheme(java.lang.String url) {

        int back = 0;

        java.lang.String mipmaprooturl = "/data/user/0/open.cn.awg.pro/res/";
        back = wj.jy(url, mipmaprooturl, true);

        if (back == 0) {

            tsk("提示", "导入主题失败\n文件可能已损坏");

        } else {

            tsk("提示", "导入主题成功");

        }

    }

    public java.lang.String[] themeInfo() {

        java.lang.String[] back = new java.lang.String[]{"加载异常...", "加载异常...", "加载异常...", "加载异常...", "加载异常...", "加载异常..."};

        java.lang.String info = "/data/user/0/open.cn.awg.pro/res/Manifest.json";

        java.lang.String text = wj.dqwb(info);

        java.lang.String info1 = readJson(text, "name");

        if (!zf.dy(info1, "0")) {

            back[0] = info1;

        }

        java.lang.String info2 = readJson(text, "version");

        if (!zf.dy(info2, "0")) {

            back[1] = info2;

        }

        java.lang.String info3 = readJson(text, "maker");

        if (!zf.dy(info3, "0")) {

            back[2] = info3;

        }

        java.lang.String info4 = readJson(text, "time");

        if (!zf.dy(info4, "0")) {

            back[3] = info4;

        }

        java.lang.String info5 = readJson(text, "awg_version");

        if (!zf.dy(info5, "0")) {

            back[4] = info5;

        }

        java.lang.String info6 = readJson(text, "tip");

        if (!zf.dy(info6, "0")) {

            back[5] = info6;

        }
        return back;

    }

    public java.lang.String urlLockString(java.lang.String t) {

        java.lang.String back = "";
        t = zf.qctwkg(t);

        if (zf.ckt(t, "awg://")) {

            back = t;

        } else {

            if (zf.ckt(t, "https://service.typheye.cn/")) {

                java.lang.String n = zf.qc(t, "https://service.typheye.cn/", null);
                back = "awg://web.url/" + n;

            } else if (zf.ckt(t, "https://")) {

                t = zf.th(t, "https://", "");
                back = "awg://thirds.url/" + t;

            } else if (zf.ckt(t, "http://")) {

                t = zf.th(t, "http://", "");
                back = "awg://third.url/" + t;

            } else {

                back = "0";

            }

        }
        return back;

    }

    public java.lang.String urlUnlockString(java.lang.String t) {

        java.lang.String back = "";

        java.lang.String url = "https://service.typheye.cn/";

        java.lang.String http = "http://";

        java.lang.String https = "https://";
        t = zf.qctwkg(t);

        if (zf.ckt(t, "awg://")) {

            if (zf.ckt(t, "awg://web.url/")) {

                back = zf.th(t, "awg://web.url/", url);

            } else if (zf.ckt(t, "awg://third.url/")) {

                back = zf.th(t, "awg://third.url/", http);

            } else if (zf.ckt(t, "awg://thirds.url/")) {

                back = zf.th(t, "awg://thirds.url/", https);

            } else {

                back = "0";

            }

        } else {

            back = t;

        }
        return back;

    }

    public java.lang.String htmlToText(java.lang.String t) {

        java.lang.String back = "";
        t = zf.th(t, "&quot;", "\"");
        back = zf.th(t, "&amp;", "&");
        return back;

    }

    public void update(int a11) {

        java.lang.String set36 = "/data/user/0/open.cn.awg.pro/settings/fixVersion";

        final java.lang.String appfixversion = zf.qctwkg(wj.dqwb(set36));

        final int myappversion = a11;

        java.lang.String set2e = "/data/user/0/open.cn.awg.pro/settings/f10/set2.inf";
        set2e = wj.dqwb(set2e);
        set2e = urlUnlockString(set2e);

        if (zf.cjw(set2e, "/")) {

        } else {

            set2e = set2e + "/";

        }
        set2e = set2e + "config.json";

        final java.lang.String url = set2e;
        gj.xc(new java.lang.Thread() {

            public void run() {

                if (yz()) {

                    java.lang.String setback = "";

                    java.lang.String version = "";

                    java.lang.String version2 = "";

                    java.lang.String back = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                    if (zf.dy(back, "") || zf.dy(back, null)) {

                        setback = "0";

                    } else {

                        setback = htmlToText(back);

                        if (zf.dy(setback, "0")) {

                            setback = "0";

                        } else {

                            version = readJson(setback, "UpdateVersion");
                            version2 = readJson(setback, "FixVersion");
                            setback = String.valueOf(1);

                        }

                    }

                    if (zf.dy(setback, "0")) {

                    } else {

                        java.lang.String ek = "";

                        final int b1 = Integer.parseInt(version);

                        final int b2 = Integer.parseInt(version2);

                        if (myappversion >= b1) {

                        } else {

                            ek = "应用";

                        }

                        if (Double.parseDouble(appfixversion) >= b2) {

                        } else {

                            if (zf.dy(ek, "应用")) {

                                ek = ek + "和补丁";

                            } else {

                                ek = "补丁";

                            }

                        }

                        if (myappversion < b1 || Double.parseDouble(appfixversion) < b2) {

                            final java.lang.String eks = ek;
                            gj.jmxc(new java.lang.Runnable() {

                                public void run() {

                                    gj.tz(f9.class);
                                    tsk("提示", eks + "有新版本了");

                                }

                            });

                        }

                    }

                } else {

                }

            }

        });

    }

    public void online() {
        onlineall();

    }

    public void onlineall() {

        java.lang.String set2e = "/data/user/0/open.cn.awg.pro/settings/f10/set2.inf";
        set2e = wj.dqwb(set2e);
        set2e = urlUnlockString(set2e) + "/online.php";

        final java.lang.String url = set2e;
        gj.xc(new java.lang.Thread() {

            public void run() {

                java.lang.String setback = "";

                java.lang.String version = "";

                java.lang.String version2 = "";

                java.lang.String back = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                if (zf.dy(back, "") || zf.dy(back, null)) {

                } else {

                }

            }

        });

    }

    public boolean isInstallX5() {

        boolean back;

        back = wj.cz("/data/user/0/open.cn.awg.pro/app_tbs/core_share/tbs.conf") || wj.cz("/data/user/0/open.cn.awg.pro/app_tbs_64/core_share/tbs.conf");
        return back;

    }
}
