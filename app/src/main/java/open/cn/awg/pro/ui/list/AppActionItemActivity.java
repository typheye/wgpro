/**
 ******************************************************************************
 * @file    AppActionItemActivity.java
 * @author  Typheye
 * @brief   Runs an app-specific action from a list item.
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
import android.widget.RelativeLayout;

import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class AppActionItemActivity extends BaseAwgActivity {

    public final AppActionItemActivity lei = this, 类 = this;
    private final View.OnLongClickListener appActionItemRootLongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onAppActionItemRootLongClick(vw);
        }

    };
    private final View.OnClickListener appActionItemRootClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAppActionItemRootClick(vw);
        }

    };

    private void onAppActionItemRootClick(View vw) {

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) (Object) sj.lb(vw);

        final Object a = lb.lbcfsj(-1);

        final Object b = lb.lbcfsj(-2);

        try {

            gj.jmxc(new Runnable() {

                public void run() {

                    xt.dkyy(a, b);

                }

            });

        } catch (Throwable e) {

            e1.tsk("提示", "没有可执行该操作的应用");

        }

    }

    private boolean onAppActionItemRootLongClick(View vw) {

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) (Object) sj.lb(vw);

        Object l1 = lb.lbcfsj(R.id.wb1);

        Object l2 = lb.lbcfsj(R.id.wb2);

        Object l = lb.lbcfsj(-1);
        e1.tsk("详细信息", "[功能名称]\n" + l1 + "\n\n[功能简介]\n" + l2);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        RelativeLayout i2b32d98dc8 = (RelativeLayout) findViewById(ay, vw, R.id.app_action_item_root);
        i2b32d98dc8.setOnClickListener(appActionItemRootClickListener);
        i2b32d98dc8.setOnLongClickListener(appActionItemRootLongClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
