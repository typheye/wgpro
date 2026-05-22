/**
 ******************************************************************************
 * @file    TextViewerSettingsActivity.java
 * @author  Typheye
 * @brief   Configures text viewer and editor display options.
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
import android.support.v7.widget.CardView;
import android.support.v7.widget.SwitchCompat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
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

public class TextViewerSettingsActivity extends BaseAwgActivity {

    public final TextViewerSettingsActivity lei = this, 类 = this;
    public String lin = "";
    public String newcolor = "";
    public int bs = 0;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    private final View.OnClickListener tx3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx3Click(vw);
        }

    };
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };
    private final SeekBar.OnSeekBarChangeListener tdt4SeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        public void onProgressChanged(SeekBar vw, int ps, boolean fu) {
            onTdt4ProgressChanged(vw, ps, fu);
        }

        public void onStartTrackingTouch(SeekBar vw) {
            onTdt4StartTrackingTouch(vw);
        }

        public void onStopTrackingTouch(SeekBar vw) {
            onTdt4StopTrackingTouch(vw);
        }

    };
    private final SeekBar.OnSeekBarChangeListener tdt3SeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        public void onProgressChanged(SeekBar vw, int ps, boolean fu) {
            onTdt3ProgressChanged(vw, ps, fu);
        }

        public void onStartTrackingTouch(SeekBar vw) {
            onTdt3StartTrackingTouch(vw);
        }

        public void onStopTrackingTouch(SeekBar vw) {
            onTdt3StopTrackingTouch(vw);
        }

    };
    private final SeekBar.OnSeekBarChangeListener tdt2SeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        public void onProgressChanged(SeekBar vw, int ps, boolean fu) {
            onTdt2ProgressChanged(vw, ps, fu);
        }

        public void onStartTrackingTouch(SeekBar vw) {
            onTdt2StartTrackingTouch(vw);
        }

        public void onStopTrackingTouch(SeekBar vw) {
            onTdt2StopTrackingTouch(vw);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj53LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj53LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj53ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj53Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj52LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj52LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj52ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj52Click(vw);
        }

    };
    private final SeekBar.OnSeekBarChangeListener tdt1SeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        public void onProgressChanged(SeekBar vw, int ps, boolean fu) {
            onTdt1ProgressChanged(vw, ps, fu);
        }

        public void onStartTrackingTouch(SeekBar vw) {
            onTdt1StartTrackingTouch(vw);
        }

        public void onStopTrackingTouch(SeekBar vw) {
            onTdt1StopTrackingTouch(vw);
        }

    };
    private final View.OnLongClickListener xdbj51LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj51LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj51ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj51Click(vw);
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
    private final CompoundButton.OnCheckedChangeListener kg2CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onKg2CheckedChanged(vw, ic);
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
        setContentView(R.layout.text_viewer_settings);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

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

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb(AppPaths.appPath("settings/f17/set1.inf")));

        if (zf.dy(a, "4")) {

            st.xdbj(R.id.text_viewer_settings_root).shxtck(true);

            String clr = zf.zf(R.color.colorTab);
            setStatusBarColor(clr);

        } else {

            st.xdbj(R.id.text_viewer_settings_root).shxtck(false);
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

    public void jz() {

        String set1 = AppPaths.appPath("settings/d2/set1.inf");

        String set2 = AppPaths.appPath("settings/d2/set2.inf");

        String set3 = AppPaths.appPath("settings/d2/set3.inf");

        String set4 = AppPaths.appPath("settings/d2/set4.inf");

        String set5 = AppPaths.appPath("settings/d2/set5.inf");

        i.runlibrary.app.v.kg kg1 = st.kg(R.id.kg1);

        i.runlibrary.app.v.kg kg2 = st.kg(R.id.kg2);

        i.runlibrary.app.v.kp kp2 = st.kp(R.id.kp2);

        i.runlibrary.app.v.kp kp3 = st.kp(R.id.kp3);

        if (zf.dy(wj.dqwb(set1), "true")) {

            kg1.xzzt(true);

        }

        if (zf.dy(wj.dqwb(set5), "true")) {

            kg2.xzzt(true);

        }

        int i = Integer.parseInt(wj.dqwb(set2));

        i.runlibrary.app.v.tdt tdt1 = st.tdt(R.id.tdt1);
        tdt1.jdz(i);
        st.wb(R.id.wb10).zf(i + "sp");

        String ztys = wj.dqwb(set3);
        kp2.kpbjys(ztys);

        String bjys = wj.dqwb(set4);
        kp3.kpbjys(bjys);
        st.wb(R.id.wb5).zf(ztys);
        st.wb(R.id.wb7).zf(bjys);

        String code = sj.hqtz("code");

        if (zf.dy(code, "super1")) {

            st.xdbj(R.id.xdbj6).kjd(8);

        }

    }

    public void rgbsx() {

        int r = st.tdt(R.id.tdt2).jdz();

        int g = st.tdt(R.id.tdt3).jdz();

        int b = st.tdt(R.id.tdt4).jdz();

        String r1 = "";

        String g1 = "";

        String b1 = "";
        r1 = Integer.toHexString(r);

        if (r1.length() == 1) {

            r1 = "0" + r1;

        }
        g1 = Integer.toHexString(g);

        if (g1.length() == 1) {

            g1 = "0" + g1;

        }
        b1 = Integer.toHexString(b);

        if (b1.length() == 1) {

            b1 = "0" + b1;

        }

        String color = "#" + r1 + g1 + b1;
        newcolor = color;

        i.runlibrary.app.v.kp yst = st.kp(R.id.kp4);
        yst.kpbjys(color);

    }

    public void rgbcsh(int o) {

        final int tsq = o;

        int r1 = 0;

        int g1 = 0;

        int b1 = 0;

        String set1 = AppPaths.appPath("settings/d2/set3.inf");

        String set2 = AppPaths.appPath("settings/d2/set4.inf");

        final String color1 = wj.dqwb(set1);

        final String color2 = wj.dqwb(set2);

        String color = "";

        if (tsq == 1) {

            bs = 1;
            color = color1;

        } else if (tsq == 2) {

            bs = 2;
            color = color2;

        }

        String r = zf.qc(color, 1, 3);

        String g = zf.qc(color, 3, 5);

        String b = zf.qc(color, 5, null);
        r1 = Integer.parseInt(r, 16);
        g1 = Integer.parseInt(g, 16);
        b1 = Integer.parseInt(b, 16);

        final int r2 = r1;

        final int g2 = g1;

        final int b2 = b1;
        st.kp(R.id.kp4).kpbjys(color);
        st.tdt(R.id.tdt2).jdz(r2);
        st.tdt(R.id.tdt3).jdz(g2);
        st.tdt(R.id.tdt4).jdz(b2);
        st.xxbj(R.id.xxbj1).kjd(8);
        st.xxbj(R.id.xxbj6).kjd(0);
        st.wb(R.id.title_bar).zf("编辑颜色");

    }

    public void rgbbc() {

        String set1 = AppPaths.appPath("settings/d2/set3.inf");

        String set2 = AppPaths.appPath("settings/d2/set4.inf");

        if (bs == 1) {

            wj.xrwb(set1, newcolor);

        } else if (bs == 2) {

            wj.xrwb(set2, newcolor);

        }
        jz();
        st.xxbj(R.id.xxbj1).kjd(0);
        st.xxbj(R.id.xxbj6).kjd(8);
        st.wb(R.id.title_bar).zf("< 文本浏览");

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

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

            if (st.xxbj(R.id.xxbj6).kjd() == 0) {

            } else {

                gj.gb();

            }

        }
        return false;

    }

    private void onTitleBarClick(View vw) {

        if (zf.dy(st.wb(R.id.title_bar).zf(), "< 文本浏览")) {

            gj.gb();

        }

    }

    private void onKg1CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/d2/set1.inf");

        if (ic) {

            wj.xrwb(set, "true");

        } else {

            wj.xrwb(set, "false");

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

        String lj = st.wb(R.id.wb3).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onKg2CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/d2/set5.inf");

        if (ic) {

            wj.xrwb(set, "true");

        } else {

            wj.xrwb(set, "false");

        }

    }

    private void onXdbj6Click(View vw) {

        i.runlibrary.app.v.kg kg = st.kg(R.id.kg2);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean onXdbj6LongClick(View vw) {

        String te = st.wb(R.id.wb12).zf();

        String lj = st.wb(R.id.wb13).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj51Click(View vw) {

        int i;

    }

    private boolean onXdbj51LongClick(View vw) {

        String te = st.wb(R.id.wb9).zf();

        String lj = st.wb(R.id.wb10).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTdt1ProgressChanged(SeekBar vw, int ps, boolean fu) {

        String set = AppPaths.appPath("settings/d2/set2.inf");

        i.runlibrary.app.v.tdt tdt1 = st.tdt(R.id.tdt1);

        int i = tdt1.jdz();
        st.wb(R.id.wb10).zf(i + "sp");

        String sis = sj.zh(i).zstring();
        wj.xrwb(set, sis);

    }

    private void onTdt1StartTrackingTouch(SeekBar vw) {

    }

    private void onTdt1StopTrackingTouch(SeekBar vw) {

    }

    private void onXdbj52Click(View vw) {
        rgbcsh(1);

    }

    private boolean onXdbj52LongClick(View vw) {

        String te = st.wb(R.id.wb4).zf();

        String lj = st.wb(R.id.wb5).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj53Click(View vw) {
        rgbcsh(2);

    }

    private boolean onXdbj53LongClick(View vw) {

        String te = st.wb(R.id.wb6).zf();

        String lj = st.wb(R.id.wb7).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx1Click(View vw) {
        gj.gb();

    }

    private void onTdt2ProgressChanged(SeekBar vw, int ps, boolean fu) {
        rgbsx();

    }

    private void onTdt2StartTrackingTouch(SeekBar vw) {

    }

    private void onTdt2StopTrackingTouch(SeekBar vw) {

    }

    private void onTdt3ProgressChanged(SeekBar vw, int ps, boolean fu) {
        rgbsx();

    }

    private void onTdt3StartTrackingTouch(SeekBar vw) {

    }

    private void onTdt3StopTrackingTouch(SeekBar vw) {

    }

    private void onTdt4ProgressChanged(SeekBar vw, int ps, boolean fu) {
        rgbsx();

    }

    private void onTdt4StartTrackingTouch(SeekBar vw) {

    }

    private void onTdt4StopTrackingTouch(SeekBar vw) {

    }

    private void onTx2Click(View vw) {
        st.xxbj(R.id.xxbj1).kjd(0);
        st.xxbj(R.id.xxbj6).kjd(8);
        st.wb(R.id.title_bar).zf("< 文本浏览");

    }

    private void onTx3Click(View vw) {
        rgbbc();

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

        SwitchCompat ab7dd47b08 = (SwitchCompat) findViewById(ay, vw, R.id.kg2);
        ab7dd47b08.setOnCheckedChangeListener(kg2CheckedChangeListener);

        RelativeLayout i171cedf1eb = (RelativeLayout) findViewById(ay, vw, R.id.xdbj6);
        i171cedf1eb.setOnClickListener(xdbj6ClickListener);
        i171cedf1eb.setOnLongClickListener(xdbj6LongClickListener);

        RelativeLayout i248435a9e2 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj51);
        i248435a9e2.setOnClickListener(xdbj51ClickListener);
        i248435a9e2.setOnLongClickListener(xdbj51LongClickListener);

        SeekBar d11169f9c0 = (SeekBar) findViewById(ay, vw, R.id.tdt1);
        d11169f9c0.setOnSeekBarChangeListener(tdt1SeekBarChangeListener);

        RelativeLayout c04397461c = (RelativeLayout) findViewById(ay, vw, R.id.xdbj52);
        c04397461c.setOnClickListener(xdbj52ClickListener);
        c04397461c.setOnLongClickListener(xdbj52LongClickListener);

        CardView i4746b35274 = (CardView) findViewById(ay, vw, R.id.kp2);

        RelativeLayout i172cf45127 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj53);
        i172cf45127.setOnClickListener(xdbj53ClickListener);
        i172cf45127.setOnLongClickListener(xdbj53LongClickListener);

        CardView d70d738137 = (CardView) findViewById(ay, vw, R.id.kp3);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        SeekBar i8c36c72cce = (SeekBar) findViewById(ay, vw, R.id.tdt2);
        i8c36c72cce.setOnSeekBarChangeListener(tdt2SeekBarChangeListener);

        SeekBar i7843ee147b = (SeekBar) findViewById(ay, vw, R.id.tdt3);
        i7843ee147b.setOnSeekBarChangeListener(tdt3SeekBarChangeListener);

        SeekBar b753c6bb90 = (SeekBar) findViewById(ay, vw, R.id.tdt4);
        b753c6bb90.setOnSeekBarChangeListener(tdt4SeekBarChangeListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
