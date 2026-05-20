/**
 ******************************************************************************
 * @file    j6.java
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

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebStorage;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.smtt.export.external.interfaces.HttpAuthHandler;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.PermissionRequest;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebView;
import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;

public class j6 extends iActivity {

    public final j6 lei = this, 类 = this;
    public open.cn.awg.pro.e1 e1 = new e1(_APPINFO);
    public java.lang.String hqdz;
    public java.lang.String Url;
    public boolean isTip = false;
    public android.net.Uri cameraUri;
    public com.tencent.smtt.export.external.interfaces.PermissionRequest request2 = null;
    public int REQUEST_CAMERA = 1;
    public int REQUEST_CHOOSE = 2;
    public com.tencent.smtt.sdk.ValueCallback<android.net.Uri> uploadFile;
    public com.tencent.smtt.sdk.ValueCallback<android.net.Uri[]> uploadFiles;
    private final android.view.View.OnClickListener $_on_setOnClickListener_i9068f5d66e = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i9068f5d66e(vw);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_bd584e50cd = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_bd584e50cd(vw, me);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i6c4a6b953f = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i6c4a6b953f(vw, me);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i32ee139ba1 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i32ee139ba1(vw, me);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i3f6c9f386b = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i3f6c9f386b(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_ff56a8e7f6 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_ff56a8e7f6(vw);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i701ef6a186 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i701ef6a186(vw, me);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i0726d8c523 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i0726d8c523(vw, me);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_c26905d21b = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_c26905d21b(vw, me);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i464e6cc6fb = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i464e6cc6fb(vw, me);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_ee3b885ad5 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_ee3b885ad5(vw, me);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i015f182c91 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i015f182c91(vw, me);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_efa568dfe6 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_efa568dfe6(vw, me);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i812801c3fe = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i812801c3fe(vw, me);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_e294616700 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_e294616700(vw, me);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d26d5d7080(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.j6);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.e6.getInstance().addActivity(lei);

        java.lang.String ul = sj.hqtz("url");
        ul = zf.qctwkg(ul);

        if (!zf.dy(ul, null) && !zf.dy(ul, "")) {

            Url = ul;

        } else {

            android.content.Intent intent = getIntent();
            String action = intent.getAction();
            if (Intent.ACTION_VIEW.equals(action)) {
                android.net.Uri uri = intent.getData();
                hqdz = uri.getScheme() + "://" + uri.getHost();
                if (uri.getPort() != -1) hqdz = hqdz + ":" + uri.getPort();
                if (uri.getPath() != null) hqdz = hqdz + uri.getPath();
                if (uri.getQuery() != null) hqdz = hqdz + "?" + uri.getQuery();
            }

            if (!zf.dy(hqdz, null) && !zf.dy(hqdz, "")) {

                Url = hqdz;

            }

        }

        if (wj.cz("/data/user/0/open.cn.awg.pro/app_tbs/core_share/tbs.conf") || wj.cz("/data/user/0/open.cn.awg.pro/app_tbs_64/core_share/tbs.conf")) {

            try {

                csh();

            } catch (java.lang.Throwable e) {

                e1.tsk("提示", "出现异常\n" + e);

            }

        } else {

            gj.tz(l4.class);
            e1.tsk("提示", "未找到腾讯X5内核,无法启动X5浏览器\n将为您跳转到内核安装\nPS:轻触顶部标题栏关闭提示");
            gj.gb();

        }

    }

    public void __layoutIsLoaded(android.app.Activity ay, android.view.View vw) {

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
        restart();

        if (zf.dy(a, "1")) {

            st.xxbj(open.cn.awg.pro.R.id.xxbj9).nbj(u, 0, u, 50);
            st.xdbj(open.cn.awg.pro.R.id.xdbj3).nbj(u, 0, u, 50);

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
            st.xxbj(open.cn.awg.pro.R.id.xxbj9).nbj(0, 0, 0, 0);
            st.xdbj(open.cn.awg.pro.R.id.xdbj3).nbj(0, 0, 0, 0);

        } else if (zf.dy(a, "3")) {

            st.xxbj(open.cn.awg.pro.R.id.xxbj9).nbj(u, 0, u, 50);
            st.xdbj(open.cn.awg.pro.R.id.xdbj3).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "4")) {

            int uih = sj.zh().pxzdp(pm.ztl);
            uih = uih + 9;

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, uih + "dp", 0, "9dp");
            wtab.dqfs("center");
            st.xxbj(open.cn.awg.pro.R.id.xxbj9).nbj(0, 0, 0, 0);
            st.xdbj(open.cn.awg.pro.R.id.xdbj3).nbj(0, 0, 0, 0);

        }

    }

    public void llqhdyc() {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.xdbj(open.cn.awg.pro.R.id.llqHdRoot).kjd(8);
                st.xxbj(open.cn.awg.pro.R.id.llqAuSfrz).kjd(8);
                st.xxbj(open.cn.awg.pro.R.id.llqJsAlert).kjd(8);
                st.xxbj(open.cn.awg.pro.R.id.llqJsConfirm).kjd(8);
                st.xxbj(open.cn.awg.pro.R.id.llqJsPrompt).kjd(8);
                st.xxbj(open.cn.awg.pro.R.id.llqQqDkyy).kjd(8);

            }

        });

    }

    public void csh() {

        final java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/f13/set1.inf";

        final java.lang.String set2 = "/data/user/0/open.cn.awg.pro/settings/f13/set2.inf";

        final java.lang.String set3 = "/data/user/0/open.cn.awg.pro/settings/f13/set3.inf";

        if (zf.dy(wj.dqwb(set1), "true")) {

            int pk;
            pk = open.cn.awg.pro.e6.x5(lei);

            if (pk != 1) {

                e1.tsk("提示", "X5浏览器内核不完整!\n请等待内核下载完成后,再次打开X5浏览器\nPS:轻触顶部标题栏关闭提示");
                gj.gb();

            }

        }

        if (zf.dy(Url, null) || zf.dy(Url, "") || zf.dy(Url, "null")) {

            Url = wj.dqwb(set2);
            Url = e1.urlUnlockString(Url);

        }

        final i.runlibrary.app.v.xxbj xxbj = st.xxbj(open.cn.awg.pro.R.id.llq_v);

        final i.runlibrary.app.v.wb bt = st.wb(open.cn.awg.pro.R.id.Tab);

        final i.runlibrary.app.v.bjk bjk1 = st.bjk(open.cn.awg.pro.R.id.bjk1);

        final i.runlibrary.app.v.xxbj xxbj8 = st.xxbj(open.cn.awg.pro.R.id.xxbj8);

        final i.runlibrary.app.v.xxbj xxbj4 = st.xxbj(open.cn.awg.pro.R.id.xxbj4);

        final i.runlibrary.app.v.kp kp3 = st.kp(open.cn.awg.pro.R.id.kp3);

        final i.runlibrary.app.v.jdt jdt1 = st.jdt(open.cn.awg.pro.R.id.jdt1);

        final i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(open.cn.awg.pro.R.id.qtgd1);

        final i.runlibrary.app.v.qtgd qtgd2 = st.qtgd(open.cn.awg.pro.R.id.qtgd2);

        final java.lang.String isShowTab = "/data/user/0/open.cn.awg.pro/settings/f13/set4.inf";

        lei.getWindow().setFormat(PixelFormat.TRANSLUCENT);
        Button an1 = findViewById(R.id.an1);
        Button an2 = findViewById(R.id.an2);
        Button an4 = findViewById(R.id.an4);
        Button an3 = findViewById(R.id.an3);
        ImageView tx5 = findViewById(R.id.tx5);
        ImageView tx6 = findViewById(R.id.tx6);
        ImageView tx3 = findViewById(R.id.tx3);
        ImageView tx4 = findViewById(R.id.tx4);
        ImageView tx7 = findViewById(R.id.tx7);
        TextView wb1 = findViewById(R.id.wb1);
        final RelativeLayout llqHdRoot = findViewById(R.id.llqHdRoot);
        final LinearLayout llqJsAlert = findViewById(R.id.llqJsAlert);
        final TextView llqJsAlertTs = findViewById(R.id.llqJsAlertTs);
        final ImageView llqJsAlertTrue = findViewById(R.id.llqJsAlertTrue);
        final LinearLayout llqJsConfirm = findViewById(R.id.llqJsConfirm);
        final TextView llqJsConfirmTs = findViewById(R.id.llqJsConfirmTs);
        final ImageView llqJsConfirmTrue = findViewById(R.id.llqJsConfirmTrue);
        final ImageView llqJsConfirmFalse = findViewById(R.id.llqJsConfirmFalse);
        final LinearLayout llqJsPrompt = findViewById(R.id.llqJsPrompt);
        final TextView llqJsPromptTs = findViewById(R.id.llqJsPromptTs);
        final EditText llqJsPromptBjk = findViewById(R.id.llqJsPromptBjk);
        final ImageView llqJsPromptTrue = findViewById(R.id.llqJsPromptTrue);
        final ImageView llqJsPromptFalse = findViewById(R.id.llqJsPromptFalse);
        final LinearLayout llqAuSfrz = findViewById(R.id.llqAuSfrz);
        final TextView llqAuSfrzTs = findViewById(R.id.llqAuSfrzTs);
        final EditText llqAuSfrzUsername = findViewById(R.id.llqAuSfrzUsername);
        final EditText llqAuSfrzPassword = findViewById(R.id.llqAuSfrzPassword);
        final ImageView llqAuSfrzTrue = findViewById(R.id.llqAuSfrzTrue);
        final ImageView llqAuSfrzFalse = findViewById(R.id.llqAuSfrzFalse);
        final LinearLayout llqQqDkyy = findViewById(R.id.llqQqDkyy);
        final TextView llqQqDkyyTs = findViewById(R.id.llqQqDkyyTs);
        final ImageView llqQqDkyyTrue = findViewById(R.id.llqQqDkyyTrue);
        final ImageView llqQqDkyyFalse = findViewById(R.id.llqQqDkyyFalse);
        final i.app.x5 x5llq = i.app.x5.x5(lei);
        xxbj.j(x5llq.llq(-1, -1, Url));
        x5llq.st.setFocusable(true);
        x5llq.st.setFocusableInTouchMode(true);
        x5llq.st.requestFocus();
        x5llq.st.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                com.tencent.smtt.sdk.WebView.HitTestResult htr = x5llq.st.getHitTestResult();
                int type = htr.getType();
                final int IMAGE_TYPE = com.tencent.smtt.sdk.WebView.HitTestResult.IMAGE_TYPE;
                final int SRC_IMAGE_ANCHOR_TYPE = com.tencent.smtt.sdk.WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE;
                if (type == IMAGE_TYPE || type == SRC_IMAGE_ANCHOR_TYPE) {
                    String imageUrl = htr.getExtra();
                    Intent intent = new Intent(lei, open.cn.awg.pro.l2.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("url", imageUrl);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                return true;
            }
        });
        x5llq.st.setWebChromeClient(new com.tencent.smtt.sdk.WebChromeClient() {

            private View myVideoView = null;
            private com.tencent.smtt.export.external.interfaces.IX5WebChromeClient.CustomViewCallback callback = null;

            public void onShowCustomView(View view, com.tencent.smtt.export.external.interfaces.IX5WebChromeClient.CustomViewCallback customViewCallback) {
                ViewGroup viewGroup = (ViewGroup) x5llq.st.getParent();
                viewGroup.removeView(x5llq.st);
                viewGroup.addView(view);
                myVideoView = view;
                callback = customViewCallback;
            }

            public void onHideCustomView() {
                if (callback != null) {
                    callback.onCustomViewHidden();
                    callback = null;
                }
                if (myVideoView != null) {
                    ViewGroup viewGroup = (ViewGroup) myVideoView.getParent();
                    viewGroup.removeView(myVideoView);
                    viewGroup.addView(x5llq.st);
                }
            }

            public void onExceededDatabaseQuota(String url, String databaseIdentifier, long currentQuota, long estimatedSize, long totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) {
                quotaUpdater.updateQuota(estimatedSize * 2);
            }

            public void onReachedMaxAppCacheSize(long spaceNeeded, long totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) {
                quotaUpdater.updateQuota(spaceNeeded * 2);
            }

            public boolean onShowFileChooser(com.tencent.smtt.sdk.WebView webView,
                                             com.tencent.smtt.sdk.ValueCallback<android.net.Uri[]> filePathCallback,
                                             com.tencent.smtt.sdk.WebChromeClient.FileChooserParams fileChooserParams) {

                uploadFiles = filePathCallback;
                android.content.Intent i = new android.content.Intent(android.content.Intent.ACTION_GET_CONTENT);
                i.addCategory(android.content.Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                startActivityForResult(android.content.Intent.createChooser(i, "test"), 0);
                return true;
            }

            public void onProgressChanged(com.tencent.smtt.sdk.WebView vw, int np) {
                if (np < 99) {
                    jdt1.kjd(0);
                    jdt1.jdz(np);
                } else {
                    jdt1.jdz(100);
                    jdt1.kjd(8);
                }
                String btss = x5llq.bt();
                String wzss = x5llq.wz();
                if (!isTip) {
                    if (btss == null || btss == "" || btss == "null") {

                    } else {

                    }
                }
            }

            @Override
            public boolean onJsAlert(WebView view, String url2, String message, final JsResult result) {

                llqHdRoot.setVisibility(View.VISIBLE);
                llqJsAlert.setVisibility(View.VISIBLE);
                llqJsAlertTs.setText(message);
                llqJsAlertTrue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        result.confirm();
                        llqHdRoot.setVisibility(View.GONE);
                        llqJsAlert.setVisibility(View.GONE);
                        x5llq.st.requestFocus();
                    }
                });
                qtgd2.v.requestFocus();
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url2, String message, final JsResult result) {

                llqHdRoot.setVisibility(View.VISIBLE);
                llqJsConfirm.setVisibility(View.VISIBLE);
                llqJsConfirmTs.setText(message);
                llqJsConfirmTrue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        result.confirm();
                        llqHdRoot.setVisibility(View.GONE);
                        llqJsConfirm.setVisibility(View.GONE);
                        x5llq.st.requestFocus();
                    }
                });
                llqJsConfirmFalse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        result.cancel();
                        llqHdRoot.setVisibility(View.GONE);
                        llqJsConfirm.setVisibility(View.GONE);
                        x5llq.st.requestFocus();
                    }
                });
                qtgd2.v.requestFocus();
                return true;
            }

            @Override
            public boolean onJsPrompt(WebView view, String url2, String message, String defaultValue, final JsPromptResult result) {

                llqHdRoot.setVisibility(View.VISIBLE);
                llqJsPrompt.setVisibility(View.VISIBLE);
                llqJsPromptTs.setText(message);
                llqJsPromptBjk.setText(defaultValue);
                llqJsPromptTrue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        result.confirm(llqJsPromptBjk.getText().toString());
                        llqHdRoot.setVisibility(View.GONE);
                        llqJsConfirm.setVisibility(View.GONE);
                        x5llq.st.requestFocus();
                    }
                });
                llqJsPromptFalse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        result.cancel();
                        llqHdRoot.setVisibility(View.GONE);
                        llqJsConfirm.setVisibility(View.GONE);
                        x5llq.st.requestFocus();
                    }
                });
                qtgd2.v.requestFocus();
                return true;
            }

            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                }
            }

        });

        x5llq.st.setWebViewClient(new com.tencent.smtt.sdk.WebViewClient() {
            public boolean shouldOverrideUrlLoading(com.tencent.smtt.sdk.WebView view, String url) {

                String urlX = url.toLowerCase();
                if (urlX.startsWith("http:") || urlX.startsWith("https:") || urlX.startsWith("rtsp:") || urlX.startsWith("ftp:")) {
                    view.loadUrl(url);
                } else {
                    final String url2 = url;
                    llqHdRoot.setVisibility(View.VISIBLE);
                    llqQqDkyy.setVisibility(View.VISIBLE);

                    llqQqDkyyTrue.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                android.content.Intent intent = new android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(url2));
                                intent.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK | android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                startActivity(intent);
                            } catch (Exception e) {

                            }
                            llqHdRoot.setVisibility(View.GONE);
                            llqQqDkyy.setVisibility(View.GONE);
                            x5llq.st.requestFocus();
                        }
                    });
                    llqQqDkyyFalse.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            llqHdRoot.setVisibility(View.GONE);
                            llqQqDkyy.setVisibility(View.GONE);
                            x5llq.st.requestFocus();
                        }
                    });
                    qtgd2.v.requestFocus();
                }
                return true;
            }

            public void onReceivedSslError(com.tencent.smtt.sdk.WebView view, SslErrorHandler handler, SslError error) {

                handler.proceed();
            }

            public void onPageStarted(com.tencent.smtt.sdk.WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            public void onPageFinished(com.tencent.smtt.sdk.WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedHttpAuthRequest(WebView view, final HttpAuthHandler handler, String host, String realm) {

                llqHdRoot.setVisibility(View.VISIBLE);
                llqAuSfrz.setVisibility(View.VISIBLE);
                llqAuSfrzUsername.setText("");
                llqAuSfrzPassword.setText("");
                llqAuSfrzTrue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handler.proceed(llqAuSfrzUsername.getText().toString(), llqAuSfrzPassword.getText().toString());
                        llqHdRoot.setVisibility(View.GONE);
                        llqAuSfrz.setVisibility(View.GONE);
                        x5llq.st.requestFocus();
                    }
                });
                llqAuSfrzFalse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handler.cancel();
                        llqHdRoot.setVisibility(View.GONE);
                        llqAuSfrz.setVisibility(View.GONE);
                        x5llq.st.requestFocus();
                    }
                });
                qtgd2.v.requestFocus();
            }
        });

        an1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bj = bjk1.zf();
                bj = zf.qctwkg(bj);
                if (bj == "") {
                    e1.tsk("提示", "编辑框不能为空");
                } else {
                    llqhdyc();
                    x5llq.zrwz(bj);
                    kp3.kjd(0);
                    xxbj8.kjd(8);
                    restart();
                    String btss = x5llq.bt();
                    String wzss = x5llq.wz();
                    isTip = false;
                    if (btss == null || btss == "" || btss == "null") {

                    } else {

                    }
                    bt.zf("< X5浏览器");
                }
            }
        });

        an2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llqhdyc();
                x5llq.zrwz("http://debugx5.qq.com");
                kp3.kjd(0);
                xxbj8.kjd(8);
                restart();
                String btss = x5llq.bt();
                String wzss = x5llq.wz();
                isTip = false;
                if (btss == null || btss == "" || btss == "null") {

                } else {

                }
                bt.zf("< X5浏览器");
            }
        });

        an4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gj.tz(open.cn.awg.pro.f13.class);
                kp3.kjd(0);
                xxbj8.kjd(8);
                restart();
                String btss = x5llq.bt();
                String wzss = x5llq.wz();
                isTip = false;
                if (btss == null || btss == "" || btss == "null") {

                } else {

                }
                bt.zf("< X5浏览器");
            }
        });

        an3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llqhdyc();
                x5llq.zrwz("about:blank");
                gj.gb();
            }
        });

        tx5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (xxbj4.kjd() == 0) {
                    xxbj4.kjd(8);
                    kp3.kjd(0);
                    restart();
                } else {
                    xxbj4.kjd(0);
                    kp3.kjd(8);
                }
                boolean pds = x5llq.kht();
                isTip = false;
                if (pds) {
                    llqhdyc();
                    x5llq.kzqjht(-1);
                } else {
                    e1.tsk("提示", "没有可后退的网页");
                }
                bt.zf("< X5浏览器");
                x5llq.st.requestFocus();
            }
        });

        tx6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (xxbj4.kjd() == 0) {
                    xxbj4.kjd(8);
                    kp3.kjd(0);
                    restart();
                } else {
                    xxbj4.kjd(0);
                    kp3.kjd(8);
                }
                boolean pds = x5llq.kqj();
                isTip = false;
                if (pds) {
                    llqhdyc();
                    x5llq.kzqjht(1);
                } else {
                    e1.tsk("提示", "没有可前进的网页");
                }
                bt.zf("< X5浏览器");
                x5llq.st.requestFocus();
            }
        });

        tx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (xxbj4.kjd() == 0) {
                    xxbj4.kjd(8);
                    kp3.kjd(0);
                    restart();
                } else {
                    xxbj4.kjd(0);
                    kp3.kjd(8);
                }
                isTip = false;
                String url = x5llq.wz();
                llqhdyc();
                x5llq.zrwz(url);
                bt.zf("< X5浏览器");
                x5llq.st.requestFocus();
            }
        });

        tx4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xxbj4.kjd(8);
                kp3.kjd(8);
                xxbj8.kjd(0);
                bt.zf("更多");
                isTip = true;
                bjk1.zf(x5llq.wz());
                qtgd1.v.requestFocus();
            }
        });

        tx7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kp3.kjd(0);
                xxbj8.kjd(8);
                restart();
                String btss = x5llq.bt();
                String wzss = x5llq.wz();
                isTip = false;
                if (btss == null || btss == "" || btss == "null") {

                } else {

                }
                bt.zf("< X5浏览器");
                x5llq.st.requestFocus();
            }
        });

        wb1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (llqHdRoot.getVisibility() == View.VISIBLE) {
                    e1.tsk("提示", "请先完成当前操作");
                } else {
                    showtab();
                    xxbj4.kjd(8);
                    kp3.kjd(8);
                    xxbj8.kjd(0);
                    bt.zf("更多");
                    isTip = true;
                    bjk1.zf(x5llq.wz());
                    qtgd1.v.requestFocus();
                }
                return true;
            }
        });

    }

    public void restart() {

        final i.runlibrary.app.v.xxbj xxbj8 = st.xxbj(open.cn.awg.pro.R.id.xxbj8);

        final i.runlibrary.app.v.xxbj xxbj4 = st.xxbj(open.cn.awg.pro.R.id.xxbj4);

        java.lang.String set = "/data/user/0/open.cn.awg.pro/settings/f13/set4.inf";

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            if (zf.dy(wj.dqwb(set), "true") && xxbj4.kjd() == 8 && xxbj8.kjd() == 8) {

                st.wb(open.cn.awg.pro.R.id.Tab).kjd(8);

            } else {

                st.wb(open.cn.awg.pro.R.id.Tab).kjd(0);

            }

        } else {

            if (zf.dy(wj.dqwb(set), "true") && xxbj4.kjd() == 8 && xxbj8.kjd() == 8) {

                st.wb(open.cn.awg.pro.R.id.Tab).kjd(8);

            } else {

                st.wb(open.cn.awg.pro.R.id.Tab).kjd(0);

            }

        }

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public void showtab() {

        java.lang.String set = "/data/user/0/open.cn.awg.pro/settings/f13/set4.inf";

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            st.wb(open.cn.awg.pro.R.id.Tab).kjd(0);

        } else {

            st.wb(open.cn.awg.pro.R.id.Tab).kjd(0);

        }

        Window window = lei.getWindow();
        BangScreenTools.getBangScreenTools().fullscreen(window, lei);
        BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

    }

    public boolean onKeyDown(int kc, android.view.KeyEvent ke) {

        java.lang.String set4 = "/data/user/0/open.cn.awg.pro/settings/f13/set5.inf";

        if (zf.dy(wj.dqwb(set4), "false")) {

            if (kc == 4) {

                gj.gb();

            }

        } else {

            if (kc == 4) {

            }

        }
        return false;

    }

    public void onActivityResult(int re, int rc, android.content.Intent it) {
        super.onActivityResult(re, rc, it);

        if (rc == RESULT_OK) {
            switch (re) {
                case 0:
                    if (uploadFile != null) {
                        android.net.Uri result = it == null || rc != RESULT_OK ? null
                                : it.getData();
                        uploadFile.onReceiveValue(result);
                        uploadFile = null;
                    }
                    if (uploadFiles != null) {
                        android.net.Uri result = it == null || rc != RESULT_OK ? null
                                : it.getData();
                        uploadFiles.onReceiveValue(new android.net.Uri[]{result});
                        uploadFiles = null;
                    }
                    break;
                default:
                    break;
            }
        } else if (rc == RESULT_CANCELED) {
            if (uploadFile != null) {
                uploadFile.onReceiveValue(null);
                uploadFile = null;
            }
            if (uploadFiles != null) {
                uploadFiles.onReceiveValue(null);
                uploadFiles = null;
            }
        }

    }

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.e6.getInstance().removeActivity(lei);

        i.runlibrary.app.v.xxbj xxbj = st.xxbj(open.cn.awg.pro.R.id.llq_v);
        xxbj.scqb();

    }

    public void onRestart() {
        super.onRestart();
        restart();

    }

    public void onStart() {
        super.onStart();
        restart();

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

        i.runlibrary.app.v.qtgd qtgd2 = st.qtgd(open.cn.awg.pro.R.id.qtgd2);

        qtgd2.v.setOnGenericMotionListener(new View.OnGenericMotionListener() {
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

    }

    public void onResume() {
        super.onResume();
        restart();

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
        restart();

    }

    private void $_onClick_d26d5d7080(android.view.View vw) {

        if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< X5浏览器")) {

            gj.gb();

        }

    }

    private boolean $_onTouch_e294616700(android.view.View vw, android.view.MotionEvent me) {

        if (!vw.isFocused()) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    i.runlibrary.app.v.qtgd qtgd2 = st.qtgd(open.cn.awg.pro.R.id.qtgd2);
                    qtgd2.v.requestFocus();

                }

            });

        }
        return false;

    }

    private boolean $_onTouch_i812801c3fe(android.view.View vw, android.view.MotionEvent me) {

        if (!vw.isFocused()) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    i.runlibrary.app.v.qtgd qtgd2 = st.qtgd(open.cn.awg.pro.R.id.qtgd2);
                    qtgd2.v.requestFocus();

                }

            });

        }
        return false;

    }

    private boolean $_onTouch_efa568dfe6(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private boolean $_onTouch_i015f182c91(android.view.View vw, android.view.MotionEvent me) {

        if (!vw.isFocused()) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    i.runlibrary.app.v.qtgd qtgd2 = st.qtgd(open.cn.awg.pro.R.id.qtgd2);
                    qtgd2.v.requestFocus();

                }

            });

        }
        return false;

    }

    private boolean $_onTouch_ee3b885ad5(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private boolean $_onTouch_i464e6cc6fb(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private boolean $_onTouch_c26905d21b(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private boolean $_onTouch_i0726d8c523(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private boolean $_onTouch_i701ef6a186(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private void $_onClick_ff56a8e7f6(android.view.View vw) {

        if (st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd() == 0) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(8);
                    st.kp(open.cn.awg.pro.R.id.kp3).kjd(0);

                }

            });

            java.lang.String set = "/data/user/0/open.cn.awg.pro/settings/f13/set4.inf";
            restart();

        } else {

            if (st.xdbj(open.cn.awg.pro.R.id.llqHdRoot).kjd() == 0) {

                e1.tsk("提示", "请先完成当前操作");

            } else {

                showtab();
                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.wb(open.cn.awg.pro.R.id.Tab).zf("菜单");
                        st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(0);
                        st.kp(open.cn.awg.pro.R.id.kp3).kjd(8);

                    }

                });

            }

        }

    }

    private void $_onClick_i3f6c9f386b(android.view.View vw) {

        int i;

    }

    private boolean $_onTouch_i32ee139ba1(android.view.View vw, android.view.MotionEvent me) {

        if (!vw.isFocused()) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(open.cn.awg.pro.R.id.qtgd1);
                    qtgd1.v.requestFocus();

                }

            });

        }
        return false;

    }

    private boolean $_onTouch_i6c4a6b953f(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private boolean $_onTouch_bd584e50cd(android.view.View vw, android.view.MotionEvent me) {
        return true;

    }

    private void $_onClick_i9068f5d66e(android.view.View vw) {

        if (st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd() == 0) {

            st.wb(open.cn.awg.pro.R.id.Tab).zf("< X5浏览器");
            st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(8);
            st.kp(open.cn.awg.pro.R.id.kp3).kjd(0);

            java.lang.String set = "/data/user/0/open.cn.awg.pro/settings/f13/set4.inf";
            restart();

        } else {

            showtab();
            st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(0);
            st.kp(open.cn.awg.pro.R.id.kp3).kjd(8);

        }

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
        d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

        android.widget.EditText e294616700 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.llqAuSfrzUsername);
        e294616700.setOnTouchListener($_on_setOnTouchListener_e294616700);

        android.widget.EditText i812801c3fe = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.llqAuSfrzPassword);
        i812801c3fe.setOnTouchListener($_on_setOnTouchListener_i812801c3fe);

        android.widget.ImageView i16a1d50d32 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.llqAuSfrzFalse);

        android.widget.ImageView i434644d42f = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.llqAuSfrzTrue);

        android.widget.LinearLayout efa568dfe6 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj3);
        efa568dfe6.setOnTouchListener($_on_setOnTouchListener_efa568dfe6);

        android.widget.ImageView i75b44e9f85 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx11);

        android.widget.EditText i015f182c91 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.llqJsPromptBjk);
        i015f182c91.setOnTouchListener($_on_setOnTouchListener_i015f182c91);

        android.widget.ImageView i442dd3f223 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.llqJsPromptFalse);

        android.widget.ImageView i712f96d728 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.llqJsPromptTrue);

        android.widget.LinearLayout ee3b885ad5 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj11);
        ee3b885ad5.setOnTouchListener($_on_setOnTouchListener_ee3b885ad5);

        android.widget.ImageView f1070075ac = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx12);

        android.widget.ImageView i3727f0a4c0 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.llqJsConfirmFalse);

        android.widget.ImageView i0180fbc5c7 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.llqJsConfirmTrue);

        android.widget.LinearLayout i464e6cc6fb = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj10);
        i464e6cc6fb.setOnTouchListener($_on_setOnTouchListener_i464e6cc6fb);

        android.widget.ImageView i7a998f9ecb = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx15);

        android.widget.ImageView eccd04b893 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.llqJsAlertTrue);

        android.widget.LinearLayout c26905d21b = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj12);
        c26905d21b.setOnTouchListener($_on_setOnTouchListener_c26905d21b);

        android.widget.ImageView i4edbdd42e9 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx18);

        android.widget.ImageView deff5c19b4 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.llqQqDkyyFalse);

        android.widget.ImageView i1203bdd8db = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.llqQqDkyyTrue);

        android.widget.LinearLayout i0726d8c523 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj13);
        i0726d8c523.setOnTouchListener($_on_setOnTouchListener_i0726d8c523);

        android.widget.ImageView e75270b207 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx20);

        android.widget.RelativeLayout i701ef6a186 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.llqHdRoot);
        i701ef6a186.setOnTouchListener($_on_setOnTouchListener_i701ef6a186);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);

        android.widget.TextView ff56a8e7f6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb1);
        ff56a8e7f6.setOnClickListener($_on_setOnClickListener_ff56a8e7f6);

        android.widget.LinearLayout i3f6c9f386b = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj8);
        i3f6c9f386b.setOnClickListener($_on_setOnClickListener_i3f6c9f386b);

        android.widget.EditText i32ee139ba1 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjk1);
        i32ee139ba1.setOnTouchListener($_on_setOnTouchListener_i32ee139ba1);

        android.widget.Button i171ea34f1a = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an1);

        android.widget.Button i0b66a667b1 = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an2);

        android.widget.Button i74ce6319f3 = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an4);

        android.widget.Button i8f51a35692 = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an3);

        android.widget.ImageView i3621e3f4a1 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx7);

        android.widget.ImageView i4a4248a0f2 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx5);

        android.widget.ImageView i6f2c7751cf = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx6);

        android.widget.LinearLayout i6c4a6b953f = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj6);
        i6c4a6b953f.setOnTouchListener($_on_setOnTouchListener_i6c4a6b953f);

        android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);

        android.widget.ImageView i11aeec1890 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx4);

        android.widget.LinearLayout bd584e50cd = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj5);
        bd584e50cd.setOnTouchListener($_on_setOnTouchListener_bd584e50cd);

        android.widget.LinearLayout i9068f5d66e = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj4);
        i9068f5d66e.setOnClickListener($_on_setOnClickListener_i9068f5d66e);

        __layoutIsLoaded(ay, vw);
    }
}
