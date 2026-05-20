/**
 ******************************************************************************
 * @file    OnboardingSetupActivity.java
 * @author  Typheye
 * @brief   Initial setup, device selection, agreement, and activation entry screen.
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
package open.cn.awg.pro.ui.main;

import android.graphics.Bitmap;
import android.view.View;
import android.view.Window;

import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;
import open.cn.awg.pro.account.AppActivationActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.R;
import open.cn.awg.pro.viewer.TextViewerActivity;


public class OnboardingSetupActivity extends iActivity {

    public final OnboardingSetupActivity lei = this, 类 = this;
    public int A0 = 0;
    public int j1 = 0;
    public java.lang.String yhxyurl = "https://shimo.im/docs/83tYhrjCPDqkQTvD";
    private final android.view.View.OnClickListener $_on_setOnClickListener_i3621e3f4a1 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i3621e3f4a1(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i6f2c7751cf = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i6f2c7751cf(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i4a4248a0f2 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i4a4248a0f2(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i16ad18f521 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i16ad18f521(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_cb1d655d23 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_cb1d655d23(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i3ab5c5fd62 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i3ab5c5fd62(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_a81b730195 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_a81b730195(vw);
        }

    };
    private final android.widget.CompoundButton.OnCheckedChangeListener $_on_setOnCheckedChangeListener_i1bca89f073 = new android.widget.CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(android.widget.CompoundButton vw, boolean ic) {
            $_onCheckedChanged_i1bca89f073(vw, ic);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_ac03b05d0f = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_ac03b05d0f(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_ee0ece10ae = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_ee0ece10ae(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i7a998f9ecb = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i7a998f9ecb(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i3f6c9f386b = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i3f6c9f386b(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i84f04cd47e = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i84f04cd47e(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i6c4a6b953f = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i6c4a6b953f(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_bd584e50cd = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_bd584e50cd(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.a2);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.app.AwgProApplication.getInstance().addActivity(lei);
        csh();

    }

    public void __layoutIsLoaded(android.app.Activity ay, android.view.View vw) {

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        i.runlibrary.app.xt$pm pm = xt.pm();

        if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4s.inf"), "false")) {

            if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4.inf"), "true")) {

                getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO);

            }

        }
        st.xxbj(open.cn.awg.pro.R.id.xxbj2).kjd(0);
        st.xxbj(open.cn.awg.pro.R.id.xxbj18).kjd(8);
        st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
        st.xxbj(open.cn.awg.pro.R.id.xxbj10).kjd(8);
        st.wb(open.cn.awg.pro.R.id.wb2).zf("屏宽: " + pm.k + "\n屏高: " + pm.g);

    }

    public void loadewm(java.lang.Object qrs) {

        try {

            java.lang.String qr = qrs.toString();

            android.widget.ImageView imageView = findViewById(R.id.tx8);
            Bitmap bitmap = com.demo.e4.createQRCodeBitmap(qr, 800, 800, "UTF-8", "H", "1");
            imageView.setImageBitmap(bitmap);

            st.tx(open.cn.awg.pro.R.id.tx8).kjd(0);

        } catch (java.lang.Throwable e) {

            st.tx(open.cn.awg.pro.R.id.tx8).kjd(8);

        }

    }

    public void csh() {

        final java.lang.String a0 = "/data/user/0/open.cn.awg.pro/settings/a4.inf";

        if (wj.cz(a0) && zf.dy(wj.dqwb(a0), "1")) {

            gj.xc(new java.lang.Thread() {

                public void run() {

                    final java.lang.String a3 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

                    final java.lang.String a001 = "/data/user/0/open.cn.awg.pro/settings/dpi.inf";

                    final java.lang.String coltrue = zf.zf(open.cn.awg.pro.R.color.colorTextTrue);

                    final java.lang.String colfalse = "#00000000";

                    if (wj.cz(a3) && !zf.dy(wj.dqwb(a3), "")) {

                        if (zf.dy(wj.dqwb(a3), "1")) {

                            gj.jmxc(new java.lang.Runnable() {

                                public void run() {

                                    st.xxbj(open.cn.awg.pro.R.id.xxbj7).bj(coltrue);
                                    st.xxbj(open.cn.awg.pro.R.id.xxbj6).bj(colfalse);
                                    st.xxbj(open.cn.awg.pro.R.id.xxbj5).bj(colfalse);
                                    st.xxbj(open.cn.awg.pro.R.id.xxbj8).bj(colfalse);
                                    A0 = 1;

                                }

                            });
                            gj.jmxc(new java.lang.Runnable() {

                                public void run() {

                                    i.runlibrary.app.v.tx tx9 = st.tx(open.cn.awg.pro.R.id.tx9);
                                    tx9.v.performClick();

                                }

                            });

                        } else if (zf.dy(wj.dqwb(a3), "2")) {

                            gj.jmxc(new java.lang.Runnable() {

                                public void run() {

                                    st.xxbj(open.cn.awg.pro.R.id.xxbj7).bj(colfalse);
                                    st.xxbj(open.cn.awg.pro.R.id.xxbj6).bj(coltrue);
                                    st.xxbj(open.cn.awg.pro.R.id.xxbj5).bj(colfalse);
                                    st.xxbj(open.cn.awg.pro.R.id.xxbj8).bj(colfalse);
                                    A0 = 2;

                                }

                            });
                            gj.jmxc(new java.lang.Runnable() {

                                public void run() {

                                    i.runlibrary.app.v.tx tx9 = st.tx(open.cn.awg.pro.R.id.tx9);
                                    tx9.v.performClick();

                                }

                            });

                            if (wj.cz(a001) && zf.dy(wj.dqwb(a001), "true")) {

                                gj.jmxc(new java.lang.Runnable() {

                                    public void run() {

                                        i.runlibrary.app.v.kg kg1 = st.kg(open.cn.awg.pro.R.id.kg1);
                                        kg1.xzzt(true);

                                    }

                                });

                            }
                            gj.jmxc(new java.lang.Runnable() {

                                public void run() {

                                    i.runlibrary.app.v.tx tx16 = st.tx(open.cn.awg.pro.R.id.tx16);
                                    tx16.v.performClick();

                                }

                            });

                        } else if (zf.dy(wj.dqwb(a3), "3")) {

                            gj.jmxc(new java.lang.Runnable() {

                                public void run() {

                                    st.xxbj(open.cn.awg.pro.R.id.xxbj7).bj(colfalse);
                                    st.xxbj(open.cn.awg.pro.R.id.xxbj6).bj(colfalse);
                                    st.xxbj(open.cn.awg.pro.R.id.xxbj5).bj(coltrue);
                                    st.xxbj(open.cn.awg.pro.R.id.xxbj8).bj(colfalse);
                                    A0 = 3;

                                }

                            });
                            gj.jmxc(new java.lang.Runnable() {

                                public void run() {

                                    i.runlibrary.app.v.tx tx9 = st.tx(open.cn.awg.pro.R.id.tx9);
                                    tx9.v.performClick();

                                }

                            });

                        } else if (zf.dy(wj.dqwb(a3), "4")) {

                            gj.jmxc(new java.lang.Runnable() {

                                public void run() {

                                    st.xxbj(open.cn.awg.pro.R.id.xxbj7).bj(colfalse);
                                    st.xxbj(open.cn.awg.pro.R.id.xxbj6).bj(colfalse);
                                    st.xxbj(open.cn.awg.pro.R.id.xxbj5).bj(colfalse);
                                    st.xxbj(open.cn.awg.pro.R.id.xxbj8).bj(coltrue);
                                    A0 = 4;

                                }

                            });
                            gj.jmxc(new java.lang.Runnable() {

                                public void run() {

                                    i.runlibrary.app.v.tx tx9 = st.tx(open.cn.awg.pro.R.id.tx9);
                                    tx9.v.performClick();

                                }

                            });

                        }

                    }

                }

            });

        }

    }

    public boolean onKeyDown(int kc, android.view.KeyEvent ke) {
        return true;

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public void onRestart() {
        super.onRestart();

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public void onStart() {
        super.onStart();

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(open.cn.awg.pro.R.id.qtgd1);

        qtgd1.v.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View vw, android.view.MotionEvent me) {
                if (me.getAction() == android.view.MotionEvent.ACTION_SCROLL && me.isFromSource(androidx.core.view.InputDeviceCompat.SOURCE_ROTARY_ENCODER)) {

                    float delta = -me.getAxisValue(androidx.core.view.MotionEventCompat.AXIS_SCROLL) *
                            androidx.core.view.ViewConfigurationCompat.getScaledVerticalScrollFactor(
                                    android.view.ViewConfiguration.get(lei), lei
                            );

                    vw.scrollBy(0, java.lang.Math.round(delta));
                    return true;
                }
                return false;
            }
        });

        qtgd1.v.requestFocus();

    }

    public void onResume() {
        super.onResume();

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.app.AwgProApplication.getInstance().removeActivity(lei);

    }

    private void $_onClick_bd584e50cd(android.view.View vw) {
        st.xxbj(open.cn.awg.pro.R.id.xxbj5).bj(open.cn.awg.pro.R.color.colorTextTrue);
        st.xxbj(open.cn.awg.pro.R.id.xxbj6).bj("#00000000");
        st.xxbj(open.cn.awg.pro.R.id.xxbj7).bj("#00000000");
        st.xxbj(open.cn.awg.pro.R.id.xxbj8).bj("#00000000");
        A0 = 1;
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.wb(open.cn.awg.pro.R.id.wb10).kjd(8);

            }

        });

    }

    private void $_onClick_i6c4a6b953f(android.view.View vw) {
        st.xxbj(open.cn.awg.pro.R.id.xxbj6).bj(open.cn.awg.pro.R.color.colorTextTrue);
        st.xxbj(open.cn.awg.pro.R.id.xxbj5).bj("#00000000");
        st.xxbj(open.cn.awg.pro.R.id.xxbj7).bj("#00000000");
        st.xxbj(open.cn.awg.pro.R.id.xxbj8).bj("#00000000");
        A0 = 2;
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.wb(open.cn.awg.pro.R.id.wb10).kjd(8);

            }

        });

    }

    private void $_onClick_i84f04cd47e(android.view.View vw) {
        st.xxbj(open.cn.awg.pro.R.id.xxbj7).bj(open.cn.awg.pro.R.color.colorTextTrue);
        st.xxbj(open.cn.awg.pro.R.id.xxbj6).bj("#00000000");
        st.xxbj(open.cn.awg.pro.R.id.xxbj5).bj("#00000000");
        st.xxbj(open.cn.awg.pro.R.id.xxbj8).bj("#00000000");
        A0 = 3;
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.wb(open.cn.awg.pro.R.id.wb10).kjd(8);

            }

        });

    }

    private void $_onClick_i3f6c9f386b(android.view.View vw) {
        st.xxbj(open.cn.awg.pro.R.id.xxbj7).bj("#00000000");
        st.xxbj(open.cn.awg.pro.R.id.xxbj6).bj("#00000000");
        st.xxbj(open.cn.awg.pro.R.id.xxbj5).bj("#00000000");
        st.xxbj(open.cn.awg.pro.R.id.xxbj8).bj(open.cn.awg.pro.R.color.colorTextTrue);
        A0 = 4;
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.wb(open.cn.awg.pro.R.id.wb10).kjd(8);

            }

        });

    }

    private void $_onClick_i7a998f9ecb(android.view.View vw) {
        gj.gb();

    }

    private void $_onClick_ee0ece10ae(android.view.View vw) {

        java.lang.String a1 = "";

        java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a2.inf";

        java.lang.String a3 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a4 = "";

        if (A0 == 0) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.wb(open.cn.awg.pro.R.id.wb10).kjd(0);

                }

            });

        } else {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.wb(open.cn.awg.pro.R.id.wb10).kjd(8);

                }

            });

            if (A0 == 1) {

                a1 = "25dp";
                a4 = "1";
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.xxbj(open.cn.awg.pro.R.id.xxbj2).kjd(8);
                        st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(0);

                    }

                });

            } else if (A0 == 2) {

                a1 = "30dp";
                a4 = "2";
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.xxbj(open.cn.awg.pro.R.id.xxbj2).kjd(8);
                        st.xxbj(open.cn.awg.pro.R.id.xxbj18).kjd(0);

                    }

                });

            } else if (A0 == 3) {

                a1 = "25dp";
                a4 = "3";
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.xxbj(open.cn.awg.pro.R.id.xxbj2).kjd(8);
                        st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(0);

                    }

                });

            } else if (A0 == 4) {

                a1 = "30dp";
                a4 = "4";
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.xxbj(open.cn.awg.pro.R.id.xxbj2).kjd(8);
                        st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(0);

                    }

                });

            }
            wj.xrwb(a2, a1);
            wj.xrwb(a3, a4);

        }

    }

    private void $_onClick_ac03b05d0f(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.xxbj(open.cn.awg.pro.R.id.xxbj18).kjd(8);
                st.xxbj(open.cn.awg.pro.R.id.xxbj2).kjd(0);

            }

        });

    }

    private void $_onCheckedChanged_i1bca89f073(android.widget.CompoundButton vw, boolean ic) {

        if (ic) {

        } else {

        }

    }

    private void $_onClick_a81b730195(android.view.View vw) {

        java.lang.String a001 = "/data/user/0/open.cn.awg.pro/settings/dpi.inf";
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.xxbj(open.cn.awg.pro.R.id.xxbj18).kjd(8);
                st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(0);

            }

        });

        i.runlibrary.app.v.kg kg = st.kg(open.cn.awg.pro.R.id.kg1);

        if (kg.xzzt()) {

            wj.xrwb(a001, "true");

        } else {

            wj.sc(a001);

        }

    }

    private void $_onClick_i3ab5c5fd62(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                if (A0 == 1) {

                    st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj18).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj2).kjd(0);

                } else if (A0 == 2) {

                    st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj18).kjd(0);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj2).kjd(8);

                } else if (A0 == 3) {

                    st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj18).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj2).kjd(0);

                } else if (A0 == 4) {

                    st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj18).kjd(8);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj2).kjd(0);

                }

            }

        });

    }

    private void $_onClick_cb1d655d23(android.view.View vw) {

        java.lang.String[] name = new java.lang.String[]{"url", "title", "code"};

        java.lang.String[] value = new java.lang.String[]{"/data/user/0/open.cn.awg.pro/data/assets/b0", "用户协议", "super1"};
        gj.tz(TextViewerActivity.class, name, value);

    }

    private void $_onClick_i16ad18f521(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.xxbj(open.cn.awg.pro.R.id.xxbj10).kjd(0);
                st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);

            }

        });

    }

    private void $_onClick_i4a4248a0f2(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.xxbj(open.cn.awg.pro.R.id.xxbj10).kjd(8);
                st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(0);

            }

        });

    }

    private void $_onClick_i6f2c7751cf(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                gj.tz(AppActivationActivity.class);

            }

        });

    }

    private void $_onClick_i3621e3f4a1(android.view.View vw) {

        open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        if (j1 == 0) {

            j1 = 1;

            final java.lang.String a0 = "/data/user/0/open.cn.awg.pro/settings/a4.inf";

            final java.lang.String set66 = "/data/user/0/open.cn.awg.pro/settings/set6.inf";
            wj.xrwb(a0, "1");
            wj.xrwb(set66, "true");
            gj.xc(new java.lang.Thread() {

                public void run() {

                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            st.xxbj(open.cn.awg.pro.R.id.xxbj10).kjd(8);

                        }

                    });
                    gj.zt(500);
                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            android.content.Intent intent = lei.getPackageManager().getLaunchIntentForPackage(lei.getPackageName());
                            intent.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            lei.startActivity(intent);
                            open.cn.awg.pro.app.AwgProApplication.getInstance().exit();

                        }

                    });

                }

            });

            if (!e1.isJh() && A0 != 4) {

            }

        }

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.LinearLayout bd584e50cd = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj5);
        bd584e50cd.setOnClickListener($_on_setOnClickListener_bd584e50cd);

        android.widget.LinearLayout i6c4a6b953f = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj6);
        i6c4a6b953f.setOnClickListener($_on_setOnClickListener_i6c4a6b953f);

        android.widget.LinearLayout i84f04cd47e = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj7);
        i84f04cd47e.setOnClickListener($_on_setOnClickListener_i84f04cd47e);

        android.widget.LinearLayout i3f6c9f386b = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj8);
        i3f6c9f386b.setOnClickListener($_on_setOnClickListener_i3f6c9f386b);

        android.widget.LinearLayout i9068f5d66e = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj4);

        android.widget.ImageView i7a998f9ecb = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx15);
        i7a998f9ecb.setOnClickListener($_on_setOnClickListener_i7a998f9ecb);

        android.widget.ImageView ee0ece10ae = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx9);
        ee0ece10ae.setOnClickListener($_on_setOnClickListener_ee0ece10ae);

        android.widget.ImageView ac03b05d0f = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx14);
        ac03b05d0f.setOnClickListener($_on_setOnClickListener_ac03b05d0f);

        android.support.v7.widget.SwitchCompat i1bca89f073 = (android.support.v7.widget.SwitchCompat) findViewById(ay, vw, open.cn.awg.pro.R.id.kg1);
        i1bca89f073.setOnCheckedChangeListener($_on_setOnCheckedChangeListener_i1bca89f073);

        android.widget.ImageView a81b730195 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx16);
        a81b730195.setOnClickListener($_on_setOnClickListener_a81b730195);

        android.widget.ImageView i3ab5c5fd62 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx10);
        i3ab5c5fd62.setOnClickListener($_on_setOnClickListener_i3ab5c5fd62);

        android.widget.ImageView cb1d655d23 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx8);
        cb1d655d23.setOnClickListener($_on_setOnClickListener_cb1d655d23);

        android.widget.ImageView i16ad18f521 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx13);
        i16ad18f521.setOnClickListener($_on_setOnClickListener_i16ad18f521);

        android.widget.ImageView i4a4248a0f2 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx5);
        i4a4248a0f2.setOnClickListener($_on_setOnClickListener_i4a4248a0f2);

        android.widget.ImageView i6f2c7751cf = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx6);
        i6f2c7751cf.setOnClickListener($_on_setOnClickListener_i6f2c7751cf);

        android.widget.ImageView i3621e3f4a1 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx7);
        i3621e3f4a1.setOnClickListener($_on_setOnClickListener_i3621e3f4a1);

        __layoutIsLoaded(ay, vw);
    }
}
