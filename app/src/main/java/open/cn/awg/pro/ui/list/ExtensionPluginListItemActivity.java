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

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.data.LocalDatabaseBridge;
import open.cn.awg.pro.plugin.ExtensionPluginBridge;
import open.cn.awg.pro.plugin.ExtensionPluginDetailActivity;
import open.cn.awg.pro.R;

public class ExtensionPluginListItemActivity extends BaseAwgActivity {

    public final ExtensionPluginListItemActivity lei = this, 类 = this;
    private final View.OnLongClickListener xdbj1LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj1LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj1Click(vw);
        }

    };

    private void onXdbj1Click(View vw) {

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        Object a = lb.lbcfsj(R.id.wb2);

        Object b = lb.lbcfsj(R.id.wb1);

        LocalDatabaseBridge e11 = new LocalDatabaseBridge(_APPINFO);

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
        e11.setup_pluginlist();

        if (e11.findid_pluginlist(a) != 0) {

            String cachehd = AppPaths.appPath("data/cjlb/lbx_id");
            wj.xrwb(cachehd, a);

            String cachehd2 = AppPaths.appPath("data/cjlb/lbx_title");
            wj.xrwb(cachehd2, b);

            ExtensionPluginBridge e14 = new ExtensionPluginBridge(_APPINFO);

            String set1 = AppPaths.appPath("settings/f19/set1.inf");

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

    private boolean onXdbj1LongClick(View vw) {

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        Object a = lb.lbcfsj(R.id.wb2);

        Object b = lb.lbcfsj(R.id.wb1);

        ExtensionPluginBridge e14 = new ExtensionPluginBridge(_APPINFO);
        e14.cjlbcd(2);

        String cachehd = AppPaths.appPath("data/cjlb/lbx_id");
        wj.xrwb(cachehd, a);

        String cachehd2 = AppPaths.appPath("data/cjlb/lbx_title");
        wj.xrwb(cachehd2, b);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        RelativeLayout df0f5f2ce0 = (RelativeLayout) findViewById(ay, vw, R.id.extension_plugin_list_item_root);

        LinearLayout e1433e26d6 = (LinearLayout) findViewById(ay, vw, R.id.xxbj1);

        RelativeLayout aaeebbd136 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj1);
        aaeebbd136.setOnClickListener(xdbj1ClickListener);
        aaeebbd136.setOnLongClickListener(xdbj1LongClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
