package com.typheye.wgpro.ui.function.community;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.utils.tAccUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public final class AppDetailFragment extends Fragment {
    private com.google.android.material.card.MaterialCardView actionCard;
    private View actionBar;
    private View actionFill;
    private TextView actionText;
    private android.widget.ImageView actionBlur;
    private JSONObject appJson = new JSONObject();
    private String appKey = "";
    private String downloadUrl = "";
    private boolean downloadStarted;
    private androidx.activity.result.ActivityResultLauncher<Intent> saveLauncher;
    private JSONObject pendingSaveApp;
    private String pendingSaveUrl;

    @Override
    public void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        saveLauncher = registerForActivityResult(
                new androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() != android.app.Activity.RESULT_OK
                            || result.getData() == null) return;
                    android.net.Uri uri = result.getData().getData();
                    if (uri == null || pendingSaveApp == null || pendingSaveUrl == null) return;
                    startDownload(pendingSaveApp, pendingSaveUrl,
                            com.typheye.wgpro.core.download.DownloadTask.DEST_LOCAL, uri);
                });
    }

    static AppDetailFragment newInstance(String json) {
        AppDetailFragment fragment = new AppDetailFragment(); Bundle args = new Bundle();
        args.putString("json", json == null ? "{}" : json); fragment.setArguments(args); return fragment;
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater,
                                                  @Nullable ViewGroup container, @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment_app_detail, container, false);
        BaseSectionActivity host = (BaseSectionActivity) requireActivity();
        host.showContentLoading();
        JSONObject app; try { app = new JSONObject(requireArguments().getString("json", "{}")); }
        catch (Exception ignored) { app = new JSONObject(); }
        final boolean[] hidden = {false};
        String historyKey = app.optString("id", app.optString("package", ""));
        if (!historyKey.isEmpty() && new tAccUtils(requireContext()).isLogin() && requireContext().getSharedPreferences("history_preferences", android.content.Context.MODE_PRIVATE).getBoolean("enabled", true)) {
            java.util.Map<String, String> fields = new java.util.LinkedHashMap<>();
            fields.put("target_type", "app"); fields.put("target_key", historyKey);
            new tAccUtils(requireContext()).postV2Json("history_record2", fields, new tAccUtils.JsonCallback() {
                @Override public void onSuccess(@NonNull JSONObject ignored) { }
                @Override public void onError(int code, @NonNull String message) { }
            });
        }
        long started = android.os.SystemClock.uptimeMillis();
        Runnable hide = () -> {
            long wait = Math.max(0L, 300L - (android.os.SystemClock.uptimeMillis() - started));
            root.postDelayed(() -> { if (!hidden[0] && isAdded()) { hidden[0] = true; host.hideContentLoading(); } }, wait);
        };
        bind(root, app, hide);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle state) {
        super.onViewCreated(view, state);
        com.typheye.wgpro.core.download.DownloadManager.get().tasks()
                .observe(getViewLifecycleOwner(), this::renderInstallState);
    }

    /** 底部「动作药丸」既是按钮也是进度条：文字随状态变化，主题色按进度填充。 */
    private void renderInstallState(@Nullable java.util.List<com.typheye.wgpro.core.download.DownloadTask> tasks) {
        if (actionCard == null || actionText == null || !isAdded()) return;
        com.typheye.wgpro.core.download.DownloadTask task = null;
        if (tasks != null) {
            for (com.typheye.wgpro.core.download.DownloadTask t : tasks) {
                if (t.id.equals(appKey)) { task = t; break; }
            }
        }
        final com.typheye.wgpro.core.download.DownloadManager manager =
                com.typheye.wgpro.core.download.DownloadManager.get();
        if (task == null) {
            actionText.setText("获取");
            actionCard.setEnabled(!downloadUrl.isEmpty());
            setActionFill(0);
            actionCard.setOnClickListener(v -> acquire());
            return;
        }
        final com.typheye.wgpro.core.download.DownloadTask current = task;
        switch (task.status) {
            case com.typheye.wgpro.core.download.DownloadTask.STATUS_RUNNING:
                actionCard.setEnabled(true);
                actionText.setText(task.progress() + "%");
                setActionFill(task.progress());
                actionCard.setOnClickListener(v -> manager.control(requireContext(), current.id,
                        com.typheye.wgpro.core.download.DownloadService.ACTION_PAUSE));
                break;
            case com.typheye.wgpro.core.download.DownloadTask.STATUS_QUEUED:
                actionCard.setEnabled(true);
                actionText.setText("等待中");
                setActionFill(task.progress());
                actionCard.setOnClickListener(v -> manager.control(requireContext(), current.id,
                        com.typheye.wgpro.core.download.DownloadService.ACTION_PAUSE));
                break;
            case com.typheye.wgpro.core.download.DownloadTask.STATUS_PAUSED:
                actionCard.setEnabled(true);
                actionText.setText("继续");
                setActionFill(task.progress());
                actionCard.setOnClickListener(v -> manager.control(requireContext(), current.id,
                        com.typheye.wgpro.core.download.DownloadService.ACTION_RESUME));
                break;
            case com.typheye.wgpro.core.download.DownloadTask.STATUS_FAILED:
                actionCard.setEnabled(true);
                actionText.setText("重试");
                setActionFill(0);
                actionCard.setOnClickListener(v -> manager.control(requireContext(), current.id,
                        com.typheye.wgpro.core.download.DownloadService.ACTION_RESUME));
                break;
            case com.typheye.wgpro.core.download.DownloadTask.STATUS_COMPLETED:
                actionCard.setEnabled(true);
                actionText.setText("操作");
                setActionFill(0);
                actionCard.setOnClickListener(v -> showOperationSheet(appJson, downloadUrl, current));
                break;
            default:
                break;
        }
    }

    /** 进度填充宽度 = 药丸宽度的 progress%。 */
    private void setActionFill(int progress) {
        actionCard.post(() -> {
            if (actionFill == null || actionCard == null) return;
            int width = actionCard.getWidth();
            android.view.ViewGroup.LayoutParams params = actionFill.getLayoutParams();
            int desired = Math.max(0, Math.round(width * progress / 100f));
            if (params.width != desired) {
                params.width = desired;
                actionFill.setLayoutParams(params);
            }
        });
    }

    /** 底栏：贴底 + 毛玻璃，逐字对齐动态详情页（registerBar / rebindSource / addBackdrop / insets / 内容底部留白）。 */
    private void setupActionBar(View root) {
        actionBar = root.findViewById(R.id.app_detail_action_bar);
        actionBlur = root.findViewById(R.id.app_detail_action_blur);
        final View scroll = root.findViewById(R.id.app_detail_scroll);
        com.typheye.wgpro.utils.BarBlurController.registerBar(requireContext(), actionBar);
        // 与页面容器共用同一个毛玻璃控制器：快照源换成内容容器（不含底栏），再追加底栏快照层。
        com.typheye.wgpro.utils.BarBlurController controller =
                ((BaseSectionActivity) requireActivity()).blurController();
        View source = root.findViewById(R.id.app_detail_content);
        if (controller != null) {
            controller.rebindSource(source);
            controller.addBackdrop(actionBlur);
        } else {
            com.typheye.wgpro.utils.BarBlurController.install(requireActivity(), source, actionBlur);
        }
        final int baseBottom = actionBar.getPaddingBottom();
        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(root, (view, insets) -> {
            int inset = com.typheye.wgpro.utils.SystemBars.bottomInsetForView(view, insets);
            actionBar.setPadding(actionBar.getPaddingLeft(), actionBar.getPaddingTop(),
                    actionBar.getPaddingRight(), baseBottom + inset);
            return insets;
        });
        androidx.core.view.ViewCompat.requestApplyInsets(root);
        // 内容底部留出底栏高度，末尾内容也能滚到底栏上方（内容从毛玻璃下穿过）。
        root.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            int barHeight = actionBar.getHeight();
            if (barHeight > 0 && scroll instanceof androidx.core.widget.NestedScrollView) {
                androidx.core.widget.NestedScrollView sv = (androidx.core.widget.NestedScrollView) scroll;
                if (sv.getPaddingBottom() != barHeight) {
                    sv.setClipToPadding(false);
                    sv.setPadding(sv.getPaddingLeft(), sv.getPaddingTop(),
                            sv.getPaddingRight(), barHeight);
                }
            }
        });
    }

    private void bind(View root, JSONObject app, @Nullable Runnable loaded) {
        String name = app.optString("name", "未命名应用");
        ((TextView) root.findViewById(R.id.app_detail_initial)).setText(first(name));
        TextView nameView = root.findViewById(R.id.app_detail_name);
        nameView.setText(name);
        nameView.setOnClickListener(v -> com.typheye.wgpro.ui.widget.WGProHint.showInfo(
                requireContext(), "应用名称", name));
        String author = app.optString("author", "Typheye 应用平台");
        if (author.trim().isEmpty()) author = "Typheye 应用平台";
        TextView authorView = root.findViewById(R.id.app_detail_author);
        authorView.setText(author);
        final String authorName = author;
        authorView.setOnClickListener(v -> com.typheye.wgpro.ui.widget.WGProHint.showInfo(
                requireContext(), "开发者", "此应用由“" + authorName + "”开发并发行。"));
        // 「包含应用内服务」这类标记由云端 config 控制（app-service-flag / app-service-note）
        String serviceFlag = app.optString("service_flag", "").trim();
        if (serviceFlag.isEmpty()) serviceFlag = "包含应用内服务";
        TextView serviceView = root.findViewById(R.id.app_detail_service);
        serviceView.setText(serviceFlag);
        String serviceNote = app.optString("service_note", "").trim();
        if (serviceNote.isEmpty()) {
            serviceNote = "此应用" + serviceFlag + "，相关功能由应用自身提供，具体以应用内说明为准。";
        }
        final String serviceMessage = serviceNote;
        serviceView.setOnClickListener(v -> com.typheye.wgpro.ui.widget.WGProHint.showInfo(
                requireContext(), "服务说明", serviceMessage));
        ((TextView) root.findViewById(R.id.app_detail_size)).setText(blank(app.optString("size"), "--"));
        ((TextView) root.findViewById(R.id.app_detail_downloads)).setText(formatDownloads(app.optInt("downloads")));
        ((TextView) root.findViewById(R.id.app_detail_stars)).setText(formatDownloads(app.optInt("stars")));
        bindMetaClicks(root, app);
        bindChangelog(root, app);
        bindExtraInfo(root, app);
        bindAppInfo(root, app);
        String description = cleanBr(app.optString("description", app.optString("summary", "暂无应用介绍。")));
        TextView descriptionView = root.findViewById(R.id.app_detail_description);
        descriptionView.setText(description);
        com.typheye.wgpro.utils.AppUtils.longPressToCopy(descriptionView);
        boolean vela = "velaapp".equals(app.optString("kind", ""));
        String iconUrl = app.optString("icon_url", "");
        ImageView icon = root.findViewById(R.id.app_detail_icon);
        if (vela) {
            // Vela 图标本身是圆的：放在之前的圆角卡片底（surface_secondary）上，并留一点外边距。
            icon.setBackgroundResource(R.drawable.bg_app_icon_clip);
            icon.setClipToOutline(true);
            int pad = dp(6);
            icon.setPadding(pad, pad, pad, pad);
            icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        if (!iconUrl.isEmpty()) load(iconUrl, icon, () -> {
            icon.setVisibility(View.VISIBLE); root.findViewById(R.id.app_detail_initial).setVisibility(View.GONE);
            if (loaded != null) loaded.run();
        });
        else if (loaded != null) root.postDelayed(loaded, 300L);
        actionCard = root.findViewById(R.id.app_detail_action);
        actionBar = root.findViewById(R.id.app_detail_action_bar);
        actionFill = root.findViewById(R.id.app_detail_action_fill);
        actionText = root.findViewById(R.id.app_detail_action_text);
        setupActionBar(root);
        appJson = app;
        appKey = app.optString("id", app.optString("package", ""));
        downloadUrl = app.optString("download_url", app.optString("detail_url", ""));
        actionCard.setEnabled(!downloadUrl.isEmpty());
        actionText.setText("获取");
        actionCard.setOnClickListener(v -> acquire());
        LinearLayout screenshots = root.findViewById(R.id.app_detail_screenshots);
        JSONArray urls = app.optJSONArray("screenshots");
        if (urls == null || urls.length() == 0) {
            root.findViewById(R.id.app_detail_screenshot_scroll).setVisibility(View.GONE);
        } else {
            final java.util.ArrayList<String> shotUrls = new java.util.ArrayList<>();
            for (int i = 0; i < urls.length(); i++) {
                String url = urls.optString(i);
                if (url != null && !url.trim().isEmpty()) shotUrls.add(url.trim());
            }
            for (int i = 0; i < shotUrls.size(); i++) {
                final int index = i;
                ImageView shot = new ImageView(requireContext()); shot.setScaleType(ImageView.ScaleType.CENTER_CROP);
                shot.setBackgroundResource(R.drawable.bg_app_screenshot); shot.setClipToOutline(true);
                LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(dp(178), dp(316)); p.setMarginEnd(dp(12));
                shot.setClickable(true);
                shot.setOnClickListener(v -> com.typheye.wgpro.ui.function.PreviewActivity
                        .open(requireContext(), shotUrls, index));
                screenshots.addView(shot, p); load(shotUrls.get(i), shot, null);
            }
        }
    }

    /**
     * 「获取」入口：先问服务端报价——
     * 免费或已拥有 → 直接开始下载；要扣积分 → 弹「获取」弹窗，交易成功后再下载。
     */
    private void acquire() {
        if (downloadUrl == null || downloadUrl.isEmpty()) return;
        final android.content.Context context = requireContext();
        com.typheye.wgpro.ui.widget.Points.refresh(context, null);
        com.typheye.wgpro.utils.tPointsUtils.quote(context, "app", appKey,
                new com.typheye.wgpro.utils.tPointsUtils.JsonCallback() {
                    @Override
                    public void onSuccess(@NonNull JSONObject info) {
                        int price = info.optInt("price", 0);
                        boolean owned = info.optBoolean("owned", price <= 0);
                        com.typheye.wgpro.utils.tPointsUtils.cachePrice(context, "app", appKey, price, owned);
                        if (price <= 0 || owned) {
                            startDownload(appJson, downloadUrl, 1);
                        } else {
                            showDownloadSheet(price, info.optString("stock_label", "有库存"),
                                    info.optInt("total", 0));
                        }
                    }

                    @Override
                    public void onError(int status, @NonNull String message, @Nullable JSONObject info) {
                        // 服务不可用：已知免费的条目照常放行，收费条目提示稍后再试
                        int cachedPrice = com.typheye.wgpro.utils.tPointsUtils.cachedPrice(context, "app", appKey);
                        boolean cachedOwned = com.typheye.wgpro.utils.tPointsUtils.cachedOwned(context, "app", appKey);
                        if (cachedPrice == 0 || cachedOwned) {
                            startDownload(appJson, downloadUrl, 1);
                            return;
                        }
                        if (!isAdded()) return;
                        com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(),
                                status == 401 ? "请先登录" : "获取失败",
                                status == 401 ? "登录后才能获取该应用。" : message + "，请稍后再试。");
                    }
                });
    }

    /** 「获取」弹窗：定价头部（库存 | 账户可用积分）+ 使用余额支付 / 使用兑换代码 / 找他人代付。 */
    private void showDownloadSheet(int price, String stockLabel, int availableTotal) {
        final java.util.List<String> labels = new java.util.ArrayList<>();
        labels.add("使用余额支付");
        labels.add("使用兑换代码");
        labels.add("找他人代付");
        com.typheye.wgpro.ui.widget.GetSheet.show(requireContext(), price, stockLabel, availableTotal,
                labels, (dialog, which) -> {
                    if (which == 0) {
                        confirmAndPay(price);
                    } else if (which == 1) {
                        com.typheye.wgpro.ui.widget.GetSheet.showRedeem(requireContext(),
                                points -> acquire());
                    } else {
                        com.typheye.wgpro.ui.widget.WGProHint.showInfo(requireContext(), "找他人代付",
                                "把该应用的分享链接发给好友，由对方为你获取。");
                    }
                });
    }

    /** 二次确认 → 云端下发验证码 → 输入验证码完成交易 → 开始下载。 */
    private void confirmAndPay(int price) {
        final android.content.Context context = requireContext();
        String name = appJson.optString("name", "该应用");
        com.typheye.wgpro.ui.widget.GetSheet.showConfirm(context, name, price, () ->
                com.typheye.wgpro.utils.tPointsUtils.verifySend(context, "app", appKey,
                        new com.typheye.wgpro.utils.tPointsUtils.JsonCallback() {
                            @Override
                            public void onSuccess(@NonNull JSONObject info) {
                                if (!isAdded()) return;
                                String txnNo = info.optString("txn_no", "");
                                String debugCode = info.optString("debug_code", "");
                                com.typheye.wgpro.ui.widget.GetSheet.showVerifyCode(
                                        requireContext(), txnNo,
                                        debugCode.isEmpty() ? null : debugCode,
                                        info.optInt("expires_in", 300),
                                        code -> completePurchase(txnNo, code));
                            }

                            @Override
                            public void onError(int status, @NonNull String message,
                                                @Nullable JSONObject info) {
                                if (!isAdded()) return;
                                showPayError(status, message);
                            }
                        }));
    }

    /** 凭验证码完成扣费。 */
    private void completePurchase(String txnNo, String code) {
        final android.content.Context context = requireContext();
        com.typheye.wgpro.utils.tPointsUtils.purchase(context, txnNo, code, null,
                new com.typheye.wgpro.utils.tPointsUtils.JsonCallback() {
                    @Override
                    public void onSuccess(@NonNull JSONObject info) {
                        com.typheye.wgpro.ui.widget.Points.apply(
                                info.optInt("balance", com.typheye.wgpro.ui.widget.Points.balance),
                                info.optInt("gift", com.typheye.wgpro.ui.widget.Points.gift));
                        int charged = info.optInt("charged", 0);
                        if (charged > 0) {
                            com.typheye.wgpro.ui.widget.WGProHint.show(context,
                                    "已扣除 " + charged + " 积分");
                        }
                        if (!isAdded()) return;
                        startDownload(appJson, downloadUrl, 1);
                    }

                    @Override
                    public void onError(int status, @NonNull String message, @Nullable JSONObject info) {
                        if (!isAdded()) return;
                        showPayError(status, message);
                    }
                });
    }

    /** 交易失败的统一提示。 */
    private void showPayError(int status, String message) {
        if (status == 402) {
            com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(),
                    "积分不足", message + "，可以先兑换积分再获取。");
        } else if (status == 409) {
            com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(), "无库存", message);
        } else if (status == 401) {
            com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(),
                    "请先登录", "登录后才能获取该应用。");
        } else {
            com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(), "获取失败", message);
        }
    }

    /** 「操作」：已下载/已安装后的动作（安装到手机 / 在浏览器打开 / 保存到本机 / 分享 / 清理该文件）。 */
    private void showOperationSheet(JSONObject app, String downloadUrl,
                                    com.typheye.wgpro.core.download.DownloadTask task) {
        com.typheye.wgpro.core.xms.UIParams params =
                com.typheye.wgpro.core.state.AppState.get().deviceSnapshot();
        boolean hasDevice = params.connected && params.connected_device_name != null
                && !params.connected_device_name.trim().isEmpty();
        String deviceName = hasDevice ? params.connected_device_name.trim() : "设备";
        boolean isVela = "velaapp".equals(app.optString("kind", ""));
        final java.util.List<String> labels = new java.util.ArrayList<>();
        final java.util.List<Integer> codes = new java.util.ArrayList<>();
        if (isVela && hasDevice) { labels.add("安装到" + deviceName); codes.add(0); }
        if (!isVela) { labels.add("安装到手机"); codes.add(1); }
        labels.add("在浏览器打开"); codes.add(3);
        labels.add("保存到本机"); codes.add(2);
        labels.add("分享给好友"); codes.add(4);
        labels.add("清理该文件"); codes.add(5);
        com.typheye.wgpro.ui.widget.GetSheet.showActions(requireContext(), labels, (dialog, which) -> {
            int code = codes.get(which);
            if (code == 5) {
                com.typheye.wgpro.core.download.DownloadManager.get().control(
                        requireContext(), task.id, com.typheye.wgpro.core.download.DownloadService.ACTION_REMOVE);
                deleteLocalFile(task);
            } else if (code == 4) {
                shareDownloadLink(app, downloadUrl);
            } else if (code == 3) {
                openInBrowser(downloadUrl);
            } else if (code == 2) {
                launchSavePicker(app, downloadUrl);
            } else {
                startDownload(app, downloadUrl, code);
            }
        });
    }

    /** 分享下载链接（对方点开可进详情页）。 */
    private void shareDownloadLink(JSONObject app, String downloadUrl) {
        String name = app.optString("name", "应用");
        android.content.Intent share = new android.content.Intent(android.content.Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(android.content.Intent.EXTRA_TEXT, name + "　下载地址：" + downloadUrl);
        startActivity(android.content.Intent.createChooser(share, "分享给好友"));
    }

    /** 清理本地文件（下载记录由 DownloadManager 一并删除）。 */
    private void deleteLocalFile(com.typheye.wgpro.core.download.DownloadTask task) {
        try {
            String path = task.localPath;
            if (path != null && !path.startsWith("content://")) new java.io.File(path).delete();
        } catch (Exception ignored) { }
    }

    /** 「保存到本地」：拉起系统文件保存框架，让用户自己选位置，再开始下载。 */
    private void launchSavePicker(JSONObject app, String downloadUrl) {
        pendingSaveApp = app;
        pendingSaveUrl = downloadUrl;
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT)
                .addCategory(Intent.CATEGORY_OPENABLE)
                .setType("application/vnd.android.package-archive")
                .putExtra(Intent.EXTRA_TITLE,
                        app.optString("package", app.optString("id", "app")) + ".apk");
        try {
            saveLauncher.launch(intent);
        } catch (Exception e) {
            com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(), "无法保存", "无法打开文件保存窗口");
        }
    }

    private void openInBrowser(String url) {
        if (url == null || url.isEmpty()) return;
        startActivity(new Intent(requireContext(), WebActivity.class).putExtra("URL", url));
    }

    private void startDownload(JSONObject app, String downloadUrl, int which) {
        startDownload(app, downloadUrl, which, null);
    }

    private void startDownload(JSONObject app, String downloadUrl, int which,
                               @Nullable android.net.Uri targetUri) {
        int destination = which == 0 ? com.typheye.wgpro.core.download.DownloadTask.DEST_DEVICE
                : which == 1 ? com.typheye.wgpro.core.download.DownloadTask.DEST_PHONE
                : com.typheye.wgpro.core.download.DownloadTask.DEST_LOCAL;
        String id = app.optString("id", app.optString("package", downloadUrl));
        com.typheye.wgpro.core.download.DownloadTask task =
                new com.typheye.wgpro.core.download.DownloadTask(
                        id, app.optString("name", "未命名应用"), app.optString("icon_url", ""),
                        downloadUrl, app.optString("size", ""), destination,
                        "app", app.toString());
        if (targetUri != null) task.targetUri = targetUri.toString();
        com.typheye.wgpro.core.download.DownloadManager.get().enqueue(requireContext(), task);
        downloadStarted = true;
        com.typheye.wgpro.ui.widget.WGProHint.show(requireContext(), "已加入下载");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (downloadStarted && isAdded() && !appKey.isEmpty()) {
            downloadStarted = false;
            refreshDownloadCount();
        }
    }

    /** 下载后回到详情页时重新拉一次真实下载量并刷新显示。 */
    private void refreshDownloadCount() {
        Request request;
        try {
            request = new Request.Builder().url("https://res.typheye.cn/api.php?type=app&package="
                    + android.net.Uri.encode(appKey)).build();
        } catch (Exception ignored) {
            return;
        }
        new tAccUtils(requireContext().getApplicationContext()).getClient().newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException error) { }
            @Override public void onResponse(Call call, Response response) {
                try (Response body = response) {
                    if (!body.isSuccessful() || body.body() == null) return;
                    JSONObject json = new JSONObject(body.body().string());
                    JSONObject info = json.optJSONObject("info");
                    if (info == null) return;
                    final int downloads = info.optInt("downloads", -1);
                    if (downloads < 0) return;
                    View view = getView();
                    if (view == null) return;
                    view.post(() -> {
                        View target = getView();
                        if (target != null) {
                            ((TextView) target.findViewById(R.id.app_detail_downloads))
                                    .setText(formatDownloads(downloads));
                        }
                    });
                } catch (Exception ignored) {
                }
            }
        });
    }

    private void load(String url, ImageView target, @Nullable Runnable done) {        Request request; try { request = new Request.Builder().url(url).build(); } catch (Exception ignored) { return; }
        new tAccUtils(requireContext().getApplicationContext()).getClient().newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException error) {
                if (done != null) target.post(done);
            }
            @Override public void onResponse(Call call, Response response) {
                try (Response body = response) {
                    if (!body.isSuccessful() || body.body() == null) { if (done != null) target.post(done); return; }
                    Bitmap bitmap = BitmapFactory.decodeStream(body.body().byteStream());
                    target.post(() -> {
                        if (bitmap != null) target.setImageBitmap(bitmap);
                        if (done != null) done.run();
                    });
                }
            }
        });
    }

    private String first(String value) { return value.trim().isEmpty() ? "应" : value.trim().substring(0, 1); }
    private String blank(String value, String fallback) { return value == null || value.trim().isEmpty() ? fallback : value; }
    private String formatDownloads(int value) { return value >= 10000 ? (value / 10000) + "万" : String.valueOf(value); }

    /** 把接口文本里的 &lt;br&gt; 换成换行。 */
    private static String cleanBr(String value) {
        if (value == null) return "";
        return value.replaceAll("(?i)<br\\s*/?>", "\n");
    }
    /** 星标增删后立即刷新数据条上的星标数。 */
    void refreshStars(int count) {
        View view = getView();
        if (view == null) return;
        ((TextView) view.findViewById(R.id.app_detail_stars)).setText(formatDownloads(count));
    }

    private int dp(int value) { return Math.round(value * getResources().getDisplayMetrics().density); }

    /** 数据条四格（版本/大小/下载/评分）都可点：弹出底部说明弹窗。 */
    private void bindMetaClicks(@NonNull View root, @NonNull JSONObject app) {
        String version = app.optString("version_name", "").trim();
        String versionCode = app.optString("version_code", "").trim();
        String updated = app.optString("updated_at", "").trim();
        StringBuilder versionText = new StringBuilder();
        if (!version.isEmpty()) versionText.append("当前版本 ").append(version);
        if (!versionCode.isEmpty()) {
            if (versionText.length() > 0) versionText.append("（版本号 ").append(versionCode).append("）");
            else versionText.append("版本号 ").append(versionCode);
        }
        if (!updated.isEmpty()) {
            if (versionText.length() > 0) versionText.append("，");
            versionText.append("更新于 ").append(updated);
        }
        if (versionText.length() > 0) versionText.append("。");
        else versionText.append("暂未提供版本信息。");

        String size = app.optString("size", "").trim();
        String sizeText = size.isEmpty()
                ? "暂未提供安装包大小。"
                : "安装包大小约 " + size + "，实际占用空间会因系统与运行数据略有差异。";

        int downloads = app.optInt("downloads");
        String downloadText = "累计下载 " + formatDownloads(downloads)
                + " 次，统计的是通过腕管Pro发起的下载次数。";

        int stars = app.optInt("stars");
        String starsText = "当前有 " + formatDownloads(stars) + " 位用户把此应用加入了星标"
                + "。星标后可以在「我的 - 星标应用」里快速找到。";

        tapMeta(root, R.id.app_detail_cell_size, "大小", sizeText);
        tapMeta(root, R.id.app_detail_cell_downloads, "下载", downloadText);
        tapMeta(root, R.id.app_detail_cell_stars, "星标", starsText);
    }

    private void tapMeta(@NonNull View root, int id, @NonNull String title, @NonNull String message) {
        View view = root.findViewById(id);
        if (view == null) return;
        view.setOnClickListener(v -> com.typheye.wgpro.ui.widget.WGProHint.showInfo(
                requireContext(), title, message));
    }

    /** 新特性 = 更新日志（changelog）；为空或是「-」这类占位时整块隐藏。 */
    private void bindChangelog(@NonNull View root, @NonNull JSONObject app) {
        View section = root.findViewById(R.id.app_detail_changelog_section);
        if (section == null) return;
        String changelog = cleanBr(app.optString("changelog", "")).trim();
        if (isBlankChangelog(changelog)) {
            section.setVisibility(View.GONE);
            return;
        }
        section.setVisibility(View.VISIBLE);
        TextView body = root.findViewById(R.id.app_detail_changelog);
        body.setText(changelog);
        com.typheye.wgpro.utils.AppUtils.longPressToCopy(body);
    }

    /** 服务端没写更新日志时会填「-」「--」「—」「无」之类的占位，这些一律当没有。 */
    private static boolean isBlankChangelog(String value) {
        String text = value == null ? "" : value.trim();
        if (isPlaceholderOnly(text)) return true;
        switch (text) {
            case "无":
            case "没有":
            case "暂无":
            case "暂无更新":
            case "暂无更新日志":
            case "null":
            case "NULL":
            case "N/A":
            case "0":
                return true;
            default:
                return false;
        }
    }

    /** 空、或只有连字符/波浪号这类占位符号。 */
    private static boolean isPlaceholderOnly(String value) {
        String text = value == null ? "" : value.trim();
        return text.isEmpty() || text.replaceAll("[-—–－~～_\\s]", "").isEmpty();
    }

    /** 附加信息：与网站应用页一致（config 的 app-comment-show）；为空/纯占位符时整块隐藏。 */
    private void bindExtraInfo(@NonNull View root, @NonNull JSONObject app) {
        View section = root.findViewById(R.id.app_detail_extra_section);
        if (section == null) return;
        String extra = cleanBr(app.optString("extra_info", "")).trim();
        if (isPlaceholderOnly(extra)) {
            section.setVisibility(View.GONE);
            return;
        }
        section.setVisibility(View.VISIBLE);
        TextView body = root.findViewById(R.id.app_detail_extra);
        body.setText(extra);
        com.typheye.wgpro.utils.AppUtils.longPressToCopy(body);
    }

    /**
     * 更多信息：只列界面上还没展示过的字段
     * （开发者/版本/大小/下载量已经在头部与上面的数据条里，这里不再重复）。
     */
    private void bindAppInfo(@NonNull View root, @NonNull JSONObject app) {
        LinearLayout list = root.findViewById(R.id.app_detail_info_list);
        if (list == null) return;
        list.removeAllViews();
        boolean vela = "velaapp".equals(app.optString("kind", ""));
        addInfoRow(list, "包名", app.optString("package", ""));
        addInfoRow(list, "版本", app.optString("version_name", ""));
        addInfoRow(list, "版本号", app.optString("version_code", ""));
        addInfoRow(list, "更新日期", app.optString("updated_at", ""));
        addInfoRow(list, "语言", app.optString("language", ""));
        addInfoRow(list, "架构", app.optString("abi", ""));
        addInfoRow(list, "分级", app.optString("age_rating", ""));
        addInfoRow(list, "应用类型", vela ? "Xiaomi Vela 应用" : "Android 应用");
    }

    private void addInfoRow(@NonNull LinearLayout list, @NonNull String label, @Nullable String value) {
        if (value == null || value.trim().isEmpty()) return;
        android.content.Context context = list.getContext();
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(android.view.Gravity.CENTER_VERTICAL);
        row.setPadding(0, dp(7), 0, dp(7));

        TextView labelView = new TextView(context);
        labelView.setText(label);
        labelView.setTextColor(context.getColor(R.color.text_secondary));
        labelView.setTextSize(15f);
        row.addView(labelView, new LinearLayout.LayoutParams(dp(84), -2));

        TextView valueView = new TextView(context);
        valueView.setText(value);
        valueView.setTextColor(context.getColor(R.color.text_primary));
        valueView.setTextSize(15f);
        valueView.setSingleLine(true);
        valueView.setEllipsize(android.text.TextUtils.TruncateAt.END);
        row.addView(valueView, new LinearLayout.LayoutParams(0, -2, 1f));
        com.typheye.wgpro.utils.AppUtils.longPressToCopy(valueView);
        list.addView(row);
    }
}
