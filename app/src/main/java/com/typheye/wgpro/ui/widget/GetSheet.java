package com.typheye.wgpro.ui.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.tPointsUtils;

import org.json.JSONObject;

import java.util.List;

/**
 * 详情页底部动作里的「获取 / 操作」弹窗：统一的定价头部 + 分组列表。
 *
 * <p>头部 = 莫奈圆底 + 积分图标 + 「需消耗 x 积分」 + 「库存 | 账户可用 x 积分」；下面是分组条目。</p>
 */
public final class GetSheet {
    private GetSheet() { }

    /**
     * 收起软键盘并清掉焦点。
     * <p>底部弹窗里有输入框时，若在键盘还开着的时候弹下一个弹窗，新弹窗会被 IME 顶到几乎没有高度
     * （表现就是"窗口被挤掉"）——所以换弹窗前先收键盘。</p>
     */
    /**
     * 把动作推迟到"当前弹窗 dismiss 完成"之后执行。
     *
     * <p>{@code WGProAlertDialogBuilder} 的按钮回调是在 {@code dialog.dismiss()} **之前**调用的，
     * 而 {@code WGProBottomSheetDialog} 同时只允许一个弹窗——在回调里直接弹下一个会被静默丢弃
     * （历史表现：购买应用时「使用兑换代码 → 下一步」之后没有任何反应）。</p>
     */
    private static void afterDismiss(@NonNull Runnable action) {
        new android.os.Handler(android.os.Looper.getMainLooper()).post(action);
    }

