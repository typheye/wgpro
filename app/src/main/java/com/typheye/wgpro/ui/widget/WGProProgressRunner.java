package com.typheye.wgpro.ui.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.typheye.wgpro.R;

import org.json.JSONObject;

/**
 * 统一的底部弹窗进度流程：先展示进度，至少 300ms 后再执行任务，
 * 任务完成后关闭进度并回调成功或失败。
 */
public final class WGProProgressRunner {
    public static final long MIN_VISIBLE_MS = 300L;

    public interface Task {
        void run(@NonNull Completion completion);
    }

    public interface Completion {
        void success(@NonNull JSONObject json);
        void error(int code, @NonNull String message);
    }

    public interface Callback {
        void success(@NonNull JSONObject json);
        void error(int code, @NonNull String message);
    }

    private interface Host {
        @NonNull Context context();
        boolean alive();
    }

    private WGProProgressRunner() { }

    public static void run(@NonNull Fragment fragment, @NonNull String title,
                           @NonNull String message, @NonNull Task task,
                           @NonNull Callback callback) {
        run(new Host() {
            @NonNull @Override public Context context() { return fragment.requireContext(); }
            @Override public boolean alive() { return fragment.isAdded(); }
        }, title, message, task, callback);
    }

    public static void run(@NonNull AppCompatActivity activity, @NonNull String title,
                           @NonNull String message, @NonNull Task task,
                           @NonNull Callback callback) {
        run(new Host() {
            @NonNull @Override public Context context() { return activity; }
            @Override public boolean alive() {
                return !activity.isFinishing() && !activity.isDestroyed();
            }
        }, title, message, task, callback);
    }

    private static void run(@NonNull Host host, @NonNull String title,
                            @NonNull String message, @NonNull Task task,
                            @NonNull Callback callback) {
        if (!host.alive()) return;
        View progressView = View.inflate(host.context(), R.layout.progress_dialog, null);
        ((TextView) progressView.findViewById(android.R.id.message)).setText(message);
        WGProBottomSheetDialog progress = new WGProAlertDialogBuilder(host.context())
                .setTitle(title).setView(progressView).setCancelable(false).create();
        progress.show();

        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            if (!host.alive()) {
                progress.dismissForReplacement();
                return;
            }
            task.run(new Completion() {
                @Override public void success(@NonNull JSONObject json) {
                    handler.post(() -> {
                        if (!host.alive()) return;
                        progress.dismissForReplacement();
                        callback.success(json);
                    });
                }

                @Override public void error(int code, @NonNull String message) {
                    handler.post(() -> {
                        if (!host.alive()) return;
                        progress.dismissForReplacement();
                        callback.error(code, message);
                    });
                }
            });
        }, MIN_VISIBLE_MS);
    }
}
