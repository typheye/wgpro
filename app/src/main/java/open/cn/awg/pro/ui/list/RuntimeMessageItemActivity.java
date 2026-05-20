/**
 ******************************************************************************
 * @file    RuntimeMessageItemActivity.java
 * @author  Typheye
 * @brief   Displays runtime notification or message item details.
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


public class RuntimeMessageItemActivity extends iActivity {

    public final RuntimeMessageItemActivity lei = this, 类 = this;
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_aaeebbd136 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_aaeebbd136(vw);
        }

    };

    private boolean $_onLongClick_aaeebbd136(android.view.View vw) {

        open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        java.lang.Object a = lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

        java.lang.Object b = lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

        java.lang.Object c = lb.lbcfsj(open.cn.awg.pro.R.id.wb3);
        e1.tsk("详细信息", "[时间]\n" + c + "\n\n[消息]\n" + a);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.LinearLayout e1433e26d6 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj1);

        android.widget.TextView i0d2d4d9236 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb3);

        android.widget.TextView ff56a8e7f6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb1);

        android.widget.RelativeLayout aaeebbd136 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj1);
        aaeebbd136.setOnLongClickListener($_on_setOnLongClickListener_aaeebbd136);

        android.widget.RelativeLayout c93275c379 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.c21_v);

        __layoutIsLoaded(ay, vw);
    }
}
