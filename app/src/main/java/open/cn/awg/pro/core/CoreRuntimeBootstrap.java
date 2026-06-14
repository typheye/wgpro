/**
 ******************************************************************************
 * @file    CoreRuntimeBootstrap.java
 * @author  Typheye
 * @brief   Core runtime bootstrap and notification helper.
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
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

import i.app.iClass;

import org.json.JSONArray;
import org.json.JSONObject;

import open.cn.awg.pro.account.TypheyeServiceBridge;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.chat.ChatRoomActivity;
import open.cn.awg.pro.data.LocalDatabaseBridge;
import open.cn.awg.pro.R;
import open.cn.awg.pro.service.AwgCoreService;
import open.cn.awg.pro.service.SystemAccessibilityService;

@SuppressLint("StaticFieldLeak")
public class CoreRuntimeBootstrap extends iClass {

    public static String set17 = AppPaths.appPath("settings/db/talking.db");

    public static i.runlibrary.app.sj$sjk sjk;

    public static String set2 = AppPaths.appPath("settings/f18/set2.inf");
    public static Context context;
    public static NotificationManager notificationManager;
    public static String channelId = "chat";
    public static int msgids = 1;
    public static i.runlibrary.app.st$xfc xfc = null;
    public final CoreRuntimeBootstrap lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public LocalDatabaseBridge e11 = new LocalDatabaseBridge(_APPINFO);
    public TypheyeServiceBridge easy = new TypheyeServiceBridge(_APPINFO);
    public DevicePolicyBridge carton = new DevicePolicyBridge(_APPINFO);
    public boolean state_remotelock = false;
    public boolean state_ltstz = false;

    public CoreRuntimeBootstrap(i.runlibrary.app.AppInfo _APPINFO) {
        super(_APPINFO);

    }

    public void upload_log(Object type_, Object log_) {

        final Object type = type_;

        final Object log = log_;
        gj.xc(new Thread() {

            public void run() {

                String urls = "";

                final String set2 = AppPaths.appPath("settings/f11/set2.inf");
                urls = e1.urlUnlockString(wj.dqwb(set2));

                if (zf.cjw(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                String ljf = wj.dqwb(AppPaths.appPath("data/assets/a17"));

                String idget = urls + "class/api.php?type=release" + ljf + "id=405";

                if (zf.dy(log, "")) {

                } else {

                    String namex0 = "日志上报";

                    final String namex = namex0;

                    String xxk = "[日志类型]\n" + type + "\n[日志信息]\n" + log;

                    final String xx = xxk;

                    final String url = idget;

                    String setback = "";

                    String[] wlhq1 = new String[]{"name=" + namex, "value=" + xx, "code=baseTextUpload"};

                    String back = wl.hq(url, wlhq1, "utf-8", null, true, null, 20000, 20000, null);

                    if (zf.dy(back, "") || zf.dy(back, null)) {

                        setback = "0";

                    } else {

                        if (zf.dy(setback, "0")) {

                            setback = "0";

                        } else {

                            setback = back;

                        }

                    }

                    if (zf.dy(setback, "0")) {

                    } else {

                        if (zf.dy(setback, "true")) {

                        } else {

                        }

                    }

                }

            }

        });

    }

    public void bhxfc() {

        final String set1 = AppPaths.appPath("settings/other/0xK9HJ4/Enable");

        if (zf.dy(wj.dqwb(set1), "true") && e1.islogin()) {

            gj.jmxc(new Runnable() {

                public void run() {

                    i.runlibrary.app.v.xxbj xxbj = st.xxbj();
                    xxbj.kg(-2, -2);
                    xxbj.fx(1);

                    i.runlibrary.app.v.wb wb = st.wb();
                    wb.kg(1, 1);
                    wb.zf("");
                    xxbj.j(wb);

                    if (zf.dy(xfc, null)) {

                        xfc = (i.runlibrary.app.st$xfc) (Object) st.xfc(xxbj, 1, 1, 0, 0);

                    }

                }

            });

        }

    }

    public void remotelock() {

        final String set1 = AppPaths.appPath("settings/other/0xK9HJ4/Enable");

        final String user = AppPaths.appPath("settings/account/user");

        final String urls = "https://service.typheye.cn/app/com.typheye.awg.remote/command/";

        if (!state_remotelock) {

            state_remotelock = true;
            gj.xc(new Thread() {

                public void run() {

                    bhxfc();

                    String ljf = wj.dqwb(AppPaths.appPath("data/assets/a17"));

                    String url = urls + wj.dqwb(user) + "/running.dat";

                    String back = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                    if (zf.dy(back, "cmdv1:lock")) {

                        carton.lockNow(context);

                    }
                    state_remotelock = false;

                }

            });

        }

    }

    public void downAssets() {

        String urls = AppPaths.appPath("settings/f10/set2.inf");
        urls = wj.dqwb(urls);
        urls = e1.urlUnlockString(urls);

        if (zf.cjw(urls, "/")) {

        } else {

            urls = urls + "/";

        }

        final String url = urls + "download/assets";

        final String filename = AppPaths.appPath("data/down/assets");

        final String filename2 = "@resource/a9d2b8288117333d815efe8e2feb5862";

        final String assetspath = AppPaths.appPath("data/assets/");
        gj.xc(new Thread() {

            public void run() {

                int st5 = wl.xz(url, filename, true, null, "utf-8", "", true, "User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept-language=zh-CN");

                if (st5 == 0 || st5 == 1) {

                    if (wj.jy(filename, assetspath, true) > 0) {

                    } else {

                    }

                } else {

                    wj.fz(filename2, filename, true);

                    if (wj.jy(filename, assetspath, true) > 0) {

                    } else {

                    }

                    if (!wj.cz(filename)) {

                        e1.tsk("提示", "应用资源下载失败，可能导致应用部分内容显示异常！\n请在联网后，重启腕管Pro，我们将重新为您下载应用资源。");

                    } else {

                    }

                }

            }

        });

    }

    public void run() {

        if (e1.getNowUserId() == 0) {

            final String set1 = AppPaths.appPath("settings/other/0xK9HJ4/Enable");

            final String user = AppPaths.appPath("settings/account/user");

            final String urls = "https://service.typheye.cn/app/com.typheye.awg.remote/command/";
            context = AwgProApplication.getContext();
            wj.sc(AppPaths.appPath("data/ltlb/ftz"));

            i.runlibrary.app.sj$zh sjl = (i.runlibrary.app.sj$zh) (Object) sj.zh(wj.dqwb(set2));

            final long tim = sjl.zlong(3000);
            N_csh();

            final AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
            gj.xc(new Thread() {

                public void run() {

                    e11.setup_talklist();
                    sjk = (i.runlibrary.app.sj$sjk) (Object) sj.sjk(set17);

                    if (!zf.dy(wj.dqwb(AppPaths.appPath("settings/a3.inf")), "0") && zf.dy(wj.dqwb(AppPaths.appPath("settings/f11/set1.inf")), "true")) {

                        ltstz();

                    }
                    wj.xrwb(AppPaths.appPath("data/AwgCoreService.r"), "0");

                    while (wj.cz(AppPaths.appPath("data/AwgCoreService.r"))) {

                        gj.zt(tim);

                        if (e1.awgwl_state() && !zf.dy(wj.dqwb(AppPaths.appPath("settings/a3.inf")), "0") && zf.dy(wj.dqwb(AppPaths.appPath("settings/f11/set1.inf")), "true") && !wj.cz(AppPaths.appPath("data/ltlb/ftz"))) {

                            ltstz();

                        }

                        boolean aa;

                        try {

                            aa = SystemAccessibilityService.isAccessibilitySettingsOn(context, SystemAccessibilityService.class.getName());

                        } catch (Throwable e) {

                            aa = false;

                        }

                        if (e1.islogin() && zf.dy(wj.dqwb(set1), "true") && carton.state(context) && aa) {

                            remotelock();

                        } else {

                            wj.xrwb(set1, "false");

                        }

                        if (e1.islogin()) {

                            easy.getDataUpdate();
                            easy.updateUserData();

                        }
                        e1.online();

                    }

                }

            });

        }

    }

    public void N_cancel() {

        try {

            notificationManager.cancel(1);

        } catch (Throwable __$_e__) {

        }

    }

    public void N_csh() {

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        String channelName = "腕上微聊";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);

            channel.setImportance(NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

    }

    public void N_wl(String a, String b, String c) {

        final String cachehd = AppPaths.appPath("data/ltlb/lbx_id");
        wj.xrwb(cachehd, c);

        final String cachehd2 = AppPaths.appPath("data/ltlb/lbx_title");
        wj.xrwb(cachehd2, a);

        Intent intent = new Intent(context, ChatRoomActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("uid", c);
        intent.putExtras(bundle);
        Notification notification = new NotificationCompat.Builder(context, channelId)
                .setAutoCancel(true)
                .setContentTitle(a)
                .setContentText(b)
                .setPriority(Notification.PRIORITY_MAX)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.a37)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.c1))
                .setContentIntent(PendingIntent.getActivity(context, 0, intent, 0))

                .build();
        notificationManager.notify(msgids, notification);
        msgids++;

    }

    public void ltstz() {

        final String his = AppPaths.appPath("data/ltlb/history");

        if (!wj.cz(AppPaths.appPath("data/ltlb/showing"))) {

            if (!state_ltstz) {

                state_ltstz = true;
                gj.xc(new Thread() {

                    public void run() {

                        wj.xrwb(AppPaths.appPath("data/ltlb/ftz"), "0");

                        final String set2 = AppPaths.appPath("settings/f11/set2.inf");

                        String urls = e1.urlUnlockString(wj.dqwb(set2));

                        String table = "_id,uid,name,utext";

                        Object sqlx = null;

                        i.runlibrary.app.sj$cxsj sjk15 = (i.runlibrary.app.sj$cxsj) (Object) sjk.cxsj("talklist", table, sqlx);

                        while (sjk15.xyh()) {

                            final String rooms = sjk15.sj(2);

                            final String uids = sjk15.sj(1);

                            String sjhc = AppPaths.appPath("data/ltlb/chat/") + uids;

                            if (zf.cjw(urls, "/")) {

                            } else {

                                urls = urls + "/";

                            }

                            String db = "";

                            if (wj.cz(sjhc)) {

                                db = String.valueOf(wj.dx(sjhc));

                                String ljf = wj.dqwb(AppPaths.appPath("data/assets/a17"));

                                String url = urls + "re.php?uid=" + uids + ljf + "token=" + db;

                                String back = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                                if (zf.dy(back, "") || zf.dy(back, null)) {

                                } else {

                                    String be = wj.dqwb(his);

                                    String pp = "[" + uids + "]:";

                                    String pp1 = "[" + uids + "]";

                                    if (zf.dy(back, "true")) {

                                        String pp2 = pp + db + pp1;

                                        if (zf.cz(be, pp)) {

                                            String db2 = zf.qc(wj.dqwb(his), pp, pp1);

                                            if (!zf.dy(db2, db) && update(uids)) {

                                                N_wl(rooms, getdata(uids), uids);

                                                String pp3 = pp + db2 + pp1;
                                                be = zf.th(be, pp3, pp2);
                                                wj.xrwb(his, be);

                                            } else {

                                            }

                                        } else {

                                            if (update(uids)) {

                                                N_wl(rooms, getdata(uids), uids);
                                                pp2 = "\n" + pp2;
                                                wj.xrwb(his, pp2);

                                            } else {

                                            }

                                        }

                                    } else if (zf.dy(back, "error")) {

                                        String pp2 = pp + "error" + pp1;

                                        if (zf.cz(be, pp)) {

                                            String db2 = zf.qc(wj.dqwb(his), pp, pp1);

                                            if (!zf.dy(db2, "error")) {

                                                N_wl(rooms, "该房间不存在或已被删除", uids);

                                                String pp3 = pp + db2 + pp1;
                                                be = zf.th(be, pp3, pp2);
                                                wj.xrwb(his, be);

                                            } else {

                                            }

                                        } else {

                                            N_wl(rooms, "该房间不存在或已被删除", uids);
                                            pp2 = "\n" + pp2;
                                            wj.xrwb(his, pp2);

                                        }

                                    }

                                }

                            }

                        }
                        wj.sc(AppPaths.appPath("data/ltlb/ftz"));
                        state_ltstz = false;

                    }

                });

            }

        }

    }

    public boolean update(Object uid) {

        boolean isF = false;

        try {

            if (!wj.cz(AppPaths.appPath("data/ltlb/showing"))) {

                final String set2 = AppPaths.appPath("settings/f11/set2.inf");

                String urls = e1.urlUnlockString(wj.dqwb(set2));

                String sjhc = AppPaths.appPath("data/ltlb/chat/") + uid;

                if (zf.cjw(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                String url = urls + "chat/" + uid + ".json";

                String back0 = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                if (!zf.dy(back0, "") || !zf.dy(back0, null)) {

                    wj.xrwb(sjhc, back0);
                    isF = true;

                }

            }

        } catch (Throwable __$_e__) {

        }
        return isF;

    }

    public String getdata(Object uid) {

        String back = "";

        String sjhc = AppPaths.appPath("data/ltlb/chat/") + uid;
        back = wj.dqwb(sjhc);

        try {

            i.runlibrary.app.zf$json jo = (i.runlibrary.app.zf$json) (Object) zf.json(back);

            JSONObject json = jo.json;

            JSONArray list = jo.dxlb(json, "data");

            int size = jo.cd(list);
            size = size - 1;

            JSONObject dx = jo.dx(list, size);

            Object data = jo.hq(dx, "data");

            Object name = jo.hq(dx, "name");

            String jname = "";

            String jdata = "";
            jname = name.toString();
            jdata = data.toString();

            if (zf.cz(jdata, "[PHOTO]") && zf.cz(jdata, "[P:END]")) {

                String tx = zf.qc(jdata, null, "[PHOTO]");
                jdata = tx + "[附件]图片";

            } else if (zf.cz(jdata, "[AUDIO]") && zf.cz(jdata, "[A:END]")) {

                String tx = zf.qc(jdata, null, "[AUDIO]");
                jdata = tx + "[附件]音频";

            } else if (zf.cz(jdata, "[VIDEO]") && zf.cz(jdata, "[V:END]")) {

                String tx = zf.qc(jdata, null, "[VIDEO]");
                jdata = tx + "[附件]视频";

            } else if (zf.cz(jdata, "[OTHER]") && zf.cz(jdata, "[O:END]")) {

                String tx = zf.qc(jdata, null, "[OTHER]");
                jdata = tx + "[附件]文件";

            } else if (zf.cz(jdata, "[INFOS]") && zf.cz(jdata, "[I:END]")) {

                String tx = zf.qc(jdata, "[INFOS]", "[I:END]");
                jdata = tx;

            }
            back = jname + ":" + jdata;

        } catch (Throwable e) {

            back = "您有新的消息";

        }
        return back;

    }
}
