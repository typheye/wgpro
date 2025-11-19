package open.cn.awg.pro;

import java.lang.*;
import open.cn.awg.pro.R;

import android.location.*;
import android.os.*;

class locationListener implements LocationListener {
  @Override
  public void onLocationChanged(Location location) {
    
  }
  
  @Override
  public void onStatusChanged(String provider, int status, Bundle extras) {
    
  }
  
  @Override
  public void onProviderEnabled(String provider) {
    //Log.e("位置提供器：", "启用");
  }
  
  @Override
  public void onProviderDisabled(String provider) {
    
  }
}