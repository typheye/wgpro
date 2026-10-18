package com.typheye.wgpro.utils;

import android.content.Context;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 崩溃/异常上报：发到服务端 {@code api.php?type=crash_report}。
 *
 * <p>两条路径：</p>
 * <ul>
 *   <li>{@link #reportBlocking} / {@link #reportAsync}：进程崩溃时的自动上报（限时，失败不影响重启）；</li>
 *   <li>{@link #reportRawBlocking}：崩溃页「上报」按钮的手动上报（配底部弹窗进度条）。</li>
 * </ul>
 */
public final class CrashReporter {
    private static final String ENDPOINT = "https://service.typheye.cn/api.php?type=crash_report";

    private CrashReporter() {
    }

    /** 组装完整堆栈文本。 */
    @NonNull
    public static String stackOf(@Nullable Throwable throwable) {
        if (throwable == null) return "";
        StringWriter writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        throwable.printStackTrace(printWriter);
        printWriter.close();
        return writer.toString();
    }

    @Nullable
    public static String reportBlocking(@NonNull Context context, @Nullable Thread thread,
                                        @NonNull Throwable throwable, long timeoutMs) {
        final String[] result = new String[1];
        Thread worker = new Thread(() -> result[0] = send(buildBody(context,
                String.valueOf(throwable.getMessage()), stackOf(throwable),
                thread == null ? "unknown" : thread.getName())), "crash-report");
        worker.setDaemon(true);
        worker.start();
        try {
            worker.join(Math.max(300L, timeoutMs));
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
        return result[0];
    }

    public static void reportAsync(@NonNull Context context, @Nullable Thread thread,
                                   @NonNull Throwable throwable) {
        Thread worker = new Thread(() -> send(buildBody(context,
                String.valueOf(throwable.getMessage()), stackOf(throwable),
                thread == null ? "unknown" : thread.getName())), "crash-report");
        worker.setDaemon(true);
        worker.start();
    }

    /**
     * 手动上报一段日志文本（崩溃页「上报」按钮）。
     *
     * @return true = 服务端已接收
     */
    public static boolean reportRawBlocking(@NonNull Context context, @NonNull String message,
                                            @NonNull String stack, long timeoutMs) {
        final boolean[] ok = new boolean[1];
        Thread worker = new Thread(() -> ok[0] = send(buildBody(context,
                message, stack, "manual")) != null, "crash-report");
        worker.setDaemon(true);
        worker.start();
        try {
            worker.join(Math.max(500L, timeoutMs));
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
        return ok[0];
    }

    @NonNull
    private static String buildBody(@NonNull Context context, @NonNull String message,
                                    @NonNull String stack, @NonNull String threadName) {
        StringBuilder body = new StringBuilder();
        append(body, "message", message);
        append(body, "stack", stack);
        append(body, "thread", threadName);
        append(body, "client", "android");
        append(body, "version_name", versionName(context));
        append(body, "version_code", String.valueOf(versionCode(context)));
        append(body, "device", (Build.MANUFACTURER + " " + Build.MODEL).trim());
        append(body, "android", Build.VERSION.RELEASE == null ? "" : Build.VERSION.RELEASE);
        String uid = "";
        try {
            uid = new tAccUtils(context.getApplicationContext()).getUid();
        } catch (Throwable ignored) {
        }
        append(body, "uid", uid == null ? "" : uid);
        return body.toString();
    }

    @Nullable
    private static String send(@NonNull String body) {
        HttpURLConnection conn = null;
        try {
            byte[] payload = body.getBytes("UTF-8");
            conn = (HttpURLConnection) new URL(ENDPOINT).openConnection();
            conn.setConnectTimeout(4000);
            conn.setReadTimeout(4000);
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            try (OutputStream out = conn.getOutputStream()) {
                out.write(payload);
            }
            int code = conn.getResponseCode();
            return code == 200 ? "ok" : ("http " + code);
        } catch (Throwable error) {
            return null;
        } finally {
            if (conn != null) conn.disconnect();
        }
    }

    private static void append(@NonNull StringBuilder body, @NonNull String key,
                               @NonNull String value) {
        if (body.length() > 0) body.append('&');
        try {
            body.append(URLEncoder.encode(key, "UTF-8")).append('=')
                    .append(URLEncoder.encode(value, "UTF-8"));
        } catch (Exception ignored) {
        }
    }

    @NonNull
    private static String versionName(@NonNull Context context) {
        try {
            return String.valueOf(context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0).versionName);
        } catch (Throwable ignored) {
            return "";
        }
    }

    private static long versionCode(@NonNull Context context) {
        try {
            android.content.pm.PackageInfo info = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
                    ? info.getLongVersionCode() : info.versionCode;
        } catch (Throwable ignored) {
            return 0L;
        }
    }
}
