/**
 ******************************************************************************
 * @file    MainActivity.java
 * @author  Typheye
 * @brief   Main launcher activity and home surface.
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
package open.cn.awg.pro.ui.main;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.PushAgent;
import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;
import open.cn.awg.pro.account.AppActivationActivity;
import open.cn.awg.pro.account.ThirdPartyAuthorizationActivity;
import open.cn.awg.pro.account.TypheyeServiceBridge;
import open.cn.awg.pro.announcement.AnnouncementActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.browser.X5BrowserActivity;
import open.cn.awg.pro.chat.WearChatActivity;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.CoreRuntimeBootstrap;
import open.cn.awg.pro.core.DebugStateStore;
import open.cn.awg.pro.data.LocalDatabaseBridge;
import open.cn.awg.pro.file.FileRootPickerActivity;
import open.cn.awg.pro.plugin.ExtensionPluginActivity;
import open.cn.awg.pro.service.AwgCoreService;
import open.cn.awg.pro.service.BootJobService;
import open.cn.awg.pro.store.StoreDetailActivity;
import open.cn.awg.pro.store.StoreListActivity;
import open.cn.awg.pro.store.StoreSearchActivity;
import open.cn.awg.pro.ui.list.LegacyMenuItemActivity;
import open.cn.awg.pro.ui.list.MainMenuItemActivity;
import open.cn.awg.pro.update.UpdatePromptActivity;


public class MainActivity extends iActivity {

    public static android.content.Context context;
    public final MainActivity lei = this, 类 = this;
    public i.runlibrary.app.v.v7lb$UserAdapter spq = null;
    public open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public open.cn.awg.pro.data.LocalDatabaseBridge e11 = new LocalDatabaseBridge(_APPINFO);
    public open.cn.awg.pro.core.CoreRuntimeBootstrap e13 = new CoreRuntimeBootstrap(_APPINFO);
    public open.cn.awg.pro.core.DebugStateStore debug = new DebugStateStore(_APPINFO);
    public int vercode = 0;
    public boolean isNotMainOnce = true;
    public boolean isShow = true;
    public boolean loaded = false;
    public boolean state_homeshow = false;
    public boolean state_once_homeshow = false;
    public boolean isHasReq = false;
    public java.lang.String div_lbl1_url = "";
    public java.lang.String div_ad1_url = "";
    public java.lang.String div_yytj_item1_url = "";
    public java.lang.String div_yytj_item2_url = "";
    public java.lang.String div_yytj_item3_url = "";
    public java.lang.String div_jcsp_item1_url = "";
    public java.lang.String div_jcsp_item2_url = "";
    public java.lang.String div_jcsp_item3_url = "";
    public java.lang.String div_rmyy_item1_url = "";
    public java.lang.String div_rmyy_item2_url = "";
    public java.lang.String div_rmyy_item3_url = "";
    public java.lang.String src_url = "";
    public int supertip_mode = 0;
    public java.lang.String android_id;
    public open.cn.awg.pro.account.TypheyeServiceBridge easy = new TypheyeServiceBridge(_APPINFO);
    private final android.view.View.OnClickListener $_on_setOnClickListener_i6a8b9facdf = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i6a8b9facdf(vw);
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
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_b0f5bad894 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_b0f5bad894(vw, me);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i3167b45174 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i3167b45174(vw, me);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i2318575919 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i2318575919(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i3a177cf5f1 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i3a177cf5f1(vw);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i5b34eede7f = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i5b34eede7f(vw, me);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_a81b730195 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_a81b730195(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i7a998f9ecb = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i7a998f9ecb(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i4ba8980add = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i4ba8980add(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_adf370a235 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_adf370a235(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i6a848b922c = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i6a848b922c(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_a229195608 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_a229195608(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_a229195608 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_a229195608(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i11185eacd9 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i11185eacd9(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i11185eacd9 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i11185eacd9(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i8890b048c8 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i8890b048c8(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i8890b048c8 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i8890b048c8(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i3d78c07838 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i3d78c07838(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_c9f5a3ee67 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_c9f5a3ee67(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_c9f5a3ee67 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_c9f5a3ee67(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i6834408ef9 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i6834408ef9(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i6834408ef9 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i6834408ef9(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_c4cbe9d7ff = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_c4cbe9d7ff(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_c4cbe9d7ff = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_c4cbe9d7ff(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i81652f77b6 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i81652f77b6(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i62f27ff563 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i62f27ff563(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i62f27ff563 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i62f27ff563(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_f73b53def9 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_f73b53def9(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_f73b53def9 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_f73b53def9(vw);
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
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_bab1d5f82e = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_bab1d5f82e(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_bab1d5f82e = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_bab1d5f82e(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i9d626a4248 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i9d626a4248(vw);
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
    private final android.view.View.OnClickListener $_on_setOnClickListener_i7c3e3c6b5d = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i7c3e3c6b5d(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i42afe4289f = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i42afe4289f(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_b8bc22741b = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_b8bc22741b(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_bd584e50cd = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_bd584e50cd(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d26d5d7080(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.main);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.app.AwgProApplication.getInstance().addActivity(lei);

        if (e1.getNowUserId() == 0) {

            final java.lang.String a0 = "/data/user/0/open.cn.awg.pro/settings/a4.inf";

            final java.lang.String a1 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

            final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

            if ((getIntent().getFlags() & android.content.Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
                finish();
                return;
            }

            gj.xc(new java.lang.Thread() {

                public void run() {

                    csh0();
                    gj.zt(500);
                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            e13.downAssets();

                            java.lang.String set0 = "/data/user/0/open.cn.awg.pro/settings/version";
                            vercode = com.demo.e4.getVersionCode(lei);

                            java.lang.String bb = String.valueOf(vercode);

                            if (!zf.dy(wj.dqwb(set0), bb) || !wj.cz(set0)) {

                                set();

                            } else {

                                inj();

                            }

                            if (isShow && wj.cz("/data/user/0/open.cn.awg.pro/settings/a4.inf")) {

                                i.runlibrary.app.gj$dh dh = gj.dh();

                                i.runlibrary.app.gj$Animationx jbtm = dh.jbtm(1, 0);
                                jbtm.cxsc(500);
                                st.xdbj(open.cn.awg.pro.R.id.xdbj2).kjd(8);
                                st.xdbj(open.cn.awg.pro.R.id.xdbj2).dh(jbtm);
                                loaded = true;

                                final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

                                java.lang.String a = wj.dqwb(a2);

                                if (zf.dy(a, "4")) {

                                    if (loaded) {

                                        java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
                                        xt.pm().ztl(clr, 0);

                                    } else {

                                        java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTabBackground);
                                        xt.pm().ztl(clr, 0);

                                    }

                                }

                                java.lang.String set67 = "/data/user/0/open.cn.awg.pro/settings/set7.inf";
                                src_url = wj.dqwb(set67);
                                zyqd();

                                if (!debug.isShowed()) {

                                    e1.goplu("20005", "后台诊断", false);

                                }

                                if (wj.cz("/data/user/0/open.cn.awg.pro/settings/a4.inf")) {

                                    if (e1.islogin()) {

                                        n3_jz();

                                    }

                                    java.lang.String set11 = "/data/user/0/open.cn.awg.pro/settings/f9/set1.inf";

                                    if (zf.dy(wj.dqwb(set11), "true")) {

                                        int a11;

                                        java.lang.String vn;
                                        a11 = com.demo.e4.getVersionCode(lei);
                                        vn = com.demo.e4.getVersionName(lei);
                                        e1.update(a11);

                                        if (e1.islogin()) {

                                        }

                                    }
                                    wj.sc("/data/user/0/open.cn.awg.pro/data/ltlb/showing");

                                    try {

                                        UMConfigure.init(lei, "6687f537940d5a4c4983309f", "awg_um", UMConfigure.DEVICE_TYPE_PHONE, "673a08d091d30693eb8353adf5c1e554");
                                        PushAgent.getInstance(lei).onAppStart();

                                        PushAgent api = PushAgent.getInstance(lei);

                                    } catch (java.lang.Throwable __$_e__) {

                                    }

                                }

                            }
                            e13.run();

                        }

                    });

                }

            });

        } else {

            open.cn.awg.pro.app.AwgProApplication.getInstance().exit();

        }

    }

    public void __layoutIsLoaded(android.app.Activity ay, android.view.View vw) {

        if (e1.getNowUserId() == 0) {

            i.runlibrary.app.xt$pm pm = xt.pm();

            if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4s.inf"), "false")) {

                if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4.inf"), "true")) {

                    getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES);

                } else {

                    getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO);

                }

            }

            final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

            java.lang.String a = "";

            if (wj.cz(a2)) {

                a = wj.dqwb(a2);

            }

            if (zf.dy(a, "4")) {

                st.xdbj(open.cn.awg.pro.R.id.main_v).shxtck(true);

                if (loaded) {

                    java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
                    xt.pm().ztl(clr, 0);

                } else {

                    java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTabBackground);
                    xt.pm().ztl(clr, 0);

                }

            } else {

                st.xdbj(open.cn.awg.pro.R.id.main_v).shxtck(false);

                Window window = lei.getWindow();
                BangScreenTools.getBangScreenTools().fullscreen(window, lei);
                BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

            }

            int uu = 0;

            if (wj.cz("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf")) {

                uu = Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));

            }

            final int u = uu;

            if (zf.dy(a, "1")) {

                st.xdbj(open.cn.awg.pro.R.id.xdbj4).wbj(u, 10, u, 10);

                i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
                wtab.nbj(0, "15dp", 0, "3dp");
                wtab.dqfs("center");

                i.runlibrary.app.v.wb wtab2 = st.wb(open.cn.awg.pro.R.id.Title2);
                wtab2.nbj(0, "15dp", 0, "3dp");
                wtab2.dqfs("center");

                i.runlibrary.app.v.v7lb lb = st.v7lb(open.cn.awg.pro.R.id.v7lb1);
                spq = lb.v7lbspq(MainMenuItemActivity.class, open.cn.awg.pro.R.layout.c3, new i.runlibrary.app.v.v7lb$OnUserAdapterView() {

                    public void getView(i.runlibrary.app.v.v7lb$UserAdapter ua, int pn, android.view.View vw) {

                        i.runlibrary.app.sj$lb lbsj = sj.lb(vw);

                        int f = sj.zh(lbsj.lbsj(pn, -1)).zint();

                        if (f == 0) {

                            i.runlibrary.app.v.xxbj xxbj = st.xxbj(vw, open.cn.awg.pro.R.id.xxbj2);
                            xxbj.dqfs("center");
                            xxbj.kjd(0);
                            xxbj.xydj(true);
                            xxbj.xyca(true);

                        } else if (f == 1) {

                            i.runlibrary.app.v.xxbj xxbj = st.xxbj(vw, open.cn.awg.pro.R.id.xxbj2);
                            xxbj.dqfs("center_vertical|left");
                            xxbj.kjd(0);
                            xxbj.xydj(true);
                            xxbj.xyca(true);

                        } else if (f == -1) {

                            i.runlibrary.app.v.xxbj xxbj = st.xxbj(vw, open.cn.awg.pro.R.id.xxbj2);
                            xxbj.dqfs("center_vertical|right");
                            xxbj.kjd(0);
                            xxbj.xydj(true);
                            xxbj.xyca(true);

                        } else if (f == -2) {

                            i.runlibrary.app.v.xxbj xxbj = st.xxbj(vw, open.cn.awg.pro.R.id.xxbj2);
                            xxbj.dqfs("center");
                            xxbj.kjd(8);
                            xxbj.xydj(false);
                            xxbj.xyca(false);

                        } else {

                            i.runlibrary.app.v.xxbj xxbj = st.xxbj(vw, open.cn.awg.pro.R.id.xxbj2);
                            xxbj.dqfs("center");
                            xxbj.kjd(0);
                            xxbj.xydj(true);
                            xxbj.xyca(true);

                        }

                    }

                });

                int[] kj = new int[]{open.cn.awg.pro.R.id.tx1, open.cn.awg.pro.R.id.wb1, open.cn.awg.pro.R.id.wb2, open.cn.awg.pro.R.id.wb3, -1};

                java.lang.Object[] jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a39, "系统工具", "10", "", -1};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a29, "关闭菜单", "31", "", -1};
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a20, "文件管理", "1", "", 0};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a37, "腕上微聊", "9", "", 1};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a40, "X5浏览器", "11", "", -1};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a48, "扩展插件", "20", "", 0};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a43, "更多功能", "13", "", 1};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a10, "应用设置", "8", "", -1};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a46, "返回桌面", "14", "", 0};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a23, "退出应用", "3", "", 1};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{"", "", "", "", -2};
                spq.j(kj, jk);
                spq.j(kj, jk);
                spq.j(kj, jk);

                i.runlibrary.app.v.v7lb lbx = st.v7lb(open.cn.awg.pro.R.id.v7lb1);

                android.support.v7.widget.GridLayoutManager mLayoutManager = new android.support.v7.widget.GridLayoutManager(lei, 3);
                lbx.st.setLayoutManager(mLayoutManager);
                spq.sx();

            } else if (zf.dy(a, "2")) {

                java.lang.String a001 = "/data/user/0/open.cn.awg.pro/settings/dpi.inf";

                if (wj.cz(a001) || zf.dy(wj.dqwb(a001), "true")) {

                    i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
                    wtab.nbj(0, "3dp", 0, "3dp");
                    wtab.dqfs("center");

                    i.runlibrary.app.v.wb wtab2 = st.wb(open.cn.awg.pro.R.id.Title2);
                    wtab2.nbj(0, "3dp", 0, "3dp");
                    wtab2.dqfs("center");

                } else {

                    i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
                    wtab.nbj(0, "9dp", 0, "9dp");
                    wtab.dqfs("center");

                    i.runlibrary.app.v.wb wtab2 = st.wb(open.cn.awg.pro.R.id.Title2);
                    wtab2.nbj(0, "9dp", 0, "9dp");
                    wtab2.dqfs("center");

                }
                st.xdbj(open.cn.awg.pro.R.id.xdbj4).wbj(10, 10, 10, 10);

                i.runlibrary.app.v.v7lb lb = st.v7lb(open.cn.awg.pro.R.id.v7lb1);
                spq = lb.v7lbspq(MainMenuItemActivity.class, open.cn.awg.pro.R.layout.c3);

                int[] kj = new int[]{open.cn.awg.pro.R.id.tx1, open.cn.awg.pro.R.id.wb1, open.cn.awg.pro.R.id.wb2, open.cn.awg.pro.R.id.wb3};

                java.lang.Object[] jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a39, "系统工具", "10", ""};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a29, "关闭菜单", "31", ""};
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a20, "文件管理", "1", ""};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a37, "腕上微聊", "9", ""};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a40, "X5浏览器", "11", ""};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a48, "扩展插件", "20", ""};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a43, "更多功能", "13", ""};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a10, "应用设置", "8", ""};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a46, "返回桌面", "14", ""};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a23, "退出应用", "3", ""};
                spq.j(kj, jk);

                i.runlibrary.app.v.v7lb lbx = st.v7lb(open.cn.awg.pro.R.id.v7lb1);

                if (wj.cz(a001) || zf.dy(wj.dqwb(a001), "true")) {

                    android.support.v7.widget.GridLayoutManager mLayoutManager = new android.support.v7.widget.GridLayoutManager(lei, 2);
                    lbx.st.setLayoutManager(mLayoutManager);

                } else {

                    android.support.v7.widget.GridLayoutManager mLayoutManager = new android.support.v7.widget.GridLayoutManager(lei, 3);
                    lbx.st.setLayoutManager(mLayoutManager);

                }
                spq.sx();

            } else if (zf.dy(a, "3")) {

                st.xdbj(open.cn.awg.pro.R.id.xdbj4).wbj(u, 10, u, 10);

                i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
                wtab.nbj(0, "15dp", 0, "3dp");
                wtab.dqfs("center");

                i.runlibrary.app.v.wb wtab2 = st.wb(open.cn.awg.pro.R.id.Title2);
                wtab2.nbj(0, "15dp", 0, "3dp");
                wtab2.dqfs("center");

                i.runlibrary.app.v.v7lb lb = st.v7lb(open.cn.awg.pro.R.id.v7lb1);
                spq = lb.v7lbspq(MainMenuItemActivity.class, open.cn.awg.pro.R.layout.c3, new i.runlibrary.app.v.v7lb$OnUserAdapterView() {

                    public void getView(i.runlibrary.app.v.v7lb$UserAdapter ua, int pn, android.view.View vw) {

                        i.runlibrary.app.sj$lb lbsj = sj.lb(vw);

                        int f = sj.zh(lbsj.lbsj(pn, -1)).zint();

                        if (f == 0) {

                            i.runlibrary.app.v.xxbj xxbj = st.xxbj(vw, open.cn.awg.pro.R.id.xxbj2);
                            xxbj.dqfs("center");
                            xxbj.kjd(0);
                            xxbj.xydj(true);
                            xxbj.xyca(true);

                        } else if (f == 1) {

                            i.runlibrary.app.v.xxbj xxbj = st.xxbj(vw, open.cn.awg.pro.R.id.xxbj2);
                            xxbj.dqfs("center_vertical|left");
                            xxbj.kjd(0);
                            xxbj.xydj(true);
                            xxbj.xyca(true);

                        } else if (f == -1) {

                            i.runlibrary.app.v.xxbj xxbj = st.xxbj(vw, open.cn.awg.pro.R.id.xxbj2);
                            xxbj.dqfs("center_vertical|right");
                            xxbj.kjd(0);
                            xxbj.xydj(true);
                            xxbj.xyca(true);

                        } else if (f == -2) {

                            i.runlibrary.app.v.xxbj xxbj = st.xxbj(vw, open.cn.awg.pro.R.id.xxbj2);
                            xxbj.dqfs("center");
                            xxbj.kjd(8);
                            xxbj.xydj(false);
                            xxbj.xyca(false);

                        } else {

                            i.runlibrary.app.v.xxbj xxbj = st.xxbj(vw, open.cn.awg.pro.R.id.xxbj2);
                            xxbj.dqfs("center");
                            xxbj.kjd(0);
                            xxbj.xydj(true);
                            xxbj.xyca(true);

                        }

                    }

                });

                int[] kj = new int[]{open.cn.awg.pro.R.id.tx1, open.cn.awg.pro.R.id.wb1, open.cn.awg.pro.R.id.wb2, open.cn.awg.pro.R.id.wb3, -1};

                java.lang.Object[] jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a39, "系统工具", "10", "", -1};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a29, "关闭菜单", "31", "", -1};
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a20, "文件管理", "1", "", 0};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a37, "腕上微聊", "9", "", 1};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a40, "X5浏览器", "11", "", -1};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a48, "扩展插件", "20", "", 0};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a43, "更多功能", "13", "", 1};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a10, "应用设置", "8", "", -1};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a46, "返回桌面", "14", "", 0};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a23, "退出应用", "3", "", 1};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{"", "", "", "", -2};
                spq.j(kj, jk);
                spq.j(kj, jk);
                spq.j(kj, jk);

                i.runlibrary.app.v.v7lb lbx = st.v7lb(open.cn.awg.pro.R.id.v7lb1);

                android.support.v7.widget.GridLayoutManager mLayoutManager = new android.support.v7.widget.GridLayoutManager(lei, 3);
                lbx.st.setLayoutManager(mLayoutManager);
                spq.sx();

            } else if (zf.dy(a, "4")) {

                st.xdbj(open.cn.awg.pro.R.id.xdbj4).wbj(10, 10, 10, 10);

                i.runlibrary.app.v.v7lb lb = st.v7lb(open.cn.awg.pro.R.id.v7lb1);
                spq = lb.v7lbspq(LegacyMenuItemActivity.class, open.cn.awg.pro.R.layout.c7, new i.runlibrary.app.v.v7lb$OnUserAdapterView() {

                    public void getView(i.runlibrary.app.v.v7lb$UserAdapter ua, int pn, android.view.View vw) {

                        i.runlibrary.app.sj$lb lbsj = sj.lb(vw);

                        int f = sj.zh(lbsj.lbsj(pn, -1)).zint();

                        final i.runlibrary.app.v.xdbj xdbj1 = st.xdbj(vw, open.cn.awg.pro.R.id.xdbj1);

                        final i.runlibrary.app.v.wb wb3 = st.wb(vw, open.cn.awg.pro.R.id.wb3);

                        if (f == 0) {

                            xdbj1.kjd(0);
                            wb3.kjd(8);

                        } else {

                            xdbj1.kjd(8);
                            wb3.kjd(0);

                        }

                    }

                });

                int[] kj = new int[]{open.cn.awg.pro.R.id.tx1, open.cn.awg.pro.R.id.wb1, open.cn.awg.pro.R.id.wb2, open.cn.awg.pro.R.id.wb3, -1};

                java.lang.Object[] jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a39, "系统工具", "27", "", 0};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a29, "关闭菜单", "31", "", 0};
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a20, "文件管理", "6", "", 0};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a37, "腕上微聊", "7", "", 0};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a40, "X5浏览器", "10", "", 0};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a48, "扩展插件", "20", "", 0};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a43, "更多功能", "13", "", 0};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a10, "应用设置", "2", "", 0};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a46, "返回桌面", "14", "", 0};
                spq.j(kj, jk);
                jk = new java.lang.Object[]{open.cn.awg.pro.R.mipmap.a23, "退出应用", "12", "", 0};
                spq.j(kj, jk);
                spq.sx();

            }

        }

    }

    public boolean onKeyDown(int kc, android.view.KeyEvent ke) {

        if (kc == 4 && st.xdbj(open.cn.awg.pro.R.id.xdbj10).kjd() == 8 && st.xdbj(open.cn.awg.pro.R.id.xdbj2).kjd() == 8 && st.xdbj(open.cn.awg.pro.R.id.TCxdbj).kjd() == 8) {

            if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "腕管Pro")) {

                show(1);

            } else if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), " 腕管Pro ")) {

                i.runlibrary.app.gj$dh dh = gj.dh();

                final i.runlibrary.app.gj$Animationx jbtm = dh.jbtm(0, 1);
                jbtm.cxsc(300);
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.xdbj(open.cn.awg.pro.R.id.TCxdbj).kjd(0);
                        st.xdbj(open.cn.awg.pro.R.id.TCxdbj).dh(jbtm);

                    }

                });

            }

        }
        return false;

    }

    public void csh0() {

        java.lang.String sec_updater = "/data/user/0/open.cn.awg.pro/data/sec/updater";

        java.lang.String sec_license = "/data/user/0/open.cn.awg.pro/data/sec/license";
        wj.xrwb(sec_license, "1");
        wj.sc(sec_updater);

    }

    public void n3_jz() {
        gj.xc(new java.lang.Thread() {

            public void run() {

                android_id = android.provider.Settings.System.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

                java.lang.String urls = "/data/user/0/open.cn.awg.pro/settings/f10/set2.inf";
                urls = wj.dqwb(urls);
                urls = e1.urlUnlockString(urls);

                if (zf.cjw(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                java.lang.String url = urls + "api.php";

                java.lang.String post_ = "req=openawg_get&token=" + android_id;

                java.lang.String[] post = zf.fg(post_, "&", false);

                if (e1.yz()) {

                    java.lang.String st2 = wl.hq(url, post, "utf-8", null, true, null, 20000, 20000, null);

                    if (zf.dy(st2, null)) {

                    } else {

                        try {

                            java.lang.String code = e1.readJson(st2, "code");

                            if (zf.dy(code, "0")) {

                            } else {

                                isHasReq = true;
                                gj.jmxc(new java.lang.Runnable() {

                                    public void run() {

                                        gj.tz(ThirdPartyAuthorizationActivity.class);

                                    }

                                });

                            }

                        } catch (java.lang.Throwable __$_e__) {

                        }

                    }

                } else {

                }

            }

        });

    }

    public void supertip(java.lang.String t_, int m) {
        supertip_mode = m;

        final java.lang.String t = t_;
        isShow = false;
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                if (supertip_mode == 0) {

                    st.kp(open.cn.awg.pro.R.id.kp5).kjd(0);
                    st.tx(open.cn.awg.pro.R.id.tx16).tx(open.cn.awg.pro.R.mipmap.a17);

                } else if (supertip_mode == 1) {

                    st.kp(open.cn.awg.pro.R.id.kp5).kjd(0);
                    st.tx(open.cn.awg.pro.R.id.tx16).tx(open.cn.awg.pro.R.mipmap.a17);

                } else if (supertip_mode == 2) {

                    st.kp(open.cn.awg.pro.R.id.kp5).kjd(0);
                    st.tx(open.cn.awg.pro.R.id.tx16).tx(open.cn.awg.pro.R.mipmap.a28);

                } else if (supertip_mode == 3) {

                    st.kp(open.cn.awg.pro.R.id.kp5).kjd(0);
                    st.tx(open.cn.awg.pro.R.id.tx16).tx(open.cn.awg.pro.R.mipmap.a62);

                } else if (supertip_mode == 4) {

                    st.kp(open.cn.awg.pro.R.id.kp5).kjd(0);
                    st.tx(open.cn.awg.pro.R.id.tx16).tx(open.cn.awg.pro.R.mipmap.a17);

                } else {

                    st.kp(open.cn.awg.pro.R.id.kp5).kjd(8);
                    st.tx(open.cn.awg.pro.R.id.tx16).tx(open.cn.awg.pro.R.mipmap.a28);

                }

            }

        });
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.wb(open.cn.awg.pro.R.id.wb17).zf("");
                st.wb(open.cn.awg.pro.R.id.wb17).kjd(8);
                st.wb(open.cn.awg.pro.R.id.wb3).zf(t);
                st.xdbj(open.cn.awg.pro.R.id.xdbj10).kjd(0);

            }

        });

        i.runlibrary.app.gj$dh dh = gj.dh();

        i.runlibrary.app.gj$Animationx jbtm = dh.jbtm(1, 0);
        jbtm.cxsc(500);
        st.xdbj(open.cn.awg.pro.R.id.xdbj2).kjd(8);
        st.xdbj(open.cn.awg.pro.R.id.xdbj2).dh(jbtm);

    }

    public void supertip_an() {

        if (supertip_mode == 0) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.wb(open.cn.awg.pro.R.id.wb17).zf("等待联网验证腕管Pro，从V4.0.0版本开始，此过程是必须的\n您可以尝试在设备联网后重启腕管Pro");
                    st.wb(open.cn.awg.pro.R.id.wb17).kjd(0);

                }

            });

        } else if (supertip_mode == 1) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.wb(open.cn.awg.pro.R.id.wb17).zf("您正在使用非官方发行的腕管Pro，可能存在未知风险\n请前往官方网站（https://awg.typheye.cn）下载安装腕管Pro");
                    st.wb(open.cn.awg.pro.R.id.wb17).kjd(0);

                }

            });

        } else if (supertip_mode == 2) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    gj.tz(OnboardingSetupActivity.class);

                }

            });

        } else if (supertip_mode == 3) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    gj.tz(UpdatePromptActivity.class);

                }

            });

        } else if (supertip_mode == 4) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.wb(open.cn.awg.pro.R.id.wb17).zf("您可能并未获得应用许可，该版本不适用于您的设备\n如您已获得应用许可，可以尝试退出应用并联网重新进入应用");
                    st.wb(open.cn.awg.pro.R.id.wb17).kjd(0);

                }

            });

        }

    }

    public void zyqd() {

        java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/f21/set1.inf";

        if (zf.dy(wj.dqwb(set1), "1")) {

            show(1);

        } else if (zf.dy(wj.dqwb(set1), "2")) {

            show(1);
            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    gj.tz(FileRootPickerActivity.class);

                }

            });

        } else if (zf.dy(wj.dqwb(set1), "3")) {

            show(1);
            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    gj.tz(WearChatActivity.class);

                }

            });

        } else if (zf.dy(wj.dqwb(set1), "4")) {

            show(1);
            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    gj.tz(ExtensionPluginActivity.class);

                }

            });

        } else if (zf.dy(wj.dqwb(set1), "5")) {

            show(1);
            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    gj.tz(X5BrowserActivity.class);

                }

            });

        } else {

            homeshow(true);
            isNotMainOnce = false;

        }

    }

    public void homeshow_show(int i_) {

        final int i = i_;

        final i.runlibrary.app.v.xxbj xxbj9 = st.xxbj(open.cn.awg.pro.R.id.xxbj9);

        final i.runlibrary.app.v.wb wb2 = st.wb(open.cn.awg.pro.R.id.wb2);
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                if (i == 0) {

                    xxbj9.kjd(0);
                    wb2.kjd(8);

                } else if (i == 1) {

                    xxbj9.kjd(8);
                    wb2.kjd(0);

                }

            }

        });

    }

    public void homeshow(boolean t_) {

        final boolean t = t_;

        if (t) {

            state_once_homeshow = true;

        }

        if (!state_homeshow && state_once_homeshow) {

            state_homeshow = true;

            final i.runlibrary.app.v.xdbj xdbj20 = st.xdbj(open.cn.awg.pro.R.id.xdbj20);

            final i.runlibrary.app.v.tx tx11 = st.tx(open.cn.awg.pro.R.id.tx11);

            final i.runlibrary.app.v.wb wb29 = st.wb(open.cn.awg.pro.R.id.wb29);

            final i.runlibrary.app.v.tx tx18 = st.tx(open.cn.awg.pro.R.id.tx18);

            final i.runlibrary.app.v.wb wb52 = st.wb(open.cn.awg.pro.R.id.wb52);

            final i.runlibrary.app.v.wb wb54 = st.wb(open.cn.awg.pro.R.id.wb54);

            final i.runlibrary.app.v.tx tx6 = st.tx(open.cn.awg.pro.R.id.tx6);

            final i.runlibrary.app.v.wb wb14 = st.wb(open.cn.awg.pro.R.id.wb14);

            final i.runlibrary.app.v.wb wb16 = st.wb(open.cn.awg.pro.R.id.wb16);

            final i.runlibrary.app.v.tx tx9 = st.tx(open.cn.awg.pro.R.id.tx9);

            final i.runlibrary.app.v.wb wb23 = st.wb(open.cn.awg.pro.R.id.wb23);

            final i.runlibrary.app.v.wb wb25 = st.wb(open.cn.awg.pro.R.id.wb25);

            final i.runlibrary.app.v.tx tx10 = st.tx(open.cn.awg.pro.R.id.tx10);

            final i.runlibrary.app.v.wb wb26 = st.wb(open.cn.awg.pro.R.id.wb26);

            final i.runlibrary.app.v.wb wb28 = st.wb(open.cn.awg.pro.R.id.wb28);

            final i.runlibrary.app.v.wb wb31 = st.wb(open.cn.awg.pro.R.id.wb31);

            final i.runlibrary.app.v.wb wb33 = st.wb(open.cn.awg.pro.R.id.wb33);

            final i.runlibrary.app.v.wb wb34 = st.wb(open.cn.awg.pro.R.id.wb34);

            final i.runlibrary.app.v.wb wb36 = st.wb(open.cn.awg.pro.R.id.wb36);

            final i.runlibrary.app.v.wb wb37 = st.wb(open.cn.awg.pro.R.id.wb37);

            final i.runlibrary.app.v.wb wb39 = st.wb(open.cn.awg.pro.R.id.wb39);

            final i.runlibrary.app.v.wb wb42 = st.wb(open.cn.awg.pro.R.id.wb42);

            final i.runlibrary.app.v.wb wb44 = st.wb(open.cn.awg.pro.R.id.wb44);

            final i.runlibrary.app.v.wb wb45 = st.wb(open.cn.awg.pro.R.id.wb45);

            final i.runlibrary.app.v.wb wb47 = st.wb(open.cn.awg.pro.R.id.wb47);

            final i.runlibrary.app.v.wb wb48 = st.wb(open.cn.awg.pro.R.id.wb48);

            final i.runlibrary.app.v.wb wb50 = st.wb(open.cn.awg.pro.R.id.wb50);
            gj.xc(new java.lang.Thread() {

                public void run() {

                    if (t) {

                        show(2);

                    }
                    gj.zt(500);

                    boolean canshow = false;

                    final java.lang.String nopa2 = "/data/user/0/open.cn.awg.pro/data/homeshow/json";

                    final java.lang.String img_lbl1_path = "/data/user/0/open.cn.awg.pro/data/homeshow/src/img/lbl1";

                    final java.lang.String img_ad1_path = "/data/user/0/open.cn.awg.pro/data/homeshow/src/img/ad1";

                    final java.lang.String img_yytj_item1_path = "/data/user/0/open.cn.awg.pro/data/homeshow/src/img/yytj_item1";

                    final java.lang.String img_yytj_item2_path = "/data/user/0/open.cn.awg.pro/data/homeshow/src/img/yytj_item2";

                    final java.lang.String img_yytj_item3_path = "/data/user/0/open.cn.awg.pro/data/homeshow/src/img/yytj_item3";

                    java.lang.String urls = "/data/user/0/open.cn.awg.pro/settings/f10/set2.inf";
                    urls = wj.dqwb(urls);
                    urls = e1.urlUnlockString(urls);

                    if (zf.cjw(urls, "/")) {

                    } else {

                        urls = urls + "/";

                    }

                    java.lang.String url = urls + "homeshow.json";

                    if (e1.yz()) {

                        java.lang.String st2 = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                        if (zf.dy(st2, null)) {

                            if (wj.cz(nopa2)) {

                                canshow = true;

                            }

                        } else {

                            java.lang.String f1 = e1.readJson(st2, "code");

                            if (zf.dy(f1, "200")) {

                                if (wj.cz(nopa2)) {

                                    java.lang.String md5a = sj.md5(wj.dqwb(nopa2));

                                    java.lang.String md5b = sj.md5(st2);

                                    if (!zf.dy(md5a, md5b)) {

                                        wj.sc(img_lbl1_path);
                                        wj.sc(img_ad1_path);
                                        wj.sc(img_yytj_item1_path);
                                        wj.sc(img_yytj_item2_path);
                                        wj.sc(img_yytj_item3_path);

                                    }

                                }
                                wj.xrwb(nopa2, st2);
                                canshow = true;

                            }

                        }

                    }

                    if (canshow) {

                        final java.lang.String st5 = wj.dqwb(nopa2);
                        div_lbl1_url = e1.readJson(st5, "div_lbl1_url");
                        div_ad1_url = e1.readJson(st5, "div_ad1_url");
                        div_yytj_item1_url = e1.readJson(st5, "div_yytj_item1_url");
                        div_yytj_item2_url = e1.readJson(st5, "div_yytj_item2_url");
                        div_yytj_item3_url = e1.readJson(st5, "div_yytj_item3_url");
                        div_jcsp_item1_url = e1.readJson(st5, "div_jcsp_item1_url");
                        div_jcsp_item2_url = e1.readJson(st5, "div_jcsp_item2_url");
                        div_jcsp_item3_url = e1.readJson(st5, "div_jcsp_item3_url");
                        div_rmyy_item1_url = e1.readJson(st5, "div_rmyy_item1_url");
                        div_rmyy_item2_url = e1.readJson(st5, "div_rmyy_item2_url");
                        div_rmyy_item3_url = e1.readJson(st5, "div_rmyy_item3_url");

                        final java.lang.String ad_state = e1.readJson(st5, "ad_state");

                        final java.lang.String div_lbl1_img_url = e1.readJson(st5, "div_lbl1_img_url");

                        final java.lang.String div_lbl1_text = e1.readJson(st5, "div_lbl1_text");

                        final java.lang.String div_ad1_img_url = e1.readJson(st5, "div_ad1_img_url");

                        final java.lang.String div_ad1_text = e1.readJson(st5, "div_ad1_text");

                        final java.lang.String div_ad1_subtext = e1.readJson(st5, "div_ad1_subtext");

                        final java.lang.String div_yytj_item1_text = e1.readJson(st5, "div_yytj_item1_text");

                        final java.lang.String div_yytj_item1_subtext = e1.readJson(st5, "div_yytj_item1_subtext");

                        final java.lang.String div_yytj_item2_text = e1.readJson(st5, "div_yytj_item2_text");

                        final java.lang.String div_yytj_item2_subtext = e1.readJson(st5, "div_yytj_item2_subtext");

                        final java.lang.String div_yytj_item3_text = e1.readJson(st5, "div_yytj_item3_text");

                        final java.lang.String div_yytj_item3_subtext = e1.readJson(st5, "div_yytj_item3_subtext");

                        final java.lang.String div_jcsp_item1_text = e1.readJson(st5, "div_jcsp_item1_text");

                        final java.lang.String div_jcsp_item1_subtext = e1.readJson(st5, "div_jcsp_item1_subtext");

                        final java.lang.String div_jcsp_item2_text = e1.readJson(st5, "div_jcsp_item2_text");

                        final java.lang.String div_jcsp_item2_subtext = e1.readJson(st5, "div_jcsp_item2_subtext");

                        final java.lang.String div_jcsp_item3_text = e1.readJson(st5, "div_jcsp_item3_text");

                        final java.lang.String div_jcsp_item3_subtext = e1.readJson(st5, "div_jcsp_item3_subtext");

                        final java.lang.String div_rmyy_item1_text = e1.readJson(st5, "div_rmyy_item1_text");

                        final java.lang.String div_rmyy_item1_subtext = e1.readJson(st5, "div_rmyy_item1_subtext");

                        final java.lang.String div_rmyy_item2_text = e1.readJson(st5, "div_rmyy_item2_text");

                        final java.lang.String div_rmyy_item2_subtext = e1.readJson(st5, "div_rmyy_item2_subtext");

                        final java.lang.String div_rmyy_item3_text = e1.readJson(st5, "div_rmyy_item3_text");

                        final java.lang.String div_rmyy_item3_subtext = e1.readJson(st5, "div_rmyy_item3_subtext");

                        java.lang.String downfile = div_lbl1_img_url;

                        java.lang.String downfilepath = img_lbl1_path;

                        if (!wj.cz(downfilepath)) {

                            if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                wj.sc(downfilepath);

                            }

                        }
                        downfile = div_ad1_img_url;
                        downfilepath = img_ad1_path;

                        if (!wj.cz(downfilepath)) {

                            if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                wj.sc(downfilepath);

                            }

                        }
                        downfile = getAppIconUrl(div_yytj_item1_url);
                        downfilepath = img_yytj_item1_path;

                        if (!wj.cz(downfilepath)) {

                            if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                wj.sc(downfilepath);

                            }

                        }
                        downfile = getAppIconUrl(div_yytj_item2_url);
                        downfilepath = img_yytj_item2_path;

                        if (!wj.cz(downfilepath)) {

                            if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                wj.sc(downfilepath);

                            }

                        }
                        downfile = getAppIconUrl(div_yytj_item3_url);
                        downfilepath = img_yytj_item3_path;

                        if (!wj.cz(downfilepath)) {

                            if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                wj.sc(downfilepath);

                            }

                        }
                        gj.jmxc(new java.lang.Runnable() {

                            public void run() {

                                if (zf.dy(ad_state, "true") && !e1.isJh()) {

                                    xdbj20.kjd(0);

                                } else {

                                    xdbj20.kjd(8);

                                }

                                if (wj.cz(img_lbl1_path)) {

                                    tx11.tx(img_lbl1_path);

                                } else {

                                    tx11.tx(open.cn.awg.pro.R.mipmap.load_photo);

                                }
                                wb29.zf(div_lbl1_text);

                                if (wj.cz(img_ad1_path)) {

                                    tx18.tx(img_ad1_path);

                                } else {

                                    tx18.tx(open.cn.awg.pro.R.mipmap.b11);

                                }
                                wb52.zf(div_ad1_text);
                                wb54.zf(div_ad1_subtext);

                                if (wj.cz(img_yytj_item1_path)) {

                                    tx6.tx(img_yytj_item1_path);

                                } else {

                                    tx6.tx(open.cn.awg.pro.R.mipmap.appicon);

                                }
                                wb14.zf(div_yytj_item1_text);
                                wb16.zf(div_yytj_item1_subtext);

                                if (wj.cz(img_yytj_item2_path)) {

                                    tx9.tx(img_yytj_item2_path);

                                } else {

                                    tx9.tx(open.cn.awg.pro.R.mipmap.appicon);

                                }
                                wb23.zf(div_yytj_item2_text);
                                wb25.zf(div_yytj_item2_subtext);

                                if (wj.cz(img_yytj_item3_path)) {

                                    tx10.tx(img_yytj_item3_path);

                                } else {

                                    tx10.tx(open.cn.awg.pro.R.mipmap.appicon);

                                }
                                wb26.zf(div_yytj_item3_text);
                                wb28.zf(div_yytj_item3_subtext);
                                wb31.zf(div_jcsp_item1_text);
                                wb33.zf(div_jcsp_item1_subtext);
                                wb34.zf(div_jcsp_item2_text);
                                wb36.zf(div_jcsp_item2_subtext);
                                wb37.zf(div_jcsp_item3_text);
                                wb39.zf(div_jcsp_item3_subtext);
                                wb42.zf(div_rmyy_item1_text);
                                wb44.zf(div_rmyy_item1_subtext);
                                wb45.zf(div_rmyy_item2_text);
                                wb47.zf(div_rmyy_item2_subtext);
                                wb48.zf(div_rmyy_item3_text);
                                wb50.zf(div_rmyy_item3_subtext);

                            }

                        });
                        homeshow_show(0);

                        if (t) {

                            show(0);

                        }

                    } else {

                        homeshow_show(1);

                        if (t) {

                            show(0);

                        }

                    }
                    state_homeshow = false;

                }

            });

        } else {

            if (t) {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            }

        }

    }

    public java.lang.String getAppIconUrl(java.lang.Object id) {
        id = zf.qc(id, "awg://uri/app/", null);
        return src_url + "/app/" + id + "/app-image/icon.png";

    }

    public int homeshow_filedown(java.lang.Object url, java.lang.Object post, java.lang.Object cookie, java.lang.Object path) {

        java.lang.Object a = url;

        java.lang.Object b = path;

        java.lang.Object c = post;

        java.lang.Object d = cookie;

        boolean e = true;

        java.lang.String f = "User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept-language=zh-CN";
        return wl.xz(a, b, true, c, "utf-8", d, e, f);

    }

    public void show(int i_) {

        final java.lang.String set65 = "/data/user/0/open.cn.awg.pro/settings/set5.inf";

        final int i = i_;

        final i.runlibrary.app.v.wb tab = st.wb(open.cn.awg.pro.R.id.Tab);

        final i.runlibrary.app.v.xdbj xdbj1 = st.xdbj(open.cn.awg.pro.R.id.xdbj1);

        final i.runlibrary.app.v.xdbj xdbj5 = st.xdbj(open.cn.awg.pro.R.id.xdbj5);

        final i.runlibrary.app.v.xdbj xdbj6 = st.xdbj(open.cn.awg.pro.R.id.xdbj6);

        final i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(open.cn.awg.pro.R.id.qtgd1);

        final i.runlibrary.app.v.v7lb v7lb1 = st.v7lb(open.cn.awg.pro.R.id.v7lb1);

        final java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/f21/set1.inf";

        i.runlibrary.app.gj$dh dh = gj.dh();

        final i.runlibrary.app.gj$Animationx jbtm = dh.jbtm(0, 1);
        jbtm.cxsc(300);
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                if (i == 0) {

                    qtgd1.v.requestFocus();
                    tab.zf(zf.zf(open.cn.awg.pro.R.string.app_name));
                    xdbj1.kjd(8);
                    xdbj5.kjd(0);
                    xdbj6.kjd(8);
                    xdbj5.dh(jbtm);

                    if (isNotMainOnce && !zf.dy(wj.dqwb(set1), "0")) {

                        show(2);
                        isNotMainOnce = false;
                        homeshow(true);

                    } else {

                        homeshow(false);

                    }

                    if (zf.dy(wj.dqwb(set65), "false") || !wj.cz(set65)) {

                        st.wb(open.cn.awg.pro.R.id.wb9).kjd(0);

                    }

                } else if (i == 1) {

                    v7lb1.v.requestFocus();
                    tab.zf(" 腕管Pro ");
                    xdbj1.kjd(0);
                    xdbj5.kjd(8);
                    xdbj6.kjd(8);
                    xdbj1.dh(jbtm);

                } else if (i == 2) {

                    tab.zf("  腕管Pro  ");
                    xdbj1.kjd(8);
                    xdbj5.kjd(8);
                    xdbj6.kjd(0);
                    xdbj6.dh(jbtm);

                }

            }

        });

    }

    private void scheduleJob() {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (jobScheduler != null) {
            int jobId = 1;
            ComponentName serviceComponent = new ComponentName(this, BootJobService.class);

            JobInfo.Builder builder = new JobInfo.Builder(jobId, serviceComponent);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
                builder.setRequiresDeviceIdle(false);
                builder.setRequiresStorageNotLow(false);
                builder.setRequiresBatteryNotLow(false);
                builder.setPersisted(true);
            } else {

            }

            JobInfo jobInfo = builder.build();

            try {
                jobScheduler.schedule(jobInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void noticeShow() {

        if (e1.isJh()) {

            st.wb(open.cn.awg.pro.R.id.wb11).kjd(8);
            st.wb(open.cn.awg.pro.R.id.wb13).kjd(8);

        } else {

            st.wb(open.cn.awg.pro.R.id.wb11).kjd(0);
            st.wb(open.cn.awg.pro.R.id.wb13).kjd(0);

        }

        final java.lang.String def = "欢迎使用腕管Pro，这是一款优秀的应用。您可以前往腕管Pro官网：https://www.typheye.cn/awg，提出建议或意见。如果您有任何问题，可以联系开发者台风眼(QQ498978473)，或者通过邮箱(498978473@qq.com)联系我们！";
        gj.xc(new java.lang.Thread() {

            public void run() {

                final java.lang.String nopa = "/data/user/0/open.cn.awg.pro/data/notice/showing";

                final java.lang.String nopa2 = "/data/user/0/open.cn.awg.pro/data/notice/json";

                java.lang.String nr = "";

                if (wj.cz(nopa) && !zf.dy(wj.dqwb(nopa), "")) {

                    nr = wj.dqwb(nopa);

                } else {

                    nr = def;

                }

                final java.lang.String nr1 = nr;
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.wb(open.cn.awg.pro.R.id.notice_wb).zf(nr1);

                    }

                });

                java.lang.String urls = "/data/user/0/open.cn.awg.pro/settings/f10/set2.inf";
                urls = wj.dqwb(urls);
                urls = e1.urlUnlockString(urls);

                if (zf.cjw(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                java.lang.String url = urls + "notice.json";

                final java.lang.String st5 = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                if (zf.dy(st5, null)) {

                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            java.lang.String nr = "";

                            if (wj.cz(nopa) && !zf.dy(wj.dqwb(nopa), "")) {

                                nr = wj.dqwb(nopa);

                            } else {

                                nr = def;

                            }
                            st.wb(open.cn.awg.pro.R.id.notice_wb).zf(nr);

                        }

                    });

                } else {

                    java.lang.String f1 = e1.readJson(st5, "Showing");

                    if (zf.dy(f1, "0")) {

                        gj.jmxc(new java.lang.Runnable() {

                            public void run() {

                                java.lang.String nr = "";

                                if (wj.cz(nopa) && !zf.dy(wj.dqwb(nopa), "")) {

                                    nr = wj.dqwb(nopa);

                                } else {

                                    nr = def;

                                }
                                st.wb(open.cn.awg.pro.R.id.notice_wb).zf(nr);

                            }

                        });

                    } else {

                        if (zf.dy(f1, "")) {

                            gj.jmxc(new java.lang.Runnable() {

                                public void run() {

                                    java.lang.String nr = "";

                                    if (wj.cz(nopa) && !zf.dy(wj.dqwb(nopa), "")) {

                                        nr = wj.dqwb(nopa);

                                    } else {

                                        nr = def;

                                    }
                                    st.wb(open.cn.awg.pro.R.id.notice_wb).zf(nr);

                                }

                            });

                        } else {

                            final java.lang.String nr2 = f1;
                            wj.xrwb(nopa, nr2);
                            wj.xrwb(nopa2, st5);
                            gj.jmxc(new java.lang.Runnable() {

                                public void run() {

                                    st.wb(open.cn.awg.pro.R.id.notice_wb).zf(nr2);

                                }

                            });

                        }

                    }

                }

            }

        });

    }

    public void inj() {

        final java.lang.String sec_updater = "/data/user/0/open.cn.awg.pro/data/sec/updater";

        final java.lang.String sec_isunlock = "/data/user/0/open.cn.awg.pro/data/sec/unlock";

        final java.lang.String sec_license = "/data/user/0/open.cn.awg.pro/data/sec/license";
        gj.xc(new java.lang.Thread() {

            public void run() {

                e1.yz();

            }

        });

        if (!wj.cz(sec_isunlock)) {

            gj.zt(2500);

        }

        if (wj.cz(sec_isunlock)) {

            if (zf.dy(wj.dqwb(sec_isunlock), "false")) {

                supertip("应用验证未通过", 1);

            } else {

                if (wj.cz(sec_license)) {

                    if (wj.cz(sec_updater)) {

                        supertip("应用需要更新", 3);

                    } else {

                        run();

                    }

                } else {

                    supertip("未获得应用许可", 4);

                }

            }

        } else {

            supertip("等待联网验证", 0);

        }

    }

    public void set() {

        java.lang.String asset_a0 = "/data/user/0/open.cn.awg.pro/data/assets/a0";

        java.lang.String asset_a2 = "/data/user/0/open.cn.awg.pro/data/assets/a2";

        java.lang.String asset_a3 = "/data/user/0/open.cn.awg.pro/data/assets/a3";

        java.lang.String asset_a4 = "/data/user/0/open.cn.awg.pro/data/assets/a4";

        java.lang.String asset_a5 = "/data/user/0/open.cn.awg.pro/data/assets/a5";

        java.lang.String asset_a6 = "/data/user/0/open.cn.awg.pro/data/assets/a6";

        java.lang.String asset_a7 = "/data/user/0/open.cn.awg.pro/data/assets/a7";

        java.lang.String asset_a8 = "/data/user/0/open.cn.awg.pro/data/assets/a8";

        java.lang.String asset_a9 = "/data/user/0/open.cn.awg.pro/data/assets/a9";

        java.lang.String asset_a10 = "/data/user/0/open.cn.awg.pro/data/assets/a10";

        java.lang.String asset_a12 = "/data/user/0/open.cn.awg.pro/data/assets/a12";

        java.lang.String asset_a13 = "/data/user/0/open.cn.awg.pro/data/assets/a13";

        java.lang.String asset_a14 = "/data/user/0/open.cn.awg.pro/data/assets/a14";

        java.lang.String asset_a15 = "/data/user/0/open.cn.awg.pro/data/assets/a15";

        java.lang.String asset_a16 = "/data/user/0/open.cn.awg.pro/data/assets/a16";

        java.lang.String asset_a17 = "/data/user/0/open.cn.awg.pro/data/assets/a17";

        java.lang.String asset_a18 = "/data/user/0/open.cn.awg.pro/data/assets/a18";

        java.lang.String asset_a19 = "/data/user/0/open.cn.awg.pro/data/assets/a19";

        java.lang.String asset_a20 = "/data/user/0/open.cn.awg.pro/data/assets/a20";

        java.lang.String asset_b0 = "/data/user/0/open.cn.awg.pro/data/assets/b0";

        java.lang.String asset_b1 = "/data/user/0/open.cn.awg.pro/data/assets/b1";

        java.lang.String asset_b2 = "/data/user/0/open.cn.awg.pro/data/assets/b2";

        java.lang.String asset_b3 = "/data/user/0/open.cn.awg.pro/data/assets/b3";

        java.lang.String asset_c1 = "/data/user/0/open.cn.awg.pro/data/assets/c1";

        java.lang.String asset_e3 = "/data/user/0/open.cn.awg.pro/data/assets/e3";

        if (!wj.cz(asset_a0)) {

            wj.xrwb(asset_a0, "0");

        }

        if (!wj.cz(asset_a2)) {

            wj.xrwb(asset_a2, "加载失败,未找到该资源!");

        }

        if (!wj.cz(asset_a3)) {

            wj.xrwb(asset_a3, "加载失败,未找到该资源!");

        }

        if (!wj.cz(asset_a4)) {

            wj.xrwb(asset_a4, "加载失败,未找到该资源!");

        }

        if (!wj.cz(asset_a5)) {

            wj.xrwb(asset_a5, "加载失败,未找到该资源!");

        }

        if (!wj.cz(asset_a6)) {

            wj.xrwb(asset_a6, "加载失败,未找到该资源!");

        }

        if (!wj.cz(asset_a7)) {

            wj.xrwb(asset_a7, "加载失败,未找到该资源!");

        }

        if (!wj.cz(asset_a8)) {

            wj.xrwb(asset_a8, "加载失败,未找到该资源!");

        }

        if (!wj.cz(asset_a9)) {

            wj.xrwb(asset_a9, "加载失败,未找到该资源!");

        }

        if (!wj.cz(asset_a10)) {

            wj.xrwb(asset_a10, "加载失败,未找到该资源!");

        }

        if (!wj.cz(asset_a12)) {

            wj.xrwb(asset_a12, "加载失败,未找到该资源!");

        }

        if (!wj.cz(asset_a13)) {

            wj.xrwb(asset_a13, "加载失败,未找到该资源!");

        }

        if (!wj.cz(asset_a14)) {

            wj.xrwb(asset_a14, "加载失败,未找到该资源!");

        }

        if (!wj.cz(asset_a15)) {

            wj.xrwb(asset_a15, "加载失败,未找到该资源!");

        }

        if (!wj.cz(asset_a16)) {

            wj.xrwb(asset_a16, "加载失败,未找到该资源!");

        }

        if (!wj.cz(asset_a17)) {

            wj.xrwb(asset_a17, "&");

        }

        if (!wj.cz(asset_a18)) {

            wj.xrwb(asset_a18, "{}");

        }

        if (!wj.cz(asset_a19)) {

            wj.xrwb(asset_a19, "加载失败,未找到该资源!");

        }

        if (!wj.cz(asset_a20)) {

            wj.xrwb(asset_a20, "加载失败,未找到该资源!");

        }

        if (!wj.cz(asset_b0)) {

            wj.xrwb(asset_b0, "加载失败,未找到该资源!");

        }

        if (!wj.cz(asset_b1)) {

            wj.xrwb(asset_b1, "加载失败,未找到该资源!");

        }

        if (!wj.cz(asset_b2)) {

            wj.xrwb(asset_b2, "加载失败,未找到该资源!");

        }

        if (!wj.cz(asset_b3)) {

            wj.xrwb(asset_b3, "加载失败,未找到该资源!");

        }

        if (!wj.cz(asset_c1)) {

            wj.xrwb(asset_c1, "echo \"加载失败,未找到该资源!\"");

        }

        if (!wj.cz(asset_e3)) {

            wj.xrwb(asset_e3, "加载失败,未找到该资源!");

        }

        java.lang.String set0 = "/data/user/0/open.cn.awg.pro/settings/version";

        java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/set1.inf";

        java.lang.String set2 = "/data/user/0/open.cn.awg.pro/settings/set2.inf";

        java.lang.String set3 = "/data/user/0/open.cn.awg.pro/settings/d2/set1.inf";

        java.lang.String set4 = "/data/user/0/open.cn.awg.pro/settings/d2/set2.inf";

        java.lang.String set5 = "/data/user/0/open.cn.awg.pro/settings/set3.inf";

        java.lang.String set6 = "/data/user/0/open.cn.awg.pro/settings/d2/set3.inf";

        java.lang.String set7 = "/data/user/0/open.cn.awg.pro/settings/d2/set4.inf";

        java.lang.String set8 = "/data/user/0/open.cn.awg.pro/settings/d1/set1.inf";

        java.lang.String set9 = "/data/user/0/open.cn.awg.pro/settings/f7/set1.inf";

        java.lang.String set10 = "/data/user/0/open.cn.awg.pro/settings/f8/set1.inf";

        java.lang.String set11 = "/data/user/0/open.cn.awg.pro/settings/f9/set1.inf";

        java.lang.String set12 = "/data/user/0/open.cn.awg.pro/settings/f10/set1.inf";

        java.lang.String set13 = "/data/user/0/open.cn.awg.pro/settings/f10/set2.inf";

        java.lang.String set14 = "/data/user/0/open.cn.awg.pro/settings/f11/set1.inf";

        java.lang.String set15 = "/data/user/0/open.cn.awg.pro/settings/f11/set2.inf";

        java.lang.String set16 = "/data/user/0/open.cn.awg.pro/settings/f11/set3.inf";

        java.lang.String set17 = "/data/user/0/open.cn.awg.pro/settings/db/talking.db";

        java.lang.String set18 = "/data/user/0/open.cn.awg.pro/settings/f8/set2.inf";

        java.lang.String set19 = "/data/user/0/open.cn.awg.pro/settings/f13/set1.inf";

        java.lang.String set20 = "/data/user/0/open.cn.awg.pro/settings/f13/set2.inf";

        java.lang.String set21 = "/data/user/0/open.cn.awg.pro/settings/f13/set3.inf";

        java.lang.String set22 = "/data/user/0/open.cn.awg.pro/settings/f8/set3.inf";

        java.lang.String set23 = "/data/user/0/open.cn.awg.pro/settings/d1/set3.inf";

        java.lang.String set24 = "/data/user/0/open.cn.awg.pro/settings/f15/set1.inf";

        java.lang.String set25 = "/data/user/0/open.cn.awg.pro/settings/d1/set4.inf";

        java.lang.String set26 = "/data/user/0/open.cn.awg.pro/settings/f15/set2.inf";

        java.lang.String set27 = "/data/user/0/open.cn.awg.pro/settings/db/file.db";

        java.lang.String set28 = "/storage/emulated/0/Download/.nomedia";

        java.lang.String set29 = "/data/user/0/open.cn.awg.pro/Download/.nomedia";

        java.lang.String set30 = "/data/user/0/open.cn.awg.pro/settings/f8/set10.inf";

        java.lang.String set31 = "/data/user/0/open.cn.awg.pro/settings/d2/set5.inf";

        java.lang.String set32 = "/data/user/0/open.cn.awg.pro/settings/f13/set4.inf";

        java.lang.String set33 = "/data/user/0/open.cn.awg.pro/settings/other/0xF0HX1/index.json";

        java.lang.String set34 = "/data/user/0/open.cn.awg.pro/settings/f15/set3.inf";

        java.lang.String set35 = "/data/user/0/open.cn.awg.pro/settings/other/0xO7YX2/url";

        java.lang.String set36 = "/data/user/0/open.cn.awg.pro/settings/fixVersion";

        java.lang.String set37 = "/data/user/0/open.cn.awg.pro/settings/f15/set4.inf";

        java.lang.String set38 = "/data/user/0/open.cn.awg.pro/settings/f13/set5.inf";

        java.lang.String set39 = "/data/user/0/open.cn.awg.pro/settings/f11/set4.inf";

        java.lang.String set40 = "/data/user/0/open.cn.awg.pro/settings/f17/set1.inf";

        java.lang.String set41 = "/data/user/0/open.cn.awg.pro/settings/f8/set4.inf";

        java.lang.String set42 = "/data/user/0/open.cn.awg.pro/settings/set4s.inf";

        java.lang.String set43 = "/data/user/0/open.cn.awg.pro/settings/l6/set1.inf";

        java.lang.String set44 = "/data/user/0/open.cn.awg.pro/settings/db/isO";

        java.lang.String set45 = "/data/user/0/open.cn.awg.pro/settings/f18/set1.inf";

        java.lang.String set46 = "/data/user/0/open.cn.awg.pro/settings/f18/set2.inf";

        java.lang.String set47 = "/data/user/0/open.cn.awg.pro/settings/db/plugin.db";

        java.lang.String set48 = "/data/user/0/open.cn.awg.pro/settings/f19/set1.inf";

        java.lang.String set49 = "/data/user/0/open.cn.awg.pro/settings/f19/set2.inf";

        java.lang.String set50 = "/data/user/0/open.cn.awg.pro/settings/f19/set3.inf";

        java.lang.String set51 = "/data/user/0/open.cn.awg.pro/settings/f19/set4.inf";

        java.lang.String set52 = "/data/user/0/open.cn.awg.pro/settings/n1/set1.inf";

        java.lang.String set53 = "/data/user/0/open.cn.awg.pro/settings/n1/set2.inf";

        java.lang.String set54 = "/data/user/0/open.cn.awg.pro/settings/n1/set3.inf";

        java.lang.String set55 = "/data/user/0/open.cn.awg.pro/settings/n1/set4.inf";

        java.lang.String set56 = "/data/user/0/open.cn.awg.pro/settings/db/isO2";

        java.lang.String set57 = "/data/user/0/open.cn.awg.pro/settings/n1/url.inf";

        java.lang.String set58 = "/data/user/0/open.cn.awg.pro/settings/f7/Enable";

        java.lang.String set59 = "/data/user/0/open.cn.awg.pro/settings/other/0xO5HJ7/url";

        java.lang.String set60 = "/data/user/0/open.cn.awg.pro/settings/f7/Enable2";

        java.lang.String set61 = "/data/user/0/open.cn.awg.pro/settings/f15/set5.inf";

        java.lang.String set62 = "/data/user/0/open.cn.awg.pro/settings/other/0xO7YX2/cookie";

        java.lang.String set63 = "/data/user/0/open.cn.awg.pro/settings/set4.inf";

        java.lang.String set64 = "/data/user/0/open.cn.awg.pro/settings/i1/set1.inf";

        java.lang.String set65 = "/data/user/0/open.cn.awg.pro/settings/set5.inf";

        java.lang.String set66 = "/data/user/0/open.cn.awg.pro/settings/set6.inf";

        java.lang.String set67 = "/data/user/0/open.cn.awg.pro/settings/set7.inf";

        java.lang.String set68 = "/data/user/0/open.cn.awg.pro/settings/f21/set1.inf";

        if (!wj.cz(set1)) {

            wj.xrwb(set1, "false");

        }

        if (!wj.cz(set2)) {

            wj.xrwb(set2, "false");

        }

        if (!wj.cz(set3)) {

            wj.xrwb(set3, "false");

        }

        if (!wj.cz(set4)) {

            wj.xrwb(set4, "13");

        }

        if (!wj.cz(set5)) {

            wj.xrwb(set5, "false");

        }

        if (!wj.cz(set6)) {

            wj.xrwb(set6, "#ffffff");

        }

        if (!wj.cz(set7)) {

            wj.xrwb(set7, "#000000");

        }

        if (!wj.cz(set8)) {

            wj.xrwb(set8, "false");

        }

        if (!wj.cz(set9)) {

            wj.xrwb(set9, "false");

        }

        if (!wj.cz(set10)) {

            wj.xrwb(set10, "false");

        }

        if (!wj.cz(set11)) {

            wj.xrwb(set11, "true");

        }

        if (!wj.cz(set12)) {

            wj.xrwb(set12, "true");

        }

        if (!wj.cz(set13)) {

            wj.xrwb(set13, "awg://web.url/app/open.cn.awg.pro");

        }

        if (!wj.cz(set14)) {

            wj.xrwb(set14, "true");

        }

        if (!wj.cz(set15)) {

            wj.xrwb(set15, "awg://web.url/app/open.cn.awg.pro/talking");

        }

        if (!wj.cz(set16)) {

            wj.xrwb(set16, "腕管用户" + xt.sj(4));

        }

        if (!wj.cz(set17)) {

            e11.setup_talklist();

        }

        if (!wj.cz(set18)) {

            wj.xrwb(set18, "true");

        }

        if (!wj.cz(set19)) {

            wj.xrwb(set19, "false");

        }

        if (!wj.cz(set20)) {

            wj.xrwb(set20, "awg://thirds.url/www.baidu.com");

        }

        if (!wj.cz(set21)) {

            wj.xrwb(set21, "{\"MODE\":0,\"UA\":\"Android\"}");

        }

        if (!wj.cz(set22)) {

            wj.xrwb(set22, "true");

        }

        if (!wj.cz(set23)) {

            wj.xrwb(set23, "true");

        }

        if (!wj.cz(set24)) {

            wj.xrwb(set24, "false");

        }

        if (!wj.cz(set25)) {

            wj.xrwb(set25, "true");

        }

        if (!wj.cz(set26)) {

            wj.xrwb(set26, "false");

        }
        wj.sc(set27);
        e11.setup_filelist();

        i.runlibrary.app.xt$qxgl a = xt.qxgl();

        int sdk = xt.sbxx().sdk;

        if (a.qx("android.permission.WRITE_EXTERNAL_STORAGE") && sdk < 30) {

            java.lang.String m = "mkdir \"/sdcard/Download\"";
            com.demo.e3.cmd(lei, m, false);
            m = "mkdir \"/sdcard/DCIM\"";
            com.demo.e3.cmd(lei, m, false);
            m = "mkdir \"/sdcard/Documents\"";
            com.demo.e3.cmd(lei, m, false);
            m = "mkdir \"/sdcard/Movies\"";
            com.demo.e3.cmd(lei, m, false);
            m = "mkdir \"/sdcard/Music\"";
            com.demo.e3.cmd(lei, m, false);
            m = "mkdir \"/sdcard/Pictures\"";
            com.demo.e3.cmd(lei, m, false);

        }

        if (!wj.cz(set29)) {

            wj.xrwb(set29, "");

        }

        if (!wj.cz(set30)) {

            wj.xrwb(set30, "false");

        }

        if (!wj.cz(set31)) {

            wj.xrwb(set31, "false");

        }

        if (!wj.cz(set32)) {

            wj.xrwb(set32, "false");

        }

        if (!wj.cz(set33)) {

            wj.xrwb(set33, wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a18"));

        }

        if (!wj.cz(set34)) {

            wj.xrwb(set34, "true");

        }

        if (!wj.cz(set35)) {

            wj.xrwb(set35, "https://api.sayqz.com/tunefree/ncmapi");

        }

        if (!wj.cz(set36)) {

            int a11;
            a11 = com.demo.e4.getVersionCode(lei);

            java.lang.String fv = a11 + "00";
            wj.xrwb(set36, fv);

        }

        if (!wj.cz(set37)) {

            wj.xrwb(set37, "false");

        }

        if (!wj.cz(set38)) {

            wj.xrwb(set38, "false");

        }

        if (!wj.cz(set39)) {

            wj.xrwb(set39, "default");

        }

        if (!wj.cz(set40)) {

            wj.xrwb(set40, "20");

        }

        if (!wj.cz(set41)) {

            wj.xrwb(set41, "false");

        }

        if (!wj.cz(set42)) {

            wj.xrwb(set42, "true");

        }

        if (!wj.cz(set43)) {

            wj.xrwb(set43, "false");

        }

        if (!wj.cz(set44)) {

            e11.setup_talklist();
            e11.add_talklist("10001", "官方聊天室", "10001-官方聊天室");
            wj.xrwb(set44, "true");

        }

        if (!wj.cz(set45)) {

            wj.xrwb(set45, "3000");

        }

        if (!wj.cz(set46)) {

            wj.xrwb(set46, "3000");

        }

        if (!wj.cz(set47)) {

            e11.setup_pluginlist();

        }

        if (!wj.cz(set48)) {

            wj.xrwb(set48, "true");

        }

        if (!wj.cz(set49)) {

            wj.xrwb(set49, "awg://web.url/app/open.cn.awg.pro/plugin");

        }

        if (!wj.cz(set50)) {

            wj.xrwb(set50, "default");

        }

        if (!wj.cz(set51)) {

            wj.xrwb(set51, "default");

        }

        if (!wj.cz(set52)) {

            wj.xrwb(set52, "true");

        }

        if (!wj.cz(set53)) {

            wj.xrwb(set53, "default");

        }

        if (!wj.cz(set54)) {

            wj.xrwb(set54, "default");

        }

        if (!wj.cz(set55)) {

            wj.xrwb(set55, "default");

        }

        if (!wj.cz(set56)) {

            e11.setup_pluginlist();

            java.lang.String q = "10000";

            java.lang.String w = "扩展推荐";

            boolean b3 = e11.add_pluginlist(q, w, q + "-" + w);
            wj.xrwb(set56, "true");

        }

        if (!wj.cz(set57)) {

            wj.xrwb(set57, "https://service.typheye.cn");

        }

        if (!wj.cz(set58)) {

            wj.xrwb(set58, "");

        }

        if (!wj.cz(set59)) {

            wj.xrwb(set59, "awg://web.url/upload/up.php");

        }

        if (!wj.cz(set60)) {

            wj.xrwb(set60, "");

        }

        if (!wj.cz(set61)) {

            wj.xrwb(set61, "true");

        }

        if (!wj.cz(set62)) {

            wj.xrwb(set62, "");

        }

        if (!wj.cz(set64)) {

            wj.xrwb(set64, "true");

        }

        if (!wj.cz(set65)) {

            wj.xrwb(set65, "false");

        }

        if (!wj.cz(set66)) {

            wj.xrwb(set66, "false");

        }

        if (!wj.cz(set67)) {

            wj.xrwb(set67, "https://res.typheye.cn");

        }

        if (!wj.cz(set68)) {

            wj.xrwb(set68, "0");

        }
        wj.xrwb(set5, "false");
        wj.xrwb(set53, wj.dqwb(set16));
        vercode = com.demo.e4.getVersionCode(lei);

        java.lang.String fv = String.valueOf(vercode);
        wj.xrwb(set0, fv);
        wj.sc(set63);

        final java.lang.String sec_updater = "/data/user/0/open.cn.awg.pro/data/sec/updater";

        final java.lang.String sec_isunlock = "/data/user/0/open.cn.awg.pro/data/sec/unlock";

        final java.lang.String sec_license = "/data/user/0/open.cn.awg.pro/data/sec/license";

        if (wj.cz(sec_updater)) {

            wj.sc(sec_updater);

        }
        inj();

    }

    public void run() {
        usual();

    }

    public void usual() {

        final java.lang.String a0 = "/data/user/0/open.cn.awg.pro/settings/a4.inf";

        final java.lang.String a1 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        final java.lang.String set66 = "/data/user/0/open.cn.awg.pro/settings/set6.inf";

        if (!wj.cz(a0) || zf.dy(wj.dqwb(set66), "false") || !wj.cz(set66)) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    supertip("点击按钮以继续", 2);

                }

            });

        } else {

        }

    }

    public void rjh() {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                if (!e1.isJh()) {

                    st.wb(open.cn.awg.pro.R.id.wb3).kjd(0);

                } else {

                    st.wb(open.cn.awg.pro.R.id.wb3).kjd(8);

                }

            }

        });

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);

        if (e1.getNowUserId() == 0) {

            final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

            java.lang.String a = wj.dqwb(a2);

            if (zf.dy(a, "4")) {

                if (loaded) {

                    java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
                    xt.pm().ztl(clr, 0);

                } else {

                    java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTabBackground);
                    xt.pm().ztl(clr, 0);

                }

            } else {

                Window window = lei.getWindow();
                BangScreenTools.getBangScreenTools().fullscreen(window, lei);
                BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

            }

            final java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/f21/set1.inf";

            if (wj.cz("/data/user/0/open.cn.awg.pro/settings/a4.inf")) {

                if (!isNotMainOnce) {

                    homeshow(false);

                }
                noticeShow();

            }

            if (isHasReq) {

                final java.lang.String ca = "/data/user/0/open.cn.awg.pro/cache/req/exit";

                if (wj.cz(ca) && zf.dy(wj.dqwb(ca), "true")) {

                    wj.sc(ca);
                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            i.runlibrary.app.v.tx tx3 = st.tx(open.cn.awg.pro.R.id.tx3);
                            tx3.v.performClick();

                        }

                    });

                }

            }

        }

    }

    public void onRestart() {
        super.onRestart();

        if (e1.getNowUserId() == 0) {

            final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

            java.lang.String a = wj.dqwb(a2);

            if (zf.dy(a, "4")) {

                if (loaded) {

                    java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
                    xt.pm().ztl(clr, 0);

                } else {

                    java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTabBackground);
                    xt.pm().ztl(clr, 0);

                }

            } else {

                Window window = lei.getWindow();
                BangScreenTools.getBangScreenTools().fullscreen(window, lei);
                BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

            }

        }

    }

    public void onStart() {
        super.onStart();

        if (e1.getNowUserId() == 0) {

            lei.overridePendingTransition(open.cn.awg.pro.R.anim.fade_in, open.cn.awg.pro.R.anim.fade_out);

            final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

            java.lang.String a = wj.dqwb(a2);

            if (zf.dy(a, "4")) {

                if (loaded) {

                    java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
                    xt.pm().ztl(clr, 0);

                } else {

                    java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTabBackground);
                    xt.pm().ztl(clr, 0);

                }

            } else {

                Window window = lei.getWindow();
                BangScreenTools.getBangScreenTools().fullscreen(window, lei);
                BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

            }

        }

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

        qtgd1.v.requestFocus();

    }

    public void onResume() {
        super.onResume();

        if (e1.getNowUserId() == 0) {

            final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

            java.lang.String a = wj.dqwb(a2);

            if (zf.dy(a, "4")) {

                if (loaded) {

                    java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
                    xt.pm().ztl(clr, 0);

                } else {

                    java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTabBackground);
                    xt.pm().ztl(clr, 0);

                }

            } else {

                Window window = lei.getWindow();
                BangScreenTools.getBangScreenTools().fullscreen(window, lei);
                BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

            }

        }

    }

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.app.AwgProApplication.getInstance().removeActivity(lei);

        if (e1.getNowUserId() == 0) {

            e13.N_cancel();
            wj.sc("/data/user/0/open.cn.awg.pro/data/AwgCoreService.r");

        }

    }

    private void $_onClick_d26d5d7080(android.view.View vw) {

        if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), zf.zf(open.cn.awg.pro.R.string.app_name))) {

            show(1);

        } else if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), " 腕管Pro ")) {

            show(0);

        }

    }

    private void $_onClick_bd584e50cd(android.view.View vw) {
        gj.tz(AnnouncementActivity.class);

    }

    private void $_onClick_b8bc22741b(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.xdbj(open.cn.awg.pro.R.id.xdbj7).kjd(8);

            }

        });

    }

    private void $_onClick_i42afe4289f(android.view.View vw) {
        gj.tz(AppActivationActivity.class);

    }

    private void $_onClick_i7c3e3c6b5d(android.view.View vw) {
        homeshow(true);

    }

    private void $_onClick_fab146d7a5(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                java.lang.String[] iyu = new java.lang.String[]{"uri"};

                java.lang.String[] val = new java.lang.String[]{div_lbl1_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean $_onLongClick_fab146d7a5(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb29).zf();
        e1.tsk("详细信息", te);
        return true;

    }

    private void $_onClick_i9d626a4248(android.view.View vw) {
        e1.tsk("提示", "激活腕管Pro后可关闭所有广告");

    }

    private void $_onClick_bab1d5f82e(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                java.lang.String[] iyu = new java.lang.String[]{"uri"};

                java.lang.String[] val = new java.lang.String[]{div_ad1_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean $_onLongClick_bab1d5f82e(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb52).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb54).zf();
        e1.tsk("详细信息", "[推荐名称]\n" + te + "\n\n[推荐简介]\n" + lj);
        return true;

    }

    private void $_onClick_i5a93676674(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                java.lang.String[] iyu = new java.lang.String[]{"uri"};

                java.lang.String[] val = new java.lang.String[]{div_yytj_item1_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean $_onLongClick_i5a93676674(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb14).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb16).zf();
        e1.tsk("详细信息", "[应用名称]\n" + te + "\n\n[应用简介]\n" + lj);
        return true;

    }

    private void $_onClick_f73b53def9(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                java.lang.String[] iyu = new java.lang.String[]{"uri"};

                java.lang.String[] val = new java.lang.String[]{div_yytj_item2_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean $_onLongClick_f73b53def9(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb23).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb25).zf();
        e1.tsk("详细信息", "[应用名称]\n" + te + "\n\n[应用简介]\n" + lj);
        return true;

    }

    private void $_onClick_i62f27ff563(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                java.lang.String[] iyu = new java.lang.String[]{"uri"};

                java.lang.String[] val = new java.lang.String[]{div_yytj_item3_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean $_onLongClick_i62f27ff563(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb26).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb28).zf();
        e1.tsk("详细信息", "[应用名称]\n" + te + "\n\n[应用简介]\n" + lj);
        return true;

    }

    private void $_onClick_i81652f77b6(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                java.lang.String[] iyu = new java.lang.String[]{"type", "search"};

                java.lang.String[] val = new java.lang.String[]{"app", "all"};
                gj.tz(StoreListActivity.class, iyu, val);

            }

        });

    }

    private void $_onClick_c4cbe9d7ff(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                java.lang.String[] iyu = new java.lang.String[]{"uri"};

                java.lang.String[] val = new java.lang.String[]{div_jcsp_item1_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean $_onLongClick_c4cbe9d7ff(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb31).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb33).zf();
        e1.tsk("详细信息", "[视频名称]\n" + te + "\n\n[视频简介]\n" + lj);
        return true;

    }

    private void $_onClick_i6834408ef9(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                java.lang.String[] iyu = new java.lang.String[]{"uri"};

                java.lang.String[] val = new java.lang.String[]{div_jcsp_item2_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean $_onLongClick_i6834408ef9(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb34).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb36).zf();
        e1.tsk("详细信息", "[视频名称]\n" + te + "\n\n[视频简介]\n" + lj);
        return true;

    }

    private void $_onClick_c9f5a3ee67(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                java.lang.String[] iyu = new java.lang.String[]{"uri"};

                java.lang.String[] val = new java.lang.String[]{div_jcsp_item3_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean $_onLongClick_c9f5a3ee67(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb37).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb39).zf();
        e1.tsk("详细信息", "[视频名称]\n" + te + "\n\n[视频简介]\n" + lj);
        return true;

    }

    private void $_onClick_i3d78c07838(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                java.lang.String[] iyu = new java.lang.String[]{"type", "search"};

                java.lang.String[] val = new java.lang.String[]{"video", "all"};
                gj.tz(StoreListActivity.class, iyu, val);

            }

        });

    }

    private void $_onClick_i8890b048c8(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                java.lang.String[] iyu = new java.lang.String[]{"uri"};

                java.lang.String[] val = new java.lang.String[]{div_rmyy_item1_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean $_onLongClick_i8890b048c8(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb42).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb44).zf();
        e1.tsk("详细信息", "[歌曲名称]\n" + te + "\n\n[歌曲作者]\n" + lj);
        return true;

    }

    private void $_onClick_i11185eacd9(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                java.lang.String[] iyu = new java.lang.String[]{"uri"};

                java.lang.String[] val = new java.lang.String[]{div_rmyy_item2_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean $_onLongClick_i11185eacd9(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb45).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb47).zf();
        e1.tsk("详细信息", "[歌曲名称]\n" + te + "\n\n[歌曲作者]\n" + lj);
        return true;

    }

    private void $_onClick_a229195608(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                java.lang.String[] iyu = new java.lang.String[]{"uri"};

                java.lang.String[] val = new java.lang.String[]{div_rmyy_item3_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean $_onLongClick_a229195608(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb48).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb50).zf();
        e1.tsk("详细信息", "[歌曲名称]\n" + te + "\n\n[歌曲作者]\n" + lj);
        return true;

    }

    private void $_onClick_i6a848b922c(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                java.lang.String[] iyu = new java.lang.String[]{"type", "search"};

                java.lang.String[] val = new java.lang.String[]{"music", "all"};
                gj.tz(StoreListActivity.class, iyu, val);

            }

        });

    }

    private void $_onClick_i34dc7bf20a(android.view.View vw) {
        gj.tz(StoreSearchActivity.class);

    }

    private void $_onClick_adf370a235(android.view.View vw) {
        gj.tz(AppActivationActivity.class);

    }

    private boolean $_onLongClick_i4ba8980add(android.view.View vw) {

        java.lang.String set65 = "/data/user/0/open.cn.awg.pro/settings/set5.inf";
        wj.xrwb(set65, "true");
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.wb(open.cn.awg.pro.R.id.wb9).kjd(8);

            }

        });
        return false;

    }

    private void $_onClick_i7a998f9ecb(android.view.View vw) {
        homeshow_show(1);
        show(0);

    }

    private void $_onClick_a81b730195(android.view.View vw) {
        supertip_an();

    }

    private boolean $_onTouch_i5b34eede7f(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private void $_onClick_i3a177cf5f1(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                i.runlibrary.app.v.tx tx3 = st.tx(open.cn.awg.pro.R.id.tx3);
                tx3.v.performClick();

            }

        });

    }

    private void $_onClick_i2318575919(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.wb(open.cn.awg.pro.R.id.wb17).kjd(8);

            }

        });

    }

    private boolean $_onTouch_i3167b45174(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private boolean $_onTouch_b0f5bad894(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private void $_onClick_i4d951cdb0e(android.view.View vw) {

        i.runlibrary.app.gj$dh dh = gj.dh();

        final i.runlibrary.app.gj$Animationx jbtm = dh.jbtm(1, 0);
        jbtm.cxsc(300);
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.xdbj(open.cn.awg.pro.R.id.TCxdbj).kjd(8);
                st.xdbj(open.cn.awg.pro.R.id.TCxdbj).dh(jbtm);

            }

        });

    }

    private void $_onClick_i8217d42a17(android.view.View vw) {
        gj.xc(new java.lang.Thread() {

            public void run() {

                final i.runlibrary.app.v.xdbj main_v = st.xdbj(open.cn.awg.pro.R.id.main_v);
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        main_v.scqb();

                    }

                });
                gj.zt(500);
                wj.sc("/data/user/0/open.cn.awg.pro/data/AwgCoreService.r");

                try {

                    e13.N_cancel();

                } catch (java.lang.Throwable __$_e__) {

                }
                open.cn.awg.pro.app.AwgProApplication.getInstance().exit();

            }

        });

    }

    private void $_onClick_i6a8b9facdf(android.view.View vw) {
        show(0);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
        d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

        android.widget.TextView e943ebd71e = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb4);

        android.widget.TextView i2a86c78b06 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb5);

        android.widget.TextView i78e3e43012 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.notice_wb);

        android.widget.LinearLayout bd584e50cd = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj5);
        bd584e50cd.setOnClickListener($_on_setOnClickListener_bd584e50cd);

        android.widget.TextView b8bc22741b = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb7);
        b8bc22741b.setOnClickListener($_on_setOnClickListener_b8bc22741b);

        android.widget.TextView i42afe4289f = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb11);
        i42afe4289f.setOnClickListener($_on_setOnClickListener_i42afe4289f);

        android.widget.TextView i7c3e3c6b5d = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb2);
        i7c3e3c6b5d.setOnClickListener($_on_setOnClickListener_i7c3e3c6b5d);

        android.widget.RelativeLayout fab146d7a5 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj8);
        fab146d7a5.setOnClickListener($_on_setOnClickListener_fab146d7a5);
        fab146d7a5.setOnLongClickListener($_on_setOnLongClickListener_fab146d7a5);

        android.widget.TextView i9d626a4248 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb56);
        i9d626a4248.setOnClickListener($_on_setOnClickListener_i9d626a4248);

        android.widget.RelativeLayout bab1d5f82e = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj20);
        bab1d5f82e.setOnClickListener($_on_setOnClickListener_bab1d5f82e);
        bab1d5f82e.setOnLongClickListener($_on_setOnLongClickListener_bab1d5f82e);

        android.widget.RelativeLayout i5a93676674 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj9);
        i5a93676674.setOnClickListener($_on_setOnClickListener_i5a93676674);
        i5a93676674.setOnLongClickListener($_on_setOnLongClickListener_i5a93676674);

        android.widget.RelativeLayout f73b53def9 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj12);
        f73b53def9.setOnClickListener($_on_setOnClickListener_f73b53def9);
        f73b53def9.setOnLongClickListener($_on_setOnLongClickListener_f73b53def9);

        android.widget.RelativeLayout i62f27ff563 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj13);
        i62f27ff563.setOnClickListener($_on_setOnClickListener_i62f27ff563);
        i62f27ff563.setOnLongClickListener($_on_setOnLongClickListener_i62f27ff563);

        android.widget.TextView i81652f77b6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb10);
        i81652f77b6.setOnClickListener($_on_setOnClickListener_i81652f77b6);

        android.widget.RelativeLayout c4cbe9d7ff = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj14);
        c4cbe9d7ff.setOnClickListener($_on_setOnClickListener_c4cbe9d7ff);
        c4cbe9d7ff.setOnLongClickListener($_on_setOnLongClickListener_c4cbe9d7ff);

        android.widget.RelativeLayout i6834408ef9 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj15);
        i6834408ef9.setOnClickListener($_on_setOnClickListener_i6834408ef9);
        i6834408ef9.setOnLongClickListener($_on_setOnLongClickListener_i6834408ef9);

        android.widget.RelativeLayout c9f5a3ee67 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj16);
        c9f5a3ee67.setOnClickListener($_on_setOnClickListener_c9f5a3ee67);
        c9f5a3ee67.setOnLongClickListener($_on_setOnLongClickListener_c9f5a3ee67);

        android.widget.TextView i3d78c07838 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb40);
        i3d78c07838.setOnClickListener($_on_setOnClickListener_i3d78c07838);

        android.widget.RelativeLayout i8890b048c8 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj17);
        i8890b048c8.setOnClickListener($_on_setOnClickListener_i8890b048c8);
        i8890b048c8.setOnLongClickListener($_on_setOnLongClickListener_i8890b048c8);

        android.widget.RelativeLayout i11185eacd9 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj18);
        i11185eacd9.setOnClickListener($_on_setOnClickListener_i11185eacd9);
        i11185eacd9.setOnLongClickListener($_on_setOnLongClickListener_i11185eacd9);

        android.widget.RelativeLayout a229195608 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj19);
        a229195608.setOnClickListener($_on_setOnClickListener_a229195608);
        a229195608.setOnLongClickListener($_on_setOnLongClickListener_a229195608);

        android.widget.TextView i6a848b922c = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb51);
        i6a848b922c.setOnClickListener($_on_setOnClickListener_i6a848b922c);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        android.widget.RelativeLayout c0d3e00d2b = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj5);

        android.widget.RelativeLayout aaeebbd136 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj1);

        android.widget.TextView adf370a235 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb13);
        adf370a235.setOnClickListener($_on_setOnClickListener_adf370a235);

        android.widget.TextView i4ba8980add = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb9);
        i4ba8980add.setOnLongClickListener($_on_setOnLongClickListener_i4ba8980add);

        android.widget.TextView f52e07ac8c = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb6);

        android.widget.ImageView i7a998f9ecb = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx15);
        i7a998f9ecb.setOnClickListener($_on_setOnClickListener_i7a998f9ecb);

        android.widget.RelativeLayout i171cedf1eb = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj6);

        android.widget.ImageView a81b730195 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx16);
        a81b730195.setOnClickListener($_on_setOnClickListener_a81b730195);

        android.support.v7.widget.CardView i358e59a100 = (android.support.v7.widget.CardView) findViewById(ay, vw, open.cn.awg.pro.R.id.kp5);

        android.widget.RelativeLayout i5b34eede7f = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj10);
        i5b34eede7f.setOnTouchListener($_on_setOnTouchListener_i5b34eede7f);

        android.widget.TextView i3a177cf5f1 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb12);
        i3a177cf5f1.setOnClickListener($_on_setOnClickListener_i3a177cf5f1);

        android.widget.TextView i2318575919 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb17);
        i2318575919.setOnClickListener($_on_setOnClickListener_i2318575919);

        android.widget.RelativeLayout i3167b45174 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj2);
        i3167b45174.setOnTouchListener($_on_setOnTouchListener_i3167b45174);

        android.widget.RelativeLayout b0f5bad894 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.TCxdbj);
        b0f5bad894.setOnTouchListener($_on_setOnTouchListener_b0f5bad894);

        android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
        i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

        android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);
        i8217d42a17.setOnClickListener($_on_setOnClickListener_i8217d42a17);

        android.widget.TextView i6a8b9facdf = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb_main_kjfs_gbcd);
        i6a8b9facdf.setOnClickListener($_on_setOnClickListener_i6a8b9facdf);

        __layoutIsLoaded(ay, vw);
    }
}
