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

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import com.alibaba.fastjson.JSONObject;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.browser.X5BrowserActivity;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class TypheyeLoginActivity extends BaseAwgActivity {

    public final TypheyeLoginActivity lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public TypheyeServiceBridge easy = new TypheyeServiceBridge(_APPINFO);
    public boolean st_go2 = false;
    public boolean st_go2_xh = false;
    public boolean go2xh = false;
    public boolean go2qx = false;
    public boolean switch_pwdlogin = false;
    private final View.OnClickListener an1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn1Click(vw);
        }

    };
    private final View.OnClickListener wb5ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb5Click(vw);
        }

    };
    private final View.OnClickListener tx3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx3Click(vw);
        }

    };
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };
    private final View.OnClickListener wb3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb3Click(vw);
        }

    };
    private final View.OnClickListener wb1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb1Click(vw);
        }

    };
    private final View.OnClickListener wb6ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb6Click(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.typheye_login);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        String aa = AppPaths.appPath("settings/account/userx");

        String bb = AppPaths.appPath("settings/account/passx");

        if (wj.cz(aa) && !zf.dy(zf.qctwkg(wj.dqwb(aa)), "") && wj.cz(bb) && !zf.dy(zf.qctwkg(wj.dqwb(bb)), "")) {

            st.bjk(R.id.bjk1).zf(wj.dqwb(aa));
            st.bjk(R.id.bjk2).zf(wj.dqwb(bb));

        }
        go2();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.typheye_login_root, R.id.xdbj1, R.id.title_bar);

    }

    public void zt(int a) {

        final int i = a;
        gj.jmxc(new Runnable() {

            public void run() {

                if (i == 0) {

                    st.wb(R.id.wb5).kjd(0);
                    st.wb(R.id.wb4).zf("扫描二维码完成登录");

                } else if (i == 1) {

                    st.wb(R.id.wb5).kjd(4);
                    st.tx(R.id.tx1).kjd(8);
                    st.wb(R.id.wb4).zf("已扫码等待用户操作");

                } else if (i == 2) {

                    st.wb(R.id.wb5).kjd(4);
                    show(1);

                }

            }

        });

    }

    public void go2() {

        if (!st_go2) {

            st_go2 = true;
            gj.xc(new Thread() {

                public void run() {

                    show(1);
                    gj.zt(500);

                    String set2 = AppPaths.appPath("settings/f7/Enable");

                    String oback = "";

                    if (e1.yz()) {

                        try {

                            String ljf = wj.dqwb(AppPaths.appPath("data/assets/a17"));

                            String url = e1.urlUnlockString(wj.dqwb(AppPaths.appPath("settings/n1/url.inf")));

                            if (zf.cjw(url, "/")) {

                            } else {

                                url = url + "/";

                            }

                            String send0 = "type=generate_login_request";

                            final String send = "/api.php?" + send0 + "&token=" + sj.md5(send0);

                            String back = wl.hq(url + send, null, "utf-8", null, true, null, 20000, 20000, null);

                            if (zf.dy(back, "") || zf.dy(back, null)) {

                                oback = "登录失败:连接服务器超时";
                                e1.tsk("提示", oback + url + send);
                                show(0);

                            } else {

                                String ce = e1.readJson(back, "code");

                                if (zf.dy(ce, "200")) {

                                    try {

                                        i.runlibrary.app.zf$json jo = zf.json(back);

                                        org.json.JSONObject json = jo.json;

                                        Object v_request_id = jo.hq(json, "request_id");

                                        Object v_qr_code_url = jo.hq(json, "qr_code_url");

                                        if (!zf.dy(v_request_id, "") && !zf.dy(v_qr_code_url, "")) {

                                            go2_xh(sj.zh().zstring(v_request_id), sj.zh().zstring(v_qr_code_url));

                                        } else {

                                            oback = "登录失败:出现异常";
                                            e1.tsk("提示", oback);
                                            show(0);

                                        }

                                    } catch (Throwable e) {

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

                        } catch (Throwable __$_e__) {

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

    public void go2_xh(String v_request_id_, String v_qr_code_url_) {

        if (!st_go2_xh) {

            st_go2_xh = true;

            final String v_request_id = v_request_id_;

            final String v_qr_code_url = v_qr_code_url_;
            gj.xc(new Thread() {

                public void run() {

                    go2xh = true;
                    go2qx = false;
                    switch_pwdlogin = false;
                    show(2);
                    zt(0);
                    ewm(v_qr_code_url);

                    String oback = "";

                    String set2 = AppPaths.appPath("settings/n1/set2.inf");

                    String set3 = AppPaths.appPath("settings/n1/set3.inf");

                    String set4 = AppPaths.appPath("data/easy/cookie");

                    String set2s = AppPaths.appPath("settings/f11/set3.inf");

                    String aa = AppPaths.appPath("settings/account/user");

                    String bb = AppPaths.appPath("settings/account/pass");

                    String aax = AppPaths.appPath("settings/account/userx");

                    String bbx = AppPaths.appPath("settings/account/passx");

                    String j_info_cookie = "";

                    String j_info_email = "";

                    String j_info_more = "";

                    String j_info_uid = "";

                    String j_info_shuo = "";

                    String j_info_nick = "";

                    String j_info_note = "";

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

                        String url = e1.urlUnlockString(wj.dqwb(AppPaths.appPath("settings/n1/url.inf")));

                        if (zf.cjw(url, "/")) {

                        } else {

                            url = url + "/";

                        }

                        String send = "/api.php?type=check_login_request_status&request_id=" + v_request_id;

                        String back = wl.hq(url + send, "utf-8");

                        if (zf.dy(back, "") || zf.dy(back, null)) {

                            go2xh = false;
                            oback = "登录失败:连接服务器超时";
                            e1.tsk("提示", oback);
                            show(0);
                            break;

                        } else {

                            String ce = e1.readJson(back, "code");

                            if (zf.dy(ce, "200")) {

                                try {

                                    i.runlibrary.app.zf$json jo = zf.json(back);

                                    org.json.JSONObject json0 = jo.json;

                                    final String v_status = sj.zh().zstring(jo.hq(json0, "status"));

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

                                        String b = sj.md5("none");
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

                                } catch (Throwable e) {

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

            String oback = "登录失败:操作过于频繁，请稍后重试";
            e1.tsk("提示", oback);
            show(0);

        }

    }

    public void ewm(String url) {

        final String qr = url;
        gj.jmxc(new Runnable() {

            public void run() {

                try {

                    ImageView imageView = findViewById(R.id.tx1);
                    Bitmap bitmap = com.demo.e4.createQRCodeBitmap(qr, 800, 800, "UTF-8", "H", "1");
                    imageView.setImageBitmap(bitmap);

                    st.tx(R.id.tx1).kjd(0);

                } catch (Throwable e) {

                    st.tx(R.id.tx1).kjd(8);

                }

            }

        });

    }

    public void go() {
        gj.xc(new Thread() {

            public void run() {

                show(1);
                gj.zt(500);

                String set2 = AppPaths.appPath("settings/n1/set2.inf");

                String set3 = AppPaths.appPath("settings/n1/set3.inf");

                String set4 = AppPaths.appPath("data/easy/cookie");

                String set2s = AppPaths.appPath("settings/f11/set3.inf");

                String aa = AppPaths.appPath("settings/account/user");

                String bb = AppPaths.appPath("settings/account/pass");

                String aax = AppPaths.appPath("settings/account/userx");

                String bbx = AppPaths.appPath("settings/account/passx");

                String a = st.bjk(R.id.bjk1).zf();
                a = zf.qctwkg(a);

                String b = st.bjk(R.id.bjk2).zf();
                b = zf.qctwkg(b);

                if (zf.dy(a, "") || zf.dy(b, "")) {

                    e1.tsk("提示", "账户号码或密码不能为空");
                    show(0);

                } else {

                    gj.zt(250);

                    String ljf = wj.dqwb(AppPaths.appPath("data/assets/a17"));

                    String url = e1.urlUnlockString(wj.dqwb(AppPaths.appPath("settings/n1/url.inf")));

                    if (zf.cjw(url, "/")) {

                    } else {

                        url = url + "/";

                    }

                    String j_code = "";

                    String j_msg = "";

                    String j_info_cookie = "";

                    String j_info_email = "";

                    String j_info_more = "";

                    String j_info_uid = "";

                    String j_info_shuo = "";

                    String j_info_nick = "";

                    String j_info_note = "";
                    a = zf.qctwkg(a);
                    b = zf.qctwkg(b);

                    String salt = sj.md5(sj.zh().zstring(a));
                    salt = zf.qc(salt, 0, 6);
                    salt = sj.md5(salt);
                    salt = zf.qc(salt, 0, 5);
                    salt = sj.md5(salt);
                    b = sj.md5(b) + salt;

                    String sjzx = xt.sj(4);

                    String send0 = "type=login&username=" + sj.zh().zstring(a) + "&password=" + sj.zh().zstring(b) + "&time=" + sjzx;

                    final String send = "/api.php?" + send0 + "&token=" + sj.md5(send0);

                    if (e1.yz()) {

                        final String back = wl.hq(url + send, null, "utf-8", null, true, null, 20000, 20000, null);

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

                            } catch (Throwable __$_e__) {

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

        final i.runlibrary.app.v.xxbj xxbj1 = st.xxbj(R.id.xxbj1);

        final i.runlibrary.app.v.xxbj xxbj2 = st.xxbj(R.id.xxbj2);

        final i.runlibrary.app.v.xxbj xxbj4 = st.xxbj(R.id.xxbj4);
        gj.jmxc(new Runnable() {

            public void run() {

                if (ii == 0) {

                    st.wb(R.id.wb5).kjd(4);
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
            applyWindowModeFromSettings();

    }

    public void onStart() {
        super.onStart();
            applyWindowModeFromSettings();

        i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(R.id.qtgd1);

        qtgd1.v.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View vw, MotionEvent me) {
                if (me.getAction() == MotionEvent.ACTION_SCROLL && me.isFromSource(InputDeviceCompat.SOURCE_ROTARY_ENCODER)) {

                    float delta = -me.getAxisValue(MotionEventCompat.AXIS_SCROLL) *
                            ViewConfigurationCompat.getScaledVerticalScrollFactor(
                                    ViewConfiguration.get(lei), lei
                            );

                    vw.scrollBy(0, Math.round(delta));
                    return true;
                }
                return false;
            }
        });

        qtgd1.v.requestFocus();

    }

    public void onResume() {
        super.onResume();
            applyWindowModeFromSettings();

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
            applyWindowModeFromSettings();

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

        }
        return false;

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onWb6Click(View vw) {
        show(1);
        go2();

    }

    private void onWb1Click(View vw) {

        String url = "https://service.typheye.cn/site/user/center/register/";

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a8 = wj.dqwb(a2);

        if (zf.dy(a8, "4")) {

            if (e1.isInstallX5()) {

                final String[] name = new String[]{"url"};

                final String[] value = new String[]{url};
                gj.tz(X5BrowserActivity.class, name, value);

            } else {

                wl.fw(url);

            }

        } else {

            if (e1.isInstallX5()) {

                final String[] name = new String[]{"url"};

                final String[] value = new String[]{url};
                gj.tz(X5BrowserActivity.class, name, value);

            } else {

                e1.tsk_QR("账户注册", "如果您需要注册Typheye账户,请扫描下方二维码后在手机上操作.", url);

            }

        }

    }

    private void onWb3Click(View vw) {

        String url = "https://service.typheye.cn/site/user/center/find/";

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a8 = wj.dqwb(a2);

        if (zf.dy(a8, "4")) {

            if (e1.isInstallX5()) {

                final String[] name = new String[]{"url"};

                final String[] value = new String[]{url};
                gj.tz(X5BrowserActivity.class, name, value);

            } else {

                wl.fw(url);

            }

        } else {

            if (e1.isInstallX5()) {

                final String[] name = new String[]{"url"};

                final String[] value = new String[]{url};
                gj.tz(X5BrowserActivity.class, name, value);

            } else {

                e1.tsk_QR("找回密码", "如果您需要找回Typheye账户,请扫描下方二维码后在手机上操作.", url);

            }

        }

    }

    private void onTx2Click(View vw) {
        gj.gb();

    }

    private void onTx3Click(View vw) {
        go();

    }

    private void onWb5Click(View vw) {
        switch_pwdlogin = true;
        show(1);

    }

    private void onAn1Click(View vw) {
        go2qx = true;
        show(1);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);

        TextView f52e07ac8c = (TextView) findViewById(ay, vw, R.id.wb6);
        f52e07ac8c.setOnClickListener(wb6ClickListener);

        TextView ff56a8e7f6 = (TextView) findViewById(ay, vw, R.id.wb1);
        ff56a8e7f6.setOnClickListener(wb1ClickListener);

        TextView i0d2d4d9236 = (TextView) findViewById(ay, vw, R.id.wb3);
        i0d2d4d9236.setOnClickListener(wb3ClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        TextView i2a86c78b06 = (TextView) findViewById(ay, vw, R.id.wb5);
        i2a86c78b06.setOnClickListener(wb5ClickListener);

        Button i171ea34f1a = (Button) findViewById(ay, vw, R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
