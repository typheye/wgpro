package open.cn.awg.pro.ui.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.util.Map;
import java.util.Objects;

import open.cn.awg.pro.R;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.NativeFiles;
import open.cn.awg.pro.core.TextValues;
import open.cn.awg.pro.file.DeleteConfirmActivity;

/** Native file-operation menu actions for copy, move, refresh, and file details. */
public final class FileOperationMenuRowBinder implements NativeListAdapter.RowBinder {
    private final Activity owner;
    private final AppRuntimeBridge runtime;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public FileOperationMenuRowBinder(@NonNull Activity owner) {
        this.owner = owner;
        this.runtime = new AppRuntimeBridge(owner);
    }

    @Override
    public void bind(@NonNull View itemView, @NonNull Map<Integer, Object> row, int position) {
        int role = TextValues.intValue(row.get(-1), -1);
        TextView label = itemView.findViewById(R.id.wb1);
        label.setTextColor(ContextCompat.getColor(owner,
                role == 1 ? R.color.colorTextFalse : R.color.colorTextU1));

        View root = itemView.findViewById(R.id.xxbj1);
        boolean placeholder = role == -1;
        root.setVisibility(placeholder ? View.GONE : View.VISIBLE);
        root.setClickable(!placeholder);
        root.setLongClickable(!placeholder);
        itemView.findViewById(R.id.dp20).setVisibility(placeholder ? View.VISIBLE : View.GONE);
        root.setOnClickListener(this::onClick);
        root.setOnLongClickListener(this::onLongClick);
    }

    private void onClick(View view) {
        Object action = NativeListAdapter.valueForView(view, R.id.wb1);
        Object b = NativeListAdapter.valueForView(view, R.id.wb2);
        Object c = NativeListAdapter.valueForView(view, R.id.wb3);
        Object d = NativeListAdapter.valueForView(view, R.id.wb4);
        String currentDirectory = textAt(R.id.zywblj);
        onMain(() -> {
            TextView title = owner.findViewById(R.id.title_bar);
            if (title != null) title.setText("提示");
            View progress = owner.findViewById(R.id.xxbj9);
            if (progress != null) progress.setVisibility(View.VISIBLE);
        });
        new Thread(() -> perform(action, b, c, d, currentDirectory), "file-operation-menu").start();
    }

    private void perform(Object action, Object b, Object c, Object d, String currentDirectory) {
        String cacheCopy = AppPaths.appPath("cache/1");
        String cacheMove = AppPaths.appPath("cache/2");
        String cacheName = AppPaths.appPath("cache/3");
        String cacheCurrent = AppPaths.appPath("cache/00");
        String cacheSize = AppPaths.appPath("cache/41");
        try {
            if (Objects.equals(action, "返回")) {
                runtime.cdx(owner, 0, null, null, null);
            } else if (Objects.equals(action, "删除")) {
                Intent intent = new Intent(owner, DeleteConfirmActivity.class);
                intent.putExtra("b", b == null ? null : String.valueOf(b));
                intent.putExtra("c", c == null ? null : String.valueOf(c));
                intent.putExtra("d", d == null ? null : String.valueOf(d));
                intent.putExtra("es", currentDirectory);
                onMain(() -> {
                    owner.startActivity(intent);
                    mainHandler.postDelayed(() -> runtime.cdx(owner, 0, null, null, null), 500L);
                });
            } else if (Objects.equals(action, "刷新")) {
                runtime.sx(owner, stringValue(c), currentDirectory);
                runtime.cdx(owner, 0, null, null, null);
            } else if (Objects.equals(action, "复制")) {
                NativeFiles.delete(owner, cacheCopy, true);
                NativeFiles.writeText(owner, cacheCopy, stringValue(d));
                hint("提示", "已复制,长按粘贴");
                runtime.cdx(owner, 0, null, null, null);
            } else if (Objects.equals(action, "剪切")) {
                NativeFiles.delete(owner, cacheMove, true);
                NativeFiles.writeText(owner, cacheMove, stringValue(d));
                hint("提示", "已剪切,长按粘贴");
                runtime.cdx(owner, 0, null, null, null);
            } else if (Objects.equals(action, "粘贴到当前目录")) {
                if (NativeFiles.exists(owner, cacheCopy)) {
                    paste(cacheCopy, false, cacheCurrent, currentDirectory);
                } else if (NativeFiles.exists(owner, cacheMove)) {
                    paste(cacheMove, true, cacheCurrent, currentDirectory);
                }
                runtime.sx(owner, stringValue(c), currentDirectory);
                runtime.cdx(owner, 0, null, null, null);
            } else if (Objects.equals(action, "取消粘贴")) {
                NativeFiles.delete(owner, cacheCopy, true);
                NativeFiles.delete(owner, cacheMove, true);
                hint("提示", "已取消");
                runtime.cdx(owner, 0, null, null, null);
            } else if (Objects.equals(action, "重命名") || Objects.equals(action, "新建目录")
                    || Objects.equals(action, "新建文件")) {
                NativeFiles.writeText(owner, cacheName, String.valueOf(action));
                runtime.cdx(owner, 4, stringValue(c), stringValue(d), currentDirectory);
            } else if (Objects.equals(action, "打开方式")) {
                runtime.cdx(owner, 6, stringValue(c), stringValue(d), currentDirectory);
            } else if (Objects.equals(action, "属性")) {
                if (NativeFiles.type(owner, stringValue(d)) == 2) {
                    NativeFiles.writeText(owner, cacheSize, runtime.hqwjdx(d));
                }
                runtime.cdx(owner, 5, stringValue(c), stringValue(d), currentDirectory);
            }
        } catch (Throwable failure) {
            AppRuntimeBridge.reportError(owner, failure, "c4.*");
        }
    }

