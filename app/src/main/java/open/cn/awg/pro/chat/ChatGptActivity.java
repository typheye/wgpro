/**
 ******************************************************************************
 * @file    ChatGptActivity.java
 * @author  Typheye
 * @brief   ChatGPT plugin screen.
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
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import androidx.recyclerview.widget.RecyclerView;

import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.ui.list.RuntimeMessageItemActivity;
import open.cn.awg.pro.ui.widget.XLinearLayoutManager;


public class ChatGptActivity extends iActivity {

    public static i.runlibrary.app.v.v7lb$UserAdapter spqc;
    public static i.runlibrary.app.v.v7lb lbc;
    public static android.support.v7.widget.LinearLayoutManager mLayoutManager;
    public static android.content.Context context;
    public final ChatGptActivity lei = this, 类 = this;
    public open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public java.lang.String namer = "User";
    public boolean isOnce = false;
    public java.lang.String ml = "/data/user/0/open.cn.awg.pro/data/chatgpt/chat";
    private final android.view.View.OnClickListener $_on_setOnClickListener_i6c4a6b953f = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i6c4a6b953f(vw);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i3f6c9f386b = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i3f6c9f386b(vw, me);
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
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i84f04cd47e = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i84f04cd47e(vw, me);
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
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_e1433e26d6 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_e1433e26d6(vw, me);
        }

    };
    private final androidx.recyclerview.widget.RecyclerView.OnScrollListener $_on_addOnScrollListener_f085d7c0f8 = new androidx.recyclerview.widget.RecyclerView.OnScrollListener() {

        public void onScrollStateChanged(androidx.recyclerview.widget.RecyclerView vw, int ns) {
            $_onScrollStateChanged_f085d7c0f8(vw, ns);
        }

        public void onScrolled(androidx.recyclerview.widget.RecyclerView vw, int dx, int dy) {
            $_onScrolled_f085d7c0f8(vw, dx, dy);
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
        setContentView(open.cn.awg.pro.R.layout.l5_a5_chatg_d0312_y2023);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.app.AwgProApplication.getInstance().addActivity(lei);
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
        st.xdbj(open.cn.awg.pro.R.id.l5_a5_chatg_d0312_y2023_v).shxtck(false);

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        if (zf.dy(a, "1")) {

            st.xxbj(open.cn.awg.pro.R.id.xxbj1).nbj(u, 0, u, 0);
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
            st.xxbj(open.cn.awg.pro.R.id.xxbj1).nbj(0, 0, 0, 0);
            st.xxbj(open.cn.awg.pro.R.id.xxbj4).nbj(0, 0, 0, 0);

        } else if (zf.dy(a, "3")) {

            st.xxbj(open.cn.awg.pro.R.id.xxbj1).nbj(u, 0, u, 0);
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
            st.xxbj(open.cn.awg.pro.R.id.xxbj1).nbj(0, 0, 0, 0);
            st.xxbj(open.cn.awg.pro.R.id.xxbj4).nbj(0, 0, 0, 0);

        }

    }

    public void tobottom() {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                lbc.st.scrollToPosition(spqc.zs() - 1);

            }

        });

    }

    public void totop() {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                lbc.st.scrollToPosition(0);

            }

        });

    }

    public void del() {
        gj.xc(new java.lang.Thread() {

            public void run() {

                show(1);
                gj.zt(500);
                wj.sc(ml);
                gj.zt(250);
                update();
                gj.zt(250);
                show(0);

            }

        });

    }

    public void show(int i) {

        if (i == 0) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    lbc.v.requestFocus();
                    st.wb(open.cn.awg.pro.R.id.Tab).zf("< ChatGPT");
                    st.bjk(open.cn.awg.pro.R.id.bjk1).zf("");
                    st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj3s).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(8);
                    st.kp(open.cn.awg.pro.R.id.kp7).kjd(0);

                }

            });

        } else if (i == 1) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.wb(open.cn.awg.pro.R.id.Tab).zf("ChatGPT");
                    st.bjk(open.cn.awg.pro.R.id.bjk1).zf("");
                    st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj3s).kjd(0);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(8);
                    st.kp(open.cn.awg.pro.R.id.kp7).kjd(8);

                }

            });

        } else if (i == 2) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.wb(open.cn.awg.pro.R.id.Tab).zf("发送消息");
                    st.bjk(open.cn.awg.pro.R.id.bjk1).zf("");
                    st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj3s).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(0);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(8);
                    st.kp(open.cn.awg.pro.R.id.kp7).kjd(8);

                }

            });

        } else if (i == 3) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.wb(open.cn.awg.pro.R.id.Tab).zf("ChatGPT");
                    st.bjk(open.cn.awg.pro.R.id.bjk1).zf("");
                    st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj3s).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(0);
                    st.kp(open.cn.awg.pro.R.id.kp7).kjd(8);

                }

            });

        }

    }

    public void csh() {
        lbc = st.v7lb(open.cn.awg.pro.R.id.v7lb1);

        context = open.cn.awg.pro.app.AwgProApplication.getContext();
        mLayoutManager = new XLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        lbc.st.setLayoutManager(mLayoutManager);
        spqc = lbc.v7lbspq(RuntimeMessageItemActivity.class, open.cn.awg.pro.R.layout.c21, new i.runlibrary.app.v.v7lb$OnUserAdapterView() {

            public void getView(i.runlibrary.app.v.v7lb$UserAdapter ua, int pn, android.view.View vw) {

                i.runlibrary.app.sj$lb lbsj = sj.lb(vw);

                final int f = sj.zh(lbsj.lbsj(pn, -1)).zint();

                if (f == -1) {

                    java.lang.String color0 = zf.zf(open.cn.awg.pro.R.color.colorTextTrue2);

                    java.lang.String color1 = zf.zf(open.cn.awg.pro.R.color.colorBlack);

                    java.lang.String color2 = zf.zf(open.cn.awg.pro.R.color.colorTextU1);
                    st.wb(vw, open.cn.awg.pro.R.id.wb1).zfys(color1);
                    st.wb(vw, open.cn.awg.pro.R.id.wb3).zfys(color2);
                    st.kp(vw, open.cn.awg.pro.R.id.kp1).kpbjys(color0);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).dqfs("right");
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).kjd(0);
                    st.xdbj(vw, open.cn.awg.pro.R.id.c21_v).xydj(true);
                    st.xdbj(vw, open.cn.awg.pro.R.id.c21_v).xyca(true);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj3).kjd(8);

                } else if (f == 0) {

                    java.lang.String color0 = "#00000000";

                    java.lang.String color1 = "#00000000";

                    java.lang.String color2 = "#00000000";
                    st.wb(vw, open.cn.awg.pro.R.id.wb1).zfys(color1);
                    st.wb(vw, open.cn.awg.pro.R.id.wb3).zfys(color2);
                    st.kp(vw, open.cn.awg.pro.R.id.kp1).kpbjys(color0);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).dqfs("left");
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).kjd(0);
                    st.xdbj(vw, open.cn.awg.pro.R.id.c21_v).xydj(false);
                    st.xdbj(vw, open.cn.awg.pro.R.id.c21_v).xyca(false);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj3).kjd(0);

                } else if (f == 1) {

                    java.lang.String color0 = zf.zf(open.cn.awg.pro.R.color.colorWBK);

                    java.lang.String color1 = zf.zf(open.cn.awg.pro.R.color.colorTextU1);

                    java.lang.String color2 = zf.zf(open.cn.awg.pro.R.color.colorTextU1);
                    st.wb(vw, open.cn.awg.pro.R.id.wb1).zfys(color1);
                    st.wb(vw, open.cn.awg.pro.R.id.wb3).zfys(color2);
                    st.kp(vw, open.cn.awg.pro.R.id.kp1).kpbjys(color0);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).dqfs("left");
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).kjd(0);
                    st.xdbj(vw, open.cn.awg.pro.R.id.c21_v).xydj(true);
                    st.xdbj(vw, open.cn.awg.pro.R.id.c21_v).xyca(true);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj3).kjd(8);

                } else {

                    java.lang.String color0 = zf.zf(open.cn.awg.pro.R.color.colorWBK);

                    java.lang.String color1 = zf.zf(open.cn.awg.pro.R.color.colorTextU1);

                    java.lang.String color2 = zf.zf(open.cn.awg.pro.R.color.colorTextU1);
                    st.wb(vw, open.cn.awg.pro.R.id.wb1).zfys(color1);
                    st.wb(vw, open.cn.awg.pro.R.id.wb3).zfys(color2);
                    st.kp(vw, open.cn.awg.pro.R.id.kp1).kpbjys(color0);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).dqfs("left");
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj1).kjd(0);
                    st.xdbj(vw, open.cn.awg.pro.R.id.c21_v).xydj(true);
                    st.xdbj(vw, open.cn.awg.pro.R.id.c21_v).xyca(true);
                    st.xxbj(vw, open.cn.awg.pro.R.id.xxbj3).kjd(8);

                }

            }

        });

    }

    public void update() {

        if (wj.cz(ml) && !zf.dy(wj.dqwb(ml), "")) {

            gj.xc(new java.lang.Thread() {

                public void run() {

                    if (!isOnce) {

                        gj.zt(1000);

                    }

                    int[] kj = new int[]{open.cn.awg.pro.R.id.wb1, open.cn.awg.pro.R.id.wb2, open.cn.awg.pro.R.id.wb3, -1};

                    java.lang.Object[] jk = new java.lang.Object[]{"", "", "", 0};

                    int size = 0;

                    java.util.ArrayList arr = new java.util.ArrayList();

                    java.lang.String hcw = wj.dqwb(ml);

                    java.lang.String[] hcw2 = zf.fg(hcw, "\n", true);

                    for (java.lang.String hcw3 : hcw2) {

                        hcw3 = zf.qctwkg(hcw3);

                        if (hcw3.length() != 0) {

                            try {

                                java.lang.String jname = zf.qc(hcw3, 0, 1);

                                if (zf.dy(jname, "Q")) {

                                    jname = "User";

                                } else {

                                    jname = "ChatGPT";

                                }

                                java.lang.String jdata = zf.qc(hcw3, 22);
                                jdata = zf.th(jdata, "<br>", "\n", true);

                                java.lang.String date = zf.qc(hcw3, 2, 21);
                                size++;

                                if (zf.dy(jname, namer)) {

                                    jk = new java.lang.Object[]{jdata, jname, date, -1};

                                } else {

                                    jk = new java.lang.Object[]{jdata, jname, date, 1};

                                }

                                java.util.HashMap hs = new java.util.HashMap();

                                for (int ii = 0; ii < kj.length; ii++) {

                                    hs.put(kj[ii], jk[ii]);

                                }
                                arr.add(hs);

                            } catch (java.lang.Throwable __$_e__) {

                            }

                        }

                    }

                    java.util.HashMap hs = new java.util.HashMap();
                    hs.put(kj[0], "");
                    hs.put(kj[1], "");
                    hs.put(kj[2], "");
                    hs.put(kj[3], 0);
                    arr.add(hs);
                    arr.add(hs);
                    spqc.list_S = arr;
                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            spqc.sx();

                        }

                    });

                    if (!isOnce) {

                        isOnce = true;
                        show(0);
                        tobottom();

                    }

                }

            });

        } else {

            gj.xc(new java.lang.Thread() {

                public void run() {

                    spqc.sc();

                    if (!isOnce) {

                        gj.zt(1000);
                        isOnce = true;
                        show(0);

                    }

                }

            });

        }

    }

    public void add(java.lang.Object name, java.lang.Object msg) {
        msg = zf.qctwkg(msg);
        msg = zf.th(msg, "\n", "<br>", true);

        java.lang.String d = name + " " + xt.sj(0) + " " + msg;

        if (wj.cz(ml) && !zf.dy(wj.dqwb(ml), "")) {

            wj.xrwb(ml, wj.dqwb(ml) + "\n" + d);

        } else {

            wj.xrwb(ml, d);

        }
        update();

    }

    public void jz() {
        show(1);
        csh();
        update();

    }

    public void post() {
        gj.xc(new java.lang.Thread() {

            public void run() {

                java.lang.String a = st.bjk(open.cn.awg.pro.R.id.bjk1).zf();
                a = zf.qctwkg(a);

                if (a.length() == 0) {

                    e1.tsk("提示", "消息不能为空");

                } else {

                    show(1);
                    add("Q", a);
                    gj.zt(500);
                    show(0);

                    java.lang.String bak = wl.hq("https://api.sayqz.com/chatgpt/?msg=" + a + "&sys=", "utf-8");

                    if (!zf.dy(bak, null)) {

                        try {

                            i.runlibrary.app.zf$json jo = zf.json(bak);

                            org.json.JSONObject json = jo.json;

                            org.json.JSONArray list = jo.dxlb(json, "choices");

                            int size = jo.cd(list);

                            while (size > 0) {

                                size = size - 1;

                                org.json.JSONObject dx = jo.dx(list, size);

                                java.lang.Object msg = jo.hq(dx, "message");
                                bak = e1.readJson(String.valueOf(msg), "content");

                            }
                            add("A", bak);

                        } catch (java.lang.Throwable __$_e__) {

                            add("A", "很抱歉，程序似乎出现了异常");

                        }

                    } else {

                        add("A", "很抱歉，网络连接不通畅，请检查后重试");

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

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));
        st.xdbj(open.cn.awg.pro.R.id.l5_a5_chatg_d0312_y2023_v).shxtck(false);

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public void onStart() {
        super.onStart();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));
        st.xdbj(open.cn.awg.pro.R.id.l5_a5_chatg_d0312_y2023_v).shxtck(false);

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        i.runlibrary.app.v.v7lb v7lb1 = st.v7lb(open.cn.awg.pro.R.id.v7lb1);
        v7lb1.v.requestFocus();

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

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));
        st.xdbj(open.cn.awg.pro.R.id.l5_a5_chatg_d0312_y2023_v).shxtck(false);

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public void onResume() {
        super.onResume();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));
        st.xdbj(open.cn.awg.pro.R.id.l5_a5_chatg_d0312_y2023_v).shxtck(false);

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.app.AwgProApplication.getInstance().removeActivity(lei);

    }

    public boolean onKeyDown(int kc, android.view.KeyEvent ke) {

        if (kc == 4) {

            if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< ChatGPT")) {

                gj.gb();

            } else {

            }

        }
        return false;

    }

    private void $_onClick_d26d5d7080(android.view.View vw) {

        if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< ChatGPT")) {

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

        }
        return true;

    }

    private void $_onScrollStateChanged_f085d7c0f8(androidx.recyclerview.widget.RecyclerView vw, int ns) {

        if (ns == RecyclerView.SCROLL_STATE_IDLE) {

            if (!vw.canScrollVertically(-1)) {

            } else if (!vw.canScrollVertically(1)) {

            } else {

            }

        }

    }

    private void $_onScrolled_f085d7c0f8(androidx.recyclerview.widget.RecyclerView vw, int dx, int dy) {

    }

    private boolean $_onTouch_e1433e26d6(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private void $_onClick_ff56a8e7f6(android.view.View vw) {
        show(3);

    }

    private boolean $_onLongClick_ff56a8e7f6(android.view.View vw) {
        show(2);
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

    private void $_onClick_i8217d42a17(android.view.View vw) {
        shouJp();
        show(0);

    }

    private void $_onClick_i4d951cdb0e(android.view.View vw) {
        shouJp();
        post();

    }

    private boolean $_onTouch_i9068f5d66e(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private void $_onClick_i34dc7bf20a(android.view.View vw) {
        totop();
        show(0);

    }

    private void $_onClick_i11aeec1890(android.view.View vw) {
        show(2);

    }

    private boolean $_onTouch_i84f04cd47e(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private void $_onClick_i4a4248a0f2(android.view.View vw) {
        tobottom();
        show(0);

    }

    private void $_onClick_i6f2c7751cf(android.view.View vw) {
        del();
        show(0);

    }

    private boolean $_onTouch_i3f6c9f386b(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private void $_onClick_i6c4a6b953f(android.view.View vw) {
        show(0);

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

        android.support.v7.widget.RecyclerView f085d7c0f8 = (android.support.v7.widget.RecyclerView) findViewById(ay, vw, open.cn.awg.pro.R.id.v7lb1);
        f085d7c0f8.addOnScrollListener($_on_addOnScrollListener_f085d7c0f8);

        android.widget.LinearLayout e1433e26d6 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj1);
        e1433e26d6.setOnTouchListener($_on_setOnTouchListener_e1433e26d6);

        android.widget.TextView ff56a8e7f6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb1);
        ff56a8e7f6.setOnClickListener($_on_setOnClickListener_ff56a8e7f6);
        ff56a8e7f6.setOnLongClickListener($_on_setOnLongClickListener_ff56a8e7f6);

        android.widget.LinearLayout i469306663f = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj3s);

        android.widget.EditText i32ee139ba1 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjk1);
        i32ee139ba1.setOnTouchListener($_on_setOnTouchListener_i32ee139ba1);

        android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);
        i8217d42a17.setOnClickListener($_on_setOnClickListener_i8217d42a17);

        android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
        i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

        android.widget.LinearLayout i9068f5d66e = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj4);
        i9068f5d66e.setOnTouchListener($_on_setOnTouchListener_i9068f5d66e);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        android.widget.ImageView i11aeec1890 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx4);
        i11aeec1890.setOnClickListener($_on_setOnClickListener_i11aeec1890);

        android.widget.LinearLayout i84f04cd47e = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj7);
        i84f04cd47e.setOnTouchListener($_on_setOnTouchListener_i84f04cd47e);

        android.widget.ImageView i4a4248a0f2 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx5);
        i4a4248a0f2.setOnClickListener($_on_setOnClickListener_i4a4248a0f2);

        android.widget.ImageView i6f2c7751cf = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx6);
        i6f2c7751cf.setOnClickListener($_on_setOnClickListener_i6f2c7751cf);

        android.widget.LinearLayout i3f6c9f386b = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj8);
        i3f6c9f386b.setOnTouchListener($_on_setOnTouchListener_i3f6c9f386b);

        android.widget.LinearLayout i6c4a6b953f = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj6);
        i6c4a6b953f.setOnClickListener($_on_setOnClickListener_i6c4a6b953f);

        __layoutIsLoaded(ay, vw);
    }
}
