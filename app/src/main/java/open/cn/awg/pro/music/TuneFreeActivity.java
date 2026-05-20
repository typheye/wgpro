/**
 ******************************************************************************
 * @file    TuneFreeActivity.java
 * @author  Typheye
 * @brief   TuneFree music plugin screen.
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
package open.cn.awg.pro.music;

import android.graphics.Bitmap;
import android.view.View;
import android.view.Window;

import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.R;


public class TuneFreeActivity extends iActivity {

    public final TuneFreeActivity lei = this, 类 = this;
    public java.lang.String um = "";
    public java.io.File f;
    public open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public open.cn.awg.pro.music.TuneFreeMusicBridge e12 = new TuneFreeMusicBridge(_APPINFO);
    public boolean state_login_xh = true;
    public java.lang.String cookiepath = "/data/user/0/open.cn.awg.pro/data/plugin/tunefree/cookie";
    private final android.view.View.OnClickListener $_on_setOnClickListener_i6f2c7751cf = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i6f2c7751cf(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i4a4248a0f2 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i4a4248a0f2(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i11aeec1890 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i11aeec1890(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i0b66a667b1 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i0b66a667b1(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i171ea34f1a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i171ea34f1a(vw);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i32ee139ba1 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i32ee139ba1(vw, me);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i4d951cdb0e = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i4d951cdb0e(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i20c2777fab = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i20c2777fab(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i20c2777fab = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i20c2777fab(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i3167b45174 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i3167b45174(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i3167b45174 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i3167b45174(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_c0d3e00d2b = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_c0d3e00d2b(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_c0d3e00d2b = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_c0d3e00d2b(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i171cedf1eb = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i171cedf1eb(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i171cedf1eb = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i171cedf1eb(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i2202ede8ff = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i2202ede8ff(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i2202ede8ff = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i2202ede8ff(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d26d5d7080(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.l5_a2_nimin_d0415_y2022);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.app.AwgProApplication.getInstance().addActivity(lei);
        show(0);

    }

    public void __layoutIsLoaded(android.app.Activity ay, android.view.View vw) {

        if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f8/set4.inf"), "true")) {

        }

        i.runlibrary.app.xt$pm pm = xt.pm();

        if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4s.inf"), "false")) {

            if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4.inf"), "true")) {

                getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));

        if (zf.dy(a, "4")) {

            st.xdbj(open.cn.awg.pro.R.id.l5_a2_nimin_d0415_y2022_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.l5_a2_nimin_d0415_y2022_v).shxtck(false);

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

        if (zf.dy(a, "1")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "2")) {

            java.lang.String a001 = "/data/user/0/open.cn.awg.pro/settings/dpi.inf";

            if (wj.cz(a001) || zf.dy(wj.dqwb(a001), "true")) {

                i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
                wtab.nbj(0, "3dp", 0, "3dp");
                wtab.dqfs("center");

            } else {

                i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
                wtab.nbj(0, "9dp", 0, "9dp");
                wtab.dqfs("center");

            }
            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0, 0, 0, 0);

        } else if (zf.dy(a, "3")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "4")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0, 0, 0, 0);

        }

        i.runlibrary.app.v.xlcd xlcd1 = st.xlcd(open.cn.awg.pro.R.id.xlcd1);

        java.lang.String[] sz = new java.lang.String[]{"单曲"};
        xlcd1.csh(sz);

    }

    public void login$exit() {
        state_login_xh = false;
        show(0);

    }

    public void jz() {

        if (wj.cz(cookiepath) && !zf.dy(wj.dqwb(cookiepath), "")) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.xdbj(open.cn.awg.pro.R.id.xdbj4).kjd(0);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj5).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj10).kjd(0);

                }

            });

        } else {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.xdbj(open.cn.awg.pro.R.id.xdbj4).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj5).kjd(0);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj10).kjd(8);

                }

            });

        }

    }

    public void login_xh(java.lang.Object url_, java.lang.Object key_) {

        final java.lang.Object url = url_;

        final java.lang.Object key = key_;

        final java.lang.String ljf = wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a17");

        final java.lang.String set35 = "/data/user/0/open.cn.awg.pro/settings/other/0xO7YX2/url";
        gj.xc(new java.lang.Thread() {

            public void run() {

                state_login_xh = true;
                ewm(String.valueOf(url));
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.wb(open.cn.awg.pro.R.id.wb11).zf("扫描二维码完成登录");

                    }

                });
                show(3);

                while (true) {

                    gj.zt(3000);

                    if (!state_login_xh) {

                        break;

                    }

                    java.lang.String setback = "";

                    java.lang.String code = "";

                    java.lang.String url = zf.qctwkg(wj.dqwb(set35)) + "/login/qr/check";

                    java.lang.String whq = "?key=" + key + ljf + "timestamp=" + xt.sj(4);

                    java.lang.String back = wl.hq(url + whq, null, "utf-8");

                    if (zf.dy(back, "") || zf.dy(back, null)) {

                        setback = "0";

                    } else {

                        try {

                            code = e1.readJson(back, "code");

                        } catch (java.lang.Throwable __$_e__) {

                            back = "0";

                        }
                        setback = back;

                    }

                    if (zf.dy(setback, "0")) {

                        show(0);
                        e1.tsk("提示", "加载失败,请重试");
                        break;

                    } else {

                        if (zf.dy(code, "803")) {

                            java.lang.String coo = e1.readJson(setback, "cookie");
                            wj.xrwb(cookiepath, coo);
                            show(0);
                            e1.tsk("提示", "登录成功");
                            break;

                        } else if (zf.dy(code, "800")) {

                            gj.jmxc(new java.lang.Runnable() {

                                public void run() {

                                    st.wb(open.cn.awg.pro.R.id.wb11).zf("二维码已过期");
                                    st.tx(open.cn.awg.pro.R.id.tx3).kjd(8);

                                }

                            });

                        } else if (zf.dy(code, "802")) {

                            gj.jmxc(new java.lang.Runnable() {

                                public void run() {

                                    st.wb(open.cn.awg.pro.R.id.wb11).zf("扫码成功 等待确认");
                                    st.tx(open.cn.awg.pro.R.id.tx3).kjd(8);

                                }

                            });

                        } else {

                        }

                    }

                }

            }

        });

    }

    public void login() {

        final java.lang.String ljf = wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a17");

        final java.lang.String set35 = "/data/user/0/open.cn.awg.pro/settings/other/0xO7YX2/url";
        gj.xc(new java.lang.Thread() {

            public void run() {

                show(2);

                java.lang.String key = "";

                java.lang.String url = zf.qctwkg(wj.dqwb(set35)) + "/login/qr/key?timestamp=" + xt.sj(4);

                java.lang.String setback = "";

                java.lang.String f = "User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept-language=zh-CN";

                java.lang.String back = wl.hq(url, null, "utf-8");

                if (zf.dy(back, "") || zf.dy(back, null)) {

                    setback = "0";

                } else {

                    try {

                        key = e1.readJson(back, "data");
                        key = e1.readJson(key, "unikey");
                        url = zf.qctwkg(wj.dqwb(set35)) + "/login/qr/create";

                        java.lang.String whq = "?key=" + key + ljf + "timestamp=" + xt.sj(4);
                        back = wl.hq(url + whq, null, "utf-8");

                        if (zf.dy(back, "") || zf.dy(back, null)) {

                            setback = "0";

                        } else {

                            try {

                                back = e1.readJson(back, "data");
                                back = e1.readJson(back, "qrurl");

                            } catch (java.lang.Throwable __$_e__) {

                                back = "0";

                            }
                            setback = back;

                        }

                    } catch (java.lang.Throwable __$_e__) {

                    }

                }

                if (zf.dy(setback, "0")) {

                    show(0);
                    e1.tsk("提示", "加载失败,请重试");

                } else {

                    login_xh(setback, key);

                }

            }

        });

    }

    public void show(int i) {

        final i.runlibrary.app.v.wb tab = st.wb(open.cn.awg.pro.R.id.Tab);

        final i.runlibrary.app.v.xxbj x1 = st.xxbj(open.cn.awg.pro.R.id.xxbj1);

        final i.runlibrary.app.v.xxbj x2 = st.xxbj(open.cn.awg.pro.R.id.xxbj6);

        final i.runlibrary.app.v.xxbj x3 = st.xxbj(open.cn.awg.pro.R.id.xxbj2);

        final i.runlibrary.app.v.xxbj x4 = st.xxbj(open.cn.awg.pro.R.id.xxbj8);

        final i.runlibrary.app.v.xxbj x5 = st.xxbj(open.cn.awg.pro.R.id.xxbj12);

        if (i == 0) {

            jz();
            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    xt.sbxm(true);
                    tab.zf("< TuneFree");
                    x1.kjd(8);
                    x2.kjd(8);
                    x3.kjd(0);
                    x4.kjd(8);
                    x5.kjd(8);

                }

            });

        } else if (i == 1) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    xt.sbxm(true);
                    tab.zf("< 搜索歌曲");
                    x1.kjd(0);
                    x2.kjd(8);
                    x3.kjd(8);
                    x4.kjd(8);
                    x5.kjd(8);

                }

            });

        } else if (i == 2) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    xt.sbxm(true);
                    tab.zf("TuneFree");
                    x1.kjd(8);
                    x2.kjd(0);
                    x3.kjd(8);
                    x4.kjd(8);
                    x5.kjd(8);

                }

            });

        } else if (i == 3) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    xt.sbxm(false);
                    tab.zf("TuneFree");
                    x1.kjd(8);
                    x2.kjd(8);
                    x3.kjd(8);
                    x4.kjd(0);
                    x5.kjd(8);

                }

            });

        } else if (i == 4) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    xt.sbxm(false);
                    tab.zf("TuneFree");
                    x1.kjd(8);
                    x2.kjd(8);
                    x3.kjd(8);
                    x4.kjd(8);
                    x5.kjd(0);

                }

            });

        }

    }

    public void ewm(java.lang.String url) {

        final java.lang.String qr = url;
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                try {

                    android.widget.ImageView imageView = findViewById(R.id.tx3);
                    Bitmap bitmap = com.demo.e4.createQRCodeBitmap(qr, 800, 800, "UTF-8", "H", "1");
                    imageView.setImageBitmap(bitmap);

                    st.tx(open.cn.awg.pro.R.id.tx3).kjd(0);

                } catch (java.lang.Throwable e) {

                    st.tx(open.cn.awg.pro.R.id.tx3).kjd(8);

                }

            }

        });

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }
        jz();

    }

    public void onRestart() {
        super.onRestart();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

    }

    public void onStart() {
        super.onStart();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

        i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(open.cn.awg.pro.R.id.qtgd1);

        qtgd1.v.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View vw, android.view.MotionEvent me) {
                if (me.getAction() == android.view.MotionEvent.ACTION_SCROLL && me.isFromSource(androidx.core.view.InputDeviceCompat.SOURCE_ROTARY_ENCODER)) {

                    float delta = -me.getAxisValue(androidx.core.view.MotionEventCompat.AXIS_SCROLL) *
                            androidx.core.view.ViewConfigurationCompat.getScaledVerticalScrollFactor(
                                    android.view.ViewConfiguration.get(lei), lei
                            );

                    vw.scrollBy(0, java.lang.Math.round(delta));
                    return true;
                }
                return false;
            }
        });

        qtgd1.v.requestFocus();

    }

    public void onResume() {
        super.onResume();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

    }

    public boolean onKeyDown(int kc, android.view.KeyEvent ke) {

        if (kc == 4) {

            if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< TuneFree")) {

                gj.gb();

            } else if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< 搜索歌曲")) {

                show(0);

            }

        }
        return false;

    }

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.app.AwgProApplication.getInstance().removeActivity(lei);

    }

    private void $_onClick_d26d5d7080(android.view.View vw) {

        if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< TuneFree")) {

            gj.gb();

        } else if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< 搜索歌曲")) {

            show(0);

        }

    }

    private void $_onClick_i2202ede8ff(android.view.View vw) {
        gj.xc(new java.lang.Thread() {

            public void run() {

                show(2);
                gj.zt(500);
                show(4);

            }

        });

    }

    private boolean $_onLongClick_i2202ede8ff(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb7).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb8).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i171cedf1eb(android.view.View vw) {
        e1.goplu("20007", "TuneFree", false);

    }

    private boolean $_onLongClick_i171cedf1eb(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb12).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb13).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_c0d3e00d2b(android.view.View vw) {
        login();

    }

    private boolean $_onLongClick_c0d3e00d2b(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb9).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb10).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i3167b45174(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.bjk(open.cn.awg.pro.R.id.bjk1).zf("");

            }

        });
        show(1);

    }

    private boolean $_onLongClick_i3167b45174(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb3).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb4).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i20c2777fab(android.view.View vw) {
        e1.tsk_QR("功能帮助", "此服务由TuneFree提供,如有侵权,请联系498978473@qq.com\n\n体验完整TuneFree\n扫描下方二维码继续", "https://tunefree.fun");

    }

    private boolean $_onLongClick_i20c2777fab(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb5).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb6).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i4d951cdb0e(android.view.View vw) {
        gj.gb();

    }

    private boolean $_onTouch_i32ee139ba1(android.view.View vw, android.view.MotionEvent me) {

        if (!vw.isFocused()) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(open.cn.awg.pro.R.id.qtgd1);
                    qtgd1.v.requestFocus();

                }

            });

        }
        return false;

    }

    private void $_onClick_i171ea34f1a(android.view.View vw) {

        final java.lang.String l = st.bjk(open.cn.awg.pro.R.id.bjk1).zf();

        final int p = st.xlcd(open.cn.awg.pro.R.id.xlcd1).dqzsxmxh();
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                java.lang.String[] a1 = new java.lang.String[]{"name", "type"};

                java.lang.Object[] a2 = new java.lang.Object[]{l, p};
                gj.tz(TuneFreeCloudSearchActivity.class, a1, a2);

            }

        });

    }

    private void $_onClick_i34dc7bf20a(android.view.View vw) {
        show(0);

    }

    private void $_onClick_i0b66a667b1(android.view.View vw) {
        login$exit();

    }

    private void $_onClick_i11aeec1890(android.view.View vw) {
        show(0);

    }

    private void $_onClick_i4a4248a0f2(android.view.View vw) {
        gj.xc(new java.lang.Thread() {

            public void run() {

                show(2);
                gj.zt(500);
                show(0);

            }

        });

    }

    private void $_onClick_i6f2c7751cf(android.view.View vw) {
        gj.xc(new java.lang.Thread() {

            public void run() {

                show(2);
                wj.sc(cookiepath);
                gj.zt(500);
                show(0);
                e1.tsk("提示", "已退出登录");

            }

        });

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
        d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

        android.widget.RelativeLayout i2202ede8ff = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj4);
        i2202ede8ff.setOnClickListener($_on_setOnClickListener_i2202ede8ff);
        i2202ede8ff.setOnLongClickListener($_on_setOnLongClickListener_i2202ede8ff);

        android.widget.RelativeLayout i171cedf1eb = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj6);
        i171cedf1eb.setOnClickListener($_on_setOnClickListener_i171cedf1eb);
        i171cedf1eb.setOnLongClickListener($_on_setOnLongClickListener_i171cedf1eb);

        android.widget.RelativeLayout c0d3e00d2b = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj5);
        c0d3e00d2b.setOnClickListener($_on_setOnClickListener_c0d3e00d2b);
        c0d3e00d2b.setOnLongClickListener($_on_setOnLongClickListener_c0d3e00d2b);

        android.widget.RelativeLayout i3167b45174 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj2);
        i3167b45174.setOnClickListener($_on_setOnClickListener_i3167b45174);
        i3167b45174.setOnLongClickListener($_on_setOnLongClickListener_i3167b45174);

        android.widget.RelativeLayout i20c2777fab = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj3);
        i20c2777fab.setOnClickListener($_on_setOnClickListener_i20c2777fab);
        i20c2777fab.setOnLongClickListener($_on_setOnLongClickListener_i20c2777fab);

        android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
        i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

        android.widget.EditText i32ee139ba1 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjk1);
        i32ee139ba1.setOnTouchListener($_on_setOnTouchListener_i32ee139ba1);

        android.widget.Button i171ea34f1a = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an1);
        i171ea34f1a.setOnClickListener($_on_setOnClickListener_i171ea34f1a);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        android.widget.Button i0b66a667b1 = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an2);
        i0b66a667b1.setOnClickListener($_on_setOnClickListener_i0b66a667b1);

        android.widget.ImageView i11aeec1890 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx4);
        i11aeec1890.setOnClickListener($_on_setOnClickListener_i11aeec1890);

        android.widget.ImageView i4a4248a0f2 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx5);
        i4a4248a0f2.setOnClickListener($_on_setOnClickListener_i4a4248a0f2);

        android.widget.ImageView i6f2c7751cf = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx6);
        i6f2c7751cf.setOnClickListener($_on_setOnClickListener_i6f2c7751cf);

        android.support.v4.widget.NestedScrollView a6dab51133 = (android.support.v4.widget.NestedScrollView) findViewById(ay, vw, open.cn.awg.pro.R.id.qtgd1);

        __layoutIsLoaded(ay, vw);
    }
}
