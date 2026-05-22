/**
 ******************************************************************************
 * @file    DownloadProgressActivity.java
 * @author  Typheye
 * @brief   Generic file download progress screen.
 ******************************************************************************
 * @attention
 *
 * Copyright (c) 2021-2026 Typheye. All rights reserved.
 * <p>
 * This software is licensed under terms that can be found in the LICENSE file
 * in the root directory of this software component.
 * If no LICENSE file comes with this software, it is provided AS-IS.
 *
 ******************************************************************************
 */
package open.cn.awg.pro.download;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
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
import open.cn.awg.pro.R;
import open.cn.awg.pro.repair.ErrorMessageActivity;

public class DownloadProgressActivity extends BaseAwgActivity {

    public final DownloadProgressActivity lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public Thread down;
    private final View.OnClickListener tx1ClickListener = this::onTx1Click;
    private final View.OnClickListener tx2ClickListener = this::onTx2Click;

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.download_progress);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        String t = sj.hqtz("url");

        String p = sj.hqtz("path");
        download(t, p);

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

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

            st.xdbj(R.id.download_progress_root).shxtck(true);

            String clr = zf.zf(R.color.colorTab);
            setStatusBarColor(clr);

        } else {

            st.xdbj(R.id.download_progress_root).shxtck(false);
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

    public void download(Object lj, Object path) {

        final String dl = lj.toString();

        final String paths = path.toString();
        down = gj.xc(new Thread() {

            public void run() {

                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.show).zf("加载中");

                    }

                });
                gj.zt(1000);
                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.show).zf("准备下载");

                    }

                });
                gj.zt(250);
                gj.jmxc(new Runnable() {

                    public void run() {

                        st.kp(R.id.kp2).kjd(0);
                        st.wb(R.id.show).zf("正在下载");

                    }

                });

                int dg = wl.xz(dl, paths, true, null, "utf-8", null, true, null);

                String bk = "";

                if (dg == 0) {

                    bk = "下载成功";

                } else if (dg == 1) {

                    bk = "已下载";

                } else if (dg == 2) {

                    bk = "下载失败";

                } else {

                    bk = "未知错误";

                }

                final String df = bk;
                gj.jmxc(new Runnable() {

                    public void run() {

                        st.kp(R.id.kp2).kjd(8);
                        st.wb(R.id.show).zf(df);

                    }

                });
                gj.zt(250);

                if (dg == 0 || dg == 1) {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            gj.gb();

                        }

                    });
                    e1.tsk("提示", "下载成功\n\"" + paths + "\"");

                } else {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            gj.gb();

                        }

                    });
                    e1.tsk("提示", "下载失败");

                }

            }

        });

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {
        return true;

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

    private void onTx1Click(View vw) {
        gj.gb();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        CardView i4746b35274 = (CardView) findViewById(ay, vw, R.id.kp2);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
