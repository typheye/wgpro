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

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SeekBar;
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
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4s.inf")), "false")) {

            if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4.inf")), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }
        applyFullscreenWindow();

    }

    public void jz() {

        String set2 = AppPaths.appPath("settings/f17/set1.inf");

        int i = Integer.parseInt(wj.dqwb(set2));
        old_i = i;

        i.runlibrary.app.v.tdt tdt1 = st.tdt(R.id.tdt1);
        tdt1.jdz(i);

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

        i.runlibrary.app.v.tdt tdt1 = st.tdt(R.id.tdt1);

        final int i = tdt1.jdz();

        final int ii = i / 2;
        gj.jmxc(new Runnable() {

            public void run() {

                st.xdbj(R.id.xdbj3).wbj(i, i, i, i);
                st.wb(R.id.wb10).zf(ii + "dp");

            }

        });

        String sis = sj.zh(i).zstring();
        wj.xrwb(set, sis);

    }

    private void onTdt1StartTrackingTouch(SeekBar vw) {

    }

    private void onTdt1StopTrackingTouch(SeekBar vw) {

    }

    private void onWb1Click(View vw) {

        i.runlibrary.app.v.tdt tdt1 = st.tdt(R.id.tdt1);

        if (tdt1.jdz() == old_i) {

            gj.gb();

        } else {

            gj.xc(new Thread() {

                public void run() {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            String[] name = new String[]{"Msg"};

                            String[] value = new String[]{"应用成功 点击重启"};
                            gj.tz(ErrorMessageActivity.class, name, value);

                        }

                    });

                }

            });

        }

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        SeekBar d11169f9c0 = (SeekBar) findViewById(ay, vw, R.id.tdt1);
        d11169f9c0.setOnSeekBarChangeListener(tdt1SeekBarChangeListener);

        TextView ff56a8e7f6 = (TextView) findViewById(ay, vw, R.id.wb1);
        ff56a8e7f6.setOnClickListener(wb1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
