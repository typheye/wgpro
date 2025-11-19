// SplashActivity.java
package com.typheye.wgpro.ui; // 替换为你的包名

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.content.Intent;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.ui.main.MainActivity;
import com.typheye.wgpro.utils.AppUtils;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    private static final long SPLASH_DELAY = 800;
    private static final int NOTIFICATION_ID = 1001;
    private static final String CHANNEL_ID = "account_channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.useScreenCutArea(getWindow(),this);
        setContentView(R.layout.activity_splash);
        AppUtils.fixScreenCutArea(findViewById(R.id.container));

        // ====== 关键修复：创建通知渠道 (仅需执行一次) ======
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
        }

        // 在启动服务前请求通知权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, NOTIFICATION_ID);
            }
        }

        new Handler(Looper.getMainLooper()).postDelayed(this::goToMain, SPLASH_DELAY);
    }

    private void goToMain() {
        if (AppUtils.getAppInit(this)) {
            goMain();
        } else {
            // 创建对话框（关键：使用 MaterialAlertDialogBuilder 但自定义按钮行为）
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this)
                    .setTitle("欢迎")
                    .setMessage("哈喽，新用户！\n\n在使用应用之前，请您务必点击下方“查看条款”按钮，仔细阅读《Typheye服务条款与隐私政策》。\n\n如果您同意条款内容，点击下方“同意”按钮接受条款，即可使用应用提供的服务。\n若您不同意条款内容，本应用无法为您提供服务，您可以点击“离开”按钮以退出应用。")
                    .setPositiveButton("同意", (dialog, which) -> {
                        AppUtils.appInit(this);
                        goMain();
                    })
                    .setNegativeButton("离开", (dialog, which) -> finishAndRemoveTask())
                    .setCancelable(false); // 防止点击空白区域关闭

            // 关键：使用 setNeutralButton 但不关闭对话框
            builder.setNeutralButton("查看条款", (dialog, which) -> {
                // 1. 不关闭对话框（关键！）
                // 2. 启动WebActivity
                Intent intent = new Intent(this, WebActivity.class);
                intent.putExtra("URL", "https://www.typheye.cn/policies/");
                startActivity(intent);

            });

            // 创建对话框实例
            AlertDialog dialog = builder.create();

            // 关键：阻止对话框在点击"查看条款"时关闭
            dialog.setOnShowListener(dialogInterface -> {
                // 获取NeutralButton的View
                Button neutralButton = dialog.getButton(AlertDialog.BUTTON_NEUTRAL);
                if (neutralButton != null) {
                    neutralButton.setOnClickListener(v -> goPolicy());
                }
            });

            // 显示对话框
            dialog.show();
        }
    }

    void goMain(){
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    void goPolicy(){
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("URL", "https://www.typheye.cn/policies/");
        startActivity(intent);
    }

    // ====== 创建通知渠道的方法 ======
    private void createNotificationChannel() {
        // 1. 创建渠道名称和描述
        CharSequence name = "Typheye账户";
        String description = "与您账户相关的通知";
        int importance = NotificationManager.IMPORTANCE_HIGH; // 高优先级

        // 2. 创建渠道对象
        NotificationChannel channel = getNotificationChannel(name, importance, description);

        // 3. 注册渠道到系统
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (channel != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    @Nullable
    private static NotificationChannel getNotificationChannel(CharSequence name, int importance, String description) {
        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(CHANNEL_ID, name, importance);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel.setDescription(description);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel.setShowBadge(true); // 显示角标
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC); // 锁屏可见
        }
        return channel;
    }


}