package open.cn.awg.pro;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.app.Instrumentation;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import open.cn.awg.pro.R;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public final class NativeLayoutInflationTest {
    private static final int EXPECTED_APP_LAYOUT_COUNT = 103;
    private static final int[] APP_LAYOUTS = {
            R.layout.about_app,
            R.layout.accessibility_script,
            R.layout.announcement,
            R.layout.announcement_item,
            R.layout.apk_preview,
            R.layout.app_action_item,
            R.layout.app_activation,
            R.layout.app_debug,
            R.layout.app_download,
            R.layout.app_update,
            R.layout.app_update_install,
            R.layout.chat_attachment_item,
            R.layout.chat_gpt,
            R.layout.chat_message_detail,
            R.layout.chat_room,
            R.layout.chat_room_list_item,
            R.layout.cloud_service,
            R.layout.crash_feedback,
            R.layout.deep_seek,
            R.layout.delete_confirm,
            R.layout.developer_test,
            R.layout.device_scan_placeholder,
            R.layout.dialog_content_before_install,
            R.layout.dialog_content_install,
            R.layout.dialog_content_install_status,
            R.layout.display_margin,
            R.layout.download_progress,
            R.layout.error_message,
            R.layout.experimental_feature_item,
            R.layout.experimental_features,
            R.layout.extension_plugin,
            R.layout.extension_plugin_detail,
            R.layout.extension_plugin_list_item,
            R.layout.extension_plugin_menu_item,
            R.layout.file_list_item,
            R.layout.file_manager,
            R.layout.file_manager_settings,
            R.layout.file_open,
            R.layout.file_open_option_item,
            R.layout.file_operation_info_item,
            R.layout.file_operation_menu_item,
            R.layout.file_root_item,
            R.layout.file_root_picker,
            R.layout.fingerprint,
            R.layout.image_viewer,
            R.layout.installed_app_item,
            R.layout.installed_app_list,
            R.layout.legacy_menu_item,
            R.layout.legal_info,
            R.layout.live_channel_detail,
            R.layout.live_stream,
            R.layout.live_stream_item,
            R.layout.location,
            R.layout.location_info,
            R.layout.log_cleanup,
            R.layout.main_activity,
            R.layout.main_menu_item,
            R.layout.media_browser_settings,
            R.layout.more_features,
            R.layout.network_optimize,
            R.layout.onboarding_setup,
            R.layout.online_id,
            R.layout.package_installer,
            R.layout.product_purchase,
            R.layout.recovery_options,
            R.layout.remote_control,
            R.layout.reset_app,
            R.layout.runtime_message_item,
            R.layout.server_debug,
            R.layout.settings,
            R.layout.shell_executor,
            R.layout.store_app_item,
            R.layout.store_detail,
            R.layout.store_list,
            R.layout.store_music_item,
            R.layout.store_search,
            R.layout.store_video_item,
            R.layout.system_permission,
            R.layout.system_tools,
            R.layout.terminal_compatibility,
            R.layout.text_editor,
            R.layout.text_qr_display,
            R.layout.text_translate,
            R.layout.text_viewer,
            R.layout.text_viewer_settings,
            R.layout.theme_settings,
            R.layout.third_party_authorization,
            R.layout.tune_free,
            R.layout.tune_free_cloud_search,
            R.layout.tune_free_song_item,
            R.layout.typheye_account,
            R.layout.typheye_login,
            R.layout.update_prompt,
            R.layout.video_player,
            R.layout.wear_chat,
            R.layout.wear_chat_agreement,
            R.layout.wear_chat_menu_item,
            R.layout.wear_chat_settings,
            R.layout.wear_music,
            R.layout.wear_os_toolbox,
            R.layout.x5_browser,
            R.layout.x5_browser_settings,
            R.layout.x5_core_install
    };

    @Test
    public void everyProjectLayoutInflatesWithAppBaseTheme() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        Context targetContext = instrumentation.getTargetContext();
        String expectedRound = InstrumentationRegistry.getArguments().getString("expectedScreenRound");
        if (expectedRound != null) {
            assertEquals("Actual Wear screen shape", Boolean.parseBoolean(expectedRound),
                    targetContext.getResources().getConfiguration().isScreenRound());
        }
        Context themedContext = new ContextThemeWrapper(targetContext, R.style.AppBaseTheme);
        assertEquals("Fixed app/src/main/res/layout inventory", EXPECTED_APP_LAYOUT_COUNT, APP_LAYOUTS.length);

        List<String> failures = new ArrayList<>();
        instrumentation.runOnMainSync(() -> {
            LayoutInflater inflater = LayoutInflater.from(themedContext);
            for (int layoutId : APP_LAYOUTS) {
                String name = targetContext.getResources().getResourceEntryName(layoutId);
                try {
                    View inflated = inflater.inflate(layoutId, null, false);
                    assertNotNull(inflated);
                } catch (Throwable failure) {
                    failures.add(name + ": " + failure);
                }
            }
        });

        assertTrue("Layout inflation failures: " + failures, failures.isEmpty());
    }
}
