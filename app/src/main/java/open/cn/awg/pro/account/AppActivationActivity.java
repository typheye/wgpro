/**
 ******************************************************************************
 * @file    AppActivationActivity.java
 * @author  Typheye
 * @brief   Application activation screen.
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
package open.cn.awg.pro.account;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import com.kingqi.zwcj.日期工具;
import com.kingqi.zwcj.应用工具;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.store.ProductPurchaseActivity;
import open.cn.awg.pro.viewer.TextViewerActivity;

public class AppActivationActivity extends BaseAwgActivity {

    public final AppActivationActivity lei = this, 类 = this;
    public int mode = 0;
    public boolean isJh = false;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    private final View.OnClickListener tx6ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx6Click(vw);
        }

    };
    private final View.OnClickListener tx5ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx5Click(vw);
        }

    };
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
    private final View.OnTouchListener bjk1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjk1Touch(vw, me);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
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
    private final View.OnClickListener xxbj8ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj8Click(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.app_activation);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.app_activation_root, R.id.xdbj1, R.id.title_bar);

    }

    public void jz() {

        final String set1 = AppPaths.appPath("settings/f7/Enable2");
        gj.jmxc(new Runnable() {

            public void run() {

                String te1 = wj.dqwb(set1);

                if (zf.dy(wj.dqwb(set1), "") || !wj.cz(set1)) {

                    st.wb(R.id.wb5).zf("请输入15位产品ID");

                } else {

                    st.wb(R.id.wb5).zf(te1);

                }

            }

        });
        zh();

    }

    public void show(int i) {

        if (i == 0) {

            qh_();
            gj.jmxc(new Runnable() {

                public void run() {

                    st.xxbj(R.id.xxbj1).kjd(0);
                    st.xxbj(R.id.xxbj4).kjd(8);
                    st.xxbj(R.id.xxbj9).kjd(8);
                    st.xxbj(R.id.xxbj6).kjd(8);

                }

            });

        } else if (i == 1) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.xxbj(R.id.xxbj1).kjd(8);
                    st.xxbj(R.id.xxbj4).kjd(8);
                    st.xxbj(R.id.xxbj9).kjd(8);
                    st.xxbj(R.id.xxbj6).kjd(0);

                }

            });

        }

    }

    public void zh() {
        gj.jmxc(new Runnable() {

            public void run() {

                String a = AppPaths.appPath("settings/f7/Enable2");

                if (wj.cz(a) && !zf.dy(wj.dqwb(a), "")) {

                    st.wb(R.id.wb10).zf("已激活");
                    st.wb(R.id.wb11).zf("");
                    st.wb(R.id.wb11).kjd(8);
                    st.tx(R.id.tx4).tx(R.mipmap.a28);

                    String color = zf.zf(R.color.colorTextTrue);
                    st.kp(R.id.kp4).kpbjys(color);
                    st.xdbj(R.id.xdbj4).kjd(8);
                    isJh = true;

                } else {

                    st.wb(R.id.wb10).zf("未激活");
                    st.wb(R.id.wb11).zf("一些功能将无法使用");
                    st.wb(R.id.wb11).kjd(0);
                    st.tx(R.id.tx4).tx(R.mipmap.a29);

                    String color = zf.zf(R.color.colorTextFalse);
                    st.kp(R.id.kp4).kpbjys(color);
                    st.xdbj(R.id.xdbj4).kjd(0);
                    isJh = false;

                }

            }

        });

    }

    public void load(Object url, Object title) {

        String[] name = new String[]{"url", "title", "code"};

        Object[] value = new Object[]{url, title, "super1"};
        gj.tz(TextViewerActivity.class, name, value);

    }

    public void qh() {
        gj.jmxc(new Runnable() {

            public void run() {

                String set2 = AppPaths.appPath("settings/f7/Enable2");

                if (st.xxbj(R.id.xxbj1).kjd() == 0) {

                    String z = "";
                    st.xxbj(R.id.xxbj1).kjd(8);
                    st.xxbj(R.id.xxbj4).kjd(0);

                    if (mode == 1) {

                        z = st.wb(R.id.wb5).zf();
                        st.wb(R.id.title_bar).zf("产品ID");
                        st.bjk(R.id.bjk1).tszf("请输入15位产品ID");
                        st.bjk(R.id.bjk1).wblx("number");
                        st.bjk(R.id.bjk1).xzxscd(15);

                    }
                    st.bjk(R.id.bjk1).zf(wj.dqwb(set2));

                } else {

                    st.xxbj(R.id.xxbj1).kjd(0);
                    st.xxbj(R.id.xxbj4).kjd(8);
                    st.bjk(R.id.bjk1).zf("");
                    st.wb(R.id.title_bar).zf("< 应用激活");

                }

            }

        });

    }

    public void qh_() {
        gj.jmxc(new Runnable() {

            public void run() {

                st.xxbj(R.id.xxbj1).kjd(0);
                st.xxbj(R.id.xxbj4).kjd(8);
                st.bjk(R.id.bjk1).zf("");
                st.wb(R.id.title_bar).zf("< 应用激活");

            }

        });

    }

    public void gx() {
        gj.xc(new Thread() {

            public void run() {

                show(1);
                gj.zt(500);

                String set2 = AppPaths.appPath("settings/f7/Enable2");

                String b = st.bjk(R.id.bjk1).zf();
                b = zf.qctwkg(b);

                if (mode == 1) {

                    if (b.length() != 15) {

                        e1.tsk("提示", "产品ID不符合规范");
                        show(0);

                    } else {

                        if (e1.yz()) {

                            String urls = AppPaths.appPath("settings/f10/set2.inf");
                            urls = wj.dqwb(urls);
                            urls = e1.urlUnlockString(urls);

                            if (zf.cjw(urls, "/")) {

                            } else {

                                urls = urls + "/";

                            }

                            String url = urls + "open.php?token=" + b;

                            String back = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                            if (zf.dy(back, "") || zf.dy(back, null)) {

                                e1.tsk("提示", "验证产品ID失败:连接服务器超时(-1)");
                                show(0);

                            } else {

                                if (zf.dy(back, "true")) {

                                    wj.xrwb(set2, b);
                                    show(0);
                                    jz();

                                } else {

                                    e1.tsk("提示", "验证产品ID失败:该产品ID不存在");
                                    show(0);

                                }

                            }

                        } else {

                            e1.tsk("提示", "验证产品ID失败:连接服务器超时(-2)");
                            show(0);

                        }

                    }

                }

            }

        });

    }

    public void ksjh() {
        gj.xc(new Thread() {

            public void run() {

                show(1);
                gj.zt(500);

                String urls = AppPaths.appPath("settings/f10/set2.inf");
                urls = wj.dqwb(urls);
                urls = e1.urlUnlockString(urls);

                if (zf.cjw(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                String url = urls + "vip.json";

                if (e1.yz()) {

                    String back = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                    if (zf.dy(back, "") || zf.dy(back, null)) {

                        e1.tsk("提示", "连接服务器超时");
                        show(0);

                    } else {

                        try {

                            String oldenable = e1.readJson(back, "OldEnable");

                            String money = e1.readJson(back, "Money");

                            double money_ = sj.zh(money).zdouble(((double) (0)));

                            String h_y = e1.readJson(back, "Act_Y");

                            int h_y_ = sj.zh(h_y).zint(0);

                            String h_m = e1.readJson(back, "Act_M");

                            int h_m_ = sj.zh(h_m).zint(0);

                            String h_d = e1.readJson(back, "Act_D");

                            int h_d_ = sj.zh(h_d).zint(0);

                            String a3 = 日期工具.格式化日期时间(应用工具.获取应用第一次安装日期(lei, "open.cn.awg.pro"));

                            String y = zf.qc(a3, 0, 4);

                            String m = zf.qc(a3, 5, 7);

                            String d = zf.qc(a3, 8, 10);

                            int y_ = sj.zh(y).zint(9999);

                            int m_ = sj.zh(m).zint(99);

                            int d_ = sj.zh(d).zint(99);

                            final String[] name = new String[]{"id", "name", "jg", "text"};

                            final Object[] value = new Object[]{"a1", "腕管Pro-产品ID", money_, "用以激活腕管Pro的产品ID，一经售出，概不退换！"};

                            if (zf.dy(oldenable, "true")) {

                                if (y_ <= h_y_ && m_ <= h_m_ && d_ <= h_d_) {

                                    e1.jh();
                                    e1.tsk("提示", "检测到您为老用户,已为您激活应用");
                                    gj.zt(1000);
                                    show(0);

                                } else {

                                    gj.jmxc(new Runnable() {

                                        public void run() {

                                            gj.tz(ProductPurchaseActivity.class, name, value);

                                        }

                                    });
                                    gj.zt(1000);
                                    show(0);

                                }

                            } else {

                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        gj.tz(ProductPurchaseActivity.class, name, value);

                                    }

                                });
                                gj.zt(1000);
                                show(0);

                            }

                        } catch (Throwable e) {

                            e1.upload_error(e, "f7.ksjh()");
                            show(0);

                        }

                    }

                } else {

                    e1.tsk("提示", "应用验证未通过");
                    show(0);

                }

            }

        });

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

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
            applyWindowModeFromSettings();
        jz();

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onTitleBarClick(View vw) {

        if (zf.dy(st.wb(R.id.title_bar).zf(), "< 应用激活")) {

            gj.gb();

        }

    }

    private void onXxbj8Click(View vw) {

        if (!isJh) {

            mode = 1;
            qh();

        } else {

            if (st.xxbj(R.id.xxbj9).kjd() == 0) {

                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.title_bar).zf("< 应用激活");
                        st.xxbj(R.id.xxbj9).kjd(8);
                        st.xxbj(R.id.xxbj1).kjd(0);

                    }

                });

            } else {

                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.title_bar).zf("提示");
                        st.xxbj(R.id.xxbj9).kjd(0);
                        st.xxbj(R.id.xxbj1).kjd(8);

                    }

                });

            }

        }

    }

    private void onXdbj3Click(View vw) {

        if (zf.dy(st.wb(R.id.wb5).zf(), "请输入15位产品ID")) {

            mode = 1;
            qh();

        } else {

            e1.tsk("产品ID", st.wb(R.id.wb5).zf());

        }

    }

    private boolean onXdbj3LongClick(View vw) {

        String te = st.wb(R.id.wb4).zf();

        String lj = st.wb(R.id.wb5).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj2Click(View vw) {
        load(AppPaths.appPath("data/assets/e3"), "功能帮助");

    }

    private boolean onXdbj2LongClick(View vw) {

        String te = st.wb(R.id.wb2).zf();

        String lj = st.wb(R.id.wb3).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj4Click(View vw) {
        ksjh();

    }

    private boolean onXdbj4LongClick(View vw) {

        String te = st.wb(R.id.wb7).zf();

        String lj = st.wb(R.id.wb8).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx1Click(View vw) {
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

    private void onTx2Click(View vw) {
        qh();

    }

    private void onTx3Click(View vw) {
        gx();

    }

    private void onTx5Click(View vw) {

        if (st.xxbj(R.id.xxbj9).kjd() == 0) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf("< 应用激活");
                    st.xxbj(R.id.xxbj9).kjd(8);
                    st.xxbj(R.id.xxbj1).kjd(0);

                }

            });

        } else {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf("提示");
                    st.xxbj(R.id.xxbj9).kjd(0);
                    st.xxbj(R.id.xxbj1).kjd(8);

                }

            });

        }

    }

    private void onTx6Click(View vw) {

        String a = AppPaths.appPath("settings/f7/Enable2");
        wj.xrwb(a, "");
        jz();

        if (st.xxbj(R.id.xxbj9).kjd() == 0) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf("< 应用激活");
                    st.xxbj(R.id.xxbj9).kjd(8);
                    st.xxbj(R.id.xxbj1).kjd(0);

                }

            });

        } else {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf("提示");
                    st.xxbj(R.id.xxbj9).kjd(0);
                    st.xxbj(R.id.xxbj1).kjd(8);

                }

            });

        }

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        ImageView i11aeec1890 = (ImageView) findViewById(ay, vw, R.id.tx4);

        LinearLayout i3f6c9f386b = (LinearLayout) findViewById(ay, vw, R.id.xxbj8);
        i3f6c9f386b.setOnClickListener(xxbj8ClickListener);

        RelativeLayout i20c2777fab = (RelativeLayout) findViewById(ay, vw, R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);
        i20c2777fab.setOnLongClickListener(xdbj3LongClickListener);

        RelativeLayout i3167b45174 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);
        i3167b45174.setOnLongClickListener(xdbj2LongClickListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) findViewById(ay, vw, R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);
        i2202ede8ff.setOnLongClickListener(xdbj4LongClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        EditText i32ee139ba1 = (EditText) findViewById(ay, vw, R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i4a4248a0f2 = (ImageView) findViewById(ay, vw, R.id.tx5);
        i4a4248a0f2.setOnClickListener(tx5ClickListener);

        ImageView i6f2c7751cf = (ImageView) findViewById(ay, vw, R.id.tx6);
        i6f2c7751cf.setOnClickListener(tx6ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
