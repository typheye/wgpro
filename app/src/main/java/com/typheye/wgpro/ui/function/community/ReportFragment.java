package com.typheye.wgpro.ui.function.community;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.radiobutton.MaterialRadioButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProBottomSheetDialog;
import com.typheye.wgpro.ui.widget.WGProProgressRunner;
import com.typheye.wgpro.utils.tAccUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 统一举报页的通用 Fragment。具体举报对象由 ReportActivity 选择对应子类。
 */
public class ReportFragment extends Fragment {
    private static final String[] REASON_TITLES = {
            "垃圾信息", "骚扰或辱骂", "欺诈或虚假信息", "其他"
    };
    private static final String[] REASON_DESCRIPTIONS = {
            "广告、刷屏、无意义内容",
            "攻击、辱骂、恶意骚扰",
            "诈骗、虚假身份或误导信息",
            "其他违反社区规范的内容"
    };
    private static final String[] REASON_CODES = {
            "spam", "harassment", "fraud", "other"
    };

    private final List<MaterialRadioButton> reasonButtons = new ArrayList<>();
    private LinearLayout reasonContainer;
    private String descriptionValue = "";
    private String contactValue = "";
    private MaterialButton submit;
    private tAccUtils account;
    private int selectedReason = -1;

    protected String targetType() { return argument("target_type"); }
    protected String targetKey() { return argument("target_key"); }
    protected String targetTitle() { return argument("target_title"); }
    protected String targetLabel() { return "举报"; }

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment_report, container, false);
        account = new tAccUtils(requireContext().getApplicationContext());
        reasonContainer = root.findViewById(R.id.report_reasons);
        root.findViewById(R.id.report_extra_description_row)
                .setOnClickListener(v -> showExtraEditor(false));
        root.findViewById(R.id.report_extra_contact_row)
                .setOnClickListener(v -> showExtraEditor(true));
        submit = root.findViewById(R.id.report_submit);
        ((TextView) root.findViewById(R.id.report_target_label)).setText(targetLabel());
        ((TextView) root.findViewById(R.id.report_target)).setText(targetSummary());
        buildReasonRows();
        submit.setOnClickListener(v -> submit());
        return root;
    }

    private String targetSummary() {
        String title = targetTitle();
        String key = targetKey();
        String value = title == null || title.trim().isEmpty() ? key : title.trim();
        return value == null || value.trim().isEmpty() ? "未命名对象" : value.trim();
    }

    private void buildReasonRows() {
        reasonContainer.removeAllViews();
        reasonButtons.clear();
        for (int i = 0; i < REASON_TITLES.length; i++) {
            final int index = i;
            LinearLayout row = new LinearLayout(requireContext());
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setGravity(Gravity.CENTER_VERTICAL);
            row.setMinimumHeight(dp(72));
            row.setPadding(dp(24), dp(10), dp(16), dp(10));
            row.setBackgroundResource(R.drawable.bg_list_item_ripple);

            MaterialRadioButton radio = new MaterialRadioButton(requireContext());
            radio.setClickable(false);
            radio.setFocusable(false);
            radio.setMinWidth(0);
            radio.setMinHeight(0);
            radio.setPadding(0, 0, 0, 0);
            radio.setGravity(Gravity.CENTER);
            radio.setButtonTintList(AppCompatResources.getColorStateList(
                    requireContext(), R.color.report_radio_colors));
            row.addView(radio, new LinearLayout.LayoutParams(dp(32), dp(32)));

            LinearLayout labels = new LinearLayout(requireContext());
            labels.setOrientation(LinearLayout.VERTICAL);
            TextView title = new TextView(requireContext());
            title.setText(REASON_TITLES[i]);
            title.setTextSize(16);
            title.setTextColor(color(R.color.text_primary));
            title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
            TextView detail = new TextView(requireContext());
            detail.setText(REASON_DESCRIPTIONS[i]);
            detail.setTextSize(13);
            detail.setTextColor(color(R.color.text_secondary));
            detail.setLineSpacing(0f, 1.1f);
            detail.setPadding(0, dp(3), 0, 0);
            labels.addView(title);
            labels.addView(detail);
            LinearLayout.LayoutParams labelParams = new LinearLayout.LayoutParams(0, -2, 1f);
            labelParams.setMarginStart(dp(12));
            row.addView(labels, labelParams);

            row.setOnClickListener(v -> {
                selectedReason = index;
                updateReasonRows();
            });
            reasonContainer.addView(row, new LinearLayout.LayoutParams(-1, -2));
            if (i < REASON_TITLES.length - 1) {
                View divider = new View(requireContext());
                divider.setBackgroundColor(color(R.color.outline_soft));
                LinearLayout.LayoutParams dividerParams = new LinearLayout.LayoutParams(-1, dp(1));
                dividerParams.setMarginStart(dp(64));
                reasonContainer.addView(divider, dividerParams);
            }
            reasonButtons.add(radio);
        }
    }

    private void updateReasonRows() {
        for (int i = 0; i < reasonButtons.size(); i++) {
            reasonButtons.get(i).setChecked(i == selectedReason);
        }
    }

    private void showExtraEditor(boolean contact) {
        View content = LayoutInflater.from(requireContext())
                .inflate(R.layout.dialog_edittext, null, false);
        TextInputLayout layout = content.findViewById(R.id.textInputLayout);
        TextInputEditText input = content.findViewById(R.id.editText);
        layout.setHint(contact ? "联系方式" : "补充说明");
        input.setText(contact ? contactValue : descriptionValue);
        input.setSelection(input.length());
        input.setSingleLine(contact);
        input.setMaxLines(contact ? 1 : 5);
        input.setInputType(contact
                ? android.text.InputType.TYPE_CLASS_TEXT
                | android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                : android.text.InputType.TYPE_CLASS_TEXT
                | android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        WGProBottomSheetDialog dialog = new WGProAlertDialogBuilder(requireContext())
                .setTitle(contact ? "填写联系方式" : "填写补充说明")
                .setView(content).setNegativeButton("取消", null)
                .setPositiveButton("保存", null).create();
        dialog.setOnShowListener(ignored ->
                dialog.getButton(android.content.DialogInterface.BUTTON_POSITIVE)
                        .setOnClickListener(v -> {
                            String value = input.getText() == null ? ""
                                    : input.getText().toString().trim();
                            if (contact) {
                                contactValue = value;
                            } else {
                                descriptionValue = value;
                            }
                            dialog.dismiss();
                        }));
        dialog.show();
    }

    private void submit() {
        String key = targetKey();
        if (key == null || key.trim().isEmpty()) {
            showResult("无法举报", "举报对象标识无效，请返回后重试。", false);
            return;
        }
        if ("user".equals(targetType()) && key.trim().equals(account.getUid())) {
            showResult("不能举报自己", "不能举报自己的账号。", true);
            return;
        }
        if (selectedReason < 0 || selectedReason >= REASON_CODES.length) {
            showResult("请选择举报原因", "请先选择一条举报原因。", false);
            return;
        }
        String detail = descriptionValue == null ? "" : descriptionValue.trim();
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("target_type", targetType());
        fields.put("target_key", key.trim());
        fields.put("reason_code", REASON_CODES[selectedReason]);
        if (!detail.isEmpty()) fields.put("description", detail);
        if (contactValue != null && !contactValue.trim().isEmpty()) {
            fields.put("contact", contactValue.trim());
        }
        WGProProgressRunner.run(this, "提交中", "正在提交举报...", completion ->
                account.postV2Json("report_create2", fields, new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        completion.success(json);
                    }

                    @Override public void onError(int code, @NonNull String message) {
                        completion.error(code, message);
                    }
                }), new WGProProgressRunner.Callback() {
            @Override public void success(@NonNull JSONObject json) {
                showResult("举报已提交", "感谢反馈，我们会尽快处理。", true);
            }

            @Override public void error(int code, @NonNull String message) {
                showResult("举报失败", message == null || message.trim().isEmpty()
                        ? "请稍后重试" : message, false);
            }
        });
    }

    private void showResult(String title, String message, boolean finish) {
        if (!isAdded()) return;
        new WGProAlertDialogBuilder(requireContext()).setTitle(title).setMessage(message)
                .setNegativeButton("关闭", (dialog, which) -> {
                    if (finish && isAdded()) requireActivity().finish();
                }).show();
    }

    private String argument(String key) {
        Bundle args = getArguments();
        return args == null ? "" : args.getString(key, "");
    }

    private int color(int id) { return requireContext().getColor(id); }
    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
