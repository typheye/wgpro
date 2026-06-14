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

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class FileOperationInfoItemActivity extends BaseAwgActivity {

    public final FileOperationInfoItemActivity lei = this, 类 = this;
    private final View.OnClickListener xxbj1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj1Click(vw);
        }

    };

    private void onXxbj1Click(View vw) {

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        try {

            i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) (Object) sj.lb(vw);

            Object a = lb.lbcfsj(R.id.wb1);

            Object b = lb.lbcfsj(R.id.wb2);

            if (zf.dy(a, "返回")) {

                e1.cdx(0, null, null, null);

            } else {

                e1.tsk(a, b);

            }

        } catch (Throwable e) {

            e1.upload_error(e, "c5.*");

        }

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        LinearLayout e1433e26d6 = (LinearLayout) findViewById(ay, vw, R.id.xxbj1);
        e1433e26d6.setOnClickListener(xxbj1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
