package open.cn.awg.pro.ui.list;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Map;

import open.cn.awg.pro.R;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.NativeFiles;

/** Native open and long-press file-management behavior for file rows. */
public final class FileListRowBinder implements NativeListAdapter.RowBinder {
    private final Activity owner;

    public FileListRowBinder(@NonNull Activity owner) {
        this.owner = owner;
    }

    @Override
    public void bind(@NonNull View itemView, @NonNull Map<Integer, Object> row, int position) {
        View root = itemView.findViewById(R.id.xdbj1);
        root.setOnClickListener(this::openFile);
        root.setOnLongClickListener(this::showFileActions);
    }

    private void openFile(View view) {
        Object path = NativeListAdapter.valueForView(view, R.id.wb2);
        Object label = NativeListAdapter.valueForView(view, R.id.wb3);
        new AppRuntimeBridge(owner).fileTouch(owner, label, path);
    }

    private boolean showFileActions(View view) {
        AppRuntimeBridge runtime = new AppRuntimeBridge(owner);
        try {
            Object label = NativeListAdapter.valueForView(view, R.id.wb1);
            Object path = NativeListAdapter.valueForView(view, R.id.wb2);
            String[] info = runtime.fileinfo("%", path);
            String displayPath = info[2];
            Object action = NativeListAdapter.valueForView(view, R.id.wb3);
            if ("返回上层目录".equals(action)) {
                runtime.cdx(owner, 3, String.valueOf(label), String.valueOf(path), displayPath);
            } else {
                int type = NativeFiles.type(owner, String.valueOf(path));
                if (type == 1) {
                    runtime.cdx(owner, 2, String.valueOf(label), String.valueOf(path), displayPath);
                } else if (type == 2) {
                    runtime.cdx(owner, 1, String.valueOf(label), String.valueOf(path), displayPath);
                }
            }
        } catch (Throwable failure) {
            AppRuntimeBridge.reportError(owner, failure, "c2.*");
        }
        return true;
    }
}
