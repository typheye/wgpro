package com.typheye.wgpro.ui.main.mainFragments;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.card.MaterialCardView;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.ui.function.account.UserDetailActivity;
import com.typheye.wgpro.ui.function.community.AppDetailActivity;
import com.typheye.wgpro.ui.function.community.DynamicCardFactory;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.utils.tAccUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private LinearLayout banners;
    private HorizontalScrollView bannerScroll;
    private View announcement;
    private TextView announcementText;
    private LinearLayout feed;
    private View communityStatus;
    private LinearLayout apps;
    private View appsStatus;
    private LinearLayout resources;
    private View resourcesStatus;
    private tAccUtils account;
    private SwipeRefreshLayout communityRefresh;
    private SwipeRefreshLayout appsRefresh;
    private SwipeRefreshLayout resourcesRefresh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        bindContent(root);
        setupPages(root);
        account = new tAccUtils(requireContext());
        loadAll();
        return root;
    }

    private void setupPages(View root) {
        MaterialButtonToggleGroup segments = root.findViewById(R.id.home_segments);
        ViewPager2 pager = root.findViewById(R.id.home_pager);
        FrameLayout pool = root.findViewById(R.id.home_page_pool);
        List<View> pages = Arrays.asList(root.findViewById(R.id.page_community),
                root.findViewById(R.id.page_apps), root.findViewById(R.id.page_resources));
        for (View page : pages) pool.removeView(page);
        pager.setAdapter(new LocalPageAdapter(pages));
        pager.setOffscreenPageLimit(2);
        int[] ids = {R.id.home_segment_community, R.id.home_segment_apps,
                R.id.home_segment_resources};
        segments.addOnButtonCheckedListener((group, checkedId, checked) -> {
            if (!checked) return;
            for (int i = 0; i < ids.length; i++) {
                if (ids[i] == checkedId && pager.getCurrentItem() != i) {
                    pager.setCurrentItem(i, true);
                    break;
                }
            }
        });
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override public void onPageSelected(int position) {
                if (position >= 0 && position < ids.length
                        && segments.getCheckedButtonId() != ids[position]) {
                    segments.check(ids[position]);
                }
            }
        });
    }

    private void bindContent(View root) {
        bannerScroll = root.findViewById(R.id.community_banner_scroll);
        banners = root.findViewById(R.id.community_banners);
        announcement = root.findViewById(R.id.community_announcement);
        announcementText = root.findViewById(R.id.community_announcement_text);
        feed = root.findViewById(R.id.community_feed);
        communityStatus = root.findViewById(R.id.community_status);
        apps = root.findViewById(R.id.apps_list);
        appsStatus = root.findViewById(R.id.apps_status);
        resources = root.findViewById(R.id.resources_list);
        resourcesStatus = root.findViewById(R.id.resources_status);
        // An id set on <include> replaces the included root id.
        communityRefresh = root.findViewById(R.id.page_community);
        appsRefresh = root.findViewById(R.id.page_apps);
        resourcesRefresh = root.findViewById(R.id.page_resources);
        int accent = requireContext().getColor(R.color.brand_primary);
        communityRefresh.setColorSchemeColors(accent);
        appsRefresh.setColorSchemeColors(accent);
        resourcesRefresh.setColorSchemeColors(accent);
        communityRefresh.setOnRefreshListener(() -> {
            loadBanners(); loadAnnouncement(); loadDynamics();
        });
        appsRefresh.setOnRefreshListener(this::loadApps);
        resourcesRefresh.setOnRefreshListener(this::loadResources);
        communityRefresh.post(() -> communityRefresh.setRefreshing(true));
        appsRefresh.post(() -> appsRefresh.setRefreshing(true));
        resourcesRefresh.post(() -> resourcesRefresh.setRefreshing(true));
    }

    private void loadAll() {
        loadBanners();
        loadAnnouncement();
        loadDynamics();
        loadApps();
        loadResources();
    }

    public void refreshContent() {
        if (account != null && isAdded()) loadAll();
    }

    private void loadBanners() {
        final long started = android.os.SystemClock.uptimeMillis();
        account.getV2Json("home_banners2", empty(), false, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                completeAtLeast(started, () -> {
                    JSONArray items = items(json, "items", "banners", "data");
                    banners.removeAllViews();
                    for (int i = 0; i < items.length(); i++) addBanner(items.optJSONObject(i));
                    bannerScroll.setVisibility(items.length() == 0 ? View.GONE : View.VISIBLE);
                });
            }
            @Override public void onError(int code, @NonNull String message) {
                completeAtLeast(started, () -> bannerScroll.setVisibility(View.GONE));
            }
        });
    }

    private void addBanner(@Nullable JSONObject item) {
        if (item == null) return;
        MaterialCardView card = card();
        card.setRadius(dp(8));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dp(280), dp(132));
        params.setMarginEnd(dp(12));
        card.setLayoutParams(params);
        LinearLayout content = column(dp(18));
        content.setGravity(Gravity.BOTTOM);
        String imageUrl = item.optString("image_url", "");
        if (!imageUrl.isEmpty()) {
            ImageView image = new ImageView(requireContext());
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            card.addView(image, new MaterialCardView.LayoutParams(-1, -1));
            View shade = new View(requireContext());
            shade.setBackgroundColor(Color.argb(92, 0, 0, 0));
            card.addView(shade, new MaterialCardView.LayoutParams(-1, -1));
            loadImage(imageUrl, image);
        }
        TextView tag = text(targetLabel(item.optString("target_type")), 12, false,
                imageUrl.isEmpty() ? R.color.brand_primary : R.color.white);
        TextView title = text(item.optString("title", "Typheye"), 20, true,
                imageUrl.isEmpty() ? R.color.text_primary : R.color.white);
        title.setMaxLines(2);
        content.addView(tag);
        content.addView(title, top(dp(6)));
        card.addView(content, new MaterialCardView.LayoutParams(-1, -1));
        card.setOnClickListener(v -> openTarget(item));
        banners.addView(card);
    }

    private void loadImage(String url, ImageView target) {
        loadImage(url, target, null);
    }

    private void loadImage(String url, ImageView target, @Nullable Runnable loaded) {
        Request request;
        try {
            request = new Request.Builder().url(url).build();
        } catch (IllegalArgumentException ignored) {
            return;
        }
        account.getClient().newCall(request).enqueue(new Callback() {
            @Override public void onFailure(@NonNull Call call, @NonNull IOException error) { }
            @Override public void onResponse(@NonNull Call call, @NonNull Response response) {
                try (Response body = response) {
                    if (!body.isSuccessful() || body.body() == null) return;
                    Bitmap bitmap = BitmapFactory.decodeStream(body.body().byteStream());
                    if (bitmap != null) onUi(() -> {
                        target.setImageBitmap(bitmap);
                        if (loaded != null) loaded.run();
                    });
                }
            }
        });
    }

    private void loadAnnouncement() {
        final long started = android.os.SystemClock.uptimeMillis();
        Map<String, String> query = new LinkedHashMap<>();
        query.put("page", "1");
        query.put("size", "1");
        account.getV2Json("announcements2", query, false, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                completeAtLeast(started, () -> {
                    JSONArray list = items(json, "items", "announcements", "data");
                    JSONObject first = list.optJSONObject(0);
                    if (first == null) {
                        announcement.setVisibility(View.GONE);
                        return;
                    }
                    announcementText.setText("公告  " + first.optString("title", first.optString("content")));
                    announcement.setVisibility(View.VISIBLE);
                    announcement.setOnClickListener(v -> showJsonDetail("公告", first));
                });
            }
            @Override public void onError(int code, @NonNull String message) {
                completeAtLeast(started, () -> announcement.setVisibility(View.GONE));
            }
        });
    }

    private void loadDynamics() {
        final long started = android.os.SystemClock.uptimeMillis();
        Map<String, String> query = pageQuery();
        query.put("feed", "public");
        account.getV2Json("dynamics2", query, false, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                completeAtLeast(started, () -> {
                    JSONArray list = items(json, "items", "dynamics", "data");
                    feed.removeAllViews();
                    for (int i = 0; i < list.length(); i++) addDynamic(list.optJSONObject(i));
                    showStatus(communityStatus, list.length() == 0, "社区里还没有动态");
                    communityRefresh.setRefreshing(false);
                });
            }
            @Override public void onError(int code, @NonNull String message) {
                completeAtLeast(started, () -> {
                    showStatus(communityStatus, true, readableError(code, message));
                    communityRefresh.setRefreshing(false);
                });
            }
        });
    }

    private void addDynamic(@Nullable JSONObject item) {
        if (item == null) return;
        View card = DynamicCardFactory.create(requireContext(), item, v -> startActivity(new Intent(requireContext(),
                com.typheye.wgpro.ui.function.community.DynamicDetailActivity.class)
                .putExtra(com.typheye.wgpro.ui.function.community.DynamicDetailActivity.EXTRA_DYNAMIC_ID,
                        item.optString("id", ""))));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.topMargin = dp(10);
        card.setLayoutParams(params);
        feed.addView(card);
    }

    private void loadApps() {
        final long started = android.os.SystemClock.uptimeMillis();
        account.getPublicJsonUrl("https://res.typheye.cn/api.php?type=app&page=1&size=20",
                new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        completeAtLeast(started, () -> {
                            JSONArray list = items(json, "items", "apps", "data", "info");
                            apps.removeAllViews();
                            for (int i = 0; i < list.length(); i++) addCatalogCard(apps,
                                    list.optJSONObject(i), true);
                            showStatus(appsStatus, list.length() == 0, "应用目录暂时为空");
                            appsRefresh.setRefreshing(false);
                        });
                    }
                    @Override public void onError(int code, @NonNull String message) {
                        completeAtLeast(started, () -> {
                            showStatus(appsStatus, true, readableError(code, message));
                            appsRefresh.setRefreshing(false);
                        });
                    }
                });
    }

    private void loadResources() {
        final long started = android.os.SystemClock.uptimeMillis();
        account.getV2Json("resources2", pageQuery(), false, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                completeAtLeast(started, () -> {
                    JSONArray list = items(json, "items", "resources", "data");
                    resources.removeAllViews();
                    for (int i = 0; i < list.length(); i++) addCatalogCard(resources,
                            list.optJSONObject(i), false);
                    showStatus(resourcesStatus, list.length() == 0, "还没有用户分享资源");
                    resourcesRefresh.setRefreshing(false);
                });
            }
            @Override public void onError(int code, @NonNull String message) {
                completeAtLeast(started, () -> {
                    showStatus(resourcesStatus, true, readableError(code, message));
                    resourcesRefresh.setRefreshing(false);
                });
            }
        });
    }

    private void addCatalogCard(LinearLayout parent, @Nullable JSONObject item, boolean app) {
        if (item == null) return;
        if (app) {
            addAppRow(parent, item);
            return;
        }
        MaterialCardView card = card();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.bottomMargin = dp(12);
        card.setLayoutParams(params);
        LinearLayout row = new LinearLayout(requireContext());
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(dp(16), dp(16), dp(16), dp(16));
        String titleValue = item.optString(app ? "name" : "title", app ? "未命名应用" : "未命名资源");
        TextView initial = text(first(titleValue), 18, true, R.color.brand_primary);
        initial.setGravity(Gravity.CENTER);
        initial.setBackgroundResource(R.drawable.bg_function_initial_blue);
        row.addView(initial, new LinearLayout.LayoutParams(dp(48), dp(48)));
        LinearLayout labels = column(0);
        labels.addView(text(titleValue, 16, true, R.color.text_primary));
        String summary = item.optString("summary", item.optString("description", "暂无介绍"));
        TextView sub = text(summary, 13, false, R.color.text_secondary);
        sub.setMaxLines(2);
        labels.addView(sub, top(dp(5)));
        String meta = app
                ? item.optString("version_name", item.optString("package", ""))
                : String.format(Locale.getDefault(), "%d 次下载  ·  %d 次收藏",
                item.optInt("download_count"), item.optInt("collection_count"));
        if (!meta.isEmpty()) labels.addView(text(meta, 12, false, R.color.text_secondary), top(dp(6)));
        LinearLayout.LayoutParams labelParams = new LinearLayout.LayoutParams(0, -2, 1);
        labelParams.setMarginStart(dp(14));
        row.addView(labels, labelParams);
        card.addView(row);
        card.setOnClickListener(v -> {
            String url = item.optString("detail_url", item.optString("url", ""));
            if (!url.isEmpty()) openUrl(url); else showJsonDetail(app ? "应用详情" : "资源详情", item);
        });
        parent.addView(card);
    }

    private void addAppRow(LinearLayout parent, JSONObject item) {
        LinearLayout row = new LinearLayout(requireContext());
        row.setGravity(Gravity.CENTER_VERTICAL); row.setPadding(dp(4), dp(12), dp(4), dp(12));
        row.setClickable(true); row.setFocusable(true);
        row.setBackgroundResource(R.drawable.bg_list_item_ripple);
        String name = item.optString("name", "未命名应用");
        FrameLayout iconBox = new FrameLayout(requireContext());
        TextView initial = text(first(name), 22, true, R.color.brand_primary);
        initial.setGravity(Gravity.CENTER); initial.setBackgroundResource(R.drawable.bg_function_initial_blue);
        iconBox.addView(initial, new FrameLayout.LayoutParams(-1, -1));
        ImageView icon = new ImageView(requireContext()); icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
        icon.setVisibility(View.GONE); icon.setBackgroundResource(R.drawable.bg_app_icon_clip);
        icon.setClipToOutline(true); iconBox.addView(icon, new FrameLayout.LayoutParams(-1, -1));
        row.addView(iconBox, new LinearLayout.LayoutParams(dp(56), dp(56)));
        String iconUrl = item.optString("icon_url", "");
        if (!iconUrl.isEmpty()) loadImage(iconUrl, icon, () -> { icon.setVisibility(View.VISIBLE); initial.setVisibility(View.GONE); });
        LinearLayout labels = column(0);
        TextView title = text(name, 17, false, R.color.text_primary); title.setMaxLines(1);
        labels.addView(title);
        String summary = item.optString("summary", item.optString("package", "应用"));
        TextView sub = text(summary, 14, false, R.color.text_secondary); sub.setMaxLines(1);
        labels.addView(sub, top(dp(5)));
        String version = item.optString("version_name", "");
        if (!version.isEmpty()) labels.addView(text("版本 " + version, 12, false, R.color.text_secondary), top(dp(6)));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, -2, 1f); lp.setMarginStart(dp(16));
        row.addView(labels, lp);
        row.setOnClickListener(v -> startActivity(new Intent(requireContext(), AppDetailActivity.class)
                .putExtra(AppDetailActivity.EXTRA_APP_JSON, item.toString())));
        parent.addView(row);
    }

    private MaterialCardView card() {
        MaterialCardView card = new MaterialCardView(requireContext());
        card.setCardBackgroundColor(color(R.color.surface_primary));
        card.setCardElevation(0);
        card.setStrokeWidth(0);
        card.setRadius(dp(8));
        card.setClickable(true);
        card.setFocusable(true);
        card.setRippleColor(ColorStateList.valueOf(color(R.color.brand_soft)));
        return card;
    }

    private LinearLayout column(int padding) {
        LinearLayout result = new LinearLayout(requireContext());
        result.setOrientation(LinearLayout.VERTICAL);
        result.setPadding(padding, padding, padding, padding);
        return result;
    }

    private TextView stat(String label, int count, boolean active) {
        String value = count < 0 ? label : label + " " + count;
        TextView view = text(value, 12, active, active ? R.color.brand_primary : R.color.text_secondary);
        view.setGravity(Gravity.CENTER);
        return view;
    }

    private TextView text(String value, int size, boolean bold, int color) {
        TextView view = new TextView(requireContext());
        view.setText(value);
        view.setTextSize(size);
        view.setTextColor(color(color));
        view.setTypeface(Typeface.DEFAULT, bold ? Typeface.BOLD : Typeface.NORMAL);
        return view;
    }

    private LinearLayout.LayoutParams top(int margin) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.topMargin = margin;
        return params;
    }

    private LinearLayout.LayoutParams weighted() {
        return new LinearLayout.LayoutParams(0, dp(36), 1);
    }

    private void openTarget(JSONObject item) {
        String type = item.optString("target_type", "none");
        String value = item.optString("target_value", "");
        if ("url".equals(type) && !value.isEmpty()) openUrl(value);
        else showJsonDetail(item.optString("title", "推荐内容"), item);
    }

    private void openUrl(String url) {
        Intent intent = new Intent(requireContext(), WebActivity.class);
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    private void showJsonDetail(String title, JSONObject item) {
        String message = item.optString("description", item.optString("content",
                item.optString("summary", "该内容暂时没有更多介绍。")));
        new WGProAlertDialogBuilder(requireContext()).setTitle(title).setMessage(message)
                .setNegativeButton("关闭", null).show();
    }

    private void showStatus(View view, boolean visible, String message) {
        ((TextView) view.findViewById(R.id.stream_empty_title)).setText(message);
        ((TextView) view.findViewById(R.id.stream_empty_description)).setText("下拉即可重新加载");
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private void onUi(Runnable action) {
        mainHandler.post(() -> {
            if (isAdded() && getView() != null) action.run();
        });
    }

    private void completeAtLeast(long started, Runnable action) {
        long delay = Math.max(0L, 300L - (android.os.SystemClock.uptimeMillis() - started));
        mainHandler.postDelayed(() -> {
            if (isAdded() && getView() != null) action.run();
        }, delay);
    }

    private static JSONArray items(JSONObject json, String... names) {
        for (String name : names) {
            JSONArray value = json.optJSONArray(name);
            if (value != null) return value;
        }
        JSONObject info = json.optJSONObject("info");
        if (info != null) {
            for (String name : names) {
                JSONArray value = info.optJSONArray(name);
                if (value != null) return value;
            }
        }
        return new JSONArray();
    }

    private static Map<String, String> empty() {
        return new LinkedHashMap<>();
    }

    private static Map<String, String> pageQuery() {
        Map<String, String> query = new LinkedHashMap<>();
        query.put("page", "1");
        query.put("size", "20");
        return query;
    }

    private String readableError(int code, String message) {
        if (code == 401) return "登录后即可查看此内容";
        return message == null || message.trim().isEmpty() ? "加载失败，请稍后重试" : message;
    }

    private String targetLabel(String type) {
        if ("app".equals(type)) return "应用推荐";
        if ("resource".equals(type)) return "资源推荐";
        if ("announcement".equals(type)) return "公告";
        return "推荐";
    }

    private static String first(String value) {
        String trimmed = value == null ? "" : value.trim();
        return trimmed.isEmpty() ? "?" : trimmed.substring(0, 1).toUpperCase(Locale.ROOT);
    }

    private int color(int id) { return requireContext().getColor(id); }
    private int dp(int value) { return Math.round(value * getResources().getDisplayMetrics().density); }

    private static final class LocalPageAdapter extends RecyclerView.Adapter<LocalPageAdapter.Holder> {
        private final List<View> pages;
        LocalPageAdapter(List<View> pages) { this.pages = pages; setHasStableIds(true); }
        @Override public long getItemId(int position) { return position; }
        @Override public int getItemCount() { return pages.size(); }
        @NonNull @Override public Holder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
            FrameLayout frame = new FrameLayout(parent.getContext());
            frame.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            return new Holder(frame);
        }
        @Override public void onBindViewHolder(@NonNull Holder holder, int position) {
            View page = pages.get(position);
            if (page.getParent() instanceof ViewGroup) ((ViewGroup) page.getParent()).removeView(page);
            holder.frame.removeAllViews();
            holder.frame.addView(page, new FrameLayout.LayoutParams(-1, -1));
        }
        static final class Holder extends RecyclerView.ViewHolder {
            final FrameLayout frame;
            Holder(FrameLayout frame) { super(frame); this.frame = frame; }
        }
    }
}
