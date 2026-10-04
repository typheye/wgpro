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
    public AppRuntimeBridge e1;
    public String type = "";
    public String auto = "";
    public String fixcode = "";
    private TextView textView;
    private EditText editText;
    private android.widget.RadioButton suDxx;
    private android.widget.RadioButton shDxx;
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
        e1 = new AppRuntimeBridge(this);
        setContentView(R.layout.shell_executor);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        textView = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.wb1));
        editText = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.bjk1));
        suDxx = ((android.widget.RadioButton) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.dxx1));
        shDxx = ((android.widget.RadioButton) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.dxx2));
        shDxx.setChecked(java.lang.Boolean.TRUE.equals(true));

        String shs = ShellExecutorActivity.this.getIntent().getStringExtra(String.valueOf("sh"));
        shs = open.cn.awg.pro.core.TextValues.trim(shs);
        type = ShellExecutorActivity.this.getIntent().getStringExtra(String.valueOf("type"));
        type = open.cn.awg.pro.core.TextValues.trim(type);

        if (!java.util.Objects.equals(shs, null) && !java.util.Objects.equals(shs, "")) {

            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(shs));
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.wb2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.bjk1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.wb2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.bjk1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

        }
        auto = ShellExecutorActivity.this.getIntent().getStringExtra(String.valueOf("auto"));
        auto = open.cn.awg.pro.core.TextValues.trim(auto);
        fixcode = ShellExecutorActivity.this.getIntent().getStringExtra(String.valueOf("fixcode"));
        fixcode = open.cn.awg.pro.core.TextValues.trim(fixcode);

        if (java.util.Objects.equals(auto, "enable")) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    android.widget.Button an1 = ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.an1));
                    an1.performClick();

                }

            });

        }

        String szk = ShellExecutorActivity.this.getIntent().getStringExtra(String.valueOf("szk"));

        android.widget.CheckBox dx1 = ((android.widget.CheckBox) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.dx1));

        if (java.util.Objects.equals(szk, "true")) {

            dx1.setChecked(java.lang.Boolean.TRUE.equals(true));

        }

        open.cn.awg.pro.core.ShizukuShell.initialize(lei);

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.shell_executor_root, R.id.xdbj1, R.id.title_bar);

    }

    public void runtime(String cmd) {

        android.widget.CheckBox dx1 = ((android.widget.CheckBox) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.dx1));

        if (dx1.isChecked()) {

            try {

                String[] lines = open.cn.awg.pro.core.TextValues.split(cmd, "\n", true);

                for (String line : lines) {

                    line = open.cn.awg.pro.core.TextValues.trim(line);

                    String t = open.cn.awg.pro.core.TextValues.slice(line, 0, 1);

                    String[] bltop = new String[]{"#"};

                    String[] blin = new String[]{AppPaths.appPath("")};

                    boolean istoppass = true;

                    boolean isinpass = true;

                    for (String bl : bltop) {

                        if (open.cn.awg.pro.core.TextValues.startsWith(line, bl)) {

                            istoppass = false;

                        }

                    }

                    for (String bl : blin) {

                        if (open.cn.awg.pro.core.TextValues.contains(line, bl)) {

                            isinpass = false;

                        }

                    }

                    if (istoppass && isinpass) {

                        open.cn.awg.pro.core.ShizukuShell.execute(line);

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

        final String log = open.cn.awg.pro.core.TextValues.trim(l);
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                textView.append(log);
                textView.append("\n");

            }

        });

        if (java.util.Objects.equals(log, "Finish")) {

            if (java.util.Objects.equals(auto, "enable") && java.util.Objects.equals(type, "fixupdate")) {

                String set36 = AppPaths.appPath("settings/fixVersion");
                open.cn.awg.pro.core.NativeFiles.writeText(ShellExecutorActivity.this, (String) (set36), (String) (fixcode));
                e1.tsk("提示", "执行完毕,更新成功");
                open.cn.awg.pro.core.NativeActions.finish(ShellExecutorActivity.this);

            }

        } else if (java.util.Objects.equals(log, "AWG:NEED_REBOOT")) {

            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.sleep(2000);
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            String[] name = new String[]{"Msg"};

                            String[] value = new String[]{"执行成功 点击重启"};
                            open.cn.awg.pro.core.NativeActions.launch(ShellExecutorActivity.this, (Class<?>) (ErrorMessageActivity.class), (String[]) (name), (Object[]) (value));

                        }

                    });

                }

            });

        }

    }

    public String getCode() {

        return suDxx.isChecked() ? suDxx.getText().toString() : shDxx.getText().toString();

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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
        open.cn.awg.pro.core.NativeActions.finish(ShellExecutorActivity.this);

    }

    private boolean onBjk1Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private void onAn1Click(View vw) {

        final String cmd = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.bjk1)).getText().toString();

        try {

            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.wb1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.wb2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.bjk1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                            ((android.widget.RadioGroup) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.dxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                            ((android.widget.CheckBox) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.dx1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                            ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.an1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

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

        android.widget.TextView wb = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.wb1));

        String wb1 = wb.getText().toString();
        wb1 = open.cn.awg.pro.core.TextValues.slice(wb1, "执行返回结果:\n", null);
        e1.tsk("详细信息", wb1);
        return true;

    }

    private void onAn2Click(View vw) {
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ShellExecutorActivity.this.getWindow().getDecorView(), R.id.wb1)).setText(String.valueOf("执行返回结果:\n"));

    }

    private void onTx1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(ShellExecutorActivity.this);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        EditText i32ee139ba1 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        Button i171ea34f1a = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        TextView ff56a8e7f6 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb1);
        ff56a8e7f6.setOnLongClickListener(wb1LongClickListener);

        Button i0b66a667b1 = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an2);
        i0b66a667b1.setOnClickListener(an2ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
