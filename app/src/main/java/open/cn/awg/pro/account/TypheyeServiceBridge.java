/**
 ******************************************************************************
 * @file    TypheyeServiceBridge.java
 * @author  Typheye
 * @brief   Wraps Typheye account and service network calls.
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
package open.cn.awg.pro.account;



import org.json.JSONObject;



import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.NativeFiles;
import open.cn.awg.pro.core.NativeHttp;
import open.cn.awg.pro.core.AppRuntimeBridge;

public class TypheyeServiceBridge {

    public static boolean state_get_data_update = false;
    public static boolean state_update_user_data = false;
    public static boolean state_get_pericon = false;
    public final TypheyeServiceBridge lei = this, 类 = this;
    public String url = "https://service.typheye.cn";
    public String uidxml = AppPaths.appPath("settings/account/user");
    public String cookiexml = AppPaths.appPath("data/easy/cookie");
    private final Context applicationContext;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    public final AppRuntimeBridge e1;

    public TypheyeServiceBridge(Context context) {
        Context owner = context == null ? null : context.getApplicationContext();
        applicationContext = owner == null ? context : owner;
        e1 = new AppRuntimeBridge(applicationContext);
    }

    public String getPerIcon(Object uid) {

        String url = getPerIconPath(open.cn.awg.pro.core.TextValues.string(uid));

        if (NativeFiles.exists(applicationContext, (String) (url))) {

            return url;

        } else {

            return "null";

        }

    }

    public String getPerIconPath(Object uid) {

        String url = NativeFiles.resolvePath(applicationContext, (String) (AppPaths.appPath("data/user/src/pericon/") + open.cn.awg.pro.core.TextValues.string(uid)));
        return url;

    }

    public void getPericon(Object a) {

        if (!state_get_pericon) {

            state_get_pericon = true;
            a = open.cn.awg.pro.core.TextValues.trim(a);

            final String uid = open.cn.awg.pro.core.TextValues.string(a);
            startBackground(new Thread() {

                public void run() {

                    getPericon$1(uid);

                }

            });

        } else {

            runOnMain(new Runnable() {

                public void run() {

                }

            });

        }

    }

    public void getPericon$1(String uid) {

        String send = "/src/pericon/" + uid;

        int back = download((String) (url + send), (String) (getPerIconPath(uid)), Boolean.TRUE.equals(true));

        if (back == -1) {

            getPericon$error();

        } else {

        }
        state_get_pericon = false;

    }

    public void getPericon$error() {
        runOnMain(new Runnable() {

            public void run() {

            }

        });

    }

    public void getDataUpdate() {

        if (!state_get_data_update) {

            state_get_data_update = true;

            String a = NativeFiles.readText(applicationContext, (String) (uidxml));

            String b = NativeFiles.readText(applicationContext, (String) (cookiexml));

            String sjzx = String.valueOf(System.currentTimeMillis());

            final String[] post = new String[]{"v1=", "v2=", "v3="};

            String send0 = "type=get_data_update&uid=" + a + "&cookie=" + b + "&time=" + sjzx;

            final String send = "/api.php?" + send0 + "&token=" + open.cn.awg.pro.core.TextValues.md5(send0);
            startBackground(new Thread() {

                public void run() {

                    String j_code = "";

                    String j_msg = "";

                    int j_info_v0 = 0;

                    int j_info_v1 = 0;

                    int j_info_v2 = 0;

                    int j_info_v3 = 0;

                    String back = request((String) (url + send), (String[]) (post), (String) ("utf-8"));

                    if (java.util.Objects.equals(back, "null") || java.util.Objects.equals(back, null) || java.util.Objects.equals(back, "")) {

                        getDataUpdate$error();

                    } else {

                        try {

                            JSONObject json = new JSONObject(back);
                            j_code = json.optString("code");
                            j_msg = json.optString("msg");
                            try {
                                JSONObject info = json.optJSONObject("info");
                                j_info_v0 = info.optInt("v0");
                                j_info_v1 = info.optInt("v1");
                                j_info_v2 = info.optInt("v2");
                                j_info_v3 = info.optInt("v3");
                            } catch (Exception e) {

                            }

                            if (java.util.Objects.equals(j_code, "200")) {

                                if (j_info_v0 == 1) {

                                    getLoginError();

                                } else {

                                }

                            } else {

                            }

                        } catch (Throwable __$_e__) {

                            getDataUpdate$error();

                        }

                    }
                    state_get_data_update = false;

                }

            });

        } else {

            runOnMain(new Runnable() {

                public void run() {

                }

            });

        }

    }

    public void getDataUpdate$error() {

    }

    public void updateUserData() {

        if (!state_update_user_data) {

            state_update_user_data = true;

            final String set2 = AppPaths.appPath("settings/n1/set2.inf");

            final String set3 = AppPaths.appPath("settings/n1/set3.inf");

            final String set2s = AppPaths.appPath("settings/f11/set3.inf");

            String a = NativeFiles.readText(applicationContext, (String) (uidxml));

            String b = NativeFiles.readText(applicationContext, (String) (cookiexml));

            final String uid_ = a;

            String sjzx = String.valueOf(System.currentTimeMillis());

            String send0 = "type=get_user_data&uid=" + a + "&cookie=" + b + "&time=" + sjzx;

            final String send = "/api.php?" + send0 + "&token=" + open.cn.awg.pro.core.TextValues.md5(send0);
            startBackground(new Thread() {

                public void run() {

                    String j_code = "";

                    String j_msg = "";

                    String j_info_shuo = "";

                    String j_info_nick = "";

                    String back = request((String) (url + send), (String[]) (null), (String) ("utf-8"));

                    if (java.util.Objects.equals(back, "null") || java.util.Objects.equals(back, null) || java.util.Objects.equals(back, "")) {

                        updateUserData$error();

                    } else {

                        try {

                            JSONObject json = new JSONObject(back);
                            j_code = json.optString("code");
                            j_msg = json.optString("msg");
                            try {
                                JSONObject info = json.optJSONObject("info");
                                j_info_shuo = info.optString("shuo");
                                j_info_nick = info.optString("nick");
                            } catch (Exception e) {

                            }

                            if (java.util.Objects.equals(j_code, "200")) {

                                getPericon(uid_);
                                NativeFiles.writeText(applicationContext, (String) (set2), (String) (j_info_nick));
                                NativeFiles.writeText(applicationContext, (String) (set2s), (String) (j_info_nick));
                                NativeFiles.writeText(applicationContext, (String) (set3), (String) (j_info_shuo));

                            } else {

                            }

                        } catch (Throwable __$_e__) {

                            updateUserData$error();

                        }

                    }
                    state_update_user_data = false;

                }

            });

        } else {

            runOnMain(new Runnable() {

                public void run() {

                }

            });

        }

    }

    public void updateUserData$error() {

    }

    public void getLoginError() {

        if (e1.islogin()) {

            String a = AppPaths.appPath("settings/account/user");

            String b = AppPaths.appPath("settings/account/pass");

            String c = AppPaths.appPath("settings/account/userx");

            String d = AppPaths.appPath("settings/account/passx");
            NativeFiles.delete(applicationContext, (String) (a), false);
            NativeFiles.delete(applicationContext, (String) (b), false);
            NativeFiles.delete(applicationContext, (String) (c), false);
            NativeFiles.delete(applicationContext, (String) (d), false);
            runOnMain(new Runnable() {

                public void run() {

                    startActivity(TypheyeLoginActivity.class);

                }

            });
            e1.tsk("提示", "您的账户异常，已退出登录");

        }

    }

    public boolean register$request(Object a, Object b, Object c, Object d) {
        a = open.cn.awg.pro.core.TextValues.trim(a);
        b = open.cn.awg.pro.core.TextValues.trim(b);
        c = open.cn.awg.pro.core.TextValues.trim(c);
        d = open.cn.awg.pro.core.TextValues.trim(d);

        final Object username = a;

        final Object regtoken = b;

        final Object password = c;

        final Object password2 = d;

        String sjzx = String.valueOf(System.currentTimeMillis());

        String send0 = "type=register&username=" + open.cn.awg.pro.core.TextValues.string(a) + "&regtoken=" + open.cn.awg.pro.core.TextValues.string(b) + "&password=" + open.cn.awg.pro.core.TextValues.string(c) + "&password2=" + open.cn.awg.pro.core.TextValues.string(d) + "&time=" + sjzx;

        final String send = "/api.php?" + send0 + "&token=" + open.cn.awg.pro.core.TextValues.md5(send0);

        String j_code = "";

        String j_msg = "";

        String cookie = "";

        String back = request((String) (url + send), (String[]) (null), (String) ("utf-8"));

        if (java.util.Objects.equals(back, "null") || java.util.Objects.equals(back, null) || java.util.Objects.equals(back, "")) {

            register$error();

        } else {

            try {

                JSONObject json = new JSONObject(back);
                j_code = json.optString("code");
                j_msg = json.optString("msg");

                if (java.util.Objects.equals(j_code, "200")) {

                    e1.tsk("提示", "注册成功");

                } else {

                    final String msg = j_msg;
                    e1.tsk("提示", msg);

                }

            } catch (Throwable __$_e__) {

                register$error();

            }

        }
        return true;

    }

    public void register$error() {
        e1.tsk("提示", "注册失败：未知异常");

    }

    public void register$request$token(Object a, TextView b) {
        a = open.cn.awg.pro.core.TextValues.trim(a);

        final TextView an = b;

        String sjzx = String.valueOf(System.currentTimeMillis());

        String send0 = "type=register_token&email=" + open.cn.awg.pro.core.TextValues.string(a) + "&time=" + sjzx;

        final String send = "/api.php?" + send0 + "&token=" + open.cn.awg.pro.core.TextValues.md5(send0);
        runOnMain(new Runnable() {

            public void run() {

                an.setText("发送中");

            }

        });

        String j_code = "";

        String j_msg = "";

        String back = request((String) (url + send), (String[]) (null), (String) ("utf-8"));

        if (java.util.Objects.equals(back, "null") || java.util.Objects.equals(back, null) || java.util.Objects.equals(back, "")) {

            register$token$error(an);

        } else {

            try {

                JSONObject json = new JSONObject(back);
                j_code = json.optString("code");
                j_msg = json.optString("msg");

                if (java.util.Objects.equals(j_code, "200")) {

                    runOnMain(new Runnable() {

                        public void run() {

                            an.setText("已发送");
                            e1.tsk("提示", "发送验证码成功,请在邮箱查收");

                        }

                    });

                } else {

                    final String msg = j_msg;
                    runOnMain(new Runnable() {

                        public void run() {

                            an.setText("获取");
                            e1.tsk("提示", msg);

                        }

                    });

                }

            } catch (Throwable __$_e__) {

                register$token$error(an);

            }

        }

    }

    public void register$token$error(TextView an) {

        final TextView an2 = an;
        runOnMain(new Runnable() {

            public void run() {

                an2.setText("获取");
                e1.tsk("提示", "发送失败：未知异常");

            }

        });

    }

    public boolean findpwd(Object a, Object b, Object c, Object d) {
        a = open.cn.awg.pro.core.TextValues.trim(a);
        b = open.cn.awg.pro.core.TextValues.trim(b);
        c = open.cn.awg.pro.core.TextValues.trim(c);
        d = open.cn.awg.pro.core.TextValues.trim(d);

        final String email = open.cn.awg.pro.core.TextValues.string(a);

        final String tokenCode = open.cn.awg.pro.core.TextValues.string(b);

        final String newpassword = open.cn.awg.pro.core.TextValues.string(c);

        final String newpasswords = open.cn.awg.pro.core.TextValues.string(d);

        String sjzx = String.valueOf(System.currentTimeMillis());

        String[] postt = new String[]{"email=" + email, "tokenCode=" + tokenCode, "newpassword=" + newpassword, "newpasswords=" + newpasswords};

        String send0 = "time=" + sjzx;

        final String send = "/find.php?" + send0 + "&token=" + open.cn.awg.pro.core.TextValues.md5(send0);

        String j_code = "";

        String j_msg = "";

        String cookie = "";

        String back = request((String) (url + send), (String[]) (postt), (String) ("utf-8"));

        if (java.util.Objects.equals(back, "null") || java.util.Objects.equals(back, null) || java.util.Objects.equals(back, "")) {

            findpwd$error();

        } else {

            try {

                e1.tsk("提示", back);

            } catch (Throwable __$_e__) {

                findpwd$error();

            }

        }
        return true;

    }

    public void findpwd$error() {
        e1.tsk("提示", "密码重置失败：未知异常");

    }


    private String request(String requestUrl, String[] fields, String charset) {
        NativeHttp.Response response = NativeHttp.postForm(requestUrl, fields, charset,
                null, false, null, 0, 0, null);
        return response.bodyForHttp200();
    }

    private int download(String source, String destination, boolean overwrite) {
        File target = NativeFiles.file(applicationContext, destination);
        File parent = target.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs() && !parent.isDirectory()) {
            return -1;
        }
        File temporary = new File(parent, System.nanoTime() + ".xzz");
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(source).openConnection();
            connection.setUseCaches(false);
            try (InputStream input = connection.getInputStream();
                 OutputStream output = new FileOutputStream(temporary)) {
                byte[] buffer = new byte[8192];
                int count;
                while ((count = input.read(buffer)) != -1) {
                    output.write(buffer, 0, count);
                }
            } finally {
                connection.disconnect();
            }
            if (target.exists() && !overwrite) {
                temporary.delete();
                return 1;
            }
            if (target.exists() && !target.delete()) {
                temporary.delete();
                return -1;
            }
            return temporary.renameTo(target) ? 0 : -1;
        } catch (Exception exception) {
            temporary.delete();
            return -1;
        }
    }

    private static void startBackground(Thread thread) {
        thread.setName(String.format("I_APP_Thread_%d", thread.getId()));
        thread.start();
    }

    private void runOnMain(Runnable action) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            action.run();
        } else {
            mainHandler.post(action);
        }
    }

    private static void pause(Object millis) {
        try {
            Thread.sleep(Long.parseLong(String.valueOf(millis)));
        } catch (Exception ignored) {
        }
    }

    private void startActivity(Class<?> activityClass) {
        Intent intent = new Intent(applicationContext, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        applicationContext.startActivity(intent);
    }

}
