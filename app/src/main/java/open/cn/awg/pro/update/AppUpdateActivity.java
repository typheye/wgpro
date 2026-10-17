/**
 ******************************************************************************
 * @file    AppUpdateActivity.java
 * @author  Typheye
 * @brief   Checks updates and opens update installation flows.
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
import android.os.Bundle;
import androidx.appcompat.widget.SwitchCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class AppUpdateActivity extends BaseAwgActivity {

    public final AppUpdateActivity lei = this, 类 = this;
    public AppRuntimeBridge e1;
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj6ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj6Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj9ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj9Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj5ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj5Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj4Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj3Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj8ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj8Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj15ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj15Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj14ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj14Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj13ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj13Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj12ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj12Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj11ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj11Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj10ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj10Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj17ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj17Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj2Click(vw);
        }

    };
    private final CompoundButton.OnCheckedChangeListener kg1CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onKg1CheckedChanged(vw, ic);
        }

    };
;
    private final View.OnClickListener xdbj7ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj7Click(vw);
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
        setContentView(R.layout.app_update);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();
        update();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.app_update_root, R.id.xdbj1, R.id.title_bar);

    }

    public void jz() {

        String set1 = AppPaths.appPath("settings/f9/set1.inf");

        android.widget.CompoundButton kg1 = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.kg1));

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(AppUpdateActivity.this, (String) (set1)), "true")) {

            kg1.setChecked(java.lang.Boolean.TRUE.equals(true));

        }

    }

    public void update() {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb17)).getText().toString(), "正在检查...")) {

            e1.tsk("提示", "操作过于频繁，请稍后重试！");

        } else {

            final String colorf = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTextU2);

            final String colort = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTextTrue);

            String set36 = AppPaths.appPath("settings/fixVersion");

            int a11;
            a11 = com.demo.e4.getVersionCode(lei);

            final int myappversion = a11;

            final String appfixversion = open.cn.awg.pro.core.TextValues.trim(open.cn.awg.pro.core.NativeFiles.readText(AppUpdateActivity.this, (String) (set36)));
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb19)).setText(String.valueOf(a11));
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb24)).setText(String.valueOf(appfixversion));

            String set2e = AppPaths.appPath("settings/f10/set2.inf");

            final String set3 = AppPaths.appPath("cache/update");
            set2e = open.cn.awg.pro.core.NativeFiles.readText(AppUpdateActivity.this, (String) (set2e));
            set2e = e1.urlUnlockString(set2e);

            if (open.cn.awg.pro.core.TextValues.endsWith(set2e, "/")) {

            } else {

                set2e = set2e + "/";

            }
            set2e = set2e + "config.json";

            final String url = set2e;
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            String e = "加载中...";
                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb5)).setText(String.valueOf(e));
                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb8)).setText(String.valueOf(e));
                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb10)).setText(String.valueOf(e));
                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb26)).setText(String.valueOf(e));
                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb28)).setText(String.valueOf(e));
                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb30)).setText(String.valueOf(e));
                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb35)).setText(String.valueOf(e));
                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb36)).setText(String.valueOf(e));
                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb17)).setText(String.valueOf("正在检查..."));
                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb35)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(colorf));
                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb36)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(colorf));
                            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj7)).setVisibility(android.view.View.GONE);
                            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj15)).setVisibility(android.view.View.GONE);

                        }

                    });

                    if (e1.yz()) {

                        String setback = "";

                        String version = "";

                        String time = "";

                        String text = "";

                        String urls = "";

                        String version2 = "";

                        String time2 = "";

                        String text2 = "";

                        String urls2 = "";

                        String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                        if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                            setback = "0";

                        } else {

                            setback = e1.htmlToText(back);

                            if (java.util.Objects.equals(setback, "0")) {

                                setback = "0";

                            } else {

                                version = e1.readJson(setback, "UpdateVersion");
                                time = e1.readJson(setback, "UpdateTime");
                                text = e1.readJson(setback, "UpdateText");
                                urls = e1.readJson(setback, "UpdateUrl");
                                urls = e1.urlUnlockString(urls);
                                version2 = e1.readJson(setback, "FixVersion");
                                time2 = e1.readJson(setback, "FixTime");
                                text2 = e1.readJson(setback, "FixText");
                                urls2 = e1.readJson(setback, "FixUrl");
                                urls2 = e1.urlUnlockString(urls2);
                                setback = String.valueOf(1);

                            }

                        }

                        if (java.util.Objects.equals(setback, "0")) {

                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    String e = "加载异常...";
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb5)).setText(String.valueOf(e));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb8)).setText(String.valueOf(e));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb10)).setText(String.valueOf(e));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb26)).setText(String.valueOf(e));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb28)).setText(String.valueOf(e));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb30)).setText(String.valueOf(e));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb35)).setText(String.valueOf(e));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb36)).setText(String.valueOf(e));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb17)).setText(String.valueOf("检查应用和补丁是否有更新"));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb35)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(colorf));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb36)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(colorf));
                                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj7)).setVisibility(android.view.View.GONE);
                                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj15)).setVisibility(android.view.View.GONE);

                                }

                            });

                        } else {

                            final int b1 = Integer.parseInt(version);

                            final String b2 = time;

                            final String b3 = text;

                            final String b4 = urls;

                            final int b5 = Integer.parseInt(version2);

                            final String b6 = time2;

                            final String b7 = text2;

                            final String b8 = urls2;
                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb5)).setText(String.valueOf(b1));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb8)).setText(String.valueOf(b2));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb10)).setText(String.valueOf("点击查看详情"));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb11)).setText(String.valueOf(b3));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb15)).setText(String.valueOf(b4));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb26)).setText(String.valueOf(b5));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb28)).setText(String.valueOf(b6));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb30)).setText(String.valueOf("点击查看详情"));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb31)).setText(String.valueOf(b7));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb37)).setText(String.valueOf(b8));
                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb17)).setText(String.valueOf("检查应用和补丁是否有更新"));

                                    if (Double.parseDouble(appfixversion) >= b5) {

                                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj15)).setVisibility(android.view.View.GONE);
                                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb35)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(colorf));
                                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb35)).setText(String.valueOf("已是最新版本"));

                                    } else {

                                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj15)).setVisibility(android.view.View.VISIBLE);
                                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb35)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(colort));
                                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb35)).setText(String.valueOf("有新版本发布"));

                                    }

                                    if (myappversion >= b1) {

                                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj7)).setVisibility(android.view.View.GONE);
                                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb36)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(colorf));
                                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb36)).setText(String.valueOf("已是最新版本"));

                                    } else {

                                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj7)).setVisibility(android.view.View.VISIBLE);
                                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb36)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(colort));
                                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb36)).setText(String.valueOf("有新版本发布"));

                                    }

                                }

                            });

                        }

                    } else {

                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                String e = "加载异常...";
                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb5)).setText(String.valueOf(e));
                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb8)).setText(String.valueOf(e));
                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb10)).setText(String.valueOf(e));
                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb26)).setText(String.valueOf(e));
                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb28)).setText(String.valueOf(e));
                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb30)).setText(String.valueOf(e));
                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb35)).setText(String.valueOf(e));
                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb36)).setText(String.valueOf(e));
                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb17)).setText(String.valueOf("检查应用和补丁是否有更新"));
                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb35)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(colorf));
                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb36)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(colorf));
                                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj7)).setVisibility(android.view.View.GONE);
                                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj15)).setVisibility(android.view.View.GONE);

                            }

                        });

                    }

                }

            });

        }

    }

    public void downing() {

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
            applyWindowModeFromSettings();

    }

    public void onRestart() {
        super.onRestart();
            applyWindowModeFromSettings();

    }

    public void onStart() {
        super.onStart();
            applyWindowModeFromSettings();

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onTitleBarClick(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(AppUpdateActivity.this);

    }

    private void onXdbj7Click(View vw) {
        update();

    }



    private void onKg1CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/f9/set1.inf");

        if (ic) {

            open.cn.awg.pro.core.NativeFiles.writeText(AppUpdateActivity.this, (String) (set), (String) ("true"));

        } else {

            open.cn.awg.pro.core.NativeFiles.writeText(AppUpdateActivity.this, (String) (set), (String) ("false"));

        }

    }

    private void onXdbj2Click(View vw) {

        android.widget.CompoundButton kg = ((android.widget.CompoundButton) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.kg1));

        if (!kg.isChecked()) {

            kg.setChecked(java.lang.Boolean.TRUE.equals(true));

        } else {

            kg.setChecked(java.lang.Boolean.TRUE.equals(false));

        }

    }



    private void onXdbj17Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbjgxbd)).getVisibility() == android.view.View.GONE) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeViewValues.margin(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj22)), 1, "10dp");
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj21)), R.drawable.setbjx_group);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj22)), R.drawable.setbjx_group);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbjgxbd)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbjgxyy)).setVisibility(android.view.View.GONE);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb39)).setText(String.valueOf("「隐藏」"));

                }

            });

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeViewValues.margin(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj22)), 1, "0dp");
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj21)), R.drawable.setbjx_top);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj22)), R.drawable.setbjx_bottom);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbjgxbd)).setVisibility(android.view.View.GONE);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb39)).setText(String.valueOf("「展开」"));

                }

            });

        }

    }



    private void onXdbj10Click(View vw) {

        int i;

    }



    private void onXdbj11Click(View vw) {

        int i;

    }



    private void onXdbj12Click(View vw) {

        int i;

    }



    private void onXdbj13Click(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb30)).getText().toString(), "点击查看详情")) {

            String t = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb31)).getText().toString();
            e1.tsk("补丁说明", t);

        } else {

            int i;

        }

    }



    private void onXdbj14Click(View vw) {

        String t = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb37)).getText().toString();

        String ce = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb26)).getText().toString();

        String[] name = new String[]{"url", "mode", "fixcode"};

        String[] value = new String[]{t, "fix", ce};
        open.cn.awg.pro.core.NativeActions.launch(AppUpdateActivity.this, (Class<?>) (AppUpdateInstallActivity.class), (String[]) (name), (Object[]) (value));
        open.cn.awg.pro.core.NativeActions.finish(AppUpdateActivity.this);

    }



    private void onXdbj15Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbjgxyy)).getVisibility() == android.view.View.GONE) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeViewValues.margin(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj22)), 1, "10dp");
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj21)), R.drawable.setbjx_group);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj22)), R.drawable.setbjx_group);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbjgxyy)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbjgxbd)).setVisibility(android.view.View.GONE);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb40)).setText(String.valueOf("「隐藏」"));

                }

            });

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeViewValues.margin(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj22)), 1, "0dp");
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj21)), R.drawable.setbjx_top);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbj22)), R.drawable.setbjx_bottom);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.xxbjgxyy)).setVisibility(android.view.View.GONE);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb40)).setText(String.valueOf("「展开」"));

                }

            });

        }

    }



    private void onXdbj8Click(View vw) {

        int i;

    }



    private void onXdbj3Click(View vw) {

        int i;

    }



    private void onXdbj4Click(View vw) {

        int i;

    }



    private void onXdbj5Click(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb10)).getText().toString(), "点击查看详情")) {

            String t = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb11)).getText().toString();
            e1.tsk("更新日志", t);

        } else {

            int i;

        }

    }



    private void onXdbj9Click(View vw) {

        String t = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb15)).getText().toString();

        String[] name = new String[]{"url", "mode"};

        String[] value = new String[]{t, "update"};
        open.cn.awg.pro.core.NativeActions.launch(AppUpdateActivity.this, (Class<?>) (AppUpdateInstallActivity.class), (String[]) (name), (Object[]) (value));
        open.cn.awg.pro.core.NativeActions.finish(AppUpdateActivity.this);

    }



    private void onXdbj6Click(View vw) {

        String t = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AppUpdateActivity.this.getWindow().getDecorView(), R.id.wb15)).getText().toString();
        e1.tsk_QR("更新链接", "扫描二维码\n下载最新版腕管Pro", t);

    }



    private void onTx1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(AppUpdateActivity.this);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        RelativeLayout e585d94488 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj7);
        e585d94488.setOnClickListener(xdbj7ClickListener);

        SwitchCompat i1bca89f073 = (SwitchCompat) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.kg1);
        i1bca89f073.setOnCheckedChangeListener(kg1CheckedChangeListener);

        RelativeLayout i3167b45174 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);

        TextView i3b389d6120 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb22);

        RelativeLayout i8890b048c8 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj17);
        i8890b048c8.setOnClickListener(xdbj17ClickListener);

        TextView f0afba5bfe = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb39);

        RelativeLayout i5b34eede7f = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj10);
        i5b34eede7f.setOnClickListener(xdbj10ClickListener);

        RelativeLayout i67b461495f = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj11);
        i67b461495f.setOnClickListener(xdbj11ClickListener);

        RelativeLayout f73b53def9 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj12);
        f73b53def9.setOnClickListener(xdbj12ClickListener);

        RelativeLayout i62f27ff563 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj13);
        i62f27ff563.setOnClickListener(xdbj13ClickListener);

        RelativeLayout c4cbe9d7ff = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj14);
        c4cbe9d7ff.setOnClickListener(xdbj14ClickListener);

        TextView i81d18382af = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.w22b);

        RelativeLayout i6834408ef9 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj15);
        i6834408ef9.setOnClickListener(xdbj15ClickListener);

        TextView i3d78c07838 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb40);

        RelativeLayout fab146d7a5 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj8);
        fab146d7a5.setOnClickListener(xdbj8ClickListener);

        RelativeLayout i20c2777fab = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);

        RelativeLayout c0d3e00d2b = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj5);
        c0d3e00d2b.setOnClickListener(xdbj5ClickListener);

        RelativeLayout i5a93676674 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj9);
        i5a93676674.setOnClickListener(xdbj9ClickListener);

        RelativeLayout i171cedf1eb = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj6);
        i171cedf1eb.setOnClickListener(xdbj6ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
