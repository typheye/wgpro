package com.typheye.wgpro.ui.function.community;

import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.typheye.wgpro.R;

public final class DynamicDetailActivity extends BaseSectionActivity {
    public static final String EXTRA_DYNAMIC_ID = "dynamic_id";
    private View authorView;
    @Override protected String screenTitle() { return ""; }
    @Override protected Fragment createContent() {
        return DynamicDetailFragment.newInstance(getIntent().getStringExtra(EXTRA_DYNAMIC_ID));
    }

    void bindAuthor(String uid, String nick, String avatarUrl) {
        androidx.appcompat.widget.Toolbar toolbar = sectionToolbar();
        toolbar.setTitle("");
        if (authorView != null) toolbar.removeView(authorView);
        LinearLayout title = new LinearLayout(this); title.setGravity(Gravity.CENTER_VERTICAL);
        MaterialCardView avatarBox = new MaterialCardView(this); avatarBox.setRadius(dp(18));
        avatarBox.setCardElevation(0); avatarBox.setStrokeWidth(0);
        avatarBox.setCardBackgroundColor(getColor(R.color.brand_soft)); avatarBox.setClipToOutline(true);
        TextView initial = new TextView(this); initial.setText(first(nick)); initial.setGravity(Gravity.CENTER);
        initial.setTextSize(15); initial.setTextColor(getColor(R.color.brand_on_soft));
        initial.setTypeface(Typeface.DEFAULT, Typeface.BOLD); initial.setVisibility(View.VISIBLE);
        avatarBox.addView(initial, new MaterialCardView.LayoutParams(-1, -1));
        ShapeableImageView avatar = new ShapeableImageView(this); avatar.setScaleType(ImageView.ScaleType.CENTER_CROP);
        avatar.setVisibility(View.GONE); avatar.setShapeAppearanceModel(avatar.getShapeAppearanceModel()
                .toBuilder().setAllCornerSizes(1000f).build());
        avatarBox.addView(avatar, new MaterialCardView.LayoutParams(-1, -1));
        title.addView(avatarBox, new LinearLayout.LayoutParams(dp(36), dp(36)));
        TextView name = new TextView(this); name.setText(nick); name.setTextSize(18);
        name.setTextColor(getColor(R.color.text_primary)); name.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        LinearLayout.LayoutParams np = new LinearLayout.LayoutParams(-2, -2); np.setMarginStart(dp(10));
        title.addView(name, np);
        toolbar.addView(title, new androidx.appcompat.widget.Toolbar.LayoutParams(-2, -1, Gravity.START));
        authorView = title;
        DynamicCardFactory.bindAvatar(this, uid, avatarUrl, avatar, initial);
    }

    private String first(String value) { return value == null || value.trim().isEmpty() ? "用" : value.trim().substring(0, 1); }
    private int dp(int value) { return Math.round(value * getResources().getDisplayMetrics().density); }
}
