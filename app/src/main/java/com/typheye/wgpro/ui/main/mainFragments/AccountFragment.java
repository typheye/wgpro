package com.typheye.wgpro.ui.main.mainFragments;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.Context;
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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.function.account.AccMangerActivity;
import com.typheye.wgpro.ui.function.account.AccountBottomSheets;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.ui.function.account.UserDetailActivity;
import com.typheye.wgpro.ui.function.community.AccountListActivity;
import com.typheye.wgpro.ui.function.community.CloudListFragment;
import com.typheye.wgpro.ui.function.community.ContactActivity;
import com.typheye.wgpro.utils.tAccUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

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
    private View account_btn_goto_help;
    private TextView account_text_usr_icon;
    private TextView account_text_usr_uid;
    private TextView account_text_usr_nick;
    private TextView account_text_usr_shuo;
    private ImageView account_image_usr_icon;
    private View account_content;
    private View account_stats_card;
    private TextView accountIdentityValue;
    private TextView activityCount;
    private TextView followingCount;
    private TextView followersCount;
    private boolean profileRequestInFlight;
    private SwipeRefreshLayout accountRefresh;
    private View accountPageContent;
    private Context appContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appContext = requireContext().getApplicationContext();
        accUtils = new tAccUtils(appContext);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        accountRefresh = view.findViewById(R.id.account_refresh);
        accountPageContent = view.findViewById(R.id.account_page_content);
        accountRefresh.setColorSchemeColors(requireContext().getColor(R.color.brand_primary));
        accountRefresh.setOnRefreshListener(() -> {
            if (getActivity() instanceof com.typheye.wgpro.ui.main.MainActivity) {
                ((com.typheye.wgpro.ui.main.MainActivity) getActivity()).refreshAccountFromUser();
            } else {
                refreshAccountUi(true);
            }
            accountRefresh.postDelayed(() -> accountRefresh.setRefreshing(false), 10000L);
        });
        // 初始化所有控件
        account_card_loginless = view.findViewById(R.id.card_loginless);
        account_linear_logined = view.findViewById(R.id.linear_logined);
        account_btn_sign_in = view.findViewById(R.id.btn_account_sign_in);
        account_btn_sign_up = view.findViewById(R.id.btn_account_sign_up);
        account_btn_edit = view.findViewById(R.id.btn_account_edit);
        account_btn_goto_help = view.findViewById(R.id.btn_account_goto_help);
        account_text_usr_icon = view.findViewById(R.id.text_usr_icon);
        account_text_usr_uid = view.findViewById(R.id.text_usr_uid);
        account_text_usr_nick = view.findViewById(R.id.text_usr_nick);
        account_text_usr_shuo = view.findViewById(R.id.text_usr_shuo);
        account_image_usr_icon = view.findViewById(R.id.image_usr_icon);
        account_content = view.findViewById(R.id.account_content);
        account_stats_card = view.findViewById(R.id.account_stats_card);
        setGroupRowTitle(view, R.id.account_history, "浏览历史");
        setGroupRowTitle(view, R.id.account_favorites, "我的收藏");
        setGroupRowTitle(view, R.id.account_starred_apps, "星标应用");
        setGroupRowTitle(view, R.id.account_resources, "星标资源");
        setGroupRowTitle(view, R.id.account_identity, "社区身份");
        accountIdentityValue = view.findViewById(R.id.account_identity)
                .findViewById(R.id.account_row_value);
        accountIdentityValue.setText("普通用户");
        accountIdentityValue.setVisibility(View.VISIBLE);
        activityCount = view.findViewById(R.id.account_activity_count);
        followingCount = view.findViewById(R.id.account_following_count);
        followersCount = view.findViewById(R.id.account_followers_count);
        setGroupRowTitle(view, R.id.account_creator_center, "创作中心");
        view.findViewById(R.id.profile_card).setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), UserDetailActivity.class);
            startActivity(intent);
        });

        updateUI();
        setupListeners(view);
        return view;
    }

    private void showMoreMenu() {
        new WGProAlertDialogBuilder(requireActivity())
                .setTitle("登录")
                .setItems(new CharSequence[]{
                        "通过账号密码登录",
                        "通过其他设备扫码登录",
                }, (dialog, which) -> {
                    if (which == 0) {
                        AccountBottomSheets.showPasswordLogin(requireActivity(), this::onAccountChanged);
                    } else {
                        AccountBottomSheets.showQrLogin(requireActivity(), this::onAccountChanged);
                    }
                })
                .show();
    }

    private void setupListeners(View root) {
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

        account_btn_goto_help.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WebActivity.class);
            intent.putExtra("URL", "https://account.typheye.cn/?tab=security");
            startActivity(intent);
        });

        root.findViewById(R.id.account_identity).setOnClickListener(v -> new WGProAlertDialogBuilder(requireContext())
                .setTitle("社区身份")
                .setMessage(accountIdentityValue.getText())
                .setPositiveButton("完成", null)
                .show());
        root.findViewById(R.id.account_activity).setOnClickListener(v ->
                openAccountList("我的动态", CloudListFragment.MODE_ACTIVITY));
        root.findViewById(R.id.account_following).setOnClickListener(v ->
                openContacts(CloudListFragment.MODE_FOLLOWING, numberOf(followingCount)));
        root.findViewById(R.id.account_followers).setOnClickListener(v ->
                openContacts(CloudListFragment.MODE_FOLLOWERS, numberOf(followersCount)));
        root.findViewById(R.id.account_history).setOnClickListener(v -> openAccountList("浏览历史", CloudListFragment.MODE_HISTORY));
        root.findViewById(R.id.account_favorites).setOnClickListener(v -> openAccountList("我的收藏", CloudListFragment.MODE_COLLECTION_DYNAMIC));
        root.findViewById(R.id.account_starred_apps).setOnClickListener(v -> openAccountList("星标应用", CloudListFragment.MODE_COLLECTION_APP));
        root.findViewById(R.id.account_resources).setOnClickListener(v -> openAccountList("星标资源", CloudListFragment.MODE_COLLECTION_RESOURCE));
        root.findViewById(R.id.account_creator_center).setOnClickListener(v -> showCreatorStats());
    }

    private void setGroupRowTitle(View root, int rowId, String title) {
        View row = root.findViewById(rowId);
        TextView label = row == null ? null : row.findViewById(R.id.account_row_title);
        if (label != null) label.setText(title);
    }

    private void openUserDetail() {
        startActivity(new Intent(requireContext(), UserDetailActivity.class));
    }

    private int numberOf(TextView view) {
        try { return Integer.parseInt(view.getText().toString()); }
        catch (Exception ignored) { return -1; }
    }

    private void openContacts(String mode, int expectedCount) {
        startActivity(new Intent(requireContext(), ContactActivity.class)
                .putExtra(ContactActivity.EXTRA_MODE, mode)
                .putExtra(ContactActivity.EXTRA_EXPECTED_COUNT, expectedCount));
    }

    private void openAccountList(String title, String mode) {
        startActivity(new Intent(requireContext(), AccountListActivity.class)
                .putExtra(AccountListActivity.EXTRA_TITLE, title)
                .putExtra(AccountListActivity.EXTRA_MODE, mode));
    }

    private void showCloudList(String title, String action, @Nullable String targetType) {
        Map<String, String> query = new LinkedHashMap<>();
        query.put("page", "1");
        query.put("size", "20");
        if (targetType != null) query.put("target_type", targetType);
        accUtils.getV2Json(action, query, true, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                mainHandler.post(() -> {
                    if (!isAdded()) return;
                    JSONArray items = json.optJSONArray("items");
                    String message = formatCloudItems(items);
                    new WGProAlertDialogBuilder(requireContext()).setTitle(title).setMessage(message)
                            .setNegativeButton("关闭", null).show();
                });
            }

            @Override public void onError(int code, @NonNull String message) {
                mainHandler.post(() -> {
                    if (!isAdded()) return;
                    new WGProAlertDialogBuilder(requireContext()).setTitle("加载失败")
                            .setMessage(message).setNegativeButton("关闭", null).show();
                });
            }
        });
    }

    private String formatCloudItems(@Nullable JSONArray items) {
        if (items == null || items.length() == 0) return "这里暂时还没有内容。";
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < items.length(); index++) {
            JSONObject item = items.optJSONObject(index);
            if (item == null) continue;
            JSONObject target = item.optJSONObject("target");
            if (target == null) target = item;
            String label = target.optString("title", target.optString("name",
                    target.optString("content", ""))).trim();
            if (label.isEmpty()) {
                String type = item.optString("target_type", "内容");
                String key = item.optString("target_key", "");
                label = key.isEmpty() ? "未命名内容" : type + "  " + key;
            }
            if (label.length() > 44) label = label.substring(0, 44) + "…";
            if (result.length() > 0) result.append("\n\n");
            result.append(label);
            String time = item.optString("created_at", item.optString("last_viewed_at", ""));
            if (!time.isEmpty()) result.append("\n").append(time);
        }
        return result.length() == 0 ? "这里暂时还没有内容。" : result.toString();
    }

    private void showCreatorStats() {
        accUtils.getV2Json("creator_stats2", Collections.emptyMap(), true, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                mainHandler.post(() -> {
                    if (!isAdded()) return;
                    JSONObject info = json.optJSONObject("info");
                    if (info == null) info = new JSONObject();
                    String message = "动态  " + info.optInt("dynamic_count")
                            + "\n资源  " + info.optInt("resource_count")
                            + "\n审核中  " + info.optInt("pending_count")
                            + "\n未通过  " + info.optInt("rejected_count");
                    new WGProAlertDialogBuilder(requireContext()).setTitle("创作中心")
                            .setMessage(message).setNegativeButton("关闭", null).show();
                });
            }

            @Override public void onError(int code, @NonNull String message) {
                mainHandler.post(() -> {
                    if (!isAdded()) return;
                    new WGProAlertDialogBuilder(requireContext()).setTitle("加载失败")
                            .setMessage(message).setNegativeButton("关闭", null).show();
                });
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void updateUI() {
        if (!isAdded() || getView() == null) return;
        accountPageContent.setVisibility(View.VISIBLE);
        if (accUtils.isLogin()) {
            viewServicesVisibility(View.VISIBLE);
            account_card_loginless.setVisibility(View.GONE);
            account_linear_logined.setVisibility(View.VISIBLE);
            account_btn_edit.setVisibility(View.VISIBLE);
            account_stats_card.setVisibility(View.VISIBLE);
            account_content.setVisibility(View.VISIBLE);
            account_btn_goto_help.setVisibility(View.VISIBLE);

            String uid = accUtils.getUid();
            String nick = accUtils.getNick();
            String shuo = accUtils.getShuo();

            account_text_usr_uid.setText(uid != null && !uid.isEmpty() ? "UID " + uid : "UID 未知");
            account_text_usr_nick.setText(nick != null && !nick.isEmpty() ? nick : "用户");

            String cleanShuo = shuo != null ? shuo.trim() : "";
            account_text_usr_shuo.setText(cleanShuo.isEmpty() ? "这个人还没有简介呢~" : cleanShuo);

            // ====== 关键修复：直接加载头像，不隐藏头像区域 ======
            loadAvatarFromCache(uid);
            loadPublicProfile();
        } else {
            profileRequestInFlight = false;
            account_card_loginless.setVisibility(View.VISIBLE);
            account_linear_logined.setVisibility(View.GONE);
            account_stats_card.setVisibility(View.GONE);
            account_content.setVisibility(View.GONE);
            account_btn_goto_help.setVisibility(View.GONE);
            viewServicesVisibility(View.GONE);
            account_text_usr_uid.setText("");
            account_text_usr_nick.setText("");
            account_text_usr_shuo.setText("");
            // 未登录状态直接显示文字头像
            account_text_usr_icon.setText("U");
            account_text_usr_icon.setVisibility(View.VISIBLE);
            account_image_usr_icon.setVisibility(View.GONE);
        }
    }

    private void loadPublicProfile() {
        if (profileRequestInFlight || !isAdded()) return;
        String uid = accUtils.getUid();
        if (uid == null || uid.isEmpty()) return;
        profileRequestInFlight = true;
        accUtils.getV2Json("user_profile2",
                Collections.singletonMap("target_uid", uid), true,
                new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        mainHandler.post(() -> {
                            profileRequestInFlight = false;
                            if (!isAdded() || getView() == null || !accUtils.isLogin()) return;
                            JSONObject info = json.optJSONObject("info");
                            if (info == null) return;
                            activityCount.setText(String.valueOf(info.optInt("dynamic_count", 0)));
                            followingCount.setText(String.valueOf(info.optInt("following_count", 0)));
                            followersCount.setText(String.valueOf(info.optInt("follower_count", 0)));
                            String role = info.optString("role_name", "").trim();
                            accountIdentityValue.setText(role.isEmpty() ? "普通用户" : role);
                        });
                    }

                    @Override public void onError(int statusCode, @NonNull String message) {
                        mainHandler.post(() -> {
                            profileRequestInFlight = false;
                            if (isAdded() && statusCode == 401) updateUI();
                        });
                    }
                });
    }

    public void refreshAccountUi() {
        refreshAccountUi(false);
    }

    public void refreshAccountUi(boolean refreshAvatar) {
        if (isAdded()) updateUI();
        if (refreshAvatar && isAdded() && accUtils.isLogin()) checkAvatarAndUpdate();
        if (accountRefresh != null) accountRefresh.setRefreshing(false);
    }

    private void onAccountChanged() {
        if (!isAdded()) return;
        updateUI();
        if (accUtils.isLogin()) checkAvatarAndUpdate();
        requireActivity().invalidateOptionsMenu();
    }

    private void viewServicesVisibility(int visibility) {
        if (getView() != null) {
            View services = getView().findViewById(R.id.account_services_container);
            if (services != null) services.setVisibility(visibility);
        }
    }

    // ====== 核心修复：本地头像存在时直接显示，不显示文字头像 ======
    private void loadAvatarFromCache(String uid) {
        if (uid == null || uid.isEmpty()) {
            showDefaultTextAvatar();
            return;
        }

        File cacheDir = appContext.getFilesDir();
        File cacheFile = new File(cacheDir, "avatar_" + uid + ".jpg");

        // 1. 本地头像存在且有效 → 直接显示图片头像
        if (cacheFile.exists() && isValidAvatarFile(cacheFile)) {
            try {
                Bitmap bitmap = BitmapFactory.decodeFile(cacheFile.getAbsolutePath());
                if (bitmap != null && !bitmap.isRecycled()) {
                    new Handler(Looper.getMainLooper()).post(() -> {
                        if (!isAdded() || getView() == null) return;
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
            if (!isAdded() || getView() == null) return;
            account_text_usr_icon.setText(initial);
            account_text_usr_icon.setVisibility(View.VISIBLE);
            account_image_usr_icon.setVisibility(View.GONE);
        });
    }

    // 未登录状态的默认文字头像
    private void showDefaultTextAvatar() {
        new Handler(Looper.getMainLooper()).post(() -> {
            if (!isAdded() || getView() == null) return;
            account_text_usr_icon.setText("U");
            account_text_usr_icon.setVisibility(View.VISIBLE);
            account_image_usr_icon.setVisibility(View.GONE);
        });
    }

    // 检查服务器头像更新
    private void checkAvatarAndUpdate() {
        if (!isAdded() || getView() == null) return;
        String uid = accUtils.getUid();
        if (uid == null || uid.isEmpty()) return;

        // 1. 检查本地头像是否存在（用于计算MD5）
        File cacheDir = appContext.getFilesDir();
        File cacheFile = new File(cacheDir, "avatar_" + uid + ".jpg");
        String md5Str = cacheFile.exists() ? getMd5OfFile(cacheFile) : "none";

        accUtils.getAvatarInfo(md5Str, new tAccUtils.AvatarInfoCallback() {
            @Override
            public void onSuccess(boolean hasAvatar, boolean shouldDownload, String url) {
                if (!hasAvatar) {
                    if (cacheFile.exists() && !cacheFile.delete()) {
                        android.util.Log.w("AccountFragment", "Unable to remove stale avatar cache");
                    }
                    showTextAvatar();
                } else if (shouldDownload && url != null && !url.isEmpty()) {
                    downloadAvatar(uid, url);
                }
            }

            @Override
            public void onError(String message) {
                android.util.Log.w("AccountFragment", message);
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
                            tAccUtils.saveAvatarToCache(appContext, uid, bitmap);

                            new Handler(Looper.getMainLooper()).post(() -> {
                                if (!isAdded() || getView() == null) return;
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
    }
}
