package com.typheye.wgpro.ui.function.community;

import android.os.Bundle;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.AppUtils;

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
        AppUtils.applyMainWindowInsets(appBar, content);
        Toolbar toolbar = findViewById(R.id.section_toolbar);
        toolbar.setTitle(screenTitle());
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        if (state == null) getSupportFragmentManager().beginTransaction()
                .replace(R.id.section_container, createContent()).commit();
    }

    protected abstract String screenTitle();
    protected abstract Fragment createContent();
    protected Toolbar sectionToolbar() { return findViewById(R.id.section_toolbar); }

    void showContentLoading() {
        loadingStarted = android.os.SystemClock.uptimeMillis();
        loadingOverlay.animate().cancel(); loadingOverlay.setAlpha(1f); loadingOverlay.setVisibility(View.VISIBLE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            RenderEffect blur = RenderEffect.createBlurEffect(18f, 18f, Shader.TileMode.CLAMP);
            content.setRenderEffect(blur);
        }
    }

    void hideContentLoading() {
        long delay = Math.max(0L, 300L - (android.os.SystemClock.uptimeMillis() - loadingStarted));
        loadingOverlay.postDelayed(() -> {
            if (isFinishing() || isDestroyed()) return;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
                content.setRenderEffect(null);
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
