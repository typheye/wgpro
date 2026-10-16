package com.typheye.wgpro.ui.function.community;

import android.content.Intent;
import android.os.Bundle;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.utils.SystemBars;

public abstract class BaseSectionActivity extends AppCompatActivity {
    private View appBar;
    private View content;
    private View loadingOverlay;
    private long loadingStarted;
    private com.typheye.wgpro.utils.BarBlurController blurController;
    @Override protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        AppUtils.useScreenCutArea(getWindow(), this);
        setContentView(R.layout.activity_section_host);
        appBar = findViewById(R.id.section_app_bar);
        content = findViewById(R.id.section_container);
        loadingOverlay = findViewById(R.id.section_loading_overlay);
        if (!usesContentLoadingOverlay()) {
            // ★ 本页不用整页加载遮罩：直接把它从布局里**摘掉**（不是隐藏），
            //   加载态改由内容里的下拉刷新指示器表达（见 showContentLoading/hideContentLoading）。
            ViewGroup parent = (ViewGroup) loadingOverlay.getParent();
            if (parent != null) {
                parent.removeView(loadingOverlay);
            }
            loadingOverlay = null;
        } else if (loadingCoversAppBar()) {
            ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) loadingOverlay.getLayoutParams();
            params.topToBottom = ConstraintLayout.LayoutParams.UNSET;
            params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
            loadingOverlay.setLayoutParams(params);
        }
        if (hasPinnedBottomBar()) {
            // 底部有固定控件（输入栏/提交按钮）：内容容器让开系统栏，固定控件始终在导航条上方。
            blurController = com.typheye.wgpro.utils.AppBarBlur.install(this, appBar, content);
            if (!bottomBarOwnsNavigationInset()) {
                SystemBars.reserveBottomInset(content);
            }
        } else {
            // 纯滚动页：容器不带底部内边距，改由页面自己的滚动视图预留，
            // 这样内容能画到导航条下面，末尾又不会被小横条永久遮住。
            blurController = com.typheye.wgpro.utils.AppBarBlur.install(this, appBar, content);
            AppUtils.applyScrollBottomInsets(getSupportFragmentManager(),
                    R.id.section_container);
        }
        Toolbar toolbar = findViewById(R.id.section_toolbar);
        toolbar.setTitle(screenTitle());
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        if (state == null) getSupportFragmentManager().beginTransaction()
                .replace(R.id.section_container, createContent()).commit();

    }

    /** 已在同一页面时（通知点击）不重建内容，避免"二次进入"。 */
    @Override protected void onNewIntent(Intent intent) {
        if (isSamePage(intent)) {
            super.onNewIntent(intent);
            return;
        }
        super.onNewIntent(intent);
        if (intent == null) return;
        setIntent(intent);
        sectionToolbar().setTitle(screenTitle());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.section_container, createContent()).commit();
    }

    /** 通知/外部再次拉起时，判断是不是"已经在看这一页"。 */
    private boolean isSamePage(Intent intent) {
        if (intent == null || intent.getComponent() == null || getIntent() == null) {
            return true;
        }
        return intent.getComponent().equals(getIntent().getComponent());
    }

    protected abstract String screenTitle();
    protected abstract Fragment createContent();
    /** 底部是否有必须固定在屏幕底部的控件（聊天输入栏、提交按钮等）。 */
    protected boolean hasPinnedBottomBar() { return false; }

    /** 固定底栏是否自己消化系统导航栏内边距（沉浸式：底栏背景延伸到导航条后面）。 */
    protected boolean bottomBarOwnsNavigationInset() { return false; }
    protected Toolbar sectionToolbar() { return findViewById(R.id.section_toolbar); }
    /** 整页加载遮罩默认就盖住应用栏（要只盖内容区的页面可重写为 false）。 */
    protected boolean loadingCoversAppBar() { return true; }

    /**
     * 本页是否使用**整页加载遮罩**。
     *
     * <p>默认 {@code false}：遮罩会被从布局里摘掉，加载态改用内容里的下拉刷新指示器
     * （见 {@link #showContentLoading()}）。只有**详情类**页面（应用详情 / 资源详情 /
     * 模拟设备详情）重写为 {@code true}，保留原来的整页遮罩逻辑。</p>
     */
    protected boolean usesContentLoadingOverlay() { return false; }

    /** 页面容器上的毛玻璃控制器（固定底栏可追加自己的快照层，共用同一份快照）。 */
    public com.typheye.wgpro.utils.BarBlurController blurController() { return blurController; }

    /** 二级页应用栏里的子标签（默认隐藏，由页面自己挂载，例如浏览历史的 动态/应用/资源）。 */
    public com.google.android.material.tabs.TabLayout pageTabs() {
        return findViewById(R.id.section_page_tabs);
    }

    /** 显示整页加载遮罩（`loadingCoversAppBar()` 为 true 时会盖住应用栏）；跨包二级页也可调用。 */
    public void showContentLoading() {
        loadingStarted = android.os.SystemClock.uptimeMillis();
        if (loadingOverlay == null) {
            // 没有整页遮罩的页面：加载态点亮内容里的下拉刷新指示器
            setContentRefreshing(true);
            return;
        }
        loadingOverlay.animate().cancel(); loadingOverlay.setAlpha(1f); loadingOverlay.setVisibility(View.VISIBLE);
        // 纯色遮罩期间应用栏改用页面底色：与遮罩同色，避免「毛玻璃 + 遮罩」叠加出异常渲染
        appBar.setBackgroundColor(com.typheye.wgpro.ui.widget.WGProColors.of(this, R.color.surface_primary));
        appBar.setBackgroundTintList(android.content.res.ColorStateList.valueOf(
                com.typheye.wgpro.ui.widget.WGProColors.of(this, R.color.surface_primary)));
        // 注意：不要给整个内容容器加 RenderEffect（大面积模糊与子 View 的毛玻璃
        // RenderEffect 叠加会出现残留/错位的模糊带），只用纯色遮罩即可。
    }

    /** 关闭整页加载遮罩（含最短展示时间）。 */
    public void hideContentLoading() {
        long delay = Math.max(0L, 300L - (android.os.SystemClock.uptimeMillis() - loadingStarted));
        if (loadingOverlay == null) {
            // 没有整页遮罩的页面：收起内容里的下拉刷新指示器（同样保证最短展示时间）
            content.postDelayed(() -> {
                if (isFinishing() || isDestroyed()) return;
                setContentRefreshing(false);
            }, delay);
            return;
        }
        loadingOverlay.postDelayed(() -> {
            if (isFinishing() || isDestroyed()) return;
            // 恢复应用栏（按「启用高斯模糊」开关：开=半透明、关=不透明页面底色）
            com.typheye.wgpro.utils.BarBlurController.registerBar(this, appBar);
            loadingOverlay.animate().alpha(0f).setDuration(120L)
                    .withEndAction(() -> loadingOverlay.setVisibility(View.GONE)).start();
        }, delay);
    }

    /**
     * 用**内容里的下拉刷新指示器**表达加载态（没有整页遮罩的页面走这条）。
     *
     * <p>ViewPager2 的不可见页也在视图树上，所以只认"可见"的那一支，避免点亮别的标签页。</p>
     */
    private void setContentRefreshing(boolean refreshing) {
        com.typheye.wgpro.ui.widget.WGProRefreshLayout refresh = findRefreshLayout(content);
        if (refresh != null) {
            refresh.setRefreshing(refreshing);
        }
    }

    /** 内容里第一个（优先可见支路的）下拉刷新控件；没有就返回 null。 */
    @Nullable
    private static com.typheye.wgpro.ui.widget.WGProRefreshLayout findRefreshLayout(
            @Nullable View root) {
        if (root == null) {
            return null;
        }
        if (root instanceof com.typheye.wgpro.ui.widget.WGProRefreshLayout) {
            return (com.typheye.wgpro.ui.widget.WGProRefreshLayout) root;
        }
        if (root instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) root;
            for (int index = 0; index < group.getChildCount(); index++) {
                View child = group.getChildAt(index);
                if (child.getVisibility() != View.VISIBLE) {
                    continue;
                }
                com.typheye.wgpro.ui.widget.WGProRefreshLayout hit = findRefreshLayout(child);
                if (hit != null) {
                    return hit;
                }
            }
        }
        return null;
    }

    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) { finish(); return true; }
        return super.onOptionsItemSelected(item);
    }
}
