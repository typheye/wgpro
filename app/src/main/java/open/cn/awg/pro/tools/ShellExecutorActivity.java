/**
 ******************************************************************************
 * @file    ShellExecutorActivity.java
 * @author  Typheye
 * @brief   Runs shell commands from the app diagnostics surface.
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
package open.cn.awg.pro.tools;

import android.view.View;
import android.view.Window;

import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.repair.ErrorMessageActivity;


public class ShellExecutorActivity extends iActivity {

    public final ShellExecutorActivity lei = this, 类 = this;
    public open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public java.lang.String type = "";
    public java.lang.String auto = "";
    public java.lang.String fixcode = "";
    private android.widget.TextView textView;
    private android.widget.EditText editText;
    private i.runlibrary.app.v.dxx suDxx;
    private i.runlibrary.app.v.dxx shDxx;
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i0b66a667b1 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i0b66a667b1(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_ff56a8e7f6 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_ff56a8e7f6(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i171ea34f1a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i171ea34f1a(vw);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i32ee139ba1 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i32ee139ba1(vw, me);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d26d5d7080(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.j4);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.app.AwgProApplication.getInstance().addActivity(lei);
        textView = st.wb(open.cn.awg.pro.R.id.wb1).st;
        editText = st.bjk(open.cn.awg.pro.R.id.bjk1).st;
        suDxx = st.dxx(open.cn.awg.pro.R.id.dxx1);
        shDxx = st.dxx(open.cn.awg.pro.R.id.dxx2);
        shDxx.xzzt(true);

        java.lang.String shs = sj.hqtz("sh");
        shs = zf.qctwkg(shs);
        type = sj.hqtz("type");
        type = zf.qctwkg(type);

        if (!zf.dy(shs, null) && !zf.dy(shs, "")) {

            st.bjk(open.cn.awg.pro.R.id.bjk1).zf(shs);
            st.wb(open.cn.awg.pro.R.id.wb2).kjd(0);
            st.bjk(open.cn.awg.pro.R.id.bjk1).kjd(8);

        } else {

            st.wb(open.cn.awg.pro.R.id.wb2).kjd(8);
            st.bjk(open.cn.awg.pro.R.id.bjk1).kjd(0);

        }
        auto = sj.hqtz("auto");
        auto = zf.qctwkg(auto);
        fixcode = sj.hqtz("fixcode");
        fixcode = zf.qctwkg(fixcode);

        if (zf.dy(auto, "enable")) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    i.runlibrary.app.v.an an1 = st.an(open.cn.awg.pro.R.id.an1);
                    an1.v.performClick();

                }

            });

        }

        java.lang.String szk = sj.hqtz("szk");

        i.runlibrary.app.v.dx dx1 = st.dx(open.cn.awg.pro.R.id.dx1);

        if (zf.dy(szk, "true")) {

            dx1.xzzt(true);

        }

        i.app.shizuku.setCallback(new i.app.shizuku.Callback() {
            public void ok(int o) {
            }
        });

        int c = i.app.shizuku.init(lei);

    }

    public void __layoutIsLoaded(android.app.Activity ay, android.view.View vw) {

        if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f8/set4.inf"), "true")) {

        }

        i.runlibrary.app.xt$pm pm = xt.pm();

        if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4s.inf"), "false")) {

            if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4.inf"), "true")) {

                getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));

        if (zf.dy(a, "4")) {

            st.xdbj(open.cn.awg.pro.R.id.j4_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.j4_v).shxtck(false);

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

        if (zf.dy(a, "1")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "2")) {

            java.lang.String a001 = "/data/user/0/open.cn.awg.pro/settings/dpi.inf";

            if (wj.cz(a001) || zf.dy(wj.dqwb(a001), "true")) {

                i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
                wtab.nbj(0, "3dp", 0, "3dp");
                wtab.dqfs("center");

            } else {

                i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
                wtab.nbj(0, "9dp", 0, "9dp");
                wtab.dqfs("center");

            }
            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0, 0, 0, 0);

        } else if (zf.dy(a, "3")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "4")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0, 0, 0, 0);

        }

    }

    public void runtime(java.lang.String cmd) {

        i.runlibrary.app.v.dx dx1 = st.dx(open.cn.awg.pro.R.id.dx1);

        if (dx1.xzzt()) {

            try {

                java.lang.String[] lines = zf.fg(cmd, "\n", true);

                for (java.lang.String line : lines) {

                    line = zf.qctwkg(line);

                    java.lang.String t = zf.qc(line, 0, 1);

                    java.lang.String[] bltop = new java.lang.String[]{"#"};

                    java.lang.String[] blin = new java.lang.String[]{"/data/user/0/open.cn.awg.pro"};

                    boolean istoppass = true;

                    boolean isinpass = true;

                    for (java.lang.String bl : bltop) {

                        if (zf.ckt(line, bl)) {

                            istoppass = false;

                        }

                    }

                    for (java.lang.String bl : blin) {

                        if (zf.cz(line, bl)) {

                            isinpass = false;

                        }

                    }

                    if (istoppass && isinpass) {

                        i.app.shizuku.Shell(line);

                    }

                }

            } catch (java.lang.Throwable e) {

                locat(String.valueOf(e));

            }

        } else {

            try {

                java.lang.Process process = java.lang.Runtime.getRuntime().exec(getCode());

                java.io.OutputStream outPut = process.getOutputStream();

                java.io.InputStream inPut = process.getInputStream();

                java.io.InputStream eIn = process.getErrorStream();

                java.io.InputStreamReader isr = new java.io.InputStreamReader(inPut);

                java.io.OutputStreamWriter osw = new java.io.OutputStreamWriter(outPut);

                java.io.InputStreamReader eisr = new java.io.InputStreamReader(eIn);

                java.io.BufferedReader br = new java.io.BufferedReader(isr);

                java.io.BufferedReader ebr = new java.io.BufferedReader(eisr);

                java.io.BufferedWriter bw = new java.io.BufferedWriter(osw);
                bw.write(cmd + "\n");
                bw.flush();

                java.lang.String result = null;

                while ((result = br.readLine()) != null) {
                    locat(result);
                }

                java.lang.String errorResult = null;

                while ((errorResult = ebr.readLine()) != null) {
                    locat(errorResult);
                }

                br.close();
                ebr.close();
                bw.close();
                isr.close();
                eisr.close();
                osw.close();
                inPut.close();
                outPut.close();
                eIn.close();

            } catch (java.lang.Throwable e) {

                locat(String.valueOf(e));

            }

        }

    }

    public void locat(java.lang.String l) {

        final java.lang.String log = zf.qctwkg(l);
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                textView.append(log);
                textView.append("\n");

            }

        });

        if (zf.dy(log, "Finish")) {

            if (zf.dy(auto, "enable") && zf.dy(type, "fixupdate")) {

                java.lang.String set36 = "/data/user/0/open.cn.awg.pro/settings/fixVersion";
                wj.xrwb(set36, fixcode);
                e1.tsk("提示", "执行完毕,更新成功");
                gj.gb();

            }

        } else if (zf.dy(log, "AWG:NEED_REBOOT")) {

            gj.xc(new java.lang.Thread() {

                public void run() {

                    gj.zt(2000);
                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            java.lang.String[] name = new java.lang.String[]{"Msg"};

                            java.lang.String[] value = new java.lang.String[]{"执行成功 点击重启"};
                            gj.tz(ErrorMessageActivity.class, name, value);

                        }

                    });

                }

            });

        }

    }

    public java.lang.String getCode() {

        return suDxx.xzzt() ? suDxx.st.getText().toString() : shDxx.st.getText().toString();

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

    }

    public void onRestart() {
        super.onRestart();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

    }

    public void onStart() {
        super.onStart();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

        i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(open.cn.awg.pro.R.id.qtgd1);

        qtgd1.v.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View vw, android.view.MotionEvent me) {
                if (me.getAction() == android.view.MotionEvent.ACTION_SCROLL && me.isFromSource(androidx.core.view.InputDeviceCompat.SOURCE_ROTARY_ENCODER)) {

                    float delta = -me.getAxisValue(androidx.core.view.MotionEventCompat.AXIS_SCROLL) *
                            androidx.core.view.ViewConfigurationCompat.getScaledVerticalScrollFactor(
                                    android.view.ViewConfiguration.get(lei), lei
                            );

                    vw.scrollBy(0, java.lang.Math.round(delta));
                    return true;
                }
                return false;
            }
        });

        qtgd1.v.requestFocus();

    }

    public void onResume() {
        super.onResume();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

    }

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.app.AwgProApplication.getInstance().removeActivity(lei);

    }

    private void $_onClick_d26d5d7080(android.view.View vw) {
        gj.gb();

    }

    private boolean $_onTouch_i32ee139ba1(android.view.View vw, android.view.MotionEvent me) {

        if (!vw.isFocused()) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(open.cn.awg.pro.R.id.qtgd1);
                    qtgd1.v.requestFocus();

                }

            });

        }
        return false;

    }

    private void $_onClick_i171ea34f1a(android.view.View vw) {

        final java.lang.String cmd = st.bjk(open.cn.awg.pro.R.id.bjk1).zf();

        try {

            gj.xc(new java.lang.Thread() {

                public void run() {

                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            st.wb(open.cn.awg.pro.R.id.wb1).kjd(0);
                            st.wb(open.cn.awg.pro.R.id.wb2).kjd(8);
                            st.bjk(open.cn.awg.pro.R.id.bjk1).kjd(8);
                            st.dxbj(open.cn.awg.pro.R.id.dxbj1).kjd(8);
                            st.dx(open.cn.awg.pro.R.id.dx1).kjd(8);
                            st.an(open.cn.awg.pro.R.id.an1).kjd(8);

                        }

                    });
                    runtime(cmd);

                }

            });

        } catch (java.lang.Throwable e) {

            e1.upload_error(e, "j4.*");

        }

    }

    private boolean $_onLongClick_ff56a8e7f6(android.view.View vw) {

        i.runlibrary.app.v.wb wb = st.wb(open.cn.awg.pro.R.id.wb1);

        java.lang.String wb1 = wb.zf();
        wb1 = zf.qc(wb1, "执行返回结果:\n", null);
        e1.tsk("详细信息", wb1);
        return true;

    }

    private void $_onClick_i0b66a667b1(android.view.View vw) {
        st.wb(open.cn.awg.pro.R.id.wb1).zf("执行返回结果:\n");

    }

    private void $_onClick_i34dc7bf20a(android.view.View vw) {
        gj.gb();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
        d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

        android.widget.EditText i32ee139ba1 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjk1);
        i32ee139ba1.setOnTouchListener($_on_setOnTouchListener_i32ee139ba1);

        android.widget.Button i171ea34f1a = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an1);
        i171ea34f1a.setOnClickListener($_on_setOnClickListener_i171ea34f1a);

        android.widget.TextView ff56a8e7f6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb1);
        ff56a8e7f6.setOnLongClickListener($_on_setOnLongClickListener_ff56a8e7f6);

        android.widget.Button i0b66a667b1 = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an2);
        i0b66a667b1.setOnClickListener($_on_setOnClickListener_i0b66a667b1);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        __layoutIsLoaded(ay, vw);
    }
}
