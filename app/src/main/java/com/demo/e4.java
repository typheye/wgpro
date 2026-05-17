package com.demo;

import android.graphics.Color;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.QRCodeWriter;
import java.util.Hashtable;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import android.graphics.Bitmap;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.content.ContentResolver;
import android.provider.MediaStore;
import android.net.Uri;
import android.content.Context;
import android.content.Intent;
import android.app.Activity;
import android.app.ActivityManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Build;
import android.text.TextUtils;
import android.os.FileUriExposedException;
import androidx.core.content.FileProvider;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.webkit.MimeTypeMap;
import java.util.List;
import android.content.pm.ResolveInfo;
import java.lang.*;
import java.security.cert.Extension;
import java.text.DecimalFormat;
//import android.icu.text.DecimalFormat;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.io.InputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import android.graphics.BitmapFactory;
import android.database.Cursor;
import android.provider.MediaStore;
import java.io.FileOutputStream;
import android.provider.OpenableColumns;

public class e4 {
  
  public static String getFilePathForN(Uri uri, Context context) {
    Uri returnUri = uri;
    Cursor returnCursor = context.getContentResolver().query(returnUri, null, null, null, null);
    /*
    * Get the column indexes of the data in the Cursor,
    *     * move to the first row in the Cursor, get the data,
    *     * and display it.
    * */
    int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
    int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
    returnCursor.moveToFirst();
    String name = (returnCursor.getString(nameIndex));
    String size = (Long.toString(returnCursor.getLong(sizeIndex)));
    File file = new File(context.getCacheDir(), name);
    try {
      InputStream inputStream = context.getContentResolver().openInputStream(uri);
      FileOutputStream outputStream = new FileOutputStream(file);
      int read = 0;
      int maxBufferSize = 1 * 1024 * 1024;
      int bytesAvailable = inputStream.available();
      
      //int bufferSize = 1024;
      int bufferSize = Math.min(bytesAvailable, maxBufferSize);
      
      final byte[] buffers = new byte[bufferSize];
      while ((read = inputStream.read(buffers)) != -1) {
        outputStream.write(buffers, 0, read);
      }
      //Log.e("File Size", "Size " + file.length());
      inputStream.close();
      outputStream.close();
      //Log.e("File Path", "Path " + file.getPath());
      //Log.e("File Size", "Size " + file.length());
    } catch (Exception e) {
      //Log.e("Exception", e.getMessage());
    }
    return file.getPath();
  }
  
  
  public static String getRealPathFromUri(Context context, Uri uri) {
    String filePath = "";
    String scheme = uri.getScheme();
    if (scheme == null)
    filePath = uri.getPath();
    else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
      filePath = uri.getPath();
    } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
      String[] proj = { MediaStore.Images.Media.DATA };
      Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
      if (cursor != null) {
        if (cursor.moveToFirst()) {
          int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
          filePath = cursor.getString(columnIndex);
        }
        cursor.close();
      }
      if (TextUtils.isEmpty(filePath)) {
        filePath = getFilePathForNonMediaUri(context, uri);
      }
    }
    return filePath;
  }
  
  //非媒体文件中查找
  private static String getFilePathForNonMediaUri(Context context, Uri uri) {
    String filePath = "";
    Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
    if (cursor != null) {
      if (cursor.moveToFirst()) {
        int columnIndex = cursor.getColumnIndexOrThrow("_data");
        filePath = cursor.getString(columnIndex);
      }
      cursor.close();
    }
    return filePath;
  }
  
  public static Bitmap getURLimage(String url) {
    Bitmap bmp = null;
    try {
      URL myurl = new URL(url);
      // 获得连接
      HttpURLConnection conn = (HttpURLConnection) myurl.openConnection();
      conn.setConnectTimeout(6000);//设置超时
      conn.setDoInput(true);
      conn.setUseCaches(false);//不缓存
      conn.connect();
      InputStream is = conn.getInputStream();//获得图片的数据流
      bmp = BitmapFactory.decodeStream(is);
      is.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return bmp;
  }
  
  
  public static Bitmap createQRCodeBitmap(String content, int width,int height,
  String character_set,String error_correction_level,
  String margin) {
    int color_black=Color.BLACK;
    int color_white=Color.WHITE;
    // 字符串内容判空
    if (TextUtils.isEmpty(content)) {
      return null;
    }
    // 宽和高>=0
    if (width < 0 || height < 0) {
      return null;
    }
    try {
      /** 1.设置二维码相关配置 */
      Hashtable<EncodeHintType, String> hints = new Hashtable<>();
      // 字符转码格式设置
      if (!TextUtils.isEmpty(character_set)) {
        hints.put(EncodeHintType.CHARACTER_SET, character_set);
      }
      // 容错率设置
      if (!TextUtils.isEmpty(error_correction_level)) {
        hints.put(EncodeHintType.ERROR_CORRECTION, error_correction_level);
      }
      // 空白边距设置
      if (!TextUtils.isEmpty(margin)) {
        hints.put(EncodeHintType.MARGIN, margin);
      }
      /** 2.将配置参数传入到QRCodeWriter的encode方法生成BitMatrix(位矩阵)对象 */
      BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
      
      /** 3.创建像素数组,并根据BitMatrix(位矩阵)对象为数组元素赋颜色值 */
      int[] pixels = new int[width * height];
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          //bitMatrix.get(x,y)方法返回true是黑色色块，false是白色色块
          if (bitMatrix.get(x, y)) {
            pixels[y * width + x] = color_black;//黑色色块像素设置
          } else {
            pixels[y * width + x] = color_white;// 白色色块像素设置
          }
        }
      }
      /** 4.创建Bitmap对象,根据像素数组设置Bitmap每个像素点的颜色值,并返回Bitmap对象 */
      Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
      bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
      return bitmap;
    } catch (Exception e) {
      return null;
    }
  }
  
  
  public static boolean wearvideo(Context context,String mvUrl,String title){
    boolean fhs=true;
    Intent intent = new Intent();
    intent.putExtra("mode", 1);
    intent.putExtra("url", mvUrl);
    intent.putExtra("url_backup", mvUrl);
    intent.putExtra("title", title);
    intent.putExtra("identity_name", "腕管Pro");
    try {
      intent.setComponent(new ComponentName("cn.luern0313.wristvideoplayer", "cn.luern0313.wristvideoplayer.ui.PlayerActivity"));
      context.startActivity(intent);
    }
    catch(Exception e) {
      e.printStackTrace();
      try {
        intent.setComponent(new ComponentName("cn.luern0313.wristvideoplayer_free", "cn.luern0313.wristvideoplayer_free.ui.PlayerActivity"));
        context.startActivity(intent);
      }
      catch(Exception ee)
      {
        fhs=false;
      }
    }
    return fhs;
  }
  
  
  public static String getIp(Context context){
    //获取wifi服务
    WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    //判断wifi是否开启
    /*if (!wifiManager.isWifiEnabled()) {
    wifiManager.setWifiEnabled(true);
    }*/
    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
    int ipAddress = wifiInfo.getIpAddress();
    String ip = intToIp(ipAddress);
    return ip;
  }
  
  private static String intToIp(int i) {
    
    return (i & 0xFF ) + "." +
    ((i >> 8 ) & 0xFF) + "." +
    ((i >> 16 ) & 0xFF) + "." +
    ( i >> 24 & 0xFF) ;
  }
  
  public static String doubleToString(double num){
    //使用0.00不足位补0，#.##仅保留有效位
    return new DecimalFormat("0.00").format(num);
  }
  
  public static String fileTime(int i,String url){
    String back=null;
    try{
      Path paths = Paths.get(url);
      BasicFileAttributeView basicview = Files.getFileAttributeView(paths, BasicFileAttributeView.class,
      LinkOption.NOFOLLOW_LINKS);
      BasicFileAttributes attr = basicview.readAttributes();
      //#修改时间#
      //Date lastmodfiyTimeDate=new Date(attr.lastModifiedTime().toMillis());
      //#创建时间#
      //Date CreateTimeDate= new Date(attr.creationTime().toMillis());
      long time = attr.lastModifiedTime().toMillis();
      String sDate =null;
      if (i==1){
        SimpleDateFormat shortDateFormat = new SimpleDateFormat("yy-MM-dd hh:mm");
        sDate = shortDateFormat.format(new Date(time));
      }else if(i==2){
        SimpleDateFormat shortDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        sDate = shortDateFormat.format(new Date(time));
      }
      back = sDate;
    } catch (IOException e) {
    } catch (Exception e) {
    } finally {
    }
    return back;
  }
  public static String getVersionName(Context context) {
    try {
      PackageManager packageManager = context.getPackageManager();
      PackageInfo packageInfo = packageManager.getPackageInfo(
      context.getPackageName(), 0);
      return packageInfo.versionName;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public static int getVersionCode(Context context) {
    try {
      PackageManager packageManager = context.getPackageManager();
      PackageInfo packageInfo = packageManager.getPackageInfo(
      context.getPackageName(), 0);
      return packageInfo.versionCode;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return 0;
  }
  
  private static String getMIMEType(File file) {
    
    String type="*/*";
    String fName = file.getName();
    //获取后缀名前的分隔符"."在fName中的位置。
    int dotIndex = fName.lastIndexOf(".");
    if(dotIndex < 0){
      return type;
    }
    /* 获取文件的后缀名 */
    String end=fName.substring(dotIndex,fName.length()).toLowerCase();
    if(end=="")return type;
    //在MIME和文件类型的匹配表中找到对应的MIME类型。
    for(int i=0;i<MIME_MapTable.length;i++){ //MIME_MapTable??在这里你一定有疑问，这个MIME_MapTable是什么？
      if(end.equals(MIME_MapTable[i][0]))
      type = MIME_MapTable[i][1];
    }
    return type;
  }
  private static void grantUriPermission (Context context, Uri fileUri, Intent intent) {
    List<ResolveInfo> resInfoList = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
    for (ResolveInfo resolveInfo : resInfoList) {
      String packageName = resolveInfo.activityInfo.packageName;
      context.grantUriPermission(packageName, fileUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
    }
  }
  
  private static final String[][] MIME_MapTable={
    //{后缀名， MIME类型}
    {".3gp",    "video/3gpp"},
    {".apk",    "application/vnd.android.package-archive"},
    {".asf",    "video/x-ms-asf"},
    {".avi",    "video/x-msvideo"},
    {".bin",    "application/octet-stream"},
    {".bmp",    "image/bmp"},
    {".c",  "text/plain"},
    {".class",  "application/octet-stream"},
    {".conf",   "text/plain"},
    {".cpp",    "text/plain"},
    {".doc",    "application/msword"},
    {".docx",   "application/vnd.openxmlformats-officedocument.wordprocessingml.document"},
    {".xls",    "application/vnd.ms-excel"},
    {".xlsx",   "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"},
    {".exe",    "application/octet-stream"},
    {".gif",    "image/gif"},
    {".gtar",   "application/x-gtar"},
    {".gz", "application/x-gzip"},
    {".h",  "text/plain"},
    {".htm",    "text/html"},
    {".html",   "text/html"},
    {".jar",    "application/java-archive"},
    {".java",   "text/plain"},
    {".jpeg",   "image/jpeg"},
    {".jpg",    "image/jpeg"},
    {".js", "application/x-javascript"},
    {".log",    "text/plain"},
    {".m3u",    "audio/x-mpegurl"},
    {".m4a",    "audio/mp4a-latm"},
    {".m4b",    "audio/mp4a-latm"},
    {".m4p",    "audio/mp4a-latm"},
    {".m4u",    "video/vnd.mpegurl"},
    {".m4v",    "video/x-m4v"},
    {".mov",    "video/quicktime"},
    {".mp2",    "audio/x-mpeg"},
    {".mp3",    "audio/x-mpeg"},
    {".mp4",    "video/mp4"},
    {".mpc",    "application/vnd.mpohun.certificate"},
    {".mpe",    "video/mpeg"},
    {".mpeg",   "video/mpeg"},
    {".mpg",    "video/mpeg"},
    {".mpg4",   "video/mp4"},
    {".mpga",   "audio/mpeg"},
    {".msg",    "application/vnd.ms-outlook"},
    {".ogg",    "audio/ogg"},
    {".pdf",    "application/pdf"},
    {".png",    "image/png"},
    {".pps",    "application/vnd.ms-powerpoint"},
    {".ppt",    "application/vnd.ms-powerpoint"},
    {".pptx",   "application/vnd.openxmlformats-officedocument.presentationml.presentation"},
    {".prop",   "text/plain"},
    {".rc", "text/plain"},
    {".rmvb",   "audio/x-pn-realaudio"},
    {".rtf",    "application/rtf"},
    {".sh", "text/plain"},
    {".tar",    "application/x-tar"},
    {".tgz",    "application/x-compressed"},
    {".txt",    "text/plain"},
    {".wav",    "audio/x-wav"},
    {".wma",    "audio/x-ms-wma"},
    {".wmv",    "audio/x-ms-wmv"},
    {".wps",    "application/vnd.ms-works"},
    {".xml",    "text/plain"},
    {".z",  "application/x-compress"},
    {".zip",    "application/x-zip-compressed"},
    {"",        "*/*"}
  };
  
  public static void openFile(Context context, File file){
    try {
      Intent intent = new Intent();
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      //设置intent的Action属性
      intent.setAction(Intent.ACTION_VIEW);
      //获取文件file的MIME类型
      String type = getMIMEType(file);
      //设置intent的data和Type属性。android 7.0以上crash,改用provider
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Uri fileUri = FileProvider.getUriForFile(context, context.getPackageName()+".provider", file);//android 7.0以上
        intent.setDataAndType(fileUri, type);
        grantUriPermission(context, fileUri, intent);
      }else {
        intent.setDataAndType(/*uri*/Uri.fromFile(file), type);
      }
      //跳转
      context.startActivity(intent);
    }catch (Exception e){
      
    }
    
  }
  
}