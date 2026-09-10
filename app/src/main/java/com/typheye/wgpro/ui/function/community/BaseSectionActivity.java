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
            // 底部有固定控件（输入栏/提交按钮）：整个容器让开系统栏，固定控件始终在导航条上方。
            AppUtils.applyMainWindowInsets(appBar, content);
        } else {
            // 纯滚动页：容器不带底部内边距，改由页面自己的滚动视图预留，
            // 这样内容能画到导航条下面，末尾又不会被小横条永久遮住。
            SystemBars.applyAppBarInsets(appBar, null);
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
    protected Toolbar sectionToolbar() { return findViewById(R.id.section_toolbar); }
    protected boolean loadingCoversAppBar() { return false; }

    void showContentLoading() {
        loadingStarted = android.os.SystemClock.uptimeMillis();
        loadingOverlay.animate().cancel(); loadingOverlay.setAlpha(1f); loadingOverlay.setVisibility(View.VISIBLE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            RenderEffect blur = RenderEffect.createBlurEffect(18f, 18f, Shader.TileMode.CLAMP);
            content.setRenderEffect(blur);
            if (loadingCoversAppBar()) appBar.setRenderEffect(blur);
        }
    }

    void hideContentLoading() {
        long delay = Math.max(0L, 300L - (android.os.SystemClock.uptimeMillis() - loadingStarted));
        loadingOverlay.postDelayed(() -> {
            if (isFinishing() || isDestroyed()) return;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                content.setRenderEffect(null);
                if (loadingCoversAppBar()) appBar.setRenderEffect(null);
            }
            loadingOverlay.animate().alpha(0f).setDuration(120L)
                    .withEndAction(() -> loadingOverlay.setVisibility(View.GONE)).start();
        }, delay);
    }

    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) { finish(); return true; }
        return super.onOptionsItemSelected(item);
    }
}
