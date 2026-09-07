package com.typheye.wgpro.ui.function.community;

import androidx.fragment.app.Fragment;

public class ContactActivity extends BaseSectionActivity {
    public static final String EXTRA_MODE = "mode";
    public static final String EXTRA_EXPECTED_COUNT = "expected_count";
    @Override protected String screenTitle() {
        return CloudListFragment.MODE_FOLLOWERS.equals(getIntent().getStringExtra(EXTRA_MODE)) ? "粉丝" : "关注";
    }
    @Override protected Fragment createContent() {
        return CloudListFragment.newContactInstance(getIntent().getStringExtra(EXTRA_MODE),
                getIntent().getIntExtra(EXTRA_EXPECTED_COUNT, -1));
    }
}
