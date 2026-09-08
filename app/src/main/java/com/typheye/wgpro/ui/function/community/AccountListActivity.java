package com.typheye.wgpro.ui.function.community;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.button.MaterialButtonToggleGroup;

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
        @Override public android.view.View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup parent, Bundle state) {
            android.view.View root=inflater.inflate(com.typheye.wgpro.R.layout.fragment_history_pager,parent,false);
            ViewPager2 pager=root.findViewById(com.typheye.wgpro.R.id.history_pager);
            String[] types={"dynamic","app","resource"};
            pager.setAdapter(new androidx.viewpager2.adapter.FragmentStateAdapter(this){ public int getItemCount(){return 3;} public Fragment createFragment(int p){return CloudListFragment.newHistoryPage(types[p]);} }); pager.setOffscreenPageLimit(2);
            MaterialButtonToggleGroup group=root.findViewById(com.typheye.wgpro.R.id.history_segments); int[] ids={com.typheye.wgpro.R.id.history_segment_dynamic,com.typheye.wgpro.R.id.history_segment_app,com.typheye.wgpro.R.id.history_segment_resource}; group.check(ids[0]);
            group.addOnButtonCheckedListener((g,id,c)->{if(c)for(int i=0;i<3;i++)if(id==ids[i]&&pager.getCurrentItem()!=i)pager.setCurrentItem(i);}); pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){public void onPageSelected(int p){group.check(ids[p]);}}); return root;
        }
        void showMenu(){boolean enabled=requireContext().getSharedPreferences("history_preferences",0).getBoolean("enabled",true);new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(requireContext()).setTitle("浏览历史").setItems(new CharSequence[]{"清空所有历史",enabled?"不再记录历史":"开启记录历史"},(d,w)->{if(w==0)new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(requireContext()).setTitle("清空所有历史？").setMessage("将删除全部浏览记录。").setNegativeButton("取消",null).setPositiveButton("清空",(x,y)->new com.typheye.wgpro.utils.tAccUtils(requireContext()).postV2Json("history_clear2",new java.util.LinkedHashMap<>(),new com.typheye.wgpro.utils.tAccUtils.JsonCallback(){public void onSuccess(org.json.JSONObject j){requireActivity().runOnUiThread(()->{for(Fragment f:getChildFragmentManager().getFragments())if(f instanceof CloudListFragment)((CloudListFragment)f).reloadFromHistoryAction();});} public void onError(int c,String m){}})).show();else new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(requireContext()).setTitle(enabled?"不再记录历史？":"开启记录历史？").setMessage("确认更改浏览历史记录设置？").setNegativeButton("取消",null).setPositiveButton("确认",(x,y)->requireContext().getSharedPreferences("history_preferences",0).edit().putBoolean("enabled",!enabled).apply()).show();}).show();}
    }
}
