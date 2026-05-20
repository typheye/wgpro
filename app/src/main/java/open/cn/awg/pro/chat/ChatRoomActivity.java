/**
 ******************************************************************************
 * @file    ChatRoomActivity.java
 * @author  Typheye
 * @brief   Displays a WearChat room and handles message sending.
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
package open.cn.awg.pro.chat;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import androidx.recyclerview.widget.RecyclerView;

import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.UriPathResolver;
import open.cn.awg.pro.file.FileManagerActivity;
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;


public class ChatRoomActivity extends iActivity {

    public static java.lang.String uid = "";
    public final ChatRoomActivity lei = this, 类 = this;
    public open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

    public open.cn.awg.pro.chat.WearChatRuntimeBridge e10 = new WearChatRuntimeBridge(_APPINFO);
    public boolean xhtrue = true;
    public java.lang.String path = "/data/user/0/open.cn.awg.pro/cache/chat/upload/path";
    public java.lang.String room_name = "";
    private final android.view.View.OnClickListener $_on_setOnClickListener_d2a1859abe = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d2a1859abe(vw);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i6c4a6b953f = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i6c4a6b953f(vw, me);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i11aeec1890 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i11aeec1890(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i84f04cd47e = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i84f04cd47e(vw, me);
        }

    };
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
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i9068f5d66e = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i9068f5d66e(vw, me);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i4d951cdb0e = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i4d951cdb0e(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i8217d42a17 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i8217d42a17(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i3295d3c478 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i3295d3c478(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_fb3bffd427 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_fb3bffd427(vw);
        }

    };
    private final android.widget.CompoundButton.OnCheckedChangeListener $_on_setOnCheckedChangeListener_i4b3a71b80a = new android.widget.CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(android.widget.CompoundButton vw, boolean ic) {
            $_onCheckedChanged_i4b3a71b80a(vw, ic);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i32ee139ba1 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i32ee139ba1(vw, me);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_ff56a8e7f6 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_ff56a8e7f6(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_ff56a8e7f6 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_ff56a8e7f6(vw);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_e76307d04a = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_e76307d04a(vw, me);
        }

    };
    private final androidx.recyclerview.widget.RecyclerView.OnScrollListener $_on_addOnScrollListener_c2f5ccfa3a = new androidx.recyclerview.widget.RecyclerView.OnScrollListener() {

        public void onScrollStateChanged(androidx.recyclerview.widget.RecyclerView vw, int ns) {
            $_onScrollStateChanged_c2f5ccfa3a(vw, ns);
        }

        public void onScrolled(androidx.recyclerview.widget.RecyclerView vw, int dx, int dy) {
            $_onScrolled_c2f5ccfa3a(vw, dx, dy);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i0d2d4d9236 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i0d2d4d9236(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i0d2d4d9236 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i0d2d4d9236(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d26d5d7080(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.i2);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.app.AwgProApplication.getInstance().addActivity(lei);

        java.lang.String set = "/data/user/0/open.cn.awg.pro/settings/set3.inf";

        if (zf.dy(wj.dqwb(set), "true")) {

            java.lang.String te = wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a13");

            java.lang.String[] name = new java.lang.String[]{"title", "text"};

            java.lang.String[] value = new java.lang.String[]{"新手教程", te};
            gj.tz(TextQrDisplayActivity.class, name, value);

        }

        if (e1.isJh()) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.dx(open.cn.awg.pro.R.id.dx_chat_upload).kjd(0);

                }

            });

        }
        wj.xrwb("/data/user/0/open.cn.awg.pro/data/ltlb/showing", "true");
        jz();

    }

    public void __layoutIsLoaded(android.app.Activity ay, android.view.View vw) {

        if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f8/set4.inf"), "true")) {

        }

        i.runlibrary.app.xt$pm pm = xt.pm();
        xt.sbxm(false);

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
        st.xdbj(open.cn.awg.pro.R.id.i2_v).shxtck(false);

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        if (zf.dy(a, "1")) {

            st.xxbj(open.cn.awg.pro.R.id.i2xxbj1).nbj(u, 0, u, 0);
            st.xxbj(open.cn.awg.pro.R.id.xxbj4).nbj(u, 0, u, 0);

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
            st.xxbj(open.cn.awg.pro.R.id.i2xxbj1).nbj(0, 0, 0, 0);
            st.xxbj(open.cn.awg.pro.R.id.xxbj4).nbj(0, 0, 0, 0);

        } else if (zf.dy(a, "3")) {

            st.xxbj(open.cn.awg.pro.R.id.i2xxbj1).nbj(u, 0, u, 0);
            st.xxbj(open.cn.awg.pro.R.id.xxbj4).nbj(u, 0, u, 0);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "4")) {

            int uih = sj.zh().pxzdp(pm.ztl);
            uih = uih + 9;

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, uih + "dp", 0, "9dp");
            wtab.dqfs("center");
            st.xxbj(open.cn.awg.pro.R.id.i2xxbj1).nbj(0, 0, 0, 0);
            st.xxbj(open.cn.awg.pro.R.id.xxbj4).nbj(0, 0, 0, 0);

        }

        i.runlibrary.app.v.xlcd xlcd1 = st.xlcd(open.cn.awg.pro.R.id.ltsup_xlcd1);

        java.lang.String[] sz = new java.lang.String[]{"图像文件", "音频文件", "视频文件", "其他文件"};
        xlcd1.csh(sz);

        i.runlibrary.app.v.xlcd xlcd2 = st.xlcd(open.cn.awg.pro.R.id.ltsup_xlcd2);
        sz = new java.lang.String[]{"应用内置选择器", "系统默认选择器"};
        xlcd2.csh(sz);

    }

    public void jz() {
        uid = sj.hqtz("uid");

        java.lang.String cachehd = "/data/user/0/open.cn.awg.pro/data/ltlb/lbx_title";
        room_name = e10.getTabName(wj.dqwb(cachehd));
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.wb(open.cn.awg.pro.R.id.Tab).zf(room_name);

            }

        });
        e10.lts_csh();
        e10.ltsview(4);
        ltsgxxh();

    }

    public void ltsgxxh() {

        final java.lang.String uid2 = wj.dqwb("/data/user/0/open.cn.awg.pro/data/ltlb/lbx_id");

        final java.lang.String sjhc = "/data/user/0/open.cn.awg.pro/data/ltlb/chat/" + uid2;

        java.lang.String set2 = "/data/user/0/open.cn.awg.pro/settings/f18/set1.inf";

        i.runlibrary.app.sj$zh sjl = sj.zh(wj.dqwb(set2));

        final long tim = sjl.zlong(3000);
        gj.xc(new java.lang.Thread() {

            public void run() {

                final java.lang.String uid = wj.dqwb("/data/user/0/open.cn.awg.pro/data/ltlb/lbx_id");

                final java.lang.String sjhc = "/data/user/0/open.cn.awg.pro/data/ltlb/chat/" + uid;

                if (wj.cz(sjhc) && !zf.dy(wj.dqwb(sjhc), "")) {

                    e10.ltsgx1(false);

                } else {

                    e10.ltsgx3();

                }

                while (xhtrue) {

                    if (wj.cz("/data/user/0/open.cn.awg.pro/data/ltlb/showing")) {

                        gj.zt(tim);

                        if (!wj.cz("/data/user/0/open.cn.awg.pro/data/ltlb/disTouch")) {

                            e10.ltsgx2();

                        }

                        if (wj.cz(sjhc) && !zf.dy(zf.qctwkg(wj.dqwb(sjhc)), "")) {

                            try {

                                java.lang.String back = wj.dqwb(sjhc);

                                i.runlibrary.app.zf$json jo = zf.json(back);

                                org.json.JSONObject json = jo.json;

                                org.json.JSONArray list = jo.dxlb(json, "data");

                                int i = jo.cd(list);

                                int i2 = open.cn.awg.pro.chat.WearChatRuntimeBridge.spq3.zs() - 2;

                                if (i != i2 && i != 0 && i2 != 0) {

                                    e10.ltsgx1(true);

                                }

                            } catch (java.lang.Throwable __$_e__) {

                            }

                        }

                    }

                }

            }

        });

    }

    public void shouJp() {

        try {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    InputMethodManager imm = (InputMethodManager) lei.getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(lei.getWindow().getDecorView().getWindowToken(), 0);

                }

            });

        } catch (java.lang.Throwable __$_e__) {

        }

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
        wj.xrwb("/data/user/0/open.cn.awg.pro/data/ltlb/showing", "true");

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));
        st.xdbj(open.cn.awg.pro.R.id.i2_v).shxtck(false);

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        final i.runlibrary.app.v.an an = st.an(open.cn.awg.pro.R.id.an_upload);

        final i.runlibrary.app.v.an an2 = st.an(open.cn.awg.pro.R.id.an_disupload);

        final i.runlibrary.app.v.dx dx_chat_upload = st.dx(open.cn.awg.pro.R.id.dx_chat_upload);

        final i.runlibrary.app.v.xlcd ltsup_xlcd1 = st.xlcd(open.cn.awg.pro.R.id.ltsup_xlcd1);

        final i.runlibrary.app.v.xlcd ltsup_xlcd2 = st.xlcd(open.cn.awg.pro.R.id.ltsup_xlcd2);

        if (wj.cz(path) && !zf.dy(wj.dqwb(path), "") && !zf.dy(wj.dqwb(path), "null") && zf.dy(an.tszf(), "选择文件")) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    an.kjd(0);
                    an.tszf("已选择•开始上传");
                    ltsup_xlcd1.st.setEnabled(false);
                    ltsup_xlcd1.kjd(8);
                    ltsup_xlcd2.st.setEnabled(false);
                    ltsup_xlcd2.kjd(8);
                    dx_chat_upload.st.setEnabled(false);
                    an2.kjd(0);

                }

            });

        }

    }

    public void onStop() {
        super.onStop();
        wj.sc("/data/user/0/open.cn.awg.pro/data/ltlb/showing");

    }

    public void onStart() {
        super.onStart();
        wj.xrwb("/data/user/0/open.cn.awg.pro/data/ltlb/showing", "true");

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));
        st.xdbj(open.cn.awg.pro.R.id.i2_v).shxtck(false);

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        i.runlibrary.app.v.v7lb ltsv7lb1 = st.v7lb(open.cn.awg.pro.R.id.ltsv7lb1);
        ltsv7lb1.v.requestFocus();

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

    }

    public void onRestart() {
        super.onRestart();
        wj.xrwb("/data/user/0/open.cn.awg.pro/data/ltlb/showing", "true");

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));
        st.xdbj(open.cn.awg.pro.R.id.i2_v).shxtck(false);

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public void onResume() {
        super.onResume();
        wj.xrwb("/data/user/0/open.cn.awg.pro/data/ltlb/showing", "true");

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));
        st.xdbj(open.cn.awg.pro.R.id.i2_v).shxtck(false);

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public void onPause() {
        super.onPause();
        wj.sc("/data/user/0/open.cn.awg.pro/data/ltlb/showing");

    }

    public void onActivityResult(int re, int rc, android.content.Intent it) {
        super.onActivityResult(re, rc, it);

        if (re == 1) {

            if (rc == -1) {

                java.lang.String realPath = null;

                try {

                    realPath = UriPathResolver.getByIntent(lei, it);

                } catch (java.lang.Throwable e) {

                }

                if (!zf.dy(realPath, null)) {

                    realPath = wj.hqml(realPath);
                    wj.xrwb(path, realPath);

                    final i.runlibrary.app.v.an an = st.an(open.cn.awg.pro.R.id.an_upload);

                    final i.runlibrary.app.v.an an2 = st.an(open.cn.awg.pro.R.id.an_disupload);

                    final i.runlibrary.app.v.dx dx_chat_upload = st.dx(open.cn.awg.pro.R.id.dx_chat_upload);

                    final i.runlibrary.app.v.xlcd ltsup_xlcd1 = st.xlcd(open.cn.awg.pro.R.id.ltsup_xlcd1);

                    final i.runlibrary.app.v.xlcd ltsup_xlcd2 = st.xlcd(open.cn.awg.pro.R.id.ltsup_xlcd2);

                    if (zf.dy(an.tszf(), "选择文件")) {

                        gj.jmxc(new java.lang.Runnable() {

                            public void run() {

                                an.kjd(0);
                                an.tszf("已选择•开始上传");
                                ltsup_xlcd1.st.setEnabled(false);
                                ltsup_xlcd1.kjd(8);
                                ltsup_xlcd2.st.setEnabled(false);
                                ltsup_xlcd2.kjd(8);
                                dx_chat_upload.st.setEnabled(false);
                                an2.kjd(0);

                            }

                        });

                    }

                }

            }

        }

    }

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.app.AwgProApplication.getInstance().removeActivity(lei);
        xhtrue = false;
        wj.sc("/data/user/0/open.cn.awg.pro/data/ltlb/showing");

    }

    public boolean onKeyDown(int kc, android.view.KeyEvent ke) {

        if (kc == 4) {

            if (st.xxbj(open.cn.awg.pro.R.id.i2xxbj1).kjd() == 0) {

                gj.gb();

            } else {

            }

        }
        return false;

    }

    private void $_onClick_d26d5d7080(android.view.View vw) {

        java.lang.String cachehd = "/data/user/0/open.cn.awg.pro/data/ltlb/lbx_title";

        final java.lang.String name = wj.dqwb(cachehd);

        if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "[有新消息]")) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    if (st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd() == 0) {

                        st.wb(open.cn.awg.pro.R.id.Tab).zf("< ");

                    }
                    st.v7lb(open.cn.awg.pro.R.id.ltsv7lb1).xzwz("top");

                }

            });

        } else if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), room_name)) {

            gj.gb();

        }

    }

    private void $_onClick_i0d2d4d9236(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                java.lang.String z = st.wb(open.cn.awg.pro.R.id.wb3).zf();
                z = zf.qc(z, "[提示] ");

                if (zf.dy(st.wb(open.cn.awg.pro.R.id.wb3).zf(), "[提示] 发送成功")) {

                    z = z + "\n(ps:长按提示可隐藏哦)";

                }
                e1.tsk("详细信息", z);

            }

        });

    }

    private boolean $_onLongClick_i0d2d4d9236(android.view.View vw) {

        if (zf.dy(st.wb(open.cn.awg.pro.R.id.wb3).zf(), "[提示] 发送成功")) {

            e10.ltjmshowgb();

        }
        return true;

    }

    private void $_onScrollStateChanged_c2f5ccfa3a(androidx.recyclerview.widget.RecyclerView vw, int ns) {

        if (ns == RecyclerView.SCROLL_STATE_IDLE) {

            if (!vw.canScrollVertically(-1)) {

                wj.xrwb("/data/user/0/open.cn.awg.pro/data/ltlb/hdva", "-1");

            } else if (!vw.canScrollVertically(1)) {

                java.lang.String cachehd = "/data/user/0/open.cn.awg.pro/data/ltlb/lbx_title";

                final java.lang.String name = wj.dqwb(cachehd);

                if (st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd() == 0) {

                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            st.wb(open.cn.awg.pro.R.id.Tab).zf(room_name);

                        }

                    });

                }
                wj.xrwb("/data/user/0/open.cn.awg.pro/data/ltlb/hdva", "0");

            } else {

                wj.xrwb("/data/user/0/open.cn.awg.pro/data/ltlb/hdva", "1");

            }

        }

    }

    private void $_onScrolled_c2f5ccfa3a(androidx.recyclerview.widget.RecyclerView vw, int dx, int dy) {

    }

    private boolean $_onTouch_e76307d04a(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private void $_onClick_ff56a8e7f6(android.view.View vw) {

        if (st.xxbj(open.cn.awg.pro.R.id.xxbjcd).kjd() == 0) {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("< 房间详情");
            st.xxbj(open.cn.awg.pro.R.id.i2xxbj1).kjd(0);
            st.xxbj(open.cn.awg.pro.R.id.xxbjcd).kjd(8);
            st.kp(open.cn.awg.pro.R.id.kp7).kjd(0);

        } else {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("菜单");
            st.xxbj(open.cn.awg.pro.R.id.i2xxbj1).kjd(8);
            st.xxbj(open.cn.awg.pro.R.id.xxbjcd).kjd(0);
            st.kp(open.cn.awg.pro.R.id.kp7).kjd(8);

        }

    }

    private boolean $_onLongClick_ff56a8e7f6(android.view.View vw) {
        st.kp(open.cn.awg.pro.R.id.kp7).kjd(8);
        e10.ltspost(1);
        return true;

    }

    private boolean $_onTouch_i32ee139ba1(android.view.View vw, android.view.MotionEvent me) {

        if (!vw.isFocused()) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(open.cn.awg.pro.R.id.qtgd1);
                    qtgd1.v.requestFocus();

                }

            });

        }
        return false;

    }

    private void $_onCheckedChanged_i4b3a71b80a(android.widget.CompoundButton vw, boolean ic) {

        if (ic) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.xxbj(open.cn.awg.pro.R.id.xxbjyc3).kjd(0);

                }

            });

        } else {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.xxbj(open.cn.awg.pro.R.id.xxbjyc3).kjd(8);

                }

            });

        }

    }

    private void $_onClick_fb3bffd427(android.view.View vw) {

        i.runlibrary.app.v.xlcd xlcd1 = st.xlcd(open.cn.awg.pro.R.id.ltsup_xlcd1);

        i.runlibrary.app.v.xlcd xlcd2 = st.xlcd(open.cn.awg.pro.R.id.ltsup_xlcd2);

        final i.runlibrary.app.v.an an = st.an(open.cn.awg.pro.R.id.an_upload);

        final java.lang.String choose = "/data/user/0/open.cn.awg.pro/cache/file/choose";

        if (zf.dy(an.tszf(), "选择文件")) {

            wj.sc(path);

            int sdk = xt.sbxx().sdk;

            java.lang.String ftitle = "/data/user/0/open.cn.awg.pro/cache/file/title";
            wj.xrwb(ftitle, "< 选择附件");

            if (xlcd2.dqzsxmxh() == 0) {

                if (xlcd1.dqzsxmxh() == 0) {

                    wj.xrwb(choose, "h1");

                } else if (xlcd1.dqzsxmxh() == 1) {

                    wj.xrwb(choose, "h2");

                } else if (xlcd1.dqzsxmxh() == 2) {

                    wj.xrwb(choose, "h3");

                } else if (xlcd1.dqzsxmxh() == 3) {

                    wj.xrwb(choose, "h4");

                }

                try {

                    i.runlibrary.app.xt$qxgl a = xt.qxgl();

                    if (a.qx("android.permission.WRITE_EXTERNAL_STORAGE")) {

                        if (sdk < 30) {

                            java.lang.String lj = wj.hqml("%");

                            final java.lang.String[] name = new java.lang.String[]{"url", "root"};

                            final java.lang.String[] value = new java.lang.String[]{lj, lj};
                            gj.jmxc(new java.lang.Runnable() {

                                public void run() {

                                    gj.tz(FileManagerActivity.class, name, value);

                                }

                            });

                        } else {

                            java.lang.String lj = "/storage/emulated/0/";

                            final java.lang.String[] name = new java.lang.String[]{"url", "root"};

                            final java.lang.String[] value = new java.lang.String[]{lj, lj};
                            gj.jmxc(new java.lang.Runnable() {

                                public void run() {

                                    gj.tz(FileManagerActivity.class, name, value);

                                }

                            });

                        }

                    } else {

                        e1.tsk("提示", "您未授权应用\"修改或删除您共享存储空间中的内容\"，请先前往设置授权后再使用");

                    }

                } catch (java.lang.Throwable e) {

                    e1.upload_error(e, "i2.*");

                }

            } else if (xlcd2.dqzsxmxh() == 1) {

                try {

                    android.content.Intent intent = new android.content.Intent(android.content.Intent.ACTION_GET_CONTENT);
                    if (xlcd1.dqzsxmxh() == 0)
                        intent.setType("image/*");
                    else if (xlcd1.dqzsxmxh() == 1)
                        intent.setType("audio/*");
                    else if (xlcd1.dqzsxmxh() == 2)
                        intent.setType("video/*");
                    else if (xlcd1.dqzsxmxh() == 3)
                        intent.setType("*/*");
                    lei.startActivityForResult(intent, 1);

                } catch (java.lang.Throwable e) {

                    e1.upload_error(e, "i2.*");

                }

            }

        } else if (zf.dy(an.tszf(), "已选择•开始上传")) {

            e10.upload(wj.dqwb(path), xlcd1.dqzsxmxh());

        } else if (zf.dy(an.tszf(), "上传成功")) {

            e1.tsk("提示", "已成功上传文件");

        }

    }

    private void $_onClick_i3295d3c478(android.view.View vw) {
        wj.sc(path);

        final i.runlibrary.app.v.an an = st.an(open.cn.awg.pro.R.id.an_upload);

        final i.runlibrary.app.v.an an2 = st.an(open.cn.awg.pro.R.id.an_disupload);

        final i.runlibrary.app.v.dx dx_chat_upload = st.dx(open.cn.awg.pro.R.id.dx_chat_upload);

        final i.runlibrary.app.v.xlcd ltsup_xlcd1 = st.xlcd(open.cn.awg.pro.R.id.ltsup_xlcd1);

        final i.runlibrary.app.v.xlcd ltsup_xlcd2 = st.xlcd(open.cn.awg.pro.R.id.ltsup_xlcd2);
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                an.kjd(0);
                an.tszf("选择文件");
                an2.kjd(8);
                ltsup_xlcd1.st.setEnabled(true);
                ltsup_xlcd1.kjd(0);
                ltsup_xlcd2.st.setEnabled(true);
                ltsup_xlcd2.kjd(0);
                dx_chat_upload.st.setEnabled(true);

            }

        });

    }

    private void $_onClick_i8217d42a17(android.view.View vw) {

        final i.runlibrary.app.v.an an = st.an(open.cn.awg.pro.R.id.an_upload);

        if (zf.dy(an.tszf(), "上传中")) {

            e1.tsk("提示", "请耐心等待文件上传完毕");

        } else {

            shouJp();
            e10.ltspost(0);
            st.kp(open.cn.awg.pro.R.id.kp7).kjd(0);

        }

    }

    private void $_onClick_i4d951cdb0e(android.view.View vw) {
        shouJp();
        e10.post();

    }

    private boolean $_onTouch_i9068f5d66e(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private void $_onClick_i4a4248a0f2(android.view.View vw) {

        if (st.xxbj(open.cn.awg.pro.R.id.xxbjcd).kjd() == 0) {

            st.wb(open.cn.awg.pro.R.id.Tab).zf(room_name);
            st.xxbj(open.cn.awg.pro.R.id.i2xxbj1).kjd(0);
            st.xxbj(open.cn.awg.pro.R.id.xxbjcd).kjd(8);
            st.kp(open.cn.awg.pro.R.id.kp7).kjd(0);
            e10.ltstotop();

        } else {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("菜单");
            st.xxbj(open.cn.awg.pro.R.id.i2xxbj1).kjd(8);
            st.xxbj(open.cn.awg.pro.R.id.xxbjcd).kjd(0);
            st.kp(open.cn.awg.pro.R.id.kp7).kjd(8);

        }

    }

    private void $_onClick_i6f2c7751cf(android.view.View vw) {

        if (st.xxbj(open.cn.awg.pro.R.id.xxbjcd).kjd() == 0) {

            st.wb(open.cn.awg.pro.R.id.Tab).zf(room_name);
            st.xxbj(open.cn.awg.pro.R.id.i2xxbj1).kjd(0);
            st.xxbj(open.cn.awg.pro.R.id.xxbjcd).kjd(8);

        } else {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("菜单");
            st.xxbj(open.cn.awg.pro.R.id.i2xxbj1).kjd(8);
            st.xxbj(open.cn.awg.pro.R.id.xxbjcd).kjd(0);
            st.kp(open.cn.awg.pro.R.id.kp7).kjd(8);

        }
        e10.ltspost(1);

    }

    private boolean $_onTouch_i84f04cd47e(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private void $_onClick_i34dc7bf20a(android.view.View vw) {

        if (st.xxbj(open.cn.awg.pro.R.id.xxbjcd).kjd() == 0) {

            st.wb(open.cn.awg.pro.R.id.Tab).zf(room_name);
            st.xxbj(open.cn.awg.pro.R.id.i2xxbj1).kjd(0);
            st.xxbj(open.cn.awg.pro.R.id.xxbjcd).kjd(8);
            st.kp(open.cn.awg.pro.R.id.kp7).kjd(0);
            e10.ltstobottom();

        } else {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("菜单");
            st.xxbj(open.cn.awg.pro.R.id.i2xxbj1).kjd(8);
            st.xxbj(open.cn.awg.pro.R.id.xxbjcd).kjd(0);
            st.kp(open.cn.awg.pro.R.id.kp7).kjd(8);

        }

    }

    private void $_onClick_i11aeec1890(android.view.View vw) {

        if (st.xxbj(open.cn.awg.pro.R.id.xxbjcd).kjd() == 0) {

            st.wb(open.cn.awg.pro.R.id.Tab).zf(room_name);
            st.xxbj(open.cn.awg.pro.R.id.i2xxbj1).kjd(0);
            st.xxbj(open.cn.awg.pro.R.id.xxbjcd).kjd(8);
            st.kp(open.cn.awg.pro.R.id.kp7).kjd(0);

        } else {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("菜单");
            st.xxbj(open.cn.awg.pro.R.id.i2xxbj1).kjd(8);
            st.xxbj(open.cn.awg.pro.R.id.xxbjcd).kjd(0);
            st.kp(open.cn.awg.pro.R.id.kp7).kjd(8);

        }
        gj.gb();

    }

    private boolean $_onTouch_i6c4a6b953f(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private void $_onClick_d2a1859abe(android.view.View vw) {

        if (st.xxbj(open.cn.awg.pro.R.id.xxbjcd).kjd() == 0) {

            st.wb(open.cn.awg.pro.R.id.Tab).zf(room_name);
            st.xxbj(open.cn.awg.pro.R.id.i2xxbj1).kjd(0);
            st.xxbj(open.cn.awg.pro.R.id.xxbjcd).kjd(8);
            st.kp(open.cn.awg.pro.R.id.kp7).kjd(0);

        } else {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("菜单");
            st.xxbj(open.cn.awg.pro.R.id.i2xxbj1).kjd(8);
            st.xxbj(open.cn.awg.pro.R.id.xxbjcd).kjd(0);
            st.kp(open.cn.awg.pro.R.id.kp7).kjd(8);

        }

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
        d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

        android.widget.TextView i0d2d4d9236 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb3);
        i0d2d4d9236.setOnClickListener($_on_setOnClickListener_i0d2d4d9236);
        i0d2d4d9236.setOnLongClickListener($_on_setOnLongClickListener_i0d2d4d9236);

        android.support.v7.widget.RecyclerView c2f5ccfa3a = (android.support.v7.widget.RecyclerView) findViewById(ay, vw, open.cn.awg.pro.R.id.ltsv7lb1);
        c2f5ccfa3a.addOnScrollListener($_on_addOnScrollListener_c2f5ccfa3a);

        android.widget.LinearLayout e76307d04a = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.i2xxbj1);
        e76307d04a.setOnTouchListener($_on_setOnTouchListener_e76307d04a);

        android.widget.TextView ff56a8e7f6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb1);
        ff56a8e7f6.setOnClickListener($_on_setOnClickListener_ff56a8e7f6);
        ff56a8e7f6.setOnLongClickListener($_on_setOnLongClickListener_ff56a8e7f6);

        android.widget.TextView e943ebd71e = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb4);

        android.widget.LinearLayout i5f37bb7e71 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.ltssxxxbj3);

        android.widget.EditText i32ee139ba1 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjk1);
        i32ee139ba1.setOnTouchListener($_on_setOnTouchListener_i32ee139ba1);

        android.widget.CheckBox i4b3a71b80a = (android.widget.CheckBox) findViewById(ay, vw, open.cn.awg.pro.R.id.dx_chat_upload);
        i4b3a71b80a.setOnCheckedChangeListener($_on_setOnCheckedChangeListener_i4b3a71b80a);

        android.widget.Button fb3bffd427 = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an_upload);
        fb3bffd427.setOnClickListener($_on_setOnClickListener_fb3bffd427);

        android.widget.Button i3295d3c478 = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an_disupload);
        i3295d3c478.setOnClickListener($_on_setOnClickListener_i3295d3c478);

        android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);
        i8217d42a17.setOnClickListener($_on_setOnClickListener_i8217d42a17);

        android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
        i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

        android.widget.LinearLayout i9068f5d66e = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj4);
        i9068f5d66e.setOnTouchListener($_on_setOnTouchListener_i9068f5d66e);

        android.widget.ImageView i4a4248a0f2 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx5);
        i4a4248a0f2.setOnClickListener($_on_setOnClickListener_i4a4248a0f2);

        android.widget.ImageView i6f2c7751cf = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx6);
        i6f2c7751cf.setOnClickListener($_on_setOnClickListener_i6f2c7751cf);

        android.widget.LinearLayout i84f04cd47e = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj7);
        i84f04cd47e.setOnTouchListener($_on_setOnTouchListener_i84f04cd47e);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        android.widget.ImageView i11aeec1890 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx4);
        i11aeec1890.setOnClickListener($_on_setOnClickListener_i11aeec1890);

        android.widget.LinearLayout i6c4a6b953f = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj6);
        i6c4a6b953f.setOnTouchListener($_on_setOnTouchListener_i6c4a6b953f);

        android.widget.LinearLayout d2a1859abe = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbjcd);
        d2a1859abe.setOnClickListener($_on_setOnClickListener_d2a1859abe);

        __layoutIsLoaded(ay, vw);
    }
}
