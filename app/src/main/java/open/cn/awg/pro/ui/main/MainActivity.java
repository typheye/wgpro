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
import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
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
import open.cn.awg.pro.service.BootJobService;
import open.cn.awg.pro.store.StoreDetailActivity;
import open.cn.awg.pro.store.StoreListActivity;
import open.cn.awg.pro.store.StoreSearchActivity;
import open.cn.awg.pro.update.UpdatePromptActivity;

@SuppressLint("StaticFieldLeak")
public class MainActivity extends BaseAwgActivity {

    public static Context context;
    public final MainActivity lei = this, 类 = this;
    public open.cn.awg.pro.ui.list.NativeListAdapter spq = null;
    public AppRuntimeBridge e1;
    public LocalDatabaseBridge e11;
    public CoreRuntimeBootstrap e13;
    public DebugStateStore debug;
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
    public TypheyeServiceBridge easy;
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
        debug = new DebugStateStore(MainActivity.this);
        e1 = new AppRuntimeBridge(this);
        e11 = new LocalDatabaseBridge(this);
        e13 = new CoreRuntimeBootstrap(this);
        easy = new TypheyeServiceBridge(this);
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

            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    csh0();
                    open.cn.awg.pro.core.NativeActions.sleep(500);
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            e13.downAssets();

                            String set0 = AppPaths.appPath("settings/version");
                            vercode = com.demo.e4.getVersionCode(lei);

                            String bb = String.valueOf(vercode);

