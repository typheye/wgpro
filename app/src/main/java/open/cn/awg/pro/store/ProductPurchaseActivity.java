/**
 ******************************************************************************
 * @file    ProductPurchaseActivity.java
 * @author  Typheye
 * @brief   Product purchase screen.
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
package open.cn.awg.pro.store;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import org.json.JSONObject;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class ProductPurchaseActivity extends BaseAwgActivity {

    public final ProductPurchaseActivity lei = this, 类 = this;
    public double cp_jg = 0;
    public String cp_name = "";
    public String cp_text = "";
    public String cp_time = "";
    public String cp_id = "";
    public String oback = "";
    public String nopay_why2 = "";
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public boolean zfxh = true;
    public boolean zfqx = false;
    public boolean st_zf0 = false;
    public boolean st_payconf = false;
    public boolean st_zf0_xh = false;
    public String pay_cache = "$cache/pay/pay_id";
    public String url = "https://pay.typheye.cn";
    public String url2 = "https://service.typheye.cn";
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
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };
    private final View.OnClickListener an1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn1Click(vw);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
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
        setContentView(R.layout.product_purchase);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        cp_name = sj.hqtz("name");
        cp_jg = Double.parseDouble(sj.hqtz("jg"));
        cp_text = sj.hqtz("text");
        cp_id = sj.hqtz("id");
        zt(0);
        payconf();
        show(0);

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

            st.xdbj(R.id.product_purchase_root).shxtck(true);

            String clr = zf.zf(R.color.colorTab);
            setStatusBarColor(clr);

        } else {

            st.xdbj(R.id.product_purchase_root).shxtck(false);
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

    public void show(int i) {

        if (i == 0) {

            gj.jmxc(new Runnable() {

                public void run() {

                    xt.sbxm(true);
                    st.wb(R.id.title_bar).zf("< 产品购买");
                    st.xxbj(R.id.xxbj1).kjd(0);
                    st.xxbj(R.id.xxbj9).kjd(8);
                    st.xxbj(R.id.xxbj6).kjd(8);
                    st.xxbj(R.id.xxbj5).kjd(8);

                }

            });

        } else if (i == 1) {

            gj.jmxc(new Runnable() {

                public void run() {

                    xt.sbxm(true);
                    st.wb(R.id.title_bar).zf("产品购买");
                    st.xxbj(R.id.xxbj1).kjd(8);
                    st.xxbj(R.id.xxbj9).kjd(8);
                    st.xxbj(R.id.xxbj6).kjd(0);
                    st.xxbj(R.id.xxbj5).kjd(8);

                }

            });

        } else if (i == 2) {

            gj.jmxc(new Runnable() {

                public void run() {

                    xt.sbxm(false);
                    st.wb(R.id.title_bar).zf("产品购买");
                    st.xxbj(R.id.xxbj1).kjd(8);
                    st.xxbj(R.id.xxbj9).kjd(8);
                    st.xxbj(R.id.xxbj6).kjd(8);
                    st.xxbj(R.id.xxbj5).kjd(0);

                }

            });

        }

    }

    public void payconf() {

        final i.runlibrary.app.v.xdbj xdbj7 = st.xdbj(R.id.xdbj7);

        final i.runlibrary.app.v.xdbj xdbj8 = st.xdbj(R.id.xdbj8);

        final i.runlibrary.app.v.wb wb18 = st.wb(R.id.wb18);

        if (!st_payconf) {

            st_payconf = true;
            gj.xc(new Thread() {

                public void run() {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            xdbj7.kjd(8);
                            xdbj8.kjd(8);
                            wb18.kjd(0);

                        }

                    });

                    String set2 = AppPaths.appPath("settings/f7/Enable");

                    if (e1.yz()) {

                        if (zf.dy(cp_name, "") || zf.dy(cp_text, "")) {

                            payconf$error("");

                        } else if (cp_jg == 0) {

                            gj.zt(1500);
                            show(0);
                            zt(1);
                            e1.zfwc(cp_id);

                        } else {

                            String back = wl.hq(url2 + "/app/open.cn.awg.pro/pay_config_v3.json", null, "utf-8", null, true, null, 20000, 20000, null);

                            if (zf.dy(back, "") || zf.dy(back, null)) {

                                payconf$error("");

                            } else {

                                try {

                                    String can_pay = e1.readJson(back, "can_pay");

                                    String nopay_why = e1.readJson(back, "nopay_why");

                                    if (zf.dy(can_pay, "1")) {

                                        gj.jmxc(new Runnable() {

                                            public void run() {

                                                xdbj8.kjd(8);
                                                xdbj7.kjd(0);
                                                wb18.kjd(8);

                                            }

                                        });
                                        st_payconf = false;

                                    } else {

                                        payconf$error(nopay_why);

                                    }

                                } catch (Throwable __$_e__) {

                                    payconf$error("");

                                }

                            }

                        }

                    } else {

                        payconf$error("");

                    }
                    st_payconf = false;

                }

            });

        } else {

            e1.tsk("提示", "操作过于频繁，请稍后重试");

        }

    }

    public void payconf$error(Object t) {
        st_payconf = false;

        final i.runlibrary.app.v.wb wb18 = st.wb(R.id.wb18);

        final i.runlibrary.app.v.xdbj xdbj8 = st.xdbj(R.id.xdbj8);

        if (zf.dy(t, "")) {

            nopay_why2 = "获取支付方式失败\n网络异常，请稍后重试";

        } else {

            nopay_why2 = String.valueOf(t);

        }
        gj.jmxc(new Runnable() {

            public void run() {

                wb18.kjd(8);
                xdbj8.kjd(0);

            }

        });

    }

    public void sendPayInfo(Object a, Object b, Object c, Object d) {

        final Object j1 = a;

        final Object j2 = b;

        final Object j3 = c;

        final Object j4 = d;
        gj.xc(new Thread() {

            public void run() {

                String urls = "";

                final String set2 = AppPaths.appPath("settings/f11/set2.inf");
                urls = e1.urlUnlockString(wj.dqwb(set2));

                if (zf.cjw(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                String ljf = wj.dqwb(AppPaths.appPath("data/assets/a17"));

                String idget = urls + "class/api.php?type=release" + ljf + "id=600";

                if (zf.dy(j1, "") || zf.dy(j2, "") || zf.dy(j3, "") || zf.dy(j4, "")) {

                } else {

                    String namex0 = "用户交易";

                    final String namex = namex0;

                    String xxk = "[产品标识] " + j1 + "\n[产品价格] " + j2 + "\n[交易方式] " + j3 + "\n[交易标识] " + j4;

                    final String xx = xxk;

                    final String url = idget;

                    String setback = "";

                    String[] wlhq1 = new String[]{"name=" + namex, "value=" + xx, "code=baseTextUpload"};

                    String back = wl.hq(url, wlhq1, "utf-8", null, true, null, 20000, 20000, null);

                    if (zf.dy(back, "") || zf.dy(back, null)) {

                        setback = "0";

                    } else {

                        if (zf.dy(setback, "0")) {

                            setback = "0";

                        } else {

                            setback = back;

                        }

                    }

                    if (zf.dy(setback, "0")) {

                    } else {

                        if (zf.dy(setback, "true")) {

                        } else {

                        }

                    }

                }

            }

        });

    }

    public void zt(int a) {

        final int i = a;
        gj.jmxc(new Runnable() {

            public void run() {

                if (i == 0) {

                    String cp_jg_;
                    cp_jg_ = com.demo.e4.doubleToString(cp_jg);
                    st.wb(R.id.wb10).zf("¥" + cp_jg_);
                    st.wb(R.id.wb11).zf("待支付");
                    st.wb(R.id.wb11).kjd(0);
                    st.tx(R.id.tx4).tx(R.mipmap.a27);

                    String color = zf.zf(R.color.colorAccent);
                    st.kp(R.id.kp4).kpbjys(color);
                    st.xdbj(R.id.xdbj6).kjd(8);
                    st.xxbj(R.id.xxbj7).kjd(0);

                } else if (i == 1) {

                    st.wb(R.id.wb10).zf("支付成功");
                    st.wb(R.id.wb11).zf("");
                    st.wb(R.id.wb11).kjd(8);
                    st.tx(R.id.tx4).tx(R.mipmap.a28);

                    String color = zf.zf(R.color.colorTextTrue);
                    st.kp(R.id.kp4).kpbjys(color);
                    st.xdbj(R.id.xdbj6).kjd(8);
                    st.xxbj(R.id.xxbj7).kjd(8);

                } else if (i == 2) {

                    st.wb(R.id.wb10).zf("支付失败");
                    st.wb(R.id.wb11).zf("");
                    st.wb(R.id.wb11).kjd(8);
                    st.tx(R.id.tx4).tx(R.mipmap.a29);

                    String color = zf.zf(R.color.colorTextFalse);
                    st.kp(R.id.kp4).kpbjys(color);
                    st.xdbj(R.id.xdbj6).kjd(0);
                    st.xxbj(R.id.xxbj7).kjd(8);

                }

            }

        });

    }

    public void zf0() {

        if (!st_zf0) {

            st_zf0 = true;

            final String type = "default";
            gj.xc(new Thread() {

                public void run() {

                    show(1);
                    gj.zt(500);

                    String set2 = AppPaths.appPath("settings/f7/Enable");

                    if (e1.yz()) {

                        if (zf.dy(cp_name, "") || cp_jg == 0 || zf.dy(cp_text, "")) {

                            oback = "支付失败:参数错误";
                            e1.tsk("提示", oback);
                            show(0);
                            zt(2);

                        } else {

                            String back = wl.hq(url2 + "/app/open.cn.awg.pro/pay_config_v3.json", null, "utf-8", null, true, null, 20000, 20000, null);

                            if (zf.dy(back, "") || zf.dy(back, null)) {

                                oback = "支付失败:连接服务器超时";
                                e1.tsk("提示", oback);
                                show(0);
                                zt(2);

                            } else {

                                try {

                                    String pid = "awg";

                                    String ljf = wj.dqwb(AppPaths.appPath("data/assets/a17"));

                                    String cp_jg_;
                                    cp_jg_ = com.demo.e4.doubleToString(cp_jg);

                                    final String otn = "D" + xt.sj(4) + sj.zh().zstring(zf.sjs(10000000, 99999999));

                                    String lock = "money=" + cp_jg_ + "&name=" + cp_name + "&id=" + cp_id + "&out_trade_no=" + otn + "&pid=" + pid + "&type=" + type;

                                    String get = "/api/v3/?" + lock + "&sign=" + sj.md5(lock);
                                    back = wl.hq(url + get, null, "utf-8", null, true, null, 20000, 20000, null);

                                    if (zf.dy(back, "") || zf.dy(back, null)) {

                                        oback = "支付失败:连接服务器超时";
                                        e1.tsk("提示", oback);
                                        show(0);
                                        zt(2);

                                    } else {

                                        String ce = e1.readJson(back, "code");

                                        if (zf.dy(ce, "200")) {

                                            try {

                                                i.runlibrary.app.zf$json jo = zf.json(back);

                                                JSONObject json = jo.json;

                                                Object v_id = jo.hq(json, "trade_no");

                                                Object v_v = jo.hq(json, "qrcode");

                                                if (!zf.dy(v_id, "") && !zf.dy(v_v, "")) {

                                                    zf0_xh(v_v, v_id, type);

                                                } else {

                                                    oback = "支付失败:出现异常";
                                                    e1.tsk("提示", oback);
                                                    show(0);
                                                    zt(2);

                                                }

                                            } catch (Throwable e) {

                                                oback = "支付失败:出现异常";
                                                e1.tsk("提示", oback);
                                                show(0);
                                                zt(2);

                                            }

                                        } else {

                                            oback = "支付失败";
                                            e1.tsk("提示", oback);
                                            show(0);
                                            zt(2);

                                        }

                                    }

                                } catch (Throwable __$_e__) {

                                    oback = "支付失败:出现异常";
                                    e1.tsk("提示", oback);
                                    show(0);
                                    zt(2);

                                }

                            }

                        }

                    } else {

                        oback = "支付失败:支付环境检测结果异常(-1)";
                        e1.tsk("提示", oback);
                        show(0);
                        zt(2);

                    }
                    st_zf0 = false;

                }

            });

        } else {

            e1.tsk("提示", "操作过于频繁，请稍后重试");

        }

    }

    public void zf0_xh(Object v_v_, Object v_id_, Object type_) {

        if (!st_zf0_xh) {

            st_zf0_xh = true;

            final Object v_v = v_v_;

            final Object v_id = v_id_;

            final Object type = type_;
            gj.xc(new Thread() {

                public void run() {

                    zfxh = true;
                    zfqx = false;
                    show(2);
                    zt(0);
                    ewm(String.valueOf(v_v));
                    wj.xrwb(pay_cache, v_id);

                    while (zfxh) {

                        gj.zt(3000);

                        if (zfqx) {

                            zfxh = false;
                            oback = "支付失败:用户取消支付";
                            e1.tsk("提示", oback);
                            show(0);
                            zt(2);
                            break;

                        }

                        String ljf = wj.dqwb(AppPaths.appPath("data/assets/a17"));

                        String id = wj.dqwb(pay_cache);

                        String send = "/api/v3/reback/?trade_no=" + id;

                        String back = wl.hq(url + send, "utf-8");

                        if (zf.dy(back, "") || zf.dy(back, null)) {

                            zfxh = false;
                            oback = "支付失败:连接服务器超时";
                            e1.tsk("提示", oback);
                            show(0);
                            zt(2);
                            break;

                        } else {

                            String ce = e1.readJson(back, "code");

                            if (zf.dy(ce, "200")) {

                                try {

                                    i.runlibrary.app.zf$json jo = zf.json(back);

                                    JSONObject json = jo.json;

                                    final String v_status = sj.zh().zstring(jo.hq(json, "status"));

                                    if (zf.dy(v_status, "1")) {

                                        show(0);
                                        zt(1);
                                        sendPayInfo(cp_id, cp_jg, type, v_id);
                                        zfxh = false;
                                        e1.zfwc(cp_id);
                                        break;

                                    } else if (zf.dy(v_status, "-1")) {

                                        zfxh = false;
                                        oback = "支付失败:超时支付";
                                        e1.tsk("提示", oback);
                                        show(0);
                                        zt(2);

                                    } else if (zf.dy(v_status, "-2")) {

                                        zfxh = false;
                                        oback = "支付失败:订单已关闭";
                                        e1.tsk("提示", oback);
                                        show(0);
                                        zt(2);
                                        break;

                                    }

                                } catch (Throwable e) {

                                    zfxh = false;
                                    oback = "支付失败:出现异常";
                                    e1.tsk("提示", oback);
                                    show(0);
                                    zt(2);
                                    break;

                                }

                            } else {

                            }

                        }

                    }
                    st_zf0_xh = false;

                }

            });

        } else {

            zfxh = false;
            oback = "支付失败:操作过于频繁，请稍后重试";
            e1.tsk("提示", oback);
            show(0);
            zt(2);

        }

    }

    public void ewm(String url) {

        final String qr = url;
        gj.jmxc(new Runnable() {

            public void run() {

                try {

                    ImageView imageView = findViewById(R.id.tx3);
                    Bitmap bitmap = com.demo.e4.createQRCodeBitmap(qr, 800, 800, "UTF-8", "H", "1");
                    imageView.setImageBitmap(bitmap);

                    st.tx(R.id.tx3).kjd(0);

                } catch (Throwable e) {

                    st.tx(R.id.tx3).kjd(8);

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

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4 && st.xxbj(R.id.xxbj1).kjd() == 0) {

            if (st.xxbj(R.id.xxbj9).kjd() == 0) {

                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.title_bar).zf("< 产品购买");
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
        return false;

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onTitleBarClick(View vw) {

        if (zf.dy(st.wb(R.id.title_bar).zf(), "< 产品购买") && st.xxbj(R.id.xxbj1).kjd() == 0) {

            if (st.xxbj(R.id.xxbj9).kjd() == 0) {

                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.title_bar).zf("< 产品购买");
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

    private void onXxbj8Click(View vw) {

        if (zf.dy(st.wb(R.id.wb10).zf(), "支付成功")) {

            e1.tsk("提示", "您已成功完成支付");

        } else if (zf.dy(st.wb(R.id.wb10).zf(), "支付失败")) {

            e1.tsk("提示", oback);

        } else {

            e1.tsk("提示", "产品待支付");

        }

    }

    private void onXdbj3Click(View vw) {

        String cp_jg_;
        cp_jg_ = com.demo.e4.doubleToString(cp_jg);

        String sho = "[产品名称]\n" + cp_name + "\n\n[产品价格]\n¥" + cp_jg_ + "\n\n[产品说明]\n" + cp_text;
        e1.tsk("产品详情", sho);

    }

    private boolean onXdbj3LongClick(View vw) {

        String te = st.wb(R.id.wb4).zf();

        String lj = st.wb(R.id.wb5).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj6Click(View vw) {
        payconf();
        zt(0);

    }

    private boolean onXdbj6LongClick(View vw) {

        String te = st.wb(R.id.wb15).zf();

        String lj = st.wb(R.id.wb16).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj7Click(View vw) {
        zf0();

    }

    private boolean onXdbj7LongClick(View vw) {

        String te = st.wb(R.id.wb19).zf();

        String lj = st.wb(R.id.wb20).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj8Click(View vw) {
        e1.tsk("产品购买", nopay_why2);
        gj.gb();

    }

    private boolean onXdbj8LongClick(View vw) {

        String te = st.wb(R.id.wb21).zf();

        String lj = st.wb(R.id.wb22).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx1Click(View vw) {
        gj.gb();

    }

    private void onAn1Click(View vw) {
        zfqx = true;
        show(1);

    }

    private void onTx2Click(View vw) {
        gj.gb();

    }

    private void onTx5Click(View vw) {

        if (st.xxbj(R.id.xxbj9).kjd() == 0) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf("< 产品购买");
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

        if (st.xxbj(R.id.xxbj9).kjd() == 0) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf("< 产品购买");
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
        gj.gb();

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

        RelativeLayout i171cedf1eb = (RelativeLayout) findViewById(ay, vw, R.id.xdbj6);
        i171cedf1eb.setOnClickListener(xdbj6ClickListener);
        i171cedf1eb.setOnLongClickListener(xdbj6LongClickListener);

        RelativeLayout e585d94488 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj7);
        e585d94488.setOnClickListener(xdbj7ClickListener);
        e585d94488.setOnLongClickListener(xdbj7LongClickListener);

        RelativeLayout fab146d7a5 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj8);
        fab146d7a5.setOnClickListener(xdbj8ClickListener);
        fab146d7a5.setOnLongClickListener(xdbj8LongClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        Button i171ea34f1a = (Button) findViewById(ay, vw, R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i4a4248a0f2 = (ImageView) findViewById(ay, vw, R.id.tx5);
        i4a4248a0f2.setOnClickListener(tx5ClickListener);

        ImageView i6f2c7751cf = (ImageView) findViewById(ay, vw, R.id.tx6);
        i6f2c7751cf.setOnClickListener(tx6ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
