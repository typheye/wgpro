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
import com.typheye.wgpro.ui.function.account.UserDetailActivity;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProProgressRunner;
import com.typheye.wgpro.utils.InboxNotificationHelper;
import com.typheye.wgpro.utils.tAccUtils;

import java.util.LinkedHashMap;
import java.util.Map;

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
    private androidx.core.widget.NestedScrollView scroll;
    private boolean loadedOnce;
    /** 首次载入与实时刷新后滚动到最新一条，避免新消息停在屏幕外。 */
    private boolean scrollToNewestOnRender = true;

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater,
                                                  @Nullable ViewGroup container,
                                                  @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment_system_messages, container, false);
        account = new tAccUtils(requireContext().getApplicationContext());
        messageDb = new MessageDatabase(requireContext());
        refresh = root.findViewById(R.id.system_message_refresh);
        list = root.findViewById(R.id.system_message_list);
        scroll = root.findViewById(R.id.system_message_scroll);
        InboxNotificationHelper.cancelSystemNotifications(requireContext());
        refresh.setColorSchemeColors(requireContext().getColor(R.color.brand_primary));
        refresh.setOnRefreshListener(this::load);
        if (!loadedOnce && requireActivity() instanceof BaseSectionActivity) {
            ((BaseSectionActivity) requireActivity()).showContentLoading();
        }
        load();
        return root;
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle state) {
        super.onViewCreated(view, state);
        // 实时事件到达时立即刷新系统消息卡片（含举报动作行）
        com.typheye.wgpro.core.state.AppState.get().pushEvents().observe(
                getViewLifecycleOwner(), event -> {
                    if (event == null || !"notification".equals(event.type)) return;
                    if (!isAdded()) return;
                    scrollToNewestOnRender = true;
                    load();
                });
    }

    @Override public void onResume() {
        super.onResume();
        // 用户正在看系统消息页：期间不再为系统消息弹 Android 通知
        com.typheye.wgpro.core.state.AppState.get().setSystemMessagesVisible(true);
        if (loadedOnce) load();
    }

    @Override public void onPause() {
        com.typheye.wgpro.core.state.AppState.get().setSystemMessagesVisible(false);
        super.onPause();
    }

    private void load() {
        Map<String, String> query = new LinkedHashMap<>();
        query.put("page", "1");
        query.put("size", "50");
        account.getV2Json("notifications2", query, true, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                JSONArray items = json.optJSONArray("items");
                cacheSystemMessages(items);
                main.post(() -> render(cachedSystemJson()));
            }

            @Override public void onError(int code, @NonNull String message) {
                main.post(() -> {
                    refresh.setRefreshing(false);
                    render(cachedSystemJson());
                });
            }
        });
    }

    private void cacheSystemMessages(@Nullable JSONArray source) {
        List<MessageDatabase.SystemMessage> cache = new ArrayList<>();
        if (source != null) {
            for (int i = 0; i < source.length(); i++) {
                JSONObject item = source.optJSONObject(i);
                if (item == null) continue;
                MessageDatabase.SystemMessage message = new MessageDatabase.SystemMessage();
                message.id = item.optString("id", "");
                message.title = item.optString("title", "");
                message.content = item.optString("content", "");
                JSONObject metadata = item.optJSONObject("metadata");
                message.metadata = metadata == null ? "{}" : metadata.toString();
                message.targetType = item.optString("target_type", "");
                message.targetKey = item.optString("target_key", "");
                message.notificationType = item.optString("notification_type", "");
                message.createdAt = item.optString("created_at", "");
                message.isRead = item.optBoolean("is_read", false);
                cache.add(message);
            }
        }
        messageDb.replaceSystemMessages(account.getUid(), cache);
    }

    private JSONArray cachedSystemJson() {
        JSONArray result = new JSONArray();
        for (MessageDatabase.SystemMessage message : messageDb.getSystemMessages(account.getUid())) {
            JSONObject item = new JSONObject();
            try {
                item.put("id", message.id);
                item.put("title", message.title);
                item.put("content", message.content);
                item.put("target_type", message.targetType);
                item.put("target_key", message.targetKey);
                item.put("notification_type", message.notificationType);
                try { item.put("metadata", new JSONObject(message.metadata)); }
                catch (Exception ignored) { item.put("metadata", new JSONObject()); }
                item.put("created_at", message.createdAt);
                item.put("is_read", message.isRead);
            } catch (Exception ignored) { }
            result.put(item);
        }
        return result;
    }

    private void render(@Nullable JSONArray source) {
        if (!isAdded()) return;
        refresh.setRefreshing(false);
        list.removeAllViews();
        long clearedAt = messageDb.getSystemClearedAt(account.getUid());
        String clearedTime = clearedTimeString(clearedAt);
        List<JSONObject> items = new ArrayList<>();
        boolean hasUnread = false;
        if (source != null) {
            for (int i = 0; i < source.length(); i++) {
                JSONObject item = source.optJSONObject(i);
                if (item == null) continue;
                if (clearedAt > 0
                        && item.optString("created_at", "").compareTo(clearedTime) <= 0) continue;
                if (!item.optBoolean("is_read", false)) hasUnread = true;
                items.add(item);
            }
        }
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
        if (scrollToNewestOnRender && scroll != null) {
            scrollToNewestOnRender = false;
            // 先滚到最新一条，再撤掉加载遮罩，避免用户看到列表跳动的过程
            scroll.post(() -> {
                scroll.fullScroll(View.FOCUS_DOWN);
                scroll.postOnAnimation(this::finishInitialLoading);
            });
        } else {
            finishInitialLoading();
        }
    }

    private void finishInitialLoading() {
        if (loadedOnce) return;
        loadedOnce = true;
        if (isAdded() && requireActivity() instanceof BaseSectionActivity) {
            ((BaseSectionActivity) requireActivity()).hideContentLoading();
        }
    }

    /** 旧数据里的"（编号 #21）"不再显示在通知卡片标题上，编号在举报详情页里可见。 */
    private static String cleanTitle(String title) {
        return com.typheye.wgpro.utils.SystemMessageTitle.clean(title);
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
        card.setCardBackgroundColor(requireContext().getColor(R.color.surface_secondary));
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

        String title = cleanTitle(item.optString("title", "系统消息").trim());
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
        final ActionInfo resolved = resolveAction(item);
        if (resolved == null) return;
        String label = resolved.label;
        String hint = resolved.hint;

        // 圆角动作条：主标题 + 状态说明 + 箭头，整行带水波纹点击反馈
        LinearLayout row = new LinearLayout(requireContext());
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setMinimumHeight(dp(56));
        row.setPadding(dp(16), dp(8), dp(12), dp(8));
        row.setBackgroundResource(R.drawable.bg_system_action);
        row.setClickable(true);
        row.setFocusable(true);

        LinearLayout texts = new LinearLayout(requireContext());
        texts.setOrientation(LinearLayout.VERTICAL);
        TextView actionLabel = new TextView(requireContext());
        actionLabel.setText(label);
        actionLabel.setTextSize(15);
        actionLabel.setTextColor(requireContext().getColor(R.color.brand_primary));
        actionLabel.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        texts.addView(actionLabel);
        TextView sub = new TextView(requireContext());
        sub.setText(hint);
        sub.setTextSize(12);
        sub.setTextColor(requireContext().getColor(R.color.text_secondary));
        LinearLayout.LayoutParams subParams = new LinearLayout.LayoutParams(-2, -2);
        subParams.topMargin = dp(2);
        texts.addView(sub, subParams);
        row.addView(texts, new LinearLayout.LayoutParams(0, -2, 1f));

        android.widget.ImageView arrow = new android.widget.ImageView(requireContext());
        arrow.setImageResource(R.drawable.ic_chevron_right_vector);
        arrow.setImageTintList(android.content.res.ColorStateList.valueOf(
                requireContext().getColor(R.color.text_tertiary)));
        row.addView(arrow, new LinearLayout.LayoutParams(dp(18), dp(18)));
        row.setOnClickListener(v -> resolved.run());
        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(-1, -2);
        rowParams.topMargin = dp(16);
        body.addView(row, rowParams);
    }

    /** 动作行的描述：不同通知类型跳转到不同页面。 */
    private final class ActionInfo {
        final String label;
        final String hint;
        final Runnable action;

        ActionInfo(String label, String hint, Runnable action) {
            this.label = label;
            this.hint = hint;
            this.action = action;
        }

        void run() {
            if (!isAdded()) return;
            action.run();
        }
    }

    /**
     * 按通知类型解析动作：
     *  - report          → 举报处理页（网页）
     *  - dynamic         → 动态详情（收到评论/点赞等）
     *  - user + follow   → 粉丝列表
     *  - user            → 用户主页
     *  - message         → 对应私信会话
     *  - resource        → 资源详情（先拉取详情再打开）
     */
    private ActionInfo resolveAction(JSONObject item) {
        String targetType = item.optString("target_type", "");
        String targetKey = item.optString("target_key", "").trim();
        String notificationType = item.optString("notification_type", "");
        JSONObject metadata = item.optJSONObject("metadata");

        if ("report".equals(targetType)) {
            String reportId = metadata == null ? "" : metadata.optString("report_id", "");
            if (reportId.isEmpty()) reportId = targetKey;
            if (reportId.isEmpty()) return null;
            String status = metadata == null ? "" : metadata.optString("status", "");
            String label;
            String hint;
            if ("resolved".equalsIgnoreCase(status)) {
                label = "查看举报结果";
                hint = "举报已处理完成";
            } else if ("rejected".equalsIgnoreCase(status)) {
                label = "查看驳回原因";
                hint = "举报未通过";
            } else {
                label = "查看处理进度";
                hint = "正在核查中";
            }
            String url = "https://service.typheye.cn/site/report/index.php?id="
                    + Uri.encode(reportId) + "&status=" + Uri.encode(status);
            return new ActionInfo(label, hint, () -> startActivity(
                    new Intent(requireContext(), WebActivity.class).putExtra("URL", url)));
        }

        if ("dynamic".equals(targetType)) {
            if (targetKey.isEmpty()) return null;
            String hint = "comment".equals(notificationType) ? "查看相关评论" : "打开动态详情";
            return new ActionInfo("查看动态", hint, () -> startActivity(
                    new Intent(requireContext(), DynamicDetailActivity.class)
                            .putExtra(DynamicDetailActivity.EXTRA_DYNAMIC_ID, targetKey)));
        }

        if ("user".equals(targetType)) {
            if (targetKey.isEmpty()) return null;
            if ("follow".equals(notificationType)) {
                return new ActionInfo("查看粉丝列表", "看看是谁关注了你", () -> startActivity(
                        new Intent(requireContext(), AccountListActivity.class)
                                .putExtra(AccountListActivity.EXTRA_MODE,
                                        CloudListFragment.MODE_FOLLOWERS)
                                .putExtra(AccountListActivity.EXTRA_TITLE, "粉丝")));
            }
            return new ActionInfo("查看用户主页", "打开对方的主页", () -> startActivity(
                    new Intent(requireContext(), UserDetailActivity.class)
                            .putExtra(UserDetailActivity.EXTRA_TARGET_UID, targetKey)));
        }

        if ("message".equals(targetType)) {
            String peerUid = item.optString("actor_uid", "").trim();
            if (peerUid.isEmpty() || "0".equals(peerUid)) return null;
            String peerName = item.optString("actor_nick", "用户");
            return new ActionInfo("查看消息", "打开与对方的会话", () -> startActivity(
                    new Intent(requireContext(), ChatActivity.class)
                            .putExtra(ChatActivity.EXTRA_PEER_UID, peerUid)
                            .putExtra(ChatActivity.EXTRA_PEER_NAME, peerName)));
        }

        if ("resource".equals(targetType)) {
            if (targetKey.isEmpty()) return null;
            return new ActionInfo("查看资源", "打开资源详情", () -> openResourceDetail(targetKey));
        }

        return null;
    }

    /** 资源通知只带 id，先取详情再打开详情页。 */
    private void openResourceDetail(String resourceId) {
        Map<String, String> query = new LinkedHashMap<>();
        query.put("resource_id", resourceId);
        account.getV2Json("resource_detail2", query, false, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                JSONObject info = json.optJSONObject("info");
                main.post(() -> {
                    if (!isAdded() || info == null) return;
                    startActivity(new Intent(requireContext(), ResourceDetailActivity.class)
                            .putExtra(ResourceDetailActivity.EXTRA_RESOURCE_JSON, info.toString()));
                });
            }

            @Override public void onError(int code, @NonNull String message) {
                main.post(() -> {
                    if (isAdded()) showResult("无法打开资源", message);
                });
            }
        });
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
                messageDb.deleteSystemMessage(account.getUid(), item.optString("id", ""));
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
        messageDb.setSystemClearedAt(account.getUid(), System.currentTimeMillis());
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

    private String clearedTimeString(long millis) {
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.US)
                .format(new java.util.Date(millis));
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
