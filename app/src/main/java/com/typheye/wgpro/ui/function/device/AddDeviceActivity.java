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
 *   <li>{@link #MODE_VIRTUAL} → {@link VirtualDeviceListFragment}：**旧**模拟设备机型列表
 *       （拉云端 {@code vela_devices}）。上一个内测版还在用，**保留不动**。</li>
 *   <li>{@link #MODE_CREATE} → {@link CreateDeviceFragment}：**新**创建设备页，
 *       列表保持为空、不依赖云端机型，设备定义来自「从本地导入」的包 {@code config.json}。</li>
 * </ul>
 *
 * <p>应用栏毛玻璃在这里统一装：{@code content} 直接给"承载 Fragment 的容器"，
 * {@link AppBarBlur#install} 会递归给里面第一个滚动视图补顶部留白、并把下拉刷新圈压到应用栏下方。</p>
 */
public class AddDeviceActivity extends AppCompatActivity {

    public static final String EXTRA_MODE = "mode";
    public static final String MODE_WEARABLE = "wearable";
    /** 旧：云端机型列表（内测版在用，勿删）。 */
    public static final String MODE_VIRTUAL = "virtual";
    /** 新：列表保持为空的创建设备页。 */
    public static final String MODE_CREATE = "create";

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

        importLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(), uri -> {
                    if (uri != null) {
                        importLocalPackage(uri);
                    }
                });
        final String mode = modeOf(getIntent());
        final boolean virtual = MODE_VIRTUAL.equals(mode);
        final boolean create = MODE_CREATE.equals(mode);
        // 两种"创建设备"都要显示「从本地导入」菜单
        virtualMode = virtual || create;
        Toolbar toolbar = findViewById(R.id.add_device_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        toolbar.setTitle((virtual || create) ? "创建设备" : "添加设备");

        AppBarBlur.install(this, findViewById(R.id.app_bar_layout),
                findViewById(R.id.add_device_fragment_host));

        if (state == null) {
            final Fragment content = create
                    ? new CreateDeviceFragment()
                    : (virtual ? new VirtualDeviceListFragment() : new DeviceDiscoverFragment());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.add_device_fragment_host, content, "add-device-content")
                    .commit();
        }
    }

    @NonNull
    private static String modeOf(@Nullable Intent intent) {
        String value = intent == null ? null : intent.getStringExtra(EXTRA_MODE);
        return value == null ? MODE_WEARABLE : value;
    }

    /*
     * 这里原来有一个"页面级加载遮罩"（showPageOverlay/hidePageOverlay + 300ms 到点自动收起 +
     * 收起后重载）。已**整体移除**：加载态一律用列表自己的下拉刷新指示器表达
     * （见 VirtualDeviceListFragment/DeviceDiscoverFragment 的 setRefreshing(true/false)）。
     * 遮罩会盖住应用栏与右上角菜单，而这些页并没有"必须挡住用户"的加载，属于多余。
     */

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
        Fragment host = getSupportFragmentManager()
                .findFragmentById(R.id.add_device_fragment_host);
        if (host instanceof CreateDeviceFragment) {
            // 新「创建设备」页不依赖云端机型：机型标识直接取所选包的文件名
            importLocalPackage(uri, modelIdFromUri(uri));
            return;
        }
        final VelaModel model = currentModel();
        if (model == null || model.id.isEmpty()) {
            com.typheye.wgpro.ui.widget.WGProHint.showError(this, "机型还没加载完，稍后再试");
            return;
        }
        importLocalPackage(uri, model.id);
    }

    /**
     * 所选镜像包的机型标识：取文件名（去扩展名）并做与运行目录同一套字符白名单转义。
     *
     * <p>新创建设备页没有云端机型可选，机型标识只能来自包本身 ——
     * 例如 {@code xiaomi-vela-v5.0.zip} → {@code xiaomi-vela-v5.0}。</p>
     */
    @NonNull
    private String modelIdFromUri(@NonNull final Uri uri) {
        String name = null;
        try (android.database.Cursor cursor = getContentResolver()
                .query(uri, null, null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndex(
                        android.provider.OpenableColumns.DISPLAY_NAME);
                if (index >= 0) {
                    name = cursor.getString(index);
                }
            }
        } catch (Exception ignored) {
        }
        if (name == null || name.trim().isEmpty()) {
            name = uri.getLastPathSegment();
        }
        if (name == null || name.trim().isEmpty()) {
            return "local-vela";
        }
        name = name.trim();
        int dot = name.lastIndexOf('.');
        if (dot > 0) {
            name = name.substring(0, dot);
        }
        String safe = name.replaceAll("[^A-Za-z0-9._-]", "_");
        return safe.isEmpty() ? "local-vela" : safe;
    }

    private void importLocalPackage(@NonNull final Uri uri, @NonNull final String modelId) {
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
                        .packageFile(this, modelId);
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
                deviceId = com.typheye.wgpro.core.emu.VelaEmulatorManager.deviceId(modelId)
                        + ":" + com.typheye.wgpro.core.emu.VelaProvision.randomSuffix();
                unpacked = com.typheye.wgpro.core.emu.VelaProvision
                        .unpackRuntime(this, modelId, deviceId);
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
