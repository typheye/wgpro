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
        refresh.setOnRefreshListener(() -> {
            // 下载数据是本地的，下拉只是重读本地记录 + 重渲染（手感同关注/粉丝页）。
            DownloadManager.get().reload();
            refresh.postDelayed(() -> { if (isAdded()) refresh.setRefreshing(false); }, 400L);
        });
        // 进入页面先走一次下拉刷新加载态（本地数据也统一）
        refresh.post(() -> { if (isAdded() && refresh != null) refresh.setRefreshing(true); });
        refresh.postDelayed(() -> { if (isAdded() && refresh != null) refresh.setRefreshing(false); }, 600L);
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
                // 失败项不留在列表里（列表页不弹窗打扰：失败提示只在对应的详情页给）
                if (task.status == DownloadTask.STATUS_FAILED) {
                    com.typheye.wgpro.ui.widget.DownloadRetryFlow.resetNotification(task);
                    continue;
                }
                boolean done = task.status == DownloadTask.STATUS_COMPLETED;
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
            icon.setVisibility(View.GONE);
            com.typheye.wgpro.ui.widget.WGProIconStyle.apply(icon, false);
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
            // 反色文字层：只显示在进度填充区域内（原理同详情页），49% 时左右两边都清晰
            int pillFillColor = com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder.resolveThemeColor(
                    context, "colorPrimary", R.color.brand_primary);
            // 反色文字按对比度择优（夜间 colorPrimary 是亮蓝，白字看不清）
            int inkWhite = context.getColor(R.color.white);
            int inkDark = context.getColor(R.color.text_on_fill);
            int pillFillInk = androidx.core.graphics.ColorUtils.calculateContrast(inkWhite, pillFillColor)
                    >= androidx.core.graphics.ColorUtils.calculateContrast(inkDark, pillFillColor)
                    ? inkWhite : inkDark;
            TextView pillTextFill = text(context, "", 14, true, R.color.white);
            pillTextFill.setTextColor(pillFillInk);
            pillTextFill.setGravity(Gravity.CENTER);
            pillTextFill.setVisibility(View.INVISIBLE);
            pill.addView(pillTextFill, new FrameLayout.LayoutParams(-1, -1));
            row.addView(pill, new LinearLayout.LayoutParams(dp(PILL_WIDTH_DP), dp(PILL_HEIGHT_DP)));

            return new Holder(card, initial, icon, name, meta, pill, fill, pillText, pillTextFill);
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
            private final TextView pillTextFill;
            private int lastFillWidth = -1;
            private CharSequence lastPillText = null;
            private String iconUrl;
            private boolean iconIsVela;

            Holder(@NonNull View itemView, TextView initial, ImageView icon, TextView name,
                   TextView meta, FrameLayout pill, View fill, TextView pillText,
                   TextView pillTextFill) {
                super(itemView);
                this.initial = initial;
                this.icon = icon;
                this.name = name;
                this.meta = meta;
                this.pill = pill;
                this.fill = fill;
                this.pillText = pillText;
                this.pillTextFill = pillTextFill;
            }

            void bind(DownloadTask task) {
                Context context = itemView.getContext();
                name.setText(task.name);
                meta.setText(secondLine(task));
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
                setPillText(pillText(task));
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

            /** 药丸文字：常规层 + 填充区域反色层同步；没变化就不动（防闪）。 */
            private void setPillText(CharSequence text) {
                if (text == null) text = "";
                if (android.text.TextUtils.equals(lastPillText, text)) return;
                lastPillText = text;
                pillText.setText(text);
                pillTextFill.setText(text);
            }

            /** 反色层按填充宽度裁剪；填充为 0 时隐藏。 */
            private void clipPillTextFill(int fillWidth) {
                if (pillTextFill == null || pill == null) return;
                if (fillWidth <= 0) {
                    if (pillTextFill.getVisibility() != View.INVISIBLE) {
                        pillTextFill.setVisibility(View.INVISIBLE);
                    }
                    return;
                }
                if (pillTextFill.getVisibility() != View.VISIBLE) {
                    pillTextFill.setVisibility(View.VISIBLE);
                }
                int h = Math.max(pillTextFill.getHeight(), pill.getHeight());
                pillTextFill.setClipBounds(new android.graphics.Rect(0, 0, fillWidth, h));
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
                if (lastFillWidth != desired) {
                    lastFillWidth = desired;
                    clipPillTextFill(desired);
                }
            }

            /** 图标呈现规则统一（Vela 圆图标原样显示，Android 方图标圆角裁切）。 */
            private void applyIconShape(boolean vela) {
                if (vela == iconIsVela) return;
                iconIsVela = vela;
                com.typheye.wgpro.ui.widget.WGProIconStyle.apply(icon, vela);
            }
        }
    }

    private static MaterialCardView appCard(Context context) {
        MaterialCardView card = new MaterialCardView(context);
        card.setCardBackgroundColor(com.typheye.wgpro.ui.widget.WGProColors.of(context, R.color.surface_secondary));
        card.setCardElevation(0f);
        card.setStrokeWidth(0);
        card.setRadius(Math.round(8 * context.getResources().getDisplayMetrics().density));
        card.setClickable(true);
        card.setFocusable(true);
        card.setRippleColor(ColorStateList.valueOf(com.typheye.wgpro.ui.widget.WGProColors.of(context, R.color.brand_soft)));
        return card;
    }

    /** 是否能定位到来源（应用/资源详情页）。 */
    private boolean hasSourceDetail(DownloadTask task) {
        if (isVelaTask(task)) {
            // 系统镜像（模拟设备）→ 跳设备详情页
            return true;
        }
        String json = task.detailJson == null ? "" : task.detailJson.trim();
        return json.startsWith("{") && task.detailType != null && !task.detailType.isEmpty();
    }

    /** 是否是模拟设备的系统镜像任务（任务 id 形如 {@code vela-<机型>}）。 */
    private static boolean isVelaTask(@Nullable DownloadTask task) {
        return task != null && task.id != null && task.id.startsWith("vela-");
    }

    /** 第二行：已完成的显示文件大小（如 0.0MB），其它状态照旧。 */
    private String secondLine(DownloadTask task) {
        if (task.status == DownloadTask.STATUS_COMPLETED) {
            // 冗余设计：有大小就显示大小，拿不到大小就显示「已完成」，不留空行
            String byBytes = humanSize(task.totalBytes);
            if (!byBytes.isEmpty()) return byBytes;
            String raw = task.sizeLabel == null ? "" : task.sizeLabel.trim();
            if (raw.matches("^[0-9.]+[Mm]$")) return raw.substring(0, raw.length() - 1) + "MB";
            if (raw.matches("^[0-9.]+[Kk]$")) return raw.substring(0, raw.length() - 1) + "KB";
            if (!raw.isEmpty()) return raw;
            String onDisk = humanSize(localFileSize(task));
            return onDisk.isEmpty() ? "已完成" : onDisk;
        }
        return statusSimple(task);
    }

    /** 本地已下文件的字节数（拿不到返回 0）。 */
    private long localFileSize(DownloadTask task) {
        try {
            java.io.File file = localFile(task);
            return file != null && file.exists() ? file.length() : 0L;
        } catch (Exception e) {
            return 0L;
        }
    }

    /** 应用私有目录里的那份文件（按 task.id / 资源名兜底查找）。 */
    @Nullable
    private java.io.File localFile(DownloadTask task) {
        try {
            java.io.File dir = new java.io.File(requireContext().getFilesDir(), "downloads");
            String path = task.localPath;
            if (path != null && !path.isEmpty() && !path.startsWith("content://")) {
                java.io.File direct = new java.io.File(path);
                if (direct.exists()) return direct;
            }
            java.io.File byId = new java.io.File(dir, task.id + ".apk");
            if (byId.exists()) return byId;
            java.io.File named = new java.io.File(dir, task.name);
            if (named.exists()) return named;
            // 目录里找同名/同前缀的（资源保存时用的文件名不一定等于 id）
            java.io.File[] files = dir.listFiles();
            if (files != null) {
                for (java.io.File f : files) {
                    if (f.getName().startsWith(task.id)) return f;
                }
            }
        } catch (Exception ignored) { }
        return null;
    }

    /** 字节数 → 人类可读（39.6MB / 702.6KB）。 */
    private static String humanSize(long bytes) {
        if (bytes <= 0L) return "";
        if (bytes < 1024L) return bytes + "B";
        double kb = bytes / 1024.0;
        if (kb < 1024.0) return String.format(java.util.Locale.US, "%.1fKB", kb);
        double mb = kb / 1024.0;
        if (mb < 1024.0) return String.format(java.util.Locale.US, "%.1fMB", mb);
        return String.format(java.util.Locale.US, "%.1fGB", mb / 1024.0);
    }

    /** 按扩展名猜 MIME（各版本系统打开都不会因为类型不对而找不到应用）。 */
    private static String mimeOf(String nameOrPath) {
        String lower = nameOrPath == null ? "" : nameOrPath.toLowerCase(java.util.Locale.ROOT);
        if (lower.endsWith(".apk")) return "application/vnd.android.package-archive";
        if (lower.endsWith(".png")) return "image/png";
        if (lower.endsWith(".jpg") || lower.endsWith(".jpeg")) return "image/jpeg";
        if (lower.endsWith(".gif")) return "image/gif";
        if (lower.endsWith(".webp")) return "image/webp";
        if (lower.endsWith(".mp4")) return "video/mp4";
        if (lower.endsWith(".mp3")) return "audio/mpeg";
        if (lower.endsWith(".pdf")) return "application/pdf";
        if (lower.endsWith(".txt")) return "text/plain";
        if (lower.endsWith(".zip")) return "application/zip";
        return "*/*";
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
                return "打开";
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
                DownloadManager.get().control(context, task.id, DownloadService.ACTION_RESUME);
                break;
            case DownloadTask.STATUS_FAILED:
                // 重试 = 重新测速挑最快节点再下（旧节点可能已变慢/挂掉）
                com.typheye.wgpro.ui.widget.DownloadRetryFlow.retry(context, task, null);
                break;
            case DownloadTask.STATUS_COMPLETED:
                if (hasSourceDetail(task)) {
                    openDetail(task);
                } else {
                    openWithOtherApp(task);
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
            CharSequence[] actions = new CharSequence[]{isVelaTask(task) ? "查看设备详情" : "查看详情",
                    "取消"};
            new WGProAlertDialogBuilder(context).setTitle("操作").setItems(actions, (dialog, which) -> {
                if (which == 0) openDetail(task);
                // 取消 = 相当于从没下过：清掉下载记录与已下文件（详情页会重新显示「获取」）
                else DownloadManager.get().control(context, task.id, DownloadService.ACTION_REMOVE);
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
            String label = task.localPath == null ? task.name : task.localPath;
            android.content.Intent intent = new android.content.Intent(android.content.Intent.ACTION_VIEW)
                    .setDataAndType(uri, mimeOf(label))
                    .addFlags(android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION);
            if (intent.resolveActivity(requireContext().getPackageManager()) == null) {
                // 没有应用能处理这个类型（不同 ROM 差异大）：退回「任意类型」再试一次
                intent.setDataAndType(uri, "*/*");
            }
            startActivity(android.content.Intent.createChooser(intent, "打开方式"));
        } catch (SecurityException e) {
            // 系统 Downloads 目录的 URI 可能没有授权（UID ... does not have permission）
            com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(), "无法打开",
                    "系统下载目录里的这个文件已没有访问权限，请重新下载后再打开。");
        } catch (Exception e) {
            com.typheye.wgpro.ui.widget.WGProHint.showError(requireContext(), "无法打开", "无法打开："
                    + (e.getMessage() == null ? "未知错误" : e.getMessage()));
        }
    }

    @Nullable
    private android.net.Uri downloadedFileUri(DownloadTask task) {
        String path = task.localPath;
        if (path != null && path.startsWith("content://")) {
            android.net.Uri external = android.net.Uri.parse(path);
            if (canRead(external)) return external;
            // 系统下载目录的 URI 会因权限/记录失效读不了（SecurityException），
            // 回退到应用私有目录里那一份；都没有就让调用方提示"文件不存在"
            java.io.File fallback = localFile(task);
            if (fallback == null) return null;
            try {
                return androidx.core.content.FileProvider.getUriForFile(requireContext(),
                        requireContext().getPackageName() + ".fileprovider", fallback);
            } catch (Exception e) {
                return null;
            }
        }
        java.io.File file = localFile(task);
        if (file == null) return null;
        try {
            return androidx.core.content.FileProvider.getUriForFile(requireContext(),
                    requireContext().getPackageName() + ".fileprovider", file);
        } catch (Exception e) {
            return null;
        }
    }

    /** 探一下这个 content URI 现在还能不能读（不能读就换成本地文件）。 */
    private boolean canRead(android.net.Uri uri) {
        try (java.io.InputStream in = requireContext().getContentResolver().openInputStream(uri)) {
            return in != null;
        } catch (Exception e) {
            return false;
        }
    }

    /** 打开该下载项对应的应用/资源详情页。 */
    private void openDetail(DownloadTask task) {
        try {
            if (isVelaTask(task)) {
                // 系统镜像：直接打开该机型的「设备详情」二级页
                com.typheye.wgpro.ui.function.device.VirtualDeviceDetailActivity.openById(
                        requireContext(), task.id.substring("vela-".length()));
                return;
            }
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
        view.setTextColor(com.typheye.wgpro.ui.widget.WGProColors.of(c, color));
        view.setTypeface(Typeface.DEFAULT, bold ? Typeface.BOLD : Typeface.NORMAL);
        return view;
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }
}
