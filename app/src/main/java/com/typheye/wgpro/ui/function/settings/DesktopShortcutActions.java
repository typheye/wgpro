package com.typheye.wgpro.ui.function.settings;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.ui.main.MainActivity;
import com.typheye.wgpro.ui.widget.WGProHint;

/**
 * 桌面快捷菜单能执行的动作：设置页里的宫格点击、以及桌面长按图标弹出的快捷方式，都走这里，
 * 保证"点击直达"的入口只有一份实现。
 */
public final class DesktopShortcutActions {
    /** 桌面快捷方式带回来的功能 id（launcher 是外部进程，只能落到导出的 MainActivity）。 */
    public static final String EXTRA_SHORTCUT_ID = "desktop_shortcut_id";

    private DesktopShortcutActions() {
    }

    /**
     * 快捷方式的 Intent：必须指向**导出的**入口（launcher 属于外部进程，启动不了未导出的
     * WebActivity/设置页），所以统一进 MainActivity 再由它按 extra 分发。
     */
    public static Intent shortcutIntent(Context context, String id) {
        return new Intent(context, MainActivity.class)
                .setAction(Intent.ACTION_VIEW)
                .putExtra(EXTRA_SHORTCUT_ID, id);
    }

    /** 执行某个功能。 */
    public static void run(Context context, String id) {
        if (context == null || id == null) return;
        switch (id) {
            case "transfer": openWeb(context, "https://wgpro.typheye.cn/push"); break;
            case "guide": openWeb(context, "https://wgpro.typheye.cn/docs"); break;
            case "website": openWeb(context, "https://wgpro.typheye.cn/"); break;
            case "beta": openWeb(context, "https://www.typheye.cn/?p=beta"); break;
            case "devices":
                context.startActivity(new Intent(context,
                        com.typheye.wgpro.ui.function.device.AddDeviceActivity.class)
                        .putExtra(com.typheye.wgpro.ui.function.device.AddDeviceActivity.EXTRA_MODE,
                                com.typheye.wgpro.ui.function.device.AddDeviceActivity.MODE_VIRTUAL));
                break;
            case "notifications":
                context.startActivity(new Intent(context,
                        com.typheye.wgpro.ui.function.community.NotificationActivity.class));
                break;
            case "history":
                context.startActivity(new Intent(context,
                        com.typheye.wgpro.ui.function.community.AccountListActivity.class)
                        .putExtra(com.typheye.wgpro.ui.function.community.AccountListActivity.EXTRA_TITLE,
                                "浏览历史")
                        .putExtra(com.typheye.wgpro.ui.function.community.AccountListActivity.EXTRA_MODE,
                                com.typheye.wgpro.ui.function.community.CloudListFragment.MODE_HISTORY));
                break;
            case "starred":
                context.startActivity(new Intent(context,
                        com.typheye.wgpro.ui.function.community.AccountListActivity.class)
                        .putExtra(com.typheye.wgpro.ui.function.community.AccountListActivity.EXTRA_TITLE,
                                "星标应用")
                        .putExtra(com.typheye.wgpro.ui.function.community.AccountListActivity.EXTRA_MODE,
                                com.typheye.wgpro.ui.function.community.CloudListFragment.MODE_COLLECTION_APP));
                break;
            case "points": {
                String uid = new com.typheye.wgpro.utils.tAccUtils(
                        context.getApplicationContext()).getUid();
                String url = com.typheye.wgpro.utils.tPointsUtils.webUrl(
                        "mall", uid, com.typheye.wgpro.ui.widget.Points.webToken);
                openWeb(context, url);
                break;
            }
            case "scan":
                context.startActivity(new Intent(context,
                        com.typheye.wgpro.ui.function.ScanQRActivity.class));
                break;
            case "downloads":
                context.startActivity(new Intent(context,
                        com.typheye.wgpro.ui.function.download.DownloadManagerActivity.class));
                break;
            case "share": {
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, "腕管Pro\nhttps://wgpro.typheye.cn");
                context.startActivity(Intent.createChooser(share, "分享应用"));
                break;
            }
            case "feedback":
                try {
                    context.startActivity(new Intent(Intent.ACTION_SENDTO,
                            Uri.parse("mailto:typheye@typheye.com"))
                            .putExtra(Intent.EXTRA_SUBJECT, "腕管Pro 反馈"));
                } catch (Exception error) {
                    android.content.ClipboardManager clipboard =
                            (android.content.ClipboardManager)
                                    context.getSystemService(Context.CLIPBOARD_SERVICE);
                    if (clipboard != null) {
                        clipboard.setPrimaryClip(
                                ClipData.newPlainText("邮箱", "typheye@typheye.com"));
                    }
                    WGProHint.show(context, "邮箱 typheye@typheye.com 已复制");
                }
                break;
            default:
                break;
        }
    }

    private static void openWeb(Context context, String url) {
        context.startActivity(new Intent(context, WebActivity.class).putExtra("URL", url));
    }
}
