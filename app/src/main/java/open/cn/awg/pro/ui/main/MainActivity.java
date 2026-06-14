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



import android.annotation.SuppressLint;
import open.cn.awg.pro.core.IappCompat;
import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.PushAgent;

import open.cn.awg.pro.account.AppActivationActivity;
import open.cn.awg.pro.account.ThirdPartyAuthorizationActivity;
import open.cn.awg.pro.account.TypheyeServiceBridge;
import open.cn.awg.pro.announcement.AnnouncementActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.browser.X5BrowserActivity;
import open.cn.awg.pro.chat.WearChatActivity;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.core.CoreRuntimeBootstrap;
import open.cn.awg.pro.core.DebugStateStore;
import open.cn.awg.pro.data.LocalDatabaseBridge;
import open.cn.awg.pro.file.FileRootPickerActivity;
import open.cn.awg.pro.plugin.ExtensionPluginActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.service.AwgCoreService;
import open.cn.awg.pro.service.BootJobService;
import open.cn.awg.pro.store.StoreDetailActivity;
import open.cn.awg.pro.store.StoreListActivity;
import open.cn.awg.pro.store.StoreSearchActivity;
import open.cn.awg.pro.ui.list.LegacyMenuItemActivity;
import open.cn.awg.pro.ui.list.MainMenuItemActivity;
import open.cn.awg.pro.update.UpdatePromptActivity;

@SuppressLint("StaticFieldLeak")
public class MainActivity extends BaseAwgActivity {

