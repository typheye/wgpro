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

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.file.FileManagerActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.repair.ErrorMessageActivity;
import open.cn.awg.pro.tools.ShellExecutorActivity;

public class AppUpdateInstallActivity extends BaseAwgActivity {

    public final AppUpdateInstallActivity lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public String mmk = "";
    public String fixcode = "";
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.app_update_install);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        String t = sj.hqtz("url");

        String t2 = sj.hqtz("mode");
        fixcode = sj.hqtz("fixcode");
        mmk = t2;
        download(t, t2);

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        i.runlibrary.app.xt$pm pm = xt.pm();

        if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4s.inf")), "false")) {

            if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4.inf")), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }
        xt.sbxm(false);

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb(AppPaths.appPath("settings/f17/set1.inf")));

        if (zf.dy(a, "4")) {

            st.xdbj(R.id.app_update_install_root).shxtck(true);

            String clr = zf.zf(R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(R.id.app_update_install_root).shxtck(false);
        applyFullscreenWindow();

        }

        if (zf.dy(a, "1")) {

            st.xdbj(R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "2")) {

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (wj.cz(a001) || zf.dy(wj.dqwb(a001), "true")) {

                i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
                wtab.nbj(0, "3dp", 0, "3dp");
                wtab.dqfs("center");

            } else {

                i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
                wtab.nbj(0, "9dp", 0, "9dp");
                wtab.dqfs("center");

            }
            st.xdbj(R.id.xdbj1).nbj(0, 0, 0, 0);

        } else if (zf.dy(a, "3")) {

            st.xdbj(R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "4")) {

            st.xdbj(R.id.xdbj1).nbj(0, 0, 0, 0);

        }

    }

    public void download(Object lj, Object m) {

        final String a2 = AppPaths.appPath("settings/a3.inf");

        final String as1 = wj.dqwb(a2);

        final String dl = lj.toString();

        final Object mm = m;
        gj.xc(new Thread() {

            public void run() {

                gj.jmxc(new Runnable() {

                    public void run() {

                        st.kp(R.id.kp1).kjd(8);
                        st.kp(R.id.kp2).kjd(8);
                        st.wb(R.id.show).zf("加载中");

                    }

                });
                gj.zt(1000);
                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.show).zf("清理旧数据");

                        String d = AppPaths.externalFilesPath("update/");

                        String m = "rm -rf \"" + d + "\"";
                        com.demo.e3.cmd(lei, m, false);
                        d = AppPaths.appPath("fix/");
                        m = "rm -rf \"" + d + "\"";
                        com.demo.e3.cmd(lei, m, false);

                    }

                });
                gj.zt(500);
                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.show).zf("下载更新");
                        st.kp(R.id.kp2).kjd(0);

                    }

                });
                gj.zt(500);

                String a = dl;

                String bb = "";

                if (zf.dy(mm, "update")) {

                    bb = AppPaths.externalFilesPath("update/update.apk");

                } else if (zf.dy(mm, "fix")) {

                    bb = AppPaths.appPath("fix/fix.sh");

                }

                final String b = bb;

                int dg = wl.xz(a, b, true, null, "utf-8", null, true, null);

                String bk = "";

                if (dg == 0) {

                    bk = "下载成功";

                } else if (dg == 1) {

                    bk = "已下载";

                } else if (dg == -1) {

                    bk = "下载失败";

                } else {

                    bk = "未知错误";

                }

                final String df = bk;
                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.show).zf(df);
                        st.kp(R.id.kp2).kjd(8);

                    }

                });
                gj.zt(1000);

                if (dg == 0 || dg == 1) {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            st.wb(R.id.show).zf("准备安装");

                        }

                    });
                    gj.zt(500);
                    gj.jmxc(new Runnable() {

                        public void run() {

                            st.wb(R.id.show).zf("等待安装");

                        }

                    });
                    gj.zt(500);
                    gj.jmxc(new Runnable() {

                        public void run() {

                            st.kp(R.id.kp2).kjd(0);
                            st.kp(R.id.kp1).kjd(0);

                        }

                    });

                    if (zf.dy(mm, "update")) {

                        load();

                    } else if (zf.dy(mm, "fix")) {

                        load2();

                    }

                } else {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            st.wb(R.id.show).zf("取消更新");
                            st.kp(R.id.kp2).kjd(8);

                        }

                    });
                    gj.zt(500);
                    gj.jmxc(new Runnable() {

                        public void run() {

                            gj.gb();

                        }

                    });

                }

            }

        });

    }

    public void load() {

        String ftitle = AppPaths.appPath("cache/file/title");
        wj.xrwb(ftitle, "< 更新目录");

        String path = AppPaths.externalFilesPath("update/");

        final String[] name = new String[]{"url", "root"};

        final String[] value = new String[]{path, path};
        gj.jmxc(new Runnable() {

            public void run() {

                gj.tz(FileManagerActivity.class, name, value);

            }

        });

    }

    public void load2() {

        String bb = AppPaths.appPath("fix/fix.sh");

        String nrs = wj.dqwb(bb);

        final String[] name = new String[]{"sh", "auto", "fixcode", "type"};

        final String[] value = new String[]{nrs, "enable", fixcode, "fixupdate"};
        gj.jmxc(new Runnable() {

            public void run() {

                gj.tz(ShellExecutorActivity.class, name, value);

            }

        });
        gj.gb();

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

        }
        return false;

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
            applyWindowModeFromSettings();

    }

    public void onRestart() {
        super.onRestart();
            applyWindowModeFromSettings();

    }

    public void onStart() {
        super.onStart();
            applyWindowModeFromSettings();

        i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(R.id.qtgd1);

        qtgd1.v.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View vw, MotionEvent me) {
                if (me.getAction() == MotionEvent.ACTION_SCROLL && me.isFromSource(InputDeviceCompat.SOURCE_ROTARY_ENCODER)) {

                    float delta = -me.getAxisValue(MotionEventCompat.AXIS_SCROLL) *
                            ViewConfigurationCompat.getScaledVerticalScrollFactor(
                                    ViewConfiguration.get(lei), lei
                            );

                    vw.scrollBy(0, Math.round(delta));
                    return true;
                }
                return false;
            }
        });

        qtgd1.v.requestFocus();

    }

    public void onResume() {
        super.onResume();
            applyWindowModeFromSettings();

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onTx2Click(View vw) {

        if (st.kp(R.id.kp1).kjd() == 0) {

            gj.gb();
            e1.tsk("提示", "您已取消更新");

        } else {

            gj.xc(new Thread() {

                public void run() {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            String[] name = new String[]{"Msg"};

                            String[] value = new String[]{"中断成功 点击重启"};
                            gj.tz(ErrorMessageActivity.class, name, value);

                        }

                    });

                }

            });

        }

    }

    private void onTx1Click(View vw) {

        if (zf.dy(mmk, "update")) {

            load();

        } else if (zf.dy(mmk, "fix")) {

            load2();

        }

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);

        __layoutIsLoaded(ay, vw);
    }
}
