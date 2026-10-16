package com.typheye.wgpro.ui.function.device;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.google.gson.Gson;
import com.typheye.wgpro.R;
import com.typheye.wgpro.core.download.DownloadManager;
import com.typheye.wgpro.core.download.DownloadTask;
import com.typheye.wgpro.core.emu.VelaEmulator;
import com.typheye.wgpro.core.emu.VelaProvision;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProColors;
import com.typheye.wgpro.ui.widget.WGProHint;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.utils.BarBlurController;
import com.typheye.wgpro.utils.ImageCache;
import com.typheye.wgpro.utils.SystemBars;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 设备详情页：布局与交互**一比一**照资源详情页（`fragment_resource_detail.xml`）。
 *
 * <p>介绍类数据以镜像自带的 {@code images/<镜像标识>/config.ini} 为准（云端同一份文件），
 * 列表 JSON 只提供"能列出来"的字段。</p>
 */
public class VirtualDeviceDetailFragment extends Fragment {

    public static final String ARG_MODEL_JSON = "model_json";
    public static final String ARG_MODEL_ID = "model_id";
    private static final String API = "https://service.typheye.cn/api.php?type=vela_devices";
    private static final String IMAGE_BASE = "https://res.typheye.cn/images/";

    private final Gson gson = new Gson();
    private final Handler ui = new Handler(Looper.getMainLooper());
    private final OkHttpClient client = new OkHttpClient();

    private VelaModel model;
    /** config.ini 解析结果（介绍数据的唯一真源）。 */
    private final Map<String, String> config = new HashMap<>();

    private View root;
    private View actionCard;
    private View actionFill;
    private TextView actionText;
    private TextView actionTextFill;
    private View deleteCard;
    private Observer<List<DownloadTask>> observer;
    private Observer<List<DownloadTask>> completionObserver;
    private boolean cachedCreated;
    private long createdCheckedAt;
    private int lastFillWidth = -1;
    private boolean rendered;
    /** 分割胶囊状态：只在真正切换时重设形状（每个进度回调都重设背景会闪）。 */
    private boolean lastSplitState = false;
    private CharSequence lastActionText;

