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

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.repair.ErrorMessageActivity;

public class ShellExecutorActivity extends BaseAwgActivity {

    public final ShellExecutorActivity lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public String type = "";
    public String auto = "";
    public String fixcode = "";
    private TextView textView;
    private EditText editText;
    private i.runlibrary.app.v.dxx suDxx;
    private i.runlibrary.app.v.dxx shDxx;
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnClickListener an2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn2Click(vw);
        }

    };
    private final View.OnLongClickListener wb1LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onWb1LongClick(vw);
        }

    };
    private final View.OnClickListener an1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn1Click(vw);
        }

    };
    private final View.OnTouchListener bjk1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjk1Touch(vw, me);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.shell_executor);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        textView = st.wb(R.id.wb1).st;
        editText = st.bjk(R.id.bjk1).st;
        suDxx = st.dxx(R.id.dxx1);
        shDxx = st.dxx(R.id.dxx2);
        shDxx.xzzt(true);

        String shs = sj.hqtz("sh");
        shs = zf.qctwkg(shs);
        type = sj.hqtz("type");
        type = zf.qctwkg(type);

        if (!zf.dy(shs, null) && !zf.dy(shs, "")) {

            st.bjk(R.id.bjk1).zf(shs);
            st.wb(R.id.wb2).kjd(0);
            st.bjk(R.id.bjk1).kjd(8);

        } else {

            st.wb(R.id.wb2).kjd(8);
            st.bjk(R.id.bjk1).kjd(0);

        }
        auto = sj.hqtz("auto");
        auto = zf.qctwkg(auto);
        fixcode = sj.hqtz("fixcode");
        fixcode = zf.qctwkg(fixcode);

        if (zf.dy(auto, "enable")) {

            gj.jmxc(new Runnable() {

                public void run() {

                    i.runlibrary.app.v.an an1 = st.an(R.id.an1);
                    an1.v.performClick();

                }

            });

        }

        String szk = sj.hqtz("szk");

        i.runlibrary.app.v.dx dx1 = st.dx(R.id.dx1);

        if (zf.dy(szk, "true")) {

            dx1.xzzt(true);

        }

        i.app.shizuku.setCallback(new i.app.shizuku.Callback() {
            public void ok(int o) {
            }
        });

        int c = i.app.shizuku.init(lei);

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.shell_executor_root, R.id.xdbj1, R.id.title_bar);

    }

    public void runtime(String cmd) {

        i.runlibrary.app.v.dx dx1 = st.dx(R.id.dx1);

        if (dx1.xzzt()) {

            try {

                String[] lines = zf.fg(cmd, "\n", true);

                for (String line : lines) {

                    line = zf.qctwkg(line);

                    String t = zf.qc(line, 0, 1);

                    String[] bltop = new String[]{"#"};

                    String[] blin = new String[]{AppPaths.appPath("")};

                    boolean istoppass = true;

                    boolean isinpass = true;

                    for (String bl : bltop) {

                        if (zf.ckt(line, bl)) {

                            istoppass = false;

                        }

                    }

                    for (String bl : blin) {

                        if (zf.cz(line, bl)) {

                            isinpass = false;

                        }

                    }

                    if (istoppass && isinpass) {

                        i.app.shizuku.Shell(line);

                    }

                }

            } catch (Throwable e) {

                locat(String.valueOf(e));

            }

        } else {

            try {

                Process process = Runtime.getRuntime().exec(getCode());

                OutputStream outPut = process.getOutputStream();

                InputStream inPut = process.getInputStream();

                InputStream eIn = process.getErrorStream();

                InputStreamReader isr = new InputStreamReader(inPut);

                OutputStreamWriter osw = new OutputStreamWriter(outPut);

                InputStreamReader eisr = new InputStreamReader(eIn);

                BufferedReader br = new BufferedReader(isr);

                BufferedReader ebr = new BufferedReader(eisr);

                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(cmd + "\n");
                bw.flush();

                String result = null;

                while ((result = br.readLine()) != null) {
                    locat(result);
                }

                String errorResult = null;

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

            } catch (Throwable e) {

                locat(String.valueOf(e));

            }

        }

    }

    public void locat(String l) {

        final String log = zf.qctwkg(l);
        gj.jmxc(new Runnable() {

            public void run() {

                textView.append(log);
                textView.append("\n");

            }

        });

        if (zf.dy(log, "Finish")) {

            if (zf.dy(auto, "enable") && zf.dy(type, "fixupdate")) {

                String set36 = AppPaths.appPath("settings/fixVersion");
                wj.xrwb(set36, fixcode);
                e1.tsk("提示", "执行完毕,更新成功");
                gj.gb();

            }

        } else if (zf.dy(log, "AWG:NEED_REBOOT")) {

            gj.xc(new Thread() {

                public void run() {

                    gj.zt(2000);
                    gj.jmxc(new Runnable() {

                        public void run() {

                            String[] name = new String[]{"Msg"};

                            String[] value = new String[]{"执行成功 点击重启"};
                            gj.tz(ErrorMessageActivity.class, name, value);

                        }

                    });

                }

            });

        }

    }

    public String getCode() {

        return suDxx.xzzt() ? suDxx.st.getText().toString() : shDxx.st.getText().toString();

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

    private boolean onBjk1Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            gj.jmxc(new Runnable() {

                public void run() {

                    i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(R.id.qtgd1);
                    qtgd1.v.requestFocus();

                }

            });

        }
        return false;

    }

    private void onAn1Click(View vw) {

        final String cmd = st.bjk(R.id.bjk1).zf();

        try {

            gj.xc(new Thread() {

                public void run() {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            st.wb(R.id.wb1).kjd(0);
                            st.wb(R.id.wb2).kjd(8);
                            st.bjk(R.id.bjk1).kjd(8);
                            st.dxbj(R.id.dxbj1).kjd(8);
                            st.dx(R.id.dx1).kjd(8);
                            st.an(R.id.an1).kjd(8);

                        }

                    });
                    runtime(cmd);

                }

            });

        } catch (Throwable e) {

            e1.upload_error(e, "j4.*");

        }

    }

    private boolean onWb1LongClick(View vw) {

        i.runlibrary.app.v.wb wb = st.wb(R.id.wb1);

        String wb1 = wb.zf();
        wb1 = zf.qc(wb1, "执行返回结果:\n", null);
        e1.tsk("详细信息", wb1);
        return true;

    }

    private void onAn2Click(View vw) {
        st.wb(R.id.wb1).zf("执行返回结果:\n");

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

        EditText i32ee139ba1 = (EditText) findViewById(ay, vw, R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        Button i171ea34f1a = (Button) findViewById(ay, vw, R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        TextView ff56a8e7f6 = (TextView) findViewById(ay, vw, R.id.wb1);
        ff56a8e7f6.setOnLongClickListener(wb1LongClickListener);

        Button i0b66a667b1 = (Button) findViewById(ay, vw, R.id.an2);
        i0b66a667b1.setOnClickListener(an2ClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
