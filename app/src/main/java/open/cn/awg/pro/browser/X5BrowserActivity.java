/**
 ******************************************************************************
 * @file    X5BrowserActivity.java
 * @author  Typheye
 * @brief   X5 browser screen.
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
package open.cn.awg.pro.browser;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.webkit.WebStorage;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import com.tencent.smtt.export.external.interfaces.HttpAuthHandler;
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;
import com.tencent.smtt.export.external.interfaces.JsPromptResult;
import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.PermissionRequest;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.ValueCallback;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.download.AppDownloadActivity;
import open.cn.awg.pro.R;

public class X5BrowserActivity extends BaseAwgActivity {

    public final X5BrowserActivity lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public String hqdz;
    public String Url;
    public boolean isTip = false;
    public Uri cameraUri;
    public PermissionRequest request2 = null;
    public int REQUEST_CAMERA = 1;
    public int REQUEST_CHOOSE = 2;
    public ValueCallback<Uri> uploadFile;
    public ValueCallback<Uri[]> uploadFiles;
    private final View.OnClickListener xxbj4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj4Click(vw);
        }

    };
    private final View.OnTouchListener xxbj5TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj5Touch(vw, me);
        }

    };
    private final View.OnTouchListener xxbj6TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj6Touch(vw, me);
        }

    };
    private final View.OnTouchListener bjk1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjk1Touch(vw, me);
        }

    };
    private final View.OnClickListener xxbj8ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj8Click(vw);
        }

    };
    private final View.OnClickListener wb1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb1Click(vw);
        }

    };
    private final View.OnTouchListener llqHdRootTouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onLlqHdRootTouch(vw, me);
        }

    };
    private final View.OnTouchListener xxbj13TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj13Touch(vw, me);
        }

    };
    private final View.OnTouchListener xxbj12TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj12Touch(vw, me);
        }

    };
    private final View.OnTouchListener xxbj10TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj10Touch(vw, me);
        }

    };
    private final View.OnTouchListener xxbj11TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj11Touch(vw, me);
        }

    };
    private final View.OnTouchListener llqJsPromptBjkTouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onLlqJsPromptBjkTouch(vw, me);
        }

    };
    private final View.OnTouchListener xxbj3TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj3Touch(vw, me);
        }

    };
    private final View.OnTouchListener llqAuSfrzPasswordTouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onLlqAuSfrzPasswordTouch(vw, me);
        }

    };
    private final View.OnTouchListener llqAuSfrzUsernameTouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onLlqAuSfrzUsernameTouch(vw, me);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.x5_browser);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        String ul = sj.hqtz("url");
        ul = zf.qctwkg(ul);

        if (!zf.dy(ul, null) && !zf.dy(ul, "")) {

            Url = ul;

        } else {

            Intent intent = getIntent();
            String action = intent.getAction();
            if (Intent.ACTION_VIEW.equals(action)) {
                Uri uri = intent.getData();
                hqdz = uri.getScheme() + "://" + uri.getHost();
                if (uri.getPort() != -1) hqdz = hqdz + ":" + uri.getPort();
                if (uri.getPath() != null) hqdz = hqdz + uri.getPath();
                if (uri.getQuery() != null) hqdz = hqdz + "?" + uri.getQuery();
            }

            if (!zf.dy(hqdz, null) && !zf.dy(hqdz, "")) {

                Url = hqdz;

            }

        }

        if (wj.cz(AppPaths.appPath("app_tbs/core_share/tbs.conf")) || wj.cz(AppPaths.appPath("app_tbs_64/core_share/tbs.conf"))) {

            try {

                csh();

            } catch (Throwable e) {

                e1.tsk("提示", "出现异常\n" + e);

            }

        } else {

            gj.tz(X5CoreInstallActivity.class);
            e1.tsk("提示", "未找到腾讯X5内核,无法启动X5浏览器\n将为您跳转到内核安装\nPS:轻触顶部标题栏关闭提示");
            gj.gb();

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        i.runlibrary.app.xt$pm pm = xt.pm();

        if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4s.inf")), "false")) {

            if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4.inf")), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb(AppPaths.appPath("settings/f17/set1.inf")));
        restart();

        if (zf.dy(a, "1")) {

            st.xxbj(R.id.xxbj9).nbj(u, 0, u, 50);
            st.xdbj(R.id.xdbj3).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "2")) {

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (wj.cz(a001) || zf.dy(wj.dqwb(a001), "true")) {

                i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
                wtab.nbj(0, "3dp", 0, "3dp");
                wtab.dqfs("center");

            } else {

                i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
                wtab.nbj(0, "9dp", 0, "9dp");
                wtab.dqfs("center");

            }
            st.xxbj(R.id.xxbj9).nbj(0, 0, 0, 0);
            st.xdbj(R.id.xdbj3).nbj(0, 0, 0, 0);

        } else if (zf.dy(a, "3")) {

            st.xxbj(R.id.xxbj9).nbj(u, 0, u, 50);
            st.xdbj(R.id.xdbj3).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "4")) {

            int uih = sj.zh().pxzdp(pm.ztl);
            uih = uih + 9;

            i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
            wtab.nbj(0, uih + "dp", 0, "9dp");
            wtab.dqfs("center");
            st.xxbj(R.id.xxbj9).nbj(0, 0, 0, 0);
            st.xdbj(R.id.xdbj3).nbj(0, 0, 0, 0);

        }

    }

    public void llqhdyc() {
        gj.jmxc(new Runnable() {

            public void run() {

                st.xdbj(R.id.llqHdRoot).kjd(8);
                st.xxbj(R.id.llqAuSfrz).kjd(8);
                st.xxbj(R.id.llqJsAlert).kjd(8);
                st.xxbj(R.id.llqJsConfirm).kjd(8);
                st.xxbj(R.id.llqJsPrompt).kjd(8);
                st.xxbj(R.id.llqQqDkyy).kjd(8);

            }

        });

    }

    public void csh() {

        final String set1 = AppPaths.appPath("settings/f13/set1.inf");

        final String set2 = AppPaths.appPath("settings/f13/set2.inf");

        final String set3 = AppPaths.appPath("settings/f13/set3.inf");

        if (zf.dy(wj.dqwb(set1), "true")) {

            int pk;
            pk = AwgProApplication.x5(lei);

            if (pk != 1) {

                e1.tsk("提示", "X5浏览器内核不完整!\n请等待内核下载完成后,再次打开X5浏览器\nPS:轻触顶部标题栏关闭提示");
                gj.gb();

            }

        }

        if (zf.dy(Url, null) || zf.dy(Url, "") || zf.dy(Url, "null")) {

            Url = wj.dqwb(set2);
            Url = e1.urlUnlockString(Url);

        }

        final i.runlibrary.app.v.xxbj xxbj = st.xxbj(R.id.browser_container);

        final i.runlibrary.app.v.wb bt = st.wb(R.id.title_bar);

        final i.runlibrary.app.v.bjk bjk1 = st.bjk(R.id.bjk1);

        final i.runlibrary.app.v.xxbj xxbj8 = st.xxbj(R.id.xxbj8);

        final i.runlibrary.app.v.xxbj xxbj4 = st.xxbj(R.id.xxbj4);

        final i.runlibrary.app.v.kp kp3 = st.kp(R.id.kp3);

        final i.runlibrary.app.v.jdt jdt1 = st.jdt(R.id.jdt1);

        final i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(R.id.qtgd1);

        final i.runlibrary.app.v.qtgd qtgd2 = st.qtgd(R.id.qtgd2);

        final String isShowTab = AppPaths.appPath("settings/f13/set4.inf");

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
                WebView.HitTestResult htr = x5llq.st.getHitTestResult();
                int type = htr.getType();
                final int IMAGE_TYPE = WebView.HitTestResult.IMAGE_TYPE;
                final int SRC_IMAGE_ANCHOR_TYPE = WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE;
                if (type == IMAGE_TYPE || type == SRC_IMAGE_ANCHOR_TYPE) {
                    String imageUrl = htr.getExtra();
                    Intent intent = new Intent(lei, AppDownloadActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("url", imageUrl);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                return true;
            }
        });
        x5llq.st.setWebChromeClient(new WebChromeClient() {

            private View myVideoView = null;
            private IX5WebChromeClient.CustomViewCallback callback = null;

            public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
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

            public boolean onShowFileChooser(WebView webView,
                                             ValueCallback<Uri[]> filePathCallback,
                                             WebChromeClient.FileChooserParams fileChooserParams) {

                uploadFiles = filePathCallback;
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.addCategory(Intent.CATEGORY_OPENABLE);
                i.setType("*/*");
                startActivityForResult(Intent.createChooser(i, "test"), 0);
                return true;
            }

            public void onProgressChanged(WebView vw, int np) {
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

        x5llq.st.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

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
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url2));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
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

            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {

                handler.proceed();
            }

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            public void onPageFinished(WebView view, String url) {
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
                gj.tz(X5BrowserSettingsActivity.class);
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

        final i.runlibrary.app.v.xxbj xxbj8 = st.xxbj(R.id.xxbj8);

        final i.runlibrary.app.v.xxbj xxbj4 = st.xxbj(R.id.xxbj4);

        String set = AppPaths.appPath("settings/f13/set4.inf");

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            if (zf.dy(wj.dqwb(set), "true") && xxbj4.kjd() == 8 && xxbj8.kjd() == 8) {

                st.wb(R.id.title_bar).kjd(8);

            } else {

                st.wb(R.id.title_bar).kjd(0);

            }

        } else {

            if (zf.dy(wj.dqwb(set), "true") && xxbj4.kjd() == 8 && xxbj8.kjd() == 8) {

                st.wb(R.id.title_bar).kjd(8);

            } else {

                st.wb(R.id.title_bar).kjd(0);

            }

        }
        applyFullscreenWindow();

    }

    public void showtab() {

        String set = AppPaths.appPath("settings/f13/set4.inf");

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            st.wb(R.id.title_bar).kjd(0);

        } else {

            st.wb(R.id.title_bar).kjd(0);

        }
        applyFullscreenWindow();

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        String set4 = AppPaths.appPath("settings/f13/set5.inf");

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

    public void onActivityResult(int re, int rc, Intent it) {
        super.onActivityResult(re, rc, it);

        if (rc == RESULT_OK) {
            switch (re) {
                case 0:
                    if (uploadFile != null) {
                        Uri result = it == null || rc != RESULT_OK ? null
                                : it.getData();
                        uploadFile.onReceiveValue(result);
                        uploadFile = null;
                    }
                    if (uploadFiles != null) {
                        Uri result = it == null || rc != RESULT_OK ? null
                                : it.getData();
                        uploadFiles.onReceiveValue(new Uri[]{result});
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
        AwgProApplication.getInstance().removeActivity(lei);

        i.runlibrary.app.v.xxbj xxbj = st.xxbj(R.id.browser_container);
        xxbj.scqb();

    }

    public void onRestart() {
        super.onRestart();
        restart();

    }

    public void onStart() {
        super.onStart();
        restart();

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

        i.runlibrary.app.v.qtgd qtgd2 = st.qtgd(R.id.qtgd2);

        qtgd2.v.setOnGenericMotionListener(new View.OnGenericMotionListener() {
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

    public void onResume() {
        super.onResume();
        restart();

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
        restart();

    }

    private void onTitleBarClick(View vw) {

        if (zf.dy(st.wb(R.id.title_bar).zf(), "< X5浏览器")) {

            gj.gb();

        }

    }

    private boolean onLlqAuSfrzUsernameTouch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            gj.jmxc(new Runnable() {

                public void run() {

                    i.runlibrary.app.v.qtgd qtgd2 = st.qtgd(R.id.qtgd2);
                    qtgd2.v.requestFocus();

                }

            });

        }
        return false;

    }

    private boolean onLlqAuSfrzPasswordTouch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            gj.jmxc(new Runnable() {

                public void run() {

                    i.runlibrary.app.v.qtgd qtgd2 = st.qtgd(R.id.qtgd2);
                    qtgd2.v.requestFocus();

                }

            });

        }
        return false;

    }

    private boolean onXxbj3Touch(View vw, MotionEvent me) {
        return true;

    }

    private boolean onLlqJsPromptBjkTouch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            gj.jmxc(new Runnable() {

                public void run() {

                    i.runlibrary.app.v.qtgd qtgd2 = st.qtgd(R.id.qtgd2);
                    qtgd2.v.requestFocus();

                }

            });

        }
        return false;

    }

    private boolean onXxbj11Touch(View vw, MotionEvent me) {
        return true;

    }

    private boolean onXxbj10Touch(View vw, MotionEvent me) {
        return true;

    }

    private boolean onXxbj12Touch(View vw, MotionEvent me) {
        return true;

    }

    private boolean onXxbj13Touch(View vw, MotionEvent me) {
        return true;

    }

    private boolean onLlqHdRootTouch(View vw, MotionEvent me) {
        return true;

    }

    private void onWb1Click(View vw) {

        if (st.xxbj(R.id.xxbj4).kjd() == 0) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.xxbj(R.id.xxbj4).kjd(8);
                    st.kp(R.id.kp3).kjd(0);

                }

            });

            String set = AppPaths.appPath("settings/f13/set4.inf");
            restart();

        } else {

            if (st.xdbj(R.id.llqHdRoot).kjd() == 0) {

                e1.tsk("提示", "请先完成当前操作");

            } else {

                showtab();
                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.title_bar).zf("菜单");
                        st.xxbj(R.id.xxbj4).kjd(0);
                        st.kp(R.id.kp3).kjd(8);

                    }

                });

            }

        }

    }

    private void onXxbj8Click(View vw) {

        int i;

    }

    private boolean onBjk1Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            gj.jmxc(new Runnable() {

                public void run() {

                    i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(R.id.qtgd1);
                    qtgd1.v.requestFocus();

                }

            });

        }
        return false;

    }

    private boolean onXxbj6Touch(View vw, MotionEvent me) {
        return true;

    }

    private boolean onXxbj5Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onXxbj4Click(View vw) {

        if (st.xxbj(R.id.xxbj4).kjd() == 0) {

            st.wb(R.id.title_bar).zf("< X5浏览器");
            st.xxbj(R.id.xxbj4).kjd(8);
            st.kp(R.id.kp3).kjd(0);

            String set = AppPaths.appPath("settings/f13/set4.inf");
            restart();

        } else {

            showtab();
            st.xxbj(R.id.xxbj4).kjd(0);
            st.kp(R.id.kp3).kjd(8);

        }

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        EditText e294616700 = (EditText) findViewById(ay, vw, R.id.llqAuSfrzUsername);
        e294616700.setOnTouchListener(llqAuSfrzUsernameTouchListener);

        EditText i812801c3fe = (EditText) findViewById(ay, vw, R.id.llqAuSfrzPassword);
        i812801c3fe.setOnTouchListener(llqAuSfrzPasswordTouchListener);

        ImageView i16a1d50d32 = (ImageView) findViewById(ay, vw, R.id.llqAuSfrzFalse);

        ImageView i434644d42f = (ImageView) findViewById(ay, vw, R.id.llqAuSfrzTrue);

        LinearLayout efa568dfe6 = (LinearLayout) findViewById(ay, vw, R.id.xxbj3);
        efa568dfe6.setOnTouchListener(xxbj3TouchListener);

        ImageView i75b44e9f85 = (ImageView) findViewById(ay, vw, R.id.tx11);

        EditText i015f182c91 = (EditText) findViewById(ay, vw, R.id.llqJsPromptBjk);
        i015f182c91.setOnTouchListener(llqJsPromptBjkTouchListener);

        ImageView i442dd3f223 = (ImageView) findViewById(ay, vw, R.id.llqJsPromptFalse);

        ImageView i712f96d728 = (ImageView) findViewById(ay, vw, R.id.llqJsPromptTrue);

        LinearLayout ee3b885ad5 = (LinearLayout) findViewById(ay, vw, R.id.xxbj11);
        ee3b885ad5.setOnTouchListener(xxbj11TouchListener);

        ImageView f1070075ac = (ImageView) findViewById(ay, vw, R.id.tx12);

        ImageView i3727f0a4c0 = (ImageView) findViewById(ay, vw, R.id.llqJsConfirmFalse);

        ImageView i0180fbc5c7 = (ImageView) findViewById(ay, vw, R.id.llqJsConfirmTrue);

        LinearLayout i464e6cc6fb = (LinearLayout) findViewById(ay, vw, R.id.xxbj10);
        i464e6cc6fb.setOnTouchListener(xxbj10TouchListener);

        ImageView i7a998f9ecb = (ImageView) findViewById(ay, vw, R.id.tx15);

        ImageView eccd04b893 = (ImageView) findViewById(ay, vw, R.id.llqJsAlertTrue);

        LinearLayout c26905d21b = (LinearLayout) findViewById(ay, vw, R.id.xxbj12);
        c26905d21b.setOnTouchListener(xxbj12TouchListener);

        ImageView i4edbdd42e9 = (ImageView) findViewById(ay, vw, R.id.tx18);

        ImageView deff5c19b4 = (ImageView) findViewById(ay, vw, R.id.llqQqDkyyFalse);

        ImageView i1203bdd8db = (ImageView) findViewById(ay, vw, R.id.llqQqDkyyTrue);

        LinearLayout i0726d8c523 = (LinearLayout) findViewById(ay, vw, R.id.xxbj13);
        i0726d8c523.setOnTouchListener(xxbj13TouchListener);

        ImageView e75270b207 = (ImageView) findViewById(ay, vw, R.id.tx20);

        RelativeLayout i701ef6a186 = (RelativeLayout) findViewById(ay, vw, R.id.llqHdRoot);
        i701ef6a186.setOnTouchListener(llqHdRootTouchListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);

        TextView ff56a8e7f6 = (TextView) findViewById(ay, vw, R.id.wb1);
        ff56a8e7f6.setOnClickListener(wb1ClickListener);

        LinearLayout i3f6c9f386b = (LinearLayout) findViewById(ay, vw, R.id.xxbj8);
        i3f6c9f386b.setOnClickListener(xxbj8ClickListener);

        EditText i32ee139ba1 = (EditText) findViewById(ay, vw, R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        Button i171ea34f1a = (Button) findViewById(ay, vw, R.id.an1);

        Button i0b66a667b1 = (Button) findViewById(ay, vw, R.id.an2);

        Button i74ce6319f3 = (Button) findViewById(ay, vw, R.id.an4);

        Button i8f51a35692 = (Button) findViewById(ay, vw, R.id.an3);

        ImageView i3621e3f4a1 = (ImageView) findViewById(ay, vw, R.id.tx7);

        ImageView i4a4248a0f2 = (ImageView) findViewById(ay, vw, R.id.tx5);

        ImageView i6f2c7751cf = (ImageView) findViewById(ay, vw, R.id.tx6);

        LinearLayout i6c4a6b953f = (LinearLayout) findViewById(ay, vw, R.id.xxbj6);
        i6c4a6b953f.setOnTouchListener(xxbj6TouchListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);

        ImageView i11aeec1890 = (ImageView) findViewById(ay, vw, R.id.tx4);

        LinearLayout bd584e50cd = (LinearLayout) findViewById(ay, vw, R.id.xxbj5);
        bd584e50cd.setOnTouchListener(xxbj5TouchListener);

        LinearLayout i9068f5d66e = (LinearLayout) findViewById(ay, vw, R.id.xxbj4);
        i9068f5d66e.setOnClickListener(xxbj4ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
