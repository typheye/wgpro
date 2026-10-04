package open.cn.awg.pro.ui.list;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Map;

import open.cn.awg.pro.R;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.music.TuneFreeMusicBridge;

/** Native song playback and detail behavior for TuneFree rows. */
public final class TuneFreeSongRowBinder implements NativeListAdapter.RowBinder {
    private final Activity owner;

    public TuneFreeSongRowBinder(@NonNull Activity owner) {
        this.owner = owner;
    }

    @Override
    public void bind(@NonNull View itemView, @NonNull Map<Integer, Object> row, int position) {
        View root = itemView.findViewById(R.id.xdbj1);
        root.setOnClickListener(this::playSong);
        root.setOnLongClickListener(this::showDetails);
    }

    private void playSong(View view) {
        Object name = NativeListAdapter.valueForView(view, R.id.wb1);
        Object artist = NativeListAdapter.valueForView(view, R.id.wb3);
        Object songId = NativeListAdapter.valueForView(view, R.id.wb2);
        new TuneFreeMusicBridge(owner).loadMusic(songId, name + "-" + artist);
    }

    private boolean showDetails(View view) {
        Object name = NativeListAdapter.valueForView(view, R.id.wb1);
        Object artist = NativeListAdapter.valueForView(view, R.id.wb3);
        Object songId = NativeListAdapter.valueForView(view, R.id.wb2);
        new AppRuntimeBridge(owner).tsk(owner, "详细信息",
                "[歌名]\n" + name + "\n\n[歌手]\n" + artist + "\n\n[歌曲ID]\n" + songId);
        return true;
    }
}
