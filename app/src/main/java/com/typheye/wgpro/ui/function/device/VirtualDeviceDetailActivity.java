package com.typheye.wgpro.ui.function.device;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.typheye.wgpro.ui.function.community.BaseSectionActivity;

/**
 * 虚拟设备详情：与 {@code AppDetailActivity} / {@code ResourceDetailActivity} **同构**的二级页
 * （继承 {@link BaseSectionActivity}，宿主布局用壳的 {@code activity_section_host}）。
 *
 * <ul>
 *   <li>{@code screenTitle()} → {@code ""}：应用栏空文本（标题在内容里）</li>
 *   <li>{@code loadingCoversAppBar()} → {@code true}：整页加载遮罩盖住应用栏</li>
 *   <li>{@code createContent()} → {@link VirtualDeviceDetailFragment}</li>
 * </ul>
 */
public final class VirtualDeviceDetailActivity extends BaseSectionActivity {

    public static final String EXTRA_MODEL_JSON = "model_json";
    public static final String EXTRA_MODEL_ID = "model_id";

    @Override
    protected boolean loadingCoversAppBar() {
        return true;
    }

    @Override
    protected String screenTitle() {
        return "";
    }

    @Override
    protected Fragment createContent() {
        Intent intent = getIntent();
        String json = intent == null ? null : intent.getStringExtra(EXTRA_MODEL_JSON);
        if (!TextUtils.isEmpty(json)) {
            return VirtualDeviceDetailFragment.newInstance(new Gson().fromJson(json, VelaModel.class));
        }
        return VirtualDeviceDetailFragment.newInstanceById(
                intent == null ? null : intent.getStringExtra(EXTRA_MODEL_ID));
    }

    /** 入口一：已有完整机型对象（列表页点卡片）。 */
    public static void openModel(@NonNull Context context, @NonNull VelaModel model) {
        context.startActivity(new Intent(context, VirtualDeviceDetailActivity.class)
                .putExtra(EXTRA_MODEL_JSON, new Gson().toJson(model)));
    }

    /** 入口二：只有机型标识（下载管理里点系统镜像）。 */
    public static void openById(@NonNull Context context, @NonNull String modelId) {
        context.startActivity(new Intent(context, VirtualDeviceDetailActivity.class)
                .putExtra(EXTRA_MODEL_ID, modelId));
    }
}
