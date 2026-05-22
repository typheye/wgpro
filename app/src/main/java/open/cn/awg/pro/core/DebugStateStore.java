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

import i.app.iClass;

public class DebugStateStore extends iClass {

    public final DebugStateStore lei = this, 类 = this;
    public String de = AppPaths.appPath("data/debug/state/");
    public String dd = AppPaths.appPath("data/debug/showed.inf");

    public DebugStateStore(i.runlibrary.app.AppInfo _APPINFO) {
        super(_APPINFO);

    }

    public boolean getDebugState(Object i) {

        String ph = de + i;

        if (wj.cz(ph)) {

            return zf.dy(wj.dqwb(ph), "1");

        }
        return false;

    }

    public boolean setDebugState(Object i, boolean i2) {

        String ph = de + i;

        if (i2) {

            return wj.xrwb(ph, "1");

        } else {

            return wj.xrwb(ph, "0");

        }

    }

    public boolean isShowed() {

        if (wj.cz(dd)) {

            return zf.dy(wj.dqwb(dd), "1");

        }
        return false;

    }

    public boolean showed() {

        return wj.xrwb(dd, "1");

    }

    public boolean delShowed() {
        wj.sc(dd);
        return true;

    }
}
