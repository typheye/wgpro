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
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
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
        setContentView(R.layout.server_debug);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        show(0);

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.server_debug_root, R.id.xdbj1, R.id.title_bar);

        i.runlibrary.app.v.xlcd xlcd1 = st.xlcd(R.id.xlcd1);

        String[] sz = new String[]{"GET方式", "POST提交数据方式", "POST提交JSON数据方式"};
        xlcd1.csh(sz);

        i.runlibrary.app.v.xlcd xlcd2 = st.xlcd(R.id.xlcd2);
        sz = new String[]{"常规模式", "高级模式"};
        xlcd2.csh(sz);

    }

    public void show(int i) {

        i.runlibrary.app.v.xxbj x1 = st.xxbj(R.id.xxbj1);

        i.runlibrary.app.v.xxbj x2 = st.xxbj(R.id.xxbj6);

        if (i == 0) {

            x1.kjd(0);
            x2.kjd(8);

        } else if (i == 1) {

            x1.kjd(8);
            x2.kjd(0);

        }

    }

    public void load(Object url, Object title) {

        String[] name = new String[]{"url", "title", "code"};

        Object[] value = new Object[]{url, title, "super1"};
        gj.tz(TextViewerActivity.class, name, value);

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

    private boolean onBjkurlTouch(View vw, MotionEvent me) {

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

    private void onXlcd1ItemSelected(AdapterView vw, View view, int pn, long id) {

        final i.runlibrary.app.v.xxbj xxbj4 = st.xxbj(R.id.xxbj4);

        final i.runlibrary.app.v.xxbj xxbj5 = st.xxbj(R.id.xxbj5);

        if (pn == 0) {

            gj.jmxc(new Runnable() {

                public void run() {

                    xxbj4.kjd(8);
                    xxbj5.kjd(8);

                }

            });

        } else if (pn == 1) {

            gj.jmxc(new Runnable() {

                public void run() {

                    xxbj4.kjd(0);
                    xxbj5.kjd(8);

                }

            });

        } else if (pn == 2) {

            gj.jmxc(new Runnable() {

                public void run() {

                    xxbj4.kjd(8);
                    xxbj5.kjd(0);

                }

            });

        }

    }

    private void onXlcd1NothingSelected(AdapterView vw) {

    }

    private boolean onBjkgp1Touch(View vw, MotionEvent me) {

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

    private boolean onBjkgp2Touch(View vw, MotionEvent me) {

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

    private void onXlcd2ItemSelected(AdapterView vw, View view, int pn, long id) {

        final i.runlibrary.app.v.xxbj xxbj7 = st.xxbj(R.id.xxbj7);

        if (pn == 0) {

            gj.jmxc(new Runnable() {

                public void run() {

                    xxbj7.kjd(8);

                }

            });

        } else if (pn == 1) {

            gj.jmxc(new Runnable() {

                public void run() {

                    xxbj7.kjd(0);

                }

            });

        }

    }

    private void onXlcd2NothingSelected(AdapterView vw) {

    }

    private void onDx1CheckedChanged(CompoundButton vw, boolean ic) {

        final i.runlibrary.app.v.bjk bjks1 = st.bjk(R.id.bjks1);

        final i.runlibrary.app.v.bjk bjks2 = st.bjk(R.id.bjks2);

        final i.runlibrary.app.v.bjk bjks3 = st.bjk(R.id.bjks3);

        final i.runlibrary.app.v.bjk bjks4 = st.bjk(R.id.bjks4);

        final i.runlibrary.app.v.bjk bjks5 = st.bjk(R.id.bjks5);

        final i.runlibrary.app.v.bjk bjks6 = st.bjk(R.id.bjks6);

        final i.runlibrary.app.v.bjk bjks7 = st.bjk(R.id.bjks7);

        final String va1 = "utf-8";

        final String va2 = "name1=value1;name2=value2;";

        final String va3 = "true";

        final String va4 = "User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept=*/*||accept-language=zh-CN";

        final String va5 = "20000";

        final String va6 = "20000";

        final String va7 = "10.0.0.172:80";

        if (ic) {

            gj.jmxc(new Runnable() {

                public void run() {

                    bjks1.zf(va1);
                    bjks2.zf(va2);
                    bjks3.zf(va3);
                    bjks4.zf(va4);
                    bjks5.zf(va5);
                    bjks6.zf(va6);
                    bjks7.zf(va7);

                }

            });

        } else {

            gj.jmxc(new Runnable() {

                public void run() {

                    bjks1.zf("");
                    bjks2.zf("");
                    bjks3.zf("");
                    bjks4.zf("");
                    bjks5.zf("");
                    bjks6.zf("");
                    bjks7.zf("");

                }

            });

        }

    }

    private boolean onBjks1Touch(View vw, MotionEvent me) {

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

    private boolean onBjks2Touch(View vw, MotionEvent me) {

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

    private boolean onBjks3Touch(View vw, MotionEvent me) {

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

    private boolean onBjks4Touch(View vw, MotionEvent me) {

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

    private boolean onBjks5Touch(View vw, MotionEvent me) {

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

    private boolean onBjks6Touch(View vw, MotionEvent me) {

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

    private boolean onBjks7Touch(View vw, MotionEvent me) {

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

        String a = "";

        String[] b = new String[]{""};

        String c = "utf-8";

        String d = "";

        boolean e = true;

        String f = "";

        int g = -1;

        int h = -1;

        String i = "";

        final i.runlibrary.app.v.xlcd xl1 = st.xlcd(R.id.xlcd1);

        final i.runlibrary.app.v.xlcd xl2 = st.xlcd(R.id.xlcd2);

        if (zf.dy(st.bjk(R.id.bjkurl).zf(), "")) {

            e1.tsk("提示", "参数:\"服务端链接\"不能为空");

        } else {

            a = st.bjk(R.id.bjkurl).zf();
            a = zf.qctwkg(a);

        }

        if (xl1.dqzsxmxh() == 0) {

            b = new String[]{""};

        } else if (xl1.dqzsxmxh() == 1) {

            if (zf.dy(st.bjk(R.id.bjkgp1).zf(), "")) {

                e1.tsk("提示", "参数\"POST提交的数据\"不能为空");

            } else {

                String fb = st.bjk(R.id.bjkgp1).zf();
                fb = zf.qctwkg(fb);
                b = zf.fg(fb, "\n", true);

            }

        } else if (xl1.dqzsxmxh() == 2) {

            if (zf.dy(st.bjk(R.id.bjkgp2).zf(), "")) {

                e1.tsk("提示", "参数\"POST提交的JSON数据\"不能为空");

            } else {

                String fb = st.bjk(R.id.bjkgp2).zf();
                fb = zf.qctwkg(fb);
                b = zf.fg(fb, "\n", true);

            }

        }

        if (xl2.dqzsxmxh() == 1) {

            if (zf.dy(st.bjk(R.id.bjks1).zf(), "")) {

                e1.tsk("提示", "参数\"编码\"不能为空");

            } else {

                c = st.bjk(R.id.bjks1).zf();
                c = zf.qctwkg(c);

            }
            d = st.bjk(R.id.bjks2).zf();
            d = zf.qctwkg(d);

            if (zf.dy(st.bjk(R.id.bjks3).zf(), "")) {

                e1.tsk("提示", "参数\"是否自动设置Cookie\"不能为空");

            } else {

                String fe = st.bjk(R.id.bjks3).zf();
                fe = zf.qctwkg(fe);

                if (zf.dy(fe, "true")) {

                    e = true;

                } else if (zf.dy(fe, "false")) {

                    e = false;

                }

            }

            if (zf.dy(st.bjk(R.id.bjks4).zf(), "")) {

                e1.tsk("提示", "参数\"Header头\"不能为空");

            } else {

                f = st.bjk(R.id.bjks4).zf();
                f = zf.qctwkg(f);

            }

            if (zf.dy(st.bjk(R.id.bjks5).zf(), "")) {

                e1.tsk("提示", "参数\"连接超时\"不能为空");

            } else {

                g = Integer.parseInt(st.bjk(R.id.bjks5).zf());

            }

            if (zf.dy(st.bjk(R.id.bjks6).zf(), "")) {

                e1.tsk("提示", "参数\"接收超时\"不能为空");

            } else {

                h = Integer.parseInt(st.bjk(R.id.bjks6).zf());

            }
            i = st.bjk(R.id.bjks7).zf();
            i = zf.qctwkg(i);

        } else {

            d = "1";
            e = true;
            f = "1";
            g = 1;
            h = 1;
            i = "1";

        }

        if (zf.dy(a, "") || zf.dy(c, "") || zf.dy(f, "") || g == -1 || h == -1) {

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
            gj.xc(new Thread() {

                public void run() {

                    String back = "";
                    gj.jmxc(new Runnable() {

                        public void run() {

                            show(1);

                        }

                    });

                    if (xl1.dqzsxmxh() == 0) {

                        if (xl2.dqzsxmxh() == 0) {

                            try {

                                back = wl.hq(s_a, null, s_c);

                            } catch (Throwable e) {

                                e1.upload_error(e, "l5_a1_fuwuq_d0313_y2022.*");
                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        show(0);

                                    }

                                });

                            }

                        } else if (xl2.dqzsxmxh() == 1) {

                            try {

                                back = wl.hq(s_a, null, s_c, s_d, s_e, s_f, s_g, s_h, null);

                            } catch (Throwable e) {

                                e1.upload_error(e, "l5_a1_fuwuq_d0313_y2022.*");
                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        show(0);

                                    }

                                });

                            }

                        }

                    } else if (xl1.dqzsxmxh() == 1) {

                        if (xl2.dqzsxmxh() == 0) {

                            try {

                                back = wl.hq(s_a, s_b, s_c);

                            } catch (Throwable e) {

                                e1.upload_error(e, "l5_a1_fuwuq_d0313_y2022.*");
                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        show(0);

                                    }

                                });

                            }

                        } else if (xl2.dqzsxmxh() == 1) {

                            try {

                                back = wl.hq(s_a, s_b, s_c, s_d, s_e, s_f, s_g, s_h, null);

                            } catch (Throwable e) {

                                e1.upload_error(e, "l5_a1_fuwuq_d0313_y2022.*");
                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        show(0);

                                    }

                                });

                            }

                        }

                    } else if (xl1.dqzsxmxh() == 2) {

                        if (xl2.dqzsxmxh() == 0) {

                            try {

                                back = wl.hqjson(s_a, s_b, s_c);

                            } catch (Throwable e) {

                                e1.upload_error(e, "l5_a1_fuwuq_d0313_y2022.*");
                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        show(0);

                                    }

                                });

                            }

                        } else if (xl2.dqzsxmxh() == 1) {

                            try {

                                back = wl.hqjson(s_a, s_b, s_c, s_d, s_e, s_f, s_g, s_h, null);

                            } catch (Throwable e) {

                                e1.upload_error(e, "l5_a1_fuwuq_d0313_y2022.*");
                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        show(0);

                                    }

                                });

                            }

                        }

                    }

                    final String wblj = AppPaths.appPath("cache/webbacktext");

                    final String nr = back;
                    gj.sc(nr);
                    gj.jmxc(new Runnable() {

                        public void run() {

                            try {

                                wj.xrwb(wblj, nr);

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
        gj.gb();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        EditText e45f6690d3 = (EditText) findViewById(ay, vw, R.id.bjkurl);
        e45f6690d3.setOnTouchListener(bjkurlTouchListener);

        Spinner i004a739c93 = (Spinner) findViewById(ay, vw, R.id.xlcd1);
        i004a739c93.setOnItemSelectedListener(xlcd1ItemSelectedListener);

        EditText i8d69eec323 = (EditText) findViewById(ay, vw, R.id.bjkgp1);
        i8d69eec323.setOnTouchListener(bjkgp1TouchListener);

        EditText i956d0f4bd8 = (EditText) findViewById(ay, vw, R.id.bjkgp2);
        i956d0f4bd8.setOnTouchListener(bjkgp2TouchListener);

        Spinner fef1635c8d = (Spinner) findViewById(ay, vw, R.id.xlcd2);
        fef1635c8d.setOnItemSelectedListener(xlcd2ItemSelectedListener);

        CheckBox i3d918d2dcb = (CheckBox) findViewById(ay, vw, R.id.dx1);
        i3d918d2dcb.setOnCheckedChangeListener(dx1CheckedChangeListener);

        EditText i2ce2aa6972 = (EditText) findViewById(ay, vw, R.id.bjks1);
        i2ce2aa6972.setOnTouchListener(bjks1TouchListener);

        EditText b7e129881e = (EditText) findViewById(ay, vw, R.id.bjks2);
        b7e129881e.setOnTouchListener(bjks2TouchListener);

        EditText d9fcd0aec2 = (EditText) findViewById(ay, vw, R.id.bjks3);
        d9fcd0aec2.setOnTouchListener(bjks3TouchListener);

        EditText fc0b80656a = (EditText) findViewById(ay, vw, R.id.bjks4);
        fc0b80656a.setOnTouchListener(bjks4TouchListener);

        EditText i8dfdb24277 = (EditText) findViewById(ay, vw, R.id.bjks5);
        i8dfdb24277.setOnTouchListener(bjks5TouchListener);

        EditText i3793cc74e0 = (EditText) findViewById(ay, vw, R.id.bjks6);
        i3793cc74e0.setOnTouchListener(bjks6TouchListener);

        EditText i026fdb816d = (EditText) findViewById(ay, vw, R.id.bjks7);
        i026fdb816d.setOnTouchListener(bjks7TouchListener);

        Button i171ea34f1a = (Button) findViewById(ay, vw, R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
