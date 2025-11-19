package com.typheye.wgpro.ui.function;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.typheye.wgpro.ui.function.account.AccMangerActivity;
import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.AppUtils;

import android.view.inputmethod.InputMethodManager;

import java.util.Objects;

public class WebActivity extends AppCompatActivity {

    private WebView webView;
    private ValueCallback<Uri[]> mFilePathCallback; // 保存文件选择回调
    private ActivityResultLauncher<Intent> fileChooserLauncher; // 文件选择器启动器
    private DownloadManager downloadManager;
    private ActivityResultLauncher<String> requestPermissionLauncher;

    // 用于存储待下载的文件信息（权限请求后使用）
    private String pendingDownloadUrl;
    private String pendingDownloadMimeType;


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

        // 保存当前URL

        // 配置WebView
        initWebView(url);

        // 返回键处理
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    webView.loadUrl("about:blank");
                    finish();
                }
            }
        });
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView(String url) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                try {
                    // ✅ 修复：使用当前请求的URL，不是初始URL
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

                    // ✅ 修复：使用当前请求的URL加载
                    if (Objects.requireNonNull(request.getUrl().getScheme()).startsWith("http")) {
                        view.loadUrl(currentUrl); // ✅ 关键修复
                        return true;
                    }

                    // 处理其他协议
                    Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
                    startActivity(intent);
                    return true;
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
                        startDownload(url1, mimeType);
                    })
                    .setNegativeButton("取消", (dialog, which) -> dialog.dismiss())
                    .show();
        });

        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);

        // ✅ 权限请求处理
        requestPermissionLauncher = registerForActivityResult(
                new ActivityResultContracts.RequestPermission(),
                isGranted -> {
                    if (isGranted && pendingDownloadUrl != null) {
                        // 权限已授予，执行下载
                        handleDownload(pendingDownloadUrl, pendingDownloadMimeType);
                        // 清除待下载状态
                        pendingDownloadUrl = null;
                        pendingDownloadMimeType = null;
                    } else {
                        Toast.makeText(WebActivity.this, "需要存储权限才能下载", Toast.LENGTH_SHORT).show();
                    }
                }
        );

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

        // 加载URL
        webView.loadUrl(url);
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
    private void startDownload(String url, String mimeType) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // API 33+ 需要权限
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                // 保存待下载信息
                pendingDownloadUrl = url;
                pendingDownloadMimeType = mimeType;
                // 请求权限
                requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                return;
            }
        }

        Toast.makeText(WebActivity.this, "文件已开始下载，打开通知栏查看下载进度", Toast.LENGTH_SHORT).show();
        // 权限已授予，直接下载
        handleDownload(url, mimeType);
    }

    // ✅ 执行实际下载
    private void handleDownload(String url, String mimeType) {
        // 从URL提取安全文件名
        String fileName = getFileNameFromUrl(url);
        Log.d("Download", "开始下载: " + fileName);

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setMimeType(mimeType);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle("正在下载: " + fileName);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

        // 启动下载
        downloadManager.enqueue(request);
        Toast.makeText(this, "下载已开始", Toast.LENGTH_SHORT).show();
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

}