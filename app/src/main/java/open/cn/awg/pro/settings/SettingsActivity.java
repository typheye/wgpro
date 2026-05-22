/**
 ******************************************************************************
 * @file    SettingsActivity.java
 * @author  Typheye
 * @brief   Hosts the main application settings screen.
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
package open.cn.awg.pro.settings;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
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

import open.cn.awg.pro.account.TypheyeAccountActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.browser.X5BrowserSettingsActivity;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.core.CacheDataManager;
import open.cn.awg.pro.R;
import open.cn.awg.pro.tools.CloudServiceActivity;
import open.cn.awg.pro.ui.main.AboutAppActivity;

public class SettingsActivity extends BaseAwgActivity {

    public final SettingsActivity lei = this, 类 = this;
    public String CacheSize = "";
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj13LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj13LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj13ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj13Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj4LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj4LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj4Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj19LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj19LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj19ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj19Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj18LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj18LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj18ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj18Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj15LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj15LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj15ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj15Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj8LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj8LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj8ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj8Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj6LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj6LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj6ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj6Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj9LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj9LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj9ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj9Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj11LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj11LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj11ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj11Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj14LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj14LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj14ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj14Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj7LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj7LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj7ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj7Click(vw);
        }

    };
    private final CompoundButton.OnCheckedChangeListener kg3CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onKg3CheckedChanged(vw, ic);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.settings);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.settings_root, R.id.xdbj1, R.id.title_bar);

    }

    public void jz() {

        String set3 = AppPaths.appPath("settings/set3.inf");

        i.runlibrary.app.v.kg kg3 = st.kg(R.id.kg3);

        if (zf.dy(wj.dqwb(set3), "true")) {

            kg3.xzzt(true);

        }

        try {

            CacheSize = CacheDataManager.getTotalCacheSize(this);

            if (zf.dy(CacheSize, "0.00M")) {

                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.wb28).zf("应用暂无产生缓存");

                    }

                });

            } else {

                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.wb28).zf("将清除" + CacheSize + "的缓存");

                    }

                });

            }

        } catch (Throwable __$_e__) {

        }

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

    private void onKg3CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/set3.inf");

        if (ic) {

            wj.xrwb(set, "true");

        } else {

            wj.xrwb(set, "false");

        }

    }

    private void onXdbj7Click(View vw) {

        i.runlibrary.app.v.kg kg = st.kg(R.id.kg3);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean onXdbj7LongClick(View vw) {

        String te = st.wb(R.id.wb14).zf();

        String lj = st.wb(R.id.wb15).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj14Click(View vw) {
        gj.tz(TypheyeAccountActivity.class);

    }

    private boolean onXdbj14LongClick(View vw) {

        String te = st.wb(R.id.wb35).zf();

        String lj = st.wb(R.id.wb36).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj11Click(View vw) {
        gj.tz(CloudServiceActivity.class);

    }

    private boolean onXdbj11LongClick(View vw) {

        String te = st.wb(R.id.wb22).zf();

        String lj = st.wb(R.id.wb23).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj9Click(View vw) {
        gj.tz(ThemeSettingsActivity.class);

    }

    private boolean onXdbj9LongClick(View vw) {

        String te = st.wb(R.id.wb18).zf();

        String lj = st.wb(R.id.wb19).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj6Click(View vw) {
        gj.tz(TextViewerSettingsActivity.class);

    }

    private boolean onXdbj6LongClick(View vw) {

        String te = st.wb(R.id.wb12).zf();

        String lj = st.wb(R.id.wb13).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj8Click(View vw) {
        gj.tz(FileManagerSettingsActivity.class);

    }

    private boolean onXdbj8LongClick(View vw) {

        String te = st.wb(R.id.wb16).zf();

        String lj = st.wb(R.id.wb17).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj15Click(View vw) {
        gj.tz(X5BrowserSettingsActivity.class);

    }

    private boolean onXdbj15LongClick(View vw) {

        String te = st.wb(R.id.wb31).zf();

        String lj = st.wb(R.id.wb32).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj18Click(View vw) {
        gj.tz(MediaBrowserSettingsActivity.class);

    }

    private boolean onXdbj18LongClick(View vw) {

        String te = st.wb(R.id.wb37).zf();

        String lj = st.wb(R.id.wb38).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj19Click(View vw) {
        gj.tz(AboutAppActivity.class);

    }

    private boolean onXdbj19LongClick(View vw) {

        String te = st.wb(R.id.wb39).zf();

        String lj = st.wb(R.id.wb40).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj4Click(View vw) {
        gj.tz(LegalInfoActivity.class);

    }

    private boolean onXdbj4LongClick(View vw) {

        String te = st.wb(R.id.wb7).zf();

        String lj = st.wb(R.id.wb8).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj13Click(View vw) {

        if (zf.dy(st.wb(R.id.wb28).zf(), "应用暂无产生缓存")) {

            e1.tsk("提示", "应用暂无产生缓存");

        } else {

            CacheDataManager.clearAllCache(this);
            e1.tsk("提示", "清除成功");
            jz();

        }

    }

    private boolean onXdbj13LongClick(View vw) {

        String te = st.wb(R.id.wb27).zf();

        String lj = st.wb(R.id.wb28).zf();
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

        SwitchCompat i2e1dda294f = (SwitchCompat) findViewById(ay, vw, R.id.kg3);
        i2e1dda294f.setOnCheckedChangeListener(kg3CheckedChangeListener);

        RelativeLayout e585d94488 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj7);
        e585d94488.setOnClickListener(xdbj7ClickListener);
        e585d94488.setOnLongClickListener(xdbj7LongClickListener);

        RelativeLayout c4cbe9d7ff = (RelativeLayout) findViewById(ay, vw, R.id.xdbj14);
        c4cbe9d7ff.setOnClickListener(xdbj14ClickListener);
        c4cbe9d7ff.setOnLongClickListener(xdbj14LongClickListener);

        RelativeLayout i67b461495f = (RelativeLayout) findViewById(ay, vw, R.id.xdbj11);
        i67b461495f.setOnClickListener(xdbj11ClickListener);
        i67b461495f.setOnLongClickListener(xdbj11LongClickListener);

        RelativeLayout i5a93676674 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj9);
        i5a93676674.setOnClickListener(xdbj9ClickListener);
        i5a93676674.setOnLongClickListener(xdbj9LongClickListener);

        RelativeLayout i171cedf1eb = (RelativeLayout) findViewById(ay, vw, R.id.xdbj6);
        i171cedf1eb.setOnClickListener(xdbj6ClickListener);
        i171cedf1eb.setOnLongClickListener(xdbj6LongClickListener);

        RelativeLayout fab146d7a5 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj8);
        fab146d7a5.setOnClickListener(xdbj8ClickListener);
        fab146d7a5.setOnLongClickListener(xdbj8LongClickListener);

        RelativeLayout i6834408ef9 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj15);
        i6834408ef9.setOnClickListener(xdbj15ClickListener);
        i6834408ef9.setOnLongClickListener(xdbj15LongClickListener);

        RelativeLayout i11185eacd9 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj18);
        i11185eacd9.setOnClickListener(xdbj18ClickListener);
        i11185eacd9.setOnLongClickListener(xdbj18LongClickListener);

        RelativeLayout a229195608 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj19);
        a229195608.setOnClickListener(xdbj19ClickListener);
        a229195608.setOnLongClickListener(xdbj19LongClickListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) findViewById(ay, vw, R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);
        i2202ede8ff.setOnLongClickListener(xdbj4LongClickListener);

        RelativeLayout i62f27ff563 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj13);
        i62f27ff563.setOnClickListener(xdbj13ClickListener);
        i62f27ff563.setOnLongClickListener(xdbj13LongClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        NestedScrollView a6dab51133 = (NestedScrollView) findViewById(ay, vw, R.id.qtgd1);

        __layoutIsLoaded(ay, vw);
    }
}
