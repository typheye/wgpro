package com.demo;

import java.lang.*;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;


public class e0 {
  /*获取签名*/
  public static int signture(Context context){
    StringBuilder text = new StringBuilder();
    try{
      PackageManager packageManager = context.getPackageManager();
      PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
      Signature[] signatures = packageInfo.signatures;
      for (Signature signature : signatures) {
        text.append(signature.toString());
      }
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return text.toString().hashCode();
  }
}