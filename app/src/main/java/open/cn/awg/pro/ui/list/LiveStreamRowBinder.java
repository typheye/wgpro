package open.cn.awg.pro.ui.list;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Map;

import open.cn.awg.pro.R;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.TextValues;
import open.cn.awg.pro.live.LiveChannelDetailActivity;

/** Native live-channel navigation and detail behavior. */
public final class LiveStreamRowBinder implements NativeListAdapter.RowBinder {
    private final Activity owner;

    public LiveStreamRowBinder(@NonNull Activity owner) {
        this.owner = owner;
    }

    @Override
    public void bind(@NonNull View itemView, @NonNull Map<Integer, Object> row, int position) {
        View root = itemView.findViewById(R.id.xdbj1);
        root.setOnClickListener(this::openChannel);
        root.setOnLongClickListener(this::showDetails);
    }

    private void openChannel(View view) {
        Object title = NativeListAdapter.valueForView(view, R.id.wb1);
        Object text = NativeListAdapter.valueForView(view, R.id.wb3);
        Object url = NativeListAdapter.valueForView(view, R.id.wb2);
        Object additional = NativeListAdapter.valueForView(view, -2);
        Intent intent = new Intent(owner, LiveChannelDetailActivity.class);
        if ("".equals(additional)) {
            intent.putExtra("urls", "");
            intent.putExtra("title", String.valueOf(title));
            intent.putExtra("text", String.valueOf(text));
            intent.putExtra("url", String.valueOf(url));
        } else {
            String urls = TextValues.slice(additional, "[", "]");
            urls = TextValues.replace(urls, "\\/", "/", false);
            urls = TextValues.replace(urls, "\"", "", false);
            intent.putExtra("urls", String.valueOf(text) + "," + urls);
            intent.putExtra("title", String.valueOf(title));
            intent.putExtra("text", String.valueOf(text));
        }
        owner.startActivity(intent);
    }

    private boolean showDetails(View view) {
        Object title = NativeListAdapter.valueForView(view, R.id.wb1);
        Object description = NativeListAdapter.valueForView(view, R.id.wb3);
        new AppRuntimeBridge(owner).tsk(owner, "详细信息",
                "[直播标题]\n" + title + "\n\n[直播简介]\n" + description);
        return true;
    }
}
