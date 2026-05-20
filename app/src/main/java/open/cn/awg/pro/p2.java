/**
 ******************************************************************************
 * @file    p2.java
 * @author  Typheye
 * @brief   AWG Pro module source.
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
package open.cn.awg.pro;

import android.view.View;
import android.view.Window;

import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;

public class p2 extends iActivity {

    public final p2 lei = this, 类 = this;
    public open.cn.awg.pro.e1 e1 = new e1(_APPINFO);
    public java.lang.String uri = "";
    public java.lang.String src_url = "";
    public java.lang.String v_aurl = "";
    public java.lang.String v_img_show = "";
    public java.lang.String v_id = "";
    public java.lang.String v_title = "";
    public java.lang.String v_text = "";
    public java.lang.String s_id = "";
    public java.lang.String app_state = "";
    public long v_appcode;
    private final android.view.View.OnClickListener $_on_setOnClickListener_ee0ece10ae = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_ee0ece10ae(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_f1070075ac = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_f1070075ac(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i75b44e9f85 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i75b44e9f85(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i3ab5c5fd62 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i3ab5c5fd62(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i11aeec1890 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i11aeec1890(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i8217d42a17 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i8217d42a17(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i6f2c7751cf = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i6f2c7751cf(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_cb1d655d23 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_cb1d655d23(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i3621e3f4a1 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i3621e3f4a1(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_f73b53def9 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_f73b53def9(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_f73b53def9 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_f73b53def9(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i5a93676674 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i5a93676674(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i5a93676674 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i5a93676674(vw);
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
        setContentView(open.cn.awg.pro.R.layout.p2);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.e6.getInstance().addActivity(lei);
        uri = sj.hqtz("uri");
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

            st.xdbj(open.cn.awg.pro.R.id.p2_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.p2_v).shxtck(false);

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

    public void csh() {

        java.lang.String set67 = "/data/user/0/open.cn.awg.pro/settings/set7.inf";
        src_url = wj.dqwb(set67);

        final i.runlibrary.app.v.tx tx1 = st.tx(open.cn.awg.pro.R.id.tx1);

        final i.runlibrary.app.v.tx tx2 = st.tx(open.cn.awg.pro.R.id.tx2);

        final i.runlibrary.app.v.tx tx3 = st.tx(open.cn.awg.pro.R.id.tx3);

        final i.runlibrary.app.v.tx tx4 = st.tx(open.cn.awg.pro.R.id.tx4);

        final i.runlibrary.app.v.tx tx10 = st.tx(open.cn.awg.pro.R.id.tx10);

        final i.runlibrary.app.v.tx tx11 = st.tx(open.cn.awg.pro.R.id.tx11);

        final i.runlibrary.app.v.tx tx12 = st.tx(open.cn.awg.pro.R.id.tx12);

        final i.runlibrary.app.v.wb wb1 = st.wb(open.cn.awg.pro.R.id.wb1);

        final i.runlibrary.app.v.wb wb6 = st.wb(open.cn.awg.pro.R.id.wb6);

        final i.runlibrary.app.v.wb wb2 = st.wb(open.cn.awg.pro.R.id.wb2);

        final i.runlibrary.app.v.wb wb7 = st.wb(open.cn.awg.pro.R.id.wb7);

        final i.runlibrary.app.v.wb wb3 = st.wb(open.cn.awg.pro.R.id.wb3);

        final i.runlibrary.app.v.wb wb19 = st.wb(open.cn.awg.pro.R.id.wb19);

        final i.runlibrary.app.v.wb wb18 = st.wb(open.cn.awg.pro.R.id.wb18);

        final i.runlibrary.app.v.wb wb20 = st.wb(open.cn.awg.pro.R.id.wb20);

        final i.runlibrary.app.v.wb wb16 = st.wb(open.cn.awg.pro.R.id.wb16);

        final i.runlibrary.app.v.wb wb24 = st.wb(open.cn.awg.pro.R.id.wb24);

        final i.runlibrary.app.v.wb wb15 = st.wb(open.cn.awg.pro.R.id.wb15);

        final i.runlibrary.app.v.wb wb8 = st.wb(open.cn.awg.pro.R.id.wb8);

        final i.runlibrary.app.v.wb wb14 = st.wb(open.cn.awg.pro.R.id.wb14);

        final i.runlibrary.app.v.wb wb21 = st.wb(open.cn.awg.pro.R.id.wb21);

        final i.runlibrary.app.v.tx tx6 = st.tx(open.cn.awg.pro.R.id.tx6);
        gj.xc(new java.lang.Thread() {

            public void run() {

                show(4);
                gj.zt(500);

                boolean canshow = false;

                java.lang.String id = "";

                java.lang.String ini = "";

                if (zf.ckt(uri, "awg://uri/video")) {

                    id = zf.qc(uri, "awg://uri/video/", null);
                    s_id = id;

                    final java.lang.String img_video_show_path = "/data/user/0/open.cn.awg.pro/data/video/img/" + id + "/show.png";
                    ini = getIni("video", id);

                    if (!zf.dy(ini, null)) {

                        try {

                            java.lang.String[] inis = zf.fg(ini, "\n", true);

                            if (zf.dy(inis[0], "[CONF:V1]")) {

                                v_title = zf.qc(inis[1], "title=", null);
                                v_text = zf.th(zf.qc(inis[2], "text=", null), "<br>", "\n", true);
                                v_aurl = getFullUrl("video", id, zf.qc(inis[3], "aurl=", null));
                                v_img_show = getFullUrl("video", id, zf.qc(inis[4], "img-show=", null));

                                java.lang.String downfile = v_img_show;

                                java.lang.String downfilepath = img_video_show_path;

                                if (!wj.cz(downfilepath)) {

                                    if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                        wj.sc(downfilepath);

                                    }

                                }
                                gj.jmxc(new java.lang.Runnable() {

                                    public void run() {

                                        if (wj.cz(img_video_show_path)) {

                                            tx1.tx(img_video_show_path);

                                        } else {

                                            tx1.tx(open.cn.awg.pro.R.mipmap.b11);

                                        }
                                        wb1.zf(v_title);
                                        wb6.zf(v_text);

                                    }

                                });
                                canshow = true;

                            }

                        } catch (java.lang.Throwable e) {

                            e1.upload_error(e, "p2.csh()");

                        }

                    }

                } else if (zf.ckt(uri, "awg://uri/music")) {

                    id = zf.qc(uri, "awg://uri/music/", null);
                    ini = getIni("music", id);

                    if (!zf.dy(ini, null)) {

                        try {

                            java.lang.String[] inis = zf.fg(ini, "\n", true);

                            if (zf.dy(inis[0], "[CONF:V1]")) {

                                v_title = zf.qc(inis[1], "title=", null);
                                v_text = zf.qc(inis[2], "text=", null);
                                v_aurl = getFullUrl("music", id, zf.qc(inis[3], "aurl=", null));
                                gj.jmxc(new java.lang.Runnable() {

                                    public void run() {

                                        wb2.zf(v_title);
                                        wb7.zf(v_text);

                                    }

                                });
                                canshow = true;

                            }

                        } catch (java.lang.Throwable e) {

                            e1.upload_error(e, "p2.csh()");

                        }

                    }

                } else if (zf.ckt(uri, "awg://uri/app")) {

                    id = zf.qc(uri, "awg://uri/app/", null);
                    s_id = id;

                    final java.lang.String img_app_icon_path = "/data/user/0/open.cn.awg.pro/data/app/img/" + id + "/icon.png";

                    final java.lang.String img_app_show1_path = "/data/user/0/open.cn.awg.pro/data/app/img/" + id + "/show1.png";

                    final java.lang.String img_app_show2_path = "/data/user/0/open.cn.awg.pro/data/app/img/" + id + "/show2.png";

                    final java.lang.String img_app_show3_path = "/data/user/0/open.cn.awg.pro/data/app/img/" + id + "/show3.png";

                    final java.lang.String img_app_show4_path = "/data/user/0/open.cn.awg.pro/data/app/img/" + id + "/show4.png";

                    final java.lang.String img_app_show5_path = "/data/user/0/open.cn.awg.pro/data/app/img/" + id + "/show5.png";
                    ini = getIni("app", id);

                    if (!zf.dy(ini, null)) {

                        try {

                            java.lang.String[] inis = zf.fg(ini, "\n", true);

                            if (zf.dy(inis[0], "[CONF:V1]")) {

                                v_id = zf.qc(inis[1], "id=", null);
                                v_title = zf.qc(inis[2], "title=", null);
                                v_text = zf.th(zf.qc(inis[3], "text=", null), "<br>", "\n", true);
                                v_aurl = getFullUrl("app", id, zf.qc(inis[4], "aurl=", null));

                                final java.lang.String v_app_info_level = zf.qc(inis[5], "app-info-level=", null);

                                final java.lang.String v_app_info_language = zf.qc(inis[6], "app-info-language=", null);

                                final java.lang.String v_app_info_abi = zf.qc(inis[7], "app-info-abi=", null);

                                final java.lang.String v_app_info_size = zf.qc(inis[8], "app-info-size=", null);

                                final java.lang.String v_app_info_author = zf.qc(inis[9], "app-info-author=", null);

                                final java.lang.String v_app_latest_ver = zf.qc(inis[10], "app-latest-ver=", null);

                                final java.lang.String v_app_latest_code = zf.qc(inis[11], "app-latest-code=", null);

                                final java.lang.String v_app_latest_date = zf.qc(inis[12], "app-latest-date=", null);

                                final java.lang.String v_app_latest_update = zf.th(zf.qc(inis[13], "app-latest-update=", null), "<br>", "\n", true);

                                final java.lang.String v_app_more_limit = zf.qc(inis[14], "app-more-limit=", null);

                                final java.lang.String v_app_comment_show = zf.th(zf.qc(inis[15], "app-comment-show=", null), "<br>", "\n", true);

                                final java.lang.String xbtx = "应用最新版本为" + v_app_latest_ver + "(" + v_app_latest_code + "),于" + v_app_latest_date + "发布, 更新内容如下:\n" + v_app_latest_update;

                                final java.lang.String gdxx = "应用包名: " + v_id + "\n应用分级: " + v_app_more_limit;

                                java.lang.String downfile = getAppIconUrl(id);

                                java.lang.String downfilepath = img_app_icon_path;

                                if (!wj.cz(downfilepath)) {

                                    if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                        wj.sc(downfilepath);

                                    }

                                }
                                downfile = getAppShowUrl(id, "1");
                                downfilepath = img_app_show1_path;

                                if (!wj.cz(downfilepath)) {

                                    if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                        wj.sc(downfilepath);

                                    }

                                }
                                downfile = getAppShowUrl(id, "2");
                                downfilepath = img_app_show2_path;

                                if (!wj.cz(downfilepath)) {

                                    if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                        wj.sc(downfilepath);

                                    }

                                }
                                downfile = getAppShowUrl(id, "3");
                                downfilepath = img_app_show3_path;

                                if (!wj.cz(downfilepath)) {

                                    if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                        wj.sc(downfilepath);

                                    }

                                }
                                downfile = getAppShowUrl(id, "4");
                                downfilepath = img_app_show4_path;

                                if (!wj.cz(downfilepath)) {

                                    if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                        wj.sc(downfilepath);

                                    }

                                }
                                downfile = getAppShowUrl(id, "5");
                                downfilepath = img_app_show5_path;

                                if (!wj.cz(downfilepath)) {

                                    if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                        wj.sc(downfilepath);

                                    }

                                }

                                final boolean vv_yysfaz = com.kingqi.zwcj.应用工具.应用是否安装(lei, v_id);

                                final long vv_yybbh = com.kingqi.zwcj.应用工具.获取应用版本号(lei, v_id);
                                v_appcode = sj.zh().zlong(v_app_latest_code, vv_yybbh);
                                gj.jmxc(new java.lang.Runnable() {

                                    public void run() {

                                        if (vv_yysfaz) {

                                            if (v_appcode > vv_yybbh) {

                                                tx6.tx(open.cn.awg.pro.R.mipmap.a62);
                                                app_state = "update";

                                            } else {

                                                tx6.tx(open.cn.awg.pro.R.mipmap.a61);
                                                app_state = "open";

                                            }

                                        } else {

                                            tx6.tx(open.cn.awg.pro.R.mipmap.a58);
                                            app_state = "download";

                                        }

                                        if (wj.cz(img_app_icon_path)) {

                                            tx2.tx(img_app_icon_path);

                                        } else {

                                            tx2.tx(open.cn.awg.pro.R.mipmap.appicon);

                                        }

                                        if (wj.cz(img_app_show1_path)) {

                                            tx3.tx(img_app_show1_path);

                                        } else {

                                            tx3.tx(open.cn.awg.pro.R.mipmap.b11);

                                        }

                                        if (wj.cz(img_app_show2_path)) {

                                            tx4.tx(img_app_show2_path);

                                        } else {

                                            tx4.tx(open.cn.awg.pro.R.mipmap.b11);

                                        }

                                        if (wj.cz(img_app_show3_path)) {

                                            tx10.tx(img_app_show3_path);

                                        } else {

                                            tx10.tx(open.cn.awg.pro.R.mipmap.b11);

                                        }

                                        if (wj.cz(img_app_show4_path)) {

                                            tx11.tx(img_app_show4_path);

                                        } else {

                                            tx11.tx(open.cn.awg.pro.R.mipmap.b11);

                                        }

                                        if (wj.cz(img_app_show5_path)) {

                                            tx12.tx(img_app_show5_path);

                                        } else {

                                            tx12.tx(open.cn.awg.pro.R.mipmap.b11);

                                        }
                                        wb3.zf(v_title);
                                        wb19.zf(v_app_info_size);
                                        wb18.zf(v_app_info_language);
                                        wb20.zf(v_app_info_abi);
                                        wb16.zf(v_app_info_size);
                                        wb24.zf(v_app_info_author);
                                        wb15.zf(xbtx);
                                        wb8.zf(v_text);
                                        wb14.zf(gdxx);
                                        wb21.zf(v_app_comment_show);

                                    }

                                });
                                canshow = true;

                            }

                        } catch (java.lang.Throwable e) {

                            e1.upload_error(e, "p2.csh()");

                        }

                    }

                }

                if (canshow) {

                    if (zf.ckt(uri, "awg://uri/video")) {

                        show(0);

                    } else if (zf.ckt(uri, "awg://uri/music")) {

                        show(1);

                    } else if (zf.ckt(uri, "awg://uri/app")) {

                        show(2);

                    }

                } else {

                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            st.wb(open.cn.awg.pro.R.id.wb30).zf("加载失败");

                        }

                    });

                }

            }

        });

    }

    public void exitdel() {

        java.lang.String id = s_id;

        final java.lang.String img_app_icon_path = "/data/user/0/open.cn.awg.pro/data/app/img/" + id + "/icon.png";

        final java.lang.String img_app_show1_path = "/data/user/0/open.cn.awg.pro/data/app/img/" + id + "/show1.png";

        final java.lang.String img_app_show2_path = "/data/user/0/open.cn.awg.pro/data/app/img/" + id + "/show2.png";

        final java.lang.String img_app_show3_path = "/data/user/0/open.cn.awg.pro/data/app/img/" + id + "/show3.png";

        final java.lang.String img_app_show4_path = "/data/user/0/open.cn.awg.pro/data/app/img/" + id + "/show4.png";

        final java.lang.String img_app_show5_path = "/data/user/0/open.cn.awg.pro/data/app/img/" + id + "/show5.png";

        final java.lang.String img_video_show_path = "/data/user/0/open.cn.awg.pro/data/video/img/" + id + "/show.png";
        wj.sc(img_app_icon_path);
        wj.sc(img_app_show1_path);
        wj.sc(img_app_show2_path);
        wj.sc(img_app_show3_path);
        wj.sc(img_app_show4_path);
        wj.sc(img_app_show5_path);
        wj.sc(img_video_show_path);

    }

    public java.lang.String getIni(java.lang.Object type, java.lang.Object id) {

        if (e1.yz()) {

            java.lang.String url = src_url + "/" + type + "/" + id + "/config.ini";
            return wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

        } else {

            return "";

        }

    }

    public java.lang.String getFullUrl(java.lang.Object type, java.lang.Object id, java.lang.Object file) {

        if (zf.ckt(file, "./")) {

            return src_url + "/" + type + "/" + id + "/" + zf.qc(file, "./", null);

        } else {

            return sj.zh().zstring(file);

        }

    }

    public java.lang.String getAppIconUrl(java.lang.Object id) {
        return src_url + "/app/" + id + "/app-image/icon.png";

    }

    public java.lang.String getAppShowUrl(java.lang.Object id, java.lang.Object i) {
        return src_url + "/app/" + id + "/app-image/show" + i + ".png";

    }

    public int homeshow_filedown(java.lang.Object url, java.lang.Object post, java.lang.Object cookie, java.lang.Object path) {

        java.lang.Object a = url;

        java.lang.Object b = path;

        java.lang.Object c = post;

        java.lang.Object d = cookie;

        boolean e = true;

        java.lang.String f = "User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept-language=zh-CN";
        return wl.xz(a, b, true, c, "utf-8", d, e, f);

    }

    public void show(int i_) {

        final int i = i_;
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                i.runlibrary.app.v.wb tab = st.wb(open.cn.awg.pro.R.id.Tab);

                i.runlibrary.app.v.xxbj x1 = st.xxbj(open.cn.awg.pro.R.id.xxbj2);

                i.runlibrary.app.v.xxbj x2 = st.xxbj(open.cn.awg.pro.R.id.xxbj4);

                i.runlibrary.app.v.xxbj x3 = st.xxbj(open.cn.awg.pro.R.id.xxbj5);

                i.runlibrary.app.v.xxbj x4 = st.xxbj(open.cn.awg.pro.R.id.xxbj6);

                if (i == 0) {

                    tab.zf("< 视频详情");
                    x1.kjd(0);
                    x2.kjd(8);
                    x3.kjd(8);
                    x4.kjd(8);

                } else if (i == 1) {

                    tab.zf("< 歌曲详情");
                    x1.kjd(8);
                    x2.kjd(0);
                    x3.kjd(8);
                    x4.kjd(8);

                } else if (i == 2) {

                    tab.zf("< 应用详情");
                    x1.kjd(8);
                    x2.kjd(8);
                    x3.kjd(0);
                    x4.kjd(8);

                } else if (i == 4) {

                    if (zf.ckt(uri, "awg://uri/video")) {

                        tab.zf("< 视频详情");

                    } else if (zf.ckt(uri, "awg://uri/music")) {

                        tab.zf("< 歌曲详情");

                    } else if (zf.ckt(uri, "awg://uri/app")) {

                        tab.zf("< 应用详情");

                    }
                    x1.kjd(8);
                    x2.kjd(8);
                    x3.kjd(8);
                    x4.kjd(0);

                }

            }

        });

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
        open.cn.awg.pro.e6.getInstance().removeActivity(lei);
        exitdel();

    }

    private void $_onClick_d26d5d7080(android.view.View vw) {
        gj.gb();

    }

    private void $_onClick_i34dc7bf20a(android.view.View vw) {

        final java.lang.String path = "/data/user/0/open.cn.awg.pro/data/video/img/" + s_id + "/show.png";

        final java.lang.String[] name = new java.lang.String[]{"url"};

        final java.lang.String[] value = new java.lang.String[]{path};
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                gj.tz(d3.class, name, value);

            }

        });

    }

    private void $_onClick_i5a93676674(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                java.lang.String[] name = new java.lang.String[]{"url", "title"};

                java.lang.String[] value = new java.lang.String[]{v_aurl, v_title};
                gj.tz(g2.class, name, value);

            }

        });

    }

    private boolean $_onLongClick_i5a93676674(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb4).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb5).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_f73b53def9(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                java.lang.String[] name = new java.lang.String[]{"url", "title"};

                java.lang.String[] value = new java.lang.String[]{v_aurl, v_title + "-" + v_text};
                gj.tz(g2.class, name, value);

            }

        });

    }

    private boolean $_onLongClick_f73b53def9(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb9).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb10).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i3621e3f4a1(android.view.View vw) {
        gj.gb();

    }

    private void $_onClick_cb1d655d23(android.view.View vw) {

        java.lang.String surl = "https://www.typheye.cn/share/app/?uri=" + s_id;
        e1.tsk_QR("应用分享", surl + "\n\n扫描二维码快速分享", surl);

    }

    private void $_onClick_i6f2c7751cf(android.view.View vw) {

        if (zf.dy(app_state, "open")) {

            if (zf.dy(v_id, "open.cn.awg.pro")) {

                e1.tsk("提示", "已经在应用内了");

            } else {

                com.kingqi.zwcj.应用工具.启动应用(lei, v_id);

            }

        } else {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    java.lang.String[] iyu = new java.lang.String[]{"url", "type", "appid"};

                    java.lang.String[] val = new java.lang.String[]{v_aurl, "app", v_id};
                    gj.tz(l2.class, iyu, val);

                }

            });

        }

    }

    private void $_onClick_i8217d42a17(android.view.View vw) {

        final java.lang.String path = "/data/user/0/open.cn.awg.pro/data/app/img/" + s_id + "/show1.png";

        final java.lang.String[] name = new java.lang.String[]{"url"};

        final java.lang.String[] value = new java.lang.String[]{path};
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                gj.tz(d3.class, name, value);

            }

        });

    }

    private void $_onClick_i11aeec1890(android.view.View vw) {

        final java.lang.String path = "/data/user/0/open.cn.awg.pro/data/app/img/" + s_id + "/show2.png";

        final java.lang.String[] name = new java.lang.String[]{"url"};

        final java.lang.String[] value = new java.lang.String[]{path};
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                gj.tz(d3.class, name, value);

            }

        });

    }

    private void $_onClick_i3ab5c5fd62(android.view.View vw) {

        final java.lang.String path = "/data/user/0/open.cn.awg.pro/data/app/img/" + s_id + "/show3.png";

        final java.lang.String[] name = new java.lang.String[]{"url"};

        final java.lang.String[] value = new java.lang.String[]{path};
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                gj.tz(d3.class, name, value);

            }

        });

    }

    private void $_onClick_i75b44e9f85(android.view.View vw) {

        final java.lang.String path = "/data/user/0/open.cn.awg.pro/data/app/img/" + s_id + "/show4.png";

        final java.lang.String[] name = new java.lang.String[]{"url"};

        final java.lang.String[] value = new java.lang.String[]{path};
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                gj.tz(d3.class, name, value);

            }

        });

    }

    private void $_onClick_f1070075ac(android.view.View vw) {

        final java.lang.String path = "/data/user/0/open.cn.awg.pro/data/app/img/" + s_id + "/show5.png";

        final java.lang.String[] name = new java.lang.String[]{"url"};

        final java.lang.String[] value = new java.lang.String[]{path};
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                gj.tz(d3.class, name, value);

            }

        });

    }

    private void $_onClick_ee0ece10ae(android.view.View vw) {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                java.lang.String[] name = new java.lang.String[]{"url", "title"};

                java.lang.String[] value = new java.lang.String[]{v_aurl, v_title + "-" + v_text};
                gj.tz(g2.class, name, value);

            }

        });

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
        d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        android.widget.RelativeLayout i5a93676674 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj9);
        i5a93676674.setOnClickListener($_on_setOnClickListener_i5a93676674);
        i5a93676674.setOnLongClickListener($_on_setOnLongClickListener_i5a93676674);

        android.widget.ImageView i4a4248a0f2 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx5);

        android.widget.RelativeLayout f73b53def9 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj12);
        f73b53def9.setOnClickListener($_on_setOnClickListener_f73b53def9);
        f73b53def9.setOnLongClickListener($_on_setOnLongClickListener_f73b53def9);

        android.widget.ImageView i3621e3f4a1 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx7);
        i3621e3f4a1.setOnClickListener($_on_setOnClickListener_i3621e3f4a1);

        android.widget.ImageView cb1d655d23 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx8);
        cb1d655d23.setOnClickListener($_on_setOnClickListener_cb1d655d23);

        android.widget.ImageView i6f2c7751cf = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx6);
        i6f2c7751cf.setOnClickListener($_on_setOnClickListener_i6f2c7751cf);

        android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);
        i8217d42a17.setOnClickListener($_on_setOnClickListener_i8217d42a17);

        android.widget.ImageView i11aeec1890 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx4);
        i11aeec1890.setOnClickListener($_on_setOnClickListener_i11aeec1890);

        android.widget.ImageView i3ab5c5fd62 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx10);
        i3ab5c5fd62.setOnClickListener($_on_setOnClickListener_i3ab5c5fd62);

        android.widget.ImageView i75b44e9f85 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx11);
        i75b44e9f85.setOnClickListener($_on_setOnClickListener_i75b44e9f85);

        android.widget.ImageView f1070075ac = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx12);
        f1070075ac.setOnClickListener($_on_setOnClickListener_f1070075ac);

        android.widget.ImageView ee0ece10ae = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx9);
        ee0ece10ae.setOnClickListener($_on_setOnClickListener_ee0ece10ae);

        __layoutIsLoaded(ay, vw);
    }
}
