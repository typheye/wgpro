/**
 ******************************************************************************
 * @file    FileOperationInfoItemActivity.java
 * @author  Typheye
 * @brief   Displays file operation status items and back navigation.
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


public class FileOperationInfoItemActivity extends iActivity {

    public final FileOperationInfoItemActivity lei = this, 类 = this;
    private final android.view.View.OnClickListener $_on_setOnClickListener_e1433e26d6 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_e1433e26d6(vw);
        }

    };

    private void $_onClick_e1433e26d6(android.view.View vw) {

        open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        try {

            i.runlibrary.app.sj$lb lb = sj.lb(vw);

            java.lang.Object a = lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

            java.lang.Object b = lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

            if (zf.dy(a, "返回")) {

                e1.cdx(0, null, null, null);

            } else {

                e1.tsk(a, b);

            }

        } catch (java.lang.Throwable e) {

            e1.upload_error(e, "c5.*");

        }

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.LinearLayout e1433e26d6 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj1);
        e1433e26d6.setOnClickListener($_on_setOnClickListener_e1433e26d6);

        __layoutIsLoaded(ay, vw);
    }
}
