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

import java.util.Locale;

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
    public AppRuntimeBridge e1;
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
        e1 = new AppRuntimeBridge(this);
        setContentView(R.layout.x5_browser);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        String ul = X5BrowserActivity.this.getIntent().getStringExtra(String.valueOf("url"));
        ul = open.cn.awg.pro.core.TextValues.trim(ul);

        if (!java.util.Objects.equals(ul, null) && !java.util.Objects.equals(ul, "")) {

            Url = ul;

        } else {

            Intent intent = getIntent();
            String action = intent.getAction();
            if (Intent.ACTION_VIEW.equals(action)) {
                Uri uri = intent.getData();
                if (uri != null) {
                    hqdz = uri.getScheme() + "://" + uri.getHost();
                    if (uri.getPort() != -1) hqdz = hqdz + ":" + uri.getPort();
                    if (uri.getPath() != null) hqdz = hqdz + uri.getPath();
                    if (uri.getQuery() != null) hqdz = hqdz + "?" + uri.getQuery();
                }
            }

            if (!java.util.Objects.equals(hqdz, null) && !java.util.Objects.equals(hqdz, "")) {

                Url = hqdz;

            }

        }

        if (open.cn.awg.pro.core.NativeFiles.exists(X5BrowserActivity.this, (String) (AppPaths.appPath("app_tbs/core_share/tbs.conf"))) || open.cn.awg.pro.core.NativeFiles.exists(X5BrowserActivity.this, (String) (AppPaths.appPath("app_tbs_64/core_share/tbs.conf")))) {

            try {

                csh();

            } catch (Throwable e) {

                e1.tsk("提示", "出现异常\n" + e);

            }

        } else {

            open.cn.awg.pro.core.NativeActions.launch(X5BrowserActivity.this, (Class<?>) (X5CoreInstallActivity.class));
            e1.tsk("提示", "未找到腾讯X5内核,无法启动X5浏览器\n将为您跳转到内核安装\nPS:轻触顶部标题栏关闭提示");
            open.cn.awg.pro.core.NativeActions.finish(X5BrowserActivity.this);

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        

        applyStandardScreenMode(R.id.x5_browser_root, R.id.xdbj1, R.id.title_bar);
        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(X5BrowserActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(X5BrowserActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(X5BrowserActivity.this, (String) (a2));

        final int u = Integer.parseInt(open.cn.awg.pro.core.NativeFiles.readText(X5BrowserActivity.this, (String) (AppPaths.appPath("settings/f17/set1.inf"))));
        restart();

        if (java.util.Objects.equals(a, "1")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.xxbj9)), u, 0, u, 50);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.xdbj3)), u, 0, u, 50);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.title_bar));
            // 应用栏接管标题排版，移除旧运行时 padding
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "2")) {

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (open.cn.awg.pro.core.NativeFiles.exists(X5BrowserActivity.this, (String) (a001)) || java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(X5BrowserActivity.this, (String) (a001)), "true")) {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.title_bar));
                // 应用栏接管标题排版，移除旧运行时 padding
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            } else {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.title_bar));
                // 应用栏接管标题排版，移除旧运行时 padding
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            }
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.xxbj9)), 0, 0, 0, 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.xdbj3)), 0, 0, 0, 0);

        } else if (java.util.Objects.equals(a, "3")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.xxbj9)), u, 0, u, 50);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.xdbj3)), u, 0, u, 50);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.title_bar));
            // 应用栏接管标题排版，移除旧运行时 padding
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "4")) {

            int uih = open.cn.awg.pro.core.DisplayValues.pxToDp(X5BrowserActivity.this, open.cn.awg.pro.core.DisplayValues.systemBarHeight(X5BrowserActivity.this, "status_bar_height"));
            uih = uih + 9;

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.title_bar));
            // 应用栏接管标题排版，移除旧运行时 padding
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.xxbj9)), 0, 0, 0, 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.xdbj3)), 0, 0, 0, 0);

        }

    }

    public void llqhdyc() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.llqHdRoot)).setVisibility(android.view.View.GONE);
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.llqAuSfrz)).setVisibility(android.view.View.GONE);
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.llqJsAlert)).setVisibility(android.view.View.GONE);
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.llqJsConfirm)).setVisibility(android.view.View.GONE);
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.llqJsPrompt)).setVisibility(android.view.View.GONE);
                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.llqQqDkyy)).setVisibility(android.view.View.GONE);

            }

        });

    }

    public void csh() {

        final String set1 = AppPaths.appPath("settings/f13/set1.inf");

        final String set2 = AppPaths.appPath("settings/f13/set2.inf");

        final String set3 = AppPaths.appPath("settings/f13/set3.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(X5BrowserActivity.this, (String) (set1)), "true")) {

            int pk;
            pk = X5WebViewCompat.initializeSdk(lei);

            if (pk != 1) {

                e1.tsk("提示", "X5浏览器内核不完整!\n请等待内核下载完成后,再次打开X5浏览器\nPS:轻触顶部标题栏关闭提示");
                open.cn.awg.pro.core.NativeActions.finish(X5BrowserActivity.this);

            }

        }

        if (java.util.Objects.equals(Url, null) || java.util.Objects.equals(Url, "") || java.util.Objects.equals(Url, "null")) {

            Url = open.cn.awg.pro.core.NativeFiles.readText(X5BrowserActivity.this, (String) (set2));
            Url = e1.urlUnlockString(Url);

        }

        final android.widget.LinearLayout xxbj = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.browser_container));

        final android.widget.TextView bt = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.title_bar));

        final android.widget.EditText bjk1 = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.bjk1));

        final android.widget.LinearLayout xxbj8 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.xxbj8));

        final android.widget.LinearLayout xxbj4 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.xxbj4));

        final androidx.cardview.widget.CardView kp3 = ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.kp3));

        final android.widget.ProgressBar jdt1 = ((android.widget.ProgressBar) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.jdt1));

        final androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.qtgd1));

        final androidx.core.widget.NestedScrollView qtgd2 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.qtgd2));

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
        final X5WebViewCompat x5llq = new X5WebViewCompat(lei);
        xxbj.addView(x5llq.configure(-1, -1, Url).getWebView());
        x5llq.getWebView().setFocusable(true);
        x5llq.getWebView().setFocusableInTouchMode(true);
        x5llq.getWebView().requestFocus();
        x5llq.getWebView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                WebView.HitTestResult htr = x5llq.getWebView().getHitTestResult();
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
        x5llq.getWebView().setWebChromeClient(new WebChromeClient() {

            private View myVideoView = null;
            private IX5WebChromeClient.CustomViewCallback callback = null;

            public void onShowCustomView(View view, IX5WebChromeClient.CustomViewCallback customViewCallback) {
                ViewGroup viewGroup = (ViewGroup) x5llq.getWebView().getParent();
                viewGroup.removeView(x5llq.getWebView());
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
                    viewGroup.addView(x5llq.getWebView());
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
                    jdt1.setVisibility(android.view.View.VISIBLE);
                    jdt1.setProgress(open.cn.awg.pro.core.TextValues.intValue(np, -1));
                } else {
                    jdt1.setProgress(open.cn.awg.pro.core.TextValues.intValue(100, -1));
                    jdt1.setVisibility(android.view.View.GONE);
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
                        x5llq.getWebView().requestFocus();
                    }
                });
                qtgd2.requestFocus();
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
                        x5llq.getWebView().requestFocus();
                    }
                });
                llqJsConfirmFalse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        result.cancel();
                        llqHdRoot.setVisibility(View.GONE);
                        llqJsConfirm.setVisibility(View.GONE);
                        x5llq.getWebView().requestFocus();
                    }
                });
                qtgd2.requestFocus();
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
                        x5llq.getWebView().requestFocus();
                    }
                });
                llqJsPromptFalse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        result.cancel();
                        llqHdRoot.setVisibility(View.GONE);
                        llqJsConfirm.setVisibility(View.GONE);
                        x5llq.getWebView().requestFocus();
                    }
                });
                qtgd2.requestFocus();
                return true;
            }

            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                }
            }

        });

        x5llq.getWebView().setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                String urlX = url.toLowerCase(Locale.ROOT);
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
                            x5llq.getWebView().requestFocus();
                        }
                    });
                    llqQqDkyyFalse.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            llqHdRoot.setVisibility(View.GONE);
                            llqQqDkyy.setVisibility(View.GONE);
                            x5llq.getWebView().requestFocus();
                        }
                    });
                    qtgd2.requestFocus();
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
                        x5llq.getWebView().requestFocus();
                    }
                });
                llqAuSfrzFalse.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        handler.cancel();
                        llqHdRoot.setVisibility(View.GONE);
                        llqAuSfrz.setVisibility(View.GONE);
                        x5llq.getWebView().requestFocus();
                    }
                });
                qtgd2.requestFocus();
            }
        });

        an1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bj = bjk1.getText().toString();
                bj = open.cn.awg.pro.core.TextValues.trim(bj);
                if (bj == null || bj.length() == 0) {
                    e1.tsk("提示", "编辑框不能为空");
                } else {
                    llqhdyc();
                    x5llq.loadUrl(bj);
                    kp3.setVisibility(android.view.View.VISIBLE);
                    xxbj8.setVisibility(android.view.View.GONE);
                    restart();
                    isTip = false;
                    bt.setText(String.valueOf("X5浏览器"));
                }
            }
        });

        an2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llqhdyc();
                x5llq.loadUrl("http://debugx5.qq.com");
                kp3.setVisibility(android.view.View.VISIBLE);
                xxbj8.setVisibility(android.view.View.GONE);
                restart();
                isTip = false;
                bt.setText(String.valueOf("X5浏览器"));
            }
        });

        an4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open.cn.awg.pro.core.NativeActions.launch(X5BrowserActivity.this, (Class<?>) (X5BrowserSettingsActivity.class));
                kp3.setVisibility(android.view.View.VISIBLE);
                xxbj8.setVisibility(android.view.View.GONE);
                restart();
                isTip = false;
                bt.setText(String.valueOf("X5浏览器"));
            }
        });

        an3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llqhdyc();
                x5llq.loadUrl("about:blank");
                open.cn.awg.pro.core.NativeActions.finish(X5BrowserActivity.this);
            }
        });

        tx5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (xxbj4.getVisibility() == android.view.View.VISIBLE) {
                    xxbj4.setVisibility(android.view.View.GONE);
                    kp3.setVisibility(android.view.View.VISIBLE);
                    restart();
                } else {
                    xxbj4.setVisibility(android.view.View.VISIBLE);
                    kp3.setVisibility(android.view.View.GONE);
                }
                boolean pds = x5llq.getWebView().canGoBack();
                isTip = false;
                if (pds) {
                    llqhdyc();
                    x5llq.getWebView().goBackOrForward(-1);
                } else {
                    e1.tsk("提示", "没有可后退的网页");
                }
                bt.setText(String.valueOf("X5浏览器"));
                x5llq.getWebView().requestFocus();
            }
        });

        tx6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (xxbj4.getVisibility() == android.view.View.VISIBLE) {
                    xxbj4.setVisibility(android.view.View.GONE);
                    kp3.setVisibility(android.view.View.VISIBLE);
                    restart();
                } else {
                    xxbj4.setVisibility(android.view.View.VISIBLE);
                    kp3.setVisibility(android.view.View.GONE);
                }
                boolean pds = x5llq.getWebView().canGoForward();
                isTip = false;
                if (pds) {
                    llqhdyc();
                    x5llq.getWebView().goBackOrForward(1);
                } else {
                    e1.tsk("提示", "没有可前进的网页");
                }
                bt.setText(String.valueOf("X5浏览器"));
                x5llq.getWebView().requestFocus();
            }
        });

        tx3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (xxbj4.getVisibility() == android.view.View.VISIBLE) {
                    xxbj4.setVisibility(android.view.View.GONE);
                    kp3.setVisibility(android.view.View.VISIBLE);
                    restart();
                } else {
                    xxbj4.setVisibility(android.view.View.VISIBLE);
                    kp3.setVisibility(android.view.View.GONE);
                }
                isTip = false;
                String url = x5llq.getWebView().getUrl();
                llqhdyc();
                x5llq.loadUrl(url);
                bt.setText(String.valueOf("X5浏览器"));
                x5llq.getWebView().requestFocus();
            }
        });

        tx4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xxbj4.setVisibility(android.view.View.GONE);
                kp3.setVisibility(android.view.View.GONE);
                xxbj8.setVisibility(android.view.View.VISIBLE);
                bt.setText(String.valueOf("更多"));
                isTip = true;
                bjk1.setText(String.valueOf(x5llq.getWebView().getUrl()));
                qtgd1.requestFocus();
            }
        });

        tx7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kp3.setVisibility(android.view.View.VISIBLE);
                xxbj8.setVisibility(android.view.View.GONE);
                restart();
                isTip = false;
                bt.setText(String.valueOf("X5浏览器"));
                x5llq.getWebView().requestFocus();
            }
        });

        wb1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (llqHdRoot.getVisibility() == View.VISIBLE) {
                    e1.tsk("提示", "请先完成当前操作");
                } else {
                    showtab();
                    xxbj4.setVisibility(android.view.View.GONE);
                    kp3.setVisibility(android.view.View.GONE);
                    xxbj8.setVisibility(android.view.View.VISIBLE);
                    bt.setText(String.valueOf("更多"));
                    isTip = true;
                    bjk1.setText(String.valueOf(x5llq.getWebView().getUrl()));
                    qtgd1.requestFocus();
                }
                return true;
            }
        });

    }

    public void restart() {

        final android.widget.LinearLayout xxbj8 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.xxbj8));

        final android.widget.LinearLayout xxbj4 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.xxbj4));

        String set = AppPaths.appPath("settings/f13/set4.inf");

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(X5BrowserActivity.this, (String) (a2));

        if (java.util.Objects.equals(a, "4")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(X5BrowserActivity.this, (String) (set)), "true") && xxbj4.getVisibility() == android.view.View.GONE && xxbj8.getVisibility() == android.view.View.GONE) {

                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.title_bar)).setVisibility(android.view.View.GONE);

            } else {

                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.title_bar)).setVisibility(android.view.View.VISIBLE);

            }

        } else {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(X5BrowserActivity.this, (String) (set)), "true") && xxbj4.getVisibility() == android.view.View.GONE && xxbj8.getVisibility() == android.view.View.GONE) {

                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.title_bar)).setVisibility(android.view.View.GONE);

            } else {

                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.title_bar)).setVisibility(android.view.View.VISIBLE);

            }

        }
        applyFullscreenWindow();

    }

    public void showtab() {

        String set = AppPaths.appPath("settings/f13/set4.inf");

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(X5BrowserActivity.this, (String) (a2));

        if (java.util.Objects.equals(a, "4")) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.title_bar)).setVisibility(android.view.View.VISIBLE);

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.title_bar)).setVisibility(android.view.View.VISIBLE);

        }
        applyFullscreenWindow();

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        String set4 = AppPaths.appPath("settings/f13/set5.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(X5BrowserActivity.this, (String) (set4)), "false")) {

            if (kc == 4) {

                open.cn.awg.pro.core.NativeActions.finish(X5BrowserActivity.this);

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

        android.widget.LinearLayout xxbj = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.browser_container));
        xxbj.removeAllViews();

    }

    public void onRestart() {
        super.onRestart();
        restart();

    }

    public void onStart() {
        super.onStart();
        restart();

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

        androidx.core.widget.NestedScrollView qtgd2 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.qtgd2));

        qtgd2.setOnGenericMotionListener(new View.OnGenericMotionListener() {
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

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "X5浏览器")) {

            open.cn.awg.pro.core.NativeActions.finish(X5BrowserActivity.this);

        }

    }

    private boolean onLlqAuSfrzUsernameTouch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd2 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.qtgd2));
                    qtgd2.requestFocus();

                }

            });

        }
        return false;

    }

    private boolean onLlqAuSfrzPasswordTouch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd2 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.qtgd2));
                    qtgd2.requestFocus();

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

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd2 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.qtgd2));
                    qtgd2.requestFocus();

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

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.xxbj4)).getVisibility() == android.view.View.VISIBLE) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(android.view.View.GONE);
                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.kp3)).setVisibility(android.view.View.VISIBLE);

                }

            });

            String set = AppPaths.appPath("settings/f13/set4.inf");
            restart();

        } else {

            if (((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.llqHdRoot)).getVisibility() == android.view.View.VISIBLE) {

                e1.tsk("提示", "请先完成当前操作");

            } else {

                showtab();
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("菜单"));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(android.view.View.VISIBLE);
                        ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.kp3)).setVisibility(android.view.View.GONE);

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

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

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

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.xxbj4)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("X5浏览器"));
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(android.view.View.GONE);
            ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.kp3)).setVisibility(android.view.View.VISIBLE);

            String set = AppPaths.appPath("settings/f13/set4.inf");
            restart();

        } else {

            showtab();
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(android.view.View.VISIBLE);
            ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(X5BrowserActivity.this.getWindow().getDecorView(), R.id.kp3)).setVisibility(android.view.View.GONE);

        }

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        EditText e294616700 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.llqAuSfrzUsername);
        e294616700.setOnTouchListener(llqAuSfrzUsernameTouchListener);

        EditText i812801c3fe = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.llqAuSfrzPassword);
        i812801c3fe.setOnTouchListener(llqAuSfrzPasswordTouchListener);

        ImageView i16a1d50d32 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.llqAuSfrzFalse);

        ImageView i434644d42f = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.llqAuSfrzTrue);

        LinearLayout efa568dfe6 = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj3);
        efa568dfe6.setOnTouchListener(xxbj3TouchListener);

        ImageView i75b44e9f85 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx11);

        EditText i015f182c91 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.llqJsPromptBjk);
        i015f182c91.setOnTouchListener(llqJsPromptBjkTouchListener);

        ImageView i442dd3f223 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.llqJsPromptFalse);

        ImageView i712f96d728 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.llqJsPromptTrue);

        LinearLayout ee3b885ad5 = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj11);
        ee3b885ad5.setOnTouchListener(xxbj11TouchListener);

        ImageView f1070075ac = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx12);

        ImageView i3727f0a4c0 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.llqJsConfirmFalse);

        ImageView i0180fbc5c7 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.llqJsConfirmTrue);

        LinearLayout i464e6cc6fb = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj10);
        i464e6cc6fb.setOnTouchListener(xxbj10TouchListener);

        ImageView i7a998f9ecb = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx15);

        ImageView eccd04b893 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.llqJsAlertTrue);

        LinearLayout c26905d21b = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj12);
        c26905d21b.setOnTouchListener(xxbj12TouchListener);

        ImageView i4edbdd42e9 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx18);

        ImageView deff5c19b4 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.llqQqDkyyFalse);

        ImageView i1203bdd8db = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.llqQqDkyyTrue);

        LinearLayout i0726d8c523 = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj13);
        i0726d8c523.setOnTouchListener(xxbj13TouchListener);

        ImageView e75270b207 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx20);

        RelativeLayout i701ef6a186 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.llqHdRoot);
        i701ef6a186.setOnTouchListener(llqHdRootTouchListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);

        TextView ff56a8e7f6 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb1);
        ff56a8e7f6.setOnClickListener(wb1ClickListener);

        LinearLayout i3f6c9f386b = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj8);
        i3f6c9f386b.setOnClickListener(xxbj8ClickListener);

        EditText i32ee139ba1 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        Button i171ea34f1a = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an1);

        Button i0b66a667b1 = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an2);

        Button i74ce6319f3 = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an4);

        Button i8f51a35692 = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an3);

        ImageView i3621e3f4a1 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx7);

        ImageView i4a4248a0f2 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx5);

        ImageView i6f2c7751cf = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx6);

        LinearLayout i6c4a6b953f = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj6);
        i6c4a6b953f.setOnTouchListener(xxbj6TouchListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);

        ImageView i11aeec1890 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx4);

        LinearLayout bd584e50cd = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj5);
        bd584e50cd.setOnTouchListener(xxbj5TouchListener);

        LinearLayout i9068f5d66e = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj4);
        i9068f5d66e.setOnClickListener(xxbj4ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
