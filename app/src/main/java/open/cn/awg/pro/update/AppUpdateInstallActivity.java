/**
 ******************************************************************************
 * @file    AppUpdateInstallActivity.java
 * @author  Typheye
 * @brief   Downloads update packages and hands them to the installer flow.
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
package open.cn.awg.pro.update;

import android.view.View;
import android.view.Window;

import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.file.FileManagerActivity;
import open.cn.awg.pro.repair.ErrorMessageActivity;
import open.cn.awg.pro.tools.ShellExecutorActivity;


public class AppUpdateInstallActivity extends iActivity {

    public final AppUpdateInstallActivity lei = this, 类 = this;
    public open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public java.lang.String mmk = "";
    public java.lang.String fixcode = "";
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i4d951cdb0e = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i4d951cdb0e(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.a4);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.app.AwgProApplication.getInstance().addActivity(lei);

        java.lang.String t = sj.hqtz("url");

        java.lang.String t2 = sj.hqtz("mode");
        fixcode = sj.hqtz("fixcode");
        mmk = t2;
        download(t, t2);

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
        xt.sbxm(false);

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));

        if (zf.dy(a, "4")) {

            st.xdbj(open.cn.awg.pro.R.id.a4_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.a4_v).shxtck(false);

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

    }

    public void download(java.lang.Object lj, java.lang.Object m) {

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        final java.lang.String as1 = wj.dqwb(a2);

        final java.lang.String dl = lj.toString();

        final java.lang.Object mm = m;
        gj.xc(new java.lang.Thread() {

            public void run() {

                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.kp(open.cn.awg.pro.R.id.kp1).kjd(8);
                        st.kp(open.cn.awg.pro.R.id.kp2).kjd(8);
                        st.wb(open.cn.awg.pro.R.id.show).zf("加载中");

                    }

                });
                gj.zt(1000);
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.wb(open.cn.awg.pro.R.id.show).zf("清理旧数据");

                        java.lang.String d = "/storage/emulated/0/Android/data/open.cn.awg.pro/files/update/";

                        java.lang.String m = "rm -rf \"" + d + "\"";
                        com.demo.e3.cmd(lei, m, false);
                        d = "/data/user/0/open.cn.awg.pro/fix/";
                        m = "rm -rf \"" + d + "\"";
                        com.demo.e3.cmd(lei, m, false);

                    }

                });
                gj.zt(500);
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.wb(open.cn.awg.pro.R.id.show).zf("下载更新");
                        st.kp(open.cn.awg.pro.R.id.kp2).kjd(0);

                    }

                });
                gj.zt(500);

                java.lang.String a = dl;

                java.lang.String bb = "";

                if (zf.dy(mm, "update")) {

                    bb = "/storage/emulated/0/Android/data/open.cn.awg.pro/files/update/update.apk";

                } else if (zf.dy(mm, "fix")) {

                    bb = "/data/user/0/open.cn.awg.pro/fix/fix.sh";

                }

                final java.lang.String b = bb;

                int dg = wl.xz(a, b, true, null, "utf-8", null, true, null);

                java.lang.String bk = "";

                if (dg == 0) {

                    bk = "下载成功";

                } else if (dg == 1) {

                    bk = "已下载";

                } else if (dg == -1) {

                    bk = "下载失败";

                } else {

                    bk = "未知错误";

                }

                final java.lang.String df = bk;
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.wb(open.cn.awg.pro.R.id.show).zf(df);
                        st.kp(open.cn.awg.pro.R.id.kp2).kjd(8);

                    }

                });
                gj.zt(1000);

                if (dg == 0 || dg == 1) {

                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            st.wb(open.cn.awg.pro.R.id.show).zf("准备安装");

                        }

                    });
                    gj.zt(500);
                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            st.wb(open.cn.awg.pro.R.id.show).zf("等待安装");

                        }

                    });
                    gj.zt(500);
                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            st.kp(open.cn.awg.pro.R.id.kp2).kjd(0);
                            st.kp(open.cn.awg.pro.R.id.kp1).kjd(0);

                        }

                    });

                    if (zf.dy(mm, "update")) {

                        load();

                    } else if (zf.dy(mm, "fix")) {

                        load2();

                    }

                } else {

                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            st.wb(open.cn.awg.pro.R.id.show).zf("取消更新");
                            st.kp(open.cn.awg.pro.R.id.kp2).kjd(8);

                        }

                    });
                    gj.zt(500);
                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            gj.gb();

                        }

                    });

                }

            }

        });

    }

    public void load() {

        java.lang.String ftitle = "/data/user/0/open.cn.awg.pro/cache/file/title";
        wj.xrwb(ftitle, "< 更新目录");

        java.lang.String path = "/storage/emulated/0/Android/data/open.cn.awg.pro/files/update/";

        final java.lang.String[] name = new java.lang.String[]{"url", "root"};

        final java.lang.String[] value = new java.lang.String[]{path, path};
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                gj.tz(FileManagerActivity.class, name, value);

            }

        });

    }

    public void load2() {

        java.lang.String bb = "/data/user/0/open.cn.awg.pro/fix/fix.sh";

        java.lang.String nrs = wj.dqwb(bb);

        final java.lang.String[] name = new java.lang.String[]{"sh", "auto", "fixcode", "type"};

        final java.lang.String[] value = new java.lang.String[]{nrs, "enable", fixcode, "fixupdate"};
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                gj.tz(ShellExecutorActivity.class, name, value);

            }

        });
        gj.gb();

    }

    public boolean onKeyDown(int kc, android.view.KeyEvent ke) {

        if (kc == 4) {

        }
        return false;

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

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.app.AwgProApplication.getInstance().removeActivity(lei);

    }

    private void $_onClick_i4d951cdb0e(android.view.View vw) {

        if (st.kp(open.cn.awg.pro.R.id.kp1).kjd() == 0) {

            gj.gb();
            e1.tsk("提示", "您已取消更新");

        } else {

            gj.xc(new java.lang.Thread() {

                public void run() {

                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            java.lang.String[] name = new java.lang.String[]{"Msg"};

                            java.lang.String[] value = new java.lang.String[]{"中断成功 点击重启"};
                            gj.tz(ErrorMessageActivity.class, name, value);

                        }

                    });

                }

            });

        }

    }

    private void $_onClick_i34dc7bf20a(android.view.View vw) {

        if (zf.dy(mmk, "update")) {

            load();

        } else if (zf.dy(mmk, "fix")) {

            load2();

        }

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);

        android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
        i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);

        __layoutIsLoaded(ay, vw);
    }
}
