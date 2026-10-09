package com.typheye.wgpro.ui.function.community;

import android.os.Bundle;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
                starred ? "移除星标" : "加入星标", "查看封面", "举报"};
        new WGProAlertDialogBuilder(this).setTitle("资源操作").setItems(actions, (dialog, which) -> {
            if (dialog instanceof WGProBottomSheetDialog) ((WGProBottomSheetDialog) dialog).dismissForReplacement();
            sectionToolbar().postDelayed(() -> {
                if (which == 0) fragment.toggleStar();
                else if (which == 1) fragment.viewCover();
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
            TextView titleView = root.findViewById(R.id.resource_detail_title);
            TextView summaryView = root.findViewById(R.id.resource_detail_summary);
            TextView descriptionView = root.findViewById(R.id.resource_detail_description);
            titleView.setText(info.optString("title", "未命名资源"));
            summaryView.setText(info.optString("summary", "暂无摘要"));
            descriptionView.setText(info.optString("description", "暂无详细介绍"));
            com.typheye.wgpro.utils.AppUtils.longPressToCopy(titleView);
            com.typheye.wgpro.utils.AppUtils.longPressToCopy(summaryView);
            com.typheye.wgpro.utils.AppUtils.longPressToCopy(descriptionView);
            JSONObject author = info.optJSONObject("author");
            String authorName = author == null ? "UID " + info.optString("uid", "--")
                    : author.optString("nick", "用户");
            ((TextView) root.findViewById(R.id.resource_detail_meta)).setText(authorName + "  ·  "
                    + info.optInt("download_count") + " 次下载  ·  " + info.optInt("collection_count") + " 次星标");
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
                actionCard.setOnClickListener(v -> showDownloadSheet());
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
                    actionText.setText("打开");
                    setActionFill(0);
                    actionCard.setOnClickListener(v -> feedback("下载完成",
                            current.localPath == null ? "文件已保存。" : current.localPath));
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

        private void showDownloadSheet() {
            CharSequence[] items = {
                    "保存到本地",
                    "在浏览器打开",
            };
            new WGProAlertDialogBuilder(requireContext()).setTitle("获取")
                    .setItems(items, (dialog, which) -> {
                        if (which == 0) launchSavePicker();
                        else openInBrowser();
                    }).show();
        }

        private void launchSavePicker() {
            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT)
                    .addCategory(Intent.CATEGORY_OPENABLE)
                    .setType("*/*")
                    .putExtra(Intent.EXTRA_TITLE, suggestedFileName());
            try {
                saveLauncher.launch(intent);
            } catch (Exception e) {
                android.widget.Toast.makeText(requireContext(), "无法打开文件保存窗口",
                        android.widget.Toast.LENGTH_SHORT).show();
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
            android.widget.Toast.makeText(requireContext(), "已加入下载", android.widget.Toast.LENGTH_SHORT).show();
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
