package com.typheye.wgpro.ui.function.device;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.widget.WGProRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 虚拟设备机型列表：原「创建设备」页的"查找设备"部分。
 *
 * <p>点机型卡片 → 打开 {@link VirtualDeviceDetailActivity}（与 App/资源详情同构的二级页）。</p>
 */
public class VirtualDeviceListFragment extends Fragment {

    private static final String API = "https://service.typheye.cn/api.php?type=vela_devices";

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    private final Handler ui = new Handler(Looper.getMainLooper());
    private final List<VelaModel> models = new ArrayList<>();

    private RecyclerView listView;
    private WGProRefreshLayout refreshLayout;
    private View emptyView;
    private ModelAdapter adapter;
    /** 进入遮罩只在首次拉取出现，下拉刷新不再出现。 */
    private boolean firstLoad = true;

    /** 打开详情页；详情里创建成功会回 RESULT_OK，此时本页（机型列表）也一起关闭 → 逐级退回设备页。 */
    private final androidx.activity.result.ActivityResultLauncher<Intent> detailLauncher =
            registerForActivityResult(
                    new androidx.activity.result.contract.ActivityResultContracts
                            .StartActivityForResult(),
                    result -> {
                        if (result.getResultCode() == android.app.Activity.RESULT_OK
                                && isAdded()) {
                            requireActivity().finish();
                        }
                    });

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle state) {
        return inflater.inflate(R.layout.fragment_virtual_device_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle state) {
        super.onViewCreated(view, state);
        listView = view.findViewById(R.id.virtual_list);
        emptyView = view.findViewById(R.id.virtual_list_state);
        // 空态是盖在列表上的覆盖层：必须跟随滚动容器的上下留白，否则会被应用栏盖住（看着像"没触发"）
        final View stateContainer = (View) emptyView.getParent();
        ((TextView) emptyView.findViewById(R.id.stream_empty_title)).setText("暂无可用机型");
        ((TextView) emptyView.findViewById(R.id.stream_empty_description))
                .setText("请检查网络后下拉重试。");
        listView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            // 顶边距规则：可视范围上边缘（= 应用栏下方）+ 该页左右留白(16dp)
            int top = listView.getPaddingTop()
                    + Math.round(getResources().getDisplayMetrics().density * 16f);
            if (stateContainer.getPaddingTop() != top) {
                stateContainer.setPadding(stateContainer.getPaddingLeft(), top,
                        stateContainer.getPaddingRight(), stateContainer.getPaddingBottom());
            }
        });
        refreshLayout = view.findViewById(R.id.virtual_list_refresh);
        refreshLayout.setOnRefreshListener(this::fetchModels);
        listView.setLayoutManager(new LinearLayoutManager(requireContext()));
        final int side = Math.round(getResources().getDisplayMetrics().density * 16f);
        final int gap = Math.round(getResources().getDisplayMetrics().density * 10f);
        // 顶边距规则：第一项距"可视范围上边缘（应用栏下沿）"= 该页左右留白（16dp）；
        // 应用栏高度那份留白由宿主 AppBarBlur 写在列表 paddingTop 上，这里只补左右留白那一段。
        listView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull android.graphics.Rect outRect, @NonNull View child,
                                       @NonNull RecyclerView parent, @NonNull RecyclerView.State s) {
                outRect.top = parent.getChildAdapterPosition(child) == 0 ? side : gap;
            }
        });
        adapter = new ModelAdapter();
        listView.setAdapter(adapter);
        fetchModels();
    }

    private void fetchModels() {
        if (firstLoad) {
            firstLoad = false;
            host().showPageOverlay();
        } else {
            // 之后的刷新只用下拉指示器（遮罩只在进入页面时出现一次）
            refreshLayout.setRefreshing(true);
        }
        new Thread(() -> {
            String body = null;
            try (Response response = client.newCall(new Request.Builder().url(API).build()).execute()) {
                if (response.body() != null) {
                    body = response.body().string();
                }
            } catch (Exception error) {
                android.util.Log.w("VelaModels", "拉取机型失败", error);
            }
            final String json = body;
            ui.post(() -> {
                if (isAdded()) {
                    applyModels(json);
                }
            });
        }, "vela-models").start();
    }

    private void applyModels(@Nullable String json) {
        host().hidePageOverlay();
        refreshLayout.setRefreshing(false);
        if (TextUtils.isEmpty(json)) {
            listView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
            return;
        }
        try {
            Root root = gson.fromJson(json, Root.class);
            models.clear();
            if (root != null && root.devices != null) {
                models.addAll(root.devices);
            }
            adapter.notifyDataSetChanged();
            boolean hasData = !models.isEmpty();
            listView.setVisibility(hasData ? View.VISIBLE : View.GONE);
            emptyView.setVisibility(hasData ? View.GONE : View.VISIBLE);
        } catch (Exception error) {
            listView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    private static final class Root {
        List<VelaModel> devices;
    }

    private final class ModelAdapter extends RecyclerView.Adapter<ModelHolder> {

        @NonNull
        @Override
        public ModelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ModelHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_vela_model, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ModelHolder holder, int position) {
            VelaModel model = models.get(position);
            // 第一行=设备名称，第二行=镜像文件名（标识），无其他字符
            holder.title.setText(model.name);
            holder.subtitle.setText(model.code);
            holder.initial.setText(model.name == null || model.name.isEmpty()
                    ? "?" : model.name.substring(0, 1).toUpperCase());
            holder.itemView.setOnClickListener(v -> detailLauncher.launch(
                    new Intent(requireContext(), VirtualDeviceDetailActivity.class)
                            .putExtra(VirtualDeviceDetailActivity.EXTRA_MODEL_JSON,
                                    new Gson().toJson(model))));
        }

        @Override
        public int getItemCount() {
            return models.size();
        }
    }

    private static final class ModelHolder extends RecyclerView.ViewHolder {
        final TextView initial;
        final TextView title;
        final TextView subtitle;

        ModelHolder(@NonNull View itemView) {
            super(itemView);
            initial = itemView.findViewById(R.id.model_initial);
            title = itemView.findViewById(R.id.model_name);
            subtitle = itemView.findViewById(R.id.model_code);
        }
    }

    @NonNull
    private AddDeviceActivity host() {
        return (AddDeviceActivity) requireActivity();
    }
}
