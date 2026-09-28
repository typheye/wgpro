package com.typheye.wgpro.ui.function.debug;

import android.text.InputType;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProBottomSheetDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 调试页「启动底部弹窗」：用 {@link WGProAlertDialogBuilder} 演示应用里各种形态的底部弹窗，
 * 均以最小配置弹出（并且都保证有办法关闭，不会把调试页卡住）。
 */
public final class DebugBottomSheetListFragment extends DebugListFragment {

    @Override protected List<Row> buildRows() {
        List<Row> rows = new ArrayList<>();
        add(rows, "标题 + 文字 + 确定/取消", "最标准的两按钮弹窗", () ->
                new WGProAlertDialogBuilder(requireContext())
                        .setTitle("底部弹窗示例")
                        .setMessage("这是标准的「标题 + 内容 + 确定/取消」底部弹窗。")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", (d, w) -> toast("点了确定"))
                        .show());
        add(rows, "标题 + 文字 + 单个按钮", "只有一个确认出口", () ->
                new WGProAlertDialogBuilder(requireContext())
                        .setTitle("提示")
                        .setMessage("只有一个「知道了」按钮。")
                        .setPositiveButton("知道了", null)
                        .show());
        add(rows, "标题 + 文字 + 三个按钮", "确定 / 取消 / 稍后", () ->
                new WGProAlertDialogBuilder(requireContext())
                        .setTitle("三个按钮")
                        .setMessage("演示 neutral / negative / positive 三个动作。")
                        .setNeutralButton("稍后", (d, w) -> toast("稍后"))
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", (d, w) -> toast("确定"))
                        .show());
        add(rows, "无标题 + 文字 + 确定", "只有正文", () ->
                new WGProAlertDialogBuilder(requireContext())
                        .setMessage("没有标题，只有一段正文和一个确定按钮。")
                        .setPositiveButton("确定", null)
                        .show());
        add(rows, "列表选择", "标题 + 选项列表", () ->
                new WGProAlertDialogBuilder(requireContext())
                        .setTitle("请选择一项")
                        .setItems(new CharSequence[]{"选项一", "选项二", "选项三"},
                                (d, which) -> toast("选择了第 " + (which + 1) + " 项"))
                        .setNegativeButton("取消", null)
                        .show());
        add(rows, "带输入框", "标题 + 文本输入 + 确定", () -> {
            View content = getLayoutInflater().inflate(R.layout.dialog_edittext, null, false);
            TextInputLayout inputLayout = content.findViewById(R.id.textInputLayout);
            TextInputEditText input = content.findViewById(R.id.editText);
            inputLayout.setHint("随便输入点什么");
            input.setSingleLine(true);
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            new WGProAlertDialogBuilder(requireContext())
                    .setTitle("输入框弹窗")
                    .setView(content)
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确定", (d, w) -> toast("输入：" +
                            (input.getText() == null ? "" : input.getText().toString())))
                    .show();
        });
        add(rows, "进度（不可取消）", "1.5 秒后自动关闭", () -> {
            View content = View.inflate(requireContext(), R.layout.progress_dialog, null);
            TextView message = content.findViewById(android.R.id.message);
            if (message != null) message.setText("正在处理…");
            final WGProBottomSheetDialog dialog = new WGProAlertDialogBuilder(requireContext())
                    .setTitle("处理中")
                    .setView(content)
                    .setCancelable(false)
                    .create();
            dialog.show();
            content.postDelayed(() -> {
                if (dialogsAlive()) dialog.dismiss();
            }, 1500L);
        });
        add(rows, "长内容（可滚动）", "正文很长时内容区可滚动", () -> {
            StringBuilder text = new StringBuilder();
            for (int i = 1; i <= 40; i++) text.append("第 ").append(i).append(" 行：用于测试底部弹窗内容区滚动。\n");
            new WGProAlertDialogBuilder(requireContext())
                    .setTitle("长内容")
                    .setMessage(text.toString().trim())
                    .setPositiveButton("关闭", null)
                    .show();
        });
        add(rows, "点外部/返回不关闭", "只能点按钮关闭", () ->
                new WGProAlertDialogBuilder(requireContext())
                        .setTitle("不可取消")
                        .setMessage("点击弹窗外或按返回键都不会关闭，只能点「关闭」。")
                        .setCancelable(false)
                        .setPositiveButton("关闭", null)
                        .show());
        add(rows, "纯提示（无按钮）", "点外部关闭", () ->
                new WGProAlertDialogBuilder(requireContext())
                        .setTitle("纯提示")
                        .setMessage("没有按钮，点击空白处或返回键关闭。")
                        .show());
        return rows;
    }

    private boolean dialogsAlive() {
        return isAdded() && getActivity() != null && !getActivity().isFinishing();
    }

    private void toast(String message) {
        if (isAdded()) Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
