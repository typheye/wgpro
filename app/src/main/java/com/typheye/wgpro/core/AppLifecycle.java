package com.typheye.wgpro.core;

import java.util.concurrent.atomic.AtomicBoolean;

/** 记录应用是否处于前台，用于判断"连接是不是在后台被系统掐断的"。 */
public final class AppLifecycle {
    private static final AtomicBoolean FOREGROUND = new AtomicBoolean(false);

    private AppLifecycle() {
    }

    public static void setForeground(boolean foreground) {
        FOREGROUND.set(foreground);
    }

    public static boolean isForeground() {
        return FOREGROUND.get();
    }
}
