package i.app;

/**
 * iApp X5 browser compatibility wrapper.
 *
 * The real iApp SDK jar you found does not include x5, but the generated
 * project code still calls:
 *
 *     final x5 x5llq = x5.x5(lei);
 *
 * and then uses:
 *
 *     x5llq.st
 *     x5llq.llq(width, height, url)
 *     x5llq.pxzdp(...)
 *     x5llq.bt()
 *     x5llq.wz()
 *     x5llq.zrwz(url)
 *     x5llq.kht()
 *     x5llq.kqj()
 *     x5llq.kzqjht(...)
 *
 * This class intentionally lives in source, not inside iapp-sdk-core.jar.
 */
public class x5 {
    public com.tencent.smtt.sdk.WebView st;
    public android.content.Context context;
    public android.app.Activity activity;

    public x5(android.content.Context ctx) {
        bind(ctx);
    }

    public x5(android.app.Activity act) {
        bind(act);
    }

    public x5(Object ctxOrActivity) {
        if (ctxOrActivity instanceof android.app.Activity) {
            bind((android.app.Activity) ctxOrActivity);
        } else if (ctxOrActivity instanceof android.content.Context) {
            bind((android.content.Context) ctxOrActivity);
        } else {
            bind((android.content.Context) null);
        }
    }

    public static x5 x5(android.app.Activity act) {
        return new x5(act);
    }

    public static x5 x5(android.content.Context ctx) {
        return new x5(ctx);
    }

    public static x5 x5(Object ctxOrActivity) {
        return new x5(ctxOrActivity);
    }

    private void bind(android.app.Activity act) {
        this.activity = act;
        this.context = act;
        ensureWebView();
    }

    private void bind(android.content.Context ctx) {
        this.context = ctx;
        if (ctx instanceof android.app.Activity) this.activity = (android.app.Activity) ctx;
        ensureWebView();
    }

    private android.content.Context safeContext() {
        if (context != null) return context;
        if (activity != null) return activity;
        return ApplicationHolder.INSTANCE;
    }

    private void ensureWebView() {
        if (st != null) return;
        android.content.Context ctx = safeContext();
        try {
            if (ctx == null) {
                android.util.Log.e("iApp.x5", "create X5 WebView failed: no context");
                return;
            }
            st = new com.tencent.smtt.sdk.WebView(ctx);
            initSettings();
        } catch (Throwable t) {
            android.util.Log.e("iApp.x5", "create X5 WebView failed", t);
            try {
                if (ctx != null && ctx.getApplicationContext() != null) {
                    st = new com.tencent.smtt.sdk.WebView(ctx.getApplicationContext());
                    initSettings();
                }
            } catch (Throwable t2) {
                android.util.Log.e("iApp.x5", "create X5 WebView fallback failed", t2);
            }
        }
    }

