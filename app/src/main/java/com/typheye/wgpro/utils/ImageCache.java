package com.typheye.wgpro.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.LruCache;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 统一图片缓存：内存 LruCache + 磁盘缓存，用于头像、背景图、应用图标和资源图片。
 */
public final class ImageCache {
    private static final long MAX_DISK_BYTES = 50L * 1024L * 1024L;
    private static final Handler MAIN = new Handler(Looper.getMainLooper());
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(4);
    private static final LruCache<String, Bitmap> MEMORY =
            new LruCache<String, Bitmap>(memoryCacheKb()) {
                @Override protected int sizeOf(@NonNull String key, @NonNull Bitmap value) {
                    return Math.max(1, value.getByteCount() / 1024);
                }
            };

    /** 内存缓存：最大堆的 1/4，限制在 24–64MB（列表图片较多，太小会频繁重新解码导致刷新闪图）。 */
    private static int memoryCacheKb() {
        long maxKb = Runtime.getRuntime().maxMemory() / 1024L;
        return (int) Math.max(24L * 1024L, Math.min(64L * 1024L, maxKb / 4L));
    }

    public interface BitmapCallback {
        void onBitmap(@Nullable Bitmap bitmap);
    }

    /** 采样解码版：按 {@code targetPx} 解码后回调（用于 RecyclerView 复用行，调用方可校验是否已复用）。 */
    public static void loadBitmapSized(@NonNull Context context, @Nullable String url, int targetPx,
                                       @NonNull BitmapCallback callback) {
        final String source = normalize(url);
        if (source.isEmpty()) {
            MAIN.post(() -> callback.onBitmap(null));
            return;
        }
        final String key = source + "@" + targetPx;
        Bitmap cached = MEMORY.get(key);
        if (cached != null && !cached.isRecycled()) {
            MAIN.post(() -> callback.onBitmap(cached));
            return;
        }
        final Context appContext = context.getApplicationContext();
        EXECUTOR.execute(() -> {
            Bitmap bitmap = readDiskScaled(appContext, source, targetPx);
            if (bitmap == null) bitmap = downloadScaled(appContext, source, targetPx);
            if (bitmap != null) MEMORY.put(key, bitmap);
            final Bitmap result = bitmap;
            MAIN.post(() -> callback.onBitmap(result));
        });
    }

    private ImageCache() { }

