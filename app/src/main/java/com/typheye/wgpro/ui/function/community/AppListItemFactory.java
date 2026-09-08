package com.typheye.wgpro.ui.function.community;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.tAccUtils;

import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/** The single app-row implementation used by Home, history and starred-app lists. */
public final class AppListItemFactory {
    private AppListItemFactory() { }

    public static View create(@NonNull Context context, @NonNull JSONObject item) {
        LinearLayout row = new LinearLayout(context);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(dp(context, 4), dp(context, 12), dp(context, 4), dp(context, 12));
        row.setClickable(true); row.setFocusable(true);
        row.setBackgroundResource(R.drawable.bg_list_item_ripple);
        String name = item.optString("name", "未命名应用");
        FrameLayout iconBox = new FrameLayout(context);
        TextView initial = text(context, first(name), 22, true, R.color.brand_primary);
        initial.setGravity(Gravity.CENTER); initial.setBackgroundResource(R.drawable.bg_app_icon_clip);
        iconBox.addView(initial, new FrameLayout.LayoutParams(-1, -1));
        ImageView icon = new ImageView(context); icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
        icon.setVisibility(View.GONE); icon.setBackgroundResource(R.drawable.bg_app_icon_clip); icon.setClipToOutline(true);
        iconBox.addView(icon, new FrameLayout.LayoutParams(-1, -1));
        row.addView(iconBox, new LinearLayout.LayoutParams(dp(context, 56), dp(context, 56)));
        loadIcon(context, item.optString("icon_url", ""), icon, initial);
        LinearLayout labels = new LinearLayout(context); labels.setOrientation(LinearLayout.VERTICAL);
        TextView title = text(context, name, 17, false, R.color.text_primary);
        title.setSingleLine(true); title.setEllipsize(android.text.TextUtils.TruncateAt.END); labels.addView(title);
        TextView summary = text(context, item.optString("summary", item.optString("package", "应用")), 14, false, R.color.text_secondary);
        summary.setSingleLine(true); summary.setEllipsize(android.text.TextUtils.TruncateAt.END);
        LinearLayout.LayoutParams summaryParams = new LinearLayout.LayoutParams(-1, -2);
        summaryParams.topMargin = dp(context, 5); labels.addView(summary, summaryParams);
        String version = item.optString("version_name", "");
        if (!version.isEmpty()) {
            TextView versionView = text(context, "版本 " + version, 12, false, R.color.text_secondary);
            versionView.setSingleLine(true); LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2);
            p.topMargin = dp(context, 6); labels.addView(versionView, p);
        }
        LinearLayout.LayoutParams labelsParams = new LinearLayout.LayoutParams(0, -2, 1f);
        labelsParams.setMarginStart(dp(context, 16)); row.addView(labels, labelsParams);
        row.setOnClickListener(v -> context.startActivity(new Intent(context, AppDetailActivity.class)
                .putExtra(AppDetailActivity.EXTRA_APP_JSON, item.toString())));
        return row;
    }

    private static void loadIcon(Context context, String url, ImageView image, View fallback) {
        if (url == null || url.trim().isEmpty()) return;
        if (url.startsWith("/")) url = "https://res.typheye.cn" + url;
        Request request; try { request = new Request.Builder().url(url).build(); }
        catch (IllegalArgumentException ignored) { return; }
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
    private static TextView text(Context c, String value, int size, boolean bold, int color) {
        TextView v = new TextView(c); v.setText(value); v.setTextSize(size); v.setTextColor(c.getColor(color));
        v.setTypeface(Typeface.DEFAULT, bold ? Typeface.BOLD : Typeface.NORMAL); return v;
    }
    private static String first(String value) { String s = value == null ? "" : value.trim(); return s.isEmpty() ? "应" : s.substring(0, 1).toUpperCase(); }
    private static int dp(Context c, int value) { return Math.round(value * c.getResources().getDisplayMetrics().density); }
}
