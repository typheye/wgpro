package com.typheye.wgpro.ui.function.community;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.viewpager2.widget.ViewPager2;

public class AccountListActivity extends BaseSectionActivity {
    public static final String EXTRA_MODE = "mode";
    public static final String EXTRA_TITLE = "title";
    @Override protected String screenTitle() { return getIntent().getStringExtra(EXTRA_TITLE); }
    @Override protected Fragment createContent() {
        if (CloudListFragment.MODE_HISTORY.equals(getIntent().getStringExtra(EXTRA_MODE))) return new HistoryPagerFragment();
        return CloudListFragment.newInstance(getIntent().getStringExtra(EXTRA_MODE));
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        if (CloudListFragment.MODE_HISTORY.equals(getIntent().getStringExtra(EXTRA_MODE))) {
            MenuItem item = menu.add("历史选项").setIcon(com.typheye.wgpro.R.drawable.ic_more_vertical_vector);
            item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }
        return super.onCreateOptionsMenu(menu);
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if ("历史选项".contentEquals(item.getTitle())) {
            Fragment f=getSupportFragmentManager().findFragmentById(com.typheye.wgpro.R.id.section_container);
            if (f instanceof HistoryPagerFragment) ((HistoryPagerFragment) f).showMenu();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static final class HistoryPagerFragment extends Fragment {
        private static final String[] TITLES = {"动态", "应用", "资源"};
        private static final String[] TYPES = {"dynamic", "app", "resource"};
        private com.google.android.material.tabs.TabLayoutMediator tabsMediator;
        private ViewPager2 pager;

        @Override public android.view.View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup parent, Bundle state) {
            android.view.View root = inflater.inflate(com.typheye.wgpro.R.layout.fragment_history_pager, parent, false);
            pager = root.findViewById(com.typheye.wgpro.R.id.history_pager);
            String[] types = TYPES;
            pager.setAdapter(new androidx.viewpager2.adapter.FragmentStateAdapter(this) {
                public int getItemCount() { return 3; }
                public Fragment createFragment(int p) { return CloudListFragment.newHistoryPage(types[p]); }
            });
            pager.setOffscreenPageLimit(2);
            // 子标签挂在应用栏里（与主页 fragment 一致：铺满、下划线、共用毛玻璃），
            // 不再放在 Fragment 内部——那样会被半透明应用栏盖住、层级错位。
            com.google.android.material.tabs.TabLayout tabs =
                    ((BaseSectionActivity) requireActivity()).pageTabs();
            if (tabs != null) {
                tabs.setVisibility(android.view.View.VISIBLE);
                tabsMediator = new com.google.android.material.tabs.TabLayoutMediator(tabs, pager,
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
            com.google.android.material.tabs.TabLayout tabs =
                    ((BaseSectionActivity) requireActivity()).pageTabs();
            if (tabs != null) {
                tabs.removeAllTabs();
                tabs.setVisibility(android.view.View.GONE);
            }
            super.onDestroyView();
        }

        private void reloadAll() {
            requireActivity().runOnUiThread(() -> {
                for (Fragment f : getChildFragmentManager().getFragments())
                    if (f instanceof CloudListFragment) ((CloudListFragment) f).reloadFromHistoryAction();
            });
        }

        private void confirmClear(String type, String title, String message) {
            new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(requireContext())
                    .setTitle(title).setMessage(message)
                    .setNegativeButton("取消", null).setPositiveButton("清空", (x, y) ->
                            new com.typheye.wgpro.utils.tAccUtils(requireContext()).postV2Json("history_clear2",
                                    fieldsFor(type), new com.typheye.wgpro.utils.tAccUtils.JsonCallback() {
                                        public void onSuccess(org.json.JSONObject j) { reloadAll(); }
                                        public void onError(int c, String m) {
                                            requireActivity().runOnUiThread(() ->
                                                    new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(requireContext())
                                                            .setTitle("清空失败")
                                                            .setMessage(m == null || m.trim().isEmpty() ? "请稍后重试" : m)
                                                            .setNegativeButton("关闭", null).show());
                                        }
                                    }))
                    .show();
        }

        private java.util.Map<String, String> fieldsFor(String type) {
            java.util.Map<String, String> fields = new java.util.LinkedHashMap<>();
            if (type != null) fields.put("target_type", type);
            return fields;
        }

        void showMenu() {
            boolean enabled = requireContext().getSharedPreferences("history_preferences", 0).getBoolean("enabled", true);
            int position = pager == null ? 0 : Math.max(0, Math.min(TYPES.length - 1, pager.getCurrentItem()));
            String typeLabel = TITLES[position];
            new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(requireContext()).setTitle("浏览历史")
                    .setItems(new CharSequence[]{"清空" + typeLabel + "记录", "清空所有历史",
                            enabled ? "不再记录历史" : "开启记录历史"}, (d, w) -> {
                        if (w == 0) {
                            confirmClear(TYPES[position], "清空" + typeLabel + "记录？", "将删除全部" + typeLabel + "浏览记录。");
                        } else if (w == 1) {
                            confirmClear(null, "清空所有历史？", "将删除动态、应用和资源的全部浏览记录。");
                        } else {
                            new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(requireContext())
                                    .setTitle(enabled ? "不再记录历史？" : "开启记录历史？")
                                    .setMessage("确认更改浏览历史记录设置？")
                                    .setNegativeButton("取消", null)
                                    .setPositiveButton("确认", (x, y) -> requireContext()
                                            .getSharedPreferences("history_preferences", 0).edit()
                                            .putBoolean("enabled", !enabled).apply())
                                    .show();
                        }
                    }).show();
        }
    }
}
