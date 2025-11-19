package open.cn.awg.pro;

import java.lang.*;
import i.app.*;
import open.cn.awg.pro.R;
import android.app.Application;
import android.content.Context;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import java.util.HashMap;
import android.content.pm.PackageManager;
import xyz.doikki.videoplayer.player.VideoViewManager;
import xyz.doikki.videoplayer.player.VideoViewConfig;
import xyz.doikki.videoplayer.ijk.IjkPlayerFactory;
import xyz.doikki.videoplayer.ijk.IjkPlayer;
import java.util.LinkedList;
import java.util.List;
import open.cn.awg.pro.repair.exception.CrashHandler;
import android.app.Activity;
import android.app.AlarmManager;
import android.content.Intent;
import android.app.PendingIntent;
import java.lang.Exception;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.analytics.MobclickAgent;

public class e6 extends i.app.applicationMain{
  private static Context mContext;
  private List<Activity> activitys = null;
  private static e6 instance;
  
  @Override
  public void onCreate() {
    super.onCreate();
    mContext = getApplicationContext();
    CrashHandler crashHandler = CrashHandler.getInstance();
    crashHandler.init(getApplicationContext());
    VideoViewManager.setConfig(VideoViewConfig.newBuilder()
    //使用使用IjkPlayer解码
    .setPlayerFactory(IjkPlayerFactory.create())
    //                //使用ExoPlayer解码
    //                .setPlayerFactory(ExoMediaPlayerFactory.create())
    //                //使用MediaPlayer解码
    //                .setPlayerFactory(AndroidMediaPlayerFactory.create())
    .build());
    //X5
    x5(getApplicationContext());
    //设置LOG开关，默认为false
    UMConfigure.setLogEnabled(true);
    //初始化
    UMConfigure.preInit(this,"6687f537940d5a4c4983309f","awg_um");
  }
  
  public static Context getContext() {
    return mContext;
  }
  
  public static e6 getInstance() {
    if (null == instance) {
      instance = new e6();
    }
    return instance;
  }
  
  public e6() {
    activitys = new LinkedList<Activity>();
  }
  // 添加Activity到容器中
  public void addActivity(Activity activity) {
    if (activitys != null && activitys.size() > 0) {
      if (!activitys.contains(activity)) {
        activitys.add(activity);
      }
    } else {
      activitys.add(activity);
    }
  }
  
  // 移除Activity到容器中
  public void removeActivity(Activity activity) {
    if (activitys != null && activitys.size() > 0) {
      activitys.remove(activity);
    }
  }
  
  // 遍历所有Activity并finish
  public void exit() {
    if (activitys != null && activitys.size() > 0) {
      MobclickAgent.onKillProcess(this);
      for (Activity activity : activitys) {
        try{
          activity.finishAndRemoveTask();
        } catch (Exception e) {
          activity.finish();
        }
      }
      System.exit(0);
    }
  }
  
  private static boolean isx5 = false;
  public static int x5(Context c)
  {
    int resr = c.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE");
    if(resr != PackageManager.PERMISSION_GRANTED)
    return 0;
    if(isx5)
    return 2;
    isx5 = true;
    
    // 在调用TBS初始化、创建WebView之前进行如下配置，以开启优化方案
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
    map.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
    QbSdk.initTbsSettings(map);
    //非WIFI模式也下载X5组件
    //QbSdk.setDownloadWithoutWifi(true);
    
    //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
    /*QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
    public void onViewInitFinished(boolean arg0) {
    //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
    x5.zt = arg0 ? 1 : 2;
    
    }
    public void onCoreInitFinished() {
    }
    };
    //x5内核初始化接口 initX5Enviroment
    QbSdk.initX5Environment(c,  cb);
    
    QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
    
    @Override
    public void onViewInitFinished(boolean arg0) {
    // TODO Auto-generated method stub
    //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
    }
    
    @Override
    public void onCoreInitFinished() {
    // TODO Auto-generated method stub
    }
    };
    //x5内核初始化接口
    QbSdk.initX5Environment(c,  cb);
    */
    return 1;
  }
}