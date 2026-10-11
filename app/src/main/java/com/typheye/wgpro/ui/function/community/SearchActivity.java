package com.typheye.wgpro.ui.function.community;

import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.typheye.wgpro.R;

/**
 * 搜索页：复制浏览历史页的结构——应用栏里放一个可编辑搜索框（代替标题），
 * 下面是 动态 / 应用 / 资源 三个标签页，每页都是 {@link CloudListFragment} 的搜索模式。
 *
 * <p>数据来源：动态走社区接口 {@code dynamics2&q=}，资源走 {@code resources2&q=}（服务端已支持），
 * 应用直接读公开应用目录后在本地按关键词过滤。</p>
 */
public class SearchActivity extends BaseSectionActivity {
    public static final String EXTRA_QUERY = "query";
    private static final String ACTION_SEARCH = "搜索";

    private EditText searchInput;

    @Override protected String screenTitle() { return ""; }

    @Override protected Fragment createContent() {
        String initial = getIntent() == null ? "" : getIntent().getStringExtra(EXTRA_QUERY);
        return SearchPagerFragment.newInstance(initial == null ? "" : initial.trim());
    }

    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        // 应用栏三明治：[返回] [输入框] [搜索]（返回用原生导航图标位，搜索用应用栏菜单项）
        searchInput = new EditText(this);
        searchInput.setSingleLine(true);
        searchInput.setHint("搜索动态、应用、资源");
        searchInput.setHintTextColor(getColor(R.color.text_tertiary));
        searchInput.setTextColor(getColor(R.color.text_primary));
        searchInput.setTextSize(15f);
        searchInput.setGravity(Gravity.CENTER_VERTICAL);
        searchInput.setBackgroundResource(R.drawable.bg_home_search_box);
        int padH = dp(16);
        searchInput.setPadding(padH, 0, padH, 0);
        // 框内左侧放大镜（与占位文字同色）
        searchInput.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_search_vector, 0, 0, 0);
        searchInput.setCompoundDrawablePadding(dp(8));
        searchInput.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        searchInput.setInputType(android.text.InputType.TYPE_CLASS_TEXT);
        sectionToolbar().addView(searchInput, new Toolbar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, dp(40),
                Gravity.CENTER_VERTICAL));

        String initial = getIntent() == null ? null : getIntent().getStringExtra(EXTRA_QUERY);
        if (initial != null && !initial.trim().isEmpty()) {
            searchInput.setText(initial.trim());
            searchInput.setSelection(searchInput.getText().length());
        }
        searchInput.setOnEditorActionListener((v, actionId, event) -> {
            boolean enter = event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                    && event.getAction() == KeyEvent.ACTION_DOWN;
            if (actionId == EditorInfo.IME_ACTION_SEARCH || enter) {
                submitQuery();
                return true;
            }
            return false;
        });
        searchInput.postDelayed(() -> {
            if (isFinishing() || isDestroyed()) return;
            searchInput.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) imm.showSoftInput(searchInput, InputMethodManager.SHOW_IMPLICIT);
        }, 200);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add(ACTION_SEARCH);
        item.setIcon(R.drawable.ic_send_vector);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (ACTION_SEARCH.contentEquals(item.getTitle())) {
            submitQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** 应用栏右侧「搜索」按钮 / 键盘「搜索」键：提交关键词，并收起键盘。 */
    private void submitQuery() {
        if (searchInput == null) return;
        String query = searchInput.getText().toString().trim();
        Fragment content = getSupportFragmentManager().findFragmentById(R.id.section_container);
        if (content instanceof SearchPagerFragment) ((SearchPagerFragment) content).setQuery(query);
        searchInput.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) imm.hideSoftInputFromWindow(searchInput.getWindowToken(), 0);
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    /** 搜索页：动态 / 应用 / 资源 三个标签页（与浏览历史页同构）。 */
    public static final class SearchPagerFragment extends Fragment {
        private static final String ARG_QUERY = "query";
        private static final String[] TITLES = {"动态", "应用", "资源"};
        private static final String[] TYPES = {"dynamic", "app", "resource"};

        private TabLayoutMediator tabsMediator;
        private ViewPager2 pager;
        private String query = "";

        static SearchPagerFragment newInstance(String query) {
            SearchPagerFragment fragment = new SearchPagerFragment();
            Bundle args = new Bundle();
            args.putString(ARG_QUERY, query == null ? "" : query);
            fragment.setArguments(args);
            return fragment;
        }

        @Override public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
            View root = inflater.inflate(R.layout.fragment_search_pager, parent, false);
            query = getArguments() == null ? "" : getArguments().getString(ARG_QUERY, "");
            pager = root.findViewById(R.id.search_pager);
            pager.setAdapter(new FragmentStateAdapter(this) {
                @Override public int getItemCount() { return TYPES.length; }
                @Override public Fragment createFragment(int position) {
                    return CloudListFragment.newSearchPage(TYPES[position], query);
                }
            });
            pager.setOffscreenPageLimit(TYPES.length - 1);
            // 子标签挂在二级页应用栏里（与浏览历史页一致，共用毛玻璃）
            TabLayout tabs = ((BaseSectionActivity) requireActivity()).pageTabs();
            if (tabs != null) {
                tabs.setVisibility(View.VISIBLE);
                tabsMediator = new TabLayoutMediator(tabs, pager,
                        (tab, position) -> tab.setText(TITLES[position]));
                tabsMediator.attach();
            }
            return root;
        }

        @Override public void onDestroyView() {
            if (tabsMediator != null) {
                tabsMediator.detach();
                tabsMediator = null;
            }
            pager = null;
            TabLayout tabs = ((BaseSectionActivity) requireActivity()).pageTabs();
            if (tabs != null) {
                tabs.removeAllTabs();
                tabs.setVisibility(View.GONE);
            }
            super.onDestroyView();
        }

        /** 关键词变化：让三个标签页各自重新搜索。 */
        void setQuery(String value) {
            query = value == null ? "" : value.trim();
            for (Fragment fragment : getChildFragmentManager().getFragments()) {
                if (fragment instanceof CloudListFragment) {
                    ((CloudListFragment) fragment).setSearchQuery(query);
                }
            }
        }
    }
}
