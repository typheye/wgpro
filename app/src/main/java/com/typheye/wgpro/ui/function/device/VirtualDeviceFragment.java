package com.typheye.wgpro.ui.function.device;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.typheye.wgpro.R;
import com.typheye.wgpro.core.emu.VelaEmulator;
import com.typheye.wgpro.core.emu.VelaEmulatorManager;
import com.typheye.wgpro.core.emu.VelaFrames;
import com.typheye.wgpro.ui.widget.WGProHint;

import java.io.File;
import java.util.ArrayDeque;
import java.util.List;

/**
 * 模拟设备画面 + 常驻实时日志 + 触摸注入。
 *
 * <p>引擎由 {@link VelaEmulatorManager} 持有：进入本页是**附着**到已有实例（没跑才启动），
 * 离开本页**不停止**引擎，仅停止取帧与日志订阅。</p>
 */
public class VirtualDeviceFragment extends Fragment {

    /** 默认机型（已跑通）；实际以参数为准。 */
    public static final String AVD_ID = VelaEmulatorManager.MODEL_MIWEAR_WATCH;
    /** 实例键 = 设备 id（同一机型可有多台，各自独立运行/沙盒）。 */
    public static final String ARG_DEVICE_ID = "device_id";

    public static final String ARG_AVD = "avd_id";

    private static final int LOG_MAX = 300;
    /** 皮肤档位与 layout（来源：引擎自带 skins/xiaomi_s4/layout）。 */
    private static final String SKIN = "xiaomi_s4";
    private static final int SKIN_CANVAS_W = 601;
    private static final int SKIN_CANVAS_H = 973;
    private static final int SKIN_SCREEN_W = 466;
    private static final int SKIN_SCREEN_H = 466;
    private static final int SKIN_SCREEN_X = 65;
    private static final int SKIN_SCREEN_Y = 253;
    /** 屏幕圆角 / 屏幕边长（xiaomi_s4 = 233/466 = 圆形）。 */
    private static final float SKIN_RADIUS_RATIO = 233f / 466f;
    /** 引擎控制台端口 5560（见 VelaEmulator），adb 占其 +1。 */
    private static final int ADB_PORT = 5561;

    private VelaFrames frames;
    private com.typheye.wgpro.core.emu.VelaTouchSender touchSender;
    private VirtualScreenView frameView;
    /** 客机屏幕是否点亮（默认 true；引擎报 ON-->OFF 才置 false）。 */
    private volatile boolean screenOn = true;
    /** 客机是否已经真正出画面（引擎早期会推**纯黑帧**，不能拿"有帧了"当已启动）。 */
    private volatile boolean bootHintCleared;
    private TextView logView;
    private ScrollView logScroll;

    private final Handler ui = new Handler(Looper.getMainLooper());
    private final ArrayDeque<String> logLines = new ArrayDeque<>();
    private boolean logFlushScheduled;
    private volatile boolean polling;
    private volatile long lastFrameAt;
    private volatile android.graphics.Bitmap frameBitmap;
    private volatile java.util.concurrent.atomic.AtomicBoolean streamFlag;
    private Bitmap lastFrame;
    private String avdId = AVD_ID;

    /** 实例键：设备 id（同机型多台各自独立）；缺省回退机型标识，兼容老入口。 */
    private String deviceKey;
    private VelaEmulator.LineListener lineListener;

    @NonNull
    public static VirtualDeviceFragment newInstance(@Nullable String avdId) {
        return newInstance(avdId, null);
    }

