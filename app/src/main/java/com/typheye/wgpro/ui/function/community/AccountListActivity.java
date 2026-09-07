package com.typheye.wgpro.ui.function.community;

import androidx.fragment.app.Fragment;

public class AccountListActivity extends BaseSectionActivity {
    public static final String EXTRA_MODE = "mode";
    public static final String EXTRA_TITLE = "title";
    @Override protected String screenTitle() { return getIntent().getStringExtra(EXTRA_TITLE); }
    @Override protected Fragment createContent() {
        return CloudListFragment.newInstance(getIntent().getStringExtra(EXTRA_MODE));
    }
}
