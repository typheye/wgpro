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

import open.cn.awg.pro.core.NativeDevice.InstalledApp;


import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.widget.SwitchCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.ui.list.NativeListAdapter;
import open.cn.awg.pro.ui.list.InstalledAppRowBinder;
import open.cn.awg.pro.R;

public class InstalledAppListActivity extends BaseAwgActivity {

    public final InstalledAppListActivity lei = this, 类 = this;
    public NativeListAdapter spq = null;
    public String lin;
    public String CacheSize = null;
    public AppRuntimeBridge e1;
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
        e1 = new AppRuntimeBridge(this);
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

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(InstalledAppListActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(InstalledAppListActivity.this.getWindow().getDecorView(), R.id.xxbj2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        } else if (i == 1) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(InstalledAppListActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(InstalledAppListActivity.this.getWindow().getDecorView(), R.id.xxbj2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                }

            });

        }

    }

    public void jz() {

        boolean issa = false;

        RecyclerView list = findViewById(R.id.v7lb1);
        spq = NativeListAdapter.attach(this, list, R.layout.installed_app_item, new InstalledAppRowBinder(this));

        String set1 = AppPaths.appPath("settings/l6/set1.inf");

        android.widget.CompoundButton kg1 = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(InstalledAppListActivity.this.getWindow().getDecorView(), R.id.kg1));

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(InstalledAppListActivity.this, (String) (set1)), "true")) {

            kg1.setChecked(java.lang.Boolean.TRUE.equals(true));
            issa = true;

        }

        final int[] kj = new int[]{R.id.tx1, R.id.wb1, R.id.wb3, -1};

        final boolean iss = issa;
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                show(1);

                try {

                    InstalledApp[] sz = new open.cn.awg.pro.core.NativeDevice(InstalledAppListActivity.this).installedApps(iss);

                    for (InstalledApp app : sz) {

                        String bbh = String.valueOf(app.versionCode);

                        String zbt = app.versionName + "[" + bbh + "]";

                        Object[] jk = new Object[]{app.icon, app.label, zbt, app.packageName};
                        spq.addRow(kj, jk);

                    }
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            spq.refreshRows();

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(InstalledAppListActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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
        open.cn.awg.pro.core.NativeActions.finish(InstalledAppListActivity.this);

    }

    private void onKg1CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/l6/set1.inf");

        if (ic) {

            open.cn.awg.pro.core.NativeFiles.writeText(InstalledAppListActivity.this, (String) (set), (String) ("true"));
            jz();

        } else {

            open.cn.awg.pro.core.NativeFiles.writeText(InstalledAppListActivity.this, (String) (set), (String) ("false"));
            jz();

        }

    }

    private void onXdbj2Click(View vw) {

        android.widget.CompoundButton kg = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(InstalledAppListActivity.this.getWindow().getDecorView(), R.id.kg1));

        if (!kg.isChecked()) {

            kg.setChecked(java.lang.Boolean.TRUE.equals(true));

        } else {

            kg.setChecked(java.lang.Boolean.TRUE.equals(false));

        }

    }

    private boolean onXdbj2LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(InstalledAppListActivity.this.getWindow().getDecorView(), R.id.wb2)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(InstalledAppListActivity.this.getWindow().getDecorView(), R.id.wb4)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(InstalledAppListActivity.this);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        SwitchCompat i1bca89f073 = (SwitchCompat) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.kg1);
        i1bca89f073.setOnCheckedChangeListener(kg1CheckedChangeListener);

        RelativeLayout i3167b45174 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);
        i3167b45174.setOnLongClickListener(xdbj2LongClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
