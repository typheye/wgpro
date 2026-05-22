/**
 ******************************************************************************
 * @file    TuneFreeCloudSearchActivity.java
 * @author  Typheye
 * @brief   TuneFree cloud resource search screen.
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

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import java.io.File;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class TuneFreeCloudSearchActivity extends BaseAwgActivity {

    public final TuneFreeCloudSearchActivity lei = this, 类 = this;
    public String um = "";
    public File f;
    public String ll = "";
    public int pp = 0;
    public int oo = 1;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public TuneFreeMusicBridge e12 = new TuneFreeMusicBridge(_APPINFO);
    public String endurl = "";
    public String set35 = AppPaths.appPath("settings/other/0xO7YX2/url");
    public String api_search = "/cloudsearch";
    public String api_search2 = "/user/cloud";
    public int[] api_search_type = new int[]{1, 10, 100, 1000, 1002, 1004, 1006, 1009, 1014, 1018, 2000};
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };
    private final View.OnClickListener tx3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx3Click(vw);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.tune_free_cloud_search);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        e12.csh();
        ll = sj.hqtz("name");
        pp = Integer.parseInt(sj.hqtz("type"));
        search(ll, pp, oo);

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.tune_free_cloud_search_root, R.id.xdbj1, R.id.title_bar);

    }

    public void show(int i) {

        final i.runlibrary.app.v.xxbj x2 = st.xxbj(R.id.xxbj6);

        final i.runlibrary.app.v.xxbj x3 = st.xxbj(R.id.xxbj4);

        if (i == 1) {

            gj.jmxc(new Runnable() {

                public void run() {

                    x2.kjd(0);
                    x3.kjd(8);

                }

            });

        } else if (i == 2) {

            gj.jmxc(new Runnable() {

                public void run() {

                    x2.kjd(8);
                    x3.kjd(0);

                }

            });

        }

    }

    public void search(Object l, int p, int k) {

        final String ljf = wj.dqwb(AppPaths.appPath("data/assets/a17"));

        final Object xx = l;

        final int type = p;

        final int kk = k;
        gj.jmxc(new Runnable() {

            public void run() {

                if (type == -1) {

                    st.wb(R.id.title_bar).zf("< 云盘资源");

                }

            }

        });
        gj.xc(new Thread() {

            public void run() {

                show(1);

                if (zf.dy(xx, "")) {

                    e1.tsk("提示", "搜索不能为空");
                    gj.gb();

                } else {

                    if (type == 0) {

                        String url = zf.qctwkg(wj.dqwb(set35)) + api_search;

                        String setback = "";

                        String f = "User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept-language=zh-CN";

                        int ot = (kk - 1) * 20;

                        String whq = "?keywords=" + xx + ljf + "type=" + api_search_type[type] + ljf + "limit=20" + ljf + "offset=" + ot;

                        String back = wl.hq(url + whq, null, "utf-8");

                        if (zf.dy(back, "") || zf.dy(back, null)) {

                            setback = "0";

                        } else {

                            setback = back;

                        }

                        if (zf.dy(setback, "0")) {

                            e1.tsk("提示", "加载失败,请重试");
                            gj.gb();

                        } else {

                            try {

                                e12.search_i1(setback, oo);

                            } catch (Throwable e) {

                                e1.upload_error(e, "l5_a2_nimin_d0415_y2022_a1.search(s,int,int)");
                                gj.gb();

                            }
                            show(2);

                        }

                    } else if (type == -1) {

                        String whq0 = "/cookie2.csm";

                        String back0 = wl.hq(zf.qctwkg(wj.dqwb(set35)) + whq0, null, "utf-8");
                        back0 = zf.qctwkg(back0);

                        if (!zf.dy(back0, "") || !zf.dy(back0, null)) {

                            try {

                                int len = 15;

                                if (back0.length() <= len) {

                                    back0 = "";

                                } else {

                                    back0 = back0;

                                    if (back0.length() <= len) {

                                        back0 = "";

                                    }

                                }

                            } catch (Throwable e) {

                                back0 = "";

                            }
                            back0 = zf.qctwkg(back0);

                            if (zf.dy(back0, "") || zf.dy(back0, null)) {

                                e1.tsk("提示", "加载失败,请重试(-1)");
                                gj.gb();

                            } else {

                                String sjzx = xt.sj(4);

                                String url = zf.qctwkg(wj.dqwb(set35)) + api_search2;

                                String setback = "";

                                String f = "User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept-language=zh-CN";

                                int ot = (kk - 1) * 20;

                                String whq = "?limit=20" + ljf + "offset=" + ot + ljf + "timestamp=" + sjzx + ljf + "cookie=" + back0;

                                String back = wl.hq(url + whq, null, "utf-8");

                                if (zf.dy(back, "") || zf.dy(back, null)) {

                                    setback = "0";

                                } else {

                                    setback = back;

                                }

                                if (zf.dy(setback, "0")) {

                                    e1.tsk("提示", "加载失败,请重试(-2)");
                                    gj.gb();

                                } else {

                                    try {

                                        e12.search_i2(setback, oo);

                                    } catch (Throwable e) {

                                        e1.upload_error(e, "l5_a2_nimin_d0415_y2022_a1.search(s,int,int)");
                                        gj.gb();

                                    }
                                    show(2);

                                }

                            }

                        } else {

                            e1.tsk("提示", "加载失败,请重试(-3)");
                            gj.gb();

                        }

                    }

                }

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

    }

    private void onTitleBarClick(View vw) {
        gj.gb();

    }

    private void onTx1Click(View vw) {

        try {

            String p = st.wb(R.id.myswb).zf();

            final String max = zf.qc(p, "/", "页");

            if (oo == 1) {

                e1.tsk("提示", "已经在第一页了");

            } else {

                oo = oo - 1;
                search(ll, pp, oo);

                final int oog = oo;
                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.wb2).zf("第" + oog + "/" + max + "页");

                    }

                });

            }

        } catch (Throwable e) {

            e1.upload_error(e, "l5_a2_nimin_d0415_y2022_a1.*");

        }

    }

    private void onTx3Click(View vw) {

        try {

            String p = st.wb(R.id.myswb).zf();
            p = zf.qc(p, "/", "页");

            String o = String.valueOf(oo);

            if (zf.dy(o, p)) {

                e1.tsk("提示", "已经在最后一页了");

            } else {

                oo = oo + 1;
                search(ll, pp, oo);

                final int oog = oo;

                final String pg = p;
                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.wb2).zf("第" + oog + "/" + pg + "页");

                    }

                });

            }

        } catch (Throwable e) {

            e1.upload_error(e, "l5_a2_nimin_d0415_y2022_a1.*");

        }

    }

    private void onTx2Click(View vw) {
        gj.gb();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
