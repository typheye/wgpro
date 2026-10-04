package open.cn.awg.pro.ui.list;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.Map;
import java.util.Objects;

import open.cn.awg.pro.R;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.GlobalRuntimeState;
import open.cn.awg.pro.core.NativeArchives;
import open.cn.awg.pro.core.NativeFiles;
import open.cn.awg.pro.core.TextValues;
import open.cn.awg.pro.file.FileOpenActivity;
import open.cn.awg.pro.installer.ApkPreviewActivity;
import open.cn.awg.pro.installer.PackageInstallerActivity;
import open.cn.awg.pro.media.VideoPlayerActivity;
import open.cn.awg.pro.media.WearMusicActivity;
import open.cn.awg.pro.tools.AccessibilityScriptActivity;
import open.cn.awg.pro.tools.ShellExecutorActivity;
import open.cn.awg.pro.viewer.ImageViewerActivity;
import open.cn.awg.pro.viewer.TextEditorActivity;
import open.cn.awg.pro.viewer.TextViewerActivity;

/** Native open-option row styling and file-viewer routing. */
public final class FileOpenOptionRowBinder implements NativeListAdapter.RowBinder {
    private final Activity owner;
    private final AppRuntimeBridge runtime;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    public FileOpenOptionRowBinder(@NonNull Activity owner) {
        this.owner = owner;
        this.runtime = new AppRuntimeBridge(owner);
    }

