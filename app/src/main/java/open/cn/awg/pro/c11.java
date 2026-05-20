/**
 ******************************************************************************
 * @file    c11.java
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

public class c11 extends iActivity {

    public final c11 lei = this, 类 = this;
    private final android.view.View.OnClickListener $_on_setOnClickListener_i81652f77b6 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i81652f77b6(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_aaeebbd136 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_aaeebbd136(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_bf684e97e0 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_bf684e97e0(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_dc4433170e = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_dc4433170e(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_dc4433170e = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_dc4433170e(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i34dc7bf20a = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i34dc7bf20a(vw);
        }

    };

    private boolean $_onLongClick_i34dc7bf20a(android.view.View vw) {

        open.cn.awg.pro.e10 e10 = new e10(_APPINFO);

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        java.lang.Object b = lb.lbcfsj(open.cn.awg.pro.R.id.wb2);
        e10.ltspostat(b);
        st.kp(open.cn.awg.pro.R.id.kp7).kjd(8);
        return true;

    }

    private boolean $_onLongClick_dc4433170e(android.view.View vw) {

        open.cn.awg.pro.e10 e10 = new e10(_APPINFO);

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        java.lang.Object b = lb.lbcfsj(open.cn.awg.pro.R.id.wb2);
        e10.ltspostat(b);
        st.kp(open.cn.awg.pro.R.id.kp7).kjd(8);
        return true;

    }

    private void $_onClick_dc4433170e(android.view.View vw) {

        open.cn.awg.pro.e1 e1 = new e1(_APPINFO);

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        java.lang.Object a = lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

        java.lang.Object b = lb.lbcfsj(-3);

        java.lang.Object c = lb.lbcfsj(-2);
        b = zf.qctwkg(b);

        if (zf.dy(b, "w0") || zf.dy(b, "") || zf.dy(b, "0") || zf.dy(b, null) || zf.dy(b, "null")) {

            b = "未注册用户";

        }
        c = zf.qctwkg(c);

        if (zf.dy(c, "未知") || zf.dy(c, "0") || zf.dy(c, "") || zf.dy(c, "null") || zf.dy(c, null)) {

            c = "未知IP属地";

        }
        e1.tsk("详细信息", "[用户昵称]\n" + a + "\n\n[用户UID]\n" + b + "\n\n[IP属地]\n" + c);

    }

    private void $_onClick_bf684e97e0(android.view.View vw) {

        open.cn.awg.pro.e1 e1 = new e1(_APPINFO);

        java.lang.String a = st.wb(vw, open.cn.awg.pro.R.id.wb8).zf();

        java.lang.String b = st.wb(vw, open.cn.awg.pro.R.id.wb9).zf();

        if (zf.dy(a, "[附件]图片")) {

            if (zf.dy(b, "")) {

                e1.tsk("提示", "链接指向错误");

            } else {

                if (!zf.dy(b, "")) {

                    java.lang.String c1 = b;

                    try {

                        java.lang.String[] cc = zf.fg(b, "/");

                        java.lang.String c2 = cc[cc.length - 1];

                        if (!zf.dy(c1, "") && !zf.dy(c2, "")) {

                            final java.lang.String[] name = new java.lang.String[]{"url", "md5"};

                            final java.lang.String[] value = new java.lang.String[]{c1, c2};
                            gj.jmxc(new java.lang.Runnable() {

                                public void run() {

                                    gj.tz(d3.class, name, value);

                                }

                            });

                        } else {

                            e1.tsk("提示", "链接指向错误");

                        }

                    } catch (java.lang.Throwable e) {

                        e1.upload_error(e, "c11.*");

                    }

                } else {

                    e1.tsk("提示", "链接指向错误");

                }

            }

        } else if (zf.dy(a, "[附件]音频") || zf.dy(a, "[附件]视频")) {

            if (zf.dy(b, "")) {

                e1.tsk("提示", "链接指向错误");

            } else {

                if (!zf.dy(b, "")) {

                    java.lang.String c1 = b;

                    try {

                        java.lang.String[] cc = zf.fg(b, "/");

                        java.lang.String c2 = cc[cc.length - 1];

                        if (!zf.dy(c1, "") && !zf.dy(c2, "")) {

                            java.lang.String[] name = new java.lang.String[]{"url"};

                            java.lang.String[] value = new java.lang.String[]{c1};
                            gj.tz(g2.class, name, value);

                        } else {

                            e1.tsk("提示", "链接指向错误");

                        }

                    } catch (java.lang.Throwable e) {

                        e1.upload_error(e, "c11.*");

                    }

                } else {

                    e1.tsk("提示", "链接指向错误");

                }

            }

        } else if (zf.dy(a, "[附件]文件")) {

            if (zf.dy(b, "")) {

                e1.tsk("提示", "链接指向错误");

            } else {

                if (!zf.dy(b, "")) {

                    java.lang.String c1 = b;

                    try {

                        java.lang.String[] cc = zf.fg(b, "/");

                        java.lang.String c2 = cc[cc.length - 1];

                        if (!zf.dy(c1, "") && !zf.dy(c2, "")) {

                            final java.lang.String[] name = new java.lang.String[]{"url"};

                            final java.lang.String[] value = new java.lang.String[]{c1};
                            gj.jmxc(new java.lang.Runnable() {

                                public void run() {

                                    gj.tz(l2.class, name, value);

                                }

                            });

                        } else {

                            e1.tsk("提示", "链接指向错误");

                        }

                    } catch (java.lang.Throwable e) {

                        e1.upload_error(e, "c11.*");

                    }

                } else {

                    e1.tsk("提示", "链接指向错误");

                }

            }

        } else {

            e1.tsk("提示", "未知类型附件");

        }

    }

    private boolean $_onLongClick_aaeebbd136(android.view.View vw) {

        open.cn.awg.pro.e1 e1 = new e1(_APPINFO);

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        java.lang.Object a = lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

        java.lang.Object b = lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

        java.lang.Object c = lb.lbcfsj(open.cn.awg.pro.R.id.wb3);

        java.lang.Object d = lb.lbcfsj(-2);

        java.lang.Object e = lb.lbcfsj(-3);

        java.lang.String[] name = new java.lang.String[]{"name", "time", "data", "address", "luid"};

        java.lang.Object[] value = new java.lang.Object[]{b, c, a, d, e};
        gj.tz(i3.class, name, value);
        return true;

    }

    private void $_onClick_i81652f77b6(android.view.View vw) {

        open.cn.awg.pro.e1 e1 = new e1(_APPINFO);

        java.lang.String a = st.wb(vw, open.cn.awg.pro.R.id.wb10).zf();
        e1.tsk("消息详情", a);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnLongClickListener($_on_setOnLongClickListener_i34dc7bf20a);

        android.widget.TextView i0d2d4d9236 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb3);

        android.widget.LinearLayout e1433e26d6 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj1);

        android.widget.TextView i7c3e3c6b5d = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb2);

        android.widget.LinearLayout dc4433170e = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj4k);
        dc4433170e.setOnLongClickListener($_on_setOnLongClickListener_dc4433170e);
        dc4433170e.setOnClickListener($_on_setOnClickListener_dc4433170e);

        android.widget.TextView e943ebd71e = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb4);

        android.widget.TextView ff56a8e7f6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb1);

        android.widget.LinearLayout bf684e97e0 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbjfj);
        bf684e97e0.setOnClickListener($_on_setOnClickListener_bf684e97e0);

        android.widget.RelativeLayout aaeebbd136 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj1);
        aaeebbd136.setOnLongClickListener($_on_setOnLongClickListener_aaeebbd136);

        android.widget.RelativeLayout i9a2a79f0f0 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.c11_v);

        android.widget.TextView i81652f77b6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb10);
        i81652f77b6.setOnClickListener($_on_setOnClickListener_i81652f77b6);

        __layoutIsLoaded(ay, vw);
    }
}
