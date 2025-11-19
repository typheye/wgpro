package open.cn.awg.pro;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.io.File;

public class StartReceiver extends BroadcastReceiver {
  static final String ACTION = "android.intent.action.BOOT_COMPLETED";
  @Override
  public void onReceive(Context context, Intent intent) {
    if (intent.getAction().equals(ACTION)) {
      File file = new File("/data/user/0/open.cn.awg.pro/settings/a4.inf");
      int i=android.os.Process.myUid()/100000;
      if(file.exists() && i==0){
        
        Intent mainActivityIntent = new Intent(context, main.class);  // 要启动的Activity
        mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(mainActivityIntent);
        
      }
    }
    if(intent.getAction().equals("WALLPAPER_CHANGED")){
      System.out.println("=============");
    }
  }
  
}