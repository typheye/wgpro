package com.typheye.wgpro.ui.function.account;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProBottomSheetDialog;
import com.typheye.wgpro.ui.widget.WGProProgressRunner;
import com.typheye.wgpro.ui.widget.BadgeFactory;
import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.utils.ImageCache;
import com.typheye.wgpro.utils.SystemBars;
import com.typheye.wgpro.utils.tAccUtils;
import com.typheye.wgpro.ui.function.community.CloudListFragment;
import com.typheye.wgpro.ui.function.community.DynamicCardFactory;
import com.typheye.wgpro.ui.function.community.CommunityWeb;

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
    /** Collapsed drawer baseline captured from the first normal full-screen layout. */
    private int fullScreenCollapsedTop = -1;
    /** 保存/恢复抽屉展开态（主题切换、多窗口重建、进程恢复）。 */
    private static final String STATE_SHEET_EXPANDED = "profile_sheet_expanded";
    private boolean pendingExpandSheet = false;
    private String targetUid;
    private boolean isSelf;
    private boolean following;
    private boolean canUnfollow = true;
    private MaterialButton followButton;
    private long profileLoadingStarted;
    private boolean profileLoaded;
    private boolean profileRequestActive;
    private boolean profileContentReady;
    private boolean profileLoadingFinished;
    private int profileAssetsPending;
    private JSONObject profileInfo;
    private TextView detailLikesCount;
    private TextView detailFollowingCount;
    private TextView detailFollowersCount;
    private ActivityResultLauncher<String> backgroundPicker;
    private com.typheye.wgpro.utils.BarBlurController segmentsBlurController;
    private final Runnable settledWindowLayout = () -> {
        if (detailRoot != null && detailRoot.isAttachedToWindow()) {
            scheduleProfileLayout(true);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        if (state != null) pendingExpandSheet = state.getBoolean(STATE_SHEET_EXPANDED, false);
        AppUtils.useScreenCutArea(getWindow(), this);
        // 头图是深色背景，状态栏用浅色图标。
        SystemBars.setLightSystemBars(getWindow(), false);
        setContentView(R.layout.activity_user_detail);
        // 顶部由沉浸式头图自行处理，安装全应用适配时不再兜底；底部保留系统导航栏高度，
        // 卡片背景继续铺满到屏幕底边（含手势小横条区域）。
        SystemBars.markImmersive(findViewById(R.id.detail_root));
        SystemBars.reserveBottomInset(findViewById(R.id.detail_sheet_content));
        profileLoadingStarted = android.os.SystemClock.uptimeMillis();
        account = new tAccUtils(this);
        detailLikesCount = findViewById(R.id.detail_likes_count);
        detailFollowingCount = findViewById(R.id.detail_following_count);
        detailFollowersCount = findViewById(R.id.detail_followers_count);
        backgroundPicker = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> { if (uri != null) uploadBackground(uri); });
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
                        .setPositiveButton("取消关注", (dialog, which) -> {
                            if (dialog instanceof WGProBottomSheetDialog) ((WGProBottomSheetDialog) dialog).dismissForReplacement();
                            followButton.postDelayed(this::changeFollowState, 40L);
                        }).show();
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
        if (profileRequestActive) return;
        profileRequestActive = true;
        account.getV2Json("user_profile2", java.util.Collections.singletonMap("target_uid", targetUid),
                false, new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        JSONObject info = json.optJSONObject("info");
                        runOnUiThread(() -> {
                            profileRequestActive = false;
                            if (isFinishing() || isDestroyed()) return;
                            if (info == null) { showProfileError("未找到该用户"); return; }
                            profileInfo = info;
                            bindProfileStats(info);
                            BadgeFactory.bindTransparentRing(UserDetailActivity.this, info,
                                    findViewById(R.id.detail_collapsed_avatar_box));
                            applyCollapsedAvatarCutout(collapsedAvatar);
                            bindProfileIdentity(info);
                            profileContentReady = true;
                            profileAssetsPending = 0;
                            String backgroundUrl = info.optString("background_url", "");
                            if (!backgroundUrl.isEmpty()) profileAssetsPending++;
                            showProfileBackground(backgroundUrl,
                                    UserDetailActivity.this::onProfileAssetLoaded);
                            String name = info.optString("nick", "用户");
                            String bio = info.optString("shuo",
                                    info.optString("bio", "")).trim();
                            ((TextView) findViewById(R.id.detail_nick)).setText(name);
                            ((TextView) findViewById(R.id.detail_collapsed_nick)).setText(name);
                            ((TextView) findViewById(R.id.detail_bio)).setText(bio.isEmpty()
                                    ? "这个人还没有简介呢~" : bio);
                            String initial = name.isEmpty() ? "U" : name.substring(0, 1).toUpperCase();
                            avatarText.setText(initial); collapsedAvatarText.setText(initial);
                            following = info.optBoolean("is_following", false);
                            if ("10000".equals(targetUid) && getSharedPreferences("local_follow_state", MODE_PRIVATE)
                                    .getBoolean("unfollow_10000", false)) following = false;
                            canUnfollow = info.optBoolean("can_unfollow", true);
                            updateFollowButton();
                            expandedIdentity.setVisibility(View.VISIBLE);
                            collapsedIdentity.setVisibility(View.VISIBLE);
                            // The identity block was invisible during the initial measure, so
                            // recalculate once its real height is known. preserveState=true keeps
                            // the drawer's current expanded/collapsed state on silent refreshes
                            // (onResume / onNewIntent / 下拉刷新) instead of snapping it shut.
                            // On the very first load the drawer is still collapsed, so the
                            // full-screen baseline is captured exactly as before.
                            detailRoot.post(() -> scheduleProfileLayout(true));
                            String avatarUrl = info.optString("avatar_url", "");
                            if (!avatarUrl.isEmpty()) {
                                profileAssetsPending++;
                                loadRemoteAvatar(avatarUrl, avatar, collapsedAvatar,
                                        avatarText, collapsedAvatarText,
                                        UserDetailActivity.this::onProfileAssetLoaded);
                            }
                            maybeFinishProfileLoading();
                            profileLoaded = true;
                        });
                    }
                    @Override public void onError(int code, @NonNull String message) {
                        runOnUiThread(() -> {
                            profileRequestActive = false;
                            if (!profileLoaded) { showProfileError(message); }
                        });
                    }
                });
    }

    private void showProfileError(String message) {
        if (isFinishing() || isDestroyed()) return;
        new WGProAlertDialogBuilder(this).setTitle("无法加载用户主页")
                .setMessage(message == null || message.trim().isEmpty() ? "用户不存在或暂无可加载数据" : message)
                .setCancelable(false)
                .setNegativeButton("关闭", (d,w) -> finish()).show();
    }

    @Override protected void onResume() {
        super.onResume();
        if (profileLoaded && !isFinishing()) {
            ImageView avatar = findViewById(R.id.detail_avatar), collapsed = findViewById(R.id.detail_collapsed_avatar);
            loadPublicProfile(avatar, collapsed, findViewById(R.id.detail_avatar_text), findViewById(R.id.detail_collapsed_avatar_text));
        }
    }

    @Override protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent == null) return;
        String requestedUid = intent.getStringExtra(EXTRA_TARGET_UID);
        String nextUid = requestedUid == null || requestedUid.trim().isEmpty()
                ? account.getUid() : requestedUid.trim();
        setIntent(intent);
        if (!nextUid.equals(targetUid)) {
            recreate();
            return;
        }
        ImageView avatar = findViewById(R.id.detail_avatar);
        ImageView collapsed = findViewById(R.id.detail_collapsed_avatar);
        loadPublicProfile(avatar, collapsed, findViewById(R.id.detail_avatar_text),
                findViewById(R.id.detail_collapsed_avatar_text));
    }

    public void refreshProfileFromTabs() {
        if (isFinishing() || isDestroyed() || profileRequestActive) return;
        ImageView avatar = findViewById(R.id.detail_avatar);
        ImageView collapsed = findViewById(R.id.detail_collapsed_avatar);
        loadPublicProfile(avatar, collapsed, findViewById(R.id.detail_avatar_text),
                findViewById(R.id.detail_collapsed_avatar_text));
    }

    private void bindProfileStats(@NonNull JSONObject info) {
        if (detailLikesCount == null || detailFollowingCount == null
                || detailFollowersCount == null) return;
        detailLikesCount.setText(String.valueOf(Math.max(0, info.optInt("received_like_count", 0))));
        detailFollowingCount.setText(String.valueOf(Math.max(0, info.optInt("following_count", 0))));
        detailFollowersCount.setText(String.valueOf(Math.max(0, info.optInt("follower_count", 0))));
    }

    private void bindProfileIdentity(@NonNull JSONObject info) {
        LinearLayout row = findViewById(R.id.detail_badge_row);
        LinearLayout icons = findViewById(R.id.detail_badge_icons);
        TextView text = findViewById(R.id.detail_badge_text);
        if (row == null || icons == null || text == null) return;
        if (!info.optString("badge_type", "").trim().isEmpty()) {
            BadgeFactory.bindProfileRow(this, info, icons, text, row, BadgeFactory.badgeColor(info));
            return;
        }
        icons.removeAllViews();
        ImageView icon = new ImageView(this);
        icon.setImageResource(R.drawable.ic_person_vector);
        icon.setImageTintList(ColorStateList.valueOf(Color.WHITE));
        icons.addView(icon, new LinearLayout.LayoutParams(dp(18), dp(18)));
        text.setText("UID " + info.optString("uid", targetUid));
        row.setVisibility(View.VISIBLE);
    }

    private void onProfileAssetLoaded() {
        if (profileAssetsPending > 0) profileAssetsPending--;
        maybeFinishProfileLoading();
    }

    private void maybeFinishProfileLoading() {
        if (profileContentReady && profileAssetsPending <= 0) finishProfileLoading();
    }

    private void finishProfileLoading() {
        if (profileLoadingFinished) return;
        profileLoadingFinished = true;
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
        followButton.setStrokeWidth(following ? dp(2) : 0);
        int messageButtonColor = Color.argb(0x40, 0xFF, 0xFF, 0xFF);
        followButton.setStrokeColor(ColorStateList.valueOf(messageButtonColor));
        followButton.setTextColor(Color.WHITE);
        followButton.setBackgroundTintList(ColorStateList.valueOf(
                following ? Color.TRANSPARENT : messageButtonColor));
        followButton.setElevation(0f);
        followButton.setEnabled(!following || canUnfollow || "10000".equals(targetUid));
    }

    private void changeFollowState() {
        if (targetUid == null || targetUid.isEmpty()) return;
        if (!account.isLogin()) {
            com.typheye.wgpro.ui.LoginGate.require(this, "关注用户");
            return;
        }
        followButton.setEnabled(false);
        final boolean wasFollowing = following;
        View progressView = View.inflate(this, R.layout.progress_dialog, null);
        ((TextView) progressView.findViewById(android.R.id.message)).setText(wasFollowing ? "正在取消关注..." : "正在关注...");
        WGProBottomSheetDialog progress = new WGProAlertDialogBuilder(this).setTitle("处理中")
                .setView(progressView).setCancelable(false).create();
        progress.show();
        long started = android.os.SystemClock.uptimeMillis();
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("target_uid", targetUid);
        fields.put("action", following ? "unfollow" : "follow");
        account.postV2Json("follow_action2", fields, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                runOnUiThread(() -> new android.os.Handler(getMainLooper()).postDelayed(() -> {
                    progress.dismissForReplacement();
                    following = json.optBoolean("following", !following);
                    if ("10000".equals(targetUid)) getSharedPreferences("local_follow_state", MODE_PRIVATE)
                            .edit().putBoolean("unfollow_10000", !following).apply();
                    canUnfollow = json.optBoolean("can_unfollow", true);
                    updateFollowButton();
                    new WGProAlertDialogBuilder(UserDetailActivity.this).setTitle("操作成功")
                            .setMessage(following ? "已关注该用户" : "已取消关注")
                            .setNegativeButton("关闭", null).show();
                }, Math.max(0, 300L - (android.os.SystemClock.uptimeMillis() - started))));
            }
            @Override public void onError(int code, @NonNull String message) {
                runOnUiThread(() -> new android.os.Handler(getMainLooper()).postDelayed(() -> {
                    progress.dismissForReplacement();
                    updateFollowButton();
                    new WGProAlertDialogBuilder(UserDetailActivity.this).setTitle("操作失败")
                            .setMessage(message).setNegativeButton("关闭", null).show();
                }, Math.max(0, 300L - (android.os.SystemClock.uptimeMillis() - started))));
            }
        });
    }

    private void loadRemoteAvatar(String url, ImageView avatar, ImageView collapsedAvatar,
                                  TextView avatarText, TextView collapsedAvatarText,
                                  @NonNull Runnable onComplete) {
        ImageCache.loadBitmap(this, url, bitmap -> {
            if (bitmap != null && !isFinishing() && !isDestroyed()) {
                avatar.setImageBitmap(bitmap);
                avatar.setVisibility(View.VISIBLE);
                avatarText.setVisibility(View.GONE);
                int collapsedSize = collapsedAvatar.getWidth() > 0
                        ? collapsedAvatar.getWidth() : dp(32);
                Bitmap collapsedBitmap = BadgeFactory.collapsedAvatar(
                        UserDetailActivity.this, bitmap, profileInfo, collapsedSize);
                collapsedAvatar.setImageBitmap(collapsedBitmap == null ? bitmap : collapsedBitmap);
                collapsedAvatar.setVisibility(View.VISIBLE);
                collapsedAvatarText.setVisibility(View.GONE);
            }
            onComplete.run();
        });
    }

    private void applyCollapsedAvatarCutout(ImageView collapsedAvatar) {
        if (collapsedAvatar == null || profileInfo == null) return;
        Drawable drawable = collapsedAvatar.getDrawable();
        if (!(drawable instanceof BitmapDrawable)) return;
        Bitmap source = ((BitmapDrawable) drawable).getBitmap();
        int size = collapsedAvatar.getWidth() > 0 ? collapsedAvatar.getWidth() : dp(32);
        Bitmap cutout = BadgeFactory.collapsedAvatar(this, source, profileInfo, size);
        if (cutout != null && cutout != source) collapsedAvatar.setImageBitmap(cutout);
    }

    private void setupProfileSheet() {
        detailRoot = findViewById(R.id.detail_root);
        detailSheet = findViewById(R.id.detail_sheet);
        expandedIdentity = findViewById(R.id.detail_expanded_identity);
        collapsedIdentity = findViewById(R.id.detail_collapsed_identity);
        sheetBehavior = BottomSheetBehavior.from(detailSheet);
        sheetBehavior.setFitToContents(false);
        sheetBehavior.setHideable(false);
        // 默认手势/UX：抽屉可自由拖拽展开收起；顶部小横条额外支持点击切换。
        // 拖拽只允许从顶部条带（小横条/标签栏）开始，列表区域的触摸全部交给列表，
        // 避免与列表滚动、下拉刷新抢手势。
        sheetBehavior.setDraggable(true);
        sheetBehavior.setShouldRemoveExpandedCorners(false);
        if (sheetBehavior instanceof com.typheye.wgpro.ui.widget.ProfileBottomSheetBehavior) {
            ((com.typheye.wgpro.ui.widget.ProfileBottomSheetBehavior<?>) sheetBehavior)
                    .setDragZoneAnchor(findViewById(R.id.detail_tabs));
        }
        View sheetHandle = findViewById(R.id.detail_sheet_handle);
        if (sheetHandle != null) sheetHandle.setOnClickListener(v -> toggleProfileSheetState());
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
            applyProfilePagerInsets();
        });
    }

    /** 系统导航栏高度（拿不到时按 48dp 估算）。 */
    private int navigationBarInset() {
        WindowInsetsCompat insets = detailRoot == null ? null
                : ViewCompat.getRootWindowInsets(detailRoot);
        if (insets != null) {
            int inset = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
            if (inset > 0) return inset;
        }
        return dp(48);
    }

    /** 窗口可用高度：优先用 WindowMetrics（窗口真实边界，含系统栏区域）。 */
    private int windowHeight() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            try {
                return getWindowManager().getCurrentWindowMetrics().getBounds().height();
            } catch (Exception ignored) { }
        }
        android.view.Window window = getWindow();
        View decor = window == null ? null : window.getDecorView();
        if (decor != null && decor.getHeight() > 0) return decor.getHeight();
        return detailRoot == null ? 0 : detailRoot.getHeight();
    }

    private void scheduleSettledWindowLayout() {
        detailRoot.removeCallbacks(settledWindowLayout);
        detailRoot.postDelayed(settledWindowLayout, 350L);
    }

    /** 顶部小短横条：点击折叠 / 展开（抽屉已关闭拖拽展开，避免滑列表被当成拖抽屉）。 */
    private void toggleProfileSheetState() {
        if (sheetBehavior == null) return;
        sheetBehavior.setState(sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED
                ? BottomSheetBehavior.STATE_COLLAPSED
                : BottomSheetBehavior.STATE_EXPANDED);
    }

    private void scheduleProfileLayout(boolean preserveState) {
        int currentState = sheetBehavior.getState();
        if (preserveState && (currentState == BottomSheetBehavior.STATE_DRAGGING
                || currentState == BottomSheetBehavior.STATE_SETTLING)) {
            scheduleSettledWindowLayout();
            return;
        }
        int targetState = pendingExpandSheet
                ? BottomSheetBehavior.STATE_EXPANDED
                : (preserveState && currentState == BottomSheetBehavior.STATE_EXPANDED
                        ? BottomSheetBehavior.STATE_EXPANDED
                        : BottomSheetBehavior.STATE_COLLAPSED);
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
                // Keep the sheet just below the identity block.  The previous overlap
                // pulled the default drawer too far upward in compact/freeform windows.
                int collapsedTop = identityBottom + dp(16);
                boolean freeform = isInMultiWindowMode();
                if (!freeform && fullScreenCollapsedTop > 0) {
                    // Re-entering full-screen after freeform must return to the same
                    // visual baseline as a freshly opened profile.
                    collapsedTop = fullScreenCollapsedTop;
                } else if (!freeform && expandedIdentity.getVisibility() == View.VISIBLE
                        && expandedIdentity.getMeasuredHeight() > 0) {
                    fullScreenCollapsedTop = collapsedTop;
                }

                // Keep two 24dp sheet-corner radii of the dimmed header behind the sheet.
                // The sheet position stays content-driven; only its backdrop extends.
                int requiredHeaderHeight = collapsedTop + dp(48);
                ViewGroup.LayoutParams headerParams = appBar.getLayoutParams();
                if (headerParams.height != requiredHeaderHeight) {
                    headerParams.height = requiredHeaderHeight;
                    appBar.setLayoutParams(headerParams);
                }
                resizeProfileBackground(requiredHeaderHeight);

                // 抽屉展开时容器高度 = 窗口高度 - 顶部偏移。用 match_parent 的话，
                // 内容底部会被顶到屏幕外面，列表永远滚不到底（最后一张卡片看不全）。
                // 高度以 WindowMetrics（窗口真实边界）为准，并额外加一段系统栏高度的余量，
                // 保证抽屉底边一定盖过系统手势条（多出来的部分在屏幕外，不影响观感）。
                int windowHeight = windowHeight();
                int sheetHeight = Math.max(0, windowHeight - toolbarBottom)
                        + navigationBarInset();
                ViewGroup.LayoutParams sheetParams = detailSheet.getLayoutParams();
                if (sheetHeight > 0 && sheetParams.height != sheetHeight) {
                    sheetParams.height = sheetHeight;
                    detailSheet.setLayoutParams(sheetParams);
                }

                // Keep a shallow overlap for the floating rounded edge. CollapsingToolbarLayout
                // also offsets inset-aware children, so a larger overlap can cover the last
                // bio line on devices with tall status bars. Preserve a compact selector-sized
                // peek on short windows instead of covering the bio.
                int minimumPeek = dp(96);
                collapsedTop = Math.min(collapsedTop, windowHeight - minimumPeek);
                sheetBehavior.setPeekHeight(Math.max(minimumPeek,
                        windowHeight - collapsedTop), false);
                sheetBehavior.setState(targetState);
                pendingExpandSheet = false;
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

    /**
     * 是否是分屏（贴边分配）模式。
     *
     * <p>SDK 没有公开的 windowingMode 查询接口（{@code WindowConfiguration} 是 @hide），
     * 所以用窗口几何判断：分屏窗口会占满屏幕的整宽（上下分屏）或整高（左右分屏），
     * 而 MIUI 小窗是悬浮窗、四周始终留有空隙，不会被误判。
     */
    private boolean isInSplitScreenWindow() {
        if (!isInMultiWindowMode()) return false;
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.R) {
            // 旧版本没有窗口几何接口，用可用高度兜底。
            return getResources().getConfiguration().screenHeightDp < 480;
        }
        android.view.WindowManager windowManager = getWindowManager();
        android.graphics.Rect window =
                windowManager.getCurrentWindowMetrics().getBounds();
        android.graphics.Rect screen =
                windowManager.getMaximumWindowMetrics().getBounds();
        int tolerance = dp(8);
        boolean spansWidth = window.width() >= screen.width() - tolerance;
        boolean spansHeight = window.height() >= screen.height() - tolerance;
        return (spansWidth && window.height() < screen.height() - tolerance)
                || (spansHeight && window.width() < screen.width() - tolerance);
    }

    private void refreshWindowGeometry() {
        if (detailRoot == null) return;
        ViewCompat.requestApplyInsets(detailRoot);
        detailRoot.requestLayout();
        scheduleProfileLayout(true);
        scheduleSettledWindowLayout();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SHEET_EXPANDED,
                sheetBehavior != null && sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED);
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
        com.google.android.material.tabs.TabLayout tabs = findViewById(R.id.detail_tabs);
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
        // 保留 ViewPager2 内部 RecyclerView 的嵌套滚动：分页里的列表滚动到底/到顶时，
        // 未消费的滚动才能沿着链路交给抽屉（BottomSheetBehavior）决定是否跟手——
        // 之前关掉它导致"列表和抽屉抢手势"（抽屉分不清列表还能不能滚）。
        RecyclerView pagerRecycler = (RecyclerView) pager.getChildAt(0);
        ViewCompat.setNestedScrollingEnabled(pagerRecycler, true);
        // 与主页/浏览历史一致的下划线标签（铺满、下划线指示器、坐在毛玻璃上）
        String[] titles = {"主页", "应用", "资源"};
        new com.google.android.material.tabs.TabLayoutMediator(tabs, pager,
                (tab, position) -> tab.setText(titles[position])).attach();
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override public void onPageSelected(int position) {
                findViewById(R.id.detail_sheet).requestLayout();
                applyProfilePagerInsets();
            }
        });
        setupProfileSegmentsBlur();
        pager.post(this::applyProfilePagerInsets);
    }

    /** 滚动指示器的毛玻璃：pager 作为快照源，指示器那一条作为快照层（与固定栏同款）。 */
    private void setupProfileSegmentsBlur() {
        ImageView backdrop = findViewById(R.id.detail_segments_blur);
        View tint = findViewById(R.id.detail_segments_tint);
        if (tint != null) com.typheye.wgpro.utils.BarBlurController.registerBar(this, tint);
        if (backdrop == null) return;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            segmentsBlurController = com.typheye.wgpro.utils.BarBlurController.install(
                    this, findViewById(R.id.detail_pager), backdrop);
        }
    }

    /**
     * 内容顶部留白让开滚动指示器（内容从毛玻璃条下面穿过，模糊才有东西可糊）；
     * 毛玻璃条的底边与指示器底边（下划线）齐平。
     */
    private void applyProfilePagerInsets() {
        ViewPager2 pager = findViewById(R.id.detail_pager);
        View tabs = findViewById(R.id.detail_tabs);
        ImageView backdrop = findViewById(R.id.detail_segments_blur);
        if (pager == null || tabs == null || !(pager.getChildAt(0) instanceof ViewGroup)) return;
        int band = tabs.getBottom();
        if (band <= 0) return;
        for (int id : new int[]{R.id.detail_segments_blur, R.id.detail_segments_tint}) {
            View bar = findViewById(id);
            if (bar == null || bar.getLayoutParams() == null) continue;
            ViewGroup.LayoutParams params = bar.getLayoutParams();
            if (params.height != band) {
                params.height = band;
                bar.setLayoutParams(params);
            }
        }
        ViewGroup holder = (ViewGroup) pager.getChildAt(0);
        for (int index = 0; index < holder.getChildCount(); index++) {
            applyProfilePageInsets(holder.getChildAt(index), band + dp(6));
        }
    }

    private void applyProfilePageInsets(View view, int top) {
        // 平铺（全屏）时底部完全沉浸、不留导航栏内边距；
        // 小窗（freeform）窗口底部有 MIUI 自己的把手，必须垫高，否则列表末尾压在把手上。
        int bottom = 0;
        if (detailRoot != null && com.typheye.wgpro.utils.SystemBars.isInMultiWindow(detailRoot)) {
            WindowInsetsCompat insets = ViewCompat.getRootWindowInsets(detailRoot);
            if (insets != null) {
                bottom = com.typheye.wgpro.utils.SystemBars
                        .bottomInsetForView(detailRoot, insets);
            }
        }
        applyProfilePageInsets(view, top, bottom);
    }

    private void applyProfilePageInsets(View view, int top, int bottom) {
        if (view instanceof androidx.swiperefreshlayout.widget.SwipeRefreshLayout) {
            com.typheye.wgpro.utils.AppBarBlur.offsetRefreshIndicator(
                    (androidx.swiperefreshlayout.widget.SwipeRefreshLayout) view, top);
        }
        if (view instanceof androidx.core.widget.NestedScrollView) {
            androidx.core.widget.NestedScrollView scroll =
                    (androidx.core.widget.NestedScrollView) view;
            scroll.setClipToPadding(false);
            scroll.setPadding(scroll.getPaddingLeft(), top, scroll.getPaddingRight(), bottom);
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int index = 0; index < group.getChildCount(); index++) {
                applyProfilePageInsets(group.getChildAt(index), top, bottom);
            }
        }
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
            String nick = ((TextView) findViewById(R.id.detail_nick)).getText().toString();
            if (isSelf) {
                new WGProAlertDialogBuilder(this).setTitle("更多操作")
                        .setItems(new String[]{"编辑资料", "更换背景图", "更多信息"},
                                (dialog, which) -> {
                                    if (which == 0) {
                                        startActivity(new Intent(this, AccMangerActivity.class)
                                                .putExtra("TARGET_FRAGMENT", "edit"));
                                    } else if (which == 1) {
                                        showBackgroundSheet();
                                    } else {
                                        showMoreInfo();
                                    }
                                }).show();
            } else {
                new WGProAlertDialogBuilder(this).setTitle("更多操作")
                        .setItems(new String[]{"举报该用户", "更多信息"},
                                (dialog, which) -> {
                                    if (which == 0) {
                                        CommunityWeb.openReport(this, "user", targetUid, nick);
                                    } else {
                                        showMoreInfo();
                                    }
                                }).show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showMoreInfo() {
        if (profileInfo == null) {
            showResult("更多信息", "资料还在加载中，请稍后重试。");
            return;
        }
        StringBuilder info = new StringBuilder();
        appendInfo(info, "UID", profileInfo.optString("uid", targetUid));
        appendInfo(info, "昵称", profileInfo.optString("nick", ""));
        appendInfo(info, "简介", profileInfo.optString("shuo",
                profileInfo.optString("bio", "")));
        appendInfo(info, "角色", profileInfo.optString("role_name", ""));
        String badgeType = profileInfo.optString("badge_type", "").trim();
        if (!badgeType.isEmpty()) {
            String verification = profileInfo.optString("verification_description", "").trim();
            if (verification.isEmpty()) {
                if ("developer".equalsIgnoreCase(badgeType)) verification = "开发者认证";
                else if ("creator".equalsIgnoreCase(badgeType)) verification = "创作者认证";
                else if ("honor".equalsIgnoreCase(badgeType)) verification = "荣誉认证";
            }
            appendInfo(info, "认证", verification);
        }
        appendInfo(info, "创作者",
                profileInfo.optBoolean("creator_enabled", false) ? "已开启" : "未开启");
        appendInfo(info, "动态", String.valueOf(Math.max(0, profileInfo.optInt("dynamic_count", 0))));
        appendInfo(info, "关注", String.valueOf(Math.max(0, profileInfo.optInt("following_count", 0))));
        appendInfo(info, "粉丝", String.valueOf(Math.max(0, profileInfo.optInt("follower_count", 0))));
        appendInfo(info, "背景图",
                profileInfo.optString("background_url", "").isEmpty() ? "未设置" : "已设置");
        new WGProAlertDialogBuilder(this).setTitle("更多信息")
                .setMessage(info.length() == 0 ? "暂无更多公开信息。" : info.toString())
                .setNegativeButton("关闭", null).show();
    }

    private static void appendInfo(StringBuilder target, String label, String value) {
        if (value == null || value.trim().isEmpty()) return;
        if (target.length() > 0) target.append('\n');
        target.append(label).append("：").append(value.trim());
    }

    private void showBackgroundSheet() {
        new WGProAlertDialogBuilder(this).setTitle("更换背景图")
                .setItems(new String[]{"从相册选择", "移除当前背景"}, (dialog, which) -> {
                    if (which == 0) backgroundPicker.launch("image/*");
                    else confirmRemoveBackground();
                }).show();
    }

    private void confirmRemoveBackground() {
        new WGProAlertDialogBuilder(this).setTitle("移除背景图？")
                .setMessage("移除后将恢复默认背景。")
                .setNegativeButton("取消", null)
                .setPositiveButton("移除", (dialog, which) -> removeBackground())
                .show();
    }

    private void uploadBackground(Uri uri) {
        WGProProgressRunner.run(this, "上传中", "正在上传背景图...", completion ->
                account.uploadUserBackground(uri, new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        completion.success(json);
                    }

                    @Override public void onError(int code, @NonNull String message) {
                        completion.error(code, message);
                    }
                }), new WGProProgressRunner.Callback() {
            @Override public void success(@NonNull JSONObject json) {
                String url = json.optString("url", "");
                try {
                    if (profileInfo != null) profileInfo.put("background_url", url);
                } catch (Exception ignored) { }
                showProfileBackground(url, () -> { });
                showResult("背景已更新", "新的背景图已生效。");
            }

            @Override public void error(int code, @NonNull String message) {
                showResult("上传失败", message);
            }
        });
    }

    private void removeBackground() {
        WGProProgressRunner.run(this, "处理中", "正在移除背景图...", completion ->
                account.postV2Json("user_background_delete2", new LinkedHashMap<>(),
                        new tAccUtils.JsonCallback() {
                            @Override public void onSuccess(@NonNull JSONObject json) {
                                completion.success(json);
                            }

                            @Override public void onError(int code, @NonNull String message) {
                                completion.error(code, message);
                            }
                        }), new WGProProgressRunner.Callback() {
            @Override public void success(@NonNull JSONObject json) {
                try {
                    if (profileInfo != null) profileInfo.put("background_url", "");
                } catch (Exception ignored) { }
                showProfileBackground("", () -> { });
                showResult("已移除", "背景图已移除。");
            }

            @Override public void error(int code, @NonNull String message) {
                showResult("移除失败", message);
            }
        });
    }

    private void showProfileBackground(String url, @NonNull Runnable onComplete) {
        ImageView background = findViewById(R.id.detail_background);
        View dim = findViewById(R.id.detail_background_dim);
        if (background == null || dim == null) {
            onComplete.run();
            return;
        }
        if (url == null || url.trim().isEmpty()) {
            background.setVisibility(View.GONE);
            dim.setVisibility(View.GONE);
            onComplete.run();
            return;
        }
        background.setVisibility(View.VISIBLE);
        dim.setVisibility(View.VISIBLE);
        resizeProfileBackground(appBar.getHeight() > 0 ? appBar.getHeight() : dp(410));
        ImageCache.loadBitmap(this, url.trim(), bitmap -> {
            if (bitmap != null && !isFinishing() && !isDestroyed()) {
                background.setImageBitmap(bitmap);
            }
            onComplete.run();
        });
    }

    private void resizeProfileBackground(int headerHeight) {
        ImageView background = findViewById(R.id.detail_background);
        View dim = findViewById(R.id.detail_background_dim);
        if (background == null || dim == null) return;
        WindowInsetsCompat insets = ViewCompat.getRootWindowInsets(detailRoot);
        int insetTop = insets == null ? 0
                : insets.getInsets(WindowInsetsCompat.Type.statusBars()).top;
        // MIUI freeform windows can report a zero status-bar inset while still
        // reserving a caption/status area above the app content.  Extend the
        // bitmap by the platform status-bar size (and a slightly larger caption
        // allowance in multi-window mode) so custom backgrounds reach the top.
        int resourceTop = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId != 0) resourceTop = getResources().getDimensionPixelSize(resourceId);
        boolean freeform = isInMultiWindowMode();
        int windowTop = freeform ? dp(32) : resourceTop;
        int top = Math.max(insetTop, windowTop);
        // The freeform caption can sit above the content origin even when insets
        // report zero. Move the bitmap itself upward so it covers that strip.
        int extraOffset = freeform ? dp(24) : 0;
        int height = headerHeight + top + extraOffset;
        ViewGroup.LayoutParams bgParams = background.getLayoutParams();
        bgParams.height = height;
        background.setLayoutParams(bgParams);
        background.setTranslationY(-(top + extraOffset));
        ViewGroup.LayoutParams dimParams = dim.getLayoutParams();
        dimParams.height = height;
        dim.setLayoutParams(dimParams);
        dim.setTranslationY(-(top + extraOffset));
    }

    private void showResult(String title, String message) {
        new WGProAlertDialogBuilder(this).setTitle(title)
                .setMessage(message == null || message.trim().isEmpty() ? "操作完成" : message)
                .setNegativeButton("关闭", null).show();
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
        SystemBars.setLightSystemBars(getWindow(), false);
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
