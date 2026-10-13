package com.typheye.wgpro.ui.function.virtualdevice;

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

    private VelaFrames frames;
    private ImageView frameView;
    private TextView logView;
    private ScrollView logScroll;
    private View loading;

    private final Handler ui = new Handler(Looper.getMainLooper());
    private final ArrayDeque<String> logLines = new ArrayDeque<>();
    private volatile boolean polling;
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
        loading = view.findViewById(R.id.virtual_loading);
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

    /** 底部日志避开导航条（全屏页自己做 insets）。 */
    private void applyInsets(@NonNull View root) {
        final View log = root.findViewById(R.id.virtual_log_scroll);
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            log.setPadding(8, 8, 8, 8 + bars.bottom);
            return insets;
        });
    }

    private void attach(@NonNull VelaEmulatorManager manager) {
        if (manager.isRunning(avdId)) {
            setStatus("已附着到运行中的实例");
            startPolling(manager.port(avdId));
            return;
        }
        setStatus("启动中…（资源已就位时很快）");
        new Thread(() -> {
            try {
                VelaEmulatorManager.Instance instance = manager.start(requireContext(), avdId);
                ui.post(() -> {
                    loading.setVisibility(View.GONE);
                    setStatus("运行中 · gRPC 127.0.0.1:" + instance.port);
                    startPolling(instance.port);
                });
            } catch (Exception error) {
                ui.post(() -> {
                    loading.setVisibility(View.GONE);
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
        new Thread(() -> {
            while (polling) {
                VelaFrames current = frames;
                byte[] png = current == null ? null : current.screenshot();
                if (png != null && png.length > 1000) {
                    Bitmap bmp = BitmapFactory.decodeByteArray(png, 0, png.length);
                    if (bmp != null) {
                        lastFrame = bmp;
                        ui.post(() -> {
                            frameView.setImageBitmap(bmp);
                            loading.setVisibility(View.GONE);
                        });
                    }
                }
                try {
                    Thread.sleep(250);
                } catch (InterruptedException stop) {
                    return;
                }
            }
        }, "virtual-frames").start();
    }

    // ------------------------------------------------------------ 触摸（按官方 proto）

    private boolean onFrameTouch(View view, MotionEvent event) {
        VelaFrames current = frames;
        if (current == null || lastFrame == null) {
            return false;
        }
        int action = event.getActionMasked();
        int pointerIndex = event.getActionIndex();
        int id = event.getPointerId(pointerIndex);
        int[] mapped = mapPoint(view, event.getX(pointerIndex), event.getY(pointerIndex));
        if (mapped == null) {
            return false;
        }
        final int x = mapped[0];
        final int y = mapped[1];
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                new Thread(() -> current.touch(x, y, id, true), "virtual-touch").start();
                return true;
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < event.getPointerCount(); i++) {
                    int[] point = mapPoint(view, event.getX(i), event.getY(i));
                    if (point == null) {
                        continue;
                    }
                    final int mx = point[0];
                    final int my = point[1];
                    final int mid = event.getPointerId(i);
                    new Thread(() -> current.touch(mx, my, mid, true), "virtual-touch").start();
                }
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                // 抬手必须把 pressure 归 0，否则仿真器里手指一直按着
                new Thread(() -> current.touch(x, y, id, false), "virtual-touch").start();
                return true;
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
        StringBuilder sb = new StringBuilder();
        for (String item : logLines) {
            sb.append(item).append('\n');
        }
        logView.setText(sb.toString());
        logScroll.post(() -> logScroll.fullScroll(View.FOCUS_DOWN));
    }

    @Override
    public void onDestroyView() {
        polling = false;
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
