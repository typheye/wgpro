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
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public TypheyeServiceBridge easy = new TypheyeServiceBridge(_APPINFO);
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
        setContentView(R.layout.third_party_authorization);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.third_party_authorization_root, R.id.xdbj1, R.id.title_bar);

    }

    public void jz() {
        gj.xc(new Thread() {

            public void run() {

                show(2);

                if (!isExit) {

                    android_id = Settings.System.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

                    String urls = AppPaths.appPath("settings/f10/set2.inf");
                    urls = wj.dqwb(urls);
                    urls = e1.urlUnlockString(urls);

                    if (zf.cjw(urls, "/")) {

                    } else {

                        urls = urls + "/";

                    }

                    String url = urls + "api.php";

                    String post_ = "req=openawg_get&token=" + android_id;

                    String[] post = zf.fg(post_, "&", false);

                    if (e1.yz()) {

                        String st2 = wl.hq(url, post, "utf-8", null, true, null, 20000, 20000, null);

                        if (zf.dy(st2, null)) {

                            gj.jmxc(new Runnable() {

                                public void run() {

                                    st.wb(R.id.wb3).zf("网络异常");

                                }

                            });
                            show(1);

                        } else {

                            try {

                                String code = e1.readJson(st2, "code");

                                if (zf.dy(code, "0")) {

                                    gj.jmxc(new Runnable() {

                                        public void run() {

                                            st.wb(R.id.wb3).zf("未经授权的请求");

                                        }

                                    });
                                    show(1);

                                } else if (zf.dy(code, "200")) {

                                    String ap = e1.readJson(st2, "ap");

                                    final String an = e1.readJson(st2, "an");

                                    String imgurl = urls + "open/icon/" + ap + ".png";
                                    dbe = new BitmapDrawable(lei.getResources(), com.demo.e4.getURLimage(imgurl));
                                    gj.jmxc(new Runnable() {

                                        public void run() {

                                            st.tx(R.id.tx5).tx(dbe);
                                            st.wb(R.id.wb16).zf(an);

                                        }

                                    });
                                    zh();

                                } else if (zf.dy(code, "20")) {

                                    gj.jmxc(new Runnable() {

                                        public void run() {

                                            st.wb(R.id.wb3).zf("应用未获得授权");

                                        }

                                    });
                                    show(1);

                                } else if (zf.dy(code, "10")) {

                                    gj.jmxc(new Runnable() {

                                        public void run() {

                                            st.wb(R.id.wb3).zf("请求超时");

                                        }

                                    });
                                    show(1);

                                } else {

                                    gj.jmxc(new Runnable() {

                                        public void run() {

                                            st.wb(R.id.wb3).zf("未经授权的请求");

                                        }

                                    });
                                    show(1);

                                }

                            } catch (Throwable __$_e__) {

                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        st.wb(R.id.wb3).zf("未经授权的请求");

                                    }

                                });
                                show(1);

                            }

                        }

                    } else {

                        gj.jmxc(new Runnable() {

                            public void run() {

                                st.wb(R.id.wb3).zf("应用验证未通过");

                            }

                        });
                        show(1);

                    }

                }

            }

        });

    }

    public void sq_true() {
        gj.xc(new Thread() {

            public void run() {

                show(2);

                String a = AppPaths.appPath("settings/account/user");

                String set2 = AppPaths.appPath("settings/n1/set2.inf");

                String urls = AppPaths.appPath("settings/f10/set2.inf");
                urls = wj.dqwb(urls);
                urls = e1.urlUnlockString(urls);

                if (zf.cjw(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                String url = urls + "api.php";

                String post_ = "req=openawg_ctrl&token=" + android_id + "&state=true&uid=" + wj.dqwb(a) + "&uname=" + wj.dqwb(set2);

                String[] post = zf.fg(post_, "&", false);

                if (e1.yz()) {

                    String st2 = wl.hq(url, post, "utf-8", null, true, null, 20000, 20000, null);

                    if (zf.dy(st2, null)) {

                        gj.jmxc(new Runnable() {

                            public void run() {

                                st.wb(R.id.wb3).zf("网络异常");

                            }

                        });
                        show(1);

                    } else {

                        try {

                            String code = e1.readJson(st2, "code");

                            if (zf.dy(code, "200")) {

                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        st.wb(R.id.wb3).zf("已成功授权");

                                    }

                                });
                                show(1);

                            } else if (zf.dy(code, "10")) {

                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        st.wb(R.id.wb3).zf("请求超时");

                                    }

                                });
                                show(1);

                            } else {

                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        st.wb(R.id.wb3).zf("授权失败");

                                    }

                                });
                                show(1);

                            }

                        } catch (Throwable __$_e__) {

                            gj.jmxc(new Runnable() {

                                public void run() {

                                    st.wb(R.id.wb3).zf("授权失败");

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
        gj.xc(new Thread() {

            public void run() {

                show(2);

                String a = AppPaths.appPath("settings/account/user");

                String set2 = AppPaths.appPath("settings/n1/set2.inf");

                String urls = AppPaths.appPath("settings/f10/set2.inf");
                urls = wj.dqwb(urls);
                urls = e1.urlUnlockString(urls);

                if (zf.cjw(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                String url = urls + "api.php";

                String post_ = "req=openawg_ctrl&token=" + android_id + "&state=false";

                String[] post = zf.fg(post_, "&", false);

                if (e1.yz()) {

                    String st2 = wl.hq(url, post, "utf-8", null, true, null, 20000, 20000, null);

                    if (zf.dy(st2, null)) {

                        gj.jmxc(new Runnable() {

                            public void run() {

                                st.wb(R.id.wb3).zf("网络异常");

                            }

                        });
                        show(1);

                    } else {

                        try {

                            String code = e1.readJson(st2, "code");

                            if (zf.dy(code, "200")) {

                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        st.wb(R.id.wb3).zf("已拒绝授权");

                                    }

                                });
                                show(1);

                            } else if (zf.dy(code, "10")) {

                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        st.wb(R.id.wb3).zf("请求超时");

                                    }

                                });
                                show(1);

                            } else {

                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        st.wb(R.id.wb3).zf("授权失败");

                                    }

                                });
                                show(1);

                            }

                        } catch (Throwable __$_e__) {

                            gj.jmxc(new Runnable() {

                                public void run() {

                                    st.wb(R.id.wb3).zf("授权失败");

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

        final i.runlibrary.app.v.xxbj xxbj1 = st.xxbj(R.id.xxbj1);

        final i.runlibrary.app.v.xxbj xxbj4 = st.xxbj(R.id.xxbj4);

        final i.runlibrary.app.v.xxbj xxbj5 = st.xxbj(R.id.xxbj5);
        gj.jmxc(new Runnable() {

            public void run() {

                if (i == 0) {

                    xxbj1.kjd(0);
                    xxbj4.kjd(8);
                    xxbj5.kjd(8);

                } else if (i == 1) {

                    xxbj1.kjd(8);
                    xxbj4.kjd(0);
                    xxbj5.kjd(8);

                } else if (i == 2) {

                    xxbj1.kjd(8);
                    xxbj4.kjd(8);
                    xxbj5.kjd(0);

                }

            }

        });

    }

    public void zh() {
        gj.jmxc(new Runnable() {

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

                if (!wj.cz(a) || !wj.cz(b)) {

                    st.wb(R.id.wb10).zf("未登录");
                    st.wb(R.id.wb11).zf("");
                    st.wb(R.id.wb6).zf("");
                    st.wb(R.id.wb6).kjd(8);
                    st.wb(R.id.wb11).kjd(8);
                    st.tx(R.id.tx4).kjd(0);
                    st.tx(R.id.tx7).kjd(8);
                    st.wb(R.id.wb3).zf("请先登录您的账户后再操作");
                    show(1);

                } else {

                    te1 = wj.dqwb(set2);
                    st.wb(R.id.wb10).zf(te1);
                    st.wb(R.id.wb11).zf("UID:" + wj.dqwb(a));
                    st.wb(R.id.wb6).zf("正");
                    st.wb(R.id.wb6).kjd(8);
                    st.wb(R.id.wb11).kjd(0);
                    st.tx(R.id.tx4).kjd(0);

                    if (!zf.dy(easy.getPerIcon(wj.dqwb(a)), "null")) {

                        st.tx(R.id.tx7).tx(easy.getPerIconPath(wj.dqwb(a)));
                        st.tx(R.id.tx7).kjd(0);

                    }

                    String set1 = AppPaths.appPath("settings/n1/set1.inf");

                    if (zf.dy(wj.dqwb(set1), "true")) {

                        show(0);

                    } else {

                        st.wb(R.id.wb3).zf("此账户不允许授权至第三方应用");
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
        gj.gb();

    }

    private void onTx6Click(View vw) {
        gj.xc(new Thread() {

            public void run() {

                final String ca = AppPaths.appPath("cache/req/exit");
                wj.xrwb(ca, "true");
                isExit = true;
                show(2);
                gj.zt(500);
                gj.gb();

            }

        });

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);

        ImageView i11aeec1890 = (ImageView) findViewById(ay, vw, R.id.tx4);

        ImageView i3621e3f4a1 = (ImageView) findViewById(ay, vw, R.id.tx7);

        LinearLayout i3f6c9f386b = (LinearLayout) findViewById(ay, vw, R.id.xxbj8);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i6f2c7751cf = (ImageView) findViewById(ay, vw, R.id.tx6);
        i6f2c7751cf.setOnClickListener(tx6ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
