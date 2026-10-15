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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.oobe.oobeFragments.OobeFinishFragment;
import com.typheye.wgpro.ui.oobe.oobeFragments.OobePoliciesFragment;
import com.typheye.wgpro.ui.oobe.oobeFragments.OobeWelcomeFragment;
import com.typheye.wgpro.ui.oobe.oobeFragments.OobeWhatsNewFragment;
import com.typheye.wgpro.utils.AppUtils;

public class OobeActivity extends AppCompatActivity {
    private static final int NOTIFICATION_ID = 1001;
    private static final String CHANNEL_ID = "account_channel";

    Fragment fragment_oobeWelcomeFragment;
    Fragment fragment_oobeFinishFragment;
    Fragment fragment_oobePoliciesFragment;
    Fragment fragment_oobeWhatsNewFragment;
    Fragment fragment_now;
    private OnBackPressedCallback compatibilityBackCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.useScreenCutArea(getWindow(),this);
        // 系统栏统一由 SystemBars 透明化；OOBE 页面自身是纯色背景，因此视觉上仍是纯色系统栏。
        setContentView(R.layout.activity_oobe);
        AppUtils.fixScreenCutArea(findViewById(R.id.container));
        // 金刚键适配：无应用栏的全屏页，三键 + 前台全屏时垫一条页面底色
        com.typheye.wgpro.utils.AppBarBlur.installNavBarSpacer(this,
                getColor(com.typheye.wgpro.R.color.surface_page),
                findViewById(R.id.fragment_container));

        fragment_oobeWelcomeFragment = new OobeWelcomeFragment();
        fragment_oobeFinishFragment = new OobeFinishFragment();
        fragment_oobePoliciesFragment = new OobePoliciesFragment();
        fragment_oobeWhatsNewFragment = new OobeWhatsNewFragment();

        // 获取传入的参数
        Intent intent = getIntent();
        String target = intent.getStringExtra("TARGET_FRAGMENT");

        // ====== 关键修复：创建通知渠道 (仅需执行一次) ======
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
        }

        if (savedInstanceState == null) {
            switchFragment(target);
        } else {
            Fragment restored = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            if (restored instanceof OobePoliciesFragment) {
                fragment_oobePoliciesFragment = restored;
            } else if (restored instanceof OobeWhatsNewFragment) {
                fragment_oobeWhatsNewFragment = restored;
            } else if (restored instanceof OobeFinishFragment) {
                fragment_oobeFinishFragment = restored;
            } else if (restored instanceof OobeWelcomeFragment) {
                fragment_oobeWelcomeFragment = restored;
            }
            fragment_now = restored;
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
                if (fragment_now == fragment_oobePoliciesFragment) {
                    switchFragment("whatsnew");
                } else if (fragment_now == fragment_oobeFinishFragment) {
                    switchFragment("policies");
                } else if (fragment_now == fragment_oobeWhatsNewFragment) {
                    switchFragment("welcome");
                } else {
                    finish();
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, compatibilityBackCallback);
    }

    public void switchFragment(String target) {
        Fragment next = resolveFragment(target);
        boolean forward = orderOf(next) >= orderOf(fragment_now);
        androidx.fragment.app.FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();
        transaction.setReorderingAllowed(true);
        if (forward) {
            transaction.setCustomAnimations(R.animator.fragment_enter, R.animator.fragment_exit);
        } else {
            transaction.setCustomAnimations(R.animator.fragment_pop_enter, R.animator.fragment_pop_exit);
        }
        fragment_now = next;
        transaction.replace(R.id.fragment_container, next).commit();
    }

    private Fragment resolveFragment(String target) {
        if ("finish".equals(target)) return fragment_oobeFinishFragment;
        if ("policies".equals(target)) return fragment_oobePoliciesFragment;
        if ("whatsnew".equals(target)) return fragment_oobeWhatsNewFragment;
        return fragment_oobeWelcomeFragment;
    }

    /** 引导步骤顺序：欢迎 → 新版变化 → 政策 → 完成。用于决定切换动画方向。 */
    private int orderOf(Fragment fragment) {
        if (fragment == fragment_oobeWelcomeFragment) return 0;
        if (fragment == fragment_oobeWhatsNewFragment) return 1;
        if (fragment == fragment_oobePoliciesFragment) return 2;
        if (fragment == fragment_oobeFinishFragment) return 3;
        return -1;
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
