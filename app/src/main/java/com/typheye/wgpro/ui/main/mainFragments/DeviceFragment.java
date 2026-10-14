package com.typheye.wgpro.ui.main.mainFragments;

import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputFilter;
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
import androidx.core.widget.NestedScrollView;

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
import com.typheye.wgpro.ui.widget.WGProProgressRunner;
import com.typheye.wgpro.utils.tAccUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DeviceFragment extends Fragment {

    /** 当前可见实例（主内容是 ViewPager2，外面拿不到，自己登记一下给右上角 + 分流用）。 */
    private static java.lang.ref.WeakReference<DeviceFragment> sCurrent;

    @Nullable
    public static DeviceFragment current() {
        return sCurrent == null ? null : sCurrent.get();
    }
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private LinearLayout wearable;
    private LinearLayout other;
    private View wearableEmpty;
    private View otherEmpty;
    private View detected;
    private boolean wearableLoaded;
    private String wearableSignature = "";
    private boolean otherLoaded;
    private String otherSignature = "";
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
        // 「模拟设备」：会话卡片已搬到 设置 → 账户 → 已登录的会话，这里先留空位等虚拟机能力。
        ((TextView) otherEmpty.findViewById(R.id.stream_empty_title)).setText("暂无模拟设备");
        ((TextView) otherEmpty.findViewById(R.id.stream_empty_description))
                .setText("点击「+」创建的模拟设备，可在这里集中管理。");
        detected = root.findViewById(R.id.card_detected_device);
        wearableRefresh = root.findViewById(R.id.device_page_wearable);
        otherRefresh = root.findViewById(R.id.device_page_other);
        wearableRefresh.setEnabled(true);
        otherRefresh.setEnabled(true);
        NestedScrollView wearableScroll = root.findViewById(R.id.device_wearable_scroll);
        NestedScrollView otherScroll = root.findViewById(R.id.device_other_scroll);
        wearableRefresh.setOnChildScrollUpCallback((layout, child) -> wearableScroll.getScrollY() > 0);
        otherRefresh.setOnChildScrollUpCallback((layout, child) -> otherScroll.getScrollY() > 0);
        wearableRefresh.setOnRefreshListener(() -> {
            wearableLoaded = true;
            refreshWearable();
        });
        otherRefresh.setOnRefreshListener(this::refresh);
        wearableRefresh.post(() -> wearableRefresh.setRefreshing(true));
        otherRefresh.post(() -> otherRefresh.setRefreshing(true));
        mainHandler.postDelayed(() -> {
            if (!isAdded()) return;
            wearableLoaded = true;
            refreshWearable();
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

    /** 当前是否停在「模拟设备」页（供右上角 + 分流）。 */
    public boolean isVirtualPage() {
        View root = getView();
        if (root == null) {
            return false;
        }
        androidx.viewpager2.widget.ViewPager2 pager = root.findViewById(R.id.device_pager);
        return pager != null && pager.getCurrentItem() == 1;
    }

    private void setupPages(View root) {
        ViewPager2 pager = root.findViewById(R.id.device_pager);
        FrameLayout pool = root.findViewById(R.id.device_page_pool);
        List<View> pages = Arrays.asList(root.findViewById(R.id.device_page_wearable),
                root.findViewById(R.id.device_page_other));
        for (View page : pages) pool.removeView(page);
        pager.setAdapter(new LocalPageAdapter(pages));
        pager.setOffscreenPageLimit(1);
    }

    @Override public void onResume() {
        super.onResume();
        sCurrent = new java.lang.ref.WeakReference<>(this);
        refresh();
        // 历史遗留设备名修正：独立于 refresh()，每进程一次，改完自己补一次刷新
        repairLegacyVirtualNamesOnce();
    }

    @Override public void onPause() {
        if (sCurrent != null && sCurrent.get() == this) {
            sCurrent = null;
        }
        super.onPause();
    }
    public void updateUI(UIParams ignored) { refresh(); }

    private void refreshWearable() {
        try {
            refresh();
        } catch (Throwable ignored) {
            // XMS SDK 或数据库异常不能让下拉刷新的指示器一直挂起。
        }
        mainHandler.postDelayed(() -> {
            if (!isAdded()) return;
            if (wearableRefresh != null) wearableRefresh.setRefreshing(false);
        }, 250L);
    }

    private void refresh() {
        if (!isAdded() || wearable == null || other == null) return;
        // 穿戴设备组 = 小米穿戴设备 + 其它已配对设备（非 Xiaomi 的现在也并到这一组）
        StringBuilder wearableKey = new StringBuilder();
        LinearLayout newWearable = new LinearLayout(requireContext());
        newWearable.setOrientation(LinearLayout.VERTICAL);
        collectDevices("xiaomi", wearableKey, newWearable);
        collectDevicesExcept("xiaomi", com.typheye.wgpro.data.DeviceDatabase.TYPE_VIRTUAL,
                wearableKey, newWearable);
        String nextWearable = wearableKey.toString();
        if (!nextWearable.equals(wearableSignature)) {
            wearable.removeAllViews();
            while (newWearable.getChildCount() > 0) {
                View child = newWearable.getChildAt(0);
                newWearable.removeView(child);
                wearable.addView(child);
            }
            wearableSignature = nextWearable;
        }
        // 判断"有没有卡片"必须看**已经挂到页面上**的容器：上面交换时已把暂存容器搬空，
        // 用 newWearable.getChildCount() 会恒为 0，导致有设备时空态与列表同时显示。
        wearableEmpty.setVisibility(wearableLoaded && wearable.getChildCount() == 0
                ? View.VISIBLE : View.GONE);
        // 「模拟设备」页 = 设备库里的 type='virtual'（与真机设备严格隔离）
        StringBuilder otherKey = new StringBuilder();
        LinearLayout newOther = new LinearLayout(requireContext());
        newOther.setOrientation(LinearLayout.VERTICAL);
        collectVirtual(otherKey, newOther);
        String nextOther = otherKey.toString();
        if (!nextOther.equals(otherSignature)) {
            other.removeAllViews();
            while (newOther.getChildCount() > 0) {
                View child = newOther.getChildAt(0);
                newOther.removeView(child);
                other.addView(child);
            }
            otherSignature = nextOther;
        }
        otherEmpty.setVisibility(otherLoaded && other.getChildCount() == 0
                ? View.VISIBLE : View.GONE);
        // 修掉模拟页下拉刷新一直转圈（原来只停了穿戴页的）
        if (otherRefresh != null) otherRefresh.setRefreshing(false);
    }

    /** 模拟设备分组：只取 type='virtual'。 */
    private void collectVirtual(StringBuilder key, LinearLayout target) {
        try (DeviceDatabase db = new DeviceDatabase(requireContext());
             Cursor cursor = db.all(com.typheye.wgpro.data.DeviceDatabase.TYPE_VIRTUAL)) {
            appendDeviceCards(cursor, key, target);
            otherLoaded = true;
        }
    }

    /** 云端机型「设备名称」缓存（id → name），供卡片即时兜底显示。 */
    private static java.util.Map<String, String> sCloudNames;
    /** 历史遗留补名：**每个进程只做一次**（必须在 refresh() 之外触发，避免嵌套 refresh 卡死）。 */
    private static boolean sLegacyNamesChecked;

    /**
     * 修历史数据：早期版本把 {@code model} 列写成了机型标识（如 vela-test），
     * 这里用云端「设备名称」+ 4 位随机改写掉。整个过程在后台线程完成，改完**只补一次刷新**。
     */
    private void repairLegacyVirtualNamesOnce() {
        if (sLegacyNamesChecked) {
            return;
        }
        sLegacyNamesChecked = true;
        final java.util.List<String[]> bad = new java.util.ArrayList<>();
        try (DeviceDatabase db = new DeviceDatabase(requireContext());
             Cursor cursor = db.all(com.typheye.wgpro.data.DeviceDatabase.TYPE_VIRTUAL)) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                String model = cursor.getString(cursor.getColumnIndexOrThrow("model"));
                String avd = com.typheye.wgpro.core.emu.VelaProvision.modelIdOf(id);
                if (avd.isEmpty()) {
                    continue;
                }
                if (model == null || model.trim().isEmpty() || model.trim().equals(avd)) {
                    bad.add(new String[]{id, avd});
                }
            }
        } catch (Exception error) {
            return;
        }
        if (bad.isEmpty()) {
            return;
        }
        final android.content.Context app = requireContext().getApplicationContext();
        new Thread(() -> {
            java.util.Map<String, String> names = new java.util.HashMap<>();
            try (okhttp3.Response response = new okhttp3.OkHttpClient().newCall(
                    new okhttp3.Request.Builder()
                            .url("https://service.typheye.cn/api.php?type=vela_devices")
                            .build()).execute()) {
                if (response.body() != null) {
                    com.google.gson.JsonObject root = com.google.gson.JsonParser
                            .parseString(response.body().string()).getAsJsonObject();
                    for (com.google.gson.JsonElement element : root.getAsJsonArray("devices")) {
                        com.google.gson.JsonObject item = element.getAsJsonObject();
                        names.put(item.get("id").getAsString(), item.get("name").getAsString());
                    }
                }
            } catch (Exception error) {
                android.util.Log.w("DeviceFragment", "拉取机型名称失败", error);
            }
            sCloudNames = names;
            if (names.isEmpty()) {
                return;
            }
            boolean changed = false;
            try (DeviceDatabase db = new DeviceDatabase(app)) {
                for (String[] row : bad) {
                    String cloud = names.get(row[1]);
                    if (cloud == null || cloud.trim().isEmpty()) {
                        continue;
                    }
                    db.updateModel(row[0], cloud.trim() + " "
                            + com.typheye.wgpro.core.emu.VelaProvision.randomSuffix());
                    changed = true;
                }
            } catch (Exception ignored) {
            }
            if (changed) {
                mainHandler.post(() -> {
                    if (isAdded()) {
                        refresh();
                    }
                });
            }
        }, "vela-names").start();
    }

    /** 把某一类已配对设备追加到目标组（卡片顺序按 last_seen 倒序）。 */
    private void collectDevices(String type, StringBuilder key, LinearLayout target) {
        try (DeviceDatabase db = new DeviceDatabase(requireContext()); Cursor cursor = db.all(type)) {
            appendDeviceCards(cursor, key, target);
        }
    }

    private void collectDevicesExcept(String type, String alsoExclude, StringBuilder key,
                                       LinearLayout target) {
        try (DeviceDatabase db = new DeviceDatabase(requireContext());
             Cursor cursor = db.allExcept(type, alsoExclude)) {
            appendDeviceCards(cursor, key, target);
        }
    }

    private void collectDevicesExcept(String type, StringBuilder key, LinearLayout target) {
        try (DeviceDatabase db = new DeviceDatabase(requireContext());
             Cursor cursor = db.allExcept(type)) {
            appendDeviceCards(cursor, key, target);
        }
    }

    private void appendDeviceCards(Cursor cursor, StringBuilder key, LinearLayout target) {
        while (cursor.moveToNext()) {
            key.append(cursor.getString(cursor.getColumnIndexOrThrow("id"))).append('|')
                    .append(cursor.getString(cursor.getColumnIndexOrThrow("note"))).append('|')
                    .append(cursor.getInt(cursor.getColumnIndexOrThrow("connected"))).append(';');
            addCard(cursor, target);
        }
    }

    private void addCard(Cursor cursor, LinearLayout parent) {
        String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
        String model = cursor.getString(cursor.getColumnIndexOrThrow("model"));
        String type = cursor.getString(cursor.getColumnIndexOrThrow("type"));
        String note = cursor.getString(cursor.getColumnIndexOrThrow("note"));
        boolean connected = cursor.getInt(cursor.getColumnIndexOrThrow("connected")) != 0;
        View card = getLayoutInflater().inflate(R.layout.item_device, parent, false);
        // 模拟设备的**机型标识只从设备 id 解析**（model 列存的是设备显示名，不一定是机型）
        final String avd = com.typheye.wgpro.core.emu.VelaProvision.modelIdOf(id);
        String displayName = note == null ? "" : note.trim();
        if (displayName.isEmpty()) {
            displayName = model == null ? "" : model.trim();
        }
        // 万一是历史遗留（model 列被写成了机型标识）→ 用云端「设备名称」兜底，绝不显示标识
        if (com.typheye.wgpro.data.DeviceDatabase.TYPE_VIRTUAL.equals(type)
                && displayName.equals(avd) && sCloudNames != null) {
            String cloud = sCloudNames.get(avd);
            if (cloud != null && !cloud.trim().isEmpty()) {
                displayName = cloud.trim();
            }
        }
        ((TextView) card.findViewById(R.id.text_device_initial))
                .setText(firstCharacter(displayName));
        ((TextView) card.findViewById(R.id.text_device_model))
                .setText(displayName);
        String version = cursor.getString(cursor.getColumnIndexOrThrow("version"));
        String versionLabel;
        if (com.typheye.wgpro.data.DeviceDatabase.TYPE_VIRTUAL.equals(type)) {
            versionLabel = "模拟设备";   // 模拟设备不显示系统版本
        } else if ("xiaomi".equals(type)) {
            versionLabel = "小米运动健康";
        } else {
            versionLabel = version == null ? "" : version;
        }
        TextView versionView = card.findViewById(R.id.text_device_version);
        if (versionView != null) {
            // 视图已隐藏（第二行不再展示），但字段仍然写下去，保持数据链路完整
            versionView.setText(versionLabel);
        }
        boolean virtual = com.typheye.wgpro.data.DeviceDatabase.TYPE_VIRTUAL.equals(type);
        if (virtual) {
            connected = com.typheye.wgpro.core.emu.VelaEmulatorManager.get().isRunning(avd);
        }
        TextView status = card.findViewById(R.id.text_device_status);
        status.setText(virtual ? (connected ? "运行中" : "已停止") : (connected ? "已连接" : "已断开"));
        status.setBackgroundResource(connected ? R.drawable.bg_device_status_connected
                : R.drawable.bg_device_status_disconnected);
        String stateColumn = connected ? "connected_at" : "disconnected_at";
        int stateIndex = cursor.getColumnIndex(stateColumn);
        long stateTime = stateIndex < 0 ? 0L : cursor.getLong(stateIndex);
        if (stateTime <= 0L) {
            // 状态列还没写过（例如模拟设备首次运行）→ 回退最后活跃时间，别显示"未知时间"
            stateTime = cursor.getLong(cursor.getColumnIndexOrThrow("last_seen"));
        }
        TextView sinceView = card.findViewById(R.id.text_device_since);
        if (sinceView != null) {
            sinceView.setVisibility(View.VISIBLE);
            sinceView.setText((connected ? "连接于 " : "断开于 ") + relativeTime(stateTime));
        }
        final String sheetName = displayName;
        card.setOnClickListener(v -> {
            if (com.typheye.wgpro.data.DeviceDatabase.TYPE_VIRTUAL.equals(type)) {
                // 与真机一致：先弹「设备详情」，再进模拟页面（机型标识用解析出来的 avd）
                showVirtualDeviceDetail(id, avd, sheetName, note);
            } else {
                showDeviceDetail(id);
            }
        });
        // 不再放竖点：长按卡片弹设备管理菜单。
        card.setOnLongClickListener(v -> {
            showMenu(id, note, "xiaomi".equals(type) ? wearableRefresh : otherRefresh);
            return true;
        });
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

    /** 模拟设备的「设备详情」：复用同款 sheet，行文案换成模拟语义。 */
    private void showVirtualDeviceDetail(String id, String avd, String displayName, String note) {
        boolean running = com.typheye.wgpro.core.emu.VelaEmulatorManager.get().isRunning(avd);
        View content = getLayoutInflater().inflate(R.layout.sheet_device_detail, null, false);
        // 标题显示**设备名**（note > model 列里的显示名），机型标识只用于运行态判断
        String name = note == null || note.trim().isEmpty() ? displayName : note.trim();
        ((TextView) content.findViewById(R.id.sheet_device_initial)).setText(firstCharacter(name));
        ((TextView) content.findViewById(R.id.sheet_device_name)).setText(name);
        ((TextView) content.findViewById(R.id.sheet_device_status_version))
                .setText((running ? "运行中" : "已停止") + " | 模拟设备");
        content.findViewById(R.id.sheet_device_actions).setVisibility(View.VISIBLE);
        content.findViewById(R.id.sheet_device_more_group).setVisibility(View.VISIBLE);
        ((TextView) content.findViewById(R.id.sheet_device_action_primary_label))
                .setText("进入模拟页面");
        ((TextView) content.findViewById(R.id.sheet_device_action_more_label))
                .setText("显示更多操作");
        View stopRow = content.findViewById(R.id.sheet_device_logs);
        ((TextView) content.findViewById(R.id.sheet_device_action_secondary_label))
                .setText("停止当前实例");
        // 「停止当前实例」仅在运行时展示，且位于「显示更多操作」上一项；
        // 它上方那条分割线必须一起隐藏，否则会留下一条孤线。
        stopRow.setVisibility(running ? View.VISIBLE : View.GONE);
        View stopDivider = content.findViewById(R.id.sheet_device_divider_logs);
        if (stopDivider != null) {
            stopDivider.setVisibility(running ? View.VISIBLE : View.GONE);
        }
        WGProBottomSheetDialog dialog = new WGProAlertDialogBuilder(requireContext())
                .setTitle("设备详情").setView(content).setNegativeButton("关闭", null).create();
        content.findViewById(R.id.sheet_device_transfer).setOnClickListener(v -> {
            dialog.dismiss();
            android.content.Intent intent = new android.content.Intent(requireContext(),
                    com.typheye.wgpro.ui.function.device.VirtualDeviceActivity.class);
            intent.putExtra(com.typheye.wgpro.ui.function.device.VirtualDeviceFragment.ARG_AVD,
                    avd);
            startActivity(intent);
        });
        stopRow.setOnClickListener(v -> {
            dialog.dismiss();
            com.typheye.wgpro.core.emu.VelaEmulatorManager.get().stop(requireContext(), avd);
            com.typheye.wgpro.ui.widget.WGProHint.show(requireContext(), "已停止当前实例");
            refresh();
        });
        content.findViewById(R.id.sheet_device_more).setOnClickListener(v -> {
            dialog.dismissForReplacement();
            showMenu(id, note, otherRefresh);
        });
        dialog.setOnDismissListener(d -> {
            if (isAdded()) {
                refresh();
            }
        });
        dialog.show();
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
            String systemVersion = "xiaomi".equals(type)
                    ? "小米运动健康"
                    : (version == null || version.trim().isEmpty() ? "系统版本未知" : version.trim());
            ((TextView) content.findViewById(R.id.sheet_device_status_version))
                    .setText((connected ? "已连接" : "已断开") + " | " + systemVersion);
            View actions = content.findViewById(R.id.sheet_device_actions);
            actions.setVisibility("xiaomi".equals(type) ? View.VISIBLE : View.GONE);
            // 「穿戴设备」详情页末尾增加「显示更多操作」，直接打开设备管理菜单。
            View moreGroup = content.findViewById(R.id.sheet_device_more_group);
            moreGroup.setVisibility("xiaomi".equals(type) ? View.VISIBLE : View.GONE);
            WGProBottomSheetDialog dialog = new WGProAlertDialogBuilder(requireContext())
                    .setTitle("设备详情").setView(content).setNegativeButton("关闭", null).create();
            content.findViewById(R.id.sheet_device_more).setOnClickListener(v -> {
                dialog.dismissForReplacement();
                showMenu(id, note, "xiaomi".equals(type) ? wearableRefresh : otherRefresh);
            });
            content.findViewById(R.id.sheet_device_transfer).setOnClickListener(v -> {
                dialog.dismiss();
                Intent intent = new Intent(requireContext(), WebActivity.class);
                intent.putExtra("URL", "https://wgpro.typheye.cn/push");
                startActivity(intent);
            });
            content.findViewById(R.id.sheet_device_logs).setOnClickListener(v -> {
                dialog.dismissForReplacement();
                String deviceLog = String.join("\n",
                        com.typheye.wgpro.core.state.AppState.get().deviceLog());
                new WGProAlertDialogBuilder(requireContext())
                        .setTitle("设备通信日志")
                        .setMessage(deviceLog.trim().isEmpty() ? "暂无设备通信日志" : deviceLog)
                        .setNegativeButton("关闭", null)
                        .show();
            });
            dialog.show();
        }
    }

    private void showMenu(String id, String currentNote, SwipeRefreshLayout refreshLayout) {
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
                                refreshWithSpinner(refreshLayout);
                            }).show();
                }).show();
    }

    private void refreshWithSpinner(@Nullable SwipeRefreshLayout layout) {
        if (layout == null) {
            refresh();
            return;
        }
        layout.setRefreshing(true);
        try {
            refresh();
        } catch (Throwable ignored) { }
        mainHandler.postDelayed(() -> {
            if (isAdded()) layout.setRefreshing(false);
        }, 250L);
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
