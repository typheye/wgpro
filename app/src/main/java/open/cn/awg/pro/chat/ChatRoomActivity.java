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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.core.UriPathResolver;
import open.cn.awg.pro.file.FileManagerActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;

public class ChatRoomActivity extends BaseAwgActivity {

    public static String uid = "";
    public final ChatRoomActivity lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

    public WearChatRuntimeBridge e10 = new WearChatRuntimeBridge(_APPINFO);
    public boolean xhtrue = true;
    public String path = AppPaths.appPath("cache/chat/upload/path");
    public String room_name = "";
    private final View.OnClickListener xxbjcdClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbjcdClick(vw);
        }

    };
    private final View.OnTouchListener xxbj6TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj6Touch(vw, me);
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
    private final View.OnTouchListener xxbj7TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj7Touch(vw, me);
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
    private final View.OnClickListener anDisuploadClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAnDisuploadClick(vw);
        }

    };
    private final View.OnClickListener anUploadClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAnUploadClick(vw);
        }

    };
    private final CompoundButton.OnCheckedChangeListener dxChatUploadCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onDxChatUploadCheckedChanged(vw, ic);
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
    private final View.OnTouchListener i2xxbj1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onI2xxbj1Touch(vw, me);
        }

    };
    private final RecyclerView.OnScrollListener ltsv7lb1ScrollListener = new RecyclerView.OnScrollListener() {

        public void onScrollStateChanged(RecyclerView vw, int ns) {
            onLtsv7lb1ScrollStateChanged(vw, ns);
        }

        public void onScrolled(RecyclerView vw, int dx, int dy) {
            onLtsv7lb1Scrolled(vw, dx, dy);
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
        setContentView(R.layout.chat_room);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        String set = AppPaths.appPath("settings/set3.inf");

        if (zf.dy(wj.dqwb(set), "true")) {

            String te = wj.dqwb(AppPaths.appPath("data/assets/a13"));

            String[] name = new String[]{"title", "text"};

            String[] value = new String[]{"新手教程", te};
            gj.tz(TextQrDisplayActivity.class, name, value);

        }

        if (e1.isJh()) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.dx(R.id.dx_chat_upload).kjd(0);

                }

            });

        }
        wj.xrwb(AppPaths.appPath("data/ltlb/showing"), "true");
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
        st.xdbj(R.id.chat_room_root).shxtck(false);
        applyFullscreenWindow();

        if (zf.dy(a, "1")) {

            st.xxbj(R.id.i2xxbj1).nbj(u, 0, u, 0);
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
            st.xxbj(R.id.i2xxbj1).nbj(0, 0, 0, 0);
            st.xxbj(R.id.xxbj4).nbj(0, 0, 0, 0);

        } else if (zf.dy(a, "3")) {

            st.xxbj(R.id.i2xxbj1).nbj(u, 0, u, 0);
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
            st.xxbj(R.id.i2xxbj1).nbj(0, 0, 0, 0);
            st.xxbj(R.id.xxbj4).nbj(0, 0, 0, 0);

        }

        i.runlibrary.app.v.xlcd xlcd1 = st.xlcd(R.id.ltsup_xlcd1);

        String[] sz = new String[]{"图像文件", "音频文件", "视频文件", "其他文件"};
        xlcd1.csh(sz);

        i.runlibrary.app.v.xlcd xlcd2 = st.xlcd(R.id.ltsup_xlcd2);
        sz = new String[]{"应用内置选择器", "系统默认选择器"};
        xlcd2.csh(sz);

    }

    public void jz() {
        uid = sj.hqtz("uid");

        String cachehd = AppPaths.appPath("data/ltlb/lbx_title");
        room_name = e10.getTabName(wj.dqwb(cachehd));
        gj.jmxc(new Runnable() {

            public void run() {

                st.wb(R.id.title_bar).zf(room_name);

            }

        });
        e10.lts_csh();
        e10.ltsview(4);
        ltsgxxh();

    }

    public void ltsgxxh() {

        final String uid2 = wj.dqwb(AppPaths.appPath("data/ltlb/lbx_id"));

        final String sjhc = AppPaths.appPath("data/ltlb/chat/") + uid2;

        String set2 = AppPaths.appPath("settings/f18/set1.inf");

        i.runlibrary.app.sj$zh sjl = (i.runlibrary.app.sj$zh) sj.zh(wj.dqwb(set2));

        final long tim = sjl.zlong(3000);
        gj.xc(new Thread() {

            public void run() {

                final String uid = wj.dqwb(AppPaths.appPath("data/ltlb/lbx_id"));

                final String sjhc = AppPaths.appPath("data/ltlb/chat/") + uid;

                if (wj.cz(sjhc) && !zf.dy(wj.dqwb(sjhc), "")) {

                    e10.ltsgx1(false);

                } else {

                    e10.ltsgx3();

                }

                while (xhtrue) {

                    if (wj.cz(AppPaths.appPath("data/ltlb/showing"))) {

                        gj.zt(tim);

                        if (!wj.cz(AppPaths.appPath("data/ltlb/disTouch"))) {

                            e10.ltsgx2();

                        }

                        if (wj.cz(sjhc) && !zf.dy(zf.qctwkg(wj.dqwb(sjhc)), "")) {

                            try {

                                String back = wj.dqwb(sjhc);

                                i.runlibrary.app.zf$json jo = (i.runlibrary.app.zf$json) zf.json(back);

                                JSONObject json = jo.json;

                                JSONArray list = jo.dxlb(json, "data");

                                int i = jo.cd(list);

                                int i2 = WearChatRuntimeBridge.spq3.zs() - 2;

                                if (i != i2 && i != 0 && i2 != 0) {

                                    e10.ltsgx1(true);

                                }

                            } catch (Throwable __$_e__) {

                            }

                        }

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
        wj.xrwb(AppPaths.appPath("data/ltlb/showing"), "true");

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb(AppPaths.appPath("settings/f17/set1.inf")));
        st.xdbj(R.id.chat_room_root).shxtck(false);
        applyFullscreenWindow();

        final i.runlibrary.app.v.an an = st.an(R.id.an_upload);

        final i.runlibrary.app.v.an an2 = st.an(R.id.an_disupload);

        final i.runlibrary.app.v.dx dx_chat_upload = st.dx(R.id.dx_chat_upload);

        final i.runlibrary.app.v.xlcd ltsup_xlcd1 = st.xlcd(R.id.ltsup_xlcd1);

        final i.runlibrary.app.v.xlcd ltsup_xlcd2 = st.xlcd(R.id.ltsup_xlcd2);

        if (wj.cz(path) && !zf.dy(wj.dqwb(path), "") && !zf.dy(wj.dqwb(path), "null") && zf.dy(an.tszf(), "选择文件")) {

            gj.jmxc(new Runnable() {

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
        wj.sc(AppPaths.appPath("data/ltlb/showing"));

    }

    public void onStart() {
        super.onStart();
        wj.xrwb(AppPaths.appPath("data/ltlb/showing"), "true");

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb(AppPaths.appPath("settings/f17/set1.inf")));
        st.xdbj(R.id.chat_room_root).shxtck(false);
        applyFullscreenWindow();

        i.runlibrary.app.v.v7lb ltsv7lb1 = st.v7lb(R.id.ltsv7lb1);
        ltsv7lb1.v.requestFocus();

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
        wj.xrwb(AppPaths.appPath("data/ltlb/showing"), "true");

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb(AppPaths.appPath("settings/f17/set1.inf")));
        st.xdbj(R.id.chat_room_root).shxtck(false);
        applyFullscreenWindow();

    }

    public void onResume() {
        super.onResume();
        wj.xrwb(AppPaths.appPath("data/ltlb/showing"), "true");

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb(AppPaths.appPath("settings/f17/set1.inf")));
        st.xdbj(R.id.chat_room_root).shxtck(false);
        applyFullscreenWindow();

    }

    public void onPause() {
        super.onPause();
        wj.sc(AppPaths.appPath("data/ltlb/showing"));

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
                    wj.xrwb(path, realPath);

                    final i.runlibrary.app.v.an an = st.an(R.id.an_upload);

                    final i.runlibrary.app.v.an an2 = st.an(R.id.an_disupload);

                    final i.runlibrary.app.v.dx dx_chat_upload = st.dx(R.id.dx_chat_upload);

                    final i.runlibrary.app.v.xlcd ltsup_xlcd1 = st.xlcd(R.id.ltsup_xlcd1);

                    final i.runlibrary.app.v.xlcd ltsup_xlcd2 = st.xlcd(R.id.ltsup_xlcd2);

                    if (zf.dy(an.tszf(), "选择文件")) {

                        gj.jmxc(new Runnable() {

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
        AwgProApplication.getInstance().removeActivity(lei);
        xhtrue = false;
        wj.sc(AppPaths.appPath("data/ltlb/showing"));

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

            if (st.xxbj(R.id.i2xxbj1).kjd() == 0) {

                gj.gb();

            } else {

            }

        }
        return false;

    }

    private void onTitleBarClick(View vw) {

        String cachehd = AppPaths.appPath("data/ltlb/lbx_title");

        final String name = wj.dqwb(cachehd);

        if (zf.dy(st.wb(R.id.title_bar).zf(), "[有新消息]")) {

            gj.jmxc(new Runnable() {

                public void run() {

                    if (st.xxbj(R.id.xxbj1).kjd() == 0) {

                        st.wb(R.id.title_bar).zf("< ");

                    }
                    st.v7lb(R.id.ltsv7lb1).xzwz("top");

                }

            });

        } else if (zf.dy(st.wb(R.id.title_bar).zf(), room_name)) {

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

            e10.ltjmshowgb();

        }
        return true;

    }

    private void onLtsv7lb1ScrollStateChanged(RecyclerView vw, int ns) {

        if (ns == RecyclerView.SCROLL_STATE_IDLE) {

            if (!vw.canScrollVertically(-1)) {

                wj.xrwb(AppPaths.appPath("data/ltlb/hdva"), "-1");

            } else if (!vw.canScrollVertically(1)) {

                String cachehd = AppPaths.appPath("data/ltlb/lbx_title");

                final String name = wj.dqwb(cachehd);

                if (st.xxbj(R.id.xxbj1).kjd() == 0) {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            st.wb(R.id.title_bar).zf(room_name);

                        }

                    });

                }
                wj.xrwb(AppPaths.appPath("data/ltlb/hdva"), "0");

            } else {

                wj.xrwb(AppPaths.appPath("data/ltlb/hdva"), "1");

            }

        }

    }

    private void onLtsv7lb1Scrolled(RecyclerView vw, int dx, int dy) {

    }

    private boolean onI2xxbj1Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onWb1Click(View vw) {

        if (st.xxbj(R.id.xxbjcd).kjd() == 0) {

            st.wb(R.id.title_bar).zf("< 房间详情");
            st.xxbj(R.id.i2xxbj1).kjd(0);
            st.xxbj(R.id.xxbjcd).kjd(8);
            st.kp(R.id.kp7).kjd(0);

        } else {

            st.wb(R.id.title_bar).zf("菜单");
            st.xxbj(R.id.i2xxbj1).kjd(8);
            st.xxbj(R.id.xxbjcd).kjd(0);
            st.kp(R.id.kp7).kjd(8);

        }

    }

    private boolean onWb1LongClick(View vw) {
        st.kp(R.id.kp7).kjd(8);
        e10.ltspost(1);
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

    private void onDxChatUploadCheckedChanged(CompoundButton vw, boolean ic) {

        if (ic) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.xxbj(R.id.xxbjyc3).kjd(0);

                }

            });

        } else {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.xxbj(R.id.xxbjyc3).kjd(8);

                }

            });

        }

    }

    private void onAnUploadClick(View vw) {

        i.runlibrary.app.v.xlcd xlcd1 = st.xlcd(R.id.ltsup_xlcd1);

        i.runlibrary.app.v.xlcd xlcd2 = st.xlcd(R.id.ltsup_xlcd2);

        final i.runlibrary.app.v.an an = st.an(R.id.an_upload);

        final String choose = AppPaths.appPath("cache/file/choose");

        if (zf.dy(an.tszf(), "选择文件")) {

            wj.sc(path);

            int sdk = xt.sbxx().sdk;

            String ftitle = AppPaths.appPath("cache/file/title");
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

                    i.runlibrary.app.xt$qxgl a = (i.runlibrary.app.xt$qxgl) xt.qxgl();

                    if (a.qx("android.permission.WRITE_EXTERNAL_STORAGE")) {

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

                    e1.upload_error(e, "i2.*");

                }

            } else if (xlcd2.dqzsxmxh() == 1) {

                try {

                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    if (xlcd1.dqzsxmxh() == 0)
                        intent.setType("image/*");
                    else if (xlcd1.dqzsxmxh() == 1)
                        intent.setType("audio/*");
                    else if (xlcd1.dqzsxmxh() == 2)
                        intent.setType("video/*");
                    else if (xlcd1.dqzsxmxh() == 3)
                        intent.setType("*/*");
                    lei.startActivityForResult(intent, 1);

                } catch (Throwable e) {

                    e1.upload_error(e, "i2.*");

                }

            }

        } else if (zf.dy(an.tszf(), "已选择•开始上传")) {

            e10.upload(wj.dqwb(path), xlcd1.dqzsxmxh());

        } else if (zf.dy(an.tszf(), "上传成功")) {

            e1.tsk("提示", "已成功上传文件");

        }

    }

    private void onAnDisuploadClick(View vw) {
        wj.sc(path);

        final i.runlibrary.app.v.an an = st.an(R.id.an_upload);

        final i.runlibrary.app.v.an an2 = st.an(R.id.an_disupload);

        final i.runlibrary.app.v.dx dx_chat_upload = st.dx(R.id.dx_chat_upload);

        final i.runlibrary.app.v.xlcd ltsup_xlcd1 = st.xlcd(R.id.ltsup_xlcd1);

        final i.runlibrary.app.v.xlcd ltsup_xlcd2 = st.xlcd(R.id.ltsup_xlcd2);
        gj.jmxc(new Runnable() {

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

    private void onTx3Click(View vw) {

        final i.runlibrary.app.v.an an = st.an(R.id.an_upload);

        if (zf.dy(an.tszf(), "上传中")) {

            e1.tsk("提示", "请耐心等待文件上传完毕");

        } else {

            shouJp();
            e10.ltspost(0);
            st.kp(R.id.kp7).kjd(0);

        }

    }

    private void onTx2Click(View vw) {
        shouJp();
        e10.post();

    }

    private boolean onXxbj4Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onTx5Click(View vw) {

        if (st.xxbj(R.id.xxbjcd).kjd() == 0) {

            st.wb(R.id.title_bar).zf(room_name);
            st.xxbj(R.id.i2xxbj1).kjd(0);
            st.xxbj(R.id.xxbjcd).kjd(8);
            st.kp(R.id.kp7).kjd(0);
            e10.ltstotop();

        } else {

            st.wb(R.id.title_bar).zf("菜单");
            st.xxbj(R.id.i2xxbj1).kjd(8);
            st.xxbj(R.id.xxbjcd).kjd(0);
            st.kp(R.id.kp7).kjd(8);

        }

    }

    private void onTx6Click(View vw) {

        if (st.xxbj(R.id.xxbjcd).kjd() == 0) {

            st.wb(R.id.title_bar).zf(room_name);
            st.xxbj(R.id.i2xxbj1).kjd(0);
            st.xxbj(R.id.xxbjcd).kjd(8);

        } else {

            st.wb(R.id.title_bar).zf("菜单");
            st.xxbj(R.id.i2xxbj1).kjd(8);
            st.xxbj(R.id.xxbjcd).kjd(0);
            st.kp(R.id.kp7).kjd(8);

        }
        e10.ltspost(1);

    }

    private boolean onXxbj7Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onTx1Click(View vw) {

        if (st.xxbj(R.id.xxbjcd).kjd() == 0) {

            st.wb(R.id.title_bar).zf(room_name);
            st.xxbj(R.id.i2xxbj1).kjd(0);
            st.xxbj(R.id.xxbjcd).kjd(8);
            st.kp(R.id.kp7).kjd(0);
            e10.ltstobottom();

        } else {

            st.wb(R.id.title_bar).zf("菜单");
            st.xxbj(R.id.i2xxbj1).kjd(8);
            st.xxbj(R.id.xxbjcd).kjd(0);
            st.kp(R.id.kp7).kjd(8);

        }

    }

    private void onTx4Click(View vw) {

        if (st.xxbj(R.id.xxbjcd).kjd() == 0) {

            st.wb(R.id.title_bar).zf(room_name);
            st.xxbj(R.id.i2xxbj1).kjd(0);
            st.xxbj(R.id.xxbjcd).kjd(8);
            st.kp(R.id.kp7).kjd(0);

        } else {

            st.wb(R.id.title_bar).zf("菜单");
            st.xxbj(R.id.i2xxbj1).kjd(8);
            st.xxbj(R.id.xxbjcd).kjd(0);
            st.kp(R.id.kp7).kjd(8);

        }
        gj.gb();

    }

    private boolean onXxbj6Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onXxbjcdClick(View vw) {

        if (st.xxbj(R.id.xxbjcd).kjd() == 0) {

            st.wb(R.id.title_bar).zf(room_name);
            st.xxbj(R.id.i2xxbj1).kjd(0);
            st.xxbj(R.id.xxbjcd).kjd(8);
            st.kp(R.id.kp7).kjd(0);

        } else {

            st.wb(R.id.title_bar).zf("菜单");
            st.xxbj(R.id.i2xxbj1).kjd(8);
            st.xxbj(R.id.xxbjcd).kjd(0);
            st.kp(R.id.kp7).kjd(8);

        }

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

        RecyclerView c2f5ccfa3a = (RecyclerView) findViewById(ay, vw, R.id.ltsv7lb1);
        c2f5ccfa3a.addOnScrollListener(ltsv7lb1ScrollListener);

        LinearLayout e76307d04a = (LinearLayout) findViewById(ay, vw, R.id.i2xxbj1);
        e76307d04a.setOnTouchListener(i2xxbj1TouchListener);

        TextView ff56a8e7f6 = (TextView) findViewById(ay, vw, R.id.wb1);
        ff56a8e7f6.setOnClickListener(wb1ClickListener);
        ff56a8e7f6.setOnLongClickListener(wb1LongClickListener);

        TextView e943ebd71e = (TextView) findViewById(ay, vw, R.id.wb4);

        LinearLayout i5f37bb7e71 = (LinearLayout) findViewById(ay, vw, R.id.ltssxxxbj3);

        EditText i32ee139ba1 = (EditText) findViewById(ay, vw, R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        CheckBox i4b3a71b80a = (CheckBox) findViewById(ay, vw, R.id.dx_chat_upload);
        i4b3a71b80a.setOnCheckedChangeListener(dxChatUploadCheckedChangeListener);

        Button fb3bffd427 = (Button) findViewById(ay, vw, R.id.an_upload);
        fb3bffd427.setOnClickListener(anUploadClickListener);

        Button i3295d3c478 = (Button) findViewById(ay, vw, R.id.an_disupload);
        i3295d3c478.setOnClickListener(anDisuploadClickListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        LinearLayout i9068f5d66e = (LinearLayout) findViewById(ay, vw, R.id.xxbj4);
        i9068f5d66e.setOnTouchListener(xxbj4TouchListener);

        ImageView i4a4248a0f2 = (ImageView) findViewById(ay, vw, R.id.tx5);
        i4a4248a0f2.setOnClickListener(tx5ClickListener);

        ImageView i6f2c7751cf = (ImageView) findViewById(ay, vw, R.id.tx6);
        i6f2c7751cf.setOnClickListener(tx6ClickListener);

        LinearLayout i84f04cd47e = (LinearLayout) findViewById(ay, vw, R.id.xxbj7);
        i84f04cd47e.setOnTouchListener(xxbj7TouchListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i11aeec1890 = (ImageView) findViewById(ay, vw, R.id.tx4);
        i11aeec1890.setOnClickListener(tx4ClickListener);

        LinearLayout i6c4a6b953f = (LinearLayout) findViewById(ay, vw, R.id.xxbj6);
        i6c4a6b953f.setOnTouchListener(xxbj6TouchListener);

        LinearLayout d2a1859abe = (LinearLayout) findViewById(ay, vw, R.id.xxbjcd);
        d2a1859abe.setOnClickListener(xxbjcdClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
