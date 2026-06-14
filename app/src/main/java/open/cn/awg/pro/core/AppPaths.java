/**
 ******************************************************************************
 * @file    AppPaths.java
 * @author  Typheye
 * @brief   Centralized app-private and external file path resolver.
 ******************************************************************************
 * @attention
 *
 * Copyright (c) 2021-2026 Typheye. All rights reserved.
 *
 * This software is licensed under terms that can be found in the LICENSE file
 * in the root directory of this software component.
 * If no LICENSE file comes with this software, it is provided AS-IS.
 *
 ******************************************************************************
 */
package open.cn.awg.pro.core;

import android.content.Context;
import android.os.Build;

import java.io.File;

import open.cn.awg.pro.app.AwgProApplication;

public final class AppPaths {

    public static final String PACKAGE_NAME = "open.cn.awg.pro";
    private static final String ROOT = File.separator;

    private static final String LEGACY_INTERNAL_ROOT =
            ROOT + "data" + ROOT + "user" + ROOT + "0" + ROOT + PACKAGE_NAME;
    private static final String LEGACY_DATA_ROOT =
            ROOT + "data" + ROOT + "data" + ROOT + PACKAGE_NAME;
    private static final String LEGACY_EXTERNAL_FILES_ROOT =
            "/storage/emulated/0/Android/data/" + PACKAGE_NAME + "/files";
    private static final String LEGACY_EXTERNAL_APP_ROOT =
            "/storage/emulated/0/Android/data/" + PACKAGE_NAME;
    private static final String LEGACY_SDCARD_EXTERNAL_FILES_ROOT =
            ROOT + "sdcard" + ROOT + "Android" + ROOT + "data" + ROOT + PACKAGE_NAME + ROOT + "files";
    private static final String LEGACY_SDCARD_EXTERNAL_APP_ROOT =
            ROOT + "sdcard" + ROOT + "Android" + ROOT + "data" + ROOT + PACKAGE_NAME;

    private AppPaths() {
    }

    public static String appPath(String relativePath) {
        return join(internalRoot(), relativePath);
    }

    public static String externalFilesPath(String relativePath) {
        return join(externalFilesRoot(), relativePath);
    }

    public static String externalAppPath(String relativePath) {
        return join(externalAppRoot(), relativePath);
    }

    public static String internalRoot() {
        Context context = context();
        if (context == null) {
            return LEGACY_INTERNAL_ROOT;
        }

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return context.getDataDir().getAbsolutePath();
            }

            File filesDir = context.getFilesDir();
            File parent = filesDir == null ? null : filesDir.getParentFile();
            if (parent != null) {
                return parent.getAbsolutePath();
            }
        } catch (Exception ignored) {
        }

        return LEGACY_INTERNAL_ROOT;
    }

    public static String externalFilesRoot() {
        return join(externalAppRoot(), "files");
    }

    public static String externalAppRoot() {
        Context context = context();
        if (context != null) {
            try {
                File externalFilesDir = context.getExternalFilesDir(null);
                File externalRoot = externalFilesDir == null ? null : externalFilesDir.getParentFile();
                if (externalRoot != null) {
                    return externalRoot.getAbsolutePath();
                }
            } catch (Exception ignored) {
            }
        }

        return LEGACY_EXTERNAL_APP_ROOT;
    }

    public static String normalize(String path) {
        if (path == null) {
            return null;
        }

        String normalized = replacePrefix(path, LEGACY_INTERNAL_ROOT, internalRoot());
        normalized = replacePrefix(normalized, LEGACY_DATA_ROOT, internalRoot());
        normalized = replacePrefix(normalized, LEGACY_EXTERNAL_FILES_ROOT, externalFilesRoot());
        normalized = replacePrefix(normalized, LEGACY_SDCARD_EXTERNAL_FILES_ROOT, externalFilesRoot());
        normalized = replacePrefix(normalized, LEGACY_EXTERNAL_APP_ROOT, externalAppRoot());
        return replacePrefix(normalized, LEGACY_SDCARD_EXTERNAL_APP_ROOT, externalAppRoot());
    }

    private static Context context() {
        try {
            return AwgProApplication.getContext();
        } catch (Exception ignored) {
            return null;
        }
    }

    private static String replacePrefix(String path, String oldPrefix, String newPrefix) {
        if (path.equals(oldPrefix)) {
            return newPrefix;
        }

        if (path.startsWith(oldPrefix + "/")) {
            return join(newPrefix, path.substring(oldPrefix.length() + 1));
        }

        return path;
    }

    private static String join(String root, String relativePath) {
        if (relativePath == null || relativePath.length() == 0) {
            return trimTrailingSlash(root);
        }

        String cleanRoot = trimTrailingSlash(root);
        String cleanRelativePath = trimLeadingSlash(relativePath);
        if (cleanRelativePath.length() == 0) {
            return cleanRoot;
        }

        return cleanRoot + "/" + cleanRelativePath;
    }

    private static String trimTrailingSlash(String value) {
        while (value.endsWith("/") && value.length() > 1) {
            value = value.substring(0, value.length() - 1);
        }
        return value;
    }

    private static String trimLeadingSlash(String value) {
        while (value.startsWith("/")) {
            value = value.substring(1);
        }
        return value;
    }
}
