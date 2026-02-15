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
import com.typheye.wgpro.ui.oobe.OobeActivity;
import com.typheye.wgpro.utils.AppUtils;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {
    private static final long SPLASH_DELAY = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.useScreenCutArea(getWindow(),this);
        setContentView(R.layout.activity_splash);
        AppUtils.fixScreenCutArea(findViewById(R.id.container));

        new Handler(Looper.getMainLooper()).postDelayed(this::goToMain, SPLASH_DELAY);
    }

    private void goToMain() {
        if (AppUtils.getAppInit(this)) {
            goMain();
        } else {
            goOOBE();
        }
    }

    void goMain(){
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    void goOOBE(){
        Intent intent = new Intent(SplashActivity.this, OobeActivity.class);
        startActivity(intent);
        finish();
    }

}