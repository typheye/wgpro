package com.typheye.wgpro.core.state;

import java.util.ArrayList;

/**
 * 进程级环形日志。
 *
 * 替代原来的 {@code MainActivity.logs}（普通 ArrayList，被 XMS 回调线程与 UI 线程同时写入）。
 * 仍然是一个 List，因此 {@code String.join("\n", logs)} 与 Gson 序列化保持可用，
 * 内置浏览器 JS 桥（{@code JSKit.GetLogs}）的输出格式不变。
 */
public final class ServiceLog extends ArrayList<String> {
    private final int maxEntries;

    public ServiceLog(int maxEntries) {
        this.maxEntries = Math.max(16, maxEntries);
    }

    @Override
    public synchronized boolean add(String value) {
        if (size() >= maxEntries) {
            removeRange(0, size() - maxEntries + 1);
        }
        return super.add(value);
    }

    public synchronized void addAll(String... values) {
        for (String value : values) {
            add(value);
        }
    }
}
