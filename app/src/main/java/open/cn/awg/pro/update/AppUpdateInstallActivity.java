/**
 ******************************************************************************
 * @file    AppUpdateInstallActivity.java
 * @author  Typheye
 * @brief   Downloads update packages and hands them to the installer flow.
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
package open.cn.awg.pro.update;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.file.FileManagerActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.repair.ErrorMessageActivity;
import open.cn.awg.pro.tools.ShellExecutorActivity;

public class AppUpdateInstallActivity extends BaseAwgActivity {

    public final AppUpdateInstallActivity lei = this, 类 = this;
    public AppRuntimeBridge e1;
    public String mmk = "";
    public String fixcode = "";
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        e1 = new AppRuntimeBridge(this);
        setContentView(R.layout.app_update_install);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        String t = AppUpdateInstallActivity.this.getIntent().getStringExtra(String.valueOf("url"));

        String t2 = AppUpdateInstallActivity.this.getIntent().getStringExtra(String.valueOf("mode"));
        fixcode = AppUpdateInstallActivity.this.getIntent().getStringExtra(String.valueOf("fixcode"));
        mmk = t2;
        download(t, t2);

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        

        applyStandardScreenMode(R.id.app_update_install_root, R.id.xdbj1, R.id.title_bar);
        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(AppUpdateInstallActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(AppUpdateInstallActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }
        open.cn.awg.pro.core.NativeDevice.setKeepScreenOn(AppUpdateInstallActivity.this, !(false));

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(AppUpdateInstallActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(AppUpdateInstallActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));

        if (java.util.Objects.equals(a, "4")) {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.app_update_install_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(true));

            String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTab);
            setStatusBarColor(clr);

        } else {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.app_update_install_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

        }

        if (java.util.Objects.equals(a, "1")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.xdbj1)), u, 0, u, 50);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.title_bar));
            // 应用栏接管标题排版，移除旧运行时 padding
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "2")) {

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (open.cn.awg.pro.core.NativeFiles.exists(AppUpdateInstallActivity.this, (String) (a001)) || java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(AppUpdateInstallActivity.this, (String) (a001)), "true")) {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.title_bar));
                // 应用栏接管标题排版，移除旧运行时 padding
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            } else {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.title_bar));
                // 应用栏接管标题排版，移除旧运行时 padding
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            }
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.xdbj1)), 0, 0, 0, 0);

        } else if (java.util.Objects.equals(a, "3")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.xdbj1)), u, 0, u, 50);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.title_bar));
            // 应用栏接管标题排版，移除旧运行时 padding
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "4")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.xdbj1)), 0, 0, 0, 0);

        }

    }

    public void download(Object lj, Object m) {

        final String a2 = AppPaths.appPath("settings/a3.inf");

        final String as1 = open.cn.awg.pro.core.NativeFiles.readText(AppUpdateInstallActivity.this, (String) (a2));

        final String dl = lj.toString();

        final Object mm = m;
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.kp1)).setVisibility(android.view.View.GONE);
                        ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.kp2)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.show)).setText(String.valueOf("加载中"));

                    }

                });
                open.cn.awg.pro.core.NativeActions.sleep(1000);
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.show)).setText(String.valueOf("清理旧数据"));

                        String d = AppPaths.externalFilesPath("update/");

                        String m = "rm -rf \"" + d + "\"";
                        com.demo.e3.cmd(lei, m, false);
                        d = AppPaths.appPath("fix/");
                        m = "rm -rf \"" + d + "\"";
                        com.demo.e3.cmd(lei, m, false);

                    }

                });
                open.cn.awg.pro.core.NativeActions.sleep(500);
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.show)).setText(String.valueOf("下载更新"));
                        ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.kp2)).setVisibility(android.view.View.VISIBLE);

                    }

                });
                open.cn.awg.pro.core.NativeActions.sleep(500);

                String a = dl;

                String bb = "";

                if (java.util.Objects.equals(mm, "update")) {

                    bb = AppPaths.externalFilesPath("update/update.apk");

                } else if (java.util.Objects.equals(mm, "fix")) {

                    bb = AppPaths.appPath("fix/fix.sh");

                }

                final String b = bb;

                int dg = open.cn.awg.pro.core.NativeHttp.download(AppUpdateInstallActivity.this, String.valueOf(a), (String) (b), java.lang.Boolean.TRUE.equals(true), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), (String) (null), 0, 0, null);

                String bk = "";

                if (dg == 0) {

                    bk = "下载成功";

                } else if (dg == 1) {

                    bk = "已下载";

                } else if (dg == -1) {

                    bk = "下载失败";

                } else {

                    bk = "未知错误";

                }

                final String df = bk;
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.show)).setText(String.valueOf(df));
                        ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.kp2)).setVisibility(android.view.View.GONE);

                    }

                });
                open.cn.awg.pro.core.NativeActions.sleep(1000);

                if (dg == 0 || dg == 1) {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.show)).setText(String.valueOf("准备安装"));

                        }

                    });
                    open.cn.awg.pro.core.NativeActions.sleep(500);
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.show)).setText(String.valueOf("等待安装"));

                        }

                    });
                    open.cn.awg.pro.core.NativeActions.sleep(500);
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.kp2)).setVisibility(android.view.View.VISIBLE);
                            ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.kp1)).setVisibility(android.view.View.VISIBLE);

                        }

                    });

                    if (java.util.Objects.equals(mm, "update")) {

                        load();

                    } else if (java.util.Objects.equals(mm, "fix")) {

                        load2();

                    }

                } else {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.show)).setText(String.valueOf("取消更新"));
                            ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.kp2)).setVisibility(android.view.View.GONE);

                        }

                    });
                    open.cn.awg.pro.core.NativeActions.sleep(500);
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            open.cn.awg.pro.core.NativeActions.finish(AppUpdateInstallActivity.this);

                        }

                    });

                }

            }

        });

    }

    public void load() {

        String ftitle = AppPaths.appPath("cache/file/title");
        open.cn.awg.pro.core.NativeFiles.writeText(AppUpdateInstallActivity.this, (String) (ftitle), (String) ("更新目录"));

        String path = AppPaths.externalFilesPath("update/");

        final String[] name = new String[]{"url", "root"};

        final String[] value = new String[]{path, path};
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.launch(AppUpdateInstallActivity.this, (Class<?>) (FileManagerActivity.class), (String[]) (name), (Object[]) (value));

            }

        });

    }

    public void load2() {

        String bb = AppPaths.appPath("fix/fix.sh");

        String nrs = open.cn.awg.pro.core.NativeFiles.readText(AppUpdateInstallActivity.this, (String) (bb));

        final String[] name = new String[]{"sh", "auto", "fixcode", "type"};

        final String[] value = new String[]{nrs, "enable", fixcode, "fixupdate"};
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.launch(AppUpdateInstallActivity.this, (Class<?>) (ShellExecutorActivity.class), (String[]) (name), (Object[]) (value));

            }

        });
        open.cn.awg.pro.core.NativeActions.finish(AppUpdateInstallActivity.this);

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

        }
        return false;

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
            applyWindowModeFromSettings();

    }

    public void onRestart() {
        super.onRestart();
            applyWindowModeFromSettings();

    }

    public void onStart() {
        super.onStart();
            applyWindowModeFromSettings();

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onTx2Click(View vw) {

        if (((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateInstallActivity.this.getWindow().getDecorView(), R.id.kp1)).getVisibility() == android.view.View.VISIBLE) {

            open.cn.awg.pro.core.NativeActions.finish(AppUpdateInstallActivity.this);
            e1.tsk("提示", "您已取消更新");

        } else {

            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            String[] name = new String[]{"Msg"};

                            String[] value = new String[]{"中断成功 点击重启"};
                            open.cn.awg.pro.core.NativeActions.launch(AppUpdateInstallActivity.this, (Class<?>) (ErrorMessageActivity.class), (String[]) (name), (Object[]) (value));

                        }

                    });

                }

            });

        }

    }

    private void onTx1Click(View vw) {

        if (java.util.Objects.equals(mmk, "update")) {

            load();

        } else if (java.util.Objects.equals(mmk, "fix")) {

            load2();

        }

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);

        __layoutIsLoaded(ay, vw);
    }
}
