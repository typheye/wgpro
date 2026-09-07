package com.typheye.wgpro.ui.function.account;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProBottomSheetDialog;
import com.typheye.wgpro.utils.ImageUtils;
import com.typheye.wgpro.utils.tAccUtils;

/** Account authentication flows hosted by the app's shared Material bottom sheets. */
public final class AccountBottomSheets {
    private static final long RESULT_DELAY_MS = 380L;

    private AccountBottomSheets() { }

    public static void showPasswordLogin(Activity activity, Runnable onLoginChanged) {
        View content = LayoutInflater.from(activity)
                .inflate(R.layout.sheet_account_password_login, null, false);
        TextInputLayout accountLayout = content.findViewById(R.id.login_account_layout);
        TextInputLayout passwordLayout = content.findViewById(R.id.login_password_layout);
        TextInputEditText account = content.findViewById(R.id.login_account);
        TextInputEditText password = content.findViewById(R.id.login_password);
        View forgotPassword = content.findViewById(R.id.login_forgot_password);

        WGProBottomSheetDialog dialog = new WGProAlertDialogBuilder(activity)
                .setTitle("账密登录")
                .setView(content)
                .setNegativeButton("取消", null)
                .setPositiveButton("登录", null)
                .create();
        forgotPassword.setOnClickListener(v -> {
            dialog.dismissForReplacement();
            Intent intent = new Intent(activity, WebActivity.class);
            intent.putExtra("URL", "https://service.typheye.cn/site/user/center/find/");
            activity.startActivity(intent);
        });
        dialog.setOnShowListener(ignored -> {
            View submit = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            submit.setOnClickListener(v -> {
                String username = textOf(account);
                String passwordValue = textOf(password);
                accountLayout.setError(username.isEmpty() ? "请输入 UID 或邮箱" : null);
                passwordLayout.setError(passwordValue.isEmpty() ? "请输入密码" : null);
                if (username.isEmpty() || passwordValue.isEmpty()) return;

                hideKeyboard(activity, content);
                dialog.dismissForReplacement();
                new tAccUtils(activity).login(username, passwordValue, new tAccUtils.LoginCallback() {
                    @Override public void onSuccess(tAccUtils.LoginResult result) {
                        main(activity, () -> {
                            new tAccUtils(activity).setWebViewCookies(() -> main(activity, () -> {
                                if (onLoginChanged != null) onLoginChanged.run();
                            }));
                        });
                    }

                    @Override public void onError(String message) {
                        mainDelayed(activity, () -> showMessage(activity, "登录失败", message, null));
                    }
                });
            });
            password.setOnEditorActionListener((v, actionId, event) -> {
                submit.performClick();
                return true;
            });
        });
        dialog.show();
    }

    public static void showQrLogin(Activity activity, Runnable onLoginChanged) {
        tAccUtils accountUtils = new tAccUtils(activity);
        accountUtils.generateLoginRequest(new tAccUtils.GenerateRequestCallback() {
            @Override public void onSuccess(String requestId, String qrCodeUrl) {
                main(activity, () -> showQrCode(
                        activity, accountUtils, requestId, qrCodeUrl, onLoginChanged));
            }

            @Override public void onError(String message) {
                mainDelayed(activity, () -> showMessage(activity,
                        "无法生成二维码", message, null));
            }
        });
    }

    private static void showQrCode(Activity activity, tAccUtils accountUtils, String requestId,
                                   String qrCodeUrl, Runnable onLoginChanged) {
        View content = LayoutInflater.from(activity)
                .inflate(R.layout.sheet_account_qr_login, null, false);
        TextView status = content.findViewById(R.id.qr_login_status);
        View qrCard = content.findViewById(R.id.qr_login_card);
        ImageView qrImage = content.findViewById(R.id.qr_login_image);
        Bitmap bitmap = ImageUtils.createQRCodeBitmap(qrCodeUrl, 800, 800, "UTF-8", "H", "1",
                ContextCompat.getColor(activity, R.color.brand_soft),
                ContextCompat.getColor(activity, R.color.brand_primary));
        if (bitmap == null) {
            showMessage(activity, "二维码生成失败", "请重新生成二维码后再试。", null);
            return;
        }
        qrImage.setImageBitmap(bitmap);

        WGProBottomSheetDialog dialog = new WGProAlertDialogBuilder(activity)
                .setTitle("扫码登录")
                .setView(content)
                .setNegativeButton("取消", null)
                .create();
        Handler handler = new Handler(Looper.getMainLooper());
        long expiresAt = System.currentTimeMillis() + 300_000L;
        boolean[] listening = {true};
        Runnable[] poll = new Runnable[1];
        poll[0] = () -> {
            if (!listening[0] || !dialog.isShowing()) return;
            if (System.currentTimeMillis() >= expiresAt) {
                listening[0] = false;
                dialog.dismissForReplacement();
                showMessage(activity, "二维码已失效", "请重新生成二维码后再试。", null);
                return;
            }
            accountUtils.checkLoginRequestStatus(requestId, new tAccUtils.CheckStatusCallback() {
                @Override public void onSuccess(String value) {
                    main(activity, () -> {
                        if (!listening[0]) return;
                        if ("approved".equals(value)) {
                            listening[0] = false;
                            dialog.dismiss();
                            accountUtils.setWebViewCookies(() -> main(activity, () -> {
                                if (onLoginChanged != null) onLoginChanged.run();
                            }));
                        } else if ("rejected".equals(value)) {
                            listening[0] = false;
                            dialog.dismissForReplacement();
                            showMessage(activity, "登录已拒绝", "已登录设备拒绝了本次请求。", null);
                        } else {
                            if ("waiting".equals(value)) {
                                qrCard.setVisibility(View.GONE);
                                status.setText("已扫码，请在另一台设备上确认");
                            }
                            handler.postDelayed(poll[0], 3000L);
                        }
                    });
                }

                @Override public void onError(String message) {
                    main(activity, () -> {
                        if (!listening[0]) return;
                        listening[0] = false;
                        dialog.dismissForReplacement();
                        showMessage(activity, "登录失败", message, null);
                    });
                }
            });
        };
        dialog.setOnDismissListener(ignored -> {
            listening[0] = false;
            handler.removeCallbacks(poll[0]);
            if (!bitmap.isRecycled()) bitmap.recycle();
        });
        dialog.show();
        handler.post(poll[0]);
    }

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

    private static String textOf(TextInputEditText input) {
        return input.getText() == null ? "" : input.getText().toString().trim();
    }

    private static void hideKeyboard(Activity activity, View view) {
        InputMethodManager manager = (InputMethodManager)
                activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (manager != null) manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private static void main(Activity activity, Runnable action) {
        activity.runOnUiThread(() -> {
            if (usable(activity)) action.run();
        });
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
