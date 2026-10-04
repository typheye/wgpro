/**
 ******************************************************************************
 * @file    DeepSeekActivity.java
 * @author  Typheye
 * @brief   DeepSeek plugin screen.
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



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.inputmethod.InputMethodManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.ui.widget.XLinearLayoutManager;

@SuppressLint("StaticFieldLeak")
public class DeepSeekActivity extends BaseAwgActivity {

    public static open.cn.awg.pro.ui.list.NativeListAdapter spqc;
    public static androidx.recyclerview.widget.RecyclerView lbc;
    public static LinearLayoutManager mLayoutManager;
    public static Context context;
    public final DeepSeekActivity lei = this, 类 = this;
    public AppRuntimeBridge e1;
    public String namer = "User";
    public boolean isOnce = false;
    public String ml = AppPaths.appPath("data/deepseek/chat");
    private final View.OnClickListener xxbj6ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj6Click(vw);
        }

    };
    private final View.OnTouchListener xxbj8TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj8Touch(vw, me);
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
    private final View.OnTouchListener xxbj7TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj7Touch(vw, me);
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
    private final View.OnTouchListener xxbj1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj1Touch(vw, me);
        }

    };
    private final RecyclerView.OnScrollListener v7lb1ScrollListener = new RecyclerView.OnScrollListener() {

        public void onScrollStateChanged(RecyclerView vw, int ns) {
            onV7lb1ScrollStateChanged(vw, ns);
        }

        public void onScrolled(RecyclerView vw, int dx, int dy) {
            onV7lb1Scrolled(vw, dx, dy);
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
        setContentView(R.layout.deep_seek);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(DeepSeekActivity.this, (String) (AppPaths.appPath("settings/f8/set4.inf"))), "true")) {

        }

        
        open.cn.awg.pro.core.NativeDevice.setKeepScreenOn(DeepSeekActivity.this, !(false));

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(DeepSeekActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(DeepSeekActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(DeepSeekActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(DeepSeekActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));
        ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.deep_seek_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

        if (java.util.Objects.equals(a, "1")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj1)), u, 0, u, 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj4)), u, 0, u, 0);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.title_bar));
            open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "15dp", 0, "3dp");
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "2")) {

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (open.cn.awg.pro.core.NativeFiles.exists(DeepSeekActivity.this, (String) (a001)) || java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(DeepSeekActivity.this, (String) (a001)), "true")) {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.title_bar));
                open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "3dp", 0, "3dp");
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            } else {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.title_bar));
                open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "9dp", 0, "9dp");
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            }
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj1)), 0, 0, 0, 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj4)), 0, 0, 0, 0);

        } else if (java.util.Objects.equals(a, "3")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj1)), u, 0, u, 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj4)), u, 0, u, 0);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.title_bar));
            open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "15dp", 0, "3dp");
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "4")) {

            int uih = open.cn.awg.pro.core.DisplayValues.pxToDp(DeepSeekActivity.this, open.cn.awg.pro.core.DisplayValues.systemBarHeight(DeepSeekActivity.this, "status_bar_height"));
            uih = uih + 9;

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.title_bar));
            open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, uih + "dp", 0, "9dp");
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj1)), 0, 0, 0, 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj4)), 0, 0, 0, 0);

        }

    }

    public void tobottom() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                lbc.scrollToPosition(spqc.rowCount() - 1);

            }

        });

    }

    public void totop() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                lbc.scrollToPosition(0);

            }

        });

    }

    public void del() {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                show(1);
                open.cn.awg.pro.core.NativeActions.sleep(500);
                open.cn.awg.pro.core.NativeFiles.delete(DeepSeekActivity.this, (String) (ml), false);
                open.cn.awg.pro.core.NativeActions.sleep(250);
                update();
                open.cn.awg.pro.core.NativeActions.sleep(250);
                show(0);

            }

        });

    }

    public void show(int i) {

        if (i == 0) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    lbc.requestFocus();
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("< DeepSeek"));
                    ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(""));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj3s)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.kp7)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                }

            });

        } else if (i == 1) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("DeepSeek"));
                    ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(""));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj3s)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.kp7)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        } else if (i == 2) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("发送消息"));
                    ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(""));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj3s)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.kp7)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        } else if (i == 3) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("DeepSeek"));
                    ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(""));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj3s)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.kp7)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        }

    }

    public void csh() {
        lbc = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.v7lb1));

        context = AwgProApplication.getContext();
        mLayoutManager = new XLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        lbc.setLayoutManager(mLayoutManager);
        spqc = open.cn.awg.pro.ui.list.NativeListAdapter.attach(DeepSeekActivity.this, lbc, R.layout.runtime_message_item, new open.cn.awg.pro.ui.list.RuntimeMessageRowBinder(DeepSeekActivity.this), new open.cn.awg.pro.ui.list.NativeListAdapter.RowBinder() {

            public void bind(View vw, java.util.Map<Integer, Object> row, int pn) {

                

                final int f = open.cn.awg.pro.core.TextValues.intValue(row.get(-1), -1);

                if (f == -1) {

                    String color0 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTextTrue2);

                    String color1 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorBlack);

                    String color2 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTextU1);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb3)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color2));
                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.kp1)).setCardBackgroundColor(open.cn.awg.pro.core.NativeViewValues.color(color0));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("right"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.runtime_message_item_root)).setClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.runtime_message_item_root)).setLongClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                } else if (f == 0) {

                    String color0 = "#00000000";

                    String color1 = "#00000000";

                    String color2 = "#00000000";
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb3)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color2));
                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.kp1)).setCardBackgroundColor(open.cn.awg.pro.core.NativeViewValues.color(color0));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("left"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.runtime_message_item_root)).setClickable(java.lang.Boolean.TRUE.equals(false));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.runtime_message_item_root)).setLongClickable(java.lang.Boolean.TRUE.equals(false));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                } else if (f == 1) {

                    String color0 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorWBK);

                    String color1 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTextU1);

                    String color2 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTextU1);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb3)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color2));
                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.kp1)).setCardBackgroundColor(open.cn.awg.pro.core.NativeViewValues.color(color0));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("left"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.runtime_message_item_root)).setClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.runtime_message_item_root)).setLongClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                } else {

                    String color0 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorWBK);

                    String color1 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTextU1);

                    String color2 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTextU1);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb3)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color2));
                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.kp1)).setCardBackgroundColor(open.cn.awg.pro.core.NativeViewValues.color(color0));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("left"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.runtime_message_item_root)).setClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.runtime_message_item_root)).setLongClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            }

        });

    }

    public void update() {

        if (open.cn.awg.pro.core.NativeFiles.exists(DeepSeekActivity.this, (String) (ml)) && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(DeepSeekActivity.this, (String) (ml)), "")) {

            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    if (!isOnce) {

                        open.cn.awg.pro.core.NativeActions.sleep(1000);

                    }

                    int[] kj = new int[]{R.id.wb1, R.id.wb2, R.id.wb3, -1};

                    Object[] jk = new Object[]{"", "", "", 0};

                    int size = 0;

                    ArrayList arr = new ArrayList();

                    String hcw = open.cn.awg.pro.core.NativeFiles.readText(DeepSeekActivity.this, (String) (ml));

                    String[] hcw2 = open.cn.awg.pro.core.TextValues.split(hcw, "\n", true);

                    for (String hcw3 : hcw2) {

                        hcw3 = open.cn.awg.pro.core.TextValues.trim(hcw3);

                        if (hcw3.length() != 0) {

                            try {

                                String jname = open.cn.awg.pro.core.TextValues.slice(hcw3, 0, 1);

                                if (java.util.Objects.equals(jname, "Q")) {

                                    jname = "User";

                                } else {

                                    jname = "ChatGPT";

                                }

                                String jdata = open.cn.awg.pro.core.TextValues.slice(hcw3, 22);
                                jdata = open.cn.awg.pro.core.TextValues.replace(jdata, "<br>", "\n", true);

                                String date = open.cn.awg.pro.core.TextValues.slice(hcw3, 2, 21);
                                size++;

                                if (java.util.Objects.equals(jname, namer)) {

                                    jk = new Object[]{jdata, jname, date, -1};

                                } else {

                                    jk = new Object[]{jdata, jname, date, 1};

                                }

                                HashMap hs = new HashMap();

                                for (int ii = 0; ii < kj.length; ii++) {

                                    hs.put(kj[ii], jk[ii]);

                                }
                                arr.add(hs);

                            } catch (Throwable __$_e__) {

                            }

                        }

                    }

                    HashMap hs = new HashMap();
                    hs.put(kj[0], "");
                    hs.put(kj[1], "");
                    hs.put(kj[2], "");
                    hs.put(kj[3], 0);
                    arr.add(hs);
                    arr.add(hs);
                    spqc.replaceRows(arr);
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            spqc.refreshRows();

                        }

                    });

                    if (!isOnce) {

                        isOnce = true;
                        show(0);
                        tobottom();

                    }

                }

            });

        } else {

            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    spqc.clearRows();

                    if (!isOnce) {

                        open.cn.awg.pro.core.NativeActions.sleep(1000);
                        isOnce = true;
                        show(0);

                    }

                }

            });

        }

    }

    public void add(Object name, Object msg) {
        msg = open.cn.awg.pro.core.TextValues.trim(msg);
        msg = open.cn.awg.pro.core.TextValues.replace(msg, "\n", "<br>", true);

        String d = name + " " + open.cn.awg.pro.core.NativeDevice.formatDate(0) + " " + msg;

        if (open.cn.awg.pro.core.NativeFiles.exists(DeepSeekActivity.this, (String) (ml)) && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(DeepSeekActivity.this, (String) (ml)), "")) {

            open.cn.awg.pro.core.NativeFiles.writeText(DeepSeekActivity.this, (String) (ml), (String) (open.cn.awg.pro.core.NativeFiles.readText(DeepSeekActivity.this, (String) (ml)) + "\n" + d));

        } else {

            open.cn.awg.pro.core.NativeFiles.writeText(DeepSeekActivity.this, (String) (ml), (String) (d));

        }
        update();

    }

    public void jz() {
        show(1);
        csh();
        update();

    }

    public void post() {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                String a = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.bjk1)).getText().toString();
                a = open.cn.awg.pro.core.TextValues.trim(a);

                if (a.length() == 0) {

                    e1.tsk("提示", "消息不能为空");

                } else {

                    show(1);
                    add("Q", a);
                    open.cn.awg.pro.core.NativeActions.sleep(500);
                    show(0);

                    String bak = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf("https://api.sayqz.com/chatgpt/?msg=" + a + "&sys="), null, (String) ("utf-8"), null, false, null, 0, 0, null).bodyForHttp200();

                    if (!java.util.Objects.equals(bak, null)) {

                        try {

                            org.json.JSONObject jo = (org.json.JSONObject) (Object) open.cn.awg.pro.core.JsonValues.parseObject(bak);

                            JSONObject json = jo;

                            JSONArray list = open.cn.awg.pro.core.JsonValues.array(json, "choices");

                            int size = open.cn.awg.pro.core.JsonValues.length(list);

                            while (size > 0) {

                                size = size - 1;

                                JSONObject dx = open.cn.awg.pro.core.JsonValues.object(list, size);

                                Object msg = open.cn.awg.pro.core.JsonValues.value(dx, "message");
                                bak = e1.readJson(String.valueOf(msg), "content");

                            }
                            add("A", bak);

                        } catch (Throwable __$_e__) {

                            add("A", "很抱歉，程序似乎出现了异常");

                        }

                    } else {

                        add("A", "很抱歉，网络连接不通畅，请检查后重试");

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

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(DeepSeekActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(DeepSeekActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));
        ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.deep_seek_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

    }

    public void onStart() {
        super.onStart();

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(DeepSeekActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(DeepSeekActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));
        ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.deep_seek_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

        androidx.recyclerview.widget.RecyclerView v7lb1 = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.v7lb1));
        v7lb1.requestFocus();

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(DeepSeekActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(DeepSeekActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));
        ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.deep_seek_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

    }

    public void onResume() {
        super.onResume();

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(DeepSeekActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(DeepSeekActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));
        ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.deep_seek_root)).setFitsSystemWindows(java.lang.Boolean.TRUE.equals(false));
        applyFullscreenWindow();

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

            if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "< DeepSeek")) {

                open.cn.awg.pro.core.NativeActions.finish(DeepSeekActivity.this);

            } else {

            }

        }
        return false;

    }

    private void onTitleBarClick(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "< DeepSeek")) {

            open.cn.awg.pro.core.NativeActions.finish(DeepSeekActivity.this);

        }

    }

    private void onWb3Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String z = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.wb3)).getText().toString();
                z = open.cn.awg.pro.core.TextValues.slice(z, "[提示] ");

                if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.wb3)).getText().toString(), "[提示] 发送成功")) {

                    z = z + "\n(ps:长按提示可隐藏哦)";

                }
                e1.tsk("详细信息", z);

            }

        });

    }

    private boolean onWb3LongClick(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.wb3)).getText().toString(), "[提示] 发送成功")) {

        }
        return true;

    }

    private void onV7lb1ScrollStateChanged(RecyclerView vw, int ns) {

        if (ns == RecyclerView.SCROLL_STATE_IDLE) {

            if (!vw.canScrollVertically(-1)) {

            } else if (!vw.canScrollVertically(1)) {

            } else {

            }

        }

    }

    private void onV7lb1Scrolled(RecyclerView vw, int dx, int dy) {

    }

    private boolean onXxbj1Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onWb1Click(View vw) {
        show(3);

    }

    private boolean onWb1LongClick(View vw) {
        show(2);
        return true;

    }

    private boolean onBjk1Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(DeepSeekActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private void onTx3Click(View vw) {
        shouJp();
        show(0);

    }

    private void onTx2Click(View vw) {
        shouJp();
        post();

    }

    private boolean onXxbj4Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onTx1Click(View vw) {
        totop();
        show(0);

    }

    private void onTx4Click(View vw) {
        show(2);

    }

    private boolean onXxbj7Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onTx5Click(View vw) {
        tobottom();
        show(0);

    }

    private void onTx6Click(View vw) {
        del();
        show(0);

    }

    private boolean onXxbj8Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onXxbj6Click(View vw) {
        show(0);

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

        RecyclerView f085d7c0f8 = (RecyclerView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.v7lb1);
        f085d7c0f8.addOnScrollListener(v7lb1ScrollListener);

        LinearLayout e1433e26d6 = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj1);
        e1433e26d6.setOnTouchListener(xxbj1TouchListener);

        TextView ff56a8e7f6 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb1);
        ff56a8e7f6.setOnClickListener(wb1ClickListener);
        ff56a8e7f6.setOnLongClickListener(wb1LongClickListener);

        LinearLayout i469306663f = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj3s);

        EditText i32ee139ba1 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        LinearLayout i9068f5d66e = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj4);
        i9068f5d66e.setOnTouchListener(xxbj4TouchListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i11aeec1890 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx4);
        i11aeec1890.setOnClickListener(tx4ClickListener);

        LinearLayout i84f04cd47e = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj7);
        i84f04cd47e.setOnTouchListener(xxbj7TouchListener);

        ImageView i4a4248a0f2 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx5);
        i4a4248a0f2.setOnClickListener(tx5ClickListener);

        ImageView i6f2c7751cf = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx6);
        i6f2c7751cf.setOnClickListener(tx6ClickListener);

        LinearLayout i3f6c9f386b = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj8);
        i3f6c9f386b.setOnTouchListener(xxbj8TouchListener);

        LinearLayout i6c4a6b953f = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj6);
        i6c4a6b953f.setOnClickListener(xxbj6ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
