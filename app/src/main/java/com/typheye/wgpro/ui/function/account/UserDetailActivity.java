package com.typheye.wgpro.ui.function.account;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.content.Intent;
import android.content.res.Configuration;
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

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.utils.tAccUtils;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/** Reusable profile surface used when opening the account card. */
public class UserDetailActivity extends AppCompatActivity {
    private tAccUtils account;
    private Toolbar toolbar;
    private AppBarLayout appBar;
    private Menu toolbarMenu;
    private View detailRoot;
    private View detailSheet;
    private View expandedIdentity;
    private BottomSheetBehavior<View> sheetBehavior;
    private ViewTreeObserver.OnPreDrawListener pendingProfileLayout;
    private int lastRootWidth = -1;
    private int lastRootHeight = -1;
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
        account = new tAccUtils(this);
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
        String nick = account.getNick();
        String uid = account.getUid();
        String bio = account.getShuo();
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

        setupProfileSheet();
        setupProfilePages();
        appBar.post(this::applyAdaptiveChromeColors);
    }

    private void setupProfileSheet() {
        detailRoot = findViewById(R.id.detail_root);
        detailSheet = findViewById(R.id.detail_sheet);
        expandedIdentity = findViewById(R.id.detail_expanded_identity);
        View collapsedIdentity = findViewById(R.id.detail_collapsed_identity);
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
        List<View> pages = Arrays.asList(findViewById(R.id.detail_page_activity),
                findViewById(R.id.detail_page_favorites), findViewById(R.id.detail_page_resources));
        for (View page : pages) pool.removeView(page);
        pager.setAdapter(new ProfilePageAdapter(pages));
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
            new WGProAlertDialogBuilder(this)
                    .setItems(new String[]{"编辑资料", "分享主页"}, (dialog, which) -> {
                        if (which == 0) {
                            startActivity(new Intent(this, AccMangerActivity.class)
                                    .putExtra("TARGET_FRAGMENT", "edit"));
                        } else {
                            Intent share = new Intent(Intent.ACTION_SEND)
                                    .setType("text/plain")
                                    .putExtra(Intent.EXTRA_TEXT, "Typheye 用户 " + account.getNick());
                            startActivity(Intent.createChooser(share, "分享主页"));
                        }
                    }).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
