package open.cn.awg.pro;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.app.Instrumentation;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.SystemClock;
import android.widget.ImageView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import open.cn.awg.pro.core.NativeViewValues;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RunWith(AndroidJUnit4.class)
public final class NativeImageDependenciesTest {

    @Test
    public void systemDrawableLoadsThroughNativeImageApi() {
        android.app.Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        instrumentation.runOnMainSync(() -> {
            Context targetContext = instrumentation.getTargetContext();
            ImageView imageView = new ImageView(targetContext);
            NativeViewValues.image(imageView, android.R.drawable.ic_dialog_info);
            assertNotNull(imageView.getDrawable());
        });
    }

    @Test
    public void localJpegLoadsThroughNativeImageApi() throws IOException {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        Context targetContext = instrumentation.getTargetContext();
        File fixture = File.createTempFile("native-image-deps-", ".jpg", targetContext.getCacheDir());
        try {
            Bitmap bitmap = Bitmap.createBitmap(2, 2, Bitmap.Config.ARGB_8888);
            try {
                bitmap.eraseColor(Color.MAGENTA);
                try (FileOutputStream output = new FileOutputStream(fixture)) {
                    assertTrue("JPEG fixture should be encoded",
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, output));
                }

                ImageView[] imageView = new ImageView[1];
                instrumentation.runOnMainSync(() -> {
                    imageView[0] = new ImageView(targetContext);
                    NativeViewValues.image(imageView[0], Uri.fromFile(fixture));
                });

                Drawable drawable = null;
                long deadline = SystemClock.uptimeMillis() + 5000L;
                while (drawable == null && SystemClock.uptimeMillis() < deadline) {
                    instrumentation.waitForIdleSync();
                    Drawable[] currentDrawable = new Drawable[1];
                    instrumentation.runOnMainSync(() -> currentDrawable[0] = imageView[0].getDrawable());
                    drawable = currentDrawable[0];
                    if (drawable == null) SystemClock.sleep(50L);
                }
                assertNotNull("Picasso should load the local JPEG", drawable);
            } finally {
                bitmap.recycle();
            }
        } finally {
            fixture.delete();
        }
    }
}
