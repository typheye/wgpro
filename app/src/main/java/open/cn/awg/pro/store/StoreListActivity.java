/**
 ******************************************************************************
 * @file    StoreListActivity.java
 * @author  Typheye
 * @brief   Store list screen.
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
package open.cn.awg.pro.store;


import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
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
import open.cn.awg.pro.R;
import open.cn.awg.pro.ui.list.StoreAppRowBinder;
import open.cn.awg.pro.ui.list.StoreMusicRowBinder;
import open.cn.awg.pro.ui.list.StoreVideoRowBinder;

public class StoreListActivity extends BaseAwgActivity {

    public final StoreListActivity lei = this, 类 = this;
    public String um = "";
    public File f;
    public String ll = "";
    public String type = "";
    public String search = "";
    // 不能在这里 new：字段初始化在 attachBaseContext 之前执行，AppRuntimeBridge 里
    // LocalDatabaseBridge 会取 applicationContext 得到 null 直接崩溃（页面无法打开）。改到 onCreate 里建。
    public AppRuntimeBridge e1;
    public NativeListAdapter spq = null;
    public String src_url = "";
    public int v_totalPages = 0;
    public int v_nowPages = 0;
    public Drawable dbe;
    public boolean state_fun_app = false;
    public boolean state_fun_video = false;
    public boolean state_fun_music = false;
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
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
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
        setContentView(R.layout.store_list);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        type = StoreListActivity.this.getIntent().getStringExtra(String.valueOf("type"));
        search = StoreListActivity.this.getIntent().getStringExtra(String.valueOf("search"));
        csh();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.store_list_root, R.id.xdbj1, R.id.title_bar);

    }

    public void show(int i) {

        final android.widget.TextView tab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreListActivity.this.getWindow().getDecorView(), R.id.title_bar));

        final android.widget.LinearLayout x2 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(StoreListActivity.this.getWindow().getDecorView(), R.id.xxbj6));

        final android.widget.LinearLayout x3 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(StoreListActivity.this.getWindow().getDecorView(), R.id.xxbj4));

        if (i == 1) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                }

            });

        } else if (i == 2) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                }

            });

        }
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                if (java.util.Objects.equals(type, "app")) {

                    tab.setText(String.valueOf("应用列表"));

                } else if (java.util.Objects.equals(type, "video")) {

                    tab.setText(String.valueOf("视频列表"));

                } else if (java.util.Objects.equals(type, "music")) {

                    tab.setText(String.valueOf("歌曲列表"));

                }

            }

        });

    }

    public void csh() {

        String set67 = AppPaths.appPath("settings/set7.inf");
        src_url = open.cn.awg.pro.core.NativeFiles.readText(StoreListActivity.this, (String) (set67));
        show(1);

        if (java.util.Objects.equals(type, "app")) {

            fun_app(0);

        } else if (java.util.Objects.equals(type, "video")) {

            fun_video(0);

        } else if (java.util.Objects.equals(type, "music")) {

            fun_music(0);

        }

    }

    public void but_back() {

        if (v_nowPages > 1) {

            if (java.util.Objects.equals(type, "app")) {

                fun_app(-1);

            } else if (java.util.Objects.equals(type, "video")) {

                fun_video(-1);

            } else if (java.util.Objects.equals(type, "music")) {

                fun_music(-1);

            }

        } else {

            e1.tsk("提示", "已经在第一页了噢");

        }

    }

    public void but_next() {

        if (v_nowPages < v_totalPages) {

            if (java.util.Objects.equals(type, "app")) {

                fun_app(1);

            } else if (java.util.Objects.equals(type, "video")) {

                fun_video(1);

            } else if (java.util.Objects.equals(type, "music")) {

                fun_music(1);

            }

        } else {

            e1.tsk("提示", "已经在最后一页了噢");

        }

    }

    public void fun_music(int y_) {

        if (!state_fun_music) {

            state_fun_music = true;

            final int y = y_;

            RecyclerView list = findViewById(R.id.v7lb1);
            spq = NativeListAdapter.attach(this, list, R.layout.store_music_item, new StoreMusicRowBinder(this));

            final int[] kj = new int[]{R.id.wb1, R.id.wb3, -1};

            final android.widget.TextView wb2 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreListActivity.this.getWindow().getDecorView(), R.id.wb2));
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    show(1);
                    open.cn.awg.pro.core.NativeActions.sleep(500);

                    boolean canshow = false;

                    String url = src_url + "/api.php";

                    String[] post = new String[]{"type=music", "code=" + search, "page=1"};

                    if (y != 0) {

                        if (y == 1) {

                            int yy = v_nowPages + 1;
                            post[2] = "page=" + open.cn.awg.pro.core.TextValues.string(yy);

                        } else {

                            int yy = v_nowPages - 1;
                            post[2] = "page=" + open.cn.awg.pro.core.TextValues.string(yy);

                        }

                    }

                    if (e1.yz()) {

                        String v = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (post), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                        if (!java.util.Objects.equals(v, null)) {

                            try {

                                org.json.JSONObject jo = (org.json.JSONObject) (Object) open.cn.awg.pro.core.JsonValues.parseObject(v);

                                JSONObject json = jo;

                                Object v_msg = open.cn.awg.pro.core.JsonValues.value(json, "msg");

                                Object v_code = open.cn.awg.pro.core.JsonValues.value(json, "code");

                                if (java.util.Objects.equals(v_code, 200)) {

                                    Object v_totalItems = open.cn.awg.pro.core.JsonValues.value(json, "totalItems");
                                    v_totalPages = Integer.parseInt(String.valueOf(open.cn.awg.pro.core.JsonValues.value(json, "totalPages")));

                                    Object v_nowItems = open.cn.awg.pro.core.JsonValues.value(json, "nowItems");
                                    v_nowPages = Integer.parseInt(String.valueOf(open.cn.awg.pro.core.JsonValues.value(json, "nowPages")));

                                    if (java.util.Objects.equals(v_totalItems, 0)) {

                                    } else {

                                        JSONArray list = open.cn.awg.pro.core.JsonValues.array(json, "info");

                                        int size = open.cn.awg.pro.core.JsonValues.length(list);

                                        while (size > 0) {

                                            size--;

                                            JSONObject dx = open.cn.awg.pro.core.JsonValues.object(list, size);

                                            Object v_l_title = open.cn.awg.pro.core.JsonValues.value(dx, "title");

                                            Object v_l_text = open.cn.awg.pro.core.JsonValues.value(dx, "text");

                                            Object v_l_aurl = open.cn.awg.pro.core.JsonValues.value(dx, "aurl");

                                            Object v_l_id = open.cn.awg.pro.core.JsonValues.value(dx, "id");

                                            Object[] jk = new Object[]{v_l_title, v_l_text, v_l_id};
                                            spq.addRow(kj, jk);

                                        }

                                        final String wb2_zf = "第" + open.cn.awg.pro.core.TextValues.string(v_nowPages) + "/" + open.cn.awg.pro.core.TextValues.string(v_totalPages) + "页";
                                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                            public void run() {

                                                wb2.setText(String.valueOf(wb2_zf));
                                                spq.refreshRows();

                                            }

                                        });
                                        canshow = true;

                                    }

                                }

                            } catch (Throwable e) {

                                e1.upload_error(e, "p3.fun_music(int)");

                            }

                        }

                    }

                    if (canshow) {

                        show(2);

                    } else {

                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreListActivity.this.getWindow().getDecorView(), R.id.wb1)).setText(String.valueOf("加载失败"));

                            }

                        });

                    }
                    state_fun_music = false;

                }

            });

        } else {

            e1.tsk("提示", "操作过于频繁，请稍后重试");

        }

    }

    public void fun_video(int y_) {

        if (!state_fun_video) {

            state_fun_video = true;

            final int y = y_;

            RecyclerView list = findViewById(R.id.v7lb1);
            spq = NativeListAdapter.attach(this, list, R.layout.store_video_item, new StoreVideoRowBinder(this));

            final int[] kj = new int[]{R.id.wb1, R.id.wb3, -1};

            final android.widget.TextView wb2 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreListActivity.this.getWindow().getDecorView(), R.id.wb2));
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    show(1);
                    open.cn.awg.pro.core.NativeActions.sleep(500);

                    boolean canshow = false;

                    String url = src_url + "/api.php";

                    String[] post = new String[]{"type=video", "code=" + search, "page=1"};

                    if (y != 0) {

                        if (y == 1) {

                            int yy = v_nowPages + 1;
                            post[2] = "page=" + open.cn.awg.pro.core.TextValues.string(yy);

                        } else {

                            int yy = v_nowPages - 1;
                            post[2] = "page=" + open.cn.awg.pro.core.TextValues.string(yy);

                        }

                    }

                    if (e1.yz()) {

                        String v = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (post), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                        if (!java.util.Objects.equals(v, null)) {

                            try {

                                org.json.JSONObject jo = (org.json.JSONObject) (Object) open.cn.awg.pro.core.JsonValues.parseObject(v);

                                JSONObject json = jo;

                                Object v_msg = open.cn.awg.pro.core.JsonValues.value(json, "msg");

                                Object v_code = open.cn.awg.pro.core.JsonValues.value(json, "code");

                                if (java.util.Objects.equals(v_code, 200)) {

                                    Object v_totalItems = open.cn.awg.pro.core.JsonValues.value(json, "totalItems");
                                    v_totalPages = Integer.parseInt(String.valueOf(open.cn.awg.pro.core.JsonValues.value(json, "totalPages")));

                                    Object v_nowItems = open.cn.awg.pro.core.JsonValues.value(json, "nowItems");
                                    v_nowPages = Integer.parseInt(String.valueOf(open.cn.awg.pro.core.JsonValues.value(json, "nowPages")));

                                    if (java.util.Objects.equals(v_totalItems, 0)) {

                                    } else {

                                        JSONArray list = open.cn.awg.pro.core.JsonValues.array(json, "info");

                                        int size = open.cn.awg.pro.core.JsonValues.length(list);

                                        while (size > 0) {

                                            size--;

                                            JSONObject dx = open.cn.awg.pro.core.JsonValues.object(list, size);

                                            Object v_l_title = open.cn.awg.pro.core.JsonValues.value(dx, "title");

                                            Object v_l_text = open.cn.awg.pro.core.JsonValues.value(dx, "text");
                                            v_l_text = open.cn.awg.pro.core.TextValues.replace(v_l_text, "<br>", " ");

                                            Object v_l_aurl = open.cn.awg.pro.core.JsonValues.value(dx, "aurl");

                                            Object v_l_id = open.cn.awg.pro.core.JsonValues.value(dx, "id");

                                            Object[] jk = new Object[]{v_l_title, v_l_text, v_l_id};
                                            spq.addRow(kj, jk);

                                        }

                                        final String wb2_zf = "第" + open.cn.awg.pro.core.TextValues.string(v_nowPages) + "/" + open.cn.awg.pro.core.TextValues.string(v_totalPages) + "页";
                                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                            public void run() {

                                                wb2.setText(String.valueOf(wb2_zf));
                                                spq.refreshRows();

                                            }

                                        });
                                        canshow = true;

                                    }

                                }

                            } catch (Throwable e) {

                                e1.upload_error(e, "p3.fun_video(int)");

                            }

                        }

                    }

                    if (canshow) {

                        show(2);

                    } else {

                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreListActivity.this.getWindow().getDecorView(), R.id.wb1)).setText(String.valueOf("加载失败"));

                            }

                        });

                    }
                    state_fun_video = false;

                }

            });

        } else {

            e1.tsk("提示", "操作过于频繁，请稍后重试");

        }

    }

    public void fun_app(int y_) {

        if (!state_fun_app) {

            state_fun_app = true;

            final int y = y_;

            RecyclerView list = findViewById(R.id.v7lb1);
            spq = NativeListAdapter.attach(this, list, R.layout.store_app_item, new StoreAppRowBinder(this));

            final int[] kj = new int[]{R.id.tx1, R.id.wb1, R.id.wb3, -1};

            final android.widget.TextView wb2 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreListActivity.this.getWindow().getDecorView(), R.id.wb2));
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    show(1);
                    open.cn.awg.pro.core.NativeActions.sleep(500);

                    boolean canshow = false;

                    String url = src_url + "/api.php";

                    String[] post = new String[]{"type=app", "code=" + search, "page=1"};

                    if (y != 0) {

                        if (y == 1) {

                            int yy = v_nowPages + 1;
                            post[2] = "page=" + open.cn.awg.pro.core.TextValues.string(yy);

                        } else {

                            int yy = v_nowPages - 1;
                            post[2] = "page=" + open.cn.awg.pro.core.TextValues.string(yy);

                        }

                    }

                    if (e1.yz()) {

                        String v = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (post), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                        if (!java.util.Objects.equals(v, null)) {

                            try {

                                org.json.JSONObject jo = (org.json.JSONObject) (Object) open.cn.awg.pro.core.JsonValues.parseObject(v);

                                JSONObject json = jo;

                                Object v_msg = open.cn.awg.pro.core.JsonValues.value(json, "msg");

                                Object v_code = open.cn.awg.pro.core.JsonValues.value(json, "code");

                                if (java.util.Objects.equals(v_code, 200)) {

                                    Object v_totalItems = open.cn.awg.pro.core.JsonValues.value(json, "totalItems");
                                    v_totalPages = Integer.parseInt(String.valueOf(open.cn.awg.pro.core.JsonValues.value(json, "totalPages")));

                                    Object v_nowItems = open.cn.awg.pro.core.JsonValues.value(json, "nowItems");
                                    v_nowPages = Integer.parseInt(String.valueOf(open.cn.awg.pro.core.JsonValues.value(json, "nowPages")));

                                    if (java.util.Objects.equals(v_totalItems, 0)) {

                                    } else {

                                        JSONArray list = open.cn.awg.pro.core.JsonValues.array(json, "info");

                                        int size = open.cn.awg.pro.core.JsonValues.length(list);

                                        while (size > 0) {

                                            size--;

                                            JSONObject dx = open.cn.awg.pro.core.JsonValues.object(list, size);

                                            Object v_l_appname = open.cn.awg.pro.core.JsonValues.value(dx, "appname");

                                            Object v_l_appsize = open.cn.awg.pro.core.JsonValues.value(dx, "appsize");

                                            Object v_l_apptext = open.cn.awg.pro.core.JsonValues.value(dx, "apptext");

                                            Object v_l_appdown = open.cn.awg.pro.core.JsonValues.value(dx, "appdown");

                                            Object v_l_appid = open.cn.awg.pro.core.JsonValues.value(dx, "appid");

                                            String jian = v_l_appsize + " | " + v_l_apptext;

                                            String img_url = src_url + "/app/" + v_l_appid + "/app-image/icon.png";
                                            dbe = new BitmapDrawable(lei.getResources(), com.demo.e4.getURLimage(img_url));

                                            Object[] jk = new Object[]{dbe, v_l_appname, jian, v_l_appid};
                                            spq.addRow(kj, jk);

                                        }

                                        final String wb2_zf = "第" + open.cn.awg.pro.core.TextValues.string(v_nowPages) + "/" + open.cn.awg.pro.core.TextValues.string(v_totalPages) + "页";
                                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                            public void run() {

                                                wb2.setText(String.valueOf(wb2_zf));
                                                spq.refreshRows();

                                            }

                                        });
                                        canshow = true;

                                    }

                                }

                            } catch (Throwable e) {

                                e1.upload_error(e, "p3.fun_app(int)");

                            }

                        }

                    }

                    if (canshow) {

                        show(2);

                    } else {

                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreListActivity.this.getWindow().getDecorView(), R.id.wb1)).setText(String.valueOf("加载失败"));

                            }

                        });

                    }
                    state_fun_app = false;

                }

            });

        } else {

            e1.tsk("提示", "操作过于频繁，请稍后重试");

        }

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(StoreListActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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
        open.cn.awg.pro.core.NativeActions.finish(StoreListActivity.this);

    }

    private void onTx1Click(View vw) {
        but_back();

    }

    private void onTx3Click(View vw) {
        but_next();

    }

    private void onTx2Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(StoreListActivity.this);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        TextView i7c3e3c6b5d = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb2);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
