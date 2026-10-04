package open.cn.awg.pro.ui.list;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Map;

import open.cn.awg.pro.R;
import open.cn.awg.pro.store.StoreDetailActivity;

/** Native click and detail behavior for music-store rows. */
public final class StoreMusicRowBinder implements NativeListAdapter.RowBinder {
    private final Activity owner;

    public StoreMusicRowBinder(@NonNull Activity owner) {
        this.owner = owner;
    }

    @Override
    public void bind(@NonNull View itemView, @NonNull Map<Integer, Object> row, int position) {
        View root = itemView.findViewById(R.id.xdbj1);
        root.setOnClickListener(this::openDetails);
        root.setOnLongClickListener(this::showDetails);
    }

    private void openDetails(View view) {
        Object id = NativeListAdapter.valueForView(view, -1);
        Intent intent = new Intent(owner, StoreDetailActivity.class);
        intent.putExtra("uri", "awg://uri/music/" + id);
        owner.startActivity(intent);
    }

    private boolean showDetails(View view) {
        Object name = NativeListAdapter.valueForView(view, R.id.wb1);
        Object description = NativeListAdapter.valueForView(view, R.id.wb3);
        new android.app.AlertDialog.Builder(owner)
                .setTitle("详细信息")
                .setMessage("[歌曲名称]\n" + name + "\n\n[歌曲简介]\n" + description)
                .setPositiveButton(android.R.string.ok, null)
                .show();
        return true;
    }
}
