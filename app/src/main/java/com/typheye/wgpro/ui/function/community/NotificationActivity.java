package com.typheye.wgpro.ui.function.community;

import androidx.fragment.app.Fragment;

public class NotificationActivity extends BaseSectionActivity {
    @Override protected String screenTitle() { return "通知"; }
    @Override protected Fragment createContent() {
        return CloudListFragment.newInstance(CloudListFragment.MODE_NOTIFICATIONS);
    }
}
