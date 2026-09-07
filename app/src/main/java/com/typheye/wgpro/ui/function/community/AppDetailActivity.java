package com.typheye.wgpro.ui.function.community;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;
import com.typheye.wgpro.R;

public final class AppDetailActivity extends BaseSectionActivity {
    public static final String EXTRA_APP_JSON = "app_json";
    @Override protected String screenTitle() { return ""; }
    @Override protected Fragment createContent() {
        return AppDetailFragment.newInstance(getIntent().getStringExtra(EXTRA_APP_JSON));
    }
    @Override protected void onCreate(@Nullable Bundle state) {
        super.onCreate(state);
        int background = getColor(R.color.surface_page);
        getWindow().setStatusBarColor(background);
        findViewById(R.id.section_app_bar).setBackgroundColor(background);
        sectionToolbar().setBackgroundColor(background);
        boolean light = (getResources().getConfiguration().uiMode
                & android.content.res.Configuration.UI_MODE_NIGHT_MASK)
                != android.content.res.Configuration.UI_MODE_NIGHT_YES;
        new WindowInsetsControllerCompat(getWindow(), getWindow().getDecorView())
                .setAppearanceLightStatusBars(light);
    }
}
