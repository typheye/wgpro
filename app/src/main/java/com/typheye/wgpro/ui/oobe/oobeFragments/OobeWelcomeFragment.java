package com.typheye.wgpro.ui.oobe.oobeFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.google.android.material.button.MaterialButton;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.oobe.OobeActivity;

public class OobeWelcomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_oobe_welcome, container, false);

        MaterialButton forward = view.findViewById(R.id.btn_forward);
        View exit = view.findViewById(R.id.btn_back);
        forward.setOnClickListener(v -> {
            if (requireActivity() instanceof OobeActivity) {
                ((OobeActivity) requireActivity()).switchFragment("policies");
            }
        });
        exit.setOnClickListener(v -> exitApp());

        return view;
    }

    private void exitApp(){
        // 创建对话框（关键：使用 MaterialAlertDialogBuilder 但自定义按钮行为）
            WGProAlertDialogBuilder builder = new WGProAlertDialogBuilder(requireActivity())
                    .setTitle("提示")
                    .setMessage("您确定要退出应用吗？")
                    .setPositiveButton("退出", (dialog, which) -> requireActivity().finish())
                    .setNegativeButton("返回", null);
            builder.setCancelable(false); // 防止点击空白区域关闭

            // 创建对话框实例
            com.google.android.material.bottomsheet.BottomSheetDialog dialog = builder.create();

            // 显示对话框
            dialog.show();
    }
}
