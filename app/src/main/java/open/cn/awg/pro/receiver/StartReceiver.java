/**
 ******************************************************************************
 * @file    StartReceiver.java
 * @author  Typheye
 * @brief   Startup and wallpaper-change broadcast receiver.
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
package open.cn.awg.pro.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Process;

import java.io.File;

import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.ui.main.MainActivity;

public class StartReceiver extends BroadcastReceiver {
    static final String ACTION = "android.intent.action.BOOT_COMPLETED";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (context == null || intent == null) {
            return;
        }
        String action = intent.getAction();
        if (ACTION.equals(action)) {
            File file = new File(AppPaths.appPath("settings/a4.inf"));
            int i = Process.myUid() / 100000;
            // Re-open the app after boot only when the persisted startup flag is enabled.
            if (file.exists() && i == 0) {

                Intent mainActivityIntent = new Intent(context, MainActivity.class);  // 要启动的Activity
                mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(mainActivityIntent);

            }
        }
        if ("WALLPAPER_CHANGED".equals(action)) {
            System.out.println("=============");
        }
    }

}
