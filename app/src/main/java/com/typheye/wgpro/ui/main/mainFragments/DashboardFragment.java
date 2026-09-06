package com.typheye.wgpro.ui.main.mainFragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.ScanQRActivity;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.ui.main.MainActivity;
import com.typheye.wgpro.utils.AppUtils;

public class DashboardFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        view.findViewById(R.id.tool_file_transfer).setOnClickListener(v -> openWeb("https://wgpro.typheye.cn/push"));
        view.findViewById(R.id.discover_beta).setOnClickListener(v -> openWeb("https://www.typheye.cn/?p=beta"));
        view.findViewById(R.id.tool_guide).setOnClickListener(v -> openWeb("https://wgpro.typheye.cn/docs"));
        view.findViewById(R.id.tool_scan).setOnClickListener(v ->
                startActivity(new Intent(requireContext(), ScanQRActivity.class)));
        view.findViewById(R.id.tool_diagnostics).setOnClickListener(v -> showDiagnostics());
        view.findViewById(R.id.tool_logs).setOnClickListener(v -> new WGProAlertDialogBuilder(requireContext())
                .setTitle("运行日志")
                .setMessage(String.join("\n", MainActivity.logs))
                .setPositiveButton("完成", null)
                .show());
        View console = view.findViewById(R.id.tool_console);
        console.setVisibility("development".equals(AppUtils.getAppVersionMode(requireContext())) ? View.VISIBLE : View.GONE);
        console.setOnClickListener(v -> openWeb("https://www.typheye.cn/admin"));
        return view;
    }

    private void openWeb(String url) {
        Intent intent = new Intent(requireContext(), WebActivity.class);
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    private void showDiagnostics() {
        boolean connected = MainActivity.current_params.connected;
        String device = MainActivity.current_params.connected_device_name;
        String message = connected
                ? "穿戴服务正常\n当前设备：" + (device == null || device.isEmpty() ? "已连接设备" : device) + "\n消息通道：可用"
                : "暂未发现穿戴设备\n请打开小米运动健康，并检查设备管理权限。";
        new WGProAlertDialogBuilder(requireContext())
                .setTitle("连接诊断")
                .setMessage(message)
                .setPositiveButton("完成", null)
                .show();
    }
}
