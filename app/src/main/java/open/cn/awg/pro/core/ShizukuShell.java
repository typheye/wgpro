package open.cn.awg.pro.core;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.os.RemoteException;
import open.cn.awg.pro.service.IShizukuShell;
import open.cn.awg.pro.service.ShizukuShellService;
import rikka.shizuku.Shizuku;

/** Permission and native Binder connection for the app's Shizuku shell option. */
public final class ShizukuShell {
    private static final int PERMISSION_REQUEST = 8961876;
    private static String packageName;
    private static IShizukuShell service;
    private static boolean permissionListenerAdded;
    private static boolean binderListenersAdded;

    private static final ServiceConnection CONNECTION = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            service = binder != null && binder.pingBinder() ? IShizukuShell.Stub.asInterface(binder) : null;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) { service = null; }
    };
    private static final Shizuku.OnBinderReceivedListener RECEIVED = () -> {
        if (!Shizuku.isPreV11() && Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED) bind();
    };
    private static final Shizuku.OnBinderDeadListener DEAD = () -> service = null;
    private static final Shizuku.OnRequestPermissionResultListener PERMISSION = (requestCode, result) -> {
        if (requestCode == PERMISSION_REQUEST && result == PackageManager.PERMISSION_GRANTED) listenForBinder();
    };

    private ShizukuShell() { }

    public static void initialize(Context context) {
        packageName = context.getPackageName();
        if (!Shizuku.pingBinder()) return;
        if (!permissionListenerAdded) {
            Shizuku.addRequestPermissionResultListener(PERMISSION);
            permissionListenerAdded = true;
        }
        if (Shizuku.isPreV11() || Shizuku.checkSelfPermission() != PackageManager.PERMISSION_GRANTED) {
            Shizuku.requestPermission(PERMISSION_REQUEST);
            return;
        }
        listenForBinder();
    }

    private static void listenForBinder() {
        if (binderListenersAdded) return;
        binderListenersAdded = true;
        Shizuku.addBinderReceivedListenerSticky(RECEIVED);
        Shizuku.addBinderDeadListener(DEAD);
    }

    private static void bind() {
        Shizuku.UserServiceArgs args = new Shizuku.UserServiceArgs(
                new ComponentName(packageName, ShizukuShellService.class.getName()))
                .daemon(false).processNameSuffix("service").debuggable(false).version(1);
        Shizuku.bindUserService(args, CONNECTION);
    }

    public static String execute(String command) {
        IShizukuShell current = service;
        if (current == null) return null;
        try { return current.execute(command); }
        catch (RemoteException failure) { return null; }
    }
}
