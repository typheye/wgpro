/**
 ******************************************************************************
 * @file    UriPathResolver.java
 * @author  Typheye
 * @brief   Uri-to-file-path resolution helper.
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

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;

public class UriPathResolver {

    public static String getByIntent(Context context, Intent intent) throws Exception {
        if (intent == null) {
            return null;
        }
        return getByUri(context, intent.getData());
    }

    public static String getByUri(Context context, Uri uri) throws Exception {
        String realPath = null;
        if (context == null || uri == null) {
            return null;
        }
        // Resolve common document/content/file providers used by Android file pickers.
        //如果大于4.4
        if (isKitKat()) {
            //如果是document类型uri, 则通过id获取
            if (DocumentsContract.isDocumentUri(context, uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                if (isDownloadsDocuments(uri)) {
                    Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                    String[] proj = {"_data"};
                    Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        realPath = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                        cursor.close();
                    }

                } else if (isMediaDocuments(uri)) {
                    String id = docId.split(":")[1];
                    Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://media/external/file"), Long.valueOf(id));
                    realPath = getRealPath(context, contentUri, null);
					/*
					 String type = docId.split(":")[0];
					 String id = docId.split(":")[1];
					 Uri contentUri = null;
					 String selection = "_id=" + id;
					 if (type.equals("image")) {
					 contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;	
					 } else if (type.equals("audio")) {
					 contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
					 } else if (type.equals("video")) {
					 contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
					 }
					 if (contentUri != null && selection != null) {
					 //realPath = getRealPath(context, contentUri, selection);
					 realPath=getRealPath(context,contentUri,null);
					 }
					 */
                } else if (isExternalStorageDocuments(uri)) {
                    return getRootPath() + "/" + docId.split(":")[1];
                }

            } else if (isSchemeContent(uri)) {
                if (isRE(uri) || isEstrongs(uri)) {
                    realPath = uri.getPath();
                } else if (isQQBrowserFileProvider(uri)) {
                    realPath = getRootPath() + uri.getPath();
                } else if (isFileExplorerMyProvider(uri)) {
                    String path = uri.getPath();
                    if (path != null) {
                        realPath = path.replaceFirst("/external_files", getRootPath());
                    }
                } else {
                    realPath = getRealPath(context, uri, null);
                }
            } else if (isSchemeFile(uri)) {
                realPath = uri.getPath();
            }
        } else {
            //小于4.4
            realPath = getRealPath(context, uri, null);
        }
        return realPath;
    }

    private static String getRealPath(Context context, Uri uri, String selection) {

        String path = null;
        // Some providers no longer expose "_data"; return null instead of crashing in that case.
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = context.getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    int dataIndex = cursor.getColumnIndex("_data");
                    if (dataIndex >= 0) {
                        path = cursor.getString(dataIndex);
                    }
                }
            } finally {
                cursor.close();
            }
        }
        return path;
    }

    private static boolean isRE(Uri uri) {
        ///storage/emulated/0/log.txt
        // content
        return "com.speedsoftware.rootexplorer.content".equals(uri.getAuthority());
    }

    private static boolean isEstrongs(Uri uri) {
        ///storage/emulated/0/log.txt
        // content
        return "com.estrongs.files".equals(uri.getAuthority());
    }

    private static boolean isMIUIGallery(Uri uri) {
        // /raw//storage/emulated/0/DCIM/Camera/IMG_20200318_080535.jpg
        // content
        return "com.miui.gallery.open".equals(uri.getAuthority());
    }

    private static boolean isMedia(Uri uri) {
        // /external/audio/media/69767
        // /external/images/media/86837
        // /external/file/130685
        // content
        return "media".equals(uri.getAuthority());
    }

    private static boolean isQQBrowserFileProvider(Uri uri) {
        // /QQBrowser/log.txt
        // content
        return "com.tencent.mtt.fileprovider".equals(uri.getAuthority());
    }

    private static boolean isFileExplorerMyProvider(Uri uri) {
        // /external_files/netease/cloudmusic/Music/许嵩 - 幻听.mp3
        // content
        return "com.android.fileexplorer.myprovider".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocuments(Uri uri) {
        // /document/503
        // documentUri
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocuments(Uri uri) {
        // /document/audio:69767
        // /document/video:126419
        // /document/image:130682
        // documentUri
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static boolean isExternalStorageDocuments(Uri uri) {
        // /document/primary:{文件相对路径}
        // documentUri
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    private static boolean isSchemeContent(Uri uri) {
        return ContentResolver.SCHEME_CONTENT.equals(uri.getScheme());
    }

    private static boolean isSchemeFile(Uri uri) {
        return ContentResolver.SCHEME_FILE.equals(uri.getScheme());
    }

    private static String getRootPath() {
        return Environment.getExternalStorageDirectory().getPath();
    }
}
