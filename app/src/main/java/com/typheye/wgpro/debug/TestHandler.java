package com.typheye.wgpro.debug;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.NonNull;

import com.typheye.wgpro.utils.tAccUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public final class TestHandler {
    private static final String PREFS = "development_mode";
    private static final String KEY_ENABLED = "enabled";
    private static final int MAX_EVENTS = 200;
    private static final ArrayDeque<String> EVENTS = new ArrayDeque<>();
    private static volatile boolean simulateOffline;
    private static volatile boolean simulateLoggedOut;
    private static volatile Context applicationContext;

    private TestHandler() { }

    public static void install(@NonNull Application application) {
        applicationContext = application.getApplicationContext();
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override public void onActivityCreated(@NonNull Activity activity, Bundle state) {
                recordOperation(activity, "打开 " + activity.getClass().getSimpleName());
            }
            @Override public void onActivityResumed(@NonNull Activity activity) {
                recordOperation(activity, "进入前台 " + activity.getClass().getSimpleName());
            }
            @Override public void onActivityPaused(@NonNull Activity activity) {
                recordOperation(activity, "离开前台 " + activity.getClass().getSimpleName());
            }
            @Override public void onActivityStarted(@NonNull Activity activity) { }
            @Override public void onActivityStopped(@NonNull Activity activity) { }
            @Override public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle state) { }
            @Override public void onActivityDestroyed(@NonNull Activity activity) { }
        });
    }

    public static void recordOperation(@NonNull Context context, @NonNull String message) {
        record(context, "STEP", message);
    }

    public interface ProbeCallback {
        void onComplete(@NonNull String summary);
    }

    public static boolean isEnabled(@NonNull Context context) {
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .getBoolean(KEY_ENABLED, false);
    }

    public static void setEnabled(@NonNull Context context, boolean enabled) {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit()
                .putBoolean(KEY_ENABLED, enabled).apply();
        if (!enabled) {
            simulateOffline = false;
            simulateLoggedOut = false;
            clear();
        } else record(context, "SYSTEM", "开发模式已启用");
    }

    @NonNull public static Interceptor networkLogger() {
        return chain -> {
            Request request = chain.request();
            Context activeContext = applicationContext;
            if (activeContext != null && isOfflineSimulationEnabled(activeContext)) {
                recordApi(activeContext, request.method(), request.url().host(), 0, "调试模式已阻止联网");
                throw new java.io.IOException("调试模式：网络已断开");
            }
            if (activeContext != null && isLoggedOutSimulationEnabled(activeContext)) {
                request = request.newBuilder().removeHeader("Authorization")
                        .removeHeader("Cookie").removeHeader("X-Typheye-Session-Id").build();
            }
            long startedAt = SystemClock.elapsedRealtime();
            try {
                Response response = chain.proceed(request);
                recordNetwork(request, response.code(), SystemClock.elapsedRealtime() - startedAt, null);
                return response;
            } catch (java.io.IOException error) {
                recordNetwork(request, 0, SystemClock.elapsedRealtime() - startedAt, error.getMessage());
                throw error;
            }
        };
    }

    private static void recordNetwork(Request request, int status, long elapsedMs, String error) {
        Context context = applicationContext;
        if (context == null || !isEnabled(context)) return;
        String action = request.url().queryParameter("type");
        String target = action == null || action.isEmpty()
                ? request.url().host() + request.url().encodedPath() : action;
        recordApi(context, request.method(), target, status,
                elapsedMs + "ms" + (error == null || error.isEmpty() ? "" : " | " + error));
    }

    public static void recordApi(@NonNull Context context, @NonNull String method,
                                 @NonNull String action, int status, String message) {
        if (!isEnabled(context)) return;
        String clean = message == null ? "" : message.replace('\n', ' ').replace('\r', ' ');
        if (clean.length() > 160) clean = clean.substring(0, 160);
        record(context, "API", method + " " + action + " -> " + status
                + (clean.isEmpty() ? "" : " | " + clean));
    }

    private static void record(Context context, String category, String message) {
        if (!isEnabled(context) && !"SYSTEM".equals(category)) return;
        String time = new SimpleDateFormat("HH:mm:ss.SSS", Locale.US).format(new Date());
        synchronized (EVENTS) {
            EVENTS.addFirst(time + "  " + category + "  " + message);
            while (EVENTS.size() > MAX_EVENTS) EVENTS.removeLast();
        }
    }

    @NonNull public static String logs() {
        synchronized (EVENTS) {
            if (EVENTS.isEmpty()) return "暂无调试日志";
            StringBuilder output = new StringBuilder();
            for (String event : EVENTS) output.append(event).append('\n');
            return output.toString().trim();
        }
    }

    public static void clear() {
        synchronized (EVENTS) { EVENTS.clear(); }
    }

    public static void armOffline(@NonNull Context context) {
        if (!isEnabled(context)) return;
        simulateOffline = true;
        record(context, "SIM", "下一次网络请求模拟断网");
    }

    public static void armLoggedOut(@NonNull Context context) {
        if (!isEnabled(context)) return;
        simulateLoggedOut = true;
        record(context, "SIM", "下一次需要登录的请求模拟未登录");
    }

    public static synchronized boolean consumeOffline(@NonNull Context context) {
        return isEnabled(context) && simulateOffline;
    }

    public static synchronized boolean consumeLoggedOut(@NonNull Context context) {
        return isEnabled(context) && simulateLoggedOut;
    }

    public static boolean isOfflineSimulationEnabled(@NonNull Context context) {
        return isEnabled(context) && simulateOffline;
    }

    public static boolean isLoggedOutSimulationEnabled(@NonNull Context context) {
        return isEnabled(context) && simulateLoggedOut;
    }

    public static synchronized void setOfflineSimulation(@NonNull Context context, boolean enabled) {
        if (!isEnabled(context)) return;
        simulateOffline = enabled;
        record(context, "SIM", enabled ? "真实断网模拟已开启" : "真实断网模拟已关闭");
    }

    public static synchronized void setLoggedOutSimulation(@NonNull Context context, boolean enabled) {
        if (!isEnabled(context)) return;
        simulateLoggedOut = enabled;
        record(context, "SIM", enabled ? "未登录模拟已开启" : "未登录模拟已关闭");
    }

    @NonNull public static String memorySummary() {
        Runtime runtime = Runtime.getRuntime();
        long used = runtime.totalMemory() - runtime.freeMemory();
        return "Java heap  " + mb(used) + " MB / " + mb(runtime.maxMemory()) + " MB\n"
                + "Allocated  " + mb(runtime.totalMemory()) + " MB\n"
                + "Free heap  " + mb(runtime.freeMemory()) + " MB";
    }

    private static long mb(long bytes) { return bytes / 1024L / 1024L; }

    public static void probeApis(@NonNull Context context, @NonNull ProbeCallback callback) {
        if (!isEnabled(context)) {
            callback.onComplete("请先开启开发模式");
            return;
        }
        tAccUtils account = new tAccUtils(context.getApplicationContext());
        Map<String, Map<String, String>> probes = new LinkedHashMap<>();
        probes.put("home_banners2", new LinkedHashMap<>());
        Map<String, String> page = new LinkedHashMap<>();
        page.put("page", "1"); page.put("size", "1");
        probes.put("resources2", page);
        AtomicInteger remaining = new AtomicInteger(probes.size() + 1);
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Map<String, String>> probe : probes.entrySet()) {
            String action = probe.getKey();
            account.getV2JsonFresh(action, probe.getValue(), false, new tAccUtils.JsonCallback() {
                @Override public void onSuccess(@NonNull org.json.JSONObject json) {
                    appendProbe(result, action, "OK code=" + json.optInt("code"));
                    finishProbe(remaining, result, callback);
                }
                @Override public void onError(int statusCode, @NonNull String message) {
                    appendProbe(result, action, "FAIL code=" + statusCode + " " + message);
                    finishProbe(remaining, result, callback);
                }
            });
        }
        account.getPublicJsonUrl("https://res.typheye.cn/api.php?type=app&page=1&size=1",
                new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull org.json.JSONObject json) {
                        appendProbe(result, "app market", "OK code=" + json.optInt("code"));
                        finishProbe(remaining, result, callback);
                    }
                    @Override public void onError(int statusCode, @NonNull String message) {
                        appendProbe(result, "app market", "FAIL code=" + statusCode + " " + message);
                        finishProbe(remaining, result, callback);
                    }
                });
    }

    private static void appendProbe(StringBuilder result, String name, String value) {
        synchronized (result) { result.append(name).append("  ").append(value).append('\n'); }
    }

    private static void finishProbe(AtomicInteger remaining, StringBuilder result,
                                    ProbeCallback callback) {
        if (remaining.decrementAndGet() == 0) callback.onComplete(result.toString().trim());
    }
}
