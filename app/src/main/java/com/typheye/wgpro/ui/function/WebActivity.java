package com.typheye.wgpro.ui.function;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.typheye.wgpro.core.xms.JSKit;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.main.MainActivity;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.utils.tAccUtils;


import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class WebActivity extends AppCompatActivity {

    private static final String WEB_LOGIN_AUTH_URL = "https://account.typheye.cn/auth";
    /** 打开页面时只读校验网页会话的超时；超时按「存活」放行，避免弱网卡住加载。 */
    private static final long WEB_SESSION_CHECK_TIMEOUT_MS = 4000L;

    private WebView webView;
    private android.widget.ProgressBar loadProgress;
    private ValueCallback<Uri[]> mFilePathCallback; // 保存文件选择回调
    private ActivityResultLauncher<Intent> fileChooserLauncher; // 文件选择器启动器
    private OnBackPressedCallback webBackCallback;
    private boolean grantRequestForwarded;
    private boolean webLoginExchangePending;
    private boolean reusedWebSession;
    private String webLoginTargetUrl;

    private String FLAG;
    /** 网页渲染前的全屏遮罩（登录态同步、首次加载都走它） */
    private android.view.View loadingOverlay;
    /** 遮罩最短显示时长：给"登录同步/数据加载"一个稳定的观感，避免一闪而过。 */
    private static final long OVERLAY_MIN_SHOW_MS = 300L;
    private long overlayShownAt = 0L;
    private boolean overlayHidePending = false;
    /** 遮罩只在 WebActivity 刚创建时出现一次；首次收起后不再出现（后续交给 WebView 自身逻辑）。 */
    private boolean overlayConsumed = false;

    /** 加载卡死自愈：进度在 {@link #STUCK_THRESHOLD} 以下**停滞**超过 {@link #STUCK_MS} 就自动重载。 */
    private static final int STUCK_THRESHOLD = 20;
    private static final long STUCK_MS = 1500L;
    private static final int STUCK_MAX_RETRY = 2;
    private final android.os.Handler stuckHandler =
            new android.os.Handler(android.os.Looper.getMainLooper());
    private int stuckRetryCount = 0;
    private int currentProgress = 0;
    private String lastLoadUrl = "";

    private final Runnable stuckWatchdog = new Runnable() {
        @Override
        public void run() {
            if (isFinishing() || isDestroyed()) return;
            if (currentProgress >= STUCK_THRESHOLD) return;
            if (loadingOverlay == null
                    || loadingOverlay.getVisibility() != android.view.View.VISIBLE) return;
            if (stuckRetryCount >= STUCK_MAX_RETRY) return;
            stuckRetryCount++;
            // 优先 reload；还没真正开始加载（about:blank）时用原始 URL 重新发起
            String current = webView == null ? null : webView.getUrl();
            if (current == null || current.isEmpty() || "about:blank".equals(current)) {
                if (lastLoadUrl != null && !lastLoadUrl.isEmpty() && webView != null) {
                    loadWebPage(lastLoadUrl);
                }
            } else {
                webView.reload();
            }
            armStuckWatchdog();
        }
    };

    private void armStuckWatchdog() {
        stuckHandler.removeCallbacks(stuckWatchdog);
        stuckHandler.postDelayed(stuckWatchdog, STUCK_MS);
    }

    private void cancelStuckWatchdog() {
        stuckHandler.removeCallbacks(stuckWatchdog);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppUtils.useScreenCutArea(getWindow(), this);
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setContentView(R.layout.activity_web);
        AppUtils.fixScreenCutAreaWithIme(findViewById(R.id.container));

        // 初始化组件
        Toolbar toolbar = findViewById(R.id.toolbar);
        webView = findViewById(R.id.web_webview);
        loadProgress = findViewById(R.id.web_load_progress);
        loadingOverlay = findViewById(R.id.web_loading_overlay);

        // 网页正式开始加载之前先盖遮罩（登录同步、数据准备都在这个阶段）
        showLoadingOverlay();

        // 设置工具栏
        setSupportActionBar(toolbar);
        // 标题默认置空：否则会显示 Activity 的 label（R.string.app_name）；加载完成后才填标题。
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("");

        FLAG = getIntent().getStringExtra("FLAG");

        if (getIntent().getBooleanExtra("browser_login", false)) {
            tAccUtils.attachBrowserLoginHost(this);
        }

        if (getIntent().getData() != null && openGrantRequest(getIntent().getData().toString())) {
            return;
        }

        // 从 intent:// 唤起时 request_id 直接以 extra 传入：直接交给主界面处理授权，
        // 不再多走一次网页跳转（旧实现会再打开一次 app_request_login 页面，链路更长更易失败）
        String requestId = getIntent().getStringExtra("request_id");
        if (requestId != null && !requestId.trim().isEmpty()) {
            forwardGrantRequest(requestId.trim());
            return;
        }
        String url;
        // 从Intent获取URL
        url = getIntent().getStringExtra("URL");
        if (url == null || url.isEmpty()) {
            url = "file:///android_asset/wait.html";
        }

        if (Objects.equals(FLAG, "XMS_WEARABLE"))
            url = "file:///android_asset/index.html";

        // 配置WebView
        initWebView(url);

        // 返回键处理
        webBackCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, webBackCallback);
        updateBackCallback();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView(String url) {
        webView.setScrollbarFadingEnabled(true);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.getSettings().setAllowUniversalAccessFromFileURLs(true);

        // 受信任页面用原生桥通知客户端（https 页面无法通过自定义 scheme 回调 shouldOverrideUrlLoading）。
        webView.addJavascriptInterface(new WebBridge(), "wgproNative");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                try {
                    // 使用当前请求的URL
                    String currentUrl = request.getUrl().toString();

                    // 发布成功页用 wgpro://close 让内置浏览器自动关闭，效果与登录成功一致。
                    // 必须排在「打开外部应用」确认之前，否则会先弹确认框。
                    if (handleCloseSignal(currentUrl)) return true;

                    if (openGrantRequest(currentUrl)) return true;

                    // HTTP/HTTPS 链接 - 直接在WebView加载
                    if (currentUrl.startsWith("http://") || currentUrl.startsWith("https://")) {
                        loadWebPage(currentUrl);
                        return true;
                    }

                    // 非HTTP链接（外部应用跳转）- 先确认再跳转
                    showExternalAppConfirmDialog(currentUrl);
                    return true; // 拦截当前加载，等待用户确认

                } catch (Exception e) {
                    com.typheye.wgpro.ui.widget.WGProHint.showError(WebActivity.this, "无法打开链接", "无法处理链接: " + request.getUrl());
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                cancelStuckWatchdog();
                stuckRetryCount = 0;
                hideLoadingOverlay();   // 页面可以看了，收起遮罩
                if (webLoginExchangePending) {
                    android.webkit.CookieManager.getInstance().flush();
                    String target = webLoginTargetUrl;
                    webLoginExchangePending = false;
                    webLoginTargetUrl = null;
                    tAccUtils accountUtils = new tAccUtils(WebActivity.this);
                    if (!accountUtils.markWebViewSessionSynchronized()
                            || !accountUtils.hasWebViewSessionCookie()) {
                        com.typheye.wgpro.ui.widget.WGProHint.showError(WebActivity.this, "登录状态异常", "网页登录状态同步失败，请稍后重试");
                    }
                    if (target != null && !target.equals(url)) {
                        loadWebPage(target);
                        return;
                    }
                }
                if (reusedWebSession && isWebLoginPage(url)) {
                    reusedWebSession = false;
                    tAccUtils accountUtils = new tAccUtils(WebActivity.this);
                    accountUtils.clearWebSessionMarkerForReauth();
                    startV2WebLogin(accountUtils, webLoginTargetUrl == null ? url : webLoginTargetUrl);
                    return;
                }
                // 发布成功页：由服务端带客户端请求头时注入 wgpro://close 触发关窗，
                // 客户端不再为具体页面做 URL 识别适配。
                updateBackCallback();
                String title = view.getTitle();
                if (title == null || title.isEmpty()) {
                    Objects.requireNonNull(getSupportActionBar()).setTitle(Uri.parse(url).getHost());
                } else {
                    Objects.requireNonNull(getSupportActionBar()).setTitle(title);
                }

                if (Objects.equals(FLAG, "XMS_WEARABLE")) {
                    // ✅ 在这里获取系统主题并通知前端
                    boolean isDarkMode = (getResources().getConfiguration().uiMode
                            & Configuration.UI_MODE_NIGHT_MASK)
                            == Configuration.UI_MODE_NIGHT_YES;
                    String jsCode = String.format(
                            "javascript:window.dispatchEvent(" +
                                    "  new CustomEvent('systemThemeChange', { detail: { dark: %b } })" +
                                    ");",
                            isDarkMode
                    );
                    // 根据 API 版本选择执行 JS 的方式
                    view.evaluateJavascript(jsCode, null);
                }
            }
        });


        // 设置WebChromeClient处理弹窗和进度
        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                currentProgress = newProgress;
                if (newProgress >= STUCK_THRESHOLD) {
                    cancelStuckWatchdog();      // 已经加载起来，不再自愈
                } else if (loadingOverlay != null
                        && loadingOverlay.getVisibility() == android.view.View.VISIBLE
                        && stuckRetryCount < STUCK_MAX_RETRY) {
                    armStuckWatchdog();         // 低于阈值时每次进度变化都重新计时（停滞才触发）
                }
                loadProgress.setProgress(newProgress, false);
                loadProgress.setVisibility(newProgress >= 100 ? View.GONE : View.VISIBLE);
                if (newProgress == 100) {
                    // 加载完成，设置标题
                    String title = view.getTitle();
                    if (title == null || title.isEmpty()) {
                        Objects.requireNonNull(getSupportActionBar()).setTitle(Uri.parse(view.getUrl()).getHost());
                    } else {
                        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
                    }
                } else {
                    Objects.requireNonNull(getSupportActionBar()).setTitle("加载中 (" + newProgress + "%)");
                }

            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                showMd3Alert(message, result);
                return true;
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                showMd3Confirm(message, result);
                return true;
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                showMd3Prompt(message, defaultValue, result);
                return true;
            }

            // ✅ 关键修复：实现文件上传（使用新API）
            @Override
            public boolean onShowFileChooser(
                    WebView webView,
                    ValueCallback<Uri[]> filePathCallback,
                    WebChromeClient.FileChooserParams fileChooserParams) {

                // 保存回调
                mFilePathCallback = filePathCallback;

                // 创建意图选择文件
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*"); // 允许所有文件类型

                // 启动文件选择器
                fileChooserLauncher.launch(Intent.createChooser(intent, "选择文件"));
                return true;
            }

        });

        // ✅ 关键：添加确认弹窗的下载监听器
        webView.setDownloadListener((url1, userAgent, contentDisposition, mimeType, contentLength) -> {
            // 从URL提取文件名（安全处理）
            String fileName = getFileNameFromUrl(url1);

            // ✅ 添加用户确认弹窗
            new WGProAlertDialogBuilder(WebActivity.this)
                    .setTitle("文件下载")
                    .setMessage("是否要下载文件：\n" + fileName)
                    .setPositiveButton("下载", (dialog, which) -> {
                        // 用户确认下载
                        startDownload(url1);
                    })
                    .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                    .show();
        });

        // 初始化文件选择器启动器
        fileChooserLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        Uri uri = data != null ? data.getData() : null;
                        if (mFilePathCallback != null) {
                            mFilePathCallback.onReceiveValue(uri != null ? new Uri[]{uri} : null);
                        }
                    } else {
                        if (mFilePathCallback != null) {
                            mFilePathCallback.onReceiveValue(null);
                        }
                    }
                    mFilePathCallback = null;
                }
        );

        if (Objects.equals(FLAG, "XMS_WEARABLE")) {
            webView.addJavascriptInterface(new JSKit(), "androidlib");
            webView.setWebViewClient(new XMSLocalContentWebViewClient());
            webView.loadUrl(url);
            return;
        }

        tAccUtils accUtils = new tAccUtils(this);
        if (accUtils.isLogin()) {
            if (accUtils.isV2Session()) {
                if (!accUtils.hasWebViewSessionCookie()) {
                    // 本地还没有网页会话：走一次兑换，把登录态同步给内置浏览器。
                    accUtils.setWebViewCookies(() -> runOnUiThread(() -> {
                        if (!isFinishing() && !isDestroyed()) startV2WebLogin(accUtils, url);
                    }));
                } else {
                    // 本地已有会话：无论访问哪个页面都做一次只读校验。
                    // 会话未失效就直接用，绝不创建新会话（否则会顶掉现有登录态）。
                    accUtils.checkWebSessionAlive(WEB_SESSION_CHECK_TIMEOUT_MS,
                            alive -> runOnUiThread(() -> {
                                if (isFinishing() || isDestroyed()) return;
                                if (alive) {
                                    reusedWebSession = true;
                                    webLoginTargetUrl = url;
                                    loadWebPage(url);
                                } else {
                                    accUtils.clearWebSessionMarkerForReauth();
                                    startV2WebLogin(accUtils, url);
                                }
                            }));
                }
            } else {
                accUtils.setWebViewCookies(() -> {
                    if (!isFinishing() && !isDestroyed()) loadWebPage(url);
                });
            }
        } else {
            loadWebPage(url);
        }
    }

    private void loadWebPage(@NonNull String url) {
        lastLoadUrl = url;
        // 遮罩只在刚创建时出现，后续加载交给 WebView 自身逻辑（细进度条 + 标题）
        stuckRetryCount = 0;
        currentProgress = 0;
        java.util.Map<String, String> headers = new java.util.HashMap<>();
        headers.put("X-Typheye-Client-WebView", "1");
        headers.put("X-Typheye-Client", "webview");
        webView.loadUrl(url, headers);
    }

    /**
     * 盖上全屏遮罩（与应用详情页同一套：纯色底 + 48dp 加载圈，没有文字，盖住应用栏）。
     * <p>用途：网页**正式开始加载之前**的登录态同步、数据准备阶段。</p>
     */
    private void showLoadingOverlay() {
        if (loadingOverlay == null || overlayConsumed) return;
        if (loadingOverlay.getVisibility() != android.view.View.VISIBLE) {
            overlayShownAt = android.os.SystemClock.uptimeMillis();
            overlayHidePending = false;
            loadingOverlay.setVisibility(android.view.View.VISIBLE);
        }
    }

    /** 收起遮罩；不足 {@link #OVERLAY_MIN_SHOW_MS} 时延后收，保证不会一闪而过。 */
    private void hideLoadingOverlay() {
        if (loadingOverlay == null
                || loadingOverlay.getVisibility() != android.view.View.VISIBLE) return;
        long remain = OVERLAY_MIN_SHOW_MS
                - (android.os.SystemClock.uptimeMillis() - overlayShownAt);
        if (remain > 0) {
            if (!overlayHidePending) {
                overlayHidePending = true;
                loadingOverlay.postDelayed(() -> {
                    overlayHidePending = false;
                    hideLoadingOverlay();
                }, remain);
            }
            return;
        }
        loadingOverlay.setVisibility(android.view.View.GONE);
        overlayConsumed = true;   // 只在 WebActivity 刚创建时露一次面，之后不再出现
    }

    private boolean isWebLoginPage(String url) {
        if (url == null) return false;
        String value = url.toLowerCase(java.util.Locale.ROOT);
        return value.contains("/auth") || value.contains("/login") || value.contains("login=1");
    }

    private void startV2WebLogin(tAccUtils accUtils, String targetUrl) {
        // 同步登录状态期间只盖遮罩，不在应用栏写任何过程文案
        showLoadingOverlay();
        final String redirectPath = "/site/user/center/";
        accUtils.createWebLoginTicket(redirectPath, new tAccUtils.WebLoginTicketCallback() {
            @Override
            public void onSuccess(String ticket, String authUrl, String confirmedRedirectPath) {
                runOnUiThread(() -> {
                    if (isFinishing() || isDestroyed()) return;
                    if (!WEB_LOGIN_AUTH_URL.equals(authUrl)) {
                        onError("服务器返回了不受信任的网页登录地址");
                        return;
                    }
                    try {
                        String body = "ticket=" + URLEncoder.encode(ticket, "UTF-8")
                                + "&redirect_path=" + URLEncoder.encode(
                                        safeRedirectPath(confirmedRedirectPath), "UTF-8");
                        webLoginTargetUrl = targetUrl;
                        webLoginExchangePending = true;
                        webView.postUrl(WEB_LOGIN_AUTH_URL,
                                body.getBytes(StandardCharsets.UTF_8));
                    } catch (Exception e) {
                        onError("无法提交网页登录票据");
                    }
                });
            }

            @Override
            public void onError(String message) {
                runOnUiThread(() -> {
                    if (isFinishing() || isDestroyed()) return;
                    webLoginExchangePending = false;
                    webLoginTargetUrl = null;
                    com.typheye.wgpro.ui.widget.WGProHint.showError(WebActivity.this, "登录失败", message);
                    loadWebPage(targetUrl);
                });
            }
        });
    }

    private String safeRedirectPath(String value) {
        return value != null && value.startsWith("/") && !value.startsWith("//")
                ? value : "/site/user/center/";
    }

    // ✅ 核心：安全提取文件名
    private String getFileNameFromUrl(String url) {
        String fileName = url.substring(url.lastIndexOf('/') + 1);

        // 清理非法字符（如?、=等）
        fileName = fileName.replaceAll("[^a-zA-Z0-9._-]", "_");

        // 保留文件扩展名
        if (fileName.contains(".")) {
            return fileName;
        }

        // 无扩展名时添加默认扩展
        return fileName + ".file";
    }

    // ✅ 处理下载请求（含权限检查）
    private void startDownload(String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // 检查是否有应用可以处理此链接
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                // 没有可处理的应用，使用默认浏览器
                intent.setPackage("com.android.chrome"); // 尝试Chrome
                try {
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    // 回退到任何可用的浏览器
                    intent.setPackage(null);
                    startActivity(Intent.createChooser(intent, "选择浏览器进行下载"));
                }
            }
        } catch (Exception e) {
            com.typheye.wgpro.ui.widget.WGProHint.showError(this, "无法打开链接", "无法打开下载链接: " + e.getMessage());
            Log.e("Download", "Failed to open download link", e);
        }
    }


    private String extractRequestIdFromIntentUri(String intentUri) {
        // 正则匹配 S.request_id=后面的内容，直到分号或end
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("S\\.request_id=([^;]+)");
        java.util.regex.Matcher matcher = pattern.matcher(intentUri);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateBackCallback();
    }

    private void updateBackCallback() {
        if (webBackCallback == null || webView == null) return;
        boolean predictiveEnabled = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("predictive_back_enabled", false);
        webBackCallback.setEnabled(!predictiveEnabled || webView.canGoBack());
    }

    /**
     * 处理发布成功页的自动关窗信号（{@code wgpro://close}）。
     *
     * <p>「发布动态 / 发布资源」成功页在内置浏览器里加载时带 {@code X-Typheye-Client} 请求头，
     * 服务端据此注入跳转脚本，让客户端像登录成功那样自动关闭窗口。
     * 普通浏览器打开同一页面不会带该头，因此不会触发关窗。</p>
     *
     * @return true 表示已识别为关窗信号并处理完毕
     */
    private boolean handleCloseSignal(String rawUri) {
        if (rawUri == null || !rawUri.startsWith("wgpro://close")) return false;
        String message = null;
        try {
            message = Uri.parse(rawUri).getQueryParameter("msg");
        } catch (Exception ignored) {
        }
        com.typheye.wgpro.core.state.AppState.get().publishContentRefresh();
        finish();
        return true;
    }

    /**
     * 原生桥：发布成功等场景下，服务端在受信任页面里调用
     * {@code window.wgproNative.close('动态已发布')} 通知客户端关窗。
     *
     * <p>https 页面里 {@code location.href='wgpro://...'} 不会回调
     * {@code shouldOverrideUrlLoading}（Chromium 直接拦截未知 scheme），所以用原生桥，
     * 与内置 XMS 页面的 {@code androidlib} 是同类协议。</p>
     */
    private final class WebBridge {
        @JavascriptInterface
        public void close(final String message) {
            runOnUiThread(() -> {
                if (isFinishing() || isDestroyed()) return;
                if (!isTrustedHost(webView.getUrl())) return;
                com.typheye.wgpro.core.state.AppState.get().publishContentRefresh();
                finish();
            });
        }
    }

    /** 原生桥只对 Typheye 域名开放，避免任意网页触发关窗。 */
    private boolean isTrustedHost(String url) {
        try {
            String host = Uri.parse(url).getHost();
            return host != null && (host.equals("typheye.cn") || host.endsWith(".typheye.cn"));
        } catch (Exception e) {
            return false;
        }
    }

    private boolean openGrantRequest(String rawUri) {
        try {
            String requestId = null;
            String scheme = Uri.parse(rawUri).getScheme();
            if (rawUri.startsWith("intent:")) {
                Intent parsed = Intent.parseUri(rawUri, Intent.URI_INTENT_SCHEME);
                Uri data = parsed.getData();
                scheme = data == null ? parsed.getScheme() : data.getScheme();
                requestId = parsed.getStringExtra("request_id");
                if ((requestId == null || requestId.isEmpty()) && data != null) {
                    requestId = data.getQueryParameter("request_id");
                }
            } else if ("wgproGrant".equalsIgnoreCase(scheme)) {
                Uri data = Uri.parse(rawUri);
                requestId = data.getQueryParameter("request_id");
            }
            if (!"wgproGrant".equalsIgnoreCase(scheme)) return false;
            if (requestId == null || requestId.isEmpty()) {
                requestId = extractRequestIdFromIntentUri(rawUri);
            }
            if (requestId == null || requestId.trim().isEmpty()) {
                com.typheye.wgpro.ui.widget.WGProHint.showError(this, "登录请求异常", "登录请求缺少 request_id");
                return true;
            }
            if (grantRequestForwarded) return true;
            forwardGrantRequest(requestId.trim());
            return true;
        } catch (Exception e) {
            Log.e("WebActivity", "Unable to parse grant URI", e);
            com.typheye.wgpro.ui.widget.WGProHint.showError(this, "登录请求异常", "无法识别登录授权请求");
            return rawUri.startsWith("intent:") || rawUri.startsWith("wgproGrant:");
        }
    }

    /** 把扫码授权请求交给主界面处理（MainActivity 会弹出授权底部弹窗）。 */
    private void forwardGrantRequest(String requestId) {
        if (grantRequestForwarded) {
            finish();
            return;
        }
        grantRequestForwarded = true;
        Intent grantIntent = new Intent(this, MainActivity.class);
        grantIntent.putExtra(MainActivity.EXTRA_LOGIN_GRANT_REQUEST_ID, requestId);
        grantIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(grantIntent);
        finish();
    }

    private void showExternalAppConfirmDialog(String url) {
        new WGProAlertDialogBuilder(this)
                .setTitle("打开外部应用")
                .setMessage("即将跳转到其他应用，是否继续？")
                .setPositiveButton("继续", (dialog, which) -> {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        com.typheye.wgpro.ui.widget.WGProHint.showError(this, "无法打开链接", "未找到可打开的应用");
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

    private void showMd3Alert(String message, JsResult result) {
        new WGProAlertDialogBuilder(this)
                .setTitle("提示")
                .setMessage(message)
                .setPositiveButton("确定", (dialog, which) -> result.confirm())
                .show();
    }

    private void showMd3Confirm(String message, JsResult result) {
        new WGProAlertDialogBuilder(this)
                .setTitle("确认")
                .setMessage(message)
                .setPositiveButton("确定", (dialog, which) -> result.confirm())
                .setNegativeButton("取消", (dialog, which) -> result.cancel())
                .show();
    }

    private void showMd3Prompt(String message, String defaultValue, JsPromptResult result) {
        View view = View.inflate(this, R.layout.dialog_edittext, null);
        TextInputLayout textInputLayout = view.findViewById(R.id.textInputLayout);
        TextInputEditText editText = view.findViewById(R.id.editText);

        // 设置提示和默认值
        textInputLayout.setHint(message);
        editText.setText(defaultValue);
        editText.setSelection(Objects.requireNonNull(editText.getText()).length());
        new WGProAlertDialogBuilder(this)
                .setTitle("输入")
                .setView(view)
                .setPositiveButton("确定", (dialog, which) -> {
                    String value = editText.getText().toString();
                    result.confirm(value);
                })
                .setNegativeButton("取消", (dialog, which) -> result.cancel() )
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // 返回按钮被点击
            if (webView.canGoBack()) {
                webView.goBack();
            } else {
                finish();
            }
            return true;
        } else if (id == R.id.action_more) {
            showMoreMenu();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showMoreMenu() {
        if (Objects.equals(FLAG, "XMS_WEARABLE"))
            new WGProAlertDialogBuilder(this)
                    .setTitle("更多")
                    .setItems(new CharSequence[]{
                            "刷新",
                            "退出"
                    }, (dialog, which) -> {
                        switch (which) {
                            case 0: // 刷新
                                webView.reload();
                                break;
                            case 1: // 退出
                                finish();
                                break;
                        }
                    })
                    .show();
        else
            new WGProAlertDialogBuilder(this)
                    .setTitle("更多")
                    .setItems(new CharSequence[]{
                            "刷新",
                            "复制链接",
                            "使用系统浏览器打开",
                            "退出"
                    }, (dialog, which) -> {
                        switch (which) {
                            case 0: // 刷新
                                webView.reload();
                                break;
                            case 1: // 复制链接
                                copyToClipboard(webView.getUrl());
                                break;
                            case 2: // 使用系统浏览器打开
                                openInBrowser();
                                break;
                            case 3: // 退出
                                finish();
                                break;
                        }
                    })
                    .show();
    }

    private void copyToClipboard(String url) {
        if (url != null) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("URL", url);
            clipboard.setPrimaryClip(clip);
            com.typheye.wgpro.ui.widget.WGProHint.show(this, "链接已复制到剪贴板");
        }
    }

    private void openInBrowser() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webView.getUrl()));
        // ✅ 移除 setClassName，让系统选择默认浏览器
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        cancelStuckWatchdog();
        tAccUtils.detachBrowserLoginHost(this);
        if (loadProgress != null) loadProgress.setVisibility(View.GONE);
        if (webView != null)
            webView.loadUrl("about:blank");

        super.onDestroy();
    }

    // 自定义 WebViewClient
    private class XMSLocalContentWebViewClient extends WebViewClient {

        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            String url = request.getUrl().toString();
            if (url.startsWith("file:///")) {
                String assetPath = url.substring(8); // 移除 "file:///"
                try {
                    InputStream inputStream = getAssets().open(assetPath);
                    String mimeType = getMimeType(assetPath);
                    return new WebResourceResponse(mimeType, "UTF-8", inputStream);
                } catch (IOException e) {
                    //e.printStackTrace();
                    return null;
                }
            }
            return super.shouldInterceptRequest(view, request);
        }

        private String getMimeType(String url) {
            if (url.endsWith(".html")) return "text/html";
            else if (url.endsWith(".js")) return "application/javascript";
            else if (url.endsWith(".css")) return "text/css";
            else if (url.endsWith(".png")) return "image/png";
            else if (url.endsWith(".jpg") || url.endsWith(".jpeg")) return "image/jpeg";
            else if (url.endsWith(".gif")) return "image/gif";
            else if (url.endsWith(".svg")) return "image/svg+xml";
            else if (url.endsWith(".woff")) return "font/woff";
            else if (url.endsWith(".woff2")) return "font/woff2";
            else if (url.endsWith(".ttf")) return "font/ttf";
            else return "text/plain";
        }
    }

}
