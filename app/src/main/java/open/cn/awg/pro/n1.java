/**
 ******************************************************************************
 * @file    n1.java
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

import android.view.View;
import android.view.Window;

import com.alibaba.fastjson.JSONObject;
import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.listener.UploadProgressListener;
import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;

public class n1 extends iActivity {

    public final n1 lei = this, 类 = this;
    public int mode = 0;
    public boolean isAcc = false;
    public boolean state_upload = false;
    public java.lang.String upload_back = "";
    public open.cn.awg.pro.e1 e1 = new e1(_APPINFO);
    public open.cn.awg.pro.easy easy = new easy(_APPINFO);
    public java.lang.String path = "/data/user/0/open.cn.awg.pro/cache/user/upload/path";
    private final android.view.View.OnClickListener $_on_setOnClickListener_i6f2c7751cf = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i6f2c7751cf(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i4a4248a0f2 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i4a4248a0f2(vw);
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
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i38cf17850d = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i38cf17850d(vw, me);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_cb1d655d23 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_cb1d655d23(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i0b66a667b1 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i0b66a667b1(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i171ea34f1a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i171ea34f1a(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_c0d3e00d2b = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_c0d3e00d2b(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_c0d3e00d2b = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_c0d3e00d2b(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i171cedf1eb = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i171cedf1eb(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i171cedf1eb = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i171cedf1eb(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i5b34eede7f = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i5b34eede7f(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i5b34eede7f = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i5b34eede7f(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i5a93676674 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i5a93676674(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i5a93676674 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i5a93676674(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_fab146d7a5 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_fab146d7a5(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_fab146d7a5 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_fab146d7a5(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i3167b45174 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i3167b45174(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i3167b45174 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i3167b45174(vw);
        }

    };
    private final android.widget.CompoundButton.OnCheckedChangeListener $_on_setOnCheckedChangeListener_i1bca89f073 = new android.widget.CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(android.widget.CompoundButton vw, boolean ic) {
            $_onCheckedChanged_i1bca89f073(vw, ic);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i2202ede8ff = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i2202ede8ff(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i2202ede8ff = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i2202ede8ff(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i3f6c9f386b = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i3f6c9f386b(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i42afe4289f = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i42afe4289f(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i81652f77b6 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i81652f77b6(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_e585d94488 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_e585d94488(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d26d5d7080(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.n1);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.e6.getInstance().addActivity(lei);
        jz();

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

            st.xdbj(open.cn.awg.pro.R.id.n1_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.n1_v).shxtck(false);

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

        i.runlibrary.app.v.xlcd xlcd1 = st.xlcd(open.cn.awg.pro.R.id.xlcd1);

        java.lang.String[] sz = new java.lang.String[]{"应用内置选择器", "系统默认选择器"};
        xlcd1.csh(sz);

    }

    public void jz() {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/n1/set1.inf";

                i.runlibrary.app.v.kg kg1 = st.kg(open.cn.awg.pro.R.id.kg1);

                if (zf.dy(wj.dqwb(set1), "true")) {

                    kg1.xzzt(true);

                }
                zh();

            }

        });

    }

    public void zh() {

        java.lang.String te1 = "";

        java.lang.String te2 = "";

        java.lang.String te3 = "";

        java.lang.String a = "/data/user/0/open.cn.awg.pro/settings/account/user";

        java.lang.String b = "/data/user/0/open.cn.awg.pro/settings/account/pass";

        java.lang.String c = "/data/user/0/open.cn.awg.pro/settings/account/isAcc";

        java.lang.String set2 = "/data/user/0/open.cn.awg.pro/settings/n1/set2.inf";

        java.lang.String set3 = "/data/user/0/open.cn.awg.pro/settings/n1/set3.inf";

        if (!wj.cz(a) || !wj.cz(b)) {

            isAcc = false;
            wj.sc(c);
            te1 = "请先登录您的账户后再操作";
            st.wb(open.cn.awg.pro.R.id.wb5).zf(te1);
            te2 = "请先登录您的账户后再操作";
            st.wb(open.cn.awg.pro.R.id.wb8).zf(te2);
            st.xdbj(open.cn.awg.pro.R.id.xdbj2).kjd(8);
            st.xdbj(open.cn.awg.pro.R.id.xdbj5).kjd(8);
            st.xdbj(open.cn.awg.pro.R.id.xdbj6).kjd(8);
            st.xdbj(open.cn.awg.pro.R.id.xdbj8).kjd(8);
            st.xdbj(open.cn.awg.pro.R.id.xdbj9).kjd(8);
            st.xdbj(open.cn.awg.pro.R.id.xdbj10).kjd(8);
            st.wb(open.cn.awg.pro.R.id.wb10).zf("未登录");
            st.wb(open.cn.awg.pro.R.id.wb11).zf("");
            st.wb(open.cn.awg.pro.R.id.wb6).zf("");
            st.wb(open.cn.awg.pro.R.id.wb11).kjd(8);
            st.wb(open.cn.awg.pro.R.id.wb6).kjd(8);
            st.tx(open.cn.awg.pro.R.id.tx4).kjd(0);
            st.tx(open.cn.awg.pro.R.id.tx7).kjd(8);

        } else {

            isAcc = true;
            wj.xrwb(c, "0");
            te1 = wj.dqwb(set2);
            st.wb(open.cn.awg.pro.R.id.wb5).zf(te1);
            te2 = wj.dqwb(set3);

            if (zf.dy(zf.qctwkg(te2), "")) {

                te2 = "暂未设置";

            }
            st.wb(open.cn.awg.pro.R.id.wb8).zf(te2);
            st.xdbj(open.cn.awg.pro.R.id.xdbj2).kjd(0);
            st.xdbj(open.cn.awg.pro.R.id.xdbj5).kjd(0);
            st.xdbj(open.cn.awg.pro.R.id.xdbj6).kjd(0);
            st.xdbj(open.cn.awg.pro.R.id.xdbj8).kjd(0);
            st.xdbj(open.cn.awg.pro.R.id.xdbj9).kjd(0);
            st.xdbj(open.cn.awg.pro.R.id.xdbj10).kjd(0);
            st.wb(open.cn.awg.pro.R.id.wb10).zf(te1);
            st.wb(open.cn.awg.pro.R.id.wb11).zf("UID:" + wj.dqwb(a));
            st.wb(open.cn.awg.pro.R.id.wb6).zf("正");
            st.wb(open.cn.awg.pro.R.id.wb11).kjd(0);
            st.wb(open.cn.awg.pro.R.id.wb6).kjd(8);
            st.tx(open.cn.awg.pro.R.id.tx4).kjd(0);
            easy.getPericon(wj.dqwb(a));

            if (!zf.dy(easy.getPerIcon(wj.dqwb(a)), "null")) {

                st.tx(open.cn.awg.pro.R.id.tx7).tx(easy.getPerIconPath(wj.dqwb(a)));
                st.tx(open.cn.awg.pro.R.id.tx7).kjd(0);

            }

        }
        st.wb(open.cn.awg.pro.R.id.wb18).zf("点击更改账户头像");
        st.wb(open.cn.awg.pro.R.id.wb19).zf("点击更改账户昵称");
        st.wb(open.cn.awg.pro.R.id.wb22).zf("点击更改账户简介");

    }

    public void qh() {

        java.lang.String set3 = "/data/user/0/open.cn.awg.pro/settings/n1/set3.inf";

        if (st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd() == 0) {

            java.lang.String z = "";
            st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
            st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(0);
            st.xxbj(open.cn.awg.pro.R.id.xxbj15).kjd(8);

            if (mode == 1) {

                z = st.wb(open.cn.awg.pro.R.id.wb5).zf();
                st.wb(open.cn.awg.pro.R.id.Tab).zf("账户昵称");
                st.bjk(open.cn.awg.pro.R.id.bjk1).tszf("请输入昵称");

            } else if (mode == 2) {

                z = zf.qctwkg(wj.dqwb(set3));
                st.wb(open.cn.awg.pro.R.id.Tab).zf("账户简介");
                st.bjk(open.cn.awg.pro.R.id.bjk1).tszf("请输入简介");

            } else if (mode == 3) {

            }
            st.bjk(open.cn.awg.pro.R.id.bjk1).zf(z);

        } else {

            st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);
            st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(8);
            st.xxbj(open.cn.awg.pro.R.id.xxbj15).kjd(8);
            st.bjk(open.cn.awg.pro.R.id.bjk1).zf("");
            st.wb(open.cn.awg.pro.R.id.Tab).zf("< Typheye账户");

        }

    }

    public void gx() {

        final java.lang.String set5 = "/data/user/0/open.cn.awg.pro/settings/account/user";

        final java.lang.String set2 = "/data/user/0/open.cn.awg.pro/settings/n1/set2.inf";

        final java.lang.String set3 = "/data/user/0/open.cn.awg.pro/settings/n1/set3.inf";

        final java.lang.String set4 = "/data/user/0/open.cn.awg.pro/data/easy/cookie";

        final java.lang.String set2s = "/data/user/0/open.cn.awg.pro/settings/f11/set3.inf";

        java.lang.String b = st.bjk(open.cn.awg.pro.R.id.bjk1).zf();
        b = zf.qctwkg(b);

        if (mode == 1) {

            if (zf.dy(b, "")) {

                e1.tsk("提示", "昵称不符合规范");

            } else {

                final java.lang.String nick = b;
                gj.xc(new java.lang.Thread() {

                    public void run() {

                        gj.jmxc(new java.lang.Runnable() {

                            public void run() {

                                st.wb(open.cn.awg.pro.R.id.wb5).zf("加载中...");
                                st.wb(open.cn.awg.pro.R.id.wb19).zf("加载中...");

                            }

                        });

                        java.lang.String url = e1.urlUnlockString(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/n1/url.inf"));

                        if (zf.cjw(url, "/")) {

                        } else {

                            url = url + "/";

                        }

                        java.lang.String j_code = "";

                        java.lang.String j_msg = "";

                        java.lang.String a = wj.dqwb(set4);

                        java.lang.String uid = wj.dqwb(set5);

                        java.lang.String sjzx = xt.sj(4);

                        java.lang.String send0 = "type=set_nick&uid=" + uid + "&cookie=" + a + "&value=" + sj.zh().zstring(nick) + "&time=" + sjzx;

                        final java.lang.String send = "/api.php?" + send0 + "&token=" + sj.md5(send0);

                        final java.lang.String back = wl.hq(url + send, null, "utf-8", null, true, null, 20000, 20000, null);

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

                            } catch (java.lang.Throwable __$_e__) {

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

                final java.lang.String shuo = b;
                gj.xc(new java.lang.Thread() {

                    public void run() {

                        gj.jmxc(new java.lang.Runnable() {

                            public void run() {

                                st.wb(open.cn.awg.pro.R.id.wb22).zf("加载中...");

                            }

                        });

                        java.lang.String url = e1.urlUnlockString(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/n1/url.inf"));

                        if (zf.cjw(url, "/")) {

                        } else {

                            url = url + "/";

                        }

                        java.lang.String j_code = "";

                        java.lang.String j_msg = "";

                        java.lang.String a = wj.dqwb(set4);

                        java.lang.String uid = wj.dqwb(set5);

                        java.lang.String sjzx = xt.sj(4);

                        java.lang.String send0 = "type=set_shuo&uid=" + uid + "&cookie=" + a + "&value=" + sj.zh().zstring(shuo) + "&time=" + sjzx;

                        final java.lang.String send = "/api.php?" + send0 + "&token=" + sj.md5(send0);

                        final java.lang.String back = wl.hq(url + send, null, "utf-8", null, true, null, 20000, 20000, null);

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

                            } catch (java.lang.Throwable __$_e__) {

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

    public void upload(java.lang.Object a) {

        if (!state_upload) {

            state_upload = true;

            final java.lang.String a3 = "/data/user/0/open.cn.awg.pro/settings/account/user";

            final java.lang.String path_ = wj.hqml(a);

            final java.lang.String[] path = new java.lang.String[]{path_};

            final i.runlibrary.app.v.wb wb18 = st.wb(open.cn.awg.pro.R.id.wb18);
            gj.xc(new java.lang.Thread() {

                public void run() {

                    gj.jmxc(new java.lang.Runnable() {

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

                            final java.lang.String url = "https://service.typheye.cn/src/up.php?uid=" + wj.dqwb(a3);

                            java.lang.String fn__ = "null";

                            java.lang.String[] fn_ = e1.fileinfo("/", path[0]);

                            if (zf.dy(fn_[0], "true")) {

                                fn__ = fn_[3];

                            }

                            final java.lang.String file_name = fn__;

                            final long file_size = wj.dx(path_);
                            gj.jmxc(new java.lang.Runnable() {

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
                                                        wb18.zf("已上传" + sj.zh().zstring(now) + "%");
                                                    }
                                                })
                                                .setResponseListener(new ResponseListener() {
                                                    @Override
                                                    public void onResponse(String response, Exception error) {
                                                        uploadResult(error, response);
                                                    }
                                                })
                                                .doPost();

                                    } catch (java.lang.Throwable e) {

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

    public void uploadResult(java.lang.Exception error, java.lang.Object upload_back) {

        java.lang.Object back = upload_back;

        if (!zf.dy(sj.zh().zstring(error), null)) {

            jz();
            e1.tsk("提示", "上传失败\n" + sj.zh().zstring(error));

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
        jz();

        if (wj.cz(path) && !zf.dy(wj.dqwb(path), "") && !zf.dy(wj.dqwb(path), "null")) {

            upload(wj.dqwb(path));
            wj.sc(path);

        }

    }

    public boolean onKeyDown(int kc, android.view.KeyEvent ke) {

        if (kc == 4) {

            if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< Typheye账户")) {

                gj.gb();

            }

        }
        return false;

    }

    public void onActivityResult(int re, int rc, android.content.Intent it) {
        super.onActivityResult(re, rc, it);

        if (re == 1) {

            if (rc == -1) {

                java.lang.String realPath = null;

                try {

                    realPath = uri.getByIntent(lei, it);

                } catch (java.lang.Throwable e) {

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
        open.cn.awg.pro.e6.getInstance().removeActivity(lei);

    }

    private void $_onClick_d26d5d7080(android.view.View vw) {

        if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< Typheye账户")) {

            gj.gb();

        }

    }

    private void $_onClick_e585d94488(android.view.View vw) {

        if (!isAcc) {

            gj.tz(n2.class);

        } else {

            java.lang.String a = "/data/user/0/open.cn.awg.pro/settings/account/user";

            if (!zf.dy(easy.getPerIcon(wj.dqwb(a)), "null")) {

                final java.lang.String[] name = new java.lang.String[]{"url"};

                final java.lang.String[] value = new java.lang.String[]{easy.getPerIconPath(wj.dqwb(a))};
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        gj.tz(d3.class, name, value);

                    }

                });

            } else {

                e1.tsk("账户头像", "您还未上传头像");

            }

        }

    }

    private void $_onClick_i81652f77b6(android.view.View vw) {

        if (!isAcc) {

            gj.tz(n2.class);

        } else {

            if (zf.dy(st.wb(open.cn.awg.pro.R.id.wb5).zf(), "加载中...")) {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            } else {

                e1.tsk("账户昵称", st.wb(open.cn.awg.pro.R.id.wb10).zf());

            }

        }

    }

    private void $_onClick_i42afe4289f(android.view.View vw) {
        e1.tsk("账户UID", st.wb(open.cn.awg.pro.R.id.wb11).zf());

    }

    private void $_onClick_i3f6c9f386b(android.view.View vw) {

        if (!isAcc) {

            gj.tz(n2.class);

        } else {

        }

    }

    private void $_onClick_i2202ede8ff(android.view.View vw) {

        if (!isAcc) {

            e1.tsk("提示", "请先登录您的账户后再操作");

        } else {

            if (zf.dy(st.wb(open.cn.awg.pro.R.id.wb8).zf(), "加载中...")) {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            } else {

                e1.tsk("账户简介", st.wb(open.cn.awg.pro.R.id.wb8).zf());

            }

        }

    }

    private boolean $_onLongClick_i2202ede8ff(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb7).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb8).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onCheckedChanged_i1bca89f073(android.widget.CompoundButton vw, boolean ic) {

        java.lang.String set = "/data/user/0/open.cn.awg.pro/settings/n1/set1.inf";

        if (ic) {

            wj.xrwb(set, "true");

        } else {

            wj.xrwb(set, "false");

        }

    }

    private void $_onClick_i3167b45174(android.view.View vw) {

        i.runlibrary.app.v.kg kg = st.kg(open.cn.awg.pro.R.id.kg1);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean $_onLongClick_i3167b45174(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb2).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb3).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_fab146d7a5(android.view.View vw) {

        if (!isAcc) {

            e1.tsk("提示", "请先登录您的账户后再操作");

        } else {

            if (zf.dy(st.wb(open.cn.awg.pro.R.id.wb18).zf(), "点击更改账户头像")) {

                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.wb(open.cn.awg.pro.R.id.Tab).zf("账户头像");
                        st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
                        st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(8);
                        st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
                        st.xxbj(open.cn.awg.pro.R.id.xxbj15).kjd(0);

                    }

                });

            } else {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            }

        }

    }

    private boolean $_onLongClick_fab146d7a5(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb17).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb18).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i5a93676674(android.view.View vw) {

        if (!isAcc) {

            e1.tsk("提示", "请先登录您的账户后再操作");

        } else {

            if (zf.dy(st.wb(open.cn.awg.pro.R.id.wb19).zf(), "加载中...")) {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            } else {

                mode = 1;
                qh();

            }

        }

    }

    private boolean $_onLongClick_i5a93676674(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb16).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb19).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i5b34eede7f(android.view.View vw) {

        if (!isAcc) {

            e1.tsk("提示", "请先登录您的账户后再操作");

        } else {

            if (zf.dy(st.wb(open.cn.awg.pro.R.id.wb22).zf(), "加载中...")) {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            } else {

                mode = 2;
                qh();

            }

        }

    }

    private boolean $_onLongClick_i5b34eede7f(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb21).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb22).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i171cedf1eb(android.view.View vw) {

        if (!isAcc) {

            e1.tsk("提示", "请先登录您的账户后再操作");

        } else {

            if (st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd() == 0) {

                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.wb(open.cn.awg.pro.R.id.Tab).zf("< 我的账户");
                        st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
                        st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);

                    }

                });

            } else {

                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.wb(open.cn.awg.pro.R.id.Tab).zf("提示");
                        st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(0);
                        st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);

                    }

                });

            }

        }

    }

    private boolean $_onLongClick_i171cedf1eb(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb14).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb15).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_c0d3e00d2b(android.view.View vw) {

        if (!isAcc) {

            e1.tsk("提示", "请先登录您的账户后再操作");

        } else {

            java.lang.String url = "https://service.typheye.cn/site/user/center/delete/";

            final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

            java.lang.String a8 = wj.dqwb(a2);

            if (zf.dy(a8, "4")) {

                if (e1.isInstallX5()) {

                    final java.lang.String[] name = new java.lang.String[]{"url"};

                    final java.lang.String[] value = new java.lang.String[]{url};
                    gj.tz(j6.class, name, value);

                } else {

                    wl.fw(url);

                }

            } else {

                if (e1.isInstallX5()) {

                    final java.lang.String[] name = new java.lang.String[]{"url"};

                    final java.lang.String[] value = new java.lang.String[]{url};
                    gj.tz(j6.class, name, value);

                } else {

                    e1.tsk_QR("账户注销", "如果您需要注销Typheye账户,请扫描下方二维码后在手机上操作.", url);

                }

            }

        }

    }

    private boolean $_onLongClick_c0d3e00d2b(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb1).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb9).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i34dc7bf20a(android.view.View vw) {
        gj.gb();

    }

    private void $_onClick_i171ea34f1a(android.view.View vw) {

        i.runlibrary.app.v.xlcd xlcd1 = st.xlcd(open.cn.awg.pro.R.id.xlcd1);

        if (xlcd1.dqzsxmxh() == 1) {

            try {

                android.content.Intent intent = new android.content.Intent(android.content.Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                lei.startActivityForResult(intent, 1);

            } catch (java.lang.Throwable e) {

                e1.tsk("提示", "此方式存在异常，暂时无法使用");

            }

        } else {

            try {

                i.runlibrary.app.xt$qxgl a = xt.qxgl();

                if (a.qx("android.permission.WRITE_EXTERNAL_STORAGE")) {

                    final java.lang.String choose2 = "/data/user/0/open.cn.awg.pro/cache/file/choose2";
                    wj.sc(path);

                    int sdk = xt.sbxx().sdk;

                    java.lang.String ftitle = "/data/user/0/open.cn.awg.pro/cache/file/title";
                    wj.xrwb(ftitle, "< 选择头像");
                    wj.xrwb(choose2, "h1");

                    if (sdk < 30) {

                        java.lang.String lj = wj.hqml("%");

                        final java.lang.String[] name = new java.lang.String[]{"url", "root"};

                        final java.lang.String[] value = new java.lang.String[]{lj, lj};
                        gj.jmxc(new java.lang.Runnable() {

                            public void run() {

                                gj.tz(d1.class, name, value);

                            }

                        });

                    } else {

                        java.lang.String lj = "/storage/emulated/0/";

                        final java.lang.String[] name = new java.lang.String[]{"url", "root"};

                        final java.lang.String[] value = new java.lang.String[]{lj, lj};
                        gj.jmxc(new java.lang.Runnable() {

                            public void run() {

                                gj.tz(d1.class, name, value);

                            }

                        });

                    }

                } else {

                    e1.tsk("提示", "您未授权应用\"修改或删除您共享存储空间中的内容\"，请先前往设置授权后再使用");

                }

            } catch (java.lang.Throwable e) {

                e1.upload_error(e, "n1.*");

            }

        }
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.wb(open.cn.awg.pro.R.id.Tab).zf("< Typheye账户");
                st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);
                st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(8);
                st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
                st.xxbj(open.cn.awg.pro.R.id.xxbj15).kjd(8);

            }

        });

    }

    private void $_onClick_i0b66a667b1(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.wb(open.cn.awg.pro.R.id.Tab).zf("< Typheye账户");
                st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);
                st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(8);
                st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
                st.xxbj(open.cn.awg.pro.R.id.xxbj15).kjd(8);

            }

        });

    }

    private void $_onClick_cb1d655d23(android.view.View vw) {
        gj.gb();

    }

    private boolean $_onTouch_i38cf17850d(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private void $_onClick_i4d951cdb0e(android.view.View vw) {
        qh();

    }

    private void $_onClick_i8217d42a17(android.view.View vw) {
        gx();

    }

    private void $_onClick_i4a4248a0f2(android.view.View vw) {

        if (st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd() == 0) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.wb(open.cn.awg.pro.R.id.Tab).zf("< Typheye账户");
                    st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);

                }

            });

        } else {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.wb(open.cn.awg.pro.R.id.Tab).zf("提示");
                    st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(0);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);

                }

            });

        }

    }

    private void $_onClick_i6f2c7751cf(android.view.View vw) {

        java.lang.String a = "/data/user/0/open.cn.awg.pro/settings/account/user";

        java.lang.String b = "/data/user/0/open.cn.awg.pro/settings/account/pass";

        java.lang.String c = "/data/user/0/open.cn.awg.pro/settings/account/userx";

        java.lang.String d = "/data/user/0/open.cn.awg.pro/settings/account/passx";
        wj.sc(a);
        wj.sc(b);
        wj.sc(c);
        wj.sc(d);
        jz();

        if (st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd() == 0) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.wb(open.cn.awg.pro.R.id.Tab).zf("< Typheye账户");
                    st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);

                }

            });

        } else {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.wb(open.cn.awg.pro.R.id.Tab).zf("提示");
                    st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(0);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);

                }

            });

        }

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
        d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

        android.widget.ImageView i11aeec1890 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx4);

        android.widget.ImageView i3621e3f4a1 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx7);

        android.widget.RelativeLayout e585d94488 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj7);
        e585d94488.setOnClickListener($_on_setOnClickListener_e585d94488);

        android.widget.TextView f52e07ac8c = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb6);

        android.widget.TextView i81652f77b6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb10);
        i81652f77b6.setOnClickListener($_on_setOnClickListener_i81652f77b6);

        android.widget.TextView i42afe4289f = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb11);
        i42afe4289f.setOnClickListener($_on_setOnClickListener_i42afe4289f);

        android.widget.LinearLayout i3f6c9f386b = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj8);
        i3f6c9f386b.setOnClickListener($_on_setOnClickListener_i3f6c9f386b);

        android.widget.TextView fc1598ee09 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb8);

        android.widget.RelativeLayout i2202ede8ff = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj4);
        i2202ede8ff.setOnClickListener($_on_setOnClickListener_i2202ede8ff);
        i2202ede8ff.setOnLongClickListener($_on_setOnLongClickListener_i2202ede8ff);

        android.support.v7.widget.SwitchCompat i1bca89f073 = (android.support.v7.widget.SwitchCompat) findViewById(ay, vw, open.cn.awg.pro.R.id.kg1);
        i1bca89f073.setOnCheckedChangeListener($_on_setOnCheckedChangeListener_i1bca89f073);

        android.widget.RelativeLayout i3167b45174 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj2);
        i3167b45174.setOnClickListener($_on_setOnClickListener_i3167b45174);
        i3167b45174.setOnLongClickListener($_on_setOnLongClickListener_i3167b45174);

        android.widget.RelativeLayout fab146d7a5 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj8);
        fab146d7a5.setOnClickListener($_on_setOnClickListener_fab146d7a5);
        fab146d7a5.setOnLongClickListener($_on_setOnLongClickListener_fab146d7a5);

        android.widget.RelativeLayout i5a93676674 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj9);
        i5a93676674.setOnClickListener($_on_setOnClickListener_i5a93676674);
        i5a93676674.setOnLongClickListener($_on_setOnLongClickListener_i5a93676674);

        android.widget.RelativeLayout i5b34eede7f = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj10);
        i5b34eede7f.setOnClickListener($_on_setOnClickListener_i5b34eede7f);
        i5b34eede7f.setOnLongClickListener($_on_setOnLongClickListener_i5b34eede7f);

        android.widget.RelativeLayout i171cedf1eb = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj6);
        i171cedf1eb.setOnClickListener($_on_setOnClickListener_i171cedf1eb);
        i171cedf1eb.setOnLongClickListener($_on_setOnLongClickListener_i171cedf1eb);

        android.widget.RelativeLayout c0d3e00d2b = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj5);
        c0d3e00d2b.setOnClickListener($_on_setOnClickListener_c0d3e00d2b);
        c0d3e00d2b.setOnLongClickListener($_on_setOnLongClickListener_c0d3e00d2b);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        android.widget.Button i171ea34f1a = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an1);
        i171ea34f1a.setOnClickListener($_on_setOnClickListener_i171ea34f1a);

        android.widget.Button i0b66a667b1 = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an2);
        i0b66a667b1.setOnClickListener($_on_setOnClickListener_i0b66a667b1);

        android.widget.ImageView cb1d655d23 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx8);
        cb1d655d23.setOnClickListener($_on_setOnClickListener_cb1d655d23);

        android.widget.LinearLayout i38cf17850d = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj15);
        i38cf17850d.setOnTouchListener($_on_setOnTouchListener_i38cf17850d);

        android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
        i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

        android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);
        i8217d42a17.setOnClickListener($_on_setOnClickListener_i8217d42a17);

        android.widget.ImageView i4a4248a0f2 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx5);
        i4a4248a0f2.setOnClickListener($_on_setOnClickListener_i4a4248a0f2);

        android.widget.ImageView i6f2c7751cf = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx6);
        i6f2c7751cf.setOnClickListener($_on_setOnClickListener_i6f2c7751cf);

        android.support.v4.widget.NestedScrollView a6dab51133 = (android.support.v4.widget.NestedScrollView) findViewById(ay, vw, open.cn.awg.pro.R.id.qtgd1);

        __layoutIsLoaded(ay, vw);
    }
}
