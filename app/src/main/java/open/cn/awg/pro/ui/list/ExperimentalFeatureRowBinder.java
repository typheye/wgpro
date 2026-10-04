package open.cn.awg.pro.ui.list;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.Map;
import java.util.Objects;

import open.cn.awg.pro.R;
import open.cn.awg.pro.apps.InstalledAppListActivity;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.experimental.ServerDebugActivity;
import open.cn.awg.pro.tools.FingerprintActivity;
import open.cn.awg.pro.tools.ShellExecutorActivity;

/** Native navigation and detail behavior for experimental-feature rows. */
public final class ExperimentalFeatureRowBinder implements NativeListAdapter.RowBinder {
    private final Activity owner;

    public ExperimentalFeatureRowBinder(@NonNull Activity owner) {
        this.owner = owner;
    }

    @Override
    public void bind(@NonNull View itemView, @NonNull Map<Integer, Object> row, int position) {
        View root = itemView.findViewById(R.id.experimental_feature_item_root);
        root.setOnClickListener(this::openFeature);
        root.setOnLongClickListener(this::showDetails);
    }

    private void openFeature(View view) {
        String feature = String.valueOf(NativeListAdapter.valueForView(view, -1));
        Class<?> target;
        if (Objects.equals(feature, "l5_a1_fuwuq_d0313_y2022")) {
            target = ServerDebugActivity.class;
        } else if (Objects.equals(feature, "j2")) {
            target = FingerprintActivity.class;
        } else if (Objects.equals(feature, "l6")) {
            target = InstalledAppListActivity.class;
        } else if (Objects.equals(feature, "j4")) {
            Intent intent = new Intent(owner, ShellExecutorActivity.class);
            intent.putExtra("szk", "true");
            owner.startActivity(intent);
            return;
        } else {
            new AppRuntimeBridge(owner).tsk(owner, "提示", "该功能不存在");
            return;
        }
        owner.startActivity(new Intent(owner, target));
    }

    private boolean showDetails(View view) {
        Object name = NativeListAdapter.valueForView(view, R.id.wb1);
        Object description = NativeListAdapter.valueForView(view, R.id.wb2);
        Object feature = NativeListAdapter.valueForView(view, -1);
        new AppRuntimeBridge(owner).tsk_QR(owner, "详细信息",
                "[功能名称]\n" + name + "\n\n[功能简介]\n" + description + "\n\n[功能标识]",
                feature);
        return true;
    }
}
