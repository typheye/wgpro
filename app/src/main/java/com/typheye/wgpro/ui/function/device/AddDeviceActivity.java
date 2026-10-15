package com.typheye.wgpro.ui.function.device;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.typheye.wgpro.R;
import com.typheye.wgpro.utils.AppBarBlur;
import com.typheye.wgpro.utils.AppUtils;

/**
 * 添加设备（**统一入口**）：按 {@link #EXTRA_MODE} 挂不同 Fragment。
 *
 * <ul>
 *   <li>{@link #MODE_WEARABLE} → {@link DeviceDiscoverFragment}：附近设备（扫描/发现真机）</li>
 *   <li>{@link #MODE_VIRTUAL} → {@link VirtualDeviceListFragment}：模拟设备机型列表</li>
 * </ul>
 *
 * <p>应用栏毛玻璃在这里统一装：{@code content} 直接给"承载 Fragment 的容器"，
 * {@link AppBarBlur#install} 会递归给里面第一个滚动视图补顶部留白、并把下拉刷新圈压到应用栏下方。</p>
 */
public class AddDeviceActivity extends AppCompatActivity {

    public static final String EXTRA_MODE = "mode";
    public static final String MODE_WEARABLE = "wearable";
    public static final String MODE_VIRTUAL = "virtual";

    private View overlay;
    private boolean virtualMode;
    /**
     * SAF 选镜像包：用 {@link ActivityResultContracts.GetContent}（ACTION_GET_CONTENT + type=*&#47;*）。
     *
     * <p>不要用 {@code OpenDocument}：它会把类型塞进 {@code EXTRA_MIME_TYPES} 数组，MIUI 的
     * DocumentsUI 对数组处理不对（哪怕只放 {@code "*&#47;*"} 也会把文件全过滤掉，
     * 表现就是"什么都选不了 / 无任何文件"）。{@code GetContent} 只设 intent type，
     * 全 ROM 都正常（App 里选头像用的就是它）。</p>
     */
    private ActivityResultLauncher<String> importLauncher;
    private com.typheye.wgpro.ui.widget.WGProBottomSheetDialog importDialog;

    @Override
    protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        AppUtils.useScreenCutArea(getWindow(), this);
        setContentView(R.layout.activity_add_device);

