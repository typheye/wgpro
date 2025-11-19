package com.typheye.wgpro.ui.main.mainFragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.utils.tAccUtils;

public class HomeFragment extends Fragment {

    private tAccUtils accUtils;
    TextView home_text_hello_title;
    TextView home_text_notice_text;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accUtils = new tAccUtils(getActivity());
    }

    @SuppressLint("SetTextI18n")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        home_text_hello_title = view.findViewById(R.id.text_hello_title);
        home_text_notice_text = view.findViewById(R.id.text_notice_text);
        Button home_btn_help_play = view.findViewById(R.id.btn_help_play);
        Button home_btn_notice_more = view.findViewById(R.id.btn_notice_more);
        Button home_btn_fun_airpush = view.findViewById(R.id.btn_fun_airpush);
        Button home_btn_help_support = view.findViewById(R.id.btn_help_support);

        String nick = accUtils.getNick();
        if (accUtils.isLogin()) home_text_hello_title.setText("你好，" + nick);

        home_btn_help_play.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WebActivity.class);
            intent.putExtra("URL", "https://wgpro.typheye.cn/docs");
            startActivity(intent);
        });

        home_btn_fun_airpush.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WebActivity.class);
            intent.putExtra("URL", "https://wgpro.typheye.cn/push");
            startActivity(intent);
        });

        home_btn_notice_more.setOnClickListener(v -> new MaterialAlertDialogBuilder(requireContext())
                .setTitle("应用公告")
                .setMessage(home_text_notice_text.getText())
                .setPositiveButton("确定", null)
                .show());

        home_btn_help_support.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WebActivity.class);
            intent.putExtra("URL", "https://afdian.com/a/typheye");
            startActivity(intent);
        });

        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onResume() {
        super.onResume();
        updataUIShow();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            // 当Fragment变为可见时执行
            updataUIShow();
        }
    }

    @SuppressLint("SetTextI18n")
    private void updataUIShow(){
        String nick = accUtils.getNick();
        if (accUtils.isLogin()) home_text_hello_title.setText("你好，" + nick);
        else home_text_hello_title.setText("你好，新用户");
        home_text_notice_text.setText(AppUtils.getAppNotice(requireContext()));
    }
}