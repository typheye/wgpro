package open.cn.awg.pro.ui.list;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.Map;
import java.util.Objects;

import open.cn.awg.pro.R;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.NativeFiles;
import open.cn.awg.pro.core.TextValues;
import open.cn.awg.pro.file.FileManagerActivity;

/** Native root selection and sentinel-row presentation for the file-root list. */
public final class FileRootRowBinder implements NativeListAdapter.RowBinder {
    private final Activity owner;

    public FileRootRowBinder(@NonNull Activity owner) {
        this.owner = owner;
    }

    @Override
    public void bind(@NonNull View itemView, @NonNull Map<Integer, Object> row, int position) {
        boolean placeholder = TextValues.intValue(row.get(-1), -1) == -1;
        View root = itemView.findViewById(R.id.xdbj1);
        root.setVisibility(placeholder ? View.GONE : View.VISIBLE);
        root.setClickable(!placeholder);
        root.setLongClickable(!placeholder);
        itemView.findViewById(R.id.dp20).setVisibility(placeholder ? View.VISIBLE : View.GONE);
        root.setOnClickListener(this::openRoot);
        root.setOnLongClickListener(this::showDetails);
    }

    private void openRoot(View view) {
        Object pathValue = NativeListAdapter.valueForView(view, R.id.wb3);
        Object mode = NativeListAdapter.valueForView(view, R.id.wb2);
        Object title = NativeListAdapter.valueForView(view, R.id.wb1);
        String path = text(pathValue);
        NativeFiles.writeText(owner, AppPaths.appPath("cache/file/title"), "< " + title);

        if (Objects.equals(mode, "a0")) {
            openFileManager(path);
        } else if (Objects.equals(mode, "a1")) {
            if (ContextCompat.checkSelfPermission(owner, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                openFileManager(path);
            } else {
                new AppRuntimeBridge(owner).tsk(owner, "提示",
                        "您未授权应用\"修改或删除您共享存储空间中的内容\"，请先前往设置授权后再使用");
            }
        } else if (Objects.equals(mode, "a2")) {
            openFileManager(path);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R
                    && !Environment.isExternalStorageManager()
                    && !Objects.equals(NativeFiles.readText(owner,
                    AppPaths.appPath("settings/d1/set2.inf")), "true")) {
                new AppRuntimeBridge(owner).tsk(owner, "提示",
                        "您未授权应用\"所有文件访问权限\"，可能会存在设备兼容问题，请尝试授权。\n"
                                + "您也可前往应用设置>文件管理页面，打开\"忽略兼容提示\"，使本提示不再弹出。");
            }
        }
    }

    private void openFileManager(String path) {
        NativeFiles.writeText(owner, AppPaths.appPath("cache/00"), path);
        Intent intent = new Intent(owner, FileManagerActivity.class);
        intent.putExtra("url", path);
        intent.putExtra("root", path);
        owner.startActivity(intent);
    }

    private boolean showDetails(View view) {
        Object title = NativeListAdapter.valueForView(view, R.id.wb1);
        Object path = NativeListAdapter.valueForView(view, R.id.wb3);
        new AppRuntimeBridge(owner).tsk(owner, "详细信息",
                "[标题]\n" + title + "\n\n[路径]\n" + path);
        return true;
    }

    private static String text(Object value) {
        return value == null ? "" : String.valueOf(value);
    }
}
