package open.cn.awg.pro.ui.list;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Map;
import java.util.Objects;

import open.cn.awg.pro.R;
import open.cn.awg.pro.core.AppRuntimeBridge;

/** Native click behavior for file-operation status rows. */
public final class FileOperationInfoRowBinder implements NativeListAdapter.RowBinder {
    private final Activity owner;

    public FileOperationInfoRowBinder(@NonNull Activity owner) {
        this.owner = owner;
    }

    @Override
    public void bind(@NonNull View itemView, @NonNull Map<Integer, Object> row, int position) {
        itemView.findViewById(R.id.xxbj1).setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        Object title = NativeListAdapter.valueForView(view, R.id.wb1);
        Object message = NativeListAdapter.valueForView(view, R.id.wb2);
        AppRuntimeBridge runtime = new AppRuntimeBridge(owner);
        if (Objects.equals(title, "返回")) {
            runtime.cdx(owner, 0, null, null, null);
        } else {
            runtime.tsk(owner, title, message);
        }
    }
}
