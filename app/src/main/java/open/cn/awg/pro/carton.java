package open.cn.awg.pro;

import i.app.iClass;
 import android.support.v7.app.AppCompatActivity; import android.os.Bundle; import android.view.View; import android.app.admin.DevicePolicyManager; import android.content.ComponentName; import android.content.Context; import android.content.Intent; import android.widget.Toast; import android.os.Process; import android.provider.Settings;import java.lang.*;

public class carton extends iClass {

public android.app.admin.DevicePolicyManager devicePolicyManager;

public android.content.Context mContext;

public android.content.ComponentName componentName;

public void gets(android.content.Context context){

    mContext = context;
    devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
    componentName = new ComponentName(context, DeviceReceiver.class);
    if (devicePolicyManager.isAdminActive(componentName)) {
      
    } else {
      Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
      intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
      intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
      mContext.getString(R.string.dm_extra_add_explanation));
      mContext.startActivity(intent);
    }
  

}

public boolean state(android.content.Context context){

boolean bak;

    mContext = context;
    devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
    componentName = new ComponentName(context, DeviceReceiver.class);
    bak=devicePolicyManager.isAdminActive(componentName);
  
return bak;

}

public void setCameraDisabled(android.content.Context context, boolean disabled){

    mContext = context;
    devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
    componentName = new ComponentName(context, DeviceReceiver.class);
    if (devicePolicyManager.isAdminActive(componentName)) {
      devicePolicyManager.setCameraDisabled(componentName, disabled);
      
    } else {
      Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
      intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
      intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
      mContext.getString(R.string.dm_extra_add_explanation));
      mContext.startActivity(intent);
      
    }
  

}

public void wipeData(android.content.Context context){

    mContext = context;
    devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
    componentName = new ComponentName(context, DeviceReceiver.class);
    if (devicePolicyManager.isAdminActive(componentName)) {
      devicePolicyManager.wipeData(1);
    } else {
      Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
      intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
      intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
      mContext.getString(R.string.dm_extra_add_explanation));
      mContext.startActivity(intent);
      
    }
  

}

public void lockNow(android.content.Context context){

    mContext = context;
    devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
    componentName = new ComponentName(context, DeviceReceiver.class);
    if (devicePolicyManager.isAdminActive(componentName)) {
      devicePolicyManager.lockNow();
      
    } else {
      Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
      intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
      intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
      mContext.getString(R.string.dm_extra_add_explanation));
      mContext.startActivity(intent);
      
    }
  

}

public void startDeviceOwner(android.content.Context context){

    String text="腕管Pro";
    mContext = context;
    devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
    componentName = new ComponentName(context, DeviceReceiver.class);
    if (devicePolicyManager.isDeviceOwnerApp(componentName.getPackageName())) {
      devicePolicyManager.setOrganizationName(componentName, text);
      devicePolicyManager.setUninstallBlocked(componentName, "open.cn.awg.pro", true);
      
    } else {
      Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
      intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
      intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
      mContext.getString(R.string.dm_extra_add_explanation));
      mContext.startActivity(intent);
      
    }
  

}

public boolean isDeviceOwnerApp(android.content.Context context){

boolean bak;

    mContext = context;
    devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
    componentName = new ComponentName(context, DeviceReceiver.class);
    bak=devicePolicyManager.isDeviceOwnerApp(componentName.getPackageName());
  
return bak;

}

public void LockActivity(android.content.Context context, boolean i){

    mContext = context;
    devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
    componentName = new ComponentName(context, DeviceReceiver.class);
    if (devicePolicyManager.isDeviceOwnerApp(componentName.getPackageName())) {
      if (i==false){
        devicePolicyManager.setStatusBarDisabled(componentName, true);
        devicePolicyManager.setCameraDisabled(componentName, true);
        devicePolicyManager.setScreenCaptureDisabled(componentName, true);
      }else{
        devicePolicyManager.setStatusBarDisabled(componentName, false);
        devicePolicyManager.setCameraDisabled(componentName, false);
        devicePolicyManager.setScreenCaptureDisabled(componentName, false);
      }
      
    } else {
      Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
      intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
      intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
      mContext.getString(R.string.dm_extra_add_explanation));
      mContext.startActivity(intent);
      
    }
  

}

public void reboot(android.content.Context context){

    mContext = context;
    devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
    componentName = new ComponentName(context, DeviceReceiver.class);
    if (devicePolicyManager.isDeviceOwnerApp(componentName.getPackageName())) {
      devicePolicyManager.reboot(componentName);
      
    } else {
      Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
      intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
      intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
      mContext.getString(R.string.dm_extra_add_explanation));
      mContext.startActivity(intent);
      
    }
  

}

public void setStatusBarDisabled(android.content.Context context, boolean disabled){

    mContext = context;
    devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
    componentName = new ComponentName(context, DeviceReceiver.class);
    if (devicePolicyManager.isDeviceOwnerApp(componentName.getPackageName())) {
      devicePolicyManager.setStatusBarDisabled(componentName, disabled);
      
    } else {
      Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
      intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
      intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
      mContext.getString(R.string.dm_extra_add_explanation));
      mContext.startActivity(intent);
      
    }
  

}

public void setUninstallBlocked(android.content.Context context, java.lang.String packageName, boolean uninstallBlocked){

    mContext = context;
    devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
    componentName = new ComponentName(context, DeviceReceiver.class);
    if (devicePolicyManager.isDeviceOwnerApp(componentName.getPackageName())) {
      devicePolicyManager.setUninstallBlocked(componentName, packageName, uninstallBlocked);
      
    } else {
      Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
      intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
      intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
      mContext.getString(R.string.dm_extra_add_explanation));
      mContext.startActivity(intent);
      
    }
  

}

public void setDeviceOwnerLockScreenInfo(android.content.Context context, java.lang.String text){

    mContext = context;
    devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
    componentName = new ComponentName(context, DeviceReceiver.class);
    if (devicePolicyManager.isDeviceOwnerApp(componentName.getPackageName())) {
      devicePolicyManager.setDeviceOwnerLockScreenInfo(componentName, text);
      
    } else {
      Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
      intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
      intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
      mContext.getString(R.string.dm_extra_add_explanation));
      mContext.startActivity(intent);
      
    }
  

}

public void setOrganizationName(android.content.Context context, java.lang.String text){

    mContext = context;
    devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
    componentName = new ComponentName(context, DeviceReceiver.class);
    if (devicePolicyManager.isDeviceOwnerApp(componentName.getPackageName())) {
      devicePolicyManager.setOrganizationName(componentName, text);
      
    } else {
      Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
      intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
      intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
      mContext.getString(R.string.dm_extra_add_explanation));
      mContext.startActivity(intent);
      
    }
  

}

public carton(i.runlibrary.app.AppInfo _APPINFO){
super(_APPINFO);

}

    public final carton lei = this, 类 = this;
}
