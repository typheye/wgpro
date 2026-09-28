package com.typheye.wgpro.ui.function.debug;

import android.content.Intent;
import android.widget.Toast;

import com.typheye.wgpro.ui.SplashActivity;
import com.typheye.wgpro.ui.function.PreviewActivity;
import com.typheye.wgpro.ui.function.ScanQRActivity;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.ui.function.account.AccMangerActivity;
import com.typheye.wgpro.ui.function.account.UserDetailActivity;
import com.typheye.wgpro.ui.function.community.AccountListActivity;
import com.typheye.wgpro.ui.function.community.AppDetailActivity;
import com.typheye.wgpro.ui.function.community.ChatActivity;
import com.typheye.wgpro.ui.function.community.CloudListFragment;
import com.typheye.wgpro.ui.function.community.ContactActivity;
import com.typheye.wgpro.ui.function.community.DynamicDetailActivity;
import com.typheye.wgpro.ui.function.community.NotificationActivity;
import com.typheye.wgpro.ui.function.community.ResourceDetailActivity;
import com.typheye.wgpro.ui.function.device.AddDeviceActivity;
import com.typheye.wgpro.ui.function.settings.SettingsActivity;
import com.typheye.wgpro.ui.function.settings.UpdateRequiredActivity;
import com.typheye.wgpro.ui.main.MainActivity;
import com.typheye.wgpro.ui.oobe.OobeActivity;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 调试页「启动 Activity」：列出应用里存在的所有 Activity，逐个以「最小传参」启动，
 * 保证每个页面都能顺利加载（内容页给空/占位参数，走各自的空态或错误态）。
 *
 * <p>有意排除：
 * <ul>
 *     <li>{@code UpdateRequiredActivity}（强制更新拦截页，返回键无效，会卡住；单独放在末尾并二次确认）；</li>
 *     <li>{@code com.journeyapps.barcodescanner.CaptureActivity}（第三方扫码内部页，正常入口是 ScanQRActivity）。</li>
 * </ul>
 */
public final class DebugActivityListFragment extends DebugListFragment {

    @Override protected List<Row> buildRows() {
        List<Row> rows = new ArrayList<>();
        launch(rows, "MainActivity（主界面）", "底部导航 · 首页 / 设备 / 账户", new Intent(requireContext(), MainActivity.class));
        launch(rows, "SplashActivity（启动页）", "启动页 → 主界面", new Intent(requireContext(), SplashActivity.class));
        launch(rows, "SettingsActivity（设置）", "设置与子页面", new Intent(requireContext(), SettingsActivity.class));
        launch(rows, "AccMangerActivity（账户管理）", "编辑资料", new Intent(requireContext(), AccMangerActivity.class));
        launch(rows, "TestActivity（调试）", "本页", new Intent(requireContext(), TestActivity.class));
        launch(rows, "CrashActivity（异常）", "崩溃日志页", new Intent(requireContext(), CrashActivity.class));
        launch(rows, "ScanQRActivity（扫一扫）", "需要相机权限", new Intent(requireContext(), ScanQRActivity.class));
        launch(rows, "AddDeviceActivity（添加设备）", "扫描附近设备", new Intent(requireContext(), AddDeviceActivity.class));
        launch(rows, "NotificationActivity（消息通知）", "通知中心", new Intent(requireContext(), NotificationActivity.class));
        launch(rows, "ContactActivity（联系人）", "粉丝列表", new Intent(requireContext(), ContactActivity.class)
                .putExtra(ContactActivity.EXTRA_MODE, CloudListFragment.MODE_FOLLOWERS)
                .putExtra(ContactActivity.EXTRA_EXPECTED_COUNT, -1));
        launch(rows, "ChatActivity（私信）", "peer_uid=10000（官方）", new Intent(requireContext(), ChatActivity.class)
                .putExtra(ChatActivity.EXTRA_PEER_UID, "10000")
                .putExtra(ChatActivity.EXTRA_PEER_NAME, "Typheye 助手"));
        launch(rows, "UserDetailActivity（用户主页）", "target_uid=10000（官方）", new Intent(requireContext(), UserDetailActivity.class)
                .putExtra(UserDetailActivity.EXTRA_TARGET_UID, "10000"));
        launch(rows, "AccountListActivity（浏览历史/收藏）", "我的收藏 · 动态", new Intent(requireContext(), AccountListActivity.class)
                .putExtra(AccountListActivity.EXTRA_MODE, CloudListFragment.MODE_COLLECTION_DYNAMIC)
                .putExtra(AccountListActivity.EXTRA_TITLE, "我的收藏"));
        launch(rows, "DynamicDetailActivity（动态详情）", "空 id → 走失效态", new Intent(requireContext(), DynamicDetailActivity.class)
                .putExtra(DynamicDetailActivity.EXTRA_DYNAMIC_ID, ""));
        launch(rows, "AppDetailActivity（应用详情）", "空 JSON → 走空态", new Intent(requireContext(), AppDetailActivity.class)
                .putExtra(AppDetailActivity.EXTRA_APP_JSON, "{}"));
        launch(rows, "ResourceDetailActivity（资源详情）", "空 JSON → 走空态", new Intent(requireContext(), ResourceDetailActivity.class)
                .putExtra(ResourceDetailActivity.EXTRA_RESOURCE_JSON, "{}"));
        launch(rows, "WebActivity（内置浏览器）", "打开用户中心网页", new Intent(requireContext(), WebActivity.class)
                .putExtra("URL", "https://service.typheye.cn/site/user/center/"));
        add(rows, "PreviewActivity（图片预览）", "预览一张图片", () -> {
            try {
                PreviewActivity.open(requireContext(), "https://typheye.cn/favicon.ico");
            } catch (Exception e) {
                toast("启动失败：" + e);
            }
        });
        launch(rows, "OobeActivity（开机引导）", "新手引导流程", new Intent(requireContext(), OobeActivity.class));

        add(rows, "UpdateRequiredActivity（强制更新）", "⚠ 返回键无效，只能杀进程退出", () ->
                new WGProAlertDialogBuilder(requireContext()).setTitle("强制更新页")
                        .setMessage("该页面会拦截返回键，打开后只能通过杀进程退出。确定要打开吗？")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("打开", (d, w) -> start(new Intent(requireContext(), UpdateRequiredActivity.class)))
                        .show());
        return rows;
    }

    private void launch(List<Row> rows, CharSequence title, CharSequence subtitle, Intent intent) {
        add(rows, title, subtitle, () -> start(intent));
    }

    private void start(Intent intent) {
        try {
            requireContext().startActivity(intent);
        } catch (Exception e) {
            toast("启动失败：" + e);
        }
    }

    private void toast(String message) {
        if (isAdded()) Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
