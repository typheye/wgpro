/**
 ******************************************************************************
 * @file    AppRuntimeBridge.java
 * @author  Typheye
 * @brief   Shared runtime bridge for UI, files, dialogs, updates, and error reporting.
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
package open.cn.awg.pro.core;

import android.content.Context;
import android.os.Process;
import android.view.View;

import com.file.Xor.xor;
import com.kingqi.zwcj.应用工具;

import i.app.iClass;

import java.io.File;
import java.util.Arrays;

import org.json.JSONObject;

import open.cn.awg.pro.account.AppActivationActivity;
import open.cn.awg.pro.account.TypheyeAccountActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.data.LocalDatabaseBridge;
import open.cn.awg.pro.plugin.ExtensionPluginBridge;
import open.cn.awg.pro.plugin.ExtensionPluginDetailActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.repair.CrashFeedbackActivity;
import open.cn.awg.pro.repair.ErrorMessageActivity;
import open.cn.awg.pro.ui.list.FileListItemActivity;
import open.cn.awg.pro.ui.list.FileOpenOptionItemActivity;
import open.cn.awg.pro.ui.list.FileOperationInfoItemActivity;
import open.cn.awg.pro.ui.list.FileOperationMenuItemActivity;
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;
import open.cn.awg.pro.update.AppUpdateActivity;
import open.cn.awg.pro.update.UpdatePromptActivity;

public class AppRuntimeBridge extends iClass {

    public static i.runlibrary.app.v.v7lb$UserAdapter spq = null;

    public static i.runlibrary.app.v.v7lb lb = null;

    public static i.runlibrary.app.v.v7lb$UserAdapter cdspq = null;

    public static i.runlibrary.app.v.v7lb cdlb = null;

    public static i.runlibrary.app.v.v7lb$UserAdapter sxspq = null;

    public static i.runlibrary.app.v.v7lb sxlb = null;

    public static i.runlibrary.app.v.v7lb$UserAdapter dkspq = null;

    public static i.runlibrary.app.v.v7lb dklb = null;

    public static Context context;

    public static String d1_rootpaths;
    public final AppRuntimeBridge lei = this, 类 = this;
    public LocalDatabaseBridge e11 = new LocalDatabaseBridge(_APPINFO);

    public AppRuntimeBridge(i.runlibrary.app.AppInfo _APPINFO) {
        super(_APPINFO);

    }

    public void getlic() {

        final String auid = AppPaths.appPath("settings/account/user");

        final String sec_license = AppPaths.appPath("data/sec/license");

        String set2e = AppPaths.appPath("settings/f10/set2.inf");
        set2e = wj.dqwb(set2e);
        set2e = urlUnlockString(set2e);

        if (zf.cjw(set2e, "/")) {

        } else {

            set2e = set2e + "/";

        }
        set2e = set2e + "config_v2.json";

        final String url = set2e;
        gj.xc(new Thread() {

            public void run() {

                String setback = "";

                String version = "";

                String allow = "";

                String back = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

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

    public void update_v2(int a11, String vn_) {

        final String auid = AppPaths.appPath("settings/account/user");

        final String sec_updater = AppPaths.appPath("data/sec/updater");

        final int myappversion = a11;

        final String vn = vn_;

        String set2e = AppPaths.appPath("settings/f10/set2.inf");
        set2e = wj.dqwb(set2e);
        set2e = urlUnlockString(set2e);

        if (zf.cjw(set2e, "/")) {

        } else {

            set2e = set2e + "/";

        }
        set2e = set2e + "config_v2.json";

        final String url = set2e;
        gj.xc(new Thread() {

            public void run() {

                if (yz()) {

                    String setback = "";

                    String version = "";

                    String versionname = "";

                    String allow = "";

                    String back = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

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

                            String ek = "";

                            final int b1 = Integer.parseInt(version);

                            if (myappversion < b1 && b1 != 9999999) {

                                final String eks = ek;
                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        gj.tz(UpdatePromptActivity.class);

                                    }

                                });

                            } else {

                                int ccc = versionname.compareTo(vn);

                                if (!zf.dy(versionname, vn) && ccc > 0) {

                                    final String eks = ek;
                                    gj.jmxc(new Runnable() {

                                        public void run() {

                                            gj.tz(UpdatePromptActivity.class);

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

            String a = AppPaths.appPath("settings/account/user");

            String tmppath = AppPaths.appPath("data/backup/tmp.awgbak");

            String tmp2path = AppPaths.appPath("data/backup/.nomedia");
            a = sj.md5(wj.dqwb(a));

            String filename = xt.sj(4) + ".awgbak";

            String backpath = AppPaths.appPath("settings");

            String mkdirpath = AppPaths.externalFilesPath("backup/.nomedia");

            String outpath = AppPaths.externalFilesPath("backup/") + filename;

            String out2path = "/storage/emulated/0/Download/" + filename;
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

            gj.tz(TypheyeAccountActivity.class);
            tsk("提示", "该操作需要登录Typheye账户");

        }

    }

    public boolean app_backup2() {

        boolean bk = false;

        if (islogin()) {

            String a = AppPaths.appPath("settings/account/user");

            String tmppath = AppPaths.appPath("data/backup/tmp.awgbak");

            String tmp2path = AppPaths.appPath("data/backup/.nomedia");
            a = sj.md5(wj.dqwb(a));

            String filename = "last.awgbak.zip";

            String backpath = AppPaths.appPath("settings");

            String mkdirpath = AppPaths.appPath("data/backup/yun/.nomedia");

            String outpath = AppPaths.appPath("data/backup/yun/") + filename;

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

    public boolean app_recovery(String path) {

        String tmp2path = AppPaths.appPath("data/backup/.nomedia");

        String tmppath = AppPaths.appPath("data/backup/tmp.awgbak");

        String a = AppPaths.appPath("settings/account/user");
        a = sj.md5(wj.dqwb(a));

        String backpath = AppPaths.appPath("settings/");
        wj.xrwb(tmp2path, "");
        wj.fz(path, tmppath, true);
        xor.FileXor(tmppath, tmppath + "_", a);

        int i = wj.jy(tmppath + "_", backpath, true);
        wj.sc(tmppath, true);
        wj.sc(tmppath + "_", true);
        wj.sc(tmp2path, true);

        if (i > 0) {

            gj.zt(1000);
            gj.jmxc(new Runnable() {

                public void run() {

                    String[] name = new String[]{"Msg"};

                    String[] value = new String[]{"导入成功 点击重启"};
                    gj.tz(ErrorMessageActivity.class, name, value);

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

                String lists = "";

                String[] gl = new String[]{"*.awgbak"};

                File[] lb = wj.czwj("/storage/emulated/0/", gl);

                for (File c : lb) {

                    if (zf.dy(lists, "")) {

                        lists = c.getAbsolutePath();

                    } else {

                        lists = lists + "\n" + c.getAbsolutePath();

                    }

                }

                long test0 = 100000000;

                String endpath = "";

                String[] d = zf.fg(lists, "\n", true);

                if (d.length > 0) {

                    for (String e : d) {

                        String[] bb;
                        bb = fileinfo("/storage/emulated/0/", e);

                        if (zf.dy(bb[0], "true")) {

                            String sts = zf.qc(bb[3], null, ".awgbak");

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
                gj.tz(TypheyeAccountActivity.class);
                tsk("提示", "该操作需要登录腕管Pro账户");

            }

        } else {

            tsk("提示", "您未授权应用\"修改或删除您共享存储空间中的内容\"，请先前往设置授权后再操作");

        }
        return false;

    }

    public void goplu(Object pluid, Object pluname, boolean p) {

        if (p) {

            e11.add_pluginlist(pluid, pluname, pluid + "-" + pluname);

        }

        String cachehd = AppPaths.appPath("data/cjlb/lbx_id");
        wj.xrwb(cachehd, pluid);

        String cachehd2 = AppPaths.appPath("data/cjlb/lbx_title");
        wj.xrwb(cachehd2, pluname);

        ExtensionPluginBridge e14 = new ExtensionPluginBridge(_APPINFO);

        String set1 = AppPaths.appPath("settings/f19/set1.inf");

        if (zf.dy(wj.dqwb(set1), "true")) {

            if (e14.newG(Integer.parseInt(String.valueOf(pluid)))) {

                gj.jmxc(new Runnable() {

                    public void run() {

                        gj.tz(ExtensionPluginDetailActivity.class);

                    }

                });

            } else {

                tsk("提示", "没有可执行此操作的扩展");

            }

        } else {

            gj.jmxc(new Runnable() {

                public void run() {

                    gj.tz(ExtensionPluginDetailActivity.class);

                }

            });

        }

    }

    public String awgwl_getuid() {

        String a = AppPaths.appPath("settings/account/user");

        String b = wj.dqwb(a);

        if (zf.dy(b, null) || zf.dy(b, "") || zf.dy(b, "null")) {

            b = "0";

        }
        return b;

    }

    public boolean islogin() {

        String a = AppPaths.appPath("settings/account/user");

        String b = AppPaths.appPath("settings/account/pass");

        return wj.cz(a) && wj.cz(b);

    }

    public boolean awgwl_state() {

        String ic = AppPaths.appPath("settings/f11/true");

        return wj.cz(ic) && zf.dy(wj.dqwb(ic), "1");

    }

    public boolean awgwl_true() {

        String ic = AppPaths.appPath("settings/f11/true");

        boolean f = wj.xrwb(ic, "1");

        return f;

    }

    public void awgwl_false() {

        String ic = AppPaths.appPath("settings/f11/true");
        wj.sc(ic);

    }

    public int getNowUserId() {

        int b = -1;

        try {

            b = Process.myUid() / 100000;

        } catch (Throwable __$_e__) {

            b = -1;

        }
        return b;

    }

    public boolean isJhs() {

        boolean zt = false;

        String a = AppPaths.appPath("settings/f7/Enable2");

        if (wj.cz(a) && !zf.dy(wj.dqwb(a), "")) {

            zt = true;

        } else {

            gj.tz(AppActivationActivity.class);

        }
        return zt;

    }

    public void zfsb(Object id) {

        final Object i = id;
        gj.xc(new Thread() {

            public void run() {

                gj.zt(1000);

                if (zf.dy(i, "a1")) {

                }

            }

        });

    }

    public void zfwc(Object id) {

        final Object i = id;
        gj.xc(new Thread() {

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

        String a = String.valueOf(zf.sjs(1, 9));

        int i = 14;

        while (i > 0) {

            String b = String.valueOf(zf.sjs(0, 9));
            a = a + b;
            i = i - 1;

        }

        String j = AppPaths.appPath("settings/f7/Enable2");
        wj.xrwb(j, a);

    }

    public boolean yz() {
//        String sec_isunlock = AppPaths.appPath("data/sec/unlock");
//        wj.xrwb(sec_isunlock, "true");
//        return true;
        context = AwgProApplication.getContext();

        String sec_isunlock = AppPaths.appPath("data/sec/unlock");

        String urls = AppPaths.appPath("settings/f10/set2.inf");
        urls = wj.dqwb(urls);
        urls = urlUnlockString(urls);

        if (zf.cjw(urls, "/")) {

        } else {

            urls = urls + "/";

        }

        String ljf = wj.dqwb(AppPaths.appPath("data/assets/a17"));

        String a2 = 应用工具.获取应用签名(context, "open.cn.awg.pro");

        String path = 应用工具.获取应用apk文件(context, "open.cn.awg.pro");

        File mfile;
        mfile = new File(path);

        String aa = sj.md5(mfile);

        int a11;

        String vn;
        a11 = com.demo.e4.getVersionCode(context);
        vn = com.demo.e4.getVersionName(context);

        String bb = String.valueOf(a11);

        String url = urls + "x.php?token=" + a2 + ljf + "id=" + aa + ljf + "ver=" + bb + ljf + "vername=" + vn;

        String st5 = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

        if (zf.dy(st5, null)) {

            return false;

        } else {

            wj.xrwb(sec_isunlock, st5);

            return zf.dy(st5, "true");

        }

    }

    public boolean isJh() {

        boolean zt = false;

        String a = AppPaths.appPath("settings/f7/Enable2");

        if (wj.cz(a) && !zf.dy(wj.dqwb(a), "")) {

            zt = true;

        }
        return zt;

    }

    public void csh() {

        String cachehd = AppPaths.appPath("cache/hd");
        wj.xrwb(cachehd, "");
        context = AwgProApplication.getContext();
        lb = st.v7lb(R.id.v7lb1);
        spq = lb.v7lbspq(FileListItemActivity.class, R.layout.file_list_item, new i.runlibrary.app.v.v7lb$OnUserAdapterView() {

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
        cdlb = st.v7lb(R.id.v7lb2);
        cdspq = cdlb.v7lbspq(FileOperationMenuItemActivity.class, R.layout.file_operation_menu_item, new i.runlibrary.app.v.v7lb$OnUserAdapterView() {

            public void getView(i.runlibrary.app.v.v7lb$UserAdapter ua, int pn, View vw) {

                i.runlibrary.app.sj$lb lbsj = sj.lb(vw);

                int f = sj.zh(lbsj.lbsj(pn, -1)).zint();

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
        sxlb = st.v7lb(R.id.v7lb3);
        sxspq = sxlb.v7lbspq(FileOperationInfoItemActivity.class, R.layout.file_operation_info_item, new i.runlibrary.app.v.v7lb$OnUserAdapterView() {

            public void getView(i.runlibrary.app.v.v7lb$UserAdapter ua, int pn, View vw) {

                i.runlibrary.app.sj$lb lbsj = sj.lb(vw);

                int f = sj.zh(lbsj.lbsj(pn, -1)).zint();

                if (f == 1) {

                    st.xxbj(vw, R.id.xxbj1).dqfs("center");
                    st.wb(vw, R.id.wb2).kjd(8);

                    String color = zf.zf(R.color.colorTextFalse);
                    st.wb(vw, R.id.wb1).zfys(color);
                    st.xxbj(vw, R.id.xxbj1).kjd(0);
                    st.xxbj(vw, R.id.xxbj1).xydj(true);
                    st.xxbj(vw, R.id.xxbj1).xyca(true);
                    st.wb(vw, R.id.dp20).kjd(8);

                } else if (f == -1) {

                    st.xxbj(vw, R.id.xxbj1).dqfs("left");
                    st.wb(vw, R.id.wb2).kjd(0);

                    String color = zf.zf(R.color.colorTextU1);
                    st.wb(vw, R.id.wb1).zfys(color);
                    st.xxbj(vw, R.id.xxbj1).kjd(8);
                    st.xxbj(vw, R.id.xxbj1).xydj(false);
                    st.xxbj(vw, R.id.xxbj1).xyca(false);
                    st.wb(vw, R.id.dp20).kjd(0);

                } else {

                    st.xxbj(vw, R.id.xxbj1).dqfs("left");
                    st.wb(vw, R.id.wb2).kjd(0);

                    String color = zf.zf(R.color.colorTextU1);
                    st.wb(vw, R.id.wb1).zfys(color);
                    st.xxbj(vw, R.id.xxbj1).kjd(0);
                    st.xxbj(vw, R.id.xxbj1).xydj(true);
                    st.xxbj(vw, R.id.xxbj1).xyca(true);
                    st.wb(vw, R.id.dp20).kjd(8);

                }

            }

        });
        dklb = st.v7lb(R.id.v7lb4);
        dkspq = dklb.v7lbspq(FileOpenOptionItemActivity.class, R.layout.file_open_option_item, new i.runlibrary.app.v.v7lb$OnUserAdapterView() {

            public void getView(i.runlibrary.app.v.v7lb$UserAdapter ua, int pn, View vw) {

                i.runlibrary.app.sj$lb lbsj = sj.lb(vw);

                int f = sj.zh(lbsj.lbsj(pn, -1)).zint();

                if (f == 1) {

                    String color = zf.zf(R.color.colorTextFalse);
                    st.wb(vw, R.id.wb1).zfys(color);
                    st.wb(vw, R.id.wb1).ztdx(13);
                    st.xxbj(vw, R.id.xxbj1).kjd(0);
                    st.xxbj(vw, R.id.xxbj1).xydj(true);
                    st.xxbj(vw, R.id.xxbj1).xyca(true);
                    st.wb(vw, R.id.dp20).kjd(8);

                } else if (f == -1) {

                    String color = zf.zf(R.color.colorTextU1);
                    st.wb(vw, R.id.wb1).zfys(color);
                    st.wb(vw, R.id.wb1).ztdx(13);
                    st.xxbj(vw, R.id.xxbj1).kjd(8);
                    st.xxbj(vw, R.id.xxbj1).xydj(false);
                    st.xxbj(vw, R.id.xxbj1).xyca(false);
                    st.wb(vw, R.id.dp20).kjd(0);

                } else if (f == 0) {

                    String color = zf.zf(R.color.colorTextU1);
                    st.wb(vw, R.id.wb1).zfys(color);
                    st.wb(vw, R.id.wb1).ztdx(13);
                    st.xxbj(vw, R.id.xxbj1).kjd(0);
                    st.xxbj(vw, R.id.xxbj1).xydj(true);
                    st.xxbj(vw, R.id.xxbj1).xyca(true);
                    st.wb(vw, R.id.dp20).kjd(8);

                } else if (f == -2) {

                    String color = zf.zf(R.color.colorTextTrue);
                    st.wb(vw, R.id.wb1).zfys(color);
                    st.wb(vw, R.id.wb1).ztdx(13);
                    st.xxbj(vw, R.id.xxbj1).kjd(0);
                    st.xxbj(vw, R.id.xxbj1).xydj(true);
                    st.xxbj(vw, R.id.xxbj1).xyca(true);
                    st.wb(vw, R.id.dp20).kjd(8);

                } else {

                    String color = zf.zf(R.color.colorTextU1);
                    st.wb(vw, R.id.wb1).zfys(color);
                    st.wb(vw, R.id.wb1).ztdx(13);
                    st.xxbj(vw, R.id.xxbj1).kjd(0);
                    st.xxbj(vw, R.id.xxbj1).xydj(true);
                    st.xxbj(vw, R.id.xxbj1).xyca(true);
                    st.wb(vw, R.id.dp20).kjd(8);

                }

            }

        });
        e11.setup_filelist();

    }

    public String[] fileinfo(Object root, Object url) {

        String Root = wj.hqml(url);

        File mfile;

        String Runs = "true";

        String FileUrl = "";

        String FileFatherUrl = "";

        String UrlName = "";

        try {

            mfile = new File(Root);
            UrlName = mfile.getName();
            FileFatherUrl = mfile.getParent();
            mfile.hashCode();
            FileUrl = mfile.getPath();

        } catch (Throwable e) {

            Runs = "false";

        }

        String[] Back = new String[]{Runs, FileUrl, FileFatherUrl, UrlName};
        return Back;

    }

    public void NewFilesLoad(Object url, Object roots) {

        final String set2 = AppPaths.appPath("settings/f8/set1.inf");

        final String set1 = AppPaths.appPath("settings/d1/set1.inf");

        final String spxx = AppPaths.appPath("settings/a3.inf");

        final String set3 = AppPaths.appPath("settings/d1/set3.inf");

        String root = wj.hqml(roots);

        String srcs = "";

        try {

            String FileUrl = wj.hqml(url);

            if (zf.cjw(FileUrl, "/")) {

                FileUrl = FileUrl;

            } else {

                FileUrl = FileUrl + "/";

            }
            spq.sc();

            int[] kj = new int[]{R.id.tx1, R.id.wb1, R.id.wb2, R.id.wb3, -1};

            Object[] jk = new Object[]{R.mipmap.b5, "...", FileUrl, "返回上层目录", 0};

            if (zf.dy(wj.dqwb(set2), "true")) {

                srcs = getMipmap("b5");

                if (zf.dy(srcs, "0")) {

                    jk = new Object[]{R.mipmap.b5, "...", FileUrl, "返回上层目录", 0};

                } else {

                    jk = new Object[]{srcs, "...", FileUrl, "返回上层目录", 0};

                }

            } else {

                jk = new Object[]{R.mipmap.b5, "...", FileUrl, "返回上层目录", 0};

            }
            spq.j(kj, jk);

            String[] FileXH1 = wj.lb(url, true);

            if (zf.dy(wj.dqwb(set3), "true")) {

                Arrays.sort(FileXH1);

            }

            for (String c : FileXH1) {

                String urlName = "";

                String[] fhValue = fileinfo(root, c);

                if (zf.dy(fhValue[0], "true")) {

                    urlName = fhValue[3];

                } else {

                    urlName = "Error";

                }

                String Two = "目录";

                if (zf.dy(wj.dqwb(set2), "true")) {

                    srcs = getMipmap("b5");

                    if (zf.dy(srcs, "0")) {

                        jk = new Object[]{R.mipmap.b5, urlName, c, Two, 0};

                    } else {

                        jk = new Object[]{srcs, urlName, c, Two, 0};

                    }

                } else {

                    jk = new Object[]{R.mipmap.b5, urlName, c, Two, 0};

                }

                String pb1 = AppPaths.appPath("settings");

                String pb2 = AppPaths.appPath("data");

                String pb3 = AppPaths.appPath("fix");

                String pb4 = AppPaths.appPath("cache");

                String pb5 = AppPaths.appPath("files/cache");

                String pb6 = "/storage/emulated/0/Android/data";

                String pb7 = "/storage/emulated/0/Android/obb";

                if (zf.dy(c, pb1) || zf.dy(c, pb2) || zf.dy(c, pb3) || zf.dy(c, pb4) || zf.dy(c, pb5) || (xt.sbxx().sdk > 29 && zf.dy(c, pb6)) || (xt.sbxx().sdk > 29 && zf.dy(c, pb7))) {

                } else {

                    spq.j(kj, jk);

                }

            }

            String[] FileXH2 = wj.lb(url, false);

            if (zf.dy(wj.dqwb(set3), "true")) {

                Arrays.sort(FileXH2);

            }

            for (String c : FileXH2) {

                String bt = "";

                String[] fhValue = fileinfo(root, c);

                if (zf.dy(fhValue[0], "true")) {

                    bt = fhValue[3];

                } else {

                    bt = "Error";

                }

                double dx = ((double) (wj.dx(c)));

                double d1 = 0;

                String d0;

                String cc = zf.zhxx(c);

                String wjdx = "";

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

                            jk = new Object[]{R.mipmap.b1, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b1, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".png") || zf.cjw(cc, "jpg")) {

                    if (zf.dy(wj.dqwb(set1), "true")) {

                        jk = new Object[]{c, bt, c, wjdx, 0};

                    } else {

                        if (zf.dy(wj.dqwb(set2), "true")) {

                            srcs = getMipmap("b11");

                            if (zf.dy(srcs, "0")) {

                                jk = new Object[]{R.mipmap.b11, bt, c, wjdx, 0};

                            } else {

                                jk = new Object[]{srcs, bt, c, wjdx, 0};

                            }

                        } else {

                            jk = new Object[]{R.mipmap.b11, bt, c, wjdx, 0};

                        }

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".mp3") || zf.cjw(cc, ".flac") || zf.cjw(cc, ".ogg")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b9");

                        if (zf.dy(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b9, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b9, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".mp4") || zf.cjw(cc, ".mkv") || zf.cjw(cc, ".webm") || zf.cjw(cc, ".m3u8") || zf.cjw(cc, ".avi") || zf.cjw(cc, ".mts")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b8");

                        if (zf.dy(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b8, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b8, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".txt") || zf.cjw(cc, ".xml") || zf.cjw(cc, ".java")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b13");

                        if (zf.dy(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b13, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b13, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".zip") || zf.cjw(cc, ".rar") || zf.cjw(cc, "7z")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b16");

                        if (zf.dy(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b16, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b16, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".html")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b6");

                        if (zf.dy(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b6, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b6, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".pdf")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b10");

                        if (zf.dy(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b10, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b10, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".doc") || zf.cjw(cc, ".docx")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b15");

                        if (zf.dy(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b15, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b15, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".xls") || zf.cjw(cc, ".xlsx")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b2");

                        if (zf.dy(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b2, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b2, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".ppt") || zf.cjw(cc, ".pptx")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b12");

                        if (zf.dy(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b12, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b12, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".awgtheme")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b17");

                        if (zf.dy(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b17, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b17, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".awgsash")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b18");

                        if (zf.dy(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b18, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b18, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".sh")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b19");

                        if (zf.dy(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b19, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b19, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else if (zf.cjw(cc, ".awgbak")) {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b20");

                        if (zf.dy(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b20, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b20, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                } else {

                    if (zf.dy(wj.dqwb(set2), "true")) {

                        srcs = getMipmap("b14");

                        if (zf.dy(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b14, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b14, bt, c, wjdx, 0};

                    }
                    spq.j(kj, jk);

                }

            }
            jk = new Object[]{"", "", "", "", -1};
            spq.j(kj, jk);

            String axc = wj.dqwb(spxx);

            if (zf.dy(axc, "1") || zf.dy(axc, "3")) {

                jk = new Object[]{"", "", "", "", -1};
                spq.j(kj, jk);
                jk = new Object[]{"", "", "", "", -1};
                spq.j(kj, jk);

            }

            final String ok = FileUrl;
            gj.jmxc(new Runnable() {

                public void run() {

                    spq.sx();
                    lb.xzwz(e11.get_filelist(ok));
                    st.wb(R.id.zywblj).zf(ok);

                }

            });

        } catch (Throwable e) {

            upload_error(e, "e1.NewFilesLoad(s,s)");
            gj.gb();

        }

    }

    public void fileTouch(Object text, Object url) {
        d1_rootpaths = GlobalRuntimeState.d1_rootpath;

        String root = d1_rootpaths;

        if (zf.dy(text, "返回上层目录")) {

            if (zf.dy(url, root)) {

                gj.gb();

            } else {

                String[] fhValue = fileinfo("%", url);
                NewFilesLoad(fhValue[2], root);

            }

        } else {

            if (wj.lx(url) == 2) {

                NewFilesLoad(url, root);

            } else {

                String[] fhValue = fileinfo(root, url);

                String bts = fhValue[3];

                String lj = fhValue[1];

                String ljs = fhValue[2];

                String c = lj;

                String cc = zf.zhxx(c);

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

    public void dkfs(Object z, Object b, int e) {

        final String choose = AppPaths.appPath("cache/file/choose");

        final String choose2 = AppPaths.appPath("cache/file/choose2");

        String xx = "";
        dkspq.sc();

        int[] kj = new int[]{R.id.wb1, R.id.wb2, R.id.wb3, -1};

        Object[] jk = new Object[]{"", "", "", 0};

        if (zf.dy(z, "a")) {

            xx = "文本";

            if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h1")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h2")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h3")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h4")) {

            } else {

                jk = new Object[]{"腕管Pro：文本浏览", "a1", b, 0};
                dkspq.j(kj, jk);
                jk = new Object[]{"腕管Pro：文本编辑", "a2", b, 0};
                dkspq.j(kj, jk);

            }

        } else if (zf.dy(z, "b")) {

            xx = "图片";

            if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h1")) {

                jk = new Object[]{"腕上微聊：选择附件", "h1", b, 0};
                dkspq.j(kj, jk);
                jk = new Object[]{"PinchImageView：图片浏览", "b1", b, 0};
                dkspq.j(kj, jk);

            } else if (wj.cz(choose2) && zf.dy(wj.dqwb(choose2), "h1")) {

                jk = new Object[]{"账户头像：选择附件", "i1", b, 0};
                dkspq.j(kj, jk);
                jk = new Object[]{"PinchImageView：图片浏览", "b1", b, 0};
                dkspq.j(kj, jk);

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h2")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h3")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h4")) {

            } else {

                jk = new Object[]{"PinchImageView：图片浏览", "b1", b, 0};
                dkspq.j(kj, jk);

            }

        } else if (zf.dy(z, "c")) {

            xx = "音频";

            if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h1")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h2")) {

                jk = new Object[]{"腕上微聊：选择附件", "h2", b, 0};
                dkspq.j(kj, jk);
                jk = new Object[]{"DKPlayer：媒体浏览", "c2", b, 0};
                dkspq.j(kj, jk);

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h3")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h4")) {

            } else {

                jk = new Object[]{"DKPlayer：媒体浏览", "c2", b, 0};
                dkspq.j(kj, jk);
                jk = new Object[]{"WearMusic：音频浏览", "c1", b, -2};

                boolean apppd1;
                apppd1 = com.demo.e8.app(context, "cn.wearbbs.music");

                if (apppd1) {

                    dkspq.j(kj, jk);

                }
                jk = new Object[]{"抬腕视频：音频浏览", "c3", b, -2};

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

                jk = new Object[]{"腕上微聊：选择附件", "h3", b, 0};
                dkspq.j(kj, jk);
                jk = new Object[]{"DKPlayer：媒体浏览", "d2", b, 0};
                dkspq.j(kj, jk);

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h4")) {

            } else {

                jk = new Object[]{"DKPlayer：媒体浏览", "d2", b, 0};
                dkspq.j(kj, jk);
                jk = new Object[]{"腕上视频：视频浏览", "d3", b, -2};

                boolean apppd1;
                apppd1 = com.demo.e8.app(context, "cn.luern0313.wristvideoplayer");

                if (apppd1) {

                    dkspq.j(kj, jk);

                }
                jk = new Object[]{"抬腕视频：视频浏览", "d4", b, -2};

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

                jk = new Object[]{"腕上微聊：选择附件", "h4", b, 0};
                dkspq.j(kj, jk);

            } else {

                jk = new Object[]{"腕管Pro：解压到当前目录", "e1", b, 0};
                dkspq.j(kj, jk);

            }

        } else if (zf.dy(z, "f")) {

            xx = "应用";

            if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h1")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h2")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h3")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h4")) {

            } else {

                jk = new Object[]{"腕管Pro：应用安装", "f1", b, 0};
                dkspq.j(kj, jk);
                jk = new Object[]{"系统：打包安装程序", "f2", b, 0};
                dkspq.j(kj, jk);
                jk = new Object[]{"系统：打包安装程序(备用)", "f3", b, 0};
                dkspq.j(kj, jk);

            }

        } else if (zf.dy(z, "g")) {

            xx = "其他";

            if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h1")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h2")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h3")) {

            } else if (wj.cz(choose) && zf.dy(wj.dqwb(choose), "h4")) {

            } else {

                jk = new Object[]{"腕管Pro：系统打开", "g1", b, 0};
                dkspq.j(kj, jk);
                jk = new Object[]{"腕管Pro：关于主题文件", "g2", b, 0};

                if (zf.cjw(b, ".awgtheme")) {

                    dkspq.j(kj, jk);

                }
                jk = new Object[]{"腕管Pro：无障碍脚本执行", "g3", b, 0};

                if (zf.cjw(b, ".awgsash")) {

                    dkspq.j(kj, jk);

                }
                jk = new Object[]{"腕管Pro：Shell脚本执行", "g4", b, 0};

                if (zf.cjw(b, ".sh")) {

                    dkspq.j(kj, jk);

                }
                jk = new Object[]{"腕管Pro：关于备份文件", "g5", b, 0};

                if (zf.cjw(b, ".awgbak")) {

                    dkspq.j(kj, jk);

                }

            }

        }

        if (e == 0) {

            jk = new Object[]{"返回", "0", "", 1};
            dkspq.j(kj, jk);

        } else if (e == 1) {

            jk = new Object[]{"返回", "-1", "", 1};
            dkspq.j(kj, jk);

        }

        String spxx = AppPaths.appPath("settings/a3.inf");

        String axc = wj.dqwb(spxx);

        if (zf.dy(axc, "1") || zf.dy(axc, "3")) {

            jk = new Object[]{"", "", "", -1};
            dkspq.j(kj, jk);
            jk = new Object[]{"", "", "", -1};
            dkspq.j(kj, jk);

        }

        final String xxs = xx;
        gj.jmxc(new Runnable() {

            public void run() {

                dkspq.sx();
                st.v7lb(R.id.v7lb4).xzwz("top");
                st.wb(R.id.title_bar).zf(xxs);

            }

        });

    }

    public void cdx(int i, String a, String b, String c) {
        gj.jmxc(new Runnable() {

            public void run() {

                st.xxbj(R.id.xxbj9).kjd(0);

            }

        });

        final String choose = AppPaths.appPath("cache/file/choose");

        final String ftitle = AppPaths.appPath("cache/file/title");

        String cache1 = AppPaths.appPath("cache/1");

        String cache2 = AppPaths.appPath("cache/2");

        String cache3 = AppPaths.appPath("cache/3");

        String cachec1 = AppPaths.appPath("cache/c1");

        String cachec2 = AppPaths.appPath("cache/c2");

        String cachec3 = AppPaths.appPath("cache/c3");

        String cache41 = AppPaths.appPath("cache/41");

        String cachehd = AppPaths.appPath("cache/hd");

        if (i == 0) {

            wj.sc(cache3);
            wj.sc(cachec1);
            wj.sc(cachec2);
            wj.sc(cachec3);
            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).kjd(0);
                    st.wb(R.id.title_bar).zf(wj.dqwb(ftitle));
                    st.xxbj(R.id.xxbj3).kjd(8);
                    st.xxbj(R.id.xxbj5).kjd(8);
                    st.xxbj(R.id.xxbj6).kjd(8);
                    st.xxbj(R.id.xxbj7).kjd(8);
                    st.xxbj(R.id.xxbj9).kjd(8);
                    lb.v.requestFocus();

                }

            });

        } else if (i == 1 || i == 2 || i == 3) {

            cdspq.sc();

            int[] kj = new int[]{R.id.wb1, R.id.wb2, R.id.wb3, R.id.wb4, R.id.wb5, -1};

            Object[] jk = new Object[]{"", "", "", "", "", 0};
            jk = new Object[]{"刷新", "", a, b, c, 0};
            cdspq.j(kj, jk);

            if (wj.cz(cache1) || wj.cz(cache2)) {

                jk = new Object[]{"粘贴到当前目录", "", a, b, c, 0};
                cdspq.j(kj, jk);
                jk = new Object[]{"取消粘贴", "", a, b, c, 0};
                cdspq.j(kj, jk);

            }

            if (!wj.cz(cache1) && !wj.cz(cache2)) {

                jk = new Object[]{"新建目录", "", a, b, c, 0};
                cdspq.j(kj, jk);
                jk = new Object[]{"新建文件", "", a, b, c, 0};
                cdspq.j(kj, jk);

                if (i == 1) {

                    jk = new Object[]{"复制", "1", a, b, c, 0};
                    cdspq.j(kj, jk);
                    jk = new Object[]{"剪切", "1", a, b, c, 0};
                    cdspq.j(kj, jk);
                    jk = new Object[]{"删除", "1", a, b, c, 0};
                    cdspq.j(kj, jk);
                    jk = new Object[]{"重命名", "1", a, b, c, 0};
                    cdspq.j(kj, jk);
                    jk = new Object[]{"属性", "1", a, b, c, 0};
                    cdspq.j(kj, jk);

                } else if (i == 2) {

                    jk = new Object[]{"打开方式", "0", a, b, c, 0};
                    cdspq.j(kj, jk);
                    jk = new Object[]{"复制", "2", a, b, c, 0};
                    cdspq.j(kj, jk);
                    jk = new Object[]{"剪切", "2", a, b, c, 0};
                    cdspq.j(kj, jk);
                    jk = new Object[]{"删除", "2", a, b, c, 0};
                    cdspq.j(kj, jk);
                    jk = new Object[]{"重命名", "2", a, b, c, 0};
                    cdspq.j(kj, jk);
                    jk = new Object[]{"属性", "2", a, b, c, 0};
                    cdspq.j(kj, jk);

                } else if (i == 3) {

                }

            }
            jk = new Object[]{"返回", "back", "", "", "", 1};
            cdspq.j(kj, jk);

            String spxx = AppPaths.appPath("settings/a3.inf");
            jk = new Object[]{"", "", "", "", "", -1};
            cdspq.j(kj, jk);

            String axc = wj.dqwb(spxx);

            if (zf.dy(axc, "1") || zf.dy(axc, "3")) {

                jk = new Object[]{"", "", "", "", "", -1};
                cdspq.j(kj, jk);
                jk = new Object[]{"", "", "", "", "", -1};
                cdspq.j(kj, jk);

            }
            gj.jmxc(new Runnable() {

                public void run() {

                    cdspq.sx();
                    cdlb.v.requestFocus();
                    st.v7lb(R.id.v7lb2).xzwz("top");
                    st.wb(R.id.title_bar).kjd(0);
                    st.wb(R.id.title_bar).zf("菜单");
                    st.xxbj(R.id.xxbj3).kjd(0);
                    st.xxbj(R.id.xxbj5).kjd(8);
                    st.xxbj(R.id.xxbj6).kjd(8);
                    st.xxbj(R.id.xxbj7).kjd(8);
                    st.xxbj(R.id.xxbj9).kjd(8);

                }

            });

        } else if (i == 4) {

            if (wj.cz(cache3)) {

                String w3 = wj.dqwb(cache3);

                if (zf.dy(w3, "重命名")) {

                    final String nr = a;
                    gj.jmxc(new Runnable() {

                        public void run() {

                            st.bjk(R.id.bjk1).zf(nr);
                            st.bjk(R.id.bjk1).tszf("请重命名文件");

                        }

                    });

                } else {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            st.bjk(R.id.bjk1).zf("");
                            st.bjk(R.id.bjk1).tszf("请输入目录/文件名");

                        }

                    });

                }

                final String xs = wj.dqwb(cache3);
                wj.xrwb(cachec1, a);
                wj.xrwb(cachec2, b);
                wj.xrwb(cachec3, c);
                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.title_bar).kjd(0);
                        st.wb(R.id.title_bar).zf(xs);
                        st.bjk(R.id.bjk1).tszf(xs);
                        st.xxbj(R.id.xxbj3).kjd(8);
                        st.xxbj(R.id.xxbj5).kjd(0);
                        st.xxbj(R.id.xxbj6).kjd(8);
                        st.xxbj(R.id.xxbj7).kjd(8);
                        st.xxbj(R.id.xxbj9).kjd(8);
                        st.qtgd(R.id.qtgd1).v.requestFocus();

                    }

                });

            } else {

                cdx(0, null, null, null);

            }

        } else if (i == 5) {

            sxspq.sc();

            int[] kj = new int[]{R.id.wb1, R.id.wb2, -1};

            Object[] jk = new Object[]{"", "", 0};
            jk = new Object[]{"名称", a, 0};
            sxspq.j(kj, jk);
            jk = new Object[]{"目录", c, 0};
            sxspq.j(kj, jk);

            if (wj.lx(b) == 1) {

                String dx = hqwjdx(b);
                jk = new Object[]{"大小", dx, 0};
                sxspq.j(kj, jk);

            } else {

                String dxs = wj.dqwb(cache41);
                jk = new Object[]{"大小", dxs, 0};
                sxspq.j(kj, jk);

            }
            jk = new Object[]{"返回", "", 1};
            sxspq.j(kj, jk);

            String spxx = AppPaths.appPath("settings/a3.inf");
            jk = new Object[]{"", "", -1};
            sxspq.j(kj, jk);

            String axc = wj.dqwb(spxx);

            if (zf.dy(axc, "1") || zf.dy(axc, "3")) {

                jk = new Object[]{"", "", -1};
                sxspq.j(kj, jk);
                jk = new Object[]{"", "", -1};
                sxspq.j(kj, jk);

            }
            gj.jmxc(new Runnable() {

                public void run() {

                    sxspq.sx();
                    sxlb.v.requestFocus();
                    st.v7lb(R.id.v7lb3).xzwz("top");
                    st.wb(R.id.title_bar).kjd(0);
                    st.wb(R.id.title_bar).zf("属性");
                    st.xxbj(R.id.xxbj3).kjd(8);
                    st.xxbj(R.id.xxbj5).kjd(8);
                    st.xxbj(R.id.xxbj6).kjd(0);
                    st.xxbj(R.id.xxbj7).kjd(8);
                    st.xxbj(R.id.xxbj9).kjd(8);

                }

            });

        } else if (i == 6) {

            dkspq.sc();

            int[] kj = new int[]{R.id.wb1, R.id.wb2, R.id.wb3, -1};

            Object[] jk = new Object[]{"", "", "", 0};
            jk = new Object[]{"文本", "a", b, 2};
            dkspq.j(kj, jk);
            jk = new Object[]{"图片", "b", b, 2};
            dkspq.j(kj, jk);
            jk = new Object[]{"音频", "c", b, 2};
            dkspq.j(kj, jk);
            jk = new Object[]{"视频", "d", b, 2};
            dkspq.j(kj, jk);
            jk = new Object[]{"压缩", "e", b, 2};
            dkspq.j(kj, jk);
            jk = new Object[]{"应用", "f", b, 2};
            dkspq.j(kj, jk);
            jk = new Object[]{"其他", "g", b, 2};
            dkspq.j(kj, jk);
            jk = new Object[]{"返回", "-1", "", 1};
            dkspq.j(kj, jk);

            String spxx = AppPaths.appPath("settings/a3.inf");
            jk = new Object[]{"", "", "", -1};
            dkspq.j(kj, jk);

            String axc = wj.dqwb(spxx);

            if (zf.dy(axc, "1") || zf.dy(axc, "3")) {

                jk = new Object[]{"", "", "", -1};
                dkspq.j(kj, jk);
                jk = new Object[]{"", "", "", -1};
                dkspq.j(kj, jk);

            }
            gj.jmxc(new Runnable() {

                public void run() {

                    dkspq.sx();
                    dklb.v.requestFocus();
                    st.v7lb(R.id.v7lb4).xzwz("top");
                    st.wb(R.id.title_bar).kjd(0);
                    st.wb(R.id.title_bar).zf("打开方式");
                    st.xxbj(R.id.xxbj3).kjd(8);
                    st.xxbj(R.id.xxbj5).kjd(8);
                    st.xxbj(R.id.xxbj6).kjd(8);
                    st.xxbj(R.id.xxbj7).kjd(0);
                    st.xxbj(R.id.xxbj9).kjd(8);

                }

            });

        } else {

        }

    }

    public void sx(String na, String lj) {
        d1_rootpaths = GlobalRuntimeState.d1_rootpath;

        String root = d1_rootpaths;
        NewFilesLoad(lj, root);

    }

    public String hqwjdx(Object url) {

        double dx = ((double) (wj.dx(url)));

        double d1 = 0;

        String d0;

        String wjdx = "";

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

    public void tsk(Object a, Object b) {

        final String[] name = new String[]{"title", "text"};

        final Object[] value = new Object[]{a, b};
        gj.jmxc(new Runnable() {

            public void run() {

                gj.tz(TextQrDisplayActivity.class, name, value);

            }

        });

    }

    public void tsk_QR(Object a, Object b, Object c) {

        final String[] name = new String[]{"title", "text", "QR-Code"};

        final Object[] value = new Object[]{a, b, c};
        gj.jmxc(new Runnable() {

            public void run() {

                gj.tz(TextQrDisplayActivity.class, name, value);

            }

        });

    }

    public void upload_error(Object a, Object b) {

        final String[] name = new String[]{"log", "location"};

        final Object[] value = new Object[]{a, b};
        gj.jmxc(new Runnable() {

            public void run() {

                gj.tz(CrashFeedbackActivity.class, name, value);

            }

        });

    }

    public String readJson(String text, String value) {

        String back = "";

        try {

            i.runlibrary.app.zf$json jo = zf.json(text);

            JSONObject json = jo.json;

            Object sjs = jo.hq(json, value);
            back = sjs.toString();

        } catch (Throwable e) {

            back = "0";

        }
        return back;

    }

    public String getMipmap(String v) {

        String back = "";

        String mipmaprooturl = AppPaths.appPath("res/mipmap/");

        String m = mipmaprooturl + v + ".png";

        try {

            if (wj.cz(m)) {

                back = m;

            } else {

                back = "0";

            }

        } catch (Throwable e) {

            back = "0";

        }
        return back;

    }

    public void loadTheme(String url) {

        int back = 0;

        String mipmaprooturl = AppPaths.appPath("res/");
        back = wj.jy(url, mipmaprooturl, true);

        if (back == 0) {

            tsk("提示", "导入主题失败\n文件可能已损坏");

        } else {

            tsk("提示", "导入主题成功");

        }

    }

    public String[] themeInfo() {

        String[] back = new String[]{"加载异常...", "加载异常...", "加载异常...", "加载异常...", "加载异常...", "加载异常..."};

        String info = AppPaths.appPath("res/Manifest.json");

        String text = wj.dqwb(info);

        String info1 = readJson(text, "name");

        if (!zf.dy(info1, "0")) {

            back[0] = info1;

        }

        String info2 = readJson(text, "version");

        if (!zf.dy(info2, "0")) {

            back[1] = info2;

        }

        String info3 = readJson(text, "maker");

        if (!zf.dy(info3, "0")) {

            back[2] = info3;

        }

        String info4 = readJson(text, "time");

        if (!zf.dy(info4, "0")) {

            back[3] = info4;

        }

        String info5 = readJson(text, "awg_version");

        if (!zf.dy(info5, "0")) {

            back[4] = info5;

        }

        String info6 = readJson(text, "tip");

        if (!zf.dy(info6, "0")) {

            back[5] = info6;

        }
        return back;

    }

    public String urlLockString(String t) {

        String back = "";
        t = zf.qctwkg(t);

        if (zf.ckt(t, "awg://")) {

            back = t;

        } else {

            if (zf.ckt(t, "https://service.typheye.cn/")) {

                String n = zf.qc(t, "https://service.typheye.cn/", null);
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

    public String urlUnlockString(String t) {

        String back = "";

        String url = "https://service.typheye.cn/";

        String http = "http://";

        String https = "https://";
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

    public String htmlToText(String t) {

        String back = "";
        t = zf.th(t, "&quot;", "\"");
        back = zf.th(t, "&amp;", "&");
        return back;

    }

    public void update(int a11) {

        String set36 = AppPaths.appPath("settings/fixVersion");

        final String appfixversion = zf.qctwkg(wj.dqwb(set36));

        final int myappversion = a11;

        String set2e = AppPaths.appPath("settings/f10/set2.inf");
        set2e = wj.dqwb(set2e);
        set2e = urlUnlockString(set2e);

        if (zf.cjw(set2e, "/")) {

        } else {

            set2e = set2e + "/";

        }
        set2e = set2e + "config.json";

        final String url = set2e;
        gj.xc(new Thread() {

            public void run() {

                if (yz()) {

                    String setback = "";

                    String version = "";

                    String version2 = "";

                    String back = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

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

                        String ek = "";

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

                            final String eks = ek;
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    gj.tz(AppUpdateActivity.class);
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

        String set2e = AppPaths.appPath("settings/f10/set2.inf");
        set2e = wj.dqwb(set2e);
        set2e = urlUnlockString(set2e) + "/online.php";

        final String url = set2e;
        gj.xc(new Thread() {

            public void run() {

                String setback = "";

                String version = "";

                String version2 = "";

                String back = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                if (zf.dy(back, "") || zf.dy(back, null)) {

                } else {

                }

            }

        });

    }

    public boolean isInstallX5() {

        boolean back;

        back = wj.cz(AppPaths.appPath("app_tbs/core_share/tbs.conf")) || wj.cz(AppPaths.appPath("app_tbs_64/core_share/tbs.conf"));
        return back;

    }
}
