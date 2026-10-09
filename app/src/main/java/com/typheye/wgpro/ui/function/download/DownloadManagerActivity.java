package com.typheye.wgpro.ui.function.download;

import androidx.fragment.app.Fragment;

import com.typheye.wgpro.ui.function.community.BaseSectionActivity;

/** 下载管理页：应用栏子标签「下载中 / 已完成」。 */
public final class DownloadManagerActivity extends BaseSectionActivity {
    @Override
    protected String screenTitle() {
        return "下载管理";
    }

    @Override
    protected Fragment createContent() {
        return new DownloadManagerFragment();
    }
}
