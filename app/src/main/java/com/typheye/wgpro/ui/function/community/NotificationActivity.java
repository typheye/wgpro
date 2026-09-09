package com.typheye.wgpro.ui.function.community;

import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;

import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.utils.InboxNotificationHelper;

public class NotificationActivity extends BaseSectionActivity {
    @Override protected String screenTitle() { return "通知"; }
    @Override protected Fragment createContent() {
        return CloudListFragment.newInstance(CloudListFragment.MODE_NOTIFICATIONS);
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

    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_notification_more) {
            showInboxMenu();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showInboxMenu() {
        boolean dnd = InboxNotificationHelper.isDoNotDisturb(this);
        String dndLabel = dnd ? "关闭免打扰" : "开启免打扰";
        new WGProAlertDialogBuilder(this).setTitle("更多")
                .setItems(new CharSequence[]{"清空消息列表", dndLabel}, (dialog, which) -> {
                    if (which == 0) confirmClearMessages();
                    else confirmToggleDoNotDisturb(!dnd);
                }).show();
    }

    private void confirmClearMessages() {
        new WGProAlertDialogBuilder(this)
                .setTitle("清空消息列表？")
                .setMessage("将删除当前账户的全部私信会话和系统消息。")
                .setNegativeButton("取消", null)
                .setPositiveButton("清空", (dialog, which) -> {
                    Fragment current = getSupportFragmentManager()
                            .findFragmentById(R.id.section_container);
                    if (current instanceof CloudListFragment) {
                        ((CloudListFragment) current).clearMessageList();
                    }
                    showResult("已清空", "私信会话和系统消息已清空。");
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
