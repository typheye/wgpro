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

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.tAccUtils;

/**
 * 搜索页，应用栏是「[返回] [输入框] [搜索/关闭]」三明治结构，内容区两个 Fragment 二选一：
 * <ul>
 *     <li>默认：{@link SearchHistoryFragment}（搜索历史，空态用 view_stream_empty）</li>
 *     <li>提交后：{@code SearchPagerFragment}（动态 / 应用 / 资源 / 用户 四个标签页）</li>
 * </ul>
 * 结果态右上角是「X」：点了回历史页并清空输入框；结果态点输入框也会回历史页（但保留文字）。
 */
public class SearchActivity extends BaseSectionActivity {
    public static final String EXTRA_QUERY = "query";
    /** 应用栏右侧动作（搜索 / 关闭）的菜单 id。 */
    private static final int ID_ACTION = 0x0F0F0001;

    private EditText searchInput;
    /** true = 当前显示结果页（右上角是 X），false = 当前显示历史页（右上角是飞机）。 */
    private boolean showingResults;
    /** 深链/调试带进来的关键词（进页面就直接搜）。 */
    private String pendingQuery = "";
    /** 结果页当前所在标签：同一次搜索会话内记住，二次搜索默认回到这个标签。 */
    private int lastResultTab;

    @Override protected String screenTitle() { return ""; }

    @Override protected Fragment createContent() {
        // 带关键词直接进来（深链/adb 调试）：直接落到结果页
        String initial = getIntent() == null ? "" : getIntent().getStringExtra(EXTRA_QUERY);
        if (initial == null) initial = "";
        initial = initial.trim();
        if (!initial.isEmpty()) {
            pendingQuery = initial;
            showingResults = true;
            return SearchPagerFragment.newInstance(initial, lastResultTab);
        }
        return new SearchHistoryFragment();
    }

