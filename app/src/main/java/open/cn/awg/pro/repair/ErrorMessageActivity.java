/**
 ******************************************************************************
 * @file    ErrorMessageActivity.java
 * @author  Typheye
 * @brief   Error message and restart screen.
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
package open.cn.awg.pro.repair;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class ErrorMessageActivity extends BaseAwgActivity {

    public final ErrorMessageActivity lei = this, 类 = this;
    public int mode;
    private final View.OnClickListener wb7ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb7Click(vw);
        }

    };
    private final View.OnClickListener xdbj7ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj7Click(vw);
        }

    };
    private final View.OnClickListener wb3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb3Click(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.error_message);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        if (wj.cz(AppPaths.appPath("settings/a4.inf"))) {

            show(0);

        } else {

            gj.jmxc(new Runnable() {

                public void run() {

                    Intent intent = lei.getPackageManager().getLaunchIntentForPackage(lei.getPackageName());
                    if (intent != null) {
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        lei.startActivity(intent);
                    }
                    AwgProApplication.getInstance().exit();

                }

            });

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        i.runlibrary.app.xt$pm pm = (i.runlibrary.app.xt$pm) (Object) xt.pm();

        if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4s.inf")), "false")) {

            if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4.inf")), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }
        applyFullscreenWindow();

        String e = sj.hqtz("Error");

        if (!zf.dy(e, "") || !zf.dy(e, null)) {

            st.wb(R.id.wb2).zf("Error: " + e);

        }
        e = sj.hqtz("Msg");

        if (!zf.dy(e, "") || !zf.dy(e, null)) {

            st.wb(R.id.wb2).zf(e);

        }

    }

    public void show(int i_) {

        final int i = i_;

        final i.runlibrary.app.v.xxbj xxbj4 = st.xxbj(R.id.xxbj4);

        final i.runlibrary.app.v.xxbj xxbj1 = st.xxbj(R.id.xxbj1);
        gj.jmxc(new Runnable() {

            public void run() {

                if (i == 0) {

                    xxbj1.kjd(0);
                    xxbj4.kjd(8);

                } else if (i == 1) {

                    xxbj1.kjd(8);
                    xxbj4.kjd(0);

                } else if (i == 2) {

                    xxbj1.kjd(8);
                    xxbj4.kjd(8);

                }

            }

        });

    }

    public void run() {
        gj.xc(new Thread() {

            public void run() {

                show(2);
                gj.zt(500);

                if (mode == 0) {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            Intent intent = lei.getPackageManager().getLaunchIntentForPackage(lei.getPackageName());
                            if (intent != null) {
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                lei.startActivity(intent);
                            }
                            AwgProApplication.getInstance().exit();

                        }

                    });

                }

            }

        });

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {
        return true;

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
        applyFullscreenWindow();

    }

    public void onRestart() {
        super.onRestart();
        applyFullscreenWindow();

    }

    public void onResume() {
        super.onResume();
        applyFullscreenWindow();

    }

    public void onStart() {
        super.onStart();
        applyFullscreenWindow();

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onWb3Click(View vw) {
        mode = 0;
        show(1);

    }

    private void onXdbj7Click(View vw) {
        run();

    }

    private void onWb7Click(View vw) {
        show(0);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView i0d2d4d9236 = (TextView) findViewById(ay, vw, R.id.wb3);
        i0d2d4d9236.setOnClickListener(wb3ClickListener);

        RelativeLayout e585d94488 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj7);
        e585d94488.setOnClickListener(xdbj7ClickListener);

        TextView b8bc22741b = (TextView) findViewById(ay, vw, R.id.wb7);
        b8bc22741b.setOnClickListener(wb7ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
