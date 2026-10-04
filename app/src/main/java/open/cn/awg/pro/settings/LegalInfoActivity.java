/**
 ******************************************************************************
 * @file    LegalInfoActivity.java
 * @author  Typheye
 * @brief   Opens user agreement, permission explanation, and chat convention documents.
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

import android.content.Intent;
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
import open.cn.awg.pro.viewer.TextViewerActivity;

public class LegalInfoActivity extends BaseAwgActivity {

    public final LegalInfoActivity lei = this, 类 = this;
    public String lin;
    public String CacheSize = null;
    public AppRuntimeBridge e1;
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj3LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj3LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj3Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj2LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj2LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj2Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj14LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj14LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj14ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj14Click(vw);
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
        setContentView(R.layout.legal_info);
        bindViews();
        AwgProApplication.getInstance().addActivity(lei);

    }

    private void applyScreenPolicy() {

        applyStandardScreenMode(R.id.legal_info_root, R.id.xdbj1, R.id.title_bar);

    }

    public void load(Object url, Object title) {
        startActivity(new Intent(lei, TextViewerActivity.class)
                .putExtra("url", String.valueOf(url))
                .putExtra("title", String.valueOf(title))
                .putExtra("code", "super1"));

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

    private void onXdbj14Click(View vw) {
        load(AppPaths.appPath("data/assets/b0"), "用户协议");

    }

    private boolean onXdbj14LongClick(View vw) {

        String te = ((TextView) findViewById(R.id.wb29)).getText().toString();

        String lj = ((TextView) findViewById(R.id.wb30)).getText().toString();
        e1.tsk(lei, "详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj2Click(View vw) {
        load(AppPaths.appPath("data/assets/b1"), "权限使用说明");

    }

    private boolean onXdbj2LongClick(View vw) {

        String te = ((TextView) findViewById(R.id.wb2)).getText().toString();

        String lj = ((TextView) findViewById(R.id.wb3)).getText().toString();
        e1.tsk(lei, "详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj3Click(View vw) {
        load(AppPaths.appPath("data/assets/b3"), "腕上微聊公约");

    }

    private boolean onXdbj3LongClick(View vw) {

        String te = ((TextView) findViewById(R.id.wb4)).getText().toString();

        String lj = ((TextView) findViewById(R.id.wb5)).getText().toString();
        e1.tsk(lei, "详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx1Click(View vw) {
        finish();

    }

    private void bindViews() {

        TextView d26d5d7080 = (TextView) findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        RelativeLayout c4cbe9d7ff = (RelativeLayout) findViewById(R.id.xdbj14);
        c4cbe9d7ff.setOnClickListener(xdbj14ClickListener);
        c4cbe9d7ff.setOnLongClickListener(xdbj14LongClickListener);

        RelativeLayout i3167b45174 = (RelativeLayout) findViewById(R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);
        i3167b45174.setOnLongClickListener(xdbj2LongClickListener);

        RelativeLayout i20c2777fab = (RelativeLayout) findViewById(R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);
        i20c2777fab.setOnLongClickListener(xdbj3LongClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        applyScreenPolicy();
    }
}
