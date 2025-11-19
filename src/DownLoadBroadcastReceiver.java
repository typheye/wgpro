package gl;


import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.Toast;
import android.os.Bundle;
import android.graphics.Path;
import java.lang.Exception;

/**
* 下载完成广播监听：比如下载APP
*
* */
public class DownLoadBroadcastReceiver extends BroadcastReceiver {
  
  @SuppressLint("NewApi")
  public void onReceive(Context context, Intent intent) {
    long myDwonloadID = intent.getLongExtra(
    DownloadManager.EXTRA_DOWNLOAD_ID, -1);
    //  LogUtils.i("下载完成 ID = " + myDwonloadID);
    //Toast.makeText(context,"下载完成",Toast.LENGTH_SHORT).show();
    SharedPreferences sPreferences = context.getSharedPreferences(
    "downloadplato", 0);
    long refernece = sPreferences.getLong("plato", 0);
    if (refernece == myDwonloadID) {
      try{
        String serviceString = Context.DOWNLOAD_SERVICE;
        DownloadManager dManager = (DownloadManager) context
        .getSystemService(serviceString);
        Intent intents = new Intent(context, open.cn.awg.pro.a3.class);
        Bundle bundle=new Bundle();
        Uri downloadFileUri = dManager.getUriForDownloadedFile(myDwonloadID);
        String path = downloadFileUri.toString();
        bundle.putString("text","文件下载完毕.\n[Uri]"+path);
        bundle.putString("title","提示");
        intents.putExtras(bundle);
        context.startActivity(intents);
        /*
        //这个是下载完成后自动打开文件
        Intent install = new Intent(Intent.ACTION_VIEW);
        Uri downloadFileUri = dManager
        .getUriForDownloadedFile(myDwonloadID);
        install.setDataAndType(downloadFileUri,
        "application/vnd.android.package-archive");
        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(install);
        */
      } catch(Exception e) {
        
      }
    }
    
  }
  
}

// 记得注册广播