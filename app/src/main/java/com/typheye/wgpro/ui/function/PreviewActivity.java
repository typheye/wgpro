package com.typheye.wgpro.ui.function;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProBottomSheetDialog;
import com.typheye.wgpro.ui.widget.ZoomableImageView;
import com.typheye.wgpro.utils.AppUtils;
import com.typheye.wgpro.utils.ImageCache;
import com.typheye.wgpro.utils.SystemBars;
import com.typheye.wgpro.utils.tAccUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import okhttp3.Request;
import okhttp3.Response;

/**
 * 图片预览：全屏黑底 + 可缩放/拖动，无应用栏。
 * 单击屏幕退出，长按屏幕弹出底部菜单（保存到手机 / 退出）。用于头像、应用截图、动态/资源附图的预览。
 */
public class PreviewActivity extends AppCompatActivity {
    public static final String EXTRA_IMAGES = "images";
    public static final String EXTRA_INDEX = "index";
    private static final int REQUEST_STORAGE = 9001;

    private ViewPager2 pager;
    private final List<String> images = new ArrayList<>();
    private int currentIndex = 0;
    private boolean pendingSave = false;
    /** 正在加载（未命中内存缓存）的图片 URL；当前页在加载中时显示全屏遮罩。 */
    private final Set<String> loadingUrls = new HashSet<>();
    private View loadingOverlay;
    private final Runnable hideLoadingTask = this::hideLoading;

    /** 预览单张图片。 */
    public static void open(@NonNull Context context, @Nullable String url) {
        if (url == null || url.trim().isEmpty()) return;
        ArrayList<String> list = new ArrayList<>();
        list.add(url.trim());
        open(context, list, 0);
    }

