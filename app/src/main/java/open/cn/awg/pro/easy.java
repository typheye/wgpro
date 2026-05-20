/**
 ******************************************************************************
 * @file    easy.java
 * @author  Typheye
 * @brief   AWG Pro module source.
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

import com.alibaba.fastjson.JSONObject;

import i.app.iClass;

public class easy extends iClass {

    public static boolean state_get_data_update = false;
    public static boolean state_update_user_data = false;
    public static boolean state_get_pericon = false;
    public final easy lei = this, 类 = this;
    public java.lang.String url = "https://service.typheye.cn";
    public java.lang.String uidxml = "/data/user/0/open.cn.awg.pro/settings/account/user";
    public java.lang.String cookiexml = "/data/user/0/open.cn.awg.pro/data/easy/cookie";
    public open.cn.awg.pro.e1 e1 = new e1(_APPINFO);

    public easy(i.runlibrary.app.AppInfo _APPINFO) {
        super(_APPINFO);

    }

    public java.lang.String getPerIcon(java.lang.Object uid) {

        java.lang.String url = getPerIconPath(sj.zh().zstring(uid));

        if (wj.cz(url)) {

            return url;

        } else {

            return "null";

        }

    }

    public java.lang.String getPerIconPath(java.lang.Object uid) {

        java.lang.String url = wj.hqml("/data/user/0/open.cn.awg.pro/data/user/src/pericon/" + sj.zh().zstring(uid));
        return url;

    }

    public void getPericon(java.lang.Object a) {

        if (!state_get_pericon) {

            state_get_pericon = true;
            a = zf.qctwkg(a);

            final java.lang.String uid = sj.zh().zstring(a);
            gj.xc(new java.lang.Thread() {

                public void run() {

                    getPericon$1(uid);

                }

            });

        } else {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                }

            });

        }

    }

    public void getPericon$1(java.lang.String uid) {

        java.lang.String send = "/src/pericon/" + uid;

        int back = wl.xz(url + send, getPerIconPath(uid), true);

        if (back == -1) {

            getPericon$error();

        } else {

        }
        state_get_pericon = false;

    }

    public void getPericon$error() {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

            }

        });

    }

    public void getDataUpdate() {

        if (!state_get_data_update) {

            state_get_data_update = true;

            java.lang.String a = wj.dqwb(uidxml);

            java.lang.String b = wj.dqwb(cookiexml);

            java.lang.String sjzx = xt.sj(4);

            final java.lang.String[] post = new java.lang.String[]{"v1=", "v2=", "v3="};

            java.lang.String send0 = "type=get_data_update&uid=" + a + "&cookie=" + b + "&time=" + sjzx;

            final java.lang.String send = "/api.php?" + send0 + "&token=" + sj.md5(send0);
            gj.xc(new java.lang.Thread() {

                public void run() {

                    java.lang.String j_code = "";

                    java.lang.String j_msg = "";

                    int j_info_v0 = 0;

                    int j_info_v1 = 0;

                    int j_info_v2 = 0;

                    int j_info_v3 = 0;

                    java.lang.String back = wl.hq(url + send, post, "utf-8");

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

                        } catch (java.lang.Throwable __$_e__) {

                            getDataUpdate$error();

                        }

                    }
                    state_get_data_update = false;

                }

            });

        } else {

            gj.jmxc(new java.lang.Runnable() {

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

            final java.lang.String set2 = "/data/user/0/open.cn.awg.pro/settings/n1/set2.inf";

            final java.lang.String set3 = "/data/user/0/open.cn.awg.pro/settings/n1/set3.inf";

            final java.lang.String set2s = "/data/user/0/open.cn.awg.pro/settings/f11/set3.inf";

            java.lang.String a = wj.dqwb(uidxml);

            java.lang.String b = wj.dqwb(cookiexml);

            final java.lang.String uid_ = a;

            java.lang.String sjzx = xt.sj(4);

            java.lang.String send0 = "type=get_user_data&uid=" + a + "&cookie=" + b + "&time=" + sjzx;

            final java.lang.String send = "/api.php?" + send0 + "&token=" + sj.md5(send0);
            gj.xc(new java.lang.Thread() {

                public void run() {

                    java.lang.String j_code = "";

                    java.lang.String j_msg = "";

                    java.lang.String j_info_shuo = "";

                    java.lang.String j_info_nick = "";

                    java.lang.String back = wl.hq(url + send, null, "utf-8");

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

                        } catch (java.lang.Throwable __$_e__) {

                            updateUserData$error();

                        }

                    }
                    state_update_user_data = false;

                }

            });

        } else {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                }

            });

        }

    }

    public void updateUserData$error() {

    }

    public void getLoginError() {

        if (e1.islogin()) {

            java.lang.String a = "/data/user/0/open.cn.awg.pro/settings/account/user";

            java.lang.String b = "/data/user/0/open.cn.awg.pro/settings/account/pass";

            java.lang.String c = "/data/user/0/open.cn.awg.pro/settings/account/userx";

            java.lang.String d = "/data/user/0/open.cn.awg.pro/settings/account/passx";
            wj.sc(a);
            wj.sc(b);
            wj.sc(c);
            wj.sc(d);
            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    gj.tz(n2.class);

                }

            });
            e1.tsk("提示", "您的账户异常，已退出登录");

        }

    }

    public boolean register$request(java.lang.Object a, java.lang.Object b, java.lang.Object c, java.lang.Object d) {
        a = zf.qctwkg(a);
        b = zf.qctwkg(b);
        c = zf.qctwkg(c);
        d = zf.qctwkg(d);

        final java.lang.Object username = a;

        final java.lang.Object regtoken = b;

        final java.lang.Object password = c;

        final java.lang.Object password2 = d;

        java.lang.String sjzx = xt.sj(4);

        java.lang.String send0 = "type=register&username=" + sj.zh().zstring(a) + "&regtoken=" + sj.zh().zstring(b) + "&password=" + sj.zh().zstring(c) + "&password2=" + sj.zh().zstring(d) + "&time=" + sjzx;

        final java.lang.String send = "/api.php?" + send0 + "&token=" + sj.md5(send0);

        java.lang.String j_code = "";

        java.lang.String j_msg = "";

        java.lang.String cookie = "";

        java.lang.String back = wl.hq(url + send, null, "utf-8");

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

                    final java.lang.String msg = j_msg;
                    e1.tsk("提示", msg);

                }

            } catch (java.lang.Throwable __$_e__) {

                register$error();

            }

        }
        return true;

    }

    public void register$error() {
        e1.tsk("提示", "注册失败：未知异常");

    }

    public void register$request$token(java.lang.Object a, i.runlibrary.app.v.an b) {
        a = zf.qctwkg(a);

        final i.runlibrary.app.v.an an = b;

        java.lang.String sjzx = xt.sj(4);

        java.lang.String send0 = "type=register_token&email=" + sj.zh().zstring(a) + "&time=" + sjzx;

        final java.lang.String send = "/api.php?" + send0 + "&token=" + sj.md5(send0);
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                an.zf("发送中");

            }

        });

        java.lang.String j_code = "";

        java.lang.String j_msg = "";

        java.lang.String back = wl.hq(url + send, null, "utf-8");

        if (zf.dy(back, "null") || zf.dy(back, null) || zf.dy(back, "")) {

            register$token$error(an);

        } else {

            try {

                JSONObject json = JSONObject.parseObject(back);
                j_code = json.getString("code");
                j_msg = json.getString("msg");

                if (zf.dy(j_code, "200")) {

                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            an.zf("已发送");
                            e1.tsk("提示", "发送验证码成功,请在邮箱查收");

                        }

                    });

                } else {

                    final java.lang.String msg = j_msg;
                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            an.zf("获取");
                            e1.tsk("提示", msg);

                        }

                    });

                }

            } catch (java.lang.Throwable __$_e__) {

                register$token$error(an);

            }

        }

    }

    public void register$token$error(i.runlibrary.app.v.an an) {

        final i.runlibrary.app.v.an an2 = an;
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                an2.zf("获取");
                e1.tsk("提示", "发送失败：未知异常");

            }

        });

    }

    public boolean findpwd(java.lang.Object a, java.lang.Object b, java.lang.Object c, java.lang.Object d) {
        a = zf.qctwkg(a);
        b = zf.qctwkg(b);
        c = zf.qctwkg(c);
        d = zf.qctwkg(d);

        final java.lang.String email = sj.zh().zstring(a);

        final java.lang.String tokenCode = sj.zh().zstring(b);

        final java.lang.String newpassword = sj.zh().zstring(c);

        final java.lang.String newpasswords = sj.zh().zstring(d);

        java.lang.String sjzx = xt.sj(4);

        java.lang.String[] postt = new java.lang.String[]{"email=" + email, "tokenCode=" + tokenCode, "newpassword=" + newpassword, "newpasswords=" + newpasswords};

        java.lang.String send0 = "time=" + sjzx;

        final java.lang.String send = "/find.php?" + send0 + "&token=" + sj.md5(send0);

        java.lang.String j_code = "";

        java.lang.String j_msg = "";

        java.lang.String cookie = "";

        java.lang.String back = wl.hq(url + send, postt, "utf-8");

        if (zf.dy(back, "null") || zf.dy(back, null) || zf.dy(back, "")) {

            findpwd$error();

        } else {

            try {

                e1.tsk("提示", back);

            } catch (java.lang.Throwable __$_e__) {

                findpwd$error();

            }

        }
        return true;

    }

    public void findpwd$error() {
        e1.tsk("提示", "密码重置失败：未知异常");

    }
}
