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


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Process;
import android.view.View;

import com.file.Xor.xor;
import com.kingqi.zwcj.应用工具;

import android.app.Activity;
import android.content.Context;

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
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;
import open.cn.awg.pro.update.AppUpdateActivity;
import open.cn.awg.pro.update.UpdatePromptActivity;

@SuppressLint("StaticFieldLeak")
public class AppRuntimeBridge {
    private final Context context;
    private final Activity owner;

    public static open.cn.awg.pro.ui.list.NativeListAdapter spq = null;

    public static androidx.recyclerview.widget.RecyclerView lb = null;

    public static open.cn.awg.pro.ui.list.NativeListAdapter cdspq = null;

    public static androidx.recyclerview.widget.RecyclerView cdlb = null;

    public static open.cn.awg.pro.ui.list.NativeListAdapter sxspq = null;

    public static androidx.recyclerview.widget.RecyclerView sxlb = null;

    public static open.cn.awg.pro.ui.list.NativeListAdapter dkspq = null;

    public static androidx.recyclerview.widget.RecyclerView dklb = null;

public static String d1_rootpaths;
    public final AppRuntimeBridge lei = this, 类 = this;
    public LocalDatabaseBridge e11;

    public AppRuntimeBridge(Context context) {
        this.context = java.util.Objects.requireNonNull(context);
        this.owner = context instanceof Activity ? (Activity) context : null;
        e11 = new LocalDatabaseBridge(context);
    }

