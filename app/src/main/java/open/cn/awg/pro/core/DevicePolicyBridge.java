/**
 ******************************************************************************
 * @file    DevicePolicyBridge.java
 * @author  Typheye
 * @brief   Bridge around Android DevicePolicyManager controls.
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

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import i.app.iClass;

import open.cn.awg.pro.R;
import open.cn.awg.pro.receiver.DeviceReceiver;

public class DevicePolicyBridge extends iClass {

    public final DevicePolicyBridge lei = this, 类 = this;
    public DevicePolicyManager devicePolicyManager;
    public Context mContext;
    public ComponentName componentName;

    public DevicePolicyBridge(i.runlibrary.app.AppInfo _APPINFO) {
        super(_APPINFO);

    }

    public void gets(Context context) {

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

    public boolean state(Context context) {

        boolean bak;

        mContext = context;
        devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(context, DeviceReceiver.class);
        bak = devicePolicyManager.isAdminActive(componentName);

        return bak;

    }

    public void setCameraDisabled(Context context, boolean disabled) {

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

    public void wipeData(Context context) {

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

    public void lockNow(Context context) {

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

    public void startDeviceOwner(Context context) {

        String text = "腕管Pro";
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

    public boolean isDeviceOwnerApp(Context context) {

        boolean bak;

        mContext = context;
        devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(context, DeviceReceiver.class);
        bak = devicePolicyManager.isDeviceOwnerApp(componentName.getPackageName());

        return bak;

    }

    public void LockActivity(Context context, boolean i) {

        mContext = context;
        devicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(context, DeviceReceiver.class);
        if (devicePolicyManager.isDeviceOwnerApp(componentName.getPackageName())) {
            if (!i) {
                devicePolicyManager.setStatusBarDisabled(componentName, true);
                devicePolicyManager.setCameraDisabled(componentName, true);
                devicePolicyManager.setScreenCaptureDisabled(componentName, true);
            } else {
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

    public void reboot(Context context) {

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

    public void setStatusBarDisabled(Context context, boolean disabled) {

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

    public void setUninstallBlocked(Context context, String packageName, boolean uninstallBlocked) {

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

    public void setDeviceOwnerLockScreenInfo(Context context, String text) {

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

    public void setOrganizationName(Context context, String text) {

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
}
