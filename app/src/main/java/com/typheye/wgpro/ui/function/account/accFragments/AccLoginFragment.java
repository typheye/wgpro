package com.typheye.wgpro.ui.function.account.accFragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.tAccUtils;

import java.util.Objects;

public class AccLoginFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_acclogin, container, false);

        TextInputEditText edittext_account = view.findViewById(R.id.editText_account);
        TextInputEditText edittext_password = view.findViewById(R.id.editText_password);
        Button button_login = view.findViewById(R.id.button_login);


        button_login.setOnClickListener(v -> {
            // 防止重复点击
            v.setEnabled(false);
            v.postDelayed(() -> v.setEnabled(true), 2000); // 2秒内不可重复点击

            String username = Objects.requireNonNull(edittext_account.getText()).toString().trim();
            String password = Objects.requireNonNull(edittext_password.getText()).toString().trim();

            if (username.isEmpty()) {
                Toast.makeText(requireContext(), "请输入用户名", Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.isEmpty()) {
                Toast.makeText(requireContext(), "请输入密码", Toast.LENGTH_SHORT).show();
                return;
            }

                tAccUtils accUtils = new tAccUtils(requireContext()); // 单例仍可用，但不再依赖它持有 context 显示 dialog
                accUtils.login( username, password, new tAccUtils.LoginCallback() {
                    @Override
                    public void onSuccess(tAccUtils.LoginResult result) {
                        // ✅ 修复：在后台线程获取昵称，避免阻塞主线程
                        requireActivity().finish();
                    }

                    @Override
                    public void onError(String message) {
                        new Handler(Looper.getMainLooper()).post(() -> {
                            if (isAdded() && getContext() != null) {
                                new MaterialAlertDialogBuilder(requireContext())
                                        .setTitle("登录失败")
                                        .setCancelable(false)
                                        .setMessage(message)
                                        .setPositiveButton("确定", null)
                                        .show();
                            }
                        });
                    }
                });
        });

        return view;
    }

}