    @Override protected void onCreate(Bundle state) {
        super.onCreate(state);
        searchInput = new EditText(this);
        searchInput.setSingleLine(true);
        searchInput.setHint("搜索从这里开始");
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

        // 结果态点输入框 → 自动回历史页（等价点一次 X，但不清空文字，方便改词重搜）
        searchInput.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus && showingResults) showHistory(false);
        });
        searchInput.setOnEditorActionListener((v, actionId, event) -> {
            boolean enter = event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                    && event.getAction() == KeyEvent.ACTION_DOWN;
            if (actionId == EditorInfo.IME_ACTION_SEARCH || enter) {
                runSearch(searchInput.getText().toString());
                return true;
            }
            return false;
        });
        searchInput.postDelayed(() -> {
            if (isFinishing() || isDestroyed()) return;
            if (!pendingQuery.isEmpty()) {
                // 深链进来：填好关键词、记录历史、不自动弹键盘（直接看结果）
                searchInput.setText(pendingQuery);
                searchInput.setSelection(pendingQuery.length());
                new SearchHistoryStore(this, new tAccUtils(getApplicationContext()).getUid()).add(pendingQuery);
                pendingQuery = "";
                return;
            }
            searchInput.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            if (imm != null) imm.showSoftInput(searchInput, InputMethodManager.SHOW_IMPLICIT);
        }, 200);

        // 返回键：结果态先回历史页，历史态才退出搜索页
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override public void handleOnBackPressed() {
                if (showingResults) {
                    showHistory(false);
                    return;
                }
                setEnabled(false);
                getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, ID_ACTION, Menu.NONE, "搜索");
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(ID_ACTION);
        if (item != null) {
            if (showingResults) {
                item.setIcon(R.drawable.ic_close_vector);
                item.setTitle("关闭");
            } else {
                item.setIcon(R.drawable.ic_send_vector);
                item.setTitle("搜索");
            }
            item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == ID_ACTION) {
            if (showingResults) showHistory(true);
            else runSearch(searchInput == null ? "" : searchInput.getText().toString());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** 提交搜索：记录历史 + 切到结果页。关键词为空时弹底部提示，不切页。 */
    void runSearch(String query) {
        String value = query == null ? "" : query.trim();
        if (value.isEmpty()) {
            // 用 app 自己的底部弹窗规范（WGProAlertDialogBuilder），不要 MD 原生按钮样式
            new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(this)
                    .setTitle("请输入关键词")
                    .setMessage("输入要搜索的内容后再点搜索。")
                    .setNegativeButton("知道了", null)
                    .show();
            return;
        }
        if (searchInput != null) {
            searchInput.setText(value);
            searchInput.setSelection(value.length());
            searchInput.clearFocus();
        }
        hideKeyboard();
        new SearchHistoryStore(this, new tAccUtils(getApplicationContext()).getUid()).add(value);
        // 立刻切到「结果态」：右上角变 X + 立刻显示加载遮罩。
        // 这里在点击瞬间记下遮罩起点，保证无论接口多快，遮罩都至少停留 300ms（不闪）。
        showContentLoading();
        showingResults = true;
        invalidateOptionsMenu();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.section_container, SearchPagerFragment.newInstance(value, lastResultTab))
                .commitAllowingStateLoss();
    }

    /** 结果页切换标签时回传，供二次搜索时默认回到同一标签。 */
    void onResultTabChanged(int index) {
        lastResultTab = Math.max(0, index);
    }

    /** 回历史页。{@code clearText=true}（点 X）= 顺带清空输入框并收键盘。 */
    private void showHistory(boolean clearText) {
        showingResults = false;
        invalidateOptionsMenu();
        // 结果页可能还在加载：这里主动收起遮罩，避免刚发出搜索就点 X 时遮罩永远留着
        hideContentLoading();
        if (clearText && searchInput != null) {
            searchInput.setText("");
            searchInput.clearFocus();
            hideKeyboard();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.section_container, new SearchHistoryFragment())
                .commitAllowingStateLoss();
    }

    private void hideKeyboard() {
        if (searchInput == null) return;
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) imm.hideSoftInputFromWindow(searchInput.getWindowToken(), 0);
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    /** 结果页：动态 / 应用 / 资源 / 用户 四个标签页。 */
    public static final class SearchPagerFragment extends Fragment {
        private static final String ARG_QUERY = "query";
        private static final String ARG_TAB = "tab";
        private static final String[] TITLES = {"动态", "应用", "资源", "用户"};
        private static final String[] TYPES = {"dynamic", "app", "resource", "user"};

        private TabLayoutMediator tabsMediator;
        private ViewPager2 pager;
        private String query = "";
        private int initialTab;

        static SearchPagerFragment newInstance(String query, int tab) {
            SearchPagerFragment fragment = new SearchPagerFragment();
            Bundle args = new Bundle();
            args.putString(ARG_QUERY, query == null ? "" : query);
            args.putInt(ARG_TAB, Math.max(0, tab));
            fragment.setArguments(args);
            return fragment;
        }

        @Override public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle state) {
            View root = inflater.inflate(R.layout.fragment_search_pager, parent, false);
            query = getArguments() == null ? "" : getArguments().getString(ARG_QUERY, "");
            initialTab = getArguments() == null ? 0 : getArguments().getInt(ARG_TAB, 0);
            pager = root.findViewById(R.id.search_pager);
            pager.setAdapter(new FragmentStateAdapter(this) {
                @Override public int getItemCount() { return TYPES.length; }
                @Override public Fragment createFragment(int position) {
                    return CloudListFragment.newSearchPage(TYPES[position], query);
                }
            });
            pager.setOffscreenPageLimit(TYPES.length - 1);
            // 二次搜索默认回到上次所在标签：初始选中 + 把切换回传给 Activity 记住
            pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override public void onPageSelected(int position) {
                    if (getActivity() instanceof SearchActivity) {
                        ((SearchActivity) getActivity()).onResultTabChanged(position);
                    }
                }
            });
            pager.setCurrentItem(Math.min(initialTab, TYPES.length - 1), false);
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
    }
}
