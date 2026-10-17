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
    public AppRuntimeBridge e1;
    public TypheyeServiceBridge easy;
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
        e1 = new AppRuntimeBridge(this);
        easy = new TypheyeServiceBridge(this);
        setContentView(R.layout.typheye_login);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        String aa = AppPaths.appPath("settings/account/userx");

        String bb = AppPaths.appPath("settings/account/passx");

        if (open.cn.awg.pro.core.NativeFiles.exists(TypheyeLoginActivity.this, (String) (aa)) && !java.util.Objects.equals(open.cn.awg.pro.core.TextValues.trim(open.cn.awg.pro.core.NativeFiles.readText(TypheyeLoginActivity.this, (String) (aa))), "") && open.cn.awg.pro.core.NativeFiles.exists(TypheyeLoginActivity.this, (String) (bb)) && !java.util.Objects.equals(open.cn.awg.pro.core.TextValues.trim(open.cn.awg.pro.core.NativeFiles.readText(TypheyeLoginActivity.this, (String) (bb))), "")) {

            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeLoginActivity.this.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(open.cn.awg.pro.core.NativeFiles.readText(TypheyeLoginActivity.this, (String) (aa))));
            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeLoginActivity.this.getWindow().getDecorView(), R.id.bjk2)).setText(String.valueOf(open.cn.awg.pro.core.NativeFiles.readText(TypheyeLoginActivity.this, (String) (bb))));

        }
        go2();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.typheye_login_root, R.id.xdbj1, R.id.title_bar);

    }

    public void zt(int a) {

        final int i = a;
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                if (i == 0) {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeLoginActivity.this.getWindow().getDecorView(), R.id.wb5)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeLoginActivity.this.getWindow().getDecorView(), R.id.wb4)).setText(String.valueOf("扫描二维码完成登录"));

                } else if (i == 1) {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeLoginActivity.this.getWindow().getDecorView(), R.id.wb5)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(4, -1));
                    ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeLoginActivity.this.getWindow().getDecorView(), R.id.tx1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeLoginActivity.this.getWindow().getDecorView(), R.id.wb4)).setText(String.valueOf("已扫码等待用户操作"));

                } else if (i == 2) {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeLoginActivity.this.getWindow().getDecorView(), R.id.wb5)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(4, -1));
                    show(1);

                }

            }

        });

    }

    public void go2() {

        if (!st_go2) {

            st_go2 = true;
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    show(1);
                    open.cn.awg.pro.core.NativeActions.sleep(500);

                    String set2 = AppPaths.appPath("settings/f7/Enable");

                    String oback = "";

                    if (e1.yz()) {

                        try {

                            String ljf = open.cn.awg.pro.core.NativeFiles.readText(TypheyeLoginActivity.this, (String) (AppPaths.appPath("data/assets/a17")));

                            String url = e1.urlUnlockString(open.cn.awg.pro.core.NativeFiles.readText(TypheyeLoginActivity.this, (String) (AppPaths.appPath("settings/n1/url.inf"))));

                            if (open.cn.awg.pro.core.TextValues.endsWith(url, "/")) {

                            } else {

                                url = url + "/";

                            }

                            String send0 = "type=generate_login_request";

                            final String send = "/api.php?" + send0 + "&token=" + open.cn.awg.pro.core.TextValues.md5(send0);

                            String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url + send), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                            if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                                oback = "登录失败:连接服务器超时";
                                e1.tsk("提示", oback + url + send);
                                show(0);

                            } else {

                                String ce = e1.readJson(back, "code");

                                if (java.util.Objects.equals(ce, "200")) {

                                    try {

                                        org.json.JSONObject jo = (org.json.JSONObject) (Object) open.cn.awg.pro.core.JsonValues.parseObject(back);

                                        org.json.JSONObject json = jo;

                                        Object v_request_id = open.cn.awg.pro.core.JsonValues.value(json, "request_id");

                                        Object v_qr_code_url = open.cn.awg.pro.core.JsonValues.value(json, "qr_code_url");

                                        if (!java.util.Objects.equals(v_request_id, "") && !java.util.Objects.equals(v_qr_code_url, "")) {

                                            go2_xh(open.cn.awg.pro.core.TextValues.string(v_request_id), open.cn.awg.pro.core.TextValues.string(v_qr_code_url));

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
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

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

                    int pollFail = 0;

                    while (go2xh) {

                        open.cn.awg.pro.core.NativeActions.sleep(3000);

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

                        String url = e1.urlUnlockString(open.cn.awg.pro.core.NativeFiles.readText(TypheyeLoginActivity.this, (String) (AppPaths.appPath("settings/n1/url.inf"))));

                        if (open.cn.awg.pro.core.TextValues.endsWith(url, "/")) {

                        } else {

                            url = url + "/";

                        }

                        String send = "/api.php?type=check_login_request_status&request_id=" + v_request_id;

                        String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url + send), null, (String) ("utf-8"), null, false, null, open.cn.awg.pro.core.TextValues.intValue(15000, -1), open.cn.awg.pro.core.TextValues.intValue(15000, -1), null).bodyForHttp200();

                        if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                            // 单次超时不再直接判失败：网络抖动时继续轮询，连续 3 次失败才报错
                            pollFail++;

                            if (pollFail < 3) {

                                continue;

                            }

                            go2xh = false;
                            oback = "登录失败:连接服务器超时";
                            e1.tsk("提示", oback);
                            show(0);
                            break;

                        } else {

                            pollFail = 0;

                            String ce = e1.readJson(back, "code");

                            if (java.util.Objects.equals(ce, "200")) {

                                try {

                                    org.json.JSONObject jo = (org.json.JSONObject) (Object) open.cn.awg.pro.core.JsonValues.parseObject(back);

                                    org.json.JSONObject json0 = jo;

                                    final String v_status = open.cn.awg.pro.core.TextValues.string(open.cn.awg.pro.core.JsonValues.value(json0, "status"));

                                    if (java.util.Objects.equals(v_status, "approved")) {

                                        go2xh = false;
                                        zt(2);

                                        try {

                                            JSONObject json = JSONObject.parseObject(back);
                                            JSONObject info = json.getJSONObject("info");
                                            if (info != null) {
                                                j_info_cookie = nz(info.getString("cookie"));
                                                j_info_shuo = nz(info.getString("shuo"));
                                                j_info_nick = nz(info.getString("nick"));
                                                j_info_note = nz(info.getString("note"));
                                                j_info_uid = nz(info.getString("uid"));
                                                j_info_more = nz(info.getString("more"));
                                                j_info_email = nz(info.getString("email"));
                                            }
                                            // 兼容旧服务端：只给 login_url 时从 query 里取 cookie/uid
                                            if (j_info_cookie.isEmpty() || j_info_uid.isEmpty()) {
                                                String lu = nz(json.getString("login_url"));
                                                if (j_info_cookie.isEmpty()) {
                                                    j_info_cookie = pickQuery(lu, "cookie");
                                                }
                                                if (j_info_uid.isEmpty()) {
                                                    j_info_uid = pickQuery(lu, "uid");
                                                }
                                            }
                                        } catch (Throwable e) {

                                        }

                                        if (j_info_cookie.isEmpty() || j_info_uid.isEmpty()) {
                                            oback = "登录失败:服务器返回数据不完整";
                                            e1.tsk("提示", oback);
                                            show(0);
                                            break;
                                        }

                                        if (j_info_nick.isEmpty()) {
                                            j_info_nick = "用户" + j_info_uid;
                                        }

                                        if (j_info_note == null) {
                                            j_info_note = "";
                                        }

                                    e1.tsk("提示", "登录成功");
                                        open.cn.awg.pro.core.NativeFiles.writeText(TypheyeLoginActivity.this, (String) (set2), (String) (j_info_nick));
                                        open.cn.awg.pro.core.NativeFiles.writeText(TypheyeLoginActivity.this, (String) (set2s), (String) (j_info_nick));
                                        open.cn.awg.pro.core.NativeFiles.writeText(TypheyeLoginActivity.this, (String) (set3), (String) (j_info_shuo));
                                        open.cn.awg.pro.core.NativeFiles.writeText(TypheyeLoginActivity.this, (String) (set4), (String) (j_info_cookie));

                                        String b = open.cn.awg.pro.core.TextValues.md5("none");
                                        open.cn.awg.pro.core.NativeFiles.writeText(TypheyeLoginActivity.this, (String) (aa), (String) (j_info_uid));
                                        open.cn.awg.pro.core.NativeFiles.writeText(TypheyeLoginActivity.this, (String) (bb), (String) (b));
                                        open.cn.awg.pro.core.NativeFiles.writeText(TypheyeLoginActivity.this, (String) (aax), (String) (j_info_uid));
                                        open.cn.awg.pro.core.NativeFiles.writeText(TypheyeLoginActivity.this, (String) (bbx), (String) (b));
                                        easy.getPericon(j_info_uid);
                                        show(0);
                                        break;

                                    } else if (java.util.Objects.equals(v_status, "rejected")) {

                                        go2xh = false;
                                        oback = "登录失败:请求被拒绝";
                                        e1.tsk("提示", oback);
                                        show(0);
                                        break;

                                    } else if (java.util.Objects.equals(v_status, "pending")) {

                                        zt(0);

                                    } else if (java.util.Objects.equals(v_status, "waiting")) {

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

    private String nz(String v) {

        return v == null ? "" : open.cn.awg.pro.core.TextValues.trim(v);
    }

    private String pickQuery(String url, String key) {

        if (url == null || url.isEmpty() || key == null || key.isEmpty()) {
            return "";
        }

        int q = url.indexOf('?');
        if (q < 0) {
            return "";
        }

        String[] parts = url.substring(q + 1).split("&");
        for (String part : parts) {
            int eq = part.indexOf('=');
            if (eq > 0 && part.substring(0, eq).equals(key)) {
                return open.cn.awg.pro.core.TextValues.trim(android.net.Uri.decode(part.substring(eq + 1)));
            }
        }
        return "";
    }

    public void ewm(String url) {

        final String qr = url;
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                try {

                    ImageView imageView = findViewById(R.id.tx1);
                    Bitmap bitmap = com.demo.e4.createQRCodeBitmap(qr, 800, 800, "UTF-8", "H", "1");
                    imageView.setImageBitmap(bitmap);

                    ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeLoginActivity.this.getWindow().getDecorView(), R.id.tx1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                } catch (Throwable e) {

                    ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeLoginActivity.this.getWindow().getDecorView(), R.id.tx1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            }

        });

    }

    public void go() {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                show(1);
                open.cn.awg.pro.core.NativeActions.sleep(500);

                String set2 = AppPaths.appPath("settings/n1/set2.inf");

                String set3 = AppPaths.appPath("settings/n1/set3.inf");

                String set4 = AppPaths.appPath("data/easy/cookie");

                String set2s = AppPaths.appPath("settings/f11/set3.inf");

                String aa = AppPaths.appPath("settings/account/user");

                String bb = AppPaths.appPath("settings/account/pass");

                String aax = AppPaths.appPath("settings/account/userx");

                String bbx = AppPaths.appPath("settings/account/passx");

                String a = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeLoginActivity.this.getWindow().getDecorView(), R.id.bjk1)).getText().toString();
                a = open.cn.awg.pro.core.TextValues.trim(a);

                String b = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeLoginActivity.this.getWindow().getDecorView(), R.id.bjk2)).getText().toString();
                b = open.cn.awg.pro.core.TextValues.trim(b);

                if (java.util.Objects.equals(a, "") || java.util.Objects.equals(b, "")) {

                    e1.tsk("提示", "账户号码或密码不能为空");
                    show(0);

                } else {

                    open.cn.awg.pro.core.NativeActions.sleep(250);

                    String ljf = open.cn.awg.pro.core.NativeFiles.readText(TypheyeLoginActivity.this, (String) (AppPaths.appPath("data/assets/a17")));

                    String url = e1.urlUnlockString(open.cn.awg.pro.core.NativeFiles.readText(TypheyeLoginActivity.this, (String) (AppPaths.appPath("settings/n1/url.inf"))));

                    if (open.cn.awg.pro.core.TextValues.endsWith(url, "/")) {

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
                    a = open.cn.awg.pro.core.TextValues.trim(a);
                    b = open.cn.awg.pro.core.TextValues.trim(b);

                    String salt = open.cn.awg.pro.core.TextValues.md5(open.cn.awg.pro.core.TextValues.string(a));
                    salt = open.cn.awg.pro.core.TextValues.slice(salt, 0, 6);
                    salt = open.cn.awg.pro.core.TextValues.md5(salt);
                    salt = open.cn.awg.pro.core.TextValues.slice(salt, 0, 5);
                    salt = open.cn.awg.pro.core.TextValues.md5(salt);
                    b = open.cn.awg.pro.core.TextValues.md5(b) + salt;

                    String sjzx = open.cn.awg.pro.core.NativeDevice.formatDate(4);

                    String send0 = "type=login&username=" + open.cn.awg.pro.core.TextValues.string(a) + "&password=" + open.cn.awg.pro.core.TextValues.string(b) + "&time=" + sjzx;

                    final String send = "/api.php?" + send0 + "&token=" + open.cn.awg.pro.core.TextValues.md5(send0);

                    if (e1.yz()) {

                        final String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url + send), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                        if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                            e1.tsk("提示", "登录失败，无法连接到服务器");
                            show(0);

                        } else {

                            try {

                                JSONObject json = JSONObject.parseObject(back);
                                j_code = json.getString("code");
                                j_msg = json.getString("msg");
                                try {
                                    JSONObject info = json.getJSONObject("info");
                                    if (info != null) {
                                        j_info_cookie = nz(info.getString("cookie"));
                                        j_info_shuo = nz(info.getString("shuo"));
                                        j_info_nick = nz(info.getString("nick"));
                                        j_info_note = nz(info.getString("note"));
                                        j_info_uid = nz(info.getString("uid"));
                                        j_info_more = nz(info.getString("more"));
                                        j_info_email = nz(info.getString("email"));
                                    }
                                } catch (Throwable e) {

                                }

                                if (java.util.Objects.equals(j_code, "200")
                                        && (j_info_cookie.isEmpty() || j_info_uid.isEmpty())) {
                                    e1.tsk("提示", "登录失败:服务器返回数据不完整");
                                    show(0);

                                } else if (java.util.Objects.equals(j_code, "200")) {

                                    if (j_info_nick.isEmpty()) {
                                        j_info_nick = "用户" + j_info_uid;
                                    }

                                    e1.tsk("提示", "登录成功");
                                    easy.getPericon(j_info_uid);
                                    open.cn.awg.pro.core.NativeFiles.writeText(TypheyeLoginActivity.this, (String) (set2), (String) (j_info_nick));
                                    open.cn.awg.pro.core.NativeFiles.writeText(TypheyeLoginActivity.this, (String) (set2s), (String) (j_info_nick));
                                    open.cn.awg.pro.core.NativeFiles.writeText(TypheyeLoginActivity.this, (String) (set3), (String) (j_info_shuo));
                                    open.cn.awg.pro.core.NativeFiles.writeText(TypheyeLoginActivity.this, (String) (set4), (String) (j_info_cookie));
                                    open.cn.awg.pro.core.NativeFiles.writeText(TypheyeLoginActivity.this, (String) (aa), (String) (j_info_uid));
                                    open.cn.awg.pro.core.NativeFiles.writeText(TypheyeLoginActivity.this, (String) (bb), (String) (b));
                                    open.cn.awg.pro.core.NativeFiles.writeText(TypheyeLoginActivity.this, (String) (aax), (String) (j_info_uid));
                                    open.cn.awg.pro.core.NativeFiles.writeText(TypheyeLoginActivity.this, (String) (bbx), (String) (b));
                                    open.cn.awg.pro.core.NativeActions.finish(TypheyeLoginActivity.this);

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

        final android.widget.LinearLayout xxbj1 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeLoginActivity.this.getWindow().getDecorView(), R.id.xxbj1));

        final android.widget.LinearLayout xxbj2 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeLoginActivity.this.getWindow().getDecorView(), R.id.xxbj2));

        final android.widget.LinearLayout xxbj4 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeLoginActivity.this.getWindow().getDecorView(), R.id.xxbj4));
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                if (ii == 0) {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeLoginActivity.this.getWindow().getDecorView(), R.id.wb5)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(4, -1));
                    xxbj2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    xxbj4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    xxbj1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    open.cn.awg.pro.core.NativeActions.finish(TypheyeLoginActivity.this);

                } else if (ii == 1) {

                    xxbj1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    xxbj2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    xxbj4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                } else if (ii == 2) {

                    xxbj1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    xxbj2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    xxbj4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                } else if (ii == 3) {

                    xxbj1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    xxbj2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    xxbj4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeLoginActivity.this.getWindow().getDecorView(), R.id.qtgd1));

        qtgd1.setOnGenericMotionListener(new View.OnGenericMotionListener() {
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

        qtgd1.requestFocus();

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

        String a8 = open.cn.awg.pro.core.NativeFiles.readText(TypheyeLoginActivity.this, (String) (a2));

        if (java.util.Objects.equals(a8, "4")) {

            if (e1.isInstallX5()) {

                final String[] name = new String[]{"url"};

                final String[] value = new String[]{url};
                open.cn.awg.pro.core.NativeActions.launch(TypheyeLoginActivity.this, (Class<?>) (X5BrowserActivity.class), (String[]) (name), (Object[]) (value));

            } else {

                open.cn.awg.pro.core.NativeActions.openUrl(TypheyeLoginActivity.this, url);

            }

        } else {

            if (e1.isInstallX5()) {

                final String[] name = new String[]{"url"};

                final String[] value = new String[]{url};
                open.cn.awg.pro.core.NativeActions.launch(TypheyeLoginActivity.this, (Class<?>) (X5BrowserActivity.class), (String[]) (name), (Object[]) (value));

            } else {

                e1.tsk_QR("账户注册", "如果您需要注册Typheye账户,请扫描下方二维码后在手机上操作.", url);

            }

        }

    }

    private void onWb3Click(View vw) {

        String url = "https://service.typheye.cn/site/user/center/find/";

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a8 = open.cn.awg.pro.core.NativeFiles.readText(TypheyeLoginActivity.this, (String) (a2));

        if (java.util.Objects.equals(a8, "4")) {

            if (e1.isInstallX5()) {

                final String[] name = new String[]{"url"};

                final String[] value = new String[]{url};
                open.cn.awg.pro.core.NativeActions.launch(TypheyeLoginActivity.this, (Class<?>) (X5BrowserActivity.class), (String[]) (name), (Object[]) (value));

            } else {

                open.cn.awg.pro.core.NativeActions.openUrl(TypheyeLoginActivity.this, url);

            }

        } else {

            if (e1.isInstallX5()) {

                final String[] name = new String[]{"url"};

                final String[] value = new String[]{url};
                open.cn.awg.pro.core.NativeActions.launch(TypheyeLoginActivity.this, (Class<?>) (X5BrowserActivity.class), (String[]) (name), (Object[]) (value));

            } else {

                e1.tsk_QR("找回密码", "如果您需要找回Typheye账户,请扫描下方二维码后在手机上操作.", url);

            }

        }

    }

    private void onTx2Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(TypheyeLoginActivity.this);

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

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);

        TextView f52e07ac8c = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb6);
        f52e07ac8c.setOnClickListener(wb6ClickListener);

        TextView ff56a8e7f6 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb1);
        ff56a8e7f6.setOnClickListener(wb1ClickListener);

        TextView i0d2d4d9236 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb3);
        i0d2d4d9236.setOnClickListener(wb3ClickListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        TextView i2a86c78b06 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb5);
        i2a86c78b06.setOnClickListener(wb5ClickListener);

        Button i171ea34f1a = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