    @Override
    public void bind(@NonNull View itemView, @NonNull Map<Integer, Object> row, int position) {
        int role = TextValues.intValue(row.get(-1), -1);
        TextView label = itemView.findViewById(R.id.wb1);
        int color = role == 1 ? R.color.colorTextFalse
                : role == -2 ? R.color.colorTextTrue : R.color.colorTextU1;
        label.setTextColor(ContextCompat.getColor(owner, color));
        label.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);

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
        String mode = stringValue(NativeListAdapter.valueForView(view, R.id.wb2));
        String path = stringValue(NativeListAdapter.valueForView(view, R.id.wb3));
        String currentDirectory = textAt(R.id.zywblj);
        new Thread(() -> route(mode, path, currentDirectory), "file-open-option").start();
    }

    private void route(String mode, String path, String currentDirectory) {
        try {
            if (Objects.equals(mode, "-1")) {
                cdx(0, null, null, null);
                return;
            }
            if (Objects.equals(mode, "0")) {
                cdx(6, null, path, null);
                return;
            }
            if (mode.length() == 1 && "abcdefg".contains(mode)) {
                onMain(() -> runtime.dkfs(owner, mode, path, 0));
                return;
            }

            if (Objects.equals(mode, "a1")) {
                open(TextViewerActivity.class, "url", path);
            } else if (Objects.equals(mode, "a2")) {
                open(TextEditorActivity.class, "url", path);
            } else if (Objects.equals(mode, "b1")) {
                open(ImageViewerActivity.class, "url", path);
            } else if (Objects.equals(mode, "c1")) {
                open(WearMusicActivity.class, "url", path);
            } else if (Objects.equals(mode, "c2") || Objects.equals(mode, "d2")) {
                open(VideoPlayerActivity.class, "url", path);
            } else if (Objects.equals(mode, "c3")) {
                launchExternalMedia("/HankMi/cache/music/keydata.hmd", "com.hankmi.wearmusic", path);
            } else if (Objects.equals(mode, "d3")) {
                String[] info = runtime.fileinfo(GlobalRuntimeState.d1_rootpath, path);
                String title = info != null && info.length > 3 ? info[3] : "";
                onMain(() -> {
                    try {
                        if (!com.demo.e4.wearvideo(owner, path, title)) {
                            runtime.tsk(owner, "提示", "打开异常");
                        }
                    } catch (Throwable failure) {
                        runtime.tsk(owner, "提示", "打开异常\n" + failure);
                    }
                });
            } else if (Objects.equals(mode, "d4")) {
                launchExternalMedia("/HankMi/cache/media/keydata.hmd", "com.hankmi.media", path);
            } else if (Objects.equals(mode, "e1")) {
                String[] info = runtime.fileinfo(GlobalRuntimeState.d1_rootpath, path);
                String parent = info != null && info.length > 2 ? info[2] : "";
                onMain(() -> {
                    TextView title = owner.findViewById(R.id.title_bar);
                    if (title != null) title.setText("提示");
                    View progress = owner.findViewById(R.id.xxbj9);
                    if (progress != null) progress.setVisibility(View.VISIBLE);
                });
                int extracted = NativeArchives.extract(owner, path, parent, true);
                hint("提示", extracted > 0 ? "解压成功" : "解压失败");
                runtime.sx(owner, path, currentDirectory);
            } else if (Objects.equals(mode, "f1")) {
                open(ApkPreviewActivity.class, "url", path);
            } else if (Objects.equals(mode, "f2") || Objects.equals(mode, "f3")) {
                Intent intent = new Intent(owner, PackageInstallerActivity.class);
                intent.putExtra("url", path);
                intent.putExtra("mode", Objects.equals(mode, "f2") ? "0" : "1");
                start(intent);
            } else if (Objects.equals(mode, "g1")) {
                open(FileOpenActivity.class, "url", path);
            } else if (Objects.equals(mode, "g2")) {
                hint("提示", "腕管Pro已不再支持应用主题文件");
            } else if (Objects.equals(mode, "g3") || Objects.equals(mode, "g4")) {
                if (NativeFiles.size(owner, path) > 102400) {
                    hint("提示", "文件过大,无法打开");
                } else {
                    String contents = NativeFiles.readText(owner, path);
                    if (contents == null) {
                        hint("提示", "打开异常");
                    } else if (Objects.equals(mode, "g3")) {
                        open(AccessibilityScriptActivity.class, "cmd", contents);
                    } else {
                        open(ShellExecutorActivity.class, "sh", contents);
                    }
                }
            } else if (Objects.equals(mode, "g5")) {
                hint("提示", "请前往 应用设置>关于应用>应用备份 页面恢复应用备份");
            } else if (mode.matches("h[1-4]")) {
                NativeFiles.delete(owner, AppPaths.appPath("cache/file/choose"), true);
                NativeFiles.writeText(owner, AppPaths.appPath("cache/chat/upload/path"), path);
                hideKeyboard();
            } else if (Objects.equals(mode, "i1")) {
                NativeFiles.delete(owner, AppPaths.appPath("cache/file/choose2"), true);
                NativeFiles.writeText(owner, AppPaths.appPath("cache/user/upload/path"), path);
                hideKeyboard();
            } else {
                hint("提示", "未找到该打开方式");
            }
            cdx(0, null, null, null);
        } catch (Throwable failure) {
            AppRuntimeBridge.reportError(owner, failure, "c6.*");
        }
    }

    private void launchExternalMedia(String cachePath, String packageName, String path) {
        String target = NativeFiles.resolvePath(owner, "%") + cachePath;
        if (!NativeFiles.writeText(owner, target, "hmmedia=" + path)) {
            hint("提示", "打开异常");
            return;
        }
        onMain(() -> {
            try {
                Intent intent = owner.getPackageManager().getLaunchIntentForPackage(packageName);
                if (intent == null) throw new IllegalStateException("应用未安装");
                owner.startActivity(intent);
            } catch (Throwable failure) {
                runtime.tsk(owner, "提示", "打开异常\n" + failure);
            }
        });
    }

    private void open(Class<?> activity, String key, String value) {
        Intent intent = new Intent(owner, activity);
        intent.putExtra(key, value);
        start(intent);
    }

    private void start(Intent intent) {
        onMain(() -> owner.startActivity(intent));
    }

    private void cdx(int operation, String a, String b, String c) {
        runtime.cdx(owner, operation, a, b, c);
    }

    private void hint(Object title, Object message) {
        onMain(() -> runtime.tsk(owner, title, message));
    }

    private void hideKeyboard() {
        onMain(() -> {
            View focus = owner.getCurrentFocus();
            if (focus != null) {
                android.view.inputmethod.InputMethodManager keyboard =
                        (android.view.inputmethod.InputMethodManager) owner.getSystemService(Activity.INPUT_METHOD_SERVICE);
                if (keyboard != null) keyboard.hideSoftInputFromWindow(focus.getWindowToken(), 0);
            }
        });
    }

    private String textAt(int id) {
        View value = owner.findViewById(id);
        return value instanceof TextView ? ((TextView) value).getText().toString() : "";
    }

    private void onMain(Runnable action) {
        if (Looper.myLooper() == Looper.getMainLooper()) action.run();
        else mainHandler.post(action);
    }

    private boolean onLongClick(View view) {
        Object label = NativeListAdapter.valueForView(view, R.id.wb1);
        hint("详细信息", label);
        return true;
    }

    private static String stringValue(Object value) {
        return String.valueOf(value);
    }
}
