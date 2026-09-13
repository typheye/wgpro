package com.typheye.wgpro.ui;

import android.app.Activity;

import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.utils.tAccUtils;

/**
 * 需要登录的操作统一入口。
 *
 * 浏览（动态/应用/资源）不需要登录；点赞、评论、收藏、发帖、通知等操作未登录时：
 * 先给"需要登录"提示，点「去登录」直接跳系统浏览器完成登录，登录后自动同步回应用。
 */
public final class LoginGate {
    public static final String EXTRA_OPEN_LOGIN = "open_account_login";
    /** 只切到主界面的「我的」标签，不打开任何登录面板。 */
    public static final String EXTRA_SELECT_ACCOUNT_TAB = "select_account_tab";

    private LoginGate() {
    }

    /** 已登录返回 true；未登录时弹提示并返回 false。 */
    public static boolean require(Activity activity, String actionName) {
        if (activity == null) return false;
        if (new tAccUtils(activity).isLogin()) return true;
        new WGProAlertDialogBuilder(activity)
                .setTitle("需要登录")
                .setMessage(actionName + "需要先登录 Typheye 账户。")
                .setNegativeButton("取消", null)
                .setPositiveButton("去登录", (dialog, which) -> goLogin(activity))
                .show();
        return false;
    }

    /** 直接跳系统浏览器登录；网页端批准后应用自动领取会话。 */
    public static void goLogin(Activity activity) {
        if (activity == null) return;
        tAccUtils.startBrowserLogin(activity, null);
    }
}
