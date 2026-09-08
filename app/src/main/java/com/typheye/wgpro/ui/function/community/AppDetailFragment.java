package com.typheye.wgpro.ui.function.community;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.utils.tAccUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public final class AppDetailFragment extends Fragment {
    static AppDetailFragment newInstance(String json) {
        AppDetailFragment fragment = new AppDetailFragment(); Bundle args = new Bundle();
        args.putString("json", json == null ? "{}" : json); fragment.setArguments(args); return fragment;
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater,
                                                  @Nullable ViewGroup container, @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment_app_detail, container, false);
        BaseSectionActivity host = (BaseSectionActivity) requireActivity();
        host.showContentLoading();
        JSONObject app; try { app = new JSONObject(requireArguments().getString("json", "{}")); }
        catch (Exception ignored) { app = new JSONObject(); }
        final boolean[] hidden = {false};
        long started = android.os.SystemClock.uptimeMillis();
        Runnable hide = () -> {
            long wait = Math.max(0L, 300L - (android.os.SystemClock.uptimeMillis() - started));
            root.postDelayed(() -> { if (!hidden[0] && isAdded()) { hidden[0] = true; host.hideContentLoading(); } }, wait);
        };
        bind(root, app, hide);
        return root;
    }

    private void bind(View root, JSONObject app, @Nullable Runnable loaded) {
        String name = app.optString("name", "未命名应用");
        ((TextView) root.findViewById(R.id.app_detail_initial)).setText(first(name));
        ((TextView) root.findViewById(R.id.app_detail_name)).setText(name);
        ((TextView) root.findViewById(R.id.app_detail_author)).setText(app.optString("author", "Typheye 应用平台"));
        ((TextView) root.findViewById(R.id.app_detail_rating)).setText(app.optString("version_name", "--"));
        ((TextView) root.findViewById(R.id.app_detail_rating_label)).setText("版本");
        ((TextView) root.findViewById(R.id.app_detail_size)).setText(blank(app.optString("size"), "--"));
        ((TextView) root.findViewById(R.id.app_detail_downloads)).setText(formatDownloads(app.optInt("downloads")));
        ((TextView) root.findViewById(R.id.app_detail_age)).setText(blank(app.optString("age_rating"), "全年龄"));
        String description = app.optString("description", app.optString("summary", "暂无应用介绍。"));
        ((TextView) root.findViewById(R.id.app_detail_description)).setText(description);
        String iconUrl = app.optString("icon_url", "");
        ImageView icon = root.findViewById(R.id.app_detail_icon);
        if (!iconUrl.isEmpty()) load(iconUrl, icon, () -> {
            icon.setVisibility(View.VISIBLE); root.findViewById(R.id.app_detail_initial).setVisibility(View.GONE);
            if (loaded != null) loaded.run();
        });
        else if (loaded != null) root.postDelayed(loaded, 300L);
        MaterialButton install = root.findViewById(R.id.app_detail_install);
        String downloadUrl = app.optString("download_url", app.optString("detail_url", ""));
        install.setEnabled(!downloadUrl.isEmpty());
        install.setOnClickListener(v -> startActivity(new Intent(requireContext(), WebActivity.class)
                .putExtra("URL", downloadUrl)));
        LinearLayout screenshots = root.findViewById(R.id.app_detail_screenshots);
        JSONArray urls = app.optJSONArray("screenshots");
        if (urls == null || urls.length() == 0) {
            root.findViewById(R.id.app_detail_screenshot_scroll).setVisibility(View.GONE);
        } else for (int i = 0; i < urls.length(); i++) {
            String url = urls.optString(i); if (url.isEmpty()) continue;
            ImageView shot = new ImageView(requireContext()); shot.setScaleType(ImageView.ScaleType.CENTER_CROP);
            shot.setBackgroundResource(R.drawable.bg_app_screenshot); shot.setClipToOutline(true);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(dp(178), dp(316)); p.setMarginEnd(dp(12));
            screenshots.addView(shot, p); load(url, shot, null);
        }
    }

    private void load(String url, ImageView target, @Nullable Runnable done) {
        Request request; try { request = new Request.Builder().url(url).build(); } catch (Exception ignored) { return; }
        new tAccUtils(requireContext().getApplicationContext()).getClient().newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException error) {
                if (done != null) target.post(done);
            }
            @Override public void onResponse(Call call, Response response) {
                try (Response body = response) {
                    if (!body.isSuccessful() || body.body() == null) { if (done != null) target.post(done); return; }
                    Bitmap bitmap = BitmapFactory.decodeStream(body.body().byteStream());
                    target.post(() -> {
                        if (bitmap != null) target.setImageBitmap(bitmap);
                        if (done != null) done.run();
                    });
                }
            }
        });
    }

    private String first(String value) { return value.trim().isEmpty() ? "应" : value.trim().substring(0, 1); }
    private String blank(String value, String fallback) { return value == null || value.trim().isEmpty() ? fallback : value; }
    private String formatDownloads(int value) { return value >= 10000 ? (value / 10000) + "万+" : value + "+"; }
    private int dp(int value) { return Math.round(value * getResources().getDisplayMetrics().density); }
}
