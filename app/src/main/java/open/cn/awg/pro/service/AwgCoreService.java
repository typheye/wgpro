/**
 ******************************************************************************
 * @file    AwgCoreService.java
 * @author  Typheye
 * @brief   Background core service for AWG Pro runtime tasks.
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
package open.cn.awg.pro.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import i.runlibrary.app.AppInfo;

import java.io.File;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.CoreRuntimeBootstrap;
import open.cn.awg.pro.R;

public class AwgCoreService extends Service {

    private AppInfo myu_;
    private Context context;
    // Marker file used by legacy iApp scripts to decide whether the core runtime should be re-created.
    private final String runPath = AppPaths.appPath("data/AwgCoreService.r");

    private NotificationManager notificationManager;
    private final String notificationId = "10001";
    private final String notificationName = "腕管Pro";

    @Override
    public void onCreate() {
        super.onCreate();
		/*
		notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		//创建NotificationChannel
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
		NotificationChannel channel = new NotificationChannel(notificationId, notificationName, NotificationManager.IMPORTANCE_HIGH);
		notificationManager.createNotificationChannel(channel);
		}
		startForeground(1,getNotification());
		*/
        context = AwgProApplication.getContext();
    }

    public void myu(AppInfo myu, Context too) {
        CoreRuntimeBootstrap aa = new CoreRuntimeBootstrap(myu);
        myu_ = myu;
    }

    private Notification getNotification() {
        Notification.Builder builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.i)
                .setContentTitle("腕管Pro")
                .setContentText("正在后台运行...");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(notificationId);
        }
        Notification notification = builder.build();
        return notification;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // Keep this service process alive for runtime hooks that expect a long-lived background process.
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    class ServiceThread implements Runnable {
        //用volatile修饰保证变量在线程间的可见性
        volatile boolean flag = true;

        @Override
        public void run() {
            File mfile = new File(runPath);
            while (flag) {
                try {
                    // If the runtime marker disappears, rebuild the iApp entry object.
                    if (!mfile.exists()) {
                        myu(myu_, context);
                    }
                    //间隔
                    Thread.sleep(5000);
                } catch (InterruptedException exception) {
                }
            }
        }
    }
}
