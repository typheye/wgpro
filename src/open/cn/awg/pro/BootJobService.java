package open.cn.awg.pro;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.util.Log;
import android.os.Build;
import android.content.Context;
import java.io.File;

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
		int i=android.os.Process.myUid()/100000;
		if(file.exists() && i==0){
			// 例如，启动一个Activity或者执行其他任务
			
			Intent intent = new Intent(this, open.cn.awg.pro.main.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			
			context = open.cn.awg.pro.e6.getContext();
			/*
			if(com.kingqi.zwcj.应用工具.服务是否在运行(context,"open.cn.awg.pro.AwgCoreService")==false){
			Intent startIntent=new Intent(context,open.cn.awg.pro.AwgCoreService.class);
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