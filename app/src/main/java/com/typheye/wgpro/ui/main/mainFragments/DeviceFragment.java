package com.typheye.wgpro.ui.main.mainFragments;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProBottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.typheye.wgpro.R;
import com.typheye.wgpro.core.xms.UIParams;
import com.typheye.wgpro.data.DeviceDatabase;
import com.typheye.wgpro.ui.function.device.AddDeviceActivity;
import com.typheye.wgpro.ui.function.device.DeviceDetailActivity;
import com.typheye.wgpro.ui.main.MainActivity;

public class DeviceFragment extends Fragment {
    private LinearLayout wearable;
    private View wearableEmpty, otherEmpty, detected;
    private TextView detectedName;

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment_device, parent, false);
        wearable = root.findViewById(R.id.container_wearable_devices);
        wearableEmpty = root.findViewById(R.id.card_wearable_empty);
        otherEmpty = root.findViewById(R.id.card_other_empty);
        detected = root.findViewById(R.id.card_detected_device);
        detectedName = root.findViewById(R.id.text_detected_name);
        detected.setOnClickListener(v -> startActivity(new Intent(requireContext(), AddDeviceActivity.class)));
        return root;
    }

    @Override public void onResume() { super.onResume(); refresh(); }
    public void updateUI(UIParams ignored) { refresh(); }

    private void refresh() {
        if (!isAdded() || wearable == null) return;
        try (DeviceDatabase db = new DeviceDatabase(requireContext()); Cursor cursor = db.all("xiaomi")) {
            wearable.removeAllViews();
            boolean hasDevice = false;
            while (cursor.moveToNext()) { hasDevice = true; addCard(cursor, wearable); }
            wearableEmpty.setVisibility(hasDevice ? View.GONE : View.VISIBLE);
            otherEmpty.setVisibility(View.VISIBLE);
            UIParams params = MainActivity.current_params;
            boolean found = params != null && params.connected && params.connected_device_id != null
                    && !params.connected_device_id.isEmpty() && !db.exists(params.connected_device_id);
            detected.setVisibility(found ? View.VISIBLE : View.GONE);
            if (found) detectedName.setText(params.connected_device_name);
        }
    }

    private void addCard(Cursor cursor, LinearLayout parent) {
        String id = cursor.getString(cursor.getColumnIndexOrThrow("id"));
        String model = cursor.getString(cursor.getColumnIndexOrThrow("model"));
        String note = cursor.getString(cursor.getColumnIndexOrThrow("note"));
        boolean connected = cursor.getInt(cursor.getColumnIndexOrThrow("connected")) != 0;
        View card = getLayoutInflater().inflate(R.layout.item_device, parent, false);
        ((TextView) card.findViewById(R.id.text_device_initial)).setText(model.substring(0, 1).toUpperCase());
        ((TextView) card.findViewById(R.id.text_device_model)).setText(note == null || note.trim().isEmpty() ? model : note.trim());
        ((TextView) card.findViewById(R.id.text_device_version)).setText(cursor.getString(cursor.getColumnIndexOrThrow("version")));
        TextView status = card.findViewById(R.id.text_device_status);
        status.setText(connected ? "已连接" : "已断开");
        status.setBackgroundResource(connected ? R.drawable.bg_device_status_connected : R.drawable.bg_device_status_disconnected);
        ((TextView) card.findViewById(R.id.text_device_since)).setText(connected ? "连接于刚刚" : "最近离线");
        card.setOnClickListener(v -> openDetail(id));
        card.findViewById(R.id.button_device_more).setOnClickListener(v -> showMenu(v, id, note));
        parent.addView(card);
    }

    private void openDetail(String id) {
        Intent intent = new Intent(requireContext(), DeviceDetailActivity.class);
        intent.putExtra(DeviceDetailActivity.EXTRA_ID, id);
        intent.putExtra(DeviceDetailActivity.EXTRA_TYPE, "xiaomi");
        startActivity(intent);
    }

    private void showMenu(View anchor, String id, String currentNote) {
        PopupMenu menu = new PopupMenu(requireContext(), anchor);
        menu.getMenu().add("备注"); menu.getMenu().add("移除");
        menu.setOnMenuItemClickListener(item -> {
            if ("备注".contentEquals(item.getTitle())) showNoteDialog(id, currentNote);
            else new WGProAlertDialogBuilder(requireContext()).setTitle("移除设备？")
                    .setMessage("移除后可在设备重新连接时再次添加。")
                    .setNegativeButton("取消", null).setPositiveButton("移除", (dialog, which) -> {
                        try (DeviceDatabase db = new DeviceDatabase(requireContext())) { db.remove(id); }
                        refresh();
                    }).show();
            return true;
        });
        menu.show();
    }

    private void showNoteDialog(String id, String currentNote) {
        View content = getLayoutInflater().inflate(R.layout.dialog_edittext, null, false);
        TextInputLayout layout = content.findViewById(R.id.textInputLayout);
        TextInputEditText input = content.findViewById(R.id.editText);
        layout.setHint("备注"); input.setText(currentNote == null ? "" : currentNote); input.setSelection(input.length()); input.setSingleLine(true);
        WGProBottomSheetDialog dialog = new WGProAlertDialogBuilder(requireContext()).setTitle("修改设备备注").setView(content)
                .setNegativeButton("取消", null).setPositiveButton("保存", null).create();
        dialog.setOnShowListener(ignored -> {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
                String value = input.getText() == null ? "" : input.getText().toString().trim();
                try (DeviceDatabase db = new DeviceDatabase(requireContext())) { db.updateNote(id, value); }
                dialog.dismiss(); refresh();
            });
            input.requestFocus(); dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        });
        dialog.show();
    }
}
