package open.cn.awg.pro.ui.list;

import android.app.Activity;
import android.view.View;
import android.view.animation.AlphaAnimation;

import open.cn.awg.pro.R;
import open.cn.awg.pro.apps.InstalledAppListActivity;
import open.cn.awg.pro.browser.X5BrowserActivity;
import open.cn.awg.pro.chat.WearChatActivity;
import open.cn.awg.pro.chat.WearChatAgreementActivity;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.NativeActions;
import open.cn.awg.pro.core.NativeFiles;
import open.cn.awg.pro.experimental.MoreFeaturesActivity;
import open.cn.awg.pro.file.FileRootPickerActivity;
import open.cn.awg.pro.plugin.ExtensionPluginActivity;
import open.cn.awg.pro.repair.CrashFeedbackActivity;
import open.cn.awg.pro.repair.ResetAppActivity;
import open.cn.awg.pro.settings.SettingsActivity;
import open.cn.awg.pro.tools.LocationInfoActivity;
import open.cn.awg.pro.tools.SystemToolsActivity;
import open.cn.awg.pro.ui.main.AboutAppActivity;
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;

import java.util.Map;
import java.util.Objects;

/** Native click and long-click behavior for the main menu row. */
public final class MainMenuRowBinder implements NativeListAdapter.RowBinder {
    private final Activity owner;

    public MainMenuRowBinder(Activity owner) {
        this.owner = Objects.requireNonNull(owner, "owner");
    }

    @Override
    public void bind(View itemView, Map<Integer, Object> row, int position) {
        View clickTarget = itemView.findViewById(R.id.xxbj1);
        clickTarget.setOnClickListener(view -> onClick(row));
        clickTarget.setOnLongClickListener(view -> {
            showText("详细信息", row.get(R.id.wb1));
            return true;
        });
    }

    private void onClick(Map<Integer, Object> row) {
        try {
            Object menuId = row.get(R.id.wb2);
            if (Objects.equals(menuId, "1")) {
                NativeActions.launch(owner, FileRootPickerActivity.class);
            } else if (Objects.equals(menuId, "5")) {
                NativeActions.launch(owner, InstalledAppListActivity.class);
            } else if (Objects.equals(menuId, "2")) {
                NativeActions.launch(owner, AboutAppActivity.class);
            } else if (Objects.equals(menuId, "3")) {
                showExitPrompt();
            } else if (Objects.equals(menuId, "4")) {
                NativeActions.launch(owner, TextQrDisplayActivity.class);
                NativeActions.finish(owner);
            } else if (Objects.equals(menuId, "6")) {
                NativeActions.launch(owner, ResetAppActivity.class);
                NativeActions.finish(owner);
            } else if (Objects.equals(menuId, "8")) {
                NativeActions.launch(owner, SettingsActivity.class);
            } else if (Objects.equals(menuId, "9")) {
                NativeActions.launch(owner, isWearChatEnabled()
                        ? WearChatActivity.class : WearChatAgreementActivity.class);
            } else if (Objects.equals(menuId, "10")) {
                NativeActions.launch(owner, SystemToolsActivity.class);
            } else if (Objects.equals(menuId, "11")) {
                NativeActions.launch(owner, X5BrowserActivity.class);
            } else if (Objects.equals(menuId, "12")) {
                NativeActions.launch(owner, LocationInfoActivity.class);
            } else if (Objects.equals(menuId, "13")) {
                NativeActions.launch(owner, MoreFeaturesActivity.class);
            } else if (Objects.equals(menuId, "14")) {
                NativeActions.home(owner);
            } else if (Objects.equals(menuId, "20")) {
                NativeActions.launch(owner, ExtensionPluginActivity.class);
            } else if (Objects.equals(menuId, "31")) {
                NativeActions.onMain(() -> {
                    try {
                        View closeMenu = owner.findViewById(R.id.wb_main_kjfs_gbcd);
                        if (closeMenu != null) {
                            closeMenu.performClick();
                        }
                    } catch (Throwable ignored) {
                    }
                });
            } else {
                showText("提示", "未找到该选项");
            }
        } catch (Throwable error) {
            NativeActions.launch(owner, CrashFeedbackActivity.class,
                    new String[]{"log", "location"}, new Object[]{error, "c3.*"});
        }
    }

    private boolean isWearChatEnabled() {
        String path = AppPaths.appPath("settings/f11/true");
        return NativeFiles.exists(owner, path)
                && Objects.equals(NativeFiles.readText(owner, path), "1");
    }

    private void showExitPrompt() {
        AlphaAnimation animation = new AlphaAnimation(0f, 1f);
        animation.setDuration(300);
        NativeActions.onMain(() -> {
            View prompt = owner.findViewById(R.id.TCxdbj);
            if (prompt != null) {
                prompt.setVisibility(View.VISIBLE);
                prompt.startAnimation(animation);
            }
        });
    }

    private void showText(Object title, Object text) {
        NativeActions.launch(owner, TextQrDisplayActivity.class,
                new String[]{"title", "text"},
                new Object[]{String.valueOf(title), text == null ? "" : text.toString()});
    }
}
