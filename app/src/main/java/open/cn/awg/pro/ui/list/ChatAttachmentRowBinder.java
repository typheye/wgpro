package open.cn.awg.pro.ui.list;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Map;
import java.util.Objects;

import open.cn.awg.pro.R;
import open.cn.awg.pro.chat.ChatMessageDetailActivity;
import open.cn.awg.pro.chat.WearChatRuntimeBridge;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.TextValues;
import open.cn.awg.pro.download.AppDownloadActivity;
import open.cn.awg.pro.media.VideoPlayerActivity;
import open.cn.awg.pro.viewer.ImageViewerActivity;

/** Native attachment, author, and message-detail behavior for chat rows. */
public final class ChatAttachmentRowBinder implements NativeListAdapter.RowBinder {
    private final Activity owner;

    public ChatAttachmentRowBinder(@NonNull Activity owner) {
        this.owner = owner;
    }

    @Override
    public void bind(@NonNull View itemView, @NonNull Map<Integer, Object> row, int position) {
        itemView.findViewById(R.id.tx1).setOnLongClickListener(this::markAuthorRecent);
        itemView.findViewById(R.id.xxbj4k).setOnClickListener(this::openAttachment);
        itemView.findViewById(R.id.xxbj4k).setOnLongClickListener(this::markAuthorRecent);
        itemView.findViewById(R.id.xdbj1).setOnLongClickListener(this::showMessageDetails);
        itemView.findViewById(R.id.xxbjfj).setOnClickListener(this::openAttachment);
        itemView.findViewById(R.id.wb10).setOnClickListener(this::showMessageText);
    }

    private boolean markAuthorRecent(View view) {
        Object authorId = NativeListAdapter.valueForView(view, R.id.wb2);
        new WearChatRuntimeBridge(owner).ltspostat(owner, authorId);
        owner.findViewById(R.id.kp7).setVisibility(View.GONE);
        return true;
    }

    private void openAttachment(View view) {
        String type = text(NativeListAdapter.valueForView(view, R.id.wb8));
        String url = text(NativeListAdapter.valueForView(view, R.id.wb9));
        boolean image = Objects.equals(type, "[附件]图片");
        boolean media = Objects.equals(type, "[附件]音频") || Objects.equals(type, "[附件]视频");
        boolean file = Objects.equals(type, "[附件]文件");
        if (!image && !media && !file) {
            new AppRuntimeBridge(owner).tsk(owner, "提示", "未知类型附件");
            return;
        }
        if (url.isEmpty()) {
            showLinkError();
            return;
        }

        try {
            String[] parts = TextValues.split(url, "/");
            String filename = parts.length == 0 ? "" : parts[parts.length - 1];
            if (filename.isEmpty()) {
                showLinkError();
            } else if (image) {
                Intent intent = new Intent(owner, ImageViewerActivity.class);
                intent.putExtra("url", url);
                intent.putExtra("md5", filename);
                owner.startActivity(intent);
            } else if (media) {
                startWithUrl(VideoPlayerActivity.class, url);
            } else if (file) {
                startWithUrl(AppDownloadActivity.class, url);
            }
        } catch (Throwable failure) {
            AppRuntimeBridge.reportError(owner, failure, "c11.*");
        }
    }

    private boolean showMessageDetails(View view) {
        Intent intent = new Intent(owner, ChatMessageDetailActivity.class);
        intent.putExtra("name", text(NativeListAdapter.valueForView(view, R.id.wb2)));
        intent.putExtra("time", text(NativeListAdapter.valueForView(view, R.id.wb3)));
        intent.putExtra("data", text(NativeListAdapter.valueForView(view, R.id.wb1)));
        intent.putExtra("address", text(NativeListAdapter.valueForView(view, -2)));
        intent.putExtra("luid", text(NativeListAdapter.valueForView(view, -3)));
        owner.startActivity(intent);
        return true;
    }

    private void showMessageText(View view) {
        new AppRuntimeBridge(owner).tsk(owner, "消息详情",
                NativeListAdapter.valueForView(view, R.id.wb10));
    }

    private void showLinkError() {
        new AppRuntimeBridge(owner).tsk(owner, "提示", "链接指向错误");
    }

    private void startWithUrl(Class<?> target, String url) {
        Intent intent = new Intent(owner, target);
        intent.putExtra("url", url);
        owner.startActivity(intent);
    }

    private static String text(Object value) {
        return value == null ? "" : String.valueOf(value);
    }
}
