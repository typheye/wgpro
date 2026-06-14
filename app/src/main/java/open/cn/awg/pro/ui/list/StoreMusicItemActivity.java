/**
 ******************************************************************************
 * @file    StoreMusicItemActivity.java
 * @author  Typheye
 * @brief   Opens music store product details from a list item.
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
import open.cn.awg.pro.store.StoreDetailActivity;

public class StoreMusicItemActivity extends BaseAwgActivity {

    public final StoreMusicItemActivity lei = this, 类 = this;
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

        i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) (Object) sj.lb(vw);

        Object id = lb.lbcfsj(-1);

        final String[] iyu = new String[]{"uri"};

        final String[] val = new String[]{"awg://uri/music/" + id};
        gj.jmxc(new Runnable() {

            public void run() {

                gj.tz(StoreDetailActivity.class, iyu, val);

            }

        });

    }

    private boolean onXdbj1LongClick(View vw) {

        final AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) (Object) sj.lb(vw);

        Object te = lb.lbcfsj(R.id.wb1);

        Object lj = lb.lbcfsj(R.id.wb3);
        e1.tsk("详细信息", "[歌曲名称]\n" + te + "\n\n[歌曲简介]\n" + lj);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        RelativeLayout aaeebbd136 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj1);
        aaeebbd136.setOnClickListener(xdbj1ClickListener);
        aaeebbd136.setOnLongClickListener(xdbj1LongClickListener);

        RelativeLayout i7cc950df1a = (RelativeLayout) findViewById(ay, vw, R.id.store_music_item_root);

        __layoutIsLoaded(ay, vw);
    }
}
