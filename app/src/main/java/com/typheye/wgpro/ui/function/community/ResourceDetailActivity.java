package com.typheye.wgpro.ui.function.community;

import android.os.Bundle;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.tAccUtils;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProBottomSheetDialog;
import org.json.JSONObject;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ResourceDetailActivity extends BaseSectionActivity {
    public static final String EXTRA_RESOURCE_JSON = "resource_json";
    @Override protected String screenTitle() { return "资源详情"; }
    @Override protected Fragment createContent() { return ResourceDetailFragment.newInstance(getIntent().getStringExtra(EXTRA_RESOURCE_JSON)); }

    public static final class ResourceDetailFragment extends Fragment {
        private JSONObject resource = new JSONObject();
        private tAccUtils account;
        static ResourceDetailFragment newInstance(String json) { ResourceDetailFragment f = new ResourceDetailFragment(); Bundle b = new Bundle(); b.putString("json", json == null ? "{}" : json); f.setArguments(b); return f; }
        @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable android.view.ViewGroup parent, @Nullable Bundle state) {
            View root = inflater.inflate(R.layout.fragment_resource_detail, parent, false);
            BaseSectionActivity host = (BaseSectionActivity) requireActivity(); host.showContentLoading();
            account = new tAccUtils(requireContext());
            JSONObject seed; try { seed = new JSONObject(requireArguments().getString("json", "{}")); } catch (Exception e) { seed = new JSONObject(); }
            String id = seed.optString("id", seed.optString("resource_id", seed.optString("target_key", "")));
            bind(root, seed);
            if (id.matches("[a-fA-F0-9]{32}")) {
                Map<String,String> q = new LinkedHashMap<>(); q.put("resource_id", id);
                account.getV2Json("resource_detail2", q, false, new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) { requireActivity().runOnUiThread(() -> { bind(root, json.optJSONObject("info")); host.hideContentLoading(); }); }
                    @Override public void onError(int code, @NonNull String msg) { requireActivity().runOnUiThread(host::hideContentLoading); }
                });
                if (account.isLogin() && requireContext().getSharedPreferences("history_preferences", android.content.Context.MODE_PRIVATE).getBoolean("enabled", true)) { Map<String,String> h = new LinkedHashMap<>(); h.put("target_type", "resource"); h.put("target_key", id); account.postV2Json("history_record2", h, new tAccUtils.JsonCallback() { public void onSuccess(JSONObject j) {} public void onError(int c, String m) {} }); }
            } else host.hideContentLoading();
            return root;
        }
        private void bind(View root, JSONObject info) {
            if (info == null) return;
            resource = info;
            ((TextView) root.findViewById(R.id.resource_detail_title)).setText(info.optString("title", "未命名资源"));
            ((TextView) root.findViewById(R.id.resource_detail_summary)).setText(info.optString("summary", "暂无摘要"));
            ((TextView) root.findViewById(R.id.resource_detail_description)).setText(info.optString("description", "暂无详细介绍"));
            JSONObject author = info.optJSONObject("author");
            String authorName = author == null ? "UID " + info.optString("uid", "--") : author.optString("nick", "用户");
            ((TextView) root.findViewById(R.id.resource_detail_meta)).setText(authorName + "  ·  "
                    + info.optInt("download_count") + " 次下载  ·  " + info.optInt("collection_count") + " 次星标");
            MaterialButton download = root.findViewById(R.id.resource_detail_download);
            download.setEnabled(info.optString("id", "").matches("[a-fA-F0-9]{32}"));
            download.setOnClickListener(v -> download());
            MaterialButton star = root.findViewById(R.id.resource_detail_star);
            boolean starred = info.optBoolean("is_collected");
            star.setText(starred ? "移除星标" : "加入星标"); star.setEnabled(account.isLogin());
            star.setOnClickListener(v -> toggleStar(root, starred));
        }
        private void toggleStar(View root, boolean before) { Map<String,String> f = new LinkedHashMap<>(); f.put("target_type", "resource"); f.put("target_key", resource.optString("id")); f.put("action", before ? "remove" : "add"); WGProBottomSheetDialog progress=progress("正在更新星标..."); long started=android.os.SystemClock.uptimeMillis(); account.postV2Json("collection_action2", f, new tAccUtils.JsonCallback() { public void onSuccess(JSONObject j) { requireActivity().runOnUiThread(() -> root.postDelayed(() -> { progress.dismissForReplacement(); try { resource.put("is_collected", j.optBoolean("collected", !before)); } catch (Exception ignored) {} bind(root, resource); feedback("操作成功", resource.optBoolean("is_collected") ? "已加入星标" : "已移除星标"); }, delay(started))); } public void onError(int c, String m) { requireActivity().runOnUiThread(() -> root.postDelayed(() -> { progress.dismissForReplacement(); feedback("操作失败", m); }, delay(started))); } }); }
        private void download() {
            if (!account.isLogin()) { openDownload(resource.optString("file_url")); return; }
            Map<String,String> f = new LinkedHashMap<>(); f.put("resource_id", resource.optString("id"));
            WGProBottomSheetDialog progress=progress("正在准备下载..."); long started=android.os.SystemClock.uptimeMillis();
            account.postV2Json("resource_download2", f, new tAccUtils.JsonCallback() {
                public void onSuccess(JSONObject j) { requireActivity().runOnUiThread(() -> requireView().postDelayed(() -> { progress.dismissForReplacement(); String url=j.optString("download_url", resource.optString("file_url")); new WGProAlertDialogBuilder(requireContext()).setTitle("下载已准备").setMessage("点击继续打开下载页面").setNegativeButton("继续",(d,w)->openDownload(url)).show(); }, delay(started))); }
                public void onError(int c, String m) { requireActivity().runOnUiThread(() -> requireView().postDelayed(() -> { progress.dismissForReplacement(); feedback("下载失败", m); }, delay(started))); }
            });
        }
        private WGProBottomSheetDialog progress(String message) { View v=View.inflate(requireContext(),R.layout.progress_dialog,null); ((TextView)v.findViewById(android.R.id.message)).setText(message); WGProBottomSheetDialog d=new WGProAlertDialogBuilder(requireContext()).setTitle("处理中").setView(v).setCancelable(false).create(); d.show(); return d; }
        private long delay(long started) { return Math.max(0,300L-(android.os.SystemClock.uptimeMillis()-started)); }
        private void feedback(String title,String message) { new WGProAlertDialogBuilder(requireContext()).setTitle(title).setMessage(message==null||message.isEmpty()?"请稍后重试":message).setNegativeButton("关闭",null).show(); }
        private void openDownload(String url) { if (!url.isEmpty()) startActivity(new Intent(requireContext(), com.typheye.wgpro.ui.function.WebActivity.class).putExtra("URL", url)); }
    }
}
