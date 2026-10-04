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
    public AppRuntimeBridge e1;
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
        e1 = new AppRuntimeBridge(this);
        setContentView(R.layout.store_detail);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        uri = StoreDetailActivity.this.getIntent().getStringExtra(String.valueOf("uri"));
        csh();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.store_detail_root, R.id.xdbj1, R.id.title_bar);

    }

    public void csh() {

        String set67 = AppPaths.appPath("settings/set7.inf");
        src_url = open.cn.awg.pro.core.NativeFiles.readText(StoreDetailActivity.this, (String) (set67));

        final android.widget.ImageView tx1 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.tx1));

        final android.widget.ImageView tx2 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.tx2));

        final android.widget.ImageView tx3 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.tx3));

        final android.widget.ImageView tx4 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.tx4));

        final android.widget.ImageView tx10 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.tx10));

        final android.widget.ImageView tx11 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.tx11));

        final android.widget.ImageView tx12 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.tx12));

        final android.widget.TextView wb1 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.wb1));

        final android.widget.TextView wb6 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.wb6));

        final android.widget.TextView wb2 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.wb2));

        final android.widget.TextView wb7 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.wb7));

        final android.widget.TextView wb3 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.wb3));

        final android.widget.TextView wb19 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.wb19));

        final android.widget.TextView wb18 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.wb18));

        final android.widget.TextView wb20 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.wb20));

        final android.widget.TextView wb16 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.wb16));

        final android.widget.TextView wb24 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.wb24));

        final android.widget.TextView wb15 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.wb15));

        final android.widget.TextView wb8 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.wb8));

        final android.widget.TextView wb14 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.wb14));

        final android.widget.TextView wb21 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.wb21));

        final android.widget.ImageView tx6 = ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.tx6));
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                show(4);
                open.cn.awg.pro.core.NativeActions.sleep(500);

                boolean canshow = false;

                String id = "";

                String ini = "";

                if (open.cn.awg.pro.core.TextValues.startsWith(uri, "awg://uri/video")) {

                    id = open.cn.awg.pro.core.TextValues.slice(uri, "awg://uri/video/", null);
                    s_id = id;

                    final String img_video_show_path = AppPaths.appPath("data/video/img/") + id + "/show.png";
                    ini = getIni("video", id);

                    if (!java.util.Objects.equals(ini, null)) {

                        try {

                            String[] inis = open.cn.awg.pro.core.TextValues.split(ini, "\n", true);

                            if (java.util.Objects.equals(inis[0], "[CONF:V1]")) {

                                v_title = open.cn.awg.pro.core.TextValues.slice(inis[1], "title=", null);
                                v_text = open.cn.awg.pro.core.TextValues.replace(open.cn.awg.pro.core.TextValues.slice(inis[2], "text=", null), "<br>", "\n", true);
                                v_aurl = getFullUrl("video", id, open.cn.awg.pro.core.TextValues.slice(inis[3], "aurl=", null));
                                v_img_show = getFullUrl("video", id, open.cn.awg.pro.core.TextValues.slice(inis[4], "img-show=", null));

                                String downfile = v_img_show;

                                String downfilepath = img_video_show_path;

                                if (!open.cn.awg.pro.core.NativeFiles.exists(StoreDetailActivity.this, (String) (downfilepath))) {

                                    if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                        open.cn.awg.pro.core.NativeFiles.delete(StoreDetailActivity.this, (String) (downfilepath), false);

                                    }

                                }
                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        if (open.cn.awg.pro.core.NativeFiles.exists(StoreDetailActivity.this, (String) (img_video_show_path))) {

                                            open.cn.awg.pro.core.NativeViewValues.image(tx1, img_video_show_path);

                                        } else {

                                            open.cn.awg.pro.core.NativeViewValues.image(tx1, R.mipmap.b11);

                                        }
                                        wb1.setText(String.valueOf(v_title));
                                        wb6.setText(String.valueOf(v_text));

                                    }

                                });
                                canshow = true;

                            }

                        } catch (Throwable e) {

                            e1.upload_error(e, "p2.csh()");

                        }

                    }

                } else if (open.cn.awg.pro.core.TextValues.startsWith(uri, "awg://uri/music")) {

                    id = open.cn.awg.pro.core.TextValues.slice(uri, "awg://uri/music/", null);
                    ini = getIni("music", id);

                    if (!java.util.Objects.equals(ini, null)) {

                        try {

                            String[] inis = open.cn.awg.pro.core.TextValues.split(ini, "\n", true);

                            if (java.util.Objects.equals(inis[0], "[CONF:V1]")) {

                                v_title = open.cn.awg.pro.core.TextValues.slice(inis[1], "title=", null);
                                v_text = open.cn.awg.pro.core.TextValues.slice(inis[2], "text=", null);
                                v_aurl = getFullUrl("music", id, open.cn.awg.pro.core.TextValues.slice(inis[3], "aurl=", null));
                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        wb2.setText(String.valueOf(v_title));
                                        wb7.setText(String.valueOf(v_text));

                                    }

                                });
                                canshow = true;

                            }

                        } catch (Throwable e) {

                            e1.upload_error(e, "p2.csh()");

                        }

                    }

                } else if (open.cn.awg.pro.core.TextValues.startsWith(uri, "awg://uri/app")) {

                    id = open.cn.awg.pro.core.TextValues.slice(uri, "awg://uri/app/", null);
                    s_id = id;

                    final String img_app_icon_path = AppPaths.appPath("data/app/img/") + id + "/icon.png";

                    final String img_app_show1_path = AppPaths.appPath("data/app/img/") + id + "/show1.png";

                    final String img_app_show2_path = AppPaths.appPath("data/app/img/") + id + "/show2.png";

                    final String img_app_show3_path = AppPaths.appPath("data/app/img/") + id + "/show3.png";

                    final String img_app_show4_path = AppPaths.appPath("data/app/img/") + id + "/show4.png";

                    final String img_app_show5_path = AppPaths.appPath("data/app/img/") + id + "/show5.png";
                    ini = getIni("app", id);

                    if (!java.util.Objects.equals(ini, null)) {

                        try {

                            String[] inis = open.cn.awg.pro.core.TextValues.split(ini, "\n", true);

                            if (java.util.Objects.equals(inis[0], "[CONF:V1]")) {

                                v_id = open.cn.awg.pro.core.TextValues.slice(inis[1], "id=", null);
                                v_title = open.cn.awg.pro.core.TextValues.slice(inis[2], "title=", null);
                                v_text = open.cn.awg.pro.core.TextValues.replace(open.cn.awg.pro.core.TextValues.slice(inis[3], "text=", null), "<br>", "\n", true);
                                v_aurl = getFullUrl("app", id, open.cn.awg.pro.core.TextValues.slice(inis[4], "aurl=", null));

                                final String v_app_info_level = open.cn.awg.pro.core.TextValues.slice(inis[5], "app-info-level=", null);

                                final String v_app_info_language = open.cn.awg.pro.core.TextValues.slice(inis[6], "app-info-language=", null);

                                final String v_app_info_abi = open.cn.awg.pro.core.TextValues.slice(inis[7], "app-info-abi=", null);

                                final String v_app_info_size = open.cn.awg.pro.core.TextValues.slice(inis[8], "app-info-size=", null);

                                final String v_app_info_author = open.cn.awg.pro.core.TextValues.slice(inis[9], "app-info-author=", null);

                                final String v_app_latest_ver = open.cn.awg.pro.core.TextValues.slice(inis[10], "app-latest-ver=", null);

                                final String v_app_latest_code = open.cn.awg.pro.core.TextValues.slice(inis[11], "app-latest-code=", null);

                                final String v_app_latest_date = open.cn.awg.pro.core.TextValues.slice(inis[12], "app-latest-date=", null);

                                final String v_app_latest_update = open.cn.awg.pro.core.TextValues.replace(open.cn.awg.pro.core.TextValues.slice(inis[13], "app-latest-update=", null), "<br>", "\n", true);

                                final String v_app_more_limit = open.cn.awg.pro.core.TextValues.slice(inis[14], "app-more-limit=", null);

                                final String v_app_comment_show = open.cn.awg.pro.core.TextValues.replace(open.cn.awg.pro.core.TextValues.slice(inis[15], "app-comment-show=", null), "<br>", "\n", true);

                                final String xbtx = "应用最新版本为" + v_app_latest_ver + "(" + v_app_latest_code + "),于" + v_app_latest_date + "发布, 更新内容如下:\n" + v_app_latest_update;

                                final String gdxx = "应用包名: " + v_id + "\n应用分级: " + v_app_more_limit;

                                String downfile = getAppIconUrl(id);

                                String downfilepath = img_app_icon_path;

                                if (!open.cn.awg.pro.core.NativeFiles.exists(StoreDetailActivity.this, (String) (downfilepath))) {

                                    if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                        open.cn.awg.pro.core.NativeFiles.delete(StoreDetailActivity.this, (String) (downfilepath), false);

                                    }

                                }
                                downfile = getAppShowUrl(id, "1");
                                downfilepath = img_app_show1_path;

                                if (!open.cn.awg.pro.core.NativeFiles.exists(StoreDetailActivity.this, (String) (downfilepath))) {

                                    if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                        open.cn.awg.pro.core.NativeFiles.delete(StoreDetailActivity.this, (String) (downfilepath), false);

                                    }

                                }
                                downfile = getAppShowUrl(id, "2");
                                downfilepath = img_app_show2_path;

                                if (!open.cn.awg.pro.core.NativeFiles.exists(StoreDetailActivity.this, (String) (downfilepath))) {

                                    if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                        open.cn.awg.pro.core.NativeFiles.delete(StoreDetailActivity.this, (String) (downfilepath), false);

                                    }

                                }
                                downfile = getAppShowUrl(id, "3");
                                downfilepath = img_app_show3_path;

                                if (!open.cn.awg.pro.core.NativeFiles.exists(StoreDetailActivity.this, (String) (downfilepath))) {

                                    if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                        open.cn.awg.pro.core.NativeFiles.delete(StoreDetailActivity.this, (String) (downfilepath), false);

                                    }

                                }
                                downfile = getAppShowUrl(id, "4");
                                downfilepath = img_app_show4_path;

                                if (!open.cn.awg.pro.core.NativeFiles.exists(StoreDetailActivity.this, (String) (downfilepath))) {

                                    if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                        open.cn.awg.pro.core.NativeFiles.delete(StoreDetailActivity.this, (String) (downfilepath), false);

                                    }

                                }
                                downfile = getAppShowUrl(id, "5");
                                downfilepath = img_app_show5_path;

                                if (!open.cn.awg.pro.core.NativeFiles.exists(StoreDetailActivity.this, (String) (downfilepath))) {

                                    if (homeshow_filedown(downfile, null, null, downfilepath) == -1) {

                                        open.cn.awg.pro.core.NativeFiles.delete(StoreDetailActivity.this, (String) (downfilepath), false);

                                    }

                                }

                                final boolean vv_yysfaz = 应用工具.应用是否安装(lei, v_id);

                                final long vv_yybbh = 应用工具.获取应用版本号(lei, v_id);
                                v_appcode = open.cn.awg.pro.core.TextValues.longValue(v_app_latest_code, vv_yybbh);
                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        if (vv_yysfaz) {

                                            if (v_appcode > vv_yybbh) {

                                                open.cn.awg.pro.core.NativeViewValues.image(tx6, R.mipmap.a62);
                                                app_state = "update";

                                            } else {

                                                open.cn.awg.pro.core.NativeViewValues.image(tx6, R.mipmap.a61);
                                                app_state = "open";

                                            }

                                        } else {

                                            open.cn.awg.pro.core.NativeViewValues.image(tx6, R.mipmap.a58);
                                            app_state = "download";

                                        }

                                        if (open.cn.awg.pro.core.NativeFiles.exists(StoreDetailActivity.this, (String) (img_app_icon_path))) {

                                            open.cn.awg.pro.core.NativeViewValues.image(tx2, img_app_icon_path);

                                        } else {

                                            open.cn.awg.pro.core.NativeViewValues.image(tx2, R.mipmap.appicon);

                                        }

                                        if (open.cn.awg.pro.core.NativeFiles.exists(StoreDetailActivity.this, (String) (img_app_show1_path))) {

                                            open.cn.awg.pro.core.NativeViewValues.image(tx3, img_app_show1_path);

                                        } else {

                                            open.cn.awg.pro.core.NativeViewValues.image(tx3, R.mipmap.b11);

                                        }

                                        if (open.cn.awg.pro.core.NativeFiles.exists(StoreDetailActivity.this, (String) (img_app_show2_path))) {

                                            open.cn.awg.pro.core.NativeViewValues.image(tx4, img_app_show2_path);

                                        } else {

                                            open.cn.awg.pro.core.NativeViewValues.image(tx4, R.mipmap.b11);

                                        }

                                        if (open.cn.awg.pro.core.NativeFiles.exists(StoreDetailActivity.this, (String) (img_app_show3_path))) {

                                            open.cn.awg.pro.core.NativeViewValues.image(tx10, img_app_show3_path);

                                        } else {

                                            open.cn.awg.pro.core.NativeViewValues.image(tx10, R.mipmap.b11);

                                        }

                                        if (open.cn.awg.pro.core.NativeFiles.exists(StoreDetailActivity.this, (String) (img_app_show4_path))) {

                                            open.cn.awg.pro.core.NativeViewValues.image(tx11, img_app_show4_path);

                                        } else {

                                            open.cn.awg.pro.core.NativeViewValues.image(tx11, R.mipmap.b11);

                                        }

                                        if (open.cn.awg.pro.core.NativeFiles.exists(StoreDetailActivity.this, (String) (img_app_show5_path))) {

                                            open.cn.awg.pro.core.NativeViewValues.image(tx12, img_app_show5_path);

                                        } else {

                                            open.cn.awg.pro.core.NativeViewValues.image(tx12, R.mipmap.b11);

                                        }
                                        wb3.setText(String.valueOf(v_title));
                                        wb19.setText(String.valueOf(v_app_info_size));
                                        wb18.setText(String.valueOf(v_app_info_language));
                                        wb20.setText(String.valueOf(v_app_info_abi));
                                        wb16.setText(String.valueOf(v_app_info_size));
                                        wb24.setText(String.valueOf(v_app_info_author));
                                        wb15.setText(String.valueOf(xbtx));
                                        wb8.setText(String.valueOf(v_text));
                                        wb14.setText(String.valueOf(gdxx));
                                        wb21.setText(String.valueOf(v_app_comment_show));

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

                    if (open.cn.awg.pro.core.TextValues.startsWith(uri, "awg://uri/video")) {

                        show(0);

                    } else if (open.cn.awg.pro.core.TextValues.startsWith(uri, "awg://uri/music")) {

                        show(1);

                    } else if (open.cn.awg.pro.core.TextValues.startsWith(uri, "awg://uri/app")) {

                        show(2);

                    }

                } else {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.wb30)).setText(String.valueOf("加载失败"));

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
        open.cn.awg.pro.core.NativeFiles.delete(StoreDetailActivity.this, (String) (img_app_icon_path), false);
        open.cn.awg.pro.core.NativeFiles.delete(StoreDetailActivity.this, (String) (img_app_show1_path), false);
        open.cn.awg.pro.core.NativeFiles.delete(StoreDetailActivity.this, (String) (img_app_show2_path), false);
        open.cn.awg.pro.core.NativeFiles.delete(StoreDetailActivity.this, (String) (img_app_show3_path), false);
        open.cn.awg.pro.core.NativeFiles.delete(StoreDetailActivity.this, (String) (img_app_show4_path), false);
        open.cn.awg.pro.core.NativeFiles.delete(StoreDetailActivity.this, (String) (img_app_show5_path), false);
        open.cn.awg.pro.core.NativeFiles.delete(StoreDetailActivity.this, (String) (img_video_show_path), false);

    }

    public String getIni(Object type, Object id) {

        if (e1.yz()) {

            String url = src_url + "/" + type + "/" + id + "/config.ini";
            return open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

        } else {

            return "";

        }

    }

    public String getFullUrl(Object type, Object id, Object file) {

        if (open.cn.awg.pro.core.TextValues.startsWith(file, "./")) {

            return src_url + "/" + type + "/" + id + "/" + open.cn.awg.pro.core.TextValues.slice(file, "./", null);

        } else {

            return open.cn.awg.pro.core.TextValues.string(file);

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
        return open.cn.awg.pro.core.NativeHttp.download(StoreDetailActivity.this, String.valueOf(a), (String) (b), java.lang.Boolean.TRUE.equals(true), (String[]) (c), (String) ("utf-8"), (String) (d), java.lang.Boolean.TRUE.equals(e), (String) (f), 0, 0, null);

    }

    public void show(int i_) {

        final int i = i_;
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                android.widget.TextView tab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.title_bar));

                android.widget.LinearLayout x1 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.xxbj2));

                android.widget.LinearLayout x2 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.xxbj4));

                android.widget.LinearLayout x3 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.xxbj5));

                android.widget.LinearLayout x4 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.xxbj6));

                if (i == 0) {

                    tab.setText(String.valueOf("< 视频详情"));
                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                } else if (i == 1) {

                    tab.setText(String.valueOf("< 歌曲详情"));
                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                } else if (i == 2) {

                    tab.setText(String.valueOf("< 应用详情"));
                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                    x4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                } else if (i == 4) {

                    if (open.cn.awg.pro.core.TextValues.startsWith(uri, "awg://uri/video")) {

                        tab.setText(String.valueOf("< 视频详情"));

                    } else if (open.cn.awg.pro.core.TextValues.startsWith(uri, "awg://uri/music")) {

                        tab.setText(String.valueOf("< 歌曲详情"));

                    } else if (open.cn.awg.pro.core.TextValues.startsWith(uri, "awg://uri/app")) {

                        tab.setText(String.valueOf("< 应用详情"));

                    }
                    x1.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                    x4.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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
        exitdel();

    }

    private void onTitleBarClick(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(StoreDetailActivity.this);

    }

    private void onTx1Click(View vw) {

        final String path = AppPaths.appPath("data/video/img/") + s_id + "/show.png";

        final String[] name = new String[]{"url"};

        final String[] value = new String[]{path};
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.launch(StoreDetailActivity.this, (Class<?>) (ImageViewerActivity.class), (String[]) (name), (Object[]) (value));

            }

        });

    }

    private void onXdbj9Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String[] name = new String[]{"url", "title"};

                String[] value = new String[]{v_aurl, v_title};
                open.cn.awg.pro.core.NativeActions.launch(StoreDetailActivity.this, (Class<?>) (VideoPlayerActivity.class), (String[]) (name), (Object[]) (value));

            }

        });

    }

    private boolean onXdbj9LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.wb4)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.wb5)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj12Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String[] name = new String[]{"url", "title"};

                String[] value = new String[]{v_aurl, v_title + "-" + v_text};
                open.cn.awg.pro.core.NativeActions.launch(StoreDetailActivity.this, (Class<?>) (VideoPlayerActivity.class), (String[]) (name), (Object[]) (value));

            }

        });

    }

    private boolean onXdbj12LongClick(View vw) {

        String te = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.wb9)).getText().toString();

        String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(StoreDetailActivity.this.getWindow().getDecorView(), R.id.wb10)).getText().toString();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx7Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(StoreDetailActivity.this);

    }

    private void onTx8Click(View vw) {

        String surl = "https://www.typheye.cn/share/app/?uri=" + s_id;
        e1.tsk_QR("应用分享", surl + "\n\n扫描二维码快速分享", surl);

    }

    private void onTx6Click(View vw) {

        if (java.util.Objects.equals(app_state, "open")) {

            if (java.util.Objects.equals(v_id, "open.cn.awg.pro")) {

                e1.tsk("提示", "已经在应用内了");

            } else {

                应用工具.启动应用(lei, v_id);

            }

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    String[] iyu = new String[]{"url", "type", "appid"};

                    String[] val = new String[]{v_aurl, "app", v_id};
                    open.cn.awg.pro.core.NativeActions.launch(StoreDetailActivity.this, (Class<?>) (AppDownloadActivity.class), (String[]) (iyu), (Object[]) (val));

                }

            });

        }

    }

    private void onTx3Click(View vw) {

        final String path = AppPaths.appPath("data/app/img/") + s_id + "/show1.png";

        final String[] name = new String[]{"url"};

        final String[] value = new String[]{path};
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.launch(StoreDetailActivity.this, (Class<?>) (ImageViewerActivity.class), (String[]) (name), (Object[]) (value));

            }

        });

    }

    private void onTx4Click(View vw) {

        final String path = AppPaths.appPath("data/app/img/") + s_id + "/show2.png";

        final String[] name = new String[]{"url"};

        final String[] value = new String[]{path};
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.launch(StoreDetailActivity.this, (Class<?>) (ImageViewerActivity.class), (String[]) (name), (Object[]) (value));

            }

        });

    }

    private void onTx10Click(View vw) {

        final String path = AppPaths.appPath("data/app/img/") + s_id + "/show3.png";

        final String[] name = new String[]{"url"};

        final String[] value = new String[]{path};
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.launch(StoreDetailActivity.this, (Class<?>) (ImageViewerActivity.class), (String[]) (name), (Object[]) (value));

            }

        });

    }

    private void onTx11Click(View vw) {

        final String path = AppPaths.appPath("data/app/img/") + s_id + "/show4.png";

        final String[] name = new String[]{"url"};

        final String[] value = new String[]{path};
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.launch(StoreDetailActivity.this, (Class<?>) (ImageViewerActivity.class), (String[]) (name), (Object[]) (value));

            }

        });

    }

    private void onTx12Click(View vw) {

        final String path = AppPaths.appPath("data/app/img/") + s_id + "/show5.png";

        final String[] name = new String[]{"url"};

        final String[] value = new String[]{path};
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.launch(StoreDetailActivity.this, (Class<?>) (ImageViewerActivity.class), (String[]) (name), (Object[]) (value));

            }

        });

    }

    private void onTx9Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String[] name = new String[]{"url", "title"};

                String[] value = new String[]{v_aurl, v_title + "-" + v_text};
                open.cn.awg.pro.core.NativeActions.launch(StoreDetailActivity.this, (Class<?>) (VideoPlayerActivity.class), (String[]) (name), (Object[]) (value));

            }

        });

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        RelativeLayout i5a93676674 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj9);
        i5a93676674.setOnClickListener(xdbj9ClickListener);
        i5a93676674.setOnLongClickListener(xdbj9LongClickListener);

        ImageView i4a4248a0f2 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx5);

        RelativeLayout f73b53def9 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj12);
        f73b53def9.setOnClickListener(xdbj12ClickListener);
        f73b53def9.setOnLongClickListener(xdbj12LongClickListener);

        ImageView i3621e3f4a1 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx7);
        i3621e3f4a1.setOnClickListener(tx7ClickListener);

        ImageView cb1d655d23 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx8);
        cb1d655d23.setOnClickListener(tx8ClickListener);

        ImageView i6f2c7751cf = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx6);
        i6f2c7751cf.setOnClickListener(tx6ClickListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        ImageView i11aeec1890 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx4);
        i11aeec1890.setOnClickListener(tx4ClickListener);

        ImageView i3ab5c5fd62 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx10);
        i3ab5c5fd62.setOnClickListener(tx10ClickListener);

        ImageView i75b44e9f85 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx11);
        i75b44e9f85.setOnClickListener(tx11ClickListener);

        ImageView f1070075ac = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx12);
        f1070075ac.setOnClickListener(tx12ClickListener);

        ImageView ee0ece10ae = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx9);
        ee0ece10ae.setOnClickListener(tx9ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
