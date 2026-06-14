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

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.account.AppActivationActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.viewer.TextViewerActivity;

public class OnboardingSetupActivity extends BaseAwgActivity {

    public final OnboardingSetupActivity lei = this, 类 = this;
    public int A0 = 0;
    public int j1 = 0;
    public String yhxyurl = "https://shimo.im/docs/83tYhrjCPDqkQTvD";
    private final View.OnClickListener tx7ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx7Click(vw);
        }

    };
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
    private final View.OnClickListener tx13ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx13Click(vw);
        }

    };
    private final View.OnClickListener tx8ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx8Click(vw);
        }

    };
    private final View.OnClickListener tx10ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx10Click(vw);
        }

    };
    private final View.OnClickListener tx16ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx16Click(vw);
        }

    };
    private final CompoundButton.OnCheckedChangeListener kg1CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onKg1CheckedChanged(vw, ic);
        }

    };
    private final View.OnClickListener tx14ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx14Click(vw);
        }

    };
    private final View.OnClickListener tx9ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx9Click(vw);
        }

    };
    private final View.OnClickListener tx15ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx15Click(vw);
        }

    };
    private final View.OnClickListener xxbj8ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj8Click(vw);
        }

    };
    private final View.OnClickListener xxbj7ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj7Click(vw);
        }

    };
    private final View.OnClickListener xxbj6ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj6Click(vw);
        }

    };
    private final View.OnClickListener xxbj5ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj5Click(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.onboarding_setup);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        csh();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {
        applyFullscreenWindow();

        i.runlibrary.app.xt$pm pm = (i.runlibrary.app.xt$pm) (Object) xt.pm();

        if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4s.inf")), "false")) {

            if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4.inf")), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }
        st.xxbj(R.id.xxbj2).kjd(0);
        st.xxbj(R.id.xxbj18).kjd(8);
        st.xxbj(R.id.xxbj9).kjd(8);
        st.xxbj(R.id.xxbj10).kjd(8);
        st.wb(R.id.wb2).zf("屏宽: " + pm.k + "\n屏高: " + pm.g);

    }

    public void loadewm(Object qrs) {

        try {

            String qr = qrs.toString();

            ImageView imageView = findViewById(R.id.tx8);
            Bitmap bitmap = com.demo.e4.createQRCodeBitmap(qr, 800, 800, "UTF-8", "H", "1");
            imageView.setImageBitmap(bitmap);

            st.tx(R.id.tx8).kjd(0);

        } catch (Throwable e) {

            st.tx(R.id.tx8).kjd(8);

        }

    }

    public void csh() {

        final String a0 = AppPaths.appPath("settings/a4.inf");

        if (wj.cz(a0) && zf.dy(wj.dqwb(a0), "1")) {

            gj.xc(new Thread() {

                public void run() {

                    final String a3 = AppPaths.appPath("settings/a3.inf");

                    final String a001 = AppPaths.appPath("settings/dpi.inf");

                    final String coltrue = zf.zf(R.color.colorTextTrue);

                    final String colfalse = "#00000000";

                    if (wj.cz(a3) && !zf.dy(wj.dqwb(a3), "")) {

                        if (zf.dy(wj.dqwb(a3), "1")) {

                            gj.jmxc(new Runnable() {

                                public void run() {

                                    st.xxbj(R.id.xxbj7).bj(coltrue);
                                    st.xxbj(R.id.xxbj6).bj(colfalse);
                                    st.xxbj(R.id.xxbj5).bj(colfalse);
                                    st.xxbj(R.id.xxbj8).bj(colfalse);
                                    A0 = 1;

                                }

                            });
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    i.runlibrary.app.v.tx tx9 = st.tx(R.id.tx9);
                                    tx9.v.performClick();

                                }

                            });

                        } else if (zf.dy(wj.dqwb(a3), "2")) {

                            gj.jmxc(new Runnable() {

                                public void run() {

                                    st.xxbj(R.id.xxbj7).bj(colfalse);
                                    st.xxbj(R.id.xxbj6).bj(coltrue);
                                    st.xxbj(R.id.xxbj5).bj(colfalse);
                                    st.xxbj(R.id.xxbj8).bj(colfalse);
                                    A0 = 2;

                                }

                            });
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    i.runlibrary.app.v.tx tx9 = st.tx(R.id.tx9);
                                    tx9.v.performClick();

                                }

                            });

                            if (wj.cz(a001) && zf.dy(wj.dqwb(a001), "true")) {

                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        i.runlibrary.app.v.kg kg1 = st.kg(R.id.kg1);
                                        kg1.xzzt(true);

                                    }

                                });

                            }
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    i.runlibrary.app.v.tx tx16 = st.tx(R.id.tx16);
                                    tx16.v.performClick();

                                }

                            });

                        } else if (zf.dy(wj.dqwb(a3), "3")) {

                            gj.jmxc(new Runnable() {

                                public void run() {

                                    st.xxbj(R.id.xxbj7).bj(colfalse);
                                    st.xxbj(R.id.xxbj6).bj(colfalse);
                                    st.xxbj(R.id.xxbj5).bj(coltrue);
                                    st.xxbj(R.id.xxbj8).bj(colfalse);
                                    A0 = 3;

                                }

                            });
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    i.runlibrary.app.v.tx tx9 = st.tx(R.id.tx9);
                                    tx9.v.performClick();

                                }

                            });

                        } else if (zf.dy(wj.dqwb(a3), "4")) {

                            gj.jmxc(new Runnable() {

                                public void run() {

                                    st.xxbj(R.id.xxbj7).bj(colfalse);
                                    st.xxbj(R.id.xxbj6).bj(colfalse);
                                    st.xxbj(R.id.xxbj5).bj(colfalse);
                                    st.xxbj(R.id.xxbj8).bj(coltrue);
                                    A0 = 4;

                                }

                            });
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    i.runlibrary.app.v.tx tx9 = st.tx(R.id.tx9);
                                    tx9.v.performClick();

                                }

                            });

                        }

                    }

                }

            });

        }

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {
        return true;

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

    private void onXxbj5Click(View vw) {
        st.xxbj(R.id.xxbj5).bj(R.color.colorTextTrue);
        st.xxbj(R.id.xxbj6).bj("#00000000");
        st.xxbj(R.id.xxbj7).bj("#00000000");
        st.xxbj(R.id.xxbj8).bj("#00000000");
        A0 = 1;
        gj.jmxc(new Runnable() {

            public void run() {

                st.wb(R.id.wb10).kjd(8);

            }

        });

    }

    private void onXxbj6Click(View vw) {
        st.xxbj(R.id.xxbj6).bj(R.color.colorTextTrue);
        st.xxbj(R.id.xxbj5).bj("#00000000");
        st.xxbj(R.id.xxbj7).bj("#00000000");
        st.xxbj(R.id.xxbj8).bj("#00000000");
        A0 = 2;
        gj.jmxc(new Runnable() {

            public void run() {

                st.wb(R.id.wb10).kjd(8);

            }

        });

    }

    private void onXxbj7Click(View vw) {
        st.xxbj(R.id.xxbj7).bj(R.color.colorTextTrue);
        st.xxbj(R.id.xxbj6).bj("#00000000");
        st.xxbj(R.id.xxbj5).bj("#00000000");
        st.xxbj(R.id.xxbj8).bj("#00000000");
        A0 = 3;
        gj.jmxc(new Runnable() {

            public void run() {

                st.wb(R.id.wb10).kjd(8);

            }

        });

    }

    private void onXxbj8Click(View vw) {
        st.xxbj(R.id.xxbj7).bj("#00000000");
        st.xxbj(R.id.xxbj6).bj("#00000000");
        st.xxbj(R.id.xxbj5).bj("#00000000");
        st.xxbj(R.id.xxbj8).bj(R.color.colorTextTrue);
        A0 = 4;
        gj.jmxc(new Runnable() {

            public void run() {

                st.wb(R.id.wb10).kjd(8);

            }

        });

    }

    private void onTx15Click(View vw) {
        gj.gb();

    }

    private void onTx9Click(View vw) {

        String a1 = "";

        String a2 = AppPaths.appPath("settings/a2.inf");

        String a3 = AppPaths.appPath("settings/a3.inf");

        String a4 = "";

        if (A0 == 0) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.wb10).kjd(0);

                }

            });

        } else {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.wb10).kjd(8);

                }

            });

            if (A0 == 1) {

                a1 = "25dp";
                a4 = "1";
                gj.jmxc(new Runnable() {

                    public void run() {

                        st.xxbj(R.id.xxbj2).kjd(8);
                        st.xxbj(R.id.xxbj9).kjd(0);

                    }

                });

            } else if (A0 == 2) {

                a1 = "30dp";
                a4 = "2";
                gj.jmxc(new Runnable() {

                    public void run() {

                        st.xxbj(R.id.xxbj2).kjd(8);
                        st.xxbj(R.id.xxbj18).kjd(0);

                    }

                });

            } else if (A0 == 3) {

                a1 = "25dp";
                a4 = "3";
                gj.jmxc(new Runnable() {

                    public void run() {

                        st.xxbj(R.id.xxbj2).kjd(8);
                        st.xxbj(R.id.xxbj9).kjd(0);

                    }

                });

            } else if (A0 == 4) {

                a1 = "30dp";
                a4 = "4";
                gj.jmxc(new Runnable() {

                    public void run() {

                        st.xxbj(R.id.xxbj2).kjd(8);
                        st.xxbj(R.id.xxbj9).kjd(0);

                    }

                });

            }
            wj.xrwb(a2, a1);
            wj.xrwb(a3, a4);

        }

    }

    private void onTx14Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                st.xxbj(R.id.xxbj18).kjd(8);
                st.xxbj(R.id.xxbj2).kjd(0);

            }

        });

    }

    private void onKg1CheckedChanged(CompoundButton vw, boolean ic) {

        if (ic) {

        } else {

        }

    }

    private void onTx16Click(View vw) {

        String a001 = AppPaths.appPath("settings/dpi.inf");
        gj.jmxc(new Runnable() {

            public void run() {

                st.xxbj(R.id.xxbj18).kjd(8);
                st.xxbj(R.id.xxbj9).kjd(0);

            }

        });

        i.runlibrary.app.v.kg kg = st.kg(R.id.kg1);

        if (kg.xzzt()) {

            wj.xrwb(a001, "true");

        } else {

            wj.sc(a001);

        }

    }

    private void onTx10Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                if (A0 == 1) {

                    st.xxbj(R.id.xxbj9).kjd(8);
                    st.xxbj(R.id.xxbj18).kjd(8);
                    st.xxbj(R.id.xxbj2).kjd(0);

                } else if (A0 == 2) {

                    st.xxbj(R.id.xxbj9).kjd(8);
                    st.xxbj(R.id.xxbj18).kjd(0);
                    st.xxbj(R.id.xxbj2).kjd(8);

                } else if (A0 == 3) {

                    st.xxbj(R.id.xxbj9).kjd(8);
                    st.xxbj(R.id.xxbj18).kjd(8);
                    st.xxbj(R.id.xxbj2).kjd(0);

                } else if (A0 == 4) {

                    st.xxbj(R.id.xxbj9).kjd(8);
                    st.xxbj(R.id.xxbj18).kjd(8);
                    st.xxbj(R.id.xxbj2).kjd(0);

                }

            }

        });

    }

    private void onTx8Click(View vw) {

        String[] name = new String[]{"url", "title", "code"};

        String[] value = new String[]{AppPaths.appPath("data/assets/b0"), "用户协议", "super1"};
        gj.tz(TextViewerActivity.class, name, value);

    }

    private void onTx13Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                st.xxbj(R.id.xxbj10).kjd(0);
                st.xxbj(R.id.xxbj9).kjd(8);

            }

        });

    }

    private void onTx5Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                st.xxbj(R.id.xxbj10).kjd(8);
                st.xxbj(R.id.xxbj9).kjd(0);

            }

        });

    }

    private void onTx6Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                gj.tz(AppActivationActivity.class);

            }

        });

    }

    private void onTx7Click(View vw) {

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        if (j1 == 0) {

            j1 = 1;

            final String a0 = AppPaths.appPath("settings/a4.inf");

            final String set66 = AppPaths.appPath("settings/set6.inf");
            wj.xrwb(a0, "1");
            wj.xrwb(set66, "true");
            gj.xc(new Thread() {

                public void run() {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            st.xxbj(R.id.xxbj10).kjd(8);

                        }

                    });
                    gj.zt(500);
                    gj.jmxc(new Runnable() {

                        public void run() {

                            Intent intent = lei.getPackageManager().getLaunchIntentForPackage(lei.getPackageName());
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            lei.startActivity(intent);
                            AwgProApplication.getInstance().exit();

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

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        LinearLayout bd584e50cd = (LinearLayout) findViewById(ay, vw, R.id.xxbj5);
        bd584e50cd.setOnClickListener(xxbj5ClickListener);

        LinearLayout i6c4a6b953f = (LinearLayout) findViewById(ay, vw, R.id.xxbj6);
        i6c4a6b953f.setOnClickListener(xxbj6ClickListener);

        LinearLayout i84f04cd47e = (LinearLayout) findViewById(ay, vw, R.id.xxbj7);
        i84f04cd47e.setOnClickListener(xxbj7ClickListener);

        LinearLayout i3f6c9f386b = (LinearLayout) findViewById(ay, vw, R.id.xxbj8);
        i3f6c9f386b.setOnClickListener(xxbj8ClickListener);

        LinearLayout i9068f5d66e = (LinearLayout) findViewById(ay, vw, R.id.xxbj4);

        ImageView i7a998f9ecb = (ImageView) findViewById(ay, vw, R.id.tx15);
        i7a998f9ecb.setOnClickListener(tx15ClickListener);

        ImageView ee0ece10ae = (ImageView) findViewById(ay, vw, R.id.tx9);
        ee0ece10ae.setOnClickListener(tx9ClickListener);

        ImageView ac03b05d0f = (ImageView) findViewById(ay, vw, R.id.tx14);
        ac03b05d0f.setOnClickListener(tx14ClickListener);

        SwitchCompat i1bca89f073 = (SwitchCompat) findViewById(ay, vw, R.id.kg1);
        i1bca89f073.setOnCheckedChangeListener(kg1CheckedChangeListener);

        ImageView a81b730195 = (ImageView) findViewById(ay, vw, R.id.tx16);
        a81b730195.setOnClickListener(tx16ClickListener);

        ImageView i3ab5c5fd62 = (ImageView) findViewById(ay, vw, R.id.tx10);
        i3ab5c5fd62.setOnClickListener(tx10ClickListener);

        ImageView cb1d655d23 = (ImageView) findViewById(ay, vw, R.id.tx8);
        cb1d655d23.setOnClickListener(tx8ClickListener);

        ImageView i16ad18f521 = (ImageView) findViewById(ay, vw, R.id.tx13);
        i16ad18f521.setOnClickListener(tx13ClickListener);

        ImageView i4a4248a0f2 = (ImageView) findViewById(ay, vw, R.id.tx5);
        i4a4248a0f2.setOnClickListener(tx5ClickListener);

        ImageView i6f2c7751cf = (ImageView) findViewById(ay, vw, R.id.tx6);
        i6f2c7751cf.setOnClickListener(tx6ClickListener);

        ImageView i3621e3f4a1 = (ImageView) findViewById(ay, vw, R.id.tx7);
        i3621e3f4a1.setOnClickListener(tx7ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
