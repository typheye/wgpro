package open.cn.awg.pro.ui.list;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Map;

import open.cn.awg.pro.R;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.plugin.ExtensionPluginBridge;

/** Native menu callbacks for extension-plugin action rows. */
public final class ExtensionPluginMenuRowBinder implements NativeListAdapter.RowBinder {
    private final Activity owner;

    public ExtensionPluginMenuRowBinder(@NonNull Activity owner) {
        this.owner = owner;
    }

    @Override
    public void bind(@NonNull View itemView, @NonNull Map<Integer, Object> row, int position) {
        View root = itemView.findViewById(R.id.xxbj1);
        root.setOnClickListener(this::runAction);
        root.setOnLongClickListener(this::showDetails);
    }

    private void runAction(View view) {
        Object a = NativeListAdapter.valueForView(view, R.id.wb1);
        Object b = NativeListAdapter.valueForView(view, R.id.wb2);
        Object c = NativeListAdapter.valueForView(view, R.id.wb3);
        Object d = NativeListAdapter.valueForView(view, R.id.wb4);
        try {
            new ExtensionPluginBridge(owner).dj_cjlbcd(owner, a, b, c, d);
        } catch (Throwable failure) {
            AppRuntimeBridge.reportError(owner, failure, "c18.*");
        }
    }

    private boolean showDetails(View view) {
        Object label = NativeListAdapter.valueForView(view, R.id.wb1);
        new AppRuntimeBridge(owner).tsk(owner, "详细信息", label);
        return true;
    }
}
