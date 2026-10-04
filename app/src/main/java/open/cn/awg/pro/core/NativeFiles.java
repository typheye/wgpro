package open.cn.awg.pro.core;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public final class NativeFiles {
    private NativeFiles() {
    }

    public static String resolvePath(Context context, String path) {
        if (path == null) {
            return null;
        }
        Context appContext = applicationContext(context);
        if (path.startsWith("@")) {
            return path.substring(1);
        }
        if (path.startsWith("%")) {
            String externalPath = path.substring(1);
            if (externalPath.startsWith("/") || externalPath.startsWith("\\")) {
                return externalPath;
            }
            File base = externalFilesBase(appContext);
            return new File(base, externalPath).getAbsolutePath();
        }
        if (path.startsWith("$")) {
            return new File(appContext.getFilesDir(), path.substring(1)).getAbsolutePath();
        }
        if (path.startsWith("/")) {
            return AppPaths.normalize(path);
        }
        return new File(Environment.getExternalStorageDirectory(), path).getAbsolutePath();
    }

    public static boolean exists(Context context, String path) {
        if (path == null) {
            return false;
        }
        if (path.startsWith("@")) {
            try (InputStream stream = applicationContext(context).getAssets().open(resolvePath(context, path))) {
                return true;
            } catch (IOException | RuntimeException ignored) {
                return false;
            }
        }
        return file(context, path).exists();
    }

    public static int type(Context context, String path) {
        if (path == null) {
            return -1;
        }
        if (path.startsWith("@")) {
            Context appContext = applicationContext(context);
            String assetPath = resolvePath(appContext, path);
            try {
                if (!assetExists(appContext, assetPath)) {
                    return -1;
                }
                String[] children = appContext.getAssets().list(assetPath);
                if (children != null && children.length > 0) {
                    return 2;
                }
                try (InputStream stream = appContext.getAssets().open(assetPath)) {
                    return 1;
                } catch (IOException ignored) {
                    return 2;
                }
            } catch (IOException | RuntimeException ignored) {
                return -1;
            }
        }
        File target = file(context, path);
        if (target.isDirectory()) {
            return 2;
        }
        return target.isFile() ? 1 : -1;
    }

    public static long size(Context context, String path) {
        if (path == null) {
            return -1L;
        }
        if (path.startsWith("@")) {
            try (InputStream stream = applicationContext(context).getAssets().open(resolvePath(context, path))) {
                return stream.available();
            } catch (IOException | RuntimeException ignored) {
                return -1L;
            }
        }
        return recursiveSize(file(context, path));
    }

    public static String readText(Context context, String path) {
        return readText(context, path, StandardCharsets.UTF_8.name());
    }

    public static String readText(Context context, String path, String charsetName) {
        if (path == null) {
            return null;
        }
        Charset charset = charset(charsetName);
        try (InputStream input = openInput(context, path);
             InputStreamReader reader = new InputStreamReader(input, charset)) {
            char[] buffer = new char[4096];
            StringBuilder text = new StringBuilder();
            int count;
            while ((count = reader.read(buffer)) != -1) {
                text.append(buffer, 0, count);
            }
            return text.toString();
        } catch (IOException | RuntimeException ignored) {
            return null;
        }
    }

    public static boolean writeText(Context context, String path, String text) {
        return writeText(context, path, text, StandardCharsets.UTF_8.name());
    }

    public static boolean writeText(Context context, String path, String text, String charsetName) {
        if (path == null || path.startsWith("@")) {
            return false;
        }
        if (text == null) {
            return false;
        }
        return writeBytes(context, path, text.getBytes(charset(charsetName)));
    }

    public static byte[] readBytes(Context context, String path) {
        if (path == null) {
            return null;
        }
        try (InputStream input = openInput(context, path);
             ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[8192];
            int count;
            while ((count = input.read(buffer)) != -1) {
                output.write(buffer, 0, count);
            }
            return output.toByteArray();
        } catch (IOException | RuntimeException ignored) {
            return null;
        }
    }

    public static boolean writeBytes(Context context, String path, byte[] bytes) {
        if (path == null || path.startsWith("@") || bytes == null) {
            return false;
        }
        File target = file(context, path);
        File parent = target.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs() && !parent.isDirectory()) {
            return false;
        }
        try (OutputStream output = new FileOutputStream(target, false)) {
            output.write(bytes);
            output.flush();
            return true;
        } catch (IOException | RuntimeException ignored) {
            return false;
        }
    }

    public static boolean copy(Context context, String source, String destination, boolean overwrite) {
        if (source == null || destination == null || destination.startsWith("@")) {
            return false;
        }
        File destinationFile = file(context, destination);
        if (source.startsWith("@")) {
            return copyAsset(applicationContext(context), resolvePath(context, source), destinationFile, overwrite);
        }
        File sourceFile = file(context, source);
        if (!sourceFile.exists() || sourceFile.getAbsoluteFile().equals(destinationFile.getAbsoluteFile())) {
            return false;
        }
        return copyFileOrDirectory(sourceFile, destinationFile, overwrite);
    }

    public static boolean move(Context context, String source, String destination, boolean overwrite) {
        if (source == null || destination == null || destination.startsWith("@")) {
            return false;
        }
        File destinationFile = file(context, destination);
        if (source.startsWith("@")) {
            return copyAsset(applicationContext(context), resolvePath(context, source), destinationFile, overwrite);
        }
        File sourceFile = file(context, source);
        if (!sourceFile.exists() || sourceFile.getAbsoluteFile().equals(destinationFile.getAbsoluteFile())) {
            return false;
        }
        if (destinationFile.exists() && !overwrite) {
            return false;
        }
        File parent = destinationFile.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs() && !parent.isDirectory()) {
            return false;
        }
        if (destinationFile.exists() && destinationFile.isFile() && !destinationFile.delete()) {
            return false;
        }
        if (sourceFile.renameTo(destinationFile)) {
            return true;
        }
        if (!copyFileOrDirectory(sourceFile, destinationFile, overwrite)) {
            return false;
        }
        return deleteFileOrDirectory(sourceFile, true) > 0;
    }

    public static int delete(Context context, String path, boolean recursive) {
        if (path == null || path.startsWith("@")) {
            return 0;
        }
        return deleteFileOrDirectory(file(context, path), recursive);
    }

    public static String[] list(Context context, String path) {
        if (path == null) {
            return null;
        }
        if (path.startsWith("@")) {
            try {
                String assetPath = resolvePath(context, path);
                String[] children = applicationContext(context).getAssets().list(assetPath);
                if (children == null) {
                    return null;
                }
                String[] result = new String[children.length];
                for (int index = 0; index < children.length; index++) {
                    result[index] = path.endsWith("/") ? path + children[index] : path + "/" + children[index];
                }
                return result;
            } catch (IOException | RuntimeException ignored) {
                return null;
            }
        }
        File[] children = file(context, path).listFiles();
        if (children == null) {
            return null;
        }
        String[] result = new String[children.length];
        for (int index = 0; index < children.length; index++) {
            result[index] = children[index].getAbsolutePath();
        }
        return result;
    }

    public static String[] list(Context context, String path, boolean directories) {
        if (path == null || path.startsWith("@")) {
            return null;
        }
        File[] children = file(context, path).listFiles();
        if (children == null) {
            return null;
        }
        List<String> result = new ArrayList<>();
        for (File child : children) {
            if (directories ? child.isDirectory() : child.isFile()) {
                result.add(child.getAbsolutePath());
            }
        }
        return result.toArray(new String[0]);
    }

    public static File[] search(Context context, Object root, Object extensions) {
        File start = root instanceof File ? (File) root : file(context, String.valueOf(root));
        String[] patterns = patterns(extensions);
        List<File> matches = new ArrayList<>();
        collectMatches(start, patterns, matches);
        return matches.toArray(new File[0]);
    }

    private static String[] patterns(Object extensions) {
        if (extensions == null) {
            return null;
        }
        if (extensions instanceof String[]) {
            String[] values = ((String[]) extensions).clone();
            for (int index = 0; index < values.length; index++) {
                if (values[index] != null) {
                    values[index] = values[index].toLowerCase(java.util.Locale.ROOT);
                }
            }
            return values;
        }
        if (extensions.getClass().isArray()) {
            int length = java.lang.reflect.Array.getLength(extensions);
            String[] values = new String[length];
            for (int index = 0; index < length; index++) {
                Object value = java.lang.reflect.Array.get(extensions, index);
                values[index] = value == null ? null : value.toString().toLowerCase(java.util.Locale.ROOT);
            }
            return values;
        }
        return new String[]{extensions.toString().toLowerCase(java.util.Locale.ROOT)};
    }

    private static void collectMatches(File file, String[] patterns, List<File> matches) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    collectMatches(child, patterns, matches);
                }
            }
            return;
        }
        if (patterns == null) {
            matches.add(file);
            return;
        }
        String name = file.getName().toLowerCase(java.util.Locale.ROOT);
        for (String pattern : patterns) {
            if (pattern == null) {
                continue;
            }
            boolean matched = pattern.startsWith("*")
                    ? name.endsWith(pattern.substring(1))
                    : pattern.endsWith("*")
                    ? name.startsWith(pattern.substring(0, pattern.length() - 1))
                    : name.contains(pattern);
            if (matched) {
                matches.add(file);
                return;
            }
        }
    }

    public static File file(Context context, String path) {
        return new File(resolvePath(context, path));
    }

    private static boolean assetExists(Context context, String assetPath) throws IOException {
        if (assetPath.length() == 0) {
            return true;
        }
        String normalizedPath = assetPath.replace('\\', '/');
        String parent = "";
        for (String segment : normalizedPath.split("/")) {
            if (segment.length() == 0) {
                continue;
            }
            String[] names = context.getAssets().list(parent);
            boolean found = false;
            if (names != null) {
                for (String name : names) {
                    if (name.equals(segment)) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                return false;
            }
            parent = parent.length() == 0 ? segment : parent + "/" + segment;
        }
        return true;
    }

    private static Context applicationContext(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        Context applicationContext = context.getApplicationContext();
        return applicationContext == null ? context : applicationContext;
    }

    private static File externalFilesBase(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            File externalFiles = context.getExternalFilesDir("");
            return externalFiles == null ? context.getFilesDir() : externalFiles;
        }
        if (context.checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            return Environment.getExternalStorageDirectory();
        }
        return context.getFilesDir();
    }

    private static Charset charset(String charsetName) {
        return Charset.forName(charsetName == null ? StandardCharsets.UTF_8.name() : charsetName);
    }

    private static InputStream openInput(Context context, String path) throws IOException {
        Context appContext = applicationContext(context);
        if (path.startsWith("@")) {
            return appContext.getAssets().open(resolvePath(appContext, path));
        }
        return new FileInputStream(file(appContext, path));
    }

    private static long recursiveSize(File file) {
        if (!file.exists()) {
            return -1L;
        }
        if (file.isFile()) {
            return file.length();
        }
        File[] children = file.listFiles();
        if (children == null) {
            return 0L;
        }
        long total = 0L;
        for (File child : children) {
            long childSize = recursiveSize(child);
            if (childSize > 0L) {
                total += childSize;
            }
        }
        return total;
    }

    private static boolean copyAsset(Context context, String assetPath, File destination, boolean overwrite) {
        try {
            String[] children = context.getAssets().list(assetPath);
            if (children != null && children.length > 0) {
                if (!destination.exists() && !destination.mkdirs() && !destination.isDirectory()) {
                    return false;
                }
                boolean copied = false;
                for (String child : children) {
                    File nextDestination = new File(destination, child);
                    boolean childCopied = copyAsset(context,
                            assetPath.isEmpty() ? child : assetPath + "/" + child,
                            nextDestination, overwrite);
                    copied |= childCopied;
                }
                return copied;
            }
        } catch (IOException | RuntimeException ignored) {
            return false;
        }
        try (InputStream input = context.getAssets().open(assetPath)) {
            return copyStream(input, destination, overwrite);
        } catch (IOException | RuntimeException ignored) {
            return false;
        }
    }

    private static boolean copyFileOrDirectory(File source, File destination, boolean overwrite) {
        if (source.isDirectory()) {
            File[] children = source.listFiles();
            if (children == null) {
                return false;
            }
            boolean copied = false;
            for (File child : children) {
                if (copyFileOrDirectory(child, new File(destination, child.getName()), overwrite)) {
                    copied = true;
                }
            }
            return copied;
        }
        if (!source.isFile()) {
            return false;
        }
        if (destination.exists() && !overwrite) {
            return false;
        }
        try (InputStream input = new FileInputStream(source)) {
            return copyStream(input, destination, overwrite);
        } catch (IOException | RuntimeException ignored) {
            return false;
        }
    }

    private static boolean copyStream(InputStream input, File destination, boolean overwrite) throws IOException {
        if (destination.exists() && !overwrite) {
            return false;
        }
        File parent = destination.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs() && !parent.isDirectory()) {
            return false;
        }
        try (OutputStream output = new FileOutputStream(destination, false)) {
            byte[] buffer = new byte[8192];
            int count;
            while ((count = input.read(buffer)) != -1) {
                output.write(buffer, 0, count);
            }
            output.flush();
            return true;
        }
    }

    private static int deleteFileOrDirectory(File target, boolean recursive) {
        if (!target.exists()) {
            return 0;
        }
        if (target.isFile()) {
            return target.delete() ? 1 : 0;
        }
        File[] children = target.listFiles();
        if (!recursive) {
            if (children != null && children.length > 0) {
                return 0;
            }
            return target.delete() ? 1 : 0;
        }
        int deleted = 0;
        if (children != null) {
            for (File child : children) {
                deleted += deleteFileOrDirectory(child, true);
            }
        }
        if (children == null || children.length == 0) {
            return target.delete() ? deleted + 1 : deleted;
        }
        target.delete();
        return deleted;
    }
}