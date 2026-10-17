/**
 ******************************************************************************
 * @file    OnlineIdActivity.java
 * @author  Typheye
 * @brief   Configures and verifies the app online identifier.
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
package open.cn.awg.pro.account;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.tools.SystemPermissionActivity;

public class OnlineIdActivity extends BaseAwgActivity {

    public final OnlineIdActivity lei = this, 类 = this;
    public AppRuntimeBridge e1;
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };
;
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        e1 = new AppRuntimeBridge(this);
        setContentView(R.layout.online_id);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        show();
        e1.online();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(OnlineIdActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(OnlineIdActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }
        applyFullscreenWindow();

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {
        return true;

    }

    public void show() {

        final String a1 = AppPaths.appPath("settings/a5.inf");

        boolean t;

        t = !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(OnlineIdActivity.this, (String) (a1)), "") && open.cn.awg.pro.core.NativeFiles.exists(OnlineIdActivity.this, (String) (a1));

        final android.widget.LinearLayout x1 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnlineIdActivity.this.getWindow().getDecorView(), R.id.xxbj1));

        final android.widget.LinearLayout x2 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnlineIdActivity.this.getWindow().getDecorView(), R.id.xxbj2));

        final android.widget.TextView t2 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(OnlineIdActivity.this.getWindow().getDecorView(), R.id.wb3));

        if (t) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    t2.setText(String.valueOf(open.cn.awg.pro.core.NativeFiles.readText(OnlineIdActivity.this, (String) (a1))));

                }

            });

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        }

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

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onTx1Click(View vw) {

        String n = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(OnlineIdActivity.this.getWindow().getDecorView(), R.id.bjk1)).getText().toString();

        final String a1 = AppPaths.appPath("settings/a5.inf");
        n = open.cn.awg.pro.core.TextValues.trim(n);

        if (java.util.Objects.equals(n, "")) {

            e1.tsk("提示", "ID不能为空");

        } else {

            if (n.length() > 4) {

                open.cn.awg.pro.core.NativeFiles.writeText(OnlineIdActivity.this, (String) (a1), (String) (n));
                show();

            } else {

                e1.tsk("提示", "ID不能小于5位");

            }

        }

    }



    private void onTx2Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(OnlineIdActivity.this);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView i7c3e3c6b5d = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb2);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        TextView ff56a8e7f6 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb1);

        TextView i0d2d4d9236 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb3);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