        overlay = findViewById(R.id.add_device_loading_overlay);
        importLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(), uri -> {
                    if (uri != null) {
                        importLocalPackage(uri);
                    }
                });
        final boolean virtual = MODE_VIRTUAL.equals(modeOf(getIntent()));
        virtualMode = virtual;
        Toolbar toolbar = findViewById(R.id.add_device_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        toolbar.setTitle(virtual ? "创建设备" : "添加设备");

        AppBarBlur.install(this, findViewById(R.id.app_bar_layout),
                findViewById(R.id.add_device_fragment_host));

        if (state == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.add_device_fragment_host,
                            virtual ? new VirtualDeviceListFragment() : new DeviceDiscoverFragment(),
                            "add-device-content")
                    .commit();
        }
    }

    @NonNull
    private static String modeOf(@Nullable Intent intent) {
        String value = intent == null ? null : intent.getStringExtra(EXTRA_MODE);
        return value == null ? MODE_WEARABLE : value;
    }

    /** 加载遮罩的动画时长（同时是它的**最长停留**）：到点无论加载是否结束都直接隐藏。 */
    private static final long OVERLAY_ANIM_MS = 300L;
    /** 自动隐藏后「触发一次重新加载」是否已经用过（只触发一次，避免 加载→遮罩→到点→再加载 死循环）。 */
    private boolean autoReloadUsed;
    private final Runnable overlayTimeout = () -> hidePageOverlay(true);

    /** 页面级加载遮罩：进页面/首次拉取/扫描期间由 Fragment 调起。 */
    public void showPageOverlay() {
        if (overlay == null) return;
        overlay.removeCallbacks(overlayTimeout);
        overlay.animate().cancel();
        overlay.setAlpha(0f);
        overlay.setVisibility(View.VISIBLE);
        overlay.animate().alpha(1f).setDuration(OVERLAY_ANIM_MS).start();
        // 300ms 到点直接收起：某些路径（扫描无结果、请求超时）没人调 hidePageOverlay，
        // 遮罩会一直盖着 → 表现为"卡死"。到点后由列表自己重新加载。
        overlay.postDelayed(overlayTimeout, OVERLAY_ANIM_MS);
    }

    public void hidePageOverlay() {
        hidePageOverlay(false);
    }

    private void hidePageOverlay(boolean autoHidden) {
        if (overlay == null) return;
        overlay.removeCallbacks(overlayTimeout);
        overlay.animate().cancel();
        overlay.animate().alpha(0f).setDuration(OVERLAY_ANIM_MS).withEndAction(() -> {
            overlay.setVisibility(View.GONE);
            overlay.setAlpha(1f);
            if (autoHidden && !autoReloadUsed) {
                autoReloadUsed = true;
                requestFragmentReload();
            }
        }).start();
    }

    /** 遮罩到点收起后，让当前 Fragment 自己重新加载（等价于一次下拉刷新）。 */
    private void requestFragmentReload() {
        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.add_device_fragment_host);
        if (fragment instanceof VirtualDeviceListFragment) {
            ((VirtualDeviceListFragment) fragment).reloadModels();
        } else if (fragment instanceof DeviceDiscoverFragment) {
            ((DeviceDiscoverFragment) fragment).restartScan();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_device_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_import_image);
        if (item != null) item.setVisible(virtualMode);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_import_image) {
            confirmLocalImport();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** 导入前先确认：底部弹窗（大标题 + 说明 + 整宽按钮，且必须留「取消」）。 */
    private void confirmLocalImport() {
        new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(this)
                .setTitle("从本地导入镜像")
                .setMessage("选一个本地的 vela 镜像包（.zip），导入后用它创建一台模拟设备。\n\n"
                        + "注意：包要先放到手机存储里；解包需要几分钟，请保证存储空间充足。")
                .setNegativeButton("取消", null)
                .setPositiveButton("选择文件", (dialog, which) -> importLauncher.launch("*/*"))
                .show();
    }

    /** 「从本地导入」的目标机型：机型列表里当前第一个。 */
    @Nullable
    private VelaModel currentModel() {
        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.add_device_fragment_host);
        if (fragment instanceof VirtualDeviceListFragment) {
            java.util.List<VelaModel> models =
                    ((VirtualDeviceListFragment) fragment).loadedModels();
            if (!models.isEmpty()) return models.get(0);
        }
        return null;
    }

    /**
     * 从本地导入镜像包（SAF 选中的 zip）：先落到该机型的包路径，再复用
     * {@link com.typheye.wgpro.core.emu.VelaProvision#provision} —— 它本来就是按
     * {@code engine/qemu/linux-aarch64} 与 {@code images/} 这套结构解包并写设备库的。
     */
    private void importLocalPackage(@NonNull final Uri uri) {
        final VelaModel model = currentModel();
        if (model == null || model.id.isEmpty()) {
            com.typheye.wgpro.ui.widget.WGProHint.showError(this, "机型还没加载完，稍后再试");
            return;
        }
        View progressView = getLayoutInflater().inflate(R.layout.progress_dialog, null, false);
        ((TextView) progressView.findViewById(android.R.id.message)).setText("正在导入模拟器…");
        importDialog = new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(this)
                .setTitle("正在导入")
                .setView(progressView)
                .setCancelable(false)
                .setNegativeButton("取消", null)
                .create();
        importDialog.show();
        new Thread(() -> {
            String failure = null;
            String deviceId = null;
            com.typheye.wgpro.core.emu.VelaEmulator unpacked = null;
            try {
                java.io.File zip = com.typheye.wgpro.core.emu.VelaProvision
                        .packageFile(this, model.id);
                java.io.File parent = zip.getParentFile();
                if (parent != null) parent.mkdirs();
                try (java.io.InputStream in = getContentResolver().openInputStream(uri);
                     java.io.OutputStream out = new java.io.FileOutputStream(zip)) {
                    if (in == null) throw new java.io.IOException("无法读取所选文件");
                    byte[] buffer = new byte[1 << 16];
                    int read;
                    while ((read = in.read(buffer)) > 0) out.write(buffer, 0, read);
                }
                // 解压（engine/ + images/），**不建库** —— 建哪种设备由用户选
                deviceId = com.typheye.wgpro.core.emu.VelaEmulatorManager.deviceId(model.id)
                        + ":" + com.typheye.wgpro.core.emu.VelaProvision.randomSuffix();
                unpacked = com.typheye.wgpro.core.emu.VelaProvision
                        .unpackRuntime(this, model.id, deviceId);
            } catch (Throwable error) {
                failure = error.getMessage() == null ? "导入失败" : error.getMessage();
            }
            final String result = failure;
            final String newDeviceId = deviceId;
            final com.typheye.wgpro.core.emu.VelaEmulator emulator = unpacked;
            runOnUiThread(() -> {
                if (importDialog != null) {
                    importDialog.dismiss();
                    importDialog = null;
                }
                if (result != null) {
                    com.typheye.wgpro.ui.widget.WGProHint.showError(this, "导入失败：" + result);
                    return;
                }
                // 解压完成 → 弹「创建哪种模拟器」（包内皮肤按 手表/手环/音箱 分组）
                askDeviceType(newDeviceId, emulator);
            });
        }, "vela-import").start();
    }

    /** 解压完成后的"创建哪种模拟器"选择：列包内 engine/skins 的皮肤。 */
    private void askDeviceType(@NonNull final String deviceId,
                               @NonNull final com.typheye.wgpro.core.emu.VelaEmulator emulator) {
        // 设备清单以包内 config.json 为准（代号/名称/皮肤），老包没有清单时退回扫 skins 目录
        java.util.List<String[]> manifest = com.typheye.wgpro.core.emu.VelaProvision
                .manifestDevices(emulator.runtimeDir());
        final java.util.List<String> skins = new java.util.ArrayList<>();
        final java.util.List<String> names = new java.util.ArrayList<>();
        CharSequence[] labels;
        if (!manifest.isEmpty()) {
            for (String[] device : manifest) {
                skins.add(device[2]);
                names.add(device[1]);
            }
        } else {
            for (String skin : com.typheye.wgpro.core.emu.VelaEmulator
                    .availableSkins(emulator.engineDir())) {
                skins.add(skin);
                names.add(com.typheye.wgpro.core.emu.VelaEmulator.skinLabel(skin));
            }
        }
        if (skins.isEmpty()) {
            skins.add(emulator.skin());
            names.add(com.typheye.wgpro.core.emu.VelaEmulator.skinLabel(emulator.skin()));
        }
        labels = names.toArray(new CharSequence[0]);
        new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(this)
                .setTitle("创建哪种模拟器")
                .setItems(labels, (dialog, which) -> createWithSkin(deviceId, emulator,
                        skins.get(which), names.get(which)))
                .setNegativeButton("取消", null)
                .show();
    }

    /** 按选定皮肤写 AVD + 落库；完成后回到设备页。 */
    private void createWithSkin(@NonNull final String deviceId,
                                @NonNull final com.typheye.wgpro.core.emu.VelaEmulator emulator,
                                @NonNull final String skin, @NonNull final String deviceName) {
        View progressView = getLayoutInflater().inflate(R.layout.progress_dialog, null, false);
        ((TextView) progressView.findViewById(android.R.id.message)).setText("正在创建模拟器…");
        final com.typheye.wgpro.ui.widget.WGProBottomSheetDialog[] ref =
                new com.typheye.wgpro.ui.widget.WGProBottomSheetDialog[1];
        ref[0] = new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(this)
                .setTitle("正在创建")
                .setView(progressView)
                .setCancelable(false)
                .setNegativeButton("取消", null)
                .create();
        ref[0].show();
        new Thread(() -> {
            String failure = null;
            try {
                // 显示名用清单里的设备名（如 "Xiaomi 手环 10 Pro"），不再用"设备名称 XXXX"
                com.typheye.wgpro.core.emu.VelaProvision
                        .createDevice(this, emulator, deviceId, skin,
                                deviceName + " " + com.typheye.wgpro.core.emu.VelaProvision
                                        .randomSuffix());
            } catch (Throwable error) {
                failure = error.getMessage() == null ? "创建失败" : error.getMessage();
            }
            final String result = failure;
            runOnUiThread(() -> {
                if (ref[0] != null) {
                    ref[0].dismiss();
                }
                if (result != null) {
                    com.typheye.wgpro.ui.widget.WGProHint.showError(this, "创建失败：" + result);
                    return;
                }
                com.typheye.wgpro.ui.widget.WGProHint.show(this, "导入完成，已创建模拟设备");
                setResult(RESULT_OK);
                finish();
            });
        }, "vela-create").start();
    }
}
