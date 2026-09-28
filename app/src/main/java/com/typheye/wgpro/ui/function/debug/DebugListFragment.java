package com.typheye.wgpro.ui.function.debug;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.SystemBars;

import java.util.List;

/**
 * 调试页「界面」分组里的可用列表基类：一行一个可点击项（标题 + 说明 + 箭头）。
 * 子类只需给出 {@link Row} 列表。列表自带应用栏顶部留白与底部系统栏内边距，
 * 从调试页的毛玻璃应用栏下方穿过。
 */
public abstract class DebugListFragment extends Fragment {

    /** 一行数据。 */
    protected static final class Row {
        final CharSequence title;
        final CharSequence subtitle;
        final Runnable action;

        Row(CharSequence title, CharSequence subtitle, @Nullable Runnable action) {
            this.title = title;
            this.subtitle = subtitle;
            this.action = action;
        }
    }

    protected abstract List<Row> buildRows();

    /** 便捷添加一行。 */
    protected final void add(List<Row> rows, CharSequence title, CharSequence subtitle,
                             @Nullable Runnable action) {
        rows.add(new Row(title, subtitle, action));
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater,
                                                 @Nullable ViewGroup container,
                                                 @Nullable Bundle state) {
        View root = inflater.inflate(R.layout.fragment_debug_list, container, false);
        LinearLayout list = root.findViewById(R.id.debug_list);
        for (Row row : buildRows()) {
            list.addView(createRow(inflater, list, row));
        }
        applyBarInsets(root);
        return root;
    }

    private View createRow(LayoutInflater inflater, ViewGroup parent, Row row) {
        View item = inflater.inflate(R.layout.item_debug_row, parent, false);
        ((TextView) item.findViewById(R.id.debug_row_title)).setText(row.title);
        TextView subtitle = item.findViewById(R.id.debug_row_subtitle);
        if (row.subtitle == null || row.subtitle.length() == 0) {
            subtitle.setVisibility(View.GONE);
        } else {
            subtitle.setText(row.subtitle);
        }
        item.findViewById(R.id.debug_row_click).setOnClickListener(v -> {
            if (row.action != null) row.action.run();
        });
        return item;
    }

    /** 列表容器铺满整屏：顶部留出应用栏高度、底部预留系统栏，滚动内容从毛玻璃下穿过。 */
    private void applyBarInsets(View root) {
        final androidx.core.widget.NestedScrollView scroll = root.findViewById(R.id.debug_scroll);
        final View appBar = requireActivity().findViewById(R.id.test_app_bar);
        scroll.setClipToPadding(false);
        // 上下内边距在同一个 insets 监听里处理，避免和其它底部留白逻辑互相把对方的 padding 覆盖掉。
        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(scroll, (view, insets) -> {
            int top = appBar == null ? 0 : appBar.getHeight();
            int bottom = SystemBars.bottomInsetForView(view, insets);
            if (view.getPaddingTop() != top || view.getPaddingBottom() != bottom) {
                view.setPadding(view.getPaddingLeft(), top, view.getPaddingRight(), bottom);
            }
            return insets;
        });
        // 应用栏高度确定后重新派发一次，确保顶部留白正确。
        root.getViewTreeObserver().addOnGlobalLayoutListener(
                () -> androidx.core.view.ViewCompat.requestApplyInsets(scroll));
        androidx.core.view.ViewCompat.requestApplyInsets(scroll);
    }
}
