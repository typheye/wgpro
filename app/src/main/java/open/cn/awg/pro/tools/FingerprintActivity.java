/**
 ******************************************************************************
 * @file    FingerprintActivity.java
 * @author  Typheye
 * @brief   Runs fingerprint recognition and related diagnostics.
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
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class FingerprintActivity extends BaseAwgActivity {

    public final FingerprintActivity lei = this, 类 = this;
    public FingerprintManager fingerprintManager;
    public i.runlibrary.app.v.wb tv;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    FingerprintManager.AuthenticationCallback callback = new FingerprintManager.AuthenticationCallback() {
        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {

            tv.st.setText("验证成功");
        }

        @Override
        public void onAuthenticationError(int errorCode, CharSequence errString) {

            tv.st.setText("频繁，请稍候再试");
        }

        @Override
        public void onAuthenticationHelp(int helpCode, CharSequence helpString) {

        }

        @Override
        public void onAuthenticationFailed() {

            tv.st.setText("没有找到匹配的指纹信息，请重试");

        }
    };
    private final View.OnClickListener an1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn1Click(vw);
        }

    };
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.fingerprint);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        tv = st.wb(R.id.wb1);

        try {

            fingerprintManager = getSystemService(FingerprintManager.class);
            start();

        } catch (Throwable e) {

            e1.tsk("提示", "不受支持的设备\n" + e);

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.fingerprint_root, R.id.xdbj1, R.id.title_bar);

    }

    public void start() {

        tv.st.setText("请将手指放在指纹模块上面");
        if (fingerprintManager.isHardwareDetected() && fingerprintManager.hasEnrolledFingerprints()) {
            fingerprintManager.authenticate(null, null, 0, callback, null);
        }

    }

    public void onRestart() {
        super.onRestart();
            applyWindowModeFromSettings();

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
            applyWindowModeFromSettings();

    }

    public void onStart() {
        super.onStart();
            applyWindowModeFromSettings();

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
            applyWindowModeFromSettings();

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onTitleBarClick(View vw) {
        gj.gb();

    }

    private void onAn1Click(View vw) {
        start();

    }

    private void onTx2Click(View vw) {
        gj.gb();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        Button i171ea34f1a = (Button) findViewById(ay, vw, R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