    public static VirtualDeviceDetailFragment newInstance(@NonNull VelaModel model) {
        Bundle args = new Bundle();
        args.putString(ARG_MODEL_JSON, new Gson().toJson(model));
        VirtualDeviceDetailFragment fragment = new VirtualDeviceDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    public static VirtualDeviceDetailFragment newInstanceById(@NonNull String modelId) {
        Bundle args = new Bundle();
        args.putString(ARG_MODEL_ID, modelId);
        VirtualDeviceDetailFragment fragment = new VirtualDeviceDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_virtual_device_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle state) {
        super.onViewCreated(view, state);
        root = view;
        actionCard = view.findViewById(R.id.vd_detail_action);
        actionFill = view.findViewById(R.id.vd_detail_action_fill);
        actionText = view.findViewById(R.id.vd_detail_action_text);
        actionTextFill = view.findViewById(R.id.vd_detail_action_text_fill);
        deleteCard = view.findViewById(R.id.vd_detail_action_delete);
        if (actionTextFill != null) {
            actionTextFill.setTextColor(fillInkColor());
        }
        if (actionCard != null) {
            actionCard.setOnClickListener(v -> onAcquire());
        }
        if (deleteCard != null) {
            deleteCard.setOnClickListener(v -> confirmDelete());
        }
        setupBars(view);
        shellLoading(true);
        observeTasks();

        Bundle args = getArguments();
        if (args != null) {
            String json = args.getString(ARG_MODEL_JSON);
            if (!TextUtils.isEmpty(json)) {
                model = gson.fromJson(json, VelaModel.class);
            }
        }
        if (model != null) {
            loadConfigThenRender();
        } else {
            fetchModelThen(args == null ? null : args.getString(ARG_MODEL_ID));
        }
    }

    // ------------------------------------------------------------ 底栏与垫高

    private void setupBars(@NonNull View view) {
        final View actionBar = view.findViewById(R.id.vd_detail_action_bar);
        final View blur = view.findViewById(R.id.vd_detail_blur);
        if (actionBar != null) {
            BarBlurController.registerBar(requireContext(), actionBar);
        }
        // 底栏毛玻璃：与资源详情页一致 —— 用壳（BaseSectionActivity）的控制器共用同一份快照
        if (actionBar != null && blur != null) {
            View source = view.findViewById(R.id.vd_detail_content);
            BarBlurController controller = requireActivity()
                    instanceof com.typheye.wgpro.ui.function.community.BaseSectionActivity
                    ? ((com.typheye.wgpro.ui.function.community.BaseSectionActivity) requireActivity())
                            .blurController()
                    : null;
            if (controller != null) {
                controller.rebindSource(source);
                controller.addBackdrop((android.widget.ImageView) blur);
            } else {
                BarBlurController.install(requireActivity(), source, (android.widget.ImageView) blur);
            }
        }
        if (actionBar != null) {
            final int baseBottom = actionBar.getPaddingBottom();
            androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(view, (v, insets) -> {
                int inset = SystemBars.bottomInsetForView(v, insets);
                actionBar.setPadding(actionBar.getPaddingLeft(), actionBar.getPaddingTop(),
                        actionBar.getPaddingRight(), baseBottom + inset);
                return insets;
            });
            androidx.core.view.ViewCompat.requestApplyInsets(view);
        }
        final View scroll = view.findViewById(R.id.vd_detail_scroll);
        // 顶部留白 = 应用栏高度（列表那套 paddingTop 不管本页的滚动视图，否则标题被应用栏压住）
        final View appBar = requireActivity().findViewById(R.id.app_bar_layout);
        if (appBar != null && scroll != null) {
            appBar.post(() -> {
                if (isAdded() && appBar.getHeight() > 0) {
                    com.typheye.wgpro.utils.AppBarBlur.padScrollableTop(scroll, appBar.getHeight());
                }
            });
        }
        // 垫高：滚动内容底部留出底栏高度，否则滑不到底
        view.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            if (actionBar == null || !(scroll instanceof NestedScrollView)) {
                return;
            }
            int barHeight = actionBar.getHeight();
            NestedScrollView sv = (NestedScrollView) scroll;
            if (barHeight > 0 && sv.getPaddingBottom() != barHeight) {
                sv.setClipToPadding(false);
                sv.setPadding(sv.getPaddingLeft(), sv.getPaddingTop(), sv.getPaddingRight(),
                        barHeight);
            }
        });
    }

    // ------------------------------------------------------------ 数据

    /** 先读 config.ini（介绍数据真源），失败则退回列表字段。 */
    private void loadConfigThenRender() {
        final String modelId = model.id;
        new Thread(() -> {
            Map<String, String> parsed = new HashMap<>();
            if (!TextUtils.isEmpty(modelId)) {
                try (Response response = client.newCall(new Request.Builder()
                        .url(IMAGE_BASE + modelId + "/config.ini").build()).execute()) {
                    if (response.body() != null) {
                        parsed.putAll(parseIni(response.body().string()));
                    }
                } catch (Exception error) {
                    android.util.Log.w("VdDetail", "读取 config.ini 失败", error);
                }
            }
            ui.post(() -> {
                if (!isAdded()) {
                    return;
                }
                config.clear();
                config.putAll(parsed);
                render();
            });
        }, "vd-config").start();
    }

    private void fetchModelThen(@Nullable String modelId) {
        if (TextUtils.isEmpty(modelId)) {
            WGProHint.showError(requireContext(), "机型信息缺失");
            shellLoading(false);
            return;
        }
        new Thread(() -> {
            String body = null;
            try (Response response = client.newCall(new Request.Builder().url(API).build()).execute()) {
                if (response.body() != null) {
                    body = response.body().string();
                }
            } catch (Exception error) {
                android.util.Log.w("VdDetail", "拉取机型失败", error);
            }
            final String json = body;
            ui.post(() -> {
                if (!isAdded()) {
                    return;
                }
                VelaModel found = null;
                try {
                    Root parsed = gson.fromJson(json, Root.class);
                    if (parsed != null && parsed.devices != null) {
                        for (VelaModel item : parsed.devices) {
                            if (modelId.equals(item.id)) {
                                found = item;
                                break;
                            }
                        }
                    }
                } catch (Exception ignored) {
                }
                if (found == null) {
                    shellLoading(false);
                    WGProHint.showError(requireContext(), "机型信息获取失败");
                    return;
                }
                model = found;
                loadConfigThenRender();
            });
        }, "vd-detail-model").start();
    }

    private static final class Root {
        List<VelaModel> devices;
    }

    /** 极简 INI 解析：key = "value"。 */
    @NonNull
    private static Map<String, String> parseIni(@NonNull String text) {
        Map<String, String> map = new HashMap<>();
        for (String line : text.split("\n")) {
            int eq = line.indexOf('=');
            if (eq <= 0) {
                continue;
            }
            String key = line.substring(0, eq).trim();
            String value = line.substring(eq + 1).trim();
            if (value.length() >= 2 && value.startsWith("\"") && value.endsWith("\"")) {
                value = value.substring(1, value.length() - 1);
            }
            if (!key.isEmpty()) {
                map.put(key, value);
            }
        }
        return map;
    }

    /** 取第一个非空值（两个都空返回空串）。 */
    @NonNull
    private static String firstNonEmpty(@Nullable String first, @Nullable String second) {
        return firstNonEmpty(first, second, "");
    }

    /** 取第一个非空值（都空则用兜底）。 */
    @NonNull
    private static String firstNonEmpty(@Nullable String first, @Nullable String second,
                                        @NonNull String fallback) {
        if (first != null && !first.trim().isEmpty()) {
            return first.trim();
        }
        if (second != null && !second.trim().isEmpty()) {
            return second.trim();
        }
        return fallback;
    }

    @NonNull
    private String cfg(@NonNull String key, @NonNull String fallback) {
        String value = config.get(key);
        return TextUtils.isEmpty(value) ? fallback : value;
    }

    private void render() {
        if (root == null || model == null) {
            return;
        }
        rendered = true;
        String title = cfg("title", TextUtils.isEmpty(model.name) ? "未命名机型" : model.name);
        // 优先用接口下发的（服务端已按 config.ini 读好）；抓不到时退回 ini 解析结果
        String author = firstNonEmpty(model.author, cfg("image-author", ""), "未知发布者");
        String source = firstNonEmpty(model.source, cfg("image-source", ""), "资源来自互联网");
        String code = firstNonEmpty(model.code, cfg("image-code", ""));
        String size = firstNonEmpty(model.sizeLabel, cfg("image-size", ""), "--");
        String version = firstNonEmpty(model.version, cfg("image-version", ""), "--");
        String date = firstNonEmpty(model.updatedAt, cfg("image-date", ""), "--");
        String downloadUrl = resolveDownloadUrl();
        String text = cfg("text", model.description);

        TextView titleView = root.findViewById(R.id.vd_detail_title);
        titleView.setText(title);
        titleView.setOnClickListener(v -> WGProHint.showInfo(requireContext(), "设备名称", title));
        TextView authorView = root.findViewById(R.id.vd_detail_author);
        authorView.setText(author);
        authorView.setOnClickListener(v -> WGProHint.showInfo(requireContext(), "发布者",
                author + "\n镜像由该发布者提供，腕管 Pro 仅做本地运行。"));
        TextView statement = root.findViewById(R.id.vd_detail_statement);
        statement.setText(source);
        statement.setOnClickListener(v -> WGProHint.showInfo(requireContext(), "来源说明",
                source + "\n如权利人认为内容不当，可联系我们下架。"));
        ((TextView) root.findViewById(R.id.vd_detail_description)).setText(
                TextUtils.isEmpty(text) ? "暂无介绍。" : text);

        // 数据条：大小 | 下载 | 适配（都有点击说明）
        TextView sizeView = root.findViewById(R.id.vd_detail_size);
        sizeView.setText(TextUtils.isEmpty(size) ? "--" : size);
        tapMeta(R.id.vd_detail_cell_size, "镜像大小",
                "下载需要 " + (TextUtils.isEmpty(size) ? "未知" : size) + "\n包含系统镜像与引擎。");
        TextView downView = root.findViewById(R.id.vd_detail_downloads);
        String down = model.downloadCount > 0 ? String.valueOf(model.downloadCount)
                : cfg("image-down", "0");
        downView.setText(down);
        tapMeta(R.id.vd_detail_cell_downloads, "下载次数", "该镜像累计下载 " + down + " 次。");

        // 封面（1:1）：点击看大图
        View coverScroll = root.findViewById(R.id.vd_detail_cover_scroll);
        com.google.android.material.imageview.ShapeableImageView cover =
                root.findViewById(R.id.vd_detail_cover);
        String coverUrl = cfg("coverUrl", model.coverUrl);
        if (!TextUtils.isEmpty(coverUrl)) {
            coverUrl = coverUrl.startsWith("http") ? coverUrl : IMAGE_BASE + model.id + "/cover.png";
        }
        if (coverScroll != null && cover != null) {
            if (TextUtils.isEmpty(coverUrl)) {
                coverScroll.setVisibility(View.GONE);
            } else {
                coverScroll.setVisibility(View.VISIBLE);
                cover.setShapeAppearanceModel(cover.getShapeAppearanceModel().toBuilder()
                        .setAllCornerSizes(dp(16)).build());
                final String coverFinal = coverUrl;
                ImageCache.loadBitmap(requireContext(), coverFinal, bitmap -> {
                    if (bitmap != null && isAdded()) {
                        cover.setImageBitmap(bitmap);
                    }
                });
                cover.setOnClickListener(v -> {
                    List<String> shots = new ArrayList<>();
                    shots.add(coverFinal);
                    com.typheye.wgpro.ui.function.PreviewActivity.open(requireContext(), shots, 0);
                });
            }
        }

        // 更多：只留有意义的信息
        LinearLayout infoList = root.findViewById(R.id.vd_detail_info_list);
        infoList.removeAllViews();
        addInfoRow(infoList, "机型标识", code);
        addInfoRow(infoList, "镜像版本", version);
        addInfoRow(infoList, "更新日期", date);

        shellLoading(false);
        refreshAction();
    }

    /** config.ini 的 aurl 是相对路径；列表 JSON 的 imageUrl 是权威绝对地址。 */
    @NonNull
    private String resolveDownloadUrl() {
        if (model != null && !TextUtils.isEmpty(model.imageUrl)) {
            return model.imageUrl;
        }
        String aurl = config.get("aurl");
        if (!TextUtils.isEmpty(aurl) && model != null) {
            return IMAGE_BASE + model.id + "/" + aurl.replace("./", "");
        }
        return "";
    }

    private void tapMeta(int id, @NonNull String title, @NonNull String message) {
        View view = root == null ? null : root.findViewById(id);
        if (view != null) {
            view.setOnClickListener(v -> WGProHint.showInfo(requireContext(), title, message));
        }
    }

    private void addInfoRow(@NonNull LinearLayout list, @NonNull String label, @Nullable String value) {
        if (TextUtils.isEmpty(value)) {
            return;
        }
        android.content.Context context = list.getContext();
        LinearLayout row = new LinearLayout(context);
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(android.view.Gravity.CENTER_VERTICAL);
        row.setPadding(0, dp(7), 0, dp(7));
        TextView labelView = new TextView(context);
        labelView.setText(label);
        labelView.setTextColor(WGProColors.of(context, R.color.text_secondary));
        labelView.setTextSize(15f);
        row.addView(labelView, new LinearLayout.LayoutParams(dp(84), -2));
        TextView valueView = new TextView(context);
        valueView.setText(value);
        valueView.setTextColor(WGProColors.of(context, R.color.text_primary));
        valueView.setTextSize(15f);
        valueView.setSingleLine(true);
        valueView.setEllipsize(android.text.TextUtils.TruncateAt.END);
        row.addView(valueView, new LinearLayout.LayoutParams(0, -2, 1f));
        AppUtils.longPressToCopy(valueView);
        list.addView(row);
    }

    /** 壳的整页加载遮罩：显示 / 关闭（关闭自带最短展示时间）。 */
    private void shellLoading(boolean show) {
        if (!(requireActivity() instanceof com.typheye.wgpro.ui.function.community.BaseSectionActivity)) {
            return;
        }
        com.typheye.wgpro.ui.function.community.BaseSectionActivity host =
                (com.typheye.wgpro.ui.function.community.BaseSectionActivity) requireActivity();
        if (show) {
            host.showContentLoading();
        } else {
            host.hideContentLoading();
        }
    }

    // ------------------------------------------------------------ 底栏状态

    private void observeTasks() {
        clearObserver();
        observer = tasks -> {
            if (isAdded()) {
                refreshAction();
            }
        };
        DownloadManager.get().tasks().observeForever(observer);
    }

    private void clearObserver() {
        if (observer != null) {
            DownloadManager.get().tasks().removeObserver(observer);
            observer = null;
        }
    }

    private void clearCompletionObserver() {
        if (completionObserver != null) {
            DownloadManager.get().tasks().removeObserver(completionObserver);
            completionObserver = null;
        }
    }

    @Nullable
    private DownloadTask currentTask() {
        if (model == null) {
            return null;
        }
        String taskId = VelaProvision.PACKAGE_PREFIX + model.id;
        List<DownloadTask> tasks = DownloadManager.get().tasks().getValue();
        if (tasks == null) {
            return null;
        }
        for (DownloadTask task : tasks) {
            if (taskId.equals(task.id)) {
                return task;
            }
        }
        return null;
    }

    /** 底栏状态机：与资源详情页一比一（进行中点按=暂停/继续，完成=启动|删除 分割胶囊）。 */
    private void refreshAction() {
        if (!rendered || model == null || actionText == null) {
            return;
        }
        final DownloadTask task = currentTask();
        final DownloadManager manager = DownloadManager.get();

        if (task == null) {
            applyIdleState();
            return;
        }

        if (deleteCard != null) {
            deleteCard.setVisibility(View.GONE);
        }
        switch (task.status) {
            case DownloadTask.STATUS_RUNNING:
                setActionText(task.progress() + "%");
                setActionFill(task.progress());
                if (actionCard != null) {
                    actionCard.setOnClickListener(v -> manager.control(requireContext(), task.id,
                            com.typheye.wgpro.core.download.DownloadService.ACTION_PAUSE));
                }
                break;
            case DownloadTask.STATUS_QUEUED:
                setActionText("等待中");
                setActionFill(0);
                if (actionCard != null) {
                    actionCard.setOnClickListener(v -> manager.control(requireContext(), task.id,
                            com.typheye.wgpro.core.download.DownloadService.ACTION_PAUSE));
                }
                break;
            case DownloadTask.STATUS_PAUSED:
                setActionText("继续");
                setActionFill(task.progress());
                if (actionCard != null) {
                    actionCard.setOnClickListener(v -> manager.control(requireContext(), task.id,
                            com.typheye.wgpro.core.download.DownloadService.ACTION_RESUME));
                }
                break;
            case DownloadTask.STATUS_FAILED:
                setActionText("重试");
                setActionFill(0);
                if (actionCard != null) {
                    actionCard.setOnClickListener(v -> com.typheye.wgpro.ui.widget.DownloadRetryFlow
                            .retry(requireContext(), task, null));
                }
                break;
            case DownloadTask.STATUS_COMPLETED:
                applyIdleState();
                break;
            default:
                break;
        }
    }

    /**
     * 空闲态（没有进行中的任务）：
     * <ul>
     *   <li>镜像已下到本地 → 「**创建**｜删除」（删除只删镜像，不动已创建的设备）</li>
     *   <li>还没下载 → 「获取」（先测速再下载）</li>
     * </ul>
     */
    private void applyIdleState() {
        boolean downloaded = model != null
                && VelaProvision.packageFile(requireContext(), model.id).isFile();
        if (downloaded) {
            if (deleteCard != null) {
                deleteCard.setVisibility(View.VISIBLE);
                deleteCard.setOnClickListener(v -> confirmDelete());
            }
            applySplitPillShape(true);
            setActionText("创建");
            setActionFill(0);
            if (actionCard != null) {
                actionCard.setOnClickListener(v -> showCreateSheet());
            }
            return;
        }
        if (deleteCard != null) {
            deleteCard.setVisibility(View.GONE);
        }
        applySplitPillShape(false);
        setActionFill(0);
        setActionText("获取");
        if (actionCard != null) {
            actionCard.setOnClickListener(v -> onAcquire());
        }
    }

    /** 「创建」：底部弹窗编辑框让用户设设备名（留空用默认名），确认后装机并写入模拟设备列表。 */
    private void showCreateSheet() {
        if (model == null) {
            return;
        }
        View content = getLayoutInflater().inflate(R.layout.dialog_edittext, null, false);
        com.google.android.material.textfield.TextInputLayout layout =
                content.findViewById(R.id.textInputLayout);
        com.google.android.material.textfield.TextInputEditText input =
                content.findViewById(R.id.editText);
        layout.setHint("设备名称");
        input.setSingleLine(true);
        input.setText("");
        com.typheye.wgpro.ui.widget.WGProBottomSheetDialog dialog =
                new WGProAlertDialogBuilder(requireContext())
                        .setTitle("创建设备")
                        .setMessage("留空则使用默认设备名称")
                        .setView(content)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("创建", null)
                        .create();
        dialog.setOnShowListener(ignored ->
                dialog.getButton(android.content.DialogInterface.BUTTON_POSITIVE)
                        .setOnClickListener(v -> {
                            String name = input.getText() == null ? ""
                                    : input.getText().toString().trim();
                            dialog.dismiss();
                            // 空 → 默认名 = 云端「设备名称」字段 + 4 位随机（不是机型标识，便于区分）
                            provisionWithName(name.isEmpty()
                                    ? (model.name == null || model.name.trim().isEmpty()
                                            ? model.id : model.name.trim())
                                            + " " + VelaProvision.randomSuffix()
                                    : name);
                        }));
        dialog.show();
    }

    /** 装机（解包 → 铺 AVD → 写库），名称可为空（空则用默认名「设备名称 XXXX」）。 */
    private void provisionWithName(@Nullable String displayName) {
        if (model == null || !isAdded()) {
            return;
        }
        final String modelId = model.id;
        final android.content.Context app = requireContext().getApplicationContext();
        // 进度用**底部弹窗圆形进度**（带取消）：整页加载遮罩只允许在 Activity 创建时出现
        View progressView = getLayoutInflater().inflate(R.layout.progress_dialog, null, false);
        ((TextView) progressView.findViewById(android.R.id.message)).setText("正在创建模拟器…");
        final com.typheye.wgpro.ui.widget.WGProBottomSheetDialog[] progressRef =
                new com.typheye.wgpro.ui.widget.WGProBottomSheetDialog[1];
        progressRef[0] = new WGProAlertDialogBuilder(requireContext())
                .setTitle("正在创建")
                .setView(progressView)
                .setCancelable(false)
                .setNegativeButton("取消", null)
                .create();
        progressRef[0].show();
        new Thread(() -> {
            String failure = null;
            String deviceId = null;
            VelaEmulator unpacked = null;
            try {
                deviceId = com.typheye.wgpro.core.emu.VelaEmulatorManager.deviceId(modelId)
                        + ":" + VelaProvision.randomSuffix();
                // 第一步只解压（engine/ + images/），**不建库** —— 建哪种设备由用户选
                unpacked = VelaProvision.unpackRuntime(app, modelId, deviceId);
            } catch (Exception error) {
                failure = error.getMessage() == null ? "创建失败" : error.getMessage();
            }
            final String unpackFailure = failure;
            final String newDeviceId = deviceId;
            final VelaEmulator emulator = unpacked;
            ui.post(() -> {
                if (!isAdded()) {
                    return;
                }
                if (progressRef[0] != null) {
                    progressRef[0].dismiss();
                }
                if (unpackFailure != null) {
                    WGProHint.showError(requireContext(), "创建失败：" + unpackFailure);
                    return;
                }
                // 解压完成 → **直接创建包内声明的那一台**（单包单设备，不再让用户选）
                createDeclaredDevice(newDeviceId, emulator, displayName);
            });
        }, "vd-provision").start();
    }

    /**
     * 解压完成后**直接创建包内声明的那一台**（单包单设备，不再让用户选）。
     *
     * <p>包里没声明设备就报错 —— **不兼容**"没有清单"或"一个包多台"的老包。</p>
     */
    private void createDeclaredDevice(@NonNull String deviceId, @NonNull VelaEmulator emulator,
                                      @Nullable String displayName) {
        java.util.List<String[]> manifest = VelaProvision.manifestDevices(emulator.runtimeDir());
        if (manifest.isEmpty()) {
            WGProHint.showError(requireContext(), "镜像包不合法：config.json 里没有 devices 声明");
            return;
        }
        createWithSkin(deviceId, emulator, manifest.get(0)[2], displayName, manifest.get(0)[1]);
    }

    /** 按选定皮肤写 AVD + 落库（同一份镜像可分别建出多款设备）。 */
    private void createWithSkin(@NonNull String deviceId, @NonNull VelaEmulator emulator,
                                @NonNull String skin, @Nullable String displayName,
                                @NonNull String deviceName) {
        final android.content.Context app = requireContext().getApplicationContext();
        View progressView = getLayoutInflater().inflate(R.layout.progress_dialog, null, false);
        ((TextView) progressView.findViewById(android.R.id.message)).setText("正在创建模拟器…");
        final com.typheye.wgpro.ui.widget.WGProBottomSheetDialog[] ref =
                new com.typheye.wgpro.ui.widget.WGProBottomSheetDialog[1];
        ref[0] = new WGProAlertDialogBuilder(requireContext())
                .setTitle("正在创建")
                .setView(progressView)
                .setCancelable(false)
                .setNegativeButton("取消", null)
                .create();
        ref[0].show();
        new Thread(() -> {
            String failure = null;
            try {
                // 显示名优先用调用方给的，其次用清单里的设备名（不再落到"设备名称 XXXX"）
                String name = displayName == null || displayName.trim().isEmpty()
                        ? deviceName + " " + VelaProvision.randomSuffix()
                        : displayName;
                VelaProvision.createDevice(app, emulator, deviceId, skin, name);
            } catch (Exception error) {
                failure = error.getMessage() == null ? "创建失败" : error.getMessage();
            }
            final String result = failure;
            ui.post(() -> {
                if (!isAdded()) {
                    return;
                }
                if (ref[0] != null) {
                    ref[0].dismiss();
                }
                if (result != null) {
                    WGProHint.showError(requireContext(), "创建失败：" + result);
                    return;
                }
                refreshAction();
                // 创建成功：底部弹窗告知，点「确定」后带 RESULT_OK 关闭本页；
                // 机型列表页收到结果会把自己也关掉 → 逐级退回「设备」页并自动刷新。
                new WGProAlertDialogBuilder(requireContext())
                        .setTitle("创建成功")
                        .setMessage("模拟器创建成功，返回设备页即可查看（同一个镜像可以创建多个设备）。")
                        .setPositiveButton("确定", (dialog, which) -> {
                            requireActivity().setResult(android.app.Activity.RESULT_OK);
                            requireActivity().finish();
                        })
                        .show();
            });
        }, "vd-create").start();
    }

    /** 分割胶囊：主按钮只留左侧圆角，删除按钮贴上去（与资源详情页同款）。 */
    private void applySplitPillShape(boolean split) {
        if (split == lastSplitState) {
            return;
        }
        lastSplitState = split;
        final float radius = 25f * getResources().getDisplayMetrics().density;
        if (actionCard instanceof com.google.android.material.card.MaterialCardView) {
            com.google.android.material.shape.ShapeAppearanceModel.Builder builder =
                    com.google.android.material.shape.ShapeAppearanceModel.builder();
            if (split) {
                builder.setTopLeftCorner(com.google.android.material.shape.CornerFamily.ROUNDED, radius)
                        .setBottomLeftCorner(com.google.android.material.shape.CornerFamily.ROUNDED, radius)
                        .setTopRightCorner(com.google.android.material.shape.CornerFamily.ROUNDED, 0f)
                        .setBottomRightCorner(com.google.android.material.shape.CornerFamily.ROUNDED, 0f);
            } else {
                builder.setAllCorners(com.google.android.material.shape.CornerFamily.ROUNDED, radius);
            }
            ((com.google.android.material.card.MaterialCardView) actionCard)
                    .setShapeAppearanceModel(builder.build());
        }
        if (deleteCard instanceof com.google.android.material.card.MaterialCardView) {
            com.google.android.material.shape.ShapeAppearanceModel.Builder builder =
                    com.google.android.material.shape.ShapeAppearanceModel.builder();
            if (split) {
                builder.setTopLeftCorner(com.google.android.material.shape.CornerFamily.ROUNDED, 0f)
                        .setBottomLeftCorner(com.google.android.material.shape.CornerFamily.ROUNDED, 0f)
                        .setTopRightCorner(com.google.android.material.shape.CornerFamily.ROUNDED, radius)
                        .setBottomRightCorner(com.google.android.material.shape.CornerFamily.ROUNDED, radius)
                        .setTopLeftCorner(com.google.android.material.shape.CornerFamily.ROUNDED, 0f);
            } else {
                builder.setAllCorners(com.google.android.material.shape.CornerFamily.ROUNDED, radius);
            }
            ((com.google.android.material.card.MaterialCardView) deleteCard)
                    .setShapeAppearanceModel(builder.build());
        }
    }

    /** 药丸文字：常规层 + 填充区域反色层同步；没变化就不动（防闪）。 */
    private void setActionText(CharSequence text) {
        if (text == null) {
            text = "";
        }
        if (android.text.TextUtils.equals(lastActionText, text)) {
            return;
        }
        lastActionText = text;
        if (actionText != null) {
            actionText.setText(text);
        }
        if (actionTextFill != null) {
            actionTextFill.setText(text);
        }
    }

    /** 填充宽度 + 反色文字裁剪（与资源详情页同款）。 */
    private void setActionFill(int progress) {
        if (actionCard == null || actionFill == null) {
            return;
        }
        int width = actionCard.getWidth();
        if (width <= 0) {
            actionCard.post(() -> setActionFill(progress));
            return;
        }
        int desired = Math.max(0, Math.round(width * progress / 100f));
        if (desired == lastFillWidth) {
            return;
        }
        lastFillWidth = desired;
        ViewGroup.LayoutParams params = actionFill.getLayoutParams();
        if (params.width != desired) {
            params.width = desired;
            actionFill.setLayoutParams(params);
        }
        if (actionTextFill != null) {
            if (desired <= 0) {
                if (actionTextFill.getVisibility() != View.INVISIBLE) {
                    actionTextFill.setVisibility(View.INVISIBLE);
                }
                return;
            }
            if (actionTextFill.getVisibility() != View.VISIBLE) {
                actionTextFill.setVisibility(View.VISIBLE);
            }
            actionTextFill.setClipBounds(new android.graphics.Rect(0, 0, desired,
                    Math.max(actionTextFill.getHeight(), actionCard.getHeight())));
        }
    }


    /** 「获取」：先复用下载测速选最快节点，再入队。 */
    private void onAcquire() {
        if (model == null) {
            return;
        }
        String url = resolveDownloadUrl();
        if (TextUtils.isEmpty(url)) {
            WGProHint.showError(requireContext(), "暂无可用镜像地址");
            return;
        }
        com.typheye.wgpro.ui.widget.DownloadProbeSheet.start(requireContext(),
                cfg("title", model.name), url, mirrorsOf(model),
                new com.typheye.wgpro.ui.widget.DownloadProbeSheet.Callback() {
                    @Override
                    public void onReady(@NonNull String chosen) {
                        enqueue(chosen);
                    }

                    @Override
                    public void onCancelled() {
                    }
                });
    }

    @Nullable
    private static JSONArray mirrorsOf(@NonNull VelaModel model) {
        if (model.downloadMirrors == null || model.downloadMirrors.isEmpty()) {
            return null;
        }
        JSONArray array = new JSONArray();
        for (VelaModel.Mirror mirror : model.downloadMirrors) {
            if (mirror == null || TextUtils.isEmpty(mirror.id) || TextUtils.isEmpty(mirror.url)) {
                continue;
            }
            try {
                JSONObject item = new JSONObject();
                item.put("id", mirror.id);
                item.put("url", mirror.url);
                array.put(item);
            } catch (Exception ignored) {
            }
        }
        return array.length() == 0 ? null : array;
    }

    private void enqueue(@NonNull String url) {
        if (model == null || !isAdded()) {
            return;
        }
        String taskId = VelaProvision.PACKAGE_PREFIX + model.id;
        // 头像固定走「单文字矩形头像」（与资源详情页一致）：**不传封面**，否则下载管理里会显示预览图。
        // 注意：这里必须保持 iconUrl 为空，历史上被来回改过，别再动。
        DownloadTask task = new DownloadTask(taskId, cfg("title", model.name),
                "", url, model.sizeLabel, DownloadTask.DEST_APP_FILE);
        task.fallbackUrl = resolveDownloadUrl();
        DownloadManager.get().enqueue(requireContext(), task);
        DownloadManager.get().startService(requireContext());
        observeTasks();
        refreshAction();
        watchCompletion(taskId);
    }

    private void watchCompletion(@NonNull String taskId) {
        clearCompletionObserver();
        completionObserver = tasks -> {
            for (DownloadTask task : tasks) {
                if (!taskId.equals(task.id)) {
                    continue;
                }
                if (task.status == DownloadTask.STATUS_FAILED) {
                    clearCompletionObserver();
                    if (isAdded()) {
                        WGProHint.showError(requireContext(),
                                "下载失败：" + (task.error == null ? "镜像不可用" : task.error));
                    }
                    return;
                }
                if (task.status == DownloadTask.STATUS_COMPLETED) {
                    // 只结束观察、刷新按钮（镜像下载完成 → 按钮变「创建」）；
                    // **不再自动创建**：创建必须用户点「创建」并自定名称，否则默认名会不对。
                    clearCompletionObserver();
                    if (isAdded()) {
                        lastActionText = null;
                        refreshAction();
                    }
                    return;
                }
            }
        };
        DownloadManager.get().tasks().observeForever(completionObserver);
    }

    private void provision() {
        if (model == null || !isAdded()) {
            return;
        }
        final String modelId = model.id;
        final android.content.Context app = requireContext().getApplicationContext();
        new Thread(() -> {
            final String[] failure = new String[1];
            VelaProvision.provision(app, modelId, (success, error) -> failure[0] = success ? null : error);
            ui.post(() -> {
                if (!isAdded()) {
                    return;
                }
                if (failure[0] != null) {
                    WGProHint.showError(requireContext(), "创建失败：" + failure[0]);
                    return;
                }
                WGProHint.show(requireContext(), "设备已创建");
                createdCheckedAt = 0L;
                refreshAction();
            });
        }, "vd-provision").start();
    }

    /** 删除：**只删已下载的镜像**，已经创建的设备保留（设备在「模拟设备」里长按可移除）。 */
    private void confirmDelete() {
        if (model == null) {
            return;
        }
        new WGProAlertDialogBuilder(requireContext())
                .setTitle("删除镜像")
                .setMessage("只删除已下载的镜像包；已经创建的设备会保留，「模拟设备」里的设备不受影响。")
                .setNegativeButton("取消", null)
                .setPositiveButton("删除", (dialog, which) -> {
                    final android.content.Context app = requireContext().getApplicationContext();
                    new Thread(() -> {
                        VelaProvision.removeImage(app, model.id);
                        ui.post(() -> {
                            if (!isAdded()) {
                                return;
                            }
                            lastFillWidth = -1;
                            lastActionText = null;
                            refreshAction();
                            WGProHint.show(requireContext(), "已删除镜像");
                        });
                    }, "vd-delete").start();
                })
                .show();
    }

    private int fillInkColor() {
        int fill = WGProAlertDialogBuilder.resolveThemeColor(requireContext(),
                "colorPrimary", R.color.brand_primary);
        return androidx.core.graphics.ColorUtils.calculateLuminance(fill) > 0.5
                ? requireContext().getColor(R.color.text_on_fill)
                : requireContext().getColor(R.color.white);
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    @Override
    public void onDestroyView() {
        clearObserver();
        clearCompletionObserver();
        root = null;
        super.onDestroyView();
    }
}
