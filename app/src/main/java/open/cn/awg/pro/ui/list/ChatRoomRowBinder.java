package open.cn.awg.pro.ui.list;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Map;

import open.cn.awg.pro.R;
import open.cn.awg.pro.chat.ChatRoomActivity;
import open.cn.awg.pro.chat.WearChatRuntimeBridge;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.NativeFiles;
import open.cn.awg.pro.data.LocalDatabaseBridge;

/** Native room navigation and recent-room cache behavior. */
public final class ChatRoomRowBinder implements NativeListAdapter.RowBinder {
    private final Activity owner;

    public ChatRoomRowBinder(@NonNull Activity owner) {
        this.owner = owner;
    }

    @Override
    public void bind(@NonNull View itemView, @NonNull Map<Integer, Object> row, int position) {
        View root = itemView.findViewById(R.id.chat_room_list_item_root);
        root.setOnClickListener(this::openRoom);
        root.setOnLongClickListener(this::markRoomRecent);
    }

    private void openRoom(View view) {
        Object id = NativeListAdapter.valueForView(view, R.id.wb2);
        Object title = NativeListAdapter.valueForView(view, R.id.wb1);
        LocalDatabaseBridge database = new LocalDatabaseBridge(owner);
        database.setup_talklist();
        if (database.findid_talklist(id) == 0) {
            new AppRuntimeBridge(owner).tsk(owner, "提示", "该聊天室不存在,请刷新列表后重试");
            return;
        }
        writeRoomCache("data/ltlb/lbx_id", id);
        writeRoomCache("data/ltlb/lbx_title", title);
        Intent intent = new Intent(owner, ChatRoomActivity.class);
        intent.putExtra("uid", String.valueOf(id));
        owner.startActivity(intent);
    }

    private boolean markRoomRecent(View view) {
        Object id = NativeListAdapter.valueForView(view, R.id.wb2);
        Object title = NativeListAdapter.valueForView(view, R.id.wb1);
        Object info = NativeListAdapter.valueForView(view, R.id.wb3);
        new WearChatRuntimeBridge(owner).wllbcd(owner, 2);
        writeRoomCache("data/ltlb/lbx_id", id);
        writeRoomCache("data/ltlb/lbx_title", title);
        writeRoomCache("data/ltlb/lbx_info", info);
        return true;
    }

    private void writeRoomCache(String path, Object value) {
        NativeFiles.writeText(owner, AppPaths.appPath(path), String.valueOf(value));
    }
}
