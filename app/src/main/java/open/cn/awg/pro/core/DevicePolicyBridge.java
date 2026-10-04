package open.cn.awg.pro.core;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import open.cn.awg.pro.R;
import open.cn.awg.pro.receiver.DeviceReceiver;

/** Native Android bridge for the app's device-admin controls. */
public class DevicePolicyBridge {
    public final DevicePolicyBridge lei = this, 类 = this;
    private final Context applicationContext;

    public DevicePolicyBridge(Context context) {
        Context owner = context == null ? null : context.getApplicationContext();
        applicationContext = owner == null ? context : owner;
    }

    private static DevicePolicyManager manager(Context context) {
        return (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
    }

    private static ComponentName admin(Context context) {
        return new ComponentName(context, DeviceReceiver.class);
    }

    private static void requestAdmin(Context context, ComponentName component) {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, component);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                context.getString(R.string.dm_extra_add_explanation));
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    public void gets(Context context) {
        ComponentName component = admin(context);
        if (!manager(context).isAdminActive(component)) {
            requestAdmin(context, component);
        }
    }

    public boolean state(Context context) {
        return manager(context).isAdminActive(admin(context));
    }

    public void setCameraDisabled(Context context, boolean disabled) {
        DevicePolicyManager policy = manager(context);
        ComponentName component = admin(context);
        if (policy.isAdminActive(component)) {
            policy.setCameraDisabled(component, disabled);
        } else {
            requestAdmin(context, component);
        }
    }

    public void wipeData(Context context) {
        DevicePolicyManager policy = manager(context);
        if (policy.isAdminActive(admin(context))) {
            policy.wipeData(1);
        } else {
            requestAdmin(context, admin(context));
        }
    }

    public void lockNow(Context context) {
        DevicePolicyManager policy = manager(context);
        ComponentName component = admin(context);
        if (policy.isAdminActive(component)) {
            policy.lockNow();
        } else {
            requestAdmin(context, component);
        }
    }

    public void startDeviceOwner(Context context) {
        DevicePolicyManager policy = manager(context);
        ComponentName component = admin(context);
        if (policy.isDeviceOwnerApp(component.getPackageName())) {
            policy.setOrganizationName(component, "腕管Pro");
            policy.setUninstallBlocked(component, "open.cn.awg.pro", true);
        } else {
            requestAdmin(context, component);
        }
    }

    public boolean isDeviceOwnerApp(Context context) {
        ComponentName component = admin(context);
        return manager(context).isDeviceOwnerApp(component.getPackageName());
    }

    public void LockActivity(Context context, boolean enabled) {
        DevicePolicyManager policy = manager(context);
        ComponentName component = admin(context);
        if (policy.isDeviceOwnerApp(component.getPackageName())) {
            policy.setStatusBarDisabled(component, !enabled);
            policy.setCameraDisabled(component, !enabled);
            policy.setScreenCaptureDisabled(component, !enabled);
        } else {
            requestAdmin(context, component);
        }
    }

    public void reboot(Context context) {
        DevicePolicyManager policy = manager(context);
        ComponentName component = admin(context);
        if (policy.isDeviceOwnerApp(component.getPackageName())) {
            policy.reboot(component);
        } else {
            requestAdmin(context, component);
        }
    }

    public void setStatusBarDisabled(Context context, boolean disabled) {
        DevicePolicyManager policy = manager(context);
        ComponentName component = admin(context);
        if (policy.isDeviceOwnerApp(component.getPackageName())) {
            policy.setStatusBarDisabled(component, disabled);
        } else {
            requestAdmin(context, component);
        }
    }

    public void setUninstallBlocked(Context context, String packageName, boolean uninstallBlocked) {
        DevicePolicyManager policy = manager(context);
        ComponentName component = admin(context);
        if (policy.isDeviceOwnerApp(component.getPackageName())) {
            policy.setUninstallBlocked(component, packageName, uninstallBlocked);
        } else {
            requestAdmin(context, component);
        }
    }

    public void setDeviceOwnerLockScreenInfo(Context context, String text) {
        DevicePolicyManager policy = manager(context);
        ComponentName component = admin(context);
        if (policy.isDeviceOwnerApp(component.getPackageName())) {
            policy.setDeviceOwnerLockScreenInfo(component, text);
        } else {
            requestAdmin(context, component);
        }
    }

    public void setOrganizationName(Context context, String text) {
        DevicePolicyManager policy = manager(context);
        ComponentName component = admin(context);
        if (policy.isDeviceOwnerApp(component.getPackageName())) {
            policy.setOrganizationName(component, text);
        } else {
            requestAdmin(context, component);
        }
    }
}
