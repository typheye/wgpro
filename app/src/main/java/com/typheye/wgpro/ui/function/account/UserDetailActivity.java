package com.typheye.wgpro.ui.function.account;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
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
        ImageView avatar = findViewById(R.id.detail_avatar);
        String nick = account.getNick();
        String uid = account.getUid();
        String bio = account.getShuo();
        String displayName = nick == null || nick.trim().isEmpty() ? "用户" : nick.trim();
        ((TextView) findViewById(R.id.detail_nick)).setText(displayName);
        ((TextView) findViewById(R.id.detail_collapsed_nick)).setText(displayName);
        ((TextView) findViewById(R.id.detail_uid)).setText(uid == null || uid.isEmpty() ? "UID 未知" : "UID " + uid);
        ((TextView) findViewById(R.id.detail_bio)).setText(bio == null || bio.trim().isEmpty()
                ? "这个人还没有简介呢~" : bio.trim());
        avatarText.setText(displayName.substring(0, 1).toUpperCase());
        if (uid != null && !uid.isEmpty()) {
            File file = new File(getFilesDir(), "avatar_" + uid + ".jpg");
            Bitmap bitmap = file.exists() ? BitmapFactory.decodeFile(file.getAbsolutePath()) : null;
            if (bitmap != null) {
                avatar.setImageBitmap(bitmap);
                avatar.setVisibility(View.VISIBLE);
                avatarText.setVisibility(View.GONE);
                ((ImageView) findViewById(R.id.detail_collapsed_avatar)).setImageBitmap(bitmap);
            }
        }
        if (avatar.getVisibility() != View.VISIBLE) {
            ((ImageView) findViewById(R.id.detail_collapsed_avatar))
                    .setImageResource(R.drawable.ic_person_placeholder_vector);
        }

        setupProfileSheet();
        setupProfilePages();
        appBar.post(this::applyAdaptiveChromeColors);
    }

    private void setupProfileSheet() {
        View root = findViewById(R.id.detail_root);
        View sheet = findViewById(R.id.detail_sheet);
        View expandedIdentity = findViewById(R.id.detail_expanded_identity);
        View collapsedIdentity = findViewById(R.id.detail_collapsed_identity);
        View bio = findViewById(R.id.detail_bio);
        BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(sheet);
        behavior.setFitToContents(false);
        behavior.setHideable(false);
        behavior.setDraggable(true);
        behavior.setShouldRemoveExpandedCorners(false);
        behavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HALF_EXPANDED) {
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }

            @Override public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                float progress = Math.max(0f, Math.min(1f, slideOffset));
                expandedIdentity.setAlpha(1f - Math.min(1f, progress / 0.72f));
                collapsedIdentity.setAlpha(Math.max(0f, (progress - 0.78f) / 0.22f));
            }
        });
        root.post(() -> {
            int minimumVisibleSheet = dp(180);
            WindowInsetsCompat insets = ViewCompat.getRootWindowInsets(root);
            int statusBarInset = insets == null ? 0
                    : insets.getInsets(WindowInsetsCompat.Type.statusBars()).top;
            // The expanded sheet stops at the measured system bar plus the standard
            // 64dp app bar. This is an absolute parent coordinate on every density.
            behavior.setExpandedOffset(statusBarInset + dp(64));
            int[] rootPosition = new int[2];
            int[] bioPosition = new int[2];
            root.getLocationInWindow(rootPosition);
            bio.getLocationInWindow(bioPosition);
            int bioBottom = bioPosition[1] - rootPosition[1] + bio.getHeight();
            int collapsedTop = Math.max(dp(386), bioBottom + dp(24));
            collapsedTop = Math.min(collapsedTop, root.getHeight() - minimumVisibleSheet);
            ViewGroup.LayoutParams headerParams = appBar.getLayoutParams();
            int requiredHeaderHeight = Math.max(dp(410), collapsedTop + dp(24));
            if (headerParams.height != requiredHeaderHeight) {
                headerParams.height = requiredHeaderHeight;
                appBar.setLayoutParams(headerParams);
            }
            behavior.setPeekHeight(Math.max(minimumVisibleSheet, root.getHeight() - collapsedTop), false);
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        });
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    private void setupProfilePages() {
        TabLayout tabs = findViewById(R.id.detail_tabs);
        ViewPager2 pager = findViewById(R.id.detail_pager);
        FrameLayout pool = findViewById(R.id.detail_page_pool);
        List<View> pages = Arrays.asList(findViewById(R.id.detail_page_activity),
                findViewById(R.id.detail_page_favorites), findViewById(R.id.detail_page_resources));
        for (View page : pages) pool.removeView(page);
        pager.setAdapter(new ProfilePageAdapter(pages));
        pager.setOffscreenPageLimit(2);
        RecyclerView pagerRecycler = (RecyclerView) pager.getChildAt(0);
        ViewCompat.setNestedScrollingEnabled(pagerRecycler, false);
        new TabLayoutMediator(tabs, pager, (tab, position) ->
                tab.setText(new String[]{"动态", "收藏", "资源"}[position])).attach();
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override public void onPageSelected(int position) {
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
            new MaterialAlertDialogBuilder(this)
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
