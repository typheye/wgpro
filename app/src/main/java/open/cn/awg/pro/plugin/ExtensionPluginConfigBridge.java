/**
 ******************************************************************************
 * @file    ExtensionPluginConfigBridge.java
 * @author  Typheye
 * @brief   Extension plugin configuration bridge.
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
package open.cn.awg.pro.plugin;

import android.view.View;

import android.app.Activity;
import android.content.Context;

import open.cn.awg.pro.account.TypheyeServiceBridge;
import open.cn.awg.pro.chat.WearChatRuntimeBridge;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.DebugStateStore;
import open.cn.awg.pro.R;
import open.cn.awg.pro.tools.CloudServiceActivity;

public class ExtensionPluginConfigBridge {
    private final Context context;
    private final Activity owner;

    public static String titleback = "";

    public static String titleback2 = "";

    public static String id = "";

    public static String title = "";
    public final ExtensionPluginConfigBridge lei = this, 类 = this;
    public DebugStateStore debug;
    public AppRuntimeBridge e1;
    public ExtensionPluginBridge e14;
    public boolean state_login_base = false;

    public ExtensionPluginConfigBridge(Context context) {
        this.context = java.util.Objects.requireNonNull(context);
        this.owner = context instanceof Activity ? (Activity) context : null;
        debug = new DebugStateStore(context);
        e1 = new AppRuntimeBridge(context);
        e14 = new ExtensionPluginBridge(context);
    }

    public void jz() {

        String cachehd = AppPaths.appPath("data/cjlb/lbx_id");
        id = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cachehd));
        cachehd = AppPaths.appPath("data/cjlb/lbx_title");
        title = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cachehd));
        titleback = "" + title;
        titleback2 = title;
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                viewshow(1);
                open.cn.awg.pro.core.NativeActions.sleep(500);

                String cachehd = AppPaths.appPath("data/cjlb/lbx_id");

                final String id = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cachehd));

                if (e14.newG(Integer.parseInt(id))) {

                    try {

                        if (java.util.Objects.equals(id, "20000")) {

                            m20000();

                        } else if (java.util.Objects.equals(id, "20001")) {

                            m20001();

                        } else if (java.util.Objects.equals(id, "20002")) {

                            m20002();

                        } else if (java.util.Objects.equals(id, "20003")) {

                            m20003();

                        } else if (java.util.Objects.equals(id, "20004")) {

                            m20004();

                        } else if (java.util.Objects.equals(id, "20005")) {

                            m20005();

                        } else if (java.util.Objects.equals(id, "20006")) {

                            m20006();

                        } else if (java.util.Objects.equals(id, "20007")) {

                            m20007();

                        } else {

                            csh();

                        }

                    } catch (Throwable e) {

                        e1.upload_error(e, "m2.jz()");
                        open.cn.awg.pro.core.NativeActions.finish(context);

                    }

                } else {

                    open.cn.awg.pro.core.NativeActions.finish(context);

                }

            }

        });

    }

    public void m20000() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String color = "";

                final android.widget.RelativeLayout root = ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.plugin_runtime_view));
                root.removeAllViews();

                android.widget.LinearLayout xxbja = new android.widget.LinearLayout(context);
                open.cn.awg.pro.core.NativeViewValues.size(xxbja, -1, -2);
                xxbja.setOrientation(open.cn.awg.pro.core.TextValues.intValue(1, -1));
                xxbja.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                android.widget.Button an1 = new android.widget.Button(context);
                open.cn.awg.pro.core.NativeViewValues.size(an1, -1, -2);
                an1.setText(String.valueOf("本地激活应用"));
                an1.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        e1.jh();

                        final String msg = "已执行激活操作";
                        e1.tsk("提示", msg);

                    }

                });
                xxbja.addView(an1);
                open.cn.awg.pro.core.NativeViewValues.margins(an1, "3dp", "3dp", "3dp", "0dp");

                android.widget.Button an2 = new android.widget.Button(context);
                open.cn.awg.pro.core.NativeViewValues.size(an2, -1, -2);
                an2.setText(String.valueOf("取消激活应用"));
                an2.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        String a = AppPaths.appPath("settings/f7/Enable2");
                        open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (a), (String) (""));

                        final String msg = "已执行取消激活";
                        e1.tsk("提示", msg);

                    }

                });
                xxbja.addView(an2);
                open.cn.awg.pro.core.NativeViewValues.margins(an2, "3dp", "3dp", "3dp", "0dp");

                android.widget.TextView wba = new android.widget.TextView(context);
                open.cn.awg.pro.core.NativeViewValues.size(wba, -2, -2);
                open.cn.awg.pro.core.NativeViewValues.padding(wba, "3dp", -2, "3dp", -2);
                wba.setText(String.valueOf(""));
                wba.setTypeface(wba.getTypeface(), open.cn.awg.pro.core.NativeViewValues.textStyle("bold"));
                wba.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(9, -1));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                wba.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                xxbja.addView(wba);
                root.addView(xxbja);

            }

        });
        viewshow(0);

    }

    public void m20001() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String color = "";

                final android.widget.RelativeLayout root = ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.plugin_runtime_view));
                root.removeAllViews();

                android.widget.LinearLayout xxbja = new android.widget.LinearLayout(context);
                open.cn.awg.pro.core.NativeViewValues.size(xxbja, -1, -2);
                xxbja.setOrientation(open.cn.awg.pro.core.TextValues.intValue(1, -1));
                xxbja.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                final android.widget.EditText bjk1 = new android.widget.EditText(context);
                open.cn.awg.pro.core.NativeViewValues.size(bjk1, -1, -2);
                bjk1.setHint(String.valueOf("请输入您的邮箱"));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                bjk1.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                bjk1.setHintTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                bjk1.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                open.cn.awg.pro.core.NativeViewValues.padding(bjk1, "8dp", "8dp", "8dp", "8dp");
                bjk1.setSingleLine(java.lang.Boolean.TRUE.equals(true));
                xxbja.addView(bjk1);
                open.cn.awg.pro.core.NativeViewValues.margins(bjk1, "3dp", "3dp", "3dp", "0dp");

                android.widget.LinearLayout xxbjb = new android.widget.LinearLayout(context);
                open.cn.awg.pro.core.NativeViewValues.size(xxbjb, -1, -2);
                xxbjb.setOrientation(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                xxbjb.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                final android.widget.EditText bjk2 = new android.widget.EditText(context);
                open.cn.awg.pro.core.NativeViewValues.size(bjk2, -1, -2);
                bjk2.setHint(String.valueOf("请输入验证码"));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                bjk2.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                bjk2.setHintTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                bjk2.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                open.cn.awg.pro.core.NativeViewValues.padding(bjk2, "8dp", "8dp", "8dp", "8dp");
                bjk2.setSingleLine(java.lang.Boolean.TRUE.equals(true));
                xxbjb.addView(bjk2);

                android.widget.LinearLayout.LayoutParams bjk2_xxbjgz = ((android.widget.LinearLayout.LayoutParams) (Object) open.cn.awg.pro.core.NativeViewValues.linearParams(bjk2));
                open.cn.awg.pro.core.NativeViewValues.weight(bjk2, bjk2_xxbjgz, 1);

                final android.widget.Button an0 = new android.widget.Button(context);
                open.cn.awg.pro.core.NativeViewValues.size(an0, -2, -2);
                an0.setText(String.valueOf("获取"));
                an0.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        if (java.util.Objects.equals(an0.getText().toString(), "获取")) {

                            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                                public void run() {

                                    TypheyeServiceBridge easy = new TypheyeServiceBridge(context);
                                    easy.register$request$token(bjk1.getText().toString(), an0);

                                }

                            });

                        } else if (java.util.Objects.equals(an0.getText().toString(), "已发送")) {

                            e1.tsk("提示", "发送验证码成功,请在邮箱查收");

                        }

                    }

                });
                xxbjb.addView(an0);
                open.cn.awg.pro.core.NativeViewValues.margins(an0, "3dp", "0dp", "0dp", "0dp");
                xxbja.addView(xxbjb);
                open.cn.awg.pro.core.NativeViewValues.margins(xxbjb, "3dp", "3dp", "3dp", "0dp");

                final android.widget.EditText bjk3 = new android.widget.EditText(context);
                open.cn.awg.pro.core.NativeViewValues.size(bjk3, -1, -2);
                bjk3.setHint(String.valueOf("请输入您的密码"));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                bjk3.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                bjk3.setHintTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                bjk3.setInputType(open.cn.awg.pro.core.NativeViewValues.inputType("textpassword"));
                bjk3.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                open.cn.awg.pro.core.NativeViewValues.padding(bjk3, "8dp", "8dp", "8dp", "8dp");
                bjk3.setSingleLine(java.lang.Boolean.TRUE.equals(true));
                xxbja.addView(bjk3);
                open.cn.awg.pro.core.NativeViewValues.margins(bjk3, "3dp", "3dp", "3dp", "0dp");

                final android.widget.EditText bjk4 = new android.widget.EditText(context);
                open.cn.awg.pro.core.NativeViewValues.size(bjk4, -1, -2);
                bjk4.setHint(String.valueOf("请确认您的密码"));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                bjk4.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                bjk4.setHintTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                bjk4.setInputType(open.cn.awg.pro.core.NativeViewValues.inputType("textpassword"));
                bjk4.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                open.cn.awg.pro.core.NativeViewValues.padding(bjk4, "8dp", "8dp", "8dp", "8dp");
                bjk4.setSingleLine(java.lang.Boolean.TRUE.equals(true));
                xxbja.addView(bjk4);
                open.cn.awg.pro.core.NativeViewValues.margins(bjk4, "3dp", "3dp", "3dp", "0dp");

                android.widget.Button an1 = new android.widget.Button(context);
                open.cn.awg.pro.core.NativeViewValues.size(an1, -1, -2);
                an1.setText(String.valueOf("提交"));
                an1.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        open.cn.awg.pro.core.NativeActions.start(new Thread() {

                            public void run() {

                                viewshow(1);

                                TypheyeServiceBridge easy = new TypheyeServiceBridge(context);

                                if (easy.register$request(bjk1.getText().toString(), bjk2.getText().toString(), bjk3.getText().toString(), bjk4.getText().toString())) {

                                    viewshow(0);

                                }

                            }

                        });

                    }

                });
                xxbja.addView(an1);
                open.cn.awg.pro.core.NativeViewValues.margins(an1, "3dp", "3dp", "3dp", "0dp");

                android.widget.TextView wba = new android.widget.TextView(context);
                open.cn.awg.pro.core.NativeViewValues.size(wba, -2, -2);
                open.cn.awg.pro.core.NativeViewValues.padding(wba, "3dp", -2, "3dp", -2);
                wba.setText(String.valueOf(""));
                wba.setTypeface(wba.getTypeface(), open.cn.awg.pro.core.NativeViewValues.textStyle("bold"));
                wba.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(9, -1));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                wba.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                xxbja.addView(wba);
                root.addView(xxbja);

            }

        });
        viewshow(0);

    }

    public void m20002() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String color = "";

                final android.widget.RelativeLayout root = ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.plugin_runtime_view));
                root.removeAllViews();

                android.widget.LinearLayout xxbja = new android.widget.LinearLayout(context);
                open.cn.awg.pro.core.NativeViewValues.size(xxbja, -1, -2);
                xxbja.setOrientation(open.cn.awg.pro.core.TextValues.intValue(1, -1));
                xxbja.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                final android.widget.EditText bjk1 = new android.widget.EditText(context);
                open.cn.awg.pro.core.NativeViewValues.size(bjk1, -1, -2);
                bjk1.setHint(String.valueOf("请输入房间名"));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                bjk1.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                bjk1.setHintTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                bjk1.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                open.cn.awg.pro.core.NativeViewValues.padding(bjk1, "8dp", "8dp", "8dp", "8dp");
                bjk1.setSingleLine(java.lang.Boolean.TRUE.equals(true));
                xxbja.addView(bjk1);
                open.cn.awg.pro.core.NativeViewValues.margins(bjk1, "3dp", "3dp", "3dp", "0dp");

                final android.widget.EditText bjk2 = new android.widget.EditText(context);
                open.cn.awg.pro.core.NativeViewValues.size(bjk2, -1, -2);
                bjk2.setHint(String.valueOf("请输入房间ID"));
                bjk2.setInputType(open.cn.awg.pro.core.NativeViewValues.inputType("number"));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                bjk2.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                bjk2.setHintTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                bjk2.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                open.cn.awg.pro.core.NativeViewValues.padding(bjk2, "8dp", "8dp", "8dp", "8dp");
                bjk2.setSingleLine(java.lang.Boolean.TRUE.equals(true));
                xxbja.addView(bjk2);
                open.cn.awg.pro.core.NativeViewValues.margins(bjk2, "3dp", "3dp", "3dp", "0dp");

                android.widget.Button an1 = new android.widget.Button(context);
                open.cn.awg.pro.core.NativeViewValues.size(an1, -1, -2);
                an1.setText(String.valueOf("提交"));
                an1.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        open.cn.awg.pro.core.NativeActions.start(new Thread() {

                            public void run() {

                                viewshow(1);

                                WearChatRuntimeBridge e10 = new WearChatRuntimeBridge(context);

                                if (e10.part_buildNewRoom(bjk1.getText().toString(), bjk2.getText().toString())) {

                                    viewshow(0);

                                }

                            }

                        });

                    }

                });
                xxbja.addView(an1);
                open.cn.awg.pro.core.NativeViewValues.margins(an1, "3dp", "3dp", "3dp", "0dp");

                android.widget.TextView wba = new android.widget.TextView(context);
                open.cn.awg.pro.core.NativeViewValues.size(wba, -2, -2);
                open.cn.awg.pro.core.NativeViewValues.padding(wba, "3dp", -2, "3dp", -2);
                wba.setText(String.valueOf(""));
                wba.setTypeface(wba.getTypeface(), open.cn.awg.pro.core.NativeViewValues.textStyle("bold"));
                wba.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(9, -1));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                wba.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                xxbja.addView(wba);
                root.addView(xxbja);

            }

        });
        viewshow(0);

    }

    public void m20003() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String color = "";

                final android.widget.RelativeLayout root = ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.plugin_runtime_view));
                root.removeAllViews();

                android.widget.LinearLayout xxbja = new android.widget.LinearLayout(context);
                open.cn.awg.pro.core.NativeViewValues.size(xxbja, -1, -2);
                xxbja.setOrientation(open.cn.awg.pro.core.TextValues.intValue(1, -1));
                xxbja.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                final android.widget.EditText bjk1 = new android.widget.EditText(context);
                open.cn.awg.pro.core.NativeViewValues.size(bjk1, -1, -2);
                bjk1.setHint(String.valueOf("请输入扩展名"));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                bjk1.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                bjk1.setHintTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                bjk1.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                open.cn.awg.pro.core.NativeViewValues.padding(bjk1, "8dp", "8dp", "8dp", "8dp");
                bjk1.setSingleLine(java.lang.Boolean.TRUE.equals(true));
                xxbja.addView(bjk1);
                open.cn.awg.pro.core.NativeViewValues.margins(bjk1, "3dp", "3dp", "3dp", "0dp");

                final android.widget.EditText bjk2 = new android.widget.EditText(context);
                open.cn.awg.pro.core.NativeViewValues.size(bjk2, -1, -2);
                bjk2.setHint(String.valueOf("请输入扩展ID"));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                bjk2.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                bjk2.setHintTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                bjk2.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                open.cn.awg.pro.core.NativeViewValues.padding(bjk2, "8dp", "8dp", "8dp", "8dp");
                bjk2.setSingleLine(java.lang.Boolean.TRUE.equals(true));
                xxbja.addView(bjk2);
                open.cn.awg.pro.core.NativeViewValues.margins(bjk2, "3dp", "3dp", "3dp", "0dp");

                final android.widget.EditText bjk3 = new android.widget.EditText(context);
                open.cn.awg.pro.core.NativeViewValues.size(bjk3, -1, -2);
                bjk3.setHint(String.valueOf("请输入授权码"));
                bjk3.setInputType(open.cn.awg.pro.core.NativeViewValues.inputType("textpassword"));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                bjk3.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                bjk3.setHintTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                bjk3.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                open.cn.awg.pro.core.NativeViewValues.padding(bjk3, "8dp", "8dp", "8dp", "8dp");
                bjk3.setSingleLine(java.lang.Boolean.TRUE.equals(true));
                xxbja.addView(bjk3);
                open.cn.awg.pro.core.NativeViewValues.margins(bjk3, "3dp", "3dp", "3dp", "0dp");

                android.widget.Button an1 = new android.widget.Button(context);
                open.cn.awg.pro.core.NativeViewValues.size(an1, -1, -2);
                an1.setText(String.valueOf("提交"));
                an1.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        open.cn.awg.pro.core.NativeActions.start(new Thread() {

                            public void run() {

                                viewshow(1);

                                ExtensionPluginBridge e14 = new ExtensionPluginBridge(context);

                                if (e14.part_buildNewPlu(bjk1.getText().toString(), bjk2.getText().toString(), bjk3.getText().toString())) {

                                    viewshow(0);

                                }

                            }

                        });

                    }

                });
                xxbja.addView(an1);
                open.cn.awg.pro.core.NativeViewValues.margins(an1, "3dp", "3dp", "3dp", "0dp");

                android.widget.TextView wba = new android.widget.TextView(context);
                open.cn.awg.pro.core.NativeViewValues.size(wba, -2, -2);
                open.cn.awg.pro.core.NativeViewValues.padding(wba, "3dp", -2, "3dp", -2);
                wba.setText(String.valueOf(""));
                wba.setTypeface(wba.getTypeface(), open.cn.awg.pro.core.NativeViewValues.textStyle("bold"));
                wba.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(9, -1));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                wba.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                xxbja.addView(wba);
                root.addView(xxbja);

            }

        });
        viewshow(0);

    }

    public void m20004() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String color = "";

                final android.widget.RelativeLayout root = ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.plugin_runtime_view));
                root.removeAllViews();

                android.widget.LinearLayout xxbja = new android.widget.LinearLayout(context);
                open.cn.awg.pro.core.NativeViewValues.size(xxbja, -1, -2);
                xxbja.setOrientation(open.cn.awg.pro.core.TextValues.intValue(1, -1));
                xxbja.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                final android.widget.EditText bjk1 = new android.widget.EditText(context);
                open.cn.awg.pro.core.NativeViewValues.size(bjk1, -1, -2);
                bjk1.setHint(String.valueOf("请输入您的邮箱"));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                bjk1.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                bjk1.setHintTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                bjk1.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                open.cn.awg.pro.core.NativeViewValues.padding(bjk1, "8dp", "8dp", "8dp", "8dp");
                bjk1.setSingleLine(java.lang.Boolean.TRUE.equals(true));
                xxbja.addView(bjk1);
                open.cn.awg.pro.core.NativeViewValues.margins(bjk1, "3dp", "3dp", "3dp", "0dp");

                android.widget.LinearLayout xxbjb = new android.widget.LinearLayout(context);
                open.cn.awg.pro.core.NativeViewValues.size(xxbjb, -1, -2);
                xxbjb.setOrientation(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                xxbjb.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                final android.widget.EditText bjk2 = new android.widget.EditText(context);
                open.cn.awg.pro.core.NativeViewValues.size(bjk2, -1, -2);
                bjk2.setHint(String.valueOf("请输入验证码"));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                bjk2.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                bjk2.setHintTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                bjk2.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                open.cn.awg.pro.core.NativeViewValues.padding(bjk2, "8dp", "8dp", "8dp", "8dp");
                bjk2.setSingleLine(java.lang.Boolean.TRUE.equals(true));
                xxbjb.addView(bjk2);

                android.widget.LinearLayout.LayoutParams bjk2_xxbjgz = ((android.widget.LinearLayout.LayoutParams) (Object) open.cn.awg.pro.core.NativeViewValues.linearParams(bjk2));
                open.cn.awg.pro.core.NativeViewValues.weight(bjk2, bjk2_xxbjgz, 1);

                final android.widget.Button an0 = new android.widget.Button(context);
                open.cn.awg.pro.core.NativeViewValues.size(an0, -2, -2);
                an0.setText(String.valueOf("获取"));
                an0.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        if (java.util.Objects.equals(an0.getText().toString(), "获取")) {

                            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                                public void run() {

                                    TypheyeServiceBridge easy = new TypheyeServiceBridge(context);
                                    easy.register$request$token(bjk1.getText().toString(), an0);

                                }

                            });

                        } else if (java.util.Objects.equals(an0.getText().toString(), "已发送")) {

                            e1.tsk("提示", "发送验证码成功,请在邮箱查收");

                        }

                    }

                });
                xxbjb.addView(an0);
                open.cn.awg.pro.core.NativeViewValues.margins(an0, "3dp", "0dp", "0dp", "0dp");
                xxbja.addView(xxbjb);
                open.cn.awg.pro.core.NativeViewValues.margins(xxbjb, "3dp", "3dp", "3dp", "0dp");

                final android.widget.EditText bjk3 = new android.widget.EditText(context);
                open.cn.awg.pro.core.NativeViewValues.size(bjk3, -1, -2);
                bjk3.setHint(String.valueOf("请输入新密码"));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                bjk3.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                bjk3.setHintTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                bjk3.setInputType(open.cn.awg.pro.core.NativeViewValues.inputType("textpassword"));
                bjk3.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                open.cn.awg.pro.core.NativeViewValues.padding(bjk3, "8dp", "8dp", "8dp", "8dp");
                bjk3.setSingleLine(java.lang.Boolean.TRUE.equals(true));
                xxbja.addView(bjk3);
                open.cn.awg.pro.core.NativeViewValues.margins(bjk3, "3dp", "3dp", "3dp", "0dp");

                final android.widget.EditText bjk4 = new android.widget.EditText(context);
                open.cn.awg.pro.core.NativeViewValues.size(bjk4, -1, -2);
                bjk4.setHint(String.valueOf("请确认新密码"));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                bjk4.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                bjk4.setHintTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                bjk4.setInputType(open.cn.awg.pro.core.NativeViewValues.inputType("textpassword"));
                bjk4.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                open.cn.awg.pro.core.NativeViewValues.padding(bjk4, "8dp", "8dp", "8dp", "8dp");
                bjk4.setSingleLine(java.lang.Boolean.TRUE.equals(true));
                xxbja.addView(bjk4);
                open.cn.awg.pro.core.NativeViewValues.margins(bjk4, "3dp", "3dp", "3dp", "0dp");

                android.widget.Button an1 = new android.widget.Button(context);
                open.cn.awg.pro.core.NativeViewValues.size(an1, -1, -2);
                an1.setText(String.valueOf("提交"));
                an1.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        open.cn.awg.pro.core.NativeActions.start(new Thread() {

                            public void run() {

                                viewshow(1);

                                TypheyeServiceBridge easy = new TypheyeServiceBridge(context);

                                if (easy.findpwd(bjk1.getText().toString(), bjk2.getText().toString(), bjk3.getText().toString(), bjk4.getText().toString())) {

                                    viewshow(0);

                                }

                            }

                        });

                    }

                });
                xxbja.addView(an1);
                open.cn.awg.pro.core.NativeViewValues.margins(an1, "3dp", "3dp", "3dp", "0dp");

                android.widget.TextView wba = new android.widget.TextView(context);
                open.cn.awg.pro.core.NativeViewValues.size(wba, -2, -2);
                open.cn.awg.pro.core.NativeViewValues.padding(wba, "3dp", -2, "3dp", -2);
                wba.setText(String.valueOf(""));
                wba.setTypeface(wba.getTypeface(), open.cn.awg.pro.core.NativeViewValues.textStyle("bold"));
                wba.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(9, -1));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                wba.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                xxbja.addView(wba);
                root.addView(xxbja);

            }

        });
        viewshow(0);

    }

    public void m20005() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String color = "";

                final android.widget.RelativeLayout root = ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.plugin_runtime_view));
                root.removeAllViews();

                android.widget.LinearLayout xxbja = new android.widget.LinearLayout(context);
                open.cn.awg.pro.core.NativeViewValues.size(xxbja, -1, -2);
                xxbja.setOrientation(open.cn.awg.pro.core.TextValues.intValue(1, -1));
                xxbja.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                android.widget.TextView wb0 = new android.widget.TextView(context);
                open.cn.awg.pro.core.NativeViewValues.size(wb0, -1, -2);
                open.cn.awg.pro.core.NativeViewValues.padding(wb0, "8dp", "8dp", "8dp", "8dp");
                wb0.setText(String.valueOf("发送诊断数据，有助于我们更好的为您解决问题，提升更新质量。\n您是否同意腕管Pro发送以下诊断数据？"));
                wb0.setTypeface(wb0.getTypeface(), open.cn.awg.pro.core.NativeViewValues.textStyle("bold"));
                wb0.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                wb0.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                xxbja.addView(wb0);
                open.cn.awg.pro.core.NativeViewValues.margins(wb0, "3dp", "3dp", "3dp", "0dp");

                final android.widget.CheckBox dx1 = new android.widget.CheckBox(context);
                open.cn.awg.pro.core.NativeViewValues.size(dx1, -1, -2);
                open.cn.awg.pro.core.NativeViewValues.padding(dx1, "8dp", "8dp", "8dp", "8dp");
                dx1.setText(String.valueOf("允许发送腕管Pro于运行期间产生的日志数据\n*将用于提升用户体验"));
                open.cn.awg.pro.core.NativeViewValues.background(dx1, R.drawable.dxxbj);
                xxbja.addView(dx1);
                open.cn.awg.pro.core.NativeViewValues.margins(dx1, "3dp", "3dp", "3dp", "0dp");

                final android.widget.CheckBox dx2 = new android.widget.CheckBox(context);
                open.cn.awg.pro.core.NativeViewValues.size(dx2, -1, -2);
                open.cn.awg.pro.core.NativeViewValues.padding(dx2, "8dp", "8dp", "8dp", "8dp");
                dx2.setText(String.valueOf("允许发送位置信息服务运行期间产生的定位数据\n*将用于提高定位服务精确度"));
                open.cn.awg.pro.core.NativeViewValues.background(dx2, R.drawable.dxxbj);
                xxbja.addView(dx2);
                open.cn.awg.pro.core.NativeViewValues.margins(dx2, "3dp", "3dp", "3dp", "0dp");

                final android.widget.CheckBox dx3 = new android.widget.CheckBox(context);
                open.cn.awg.pro.core.NativeViewValues.size(dx3, -1, -2);
                open.cn.awg.pro.core.NativeViewValues.padding(dx3, "8dp", "8dp", "8dp", "8dp");
                dx3.setText(String.valueOf("允许发送用户部分隐私数据(包括但不限定于IP地址、IMEI及GPS定位信息等)\n*将用于改善用户体验"));
                open.cn.awg.pro.core.NativeViewValues.background(dx3, R.drawable.dxxbj);
                xxbja.addView(dx3);
                open.cn.awg.pro.core.NativeViewValues.margins(dx3, "3dp", "3dp", "3dp", "0dp");

                if (!debug.isShowed()) {

                    dx1.setChecked(java.lang.Boolean.TRUE.equals(true));
                    dx2.setChecked(java.lang.Boolean.TRUE.equals(true));
                    dx3.setChecked(java.lang.Boolean.TRUE.equals(false));

                } else {

                    if (debug.getDebugState("1")) {

                        dx1.setChecked(java.lang.Boolean.TRUE.equals(true));

                    }

                    if (debug.getDebugState("2")) {

                        dx2.setChecked(java.lang.Boolean.TRUE.equals(true));

                    }

                    if (debug.getDebugState("3")) {

                        dx3.setChecked(java.lang.Boolean.TRUE.equals(true));

                    }

                }

                android.widget.Button an0 = new android.widget.Button(context);
                open.cn.awg.pro.core.NativeViewValues.size(an0, -1, -2);
                an0.setText(String.valueOf("同意发送已选诊断数据"));
                an0.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        String allowed = "";

                        boolean dx_1 = dx1.isChecked();

                        boolean dx_2 = dx2.isChecked();

                        boolean dx_3 = dx3.isChecked();

                        if (dx_1 || dx_2 || dx_3) {

                            if (dx_1) {

                                debug.setDebugState("1", true);
                                allowed = allowed + "\n*腕管Pro于运行期间产生的日志数据";

                            }

                            if (dx_2) {

                                debug.setDebugState("2", true);
                                allowed = allowed + "\n*位置信息服务运行期间产生的定位数据";

                            }

                            if (dx_3) {

                                debug.setDebugState("3", true);
                                allowed = allowed + "\n*用户部分隐私数据";

                            }

                            if (!dx_1) {

                                debug.setDebugState("1", false);

                            }

                            if (!dx_2) {

                                debug.setDebugState("2", false);

                            }

                            if (!dx_3) {

                                debug.setDebugState("3", false);

                            }

                        } else {

                            debug.setDebugState("1", false);
                            debug.setDebugState("2", false);
                            debug.setDebugState("3", false);
                            allowed = "\n（未允许发送任何诊断数据）";

                        }
                        debug.showed();
                        e1.tsk("提示", "已允许发送以下诊断数据：" + allowed);
                        open.cn.awg.pro.core.NativeActions.finish(context);

                    }

                });
                xxbja.addView(an0);
                open.cn.awg.pro.core.NativeViewValues.margins(an0, "3dp", "3dp", "3dp", "0dp");

                android.widget.Button an1 = new android.widget.Button(context);
                open.cn.awg.pro.core.NativeViewValues.size(an1, -1, -2);
                an1.setText(String.valueOf("拒绝发送所有诊断数据"));
                an1.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        debug.setDebugState("1", false);
                        debug.setDebugState("2", false);
                        debug.setDebugState("3", false);
                        debug.showed();
                        e1.tsk("提示", "已拒绝发送所有诊断数据");
                        open.cn.awg.pro.core.NativeActions.finish(context);

                    }

                });
                xxbja.addView(an1);
                open.cn.awg.pro.core.NativeViewValues.margins(an1, "3dp", "3dp", "3dp", "0dp");

                android.widget.TextView wba = new android.widget.TextView(context);
                open.cn.awg.pro.core.NativeViewValues.size(wba, -2, -2);
                open.cn.awg.pro.core.NativeViewValues.padding(wba, "3dp", -2, "3dp", -2);
                wba.setText(String.valueOf(""));
                wba.setTypeface(wba.getTypeface(), open.cn.awg.pro.core.NativeViewValues.textStyle("bold"));
                wba.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(9, -1));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                wba.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                xxbja.addView(wba);
                root.addView(xxbja);

            }

        });
        viewshow(0);

    }

    public void m20006() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String color = "";

                final android.widget.RelativeLayout root = ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.plugin_runtime_view));
                root.removeAllViews();

                android.widget.LinearLayout xxbja = new android.widget.LinearLayout(context);
                open.cn.awg.pro.core.NativeViewValues.size(xxbja, -1, -2);
                xxbja.setOrientation(open.cn.awg.pro.core.TextValues.intValue(1, -1));
                xxbja.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                android.widget.TextView wb0 = new android.widget.TextView(context);
                open.cn.awg.pro.core.NativeViewValues.size(wb0, -1, -2);
                open.cn.awg.pro.core.NativeViewValues.padding(wb0, "8dp", "8dp", "8dp", "8dp");
                wb0.setText(String.valueOf("腕管Pro - 配置工具\n快捷备份/恢复应用配置\n\n*重要说明\n1.使用此功能需要登录腕管Pro账户；\n2.同一备份文件仅限在登录同账户下的腕管Pro使用；\n3.若设备存储存在多个备份文件，将导入最新的备份文件完成备份恢复。"));
                wb0.setTypeface(wb0.getTypeface(), open.cn.awg.pro.core.NativeViewValues.textStyle("bold"));
                wb0.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                wb0.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                xxbja.addView(wb0);
                open.cn.awg.pro.core.NativeViewValues.margins(wb0, "3dp", "3dp", "3dp", "0dp");

                android.widget.Button an0 = new android.widget.Button(context);
                open.cn.awg.pro.core.NativeViewValues.size(an0, -1, -2);
                an0.setText(String.valueOf("导出备份设置"));
                an0.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        open.cn.awg.pro.core.NativeActions.start(new Thread() {

                            public void run() {

                                viewshow(1);
                                open.cn.awg.pro.core.NativeActions.sleep(500);

                                AppRuntimeBridge e1 = new AppRuntimeBridge(context);
                                e1.app_backup();
                                viewshow(0);

                            }

                        });

                    }

                });
                xxbja.addView(an0);
                open.cn.awg.pro.core.NativeViewValues.margins(an0, "3dp", "3dp", "3dp", "0dp");

                android.widget.Button an1 = new android.widget.Button(context);
                open.cn.awg.pro.core.NativeViewValues.size(an1, -1, -2);
                an1.setText(String.valueOf("导入备份设置"));
                an1.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        open.cn.awg.pro.core.NativeActions.start(new Thread() {

                            public void run() {

                                viewshow(1);
                                open.cn.awg.pro.core.NativeActions.sleep(500);

                                AppRuntimeBridge e1 = new AppRuntimeBridge(context);

                                if (!e1.app_gorecovery()) {

                                    viewshow(0);

                                }

                            }

                        });

                    }

                });
                xxbja.addView(an1);
                open.cn.awg.pro.core.NativeViewValues.margins(an1, "3dp", "3dp", "3dp", "0dp");

                android.widget.Button an2 = new android.widget.Button(context);
                open.cn.awg.pro.core.NativeViewValues.size(an2, -1, -2);
                an2.setText(String.valueOf("使用云备份"));
                an2.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (CloudServiceActivity.class));
                        open.cn.awg.pro.core.NativeActions.finish(context);

                    }

                });
                xxbja.addView(an2);
                open.cn.awg.pro.core.NativeViewValues.margins(an2, "3dp", "3dp", "3dp", "0dp");

                android.widget.TextView wba = new android.widget.TextView(context);
                open.cn.awg.pro.core.NativeViewValues.size(wba, -2, -2);
                open.cn.awg.pro.core.NativeViewValues.padding(wba, "3dp", -2, "3dp", -2);
                wba.setText(String.valueOf(""));
                wba.setTypeface(wba.getTypeface(), open.cn.awg.pro.core.NativeViewValues.textStyle("bold"));
                wba.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(9, -1));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                wba.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                xxbja.addView(wba);
                root.addView(xxbja);

            }

        });
        viewshow(0);

    }

    public void m20007() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String color = "";

                final android.widget.RelativeLayout root = ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.plugin_runtime_view));
                root.removeAllViews();

                android.widget.LinearLayout xxbja = new android.widget.LinearLayout(context);
                open.cn.awg.pro.core.NativeViewValues.size(xxbja, -1, -2);
                xxbja.setOrientation(open.cn.awg.pro.core.TextValues.intValue(1, -1));
                xxbja.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                final android.widget.EditText bjk1 = new android.widget.EditText(context);
                open.cn.awg.pro.core.NativeViewValues.size(bjk1, -1, -2);
                bjk1.setHint(String.valueOf("请输入授权码"));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                bjk1.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                bjk1.setHintTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                bjk1.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                open.cn.awg.pro.core.NativeViewValues.padding(bjk1, "8dp", "8dp", "8dp", "8dp");
                bjk1.setSingleLine(java.lang.Boolean.TRUE.equals(true));
                xxbja.addView(bjk1);
                open.cn.awg.pro.core.NativeViewValues.margins(bjk1, "3dp", "3dp", "3dp", "0dp");

                android.widget.Button an1 = new android.widget.Button(context);
                open.cn.awg.pro.core.NativeViewValues.size(an1, -1, -2);
                an1.setText(String.valueOf("登录"));
                an1.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        final String authurl = "https://auth.sayqz.com";

                        final String cookiepath = AppPaths.appPath("data/plugin/tunefree/cookie");

                        if (!state_login_base) {

                            state_login_base = true;

                            final String code2 = bjk1.getText().toString();
                            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                                public void run() {

                                    viewshow(1);

                                    String code = "";

                                    String hqto = authurl + "/?path=info&code=" + code2;

                                    String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(hqto), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(5000, -1), open.cn.awg.pro.core.TextValues.intValue(5000, -1), (String) (null)).bodyForHttp200();

                                    if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                                    } else {

                                        code = e1.readJson(back, "code");

                                    }

                                    if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                                        final String msg = "请求异常，请检查网络连接";
                                        e1.tsk("提示", msg);
                                        viewshow(0);

                                    } else {

                                        if (java.util.Objects.equals(code, "200")) {

                                            String mcu = e1.readJson(back, "data");
                                            mcu = e1.readJson(mcu, "netease_cookie");
                                            mcu = mcu + ";";
                                            open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (cookiepath), (String) (mcu));

                                            final String msg = "登录成功";
                                            e1.tsk("提示", msg);
                                            open.cn.awg.pro.core.NativeActions.finish(context);

                                        } else if (java.util.Objects.equals(code, "400")) {

                                            final String msg = e1.readJson(back, "message");
                                            e1.tsk("提示", msg);
                                            viewshow(0);

                                        } else {

                                            final String msg = "请求异常，请检查网络连接";
                                            e1.tsk("提示", msg);
                                            viewshow(0);

                                        }

                                    }
                                    state_login_base = false;

                                }

                            });

                        } else {

                            final String msg = "操作过于频繁，请稍后重试";
                            e1.tsk("提示", msg);

                        }

                    }

                });
                xxbja.addView(an1);
                open.cn.awg.pro.core.NativeViewValues.margins(an1, "3dp", "3dp", "3dp", "0dp");

                android.widget.TextView wba = new android.widget.TextView(context);
                open.cn.awg.pro.core.NativeViewValues.size(wba, -2, -2);
                open.cn.awg.pro.core.NativeViewValues.padding(wba, "3dp", -2, "3dp", -2);
                wba.setText(String.valueOf(""));
                wba.setTypeface(wba.getTypeface(), open.cn.awg.pro.core.NativeViewValues.textStyle("bold"));
                wba.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(9, -1));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                wba.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                xxbja.addView(wba);
                root.addView(xxbja);

            }

        });
        viewshow(0);

    }

    public void csh() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String color = "";

                final android.widget.RelativeLayout root = ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.plugin_runtime_view));
                root.removeAllViews();

                android.widget.LinearLayout xxbja = new android.widget.LinearLayout(context);
                open.cn.awg.pro.core.NativeViewValues.size(xxbja, -1, -2);
                xxbja.setOrientation(open.cn.awg.pro.core.TextValues.intValue(1, -1));
                xxbja.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                android.widget.ImageView tx1 = new android.widget.ImageView(context);
                open.cn.awg.pro.core.NativeViewValues.size(tx1, -2, -2);
                open.cn.awg.pro.core.NativeViewValues.padding(tx1, 0, 20, 0, 0);
                open.cn.awg.pro.core.NativeViewValues.image(tx1, R.mipmap.i);
                xxbja.addView(tx1);

                android.widget.TextView wba = new android.widget.TextView(context);
                open.cn.awg.pro.core.NativeViewValues.size(wba, -2, -2);
                open.cn.awg.pro.core.NativeViewValues.padding(wba, 20, 20, 20, 10);
                wba.setText(String.valueOf(title));
                wba.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(16, -1));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                wba.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                xxbja.addView(wba);

                android.widget.TextView wbb = new android.widget.TextView(context);
                open.cn.awg.pro.core.NativeViewValues.size(wbb, -2, -2);
                open.cn.awg.pro.core.NativeViewValues.padding(wbb, 20, 10, 20, 20);
                wbb.setText(String.valueOf("扩展插件ID: " + id));
                wbb.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU2);
                wbb.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                xxbja.addView(wbb);

                android.widget.Button an1 = new android.widget.Button(context);
                open.cn.awg.pro.core.NativeViewValues.size(an1, -2, -2);
                an1.setText(String.valueOf("打开指令框"));
                an1.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        viewshow(2);

                    }

                });
                xxbja.addView(an1);
                root.addView(xxbja);

            }

        });
        viewshow(0);

    }

    public void csh_e() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String color = "";

                final android.widget.RelativeLayout root = ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.plugin_runtime_view));
                root.removeAllViews();

                android.widget.LinearLayout xxbja = new android.widget.LinearLayout(context);
                open.cn.awg.pro.core.NativeViewValues.size(xxbja, -1, -2);
                xxbja.setOrientation(open.cn.awg.pro.core.TextValues.intValue(1, -1));
                xxbja.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                android.widget.ImageView tx1 = new android.widget.ImageView(context);
                open.cn.awg.pro.core.NativeViewValues.size(tx1, -2, -2);
                open.cn.awg.pro.core.NativeViewValues.padding(tx1, 0, 20, 0, 0);
                open.cn.awg.pro.core.NativeViewValues.image(tx1, R.mipmap.i);
                xxbja.addView(tx1);

                android.widget.TextView wba = new android.widget.TextView(context);
                open.cn.awg.pro.core.NativeViewValues.size(wba, -2, -2);
                open.cn.awg.pro.core.NativeViewValues.padding(wba, 20, 20, 20, 20);
                wba.setText(String.valueOf("欢迎使用AwgRunable"));
                wba.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(16, -1));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                wba.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                xxbja.addView(wba);

                android.widget.Button an0 = new android.widget.Button(context);
                open.cn.awg.pro.core.NativeViewValues.size(an0, -2, -2);
                an0.setText(String.valueOf("查看帮助文档"));
                an0.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        e1.tsk("提示", "暂未开放");

                    }

                });
                xxbja.addView(an0);

                android.widget.TextView wbb = new android.widget.TextView(context);
                open.cn.awg.pro.core.NativeViewValues.size(wbb, -2, -2);
                open.cn.awg.pro.core.NativeViewValues.padding(wbb, 20, 10, 20, 20);
                wbb.setText(String.valueOf("示例控件"));
                wbb.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, (float) open.cn.awg.pro.core.TextValues.intValue(13, -1));
                color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextTrue);
                wbb.setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                xxbja.addView(wbb);

                android.widget.Button an1 = new android.widget.Button(context);
                open.cn.awg.pro.core.NativeViewValues.size(an1, -2, -2);
                an1.setText(String.valueOf("打开指令框"));
                an1.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        viewshow(2);

                    }

                });
                xxbja.addView(an1);

                android.widget.Button an2 = new android.widget.Button(context);
                open.cn.awg.pro.core.NativeViewValues.size(an2, -2, -2);
                an2.setText(String.valueOf("显隐标题栏"));
                an2.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        if (((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).getVisibility() == android.view.View.VISIBLE) {

                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setVisibility(android.view.View.GONE);

                                }

                            });

                        } else {

                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setVisibility(android.view.View.VISIBLE);

                                }

                            });

                        }

                    }

                });
                xxbja.addView(an2);
                root.addView(xxbja);

            }

        });
        viewshow(0);

    }

    public void viewshow(int a) {

        final int i = a;

        final android.widget.RelativeLayout root = ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.plugin_runtime_view));

        final android.widget.LinearLayout load = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj3));

        final android.widget.LinearLayout add = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj4));

        final android.widget.TextView tab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar));

        final android.widget.EditText cmd = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1));

        final androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.qtgd1));
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                if (i == 0) {

                    root.setVisibility(android.view.View.VISIBLE);
                    load.setVisibility(android.view.View.GONE);
                    add.setVisibility(android.view.View.GONE);
                    tab.setText(String.valueOf(titleback));
                    tab.setVisibility(android.view.View.VISIBLE);
                    cmd.setText(String.valueOf(""));

                } else if (i == 1) {

                    root.setVisibility(android.view.View.GONE);
                    load.setVisibility(android.view.View.VISIBLE);
                    add.setVisibility(android.view.View.GONE);
                    tab.setText(String.valueOf(titleback2));
                    tab.setVisibility(android.view.View.VISIBLE);
                    cmd.setText(String.valueOf(""));

                } else if (i == 2) {

                    root.setVisibility(android.view.View.GONE);
                    load.setVisibility(android.view.View.GONE);
                    add.setVisibility(android.view.View.VISIBLE);
                    tab.setText(String.valueOf("指令框"));
                    tab.setVisibility(android.view.View.VISIBLE);
                    cmd.setText(String.valueOf(""));

                }
                qtgd1.requestFocus();

            }

        });

    }

    public void cmd() {

        String c = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).getText().toString();
        c = open.cn.awg.pro.core.TextValues.trim(c);

        if (java.util.Objects.equals(c, "")) {

            e1.tsk("提示", "指令不能为空");

        } else {

            if (java.util.Objects.equals(c, "exit")) {

                viewshow(0);
                open.cn.awg.pro.core.NativeActions.finish(context);

            } else if (java.util.Objects.equals(c, "help")) {

                viewshow(1);
                csh_e();

            } else if (java.util.Objects.equals(c, "run")) {

                jz();

            } else {

                e1.tsk("提示", "未找到该指令");

            }

        }

    }
}
