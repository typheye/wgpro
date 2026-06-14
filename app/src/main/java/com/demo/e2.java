package com.demo;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.Context;
import java.lang.reflect.Field;

public class e2 {
  
  /*普通的签名验证*/
  public static boolean checksignture(Context context,int sign){
    boolean same=false;
    StringBuilder text = new StringBuilder();
    try{
      PackageManager packageManager = context.getPackageManager();
      PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
      Signature[] signatures = packageInfo.signatures;
      if (signatures != null) {
        for (Signature signature : signatures) {
          text.append(signature.toString());
        }
      }
    } catch (PackageManager.NameNotFoundException e) {
      android.util.Log.e("AWGPro", "Unhandled exception", e);
    }
    same = text.toString().hashCode() == sign;
    return same;
  }
  
  /*Application验证*/
  public static boolean checkApplication(Context context,String lei){
    Context app = context.getApplicationContext();
    String name = app.getClass().getSimpleName();
    return lei.equals(name);
  }
  
  /*检测IPackageManager代理*/
  public static boolean checkIPackageManager(Context context){
    String name="";
    try{
      PackageManager packageManager = context.getPackageManager();
      Field fieid = packageManager.getClass().getDeclaredField("mPM");
      fieid.setAccessible(true);
      Object mPM = fieid.get(packageManager);
      if (mPM != null) {
        name = mPM.getClass().getName();
      }
    }catch (Exception e) {
      android.util.Log.e("AWGPro", "Unhandled exception", e);
    }
    return name.equals("android.content.pm.IPackageManager$Stub$Proxy");
  }
}
