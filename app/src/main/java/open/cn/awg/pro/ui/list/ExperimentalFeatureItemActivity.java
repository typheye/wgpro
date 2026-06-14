/**
 ******************************************************************************
 * @file    ExperimentalFeatureItemActivity.java
 * @author  Typheye
 * @brief   Routes experimental feature list entries.
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

import open.cn.awg.pro.apps.InstalledAppListActivity;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.experimental.ServerDebugActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.tools.FingerprintActivity;
import open.cn.awg.pro.tools.ShellExecutorActivity;

public class ExperimentalFeatureItemActivity extends BaseAwgActivity {

    public final ExperimentalFeatureItemActivity lei = this, 类 = this;
    private final View.OnLongClickListener experimentalFeatureItemRootLongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onExperimentalFeatureItemRootLongClick(vw);
        }

    };
    private final View.OnClickListener experimentalFeatureItemRootClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onExperimentalFeatureItemRootClick(vw);
        }

    };

    private void onExperimentalFeatureItemRootClick(View vw) {

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) sj.lb(vw);

        String l = String.valueOf(lb.lbcfsj(-1));

        if (zf.dy(l, "l5_a1_fuwuq_d0313_y2022")) {

            gj.tz(ServerDebugActivity.class);

        } else if (zf.dy(l, "j2")) {

            gj.tz(FingerprintActivity.class);

        } else if (zf.dy(l, "l6")) {

            gj.tz(InstalledAppListActivity.class);

        } else if (zf.dy(l, "j4")) {

            String[] a = new String[]{"szk"};

            String[] b = new String[]{"true"};
            gj.tz(ShellExecutorActivity.class, a, b);

        } else {

            e1.tsk("提示", "该功能不存在");

        }

    }

    private boolean onExperimentalFeatureItemRootLongClick(View vw) {

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) sj.lb(vw);

        Object l1 = lb.lbcfsj(R.id.wb1);

        Object l2 = lb.lbcfsj(R.id.wb2);

        Object l = lb.lbcfsj(-1);
        e1.tsk_QR("详细信息", "[功能名称]\n" + l1 + "\n\n[功能简介]\n" + l2 + "\n\n[功能标识]", l);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        RelativeLayout i9d5d168854 = (RelativeLayout) findViewById(ay, vw, R.id.experimental_feature_item_root);
        i9d5d168854.setOnClickListener(experimentalFeatureItemRootClickListener);
        i9d5d168854.setOnLongClickListener(experimentalFeatureItemRootLongClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
