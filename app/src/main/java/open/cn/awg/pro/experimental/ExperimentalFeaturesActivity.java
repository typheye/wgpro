/**
 ******************************************************************************
 * @file    ExperimentalFeaturesActivity.java
 * @author  Typheye
 * @brief   Lists test and experimental app features.
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
package open.cn.awg.pro.experimental;


import androidx.recyclerview.widget.RecyclerView;
import open.cn.awg.pro.ui.list.NativeListAdapter;
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
import open.cn.awg.pro.ui.list.ExperimentalFeatureRowBinder;

public class ExperimentalFeaturesActivity extends BaseAwgActivity {

    public final ExperimentalFeaturesActivity lei = this, 类 = this;
    public NativeListAdapter spq = null;
    public String lin;
    public String CacheSize = null;
    public AppRuntimeBridge e1;
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
        e1 = new AppRuntimeBridge(this);
        setContentView(R.layout.experimental_features);
        bindViews();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    private void applyScreenPolicy() {

        applyStandardScreenMode(R.id.experimental_features_root, R.id.xdbj1, R.id.title_bar);

        RecyclerView lb = findViewById(R.id.v7lb1);
        spq = NativeListAdapter.attach(lei, lb, R.layout.experimental_feature_item, new ExperimentalFeatureRowBinder(lei));

        int[] kj = new int[]{R.id.wb1, R.id.wb2, -1};

        String[] jk = new String[]{"", "", ""};
        jk = new String[]{"服务端调试", "设备与服务端调试工具", "l5_a1_fuwuq_d0313_y2022"};
        spq.addRow(kj, jk);
        jk = new String[]{"Shizuku命令行", "拥有Shizuku加持的Shell", "j4"};
        spq.addRow(kj, jk);

        if (java.util.Objects.equals(readDisplayMode(), "4")) {

            jk = new String[]{"指纹验证", "指纹验证测试工具", "j2"};
            spq.addRow(kj, jk);

        }
        spq.refreshRows();

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

        View qtgd1 = findViewById(R.id.qtgd1);

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
        finish();

    }

    private void onTx1Click(View vw) {
        finish();

    }

    private void bindViews() {

        TextView d26d5d7080 = (TextView) findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        applyScreenPolicy();
    }
}
