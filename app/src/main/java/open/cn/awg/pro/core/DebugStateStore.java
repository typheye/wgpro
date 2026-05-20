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
    public java.lang.String de = "/data/user/0/open.cn.awg.pro/data/debug/state/";
    public java.lang.String dd = "/data/user/0/open.cn.awg.pro/data/debug/showed.inf";

    public DebugStateStore(i.runlibrary.app.AppInfo _APPINFO) {
        super(_APPINFO);

    }

    public boolean getDebugState(java.lang.Object i) {

        java.lang.String ph = de + i;

        if (wj.cz(ph)) {

            return zf.dy(wj.dqwb(ph), "1");

        }
        return false;

    }

    public boolean setDebugState(java.lang.Object i, boolean i2) {

        java.lang.String ph = de + i;

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