    /** 带上**设备 id**（实例键 / 独立目录都用它；缺了就退回机型，兼容老入口）。 */
    @NonNull
    public static VirtualDeviceFragment newInstance(@Nullable String avdId,
                                                    @Nullable String deviceId) {
        VirtualDeviceFragment fragment = new VirtualDeviceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_AVD, avdId == null ? AVD_ID : avdId);
        if (deviceId != null && !deviceId.isEmpty()) {
            args.putString(ARG_DEVICE_ID, deviceId);
        }
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent,
                             @Nullable Bundle state) {
        return inflater.inflate(R.layout.fragment_virtual_device, parent, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle state) {
        super.onViewCreated(view, state);
        if (getArguments() != null) {
            deviceKey = getArguments() == null ? null : getArguments().getString(ARG_DEVICE_ID);
            String fromArgs = getArguments().getString(ARG_AVD);
            if (fromArgs != null && !fromArgs.isEmpty()) {
                avdId = fromArgs;
            }
        }
        frameView = view.findViewById(R.id.virtual_frame);
        setBootHint("正在启动模拟设备，请稍候…");
        logView = view.findViewById(R.id.virtual_log);
        logScroll = view.findViewById(R.id.virtual_log_scroll);
        bindKeys(view);
        setupSkin(view);
        applyInsets(view);
        frameView.setOnTouchListener(this::onFrameTouch);

        resolveKeys();
        if (deviceKey == null || deviceKey.isEmpty()) {
            deviceKey = avdId;   // 兜底：绝不把 null 当实例键
        }
        bindWatermark(view);
        final VelaEmulatorManager manager = VelaEmulatorManager.get();
        manager.init(requireContext());
        lineListener = line -> {
            // 引擎 stdout 里带客机电源日志：AIoT IDE 也是靠这两行判断息屏的
            if (line.contains("Screen state: ON-->OFF")) {
                onScreenState(false);
            } else if (line.contains("Screen state: OFF-->ON")) {
                onScreenState(true);
            } else if (line.contains("booting completed")) {
                // 客机自己宣布启动完成 → 这是"已经出画面"的权威信号
                onGuestBooted();
            }
            ui.post(() -> appendLog(line));
        };
        manager.addLineListener(deviceKey, lineListener);
        // 附着前先补齐历史日志（可能已经在跑）
        List<String> history = manager.log(deviceKey);
        for (String line : history) {
            appendLog(line);
        }
        // 已有别的实例在跑 → 先问清楚（底部弹窗：继续会结束旧实例）
        if (manager.isRunningDevice(deviceKey)) {
            attach(manager);
        } else if (manager.hasRunningInstance()) {
            confirmSwitchInstance(manager);
        } else {
            attach(manager);
        }
        // 用户设定：遮罩最多 5s（首帧若先到则提前关）
        armEntryOverlayTimeout();
    }

    /**
     * 进入本页时已有实例在运行：底部弹窗（不可取消）告知"继续会结束旧实例"。
     * 取消 → 退出本页；继续 → 停掉当前实例，再启动本设备实例。
     */
    private void confirmSwitchInstance(@NonNull VelaEmulatorManager manager) {
        setStatus("已有实例在运行，等待用户确认");
        new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(requireContext())
                .setTitle("已有模拟设备在运行")
                .setMessage("继续启动当前模拟设备会结束正在运行的实例。是否继续？")
                .setCancelable(false)
                .setNegativeButton("取消", (dialog, which) -> {
                    if (isAdded()) {
                        requireActivity().finish();
                    }
                })
                .setPositiveButton("继续", (dialog, which) -> {
                    if (isAdded()) {
                        attach(manager);
                    }
                })
                .show();
    }

    /**
     * 顶部水印：显示"进的是哪一台模拟设备"（单实例切换时不然分不清）。
     * 名字取设备库里的显示名（note > model 列），取不到才退回机型标识。
     */
    private void bindWatermark(@NonNull View root) {
        TextView watermark = root.findViewById(R.id.virtual_watermark);
        if (watermark == null) {
            return;
        }
        String name = VelaEmulatorManager.get()
                .displayName(requireContext(), deviceKey, avdId);
        if (name == null || name.trim().isEmpty()) {
            name = avdId;
        }
        watermark.setText(name);
    }

    /**
     * 手表皮肤：用负载里 {@code engine/skins/<skin>/} 的 background/foreground 与 layout，
     * 把客机画面按 layout 里的 device 矩形摆到位（与 AIoT IDE 同款外观）。
     *
     * <p>xiaomi_s4 的 layout：画布 601x973，屏幕 466x466，屏幕偏移 (65,253)，圆角 233。</p>
     */
    private void setupSkin(@NonNull View root) {
        final View deviceView = root.findViewById(R.id.virtual_device_view);
        final com.typheye.wgpro.ui.widget.DisplayMaskView mask =
                root.findViewById(R.id.virtual_display_mask);
        final ImageView overlay = root.findViewById(R.id.virtual_skin_overlay);
        final ImageView body = root.findViewById(R.id.virtual_skin_body);
        final File dir = new File(new VelaEmulator(requireContext(), avdId, deviceKey).engineDir(),
                "skins/" + SKIN);
        File bodyFile = new File(dir, "background.png");
        File overlayFile = new File(dir, "foreground.png");
        if (!bodyFile.isFile() || !overlayFile.isFile()) {
            // 没有皮肤资源：只显示画面（显示遮罩仍按形状生效）；
            // 进入遮罩不在这里关 —— 它由"首帧到达"决定（见 hideEntryOverlayWhenReady）。
            return;
        }
        body.setImageBitmap(android.graphics.BitmapFactory.decodeFile(bodyFile.getAbsolutePath()));
        overlay.setImageBitmap(android.graphics.BitmapFactory.decodeFile(overlayFile.getAbsolutePath()));
        body.setVisibility(View.VISIBLE);
        overlay.setVisibility(View.VISIBLE);
        deviceView.post(() -> {
            int viewW = deviceView.getWidth();
            int viewH = deviceView.getHeight();
            if (viewW <= 0 || viewH <= 0) {
                return;
            }
            // 画布按 fitCenter 铺进容器
            float scale = Math.min(viewW / (float) SKIN_CANVAS_W, viewH / (float) SKIN_CANVAS_H);
            int canvasW = Math.round(SKIN_CANVAS_W * scale);
            int canvasH = Math.round(SKIN_CANVAS_H * scale);
            int originX = (viewW - canvasW) / 2;
            int originY = (viewH - canvasH) / 2;
            // 屏幕矩形（画面 / 显示遮罩洞 / 叠加皮肤 三者完全一致）
            int frameW = Math.round(SKIN_SCREEN_W * scale);
            int frameH = Math.round(SKIN_SCREEN_H * scale);
            int left = originX + Math.round(SKIN_SCREEN_X * scale);
            int top = originY + Math.round(SKIN_SCREEN_Y * scale);
            layoutChild(frameView, left, top, frameW, frameH);
            layoutChild(overlay, left, top, frameW, frameH);
            layoutChild(body, originX, originY, canvasW, canvasH);
            if (mask != null) {
                mask.setHole(new android.graphics.RectF(left, top, left + frameW, top + frameH),
                        SKIN_RADIUS_RATIO);
            }
        });
    }

    /** 按左上角 + 宽高摆放子视图（容器是 FrameLayout）。 */
    private static void layoutChild(@Nullable View child, int left, int top, int width, int height) {
        if (child == null) {
            return;
        }
        android.widget.FrameLayout.LayoutParams params =
                (android.widget.FrameLayout.LayoutParams) child.getLayoutParams();
        params.width = width;
        params.height = height;
        params.leftMargin = left;
        params.topMargin = top;
        child.setLayoutParams(params);
    }

    private volatile long lastBackAt;

    private boolean entryOverlayHidden;
    /** 定时关闭任务（只允许一个在排队；首帧先到就把它取消）。 */
    private final Runnable entryOverlayDismiss = this::dismissEntryOverlay;

    /** 取流失败后的重连间隔（引擎冷启动时 gRPC 还没起来，必须重试）。 */
    private static final long FIRST_FRAME_RETRY_MS = 1000L;

    /**
     * 进入遮罩**最长展示时间**（用户设定：最多 3s）。
     *
     * <p>语义：**5s 是上限**。第一帧到了就立刻关（正常冷启动十几秒时，用户看到的是"覆盖着的加载态"
     * 而不是黑屏）；5s 到点也一定关，绝不允许一直卡在加载遮罩上（踩过：首帧没到就永远盖着）。</p>
     */
    private static final long ENTRY_OVERLAY_MAX_MS = 3000L;
    /** 遮罩最短展示时间：太短会闪一下。 */
    private static final long ENTRY_OVERLAY_MIN_MS = 300L;
    private final long enteredAt = System.currentTimeMillis();
    /** 收到的第一帧时间（0 = 还没收到）——首帧到了就提前关遮罩。 */
    private volatile long firstFrameAt;

    /** 启动"最多 5s"的定时关闭；首帧到达时调用 {@link #hideEntryOverlayWhenReady} 提前取消它。 */
    private void armEntryOverlayTimeout() {
        ui.removeCallbacks(entryOverlayDismiss);
        ui.postDelayed(entryOverlayDismiss, ENTRY_OVERLAY_MAX_MS);
    }

    /** 到点/首帧关闭遮罩（幂等，且保证至少展示 ENTRY_OVERLAY_MIN_MS）。 */
    private void dismissEntryOverlay() {
        if (entryOverlayHidden) {
            return;
        }
        entryOverlayHidden = true;
        ui.removeCallbacks(entryOverlayDismiss);
        long delay = Math.max(0L, ENTRY_OVERLAY_MIN_MS - (System.currentTimeMillis() - enteredAt));
        ui.postDelayed(this::fadeOutEntryOverlay, delay);
    }

    /**
     * 首帧到达时提前关闭遮罩（早于 5s 上限时生效）；未到达则等
     * {@link #ENTRY_OVERLAY_MAX_MS} 定时兜底。
     */
    private void hideEntryOverlayWhenReady() {
        if (firstFrameAt > 0L) {
            dismissEntryOverlay();
        }
    }

    private void fadeOutEntryOverlay() {
        // 淡出过渡（而不是硬切）
        View overlay = getView() == null ? null
                : getView().findViewById(R.id.virtual_loading_overlay);
        if (overlay == null) {
            return;
        }
        overlay.animate()
                .alpha(0f)
                .setDuration(220L)
                .withEndAction(() -> {
                    overlay.setVisibility(View.GONE);
                    overlay.setAlpha(1f);
                })
                .start();
    }

    /** 状态栏高度（页面隐藏了状态栏，insets 里取不到，按规范用系统 dimen）。 */
    /** insets：操作栏上边距 = 状态栏高度；日志避开导航条。 */
    private void applyInsets(@NonNull View root) {
        final View actions = root.findViewById(R.id.virtual_actions);
        final View log = root.findViewById(R.id.virtual_log_scroll);
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            if (actions != null) {
                android.view.ViewGroup.MarginLayoutParams params =
                        (android.view.ViewGroup.MarginLayoutParams) actions.getLayoutParams();
                int want = statusBarHeight() + Math.round(getResources().getDisplayMetrics().density * 6f);
                if (params.topMargin != want) {
                    params.topMargin = want;
                    actions.setLayoutParams(params);
                }
            }
            if (log != null) {
                log.setPadding(8, 8, 8, 8 + bars.bottom);
            }
            return insets;
        });
        // 显示容器顶部留白 = 操作栏下沿：必须等操作栏布局完成（insets 回调时高度还是 0）
        if (actions != null) {
            actions.addOnLayoutChangeListener((v, l, t, r, b, ol, ot, or, ob) -> applyDeviceTopMargin());
            actions.post(this::applyDeviceTopMargin);
        }
    }

    /** 按键条：走 gRPC sendKey（Evdev 键码）。 */
    private void bindKeys(@NonNull View root) {
        View power = root.findViewById(R.id.key_power);
        if (power != null) {
            power.setOnClickListener(v -> confirmStopEmulator());
        }
        View enter = root.findViewById(R.id.key_enter);
        if (enter != null) {
            enter.setOnClickListener(v -> showCommandSheet());
        }
        bindKey(root, R.id.key_home, 102);       // KEY_HOME
        bindKey(root, R.id.key_back, 158);       // KEY_BACK
        bindKey(root, R.id.key_enter, 28);       // KEY_ENTER
        bindKey(root, R.id.key_vol_up, 115);     // KEY_VOLUMEUP
        bindKey(root, R.id.key_vol_down, 114);   // KEY_VOLUMEDOWN
        View logToggle = root.findViewById(R.id.key_log);
        if (logToggle != null) {
            logToggle.setOnClickListener(v -> toggleLog());
        }
        // 返回键直接退出本页（引擎常驻由守护服务持有，退出页面不会停止模拟）
    }

    /** ⏻：结束模拟器运行（底部弹窗确认）。 */
    private void confirmStopEmulator() {
        new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(requireContext())
                .setTitle("结束模拟器运行")
                .setMessage("将停止当前模拟设备实例，已创建的资源保留。")
                .setNegativeButton("取消", null)
                .setPositiveButton("结束运行", (dialog, which) -> {
                    // 实例键是**设备 id**（不是机型标识）——以前这里传 avdId（机型），
                    // stopByKey 找不到实例就静默什么都不做，"结束运行"看起来没反应（踩过）。
                    com.typheye.wgpro.core.emu.VelaEmulatorManager.get()
                            .stopByKey(requireContext(), deviceKey);
                    com.typheye.wgpro.ui.widget.WGProHint.show(requireContext(), "已结束运行");
                    requireActivity().finish();
                })
                .show();
    }

    /** ⏎：输入单条指令（底部弹窗 + 编辑框），下发到客机。 */
    private void showCommandSheet() {
        View content = getLayoutInflater().inflate(R.layout.dialog_edittext, null, false);
        com.google.android.material.textfield.TextInputLayout layout =
                content.findViewById(R.id.textInputLayout);
        com.google.android.material.textfield.TextInputEditText input =
                content.findViewById(R.id.editText);
        layout.setHint("客机指令（单条）");
        input.setSingleLine(true);
        input.setText("uname -a");
        input.setSelection(input.getText() == null ? 0 : input.getText().length());
        com.typheye.wgpro.ui.widget.WGProBottomSheetDialog dialog =
                new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(requireContext())
                        .setTitle("输入指令")
                        .setView(content)
                        .setNegativeButton("取消", null)
                        .setPositiveButton("执行", null)
                        .create();
        dialog.setOnShowListener(ignored ->
                dialog.getButton(android.content.DialogInterface.BUTTON_POSITIVE)
                        .setOnClickListener(v -> {
                            String command = input.getText() == null ? ""
                                    : input.getText().toString().trim();
                            dialog.dismiss();
                            if (!command.isEmpty()) {
                                runGuestCommand(command);
                            }
                        }));
        dialog.show();
    }

    /** 把指令下发到客机，并把输出写进日志面板。 */
    private void runGuestCommand(@NonNull String command) {
        showLog(true);
        appendLog("$ " + command);
        new Thread(() -> {
            String output;
            try {
                output = com.typheye.wgpro.core.emu.VelaAdbClient.shell(
                        "127.0.0.1", ADB_PORT, command, 15000L);
            } catch (Exception error) {
                output = "指令通道不可用：" + error.getMessage();
            }
            final String text = output;
            ui.post(() -> {
                for (String line : text.split("\n")) {
                    appendLog(line);
                }
                flushLog();
            });
        }, "vela-cmd").start();
    }

    private void bindKey(@NonNull View root, int viewId, int evdevCode) {
        View button = root.findViewById(viewId);
        if (button == null) {
            return;
        }
        button.setOnClickListener(v -> {
            final VelaFrames current = frames;
            if (current != null) {
                new Thread(() -> current.key(evdevCode), "vela-key").start();
            }
        });
    }

    /** 日志开关（默认关闭；显示时贴底、占屏幕 1/3 高、浮在画面上）。 */
    private void toggleLog() {
        showLog(logScroll == null || logScroll.getVisibility() != View.VISIBLE);
    }

    /**
     * 客机屏幕开关变化（来自引擎 stdout 的 {@code Screen state: ON-->OFF / OFF-->ON}）。
     * 熄屏时在画面区自绘「屏幕已关闭，点击唤醒」，用户以为假死这个问题就解了。
     */
    private void onScreenState(boolean on) {
        ui.post(() -> {
            if (screenOn == on) {
                return;
            }
            screenOn = on;
            if (on) {
                if (bootHintCleared) {
                    clearHint();
                }
            } else {
                setScreenOffHint();
            }
        });
    }

    /** 客机宣布 booting completed → 结束"启动等待"提示。 */
    private void onGuestBooted() {
        ui.post(() -> {
            if (!isAdded()) {
                return;
            }
            bootHintCleared = true;
            if (screenOn) {
                clearHint();
            }
        });
    }

    /** 帧里是否有可见内容（引擎启动早期会推纯黑帧，必须区分）。 */
    private static boolean hasVisibleContent(@NonNull byte[] rgba) {
        int step = 4 * 257;   // 约每 257 个像素采一个，够判"是否全黑"
        for (int i = 0; i + 3 < rgba.length; i += step) {
            int sum = (rgba[i] & 0xFF) + (rgba[i + 1] & 0xFF) + (rgba[i + 2] & 0xFF);
            if (sum > 60) {
                return true;
            }
        }
        return false;
    }

    /** 点画面 = 唤醒（只要当前判断是熄屏；**不能**再拿"有没有帧"当条件 ——
     *  熄屏时帧仍在推，旧守卫会导致点击被挡掉、只有顶部按钮能唤醒）。 */
    private boolean tryWakeScreen() {
        if (screenOn) {
            return false;
        }
        VirtualScreenView waking = frameView;
        if (waking != null) {
            waking.setHint("正在唤醒…", true);
        }
        final VelaFrames current = frames;
        if (current != null) {
            new Thread(() -> {
                current.key(102);   // KEY_HOME（与 AIoT IDE 的「点击 home 键唤醒」一致）
                try {
                    Thread.sleep(400L);
                } catch (InterruptedException ignored) {
                }
                current.key(116);   // KEY_POWER 兜底（部分状态 home 唤不醒）
            }, "vela-wake").start();
        }
        return true;
    }

    /** 启动等待提示：小面板 + 文案（不遮挡画面内容）。 */
    private void setBootHint(@Nullable String text) {
        VirtualScreenView view = frameView;
        if (view != null) {
            view.setHint(text, false);
        }
    }

    /** 熄屏提示：**铺满整个显示区域的黑遮罩** + 文案，足够显眼。 */
    private void setScreenOffHint() {
        VirtualScreenView view = frameView;
        if (view != null) {
            view.setHint("屏幕已关闭，点击唤醒", true);
        }
    }

    private void clearHint() {
        VirtualScreenView view = frameView;
        if (view != null) {
            view.setHint(null, false);
        }
    }

    private int statusBarHeight() {
        int id = getResources().getIdentifier("status_bar_height", "dimen", "android");
        return id > 0 ? getResources().getDimensionPixelSize(id) : 0;
    }

    /** 让显示容器从操作栏下沿开始，避免表盘被操作栏压住。 */
    private void applyDeviceTopMargin() {
        View root = getView();
        if (root == null) {
            return;
        }
        View actions = root.findViewById(R.id.virtual_actions);
        View device = root.findViewById(R.id.virtual_device_view);
        if (actions == null || device == null || actions.getHeight() <= 0) {
            return;
        }
        android.view.ViewGroup.MarginLayoutParams params =
                (android.view.ViewGroup.MarginLayoutParams) device.getLayoutParams();
        // 顶边距 = 操作栏自身的上边距（状态栏高度）+ 操作栏高度 + 一点间距
        android.view.ViewGroup.MarginLayoutParams barParams =
                (android.view.ViewGroup.MarginLayoutParams) actions.getLayoutParams();
        int want = barParams.topMargin + actions.getHeight()
                + Math.round(getResources().getDisplayMetrics().density * 4f);
        if (params.topMargin != want) {
            params.topMargin = want;
            device.setLayoutParams(params);
        }
        // 水印跟显示容器同一个上沿：贴在操作栏正下方，居中显示当前设备名
        View watermark = root.findViewById(R.id.virtual_watermark);
        if (watermark != null) {
            android.view.ViewGroup.MarginLayoutParams watermarkParams =
                    (android.view.ViewGroup.MarginLayoutParams) watermark.getLayoutParams();
            if (watermarkParams.topMargin != want) {
                watermarkParams.topMargin = want;
                watermark.setLayoutParams(watermarkParams);
            }
        }
    }

    /**
     * 实例键与机型互相补全：
     * <ul>
     *   <li>有设备 id → 反查该行的机型（通知栏入口没带机型时靠这个）</li>
     *   <li>没设备 id（老入口/深链）→ 按机型在库里挑一台：**优先运行中的**，其次最近活跃的</li>
     * </ul>
     * 否则同机型多台会全部落到"先启动的那台"上（踩过）。
     */
    private void resolveKeys() {
        String model = null;
        String picked = null;
        try (com.typheye.wgpro.data.DeviceDatabase db =
                     new com.typheye.wgpro.data.DeviceDatabase(requireContext());
             android.database.Cursor cursor = db.all(com.typheye.wgpro.data.DeviceDatabase.TYPE_VIRTUAL)) {
            // 只有"能在库里找到的行 id"才算有效设备 id：
            // 传进来的若其实是机型（老入口会把机型塞进 deviceKey）→ 视为没有 id，按机型重新挑。
            boolean preferRunning = true;
            if (deviceKey != null && !deviceKey.isEmpty()) {
                while (cursor.moveToNext()) {
                    if (deviceKey.equals(cursor.getString(cursor.getColumnIndexOrThrow("id")))) {
                        preferRunning = false;
                        break;
                    }
                }
                cursor.moveToPosition(-1);
            }
            long bestTime = -1L;
            while (cursor.moveToNext()) {
                String rowId = cursor.getString(cursor.getColumnIndexOrThrow("id"));
                String rowModel = com.typheye.wgpro.core.emu.VelaProvision.modelIdOf(rowId);
                boolean connected = cursor.getInt(cursor.getColumnIndexOrThrow("connected")) != 0;
                if (!preferRunning && deviceKey.equals(rowId)) {
                    model = rowModel;
                    break;
                }
                if (preferRunning && avdId.equals(rowModel)) {
                    long seen = cursor.getLong(cursor.getColumnIndexOrThrow("last_seen"));
                    if (connected || seen >= bestTime) {
                        bestTime = seen;
                        picked = rowId;
                    }
                    if (connected) {
                        break;
                    }
                }
            }
        } catch (Exception error) {
            android.util.Log.w("VelaPage", "解析设备键失败", error);
        }
        if (deviceKey == null || deviceKey.isEmpty() || picked != null) {
            if (picked != null) {
                deviceKey = picked;
            }
        }
        if ((model == null || model.isEmpty())
                && deviceKey != null && !deviceKey.isEmpty()) {
            model = com.typheye.wgpro.core.emu.VelaProvision.modelIdOf(deviceKey);
        }
        if (model != null && !model.isEmpty()) {
            avdId = model;   // 机型标识：引擎目录/皮肤用它
        }
    }

    private void attach(@NonNull VelaEmulatorManager manager) {
        // 从确认弹窗进来时计时器可能已经跑了一半，重新给满 5s
        armEntryOverlayTimeout();
        if (manager.isRunningDevice(deviceKey)) {
            setStatus("已附着到运行中的实例");
            // 引擎已在跑 → 不再显示"启动等待"（若当前是熄屏则显示唤醒提示）
            bootHintCleared = true;
            if (screenOn) {
                clearHint();
            } else {
                setScreenOffHint();
            }
            startPolling(manager.port(deviceKey));
            return;
        }
        setStatus("启动中…（资源已就位时很快）");
        new Thread(() -> {
            // 启动失败**必须可见**：以前异常被静默吞掉 → "页面进去了但引擎没起来、没有日志"（踩过）
            VelaEmulatorManager.Instance started = null;
            try {
                started = manager.start(requireContext(), avdId, deviceKey);
            } catch (Throwable error) {
                android.util.Log.e("VelaPage", "启动模拟设备失败 key=" + deviceKey
                        + " model=" + avdId, error);
                ui.post(() -> {
                    if (!isAdded()) {
                        return;
                    }
                    // 启动失败也要撤掉遮罩，否则错误弹窗被盖住、页面像卡死
                    entryOverlayHidden = true;
                    fadeOutEntryOverlay();
                    WGProHint.showError(requireContext(),
                            "启动失败：" + (error.getMessage() == null
                                    ? error.getClass().getSimpleName() : error.getMessage()));
                });
                return;
            }
            final VelaEmulatorManager.Instance instance = started;
            ui.post(() -> {
                setStatus("运行中 · gRPC 127.0.0.1:" + instance.port);
                startPolling(instance.port);
                // 遮罩不在这里关：等**首帧**（引擎冷启动会黑屏十几秒，关早了就像卡死）
            });
        }, "virtual-attach").start();
    }

    private void startPolling(int port) {
        if (polling) {
            return;
        }
        polling = true;
        final java.util.concurrent.atomic.AtomicBoolean streaming =
                new java.util.concurrent.atomic.AtomicBoolean(true);
        streamFlag = streaming;
        final java.nio.ByteBuffer pixelBuffer = java.nio.ByteBuffer.allocate(1024 * 1024);
        new Thread(() -> {
            // ★ 取流**必须能重试**：新建设备/冷启动时引擎还没监听 gRPC，
            //   第一次 streamScreenshot 会直接失败；以前失败就永久放弃 →
            //   "第一次进必然黑屏、退出重进才渲染"（踩过）。
            while (streaming.get() && isAdded()) {
                final VelaFrames current = new VelaFrames(port);
                frames = current;
                touchSender = new com.typheye.wgpro.core.emu.VelaTouchSender(current);
                current.streamFrames((width, height, rgba) -> {
            long now = System.currentTimeMillis();
            // 限帧：约 30fps。超出的帧直接丢弃，且**在拷贝像素之前**就返回（省掉一次 870KB 拷贝）
            if (now - lastFrameAt < 16L) {
                return;
            }
            lastFrameAt = now;
            android.graphics.Bitmap target = frameBitmap;
            if (target == null || target.getWidth() != width || target.getHeight() != height) {
                target = android.graphics.Bitmap.createBitmap(width, height,
                        android.graphics.Bitmap.Config.ARGB_8888);
                frameBitmap = target;
            }
            pixelBuffer.clear();
            pixelBuffer.put(rgba, 0, width * height * 4);
            pixelBuffer.rewind();
            target.copyPixelsFromBuffer(pixelBuffer);
            lastFrame = target;   // 触摸坐标映射要用当前帧的尺寸
            // 第一帧到达 = 引擎真正开始出画面 → 这时才允许撤掉进入遮罩
            // （以前 5 秒就撤，冷启动十几秒期间用户只看到黑屏，以为卡死 —— 踩过）
            if (firstFrameAt <= 0L) {
                firstFrameAt = now;
            }
            // 只有画面**真的不是纯黑**才算"出画面"：引擎先推黑帧，不能只看有没有帧，
            // 否则启动等待提示被提前清掉、用户又看到黑屏（踩过）。
            if (!bootHintCleared && screenOn && hasVisibleContent(rgba)) {
                bootHintCleared = true;
                ui.post(() -> {
                    if (isAdded() && screenOn) {
                        clearHint();
                    }
                });
            }
            final android.graphics.Bitmap shown = target;
            ui.post(() -> {
                if (isAdded() && frameView != null) {
                    frameView.setImageBitmap(shown);
                }
                hideEntryOverlayWhenReady();
            });
                }, streaming);
                // 流结束（失败或服务端关闭）→ 回收这个通道，稍后重连
                current.close();
                if (streaming.get()) {
                    try {
                        Thread.sleep(FIRST_FRAME_RETRY_MS);
                    } catch (InterruptedException ignored) {
                        break;
                    }
                }
            }
        }, "vela-frames").start();
    }

    // ------------------------------------------------------------ 触摸（按官方 proto）

    private boolean onFrameTouch(View view, MotionEvent event) {
        // 熄屏/还没出帧时，点画面 = 唤醒，不当作客机点击注入
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN && tryWakeScreen()) {
            return true;
        }
        com.typheye.wgpro.core.emu.VelaTouchSender sender = touchSender;
        if (sender == null || lastFrame == null) {
            return false;
        }
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: {
                int index = event.getActionIndex();
                int[] point = mapPoint(view, event.getX(index), event.getY(index));
                if (point == null) {
                    return false;
                }
                sender.down(point[0], point[1], event.getPointerId(index));
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                for (int i = 0; i < event.getPointerCount(); i++) {
                    int[] point = mapPoint(view, event.getX(i), event.getY(i));
                    if (point != null) {
                        sender.move(point[0], point[1], event.getPointerId(i));
                    }
                }
                return true;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL: {
                int index = event.getActionIndex();
                int[] point = mapPoint(view, event.getX(index), event.getY(index));
                if (point != null) {
                    // 抬手必须把 pressure 归 0，否则客机里手指一直按着
                    sender.up(point[0], point[1], event.getPointerId(index));
                }
                return true;
            }
            default:
                return false;
        }
    }

    @Nullable
    private int[] mapPoint(@NonNull View view, float px, float py) {
        Bitmap frame = lastFrame;
        if (frame == null || view.getWidth() == 0 || view.getHeight() == 0) {
            return null;
        }
        float scale = Math.min((float) view.getWidth() / frame.getWidth(),
                (float) view.getHeight() / frame.getHeight());
        float dx = (view.getWidth() - frame.getWidth() * scale) / 2f;
        float dy = (view.getHeight() - frame.getHeight() * scale) / 2f;
        int x = (int) ((px - dx) / scale);
        int y = (int) ((py - dy) / scale);
        if (x < 0 || y < 0 || x >= frame.getWidth() || y >= frame.getHeight()) {
            android.util.Log.d("VelaTouch", "mapPoint 拒绝 px=" + px + " py=" + py
                    + " frame=" + frame.getWidth() + "x" + frame.getHeight()
                    + " view=" + view.getWidth() + "x" + view.getHeight()
                    + " -> " + x + "," + y);
            return null;
        }
        return new int[]{x, y};
    }

    private void setStatus(@NonNull String text) {
        appendLog("[ui] " + text);
    }

    /** 日志面板是否可见（不可见时不进缓冲、不刷 UI，省掉引擎日志带来的无谓开销）。 */
    private boolean logVisible() {
        return logScroll != null && logScroll.getVisibility() == View.VISIBLE;
    }

    private void appendLog(@NonNull String line) {
        if (!logVisible()) {
            return;
        }
        while (logLines.size() >= LOG_MAX) {
            logLines.removeFirst();
        }
        logLines.addLast(line);
        // 限流但**必须补刷**：原来 200ms 内的更新被直接丢掉且不再触发，导致尾部日志永远不出现。
        if (logFlushScheduled) {
            return;
        }
        logFlushScheduled = true;
        ui.postDelayed(this::flushLog, 200L);
    }

    private void flushLog() {
        logFlushScheduled = false;
        if (logView == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String item : logLines) {
            sb.append(item).append('\n');
        }
        logView.setText(sb.toString());
        if (logScroll != null) {
            logScroll.post(() -> logScroll.fullScroll(View.FOCUS_DOWN));
        }
    }

    private void showLog(boolean visible) {
        if (logScroll == null) {
            return;
        }
        if (visible) {
            android.view.ViewGroup.LayoutParams params = logScroll.getLayoutParams();
            params.height = Math.round(getResources().getDisplayMetrics().heightPixels / 3f);
            logScroll.setLayoutParams(params);
            logScroll.setVisibility(View.VISIBLE);
            flushLog();
            logScroll.post(() -> logScroll.fullScroll(View.FOCUS_DOWN));
        } else {
            logScroll.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        polling = false;
        if (streamFlag != null) {
            streamFlag.set(false);
            streamFlag = null;
        }
        frameBitmap = null;
        if (touchSender != null) {
            touchSender.close();
            touchSender = null;
        }
        VelaFrames current = frames;
        frames = null;
        if (current != null) {
            current.close();
        }
        if (lineListener != null) {
            VelaEmulatorManager.get().removeLineListener(deviceKey, lineListener);
        }
        // 注意：这里**不停止引擎**（离开页面后继续运行）
        super.onDestroyView();
    }

    /**
     * 屏幕像素区自绘提示（不盖应用遮罩，保持"统一体验"）：
     * 启动等待、熄屏唤醒都直接画在画面区域里，覆盖在表格画面之上。
     */
    public static class VirtualScreenView extends androidx.appcompat.widget.AppCompatImageView {

        @Nullable
        private String hintText;
        /** true = 铺满整个显示区域的黑遮罩（熄屏用），false = 小面板（启动等待用）。 */
        private boolean fullMask;
        private final Paint hintPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private final Paint panelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        private RectF hintRect;

        public VirtualScreenView(android.content.Context context) {
            this(context, null);
        }

        public VirtualScreenView(android.content.Context context,
                                 @Nullable android.util.AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public VirtualScreenView(android.content.Context context,
                                 @Nullable android.util.AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            setScaleType(android.widget.ImageView.ScaleType.FIT_XY);
            float density = getResources().getDisplayMetrics().density;
            hintPaint.setColor(Color.WHITE);
            hintPaint.setTextAlign(Paint.Align.CENTER);
            hintPaint.setTextSize(15f * density);
            hintPaint.setFakeBoldText(true);
            hintPaint.setShadowLayer(4f * density, 0f, 1f * density,
                    androidx.core.graphics.ColorUtils.setAlphaComponent(Color.BLACK, 200));
            panelPaint.setColor(Color.argb(153, 0, 0, 0));
            hintRect = new RectF();
        }

        /** null / 空 = 不画提示；fullMask = 是否铺满显示区域。 */
        public void setHint(@Nullable String text, boolean fullMask) {
            String value = (text == null || text.trim().isEmpty()) ? null : text;
            if ((value == null ? hintText == null : value.equals(hintText))
                    && fullMask == this.fullMask) {
                return;
            }
            hintText = value;
            this.fullMask = fullMask;
            invalidate();
        }

        @Nullable
        public String hintText() {
            return hintText;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            String text = hintText;
            if (text == null || getWidth() == 0) {
                return;
            }
            float density = getResources().getDisplayMetrics().density;
            float cx = getWidth() / 2f;
            float cy = getHeight() / 2f;
            if (fullMask) {
                // 熄屏：铺满整个显示区域的黑遮罩（比小面板显眼得多）
                canvas.drawColor(Color.BLACK);
            }
            float paddingX = 14f * density;
            float paddingY = 10f * density;
            Paint.FontMetrics metrics = hintPaint.getFontMetrics();
            float textWidth = hintPaint.measureText(text);
            float textHeight = metrics.descent - metrics.ascent;
            float corner = 14f * density;
            hintRect.set(cx - textWidth / 2f - paddingX, cy - textHeight / 2f - paddingY,
                    cx + textWidth / 2f + paddingX, cy + textHeight / 2f + paddingY);
            canvas.drawRoundRect(hintRect, corner, corner, panelPaint);
            canvas.drawText(text, cx, cy - (metrics.ascent + metrics.descent) / 2f, hintPaint);
        }
    }
}
