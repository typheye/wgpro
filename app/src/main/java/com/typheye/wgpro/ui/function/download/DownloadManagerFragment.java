package com.typheye.wgpro.ui.function.download;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.community.BaseSectionActivity;

/** 下载管理：应用栏两个标签页「下载中 / 已完成」，内容用 ViewPager2 承载，可左右滑动切换。 */
public final class DownloadManagerFragment extends Fragment {
    private static final String[] TITLES = {"下载中", "已完成"};

    private ViewPager2 pager;
    private TabLayoutMediator tabsMediator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_download_manager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pager = view.findViewById(R.id.download_pager);
        pager.setAdapter(new FragmentStateAdapter(this) {
            @Override public int getItemCount() { return TITLES.length; }
            @NonNull @Override public Fragment createFragment(int position) {
                return DownloadListFragment.newInstance(position == 1);
            }
        });
        pager.setOffscreenPageLimit(1);
        TabLayout tabs = ((BaseSectionActivity) requireActivity()).pageTabs();
        if (tabs != null) {
            tabs.setVisibility(View.VISIBLE);
            tabs.removeAllTabs();
            tabsMediator = new TabLayoutMediator(tabs, pager,
                    (tab, position) -> tab.setText(TITLES[position]));
            tabsMediator.attach();
        }
    }

    @Override
    public void onDestroyView() {
        if (tabsMediator != null) {
            tabsMediator.detach();
            tabsMediator = null;
        }
        // 注意：这里**不能**把标签页设为 GONE —— 通知栏点进来走 onNewIntent，会先换 Fragment，
        // 新旧视图生命周期交错时新页刚显示的标签会被旧页隐藏掉（表现为"没有下载中/已完成标签"，概率出现）。
        pager = null;
        super.onDestroyView();
    }
}