    public static Context context;
    public final MainActivity lei = this, 类 = this;
    public i.runlibrary.app.v.v7lb$UserAdapter spq = null;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public LocalDatabaseBridge e11 = new LocalDatabaseBridge(_APPINFO);
    public CoreRuntimeBootstrap e13 = new CoreRuntimeBootstrap(_APPINFO);
    public DebugStateStore debug = new DebugStateStore(_APPINFO);
    public int vercode = 0;
    public boolean isNotMainOnce = true;
    public boolean isShow = true;
    public boolean loaded = false;
    public boolean state_homeshow = false;
    public boolean state_once_homeshow = false;
    public boolean isHasReq = false;
    public String div_lbl1_url = "";
    public String div_ad1_url = "";
    public String div_yytj_item1_url = "";
    public String div_yytj_item2_url = "";
    public String div_yytj_item3_url = "";
    public String div_jcsp_item1_url = "";
    public String div_jcsp_item2_url = "";
    public String div_jcsp_item3_url = "";
    public String div_rmyy_item1_url = "";
    public String div_rmyy_item2_url = "";
    public String div_rmyy_item3_url = "";
    public String src_url = "";
    public int supertip_mode = 0;
    public String android_id;
    public TypheyeServiceBridge easy = new TypheyeServiceBridge(_APPINFO);
    private final View.OnClickListener wbMainKjfsGbcdClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWbMainKjfsGbcdClick(vw);
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
    private final View.OnTouchListener tCxdbjTouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onTCxdbjTouch(vw, me);
        }

    };
    private final View.OnTouchListener xdbj2TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXdbj2Touch(vw, me);
        }

    };
    private final View.OnClickListener wb17ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb17Click(vw);
        }

    };
    private final View.OnClickListener wb12ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb12Click(vw);
        }

    };
    private final View.OnTouchListener xdbj10TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXdbj10Touch(vw, me);
        }

    };
    private final View.OnClickListener tx16ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx16Click(vw);
        }

    };
    private final View.OnClickListener tx15ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx15Click(vw);
        }

    };
    private final View.OnLongClickListener wb9LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onWb9LongClick(vw);
        }

    };
    private final View.OnClickListener wb13ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb13Click(vw);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnClickListener wb51ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb51Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj19LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj19LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj19ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj19Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj18LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj18LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj18ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj18Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj17LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj17LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj17ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj17Click(vw);
        }

    };
    private final View.OnClickListener wb40ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb40Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj16LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj16LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj16ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj16Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj15LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj15LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj15ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj15Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj14LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj14LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj14ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj14Click(vw);
        }

    };
    private final View.OnClickListener wb10ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb10Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj13LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj13LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj13ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj13Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj12LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj12LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj12ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj12Click(vw);
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
    private final View.OnLongClickListener xdbj20LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj20LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj20ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj20Click(vw);
        }

    };
    private final View.OnClickListener wb56ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb56Click(vw);
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
    private final View.OnClickListener wb2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb2Click(vw);
        }

    };
    private final View.OnClickListener wb11ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb11Click(vw);
        }

    };
    private final View.OnClickListener wb7ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb7Click(vw);
        }

    };
    private final View.OnClickListener xxbj5ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj5Click(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.main_activity);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        if (e1.getNowUserId() == 0) {

            final String a0 = AppPaths.appPath("settings/a4.inf");

            final String a1 = AppPaths.appPath("settings/a3.inf");

            final String a2 = AppPaths.appPath("settings/a3.inf");

            if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
                finish();
                return;
            }

            gj.xc(new Thread() {

                public void run() {

                    csh0();
                    gj.zt(500);
                    gj.jmxc(new Runnable() {

                        public void run() {

                            e13.downAssets();

                            String set0 = AppPaths.appPath("settings/version");
                            vercode = com.demo.e4.getVersionCode(lei);

                            String bb = String.valueOf(vercode);

                            if (!zf.dy(wj.dqwb(set0), bb) || !wj.cz(set0)) {

                                set();

                            } else {

                                inj();

                            }

                            if (isShow && wj.cz(AppPaths.appPath("settings/a4.inf"))) {

                                i.runlibrary.app.gj$dh dh = (i.runlibrary.app.gj$dh) (Object) gj.dh();

                                i.runlibrary.app.gj$Animationx jbtm = (i.runlibrary.app.gj$Animationx) (Object) dh.jbtm(1, 0);
                                jbtm.cxsc(500);
                                st.xdbj(R.id.xdbj2).kjd(8);
                                st.xdbj(R.id.xdbj2).dh(jbtm);
                                loaded = true;

                                final String a2 = AppPaths.appPath("settings/a3.inf");

                                String a = wj.dqwb(a2);

                                if (zf.dy(a, "4")) {

                                    if (loaded) {

                                        String clr = zf.zf(R.color.colorTab);
                                        setStatusBarColor(clr);

                                    } else {

                                        String clr = zf.zf(R.color.colorTabBackground);
                                        setStatusBarColor(clr);

                                    }

                                }

                                String set67 = AppPaths.appPath("settings/set7.inf");
                                src_url = wj.dqwb(set67);
                                zyqd();

                                if (!debug.isShowed()) {

                                    e1.goplu("20005", "后台诊断", false);

                                }

                                if (wj.cz(AppPaths.appPath("settings/a4.inf"))) {

                                    if (e1.islogin()) {

                                        n3_jz();

                                    }

                                    String set11 = AppPaths.appPath("settings/f9/set1.inf");

                                    if (zf.dy(wj.dqwb(set11), "true")) {

                                        int a11;

                                        String vn;
                                        a11 = com.demo.e4.getVersionCode(lei);
                                        vn = com.demo.e4.getVersionName(lei);
                                        e1.update(a11);

                                        if (e1.islogin()) {

                                        }

                                    }
                                    wj.sc(AppPaths.appPath("data/ltlb/showing"));

                                    try {

                                        UMConfigure.init(lei, "6687f537940d5a4c4983309f", "awg_um", UMConfigure.DEVICE_TYPE_PHONE, "673a08d091d30693eb8353adf5c1e554");
                                        PushAgent.getInstance(lei).onAppStart();

                                        PushAgent api = PushAgent.getInstance(lei);

                                    } catch (Throwable __$_e__) {

                                    }

                                }

                            }
                            e13.run();

                        }

                    });

                }

            });

        } else {

            AwgProApplication.getInstance().exit();

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        if (e1.getNowUserId() == 0) {

            i.runlibrary.app.xt$pm pm = (i.runlibrary.app.xt$pm) (Object) xt.pm();

            if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4s.inf")), "false")) {

                if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4.inf")), "true")) {

                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                } else {

                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                }

            }

            final String a2 = AppPaths.appPath("settings/a3.inf");

            String a = "";

            if (wj.cz(a2)) {

                a = wj.dqwb(a2);

            }

            if (zf.dy(a, "4")) {

                st.xdbj(R.id.main_activity_root).shxtck(true);

                if (loaded) {

                    String clr = zf.zf(R.color.colorTab);
                    setStatusBarColor(clr);

                } else {

                    String clr = zf.zf(R.color.colorTabBackground);
                    setStatusBarColor(clr);

                }

            } else {

                st.xdbj(R.id.main_activity_root).shxtck(false);
        applyFullscreenWindow();

            }

            int uu = 0;

            if (wj.cz(AppPaths.appPath("settings/f17/set1.inf"))) {

                uu = Integer.parseInt(wj.dqwb(AppPaths.appPath("settings/f17/set1.inf")));

            }

            final int u = uu;

            if (zf.dy(a, "1")) {

                st.xdbj(R.id.xdbj4).wbj(u, 10, u, 10);

                i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
                wtab.nbj(0, "15dp", 0, "3dp");
                wtab.dqfs("center");

                i.runlibrary.app.v.wb wtab2 = st.wb(R.id.Title2);
                wtab2.nbj(0, "15dp", 0, "3dp");
                wtab2.dqfs("center");

                i.runlibrary.app.v.v7lb lb = st.v7lb(R.id.v7lb1);
                spq = IappCompat.v7lbAdapter(lb, MainMenuItemActivity.class, R.layout.main_menu_item, new IappCompat.V7lbViewBinder() {

                    public void bind(Object ua, int pn, View vw) {

                        i.runlibrary.app.sj$lb lbsj = (i.runlibrary.app.sj$lb) (Object) sj.lb(vw);

                        int f = IappCompat.zh(sj.zh(lbsj.lbsj(pn, -1))).zint();

                        if (f == 0) {

                            i.runlibrary.app.v.xxbj xxbj = st.xxbj(vw, R.id.xxbj2);
                            xxbj.dqfs("center");
                            xxbj.kjd(0);
                            xxbj.xydj(true);
                            xxbj.xyca(true);

                        } else if (f == 1) {

                            i.runlibrary.app.v.xxbj xxbj = st.xxbj(vw, R.id.xxbj2);
                            xxbj.dqfs("center_vertical|left");
                            xxbj.kjd(0);
                            xxbj.xydj(true);
                            xxbj.xyca(true);

                        } else if (f == -1) {

                            i.runlibrary.app.v.xxbj xxbj = st.xxbj(vw, R.id.xxbj2);
                            xxbj.dqfs("center_vertical|right");
                            xxbj.kjd(0);
                            xxbj.xydj(true);
                            xxbj.xyca(true);

                        } else if (f == -2) {

                            i.runlibrary.app.v.xxbj xxbj = st.xxbj(vw, R.id.xxbj2);
                            xxbj.dqfs("center");
                            xxbj.kjd(8);
                            xxbj.xydj(false);
                            xxbj.xyca(false);

                        } else {

                            i.runlibrary.app.v.xxbj xxbj = st.xxbj(vw, R.id.xxbj2);
                            xxbj.dqfs("center");
                            xxbj.kjd(0);
                            xxbj.xydj(true);
                            xxbj.xyca(true);

                        }

                    }

                });

                int[] kj = new int[]{R.id.tx1, R.id.wb1, R.id.wb2, R.id.wb3, -1};

                Object[] jk = new Object[]{R.mipmap.a39, "系统工具", "10", "", -1};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a29, "关闭菜单", "31", "", -1};
                jk = new Object[]{R.mipmap.a20, "文件管理", "1", "", 0};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a37, "腕上微聊", "9", "", 1};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a40, "X5浏览器", "11", "", -1};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a48, "扩展插件", "20", "", 0};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a43, "更多功能", "13", "", 1};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a10, "应用设置", "8", "", -1};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a46, "返回桌面", "14", "", 0};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a23, "退出应用", "3", "", 1};
                spq.j(kj, jk);
                jk = new Object[]{"", "", "", "", -2};
                spq.j(kj, jk);
                spq.j(kj, jk);
                spq.j(kj, jk);

                i.runlibrary.app.v.v7lb lbx = st.v7lb(R.id.v7lb1);

                GridLayoutManager mLayoutManager = new GridLayoutManager(lei, 3);
                lbx.st.setLayoutManager(mLayoutManager);
                spq.sx();

            } else if (zf.dy(a, "2")) {

                String a001 = AppPaths.appPath("settings/dpi.inf");

                if (wj.cz(a001) || zf.dy(wj.dqwb(a001), "true")) {

                    i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
                    wtab.nbj(0, "3dp", 0, "3dp");
                    wtab.dqfs("center");

                    i.runlibrary.app.v.wb wtab2 = st.wb(R.id.Title2);
                    wtab2.nbj(0, "3dp", 0, "3dp");
                    wtab2.dqfs("center");

                } else {

                    i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
                    wtab.nbj(0, "9dp", 0, "9dp");
                    wtab.dqfs("center");

                    i.runlibrary.app.v.wb wtab2 = st.wb(R.id.Title2);
                    wtab2.nbj(0, "9dp", 0, "9dp");
                    wtab2.dqfs("center");

                }
                st.xdbj(R.id.xdbj4).wbj(10, 10, 10, 10);

                i.runlibrary.app.v.v7lb lb = st.v7lb(R.id.v7lb1);
                spq = IappCompat.v7lbAdapter(lb, MainMenuItemActivity.class, R.layout.main_menu_item);

                int[] kj = new int[]{R.id.tx1, R.id.wb1, R.id.wb2, R.id.wb3};

                Object[] jk = new Object[]{R.mipmap.a39, "系统工具", "10", ""};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a29, "关闭菜单", "31", ""};
                jk = new Object[]{R.mipmap.a20, "文件管理", "1", ""};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a37, "腕上微聊", "9", ""};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a40, "X5浏览器", "11", ""};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a48, "扩展插件", "20", ""};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a43, "更多功能", "13", ""};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a10, "应用设置", "8", ""};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a46, "返回桌面", "14", ""};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a23, "退出应用", "3", ""};
                spq.j(kj, jk);

                i.runlibrary.app.v.v7lb lbx = st.v7lb(R.id.v7lb1);

                if (wj.cz(a001) || zf.dy(wj.dqwb(a001), "true")) {

                    GridLayoutManager mLayoutManager = new GridLayoutManager(lei, 2);
                    lbx.st.setLayoutManager(mLayoutManager);

                } else {

                    GridLayoutManager mLayoutManager = new GridLayoutManager(lei, 3);
                    lbx.st.setLayoutManager(mLayoutManager);

                }
                spq.sx();

            } else if (zf.dy(a, "3")) {

                st.xdbj(R.id.xdbj4).wbj(u, 10, u, 10);

                i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
                wtab.nbj(0, "15dp", 0, "3dp");
                wtab.dqfs("center");

                i.runlibrary.app.v.wb wtab2 = st.wb(R.id.Title2);
                wtab2.nbj(0, "15dp", 0, "3dp");
                wtab2.dqfs("center");

                i.runlibrary.app.v.v7lb lb = st.v7lb(R.id.v7lb1);
                spq = IappCompat.v7lbAdapter(lb, MainMenuItemActivity.class, R.layout.main_menu_item, new IappCompat.V7lbViewBinder() {

                    public void bind(Object ua, int pn, View vw) {

                        i.runlibrary.app.sj$lb lbsj = (i.runlibrary.app.sj$lb) (Object) sj.lb(vw);

                        int f = IappCompat.zh(sj.zh(lbsj.lbsj(pn, -1))).zint();

                        if (f == 0) {

                            i.runlibrary.app.v.xxbj xxbj = st.xxbj(vw, R.id.xxbj2);
                            xxbj.dqfs("center");
                            xxbj.kjd(0);
                            xxbj.xydj(true);
                            xxbj.xyca(true);

                        } else if (f == 1) {

                            i.runlibrary.app.v.xxbj xxbj = st.xxbj(vw, R.id.xxbj2);
                            xxbj.dqfs("center_vertical|left");
                            xxbj.kjd(0);
                            xxbj.xydj(true);
                            xxbj.xyca(true);

                        } else if (f == -1) {

                            i.runlibrary.app.v.xxbj xxbj = st.xxbj(vw, R.id.xxbj2);
                            xxbj.dqfs("center_vertical|right");
                            xxbj.kjd(0);
                            xxbj.xydj(true);
                            xxbj.xyca(true);

                        } else if (f == -2) {

                            i.runlibrary.app.v.xxbj xxbj = st.xxbj(vw, R.id.xxbj2);
                            xxbj.dqfs("center");
                            xxbj.kjd(8);
                            xxbj.xydj(false);
                            xxbj.xyca(false);

                        } else {

                            i.runlibrary.app.v.xxbj xxbj = st.xxbj(vw, R.id.xxbj2);
                            xxbj.dqfs("center");
                            xxbj.kjd(0);
                            xxbj.xydj(true);
                            xxbj.xyca(true);

                        }

                    }

                });

                int[] kj = new int[]{R.id.tx1, R.id.wb1, R.id.wb2, R.id.wb3, -1};

                Object[] jk = new Object[]{R.mipmap.a39, "系统工具", "10", "", -1};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a29, "关闭菜单", "31", "", -1};
                jk = new Object[]{R.mipmap.a20, "文件管理", "1", "", 0};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a37, "腕上微聊", "9", "", 1};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a40, "X5浏览器", "11", "", -1};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a48, "扩展插件", "20", "", 0};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a43, "更多功能", "13", "", 1};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a10, "应用设置", "8", "", -1};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a46, "返回桌面", "14", "", 0};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a23, "退出应用", "3", "", 1};
                spq.j(kj, jk);
                jk = new Object[]{"", "", "", "", -2};
                spq.j(kj, jk);
                spq.j(kj, jk);
                spq.j(kj, jk);

                i.runlibrary.app.v.v7lb lbx = st.v7lb(R.id.v7lb1);

                GridLayoutManager mLayoutManager = new GridLayoutManager(lei, 3);
                lbx.st.setLayoutManager(mLayoutManager);
                spq.sx();

            } else if (zf.dy(a, "4")) {

                st.xdbj(R.id.xdbj4).wbj(10, 10, 10, 10);

                i.runlibrary.app.v.v7lb lb = st.v7lb(R.id.v7lb1);
                spq = IappCompat.v7lbAdapter(lb, LegacyMenuItemActivity.class, R.layout.legacy_menu_item, new IappCompat.V7lbViewBinder() {

                    public void bind(Object ua, int pn, View vw) {

                        i.runlibrary.app.sj$lb lbsj = (i.runlibrary.app.sj$lb) (Object) sj.lb(vw);

                        int f = IappCompat.zh(sj.zh(lbsj.lbsj(pn, -1))).zint();

                        final i.runlibrary.app.v.xdbj xdbj1 = st.xdbj(vw, R.id.xdbj1);

                        final i.runlibrary.app.v.wb wb3 = st.wb(vw, R.id.wb3);

                        if (f == 0) {

                            xdbj1.kjd(0);
                            wb3.kjd(8);

                        } else {

                            xdbj1.kjd(8);
                            wb3.kjd(0);

                        }

                    }

                });

                int[] kj = new int[]{R.id.tx1, R.id.wb1, R.id.wb2, R.id.wb3, -1};

                Object[] jk = new Object[]{R.mipmap.a39, "系统工具", "27", "", 0};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a29, "关闭菜单", "31", "", 0};
                jk = new Object[]{R.mipmap.a20, "文件管理", "6", "", 0};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a37, "腕上微聊", "7", "", 0};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a40, "X5浏览器", "10", "", 0};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a48, "扩展插件", "20", "", 0};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a43, "更多功能", "13", "", 0};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a10, "应用设置", "2", "", 0};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a46, "返回桌面", "14", "", 0};
                spq.j(kj, jk);
                jk = new Object[]{R.mipmap.a23, "退出应用", "12", "", 0};
                spq.j(kj, jk);
                spq.sx();

            }

        }

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4 && st.xdbj(R.id.xdbj10).kjd() == 8 && st.xdbj(R.id.xdbj2).kjd() == 8 && st.xdbj(R.id.TCxdbj).kjd() == 8) {

            if (zf.dy(st.wb(R.id.title_bar).zf(), "腕管Pro")) {

                show(1);

            } else if (zf.dy(st.wb(R.id.title_bar).zf(), " 腕管Pro ")) {

                i.runlibrary.app.gj$dh dh = (i.runlibrary.app.gj$dh) (Object) gj.dh();

                final i.runlibrary.app.gj$Animationx jbtm = (i.runlibrary.app.gj$Animationx) (Object) dh.jbtm(0, 1);
                jbtm.cxsc(300);
                gj.jmxc(new Runnable() {

                    public void run() {

                        st.xdbj(R.id.TCxdbj).kjd(0);
                        st.xdbj(R.id.TCxdbj).dh(jbtm);

                    }

                });

            }

        }
        return false;

    }

    public void csh0() {

        String sec_updater = AppPaths.appPath("data/sec/updater");

        String sec_license = AppPaths.appPath("data/sec/license");
        wj.xrwb(sec_license, "1");
        wj.sc(sec_updater);

    }

    public void n3_jz() {
        gj.xc(new Thread() {

            public void run() {

                android_id = Settings.System.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

                String urls = AppPaths.appPath("settings/f10/set2.inf");
                urls = wj.dqwb(urls);
                urls = e1.urlUnlockString(urls);

                if (zf.cjw(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                String url = urls + "api.php";

                String post_ = "req=openawg_get&token=" + android_id;

                String[] post = zf.fg(post_, "&", false);

                if (e1.yz()) {

                    String st2 = wl.hq(url, post, "utf-8", null, true, null, 20000, 20000, null);

                    if (zf.dy(st2, null)) {

                    } else {

                        try {

                            String code = e1.readJson(st2, "code");

                            if (zf.dy(code, "0")) {

                            } else {

                                isHasReq = true;
                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        gj.tz(ThirdPartyAuthorizationActivity.class);

                                    }

                                });

                            }

                        } catch (Throwable __$_e__) {

                        }

                    }

                } else {

                }

            }

        });

    }

    public void supertip(String t_, int m) {
        supertip_mode = m;

        final String t = t_;
        isShow = false;
        gj.jmxc(new Runnable() {

            public void run() {

                if (supertip_mode == 0) {

                    st.kp(R.id.kp5).kjd(0);
                    st.tx(R.id.tx16).tx(R.mipmap.a17);

                } else if (supertip_mode == 1) {

                    st.kp(R.id.kp5).kjd(0);
                    st.tx(R.id.tx16).tx(R.mipmap.a17);

                } else if (supertip_mode == 2) {

                    st.kp(R.id.kp5).kjd(0);
                    st.tx(R.id.tx16).tx(R.mipmap.a28);

                } else if (supertip_mode == 3) {

                    st.kp(R.id.kp5).kjd(0);
                    st.tx(R.id.tx16).tx(R.mipmap.a62);

                } else if (supertip_mode == 4) {

                    st.kp(R.id.kp5).kjd(0);
                    st.tx(R.id.tx16).tx(R.mipmap.a17);

                } else {

                    st.kp(R.id.kp5).kjd(8);
                    st.tx(R.id.tx16).tx(R.mipmap.a28);

                }

            }

        });
        gj.jmxc(new Runnable() {

            public void run() {

                st.wb(R.id.wb17).zf("");
                st.wb(R.id.wb17).kjd(8);
                st.wb(R.id.wb3).zf(t);
                st.xdbj(R.id.xdbj10).kjd(0);

            }

        });

        i.runlibrary.app.gj$dh dh = (i.runlibrary.app.gj$dh) (Object) gj.dh();

        i.runlibrary.app.gj$Animationx jbtm = (i.runlibrary.app.gj$Animationx) (Object) dh.jbtm(1, 0);
        jbtm.cxsc(500);
        st.xdbj(R.id.xdbj2).kjd(8);
        st.xdbj(R.id.xdbj2).dh(jbtm);

    }

    public void supertip_an() {

        if (supertip_mode == 0) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.wb17).zf("等待联网验证腕管Pro，从V4.0.0版本开始，此过程是必须的\n您可以尝试在设备联网后重启腕管Pro");
                    st.wb(R.id.wb17).kjd(0);

                }

            });

        } else if (supertip_mode == 1) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.wb17).zf("您正在使用非官方发行的腕管Pro，可能存在未知风险\n请前往官方网站（https://awg.typheye.cn）下载安装腕管Pro");
                    st.wb(R.id.wb17).kjd(0);

                }

            });

        } else if (supertip_mode == 2) {

            gj.jmxc(new Runnable() {

                public void run() {

                    gj.tz(OnboardingSetupActivity.class);

                }

            });

        } else if (supertip_mode == 3) {

            gj.jmxc(new Runnable() {

                public void run() {

                    gj.tz(UpdatePromptActivity.class);

                }

            });

        } else if (supertip_mode == 4) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.wb17).zf("您可能并未获得应用许可，该版本不适用于您的设备\n如您已获得应用许可，可以尝试退出应用并联网重新进入应用");
                    st.wb(R.id.wb17).kjd(0);

                }

            });

        }

    }

    public void zyqd() {

        String set1 = AppPaths.appPath("settings/f21/set1.inf");

        if (zf.dy(wj.dqwb(set1), "1")) {

            show(1);

        } else if (zf.dy(wj.dqwb(set1), "2")) {

            show(1);
            gj.jmxc(new Runnable() {

                public void run() {

                    gj.tz(FileRootPickerActivity.class);

                }

            });

        } else if (zf.dy(wj.dqwb(set1), "3")) {

            show(1);
            gj.jmxc(new Runnable() {

                public void run() {

                    gj.tz(WearChatActivity.class);

                }

            });

        } else if (zf.dy(wj.dqwb(set1), "4")) {

            show(1);
            gj.jmxc(new Runnable() {

                public void run() {

                    gj.tz(ExtensionPluginActivity.class);

                }

            });

        } else if (zf.dy(wj.dqwb(set1), "5")) {

            show(1);
            gj.jmxc(new Runnable() {

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

        final i.runlibrary.app.v.xxbj xxbj9 = st.xxbj(R.id.xxbj9);

        final i.runlibrary.app.v.wb wb2 = st.wb(R.id.wb2);
        gj.jmxc(new Runnable() {

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

            final i.runlibrary.app.v.xdbj xdbj20 = st.xdbj(R.id.xdbj20);

            final i.runlibrary.app.v.tx tx11 = st.tx(R.id.tx11);

            final i.runlibrary.app.v.wb wb29 = st.wb(R.id.wb29);

            final i.runlibrary.app.v.tx tx18 = st.tx(R.id.tx18);

            final i.runlibrary.app.v.wb wb52 = st.wb(R.id.wb52);

            final i.runlibrary.app.v.wb wb54 = st.wb(R.id.wb54);

            final i.runlibrary.app.v.tx tx6 = st.tx(R.id.tx6);

            final i.runlibrary.app.v.wb wb14 = st.wb(R.id.wb14);

            final i.runlibrary.app.v.wb wb16 = st.wb(R.id.wb16);

            final i.runlibrary.app.v.tx tx9 = st.tx(R.id.tx9);

            final i.runlibrary.app.v.wb wb23 = st.wb(R.id.wb23);

            final i.runlibrary.app.v.wb wb25 = st.wb(R.id.wb25);

            final i.runlibrary.app.v.tx tx10 = st.tx(R.id.tx10);

            final i.runlibrary.app.v.wb wb26 = st.wb(R.id.wb26);

            final i.runlibrary.app.v.wb wb28 = st.wb(R.id.wb28);

            final i.runlibrary.app.v.wb wb31 = st.wb(R.id.wb31);

            final i.runlibrary.app.v.wb wb33 = st.wb(R.id.wb33);

            final i.runlibrary.app.v.wb wb34 = st.wb(R.id.wb34);

            final i.runlibrary.app.v.wb wb36 = st.wb(R.id.wb36);

            final i.runlibrary.app.v.wb wb37 = st.wb(R.id.wb37);

            final i.runlibrary.app.v.wb wb39 = st.wb(R.id.wb39);

            final i.runlibrary.app.v.wb wb42 = st.wb(R.id.wb42);

            final i.runlibrary.app.v.wb wb44 = st.wb(R.id.wb44);

            final i.runlibrary.app.v.wb wb45 = st.wb(R.id.wb45);

            final i.runlibrary.app.v.wb wb47 = st.wb(R.id.wb47);

            final i.runlibrary.app.v.wb wb48 = st.wb(R.id.wb48);

            final i.runlibrary.app.v.wb wb50 = st.wb(R.id.wb50);
            gj.xc(new Thread() {

                public void run() {

                    if (t) {

                        show(2);

                    }
                    gj.zt(500);

                    boolean canshow = false;

                    final String nopa2 = AppPaths.appPath("data/homeshow/json");

                    final String img_lbl1_path = AppPaths.appPath("data/homeshow/src/img/lbl1");

                    final String img_ad1_path = AppPaths.appPath("data/homeshow/src/img/ad1");

                    final String img_yytj_item1_path = AppPaths.appPath("data/homeshow/src/img/yytj_item1");

                    final String img_yytj_item2_path = AppPaths.appPath("data/homeshow/src/img/yytj_item2");

                    final String img_yytj_item3_path = AppPaths.appPath("data/homeshow/src/img/yytj_item3");

                    String urls = AppPaths.appPath("settings/f10/set2.inf");
                    urls = wj.dqwb(urls);
                    urls = e1.urlUnlockString(urls);

                    if (zf.cjw(urls, "/")) {

                    } else {

                        urls = urls + "/";

                    }

                    String url = urls + "homeshow.json";

                    if (e1.yz()) {

                        String st2 = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                        if (zf.dy(st2, null)) {

                            if (wj.cz(nopa2)) {

                                canshow = true;

                            }

                        } else {

                            String f1 = e1.readJson(st2, "code");

                            if (zf.dy(f1, "200")) {

                                if (wj.cz(nopa2)) {

                                    String md5a = sj.md5(wj.dqwb(nopa2));

                                    String md5b = sj.md5(st2);

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

                        final String st5 = wj.dqwb(nopa2);
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

                        final String ad_state = e1.readJson(st5, "ad_state");

                        final String div_lbl1_img_url = e1.readJson(st5, "div_lbl1_img_url");

                        final String div_lbl1_text = e1.readJson(st5, "div_lbl1_text");

                        final String div_ad1_img_url = e1.readJson(st5, "div_ad1_img_url");

                        final String div_ad1_text = e1.readJson(st5, "div_ad1_text");

                        final String div_ad1_subtext = e1.readJson(st5, "div_ad1_subtext");

                        final String div_yytj_item1_text = e1.readJson(st5, "div_yytj_item1_text");

                        final String div_yytj_item1_subtext = e1.readJson(st5, "div_yytj_item1_subtext");

                        final String div_yytj_item2_text = e1.readJson(st5, "div_yytj_item2_text");

                        final String div_yytj_item2_subtext = e1.readJson(st5, "div_yytj_item2_subtext");

                        final String div_yytj_item3_text = e1.readJson(st5, "div_yytj_item3_text");

                        final String div_yytj_item3_subtext = e1.readJson(st5, "div_yytj_item3_subtext");

                        final String div_jcsp_item1_text = e1.readJson(st5, "div_jcsp_item1_text");

                        final String div_jcsp_item1_subtext = e1.readJson(st5, "div_jcsp_item1_subtext");

                        final String div_jcsp_item2_text = e1.readJson(st5, "div_jcsp_item2_text");

                        final String div_jcsp_item2_subtext = e1.readJson(st5, "div_jcsp_item2_subtext");

                        final String div_jcsp_item3_text = e1.readJson(st5, "div_jcsp_item3_text");

                        final String div_jcsp_item3_subtext = e1.readJson(st5, "div_jcsp_item3_subtext");

                        final String div_rmyy_item1_text = e1.readJson(st5, "div_rmyy_item1_text");

                        final String div_rmyy_item1_subtext = e1.readJson(st5, "div_rmyy_item1_subtext");

                        final String div_rmyy_item2_text = e1.readJson(st5, "div_rmyy_item2_text");

                        final String div_rmyy_item2_subtext = e1.readJson(st5, "div_rmyy_item2_subtext");

                        final String div_rmyy_item3_text = e1.readJson(st5, "div_rmyy_item3_text");

                        final String div_rmyy_item3_subtext = e1.readJson(st5, "div_rmyy_item3_subtext");

                        String downfile = div_lbl1_img_url;

                        String downfilepath = img_lbl1_path;

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
                        gj.jmxc(new Runnable() {

                            public void run() {

                                if (zf.dy(ad_state, "true") && !e1.isJh()) {

                                    xdbj20.kjd(0);

                                } else {

                                    xdbj20.kjd(8);

                                }

                                if (wj.cz(img_lbl1_path)) {

                                    tx11.tx(img_lbl1_path);

                                } else {

                                    tx11.tx(R.mipmap.load_photo);

                                }
                                wb29.zf(div_lbl1_text);

                                if (wj.cz(img_ad1_path)) {

                                    tx18.tx(img_ad1_path);

                                } else {

                                    tx18.tx(R.mipmap.b11);

                                }
                                wb52.zf(div_ad1_text);
                                wb54.zf(div_ad1_subtext);

                                if (wj.cz(img_yytj_item1_path)) {

                                    tx6.tx(img_yytj_item1_path);

                                } else {

                                    tx6.tx(R.mipmap.appicon);

                                }
                                wb14.zf(div_yytj_item1_text);
                                wb16.zf(div_yytj_item1_subtext);

                                if (wj.cz(img_yytj_item2_path)) {

                                    tx9.tx(img_yytj_item2_path);

                                } else {

                                    tx9.tx(R.mipmap.appicon);

                                }
                                wb23.zf(div_yytj_item2_text);
                                wb25.zf(div_yytj_item2_subtext);

                                if (wj.cz(img_yytj_item3_path)) {

                                    tx10.tx(img_yytj_item3_path);

                                } else {

                                    tx10.tx(R.mipmap.appicon);

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

    public String getAppIconUrl(Object id) {
        id = zf.qc(id, "awg://uri/app/", null);
        return src_url + "/app/" + id + "/app-image/icon.png";

    }

    public int homeshow_filedown(Object url, Object post, Object cookie, Object path) {

        Object a = url;

        Object b = path;

        Object c = post;

        Object d = cookie;

        boolean e = true;

        String f = "User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept-language=zh-CN";
        return wl.xz(a, b, true, c, "utf-8", d, e, f);

    }

    public void show(int i_) {

        final String set65 = AppPaths.appPath("settings/set5.inf");

        final int i = i_;

        final i.runlibrary.app.v.wb tab = st.wb(R.id.title_bar);

        final i.runlibrary.app.v.xdbj xdbj1 = st.xdbj(R.id.xdbj1);

        final i.runlibrary.app.v.xdbj xdbj5 = st.xdbj(R.id.xdbj5);

        final i.runlibrary.app.v.xdbj xdbj6 = st.xdbj(R.id.xdbj6);

        final i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(R.id.qtgd1);

        final i.runlibrary.app.v.v7lb v7lb1 = st.v7lb(R.id.v7lb1);

        final String set1 = AppPaths.appPath("settings/f21/set1.inf");

        i.runlibrary.app.gj$dh dh = (i.runlibrary.app.gj$dh) (Object) gj.dh();

        final i.runlibrary.app.gj$Animationx jbtm = (i.runlibrary.app.gj$Animationx) (Object) dh.jbtm(0, 1);
        jbtm.cxsc(300);
        gj.jmxc(new Runnable() {

            public void run() {

                if (i == 0) {

                    qtgd1.v.requestFocus();
                    tab.zf(zf.zf(R.string.app_name));
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

                        st.wb(R.id.wb9).kjd(0);

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

            st.wb(R.id.wb11).kjd(8);
            st.wb(R.id.wb13).kjd(8);

        } else {

            st.wb(R.id.wb11).kjd(0);
            st.wb(R.id.wb13).kjd(0);

        }

        final String def = "欢迎使用腕管Pro，这是一款优秀的应用。您可以前往腕管Pro官网：https://www.typheye.cn/awg，提出建议或意见。如果您有任何问题，可以联系开发者台风眼(QQ498978473)，或者通过邮箱(498978473@qq.com)联系我们！";
        gj.xc(new Thread() {

            public void run() {

                final String nopa = AppPaths.appPath("data/notice/showing");

                final String nopa2 = AppPaths.appPath("data/notice/json");

                String nr = "";

                if (wj.cz(nopa) && !zf.dy(wj.dqwb(nopa), "")) {

                    nr = wj.dqwb(nopa);

                } else {

                    nr = def;

                }

                final String nr1 = nr;
                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.notice_wb).zf(nr1);

                    }

                });

                String urls = AppPaths.appPath("settings/f10/set2.inf");
                urls = wj.dqwb(urls);
                urls = e1.urlUnlockString(urls);

                if (zf.cjw(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                String url = urls + "notice.json";

                final String st5 = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                if (zf.dy(st5, null)) {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            String nr = "";

                            if (wj.cz(nopa) && !zf.dy(wj.dqwb(nopa), "")) {

                                nr = wj.dqwb(nopa);

                            } else {

                                nr = def;

                            }
                            st.wb(R.id.notice_wb).zf(nr);

                        }

                    });

                } else {

                    String f1 = e1.readJson(st5, "Showing");

                    if (zf.dy(f1, "0")) {

                        gj.jmxc(new Runnable() {

                            public void run() {

                                String nr = "";

                                if (wj.cz(nopa) && !zf.dy(wj.dqwb(nopa), "")) {

                                    nr = wj.dqwb(nopa);

                                } else {

                                    nr = def;

                                }
                                st.wb(R.id.notice_wb).zf(nr);

                            }

                        });

                    } else {

                        if (zf.dy(f1, "")) {

                            gj.jmxc(new Runnable() {

                                public void run() {

                                    String nr = "";

                                    if (wj.cz(nopa) && !zf.dy(wj.dqwb(nopa), "")) {

                                        nr = wj.dqwb(nopa);

                                    } else {

                                        nr = def;

                                    }
                                    st.wb(R.id.notice_wb).zf(nr);

                                }

                            });

                        } else {

                            final String nr2 = f1;
                            wj.xrwb(nopa, nr2);
                            wj.xrwb(nopa2, st5);
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    st.wb(R.id.notice_wb).zf(nr2);

                                }

                            });

                        }

                    }

                }

            }

        });

    }

    public void inj() {

        final String sec_updater = AppPaths.appPath("data/sec/updater");

        final String sec_isunlock = AppPaths.appPath("data/sec/unlock");

        final String sec_license = AppPaths.appPath("data/sec/license");
        gj.xc(new Thread() {

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

        String asset_a0 = AppPaths.appPath("data/assets/a0");

        String asset_a2 = AppPaths.appPath("data/assets/a2");

        String asset_a3 = AppPaths.appPath("data/assets/a3");

        String asset_a4 = AppPaths.appPath("data/assets/a4");

        String asset_a5 = AppPaths.appPath("data/assets/a5");

        String asset_a6 = AppPaths.appPath("data/assets/a6");

        String asset_a7 = AppPaths.appPath("data/assets/a7");

        String asset_a8 = AppPaths.appPath("data/assets/a8");

        String asset_a9 = AppPaths.appPath("data/assets/a9");

        String asset_a10 = AppPaths.appPath("data/assets/a10");

        String asset_a12 = AppPaths.appPath("data/assets/a12");

        String asset_a13 = AppPaths.appPath("data/assets/a13");

        String asset_a14 = AppPaths.appPath("data/assets/a14");

        String asset_a15 = AppPaths.appPath("data/assets/a15");

        String asset_a16 = AppPaths.appPath("data/assets/a16");

        String asset_a17 = AppPaths.appPath("data/assets/a17");

        String asset_a18 = AppPaths.appPath("data/assets/a18");

        String asset_a19 = AppPaths.appPath("data/assets/a19");

        String asset_a20 = AppPaths.appPath("data/assets/a20");

        String asset_b0 = AppPaths.appPath("data/assets/b0");

        String asset_b1 = AppPaths.appPath("data/assets/b1");

        String asset_b2 = AppPaths.appPath("data/assets/b2");

        String asset_b3 = AppPaths.appPath("data/assets/b3");

        String asset_c1 = AppPaths.appPath("data/assets/c1");

        String asset_e3 = AppPaths.appPath("data/assets/e3");

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

        String set0 = AppPaths.appPath("settings/version");

        String set1 = AppPaths.appPath("settings/set1.inf");

        String set2 = AppPaths.appPath("settings/set2.inf");

        String set3 = AppPaths.appPath("settings/d2/set1.inf");

        String set4 = AppPaths.appPath("settings/d2/set2.inf");

        String set5 = AppPaths.appPath("settings/set3.inf");

        String set6 = AppPaths.appPath("settings/d2/set3.inf");

        String set7 = AppPaths.appPath("settings/d2/set4.inf");

        String set8 = AppPaths.appPath("settings/d1/set1.inf");

        String set9 = AppPaths.appPath("settings/f7/set1.inf");

        String set10 = AppPaths.appPath("settings/f8/set1.inf");

        String set11 = AppPaths.appPath("settings/f9/set1.inf");

        String set12 = AppPaths.appPath("settings/f10/set1.inf");

        String set13 = AppPaths.appPath("settings/f10/set2.inf");

        String set14 = AppPaths.appPath("settings/f11/set1.inf");

        String set15 = AppPaths.appPath("settings/f11/set2.inf");

        String set16 = AppPaths.appPath("settings/f11/set3.inf");

        String set17 = AppPaths.appPath("settings/db/talking.db");

        String set18 = AppPaths.appPath("settings/f8/set2.inf");

        String set19 = AppPaths.appPath("settings/f13/set1.inf");

        String set20 = AppPaths.appPath("settings/f13/set2.inf");

        String set21 = AppPaths.appPath("settings/f13/set3.inf");

        String set22 = AppPaths.appPath("settings/f8/set3.inf");

        String set23 = AppPaths.appPath("settings/d1/set3.inf");

        String set24 = AppPaths.appPath("settings/f15/set1.inf");

        String set25 = AppPaths.appPath("settings/d1/set4.inf");

        String set26 = AppPaths.appPath("settings/f15/set2.inf");

        String set27 = AppPaths.appPath("settings/db/file.db");

        String set28 = "/storage/emulated/0/Download/.nomedia";

        String set29 = AppPaths.appPath("Download/.nomedia");

        String set30 = AppPaths.appPath("settings/f8/set10.inf");

        String set31 = AppPaths.appPath("settings/d2/set5.inf");

        String set32 = AppPaths.appPath("settings/f13/set4.inf");

        String set33 = AppPaths.appPath("settings/other/0xF0HX1/index.json");

        String set34 = AppPaths.appPath("settings/f15/set3.inf");

        String set35 = AppPaths.appPath("settings/other/0xO7YX2/url");

        String set36 = AppPaths.appPath("settings/fixVersion");

        String set37 = AppPaths.appPath("settings/f15/set4.inf");

        String set38 = AppPaths.appPath("settings/f13/set5.inf");

        String set39 = AppPaths.appPath("settings/f11/set4.inf");

        String set40 = AppPaths.appPath("settings/f17/set1.inf");

        String set41 = AppPaths.appPath("settings/f8/set4.inf");

        String set42 = AppPaths.appPath("settings/set4s.inf");

        String set43 = AppPaths.appPath("settings/l6/set1.inf");

        String set44 = AppPaths.appPath("settings/db/isO");

        String set45 = AppPaths.appPath("settings/f18/set1.inf");

        String set46 = AppPaths.appPath("settings/f18/set2.inf");

        String set47 = AppPaths.appPath("settings/db/plugin.db");

        String set48 = AppPaths.appPath("settings/f19/set1.inf");

        String set49 = AppPaths.appPath("settings/f19/set2.inf");

        String set50 = AppPaths.appPath("settings/f19/set3.inf");

        String set51 = AppPaths.appPath("settings/f19/set4.inf");

        String set52 = AppPaths.appPath("settings/n1/set1.inf");

        String set53 = AppPaths.appPath("settings/n1/set2.inf");

        String set54 = AppPaths.appPath("settings/n1/set3.inf");

        String set55 = AppPaths.appPath("settings/n1/set4.inf");

        String set56 = AppPaths.appPath("settings/db/isO2");

        String set57 = AppPaths.appPath("settings/n1/url.inf");

        String set58 = AppPaths.appPath("settings/f7/Enable");

        String set59 = AppPaths.appPath("settings/other/0xO5HJ7/url");

        String set60 = AppPaths.appPath("settings/f7/Enable2");

        String set61 = AppPaths.appPath("settings/f15/set5.inf");

        String set62 = AppPaths.appPath("settings/other/0xO7YX2/cookie");

        String set63 = AppPaths.appPath("settings/set4.inf");

        String set64 = AppPaths.appPath("settings/i1/set1.inf");

        String set65 = AppPaths.appPath("settings/set5.inf");

        String set66 = AppPaths.appPath("settings/set6.inf");

        String set67 = AppPaths.appPath("settings/set7.inf");

        String set68 = AppPaths.appPath("settings/f21/set1.inf");

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

        i.runlibrary.app.xt$qxgl a = (i.runlibrary.app.xt$qxgl) (Object) xt.qxgl();

        int sdk = IappCompat.sdk(xt.sbxx());

        if (a.qx("android.permission.WRITE_EXTERNAL_STORAGE") && sdk < 30) {

            String m = "mkdir \"/sdcard/Download\"";
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

            wj.xrwb(set33, wj.dqwb(AppPaths.appPath("data/assets/a18")));

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

            String fv = a11 + "00";
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

            String q = "10000";

            String w = "扩展推荐";

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

        String fv = String.valueOf(vercode);
        wj.xrwb(set0, fv);
        wj.sc(set63);

        final String sec_updater = AppPaths.appPath("data/sec/updater");

        final String sec_isunlock = AppPaths.appPath("data/sec/unlock");

        final String sec_license = AppPaths.appPath("data/sec/license");

        if (wj.cz(sec_updater)) {

            wj.sc(sec_updater);

        }
        inj();

    }

    public void run() {
        usual();

    }

    public void usual() {

        final String a0 = AppPaths.appPath("settings/a4.inf");

        final String a1 = AppPaths.appPath("settings/a3.inf");

        final String set66 = AppPaths.appPath("settings/set6.inf");

        if (!wj.cz(a0) || zf.dy(wj.dqwb(set66), "false") || !wj.cz(set66)) {

            gj.jmxc(new Runnable() {

                public void run() {

                    supertip("点击按钮以继续", 2);

                }

            });

        } else {

        }

    }

    public void rjh() {
        gj.jmxc(new Runnable() {

            public void run() {

                if (!e1.isJh()) {

                    st.wb(R.id.wb3).kjd(0);

                } else {

                    st.wb(R.id.wb3).kjd(8);

                }

            }

        });

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);

        if (e1.getNowUserId() == 0) {

            final String a2 = AppPaths.appPath("settings/a3.inf");

            String a = wj.dqwb(a2);

            if (zf.dy(a, "4")) {

                if (loaded) {

                    String clr = zf.zf(R.color.colorTab);
                    setStatusBarColor(clr);

                } else {

                    String clr = zf.zf(R.color.colorTabBackground);
                    setStatusBarColor(clr);

                }

            } else {
        applyFullscreenWindow();

            }

            final String set1 = AppPaths.appPath("settings/f21/set1.inf");

            if (wj.cz(AppPaths.appPath("settings/a4.inf"))) {

                if (!isNotMainOnce) {

                    homeshow(false);

                }
                noticeShow();

            }

            if (isHasReq) {

                final String ca = AppPaths.appPath("cache/req/exit");

                if (wj.cz(ca) && zf.dy(wj.dqwb(ca), "true")) {

                    wj.sc(ca);
                    gj.jmxc(new Runnable() {

                        public void run() {

                            i.runlibrary.app.v.tx tx3 = st.tx(R.id.tx3);
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

            final String a2 = AppPaths.appPath("settings/a3.inf");

            String a = wj.dqwb(a2);

            if (zf.dy(a, "4")) {

                if (loaded) {

                    String clr = zf.zf(R.color.colorTab);
                    setStatusBarColor(clr);

                } else {

                    String clr = zf.zf(R.color.colorTabBackground);
                    setStatusBarColor(clr);

                }

            } else {
        applyFullscreenWindow();

            }

        }

    }

    public void onStart() {
        super.onStart();

        if (e1.getNowUserId() == 0) {

            lei.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

            final String a2 = AppPaths.appPath("settings/a3.inf");

            String a = wj.dqwb(a2);

            if (zf.dy(a, "4")) {

                if (loaded) {

                    String clr = zf.zf(R.color.colorTab);
                    setStatusBarColor(clr);

                } else {

                    String clr = zf.zf(R.color.colorTabBackground);
                    setStatusBarColor(clr);

                }

            } else {
        applyFullscreenWindow();

            }

        }

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

        qtgd1.v.requestFocus();

    }

    public void onResume() {
        super.onResume();

        if (e1.getNowUserId() == 0) {

            final String a2 = AppPaths.appPath("settings/a3.inf");

            String a = wj.dqwb(a2);

            if (zf.dy(a, "4")) {

                if (loaded) {

                    String clr = zf.zf(R.color.colorTab);
                    setStatusBarColor(clr);

                } else {

                    String clr = zf.zf(R.color.colorTabBackground);
                    setStatusBarColor(clr);

                }

            } else {
        applyFullscreenWindow();

            }

        }

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

        if (e1.getNowUserId() == 0) {

            e13.N_cancel();
            wj.sc(AppPaths.appPath("data/AwgCoreService.r"));

        }

    }

    private void onTitleBarClick(View vw) {

        if (zf.dy(st.wb(R.id.title_bar).zf(), zf.zf(R.string.app_name))) {

            show(1);

        } else if (zf.dy(st.wb(R.id.title_bar).zf(), " 腕管Pro ")) {

            show(0);

        }

    }

    private void onXxbj5Click(View vw) {
        gj.tz(AnnouncementActivity.class);

    }

    private void onWb7Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                st.xdbj(R.id.xdbj7).kjd(8);

            }

        });

    }

    private void onWb11Click(View vw) {
        gj.tz(AppActivationActivity.class);

    }

    private void onWb2Click(View vw) {
        homeshow(true);

    }

    private void onXdbj8Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_lbl1_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean onXdbj8LongClick(View vw) {

        String te = st.wb(R.id.wb29).zf();
        e1.tsk("详细信息", te);
        return true;

    }

    private void onWb56Click(View vw) {
        e1.tsk("提示", "激活腕管Pro后可关闭所有广告");

    }

    private void onXdbj20Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_ad1_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean onXdbj20LongClick(View vw) {

        String te = st.wb(R.id.wb52).zf();

        String lj = st.wb(R.id.wb54).zf();
        e1.tsk("详细信息", "[推荐名称]\n" + te + "\n\n[推荐简介]\n" + lj);
        return true;

    }

    private void onXdbj9Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_yytj_item1_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean onXdbj9LongClick(View vw) {

        String te = st.wb(R.id.wb14).zf();

        String lj = st.wb(R.id.wb16).zf();
        e1.tsk("详细信息", "[应用名称]\n" + te + "\n\n[应用简介]\n" + lj);
        return true;

    }

    private void onXdbj12Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_yytj_item2_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean onXdbj12LongClick(View vw) {

        String te = st.wb(R.id.wb23).zf();

        String lj = st.wb(R.id.wb25).zf();
        e1.tsk("详细信息", "[应用名称]\n" + te + "\n\n[应用简介]\n" + lj);
        return true;

    }

    private void onXdbj13Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_yytj_item3_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean onXdbj13LongClick(View vw) {

        String te = st.wb(R.id.wb26).zf();

        String lj = st.wb(R.id.wb28).zf();
        e1.tsk("详细信息", "[应用名称]\n" + te + "\n\n[应用简介]\n" + lj);
        return true;

    }

    private void onWb10Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"type", "search"};

                String[] val = new String[]{"app", "all"};
                gj.tz(StoreListActivity.class, iyu, val);

            }

        });

    }

    private void onXdbj14Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_jcsp_item1_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean onXdbj14LongClick(View vw) {

        String te = st.wb(R.id.wb31).zf();

        String lj = st.wb(R.id.wb33).zf();
        e1.tsk("详细信息", "[视频名称]\n" + te + "\n\n[视频简介]\n" + lj);
        return true;

    }

    private void onXdbj15Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_jcsp_item2_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean onXdbj15LongClick(View vw) {

        String te = st.wb(R.id.wb34).zf();

        String lj = st.wb(R.id.wb36).zf();
        e1.tsk("详细信息", "[视频名称]\n" + te + "\n\n[视频简介]\n" + lj);
        return true;

    }

    private void onXdbj16Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_jcsp_item3_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean onXdbj16LongClick(View vw) {

        String te = st.wb(R.id.wb37).zf();

        String lj = st.wb(R.id.wb39).zf();
        e1.tsk("详细信息", "[视频名称]\n" + te + "\n\n[视频简介]\n" + lj);
        return true;

    }

    private void onWb40Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"type", "search"};

                String[] val = new String[]{"video", "all"};
                gj.tz(StoreListActivity.class, iyu, val);

            }

        });

    }

    private void onXdbj17Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_rmyy_item1_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean onXdbj17LongClick(View vw) {

        String te = st.wb(R.id.wb42).zf();

        String lj = st.wb(R.id.wb44).zf();
        e1.tsk("详细信息", "[歌曲名称]\n" + te + "\n\n[歌曲作者]\n" + lj);
        return true;

    }

    private void onXdbj18Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_rmyy_item2_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean onXdbj18LongClick(View vw) {

        String te = st.wb(R.id.wb45).zf();

        String lj = st.wb(R.id.wb47).zf();
        e1.tsk("详细信息", "[歌曲名称]\n" + te + "\n\n[歌曲作者]\n" + lj);
        return true;

    }

    private void onXdbj19Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_rmyy_item3_url};
                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean onXdbj19LongClick(View vw) {

        String te = st.wb(R.id.wb48).zf();

        String lj = st.wb(R.id.wb50).zf();
        e1.tsk("详细信息", "[歌曲名称]\n" + te + "\n\n[歌曲作者]\n" + lj);
        return true;

    }

    private void onWb51Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"type", "search"};

                String[] val = new String[]{"music", "all"};
                gj.tz(StoreListActivity.class, iyu, val);

            }

        });

    }

    private void onTx1Click(View vw) {
        gj.tz(StoreSearchActivity.class);

    }

    private void onWb13Click(View vw) {
        gj.tz(AppActivationActivity.class);

    }

    private boolean onWb9LongClick(View vw) {

        String set65 = AppPaths.appPath("settings/set5.inf");
        wj.xrwb(set65, "true");
        gj.jmxc(new Runnable() {

            public void run() {

                st.wb(R.id.wb9).kjd(8);

            }

        });
        return false;

    }

    private void onTx15Click(View vw) {
        homeshow_show(1);
        show(0);

    }

    private void onTx16Click(View vw) {
        supertip_an();

    }

    private boolean onXdbj10Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onWb12Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                i.runlibrary.app.v.tx tx3 = st.tx(R.id.tx3);
                tx3.v.performClick();

            }

        });

    }

    private void onWb17Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                st.wb(R.id.wb17).kjd(8);

            }

        });

    }

    private boolean onXdbj2Touch(View vw, MotionEvent me) {
        return true;

    }

    private boolean onTCxdbjTouch(View vw, MotionEvent me) {
        return true;

    }

    private void onTx2Click(View vw) {

        i.runlibrary.app.gj$dh dh = (i.runlibrary.app.gj$dh) (Object) gj.dh();

        final i.runlibrary.app.gj$Animationx jbtm = (i.runlibrary.app.gj$Animationx) (Object) dh.jbtm(1, 0);
        jbtm.cxsc(300);
        gj.jmxc(new Runnable() {

            public void run() {

                st.xdbj(R.id.TCxdbj).kjd(8);
                st.xdbj(R.id.TCxdbj).dh(jbtm);

            }

        });

    }

    private void onTx3Click(View vw) {
        gj.xc(new Thread() {

            public void run() {

                final i.runlibrary.app.v.xdbj main_v = st.xdbj(R.id.main_activity_root);
                gj.jmxc(new Runnable() {

                    public void run() {

                        main_v.scqb();

                    }

                });
                gj.zt(500);
                wj.sc(AppPaths.appPath("data/AwgCoreService.r"));

                try {

                    e13.N_cancel();

                } catch (Throwable __$_e__) {

                }
                AwgProApplication.getInstance().exit();

            }

        });

    }

    private void onWbMainKjfsGbcdClick(View vw) {
        show(0);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        TextView e943ebd71e = (TextView) findViewById(ay, vw, R.id.wb4);

        TextView i2a86c78b06 = (TextView) findViewById(ay, vw, R.id.wb5);

        TextView i78e3e43012 = (TextView) findViewById(ay, vw, R.id.notice_wb);

        LinearLayout bd584e50cd = (LinearLayout) findViewById(ay, vw, R.id.xxbj5);
        bd584e50cd.setOnClickListener(xxbj5ClickListener);

        TextView b8bc22741b = (TextView) findViewById(ay, vw, R.id.wb7);
        b8bc22741b.setOnClickListener(wb7ClickListener);

        TextView i42afe4289f = (TextView) findViewById(ay, vw, R.id.wb11);
        i42afe4289f.setOnClickListener(wb11ClickListener);

        TextView i7c3e3c6b5d = (TextView) findViewById(ay, vw, R.id.wb2);
        i7c3e3c6b5d.setOnClickListener(wb2ClickListener);

        RelativeLayout fab146d7a5 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj8);
        fab146d7a5.setOnClickListener(xdbj8ClickListener);
        fab146d7a5.setOnLongClickListener(xdbj8LongClickListener);

        TextView i9d626a4248 = (TextView) findViewById(ay, vw, R.id.wb56);
        i9d626a4248.setOnClickListener(wb56ClickListener);

        RelativeLayout bab1d5f82e = (RelativeLayout) findViewById(ay, vw, R.id.xdbj20);
        bab1d5f82e.setOnClickListener(xdbj20ClickListener);
        bab1d5f82e.setOnLongClickListener(xdbj20LongClickListener);

        RelativeLayout i5a93676674 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj9);
        i5a93676674.setOnClickListener(xdbj9ClickListener);
        i5a93676674.setOnLongClickListener(xdbj9LongClickListener);

        RelativeLayout f73b53def9 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj12);
        f73b53def9.setOnClickListener(xdbj12ClickListener);
        f73b53def9.setOnLongClickListener(xdbj12LongClickListener);

        RelativeLayout i62f27ff563 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj13);
        i62f27ff563.setOnClickListener(xdbj13ClickListener);
        i62f27ff563.setOnLongClickListener(xdbj13LongClickListener);

        TextView i81652f77b6 = (TextView) findViewById(ay, vw, R.id.wb10);
        i81652f77b6.setOnClickListener(wb10ClickListener);

        RelativeLayout c4cbe9d7ff = (RelativeLayout) findViewById(ay, vw, R.id.xdbj14);
        c4cbe9d7ff.setOnClickListener(xdbj14ClickListener);
        c4cbe9d7ff.setOnLongClickListener(xdbj14LongClickListener);

        RelativeLayout i6834408ef9 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj15);
        i6834408ef9.setOnClickListener(xdbj15ClickListener);
        i6834408ef9.setOnLongClickListener(xdbj15LongClickListener);

        RelativeLayout c9f5a3ee67 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj16);
        c9f5a3ee67.setOnClickListener(xdbj16ClickListener);
        c9f5a3ee67.setOnLongClickListener(xdbj16LongClickListener);

        TextView i3d78c07838 = (TextView) findViewById(ay, vw, R.id.wb40);
        i3d78c07838.setOnClickListener(wb40ClickListener);

        RelativeLayout i8890b048c8 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj17);
        i8890b048c8.setOnClickListener(xdbj17ClickListener);
        i8890b048c8.setOnLongClickListener(xdbj17LongClickListener);

        RelativeLayout i11185eacd9 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj18);
        i11185eacd9.setOnClickListener(xdbj18ClickListener);
        i11185eacd9.setOnLongClickListener(xdbj18LongClickListener);

        RelativeLayout a229195608 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj19);
        a229195608.setOnClickListener(xdbj19ClickListener);
        a229195608.setOnLongClickListener(xdbj19LongClickListener);

        TextView i6a848b922c = (TextView) findViewById(ay, vw, R.id.wb51);
        i6a848b922c.setOnClickListener(wb51ClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        RelativeLayout c0d3e00d2b = (RelativeLayout) findViewById(ay, vw, R.id.xdbj5);

        RelativeLayout aaeebbd136 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj1);

        TextView adf370a235 = (TextView) findViewById(ay, vw, R.id.wb13);
        adf370a235.setOnClickListener(wb13ClickListener);

        TextView i4ba8980add = (TextView) findViewById(ay, vw, R.id.wb9);
        i4ba8980add.setOnLongClickListener(wb9LongClickListener);

        TextView f52e07ac8c = (TextView) findViewById(ay, vw, R.id.wb6);

        ImageView i7a998f9ecb = (ImageView) findViewById(ay, vw, R.id.tx15);
        i7a998f9ecb.setOnClickListener(tx15ClickListener);

        RelativeLayout i171cedf1eb = (RelativeLayout) findViewById(ay, vw, R.id.xdbj6);

        ImageView a81b730195 = (ImageView) findViewById(ay, vw, R.id.tx16);
        a81b730195.setOnClickListener(tx16ClickListener);

        CardView i358e59a100 = (CardView) findViewById(ay, vw, R.id.kp5);

        RelativeLayout i5b34eede7f = (RelativeLayout) findViewById(ay, vw, R.id.xdbj10);
        i5b34eede7f.setOnTouchListener(xdbj10TouchListener);

        TextView i3a177cf5f1 = (TextView) findViewById(ay, vw, R.id.wb12);
        i3a177cf5f1.setOnClickListener(wb12ClickListener);

        TextView i2318575919 = (TextView) findViewById(ay, vw, R.id.wb17);
        i2318575919.setOnClickListener(wb17ClickListener);

        RelativeLayout i3167b45174 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj2);
        i3167b45174.setOnTouchListener(xdbj2TouchListener);

        RelativeLayout b0f5bad894 = (RelativeLayout) findViewById(ay, vw, R.id.TCxdbj);
        b0f5bad894.setOnTouchListener(tCxdbjTouchListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        TextView i6a8b9facdf = (TextView) findViewById(ay, vw, R.id.wb_main_kjfs_gbcd);
        i6a8b9facdf.setOnClickListener(wbMainKjfsGbcdClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
