package com.typheye.wgpro.ui.function.community;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.card.MaterialCardView;
import com.typheye.wgpro.R;
import com.typheye.wgpro.data.MessageDatabase;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProProgressRunner;
import com.typheye.wgpro.utils.InboxNotificationHelper;
import com.typheye.wgpro.utils.tAccUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/** 系统消息详情：卡片式消息流，不带输入框和发送按钮。 */
public class SystemMessageDetailFragment extends Fragment {
    private final Handler main = new Handler(Looper.getMainLooper());
    private tAccUtils account;
    private MessageDatabase messageDb;
    private SwipeRefreshLayout refresh;
    private LinearLayout list;
    private boolean loadedOnce;
    private long latestLoadedId;

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater,
                                                  @Nullable ViewGroup container,
                                                  @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment_system_messages, container, false);
        account = new tAccUtils(requireContext().getApplicationContext());
        messageDb = new MessageDatabase(requireContext());
        refresh = root.findViewById(R.id.system_message_refresh);
        list = root.findViewById(R.id.system_message_list);
        InboxNotificationHelper.cancelSystemNotifications(requireContext());
        refresh.setColorSchemeColors(requireContext().getColor(R.color.brand_primary));
        refresh.setOnRefreshListener(this::load);
        if (!loadedOnce && requireActivity() instanceof BaseSectionActivity) {
            ((BaseSectionActivity) requireActivity()).showContentLoading();
        }
        load();
        return root;
    }

    @Override public void onResume() {
        super.onResume();
        if (loadedOnce) load();
    }

    private void load() {
        Map<String, String> query = new LinkedHashMap<>();
        query.put("page", "1");
        query.put("size", "50");
        account.getV2JsonFresh("notifications2", query, true, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                JSONArray items = json.optJSONArray("items");
                main.post(() -> render(items));
            }

            @Override public void onError(int code, @NonNull String message) {
                main.post(() -> {
                    refresh.setRefreshing(false);
                    if (!loadedOnce) render(new JSONArray());
                });
            }
        });
    }

    private void render(@Nullable JSONArray source) {
        if (!isAdded()) return;
        refresh.setRefreshing(false);
        list.removeAllViews();
        long clearedId = messageDb.getSystemClearedBefore(account.getUid());
        List<JSONObject> items = new ArrayList<>();
        boolean hasUnread = false;
        long maxId = latestLoadedId;
        if (source != null) {
            for (int i = 0; i < source.length(); i++) {
                JSONObject item = source.optJSONObject(i);
                if (item == null) continue;
                long itemId = parseId(item.optString("id", "0"));
                if (itemId > maxId) maxId = itemId;
                if (itemId <= clearedId) continue;
                if (!item.optBoolean("is_read", false)) hasUnread = true;
                items.add(item);
            }
        }
        latestLoadedId = maxId;
        Collections.reverse(items);
        String previousDate = "";
        for (JSONObject item : items) {
            String date = dateLabel(item.optString("created_at", ""));
            if (!date.equals(previousDate)) {
                list.addView(dateHeader(date));
                previousDate = date;
            }
            list.addView(messageCard(item));
        }
        if (items.isEmpty()) list.addView(emptyState());
        if (hasUnread) markAllRead();
        finishInitialLoading();
    }

    private void finishInitialLoading() {
        if (loadedOnce) return;
        loadedOnce = true;
        if (isAdded() && requireActivity() instanceof BaseSectionActivity) {
            ((BaseSectionActivity) requireActivity()).hideContentLoading();
        }
    }

    private View dateHeader(String text) {
        TextView view = new TextView(requireContext());
        view.setText(text);
        view.setTextSize(12);
        view.setTextColor(requireContext().getColor(R.color.text_tertiary));
        view.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.topMargin = dp(12);
        params.bottomMargin = dp(8);
        view.setLayoutParams(params);
        return view;
    }

    private View messageCard(JSONObject item) {
        MaterialCardView card = new MaterialCardView(requireContext());
        card.setCardBackgroundColor(requireContext().getColor(R.color.surface_primary));
        card.setRadius(dp(16));
        card.setCardElevation(0f);
        card.setStrokeWidth(0);
        card.setClickable(true);
        card.setFocusable(true);
        card.setRippleColor(android.content.res.ColorStateList.valueOf(
                requireContext().getColor(R.color.brand_soft)));

        LinearLayout body = new LinearLayout(requireContext());
        body.setOrientation(LinearLayout.VERTICAL);
        body.setPadding(dp(16), dp(16), dp(16), dp(16));

        String title = item.optString("title", "系统消息").trim();
        if (!title.isEmpty()) {
            TextView titleView = new TextView(requireContext());
            titleView.setText(title);
            titleView.setTextSize(17);
            titleView.setTextColor(requireContext().getColor(R.color.text_primary));
            titleView.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            body.addView(titleView);
        }

        String content = item.optString("content", "").trim();
        if (!content.isEmpty()) {
            TextView contentView = new TextView(requireContext());
            contentView.setText(content);
            contentView.setTextSize(15);
            contentView.setTextColor(requireContext().getColor(R.color.text_primary));
            contentView.setLineSpacing(0f, 1.12f);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
            params.topMargin = dp(8);
            body.addView(contentView, params);
        }

        addActionRow(body, item);
        card.addView(body);

        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(-1, -2);
        cardParams.bottomMargin = dp(4);
        card.setLayoutParams(cardParams);
        card.setOnLongClickListener(v -> {
            showMessageMenu(item, card);
            return true;
        });
        return card;
    }

    private void addActionRow(LinearLayout body, JSONObject item) {
        if (!"report".equals(item.optString("target_type", ""))) return;
        JSONObject metadata = item.optJSONObject("metadata");
        String reportId = metadata == null ? "" : metadata.optString("report_id", "");
        if (reportId.isEmpty()) reportId = item.optString("target_key", "");
        if (reportId.isEmpty()) return;
        String status = metadata == null ? "" : metadata.optString("status", "");
        String label;
        if ("resolved".equalsIgnoreCase(status)) label = "查看举报结果";
        else if ("rejected".equalsIgnoreCase(status)) label = "查看驳回原因";
        else label = "查看处理进度";
        String url = "https://service.typheye.cn/site/report/index.php?id="
                + Uri.encode(reportId) + "&status=" + Uri.encode(status);

        View divider = new View(requireContext());
        divider.setBackgroundColor(requireContext().getColor(R.color.outline_soft));
        LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(-1, dp(1));
        dividerParams.topMargin = dp(14);
        body.addView(divider, dividerParams);

        LinearLayout row = new LinearLayout(requireContext());
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setMinimumHeight(dp(48));
        row.setPadding(0, dp(4), 0, 0);
        row.setClickable(true);
        row.setFocusable(true);
        TextView action = new TextView(requireContext());
        action.setText(label);
        action.setTextSize(15);
        action.setTextColor(requireContext().getColor(R.color.brand_primary));
        action.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        row.addView(action, new LinearLayout.LayoutParams(0, -2, 1f));
        android.widget.ImageView arrow = new android.widget.ImageView(requireContext());
        arrow.setImageResource(R.drawable.ic_chevron_right_vector);
        arrow.setImageTintList(android.content.res.ColorStateList.valueOf(
                requireContext().getColor(R.color.text_tertiary)));
        row.addView(arrow, new LinearLayout.LayoutParams(dp(22), dp(22)));
        row.setOnClickListener(v -> startActivity(new Intent(requireContext(), WebActivity.class)
                .putExtra("URL", url)));
        body.addView(row, new LinearLayout.LayoutParams(-1, -2));
    }

    private View emptyState() {
        View state = LayoutInflater.from(requireContext())
                .inflate(R.layout.view_stream_empty, list, false);
        state.setVisibility(View.VISIBLE);
        ((TextView) state.findViewById(R.id.stream_empty_title)).setText("暂无系统消息");
        ((TextView) state.findViewById(R.id.stream_empty_description))
                .setText("系统通知和举报处理结果会显示在这里。");
        return state;
    }

    private void showMessageMenu(JSONObject item, View card) {
        if (!isAdded()) return;
        new WGProAlertDialogBuilder(requireContext()).setTitle("消息操作")
                .setItems(new CharSequence[]{"消息详情", "删除"}, (dialog, which) -> {
                    if (which == 0) showMessageDetail(item);
                    else confirmDelete(item, card);
                }).show();
    }

    private void showMessageDetail(JSONObject item) {
        StringBuilder detail = new StringBuilder();
        detail.append("标题：").append(item.optString("title", "系统消息")).append('\n');
        detail.append("时间：").append(item.optString("created_at", "未知")).append('\n');
        detail.append("消息 ID：").append(item.optString("id", "未知")).append('\n');
        detail.append("内容：").append(item.optString("content", ""));
        new WGProAlertDialogBuilder(requireContext()).setTitle("消息详情")
                .setMessage(detail.toString())
                .setNegativeButton("关闭", null).show();
    }

    private void confirmDelete(JSONObject item, View card) {
        new WGProAlertDialogBuilder(requireContext())
                .setTitle("删除系统消息？")
                .setMessage("删除后该消息只会从当前账户的通知记录中移除。")
                .setNegativeButton("取消", null)
                .setPositiveButton("删除", (dialog, which) -> {
                    if (dialog instanceof com.typheye.wgpro.ui.widget.WGProBottomSheetDialog) {
                        ((com.typheye.wgpro.ui.widget.WGProBottomSheetDialog) dialog)
                                .dismissForReplacement();
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(
                            () -> deleteMessage(item, card), 40L);
                }).show();
    }

    private void deleteMessage(JSONObject item, View card) {
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("notification_id", item.optString("id", ""));
        fields.put("action", "delete");
        WGProProgressRunner.run(this, "删除中", "正在删除系统消息...", completion ->
                account.postV2Json("notification_state2", fields, new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        completion.success(json);
                    }

                    @Override public void onError(int code, @NonNull String message) {
                        completion.error(code, message);
                    }
                }), new WGProProgressRunner.Callback() {
            @Override public void success(@NonNull JSONObject json) {
                load();
                showResult("已删除", "该消息已从当前账户的通知记录中移除。");
            }

            @Override public void error(int code, @NonNull String message) {
                showResult("删除失败", message);
            }
        });
    }

    public void showMoreMenu() {
        if (!isAdded()) return;
        new WGProAlertDialogBuilder(requireContext()).setTitle("更多")
                .setItems(new CharSequence[]{"清空记录", "关于“系统消息”"}, (dialog, which) -> {
                    if (which == 0) confirmClearLocal();
                    else showAbout();
                }).show();
    }

    private void confirmClearLocal() {
        new WGProAlertDialogBuilder(requireContext())
                .setTitle("清空系统消息记录？")
                .setMessage("仅清除当前设备上的系统消息显示记录，不会删除云端通知。")
                .setNegativeButton("取消", null)
                .setPositiveButton("清空", (dialog, which) -> {
                    if (dialog instanceof com.typheye.wgpro.ui.widget.WGProBottomSheetDialog) {
                        ((com.typheye.wgpro.ui.widget.WGProBottomSheetDialog) dialog)
                                .dismissForReplacement();
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(this::clearLocal, 40L);
                }).show();
    }

    private void clearLocal() {
        messageDb.setSystemClearedBefore(account.getUid(), latestLoadedId);
        messageDb.setSystemUnreadOverride(account.getUid(), false);
        list.removeAllViews();
        list.addView(emptyState());
        showResult("已清空", "当前设备上的系统消息记录已清空。");
    }

    private void showAbout() {
        new WGProAlertDialogBuilder(requireContext())
                .setTitle("关于“系统消息”")
                .setMessage("系统消息用于接收举报受理与处理结果、平台通知等官方信息。"
                        + "举报消息会显示独立标题，处理进度和结果也会持续同步到这里。")
                .setNegativeButton("关闭", null).show();
    }

    private void markAllRead() {
        messageDb.setSystemUnreadOverride(account.getUid(), false);
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("action", "read_all");
        account.postV2Json("notification_state2", fields, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) { }
            @Override public void onError(int code, @NonNull String message) { }
        });
    }

    private void showResult(String title, String message) {
        if (!isAdded()) return;
        new WGProAlertDialogBuilder(requireContext()).setTitle(title).setMessage(message)
                .setNegativeButton("关闭", null).show();
    }

    private long parseId(String value) {
        try { return Long.parseLong(value); } catch (Exception ignored) { return 0L; }
    }

    private String dateLabel(String value) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(value);
            return new SimpleDateFormat("yyyy年M月d日 HH:mm", Locale.getDefault()).format(date);
        } catch (Exception ignored) {
            return value;
        }
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
