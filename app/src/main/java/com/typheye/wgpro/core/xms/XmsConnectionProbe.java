package com.typheye.wgpro.core.xms;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xiaomi.xms.wearable.node.DataItem;
import com.xiaomi.xms.wearable.node.Node;
import com.xiaomi.xms.wearable.node.NodeApi;

/** Confirms that an SDK node snapshot still reports a live connection. */
public final class XmsConnectionProbe {
    private XmsConnectionProbe() { }

    public interface Callback {
        void onResult(@Nullable Node node);
        void onError(@NonNull Exception error);
    }

    public static void probe(@NonNull NodeApi nodeApi, @NonNull Callback callback) {
        nodeApi.getConnectedNodes()
                .addOnSuccessListener(nodes -> {
                    if (nodes == null || nodes.isEmpty()) {
                        callback.onResult(null);
                        return;
                    }
                    Node node = nodes.get(0);
                    nodeApi.query(node.id, DataItem.ITEM_CONNECTION)
                            .addOnSuccessListener(result -> callback.onResult(
                                    result != null && result.isConnected ? node : null))
                            .addOnFailureListener(callback::onError);
                })
                .addOnFailureListener(callback::onError);
    }
}
