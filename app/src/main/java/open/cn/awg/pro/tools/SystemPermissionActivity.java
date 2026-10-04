/**
 ******************************************************************************
 * @file    SystemPermissionActivity.java
 * @author  Typheye
 * @brief   Manages device-policy and privileged system permission actions.
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
package open.cn.awg.pro.tools;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.browser.X5BrowserActivity;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.core.DevicePolicyBridge;
import open.cn.awg.pro.file.FileManagerActivity;
import open.cn.awg.pro.media.VideoPlayerActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.repair.ErrorMessageActivity;
import open.cn.awg.pro.store.ProductPurchaseActivity;
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;
import open.cn.awg.pro.viewer.TextViewerActivity;

public class SystemPermissionActivity extends BaseAwgActivity {

    public final SystemPermissionActivity lei = this, 类 = this;
    public String lin;
    public String ujback;
    public AppRuntimeBridge e1;
    public DevicePolicyBridge carton;
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnLongClickListener backsLongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onBacksLongClick(vw);
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
    private final View.OnTouchListener bjk1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjk1Touch(vw, me);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        e1 = new AppRuntimeBridge(this);
        carton = new DevicePolicyBridge(this);
        setContentView(R.layout.system_permission);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(SystemPermissionActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(SystemPermissionActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(SystemPermissionActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(SystemPermissionActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));

        if (java.util.Objects.equals(a, "4")) {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.system_permission_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(true));

            String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTab);
            setStatusBarColor(clr);

        } else {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.system_permission_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

        }

        if (java.util.Objects.equals(a, "1")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.xdbj1)), u, 0, u, 50);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.title_bar));
            open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "15dp", 0, "3dp");
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "2")) {

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (open.cn.awg.pro.core.NativeFiles.exists(SystemPermissionActivity.this, (String) (a001)) || java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(SystemPermissionActivity.this, (String) (a001)), "true")) {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.title_bar));
                open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "3dp", 0, "3dp");
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            } else {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.title_bar));
                open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "9dp", 0, "9dp");
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            }
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.xdbj1)), 0, 0, 0, 0);

        } else if (java.util.Objects.equals(a, "3")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.xdbj1)), u, 0, u, 50);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.title_bar));
            open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "15dp", 0, "3dp");
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "4")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.xdbj1)), 0, 0, 0, 0);

        }

        android.widget.CheckBox dx1 = ((android.widget.CheckBox) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.dx1));
        dx1.setChecked(java.lang.Boolean.TRUE.equals(true));

        String set = AppPaths.appPath("settings/set3.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(SystemPermissionActivity.this, (String) (set)), "true")) {

            String te = open.cn.awg.pro.core.NativeFiles.readText(SystemPermissionActivity.this, (String) (AppPaths.appPath("data/assets/a9")));

            String[] name = new String[]{"title", "text"};

            String[] value = new String[]{"新手教程", te};
            open.cn.awg.pro.core.NativeActions.launch(SystemPermissionActivity.this, (Class<?>) (TextQrDisplayActivity.class), (String[]) (name), (Object[]) (value));

        }

    }

    public void run(boolean i) {

        final android.widget.TextView backinfo = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.backs));

        final android.widget.LinearLayout cmdlin = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.xxbj1));

        final android.widget.LinearLayout cmdbac = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.xxbj2));
        lin = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.bjk1)).getText().toString();
        lin = open.cn.awg.pro.core.TextValues.trim(lin);

        if (java.util.Objects.equals(lin, "")) {

            e1.tsk("提示", "命令行不能为空");

        } else {

            try {

                if (open.cn.awg.pro.core.TextValues.startsWith(lin, "dkplayer load ")) {

                    String url = open.cn.awg.pro.core.TextValues.slice(lin, "dkplayer load ", null);

                    String[] name = new String[]{"url"};

                    String[] value = new String[]{url};
                    open.cn.awg.pro.core.NativeActions.launch(SystemPermissionActivity.this, (Class<?>) (VideoPlayerActivity.class), (String[]) (name), (Object[]) (value));
                    bak(true);

                } else if (open.cn.awg.pro.core.TextValues.startsWith(lin, "x5web load ")) {

                    String url = open.cn.awg.pro.core.TextValues.slice(lin, "x5web load ", null);

                    final String[] name = new String[]{"url"};

                    final String[] value = new String[]{url};
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            open.cn.awg.pro.core.NativeActions.launch(SystemPermissionActivity.this, (Class<?>) (X5BrowserActivity.class), (String[]) (name), (Object[]) (value));

                        }

                    });
                    bak(true);

                } else if (open.cn.awg.pro.core.TextValues.startsWith(lin, "paytest -p ")) {

                    String _money = open.cn.awg.pro.core.TextValues.slice(lin, "paytest -p ", null);

                    final String[] name = new String[]{"id", "name", "jg", "text"};

                    final String[] value = new String[]{"a2", "腕管Pro-PayTest", _money, "腕管Pro：PayTest。"};
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            open.cn.awg.pro.core.NativeActions.launch(SystemPermissionActivity.this, (Class<?>) (ProductPurchaseActivity.class), (String[]) (name), (Object[]) (value));

                        }

                    });
                    bak(true);

                } else if (open.cn.awg.pro.core.TextValues.startsWith(lin, "file2 load ")) {

                    String url = open.cn.awg.pro.core.TextValues.slice(lin, "file2 load ", " -rp ");

                    String root = open.cn.awg.pro.core.TextValues.slice(lin, " -rp ", null);
                    url = open.cn.awg.pro.core.TextValues.trim(url);
                    root = open.cn.awg.pro.core.TextValues.trim(root);

                    String in = AppPaths.appPath("");

                    if (open.cn.awg.pro.core.TextValues.startsWith(url, in)) {

                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                String po = AppPaths.appPath("");

                                String[] name = new String[]{"url", "root"};

                                String[] value = new String[]{po, po};
                                open.cn.awg.pro.core.NativeActions.launch(SystemPermissionActivity.this, (Class<?>) (FileManagerActivity.class), (String[]) (name), (Object[]) (value));

                            }

                        });

                    } else {

                        final String[] name = new String[]{"url", "root"};

                        final String[] value = new String[]{url, root};
                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                open.cn.awg.pro.core.NativeActions.launch(SystemPermissionActivity.this, (Class<?>) (FileManagerActivity.class), (String[]) (name), (Object[]) (value));

                            }

                        });

                    }
                    bak(true);

                } else if (java.util.Objects.equals(lin, "awg open beta")) {

                    String set1 = AppPaths.appPath("settings/set1.inf");
                    open.cn.awg.pro.core.NativeFiles.writeText(SystemPermissionActivity.this, (String) (set1), (String) ("true"));
                    bak_s("成功开启实验性功能", true);

                } else if (java.util.Objects.equals(lin, "awg close beta")) {

                    String set1 = AppPaths.appPath("settings/set1.inf");
                    open.cn.awg.pro.core.NativeFiles.writeText(SystemPermissionActivity.this, (String) (set1), (String) ("false"));
                    bak_s("成功关闭实验性功能", true);

                } else if (java.util.Objects.equals(lin, "help")) {

                    final String[] name = new String[]{"url", "title", "code"};

                    final String[] value = new String[]{AppPaths.appPath("data/assets/b2"), "功能帮助", "super1"};
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            open.cn.awg.pro.core.NativeActions.launch(SystemPermissionActivity.this, (Class<?>) (TextViewerActivity.class), (String[]) (name), (Object[]) (value));

                        }

                    });
                    bak(true);

                } else if (java.util.Objects.equals(lin, "edit end")) {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            open.cn.awg.pro.core.NativeActions.launch(SystemPermissionActivity.this, (Class<?>) (TerminalCompatibilityActivity.class));

                        }

                    });
                    bak(true);

                } else if (java.util.Objects.equals(lin, "awg activate")) {

                    if (e1.isJh()) {

                        bak(false);

                    } else {

                        e1.jh();
                        bak(true);

                    }

                } else if (java.util.Objects.equals(lin, "error")) {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            String[] name = new String[]{"Error"};

                            String[] value = new String[]{"0xffff"};
                            open.cn.awg.pro.core.NativeActions.launch(SystemPermissionActivity.this, (Class<?>) (ErrorMessageActivity.class), (String[]) (name), (Object[]) (value));

                        }

                    });
                    bak(true);

                } else if (open.cn.awg.pro.core.TextValues.startsWith(lin, "deviceManger ")) {

                    cartoncmd(lin);

                } else if (java.util.Objects.equals(lin, "su") || java.util.Objects.equals(lin, "sh") || java.util.Objects.equals(lin, "shell")) {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            open.cn.awg.pro.core.NativeActions.launch(SystemPermissionActivity.this, (Class<?>) (ShellExecutorActivity.class));

                        }

                    });
                    bak(true);

                } else {

                    bak_s("未找到该指令", false);

                }

            } catch (Throwable e) {

                bak_s(e, false);

            }

        }

    }

    public void urljson(Object lables, Object set2e) {

        final Object url = set2e;

        final Object lable = lables;
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                String setback = "";

                String data = "";

                String[] wlhq1 = new String[]{"name1=value1", "name2=value2"};

                String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (wlhq1), (String) ("utf-8"), (String) ("uid=123;name=123;"), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) ("User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept=*/*||accept-language=zh-CN")), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                    setback = "0";

                } else {

                    if (java.util.Objects.equals(setback, "0")) {

                        setback = "0";

                    } else {

                        try {

                            org.json.JSONObject jo = (org.json.JSONObject) (Object) open.cn.awg.pro.core.JsonValues.parseObject(back);

                            JSONObject json = jo;

                            JSONArray list = open.cn.awg.pro.core.JsonValues.array(json, "data");

                            int size = open.cn.awg.pro.core.JsonValues.length(list);

                            while (size > 0) {

                                size = size - 1;

                                JSONObject dx = open.cn.awg.pro.core.JsonValues.object(list, size);
                                data = String.valueOf(open.cn.awg.pro.core.JsonValues.value(dx, lable));

                            }

                        } catch (Throwable e) {

                            data = "0";

                        }
                        setback = data;

                    }

                }
                ujback = setback;
                bak_s(setback, true);

            }

        });

    }

    public void cartoncmd(Object i) {

        if (java.util.Objects.equals(i, "deviceManger get")) {

            carton.gets(lei);
            bak(true);

        } else if (open.cn.awg.pro.core.TextValues.startsWith(i, "deviceManger setCameraDisabled")) {

            if (java.util.Objects.equals(i, "deviceManger setCameraDisabled true")) {

                carton.setCameraDisabled(lei, true);
                bak(true);

            } else if (java.util.Objects.equals(i, "deviceManger setCameraDisabled false")) {

                carton.setCameraDisabled(lei, false);
                bak(true);

            } else {

                bak(false);

            }

        } else if (open.cn.awg.pro.core.TextValues.startsWith(i, "deviceManger setStatusBarDisabled")) {

            if (java.util.Objects.equals(i, "deviceManger setStatusBarDisabled true")) {

                carton.setStatusBarDisabled(lei, true);
                bak(true);

            } else if (java.util.Objects.equals(i, "deviceManger setStatusBarDisabled false")) {

                carton.setStatusBarDisabled(lei, false);
                bak(true);

            } else {

                bak(false);

            }

        } else if (java.util.Objects.equals(i, "deviceManger lockNow")) {

            carton.lockNow(lei);
            bak(true);

        } else if (java.util.Objects.equals(i, "deviceManger wipeData")) {

            carton.wipeData(lei);
            bak(true);

        } else if (java.util.Objects.equals(i, "deviceManger reboot")) {

            carton.reboot(lei);
            bak(true);

        } else if (java.util.Objects.equals(i, "deviceManger isDOA")) {

            boolean e0 = carton.isDeviceOwnerApp(lei);

            boolean e = e0;
            bak_s(e, true);

        } else if (open.cn.awg.pro.core.TextValues.startsWith(i, "deviceManger setUninstallBlocked")) {

            if (open.cn.awg.pro.core.TextValues.endsWith(i, " true")) {

                if (open.cn.awg.pro.core.TextValues.startsWith(i, "deviceManger setUninstallBlocked \"") && open.cn.awg.pro.core.TextValues.endsWith(i, "\" true")) {

                    String apk = open.cn.awg.pro.core.TextValues.slice(i, "setUninstallBlocked \"", "\" true");
                    carton.setUninstallBlocked(lei, apk, true);
                    bak(true);

                } else {

                    bak(false);

                }

            } else if (open.cn.awg.pro.core.TextValues.endsWith(i, "false")) {

                if (open.cn.awg.pro.core.TextValues.startsWith(i, "deviceManger setUninstallBlocked \"") && open.cn.awg.pro.core.TextValues.endsWith(i, "\" false")) {

                    String apk = open.cn.awg.pro.core.TextValues.slice(i, "setUninstallBlocked \"", "\" false");
                    carton.setUninstallBlocked(lei, apk, false);
                    bak(true);

                } else {

                    bak(false);

                }

            } else {

                bak(false);

            }

        } else if (open.cn.awg.pro.core.TextValues.startsWith(i, "deviceManger setDeviceOwnerLockScreenInfo")) {

            if (open.cn.awg.pro.core.TextValues.startsWith(i, "deviceManger setDeviceOwnerLockScreenInfo \"") && open.cn.awg.pro.core.TextValues.endsWith(i, "\"")) {

                String text = open.cn.awg.pro.core.TextValues.slice(i, "setDeviceOwnerLockScreenInfo \"", "\"");
                carton.setDeviceOwnerLockScreenInfo(lei, text);
                bak(true);

            } else {

                bak(false);

            }

        } else if (open.cn.awg.pro.core.TextValues.startsWith(i, "deviceManger setOrganizationName")) {

            if (open.cn.awg.pro.core.TextValues.startsWith(i, "deviceManger setOrganizationName \"") && open.cn.awg.pro.core.TextValues.endsWith(i, "\"")) {

                String text = open.cn.awg.pro.core.TextValues.slice(i, "setOrganizationName \"", "\"");
                carton.setOrganizationName(lei, text);
                bak(true);

            } else {

                bak(false);

            }

        } else {

            bak_s("未找到该指令", false);

        }

    }

    public void bak(boolean i) {

        final android.widget.TextView backinfo = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.backs));

        final android.widget.LinearLayout cmdlin = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.xxbj1));

        final android.widget.LinearLayout cmdbac = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.xxbj2));

        String color = "";

        String z = "";

        if (i) {

            z = "运行成功";
            color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTextTrue);

        } else {

            z = "运行失败";
            color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTextFalse);

        }

        final String zs = z;

        final String colors = color;
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                backinfo.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(colors));
                backinfo.setText(String.valueOf(zs));
                cmdbac.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                cmdlin.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

            }

        });

    }

    public void bak_s(Object z, boolean i) {

        final Object zs = z;

        final android.widget.TextView backinfo = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.backs));

        final android.widget.LinearLayout cmdlin = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.xxbj1));

        final android.widget.LinearLayout cmdbac = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.xxbj2));

        String color = "";

        if (i) {

            color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTextTrue);

        } else {

            color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTextFalse);

        }

        final String colors = color;
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                backinfo.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(colors));
                backinfo.setText(String.valueOf(zs));
                cmdbac.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                cmdlin.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

            }

        });

    }

    public void onRestart() {
        super.onRestart();
            applyWindowModeFromSettings();

    }

    public void onStart() {
        super.onStart();
            applyWindowModeFromSettings();

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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
            applyWindowModeFromSettings();

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
            applyWindowModeFromSettings();

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

        }
        return false;

    }

    private boolean onBjk1Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private void onTx2Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(SystemPermissionActivity.this);

    }

    private void onTx3Click(View vw) {

        android.widget.CheckBox dx1 = ((android.widget.CheckBox) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.dx1));

        run(!dx1.isChecked());

    }

    private boolean onBacksLongClick(View vw) {

        String wb1 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.backs)).getText().toString();
        e1.tsk("详细信息", wb1);
        return true;

    }

    private void onTx1Click(View vw) {

        final android.widget.LinearLayout cmdlin = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.xxbj1));

        final android.widget.LinearLayout cmdbac = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(SystemPermissionActivity.this.getWindow().getDecorView(), R.id.xxbj2));
        cmdbac.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
        cmdlin.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);

        EditText i32ee139ba1 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        CheckBox i3d918d2dcb = (CheckBox) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.dx1);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        TextView i50b5d2ce90 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.backs);
        i50b5d2ce90.setOnLongClickListener(backsLongClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
