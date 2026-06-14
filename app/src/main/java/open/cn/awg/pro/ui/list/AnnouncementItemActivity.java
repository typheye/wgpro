/**
 ******************************************************************************
 * @file    AnnouncementItemActivity.java
 * @author  Typheye
 * @brief   Opens announcement content from a notice list item.
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

import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.music.TuneFreeMusicBridge;
import open.cn.awg.pro.R;
import open.cn.awg.pro.viewer.TextViewerActivity;

public class AnnouncementItemActivity extends BaseAwgActivity {

    public final AnnouncementItemActivity lei = this, 类 = this;
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

        i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) sj.lb(vw);

        Object a1 = lb.lbcfsj(R.id.wb1);

        Object a3 = lb.lbcfsj(R.id.wb2);

        final String cache = AppPaths.appPath("data/notice/cache");
        wj.xrwb(cache, a3);

        final String[] name = new String[]{"url", "title", "code"};

        final Object[] value = new Object[]{cache, a1, "super1"};
        gj.jmxc(new Runnable() {

            public void run() {

                gj.tz(TextViewerActivity.class, name, value);

            }

        });

    }

    private boolean onXdbj1LongClick(View vw) {

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) sj.lb(vw);

        Object a1 = lb.lbcfsj(R.id.wb1);

        Object a2 = lb.lbcfsj(R.id.wb3);

        Object a3 = lb.lbcfsj(R.id.wb2);

        String b1 = zf.qc(a2, null, " | ");

        String b2 = zf.qc(a2, " | ", " 发布");
        e1.tsk("详细信息", "[公告标题]\n" + a1 + "\n\n[发布人员]\n" + b2 + "\n\n[发布时间]\n" + b1);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        RelativeLayout aaeebbd136 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj1);
        aaeebbd136.setOnClickListener(xdbj1ClickListener);
        aaeebbd136.setOnLongClickListener(xdbj1LongClickListener);

        RelativeLayout i6718088b5d = (RelativeLayout) findViewById(ay, vw, R.id.live_stream_item_root);

        __layoutIsLoaded(ay, vw);
    }
}
