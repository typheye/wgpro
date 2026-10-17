/**
 ******************************************************************************
 * @file    TuneFreeActivity.java
 * @author  Typheye
 * @brief   TuneFree music plugin screen.
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
package open.cn.awg.pro.music;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.core.widget.NestedScrollView;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public class TuneFreeActivity extends BaseAwgActivity {

    public final TuneFreeActivity lei = this, 类 = this;
    public String um = "";
    public File f;
    public AppRuntimeBridge e1;
    public TuneFreeMusicBridge e12;
    public boolean state_login_xh = true;
    public String cookiepath = AppPaths.appPath("data/plugin/tunefree/cookie");
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
    private final View.OnClickListener tx4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx4Click(vw);
        }

    };
    private final View.OnClickListener an2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn2Click(vw);
        }

    };
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
    private final View.OnTouchListener bjk1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjk1Touch(vw, me);
        }

    };
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
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
    private final View.OnLongClickListener xdbj5LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj5LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj5ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj5Click(vw);
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
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        e1 = new AppRuntimeBridge(this);
        e12 = new TuneFreeMusicBridge(this);
        setContentView(R.layout.tune_free);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        show(0);

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.tune_free_root, R.id.xdbj1, R.id.title_bar);

        android.widget.Spinner xlcd1 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.xlcd1));

        String[] sz = new String[]{"单曲"};
        open.cn.awg.pro.core.NativeViewValues.spinner(xlcd1, sz);

    }

    public void login$exit() {
        state_login_xh = false;
        show(0);

    }

    public void jz() {

        if (open.cn.awg.pro.core.NativeFiles.exists(TuneFreeActivity.this, (String) (cookiepath)) && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(TuneFreeActivity.this, (String) (cookiepath)), "")) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.xdbj4)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.xxbj5)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.xxbj10)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                }

            });

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.xdbj4)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.xxbj5)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.xxbj10)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        }

    }

    public void login_xh(Object url_, Object key_) {

        final Object url = url_;

        final Object key = key_;

        final String ljf = open.cn.awg.pro.core.NativeFiles.readText(TuneFreeActivity.this, (String) (AppPaths.appPath("data/assets/a17")));

        final String set35 = AppPaths.appPath("settings/other/0xO7YX2/url");
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                state_login_xh = true;
                ewm(String.valueOf(url));
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.wb11)).setText(String.valueOf("扫描二维码完成登录"));

                    }

                });
                show(3);

                while (state_login_xh && !Thread.currentThread().isInterrupted()) {

                    open.cn.awg.pro.core.NativeActions.sleep(3000);

                    if (!state_login_xh) {

                        break;

                    }

                    String setback = "";

                    String code = "";

                    String url = open.cn.awg.pro.core.TextValues.trim(open.cn.awg.pro.core.NativeFiles.readText(TuneFreeActivity.this, (String) (set35))) + "/login/qr/check";

                    String whq = "?key=" + key + ljf + "timestamp=" + open.cn.awg.pro.core.NativeDevice.formatDate(4);

                    String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url + whq), (String[]) (null), (String) ("utf-8"), null, false, null, 0, 0, null).bodyForHttp200();

                    if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                        setback = "0";

                    } else {

                        try {

                            code = e1.readJson(back, "code");

                        } catch (Throwable __$_e__) {

                            back = "0";

                        }
                        setback = back;

                    }

                    if (java.util.Objects.equals(setback, "0")) {

                        show(0);
                        e1.tsk("提示", "加载失败,请重试");
                        break;

                    } else {

                        if (java.util.Objects.equals(code, "803")) {

                            String coo = e1.readJson(setback, "cookie");
                            open.cn.awg.pro.core.NativeFiles.writeText(TuneFreeActivity.this, (String) (cookiepath), (String) (coo));
                            show(0);
                            e1.tsk("提示", "登录成功");
                            break;

                        } else if (java.util.Objects.equals(code, "800")) {

                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.wb11)).setText(String.valueOf("二维码已过期"));
                                    ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.tx3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                                }

                            });

                        } else if (java.util.Objects.equals(code, "802")) {

                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.wb11)).setText(String.valueOf("扫码成功 等待确认"));
                                    ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.tx3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                                }

                            });

                        } else {

                        }

                    }

                }

            }

        });

    }

    public void login() {

        final String ljf = open.cn.awg.pro.core.NativeFiles.readText(TuneFreeActivity.this, (String) (AppPaths.appPath("data/assets/a17")));

        final String set35 = AppPaths.appPath("settings/other/0xO7YX2/url");
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                show(2);

                String key = "";

                String url = open.cn.awg.pro.core.TextValues.trim(open.cn.awg.pro.core.NativeFiles.readText(TuneFreeActivity.this, (String) (set35))) + "/login/qr/key?timestamp=" + open.cn.awg.pro.core.NativeDevice.formatDate(4);

                String setback = "";

                String f = "User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept-language=zh-CN";

                String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (null), (String) ("utf-8"), null, false, null, 0, 0, null).bodyForHttp200();

                if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                    setback = "0";

                } else {

                    try {

                        key = e1.readJson(back, "data");
                        key = e1.readJson(key, "unikey");
                        url = open.cn.awg.pro.core.TextValues.trim(open.cn.awg.pro.core.NativeFiles.readText(TuneFreeActivity.this, (String) (set35))) + "/login/qr/create";

                        String whq = "?key=" + key + ljf + "timestamp=" + open.cn.awg.pro.core.NativeDevice.formatDate(4);
                        back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url + whq), (String[]) (null), (String) ("utf-8"), null, false, null, 0, 0, null).bodyForHttp200();

                        if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                            setback = "0";

                        } else {

                            try {

                                back = e1.readJson(back, "data");
                                back = e1.readJson(back, "qrurl");

                            } catch (Throwable __$_e__) {

                                back = "0";

                            }
                            setback = back;

                        }

                    } catch (Throwable __$_e__) {

                    }

                }

                if (java.util.Objects.equals(setback, "0")) {

                    show(0);
                    e1.tsk("提示", "加载失败,请重试");

                } else {

                    login_xh(setback, key);

                }

            }

        });

    }

    public void show(int i) {

        final android.widget.TextView tab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.title_bar));

        final android.widget.LinearLayout x1 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.xxbj1));

        final android.widget.LinearLayout x2 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.xxbj6));

        final android.widget.LinearLayout x3 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.xxbj2));

        final android.widget.LinearLayout x4 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.xxbj8));

        final android.widget.LinearLayout x5 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.xxbj12));

        if (i == 0) {

            jz();
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeDevice.setKeepScreenOn(TuneFreeActivity.this, !(true));
                    tab.setText(String.valueOf("TuneFree"));
                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    x4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x5.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        } else if (i == 1) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeDevice.setKeepScreenOn(TuneFreeActivity.this, !(true));
                    tab.setText(String.valueOf("搜索歌曲"));
                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x5.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        } else if (i == 2) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeDevice.setKeepScreenOn(TuneFreeActivity.this, !(true));
                    tab.setText(String.valueOf("TuneFree"));
                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x5.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        } else if (i == 3) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeDevice.setKeepScreenOn(TuneFreeActivity.this, !(false));
                    tab.setText(String.valueOf("TuneFree"));
                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    x5.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        } else if (i == 4) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeDevice.setKeepScreenOn(TuneFreeActivity.this, !(false));
                    tab.setText(String.valueOf("TuneFree"));
                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x5.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                }

            });

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

                    ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.tx3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                } catch (Throwable e) {

                    ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.tx3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            }

        });

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
            applyWindowModeFromSettings();
        jz();

    }

    public void onRestart() {
        super.onRestart();
            applyWindowModeFromSettings();

    }

    public void onStart() {
        super.onStart();
            applyWindowModeFromSettings();

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

            if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "TuneFree")) {

                open.cn.awg.pro.core.NativeActions.finish(TuneFreeActivity.this);

            } else if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "搜索歌曲")) {

                show(0);

            }

        }
        return false;

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onTitleBarClick(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "TuneFree")) {

            open.cn.awg.pro.core.NativeActions.finish(TuneFreeActivity.this);

        } else if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "搜索歌曲")) {

            show(0);

        }

    }

    private void onXdbj4Click(View vw) {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                show(2);
                open.cn.awg.pro.core.NativeActions.sleep(500);
                show(4);

            }

        });

    }

    private boolean onXdbj4LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.wb7)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.wb8)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj6Click(View vw) {
        e1.goplu("20007", "TuneFree", false);

    }

    private boolean onXdbj6LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.wb12)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.wb13)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj5Click(View vw) {
        login();

    }

    private boolean onXdbj5LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.wb9)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.wb10)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj2Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(""));

            }

        });
        show(1);

    }

    private boolean onXdbj2LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.wb3)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.wb4)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj3Click(View vw) {
        e1.tsk_QR("功能帮助", "此服务由TuneFree提供,如有侵权,请联系498978473@qq.com\n\n体验完整TuneFree\n扫描下方二维码继续", "https://tunefree.fun");

    }

    private boolean onXdbj3LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.wb5)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.wb6)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx2Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(TuneFreeActivity.this);

    }

    private boolean onBjk1Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private void onAn1Click(View vw) {

        final String l = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.bjk1)).getText().toString();

        final int p = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(TuneFreeActivity.this.getWindow().getDecorView(), R.id.xlcd1)).getLastVisiblePosition();
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String[] a1 = new String[]{"name", "type"};

                Object[] a2 = new Object[]{l, p};
                open.cn.awg.pro.core.NativeActions.launch(TuneFreeActivity.this, (Class<?>) (TuneFreeCloudSearchActivity.class), (String[]) (a1), (Object[]) (a2));

            }

        });

    }

    private void onTx1Click(View vw) {
        show(0);

    }

    private void onAn2Click(View vw) {
        login$exit();

    }

    private void onTx4Click(View vw) {
        show(0);

    }

    private void onTx5Click(View vw) {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                show(2);
                open.cn.awg.pro.core.NativeActions.sleep(500);
                show(0);

            }

        });

    }

    private void onTx6Click(View vw) {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                show(2);
                open.cn.awg.pro.core.NativeFiles.delete(TuneFreeActivity.this, (String) (cookiepath), false);
                open.cn.awg.pro.core.NativeActions.sleep(500);
                show(0);
                e1.tsk("提示", "已退出登录");

            }

        });

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);
        i2202ede8ff.setOnLongClickListener(xdbj4LongClickListener);

        RelativeLayout i171cedf1eb = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj6);
        i171cedf1eb.setOnClickListener(xdbj6ClickListener);
        i171cedf1eb.setOnLongClickListener(xdbj6LongClickListener);

        RelativeLayout c0d3e00d2b = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj5);
        c0d3e00d2b.setOnClickListener(xdbj5ClickListener);
        c0d3e00d2b.setOnLongClickListener(xdbj5LongClickListener);

        RelativeLayout i3167b45174 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);
        i3167b45174.setOnLongClickListener(xdbj2LongClickListener);

        RelativeLayout i20c2777fab = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);
        i20c2777fab.setOnLongClickListener(xdbj3LongClickListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        EditText i32ee139ba1 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        Button i171ea34f1a = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        Button i0b66a667b1 = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an2);
        i0b66a667b1.setOnClickListener(an2ClickListener);

        ImageView i11aeec1890 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx4);
        i11aeec1890.setOnClickListener(tx4ClickListener);

        ImageView i4a4248a0f2 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx5);
        i4a4248a0f2.setOnClickListener(tx5ClickListener);

        ImageView i6f2c7751cf = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx6);
        i6f2c7751cf.setOnClickListener(tx6ClickListener);

        NestedScrollView a6dab51133 = (NestedScrollView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.qtgd1);

        __layoutIsLoaded(ay, vw);
    }
}
