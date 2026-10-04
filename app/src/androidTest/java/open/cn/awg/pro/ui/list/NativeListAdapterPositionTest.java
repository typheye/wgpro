package open.cn.awg.pro.ui.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import android.app.Instrumentation;
import android.content.Context;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import open.cn.awg.pro.R;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(AndroidJUnit4.class)
public class NativeListAdapterPositionTest {
    private final Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    private final Context targetContext = instrumentation.getTargetContext();

    @Test
    public void detachedHoldersResolveRowsByIdentityAfterRemovalAndReplacement() {
        Throwable[] failure = new Throwable[1];
        instrumentation.runOnMainSync(() -> {
            try {
                FrameLayout parent = new FrameLayout(targetContext);
                NativeListAdapter adapter = new NativeListAdapter(targetContext,
                        R.layout.experimental_feature_item, (itemView, row, position) -> { });
                Map<Integer, Object> firstInput = row("same");
                Map<Integer, Object> secondInput = row("same");
                assertEquals(firstInput, secondInput);
                assertNotSame(firstInput, secondInput);
                adapter.replaceRows(Arrays.asList(firstInput, secondInput));

                Map<Integer, Object> firstBoundRow = adapter.rowAt(0);
                Map<Integer, Object> secondBoundRow = adapter.rowAt(1);
                assertEquals(firstBoundRow, secondBoundRow);
                assertNotSame(firstBoundRow, secondBoundRow);

                NativeListAdapter.RowHolder firstHolder = adapter.onCreateViewHolder(parent, 0);
                NativeListAdapter.RowHolder secondHolder = adapter.onCreateViewHolder(parent, 0);
                adapter.onBindViewHolder(firstHolder, 0);
                adapter.onBindViewHolder(secondHolder, 1);
                assertEquals(RecyclerView.NO_POSITION, firstHolder.getBindingAdapterPosition());
                assertEquals(RecyclerView.NO_POSITION, secondHolder.getBindingAdapterPosition());
                assertEquals(0, NativeListAdapter.positionForView(firstHolder.itemView));
                assertEquals(1, NativeListAdapter.positionForView(secondHolder.itemView));

                assertTrue(adapter.removeRow(0));
                assertEquals(RecyclerView.NO_POSITION,
                        NativeListAdapter.positionForView(firstHolder.itemView));
                assertNull(NativeListAdapter.rowForView(firstHolder.itemView));
                assertEquals(0, NativeListAdapter.positionForView(secondHolder.itemView));
                assertSame(secondBoundRow, NativeListAdapter.rowForView(secondHolder.itemView));

                Map<Integer, Object> replacement = row("same");
                assertTrue(adapter.replaceRow(0, replacement));
                assertEquals(replacement, adapter.rowAt(0));
                assertNotSame(secondBoundRow, adapter.rowAt(0));
                assertEquals(RecyclerView.NO_POSITION,
                        NativeListAdapter.positionForView(secondHolder.itemView));
                assertNull(NativeListAdapter.rowForView(secondHolder.itemView));
            } catch (Throwable error) {
                failure[0] = error;
            }
        });
        if (failure[0] != null) {
            throw new AssertionError("Detached row holder identity assertion failed", failure[0]);
        }
    }

    private static Map<Integer, Object> row(String value) {
        Map<Integer, Object> row = new HashMap<>();
        row.put(-41, value);
        return row;
    }
}

