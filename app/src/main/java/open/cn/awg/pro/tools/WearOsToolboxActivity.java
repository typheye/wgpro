/**
 ******************************************************************************
 * @file    WearOsToolboxActivity.java
 * @author  Typheye
 * @brief   Hosts Wear OS toolbox utilities.
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.ui.list.AppActionItemActivity;

public class WearOsToolboxActivity extends BaseAwgActivity {

    public final WearOsToolboxActivity lei = this, 类 = this;
    public i.runlibrary.app.v.v7lb$UserAdapter spq = null;
    public String lin;
    public String CacheSize = null;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.wear_os_toolbox);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.wear_os_toolbox_root, R.id.xdbj1, R.id.title_bar);

        i.runlibrary.app.v.v7lb lb = st.v7lb(R.id.v7lb1);
        spq = lb.v7lbspq(AppActionItemActivity.class, R.layout.app_action_item);

        int[] kj = new int[]{R.id.wb1, R.id.wb2, -1, -2};

        String[] jk = new String[]{"", "", ""};
        jk = new String[]{"开启蓝牙", "开启设备蓝牙并向周围设备显示", "com.google.android.apps.wearable.settings", "com.google.android.clockwork.settings.connectivity.bluetooth.BluetoothRequestDiscoverableActivity"};
        spq.j(kj, jk);
        jk = new String[]{"eSIM状态", "查看eSIM状态", "com.google.android.apps.wearable.settings", "com.google.android.clockwork.settings.cellular.EsimProfileActivity"};
        spq.j(kj, jk);
        jk = new String[]{"eSIM重置", "重置eSIM虚拟卡", "com.google.android.apps.wearable.settings", "com.google.android.clockwork.settings.cellular.ResetEsimActivity"};
        spq.j(kj, jk);
        jk = new String[]{"账号", "查看设备上的账号", "com.google.android.apps.wearable.settings", "com.google.android.clockwork.settings.cellular.PhoneAccountsActivity"};
        spq.j(kj, jk);
        jk = new String[]{"Wear云同步", "启用Wear云同步", "com.google.android.apps.wearable.settings", "com.google.android.clockwork.settings.CloudSyncOptInSettingsActivity"};
        spq.j(kj, jk);
        jk = new String[]{"Google账号", "添加一个Google账号", "com.google.android.wearable.app", "com.google.android.clockwork.home.accounts.AddAccountActivity"};
        spq.j(kj, jk);
        spq.sx();

    }

    public void jz() {

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

    private void onTx1Click(View vw) {
        gj.gb();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
