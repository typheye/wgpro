package com.typheye.wgpro.ui.main.mainFragments;

import android.content.Intent;
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
import com.typheye.wgpro.R;
import com.typheye.wgpro.core.xms.UIParams;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.ui.main.MainActivity;
import com.typheye.wgpro.utils.AppUtils;

public class DashboardFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        LinearLayout dashboard_linear_standard = view.findViewById(R.id.linear_standard);
        LinearLayout dashboard_linear_professional = view.findViewById(R.id.linear_professional);
        LinearLayout dashboard_linear_development = view.findViewById(R.id.linear_development);
        CardView dashboard_card_tips_standard = view.findViewById(R.id.card_tips_standard);
        Button dashboard_btn_open_joinqqun = view.findViewById(R.id.btn_open_joinqqun);
        Button dashboard_btn_open_joinbeta = view.findViewById(R.id.btn_open_joinbeta);
        Button dashboard_btn_open_console = view.findViewById(R.id.btn_open_console);

        switch (AppUtils.getAppVersionMode(requireContext())) {
            case "professional":
                dashboard_card_tips_standard.setVisibility(View.GONE);
                dashboard_linear_standard.setVisibility(View.VISIBLE);
                dashboard_linear_professional.setVisibility(View.VISIBLE);
                dashboard_linear_development.setVisibility(View.GONE);
                break;

            case "development":
                dashboard_card_tips_standard.setVisibility(View.GONE);
                dashboard_linear_standard.setVisibility(View.VISIBLE);
                dashboard_linear_professional.setVisibility(View.VISIBLE);
                dashboard_linear_development.setVisibility(View.VISIBLE);
                break;

            default:
                dashboard_card_tips_standard.setVisibility(View.VISIBLE);
                dashboard_linear_standard.setVisibility(View.VISIBLE);
                dashboard_linear_professional.setVisibility(View.GONE);
                dashboard_linear_development.setVisibility(View.GONE);
                break;
        }

        dashboard_btn_open_joinqqun.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WebActivity.class);
            intent.putExtra("URL", "https://qm.qq.com/q/dVjDbiNmta");
            startActivity(intent);
        });

        dashboard_btn_open_joinbeta.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WebActivity.class);
            intent.putExtra("URL", "https://www.typheye.cn/?p=beta");
            startActivity(intent);
        });

        dashboard_btn_open_console.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WebActivity.class);
            intent.putExtra("URL", "https://www.typheye.cn/admin");
            startActivity(intent);
        });
        return view;
    }

}