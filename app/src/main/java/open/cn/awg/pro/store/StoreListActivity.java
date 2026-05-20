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

import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.Window;

import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.ui.list.StoreAppItemActivity;
import open.cn.awg.pro.ui.list.StoreMusicItemActivity;
import open.cn.awg.pro.ui.list.StoreVideoItemActivity;


public class StoreListActivity extends iActivity {

    public final StoreListActivity lei = this, 类 = this;
    public java.lang.String um = "";
    public java.io.File f;
    public java.lang.String ll = "";
    public java.lang.String type = "";
    public java.lang.String search = "";
    public open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public i.runlibrary.app.v.v7lb$UserAdapter spq = null;
    public java.lang.String src_url = "";
    public int v_totalPages = 0;
    public int v_nowPages = 0;
    public android.graphics.drawable.Drawable dbe;
    public boolean state_fun_app = false;
    public boolean state_fun_video = false;
    public boolean state_fun_music = false;
    private final android.view.View.OnClickListener $_on_setOnClickListener_i4d951cdb0e = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i4d951cdb0e(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i8217d42a17 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i8217d42a17(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d26d5d7080(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.p3);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.app.AwgProApplication.getInstance().addActivity(lei);
        type = sj.hqtz("type");
        search = sj.hqtz("search");
        csh();

    }

    public void __layoutIsLoaded(android.app.Activity ay, android.view.View vw) {

        if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f8/set4.inf"), "true")) {

        }

        i.runlibrary.app.xt$pm pm = xt.pm();

        if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4s.inf"), "false")) {

            if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4.inf"), "true")) {

                getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));

        if (zf.dy(a, "4")) {

            st.xdbj(open.cn.awg.pro.R.id.p3_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.p3_v).shxtck(false);

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

        if (zf.dy(a, "1")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "2")) {

            java.lang.String a001 = "/data/user/0/open.cn.awg.pro/settings/dpi.inf";

            if (wj.cz(a001) || zf.dy(wj.dqwb(a001), "true")) {

                i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
                wtab.nbj(0, "3dp", 0, "3dp");
                wtab.dqfs("center");

            } else {

                i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
                wtab.nbj(0, "9dp", 0, "9dp");
                wtab.dqfs("center");

            }
            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0, 0, 0, 0);

        } else if (zf.dy(a, "3")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "4")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0, 0, 0, 0);

        }

    }

    public void show(int i) {

        final i.runlibrary.app.v.wb tab = st.wb(open.cn.awg.pro.R.id.Tab);

        final i.runlibrary.app.v.xxbj x2 = st.xxbj(open.cn.awg.pro.R.id.xxbj6);

        final i.runlibrary.app.v.xxbj x3 = st.xxbj(open.cn.awg.pro.R.id.xxbj4);

        if (i == 1) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    x2.kjd(0);
                    x3.kjd(8);

                }

            });

        } else if (i == 2) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    x2.kjd(8);
                    x3.kjd(0);

                }

            });

        }
        gj.jmxc(new java.lang.Runnable() {

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

        java.lang.String set67 = "/data/user/0/open.cn.awg.pro/settings/set7.inf";
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

            i.runlibrary.app.v.v7lb lb = st.v7lb(open.cn.awg.pro.R.id.v7lb1);
            spq = lb.v7lbspq(StoreMusicItemActivity.class, open.cn.awg.pro.R.layout.c25);

            final int[] kj = new int[]{open.cn.awg.pro.R.id.wb1, open.cn.awg.pro.R.id.wb3, -1};

            final i.runlibrary.app.v.wb wb2 = st.wb(open.cn.awg.pro.R.id.wb2);
            gj.xc(new java.lang.Thread() {

                public void run() {

                    show(1);
                    gj.zt(500);

                    boolean canshow = false;

                    java.lang.String url = src_url + "/api.php";

                    java.lang.String[] post = new java.lang.String[]{"type=music", "code=" + search, "page=1"};

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

                        java.lang.String v = wl.hq(url, post, "utf-8", null, true, null, 20000, 20000, null);

                        if (!zf.dy(v, null)) {

                            try {

                                i.runlibrary.app.zf$json jo = zf.json(v);

                                org.json.JSONObject json = jo.json;

                                java.lang.Object v_msg = jo.hq(json, "msg");

                                java.lang.Object v_code = jo.hq(json, "code");

                                if (zf.dy(v_code, 200)) {

                                    java.lang.Object v_totalItems = jo.hq(json, "totalItems");
                                    v_totalPages = Integer.parseInt(String.valueOf(jo.hq(json, "totalPages")));

                                    java.lang.Object v_nowItems = jo.hq(json, "nowItems");
                                    v_nowPages = Integer.parseInt(String.valueOf(jo.hq(json, "nowPages")));

                                    if (zf.dy(v_totalItems, 0)) {

                                    } else {

                                        org.json.JSONArray list = jo.dxlb(json, "info");

                                        int size = jo.cd(list);

                                        while (size > 0) {

                                            size--;

                                            org.json.JSONObject dx = jo.dx(list, size);

                                            java.lang.Object v_l_title = jo.hq(dx, "title");

                                            java.lang.Object v_l_text = jo.hq(dx, "text");

                                            java.lang.Object v_l_aurl = jo.hq(dx, "aurl");

                                            java.lang.Object v_l_id = jo.hq(dx, "id");

                                            java.lang.Object[] jk = new java.lang.Object[]{v_l_title, v_l_text, v_l_id};
                                            spq.j(kj, jk);

                                        }

                                        final java.lang.String wb2_zf = "第" + sj.zh().zstring(v_nowPages) + "/" + sj.zh().zstring(v_totalPages) + "页";
                                        gj.jmxc(new java.lang.Runnable() {

                                            public void run() {

                                                wb2.zf(wb2_zf);
                                                spq.sx();

                                            }

                                        });
                                        canshow = true;

                                    }

                                }

                            } catch (java.lang.Throwable e) {

                                e1.upload_error(e, "p3.fun_music(int)");

                            }

                        }

                    }

                    if (canshow) {

                        show(2);

                    } else {

                        gj.jmxc(new java.lang.Runnable() {

                            public void run() {

                                st.wb(open.cn.awg.pro.R.id.wb1).zf("加载失败");

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

            i.runlibrary.app.v.v7lb lb = st.v7lb(open.cn.awg.pro.R.id.v7lb1);
            spq = lb.v7lbspq(StoreVideoItemActivity.class, open.cn.awg.pro.R.layout.c24);

            final int[] kj = new int[]{open.cn.awg.pro.R.id.wb1, open.cn.awg.pro.R.id.wb3, -1};

            final i.runlibrary.app.v.wb wb2 = st.wb(open.cn.awg.pro.R.id.wb2);
            gj.xc(new java.lang.Thread() {

                public void run() {

                    show(1);
                    gj.zt(500);

                    boolean canshow = false;

                    java.lang.String url = src_url + "/api.php";

                    java.lang.String[] post = new java.lang.String[]{"type=video", "code=" + search, "page=1"};

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

                        java.lang.String v = wl.hq(url, post, "utf-8", null, true, null, 20000, 20000, null);

                        if (!zf.dy(v, null)) {

                            try {

                                i.runlibrary.app.zf$json jo = zf.json(v);

                                org.json.JSONObject json = jo.json;

                                java.lang.Object v_msg = jo.hq(json, "msg");

                                java.lang.Object v_code = jo.hq(json, "code");

                                if (zf.dy(v_code, 200)) {

                                    java.lang.Object v_totalItems = jo.hq(json, "totalItems");
                                    v_totalPages = Integer.parseInt(String.valueOf(jo.hq(json, "totalPages")));

                                    java.lang.Object v_nowItems = jo.hq(json, "nowItems");
                                    v_nowPages = Integer.parseInt(String.valueOf(jo.hq(json, "nowPages")));

                                    if (zf.dy(v_totalItems, 0)) {

                                    } else {

                                        org.json.JSONArray list = jo.dxlb(json, "info");

                                        int size = jo.cd(list);

                                        while (size > 0) {

                                            size--;

                                            org.json.JSONObject dx = jo.dx(list, size);

                                            java.lang.Object v_l_title = jo.hq(dx, "title");

                                            java.lang.Object v_l_text = jo.hq(dx, "text");
                                            v_l_text = zf.th(v_l_text, "<br>", " ");

                                            java.lang.Object v_l_aurl = jo.hq(dx, "aurl");

                                            java.lang.Object v_l_id = jo.hq(dx, "id");

                                            java.lang.Object[] jk = new java.lang.Object[]{v_l_title, v_l_text, v_l_id};
                                            spq.j(kj, jk);

                                        }

                                        final java.lang.String wb2_zf = "第" + sj.zh().zstring(v_nowPages) + "/" + sj.zh().zstring(v_totalPages) + "页";
                                        gj.jmxc(new java.lang.Runnable() {

                                            public void run() {

                                                wb2.zf(wb2_zf);
                                                spq.sx();

                                            }

                                        });
                                        canshow = true;

                                    }

                                }

                            } catch (java.lang.Throwable e) {

                                e1.upload_error(e, "p3.fun_video(int)");

                            }

                        }

                    }

                    if (canshow) {

                        show(2);

                    } else {

                        gj.jmxc(new java.lang.Runnable() {

                            public void run() {

                                st.wb(open.cn.awg.pro.R.id.wb1).zf("加载失败");

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

            i.runlibrary.app.v.v7lb lb = st.v7lb(open.cn.awg.pro.R.id.v7lb1);
            spq = lb.v7lbspq(StoreAppItemActivity.class, open.cn.awg.pro.R.layout.c23);

            final int[] kj = new int[]{open.cn.awg.pro.R.id.tx1, open.cn.awg.pro.R.id.wb1, open.cn.awg.pro.R.id.wb3, -1};

            final i.runlibrary.app.v.wb wb2 = st.wb(open.cn.awg.pro.R.id.wb2);
            gj.xc(new java.lang.Thread() {

                public void run() {

                    show(1);
                    gj.zt(500);

                    boolean canshow = false;

                    java.lang.String url = src_url + "/api.php";

                    java.lang.String[] post = new java.lang.String[]{"type=app", "code=" + search, "page=1"};

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

                        java.lang.String v = wl.hq(url, post, "utf-8", null, true, null, 20000, 20000, null);

                        if (!zf.dy(v, null)) {

                            try {

                                i.runlibrary.app.zf$json jo = zf.json(v);

                                org.json.JSONObject json = jo.json;

                                java.lang.Object v_msg = jo.hq(json, "msg");

                                java.lang.Object v_code = jo.hq(json, "code");

                                if (zf.dy(v_code, 200)) {

                                    java.lang.Object v_totalItems = jo.hq(json, "totalItems");
                                    v_totalPages = Integer.parseInt(String.valueOf(jo.hq(json, "totalPages")));

                                    java.lang.Object v_nowItems = jo.hq(json, "nowItems");
                                    v_nowPages = Integer.parseInt(String.valueOf(jo.hq(json, "nowPages")));

                                    if (zf.dy(v_totalItems, 0)) {

                                    } else {

                                        org.json.JSONArray list = jo.dxlb(json, "info");

                                        int size = jo.cd(list);

                                        while (size > 0) {

                                            size--;

                                            org.json.JSONObject dx = jo.dx(list, size);

                                            java.lang.Object v_l_appname = jo.hq(dx, "appname");

                                            java.lang.Object v_l_appsize = jo.hq(dx, "appsize");

                                            java.lang.Object v_l_apptext = jo.hq(dx, "apptext");

                                            java.lang.Object v_l_appdown = jo.hq(dx, "appdown");

                                            java.lang.Object v_l_appid = jo.hq(dx, "appid");

                                            java.lang.String jian = v_l_appsize + " | " + v_l_apptext;

                                            java.lang.String img_url = src_url + "/app/" + v_l_appid + "/app-image/icon.png";
                                            dbe = new BitmapDrawable(lei.getResources(), com.demo.e4.getURLimage(img_url));

                                            java.lang.Object[] jk = new java.lang.Object[]{dbe, v_l_appname, jian, v_l_appid};
                                            spq.j(kj, jk);

                                        }

                                        final java.lang.String wb2_zf = "第" + sj.zh().zstring(v_nowPages) + "/" + sj.zh().zstring(v_totalPages) + "页";
                                        gj.jmxc(new java.lang.Runnable() {

                                            public void run() {

                                                wb2.zf(wb2_zf);
                                                spq.sx();

                                            }

                                        });
                                        canshow = true;

                                    }

                                }

                            } catch (java.lang.Throwable e) {

                                e1.upload_error(e, "p3.fun_app(int)");

                            }

                        }

                    }

                    if (canshow) {

                        show(2);

                    } else {

                        gj.jmxc(new java.lang.Runnable() {

                            public void run() {

                                st.wb(open.cn.awg.pro.R.id.wb1).zf("加载失败");

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

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

    }

    public void onRestart() {
        super.onRestart();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

    }

    public void onStart() {
        super.onStart();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

        i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(open.cn.awg.pro.R.id.qtgd1);

        qtgd1.v.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View vw, android.view.MotionEvent me) {
                if (me.getAction() == android.view.MotionEvent.ACTION_SCROLL && me.isFromSource(androidx.core.view.InputDeviceCompat.SOURCE_ROTARY_ENCODER)) {

                    float delta = -me.getAxisValue(androidx.core.view.MotionEventCompat.AXIS_SCROLL) *
                            androidx.core.view.ViewConfigurationCompat.getScaledVerticalScrollFactor(
                                    android.view.ViewConfiguration.get(lei), lei
                            );

                    vw.scrollBy(0, java.lang.Math.round(delta));
                    return true;
                }
                return false;
            }
        });

        qtgd1.v.requestFocus();

    }

    public void onResume() {
        super.onResume();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

    }

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.app.AwgProApplication.getInstance().removeActivity(lei);

    }

    private void $_onClick_d26d5d7080(android.view.View vw) {
        gj.gb();

    }

    private void $_onClick_i34dc7bf20a(android.view.View vw) {
        but_back();

    }

    private void $_onClick_i8217d42a17(android.view.View vw) {
        but_next();

    }

    private void $_onClick_i4d951cdb0e(android.view.View vw) {
        gj.gb();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
        d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        android.widget.TextView i7c3e3c6b5d = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb2);

        android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);
        i8217d42a17.setOnClickListener($_on_setOnClickListener_i8217d42a17);

        android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
        i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

        __layoutIsLoaded(ay, vw);
    }
}
