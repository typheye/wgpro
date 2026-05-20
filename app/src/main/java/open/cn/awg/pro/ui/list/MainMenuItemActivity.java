/**
 ******************************************************************************
 * @file    MainMenuItemActivity.java
 * @author  Typheye
 * @brief   Routes main-menu items to their target features.
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
import open.cn.awg.pro.apps.InstalledAppListActivity;
import open.cn.awg.pro.browser.X5BrowserActivity;
import open.cn.awg.pro.chat.WearChatActivity;
import open.cn.awg.pro.chat.WearChatAgreementActivity;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.experimental.MoreFeaturesActivity;
import open.cn.awg.pro.file.FileRootPickerActivity;
import open.cn.awg.pro.plugin.ExtensionPluginActivity;
import open.cn.awg.pro.repair.ResetAppActivity;
import open.cn.awg.pro.settings.SettingsActivity;
import open.cn.awg.pro.tools.LocationInfoActivity;
import open.cn.awg.pro.tools.SystemToolsActivity;
import open.cn.awg.pro.ui.main.AboutAppActivity;
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;


public class MainMenuItemActivity extends iActivity {

    public final MainMenuItemActivity lei = this, 类 = this;
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

            i.runlibrary.app.sj$lb lb = sj.lb(vw);

            java.lang.Object l = lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

            if (zf.dy(l, "1")) {

                gj.tz(FileRootPickerActivity.class);

            } else if (zf.dy(l, "5")) {

                gj.tz(InstalledAppListActivity.class);

            } else if (zf.dy(l, "2")) {

                gj.tz(AboutAppActivity.class);

            } else if (zf.dy(l, "3")) {

                i.runlibrary.app.gj$dh dh = gj.dh();

                final i.runlibrary.app.gj$Animationx jbtm = dh.jbtm(0, 1);
                jbtm.cxsc(300);
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.xdbj(open.cn.awg.pro.R.id.TCxdbj).kjd(0);
                        st.xdbj(open.cn.awg.pro.R.id.TCxdbj).dh(jbtm);

                    }

                });

            } else if (zf.dy(l, "4")) {

                gj.tz(TextQrDisplayActivity.class);
                gj.gb();

            } else if (zf.dy(l, "6")) {

                gj.tz(ResetAppActivity.class);
                gj.gb();

            } else if (zf.dy(l, "8")) {

                gj.tz(SettingsActivity.class);

            } else if (zf.dy(l, "9")) {

                if (e1.awgwl_state()) {

                    gj.tz(WearChatActivity.class);

                } else {

                    gj.tz(WearChatAgreementActivity.class);

                }

            } else if (zf.dy(l, "10")) {

                gj.tz(SystemToolsActivity.class);

            } else if (zf.dy(l, "11")) {

                gj.tz(X5BrowserActivity.class);

            } else if (zf.dy(l, "12")) {

                gj.tz(LocationInfoActivity.class);

            } else if (zf.dy(l, "13")) {

                gj.tz(MoreFeaturesActivity.class);

            } else if (zf.dy(l, "14")) {

                gj.xszm();

            } else if (zf.dy(l, "20")) {

                gj.tz(ExtensionPluginActivity.class);

            } else if (zf.dy(l, "31")) {

                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        try {

                            st.wb(open.cn.awg.pro.R.id.wb_main_kjfs_gbcd).v.performClick();

                        } catch (java.lang.Throwable __$_e__) {

                        }

                    }

                });

            } else {

                e1.tsk("提示", "未找到该选项");

            }

        } catch (java.lang.Throwable e) {

            e1.upload_error(e, "c3.*");

        }

    }

    private boolean $_onLongClick_e1433e26d6(android.view.View vw) {

        final open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        java.lang.Object te = lb.lbcfsj(open.cn.awg.pro.R.id.wb1);
        e1.tsk("详细信息", te);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.LinearLayout e1433e26d6 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj1);
        e1433e26d6.setOnClickListener($_on_setOnClickListener_e1433e26d6);
        e1433e26d6.setOnLongClickListener($_on_setOnLongClickListener_e1433e26d6);

        __layoutIsLoaded(ay, vw);
    }
}