    private void paste(String cachePath, boolean move, String cacheCurrent, String currentDirectory) {
        String source = NativeFiles.readText(owner, cachePath);
        String root = NativeFiles.readText(owner, cacheCurrent);
        if (source == null) return;

        String[] info = runtime.fileinfo(root, source);
        String name = info != null && info.length > 3 && Objects.equals(info[0], "true")
                ? info[3] : new File(NativeFiles.resolvePath(owner, source)).getName();
        String target = join(currentDirectory, name);
        if (isProtectedTarget(target)) {
            hint("提示", "非法操作");
            return;
        }
        if (Objects.equals(source, currentDirectory)) {
            hint("提示", "粘贴失败\n请选择其他目录");
            return;
        }

        NativeFiles.delete(owner, cachePath, true);
        boolean succeeded = move
                ? NativeFiles.move(owner, source, target, true)
                : NativeFiles.copy(owner, source, target, true);
        hint("提示", succeeded ? "粘贴成功" : "粘贴失败");
    }

    private boolean isProtectedTarget(String path) {
        String[] protectedPaths = {
                AppPaths.appPath("settings"), AppPaths.appPath("data"), AppPaths.appPath("fix"),
                AppPaths.appPath("cache"), AppPaths.appPath("files/cache")
        };
        for (String protectedPath : protectedPaths) {
            if (TextValues.startsWith(path, protectedPath)) return true;
        }
        return Build.VERSION.SDK_INT > 29
                && (TextValues.startsWith(path, "/storage/emulated/0/Android/data")
                || TextValues.startsWith(path, "/storage/emulated/0/Android/obb"));
    }

    private String textAt(int id) {
        View value = owner.findViewById(id);
        return value instanceof TextView ? ((TextView) value).getText().toString() : "";
    }

    private void hint(Object title, Object message) {
        onMain(() -> runtime.tsk(owner, title, message));
    }

    private void onMain(Runnable action) {
        if (Looper.myLooper() == Looper.getMainLooper()) action.run();
        else mainHandler.post(action);
    }

    private boolean onLongClick(View view) {
        hint("详细信息", NativeListAdapter.valueForView(view, R.id.wb1));
        return true;
    }

    private static String stringValue(Object value) {
        return String.valueOf(value);
    }

    private static String join(String directory, String name) {
        if (directory == null || directory.isEmpty()) return name;
        return directory.endsWith("/") || directory.endsWith("\\") ? directory + name : directory + "/" + name;
    }
}
