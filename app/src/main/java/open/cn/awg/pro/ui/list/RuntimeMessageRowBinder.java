package open.cn.awg.pro.ui.list;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Map;

import open.cn.awg.pro.R;
import open.cn.awg.pro.core.AppRuntimeBridge;

/** Native long-press detail behavior for runtime message rows. */
public final class RuntimeMessageRowBinder implements NativeListAdapter.RowBinder {
    private final Activity owner;

    public RuntimeMessageRowBinder(@NonNull Activity owner) {
        this.owner = owner;
    }

    @Override
    public void bind(@NonNull View itemView, @NonNull Map<Integer, Object> row, int position) {
        View root = itemView.findViewById(R.id.xdbj1);
        root.setOnLongClickListener(this::showDetails);
    }

    private boolean showDetails(View view) {
        Object message = NativeListAdapter.valueForView(view, R.id.wb1);
        Object time = NativeListAdapter.valueForView(view, R.id.wb3);
        new AppRuntimeBridge(owner).tsk(owner, "详细信息", "[时间]\n" + time + "\n\n[消息]\n" + message);
        return true;
    }
}
