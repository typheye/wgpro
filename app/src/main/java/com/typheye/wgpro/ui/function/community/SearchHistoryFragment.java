package com.typheye.wgpro.ui.function.community;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.tAccUtils;

import java.util.List;

/**
 * 搜索页默认页：搜索历史（竖向列表，行尾 ✕ 删单条；顶部「清空」删全部）。
 * 空态用 {@code view_stream_empty}（只 include，不改动）。
 *
 * <p>整页放在一个 {@code NestedScrollView} 里，应用栏/系统栏的上下留白由它消化，
 * 头部与空态都在这条滚动流里，不会被应用栏盖住。</p>
 */
public final class SearchHistoryFragment extends Fragment {
    private SearchHistoryStore store;
    private LinearLayout list;
    private View state;
    private TextView stateTitle;
    private TextView stateDescription;

    @Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle saved) {
        View root = inflater.inflate(R.layout.fragment_search_history, parent, false);
        store = new SearchHistoryStore(requireContext(), new tAccUtils(requireContext()).getUid());
        state = root.findViewById(R.id.history_state);
        stateTitle = state.findViewById(R.id.stream_empty_title);
        stateDescription = state.findViewById(R.id.stream_empty_description);
        list = root.findViewById(R.id.history_list);
        render();
        return root;
    }

    @Override public void onResume() {
        super.onResume();
        render();
    }

    private void render() {
        if (list == null) return;
        List<String> items = store.list();
        list.removeAllViews();
        for (String query : items) list.addView(createRow(query));
        boolean empty = items.isEmpty();
        state.setVisibility(empty ? View.VISIBLE : View.GONE);
        stateTitle.setText("还没有搜索记录");
        stateDescription.setText("搜索过的关键词会显示在这里。");
    }

    private View createRow(@NonNull String query) {
        com.google.android.material.card.MaterialCardView card =
                new com.google.android.material.card.MaterialCardView(requireContext());
        card.setCardBackgroundColor(com.typheye.wgpro.ui.widget.WGProColors.of(requireContext(), R.color.surface_secondary));
        card.setStrokeWidth(0);
        card.setCardElevation(0);
        card.setRadius(dp(12));
        card.setClickable(true);
        card.setFocusable(true);
        card.setRippleColor(android.content.res.ColorStateList.valueOf(
                com.typheye.wgpro.ui.widget.WGProColors.of(requireContext(), R.color.brand_soft)));
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(-1, dp(52));
        cardParams.setMarginStart(dp(16));
        cardParams.setMarginEnd(dp(16));
        cardParams.bottomMargin = dp(8);
        card.setLayoutParams(cardParams);

        LinearLayout row = new LinearLayout(requireContext());
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(dp(16), 0, dp(8), 0);

        TextView text = new TextView(requireContext());
        text.setText(query);
        text.setSingleLine(true);
        text.setEllipsize(android.text.TextUtils.TruncateAt.END);
        text.setTextColor(com.typheye.wgpro.ui.widget.WGProColors.of(requireContext(), R.color.text_primary));
        text.setTextSize(15f);
        row.addView(text, new LinearLayout.LayoutParams(0, -2, 1f));

        ImageView clear = new ImageView(requireContext());
        clear.setImageResource(R.drawable.ic_close_vector);
        clear.setColorFilter(com.typheye.wgpro.ui.widget.WGProColors.of(requireContext(), R.color.text_tertiary));
        clear.setContentDescription("删除");
        clear.setBackgroundResource(R.drawable.bg_list_item_ripple);
        int pad = dp(9);
        clear.setPadding(pad, pad, pad, pad);
        row.addView(clear, new LinearLayout.LayoutParams(dp(38), dp(38)));

        card.addView(row);
        card.setOnClickListener(v -> search(query));
        clear.setOnClickListener(v -> {
            store.remove(query);
            render();
        });
        return card;
    }

    private void search(@NonNull String query) {
        if (getActivity() instanceof SearchActivity) ((SearchActivity) getActivity()).runSearch(query);
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
