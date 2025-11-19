package com.typheye.wgpro.ui.function;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoderFactory;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.DefaultDecoderFactory;
import com.typheye.wgpro.R;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScanQRActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 1001;
    private DecoratedBarcodeView barcodeScannerView;
    private FloatingActionButton fabBack, fabFlashlight;
    private boolean isFlashlightEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ✅ 检查是否处于分屏/小窗模式
        if (isInMultiWindowMode()) {
            Toast.makeText(this, "扫码功能在分屏模式下不可用，请退出分屏后重试", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        setContentView(R.layout.activity_scanqr);

        // 全屏设置（API 30+）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowInsetsControllerCompat controller = new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView());
            controller.hide(WindowInsetsCompat.Type.statusBars() | WindowInsetsCompat.Type.navigationBars());
            controller.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
            getWindow().setDecorFitsSystemWindows(false);
            getWindow().getAttributes().layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        } else {
            // 旧版本兼容
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            );
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                getWindow().getAttributes().layoutInDisplayCutoutMode =
                        WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            }
        }

        initViews();
        setupClickListeners();
        requestCameraPermission();

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                onBack();
            }
        });
    }

    private void initViews() {
        barcodeScannerView = findViewById(R.id.barcode_scanner);

        // ✅ 去除提示文字
        barcodeScannerView.setStatusText("");

        fabBack = findViewById(R.id.fab_back);
        fabFlashlight = findViewById(R.id.fab_flashlight);
    }

    private void setupClickListeners() {
        fabBack.setOnClickListener(v -> onBack());
        // 闪光灯控制
        fabFlashlight.setOnClickListener(v -> {
            isFlashlightEnabled = !isFlashlightEnabled;
            barcodeScannerView.getBarcodeView().setTorch(isFlashlightEnabled);
            fabFlashlight.setImageResource(isFlashlightEnabled ?
                    R.drawable.ic_flash_on_white_24dp : R.drawable.ic_flash_off_white_24dp);
        });
    }

    private void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
        } else {
            startScanning();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startScanning();
            } else {
                Toast.makeText(this, "相机权限被拒绝，无法扫码", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    private void startScanning() {
        EnumSet<BarcodeFormat> decodeFormats = EnumSet.of(BarcodeFormat.QR_CODE);

        Map<DecodeHintType, Object> baseHints = new HashMap<>();

        DecoderFactory decoderFactory = new DefaultDecoderFactory(
                decodeFormats,
                baseHints,
                null,
                false
        );

        barcodeScannerView.getBarcodeView().setDecoderFactory(decoderFactory);

        BarcodeCallback callback = new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                if (result.getText() != null) {
                    barcodeScannerView.pause();
                    handleQrCodeResult(result.getText());
                }
            }

            @Override
            public void possibleResultPoints(List<com.google.zxing.ResultPoint> resultPoints) {
                // 可选处理
            }
        };

        barcodeScannerView.decodeContinuous(callback);
        barcodeScannerView.resume();
    }

    private void handleQrCodeResult(String qrContent) {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("URL", qrContent);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // ✅ 再次检查是否进入分屏模式（可选增强体验）
        if (isInMultiWindowMode()) {
            Toast.makeText(this, "已退出分屏模式后才能使用扫码", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            barcodeScannerView.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeScannerView.pause();
    }

    public void onBack() {
        finish();
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        // ✅ 实时响应分屏模式切换
        if (isInMultiWindowMode) {
            Toast.makeText(this, "扫码功能在分屏模式下不可用，请退出分屏后重试", Toast.LENGTH_LONG).show();
            finish();
        }
    }
}