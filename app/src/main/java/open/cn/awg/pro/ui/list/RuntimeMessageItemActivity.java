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

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class RuntimeMessageItemActivity extends BaseAwgActivity {

    public final RuntimeMessageItemActivity lei = this, 类 = this;
    private final View.OnLongClickListener xdbj1LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj1LongClick(vw);
        }

    };

    private boolean onXdbj1LongClick(View vw) {

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) (Object) sj.lb(vw);

        Object a = lb.lbcfsj(R.id.wb1);

        Object b = lb.lbcfsj(R.id.wb2);

        Object c = lb.lbcfsj(R.id.wb3);
        e1.tsk("详细信息", "[时间]\n" + c + "\n\n[消息]\n" + a);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        LinearLayout e1433e26d6 = (LinearLayout) findViewById(ay, vw, R.id.xxbj1);

        TextView i0d2d4d9236 = (TextView) findViewById(ay, vw, R.id.wb3);

        TextView ff56a8e7f6 = (TextView) findViewById(ay, vw, R.id.wb1);

        RelativeLayout aaeebbd136 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj1);
        aaeebbd136.setOnLongClickListener(xdbj1LongClickListener);

        RelativeLayout c93275c379 = (RelativeLayout) findViewById(ay, vw, R.id.runtime_message_item_root);

        __layoutIsLoaded(ay, vw);
    }
}
