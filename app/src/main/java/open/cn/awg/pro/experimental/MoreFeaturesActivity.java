/**
 ******************************************************************************
 * @file    MoreFeaturesActivity.java
 * @author  Typheye
 * @brief   Shows additional and experimental feature entries.
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

import android.content.Intent;
import open.cn.awg.pro.core.NativeFiles;
import open.cn.awg.pro.ui.list.NativeListAdapter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class MoreFeaturesActivity extends BaseAwgActivity {

    public final MoreFeaturesActivity lei = this, 类 = this;
    public NativeListAdapter spq = null;
    public String lin;
    public String CacheSize = null;
    public AppRuntimeBridge e1;
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj4LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj4LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj4Click(vw);
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
        setContentView(R.layout.more_features);
        bindViews();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    private void applyScreenPolicy() {

        applyStandardScreenMode(R.id.more_features_root, R.id.xdbj1, R.id.title_bar);

    }

    public void jz() {

        String set1 = AppPaths.appPath("settings/set1.inf");

        if (java.util.Objects.equals(NativeFiles.readText(lei, set1), "true")) {

            ((View) findViewById(R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
            ((TextView) findViewById(R.id.wb1)).setVisibility(android.view.View.GONE);

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

    private void onXdbj4Click(View vw) {
        startActivity(new Intent(lei, ExperimentalFeaturesActivity.class));

    }

    private boolean onXdbj4LongClick(View vw) {

        String te = ((TextView) findViewById(R.id.wb7)).getText().toString();

        String lj = ((TextView) findViewById(R.id.wb8)).getText().toString();
        e1.tsk(lei, "详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx1Click(View vw) {
        finish();

    }

    private void bindViews() {

        TextView d26d5d7080 = (TextView) findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) findViewById(R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);
        i2202ede8ff.setOnLongClickListener(xdbj4LongClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        applyScreenPolicy();
    }
}
