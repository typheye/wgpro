/**
 ******************************************************************************
 * @file    SettingsActivity.java
 * @author  Typheye
 * @brief   Hosts the main application settings screen.
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
package open.cn.awg.pro.settings;

import android.app.Activity;
import android.os.Bundle;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.widget.SwitchCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.account.TypheyeAccountActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.browser.X5BrowserSettingsActivity;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.core.CacheDataManager;
import open.cn.awg.pro.R;
import open.cn.awg.pro.tools.CloudServiceActivity;
import open.cn.awg.pro.ui.main.AboutAppActivity;

public class SettingsActivity extends BaseAwgActivity {

    public final SettingsActivity lei = this, 类 = this;
    public String CacheSize = "";
    public AppRuntimeBridge e1;
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj13ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj13Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj4Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj19ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj19Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj18ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj18Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj15ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj15Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj8ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj8Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj6ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj6Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj9ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj9Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj11ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj11Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj14ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj14Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj7ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj7Click(vw);
        }

    };
    private final CompoundButton.OnCheckedChangeListener kg3CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onKg3CheckedChanged(vw, ic);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        e1 = new AppRuntimeBridge(this);
        setContentView(R.layout.settings);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.settings_root, R.id.xdbj1, R.id.title_bar);

        // 应用栏毛玻璃：以滚动内容 qtgd1 为模糊源
        android.view.View blur = findViewById(R.id.blur_bar);
        android.view.View src = findViewById(R.id.qtgd1);
        if (blur instanceof open.cn.awg.pro.ui.widget.BlurBarView && src != null) {
            ((open.cn.awg.pro.ui.widget.BlurBarView) blur).setSource(src);
        }

        // 左 Action：返回
        android.view.View back = findViewById(R.id.appbar_action_left);
        if (back != null) {
            back.setOnClickListener(new android.view.View.OnClickListener() {
                public void onClick(android.view.View v) {
                    SettingsActivity.this.finish();
                }
            });
        }

    }

    public void jz() {

        String set3 = AppPaths.appPath("settings/set3.inf");

        android.widget.CompoundButton kg3 = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(SettingsActivity.this.getWindow().getDecorView(), R.id.kg3));

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(SettingsActivity.this, (String) (set3)), "true")) {

            kg3.setChecked(java.lang.Boolean.TRUE.equals(true));

        }

        try {

            CacheSize = CacheDataManager.getTotalCacheSize(this);

            if (java.util.Objects.equals(CacheSize, "0.00M")) {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(SettingsActivity.this.getWindow().getDecorView(), R.id.wb28)).setText(String.valueOf("应用暂无产生缓存"));

                    }

                });

            } else {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(SettingsActivity.this.getWindow().getDecorView(), R.id.wb28)).setText(String.valueOf("将清除" + CacheSize + "的缓存"));

                    }

                });

            }

        } catch (Throwable __$_e__) {

        }

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(SettingsActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

    private void onTitleBarClick(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(SettingsActivity.this);

    }

    private void onKg3CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/set3.inf");

        if (ic) {

            open.cn.awg.pro.core.NativeFiles.writeText(SettingsActivity.this, (String) (set), (String) ("true"));

        } else {

            open.cn.awg.pro.core.NativeFiles.writeText(SettingsActivity.this, (String) (set), (String) ("false"));

        }

    }

    private void onXdbj7Click(View vw) {

        android.widget.CompoundButton kg = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(SettingsActivity.this.getWindow().getDecorView(), R.id.kg3));

        if (!kg.isChecked()) {

            kg.setChecked(java.lang.Boolean.TRUE.equals(true));

        } else {

            kg.setChecked(java.lang.Boolean.TRUE.equals(false));

        }

    }



    private void onXdbj14Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(SettingsActivity.this, (Class<?>) (TypheyeAccountActivity.class));

    }



    private void onXdbj11Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(SettingsActivity.this, (Class<?>) (CloudServiceActivity.class));

    }



    private void onXdbj9Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(SettingsActivity.this, (Class<?>) (ThemeSettingsActivity.class));

    }



    private void onXdbj6Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(SettingsActivity.this, (Class<?>) (TextViewerSettingsActivity.class));

    }



    private void onXdbj8Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(SettingsActivity.this, (Class<?>) (FileManagerSettingsActivity.class));

    }



    private void onXdbj15Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(SettingsActivity.this, (Class<?>) (X5BrowserSettingsActivity.class));

    }



    private void onXdbj18Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(SettingsActivity.this, (Class<?>) (MediaBrowserSettingsActivity.class));

    }



    private void onXdbj19Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(SettingsActivity.this, (Class<?>) (AboutAppActivity.class));

    }



    private void onXdbj4Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(SettingsActivity.this, (Class<?>) (LegalInfoActivity.class));

    }



    private void onXdbj13Click(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(SettingsActivity.this.getWindow().getDecorView(), R.id.wb28)).getText().toString(), "应用暂无产生缓存")) {

            e1.tsk("提示", "应用暂无产生缓存");

        } else {

            CacheDataManager.clearAllCache(this);
            e1.tsk("提示", "清除成功");
            jz();

        }

    }



    private void onTx1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(SettingsActivity.this);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        SwitchCompat i2e1dda294f = (SwitchCompat) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.kg3);
        i2e1dda294f.setOnCheckedChangeListener(kg3CheckedChangeListener);

        RelativeLayout e585d94488 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj7);
        e585d94488.setOnClickListener(xdbj7ClickListener);

        RelativeLayout c4cbe9d7ff = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj14);
        c4cbe9d7ff.setOnClickListener(xdbj14ClickListener);

        RelativeLayout i67b461495f = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj11);
        i67b461495f.setOnClickListener(xdbj11ClickListener);

        RelativeLayout i5a93676674 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj9);
        i5a93676674.setOnClickListener(xdbj9ClickListener);

        RelativeLayout i171cedf1eb = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj6);
        i171cedf1eb.setOnClickListener(xdbj6ClickListener);

        RelativeLayout fab146d7a5 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj8);
        fab146d7a5.setOnClickListener(xdbj8ClickListener);

        RelativeLayout i6834408ef9 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj15);
        i6834408ef9.setOnClickListener(xdbj15ClickListener);

        RelativeLayout i11185eacd9 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj18);
        i11185eacd9.setOnClickListener(xdbj18ClickListener);

        RelativeLayout a229195608 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj19);
        a229195608.setOnClickListener(xdbj19ClickListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);

        RelativeLayout i62f27ff563 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj13);
        i62f27ff563.setOnClickListener(xdbj13ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        NestedScrollView a6dab51133 = (NestedScrollView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.qtgd1);

        __layoutIsLoaded(ay, vw);
    }
}
