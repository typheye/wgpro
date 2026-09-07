package com.typheye.wgpro.ui.main.mainFragments;

import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.typheye.wgpro.R;
import com.typheye.wgpro.core.xms.UIParams;
import com.typheye.wgpro.data.DeviceDatabase;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.ui.function.device.AddDeviceActivity;
import com.typheye.wgpro.ui.main.MainActivity;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProBottomSheetDialog;
import com.typheye.wgpro.utils.tAccUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DeviceFragment extends Fragment {
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private LinearLayout wearable;
    private LinearLayout other;
    private View wearableEmpty;
    private View otherEmpty;
    private View detected;
    private JSONArray cloudSessions = new JSONArray();
    private boolean cloudSessionsLoading;
    private boolean wearableLoaded;
    private boolean otherLoaded;
    private SwipeRefreshLayout wearableRefresh;
    private SwipeRefreshLayout otherRefresh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
                             @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment_device, parent, false);
        wearable = root.findViewById(R.id.container_wearable_devices);
        other = root.findViewById(R.id.container_other_devices);
        wearableEmpty = root.findViewById(R.id.card_wearable_empty);
        otherEmpty = root.findViewById(R.id.card_other_empty);
        ((TextView) wearableEmpty.findViewById(R.id.stream_empty_title)).setText("还没有穿戴设备");
        ((TextView) wearableEmpty.findViewById(R.id.stream_empty_description))
                .setText("连接小米手表或手环后，可在这里添加并集中管理。");
        ((TextView) otherEmpty.findViewById(R.id.stream_empty_title)).setText("暂无其他设备");
        ((TextView) otherEmpty.findViewById(R.id.stream_empty_description))
                .setText("使用同一 Typheye 账户登录的设备会显示在这里。");
        detected = root.findViewById(R.id.card_detected_device);
        wearableRefresh = root.findViewById(R.id.device_page_wearable);
        otherRefresh = root.findViewById(R.id.device_page_other);
        int accent = requireContext().getColor(R.color.brand_primary);
        wearableRefresh.setColorSchemeColors(accent);
        otherRefresh.setColorSchemeColors(accent);
        wearableRefresh.setOnRefreshListener(() -> {
            wearableLoaded = false;
            refresh();
            mainHandler.postDelayed(() -> {
                if (!isAdded()) return;
                wearableLoaded = true;
                refresh();
                wearableRefresh.setRefreshing(false);
            }, 400L);
        });
        otherRefresh.setOnRefreshListener(() -> {
            otherLoaded = false;
            refresh();
            cloudSessionsLoading = false;
            loadCloudSessions();
        });
        wearableRefresh.post(() -> wearableRefresh.setRefreshing(true));
        otherRefresh.post(() -> otherRefresh.setRefreshing(true));
        mainHandler.postDelayed(() -> {
            if (!isAdded()) return;
            wearableLoaded = true;
            refresh();
            wearableRefresh.setRefreshing(false);
        }, 400L);
        View dismissDetected = root.findViewById(R.id.button_dismiss_detected);
        detected.setOnClickListener(v ->
                startActivity(new Intent(requireContext(), AddDeviceActivity.class)));
        dismissDetected.setOnClickListener(v -> {
            UIParams params = MainActivity.current_params;
            if (params != null && params.connected_device_id != null) {
                requireContext().getSharedPreferences("device_preferences", Context.MODE_PRIVATE)
                        .edit().putString("dismissed_detected_device_id", params.connected_device_id).apply();
            }
            detected.setVisibility(View.GONE);
        });
        setupPages(root);
        return root;
    }

    private void setupPages(View root) {
        MaterialButtonToggleGroup segments = root.findViewById(R.id.device_segments);
        ViewPager2 pager = root.findViewById(R.id.device_pager);
        FrameLayout pool = root.findViewById(R.id.device_page_pool);
        List<View> pages = Arrays.asList(root.findViewById(R.id.device_page_wearable),
                root.findViewById(R.id.device_page_other));
        for (View page : pages) pool.removeView(page);
        pager.setAdapter(new LocalPageAdapter(pages));
        pager.setOffscreenPageLimit(1);
        int[] segmentIds = {R.id.device_segment_wearable, R.id.device_segment_other};
        segments.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (!isChecked) return;
            for (int index = 0; index < segmentIds.length; index++) {
                if (segmentIds[index] == checkedId && pager.getCurrentItem() != index) {
                    pager.setCurrentItem(index, true);
                    break;
                }
            }
        });
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override public void onPageSelected(int position) {
                if (position >= 0 && position < segmentIds.length
                        && segments.getCheckedButtonId() != segmentIds[position]) {
                    segments.check(segmentIds[position]);
                }
            }
        });
    }

    @Override public void onResume() {
        super.onResume();
        refresh();
        loadCloudSessions();
    }
    public void updateUI(UIParams ignored) { refresh(); }

    private void refresh() {
        if (!isAdded() || wearable == null || other == null) return;
        try (DeviceDatabase db = new DeviceDatabase(requireContext());
             Cursor wearableCursor = db.all("xiaomi");
             Cursor otherCursor = db.allExcept("xiaomi")) {
            wearable.removeAllViews();
            other.removeAllViews();
            boolean hasWearable = false;
            boolean hasOther = false;
            while (wearableCursor.moveToNext()) {
                hasWearable = true;
                addCard(wearableCursor, wearable);
            }
            while (otherCursor.moveToNext()) {
                hasOther = true;
                addCard(otherCursor, other);
            }
            for (int index = 0; index < cloudSessions.length(); index++) {
                JSONObject session = cloudSessions.optJSONObject(index);
                if (session != null) {
                    hasOther = true;
                    addSessionCard(session);
                }
            }
            wearableEmpty.setVisibility(wearableLoaded && !hasWearable ? View.VISIBLE : View.GONE);
            otherEmpty.setVisibility(otherLoaded && !hasOther ? View.VISIBLE : View.GONE);
            UIParams params = MainActivity.current_params;
            boolean found = params != null && params.connected && params.connected_device_id != null
                    && !params.connected_device_id.isEmpty() && !db.exists(params.connected_device_id);
            String dismissedId = requireContext()
                    .getSharedPreferences("device_preferences", Context.MODE_PRIVATE)
                    .getString("dismissed_detected_device_id", "");
            found = found && !params.connected_device_id.equals(dismissedId);
            detected.setVisibility(found ? View.VISIBLE : View.GONE);
        }
    }

    private void loadCloudSessions() {
        if (cloudSessionsLoading || !isAdded()) return;
        tAccUtils account = new tAccUtils(requireContext());
        if (!account.isLogin()) {
            cloudSessions = new JSONArray();
            refresh();
            mainHandler.postDelayed(() -> {
                if (!isAdded()) return;
                otherLoaded = true;
                refresh();
                if (otherRefresh != null) otherRefresh.setRefreshing(false);
            }, 300L);
            return;
        }
        cloudSessionsLoading = true;
        Map<String, String> query = new LinkedHashMap<>();
        query.put("page", "1");
        query.put("size", "20");
        account.getV2Json("sessions2", query, true, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                JSONArray result = json.optJSONArray("items");
                mainHandler.post(() -> {
                    if (!isAdded()) return;
                    cloudSessionsLoading = false;
                    cloudSessions = result == null ? new JSONArray() : result;
                    otherLoaded = true;
                    refresh();
                    otherRefresh.setRefreshing(false);
                });
            }

            @Override public void onError(int code, @NonNull String message) {
                mainHandler.post(() -> {
                    if (!isAdded()) return;
                    cloudSessionsLoading = false;
                    if (code == 401) cloudSessions = new JSONArray();
                    otherLoaded = true;
                    refresh();
                    otherRefresh.setRefreshing(false);
                });
            }
        });
    }

    private void addSessionCard(JSONObject session) {
        View card = getLayoutInflater().inflate(R.layout.item_device, other, false);
        String remark = session.optString("remark", "").trim();
        String device = session.optString("device", "未知设备").trim();
        String displayName = remark.isEmpty() ? device : remark;
        boolean current = session.optBoolean("is_current");
        String platform = session.optString("platform", "");
        String version = session.optString("client_version", "");
        String secondary = platform.isEmpty() ? "Typheye 登录设备" : platform;
        if (!version.isEmpty()) secondary += "  ·  " + version;
        ((TextView) card.findViewById(R.id.text_device_initial)).setText(firstCharacter(displayName));
        ((TextView) card.findViewById(R.id.text_device_model)).setText(displayName);
        ((TextView) card.findViewById(R.id.text_device_version)).setText(secondary);
        TextView status = card.findViewById(R.id.text_device_status);
        status.setText(current ? "当前设备" : "已登录");
        status.setBackgroundResource(R.drawable.bg_device_status_connected);
        String lastSeen = session.optString("last_seen_at", "");
        ((TextView) card.findViewById(R.id.text_device_since))
                .setText(lastSeen.isEmpty() ? "在线状态未知" : "活跃于 " + relativeCloudTime(lastSeen));
        card.setOnClickListener(v -> showSessionDetail(session));
        View more = card.findViewById(R.id.button_device_more);
        more.setVisibility(session.optBoolean("can_revoke", false) ? View.VISIBLE : View.GONE);
        more.setOnClickListener(v -> confirmRevokeSession(session));
        other.addView(card);
    }

    private void showSessionDetail(JSONObject session) {
        StringBuilder detail = new StringBuilder();
        appendDetail(detail, "设备", session.optString("device"));
        appendDetail(detail, "平台", session.optString("platform"));
        appendDetail(detail, "客户端版本", session.optString("client_version"));
        appendDetail(detail, "位置", session.optString("location"));
        appendDetail(detail, "IP", session.optString("ip"));
        appendDetail(detail, "创建时间", session.optString("created_at"));
        appendDetail(detail, "最后活跃", session.optString("last_seen_at"));
        new WGProAlertDialogBuilder(requireContext())
                .setTitle(session.optBoolean("is_current") ? "当前登录设备" : "登录设备详情")
                .setMessage(detail.length() == 0 ? "暂无更多设备信息" : detail.toString())
                .setNegativeButton("关闭", null).show();
    }

    private static void appendDetail(StringBuilder target, String label, String value) {
        if (value == null || value.trim().isEmpty()) return;
        if (target.length() > 0) target.append('\n');
        target.append(label).append("：").append(value.trim());
    }

    private void confirmRevokeSession(JSONObject session) {
        String id = session.optString("id", "");
        if (id.isEmpty()) return;
        boolean current = session.optBoolean("is_current");
        new WGProAlertDialogBuilder(requireContext())
                .setTitle(current ? "退出当前设备？" : "移除登录设备？")
                .setMessage(current ? "当前 Typheye 账户会立即退出登录。" : "该设备需要重新验证后才能访问账户。")
                .setNegativeButton("取消", null)
                .setPositiveButton(current ? "退出" : "移除", (dialog, which) -> revokeSession(id, current))
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
                    cloudSessions = new JSONArray();
                    refresh();
                    loadCloudSessions();
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

    private void addCard(Cursor cursor, LinearLayout parent) {
        String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
        String model = cursor.getString(cursor.getColumnIndexOrThrow("model"));
        String note = cursor.getString(cursor.getColumnIndexOrThrow("note"));
        boolean connected = cursor.getInt(cursor.getColumnIndexOrThrow("connected")) != 0;
        View card = getLayoutInflater().inflate(R.layout.item_device, parent, false);
        String displayName = note == null || note.trim().isEmpty() ? model : note.trim();
        ((TextView) card.findViewById(R.id.text_device_initial))
                .setText(firstCharacter(displayName));
        ((TextView) card.findViewById(R.id.text_device_model))
                .setText(displayName);
        ((TextView) card.findViewById(R.id.text_device_version))
                .setText(cursor.getString(cursor.getColumnIndexOrThrow("version")));
        TextView status = card.findViewById(R.id.text_device_status);
        status.setText(connected ? "已连接" : "已断开");
        status.setBackgroundResource(connected ? R.drawable.bg_device_status_connected
                : R.drawable.bg_device_status_disconnected);
        long stateTime = cursor.getLong(cursor.getColumnIndexOrThrow("last_seen"));
        ((TextView) card.findViewById(R.id.text_device_since))
                .setText("活跃于 " + relativeTime(stateTime));
        card.setOnClickListener(v -> showDeviceDetail(id));
        card.findViewById(R.id.button_device_more).setOnClickListener(v -> showMenu(id, note));
        parent.addView(card);
    }

    private static String firstCharacter(String value) {
        String trimmed = value == null ? "" : value.trim();
        if (trimmed.isEmpty()) return "?";
        return trimmed.substring(0, 1).toUpperCase();
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

    private static String relativeCloudTime(String value) {
        String normalized = value == null ? "" : value.replace('T', ' ');
        int zone = normalized.indexOf('Z'); if (zone >= 0) normalized = normalized.substring(0, zone);
        int decimal = normalized.indexOf('.'); if (decimal >= 0) normalized = normalized.substring(0, decimal);
        try {
            return relativeTime(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                    java.util.Locale.getDefault()).parse(normalized).getTime());
        } catch (Exception ignored) { return value; }
    }

    private void showDeviceDetail(String id) {
        try (DeviceDatabase db = new DeviceDatabase(requireContext()); Cursor cursor = db.get(id)) {
            if (!cursor.moveToFirst()) return;
            String model = cursor.getString(cursor.getColumnIndexOrThrow("model"));
            String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
            String version = cursor.getString(cursor.getColumnIndexOrThrow("version"));
            String note = cursor.getString(cursor.getColumnIndexOrThrow("note"));
            boolean connected = cursor.getInt(cursor.getColumnIndexOrThrow("connected")) != 0;
            View content = getLayoutInflater().inflate(R.layout.sheet_device_detail, null, false);
            ((TextView) content.findViewById(R.id.sheet_device_initial))
                    .setText(firstCharacter(note == null || note.trim().isEmpty() ? model : note));
            ((TextView) content.findViewById(R.id.sheet_device_name))
                    .setText(note == null || note.trim().isEmpty() ? model : note.trim());
            String systemVersion = version == null || version.trim().isEmpty() ? "系统版本未知" : version.trim();
            ((TextView) content.findViewById(R.id.sheet_device_status_version))
                    .setText((connected ? "已连接" : "已断开") + "  |  " + systemVersion);
            View actions = content.findViewById(R.id.sheet_device_actions);
            actions.setVisibility("xiaomi".equals(type) ? View.VISIBLE : View.GONE);
            WGProBottomSheetDialog dialog = new WGProAlertDialogBuilder(requireContext())
                    .setTitle("设备详情").setView(content).setNegativeButton("关闭", null).create();
            content.findViewById(R.id.sheet_device_transfer).setOnClickListener(v -> {
                dialog.dismiss();
                Intent intent = new Intent(requireContext(), WebActivity.class);
                intent.putExtra("URL", "https://wgpro.typheye.cn/push");
                startActivity(intent);
            });
            content.findViewById(R.id.sheet_device_logs).setOnClickListener(v -> {
                dialog.dismissForReplacement();
                new WGProAlertDialogBuilder(requireContext())
                        .setTitle("设备通信日志")
                        .setMessage(String.join("\n", MainActivity.logs))
                        .setNegativeButton("关闭", null)
                        .show();
            });
            dialog.show();
        }
    }

    private void showMenu(String id, String currentNote) {
        new WGProAlertDialogBuilder(requireContext())
                .setTitle("设备管理")
                .setItems(new CharSequence[]{"修改备注", "移除设备"}, (menuDialog, selected) -> {
                    if (selected == 0) {
                        showNoteDialog(id, currentNote);
                        return;
                    }
                    new WGProAlertDialogBuilder(requireContext())
                            .setTitle("移除设备？")
                            .setMessage("移除后可在设备重新连接时再次添加。")
                            .setNegativeButton("取消", null)
                            .setPositiveButton("移除", (confirmDialog, button) -> {
                                try (DeviceDatabase db = new DeviceDatabase(requireContext())) {
                                    db.remove(id);
                                }
                                refresh();
                            }).show();
                }).show();
    }

    private void showNoteDialog(String id, String currentNote) {
        View content = getLayoutInflater().inflate(R.layout.dialog_edittext, null, false);
        TextInputLayout layout = content.findViewById(R.id.textInputLayout);
        TextInputEditText input = content.findViewById(R.id.editText);
        layout.setHint("备注");
        input.setText(currentNote == null ? "" : currentNote);
        input.setSelection(input.length());
        input.setSingleLine(true);
        WGProBottomSheetDialog dialog = new WGProAlertDialogBuilder(requireContext())
                .setTitle("修改设备备注").setView(content).setNegativeButton("取消", null)
                .setPositiveButton("保存", null).create();
        dialog.setOnShowListener(ignored -> dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setOnClickListener(v -> {
                    String value = input.getText() == null ? "" : input.getText().toString().trim();
                    try (DeviceDatabase db = new DeviceDatabase(requireContext())) {
                        db.updateNote(id, value);
                    }
                    dialog.dismiss();
                    refresh();
                }));
        dialog.show();
    }

    private static final class LocalPageAdapter
            extends RecyclerView.Adapter<LocalPageAdapter.Holder> {
        private final List<View> pages;
        LocalPageAdapter(List<View> pages) { this.pages = pages; setHasStableIds(true); }
        @Override public long getItemId(int position) { return position; }
        @Override public int getItemCount() { return pages.size(); }
        @NonNull @Override public Holder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
            FrameLayout frame = new FrameLayout(parent.getContext());
            frame.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            return new Holder(frame);
        }
        @Override public void onBindViewHolder(@NonNull Holder holder, int position) {
            View page = pages.get(position);
            if (page.getParent() instanceof ViewGroup) ((ViewGroup) page.getParent()).removeView(page);
            holder.frame.removeAllViews();
            holder.frame.addView(page, new FrameLayout.LayoutParams(-1, -1));
        }
        static final class Holder extends RecyclerView.ViewHolder {
            final FrameLayout frame;
            Holder(FrameLayout frame) { super(frame); this.frame = frame; }
        }
    }
}
