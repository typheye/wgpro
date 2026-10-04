/**
 ******************************************************************************
 * @file    DeleteConfirmActivity.java
 * @author  Typheye
 * @brief   Confirms file or directory deletion before executing it.
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
package open.cn.awg.pro.file;


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
import open.cn.awg.pro.R;

public class DeleteConfirmActivity extends BaseAwgActivity {

    public final DeleteConfirmActivity lei = this, 类 = this;
    public String b = "";
    public String c = "";
    public String d = "";
    public String es = "";
    public AppRuntimeBridge e1;
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        e1 = new AppRuntimeBridge(this);
        setContentView(R.layout.delete_confirm);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        b = DeleteConfirmActivity.this.getIntent().getStringExtra(String.valueOf("b"));
        c = DeleteConfirmActivity.this.getIntent().getStringExtra(String.valueOf("c"));
        d = DeleteConfirmActivity.this.getIntent().getStringExtra(String.valueOf("d"));
        es = DeleteConfirmActivity.this.getIntent().getStringExtra(String.valueOf("es"));

        String lx = "";

        if (java.util.Objects.equals(b, "2")) {

            lx = "文件";

        } else {

            lx = "目录";

        }
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeleteConfirmActivity.this.getWindow().getDecorView(), R.id.wb1)).setText(String.valueOf("您确定要删除“" + c + "”" + lx + "吗？"));

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(DeleteConfirmActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(DeleteConfirmActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(DeleteConfirmActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(DeleteConfirmActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));

        if (java.util.Objects.equals(a, "4")) {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeleteConfirmActivity.this.getWindow().getDecorView(), R.id.delete_confirm_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(true));

            String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTab);
            setStatusBarColor(clr);

        } else {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeleteConfirmActivity.this.getWindow().getDecorView(), R.id.delete_confirm_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

        }

        if (java.util.Objects.equals(a, "1")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeleteConfirmActivity.this.getWindow().getDecorView(), R.id.xdbj1)), u, 0, u, 60);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeleteConfirmActivity.this.getWindow().getDecorView(), R.id.title_bar));
            open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "15dp", 0, "3dp");
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "2")) {

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (open.cn.awg.pro.core.NativeFiles.exists(DeleteConfirmActivity.this, (String) (a001)) || java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(DeleteConfirmActivity.this, (String) (a001)), "true")) {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeleteConfirmActivity.this.getWindow().getDecorView(), R.id.title_bar));
                open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "3dp", 0, "3dp");
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            } else {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeleteConfirmActivity.this.getWindow().getDecorView(), R.id.title_bar));
                open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "9dp", 0, "9dp");
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            }
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeleteConfirmActivity.this.getWindow().getDecorView(), R.id.xdbj1)), 0, 0, 0, 0);

        } else if (java.util.Objects.equals(a, "3")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeleteConfirmActivity.this.getWindow().getDecorView(), R.id.xdbj1)), u, 0, u, 60);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeleteConfirmActivity.this.getWindow().getDecorView(), R.id.title_bar));
            open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "15dp", 0, "3dp");
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "4")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeleteConfirmActivity.this.getWindow().getDecorView(), R.id.xdbj1)), 0, 0, 0, 0);

        }

    }

    public void tip() {

        String cache_c = AppPaths.appPath("cache/c");

        String cache_es = AppPaths.appPath("cache/es");
        open.cn.awg.pro.core.NativeFiles.writeText(DeleteConfirmActivity.this, (String) (cache_c), (String) (c));
        open.cn.awg.pro.core.NativeFiles.writeText(DeleteConfirmActivity.this, (String) (cache_es), (String) (es));

    }

    public void no() {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeleteConfirmActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeleteConfirmActivity.this.getWindow().getDecorView(), R.id.xxbj3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                    }

                });
                tip();
                open.cn.awg.pro.core.NativeActions.sleep(250);
                open.cn.awg.pro.core.NativeActions.finish(DeleteConfirmActivity.this);

            }

        });

    }

    public void yes() {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeleteConfirmActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeleteConfirmActivity.this.getWindow().getDecorView(), R.id.xxbj3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                    }

                });
                tip();
                open.cn.awg.pro.core.NativeActions.sleep(250);

                String[] aa;

                String pb1 = AppPaths.appPath("settings");

                String pb2 = AppPaths.appPath("data");

                String pb3 = AppPaths.appPath("fix");

                String pb4 = AppPaths.appPath("cache");

                String pb5 = AppPaths.appPath("files/cache");

                String pb6 = "/storage/emulated/0/Android/data";

                String pb7 = "/storage/emulated/0/Android/obb";

                if (open.cn.awg.pro.core.TextValues.startsWith(c, pb1) || open.cn.awg.pro.core.TextValues.startsWith(c, pb2) || open.cn.awg.pro.core.TextValues.startsWith(c, pb3) || open.cn.awg.pro.core.TextValues.startsWith(c, pb4) || open.cn.awg.pro.core.TextValues.startsWith(c, pb5) || (android.os.Build.VERSION.SDK_INT > 29 && open.cn.awg.pro.core.TextValues.startsWith(c, pb6)) || (android.os.Build.VERSION.SDK_INT > 29 && open.cn.awg.pro.core.TextValues.startsWith(c, pb7))) {

                    e1.tsk("提示", "非法操作");

                } else {

                    if (java.util.Objects.equals(b, "2")) {

                        if (open.cn.awg.pro.core.NativeFiles.delete(DeleteConfirmActivity.this, (String) (d), false) == 1) {

                            final String z = "删除成功";

                        } else {

                            final String z = "删除失败";
                            e1.tsk("提示", z);

                        }

                    } else {

                        String m = "rm -rf \"" + d + "\"";
                        aa = com.demo.e3.cmd(lei, m, false);

                        if (java.util.Objects.equals(aa[0], "") && !java.util.Objects.equals(aa[1], "")) {

                            final String z = "删除失败\n" + aa[1];
                            e1.tsk("提示", z);

                        } else if (!java.util.Objects.equals(aa[0], "") && java.util.Objects.equals(aa[1], "")) {

                            final String z = "删除成功";

                        } else {

                            final String z = "删除成功";

                        }

                    }

                }
                open.cn.awg.pro.core.NativeActions.finish(DeleteConfirmActivity.this);

            }

        });

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(DeleteConfirmActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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
        tip();

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

        }
        return false;

    }

    private void onTx1Click(View vw) {
        no();

    }

    private void onTx2Click(View vw) {
        yes();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
