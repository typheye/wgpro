package com.typheye.wgpro.ui.function.community;

import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.RelativeCornerSize;
import com.typheye.wgpro.R;
import org.json.JSONObject;

public final class DynamicDetailActivity extends BaseSectionActivity {
    public static final String EXTRA_DYNAMIC_ID = "dynamic_id";
    private View authorView;
    private JSONObject dynamicInfo;
    @Override protected String screenTitle() { return ""; }
    @Override protected Fragment createContent() {
        return DynamicDetailFragment.newInstance(getIntent().getStringExtra(EXTRA_DYNAMIC_ID));
    }

    void bindAuthor(JSONObject info) {
        dynamicInfo = info;
        String uid = info.optString("uid"); String nick = info.optString("nick", "Typheye 用户");
        String avatarUrl = info.optString("avatar_url");
        androidx.appcompat.widget.Toolbar toolbar = sectionToolbar();
        toolbar.setTitle("");
        if (authorView != null) toolbar.removeView(authorView);
        LinearLayout title = new LinearLayout(this); title.setGravity(Gravity.CENTER_VERTICAL);
        android.widget.FrameLayout avatarBox = new android.widget.FrameLayout(this);
        TextView initial = new TextView(this); initial.setText(first(nick)); initial.setGravity(Gravity.CENTER);
        initial.setTextSize(15); initial.setTextColor(getColor(R.color.brand_on_soft));
        initial.setTypeface(Typeface.DEFAULT, Typeface.BOLD); initial.setVisibility(View.VISIBLE);
        GradientDrawable background = new GradientDrawable(); background.setShape(GradientDrawable.OVAL);
        background.setColor(getColor(R.color.brand_soft)); initial.setBackground(background);
        avatarBox.addView(initial, new android.widget.FrameLayout.LayoutParams(-1, -1));
        ShapeableImageView avatar = new ShapeableImageView(this); avatar.setScaleType(ImageView.ScaleType.CENTER_CROP);
        avatar.setVisibility(View.GONE); avatar.setShapeAppearanceModel(avatar.getShapeAppearanceModel()
                .toBuilder().setAllCornerSizes(new RelativeCornerSize(0.5f)).build());
        avatarBox.addView(avatar, new android.widget.FrameLayout.LayoutParams(-1, -1));
        title.addView(avatarBox, new LinearLayout.LayoutParams(dp(36), dp(36)));
        TextView name = new TextView(this); name.setText(nick); name.setTextSize(18);
        name.setTextColor(getColor(R.color.text_primary)); name.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        LinearLayout.LayoutParams np = new LinearLayout.LayoutParams(-2, -2); np.setMarginStart(dp(10));
        title.addView(name, np);
        toolbar.addView(title, new androidx.appcompat.widget.Toolbar.LayoutParams(-2, -1, Gravity.START));
        authorView = title;
        DynamicCardFactory.bindAvatar(this, uid, avatarUrl, avatar, initial);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add("更多").setIcon(R.drawable.ic_more_vertical_vector);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS); return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if ("更多".contentEquals(item.getTitle()) && dynamicInfo != null) {
            DynamicCardFactory.showActions(this, dynamicInfo, findViewById(R.id.section_container));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String first(String value) { return value == null || value.trim().isEmpty() ? "用" : value.trim().substring(0, 1); }
    private int dp(int value) { return Math.round(value * getResources().getDisplayMetrics().density); }
}