    /** 预览一组图片。 */
    public static void open(@NonNull Context context, @Nullable List<String> urls, int index) {
        if (urls == null) return;
        ArrayList<String> list = new ArrayList<>();
        for (String url : urls) {
            if (url != null && !url.trim().isEmpty()) list.add(url.trim());
        }
        if (list.isEmpty()) return;
        Intent intent = new Intent(context, PreviewActivity.class);
        intent.putExtra(EXTRA_IMAGES, list.toArray(new String[0]));
        intent.putExtra(EXTRA_INDEX, Math.max(0, Math.min(index, list.size() - 1)));
        if (!(context instanceof android.app.Activity)) intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle state) {
        AppUtils.useScreenCutArea(getWindow(), this);
        super.onCreate(state);
        setContentView(R.layout.activity_preview);
        applyPreviewTransition();
        // 内容真正铺满整屏：标记沉浸，避免 SystemBars 的兜底内边距给根布局加顶部 padding
        SystemBars.markImmersive(findViewById(R.id.preview_root));
        // 隐藏状态栏，保留底部导航栏/导航条（透明 + 悬浮）
        hideStatusBar();

        String[] extra = getIntent().getStringArrayExtra(EXTRA_IMAGES);
        if (extra != null) {
            for (String url : extra) {
                if (url != null && !url.trim().isEmpty()) images.add(url.trim());
            }
        }
        if (images.isEmpty()) {
            finish();
            return;
        }
        currentIndex = Math.max(0, Math.min(getIntent().getIntExtra(EXTRA_INDEX, 0), images.size() - 1));

        pager = findViewById(R.id.preview_pager);
        loadingOverlay = findViewById(R.id.preview_loading_overlay);
        pager.setAdapter(new PreviewAdapter());
        pager.setCurrentItem(currentIndex, false);
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override public void onPageSelected(int position) {
                currentIndex = position;
                refreshLoadingOverlay();
            }
        });
        pager.post(this::refreshLoadingOverlay);
    }

    /** 当前页图片还在加载（未命中缓存）时显示全屏遮罩，避免出现"闪一下"。 */
    private void refreshLoadingOverlay() {
        if (currentIndex < 0 || currentIndex >= images.size()) {
            hideLoading();
            return;
        }
        if (loadingUrls.contains(images.get(currentIndex))) showLoading();
        else hideLoading();
    }

    private void showLoading() {
        if (loadingOverlay == null || loadingOverlay.getVisibility() == View.VISIBLE) return;
        loadingOverlay.removeCallbacks(hideLoadingTask);
        loadingOverlay.setVisibility(View.VISIBLE);
        // 兜底：网络异常时不会无限遮罩
        loadingOverlay.postDelayed(hideLoadingTask, 15000L);
    }

    private void hideLoading() {
        if (loadingOverlay == null) return;
        loadingOverlay.removeCallbacks(hideLoadingTask);
        loadingOverlay.setVisibility(View.GONE);
    }

    /** 进入/退出用放大缩小效果，而不是位移。 */
    private void applyPreviewTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            overrideActivityTransition(OVERRIDE_TRANSITION_OPEN,
                    R.anim.preview_zoom_in, R.anim.preview_hold);
            overrideActivityTransition(OVERRIDE_TRANSITION_CLOSE,
                    R.anim.preview_hold, R.anim.preview_zoom_out);
        } else {
            getWindow().setWindowAnimations(R.style.PreviewActivityAnimation);
        }
    }

    /** 沉浸显示图片：只隐藏状态栏，底部导航栏/手势条保持原样。 */
    private void hideStatusBar() {
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        WindowInsetsControllerCompat controller =
                WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        controller.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        controller.hide(WindowInsetsCompat.Type.statusBars());
    }

    /** 长按屏幕弹出的「更多」菜单。 */
    private void showMoreMenu() {
        if (isFinishing() || isDestroyed()) return;
        new WGProAlertDialogBuilder(this).setTitle("更多")
                .setItems(new CharSequence[]{"保存到手机", "退出"}, (dialog, which) -> {
                    if (which == 0) saveCurrentImage();
                    else finish();
                }).show();
    }

    private void saveCurrentImage() {
        if (currentIndex < 0 || currentIndex >= images.size()) return;
        final String url = images.get(currentIndex);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q
                && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            pendingSave = true;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE);
            return;
        }
        downloadAndSave(url);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != REQUEST_STORAGE) return;
        if (pendingSave && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pendingSave = false;
            saveCurrentImage();
        } else {
            pendingSave = false;
            new WGProAlertDialogBuilder(this).setTitle("无法保存")
                    .setMessage("没有存储权限，无法保存到相册。").setNegativeButton("关闭", null).show();
        }
    }

    private void downloadAndSave(final String url) {
        View progressView = View.inflate(this, R.layout.progress_dialog, null);
        ((TextView) progressView.findViewById(android.R.id.message)).setText("正在保存...");
        final WGProBottomSheetDialog progress = new WGProAlertDialogBuilder(this)
                .setTitle("保存中").setView(progressView).setCancelable(false).create();
        progress.show();

        Request request;
        try {
            request = new Request.Builder().url(url).build();
        } catch (IllegalArgumentException ignored) {
            progress.dismissForReplacement();
            showResult(false);
            return;
        }
        new tAccUtils(this).getClient().newCall(request).enqueue(new okhttp3.Callback() {
            @Override public void onFailure(@NonNull okhttp3.Call call, @NonNull java.io.IOException e) {
                runOnUiThread(() -> { progress.dismissForReplacement(); showResult(false); });
            }

            @Override public void onResponse(@NonNull okhttp3.Call call, @NonNull Response response) {
                boolean ok = false;
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        byte[] bytes = response.body().bytes();
                        ok = saveToGallery(bytes, url);
                    }
                } catch (Exception ignored) {
                    ok = false;
                } finally {
                    response.close();
                }
                final boolean success = ok;
                runOnUiThread(() -> { progress.dismissForReplacement(); showResult(success); });
            }
        });
    }

    private void showResult(boolean success) {
        if (isFinishing() || isDestroyed()) return;
        new WGProAlertDialogBuilder(this)
                .setTitle(success ? "已保存到相册" : "保存失败")
                .setMessage(success ? "图片已保存到系统相册的 Typheye 相册。" : "请检查网络后重试。")
                .setNegativeButton("关闭", null)
                .show();
    }

    /** 写入系统相册：Android 10+ 走 MediaStore，低版本写公共 Pictures 目录并通知媒体库。 */
    private boolean saveToGallery(byte[] bytes, String url) {
        String fileName = "wgpro_" + System.currentTimeMillis() + extensionOf(url);
        String mime = mimeOf(url);
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
                values.put(MediaStore.Images.Media.MIME_TYPE, mime);
                values.put(MediaStore.Images.Media.RELATIVE_PATH,
                        Environment.DIRECTORY_PICTURES + "/Typheye");
                values.put(MediaStore.Images.Media.IS_PENDING, 1);
                Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                if (uri == null) return false;
                try (OutputStream out = getContentResolver().openOutputStream(uri)) {
                    if (out == null) return false;
                    out.write(bytes);
                }
                values.clear();
                values.put(MediaStore.Images.Media.IS_PENDING, 0);
                getContentResolver().update(uri, values, null, null);
                return true;
            }
            File dir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), "Typheye");
            if (!dir.isDirectory() && !dir.mkdirs()) return false;
            File file = new File(dir, fileName);
            try (FileOutputStream out = new FileOutputStream(file)) {
                out.write(bytes);
            }
            MediaScannerConnection.scanFile(this, new String[]{file.getAbsolutePath()},
                    new String[]{mime}, null);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    private static String extensionOf(String url) {
        String lower = url == null ? "" : url.toLowerCase(java.util.Locale.ROOT);
        if (lower.contains(".png")) return ".png";
        if (lower.contains(".webp")) return ".webp";
        if (lower.contains(".gif")) return ".gif";
        return ".jpg";
    }

    private static String mimeOf(String url) {
        String lower = url == null ? "" : url.toLowerCase(java.util.Locale.ROOT);
        if (lower.contains(".png")) return "image/png";
        if (lower.contains(".webp")) return "image/webp";
        if (lower.contains(".gif")) return "image/gif";
        return "image/jpeg";
    }

    private final class PreviewAdapter extends RecyclerView.Adapter<PreviewAdapter.Holder> {
        @NonNull @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            FrameLayout container = new FrameLayout(parent.getContext());
            container.setLayoutParams(new RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ZoomableImageView image = new ZoomableImageView(parent.getContext());
            image.setLayoutParams(new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            image.setListener(new ZoomableImageView.Listener() {
                @Override public void onSingleTap() { finish(); }
                @Override public void onLongPress() { showMoreMenu(); }
            });
            container.addView(image);
            return new Holder(container, image);
        }

        @Override
        public void onBindViewHolder(@NonNull Holder holder, int position) {
            final String url = images.get(position);
            holder.boundUrl = url;
            holder.image.setImageDrawable(null);
            Bitmap cached = ImageCache.getMemory(url);
            if (cached != null) {
                loadingUrls.remove(url);
                holder.image.setImageBitmap(cached);
                if (url.equals(currentUrl())) hideLoading();
                return;
            }
            // 未命中内存缓存：标记为加载中；当前页会显示全屏遮罩
            loadingUrls.add(url);
            if (url.equals(currentUrl())) showLoading();
            ImageCache.loadBitmap(PreviewActivity.this, url, bitmap -> {
                loadingUrls.remove(url);
                if (!url.equals(holder.boundUrl)) return;
                if (bitmap != null && !bitmap.isRecycled()) holder.image.setImageBitmap(bitmap);
                if (url.equals(currentUrl())) hideLoading();
            });
        }

        @Override public int getItemCount() { return images.size(); }

        final class Holder extends RecyclerView.ViewHolder {
            final ZoomableImageView image;
            @Nullable String boundUrl;
            Holder(@NonNull View itemView, @NonNull ZoomableImageView image) {
                super(itemView);
                this.image = image;
            }
        }
    }

    @Nullable
    private String currentUrl() {
        return currentIndex >= 0 && currentIndex < images.size() ? images.get(currentIndex) : null;
    }

    @Override
    protected void onDestroy() {
        if (pager != null) pager.setAdapter(null);
        super.onDestroy();
    }
}
