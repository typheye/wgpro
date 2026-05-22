/**
 ******************************************************************************
 * @file    InstalledAppListActivity.java
 * @author  Typheye
 * @brief   Lists installed applications and exposes app actions.
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
package open.cn.awg.pro.apps;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.ui.list.InstalledAppItemActivity;

public class InstalledAppListActivity extends BaseAwgActivity {

    public final InstalledAppListActivity lei = this, 类 = this;
    public i.runlibrary.app.v.v7lb$UserAdapter spq = null;
    public String lin;
    public String CacheSize = null;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
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
    private final CompoundButton.OnCheckedChangeListener kg1CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onKg1CheckedChanged(vw, ic);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.installed_app_list);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.installed_app_list_root, R.id.xdbj1, R.id.title_bar);

    }

    public void show(int i) {

        if (i == 0) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.xxbj(R.id.xxbj1).kjd(0);
                    st.xxbj(R.id.xxbj2).kjd(8);

                }

            });

        } else if (i == 1) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.xxbj(R.id.xxbj1).kjd(8);
                    st.xxbj(R.id.xxbj2).kjd(0);

                }

            });

        }

    }

    public void jz() {

        boolean issa = false;

        i.runlibrary.app.v.v7lb lb = st.v7lb(R.id.v7lb1);
        spq = lb.v7lbspq(InstalledAppItemActivity.class, R.layout.installed_app_item);

        String set1 = AppPaths.appPath("settings/l6/set1.inf");

        i.runlibrary.app.v.kg kg1 = st.kg(R.id.kg1);

        if (zf.dy(wj.dqwb(set1), "true")) {

            kg1.xzzt(true);
            issa = true;

        }

        final int[] kj = new int[]{R.id.tx1, R.id.wb1, R.id.wb3, -1};

        final boolean iss = issa;
        gj.xc(new Thread() {

            public void run() {

                show(1);

                try {

                    i.runlibrary.app.xt$yylb[] sz = xt.yylb(iss);

                    for (i.runlibrary.app.xt$yylb app : sz) {

                        String bbh = String.valueOf(app.bbh);

                        String zbt = app.bb + "[" + bbh + "]";

                        Object[] jk = new Object[]{app.tb, app.bt, zbt, app.bm};
                        spq.j(kj, jk);

                    }
                    gj.jmxc(new Runnable() {

                        public void run() {

                            spq.sx();

                        }

                    });

                } catch (Throwable e) {

                    e1.upload_error(e, "l6.*");

                }
                show(0);

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

    private void onKg1CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/l6/set1.inf");

        if (ic) {

            wj.xrwb(set, "true");
            jz();

        } else {

            wj.xrwb(set, "false");
            jz();

        }

    }

    private void onXdbj2Click(View vw) {

        i.runlibrary.app.v.kg kg = st.kg(R.id.kg1);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean onXdbj2LongClick(View vw) {

        String te = st.wb(R.id.wb2).zf();

        String lj = st.wb(R.id.wb4).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx1Click(View vw) {
        gj.gb();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        SwitchCompat i1bca89f073 = (SwitchCompat) findViewById(ay, vw, R.id.kg1);
        i1bca89f073.setOnCheckedChangeListener(kg1CheckedChangeListener);

        RelativeLayout i3167b45174 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);
        i3167b45174.setOnLongClickListener(xdbj2LongClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
