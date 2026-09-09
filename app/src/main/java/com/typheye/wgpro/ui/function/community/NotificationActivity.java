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
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.utils.InboxNotificationHelper;

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
        Fragment current = getSupportFragmentManager().findFragmentById(R.id.section_container);
        if (current instanceof CloudListFragment) {
            ((CloudListFragment) current).markSystemMessagesReadLocally();
            ((CloudListFragment) current).skipNextResumeReload();
        }
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
        if (more != null) more.setVisible(true);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_notification_more) {
            Fragment current = getSupportFragmentManager()
                    .findFragmentById(R.id.section_container);
            if (current instanceof SystemMessageDetailFragment) {
                ((SystemMessageDetailFragment) current).showMoreMenu();
            } else {
                showInboxMenu();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showInboxMenu() {
        boolean dnd = InboxNotificationHelper.isDoNotDisturb(this);
        String dndLabel = dnd ? "关闭免打扰" : "开启免打扰";
        new WGProAlertDialogBuilder(this).setTitle("更多")
                .setItems(new CharSequence[]{"清空消息列表", dndLabel}, (dialog, which) -> {
                    if (which == 0) confirmClearConversations();
                    else confirmToggleDoNotDisturb(!dnd);
                }).show();
    }

    private void confirmClearConversations() {
        new WGProAlertDialogBuilder(this)
                .setTitle("清空消息列表？")
                .setMessage("将删除当前账户的全部私信会话，仅保留置顶的系统消息。")
                .setNegativeButton("取消", null)
                .setPositiveButton("清空", (dialog, which) -> {
                    Fragment current = getSupportFragmentManager()
                            .findFragmentById(R.id.section_container);
                    if (current instanceof CloudListFragment) {
                        ((CloudListFragment) current).clearConversations();
                    }
                    showResult("已清空", "私信会话已清空，系统消息仍会保留。");
                }).show();
    }

    private void confirmToggleDoNotDisturb(boolean enabled) {
        new WGProAlertDialogBuilder(this)
                .setTitle(enabled ? "开启免打扰？" : "关闭免打扰？")
                .setMessage(enabled
                        ? "开启后，系统消息和私信的 Android 通知将不再触发，首页通知图标也不再显示红点。"
                        : "关闭后，将恢复系统消息和私信的 Android 通知与首页红点提醒。")
                .setNegativeButton("取消", null)
                .setPositiveButton("确认", (dialog, which) -> {
                    InboxNotificationHelper.setDoNotDisturb(this, enabled);
                    showResult(enabled ? "免打扰已开启" : "免打扰已关闭",
                            enabled ? "新的消息提醒已暂停。" : "新的消息提醒已恢复。");
                }).show();
    }

    private void showResult(String title, String message) {
        new WGProAlertDialogBuilder(this).setTitle(title).setMessage(message)
                .setNegativeButton("关闭", null).show();
    }
}
