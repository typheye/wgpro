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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;



import org.json.JSONArray;
import org.json.JSONObject;

import open.cn.awg.pro.account.TypheyeServiceBridge;

import open.cn.awg.pro.chat.ChatRoomActivity;
import open.cn.awg.pro.data.LocalDatabaseBridge;
import open.cn.awg.pro.R;
import open.cn.awg.pro.service.SystemAccessibilityService;
import open.cn.awg.pro.core.NativeArchives;
import open.cn.awg.pro.core.NativeFiles;
import open.cn.awg.pro.core.NativeHttp;

@SuppressLint("StaticFieldLeak")
public class CoreRuntimeBootstrap {

    public static String set17 = AppPaths.appPath("settings/db/talking.db");

    public static SQLiteDatabase sjk;

    public static String set2 = AppPaths.appPath("settings/f18/set2.inf");
    public static Context context;
    public static NotificationManager notificationManager;
    public static String channelId = "chat";
    public static int msgids = 1;
    private View crosshairView;
    public final CoreRuntimeBootstrap lei = this, 类 = this;
    private final Context applicationContext;
    public final AppRuntimeBridge e1;
    public final LocalDatabaseBridge e11;
    public final TypheyeServiceBridge easy;
    public final DevicePolicyBridge carton;
    public boolean state_remotelock = false;
    public boolean state_ltstz = false;

    public CoreRuntimeBootstrap(Context context) {
        Context owner = context == null ? null : context.getApplicationContext();
        applicationContext = owner == null ? context : owner;
        CoreRuntimeBootstrap.context = applicationContext;
        e1 = new AppRuntimeBridge(applicationContext);
        e11 = new LocalDatabaseBridge(applicationContext);
        easy = new TypheyeServiceBridge(applicationContext);
        carton = new DevicePolicyBridge(applicationContext);
    }

