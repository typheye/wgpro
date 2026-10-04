package open.cn.awg.pro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import open.cn.awg.pro.R;
import open.cn.awg.pro.plugin.ExtensionPluginConfigBridge;
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public final class NativePluginConfigLayoutTest {

    @Test
    public void pluginConfigBuildersCreateNativeViewsWithoutRunningActions() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        Context targetContext = instrumentation.getTargetContext();
        Intent intent = new Intent(targetContext, TextQrDisplayActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("title", "Plugin layout fixture");
        intent.putExtra("text", "Local UI fixture; no QR code or network request.");

        Activity host = instrumentation.startActivitySync(intent);
        try {
            instrumentation.runOnMainSync(() -> {
                host.setContentView(R.layout.extension_plugin_detail);
                ExtensionPluginConfigBridge bridge = new ExtensionPluginConfigBridge(host);

                bridge.m20000();
                assertBuiltScreen(host, "m20000");

                bridge.m20001();
                assertBuiltScreen(host, "m20001");
                assertCaptchaRow(host);

                bridge.m20002();
                assertBuiltScreen(host, "m20002");

                bridge.m20003();
                assertBuiltScreen(host, "m20003");

                bridge.m20004();
                assertBuiltScreen(host, "m20004");
                assertCaptchaRow(host);

                bridge.m20005();
                assertBuiltScreen(host, "m20005");

                bridge.m20006();
                assertBuiltScreen(host, "m20006");

                bridge.m20007();
                assertBuiltScreen(host, "m20007");

                bridge.csh();
                assertBuiltScreen(host, "csh");

                bridge.csh_e();
                assertBuiltScreen(host, "csh_e");
            });
        } finally {
            instrumentation.runOnMainSync(host::finish);
        }
    }

    private static void assertBuiltScreen(Activity host, String method) {
        RelativeLayout root = host.findViewById(R.id.plugin_runtime_view);
        assertNotNull(method + " runtime root", root);
        assertEquals(method + " must show its runtime root", View.VISIBLE, root.getVisibility());
        assertTrue(method + " must add dynamic content", root.getChildCount() > 0);
        assertNotNull(method + " must add a button", findButton(root));
    }

    private static void assertCaptchaRow(Activity host) {
        RelativeLayout root = host.findViewById(R.id.plugin_runtime_view);
        EditText code = findEditText(root, "请输入验证码");
        assertNotNull("verification-code EditText", code);
        assertTrue("verification-code row must be LinearLayout", code.getParent() instanceof LinearLayout);

        LinearLayout row = (LinearLayout) code.getParent();
        assertEquals("verification-code controls must be horizontal", LinearLayout.HORIZONTAL, row.getOrientation());
        Button getCode = findButton(root, "获取");
        assertNotNull("get-code button", getCode);
        assertSame("get-code button must share the horizontal row", row, getCode.getParent());

        assertTrue("verification-code dimensions must remain LinearLayout params",
                code.getLayoutParams() instanceof LinearLayout.LayoutParams);
        LinearLayout.LayoutParams codeParams = (LinearLayout.LayoutParams) code.getLayoutParams();
        assertEquals("verification-code width", ViewGroup.LayoutParams.MATCH_PARENT, codeParams.width);
        assertEquals("verification-code height", ViewGroup.LayoutParams.WRAP_CONTENT, codeParams.height);
        assertEquals("verification-code weight", 1f, codeParams.weight, 0f);

        assertTrue("get-code button dimensions must remain LinearLayout params",
                getCode.getLayoutParams() instanceof LinearLayout.LayoutParams);
        LinearLayout.LayoutParams buttonParams = (LinearLayout.LayoutParams) getCode.getLayoutParams();
        assertEquals("get-code button width", ViewGroup.LayoutParams.WRAP_CONTENT, buttonParams.width);
        assertEquals("get-code button height", ViewGroup.LayoutParams.WRAP_CONTENT, buttonParams.height);
        int threeDp = Math.round(3 * getCode.getResources().getDisplayMetrics().density);
        assertEquals("get-code button start margin", threeDp, buttonParams.leftMargin);
    }

    private static EditText findEditText(View view, String hint) {
        if (view instanceof EditText && hint.contentEquals(((EditText) view).getHint())) {
            return (EditText) view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int index = 0; index < group.getChildCount(); index++) {
                EditText found = findEditText(group.getChildAt(index), hint);
                if (found != null) return found;
            }
        }
        return null;
    }

    private static Button findButton(View view) {
        if (view instanceof Button) return (Button) view;
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int index = 0; index < group.getChildCount(); index++) {
                Button found = findButton(group.getChildAt(index));
                if (found != null) return found;
            }
        }
        return null;
    }

    private static Button findButton(View view, String text) {
        if (view instanceof Button && text.contentEquals(((Button) view).getText())) {
            return (Button) view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int index = 0; index < group.getChildCount(); index++) {
                Button found = findButton(group.getChildAt(index), text);
                if (found != null) return found;
            }
        }
        return null;
    }
}
