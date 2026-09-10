package com.typheye.wgpro.ui;

import android.app.Activity;
import android.content.Intent;

import com.typheye.wgpro.ui.main.MainActivity;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.utils.tAccUtils;

/**
 * 需要登录的操作统一入口。
 *
 * 浏览（动态/应用/资源）不需要登录；点赞、评论、收藏、发帖、通知等操作未登录时：
 * 先给"需要登录"提示，点「去登录」会关闭当前页面并回到主界面的「我的」页，
 * 在那里弹出登录面板——而不是在当前页面里叠一层登录弹窗。
 */
public final class LoginGate {
    public static final String EXTRA_OPEN_LOGIN = "open_account_login";

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

    /** 关闭当前页面，回到主界面的「我的」并打开登录面板。 */
    public static void goLogin(Activity activity) {
        if (activity == null) return;
        Intent intent = new Intent(activity, MainActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP)
                .putExtra(EXTRA_OPEN_LOGIN, true);
        activity.startActivity(intent);
        if (!(activity instanceof MainActivity)) {
            activity.finish();
        }
    }
}
