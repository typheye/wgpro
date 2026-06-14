/**
 ******************************************************************************
 * @file    DeepSeekActivity.java
 * @author  Typheye
 * @brief   DeepSeek plugin screen.
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

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.inputmethod.InputMethodManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.ui.list.RuntimeMessageItemActivity;
import open.cn.awg.pro.ui.widget.XLinearLayoutManager;

public class DeepSeekActivity extends BaseAwgActivity {

    public static i.runlibrary.app.v.v7lb$UserAdapter spqc;
    public static i.runlibrary.app.v.v7lb lbc;
    public static LinearLayoutManager mLayoutManager;
    public static Context context;
    public final DeepSeekActivity lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public String namer = "User";
    public boolean isOnce = false;
    public String ml = AppPaths.appPath("data/deepseek/chat");
    private final View.OnClickListener xxbj6ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj6Click(vw);
        }

    };
    private final View.OnTouchListener xxbj8TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj8Touch(vw, me);
        }

    };
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
    private final View.OnTouchListener xxbj7TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj7Touch(vw, me);
        }

    };
    private final View.OnClickListener tx4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx4Click(vw);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnTouchListener xxbj4TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj4Touch(vw, me);
        }

    };
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };
    private final View.OnClickListener tx3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx3Click(vw);
        }

    };
    private final View.OnTouchListener bjk1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjk1Touch(vw, me);
        }

    };
    private final View.OnLongClickListener wb1LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onWb1LongClick(vw);
        }

    };
    private final View.OnClickListener wb1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb1Click(vw);
        }

    };
    private final View.OnTouchListener xxbj1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj1Touch(vw, me);
        }

    };
    private final RecyclerView.OnScrollListener v7lb1ScrollListener = new RecyclerView.OnScrollListener() {

        public void onScrollStateChanged(RecyclerView vw, int ns) {
            onV7lb1ScrollStateChanged(vw, ns);
        }

        public void onScrolled(RecyclerView vw, int dx, int dy) {
            onV7lb1Scrolled(vw, dx, dy);
        }

    };
    private final View.OnLongClickListener wb3LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onWb3LongClick(vw);
        }

    };
    private final View.OnClickListener wb3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb3Click(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.deep_seek);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        if (zf.dy(wj.dqwb(AppPaths.appPath("settings/f8/set4.inf")), "true")) {

        }

        i.runlibrary.app.xt$pm pm = (i.runlibrary.app.xt$pm) xt.pm();
        xt.sbxm(false);

        if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4s.inf")), "false")) {

            if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4.inf")), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb(AppPaths.appPath("settings/f17/set1.inf")));
        st.xdbj(R.id.deep_seek_root).shxtck(false);
        applyFullscreenWindow();

        if (zf.dy(a, "1")) {

            st.xxbj(R.id.xxbj1).nbj(u, 0, u, 0);
            st.xxbj(R.id.xxbj4).nbj(u, 0, u, 0);

            i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "2")) {

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (wj.cz(a001) || zf.dy(wj.dqwb(a001), "true")) {

                i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
                wtab.nbj(0, "3dp", 0, "3dp");
                wtab.dqfs("center");

            } else {

                i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
                wtab.nbj(0, "9dp", 0, "9dp");
                wtab.dqfs("center");

            }
            st.xxbj(R.id.xxbj1).nbj(0, 0, 0, 0);
            st.xxbj(R.id.xxbj4).nbj(0, 0, 0, 0);

        } else if (zf.dy(a, "3")) {

            st.xxbj(R.id.xxbj1).nbj(u, 0, u, 0);
            st.xxbj(R.id.xxbj4).nbj(u, 0, u, 0);

            i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "4")) {

            int uih = sj.zh().pxzdp(pm.ztl);
            uih = uih + 9;

            i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
            wtab.nbj(0, uih + "dp", 0, "9dp");
            wtab.dqfs("center");
            st.xxbj(R.id.xxbj1).nbj(0, 0, 0, 0);
            st.xxbj(R.id.xxbj4).nbj(0, 0, 0, 0);

        }

    }

    public void tobottom() {
        gj.jmxc(new Runnable() {

            public void run() {

                lbc.st.scrollToPosition(spqc.zs() - 1);

            }

        });

    }

    public void totop() {
        gj.jmxc(new Runnable() {

            public void run() {

                lbc.st.scrollToPosition(0);

            }

        });

    }

    public void del() {
        gj.xc(new Thread() {

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

            gj.jmxc(new Runnable() {

                public void run() {

                    lbc.v.requestFocus();
                    st.wb(R.id.title_bar).zf("< DeepSeek");
                    st.bjk(R.id.bjk1).zf("");
                    st.xxbj(R.id.xxbj1).kjd(0);
                    st.xxbj(R.id.xxbj3s).kjd(8);
                    st.xxbj(R.id.xxbj4).kjd(8);
                    st.xxbj(R.id.xxbj6).kjd(8);
                    st.kp(R.id.kp7).kjd(0);

                }

            });

        } else if (i == 1) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf("DeepSeek");
                    st.bjk(R.id.bjk1).zf("");
                    st.xxbj(R.id.xxbj1).kjd(8);
                    st.xxbj(R.id.xxbj3s).kjd(0);
                    st.xxbj(R.id.xxbj4).kjd(8);
                    st.xxbj(R.id.xxbj6).kjd(8);
                    st.kp(R.id.kp7).kjd(8);

                }

            });

        } else if (i == 2) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf("发送消息");
                    st.bjk(R.id.bjk1).zf("");
                    st.xxbj(R.id.xxbj1).kjd(8);
                    st.xxbj(R.id.xxbj3s).kjd(8);
                    st.xxbj(R.id.xxbj4).kjd(0);
                    st.xxbj(R.id.xxbj6).kjd(8);
                    st.kp(R.id.kp7).kjd(8);

                }

            });

        } else if (i == 3) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf("DeepSeek");
                    st.bjk(R.id.bjk1).zf("");
                    st.xxbj(R.id.xxbj1).kjd(8);
                    st.xxbj(R.id.xxbj3s).kjd(8);
                    st.xxbj(R.id.xxbj4).kjd(8);
                    st.xxbj(R.id.xxbj6).kjd(0);
                    st.kp(R.id.kp7).kjd(8);

                }

            });

        }

    }

    public void csh() {
        lbc = st.v7lb(R.id.v7lb1);

        context = AwgProApplication.getContext();
        mLayoutManager = new XLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        lbc.st.setLayoutManager(mLayoutManager);
        spqc = (i.runlibrary.app.v.v7lb$UserAdapter) lbc.v7lbspq(RuntimeMessageItemActivity.class, R.layout.runtime_message_item, new i.runlibrary.app.v.v7lb$OnUserAdapterView() {

            public void getView(i.runlibrary.app.v.v7lb$UserAdapter ua, int pn, View vw) {

                i.runlibrary.app.sj$lb lbsj = (i.runlibrary.app.sj$lb) sj.lb(vw);

                final int f = sj.zh(lbsj.lbsj(pn, -1)).zint();

                if (f == -1) {

                    String color0 = zf.zf(R.color.colorTextTrue2);

                    String color1 = zf.zf(R.color.colorBlack);

                    String color2 = zf.zf(R.color.colorTextU1);
                    st.wb(vw, R.id.wb1).zfys(color1);
                    st.wb(vw, R.id.wb3).zfys(color2);
                    st.kp(vw, R.id.kp1).kpbjys(color0);
                    st.xxbj(vw, R.id.xxbj1).dqfs("right");
                    st.xxbj(vw, R.id.xxbj1).kjd(0);
                    st.xdbj(vw, R.id.runtime_message_item_root).xydj(true);
                    st.xdbj(vw, R.id.runtime_message_item_root).xyca(true);
                    st.xxbj(vw, R.id.xxbj3).kjd(8);

                } else if (f == 0) {

                    String color0 = "#00000000";

                    String color1 = "#00000000";

                    String color2 = "#00000000";
                    st.wb(vw, R.id.wb1).zfys(color1);
                    st.wb(vw, R.id.wb3).zfys(color2);
                    st.kp(vw, R.id.kp1).kpbjys(color0);
                    st.xxbj(vw, R.id.xxbj1).dqfs("left");
                    st.xxbj(vw, R.id.xxbj1).kjd(0);
                    st.xdbj(vw, R.id.runtime_message_item_root).xydj(false);
                    st.xdbj(vw, R.id.runtime_message_item_root).xyca(false);
                    st.xxbj(vw, R.id.xxbj3).kjd(0);

                } else if (f == 1) {

                    String color0 = zf.zf(R.color.colorWBK);

                    String color1 = zf.zf(R.color.colorTextU1);

                    String color2 = zf.zf(R.color.colorTextU1);
                    st.wb(vw, R.id.wb1).zfys(color1);
                    st.wb(vw, R.id.wb3).zfys(color2);
                    st.kp(vw, R.id.kp1).kpbjys(color0);
                    st.xxbj(vw, R.id.xxbj1).dqfs("left");
                    st.xxbj(vw, R.id.xxbj1).kjd(0);
                    st.xdbj(vw, R.id.runtime_message_item_root).xydj(true);
                    st.xdbj(vw, R.id.runtime_message_item_root).xyca(true);
                    st.xxbj(vw, R.id.xxbj3).kjd(8);

                } else {

                    String color0 = zf.zf(R.color.colorWBK);

                    String color1 = zf.zf(R.color.colorTextU1);

                    String color2 = zf.zf(R.color.colorTextU1);
                    st.wb(vw, R.id.wb1).zfys(color1);
                    st.wb(vw, R.id.wb3).zfys(color2);
                    st.kp(vw, R.id.kp1).kpbjys(color0);
                    st.xxbj(vw, R.id.xxbj1).dqfs("left");
                    st.xxbj(vw, R.id.xxbj1).kjd(0);
                    st.xdbj(vw, R.id.runtime_message_item_root).xydj(true);
                    st.xdbj(vw, R.id.runtime_message_item_root).xyca(true);
                    st.xxbj(vw, R.id.xxbj3).kjd(8);

                }

            }

        });

    }

    public void update() {

        if (wj.cz(ml) && !zf.dy(wj.dqwb(ml), "")) {

            gj.xc(new Thread() {

                public void run() {

                    if (!isOnce) {

                        gj.zt(1000);

                    }

                    int[] kj = new int[]{R.id.wb1, R.id.wb2, R.id.wb3, -1};

                    Object[] jk = new Object[]{"", "", "", 0};

                    int size = 0;

                    ArrayList arr = new ArrayList();

                    String hcw = wj.dqwb(ml);

                    String[] hcw2 = zf.fg(hcw, "\n", true);

                    for (String hcw3 : hcw2) {

                        hcw3 = zf.qctwkg(hcw3);

                        if (hcw3.length() != 0) {

                            try {

                                String jname = zf.qc(hcw3, 0, 1);

                                if (zf.dy(jname, "Q")) {

                                    jname = "User";

                                } else {

                                    jname = "ChatGPT";

                                }

                                String jdata = zf.qc(hcw3, 22);
                                jdata = zf.th(jdata, "<br>", "\n", true);

                                String date = zf.qc(hcw3, 2, 21);
                                size++;

                                if (zf.dy(jname, namer)) {

                                    jk = new Object[]{jdata, jname, date, -1};

                                } else {

                                    jk = new Object[]{jdata, jname, date, 1};

                                }

                                HashMap hs = new HashMap();

                                for (int ii = 0; ii < kj.length; ii++) {

                                    hs.put(kj[ii], jk[ii]);

                                }
                                arr.add(hs);

                            } catch (Throwable __$_e__) {

                            }

                        }

                    }

                    HashMap hs = new HashMap();
                    hs.put(kj[0], "");
                    hs.put(kj[1], "");
                    hs.put(kj[2], "");
                    hs.put(kj[3], 0);
                    arr.add(hs);
                    arr.add(hs);
                    spqc.list_S = arr;
                    gj.jmxc(new Runnable() {

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

            gj.xc(new Thread() {

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

    public void add(Object name, Object msg) {
        msg = zf.qctwkg(msg);
        msg = zf.th(msg, "\n", "<br>", true);

        String d = name + " " + xt.sj(0) + " " + msg;

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
        gj.xc(new Thread() {

            public void run() {

                String a = st.bjk(R.id.bjk1).zf();
                a = zf.qctwkg(a);

                if (a.length() == 0) {

                    e1.tsk("提示", "消息不能为空");

                } else {

                    show(1);
                    add("Q", a);
                    gj.zt(500);
                    show(0);

                    String bak = wl.hq("https://api.sayqz.com/chatgpt/?msg=" + a + "&sys=", "utf-8");

                    if (!zf.dy(bak, null)) {

                        try {

                            i.runlibrary.app.zf$json jo = (i.runlibrary.app.zf$json) zf.json(bak);

                            JSONObject json = jo.json;

                            JSONArray list = jo.dxlb(json, "choices");

                            int size = jo.cd(list);

                            while (size > 0) {

                                size = size - 1;

                                JSONObject dx = jo.dx(list, size);

                                Object msg = jo.hq(dx, "message");
                                bak = e1.readJson(String.valueOf(msg), "content");

                            }
                            add("A", bak);

                        } catch (Throwable __$_e__) {

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

            gj.jmxc(new Runnable() {

                public void run() {

                    InputMethodManager imm = (InputMethodManager) lei.getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(lei.getWindow().getDecorView().getWindowToken(), 0);

                }

            });

        } catch (Throwable __$_e__) {

        }

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb(AppPaths.appPath("settings/f17/set1.inf")));
        st.xdbj(R.id.deep_seek_root).shxtck(false);
        applyFullscreenWindow();

    }

    public void onStart() {
        super.onStart();

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb(AppPaths.appPath("settings/f17/set1.inf")));
        st.xdbj(R.id.deep_seek_root).shxtck(false);
        applyFullscreenWindow();

        i.runlibrary.app.v.v7lb v7lb1 = st.v7lb(R.id.v7lb1);
        v7lb1.v.requestFocus();

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

    }

    public void onRestart() {
        super.onRestart();

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb(AppPaths.appPath("settings/f17/set1.inf")));
        st.xdbj(R.id.deep_seek_root).shxtck(false);
        applyFullscreenWindow();

    }

    public void onResume() {
        super.onResume();

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb(AppPaths.appPath("settings/f17/set1.inf")));
        st.xdbj(R.id.deep_seek_root).shxtck(false);
        applyFullscreenWindow();

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

            if (zf.dy(st.wb(R.id.title_bar).zf(), "< DeepSeek")) {

                gj.gb();

            } else {

            }

        }
        return false;

    }

    private void onTitleBarClick(View vw) {

        if (zf.dy(st.wb(R.id.title_bar).zf(), "< DeepSeek")) {

            gj.gb();

        }

    }

    private void onWb3Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                String z = st.wb(R.id.wb3).zf();
                z = zf.qc(z, "[提示] ");

                if (zf.dy(st.wb(R.id.wb3).zf(), "[提示] 发送成功")) {

                    z = z + "\n(ps:长按提示可隐藏哦)";

                }
                e1.tsk("详细信息", z);

            }

        });

    }

    private boolean onWb3LongClick(View vw) {

        if (zf.dy(st.wb(R.id.wb3).zf(), "[提示] 发送成功")) {

        }
        return true;

    }

    private void onV7lb1ScrollStateChanged(RecyclerView vw, int ns) {

        if (ns == RecyclerView.SCROLL_STATE_IDLE) {

            if (!vw.canScrollVertically(-1)) {

            } else if (!vw.canScrollVertically(1)) {

            } else {

            }

        }

    }

    private void onV7lb1Scrolled(RecyclerView vw, int dx, int dy) {

    }

    private boolean onXxbj1Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onWb1Click(View vw) {
        show(3);

    }

    private boolean onWb1LongClick(View vw) {
        show(2);
        return true;

    }

    private boolean onBjk1Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            gj.jmxc(new Runnable() {

                public void run() {

                    i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(R.id.qtgd1);
                    qtgd1.v.requestFocus();

                }

            });

        }
        return false;

    }

    private void onTx3Click(View vw) {
        shouJp();
        show(0);

    }

    private void onTx2Click(View vw) {
        shouJp();
        post();

    }

    private boolean onXxbj4Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onTx1Click(View vw) {
        totop();
        show(0);

    }

    private void onTx4Click(View vw) {
        show(2);

    }

    private boolean onXxbj7Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onTx5Click(View vw) {
        tobottom();
        show(0);

    }

    private void onTx6Click(View vw) {
        del();
        show(0);

    }

    private boolean onXxbj8Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onXxbj6Click(View vw) {
        show(0);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        TextView i0d2d4d9236 = (TextView) findViewById(ay, vw, R.id.wb3);
        i0d2d4d9236.setOnClickListener(wb3ClickListener);
        i0d2d4d9236.setOnLongClickListener(wb3LongClickListener);

        RecyclerView f085d7c0f8 = (RecyclerView) findViewById(ay, vw, R.id.v7lb1);
        f085d7c0f8.addOnScrollListener(v7lb1ScrollListener);

        LinearLayout e1433e26d6 = (LinearLayout) findViewById(ay, vw, R.id.xxbj1);
        e1433e26d6.setOnTouchListener(xxbj1TouchListener);

        TextView ff56a8e7f6 = (TextView) findViewById(ay, vw, R.id.wb1);
        ff56a8e7f6.setOnClickListener(wb1ClickListener);
        ff56a8e7f6.setOnLongClickListener(wb1LongClickListener);

        LinearLayout i469306663f = (LinearLayout) findViewById(ay, vw, R.id.xxbj3s);

        EditText i32ee139ba1 = (EditText) findViewById(ay, vw, R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        LinearLayout i9068f5d66e = (LinearLayout) findViewById(ay, vw, R.id.xxbj4);
        i9068f5d66e.setOnTouchListener(xxbj4TouchListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i11aeec1890 = (ImageView) findViewById(ay, vw, R.id.tx4);
        i11aeec1890.setOnClickListener(tx4ClickListener);

        LinearLayout i84f04cd47e = (LinearLayout) findViewById(ay, vw, R.id.xxbj7);
        i84f04cd47e.setOnTouchListener(xxbj7TouchListener);

        ImageView i4a4248a0f2 = (ImageView) findViewById(ay, vw, R.id.tx5);
        i4a4248a0f2.setOnClickListener(tx5ClickListener);

        ImageView i6f2c7751cf = (ImageView) findViewById(ay, vw, R.id.tx6);
        i6f2c7751cf.setOnClickListener(tx6ClickListener);

        LinearLayout i3f6c9f386b = (LinearLayout) findViewById(ay, vw, R.id.xxbj8);
        i3f6c9f386b.setOnTouchListener(xxbj8TouchListener);

        LinearLayout i6c4a6b953f = (LinearLayout) findViewById(ay, vw, R.id.xxbj6);
        i6c4a6b953f.setOnClickListener(xxbj6ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
