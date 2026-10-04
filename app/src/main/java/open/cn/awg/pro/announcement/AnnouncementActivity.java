/**
 ******************************************************************************
 * @file    AnnouncementActivity.java
 * @author  Typheye
 * @brief   Application announcement screen.
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
package open.cn.awg.pro.announcement;


import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONObject;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.ui.list.NativeListAdapter;
import open.cn.awg.pro.ui.list.AnnouncementRowBinder;
import open.cn.awg.pro.music.TuneFreeMusicBridge;
import open.cn.awg.pro.R;
import open.cn.awg.pro.viewer.TextViewerActivity;

public class AnnouncementActivity extends BaseAwgActivity {

    public final AnnouncementActivity lei = this, 类 = this;
    public String um = "";
    public File f;
    public AppRuntimeBridge e1;
    public TuneFreeMusicBridge e12;
    public NativeListAdapter yygglbspq = null;
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
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
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        e1 = new AppRuntimeBridge(this);
        e12 = new TuneFreeMusicBridge(this);
        setContentView(R.layout.announcement);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        csh();
        show(0);

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.announcement_root, R.id.xdbj1, R.id.title_bar);

    }

    public void dqgg() {

        final String def = "欢迎使用腕管Pro，这是一款优秀的应用。您可以前往腕管Pro官网：https://www.typheye.cn/awg，提出建议或意见。如果您有任何问题，可以联系开发者台风眼(QQ498978473)，或者通过邮箱(498978473@qq.com)联系我们！";

        final String h = AppPaths.appPath("data/notice/showing");

        final String cache = AppPaths.appPath("data/notice/cache");

        if (open.cn.awg.pro.core.NativeFiles.exists(AnnouncementActivity.this, (String) (h)) && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(AnnouncementActivity.this, (String) (h)), "")) {

            open.cn.awg.pro.core.NativeFiles.writeText(AnnouncementActivity.this, (String) (cache), (String) (open.cn.awg.pro.core.NativeFiles.readText(AnnouncementActivity.this, (String) (h))));

        } else {

            open.cn.awg.pro.core.NativeFiles.writeText(AnnouncementActivity.this, (String) (cache), (String) (def));

        }

        final String[] name = new String[]{"url", "title", "code"};

        final String[] value = new String[]{cache, "公告", "super1"};
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.launch(AnnouncementActivity.this, (Class<?>) (TextViewerActivity.class), (String[]) (name), (Object[]) (value));

            }

        });

    }

    public void show(int i) {

        final android.widget.TextView tab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AnnouncementActivity.this.getWindow().getDecorView(), R.id.title_bar));

        final android.widget.LinearLayout x1 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AnnouncementActivity.this.getWindow().getDecorView(), R.id.xxbj1));

        final android.widget.LinearLayout x2 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AnnouncementActivity.this.getWindow().getDecorView(), R.id.xxbj6));

        final android.widget.LinearLayout x3 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(AnnouncementActivity.this.getWindow().getDecorView(), R.id.xxbj2));

        if (i == 0) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    tab.setText(String.valueOf("< 应用公告"));
                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                }

            });

        } else if (i == 1) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    tab.setText(String.valueOf("< 历史公告"));
                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        } else if (i == 2) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    tab.setText(String.valueOf("< 应用公告"));
                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        }

    }

    public void csh() {

        RecyclerView list = findViewById(R.id.yyggv7lb1);
        yygglbspq = NativeListAdapter.attach(this, list, R.layout.announcement_item, new AnnouncementRowBinder(this));

    }

    public void jzpd() {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                show(2);
                yygglbspq.clearRows();

                final String set33 = AppPaths.appPath("data/notice/json");

                int[] kj = new int[]{R.id.wb1, R.id.wb3, R.id.wb2};

                String[] jk = new String[]{"", "", ""};

                try {

                    String v = open.cn.awg.pro.core.NativeFiles.readText(AnnouncementActivity.this, (String) (set33));

                    org.json.JSONObject jo = (org.json.JSONObject) (Object) open.cn.awg.pro.core.JsonValues.parseObject(v);

                    JSONObject json = jo;

                    JSONArray list = open.cn.awg.pro.core.JsonValues.array(json, "NoticeList");

                    int i = open.cn.awg.pro.core.JsonValues.length(list);

                    int size = 0;

                    while (size < i) {

                        JSONObject dx = open.cn.awg.pro.core.JsonValues.object(list, size);
                        size++;

                        Object v_title = open.cn.awg.pro.core.JsonValues.value(dx, "title");

                        Object v_data = open.cn.awg.pro.core.JsonValues.value(dx, "data");

                        Object v_date = open.cn.awg.pro.core.JsonValues.value(dx, "date");

                        Object v_writer = open.cn.awg.pro.core.JsonValues.value(dx, "writer");

                        String erji = v_date.toString() + " | " + v_writer.toString() + " 发布";
                        jk = new String[]{v_title.toString(), erji, v_data.toString()};
                        yygglbspq.addRow(kj, jk);

                    }
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            yygglbspq.refreshRows();
                            show(1);

                        }

                    });

                } catch (Throwable e) {

                    e1.tsk("提示", "当前没有公告");
                    show(0);

                }

            }

        });

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(AnnouncementActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

            if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AnnouncementActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "< 应用公告")) {

                open.cn.awg.pro.core.NativeActions.finish(AnnouncementActivity.this);

            } else if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AnnouncementActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "< 公告列表")) {

                show(0);

            }

        }
        return false;

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onTitleBarClick(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AnnouncementActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "< 应用公告")) {

            open.cn.awg.pro.core.NativeActions.finish(AnnouncementActivity.this);

        } else if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AnnouncementActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "< 历史公告")) {

            show(0);

        }

    }

    private void onXdbj3Click(View vw) {
        dqgg();

    }

    private boolean onXdbj3LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AnnouncementActivity.this.getWindow().getDecorView(), R.id.wb5)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AnnouncementActivity.this.getWindow().getDecorView(), R.id.wb6)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj2Click(View vw) {
        jzpd();

    }

    private boolean onXdbj2LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AnnouncementActivity.this.getWindow().getDecorView(), R.id.wb3)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(AnnouncementActivity.this.getWindow().getDecorView(), R.id.wb4)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx2Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(AnnouncementActivity.this);

    }

    private void onTx1Click(View vw) {
        show(0);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        RelativeLayout i20c2777fab = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);
        i20c2777fab.setOnLongClickListener(xdbj3LongClickListener);

        RelativeLayout i3167b45174 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);
        i3167b45174.setOnLongClickListener(xdbj2LongClickListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
