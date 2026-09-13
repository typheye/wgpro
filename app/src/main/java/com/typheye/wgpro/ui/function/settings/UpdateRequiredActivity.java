package com.typheye.wgpro.ui.function.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.utils.SystemBars;

/**
 * 「必须更新」页面。
 *
 * 服务端在 config-app.json 里标了 UpdateForce 且本机版本更低时会被拉起，
 * 特点：
 *   - 返回键无效，不能跳过；
 *   - 只提供"立即更新"一个出口，通往下载页；
 *   - 用户装上新版本后，本机版本号已经达到服务端要求，再次经过 onResume 会自动放行；
 *   - 运营在控制台取消强制更新后，这里重新拉一次配置也会自动放行。
 */
public class UpdateRequiredActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppUtils.useScreenCutArea(getWindow(), this);
        setContentView(R.layout.activity_update_required);
        AppUtils.fixScreenCutArea(findViewById(R.id.container));
        SystemBars.reserveBottomInsetForScroll(findViewById(R.id.container));

        // 返回键（含手势返回）一律吞掉，强制更新期间不给任何绕过的路子
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // 有意留空
            }
        });

        int currentCode = AppUtils.getVersionCode(this);
        String message = AppUtils.getForceUpdateMessage(this);
        String url = AppUtils.getForceUpdateUrl(this);

        TextView version = findViewById(R.id.update_version);
        version.setText("当前版本 " + currentCode + " · 需要更新后才能继续使用");

        TextView updateText = findViewById(R.id.update_text);
        String body = extractChangeLog(message);
        if (body.isEmpty()) {
            updateText.setVisibility(View.GONE);
        } else {
            updateText.setText(body);
        }

        MaterialButton updateButton = findViewById(R.id.btn_update);
        updateButton.setOnClickListener(v -> openDownloadPage(url));

        // 再确认一次：如果运营已经取消强制更新，或者本机其实已经达标，就直接放行
        AppUtils.loadServerConfig(getApplicationContext(), forceRequired -> {
            if (!forceRequired) finish();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!AppUtils.isForceUpdateRequired(this)) {
            finish();
        }
    }

    /** 从"Ver. x (y) 现已发布！\n\n更新日志：..."里取出更新日志正文。 */
    private String extractChangeLog(String message) {
        if (message == null || message.trim().isEmpty()) return "";
        int index = message.indexOf("更新日志：");
        if (index < 0) return message.trim();
        return message.substring(index + "更新日志：".length()).trim();
    }

    private void openDownloadPage(String url) {
        if (url == null || url.trim().isEmpty()) return;
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("URL", url.trim());
        startActivity(intent);
    }
}
