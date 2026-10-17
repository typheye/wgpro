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
    public boolean isShow = true;
    public boolean loaded = false;
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

    /** 主页底部的「应用未激活」提示：激活状态变了就更新。 */
    private void updateActivationBadge() {
        android.widget.TextView wb13 = findViewById(R.id.wb13);
        if (wb13 == null) {
            return;
        }
        wb13.setVisibility(e1.isJh() ? View.GONE : View.VISIBLE);
    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        // 圆屏自适应：给主页内容容器加左右安全边距，避免卡片四角被圆边裁切（方屏为 0）
        int roundInset = open.cn.awg.pro.core.ScreenShape.roundInset(MainActivity.this);
        if (roundInset > 0) {
            android.view.View xdbj1v = open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.xdbj1);
            if (xdbj1v != null) xdbj1v.setPadding(roundInset, 0, roundInset, 0);
        }

        // 顶栏左右 Action / 标题按屏幕形状收进安全区
        android.view.View appBarV = open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.app_bar);
        if (appBarV != null) {
            open.cn.awg.pro.core.ScreenShape.applyAppBarInsets(MainActivity.this, appBarV);
        }

        // 毛玻璃以九宫格列表为源（原来 show() 里按信息流/菜单切换，现在主页只有九宫格）
        android.view.View blurV = open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.blur_bar);
        android.view.View gridV = open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.v7lb1);
        if (blurV instanceof open.cn.awg.pro.ui.widget.BlurBarView && gridV != null) {
            ((open.cn.awg.pro.ui.widget.BlurBarView) blurV).setSource(gridV);
        }
        updateActivationBadge();

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


                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.title_bar));
                // 应用栏自行排版标题，运行时不再改标题 padding
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
                    // 应用栏自行排版标题
                    wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                    android.widget.TextView wtab2 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.Title2));
                    open.cn.awg.pro.core.NativeViewValues.padding(wtab2, 0, "3dp", 0, "3dp");
                    wtab2.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                } else {

                    android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.title_bar));
                    // 应用栏自行排版标题
                    wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                    android.widget.TextView wtab2 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.Title2));
                    open.cn.awg.pro.core.NativeViewValues.padding(wtab2, 0, "9dp", 0, "9dp");
                    wtab2.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                }

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


                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.title_bar));
                // 应用栏自行排版标题，运行时不再改标题 padding
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

        // 主页固定为九宫格：返回键直接弹退出确认（原先是「信息流 <-> 菜单」切换）
        if (kc == 4
                && ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.xdbj10)).getVisibility() == android.view.View.GONE
                && ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.xdbj2)).getVisibility() == android.view.View.GONE
                && ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.TCxdbj)).getVisibility() == android.view.View.GONE) {

            final android.view.animation.AlphaAnimation jbtm = (android.view.animation.AlphaAnimation) (Object) new android.view.animation.AlphaAnimation(open.cn.awg.pro.core.TextValues.floatValue(0, -1), open.cn.awg.pro.core.TextValues.floatValue(1, -1));
            jbtm.setDuration(300);
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.TCxdbj)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(MainActivity.this.getWindow().getDecorView(), R.id.TCxdbj)).startAnimation(jbtm);

                }

            });
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

        // 启动页设置：1/默认 = 主页九宫格；2~5 启动后直接进对应页面。
        String set1 = AppPaths.appPath("settings/f21/set1.inf");
        String v = open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (set1));

        if (java.util.Objects.equals(v, "2")) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (FileRootPickerActivity.class));

                }

            });

        } else if (java.util.Objects.equals(v, "3")) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (WearChatActivity.class));

                }

            });

        } else if (java.util.Objects.equals(v, "4")) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (ExtensionPluginActivity.class));

                }

            });

        } else if (java.util.Objects.equals(v, "5")) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(MainActivity.this, (Class<?>) (X5BrowserActivity.class));

                }

            });

        }
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

        // 新版初始化标志读 SharedPreferences；同时兼容旧的 a4/set6 文件
        boolean setupDone = open.cn.awg.pro.core.AppPrefs.isDeviceSetupDone(MainActivity.this)
                || open.cn.awg.pro.core.NativeFiles.exists(MainActivity.this, (String) (a0));
        boolean onboardingDone = open.cn.awg.pro.core.AppPrefs.isOnboardingDone(MainActivity.this)
                || java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(MainActivity.this, (String) (set66)), "true");

        if (!setupDone || !onboardingDone) {

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

            // 信息流/homeshow 已移除：主页只保留九宫格，这里不再做任何刷新

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

        // 主页只有九宫格：滚动容器 v7lb1 已在上方 requestFocus，无需再处理信息流滚动


    }

    public void onResume() {
        super.onResume();

        updateActivationBadge();

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

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        View root = (vw == null ? ay.getWindow().getDecorView() : vw);

        TextView wb13 = root.findViewById(R.id.wb13);
        if (wb13 != null) wb13.setOnClickListener(wb13ClickListener);

        TextView wb9 = root.findViewById(R.id.wb9);
        if (wb9 != null) wb9.setOnLongClickListener(wb9LongClickListener);

        ImageView tx16 = root.findViewById(R.id.tx16);
        if (tx16 != null) tx16.setOnClickListener(tx16ClickListener);

        RelativeLayout xdbj10 = root.findViewById(R.id.xdbj10);
        if (xdbj10 != null) xdbj10.setOnTouchListener(xdbj10TouchListener);

        TextView wb12 = root.findViewById(R.id.wb12);
        if (wb12 != null) wb12.setOnClickListener(wb12ClickListener);

        TextView wb17 = root.findViewById(R.id.wb17);
        if (wb17 != null) wb17.setOnClickListener(wb17ClickListener);

        RelativeLayout xdbj2 = root.findViewById(R.id.xdbj2);
        if (xdbj2 != null) xdbj2.setOnTouchListener(xdbj2TouchListener);

        RelativeLayout tCxdbj = root.findViewById(R.id.TCxdbj);
        if (tCxdbj != null) tCxdbj.setOnTouchListener(tCxdbjTouchListener);

        ImageView tx2 = root.findViewById(R.id.tx2);
        if (tx2 != null) tx2.setOnClickListener(tx2ClickListener);

        ImageView tx3 = root.findViewById(R.id.tx3);
        if (tx3 != null) tx3.setOnClickListener(tx3ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
