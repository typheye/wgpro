package com.typheye.wgpro.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.typheye.wgpro.R;
import com.typheye.wgpro.core.emu.VelaEmulatorManager;
import com.typheye.wgpro.core.emu.VelaInstall;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 把 rpk 装进**模拟设备**的统一交互流程（设备卡片页「安装到…」与模拟页「导入应用」共用一套，
 * 避免两边行为分叉）。
 *
 * <p>规范（{@code _guide/app-ui-spec.md} §3）：等待类弹窗**必须留「取消」**；
 * {@code WGProAlertDialogBuilder} 对含 {@code WGProLoadingView} 的自定义视图会自动补「取消」，
 * 这里同时显式给一个，保证语义明确。</p>
 *
 * <p>结果：成功 → 弹「安装成功」并问是否**前往模拟页面**；失败 → 底部「安装失败」，按钮只有「确定」。</p>
 */
public final class VelaInstallFlow {

    private VelaInstallFlow() {
    }

    /**
     * @param askOpenPage 成功后是否询问「前往模拟页面」（在模拟页里导入时传 false，别问了）
     */
    public static void install(@NonNull Fragment fragment, @NonNull Context app,
                               @NonNull String deviceId, @NonNull String model,
                               @NonNull String deviceName, @NonNull File rpk,
                               boolean askOpenPage) {
        if (!fragment.isAdded()) {
            return;
        }
        View progressView = fragment.getLayoutInflater()
                .inflate(R.layout.progress_dialog, null, false);
        ((TextView) progressView.findViewById(android.R.id.message))
                .setText("正在安装到「" + deviceName + "」…");
        final AtomicBoolean cancelled = new AtomicBoolean(false);
        final WGProBottomSheetDialog[] ref = new WGProBottomSheetDialog[1];
        ref[0] = new WGProAlertDialogBuilder(fragment.requireContext())
                .setTitle("正在安装")
                .setView(progressView)
                .setCancelable(false)
                .setNegativeButton("取消", (d, w) -> cancelled.set(true))
                .create();
        ref[0].show();
        new Thread(() -> VelaInstall.rpkToDevice(app, model, deviceId, rpk, (success, message) ->
                new Handler(Looper.getMainLooper()).post(() -> {
                    if (ref[0] != null) {
                        ref[0].dismissForReplacement();
                    }
                    if (!fragment.isAdded() || cancelled.get()) {
                        return;
                    }
                    if (success) {
                        WGProAlertDialogBuilder done = new WGProAlertDialogBuilder(
                                fragment.requireContext())
                                .setTitle("安装成功")
                                .setMessage(message + (askOpenPage
                                        ? "\n\n是否前往模拟页面查看？" : ""));
                        done.setNegativeButton(askOpenPage ? "稍后" : "知道了", null);
                        if (askOpenPage) {
                            done.setPositiveButton("前往",
                                    (d, w) -> openPage(fragment.requireContext(), deviceId, model));
                        }
                        done.show();
                    } else {
                        new WGProAlertDialogBuilder(fragment.requireContext())
                                .setTitle("安装失败")
                                .setMessage(message)
                                .setPositiveButton("确定", null)
                                .show();
                    }
                })), "vela-install").start();
    }

    /** 打开该模拟设备的画面页（设备卡片页与模拟页共用同一入口）。 */
    public static void openPage(@NonNull Context context, @NonNull String deviceId,
                                @NonNull String model) {
        Intent intent = new Intent(context,
                com.typheye.wgpro.ui.function.device.VirtualDeviceActivity.class);
        intent.putExtra(com.typheye.wgpro.ui.function.device.VirtualDeviceFragment.ARG_AVD, model);
        intent.putExtra(com.typheye.wgpro.ui.function.device.VirtualDeviceFragment.ARG_DEVICE_ID,
                deviceId);
        context.startActivity(intent);
    }

    /** 目标设备没在运行时，先问一句；同时只允许一台实例，所以也要问是否结束别的那台。 */
    public static boolean ensureRunningOrConfirm(@NonNull Fragment fragment,
                                                 @NonNull Context app,
                                                 @NonNull String deviceId,
                                                 @NonNull String deviceName,
                                                 @NonNull Runnable proceed) {
        VelaEmulatorManager manager = VelaEmulatorManager.get();
        manager.init(app);
        if (manager.isRunning(deviceId)) {
            return true;
        }
        if (manager.hasRunningInstance()) {
            new WGProAlertDialogBuilder(fragment.requireContext())
                    .setTitle("先结束其他模拟设备？")
                    .setMessage("同时只能运行一台模拟设备。是否结束正在运行的那台，"
                            + "改为启动「" + deviceName + "」再安装？")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("结束并安装", (d, w) -> proceed.run())
                    .show();
        } else {
            new WGProAlertDialogBuilder(fragment.requireContext())
                    .setTitle("模拟器未启动")
                    .setMessage("安装到「" + deviceName + "」需要先启动模拟器，是否现在启动？")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("启动并安装", (d, w) -> proceed.run())
                    .show();
        }
        return false;
    }
}
