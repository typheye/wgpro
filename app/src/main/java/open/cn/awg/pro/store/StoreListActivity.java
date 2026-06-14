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
import open.cn.awg.pro.R;
import open.cn.awg.pro.ui.list.StoreAppItemActivity;
import open.cn.awg.pro.ui.list.StoreMusicItemActivity;
import open.cn.awg.pro.ui.list.StoreVideoItemActivity;

public class StoreListActivity extends BaseAwgActivity {

    public final StoreListActivity lei = this, 类 = this;
    public String um = "";
    public File f;
    public String ll = "";
    public String type = "";
    public String search = "";
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public i.runlibrary.app.v.v7lb$UserAdapter spq = null;
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
        setContentView(R.layout.store_list);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        type = sj.hqtz("type");
        search = sj.hqtz("search");
        csh();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.store_list_root, R.id.xdbj1, R.id.title_bar);

    }

    public void show(int i) {

        final i.runlibrary.app.v.wb tab = st.wb(R.id.title_bar);

        final i.runlibrary.app.v.xxbj x2 = st.xxbj(R.id.xxbj6);

        final i.runlibrary.app.v.xxbj x3 = st.xxbj(R.id.xxbj4);

        if (i == 1) {

            gj.jmxc(new Runnable() {

                public void run() {

                    x2.kjd(0);
                    x3.kjd(8);

                }

            });

        } else if (i == 2) {

            gj.jmxc(new Runnable() {

                public void run() {

                    x2.kjd(8);
                    x3.kjd(0);

                }

            });

        }
        gj.jmxc(new Runnable() {

            public void run() {

                if (zf.dy(type, "app")) {

                    tab.zf("< 应用列表");

                } else if (zf.dy(type, "video")) {

                    tab.zf("< 视频列表");

                } else if (zf.dy(type, "music")) {

                    tab.zf("< 歌曲列表");

                }

            }

        });

    }

    public void csh() {

        String set67 = AppPaths.appPath("settings/set7.inf");
        src_url = wj.dqwb(set67);
        show(1);

        if (zf.dy(type, "app")) {

            fun_app(0);

        } else if (zf.dy(type, "video")) {

            fun_video(0);

        } else if (zf.dy(type, "music")) {

            fun_music(0);

        }

    }

    public void but_back() {

        if (v_nowPages > 1) {

            if (zf.dy(type, "app")) {

                fun_app(-1);

            } else if (zf.dy(type, "video")) {

                fun_video(-1);

            } else if (zf.dy(type, "music")) {

                fun_music(-1);

            }

        } else {

            e1.tsk("提示", "已经在第一页了噢");

        }

    }

    public void but_next() {

        if (v_nowPages < v_totalPages) {

            if (zf.dy(type, "app")) {

                fun_app(1);

            } else if (zf.dy(type, "video")) {

                fun_video(1);

            } else if (zf.dy(type, "music")) {

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

            i.runlibrary.app.v.v7lb lb = st.v7lb(R.id.v7lb1);
            spq = (i.runlibrary.app.v.v7lb$UserAdapter) lb.v7lbspq(StoreMusicItemActivity.class, R.layout.store_music_item);

            final int[] kj = new int[]{R.id.wb1, R.id.wb3, -1};

            final i.runlibrary.app.v.wb wb2 = st.wb(R.id.wb2);
            gj.xc(new Thread() {

                public void run() {

                    show(1);
                    gj.zt(500);

                    boolean canshow = false;

                    String url = src_url + "/api.php";

                    String[] post = new String[]{"type=music", "code=" + search, "page=1"};

                    if (y != 0) {

                        if (y == 1) {

                            int yy = v_nowPages + 1;
                            post[2] = "page=" + sj.zh().zstring(yy);

                        } else {

                            int yy = v_nowPages - 1;
                            post[2] = "page=" + sj.zh().zstring(yy);

                        }

                    }

                    if (e1.yz()) {

                        String v = wl.hq(url, post, "utf-8", null, true, null, 20000, 20000, null);

                        if (!zf.dy(v, null)) {

                            try {

                                i.runlibrary.app.zf$json jo = (i.runlibrary.app.zf$json) zf.json(v);

                                JSONObject json = jo.json;

                                Object v_msg = jo.hq(json, "msg");

                                Object v_code = jo.hq(json, "code");

                                if (zf.dy(v_code, 200)) {

                                    Object v_totalItems = jo.hq(json, "totalItems");
                                    v_totalPages = Integer.parseInt(String.valueOf(jo.hq(json, "totalPages")));

                                    Object v_nowItems = jo.hq(json, "nowItems");
                                    v_nowPages = Integer.parseInt(String.valueOf(jo.hq(json, "nowPages")));

                                    if (zf.dy(v_totalItems, 0)) {

                                    } else {

                                        JSONArray list = jo.dxlb(json, "info");

                                        int size = jo.cd(list);

                                        while (size > 0) {

                                            size--;

                                            JSONObject dx = jo.dx(list, size);

                                            Object v_l_title = jo.hq(dx, "title");

                                            Object v_l_text = jo.hq(dx, "text");

                                            Object v_l_aurl = jo.hq(dx, "aurl");

                                            Object v_l_id = jo.hq(dx, "id");

                                            Object[] jk = new Object[]{v_l_title, v_l_text, v_l_id};
                                            spq.j(kj, jk);

                                        }

                                        final String wb2_zf = "第" + sj.zh().zstring(v_nowPages) + "/" + sj.zh().zstring(v_totalPages) + "页";
                                        gj.jmxc(new Runnable() {

                                            public void run() {

                                                wb2.zf(wb2_zf);
                                                spq.sx();

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

                        gj.jmxc(new Runnable() {

                            public void run() {

                                st.wb(R.id.wb1).zf("加载失败");

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

            i.runlibrary.app.v.v7lb lb = st.v7lb(R.id.v7lb1);
            spq = (i.runlibrary.app.v.v7lb$UserAdapter) lb.v7lbspq(StoreVideoItemActivity.class, R.layout.store_video_item);

            final int[] kj = new int[]{R.id.wb1, R.id.wb3, -1};

            final i.runlibrary.app.v.wb wb2 = st.wb(R.id.wb2);
            gj.xc(new Thread() {

                public void run() {

                    show(1);
                    gj.zt(500);

                    boolean canshow = false;

                    String url = src_url + "/api.php";

                    String[] post = new String[]{"type=video", "code=" + search, "page=1"};

                    if (y != 0) {

                        if (y == 1) {

                            int yy = v_nowPages + 1;
                            post[2] = "page=" + sj.zh().zstring(yy);

                        } else {

                            int yy = v_nowPages - 1;
                            post[2] = "page=" + sj.zh().zstring(yy);

                        }

                    }

                    if (e1.yz()) {

                        String v = wl.hq(url, post, "utf-8", null, true, null, 20000, 20000, null);

                        if (!zf.dy(v, null)) {

                            try {

                                i.runlibrary.app.zf$json jo = (i.runlibrary.app.zf$json) zf.json(v);

                                JSONObject json = jo.json;

                                Object v_msg = jo.hq(json, "msg");

                                Object v_code = jo.hq(json, "code");

                                if (zf.dy(v_code, 200)) {

                                    Object v_totalItems = jo.hq(json, "totalItems");
                                    v_totalPages = Integer.parseInt(String.valueOf(jo.hq(json, "totalPages")));

                                    Object v_nowItems = jo.hq(json, "nowItems");
                                    v_nowPages = Integer.parseInt(String.valueOf(jo.hq(json, "nowPages")));

                                    if (zf.dy(v_totalItems, 0)) {

                                    } else {

                                        JSONArray list = jo.dxlb(json, "info");

                                        int size = jo.cd(list);

                                        while (size > 0) {

                                            size--;

                                            JSONObject dx = jo.dx(list, size);

                                            Object v_l_title = jo.hq(dx, "title");

                                            Object v_l_text = jo.hq(dx, "text");
                                            v_l_text = zf.th(v_l_text, "<br>", " ");

                                            Object v_l_aurl = jo.hq(dx, "aurl");

                                            Object v_l_id = jo.hq(dx, "id");

                                            Object[] jk = new Object[]{v_l_title, v_l_text, v_l_id};
                                            spq.j(kj, jk);

                                        }

                                        final String wb2_zf = "第" + sj.zh().zstring(v_nowPages) + "/" + sj.zh().zstring(v_totalPages) + "页";
                                        gj.jmxc(new Runnable() {

                                            public void run() {

                                                wb2.zf(wb2_zf);
                                                spq.sx();

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

                        gj.jmxc(new Runnable() {

                            public void run() {

                                st.wb(R.id.wb1).zf("加载失败");

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

            i.runlibrary.app.v.v7lb lb = st.v7lb(R.id.v7lb1);
            spq = (i.runlibrary.app.v.v7lb$UserAdapter) lb.v7lbspq(StoreAppItemActivity.class, R.layout.store_app_item);

            final int[] kj = new int[]{R.id.tx1, R.id.wb1, R.id.wb3, -1};

            final i.runlibrary.app.v.wb wb2 = st.wb(R.id.wb2);
            gj.xc(new Thread() {

                public void run() {

                    show(1);
                    gj.zt(500);

                    boolean canshow = false;

                    String url = src_url + "/api.php";

                    String[] post = new String[]{"type=app", "code=" + search, "page=1"};

                    if (y != 0) {

                        if (y == 1) {

                            int yy = v_nowPages + 1;
                            post[2] = "page=" + sj.zh().zstring(yy);

                        } else {

                            int yy = v_nowPages - 1;
                            post[2] = "page=" + sj.zh().zstring(yy);

                        }

                    }

                    if (e1.yz()) {

                        String v = wl.hq(url, post, "utf-8", null, true, null, 20000, 20000, null);

                        if (!zf.dy(v, null)) {

                            try {

                                i.runlibrary.app.zf$json jo = (i.runlibrary.app.zf$json) zf.json(v);

                                JSONObject json = jo.json;

                                Object v_msg = jo.hq(json, "msg");

                                Object v_code = jo.hq(json, "code");

                                if (zf.dy(v_code, 200)) {

                                    Object v_totalItems = jo.hq(json, "totalItems");
                                    v_totalPages = Integer.parseInt(String.valueOf(jo.hq(json, "totalPages")));

                                    Object v_nowItems = jo.hq(json, "nowItems");
                                    v_nowPages = Integer.parseInt(String.valueOf(jo.hq(json, "nowPages")));

                                    if (zf.dy(v_totalItems, 0)) {

                                    } else {

                                        JSONArray list = jo.dxlb(json, "info");

                                        int size = jo.cd(list);

                                        while (size > 0) {

                                            size--;

                                            JSONObject dx = jo.dx(list, size);

                                            Object v_l_appname = jo.hq(dx, "appname");

                                            Object v_l_appsize = jo.hq(dx, "appsize");

                                            Object v_l_apptext = jo.hq(dx, "apptext");

                                            Object v_l_appdown = jo.hq(dx, "appdown");

                                            Object v_l_appid = jo.hq(dx, "appid");

                                            String jian = v_l_appsize + " | " + v_l_apptext;

                                            String img_url = src_url + "/app/" + v_l_appid + "/app-image/icon.png";
                                            dbe = new BitmapDrawable(lei.getResources(), com.demo.e4.getURLimage(img_url));

                                            Object[] jk = new Object[]{dbe, v_l_appname, jian, v_l_appid};
                                            spq.j(kj, jk);

                                        }

                                        final String wb2_zf = "第" + sj.zh().zstring(v_nowPages) + "/" + sj.zh().zstring(v_totalPages) + "页";
                                        gj.jmxc(new Runnable() {

                                            public void run() {

                                                wb2.zf(wb2_zf);
                                                spq.sx();

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

                        gj.jmxc(new Runnable() {

                            public void run() {

                                st.wb(R.id.wb1).zf("加载失败");

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

    private void onTx1Click(View vw) {
        but_back();

    }

    private void onTx3Click(View vw) {
        but_next();

    }

    private void onTx2Click(View vw) {
        gj.gb();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        TextView i7c3e3c6b5d = (TextView) findViewById(ay, vw, R.id.wb2);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
