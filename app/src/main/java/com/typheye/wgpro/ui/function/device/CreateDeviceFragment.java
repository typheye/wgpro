package com.typheye.wgpro.ui.function.device;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.typheye.wgpro.R;

/**
 * **新**「创建设备」页（{@code AddDeviceActivity.MODE_CREATE}）。
 *
 * <p>与旧的 {@link VirtualDeviceListFragment} **并存**：</p>
 * <ul>
 *   <li>旧页拉云端机型（{@code api.php?type=vela_devices}）并按机型下载镜像包 ——
 *       上一个内测版还在用，**保持原样不动**；</li>
 *   <li>本页**列表保持为空**（不请求任何接口、没有任何预置机型），设备定义后续完全来自
 *       「从本地导入」的镜像包 {@code config.json}。</li>
 * </ul>
 *
 * <p>导入入口在宿主 {@link AddDeviceActivity} 的右上角菜单（{@code action_import_image}），
 * 该模式下的导入**不依赖云端机型**：机型标识直接取所选包的文件名。</p>
 */
public class CreateDeviceFragment extends Fragment {

    private RecyclerView listView;
    private View emptyView;
    private com.typheye.wgpro.ui.widget.WGProRefreshLayout refreshLayout;
    /** 加载指示器最短停留：本页没有远端数据，太快收起会看成"没触发"。 */
    private static final long MIN_SPIN_MS = 600L;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle state) {
        return inflater.inflate(R.layout.fragment_create_device, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle state) {
        super.onViewCreated(view, state);
        listView = view.findViewById(R.id.create_device_list);
        emptyView = view.findViewById(R.id.create_device_state);
        ((TextView) emptyView.findViewById(R.id.stream_empty_title)).setText("暂无模拟设备");
        ((TextView) emptyView.findViewById(R.id.stream_empty_description))
                .setText("点右上角「从本地导入镜像包」开始创建。");
        // 空态是盖在列表上的覆盖层：必须跟随滚动容器的上下留白，否则会被应用栏盖住
        final View stateContainer = (View) emptyView.getParent();
        listView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            // 顶边距规则：可视范围上边缘（= 应用栏下方）+ 该页左右留白(16dp)
            int top = listView.getPaddingTop()
                    + Math.round(getResources().getDisplayMetrics().density * 16f);
            if (stateContainer.getPaddingTop() != top) {
                stateContainer.setPadding(stateContainer.getPaddingLeft(), top,
                        stateContainer.getPaddingRight(), stateContainer.getPaddingBottom());
            }
        });
        listView.setLayoutManager(new LinearLayoutManager(requireContext()));
        refreshLayout = view.findViewById(R.id.create_device_refresh);
        // 加载态一律用下拉刷新指示器表达（原页面级遮罩已移除）：进页面先触发一次
        refreshLayout.setOnRefreshListener(this::reload);
        refreshLayout.post(this::reload);
        // ★ 列表**必须保持 VISIBLE**（哪怕一条数据都没有）：AppBarBlur.padFirstScrollable()
        //   只给"可见的"第一个滚动容器补应用栏高度的顶部留白；把它 GONE 掉，
        //   留白就补不上 → 空态顶到应用栏底下，而且模糊快照也是空的（实测踩过）。
        listView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.VISIBLE);
    }

    /**
     * "加载"一次：本页没有远端数据源，列表**保持为空**，只把加载态走下拉刷新指示器
     * （进页面与下拉刷新都调它）。
     */
    private void reload() {
        final com.typheye.wgpro.ui.widget.WGProRefreshLayout refresh = refreshLayout;
        if (refresh == null || !isAdded()) {
            return;
        }
        refresh.setRefreshing(true);
        refresh.postDelayed(() -> {
            if (isAdded() && refreshLayout != null) {
                refreshLayout.setRefreshing(false);
            }
        }, MIN_SPIN_MS);
    }

    /** 列表恒为空（对应旧页的 {@code loadedModels()} 语义）：没有可选的云端机型。 */
    public boolean hasModels() {
        return false;
    }
}
