package com.typheye.wgpro.ui.function.community;

import androidx.fragment.app.Fragment;

public class ContactActivity extends BaseSectionActivity {
    public static final String EXTRA_MODE = "mode";
    public static final String EXTRA_EXPECTED_COUNT = "expected_count";
    @Override protected String screenTitle() {
        String mode = getIntent().getStringExtra(EXTRA_MODE);
        if (CloudListFragment.MODE_BLOCKED.equals(mode)) return "已屏蔽的用户";
        return CloudListFragment.MODE_FOLLOWERS.equals(mode) ? "粉丝" : "关注";
    }
    @Override protected Fragment createContent() {
        return CloudListFragment.newContactInstance(getIntent().getStringExtra(EXTRA_MODE),
                getIntent().getIntExtra(EXTRA_EXPECTED_COUNT, -1));
    }
}
