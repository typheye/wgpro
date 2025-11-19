package com.typheye.wgpro.ui.function.debug;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.typheye.wgpro.R;
import com.typheye.wgpro.tApplication;
import com.typheye.wgpro.ui.SplashActivity;
import com.typheye.wgpro.utils.AppUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CrashActivity extends AppCompatActivity {
    private TextView tvCrashLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.useScreenCutArea(getWindow(),this);
        setContentView(R.layout.activity_crash);
        AppUtils.fixScreenCutArea(findViewById(R.id.container));

        tvCrashLog = findViewById(R.id.tv_crash_log);
        MaterialButton btnRestart = findViewById(R.id.btn_restart);
        MaterialButton btnReport = findViewById(R.id.btn_report);

        // 加载崩溃日志
        loadCrashLog();

        // 设置重启按钮
        btnRestart.setOnClickListener(v -> restartApp());

        // 设置上报按钮
        btnReport.setOnClickListener(v -> new MaterialAlertDialogBuilder(this)
                .setTitle("异常上报")
                .setMessage("异常已记录，我们正在处理中")
                .setPositiveButton("确定", (dialog, which) -> Toast.makeText(this, "异常已上报", Toast.LENGTH_SHORT).show())
                .show());
    }

    @SuppressLint("SetTextI18n")
    private void loadCrashLog() {
        // 通过Application获取日志目录
        File logDir = tApplication.getInstance().getExternalLogDir();
        if (!logDir.exists()) {
            tvCrashLog.setText("无崩溃日志");
            return;
        }

        File[] files = logDir.listFiles((dir, name) -> name.startsWith("crash_"));
        if (files == null || files.length == 0) {
            tvCrashLog.setText("无崩溃日志");
            return;
        }

        // 获取最新日志文件
        File latestFile = files[0];
        for (File file : files) {
            if (file.lastModified() > latestFile.lastModified()) {
                latestFile = file;
            }
        }

        byte[] buffer = new byte[(int) latestFile.length()];
        try (FileInputStream fis = new FileInputStream(latestFile)) {
            int offset = 0;
            int bytesRead;
            while (offset < buffer.length && (bytesRead = fis.read(buffer, offset, buffer.length - offset)) != -1) {
                offset += bytesRead;
            }
            tvCrashLog.setText(new String(buffer, StandardCharsets.UTF_8));
        } catch (IOException e) {
            tvCrashLog.setText("读取日志失败: " + e.getMessage());
        }
    }

    private void restartApp() {
        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}