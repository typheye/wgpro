/**
 ******************************************************************************
 * @file    BootJobService.java
 * @author  Typheye
 * @brief   Boot-time job service entry point.
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

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.File;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.ui.main.MainActivity;


public class BootJobService extends JobService {

    private static final String TAG = "BootJobService";
    private Context context;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        // 在这里执行你的启动逻辑
        Log.d(TAG, "onStartJob: Boot completed, starting job.");

        //        if (!Settings.canDrawOverlays(this)) {
        //            //若未授权则请求权限
        //            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        //            intent.setData(Uri.parse("package:" + getPackageName()));
        //            startActivityForResult(intent, 0);
        //        }
        File file = new File("/data/user/0/open.cn.awg.pro/settings/a4.inf");
        int i = android.os.Process.myUid() / 100000;
        // Only auto-open the main UI for the primary user when the user setting flag is present.
        if (file.exists() && i == 0) {
            // 例如，启动一个Activity或者执行其他任务

            Intent intent = new Intent(this, open.cn.awg.pro.ui.main.MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            context = open.cn.awg.pro.app.AwgProApplication.getContext();
			/*
			if(com.kingqi.zwcj.应用工具.服务是否在运行(context,"open.cn.awg.pro.service.AwgCoreService")==false){
			Intent startIntent=new Intent(context,open.cn.awg.pro.service.AwgCoreService.class);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			startForegroundService(startIntent);
			} else {
			startService(startIntent);
			}
			}
			*/

        }
        // 返回true表示任务完成，不需要再次调度
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        // 如果任务被系统停止（例如设备进入省电模式），在这里处理相关逻辑
        Log.d(TAG, "onStopJob: Job stopped by system.");
        return true;
    }
}
