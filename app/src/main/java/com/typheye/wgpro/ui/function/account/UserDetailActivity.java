package com.typheye.wgpro.ui.function.account;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
    private View expandedIdentity;
    private View collapsedIdentity;
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
    /** 应用栏当前是否处于折叠配色（避免每帧重复换背景）。 */
    private boolean barCollapsed;
    /** 当前主题是否深色 + 主题的 colorOnSurface（折叠后的应用栏前景/系统栏图标都用它）。 */
    private boolean chromeNight;
    private int chromeOnSurface;
    /** 上一次已应用的应用栏前景色（用它当开关，避免"昵称本来就同色"时漏掉图标换色）。 */
    private int chromeForeground = Integer.MIN_VALUE;

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        AppUtils.useScreenCutArea(getWindow(), this);
        // 头图是深色背景，状态栏用浅色图标。
        SystemBars.setLightSystemBars(getWindow(), false);
        setContentView(R.layout.activity_user_detail);
        detailRoot = findViewById(R.id.detail_root);
        // 顶部由沉浸式头图自行处理，安装全应用适配时不再兜底；底部保留系统导航栏高度，
        // 卡片背景继续铺满到屏幕底边（含手势小横条区域）。
        SystemBars.markImmersive(findViewById(R.id.detail_root));
        // 底部像浏览器页那样"主动垫高布局"：给 pager 加导航栏高度的底部外边距 + 用页面表面色填那一条，
        // 而不是给内容加底部内边距（那会在底部留出一块空白）。
        com.typheye.wgpro.utils.AppBarBlur.installNavBarSpacer(this,
                getColor(R.color.profile_detail_sheet), findViewById(R.id.detail_pager));
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

        // 折叠联动（原来由自定义 sheet 的手写布局负责，现在交给 AppBarLayout 偏移）：
        // 头图内容淡出、标题栏的「小头像 + 昵称」淡入；折叠后应用栏变成主题底色，
        // 于是图标/文字与系统栏图标明暗都要跟着切（否则浅色底上是白字/白图标）。
        expandedIdentity = findViewById(R.id.detail_expanded_identity);
        collapsedIdentity = findViewById(R.id.detail_collapsed_identity);
        chromeNight = (getResources().getConfiguration().uiMode
                & android.content.res.Configuration.UI_MODE_NIGHT_MASK)
                == android.content.res.Configuration.UI_MODE_NIGHT_YES;
        android.util.TypedValue themeValue = new android.util.TypedValue();
        boolean hasOnSurface = getTheme().resolveAttribute(
                com.google.android.material.R.attr.colorOnSurface, themeValue, true);
        chromeOnSurface = hasOnSurface
                ? (themeValue.resourceId != 0 ? getColor(themeValue.resourceId) : themeValue.data)
                : getColor(R.color.text_primary);
        appBar.addOnOffsetChangedListener((bar, verticalOffset) -> {
            int range = bar.getTotalScrollRange();
            float progress = range <= 0 ? 0f
                    : Math.min(1f, Math.abs(verticalOffset) / (float) range);
            float appear = progress <= 0.65f ? 0f : (progress - 0.65f) / 0.35f;
            if (collapsedIdentity != null) {
                collapsedIdentity.setAlpha(appear);
                collapsedIdentity.setVisibility(appear <= 0.01f ? View.INVISIBLE : View.VISIBLE);
            }
            if (expandedIdentity != null) {
                expandedIdentity.setAlpha(1f - Math.min(1f, progress / 0.65f));
            }
            // 背景交给随进度淡入的蒙层（连续过渡）；前景在蒙层基本不透明时再切，避免中途半深半浅
            View barScrim = findViewById(R.id.detail_bar_scrim);
            if (barScrim != null) {
                barScrim.setAlpha(progress);
            }
            applyBarChrome(progress > 0.7f);
        });
        // 底部是页面表面色（浅色主题=浅、深色主题=深）→ 导航栏图标跟随主题；
        // 状态栏展开时压在深色头图上→用浅色图标（由 applyBarChrome 在折叠时改回主题色）。
        com.typheye.wgpro.utils.SystemBars.setLightNavigationBar(getWindow(), !chromeNight);

        // 不用 fitsSystemWindows：它会给容器加顶部内边距，导致"头图"（含用户自定义背景图）只从
        // 状态栏下方开始，与容器自身背景在状态栏那条交界处形成一道缝（小窗里尤其明显），
        // 而且 match_parent 的子视图会被顶到容器外。改成自己把状态栏高度补给头图内容与 Toolbar，
        // 这样 detail_background 能从 y=0 铺满整块头图。
        final android.view.View collapsingBar = findViewById(R.id.detail_collapsing_toolbar);
        final android.view.View expandedIdentity = findViewById(R.id.detail_expanded_identity);
        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(appBar, (view, insets) -> {
            int top = insets.getInsets(
                    androidx.core.view.WindowInsetsCompat.Type.statusBars()).top;
            // 两级兜底：个别窗口/composable 场景下直接派发给 AppBar 的 insets 里状态栏是 0。
            if (top == 0 && detailRoot != null) {
                androidx.core.view.WindowInsetsCompat rootInsets =
                        androidx.core.view.ViewCompat.getRootWindowInsets(detailRoot);
                if (rootInsets != null) {
                    top = rootInsets.getInsets(
                            androidx.core.view.WindowInsetsCompat.Type.statusBars()).top;
                }
            }
            if (top == 0) {
                int statusBarId = getResources()
                        .getIdentifier("status_bar_height", "dimen", "android");
                if (statusBarId != 0) {
                    top = getResources().getDimensionPixelSize(statusBarId);
                }
            }
            // 容器高度必须把这一个状态栏高度补上：去掉 fitsSystemWindows 后容器不再自己预留，
            // 只给内容加 paddingTop 会把内容顶出 360dp 被裁（表现就是"布局变形"）。
            if (collapsingBar != null) {
                android.view.ViewGroup.LayoutParams barParams = collapsingBar.getLayoutParams();
                int wantedHeight = dp(360) + top;
                if (barParams.height != wantedHeight) {
                    barParams.height = wantedHeight;
                    collapsingBar.setLayoutParams(barParams);
                }
            }
            if (expandedIdentity != null) {
                expandedIdentity.setPadding(expandedIdentity.getPaddingLeft(), dp(56) + top,
                        expandedIdentity.getPaddingRight(), expandedIdentity.getPaddingBottom());
            }
            if (toolbar != null) {
                android.view.ViewGroup.LayoutParams lp = toolbar.getLayoutParams();
                if (lp instanceof android.view.ViewGroup.MarginLayoutParams) {
                    android.view.ViewGroup.MarginLayoutParams mlp =
                            (android.view.ViewGroup.MarginLayoutParams) lp;
                    if (mlp.topMargin != top) {
                        mlp.topMargin = top;
                        toolbar.setLayoutParams(mlp);
                    }
                }
            }
            return insets;
        });
        androidx.core.view.ViewCompat.requestApplyInsets(appBar);

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

        setupProfilePages();
        loadPublicProfile(avatar, collapsedAvatar, avatarText, collapsedAvatarText);
        appBar.post(this::applyAdaptiveChromeColors);
    }

    /** 折叠状态下的应用栏/系统栏配色：折叠后应用栏是主题底色，前景与系统栏图标都要跟着切。 */
    private void applyBarChrome(boolean collapsed) {
        // 折叠后改用主题底色（浅色主题=白 / 深色主题=黑），展开时恢复头图背景。
        // 这里直接换 AppBarLayout 的背景：CollapsingToolbarLayout 的 contentScrim 在本布局里
        // 会被头图背景盖住、不生效（试过）。
        // 折叠后固定用主题底色（浅色=白 / 深色=黑），和页面指示器（Tab 行）与底部导航栏区域保持一致。
        // 注意：底色**不再直接换 CollapsingToolbarLayout 的背景**（那样会在某个阈值上跳变），
        // 而是由 layout 里的 detail_bar_scrim 随折叠进度淡入 —— 背景是连续过渡的。
        // 这里只负责"前景（昵称/返回箭头/更多图标）+ 系统栏图标"跟着切。
        boolean themed = collapsed;
        int foreground = themed ? chromeOnSurface : getColor(R.color.white);
        // 用自己记录的"上次生效前景"当开关，而不是拿昵称当前颜色判断：
        // 展开态下昵称在 XML 里本来就是白色、foreground 也是白色，用昵称判断会导致
        // **从来没调用过 tintToolbarIcons** → 图标保留 vector 里烘死的
        // android:fillColor="?attr/colorOnSurface"（浅色主题=深色）→ 深色头图上是灰的。
        // MIUI 会额外给应用栏图标叠一层 tint，所以只有 Pixel/AOSP 这类设备会暴露出来。
        if (chromeForeground != foreground) {
            chromeForeground = foreground;
            TextView nick = findViewById(R.id.detail_collapsed_nick);
            if (nick != null) {
                nick.setTextColor(foreground);
            }
            tintToolbarIcons(foreground);
        }
        // 状态栏：展开时压在深色头图上→浅色图标；折叠成主题底色→跟随主题；小窗→仍按头图（浅色）
        SystemBars.setLightSystemBars(getWindow(), themed ? !chromeNight : false);
        // 底部：底色是页面表面色 → 始终跟随主题（浅色主题=深色图标），别再被别处刷成白色
        SystemBars.setLightNavigationBar(getWindow(), !chromeNight);
    }

    /**
     * 返回箭头 + 右上角「更多」图标一起换色。
     *
     * <p>用官方 tint API（appcompat 1.7 的 {@code Toolbar#setNavigationIconTint} 与
     * {@code MenuItemCompat#setIconTintList}），不要用 {@code DrawableCompat.setTint(drawable)}：
     * 部分 ROM 上 VectorDrawable 自带的 tint、或主题里的 {@code colorControlNormal} 会把它盖掉，
     * 表现就是"部分设备上图标颜色不跟着变"。</p>
     */
    private void tintToolbarIcons(int color) {
        // 从资源**新建**一个 drawable 再上色：不 mutate 当前那个（它可能已被 ROM/主题预置 tint，
        // mutate 后再 setTint 在部分设备上会被盖掉），换上去的是我们完全掌控的实例。
        android.graphics.drawable.Drawable navigation =
                androidx.appcompat.content.res.AppCompatResources
                        .getDrawable(this, R.drawable.ic_back_vector);
        if (navigation != null) {
            navigation = androidx.core.graphics.drawable.DrawableCompat.wrap(navigation).mutate();
            androidx.core.graphics.drawable.DrawableCompat.setTint(navigation, color);
            toolbar.setNavigationIcon(navigation);
        }
        if (toolbarMenu != null) {
            android.view.MenuItem more = toolbarMenu.findItem(R.id.action_profile_more);
            if (more != null) {
                androidx.core.view.MenuItemCompat.setIconTintList(
                        more, android.content.res.ColorStateList.valueOf(color));
            }
        }
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
                            String avatarUrl = info.optString("avatar_url", "");
                            avatar.setClickable(!avatarUrl.isEmpty());
                            avatar.setOnClickListener(v -> {
                                if (!avatarUrl.isEmpty()) {
                                    com.typheye.wgpro.ui.function.PreviewActivity
                                            .open(UserDetailActivity.this, avatarUrl);
                                }
                            });
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


    /**
     * 系统导航栏真实高度：没有导航条时返回 0（**不要**用 48dp 之类的估算值）。
     * 只有真实存在导航条时才让抽屉底部外扩去盖住它，否则会多出屏幕外的可滚动区域。
     */
    private int navigationBarInset() {
        WindowInsetsCompat insets = detailRoot == null ? null
                : ViewCompat.getRootWindowInsets(detailRoot);
        if (insets != null) {
            int inset = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
            if (inset > 0) return inset;
        }
        return 0;
    }

    /**
     * 按 AppBar 当前偏移重算「折叠蒙层 alpha + 前景配色」。
     *
     * <p>窗口尺寸变化（进/出小窗、分屏、旋转）后 {@code onOffsetChanged} 不一定再触发，
     * 若不重算，蒙层会停在旧 alpha（表现为顶部一条淡线）或前景停在旧颜色。
     * AppBarLayout 的滚动偏移 = 被折叠子视图（CollapsingToolbarLayout）的 top。</p>
     */
    private void reapplyChromeFromCurrentOffset() {
        View collapsing = findViewById(R.id.detail_collapsing_toolbar);
        View scrim = findViewById(R.id.detail_bar_scrim);
        int range = appBar == null ? 0 : appBar.getTotalScrollRange();
        int offset = collapsing == null ? 0 : collapsing.getTop();
        float progress = (range <= 0 || offset == 0)
                ? 0f : Math.min(1f, Math.abs(offset) / (float) range);
        if (scrim != null) {
            scrim.setAlpha(progress);
        }
        applyBarChrome(progress > 0.7f);
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


    /** 顶部小短横条：点击折叠 / 展开（抽屉已关闭拖拽展开，避免滑列表被当成拖抽屉）。 */


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // 小窗/分屏/旋转会重排 AppBar，但偏移监听不一定再触发 → 主动按当前偏移重算一次，
        // 否则会留下陈旧的蒙层 alpha / 前景色（表现：刚进页面立刻转小窗，顶部多一条淡线）。
        reapplyChromeFromCurrentOffset();
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode,
                                         @NonNull Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        reapplyChromeFromCurrentOffset();
    }


    @Override
    protected void onDestroy() {
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
        // 保留 ViewPager2 内部 RecyclerView 的嵌套滚动：分页里的列表滚到底/到顶后，
        // 未消费的滚动才能沿链路交给 AppBarLayout，让应用栏跟着折叠。
        RecyclerView pagerRecycler = (RecyclerView) pager.getChildAt(0);
        ViewCompat.setNestedScrollingEnabled(pagerRecycler, true);
        // 与主页/浏览历史一致的下划线标签（铺满、下划线指示器、坐在毛玻璃上）
        String[] titles = {"主页", "应用", "资源"};
        new com.google.android.material.tabs.TabLayoutMediator(tabs, pager,
                (tab, position) -> tab.setText(titles[position])).attach();
    }

    /** 滚动指示器的毛玻璃：pager 作为快照源，指示器那一条作为快照层（与固定栏同款）。 */

    /**
     * 内容顶部留白让开滚动指示器（内容从毛玻璃条下面穿过，模糊才有东西可糊）；
     * 毛玻璃条的底边与指示器底边（下划线）齐平。
     */


    private void applyProfilePageInsets(View view, int top, int bottom) {
        if (view instanceof androidx.swiperefreshlayout.widget.SwipeRefreshLayout) {
            com.typheye.wgpro.utils.AppBarBlur.offsetRefreshIndicator(
                    (androidx.swiperefreshlayout.widget.SwipeRefreshLayout) view, top);
        }
        if (view instanceof androidx.core.widget.NestedScrollView) {
            androidx.core.widget.NestedScrollView scroll =
                    (androidx.core.widget.NestedScrollView) view;
            scroll.setClipToPadding(false);
            com.typheye.wgpro.utils.AppBarBlur.padScrollableBottom(scroll, bottom);
            com.typheye.wgpro.utils.AppBarBlur.padScrollableTop(scroll, top);
            return;
        }
        if (view instanceof androidx.recyclerview.widget.RecyclerView) {
            androidx.recyclerview.widget.RecyclerView recycler =
                    (androidx.recyclerview.widget.RecyclerView) view;
            recycler.setClipToPadding(false);
            com.typheye.wgpro.utils.AppBarBlur.padScrollableBottom(recycler, bottom);
            com.typheye.wgpro.utils.AppBarBlur.padScrollableTop(recycler, top);
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
        // 前景/系统栏配色统一由 applyBarChrome 决定（它看折叠态 + 小窗 + 主题）。
        // 这里**先清掉前景缓存**：onCreateOptionsMenu 在首次布局之后才跑，而折叠监听在那之前
        // 已经记过一次前景色；缓存命中会直接跳过换色 → 右上角「更多」图标第一次进来时保留
        // vector 里烘死的颜色（表现成"首次进入颜色异常，折叠一下再展开就正常"）。强制重来即可。
        chromeForeground = Integer.MIN_VALUE;
        applyBarChrome(barCollapsed);
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
