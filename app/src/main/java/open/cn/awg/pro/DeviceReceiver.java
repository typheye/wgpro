package open.cn.awg.pro;

import java.lang.*;
import open.cn.awg.pro.R;
import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.widget.Toast;
public class DeviceReceiver extends DeviceAdminReceiver {
  @Override
  public void onEnabled(Context context, Intent intent) {
    // 设备管理：可用
  }
  
  @Override
  public void onDisabled(final Context context, Intent intent) {
    // 设备管理：不可用
    
  }
  
  @Override
  public void onPasswordChanged(Context context, Intent intent) {
    // 设备管理：密码己经改变
  }
  
  @Override
  public void onPasswordFailed(Context context, Intent intent) {
    // 设备管理：改变密码失败
  }
  
  @Override
  public void onPasswordSucceeded(Context context, Intent intent) {
    // 设备管理：改变密码成功
  }
  
  @Override
  public void onPasswordExpiring(Context context, Intent intent, UserHandle user) {
    super.onPasswordExpiring(context, intent, user);
  }
  
}