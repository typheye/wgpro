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
import com.typheye.wgpro.utils.ImageCache;
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
        return create(context, item, null);
    }

    public static View create(@NonNull Context context, @NonNull JSONObject item, View.OnClickListener menuListener) {
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
        boolean vela = "velaapp".equals(item.optString("kind", ""));
        ImageView icon = new ImageView(context);
        icon.setScaleType(vela ? ImageView.ScaleType.FIT_CENTER : ImageView.ScaleType.CENTER_CROP);
        icon.setVisibility(View.GONE);
        if (vela) {
            // Vela 图标本身是圆的：放在之前的圆角卡片底（surface_secondary）上，并留一点外边距。
            icon.setBackgroundResource(R.drawable.bg_app_icon_clip);
            icon.setClipToOutline(true);
            int pad = dp(context, 4);
            icon.setPadding(pad, pad, pad, pad);
        } else {
            icon.setBackgroundResource(R.drawable.bg_app_icon_clip);
            icon.setClipToOutline(true);
        }
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
        String meta = metaLine(item);
        if (!meta.isEmpty()) {
            TextView metaView = text(context, meta, 12, false, R.color.text_secondary);
            metaView.setSingleLine(true); LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2);
            p.topMargin = dp(context, 6); labels.addView(metaView, p);
        }
        LinearLayout.LayoutParams labelsParams = new LinearLayout.LayoutParams(0, -2, 1f);
        labelsParams.setMarginStart(dp(context, 16)); row.addView(labels, labelsParams);
        if (menuListener != null) {
            // 不再放右上角「三个竖点」：改为长按卡片弹出操作菜单（点击卡片仍进详情）。
            row.setOnLongClickListener(v -> { menuListener.onClick(row); return true; });
        }
        row.setOnClickListener(v -> context.startActivity(new Intent(context, AppDetailActivity.class)
                .putExtra(AppDetailActivity.EXTRA_APP_JSON, item.toString())));
        return row;
    }

    private static void loadIcon(Context context, String url, ImageView image, View fallback) {
        if (url == null || url.trim().isEmpty()) return;
        ImageCache.load(context, url, image, () -> {
            image.setVisibility(View.VISIBLE);
            fallback.setVisibility(View.GONE);
        });
    }

    /** 第三行：下载量 · 应用大小（vela 应用再补一段类型）。例如「117 下载 · 39.6M · Vela 应用」。 */
    private static String metaLine(JSONObject item) {
        int downloads = item.optInt("downloads", item.optInt("download_count", 0));
        String size = item.optString("size", "").trim();
        StringBuilder builder = new StringBuilder();
        builder.append(downloads).append(" 下载");
        if (!size.isEmpty()) builder.append(" · ").append(size);
        if ("velaapp".equals(item.optString("kind", ""))) builder.append(" · Vela 应用");
        return builder.toString();
    }

    private static TextView text(Context c, String value, int size, boolean bold, int color) {
        TextView v = new TextView(c); v.setText(value); v.setTextSize(size); v.setTextColor(c.getColor(color));
        v.setTypeface(Typeface.DEFAULT, bold ? Typeface.BOLD : Typeface.NORMAL); return v;
    }
    private static String first(String value) { String s = value == null ? "" : value.trim(); return s.isEmpty() ? "应" : s.substring(0, 1).toUpperCase(); }
    private static int dp(Context c, int value) { return Math.round(value * c.getResources().getDisplayMetrics().density); }
}
