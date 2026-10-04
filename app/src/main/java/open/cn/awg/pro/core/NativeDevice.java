package open.cn.awg.pro.core;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;

/** Native replacements for the legacy SDK's common device and package helpers. */
public final class NativeDevice {
    private static final int LAUNCH_PACKAGE_FLAGS = (Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

    private final Context context;

    public NativeDevice(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        Context applicationContext = context.getApplicationContext();
        this.context = applicationContext == null ? context : applicationContext;
    }

    /** Matches xt.sj(Object), including its numeric date-format selectors. */
    public static String formatDate(Object format) {
        long now = System.currentTimeMillis();
        if (format instanceof Number) {
            int selector = ((Number) format).intValue();
            if (selector == 4) {
                return String.valueOf(now);
            }
            String pattern;
            switch (selector) {
                case 0:
                    pattern = "%1$tY-%1$tm-%1$td %1$tT";
                    break;
                case 1:
                    pattern = "%1$tY/%1$tm/%1$td %1$tT";
                    break;
                case 2:
                    pattern = "%1$tY-%1$tm-%1$td";
                    break;
                case 3:
                    pattern = "%1$tT";
                    break;
                case 5:
                    pattern = "%1$tm-%1$td %1$tT";
                    break;
                default:
                    pattern = "%1$tY年%1$tm月%1$td日 %1$tT";
                    break;
            }
            return format(pattern, now);
        }

        String pattern = format.toString();
        if (pattern.length() == 1) {
            pattern = "%1$t" + pattern;
        }
        return format(pattern, now);
    }

    /** Returns package-manager order and filters system packages exactly as xt.yylb did. */
    public InstalledApp[] installedApps(boolean includeSystem) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packages = packageManager.getInstalledPackages(0);
        ArrayList<InstalledApp> result = new ArrayList<>(packages.size());
        for (PackageInfo packageInfo : packages) {
            if (!includeSystem && (packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                continue;
            }
            Drawable icon = packageInfo.applicationInfo.loadIcon(packageManager);
            String label = packageInfo.applicationInfo.loadLabel(packageManager).toString();
            result.add(new InstalledApp(packageInfo, icon, label));
        }
        return result.toArray(new InstalledApp[0]);
    }

    /** Opens a package's launcher activity with the same task flags as the legacy helper. */
    public boolean launchPackage(Object packageName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(String.valueOf(packageName));
        if (intent == null) {
            return false;
        }
        intent.setFlags(LAUNCH_PACKAGE_FLAGS);
        context.startActivity(intent);
        return true;
    }

    /** Starts an explicit component from its owning screen, matching xt.dkyy(package, class). */
    public void launchComponent(Activity owner, Object packageName, Object className) {
        if (owner == null) {
            throw new IllegalArgumentException("owner activity must not be null");
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(String.valueOf(packageName), String.valueOf(className)));
        owner.startActivity(intent);
    }

    /** Copies text to the system clipboard; null becomes the empty string as in xt.jqbxr. */
    public void copyToClipboard(Object value) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboard != null) {
            clipboard.setPrimaryClip(ClipData.newPlainText(null, value == null ? "" : value.toString()));
        }
    }

    /** Opens Android's package uninstall confirmation for the requested package. */
    public void requestUninstall(Activity owner, Object packageName) {
        if (owner == null) {
            throw new IllegalArgumentException("owner activity must not be null");
        }
        Uri packageUri = Uri.parse("package:" + String.valueOf(packageName));
        owner.startActivity(new Intent(Intent.ACTION_DELETE, packageUri));
    }

    /** Controls FLAG_KEEP_SCREEN_ON without retaining the Activity. */
    public static void setKeepScreenOn(Activity owner, boolean keepScreenOn) {
        if (owner == null) {
            throw new IllegalArgumentException("owner activity must not be null");
        }
        if (keepScreenOn) {
            owner.getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } else {
            owner.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    private static String format(String pattern, long timestamp) {
        try (Formatter formatter = new Formatter(Locale.CHINA)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timestamp);
            return formatter.format(pattern, calendar).toString();
        }
    }

    public static final class InstalledApp {
        public final String packageName;
        public final Drawable icon;
        public final String label;
        public final String versionName;
        public final int versionCode;
        public final PackageInfo packageInfo;

        private InstalledApp(PackageInfo packageInfo, Drawable icon, String label) {
            this.packageInfo = packageInfo;
            this.packageName = packageInfo.packageName;
            this.icon = icon;
            this.label = label;
            this.versionName = packageInfo.versionName;
            this.versionCode = packageInfo.versionCode;
        }
    }
}
