package com.typheye.wgpro.ui.function.community;

import android.content.Context;
import android.content.Intent;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.RelativeCornerSize;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.account.UserDetailActivity;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.utils.tAccUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public final class DynamicCardFactory {
    private DynamicCardFactory() { }

    public static void bindAvatar(Context context, String uid, String url, ImageView avatar, View initial) {
        initial.setVisibility(View.VISIBLE);
        avatar.setVisibility(View.GONE);
        loadImage(context, url, avatar, initial,
                uid == null || uid.isEmpty() ? null : new File(context.getFilesDir(), "avatar_" + uid + ".jpg"));
    }

    public static void bindImage(Context context, String url, ImageView target) {
        loadImage(context, url, target, null, null);
    }

    public static View create(Context context, JSONObject item, @Nullable View.OnClickListener click) {
        int pad = dp(context, 14);
        MaterialCardView card = new MaterialCardView(context);
        card.setCardBackgroundColor(context.getColor(R.color.surface_primary));
        card.setCardElevation(0); card.setStrokeWidth(0); card.setRadius(dp(context, 8));
        card.setClickable(click != null); card.setFocusable(click != null);
        card.setRippleColor(ColorStateList.valueOf(context.getColor(R.color.brand_soft)));
        if (click != null) card.setOnClickListener(click);

        LinearLayout body = new LinearLayout(context);
        body.setOrientation(LinearLayout.VERTICAL); body.setPadding(pad, pad, pad, dp(context, 8));
        LinearLayout header = new LinearLayout(context); header.setGravity(Gravity.CENTER_VERTICAL);
        FrameLayout avatarBox = new FrameLayout(context);
        TextView initial = text(context, first(item.optString("nick", "用")), 16, true, R.color.brand_on_soft);
        initial.setGravity(Gravity.CENTER); initial.setVisibility(View.VISIBLE);
        initial.setBackground(circle(context.getColor(R.color.brand_soft)));
        avatarBox.addView(initial, new FrameLayout.LayoutParams(-1, -1));
        ShapeableImageView avatar = new ShapeableImageView(context); avatar.setScaleType(ImageView.ScaleType.CENTER_CROP);
        avatar.setVisibility(View.GONE); avatar.setShapeAppearanceModel(avatar.getShapeAppearanceModel()
                .toBuilder().setAllCornerSizes(new RelativeCornerSize(0.5f)).build());
        avatarBox.addView(avatar, new FrameLayout.LayoutParams(-1, -1));
        header.addView(avatarBox, new LinearLayout.LayoutParams(dp(context, 40), dp(context, 40)));

        LinearLayout identity = new LinearLayout(context); identity.setOrientation(LinearLayout.VERTICAL);
        identity.addView(text(context, item.optString("nick", "Typheye 用户"), 15, true, R.color.text_primary));
        identity.addView(text(context, "发布于 " + relativeTime(item.optString("created_at", "")),
                12, false, R.color.text_secondary));
        LinearLayout.LayoutParams identityParams = new LinearLayout.LayoutParams(0, -2, 1f);
        identityParams.setMarginStart(dp(context, 10)); header.addView(identity, identityParams);
        ImageView more = new ImageView(context); more.setImageResource(R.drawable.ic_more_vertical_vector);
        more.setColorFilter(context.getColor(R.color.text_secondary)); more.setPadding(dp(context, 10), dp(context, 10), dp(context, 2), dp(context, 10));
        header.addView(more, new LinearLayout.LayoutParams(dp(context, 38), dp(context, 38)));
        body.addView(header);

        String uid = item.optString("uid", "");
        if (!uid.isEmpty()) avatarBox.setOnClickListener(v -> context.startActivity(new Intent(context,
                UserDetailActivity.class).putExtra(UserDetailActivity.EXTRA_TARGET_UID, uid)));
        more.setClickable(true); more.setFocusable(true);
        more.setOnClickListener(v -> showActions(context, item, card));
        bindAvatar(context, uid, item.optString("avatar_url", ""), avatar, initial);

        String content = item.optString("content", "");
        if (!content.isEmpty()) {
            TextView value = text(context, content, 16, false, R.color.text_primary);
            value.setLineSpacing(0f, 1.12f); value.setMaxLines(12);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2); p.topMargin = dp(context, 10);
            body.addView(value, p);
        }
        JSONArray media = item.optJSONArray("media");
        JSONObject firstMedia = media == null ? null : media.optJSONObject(0);
        if (firstMedia != null) {
            ImageView image = new ImageView(context); image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, dp(context, 190));
            p.topMargin = dp(context, 12); body.addView(image, p);
            loadImage(context, firstMedia.optString("thumbnail_url", firstMedia.optString("original_url", "")),
                    image, null, null);
        }

        LinearLayout actions = new LinearLayout(context); actions.setGravity(Gravity.CENTER_VERTICAL);
        ActionView like = action(context, R.drawable.ic_like_vector, item.optInt("like_count"));
        ActionView comment = action(context, R.drawable.ic_comment_vector, item.optInt("comment_count"));
        ActionView favorite = action(context, R.drawable.ic_bookmark_vector,
                item.optInt("collection_count", item.optBoolean("is_favorited") ? 1 : 0));
        actions.addView(like.root, weighted(context)); actions.addView(comment.root, weighted(context));
        actions.addView(favorite.root, weighted(context));
        boolean liked = item.optBoolean("is_liked"); boolean favorited = item.optBoolean("is_favorited");
        setActionState(context, like, liked); setActionState(context, favorite, favorited);
        like.root.setOnClickListener(v -> toggleReaction(context, item, like));
        favorite.root.setOnClickListener(v -> toggleCollection(context, item, favorite));
        comment.root.setOnClickListener(v -> openDetail(context, item));
        LinearLayout.LayoutParams actionParams = new LinearLayout.LayoutParams(-1, dp(context, 42));
        actionParams.topMargin = dp(context, 8); body.addView(actions, actionParams);
        card.addView(body);
        return card;
    }

    private static ActionView action(Context context, int iconRes, int count) {
        LinearLayout action = new LinearLayout(context); action.setGravity(Gravity.CENTER);
        action.setClickable(true); action.setFocusable(true);
        action.setBackgroundResource(R.drawable.bg_list_item_ripple);
        ImageView icon = new ImageView(context); icon.setImageResource(iconRes);
        action.addView(icon, new LinearLayout.LayoutParams(dp(context, 20), dp(context, 20)));
        TextView number = text(context, String.valueOf(Math.max(0, count)), 12, false, R.color.text_secondary);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-2, -2); p.setMarginStart(dp(context, 6));
        action.addView(number, p); return new ActionView(action, icon, number, Math.max(0, count));
    }

    private static void openDetail(Context context, JSONObject item) {
        context.startActivity(new Intent(context, DynamicDetailActivity.class)
                .putExtra(DynamicDetailActivity.EXTRA_DYNAMIC_ID, item.optString("id")));
    }

    private static void toggleReaction(Context context, JSONObject item, ActionView view) {
        boolean before = item.optBoolean("is_liked");
        setOptimistic(context, view, item, "is_liked", !before);
        Map<String, String> fields = new LinkedHashMap<>(); fields.put("dynamic_id", item.optString("id"));
        fields.put("action", before ? "unlike" : "like");
        new tAccUtils(context.getApplicationContext()).postV2Json("dynamic_reaction2", fields, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(JSONObject json) { view.root.post(() -> {
                boolean active = json.optBoolean("liked", !before); putBoolean(item, "is_liked", active);
                view.count = json.optInt("like_count", view.count); view.number.setText(String.valueOf(view.count));
                view.root.setEnabled(true); setActionState(context, view, active);
            }); }
            @Override public void onError(int code, String message) { view.root.post(() -> {
                view.root.setEnabled(true); setOptimistic(context, view, item, "is_liked", before); error(context, message);
            }); }
        });
        view.root.setEnabled(false);
    }

    private static void toggleCollection(Context context, JSONObject item, ActionView view) {
        boolean before = item.optBoolean("is_favorited");
        setOptimistic(context, view, item, "is_favorited", !before);
        Map<String, String> fields = new LinkedHashMap<>(); fields.put("target_type", "dynamic");
        fields.put("target_key", item.optString("id")); fields.put("action", before ? "remove" : "add");
        new tAccUtils(context.getApplicationContext()).postV2Json("collection_action2", fields, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(JSONObject json) { view.root.post(() -> {
                boolean active = json.optBoolean("collected", !before); putBoolean(item, "is_favorited", active);
                view.root.setEnabled(true); setActionState(context, view, active);
            }); }
            @Override public void onError(int code, String message) { view.root.post(() -> {
                view.root.setEnabled(true); setOptimistic(context, view, item, "is_favorited", before); error(context, message);
            }); }
        });
        view.root.setEnabled(false);
    }

    private static void setOptimistic(Context context, ActionView view, JSONObject item, String key, boolean active) {
        boolean old = item.optBoolean(key); putBoolean(item, key, active);
        if (old != active) view.count = Math.max(0, view.count + (active ? 1 : -1));
        view.number.setText(String.valueOf(view.count)); setActionState(context, view, active);
    }

    private static void putBoolean(JSONObject item, String key, boolean value) {
        try { item.put(key, value); } catch (Exception ignored) { }
    }

    private static void setActionState(Context context, ActionView view, boolean active) {
        int color = context.getColor(active ? R.color.brand_primary : R.color.text_secondary);
        view.icon.setColorFilter(color); view.number.setTextColor(color);
    }

    private static GradientDrawable circle(int color) {
        GradientDrawable drawable = new GradientDrawable(); drawable.setShape(GradientDrawable.OVAL);
        drawable.setColor(color); return drawable;
    }

    private static final class ActionView {
        final LinearLayout root; final ImageView icon; final TextView number; int count;
        ActionView(LinearLayout root, ImageView icon, TextView number, int count) {
            this.root = root; this.icon = icon; this.number = number; this.count = count;
        }
    }

    private static LinearLayout.LayoutParams weighted(Context context) {
        return new LinearLayout.LayoutParams(0, dp(context, 42), 1f);
    }

    private static TextView text(Context context, String value, int size, boolean bold, int color) {
        TextView text = new TextView(context); text.setText(value); text.setTextSize(size);
        text.setTextColor(context.getColor(color));
        text.setTypeface(Typeface.DEFAULT, bold ? Typeface.BOLD : Typeface.NORMAL); return text;
    }

    private static String first(String value) {
        String clean = value == null ? "" : value.trim(); return clean.isEmpty() ? "用" : clean.substring(0, 1).toUpperCase();
    }

    public static String relativeTime(String raw) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(raw);
            long seconds = Math.max(0, (System.currentTimeMillis() - date.getTime()) / 1000);
            if (seconds < 60) return "刚刚";
            if (seconds < 3600) return seconds / 60 + " 分钟前";
            if (seconds < 86400) return seconds / 3600 + " 小时前";
            if (seconds < 2592000L) return seconds / 86400 + " 天前";
        } catch (Exception ignored) { }
        return raw == null || raw.isEmpty() ? "未知时间" : raw;
    }

    public static void showActions(Context context, JSONObject item, View card) {
        tAccUtils account = new tAccUtils(context.getApplicationContext());
        boolean self = item.optBoolean("is_self") || account.getUid().equals(item.optString("uid"));
        CharSequence[] actions = self ? new CharSequence[]{"删除", "分享"}
                : new CharSequence[]{"屏蔽", "举报", "分享"};
        new WGProAlertDialogBuilder(context).setTitle("动态操作").setItems(actions, (dialog, which) -> {
            String action = actions[which].toString();
            if ("分享".equals(action)) { share(context, account, item); return; }
            if ("删除".equals(action)) { confirmDelete(context, account, item, card); return; }
            if ("屏蔽".equals(action)) {
                Map<String, String> fields = new LinkedHashMap<>(); fields.put("target_uid", item.optString("uid"));
                fields.put("action", "block"); postFeedback(context, account, "block_action2", fields, "已屏蔽该用户");
                return;
            }
            Map<String, String> fields = new LinkedHashMap<>(); fields.put("target_type", "dynamic");
            fields.put("target_key", item.optString("id")); fields.put("reason_code", "spam");
            fields.put("description", "通过 Android 客户端举报");
            postFeedback(context, account, "report_create2", fields, "举报已提交");
        }).show();
    }

    private static void confirmDelete(Context context, tAccUtils account, JSONObject item, View card) {
        new WGProAlertDialogBuilder(context).setTitle("删除动态？")
                .setMessage("删除后无法恢复。").setNegativeButton("取消", null)
                .setPositiveButton("删除", (dialog, which) -> {
                    Map<String, String> fields = new LinkedHashMap<>(); fields.put("dynamic_id", item.optString("id"));
                    account.postV2Json("dynamic_delete2", fields, new tAccUtils.JsonCallback() {
                        @Override public void onSuccess(JSONObject json) { card.post(() -> {
                            if (context instanceof DynamicDetailActivity) ((DynamicDetailActivity) context).finish();
                            else card.setVisibility(View.GONE);
                        }); }
                        @Override public void onError(int code, String message) { card.post(() -> error(context, message)); }
                    });
                }).show();
    }

    private static void share(Context context, tAccUtils account, JSONObject item) {
        Intent send = new Intent(Intent.ACTION_SEND).setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, item.optString("content"));
        context.startActivity(Intent.createChooser(send, "分享动态"));
        Map<String, String> fields = new LinkedHashMap<>(); fields.put("dynamic_id", item.optString("id"));
        fields.put("channel", "system"); account.postV2Json("dynamic_share2", fields, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(JSONObject json) { }
            @Override public void onError(int code, String message) { }
        });
    }

    private static void postFeedback(Context context, tAccUtils account, String action,
                                     Map<String, String> fields, String success) {
        account.postV2Json(action, fields, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(JSONObject json) { new android.os.Handler(context.getMainLooper())
                    .post(() -> android.widget.Toast.makeText(context, success, android.widget.Toast.LENGTH_SHORT).show()); }
            @Override public void onError(int code, String message) { new android.os.Handler(context.getMainLooper())
                    .post(() -> error(context, message)); }
        });
    }

    private static void error(Context context, String message) {
        new WGProAlertDialogBuilder(context).setTitle("操作失败").setMessage(message)
                .setNegativeButton("关闭", null).show();
    }

    private static void loadImage(Context context, String url, ImageView target,
                                  @Nullable View placeholder, @Nullable File cache) {
        if (cache != null && cache.isFile()) {
            Bitmap bitmap = BitmapFactory.decodeFile(cache.getAbsolutePath());
            if (bitmap != null) { target.setImageBitmap(bitmap); target.setVisibility(View.VISIBLE);
                if (placeholder != null) placeholder.setVisibility(View.GONE); return; }
        }
        if (url == null || url.isEmpty()) return;
        Request request; try { request = new Request.Builder().url(url).build(); } catch (Exception ignored) { return; }
        new tAccUtils(context.getApplicationContext()).getClient().newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException error) { }
            @Override public void onResponse(Call call, Response response) {
                try (Response body = response) {
                    if (!body.isSuccessful() || body.body() == null) return;
                    byte[] bytes = body.body().bytes(); Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    if (bitmap == null) return;
                    if (cache != null) try (FileOutputStream out = new FileOutputStream(cache)) { out.write(bytes); }
                    catch (IOException ignored) { }
                    target.post(() -> { target.setImageBitmap(bitmap); target.setVisibility(View.VISIBLE);
                        if (placeholder != null) placeholder.setVisibility(View.GONE); });
                } catch (IOException ignored) { }
            }
        });
    }

    private static int dp(Context context, int value) {
        return Math.round(value * context.getResources().getDisplayMetrics().density);
    }
}
