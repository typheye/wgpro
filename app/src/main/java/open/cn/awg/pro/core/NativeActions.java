package open.cn.awg.pro.core;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/** Shared Android operations used by the existing screens and controllers. */
public final class NativeActions {
    private NativeActions() { }

    public static void onMain(Runnable action) {
        if (Looper.myLooper() == Looper.getMainLooper()) action.run();
        else new Handler(Looper.getMainLooper()).post(action);
    }

    public static Thread start(Thread thread) {
        thread.start();
        return thread;
    }

    public static void sleep(Object millis) {
        try { Thread.sleep(TextValues.longValue(millis, -1)); }
        catch (Exception ignored) { }
    }

    public static void toast(Context context, Object text) { toast(context, text, 1); }

    public static void toast(Context context, Object text, Object duration) {
        onMain(() -> Toast.makeText(context, String.valueOf(text),
                duration == null ? android.widget.Toast.LENGTH_LONG : TextValues.intValue(duration, -1)).show());
    }

    public static void launch(Context context, Class<?> target) { launch(context, target, (Bundle) null); }

    public static void launch(Context context, Class<?> target, Bundle extras) {
        Intent intent = new Intent(context, target);
        if (extras != null) intent.putExtras(extras);
        startActivity(context, intent);
    }

    public static void launch(Context context, Class<?> target, String[] keys, Object[] values) {
        Bundle extras = null;
        if (keys != null && values != null && keys.length > 0 && keys.length == values.length) {
            extras = new Bundle();
            for (int index = 0; index < keys.length; index++) {
                extras.putString(keys[index].toString(), values[index].toString());
            }
        }
        launch(context, target, extras);
    }

    public static void finish(Context context) {
        if (context instanceof Activity) ((Activity) context).finish();
    }

    public static void home(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addFlags((android.content.Intent.FLAG_ACTIVITY_NEW_TASK | android.content.Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED));
        startActivity(context, intent);
    }

    public static void openUrl(Context context, Object url) {
        startActivity(context, new Intent(Intent.ACTION_VIEW, Uri.parse(String.valueOf(url))));
    }

    private static void startActivity(Context context, Intent intent) {
        if (!(context instanceof Activity)) intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void log(Object text) {
        System.out.println(String.valueOf(text));
        Log.v("AwgPro", String.valueOf(text));
    }

    public static boolean hasPermission(Context context, Object permission) {
        if (permission == null) return false;
        String name = permission.toString();
        return ContextCompat.checkSelfPermission(context, name) == PackageManager.PERMISSION_GRANTED
                && (!(context instanceof Activity) || !ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, name));
    }

    // API 30 keeps deprecated behavior 0 to restore its original navigation-bar interaction mode.
    @android.annotation.SuppressLint("WrongConstant")
    public static void navigationBar(Activity owner, Object visible) {
        boolean hide = Boolean.FALSE.equals(visible);
        if (android.os.Build.VERSION.SDK_INT >= 30) {
            WindowInsetsController controller = owner.getWindow().getInsetsController();
            if (controller == null) return;
            if (hide) {
                controller.hide(WindowInsets.Type.navigationBars());
                controller.setSystemBarsBehavior(android.view.WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
            } else {
                controller.show(WindowInsets.Type.navigationBars());
                controller.setSystemBarsBehavior(android.os.Build.VERSION.SDK_INT >= 31 ? android.view.WindowInsetsController.BEHAVIOR_DEFAULT : android.view.WindowInsetsController.BEHAVIOR_SHOW_BARS_BY_TOUCH);
            }
        } else {
            android.view.View view = owner.getWindow().getDecorView();
            int flags = view.getSystemUiVisibility();
            view.setSystemUiVisibility(hide ? flags | 2 | 0x1000 : flags & ~2 & ~0x1000);
            if (!hide) view.requestLayout();
        }
    }
}
