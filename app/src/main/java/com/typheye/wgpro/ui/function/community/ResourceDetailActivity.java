package com.typheye.wgpro.ui.function.community;

import android.os.Bundle;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.card.MaterialCardView;
import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.tAccUtils;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProBottomSheetDialog;
import org.json.JSONObject;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ResourceDetailActivity extends BaseSectionActivity {
    public static final String EXTRA_RESOURCE_JSON = "resource_json";
    private ResourceDetailFragment fragment;

    @Override protected boolean loadingCoversAppBar() { return true; }
    @Override protected String screenTitle() { return ""; }

    @Override protected Fragment createContent() {
        fragment = ResourceDetailFragment.newInstance(getIntent().getStringExtra(EXTRA_RESOURCE_JSON));
        return fragment;
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem more = menu.add("更多").setIcon(R.drawable.ic_more_vertical_vector);
        more.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if ("更多".contentEquals(item.getTitle())) { showActions(); return true; }
        return super.onOptionsItemSelected(item);
    }

    /** 右上角菜单：星标 / 分享 / 举报（星标从正文里挪到这里，与应用详情页一致）。 */
    private void showActions() {
        if (fragment == null) return;
        boolean starred = fragment.isStarred();
        CharSequence[] actions = new CharSequence[]{
                starred ? "移除星标" : "加入星标", "举报"};
        new WGProAlertDialogBuilder(this).setTitle("资源操作").setItems(actions, (dialog, which) -> {
            if (dialog instanceof WGProBottomSheetDialog) ((WGProBottomSheetDialog) dialog).dismissForReplacement();
            sectionToolbar().postDelayed(() -> {
                if (which == 0) fragment.toggleStar();
                else fragment.reportResource();
            }, 40L);
        }).show();
    }

    public static final class ResourceDetailFragment extends Fragment {
        private JSONObject resource = new JSONObject();
        private tAccUtils account;
        private MaterialCardView actionCard;
        private View actionBar;
        private View actionFill;
        private TextView actionText;
        private android.widget.ImageView actionBlur;
        private androidx.activity.result.ActivityResultLauncher<Intent> saveLauncher;

        @Override public void onCreate(@Nullable Bundle state) {
            super.onCreate(state);
            saveLauncher = registerForActivityResult(
                    new androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() != android.app.Activity.RESULT_OK
                                || result.getData() == null) return;
                        android.net.Uri uri = result.getData().getData();
                        if (uri == null) return;
                        startDownload(com.typheye.wgpro.core.download.DownloadTask.DEST_LOCAL, uri);
                    });
        }

        static ResourceDetailFragment newInstance(String json) {
            ResourceDetailFragment f = new ResourceDetailFragment();
            Bundle b = new Bundle();
            b.putString("json", json == null ? "{}" : json);
            f.setArguments(b);
            return f;
        }

        @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater,
                                                     @Nullable android.view.ViewGroup parent, @Nullable Bundle state) {
            View root = inflater.inflate(R.layout.fragment_resource_detail, parent, false);
            BaseSectionActivity host = (BaseSectionActivity) requireActivity();
            host.showContentLoading();
            account = new tAccUtils(requireContext());
            JSONObject seed;
            try { seed = new JSONObject(requireArguments().getString("json", "{}")); }
            catch (Exception e) { seed = new JSONObject(); }
            String id = seed.optString("id", seed.optString("resource_id", seed.optString("target_key", "")));
            bind(root, seed);
            setupActionBar(root);
            if (id.matches("[a-fA-F0-9]{32}")) {
                Map<String, String> q = new LinkedHashMap<>(); q.put("resource_id", id);
                account.getV2Json("resource_detail2", q, false, new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        requireActivity().runOnUiThread(() -> {
                            bind(root, json.optJSONObject("info"));
                            host.hideContentLoading();
                        });
                    }
                    @Override public void onError(int code, @NonNull String msg) {
                        requireActivity().runOnUiThread(host::hideContentLoading);
                    }
                });
                if (account.isLogin() && requireContext().getSharedPreferences("history_preferences",
                        android.content.Context.MODE_PRIVATE).getBoolean("enabled", true)) {
                    Map<String, String> h = new LinkedHashMap<>();
                    h.put("target_type", "resource"); h.put("target_key", id);
                    account.postV2Json("history_record2", h, new tAccUtils.JsonCallback() {
                        public void onSuccess(JSONObject j) { }
                        public void onError(int c, String m) { }
                    });
                }
            } else {
                host.hideContentLoading();
            }
            return root;
        }

        @Override public void onViewCreated(@NonNull View view, @Nullable Bundle state) {
            super.onViewCreated(view, state);
            com.typheye.wgpro.core.download.DownloadManager.get().tasks()
                    .observe(getViewLifecycleOwner(), this::renderActionState);
        }

        private void bind(View root, JSONObject info) {
            if (info == null) return;
            resource = info;
            String title = info.optString("title", "未命名资源");
            TextView titleView = root.findViewById(R.id.resource_detail_title);
            TextView descriptionView = root.findViewById(R.id.resource_detail_description);
            titleView.setText(title);
            titleView.setOnClickListener(v -> com.typheye.wgpro.ui.widget.WGProHint.showInfo(
                    requireContext(), "资源名称", title));
            descriptionView.setText(info.optString("description", "暂无详细介绍")
                    .replaceAll("(?i)<br\\s*/?>", "\n"));
            com.typheye.wgpro.utils.AppUtils.longPressToCopy(descriptionView);

            // 发布者
            JSONObject author = info.optJSONObject("author");
            final String authorName = author == null ? "UID " + info.optString("uid", "--")
                    : author.optString("nick", "用户");
            TextView authorView = root.findViewById(R.id.resource_detail_author);
            authorView.setText(authorName);
            authorView.setOnClickListener(v -> com.typheye.wgpro.ui.widget.WGProHint.showInfo(
                    requireContext(), "发布者", "此资源由“" + authorName + "”发布。"));

            // 来源声明（服务端给，客户端兜底）
            String statement = info.optString("source_note", "").trim();
            if (statement.isEmpty()) {
                statement = info.optInt("uid", 0) == 10000 ? "资源来自互联网" : "资源由发布者创建";
            }
            TextView statementView = root.findViewById(R.id.resource_detail_statement);
            statementView.setText(statement);
            final String statementText = statement;
            statementView.setOnClickListener(v -> com.typheye.wgpro.ui.widget.WGProHint.showInfo(
                    requireContext(), "来源声明", statementDetail(statementText)));

            // 封面：有就显示（位置参考应用详情页的截图区），点击看大图
            String coverUrl = info.optString("cover_url", info.optString("icon_url", "")).trim();
            View coverScroll = root.findViewById(R.id.resource_detail_cover_scroll);
            com.google.android.material.imageview.ShapeableImageView coverView =
                    root.findViewById(R.id.resource_detail_cover);
            if (coverScroll != null && coverView != null) {
                if (coverUrl.isEmpty()) {
                    coverScroll.setVisibility(View.GONE);
                } else {
                    coverScroll.setVisibility(View.VISIBLE);
                    coverView.setShapeAppearanceModel(coverView.getShapeAppearanceModel().toBuilder()
                            .setAllCornerSizes(dp(16)).build());
                    com.typheye.wgpro.utils.ImageCache.loadBitmap(requireContext(), coverUrl,
                            bitmap -> {
                                if (bitmap != null && isAdded()) coverView.setImageBitmap(bitmap);
                            });
                    coverView.setOnClickListener(v -> viewCover());
                }
            }

            // 数据条：大小 / 下载 / 星标
            String size = humanSize(info.optLong("file_size", 0L));
            int downloads = info.optInt("download_count");
            int stars = info.optInt("collection_count");
            ((TextView) root.findViewById(R.id.resource_detail_size)).setText(size);
            ((TextView) root.findViewById(R.id.resource_detail_downloads)).setText(formatCount(downloads));
            ((TextView) root.findViewById(R.id.resource_detail_stars)).setText(formatCount(stars));
            tapMeta(root, R.id.resource_detail_cell_size, "大小",
                    "文件大小约 " + size + "，实际占用会因设备与解压方式略有差异。");
            tapMeta(root, R.id.resource_detail_cell_downloads, "下载",
                    "累计下载 " + formatCount(downloads) + " 次，统计的是通过腕管Pro发起的下载次数。");
            tapMeta(root, R.id.resource_detail_cell_stars, "星标",
                    "当前有 " + formatCount(stars) + " 位用户把此资源加入了星标。星标后可以在「我的 - 星标资源」里快速找到。");

            bindExtraInfo(root, info);
            bindMoreInfo(root, info);
        }

        /** 附加信息：读服务端 metadata（没有内容就整块隐藏）。 */
        private void bindExtraInfo(View root, JSONObject info) {
            View section = root.findViewById(R.id.resource_detail_extra_section);
            if (section == null) return;
            StringBuilder text = new StringBuilder();
            JSONObject metadata = info.optJSONObject("metadata");
            if (metadata != null) {
                java.util.Iterator<String> keys = metadata.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    String value = metadata.optString(key, "").trim();
                    if (value.isEmpty()) continue;
                    if (text.length() > 0) text.append("\n");
                    text.append(key).append("：").append(value);
                }
            }
            if (text.length() == 0) {
                section.setVisibility(View.GONE);
                return;
            }
            section.setVisibility(View.VISIBLE);
            TextView body = root.findViewById(R.id.resource_detail_extra);
            body.setText(text.toString());
            com.typheye.wgpro.utils.AppUtils.longPressToCopy(body);
        }

        /** 更多：界面上还没展示过的字段。 */
        private void bindMoreInfo(View root, JSONObject info) {
            LinearLayout list = root.findViewById(R.id.resource_detail_info_list);
            if (list == null) return;
            list.removeAllViews();
            addInfoRow(list, "分类", categoryLabel(info.optString("category", "")));
            addInfoRow(list, "文件名", info.optString("file_name", ""));
            addInfoRow(list, "创建日期", info.optString("created_at", ""));
            addInfoRow(list, "更新日期", info.optString("updated_at", ""));
        }

        private void addInfoRow(LinearLayout list, String label, String value) {
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

        private void tapMeta(View root, int id, String title, String message) {
            View view = root.findViewById(id);
            if (view == null) return;
            view.setOnClickListener(v -> com.typheye.wgpro.ui.widget.WGProHint.showInfo(
                    requireContext(), title, message));
        }

        /** 字节数 → 人类可读（702.6 KB / 12.5 MB …）。 */
        private static String humanSize(long bytes) {
            if (bytes <= 0L) return "--";
            if (bytes < 1024L) return bytes + " B";
            double kb = bytes / 1024.0;
            if (kb < 1024.0) return String.format(java.util.Locale.US, "%.1f KB", kb);
            double mb = kb / 1024.0;
            if (mb < 1024.0) return String.format(java.util.Locale.US, "%.1f MB", mb);
            return String.format(java.util.Locale.US, "%.1f GB", mb / 1024.0);
        }

        private static String statementDetail(String statement) {
            if (statement != null && statement.contains("互联网")) {
                return "该资源由平台从互联网收集整理，版权归原作者所有；如有侵权请联系我们删除。";
            }
            return "该资源由发布者本人创建并发布，内容由发布者负责。";
        }

        private static String formatCount(int value) {
            return value >= 10000 ? (value / 10000) + "万" : String.valueOf(value);
        }

        private static String categoryLabel(String category) {
            String value = category == null ? "" : category.trim();
            switch (value) {
                case "app": return "应用";
                case "video": return "视频";
                case "music": return "音乐";
                case "share": return "文件";
                default: return value;
            }
        }

        private int dp(int value) {
            return Math.round(value * getResources().getDisplayMetrics().density);
        }

        /** 星标增删后立即刷新数据条上的星标数。 */
        private void updateStarCount() {
            View view = getView();
            if (view == null) return;
            int updated = Math.max(0, resource.optInt("collection_count") + (isStarred() ? 1 : -1));
            try { resource.put("collection_count", updated); } catch (Exception ignored) { }
            ((TextView) view.findViewById(R.id.resource_detail_stars)).setText(formatCount(updated));
        }

        boolean isStarred() {
            return resource.optBoolean("is_collected");
        }

        /** 查看封面：用图片预览打开资源封面。 */
        void viewCover() {
            String cover = resource.optString("cover_url", resource.optString("icon_url", ""));
            if (cover.isEmpty()) { feedback("暂无封面", "该资源没有封面图"); return; }
            java.util.ArrayList<String> shots = new java.util.ArrayList<>();
            shots.add(cover);
            com.typheye.wgpro.ui.function.PreviewActivity.open(requireContext(), shots, 0);
        }

        void reportResource() {
            CommunityWeb.openReport(requireContext(), "resource",
                    resource.optString("id"), resource.optString("title", "资源"));
        }

        void toggleStar() {
            if (!account.isLogin()) { feedback("请先登录", "登录后才能加入星标"); return; }
            final boolean before = resource.optBoolean("is_collected");
            Map<String, String> f = new LinkedHashMap<>();
            f.put("target_type", "resource");
            f.put("target_key", resource.optString("id"));
            f.put("action", before ? "remove" : "add");
            WGProBottomSheetDialog progress = progress("正在更新星标...");
            long started = android.os.SystemClock.uptimeMillis();
            account.postV2Json("collection_action2", f, new tAccUtils.JsonCallback() {
                public void onSuccess(JSONObject j) {
                    requireActivity().runOnUiThread(() -> {
                        View v = getView();
                        if (v == null) return;
                        v.postDelayed(() -> {
                            progress.dismissForReplacement();
                            try { resource.put("is_collected", j.optBoolean("collected", !before)); }
                            catch (Exception ignored) { }
                            updateStarCount();
                            feedback("操作成功", resource.optBoolean("is_collected") ? "已加入星标" : "已移除星标");
                        }, delay(started));
                    });
                }
                public void onError(int c, String m) {
                    requireActivity().runOnUiThread(() -> {
                        View v = getView();
                        if (v == null) return;
                        v.postDelayed(() -> { progress.dismissForReplacement(); feedback("操作失败", m); }, delay(started));
                    });
                }
            });
        }

        // ---- 底栏「获取」药丸（应用详情页同款逻辑） ----

        private void setupActionBar(View root) {
            actionCard = root.findViewById(R.id.resource_detail_action);
            actionBar = root.findViewById(R.id.resource_detail_action_bar);
            actionFill = root.findViewById(R.id.resource_detail_action_fill);
            actionText = root.findViewById(R.id.resource_detail_action_text);
            actionBlur = root.findViewById(R.id.resource_detail_blur);
            com.typheye.wgpro.utils.BarBlurController.registerBar(requireContext(), actionBar);
            com.typheye.wgpro.utils.BarBlurController controller =
                    ((BaseSectionActivity) requireActivity()).blurController();
            View source = root.findViewById(R.id.resource_detail_content);
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
            final View scroll = root.findViewById(R.id.resource_detail_scroll);
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

        private void renderActionState(@Nullable java.util.List<com.typheye.wgpro.core.download.DownloadTask> tasks) {
            if (actionCard == null || actionText == null || !isAdded()) return;
            final String key = resourceId();
            com.typheye.wgpro.core.download.DownloadTask task = null;
            if (tasks != null) {
                for (com.typheye.wgpro.core.download.DownloadTask t : tasks) {
                    if (t.id.equals(key)) { task = t; break; }
                }
            }
            final com.typheye.wgpro.core.download.DownloadManager manager =
                    com.typheye.wgpro.core.download.DownloadManager.get();
            if (task == null) {
                actionText.setText("获取");
                actionCard.setEnabled(true);
                setActionFill(0);
                actionCard.setOnClickListener(v -> acquire());
                return;
            }
            final com.typheye.wgpro.core.download.DownloadTask current = task;
            switch (task.status) {
                case com.typheye.wgpro.core.download.DownloadTask.STATUS_RUNNING:
                    actionText.setText(task.progress() + "%");
                    setActionFill(task.progress());
                    actionCard.setOnClickListener(v -> manager.control(requireContext(), current.id,
                            com.typheye.wgpro.core.download.DownloadService.ACTION_PAUSE));
                    break;
                case com.typheye.wgpro.core.download.DownloadTask.STATUS_QUEUED:
                    actionText.setText("等待中");
                    setActionFill(task.progress());
                    actionCard.setOnClickListener(v -> manager.control(requireContext(), current.id,
                            com.typheye.wgpro.core.download.DownloadService.ACTION_PAUSE));
                    break;
                case com.typheye.wgpro.core.download.DownloadTask.STATUS_PAUSED:
                    actionText.setText("继续");
                    setActionFill(task.progress());
                    actionCard.setOnClickListener(v -> manager.control(requireContext(), current.id,
                            com.typheye.wgpro.core.download.DownloadService.ACTION_RESUME));
                    break;
                case com.typheye.wgpro.core.download.DownloadTask.STATUS_FAILED:
                    actionText.setText("重试");
                    setActionFill(0);
                    actionCard.setOnClickListener(v -> manager.control(requireContext(), current.id,
                            com.typheye.wgpro.core.download.DownloadService.ACTION_RESUME));
                    break;
                case com.typheye.wgpro.core.download.DownloadTask.STATUS_COMPLETED:
                    actionText.setText("操作");
                    setActionFill(0);
                    actionCard.setOnClickListener(v -> showOperationSheet());
                    break;
                default:
                    break;
            }
        }

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

        /**
         * 「获取」入口：先问服务端报价——免费或已拥有 → 直接进入保存/下载；
         * 要扣积分 → 弹「获取」弹窗，交易成功后再进入保存/下载。
         */
        private void acquire() {
            final android.content.Context context = requireContext();
            final String key = resourceId();
            if (key == null || key.isEmpty()) { launchSavePicker(); return; }
            com.typheye.wgpro.ui.widget.Points.refresh(context, null);
            com.typheye.wgpro.utils.tPointsUtils.quote(context, "resource", key,
                    new com.typheye.wgpro.utils.tPointsUtils.JsonCallback() {
                        @Override
                        public void onSuccess(@NonNull org.json.JSONObject info) {
                            int price = info.optInt("price", 0);
                            boolean owned = info.optBoolean("owned", price <= 0);
                            com.typheye.wgpro.utils.tPointsUtils.cachePrice(context, "resource", key, price, owned);
                            if (price <= 0 || owned) {
                                launchSavePicker();
                            } else {
                                showDownloadSheet(price, info.optString("stock_label", "有库存"),
                                        info.optInt("total", 0));
                            }
                        }

                        @Override
                        public void onError(int status, @NonNull String message,
                                            @Nullable org.json.JSONObject info) {
                            int cachedPrice = com.typheye.wgpro.utils.tPointsUtils.cachedPrice(context, "resource", key);
                            boolean cachedOwned = com.typheye.wgpro.utils.tPointsUtils.cachedOwned(context, "resource", key);
                            if (cachedPrice == 0 || cachedOwned) { launchSavePicker(); return; }
                            if (!isAdded()) return;
                            com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(),
                                    status == 401 ? "请先登录" : "获取失败",
                                    status == 401 ? "登录后才能获取该资源。" : message + "，请稍后再试。");
                        }
                    });
        }

        /** 「获取」弹窗：定价头部（库存 | 账户可用积分）+ 使用余额支付 / 使用兑换代码 / 找他人代付。 */
        private void showDownloadSheet(int price, String stockLabel, int availableTotal) {
            java.util.List<String> labels = new java.util.ArrayList<>();
            labels.add("使用余额支付");
            labels.add("使用兑换代码");
            labels.add("找他人代付");
            com.typheye.wgpro.ui.widget.GetSheet.show(requireContext(), price, stockLabel,
                    availableTotal, labels, (dialog, which) -> {
                        if (which == 0) {
                            confirmAndPay(price);
                        } else if (which == 1) {
                            com.typheye.wgpro.ui.widget.GetSheet.showRedeem(requireContext(),
                                    points -> acquire());
                        } else {
                            com.typheye.wgpro.ui.widget.WGProHint.showInfo(requireContext(), "找他人代付",
                                    "把该资源的分享链接发给好友，由对方为你获取。");
                        }
                    });
        }

        /** 二次确认 → 云端下发验证码 → 输入验证码完成交易 → 进入保存/下载。 */
        private void confirmAndPay(int price) {
            final android.content.Context context = requireContext();
            final String key = resourceId();
            String name = resource.optString("title", resource.optString("name", "该资源"));
            com.typheye.wgpro.ui.widget.GetSheet.showConfirm(context, name, price, () ->
                    com.typheye.wgpro.utils.tPointsUtils.verifySend(context, "resource", key,
                            new com.typheye.wgpro.utils.tPointsUtils.JsonCallback() {
                                @Override
                                public void onSuccess(@NonNull org.json.JSONObject info) {
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
                                                    @Nullable org.json.JSONObject info) {
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
                        public void onSuccess(@NonNull org.json.JSONObject info) {
                            com.typheye.wgpro.ui.widget.Points.apply(
                                    info.optInt("balance", com.typheye.wgpro.ui.widget.Points.balance),
                                    info.optInt("gift", com.typheye.wgpro.ui.widget.Points.gift));
                            int charged = info.optInt("charged", 0);
                            if (charged > 0) {
                                com.typheye.wgpro.ui.widget.WGProHint.show(context,
                                        "已扣除 " + charged + " 积分");
                            }
                            if (!isAdded()) return;
                            launchSavePicker();
                        }

                        @Override
                        public void onError(int status, @NonNull String message,
                                            @Nullable org.json.JSONObject info) {
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
                        "请先登录", "登录后才能获取该资源。");
            } else {
                com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(), "获取失败", message);
            }
        }

        /** 「操作」：已下载后的动作（保存到本机 / 在浏览器打开 / 分享给好友 / 清理该文件）。 */
        private void showOperationSheet() {
            java.util.List<String> labels = new java.util.ArrayList<>();
            labels.add("保存到本机");
            labels.add("在浏览器打开");
            labels.add("分享给好友");
            labels.add("清理该文件");
            com.typheye.wgpro.ui.widget.GetSheet.showActions(requireContext(), labels, (dialog, which) -> {
                if (which == 0) launchSavePicker();
                else if (which == 1) openInBrowser();
                else if (which == 2) shareDownloadLink();
                else {
                    String key = resourceId();
                    com.typheye.wgpro.core.download.DownloadManager.get().control(requireContext(), key,
                            com.typheye.wgpro.core.download.DownloadService.ACTION_REMOVE);
                    try {
                        java.io.File file = new java.io.File(requireContext().getFilesDir(),
                                "downloads/" + suggestedFileName());
                        if (file.exists()) file.delete();
                    } catch (Exception ignored) { }
                }
            });
        }

        /** 分享下载链接。 */
        private void shareDownloadLink() {
            String title = resource.optString("title", "资源");
            android.content.Intent share = new android.content.Intent(android.content.Intent.ACTION_SEND)
                    .setType("text/plain")
                    .putExtra(android.content.Intent.EXTRA_TEXT, title + "　下载地址："
                            + resource.optString("file_url", resource.optString("download_url", "")));
            startActivity(android.content.Intent.createChooser(share, "分享给好友"));
        }

        private void launchSavePicker() {
            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT)
                    .addCategory(Intent.CATEGORY_OPENABLE)
                    .setType("*/*")
                    .putExtra(Intent.EXTRA_TITLE, suggestedFileName());
            try {
                saveLauncher.launch(intent);
            } catch (Exception e) {
                com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(), "无法保存", "无法打开文件保存窗口");
            }
        }

        private String suggestedFileName() {
            String name = resource.optString("title", "resource");
            String clean = name.replaceAll("[\\\\/:*?\"<>|]", "_").trim();
            if (clean.isEmpty()) clean = "resource";
            String url = resource.optString("file_url", resource.optString("download_url", ""));
            int dot = url.lastIndexOf('.');
            int slash = url.lastIndexOf('/');
            if (dot > slash && dot >= 0) {
                String ext = url.substring(dot);
                if (ext.length() <= 6) return clean + ext;
            }
            return clean;
        }

        private void openInBrowser() {
            String url = resource.optString("file_url", resource.optString("download_url", ""));
            if (url.isEmpty()) { feedback("打开失败", "无法获取链接"); return; }
            startActivity(new Intent(requireContext(), com.typheye.wgpro.ui.function.WebActivity.class)
                    .putExtra("URL", url));
        }

        private void startDownload(int destination) {
            startDownload(destination, null);
        }

        private void startDownload(int destination, @Nullable android.net.Uri targetUri) {
            final String resourceId = resourceId();
            final String direct = resource.optString("file_url", resource.optString("download_url", ""));
            if (!account.isLogin() || !resourceId.matches("[a-fA-F0-9]{32}")) {
                enqueue(direct, destination, targetUri);
                return;
            }
            Map<String, String> f = new LinkedHashMap<>();
            f.put("resource_id", resourceId);
            WGProBottomSheetDialog progress = progress("正在准备下载...");
            long started = android.os.SystemClock.uptimeMillis();
            account.postV2Json("resource_download2", f, new tAccUtils.JsonCallback() {
                public void onSuccess(JSONObject j) {
                    final String url = j.optString("download_url", direct);
                    requireActivity().runOnUiThread(() -> {
                        View v = getView();
                        if (v == null) return;
                        v.postDelayed(() -> { progress.dismissForReplacement(); enqueue(url, destination, targetUri); }, delay(started));
                    });
                }
                public void onError(int c, String m) {
                    requireActivity().runOnUiThread(() -> {
                        View v = getView();
                        if (v == null) return;
                        v.postDelayed(() -> { progress.dismissForReplacement(); feedback("下载失败", m); }, delay(started));
                    });
                }
            });
        }

        private void enqueue(String url, int destination) {
            enqueue(url, destination, null);
        }

        private void enqueue(String url, int destination, @Nullable android.net.Uri targetUri) {
            if (url == null || url.isEmpty()) { feedback("下载失败", "无法获取下载地址"); return; }
            com.typheye.wgpro.core.download.DownloadTask task =
                    new com.typheye.wgpro.core.download.DownloadTask(
                            resourceId(), resource.optString("title", "未命名资源"),
                            resource.optString("cover_url", resource.optString("icon_url", "")),
                            url, resource.optString("size", ""), destination,
                            "resource", resource.toString());
            if (targetUri != null) task.targetUri = targetUri.toString();
            com.typheye.wgpro.core.download.DownloadManager.get().enqueue(requireContext(), task);
            com.typheye.wgpro.ui.widget.WGProHint.show(requireContext(), "已加入下载");
        }

        private String resourceId() {
            return resource.optString("id", resource.optString("resource_id", ""));
        }

        private WGProBottomSheetDialog progress(String message) {
            View v = View.inflate(requireContext(), R.layout.progress_dialog, null);
            ((TextView) v.findViewById(android.R.id.message)).setText(message);
            WGProBottomSheetDialog d = new WGProAlertDialogBuilder(requireContext())
                    .setTitle("处理中").setView(v).setCancelable(false).create();
            d.show();
            return d;
        }

        private long delay(long started) {
            return Math.max(0, 300L - (android.os.SystemClock.uptimeMillis() - started));
        }

        private void feedback(String title, String message) {
            new WGProAlertDialogBuilder(requireContext()).setTitle(title)
                    .setMessage(message == null || message.isEmpty() ? "请稍后重试" : message)
                    .setNegativeButton("关闭", null).show();
        }
    }
}
