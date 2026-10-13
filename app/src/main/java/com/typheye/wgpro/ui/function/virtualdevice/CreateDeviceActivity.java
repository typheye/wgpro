package com.typheye.wgpro.ui.function.virtualdevice;

import android.content.Context;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.typheye.wgpro.R;
import com.typheye.wgpro.core.download.DownloadManager;
import com.typheye.wgpro.core.download.DownloadTask;
import com.typheye.wgpro.core.emu.VelaEmulator;
import com.typheye.wgpro.core.emu.VelaEmulatorManager;
import com.typheye.wgpro.data.DeviceDatabase;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProHint;
import com.typheye.wgpro.utils.AppBarBlur;
import com.typheye.wgpro.utils.AppUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 创建设备（虚拟设备）：从「模拟设备」页右上角 + 进入。
 *
 * <p>机型列表来自云端（{@code api.php?type=vela_devices}）；点卡片弹底部确认，
 * 确认后走应用自带下载服务取单个镜像包，完成后解包、铺 AVD、写入设备库并进入模拟页面。
 * 应用栏毛玻璃与 insets 由本页按 {@code _guide/gaosi-mix.md} 统一适配（结构同 activity_test.xml）。</p>
 */
public class CreateDeviceActivity extends AppCompatActivity {

    private static final String API = "https://service.typheye.cn/api.php?type=vela_devices";

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();
    private final Handler ui = new Handler(Looper.getMainLooper());
    private final List<VelaModel> models = new ArrayList<>();

    private RecyclerView listView;
    private View loadingOverlay;
    private com.typheye.wgpro.ui.widget.WGProRefreshLayout refreshLayout;
    private View emptyView;
    private ModelAdapter adapter;
    private Observer<List<DownloadTask>> downloadObserver;
    /** 进入遮罩只在 Activity 创建后的首次拉取出现，下拉刷新不再出现。 */
    private boolean firstLoad = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.useScreenCutArea(getWindow(), this);
        setContentView(R.layout.activity_create_device);

