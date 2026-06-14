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

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import open.cn.awg.pro.apps.InstalledAppListActivity;
import open.cn.awg.pro.browser.X5BrowserActivity;
import open.cn.awg.pro.chat.WearChatActivity;
import open.cn.awg.pro.chat.WearChatAgreementActivity;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.experimental.MoreFeaturesActivity;
import open.cn.awg.pro.file.FileRootPickerActivity;
import open.cn.awg.pro.plugin.ExtensionPluginActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.repair.ResetAppActivity;
import open.cn.awg.pro.settings.SettingsActivity;
import open.cn.awg.pro.tools.LocationInfoActivity;
import open.cn.awg.pro.tools.SystemToolsActivity;
import open.cn.awg.pro.ui.main.AboutAppActivity;
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;

public class MainMenuItemActivity extends BaseAwgActivity {

    public final MainMenuItemActivity lei = this, 类 = this;
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

            i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) (Object) sj.lb(vw);

            Object l = lb.lbcfsj(R.id.wb2);

            if (zf.dy(l, "1")) {

                gj.tz(FileRootPickerActivity.class);

            } else if (zf.dy(l, "5")) {

                gj.tz(InstalledAppListActivity.class);

            } else if (zf.dy(l, "2")) {

                gj.tz(AboutAppActivity.class);

            } else if (zf.dy(l, "3")) {

                i.runlibrary.app.gj$dh dh = (i.runlibrary.app.gj$dh) (Object) gj.dh();

                final i.runlibrary.app.gj$Animationx jbtm = (i.runlibrary.app.gj$Animationx) (Object) dh.jbtm(0, 1);
                jbtm.cxsc(300);
                gj.jmxc(new Runnable() {

                    public void run() {

                        st.xdbj(R.id.TCxdbj).kjd(0);
                        st.xdbj(R.id.TCxdbj).dh(jbtm);

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

                gj.jmxc(new Runnable() {

                    public void run() {

                        try {

                            st.wb(R.id.wb_main_kjfs_gbcd).v.performClick();

                        } catch (Throwable __$_e__) {

                        }

                    }

                });

            } else {

                e1.tsk("提示", "未找到该选项");

            }

        } catch (Throwable e) {

            e1.upload_error(e, "c3.*");

        }

    }

    private boolean onXxbj1LongClick(View vw) {

        final AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) (Object) sj.lb(vw);

        Object te = lb.lbcfsj(R.id.wb1);
        e1.tsk("详细信息", te);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        LinearLayout e1433e26d6 = (LinearLayout) findViewById(ay, vw, R.id.xxbj1);
        e1433e26d6.setOnClickListener(xxbj1ClickListener);
        e1433e26d6.setOnLongClickListener(xxbj1LongClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
