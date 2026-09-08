package com.typheye.wgpro.ui.function.community;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.google.android.material.card.MaterialCardView;
import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.tAccUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/** Shared two-column resource stream. */
public final class ResourceMasonryFactory {
    public interface OnResourceClick { void onClick(@NonNull JSONObject item); }
    private ResourceMasonryFactory() { }

    public static View create(@NonNull Context context, @NonNull JSONArray items, @NonNull OnResourceClick listener) {
        LinearLayout columns = new LinearLayout(context); columns.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout left = column(context), right = column(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, -2, 1f); lp.setMarginEnd(dp(context, 5));
        LinearLayout.LayoutParams rp = new LinearLayout.LayoutParams(0, -2, 1f); rp.setMarginStart(dp(context, 5));
        columns.addView(left, lp); columns.addView(right, rp);
        int leftWeight = 0, rightWeight = 0;
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.optJSONObject(i); if (item == null) continue;
            int weight = 170 + Math.min(54, item.optString("title", "").length() * 5);
            LinearLayout target = leftWeight <= rightWeight ? left : right;
            target.addView(card(context, item, listener));
            if (target == left) leftWeight += weight; else rightWeight += weight;
        }
        return columns;
    }

    private static MaterialCardView card(Context context, JSONObject item, OnResourceClick listener) {
        MaterialCardView card = new MaterialCardView(context);
        card.setCardBackgroundColor(context.getColor(R.color.surface_primary)); card.setCardElevation(0);
        card.setStrokeWidth(0); card.setRadius(dp(context, 8)); card.setClickable(true); card.setFocusable(true);
        card.setRippleColor(ColorStateList.valueOf(context.getColor(R.color.brand_soft)));
        LinearLayout body = column(context); String title = item.optString("title", "未命名资源");
        FrameLayout coverBox = new FrameLayout(context);
        TextView fallback = text(context, coverText(title), 19, true, R.color.brand_primary);
        fallback.setGravity(Gravity.CENTER); fallback.setBackground(coloredCover(context, title));
        coverBox.addView(fallback, new FrameLayout.LayoutParams(-1, -1));
        ImageView cover = new ImageView(context); cover.setScaleType(ImageView.ScaleType.CENTER_CROP); cover.setVisibility(View.GONE);
        coverBox.addView(cover, new FrameLayout.LayoutParams(-1, -1));
        body.addView(coverBox, new LinearLayout.LayoutParams(-1, dp(context, 104 + Math.abs(title.hashCode() % 36))));
        loadCover(context, item.optString("cover_url", item.optString("image_url", "")), cover, fallback);
        LinearLayout labels = column(context); labels.setPadding(dp(context, 10), dp(context, 9), dp(context, 10), dp(context, 10));
        TextView titleView = text(context, title, 15, true, R.color.text_primary); titleView.setMaxLines(2);
        titleView.setEllipsize(android.text.TextUtils.TruncateAt.END); labels.addView(titleView);
        String type = item.optString("category", item.optString("type_name", "资源"));
        TextView meta = text(context, type + " · " + item.optInt("collection_count", 0) + " 收藏", 12, false, R.color.text_secondary);
        LinearLayout.LayoutParams mp = new LinearLayout.LayoutParams(-1, -2); mp.topMargin = dp(context, 5); labels.addView(meta, mp);
        body.addView(labels); card.addView(body); card.setOnClickListener(v -> listener.onClick(item));
        LinearLayout.LayoutParams cp = new LinearLayout.LayoutParams(-1, -2); cp.bottomMargin = dp(context, 10); card.setLayoutParams(cp);
        return card;
    }

    private static GradientDrawable coloredCover(Context context, String seed) {
        int[] colors = {R.color.media_blue, R.color.media_green, R.color.media_coral, R.color.media_amber};
        GradientDrawable d = new GradientDrawable(); d.setColor(context.getColor(colors[Math.abs(seed.hashCode() % colors.length)])); return d;
    }
    private static void loadCover(Context context, String url, ImageView image, View fallback) {
        if (url == null || url.trim().isEmpty()) return;
        if (url.startsWith("/")) url = "https://service.typheye.cn" + url;
        Request request; try { request = new Request.Builder().url(url).build(); } catch (IllegalArgumentException ignored) { return; }
        new tAccUtils(context.getApplicationContext()).getClient().newCall(request).enqueue(new Callback() {
            @Override public void onFailure(@NonNull Call call, @NonNull IOException e) { }
            @Override public void onResponse(@NonNull Call call, @NonNull Response response) {
                try (Response body = response) {
                    if (!body.isSuccessful() || body.body() == null) return;
                    Bitmap bitmap = BitmapFactory.decodeStream(body.body().byteStream()); if (bitmap == null) return;
                    image.post(() -> { image.setImageBitmap(bitmap); image.setVisibility(View.VISIBLE); fallback.setVisibility(View.GONE); });
                }
            }
        });
    }
    private static LinearLayout column(Context c) { LinearLayout v = new LinearLayout(c); v.setOrientation(LinearLayout.VERTICAL); return v; }
    private static TextView text(Context c, String value, int size, boolean bold, int color) { TextView v = new TextView(c); v.setText(value); v.setTextSize(size); v.setTextColor(c.getColor(color)); v.setTypeface(Typeface.DEFAULT, bold ? Typeface.BOLD : Typeface.NORMAL); return v; }
    private static String coverText(String title) { String s = title == null ? "" : title.trim(); return s.isEmpty() ? "资源" : s.substring(0, Math.min(4, s.length())); }
    private static int dp(Context c, int value) { return Math.round(value * c.getResources().getDisplayMetrics().density); }
}
