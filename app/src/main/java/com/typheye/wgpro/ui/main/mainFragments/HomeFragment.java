package com.typheye.wgpro.ui.main.mainFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.typheye.wgpro.R;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        MaterialButtonToggleGroup segments = root.findViewById(R.id.home_segments);
        ViewPager2 pager = root.findViewById(R.id.home_pager);
        FrameLayout pool = root.findViewById(R.id.home_page_pool);
        List<View> pages = Arrays.asList(root.findViewById(R.id.page_community),
                root.findViewById(R.id.page_apps), root.findViewById(R.id.page_resources));
        for (View page : pages) pool.removeView(page);
        pager.setAdapter(new LocalPageAdapter(pages));
        pager.setOffscreenPageLimit(2);
        int[] segmentIds = {R.id.home_segment_community, R.id.home_segment_apps,
                R.id.home_segment_resources};
        segments.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (!isChecked) return;
            for (int i = 0; i < segmentIds.length; i++) {
                if (segmentIds[i] == checkedId && pager.getCurrentItem() != i) {
                    pager.setCurrentItem(i, true);
                    break;
                }
            }
        });
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override public void onPageSelected(int position) {
                if (position >= 0 && position < segmentIds.length
                        && segments.getCheckedButtonId() != segmentIds[position]) {
                    segments.check(segmentIds[position]);
                }
            }
        });
        return root;
    }

    private static final class LocalPageAdapter
            extends RecyclerView.Adapter<LocalPageAdapter.Holder> {
        private final List<View> pages;
        LocalPageAdapter(List<View> pages) {
            this.pages = pages;
            setHasStableIds(true);
        }
        @Override public long getItemId(int position) { return position; }
        @Override public int getItemCount() { return pages.size(); }
        @NonNull @Override public Holder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
            FrameLayout frame = new FrameLayout(parent.getContext());
            frame.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return new Holder(frame);
        }
        @Override public void onBindViewHolder(@NonNull Holder holder, int position) {
            View page = pages.get(position);
            if (page.getParent() instanceof ViewGroup) {
                ((ViewGroup) page.getParent()).removeView(page);
            }
            holder.frame.removeAllViews();
            holder.frame.addView(page, new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        static final class Holder extends RecyclerView.ViewHolder {
            final FrameLayout frame;
            Holder(FrameLayout frame) {
                super(frame);
                this.frame = frame;
            }
        }
    }
}
