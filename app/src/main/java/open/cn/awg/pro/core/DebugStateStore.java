/**
 ******************************************************************************
 * @file    DebugStateStore.java
 * @author  Typheye
 * @brief   Persists lightweight debug and diagnostic state.
 ******************************************************************************
 * @attention
 *
 * Copyright (c) 2021-2026 Typheye. All rights reserved.
 *
 * This software is licensed under terms that can be found in the LICENSE file
 * in the root directory of this software component.
 * If no LICENSE file comes with this software, it is provided AS-IS.
 *
 ******************************************************************************
 */
package open.cn.awg.pro.core;

import android.content.Context;

public class DebugStateStore {
    public String de = AppPaths.appPath("data/debug/state/");
    public String dd = AppPaths.appPath("data/debug/showed.inf");

    private final Context context;

    public DebugStateStore(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        Context applicationContext = context.getApplicationContext();
        this.context = applicationContext == null ? context : applicationContext;
    }

    public boolean getDebugState(Object key) {
        String path = de + key;
        return NativeFiles.exists(context, path)
                && java.util.Objects.equals(NativeFiles.readText(context, path), "1");
    }

    public boolean setDebugState(Object key, boolean enabled) {
        return NativeFiles.writeText(context, de + key, enabled ? "1" : "0");
    }

    public boolean isShowed() {
        return NativeFiles.exists(context, dd)
                && java.util.Objects.equals(NativeFiles.readText(context, dd), "1");
    }

    public boolean showed() {
        return NativeFiles.writeText(context, dd, "1");
    }

    public boolean delShowed() {
        NativeFiles.delete(context, dd, false);
        return true;
    }
}