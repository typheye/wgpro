package com.typheye.wgpro;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.preference.EditTextPreference;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class CustomEditTextPreference extends EditTextPreference {

    public CustomEditTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onClick() {
        Context context = getContext();

        // ✅ 1. 加载布局
        View view = View.inflate(context, R.layout.dialog_edittext, null);

        TextInputLayout textInputLayout = view.findViewById(R.id.textInputLayout);
        TextInputEditText editText = view.findViewById(R.id.editText);

        // ✅ 2. 动态设置属性
        textInputLayout.setHint(getTitle() != null ? getTitle().toString() : "请输入");
        editText.setText(getText());
        editText.setSingleLine();

        // ✅ 3. 兼容性：autofillHints
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            editText.setAutofillHints(getKey());
        }

        // ✅ 4. 显示对话框
        new MaterialAlertDialogBuilder(context)
                .setTitle(getDialogTitle())
                .setView(view)  // ✅ 直接传入整个 View
                .setPositiveButton(getPositiveButtonText(), (dialog, which) -> {
                    String value = Objects.requireNonNull(editText.getText()).toString();
                    if (callChangeListener(value)) {
                        setText(value);
                    }
                })
                .setNegativeButton(getNegativeButtonText(), null)
                .show();
    }
}