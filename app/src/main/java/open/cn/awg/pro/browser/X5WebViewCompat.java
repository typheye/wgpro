/**
 * Owns the app's Tencent X5 WebView setup without an iApp runtime dependency.
 */
package open.cn.awg.pro.browser;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.ViewGroup;

import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

import java.util.HashMap;

public final class X5WebViewCompat {
    private static final String TAG = "X5WebViewCompat";
    private static boolean sdkConfigured;

    private final Context context;
    private WebView webView;

    public X5WebViewCompat(Activity activity) {
        if (activity == null) {
            throw new IllegalArgumentException("activity must not be null");
        }
        context = activity;
        createWebView();
    }

    public static synchronized int initializeSdk(Context context) {
        if (context.checkCallingOrSelfPermission(
                "android.permission.WRITE_EXTERNAL_STORAGE") != PackageManager.PERMISSION_GRANTED) {
            return 0;
        }
        if (sdkConfigured) {
            return 2;
        }
        sdkConfigured = true;

        HashMap<String, Object> settings = new HashMap<String, Object>();
        settings.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        settings.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
        QbSdk.initTbsSettings(settings);
        return 1;
    }

    public X5WebViewCompat configure(int width, int height, String url) {
        ensureWebView();
        int layoutWidth = width == -1 ? ViewGroup.LayoutParams.MATCH_PARENT
                : (width == -2 ? ViewGroup.LayoutParams.WRAP_CONTENT : width);
        int layoutHeight = height == -1 ? ViewGroup.LayoutParams.MATCH_PARENT
                : (height == -2 ? ViewGroup.LayoutParams.WRAP_CONTENT : height);
        webView.setLayoutParams(new ViewGroup.LayoutParams(layoutWidth, layoutHeight));
        if (url != null && !url.isEmpty()) {
            loadUrl(url);
        }
        return this;
    }

    public WebView getWebView() {
        ensureWebView();
        return webView;
    }

    public void loadUrl(String url) {
        ensureWebView();
        final String targetUrl = url == null ? "" : url;
        if (android.os.Looper.myLooper() == android.os.Looper.getMainLooper()) {
            webView.loadUrl(targetUrl);
        } else {
            webView.post(() -> webView.loadUrl(targetUrl));
        }
    }

    private void createWebView() {
        try {
            webView = new WebView(context);
            initializeSettings();
        } catch (Throwable error) {
            Log.e(TAG, "Unable to create X5 WebView", error);
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                try {
                    webView = new WebView(applicationContext);
                    initializeSettings();
                } catch (Throwable fallbackError) {
                    Log.e(TAG, "Unable to create X5 WebView with application context",
                            fallbackError);
                }
            }
        }
    }

    private void initializeSettings() {
        if (webView == null) {
            return;
        }
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setAllowFileAccess(true);
        settings.setAllowContentAccess(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
    }

    private void ensureWebView() {
        if (webView == null) {
            throw new IllegalStateException("X5 WebView could not be created");
        }
    }
}