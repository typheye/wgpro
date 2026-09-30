package com.typheye.wgpro.ui.main.mainFragments;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.card.MaterialCardView;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.ui.function.account.UserDetailActivity;
import com.typheye.wgpro.ui.function.community.AppDetailActivity;
import com.typheye.wgpro.ui.function.community.AppListItemFactory;
import com.typheye.wgpro.ui.function.community.DynamicCardFactory;
import com.typheye.wgpro.ui.function.community.CommunityWeb;
import com.typheye.wgpro.ui.function.community.ResourceMasonryFactory;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProBottomSheetDialog;
import com.typheye.wgpro.utils.ImageCache;
import com.typheye.wgpro.utils.tAccUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    private View bannerHost;
    private ViewPager2 bannerPager;
    private final Runnable advanceBanner = () -> {
        if (bannerPager != null && bannerPager.getAdapter() != null && bannerPager.getAdapter().getItemCount() > 1) {
            bannerPager.setCurrentItem((bannerPager.getCurrentItem() + 1) % bannerPager.getAdapter().getItemCount(), true);
            scheduleBannerAdvance();
        }
    };
    private BannerAdapter bannerAdapter;
    private String bannerSignature;
    private String dynamicsSignature;
    private String appsSignature;
    private String resourcesSignature;
    private int bannerRequestSeq;
    private LinearLayout bannerIndicator;
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
    private boolean loadedOnce;
    private SwipeRefreshLayout resourcesRefresh;

    /** 当前可见页（0 社区 / 1 应用 / 2 资源）：懒加载只拉用户真正看到的那一页。 */
    private int visiblePage;
    private boolean communityLoading;
    private boolean appsLoading;
    private boolean resourcesLoading;
    /** home2 在旧服务器上不存在时，退回三个独立接口只退一次。 */
    private boolean communityLegacyFallback;
    private long communityLoadedAt;
    private long appsLoadedAt;
    private long resourcesLoadedAt;
    /** 社区页 60 秒内回到首页不重复请求；应用/资源目录变化慢，给 5 分钟。 */
    private static final long COMMUNITY_TTL_MS = 60_000L;
    private static final long CATALOG_TTL_MS = 5 * 60_000L;
    /** 回到前台时，距上次加载不足这个时间就不刷新。 */
    private static final long RESUME_STALE_MS = 30_000L;
    /** 应用目录是静态 JSON（res.typheye.cn），单独放在这里便于缓存优先读取。 */
    private static final String APPS_URL = "https://res.typheye.cn/api.php?type=app&page=1&size=20";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        // 视图重建后清空签名，强制重新渲染
        dynamicsSignature = null;
        appsSignature = null;
        resourcesSignature = null;
        bindContent(root);
        setupPages(root);
        account = new tAccUtils(requireContext());
        // 只加载用户真正看到的第一页（社区）；应用/资源等切到那一页再拉。
        loadCommunity(false);
        return root;
    }

    private void setupPages(View root) {
        ViewPager2 pager = root.findViewById(R.id.home_pager);
        FrameLayout pool = root.findViewById(R.id.home_page_pool);
        List<View> pages = Arrays.asList(root.findViewById(R.id.page_community),
                root.findViewById(R.id.page_apps), root.findViewById(R.id.page_resources));
        for (View page : pages) pool.removeView(page);
        pager.setAdapter(new LocalPageAdapter(pages));
        pager.setOffscreenPageLimit(2);
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override public void onPageSelected(int position) {
                visiblePage = position;
                // 懒加载：某一页第一次被看到时才去拉数据，没看过的页一个请求都不发。
                if (position == 1 && appsLoadedAt == 0L && !appsLoading) loadApps(false);
                else if (position == 2 && resourcesLoadedAt == 0L && !resourcesLoading) loadResources(false);
                else if (position == 0 && communityLoadedAt == 0L && !communityLoading) loadCommunity(false);
            }
        });
    }

    private void bindContent(View root) {
        bannerHost = root.findViewById(R.id.community_banner_host);
        bannerPager = root.findViewById(R.id.community_banner_pager);
        bannerIndicator = root.findViewById(R.id.community_banner_indicator);
        // 滑动过程保留卡片的圆角，不被 Pager/RecyclerView 裁成直角。
        bannerPager.setClipChildren(false);
        bannerPager.setClipToPadding(false);
        if (bannerPager.getChildCount() > 0) {
            ViewGroup bannerRecycler = (ViewGroup) bannerPager.getChildAt(0);
            bannerRecycler.setClipChildren(false);
            bannerRecycler.setClipToPadding(false);
        }
        bannerPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override public void onPageScrollStateChanged(int state) {
                mainHandler.removeCallbacks(advanceBanner);
                if (state == ViewPager2.SCROLL_STATE_IDLE) scheduleBannerAdvance();
            }
            @Override public void onPageSelected(int position) {
                renderBannerIndicator(position);
            }
        });
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
        communityRefresh.setOnRefreshListener(() -> loadCommunity(true));
        appsRefresh.setOnRefreshListener(() -> loadApps(true));
        resourcesRefresh.setOnRefreshListener(() -> loadResources(true));
    }

    /** 首页三个 tab 的懒加载入口：force=true 表示用户主动下拉刷新（必须走网络）。 */
    private void loadVisiblePage(boolean force) {
        if (visiblePage == 1) loadApps(force);
        else if (visiblePage == 2) loadResources(force);
        else loadCommunity(force);
    }

    @Override public void onResume() {
        super.onResume();
        // 首次进入由 onCreateView 负责拉起当前页。
        if (!loadedOnce || account == null || getView() == null) return;
        // 只刷新当前可见页；距上次加载不足 30 秒连请求都不发（下拉刷新永远强刷）。
        long loadedAt = visiblePage == 1 ? appsLoadedAt
                : visiblePage == 2 ? resourcesLoadedAt : communityLoadedAt;
        if (android.os.SystemClock.uptimeMillis() - loadedAt >= RESUME_STALE_MS) {
            loadVisiblePage(false);
        }
    }

    public void refreshContent() {
        if (account != null && isAdded()) loadVisiblePage(false);
    }

    /**
     * 社区页：一次 {@code home2} 聚合请求取回轮播 + 公告 + 动态流。
     *
     * <p>原来这里是 3 个并发请求（home_banners2 / announcements2 / dynamics2）。
     * 在高延迟链路上「少一次往返」比「每个请求快一点」更值钱，因此服务端提供了聚合接口。
     * 若服务器较旧（没有 home2），会自动退回三个老接口，且只退一次。</p>
     */
    private void loadCommunity(boolean force) {
        if (communityLoading) return;
        communityLoading = true;
        loadedOnce = true;
        final long started = android.os.SystemClock.uptimeMillis();
        final int requestId = ++bannerRequestSeq;
        communityRefresh.setRefreshing(true);
        account.getV2JsonCached("home2", communityQuery(), false,
                force ? 0L : COMMUNITY_TTL_MS, new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        completeAtLeast(started, () -> {
                            communityLoading = false;
                            communityLoadedAt = android.os.SystemClock.uptimeMillis();
                            if (requestId != bannerRequestSeq) return;
                            renderBanners(items(json, "banners"));
                            renderAnnouncement(json.optJSONObject("announcement"));
                            renderDynamics(items(json, "items", "dynamics", "data"));
                            communityRefresh.setRefreshing(false);
                        });
                    }

                    @Override public void onError(int code, @NonNull String message) {
                        communityLoading = false;
                        communityLoadedAt = android.os.SystemClock.uptimeMillis();
                        if (!communityLegacyFallback) {
                            // 老服务器不认识 home2：退回三个独立接口，保证首页依然可用。
                            communityLegacyFallback = true;
                            loadCommunityLegacy(started, requestId);
                            return;
                        }
                        completeAtLeast(started, () -> {
                            communityRefresh.setRefreshing(false);
                            if (requestId != bannerRequestSeq) return;
                            showLoadError("动态加载失败", readableError(code, message));
                        });
                    }
                });
    }

    private static Map<String, String> communityQuery() {
        Map<String, String> query = pageQuery();
        query.put("feed", "public");
        return query;
    }

    /** 兼容旧服务器的社区页兜底：轮播 / 公告 / 动态 各请求一次，全部返回后收起菊花。 */
    private void loadCommunityLegacy(long started, int requestId) {
        final java.util.concurrent.atomic.AtomicInteger pending =
                new java.util.concurrent.atomic.AtomicInteger(3);
        Runnable settle = () -> completeAtLeast(started, () -> {
            if (requestId != bannerRequestSeq) return;
            communityRefresh.setRefreshing(false);
        });
        account.getV2Json("home_banners2", empty(), false, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                JSONArray banners = items(json, "items", "banners", "data");
                completeAtLeast(started, () -> {
                    if (requestId == bannerRequestSeq) renderBanners(banners);
                });
                if (pending.decrementAndGet() == 0) settle.run();
            }

            @Override public void onError(int code, @NonNull String message) {
                if (pending.decrementAndGet() == 0) settle.run();
            }
        });
        Map<String, String> one = new LinkedHashMap<>();
        one.put("page", "1");
        one.put("size", "1");
        account.getV2Json("announcements2", one, false, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                JSONObject first = items(json, "items", "announcements", "data").optJSONObject(0);
                completeAtLeast(started, () -> {
                    if (requestId == bannerRequestSeq) renderAnnouncement(first);
                });
                if (pending.decrementAndGet() == 0) settle.run();
            }

            @Override public void onError(int code, @NonNull String message) {
                if (pending.decrementAndGet() == 0) settle.run();
            }
        });
        account.getV2JsonFresh("dynamics2", communityQuery(), false, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                JSONArray list = items(json, "items", "dynamics", "data");
                completeAtLeast(started, () -> {
                    if (requestId == bannerRequestSeq) renderDynamics(list);
                });
                if (pending.decrementAndGet() == 0) settle.run();
            }

            @Override public void onError(int code, @NonNull String message) {
                if (pending.decrementAndGet() == 0) settle.run();
            }
        });
    }

    private void renderBanners(@NonNull JSONArray items) {
        String signature = bannerSignature(items);
        if (bannerAdapter != null
                && bannerHost.getVisibility() == View.VISIBLE
                && signature.equals(bannerSignature)) {
            // 服务端内容没有变化：保留当前页与已加载图片，不再重建适配器，避免闪白。
            scheduleBannerAdvance();
            return;
        }
        java.util.ArrayList<View> pages = new java.util.ArrayList<>();
        for (int i = 0; i < items.length(); i++) {
            View card = createBanner(items.optJSONObject(i));
            if (card != null) pages.add(card);
        }
        bannerHost.setVisibility(items.length() == 0 ? View.GONE : View.VISIBLE);
        if (bannerAdapter == null) {
            bannerAdapter = new BannerAdapter();
            bannerPager.setAdapter(bannerAdapter);
        }
        int previousItem = bannerPager.getCurrentItem();
        bannerAdapter.submit(pages);
        if (!pages.isEmpty() && previousItem >= pages.size()) {
            bannerPager.setCurrentItem(pages.size() - 1, false);
        }
        renderBannerIndicator(bannerPager.getCurrentItem());
        bannerSignature = signature;
        scheduleBannerAdvance();
    }

    private void renderAnnouncement(@Nullable JSONObject first) {
        if (first == null || first.length() == 0) {
            announcement.setVisibility(View.GONE);
            return;
        }
        announcementText.setText("公告  " + first.optString("title", first.optString("content")));
        announcement.setVisibility(View.VISIBLE);
        announcement.setOnClickListener(v -> showJsonDetail("公告", first));
    }

    private void renderDynamics(@NonNull JSONArray list) {
        String signature = listSignature(list, "id", "content", "like_count",
                "comment_count", "forward_count", "collection_count", "is_liked", "is_favorited");
        if (!signature.equals(dynamicsSignature)) {
            dynamicsSignature = signature;
            feed.removeAllViews();
            for (int i = 0; i < list.length(); i++) addDynamic(list.optJSONObject(i));
        }
        showStatus(communityStatus, list.length() == 0, "社区里还没有动态");
    }

    private View createBanner(@Nullable JSONObject item) {
        if (item == null) return null;
        MaterialCardView card = card();
        card.setRadius(0);
        int[] placeholders = {R.color.banner_blue, R.color.banner_green,
                R.color.banner_coral, R.color.banner_amber};
        card.setCardBackgroundColor(requireContext().getColor(
                placeholders[Math.abs(item.optString("title", "").hashCode() % placeholders.length)]));
        card.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        LinearLayout content = column(0);
        content.setGravity(Gravity.BOTTOM);
        content.setPadding(dp(16), dp(16), dp(76), dp(15));
        String imageUrl = item.optString("image_url", "");
        if (!imageUrl.isEmpty()) {
            ImageView image = new ImageView(requireContext());
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
            card.addView(image, new MaterialCardView.LayoutParams(-1, -1));
            ImageCache.load(requireContext(), imageUrl, image, null);
        }
        View shade = new View(requireContext());
        shade.setBackground(new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP,
                new int[]{0xC2000000, 0x52000000, 0x00000000}));
        card.addView(shade, new MaterialCardView.LayoutParams(-1, -1));
        TextView tag = text(targetLabel(item.optString("target_type")), 11, true, R.color.white);
        GradientDrawable chip = new GradientDrawable();
        chip.setColor(Color.argb(76, 0, 0, 0));
        chip.setCornerRadius(dp(7));
        tag.setBackground(chip);
        tag.setPadding(dp(9), dp(4), dp(9), dp(4));
        LinearLayout.LayoutParams tagParams = new LinearLayout.LayoutParams(-2, -2);
        content.addView(tag, tagParams);
        TextView title = text(item.optString("title", "Typheye"), 18, true, R.color.white);
        title.setMaxLines(2);
        title.setLineSpacing(0f, 1.08f);
        content.addView(title, top(dp(8)));
        card.addView(content, new MaterialCardView.LayoutParams(-1, -1));
        card.setOnClickListener(v -> openTarget(item));
        return card;
    }

    private void scheduleBannerAdvance() {
        mainHandler.removeCallbacks(advanceBanner);
        if (isResumed()) mainHandler.postDelayed(advanceBanner, 5000L);
    }

    private void renderBannerIndicator(int position) {
        if (bannerIndicator == null) return;
        bannerIndicator.removeAllViews();
        int count = bannerAdapter == null ? 0 : bannerAdapter.getItemCount();
        if (count <= 1) {
            bannerIndicator.setVisibility(View.GONE);
            return;
        }
        bannerIndicator.setVisibility(View.VISIBLE);
        if (position < 0 || position >= count) position = 0;
        for (int i = 0; i < count; i++) {
            View dot = new View(requireContext());
            GradientDrawable dotShape = new GradientDrawable();
            dotShape.setShape(GradientDrawable.OVAL);
            dotShape.setColor(i == position
                    ? Color.WHITE
                    : Color.argb(88, 255, 255, 255));
            dot.setBackground(dotShape);
            int dotSize = dp(6);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dotSize, dotSize);
            if (i > 0) params.setMarginStart(dp(6));
            bannerIndicator.addView(dot, params);
        }
    }

    @Override public void onPause() { mainHandler.removeCallbacks(advanceBanner); super.onPause(); }

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

    private void addDynamic(@Nullable JSONObject item) {
        if (item == null) return;
        View card = DynamicCardFactory.create(requireContext(), item, v -> startActivity(new Intent(requireContext(),
                com.typheye.wgpro.ui.function.community.DynamicDetailActivity.class)
                .putExtra(com.typheye.wgpro.ui.function.community.DynamicDetailActivity.EXTRA_DYNAMIC_ID,
                        item.optString("id", ""))), () -> loadCommunity(true));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.topMargin = dp(10);
        card.setLayoutParams(params);
        feed.addView(card);
    }

    /** 应用目录：切到该 tab 时才加载；目录变化慢，TTL 内直接用本地缓存、不发请求。 */
    private void loadApps(boolean force) {
        if (appsLoading) return;
        appsLoading = true;
        appsRefresh.setRefreshing(true);
        final long started = android.os.SystemClock.uptimeMillis();
        tAccUtils.JsonCallback callback = new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                completeAtLeast(started, () -> {
                    appsLoading = false;
                    appsLoadedAt = android.os.SystemClock.uptimeMillis();
                    JSONArray list = items(json, "items", "apps", "data", "info");
                    String signature = listSignature(list, "package", "id", "name", "summary", "version_name");
                    if (!signature.equals(appsSignature)) {
                        appsSignature = signature;
                        apps.removeAllViews();
                        for (int i = 0; i < list.length(); i++) addCatalogCard(apps,
                                list.optJSONObject(i), true);
                    }
                    showStatus(appsStatus, list.length() == 0, "应用目录暂时为空");
                    appsRefresh.setRefreshing(false);
                });
            }

            @Override public void onError(int code, @NonNull String message) {
                appsLoading = false;
                appsLoadedAt = android.os.SystemClock.uptimeMillis();
                completeAtLeast(started, () -> {
                    appsStatus.setVisibility(View.GONE);
                    showLoadError("应用加载失败", message);
                    appsRefresh.setRefreshing(false);
                });
            }
        };
        if (force) {
            account.getPublicJsonUrl(APPS_URL, callback);
        } else {
            account.getPublicJsonUrlCached(APPS_URL, CATALOG_TTL_MS, callback);
        }
    }

    /** 资源列表：同上，懒加载 + TTL 缓存优先。 */
    private void loadResources(boolean force) {
        if (resourcesLoading) return;
        resourcesLoading = true;
        resourcesRefresh.setRefreshing(true);
        final long started = android.os.SystemClock.uptimeMillis();
        tAccUtils.JsonCallback callback = new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                completeAtLeast(started, () -> {
                    resourcesLoading = false;
                    resourcesLoadedAt = android.os.SystemClock.uptimeMillis();
                    JSONArray list = items(json, "items", "resources", "data");
                    String signature = listSignature(list, "id", "title", "summary",
                            "description", "collection_count", "is_collected", "status");
                    if (!signature.equals(resourcesSignature)) {
                        resourcesSignature = signature;
                        resources.removeAllViews();
                        resources.addView(ResourceMasonryFactory.create(requireContext(), list, item -> {
                            startActivity(new Intent(requireContext(),
                                    com.typheye.wgpro.ui.function.community.ResourceDetailActivity.class)
                                    .putExtra(com.typheye.wgpro.ui.function.community.ResourceDetailActivity.EXTRA_RESOURCE_JSON,
                                            item.toString()));
                        }, (item, anchor) -> showCatalogActions(item, false)), new LinearLayout.LayoutParams(-1, -2));
                    }
                    showStatus(resourcesStatus, list.length() == 0, "还没有用户分享资源");
                    resourcesRefresh.setRefreshing(false);
                });
            }

            @Override public void onError(int code, @NonNull String message) {
                resourcesLoading = false;
                resourcesLoadedAt = android.os.SystemClock.uptimeMillis();
                completeAtLeast(started, () -> {
                    resourcesStatus.setVisibility(View.GONE);
                    showLoadError("资源加载失败", message);
                    resourcesRefresh.setRefreshing(false);
                });
            }
        };
        account.getV2JsonCached("resources2", pageQuery(), false,
                force ? 0L : CATALOG_TTL_MS, callback);
    }

    private void addCatalogCard(LinearLayout parent, @Nullable JSONObject item, boolean app) {
        if (item == null) return;
        if (app) {
            parent.addView(AppListItemFactory.create(requireContext(), item, v -> showCatalogActions(item, true)));
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
                : String.format(Locale.getDefault(), "%d 次下载  ·  %d 次星标",
                item.optInt("download_count"), item.optInt("collection_count"));
        if (!meta.isEmpty()) labels.addView(text(meta, 12, false, R.color.text_secondary), top(dp(6)));
        LinearLayout.LayoutParams labelParams = new LinearLayout.LayoutParams(0, -2, 1);
        labelParams.setMarginStart(dp(14));
        row.addView(labels, labelParams);
        card.addView(row);
        card.setOnClickListener(v -> {
            if (app) showJsonDetail("应用详情", item);
            else startActivity(new Intent(requireContext(),
                    com.typheye.wgpro.ui.function.community.ResourceDetailActivity.class)
                    .putExtra(com.typheye.wgpro.ui.function.community.ResourceDetailActivity.EXTRA_RESOURCE_JSON,
                            item.toString()));
        });
        parent.addView(card);
    }

    private void showLoadError(String title, String message) {
        if (!isAdded()) return;
        new WGProAlertDialogBuilder(requireContext()).setTitle(title)
                .setMessage(message == null || message.isEmpty() ? "请稍后重试" : message)
                .setNegativeButton("关闭", null).show();
    }

    private void showCatalogActions(JSONObject item, boolean app) {
        String owner = item.optString("uid", item.optString("author_uid", item.optString("publisher_uid", "")));
        boolean mine = !owner.isEmpty() && owner.equals(account.getUid());
        String[] actions = mine ? new String[]{"删除", "分享"} : new String[]{"举报", "分享"};
        new WGProAlertDialogBuilder(requireContext()).setTitle(app ? "应用操作" : "资源操作").setItems(actions, (d,w) -> {
            if ("分享".equals(actions[w])) { Intent share = new Intent(Intent.ACTION_SEND).setType("text/plain").putExtra(Intent.EXTRA_TEXT, (app ? "应用：" : "资源：") + item.optString(app ? "name" : "title", "未知")); startActivity(Intent.createChooser(share, "分享")); return; }
            Map<String,String> fields = new LinkedHashMap<>();
            if (mine) { fields.put(app ? "package" : "resource_id", item.optString(app ? "package" : "id")); runCatalogAction(app ? "app_delete2" : "resource_delete2", fields, "确认删除？", "删除后无法恢复。", "已删除"); }
            else {
                CommunityWeb.openReport(requireContext(), app ? "app" : "resource",
                        item.optString(app ? "package" : "id"),
                        item.optString(app ? "name" : "title", app ? "应用" : "资源"));
            }
        }).show();
    }

    private void runCatalogAction(String action, Map<String,String> fields, String title, String message, String success) {
        new WGProAlertDialogBuilder(requireContext()).setTitle(title).setMessage(message).setNegativeButton("取消", null).setPositiveButton("确认", (d,w) -> {
            if (d instanceof WGProBottomSheetDialog) ((WGProBottomSheetDialog) d).dismissForReplacement();
            mainHandler.postDelayed(() -> startCatalogOperation(action, fields, success), 40L);
        }).show();
    }

    private void startCatalogOperation(String action, Map<String,String> fields, String success) {
            View progressView = View.inflate(requireContext(), R.layout.progress_dialog, null);
            ((TextView) progressView.findViewById(android.R.id.message)).setText("正在处理...");
            WGProBottomSheetDialog progress = new WGProAlertDialogBuilder(requireContext()).setTitle("处理中")
                    .setView(progressView).setCancelable(false).create();
            progress.show();
            long started = android.os.SystemClock.uptimeMillis();
            account.postV2Json(action, fields, new tAccUtils.JsonCallback() {
                public void onSuccess(JSONObject json) { mainHandler.postDelayed(() -> { if (!isAdded()) return; progress.dismissForReplacement(); new WGProAlertDialogBuilder(requireContext()).setTitle("操作成功").setMessage(success).setNegativeButton("关闭", null).show(); if ("app_delete2".equals(action)) loadApps(true); else loadResources(true); }, Math.max(0, 300 - (android.os.SystemClock.uptimeMillis() - started))); }
                public void onError(int code,String msg) { mainHandler.postDelayed(() -> { progress.dismissForReplacement(); showLoadError("操作失败", msg); }, Math.max(0, 300 - (android.os.SystemClock.uptimeMillis() - started))); }
            });
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
        card.setCardBackgroundColor(color(R.color.surface_secondary));
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

    private static String bannerSignature(JSONArray items) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.optJSONObject(i);
            if (item == null) continue;
            sb.append(item.optString("id", "")).append('\u0001')
                    .append(item.optString("title", "")).append('\u0001')
                    .append(item.optString("image_url", "")).append('\u0001')
                    .append(item.optString("target_type", "")).append('\u0001')
                    .append(item.optString("target_value", "")).append('\u0001')
                    .append(item.optString("description", "")).append('\u0001')
                    .append(item.optString("content", "")).append('\u0001')
                    .append(item.optString("summary", "")).append('\n');
        }
        return sb.toString();
    }

    /** 列表数据签名：相同就不重建列表，避免刷新时图片闪动。 */
    private static String listSignature(JSONArray items, String... keys) {
        StringBuilder sb = new StringBuilder();
        if (items == null) return "";
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.optJSONObject(i);
            if (item == null) continue;
            for (String key : keys) sb.append(item.optString(key, "")).append('\u0001');
            sb.append('\n');
        }
        return sb.toString();
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

    private static final class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.Holder> {
        private final List<View> pages = new java.util.ArrayList<>();

        void submit(List<View> newPages) {
            pages.clear();
            pages.addAll(newPages);
            notifyDataSetChanged();
        }

        @Override public int getItemCount() { return pages.size(); }
        @NonNull @Override public Holder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
            FrameLayout frame = new FrameLayout(parent.getContext());
            frame.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            frame.setClipChildren(false);
            frame.setClipToPadding(false);
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