    public void getlic() {

        final String auid = AppPaths.appPath("settings/account/user");

        final String sec_license = AppPaths.appPath("data/sec/license");

        String set2e = AppPaths.appPath("settings/f10/set2.inf");
        set2e = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2e));
        set2e = urlUnlockString(set2e);

        if (open.cn.awg.pro.core.TextValues.endsWith(set2e, "/")) {

        } else {

            set2e = set2e + "/";

        }
        set2e = set2e + "config_v2.json";

        final String url = set2e;
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                String setback = "";

                String version = "";

                String allow = "";

                String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                    setback = "0";

                } else {

                    setback = htmlToText(back);

                    if (java.util.Objects.equals(setback, "0")) {

                        setback = "0";

                    } else {

                        version = readJson(setback, "UpdateVersion");
                        allow = readJson(setback, "UpdateAllow");
                        setback = String.valueOf(1);

                    }

                }

                if (java.util.Objects.equals(setback, "0")) {

                } else {

                    if ((java.util.Objects.equals(allow, "0") || open.cn.awg.pro.core.TextValues.contains(allow, open.cn.awg.pro.core.NativeFiles.readText(context, (String) (auid)))) && islogin()) {

                        open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (sec_license), (String) (open.cn.awg.pro.core.TextValues.md5(setback)));

                    } else {

                        open.cn.awg.pro.core.NativeFiles.delete(context, (String) (sec_license), false);

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
        set2e = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2e));
        set2e = urlUnlockString(set2e);

        if (open.cn.awg.pro.core.TextValues.endsWith(set2e, "/")) {

        } else {

            set2e = set2e + "/";

        }
        set2e = set2e + "config_v2.json";

        final String url = set2e;
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                if (yz()) {

                    String setback = "";

                    String version = "";

                    String versionname = "";

                    String allow = "";

                    String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                    if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                        setback = "0";

                    } else {

                        setback = htmlToText(back);

                        if (java.util.Objects.equals(setback, "0")) {

                            setback = "0";

                        } else {

                            version = readJson(setback, "UpdateVersion");
                            versionname = readJson(setback, "UpdateVersionName");
                            allow = readJson(setback, "UpdateAllow");
                            setback = String.valueOf(1);

                        }

                    }

                    if (java.util.Objects.equals(setback, "0")) {

                    } else {

                        if ((java.util.Objects.equals(allow, "0") || open.cn.awg.pro.core.TextValues.contains(allow, open.cn.awg.pro.core.NativeFiles.readText(context, (String) (auid)))) && islogin()) {

                            open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (sec_updater), (String) (setback));

                            String ek = "";

                            final int b1 = Integer.parseInt(version);

                            if (myappversion < b1 && b1 != 9999999) {

                                final String eks = ek;
                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (UpdatePromptActivity.class));

                                    }

                                });

                            } else {

                                int ccc = versionname.compareTo(vn);

                                if (!java.util.Objects.equals(versionname, vn) && ccc > 0) {

                                    final String eks = ek;
                                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                        public void run() {

                                            open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (UpdatePromptActivity.class));

                                        }

                                    });

                                } else {

                                    open.cn.awg.pro.core.NativeFiles.delete(context, (String) (sec_updater), false);

                                }

                            }

                        } else {

                            open.cn.awg.pro.core.NativeFiles.delete(context, (String) (sec_updater), false);

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
            a = open.cn.awg.pro.core.TextValues.md5(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (a)));

            String filename = open.cn.awg.pro.core.NativeDevice.formatDate(4) + ".awgbak";

            String backpath = AppPaths.appPath("settings");

            String mkdirpath = AppPaths.externalFilesPath("backup/.nomedia");

            String outpath = AppPaths.externalFilesPath("backup/") + filename;

            String out2path = "/storage/emulated/0/Download/" + filename;
            open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (mkdirpath), (String) (""));
            open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (tmp2path), (String) (""));

            int i = open.cn.awg.pro.core.NativeArchives.create(context, (String) (backpath), (String) (tmppath));
            xor.FileXor(tmppath, outpath, a);
            open.cn.awg.pro.core.NativeFiles.delete(context, (String) (tmppath), java.lang.Boolean.TRUE.equals(true));
            open.cn.awg.pro.core.NativeFiles.delete(context, (String) (mkdirpath), java.lang.Boolean.TRUE.equals(true));
            open.cn.awg.pro.core.NativeFiles.delete(context, (String) (tmp2path), java.lang.Boolean.TRUE.equals(true));

            if (i > 0) {

                if (open.cn.awg.pro.core.NativeFiles.copy(context, (String) (outpath), (String) (out2path), java.lang.Boolean.TRUE.equals(true))) {

                    open.cn.awg.pro.core.NativeFiles.delete(context, (String) (outpath), java.lang.Boolean.TRUE.equals(true));
                    tsk("提示", "导出备份成功\n请勿修改备份文件名，否则将无法导入！\n\n[文件路径]\n" + out2path);

                } else {

                    tsk("提示", "导出备份成功\n请勿修改备份文件名，否则将无法导入！\n\n[文件路径]\n" + outpath);

                }

            } else {

                tsk("提示", "导出备份失败");

            }

        } else {

            open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (TypheyeAccountActivity.class));
            tsk("提示", "该操作需要登录Typheye账户");

        }

    }

    public boolean app_backup2() {

        boolean bk = false;

        if (islogin()) {

            String a = AppPaths.appPath("settings/account/user");

            String tmppath = AppPaths.appPath("data/backup/tmp.awgbak");

            String tmp2path = AppPaths.appPath("data/backup/.nomedia");
            a = open.cn.awg.pro.core.TextValues.md5(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (a)));

            String filename = "last.awgbak.zip";

            String backpath = AppPaths.appPath("settings");

            String mkdirpath = AppPaths.appPath("data/backup/yun/.nomedia");

            String outpath = AppPaths.appPath("data/backup/yun/") + filename;

            if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (outpath))) {

                open.cn.awg.pro.core.NativeFiles.delete(context, (String) (outpath), false);

            }
            open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (mkdirpath), (String) (""));
            open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (tmp2path), (String) (""));

            int i = open.cn.awg.pro.core.NativeArchives.create(context, (String) (backpath), (String) (tmppath));
            xor.FileXor(tmppath, outpath, a);
            open.cn.awg.pro.core.NativeFiles.delete(context, (String) (tmppath), java.lang.Boolean.TRUE.equals(true));
            open.cn.awg.pro.core.NativeFiles.delete(context, (String) (tmp2path), java.lang.Boolean.TRUE.equals(true));
            open.cn.awg.pro.core.NativeFiles.delete(context, (String) (mkdirpath), java.lang.Boolean.TRUE.equals(true));

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
        a = open.cn.awg.pro.core.TextValues.md5(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (a)));

        String backpath = AppPaths.appPath("settings/");
        open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (tmp2path), (String) (""));
        open.cn.awg.pro.core.NativeFiles.copy(context, (String) (path), (String) (tmppath), java.lang.Boolean.TRUE.equals(true));
        xor.FileXor(tmppath, tmppath + "_", a);

        int i = open.cn.awg.pro.core.NativeArchives.extract(context, (String) (tmppath + "_"), (String) (backpath), java.lang.Boolean.TRUE.equals(true));
        open.cn.awg.pro.core.NativeFiles.delete(context, (String) (tmppath), java.lang.Boolean.TRUE.equals(true));
        open.cn.awg.pro.core.NativeFiles.delete(context, (String) (tmppath + "_"), java.lang.Boolean.TRUE.equals(true));
        open.cn.awg.pro.core.NativeFiles.delete(context, (String) (tmp2path), java.lang.Boolean.TRUE.equals(true));

        if (i > 0) {

            open.cn.awg.pro.core.NativeActions.sleep(1000);
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    String[] name = new String[]{"Msg"};

                    String[] value = new String[]{"导入成功 点击重启"};
                    open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (ErrorMessageActivity.class), (String[]) (name), (Object[]) (value));

                }

            });
            return true;

        } else {

            tsk("提示", "导入备份失败，请参考\"重要说明\"");

        }
        return false;

    }

    public boolean app_gorecovery() {

        

        if (open.cn.awg.pro.core.NativeActions.hasPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {

            if (islogin()) {

                String lists = "";

                String[] gl = new String[]{"*.awgbak"};

                File[] lb = open.cn.awg.pro.core.NativeFiles.search(context, "/storage/emulated/0/", gl);

                for (File c : lb) {

                    if (java.util.Objects.equals(lists, "")) {

                        lists = c.getAbsolutePath();

                    } else {

                        lists = lists + "\n" + c.getAbsolutePath();

                    }

                }

                long test0 = 100000000;

                String endpath = "";

                String[] d = open.cn.awg.pro.core.TextValues.split(lists, "\n", true);

                if (d.length > 0) {

                    for (String e : d) {

                        String[] bb;
                        bb = fileinfo("/storage/emulated/0/", e);

                        if (java.util.Objects.equals(bb[0], "true")) {

                            String sts = open.cn.awg.pro.core.TextValues.slice(bb[3], null, ".awgbak");

                            long test1 = open.cn.awg.pro.core.TextValues.longValue(sts, 0);

                            if (test1 > test0) {

                                test0 = test1;
                                endpath = e;

                            }

                        }

                    }

                    if (java.util.Objects.equals(endpath, "")) {

                        tsk("提示", "未在设备存储中找到备份文件");

                    } else {

                        return app_recovery(endpath);

                    }

                } else {

                    tsk("提示", "未在设备存储中找到备份文件");

                }

            } else {

                open.cn.awg.pro.core.NativeActions.finish(context);
                open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (TypheyeAccountActivity.class));
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
        open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (cachehd), (String) (pluid));

        String cachehd2 = AppPaths.appPath("data/cjlb/lbx_title");
        open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (cachehd2), (String) (pluname));

        ExtensionPluginBridge e14 = new ExtensionPluginBridge(context);

        String set1 = AppPaths.appPath("settings/f19/set1.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set1)), "true")) {

            if (e14.newG(Integer.parseInt(String.valueOf(pluid)))) {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (ExtensionPluginDetailActivity.class));

                    }

                });

            } else {

                tsk("提示", "没有可执行此操作的扩展");

            }

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (ExtensionPluginDetailActivity.class));

                }

            });

        }

    }

    public String awgwl_getuid() {

        String a = AppPaths.appPath("settings/account/user");

        String b = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (a));

        if (java.util.Objects.equals(b, null) || java.util.Objects.equals(b, "") || java.util.Objects.equals(b, "null")) {

            b = "0";

        }
        return b;

    }

    public boolean islogin() {

        String a = AppPaths.appPath("settings/account/user");

        String b = AppPaths.appPath("settings/account/pass");

        return open.cn.awg.pro.core.NativeFiles.exists(context, (String) (a)) && open.cn.awg.pro.core.NativeFiles.exists(context, (String) (b));

    }

    public boolean awgwl_state() {

        String ic = AppPaths.appPath("settings/f11/true");

        return open.cn.awg.pro.core.NativeFiles.exists(context, (String) (ic)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (ic)), "1");

    }

    public boolean awgwl_true() {

        String ic = AppPaths.appPath("settings/f11/true");

        boolean f = open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (ic), (String) ("1"));

        return f;

    }

    public void awgwl_false() {

        String ic = AppPaths.appPath("settings/f11/true");
        open.cn.awg.pro.core.NativeFiles.delete(context, (String) (ic), false);

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

        if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (a)) && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (a)), "")) {

            zt = true;

        } else {

            open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (AppActivationActivity.class));

        }
        return zt;

    }

    public void zfsb(Object id) {

        final Object i = id;
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.sleep(1000);

                if (java.util.Objects.equals(i, "a1")) {

                }

            }

        });

    }

    public void zfwc(Object id) {

        final Object i = id;
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.sleep(1000);

                if (java.util.Objects.equals(i, "a1")) {

                    jh();
                    tsk("提示", "应用激活成功");

                } else if (java.util.Objects.equals(i, "a0")) {

                    tsk("提示", "捐赠成功，感谢您的支持！");

                }

            }

        });

    }

    public void jh() {

        String a = String.valueOf(open.cn.awg.pro.core.TextValues.randomInclusive(1, 9));

        int i = 14;

        while (i > 0) {

            String b = String.valueOf(open.cn.awg.pro.core.TextValues.randomInclusive(0, 9));
            a = a + b;
            i = i - 1;

        }

        String j = AppPaths.appPath("settings/f7/Enable2");
        open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (j), (String) (a));

    }

    public boolean yz() {
//        String sec_isunlock = AppPaths.appPath("data/sec/unlock");
//        wj.xrwb(sec_isunlock, "true");
//        return true;
        

        String sec_isunlock = AppPaths.appPath("data/sec/unlock");

        String urls = AppPaths.appPath("settings/f10/set2.inf");
        urls = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (urls));
        urls = urlUnlockString(urls);

        if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

        } else {

            urls = urls + "/";

        }

        String ljf = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (AppPaths.appPath("data/assets/a17")));

        String a2 = 应用工具.获取应用签名(context, "open.cn.awg.pro");

        String path = 应用工具.获取应用apk文件(context, "open.cn.awg.pro");

        File mfile;
        mfile = new File(path);

        String aa = open.cn.awg.pro.core.TextValues.md5(mfile);

        int a11;

        String vn;
        a11 = com.demo.e4.getVersionCode(context);
        vn = com.demo.e4.getVersionName(context);

        String bb = String.valueOf(a11);

        String url = urls + "x.php?token=" + a2 + ljf + "id=" + aa + ljf + "ver=" + bb + ljf + "vername=" + vn;

        String st5 = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

        if (java.util.Objects.equals(st5, null)) {

            return false;

        } else {

            open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (sec_isunlock), (String) (st5));

            return java.util.Objects.equals(st5, "true");

        }

    }

    public boolean isJh() {

        boolean zt = false;

        String a = AppPaths.appPath("settings/f7/Enable2");

        if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (a)) && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (a)), "")) {

            zt = true;

        }
        return zt;

    }

    public void csh() {
        open.cn.awg.pro.core.NativeFiles.writeText(context, AppPaths.appPath("cache/hd"), "");
        lb = owner.findViewById(R.id.v7lb1);
        spq = open.cn.awg.pro.ui.list.NativeListAdapter.attach(owner, lb, R.layout.file_list_item, new open.cn.awg.pro.ui.list.FileListRowBinder(owner), new open.cn.awg.pro.ui.list.NativeListAdapter.RowBinder() {

            public void bind(View vw, java.util.Map<Integer, Object> row, int pn) {

                

                int f = open.cn.awg.pro.core.TextValues.intValue(row.get(-1), -1);

                if (f == -1) {

                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xdbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xdbj1)).setClickable(java.lang.Boolean.TRUE.equals(false));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xdbj1)).setLongClickable(java.lang.Boolean.TRUE.equals(false));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.dp20)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                } else {

                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xdbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xdbj1)).setClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xdbj1)).setLongClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.dp20)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            }

        });
        cdlb = owner.findViewById(R.id.v7lb2);
        cdspq = open.cn.awg.pro.ui.list.NativeListAdapter.attach(owner, cdlb, R.layout.file_operation_menu_item, new open.cn.awg.pro.ui.list.FileOperationMenuRowBinder(owner), new open.cn.awg.pro.ui.list.NativeListAdapter.RowBinder() {

            public void bind(View vw, java.util.Map<Integer, Object> row, int pn) {

                

                int f = open.cn.awg.pro.core.TextValues.intValue(row.get(-1), -1);

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
        sxlb = owner.findViewById(R.id.v7lb3);
        sxspq = open.cn.awg.pro.ui.list.NativeListAdapter.attach(owner, sxlb, R.layout.file_operation_info_item, new open.cn.awg.pro.ui.list.FileOperationInfoRowBinder(owner), new open.cn.awg.pro.ui.list.NativeListAdapter.RowBinder() {

            public void bind(View vw, java.util.Map<Integer, Object> row, int pn) {

                

                int f = open.cn.awg.pro.core.TextValues.intValue(row.get(-1), -1);

                if (f == 1) {

                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                    String color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextFalse);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setLongClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.dp20)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                } else if (f == -1) {

                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("left"));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                    String color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setClickable(java.lang.Boolean.TRUE.equals(false));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setLongClickable(java.lang.Boolean.TRUE.equals(false));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.dp20)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                } else {

                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("left"));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                    String color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setLongClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.dp20)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            }

        });
        dklb = owner.findViewById(R.id.v7lb4);
        dkspq = open.cn.awg.pro.ui.list.NativeListAdapter.attach(owner, dklb, R.layout.file_open_option_item, new open.cn.awg.pro.ui.list.FileOpenOptionRowBinder(owner), new open.cn.awg.pro.ui.list.NativeListAdapter.RowBinder() {

            public void bind(View vw, java.util.Map<Integer, Object> row, int pn) {

                

                int f = open.cn.awg.pro.core.TextValues.intValue(row.get(-1), -1);

                if (f == 1) {

                    String color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextFalse);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setLongClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.dp20)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                } else if (f == -1) {

                    String color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setClickable(java.lang.Boolean.TRUE.equals(false));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setLongClickable(java.lang.Boolean.TRUE.equals(false));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.dp20)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                } else if (f == 0) {

                    String color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setLongClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.dp20)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                } else if (f == -2) {

                    String color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextTrue);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setLongClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.dp20)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                } else {

                    String color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setLongClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.dp20)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            }

        });
    }

    public String[] fileinfo(Object root, Object url) {

        String Root = open.cn.awg.pro.core.NativeFiles.resolvePath(context, (String) (url));

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

        String root = open.cn.awg.pro.core.NativeFiles.resolvePath(context, (String) (roots));

        String srcs = "";

        try {

            String FileUrl = open.cn.awg.pro.core.NativeFiles.resolvePath(context, (String) (url));

            if (open.cn.awg.pro.core.TextValues.endsWith(FileUrl, "/")) {
            } else {

                FileUrl = FileUrl + "/";

            }
            spq.clearRows();

            int[] kj = new int[]{R.id.tx1, R.id.wb1, R.id.wb2, R.id.wb3, -1};

            Object[] jk = new Object[]{R.mipmap.b5, "...", FileUrl, "返回上层目录", 0};

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)), "true")) {

                srcs = getMipmap("b5");

                if (java.util.Objects.equals(srcs, "0")) {

                    jk = new Object[]{R.mipmap.b5, "...", FileUrl, "返回上层目录", 0};

                } else {

                    jk = new Object[]{srcs, "...", FileUrl, "返回上层目录", 0};

                }

            } else {

                jk = new Object[]{R.mipmap.b5, "...", FileUrl, "返回上层目录", 0};

            }
            spq.addRow(kj, jk);

            String[] FileXH1 = open.cn.awg.pro.core.NativeFiles.list(context, (String) (url), java.lang.Boolean.TRUE.equals(true));

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set3)), "true")) {

                Arrays.sort(FileXH1);

            }

            for (String c : FileXH1) {

                String urlName = "";

                String[] fhValue = fileinfo(root, c);

                if (java.util.Objects.equals(fhValue[0], "true")) {

                    urlName = fhValue[3];

                } else {

                    urlName = "Error";

                }

                String Two = "目录";

                if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)), "true")) {

                    srcs = getMipmap("b5");

                    if (java.util.Objects.equals(srcs, "0")) {

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

                if (java.util.Objects.equals(c, pb1) || java.util.Objects.equals(c, pb2) || java.util.Objects.equals(c, pb3) || java.util.Objects.equals(c, pb4) || java.util.Objects.equals(c, pb5) || (android.os.Build.VERSION.SDK_INT > 29 && java.util.Objects.equals(c, pb6)) || (android.os.Build.VERSION.SDK_INT > 29 && java.util.Objects.equals(c, pb7))) {

                } else {

                    spq.addRow(kj, jk);

                }

            }

            String[] FileXH2 = open.cn.awg.pro.core.NativeFiles.list(context, (String) (url), java.lang.Boolean.TRUE.equals(false));

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set3)), "true")) {

                Arrays.sort(FileXH2);

            }

            for (String c : FileXH2) {

                String bt = "";

                String[] fhValue = fileinfo(root, c);

                if (java.util.Objects.equals(fhValue[0], "true")) {

                    bt = fhValue[3];

                } else {

                    bt = "Error";

                }

                double dx = ((double) (open.cn.awg.pro.core.NativeFiles.size(context, (String) (c))));

                double d1 = 0;

                String d0;

                String cc = open.cn.awg.pro.core.TextValues.lower(c);

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

                if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".apk") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".xapk") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".apks")) {

                    if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)), "true")) {

                        srcs = getMipmap("b1");

                        if (java.util.Objects.equals(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b1, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b1, bt, c, wjdx, 0};

                    }
                    spq.addRow(kj, jk);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".png") || open.cn.awg.pro.core.TextValues.endsWith(cc, "jpg")) {

                    if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set1)), "true")) {

                        jk = new Object[]{c, bt, c, wjdx, 0};

                    } else {

                        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)), "true")) {

                            srcs = getMipmap("b11");

                            if (java.util.Objects.equals(srcs, "0")) {

                                jk = new Object[]{R.mipmap.b11, bt, c, wjdx, 0};

                            } else {

                                jk = new Object[]{srcs, bt, c, wjdx, 0};

                            }

                        } else {

                            jk = new Object[]{R.mipmap.b11, bt, c, wjdx, 0};

                        }

                    }
                    spq.addRow(kj, jk);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".mp3") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".flac") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".ogg")) {

                    if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)), "true")) {

                        srcs = getMipmap("b9");

                        if (java.util.Objects.equals(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b9, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b9, bt, c, wjdx, 0};

                    }
                    spq.addRow(kj, jk);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".mp4") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".mkv") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".webm") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".m3u8") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".avi") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".mts")) {

                    if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)), "true")) {

                        srcs = getMipmap("b8");

                        if (java.util.Objects.equals(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b8, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b8, bt, c, wjdx, 0};

                    }
                    spq.addRow(kj, jk);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".txt") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".xml") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".java")) {

                    if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)), "true")) {

                        srcs = getMipmap("b13");

                        if (java.util.Objects.equals(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b13, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b13, bt, c, wjdx, 0};

                    }
                    spq.addRow(kj, jk);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".zip") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".rar") || open.cn.awg.pro.core.TextValues.endsWith(cc, "7z")) {

                    if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)), "true")) {

                        srcs = getMipmap("b16");

                        if (java.util.Objects.equals(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b16, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b16, bt, c, wjdx, 0};

                    }
                    spq.addRow(kj, jk);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".html")) {

                    if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)), "true")) {

                        srcs = getMipmap("b6");

                        if (java.util.Objects.equals(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b6, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b6, bt, c, wjdx, 0};

                    }
                    spq.addRow(kj, jk);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".pdf")) {

                    if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)), "true")) {

                        srcs = getMipmap("b10");

                        if (java.util.Objects.equals(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b10, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b10, bt, c, wjdx, 0};

                    }
                    spq.addRow(kj, jk);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".doc") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".docx")) {

                    if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)), "true")) {

                        srcs = getMipmap("b15");

                        if (java.util.Objects.equals(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b15, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b15, bt, c, wjdx, 0};

                    }
                    spq.addRow(kj, jk);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".xls") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".xlsx")) {

                    if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)), "true")) {

                        srcs = getMipmap("b2");

                        if (java.util.Objects.equals(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b2, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b2, bt, c, wjdx, 0};

                    }
                    spq.addRow(kj, jk);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".ppt") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".pptx")) {

                    if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)), "true")) {

                        srcs = getMipmap("b12");

                        if (java.util.Objects.equals(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b12, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b12, bt, c, wjdx, 0};

                    }
                    spq.addRow(kj, jk);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".awgtheme")) {

                    if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)), "true")) {

                        srcs = getMipmap("b17");

                        if (java.util.Objects.equals(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b17, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b17, bt, c, wjdx, 0};

                    }
                    spq.addRow(kj, jk);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".awgsash")) {

                    if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)), "true")) {

                        srcs = getMipmap("b18");

                        if (java.util.Objects.equals(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b18, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b18, bt, c, wjdx, 0};

                    }
                    spq.addRow(kj, jk);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".sh")) {

                    if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)), "true")) {

                        srcs = getMipmap("b19");

                        if (java.util.Objects.equals(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b19, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b19, bt, c, wjdx, 0};

                    }
                    spq.addRow(kj, jk);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".awgbak")) {

                    if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)), "true")) {

                        srcs = getMipmap("b20");

                        if (java.util.Objects.equals(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b20, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b20, bt, c, wjdx, 0};

                    }
                    spq.addRow(kj, jk);

                } else {

                    if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)), "true")) {

                        srcs = getMipmap("b14");

                        if (java.util.Objects.equals(srcs, "0")) {

                            jk = new Object[]{R.mipmap.b14, bt, c, wjdx, 0};

                        } else {

                            jk = new Object[]{srcs, bt, c, wjdx, 0};

                        }

                    } else {

                        jk = new Object[]{R.mipmap.b14, bt, c, wjdx, 0};

                    }
                    spq.addRow(kj, jk);

                }

            }
            jk = new Object[]{"", "", "", "", -1};
            spq.addRow(kj, jk);

            String axc = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (spxx));

            if (java.util.Objects.equals(axc, "1") || java.util.Objects.equals(axc, "3")) {

                jk = new Object[]{"", "", "", "", -1};
                spq.addRow(kj, jk);
                jk = new Object[]{"", "", "", "", -1};
                spq.addRow(kj, jk);

            }

            final String ok = FileUrl;
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    spq.refreshRows();
                    open.cn.awg.pro.core.NativeViewValues.scrollPosition(lb, e11.get_filelist(ok));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.zywblj)).setText(String.valueOf(ok));

                }

            });

        } catch (Throwable e) {

            upload_error(e, "e1.NewFilesLoad(s,s)");
            open.cn.awg.pro.core.NativeActions.finish(context);

        }

    }

    public void fileTouch(Object text, Object url) {
        d1_rootpaths = GlobalRuntimeState.d1_rootpath;

        String root = d1_rootpaths;

        if (java.util.Objects.equals(text, "返回上层目录")) {

            if (java.util.Objects.equals(url, root)) {

                open.cn.awg.pro.core.NativeActions.finish(context);

            } else {

                String[] fhValue = fileinfo("%", url);
                NewFilesLoad(fhValue[2], root);

            }

        } else {

            if (open.cn.awg.pro.core.NativeFiles.type(context, (String) (url)) == 2) {

                NewFilesLoad(url, root);

            } else {

                String[] fhValue = fileinfo(root, url);

                String bts = fhValue[3];

                String lj = fhValue[1];

                String ljs = fhValue[2];

                String c = lj;

                String cc = open.cn.awg.pro.core.TextValues.lower(c);

                if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".png") || open.cn.awg.pro.core.TextValues.endsWith(cc, "jpg")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("b", c, 1);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".txt") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".xml") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".java")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("a", c, 1);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".zip") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".rar") || open.cn.awg.pro.core.TextValues.endsWith(cc, "7z")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("e", c, 1);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".html")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("a", c, 1);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".pdf")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("g", c, 1);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".doc") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".docx")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("g", c, 1);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".xls") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".xlsx")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("g", c, 1);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".ppt") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".pptx")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("g", c, 1);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".apk") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".xapk") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".apks")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("f", c, 1);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".mp3") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".flac") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".ogg")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("c", c, 1);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".mp4") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".mkv") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".webm") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".m3u8") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".avi") || open.cn.awg.pro.core.TextValues.endsWith(cc, ".mts")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("d", c, 1);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".awgtheme")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("g", c, 1);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".awgsash")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("g", c, 1);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".sh")) {

                    cdx(6, bts, lj, ljs);
                    dkfs("g", c, 1);

                } else if (open.cn.awg.pro.core.TextValues.endsWith(cc, ".awgbak")) {

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
        dkspq.clearRows();

        int[] kj = new int[]{R.id.wb1, R.id.wb2, R.id.wb3, -1};

        Object[] jk = new Object[]{"", "", "", 0};

        if (java.util.Objects.equals(z, "a")) {

            xx = "文本";

            if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h1")) {

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h2")) {

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h3")) {

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h4")) {

            } else {

                jk = new Object[]{"腕管Pro：文本浏览", "a1", b, 0};
                dkspq.addRow(kj, jk);
                jk = new Object[]{"腕管Pro：文本编辑", "a2", b, 0};
                dkspq.addRow(kj, jk);

            }

        } else if (java.util.Objects.equals(z, "b")) {

            xx = "图片";

            if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h1")) {

                jk = new Object[]{"腕上微聊：选择附件", "h1", b, 0};
                dkspq.addRow(kj, jk);
                jk = new Object[]{"PinchImageView：图片浏览", "b1", b, 0};
                dkspq.addRow(kj, jk);

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose2)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose2)), "h1")) {

                jk = new Object[]{"账户头像：选择附件", "i1", b, 0};
                dkspq.addRow(kj, jk);
                jk = new Object[]{"PinchImageView：图片浏览", "b1", b, 0};
                dkspq.addRow(kj, jk);

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h2")) {

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h3")) {

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h4")) {

            } else {

                jk = new Object[]{"PinchImageView：图片浏览", "b1", b, 0};
                dkspq.addRow(kj, jk);

            }

        } else if (java.util.Objects.equals(z, "c")) {

            xx = "音频";

            if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h1")) {

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h2")) {

                jk = new Object[]{"腕上微聊：选择附件", "h2", b, 0};
                dkspq.addRow(kj, jk);
                jk = new Object[]{"DKPlayer：媒体浏览", "c2", b, 0};
                dkspq.addRow(kj, jk);

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h3")) {

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h4")) {

            } else {

                jk = new Object[]{"DKPlayer：媒体浏览", "c2", b, 0};
                dkspq.addRow(kj, jk);
                jk = new Object[]{"WearMusic：音频浏览", "c1", b, -2};

                boolean apppd1;
                apppd1 = com.demo.e8.app(context, "cn.wearbbs.music");

                if (apppd1) {

                    dkspq.addRow(kj, jk);

                }
                jk = new Object[]{"抬腕视频：音频浏览", "c3", b, -2};

                boolean apppd2;
                apppd2 = com.demo.e8.app(context, "com.hankmi.wearmusic");

                if (apppd2) {

                    dkspq.addRow(kj, jk);

                }

            }

        } else if (java.util.Objects.equals(z, "d")) {

            xx = "视频";

            if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h1")) {

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h2")) {

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h3")) {

                jk = new Object[]{"腕上微聊：选择附件", "h3", b, 0};
                dkspq.addRow(kj, jk);
                jk = new Object[]{"DKPlayer：媒体浏览", "d2", b, 0};
                dkspq.addRow(kj, jk);

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h4")) {

            } else {

                jk = new Object[]{"DKPlayer：媒体浏览", "d2", b, 0};
                dkspq.addRow(kj, jk);
                jk = new Object[]{"腕上视频：视频浏览", "d3", b, -2};

                boolean apppd1;
                apppd1 = com.demo.e8.app(context, "cn.luern0313.wristvideoplayer");

                if (apppd1) {

                    dkspq.addRow(kj, jk);

                }
                jk = new Object[]{"抬腕视频：视频浏览", "d4", b, -2};

                boolean apppd2;
                apppd2 = com.demo.e8.app(context, "com.hankmi.media");

                if (apppd2) {

                    dkspq.addRow(kj, jk);

                }

            }

        } else if (java.util.Objects.equals(z, "e")) {

            xx = "压缩";

            if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h1")) {

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h2")) {

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h3")) {

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h4")) {

                jk = new Object[]{"腕上微聊：选择附件", "h4", b, 0};
                dkspq.addRow(kj, jk);

            } else {

                jk = new Object[]{"腕管Pro：解压到当前目录", "e1", b, 0};
                dkspq.addRow(kj, jk);

            }

        } else if (java.util.Objects.equals(z, "f")) {

            xx = "应用";

            if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h1")) {

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h2")) {

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h3")) {

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h4")) {

            } else {

                jk = new Object[]{"腕管Pro：应用安装", "f1", b, 0};
                dkspq.addRow(kj, jk);
                jk = new Object[]{"系统：打包安装程序", "f2", b, 0};
                dkspq.addRow(kj, jk);
                jk = new Object[]{"系统：打包安装程序(备用)", "f3", b, 0};
                dkspq.addRow(kj, jk);

            }

        } else if (java.util.Objects.equals(z, "g")) {

            xx = "其他";

            if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h1")) {

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h2")) {

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h3")) {

            } else if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (choose)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (choose)), "h4")) {

            } else {

                jk = new Object[]{"腕管Pro：系统打开", "g1", b, 0};
                dkspq.addRow(kj, jk);
                jk = new Object[]{"腕管Pro：关于主题文件", "g2", b, 0};

                if (open.cn.awg.pro.core.TextValues.endsWith(b, ".awgtheme")) {

                    dkspq.addRow(kj, jk);

                }
                jk = new Object[]{"腕管Pro：无障碍脚本执行", "g3", b, 0};

                if (open.cn.awg.pro.core.TextValues.endsWith(b, ".awgsash")) {

                    dkspq.addRow(kj, jk);

                }
                jk = new Object[]{"腕管Pro：Shell脚本执行", "g4", b, 0};

                if (open.cn.awg.pro.core.TextValues.endsWith(b, ".sh")) {

                    dkspq.addRow(kj, jk);

                }
                jk = new Object[]{"腕管Pro：关于备份文件", "g5", b, 0};

                if (open.cn.awg.pro.core.TextValues.endsWith(b, ".awgbak")) {

                    dkspq.addRow(kj, jk);

                }

            }

        }

        if (e == 0) {

            jk = new Object[]{"返回", "0", "", 1};
            dkspq.addRow(kj, jk);

        } else if (e == 1) {

            jk = new Object[]{"返回", "-1", "", 1};
            dkspq.addRow(kj, jk);

        }

        String spxx = AppPaths.appPath("settings/a3.inf");

        String axc = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (spxx));

        if (java.util.Objects.equals(axc, "1") || java.util.Objects.equals(axc, "3")) {

            jk = new Object[]{"", "", "", -1};
            dkspq.addRow(kj, jk);
            jk = new Object[]{"", "", "", -1};
            dkspq.addRow(kj, jk);

        }

        final String xxs = xx;
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                dkspq.refreshRows();
                open.cn.awg.pro.core.NativeViewValues.scrollPosition(((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.v7lb4)), "top");
                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf(xxs));

            }

        });

    }

    public void cdx(int i, String a, String b, String c) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

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

            open.cn.awg.pro.core.NativeFiles.delete(context, (String) (cache3), false);
            open.cn.awg.pro.core.NativeFiles.delete(context, (String) (cachec1), false);
            open.cn.awg.pro.core.NativeFiles.delete(context, (String) (cachec2), false);
            open.cn.awg.pro.core.NativeFiles.delete(context, (String) (cachec3), false);
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (ftitle))));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj5)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj7)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    lb.requestFocus();

                }

            });

        } else if (i == 1 || i == 2 || i == 3) {

            cdspq.clearRows();

            int[] kj = new int[]{R.id.wb1, R.id.wb2, R.id.wb3, R.id.wb4, R.id.wb5, -1};

            Object[] jk = new Object[]{"", "", "", "", "", 0};
            jk = new Object[]{"刷新", "", a, b, c, 0};
            cdspq.addRow(kj, jk);

            if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (cache1)) || open.cn.awg.pro.core.NativeFiles.exists(context, (String) (cache2))) {

                jk = new Object[]{"粘贴到当前目录", "", a, b, c, 0};
                cdspq.addRow(kj, jk);
                jk = new Object[]{"取消粘贴", "", a, b, c, 0};
                cdspq.addRow(kj, jk);

            }

            if (!open.cn.awg.pro.core.NativeFiles.exists(context, (String) (cache1)) && !open.cn.awg.pro.core.NativeFiles.exists(context, (String) (cache2))) {

                jk = new Object[]{"新建目录", "", a, b, c, 0};
                cdspq.addRow(kj, jk);
                jk = new Object[]{"新建文件", "", a, b, c, 0};
                cdspq.addRow(kj, jk);

                if (i == 1) {

                    jk = new Object[]{"复制", "1", a, b, c, 0};
                    cdspq.addRow(kj, jk);
                    jk = new Object[]{"剪切", "1", a, b, c, 0};
                    cdspq.addRow(kj, jk);
                    jk = new Object[]{"删除", "1", a, b, c, 0};
                    cdspq.addRow(kj, jk);
                    jk = new Object[]{"重命名", "1", a, b, c, 0};
                    cdspq.addRow(kj, jk);
                    jk = new Object[]{"属性", "1", a, b, c, 0};
                    cdspq.addRow(kj, jk);

                } else if (i == 2) {

                    jk = new Object[]{"打开方式", "0", a, b, c, 0};
                    cdspq.addRow(kj, jk);
                    jk = new Object[]{"复制", "2", a, b, c, 0};
                    cdspq.addRow(kj, jk);
                    jk = new Object[]{"剪切", "2", a, b, c, 0};
                    cdspq.addRow(kj, jk);
                    jk = new Object[]{"删除", "2", a, b, c, 0};
                    cdspq.addRow(kj, jk);
                    jk = new Object[]{"重命名", "2", a, b, c, 0};
                    cdspq.addRow(kj, jk);
                    jk = new Object[]{"属性", "2", a, b, c, 0};
                    cdspq.addRow(kj, jk);

                } else if (i == 3) {

                }

            }
            jk = new Object[]{"返回", "back", "", "", "", 1};
            cdspq.addRow(kj, jk);

            String spxx = AppPaths.appPath("settings/a3.inf");
            jk = new Object[]{"", "", "", "", "", -1};
            cdspq.addRow(kj, jk);

            String axc = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (spxx));

            if (java.util.Objects.equals(axc, "1") || java.util.Objects.equals(axc, "3")) {

                jk = new Object[]{"", "", "", "", "", -1};
                cdspq.addRow(kj, jk);
                jk = new Object[]{"", "", "", "", "", -1};
                cdspq.addRow(kj, jk);

            }
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    cdspq.refreshRows();
                    cdlb.requestFocus();
                    open.cn.awg.pro.core.NativeViewValues.scrollPosition(((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.v7lb2)), "top");
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("菜单"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj5)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj7)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        } else if (i == 4) {

            if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (cache3))) {

                String w3 = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cache3));

                if (java.util.Objects.equals(w3, "重命名")) {

                    final String nr = a;
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(nr));
                            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setHint(String.valueOf("请重命名文件"));

                        }

                    });

                } else {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(""));
                            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setHint(String.valueOf("请输入目录/文件名"));

                        }

                    });

                }

                final String xs = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cache3));
                open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (cachec1), (String) (a));
                open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (cachec2), (String) (b));
                open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (cachec3), (String) (c));
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf(xs));
                        ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setHint(String.valueOf(xs));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj5)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj7)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                        ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.qtgd1)).requestFocus();

                    }

                });

            } else {

                cdx(0, null, null, null);

            }

        } else if (i == 5) {

            sxspq.clearRows();

            int[] kj = new int[]{R.id.wb1, R.id.wb2, -1};

            Object[] jk = new Object[]{"", "", 0};
            jk = new Object[]{"名称", a, 0};
            sxspq.addRow(kj, jk);
            jk = new Object[]{"目录", c, 0};
            sxspq.addRow(kj, jk);

            if (open.cn.awg.pro.core.NativeFiles.type(context, (String) (b)) == 1) {

                String dx = hqwjdx(b);
                jk = new Object[]{"大小", dx, 0};
                sxspq.addRow(kj, jk);

            } else {

                String dxs = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cache41));
                jk = new Object[]{"大小", dxs, 0};
                sxspq.addRow(kj, jk);

            }
            jk = new Object[]{"返回", "", 1};
            sxspq.addRow(kj, jk);

            String spxx = AppPaths.appPath("settings/a3.inf");
            jk = new Object[]{"", "", -1};
            sxspq.addRow(kj, jk);

            String axc = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (spxx));

            if (java.util.Objects.equals(axc, "1") || java.util.Objects.equals(axc, "3")) {

                jk = new Object[]{"", "", -1};
                sxspq.addRow(kj, jk);
                jk = new Object[]{"", "", -1};
                sxspq.addRow(kj, jk);

            }
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    sxspq.refreshRows();
                    sxlb.requestFocus();
                    open.cn.awg.pro.core.NativeViewValues.scrollPosition(((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.v7lb3)), "top");
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("属性"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj5)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj7)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        } else if (i == 6) {

            dkspq.clearRows();

            int[] kj = new int[]{R.id.wb1, R.id.wb2, R.id.wb3, -1};

            Object[] jk = new Object[]{"", "", "", 0};
            jk = new Object[]{"文本", "a", b, 2};
            dkspq.addRow(kj, jk);
            jk = new Object[]{"图片", "b", b, 2};
            dkspq.addRow(kj, jk);
            jk = new Object[]{"音频", "c", b, 2};
            dkspq.addRow(kj, jk);
            jk = new Object[]{"视频", "d", b, 2};
            dkspq.addRow(kj, jk);
            jk = new Object[]{"压缩", "e", b, 2};
            dkspq.addRow(kj, jk);
            jk = new Object[]{"应用", "f", b, 2};
            dkspq.addRow(kj, jk);
            jk = new Object[]{"其他", "g", b, 2};
            dkspq.addRow(kj, jk);
            jk = new Object[]{"返回", "-1", "", 1};
            dkspq.addRow(kj, jk);

            String spxx = AppPaths.appPath("settings/a3.inf");
            jk = new Object[]{"", "", "", -1};
            dkspq.addRow(kj, jk);

            String axc = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (spxx));

            if (java.util.Objects.equals(axc, "1") || java.util.Objects.equals(axc, "3")) {

                jk = new Object[]{"", "", "", -1};
                dkspq.addRow(kj, jk);
                jk = new Object[]{"", "", "", -1};
                dkspq.addRow(kj, jk);

            }
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    dkspq.refreshRows();
                    dklb.requestFocus();
                    open.cn.awg.pro.core.NativeViewValues.scrollPosition(((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.v7lb4)), "top");
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("打开方式"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj5)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj7)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

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

        double dx = ((double) (open.cn.awg.pro.core.NativeFiles.size(context, (String) (url))));

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
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (TextQrDisplayActivity.class), (String[]) (name), (Object[]) (value));

            }

        });

    }

    public void tsk_QR(Object a, Object b, Object c) {

        final String[] name = new String[]{"title", "text", "QR-Code"};

        final Object[] value = new Object[]{a, b, c};
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (TextQrDisplayActivity.class), (String[]) (name), (Object[]) (value));

            }

        });

    }

    public void upload_error(Object a, Object b) {

        final String[] name = new String[]{"log", "location"};

        final Object[] value = new Object[]{a, b};
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (CrashFeedbackActivity.class), (String[]) (name), (Object[]) (value));

            }

        });

    }

    public String readJson(String text, String value) {

        String back = "";

        try {

            org.json.JSONObject jo = (org.json.JSONObject) (Object) open.cn.awg.pro.core.JsonValues.parseObject(text);

            JSONObject json = jo;

            Object sjs = open.cn.awg.pro.core.JsonValues.value(json, value);
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

            if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (m))) {

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
        back = open.cn.awg.pro.core.NativeArchives.extract(context, (String) (url), (String) (mipmaprooturl), java.lang.Boolean.TRUE.equals(true));

        if (back == 0) {

            tsk("提示", "导入主题失败\n文件可能已损坏");

        } else {

            tsk("提示", "导入主题成功");

        }

    }

    public String[] themeInfo() {

        String[] back = new String[]{"加载异常...", "加载异常...", "加载异常...", "加载异常...", "加载异常...", "加载异常..."};

        String info = AppPaths.appPath("res/Manifest.json");

        String text = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (info));

        String info1 = readJson(text, "name");

        if (!java.util.Objects.equals(info1, "0")) {

            back[0] = info1;

        }

        String info2 = readJson(text, "version");

        if (!java.util.Objects.equals(info2, "0")) {

            back[1] = info2;

        }

        String info3 = readJson(text, "maker");

        if (!java.util.Objects.equals(info3, "0")) {

            back[2] = info3;

        }

        String info4 = readJson(text, "time");

        if (!java.util.Objects.equals(info4, "0")) {

            back[3] = info4;

        }

        String info5 = readJson(text, "awg_version");

        if (!java.util.Objects.equals(info5, "0")) {

            back[4] = info5;

        }

        String info6 = readJson(text, "tip");

        if (!java.util.Objects.equals(info6, "0")) {

            back[5] = info6;

        }
        return back;

    }

    public String urlLockString(String t) {

        String back = "";
        t = open.cn.awg.pro.core.TextValues.trim(t);

        if (open.cn.awg.pro.core.TextValues.startsWith(t, "awg://")) {

            back = t;

        } else {

            if (open.cn.awg.pro.core.TextValues.startsWith(t, "https://service.typheye.cn/")) {

                String n = open.cn.awg.pro.core.TextValues.slice(t, "https://service.typheye.cn/", null);
                back = "awg://web.url/" + n;

            } else if (open.cn.awg.pro.core.TextValues.startsWith(t, "https://")) {

                t = open.cn.awg.pro.core.TextValues.replace(t, "https://", "");
                back = "awg://thirds.url/" + t;

            } else if (open.cn.awg.pro.core.TextValues.startsWith(t, "http://")) {

                t = open.cn.awg.pro.core.TextValues.replace(t, "http://", "");
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
        t = open.cn.awg.pro.core.TextValues.trim(t);

        if (open.cn.awg.pro.core.TextValues.startsWith(t, "awg://")) {

            if (open.cn.awg.pro.core.TextValues.startsWith(t, "awg://web.url/")) {

                back = open.cn.awg.pro.core.TextValues.replace(t, "awg://web.url/", url);

            } else if (open.cn.awg.pro.core.TextValues.startsWith(t, "awg://third.url/")) {

                back = open.cn.awg.pro.core.TextValues.replace(t, "awg://third.url/", http);

            } else if (open.cn.awg.pro.core.TextValues.startsWith(t, "awg://thirds.url/")) {

                back = open.cn.awg.pro.core.TextValues.replace(t, "awg://thirds.url/", https);

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
        t = open.cn.awg.pro.core.TextValues.replace(t, "&quot;", "\"");
        back = open.cn.awg.pro.core.TextValues.replace(t, "&amp;", "&");
        return back;

    }

    public void update(int a11) {

        String set36 = AppPaths.appPath("settings/fixVersion");

        final String appfixversion = open.cn.awg.pro.core.TextValues.trim(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set36)));

        final int myappversion = a11;

        String set2e = AppPaths.appPath("settings/f10/set2.inf");
        set2e = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2e));
        set2e = urlUnlockString(set2e);

        if (open.cn.awg.pro.core.TextValues.endsWith(set2e, "/")) {

        } else {

            set2e = set2e + "/";

        }
        set2e = set2e + "config.json";

        final String url = set2e;
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                if (yz()) {

                    String setback = "";

                    String version = "";

                    String version2 = "";

                    String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                    if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                        setback = "0";

                    } else {

                        setback = htmlToText(back);

                        if (java.util.Objects.equals(setback, "0")) {

                            setback = "0";

                        } else {

                            version = readJson(setback, "UpdateVersion");
                            version2 = readJson(setback, "FixVersion");
                            setback = String.valueOf(1);

                        }

                    }

                    if (java.util.Objects.equals(setback, "0")) {

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

                            if (java.util.Objects.equals(ek, "应用")) {

                                ek = ek + "和补丁";

                            } else {

                                ek = "补丁";

                            }

                        }

                        if (myappversion < b1 || Double.parseDouble(appfixversion) < b2) {

                            final String eks = ek;
                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (AppUpdateActivity.class));
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
        set2e = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2e));
        set2e = urlUnlockString(set2e) + "/online.php";

        final String url = set2e;
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                String setback = "";

                String version = "";

                String version2 = "";

                String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                } else {

                }

            }

        });

    }

    public boolean isInstallX5() {

        boolean back;

        back = open.cn.awg.pro.core.NativeFiles.exists(context, (String) (AppPaths.appPath("app_tbs/core_share/tbs.conf"))) || open.cn.awg.pro.core.NativeFiles.exists(context, (String) (AppPaths.appPath("app_tbs_64/core_share/tbs.conf")));
        return back;

    }

    public void csh(Activity activity) { new AppRuntimeBridge(activity).csh(); }
    public void NewFilesLoad(Activity activity, Object url, Object roots) { new AppRuntimeBridge(activity).NewFilesLoad(url, roots); }
    public void fileTouch(Activity activity, Object text, Object url) { new AppRuntimeBridge(activity).fileTouch(text, url); }
    public void dkfs(Activity activity, Object z, Object b, int e) { new AppRuntimeBridge(activity).dkfs(z, b, e); }
    public void cdx(Activity activity, int i, String a, String b, String c) { new AppRuntimeBridge(activity).cdx(i, a, b, c); }
    public void sx(Activity activity, String na, String lj) { new AppRuntimeBridge(activity).sx(na, lj); }
    public void tsk(Activity activity, Object a, Object b) { new AppRuntimeBridge(activity).tsk(a, b); }
    public void tsk_QR(Activity activity, Object a, Object b, Object c) { new AppRuntimeBridge(activity).tsk_QR(a, b, c); }
    public void upload_error(Activity activity, Object a, Object b) { new AppRuntimeBridge(activity).upload_error(a, b); }
    public static void reportError(Context owner, Throwable failure, String location) {
        java.io.StringWriter writer = new java.io.StringWriter();
        failure.printStackTrace(new java.io.PrintWriter(writer));
        new AppRuntimeBridge(owner).upload_error(writer.toString(), location);
    }

}
