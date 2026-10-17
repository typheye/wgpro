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
    public AppRuntimeBridge e1;
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
;
    private final View.OnClickListener xdbj4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj4Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj2Click(vw);
        }

    };
;
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
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String te1 = open.cn.awg.pro.core.NativeFiles.readText(AppActivationActivity.this, (String) (set1));

                if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(AppActivationActivity.this, (String) (set1)), "") || !open.cn.awg.pro.core.NativeFiles.exists(AppActivationActivity.this, (String) (set1))) {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.wb5)).setText(String.valueOf("请输入15位产品ID"));

                } else {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.wb5)).setText(String.valueOf(te1));

                }

            }

        });
        zh();

    }

    public void show(int i) {

        if (i == 0) {

            qh_();
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(android.view.View.GONE);

                }

            });

        } else if (i == 1) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(android.view.View.VISIBLE);

                }

            });

        }

    }

    public void zh() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String a = AppPaths.appPath("settings/f7/Enable2");

                if (open.cn.awg.pro.core.NativeFiles.exists(AppActivationActivity.this, (String) (a)) && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(AppActivationActivity.this, (String) (a)), "")) {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.wb10)).setText(String.valueOf("已激活"));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.wb11)).setText(String.valueOf(""));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.wb11)).setVisibility(android.view.View.GONE);
                    open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.tx4)), R.mipmap.a28);

                    String color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTextTrue);
                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.kp4)).setCardBackgroundColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xdbj4)).setVisibility(android.view.View.GONE);
                    isJh = true;

                } else {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.wb10)).setText(String.valueOf("未激活"));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.wb11)).setText(String.valueOf("一些功能将无法使用"));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.wb11)).setVisibility(android.view.View.VISIBLE);
                    open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.tx4)), R.mipmap.a29);

                    String color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTextFalse);
                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.kp4)).setCardBackgroundColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xdbj4)).setVisibility(android.view.View.VISIBLE);
                    isJh = false;

                }

            }

        });

    }

    public void load(Object url, Object title) {

        String[] name = new String[]{"url", "title", "code"};

        Object[] value = new Object[]{url, title, "super1"};
        open.cn.awg.pro.core.NativeActions.launch(AppActivationActivity.this, (Class<?>) (TextViewerActivity.class), (String[]) (name), (Object[]) (value));

    }

    public void qh() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String set2 = AppPaths.appPath("settings/f7/Enable2");

                if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj1)).getVisibility() == android.view.View.VISIBLE) {

                    String z = "";
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(android.view.View.VISIBLE);

                    if (mode == 1) {

                        z = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.wb5)).getText().toString();
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("产品ID"));
                        ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.bjk1)).setHint(String.valueOf("请输入15位产品ID"));
                        ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.bjk1)).setInputType(open.cn.awg.pro.core.NativeViewValues.inputType("number"));
                        ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.bjk1)).setFilters(new android.text.InputFilter[]{new android.text.InputFilter.LengthFilter(open.cn.awg.pro.core.TextValues.intValue(15, -1))});

                    }
                    ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(open.cn.awg.pro.core.NativeFiles.readText(AppActivationActivity.this, (String) (set2))));

                } else {

                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(android.view.View.GONE);
                    ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(""));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("应用激活"));

                }

            }

        });

    }

    public void qh_() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(android.view.View.GONE);
                ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(""));
                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("应用激活"));

            }

        });

    }

    public void gx() {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                show(1);
                open.cn.awg.pro.core.NativeActions.sleep(500);

                String set2 = AppPaths.appPath("settings/f7/Enable2");

                String b = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.bjk1)).getText().toString();
                b = open.cn.awg.pro.core.TextValues.trim(b);

                if (mode == 1) {

                    if (b.length() != 15) {

                        e1.tsk("提示", "产品ID不符合规范");
                        show(0);

                    } else {

                        if (e1.yz()) {

                            String urls = AppPaths.appPath("settings/f10/set2.inf");
                            urls = open.cn.awg.pro.core.NativeFiles.readText(AppActivationActivity.this, (String) (urls));
                            urls = e1.urlUnlockString(urls);

                            if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

                            } else {

                                urls = urls + "/";

                            }

                            String url = urls + "open.php?token=" + b;

                            String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                            if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                                e1.tsk("提示", "验证产品ID失败:连接服务器超时(-1)");
                                show(0);

                            } else {

                                if (java.util.Objects.equals(back, "true")) {

                                    open.cn.awg.pro.core.NativeFiles.writeText(AppActivationActivity.this, (String) (set2), (String) (b));
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
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                show(1);
                open.cn.awg.pro.core.NativeActions.sleep(500);

                String urls = AppPaths.appPath("settings/f10/set2.inf");
                urls = open.cn.awg.pro.core.NativeFiles.readText(AppActivationActivity.this, (String) (urls));
                urls = e1.urlUnlockString(urls);

                if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                String url = urls + "vip.json";

                if (e1.yz()) {

                    String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                    if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                        e1.tsk("提示", "连接服务器超时");
                        show(0);

                    } else {

                        try {

                            String oldenable = e1.readJson(back, "OldEnable");

                            String money = e1.readJson(back, "Money");

                            double money_ = open.cn.awg.pro.core.TextValues.doubleValue(money, ((double) (0)));

                            String h_y = e1.readJson(back, "Act_Y");

                            int h_y_ = open.cn.awg.pro.core.TextValues.intValue(h_y, 0);

                            String h_m = e1.readJson(back, "Act_M");

                            int h_m_ = open.cn.awg.pro.core.TextValues.intValue(h_m, 0);

                            String h_d = e1.readJson(back, "Act_D");

                            int h_d_ = open.cn.awg.pro.core.TextValues.intValue(h_d, 0);

                            String a3 = 日期工具.格式化日期时间(应用工具.获取应用第一次安装日期(lei, "open.cn.awg.pro"));

                            String y = open.cn.awg.pro.core.TextValues.slice(a3, 0, 4);

                            String m = open.cn.awg.pro.core.TextValues.slice(a3, 5, 7);

                            String d = open.cn.awg.pro.core.TextValues.slice(a3, 8, 10);

                            int y_ = open.cn.awg.pro.core.TextValues.intValue(y, 9999);

                            int m_ = open.cn.awg.pro.core.TextValues.intValue(m, 99);

                            int d_ = open.cn.awg.pro.core.TextValues.intValue(d, 99);

                            final String[] name = new String[]{"id", "name", "jg", "text"};

                            final Object[] value = new Object[]{"a1", "腕管Pro-产品ID", money_, "用以激活腕管Pro的产品ID，一经售出，概不退换！"};

                            if (java.util.Objects.equals(oldenable, "true")) {

                                if (y_ <= h_y_ && m_ <= h_m_ && d_ <= h_d_) {

                                    e1.jh();
                                    e1.tsk("提示", "检测到您为老用户,已为您激活应用");
                                    open.cn.awg.pro.core.NativeActions.sleep(1000);
                                    show(0);

                                } else {

                                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                        public void run() {

                                            open.cn.awg.pro.core.NativeActions.launch(AppActivationActivity.this, (Class<?>) (ProductPurchaseActivity.class), (String[]) (name), (Object[]) (value));

                                        }

                                    });
                                    open.cn.awg.pro.core.NativeActions.sleep(1000);
                                    show(0);

                                }

                            } else {

                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        open.cn.awg.pro.core.NativeActions.launch(AppActivationActivity.this, (Class<?>) (ProductPurchaseActivity.class), (String[]) (name), (Object[]) (value));

                                    }

                                });
                                open.cn.awg.pro.core.NativeActions.sleep(1000);
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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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
        jz();

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onTitleBarClick(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "应用激活")) {

            open.cn.awg.pro.core.NativeActions.finish(AppActivationActivity.this);

        }

    }

    private void onXxbj8Click(View vw) {

        if (!isJh) {

            mode = 1;
            qh();

        } else {

            if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj9)).getVisibility() == android.view.View.VISIBLE) {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("应用激活"));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.GONE);
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);

                    }

                });

            } else {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("提示"));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);

                    }

                });

            }

        }

    }

    private void onXdbj3Click(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.wb5)).getText().toString(), "请输入15位产品ID")) {

            mode = 1;
            qh();

        } else {

            e1.tsk("产品ID", ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.wb5)).getText().toString());

        }

    }



    private void onXdbj2Click(View vw) {
        load(AppPaths.appPath("data/assets/e3"), "功能帮助");

    }



    private void onXdbj4Click(View vw) {
        ksjh();

    }



    private void onTx1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(AppActivationActivity.this);

    }

    private boolean onBjk1Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

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

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj9)).getVisibility() == android.view.View.VISIBLE) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("应用激活"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);

                }

            });

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("提示"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);

                }

            });

        }

    }

    private void onTx6Click(View vw) {

        String a = AppPaths.appPath("settings/f7/Enable2");
        open.cn.awg.pro.core.NativeFiles.writeText(AppActivationActivity.this, (String) (a), (String) (""));
        jz();

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj9)).getVisibility() == android.view.View.VISIBLE) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("应用激活"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);

                }

            });

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("提示"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppActivationActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);

                }

            });

        }

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

        RelativeLayout i3167b45174 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        EditText i32ee139ba1 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i4a4248a0f2 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx5);
        i4a4248a0f2.setOnClickListener(tx5ClickListener);

        ImageView i6f2c7751cf = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx6);
        i6f2c7751cf.setOnClickListener(tx6ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
