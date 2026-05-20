/**
 ******************************************************************************
 * @file    EmptyLocationListener.java
 * @author  Typheye
 * @brief   Empty LocationListener adapter used as a compatibility placeholder.
 ******************************************************************************
 * @attention
 *
 * Copyright (c) 2021-2026 Typheye. All rights reserved.
 *
 * This software is licensed under terms that can be found in the LICENSE file
 * in the root directory of this software component.
 * If no LICENSE file comes with this software, it is provided AS-IS.
 *
 ******************************************************************************
 */
package open.cn.awg.pro.core;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

class EmptyLocationListener implements LocationListener {
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