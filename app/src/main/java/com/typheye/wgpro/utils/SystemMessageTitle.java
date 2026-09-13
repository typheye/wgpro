package com.typheye.wgpro.utils;

import androidx.annotation.Nullable;

import java.util.regex.Pattern;

/**
 * 系统消息标题规范化。
 *
 * <p>举报相关通知的历史数据（以及管理端旧版本写入的记录）标题里带「（编号 #21）」，
 * 站内卡片、Android 通知必须用同一套规则去掉，编号只在举报详情页里展示。
 */
public final class SystemMessageTitle {
    private static final Pattern REPORT_NUMBER =
            Pattern.compile("[（(]\\s*编号\\s*#?\\s*[0-9]+\\s*[)）]\\s*$");

    private SystemMessageTitle() {
    }

    /** 去掉标题末尾的「（编号 #N）」，其余内容原样保留。 */
    public static String clean(@Nullable String title) {
        if (title == null || title.isEmpty()) return "";
        return REPORT_NUMBER.matcher(title).replaceAll("").trim();
    }
}
