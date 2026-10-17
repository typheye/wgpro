/**
 ******************************************************************************
 * @file    ApkPreviewActivity.java
 * @author  Typheye
 * @brief   APK preview and open screen.
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
package open.cn.awg.pro.installer;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
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

import com.apkinstaller.Methods.ApkMethod;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

@SuppressLint("StaticFieldLeak")
public class ApkPreviewActivity extends BaseAwgActivity {

    public static Context context;
    public final ApkPreviewActivity lei = this, 类 = this;
    public AppRuntimeBridge e1;
    public String url = "";
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
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

    public void onCreate(Bundle be) {
        super.onCreate(be);
        e1 = new AppRuntimeBridge(this);
        setContentView(R.layout.apk_preview);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        url = ApkPreviewActivity.this.getIntent().getStringExtra(String.valueOf("url"));

        try {

            ImageView imageView = findViewById(R.id.tx4);
            TextView textView = findViewById(R.id.wb1);
            String Apk_Path = url;
            ApkMethod apkMethod = new ApkMethod(this, Apk_Path);
            String Apk_Name = apkMethod.getApplicationName();
            Drawable Apk_Icon = apkMethod.getApplicationIcon(this);
            textView.setText(Apk_Name);
            imageView.setImageDrawable(Apk_Icon);

        } catch (Throwable e) {

            open.cn.awg.pro.core.NativeActions.finish(ApkPreviewActivity.this);
            e1.tsk("提示", "文件打开异常!");

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        

        applyStandardScreenMode(R.id.apk_preview_root, R.id.xdbj1, R.id.title_bar);
        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ApkPreviewActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ApkPreviewActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(ApkPreviewActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(ApkPreviewActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));

        if (java.util.Objects.equals(a, "4")) {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ApkPreviewActivity.this.getWindow().getDecorView(), R.id.apk_preview_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(true));

            String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTab);
            setStatusBarColor(clr);

        } else {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ApkPreviewActivity.this.getWindow().getDecorView(), R.id.apk_preview_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

        }

        if (java.util.Objects.equals(a, "1")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ApkPreviewActivity.this.getWindow().getDecorView(), R.id.xdbj1)), u, 0, u, 50);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ApkPreviewActivity.this.getWindow().getDecorView(), R.id.title_bar));
            // 应用栏接管标题排版，移除旧运行时 padding
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "2")) {

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (open.cn.awg.pro.core.NativeFiles.exists(ApkPreviewActivity.this, (String) (a001)) || java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ApkPreviewActivity.this, (String) (a001)), "true")) {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ApkPreviewActivity.this.getWindow().getDecorView(), R.id.title_bar));
                // 应用栏接管标题排版，移除旧运行时 padding
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            } else {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ApkPreviewActivity.this.getWindow().getDecorView(), R.id.title_bar));
                // 应用栏接管标题排版，移除旧运行时 padding
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            }
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ApkPreviewActivity.this.getWindow().getDecorView(), R.id.xdbj1)), 0, 0, 0, 0);

        } else if (java.util.Objects.equals(a, "3")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ApkPreviewActivity.this.getWindow().getDecorView(), R.id.xdbj1)), u, 0, u, 50);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ApkPreviewActivity.this.getWindow().getDecorView(), R.id.title_bar));
            // 应用栏接管标题排版，移除旧运行时 padding
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "4")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ApkPreviewActivity.this.getWindow().getDecorView(), R.id.xdbj1)), 0, 0, 0, 0);

        }

    }

    public void run() {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                Intent intent = new Intent(lei, ApkInstallerActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", url);
                intent.putExtras(bundle);
                lei.startActivity(intent);

                open.cn.awg.pro.core.NativeActions.finish(ApkPreviewActivity.this);

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ApkPreviewActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

        }
        return false;

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onTx2Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(ApkPreviewActivity.this);

    }

    private void onTx3Click(View vw) {
        run();

    }

    private void onTx1Click(View vw) {

        final android.widget.LinearLayout cmdlin = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ApkPreviewActivity.this.getWindow().getDecorView(), R.id.xxbj1));

        final android.widget.LinearLayout cmdbac = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ApkPreviewActivity.this.getWindow().getDecorView(), R.id.xxbj2));
        cmdbac.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
        cmdlin.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
