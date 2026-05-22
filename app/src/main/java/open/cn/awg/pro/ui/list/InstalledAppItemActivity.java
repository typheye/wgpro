/**
 ******************************************************************************
 * @file    InstalledAppItemActivity.java
 * @author  Typheye
 * @brief   Handles installed app list item launch and details actions.
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
import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.kingqi.zwcj.日期工具;
import com.kingqi.zwcj.应用工具;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.music.TuneFreeMusicBridge;
import open.cn.awg.pro.R;

public class InstalledAppItemActivity extends BaseAwgActivity {

    public final InstalledAppItemActivity lei = this, 类 = this;
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

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        TuneFreeMusicBridge e12 = new TuneFreeMusicBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        Object app = lb.lbcfsj(-1);

        if (zf.dy(app, "open.cn.awg.pro")) {

            e1.tsk("提示", "您已经在该应用内了");

        } else {

            try {

                xt.dkyy(app);

            } catch (Throwable e) {

                e1.upload_error(e, "c17.*");

            }

        }

    }

    private boolean onXdbj1LongClick(View vw) {

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        Object app = lb.lbcfsj(-1);

        Context ct;
        ct = AwgProApplication.getContext();

        String a2 = 应用工具.获取应用签名(ct, String.valueOf(app));

        String a3 = 日期工具.格式化日期时间(应用工具.获取应用第一次安装日期(ct, String.valueOf(app)));

        String a4 = 日期工具.格式化日期时间(应用工具.获取应用更新日期(ct, String.valueOf(app)));

        String a5 = 应用工具.获取应用的安装市场(ct, String.valueOf(app));

        String a6 = 应用工具.获取应用版本名称(ct, String.valueOf(app));

        int a7 = 应用工具.获取应用版本号(ct, String.valueOf(app));

        String a8 = 应用工具.获取应用名称(ct, String.valueOf(app));

        String sho = "[应用名称]\n" + a8 + "\n\n[应用版本]\n" + a6 + "\n\n[应用版本号]\n" + a7 + "\n\n[应用安装日期]\n" + a3 + "\n\n[应用更新日期]\n" + a4;
        e1.tsk("详细信息", sho);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        RelativeLayout aaeebbd136 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj1);
        aaeebbd136.setOnClickListener(xdbj1ClickListener);
        aaeebbd136.setOnLongClickListener(xdbj1LongClickListener);

        RelativeLayout i6b77a3b312 = (RelativeLayout) findViewById(ay, vw, R.id.installed_app_item_root);

        __layoutIsLoaded(ay, vw);
    }
}
