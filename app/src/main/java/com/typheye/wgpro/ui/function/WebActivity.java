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
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
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
import com.google.android.material.progressindicator.LinearProgressIndicator;
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

    private WebView webView;
    private LinearProgressIndicator loadProgress;
    private ValueCallback<Uri[]> mFilePathCallback; // 保存文件选择回调
    private ActivityResultLauncher<Intent> fileChooserLauncher; // 文件选择器启动器
    private OnBackPressedCallback webBackCallback;
    private boolean grantRequestForwarded;
    private boolean webLoginExchangePending;
    private boolean reusedWebSession;
    private String webLoginTargetUrl;

    private String FLAG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppUtils.useScreenCutArea(getWindow(), this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        AppUtils.fixScreenCutArea(findViewById(R.id.container));

        // 初始化组件
        Toolbar toolbar = findViewById(R.id.toolbar);
        webView = findViewById(R.id.web_webview);
        loadProgress = findViewById(R.id.web_load_progress);

        // 设置工具栏
        setSupportActionBar(toolbar);
        //Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        FLAG = getIntent().getStringExtra("FLAG");

        if (getIntent().getData() != null && openGrantRequest(getIntent().getData().toString())) {
            return;
        }

        // 修复点1：根据request_id构造业务URL（关键修改）
        String requestId = getIntent().getStringExtra("request_id");
        String url;
        if (requestId != null && !requestId.isEmpty()) {
            url = "https://service.typheye.cn/api.php?type=app_request_login&request_id=" + requestId;
        } else {
            // 从Intent获取URL
            url = getIntent().getStringExtra("URL");
            if (url == null || url.isEmpty()) {
                url = "file:///android_asset/wait.html";
            }
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

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                try {
                    // 使用当前请求的URL
                    String currentUrl = request.getUrl().toString();

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
                    Toast.makeText(WebActivity.this, "无法处理链接: " + request.getUrl(), Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (webLoginExchangePending) {
                    android.webkit.CookieManager.getInstance().flush();
                    String target = webLoginTargetUrl;
                    webLoginExchangePending = false;
                    webLoginTargetUrl = null;
                    tAccUtils accountUtils = new tAccUtils(WebActivity.this);
                    if (!accountUtils.markWebViewSessionSynchronized()
                            || !accountUtils.hasWebViewSessionCookie()) {
                        Toast.makeText(WebActivity.this,
                                "网页登录状态同步失败，请稍后重试", Toast.LENGTH_LONG).show();
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
                loadProgress.setProgressCompat(newProgress, newProgress > 0);
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
                    Objects.requireNonNull(getSupportActionBar()).setTitle("正在加载..." + newProgress + "%");
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
                if (accUtils.hasWebViewSessionCookie()) {
                    reusedWebSession = true;
                    webLoginTargetUrl = url;
                    loadWebPage(url);
                } else {
                    accUtils.setWebViewCookies(() -> runOnUiThread(() -> {
                        if (!isFinishing() && !isDestroyed()) startV2WebLogin(accUtils, url);
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
        java.util.Map<String, String> headers = new java.util.HashMap<>();
        headers.put("X-Typheye-Client-WebView", "1");
        headers.put("X-Typheye-Client", "webview");
        webView.loadUrl(url, headers);
    }

    private boolean isWebLoginPage(String url) {
        if (url == null) return false;
        String value = url.toLowerCase(java.util.Locale.ROOT);
        return value.contains("/auth") || value.contains("/login") || value.contains("login=1");
    }

    private void startV2WebLogin(tAccUtils accUtils, String targetUrl) {
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("正在同步登录...");
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
                    Toast.makeText(WebActivity.this, message, Toast.LENGTH_LONG).show();
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
                Toast.makeText(this, "正在使用系统浏览器下载", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "无法打开下载链接: " + e.getMessage(), Toast.LENGTH_SHORT).show();
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
                Toast.makeText(this, "登录请求缺少 request_id", Toast.LENGTH_SHORT).show();
                return true;
            }
            if (grantRequestForwarded) return true;
            grantRequestForwarded = true;
            Intent grantIntent = new Intent(this, MainActivity.class);
            grantIntent.putExtra(MainActivity.EXTRA_LOGIN_GRANT_REQUEST_ID, requestId.trim());
            grantIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(grantIntent);
            finish();
            return true;
        } catch (Exception e) {
            Log.e("WebActivity", "Unable to parse grant URI", e);
            Toast.makeText(this, "无法识别登录授权请求", Toast.LENGTH_SHORT).show();
            return rawUri.startsWith("intent:") || rawUri.startsWith("wgproGrant:");
        }
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
                        Toast.makeText(this, "未找到可打开的应用", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "链接已复制到剪贴板", Toast.LENGTH_SHORT).show();
        }
    }

    private void openInBrowser() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(webView.getUrl()));
        // ✅ 移除 setClassName，让系统选择默认浏览器
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
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
