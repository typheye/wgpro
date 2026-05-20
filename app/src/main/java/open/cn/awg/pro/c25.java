/**
 ******************************************************************************
 * @file    c25.java
 * @author  Typheye
 * @brief   AWG Pro module source.
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
package open.cn.awg.pro;

import i.app.iActivity;

public class c25 extends iActivity {

    public final c25 lei = this, 类 = this;
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_aaeebbd136 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_aaeebbd136(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_aaeebbd136 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_aaeebbd136(vw);
        }

    };

    private void $_onClick_aaeebbd136(android.view.View vw) {

        open.cn.awg.pro.e1 e1 = new e1(_APPINFO);

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        java.lang.Object id = lb.lbcfsj(-1);

        final java.lang.String[] iyu = new java.lang.String[]{"uri"};

        final java.lang.String[] val = new java.lang.String[]{"awg://uri/music/" + id};
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                gj.tz(p2.class, iyu, val);

            }

        });

    }

    private boolean $_onLongClick_aaeebbd136(android.view.View vw) {

        final open.cn.awg.pro.e1 e1 = new e1(_APPINFO);

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        java.lang.Object te = lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

        java.lang.Object lj = lb.lbcfsj(open.cn.awg.pro.R.id.wb3);
        e1.tsk("详细信息", "[歌曲名称]\n" + te + "\n\n[歌曲简介]\n" + lj);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.RelativeLayout aaeebbd136 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj1);
        aaeebbd136.setOnClickListener($_on_setOnClickListener_aaeebbd136);
        aaeebbd136.setOnLongClickListener($_on_setOnLongClickListener_aaeebbd136);

        android.widget.RelativeLayout i7cc950df1a = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.c25_v);

        __layoutIsLoaded(ay, vw);
    }
}
