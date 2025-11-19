package com.typheye.wgpro.ui.function.account.accFragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.typheye.wgpro.utils.ImageUtils;
import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.tAccUtils;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class AccLoginQRFragment extends Fragment {
    String request_id;
    String request_states;
    TextView statusText;
    CardView card_QRCode;
    ImageView image_QRCode;
    Button button_cancel;
    Boolean whileLogin = true;
    private long startTime;
    // ===== 修改成员变量 =====
    private Handler handler;
    private Runnable loginCheckRunnable;
    private boolean isListening = true; // 新增状态标记

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 确保初始状态为正在监听
        isListening = true;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_acclogin_qr, container, false);

        button_cancel = view.findViewById(R.id.button_cancel);
        statusText = view.findViewById(R.id.textView_text);
        card_QRCode = view.findViewById(R.id.card_qrcode);
        image_QRCode = view.findViewById(R.id.image_qrcode);

        // 在 button_cancel 的点击事件中
        button_cancel.setOnClickListener(v -> {
            // 防止重复点击
            v.setEnabled(false);
            new Handler(Looper.getMainLooper()).post(() -> {
                card_QRCode.setVisibility(View.GONE);
                statusText.setVisibility(View.GONE);
                v.setVisibility(View.GONE);
            });
            v.postDelayed(() -> v.setEnabled(true), 2000); // 2秒内不可重复点击
            whileLogin = false;
        });

        readyLogin();

        // 初始化 Handler
        handler = new Handler(Looper.getMainLooper());
        return view;
    }

    // 修改 readyLogin 方法
    private void readyLogin() {
        card_QRCode.setVisibility(View.GONE);
        statusText.setText("正在加载中...");

        tAccUtils accUtils = new tAccUtils(requireContext());
        accUtils.generateLoginRequest(new tAccUtils.GenerateRequestCallback() {
            @Override
            public void onSuccess(String requestId, String qrCodeUrl) {
                request_id = requestId;

                // 加载二维码
                card_QRCode.setVisibility(View.VISIBLE);
                statusText.setText("请使用已登录设备扫码");
                // 加载二维码图片
                loadQRCode(qrCodeUrl);

                // 开始监听
                haltLogin();
            }

            @Override
            public void onError(String message) {
                shorErrMsg("生成登录请求失败: " + message);
            }
        });
    }

    private void loadQRCode(String url) {

        Bitmap bitmap= ImageUtils.createQRCodeBitmap(url, 800, 800,"UTF-8","H", "1", ContextCompat.getColor(requireContext(), R.color.tab_accent_th2), ContextCompat.getColor(requireContext(), R.color.tab_accent));
        image_QRCode.setImageBitmap(bitmap);
    }

    private void haltLogin() {
        if (loginCheckRunnable != null) {
            handler.removeCallbacks(loginCheckRunnable);
        }

        loginCheckRunnable = () -> {
            if (!isListening) return;


            if (!whileLogin) {
                shorErrMsg("用户主动退出");
                return;
            }

            // 300秒超时（5分钟）
            if (System.currentTimeMillis() - startTime > 300000) {
                shorErrMsg("登录超时，请重新扫码");
                return;
            }

            tAccUtils accUtils = new tAccUtils(requireContext());
            accUtils.checkLoginRequestStatus(request_id, new tAccUtils.CheckStatusCallback() {
                @Override
                public void onSuccess(String status) {
                    request_states = status;

                    if ("pending".equals(status) || "waiting".equals(status)) {
                        // ✅ 修复：使用 loginCheckRunnable 而非 requireContext()
                        handler.postDelayed(loginCheckRunnable, 3000);
                        if ("waiting".equals(status)) {
                            //二维码已被扫描，等待被用户操作
                            new Handler(Looper.getMainLooper()).post(() -> {
                                card_QRCode.setVisibility(View.GONE);
                                statusText.setText("已扫描 等待用户操作");
                            });
                        }
                    } else if ("rejected".equals(status)) {
                        shorErrMsg("用户拒绝登录");
                    } else if ("approved".equals(status)) {
                        new Handler(Looper.getMainLooper()).post(() -> {
                            card_QRCode.setVisibility(View.GONE);
                            statusText.setVisibility(View.GONE);
                            button_cancel.setVisibility(View.GONE);
                        });
                        requireActivity().finish();
                    }
                }
                @Override
                public void onError(String message) {
                    shorErrMsg("检查状态失败: " + message);
                }
            });
        };

        startTime = System.currentTimeMillis();
        handler.post(loginCheckRunnable);
    }

    private void shorErrMsg(String message) {
        new Handler(Looper.getMainLooper()).post(() -> {
            card_QRCode.setVisibility(View.GONE);
            statusText.setVisibility(View.GONE);
            button_cancel.setVisibility(View.GONE);
            if (isAdded() && getContext() != null) {
                new MaterialAlertDialogBuilder(requireContext())
                        .setTitle("登录失败")
                        .setCancelable(false)
                        .setMessage(message)
                        .setPositiveButton("确定", (dialog, which) -> requireActivity().finish())
                        .show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isListening = false; // 停止监听
        if (handler != null) {
            handler.removeCallbacks(loginCheckRunnable);
            handler = null;
        }
    }
}