package com.typheye.wgpro.ui.main.mainFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.typheye.wgpro.ui.main.MainActivity;
import com.typheye.wgpro.R;
import com.typheye.wgpro.core.xms.UIParams;

public class DeviceFragment extends Fragment {
    private TextView midevice_status;
    private TextView midevice_info;

    private LinearLayout midevice_btns;

    private CardView midevice_help;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_device, container, false);

        midevice_status = view.findViewById(R.id.text_midevice_status);
        midevice_info = view.findViewById(R.id.text_midevice_info);
        midevice_btns = view.findViewById(R.id.linear_midevice_btns);
        midevice_help = view.findViewById(R.id.card_midevice_help);
        Button midevice_btn_open = view.findViewById(R.id.btn_midevice_open);
        Button midevice_btn_help = view.findViewById(R.id.btn_midevice_help);
        Button andevice_btn_open = view.findViewById(R.id.btn_andevice_open);
        Button andevice_btn_help = view.findViewById(R.id.btn_andevice_help);

        midevice_btn_open.setOnClickListener(v -> {
            // 获取宿主 Activity，并强转为 MainActivity
            MainActivity activity = (MainActivity) requireActivity();

            // 调用 MainActivity 提供的公共方法
            activity.showWebView(true);
        });

        midevice_btn_help.setOnClickListener(v -> new MaterialAlertDialogBuilder(requireContext())
                .setTitle("提示")
                .setMessage("敬请期待")
                .setPositiveButton("确定", null)
                .show());

        andevice_btn_open.setOnClickListener(v -> new MaterialAlertDialogBuilder(requireContext())
                .setTitle("提示")
                .setMessage("敬请期待")
                .setPositiveButton("确定", null)
                .show());

        andevice_btn_help.setOnClickListener(v -> new MaterialAlertDialogBuilder(requireContext())
                .setTitle("提示")
                .setMessage("敬请期待")
                .setPositiveButton("确定", null)
                .show());

        // 初始状态
        updateUI(MainActivity.current_params);

        return view;
        //return inflater.inflate(R.layout.fragment_device, container, false);
    }

    // 提供一个公共方法更新 UI
    public void updateUI(UIParams params) {
        if (midevice_status != null && midevice_info != null) {
            midevice_status.setText(params.connected ? "已连接" : "未连接");
            midevice_info.setText(params.connected_device_name);
            midevice_btns.setVisibility(params.connected ? View.VISIBLE : View.GONE);
            midevice_help.setVisibility(params.connected ? View.GONE : View.VISIBLE);
        }
    }

}