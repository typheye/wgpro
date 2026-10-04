package open.cn.awg.pro.ui.list;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;

import androidx.annotation.NonNull;

import com.kingqi.zwcj.日期工具;
import com.kingqi.zwcj.应用工具;

import java.util.Map;
import java.util.Objects;

import open.cn.awg.pro.R;
import open.cn.awg.pro.core.AppRuntimeBridge;

/** Native package launch and details behavior for installed-app rows. */
public final class InstalledAppRowBinder implements NativeListAdapter.RowBinder {
    private final Activity owner;

    public InstalledAppRowBinder(@NonNull Activity owner) {
        this.owner = owner;
    }

    @Override
    public void bind(@NonNull View itemView, @NonNull Map<Integer, Object> row, int position) {
        View root = itemView.findViewById(R.id.xdbj1);
        root.setOnClickListener(this::launchPackage);
        root.setOnLongClickListener(this::showPackageDetails);
    }

    private void launchPackage(View view) {
        Object packageValue = NativeListAdapter.valueForView(view, -1);
        String packageName = String.valueOf(packageValue);
        AppRuntimeBridge runtime = new AppRuntimeBridge(owner);
        if (Objects.equals(packageName, "open.cn.awg.pro")) {
            runtime.tsk(owner, "提示", "您已经在该应用内了");
            return;
        }
        try {
            Intent launch = owner.getPackageManager().getLaunchIntentForPackage(packageName);
            if (launch == null) {
                throw new PackageManager.NameNotFoundException(packageName);
            }
            owner.startActivity(launch);
        } catch (Throwable failure) {
            AppRuntimeBridge.reportError(owner, failure, "c17.*");
        }
    }

    private boolean showPackageDetails(View view) {
        Object packageValue = NativeListAdapter.valueForView(view, -1);
        String packageName = String.valueOf(packageValue);
        String signature = 应用工具.获取应用签名(owner, packageName);
        String firstInstall = 日期工具.格式化日期时间(
                应用工具.获取应用第一次安装日期(owner, packageName));
        String lastUpdate = 日期工具.格式化日期时间(
                应用工具.获取应用更新日期(owner, packageName));
        String installMarket = 应用工具.获取应用的安装市场(owner, packageName);
        String versionName = 应用工具.获取应用版本名称(owner, packageName);
        int versionCode = 应用工具.获取应用版本号(owner, packageName);
        String appName = 应用工具.获取应用名称(owner, packageName);
        String message = "[应用名称]\n" + appName + "\n\n[应用版本]\n" + versionName
                + "\n\n[应用版本号]\n" + versionCode + "\n\n[应用安装日期]\n" + firstInstall
                + "\n\n[应用更新日期]\n" + lastUpdate;
        new AppRuntimeBridge(owner).tsk(owner, "详细信息", message);
        return true;
    }
}
