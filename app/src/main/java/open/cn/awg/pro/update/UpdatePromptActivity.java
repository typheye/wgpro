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
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

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

        final String appfixversion = zf.qctwkg(wj.dqwb(set36));

        String set2e = AppPaths.appPath("settings/f10/set2.inf");

        final String set3 = AppPaths.appPath("cache/update");
        set2e = wj.dqwb(set2e);
        set2e = e1.urlUnlockString(set2e);

        if (zf.cjw(set2e, "/")) {

        } else {

            set2e = set2e + "/";

        }
        set2e = set2e + "config_v2.json";

        final String url = set2e;
        gj.xc(new Thread() {

            public void run() {

                gj.jmxc(new Runnable() {

                    public void run() {

                        String e = "Loading...";
                        st.wb(R.id.wb5).zf(e);
                        st.wb(R.id.wb8).zf(e);
                        st.wb(R.id.wb11).zf(e);

                    }

                });

                String setback = "";

                String version = "";

                String versionname = "";

                String time = "";

                String text = "";

                String urls = "";

                String allow = "";

                String back = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                if (zf.dy(back, "") || zf.dy(back, null)) {

                    setback = "0";

                } else {

                    setback = e1.htmlToText(back);

                    if (zf.dy(setback, "0")) {

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

                if (zf.dy(setback, "0")) {

                    if (wj.cz(sec_updater)) {

                        setback = wj.dqwb(sec_updater);
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
                        gj.jmxc(new Runnable() {

                            public void run() {

                                st.wb(R.id.wb5).zf(b1);
                                st.wb(R.id.wb8).zf(b2);
                                st.wb(R.id.wb11).zf(b3);

                            }

                        });

                    } else {

                        gj.jmxc(new Runnable() {

                            public void run() {

                                String e = "加载异常...";
                                st.wb(R.id.wb5).zf(e);
                                st.wb(R.id.wb8).zf(e);
                                st.wb(R.id.wb11).zf(e);

                            }

                        });

                    }

                } else {

                    final int b1 = Integer.parseInt(version);

                    final String b2 = time;

                    final String b3 = text;

                    if ((zf.dy(allow, "0") || zf.cz(allow, wj.dqwb(auid))) && e1.islogin()) {

                        if (myappversion < b1 && b1 != 9999999) {

                            wj.xrwb(sec_updater, setback);

                        } else {

                            int ccc = versionname.compareTo(myappversionname);

                            if (!zf.dy(versionname, myappversionname) && ccc > 0) {

                                wj.xrwb(sec_updater, setback);

                            } else {

                                wj.sc(sec_updater);

                            }

                        }

                    } else {

                        wj.sc(sec_updater);

                    }
                    update_url = urls;
                    ewm(update_url);
                    gj.jmxc(new Runnable() {

                        public void run() {

                            st.wb(R.id.wb5).zf(b1);
                            st.wb(R.id.wb8).zf(b2);
                            st.wb(R.id.wb11).zf(b3);

                        }

                    });

                }

            }

        });

    }

    public void ewm(String url) {

        final String qr = url;
        gj.jmxc(new Runnable() {

            public void run() {

                try {

                    ImageView imageView = findViewById(R.id.tx4);
                    Bitmap bitmap = com.demo.e4.createQRCodeBitmap(qr, 800, 800, "UTF-8", "H", "1");
                    imageView.setImageBitmap(bitmap);

                    st.tx(R.id.tx4).kjd(0);

                } catch (Throwable e) {

                    st.tx(R.id.tx4).kjd(8);

                }

            }

        });

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
        applyFullscreenWindow();

        if (zf.dy(a, "1")) {

            st.xdbj(R.id.xdbj1).nbj(u, 0, u, 50);

        } else if (zf.dy(a, "2")) {

            st.xdbj(R.id.xdbj1).nbj(0, 0, 0, 0);

        } else if (zf.dy(a, "3")) {

            st.xdbj(R.id.xdbj1).nbj(u, 0, u, 50);

        } else if (zf.dy(a, "4")) {

        }
        st.xxbj(R.id.xxbj10).kjd(0);
        st.xxbj(R.id.xxbj8).kjd(8);
        st.xxbj(R.id.xxbj6).kjd(8);
        st.xxbj(R.id.xxbj12).kjd(8);
        st.xxbj(R.id.xxbj11).kjd(8);
        st.xxbj(R.id.xxbj7).kjd(8);

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
        gj.jmxc(new Runnable() {

            public void run() {

                st.xxbj(R.id.xxbj10).kjd(8);
                st.xxbj(R.id.xxbj8).kjd(0);

            }

        });

    }

    private void onTx2Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                st.xxbj(R.id.xxbj10).kjd(0);
                st.xxbj(R.id.xxbj8).kjd(8);

            }

        });

    }

    private void onTx10Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                st.xxbj(R.id.xxbj8).kjd(8);
                st.xxbj(R.id.xxbj6).kjd(0);

            }

        });

    }

    private void onTx3Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                st.xxbj(R.id.xxbj8).kjd(0);
                st.xxbj(R.id.xxbj6).kjd(8);

            }

        });

    }

    private void onTx16Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                st.xxbj(R.id.xxbj6).kjd(8);
                st.xxbj(R.id.xxbj12).kjd(0);

            }

        });

    }

    private void onTx11Click(View vw) {
        gj.xc(new Thread() {

            public void run() {

                gj.jmxc(new Runnable() {

                    public void run() {

                        st.xxbj(R.id.xxbj6).kjd(8);
                        st.xxbj(R.id.xxbj12).kjd(8);
                        st.xxbj(R.id.xxbj11).kjd(0);

                    }

                });
                gj.zt(2000);
                gj.jmxc(new Runnable() {

                    public void run() {

                        st.xxbj(R.id.xxbj11).kjd(8);
                        st.xxbj(R.id.xxbj7).kjd(0);

                    }

                });

            }

        });

    }

    private void onTx13Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                st.xxbj(R.id.xxbj6).kjd(0);
                st.xxbj(R.id.xxbj12).kjd(8);

            }

        });

    }

    private void onTx14Click(View vw) {
        gj.xc(new Thread() {

            public void run() {

                gj.jmxc(new Runnable() {

                    public void run() {

                        st.xxbj(R.id.xxbj12).kjd(8);
                        st.xxbj(R.id.xxbj11).kjd(0);

                    }

                });
                gj.zt(500);
                gj.gb();

            }

        });

    }

    private void onTx4Click(View vw) {

        if (st.wb(R.id.wb16).kjd() == 0) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.wb16).kjd(8);
                    st.an(R.id.an1).kjd(8);

                }

            });

        } else {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.wb16).kjd(0);
                    st.an(R.id.an1).kjd(0);

                }

            });

        }

    }

    private void onAn1Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                st.xxbj(R.id.xxbj7).kjd(8);

                i.runlibrary.app.v.tx tx14 = st.tx(R.id.tx14);
                tx14.v.performClick();

            }

        });

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        ImageView i4a4248a0f2 = (ImageView) findViewById(ay, vw, R.id.tx5);
        i4a4248a0f2.setOnClickListener(tx5ClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) findViewById(ay, vw, R.id.xdbj4);

        RelativeLayout i3167b45174 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj2);

        RelativeLayout i20c2777fab = (RelativeLayout) findViewById(ay, vw, R.id.xdbj3);

        ImageView i3ab5c5fd62 = (ImageView) findViewById(ay, vw, R.id.tx10);
        i3ab5c5fd62.setOnClickListener(tx10ClickListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView a81b730195 = (ImageView) findViewById(ay, vw, R.id.tx16);
        a81b730195.setOnClickListener(tx16ClickListener);

        ImageView i75b44e9f85 = (ImageView) findViewById(ay, vw, R.id.tx11);
        i75b44e9f85.setOnClickListener(tx11ClickListener);

        ImageView i16ad18f521 = (ImageView) findViewById(ay, vw, R.id.tx13);
        i16ad18f521.setOnClickListener(tx13ClickListener);

        ImageView ac03b05d0f = (ImageView) findViewById(ay, vw, R.id.tx14);
        ac03b05d0f.setOnClickListener(tx14ClickListener);

        ImageView i11aeec1890 = (ImageView) findViewById(ay, vw, R.id.tx4);
        i11aeec1890.setOnClickListener(tx4ClickListener);

        Button i171ea34f1a = (Button) findViewById(ay, vw, R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
