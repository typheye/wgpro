package com.typheye.wgpro.ui.function.device;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
 * 虚拟设备画面 + 常驻实时日志 + 触摸注入。
 *
 * <p>引擎由 {@link VelaEmulatorManager} 持有：进入本页是**附着**到已有实例（没跑才启动），
 * 离开本页**不停止**引擎，仅停止取帧与日志订阅。</p>
 */
public class VirtualDeviceFragment extends Fragment {

    /** 默认机型（已跑通）；实际以参数为准。 */
    public static final String AVD_ID = VelaEmulatorManager.MODEL_MIWEAR_WATCH;
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
    private ImageView frameView;
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
    private VelaEmulator.LineListener lineListener;

    @NonNull
    public static VirtualDeviceFragment newInstance(@Nullable String avdId) {
        VirtualDeviceFragment fragment = new VirtualDeviceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_AVD, avdId == null ? AVD_ID : avdId);
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
            String fromArgs = getArguments().getString(ARG_AVD);
            if (fromArgs != null && !fromArgs.isEmpty()) {
                avdId = fromArgs;
            }
        }
        frameView = view.findViewById(R.id.virtual_frame);
        logView = view.findViewById(R.id.virtual_log);
        logScroll = view.findViewById(R.id.virtual_log_scroll);
        bindKeys(view);
        setupSkin(view);
        applyInsets(view);
        frameView.setOnTouchListener(this::onFrameTouch);

        final VelaEmulatorManager manager = VelaEmulatorManager.get();
        manager.init(requireContext());
        lineListener = line -> ui.post(() -> appendLog(line));
        manager.addLineListener(avdId, lineListener);
        // 附着前先补齐历史日志（可能已经在跑）
        List<String> history = manager.log(avdId);
        for (String line : history) {
            appendLog(line);
        }
        attach(manager);
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
        final File dir = new File(new VelaEmulator(requireContext()).engineDir(), "skins/" + SKIN);
        File bodyFile = new File(dir, "background.png");
        File overlayFile = new File(dir, "foreground.png");
        if (!bodyFile.isFile() || !overlayFile.isFile()) {
            // 没有皮肤资源：只显示画面（显示遮罩仍按形状生效）
            hideEntryOverlay();
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
            // 皮肤渲染完成 → 关闭进入页面的全屏遮罩（之前这段时间是黑/空屏，会闪一下）
            hideEntryOverlay();
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
    /** 遮罩最长展示时间：超过就直接关掉，避免启动异常时一直盖着。 */
    private static final long ENTRY_OVERLAY_MAX_MS = 5000L;
    /** 遮罩最短展示时间：太短会闪一下。 */
    private static final long ENTRY_OVERLAY_MIN_MS = 300L;
    private final long enteredAt = System.currentTimeMillis();

    /** 关闭"进入页面"的全屏加载遮罩；**至少展示 1 秒**，且可重复调用。 */
    private void hideEntryOverlay() {
        if (entryOverlayHidden) {
            return;
        }
        entryOverlayHidden = true;
        // 下限 300ms：太短会闪一下（上限由调用方 postDelayed 5s 兜底）
        long delay = Math.max(0L, ENTRY_OVERLAY_MIN_MS - (System.currentTimeMillis() - enteredAt));
        ui.postDelayed(this::fadeOutEntryOverlay, delay);
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
        // 系统返回键二次确认（先用系统 Toast）
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new androidx.activity.OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        long now = System.currentTimeMillis();
                        if (now - lastBackAt < 2000L) {
                            setEnabled(false);
                            requireActivity().getOnBackPressedDispatcher().onBackPressed();
                            return;
                        }
                        lastBackAt = now;
                        android.widget.Toast.makeText(requireContext(), "再按一次退出模拟",
                                android.widget.Toast.LENGTH_SHORT).show();
                    }
                });
    }

    /** ⏻：结束模拟器运行（底部弹窗确认）。 */
    private void confirmStopEmulator() {
        new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(requireContext())
                .setTitle("结束模拟器运行")
                .setMessage("将停止当前虚拟设备实例，已创建的资源保留。")
                .setNegativeButton("取消", null)
                .setPositiveButton("结束运行", (dialog, which) -> {
                    com.typheye.wgpro.core.emu.VelaEmulatorManager.get()
                            .stop(requireContext(), avdId);
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
    }

    private void attach(@NonNull VelaEmulatorManager manager) {
        if (manager.isRunning(avdId)) {
            setStatus("已附着到运行中的实例");
            startPolling(manager.port(avdId));
            hideEntryOverlay();
            return;
        }
        setStatus("启动中…（资源已就位时很快）");
        new Thread(() -> {
            try {
                VelaEmulatorManager.Instance instance = manager.start(requireContext(), avdId);
                ui.post(() -> {
                                        setStatus("运行中 · gRPC 127.0.0.1:" + instance.port);
                    startPolling(instance.port);
                    // 启动完成 → 关掉进入遮罩（日志默认关闭，只跟操作栏按钮）
                    hideEntryOverlay();
                });
            } catch (Exception error) {
                ui.post(() -> {
                    setStatus("启动失败：" + error.getMessage());
                    WGProHint.showError(requireContext(), "启动失败：" + error.getMessage());
                });
            }
        }, "virtual-attach").start();
    }

    private void startPolling(int port) {
        if (polling) {
            return;
        }
        polling = true;
        frames = new VelaFrames(port);
        touchSender = new com.typheye.wgpro.core.emu.VelaTouchSender(frames);
        final java.util.concurrent.atomic.AtomicBoolean streaming =
                new java.util.concurrent.atomic.AtomicBoolean(true);
        streamFlag = streaming;
        final java.nio.ByteBuffer pixelBuffer = java.nio.ByteBuffer.allocate(1024 * 1024);
        new Thread(() -> frames.streamFrames((width, height, rgba) -> {
            long now = System.currentTimeMillis();
            // 限帧：约 30fps。超出的帧直接丢弃，且**在拷贝像素之前**就返回（省掉一次 870KB 拷贝）
            if (now - lastFrameAt < 33L) {
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
            final android.graphics.Bitmap shown = target;
            ui.post(() -> {
                if (isAdded() && frameView != null) {
                    frameView.setImageBitmap(shown);
                }
            });
        }, streaming), "vela-frames").start();
    }

    // ------------------------------------------------------------ 触摸（按官方 proto）

    private boolean onFrameTouch(View view, MotionEvent event) {
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

    private void appendLog(@NonNull String line) {
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
            VelaEmulatorManager.get().removeLineListener(avdId, lineListener);
        }
        // 注意：这里**不停止引擎**（离开页面后继续运行）
        super.onDestroyView();
    }
}
