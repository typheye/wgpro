/**
 ******************************************************************************
 * @file    DeleteConfirmActivity.java
 * @author  Typheye
 * @brief   Confirms file or directory deletion before executing it.
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
package open.cn.awg.pro.file;


import open.cn.awg.pro.core.IappCompat;
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
import open.cn.awg.pro.R;

public class DeleteConfirmActivity extends BaseAwgActivity {

    public final DeleteConfirmActivity lei = this, 类 = this;
    public String b = "";
    public String c = "";
    public String d = "";
    public String es = "";
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.delete_confirm);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        b = sj.hqtz("b");
        c = sj.hqtz("c");
        d = sj.hqtz("d");
        es = sj.hqtz("es");

        String lx = "";

        if (zf.dy(b, "2")) {

            lx = "文件";

        } else {

            lx = "目录";

        }
        st.wb(R.id.wb1).zf("您确定要删除“" + c + "”" + lx + "吗？");

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        i.runlibrary.app.xt$pm pm = (i.runlibrary.app.xt$pm) (Object) xt.pm();

        if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4s.inf")), "false")) {

            if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4.inf")), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb(AppPaths.appPath("settings/f17/set1.inf")));

        if (zf.dy(a, "4")) {

            st.xdbj(R.id.delete_confirm_root).shxtck(true);

            String clr = zf.zf(R.color.colorTab);
            setStatusBarColor(clr);

        } else {

            st.xdbj(R.id.delete_confirm_root).shxtck(false);
        applyFullscreenWindow();

        }

        if (zf.dy(a, "1")) {

            st.xdbj(R.id.xdbj1).nbj(u, 0, u, 60);

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

            st.xdbj(R.id.xdbj1).nbj(u, 0, u, 60);

            i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "4")) {

            st.xdbj(R.id.xdbj1).nbj(0, 0, 0, 0);

        }

    }

    public void tip() {

        String cache_c = AppPaths.appPath("cache/c");

        String cache_es = AppPaths.appPath("cache/es");
        wj.xrwb(cache_c, c);
        wj.xrwb(cache_es, es);

    }

    public void no() {
        gj.xc(new Thread() {

            public void run() {

                gj.jmxc(new Runnable() {

                    public void run() {

                        st.xxbj(R.id.xxbj1).kjd(8);
                        st.xxbj(R.id.xxbj3).kjd(0);

                    }

                });
                tip();
                gj.zt(250);
                gj.gb();

            }

        });

    }

    public void yes() {
        gj.xc(new Thread() {

            public void run() {

                gj.jmxc(new Runnable() {

                    public void run() {

                        st.xxbj(R.id.xxbj1).kjd(8);
                        st.xxbj(R.id.xxbj3).kjd(0);

                    }

                });
                tip();
                gj.zt(250);

                String[] aa;

                String pb1 = AppPaths.appPath("settings");

                String pb2 = AppPaths.appPath("data");

                String pb3 = AppPaths.appPath("fix");

                String pb4 = AppPaths.appPath("cache");

                String pb5 = AppPaths.appPath("files/cache");

                String pb6 = "/storage/emulated/0/Android/data";

                String pb7 = "/storage/emulated/0/Android/obb";

                if (zf.ckt(c, pb1) || zf.ckt(c, pb2) || zf.ckt(c, pb3) || zf.ckt(c, pb4) || zf.ckt(c, pb5) || (IappCompat.sdk(xt.sbxx()) > 29 && zf.ckt(c, pb6)) || (IappCompat.sdk(xt.sbxx()) > 29 && zf.ckt(c, pb7))) {

                    e1.tsk("提示", "非法操作");

                } else {

                    if (zf.dy(b, "2")) {

                        if (wj.sc(d) == 1) {

                            final String z = "删除成功";

                        } else {

                            final String z = "删除失败";
                            e1.tsk("提示", z);

                        }

                    } else {

                        String m = "rm -rf \"" + d + "\"";
                        aa = com.demo.e3.cmd(lei, m, false);

                        if (zf.dy(aa[0], "") && !zf.dy(aa[1], "")) {

                            final String z = "删除失败\n" + aa[1];
                            e1.tsk("提示", z);

                        } else if (!zf.dy(aa[0], "") && zf.dy(aa[1], "")) {

                            final String z = "删除成功";

                        } else {

                            final String z = "删除成功";

                        }

                    }

                }
                gj.gb();

            }

        });

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
        tip();

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

        }
        return false;

    }

    private void onTx1Click(View vw) {
        no();

    }

    private void onTx2Click(View vw) {
        yes();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
