/**
 ******************************************************************************
 * @file    ExtensionPluginListItemActivity.java
 * @author  Typheye
 * @brief   Opens extension plugin details from the plugin list.
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
import open.cn.awg.pro.data.LocalDatabaseBridge;
import open.cn.awg.pro.plugin.ExtensionPluginBridge;
import open.cn.awg.pro.plugin.ExtensionPluginDetailActivity;


public class ExtensionPluginListItemActivity extends iActivity {

    public final ExtensionPluginListItemActivity lei = this, 类 = this;
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_aaeebbd136 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_aaeebbd136(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_aaeebbd136 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_aaeebbd136(vw);
        }

    };

    private void $_onClick_aaeebbd136(android.view.View vw) {

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        java.lang.Object a = lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

        java.lang.Object b = lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

        open.cn.awg.pro.data.LocalDatabaseBridge e11 = new LocalDatabaseBridge(_APPINFO);

        open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
        e11.setup_pluginlist();

        if (e11.findid_pluginlist(a) != 0) {

            java.lang.String cachehd = "/data/user/0/open.cn.awg.pro/data/cjlb/lbx_id";
            wj.xrwb(cachehd, a);

            java.lang.String cachehd2 = "/data/user/0/open.cn.awg.pro/data/cjlb/lbx_title";
            wj.xrwb(cachehd2, b);

            open.cn.awg.pro.plugin.ExtensionPluginBridge e14 = new ExtensionPluginBridge(_APPINFO);

            java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/f19/set1.inf";

            if (zf.dy(wj.dqwb(set1), "true")) {

                if (e14.newG(Integer.parseInt(String.valueOf(a)))) {

                    gj.tz(ExtensionPluginDetailActivity.class);

                }

            } else {

                gj.tz(ExtensionPluginDetailActivity.class);

            }

        } else {

            e1.tsk("提示", "该扩展插件不存在,请刷新列表后重试");

        }

    }

    private boolean $_onLongClick_aaeebbd136(android.view.View vw) {

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        java.lang.Object a = lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

        java.lang.Object b = lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

        open.cn.awg.pro.plugin.ExtensionPluginBridge e14 = new ExtensionPluginBridge(_APPINFO);
        e14.cjlbcd(2);

        java.lang.String cachehd = "/data/user/0/open.cn.awg.pro/data/cjlb/lbx_id";
        wj.xrwb(cachehd, a);

        java.lang.String cachehd2 = "/data/user/0/open.cn.awg.pro/data/cjlb/lbx_title";
        wj.xrwb(cachehd2, b);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.RelativeLayout df0f5f2ce0 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.c19_v);

        android.widget.LinearLayout e1433e26d6 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj1);

        android.widget.RelativeLayout aaeebbd136 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj1);
        aaeebbd136.setOnClickListener($_on_setOnClickListener_aaeebbd136);
        aaeebbd136.setOnLongClickListener($_on_setOnLongClickListener_aaeebbd136);

        __layoutIsLoaded(ay, vw);
    }
}
