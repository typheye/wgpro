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
import open.cn.awg.pro.core.TextValues;
import open.cn.awg.pro.viewer.TextViewerActivity;

/** Native open and long-press detail behavior for announcement rows. */
public final class AnnouncementRowBinder implements NativeListAdapter.RowBinder {
    private final Activity owner;

    public AnnouncementRowBinder(@NonNull Activity owner) {
        this.owner = owner;
    }

    @Override
    public void bind(@NonNull View itemView, @NonNull Map<Integer, Object> row, int position) {
        View root = itemView.findViewById(R.id.xdbj1);
        root.setOnClickListener(this::openAnnouncement);
        root.setOnLongClickListener(this::showDetails);
    }

    private void openAnnouncement(View view) {
        Object title = NativeListAdapter.valueForView(view, R.id.wb1);
        Object data = NativeListAdapter.valueForView(view, R.id.wb2);
        String cache = AppPaths.appPath("data/notice/cache");
        NativeFiles.writeText(owner, cache, String.valueOf(data));
        Intent intent = new Intent(owner, TextViewerActivity.class);
        intent.putExtra("url", cache);
        intent.putExtra("title", String.valueOf(title));
        intent.putExtra("code", "super1");
        owner.startActivity(intent);
    }

    private boolean showDetails(View view) {
        Object title = NativeListAdapter.valueForView(view, R.id.wb1);
        Object metadata = NativeListAdapter.valueForView(view, R.id.wb3);
        String publisher = TextValues.slice(metadata, " | ", " 发布");
        String date = TextValues.slice(metadata, null, " | ");
        new AppRuntimeBridge(owner).tsk(owner, "详细信息",
                "[公告标题]\n" + title + "\n\n[发布人员]\n" + publisher + "\n\n[发布时间]\n" + date);
        return true;
    }
}
