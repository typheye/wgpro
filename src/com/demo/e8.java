package com.demo;

import java.lang.*;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import java.util.List;
import java.util.Locale;
public class e8 {
  
  //判断是否已经安装该应用
  public static boolean app(Context context,final String PACK_NAME) {
    final PackageManager packageManager = context.getPackageManager();
    List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
    if (pinfo != null) {
      for (int i = 0; i < pinfo.size(); i++) {
        String pn = pinfo.get(i).packageName.toLowerCase(Locale.ENGLISH);
        if (pn.equals(PACK_NAME)) {
          return true;
        }
      }
    }
    return false;
  }
  
}