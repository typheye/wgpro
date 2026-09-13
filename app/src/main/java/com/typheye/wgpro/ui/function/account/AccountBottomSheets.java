package com.typheye.wgpro.ui.function.account;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProBottomSheetDialog;
import com.typheye.wgpro.utils.tAccUtils;

/** 授权其它设备登录时使用的底部弹窗；本机登录已改为跳系统浏览器。 */
public final class AccountBottomSheets {
    private static final long RESULT_DELAY_MS = 380L;

    private AccountBottomSheets() { }

    public static void showGrant(Activity activity, String requestId, Runnable onFinished) {
        if (requestId == null || requestId.trim().isEmpty()) {
            showMessage(activity, "请求无效", "登录请求无效或已过期。", onFinished);
            return;
        }
        tAccUtils accountUtils = new tAccUtils(activity);
        accountUtils.goConfirmLoginRequest(requestId.trim(), new tAccUtils.SetCallback() {
            @Override public void onSuccess() {
                mainDelayed(activity, () -> showGrantDecision(
                        activity, accountUtils, requestId.trim(), onFinished));
            }

            @Override public void onError(String message) {
                mainDelayed(activity, () -> showMessage(activity,
                        "无法验证请求", message, onFinished));
            }
        });
    }

    private static void showGrantDecision(Activity activity, tAccUtils accountUtils,
                                          String requestId, Runnable onFinished) {
        View content = LayoutInflater.from(activity)
                .inflate(R.layout.sheet_account_grant, null, false);
        WGProBottomSheetDialog dialog = new WGProAlertDialogBuilder(activity)
                .setTitle("确认登录")
                .setView(content)
                .setNegativeButton("拒绝", null)
                .setPositiveButton("允许登录", null)
                .create();
        boolean[] submitted = {false};
        dialog.setOnShowListener(ignored -> {
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(v -> {
                submitted[0] = true;
                dialog.dismissForReplacement();
                submitGrant(activity, accountUtils, requestId, false, onFinished);
            });
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
                submitted[0] = true;
                dialog.dismissForReplacement();
                submitGrant(activity, accountUtils, requestId, true, onFinished);
            });
        });
        dialog.setOnDismissListener(ignored -> {
            if (!submitted[0] && onFinished != null) onFinished.run();
        });
        dialog.show();
    }

    private static void submitGrant(Activity activity, tAccUtils accountUtils, String requestId,
                                    boolean approve, Runnable onFinished) {
        accountUtils.postLoginRequest(requestId, approve, new tAccUtils.SetCallback() {
            @Override public void onSuccess() {
                mainDelayed(activity, () -> showMessage(activity,
                        approve ? "已允许登录" : "已拒绝登录",
                        approve ? "另一台设备现在可以登录。" : "本次登录请求已拒绝。",
                        onFinished));
            }

            @Override public void onError(String message) {
                mainDelayed(activity, () -> showMessage(activity,
                        "操作失败", message, onFinished));
            }
        });
    }

    private static void showMessage(Activity activity, String title, String message,
                                    Runnable onDismiss) {
        if (!usable(activity)) return;
        WGProBottomSheetDialog dialog = new WGProAlertDialogBuilder(activity)
                .setTitle(title)
                .setMessage(message == null || message.trim().isEmpty() ? "请稍后再试。" : message)
                .setPositiveButton("完成", null)
                .create();
        if (onDismiss != null) dialog.setOnDismissListener(ignored -> onDismiss.run());
        dialog.show();
    }

    private static void mainDelayed(Activity activity, Runnable action) {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (usable(activity)) action.run();
        }, RESULT_DELAY_MS);
    }

    private static boolean usable(Activity activity) {
        return !activity.isFinishing() && !activity.isDestroyed();
    }
}
