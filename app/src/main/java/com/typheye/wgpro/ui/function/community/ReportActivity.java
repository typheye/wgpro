package com.typheye.wgpro.ui.function.community;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

public final class ReportActivity extends BaseSectionActivity {
    public static final String EXTRA_TARGET_TYPE = "target_type";
    public static final String EXTRA_TARGET_KEY = "target_key";
    public static final String EXTRA_TARGET_TITLE = "target_title";

    public static void open(Context context, String type, String key, String title) {
        context.startActivity(new Intent(context, ReportActivity.class)
                .putExtra(EXTRA_TARGET_TYPE, type)
                .putExtra(EXTRA_TARGET_KEY, key)
                .putExtra(EXTRA_TARGET_TITLE, title));
    }

    @Override protected String screenTitle() { return "举报"; }

    @Override protected Fragment createContent() {
        String type = getIntent().getStringExtra(EXTRA_TARGET_TYPE);
        ReportFragment fragment;
        if ("user".equals(type)) fragment = new UserReportFragment();
        else if ("message".equals(type)) fragment = new MessageReportFragment();
        else if ("dynamic".equals(type)) fragment = new DynamicReportFragment();
        else if ("comment".equals(type)) fragment = new CommentReportFragment();
        else if ("app".equals(type)) fragment = new AppReportFragment();
        else if ("resource".equals(type)) fragment = new ResourceReportFragment();
        else fragment = new ReportFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_TARGET_TYPE, type == null ? "" : type);
        args.putString(EXTRA_TARGET_KEY,
                getIntent().getStringExtra(EXTRA_TARGET_KEY));
        args.putString(EXTRA_TARGET_TITLE,
                getIntent().getStringExtra(EXTRA_TARGET_TITLE));
        fragment.setArguments(args);
        return fragment;
    }

    public static final class UserReportFragment extends ReportFragment {
        @Override protected String targetLabel() { return "举报用户"; }
    }

    public static final class MessageReportFragment extends ReportFragment {
        @Override protected String targetLabel() { return "举报消息"; }
    }

    public static final class DynamicReportFragment extends ReportFragment {
        @Override protected String targetLabel() { return "举报动态"; }
    }

    public static final class CommentReportFragment extends ReportFragment {
        @Override protected String targetLabel() { return "举报评论"; }
    }

    public static final class AppReportFragment extends ReportFragment {
        @Override protected String targetLabel() { return "举报应用"; }
    }

    public static final class ResourceReportFragment extends ReportFragment {
        @Override protected String targetLabel() { return "举报资源"; }
    }
}
