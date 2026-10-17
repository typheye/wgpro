/**
 ******************************************************************************
 * @file    StoreSearchActivity.java
 * @author  Typheye
 * @brief   Store search screen.
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
package open.cn.awg.pro.store;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.EditText;
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

public class StoreSearchActivity extends BaseAwgActivity {

    public final StoreSearchActivity lei = this, 类 = this;
    public AppRuntimeBridge e1;
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
        setContentView(R.layout.store_search);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        

        applyStandardScreenMode(R.id.store_search_root, R.id.xdbj1, R.id.title_bar);
        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(StoreSearchActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(StoreSearchActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(StoreSearchActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(StoreSearchActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));

        if (java.util.Objects.equals(a, "4")) {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(StoreSearchActivity.this.getWindow().getDecorView(), R.id.store_search_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(true));

            String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTab);
            setStatusBarColor(clr);

        } else {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(StoreSearchActivity.this.getWindow().getDecorView(), R.id.store_search_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

        }

        if (java.util.Objects.equals(a, "1")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(StoreSearchActivity.this.getWindow().getDecorView(), R.id.xdbj1)), u, 0, u, 50);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreSearchActivity.this.getWindow().getDecorView(), R.id.title_bar));
            // 应用栏接管标题排版，移除旧运行时 padding
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "2")) {

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (open.cn.awg.pro.core.NativeFiles.exists(StoreSearchActivity.this, (String) (a001)) || java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(StoreSearchActivity.this, (String) (a001)), "true")) {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreSearchActivity.this.getWindow().getDecorView(), R.id.title_bar));
                // 应用栏接管标题排版，移除旧运行时 padding
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            } else {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreSearchActivity.this.getWindow().getDecorView(), R.id.title_bar));
                // 应用栏接管标题排版，移除旧运行时 padding
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            }
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(StoreSearchActivity.this.getWindow().getDecorView(), R.id.xdbj1)), 0, 0, 0, 0);

        } else if (java.util.Objects.equals(a, "3")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(StoreSearchActivity.this.getWindow().getDecorView(), R.id.xdbj1)), u, 0, u, 50);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreSearchActivity.this.getWindow().getDecorView(), R.id.title_bar));
            // 应用栏接管标题排版，移除旧运行时 padding
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "4")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(StoreSearchActivity.this.getWindow().getDecorView(), R.id.xdbj1)), 0, 0, 0, 0);

        }

        android.widget.Spinner xlcd1 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(StoreSearchActivity.this.getWindow().getDecorView(), R.id.xlcd1));

        String[] sz = new String[]{"查找应用", "查找视频", "查找歌曲"};
        open.cn.awg.pro.core.NativeViewValues.spinner(xlcd1, sz);

    }

    public void onRestart() {
        super.onRestart();
            applyWindowModeFromSettings();

    }

    public void onStart() {
        super.onStart();
            applyWindowModeFromSettings();

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(StoreSearchActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(StoreSearchActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private void onTx2Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(StoreSearchActivity.this);

    }

    private void onTx3Click(View vw) {

        android.widget.EditText bjk1 = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(StoreSearchActivity.this.getWindow().getDecorView(), R.id.bjk1));

        String code = open.cn.awg.pro.core.TextValues.trim(bjk1.getText().toString());

        if (java.util.Objects.equals(code, "")) {

            e1.tsk("提示", "搜索内容不能为空");

        } else {

            android.widget.Spinner xlcd1 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(StoreSearchActivity.this.getWindow().getDecorView(), R.id.xlcd1));

            String vfg = "";

            if (xlcd1.getLastVisiblePosition() == 0) {

                vfg = "app";

            } else if (xlcd1.getLastVisiblePosition() == 1) {

                vfg = "video";

            } else if (xlcd1.getLastVisiblePosition() == 2) {

                vfg = "music";

            }

            final String[] iyu = new String[]{"type", "code"};

            final String[] val = new String[]{vfg, code};
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(StoreSearchActivity.this, (Class<?>) (StoreListActivity.class), (String[]) (iyu), (Object[]) (val));

                }

            });

        }

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);

        EditText i32ee139ba1 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
