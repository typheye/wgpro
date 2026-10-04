package open.cn.awg.pro;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import open.cn.awg.pro.core.NativeViewValues;
import open.cn.awg.pro.R;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public final class NativeViewValuesColorResourceTest {
    @Test
    public void colorResourceStringPreservesAlphaAndArgbOrder() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("#6432618d", NativeViewValues.colorResourceString(context, R.color.colorTabTh));
        assertEquals("#01000000", NativeViewValues.colorResourceString(context, R.color.colorThoughTrue));
    }
}
