/**
 ******************************************************************************
 * @file    ChatRoomActivity.java
 * @author  Typheye
 * @brief   Displays a WearChat room and handles message sending.
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
package open.cn.awg.pro.chat;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.core.UriPathResolver;
import open.cn.awg.pro.file.FileManagerActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;

public class ChatRoomActivity extends BaseAwgActivity {

    public static String uid = "";
    public final ChatRoomActivity lei = this, 类 = this;
    public AppRuntimeBridge e1;

    public WearChatRuntimeBridge e10;
    public boolean xhtrue = true;
    public String path = AppPaths.appPath("cache/chat/upload/path");
    public String room_name = "";
    private final View.OnClickListener xxbjcdClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbjcdClick(vw);
        }

    };
    private final View.OnTouchListener xxbj6TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj6Touch(vw, me);
        }

    };
    private final View.OnClickListener tx4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx4Click(vw);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnTouchListener xxbj7TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj7Touch(vw, me);
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
    private final View.OnTouchListener xxbj4TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj4Touch(vw, me);
        }

    };
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };
    private final View.OnClickListener tx3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx3Click(vw);
        }

    };
    private final View.OnClickListener anDisuploadClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAnDisuploadClick(vw);
        }

    };
    private final View.OnClickListener anUploadClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAnUploadClick(vw);
        }

    };
    private final CompoundButton.OnCheckedChangeListener dxChatUploadCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        public void onCheckedChanged(CompoundButton vw, boolean ic) {
            onDxChatUploadCheckedChanged(vw, ic);
        }

    };
    private final View.OnTouchListener bjk1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjk1Touch(vw, me);
        }

    };
    private final View.OnLongClickListener wb1LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onWb1LongClick(vw);
        }

    };
    private final View.OnClickListener wb1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb1Click(vw);
        }

    };
    private final View.OnTouchListener i2xxbj1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onI2xxbj1Touch(vw, me);
        }

    };
    private final RecyclerView.OnScrollListener ltsv7lb1ScrollListener = new RecyclerView.OnScrollListener() {

        public void onScrollStateChanged(RecyclerView vw, int ns) {
            onLtsv7lb1ScrollStateChanged(vw, ns);
        }

        public void onScrolled(RecyclerView vw, int dx, int dy) {
            onLtsv7lb1Scrolled(vw, dx, dy);
        }

    };
    private final View.OnLongClickListener wb3LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onWb3LongClick(vw);
        }

    };
    private final View.OnClickListener wb3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb3Click(vw);
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
        e10 = new WearChatRuntimeBridge(this);
        setContentView(R.layout.chat_room);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        String set = AppPaths.appPath("settings/set3.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (set)), "true")) {

            String te = open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (AppPaths.appPath("data/assets/a13")));

            String[] name = new String[]{"title", "text"};

            String[] value = new String[]{"新手教程", te};
            open.cn.awg.pro.core.NativeActions.launch(ChatRoomActivity.this, (Class<?>) (TextQrDisplayActivity.class), (String[]) (name), (Object[]) (value));

        }

        if (e1.isJh()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.CheckBox) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.dx_chat_upload)).setVisibility(android.view.View.VISIBLE);

                }

            });

        }
        open.cn.awg.pro.core.NativeFiles.writeText(ChatRoomActivity.this, (String) (AppPaths.appPath("data/ltlb/showing")), (String) ("true"));
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.chat_room_root, R.id.xdbj1, R.id.title_bar);
        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (AppPaths.appPath("settings/f8/set4.inf"))), "true")) {

        }

        
        open.cn.awg.pro.core.NativeDevice.setKeepScreenOn(ChatRoomActivity.this, !(false));

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));
        ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.chat_room_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

        if (java.util.Objects.equals(a, "1")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.i2xxbj1)), u, 0, u, 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbj4)), u, 0, u, 0);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar));
            // 应用栏接管标题排版，移除旧运行时 padding
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "2")) {

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (open.cn.awg.pro.core.NativeFiles.exists(ChatRoomActivity.this, (String) (a001)) || java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (a001)), "true")) {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar));
                // 应用栏接管标题排版，移除旧运行时 padding
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            } else {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar));
                // 应用栏接管标题排版，移除旧运行时 padding
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            }
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.i2xxbj1)), 0, 0, 0, 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbj4)), 0, 0, 0, 0);

        } else if (java.util.Objects.equals(a, "3")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.i2xxbj1)), u, 0, u, 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbj4)), u, 0, u, 0);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar));
            // 应用栏接管标题排版，移除旧运行时 padding
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "4")) {

            int uih = open.cn.awg.pro.core.DisplayValues.pxToDp(ChatRoomActivity.this, open.cn.awg.pro.core.DisplayValues.systemBarHeight(ChatRoomActivity.this, "status_bar_height"));
            uih = uih + 9;

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar));
            // 应用栏接管标题排版，移除旧运行时 padding
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.i2xxbj1)), 0, 0, 0, 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbj4)), 0, 0, 0, 0);

        }

        android.widget.Spinner xlcd1 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.ltsup_xlcd1));

        String[] sz = new String[]{"图像文件", "音频文件", "视频文件", "其他文件"};
        open.cn.awg.pro.core.NativeViewValues.spinner(xlcd1, sz);

        android.widget.Spinner xlcd2 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.ltsup_xlcd2));
        sz = new String[]{"应用内置选择器", "系统默认选择器"};
        open.cn.awg.pro.core.NativeViewValues.spinner(xlcd2, sz);

    }

    public void jz() {
        uid = ChatRoomActivity.this.getIntent().getStringExtra(String.valueOf("uid"));

        String cachehd = AppPaths.appPath("data/ltlb/lbx_title");
        room_name = e10.getTabName(open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (cachehd)));
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf(room_name));

            }

        });
        e10.lts_csh();
        e10.ltsview(4);
        ltsgxxh();

    }

    public void ltsgxxh() {

        final String uid2 = open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (AppPaths.appPath("data/ltlb/lbx_id")));

        final String sjhc = AppPaths.appPath("data/ltlb/chat/") + uid2;

        String set2 = AppPaths.appPath("settings/f18/set1.inf");

        Object sjl = (Object) open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (set2));

        final long tim = open.cn.awg.pro.core.TextValues.longValue(sjl, 3000);
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                final String uid = open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (AppPaths.appPath("data/ltlb/lbx_id")));

                final String sjhc = AppPaths.appPath("data/ltlb/chat/") + uid;

                if (open.cn.awg.pro.core.NativeFiles.exists(ChatRoomActivity.this, (String) (sjhc)) && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (sjhc)), "")) {

                    e10.ltsgx1(false);

                } else {

                    e10.ltsgx3();

                }

                while (xhtrue) {

                    if (open.cn.awg.pro.core.NativeFiles.exists(ChatRoomActivity.this, (String) (AppPaths.appPath("data/ltlb/showing")))) {

                        open.cn.awg.pro.core.NativeActions.sleep(tim);

                        if (!open.cn.awg.pro.core.NativeFiles.exists(ChatRoomActivity.this, (String) (AppPaths.appPath("data/ltlb/disTouch")))) {

                            e10.ltsgx2();

                        }

                        if (open.cn.awg.pro.core.NativeFiles.exists(ChatRoomActivity.this, (String) (sjhc)) && !java.util.Objects.equals(open.cn.awg.pro.core.TextValues.trim(open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (sjhc))), "")) {

                            try {

                                String back = open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (sjhc));

                                org.json.JSONObject jo = (org.json.JSONObject) (Object) open.cn.awg.pro.core.JsonValues.parseObject(back);

                                JSONObject json = jo;

                                JSONArray list = open.cn.awg.pro.core.JsonValues.array(json, "data");

                                int i = open.cn.awg.pro.core.JsonValues.length(list);

                                int i2 = WearChatRuntimeBridge.spq3.rowCount() - 2;

                                if (i != i2 && i != 0 && i2 != 0) {

                                    e10.ltsgx1(true);

                                }

                            } catch (Throwable __$_e__) {

                            }

                        }

                    }

                }

            }

        });

    }

    public void shouJp() {

        try {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    InputMethodManager imm = (InputMethodManager) lei.getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(lei.getWindow().getDecorView().getWindowToken(), 0);

                }

            });

        } catch (Throwable __$_e__) {

        }

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
        open.cn.awg.pro.core.NativeFiles.writeText(ChatRoomActivity.this, (String) (AppPaths.appPath("data/ltlb/showing")), (String) ("true"));

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));
        ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.chat_room_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

        final android.widget.Button an = ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.an_upload));

        final android.widget.Button an2 = ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.an_disupload));

        final android.widget.CheckBox dx_chat_upload = ((android.widget.CheckBox) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.dx_chat_upload));

        final android.widget.Spinner ltsup_xlcd1 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.ltsup_xlcd1));

        final android.widget.Spinner ltsup_xlcd2 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.ltsup_xlcd2));

        if (open.cn.awg.pro.core.NativeFiles.exists(ChatRoomActivity.this, (String) (path)) && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (path)), "") && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (path)), "null") && java.util.Objects.equals(an.getHint().toString(), "选择文件")) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    an.setVisibility(android.view.View.VISIBLE);
                    an.setHint(String.valueOf("已选择•开始上传"));
                    ltsup_xlcd1.setEnabled(false);
                    ltsup_xlcd1.setVisibility(android.view.View.GONE);
                    ltsup_xlcd2.setEnabled(false);
                    ltsup_xlcd2.setVisibility(android.view.View.GONE);
                    dx_chat_upload.setEnabled(false);
                    an2.setVisibility(android.view.View.VISIBLE);

                }

            });

        }

    }

    public void onStop() {
        super.onStop();
        open.cn.awg.pro.core.NativeFiles.delete(ChatRoomActivity.this, (String) (AppPaths.appPath("data/ltlb/showing")), false);

    }

    public void onStart() {
        super.onStart();
        open.cn.awg.pro.core.NativeFiles.writeText(ChatRoomActivity.this, (String) (AppPaths.appPath("data/ltlb/showing")), (String) ("true"));

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));
        ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.chat_room_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

        androidx.recyclerview.widget.RecyclerView ltsv7lb1 = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.ltsv7lb1));
        ltsv7lb1.requestFocus();

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

    }

    public void onRestart() {
        super.onRestart();
        open.cn.awg.pro.core.NativeFiles.writeText(ChatRoomActivity.this, (String) (AppPaths.appPath("data/ltlb/showing")), (String) ("true"));

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));
        ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.chat_room_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

    }

    public void onResume() {
        super.onResume();
        open.cn.awg.pro.core.NativeFiles.writeText(ChatRoomActivity.this, (String) (AppPaths.appPath("data/ltlb/showing")), (String) ("true"));

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));
        ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.chat_room_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

    }

    public void onPause() {
        super.onPause();
        open.cn.awg.pro.core.NativeFiles.delete(ChatRoomActivity.this, (String) (AppPaths.appPath("data/ltlb/showing")), false);

    }

    public void onActivityResult(int re, int rc, Intent it) {
        super.onActivityResult(re, rc, it);

        if (re == 1) {

            if (rc == -1) {

                String realPath = null;

                try {

                    realPath = UriPathResolver.getByIntent(lei, it);

                } catch (Throwable e) {

                }

                if (!java.util.Objects.equals(realPath, null)) {

                    realPath = open.cn.awg.pro.core.NativeFiles.resolvePath(ChatRoomActivity.this, (String) (realPath));
                    open.cn.awg.pro.core.NativeFiles.writeText(ChatRoomActivity.this, (String) (path), (String) (realPath));

                    final android.widget.Button an = ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.an_upload));

                    final android.widget.Button an2 = ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.an_disupload));

                    final android.widget.CheckBox dx_chat_upload = ((android.widget.CheckBox) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.dx_chat_upload));

                    final android.widget.Spinner ltsup_xlcd1 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.ltsup_xlcd1));

                    final android.widget.Spinner ltsup_xlcd2 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.ltsup_xlcd2));

                    if (java.util.Objects.equals(an.getHint().toString(), "选择文件")) {

                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                an.setVisibility(android.view.View.VISIBLE);
                                an.setHint(String.valueOf("已选择•开始上传"));
                                ltsup_xlcd1.setEnabled(false);
                                ltsup_xlcd1.setVisibility(android.view.View.GONE);
                                ltsup_xlcd2.setEnabled(false);
                                ltsup_xlcd2.setVisibility(android.view.View.GONE);
                                dx_chat_upload.setEnabled(false);
                                an2.setVisibility(android.view.View.VISIBLE);

                            }

                        });

                    }

                }

            }

        }

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);
        xhtrue = false;
        open.cn.awg.pro.core.NativeFiles.delete(ChatRoomActivity.this, (String) (AppPaths.appPath("data/ltlb/showing")), false);

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

            if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.i2xxbj1)).getVisibility() == android.view.View.VISIBLE) {

                open.cn.awg.pro.core.NativeActions.finish(ChatRoomActivity.this);

            } else {

            }

        }
        return false;

    }

    private void onTitleBarClick(View vw) {

        String cachehd = AppPaths.appPath("data/ltlb/lbx_title");

        final String name = open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (cachehd));

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "[有新消息]")) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.i2xxbj1)).getVisibility() == android.view.View.VISIBLE) {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf(""));

                    }
                    open.cn.awg.pro.core.NativeViewValues.scrollPosition(((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.ltsv7lb1)), "top");

                }

            });

        } else if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), room_name)) {

            open.cn.awg.pro.core.NativeActions.finish(ChatRoomActivity.this);

        }

    }

    private void onWb3Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String z = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.wb3)).getText().toString();
                z = open.cn.awg.pro.core.TextValues.slice(z, "[提示] ");

                if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.wb3)).getText().toString(), "[提示] 发送成功")) {

                    z = z + "\n(ps:长按提示可隐藏哦)";

                }
                e1.tsk("详细信息", z);

            }

        });

    }

    private boolean onWb3LongClick(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.wb3)).getText().toString(), "[提示] 发送成功")) {

            e10.ltjmshowgb();

        }
        return true;

    }

    private void onLtsv7lb1ScrollStateChanged(RecyclerView vw, int ns) {

        if (ns == RecyclerView.SCROLL_STATE_IDLE) {

            if (!vw.canScrollVertically(-1)) {

                open.cn.awg.pro.core.NativeFiles.writeText(ChatRoomActivity.this, (String) (AppPaths.appPath("data/ltlb/hdva")), (String) ("-1"));

            } else if (!vw.canScrollVertically(1)) {

                String cachehd = AppPaths.appPath("data/ltlb/lbx_title");

                final String name = open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (cachehd));

                if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.i2xxbj1)).getVisibility() == android.view.View.VISIBLE) {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf(room_name));

                        }

                    });

                }
                open.cn.awg.pro.core.NativeFiles.writeText(ChatRoomActivity.this, (String) (AppPaths.appPath("data/ltlb/hdva")), (String) ("0"));

            } else {

                open.cn.awg.pro.core.NativeFiles.writeText(ChatRoomActivity.this, (String) (AppPaths.appPath("data/ltlb/hdva")), (String) ("1"));

            }

        }

    }

    private void onLtsv7lb1Scrolled(RecyclerView vw, int dx, int dy) {

    }

    private boolean onI2xxbj1Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onWb1Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbjcd)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("房间详情"));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.i2xxbj1)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbjcd)).setVisibility(android.view.View.GONE);
            ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.VISIBLE);

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("菜单"));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.i2xxbj1)).setVisibility(android.view.View.GONE);
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbjcd)).setVisibility(android.view.View.VISIBLE);
            ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.GONE);

        }

    }

    private boolean onWb1LongClick(View vw) {
        ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.GONE);
        e10.ltspost(1);
        return true;

    }

    private boolean onBjk1Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private void onDxChatUploadCheckedChanged(CompoundButton vw, boolean ic) {

        if (ic) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbjyc3)).setVisibility(android.view.View.VISIBLE);

                }

            });

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbjyc3)).setVisibility(android.view.View.GONE);

                }

            });

        }

    }

    private void onAnUploadClick(View vw) {

        android.widget.Spinner xlcd1 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.ltsup_xlcd1));

        android.widget.Spinner xlcd2 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.ltsup_xlcd2));

        final android.widget.Button an = ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.an_upload));

        final String choose = AppPaths.appPath("cache/file/choose");

        if (java.util.Objects.equals(an.getHint().toString(), "选择文件")) {

            open.cn.awg.pro.core.NativeFiles.delete(ChatRoomActivity.this, (String) (path), false);

            int sdk = android.os.Build.VERSION.SDK_INT;

            String ftitle = AppPaths.appPath("cache/file/title");
            open.cn.awg.pro.core.NativeFiles.writeText(ChatRoomActivity.this, (String) (ftitle), (String) ("选择附件"));

            if (xlcd2.getLastVisiblePosition() == 0) {

                if (xlcd1.getLastVisiblePosition() == 0) {

                    open.cn.awg.pro.core.NativeFiles.writeText(ChatRoomActivity.this, (String) (choose), (String) ("h1"));

                } else if (xlcd1.getLastVisiblePosition() == 1) {

                    open.cn.awg.pro.core.NativeFiles.writeText(ChatRoomActivity.this, (String) (choose), (String) ("h2"));

                } else if (xlcd1.getLastVisiblePosition() == 2) {

                    open.cn.awg.pro.core.NativeFiles.writeText(ChatRoomActivity.this, (String) (choose), (String) ("h3"));

                } else if (xlcd1.getLastVisiblePosition() == 3) {

                    open.cn.awg.pro.core.NativeFiles.writeText(ChatRoomActivity.this, (String) (choose), (String) ("h4"));

                }

                try {

                    

                    if (open.cn.awg.pro.core.NativeActions.hasPermission(ChatRoomActivity.this, "android.permission.WRITE_EXTERNAL_STORAGE")) {

                        if (sdk < 30) {

                            String lj = open.cn.awg.pro.core.NativeFiles.resolvePath(ChatRoomActivity.this, (String) ("%"));

                            final String[] name = new String[]{"url", "root"};

                            final String[] value = new String[]{lj, lj};
                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    open.cn.awg.pro.core.NativeActions.launch(ChatRoomActivity.this, (Class<?>) (FileManagerActivity.class), (String[]) (name), (Object[]) (value));

                                }

                            });

                        } else {

                            String lj = "/storage/emulated/0/";

                            final String[] name = new String[]{"url", "root"};

                            final String[] value = new String[]{lj, lj};
                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    open.cn.awg.pro.core.NativeActions.launch(ChatRoomActivity.this, (Class<?>) (FileManagerActivity.class), (String[]) (name), (Object[]) (value));

                                }

                            });

                        }

                    } else {

                        e1.tsk("提示", "您未授权应用\"修改或删除您共享存储空间中的内容\"，请先前往设置授权后再使用");

                    }

                } catch (Throwable e) {

                    e1.upload_error(e, "i2.*");

                }

            } else if (xlcd2.getLastVisiblePosition() == 1) {

                try {

                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    if (xlcd1.getLastVisiblePosition() == 0)
                        intent.setType("image/*");
                    else if (xlcd1.getLastVisiblePosition() == 1)
                        intent.setType("audio/*");
                    else if (xlcd1.getLastVisiblePosition() == 2)
                        intent.setType("video/*");
                    else if (xlcd1.getLastVisiblePosition() == 3)
                        intent.setType("*/*");
                    lei.startActivityForResult(intent, 1);

                } catch (Throwable e) {

                    e1.upload_error(e, "i2.*");

                }

            }

        } else if (java.util.Objects.equals(an.getHint().toString(), "已选择•开始上传")) {

            e10.upload(open.cn.awg.pro.core.NativeFiles.readText(ChatRoomActivity.this, (String) (path)), xlcd1.getLastVisiblePosition());

        } else if (java.util.Objects.equals(an.getHint().toString(), "上传成功")) {

            e1.tsk("提示", "已成功上传文件");

        }

    }

    private void onAnDisuploadClick(View vw) {
        open.cn.awg.pro.core.NativeFiles.delete(ChatRoomActivity.this, (String) (path), false);

        final android.widget.Button an = ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.an_upload));

        final android.widget.Button an2 = ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.an_disupload));

        final android.widget.CheckBox dx_chat_upload = ((android.widget.CheckBox) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.dx_chat_upload));

        final android.widget.Spinner ltsup_xlcd1 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.ltsup_xlcd1));

        final android.widget.Spinner ltsup_xlcd2 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.ltsup_xlcd2));
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                an.setVisibility(android.view.View.VISIBLE);
                an.setHint(String.valueOf("选择文件"));
                an2.setVisibility(android.view.View.GONE);
                ltsup_xlcd1.setEnabled(true);
                ltsup_xlcd1.setVisibility(android.view.View.VISIBLE);
                ltsup_xlcd2.setEnabled(true);
                ltsup_xlcd2.setVisibility(android.view.View.VISIBLE);
                dx_chat_upload.setEnabled(true);

            }

        });

    }

    private void onTx3Click(View vw) {

        final android.widget.Button an = ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.an_upload));

        if (java.util.Objects.equals(an.getHint().toString(), "上传中")) {

            e1.tsk("提示", "请耐心等待文件上传完毕");

        } else {

            shouJp();
            e10.ltspost(0);
            ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.VISIBLE);

        }

    }

    private void onTx2Click(View vw) {
        shouJp();
        e10.post();

    }

    private boolean onXxbj4Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onTx5Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbjcd)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf(room_name));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.i2xxbj1)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbjcd)).setVisibility(android.view.View.GONE);
            ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.VISIBLE);
            e10.ltstotop();

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("菜单"));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.i2xxbj1)).setVisibility(android.view.View.GONE);
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbjcd)).setVisibility(android.view.View.VISIBLE);
            ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.GONE);

        }

    }

    private void onTx6Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbjcd)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf(room_name));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.i2xxbj1)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbjcd)).setVisibility(android.view.View.GONE);

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("菜单"));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.i2xxbj1)).setVisibility(android.view.View.GONE);
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbjcd)).setVisibility(android.view.View.VISIBLE);
            ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.GONE);

        }
        e10.ltspost(1);

    }

    private boolean onXxbj7Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onTx1Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbjcd)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf(room_name));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.i2xxbj1)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbjcd)).setVisibility(android.view.View.GONE);
            ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.VISIBLE);
            e10.ltstobottom();

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("菜单"));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.i2xxbj1)).setVisibility(android.view.View.GONE);
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbjcd)).setVisibility(android.view.View.VISIBLE);
            ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.GONE);

        }

    }

    private void onTx4Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbjcd)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf(room_name));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.i2xxbj1)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbjcd)).setVisibility(android.view.View.GONE);
            ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.VISIBLE);

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("菜单"));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.i2xxbj1)).setVisibility(android.view.View.GONE);
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbjcd)).setVisibility(android.view.View.VISIBLE);
            ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.GONE);

        }
        open.cn.awg.pro.core.NativeActions.finish(ChatRoomActivity.this);

    }

    private boolean onXxbj6Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onXxbjcdClick(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbjcd)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf(room_name));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.i2xxbj1)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbjcd)).setVisibility(android.view.View.GONE);
            ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.VISIBLE);

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("菜单"));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.i2xxbj1)).setVisibility(android.view.View.GONE);
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.xxbjcd)).setVisibility(android.view.View.VISIBLE);
            ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(ChatRoomActivity.this.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.GONE);

        }

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        TextView i0d2d4d9236 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb3);
        i0d2d4d9236.setOnClickListener(wb3ClickListener);
        i0d2d4d9236.setOnLongClickListener(wb3LongClickListener);

        RecyclerView c2f5ccfa3a = (RecyclerView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.ltsv7lb1);
        c2f5ccfa3a.addOnScrollListener(ltsv7lb1ScrollListener);

        LinearLayout e76307d04a = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.i2xxbj1);
        e76307d04a.setOnTouchListener(i2xxbj1TouchListener);

        TextView ff56a8e7f6 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb1);
        ff56a8e7f6.setOnClickListener(wb1ClickListener);
        ff56a8e7f6.setOnLongClickListener(wb1LongClickListener);

        TextView e943ebd71e = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb4);

        LinearLayout i5f37bb7e71 = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.ltssxxxbj3);

        EditText i32ee139ba1 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        CheckBox i4b3a71b80a = (CheckBox) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.dx_chat_upload);
        i4b3a71b80a.setOnCheckedChangeListener(dxChatUploadCheckedChangeListener);

        Button fb3bffd427 = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an_upload);
        fb3bffd427.setOnClickListener(anUploadClickListener);

        Button i3295d3c478 = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an_disupload);
        i3295d3c478.setOnClickListener(anDisuploadClickListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        LinearLayout i9068f5d66e = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj4);
        i9068f5d66e.setOnTouchListener(xxbj4TouchListener);

        ImageView i4a4248a0f2 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx5);
        i4a4248a0f2.setOnClickListener(tx5ClickListener);

        ImageView i6f2c7751cf = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx6);
        i6f2c7751cf.setOnClickListener(tx6ClickListener);

        LinearLayout i84f04cd47e = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj7);
        i84f04cd47e.setOnTouchListener(xxbj7TouchListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i11aeec1890 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx4);
        i11aeec1890.setOnClickListener(tx4ClickListener);

        LinearLayout i6c4a6b953f = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj6);
        i6c4a6b953f.setOnTouchListener(xxbj6TouchListener);

        LinearLayout d2a1859abe = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbjcd);
        d2a1859abe.setOnClickListener(xxbjcdClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
