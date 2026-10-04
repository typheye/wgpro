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
import androidx.appcompat.widget.SwitchCompat;
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

        

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(OnboardingSetupActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(OnboardingSetupActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }
        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj18)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj10)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.wb2)).setText(String.valueOf("屏宽: " + OnboardingSetupActivity.this.getResources().getDisplayMetrics().widthPixels + "\n屏高: " + OnboardingSetupActivity.this.getResources().getDisplayMetrics().heightPixels));

    }

    public void loadewm(Object qrs) {

        try {

            String qr = qrs.toString();

            ImageView imageView = findViewById(R.id.tx8);
            Bitmap bitmap = com.demo.e4.createQRCodeBitmap(qr, 800, 800, "UTF-8", "H", "1");
            imageView.setImageBitmap(bitmap);

            ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.tx8)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

        } catch (Throwable e) {

            ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.tx8)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

        }

    }

    public void csh() {

        final String a0 = AppPaths.appPath("settings/a4.inf");

        if (open.cn.awg.pro.core.NativeFiles.exists(OnboardingSetupActivity.this, (String) (a0)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(OnboardingSetupActivity.this, (String) (a0)), "1")) {

            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    final String a3 = AppPaths.appPath("settings/a3.inf");

                    final String a001 = AppPaths.appPath("settings/dpi.inf");

                    final String coltrue = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTextTrue);

                    final String colfalse = "#00000000";

                    if (open.cn.awg.pro.core.NativeFiles.exists(OnboardingSetupActivity.this, (String) (a3)) && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(OnboardingSetupActivity.this, (String) (a3)), "")) {

                        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(OnboardingSetupActivity.this, (String) (a3)), "1")) {

                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj7)), coltrue);
                                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj6)), colfalse);
                                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj5)), colfalse);
                                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj8)), colfalse);
                                    A0 = 1;

                                }

                            });
                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    android.widget.ImageView tx9 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.tx9));
                                    tx9.performClick();

                                }

                            });

                        } else if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(OnboardingSetupActivity.this, (String) (a3)), "2")) {

                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj7)), colfalse);
                                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj6)), coltrue);
                                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj5)), colfalse);
                                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj8)), colfalse);
                                    A0 = 2;

                                }

                            });
                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    android.widget.ImageView tx9 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.tx9));
                                    tx9.performClick();

                                }

                            });

                            if (open.cn.awg.pro.core.NativeFiles.exists(OnboardingSetupActivity.this, (String) (a001)) && java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(OnboardingSetupActivity.this, (String) (a001)), "true")) {

                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        android.widget.CompoundButton kg1 = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.kg1));
                                        kg1.setChecked(java.lang.Boolean.TRUE.equals(true));

                                    }

                                });

                            }
                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    android.widget.ImageView tx16 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.tx16));
                                    tx16.performClick();

                                }

                            });

                        } else if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(OnboardingSetupActivity.this, (String) (a3)), "3")) {

                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj7)), colfalse);
                                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj6)), colfalse);
                                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj5)), coltrue);
                                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj8)), colfalse);
                                    A0 = 3;

                                }

                            });
                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    android.widget.ImageView tx9 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.tx9));
                                    tx9.performClick();

                                }

                            });

                        } else if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(OnboardingSetupActivity.this, (String) (a3)), "4")) {

                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj7)), colfalse);
                                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj6)), colfalse);
                                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj5)), colfalse);
                                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj8)), coltrue);
                                    A0 = 4;

                                }

                            });
                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    android.widget.ImageView tx9 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.tx9));
                                    tx9.performClick();

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

    private void onXxbj5Click(View vw) {
        open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj5)), R.color.colorTextTrue);
        open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj6)), "#00000000");
        open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj7)), "#00000000");
        open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj8)), "#00000000");
        A0 = 1;
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.wb10)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

            }

        });

    }

    private void onXxbj6Click(View vw) {
        open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj6)), R.color.colorTextTrue);
        open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj5)), "#00000000");
        open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj7)), "#00000000");
        open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj8)), "#00000000");
        A0 = 2;
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.wb10)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

            }

        });

    }

    private void onXxbj7Click(View vw) {
        open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj7)), R.color.colorTextTrue);
        open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj6)), "#00000000");
        open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj5)), "#00000000");
        open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj8)), "#00000000");
        A0 = 3;
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.wb10)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

            }

        });

    }

    private void onXxbj8Click(View vw) {
        open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj7)), "#00000000");
        open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj6)), "#00000000");
        open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj5)), "#00000000");
        open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj8)), R.color.colorTextTrue);
        A0 = 4;
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.wb10)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

            }

        });

    }

    private void onTx15Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(OnboardingSetupActivity.this);

    }

    private void onTx9Click(View vw) {

        String a1 = "";

        String a2 = AppPaths.appPath("settings/a2.inf");

        String a3 = AppPaths.appPath("settings/a3.inf");

        String a4 = "";

        if (A0 == 0) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.wb10)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                }

            });

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.wb10)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

            if (A0 == 1) {

                a1 = "25dp";
                a4 = "1";
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                    }

                });

            } else if (A0 == 2) {

                a1 = "30dp";
                a4 = "2";
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj18)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                    }

                });

            } else if (A0 == 3) {

                a1 = "25dp";
                a4 = "3";
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                    }

                });

            } else if (A0 == 4) {

                a1 = "30dp";
                a4 = "4";
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                    }

                });

            }
            open.cn.awg.pro.core.NativeFiles.writeText(OnboardingSetupActivity.this, (String) (a2), (String) (a1));
            open.cn.awg.pro.core.NativeFiles.writeText(OnboardingSetupActivity.this, (String) (a3), (String) (a4));

        }

    }

    private void onTx14Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj18)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

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
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj18)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

            }

        });

        android.widget.CompoundButton kg = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.kg1));

        if (kg.isChecked()) {

            open.cn.awg.pro.core.NativeFiles.writeText(OnboardingSetupActivity.this, (String) (a001), (String) ("true"));

        } else {

            open.cn.awg.pro.core.NativeFiles.delete(OnboardingSetupActivity.this, (String) (a001), false);

        }

    }

    private void onTx10Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                if (A0 == 1) {

                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj18)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                } else if (A0 == 2) {

                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj18)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                } else if (A0 == 3) {

                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj18)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                } else if (A0 == 4) {

                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj18)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj2)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                }

            }

        });

    }

    private void onTx8Click(View vw) {

        String[] name = new String[]{"url", "title", "code"};

        String[] value = new String[]{AppPaths.appPath("data/assets/b0"), "用户协议", "super1"};
        open.cn.awg.pro.core.NativeActions.launch(OnboardingSetupActivity.this, (Class<?>) (TextViewerActivity.class), (String[]) (name), (Object[]) (value));

    }

    private void onTx13Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj10)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

            }

        });

    }

    private void onTx5Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj10)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj9)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

            }

        });

    }

    private void onTx6Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.launch(OnboardingSetupActivity.this, (Class<?>) (AppActivationActivity.class));

            }

        });

    }

    private void onTx7Click(View vw) {

        AppRuntimeBridge e1 = new AppRuntimeBridge(OnboardingSetupActivity.this);

        if (j1 == 0) {

            j1 = 1;

            final String a0 = AppPaths.appPath("settings/a4.inf");

            final String set66 = AppPaths.appPath("settings/set6.inf");
            open.cn.awg.pro.core.NativeFiles.writeText(OnboardingSetupActivity.this, (String) (a0), (String) ("1"));
            open.cn.awg.pro.core.NativeFiles.writeText(OnboardingSetupActivity.this, (String) (set66), (String) ("true"));
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(OnboardingSetupActivity.this.getWindow().getDecorView(), R.id.xxbj10)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                        }

                    });
                    open.cn.awg.pro.core.NativeActions.sleep(500);
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            Intent intent = lei.getPackageManager().getLaunchIntentForPackage(lei.getPackageName());
                            if (intent != null) {
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                lei.startActivity(intent);
                            }
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

        LinearLayout bd584e50cd = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj5);
        bd584e50cd.setOnClickListener(xxbj5ClickListener);

        LinearLayout i6c4a6b953f = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj6);
        i6c4a6b953f.setOnClickListener(xxbj6ClickListener);

        LinearLayout i84f04cd47e = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj7);
        i84f04cd47e.setOnClickListener(xxbj7ClickListener);

        LinearLayout i3f6c9f386b = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj8);
        i3f6c9f386b.setOnClickListener(xxbj8ClickListener);

        LinearLayout i9068f5d66e = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj4);

        ImageView i7a998f9ecb = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx15);
        i7a998f9ecb.setOnClickListener(tx15ClickListener);

        ImageView ee0ece10ae = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx9);
        ee0ece10ae.setOnClickListener(tx9ClickListener);

        ImageView ac03b05d0f = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx14);
        ac03b05d0f.setOnClickListener(tx14ClickListener);

        SwitchCompat i1bca89f073 = (SwitchCompat) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.kg1);
        i1bca89f073.setOnCheckedChangeListener(kg1CheckedChangeListener);

        ImageView a81b730195 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx16);
        a81b730195.setOnClickListener(tx16ClickListener);

        ImageView i3ab5c5fd62 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx10);
        i3ab5c5fd62.setOnClickListener(tx10ClickListener);

        ImageView cb1d655d23 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx8);
        cb1d655d23.setOnClickListener(tx8ClickListener);

        ImageView i16ad18f521 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx13);
        i16ad18f521.setOnClickListener(tx13ClickListener);

        ImageView i4a4248a0f2 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx5);
        i4a4248a0f2.setOnClickListener(tx5ClickListener);

        ImageView i6f2c7751cf = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx6);
        i6f2c7751cf.setOnClickListener(tx6ClickListener);

        ImageView i3621e3f4a1 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx7);
        i3621e3f4a1.setOnClickListener(tx7ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