    public static void load(@NonNull Context context, @Nullable String url,
                            @NonNull ImageView target, @Nullable Runnable onComplete) {
        final String source = normalize(url);
        // 内存命中：在同一帧内同步显示。否则列表刷新/重建时，图片会先空一帧再补上（闪一下）。
        Bitmap cached = source.isEmpty() ? null : MEMORY.get(source);
        if (cached != null && !cached.isRecycled()) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                target.setImageBitmap(cached);
                if (onComplete != null) onComplete.run();
            } else {
                target.post(() -> {
                    target.setImageBitmap(cached);
                    if (onComplete != null) onComplete.run();
                });
            }
            return;
        }
        loadBitmap(context, url, bitmap -> {
            if (bitmap == null) return;
            target.post(() -> {
                target.setImageBitmap(bitmap);
                if (onComplete != null) onComplete.run();
            });
        });
    }

    public static void loadBitmap(@NonNull Context context, @Nullable String url,
                                  @NonNull BitmapCallback callback) {
        final String source = normalize(url);
        if (source.isEmpty()) {
            MAIN.post(() -> callback.onBitmap(null));
            return;
        }
        Bitmap cached = MEMORY.get(source);
        if (cached != null && !cached.isRecycled()) {
            MAIN.post(() -> callback.onBitmap(cached));
            return;
        }
        final Context appContext = context.getApplicationContext();
        EXECUTOR.execute(() -> {
            Bitmap bitmap = readDisk(appContext, source);
            if (bitmap == null) bitmap = download(appContext, source);
            if (bitmap != null) MEMORY.put(source, bitmap);
            final Bitmap result = bitmap;
            MAIN.post(() -> callback.onBitmap(result));
        });
    }

    @Nullable
    public static Bitmap getMemory(@Nullable String url) {
        String source = normalize(url);
        if (source.isEmpty()) return null;
        Bitmap bitmap = MEMORY.get(source);
        return bitmap == null || bitmap.isRecycled() ? null : bitmap;
    }

    /**
     * 列表里的小图（头像等）：按 {@code targetPx} 采样解码（RGB_565）后再缓存。
     *
     * <p>普通 {@link #load} 是按原图解码的；列表里成百上千个条目各自持有位图时，
     * 原图解码会撑爆内存（真机实测 2000 个粉丝 → 1.4GB）。采样后单张只有几十 KB。
     * 缓存键带尺寸后缀，不会影响别处按原图加载的同一 URL。
     */
    public static void loadSized(@NonNull Context context, @Nullable String url,
                                 @NonNull ImageView target, int targetPx,
                                 @Nullable Runnable onComplete) {
        final String source = normalize(url);
        if (source.isEmpty()) return;
        final String key = source + "@" + targetPx;
        Bitmap cached = MEMORY.get(key);
        if (cached != null && !cached.isRecycled()) {
            target.setImageBitmap(cached);
            if (onComplete != null) onComplete.run();
            return;
        }
        final Context appContext = context.getApplicationContext();
        EXECUTOR.execute(() -> {
            Bitmap bitmap = readDiskScaled(appContext, source, targetPx);
            if (bitmap == null) bitmap = downloadScaled(appContext, source, targetPx);
            if (bitmap != null) MEMORY.put(key, bitmap);
            final Bitmap result = bitmap;
            MAIN.post(() -> {
                if (result == null || result.isRecycled()) return;
                target.setImageBitmap(result);
                if (onComplete != null) onComplete.run();
            });
        });
    }

    @Nullable
    private static Bitmap readDiskScaled(Context context, String url, int targetPx) {
        File file = cacheFile(context, url);
        if (!file.isFile()) return null;
        Bitmap bitmap = decodeScaledFile(file.getAbsolutePath(), targetPx);
        if (bitmap == null) {
            //noinspection ResultOfMethodCallIgnored
            file.delete();
        }
        return bitmap;
    }

    @Nullable
    private static Bitmap downloadScaled(Context context, String url, int targetPx) {
        Request request;
        try {
            request = new Request.Builder().url(url).build();
        } catch (IllegalArgumentException ignored) {
            return null;
        }
        try (Response response = new tAccUtils(context).getClient()
                .newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) return null;
            byte[] bytes = response.body().bytes();
            Bitmap bitmap = decodeScaledBytes(bytes, targetPx);
            if (bitmap == null) return null;
            saveDisk(context, url, bytes);
            return bitmap;
        } catch (IOException ignored) {
            return null;
        }
    }

    @Nullable
    public static Bitmap decodeScaledFile(String path, int targetPx) {
        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, bounds);
        if (bounds.outWidth <= 0 || bounds.outHeight <= 0) return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = sampleSize(bounds.outWidth, bounds.outHeight, targetPx);
        options.inScaled = true;
        options.inDensity = Math.max(1,
                Math.min(bounds.outWidth, bounds.outHeight) / options.inSampleSize);
        options.inTargetDensity = targetPx;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(path, options);
    }

    @Nullable
    private static Bitmap decodeScaledBytes(byte[] bytes, int targetPx) {
        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bytes, 0, bytes.length, bounds);
        if (bounds.outWidth <= 0 || bounds.outHeight <= 0) return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = sampleSize(bounds.outWidth, bounds.outHeight, targetPx);
        options.inScaled = true;
        options.inDensity = Math.max(1,
                Math.min(bounds.outWidth, bounds.outHeight) / options.inSampleSize);
        options.inTargetDensity = targetPx;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
    }

    /** 取 2 的幂采样率，使解码后最短边不小于 targetPx。 */
    private static int sampleSize(int width, int height, int targetPx) {
        if (targetPx <= 0) return 1;
        int shortest = Math.min(width, height);
        int sample = 1;
        while (shortest / (sample * 2) >= targetPx) sample *= 2;
        return sample;
    }

    public static void clear(@NonNull Context context) {
        MEMORY.evictAll();
        File dir = cacheDir(context);
        File[] files = dir.listFiles();
        if (files != null) for (File file : files) {
            //noinspection ResultOfMethodCallIgnored
            file.delete();
        }
    }

    @Nullable
    private static Bitmap readDisk(Context context, String url) {
        File file = cacheFile(context, url);
        if (!file.isFile()) return null;
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        if (bitmap == null) {
            //noinspection ResultOfMethodCallIgnored
            file.delete();
        }
        return bitmap;
    }

    @Nullable
    private static Bitmap download(Context context, String url) {
        Request request;
        try {
            request = new Request.Builder().url(url).build();
        } catch (IllegalArgumentException ignored) {
            return null;
        }
        try (Response response = new tAccUtils(context).getClient()
                .newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) return null;
            byte[] bytes = response.body().bytes();
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            if (bitmap == null) return null;
            saveDisk(context, url, bytes);
            return bitmap;
        } catch (IOException ignored) {
            return null;
        }
    }

    private static void saveDisk(Context context, String url, byte[] bytes) {
        File dir = cacheDir(context);
        if (!dir.isDirectory() && !dir.mkdirs()) return;
        File file = cacheFile(context, url);
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(bytes);
        } catch (IOException ignored) {
            return;
        }
        trim(dir);
    }

    private static void trim(File dir) {
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) return;
        long total = 0L;
        for (File file : files) total += file.length();
        if (total <= MAX_DISK_BYTES) return;
        Arrays.sort(files, Comparator.comparingLong(File::lastModified));
        for (File file : files) {
            if (total <= MAX_DISK_BYTES) break;
            long length = file.length();
            if (file.delete()) total -= length;
        }
    }

    private static File cacheDir(Context context) {
        return new File(context.getCacheDir(), "image_cache");
    }

    private static File cacheFile(Context context, String url) {
        return new File(cacheDir(context), sha256(url) + ".img");
    }

    private static String normalize(@Nullable String url) {
        if (url == null) return "";
        String value = url.trim();
        if (value.isEmpty()) return "";
        if (value.startsWith("//")) return "https:" + value;
        if (value.startsWith("/")) return "https://service.typheye.cn" + value;
        return value;
    }

    private static String sha256(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(value.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            StringBuilder builder = new StringBuilder(bytes.length * 2);
            for (byte b : bytes) {
                builder.append(Character.forDigit((b >> 4) & 0xF, 16));
                builder.append(Character.forDigit(b & 0xF, 16));
            }
            return builder.toString();
        } catch (Exception ignored) {
            return Integer.toHexString(value.hashCode());
        }
    }
}
