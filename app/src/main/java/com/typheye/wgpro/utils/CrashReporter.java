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
 * 崩溃上报：把未捕获异常发到服务端 {@code api.php?type=crash_report}。
 *
 * <p>只在崩溃/主动上报时调用，不参与常规网络链路；异常一律吞掉，
 * 上报失败绝不能影响"保存本地日志 + 重启到崩溃页"这条主流程。</p>
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

    /**
     * 阻塞上报（崩溃时用）：最多等 {@code timeoutMs} 毫秒，超时就放弃，绝不拖住崩溃流程。
     *
     * @return 服务端返回的 JSON 文本（失败为 null）
     */
    @Nullable
    public static String reportBlocking(@NonNull Context context, @Nullable Thread thread,
                                        @NonNull Throwable throwable, long timeoutMs) {
        final String[] result = new String[1];
        Thread worker = new Thread(() -> result[0] = post(context, thread, throwable), "crash-report");
        worker.setDaemon(true);
        worker.start();
        try {
            worker.join(Math.max(300L, timeoutMs));
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
        return result[0];
    }

    /** 异步上报（非崩溃场景，例如调试页主动测试）。 */
    public static void reportAsync(@NonNull Context context, @Nullable Thread thread,
                                   @NonNull Throwable throwable) {
        Thread worker = new Thread(() -> post(context, thread, throwable), "crash-report");
        worker.setDaemon(true);
        worker.start();
    }

    @Nullable
    private static String post(@NonNull Context context, @Nullable Thread thread,
                               @NonNull Throwable throwable) {
        HttpURLConnection conn = null;
        try {
            StringBuilder body = new StringBuilder();
            append(body, "message", String.valueOf(throwable.getMessage()));
            append(body, "stack", stackOf(throwable));
            append(body, "thread", thread == null ? "unknown" : thread.getName());
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
            byte[] payload = body.toString().getBytes("UTF-8");

            conn = (HttpURLConnection) new URL(ENDPOINT).openConnection();
            conn.setConnectTimeout(2500);
            conn.setReadTimeout(2500);
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
