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

import com.alibaba.fastjson.JSONObject;

import i.app.iClass;

import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;

public class TypheyeServiceBridge extends iClass {

    public static boolean state_get_data_update = false;
    public static boolean state_update_user_data = false;
    public static boolean state_get_pericon = false;
    public final TypheyeServiceBridge lei = this, 类 = this;
    public String url = "https://service.typheye.cn";
    public String uidxml = AppPaths.appPath("settings/account/user");
    public String cookiexml = AppPaths.appPath("data/easy/cookie");
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

    public TypheyeServiceBridge(i.runlibrary.app.AppInfo _APPINFO) {
        super(_APPINFO);

    }

    public String getPerIcon(Object uid) {

        String url = getPerIconPath(sj.zh().zstring(uid));

        if (wj.cz(url)) {

            return url;

        } else {

            return "null";

        }

    }

    public String getPerIconPath(Object uid) {

        String url = wj.hqml(AppPaths.appPath("data/user/src/pericon/") + sj.zh().zstring(uid));
        return url;

    }

    public void getPericon(Object a) {

        if (!state_get_pericon) {

            state_get_pericon = true;
            a = zf.qctwkg(a);

            final String uid = sj.zh().zstring(a);
            gj.xc(new Thread() {

                public void run() {

                    getPericon$1(uid);

                }

            });

        } else {

            gj.jmxc(new Runnable() {

                public void run() {

                }

            });

        }

    }

    public void getPericon$1(String uid) {

        String send = "/src/pericon/" + uid;

        int back = wl.xz(url + send, getPerIconPath(uid), true);

        if (back == -1) {

            getPericon$error();

        } else {

        }
        state_get_pericon = false;

    }

    public void getPericon$error() {
        gj.jmxc(new Runnable() {

            public void run() {

            }

        });

    }

