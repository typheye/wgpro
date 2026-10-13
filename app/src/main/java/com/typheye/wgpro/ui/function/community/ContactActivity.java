package com.typheye.wgpro.ui.function.community;

import androidx.fragment.app.Fragment;

public class ContactActivity extends BaseSectionActivity {
    public static final String EXTRA_MODE = "mode";
    public static final String EXTRA_EXPECTED_COUNT = "expected_count";
    /** 已登录的会话：复用同一个壳，内容换成 {@link SessionListFragment}。 */
    public static final String MODE_SESSIONS = "sessions";

    @Override protected String screenTitle() {
        String mode = getIntent().getStringExtra(EXTRA_MODE);
        if (MODE_SESSIONS.equals(mode)) return "已登录的会话";
        if (CloudListFragment.MODE_BLOCKED.equals(mode)) return "已屏蔽的用户";
        return CloudListFragment.MODE_FOLLOWERS.equals(mode) ? "粉丝" : "关注";
    }

    @Override protected Fragment createContent() {
        String mode = getIntent().getStringExtra(EXTRA_MODE);
        if (MODE_SESSIONS.equals(mode)) {
            return new com.typheye.wgpro.ui.function.account.SessionListFragment();
        }
        return CloudListFragment.newContactInstance(mode,
                getIntent().getIntExtra(EXTRA_EXPECTED_COUNT, -1));
    }
}
