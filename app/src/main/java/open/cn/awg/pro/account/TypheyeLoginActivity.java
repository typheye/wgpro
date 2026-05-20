/**
 ******************************************************************************
 * @file    TypheyeLoginActivity.java
 * @author  Typheye
 * @brief   Typheye account login screen.
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

import android.graphics.Bitmap;
import android.view.View;
import android.view.Window;

import com.alibaba.fastjson.JSONObject;
import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.browser.X5BrowserActivity;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.R;


public class TypheyeLoginActivity extends iActivity {

    public final TypheyeLoginActivity lei = this, 类 = this;
    public open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public open.cn.awg.pro.account.TypheyeServiceBridge easy = new TypheyeServiceBridge(_APPINFO);
    public boolean st_go2 = false;
    public boolean st_go2_xh = false;
    public boolean go2xh = false;
    public boolean go2qx = false;
    public boolean switch_pwdlogin = false;
    private final android.view.View.OnClickListener $_on_setOnClickListener_i171ea34f1a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i171ea34f1a(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i2a86c78b06 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i2a86c78b06(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i8217d42a17 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i8217d42a17(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i4d951cdb0e = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i4d951cdb0e(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i0d2d4d9236 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i0d2d4d9236(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_ff56a8e7f6 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_ff56a8e7f6(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_f52e07ac8c = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_f52e07ac8c(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.n2);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.app.AwgProApplication.getInstance().addActivity(lei);

        java.lang.String aa = "/data/user/0/open.cn.awg.pro/settings/account/userx";

        java.lang.String bb = "/data/user/0/open.cn.awg.pro/settings/account/passx";

        if (wj.cz(aa) && !zf.dy(zf.qctwkg(wj.dqwb(aa)), "") && wj.cz(bb) && !zf.dy(zf.qctwkg(wj.dqwb(bb)), "")) {

            st.bjk(open.cn.awg.pro.R.id.bjk1).zf(wj.dqwb(aa));
            st.bjk(open.cn.awg.pro.R.id.bjk2).zf(wj.dqwb(bb));

        }
        go2();

    }

    public void __layoutIsLoaded(android.app.Activity ay, android.view.View vw) {

        if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f8/set4.inf"), "true")) {

        }

        i.runlibrary.app.xt$pm pm = xt.pm();

        if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4s.inf"), "false")) {

            if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4.inf"), "true")) {

                getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));

        if (zf.dy(a, "4")) {

            st.xdbj(open.cn.awg.pro.R.id.n2_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.n2_v).shxtck(false);

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

        if (zf.dy(a, "1")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "2")) {

            java.lang.String a001 = "/data/user/0/open.cn.awg.pro/settings/dpi.inf";

            if (wj.cz(a001) || zf.dy(wj.dqwb(a001), "true")) {

                i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
                wtab.nbj(0, "3dp", 0, "3dp");
                wtab.dqfs("center");

            } else {

                i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
                wtab.nbj(0, "9dp", 0, "9dp");
                wtab.dqfs("center");

            }
            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0, 0, 0, 0);

        } else if (zf.dy(a, "3")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "4")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0, 0, 0, 0);

        }

    }

    public void zt(int a) {

        final int i = a;
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                if (i == 0) {

                    st.wb(open.cn.awg.pro.R.id.wb5).kjd(0);
                    st.wb(open.cn.awg.pro.R.id.wb4).zf("扫描二维码完成登录");

                } else if (i == 1) {

                    st.wb(open.cn.awg.pro.R.id.wb5).kjd(4);
                    st.tx(open.cn.awg.pro.R.id.tx1).kjd(8);
                    st.wb(open.cn.awg.pro.R.id.wb4).zf("已扫码等待用户操作");

                } else if (i == 2) {

                    st.wb(open.cn.awg.pro.R.id.wb5).kjd(4);
                    show(1);

                }

            }

        });

    }

    public void go2() {

        if (!st_go2) {

            st_go2 = true;
            gj.xc(new java.lang.Thread() {

                public void run() {

                    show(1);
                    gj.zt(500);

                    java.lang.String set2 = "/data/user/0/open.cn.awg.pro/settings/f7/Enable";

                    java.lang.String oback = "";

                    if (e1.yz()) {

                        try {

                            java.lang.String ljf = wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a17");

                            java.lang.String url = e1.urlUnlockString(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/n1/url.inf"));

                            if (zf.cjw(url, "/")) {

                            } else {

                                url = url + "/";

                            }

                            java.lang.String send0 = "type=generate_login_request";

                            final java.lang.String send = "/api.php?" + send0 + "&token=" + sj.md5(send0);

                            java.lang.String back = wl.hq(url + send, null, "utf-8", null, true, null, 20000, 20000, null);

                            if (zf.dy(back, "") || zf.dy(back, null)) {

                                oback = "登录失败:连接服务器超时";
                                e1.tsk("提示", oback + url + send);
                                show(0);

                            } else {

                                java.lang.String ce = e1.readJson(back, "code");

                                if (zf.dy(ce, "200")) {

                                    try {

                                        i.runlibrary.app.zf$json jo = zf.json(back);

                                        org.json.JSONObject json = jo.json;

                                        java.lang.Object v_request_id = jo.hq(json, "request_id");

                                        java.lang.Object v_qr_code_url = jo.hq(json, "qr_code_url");

                                        if (!zf.dy(v_request_id, "") && !zf.dy(v_qr_code_url, "")) {

                                            go2_xh(sj.zh().zstring(v_request_id), sj.zh().zstring(v_qr_code_url));

                                        } else {

                                            oback = "登录失败:出现异常";
                                            e1.tsk("提示", oback);
                                            show(0);

                                        }

                                    } catch (java.lang.Throwable e) {

                                        oback = "登录失败:出现异常";
                                        e1.tsk("提示", oback);
                                        show(0);

                                    }

                                } else {

                                    oback = "登录失败";
                                    e1.tsk("提示", oback);
                                    show(0);

                                }

                            }

                        } catch (java.lang.Throwable __$_e__) {

                            oback = "支付失败:出现异常";
                            e1.tsk("提示", oback);
                            show(0);

                        }

                    } else {

                        oback = "登录失败:支付环境检测结果异常(-1)";
                        e1.tsk("提示", oback);
                        show(0);

                    }
                    st_go2 = false;

                }

            });

        } else {

            e1.tsk("提示", "操作过于频繁，请稍后重试");

        }

    }

    public void go2_xh(java.lang.String v_request_id_, java.lang.String v_qr_code_url_) {

        if (!st_go2_xh) {

            st_go2_xh = true;

            final java.lang.String v_request_id = v_request_id_;

            final java.lang.String v_qr_code_url = v_qr_code_url_;
            gj.xc(new java.lang.Thread() {

                public void run() {

                    go2xh = true;
                    go2qx = false;
                    switch_pwdlogin = false;
                    show(2);
                    zt(0);
                    ewm(v_qr_code_url);

                    java.lang.String oback = "";

                    java.lang.String set2 = "/data/user/0/open.cn.awg.pro/settings/n1/set2.inf";

                    java.lang.String set3 = "/data/user/0/open.cn.awg.pro/settings/n1/set3.inf";

                    java.lang.String set4 = "/data/user/0/open.cn.awg.pro/data/easy/cookie";

                    java.lang.String set2s = "/data/user/0/open.cn.awg.pro/settings/f11/set3.inf";

                    java.lang.String aa = "/data/user/0/open.cn.awg.pro/settings/account/user";

                    java.lang.String bb = "/data/user/0/open.cn.awg.pro/settings/account/pass";

                    java.lang.String aax = "/data/user/0/open.cn.awg.pro/settings/account/userx";

                    java.lang.String bbx = "/data/user/0/open.cn.awg.pro/settings/account/passx";

                    java.lang.String j_info_cookie = "";

                    java.lang.String j_info_email = "";

                    java.lang.String j_info_more = "";

                    java.lang.String j_info_uid = "";

                    java.lang.String j_info_shuo = "";

                    java.lang.String j_info_nick = "";

                    java.lang.String j_info_note = "";

                    while (go2xh) {

                        gj.zt(3000);

                        if (switch_pwdlogin) {

                            go2xh = false;
                            show(3);
                            break;

                        }

                        if (go2qx) {

                            go2xh = false;
                            oback = "登录失败:用户取消登录";
                            e1.tsk("提示", oback);
                            show(0);
                            break;

                        }

                        java.lang.String url = e1.urlUnlockString(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/n1/url.inf"));

                        if (zf.cjw(url, "/")) {

                        } else {

                            url = url + "/";

                        }

                        java.lang.String send = "/api.php?type=check_login_request_status&request_id=" + v_request_id;

                        java.lang.String back = wl.hq(url + send, "utf-8");

                        if (zf.dy(back, "") || zf.dy(back, null)) {

                            go2xh = false;
                            oback = "登录失败:连接服务器超时";
                            e1.tsk("提示", oback);
                            show(0);
                            break;

                        } else {

                            java.lang.String ce = e1.readJson(back, "code");

                            if (zf.dy(ce, "200")) {

                                try {

                                    i.runlibrary.app.zf$json jo = zf.json(back);

                                    org.json.JSONObject json0 = jo.json;

                                    final java.lang.String v_status = sj.zh().zstring(jo.hq(json0, "status"));

                                    if (zf.dy(v_status, "approved")) {

                                        go2xh = false;
                                        zt(2);

                                        JSONObject json = JSONObject.parseObject(back);

                                        try {
                                            JSONObject info = json.getJSONObject("info");
                                            j_info_cookie = info.getString("cookie");
                                            j_info_shuo = info.getString("shuo");
                                            j_info_nick = info.getString("nick");
                                            j_info_note = info.getString("note");
                                            j_info_uid = info.getString("uid");
                                            j_info_more = info.getString("more");
                                            j_info_email = info.getString("email");
                                        } catch (Exception e) {

                                        }

                                        e1.tsk("提示", "登录成功");
                                        wj.xrwb(set2, j_info_nick);
                                        wj.xrwb(set2s, j_info_nick);
                                        wj.xrwb(set3, j_info_shuo);
                                        wj.xrwb(set4, j_info_cookie);

                                        java.lang.String b = sj.md5("none");
                                        wj.xrwb(aa, j_info_uid);
                                        wj.xrwb(bb, b);
                                        wj.xrwb(aax, j_info_uid);
                                        wj.xrwb(bbx, b);
                                        easy.getPericon(j_info_uid);
                                        show(0);
                                        break;

                                    } else if (zf.dy(v_status, "rejected")) {

                                        go2xh = false;
                                        oback = "登录失败:请求被拒绝";
                                        e1.tsk("提示", oback);
                                        show(0);
                                        break;

                                    } else if (zf.dy(v_status, "pending")) {

                                        zt(0);

                                    } else if (zf.dy(v_status, "waiting")) {

                                        zt(1);

                                    }

                                } catch (java.lang.Throwable e) {

                                    go2xh = false;
                                    oback = "登录失败:出现异常";
                                    e1.tsk("提示", oback);
                                    show(0);
                                    break;

                                }

                            } else {

                                go2xh = false;
                                oback = "登录失败:出现异常";
                                e1.tsk("提示", oback);
                                show(0);
                                break;

                            }

                        }

                    }
                    st_go2_xh = false;

                }

            });

        } else {

            go2xh = false;

            java.lang.String oback = "登录失败:操作过于频繁，请稍后重试";
            e1.tsk("提示", oback);
            show(0);

        }

    }

    public void ewm(java.lang.String url) {

        final java.lang.String qr = url;
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                try {

                    android.widget.ImageView imageView = findViewById(R.id.tx1);
                    Bitmap bitmap = com.demo.e4.createQRCodeBitmap(qr, 800, 800, "UTF-8", "H", "1");
                    imageView.setImageBitmap(bitmap);

                    st.tx(open.cn.awg.pro.R.id.tx1).kjd(0);

                } catch (java.lang.Throwable e) {

                    st.tx(open.cn.awg.pro.R.id.tx1).kjd(8);

                }

            }

        });

    }

    public void go() {
        gj.xc(new java.lang.Thread() {

            public void run() {

                show(1);
                gj.zt(500);

                java.lang.String set2 = "/data/user/0/open.cn.awg.pro/settings/n1/set2.inf";

                java.lang.String set3 = "/data/user/0/open.cn.awg.pro/settings/n1/set3.inf";

                java.lang.String set4 = "/data/user/0/open.cn.awg.pro/data/easy/cookie";

                java.lang.String set2s = "/data/user/0/open.cn.awg.pro/settings/f11/set3.inf";

                java.lang.String aa = "/data/user/0/open.cn.awg.pro/settings/account/user";

                java.lang.String bb = "/data/user/0/open.cn.awg.pro/settings/account/pass";

                java.lang.String aax = "/data/user/0/open.cn.awg.pro/settings/account/userx";

                java.lang.String bbx = "/data/user/0/open.cn.awg.pro/settings/account/passx";

                java.lang.String a = st.bjk(open.cn.awg.pro.R.id.bjk1).zf();
                a = zf.qctwkg(a);

                java.lang.String b = st.bjk(open.cn.awg.pro.R.id.bjk2).zf();
                b = zf.qctwkg(b);

                if (zf.dy(a, "") || zf.dy(b, "")) {

                    e1.tsk("提示", "账户号码或密码不能为空");
                    show(0);

                } else {

                    gj.zt(250);

                    java.lang.String ljf = wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a17");

                    java.lang.String url = e1.urlUnlockString(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/n1/url.inf"));

                    if (zf.cjw(url, "/")) {

                    } else {

                        url = url + "/";

                    }

                    java.lang.String j_code = "";

                    java.lang.String j_msg = "";

                    java.lang.String j_info_cookie = "";

                    java.lang.String j_info_email = "";

                    java.lang.String j_info_more = "";

                    java.lang.String j_info_uid = "";

                    java.lang.String j_info_shuo = "";

                    java.lang.String j_info_nick = "";

                    java.lang.String j_info_note = "";
                    a = zf.qctwkg(a);
                    b = zf.qctwkg(b);

                    java.lang.String salt = sj.md5(sj.zh().zstring(a));
                    salt = zf.qc(salt, 0, 6);
                    salt = sj.md5(salt);
                    salt = zf.qc(salt, 0, 5);
                    salt = sj.md5(salt);
                    b = sj.md5(b) + salt;

                    java.lang.String sjzx = xt.sj(4);

                    java.lang.String send0 = "type=login&username=" + sj.zh().zstring(a) + "&password=" + sj.zh().zstring(b) + "&time=" + sjzx;

                    final java.lang.String send = "/api.php?" + send0 + "&token=" + sj.md5(send0);

                    if (e1.yz()) {

                        final java.lang.String back = wl.hq(url + send, null, "utf-8", null, true, null, 20000, 20000, null);

                        if (zf.dy(back, "") || zf.dy(back, null)) {

                            e1.tsk("提示", "登录失败，无法连接到服务器");
                            show(0);

                        } else {

                            try {

                                JSONObject json = JSONObject.parseObject(back);
                                j_code = json.getString("code");
                                j_msg = json.getString("msg");
                                try {
                                    JSONObject info = json.getJSONObject("info");
                                    j_info_cookie = info.getString("cookie");
                                    j_info_shuo = info.getString("shuo");
                                    j_info_nick = info.getString("nick");
                                    j_info_note = info.getString("note");
                                    j_info_uid = info.getString("uid");
                                    j_info_more = info.getString("more");
                                    j_info_email = info.getString("email");
                                } catch (Exception e) {

                                }

                                if (zf.dy(j_code, "200")) {

                                    e1.tsk("提示", "登录成功");
                                    easy.getPericon(j_info_uid);
                                    wj.xrwb(set2, j_info_nick);
                                    wj.xrwb(set2s, j_info_nick);
                                    wj.xrwb(set3, j_info_shuo);
                                    wj.xrwb(set4, j_info_cookie);
                                    wj.xrwb(aa, j_info_uid);
                                    wj.xrwb(bb, b);
                                    wj.xrwb(aax, j_info_uid);
                                    wj.xrwb(bbx, b);
                                    gj.gb();

                                } else {

                                    e1.tsk("提示", "登录失败\n" + j_msg);
                                    show(0);

                                }

                            } catch (java.lang.Throwable __$_e__) {

                                e1.tsk("提示", "登录失败，出现异常");
                                show(0);

                            }

                        }

                    } else {

                        e1.tsk("提示", "登录失败\n应用验证未通过");
                        show(0);

                    }

                }

            }

        });

    }

    public void show(int i) {

        final int ii = i;

        final i.runlibrary.app.v.xxbj xxbj1 = st.xxbj(open.cn.awg.pro.R.id.xxbj1);

        final i.runlibrary.app.v.xxbj xxbj2 = st.xxbj(open.cn.awg.pro.R.id.xxbj2);

        final i.runlibrary.app.v.xxbj xxbj4 = st.xxbj(open.cn.awg.pro.R.id.xxbj4);
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                if (ii == 0) {

                    st.wb(open.cn.awg.pro.R.id.wb5).kjd(4);
                    xxbj2.kjd(8);
                    xxbj4.kjd(8);
                    xxbj1.kjd(8);
                    gj.gb();

                } else if (ii == 1) {

                    xxbj1.kjd(8);
                    xxbj2.kjd(0);
                    xxbj4.kjd(8);

                } else if (ii == 2) {

                    xxbj1.kjd(8);
                    xxbj2.kjd(8);
                    xxbj4.kjd(0);

                } else if (ii == 3) {

                    xxbj1.kjd(0);
                    xxbj2.kjd(8);
                    xxbj4.kjd(8);

                }

            }

        });

    }

    public void onRestart() {
        super.onRestart();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

    }

    public void onStart() {
        super.onStart();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

        i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(open.cn.awg.pro.R.id.qtgd1);

        qtgd1.v.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View vw, android.view.MotionEvent me) {
                if (me.getAction() == android.view.MotionEvent.ACTION_SCROLL && me.isFromSource(androidx.core.view.InputDeviceCompat.SOURCE_ROTARY_ENCODER)) {

                    float delta = -me.getAxisValue(androidx.core.view.MotionEventCompat.AXIS_SCROLL) *
                            androidx.core.view.ViewConfigurationCompat.getScaledVerticalScrollFactor(
                                    android.view.ViewConfiguration.get(lei), lei
                            );

                    vw.scrollBy(0, java.lang.Math.round(delta));
                    return true;
                }
                return false;
            }
        });

        qtgd1.v.requestFocus();

    }

    public void onResume() {
        super.onResume();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

    }

    public boolean onKeyDown(int kc, android.view.KeyEvent ke) {

        if (kc == 4) {

        }
        return false;

    }

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.app.AwgProApplication.getInstance().removeActivity(lei);

    }

    private void $_onClick_f52e07ac8c(android.view.View vw) {
        show(1);
        go2();

    }

    private void $_onClick_ff56a8e7f6(android.view.View vw) {

        java.lang.String url = "https://service.typheye.cn/site/user/center/register/";

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a8 = wj.dqwb(a2);

        if (zf.dy(a8, "4")) {

            if (e1.isInstallX5()) {

                final java.lang.String[] name = new java.lang.String[]{"url"};

                final java.lang.String[] value = new java.lang.String[]{url};
                gj.tz(X5BrowserActivity.class, name, value);

            } else {

                wl.fw(url);

            }

        } else {

            if (e1.isInstallX5()) {

                final java.lang.String[] name = new java.lang.String[]{"url"};

                final java.lang.String[] value = new java.lang.String[]{url};
                gj.tz(X5BrowserActivity.class, name, value);

            } else {

                e1.tsk_QR("账户注册", "如果您需要注册Typheye账户,请扫描下方二维码后在手机上操作.", url);

            }

        }

    }

    private void $_onClick_i0d2d4d9236(android.view.View vw) {

        java.lang.String url = "https://service.typheye.cn/site/user/center/find/";

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a8 = wj.dqwb(a2);

        if (zf.dy(a8, "4")) {

            if (e1.isInstallX5()) {

                final java.lang.String[] name = new java.lang.String[]{"url"};

                final java.lang.String[] value = new java.lang.String[]{url};
                gj.tz(X5BrowserActivity.class, name, value);

            } else {

                wl.fw(url);

            }

        } else {

            if (e1.isInstallX5()) {

                final java.lang.String[] name = new java.lang.String[]{"url"};

                final java.lang.String[] value = new java.lang.String[]{url};
                gj.tz(X5BrowserActivity.class, name, value);

            } else {

                e1.tsk_QR("找回密码", "如果您需要找回Typheye账户,请扫描下方二维码后在手机上操作.", url);

            }

        }

    }

    private void $_onClick_i4d951cdb0e(android.view.View vw) {
        gj.gb();

    }

    private void $_onClick_i8217d42a17(android.view.View vw) {
        go();

    }

    private void $_onClick_i2a86c78b06(android.view.View vw) {
        switch_pwdlogin = true;
        show(1);

    }

    private void $_onClick_i171ea34f1a(android.view.View vw) {
        go2qx = true;
        show(1);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);

        android.widget.TextView f52e07ac8c = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb6);
        f52e07ac8c.setOnClickListener($_on_setOnClickListener_f52e07ac8c);

        android.widget.TextView ff56a8e7f6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb1);
        ff56a8e7f6.setOnClickListener($_on_setOnClickListener_ff56a8e7f6);

        android.widget.TextView i0d2d4d9236 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb3);
        i0d2d4d9236.setOnClickListener($_on_setOnClickListener_i0d2d4d9236);

        android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
        i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

        android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);
        i8217d42a17.setOnClickListener($_on_setOnClickListener_i8217d42a17);

        android.widget.TextView i2a86c78b06 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb5);
        i2a86c78b06.setOnClickListener($_on_setOnClickListener_i2a86c78b06);

        android.widget.Button i171ea34f1a = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an1);
        i171ea34f1a.setOnClickListener($_on_setOnClickListener_i171ea34f1a);

        __layoutIsLoaded(ay, vw);
    }
}
