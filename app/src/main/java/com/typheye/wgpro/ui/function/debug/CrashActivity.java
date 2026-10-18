package com.typheye.wgpro.ui.function.debug;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
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
        // 固定应用栏改毛玻璃：快照源用不滚动的容器（crash_content_host），滚动在内部 NestedScrollView 上。
        com.typheye.wgpro.utils.AppBarBlur.installWithScrollContent(this,
                findViewById(R.id.app_bar_layout), findViewById(R.id.crash_content_host),
                findViewById(R.id.crash_content));

        tvCrashLog = findViewById(R.id.tv_crash_log);
        android.view.View btnRestart = findViewById(R.id.btn_restart);
        android.view.View btnReport = findViewById(R.id.btn_report);

        // 加载崩溃日志
        loadCrashLog();

        // 两个按钮是页面底部的常驻操作（样式与底部弹窗的双按钮一致）
        btnRestart.setOnClickListener(v -> restartApp());
        btnReport.setOnClickListener(v -> reportCrash());
        setupActionsBar();
    }



    /** 上报结果：底部弹窗告知，只留「重启应用」且不可取消。 */
    private void showResultSheet(String title, String message) {
        new WGProAlertDialogBuilder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("重启应用", (dialog, which) -> restartApp())
                .setCancelable(false)
                .show();
    }


    /**
     * 底部操作栏：毛玻璃快照 + 导航栏内边距 + 滚动区底部留白。
     * 结构与私信聊天页输入栏一致（固定底部、surface_bar 底色、快照层随栏高）。
     */
    private void setupActionsBar() {
        final android.view.View bar = findViewById(R.id.crash_actions_bar);
        final android.view.View blur = findViewById(R.id.crash_actions_blur);
        final android.view.View photoSource = findViewById(R.id.crash_content_host);
        if (bar == null) return;
        com.typheye.wgpro.utils.BarBlurController.registerBar(this, bar);
        final com.typheye.wgpro.utils.BarBlurController[] controller =
                new com.typheye.wgpro.utils.BarBlurController[1];
        if (blur instanceof android.widget.ImageView && photoSource != null) {
            controller[0] = com.typheye.wgpro.utils.BarBlurController.install(this, photoSource,
                    (android.widget.ImageView) blur);
        }
        final int baseBottom = bar.getPaddingBottom();
        androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.container), (view, insets) -> {
                    // 只需要导航栏高度：直接用 navigationBars()，避免系统栏集合里
                    // 混入其它 insets 把栏位顶得过高（与聊天输入栏观感对齐）
                    int bottom = insets.getInsets(
                            androidx.core.view.WindowInsetsCompat.Type.navigationBars()).bottom;
                    bar.setPadding(bar.getPaddingLeft(), bar.getPaddingTop(),
                            bar.getPaddingRight(), baseBottom + bottom);
                    return insets;
                });
        final android.view.View scroll = findViewById(R.id.crash_content);
        bar.post(() -> {
            if (scroll != null) {
                // 内容画到操作栏下面（毛玻璃取景），底部留出操作栏高度，最后一行仍可滚出来
                scroll.setPadding(scroll.getPaddingLeft(), scroll.getPaddingTop(),
                        scroll.getPaddingRight(), bar.getHeight());
            }
            // 静态页面没有滚动事件，必须主动要一张快照，否则毛玻璃层一直是空的
            if (controller[0] != null) controller[0].scheduleUpdate();
            android.graphics.drawable.Drawable bg = bar.getBackground();
            android.util.Log.i("WGProBlur", "bar bg=" + (bg == null ? "null"
                    : (bg instanceof android.graphics.drawable.ColorDrawable
                    ? String.format("#%08X", ((android.graphics.drawable.ColorDrawable) bg).getColor())
                    : bg.getClass().getSimpleName()))
                    + " blurVis=" + blur.getVisibility());
        });
        bar.postDelayed(() -> {
            if (controller[0] != null) controller[0].scheduleUpdate();
        }, 300L);
    }

    /** 手动上报当前崩溃日志：底部弹窗圆形进度（可取消）+ 真实上传。 */
    private void reportCrash() {
        final CharSequence text = tvCrashLog == null ? "" : tvCrashLog.getText();
        final String log = text == null ? "" : text.toString();
        if (log.trim().isEmpty()) {
            showResultSheet("没有可上报的日志", "当前没有读取到崩溃日志内容。");
            return;
        }
        com.typheye.wgpro.ui.widget.WGProProgressRunner.run(this, "异常上报",
                "正在上传崩溃日志，请稍候…",
                completion -> {
                    boolean ok = com.typheye.wgpro.utils.CrashReporter.reportRawBlocking(
                            this, "崩溃日志（手动上报）", log, 8000L);
                    if (ok) {
                        completion.success(new org.json.JSONObject());
                    } else {
                        completion.error(0, "网络异常或服务端未接收，请稍后重试");
                    }
                },
                new com.typheye.wgpro.ui.widget.WGProProgressRunner.Callback() {
                    @Override public void success(@NonNull org.json.JSONObject json) {
                        showResultSheet("上报成功", "感谢反馈，我们已经收到这条崩溃日志。")
                        ;
                    }

                    @Override public void error(int code, @NonNull String message) {
                        showResultSheet("上报失败", message);
                    }
                });
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