    private static void hideIme(@NonNull Context context, @Nullable View anchor) {
        try {
            if (anchor != null) {
                android.view.inputmethod.InputMethodManager imm =
                        (android.view.inputmethod.InputMethodManager)
                                context.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(anchor.getWindowToken(), 0);
                }
                anchor.clearFocus();
            }
        } catch (Exception ignored) { }
    }

    /**
     * 头部视图：定价 + 库存与账户可用积分。
     *
     * <p>第二行**严格**按「有库存/低库存/无库存 | 账户可用 x 积分」格式。</p>
     *
     * @param price          本次获取需要消耗的积分（0 = 免费）
     * @param stockLabel     库存文案：有库存 / 低库存 / 无库存
     * @param availableTotal 账户可用积分（可用 + 赠送）
     */
    @NonNull
    public static View header(@NonNull Context context, int price, @NonNull String stockLabel,
                              int availableTotal) {
        LinearLayout box = new LinearLayout(context);
        box.setOrientation(LinearLayout.HORIZONTAL);
        box.setGravity(Gravity.CENTER_VERTICAL);
        box.setPadding(0, dp(context, 4), 0, dp(context, 16));

        FrameLayout avatar = new FrameLayout(context);
        GradientDrawable circle = new GradientDrawable();
        circle.setShape(GradientDrawable.OVAL);
        circle.setColor(WGProAlertDialogBuilder.resolveThemeColor(
                context, "colorPrimaryContainer", R.color.brand_soft));
        avatar.setBackground(circle);
        ImageView icon = new ImageView(context);
        icon.setImageResource(R.drawable.ic_points_vector);
        icon.setColorFilter(WGProAlertDialogBuilder.resolveThemeColor(
                context, "colorPrimary", R.color.brand_primary));
        int inner = dp(context, 13);
        icon.setPadding(inner, inner, inner, inner);
        avatar.addView(icon, new FrameLayout.LayoutParams(-1, -1));
        box.addView(avatar, new LinearLayout.LayoutParams(dp(context, 48), dp(context, 48)));

        LinearLayout texts = new LinearLayout(context);
        texts.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams textsParams = new LinearLayout.LayoutParams(0, -2, 1f);
        textsParams.setMarginStart(dp(context, 14));

        TextView priceText = new TextView(context);
        priceText.setText(price > 0 ? "需消耗 " + price + " 积分" : "免费获取");
        priceText.setTextSize(20);
        priceText.setTypeface(null, Typeface.BOLD);
        priceText.setTextColor(WGProAlertDialogBuilder.resolveThemeColor(
                context, "colorOnSurface", R.color.text_primary));
        texts.addView(priceText);

        // 第二行：库存状态 | 账户可用积分（格式固定，不要改动）
        TextView wallet = new TextView(context);
        wallet.setText(stockLabel + " | 账户可用 " + Math.max(0, availableTotal) + " 积分");
        wallet.setTextSize(14);
        wallet.setTextColor(WGProAlertDialogBuilder.resolveThemeColor(
                context, "colorOnSurfaceVariant", R.color.text_secondary));
        LinearLayout.LayoutParams walletParams = new LinearLayout.LayoutParams(-1, -2);
        walletParams.topMargin = dp(context, 3);
        texts.addView(wallet, walletParams);

        box.addView(texts, textsParams);
        return box;
    }

    /** 弹「获取」弹窗：定价头部 + 分组条目（使用积分支付 / 使用兑换代码 / 找他人代付）。 */
    public static void show(@NonNull Context context, int price, @NonNull String stockLabel,
                            int availableTotal, @NonNull List<String> labels,
                            @NonNull DialogInterface.OnClickListener listener) {
        new WGProAlertDialogBuilder(context)
                .setTitle("获取")
                .setView(header(context, price, stockLabel, availableTotal))
                .setItems(labels.toArray(new CharSequence[0]), listener)
                .setNegativeButton("关闭", null)
                .show();
    }

    /** 交易二次确认回调。 */
    public interface ConfirmCallback {
        void onConfirmed();
    }

    /**
     * 删除的二次确认（下载完成后右胶囊按钮）。
     * 删除 = 清掉下载记录 + 本地文件（已购买的不会因此重新扣费）。
     */
    public static void showDeleteConfirm(@NonNull Context context, @NonNull String targetName,
                                         @NonNull ConfirmCallback callback) {
        new WGProAlertDialogBuilder(context)
                .setTitle("删除")
                .setMessage("将删除「" + targetName + "」已下载的文件与下载记录。\n"
                        + "已购买的条目不会因此重新扣费，之后可以再次获取。")
                .setNegativeButton("取消", null)
                .setPositiveButton("删除", (dialog, which) ->
                        afterDismiss(callback::onConfirmed))
                .show();
    }

    /**
     * 交易的**二次确认**弹窗（第一步：确认要扣多少积分）。
     *
     * @param payMethod "balance"（积分支付，赠送优先）或 "redeem"（兑换代码支付）
     */
    public static void showConfirm(@NonNull Context context, @NonNull String targetName,
                                   int price, @NonNull String payMethod,
                                   @NonNull ConfirmCallback callback) {
        String detail = "redeem".equals(payMethod)
                ? "将使用兑换代码内的积分支付 " + price + " 积分，剩余部分存入可用积分。"
                : "将扣除 " + price + " 积分获取「" + targetName + "」。\n"
                        + "扣费优先使用赠送积分，剩余部分从可用积分中扣除。";
        new WGProAlertDialogBuilder(context)
                .setTitle("确认获取")
                .setMessage(detail)
                .setNegativeButton("取消", null)
                .setPositiveButton("确认", (dialog, which) ->
                        afterDismiss(callback::onConfirmed))
                .show();
    }

    /** 验证码确认回调。 */
    public interface VerifyCallback {
        void onVerified(@NonNull String code);
    }

    /**
     * 验证码弹窗（第二步：云端下发验证码，输入后完成交易）。
     *
     * @param txnNo       交易号（服务端 verify.send 返回）
     * @param debugCode   联调期服务端回显的验证码（正式上线后为 null，不预填）
     * @param expiresIn   有效期（秒），只用于文案
     */
    public static void showVerifyCode(@NonNull Context context, @NonNull String txnNo,
                                      @Nullable String debugCode, int expiresIn,
                                      @NonNull VerifyCallback callback) {
        View content = LayoutInflater.from(context).inflate(R.layout.dialog_edittext, null, false);
        android.widget.EditText input = content.findViewById(R.id.editText);
        input.setSingleLine(true);
        input.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
        input.setFilters(new android.text.InputFilter[]{new android.text.InputFilter.LengthFilter(6)});
        com.google.android.material.textfield.TextInputLayout inputLayout =
                content.findViewById(R.id.textInputLayout);
        if (inputLayout != null) {
            // 提示统一设置在 TextInputLayout 上（布局本身没有默认 hint）
            inputLayout.setHint("短信验证码");
        }
        int minutes = Math.max(1, Math.round(expiresIn / 60f));
        new WGProAlertDialogBuilder(context)
                .setTitle("输入验证码")
                .setMessage("验证码已发送，请在 " + minutes + " 分钟内完成验证。"
                        + (debugCode != null && !debugCode.isEmpty()
                        ? "\n（联调期验证码：" + debugCode + "）" : ""))
                .setView(content)
                .setNegativeButton("取消", null)
                .setPositiveButton("确认", (dialog, which) -> {
                    String code = input.getText() == null ? "" : input.getText().toString().trim();
                    if (code.isEmpty()) {
                        WGProHint.show(context, "请输入验证码");
                        return;
                    }
                    hideIme(context, input);
                    afterDismiss(() -> callback.onVerified(code));
                })
                .show();
    }

    /** 弹「操作」弹窗（已下载/已安装后的动作）。 */
    public static void showActions(@NonNull Context context, @NonNull List<String> labels,
                                   @NonNull DialogInterface.OnClickListener listener) {
        showActions(context, "操作", labels, listener);
    }

    /** 同上，可指定标题（详情页下载完成后用「使用」）。 */
    public static void showActions(@NonNull Context context, @NonNull String title,
                                   @NonNull List<String> labels,
                                   @NonNull DialogInterface.OnClickListener listener) {
        new WGProAlertDialogBuilder(context)
                .setTitle(title)
                .setItems(labels.toArray(new CharSequence[0]), listener)
                .show();
    }

    /** 兑换代码回调：把用户填的兑换码交回调用方（用于"兑换码直接支付商品"）。 */
    public interface RedeemCodeCallback {
        void onCode(@NonNull String code);
    }

    /**
     * 使用兑换代码支付商品：输入兑换码 → 二次确认 → 云端验证码 → 完成交易。
     * 这里只负责收集兑换码（校验/扣费由服务端在交易时完成）。
     */
    public static void showRedeem(@NonNull Context context, int price,
                                  @NonNull RedeemCodeCallback callback) {
        View content = LayoutInflater.from(context).inflate(R.layout.dialog_edittext, null, false);
        android.widget.EditText input = content.findViewById(R.id.editText);
        input.setSingleLine(true);
        input.setFilters(new android.text.InputFilter[]{
                new android.text.InputFilter.LengthFilter(64)});
        com.google.android.material.textfield.TextInputLayout inputLayout =
                content.findViewById(R.id.textInputLayout);
        if (inputLayout != null) {
            inputLayout.setHint("兑换代码");   // hint 只设在 TextInputLayout 上
        }
        new WGProAlertDialogBuilder(context)
                .setTitle("使用兑换代码")
                .setMessage("兑换代码内的积分将用于支付本单（" + price + " 积分），"
                        + "超出部分会存入可用积分；积分不足则无法使用该代码。")
                .setView(content)
                .setNegativeButton("取消", null)
                .setPositiveButton("下一步", (dialog, which) -> {
                    String code = input.getText() == null ? "" : input.getText().toString().trim();
                    if (code.isEmpty()) {
                        WGProHint.show(context, "请输入兑换代码");
                        return;
                    }
                    hideIme(context, input);
                    afterDismiss(() -> callback.onCode(code));
                })
                .show();
    }

    /** 兑换成功回调（积分已到账）。 */
    public interface RedeemCallback {
        void onRedeemed(int points);
    }

    /**
     * 兑换代码 → 直接充进积分（「积分」卡片里的兑换入口用）。
     * 失败（无效/已用/已过期/网络）统一用 {@link WGProHint} 提示，不关闭弹窗。
     */
    public static void showRedeem(@NonNull Context context, @Nullable RedeemCallback callback) {
        View content = LayoutInflater.from(context).inflate(R.layout.dialog_edittext, null, false);
        android.widget.EditText input = content.findViewById(R.id.editText);
        input.setSingleLine(true);
        // 提示挂在 TextInputLayout 上（布局里自带 hint="请输入内容"，直接改输入框的 hint 不生效）
        com.google.android.material.textfield.TextInputLayout inputLayout =
                content.findViewById(R.id.textInputLayout);
        if (inputLayout != null) {
            inputLayout.setHint("请输入兑换码");
            inputLayout.setHintTextAppearance(
                    com.google.android.material.R.style.TextAppearance_Material3_BodySmall);
        }
        new WGProAlertDialogBuilder(context)
                .setTitle("兑换积分")
                .setMessage("兑换成功后的积分可直接用于获取应用与资源。")
                .setView(content)
                .setPositiveButton("兑换", (dialog, which) -> {
                    String code = input.getText() == null ? "" : input.getText().toString().trim();
                    if (code.isEmpty()) {
                        WGProHint.show(context, "请输入兑换码");
                        return;
                    }
                    hideIme(context, input);
                    afterDismiss(() -> tPointsUtils.redeem(context, code, new tPointsUtils.JsonCallback() {
                        @Override
                        public void onSuccess(@NonNull JSONObject info) {
                            Points.apply(info.optInt("balance", Points.balance),
                                    info.optInt("gift", Points.gift));
                            int points = info.optInt("points", 0);
                            WGProHint.show(context, "兑换成功，+" + points + " 积分");
                            if (callback != null) callback.onRedeemed(points);
                        }

                        @Override
                        public void onError(int status, @NonNull String message,
                                            @Nullable JSONObject info) {
                            WGProHint.showError(context, "兑换失败", message);
                        }
                    }));
                })
                .setNegativeButton("关闭", null)
                .show();
    }

    private static int dp(Context context, int value) {
        return Math.round(value * context.getResources().getDisplayMetrics().density);
    }
}
