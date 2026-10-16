package com.typheye.wgpro.ui.function.account;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProBottomSheetDialog;
import com.typheye.wgpro.ui.widget.WGProProgressRunner;
import com.typheye.wgpro.ui.widget.WGProRefreshLayout;
import com.typheye.wgpro.utils.tAccUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 已登录的会话：从「我的 → 设备 → 其他设备」整段搬过来的会话卡片逻辑，
 * 入口在 设置 → 账户 → 已登录的会话（宿主复用 {@code ContactActivity}）。
 *
 * <p>卡片口径按"会话"来写：</p>
 * <ul>
 *   <li>标题：备注（没备注用设备名）</li>
 *   <li>副标题：登录方式（平台 | 客户端版本）</li>
 *   <li>角标：<b>本机</b> / <b>其他</b></li>
 *   <li>时间：登录于 xx（创建时间；缺失时退回活跃时间）</li>
 * </ul>
 * <p>交互与原来一致：点击看会话详情，长按弹「会话管理」（修改备注 / 退出登录·移除会话）。</p>
 */
public class SessionListFragment extends Fragment {

    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private LinearLayout container;
    private View empty;
    private WGProRefreshLayout refresh;
    private NestedScrollView scroll;
    private JSONArray sessions = new JSONArray();
    private boolean loading;
    private boolean loaded;
    private String signature = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
                             @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment_session_list, parent, false);
        container = root.findViewById(R.id.container_sessions);
        empty = root.findViewById(R.id.session_empty);
        ((TextView) empty.findViewById(R.id.stream_empty_title)).setText("暂无已登录的会话");
        ((TextView) empty.findViewById(R.id.stream_empty_description))
                .setText("使用同一 Typheye 账户登录的设备与浏览器会显示在这里。");
        refresh = root.findViewById(R.id.session_refresh);
        scroll = root.findViewById(R.id.session_scroll);
        refresh.setOnChildScrollUpCallback((layout, child) -> scroll.getScrollY() > 0);
        refresh.setOnRefreshListener(this::loadSessions);
        refresh.post(() -> refresh.setRefreshing(true));
        loadSessions();
        return root;
    }

    private void loadSessions() {
        if (!isAdded() || loading) {
            if (refresh != null) refresh.setRefreshing(false);
            return;
        }
        if (!new tAccUtils(requireContext()).isLogin()) {
            if (refresh != null) refresh.setRefreshing(false);
            render();
            return;
        }
        loading = true;
        Map<String, String> query = new LinkedHashMap<>();
        query.put("page", "1");
        query.put("size", "20");
        new tAccUtils(requireContext()).getV2JsonFresh("sessions2", query, true,
                new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        JSONArray items = json.optJSONArray("items");
                        mainHandler.post(() -> {
                            if (!isAdded()) return;
                            loading = false;
                            sessions = items == null ? new JSONArray() : items;
                            loaded = true;
                            render();
                            if (refresh != null) refresh.setRefreshing(false);
                        });
                    }

                    @Override public void onError(int code, @NonNull String message) {
                        mainHandler.post(() -> {
                            if (!isAdded()) return;
                            loading = false;
                            if (code == 401) sessions = new JSONArray();
                            loaded = true;
                            render();
                            if (refresh != null) refresh.setRefreshing(false);
                        });
                    }
                });
    }

    private void render() {
        if (!isAdded() || container == null) return;
        LinearLayout staged = new LinearLayout(requireContext());
        staged.setOrientation(LinearLayout.VERTICAL);
        StringBuilder next = new StringBuilder();
        int visible = 0;
        for (int i = 0; i < sessions.length(); i++) {
            JSONObject session = sessions.optJSONObject(i);
            if (session == null || !isVisibleSession(session)) continue;
            next.append(session.optString("id", session.optString("session_id"))).append('|')
                    .append(session.optString("remark")).append('|')
                    .append(session.optBoolean("is_current")).append(';');
            addSessionCard(session, staged);
            visible++;
        }
        String nextSignature = next.toString();
        if (!nextSignature.equals(signature) || container.getChildCount() != staged.getChildCount()) {
            container.removeAllViews();
            while (staged.getChildCount() > 0) {
                View child = staged.getChildAt(0);
                staged.removeView(child);
                container.addView(child);
            }
            signature = nextSignature;
        }
        // 加载期间也用标准空态（view_stream_empty）占位，别让内容区空着
        boolean showEmpty = visible == 0;
        if (showEmpty) {
            boolean settled = loaded && !loading;
            ((TextView) empty.findViewById(R.id.stream_empty_title))
                    .setText(settled ? "暂无已登录的会话" : "加载中…");
            ((TextView) empty.findViewById(R.id.stream_empty_description))
                    .setText(settled
                            ? "使用同一 Typheye 账户登录的设备与浏览器会显示在这里。" : "");
        }
        empty.setVisibility(showEmpty ? View.VISIBLE : View.GONE);
    }

    private void addSessionCard(JSONObject session, LinearLayout target) {
        View card = getLayoutInflater().inflate(R.layout.item_device, target, false);
        String name = displayName(session);
        boolean current = session.optBoolean("is_current");
        String platform = session.optString("platform", "").trim();
        String version = session.optString("client_version", "").trim();
        String loginWay = platform + (version.isEmpty() ? "" : " | " + version);
        ((TextView) card.findViewById(R.id.text_device_initial)).setText(firstCharacter(name));
        ((TextView) card.findViewById(R.id.text_device_model)).setText(name);
        ((TextView) card.findViewById(R.id.text_device_version))
                .setText(loginWay.isEmpty() ? "登录方式未知" : loginWay);
        TextView status = card.findViewById(R.id.text_device_status);
        status.setText(current ? "本机" : "其他");
        status.setBackgroundResource(current ? R.drawable.bg_device_status_connected
                : R.drawable.bg_device_status_disconnected);
        // 卡片统一显示「活跃于 xx」：优先用最近活跃时间，没有再退回登录时间
        // （原来的「登录于 created_at」会一直显示很久以前的登录时刻，看起来像"设备没在用"）
        String lastSeen = session.optString("last_seen_at", "");
        String signedInAt = session.optString("created_at", "");
        String timeText;
        if (!lastSeen.isEmpty()) {
            timeText = "活跃于 " + relativeCloudTime(lastSeen);
        } else if (!signedInAt.isEmpty()) {
            timeText = "活跃于 " + relativeCloudTime(signedInAt);
        } else {
            timeText = "活跃时间未知";
        }
        ((TextView) card.findViewById(R.id.text_device_since)).setText(timeText);
        card.setOnClickListener(v -> showSessionDetail(session));
        card.setOnLongClickListener(v -> {
            showSessionMenu(session);
            return true;
        });
        target.addView(card);
    }

    private static String displayName(JSONObject session) {
        String remark = session.optString("remark", "").trim();
        if ("null".equals(remark)) remark = "";
        String device = session.optString("device", "").trim();
        if (!remark.isEmpty()) return remark;
        if (!device.isEmpty()) return device;
        return "web".equalsIgnoreCase(session.optString("session_type", "")) ? "网页会话" : "未知设备";
    }

    private boolean isVisibleSession(JSONObject session) {
        String type = session.optString("session_type", "");
        String id = session.optString("id", "");
        // 挂在原生会话下的 Web 登录占位行不算独立会话（老版本服务端可能还会返回）
        return !("web".equalsIgnoreCase(type)
                && (!session.optString("source_session_id", "").isEmpty()
                || !session.optString("parent_session_id", "").isEmpty()
                || session.optBoolean("is_placeholder")
                || id.startsWith("placeholder:")));
    }

    private void showSessionDetail(JSONObject session) {
        View content = getLayoutInflater().inflate(R.layout.sheet_device_detail, null, false);
        String name = displayName(session);
        boolean current = session.optBoolean("is_current");
        String platform = session.optString("platform", "");
        ((TextView) content.findViewById(R.id.sheet_device_initial)).setText(firstCharacter(name));
        ((TextView) content.findViewById(R.id.sheet_device_name)).setText(name);
        ((TextView) content.findViewById(R.id.sheet_device_status_version))
                .setText((current ? "本机" : "其他") + (platform.isEmpty() ? "" : " | " + platform));
        content.findViewById(R.id.sheet_device_actions).setVisibility(View.VISIBLE);
        ((TextView) content.findViewById(R.id.sheet_device_action_primary_label)).setText("查看详细信息");
        ((TextView) content.findViewById(R.id.sheet_device_action_secondary_label)).setText("显示更多操作");
        StringBuilder detail = new StringBuilder();
        appendDetail(detail, "设备", session.optString("device"));
        appendDetail(detail, "登录方式", session.optString("platform"));
        appendDetail(detail, "客户端版本", session.optString("client_version"));
        appendDetail(detail, "位置", session.optString("location"));
        appendDetail(detail, "IP", session.optString("ip"));
        appendDetail(detail, "登录时间", session.optString("created_at"));
        appendDetail(detail, "最后活跃", session.optString("last_seen_at"));
        WGProBottomSheetDialog dialog = new WGProAlertDialogBuilder(requireContext())
                .setTitle("会话详情").setView(content).setNegativeButton("关闭", null).create();
        content.findViewById(R.id.sheet_device_transfer).setOnClickListener(v -> {
            dialog.dismissForReplacement();
            new WGProAlertDialogBuilder(requireContext()).setTitle("详细信息")
                    .setMessage(detail.length() == 0 ? "暂无更多会话信息" : detail.toString())
                    .setNegativeButton("关闭", null).show();
        });
        content.findViewById(R.id.sheet_device_logs).setOnClickListener(v -> {
            dialog.dismissForReplacement();
            showSessionMenu(session);
        });
        dialog.show();
    }

    private static void appendDetail(StringBuilder target, String label, String value) {
        if (value == null || value.trim().isEmpty()) return;
        if (target.length() > 0) target.append('\n');
        target.append(label).append('：').append(value.trim());
    }

    private void showSessionMenu(JSONObject session) {
        boolean current = session.optBoolean("is_current");
        new WGProAlertDialogBuilder(requireContext()).setTitle("会话管理")
                .setItems(new CharSequence[]{"修改备注", current ? "退出登录" : "移除该会话"},
                        (dialog, which) -> {
                            if (which == 0) showSessionNoteDialog(session);
                            else confirmRevokeSession(session);
                        }).show();
    }

    private void showSessionNoteDialog(JSONObject session) {
        View content = getLayoutInflater().inflate(R.layout.dialog_edittext, null, false);
        TextInputLayout layout = content.findViewById(R.id.textInputLayout);
        TextInputEditText input = content.findViewById(R.id.editText);
        layout.setHint("会话备注");
        layout.setCounterEnabled(true);
        layout.setCounterMaxLength(40);
        input.setFilters(new InputFilter[]{new InputFilter.LengthFilter(40)});
        String initialRemark = session.optString("remark", "");
        if ("null".equals(initialRemark)) initialRemark = "";
        input.setText(initialRemark);
        input.setSelection(input.length());
        input.setSingleLine(true);
        WGProBottomSheetDialog dialog = new WGProAlertDialogBuilder(requireContext())
                .setTitle("修改会话备注").setView(content).setNegativeButton("取消", null)
                .setPositiveButton("保存", null).create();
        final boolean[] submitted = {false};
        dialog.setOnShowListener(ignored ->
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
                    if (submitted[0]) return;
                    String value = input.getText() == null ? "" : input.getText().toString().trim();
                    boolean current = session.optBoolean("is_current", false);
                    String sessionId = session.optString("session_id", "").trim();
                    String recordId = session.optString("id", "").trim();
                    boolean web = recordId.startsWith("web:")
                            || "web".equalsIgnoreCase(session.optString("session_type", ""));
                    if (web) {
                        if (!recordId.matches("(?i)web:\\d+")) {
                            layout.setError("云端未返回可用的会话标识，请刷新后重试");
                            return;
                        }
                    } else if (!current && !sessionId.matches("(?i)[0-9a-f]{32}")) {
                        layout.setError("云端未返回可用的会话标识，请刷新后重试");
                        return;
                    }
                    submitted[0] = true;
                    dialog.dismissForReplacement();
                    mainHandler.postDelayed(() -> saveSessionRemark(session, value, current,
                            sessionId, web, recordId), 40L);
                }));
        dialog.show();
    }

    private void saveSessionRemark(JSONObject session, String remark, boolean current,
                                   String sessionId, boolean web, String recordId) {
        if (!isAdded()) return;
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("remark", remark);
        if (web) {
            fields.put("target_record_id", recordId);
        } else if (!current) {
            fields.put("target_session_id", sessionId);
        }
        final String target = remark;
        WGProProgressRunner.run(this, "保存中", "正在保存备注...", completion ->
                new tAccUtils(requireContext()).postV2Json("session_remark2", fields,
                        new tAccUtils.JsonCallback() {
                            @Override public void onSuccess(@NonNull JSONObject json) {
                                completion.success(json);
                            }

                            @Override public void onError(int code, @NonNull String message) {
                                completion.error(code, message);
                            }
                        }), new WGProProgressRunner.Callback() {
            @Override public void success(@NonNull JSONObject json) {
                String saved = applySessionRemarkResult(session, json, target);
                new WGProAlertDialogBuilder(requireContext()).setTitle("备注已更新")
                        .setMessage(saved.isEmpty() ? "已恢复为自动设备名称。" : "会话备注已保存。")
                        .setNegativeButton("关闭", null).show();
                loadSessions();
            }

            @Override public void error(int code, @NonNull String message) {
                new WGProAlertDialogBuilder(requireContext()).setTitle("保存失败")
                        .setMessage(message == null || message.trim().isEmpty() ? "请稍后重试" : message)
                        .setNegativeButton("关闭", null).show();
            }
        });
    }

    private String applySessionRemarkResult(JSONObject session, JSONObject json, String fallback) {
        String remark = json.isNull("remark") ? fallback : json.optString("remark", fallback);
        String serverSessionId = json.isNull("session_id") ? "" : json.optString("session_id", "");
        String serverRecordId = json.isNull("record_id") ? "" : json.optString("record_id", "");
        try {
            session.put("remark", remark);
            if (!serverSessionId.isEmpty()) session.put("session_id", serverSessionId);
            if (!serverRecordId.isEmpty()) session.put("id", serverRecordId);
        } catch (Exception ignored) { }
        render();
        return remark;
    }

    private void confirmRevokeSession(JSONObject session) {
        String id = session.optString("id",
                session.optString("session_record_id", session.optString("record_id", ""))).trim();
        if (id.isEmpty()) return;
        boolean current = session.optBoolean("is_current");
        new WGProAlertDialogBuilder(requireContext())
                .setTitle(current ? "确认退出登录？" : "移除该会话？")
                .setMessage(current ? "当前 Typheye 账户会立即退出登录。"
                        : "该会话的登录状态将立即失效，需要重新登录。")
                .setNegativeButton("取消", null)
                .setPositiveButton(current ? "退出" : "移除", (dialog, which) ->
                        revokeSession(id, current))
                .show();
    }

    private void revokeSession(String id, boolean current) {
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("session_record_id", id);
        tAccUtils account = new tAccUtils(requireContext());
        account.postV2Json("session_revoke2", fields, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                if (current || json.optBoolean("revoked_current")) account.logout();
                mainHandler.post(() -> {
                    if (!isAdded()) return;
                    removeSessionLocally(id);
                    render();
                    loadSessions();
                });
            }

            @Override public void onError(int code, @NonNull String message) {
                mainHandler.post(() -> {
                    if (!isAdded()) return;
                    new WGProAlertDialogBuilder(requireContext()).setTitle("操作失败")
                            .setMessage(message).setNegativeButton("关闭", null).show();
                });
            }
        });
    }

    /** 只摘掉这一条：整表清空会让"空提示 ↔ 卡片"来回闪。 */
    private void removeSessionLocally(String recordId) {
        JSONArray next = new JSONArray();
        for (int i = 0; i < sessions.length(); i++) {
            JSONObject session = sessions.optJSONObject(i);
            if (session == null) continue;
            String id = session.optString("id",
                    session.optString("session_record_id", session.optString("record_id", "")));
            if (!recordId.equals(id)) next.put(session);
        }
        sessions = next;
    }

    private static String firstCharacter(String value) {
        String trimmed = value == null ? "" : value.trim();
        if (trimmed.isEmpty()) return "?";
        return trimmed.substring(0, 1).toUpperCase();
    }

    private static String relativeCloudTime(String value) {
        String normalized = value == null ? "" : value.replace('T', ' ');
        int zone = normalized.indexOf('Z');
        if (zone >= 0) normalized = normalized.substring(0, zone);
        int decimal = normalized.indexOf('.');
        if (decimal >= 0) normalized = normalized.substring(0, decimal);
        try {
            long time = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                    java.util.Locale.getDefault()).parse(normalized).getTime();
            return relativeTime(time);
        } catch (Exception ignored) {
            return value;
        }
    }

    private static String relativeTime(long timestamp) {
        if (timestamp <= 0L) return "未知时间";
        long seconds = Math.max(0L, (System.currentTimeMillis() - timestamp) / 1000L);
        if (seconds < 60) return "刚刚";
        if (seconds < 3600) return seconds / 60 + " 分钟前";
        if (seconds < 86400) return seconds / 3600 + " 小时前";
        if (seconds < 2592000L) return seconds / 86400 + " 天前";
        return new java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
                .format(new java.util.Date(timestamp));
    }
}
