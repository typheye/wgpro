package gl;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import java.io.File;

public class xz extends Activity {
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }
  
  public static void download(String downloadUrl,Context ei,int i,File f) {
    DownloadManager manager = (DownloadManager) ei
    .getSystemService(ei.DOWNLOAD_SERVICE);
    DownloadManager.Request request = new DownloadManager.Request(
    Uri.parse(downloadUrl));
    //request.setDescription("正在下载");
    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
    request.allowScanningByMediaScanner();// 设置可以被扫描到
    request.setVisibleInDownloadsUi(false);// 设置下载可见
    //request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);//下载完成后通知栏任然可见
    if(i==1){
      request.setDestinationUri(Uri.fromFile(f));
    }else{
      String fileName;
      String downPath;
      fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
      downPath = Environment.DIRECTORY_DOWNLOADS;
      // 解析fileName
      request.setDestinationInExternalPublicDir(
      downPath, fileName);// 设置下载位置，sdcard/Download/fileName
      
    }
    long refernece = manager.enqueue(request);// 加入下载并取得下载ID
    SharedPreferences sPreferences = ei.getSharedPreferences(
    "downloadplato", 0);
    sPreferences.edit().putLong("plato", refernece).commit();
    //保存此次下载ID
  }
  
  
}