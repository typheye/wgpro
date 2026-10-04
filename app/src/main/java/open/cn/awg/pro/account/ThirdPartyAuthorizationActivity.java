/**
 ******************************************************************************
 * @file    ThirdPartyAuthorizationActivity.java
 * @author  Typheye
 * @brief   Third-party authorization screen.
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
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class ThirdPartyAuthorizationActivity extends BaseAwgActivity {

    public final ThirdPartyAuthorizationActivity lei = this, 类 = this;
    public int mode = 0;
    public boolean isAcc = false;
    public boolean isExit = false;
    public Drawable dbe;
    public String android_id;
    public AppRuntimeBridge e1;
    public TypheyeServiceBridge easy;
    private final View.OnClickListener tx6ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx6Click(vw);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
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

    public void onCreate(Bundle be) {
        super.onCreate(be);
        e1 = new AppRuntimeBridge(this);
        easy = new TypheyeServiceBridge(this);
        setContentView(R.layout.third_party_authorization);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.third_party_authorization_root, R.id.xdbj1, R.id.title_bar);

    }

    public void jz() {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                show(2);

                if (!isExit) {

                    android_id = Settings.System.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

                    String urls = AppPaths.appPath("settings/f10/set2.inf");
                    urls = open.cn.awg.pro.core.NativeFiles.readText(ThirdPartyAuthorizationActivity.this, (String) (urls));
                    urls = e1.urlUnlockString(urls);

                    if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

                    } else {

                        urls = urls + "/";

                    }

                    String url = urls + "api.php";

                    String post_ = "req=openawg_get&token=" + android_id;

                    String[] post = open.cn.awg.pro.core.TextValues.split(post_, "&", false);

                    if (e1.yz()) {

                        String st2 = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (post), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                        if (java.util.Objects.equals(st2, null)) {

                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("网络异常"));

                                }

                            });
                            show(1);

                        } else {

                            try {

                                String code = e1.readJson(st2, "code");

                                if (java.util.Objects.equals(code, "0")) {

                                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                        public void run() {

                                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("未经授权的请求"));

                                        }

                                    });
                                    show(1);

                                } else if (java.util.Objects.equals(code, "200")) {

                                    String ap = e1.readJson(st2, "ap");

                                    final String an = e1.readJson(st2, "an");

                                    String imgurl = urls + "open/icon/" + ap + ".png";
                                    dbe = new BitmapDrawable(lei.getResources(), com.demo.e4.getURLimage(imgurl));
                                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                        public void run() {

                                            open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.tx5)), dbe);
                                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb16)).setText(String.valueOf(an));

                                        }

                                    });
                                    zh();

                                } else if (java.util.Objects.equals(code, "20")) {

                                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                        public void run() {

                                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("应用未获得授权"));

                                        }

                                    });
                                    show(1);

                                } else if (java.util.Objects.equals(code, "10")) {

                                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                        public void run() {

                                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("请求超时"));

                                        }

                                    });
                                    show(1);

                                } else {

                                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                        public void run() {

                                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("未经授权的请求"));

                                        }

                                    });
                                    show(1);

                                }

                            } catch (Throwable __$_e__) {

                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("未经授权的请求"));

                                    }

                                });
                                show(1);

                            }

                        }

                    } else {

                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("应用验证未通过"));

                            }

                        });
                        show(1);

                    }

                }

            }

        });

    }

    public void sq_true() {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                show(2);

                String a = AppPaths.appPath("settings/account/user");

                String set2 = AppPaths.appPath("settings/n1/set2.inf");

                String urls = AppPaths.appPath("settings/f10/set2.inf");
                urls = open.cn.awg.pro.core.NativeFiles.readText(ThirdPartyAuthorizationActivity.this, (String) (urls));
                urls = e1.urlUnlockString(urls);

                if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                String url = urls + "api.php";

                String post_ = "req=openawg_ctrl&token=" + android_id + "&state=true&uid=" + open.cn.awg.pro.core.NativeFiles.readText(ThirdPartyAuthorizationActivity.this, (String) (a)) + "&uname=" + open.cn.awg.pro.core.NativeFiles.readText(ThirdPartyAuthorizationActivity.this, (String) (set2));

                String[] post = open.cn.awg.pro.core.TextValues.split(post_, "&", false);

                if (e1.yz()) {

                    String st2 = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (post), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                    if (java.util.Objects.equals(st2, null)) {

                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("网络异常"));

                            }

                        });
                        show(1);

                    } else {

                        try {

                            String code = e1.readJson(st2, "code");

                            if (java.util.Objects.equals(code, "200")) {

                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("已成功授权"));

                                    }

                                });
                                show(1);

                            } else if (java.util.Objects.equals(code, "10")) {

                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("请求超时"));

                                    }

                                });
                                show(1);

                            } else {

                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("授权失败"));

                                    }

                                });
                                show(1);

                            }

                        } catch (Throwable __$_e__) {

                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("授权失败"));

                                }

                            });
                            show(1);

                        }

                    }

                }

            }

        });

    }

    public void sq_false() {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                show(2);

                String a = AppPaths.appPath("settings/account/user");

                String set2 = AppPaths.appPath("settings/n1/set2.inf");

                String urls = AppPaths.appPath("settings/f10/set2.inf");
                urls = open.cn.awg.pro.core.NativeFiles.readText(ThirdPartyAuthorizationActivity.this, (String) (urls));
                urls = e1.urlUnlockString(urls);

                if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                String url = urls + "api.php";

                String post_ = "req=openawg_ctrl&token=" + android_id + "&state=false";

                String[] post = open.cn.awg.pro.core.TextValues.split(post_, "&", false);

                if (e1.yz()) {

                    String st2 = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (post), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                    if (java.util.Objects.equals(st2, null)) {

                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("网络异常"));

                            }

                        });
                        show(1);

                    } else {

                        try {

                            String code = e1.readJson(st2, "code");

                            if (java.util.Objects.equals(code, "200")) {

                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("已拒绝授权"));

                                    }

                                });
                                show(1);

                            } else if (java.util.Objects.equals(code, "10")) {

                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("请求超时"));

                                    }

                                });
                                show(1);

                            } else {

                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("授权失败"));

                                    }

                                });
                                show(1);

                            }

                        } catch (Throwable __$_e__) {

                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("授权失败"));

                                }

                            });
                            show(1);

                        }

                    }

                }

            }

        });

    }

    public void show(int i_) {

        final int i = i_;

        final android.widget.LinearLayout xxbj1 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.xxbj1));

        final android.widget.LinearLayout xxbj4 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.xxbj4));

        final android.widget.LinearLayout xxbj5 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.xxbj5));
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                if (i == 0) {

                    xxbj1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    xxbj4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    xxbj5.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                } else if (i == 1) {

                    xxbj1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    xxbj4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    xxbj5.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                } else if (i == 2) {

                    xxbj1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    xxbj4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    xxbj5.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                }

            }

        });

    }

    public void zh() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String te1 = "";

                String te2 = "";

                String te3 = "";

                String a = AppPaths.appPath("settings/account/user");

                String b = AppPaths.appPath("settings/account/pass");

                String c = AppPaths.appPath("settings/account/isAcc");

                String set2 = AppPaths.appPath("settings/n1/set2.inf");

                String set3 = AppPaths.appPath("settings/n1/set3.inf");

                String set4 = AppPaths.appPath("settings/n1/set4.inf");

                if (!open.cn.awg.pro.core.NativeFiles.exists(ThirdPartyAuthorizationActivity.this, (String) (a)) || !open.cn.awg.pro.core.NativeFiles.exists(ThirdPartyAuthorizationActivity.this, (String) (b))) {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb10)).setText(String.valueOf("未登录"));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb11)).setText(String.valueOf(""));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb6)).setText(String.valueOf(""));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb6)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb11)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.tx4)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.tx7)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("请先登录您的账户后再操作"));
                    show(1);

                } else {

                    te1 = open.cn.awg.pro.core.NativeFiles.readText(ThirdPartyAuthorizationActivity.this, (String) (set2));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb10)).setText(String.valueOf(te1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb11)).setText(String.valueOf("UID:" + open.cn.awg.pro.core.NativeFiles.readText(ThirdPartyAuthorizationActivity.this, (String) (a))));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb6)).setText(String.valueOf("正"));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb6)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb11)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.tx4)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                    if (!java.util.Objects.equals(easy.getPerIcon(open.cn.awg.pro.core.NativeFiles.readText(ThirdPartyAuthorizationActivity.this, (String) (a))), "null")) {

                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.tx7)), easy.getPerIconPath(open.cn.awg.pro.core.NativeFiles.readText(ThirdPartyAuthorizationActivity.this, (String) (a))));
                        ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.tx7)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                    }

                    String set1 = AppPaths.appPath("settings/n1/set1.inf");

                    if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ThirdPartyAuthorizationActivity.this, (String) (set1)), "true")) {

                        show(0);

                    } else {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("此账户不允许授权至第三方应用"));
                        show(1);

                    }

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ThirdPartyAuthorizationActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

        if (kc == 4) {

        }
        return false;

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onTx2Click(View vw) {
        sq_false();

    }

    private void onTx3Click(View vw) {
        sq_true();

    }

    private void onTx1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(ThirdPartyAuthorizationActivity.this);

    }

    private void onTx6Click(View vw) {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                final String ca = AppPaths.appPath("cache/req/exit");
                open.cn.awg.pro.core.NativeFiles.writeText(ThirdPartyAuthorizationActivity.this, (String) (ca), (String) ("true"));
                isExit = true;
                show(2);
                open.cn.awg.pro.core.NativeActions.sleep(500);
                open.cn.awg.pro.core.NativeActions.finish(ThirdPartyAuthorizationActivity.this);

            }

        });

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);

        ImageView i11aeec1890 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx4);

        ImageView i3621e3f4a1 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx7);

        LinearLayout i3f6c9f386b = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj8);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i6f2c7751cf = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx6);
        i6f2c7751cf.setOnClickListener(tx6ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
