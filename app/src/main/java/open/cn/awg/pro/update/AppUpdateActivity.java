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
import android.support.v7.widget.SwitchCompat;
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
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
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
    private final View.OnLongClickListener xdbj9LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj9LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj9ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj9Click(vw);
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
    private final View.OnLongClickListener xdbj8LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj8LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj8ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj8Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj15LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj15LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj15ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj15Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj14LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj14LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj14ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj14Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj13LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj13LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj13ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj13Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj12LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj12LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj12ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj12Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj11LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj11LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj11ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj11Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj10LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj10LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj10ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj10Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj17LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj17LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj17ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj17Click(vw);
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
    private final CompoundButton.OnCheckedChangeListener kg1CheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onKg1CheckedChanged(vw, ic);
        }

    };
    private final View.OnLongClickListener xdbj7LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj7LongClick(vw);
        }

    };
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

        i.runlibrary.app.v.kg kg1 = st.kg(R.id.kg1);

        if (zf.dy(wj.dqwb(set1), "true")) {

            kg1.xzzt(true);

        }

    }

    public void update() {

        if (zf.dy(st.wb(R.id.wb17).zf(), "正在检查...")) {

            e1.tsk("提示", "操作过于频繁，请稍后重试！");

        } else {

            final String colorf = zf.zf(R.color.colorTextU2);

            final String colort = zf.zf(R.color.colorTextTrue);

            String set36 = AppPaths.appPath("settings/fixVersion");

            int a11;
            a11 = com.demo.e4.getVersionCode(lei);

            final int myappversion = a11;

            final String appfixversion = zf.qctwkg(wj.dqwb(set36));
            st.wb(R.id.wb19).zf(a11);
            st.wb(R.id.wb24).zf(appfixversion);

            String set2e = AppPaths.appPath("settings/f10/set2.inf");

            final String set3 = AppPaths.appPath("cache/update");
            set2e = wj.dqwb(set2e);
            set2e = e1.urlUnlockString(set2e);

            if (zf.cjw(set2e, "/")) {

            } else {

                set2e = set2e + "/";

            }
            set2e = set2e + "config.json";

            final String url = set2e;
            gj.xc(new Thread() {

                public void run() {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            String e = "加载中...";
                            st.wb(R.id.wb5).zf(e);
                            st.wb(R.id.wb8).zf(e);
                            st.wb(R.id.wb10).zf(e);
                            st.wb(R.id.wb26).zf(e);
                            st.wb(R.id.wb28).zf(e);
                            st.wb(R.id.wb30).zf(e);
                            st.wb(R.id.wb35).zf(e);
                            st.wb(R.id.wb36).zf(e);
                            st.wb(R.id.wb17).zf("正在检查...");
                            st.wb(R.id.wb35).zfys(colorf);
                            st.wb(R.id.wb36).zfys(colorf);
                            st.xxbj(R.id.xxbj7).kjd(8);
                            st.xxbj(R.id.xxbj15).kjd(8);

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

                        String back = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                        if (zf.dy(back, "") || zf.dy(back, null)) {

                            setback = "0";

                        } else {

                            setback = e1.htmlToText(back);

                            if (zf.dy(setback, "0")) {

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

                        if (zf.dy(setback, "0")) {

                            gj.jmxc(new Runnable() {

                                public void run() {

                                    String e = "加载异常...";
                                    st.wb(R.id.wb5).zf(e);
                                    st.wb(R.id.wb8).zf(e);
                                    st.wb(R.id.wb10).zf(e);
                                    st.wb(R.id.wb26).zf(e);
                                    st.wb(R.id.wb28).zf(e);
                                    st.wb(R.id.wb30).zf(e);
                                    st.wb(R.id.wb35).zf(e);
                                    st.wb(R.id.wb36).zf(e);
                                    st.wb(R.id.wb17).zf("检查应用和补丁是否有更新");
                                    st.wb(R.id.wb35).zfys(colorf);
                                    st.wb(R.id.wb36).zfys(colorf);
                                    st.xxbj(R.id.xxbj7).kjd(8);
                                    st.xxbj(R.id.xxbj15).kjd(8);

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
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    st.wb(R.id.wb5).zf(b1);
                                    st.wb(R.id.wb8).zf(b2);
                                    st.wb(R.id.wb10).zf("点击查看详情");
                                    st.wb(R.id.wb11).zf(b3);
                                    st.wb(R.id.wb15).zf(b4);
                                    st.wb(R.id.wb26).zf(b5);
                                    st.wb(R.id.wb28).zf(b6);
                                    st.wb(R.id.wb30).zf("点击查看详情");
                                    st.wb(R.id.wb31).zf(b7);
                                    st.wb(R.id.wb37).zf(b8);
                                    st.wb(R.id.wb17).zf("检查应用和补丁是否有更新");

                                    if (Double.parseDouble(appfixversion) >= b5) {

                                        st.xxbj(R.id.xxbj15).kjd(8);
                                        st.wb(R.id.wb35).zfys(colorf);
                                        st.wb(R.id.wb35).zf("已是最新版本");

                                    } else {

                                        st.xxbj(R.id.xxbj15).kjd(0);
                                        st.wb(R.id.wb35).zfys(colort);
                                        st.wb(R.id.wb35).zf("有新版本发布");

                                    }

                                    if (myappversion >= b1) {

                                        st.xxbj(R.id.xxbj7).kjd(8);
                                        st.wb(R.id.wb36).zfys(colorf);
                                        st.wb(R.id.wb36).zf("已是最新版本");

                                    } else {

                                        st.xxbj(R.id.xxbj7).kjd(0);
                                        st.wb(R.id.wb36).zfys(colort);
                                        st.wb(R.id.wb36).zf("有新版本发布");

                                    }

                                }

                            });

                        }

                    } else {

                        gj.jmxc(new Runnable() {

                            public void run() {

                                String e = "加载异常...";
                                st.wb(R.id.wb5).zf(e);
                                st.wb(R.id.wb8).zf(e);
                                st.wb(R.id.wb10).zf(e);
                                st.wb(R.id.wb26).zf(e);
                                st.wb(R.id.wb28).zf(e);
                                st.wb(R.id.wb30).zf(e);
                                st.wb(R.id.wb35).zf(e);
                                st.wb(R.id.wb36).zf(e);
                                st.wb(R.id.wb17).zf("检查应用和补丁是否有更新");
                                st.wb(R.id.wb35).zfys(colorf);
                                st.wb(R.id.wb36).zfys(colorf);
                                st.xxbj(R.id.xxbj7).kjd(8);
                                st.xxbj(R.id.xxbj15).kjd(8);

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

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onTitleBarClick(View vw) {
        gj.gb();

    }

    private void onXdbj7Click(View vw) {
        update();

    }

    private boolean onXdbj7LongClick(View vw) {

        String te = st.wb(R.id.wb16).zf();

        String lj = st.wb(R.id.wb17).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onKg1CheckedChanged(CompoundButton vw, boolean ic) {

        String set = AppPaths.appPath("settings/f9/set1.inf");

        if (ic) {

            wj.xrwb(set, "true");

        } else {

            wj.xrwb(set, "false");

        }

    }

    private void onXdbj2Click(View vw) {

        i.runlibrary.app.v.kg kg = st.kg(R.id.kg1);

        if (!kg.xzzt()) {

            kg.xzzt(true);

        } else {

            kg.xzzt(false);

        }

    }

    private boolean onXdbj2LongClick(View vw) {

        String te = st.wb(R.id.wb2).zf();

        String lj = st.wb(R.id.wb3).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj17Click(View vw) {

        if (st.xxbj(R.id.xxbjgxbd).kjd() == 8) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.xxbj(R.id.xxbj22).wbjsb("10dp");
                    st.xxbj(R.id.xxbj21).bj(R.drawable.setbjx_group);
                    st.xxbj(R.id.xxbj22).bj(R.drawable.setbjx_group);
                    st.xxbj(R.id.xxbjgxbd).kjd(0);
                    st.xxbj(R.id.xxbjgxyy).kjd(8);
                    st.wb(R.id.wb39).zf("「隐藏」");

                }

            });

        } else {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.xxbj(R.id.xxbj22).wbjsb("0dp");
                    st.xxbj(R.id.xxbj21).bj(R.drawable.setbjx_top);
                    st.xxbj(R.id.xxbj22).bj(R.drawable.setbjx_bottom);
                    st.xxbj(R.id.xxbjgxbd).kjd(8);
                    st.wb(R.id.wb39).zf("「展开」");

                }

            });

        }

    }

    private boolean onXdbj17LongClick(View vw) {

        String te = st.wb(R.id.wb22).zf();

        String lj = st.wb(R.id.wb35).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj10Click(View vw) {

        int i;

    }

    private boolean onXdbj10LongClick(View vw) {

        String te = st.wb(R.id.wb23).zf();

        String lj = st.wb(R.id.wb24).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj11Click(View vw) {

        int i;

    }

    private boolean onXdbj11LongClick(View vw) {

        String te = st.wb(R.id.wb25).zf();

        String lj = st.wb(R.id.wb26).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj12Click(View vw) {

        int i;

    }

    private boolean onXdbj12LongClick(View vw) {

        String te = st.wb(R.id.wb27).zf();

        String lj = st.wb(R.id.wb28).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj13Click(View vw) {

        if (zf.dy(st.wb(R.id.wb30).zf(), "点击查看详情")) {

            String t = st.wb(R.id.wb31).zf();
            e1.tsk("补丁说明", t);

        } else {

            int i;

        }

    }

    private boolean onXdbj13LongClick(View vw) {

        String te = st.wb(R.id.wb29).zf();

        String lj = st.wb(R.id.wb30).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj14Click(View vw) {

        String t = st.wb(R.id.wb37).zf();

        String ce = st.wb(R.id.wb26).zf();

        String[] name = new String[]{"url", "mode", "fixcode"};

        String[] value = new String[]{t, "fix", ce};
        gj.tz(AppUpdateInstallActivity.class, name, value);
        gj.gb();

    }

    private boolean onXdbj14LongClick(View vw) {

        String te = st.wb(R.id.wb33).zf();

        String lj = st.wb(R.id.wb34).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj15Click(View vw) {

        if (st.xxbj(R.id.xxbjgxyy).kjd() == 8) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.xxbj(R.id.xxbj22).wbjsb("10dp");
                    st.xxbj(R.id.xxbj21).bj(R.drawable.setbjx_group);
                    st.xxbj(R.id.xxbj22).bj(R.drawable.setbjx_group);
                    st.xxbj(R.id.xxbjgxyy).kjd(0);
                    st.xxbj(R.id.xxbjgxbd).kjd(8);
                    st.wb(R.id.wb40).zf("「隐藏」");

                }

            });

        } else {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.xxbj(R.id.xxbj22).wbjsb("0dp");
                    st.xxbj(R.id.xxbj21).bj(R.drawable.setbjx_top);
                    st.xxbj(R.id.xxbj22).bj(R.drawable.setbjx_bottom);
                    st.xxbj(R.id.xxbjgxyy).kjd(8);
                    st.wb(R.id.wb40).zf("「展开」");

                }

            });

        }

    }

    private boolean onXdbj15LongClick(View vw) {

        String te = st.wb(R.id.w22b).zf();

        String lj = st.wb(R.id.wb36).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj8Click(View vw) {

        int i;

    }

    private boolean onXdbj8LongClick(View vw) {

        String te = st.wb(R.id.wb18).zf();

        String lj = st.wb(R.id.wb19).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj3Click(View vw) {

        int i;

    }

    private boolean onXdbj3LongClick(View vw) {

        String te = st.wb(R.id.wb4).zf();

        String lj = st.wb(R.id.wb5).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj4Click(View vw) {

        int i;

    }

    private boolean onXdbj4LongClick(View vw) {

        String te = st.wb(R.id.wb7).zf();

        String lj = st.wb(R.id.wb8).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj5Click(View vw) {

        if (zf.dy(st.wb(R.id.wb10).zf(), "点击查看详情")) {

            String t = st.wb(R.id.wb11).zf();
            e1.tsk("更新日志", t);

        } else {

            int i;

        }

    }

    private boolean onXdbj5LongClick(View vw) {

        String te = st.wb(R.id.wb9).zf();

        String lj = st.wb(R.id.wb10).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj9Click(View vw) {

        String t = st.wb(R.id.wb15).zf();

        String[] name = new String[]{"url", "mode"};

        String[] value = new String[]{t, "update"};
        gj.tz(AppUpdateInstallActivity.class, name, value);
        gj.gb();

    }

    private boolean onXdbj9LongClick(View vw) {

        String te = st.wb(R.id.wb20).zf();

        String lj = st.wb(R.id.wb21).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj6Click(View vw) {

        String t = st.wb(R.id.wb15).zf();
        e1.tsk_QR("更新链接", "扫描二维码\n下载最新版腕管Pro", t);

    }

    private boolean onXdbj6LongClick(View vw) {

        String te = st.wb(R.id.wb13).zf();

        String lj = st.wb(R.id.wb14).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx1Click(View vw) {
        gj.gb();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        RelativeLayout e585d94488 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj7);
        e585d94488.setOnClickListener(xdbj7ClickListener);
        e585d94488.setOnLongClickListener(xdbj7LongClickListener);

        SwitchCompat i1bca89f073 = (SwitchCompat) findViewById(ay, vw, R.id.kg1);
        i1bca89f073.setOnCheckedChangeListener(kg1CheckedChangeListener);

        RelativeLayout i3167b45174 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);
        i3167b45174.setOnLongClickListener(xdbj2LongClickListener);

        TextView i3b389d6120 = (TextView) findViewById(ay, vw, R.id.wb22);

        RelativeLayout i8890b048c8 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj17);
        i8890b048c8.setOnClickListener(xdbj17ClickListener);
        i8890b048c8.setOnLongClickListener(xdbj17LongClickListener);

        TextView f0afba5bfe = (TextView) findViewById(ay, vw, R.id.wb39);

        RelativeLayout i5b34eede7f = (RelativeLayout) findViewById(ay, vw, R.id.xdbj10);
        i5b34eede7f.setOnClickListener(xdbj10ClickListener);
        i5b34eede7f.setOnLongClickListener(xdbj10LongClickListener);

        RelativeLayout i67b461495f = (RelativeLayout) findViewById(ay, vw, R.id.xdbj11);
        i67b461495f.setOnClickListener(xdbj11ClickListener);
        i67b461495f.setOnLongClickListener(xdbj11LongClickListener);

        RelativeLayout f73b53def9 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj12);
        f73b53def9.setOnClickListener(xdbj12ClickListener);
        f73b53def9.setOnLongClickListener(xdbj12LongClickListener);

        RelativeLayout i62f27ff563 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj13);
        i62f27ff563.setOnClickListener(xdbj13ClickListener);
        i62f27ff563.setOnLongClickListener(xdbj13LongClickListener);

        RelativeLayout c4cbe9d7ff = (RelativeLayout) findViewById(ay, vw, R.id.xdbj14);
        c4cbe9d7ff.setOnClickListener(xdbj14ClickListener);
        c4cbe9d7ff.setOnLongClickListener(xdbj14LongClickListener);

        TextView i81d18382af = (TextView) findViewById(ay, vw, R.id.w22b);

        RelativeLayout i6834408ef9 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj15);
        i6834408ef9.setOnClickListener(xdbj15ClickListener);
        i6834408ef9.setOnLongClickListener(xdbj15LongClickListener);

        TextView i3d78c07838 = (TextView) findViewById(ay, vw, R.id.wb40);

        RelativeLayout fab146d7a5 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj8);
        fab146d7a5.setOnClickListener(xdbj8ClickListener);
        fab146d7a5.setOnLongClickListener(xdbj8LongClickListener);

        RelativeLayout i20c2777fab = (RelativeLayout) findViewById(ay, vw, R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);
        i20c2777fab.setOnLongClickListener(xdbj3LongClickListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) findViewById(ay, vw, R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);
        i2202ede8ff.setOnLongClickListener(xdbj4LongClickListener);

        RelativeLayout c0d3e00d2b = (RelativeLayout) findViewById(ay, vw, R.id.xdbj5);
        c0d3e00d2b.setOnClickListener(xdbj5ClickListener);
        c0d3e00d2b.setOnLongClickListener(xdbj5LongClickListener);

        RelativeLayout i5a93676674 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj9);
        i5a93676674.setOnClickListener(xdbj9ClickListener);
        i5a93676674.setOnLongClickListener(xdbj9LongClickListener);

        RelativeLayout i171cedf1eb = (RelativeLayout) findViewById(ay, vw, R.id.xdbj6);
        i171cedf1eb.setOnClickListener(xdbj6ClickListener);
        i171cedf1eb.setOnLongClickListener(xdbj6LongClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
