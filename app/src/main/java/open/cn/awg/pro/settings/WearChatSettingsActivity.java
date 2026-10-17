/**
 ******************************************************************************
 * @file    WearChatSettingsActivity.java
 * @author  Typheye
 * @brief   Configures WearChat account and chat behavior.
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
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class WearChatSettingsActivity extends BaseAwgActivity {

    public final WearChatSettingsActivity lei = this, 类 = this;
    public AppRuntimeBridge e1;
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj3Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj2Click(vw);
        }

    };
    private final CompoundButton.OnCheckedChangeListener kg1CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onKg1CheckedChanged(vw, ic);
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
        setContentView(R.layout.wear_chat_settings);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.wear_chat_settings_root, R.id.xdbj1, R.id.title_bar);

    }

    public void jz() {

        String set1 = AppPaths.appPath("settings/f11/set1.inf");

        android.widget.CompoundButton kg1 = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(WearChatSettingsActivity.this.getWindow().getDecorView(), R.id.kg1));

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(WearChatSettingsActivity.this, (String) (set1)), "true")) {

            kg1.setChecked(java.lang.Boolean.TRUE.equals(true));

        }

    }

    public void onRestart() {
        super.onRestart();
            applyWindowModeFromSettings();

    }

    public void onStart() {
        super.onStart();
            applyWindowModeFromSettings();

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(WearChatSettingsActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

    private void onTitleBarClick(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(WearChatSettingsActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "腕上微聊")) {

            open.cn.awg.pro.core.NativeActions.finish(WearChatSettingsActivity.this);

        }

    }

    private void onKg1CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/f11/set1.inf");

        if (ic) {

            open.cn.awg.pro.core.NativeFiles.writeText(WearChatSettingsActivity.this, (String) (set), (String) ("true"));

        } else {

            open.cn.awg.pro.core.NativeFiles.writeText(WearChatSettingsActivity.this, (String) (set), (String) ("false"));

        }

    }

    private void onXdbj2Click(View vw) {

        android.widget.CompoundButton kg = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(WearChatSettingsActivity.this.getWindow().getDecorView(), R.id.kg1));

        if (!kg.isChecked()) {

            kg.setChecked(java.lang.Boolean.TRUE.equals(true));

        } else {

            kg.setChecked(java.lang.Boolean.TRUE.equals(false));

        }

    }



    private void onXdbj3Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(WearChatSettingsActivity.this, (Class<?>) (TypheyeAccountActivity.class));

    }



    private void onTx1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(WearChatSettingsActivity.this);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        SwitchCompat i1bca89f073 = (SwitchCompat) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.kg1);
        i1bca89f073.setOnCheckedChangeListener(kg1CheckedChangeListener);

        RelativeLayout i3167b45174 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);

        RelativeLayout i20c2777fab = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
