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

import i.app.iActivity;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.plugin.ExtensionPluginBridge;


public class ExtensionPluginMenuItemActivity extends iActivity {

    public final ExtensionPluginMenuItemActivity lei = this, 类 = this;
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_e1433e26d6 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_e1433e26d6(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_e1433e26d6 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_e1433e26d6(vw);
        }

    };

    private void $_onClick_e1433e26d6(android.view.View vw) {

        open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        try {

            open.cn.awg.pro.plugin.ExtensionPluginBridge e14 = new ExtensionPluginBridge(_APPINFO);

            i.runlibrary.app.sj$lb lb = sj.lb(vw);

            java.lang.Object a = lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

            java.lang.Object b = lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

            java.lang.Object c = lb.lbcfsj(open.cn.awg.pro.R.id.wb3);

            java.lang.Object d = lb.lbcfsj(open.cn.awg.pro.R.id.wb4);
            e14.dj_cjlbcd(a, b, c, d);

        } catch (java.lang.Throwable e) {

            e1.upload_error(e, "c18.*");

        }

    }

    private boolean $_onLongClick_e1433e26d6(android.view.View vw) {

        open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        java.lang.Object a = lb.lbcfsj(open.cn.awg.pro.R.id.wb1);
        e1.tsk("详细信息", a);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.LinearLayout e1433e26d6 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj1);
        e1433e26d6.setOnClickListener($_on_setOnClickListener_e1433e26d6);
        e1433e26d6.setOnLongClickListener($_on_setOnLongClickListener_e1433e26d6);

        android.widget.TextView ff56a8e7f6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb1);

        android.widget.RelativeLayout b52d0781d7 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.c18_v);

        __layoutIsLoaded(ay, vw);
    }
}
