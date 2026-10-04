package open.cn.awg.pro.core;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/** ZIP operations used by application backup and theme imports. */
public final class NativeArchives {
    private NativeArchives() {
    }

    public static int extract(Context context, String archivePath, String destinationPath, boolean overwrite) {
        return extract(context, archivePath, null, destinationPath, overwrite);
    }

    public static int extract(Context context, String archivePath, String entryPrefix,
                              String destinationPath, boolean overwrite) {
        int extracted = 0;
        File archive = NativeFiles.file(context, archivePath);
        File destination = NativeFiles.file(context, destinationPath);
        String root = destination.getAbsolutePath();
        if (!root.endsWith("/") && !root.endsWith("\\")) {
            root += "/";
        }
        try (ZipInputStream input = new ZipInputStream(new FileInputStream(archive))) {
            String canonicalRoot = destination.getCanonicalPath();
            String destinationPrefix = canonicalRoot.endsWith(File.separator)
                    ? canonicalRoot : canonicalRoot + File.separator;
            ZipEntry entry;
            while ((entry = input.getNextEntry()) != null) {
                String name = entry.getName();
                if (entryPrefix != null && !name.startsWith(entryPrefix)) {
                    input.closeEntry();
                    continue;
                }
                File outputFile = new File(root + name);
                if (!isWithinDestination(outputFile, destinationPrefix)) {
                    extracted++;
                    input.closeEntry();
                    continue;
                }
                if (!entry.isDirectory()) {
                    File parent = outputFile.getParentFile();
                    if (parent != null && !parent.exists()) {
                        parent.mkdirs();
                    }
                    if (!outputFile.exists() || overwrite) {
                        try (FileOutputStream output = new FileOutputStream(outputFile, false)) {
                            byte[] buffer = new byte[4096];
                            int count;
                            while ((count = input.read(buffer)) != -1) {
                                output.write(buffer, 0, count);
                            }
                        }
                    }
                }
                extracted++;
                input.closeEntry();
            }
        } catch (IOException | RuntimeException ignored) {
            return extracted;
        }
        return extracted;
    }

    private static boolean isWithinDestination(File outputFile, String destinationPrefix)
            throws IOException {
        return outputFile.getCanonicalPath().startsWith(destinationPrefix);
    }

    public static int create(Context context, String sourcePath, String archivePath) {
        File source = NativeFiles.file(context, sourcePath);
        File archive = NativeFiles.file(context, archivePath);
        File parent = archive.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
        int count = 0;
        try (ZipOutputStream output = new ZipOutputStream(new FileOutputStream(archive, false))) {
            if (source.isFile()) {
                count = addFile(source, source.getParentFile(), source.getName(), output);
            } else if (source.isDirectory()) {
                File[] children = source.listFiles();
                if (children != null) {
                    for (File child : children) {
                        count += addPath(child, child.getName(), output);
                    }
                }
            }
        } catch (IOException | RuntimeException ignored) {
            return count;
        }
        return count;
    }

    private static int addPath(File source, String entryName, ZipOutputStream output) throws IOException {
        if (source.isFile()) {
            return addFile(source, source.getParentFile(), entryName, output);
        }
        File[] children = source.listFiles();
        if (children == null || children.length == 0) {
            output.putNextEntry(new ZipEntry(entryName + File.separator));
            output.closeEntry();
            return 1;
        }
        int count = 0;
        for (File child : children) {
            count += addPath(child, entryName + File.separator + child.getName(), output);
        }
        return count;
    }

    private static int addFile(File source, File base, String entryName, ZipOutputStream output) throws IOException {
        output.putNextEntry(new ZipEntry(entryName));
        try (FileInputStream input = new FileInputStream(source)) {
            byte[] buffer = new byte[4096];
            int count;
            while ((count = input.read(buffer)) != -1) {
                output.write(buffer, 0, count);
            }
        }
        output.closeEntry();
        return 1;
    }
}
