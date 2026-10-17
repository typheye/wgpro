/**
 ******************************************************************************
 * @file    TypheyeAccountActivity.java
 * @author  Typheye
 * @brief   Typheye account center screen.
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
import android.content.Intent;
import android.os.Bundle;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.widget.SwitchCompat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import com.alibaba.fastjson.JSONObject;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.listener.UploadProgressListener;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.browser.X5BrowserActivity;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.core.UriPathResolver;
import open.cn.awg.pro.file.FileManagerActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.viewer.ImageViewerActivity;

public class TypheyeAccountActivity extends BaseAwgActivity {

    public final TypheyeAccountActivity lei = this, 类 = this;
    public int mode = 0;
    public boolean isAcc = false;
    public boolean state_upload = false;
    public String upload_back = "";
    public AppRuntimeBridge e1;
    public TypheyeServiceBridge easy;
    public String path = AppPaths.appPath("cache/user/upload/path");
    private final View.OnClickListener tx6ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx6Click(vw);
        }

    };
    private final View.OnClickListener tx5ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx5Click(vw);
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
    private final View.OnTouchListener xxbj15TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj15Touch(vw, me);
        }

    };
    private final View.OnClickListener tx8ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx8Click(vw);
        }

    };
    private final View.OnClickListener an2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn2Click(vw);
        }

    };
    private final View.OnClickListener an1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn1Click(vw);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj5ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj5Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj6ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj6Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj10ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj10Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj9ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj9Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj8ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj8Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj2Click(vw);
        }

    };
    private final CompoundButton.OnCheckedChangeListener kg1CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onKg1CheckedChanged(vw, ic);
        }

    };
;
    private final View.OnClickListener xdbj4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj4Click(vw);
        }

    };
    private final View.OnClickListener xxbj8ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj8Click(vw);
        }

    };
    private final View.OnClickListener wb11ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb11Click(vw);
        }

    };
    private final View.OnClickListener wb10ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb10Click(vw);
        }

    };
    private final View.OnClickListener xdbj7ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj7Click(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        e1 = new AppRuntimeBridge(this);
        easy = new TypheyeServiceBridge(this);
        setContentView(R.layout.typheye_account);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.typheye_account_root, R.id.xdbj1, R.id.title_bar);

        android.widget.Spinner xlcd1 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xlcd1));

        String[] sz = new String[]{"应用内置选择器", "系统默认选择器"};
        open.cn.awg.pro.core.NativeViewValues.spinner(xlcd1, sz);

    }

    public void jz() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String set1 = AppPaths.appPath("settings/n1/set1.inf");

                android.widget.CompoundButton kg1 = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.kg1));

                if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (set1)), "true")) {

                    kg1.setChecked(java.lang.Boolean.TRUE.equals(true));

                }
                zh();

            }

        });

    }

    public void zh() {

        String te1 = "";

        String te2 = "";

        String te3 = "";

        String a = AppPaths.appPath("settings/account/user");

        String b = AppPaths.appPath("settings/account/pass");

        String c = AppPaths.appPath("settings/account/isAcc");

        String set2 = AppPaths.appPath("settings/n1/set2.inf");

        String set3 = AppPaths.appPath("settings/n1/set3.inf");

        if (!open.cn.awg.pro.core.NativeFiles.exists(TypheyeAccountActivity.this, (String) (a)) || !open.cn.awg.pro.core.NativeFiles.exists(TypheyeAccountActivity.this, (String) (b))) {

            isAcc = false;
            open.cn.awg.pro.core.NativeFiles.delete(TypheyeAccountActivity.this, (String) (c), false);
            te1 = "请先登录您的账户后再操作";
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb5)).setText(String.valueOf(te1));
            te2 = "请先登录您的账户后再操作";
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb8)).setText(String.valueOf(te2));
            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xdbj2)).setVisibility(android.view.View.GONE);
            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xdbj5)).setVisibility(android.view.View.GONE);
            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xdbj6)).setVisibility(android.view.View.GONE);
            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xdbj8)).setVisibility(android.view.View.GONE);
            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xdbj9)).setVisibility(android.view.View.GONE);
            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xdbj10)).setVisibility(android.view.View.GONE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb10)).setText(String.valueOf("未登录"));
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb11)).setText(String.valueOf(""));
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb6)).setText(String.valueOf(""));
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb11)).setVisibility(android.view.View.GONE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb6)).setVisibility(android.view.View.GONE);
            ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.tx4)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.tx7)).setVisibility(android.view.View.GONE);

        } else {

            isAcc = true;
            open.cn.awg.pro.core.NativeFiles.writeText(TypheyeAccountActivity.this, (String) (c), (String) ("0"));
            te1 = open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (set2));
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb5)).setText(String.valueOf(te1));
            te2 = open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (set3));

            if (java.util.Objects.equals(open.cn.awg.pro.core.TextValues.trim(te2), "")) {

                te2 = "暂未设置";

            }
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb8)).setText(String.valueOf(te2));
            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xdbj2)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xdbj5)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xdbj6)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xdbj8)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xdbj9)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xdbj10)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb10)).setText(String.valueOf(te1));
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb11)).setText(String.valueOf("UID:" + open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (a))));
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb6)).setText(String.valueOf("正"));
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb11)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb6)).setVisibility(android.view.View.GONE);
            ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.tx4)).setVisibility(android.view.View.VISIBLE);
            easy.getPericon(open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (a)));

            if (!java.util.Objects.equals(easy.getPerIcon(open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (a))), "null")) {

                open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.tx7)), easy.getPerIconPath(open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (a))));
                ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.tx7)).setVisibility(android.view.View.VISIBLE);

            }

        }
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb18)).setText(String.valueOf("点击更改账户头像"));
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb19)).setText(String.valueOf("点击更改账户昵称"));
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb22)).setText(String.valueOf("点击更改账户简介"));

    }

    public void qh() {

        String set3 = AppPaths.appPath("settings/n1/set3.inf");

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj1)).getVisibility() == android.view.View.VISIBLE) {

            String z = "";
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj15)).setVisibility(android.view.View.GONE);

            if (mode == 1) {

                z = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb5)).getText().toString();
                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("账户昵称"));
                ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.bjk1)).setHint(String.valueOf("请输入昵称"));

            } else if (mode == 2) {

                z = open.cn.awg.pro.core.TextValues.trim(open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (set3)));
                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("账户简介"));
                ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.bjk1)).setHint(String.valueOf("请输入简介"));

            } else if (mode == 3) {

            }
            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(z));

        } else {

            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(android.view.View.GONE);
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj15)).setVisibility(android.view.View.GONE);
            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(""));
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("Typheye账户"));

        }

    }

    public void gx() {

        final String set5 = AppPaths.appPath("settings/account/user");

        final String set2 = AppPaths.appPath("settings/n1/set2.inf");

        final String set3 = AppPaths.appPath("settings/n1/set3.inf");

        final String set4 = AppPaths.appPath("data/easy/cookie");

        final String set2s = AppPaths.appPath("settings/f11/set3.inf");

        String b = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.bjk1)).getText().toString();
        b = open.cn.awg.pro.core.TextValues.trim(b);

        if (mode == 1) {

            if (java.util.Objects.equals(b, "")) {

                e1.tsk("提示", "昵称不符合规范");

            } else {

                final String nick = b;
                open.cn.awg.pro.core.NativeActions.start(new Thread() {

                    public void run() {

                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb5)).setText(String.valueOf("加载中..."));
                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb19)).setText(String.valueOf("加载中..."));

                            }

                        });

                        String url = e1.urlUnlockString(open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (AppPaths.appPath("settings/n1/url.inf"))));

                        if (open.cn.awg.pro.core.TextValues.endsWith(url, "/")) {

                        } else {

                            url = url + "/";

                        }

                        String j_code = "";

                        String j_msg = "";

                        String a = open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (set4));

                        String uid = open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (set5));

                        String sjzx = open.cn.awg.pro.core.NativeDevice.formatDate(4);

                        String send0 = "type=set_nick&uid=" + uid + "&cookie=" + a + "&value=" + open.cn.awg.pro.core.TextValues.string(nick) + "&time=" + sjzx;

                        final String send = "/api.php?" + send0 + "&token=" + open.cn.awg.pro.core.TextValues.md5(send0);

                        final String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url + send), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                        if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                            e1.tsk("提示", "修改失败，无法连接到服务器");
                            jz();

                        } else {

                            try {

                                JSONObject json = JSONObject.parseObject(back);
                                j_code = json.getString("code");
                                j_msg = json.getString("msg");

                                if (java.util.Objects.equals(j_code, "200")) {

                                    open.cn.awg.pro.core.NativeFiles.writeText(TypheyeAccountActivity.this, (String) (set2), (String) (nick));
                                    open.cn.awg.pro.core.NativeFiles.writeText(TypheyeAccountActivity.this, (String) (set2s), (String) (nick));
                                    e1.tsk("提示", "昵称设置成功");
                                    jz();

                                } else {

                                    e1.tsk("提示", "修改失败\n" + j_msg);
                                    jz();

                                }

                            } catch (Throwable __$_e__) {

                                e1.tsk("提示", "修改失败，出现异常");
                                jz();

                            }

                        }

                    }

                });

            }

        } else if (mode == 2) {

            if (false) {

            } else {

                final String shuo = b;
                open.cn.awg.pro.core.NativeActions.start(new Thread() {

                    public void run() {

                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb22)).setText(String.valueOf("加载中..."));

                            }

                        });

                        String url = e1.urlUnlockString(open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (AppPaths.appPath("settings/n1/url.inf"))));

                        if (open.cn.awg.pro.core.TextValues.endsWith(url, "/")) {

                        } else {

                            url = url + "/";

                        }

                        String j_code = "";

                        String j_msg = "";

                        String a = open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (set4));

                        String uid = open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (set5));

                        String sjzx = open.cn.awg.pro.core.NativeDevice.formatDate(4);

                        String send0 = "type=set_shuo&uid=" + uid + "&cookie=" + a + "&value=" + open.cn.awg.pro.core.TextValues.string(shuo) + "&time=" + sjzx;

                        final String send = "/api.php?" + send0 + "&token=" + open.cn.awg.pro.core.TextValues.md5(send0);

                        final String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url + send), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                        if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                            e1.tsk("提示", "修改失败，无法连接到服务器");
                            jz();

                        } else {

                            try {

                                JSONObject json = JSONObject.parseObject(back);
                                j_code = json.getString("code");
                                j_msg = json.getString("msg");

                                if (java.util.Objects.equals(j_code, "200")) {

                                    open.cn.awg.pro.core.NativeFiles.writeText(TypheyeAccountActivity.this, (String) (set3), (String) (shuo));
                                    e1.tsk("提示", "简介设置成功");
                                    jz();

                                } else {

                                    e1.tsk("提示", "修改失败\n" + j_msg);
                                    jz();

                                }

                            } catch (Throwable __$_e__) {

                                e1.tsk("提示", "修改失败，出现异常");
                                jz();

                            }

                        }

                    }

                });

            }

        } else if (mode == 3) {

            if (java.util.Objects.equals(b, "")) {

                e1.tsk("提示", "秘钥不符合规范");

            } else {

                e1.tsk("提示", "秘钥设置成功");

            }

        }
        jz();
        qh();

    }

    public void upload(Object a) {

        if (!state_upload) {

            state_upload = true;

            final String a3 = AppPaths.appPath("settings/account/user");

            final String path_ = open.cn.awg.pro.core.NativeFiles.resolvePath(TypheyeAccountActivity.this, (String) (a));

            final String[] path = new String[]{path_};

            final android.widget.TextView wb18 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb18));
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            wb18.setText(String.valueOf("加载中..."));

                        }

                    });
                    upload_back = "";

                    if (e1.yz()) {

                        if (java.util.Objects.equals(path_, "") || !open.cn.awg.pro.core.NativeFiles.exists(TypheyeAccountActivity.this, (String) (path_))) {

                            jz();
                            e1.tsk("提示", "文件路径无效");

                        } else {

                            final String url = "https://service.typheye.cn/src/up.php?uid=" + open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (a3));

                            String fn__ = "null";

                            String[] fn_ = e1.fileinfo("/", path[0]);

                            if (java.util.Objects.equals(fn_[0], "true")) {

                                fn__ = fn_[3];

                            }

                            final String file_name = fn__;

                            final long file_size = open.cn.awg.pro.core.NativeFiles.size(TypheyeAccountActivity.this, (String) (path_));
                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    try {

                                        HttpRequest.TIME_OUT_DURATION = 300;
                                        HttpRequest.build(lei, url)
                                                .addParameter("name", file_name)
                                                .addParameter("size", file_size)
                                                .addParameter("file", open.cn.awg.pro.core.NativeFiles.file(TypheyeAccountActivity.this, (String) (path_)))
                                                .setUploadProgressListener(new UploadProgressListener() {
                                                    @Override
                                                    public void onUpload(float percentage, long current, long total, boolean done) {

                                                        long now = current / total * 100;
                                                        wb18.setText(String.valueOf("已上传" + open.cn.awg.pro.core.TextValues.string(now) + "%"));
                                                    }
                                                })
                                                .setResponseListener(new ResponseListener() {
                                                    @Override
                                                    public void onResponse(String response, Exception error) {
                                                        uploadResult(error, response);
                                                    }
                                                })
                                                .doPost();

                                    } catch (Throwable e) {

                                        jz();
                                        e1.tsk("提示", "上传失败(-1)");

                                    }

                                }

                            });

                        }

                    } else {

                        jz();
                        e1.tsk("提示", "上传失败\n连接服务器超时(-2)");

                    }
                    state_upload = false;

                }

            });

        } else {

        }

    }

    public void uploadResult(Exception error, Object upload_back) {

        Object back = upload_back;

        if (!java.util.Objects.equals(open.cn.awg.pro.core.TextValues.string(error), null)) {

            jz();
            e1.tsk("提示", "上传失败\n" + open.cn.awg.pro.core.TextValues.string(error));

        } else {

            if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                jz();
                e1.tsk("提示", "上传失败\n连接服务器超时(-1)");

            } else {

                jz();
                e1.tsk("提示", back);

            }

        }

    }

    public void onRestart() {
        super.onRestart();
            applyWindowModeFromSettings();

    }

    public void onStart() {
        super.onStart();
            applyWindowModeFromSettings();

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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
        jz();

        if (open.cn.awg.pro.core.NativeFiles.exists(TypheyeAccountActivity.this, (String) (path)) && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (path)), "") && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (path)), "null")) {

            upload(open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (path)));
            open.cn.awg.pro.core.NativeFiles.delete(TypheyeAccountActivity.this, (String) (path), false);

        }

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

            if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "Typheye账户")) {

                open.cn.awg.pro.core.NativeActions.finish(TypheyeAccountActivity.this);

            }

        }
        return false;

    }

    public void onActivityResult(int re, int rc, Intent it) {
        super.onActivityResult(re, rc, it);

        if (re == 1) {

            if (rc == -1) {

                String realPath = null;

                try {

                    realPath = UriPathResolver.getByIntent(lei, it);

                } catch (Throwable e) {

                }

                if (!java.util.Objects.equals(realPath, null)) {

                    realPath = open.cn.awg.pro.core.NativeFiles.resolvePath(TypheyeAccountActivity.this, (String) (realPath));
                    upload(realPath);

                }

            }

        }

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onTitleBarClick(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "Typheye账户")) {

            open.cn.awg.pro.core.NativeActions.finish(TypheyeAccountActivity.this);

        }

    }

    private void onXdbj7Click(View vw) {

        if (!isAcc) {

            open.cn.awg.pro.core.NativeActions.launch(TypheyeAccountActivity.this, (Class<?>) (TypheyeLoginActivity.class));

        } else {

            String a = AppPaths.appPath("settings/account/user");

            if (!java.util.Objects.equals(easy.getPerIcon(open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (a))), "null")) {

                final String[] name = new String[]{"url"};

                final String[] value = new String[]{easy.getPerIconPath(open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (a)))};
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        open.cn.awg.pro.core.NativeActions.launch(TypheyeAccountActivity.this, (Class<?>) (ImageViewerActivity.class), (String[]) (name), (Object[]) (value));

                    }

                });

            } else {

                e1.tsk("账户头像", "您还未上传头像");

            }

        }

    }

    private void onWb10Click(View vw) {

        if (!isAcc) {

            open.cn.awg.pro.core.NativeActions.launch(TypheyeAccountActivity.this, (Class<?>) (TypheyeLoginActivity.class));

        } else {

            if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb5)).getText().toString(), "加载中...")) {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            } else {

                e1.tsk("账户昵称", ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb10)).getText().toString());

            }

        }

    }

    private void onWb11Click(View vw) {
        e1.tsk("账户UID", ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb11)).getText().toString());

    }

    private void onXxbj8Click(View vw) {

        if (!isAcc) {

            open.cn.awg.pro.core.NativeActions.launch(TypheyeAccountActivity.this, (Class<?>) (TypheyeLoginActivity.class));

        } else {

        }

    }

    private void onXdbj4Click(View vw) {

        if (!isAcc) {

            e1.tsk("提示", "请先登录您的账户后再操作");

        } else {

            if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb8)).getText().toString(), "加载中...")) {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            } else {

                e1.tsk("账户简介", ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb8)).getText().toString());

            }

        }

    }



    private void onKg1CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/n1/set1.inf");

        if (ic) {

            open.cn.awg.pro.core.NativeFiles.writeText(TypheyeAccountActivity.this, (String) (set), (String) ("true"));

        } else {

            open.cn.awg.pro.core.NativeFiles.writeText(TypheyeAccountActivity.this, (String) (set), (String) ("false"));

        }

    }

    private void onXdbj2Click(View vw) {

        android.widget.CompoundButton kg = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.kg1));

        if (!kg.isChecked()) {

            kg.setChecked(java.lang.Boolean.TRUE.equals(true));

        } else {

            kg.setChecked(java.lang.Boolean.TRUE.equals(false));

        }

    }



    private void onXdbj8Click(View vw) {

        if (!isAcc) {

            e1.tsk("提示", "请先登录您的账户后再操作");

        } else {

            if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb18)).getText().toString(), "点击更改账户头像")) {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("账户头像"));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(android.view.View.GONE);
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.GONE);
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj15)).setVisibility(android.view.View.VISIBLE);

                    }

                });

            } else {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            }

        }

    }



    private void onXdbj9Click(View vw) {

        if (!isAcc) {

            e1.tsk("提示", "请先登录您的账户后再操作");

        } else {

            if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb19)).getText().toString(), "加载中...")) {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            } else {

                mode = 1;
                qh();

            }

        }

    }



    private void onXdbj10Click(View vw) {

        if (!isAcc) {

            e1.tsk("提示", "请先登录您的账户后再操作");

        } else {

            if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.wb22)).getText().toString(), "加载中...")) {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            } else {

                mode = 2;
                qh();

            }

        }

    }



    private void onXdbj6Click(View vw) {

        if (!isAcc) {

            e1.tsk("提示", "请先登录您的账户后再操作");

        } else {

            if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj9)).getVisibility() == android.view.View.VISIBLE) {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("我的账户"));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.GONE);
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);

                    }

                });

            } else {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("提示"));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);

                    }

                });

            }

        }

    }



    private void onXdbj5Click(View vw) {

        if (!isAcc) {

            e1.tsk("提示", "请先登录您的账户后再操作");

        } else {

            String url = "https://service.typheye.cn/site/user/center/delete/";

            final String a2 = AppPaths.appPath("settings/a3.inf");

            String a8 = open.cn.awg.pro.core.NativeFiles.readText(TypheyeAccountActivity.this, (String) (a2));

            if (java.util.Objects.equals(a8, "4")) {

                if (e1.isInstallX5()) {

                    final String[] name = new String[]{"url"};

                    final String[] value = new String[]{url};
                    open.cn.awg.pro.core.NativeActions.launch(TypheyeAccountActivity.this, (Class<?>) (X5BrowserActivity.class), (String[]) (name), (Object[]) (value));

                } else {

                    open.cn.awg.pro.core.NativeActions.openUrl(TypheyeAccountActivity.this, url);

                }

            } else {

                if (e1.isInstallX5()) {

                    final String[] name = new String[]{"url"};

                    final String[] value = new String[]{url};
                    open.cn.awg.pro.core.NativeActions.launch(TypheyeAccountActivity.this, (Class<?>) (X5BrowserActivity.class), (String[]) (name), (Object[]) (value));

                } else {

                    e1.tsk_QR("账户注销", "如果您需要注销Typheye账户,请扫描下方二维码后在手机上操作.", url);

                }

            }

        }

    }



    private void onTx1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(TypheyeAccountActivity.this);

    }

    private void onAn1Click(View vw) {

        android.widget.Spinner xlcd1 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xlcd1));

        if (xlcd1.getLastVisiblePosition() == 1) {

            try {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                lei.startActivityForResult(intent, 1);

            } catch (Throwable e) {

                e1.tsk("提示", "此方式存在异常，暂时无法使用");

            }

        } else {

            try {

                

                if (open.cn.awg.pro.core.NativeActions.hasPermission(TypheyeAccountActivity.this, "android.permission.WRITE_EXTERNAL_STORAGE")) {

                    final String choose2 = AppPaths.appPath("cache/file/choose2");
                    open.cn.awg.pro.core.NativeFiles.delete(TypheyeAccountActivity.this, (String) (path), false);

                    int sdk = android.os.Build.VERSION.SDK_INT;

                    String ftitle = AppPaths.appPath("cache/file/title");
                    open.cn.awg.pro.core.NativeFiles.writeText(TypheyeAccountActivity.this, (String) (ftitle), (String) ("选择头像"));
                    open.cn.awg.pro.core.NativeFiles.writeText(TypheyeAccountActivity.this, (String) (choose2), (String) ("h1"));

                    if (sdk < 30) {

                        String lj = open.cn.awg.pro.core.NativeFiles.resolvePath(TypheyeAccountActivity.this, (String) ("%"));

                        final String[] name = new String[]{"url", "root"};

                        final String[] value = new String[]{lj, lj};
                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                open.cn.awg.pro.core.NativeActions.launch(TypheyeAccountActivity.this, (Class<?>) (FileManagerActivity.class), (String[]) (name), (Object[]) (value));

                            }

                        });

                    } else {

                        String lj = "/storage/emulated/0/";

                        final String[] name = new String[]{"url", "root"};

                        final String[] value = new String[]{lj, lj};
                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                open.cn.awg.pro.core.NativeActions.launch(TypheyeAccountActivity.this, (Class<?>) (FileManagerActivity.class), (String[]) (name), (Object[]) (value));

                            }

                        });

                    }

                } else {

                    e1.tsk("提示", "您未授权应用\"修改或删除您共享存储空间中的内容\"，请先前往设置授权后再使用");

                }

            } catch (Throwable e) {

                e1.upload_error(e, "n1.*");

            }

        }
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("Typheye账户"));
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(android.view.View.GONE);
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.GONE);
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj15)).setVisibility(android.view.View.GONE);

            }

        });

    }

    private void onAn2Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("Typheye账户"));
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(android.view.View.GONE);
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.GONE);
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj15)).setVisibility(android.view.View.GONE);

            }

        });

    }

    private void onTx8Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(TypheyeAccountActivity.this);

    }

    private boolean onXxbj15Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onTx2Click(View vw) {
        qh();

    }

    private void onTx3Click(View vw) {
        gx();

    }

    private void onTx5Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj9)).getVisibility() == android.view.View.VISIBLE) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("Typheye账户"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);

                }

            });

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("提示"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);

                }

            });

        }

    }

    private void onTx6Click(View vw) {

        String a = AppPaths.appPath("settings/account/user");

        String b = AppPaths.appPath("settings/account/pass");

        String c = AppPaths.appPath("settings/account/userx");

        String d = AppPaths.appPath("settings/account/passx");
        open.cn.awg.pro.core.NativeFiles.delete(TypheyeAccountActivity.this, (String) (a), false);
        open.cn.awg.pro.core.NativeFiles.delete(TypheyeAccountActivity.this, (String) (b), false);
        open.cn.awg.pro.core.NativeFiles.delete(TypheyeAccountActivity.this, (String) (c), false);
        open.cn.awg.pro.core.NativeFiles.delete(TypheyeAccountActivity.this, (String) (d), false);
        jz();

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj9)).getVisibility() == android.view.View.VISIBLE) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("Typheye账户"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);

                }

            });

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("提示"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TypheyeAccountActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);

                }

            });

        }

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        ImageView i11aeec1890 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx4);

        ImageView i3621e3f4a1 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx7);

        RelativeLayout e585d94488 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj7);
        e585d94488.setOnClickListener(xdbj7ClickListener);

        TextView f52e07ac8c = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb6);

        TextView i81652f77b6 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb10);
        i81652f77b6.setOnClickListener(wb10ClickListener);

        TextView i42afe4289f = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb11);
        i42afe4289f.setOnClickListener(wb11ClickListener);

        LinearLayout i3f6c9f386b = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj8);
        i3f6c9f386b.setOnClickListener(xxbj8ClickListener);

        TextView fc1598ee09 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb8);

        RelativeLayout i2202ede8ff = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);

        SwitchCompat i1bca89f073 = (SwitchCompat) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.kg1);
        i1bca89f073.setOnCheckedChangeListener(kg1CheckedChangeListener);

        RelativeLayout i3167b45174 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);

        RelativeLayout fab146d7a5 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj8);
        fab146d7a5.setOnClickListener(xdbj8ClickListener);

        RelativeLayout i5a93676674 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj9);
        i5a93676674.setOnClickListener(xdbj9ClickListener);

        RelativeLayout i5b34eede7f = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj10);
        i5b34eede7f.setOnClickListener(xdbj10ClickListener);

        RelativeLayout i171cedf1eb = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj6);
        i171cedf1eb.setOnClickListener(xdbj6ClickListener);

        RelativeLayout c0d3e00d2b = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj5);
        c0d3e00d2b.setOnClickListener(xdbj5ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        Button i171ea34f1a = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        Button i0b66a667b1 = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an2);
        i0b66a667b1.setOnClickListener(an2ClickListener);

        ImageView cb1d655d23 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx8);
        cb1d655d23.setOnClickListener(tx8ClickListener);

        LinearLayout i38cf17850d = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj15);
        i38cf17850d.setOnTouchListener(xxbj15TouchListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i4a4248a0f2 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx5);
        i4a4248a0f2.setOnClickListener(tx5ClickListener);

        ImageView i6f2c7751cf = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx6);
        i6f2c7751cf.setOnClickListener(tx6ClickListener);

        NestedScrollView a6dab51133 = (NestedScrollView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.qtgd1);

        __layoutIsLoaded(ay, vw);
    }
}