                            if (!java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (set0)), bb) || !open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set0))) {

                                set();

                            } else {

                                inj();

                            }

                            if (isShow && open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (AppPaths.appPath("settings/a4.inf")))) {

                                

                                android.view.animation.AlphaAnimation jbtm = (android.view.animation.AlphaAnimation) (Object) new android.view.animation.AlphaAnimation(open.cn.awg.pro.core.TextValues.floatValue(1, -1), open.cn.awg.pro.core.TextValues.floatValue(0, -1));
                                jbtm.setDuration(500);
                                ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.xdbj2)).setVisibility(android.view.View.GONE);
                                ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.xdbj2)).startAnimation(jbtm);
                                loaded = true;

                                final String a2 = AppPaths.appPath("settings/a3.inf");

                                String a = open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (a2));

                                if (java.util.Objects.equals(a, "4")) {

                                    if (loaded) {

                                        String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTab);
                                        setStatusBarColor(clr);

                                    } else {

                                        String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTabBackground);
                                        setStatusBarColor(clr);

                                    }

                                }

                                String set67 = AppPaths.appPath("settings/set7.inf");
                                src_url = open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (set67));
                                zyqd();

                                if (!debug.isShowed()) {

                                    e1.goplu("20005", "后台诊断", false);

                                }

                                if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (AppPaths.appPath("settings/a4.inf")))) {

                                    if (e1.islogin()) {

                                        n3_jz();

                                    }

                                    String set11 = AppPaths.appPath("settings/f9/set1.inf");

                                    if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (set11)), "true")) {

                                        int a11;

                                        String vn;
                                        a11 = com.demo.e4.getVersionCode(lei);
                                        vn = com.demo.e4.getVersionName(lei);
                                        e1.update(a11);

                                        if (e1.islogin()) {

                                        }

                                    }
                                    open.cn.awg.pro.core.NativeFiles.delete(MainActivity.this, (String) (AppPaths.appPath("data/ltlb/showing")), false);

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

            

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

                if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                } else {

                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                }

            }

            final String a2 = AppPaths.appPath("settings/a3.inf");

            String a = "";

            if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (a2))) {

                a = open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (a2));

            }

            if (java.util.Objects.equals(a, "4")) {

                ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.main_activity_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(true));

                if (loaded) {

                    String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTab);
                    setStatusBarColor(clr);

                } else {

                    String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTabBackground);
                    setStatusBarColor(clr);

                }

            } else {

                ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.main_activity_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

            }

            int uu = 0;

            if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf")))) {

                uu = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));

            }

            final int u = uu;

            if (java.util.Objects.equals(a, "1")) {

                open.cn.awg.pro.core.NativeViewValues.margins(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.xdbj4)), u, 10, u, 10);

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.title_bar));
                open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "15dp", 0, "3dp");
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                android.widget.TextView wtab2 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.Title2));
                open.cn.awg.pro.core.NativeViewValues.padding(wtab2, 0, "15dp", 0, "3dp");
                wtab2.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                androidx.recyclerview.widget.RecyclerView lb = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.v7lb1));
                spq = open.cn.awg.pro.ui.list.NativeListAdapter.attach(MainActivity.this, lb, R.layout.main_menu_item, new open.cn.awg.pro.ui.list.MainMenuRowBinder(MainActivity.this), new open.cn.awg.pro.ui.list.NativeListAdapter.RowBinder() {

                    public void bind(View vw, java.util.Map<Integer, Object> row, int pn) {

                        

                        int f = open.cn.awg.pro.core.TextValues.intValue(row.get(-1), -1);

                        if (f == 0) {

                            android.widget.LinearLayout xxbj = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj2));
                            xxbj.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));
                            xxbj.setVisibility(android.view.View.VISIBLE);
                            xxbj.setClickable(java.lang.Boolean.TRUE.equals(true));
                            xxbj.setLongClickable(java.lang.Boolean.TRUE.equals(true));

                        } else if (f == 1) {

                            android.widget.LinearLayout xxbj = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj2));
                            xxbj.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center_vertical|left"));
                            xxbj.setVisibility(android.view.View.VISIBLE);
                            xxbj.setClickable(java.lang.Boolean.TRUE.equals(true));
                            xxbj.setLongClickable(java.lang.Boolean.TRUE.equals(true));

                        } else if (f == -1) {

                            android.widget.LinearLayout xxbj = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj2));
                            xxbj.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center_vertical|right"));
                            xxbj.setVisibility(android.view.View.VISIBLE);
                            xxbj.setClickable(java.lang.Boolean.TRUE.equals(true));
                            xxbj.setLongClickable(java.lang.Boolean.TRUE.equals(true));

                        } else if (f == -2) {

                            android.widget.LinearLayout xxbj = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj2));
                            xxbj.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));
                            xxbj.setVisibility(android.view.View.GONE);
                            xxbj.setClickable(java.lang.Boolean.TRUE.equals(false));
                            xxbj.setLongClickable(java.lang.Boolean.TRUE.equals(false));

                        } else {

                            android.widget.LinearLayout xxbj = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj2));
                            xxbj.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));
                            xxbj.setVisibility(android.view.View.VISIBLE);
                            xxbj.setClickable(java.lang.Boolean.TRUE.equals(true));
                            xxbj.setLongClickable(java.lang.Boolean.TRUE.equals(true));

                        }

                    }

                });

                int[] kj = new int[]{R.id.tx1, R.id.wb1, R.id.wb2, R.id.wb3, -1};

                Object[] jk = new Object[]{R.mipmap.a39, "系统工具", "10", "", -1};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a29, "关闭菜单", "31", "", -1};
                jk = new Object[]{R.mipmap.a20, "文件管理", "1", "", 0};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a37, "腕上微聊", "9", "", 1};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a40, "X5浏览器", "11", "", -1};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a48, "扩展插件", "20", "", 0};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a43, "更多功能", "13", "", 1};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a10, "应用设置", "8", "", -1};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a46, "返回桌面", "14", "", 0};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a23, "退出应用", "3", "", 1};
                spq.addRow(kj, jk);
                jk = new Object[]{"", "", "", "", -2};
                spq.addRow(kj, jk);
                spq.addRow(kj, jk);
                spq.addRow(kj, jk);

                androidx.recyclerview.widget.RecyclerView lbx = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.v7lb1));

                GridLayoutManager mLayoutManager = new GridLayoutManager(lei, 3);
                lbx.setLayoutManager(mLayoutManager);
                spq.refreshRows();

            } else if (java.util.Objects.equals(a, "2")) {

                String a001 = AppPaths.appPath("settings/dpi.inf");

                if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (a001)) || java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (a001)), "true")) {

                    android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.title_bar));
                    open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "3dp", 0, "3dp");
                    wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                    android.widget.TextView wtab2 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.Title2));
                    open.cn.awg.pro.core.NativeViewValues.padding(wtab2, 0, "3dp", 0, "3dp");
                    wtab2.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                } else {

                    android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.title_bar));
                    open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "9dp", 0, "9dp");
                    wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                    android.widget.TextView wtab2 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.Title2));
                    open.cn.awg.pro.core.NativeViewValues.padding(wtab2, 0, "9dp", 0, "9dp");
                    wtab2.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                }
                open.cn.awg.pro.core.NativeViewValues.margins(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.xdbj4)), 10, 10, 10, 10);

                androidx.recyclerview.widget.RecyclerView lb = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.v7lb1));
                spq = open.cn.awg.pro.ui.list.NativeListAdapter.attach(MainActivity.this, lb, R.layout.main_menu_item, new open.cn.awg.pro.ui.list.MainMenuRowBinder(MainActivity.this));

                int[] kj = new int[]{R.id.tx1, R.id.wb1, R.id.wb2, R.id.wb3};

                Object[] jk = new Object[]{R.mipmap.a39, "系统工具", "10", ""};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a29, "关闭菜单", "31", ""};
                jk = new Object[]{R.mipmap.a20, "文件管理", "1", ""};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a37, "腕上微聊", "9", ""};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a40, "X5浏览器", "11", ""};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a48, "扩展插件", "20", ""};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a43, "更多功能", "13", ""};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a10, "应用设置", "8", ""};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a46, "返回桌面", "14", ""};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a23, "退出应用", "3", ""};
                spq.addRow(kj, jk);

                androidx.recyclerview.widget.RecyclerView lbx = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.v7lb1));

                if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (a001)) || java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (a001)), "true")) {

                    GridLayoutManager mLayoutManager = new GridLayoutManager(lei, 2);
                    lbx.setLayoutManager(mLayoutManager);

                } else {

                    GridLayoutManager mLayoutManager = new GridLayoutManager(lei, 3);
                    lbx.setLayoutManager(mLayoutManager);

                }
                spq.refreshRows();

            } else if (java.util.Objects.equals(a, "3")) {

                open.cn.awg.pro.core.NativeViewValues.margins(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.xdbj4)), u, 10, u, 10);

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.title_bar));
                open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "15dp", 0, "3dp");
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                android.widget.TextView wtab2 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.Title2));
                open.cn.awg.pro.core.NativeViewValues.padding(wtab2, 0, "15dp", 0, "3dp");
                wtab2.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                androidx.recyclerview.widget.RecyclerView lb = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.v7lb1));
                spq = open.cn.awg.pro.ui.list.NativeListAdapter.attach(MainActivity.this, lb, R.layout.main_menu_item, new open.cn.awg.pro.ui.list.MainMenuRowBinder(MainActivity.this), new open.cn.awg.pro.ui.list.NativeListAdapter.RowBinder() {

                    public void bind(View vw, java.util.Map<Integer, Object> row, int pn) {

                        

                        int f = open.cn.awg.pro.core.TextValues.intValue(row.get(-1), -1);

                        if (f == 0) {

                            android.widget.LinearLayout xxbj = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj2));
                            xxbj.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));
                            xxbj.setVisibility(android.view.View.VISIBLE);
                            xxbj.setClickable(java.lang.Boolean.TRUE.equals(true));
                            xxbj.setLongClickable(java.lang.Boolean.TRUE.equals(true));

                        } else if (f == 1) {

                            android.widget.LinearLayout xxbj = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj2));
                            xxbj.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center_vertical|left"));
                            xxbj.setVisibility(android.view.View.VISIBLE);
                            xxbj.setClickable(java.lang.Boolean.TRUE.equals(true));
                            xxbj.setLongClickable(java.lang.Boolean.TRUE.equals(true));

                        } else if (f == -1) {

                            android.widget.LinearLayout xxbj = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj2));
                            xxbj.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center_vertical|right"));
                            xxbj.setVisibility(android.view.View.VISIBLE);
                            xxbj.setClickable(java.lang.Boolean.TRUE.equals(true));
                            xxbj.setLongClickable(java.lang.Boolean.TRUE.equals(true));

                        } else if (f == -2) {

                            android.widget.LinearLayout xxbj = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj2));
                            xxbj.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));
                            xxbj.setVisibility(android.view.View.GONE);
                            xxbj.setClickable(java.lang.Boolean.TRUE.equals(false));
                            xxbj.setLongClickable(java.lang.Boolean.TRUE.equals(false));

                        } else {

                            android.widget.LinearLayout xxbj = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj2));
                            xxbj.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));
                            xxbj.setVisibility(android.view.View.VISIBLE);
                            xxbj.setClickable(java.lang.Boolean.TRUE.equals(true));
                            xxbj.setLongClickable(java.lang.Boolean.TRUE.equals(true));

                        }

                    }

                });

                int[] kj = new int[]{R.id.tx1, R.id.wb1, R.id.wb2, R.id.wb3, -1};

                Object[] jk = new Object[]{R.mipmap.a39, "系统工具", "10", "", -1};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a29, "关闭菜单", "31", "", -1};
                jk = new Object[]{R.mipmap.a20, "文件管理", "1", "", 0};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a37, "腕上微聊", "9", "", 1};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a40, "X5浏览器", "11", "", -1};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a48, "扩展插件", "20", "", 0};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a43, "更多功能", "13", "", 1};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a10, "应用设置", "8", "", -1};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a46, "返回桌面", "14", "", 0};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a23, "退出应用", "3", "", 1};
                spq.addRow(kj, jk);
                jk = new Object[]{"", "", "", "", -2};
                spq.addRow(kj, jk);
                spq.addRow(kj, jk);
                spq.addRow(kj, jk);

                androidx.recyclerview.widget.RecyclerView lbx = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.v7lb1));

                GridLayoutManager mLayoutManager = new GridLayoutManager(lei, 3);
                lbx.setLayoutManager(mLayoutManager);
                spq.refreshRows();

            } else if (java.util.Objects.equals(a, "4")) {

                open.cn.awg.pro.core.NativeViewValues.margins(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.xdbj4)), 10, 10, 10, 10);

                androidx.recyclerview.widget.RecyclerView lb = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.v7lb1));
                spq = open.cn.awg.pro.ui.list.NativeListAdapter.attach(MainActivity.this, lb, R.layout.legacy_menu_item, new open.cn.awg.pro.ui.list.LegacyMenuRowBinder(MainActivity.this), new open.cn.awg.pro.ui.list.NativeListAdapter.RowBinder() {

                    public void bind(View vw, java.util.Map<Integer, Object> row, int pn) {

                        

                        int f = open.cn.awg.pro.core.TextValues.intValue(row.get(-1), -1);

                        final android.widget.RelativeLayout xdbj1 = ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xdbj1));

                        final android.widget.TextView wb3 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb3));

                        if (f == 0) {

                            xdbj1.setVisibility(android.view.View.VISIBLE);
                            wb3.setVisibility(android.view.View.GONE);

                        } else {

                            xdbj1.setVisibility(android.view.View.GONE);
                            wb3.setVisibility(android.view.View.VISIBLE);

                        }

                    }

                });

                int[] kj = new int[]{R.id.tx1, R.id.wb1, R.id.wb2, R.id.wb3, -1};

                Object[] jk = new Object[]{R.mipmap.a39, "系统工具", "27", "", 0};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a29, "关闭菜单", "31", "", 0};
                jk = new Object[]{R.mipmap.a20, "文件管理", "6", "", 0};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a37, "腕上微聊", "7", "", 0};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a40, "X5浏览器", "10", "", 0};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a48, "扩展插件", "20", "", 0};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a43, "更多功能", "13", "", 0};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a10, "应用设置", "2", "", 0};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a46, "返回桌面", "14", "", 0};
                spq.addRow(kj, jk);
                jk = new Object[]{R.mipmap.a23, "退出应用", "12", "", 0};
                spq.addRow(kj, jk);
                spq.refreshRows();

            }

        }

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4 && ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.xdbj10)).getVisibility() == android.view.View.GONE && ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.xdbj2)).getVisibility() == android.view.View.GONE && ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.TCxdbj)).getVisibility() == android.view.View.GONE) {

            if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "腕管Pro")) {

                show(1);

            } else if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), " 腕管Pro ")) {

                

                final android.view.animation.AlphaAnimation jbtm = (android.view.animation.AlphaAnimation) (Object) new android.view.animation.AlphaAnimation(open.cn.awg.pro.core.TextValues.floatValue(0, -1), open.cn.awg.pro.core.TextValues.floatValue(1, -1));
                jbtm.setDuration(300);
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.TCxdbj)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.TCxdbj)).startAnimation(jbtm);

                    }

                });

            }

        }
        return false;

    }

    public void csh0() {

        String sec_updater = AppPaths.appPath("data/sec/updater");

        String sec_license = AppPaths.appPath("data/sec/license");
        open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (sec_license), (String) ("1"));
        open.cn.awg.pro.core.NativeFiles.delete(MainActivity.this, (String) (sec_updater), false);

    }

    public void n3_jz() {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                android_id = Settings.System.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

                String urls = AppPaths.appPath("settings/f10/set2.inf");
                urls = open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (urls));
                urls = e1.urlUnlockString(urls);

                if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                String url = urls + "api.php";

                String post_ = "req=openawg_get&token=" + android_id;

                String[] post = open.cn.awg.pro.core.TextValues.split(post_, "&", false);

                if (e1.yz()) {

                    String st2 = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (post), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                    if (java.util.Objects.equals(st2, null)) {

                    } else {

                        try {

                            String code = e1.readJson(st2, "code");

                            if (java.util.Objects.equals(code, "0")) {

                            } else {

                                isHasReq = true;
                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (ThirdPartyAuthorizationActivity.class));

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
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                if (supertip_mode == 0) {

                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.kp5)).setVisibility(android.view.View.VISIBLE);
                    open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.tx16)), R.mipmap.a17);

                } else if (supertip_mode == 1) {

                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.kp5)).setVisibility(android.view.View.VISIBLE);
                    open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.tx16)), R.mipmap.a17);

                } else if (supertip_mode == 2) {

                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.kp5)).setVisibility(android.view.View.VISIBLE);
                    open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.tx16)), R.mipmap.a28);

                } else if (supertip_mode == 3) {

                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.kp5)).setVisibility(android.view.View.VISIBLE);
                    open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.tx16)), R.mipmap.a62);

                } else if (supertip_mode == 4) {

                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.kp5)).setVisibility(android.view.View.VISIBLE);
                    open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.tx16)), R.mipmap.a17);

                } else {

                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.kp5)).setVisibility(android.view.View.GONE);
                    open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.tx16)), R.mipmap.a28);

                }

            }

        });
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb17)).setText(String.valueOf(""));
                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb17)).setVisibility(android.view.View.GONE);
                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf(t));
                ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.xdbj10)).setVisibility(android.view.View.VISIBLE);

            }

        });

        

        android.view.animation.AlphaAnimation jbtm = (android.view.animation.AlphaAnimation) (Object) new android.view.animation.AlphaAnimation(open.cn.awg.pro.core.TextValues.floatValue(1, -1), open.cn.awg.pro.core.TextValues.floatValue(0, -1));
        jbtm.setDuration(500);
        ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.xdbj2)).setVisibility(android.view.View.GONE);
        ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.xdbj2)).startAnimation(jbtm);

    }

    public void supertip_an() {

        if (supertip_mode == 0) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb17)).setText(String.valueOf("等待联网验证腕管Pro，从V4.0.0版本开始，此过程是必须的\n您可以尝试在设备联网后重启腕管Pro"));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb17)).setVisibility(android.view.View.VISIBLE);

                }

            });

        } else if (supertip_mode == 1) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb17)).setText(String.valueOf("您正在使用非官方发行的腕管Pro，可能存在未知风险\n请前往官方网站（https://awg.typheye.cn）下载安装腕管Pro"));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb17)).setVisibility(android.view.View.VISIBLE);

                }

            });

        } else if (supertip_mode == 2) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (OnboardingSetupActivity.class));

                }

            });

        } else if (supertip_mode == 3) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (UpdatePromptActivity.class));

                }

            });

        } else if (supertip_mode == 4) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb17)).setText(String.valueOf("您可能并未获得应用许可，该版本不适用于您的设备\n如您已获得应用许可，可以尝试退出应用并联网重新进入应用"));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb17)).setVisibility(android.view.View.VISIBLE);

                }

            });

        }

    }

    public void zyqd() {

        String set1 = AppPaths.appPath("settings/f21/set1.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (set1)), "1")) {

            show(1);

        } else if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (set1)), "2")) {

            show(1);
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (FileRootPickerActivity.class));

                }

            });

        } else if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (set1)), "3")) {

            show(1);
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (WearChatActivity.class));

                }

            });

        } else if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (set1)), "4")) {

            show(1);
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (ExtensionPluginActivity.class));

                }

            });

        } else if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (set1)), "5")) {

            show(1);
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (X5BrowserActivity.class));

                }

            });

        } else {

            homeshow(true);
            isNotMainOnce = false;

        }

    }

    public void homeshow_show(int i_) {

        final int i = i_;

        final android.widget.LinearLayout xxbj9 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.xxbj9));

        final android.widget.TextView wb2 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb2));
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                if (i == 0) {

                    xxbj9.setVisibility(android.view.View.VISIBLE);
                    wb2.setVisibility(android.view.View.GONE);

                } else if (i == 1) {

                    xxbj9.setVisibility(android.view.View.GONE);
                    wb2.setVisibility(android.view.View.VISIBLE);

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

            final android.widget.RelativeLayout xdbj20 = ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.xdbj20));

            final android.widget.ImageView tx11 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.tx11));

            final android.widget.TextView wb29 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb29));

            final android.widget.ImageView tx18 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.tx18));

            final android.widget.TextView wb52 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb52));

            final android.widget.TextView wb54 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb54));

            final android.widget.ImageView tx6 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.tx6));

            final android.widget.TextView wb14 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb14));

            final android.widget.TextView wb16 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb16));

            final android.widget.ImageView tx9 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.tx9));

            final android.widget.TextView wb23 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb23));

            final android.widget.TextView wb25 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb25));

            final android.widget.ImageView tx10 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.tx10));

            final android.widget.TextView wb26 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb26));

            final android.widget.TextView wb28 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb28));

            final android.widget.TextView wb31 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb31));

            final android.widget.TextView wb33 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb33));

            final android.widget.TextView wb34 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb34));

            final android.widget.TextView wb36 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb36));

            final android.widget.TextView wb37 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb37));

            final android.widget.TextView wb39 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb39));

            final android.widget.TextView wb42 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb42));

            final android.widget.TextView wb44 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb44));

            final android.widget.TextView wb45 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb45));

            final android.widget.TextView wb47 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb47));

            final android.widget.TextView wb48 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb48));

            final android.widget.TextView wb50 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb50));
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    if (t) {

                        show(2);

                    }
                    open.cn.awg.pro.core.NativeActions.sleep(500);

                    boolean canshow = false;

                    final String nopa2 = AppPaths.appPath("data/homeshow/json");

                    final String img_lbl1_path = AppPaths.appPath("data/homeshow/src/img/lbl1");

                    final String img_ad1_path = AppPaths.appPath("data/homeshow/src/img/ad1");

                    final String img_yytj_item1_path = AppPaths.appPath("data/homeshow/src/img/yytj_item1");

                    final String img_yytj_item2_path = AppPaths.appPath("data/homeshow/src/img/yytj_item2");

                    final String img_yytj_item3_path = AppPaths.appPath("data/homeshow/src/img/yytj_item3");

                    String urls = AppPaths.appPath("settings/f10/set2.inf");
                    urls = open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (urls));
                    urls = e1.urlUnlockString(urls);

                    if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

                    } else {

                        urls = urls + "/";

                    }

                    String url = urls + "homeshow.json";

                    if (e1.yz()) {

                        String st2 = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                        if (java.util.Objects.equals(st2, null)) {

                            if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (nopa2))) {

                                canshow = true;

                            }

                        } else {

                            String f1 = e1.readJson(st2, "code");

                            if (java.util.Objects.equals(f1, "200")) {

                                if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (nopa2))) {

                                    String md5a = open.cn.awg.pro.core.TextValues.md5(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (nopa2)));

                                    String md5b = open.cn.awg.pro.core.TextValues.md5(st2);

                                    if (!java.util.Objects.equals(md5a, md5b)) {

                                        open.cn.awg.pro.core.NativeFiles.delete(MainActivity.this, (String) (img_lbl1_path), false);
                                        open.cn.awg.pro.core.NativeFiles.delete(MainActivity.this, (String) (img_ad1_path), false);
                                        open.cn.awg.pro.core.NativeFiles.delete(MainActivity.this, (String) (img_yytj_item1_path), false);
                                        open.cn.awg.pro.core.NativeFiles.delete(MainActivity.this, (String) (img_yytj_item2_path), false);
                                        open.cn.awg.pro.core.NativeFiles.delete(MainActivity.this, (String) (img_yytj_item3_path), false);

                                    }

                                }
                                open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (nopa2), (String) (st2));
                                canshow = true;

                            }

                        }

                    }

                    if (canshow) {

                        final String st5 = open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (nopa2));
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

                        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (downfilepath))) {

                            if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                open.cn.awg.pro.core.NativeFiles.delete(MainActivity.this, (String) (downfilepath), false);

                            }

                        }
                        downfile = div_ad1_img_url;
                        downfilepath = img_ad1_path;

                        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (downfilepath))) {

                            if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                open.cn.awg.pro.core.NativeFiles.delete(MainActivity.this, (String) (downfilepath), false);

                            }

                        }
                        downfile = getAppIconUrl(div_yytj_item1_url);
                        downfilepath = img_yytj_item1_path;

                        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (downfilepath))) {

                            if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                open.cn.awg.pro.core.NativeFiles.delete(MainActivity.this, (String) (downfilepath), false);

                            }

                        }
                        downfile = getAppIconUrl(div_yytj_item2_url);
                        downfilepath = img_yytj_item2_path;

                        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (downfilepath))) {

                            if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                open.cn.awg.pro.core.NativeFiles.delete(MainActivity.this, (String) (downfilepath), false);

                            }

                        }
                        downfile = getAppIconUrl(div_yytj_item3_url);
                        downfilepath = img_yytj_item3_path;

                        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (downfilepath))) {

                            if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                open.cn.awg.pro.core.NativeFiles.delete(MainActivity.this, (String) (downfilepath), false);

                            }

                        }
                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                if (java.util.Objects.equals(ad_state, "true") && !e1.isJh()) {

                                    xdbj20.setVisibility(android.view.View.VISIBLE);

                                } else {

                                    xdbj20.setVisibility(android.view.View.GONE);

                                }

                                if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (img_lbl1_path))) {

                                    open.cn.awg.pro.core.NativeViewValues.image(tx11, img_lbl1_path);

                                } else {

                                    open.cn.awg.pro.core.NativeViewValues.image(tx11, R.mipmap.load_photo);

                                }
                                wb29.setText(String.valueOf(div_lbl1_text));

                                if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (img_ad1_path))) {

                                    open.cn.awg.pro.core.NativeViewValues.image(tx18, img_ad1_path);

                                } else {

                                    open.cn.awg.pro.core.NativeViewValues.image(tx18, R.mipmap.b11);

                                }
                                wb52.setText(String.valueOf(div_ad1_text));
                                wb54.setText(String.valueOf(div_ad1_subtext));

                                if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (img_yytj_item1_path))) {

                                    open.cn.awg.pro.core.NativeViewValues.image(tx6, img_yytj_item1_path);

                                } else {

                                    open.cn.awg.pro.core.NativeViewValues.image(tx6, R.mipmap.appicon);

                                }
                                wb14.setText(String.valueOf(div_yytj_item1_text));
                                wb16.setText(String.valueOf(div_yytj_item1_subtext));

                                if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (img_yytj_item2_path))) {

                                    open.cn.awg.pro.core.NativeViewValues.image(tx9, img_yytj_item2_path);

                                } else {

                                    open.cn.awg.pro.core.NativeViewValues.image(tx9, R.mipmap.appicon);

                                }
                                wb23.setText(String.valueOf(div_yytj_item2_text));
                                wb25.setText(String.valueOf(div_yytj_item2_subtext));

                                if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (img_yytj_item3_path))) {

                                    open.cn.awg.pro.core.NativeViewValues.image(tx10, img_yytj_item3_path);

                                } else {

                                    open.cn.awg.pro.core.NativeViewValues.image(tx10, R.mipmap.appicon);

                                }
                                wb26.setText(String.valueOf(div_yytj_item3_text));
                                wb28.setText(String.valueOf(div_yytj_item3_subtext));
                                wb31.setText(String.valueOf(div_jcsp_item1_text));
                                wb33.setText(String.valueOf(div_jcsp_item1_subtext));
                                wb34.setText(String.valueOf(div_jcsp_item2_text));
                                wb36.setText(String.valueOf(div_jcsp_item2_subtext));
                                wb37.setText(String.valueOf(div_jcsp_item3_text));
                                wb39.setText(String.valueOf(div_jcsp_item3_subtext));
                                wb42.setText(String.valueOf(div_rmyy_item1_text));
                                wb44.setText(String.valueOf(div_rmyy_item1_subtext));
                                wb45.setText(String.valueOf(div_rmyy_item2_text));
                                wb47.setText(String.valueOf(div_rmyy_item2_subtext));
                                wb48.setText(String.valueOf(div_rmyy_item3_text));
                                wb50.setText(String.valueOf(div_rmyy_item3_subtext));

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
        id = open.cn.awg.pro.core.TextValues.slice(id, "awg://uri/app/", null);
        return src_url + "/app/" + id + "/app-image/icon.png";

    }

    public int homeshow_filedown(Object url, Object post, Object cookie, Object path) {

        Object a = url;

        Object b = path;

        Object c = post;

        Object d = cookie;

        boolean e = true;

        String f = "User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept-language=zh-CN";
        return open.cn.awg.pro.core.NativeHttp.download(MainActivity.this, String.valueOf(a), (String) (b), java.lang.Boolean.TRUE.equals(true), (String[]) (c), (String) ("utf-8"), (String) (d), java.lang.Boolean.TRUE.equals(e), (String) (f), 0, 0, null);

    }

    public void show(int i_) {

        final String set65 = AppPaths.appPath("settings/set5.inf");

        final int i = i_;

        final android.widget.TextView tab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.title_bar));

        final android.widget.RelativeLayout xdbj1 = ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.xdbj1));

        final android.widget.RelativeLayout xdbj5 = ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.xdbj5));

        final android.widget.RelativeLayout xdbj6 = ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.xdbj6));

        final androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.qtgd1));

        final androidx.recyclerview.widget.RecyclerView v7lb1 = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.v7lb1));

        final String set1 = AppPaths.appPath("settings/f21/set1.inf");

        

        final android.view.animation.AlphaAnimation jbtm = (android.view.animation.AlphaAnimation) (Object) new android.view.animation.AlphaAnimation(open.cn.awg.pro.core.TextValues.floatValue(0, -1), open.cn.awg.pro.core.TextValues.floatValue(1, -1));
        jbtm.setDuration(300);
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                if (i == 0) {

                    qtgd1.requestFocus();
                    tab.setText(String.valueOf(open.cn.awg.pro.app.AwgProApplication.getContext().getString(R.string.app_name)));
                    xdbj1.setVisibility(android.view.View.GONE);
                    xdbj5.setVisibility(android.view.View.VISIBLE);
                    xdbj6.setVisibility(android.view.View.GONE);
                    xdbj5.startAnimation(jbtm);

                    if (isNotMainOnce && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (set1)), "0")) {

                        show(2);
                        isNotMainOnce = false;
                        homeshow(true);

                    } else {

                        homeshow(false);

                    }

                    if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (set65)), "false") || !open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set65))) {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb9)).setVisibility(android.view.View.VISIBLE);

                    }

                } else if (i == 1) {

                    v7lb1.requestFocus();
                    tab.setText(String.valueOf(" 腕管Pro "));
                    xdbj1.setVisibility(android.view.View.VISIBLE);
                    xdbj5.setVisibility(android.view.View.GONE);
                    xdbj6.setVisibility(android.view.View.GONE);
                    xdbj1.startAnimation(jbtm);

                } else if (i == 2) {

                    tab.setText(String.valueOf("  腕管Pro  "));
                    xdbj1.setVisibility(android.view.View.GONE);
                    xdbj5.setVisibility(android.view.View.GONE);
                    xdbj6.setVisibility(android.view.View.VISIBLE);
                    xdbj6.startAnimation(jbtm);

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
                android.util.Log.e("AWGPro", "Unhandled exception", e);
            }
        }
    }

    public void noticeShow() {

        if (e1.isJh()) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb11)).setVisibility(android.view.View.GONE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb13)).setVisibility(android.view.View.GONE);

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb11)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb13)).setVisibility(android.view.View.VISIBLE);

        }

        final String def = "欢迎使用腕管Pro，这是一款优秀的应用。您可以前往腕管Pro官网：https://www.typheye.cn/awg，提出建议或意见。如果您有任何问题，可以联系开发者台风眼(QQ498978473)，或者通过邮箱(498978473@qq.com)联系我们！";
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                final String nopa = AppPaths.appPath("data/notice/showing");

                final String nopa2 = AppPaths.appPath("data/notice/json");

                String nr = "";

                if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (nopa)) && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (nopa)), "")) {

                    nr = open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (nopa));

                } else {

                    nr = def;

                }

                final String nr1 = nr;
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.notice_wb)).setText(String.valueOf(nr1));

                    }

                });

                String urls = AppPaths.appPath("settings/f10/set2.inf");
                urls = open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (urls));
                urls = e1.urlUnlockString(urls);

                if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                String url = urls + "notice.json";

                final String st5 = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                if (java.util.Objects.equals(st5, null)) {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            String nr = "";

                            if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (nopa)) && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (nopa)), "")) {

                                nr = open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (nopa));

                            } else {

                                nr = def;

                            }
                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.notice_wb)).setText(String.valueOf(nr));

                        }

                    });

                } else {

                    String f1 = e1.readJson(st5, "Showing");

                    if (java.util.Objects.equals(f1, "0")) {

                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                String nr = "";

                                if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (nopa)) && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (nopa)), "")) {

                                    nr = open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (nopa));

                                } else {

                                    nr = def;

                                }
                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.notice_wb)).setText(String.valueOf(nr));

                            }

                        });

                    } else {

                        if (java.util.Objects.equals(f1, "")) {

                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    String nr = "";

                                    if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (nopa)) && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (nopa)), "")) {

                                        nr = open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (nopa));

                                    } else {

                                        nr = def;

                                    }
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.notice_wb)).setText(String.valueOf(nr));

                                }

                            });

                        } else {

                            final String nr2 = f1;
                            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (nopa), (String) (nr2));
                            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (nopa2), (String) (st5));
                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.notice_wb)).setText(String.valueOf(nr2));

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
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                e1.yz();

            }

        });

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (sec_isunlock))) {

            open.cn.awg.pro.core.NativeActions.sleep(2500);

        }

        if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (sec_isunlock))) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (sec_isunlock)), "false")) {

                supertip("应用验证未通过", 1);

            } else {

                if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (sec_license))) {

                    if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (sec_updater))) {

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

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_a0))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_a0), (String) ("0"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_a2))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_a2), (String) ("加载失败,未找到该资源!"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_a3))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_a3), (String) ("加载失败,未找到该资源!"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_a4))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_a4), (String) ("加载失败,未找到该资源!"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_a5))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_a5), (String) ("加载失败,未找到该资源!"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_a6))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_a6), (String) ("加载失败,未找到该资源!"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_a7))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_a7), (String) ("加载失败,未找到该资源!"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_a8))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_a8), (String) ("加载失败,未找到该资源!"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_a9))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_a9), (String) ("加载失败,未找到该资源!"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_a10))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_a10), (String) ("加载失败,未找到该资源!"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_a12))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_a12), (String) ("加载失败,未找到该资源!"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_a13))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_a13), (String) ("加载失败,未找到该资源!"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_a14))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_a14), (String) ("加载失败,未找到该资源!"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_a15))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_a15), (String) ("加载失败,未找到该资源!"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_a16))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_a16), (String) ("加载失败,未找到该资源!"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_a17))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_a17), (String) ("&"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_a18))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_a18), (String) ("{}"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_a19))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_a19), (String) ("加载失败,未找到该资源!"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_a20))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_a20), (String) ("加载失败,未找到该资源!"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_b0))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_b0), (String) ("加载失败,未找到该资源!"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_b1))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_b1), (String) ("加载失败,未找到该资源!"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_b2))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_b2), (String) ("加载失败,未找到该资源!"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_b3))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_b3), (String) ("加载失败,未找到该资源!"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_c1))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_c1), (String) ("echo \"加载失败,未找到该资源!\""));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (asset_e3))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (asset_e3), (String) ("加载失败,未找到该资源!"));

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

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set1))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set1), (String) ("false"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set2))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set2), (String) ("false"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set3))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set3), (String) ("false"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set4))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set4), (String) ("13"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set5))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set5), (String) ("false"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set6))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set6), (String) ("#ffffff"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set7))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set7), (String) ("#000000"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set8))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set8), (String) ("false"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set9))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set9), (String) ("false"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set10))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set10), (String) ("false"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set11))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set11), (String) ("true"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set12))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set12), (String) ("true"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set13))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set13), (String) ("awg://web.url/app/open.cn.awg.pro"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set14))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set14), (String) ("true"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set15))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set15), (String) ("awg://web.url/app/open.cn.awg.pro/talking"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set16))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set16), (String) ("腕管用户" + open.cn.awg.pro.core.NativeDevice.formatDate(4)));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set17))) {

            e11.setup_talklist();

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set18))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set18), (String) ("true"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set19))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set19), (String) ("false"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set20))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set20), (String) ("awg://thirds.url/www.baidu.com"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set21))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set21), (String) ("{\"MODE\":0,\"UA\":\"Android\"}"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set22))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set22), (String) ("true"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set23))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set23), (String) ("true"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set24))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set24), (String) ("false"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set25))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set25), (String) ("true"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set26))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set26), (String) ("false"));

        }
        open.cn.awg.pro.core.NativeFiles.delete(MainActivity.this, (String) (set27), false);
        e11.setup_filelist();

        

        int sdk = android.os.Build.VERSION.SDK_INT;

        if (open.cn.awg.pro.core.NativeActions.hasPermission(MainActivity.this, "android.permission.WRITE_EXTERNAL_STORAGE") && sdk < 30) {

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

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set29))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set29), (String) (""));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set30))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set30), (String) ("false"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set31))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set31), (String) ("false"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set32))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set32), (String) ("false"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set33))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set33), (String) (open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (AppPaths.appPath("data/assets/a18")))));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set34))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set34), (String) ("true"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set35))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set35), (String) ("https://api.sayqz.com/tunefree/ncmapi"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set36))) {

            int a11;
            a11 = com.demo.e4.getVersionCode(lei);

            String fv = a11 + "00";
            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set36), (String) (fv));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set37))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set37), (String) ("false"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set38))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set38), (String) ("false"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set39))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set39), (String) ("default"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set40))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set40), (String) ("20"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set41))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set41), (String) ("false"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set42))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set42), (String) ("true"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set43))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set43), (String) ("false"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set44))) {

            e11.setup_talklist();
            e11.add_talklist("10001", "官方聊天室", "10001-官方聊天室");
            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set44), (String) ("true"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set45))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set45), (String) ("3000"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set46))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set46), (String) ("3000"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set47))) {

            e11.setup_pluginlist();

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set48))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set48), (String) ("true"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set49))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set49), (String) ("awg://web.url/app/open.cn.awg.pro/plugin"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set50))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set50), (String) ("default"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set51))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set51), (String) ("default"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set52))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set52), (String) ("true"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set53))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set53), (String) ("default"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set54))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set54), (String) ("default"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set55))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set55), (String) ("default"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set56))) {

            e11.setup_pluginlist();

            String q = "10000";

            String w = "扩展推荐";

            boolean b3 = e11.add_pluginlist(q, w, q + "-" + w);
            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set56), (String) ("true"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set57))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set57), (String) ("https://service.typheye.cn"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set58))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set58), (String) (""));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set59))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set59), (String) ("awg://web.url/upload/up.php"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set60))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set60), (String) (""));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set61))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set61), (String) ("true"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set62))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set62), (String) (""));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set64))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set64), (String) ("true"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set65))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set65), (String) ("false"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set66))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set66), (String) ("false"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set67))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set67), (String) ("https://res.typheye.cn"));

        }

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set68))) {

            open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set68), (String) ("0"));

        }
        open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set5), (String) ("false"));
        open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set53), (String) (open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (set16))));
        vercode = com.demo.e4.getVersionCode(lei);

        String fv = String.valueOf(vercode);
        open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set0), (String) (fv));
        open.cn.awg.pro.core.NativeFiles.delete(MainActivity.this, (String) (set63), false);

        final String sec_updater = AppPaths.appPath("data/sec/updater");

        final String sec_isunlock = AppPaths.appPath("data/sec/unlock");

        final String sec_license = AppPaths.appPath("data/sec/license");

        if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (sec_updater))) {

            open.cn.awg.pro.core.NativeFiles.delete(MainActivity.this, (String) (sec_updater), false);

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

        if (!open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (a0)) || java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (set66)), "false") || !open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (set66))) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    supertip("点击按钮以继续", 2);

                }

            });

        } else {

        }

    }

    public void rjh() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                if (!e1.isJh()) {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb3)).setVisibility(android.view.View.VISIBLE);

                } else {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb3)).setVisibility(android.view.View.GONE);

                }

            }

        });

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);

        if (e1.getNowUserId() == 0) {

            final String a2 = AppPaths.appPath("settings/a3.inf");

            String a = open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (a2));

            if (java.util.Objects.equals(a, "4")) {

                if (loaded) {

                    String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTab);
                    setStatusBarColor(clr);

                } else {

                    String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTabBackground);
                    setStatusBarColor(clr);

                }

            } else {
        applyFullscreenWindow();

            }

            final String set1 = AppPaths.appPath("settings/f21/set1.inf");

            if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (AppPaths.appPath("settings/a4.inf")))) {

                if (!isNotMainOnce) {

                    homeshow(false);

                }
                noticeShow();

            }

            if (isHasReq) {

                final String ca = AppPaths.appPath("cache/req/exit");

                if (open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (ca)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (ca)), "true")) {

                    open.cn.awg.pro.core.NativeFiles.delete(MainActivity.this, (String) (ca), false);
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            android.widget.ImageView tx3 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.tx3));
                            tx3.performClick();

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

            String a = open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (a2));

            if (java.util.Objects.equals(a, "4")) {

                if (loaded) {

                    String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTab);
                    setStatusBarColor(clr);

                } else {

                    String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTabBackground);
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

            String a = open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (a2));

            if (java.util.Objects.equals(a, "4")) {

                if (loaded) {

                    String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTab);
                    setStatusBarColor(clr);

                } else {

                    String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTabBackground);
                    setStatusBarColor(clr);

                }

            } else {
        applyFullscreenWindow();

            }

        }

        androidx.recyclerview.widget.RecyclerView v7lb1 = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.v7lb1));
        v7lb1.requestFocus();

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

        if (e1.getNowUserId() == 0) {

            final String a2 = AppPaths.appPath("settings/a3.inf");

            String a = open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (a2));

            if (java.util.Objects.equals(a, "4")) {

                if (loaded) {

                    String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTab);
                    setStatusBarColor(clr);

                } else {

                    String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTabBackground);
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
            open.cn.awg.pro.core.NativeFiles.delete(MainActivity.this, (String) (AppPaths.appPath("data/AwgCoreService.r")), false);

        }

    }

    private void onTitleBarClick(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), open.cn.awg.pro.app.AwgProApplication.getContext().getString(R.string.app_name))) {

            show(1);

        } else if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), " 腕管Pro ")) {

            show(0);

        }

    }

    private void onXxbj5Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (AnnouncementActivity.class));

    }

    private void onWb7Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.xdbj7)).setVisibility(android.view.View.GONE);

            }

        });

    }

    private void onWb11Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (AppActivationActivity.class));

    }

    private void onWb2Click(View vw) {
        homeshow(true);

    }

    private void onXdbj8Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_lbl1_url};
                open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (StoreDetailActivity.class), (String[]) (iyu), (Object[]) (val));

            }

        });

    }

    private boolean onXdbj8LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb29)).getText().toString();
        e1.tsk("详细信息", te);
        return true;

    }

    private void onWb56Click(View vw) {
        e1.tsk("提示", "激活腕管Pro后可关闭所有广告");

    }

    private void onXdbj20Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_ad1_url};
                open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (StoreDetailActivity.class), (String[]) (iyu), (Object[]) (val));

            }

        });

    }

    private boolean onXdbj20LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb52)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb54)).getText().toString();
        e1.tsk("详细信息", "[推荐名称]\n" + te + "\n\n[推荐简介]\n" + lj);
        return true;

    }

    private void onXdbj9Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_yytj_item1_url};
                open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (StoreDetailActivity.class), (String[]) (iyu), (Object[]) (val));

            }

        });

    }

    private boolean onXdbj9LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb14)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb16)).getText().toString();
        e1.tsk("详细信息", "[应用名称]\n" + te + "\n\n[应用简介]\n" + lj);
        return true;

    }

    private void onXdbj12Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_yytj_item2_url};
                open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (StoreDetailActivity.class), (String[]) (iyu), (Object[]) (val));

            }

        });

    }

    private boolean onXdbj12LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb23)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb25)).getText().toString();
        e1.tsk("详细信息", "[应用名称]\n" + te + "\n\n[应用简介]\n" + lj);
        return true;

    }

    private void onXdbj13Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_yytj_item3_url};
                open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (StoreDetailActivity.class), (String[]) (iyu), (Object[]) (val));

            }

        });

    }

    private boolean onXdbj13LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb26)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb28)).getText().toString();
        e1.tsk("详细信息", "[应用名称]\n" + te + "\n\n[应用简介]\n" + lj);
        return true;

    }

    private void onWb10Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"type", "search"};

                String[] val = new String[]{"app", "all"};
                open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (StoreListActivity.class), (String[]) (iyu), (Object[]) (val));

            }

        });

    }

    private void onXdbj14Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_jcsp_item1_url};
                open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (StoreDetailActivity.class), (String[]) (iyu), (Object[]) (val));

            }

        });

    }

    private boolean onXdbj14LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb31)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb33)).getText().toString();
        e1.tsk("详细信息", "[视频名称]\n" + te + "\n\n[视频简介]\n" + lj);
        return true;

    }

    private void onXdbj15Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_jcsp_item2_url};
                open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (StoreDetailActivity.class), (String[]) (iyu), (Object[]) (val));

            }

        });

    }

    private boolean onXdbj15LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb34)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb36)).getText().toString();
        e1.tsk("详细信息", "[视频名称]\n" + te + "\n\n[视频简介]\n" + lj);
        return true;

    }

    private void onXdbj16Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_jcsp_item3_url};
                open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (StoreDetailActivity.class), (String[]) (iyu), (Object[]) (val));

            }

        });

    }

    private boolean onXdbj16LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb37)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb39)).getText().toString();
        e1.tsk("详细信息", "[视频名称]\n" + te + "\n\n[视频简介]\n" + lj);
        return true;

    }

    private void onWb40Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"type", "search"};

                String[] val = new String[]{"video", "all"};
                open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (StoreListActivity.class), (String[]) (iyu), (Object[]) (val));

            }

        });

    }

    private void onXdbj17Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_rmyy_item1_url};
                open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (StoreDetailActivity.class), (String[]) (iyu), (Object[]) (val));

            }

        });

    }

    private boolean onXdbj17LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb42)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb44)).getText().toString();
        e1.tsk("详细信息", "[歌曲名称]\n" + te + "\n\n[歌曲作者]\n" + lj);
        return true;

    }

    private void onXdbj18Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_rmyy_item2_url};
                open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (StoreDetailActivity.class), (String[]) (iyu), (Object[]) (val));

            }

        });

    }

    private boolean onXdbj18LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb45)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb47)).getText().toString();
        e1.tsk("详细信息", "[歌曲名称]\n" + te + "\n\n[歌曲作者]\n" + lj);
        return true;

    }

    private void onXdbj19Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"uri"};

                String[] val = new String[]{div_rmyy_item3_url};
                open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (StoreDetailActivity.class), (String[]) (iyu), (Object[]) (val));

            }

        });

    }

    private boolean onXdbj19LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb48)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb50)).getText().toString();
        e1.tsk("详细信息", "[歌曲名称]\n" + te + "\n\n[歌曲作者]\n" + lj);
        return true;

    }

    private void onWb51Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"type", "search"};

                String[] val = new String[]{"music", "all"};
                open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (StoreListActivity.class), (String[]) (iyu), (Object[]) (val));

            }

        });

    }

    private void onTx1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (StoreSearchActivity.class));

    }

    private void onWb13Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (AppActivationActivity.class));

    }

    private boolean onWb9LongClick(View vw) {

        String set65 = AppPaths.appPath("settings/set5.inf");
        open.cn.awg.pro.core.NativeFiles.writeText(MainActivity.this, (String) (set65), (String) ("true"));
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb9)).setVisibility(android.view.View.GONE);

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
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                android.widget.ImageView tx3 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.tx3));
                tx3.performClick();

            }

        });

    }

    private void onWb17Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.wb17)).setVisibility(android.view.View.GONE);

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

        

        final android.view.animation.AlphaAnimation jbtm = (android.view.animation.AlphaAnimation) (Object) new android.view.animation.AlphaAnimation(open.cn.awg.pro.core.TextValues.floatValue(1, -1), open.cn.awg.pro.core.TextValues.floatValue(0, -1));
        jbtm.setDuration(300);
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.TCxdbj)).setVisibility(android.view.View.GONE);
                ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.TCxdbj)).startAnimation(jbtm);

            }

        });

    }

    private void onTx3Click(View vw) {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                final android.widget.RelativeLayout main_v = ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.main_activity_root));
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        main_v.removeAllViews();

                    }

                });
                open.cn.awg.pro.core.NativeActions.sleep(500);
                open.cn.awg.pro.core.NativeFiles.delete(MainActivity.this, (String) (AppPaths.appPath("data/AwgCoreService.r")), false);

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

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        TextView e943ebd71e = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb4);

        TextView i2a86c78b06 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb5);

        TextView i78e3e43012 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.notice_wb);

        LinearLayout bd584e50cd = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj5);
        bd584e50cd.setOnClickListener(xxbj5ClickListener);

        TextView b8bc22741b = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb7);
        b8bc22741b.setOnClickListener(wb7ClickListener);

        TextView i42afe4289f = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb11);
        i42afe4289f.setOnClickListener(wb11ClickListener);

        TextView i7c3e3c6b5d = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb2);
        i7c3e3c6b5d.setOnClickListener(wb2ClickListener);

        RelativeLayout fab146d7a5 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj8);
        fab146d7a5.setOnClickListener(xdbj8ClickListener);
        fab146d7a5.setOnLongClickListener(xdbj8LongClickListener);

        TextView i9d626a4248 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb56);
        i9d626a4248.setOnClickListener(wb56ClickListener);

        RelativeLayout bab1d5f82e = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj20);
        bab1d5f82e.setOnClickListener(xdbj20ClickListener);
        bab1d5f82e.setOnLongClickListener(xdbj20LongClickListener);

        RelativeLayout i5a93676674 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj9);
        i5a93676674.setOnClickListener(xdbj9ClickListener);
        i5a93676674.setOnLongClickListener(xdbj9LongClickListener);

        RelativeLayout f73b53def9 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj12);
        f73b53def9.setOnClickListener(xdbj12ClickListener);
        f73b53def9.setOnLongClickListener(xdbj12LongClickListener);

        RelativeLayout i62f27ff563 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj13);
        i62f27ff563.setOnClickListener(xdbj13ClickListener);
        i62f27ff563.setOnLongClickListener(xdbj13LongClickListener);

        TextView i81652f77b6 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb10);
        i81652f77b6.setOnClickListener(wb10ClickListener);

        RelativeLayout c4cbe9d7ff = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj14);
        c4cbe9d7ff.setOnClickListener(xdbj14ClickListener);
        c4cbe9d7ff.setOnLongClickListener(xdbj14LongClickListener);

        RelativeLayout i6834408ef9 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj15);
        i6834408ef9.setOnClickListener(xdbj15ClickListener);
        i6834408ef9.setOnLongClickListener(xdbj15LongClickListener);

        RelativeLayout c9f5a3ee67 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj16);
        c9f5a3ee67.setOnClickListener(xdbj16ClickListener);
        c9f5a3ee67.setOnLongClickListener(xdbj16LongClickListener);

        TextView i3d78c07838 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb40);
        i3d78c07838.setOnClickListener(wb40ClickListener);

        RelativeLayout i8890b048c8 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj17);
        i8890b048c8.setOnClickListener(xdbj17ClickListener);
        i8890b048c8.setOnLongClickListener(xdbj17LongClickListener);

        RelativeLayout i11185eacd9 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj18);
        i11185eacd9.setOnClickListener(xdbj18ClickListener);
        i11185eacd9.setOnLongClickListener(xdbj18LongClickListener);

        RelativeLayout a229195608 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj19);
        a229195608.setOnClickListener(xdbj19ClickListener);
        a229195608.setOnLongClickListener(xdbj19LongClickListener);

        TextView i6a848b922c = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb51);
        i6a848b922c.setOnClickListener(wb51ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        RelativeLayout c0d3e00d2b = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj5);

        RelativeLayout aaeebbd136 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj1);

        TextView adf370a235 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb13);
        adf370a235.setOnClickListener(wb13ClickListener);

        TextView i4ba8980add = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb9);
        i4ba8980add.setOnLongClickListener(wb9LongClickListener);

        TextView f52e07ac8c = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb6);

        ImageView i7a998f9ecb = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx15);
        i7a998f9ecb.setOnClickListener(tx15ClickListener);

        RelativeLayout i171cedf1eb = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj6);

        ImageView a81b730195 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx16);
        a81b730195.setOnClickListener(tx16ClickListener);

        CardView i358e59a100 = (CardView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.kp5);

        RelativeLayout i5b34eede7f = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj10);
        i5b34eede7f.setOnTouchListener(xdbj10TouchListener);

        TextView i3a177cf5f1 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb12);
        i3a177cf5f1.setOnClickListener(wb12ClickListener);

        TextView i2318575919 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb17);
        i2318575919.setOnClickListener(wb17ClickListener);

        RelativeLayout i3167b45174 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj2);
        i3167b45174.setOnTouchListener(xdbj2TouchListener);

        RelativeLayout b0f5bad894 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.TCxdbj);
        b0f5bad894.setOnTouchListener(tCxdbjTouchListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        TextView i6a8b9facdf = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb_main_kjfs_gbcd);
        i6a8b9facdf.setOnClickListener(wbMainKjfsGbcdClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
