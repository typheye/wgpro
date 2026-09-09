package com.typheye.wgpro.ui.function.community;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.RelativeCornerSize;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProBottomSheetDialog;
import com.typheye.wgpro.ui.widget.WGProProgressRunner;
import com.typheye.wgpro.ui.widget.BadgeFactory;
import com.typheye.wgpro.utils.tAccUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public final class DynamicDetailFragment extends Fragment {
    private String dynamicId;
    private tAccUtils account;
    private SwipeRefreshLayout refresh;
    private LinearLayout comments;
    private View empty;
    private MaterialButton likeButton, favoriteButton;
    private boolean loadedOnce, liked, favorited;
    private int likeCount, favoriteCount, pending, generation;
    private String dynamicOwnerUid = "";

    public static DynamicDetailFragment newInstance(String id) {
        DynamicDetailFragment fragment = new DynamicDetailFragment(); Bundle args = new Bundle();
        args.putString("dynamic_id", id == null ? "" : id); fragment.setArguments(args); return fragment;
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater,
                                                  @Nullable ViewGroup container, @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment_dynamic_detail, container, false);
        dynamicId = getArguments() == null ? "" : getArguments().getString("dynamic_id", "");
        account = new tAccUtils(requireContext().getApplicationContext());
        refresh = root.findViewById(R.id.dynamic_refresh); comments = root.findViewById(R.id.comment_list);
        empty = root.findViewById(R.id.comment_empty); likeButton = root.findViewById(R.id.dynamic_like_action);
        favoriteButton = root.findViewById(R.id.dynamic_favorite_action);
        ((TextView) empty.findViewById(R.id.stream_empty_title)).setText("还没有评论");
        ((TextView) empty.findViewById(R.id.stream_empty_description)).setText("说点什么，开启讨论。");
        refresh.setColorSchemeColors(requireContext().getColor(R.color.brand_primary));
        refresh.setOnRefreshListener(() -> load(root));
        root.findViewById(R.id.dynamic_comment_action).setOnClickListener(v -> showCommentSheet(root));
        likeButton.setOnClickListener(v -> toggleLike());
        favoriteButton.setOnClickListener(v -> toggleFavorite());
        load(root); return root;
    }

    private void load(View root) {
        int request = ++generation; pending = 2; empty.setVisibility(View.GONE);
        if (!loadedOnce) ((BaseSectionActivity) requireActivity()).showContentLoading();
        if (dynamicId.isEmpty()) { finishPart(request); finishPart(request); return; }
        Map<String, String> detail = new LinkedHashMap<>(); detail.put("dynamic_id", dynamicId);
        account.getV2Json("dynamic_detail2", detail, false, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) { onUi(() -> {
                bind(root, json.optJSONObject("info")); finishPart(request);
            }); }
            @Override public void onError(int code, @NonNull String message) { onUi(() -> finishPart(request)); }
        });
        Map<String, String> query = new LinkedHashMap<>(detail); query.put("page", "1"); query.put("size", "50");
        account.getV2Json("dynamic_comments2", query, false, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) { onUi(() -> {
                renderComments(json.optJSONArray("items")); finishPart(request);
            }); }
            @Override public void onError(int code, @NonNull String message) { onUi(() -> {
                comments.removeAllViews(); empty.setVisibility(View.VISIBLE); finishPart(request);
            }); }
        });
    }

    private void bind(View root, @Nullable JSONObject info) {
        if (info == null) return;
        if (account.isLogin() && requireContext().getSharedPreferences("history_preferences", android.content.Context.MODE_PRIVATE).getBoolean("enabled", true)) {
            Map<String, String> history = new LinkedHashMap<>();
            history.put("target_type", "dynamic"); history.put("target_key", dynamicId);
            account.postV2Json("history_record2", history, new tAccUtils.JsonCallback() {
                @Override public void onSuccess(@NonNull JSONObject ignored) { }
                @Override public void onError(int code, @NonNull String message) { }
            });
        }
        String nick = info.optString("nick", "Typheye 用户");
        dynamicOwnerUid = info.optString("uid", "");
        ((DynamicDetailActivity) requireActivity()).bindAuthor(info);
        ((TextView) root.findViewById(R.id.dynamic_detail_content)).setText(info.optString("content"));
        ((TextView) root.findViewById(R.id.dynamic_detail_tail)).setText("发布于 "
                + relativeTime(info.optString("created_at")));
        ((TextView) root.findViewById(R.id.dynamic_comments_title)).setText("评论（"
                + Math.max(0, info.optInt("comment_count")) + "）");
        JSONArray media = info.optJSONArray("media"); JSONObject first = media == null ? null : media.optJSONObject(0);
        ImageView image = root.findViewById(R.id.dynamic_detail_media);
        if (first == null) image.setVisibility(View.GONE); else {
            image.setVisibility(View.VISIBLE); DynamicCardFactory.bindImage(requireContext(),
                    first.optString("thumbnail_url", first.optString("original_url")), image);
        }
        liked = info.optBoolean("is_liked"); favorited = info.optBoolean("is_favorited");
        likeCount = info.optInt("like_count"); favoriteCount = info.optInt("collection_count");
        updateActions();
    }

    private void renderComments(@Nullable JSONArray items) {
        comments.removeAllViews();
        if (items != null) for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.optJSONObject(i); if (item != null) comments.addView(comment(item));
        }
        empty.setVisibility(comments.getChildCount() == 0 ? View.VISIBLE : View.GONE);
    }

    private View comment(JSONObject item) {
        LinearLayout row = new LinearLayout(requireContext()); row.setGravity(android.view.Gravity.TOP);
        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(-1, -2); rowParams.bottomMargin = dp(12);
        row.setLayoutParams(rowParams);
        FrameLayout avatarBox = new FrameLayout(requireContext());
        TextView initial = text(first(item.optString("nick", "用户")), 14, true, R.color.brand_on_soft);
        initial.setGravity(android.view.Gravity.CENTER); initial.setBackgroundResource(R.drawable.bg_avatar_placeholder_circle);
        avatarBox.addView(initial, new FrameLayout.LayoutParams(-1, -1));
        ShapeableImageView avatar = new ShapeableImageView(requireContext()); avatar.setScaleType(ImageView.ScaleType.CENTER_CROP);
        avatar.setVisibility(View.GONE); avatar.setShapeAppearanceModel(avatar.getShapeAppearanceModel().toBuilder()
                .setAllCornerSizes(new RelativeCornerSize(0.5f)).build());
        avatarBox.addView(avatar, new FrameLayout.LayoutParams(-1, -1));
        row.addView(avatarBox, new LinearLayout.LayoutParams(dp(36), dp(36)));
        BadgeFactory.bind(requireContext(), item, avatarBox,
                requireContext().getColor(R.color.surface_page));
        DynamicCardFactory.bindAvatar(requireContext(), item.optString("uid"), item.optString("avatar_url"), avatar, initial);

        LinearLayout right = new LinearLayout(requireContext()); right.setOrientation(LinearLayout.VERTICAL);
        LinearLayout author = new LinearLayout(requireContext());
        author.setGravity(android.view.Gravity.CENTER_VERTICAL);
        TextView authorName = text(item.optString("nick", "用户"), 14, true, R.color.text_primary);
        author.addView(authorName, new LinearLayout.LayoutParams(0, -2, 1f));
        TextView commentTime = text(DynamicCardFactory.relativeTime(item.optString("created_at")),
                12, false, R.color.text_secondary);
        commentTime.setGravity(android.view.Gravity.END | android.view.Gravity.CENTER_VERTICAL);
        author.addView(commentTime, new LinearLayout.LayoutParams(-2, -2));
        right.addView(author);
        MaterialCardView bubble = new MaterialCardView(requireContext()); bubble.setRadius(dp(12));
        bubble.setCardElevation(0); bubble.setStrokeWidth(0);
        bubble.setCardBackgroundColor(requireContext().getColor(R.color.surface_primary));
        LinearLayout content = new LinearLayout(requireContext()); content.setOrientation(LinearLayout.VERTICAL);
        content.setPadding(dp(13), dp(10), dp(13), dp(9));
        content.addView(text(item.optString("content"), 15, false, R.color.text_primary)); bubble.addView(content);
        LinearLayout.LayoutParams bubbleParams = new LinearLayout.LayoutParams(-1, -2); bubbleParams.topMargin = dp(4);
        right.addView(bubble, bubbleParams);
        LinearLayout.LayoutParams rightParams = new LinearLayout.LayoutParams(0, -2, 1f); rightParams.setMarginStart(dp(10));
        row.addView(right, rightParams);
        row.setLongClickable(true);
        row.setOnLongClickListener(v -> {
            showCommentMenu(item);
            return true;
        });
        String uid = item.optString("uid", "");
        View.OnClickListener openUser = v -> { if (!uid.isEmpty()) startActivity(new android.content.Intent(
                requireContext(), com.typheye.wgpro.ui.function.account.UserDetailActivity.class)
                .putExtra(com.typheye.wgpro.ui.function.account.UserDetailActivity.EXTRA_TARGET_UID, uid)); };
        avatarBox.setOnClickListener(openUser); author.setOnClickListener(openUser);
        return row;
    }

    private void showCommentSheet(View root) {
        View content = getLayoutInflater().inflate(R.layout.dialog_edittext, null, false);
        TextInputLayout layout = content.findViewById(R.id.textInputLayout);
        TextInputEditText input = content.findViewById(R.id.editText);
        layout.setHint("评论内容"); input.setSingleLine(false); input.setMaxLines(5);
        input.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        new WGProAlertDialogBuilder(requireContext()).setTitle("写评论").setView(content)
                .setNegativeButton("返回", null).setPositiveButton("发送", (dialog, which) -> {
                    String value = input.getText() == null ? "" : input.getText().toString().trim();
                    if (!value.isEmpty()) sendComment(root, value);
                }).show();
    }

    private void sendComment(View root, String value) {
        Map<String, String> fields = new LinkedHashMap<>(); fields.put("dynamic_id", dynamicId);
        fields.put("parent_id", "0"); fields.put("content", value);
        account.postV2Json("dynamic_comment_create2", fields, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) { onUi(() -> load(root)); }
            @Override public void onError(int code, @NonNull String message) { onUi(() -> showError("评论失败", message)); }
        });
    }

    private void showCommentMenu(JSONObject item) {
        if (!isAdded()) return;
        boolean mine = item.optBoolean("is_self")
                || account.getUid().equals(item.optString("uid"));
        boolean dynamicOwner = !dynamicOwnerUid.isEmpty()
                && dynamicOwnerUid.equals(account.getUid());
        boolean canDelete = mine || dynamicOwner;
        java.util.ArrayList<CharSequence> menu = new java.util.ArrayList<>();
        menu.add("评论详情");
        if (!mine) menu.add("举报");
        if (canDelete) menu.add("撤回");
        CharSequence[] actions = menu.toArray(new CharSequence[0]);
        new WGProAlertDialogBuilder(requireContext())
                .setTitle("评论操作").setItems(actions, (dialog, which) -> {
                    String action = actions[which].toString();
                    if ("评论详情".equals(action)) showCommentDetail(item);
                    else if ("举报".equals(action)) reportComment(item);
                    else confirmDeleteComment(item);
                }).show();
    }

    private void reportComment(JSONObject item) {
        String id = item.optString("id", "");
        if (id.isEmpty()) return;
        String summary = item.optString("content", "").trim();
        if (summary.isEmpty()) summary = "评论 #" + id;
        if (summary.length() > 40) summary = summary.substring(0, 40) + "…";
        ReportActivity.open(requireContext(), "comment", id, summary);
    }

    private void showCommentDetail(JSONObject item) {
        StringBuilder detail = new StringBuilder();
        detail.append("评论者：").append(item.optString("nick", "用户")).append('\n');
        detail.append("评论时间：").append(item.optString("created_at", "未知")).append('\n');
        detail.append("评论 ID：").append(item.optString("id", "未知")).append('\n');
        detail.append("内容：").append(item.optString("content", ""));
        new WGProAlertDialogBuilder(requireContext()).setTitle("评论详情")
                .setMessage(detail.toString())
                .setNegativeButton("关闭", null).show();
    }

    private void confirmDeleteComment(JSONObject item) {
        new WGProAlertDialogBuilder(requireContext())
                .setTitle("撤回评论？")
                .setMessage("撤回后评论将不再显示，且无法恢复。")
                .setNegativeButton("取消", null)
                .setPositiveButton("撤回", (dialog, which) -> {
                    if (dialog instanceof WGProBottomSheetDialog) {
                        ((WGProBottomSheetDialog) dialog).dismissForReplacement();
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(
                            () -> deleteComment(item), 40L);
                }).show();
    }

    private void deleteComment(JSONObject item) {
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("comment_id", item.optString("id", ""));
        WGProProgressRunner.run(this, "撤回中", "正在撤回评论...", completion ->
                account.postV2Json("dynamic_comment_delete2", fields, new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        completion.success(json);
                    }

                    @Override public void onError(int code, @NonNull String message) {
                        completion.error(code, message);
                    }
                }), new WGProProgressRunner.Callback() {
            @Override public void success(@NonNull JSONObject json) {
                View root = getView();
                if (root != null) load(root);
                showResult("已撤回", "评论已撤回");
            }

            @Override public void error(int code, @NonNull String message) {
                showResult("撤回失败", message);
            }
        });
    }

    private void showResult(String title, String message) {
        if (!isAdded()) return;
        new WGProAlertDialogBuilder(requireContext()).setTitle(title)
                .setMessage(message == null || message.trim().isEmpty() ? "操作完成" : message)
                .setNegativeButton("关闭", null).show();
    }

    private void toggleLike() {
        if (dynamicId == null || !dynamicId.trim().matches("\\d+")) {
            showError("操作失败", "动态标识无效，请刷新后重试");
            return;
        }
        Map<String, String> fields = new LinkedHashMap<>(); fields.put("dynamic_id", dynamicId);
        fields.put("action", liked ? "unlike" : "like"); likeButton.setEnabled(false);
        account.postV2Json("dynamic_reaction2", fields, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) { onUi(() -> {
                liked = json.optBoolean("liked", !liked); likeCount = json.optInt("like_count", Math.max(0, likeCount + (liked ? 1 : -1)));
                likeButton.setEnabled(true); updateActions();
            }); }
            @Override public void onError(int code, @NonNull String message) { onUi(() -> { likeButton.setEnabled(true); showError("操作失败", message); }); }
        });
    }

    private void toggleFavorite() {
        if (dynamicId == null || !dynamicId.trim().matches("\\d+")) {
            showError("操作失败", "动态标识无效，请刷新后重试");
            return;
        }
        Map<String, String> fields = new LinkedHashMap<>(); fields.put("target_type", "dynamic");
        fields.put("target_key", dynamicId); fields.put("action", favorited ? "remove" : "add");
        favoriteButton.setEnabled(false); boolean before = favorited;
        account.postV2Json("collection_action2", fields, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) { onUi(() -> {
                favorited = json.optBoolean("collected", !before);
                favoriteCount = Math.max(0, favoriteCount + (favorited == before ? 0 : favorited ? 1 : -1));
                favoriteButton.setEnabled(true); updateActions();
            }); }
            @Override public void onError(int code, @NonNull String message) { onUi(() -> { favoriteButton.setEnabled(true); showError("操作失败", message); }); }
        });
    }

    private void updateActions() {
        likeButton.setText("赞"); favoriteButton.setText("收藏");
        tint(likeButton, liked); tint(favoriteButton, favorited);
    }

    private void tint(MaterialButton button, boolean active) {
        int color = requireContext().getColor(active ? R.color.brand_primary : R.color.text_secondary);
        button.setIconTint(ColorStateList.valueOf(color)); button.setTextColor(color);
    }

    private void finishPart(int request) {
        if (request != generation || --pending > 0) return;
        refresh.setRefreshing(false);
        if (!loadedOnce) { loadedOnce = true; ((BaseSectionActivity) requireActivity()).hideContentLoading(); }
    }

    private String relativeTime(String raw) {
        return DynamicCardFactory.relativeTime(raw);
    }

    private void showError(String title, String message) {
        new WGProAlertDialogBuilder(requireContext()).setTitle(title).setMessage(message)
                .setNegativeButton("关闭", null).show();
    }

    private void onUi(Runnable action) { if (isAdded()) requireActivity().runOnUiThread(() -> { if (isAdded()) action.run(); }); }
    private TextView text(String value, int size, boolean bold, int color) {
        TextView view = new TextView(requireContext()); view.setText(value); view.setTextSize(size);
        view.setTextColor(requireContext().getColor(color));
        view.setTypeface(Typeface.DEFAULT, bold ? Typeface.BOLD : Typeface.NORMAL);
        view.setPadding(0, dp(3), 0, dp(3)); return view;
    }
    private int dp(int value) { return Math.round(value * getResources().getDisplayMetrics().density); }
    private String first(String value) {
        String clean = value == null ? "" : value.trim();
        return clean.isEmpty() ? "用" : clean.substring(0, 1).toUpperCase();
    }
}
