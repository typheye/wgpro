/**
 ******************************************************************************
 * @file    TextQrDisplayActivity.java
 * @author  Typheye
 * @brief   Displays text content with optional QR code rendering.
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
package open.cn.awg.pro.ui.main;

import open.cn.awg.pro.core.TextValues;
import android.graphics.Bitmap;
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
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class TextQrDisplayActivity extends BaseAwgActivity {

    public final TextQrDisplayActivity lei = this, 类 = this;
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
        setContentView(R.layout.text_qr_display);
        bindViews();
        AwgProApplication.getInstance().addActivity(lei);

        String nr = getIntent().getStringExtra("text");
        ((TextView) findViewById(R.id.wb1)).setText(String.valueOf(nr));

        String bt = getIntent().getStringExtra("title");
        bt = open.cn.awg.pro.core.TextValues.trim(bt);

        if (!java.util.Objects.equals(bt, null) && !java.util.Objects.equals(bt, "")) {

            ((TextView) findViewById(R.id.title_bar)).setText(String.valueOf("" + bt));

        }

        String qr = getIntent().getStringExtra("QR-Code");
        qr = open.cn.awg.pro.core.TextValues.trim(qr);

        if (!java.util.Objects.equals(qr, null) && !java.util.Objects.equals(qr, "")) {

            try {

                ImageView imageView = findViewById(R.id.tx1);
                Bitmap bitmap = com.demo.e4.createQRCodeBitmap(qr, 800, 800, "UTF-8", "H", "1");
                imageView.setImageBitmap(bitmap);

                ((ImageView) findViewById(R.id.tx1)).setVisibility(android.view.View.VISIBLE);

            } catch (Throwable e) {

                ((ImageView) findViewById(R.id.tx1)).setVisibility(android.view.View.GONE);

            }

        }

    }

    private void applyScreenPolicy() {

        applyStandardScreenMode(R.id.text_qr_display_root, R.id.xdbj1, R.id.title_bar);

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

    private void onTx2Click(View vw) {
        finish();

    }

    private void bindViews() {

        TextView d26d5d7080 = (TextView) findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        applyScreenPolicy();
    }
}
