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
    /** 最近一次报价（决定底部按钮显示「获取」还是「图标+价格」） */
    private int quotedPrice = -1;
    private boolean quotedOwned = false;
    /** 云端已售罄：底部按钮直接显示「已售罄」 */
    private boolean quotedStockOut = false;
    /** 「保存到本机」若是针对已下载文件，这里存着待复制的任务 */
    private com.typheye.wgpro.core.download.DownloadTask pendingSaveTask;
    private android.widget.ImageView actionIcon;
    /** 填充区域内的反色文字层（按填充宽度裁剪，保证压在深色填充上的字也清晰） */
    private android.widget.TextView actionTextFill;
    private int lastFillWidth = -1;
    private CharSequence lastActionText = null;
    /** 段形胶囊状态：只在真正切换时重设形状（每个进度回调都重设背景会闪） */
    private boolean lastSplitState = false;
    private com.google.android.material.card.MaterialCardView actionDeleteCard;
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
                    if (uri == null) return;
                    if (pendingSaveTask != null) {         // 已下载 → 复制
                        copyToPicked(uri, pendingSaveTask);
                        pendingSaveTask = null;
                        return;
                    }
                    if (pendingSaveApp == null || pendingSaveUrl == null) return;
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
        // 失败不再静默：弹失败信息弹窗（带「重试」）
        final com.typheye.wgpro.core.download.DownloadTask failedTask = task;
        if (failedTask != null) {
            if (failedTask.status == com.typheye.wgpro.core.download.DownloadTask.STATUS_FAILED) {
                com.typheye.wgpro.ui.widget.DownloadRetryFlow.notifyFailure(requireContext(), failedTask,
                        () -> com.typheye.wgpro.ui.widget.DownloadRetryFlow
                                .retry(requireContext(), failedTask, null));
            } else {
                com.typheye.wgpro.ui.widget.DownloadRetryFlow.resetNotification(failedTask);
            }
        }
        if (task == null) {
            if (actionDeleteCard != null) actionDeleteCard.setVisibility(View.GONE);
            applySplitPillShape(false);
            applyAcquireButton();
            actionCard.setEnabled(!downloadUrl.isEmpty());
            setActionFill(0);
            actionCard.setOnClickListener(v -> acquire());
            // 报价还没拿到（或过期）时补一次，回来再刷新按钮
            if (quotedPrice < 0) refreshQuoteForButton();
            return;
        }
        if (actionDeleteCard != null) actionDeleteCard.setVisibility(View.GONE);
        // 进入进度/等待/继续/重试/使用 等状态：积分图标必须消失（它只跟价格数字一起出现）
        if (actionIcon != null) actionIcon.setVisibility(View.GONE);
        final com.typheye.wgpro.core.download.DownloadTask current = task;
        switch (task.status) {
            case com.typheye.wgpro.core.download.DownloadTask.STATUS_RUNNING:
                actionCard.setEnabled(true);
                setActionText(task.progress() + "%");
                setActionFill(task.progress());
                actionCard.setOnClickListener(v -> manager.control(requireContext(), current.id,
                        com.typheye.wgpro.core.download.DownloadService.ACTION_PAUSE));
                break;
            case com.typheye.wgpro.core.download.DownloadTask.STATUS_QUEUED:
                actionCard.setEnabled(true);
                setActionText("等待中");
                setActionFill(0);   // 排队中不显示半截进度（否则像卡住）
                actionCard.setOnClickListener(v -> manager.control(requireContext(), current.id,
                        com.typheye.wgpro.core.download.DownloadService.ACTION_PAUSE));
                break;
            case com.typheye.wgpro.core.download.DownloadTask.STATUS_PAUSED:
                actionCard.setEnabled(true);
                setActionText("继续");
                setActionFill(task.progress());
                actionCard.setOnClickListener(v -> manager.control(requireContext(), current.id,
                        com.typheye.wgpro.core.download.DownloadService.ACTION_RESUME));
                break;
            case com.typheye.wgpro.core.download.DownloadTask.STATUS_FAILED:
                actionCard.setEnabled(true);
                setActionText("重试");
                setActionFill(0);
                // 重试 = 重新测速挑最快节点再下（旧节点可能已变慢/挂掉）
                actionCard.setOnClickListener(v -> com.typheye.wgpro.ui.widget.DownloadRetryFlow
                        .retry(requireContext(), current, null));
                break;
            case com.typheye.wgpro.core.download.DownloadTask.STATUS_COMPLETED:
                actionCard.setEnabled(true);
                if (actionDeleteCard != null) actionDeleteCard.setVisibility(View.VISIBLE);
                applySplitPillShape(true);
                actionIcon.setVisibility(View.GONE);
                setActionText("使用");
                setActionFill(0);
                actionCard.setOnClickListener(v -> showOperationSheet(appJson, downloadUrl, current));
                actionDeleteCard.setOnClickListener(v -> confirmDeleteDownload());
                break;
            default:
                break;
        }
    }

    /** 已售罄：定价卡 + 「加入/移出星标」单项 + 「分享应用链接」。 */
    private void showSoldOutSheet(int price, String stockLabel, int availableTotal) {
        boolean starred = appJson.optBoolean("is_collected", false)
                || requireContext().getSharedPreferences("app_stars", android.content.Context.MODE_PRIVATE)
                        .getBoolean(appKey, false);
        final java.util.List<String> labels = new java.util.ArrayList<>();
        final java.util.List<Integer> codes = new java.util.ArrayList<>();
        labels.add(starred ? "将该应用移出星标" : "将该应用加入星标");
        codes.add(0);
        labels.add("分享应用链接");
        codes.add(1);
        com.typheye.wgpro.ui.widget.GetSheet.show(requireContext(), price, stockLabel, availableTotal,
                labels, (dialog, which) -> {
                    if (codes.get(which) == 0) setStarred(!starred);
                    else shareAppLink();
                });
    }

    /** 星标开关（collection_action2，应用）。 */
    private void setStarred(boolean add) {
        if (!isAdded()) return;
        final android.content.Context context = requireContext();
        java.util.Map<String, String> fields = new java.util.LinkedHashMap<>();
        fields.put("target_type", "app");
        fields.put("target_key", appKey);
        fields.put("action", add ? "add" : "remove");
        new com.typheye.wgpro.utils.tAccUtils(context).postV2Json("collection_action2", fields,
                new com.typheye.wgpro.utils.tAccUtils.JsonCallback() {
                    @Override
                    public void onSuccess(@NonNull JSONObject json) {
                        try {
                            appJson.put("is_collected", json.optBoolean("collected", add));
                        } catch (Exception ignored) { }
                        context.getSharedPreferences("app_stars", android.content.Context.MODE_PRIVATE)
                                .edit().putBoolean(appKey, add).apply();
                        com.typheye.wgpro.ui.widget.WGProHint.show(context,
                                add ? "已加入星标" : "已移出星标");
                    }

                    @Override
                    public void onError(int statusCode, @NonNull String message) {
                        com.typheye.wgpro.ui.widget.WGProHint.showError(context, "操作失败", message);
                    }
                });
    }

    /** 分享应用（优先用详情页分享链接）。 */
    private void shareAppLink() {
        // 分享页地址统一构造：绝不再回退到 10 分钟就过期的签名下载直链
        String name = appJson.optString("name", "应用");
        String link = com.typheye.wgpro.utils.WGProShareLinks.appUrl(appJson);
        android.content.Intent share = new android.content.Intent(android.content.Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(android.content.Intent.EXTRA_TEXT,
                        com.typheye.wgpro.utils.WGProShareLinks.shareText(name, link));
        try {
            startActivity(android.content.Intent.createChooser(share, "分享给好友"));
        } catch (Exception e) {
            com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(), "分享失败", "没有可用的分享应用");
        }
    }

    /**
     * 「使用 | 删除」并排时做成"分段胶囊"：左胶囊只留左圆角、右胶囊只留右圆角，中间只有一条缝；
     * 其余状态恢复整颗胶囊圆角。
     */
    private void applySplitPillShape(boolean split) {
        if (split == lastSplitState && actionCard != null
                && actionCard.getShapeAppearanceModel() != null) return;
        lastSplitState = split;
        final float radius = 25f * getResources().getDisplayMetrics().density;
        if (actionCard != null) {
            com.google.android.material.shape.ShapeAppearanceModel model = split
                    ? com.google.android.material.shape.ShapeAppearanceModel.builder()
                        .setTopLeftCorner(com.google.android.material.shape.CornerFamily.ROUNDED, radius)
                        .setBottomLeftCorner(com.google.android.material.shape.CornerFamily.ROUNDED, radius)
                        .setTopRightCorner(com.google.android.material.shape.CornerFamily.ROUNDED, 0f)
                        .setBottomRightCorner(com.google.android.material.shape.CornerFamily.ROUNDED, 0f)
                        .build()
                    : com.google.android.material.shape.ShapeAppearanceModel.builder()
                        .setAllCorners(com.google.android.material.shape.CornerFamily.ROUNDED, radius)
                        .build();
            actionCard.setShapeAppearanceModel(model);
        }
        if (actionDeleteCard != null) {
            actionDeleteCard.setShapeAppearanceModel(
                    com.google.android.material.shape.ShapeAppearanceModel.builder()
                            .setTopLeftCorner(com.google.android.material.shape.CornerFamily.ROUNDED, 0f)
                            .setBottomLeftCorner(com.google.android.material.shape.CornerFamily.ROUNDED, 0f)
                            .setTopRightCorner(com.google.android.material.shape.CornerFamily.ROUNDED, radius)
                            .setBottomRightCorner(com.google.android.material.shape.CornerFamily.ROUNDED, radius)
                            .build());
        }
    }

    /** 付费且未拥有 → 「积分图标 + 纯数字价格」；其他情况显示「获取」。 */
    private void applyAcquireButton() {
        if (actionText == null) return;
        if (quotedStockOut) {          // 已售罄：不显示价格，也不显示积分图标
            setActionText("已售罄");
            if (actionIcon != null) actionIcon.setVisibility(View.GONE);
            return;
        }
        boolean paid = quotedPrice > 0 && !quotedOwned;
        if (paid) {
            setActionText(String.valueOf(quotedPrice));
            if (actionIcon != null) {
                actionIcon.setVisibility(View.VISIBLE);
                // 图标颜色与文字一致
                actionIcon.setColorFilter(actionText.getCurrentTextColor());
            }
        } else {
            setActionText("获取");
            if (actionIcon != null) actionIcon.setVisibility(View.GONE);
        }
    }

    /** 拉一次报价用于底部按钮（失败则用本地缓存兜底）。 */
    private void refreshQuoteForButton() {
        final android.content.Context context = requireContext();
        com.typheye.wgpro.utils.tPointsUtils.quote(context, "app", appKey,
                new com.typheye.wgpro.utils.tPointsUtils.JsonCallback() {
                    @Override
                    public void onSuccess(@NonNull JSONObject info) {
                        quotedPrice = info.optInt("price", 0);
                        quotedOwned = info.optBoolean("owned", quotedPrice <= 0);
                        quotedStockOut = "out_of_stock".equals(info.optString("stock_state", ""));
                        com.typheye.wgpro.utils.tPointsUtils.cachePrice(context, "app", appKey,
                                quotedPrice, quotedOwned);
                        if (isAdded()) applyAcquireButton();
                    }

                    @Override
                    public void onError(int status, @NonNull String message, @Nullable JSONObject info) {
                        int cached = com.typheye.wgpro.utils.tPointsUtils.cachedPrice(context, "app", appKey);
                        quotedPrice = Math.max(0, cached);
                        quotedOwned = com.typheye.wgpro.utils.tPointsUtils.cachedOwned(context, "app", appKey);
                        if (isAdded()) applyAcquireButton();
                    }
                });
    }

    /** 「删除」按钮：先二次确认，再清掉下载记录与本地文件。 */
    private void confirmDeleteDownload() {
        if (!isAdded()) return;
        com.typheye.wgpro.ui.widget.GetSheet.showDeleteConfirm(requireContext(),
                appJson.optString("name", "该应用"), this::deleteCurrentDownload);
    }

    /** 清掉下载记录与本地文件（原「操作」菜单里的清理该文件）。 */
    private void deleteCurrentDownload() {
        if (appKey == null || appKey.isEmpty()) return;
        com.typheye.wgpro.core.download.DownloadManager.get().tasks().getValue();
        for (com.typheye.wgpro.core.download.DownloadTask t
                : com.typheye.wgpro.core.download.DownloadManager.get().tasks().getValue() == null
                ? java.util.Collections.<com.typheye.wgpro.core.download.DownloadTask>emptyList()
                : com.typheye.wgpro.core.download.DownloadManager.get().tasks().getValue()) {
            if (t.id.equals(appKey)) {
                com.typheye.wgpro.core.download.DownloadManager.get().control(
                        requireContext(), t.id,
                        com.typheye.wgpro.core.download.DownloadService.ACTION_REMOVE);
                deleteLocalFile(t);
                break;
            }
        }
    }


    /**
     * 填充区域反色文字的颜色：**按对比度择优**（白 vs 深墨），深浅模式都清楚。
     * <p>不能写死白色：夜间 colorPrimary = #8EA2FF 亮蓝，白字对比度只有 ~2.4。</p>
     */
    private int fillInkColor() {
        int fill = com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder.resolveThemeColor(
                requireContext(), "colorPrimary", R.color.brand_primary);
        int white = requireContext().getColor(R.color.white);
        int dark = requireContext().getColor(R.color.text_on_fill);
        return androidx.core.graphics.ColorUtils.calculateContrast(white, fill)
                >= androidx.core.graphics.ColorUtils.calculateContrast(dark, fill) ? white : dark;
    }

    /** 设置药丸文字：常规层与"填充区域反色层"必须完全一致。 */
    private void setActionText(CharSequence text) {
        if (text == null) text = "";
        // 文字没变就不重复设置（其他下载任务的进度回调也会触发重绘，重复设置会让反色层闪一下）
        if (android.text.TextUtils.equals(lastActionText, text)) return;
        lastActionText = text;
        if (actionText != null) actionText.setText(text);
        if (actionTextFill != null) actionTextFill.setText(text);
    }

    /** 反色文字层按填充宽度裁剪；填充为 0 时整层隐藏（避免和常规层重影）。 */
    private void clipActionTextFill(int fillWidth) {
        if (actionTextFill == null || actionCard == null) return;
        if (fillWidth <= 0) {
            // 用 INVISIBLE 而不是 GONE：不进布局也不产生可见性抖动
            if (actionTextFill.getVisibility() != View.INVISIBLE) {
                actionTextFill.setVisibility(View.INVISIBLE);
            }
            return;
        }
        if (actionTextFill.getVisibility() != View.VISIBLE) {
            actionTextFill.setVisibility(View.VISIBLE);
        }
        // 视图还没量完时高度可能是 0，用父卡片高度兜底，避免裁剪成空
        int clipHeight = Math.max(actionTextFill.getHeight(), actionCard.getHeight());
        actionTextFill.setClipBounds(new android.graphics.Rect(0, 0, fillWidth, clipHeight));
    }

    /** 进度填充宽度 = 药丸宽度的 progress%。 */
    private void setActionFill(int progress) {
        if (actionCard == null || actionFill == null) return;
        int width = actionCard.getWidth();
        if (width <= 0) {                       // 还没量完，等一帧
            actionCard.post(() -> setActionFill(progress));
            return;
        }
        int desired = Math.max(0, Math.round(width * progress / 100f));
        if (desired == lastFillWidth) return;    // 宽度没变就不动，避免反复触发裁剪/可见性切换
        lastFillWidth = desired;
        android.view.ViewGroup.LayoutParams params = actionFill.getLayoutParams();
        if (params.width != desired) {
            params.width = desired;
            actionFill.setLayoutParams(params);
        }
        clipActionTextFill(desired);            // 同步裁剪，别丢到 post 里（会和文字设置错帧）
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
        // 图标呈现规则统一：Vela 圆图标原样显示（不套圆角矩形底、不留内边距）
        com.typheye.wgpro.ui.widget.WGProIconStyle.apply(icon, vela);
        if (!iconUrl.isEmpty()) load(iconUrl, icon, () -> {
            icon.setVisibility(View.VISIBLE); root.findViewById(R.id.app_detail_initial).setVisibility(View.GONE);
            if (loaded != null) loaded.run();
        });
        else if (loaded != null) root.postDelayed(loaded, 300L);
        actionCard = root.findViewById(R.id.app_detail_action);
        actionIcon = root.findViewById(R.id.app_detail_action_icon);
        actionTextFill = root.findViewById(R.id.app_detail_action_text_fill);
        if (actionTextFill != null) actionTextFill.setTextColor(fillInkColor());
        actionDeleteCard = root.findViewById(R.id.app_detail_action_delete);
        if (actionDeleteCard != null) {
            actionDeleteCard.setOnClickListener(v -> confirmDeleteDownload());
        }
        actionBar = root.findViewById(R.id.app_detail_action_bar);
        actionFill = root.findViewById(R.id.app_detail_action_fill);
        actionText = root.findViewById(R.id.app_detail_action_text);
        setupActionBar(root);
        appJson = app;
        appKey = app.optString("id", app.optString("package", ""));
        downloadUrl = app.optString("download_url", app.optString("detail_url", ""));
        actionCard.setEnabled(!downloadUrl.isEmpty());
        setActionText("获取");
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
            // 截图位固定 5 格：不足的用占位格补齐（各应用都是 5 张 720x1280 的规范，
            // 只有 1 张时整排会缺一大块，看着像坏了）
            int slots = Math.max(SCREENSHOT_SLOTS, shotUrls.size());
            for (int i = 0; i < slots; i++) {
                LinearLayout.LayoutParams p =
                        new LinearLayout.LayoutParams(dp(178), dp(316));
                p.setMarginEnd(dp(12));
                if (i < shotUrls.size()) {
                    final int index = i;
                    ImageView shot = new ImageView(requireContext());
                    shot.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    shot.setBackgroundResource(R.drawable.bg_app_screenshot);
                    shot.setClipToOutline(true);
                    shot.setClickable(true);
                    shot.setOnClickListener(v -> com.typheye.wgpro.ui.function.PreviewActivity
                            .open(requireContext(), shotUrls, index));
                    screenshots.addView(shot, p);
                    load(shotUrls.get(i), shot, null);
                } else {
                    // 占位格：淡图片图标 + 圆角暗格，不可点
                    ImageView placeholder = new ImageView(requireContext());
                    placeholder.setScaleType(ImageView.ScaleType.CENTER);
                    placeholder.setBackgroundResource(R.drawable.bg_app_screenshot);
                    placeholder.setClipToOutline(true);
                    placeholder.setImageResource(R.drawable.ic_image_placeholder);
                    placeholder.setImageTintList(android.content.res.ColorStateList.valueOf(
                            com.typheye.wgpro.ui.widget.WGProColors.of(requireContext(),
                                    R.color.text_secondary)));
                    placeholder.setAlpha(0.38f);
                    screenshots.addView(placeholder, p);
                }
            }
        }
    }

    /** 截图位固定 5 格（应用商店惯例：不足补位）。 */
    private static final int SCREENSHOT_SLOTS = 5;

    /**
     * 「获取」入口：先问服务端报价——
     * 免费或已拥有 → 直接开始下载；要扣积分 → 弹「获取」弹窗，交易成功后再下载。
     */
    private void acquire() {
        // 已经有下载任务（含已完成）就直接用，不再走一遍获取/测速流程
        java.util.List<com.typheye.wgpro.core.download.DownloadTask> existing =
                com.typheye.wgpro.core.download.DownloadManager.get().tasks().getValue();
        if (existing != null) {
            for (com.typheye.wgpro.core.download.DownloadTask t : existing) {
                if (t.id.equals(appKey)) {
                    if (t.status == com.typheye.wgpro.core.download.DownloadTask.STATUS_COMPLETED) {
                        showOperationSheet(appJson, downloadUrl, t);
                        return;
                    }
                    if (t.isActive()) return;   // 正在进行/排队：忽略重复获取
                }
            }
        }
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
                        // 异步回调可能在页面已 detach 后才回来，别再碰 UI
                        if (!isAdded()) return;
                        quotedPrice = price;
                        quotedOwned = owned;
                        quotedStockOut = "out_of_stock".equals(info.optString("stock_state", ""));
                        if (!quotedStockOut && (price <= 0 || owned)) {
                            applyAcquireButton();
                            startDownload(appJson, downloadUrl, 1);
                        } else {
                            applyAcquireButton();
                            showDownloadSheet(price, info.optString("stock_label", "有库存"),
                                    info.optInt("total", 0), info.optString("stock_state", ""),
                                    info.optString("sold_out_note", ""),
                                    info.optBoolean("enough", true));
                        }
                    }

                    @Override
                    public void onError(int status, @NonNull String message, @Nullable JSONObject info) {
                        // 服务不可用：已知免费的条目照常放行，收费条目提示稍后再试
                        int cachedPrice = com.typheye.wgpro.utils.tPointsUtils.cachedPrice(context, "app", appKey);
                        boolean cachedOwned = com.typheye.wgpro.utils.tPointsUtils.cachedOwned(context, "app", appKey);
                        if (!isAdded()) return;
                        if (cachedPrice == 0 || cachedOwned) {
                            startDownload(appJson, downloadUrl, 1);
                            return;
                        }
                        com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(),
                                status == 401 ? "请先登录" : "获取失败",
                                status == 401 ? "登录后才能获取该应用。" : message + "，请稍后再试。");
                    }
                });
    }

    /** 「获取」弹窗：定价头部（库存 | 账户可用积分）+ 使用积分支付 / 使用兑换代码 / 找他人代付。 */
    private void showDownloadSheet(int price, String stockLabel, int availableTotal,
                                   String stockState, String soldOutNote, boolean enough) {
        if (!isAdded()) return;
        // 已售罄：仍是同一张定价卡（库存那行已写「已售罄」），加回分组：星标（按当前状态单项）+ 分享链接
        if ("out_of_stock".equals(stockState)) {
            showSoldOutSheet(price, stockLabel, availableTotal);
            return;
        }
        final java.util.List<String> labels = new java.util.ArrayList<>();
        final java.util.List<Integer> codes = new java.util.ArrayList<>();
        if (enough) {   // 积分不足时不显示「使用积分支付」
            labels.add("使用积分支付");
            codes.add(0);
        }
        labels.add("使用兑换代码");
        codes.add(1);
        labels.add("找他人代付");
        codes.add(2);
        com.typheye.wgpro.ui.widget.GetSheet.show(requireContext(), price, stockLabel, availableTotal,
                labels, (dialog, which) -> {
                    int code = codes.get(which);
                    if (code == 0) {
                        confirmAndPay(price, "balance", "");
                    } else if (code == 1) {
                        com.typheye.wgpro.ui.widget.GetSheet.showRedeem(requireContext(), price,
                                redeemCode -> confirmAndPay(price, "redeem", redeemCode));
                    } else {
                        com.typheye.wgpro.ui.widget.WGProHint.showInfo(requireContext(), "找他人代付",
                                "把该应用的分享链接发给好友，由对方为你获取。");
                    }
                });
    }

    /** 二次确认 → 云端下发验证码 → 输入验证码完成交易 → 开始下载。 */
    private void confirmAndPay(int price, String payMethod, String redeemCode) {
        final android.content.Context context = requireContext();
        String name = appJson.optString("name", "该应用");
        com.typheye.wgpro.ui.widget.GetSheet.showConfirm(context, name, price, payMethod, () ->
                com.typheye.wgpro.utils.tPointsUtils.verifySend(context, "app", appKey,
                        payMethod, redeemCode,
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
            com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(), "已售罄", message);
        } else if (status == 401) {
            com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(),
                    "请先登录", "登录后才能获取该应用。");
        } else {
            com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(), "获取失败", message);
        }
    }

    /** 「使用」：已下载后的动作（安装到手机 / 保存到本机 / 分享链接）。 */
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
        if (isVela && hasDevice) { labels.add("安装到…"); codes.add(0); }
        if (!isVela) { labels.add("安装到手机"); codes.add(1); }
        labels.add("保存到本机"); codes.add(2);
        labels.add("分享应用链接"); codes.add(4);
        com.typheye.wgpro.ui.widget.GetSheet.showActions(requireContext(), "使用", labels,
                (dialog, which) -> {
            int code = codes.get(which);
            if (code == 4) {
                shareAppLink();
            } else if (code == 2) {
                // 已经下好了：把这份文件另存到用户选的位置，不再重新下载
                saveExistingFile(task);
            } else if (code == 1) {
                installDownloaded(task);
            } else {
                // 安装到… → 先选设备，再走手表侧下载/安装通道
                showDevicePicker(app, downloadUrl, params, deviceName);
            }
        });
    }

    /**
     * 「安装到…」：从**设备数据库**取设备（已删除的不会出现），
     * 列表同时给出**穿戴设备**与**模拟设备**：
     *
     * <ul>
     *   <li>穿戴设备（type=xiaomi）：第二行「已连接 / 已断开 | 小米运动健康」</li>
     *   <li>模拟设备（type=virtual）：第二行「运行中 / 已停止 | 模拟设备」</li>
     * </ul>
     */
    /** 连接/断开的时刻：对应状态列为 0 时回退最后活跃时间。 */
    private static long stateTime(@NonNull android.database.Cursor cursor, boolean connected) {
        String column = connected ? "connected_at" : "disconnected_at";
        int index = cursor.getColumnIndex(column);
        long time = index < 0 ? 0L : cursor.getLong(index);
        if (time <= 0L) {
            int lastSeen = cursor.getColumnIndex("last_seen");
            time = lastSeen < 0 ? 0L : cursor.getLong(lastSeen);
        }
        return time;
    }

    /** 相对时间文案。 */
    @NonNull
    private static String relativeTime(long time) {
        if (time <= 0L) {
            return "未知时间";
        }
        long diff = System.currentTimeMillis() - time;
        if (diff < 60_000L) {
            return "刚刚";
        }
        if (diff < 3_600_000L) {
            return (diff / 60_000L) + " 分钟前";
        }
        if (diff < 86_400_000L) {
            return (diff / 3_600_000L) + " 小时前";
        }
        if (diff < 30L * 86_400_000L) {
            return (diff / 86_400_000L) + " 天前";
        }
        return new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
                .format(new java.util.Date(time));
    }

    private void showDevicePicker(JSONObject app, String downloadUrl,
                                  com.typheye.wgpro.core.xms.UIParams params, String fallbackName) {
        final java.util.List<com.typheye.wgpro.ui.widget.GetSheet.DeviceOption> options =
                new java.util.ArrayList<>();
        /** 每项不可安装的原因（null = 可安装）。 */
        final java.util.List<String> blocks = new java.util.ArrayList<>();
        boolean velaApp = "velaapp".equals(app.optString("kind", ""));
        try (com.typheye.wgpro.data.DeviceDatabase db =
                     new com.typheye.wgpro.data.DeviceDatabase(requireContext())) {
            // 1) 穿戴设备
            try (android.database.Cursor cursor = db.all("xiaomi")) {
                while (cursor.moveToNext()) {
                    String deviceName = deviceDisplayName(cursor, fallbackName);
                    boolean connected = cursor.getInt(
                            cursor.getColumnIndexOrThrow("connected")) != 0;
                    options.add(new com.typheye.wgpro.ui.widget.GetSheet.DeviceOption(
                            deviceName, (connected ? "已连接" : "已断开") + " | "
                            + (connected ? "连接于 " : "断开于 ")
                            + relativeTime(stateTime(cursor, connected))));
                    // 安装到手表的能力**尚未适配**：先一律给提示，不直接开始安装
                    blocks.add("「安装到设备」暂未适配，后续版本开放");
                }
            }
            // 2) 模拟设备
            try (android.database.Cursor cursor =
                         db.all(com.typheye.wgpro.data.DeviceDatabase.TYPE_VIRTUAL)) {
                while (cursor.moveToNext()) {
                    String rowId = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                    String model = com.typheye.wgpro.core.emu.VelaProvision.modelIdOf(rowId);
                    if (model.isEmpty()) {
                        model = cursor.getString(cursor.getColumnIndexOrThrow("model"));
                    }
                    String deviceName = deviceDisplayName(cursor, fallbackName);
                    // 运行态：先用设备库的 connected（引擎启动/退出/崩溃都会实时写库），
                    // 再用内存里的实时状态兜底（管理器 init 幂等），避免两者不同步时显示错。
                    boolean running = cursor.getInt(
                            cursor.getColumnIndexOrThrow("connected")) != 0;
                    if (!running) {
                        com.typheye.wgpro.core.emu.VelaEmulatorManager manager =
                                com.typheye.wgpro.core.emu.VelaEmulatorManager.get();
                        manager.init(requireContext());
                        running = manager.isRunning(model);
                    }
                    options.add(new com.typheye.wgpro.ui.widget.GetSheet.DeviceOption(
                            deviceName, (running ? "运行中" : "已停止") + " | "
                            + (running ? "连接于 " : "断开于 ")
                            + relativeTime(stateTime(cursor, running))));
                    blocks.add("「安装到设备」暂未适配，后续版本开放");
                }
            }
        } catch (Exception ignored) {
        }

        com.typheye.wgpro.ui.widget.GetSheet.showDevicePicker(requireContext(), options,
                (dialog, which) -> {
                    if (which < 0 || which >= options.size()) return;
                    String block = which < blocks.size() ? blocks.get(which) : null;
                    if (block != null) {
                        com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(),
                                "无法安装", block);
                        return;
                    }
                    startDownload(app, downloadUrl, 0);
                    com.typheye.wgpro.ui.widget.WGProHint.show(requireContext(),
                            "已开始安装到 " + options.get(which).name);
                });
    }

    /** 安装已下载的 APK（不再重新下载）。 */
    private void installDownloaded(com.typheye.wgpro.core.download.DownloadTask task) {
        java.io.File file = downloadFile(task);
        if (file == null) {
            com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(), "无法安装", "本地文件不存在，请重新下载");
            return;
        }
        if (!com.typheye.wgpro.core.download.DownloadService.installFromFile(requireContext(), file)) {
            com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(), "无法安装", "没有可用的安装程序");
        }
    }

    /** 已下载文件在本地的位置（找不到返回 null）。 */
    @Nullable
    private java.io.File downloadFile(com.typheye.wgpro.core.download.DownloadTask task) {
        try {
            String path = task.localPath;
            if (path != null && !path.isEmpty() && !path.startsWith("content://")) {
                java.io.File direct = new java.io.File(path);
                if (direct.exists()) return direct;
            }
            java.io.File dir = new java.io.File(requireContext().getFilesDir(), "downloads");
            java.io.File byId = new java.io.File(dir, task.id + ".apk");
            if (byId.exists()) return byId;
            java.io.File[] files = dir.listFiles();
            if (files != null) {
                for (java.io.File f : files) {
                    if (f.getName().startsWith(task.id)) return f;
                }
            }
        } catch (Exception ignored) { }
        return null;
    }

    /** 「保存到本机」：已下载 → 让用户选位置后复制过去（不重新下载）。 */
    private void saveExistingFile(com.typheye.wgpro.core.download.DownloadTask task) {
        if (downloadFile(task) == null) {
            com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(), "无法保存", "本地文件不存在，请重新下载");
            return;
        }
        pendingSaveTask = task;
        pendingSaveApp = null;
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT)
                .addCategory(Intent.CATEGORY_OPENABLE)
                .setType("application/vnd.android.package-archive")
                .putExtra(Intent.EXTRA_TITLE,
                        appJson.optString("package", appJson.optString("id", "app")) + ".apk");
        try {
            saveLauncher.launch(intent);
        } catch (Exception e) {
            com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(), "无法保存", "无法打开文件保存窗口");
        }
    }

    /** 把已下载文件复制到 SAF 选定的位置。 */
    private void copyToPicked(android.net.Uri target, com.typheye.wgpro.core.download.DownloadTask task) {
        java.io.File source = downloadFile(task);
        if (source == null) {
            com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(), "保存失败", "本地文件不存在");
            return;
        }
        try (java.io.InputStream in = new java.io.FileInputStream(source);
             java.io.OutputStream out = requireContext().getContentResolver().openOutputStream(target)) {
            if (out == null) throw new java.io.IOException("无法写入所选位置");
            byte[] buffer = new byte[8192];
            int read;
            while ((read = in.read(buffer)) > 0) out.write(buffer, 0, read);
            com.typheye.wgpro.ui.widget.WGProHint.show(requireContext(), "已保存到所选位置");
        } catch (Exception e) {
            com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(), "保存失败",
                    e.getMessage() == null ? "写入失败" : e.getMessage());
        }
    }

    /** 分享应用（对方点开进分享页，而不是过期的签名下载直链）。 */
    private void shareDownloadLink(JSONObject app, String downloadUrl) {
        String name = app.optString("name", "应用");
        String link = com.typheye.wgpro.utils.WGProShareLinks.appUrl(app);
        android.content.Intent share = new android.content.Intent(android.content.Intent.ACTION_SEND)
                .setType("text/plain")
                .putExtra(android.content.Intent.EXTRA_TEXT,
                        com.typheye.wgpro.utils.WGProShareLinks.shareText(name, link));
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

    private void startDownload(JSONObject app, String downloadUrl, int which) {
        startDownload(app, downloadUrl, which, null);
    }

    /**
     * 拿到下载权限后真正开始下载：先弹「下载测速」底部弹窗（进度条 + 取消）选最快节点，
     * 取代原来的「已加入下载」轻提示；节点不可用时回退权威地址。
     */
    private void startDownload(JSONObject app, String downloadUrl, int which,
                               @Nullable android.net.Uri targetUri) {
        if (!isAdded()) return;
        com.typheye.wgpro.ui.widget.DownloadProbeSheet.start(requireContext(),
                app.optString("name", "应用"), downloadUrl, app.optJSONArray("download_mirrors"),
                new com.typheye.wgpro.ui.widget.DownloadProbeSheet.Callback() {
                    @Override
                    public void onReady(@NonNull String url) {
                        if (!isAdded()) return;
                        enqueueDownload(app, url, downloadUrl, which, targetUri);
                    }

                    @Override
                    public void onCancelled() {
                        // 用户取消测速：本次不下载（已获得的权限不受影响，可再次获取）
                    }
                });
    }

    private void enqueueDownload(JSONObject app, String url, String authoritativeUrl, int which,
                                 @Nullable android.net.Uri targetUri) {
        int destination = which == 0 ? com.typheye.wgpro.core.download.DownloadTask.DEST_DEVICE
                : which == 1 ? com.typheye.wgpro.core.download.DownloadTask.DEST_PHONE
                : com.typheye.wgpro.core.download.DownloadTask.DEST_LOCAL;
        String id = app.optString("id", app.optString("package", downloadUrl));
        com.typheye.wgpro.core.download.DownloadTask task =
                new com.typheye.wgpro.core.download.DownloadTask(
                        id, app.optString("name", "未命名应用"), app.optString("icon_url", ""),
                        url, app.optString("size", ""), destination,
                        "app", app.toString());
        if (targetUri != null) task.targetUri = targetUri.toString();
        // 兜底：镜像节点失败时回退到这个权威地址
        task.fallbackUrl = authoritativeUrl;
        com.typheye.wgpro.core.download.DownloadManager.get().enqueue(requireContext(), task);
        downloadStarted = true;
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
        labelView.setTextColor(com.typheye.wgpro.ui.widget.WGProColors.of(context, R.color.text_secondary));
        labelView.setTextSize(15f);
        row.addView(labelView, new LinearLayout.LayoutParams(dp(84), -2));

        TextView valueView = new TextView(context);
        valueView.setText(value);
        valueView.setTextColor(com.typheye.wgpro.ui.widget.WGProColors.of(context, R.color.text_primary));
        valueView.setTextSize(15f);
        valueView.setSingleLine(true);
        valueView.setEllipsize(android.text.TextUtils.TruncateAt.END);
        row.addView(valueView, new LinearLayout.LayoutParams(0, -2, 1f));
        com.typheye.wgpro.utils.AppUtils.longPressToCopy(valueView);
        list.addView(row);
    }

    /** 设备显示名：note（可改名备注）优先，其次 model，最后兜底名。 */
    @NonNull
    private static String deviceDisplayName(@NonNull android.database.Cursor cursor,
                                           @NonNull String fallback) {
        String note = cursor.getString(cursor.getColumnIndexOrThrow("note"));
        if (note != null && !note.trim().isEmpty()) {
            return note.trim();
        }
        int modelIndex = cursor.getColumnIndex("model");
        String model = modelIndex >= 0 ? cursor.getString(modelIndex) : null;
        return model == null || model.trim().isEmpty() ? fallback : model.trim();
    }
}
