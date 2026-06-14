/**
 ******************************************************************************
 * @file    StoreDetailActivity.java
 * @author  Typheye
 * @brief   Store detail screen.
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


import open.cn.awg.pro.core.IappCompat;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import com.kingqi.zwcj.应用工具;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.download.AppDownloadActivity;
import open.cn.awg.pro.media.VideoPlayerActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.viewer.ImageViewerActivity;

public class StoreDetailActivity extends BaseAwgActivity {

    public final StoreDetailActivity lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public String uri = "";
    public String src_url = "";
    public String v_aurl = "";
    public String v_img_show = "";
    public String v_id = "";
    public String v_title = "";
    public String v_text = "";
    public String s_id = "";
    public String app_state = "";
    public long v_appcode;
    private final View.OnClickListener tx9ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx9Click(vw);
        }

    };
    private final View.OnClickListener tx12ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx12Click(vw);
        }

    };
    private final View.OnClickListener tx11ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx11Click(vw);
        }

    };
    private final View.OnClickListener tx10ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx10Click(vw);
        }

    };
    private final View.OnClickListener tx4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx4Click(vw);
        }

    };
    private final View.OnClickListener tx3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx3Click(vw);
        }

    };
    private final View.OnClickListener tx6ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx6Click(vw);
        }

    };
    private final View.OnClickListener tx8ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx8Click(vw);
        }

    };
    private final View.OnClickListener tx7ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx7Click(vw);
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
        setContentView(R.layout.store_detail);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        uri = sj.hqtz("uri");
        csh();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.store_detail_root, R.id.xdbj1, R.id.title_bar);

    }

    public void csh() {

        String set67 = AppPaths.appPath("settings/set7.inf");
        src_url = wj.dqwb(set67);

        final i.runlibrary.app.v.tx tx1 = st.tx(R.id.tx1);

        final i.runlibrary.app.v.tx tx2 = st.tx(R.id.tx2);

        final i.runlibrary.app.v.tx tx3 = st.tx(R.id.tx3);

        final i.runlibrary.app.v.tx tx4 = st.tx(R.id.tx4);

        final i.runlibrary.app.v.tx tx10 = st.tx(R.id.tx10);

        final i.runlibrary.app.v.tx tx11 = st.tx(R.id.tx11);

        final i.runlibrary.app.v.tx tx12 = st.tx(R.id.tx12);

        final i.runlibrary.app.v.wb wb1 = st.wb(R.id.wb1);

        final i.runlibrary.app.v.wb wb6 = st.wb(R.id.wb6);

        final i.runlibrary.app.v.wb wb2 = st.wb(R.id.wb2);

        final i.runlibrary.app.v.wb wb7 = st.wb(R.id.wb7);

        final i.runlibrary.app.v.wb wb3 = st.wb(R.id.wb3);

        final i.runlibrary.app.v.wb wb19 = st.wb(R.id.wb19);

        final i.runlibrary.app.v.wb wb18 = st.wb(R.id.wb18);

        final i.runlibrary.app.v.wb wb20 = st.wb(R.id.wb20);

        final i.runlibrary.app.v.wb wb16 = st.wb(R.id.wb16);

        final i.runlibrary.app.v.wb wb24 = st.wb(R.id.wb24);

        final i.runlibrary.app.v.wb wb15 = st.wb(R.id.wb15);

        final i.runlibrary.app.v.wb wb8 = st.wb(R.id.wb8);

        final i.runlibrary.app.v.wb wb14 = st.wb(R.id.wb14);

        final i.runlibrary.app.v.wb wb21 = st.wb(R.id.wb21);

        final i.runlibrary.app.v.tx tx6 = st.tx(R.id.tx6);
        gj.xc(new Thread() {

            public void run() {

                show(4);
                gj.zt(500);

                boolean canshow = false;

                String id = "";

                String ini = "";

                if (zf.ckt(uri, "awg://uri/video")) {

                    id = zf.qc(uri, "awg://uri/video/", null);
                    s_id = id;

                    final String img_video_show_path = AppPaths.appPath("data/video/img/") + id + "/show.png";
                    ini = getIni("video", id);

                    if (!zf.dy(ini, null)) {

                        try {

                            String[] inis = zf.fg(ini, "\n", true);

                            if (zf.dy(inis[0], "[CONF:V1]")) {

                                v_title = zf.qc(inis[1], "title=", null);
                                v_text = zf.th(zf.qc(inis[2], "text=", null), "<br>", "\n", true);
                                v_aurl = getFullUrl("video", id, zf.qc(inis[3], "aurl=", null));
                                v_img_show = getFullUrl("video", id, zf.qc(inis[4], "img-show=", null));

                                String downfile = v_img_show;

                                String downfilepath = img_video_show_path;

                                if (!wj.cz(downfilepath)) {

                                    if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                        wj.sc(downfilepath);

                                    }

                                }
                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        if (wj.cz(img_video_show_path)) {

                                            tx1.tx(img_video_show_path);

                                        } else {

                                            tx1.tx(R.mipmap.b11);

                                        }
                                        wb1.zf(v_title);
                                        wb6.zf(v_text);

                                    }

                                });
                                canshow = true;

                            }

                        } catch (Throwable e) {

                            e1.upload_error(e, "p2.csh()");

                        }

                    }

                } else if (zf.ckt(uri, "awg://uri/music")) {

                    id = zf.qc(uri, "awg://uri/music/", null);
                    ini = getIni("music", id);

                    if (!zf.dy(ini, null)) {

                        try {

                            String[] inis = zf.fg(ini, "\n", true);

                            if (zf.dy(inis[0], "[CONF:V1]")) {

                                v_title = zf.qc(inis[1], "title=", null);
                                v_text = zf.qc(inis[2], "text=", null);
                                v_aurl = getFullUrl("music", id, zf.qc(inis[3], "aurl=", null));
                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        wb2.zf(v_title);
                                        wb7.zf(v_text);

                                    }

                                });
                                canshow = true;

                            }

                        } catch (Throwable e) {

                            e1.upload_error(e, "p2.csh()");

                        }

                    }

                } else if (zf.ckt(uri, "awg://uri/app")) {

                    id = zf.qc(uri, "awg://uri/app/", null);
                    s_id = id;

                    final String img_app_icon_path = AppPaths.appPath("data/app/img/") + id + "/icon.png";

                    final String img_app_show1_path = AppPaths.appPath("data/app/img/") + id + "/show1.png";

                    final String img_app_show2_path = AppPaths.appPath("data/app/img/") + id + "/show2.png";

                    final String img_app_show3_path = AppPaths.appPath("data/app/img/") + id + "/show3.png";

                    final String img_app_show4_path = AppPaths.appPath("data/app/img/") + id + "/show4.png";

                    final String img_app_show5_path = AppPaths.appPath("data/app/img/") + id + "/show5.png";
                    ini = getIni("app", id);

                    if (!zf.dy(ini, null)) {

                        try {

                            String[] inis = zf.fg(ini, "\n", true);

                            if (zf.dy(inis[0], "[CONF:V1]")) {

                                v_id = zf.qc(inis[1], "id=", null);
                                v_title = zf.qc(inis[2], "title=", null);
                                v_text = zf.th(zf.qc(inis[3], "text=", null), "<br>", "\n", true);
                                v_aurl = getFullUrl("app", id, zf.qc(inis[4], "aurl=", null));

                                final String v_app_info_level = zf.qc(inis[5], "app-info-level=", null);

                                final String v_app_info_language = zf.qc(inis[6], "app-info-language=", null);

                                final String v_app_info_abi = zf.qc(inis[7], "app-info-abi=", null);

                                final String v_app_info_size = zf.qc(inis[8], "app-info-size=", null);

                                final String v_app_info_author = zf.qc(inis[9], "app-info-author=", null);

                                final String v_app_latest_ver = zf.qc(inis[10], "app-latest-ver=", null);

                                final String v_app_latest_code = zf.qc(inis[11], "app-latest-code=", null);

                                final String v_app_latest_date = zf.qc(inis[12], "app-latest-date=", null);

                                final String v_app_latest_update = zf.th(zf.qc(inis[13], "app-latest-update=", null), "<br>", "\n", true);

                                final String v_app_more_limit = zf.qc(inis[14], "app-more-limit=", null);

                                final String v_app_comment_show = zf.th(zf.qc(inis[15], "app-comment-show=", null), "<br>", "\n", true);

                                final String xbtx = "应用最新版本为" + v_app_latest_ver + "(" + v_app_latest_code + "),于" + v_app_latest_date + "发布, 更新内容如下:\n" + v_app_latest_update;

                                final String gdxx = "应用包名: " + v_id + "\n应用分级: " + v_app_more_limit;

                                String downfile = getAppIconUrl(id);

                                String downfilepath = img_app_icon_path;

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

                                final boolean vv_yysfaz = 应用工具.应用是否安装(lei, v_id);

                                final long vv_yybbh = 应用工具.获取应用版本号(lei, v_id);
                                v_appcode = IappCompat.zh(sj.zh()).zlong(v_app_latest_code, vv_yybbh);
                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        if (vv_yysfaz) {

                                            if (v_appcode > vv_yybbh) {

                                                tx6.tx(R.mipmap.a62);
                                                app_state = "update";

                                            } else {

                                                tx6.tx(R.mipmap.a61);
                                                app_state = "open";

                                            }

                                        } else {

                                            tx6.tx(R.mipmap.a58);
                                            app_state = "download";

                                        }

                                        if (wj.cz(img_app_icon_path)) {

                                            tx2.tx(img_app_icon_path);

                                        } else {

                                            tx2.tx(R.mipmap.appicon);

                                        }

                                        if (wj.cz(img_app_show1_path)) {

                                            tx3.tx(img_app_show1_path);

                                        } else {

                                            tx3.tx(R.mipmap.b11);

                                        }

                                        if (wj.cz(img_app_show2_path)) {

                                            tx4.tx(img_app_show2_path);

                                        } else {

                                            tx4.tx(R.mipmap.b11);

                                        }

                                        if (wj.cz(img_app_show3_path)) {

                                            tx10.tx(img_app_show3_path);

                                        } else {

                                            tx10.tx(R.mipmap.b11);

                                        }

                                        if (wj.cz(img_app_show4_path)) {

                                            tx11.tx(img_app_show4_path);

                                        } else {

                                            tx11.tx(R.mipmap.b11);

                                        }

                                        if (wj.cz(img_app_show5_path)) {

                                            tx12.tx(img_app_show5_path);

                                        } else {

                                            tx12.tx(R.mipmap.b11);

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

                        } catch (Throwable e) {

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

                    gj.jmxc(new Runnable() {

                        public void run() {

                            st.wb(R.id.wb30).zf("加载失败");

                        }

                    });

                }

            }

        });

    }

    public void exitdel() {

        String id = s_id;

        final String img_app_icon_path = AppPaths.appPath("data/app/img/") + id + "/icon.png";

        final String img_app_show1_path = AppPaths.appPath("data/app/img/") + id + "/show1.png";

        final String img_app_show2_path = AppPaths.appPath("data/app/img/") + id + "/show2.png";

        final String img_app_show3_path = AppPaths.appPath("data/app/img/") + id + "/show3.png";

        final String img_app_show4_path = AppPaths.appPath("data/app/img/") + id + "/show4.png";

        final String img_app_show5_path = AppPaths.appPath("data/app/img/") + id + "/show5.png";

        final String img_video_show_path = AppPaths.appPath("data/video/img/") + id + "/show.png";
        wj.sc(img_app_icon_path);
        wj.sc(img_app_show1_path);
        wj.sc(img_app_show2_path);
        wj.sc(img_app_show3_path);
        wj.sc(img_app_show4_path);
        wj.sc(img_app_show5_path);
        wj.sc(img_video_show_path);

    }

    public String getIni(Object type, Object id) {

        if (e1.yz()) {

            String url = src_url + "/" + type + "/" + id + "/config.ini";
            return wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

        } else {

            return "";

        }

    }

    public String getFullUrl(Object type, Object id, Object file) {

        if (zf.ckt(file, "./")) {

            return src_url + "/" + type + "/" + id + "/" + zf.qc(file, "./", null);

        } else {

            return IappCompat.zh(sj.zh()).zstring(file);

        }

    }

    public String getAppIconUrl(Object id) {
        return src_url + "/app/" + id + "/app-image/icon.png";

    }

    public String getAppShowUrl(Object id, Object i) {
        return src_url + "/app/" + id + "/app-image/show" + i + ".png";

    }

    public int homeshow_filedown(Object url, Object post, Object cookie, Object path) {

        Object a = url;

        Object b = path;

        Object c = post;

        Object d = cookie;

        boolean e = true;

        String f = "User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept-language=zh-CN";
        return wl.xz(a, b, true, c, "utf-8", d, e, f);

    }

    public void show(int i_) {

        final int i = i_;
        gj.jmxc(new Runnable() {

            public void run() {

                i.runlibrary.app.v.wb tab = st.wb(R.id.title_bar);

                i.runlibrary.app.v.xxbj x1 = st.xxbj(R.id.xxbj2);

                i.runlibrary.app.v.xxbj x2 = st.xxbj(R.id.xxbj4);

                i.runlibrary.app.v.xxbj x3 = st.xxbj(R.id.xxbj5);

                i.runlibrary.app.v.xxbj x4 = st.xxbj(R.id.xxbj6);

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
        exitdel();

    }

    private void onTitleBarClick(View vw) {
        gj.gb();

    }

    private void onTx1Click(View vw) {

        final String path = AppPaths.appPath("data/video/img/") + s_id + "/show.png";

        final String[] name = new String[]{"url"};

        final String[] value = new String[]{path};
        gj.jmxc(new Runnable() {

            public void run() {

                gj.tz(ImageViewerActivity.class, name, value);

            }

        });

    }

    private void onXdbj9Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                String[] name = new String[]{"url", "title"};

                String[] value = new String[]{v_aurl, v_title};
                gj.tz(VideoPlayerActivity.class, name, value);

            }

        });

    }

    private boolean onXdbj9LongClick(View vw) {

        String te = st.wb(R.id.wb4).zf();

        String lj = st.wb(R.id.wb5).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj12Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                String[] name = new String[]{"url", "title"};

                String[] value = new String[]{v_aurl, v_title + "-" + v_text};
                gj.tz(VideoPlayerActivity.class, name, value);

            }

        });

    }

    private boolean onXdbj12LongClick(View vw) {

        String te = st.wb(R.id.wb9).zf();

        String lj = st.wb(R.id.wb10).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx7Click(View vw) {
        gj.gb();

    }

    private void onTx8Click(View vw) {

        String surl = "https://www.typheye.cn/share/app/?uri=" + s_id;
        e1.tsk_QR("应用分享", surl + "\n\n扫描二维码快速分享", surl);

    }

    private void onTx6Click(View vw) {

        if (zf.dy(app_state, "open")) {

            if (zf.dy(v_id, "open.cn.awg.pro")) {

                e1.tsk("提示", "已经在应用内了");

            } else {

                应用工具.启动应用(lei, v_id);

            }

        } else {

            gj.jmxc(new Runnable() {

                public void run() {

                    String[] iyu = new String[]{"url", "type", "appid"};

                    String[] val = new String[]{v_aurl, "app", v_id};
                    gj.tz(AppDownloadActivity.class, iyu, val);

                }

            });

        }

    }

    private void onTx3Click(View vw) {

        final String path = AppPaths.appPath("data/app/img/") + s_id + "/show1.png";

        final String[] name = new String[]{"url"};

        final String[] value = new String[]{path};
        gj.jmxc(new Runnable() {

            public void run() {

                gj.tz(ImageViewerActivity.class, name, value);

            }

        });

    }

    private void onTx4Click(View vw) {

        final String path = AppPaths.appPath("data/app/img/") + s_id + "/show2.png";

        final String[] name = new String[]{"url"};

        final String[] value = new String[]{path};
        gj.jmxc(new Runnable() {

            public void run() {

                gj.tz(ImageViewerActivity.class, name, value);

            }

        });

    }

    private void onTx10Click(View vw) {

        final String path = AppPaths.appPath("data/app/img/") + s_id + "/show3.png";

        final String[] name = new String[]{"url"};

        final String[] value = new String[]{path};
        gj.jmxc(new Runnable() {

            public void run() {

                gj.tz(ImageViewerActivity.class, name, value);

            }

        });

    }

    private void onTx11Click(View vw) {

        final String path = AppPaths.appPath("data/app/img/") + s_id + "/show4.png";

        final String[] name = new String[]{"url"};

        final String[] value = new String[]{path};
        gj.jmxc(new Runnable() {

            public void run() {

                gj.tz(ImageViewerActivity.class, name, value);

            }

        });

    }

    private void onTx12Click(View vw) {

        final String path = AppPaths.appPath("data/app/img/") + s_id + "/show5.png";

        final String[] name = new String[]{"url"};

        final String[] value = new String[]{path};
        gj.jmxc(new Runnable() {

            public void run() {

                gj.tz(ImageViewerActivity.class, name, value);

            }

        });

    }

    private void onTx9Click(View vw) {
        gj.jmxc(new Runnable() {

            public void run() {

                String[] name = new String[]{"url", "title"};

                String[] value = new String[]{v_aurl, v_title + "-" + v_text};
                gj.tz(VideoPlayerActivity.class, name, value);

            }

        });

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        RelativeLayout i5a93676674 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj9);
        i5a93676674.setOnClickListener(xdbj9ClickListener);
        i5a93676674.setOnLongClickListener(xdbj9LongClickListener);

        ImageView i4a4248a0f2 = (ImageView) findViewById(ay, vw, R.id.tx5);

        RelativeLayout f73b53def9 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj12);
        f73b53def9.setOnClickListener(xdbj12ClickListener);
        f73b53def9.setOnLongClickListener(xdbj12LongClickListener);

        ImageView i3621e3f4a1 = (ImageView) findViewById(ay, vw, R.id.tx7);
        i3621e3f4a1.setOnClickListener(tx7ClickListener);

        ImageView cb1d655d23 = (ImageView) findViewById(ay, vw, R.id.tx8);
        cb1d655d23.setOnClickListener(tx8ClickListener);

        ImageView i6f2c7751cf = (ImageView) findViewById(ay, vw, R.id.tx6);
        i6f2c7751cf.setOnClickListener(tx6ClickListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i11aeec1890 = (ImageView) findViewById(ay, vw, R.id.tx4);
        i11aeec1890.setOnClickListener(tx4ClickListener);

        ImageView i3ab5c5fd62 = (ImageView) findViewById(ay, vw, R.id.tx10);
        i3ab5c5fd62.setOnClickListener(tx10ClickListener);

        ImageView i75b44e9f85 = (ImageView) findViewById(ay, vw, R.id.tx11);
        i75b44e9f85.setOnClickListener(tx11ClickListener);

        ImageView f1070075ac = (ImageView) findViewById(ay, vw, R.id.tx12);
        f1070075ac.setOnClickListener(tx12ClickListener);

        ImageView ee0ece10ae = (ImageView) findViewById(ay, vw, R.id.tx9);
        ee0ece10ae.setOnClickListener(tx9ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
