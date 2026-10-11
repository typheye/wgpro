package com.typheye.wgpro.ui.function.download;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.typheye.wgpro.R;
import com.typheye.wgpro.core.download.DownloadManager;
import com.typheye.wgpro.core.download.DownloadService;
import com.typheye.wgpro.core.download.DownloadTask;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.utils.ImageCache;

import java.util.ArrayList;
import java.util.List;

/** 单个下载列表页（「下载中」或「已完成」），由 {@link DownloadManagerFragment} 放进 ViewPager2。 */
public final class DownloadListFragment extends Fragment {
    private static final String ARG_DONE = "done";
    private static final int PILL_WIDTH_DP = 92;
    private static final int PILL_HEIGHT_DP = 40;

    static DownloadListFragment newInstance(boolean done) {
        DownloadListFragment fragment = new DownloadListFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARG_DONE, done);
        fragment.setArguments(args);
        return fragment;
    }

    private boolean showDone;
    private RecyclerView list;
    private Adapter adapter;
    private View emptyView;
    private androidx.swiperefreshlayout.widget.SwipeRefreshLayout refresh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_download_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showDone = requireArguments().getBoolean(ARG_DONE, false);
        android.view.ViewGroup host = view.findViewById(R.id.download_list_host);
        refresh = view.findViewById(R.id.download_refresh);
        refresh.setColorSchemeColors(requireContext().getColor(R.color.brand_primary));
        refresh.setOnRefreshListener(() -> {
            // 下载数据是本地的，下拉只是重读本地记录 + 重渲染（手感同关注/粉丝页）。
            DownloadManager.get().reload();
            refresh.postDelayed(() -> { if (isAdded()) refresh.setRefreshing(false); }, 400L);
        });
        list = view.findViewById(R.id.download_list);
        list.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new Adapter();
        list.setAdapter(adapter);
        // 空状态统一复用全局占位布局（仅用于没有数据时）。
        emptyView = LayoutInflater.from(requireContext())
                .inflate(R.layout.view_stream_empty, host, false);
        host.addView(emptyView);
        list.getViewTreeObserver().addOnGlobalLayoutListener(this::applyAppBarTopPadding);
        applyAppBarTopPadding();
        DownloadManager.get().tasks().observe(getViewLifecycleOwner(), this::render);
    }

    /** 显式把应用栏高度补成列表的顶部留白，并让空状态跟随。 */
    private void applyAppBarTopPadding() {
        if (list == null || emptyView == null || !isAdded()) return;
        android.view.View appBar = requireActivity().findViewById(R.id.section_app_bar);
        if (appBar != null && appBar.getHeight() > 0) {
            com.typheye.wgpro.utils.AppBarBlur.padScrollableTop(list, appBar.getHeight());
            if (refresh != null) {
                com.typheye.wgpro.utils.AppBarBlur.offsetRefreshIndicator(refresh, appBar.getHeight());
            }
        }
        android.view.ViewGroup.LayoutParams raw = emptyView.getLayoutParams();
        if (raw instanceof android.view.ViewGroup.MarginLayoutParams) {
            android.view.ViewGroup.MarginLayoutParams lp = (android.view.ViewGroup.MarginLayoutParams) raw;
            int top = list.getPaddingTop();
            int start = list.getPaddingStart();
            int end = list.getPaddingEnd();
            if (lp.topMargin != top || lp.leftMargin != start || lp.rightMargin != end) {
                lp.topMargin = top;
                lp.leftMargin = start;
                lp.rightMargin = end;
                emptyView.setLayoutParams(lp);
            }
        }
    }

    private void render(@Nullable List<DownloadTask> tasks) {
        if (!isAdded() || adapter == null) return;
        List<DownloadTask> filtered = new ArrayList<>();
        if (tasks != null) {
            for (DownloadTask task : tasks) {
                boolean done = task.status == DownloadTask.STATUS_COMPLETED
                        || task.status == DownloadTask.STATUS_FAILED;
                if (done == showDone) filtered.add(task);
            }
        }
        adapter.submitSmart(filtered);
        boolean empty = filtered.isEmpty();
        emptyView.setVisibility(empty ? View.VISIBLE : View.GONE);
        ((TextView) emptyView.findViewById(R.id.stream_empty_title))
                .setText(showDone ? "还没有下载记录" : "当前没有下载任务");
        ((TextView) emptyView.findViewById(R.id.stream_empty_description))
                .setText(showDone ? "已完成的下载会显示在这里。"
                        : "在应用详情页点击「获取」即可开始下载。");
        applyAppBarTopPadding();
    }

    /**
     * 复用单个 ViewHolder，只做**原地更新**（不重建子视图），避免下载中不断刷新时进度条闪烁。
     */
    private final class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
        private final List<DownloadTask> items = new ArrayList<>();

        void submit(List<DownloadTask> next) {
            items.clear();
            items.addAll(next);
            notifyDataSetChanged();
        }

        /** 结构不变时原地更新可见项（不 notify），避免进度刷新打断长按/造成闪烁。 */
        void submitSmart(List<DownloadTask> next) {
            boolean sameShape = next.size() == items.size() && next.size() > 0;
            if (sameShape) {
                for (int i = 0; i < next.size(); i++) {
                    if (!next.get(i).id.equals(items.get(i).id)) { sameShape = false; break; }
                }
            }
            items.clear();
            items.addAll(next);
            if (sameShape && list != null) {
                for (int i = 0; i < items.size(); i++) {
                    RecyclerView.ViewHolder vh = list.findViewHolderForAdapterPosition(i);
                    if (vh instanceof Holder) ((Holder) vh).bind(items.get(i));
                }
            } else {
                notifyDataSetChanged();
            }
        }

        @NonNull @Override public Holder onCreateViewHolder(@NonNull ViewGroup parent, int type) {
            Context context = parent.getContext();
            MaterialCardView card = appCard(context);
            LinearLayout row = new LinearLayout(context);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setGravity(Gravity.CENTER_VERTICAL);
            row.setPadding(dp(16), dp(12), dp(16), dp(12));
            card.addView(row, new MaterialCardView.LayoutParams(-1, -2));
            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(-1, -2);
            params.bottomMargin = dp(12);
            card.setLayoutParams(params);

            FrameLayout iconBox = new FrameLayout(context);
            TextView initial = text(context, "", 20, true, R.color.brand_on_soft);
            initial.setGravity(Gravity.CENTER);
            initial.setBackgroundResource(R.drawable.bg_app_initial_rounded);
            iconBox.addView(initial, new FrameLayout.LayoutParams(-1, -1));
            ImageView icon = new ImageView(context);
            icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
            icon.setVisibility(View.GONE);
            icon.setBackgroundResource(R.drawable.bg_app_icon_clip);
            icon.setClipToOutline(true);
            iconBox.addView(icon, new FrameLayout.LayoutParams(-1, -1));
            row.addView(iconBox, new LinearLayout.LayoutParams(dp(48), dp(48)));

            LinearLayout labels = new LinearLayout(context);
            labels.setOrientation(LinearLayout.VERTICAL);
            TextView name = text(context, "", 16, true, R.color.text_primary);
            name.setSingleLine(true);
            name.setEllipsize(android.text.TextUtils.TruncateAt.END);
            labels.addView(name);
            TextView meta = text(context, "", 13, false, R.color.text_secondary);
            LinearLayout.LayoutParams metaParams = new LinearLayout.LayoutParams(-1, -2);
            metaParams.topMargin = dp(4);
            labels.addView(meta, metaParams);
            LinearLayout.LayoutParams labelParams = new LinearLayout.LayoutParams(0, -2, 1f);
            labelParams.setMarginStart(dp(14));
            labelParams.setMarginEnd(dp(12));
            row.addView(labels, labelParams);

            // 右侧小药丸：既是按钮也是进度（原理同详情页）。
            FrameLayout pill = new FrameLayout(context);
            pill.setBackgroundResource(R.drawable.bg_download_bar);
            pill.setClipToOutline(true);
            pill.setClickable(true);
            pill.setFocusable(true);
            View fill = new View(context);
            fill.setBackgroundResource(R.drawable.bg_download_fill);
            pill.addView(fill, new FrameLayout.LayoutParams(0, -1));
            TextView pillText = text(context, "", 14, true, R.color.text_primary);
            pillText.setGravity(Gravity.CENTER);
            pill.addView(pillText, new FrameLayout.LayoutParams(-1, -1));
            row.addView(pill, new LinearLayout.LayoutParams(dp(PILL_WIDTH_DP), dp(PILL_HEIGHT_DP)));

            return new Holder(card, initial, icon, name, meta, pill, fill, pillText);
        }

        @Override public void onBindViewHolder(@NonNull Holder holder, int position) {
            holder.bind(items.get(position));
        }

        @Override public int getItemCount() {
            return items.size();
        }

        final class Holder extends RecyclerView.ViewHolder {
            private final TextView initial;
            private final ImageView icon;
            private final TextView name;
            private final TextView meta;
            private final FrameLayout pill;
            private final View fill;
            private final TextView pillText;
            private String iconUrl;
            private boolean iconIsVela;

            Holder(@NonNull View itemView, TextView initial, ImageView icon, TextView name,
                   TextView meta, FrameLayout pill, View fill, TextView pillText) {
                super(itemView);
                this.initial = initial;
                this.icon = icon;
                this.name = name;
                this.meta = meta;
                this.pill = pill;
                this.fill = fill;
                this.pillText = pillText;
            }

            void bind(DownloadTask task) {
                Context context = itemView.getContext();
                name.setText(task.name);
                meta.setText(statusSimple(task));
                if (!task.name.isEmpty()) initial.setText(first(task.name));
                boolean resource = "resource".equals(task.detailType);
                boolean vela = !resource && task.downloadUrl != null
                        && task.downloadUrl.toLowerCase(java.util.Locale.ROOT).endsWith(".rpk");
                applyIconShape(vela);
                if (resource) {
                    // 资源用「文字头像」，不直接用封面头图。
                    if (iconUrl == null || !"__resource__".equals(iconUrl)) {
                        iconUrl = "__resource__";
                        icon.setVisibility(View.GONE);
                        icon.setImageDrawable(null);
                        initial.setVisibility(View.VISIBLE);
                    }
                } else if (iconUrl == null ? task.iconUrl != null : !iconUrl.equals(task.iconUrl)) {
                    iconUrl = task.iconUrl;
                    icon.setVisibility(View.GONE);
                    icon.setImageDrawable(null);
                    initial.setVisibility(View.VISIBLE);
                    if (task.iconUrl != null && !task.iconUrl.isEmpty()) {
                        ImageCache.load(context, task.iconUrl, icon, () -> {
                            icon.setVisibility(View.VISIBLE);
                            initial.setVisibility(View.GONE);
                        });
                    }
                }
                pillText.setText(pillText(task));
                pill.setOnClickListener(v -> onAction(task));
                itemView.setOnLongClickListener(v -> {
                    showRowActions(task);
                    return true;
                });
                final int progress = (task.status == DownloadTask.STATUS_RUNNING
                        || task.status == DownloadTask.STATUS_QUEUED
                        || task.status == DownloadTask.STATUS_PAUSED) ? task.progress() : 0;
                applyFill(progress);
            }

            /** 原地更新填充宽度（不重建视图，避免下载中进度条闪烁）。 */
            private void applyFill(int progress) {
                int width = pill.getWidth();
                if (width <= 0) {
                    pill.post(() -> applyFill(progress));
                    return;
                }
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) fill.getLayoutParams();
                int desired = Math.max(0, Math.round(width * progress / 100f));
                if (lp.width != desired) {
                    lp.width = desired;
                    fill.setLayoutParams(lp);
                }
            }

            /** Vela 图标是圆的：放在跟随莫奈取色的圆角卡片上；普通应用图标用圆角矩形裁切。 */
            private void applyIconShape(boolean vela) {
                if (vela == iconIsVela) return;
                iconIsVela = vela;
                if (vela) {
                    // 下载项卡片底是 surface_secondary，衬底用 surface_primary 才不会融在一起。
                    icon.setBackgroundResource(R.drawable.bg_vela_icon_clip);
                    icon.setClipToOutline(true);
                    int pad = dp(3);
                    icon.setPadding(pad, pad, pad, pad);
                    icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
                } else {
                    icon.setBackgroundResource(R.drawable.bg_app_icon_clip);
                    icon.setClipToOutline(true);
                    icon.setPadding(0, 0, 0, 0);
                    icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
            }
        }
    }

    private static MaterialCardView appCard(Context context) {
        MaterialCardView card = new MaterialCardView(context);
        card.setCardBackgroundColor(context.getColor(R.color.surface_secondary));
        card.setCardElevation(0f);
        card.setStrokeWidth(0);
        card.setRadius(Math.round(8 * context.getResources().getDisplayMetrics().density));
        card.setClickable(true);
        card.setFocusable(true);
        card.setRippleColor(ColorStateList.valueOf(context.getColor(R.color.brand_soft)));
        return card;
    }

    /** 第二行：只简写状态（下载中 / 已完成 …）。 */
    private String statusSimple(DownloadTask task) {
        switch (task.status) {
            case DownloadTask.STATUS_QUEUED: return "等待中";
            case DownloadTask.STATUS_RUNNING: return "下载中";
            case DownloadTask.STATUS_PAUSED: return "已暂停";
            case DownloadTask.STATUS_FAILED: return "下载失败";
            case DownloadTask.STATUS_COMPLETED: return "已完成";
            default: return "";
        }
    }

    /** 右侧药丸文字（指示状态 + 点击行为）。 */
    private String pillText(DownloadTask task) {
        switch (task.status) {
            case DownloadTask.STATUS_RUNNING: return task.progress() + "%";
            case DownloadTask.STATUS_QUEUED: return "等待中";
            case DownloadTask.STATUS_PAUSED: return "继续";
            case DownloadTask.STATUS_FAILED: return "重试";
            case DownloadTask.STATUS_COMPLETED:
                return task.destination == DownloadTask.DEST_DEVICE ? "安装" : "打开";
            default: return "";
        }
    }

    private void onAction(DownloadTask task) {
        Context context = requireContext();
        switch (task.status) {
            case DownloadTask.STATUS_RUNNING:
            case DownloadTask.STATUS_QUEUED:
                DownloadManager.get().control(context, task.id, DownloadService.ACTION_PAUSE);
                break;
            case DownloadTask.STATUS_PAUSED:
            case DownloadTask.STATUS_FAILED:
                DownloadManager.get().control(context, task.id, DownloadService.ACTION_RESUME);
                break;
            case DownloadTask.STATUS_COMPLETED:
                if (task.destination == DownloadTask.DEST_DEVICE) {
                    new WGProAlertDialogBuilder(context).setTitle("安装到设备")
                            .setMessage("手表端安装能力接入后即可直接从手机安装到已连接的设备。")
                            .setNegativeButton("知道了", null).show();
                } else {
                    new WGProAlertDialogBuilder(context).setTitle("下载完成")
                            .setMessage(task.localPath == null ? "文件已保存。" : task.localPath)
                            .setNegativeButton("关闭", null).show();
                }
                break;
            default:
                break;
        }
    }

    private void showRowActions(DownloadTask task) {
        Context context = requireContext();
        if (task.isFinished()) {
            CharSequence[] actions = new CharSequence[]{"用其他应用打开", "删除记录"};
            new WGProAlertDialogBuilder(context).setTitle("操作").setItems(actions, (dialog, which) -> {
                if (which == 0) openWithOtherApp(task);
                else DownloadManager.get().control(context, task.id, DownloadService.ACTION_REMOVE);
            }).show();
        } else {
            CharSequence[] actions = new CharSequence[]{"查看详情", "取消"};
            new WGProAlertDialogBuilder(context).setTitle("操作").setItems(actions, (dialog, which) -> {
                if (which == 0) openDetail(task);
                else DownloadManager.get().control(context, task.id, DownloadService.ACTION_CANCEL);
            }).show();
        }
    }

    /** 用系统「打开方式」窗口打开已下载的文件。 */
    private void openWithOtherApp(DownloadTask task) {
        try {
            android.net.Uri uri = downloadedFileUri(task);
            if (uri == null) {
                com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(), "无法打开", "文件不存在");
                return;
            }
            android.content.Intent intent = new android.content.Intent(android.content.Intent.ACTION_VIEW)
                    .setDataAndType(uri, "application/vnd.android.package-archive")
                    .addFlags(android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(android.content.Intent.createChooser(intent, "打开方式"));
        } catch (Exception e) {
            com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(), "无法打开", "无法打开："
                    + (e.getMessage() == null ? "未知错误" : e.getMessage()));
        }
    }

    @Nullable
    private android.net.Uri downloadedFileUri(DownloadTask task) {
        String path = task.localPath;
        if (path != null && path.startsWith("content://")) return android.net.Uri.parse(path);
        java.io.File file = (path != null && !path.isEmpty())
                ? new java.io.File(path)
                : new java.io.File(requireContext().getFilesDir(), "downloads/" + task.id + ".apk");
        if (!file.exists()) return null;
        try {
            return androidx.core.content.FileProvider.getUriForFile(requireContext(),
                    requireContext().getPackageName() + ".fileprovider", file);
        } catch (Exception e) {
            return null;
        }
    }

    /** 打开该下载项对应的应用/资源详情页。 */
    private void openDetail(DownloadTask task) {
        try {
            if ("resource".equals(task.detailType)) {
                startActivity(new android.content.Intent(requireContext(),
                        com.typheye.wgpro.ui.function.community.ResourceDetailActivity.class)
                        .putExtra(com.typheye.wgpro.ui.function.community.ResourceDetailActivity
                                .EXTRA_RESOURCE_JSON, task.detailJson));
            } else {
                startActivity(new android.content.Intent(requireContext(),
                        com.typheye.wgpro.ui.function.community.AppDetailActivity.class)
                        .putExtra(com.typheye.wgpro.ui.function.community.AppDetailActivity
                                .EXTRA_APP_JSON, task.detailJson));
            }
        } catch (Exception e) {
            com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(), "无法打开", "无法打开详情");
        }
    }

    private String first(String value) {
        String trimmed = value == null ? "" : value.trim();
        return trimmed.isEmpty() ? "应" : trimmed.substring(0, 1).toUpperCase();
    }

    private TextView text(Context c, String value, int size, boolean bold, int color) {
        TextView view = new TextView(c);
        view.setText(value);
        view.setTextSize(size);
        view.setTextColor(c.getColor(color));
        view.setTypeface(Typeface.DEFAULT, bold ? Typeface.BOLD : Typeface.NORMAL);
        return view;
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
