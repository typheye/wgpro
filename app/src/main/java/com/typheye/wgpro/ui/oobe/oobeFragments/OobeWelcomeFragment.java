package com.typheye.wgpro.ui.oobe.oobeFragments;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.oobe.OobeActivity;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.utils.AppUtils;

public class OobeWelcomeFragment extends Fragment {

    private MaterialCardView cardStandard, cardProfessional, cardDevelopment;
    private String selectedEdition = "standard"; // 默认

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        selectedEdition = AppUtils.getAppVersionMode(requireContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_oobe_welcome, container, false);

        cardStandard = view.findViewById(R.id.card_standard);
        cardProfessional = view.findViewById(R.id.card_professional);
        cardDevelopment = view.findViewById(R.id.card_development);

        FloatingActionButton oobe_btn_forward = view.findViewById(R.id.btn_forward);
        FloatingActionButton oobe_btn_back = view.findViewById(R.id.btn_back);

        // 初始化：标准版选中
        updateCardSelection();

        // 设置点击监听
        cardStandard.setOnClickListener(v -> {
            selectedEdition = "standard";
            updateCardSelection();
        });
        cardProfessional.setOnClickListener(v -> {
            selectedEdition = "professional";
            updateCardSelection();
        });
        cardDevelopment.setOnClickListener(v -> {
            selectedEdition = "development";
            updateCardSelection();
        });

        oobe_btn_forward.setOnClickListener(v -> {
            // 检查 activity 是否是 InitCoreActivity，并调用其 switchFragment
            if (requireActivity() instanceof OobeActivity) {
                OobeActivity hostActivity = (OobeActivity) requireActivity();

                String fragment_forward;
                boolean p1 = false;
                if (!p1)
                    fragment_forward = "policies";
                else
                    fragment_forward = "finish";

                AppUtils.setAppVersionMode(requireContext(), selectedEdition);

                hostActivity.switchFragment(fragment_forward);
            }
        });

        oobe_btn_back.setOnClickListener(v -> exitApp());

        return view;
    }

    private void updateCardSelection() {
        // 获取 colorPrimary（选中色）
        TypedValue typedValue = new TypedValue();
        requireContext().getTheme().resolveAttribute(android.R.attr.colorPrimary, typedValue, true);
        int selectedStroke = typedValue.data;

        // 获取未选中颜色（从 colors.xml）
        int unselectedStroke = ContextCompat.getColor(requireContext(), R.color.outline_unselected);

        cardStandard.setStrokeColor(selectedEdition.equals("standard") ? selectedStroke : unselectedStroke);
        cardProfessional.setStrokeColor(selectedEdition.equals("professional") ? selectedStroke : unselectedStroke);
        cardDevelopment.setStrokeColor(selectedEdition.equals("development") ? selectedStroke : unselectedStroke);
    }

    private void exitApp(){
        // 创建对话框（关键：使用 MaterialAlertDialogBuilder 但自定义按钮行为）
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(requireActivity())
                    .setTitle("提示")
                    .setMessage("您确定要退出应用吗？")
                    .setPositiveButton("退出", (dialog, which) -> requireActivity().finish())
                    .setNegativeButton("返回", null)
                    .setCancelable(false); // 防止点击空白区域关闭

            // 创建对话框实例
            AlertDialog dialog = builder.create();

            // 显示对话框
            dialog.show();
    }
}