    public void getDataUpdate() {

        if (!state_get_data_update) {

            state_get_data_update = true;

            String a = wj.dqwb(uidxml);

            String b = wj.dqwb(cookiexml);

            String sjzx = xt.sj(4);

            final String[] post = new String[]{"v1=", "v2=", "v3="};

            String send0 = "type=get_data_update&uid=" + a + "&cookie=" + b + "&time=" + sjzx;

            final String send = "/api.php?" + send0 + "&token=" + sj.md5(send0);
            gj.xc(new Thread() {

                public void run() {

                    String j_code = "";

                    String j_msg = "";

                    int j_info_v0 = 0;

                    int j_info_v1 = 0;

                    int j_info_v2 = 0;

                    int j_info_v3 = 0;

                    String back = wl.hq(url + send, post, "utf-8");

                    if (zf.dy(back, "null") || zf.dy(back, null) || zf.dy(back, "")) {

                        getDataUpdate$error();

                    } else {

                        try {

                            JSONObject json = JSONObject.parseObject(back);
                            j_code = json.getString("code");
                            j_msg = json.getString("msg");
                            try {
                                JSONObject info = json.getJSONObject("info");
                                j_info_v0 = info.getIntValue("v0");
                                j_info_v1 = info.getIntValue("v1");
                                j_info_v2 = info.getIntValue("v2");
                                j_info_v3 = info.getIntValue("v3");
                            } catch (Exception e) {

                            }

                            if (zf.dy(j_code, "200")) {

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

            gj.jmxc(new Runnable() {

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

            String a = wj.dqwb(uidxml);

            String b = wj.dqwb(cookiexml);

            final String uid_ = a;

            String sjzx = xt.sj(4);

            String send0 = "type=get_user_data&uid=" + a + "&cookie=" + b + "&time=" + sjzx;

            final String send = "/api.php?" + send0 + "&token=" + sj.md5(send0);
            gj.xc(new Thread() {

                public void run() {

                    String j_code = "";

                    String j_msg = "";

                    String j_info_shuo = "";

                    String j_info_nick = "";

                    String back = wl.hq(url + send, null, "utf-8");

                    if (zf.dy(back, "null") || zf.dy(back, null) || zf.dy(back, "")) {

                        updateUserData$error();

                    } else {

                        try {

                            JSONObject json = JSONObject.parseObject(back);
                            j_code = json.getString("code");
                            j_msg = json.getString("msg");
                            try {
                                JSONObject info = json.getJSONObject("info");
                                j_info_shuo = info.getString("shuo");
                                j_info_nick = info.getString("nick");
                            } catch (Exception e) {

                            }

                            if (zf.dy(j_code, "200")) {

                                getPericon(uid_);
                                wj.xrwb(set2, j_info_nick);
                                wj.xrwb(set2s, j_info_nick);
                                wj.xrwb(set3, j_info_shuo);

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

            gj.jmxc(new Runnable() {

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
            wj.sc(a);
            wj.sc(b);
            wj.sc(c);
            wj.sc(d);
            gj.jmxc(new Runnable() {

                public void run() {

                    gj.tz(TypheyeLoginActivity.class);

                }

            });
            e1.tsk("提示", "您的账户异常，已退出登录");

        }

    }

    public boolean register$request(Object a, Object b, Object c, Object d) {
        a = zf.qctwkg(a);
        b = zf.qctwkg(b);
        c = zf.qctwkg(c);
        d = zf.qctwkg(d);

        final Object username = a;

        final Object regtoken = b;

        final Object password = c;

        final Object password2 = d;

        String sjzx = xt.sj(4);

        String send0 = "type=register&username=" + sj.zh().zstring(a) + "&regtoken=" + sj.zh().zstring(b) + "&password=" + sj.zh().zstring(c) + "&password2=" + sj.zh().zstring(d) + "&time=" + sjzx;

        final String send = "/api.php?" + send0 + "&token=" + sj.md5(send0);

        String j_code = "";

        String j_msg = "";

        String cookie = "";

        String back = wl.hq(url + send, null, "utf-8");

        if (zf.dy(back, "null") || zf.dy(back, null) || zf.dy(back, "")) {

            register$error();

        } else {

            try {

                JSONObject json = JSONObject.parseObject(back);
                j_code = json.getString("code");
                j_msg = json.getString("msg");

                if (zf.dy(j_code, "200")) {

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

    public void register$request$token(Object a, i.runlibrary.app.v.an b) {
        a = zf.qctwkg(a);

        final i.runlibrary.app.v.an an = b;

        String sjzx = xt.sj(4);

        String send0 = "type=register_token&email=" + sj.zh().zstring(a) + "&time=" + sjzx;

        final String send = "/api.php?" + send0 + "&token=" + sj.md5(send0);
        gj.jmxc(new Runnable() {

            public void run() {

                an.zf("发送中");

            }

        });

        String j_code = "";

        String j_msg = "";

        String back = wl.hq(url + send, null, "utf-8");

        if (zf.dy(back, "null") || zf.dy(back, null) || zf.dy(back, "")) {

            register$token$error(an);

        } else {

            try {

                JSONObject json = JSONObject.parseObject(back);
                j_code = json.getString("code");
                j_msg = json.getString("msg");

                if (zf.dy(j_code, "200")) {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            an.zf("已发送");
                            e1.tsk("提示", "发送验证码成功,请在邮箱查收");

                        }

                    });

                } else {

                    final String msg = j_msg;
                    gj.jmxc(new Runnable() {

                        public void run() {

                            an.zf("获取");
                            e1.tsk("提示", msg);

                        }

                    });

                }

            } catch (Throwable __$_e__) {

                register$token$error(an);

            }

        }

    }

    public void register$token$error(i.runlibrary.app.v.an an) {

        final i.runlibrary.app.v.an an2 = an;
        gj.jmxc(new Runnable() {

            public void run() {

                an2.zf("获取");
                e1.tsk("提示", "发送失败：未知异常");

            }

        });

    }

    public boolean findpwd(Object a, Object b, Object c, Object d) {
        a = zf.qctwkg(a);
        b = zf.qctwkg(b);
        c = zf.qctwkg(c);
        d = zf.qctwkg(d);

        final String email = sj.zh().zstring(a);

        final String tokenCode = sj.zh().zstring(b);

        final String newpassword = sj.zh().zstring(c);

        final String newpasswords = sj.zh().zstring(d);

        String sjzx = xt.sj(4);

        String[] postt = new String[]{"email=" + email, "tokenCode=" + tokenCode, "newpassword=" + newpassword, "newpasswords=" + newpasswords};

        String send0 = "time=" + sjzx;

        final String send = "/find.php?" + send0 + "&token=" + sj.md5(send0);

        String j_code = "";

        String j_msg = "";

        String cookie = "";

        String back = wl.hq(url + send, postt, "utf-8");

        if (zf.dy(back, "null") || zf.dy(back, null) || zf.dy(back, "")) {

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
}