        Toolbar toolbar = findViewById(R.id.create_device_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        listView = findViewById(R.id.create_device_list);
        loadingOverlay = findViewById(R.id.create_device_loading_overlay);
        emptyView = findViewById(R.id.create_device_state);
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
        refreshLayout = findViewById(R.id.create_device_refresh);
        refreshLayout.setOnRefreshListener(this::fetchModels);
        listView.setLayoutManager(new LinearLayoutManager(this));
        final int side = Math.round(getResources().getDisplayMetrics().density * 16f);
        final int gap = Math.round(getResources().getDisplayMetrics().density * 10f);
        // 顶边距规则：第一项距"可视范围上边缘（应用栏下沿）"= 该页左右留白（16dp）；
        // 应用栏高度那份留白由 AppBarBlur 写在列表 paddingTop 上，这里只补左右留白那一段。
        listView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull android.graphics.Rect outRect, @NonNull View view,
                                       @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = parent.getChildAdapterPosition(view) == 0 ? side : gap;
            }
        });
        adapter = new ModelAdapter();
        listView.setAdapter(adapter);

        // 视图适配：快照源=不滚动的内容容器，滚动内容=列表（与 activity_test.xml 同一套做法）
        AppBarBlur.installWithScrollContent(this,
                findViewById(R.id.app_bar_layout),
                findViewById(R.id.create_device_content_host),
                listView);

        fetchModels();
    }

    // ------------------------------------------------------------ 机型列表

    private void fetchModels() {
        if (firstLoad) {
            firstLoad = false;
            loadingOverlay.setVisibility(View.VISIBLE);
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
                android.util.Log.w("CreateDevice", "拉取机型失败", error);
            }
            final String json = body;
            ui.post(() -> applyModels(json));
        }, "vela-models").start();
    }

    private void applyModels(@Nullable String json) {
        loadingOverlay.setVisibility(View.GONE);
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
            View item = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_vela_model, parent, false);
            return new ModelHolder(item);
        }

        @Override
        public void onBindViewHolder(@NonNull ModelHolder holder, int position) {
            VelaModel model = models.get(position);
            // 第一行=设备名称，第二行=镜像文件名（标识），无其他字符
            holder.title.setText(model.name);
            holder.subtitle.setText(model.code);
            holder.initial.setText(model.name == null || model.name.isEmpty()
                    ? "?" : model.name.substring(0, 1).toUpperCase());
            holder.itemView.setOnClickListener(v -> confirmCreate(model));
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

    // ------------------------------------------------------------ 创建流程

    private void confirmCreate(@NonNull VelaModel model) {
        new WGProAlertDialogBuilder(this)
                .setTitle("创建设备")
                .setMessage(model.detail())
                .setNegativeButton("取消", null)
                .setPositiveButton("创建", (dialog, which) -> startCreate(model))
                .show();
    }

    private void startCreate(@NonNull VelaModel model) {
        Context context = getApplicationContext();
        String taskId = "vela-" + model.id;
        DownloadTask task = new DownloadTask(taskId, model.name, "", model.imageUrl,
                model.sizeLabel, DownloadTask.DEST_APP_FILE);
        DownloadManager.get().enqueue(context, task);
        DownloadManager.get().startService(context);
        WGProHint.show(this, "已加入下载，完成后自动创建");
        observeDownload(taskId, model);
    }

    private void observeDownload(@NonNull String taskId, @NonNull VelaModel model) {
        clearObserver();
        downloadObserver = tasks -> {
            for (DownloadTask task : tasks) {
                if (!taskId.equals(task.id)) {
                    continue;
                }
                if (task.status == DownloadTask.STATUS_FAILED) {
                    clearObserver();
                    WGProHint.showError(this,
                            "下载失败：" + (task.error == null ? "镜像不可用" : task.error));
                } else if (task.status == DownloadTask.STATUS_COMPLETED) {
                    clearObserver();
                    provision(model);
                }
                return;
            }
        };
        DownloadManager.get().tasks().observeForever(downloadObserver);
    }

    private void clearObserver() {
        if (downloadObserver != null) {
            DownloadManager.get().tasks().removeObserver(downloadObserver);
            downloadObserver = null;
        }
    }

    /** 解包 → 铺 AVD → 写库 → 进入模拟页面。 */
    private void provision(@NonNull VelaModel model) {
        loadingOverlay.setVisibility(View.VISIBLE);
        final Context context = getApplicationContext();
        new Thread(() -> {
            try {
                File zip = new File(new File(context.getFilesDir(), "downloads"),
                        "vela-" + model.id + ".apk");
                VelaEmulator emulator = new VelaEmulator(context);
                unzip(zip, emulator.runtimeDir());
                emulator.installExecLinks();
                emulator.authorAvd(model.id, emulator.imagesDir());
                try (DeviceDatabase db = new DeviceDatabase(context)) {
                    db.upsert(VelaEmulatorManager.deviceId(model.id), model.id,
                            DeviceDatabase.TYPE_VIRTUAL, false);
                    db.updateNote(VelaEmulatorManager.deviceId(model.id), model.name);
                }
                ui.post(() -> {
                    loadingOverlay.setVisibility(View.GONE);
                    WGProHint.show(this, "设备已创建");
                    Intent intent = new Intent(this, VirtualDeviceActivity.class);
                    intent.putExtra(VirtualDeviceFragment.ARG_AVD, model.id);
                    startActivity(intent);
                    finish();
                });
            } catch (Exception error) {
                ui.post(() -> {
                    loadingOverlay.setVisibility(View.GONE);
                    WGProHint.showError(this, "创建失败：" + error.getMessage());
                });
            }
        }, "vela-provision").start();
    }

    private static void unzip(@NonNull File zip, @NonNull File targetDir) throws Exception {
        if (!targetDir.exists() && !targetDir.mkdirs()) {
            throw new IllegalStateException("无法创建运行目录");
        }
        String root = targetDir.getCanonicalPath();
        try (InputStream raw = new BufferedInputStream(new FileInputStream(zip));
             ZipInputStream in = new ZipInputStream(raw)) {
            ZipEntry entry;
            while ((entry = in.getNextEntry()) != null) {
                File out = new File(targetDir, entry.getName());
                if (!out.getCanonicalPath().startsWith(root)) {
                    throw new IllegalStateException("压缩包内容非法");
                }
                if (entry.isDirectory()) {
                    //noinspection ResultOfMethodCallIgnored
                    out.mkdirs();
                    continue;
                }
                File parent = out.getParentFile();
                if (parent != null && !parent.exists()) {
                    //noinspection ResultOfMethodCallIgnored
                    parent.mkdirs();
                }
                try (OutputStream os = new FileOutputStream(out)) {
                    byte[] buffer = new byte[1 << 16];
                    int n;
                    while ((n = in.read(buffer)) > 0) {
                        os.write(buffer, 0, n);
                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        clearObserver();
        super.onDestroy();
    }
}
