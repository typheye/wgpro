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
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };
    private final View.OnLongClickListener wb3LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onWb3LongClick(vw);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.online_id);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        show();
        e1.online();

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

    public boolean onKeyDown(int kc, KeyEvent ke) {
        return true;

    }

    public void show() {

        final String a1 = AppPaths.appPath("settings/a5.inf");

        boolean t;

        t = !zf.dy(wj.dqwb(a1), "") && wj.cz(a1);

        final i.runlibrary.app.v.xxbj x1 = st.xxbj(R.id.xxbj1);

        final i.runlibrary.app.v.xxbj x2 = st.xxbj(R.id.xxbj2);

        final i.runlibrary.app.v.wb t2 = st.wb(R.id.wb3);

        if (t) {

            gj.jmxc(new Runnable() {

                public void run() {

                    x1.kjd(8);
                    x2.kjd(0);
                    t2.zf(wj.dqwb(a1));

                }

            });

        } else {

            gj.jmxc(new Runnable() {

                public void run() {

                    x1.kjd(0);
                    x2.kjd(8);

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

        String n = st.bjk(R.id.bjk1).zf();

        final String a1 = AppPaths.appPath("settings/a5.inf");
        n = zf.qctwkg(n);

        if (zf.dy(n, "")) {

            e1.tsk("提示", "ID不能为空");

        } else {

            if (n.length() > 4) {

                wj.xrwb(a1, n);
                show();

            } else {

                e1.tsk("提示", "ID不能小于5位");

            }

        }

    }

    private boolean onWb3LongClick(View vw) {
        gj.tz(SystemPermissionActivity.class);
        return true;

    }

    private void onTx2Click(View vw) {
        gj.gb();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView i7c3e3c6b5d = (TextView) findViewById(ay, vw, R.id.wb2);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        TextView ff56a8e7f6 = (TextView) findViewById(ay, vw, R.id.wb1);

        TextView i0d2d4d9236 = (TextView) findViewById(ay, vw, R.id.wb3);
        i0d2d4d9236.setOnLongClickListener(wb3LongClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
