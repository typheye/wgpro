package com.typheye.wgpro.ui.function.community;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.utils.tAccUtils;

import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class ComposerActivity extends BaseSectionActivity {
    public static final String EXTRA_MODE = "mode";
    public static final String MODE_DYNAMIC = "dynamic";
    public static final String MODE_RESOURCE = "resource";
    @Override protected String screenTitle() {
        return MODE_RESOURCE.equals(getIntent().getStringExtra(EXTRA_MODE)) ? "发布资源" : "分享动态";
    }
    @Override protected Fragment createContent() {
        return MODE_RESOURCE.equals(getIntent().getStringExtra(EXTRA_MODE))
                ? new ResourceComposerFragment() : new DynamicComposerFragment();
    }

    public abstract static class ComposerFragment extends Fragment {
        private final Handler main = new Handler(Looper.getMainLooper());
        protected TextInputEditText title, summary, content;
        protected MaterialButton submit;
        protected TextView note;
        protected tAccUtils account;

        @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater,
                                                      @Nullable ViewGroup container,
                                                      @Nullable Bundle state) {
            View root = inflater.inflate(R.layout.fragment_composer, container, false);
            account = new tAccUtils(requireContext().getApplicationContext());
            title = root.findViewById(R.id.composer_title);
            summary = root.findViewById(R.id.composer_summary);
            content = root.findViewById(R.id.composer_content);
            submit = root.findViewById(R.id.composer_submit);
            note = root.findViewById(R.id.composer_note);
            configure(root);
            submit.setOnClickListener(v -> submit());
            return root;
        }

        protected abstract void configure(View root);
        protected abstract String action();
        protected abstract Map<String, String> fields();

        private void submit() {
            if (!account.isLogin()) { showResult("请先登录 Typheye 账户"); return; }
            Map<String, String> values = fields();
            if (values == null) return;
            submit.setEnabled(false);
            long started = android.os.SystemClock.uptimeMillis();
            account.postV2Json(action(), values, new tAccUtils.JsonCallback() {
                @Override public void onSuccess(@NonNull JSONObject json) {
                    finishAfter(started, json.optString("msg", successText()), true);
                }
                @Override public void onError(int code, @NonNull String message) {
                    finishAfter(started, message, false);
                }
            });
        }

        private void finishAfter(long started, String message, boolean success) {
            long delay = Math.max(0L, 300L - (android.os.SystemClock.uptimeMillis() - started));
            main.postDelayed(() -> {
                if (!isAdded()) return;
                submit.setEnabled(true);
                new WGProAlertDialogBuilder(requireContext())
                        .setTitle(success ? "已完成" : "发布失败").setMessage(message)
                        .setPositiveButton("完成", (d, w) -> { if (success) requireActivity().finish(); })
                        .show();
            }, delay);
        }

        protected String value(TextInputEditText input) {
            return input.getText() == null ? "" : input.getText().toString().trim();
        }
        protected void showResult(String message) {
            new WGProAlertDialogBuilder(requireContext()).setTitle("无法发布").setMessage(message)
                    .setNegativeButton("关闭", null).show();
        }
        protected String successText() { return "发布成功"; }
    }

    public static final class DynamicComposerFragment extends ComposerFragment {
        @Override protected void configure(View root) {
            note.setText("支持最多 5000 字。图片将在后续媒体选择器中提供。");
            submit.setText("发布动态");
        }
        @Override protected String action() { return "dynamic_create2"; }
        @Override protected Map<String, String> fields() {
            String body = value(content);
            if (body.isEmpty()) { content.setError("请输入动态内容"); return null; }
            Map<String, String> values = new LinkedHashMap<>();
            values.put("content", body); values.put("visibility", "public"); values.put("metadata", "{}");
            return values;
        }
    }

    public static final class ResourceComposerFragment extends ComposerFragment {
        @Override protected void configure(View root) {
            root.findViewById(R.id.composer_title_layout).setVisibility(View.VISIBLE);
            root.findViewById(R.id.composer_summary_layout).setVisibility(View.VISIBLE);
            note.setText("先创建资源草稿；添加文件和封面后才能提交审核。");
            submit.setText("创建资源草稿");
        }
        @Override protected String action() { return "resource_create2"; }
        @Override protected Map<String, String> fields() {
            String titleValue = value(title), summaryValue = value(summary), body = value(content);
            if (titleValue.isEmpty()) { title.setError("请输入标题"); return null; }
            if (summaryValue.isEmpty()) { summary.setError("请输入摘要"); return null; }
            Map<String, String> values = new LinkedHashMap<>();
            values.put("title", titleValue); values.put("summary", summaryValue);
            values.put("description", body); values.put("category", "other"); values.put("metadata", "{}");
            return values;
        }
        @Override protected String successText() { return "资源草稿已创建"; }
    }
}