    public void upload_log(Object type_, Object log_) {

        final Object type = type_;

        final Object log = log_;
        startBackground(new Thread() {

            public void run() {

                String urls = "";

                final String set2 = AppPaths.appPath("settings/f11/set2.inf");
                urls = e1.urlUnlockString(NativeFiles.readText(applicationContext, (String) (set2)));

                if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                String ljf = NativeFiles.readText(applicationContext, (String) (AppPaths.appPath("data/assets/a17")));

                String idget = urls + "class/api.php?type=release" + ljf + "id=405";

                if (java.util.Objects.equals(log, "")) {

                } else {

                    String namex0 = "日志上报";

                    final String namex = namex0;

                    String xxk = "[日志类型]\n" + type + "\n[日志信息]\n" + log;

                    final String xx = xxk;

                    final String url = idget;

                    String setback = "";

                    String[] wlhq1 = new String[]{"name=" + namex, "value=" + xx, "code=baseTextUpload"};

                    String back = NativeHttp.postForm((String) (url), (String[]) (wlhq1), (String) ("utf-8"), (String) (null), Boolean.TRUE.equals(true), NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, 0), open.cn.awg.pro.core.TextValues.intValue(20000, 0), (String) (null)).bodyForHttp200();

                    if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                        setback = "0";

                    } else {

                        if (java.util.Objects.equals(setback, "0")) {

                            setback = "0";

                        } else {

                            setback = back;

                        }

                    }

                    if (java.util.Objects.equals(setback, "0")) {

                    } else {

                        if (java.util.Objects.equals(setback, "true")) {

                        } else {

                        }

                    }

                }

            }

        });

    }

        public void bhxfc() {
        String enabled = NativeFiles.readText(applicationContext,
                AppPaths.appPath("settings/other/0xK9HJ4/Enable"));
        if (!java.util.Objects.equals(enabled, "true") || !e1.islogin()) {
            return;
        }
        runOnMain(() -> {
            if (crosshairView != null) {
                return;
            }
            WindowManager manager = (WindowManager) applicationContext.getSystemService(Context.WINDOW_SERVICE);
            LinearLayout container = new LinearLayout(applicationContext);
            container.setOrientation(LinearLayout.VERTICAL);
            TextView marker = new TextView(applicationContext);
            marker.setText("");
            container.addView(marker, new LinearLayout.LayoutParams(1, 1));
            int type = Build.VERSION.SDK_INT >= 26 ? 2038
                    : Build.VERSION.SDK_INT < 25 && Build.VERSION.SDK_INT >= 23 ? 2005 : 2003;
            WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    type,
                    24,
                    PixelFormat.RGBA_8888);
            params.gravity = Gravity.TOP | Gravity.LEFT;
            try {
                manager.addView(container, params);
                crosshairView = container;
            } catch (Exception ignored) {
            }
        });
    }


    public void remotelock() {

        final String set1 = AppPaths.appPath("settings/other/0xK9HJ4/Enable");

        final String user = AppPaths.appPath("settings/account/user");

        final String urls = "https://service.typheye.cn/app/com.typheye.awg.remote/command/";

        if (!state_remotelock) {

            state_remotelock = true;
            startBackground(new Thread() {

                public void run() {

                    bhxfc();

                    String ljf = NativeFiles.readText(applicationContext, (String) (AppPaths.appPath("data/assets/a17")));

                    String url = urls + NativeFiles.readText(applicationContext, (String) (user)) + "/running.dat";

                    String back = NativeHttp.postForm((String) (url), (String[]) (null), (String) ("utf-8"), (String) (null), Boolean.TRUE.equals(true), NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, 0), open.cn.awg.pro.core.TextValues.intValue(20000, 0), (String) (null)).bodyForHttp200();

                    if (java.util.Objects.equals(back, "cmdv1:lock")) {

                        carton.lockNow(context);

                    }
                    state_remotelock = false;

                }

            });

        }

    }

    public void downAssets() {

        String urls = AppPaths.appPath("settings/f10/set2.inf");
        urls = NativeFiles.readText(applicationContext, (String) (urls));
        urls = e1.urlUnlockString(urls);

        if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

        } else {

            urls = urls + "/";

        }

        final String url = urls + "download/assets";

        final String filename = AppPaths.appPath("data/down/assets");

        final String filename2 = "@resource/a9d2b8288117333d815efe8e2feb5862";

        final String assetspath = AppPaths.appPath("data/assets/");
        startBackground(new Thread() {

            public void run() {

                int st5 = NativeHttp.download(applicationContext, (String) (url), (String) (filename), Boolean.TRUE.equals(true), (String[]) (null), (String) ("utf-8"), (String) (""), Boolean.TRUE.equals(true), (String) ("User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept-language=zh-CN"), 0, 0, null);

                if (st5 == 0 || st5 == 1) {

                    if (NativeArchives.extract(applicationContext, (String) (filename), (String) (assetspath), Boolean.TRUE.equals(true)) > 0) {

                    } else {

                    }

                } else {

                    NativeFiles.copy(applicationContext, (String) (filename2), (String) (filename), Boolean.TRUE.equals(true));

                    if (NativeArchives.extract(applicationContext, (String) (filename), (String) (assetspath), Boolean.TRUE.equals(true)) > 0) {

                    } else {

                    }

                    if (!NativeFiles.exists(applicationContext, (String) (filename))) {

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
            context = applicationContext;
            NativeFiles.delete(applicationContext, (String) (AppPaths.appPath("data/ltlb/ftz")), false);

            Object sjl = (Object) NativeFiles.readText(applicationContext, (String) (set2));

            final long tim = open.cn.awg.pro.core.TextValues.longValue(sjl, 3000);
            N_csh();

            final AppRuntimeBridge e1 = this.e1;
            startBackground(new Thread() {

                public void run() {

                    e11.setup_talklist();
                    sjk = e11.getTalkDatabase();

                    if (!java.util.Objects.equals(NativeFiles.readText(applicationContext, (String) (AppPaths.appPath("settings/a3.inf"))), "0") && java.util.Objects.equals(NativeFiles.readText(applicationContext, (String) (AppPaths.appPath("settings/f11/set1.inf"))), "true")) {

                        ltstz();

                    }
                    NativeFiles.writeText(applicationContext, (String) (AppPaths.appPath("data/AwgCoreService.r")), (String) ("0"));

                    while (NativeFiles.exists(applicationContext, (String) (AppPaths.appPath("data/AwgCoreService.r")))) {

                        pause(tim);

                        if (e1.awgwl_state() && !java.util.Objects.equals(NativeFiles.readText(applicationContext, (String) (AppPaths.appPath("settings/a3.inf"))), "0") && java.util.Objects.equals(NativeFiles.readText(applicationContext, (String) (AppPaths.appPath("settings/f11/set1.inf"))), "true") && !NativeFiles.exists(applicationContext, (String) (AppPaths.appPath("data/ltlb/ftz")))) {

                            ltstz();

                        }

                        boolean aa;

                        try {

                            aa = SystemAccessibilityService.isAccessibilitySettingsOn(context, SystemAccessibilityService.class.getName());

                        } catch (Throwable e) {

                            aa = false;

                        }

                        if (e1.islogin() && java.util.Objects.equals(NativeFiles.readText(applicationContext, (String) (set1)), "true") && carton.state(context) && aa) {

                            remotelock();

                        } else {

                            NativeFiles.writeText(applicationContext, (String) (set1), (String) ("false"));

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
        NativeFiles.writeText(applicationContext, (String) (cachehd), (String) (c));

        final String cachehd2 = AppPaths.appPath("data/ltlb/lbx_title");
        NativeFiles.writeText(applicationContext, (String) (cachehd2), (String) (a));

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
                .setContentIntent(PendingIntent.getActivity(
                        context,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE))

                .build();
        notificationManager.notify(msgids, notification);
        msgids++;

    }

    public void ltstz() {

        final String his = AppPaths.appPath("data/ltlb/history");

        if (!NativeFiles.exists(applicationContext, (String) (AppPaths.appPath("data/ltlb/showing")))) {

            if (!state_ltstz) {

                state_ltstz = true;
                startBackground(new Thread() {

                    public void run() {

                        NativeFiles.writeText(applicationContext, (String) (AppPaths.appPath("data/ltlb/ftz")), (String) ("0"));

                        final String set2 = AppPaths.appPath("settings/f11/set2.inf");

                        String urls = e1.urlUnlockString(NativeFiles.readText(applicationContext, (String) (set2)));

                        try (Cursor sjk15 = sjk.query("talklist",
                                new String[]{"_id", "uid", "name", "utext"}, null, null, null, null, null)) {
                        while (sjk15.moveToNext()) {

                            final String rooms = sjk15.getString(2);

                            final String uids = sjk15.getString(1);

                            String sjhc = AppPaths.appPath("data/ltlb/chat/") + uids;

                            if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

                            } else {

                                urls = urls + "/";

                            }

                            String db = "";

                            if (NativeFiles.exists(applicationContext, (String) (sjhc))) {

                                db = String.valueOf(NativeFiles.size(applicationContext, (String) (sjhc)));

                                String ljf = NativeFiles.readText(applicationContext, (String) (AppPaths.appPath("data/assets/a17")));

                                String url = urls + "re.php?uid=" + uids + ljf + "token=" + db;

                                String back = NativeHttp.postForm((String) (url), (String[]) (null), (String) ("utf-8"), (String) (null), Boolean.TRUE.equals(true), NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, 0), open.cn.awg.pro.core.TextValues.intValue(20000, 0), (String) (null)).bodyForHttp200();

                                if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                                } else {

                                    String be = NativeFiles.readText(applicationContext, (String) (his));

                                    String pp = "[" + uids + "]:";

                                    String pp1 = "[" + uids + "]";

                                    if (java.util.Objects.equals(back, "true")) {

                                        String pp2 = pp + db + pp1;

                                        if (open.cn.awg.pro.core.TextValues.contains(be, pp)) {

                                            String db2 = open.cn.awg.pro.core.TextValues.slice(NativeFiles.readText(applicationContext, (String) (his)), pp, pp1);

                                            if (!java.util.Objects.equals(db2, db) && update(uids)) {

                                                N_wl(rooms, getdata(uids), uids);

                                                String pp3 = pp + db2 + pp1;
                                                be = open.cn.awg.pro.core.TextValues.replace(be, pp3, pp2);
                                                NativeFiles.writeText(applicationContext, (String) (his), (String) (be));

                                            } else {

                                            }

                                        } else {

                                            if (update(uids)) {

                                                N_wl(rooms, getdata(uids), uids);
                                                pp2 = "\n" + pp2;
                                                NativeFiles.writeText(applicationContext, (String) (his), (String) (pp2));

                                            } else {

                                            }

                                        }

                                    } else if (java.util.Objects.equals(back, "error")) {

                                        String pp2 = pp + "error" + pp1;

                                        if (open.cn.awg.pro.core.TextValues.contains(be, pp)) {

                                            String db2 = open.cn.awg.pro.core.TextValues.slice(NativeFiles.readText(applicationContext, (String) (his)), pp, pp1);

                                            if (!java.util.Objects.equals(db2, "error")) {

                                                N_wl(rooms, "该房间不存在或已被删除", uids);

                                                String pp3 = pp + db2 + pp1;
                                                be = open.cn.awg.pro.core.TextValues.replace(be, pp3, pp2);
                                                NativeFiles.writeText(applicationContext, (String) (his), (String) (be));

                                            } else {

                                            }

                                        } else {

                                            N_wl(rooms, "该房间不存在或已被删除", uids);
                                            pp2 = "\n" + pp2;
                                            NativeFiles.writeText(applicationContext, (String) (his), (String) (pp2));

                                        }

                                    }

                                }

                            }

                        }
                        }
                        NativeFiles.delete(applicationContext, (String) (AppPaths.appPath("data/ltlb/ftz")), false);
                        state_ltstz = false;

                    }

                });

            }

        }

    }

    public boolean update(Object uid) {

        boolean isF = false;

        try {

            if (!NativeFiles.exists(applicationContext, (String) (AppPaths.appPath("data/ltlb/showing")))) {

                final String set2 = AppPaths.appPath("settings/f11/set2.inf");

                String urls = e1.urlUnlockString(NativeFiles.readText(applicationContext, (String) (set2)));

                String sjhc = AppPaths.appPath("data/ltlb/chat/") + uid;

                if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                String url = urls + "chat/" + uid + ".json";

                String back0 = NativeHttp.postForm((String) (url), (String[]) (null), (String) ("utf-8"), (String) (null), Boolean.TRUE.equals(true), NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, 0), open.cn.awg.pro.core.TextValues.intValue(20000, 0), (String) (null)).bodyForHttp200();

                if (!java.util.Objects.equals(back0, "") || !java.util.Objects.equals(back0, null)) {

                    NativeFiles.writeText(applicationContext, (String) (sjhc), (String) (back0));
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
        back = NativeFiles.readText(applicationContext, (String) (sjhc));

        try {

            JSONObject json = new JSONObject(back);

            JSONArray list = json.optJSONArray("data");

            int size = list.length();
            size = size - 1;

            JSONObject dx = list.getJSONObject(size);

            Object data = dx.get("data");

            Object name = dx.get("name");

            String jname = "";

            String jdata = "";
            jname = name.toString();
            jdata = data.toString();

            if (open.cn.awg.pro.core.TextValues.contains(jdata, "[PHOTO]") && open.cn.awg.pro.core.TextValues.contains(jdata, "[P:END]")) {

                String tx = open.cn.awg.pro.core.TextValues.slice(jdata, null, "[PHOTO]");
                jdata = tx + "[附件]图片";

            } else if (open.cn.awg.pro.core.TextValues.contains(jdata, "[AUDIO]") && open.cn.awg.pro.core.TextValues.contains(jdata, "[A:END]")) {

                String tx = open.cn.awg.pro.core.TextValues.slice(jdata, null, "[AUDIO]");
                jdata = tx + "[附件]音频";

            } else if (open.cn.awg.pro.core.TextValues.contains(jdata, "[VIDEO]") && open.cn.awg.pro.core.TextValues.contains(jdata, "[V:END]")) {

                String tx = open.cn.awg.pro.core.TextValues.slice(jdata, null, "[VIDEO]");
                jdata = tx + "[附件]视频";

            } else if (open.cn.awg.pro.core.TextValues.contains(jdata, "[OTHER]") && open.cn.awg.pro.core.TextValues.contains(jdata, "[O:END]")) {

                String tx = open.cn.awg.pro.core.TextValues.slice(jdata, null, "[OTHER]");
                jdata = tx + "[附件]文件";

            } else if (open.cn.awg.pro.core.TextValues.contains(jdata, "[INFOS]") && open.cn.awg.pro.core.TextValues.contains(jdata, "[I:END]")) {

                String tx = open.cn.awg.pro.core.TextValues.slice(jdata, "[INFOS]", "[I:END]");
                jdata = tx;

            }
            back = jname + ":" + jdata;

        } catch (Throwable e) {

            back = "您有新的消息";

        }
        return back;

    }


    private static void startBackground(Thread thread) {
        thread.setName(String.format("I_APP_Thread_%d", thread.getId()));
        thread.start();
    }

    private void runOnMain(Runnable action) {
        if (android.os.Looper.myLooper() == android.os.Looper.getMainLooper()) {
            action.run();
        } else {
            new android.os.Handler(android.os.Looper.getMainLooper()).post(action);
        }
    }

    private static void pause(Object millis) {
        try {
            Thread.sleep(Long.parseLong(String.valueOf(millis)));
        } catch (Exception ignored) {
        }
    }

}
