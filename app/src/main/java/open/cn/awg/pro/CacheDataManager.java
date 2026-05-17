package open.cn.awg.pro;

import java.lang.*;
import java.io.File;
import android.os.Environment;
import android.content.Context;

public class CacheDataManager {
  public static String getTotalCacheSize(Context context) throws Exception {
    long cacheSize = getFolderSize(context.getCacheDir());
    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
      cacheSize += getFolderSize(context.getExternalCacheDir());
    }
    return getFormatSize(cacheSize);
  }
  
  public static long getFolderSize(File file) throws Exception {
    long size = 0;
    try {
      File[] fileList = file.listFiles();
      for (int i = 0; i < fileList.length; i++) {
        if (fileList[i].isDirectory()) {
          size = size + getFolderSize(fileList[i]);
        } else {
          size = size + fileList[i].length();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return size;
  }
  
  public static String getFormatSize(long size) {
    long kb = size / 1024;
    int m = (int) (kb / 1024);
    int kbs = (int) (kb % 1024);
    String kbs_ = String.valueOf(kbs);
    if(kbs_.length()>2) kbs = kbs/10;
    if(kbs_.length()<2){
      return m + "." + kbs + "0M";
    } else {
      return m + "." + kbs + "M";
    }
  }
  
  public static void clearAllCache(Context context) {
    deleteDir(context.getCacheDir());
    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
      deleteDir(context.getExternalCacheDir());
    }
  }
  
  private static boolean deleteDir(File dir) {
    if (dir != null && dir.isDirectory()) {
      String[] children = dir.list();
      for (int i = 0; i < children.length; i++) {
        boolean success = deleteDir(new File(dir, children[i]));
        if (!success) {
          return false;
        }
      }
    }
    return dir.delete();
  }
}