/**
 ******************************************************************************
 * @file    UpdatePromptActivity.java
 * @author  Typheye
 * @brief   Shows update notice and QR-code update guidance.
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
package open.cn.awg.pro.update;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class UpdatePromptActivity extends BaseAwgActivity {

    public final UpdatePromptActivity lei = this, 类 = this;
    public AppRuntimeBridge e1;

    public String update_url = "";
    private final View.OnClickListener an1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn1Click(vw);
        }

    };
    private final View.OnClickListener tx4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx4Click(vw);
        }

    };
    private final View.OnClickListener tx14ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx14Click(vw);
        }

    };
    private final View.OnClickListener tx13ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx13Click(vw);
        }

    };
    private final View.OnClickListener tx11ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx11Click(vw);
        }

    };
    private final View.OnClickListener tx16ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx16Click(vw);
        }

    };
    private final View.OnClickListener tx3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx3Click(vw);
        }

    };
    private final View.OnClickListener tx10ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx10Click(vw);
        }

    };
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };
    private final View.OnClickListener tx5ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx5Click(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        e1 = new AppRuntimeBridge(this);
        setContentView(R.layout.update_prompt);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        update();

    }

    public void update() {

        final String auid = AppPaths.appPath("settings/account/user");

        final String sec_updater = AppPaths.appPath("data/sec/updater");

        String set36 = AppPaths.appPath("settings/fixVersion");

        int a11;

        String vn;
        a11 = com.demo.e4.getVersionCode(lei);
        vn = com.demo.e4.getVersionName(lei);

        final int myappversion = a11;

        final String myappversionname = vn;

        final String appfixversion = open.cn.awg.pro.core.TextValues.trim(open.cn.awg.pro.core.NativeFiles.readText(UpdatePromptActivity.this, (String) (set36)));

        String set2e = AppPaths.appPath("settings/f10/set2.inf");

        final String set3 = AppPaths.appPath("cache/update");
        set2e = open.cn.awg.pro.core.NativeFiles.readText(UpdatePromptActivity.this, (String) (set2e));
        set2e = e1.urlUnlockString(set2e);

        if (open.cn.awg.pro.core.TextValues.endsWith(set2e, "/")) {

        } else {

            set2e = set2e + "/";

        }
        set2e = set2e + "config_v2.json";

        final String url = set2e;
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        String e = "Loading...";
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.wb5)).setText(String.valueOf(e));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.wb8)).setText(String.valueOf(e));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.wb11)).setText(String.valueOf(e));

                    }

                });

                String setback = "";

                String version = "";

                String versionname = "";

                String time = "";

                String text = "";

                String urls = "";

                String allow = "";

                String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                    setback = "0";

                } else {

                    setback = e1.htmlToText(back);

                    if (java.util.Objects.equals(setback, "0")) {

                        setback = "0";

                    } else {

                        version = e1.readJson(setback, "UpdateVersion");
                        versionname = e1.readJson(setback, "UpdateVersionName");
                        time = e1.readJson(setback, "UpdateTime");
                        text = e1.readJson(setback, "UpdateText");
                        allow = e1.readJson(setback, "UpdateAllow");
                        urls = e1.readJson(setback, "UpdateUrl");
                        urls = e1.urlUnlockString(urls);

                    }

                }

                if (java.util.Objects.equals(setback, "0")) {

                    if (open.cn.awg.pro.core.NativeFiles.exists(UpdatePromptActivity.this, (String) (sec_updater))) {

                        setback = open.cn.awg.pro.core.NativeFiles.readText(UpdatePromptActivity.this, (String) (sec_updater));
                        version = e1.readJson(setback, "UpdateVersion");
                        versionname = e1.readJson(setback, "UpdateVersionName");
                        time = e1.readJson(setback, "UpdateTime");
                        text = e1.readJson(setback, "UpdateText");
                        urls = e1.readJson(setback, "UpdateUrl");
                        urls = e1.urlUnlockString(urls);

                        final int b1 = Integer.parseInt(version);

                        final String b2 = time;

                        final String b3 = text;
                        update_url = urls;
                        ewm(update_url);
                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.wb5)).setText(String.valueOf(b1));
                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.wb8)).setText(String.valueOf(b2));
                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.wb11)).setText(String.valueOf(b3));

                            }

                        });

                    } else {

                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                String e = "加载异常...";
                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.wb5)).setText(String.valueOf(e));
                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.wb8)).setText(String.valueOf(e));
                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.wb11)).setText(String.valueOf(e));

                            }

                        });

                    }

                } else {

                    final int b1 = Integer.parseInt(version);

                    final String b2 = time;

                    final String b3 = text;

                    if ((java.util.Objects.equals(allow, "0") || open.cn.awg.pro.core.TextValues.contains(allow, open.cn.awg.pro.core.NativeFiles.readText(UpdatePromptActivity.this, (String) (auid)))) && e1.islogin()) {

                        if (myappversion < b1 && b1 != 9999999) {

                            open.cn.awg.pro.core.NativeFiles.writeText(UpdatePromptActivity.this, (String) (sec_updater), (String) (setback));

                        } else {

                            int ccc = versionname.compareTo(myappversionname);

                            if (!java.util.Objects.equals(versionname, myappversionname) && ccc > 0) {

                                open.cn.awg.pro.core.NativeFiles.writeText(UpdatePromptActivity.this, (String) (sec_updater), (String) (setback));

                            } else {

                                open.cn.awg.pro.core.NativeFiles.delete(UpdatePromptActivity.this, (String) (sec_updater), false);

                            }

                        }

                    } else {

                        open.cn.awg.pro.core.NativeFiles.delete(UpdatePromptActivity.this, (String) (sec_updater), false);

                    }
                    update_url = urls;
                    ewm(update_url);
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.wb5)).setText(String.valueOf(b1));
                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.wb8)).setText(String.valueOf(b2));
                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.wb11)).setText(String.valueOf(b3));

                        }

                    });

                }

            }

        });

    }

    public void ewm(String url) {

        final String qr = url;
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                try {

                    ImageView imageView = findViewById(R.id.tx4);
                    Bitmap bitmap = com.demo.e4.createQRCodeBitmap(qr, 800, 800, "UTF-8", "H", "1");
                    imageView.setImageBitmap(bitmap);

                    ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.tx4)).setVisibility(android.view.View.VISIBLE);

                } catch (Throwable e) {

                    ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.tx4)).setVisibility(android.view.View.GONE);

                }

            }

        });

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(UpdatePromptActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(UpdatePromptActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(UpdatePromptActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(UpdatePromptActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));
        applyFullscreenWindow();

        if (java.util.Objects.equals(a, "1")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xdbj1)), u, 0, u, 50);

        } else if (java.util.Objects.equals(a, "2")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xdbj1)), 0, 0, 0, 0);

        } else if (java.util.Objects.equals(a, "3")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xdbj1)), u, 0, u, 50);

        } else if (java.util.Objects.equals(a, "4")) {

        }
        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj10)).setVisibility(android.view.View.VISIBLE);
        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj8)).setVisibility(android.view.View.GONE);
        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(android.view.View.GONE);
        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj12)).setVisibility(android.view.View.GONE);
        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj11)).setVisibility(android.view.View.GONE);
        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj7)).setVisibility(android.view.View.GONE);

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
        applyFullscreenWindow();

    }

    public void onRestart() {
        super.onRestart();
        applyFullscreenWindow();

    }

    public void onStart() {
        super.onStart();
        applyFullscreenWindow();

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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
        applyFullscreenWindow();

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

        }
        return false;

    }

    private void onTx5Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj10)).setVisibility(android.view.View.GONE);
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj8)).setVisibility(android.view.View.VISIBLE);

            }

        });

    }

    private void onTx2Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj10)).setVisibility(android.view.View.VISIBLE);
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj8)).setVisibility(android.view.View.GONE);

            }

        });

    }

    private void onTx10Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj8)).setVisibility(android.view.View.GONE);
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(android.view.View.VISIBLE);

            }

        });

    }

    private void onTx3Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj8)).setVisibility(android.view.View.VISIBLE);
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(android.view.View.GONE);

            }

        });

    }

    private void onTx16Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(android.view.View.GONE);
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj12)).setVisibility(android.view.View.VISIBLE);

            }

        });

    }

    private void onTx11Click(View vw) {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(android.view.View.GONE);
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj12)).setVisibility(android.view.View.GONE);
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj11)).setVisibility(android.view.View.VISIBLE);

                    }

                });
                open.cn.awg.pro.core.NativeActions.sleep(2000);
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj11)).setVisibility(android.view.View.GONE);
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj7)).setVisibility(android.view.View.VISIBLE);

                    }

                });

            }

        });

    }

    private void onTx13Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(android.view.View.VISIBLE);
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj12)).setVisibility(android.view.View.GONE);

            }

        });

    }

    private void onTx14Click(View vw) {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj12)).setVisibility(android.view.View.GONE);
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj11)).setVisibility(android.view.View.VISIBLE);

                    }

                });
                open.cn.awg.pro.core.NativeActions.sleep(500);
                open.cn.awg.pro.core.NativeActions.finish(UpdatePromptActivity.this);

            }

        });

    }

    private void onTx4Click(View vw) {

        if (((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.wb16)).getVisibility() == android.view.View.VISIBLE) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.wb16)).setVisibility(android.view.View.GONE);
                    ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.an1)).setVisibility(android.view.View.GONE);

                }

            });

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.wb16)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.an1)).setVisibility(android.view.View.VISIBLE);

                }

            });

        }

    }

    private void onAn1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.xxbj7)).setVisibility(android.view.View.GONE);

                android.widget.ImageView tx14 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(UpdatePromptActivity.this.getWindow().getDecorView(), R.id.tx14));
                tx14.performClick();

            }

        });

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        ImageView i4a4248a0f2 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx5);
        i4a4248a0f2.setOnClickListener(tx5ClickListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj4);

        RelativeLayout i3167b45174 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj2);

        RelativeLayout i20c2777fab = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj3);

        ImageView i3ab5c5fd62 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx10);
        i3ab5c5fd62.setOnClickListener(tx10ClickListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView a81b730195 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx16);
        a81b730195.setOnClickListener(tx16ClickListener);

        ImageView i75b44e9f85 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx11);
        i75b44e9f85.setOnClickListener(tx11ClickListener);

        ImageView i16ad18f521 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx13);
        i16ad18f521.setOnClickListener(tx13ClickListener);

        ImageView ac03b05d0f = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx14);
        ac03b05d0f.setOnClickListener(tx14ClickListener);

        ImageView i11aeec1890 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx4);
        i11aeec1890.setOnClickListener(tx4ClickListener);

        Button i171ea34f1a = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
