/**
 ******************************************************************************
 * @file    c16.java
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

public class c16 extends iActivity {

    public final c16 lei = this, 类 = this;
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

        open.cn.awg.pro.e12 e12 = new e12(_APPINFO);

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        java.lang.Object a1 = lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

        java.lang.Object a2 = lb.lbcfsj(open.cn.awg.pro.R.id.wb3);

        java.lang.Object a3 = lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

        java.lang.Object a4 = lb.lbcfsj(-2);

        if (zf.dy(a4, "")) {

            final java.lang.String[] name = new java.lang.String[]{"urls", "title", "text", "url"};

            final java.lang.Object[] value = new java.lang.Object[]{"", a1, a2, a3};
            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    gj.tz(l5_a3_zhibo_d0420_y2022_a1.class, name, value);

                }

            });

        } else {

            java.lang.String v_url_back = zf.qc(a4, "[", "]");
            v_url_back = zf.th(v_url_back, "\\/", "/", false);
            a4 = zf.th(v_url_back, "\"", "", false);
            a3 = a3 + "," + a4;

            final java.lang.String[] name = new java.lang.String[]{"urls", "title", "text"};

            final java.lang.Object[] value = new java.lang.Object[]{a3, a1, a2};
            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    gj.tz(l5_a3_zhibo_d0420_y2022_a1.class, name, value);

                }

            });

        }

    }

    private boolean $_onLongClick_aaeebbd136(android.view.View vw) {

        open.cn.awg.pro.e1 e1 = new e1(_APPINFO);

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        java.lang.Object a1 = lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

        java.lang.Object a2 = lb.lbcfsj(open.cn.awg.pro.R.id.wb3);

        java.lang.Object a3 = lb.lbcfsj(open.cn.awg.pro.R.id.wb2);
        e1.tsk("详细信息", "[直播标题]\n" + a1 + "\n\n[直播简介]\n" + a2);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.RelativeLayout aaeebbd136 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj1);
        aaeebbd136.setOnClickListener($_on_setOnClickListener_aaeebbd136);
        aaeebbd136.setOnLongClickListener($_on_setOnLongClickListener_aaeebbd136);

        android.widget.RelativeLayout i6718088b5d = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.c16_v);

        __layoutIsLoaded(ay, vw);
    }
}
