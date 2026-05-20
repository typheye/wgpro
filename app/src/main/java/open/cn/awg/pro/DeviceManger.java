/**
 ******************************************************************************
 * @file    DeviceManger.java
 * @author  Typheye
 * @brief   Device policy helper methods.
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
package open.cn.awg.pro;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class DeviceManger {

    private final DevicePolicyManager devicePolicyManager;
    private final Context mContext;
    private final ComponentName componentName;

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