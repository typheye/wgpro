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
    public AppRuntimeBridge e1;
    public TuneFreeMusicBridge e12;
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
        e1 = new AppRuntimeBridge(this);
        e12 = new TuneFreeMusicBridge(this);
        setContentView(R.layout.tune_free_cloud_search);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        e12.csh();
        ll = TuneFreeCloudSearchActivity.this.getIntent().getStringExtra(String.valueOf("name"));
        pp = Integer.parseInt(TuneFreeCloudSearchActivity.this.getIntent().getStringExtra(String.valueOf("type")));
        search(ll, pp, oo);

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.tune_free_cloud_search_root, R.id.xdbj1, R.id.title_bar);

    }

    public void show(int i) {

        final android.widget.LinearLayout x2 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeCloudSearchActivity.this.getWindow().getDecorView(), R.id.xxbj6));

        final android.widget.LinearLayout x3 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeCloudSearchActivity.this.getWindow().getDecorView(), R.id.xxbj4));

        if (i == 1) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        } else if (i == 2) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                }

            });

        }

    }

    public void search(Object l, int p, int k) {

        final String ljf = open.cn.awg.pro.core.NativeFiles.readText(TuneFreeCloudSearchActivity.this, (String) (AppPaths.appPath("data/assets/a17")));

        final Object xx = l;

        final int type = p;

        final int kk = k;
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                if (type == -1) {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeCloudSearchActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("< 云盘资源"));

                }

            }

        });
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                show(1);

                if (java.util.Objects.equals(xx, "")) {

                    e1.tsk("提示", "搜索不能为空");
                    open.cn.awg.pro.core.NativeActions.finish(TuneFreeCloudSearchActivity.this);

                } else {

                    if (type == 0) {

                        String url = open.cn.awg.pro.core.TextValues.trim(open.cn.awg.pro.core.NativeFiles.readText(TuneFreeCloudSearchActivity.this, (String) (set35))) + api_search;

                        String setback = "";

                        String f = "User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept-language=zh-CN";

                        int ot = (kk - 1) * 20;

                        String whq = "?keywords=" + xx + ljf + "type=" + api_search_type[type] + ljf + "limit=20" + ljf + "offset=" + ot;

                        String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url + whq), (String[]) (null), (String) ("utf-8"), null, false, null, 0, 0, null).bodyForHttp200();

                        if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                            setback = "0";

                        } else {

                            setback = back;

                        }

                        if (java.util.Objects.equals(setback, "0")) {

                            e1.tsk("提示", "加载失败,请重试");
                            open.cn.awg.pro.core.NativeActions.finish(TuneFreeCloudSearchActivity.this);

                        } else {

                            try {

                                e12.search_i1(setback, oo);

                            } catch (Throwable e) {

                                e1.upload_error(e, "l5_a2_nimin_d0415_y2022_a1.search(s,int,int)");
                                open.cn.awg.pro.core.NativeActions.finish(TuneFreeCloudSearchActivity.this);

                            }
                            show(2);

                        }

                    } else if (type == -1) {

                        String whq0 = "/cookie2.csm";

                        String back0 = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(open.cn.awg.pro.core.TextValues.trim(open.cn.awg.pro.core.NativeFiles.readText(TuneFreeCloudSearchActivity.this, (String) (set35))) + whq0), (String[]) (null), (String) ("utf-8"), null, false, null, 0, 0, null).bodyForHttp200();
                        back0 = open.cn.awg.pro.core.TextValues.trim(back0);

                        if (!java.util.Objects.equals(back0, "") || !java.util.Objects.equals(back0, null)) {

                            try {

                                int len = 15;

                                if (back0.length() <= len) {

                                    back0 = "";

                                } else {

                                    if (back0.length() <= len) {

                                        back0 = "";

                                    }

                                }

                            } catch (Throwable e) {

                                back0 = "";

                            }
                            back0 = open.cn.awg.pro.core.TextValues.trim(back0);

                            if (java.util.Objects.equals(back0, "") || java.util.Objects.equals(back0, null)) {

                                e1.tsk("提示", "加载失败,请重试(-1)");
                                open.cn.awg.pro.core.NativeActions.finish(TuneFreeCloudSearchActivity.this);

                            } else {

                                String sjzx = open.cn.awg.pro.core.NativeDevice.formatDate(4);

                                String url = open.cn.awg.pro.core.TextValues.trim(open.cn.awg.pro.core.NativeFiles.readText(TuneFreeCloudSearchActivity.this, (String) (set35))) + api_search2;

                                String setback = "";

                                String f = "User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept-language=zh-CN";

                                int ot = (kk - 1) * 20;

                                String whq = "?limit=20" + ljf + "offset=" + ot + ljf + "timestamp=" + sjzx + ljf + "cookie=" + back0;

                                String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url + whq), (String[]) (null), (String) ("utf-8"), null, false, null, 0, 0, null).bodyForHttp200();

                                if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                                    setback = "0";

                                } else {

                                    setback = back;

                                }

                                if (java.util.Objects.equals(setback, "0")) {

                                    e1.tsk("提示", "加载失败,请重试(-2)");
                                    open.cn.awg.pro.core.NativeActions.finish(TuneFreeCloudSearchActivity.this);

                                } else {

                                    try {

                                        e12.search_i2(setback, oo);

                                    } catch (Throwable e) {

                                        e1.upload_error(e, "l5_a2_nimin_d0415_y2022_a1.search(s,int,int)");
                                        open.cn.awg.pro.core.NativeActions.finish(TuneFreeCloudSearchActivity.this);

                                    }
                                    show(2);

                                }

                            }

                        } else {

                            e1.tsk("提示", "加载失败,请重试(-3)");
                            open.cn.awg.pro.core.NativeActions.finish(TuneFreeCloudSearchActivity.this);

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeCloudSearchActivity.this.getWindow().getDecorView(), R.id.qtgd1));

        qtgd1.setOnGenericMotionListener(new View.OnGenericMotionListener() {
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

        qtgd1.requestFocus();

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
        open.cn.awg.pro.core.NativeActions.finish(TuneFreeCloudSearchActivity.this);

    }

    private void onTx1Click(View vw) {

        try {

            String p = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeCloudSearchActivity.this.getWindow().getDecorView(), R.id.myswb)).getText().toString();

            final String max = open.cn.awg.pro.core.TextValues.slice(p, "/", "页");

            if (oo == 1) {

                e1.tsk("提示", "已经在第一页了");

            } else {

                oo = oo - 1;
                search(ll, pp, oo);

                final int oog = oo;
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeCloudSearchActivity.this.getWindow().getDecorView(), R.id.wb2)).setText(String.valueOf("第" + oog + "/" + max + "页"));

                    }

                });

            }

        } catch (Throwable e) {

            e1.upload_error(e, "l5_a2_nimin_d0415_y2022_a1.*");

        }

    }

    private void onTx3Click(View vw) {

        try {

            String p = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeCloudSearchActivity.this.getWindow().getDecorView(), R.id.myswb)).getText().toString();
            p = open.cn.awg.pro.core.TextValues.slice(p, "/", "页");

            String o = String.valueOf(oo);

            if (java.util.Objects.equals(o, p)) {

                e1.tsk("提示", "已经在最后一页了");

            } else {

                oo = oo + 1;
                search(ll, pp, oo);

                final int oog = oo;

                final String pg = p;
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeCloudSearchActivity.this.getWindow().getDecorView(), R.id.wb2)).setText(String.valueOf("第" + oog + "/" + pg + "页"));

                    }

                });

            }

        } catch (Throwable e) {

            e1.upload_error(e, "l5_a2_nimin_d0415_y2022_a1.*");

        }

    }

    private void onTx2Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(TuneFreeCloudSearchActivity.this);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
