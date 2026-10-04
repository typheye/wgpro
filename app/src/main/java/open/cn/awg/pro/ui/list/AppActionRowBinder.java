package open.cn.awg.pro.ui.list;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Map;

import open.cn.awg.pro.R;
import open.cn.awg.pro.core.AppRuntimeBridge;

/** Native event behavior for app-action rows. */
public final class AppActionRowBinder implements NativeListAdapter.RowBinder {
    private final Activity owner;

    public AppActionRowBinder(@NonNull Activity owner) {
        this.owner = owner;
    }

    @Override
    public void bind(@NonNull View itemView, @NonNull Map<Integer, Object> row, int position) {
        View root = itemView.findViewById(R.id.app_action_item_root);
        root.setOnClickListener(this::runAction);
        root.setOnLongClickListener(this::showDetails);
    }

    private void runAction(View view) {
        Object packageName = NativeListAdapter.valueForView(view, -1);
        Object className = NativeListAdapter.valueForView(view, -2);
        try {
            Intent intent = new Intent().setComponent(
                    new ComponentName(String.valueOf(packageName), String.valueOf(className)));
            owner.startActivity(intent);
        } catch (Throwable failure) {
            new AppRuntimeBridge(owner).tsk(owner, "提示", "没有可执行该操作的应用");
        }
    }

    private boolean showDetails(View view) {
        Object name = NativeListAdapter.valueForView(view, R.id.wb1);
        Object description = NativeListAdapter.valueForView(view, R.id.wb2);
        new AppRuntimeBridge(owner).tsk(owner, "详细信息",
                "[功能名称]\n" + name + "\n\n[功能简介]\n" + description);
        return true;
    }
}
