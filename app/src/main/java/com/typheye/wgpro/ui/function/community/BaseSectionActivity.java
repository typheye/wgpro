package com.typheye.wgpro.ui.function.community;

import android.content.Intent;
import android.os.Bundle;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.utils.SystemBars;

abstract class BaseSectionActivity extends AppCompatActivity {
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
        if (loadingCoversAppBar()) {
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

    @Override protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent == null) return;
        setIntent(intent);
        sectionToolbar().setTitle(screenTitle());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.section_container, createContent()).commit();
    }

    protected abstract String screenTitle();
    protected abstract Fragment createContent();
    /** 底部是否有必须固定在屏幕底部的控件（聊天输入栏、提交按钮等）。 */
    protected boolean hasPinnedBottomBar() { return false; }

    /** 固定底栏是否自己消化系统导航栏内边距（沉浸式：底栏背景延伸到导航条后面）。 */
    protected boolean bottomBarOwnsNavigationInset() { return false; }
    protected Toolbar sectionToolbar() { return findViewById(R.id.section_toolbar); }
    protected boolean loadingCoversAppBar() { return false; }

    /** 页面容器上的毛玻璃控制器（固定底栏可追加自己的快照层，共用同一份快照）。 */
    public com.typheye.wgpro.utils.BarBlurController blurController() { return blurController; }

    /** 二级页应用栏里的子标签（默认隐藏，由页面自己挂载，例如浏览历史的 动态/应用/资源）。 */
    public com.google.android.material.tabs.TabLayout pageTabs() {
        return findViewById(R.id.section_page_tabs);
    }

    void showContentLoading() {
        loadingStarted = android.os.SystemClock.uptimeMillis();
        loadingOverlay.animate().cancel(); loadingOverlay.setAlpha(1f); loadingOverlay.setVisibility(View.VISIBLE);
        // 纯色遮罩期间应用栏改用页面底色：与遮罩同色，避免「毛玻璃 + 遮罩」叠加出异常渲染
        appBar.setBackgroundColor(getColor(R.color.surface_primary));
        appBar.setBackgroundTintList(android.content.res.ColorStateList.valueOf(
                getColor(R.color.surface_primary)));
        // 注意：不要给整个内容容器加 RenderEffect（大面积模糊与子 View 的毛玻璃
        // RenderEffect 叠加会出现残留/错位的模糊带），只用纯色遮罩即可。
    }

    void hideContentLoading() {
        long delay = Math.max(0L, 300L - (android.os.SystemClock.uptimeMillis() - loadingStarted));
        loadingOverlay.postDelayed(() -> {
            if (isFinishing() || isDestroyed()) return;
            // 恢复应用栏（按「启用高斯模糊」开关：开=半透明、关=不透明页面底色）
            com.typheye.wgpro.utils.BarBlurController.registerBar(this, appBar);
            loadingOverlay.animate().alpha(0f).setDuration(120L)
                    .withEndAction(() -> loadingOverlay.setVisibility(View.GONE)).start();
        }, delay);
    }

    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) { finish(); return true; }
        return super.onOptionsItemSelected(item);
    }
}
