/**
 ******************************************************************************
 * @file    ServerDebugActivity.java
 * @author  Typheye
 * @brief   Provides server request, cookie, header, and JSON debugging tools.
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
package open.cn.awg.pro.experimental;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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
import open.cn.awg.pro.viewer.TextViewerActivity;

public class ServerDebugActivity extends BaseAwgActivity {

    public final ServerDebugActivity lei = this, 类 = this;
    public String um = "";
    public File f;
    public AppRuntimeBridge e1;
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnClickListener an1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn1Click(vw);
        }

    };
    private final View.OnTouchListener bjks7TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjks7Touch(vw, me);
        }

    };
    private final View.OnTouchListener bjks6TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjks6Touch(vw, me);
        }

    };
    private final View.OnTouchListener bjks5TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjks5Touch(vw, me);
        }

    };
    private final View.OnTouchListener bjks4TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjks4Touch(vw, me);
        }

    };
    private final View.OnTouchListener bjks3TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjks3Touch(vw, me);
        }

    };
    private final View.OnTouchListener bjks2TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjks2Touch(vw, me);
        }

    };
    private final View.OnTouchListener bjks1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjks1Touch(vw, me);
        }

    };
    private final CompoundButton.OnCheckedChangeListener dx1CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onDx1CheckedChanged(vw, ic);
        }

    };
    private final AdapterView.OnItemSelectedListener xlcd2ItemSelectedListener = new AdapterView.OnItemSelectedListener() {

        public void onItemSelected(AdapterView vw, View view, int pn, long id) {
            onXlcd2ItemSelected(vw, view, pn, id);
        }

        public void onNothingSelected(AdapterView vw) {
            onXlcd2NothingSelected(vw);
        }

    };
    private final View.OnTouchListener bjkgp2TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjkgp2Touch(vw, me);
        }

    };
    private final View.OnTouchListener bjkgp1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjkgp1Touch(vw, me);
        }

    };
    private final AdapterView.OnItemSelectedListener xlcd1ItemSelectedListener = new AdapterView.OnItemSelectedListener() {

        public void onItemSelected(AdapterView vw, View view, int pn, long id) {
            onXlcd1ItemSelected(vw, view, pn, id);
        }

        public void onNothingSelected(AdapterView vw) {
            onXlcd1NothingSelected(vw);
        }

    };
    private final View.OnTouchListener bjkurlTouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjkurlTouch(vw, me);
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
        setContentView(R.layout.server_debug);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        show(0);

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.server_debug_root, R.id.xdbj1, R.id.title_bar);

        android.widget.Spinner xlcd1 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.xlcd1));

        String[] sz = new String[]{"GET方式", "POST提交数据方式", "POST提交JSON数据方式"};
        open.cn.awg.pro.core.NativeViewValues.spinner(xlcd1, sz);

        android.widget.Spinner xlcd2 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.xlcd2));
        sz = new String[]{"常规模式", "高级模式"};
        open.cn.awg.pro.core.NativeViewValues.spinner(xlcd2, sz);

    }

    public void show(int i) {

        android.widget.LinearLayout x1 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.xxbj1));

        android.widget.LinearLayout x2 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.xxbj6));

        if (i == 0) {

            x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
            x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

        } else if (i == 1) {

            x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
            x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

        }

    }

    public void load(Object url, Object title) {

        String[] name = new String[]{"url", "title", "code"};

        Object[] value = new Object[]{url, title, "super1"};
        open.cn.awg.pro.core.NativeActions.launch(ServerDebugActivity.this, (Class<?>) (TextViewerActivity.class), (String[]) (name), (Object[]) (value));

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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
        open.cn.awg.pro.core.NativeActions.finish(ServerDebugActivity.this);

    }

    private boolean onBjkurlTouch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private void onXlcd1ItemSelected(AdapterView vw, View view, int pn, long id) {

        final android.widget.LinearLayout xxbj4 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.xxbj4));

        final android.widget.LinearLayout xxbj5 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.xxbj5));

        if (pn == 0) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    xxbj4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    xxbj5.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        } else if (pn == 1) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    xxbj4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    xxbj5.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        } else if (pn == 2) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    xxbj4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    xxbj5.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                }

            });

        }

    }

    private void onXlcd1NothingSelected(AdapterView vw) {

    }

    private boolean onBjkgp1Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private boolean onBjkgp2Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private void onXlcd2ItemSelected(AdapterView vw, View view, int pn, long id) {

        final android.widget.LinearLayout xxbj7 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.xxbj7));

        if (pn == 0) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    xxbj7.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        } else if (pn == 1) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    xxbj7.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                }

            });

        }

    }

    private void onXlcd2NothingSelected(AdapterView vw) {

    }

    private void onDx1CheckedChanged(CompoundButton vw, boolean ic) {

        final android.widget.EditText bjks1 = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjks1));

        final android.widget.EditText bjks2 = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjks2));

        final android.widget.EditText bjks3 = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjks3));

        final android.widget.EditText bjks4 = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjks4));

        final android.widget.EditText bjks5 = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjks5));

        final android.widget.EditText bjks6 = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjks6));

        final android.widget.EditText bjks7 = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjks7));

        final String va1 = "utf-8";

        final String va2 = "name1=value1;name2=value2;";

        final String va3 = "true";

        final String va4 = "User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept=*/*||accept-language=zh-CN";

        final String va5 = "20000";

        final String va6 = "20000";

        final String va7 = "10.0.0.172:80";

        if (ic) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    bjks1.setText(String.valueOf(va1));
                    bjks2.setText(String.valueOf(va2));
                    bjks3.setText(String.valueOf(va3));
                    bjks4.setText(String.valueOf(va4));
                    bjks5.setText(String.valueOf(va5));
                    bjks6.setText(String.valueOf(va6));
                    bjks7.setText(String.valueOf(va7));

                }

            });

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    bjks1.setText(String.valueOf(""));
                    bjks2.setText(String.valueOf(""));
                    bjks3.setText(String.valueOf(""));
                    bjks4.setText(String.valueOf(""));
                    bjks5.setText(String.valueOf(""));
                    bjks6.setText(String.valueOf(""));
                    bjks7.setText(String.valueOf(""));

                }

            });

        }

    }

    private boolean onBjks1Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private boolean onBjks2Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private boolean onBjks3Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private boolean onBjks4Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private boolean onBjks5Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private boolean onBjks6Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private boolean onBjks7Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private void onAn1Click(View vw) {

        String a = "";

        String[] b = new String[]{""};

        String c = "utf-8";

        String d = "";

        boolean e = true;

        String f = "";

        int g = -1;

        int h = -1;

        String i = "";

        final android.widget.Spinner xl1 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.xlcd1));

        final android.widget.Spinner xl2 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.xlcd2));

        if (java.util.Objects.equals(((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjkurl)).getText().toString(), "")) {

            e1.tsk("提示", "参数:\"服务端链接\"不能为空");

        } else {

            a = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjkurl)).getText().toString();
            a = open.cn.awg.pro.core.TextValues.trim(a);

        }

        if (xl1.getLastVisiblePosition() == 0) {

            b = new String[]{""};

        } else if (xl1.getLastVisiblePosition() == 1) {

            if (java.util.Objects.equals(((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjkgp1)).getText().toString(), "")) {

                e1.tsk("提示", "参数\"POST提交的数据\"不能为空");

            } else {

                String fb = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjkgp1)).getText().toString();
                fb = open.cn.awg.pro.core.TextValues.trim(fb);
                b = open.cn.awg.pro.core.TextValues.split(fb, "\n", true);

            }

        } else if (xl1.getLastVisiblePosition() == 2) {

            if (java.util.Objects.equals(((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjkgp2)).getText().toString(), "")) {

                e1.tsk("提示", "参数\"POST提交的JSON数据\"不能为空");

            } else {

                String fb = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjkgp2)).getText().toString();
                fb = open.cn.awg.pro.core.TextValues.trim(fb);
                b = open.cn.awg.pro.core.TextValues.split(fb, "\n", true);

            }

        }

        if (xl2.getLastVisiblePosition() == 1) {

            if (java.util.Objects.equals(((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjks1)).getText().toString(), "")) {

                e1.tsk("提示", "参数\"编码\"不能为空");

            } else {

                c = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjks1)).getText().toString();
                c = open.cn.awg.pro.core.TextValues.trim(c);

            }
            d = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjks2)).getText().toString();
            d = open.cn.awg.pro.core.TextValues.trim(d);

            if (java.util.Objects.equals(((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjks3)).getText().toString(), "")) {

                e1.tsk("提示", "参数\"是否自动设置Cookie\"不能为空");

            } else {

                String fe = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjks3)).getText().toString();
                fe = open.cn.awg.pro.core.TextValues.trim(fe);

                if (java.util.Objects.equals(fe, "true")) {

                    e = true;

                } else if (java.util.Objects.equals(fe, "false")) {

                    e = false;

                }

            }

            if (java.util.Objects.equals(((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjks4)).getText().toString(), "")) {

                e1.tsk("提示", "参数\"Header头\"不能为空");

            } else {

                f = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjks4)).getText().toString();
                f = open.cn.awg.pro.core.TextValues.trim(f);

            }

            if (java.util.Objects.equals(((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjks5)).getText().toString(), "")) {

                e1.tsk("提示", "参数\"连接超时\"不能为空");

            } else {

                g = Integer.parseInt(((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjks5)).getText().toString());

            }

            if (java.util.Objects.equals(((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjks6)).getText().toString(), "")) {

                e1.tsk("提示", "参数\"接收超时\"不能为空");

            } else {

                h = Integer.parseInt(((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjks6)).getText().toString());

            }
            i = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ServerDebugActivity.this.getWindow().getDecorView(), R.id.bjks7)).getText().toString();
            i = open.cn.awg.pro.core.TextValues.trim(i);

        } else {

            d = "1";
            e = true;
            f = "1";
            g = 1;
            h = 1;
            i = "1";

        }

        if (java.util.Objects.equals(a, "") || java.util.Objects.equals(c, "") || java.util.Objects.equals(f, "") || g == -1 || h == -1) {

        } else {

            final String s_a = a;

            final String[] s_b = b;

            final String s_c = c;

            final String s_d = d;

            final boolean s_e = e;

            final String s_f = f;

            final int s_g = g;

            final int s_h = h;

            final String s_i = i;
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    String back = "";
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            show(1);

                        }

                    });

                    if (xl1.getLastVisiblePosition() == 0) {

                        if (xl2.getLastVisiblePosition() == 0) {

                            try {

                                back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(s_a), (String[]) (null), (String) (s_c), null, false, null, 0, 0, null).bodyForHttp200();

                            } catch (Throwable e) {

                                e1.upload_error(e, "l5_a1_fuwuq_d0313_y2022.*");
                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        show(0);

                                    }

                                });

                            }

                        } else if (xl2.getLastVisiblePosition() == 1) {

                            try {

                                back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(s_a), (String[]) (null), (String) (s_c), (String) (s_d), java.lang.Boolean.TRUE.equals(s_e), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (s_f)), open.cn.awg.pro.core.TextValues.intValue(s_g, -1), open.cn.awg.pro.core.TextValues.intValue(s_h, -1), (String) (null)).bodyForHttp200();

                            } catch (Throwable e) {

                                e1.upload_error(e, "l5_a1_fuwuq_d0313_y2022.*");
                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        show(0);

                                    }

                                });

                            }

                        }

                    } else if (xl1.getLastVisiblePosition() == 1) {

                        if (xl2.getLastVisiblePosition() == 0) {

                            try {

                                back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(s_a), (String[]) (s_b), (String) (s_c), null, false, null, 0, 0, null).bodyForHttp200();

                            } catch (Throwable e) {

                                e1.upload_error(e, "l5_a1_fuwuq_d0313_y2022.*");
                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        show(0);

                                    }

                                });

                            }

                        } else if (xl2.getLastVisiblePosition() == 1) {

                            try {

                                back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(s_a), (String[]) (s_b), (String) (s_c), (String) (s_d), java.lang.Boolean.TRUE.equals(s_e), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (s_f)), open.cn.awg.pro.core.TextValues.intValue(s_g, -1), open.cn.awg.pro.core.TextValues.intValue(s_h, -1), (String) (null)).bodyForHttp200();

                            } catch (Throwable e) {

                                e1.upload_error(e, "l5_a1_fuwuq_d0313_y2022.*");
                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        show(0);

                                    }

                                });

                            }

                        }

                    } else if (xl1.getLastVisiblePosition() == 2) {

                        if (xl2.getLastVisiblePosition() == 0) {

                            try {

                                back = open.cn.awg.pro.core.NativeHttp.postJson(String.valueOf(s_a), String.valueOf(s_b), (String) (s_c), null, false, null, 0, 0, null).bodyForHttp200();

                            } catch (Throwable e) {

                                e1.upload_error(e, "l5_a1_fuwuq_d0313_y2022.*");
                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        show(0);

                                    }

                                });

                            }

                        } else if (xl2.getLastVisiblePosition() == 1) {

                            try {

                                back = open.cn.awg.pro.core.NativeHttp.postJson(String.valueOf(s_a), String.valueOf(s_b), (String) (s_c), (String) (s_d), java.lang.Boolean.TRUE.equals(s_e), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (s_f)), open.cn.awg.pro.core.TextValues.intValue(s_g, -1), open.cn.awg.pro.core.TextValues.intValue(s_h, -1), (String) (null)).bodyForHttp200();

                            } catch (Throwable e) {

                                e1.upload_error(e, "l5_a1_fuwuq_d0313_y2022.*");
                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        show(0);

                                    }

                                });

                            }

                        }

                    }

                    final String wblj = AppPaths.appPath("cache/webbacktext");

                    final String nr = back;
                    open.cn.awg.pro.core.NativeActions.log(nr);
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            try {

                                open.cn.awg.pro.core.NativeFiles.writeText(ServerDebugActivity.this, (String) (wblj), (String) (nr));

                            } catch (Throwable e) {

                                e1.upload_error(e, "l5_a1_fuwuq_d0313_y2022.*");

                            }
                            load(wblj, "返回文本");
                            show(0);

                        }

                    });

                }

            });

        }

    }

    private void onTx1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(ServerDebugActivity.this);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        EditText e45f6690d3 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjkurl);
        e45f6690d3.setOnTouchListener(bjkurlTouchListener);

        Spinner i004a739c93 = (Spinner) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xlcd1);
        i004a739c93.setOnItemSelectedListener(xlcd1ItemSelectedListener);

        EditText i8d69eec323 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjkgp1);
        i8d69eec323.setOnTouchListener(bjkgp1TouchListener);

        EditText i956d0f4bd8 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjkgp2);
        i956d0f4bd8.setOnTouchListener(bjkgp2TouchListener);

        Spinner fef1635c8d = (Spinner) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xlcd2);
        fef1635c8d.setOnItemSelectedListener(xlcd2ItemSelectedListener);

        CheckBox i3d918d2dcb = (CheckBox) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.dx1);
        i3d918d2dcb.setOnCheckedChangeListener(dx1CheckedChangeListener);

        EditText i2ce2aa6972 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjks1);
        i2ce2aa6972.setOnTouchListener(bjks1TouchListener);

        EditText b7e129881e = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjks2);
        b7e129881e.setOnTouchListener(bjks2TouchListener);

        EditText d9fcd0aec2 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjks3);
        d9fcd0aec2.setOnTouchListener(bjks3TouchListener);

        EditText fc0b80656a = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjks4);
        fc0b80656a.setOnTouchListener(bjks4TouchListener);

        EditText i8dfdb24277 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjks5);
        i8dfdb24277.setOnTouchListener(bjks5TouchListener);

        EditText i3793cc74e0 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjks6);
        i3793cc74e0.setOnTouchListener(bjks6TouchListener);

        EditText i026fdb816d = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjks7);
        i026fdb816d.setOnTouchListener(bjks7TouchListener);

        Button i171ea34f1a = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
