package com.typheye.wgpro.ui.main.mainFragments;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.typheye.wgpro.ui.function.account.AccMangerActivity;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.utils.tAccUtils;

import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class AccountFragment extends Fragment {
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private tAccUtils accUtils;
    private CardView account_card_loginless;
    private Button account_btn_sign_in;
    private Button account_btn_sign_up;
    private LinearLayout account_linear_logined;
    private Button account_btn_edit;
    private Button account_btn_logout;
    private Button account_btn_goto_help;
    private Button account_btn_account_manger_device;
    private TextView account_text_usr_icon;
    private TextView account_text_usr_uid;
    private TextView account_text_usr_nick;
    private TextView account_text_usr_shuo;
    private TextView account_text_usr_login_device_info;
    private ImageView account_image_usr_icon;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accUtils = new tAccUtils(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        // 初始化所有控件
        account_card_loginless = view.findViewById(R.id.card_loginless);
        account_linear_logined = view.findViewById(R.id.linear_logined);
        account_btn_sign_in = view.findViewById(R.id.btn_account_sign_in);
        account_btn_sign_up = view.findViewById(R.id.btn_account_sign_up);
        account_btn_edit = view.findViewById(R.id.btn_account_edit);
        account_btn_logout = view.findViewById(R.id.btn_account_logout);
        account_btn_goto_help = view.findViewById(R.id.btn_account_goto_help);
        account_btn_account_manger_device = view.findViewById(R.id.btn_account_manger_device);
        account_text_usr_icon = view.findViewById(R.id.text_usr_icon);
        account_text_usr_uid = view.findViewById(R.id.text_usr_uid);
        account_text_usr_nick = view.findViewById(R.id.text_usr_nick);
        account_text_usr_shuo = view.findViewById(R.id.text_usr_shuo);
        account_text_usr_login_device_info = view.findViewById(R.id.text_usr_login_device_info);
        account_image_usr_icon = view.findViewById(R.id.image_usr_icon);

        updateUI();
        setupListeners();
        return view;
    }

    private void showMoreMenu() {
        new MaterialAlertDialogBuilder(requireActivity())
                .setTitle("登录")
                .setItems(new CharSequence[]{
                        "通过账号密码登录",
                        "通过其他设备扫码登录",
                }, (dialog, which) -> {
                    Intent intent;
                    switch (which) {
                        case 0: // 通过账号密码登录
                            intent = new Intent(requireContext(), AccMangerActivity.class);
                            intent.putExtra("TARGET_FRAGMENT", "login");
                            startActivity(intent);
                            break;
                        case 1: // 通过其他设备扫码登录
                            intent = new Intent(requireContext(), AccMangerActivity.class);
                            intent.putExtra("TARGET_FRAGMENT", "login_qr");
                            startActivity(intent);
                            break;
                    }
                })
                .show();
    }

    private void setupListeners() {
        account_btn_sign_in.setOnClickListener(v -> showMoreMenu());

        account_btn_sign_up.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WebActivity.class);
            intent.putExtra("URL", "https://service.typheye.cn/site/user/center/register/");
            startActivity(intent);
        });

        account_btn_edit.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), AccMangerActivity.class);
            intent.putExtra("TARGET_FRAGMENT", "edit");
            startActivity(intent);
        });

        account_btn_account_manger_device.setOnClickListener(v -> new MaterialAlertDialogBuilder(requireContext())
                .setTitle("提示")
                .setMessage("敬请期待")
                .setPositiveButton("确定", null)
                .show());

        account_btn_logout.setOnClickListener(v -> new MaterialAlertDialogBuilder(requireContext())
                .setTitle("退出登录")
                .setMessage("确定要退出登录吗？此操作将清除所有本地账户数据")
                .setPositiveButton("确定", (dialog, which) -> {
                    accUtils.logout();
                    updateUI();
                    Toast.makeText(requireContext(), "已退出登录", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("取消", null)
                .show());

        account_btn_goto_help.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WebActivity.class);
            intent.putExtra("URL", "https://service.typheye.cn/site/user/center/");
            startActivity(intent);
        });
    }

    @SuppressLint("SetTextI18n")
    private void updateUI() {
        if (accUtils.isLogin()) {
            account_card_loginless.setVisibility(View.GONE);
            account_linear_logined.setVisibility(View.VISIBLE);

            String uid = accUtils.getUid();
            String nick = accUtils.getNick();
            String shuo = accUtils.getShuo();

            account_text_usr_uid.setText(uid != null ? "UID: " + uid : "UID: 未知");
            account_text_usr_nick.setText(nick != null && !nick.isEmpty() ? nick : "用户");

            String cleanShuo = shuo != null ? shuo.trim() : "";
            account_text_usr_shuo.setText(cleanShuo.isEmpty() ? "暂未设置" : cleanShuo);
            account_text_usr_login_device_info.setText("当前设备: Android " + android.os.Build.VERSION.RELEASE);

            // ====== 关键修复：直接加载头像，不隐藏头像区域 ======
            loadAvatarFromCache(uid);
        } else {
            account_card_loginless.setVisibility(View.VISIBLE);
            account_linear_logined.setVisibility(View.GONE);
            account_text_usr_uid.setText("");
            account_text_usr_nick.setText("");
            account_text_usr_shuo.setText("");
            // 未登录状态直接显示文字头像
            account_text_usr_icon.setText("U");
            account_text_usr_icon.setVisibility(View.VISIBLE);
            account_image_usr_icon.setVisibility(View.GONE);
        }
    }

    // ====== 核心修复：本地头像存在时直接显示，不显示文字头像 ======
    private void loadAvatarFromCache(String uid) {
        if (uid == null || uid.isEmpty()) {
            showDefaultTextAvatar();
            return;
        }

        File cacheDir = requireActivity().getFilesDir();
        File cacheFile = new File(cacheDir, "avatar_" + uid + ".jpg");

        // 1. 本地头像存在且有效 → 直接显示图片头像
        if (cacheFile.exists() && isValidAvatarFile(cacheFile)) {
            try {
                Bitmap bitmap = BitmapFactory.decodeFile(cacheFile.getAbsolutePath());
                if (bitmap != null && !bitmap.isRecycled()) {
                    new Handler(Looper.getMainLooper()).post(() -> {
                        account_image_usr_icon.setImageBitmap(bitmap);
                        account_image_usr_icon.setVisibility(View.VISIBLE);
                        account_text_usr_icon.setVisibility(View.GONE);
                    });
                    return; // 本地头像已加载，直接返回
                }
            } catch (Exception e) {
                // 文件损坏，回退到文字头像
            }
        }

        // 2. 本地头像不存在或无效 → 显示文字头像
        showTextAvatar();
    }

    // 检查头像文件是否有效（非空且非损坏）
    private boolean isValidAvatarFile(File file) {
        return file.exists() && file.length() > 0;
    }

    // 显示文字头像（不隐藏头像区域，直接显示）
    private void showTextAvatar() {
        String nick = accUtils.getNick();
        String initial;
        if (nick != null && !nick.isEmpty()) {
            initial = nick.substring(0, 1).toUpperCase();
        } else {
            initial = "U";
        }

        new Handler(Looper.getMainLooper()).post(() -> {
            account_text_usr_icon.setText(initial);
            account_text_usr_icon.setVisibility(View.VISIBLE);
            account_image_usr_icon.setVisibility(View.GONE);
        });
    }

    // 未登录状态的默认文字头像
    private void showDefaultTextAvatar() {
        new Handler(Looper.getMainLooper()).post(() -> {
            account_text_usr_icon.setText("U");
            account_text_usr_icon.setVisibility(View.VISIBLE);
            account_image_usr_icon.setVisibility(View.GONE);
        });
    }

    // 检查服务器头像更新
    private void checkAvatarAndUpdate() {
        String uid = accUtils.getUid();
        if (uid == null || uid.isEmpty()) return;

        // 1. 检查本地头像是否存在（用于计算MD5）
        File cacheDir = requireActivity().getFilesDir();
        File cacheFile = new File(cacheDir, "avatar_" + uid + ".jpg");
        String md5Str = cacheFile.exists() ? getMd5OfFile(cacheFile) : "none";

        // 2. 构建API请求
        String apiUrl = "https://service.typheye.cn/api.php?type=get_avatar&uid=" + uid + "&md5=" + md5Str;

        Request request = new Request.Builder()
                .url(apiUrl)
                .header("User-Agent", "Mozilla/5.0 (Linux; Android 13; Pixel 7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36")
                .build();

        accUtils.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 网络失败，保持当前头像
                new Handler(Looper.getMainLooper()).post(() -> {
                    // 不做任何操作，保持当前头像
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (response.isSuccessful()) {
                    try {
                        String responseStr = response.body().string();
                        JSONObject json = new JSONObject(responseStr);
                        int code = json.getInt("code");
                        if (code == 200) {
                            JSONObject info = json.getJSONObject("info");
                            boolean isHave = info.getBoolean("isHave");
                            boolean isUpdated = info.getBoolean("isUpdated");
                            String url = info.getString("url");

                            // 仅当需要更新时才下载
                            if (isHave && !isUpdated) {
                                downloadAvatar(uid, url);
                            }
                        }
                    } catch (Exception e) {
                        // 解析失败，保持当前头像
                    }
                }
            }
        });
    }

    // 保存头像到缓存
    private void downloadAvatar(String uid, String url) {
        Request request = new Request.Builder()
                .url(url)
                .header("User-Agent", "Mozilla/5.0 (Linux; Android 13; Pixel 7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36")
                .build();

        accUtils.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                // 下载失败，保持当前头像
                new Handler(Looper.getMainLooper()).post(() -> {
                    // 不做任何操作
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                if (response.isSuccessful()) {
                    try {
                        byte[] bytes = response.body().bytes();
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        if (bitmap != null && !bitmap.isRecycled()) {
                            // 保存到缓存
                            tAccUtils.saveAvatarToCache(requireActivity(), uid, bitmap);

                            new Handler(Looper.getMainLooper()).post(() -> {
                                // 直接更新头像（不会闪烁，因为已有头像显示）
                                account_image_usr_icon.setImageBitmap(bitmap);
                                account_image_usr_icon.setVisibility(View.VISIBLE);
                                account_text_usr_icon.setVisibility(View.GONE);
                            });
                        }
                    } catch (Exception e) {
                        // 下载失败，保持当前头像
                    }
                }
            }
        });
    }

    private String getMd5OfFile(File file) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[8192];
                int read;
                while ((read = fis.read(buffer)) != -1) {
                    md.update(buffer, 0, read);
                }
            }
            byte[] hash = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            return "none";
        }
    }

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

    private void updataUIShow() {
        updateUI();
        // 仅在登录状态下检查头像
        if (accUtils.isLogin()) {
            // 调用 MainActivity 提供的公共方法
            new Thread(this::whileUpdateBeta).start();
            updateUI();
            checkAvatarAndUpdate();
        }
    }

    public void whileUpdateBeta(){
        // 简单调用，不需要处理dialog，因为内部已经处理
        accUtils.getUserDataUpdateJson(new tAccUtils.UserDataUpdateCallback() {
            @Override
            public void onSuccess(tAccUtils.UserDataUpdateResult result) {
                if (!result.isLoginValid) {
                    // 登录状态失效
                    mainHandler.post(() -> updateUI());
                }
            }
            @Override
            public void onError(String message) {
            }
        });
        // 简单调用，不需要处理dialog，因为内部已经处理
        accUtils.updateUserData(new tAccUtils.SetCallback() {
            @Override
            public void onSuccess() {
                mainHandler.post(() -> updateUI());
            }
            @Override
            public void onError(String message) {
            }
        });
    }
}