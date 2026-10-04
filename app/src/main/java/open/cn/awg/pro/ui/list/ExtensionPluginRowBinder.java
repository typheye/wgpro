package open.cn.awg.pro.ui.list;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Map;

import open.cn.awg.pro.R;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.NativeFiles;
import open.cn.awg.pro.data.LocalDatabaseBridge;
import open.cn.awg.pro.plugin.ExtensionPluginBridge;
import open.cn.awg.pro.plugin.ExtensionPluginDetailActivity;

/** Native open and long-press behavior for installed extension rows. */
public final class ExtensionPluginRowBinder implements NativeListAdapter.RowBinder {
    private final Activity owner;

    public ExtensionPluginRowBinder(@NonNull Activity owner) {
        this.owner = owner;
    }

    @Override
    public void bind(@NonNull View itemView, @NonNull Map<Integer, Object> row, int position) {
        View root = itemView.findViewById(R.id.xdbj1);
        root.setOnClickListener(this::openPlugin);
        root.setOnLongClickListener(this::selectPlugin);
    }

    private void openPlugin(View view) {
        Object id = NativeListAdapter.valueForView(view, R.id.wb2);
        Object title = NativeListAdapter.valueForView(view, R.id.wb1);
        LocalDatabaseBridge database = new LocalDatabaseBridge(owner);
        database.setup_pluginlist();
        if (database.findid_pluginlist(id) == 0) {
            new AppRuntimeBridge(owner).tsk(owner, "提示", "该扩展插件不存在,请刷新列表后重试");
            return;
        }
        writePluginCache("data/cjlb/lbx_id", id);
        writePluginCache("data/cjlb/lbx_title", title);
        String enabledCheck = NativeFiles.readText(owner, AppPaths.appPath("settings/f19/set1.inf"));
        if ("true".equals(enabledCheck)
                && !new ExtensionPluginBridge(owner).newG(Integer.parseInt(String.valueOf(id)))) {
            return;
        }
        owner.startActivity(new Intent(owner, ExtensionPluginDetailActivity.class));
    }

    private boolean selectPlugin(View view) {
        Object id = NativeListAdapter.valueForView(view, R.id.wb2);
        Object title = NativeListAdapter.valueForView(view, R.id.wb1);
        new ExtensionPluginBridge(owner).cjlbcd(owner, 2);
        writePluginCache("data/cjlb/lbx_id", id);
        writePluginCache("data/cjlb/lbx_title", title);
        return true;
    }

    private void writePluginCache(String path, Object value) {
        NativeFiles.writeText(owner, AppPaths.appPath(path), String.valueOf(value));
    }
}
