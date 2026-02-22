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

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.typheye.wgpro.core.xms.JSKit;
import com.typheye.wgpro.ui.function.account.AccMangerActivity;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.main.MainActivity;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.utils.tAccUtils;

import android.view.inputmethod.InputMethodManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class WebActivity extends AppCompatActivity {

    private WebView webView;
    private ValueCallback<Uri[]> mFilePathCallback; // 保存文件选择回调
    private ActivityResultLauncher<Intent> fileChooserLauncher; // 文件选择器启动器

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

        // 设置工具栏
        setSupportActionBar(toolbar);
        //Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        FLAG = getIntent().getStringExtra("FLAG");

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
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
            }
        });
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

                    // 处理业务链接 (intent://)
                    if (currentUrl.contains("intent://*#Intent;scheme=wgproGrant;")) {
                        String requestId = extractRequestIdFromIntentUri(currentUrl);
                        if (requestId != null && !requestId.isEmpty()) {
                            Intent intent = new Intent(WebActivity.this, AccMangerActivity.class);
                            intent.putExtra("TARGET_FRAGMENT", "grant");
                            intent.putExtra("REQUEST_ID", requestId);
                            startActivity(intent);
                            webView.loadUrl("about:blank");
                            finish();
                            return true;
                        }
                    }

                    // HTTP/HTTPS 链接 - 直接在WebView加载
                    if (currentUrl.startsWith("http://") || currentUrl.startsWith("https://")) {
                        view.loadUrl(currentUrl);
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
                // 显示加载进度
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
            new MaterialAlertDialogBuilder(WebActivity.this)
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
        }

        // 加载URL
        webView.loadUrl(url);

        tAccUtils accUtils = new tAccUtils(this);
        if (accUtils.isLogin()) accUtils.setWebViewCookies();
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

    private void showExternalAppConfirmDialog(String url) {
        new MaterialAlertDialogBuilder(this)
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
        new MaterialAlertDialogBuilder(this)
                .setTitle("提示")
                .setMessage(message)
                .setPositiveButton("确定", (dialog, which) -> result.confirm())
                .show();
    }

    private void showMd3Confirm(String message, JsResult result) {
        new MaterialAlertDialogBuilder(this)
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
        editText.requestFocus();

        // 显示键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);

        new MaterialAlertDialogBuilder(this)
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
            new MaterialAlertDialogBuilder(this)
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
            new MaterialAlertDialogBuilder(this)
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