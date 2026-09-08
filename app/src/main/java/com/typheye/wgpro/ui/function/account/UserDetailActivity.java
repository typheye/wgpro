package com.typheye.wgpro.ui.function.account;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.ColorStateList;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.button.MaterialButton;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.utils.tAccUtils;
import com.typheye.wgpro.ui.function.community.CloudListFragment;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

/** Reusable profile surface used when opening the account card. */
public class UserDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TARGET_UID = "target_uid";
    private tAccUtils account;
    private Toolbar toolbar;
    private AppBarLayout appBar;
    private Menu toolbarMenu;
    private View detailRoot;
    private View detailSheet;
    private View expandedIdentity;
    private View collapsedIdentity;
    private BottomSheetBehavior<View> sheetBehavior;
    private ViewTreeObserver.OnPreDrawListener pendingProfileLayout;
    private int lastRootWidth = -1;
    private int lastRootHeight = -1;
    private String targetUid;
    private boolean isSelf;
    private boolean following;
    private boolean canUnfollow = true;
    private MaterialButton followButton;
    private long profileLoadingStarted;
    private final Runnable settledWindowLayout = () -> {
        if (detailRoot != null && detailRoot.isAttachedToWindow()) {
            scheduleProfileLayout(true);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        AppUtils.useScreenCutArea(getWindow(), this);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(getColor(R.color.surface_page));
        new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView())
                .setAppearanceLightStatusBars(false);
        setContentView(R.layout.activity_user_detail);
        profileLoadingStarted = android.os.SystemClock.uptimeMillis();
        account = new tAccUtils(this);
        String requestedUid = getIntent().getStringExtra(EXTRA_TARGET_UID);
        targetUid = requestedUid == null || requestedUid.trim().isEmpty()
                ? account.getUid() : requestedUid.trim();
        isSelf = targetUid.equals(account.getUid());
        toolbar = findViewById(R.id.toolbar);
        appBar = findViewById(R.id.detail_app_bar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        setTitle("");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        TextView avatarText = findViewById(R.id.detail_avatar_text);
        TextView collapsedAvatarText = findViewById(R.id.detail_collapsed_avatar_text);
        ImageView avatar = findViewById(R.id.detail_avatar);
        ImageView collapsedAvatar = findViewById(R.id.detail_collapsed_avatar);
        String nick = isSelf ? account.getNick() : "用户";
        String uid = targetUid;
        String bio = isSelf ? account.getShuo() : "";
        String displayName = nick == null || nick.trim().isEmpty() ? "用户" : nick.trim();
        ((TextView) findViewById(R.id.detail_nick)).setText(displayName);
        ((TextView) findViewById(R.id.detail_collapsed_nick)).setText(displayName);
        ((TextView) findViewById(R.id.detail_uid)).setText(uid == null || uid.isEmpty() ? "UID 未知" : "UID " + uid);
        ((TextView) findViewById(R.id.detail_bio)).setText(bio == null || bio.trim().isEmpty()
                ? "这个人还没有简介呢~" : bio.trim());
        String avatarInitial = displayName.substring(0, 1).toUpperCase();
        avatarText.setText(avatarInitial);
        collapsedAvatarText.setText(avatarInitial);
        if (uid != null && !uid.isEmpty()) {
            File file = new File(getFilesDir(), "avatar_" + uid + ".jpg");
            Bitmap bitmap = file.exists() ? BitmapFactory.decodeFile(file.getAbsolutePath()) : null;
            if (bitmap != null) {
                avatar.setImageBitmap(bitmap);
                avatar.setVisibility(View.VISIBLE);
                avatarText.setVisibility(View.GONE);
                collapsedAvatar.setImageBitmap(bitmap);
                collapsedAvatar.setVisibility(View.VISIBLE);
                collapsedAvatarText.setVisibility(View.GONE);
            }
        }
        if (avatar.getVisibility() != View.VISIBLE) {
            collapsedAvatar.setVisibility(View.GONE);
            collapsedAvatarText.setVisibility(View.VISIBLE);
        }

        followButton = findViewById(R.id.detail_follow);
        MaterialButton messageButton = findViewById(R.id.detail_message);
        followButton.setVisibility(isSelf ? View.GONE : View.VISIBLE);
        messageButton.setVisibility(isSelf ? View.GONE : View.VISIBLE);
        followButton.setOnClickListener(v -> {
            if (following) {
                new WGProAlertDialogBuilder(this).setTitle("取消关注")
                        .setMessage("确定要取消关注该用户吗？")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("取消关注", (dialog, which) -> changeFollowState()).show();
            } else {
                changeFollowState();
            }
        });
        messageButton.setOnClickListener(v -> startActivity(new Intent(this,
                com.typheye.wgpro.ui.function.community.ChatActivity.class)
                .putExtra(com.typheye.wgpro.ui.function.community.ChatActivity.EXTRA_PEER_UID, targetUid)
                .putExtra(com.typheye.wgpro.ui.function.community.ChatActivity.EXTRA_PEER_NAME,
                        ((TextView) findViewById(R.id.detail_nick)).getText().toString())));

        setupProfileSheet();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            RenderEffect blur = RenderEffect.createBlurEffect(18f, 18f, Shader.TileMode.CLAMP);
            appBar.setRenderEffect(blur);
            detailSheet.setRenderEffect(blur);
        }
        setupProfilePages();
        loadPublicProfile(avatar, collapsedAvatar, avatarText, collapsedAvatarText);
        appBar.post(this::applyAdaptiveChromeColors);
    }

    private void loadPublicProfile(ImageView avatar, ImageView collapsedAvatar,
                                   TextView avatarText, TextView collapsedAvatarText) {
        if (targetUid == null || targetUid.isEmpty()) return;
        account.getV2Json("user_profile2", java.util.Collections.singletonMap("target_uid", targetUid),
                false, new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        JSONObject info = json.optJSONObject("info");
                        runOnUiThread(() -> {
                            if (isFinishing() || isDestroyed() || info == null) return;
                            String name = info.optString("nick", "用户");
                            String bio = info.optString("bio", "").trim();
                            ((TextView) findViewById(R.id.detail_nick)).setText(name);
                            ((TextView) findViewById(R.id.detail_collapsed_nick)).setText(name);
                            ((TextView) findViewById(R.id.detail_uid)).setText("UID " + info.optString("uid", targetUid));
                            ((TextView) findViewById(R.id.detail_bio)).setText(bio.isEmpty()
                                    ? "这个人还没有简介呢~" : bio);
                            String initial = name.isEmpty() ? "U" : name.substring(0, 1).toUpperCase();
                            avatarText.setText(initial); collapsedAvatarText.setText(initial);
                            following = info.optBoolean("is_following", false);
                            if ("10001".equals(targetUid) && getSharedPreferences("local_follow_state", MODE_PRIVATE)
                                    .getBoolean("unfollow_10001", false)) following = false;
                            canUnfollow = info.optBoolean("can_unfollow", true);
                            updateFollowButton();
                            expandedIdentity.setVisibility(View.VISIBLE);
                            collapsedIdentity.setVisibility(View.VISIBLE);
                            String avatarUrl = info.optString("avatar_url", "");
                            if (!avatarUrl.isEmpty()) loadRemoteAvatar(avatarUrl, avatar, collapsedAvatar,
                                    avatarText, collapsedAvatarText);
                            finishProfileLoading();
                        });
                    }
                    @Override public void onError(int code, @NonNull String message) {
                        runOnUiThread(() -> {
                            expandedIdentity.setVisibility(View.VISIBLE);
                            collapsedIdentity.setVisibility(View.VISIBLE);
                            finishProfileLoading();
                        });
                    }
                });
    }

    private void finishProfileLoading() {
        long delay = Math.max(0L, 300L - (android.os.SystemClock.uptimeMillis() - profileLoadingStarted));
        findViewById(R.id.detail_loading_overlay).postDelayed(() -> {
            if (isFinishing() || isDestroyed()) return;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                appBar.setRenderEffect(null);
                detailSheet.setRenderEffect(null);
            }
            View overlay = findViewById(R.id.detail_loading_overlay);
            overlay.animate().alpha(0f).setDuration(120L).withEndAction(() -> overlay.setVisibility(View.GONE)).start();
        }, delay);
    }

    private void updateFollowButton() {
        if (followButton == null || isSelf) return;
        followButton.setText(following ? "已关注" : "关注");
        followButton.setStrokeWidth(following ? dp(1) : 0);
        int messageButtonColor = Color.argb(0x40, 0xFF, 0xFF, 0xFF);
        followButton.setStrokeColor(ColorStateList.valueOf(messageButtonColor));
        followButton.setTextColor(following ? getColor(R.color.brand_primary) : Color.WHITE);
        followButton.setBackgroundTintList(ColorStateList.valueOf(
                following ? Color.TRANSPARENT : messageButtonColor));
        followButton.setEnabled(!following || canUnfollow || "10001".equals(targetUid));
    }

    private void changeFollowState() {
        if (targetUid == null || targetUid.isEmpty()) return;
        if (!account.isLogin()) {
            new WGProAlertDialogBuilder(this).setTitle("需要登录")
                    .setMessage("登录后才能关注其他用户。")
                    .setNegativeButton("关闭", null).show();
            return;
        }
        followButton.setEnabled(false);
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("target_uid", targetUid);
        fields.put("action", following ? "unfollow" : "follow");
        account.postV2Json("follow_action2", fields, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                runOnUiThread(() -> {
                    following = json.optBoolean("following", !following);
                    if ("10001".equals(targetUid)) getSharedPreferences("local_follow_state", MODE_PRIVATE)
                            .edit().putBoolean("unfollow_10001", !following).apply();
                    canUnfollow = json.optBoolean("can_unfollow", true);
                    updateFollowButton();
                });
            }
            @Override public void onError(int code, @NonNull String message) {
                runOnUiThread(() -> {
                    if ("10001".equals(targetUid) && following) {
                        // The service intentionally rejects unfollowing the official account.
                        // Keep the client-side choice available without claiming the server changed.
                        following = false;
                        canUnfollow = true;
                        getSharedPreferences("local_follow_state", MODE_PRIVATE).edit()
                                .putBoolean("unfollow_10001", true).apply();
                        updateFollowButton();
                        android.widget.Toast.makeText(UserDetailActivity.this,
                                "已在本地隐藏关注状态", android.widget.Toast.LENGTH_SHORT).show();
                        return;
                    }
                    updateFollowButton();
                    new WGProAlertDialogBuilder(UserDetailActivity.this).setTitle("操作失败")
                            .setMessage(message).setNegativeButton("关闭", null).show();
                });
            }
        });
    }

    private void loadRemoteAvatar(String url, ImageView avatar, ImageView collapsedAvatar,
                                  TextView avatarText, TextView collapsedAvatarText) {
        if (url.startsWith("/")) url = "https://service.typheye.cn" + url;
        Request request;
        try { request = new Request.Builder().url(url).build(); }
        catch (IllegalArgumentException ignored) { return; }
        account.getClient().newCall(request).enqueue(new Callback() {
            @Override public void onFailure(@NonNull Call call, @NonNull IOException e) { }
            @Override public void onResponse(@NonNull Call call, @NonNull Response response) {
                try (Response body = response) {
                    if (!body.isSuccessful() || body.body() == null) return;
                    Bitmap bitmap = BitmapFactory.decodeStream(body.body().byteStream());
                    if (bitmap == null) return;
                    runOnUiThread(() -> {
                        if (isFinishing() || isDestroyed()) return;
                        avatar.setImageBitmap(bitmap); avatar.setVisibility(View.VISIBLE); avatarText.setVisibility(View.GONE);
                        collapsedAvatar.setImageBitmap(bitmap); collapsedAvatar.setVisibility(View.VISIBLE);
                        collapsedAvatarText.setVisibility(View.GONE);
                    });
                }
            }
        });
    }

    private void setupProfileSheet() {
        detailRoot = findViewById(R.id.detail_root);
        detailSheet = findViewById(R.id.detail_sheet);
        expandedIdentity = findViewById(R.id.detail_expanded_identity);
        collapsedIdentity = findViewById(R.id.detail_collapsed_identity);
        sheetBehavior = BottomSheetBehavior.from(detailSheet);
        sheetBehavior.setFitToContents(false);
        sheetBehavior.setHideable(false);
        sheetBehavior.setDraggable(true);
        sheetBehavior.setShouldRemoveExpandedCorners(false);
        sheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HALF_EXPANDED) {
                    sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }

            @Override public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                float progress = Math.max(0f, Math.min(1f, slideOffset));
                expandedIdentity.setAlpha(1f - Math.min(1f, progress / 0.72f));
                collapsedIdentity.setAlpha(Math.max(0f, (progress - 0.78f) / 0.22f));
            }
        });
        detailRoot.addOnLayoutChangeListener((view, left, top, right, bottom,
                                               oldLeft, oldTop, oldRight, oldBottom) -> {
            int width = right - left;
            int height = bottom - top;
            if (width == lastRootWidth && height == lastRootHeight) return;
            boolean preserveState = lastRootWidth >= 0 && lastRootHeight >= 0;
            lastRootWidth = width;
            lastRootHeight = height;
            scheduleProfileLayout(preserveState);
            if (preserveState) scheduleSettledWindowLayout();
        });
    }

    private void scheduleSettledWindowLayout() {
        detailRoot.removeCallbacks(settledWindowLayout);
        detailRoot.postDelayed(settledWindowLayout, 350L);
    }

    private void scheduleProfileLayout(boolean preserveState) {
        int currentState = sheetBehavior.getState();
        if (preserveState && (currentState == BottomSheetBehavior.STATE_DRAGGING
                || currentState == BottomSheetBehavior.STATE_SETTLING)) {
            scheduleSettledWindowLayout();
            return;
        }
        int targetState = preserveState && currentState == BottomSheetBehavior.STATE_EXPANDED
                ? BottomSheetBehavior.STATE_EXPANDED
                : BottomSheetBehavior.STATE_COLLAPSED;
        ViewTreeObserver observer = detailRoot.getViewTreeObserver();
        if (pendingProfileLayout != null && observer.isAlive()) {
            observer.removeOnPreDrawListener(pendingProfileLayout);
        }
        pendingProfileLayout = new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                ViewTreeObserver currentObserver = detailRoot.getViewTreeObserver();
                if (currentObserver.isAlive()) currentObserver.removeOnPreDrawListener(this);
                pendingProfileLayout = null;
                WindowInsetsCompat insets = ViewCompat.getRootWindowInsets(detailRoot);
                int statusBarInset = insets == null ? 0
                        : insets.getInsets(WindowInsetsCompat.Type.statusBars()).top;
                // Freeform windows may report a zero status-bar inset even though MIUI
                // reserves a caption area. Use the toolbar's rendered bottom edge so the
                // identity and expanded sheet remain below the real app bar.
                int toolbarBottom = Math.round(toolbar.getY() + toolbar.getHeight());
                if (toolbarBottom <= 0) toolbarBottom = statusBarInset + dp(64);
                sheetBehavior.setExpandedOffset(toolbarBottom);

                // Anchor the identity directly below the inset-aware 64dp app bar, then let
                // its measured content determine the rest of the hero on every screen size.
                expandedIdentity.setY(toolbarBottom);
                int identityBottom = toolbarBottom + expandedIdentity.getMeasuredHeight();
                int collapsedTop = identityBottom - dp(8);

                // Keep two 24dp sheet-corner radii of the dimmed header behind the sheet.
                // The sheet position stays content-driven; only its backdrop extends.
                int requiredHeaderHeight = collapsedTop + dp(48);
                ViewGroup.LayoutParams headerParams = appBar.getLayoutParams();
                if (headerParams.height != requiredHeaderHeight) {
                    headerParams.height = requiredHeaderHeight;
                    appBar.setLayoutParams(headerParams);
                }

                // Keep a shallow overlap for the floating rounded edge. CollapsingToolbarLayout
                // also offsets inset-aware children, so a larger overlap can cover the last
                // bio line on devices with tall status bars. Preserve a compact selector-sized
                // peek on short windows instead of covering the bio.
                int minimumPeek = dp(96);
                collapsedTop = Math.min(collapsedTop, detailRoot.getHeight() - minimumPeek);
                sheetBehavior.setPeekHeight(Math.max(minimumPeek,
                        detailRoot.getHeight() - collapsedTop), false);
                sheetBehavior.setState(targetState);
                detailSheet.requestLayout();
                // Cancel this frame so the resized header and positioned sheet are the
                // first version ever submitted to the display compositor.
                return false;
            }
        };
        observer.addOnPreDrawListener(pendingProfileLayout);
        detailRoot.invalidate();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        refreshWindowGeometry();
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode,
                                         @NonNull Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        refreshWindowGeometry();
    }

    private void refreshWindowGeometry() {
        if (detailRoot == null) return;
        ViewCompat.requestApplyInsets(detailRoot);
        detailRoot.requestLayout();
        scheduleProfileLayout(true);
        scheduleSettledWindowLayout();
    }

    @Override
    protected void onDestroy() {
        if (detailRoot != null) detailRoot.removeCallbacks(settledWindowLayout);
        super.onDestroy();
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    private void setupProfilePages() {
        MaterialButtonToggleGroup segments = findViewById(R.id.detail_segments);
        ViewPager2 pager = findViewById(R.id.detail_pager);
        FrameLayout pool = findViewById(R.id.detail_page_pool);
        pool.setVisibility(View.GONE);
        pager.setAdapter(new FragmentStateAdapter(this) {
            @NonNull @Override public Fragment createFragment(int position) {
                if (position == 0) return CloudListFragment.newInstance(
                        CloudListFragment.MODE_ACTIVITY, targetUid);
                if (position == 1) return CloudListFragment.newInstance(
                        CloudListFragment.MODE_USER_APPS, targetUid);
                return CloudListFragment.newInstance(CloudListFragment.MODE_USER_RESOURCES, targetUid);
            }
            @Override public int getItemCount() { return 3; }
        });
        pager.setOffscreenPageLimit(2);
        RecyclerView pagerRecycler = (RecyclerView) pager.getChildAt(0);
        ViewCompat.setNestedScrollingEnabled(pagerRecycler, false);
        int[] segmentIds = {R.id.detail_segment_activity, R.id.detail_segment_favorites,
                R.id.detail_segment_resources};
        segments.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (!isChecked) return;
            for (int i = 0; i < segmentIds.length; i++) {
                if (segmentIds[i] == checkedId && pager.getCurrentItem() != i) {
                    pager.setCurrentItem(i, true);
                    break;
                }
            }
        });
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override public void onPageSelected(int position) {
                if (position >= 0 && position < segmentIds.length
                        && segments.getCheckedButtonId() != segmentIds[position]) {
                    segments.check(segmentIds[position]);
                }
                findViewById(R.id.detail_sheet).requestLayout();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_detail, menu);
        toolbarMenu = menu;
        toolbar.post(this::applyAdaptiveChromeColors);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        if (item.getItemId() == R.id.action_profile_more) {
            if (!isSelf) {
                new WGProAlertDialogBuilder(this).setTitle("更多操作")
                        .setItems(new String[]{"分享主页", "举报用户"}, (dialog, which) -> {
                            if (which == 0) shareProfile();
                            else new WGProAlertDialogBuilder(this).setTitle("举报用户")
                                    .setMessage("请选择具体内容后再发起举报。")
                                    .setNegativeButton("关闭", null).show();
                        }).show();
                return true;
            }
            new WGProAlertDialogBuilder(this)
                    .setTitle("更多操作")
                    .setItems(new String[]{"编辑资料", "分享主页"}, (dialog, which) -> {
                        if (which == 0) {
                            startActivity(new Intent(this, AccMangerActivity.class)
                                    .putExtra("TARGET_FRAGMENT", "edit"));
                        } else {
                            shareProfile();
                        }
                    }).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void shareProfile() {
        Intent share = new Intent(Intent.ACTION_SEND).setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, "Typheye 用户 "
                        + ((TextView) findViewById(R.id.detail_nick)).getText() + "  UID " + targetUid);
        startActivity(Intent.createChooser(share, "分享主页"));
    }

    private void applyAdaptiveChromeColors() {
        // The bundled profile header is deliberately light. Keep app-bar controls and
        // system status-bar content on the same foreground instead of sampling different
        // points of the gradient and allowing them to disagree.
        int chromeColor = Color.WHITE;

        Drawable navigation = toolbar.getNavigationIcon();
        if (navigation != null) DrawableCompat.setTint(navigation.mutate(), chromeColor);
        ((TextView) findViewById(R.id.detail_collapsed_nick)).setTextColor(chromeColor);
        if (toolbarMenu != null) {
            MenuItem more = toolbarMenu.findItem(R.id.action_profile_more);
            if (more != null && more.getIcon() != null) {
                DrawableCompat.setTint(more.getIcon().mutate(), chromeColor);
            }
        }
        new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView())
                .setAppearanceLightStatusBars(false);
    }

    private static final class ProfilePageAdapter extends RecyclerView.Adapter<ProfilePageAdapter.Holder> {
        private final List<View> pages;

        ProfilePageAdapter(List<View> pages) { this.pages = pages; }

        @Override public int getItemCount() { return pages.size(); }

        @Override public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            FrameLayout frame = new FrameLayout(parent.getContext());
            frame.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            return new Holder(frame);
        }

        @Override public void onBindViewHolder(Holder holder, int position) {
            View page = pages.get(position);
            if (page.getParent() instanceof ViewGroup) ((ViewGroup) page.getParent()).removeView(page);
            holder.frame.removeAllViews();
            page.setVisibility(View.VISIBLE);
            holder.frame.addView(page, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }

        static final class Holder extends RecyclerView.ViewHolder {
            final FrameLayout frame;
            Holder(FrameLayout frame) { super(frame); this.frame = frame; }
        }
    }
}
