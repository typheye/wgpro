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
import com.typheye.wgpro.utils.ImageCache;
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
    public interface OnResourceMenu { void onMenu(@NonNull JSONObject item, @NonNull View anchor); }
    private ResourceMasonryFactory() { }

    public static View create(@NonNull Context context, @NonNull JSONArray items, @NonNull OnResourceClick listener) {
        return create(context, items, listener, null);
    }
    public static View create(@NonNull Context context, @NonNull JSONArray items, @NonNull OnResourceClick listener, OnResourceMenu menuListener) {
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
            target.addView(card(context, item, listener, menuListener));
            if (target == left) leftWeight += weight; else rightWeight += weight;
        }
        return columns;
    }

    /** 单张资源卡（RecyclerView 复用/瀑布流逐项使用）。 */
    public static MaterialCardView card(Context context, JSONObject item, OnResourceClick listener, OnResourceMenu menuListener) {
        MaterialCardView card = new MaterialCardView(context);
        card.setCardBackgroundColor(com.typheye.wgpro.ui.widget.WGProColors.of(context, R.color.surface_secondary)); card.setCardElevation(0);
        card.setStrokeWidth(0); card.setRadius(dp(context, 8)); card.setClickable(true); card.setFocusable(true);
        card.setRippleColor(ColorStateList.valueOf(com.typheye.wgpro.ui.widget.WGProColors.of(context, R.color.brand_soft)));
        LinearLayout body = column(context); boolean invalid = item.optBoolean("_invalid", false);
        String title = invalid ? "未知资源" : item.optString("title", "未命名资源");
        // 封面严格 4:3（宽:高），用 ConstraintLayout 的 dimensionRatio 保证比例
        androidx.constraintlayout.widget.ConstraintLayout coverBox =
                new androidx.constraintlayout.widget.ConstraintLayout(context);
        // 封面兜底：只留色块占位，不再叠文字（信息在下方标题里已有）
        View fallback = new View(context);
        fallback.setBackground(coloredCover(context, title));
        coverBox.addView(fallback, ratioParams(4f / 3f));
        ImageView cover = new ImageView(context); cover.setScaleType(ImageView.ScaleType.CENTER_CROP); cover.setVisibility(View.GONE);
        coverBox.addView(cover, ratioParams(4f / 3f));
        body.addView(coverBox, new LinearLayout.LayoutParams(-1, -2));
        String signedCover = ResourceCoverResolver.cover(item.optString("id", ""));
        String coverUrl = signedCover != null && !signedCover.isEmpty()
                ? signedCover
                : item.optString("cover_url", item.optString("image_url", "")).trim();
        if (!invalid) {
            loadCover(context, coverUrl, cover, fallback);
            if (signedCover == null || signedCover.isEmpty()) {
                // 签名封面还没到：到货后给这张卡补图（不重建列表）
                final String itemId = item.optString("id", "");
                final ImageView target = cover;
                final View fallbackView = fallback;
                ResourceCoverResolver.fill(context, null, () -> {
                    String fresh = ResourceCoverResolver.cover(itemId);
                    if (fresh != null && !fresh.isEmpty()) {
                        loadCover(context, fresh, target, fallbackView);
                    }
                });
            }
        }
        // 不单独给封面设置点击：点卡片任意位置（含封面）都进入资源详情页
        LinearLayout labels = column(context); labels.setPadding(dp(context, 10), dp(context, 9), dp(context, 10), dp(context, 10));
        LinearLayout titleLine = new LinearLayout(context); titleLine.setGravity(Gravity.CENTER_VERTICAL);
        TextView titleView = text(context, title, 15, true, R.color.text_primary); titleView.setMaxLines(1); titleView.setSingleLine(true); titleView.setEllipsize(android.text.TextUtils.TruncateAt.END);
        SearchHighlight.applyFromItem(context, titleView, title, item);
        titleLine.addView(titleView, new LinearLayout.LayoutParams(0, -2, 1f));
        labels.addView(titleLine);
        // 简介固定 2 行（高度一致，卡片才能对齐）
        TextView description = text(context, invalid ? "资源已不可见" : item.optString("summary", item.optString("description", "")), 13, false, R.color.text_secondary);
        description.setMaxLines(2); description.setLines(2); description.setEllipsize(android.text.TextUtils.TruncateAt.END);
        LinearLayout.LayoutParams descParams = new LinearLayout.LayoutParams(-1, -2); descParams.topMargin = dp(context, 4); labels.addView(description, descParams);
        // 第三行与应用卡片一致：「x 下载 · 大小」
        String size = humanSize(item.optLong("file_size", 0L));
        String metaText = item.optInt("download_count", 0) + " 下载" + (size.isEmpty() ? "" : " · " + size);
        TextView meta = text(context, metaText, 12, false, R.color.text_secondary);
        meta.setMaxLines(1); meta.setSingleLine(true); meta.setEllipsize(android.text.TextUtils.TruncateAt.END);
        LinearLayout.LayoutParams mp = new LinearLayout.LayoutParams(-1, -2); mp.topMargin = dp(context, 5); labels.addView(meta, mp);
        body.addView(labels); card.addView(body); card.setOnClickListener(v -> listener.onClick(item));
        if (menuListener != null) card.setOnLongClickListener(v -> { menuListener.onMenu(item, v); return true; });
        LinearLayout.LayoutParams cp = new LinearLayout.LayoutParams(-1, -2); cp.bottomMargin = dp(context, 10); card.setLayoutParams(cp);
        return card;
    }

    /** 约束成「宽:高 = 4:3」的封面参数（横版 4:3）。 */
    private static androidx.constraintlayout.widget.ConstraintLayout.LayoutParams ratioParams(float ratio) {
        androidx.constraintlayout.widget.ConstraintLayout.LayoutParams p =
                new androidx.constraintlayout.widget.ConstraintLayout.LayoutParams(0, 0);
        p.dimensionRatio = "H,4:3";
        p.topToTop = androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID;
        p.bottomToBottom = androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID;
        p.startToStart = androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID;
        p.endToEnd = androidx.constraintlayout.widget.ConstraintLayout.LayoutParams.PARENT_ID;
        return p;
    }

    /** 字节数 → 人类可读（702.6KB / 12.5MB）。 */
    static String humanSize(long bytes) {
        if (bytes <= 0L) return "";
        if (bytes < 1024L) return bytes + "B";
        double kb = bytes / 1024.0;
        if (kb < 1024.0) return String.format(java.util.Locale.US, "%.1fKB", kb);
        double mb = kb / 1024.0;
        if (mb < 1024.0) return String.format(java.util.Locale.US, "%.1fMB", mb);
        return String.format(java.util.Locale.US, "%.1fGB", mb / 1024.0);
    }

    private static GradientDrawable coloredCover(Context context, String seed) {
        int[] colors = {R.color.media_blue, R.color.media_green, R.color.media_coral, R.color.media_amber};
        GradientDrawable d = new GradientDrawable(); d.setColor(context.getColor(colors[Math.abs(seed.hashCode() % colors.length)])); return d;
    }
    private static void loadCover(Context context, String url, ImageView image, View fallback) {
        if (url == null || url.trim().isEmpty()) return;
        ImageCache.load(context, url, image, () -> {
            image.setVisibility(View.VISIBLE);
            fallback.setVisibility(View.GONE);
        });
    }
    private static LinearLayout column(Context c) { LinearLayout v = new LinearLayout(c); v.setOrientation(LinearLayout.VERTICAL); return v; }
    private static TextView text(Context c, String value, int size, boolean bold, int color) { TextView v = new TextView(c); v.setText(value); v.setTextSize(size); v.setTextColor(com.typheye.wgpro.ui.widget.WGProColors.of(c, color)); v.setTypeface(Typeface.DEFAULT, bold ? Typeface.BOLD : Typeface.NORMAL); return v; }
    private static String coverText(String title) { String s = title == null ? "" : title.trim(); return s.isEmpty() ? "资源" : s.substring(0, Math.min(4, s.length())); }
    private static int dp(Context c, int value) { return Math.round(value * c.getResources().getDisplayMetrics().density); }
}
