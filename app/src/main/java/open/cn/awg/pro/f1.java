/**
 ******************************************************************************
 * @file    f1.java
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

import android.view.View;
import android.view.Window;

import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;

public class f1 extends iActivity {

    public final f1 lei = this, 类 = this;
    public java.lang.String lin;
    public java.lang.String ujback;
    public open.cn.awg.pro.e1 e1 = new e1(_APPINFO);
    public open.cn.awg.pro.carton carton = new carton(_APPINFO);
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i50b5d2ce90 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i50b5d2ce90(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i8217d42a17 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i8217d42a17(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i4d951cdb0e = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i4d951cdb0e(vw);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i32ee139ba1 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i32ee139ba1(vw, me);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.f1);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.e6.getInstance().addActivity(lei);

    }

    public void __layoutIsLoaded(android.app.Activity ay, android.view.View vw) {

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

            st.xdbj(open.cn.awg.pro.R.id.f1_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.f1_v).shxtck(false);

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

        i.runlibrary.app.v.dx dx1 = st.dx(open.cn.awg.pro.R.id.dx1);
        dx1.xzzt(true);

        java.lang.String set = "/data/user/0/open.cn.awg.pro/settings/set3.inf";

        if (zf.dy(wj.dqwb(set), "true")) {

            java.lang.String te = wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a9");

            java.lang.String[] name = new java.lang.String[]{"title", "text"};

            java.lang.String[] value = new java.lang.String[]{"新手教程", te};
            gj.tz(a3.class, name, value);

        }

    }

    public void run(boolean i) {

        final i.runlibrary.app.v.wb backinfo = st.wb(open.cn.awg.pro.R.id.backs);

        final i.runlibrary.app.v.xxbj cmdlin = st.xxbj(open.cn.awg.pro.R.id.xxbj1);

        final i.runlibrary.app.v.xxbj cmdbac = st.xxbj(open.cn.awg.pro.R.id.xxbj2);
        lin = st.bjk(open.cn.awg.pro.R.id.bjk1).zf();
        lin = zf.qctwkg(lin);

        if (zf.dy(lin, "")) {

            e1.tsk("提示", "命令行不能为空");

        } else {

            try {

                if (zf.ckt(lin, "dkplayer load ")) {

                    java.lang.String url = zf.qc(lin, "dkplayer load ", null);

                    java.lang.String[] name = new java.lang.String[]{"url"};

                    java.lang.String[] value = new java.lang.String[]{url};
                    gj.tz(g2.class, name, value);
                    bak(true);

                } else if (zf.ckt(lin, "x5web load ")) {

                    java.lang.String url = zf.qc(lin, "x5web load ", null);

                    final java.lang.String[] name = new java.lang.String[]{"url"};

                    final java.lang.String[] value = new java.lang.String[]{url};
                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            gj.tz(j6.class, name, value);

                        }

                    });
                    bak(true);

                } else if (zf.ckt(lin, "paytest -p ")) {

                    java.lang.String _money = zf.qc(lin, "paytest -p ", null);

                    final java.lang.String[] name = new java.lang.String[]{"id", "name", "jg", "text"};

                    final java.lang.String[] value = new java.lang.String[]{"a2", "腕管Pro-PayTest", _money, "腕管Pro：PayTest。"};
                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            gj.tz(o1.class, name, value);

                        }

                    });
                    bak(true);

                } else if (zf.ckt(lin, "file2 load ")) {

                    java.lang.String url = zf.qc(lin, "file2 load ", " -rp ");

                    java.lang.String root = zf.qc(lin, " -rp ", null);
                    url = zf.qctwkg(url);
                    root = zf.qctwkg(root);

                    java.lang.String in = "/data/user/0/open.cn.awg.pro";

                    if (zf.ckt(url, in)) {

                        gj.jmxc(new java.lang.Runnable() {

                            public void run() {

                                java.lang.String po = "/data/user/0/open.cn.awg.pro";

                                java.lang.String[] name = new java.lang.String[]{"url", "root"};

                                java.lang.String[] value = new java.lang.String[]{po, po};
                                gj.tz(d1.class, name, value);

                            }

                        });

                    } else {

                        final java.lang.String[] name = new java.lang.String[]{"url", "root"};

                        final java.lang.String[] value = new java.lang.String[]{url, root};
                        gj.jmxc(new java.lang.Runnable() {

                            public void run() {

                                gj.tz(d1.class, name, value);

                            }

                        });

                    }
                    bak(true);

                } else if (zf.dy(lin, "awg open beta")) {

                    java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/set1.inf";
                    wj.xrwb(set1, "true");
                    bak_s("成功开启实验性功能", true);

                } else if (zf.dy(lin, "awg close beta")) {

                    java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/set1.inf";
                    wj.xrwb(set1, "false");
                    bak_s("成功关闭实验性功能", true);

                } else if (zf.dy(lin, "help")) {

                    final java.lang.String[] name = new java.lang.String[]{"url", "title", "code"};

                    final java.lang.String[] value = new java.lang.String[]{"/data/user/0/open.cn.awg.pro/data/assets/b2", "功能帮助", "super1"};
                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            gj.tz(d2.class, name, value);

                        }

                    });
                    bak(true);

                } else if (zf.dy(lin, "edit end")) {

                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            gj.tz(j7.class);

                        }

                    });
                    bak(true);

                } else if (zf.dy(lin, "awg activate")) {

                    if (e1.isJh()) {

                        bak(false);

                    } else {

                        e1.jh();
                        bak(true);

                    }

                } else if (zf.dy(lin, "error")) {

                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            java.lang.String[] name = new java.lang.String[]{"Error"};

                            java.lang.String[] value = new java.lang.String[]{"0xffff"};
                            gj.tz(a0.class, name, value);

                        }

                    });
                    bak(true);

                } else if (zf.ckt(lin, "deviceManger ")) {

                    cartoncmd(lin);

                } else if (zf.dy(lin, "su") || zf.dy(lin, "sh") || zf.dy(lin, "shell")) {

                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            gj.tz(j4.class);

                        }

                    });
                    bak(true);

                } else {

                    bak_s("未找到该指令", false);

                }

            } catch (java.lang.Throwable e) {

                bak_s(e, false);

            }

        }

    }

    public void urljson(java.lang.Object lables, java.lang.Object set2e) {

        final java.lang.Object url = set2e;

        final java.lang.Object lable = lables;
        gj.xc(new java.lang.Thread() {

            public void run() {

                java.lang.String setback = "";

                java.lang.String data = "";

                java.lang.String[] wlhq1 = new java.lang.String[]{"name1=value1", "name2=value2"};

                java.lang.String back = wl.hq(url, wlhq1, "utf-8", "uid=123;name=123;", true, "User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept=*/*||accept-language=zh-CN", 20000, 20000, null);

                if (zf.dy(back, "") || zf.dy(back, null)) {

                    setback = "0";

                } else {

                    if (zf.dy(setback, "0")) {

                        setback = "0";

                    } else {

                        try {

                            i.runlibrary.app.zf$json jo = zf.json(back);

                            org.json.JSONObject json = jo.json;

                            org.json.JSONArray list = jo.dxlb(json, "data");

                            int size = jo.cd(list);

                            while (size > 0) {

                                size = size - 1;

                                org.json.JSONObject dx = jo.dx(list, size);
                                data = String.valueOf(jo.hq(dx, lable));

                            }

                        } catch (java.lang.Throwable e) {

                            data = "0";

                        }
                        setback = data;

                    }

                }
                ujback = setback;
                bak_s(setback, true);

            }

        });

    }

    public void cartoncmd(java.lang.Object i) {

        if (zf.dy(i, "deviceManger get")) {

            carton.gets(lei);
            bak(true);

        } else if (zf.ckt(i, "deviceManger setCameraDisabled")) {

            if (zf.dy(i, "deviceManger setCameraDisabled true")) {

                carton.setCameraDisabled(lei, true);
                bak(true);

            } else if (zf.dy(i, "deviceManger setCameraDisabled false")) {

                carton.setCameraDisabled(lei, false);
                bak(true);

            } else {

                bak(false);

            }

        } else if (zf.ckt(i, "deviceManger setStatusBarDisabled")) {

            if (zf.dy(i, "deviceManger setStatusBarDisabled true")) {

                carton.setStatusBarDisabled(lei, true);
                bak(true);

            } else if (zf.dy(i, "deviceManger setStatusBarDisabled false")) {

                carton.setStatusBarDisabled(lei, false);
                bak(true);

            } else {

                bak(false);

            }

        } else if (zf.dy(i, "deviceManger lockNow")) {

            carton.lockNow(lei);
            bak(true);

        } else if (zf.dy(i, "deviceManger wipeData")) {

            carton.wipeData(lei);
            bak(true);

        } else if (zf.dy(i, "deviceManger reboot")) {

            carton.reboot(lei);
            bak(true);

        } else if (zf.dy(i, "deviceManger isDOA")) {

            boolean e0 = carton.isDeviceOwnerApp(lei);

            boolean e = e0;
            bak_s(e, true);

        } else if (zf.ckt(i, "deviceManger setUninstallBlocked")) {

            if (zf.cjw(i, " true")) {

                if (zf.ckt(i, "deviceManger setUninstallBlocked \"") && zf.cjw(i, "\" true")) {

                    java.lang.String apk = zf.qc(i, "setUninstallBlocked \"", "\" true");
                    carton.setUninstallBlocked(lei, apk, true);
                    bak(true);

                } else {

                    bak(false);

                }

            } else if (zf.cjw(i, "false")) {

                if (zf.ckt(i, "deviceManger setUninstallBlocked \"") && zf.cjw(i, "\" false")) {

                    java.lang.String apk = zf.qc(i, "setUninstallBlocked \"", "\" false");
                    carton.setUninstallBlocked(lei, apk, false);
                    bak(true);

                } else {

                    bak(false);

                }

            } else {

                bak(false);

            }

        } else if (zf.ckt(i, "deviceManger setDeviceOwnerLockScreenInfo")) {

            if (zf.ckt(i, "deviceManger setDeviceOwnerLockScreenInfo \"") && zf.cjw(i, "\"")) {

                java.lang.String text = zf.qc(i, "setDeviceOwnerLockScreenInfo \"", "\"");
                carton.setDeviceOwnerLockScreenInfo(lei, text);
                bak(true);

            } else {

                bak(false);

            }

        } else if (zf.ckt(i, "deviceManger setOrganizationName")) {

            if (zf.ckt(i, "deviceManger setOrganizationName \"") && zf.cjw(i, "\"")) {

                java.lang.String text = zf.qc(i, "setOrganizationName \"", "\"");
                carton.setOrganizationName(lei, text);
                bak(true);

            } else {

                bak(false);

            }

        } else {

            bak_s("未找到该指令", false);

        }

    }

    public void bak(boolean i) {

        final i.runlibrary.app.v.wb backinfo = st.wb(open.cn.awg.pro.R.id.backs);

        final i.runlibrary.app.v.xxbj cmdlin = st.xxbj(open.cn.awg.pro.R.id.xxbj1);

        final i.runlibrary.app.v.xxbj cmdbac = st.xxbj(open.cn.awg.pro.R.id.xxbj2);

        java.lang.String color = "";

        java.lang.String z = "";

        if (i) {

            z = "运行成功";
            color = zf.zf(open.cn.awg.pro.R.color.colorTextTrue);

        } else {

            z = "运行失败";
            color = zf.zf(open.cn.awg.pro.R.color.colorTextFalse);

        }

        final java.lang.String zs = z;

        final java.lang.String colors = color;
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                backinfo.zfys(colors);
                backinfo.zf(zs);
                cmdbac.kjd(0);
                cmdlin.kjd(8);

            }

        });

    }

    public void bak_s(java.lang.Object z, boolean i) {

        final java.lang.Object zs = z;

        final i.runlibrary.app.v.wb backinfo = st.wb(open.cn.awg.pro.R.id.backs);

        final i.runlibrary.app.v.xxbj cmdlin = st.xxbj(open.cn.awg.pro.R.id.xxbj1);

        final i.runlibrary.app.v.xxbj cmdbac = st.xxbj(open.cn.awg.pro.R.id.xxbj2);

        java.lang.String color = "";

        if (i) {

            color = zf.zf(open.cn.awg.pro.R.color.colorTextTrue);

        } else {

            color = zf.zf(open.cn.awg.pro.R.color.colorTextFalse);

        }

        final java.lang.String colors = color;
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                backinfo.zfys(colors);
                backinfo.zf(zs);
                cmdbac.kjd(0);
                cmdlin.kjd(8);

            }

        });

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

    }

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.e6.getInstance().removeActivity(lei);

    }

    public boolean onKeyDown(int kc, android.view.KeyEvent ke) {

        if (kc == 4) {

        }
        return false;

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

    private void $_onClick_i4d951cdb0e(android.view.View vw) {
        gj.gb();

    }

    private void $_onClick_i8217d42a17(android.view.View vw) {

        i.runlibrary.app.v.dx dx1 = st.dx(open.cn.awg.pro.R.id.dx1);

        run(!dx1.xzzt());

    }

    private boolean $_onLongClick_i50b5d2ce90(android.view.View vw) {

        java.lang.String wb1 = st.wb(open.cn.awg.pro.R.id.backs).zf();
        e1.tsk("详细信息", wb1);
        return true;

    }

    private void $_onClick_i34dc7bf20a(android.view.View vw) {

        final i.runlibrary.app.v.xxbj cmdlin = st.xxbj(open.cn.awg.pro.R.id.xxbj1);

        final i.runlibrary.app.v.xxbj cmdbac = st.xxbj(open.cn.awg.pro.R.id.xxbj2);
        cmdbac.kjd(8);
        cmdlin.kjd(0);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);

        android.widget.EditText i32ee139ba1 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjk1);
        i32ee139ba1.setOnTouchListener($_on_setOnTouchListener_i32ee139ba1);

        android.widget.CheckBox i3d918d2dcb = (android.widget.CheckBox) findViewById(ay, vw, open.cn.awg.pro.R.id.dx1);

        android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
        i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

        android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);
        i8217d42a17.setOnClickListener($_on_setOnClickListener_i8217d42a17);

        android.widget.TextView i50b5d2ce90 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.backs);
        i50b5d2ce90.setOnLongClickListener($_on_setOnLongClickListener_i50b5d2ce90);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        __layoutIsLoaded(ay, vw);
    }
}
