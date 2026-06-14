/**
 ******************************************************************************
 * @file    LiveStreamActivity.java
 * @author  Typheye
 * @brief   Live stream plugin screen.
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
package open.cn.awg.pro.live;


import open.cn.awg.pro.core.IappCompat;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONObject;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.music.TuneFreeMusicBridge;
import open.cn.awg.pro.R;
import open.cn.awg.pro.ui.list.LiveStreamItemActivity;

public class LiveStreamActivity extends BaseAwgActivity {

    public final LiveStreamActivity lei = this, 类 = this;
    public String um = "";
    public File f;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public TuneFreeMusicBridge e12 = new TuneFreeMusicBridge(_APPINFO);
    public i.runlibrary.app.v.v7lb$UserAdapter wlzblbspq = null;
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
    private final View.OnLongClickListener xdbj3LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj3LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj3Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj2LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj2LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj2Click(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.live_stream);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        csh();
        show(0);

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.live_stream_root, R.id.xdbj1, R.id.title_bar);

    }

    public void show(int i) {

        final i.runlibrary.app.v.wb tab = st.wb(R.id.title_bar);

        final i.runlibrary.app.v.xxbj x1 = st.xxbj(R.id.xxbj1);

        final i.runlibrary.app.v.xxbj x2 = st.xxbj(R.id.xxbj6);

        final i.runlibrary.app.v.xxbj x3 = st.xxbj(R.id.xxbj2);

        if (i == 0) {

            gj.jmxc(new Runnable() {

                public void run() {

                    tab.zf("< 网络直播");
                    x1.kjd(8);
                    x2.kjd(8);
                    x3.kjd(0);

                }

            });

        } else if (i == 1) {

            gj.jmxc(new Runnable() {

                public void run() {

                    tab.zf("< 直播通道");
                    x1.kjd(0);
                    x2.kjd(8);
                    x3.kjd(8);

                }

            });

        } else if (i == 2) {

            gj.jmxc(new Runnable() {

                public void run() {

                    tab.zf("< 网络直播");
                    x1.kjd(8);
                    x2.kjd(0);
                    x3.kjd(8);

                }

            });

        }

    }

    public void csh() {

        i.runlibrary.app.v.v7lb lb = st.v7lb(R.id.wlzbv7lb1);
        wlzblbspq = IappCompat.v7lbAdapter(lb, LiveStreamItemActivity.class, R.layout.live_stream_item);

    }

    public void jzpd() {
        gj.xc(new Thread() {

            public void run() {

                show(2);
                wlzblbspq.sc();

                String set33 = AppPaths.appPath("data/assets/a18");

                int[] kj = new int[]{R.id.wb1, R.id.wb3, R.id.wb2, -2};

                String[] jk = new String[]{"", "", "", ""};

                try {

                    String v = wj.dqwb(set33);

                    i.runlibrary.app.zf$json jo = (i.runlibrary.app.zf$json) (Object) zf.json(v);

                    JSONObject json = jo.json;

                    Object v_code = jo.hq(json, "code");

                    JSONArray list = jo.dxlb(json, "data");

                    int i = jo.cd(list);

                    int size = 0;
                    gj.sc(size);

                    while (size < i) {

                        JSONObject dx = jo.dx(list, size);
                        size++;

                        String v_url_back = "";

                        try {

                            v_url_back = String.valueOf(jo.hq(dx, "url_back"));

                        } catch (Throwable __$_e__) {

                        }

                        if (zf.dy(v_url_back, null) || zf.dy(v_url_back, "null")) {

                            v_url_back = "";

                        }

                        Object v_title = jo.hq(dx, "title");

                        Object v_url = jo.hq(dx, "url");

                        Object v_text = jo.hq(dx, "text");
                        jk = new String[]{v_title.toString(), v_text.toString(), v_url.toString(), v_url_back};
                        wlzblbspq.j(kj, jk);

                    }
                    gj.jmxc(new Runnable() {

                        public void run() {

                            wlzblbspq.sx();
                            show(1);

                        }

                    });

                } catch (Throwable e) {

                    e1.upload_error(e, "l5_a3_zhibo_d0420_y2022.jzpd()");
                    show(0);

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

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

            if (zf.dy(st.wb(R.id.title_bar).zf(), "< 网络直播")) {

                gj.gb();

            } else if (zf.dy(st.wb(R.id.title_bar).zf(), "< 直播通道")) {

                show(0);

            }

        }
        return false;

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onTitleBarClick(View vw) {

        if (zf.dy(st.wb(R.id.title_bar).zf(), "< 网络直播")) {

            gj.gb();

        } else if (zf.dy(st.wb(R.id.title_bar).zf(), "< 直播通道")) {

            show(0);

        }

    }

    private void onXdbj2Click(View vw) {
        jzpd();

    }

    private boolean onXdbj2LongClick(View vw) {

        String te = st.wb(R.id.wb3).zf();

        String lj = st.wb(R.id.wb4).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj3Click(View vw) {
        e1.tsk("功能帮助", "此服务由第三方提供,如有侵权,请联系498978473@qq.com");

    }

    private boolean onXdbj3LongClick(View vw) {

        String te = st.wb(R.id.wb5).zf();

        String lj = st.wb(R.id.wb6).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx2Click(View vw) {
        gj.gb();

    }

    private void onTx1Click(View vw) {
        show(0);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        RelativeLayout i3167b45174 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);
        i3167b45174.setOnLongClickListener(xdbj2LongClickListener);

        RelativeLayout i20c2777fab = (RelativeLayout) findViewById(ay, vw, R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);
        i20c2777fab.setOnLongClickListener(xdbj3LongClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
