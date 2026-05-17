package open.cn.awg.pro;

import java.lang.*;
import open.cn.awg.pro.R;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.content.Context;
import android.widget.Toast;
import i.runlibrary.app.AppInfo;
import android.app.Activity;
import open.cn.awg.pro.e6;
import android.app.Notification;
import android.graphics.BitmapFactory;
import androidx.core.app.NotificationCompat;
import java.io.File;
import android.os.Build;
import open.cn.awg.pro.e13;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.widget.Toast;
import android.os.Looper;

public class AwgCoreService extends Service {
	
	private AppInfo myu_;
	private Context context;
	private String runPath="/data/user/0/open.cn.awg.pro/data/AwgCoreService.r";
	
	private NotificationManager notificationManager;
	private String notificationId = "10001";
	private String notificationName = "腕管Pro";
	
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
		context = open.cn.awg.pro.e6.getContext();
	}
	
	class ServiceThread implements Runnable
	{
		//用volatile修饰保证变量在线程间的可见性
		volatile boolean flag=true;
		@Override
		public void run() {
			File mfile=new File(runPath);
			while (flag)
			{
				try{
					if(mfile.exists()==false){
						myu(myu_,context);
					}
					//间隔
					Thread.sleep(5000);
				} catch (InterruptedException exception) {
				}
			}
		}
	}
	
	public void myu(AppInfo myu,Context too) {
		e13 aa=new e13(myu);
		myu_=myu;
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
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true){
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
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
}