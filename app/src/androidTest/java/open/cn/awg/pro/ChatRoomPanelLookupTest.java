package open.cn.awg.pro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import open.cn.awg.pro.R;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public final class ChatRoomPanelLookupTest {

    @Test
    public void conversationPanelLookupSurvivesRoomMenuVisibilityToggles() {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        Context themedContext = new ContextThemeWrapper(targetContext, R.style.AppBaseTheme);

        InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
            View root = LayoutInflater.from(themedContext).inflate(R.layout.chat_room, null, false);
            LinearLayout conversationPanel = root.findViewById(R.id.i2xxbj1);
            LinearLayout menuPanel = root.findViewById(R.id.xxbjcd);
            RecyclerView messageList = root.findViewById(R.id.ltsv7lb1);

            assertNotNull(conversationPanel);
            assertNotNull(menuPanel);
            assertNotNull(messageList);
            assertSame(conversationPanel, messageList.getParent());
            assertEquals(View.VISIBLE, conversationPanel.getVisibility());

            // Match the two screen states changed by the existing room-menu handlers.
            conversationPanel.setVisibility(View.GONE);
            menuPanel.setVisibility(View.VISIBLE);
            assertEquals(View.GONE, conversationPanel.getVisibility());
            assertEquals(View.VISIBLE, menuPanel.getVisibility());

            conversationPanel.setVisibility(View.VISIBLE);
            menuPanel.setVisibility(View.GONE);
            assertEquals(View.VISIBLE, conversationPanel.getVisibility());
            assertEquals(View.GONE, menuPanel.getVisibility());
        });
    }
}
