package open.cn.awg.pro;

import open.cn.awg.pro.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
public class DeviceManger {
  
  private DevicePolicyManager devicePolicyManager;
  private Context mContext;
  private ComponentName componentName;
  
  public DeviceManger(Context context) {
    mContext = context;
    //获取设备管理服务
    devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
    //DeviceReceiver 继承自 DeviceAdminReceiver
    componentName = new ComponentName(context, DeviceReceiver.class);
  }
  
  
  // 测试
  public void test(Context context) {
    DevicePolicyManager dm = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
  }
  
  // 激活设备管理器
  public void enableDeviceManager() {
    //判断是否激活  如果没有就启动激活设备
    if (!devicePolicyManager.isAdminActive(componentName)) {
      Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
      
      intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
      intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
      mContext.getString(R.string.dm_extra_add_explanation));
      mContext.startActivity(intent);
    } else {
    }
  }
  
  // 取消激活设备管理器
  public void disableDeviceManager() {
    devicePolicyManager.removeActiveAdmin(componentName);
  }
}