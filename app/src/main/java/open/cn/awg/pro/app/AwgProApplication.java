/**
 ******************************************************************************
 * @file    AwgProApplication.java
 * @author  Typheye
 * @brief   Application initialization and process-level runtime setup.
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
package open.cn.awg.pro.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;

import com.google.android.material.color.DynamicColors;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import java.util.LinkedList;
import java.util.List;

import open.cn.awg.pro.browser.X5WebViewCompat;
import open.cn.awg.pro.repair.exception.CrashHandler;
import xyz.doikki.videoplayer.ijk.IjkPlayerFactory;
import xyz.doikki.videoplayer.player.VideoViewConfig;
import xyz.doikki.videoplayer.player.VideoViewManager;

@SuppressLint("StaticFieldLeak")
public class AwgProApplication extends android.app.Application {
    private static Context mContext;
    private static AwgProApplication instance;
    private final List<Activity> activities = new LinkedList<Activity>();

    public AwgProApplication() {
        instance = this;
    }

    public static Context getContext() {
        if (mContext != null) {
            return mContext;
        }
        return instance == null ? null : instance.getApplicationContext();
    }

    public static AwgProApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        // Dynamic colors were applied by applicationMain before any activity was created.
        DynamicColors.applyToActivitiesIfAvailable(this);
        super.onCreate();
        instance = this;
        mContext = getApplicationContext();

        // Install the crash handler before SDK initialization so startup failures are captured.
        CrashHandler.getInstance().init(mContext);
        // Use IJK as the app-wide media backend for every VideoViewManager instance.
        VideoViewManager.setConfig(VideoViewConfig.newBuilder()
                .setPlayerFactory(IjkPlayerFactory.create())
                .build());
        // Configure X5 before a browser view is created.
        X5WebViewCompat.initializeSdk(this);
        // Set the analytics log switch; collection still follows the SDK's consent behavior.
        UMConfigure.setLogEnabled(true);
        UMConfigure.preInit(this, "6687f537940d5a4c4983309f", "awg_um");
    }

    public void addActivity(Activity activity) {
        if (activity != null && !activities.contains(activity)) {
            activities.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public void exit() {
        if (activities.isEmpty()) {
            return;
        }
        MobclickAgent.onKillProcess(this);
        for (Activity activity : activities) {
            try {
                activity.finishAndRemoveTask();
            } catch (Exception e) {
                activity.finish();
            }
        }
        System.exit(0);
    }
}