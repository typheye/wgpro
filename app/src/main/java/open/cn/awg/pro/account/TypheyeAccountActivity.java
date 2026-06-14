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


import open.cn.awg.pro.core.IappCompat;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.SwitchCompat;
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
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public TypheyeServiceBridge easy = new TypheyeServiceBridge(_APPINFO);
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
    private final View.OnLongClickListener xdbj5LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj5LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj5ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj5Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj6LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj6LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj6ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj6Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj10LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj10LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj10ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj10Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj9LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj9LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj9ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj9Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj8LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj8LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj8ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj8Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj2LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj2LongClick(vw);
        }

    };
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
    private final View.OnLongClickListener xdbj4LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj4LongClick(vw);
        }

    };
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
        setContentView(R.layout.typheye_account);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.typheye_account_root, R.id.xdbj1, R.id.title_bar);

        i.runlibrary.app.v.xlcd xlcd1 = st.xlcd(R.id.xlcd1);

        String[] sz = new String[]{"应用内置选择器", "系统默认选择器"};
        xlcd1.csh(sz);

    }

    public void jz() {
        gj.jmxc(new Runnable() {

            public void run() {

                String set1 = AppPaths.appPath("settings/n1/set1.inf");

                i.runlibrary.app.v.kg kg1 = st.kg(R.id.kg1);

                if (zf.dy(wj.dqwb(set1), "true")) {

                    kg1.xzzt(true);

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

        if (!wj.cz(a) || !wj.cz(b)) {

            isAcc = false;
            wj.sc(c);
            te1 = "请先登录您的账户后再操作";
            st.wb(R.id.wb5).zf(te1);
            te2 = "请先登录您的账户后再操作";
            st.wb(R.id.wb8).zf(te2);
            st.xdbj(R.id.xdbj2).kjd(8);
            st.xdbj(R.id.xdbj5).kjd(8);
            st.xdbj(R.id.xdbj6).kjd(8);
            st.xdbj(R.id.xdbj8).kjd(8);
            st.xdbj(R.id.xdbj9).kjd(8);
            st.xdbj(R.id.xdbj10).kjd(8);
            st.wb(R.id.wb10).zf("未登录");
            st.wb(R.id.wb11).zf("");
            st.wb(R.id.wb6).zf("");
            st.wb(R.id.wb11).kjd(8);
            st.wb(R.id.wb6).kjd(8);
            st.tx(R.id.tx4).kjd(0);
            st.tx(R.id.tx7).kjd(8);

        } else {

            isAcc = true;
            wj.xrwb(c, "0");
            te1 = wj.dqwb(set2);
            st.wb(R.id.wb5).zf(te1);
            te2 = wj.dqwb(set3);

            if (zf.dy(zf.qctwkg(te2), "")) {

                te2 = "暂未设置";

            }
            st.wb(R.id.wb8).zf(te2);
            st.xdbj(R.id.xdbj2).kjd(0);
            st.xdbj(R.id.xdbj5).kjd(0);
            st.xdbj(R.id.xdbj6).kjd(0);
            st.xdbj(R.id.xdbj8).kjd(0);
            st.xdbj(R.id.xdbj9).kjd(0);
            st.xdbj(R.id.xdbj10).kjd(0);
            st.wb(R.id.wb10).zf(te1);
            st.wb(R.id.wb11).zf("UID:" + wj.dqwb(a));
            st.wb(R.id.wb6).zf("正");
            st.wb(R.id.wb11).kjd(0);
            st.wb(R.id.wb6).kjd(8);
            st.tx(R.id.tx4).kjd(0);
            easy.getPericon(wj.dqwb(a));

            if (!zf.dy(easy.getPerIcon(wj.dqwb(a)), "null")) {

                st.tx(R.id.tx7).tx(easy.getPerIconPath(wj.dqwb(a)));
                st.tx(R.id.tx7).kjd(0);

            }

        }
        st.wb(R.id.wb18).zf("点击更改账户头像");
        st.wb(R.id.wb19).zf("点击更改账户昵称");
        st.wb(R.id.wb22).zf("点击更改账户简介");

    }

    public void qh() {

        String set3 = AppPaths.appPath("settings/n1/set3.inf");

        if (st.xxbj(R.id.xxbj1).kjd() == 0) {

            String z = "";
            st.xxbj(R.id.xxbj1).kjd(8);
            st.xxbj(R.id.xxbj4).kjd(0);
            st.xxbj(R.id.xxbj15).kjd(8);

            if (mode == 1) {

                z = st.wb(R.id.wb5).zf();
                st.wb(R.id.title_bar).zf("账户昵称");
                st.bjk(R.id.bjk1).tszf("请输入昵称");

            } else if (mode == 2) {

                z = zf.qctwkg(wj.dqwb(set3));
                st.wb(R.id.title_bar).zf("账户简介");
                st.bjk(R.id.bjk1).tszf("请输入简介");

            } else if (mode == 3) {

            }
            st.bjk(R.id.bjk1).zf(z);

        } else {

            st.xxbj(R.id.xxbj1).kjd(0);
            st.xxbj(R.id.xxbj4).kjd(8);
            st.xxbj(R.id.xxbj15).kjd(8);
            st.bjk(R.id.bjk1).zf("");
            st.wb(R.id.title_bar).zf("< Typheye账户");

        }

    }

    public void gx() {

        final String set5 = AppPaths.appPath("settings/account/user");

        final String set2 = AppPaths.appPath("settings/n1/set2.inf");

        final String set3 = AppPaths.appPath("settings/n1/set3.inf");

        final String set4 = AppPaths.appPath("data/easy/cookie");

        final String set2s = AppPaths.appPath("settings/f11/set3.inf");

        String b = st.bjk(R.id.bjk1).zf();
        b = zf.qctwkg(b);

        if (mode == 1) {

            if (zf.dy(b, "")) {

                e1.tsk("提示", "昵称不符合规范");

            } else {

                final String nick = b;
                gj.xc(new Thread() {

                    public void run() {

                        gj.jmxc(new Runnable() {

                            public void run() {

                                st.wb(R.id.wb5).zf("加载中...");
                                st.wb(R.id.wb19).zf("加载中...");

                            }

                        });

                        String url = e1.urlUnlockString(wj.dqwb(AppPaths.appPath("settings/n1/url.inf")));

                        if (zf.cjw(url, "/")) {

                        } else {

                            url = url + "/";

                        }

                        String j_code = "";

                        String j_msg = "";

                        String a = wj.dqwb(set4);

                        String uid = wj.dqwb(set5);

                        String sjzx = xt.sj(4);

                        String send0 = "type=set_nick&uid=" + uid + "&cookie=" + a + "&value=" + IappCompat.zh(sj.zh()).zstring(nick) + "&time=" + sjzx;

                        final String send = "/api.php?" + send0 + "&token=" + sj.md5(send0);

                        final String back = wl.hq(url + send, null, "utf-8", null, true, null, 20000, 20000, null);

                        if (zf.dy(back, "") || zf.dy(back, null)) {

                            e1.tsk("提示", "修改失败，无法连接到服务器");
                            jz();

                        } else {

                            try {

                                JSONObject json = JSONObject.parseObject(back);
                                j_code = json.getString("code");
                                j_msg = json.getString("msg");

                                if (zf.dy(j_code, "200")) {

                                    wj.xrwb(set2, nick);
                                    wj.xrwb(set2s, nick);
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
                gj.xc(new Thread() {

                    public void run() {

                        gj.jmxc(new Runnable() {

                            public void run() {

                                st.wb(R.id.wb22).zf("加载中...");

                            }

                        });

                        String url = e1.urlUnlockString(wj.dqwb(AppPaths.appPath("settings/n1/url.inf")));

                        if (zf.cjw(url, "/")) {

                        } else {

                            url = url + "/";

                        }

                        String j_code = "";

                        String j_msg = "";

                        String a = wj.dqwb(set4);

                        String uid = wj.dqwb(set5);

                        String sjzx = xt.sj(4);

                        String send0 = "type=set_shuo&uid=" + uid + "&cookie=" + a + "&value=" + IappCompat.zh(sj.zh()).zstring(shuo) + "&time=" + sjzx;

                        final String send = "/api.php?" + send0 + "&token=" + sj.md5(send0);

                        final String back = wl.hq(url + send, null, "utf-8", null, true, null, 20000, 20000, null);

                        if (zf.dy(back, "") || zf.dy(back, null)) {

                            e1.tsk("提示", "修改失败，无法连接到服务器");
                            jz();

                        } else {

                            try {

                                JSONObject json = JSONObject.parseObject(back);
                                j_code = json.getString("code");
                                j_msg = json.getString("msg");

                                if (zf.dy(j_code, "200")) {

                                    wj.xrwb(set3, shuo);
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

            if (zf.dy(b, "")) {

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

            final String path_ = wj.hqml(a);

            final String[] path = new String[]{path_};

            final i.runlibrary.app.v.wb wb18 = st.wb(R.id.wb18);
            gj.xc(new Thread() {

                public void run() {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            wb18.zf("加载中...");

                        }

                    });
                    upload_back = "";

                    if (e1.yz()) {

                        if (zf.dy(path_, "") || !wj.cz(path_)) {

                            jz();
                            e1.tsk("提示", "文件路径无效");

                        } else {

                            final String url = "https://service.typheye.cn/src/up.php?uid=" + wj.dqwb(a3);

                            String fn__ = "null";

                            String[] fn_ = e1.fileinfo("/", path[0]);

                            if (zf.dy(fn_[0], "true")) {

                                fn__ = fn_[3];

                            }

                            final String file_name = fn__;

                            final long file_size = wj.dx(path_);
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    try {

                                        HttpRequest.TIME_OUT_DURATION = 300;
                                        HttpRequest.build(lei, url)
                                                .addParameter("name", file_name)
                                                .addParameter("size", file_size)
                                                .addParameter("file", wj.wj(path_))
                                                .setUploadProgressListener(new UploadProgressListener() {
                                                    @Override
                                                    public void onUpload(float percentage, long current, long total, boolean done) {

                                                        long now = current / total * 100;
                                                        wb18.zf("已上传" + IappCompat.zh(sj.zh()).zstring(now) + "%");
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

        if (!zf.dy(IappCompat.zh(sj.zh()).zstring(error), null)) {

            jz();
            e1.tsk("提示", "上传失败\n" + IappCompat.zh(sj.zh()).zstring(error));

        } else {

            if (zf.dy(back, "") || zf.dy(back, null)) {

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
        jz();

        if (wj.cz(path) && !zf.dy(wj.dqwb(path), "") && !zf.dy(wj.dqwb(path), "null")) {

            upload(wj.dqwb(path));
            wj.sc(path);

        }

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

            if (zf.dy(st.wb(R.id.title_bar).zf(), "< Typheye账户")) {

                gj.gb();

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

                if (!zf.dy(realPath, null)) {

                    realPath = wj.hqml(realPath);
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

        if (zf.dy(st.wb(R.id.title_bar).zf(), "< Typheye账户")) {

            gj.gb();

        }

    }

    private void onXdbj7Click(View vw) {

        if (!isAcc) {

            gj.tz(TypheyeLoginActivity.class);

        } else {

            String a = AppPaths.appPath("settings/account/user");

            if (!zf.dy(easy.getPerIcon(wj.dqwb(a)), "null")) {

                final String[] name = new String[]{"url"};

                final String[] value = new String[]{easy.getPerIconPath(wj.dqwb(a))};
                gj.jmxc(new Runnable() {

                    public void run() {

                        gj.tz(ImageViewerActivity.class, name, value);

                    }

                });

            } else {

                e1.tsk("账户头像", "您还未上传头像");

            }

        }

    }

    private void onWb10Click(View vw) {

        if (!isAcc) {

            gj.tz(TypheyeLoginActivity.class);

        } else {

            if (zf.dy(st.wb(R.id.wb5).zf(), "加载中...")) {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            } else {

                e1.tsk("账户昵称", st.wb(R.id.wb10).zf());

            }

        }

    }

    private void onWb11Click(View vw) {
        e1.tsk("账户UID", st.wb(R.id.wb11).zf());

    }

    private void onXxbj8Click(View vw) {

        if (!isAcc) {

            gj.tz(TypheyeLoginActivity.class);

        } else {

        }

    }

    private void onXdbj4Click(View vw) {

        if (!isAcc) {

            e1.tsk("提示", "请先登录您的账户后再操作");

        } else {

            if (zf.dy(st.wb(R.id.wb8).zf(), "加载中...")) {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            } else {

                e1.tsk("账户简介", st.wb(R.id.wb8).zf());

            }

        }

    }

    private boolean onXdbj4LongClick(View vw) {

        String te = st.wb(R.id.wb7).zf();

        String lj = st.wb(R.id.wb8).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onKg1CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/n1/set1.inf");

        if (ic) {

            wj.xrwb(set, "true");

        } else {

            wj.xrwb(set, "false");

        }

    }

    private void onXdbj2Click(View vw) {

        i.runlibrary.app.v.kg kg = st.kg(R.id.kg1);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean onXdbj2LongClick(View vw) {

        String te = st.wb(R.id.wb2).zf();

        String lj = st.wb(R.id.wb3).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj8Click(View vw) {

        if (!isAcc) {

            e1.tsk("提示", "请先登录您的账户后再操作");

        } else {

            if (zf.dy(st.wb(R.id.wb18).zf(), "点击更改账户头像")) {

                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.title_bar).zf("账户头像");
                        st.xxbj(R.id.xxbj1).kjd(8);
                        st.xxbj(R.id.xxbj4).kjd(8);
                        st.xxbj(R.id.xxbj9).kjd(8);
                        st.xxbj(R.id.xxbj15).kjd(0);

                    }

                });

            } else {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            }

        }

    }

    private boolean onXdbj8LongClick(View vw) {

        String te = st.wb(R.id.wb17).zf();

        String lj = st.wb(R.id.wb18).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj9Click(View vw) {

        if (!isAcc) {

            e1.tsk("提示", "请先登录您的账户后再操作");

        } else {

            if (zf.dy(st.wb(R.id.wb19).zf(), "加载中...")) {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            } else {

                mode = 1;
                qh();

            }

        }

    }

    private boolean onXdbj9LongClick(View vw) {

        String te = st.wb(R.id.wb16).zf();

        String lj = st.wb(R.id.wb19).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj10Click(View vw) {

        if (!isAcc) {

            e1.tsk("提示", "请先登录您的账户后再操作");

        } else {

            if (zf.dy(st.wb(R.id.wb22).zf(), "加载中...")) {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            } else {

                mode = 2;
                qh();

            }

        }

    }

    private boolean onXdbj10LongClick(View vw) {

        String te = st.wb(R.id.wb21).zf();

        String lj = st.wb(R.id.wb22).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj6Click(View vw) {

        if (!isAcc) {

            e1.tsk("提示", "请先登录您的账户后再操作");

        } else {

            if (st.xxbj(R.id.xxbj9).kjd() == 0) {

                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.title_bar).zf("< 我的账户");
                        st.xxbj(R.id.xxbj9).kjd(8);
                        st.xxbj(R.id.xxbj1).kjd(0);

                    }

                });

            } else {

                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.title_bar).zf("提示");
                        st.xxbj(R.id.xxbj9).kjd(0);
                        st.xxbj(R.id.xxbj1).kjd(8);

                    }

                });

            }

        }

    }

    private boolean onXdbj6LongClick(View vw) {

        String te = st.wb(R.id.wb14).zf();

        String lj = st.wb(R.id.wb15).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj5Click(View vw) {

        if (!isAcc) {

            e1.tsk("提示", "请先登录您的账户后再操作");

        } else {

            String url = "https://service.typheye.cn/site/user/center/delete/";

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

                    e1.tsk_QR("账户注销", "如果您需要注销Typheye账户,请扫描下方二维码后在手机上操作.", url);

                }

            }

        }

    }

    private boolean onXdbj5LongClick(View vw) {

        String te = st.wb(R.id.wb1).zf();

        String lj = st.wb(R.id.wb9).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx1Click(View vw) {
        gj.gb();

    }

    private void onAn1Click(View vw) {

        i.runlibrary.app.v.xlcd xlcd1 = st.xlcd(R.id.xlcd1);

        if (xlcd1.dqzsxmxh() == 1) {

            try {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                lei.startActivityForResult(intent, 1);

            } catch (Throwable e) {

                e1.tsk("提示", "此方式存在异常，暂时无法使用");

            }

        } else {

            try {

                i.runlibrary.app.xt$qxgl a = (i.runlibrary.app.xt$qxgl) (Object) xt.qxgl();

                if (a.qx("android.permission.WRITE_EXTERNAL_STORAGE")) {

                    final String choose2 = AppPaths.appPath("cache/file/choose2");
                    wj.sc(path);

                    int sdk = IappCompat.sdk(xt.sbxx());

                    String ftitle = AppPaths.appPath("cache/file/title");
                    wj.xrwb(ftitle, "< 选择头像");
                    wj.xrwb(choose2, "h1");

                    if (sdk < 30) {

                        String lj = wj.hqml("%");

                        final String[] name = new String[]{"url", "root"};

                        final String[] value = new String[]{lj, lj};
                        gj.jmxc(new Runnable() {

                            public void run() {

                                gj.tz(FileManagerActivity.class, name, value);

                            }

                        });

                    } else {

                        String lj = "/storage/emulated/0/";

                        final String[] name = new String[]{"url", "root"};

                        final String[] value = new String[]{lj, lj};
                        gj.jmxc(new Runnable() {

                            public void run() {

                                gj.tz(FileManagerActivity.class, name, value);

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
        gj.jmxc(new Runnable() {

            public void run() {

                st.wb(R.id.title_bar).zf("< Typheye账户");
                st.xxbj(R.id.xxbj1).kjd(0);
                st.xxbj(R.id.xxbj4).kjd(8);
                st.xxbj(R.id.xxbj9).kjd(8);
                st.xxbj(R.id.xxbj15).kjd(8);

            }

        });

    }

    private void onAn2Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                st.wb(R.id.title_bar).zf("< Typheye账户");
                st.xxbj(R.id.xxbj1).kjd(0);
                st.xxbj(R.id.xxbj4).kjd(8);
                st.xxbj(R.id.xxbj9).kjd(8);
                st.xxbj(R.id.xxbj15).kjd(8);

            }

        });

    }

    private void onTx8Click(View vw) {
        gj.gb();

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

        if (st.xxbj(R.id.xxbj9).kjd() == 0) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf("< Typheye账户");
                    st.xxbj(R.id.xxbj9).kjd(8);
                    st.xxbj(R.id.xxbj1).kjd(0);

                }

            });

        } else {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf("提示");
                    st.xxbj(R.id.xxbj9).kjd(0);
                    st.xxbj(R.id.xxbj1).kjd(8);

                }

            });

        }

    }

    private void onTx6Click(View vw) {

        String a = AppPaths.appPath("settings/account/user");

        String b = AppPaths.appPath("settings/account/pass");

        String c = AppPaths.appPath("settings/account/userx");

        String d = AppPaths.appPath("settings/account/passx");
        wj.sc(a);
        wj.sc(b);
        wj.sc(c);
        wj.sc(d);
        jz();

        if (st.xxbj(R.id.xxbj9).kjd() == 0) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf("< Typheye账户");
                    st.xxbj(R.id.xxbj9).kjd(8);
                    st.xxbj(R.id.xxbj1).kjd(0);

                }

            });

        } else {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf("提示");
                    st.xxbj(R.id.xxbj9).kjd(0);
                    st.xxbj(R.id.xxbj1).kjd(8);

                }

            });

        }

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        ImageView i11aeec1890 = (ImageView) findViewById(ay, vw, R.id.tx4);

        ImageView i3621e3f4a1 = (ImageView) findViewById(ay, vw, R.id.tx7);

        RelativeLayout e585d94488 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj7);
        e585d94488.setOnClickListener(xdbj7ClickListener);

        TextView f52e07ac8c = (TextView) findViewById(ay, vw, R.id.wb6);

        TextView i81652f77b6 = (TextView) findViewById(ay, vw, R.id.wb10);
        i81652f77b6.setOnClickListener(wb10ClickListener);

        TextView i42afe4289f = (TextView) findViewById(ay, vw, R.id.wb11);
        i42afe4289f.setOnClickListener(wb11ClickListener);

        LinearLayout i3f6c9f386b = (LinearLayout) findViewById(ay, vw, R.id.xxbj8);
        i3f6c9f386b.setOnClickListener(xxbj8ClickListener);

        TextView fc1598ee09 = (TextView) findViewById(ay, vw, R.id.wb8);

        RelativeLayout i2202ede8ff = (RelativeLayout) findViewById(ay, vw, R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);
        i2202ede8ff.setOnLongClickListener(xdbj4LongClickListener);

        SwitchCompat i1bca89f073 = (SwitchCompat) findViewById(ay, vw, R.id.kg1);
        i1bca89f073.setOnCheckedChangeListener(kg1CheckedChangeListener);

        RelativeLayout i3167b45174 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);
        i3167b45174.setOnLongClickListener(xdbj2LongClickListener);

        RelativeLayout fab146d7a5 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj8);
        fab146d7a5.setOnClickListener(xdbj8ClickListener);
        fab146d7a5.setOnLongClickListener(xdbj8LongClickListener);

        RelativeLayout i5a93676674 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj9);
        i5a93676674.setOnClickListener(xdbj9ClickListener);
        i5a93676674.setOnLongClickListener(xdbj9LongClickListener);

        RelativeLayout i5b34eede7f = (RelativeLayout) findViewById(ay, vw, R.id.xdbj10);
        i5b34eede7f.setOnClickListener(xdbj10ClickListener);
        i5b34eede7f.setOnLongClickListener(xdbj10LongClickListener);

        RelativeLayout i171cedf1eb = (RelativeLayout) findViewById(ay, vw, R.id.xdbj6);
        i171cedf1eb.setOnClickListener(xdbj6ClickListener);
        i171cedf1eb.setOnLongClickListener(xdbj6LongClickListener);

        RelativeLayout c0d3e00d2b = (RelativeLayout) findViewById(ay, vw, R.id.xdbj5);
        c0d3e00d2b.setOnClickListener(xdbj5ClickListener);
        c0d3e00d2b.setOnLongClickListener(xdbj5LongClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        Button i171ea34f1a = (Button) findViewById(ay, vw, R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        Button i0b66a667b1 = (Button) findViewById(ay, vw, R.id.an2);
        i0b66a667b1.setOnClickListener(an2ClickListener);

        ImageView cb1d655d23 = (ImageView) findViewById(ay, vw, R.id.tx8);
        cb1d655d23.setOnClickListener(tx8ClickListener);

        LinearLayout i38cf17850d = (LinearLayout) findViewById(ay, vw, R.id.xxbj15);
        i38cf17850d.setOnTouchListener(xxbj15TouchListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i4a4248a0f2 = (ImageView) findViewById(ay, vw, R.id.tx5);
        i4a4248a0f2.setOnClickListener(tx5ClickListener);

        ImageView i6f2c7751cf = (ImageView) findViewById(ay, vw, R.id.tx6);
        i6f2c7751cf.setOnClickListener(tx6ClickListener);

        NestedScrollView a6dab51133 = (NestedScrollView) findViewById(ay, vw, R.id.qtgd1);

        __layoutIsLoaded(ay, vw);
    }
}
