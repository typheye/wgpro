/**
 ******************************************************************************
 * @file    TuneFreeSongItemActivity.java
 * @author  Typheye
 * @brief   Handles TuneFree song list item selection.
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
import open.cn.awg.pro.music.TuneFreeMusicBridge;
import open.cn.awg.pro.R;

public class TuneFreeSongItemActivity extends BaseAwgActivity {

    public final TuneFreeSongItemActivity lei = this, 类 = this;
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

        Object a2 = lb.lbcfsj(R.id.wb3);

        Object a3 = lb.lbcfsj(R.id.wb2);
        e12.loadMusic(a3, a1 + "-" + a2);

    }

    private boolean onXdbj1LongClick(View vw) {

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) sj.lb(vw);

        Object a1 = lb.lbcfsj(R.id.wb1);

        Object a2 = lb.lbcfsj(R.id.wb3);

        Object a3 = lb.lbcfsj(R.id.wb2);
        e1.tsk("详细信息", "[歌名]\n" + a1 + "\n\n[歌手]\n" + a2 + "\n\n[歌曲ID]\n" + a3);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        RelativeLayout aaeebbd136 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj1);
        aaeebbd136.setOnClickListener(xdbj1ClickListener);
        aaeebbd136.setOnLongClickListener(xdbj1LongClickListener);

        RelativeLayout i4fe9eaa1ba = (RelativeLayout) findViewById(ay, vw, R.id.tune_free_song_item_root);

        __layoutIsLoaded(ay, vw);
    }
}
