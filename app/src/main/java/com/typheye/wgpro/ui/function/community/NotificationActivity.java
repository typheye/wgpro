package com.typheye.wgpro.ui.function.community;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.AppUtils;

/** 通知中心：主列表 Fragment + 系统消息详情 Fragment。 */
public class NotificationActivity extends AppCompatActivity {
    public static final String EXTRA_OPEN_SYSTEM_MESSAGES = "open_system_messages";

    private Toolbar toolbar;
    private boolean detailOpen;

    @Override protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        AppUtils.useScreenCutArea(getWindow(), this);
        setContentView(R.layout.activity_section_host);
        toolbar = findViewById(R.id.section_toolbar);
        AppUtils.applyMainWindowInsets(findViewById(R.id.section_app_bar),
                findViewById(R.id.section_container));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> navigateBack());
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            detailOpen = getSupportFragmentManager().getBackStackEntryCount() > 0;
            syncChrome();
        });
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override public void handleOnBackPressed() {
                navigateBack();
            }
        });
        if (state == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.section_container,
                            CloudListFragment.newInstance(CloudListFragment.MODE_NOTIFICATIONS))
                    .commit();
        }
        syncChrome();
        if (getIntent().getBooleanExtra(EXTRA_OPEN_SYSTEM_MESSAGES, false)) {
            toolbar.post(this::openSystemMessages);
        }
    }

    @Override protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (intent != null && intent.getBooleanExtra(EXTRA_OPEN_SYSTEM_MESSAGES, false)) {
            openSystemMessages();
        }
    }

    @Override protected void onResume() {
        super.onResume();
        detailOpen = getSupportFragmentManager().getBackStackEntryCount() > 0;
        syncChrome();
    }

    public void openSystemMessages() {
        if (detailOpen || isFinishing() || isDestroyed()) return;
        detailOpen = true;
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.fragment_enter, R.animator.fragment_exit,
                        R.animator.fragment_pop_enter, R.animator.fragment_exit)
                .replace(R.id.section_container, new SystemMessageDetailFragment())
                .addToBackStack("system_messages")
                .commit();
        syncChrome();
    }

    private void navigateBack() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    private void syncChrome() {
        if (toolbar == null) return;
        toolbar.setTitle(detailOpen ? "系统消息" : "通知");
        invalidateOptionsMenu();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notification, menu);
        MenuItem more = menu.findItem(R.id.action_notification_more);
        if (more != null && more.getIcon() != null) {
            Drawable icon = DrawableCompat.wrap(more.getIcon()).mutate();
            DrawableCompat.setTint(icon, getColor(R.color.text_primary));
            more.setIcon(icon);
        }
        return true;
    }

    @Override public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem more = menu.findItem(R.id.action_notification_more);
        if (more != null) more.setVisible(detailOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_notification_more) {
            Fragment current = getSupportFragmentManager()
                    .findFragmentById(R.id.section_container);
            if (current instanceof SystemMessageDetailFragment) {
                ((SystemMessageDetailFragment) current).showMoreMenu();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
