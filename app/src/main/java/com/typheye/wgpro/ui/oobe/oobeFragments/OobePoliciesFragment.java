package com.typheye.wgpro.ui.oobe.oobeFragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.ui.oobe.OobeActivity;

public class OobePoliciesFragment extends Fragment {
    FloatingActionButton oobe_btn_forward;
    Button oobe_btn_approve;
    boolean isGoPolicies = false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_oobe_policies, container, false);

        oobe_btn_approve = view.findViewById(R.id.button_approve);
        FloatingActionButton oobe_btn_back = view.findViewById(R.id.btn_back);
        oobe_btn_forward = view.findViewById(R.id.btn_forward);

        oobe_btn_back.setVisibility(View.VISIBLE);
        updateUIShow();

        oobe_btn_approve.setOnClickListener(v -> goPolicy());
        oobe_btn_forward.setOnClickListener(v -> goForward(true));
        oobe_btn_back.setOnClickListener(v -> goForward(false));

        return view;
    }

    private void goForward(boolean isForward){
        // 检查 activity 是否是 InitCoreActivity，并调用其 switchFragment
        if (requireActivity() instanceof OobeActivity) {
            OobeActivity hostActivity = (OobeActivity) requireActivity();

            String fragment_back = "welcome";
            String fragment_forward;
            if (false);
            else
                fragment_forward = "finish";

            hostActivity.switchFragment(isForward ? fragment_forward : fragment_back);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onResume() {
        super.onResume();
        updateUIShow();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            // 当Fragment变为可见时执行
            updateUIShow();
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateUIShow(){
        oobe_btn_approve.setText(isGoPolicies ? "已阅读并同意" : "前往查看" );
        oobe_btn_forward.setVisibility(isGoPolicies ? View.VISIBLE : View.GONE);
//        if(isGoPolicies) goForward(true);
    }


    void goPolicy(){
        isGoPolicies = true;
        Intent intent = new Intent(requireActivity(), WebActivity.class);
        intent.putExtra("URL", "https://www.typheye.cn/policies/");
        startActivity(intent);
    }
}