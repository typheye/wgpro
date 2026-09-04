package com.typheye.wgpro.ui.oobe;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.oobe.oobeFragments.OobeFinishFragment;
import com.typheye.wgpro.ui.oobe.oobeFragments.OobePoliciesFragment;
import com.typheye.wgpro.ui.oobe.oobeFragments.OobeWelcomeFragment;
import com.typheye.wgpro.utils.AppUtils;

public class OobeActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 1001;
    private static final String CHANNEL_ID = "account_channel";

    Fragment fragment_oobeWelcomeFragment;
    Fragment fragment_oobeFinishFragment;
    Fragment fragment_oobePoliciesFragment;
    Fragment fragment_now;
    private OnBackPressedCallback compatibilityBackCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.useScreenCutArea(getWindow(),this);
        setContentView(R.layout.activity_oobe);
        AppUtils.fixScreenCutArea(findViewById(R.id.container));

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        fragment_oobeWelcomeFragment = new OobeWelcomeFragment();
        fragment_oobeFinishFragment = new OobeFinishFragment();
        fragment_oobePoliciesFragment = new OobePoliciesFragment();

        // 获取传入的参数
        Intent intent = getIntent();
        String target = intent.getStringExtra("TARGET_FRAGMENT");

        // ====== 关键修复：创建通知渠道 (仅需执行一次) ======
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
        }

        if (savedInstanceState == null) {
            switchFragment(target);
            onCreateFragment();
        }

        // 在启动服务前请求通知权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, NOTIFICATION_ID);
            }
        }

        // 返回键处理
        compatibilityBackCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
            }
        };
        getOnBackPressedDispatcher().addCallback(this, compatibilityBackCallback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        compatibilityBackCallback.setEnabled(!PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("predictive_back_enabled", false));
    }

    public void onCreateFragment(){

        boolean p1 = false;
        boolean p2 = false;

        if (fragment_now == fragment_oobeWelcomeFragment){
            if (p1 || p2) {
                //noinspection StatementWithEmptyBody
                if (!p1) {}
                else if (!p2)
                    fragment_now = fragment_oobePoliciesFragment;
            }
        }

        // 替换 Fragment
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.animator.fragment_enter, R.animator.fragment_exit)
                .replace(R.id.fragment_container, fragment_now)
                .commit();
    }


    public void switchFragment(String target){

        // 根据参数决定加载哪个 Fragment
        if (target != null) {
            switch (target){

                case "finish":
                    fragment_now = fragment_oobeFinishFragment;
                    break;

                case "policies":
                    fragment_now = fragment_oobePoliciesFragment;
                    break;

                case "welcome":
                default:
                    fragment_now = fragment_oobeWelcomeFragment;
                    break;
            }
        } else {
            fragment_now = fragment_oobeWelcomeFragment;
        }

        // 替换 Fragment
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.animator.fragment_enter, R.animator.fragment_exit)
                .replace(R.id.fragment_container, fragment_now)
                .commit();
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