    private void initSettings() {
        try {
            com.tencent.smtt.sdk.WebSettings s = st.getSettings();
            if (s != null) {
                s.setJavaScriptEnabled(true);
                s.setDomStorageEnabled(true);
                s.setDatabaseEnabled(true);
                s.setLoadWithOverviewMode(true);
                s.setUseWideViewPort(true);
                s.setSupportZoom(true);
                s.setBuiltInZoomControls(true);
                s.setDisplayZoomControls(false);
                s.setAllowFileAccess(true);
                s.setAllowContentAccess(true);
                try { s.setJavaScriptCanOpenWindowsAutomatically(true); } catch (Throwable ignored) {}
                try { s.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW); } catch (Throwable ignored) {}
                try { s.setCacheMode(com.tencent.smtt.sdk.WebSettings.LOAD_DEFAULT); } catch (Throwable ignored) {}
            }
        } catch (Throwable t) {
            android.util.Log.e("iApp.x5", "init settings failed", t);
        }
        try {
            st.setFocusable(true);
            st.setFocusableInTouchMode(true);
        } catch (Throwable ignored) {}
    }

    /**
     * iApp: create/configure browser.
     * width/height: -1 means MATCH_PARENT, -2 means WRAP_CONTENT; other values are raw px.
     */
    public x5 llq(int width, int height, String url) {
        ensureWebView();
        try {
            int w = width == -1 ? android.view.ViewGroup.LayoutParams.MATCH_PARENT :
                    (width == -2 ? android.view.ViewGroup.LayoutParams.WRAP_CONTENT : width);
            int h = height == -1 ? android.view.ViewGroup.LayoutParams.MATCH_PARENT :
                    (height == -2 ? android.view.ViewGroup.LayoutParams.WRAP_CONTENT : height);
            st.setLayoutParams(new android.view.ViewGroup.LayoutParams(w, h));
        } catch (Throwable t) {
            android.util.Log.e("iApp.x5", "set layout params failed", t);
        }
        if (url != null && url.length() > 0) zrwz(url);
        return this;
    }

    public x5 llq(Object width, Object height, Object url) {
        return llq(toInt(width, -1), toInt(height, -1), String.valueOf(url));
    }

    /**
     * pxzdp is used by the generated project as an optional X5 switch/config call.
     * Keep it no-op but chainable; do not break compilation/runtime.
     */
    public x5 pxzdp(int mode) {
        try {
            android.util.Log.d("iApp.x5", "pxzdp=" + mode);
        } catch (Throwable ignored) {}
        return this;
    }

    public x5 pxzdp(Object mode) {
        return pxzdp(toInt(mode, 0));
    }

    /** bt: current page title. */
    public String bt() {
        ensureWebView();
        try {
            String title = st.getTitle();
            return title == null ? "" : title;
        } catch (Throwable t) {
            return "";
        }
    }

    /** wz: current url. */
    public String wz() {
        ensureWebView();
        try {
            String url = st.getUrl();
            return url == null ? "" : url;
        } catch (Throwable t) {
            return "";
        }
    }

    /** zrwz: load url. */
    public void zrwz(String url) {
        ensureWebView();
        try {
            if (url == null) url = "";
            final String u = url;
            if (android.os.Looper.myLooper() == android.os.Looper.getMainLooper()) {
                st.loadUrl(u);
            } else {
                st.post(new java.lang.Runnable() {
                    @Override public void run() {
                        try { st.loadUrl(u); } catch (Throwable t) { android.util.Log.e("iApp.x5", "loadUrl failed: " + u, t); }
                    }
                });
            }
        } catch (Throwable t) {
            android.util.Log.e("iApp.x5", "zrwz failed: " + url, t);
        }
    }

    public void zrwz(Object url) {
        zrwz(String.valueOf(url));
    }

    /** kht: can go back. */
    public boolean kht() {
        ensureWebView();
        try { return st.canGoBack(); } catch (Throwable t) { return false; }
    }

    /** kqj: can go forward. */
    public boolean kqj() {
        ensureWebView();
        try { return st.canGoForward(); } catch (Throwable t) { return false; }
    }

    /**
     * kzqjht: history jump. Negative -> back; positive -> forward; 0 -> reload.
     */
    public void kzqjht(int step) {
        ensureWebView();
        try {
            final int s = step;
            java.lang.Runnable r = new java.lang.Runnable() {
                @Override public void run() {
                    try {
                        if (s < 0) {
                            int n = -s;
                            for (int i = 0; i < n && st.canGoBack(); i++) st.goBack();
                        } else if (s > 0) {
                            for (int i = 0; i < s && st.canGoForward(); i++) st.goForward();
                        } else {
                            st.reload();
                        }
                    } catch (Throwable t) {
                        android.util.Log.e("iApp.x5", "kzqjht failed step=" + s, t);
                    }
                }
            };
            if (android.os.Looper.myLooper() == android.os.Looper.getMainLooper()) r.run();
            else st.post(r);
        } catch (Throwable t) {
            android.util.Log.e("iApp.x5", "kzqjht failed step=" + step, t);
        }
    }

    public void kzqjht(Object step) {
        kzqjht(toInt(step, 0));
    }

    public void sx() {
        ensureWebView();
        try { st.reload(); } catch (Throwable ignored) {}
    }

    public void tz() {
        ensureWebView();
        try { st.stopLoading(); } catch (Throwable ignored) {}
    }

    public void qc() {
        ensureWebView();
        try { st.clearCache(true); } catch (Throwable ignored) {}
        try { st.clearHistory(); } catch (Throwable ignored) {}
    }

    public void js(String script) {
        ensureWebView();
        try {
            final String code = script == null ? "" : script;
            if (android.os.Build.VERSION.SDK_INT >= 19) {
                st.evaluateJavascript(code, null);
            } else {
                zrwz("javascript:" + code);
            }
        } catch (Throwable t) {
            android.util.Log.e("iApp.x5", "js failed", t);
        }
    }

    public void js(Object script) {
        js(String.valueOf(script));
    }

    private int toInt(Object o, int def) {
        if (o instanceof Number) return ((Number) o).intValue();
        try { return Integer.parseInt(String.valueOf(o)); } catch (Throwable t) { return def; }
    }

    /**
     * Last-resort context. Used only if generated code somehow calls x5.x5(null).
     */
    static final class ApplicationHolder extends android.app.Application {
        static final ApplicationHolder INSTANCE = new ApplicationHolder();
    }
}
