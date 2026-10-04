/**
 ******************************************************************************
 * @file    DisplayMarginActivity.java
 * @author  Typheye
 * @brief   Adjusts the app display margin calibration setting.
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
import android.widget.SeekBar;
import open.cn.awg.pro.core.NativeFiles;
import open.cn.awg.pro.core.TextValues;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.repair.ErrorMessageActivity;

public class DisplayMarginActivity extends BaseAwgActivity {

    public final DisplayMarginActivity lei = this, 类 = this;
    public int old_i = 0;
    public String lin = "";
    public String newcolor = "";
    public int bs = 0;
    private final View.OnClickListener wb1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb1Click(vw);
        }

    };
    private final SeekBar.OnSeekBarChangeListener tdt1SeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        public void onProgressChanged(SeekBar vw, int ps, boolean fu) {
            onTdt1ProgressChanged(vw, ps, fu);
        }

        public void onStartTrackingTouch(SeekBar vw) {
            onTdt1StartTrackingTouch(vw);
        }

        public void onStopTrackingTouch(SeekBar vw) {
            onTdt1StopTrackingTouch(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.display_margin);
        bindViews();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    private void applyScreenPolicy() {

        if (java.util.Objects.equals(NativeFiles.readText(lei, AppPaths.appPath("settings/set4s.inf")), "false")) {

            if (java.util.Objects.equals(NativeFiles.readText(lei, AppPaths.appPath("settings/set4.inf")), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }
        applyFullscreenWindow();

    }

    public void jz() {

        String set2 = AppPaths.appPath("settings/f17/set1.inf");

        int i = TextValues.intValue(NativeFiles.readText(lei, set2), 0);
        old_i = i;

        SeekBar tdt1 = ((SeekBar) findViewById(R.id.tdt1));
        tdt1.setProgress(i);

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
        applyFullscreenWindow();

    }

    public void onRestart() {
        super.onRestart();
        applyFullscreenWindow();

    }

    public void onStart() {
        super.onStart();
        applyFullscreenWindow();

    }

    public void onResume() {
        super.onResume();
        applyFullscreenWindow();

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {
        return true;

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onTdt1ProgressChanged(SeekBar vw, int ps, boolean fu) {

        String set = AppPaths.appPath("settings/f17/set1.inf");

        SeekBar tdt1 = ((SeekBar) findViewById(R.id.tdt1));

        final int i = tdt1.getProgress();

        final int ii = i / 2;
        runOnUiThread(new Runnable() {

            public void run() {

                View preview = findViewById(R.id.xdbj3);
                ViewGroup.MarginLayoutParams margins = (ViewGroup.MarginLayoutParams) preview.getLayoutParams();
                margins.setMargins(i, i, i, i);
                preview.setLayoutParams(margins);
                ((TextView) findViewById(R.id.wb10)).setText(String.valueOf(ii + "dp"));

            }

        });

        String sis = open.cn.awg.pro.core.TextValues.string(i);
        NativeFiles.writeText(lei, set, sis);

    }

    private void onTdt1StartTrackingTouch(SeekBar vw) {

    }

    private void onTdt1StopTrackingTouch(SeekBar vw) {

    }

    private void onWb1Click(View vw) {

        SeekBar tdt1 = ((SeekBar) findViewById(R.id.tdt1));

        if (tdt1.getProgress() == old_i) {

            finish();

        } else {

            (new Thread() {

                public void run() {

                    runOnUiThread(new Runnable() {

                        public void run() {
                            startActivity(new Intent(lei, ErrorMessageActivity.class).putExtra("Msg", "应用成功 点击重启"));

                        }

                    });

                }

            }).start();

        }

    }

    private void bindViews() {

        SeekBar d11169f9c0 = (SeekBar) findViewById(R.id.tdt1);
        d11169f9c0.setOnSeekBarChangeListener(tdt1SeekBarChangeListener);

        TextView ff56a8e7f6 = (TextView) findViewById(R.id.wb1);
        ff56a8e7f6.setOnClickListener(wb1ClickListener);

        applyScreenPolicy();
    }
}
