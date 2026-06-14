/**
 ******************************************************************************
 * @file    ExtensionPluginMenuItemActivity.java
 * @author  Typheye
 * @brief   Handles extension plugin menu commands.
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
package open.cn.awg.pro.ui.list;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.plugin.ExtensionPluginBridge;
import open.cn.awg.pro.R;

public class ExtensionPluginMenuItemActivity extends BaseAwgActivity {

    public final ExtensionPluginMenuItemActivity lei = this, 类 = this;
    private final View.OnLongClickListener xxbj1LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXxbj1LongClick(vw);
        }

    };
    private final View.OnClickListener xxbj1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj1Click(vw);
        }

    };

    private void onXxbj1Click(View vw) {

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        try {

            ExtensionPluginBridge e14 = new ExtensionPluginBridge(_APPINFO);

            i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) sj.lb(vw);

            Object a = lb.lbcfsj(R.id.wb1);

            Object b = lb.lbcfsj(R.id.wb2);

            Object c = lb.lbcfsj(R.id.wb3);

            Object d = lb.lbcfsj(R.id.wb4);
            e14.dj_cjlbcd(a, b, c, d);

        } catch (Throwable e) {

            e1.upload_error(e, "c18.*");

        }

    }

    private boolean onXxbj1LongClick(View vw) {

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) sj.lb(vw);

        Object a = lb.lbcfsj(R.id.wb1);
        e1.tsk("详细信息", a);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        LinearLayout e1433e26d6 = (LinearLayout) findViewById(ay, vw, R.id.xxbj1);
        e1433e26d6.setOnClickListener(xxbj1ClickListener);
        e1433e26d6.setOnLongClickListener(xxbj1LongClickListener);

        TextView ff56a8e7f6 = (TextView) findViewById(ay, vw, R.id.wb1);

        RelativeLayout b52d0781d7 = (RelativeLayout) findViewById(ay, vw, R.id.extension_plugin_menu_item_root);

        __layoutIsLoaded(ay, vw);
    }
}
