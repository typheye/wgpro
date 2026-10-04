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
    public AppRuntimeBridge e1;
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
        e1 = new AppRuntimeBridge(this);
        setContentView(R.layout.product_purchase);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        cp_name = ProductPurchaseActivity.this.getIntent().getStringExtra(String.valueOf("name"));
        cp_jg = Double.parseDouble(ProductPurchaseActivity.this.getIntent().getStringExtra(String.valueOf("jg")));
        cp_text = ProductPurchaseActivity.this.getIntent().getStringExtra(String.valueOf("text"));
        cp_id = ProductPurchaseActivity.this.getIntent().getStringExtra(String.valueOf("id"));
        zt(0);
        payconf();
        show(0);

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ProductPurchaseActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ProductPurchaseActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(ProductPurchaseActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(ProductPurchaseActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));

        if (java.util.Objects.equals(a, "4")) {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.product_purchase_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(true));

            String clr = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTab);
            setStatusBarColor(clr);

        } else {

            ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.product_purchase_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

        }

        if (java.util.Objects.equals(a, "1")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xdbj1)), u, 0, u, 50);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.title_bar));
            open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "15dp", 0, "3dp");
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "2")) {

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (open.cn.awg.pro.core.NativeFiles.exists(ProductPurchaseActivity.this, (String) (a001)) || java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ProductPurchaseActivity.this, (String) (a001)), "true")) {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.title_bar));
                open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "3dp", 0, "3dp");
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            } else {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.title_bar));
                open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "9dp", 0, "9dp");
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            }
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xdbj1)), 0, 0, 0, 0);

        } else if (java.util.Objects.equals(a, "3")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xdbj1)), u, 0, u, 50);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.title_bar));
            open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "15dp", 0, "3dp");
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "4")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xdbj1)), 0, 0, 0, 0);

        }

    }

    public void show(int i) {

        if (i == 0) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeDevice.setKeepScreenOn(ProductPurchaseActivity.this, !(true));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("< 产品购买"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj5)).setVisibility(android.view.View.GONE);

                }

            });

        } else if (i == 1) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeDevice.setKeepScreenOn(ProductPurchaseActivity.this, !(true));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("产品购买"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj5)).setVisibility(android.view.View.GONE);

                }

            });

        } else if (i == 2) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeDevice.setKeepScreenOn(ProductPurchaseActivity.this, !(false));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("产品购买"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj5)).setVisibility(android.view.View.VISIBLE);

                }

            });

        }

    }

    public void payconf() {

        final android.widget.RelativeLayout xdbj7 = ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xdbj7));

        final android.widget.RelativeLayout xdbj8 = ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xdbj8));

        final android.widget.TextView wb18 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb18));

        if (!st_payconf) {

            st_payconf = true;
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            xdbj7.setVisibility(android.view.View.GONE);
                            xdbj8.setVisibility(android.view.View.GONE);
                            wb18.setVisibility(android.view.View.VISIBLE);

                        }

                    });

                    String set2 = AppPaths.appPath("settings/f7/Enable");

                    if (e1.yz()) {

                        if (java.util.Objects.equals(cp_name, "") || java.util.Objects.equals(cp_text, "")) {

                            payconf$error("");

                        } else if (cp_jg == 0) {

                            open.cn.awg.pro.core.NativeActions.sleep(1500);
                            show(0);
                            zt(1);
                            e1.zfwc(cp_id);

                        } else {

                            String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url2 + "/app/open.cn.awg.pro/pay_config_v3.json"), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                            if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                                payconf$error("");

                            } else {

                                try {

                                    String can_pay = e1.readJson(back, "can_pay");

                                    String nopay_why = e1.readJson(back, "nopay_why");

                                    if (java.util.Objects.equals(can_pay, "1")) {

                                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                            public void run() {

                                                xdbj8.setVisibility(android.view.View.GONE);
                                                xdbj7.setVisibility(android.view.View.VISIBLE);
                                                wb18.setVisibility(android.view.View.GONE);

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

        final android.widget.TextView wb18 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb18));

        final android.widget.RelativeLayout xdbj8 = ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xdbj8));

        if (java.util.Objects.equals(t, "")) {

            nopay_why2 = "获取支付方式失败\n网络异常，请稍后重试";

        } else {

            nopay_why2 = String.valueOf(t);

        }
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                wb18.setVisibility(android.view.View.GONE);
                xdbj8.setVisibility(android.view.View.VISIBLE);

            }

        });

    }

    public void sendPayInfo(Object a, Object b, Object c, Object d) {

        final Object j1 = a;

        final Object j2 = b;

        final Object j3 = c;

        final Object j4 = d;
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                String urls = "";

                final String set2 = AppPaths.appPath("settings/f11/set2.inf");
                urls = e1.urlUnlockString(open.cn.awg.pro.core.NativeFiles.readText(ProductPurchaseActivity.this, (String) (set2)));

                if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                String ljf = open.cn.awg.pro.core.NativeFiles.readText(ProductPurchaseActivity.this, (String) (AppPaths.appPath("data/assets/a17")));

                String idget = urls + "class/api.php?type=release" + ljf + "id=600";

                if (java.util.Objects.equals(j1, "") || java.util.Objects.equals(j2, "") || java.util.Objects.equals(j3, "") || java.util.Objects.equals(j4, "")) {

                } else {

                    String namex0 = "用户交易";

                    final String namex = namex0;

                    String xxk = "[产品标识] " + j1 + "\n[产品价格] " + j2 + "\n[交易方式] " + j3 + "\n[交易标识] " + j4;

                    final String xx = xxk;

                    final String url = idget;

                    String setback = "";

                    String[] wlhq1 = new String[]{"name=" + namex, "value=" + xx, "code=baseTextUpload"};

                    String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (wlhq1), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                    if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                        setback = "0";

                    } else {

                        if (java.util.Objects.equals(setback, "0")) {

                            setback = "0";

                        } else {

                            setback = back;

                        }

                    }

                    if (java.util.Objects.equals(setback, "0")) {

                    } else {

                        if (java.util.Objects.equals(setback, "true")) {

                        } else {

                        }

                    }

                }

            }

        });

    }

    public void zt(int a) {

        final int i = a;
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                if (i == 0) {

                    String cp_jg_;
                    cp_jg_ = com.demo.e4.doubleToString(cp_jg);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb10)).setText(String.valueOf("¥" + cp_jg_));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb11)).setText(String.valueOf("待支付"));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb11)).setVisibility(android.view.View.VISIBLE);
                    open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.tx4)), R.mipmap.a27);

                    String color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorAccent);
                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.kp4)).setCardBackgroundColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xdbj6)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj7)).setVisibility(android.view.View.VISIBLE);

                } else if (i == 1) {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb10)).setText(String.valueOf("支付成功"));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb11)).setText(String.valueOf(""));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb11)).setVisibility(android.view.View.GONE);
                    open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.tx4)), R.mipmap.a28);

                    String color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTextTrue);
                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.kp4)).setCardBackgroundColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xdbj6)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj7)).setVisibility(android.view.View.GONE);

                } else if (i == 2) {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb10)).setText(String.valueOf("支付失败"));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb11)).setText(String.valueOf(""));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb11)).setVisibility(android.view.View.GONE);
                    open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.tx4)), R.mipmap.a29);

                    String color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTextFalse);
                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.kp4)).setCardBackgroundColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xdbj6)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj7)).setVisibility(android.view.View.GONE);

                }

            }

        });

    }

    public void zf0() {

        if (!st_zf0) {

            st_zf0 = true;

            final String type = "default";
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    show(1);
                    open.cn.awg.pro.core.NativeActions.sleep(500);

                    String set2 = AppPaths.appPath("settings/f7/Enable");

                    if (e1.yz()) {

                        if (java.util.Objects.equals(cp_name, "") || cp_jg == 0 || java.util.Objects.equals(cp_text, "")) {

                            oback = "支付失败:参数错误";
                            e1.tsk("提示", oback);
                            show(0);
                            zt(2);

                        } else {

                            String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url2 + "/app/open.cn.awg.pro/pay_config_v3.json"), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                            if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                                oback = "支付失败:连接服务器超时";
                                e1.tsk("提示", oback);
                                show(0);
                                zt(2);

                            } else {

                                try {

                                    String pid = "awg";

                                    String ljf = open.cn.awg.pro.core.NativeFiles.readText(ProductPurchaseActivity.this, (String) (AppPaths.appPath("data/assets/a17")));

                                    String cp_jg_;
                                    cp_jg_ = com.demo.e4.doubleToString(cp_jg);

                                    final String otn = "D" + open.cn.awg.pro.core.NativeDevice.formatDate(4) + open.cn.awg.pro.core.TextValues.string(open.cn.awg.pro.core.TextValues.randomInclusive(10000000, 99999999));

                                    String lock = "money=" + cp_jg_ + "&name=" + cp_name + "&id=" + cp_id + "&out_trade_no=" + otn + "&pid=" + pid + "&type=" + type;

                                    String get = "/api/v3/?" + lock + "&sign=" + open.cn.awg.pro.core.TextValues.md5(lock);
                                    back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url + get), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                                    if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                                        oback = "支付失败:连接服务器超时";
                                        e1.tsk("提示", oback);
                                        show(0);
                                        zt(2);

                                    } else {

                                        String ce = e1.readJson(back, "code");

                                        if (java.util.Objects.equals(ce, "200")) {

                                            try {

                                                org.json.JSONObject jo = (org.json.JSONObject) (Object) open.cn.awg.pro.core.JsonValues.parseObject(back);

                                                JSONObject json = jo;

                                                Object v_id = open.cn.awg.pro.core.JsonValues.value(json, "trade_no");

                                                Object v_v = open.cn.awg.pro.core.JsonValues.value(json, "qrcode");

                                                if (!java.util.Objects.equals(v_id, "") && !java.util.Objects.equals(v_v, "")) {

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
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    zfxh = true;
                    zfqx = false;
                    show(2);
                    zt(0);
                    ewm(String.valueOf(v_v));
                    open.cn.awg.pro.core.NativeFiles.writeText(ProductPurchaseActivity.this, (String) (pay_cache), (String) (v_id));

                    while (zfxh) {

                        open.cn.awg.pro.core.NativeActions.sleep(3000);

                        if (zfqx) {

                            zfxh = false;
                            oback = "支付失败:用户取消支付";
                            e1.tsk("提示", oback);
                            show(0);
                            zt(2);
                            break;

                        }

                        String ljf = open.cn.awg.pro.core.NativeFiles.readText(ProductPurchaseActivity.this, (String) (AppPaths.appPath("data/assets/a17")));

                        String id = open.cn.awg.pro.core.NativeFiles.readText(ProductPurchaseActivity.this, (String) (pay_cache));

                        String send = "/api/v3/reback/?trade_no=" + id;

                        String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url + send), null, (String) ("utf-8"), null, false, null, 0, 0, null).bodyForHttp200();

                        if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                            zfxh = false;
                            oback = "支付失败:连接服务器超时";
                            e1.tsk("提示", oback);
                            show(0);
                            zt(2);
                            break;

                        } else {

                            String ce = e1.readJson(back, "code");

                            if (java.util.Objects.equals(ce, "200")) {

                                try {

                                    org.json.JSONObject jo = (org.json.JSONObject) (Object) open.cn.awg.pro.core.JsonValues.parseObject(back);

                                    JSONObject json = jo;

                                    final String v_status = open.cn.awg.pro.core.TextValues.string(open.cn.awg.pro.core.JsonValues.value(json, "status"));

                                    if (java.util.Objects.equals(v_status, "1")) {

                                        show(0);
                                        zt(1);
                                        sendPayInfo(cp_id, cp_jg, type, v_id);
                                        zfxh = false;
                                        e1.zfwc(cp_id);
                                        break;

                                    } else if (java.util.Objects.equals(v_status, "-1")) {

                                        zfxh = false;
                                        oback = "支付失败:超时支付";
                                        e1.tsk("提示", oback);
                                        show(0);
                                        zt(2);

                                    } else if (java.util.Objects.equals(v_status, "-2")) {

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
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                try {

                    ImageView imageView = findViewById(R.id.tx3);
                    Bitmap bitmap = com.demo.e4.createQRCodeBitmap(qr, 800, 800, "UTF-8", "H", "1");
                    imageView.setImageBitmap(bitmap);

                    ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.tx3)).setVisibility(android.view.View.VISIBLE);

                } catch (Throwable e) {

                    ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.tx3)).setVisibility(android.view.View.GONE);

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
            applyWindowModeFromSettings();

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4 && ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj1)).getVisibility() == android.view.View.VISIBLE) {

            if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj9)).getVisibility() == android.view.View.VISIBLE) {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("< 产品购买"));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.GONE);
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);

                    }

                });

            } else {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("提示"));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);

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

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "< 产品购买") && ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj1)).getVisibility() == android.view.View.VISIBLE) {

            if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj9)).getVisibility() == android.view.View.VISIBLE) {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("< 产品购买"));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.GONE);
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);

                    }

                });

            } else {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("提示"));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);

                    }

                });

            }

        }

    }

    private void onXxbj8Click(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb10)).getText().toString(), "支付成功")) {

            e1.tsk("提示", "您已成功完成支付");

        } else if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb10)).getText().toString(), "支付失败")) {

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

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb4)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb5)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj6Click(View vw) {
        payconf();
        zt(0);

    }

    private boolean onXdbj6LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb15)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb16)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj7Click(View vw) {
        zf0();

    }

    private boolean onXdbj7LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb19)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb20)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj8Click(View vw) {
        e1.tsk("产品购买", nopay_why2);
        open.cn.awg.pro.core.NativeActions.finish(ProductPurchaseActivity.this);

    }

    private boolean onXdbj8LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb21)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.wb22)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(ProductPurchaseActivity.this);

    }

    private void onAn1Click(View vw) {
        zfqx = true;
        show(1);

    }

    private void onTx2Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(ProductPurchaseActivity.this);

    }

    private void onTx5Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj9)).getVisibility() == android.view.View.VISIBLE) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("< 产品购买"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);

                }

            });

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("提示"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);

                }

            });

        }

    }

    private void onTx6Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj9)).getVisibility() == android.view.View.VISIBLE) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("< 产品购买"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);

                }

            });

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("提示"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ProductPurchaseActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);

                }

            });

        }
        open.cn.awg.pro.core.NativeActions.finish(ProductPurchaseActivity.this);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        ImageView i11aeec1890 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx4);

        LinearLayout i3f6c9f386b = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj8);
        i3f6c9f386b.setOnClickListener(xxbj8ClickListener);

        RelativeLayout i20c2777fab = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);
        i20c2777fab.setOnLongClickListener(xdbj3LongClickListener);

        RelativeLayout i171cedf1eb = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj6);
        i171cedf1eb.setOnClickListener(xdbj6ClickListener);
        i171cedf1eb.setOnLongClickListener(xdbj6LongClickListener);

        RelativeLayout e585d94488 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj7);
        e585d94488.setOnClickListener(xdbj7ClickListener);
        e585d94488.setOnLongClickListener(xdbj7LongClickListener);

        RelativeLayout fab146d7a5 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj8);
        fab146d7a5.setOnClickListener(xdbj8ClickListener);
        fab146d7a5.setOnLongClickListener(xdbj8LongClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        Button i171ea34f1a = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i4a4248a0f2 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx5);
        i4a4248a0f2.setOnClickListener(tx5ClickListener);

        ImageView i6f2c7751cf = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx6);
        i6f2c7751cf.setOnClickListener(tx6ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
