package com.typheye.wgpro.ui.function.device;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.typheye.wgpro.R;
import com.typheye.wgpro.data.DeviceDatabase;

public class XiaomiDeviceFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent,
                             Bundle state) {
        View view = inflater.inflate(R.layout.fragment_device_detail, parent, false);
        String id = requireActivity().getIntent().getStringExtra(DeviceDetailActivity.EXTRA_ID);
        try (DeviceDatabase db = new DeviceDatabase(requireContext());
             Cursor cursor = db.get(id)) {
            if (cursor.moveToFirst()) {
                String model = cursor.getString(cursor.getColumnIndexOrThrow("model"));
                ((TextView) view.findViewById(R.id.detail_initial))
                        .setText(model.substring(0, 1).toUpperCase());
                ((TextView) view.findViewById(R.id.detail_model)).setText(model);
                ((TextView) view.findViewById(R.id.detail_kind)).setText("小米穿戴设备");
                ((TextView) view.findViewById(R.id.detail_status)).setText(
                        "连接状态：" + (cursor.getInt(cursor.getColumnIndexOrThrow("connected")) != 0
                                ? "已连接" : "已断开"));
                ((TextView) view.findViewById(R.id.detail_version)).setText("系统版本：小米运动健康");
                ((TextView) view.findViewById(R.id.detail_id)).setText("节点 ID：" + id);
                String note = cursor.getString(cursor.getColumnIndexOrThrow("note"));
                ((TextView) view.findViewById(R.id.detail_note)).setText(
                        note == null || note.isEmpty() ? "暂无备注" : "备注：" + note);
            }
        }
        return view;
    }
}
