package com.typheye.wgpro.ui.main.mainFragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.textfield.TextInputEditText;
import com.typheye.wgpro.R;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {
    private final Handler carouselHandler = new Handler(Looper.getMainLooper());
    private ViewPager2 carousel;
    private TextView carouselPosition;
    private boolean pageVisible;

    private final Runnable advanceCarousel = new Runnable() {
        @Override public void run() {
            if (!pageVisible || carousel == null) return;
            carousel.setCurrentItem((carousel.getCurrentItem() + 1) % 5, true);
            carouselHandler.postDelayed(this, 5000L);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        TabLayout tabs = root.findViewById(R.id.home_tabs);
        ViewPager2 pager = root.findViewById(R.id.home_pager);
        carousel = root.findViewById(R.id.community_carousel);
        carouselPosition = root.findViewById(R.id.text_carousel_position);

        FrameLayout pagePool = root.findViewById(R.id.home_page_pool);
        List<View> pages = Arrays.asList(root.findViewById(R.id.page_community),
                root.findViewById(R.id.page_apps), root.findViewById(R.id.page_resources));
        detachAll(pagePool, pages);
        pager.setAdapter(new LocalPageAdapter(pages));
        pager.setOffscreenPageLimit(2);
        configureNestedPager(pager);
        new TabLayoutMediator(tabs, pager, (tab, position) ->
                tab.setText(new String[]{"社区", "应用", "资源"}[position])).attach();
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override public void onPageSelected(int position) {
                if (position == 0 && isResumed() && !isHidden()) startCarousel(); else stopCarousel();
            }
        });

        FrameLayout bannerPool = pages.get(0).findViewById(R.id.banner_page_pool);
        List<View> banners = Arrays.asList(bannerPool.getChildAt(0), bannerPool.getChildAt(1),
                bannerPool.getChildAt(2), bannerPool.getChildAt(3), bannerPool.getChildAt(4));
        detachAll(bannerPool, banners);
        carousel.setAdapter(new LocalPageAdapter(banners));
        carousel.setOffscreenPageLimit(1);
        configureNestedPager(carousel);
        carousel.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override public void onPageSelected(int position) { updateCarouselPosition(); }
        });

        pages.get(0).findViewById(R.id.notice_bar).setOnClickListener(v -> new WGProAlertDialogBuilder(requireContext())
                .setTitle("社区公告")
                .setMessage("新版社区正在小范围测试。当前内容均为本地演示数据，发布、评论和云端资源将在接口稳定后开放。")
                .setPositiveButton("知道了", null).show());
        carousel.getChildAt(0).setOnClickListener(v -> Toast.makeText(requireContext(), "专题内容为本地预览", Toast.LENGTH_SHORT).show());

        TextInputEditText search = pages.get(1).findViewById(R.id.input_app_search);
        search.setOnEditorActionListener((textView, actionId, event) -> {
            String query = textView.getText() == null ? "" : textView.getText().toString().trim();
            Toast.makeText(requireContext(), query.isEmpty() ? "请输入应用名称" : "正在本地目录中搜索“" + query + "”", Toast.LENGTH_SHORT).show();
            return true;
        });
        int[] entries = {R.id.post_one, R.id.post_two, R.id.post_three, R.id.app_featured,
                R.id.app_item_one, R.id.app_item_two, R.id.app_item_three, R.id.resource_one,
                R.id.resource_two, R.id.resource_three, R.id.resource_four};
        for (int id : entries) {
            View entry = null;
            for (View page : pages) if (entry == null) entry = page.findViewById(id);
            if (entry != null) entry.setOnClickListener(v -> Toast.makeText(requireContext(),
                    "本地预览内容，暂未连接云端", Toast.LENGTH_SHORT).show());
        }
        updateCarouselPosition();
        return root;
    }

    private static void detachAll(ViewGroup pool, List<View> views) {
        for (View view : views) pool.removeView(view);
    }

    private static void configureNestedPager(ViewPager2 pager) {
        RecyclerView recycler = (RecyclerView) pager.getChildAt(0);
        recycler.setOnTouchListener(new View.OnTouchListener() {
            float startX;
            @Override public boolean onTouch(View view, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    startX = event.getX();
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                } else if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
                    float delta = event.getX() - startX;
                    int direction = delta > 0 ? -1 : 1;
                    view.getParent().requestDisallowInterceptTouchEvent(recycler.canScrollHorizontally(direction));
                } else if (event.getActionMasked() == MotionEvent.ACTION_UP
                        || event.getActionMasked() == MotionEvent.ACTION_CANCEL) {
                    view.getParent().requestDisallowInterceptTouchEvent(false);
                }
                return false;
            }
        });
    }

    private void updateCarouselPosition() {
        if (carousel != null && carouselPosition != null)
            carouselPosition.setText((carousel.getCurrentItem() + 1) + " / 5");
    }

    private void startCarousel() {
        pageVisible = true;
        carouselHandler.removeCallbacks(advanceCarousel);
        carouselHandler.postDelayed(advanceCarousel, 5000L);
    }

    private void stopCarousel() {
        pageVisible = false;
        carouselHandler.removeCallbacks(advanceCarousel);
    }

    @Override public void onResume() { super.onResume(); startCarousel(); }
    @Override public void onPause() { stopCarousel(); super.onPause(); }
    @Override public void onHiddenChanged(boolean hidden) { super.onHiddenChanged(hidden); if (hidden) stopCarousel(); else startCarousel(); }
    @Override public void onDestroyView() { stopCarousel(); carousel = null; carouselPosition = null; super.onDestroyView(); }

    private static final class LocalPageAdapter extends RecyclerView.Adapter<LocalPageAdapter.Holder> {
        private final List<View> pages;
        LocalPageAdapter(List<View> pages) { this.pages = pages; setHasStableIds(true); }
        @Override public long getItemId(int position) { return position; }
        @Override public int getItemCount() { return pages.size(); }
        @NonNull @Override public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            FrameLayout frame = new FrameLayout(parent.getContext());
            frame.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return new Holder(frame);
        }
        @Override public void onBindViewHolder(@NonNull Holder holder, int position) {
            View page = pages.get(position);
            if (page.getParent() instanceof ViewGroup) ((ViewGroup) page.getParent()).removeView(page);
            holder.frame.removeAllViews();
            page.setVisibility(View.VISIBLE);
            holder.frame.addView(page, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        static final class Holder extends RecyclerView.ViewHolder {
            final FrameLayout frame;
            Holder(FrameLayout frame) { super(frame); this.frame = frame; }
        }
    }
}
