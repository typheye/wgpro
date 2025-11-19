package com.typheye.wgpro.ui.function.account.accFragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.typheye.wgpro.ui.function.account.AccMangerActivity;
import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.tAccUtils;

public class AccGrantFragment extends Fragment {

    private tAccUtils accUtils;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    private Button button_approve;
    private Button button_reject;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        accUtils = new tAccUtils(requireContext());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_accgrant, container, false);

        AccMangerActivity activity_accmanger = (AccMangerActivity) requireActivity();

        button_approve = view.findViewById(R.id.button_approve);
        button_reject = view.findViewById(R.id.button_reject);

        button_approve.setOnClickListener(v -> {
            // 防止重复点击
            v.setEnabled(false);
            v.postDelayed(() -> v.setEnabled(true), 2000); // 2秒内不可重复点击
            runPostLoginRequest(activity_accmanger.grant_requestId, true);
        });

        button_reject.setOnClickListener(v -> {
            // 防止重复点击
            v.setEnabled(false);
            v.postDelayed(() -> v.setEnabled(true), 2000); // 2秒内不可重复点击
            runPostLoginRequest(activity_accmanger.grant_requestId, false);
        });

        runConfirmLoginRequest(activity_accmanger.grant_requestId);

        return view;
    }

    private void runConfirmLoginRequest(String requestId){
        accUtils.goConfirmLoginRequest(requestId, new tAccUtils.SetCallback() {
            @Override
            public void onSuccess() {
                mainHandler.post(() -> {
                    button_approve.setVisibility(View.VISIBLE);
                    button_reject.setVisibility(View.VISIBLE);
                });
            }

            @Override
            public void onError(String message) {
                mainHandler.post(() -> showFailToast(message));
            }

        });
    }

    private void runPostLoginRequest(String requestId, boolean isApprove){
        accUtils.postLoginRequest(requestId, isApprove, new tAccUtils.SetCallback() {
            @Override
            public void onSuccess() {
                mainHandler.post(() -> showSuccessToast(isApprove? "已授权登录" : "已拒绝登录"));
            }

            @Override
            public void onError(String message) {
                mainHandler.post(() -> showErrorToast(message));
            }

        });
    }

    private void showSuccessToast(String message) {
        android.util.Log.d("AccEdit", "Success: " + message);
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("授权成功")
                .setMessage(message)
                .setPositiveButton("确定", (dialog, which) -> requireActivity().finish())
                .show();
    }

    private void showErrorToast(String message) {
        android.util.Log.e("AccEdit", "Error: " + message);
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("授权失败")
                .setMessage(message)
                .setPositiveButton("确定", (dialog, which) -> requireActivity().finish())
                .show();
    }

    private void showFailToast(String message) {
        android.util.Log.e("AccEdit", "Error: " + message);
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("请求失败")
                .setMessage(message)
                .setPositiveButton("确定", (dialog, which) -> requireActivity().finish())
                .show();
    }
}