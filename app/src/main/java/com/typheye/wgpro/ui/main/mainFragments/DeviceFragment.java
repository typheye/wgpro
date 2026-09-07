package com.typheye.wgpro.ui.main.mainFragments;

import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
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

import java.util.Arrays;
import java.util.List;

public class DeviceFragment extends Fragment {
    private LinearLayout wearable;
    private LinearLayout other;
    private View wearableEmpty;
    private View otherEmpty;
    private View detected;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
                             @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment_device, parent, false);
        wearable = root.findViewById(R.id.container_wearable_devices);
        other = root.findViewById(R.id.container_other_devices);
        wearableEmpty = root.findViewById(R.id.card_wearable_empty);
        otherEmpty = root.findViewById(R.id.card_other_empty);
        detected = root.findViewById(R.id.card_detected_device);
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

    @Override public void onResume() { super.onResume(); refresh(); }
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
            wearableEmpty.setVisibility(hasWearable ? View.GONE : View.VISIBLE);
            otherEmpty.setVisibility(hasOther ? View.GONE : View.VISIBLE);
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
        ((TextView) card.findViewById(R.id.text_device_since))
                .setText(connected ? "连接于刚刚" : "最近离线");
        card.setOnClickListener(v -> showDeviceDetail(id));
        card.findViewById(R.id.button_device_more).setOnClickListener(v -> showMenu(id, note));
        parent.addView(card);
    }

    private static String firstCharacter(String value) {
        String trimmed = value == null ? "" : value.trim();
        if (trimmed.isEmpty()) return "?";
        return trimmed.substring(0, 1).toUpperCase();
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
