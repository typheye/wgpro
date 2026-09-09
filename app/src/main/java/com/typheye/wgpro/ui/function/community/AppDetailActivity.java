package com.typheye.wgpro.ui.function.community;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProBottomSheetDialog;
import com.typheye.wgpro.utils.tAccUtils;
import org.json.JSONObject;
import java.util.LinkedHashMap;
import java.util.Map;

public final class AppDetailActivity extends BaseSectionActivity {
    public static final String EXTRA_APP_JSON = "app_json";
    private JSONObject appInfo = new JSONObject();
    private boolean starred;
    @Override protected String screenTitle() { return ""; }
    @Override protected boolean loadingCoversAppBar() { return true; }
    @Override protected Fragment createContent() {
        return AppDetailFragment.newInstance(getIntent().getStringExtra(EXTRA_APP_JSON));
    }
    @Override protected void onCreate(@Nullable Bundle state) {
        try { appInfo = new JSONObject(getIntent().getStringExtra(EXTRA_APP_JSON)); }
        catch (Exception ignored) { appInfo = new JSONObject(); }
        String appKey = appInfo.optString("id", appInfo.optString("package", ""));
        starred = appInfo.optBoolean("is_collected", false)
                || getSharedPreferences("app_stars", MODE_PRIVATE).getBoolean(appKey, false);
        super.onCreate(state);
        int background = getColor(R.color.surface_page);
        getWindow().setStatusBarColor(background);
        findViewById(R.id.section_app_bar).setBackgroundColor(background);
        sectionToolbar().setBackgroundColor(background);
        boolean light = (getResources().getConfiguration().uiMode
                & android.content.res.Configuration.UI_MODE_NIGHT_MASK)
                != android.content.res.Configuration.UI_MODE_NIGHT_YES;
        new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView())
                .setAppearanceLightStatusBars(light);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem more = menu.add("更多").setIcon(R.drawable.ic_more_vertical_vector);
        more.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS); return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if ("更多".contentEquals(item.getTitle())) { showAppActions(); return true; }
        return super.onOptionsItemSelected(item);
    }

    private void showAppActions() {
        tAccUtils account = new tAccUtils(getApplicationContext());
        boolean self = appInfo.optBoolean("is_self") || (!account.getUid().isEmpty()
                && account.getUid().equals(appInfo.optString("uid", appInfo.optString("author_uid"))));
        CharSequence[] actions = self ? new CharSequence[]{"删除", "分享", starred ? "移除星标" : "加入星标"}
                : new CharSequence[]{"举报", "分享", starred ? "移除星标" : "加入星标"};
        new WGProAlertDialogBuilder(this).setTitle("应用操作").setItems(actions, (dialog, which) -> {
            String action = actions[which].toString();
            if ("分享".equals(action)) { shareApp(); return; }
            if (dialog instanceof WGProBottomSheetDialog) ((WGProBottomSheetDialog) dialog).dismissForReplacement();
            sectionToolbar().postDelayed(() -> {
                if (action.contains("星标")) { toggleStar(); return; }
                if ("举报".equals(action)) { reportApp(account); return; }
                deleteApp(account);
            }, 40L);
        }).show();
    }

    private void shareApp() {
        String url = appInfo.optString("detail_url", appInfo.optString("url", ""));
        Intent send = new Intent(Intent.ACTION_SEND).setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT, url.isEmpty() ? appInfo.optString("name") : url);
        startActivity(Intent.createChooser(send, "分享应用"));
    }

    private void reportApp(tAccUtils account) {
        Map<String, String> fields = new LinkedHashMap<>(); fields.put("target_type", "app");
        fields.put("target_key", appInfo.optString("id", appInfo.optString("package")));
        fields.put("reason_code", "other"); fields.put("description", "通过 Android 客户端举报");
        postAction(account, "report_create2", fields, false);
    }

    private void toggleStar() {
        String key = appInfo.optString("id", appInfo.optString("package", ""));
        Map<String, String> fields = new LinkedHashMap<>(); fields.put("target_type", "app");
        fields.put("target_key", key); fields.put("action", starred ? "remove" : "add");
        tAccUtils account = new tAccUtils(getApplicationContext());
        WGProBottomSheetDialog progress = showProgress("正在更新星标..."); long started = android.os.SystemClock.uptimeMillis();
        account.postV2Json("collection_action2", fields, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(JSONObject json) {
                starred = json.optBoolean("collected", !starred);
                getSharedPreferences("app_stars", MODE_PRIVATE).edit().putBoolean(key, starred).apply();
                finishProgress(progress, started, true, starred ? "已加入星标" : "已移除星标", false);
            }
            @Override public void onError(int code, String message) { finishProgress(progress, started, false, message, false); }
        });
    }

    private void deleteApp(tAccUtils account) {
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("app_id", appInfo.optString("id", appInfo.optString("package")));
        postAction(account, "app_delete2", fields, true);
    }

    private void postAction(tAccUtils account, String action, Map<String, String> fields, boolean finishOnSuccess) {
        WGProBottomSheetDialog progress = showProgress("正在处理..."); long started = android.os.SystemClock.uptimeMillis();
        account.postV2Json(action, fields, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(JSONObject json) { finishProgress(progress, started, true, json.optString("msg", "操作成功"), finishOnSuccess); }
            @Override public void onError(int code, String message) { finishProgress(progress, started, false, message, false); }
        });
    }

    private WGProBottomSheetDialog showProgress(String message) {
        View view = View.inflate(this, R.layout.progress_dialog, null); ((TextView) view.findViewById(android.R.id.message)).setText(message);
        WGProBottomSheetDialog dialog = new WGProAlertDialogBuilder(this).setTitle("处理中").setView(view).setCancelable(false).create(); dialog.show(); return dialog;
    }
    private void finishProgress(WGProBottomSheetDialog progress, long started, boolean success, String message, boolean close) {
        runOnUiThread(() -> sectionToolbar().postDelayed(() -> { if (isFinishing()) return; progress.dismissForReplacement();
            new WGProAlertDialogBuilder(this).setTitle(success ? "操作成功" : "操作失败").setMessage(message).setNegativeButton("关闭", (d,w) -> { if (close) finish(); }).show();
        }, Math.max(0, 300L - (android.os.SystemClock.uptimeMillis() - started))));
    }
}
