package com.typheye.wgpro.ui.main.mainFragments;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.BadgeFactory;
import com.typheye.wgpro.ui.function.account.AccMangerActivity;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.ui.function.account.UserDetailActivity;
import com.typheye.wgpro.ui.function.community.AccountListActivity;
import com.typheye.wgpro.ui.function.community.CloudListFragment;
import com.typheye.wgpro.ui.function.community.CommunityWeb;
import com.typheye.wgpro.ui.function.community.ContactActivity;
import com.typheye.wgpro.utils.ImageCache;
import com.typheye.wgpro.utils.tAccUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class AccountFragment extends Fragment {
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private tAccUtils accUtils;
    private CardView account_card_loginless;
    private Button account_btn_sign_in;
    private Button account_btn_sign_up;
    private LinearLayout account_linear_logined;
    private View account_btn_goto_help;
    private TextView account_text_usr_icon;
    private TextView account_text_usr_uid;
    private TextView account_text_usr_nick;
    private TextView account_text_usr_shuo;
    private ImageView account_image_usr_icon;
    private View account_content;
    private View account_stats_card;
    private TextView accountIdentityValue;
    private TextView activityCount;
    private TextView followingCount;
    private TextView followersCount;
    private boolean profileRequestInFlight;
    private SwipeRefreshLayout accountRefresh;
    private View accountPageContent;
    private Context appContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appContext = requireContext().getApplicationContext();
        accUtils = new tAccUtils(appContext);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        accountRefresh = view.findViewById(R.id.account_refresh);
        accountPageContent = view.findViewById(R.id.account_page_content);
        accountRefresh.setOnRefreshListener(() -> {
            if (getActivity() instanceof com.typheye.wgpro.ui.main.MainActivity) {
                ((com.typheye.wgpro.ui.main.MainActivity) getActivity()).refreshAccountFromUser();
            } else {
                refreshAccountUi(true);
            }
            accountRefresh.postDelayed(() -> accountRefresh.setRefreshing(false), 10000L);
        });
        // 初始化所有控件
        account_card_loginless = view.findViewById(R.id.card_loginless);
        account_linear_logined = view.findViewById(R.id.linear_logined);
        account_btn_sign_in = view.findViewById(R.id.btn_account_sign_in);
        account_btn_sign_up = view.findViewById(R.id.btn_account_sign_up);
        account_btn_goto_help = view.findViewById(R.id.btn_account_goto_help);
        account_text_usr_icon = view.findViewById(R.id.text_usr_icon);
        account_text_usr_uid = view.findViewById(R.id.text_usr_uid);
        account_text_usr_nick = view.findViewById(R.id.text_usr_nick);
        account_text_usr_shuo = view.findViewById(R.id.text_usr_shuo);
        account_image_usr_icon = view.findViewById(R.id.image_usr_icon);
        account_content = view.findViewById(R.id.account_content);
        account_stats_card = view.findViewById(R.id.account_stats_card);
        // 「我的」页不再直接显示积分数：点小图标弹底部卡片看明细
        view.findViewById(R.id.account_points_block)
                .setOnClickListener(v -> showPointsSheet());
        setGroupRowTitle(view, R.id.account_history, "浏览历史");
        setGroupRowTitle(view, R.id.account_favorites, "我的收藏");
        setGroupRowTitle(view, R.id.account_starred_apps, "星标应用");
        setGroupRowTitle(view, R.id.account_resources, "星标资源");
        setGroupRowTitle(view, R.id.account_identity, "社区身份");
        accountIdentityValue = view.findViewById(R.id.account_identity)
                .findViewById(R.id.account_row_value);
        accountIdentityValue.setText("");
        accountIdentityValue.setVisibility(View.GONE);
        activityCount = view.findViewById(R.id.account_activity_count);
        followingCount = view.findViewById(R.id.account_following_count);
        followersCount = view.findViewById(R.id.account_followers_count);
        setGroupRowTitle(view, R.id.account_creator_center, "创作中心");
        view.findViewById(R.id.profile_card).setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), UserDetailActivity.class);
            startActivity(intent);
        });

        updateUI();
        setupListeners(view);
        return view;
    }

    private void setupListeners(View root) {
        account_btn_sign_in.setOnClickListener(v -> tAccUtils.startBrowserLogin(requireActivity(), new tAccUtils.BrowserLoginCallback() {
            @Override
            public void onLoginSucceeded() {
                onAccountChanged();
            }

            @Override
            public void onLoginFailed(String message) {
                if (!isAdded()) return;
                new WGProAlertDialogBuilder(requireContext())
                        .setTitle("登录失败")
                        .setMessage(message)
                        .setPositiveButton("完成", null)
                        .show();
            }
        }));

        account_btn_sign_up.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WebActivity.class);
            intent.putExtra("URL", "https://service.typheye.cn/site/user/center/register/");
            startActivity(intent);
        });

        // 点整张资料卡 = 进自己的用户详情页（和看别人主页一致；编辑入口在详情页里）
        root.findViewById(R.id.profile_card).setOnClickListener(v -> openUserDetail());

        account_btn_goto_help.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), WebActivity.class);
            intent.putExtra("URL", "https://account.typheye.cn/?tab=security");
            startActivity(intent);
        });

        root.findViewById(R.id.account_identity).setOnClickListener(v ->
                CommunityWeb.openIdentity(requireContext()));
        root.findViewById(R.id.account_activity).setOnClickListener(v ->
                openAccountList("我的动态", CloudListFragment.MODE_ACTIVITY));
        root.findViewById(R.id.account_following).setOnClickListener(v ->
                openContacts(CloudListFragment.MODE_FOLLOWING, numberOf(followingCount)));
        root.findViewById(R.id.account_followers).setOnClickListener(v ->
                openContacts(CloudListFragment.MODE_FOLLOWERS, numberOf(followersCount)));
        root.findViewById(R.id.account_history).setOnClickListener(v -> openAccountList("浏览历史", CloudListFragment.MODE_HISTORY));
        root.findViewById(R.id.account_favorites).setOnClickListener(v -> openAccountList("我的收藏", CloudListFragment.MODE_COLLECTION_DYNAMIC));
        root.findViewById(R.id.account_starred_apps).setOnClickListener(v -> openAccountList("星标应用", CloudListFragment.MODE_COLLECTION_APP));
        root.findViewById(R.id.account_resources).setOnClickListener(v -> openAccountList("星标资源", CloudListFragment.MODE_COLLECTION_RESOURCE));
        root.findViewById(R.id.account_creator_center).setOnClickListener(v ->
                CommunityWeb.openCreatorCenter(requireContext()));
    }

    /**
     * 绿卡里的一列：大号数值 + 小字标签（图标由 {@link PointsCards} 作为浅痕迹水印画在底层）。
     *
     * <p>数值/标签字号对齐「我的」页的「动态 / 关注 / 粉丝」卡：数值 17sp 加粗、标签 13sp，
     * 且保持默认字体内边距（不调 {@code setIncludeFontPadding(false)}）。两行都锁定单行：
     * 位数变多也不会换行、不会把卡片撑开或挤到分割线。</p>
     */
    private PointsColumnViews pointsStat(android.content.Context context, int iconRes,
                                         String value, String label) {
        TextView number = new TextView(context);
        // 占位符与「动态 / 关注 / 粉丝」卡一致
        number.setText(value == null || value.isEmpty()
                ? com.typheye.wgpro.ui.widget.Points.PLACEHOLDER : value);
        number.setTextSize(17);
        number.setTypeface(null, android.graphics.Typeface.BOLD);
        number.setTextColor(0xFFFFFFFF);
        number.setSingleLine(true);
        number.setEllipsize(android.text.TextUtils.TruncateAt.END);
        TextView name = new TextView(context);
        name.setText(label);
        name.setTextSize(13);
        name.setTextColor(0xCCFFFFFF);
        name.setSingleLine(true);
        name.setEllipsize(android.text.TextUtils.TruncateAt.END);
        // 用一层壳把整列包成一个子视图：父容器（PointsCards）按 getChildAt 定位两列，
        // 直接返回 PointsColumn 也能用，但那样会把它内部的 TextView 一并暴露成父容器的
        // 直接子视图，索引就乱了。
        PointsColumn column = new PointsColumn(context, number, name);
        android.widget.FrameLayout holder = new android.widget.FrameLayout(context);
        holder.addView(column, new android.widget.FrameLayout.LayoutParams(-1, -2));
        holder.setTag(iconRes); // 图标留给 PointsCards 当水印画
        return new PointsColumnViews(holder, number);
    }

    /** 一列的引用：壳视图（PointsCards 当子视图用）+ 数值 TextView（积分回来后就地刷新）。 */
    private static final class PointsColumnViews {
        final View view;
        final TextView value;

        PointsColumnViews(View view, TextView value) {
            this.view = view;
            this.value = value;
        }
    }

    /**
     * 一列 = 数值 + 标签，整组垂直居中（图标由 {@link PointsCards} 画成底层水印）。
     */
    private final class PointsColumn extends android.view.ViewGroup {
        /** 数值与标签之间的排版间距（dp，跟参考卡一致）。 */
        final int LABEL_TOP_DP = 4;

        private final TextView number;
        private final TextView label;
        private final float density = getResources().getDisplayMetrics().density;
        private int labelTop;

        PointsColumn(android.content.Context context, TextView number, TextView label) {
            super(context);
            this.number = number;
            this.label = label;
            addView(number);
            addView(label);
        }

        private int dp(float value) { return Math.round(value * density); }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int heightLimit = MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED
                    ? Integer.MAX_VALUE : MeasureSpec.getSize(heightMeasureSpec);

            // 宽度用 UNSPECIFIED 让其按内容自适应：父容器给满宽时文字不会自己居中，
            // 干脆自己量出来再手动居中摆（见 onLayout）。
            number.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                    MeasureSpec.makeMeasureSpec(heightLimit, MeasureSpec.UNSPECIFIED));
            label.measure(MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST),
                    MeasureSpec.makeMeasureSpec(heightLimit, MeasureSpec.UNSPECIFIED));

            labelTop = dp(LABEL_TOP_DP);
            int needed = number.getMeasuredHeight() + labelTop + label.getMeasuredHeight();
            setMeasuredDimension(width, Math.min(heightLimit, needed));
        }

        /** 内容整组高度（数值 + 标签 + 间距）。 */
        int contentHeight() {
            return number.getMeasuredHeight() + labelTop + label.getMeasuredHeight();
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            int width = r - l;
            // 自己按内容居中：不依赖父容器分配的高度（父容器分配时会按自己的规则拉伸/压缩子视图）
            int top = Math.max(0, ((b - t) - contentHeight()) / 2);

            int numberLeft = (width - number.getMeasuredWidth()) / 2;
            number.layout(numberLeft, top, numberLeft + number.getMeasuredWidth(),
                    top + number.getMeasuredHeight());
            int y = top + number.getMeasuredHeight() + labelTop;
            int labelLeft = (width - label.getMeasuredWidth()) / 2;
            label.layout(labelLeft, y, labelLeft + label.getMeasuredWidth(),
                    y + label.getMeasuredHeight());
        }
    }

    /**
     * 整张积分卡：绿底圆角 + 左右两列（中间 1dp 分割线），自己管留白与高度。
     *
     * <p>高度/内边距对齐「我的」页的「动态 / 关注 / 粉丝」卡
     * （{@code account_stats_card}）：卡高 {@link #CARD_DP}、上下内边距 {@link #PAD_DP}，
     * 内容（数值 + 标签）在其中垂直居中。</p>
     */
    private final class PointsCards extends android.view.ViewGroup {
        /** 卡片高度（dp）：与「动态 / 关注 / 粉丝」卡一致（80dp）。 */
        final int CARD_DP = 80;
        /** 卡片上下内边距（dp）：与参考卡一致（80 - 内容 44 → 上下各 18）。 */
        final int PAD_DP = 18;
        /** 中间分割线高度（dp）。 */
        final int DIVIDER_DP = 60;
        /** 中间分割线颜色（白 20%）。 */
        final int DIVIDER_COLOR = 0x33FFFFFF;
        /** 水印图标占卡片高度的比例：放到角上时不宜过大，否则会被卡片圆角切掉。 */
        final float WATERMARK_RATIO = 0.5f;
        /** 水印基准尺寸（dp）：只作为上限，真正尺寸 = 卡高 × {@link #WATERMARK_RATIO}。 */
        final int WATERMARK_BASE_DP = 126;
        /** 水印距卡片角的内收距离（dp）。 */
        final int WATERMARK_INSET_DP = 8;
        /** 水印颜色 = 卡片底色加深；加深系数（0.42 ≈ 比底色暗一档）。 */
        final float WATERMARK_DARKEN = 0.42f;
        /** 水印不透明度 0~255（0.5 = 50%）。 */
        final int WATERMARK_ALPHA = 128;

        private final android.graphics.drawable.GradientDrawable cardBg;
        private final android.graphics.Paint dividerPaint = new android.graphics.Paint();
        private final android.graphics.Paint watermarkPaint = new android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG);
        private final android.graphics.drawable.Drawable[] watermarks = new android.graphics.drawable.Drawable[2];
        private final float density = getResources().getDisplayMetrics().density;
        private final int dividerWidth;
        private final int watermarkSize;
        private int dividerTop, dividerBottom;

        PointsCards(android.content.Context context, View left, View right) {
            super(context);
            setWillNotDraw(false);
            cardBg = new android.graphics.drawable.GradientDrawable();
            cardBg.setColor(context.getColor(R.color.points_card_bg));
            cardBg.setCornerRadius(Math.round(18 * density)); // 与弹窗里的分组卡圆角一致
            dividerWidth = Math.max(1, Math.round(density));
            dividerPaint.setColor(DIVIDER_COLOR);
            // 水印画笔：卡片底色加深 + 低透明度（浅痕迹）
            watermarkPaint.setColor(darken(context.getColor(R.color.points_card_bg)));
            watermarkPaint.setAlpha(WATERMARK_ALPHA);
            // 水印基准尺寸（上限，真正尺寸由 WATERMARK_RATIO × 卡高决定）
            watermarkSize = dp(WATERMARK_BASE_DP);
            addView(left);
            addView(right);
        }

        /** 把颜色整体乘一个系数（越小越暗），保持色相不变。 */
        private int darken(int color) {
            return android.graphics.Color.argb(
                    android.graphics.Color.alpha(color),
                    Math.round(android.graphics.Color.red(color) * WATERMARK_DARKEN),
                    Math.round(android.graphics.Color.green(color) * WATERMARK_DARKEN),
                    Math.round(android.graphics.Color.blue(color) * WATERMARK_DARKEN));
        }

        private int dp(float value) { return Math.round(value * density); }

        /** 取（并按卡高限制）第 index 列的水印图标。 */
        private android.graphics.drawable.Drawable watermark(int index) {
            if (index < 0 || index >= getChildCount()) return null;
            Object tag = getChildAt(index).getTag();
            if (!(tag instanceof Integer)) return null;
            if (watermarks[index] == null) {
                android.graphics.drawable.Drawable d = getContext().getDrawable((Integer) tag);
                if (d != null) d.mutate();
                watermarks[index] = d;
            }
            return watermarks[index];
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int columnWidth = Math.max(0, (width - dividerWidth) / 2);
            int childSpec = MeasureSpec.makeMeasureSpec(columnWidth, MeasureSpec.EXACTLY);

            int contentHeight = 0;
            for (int i = 0; i < getChildCount(); i++) {
                View child = getChildAt(i);
                child.measure(childSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
                contentHeight = Math.max(contentHeight, child.getMeasuredHeight());
            }
            // 高度写死 = 参考卡高度；内容不够就靠 PAD_DP 的上下内边距撑着（内容居中）
            int wanted = Math.max(dp(CARD_DP), contentHeight + dp(PAD_DP) * 2);
            int heightLimit = MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.UNSPECIFIED
                    ? Integer.MAX_VALUE : MeasureSpec.getSize(heightMeasureSpec);
            setMeasuredDimension(width, Math.min(heightLimit, wanted));
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            int columnWidth = Math.max(0, ((r - l) - dividerWidth) / 2);
            // 内容在卡内垂直居中（两列自己也会再居中一次，这里给足高度即可）
            int top = dp(PAD_DP);
            int bottom = (b - t) - dp(PAD_DP);
            getChildAt(0).layout(0, top, columnWidth, bottom);
            getChildAt(1).layout(columnWidth + dividerWidth, top, columnWidth * 2 + dividerWidth, bottom);

            int dividerHeight = Math.min(dp(DIVIDER_DP), bottom - top);
            int centerY = (top + bottom) / 2;
            dividerTop = centerY - dividerHeight / 2;
            dividerBottom = dividerTop + dividerHeight;
        }

        @Override
        protected void onDraw(android.graphics.Canvas canvas) {
            cardBg.setBounds(0, 0, getWidth(), getHeight());
            cardBg.draw(canvas);

            // 水印各贴外侧下角：左半 → 左下角；右半 → 右下角
            int size = Math.min(watermarkSize, Math.round(getHeight() * WATERMARK_RATIO));
            int columnWidth = Math.max(0, (getWidth() - dividerWidth) / 2);
            int inset = dp(WATERMARK_INSET_DP);
            for (int i = 0; i < 2; i++) {
                android.graphics.drawable.Drawable d = watermark(i);
                if (d == null) continue;
                int colLeft = i == 0 ? 0 : columnWidth + dividerWidth;
                int left = i == 0
                        ? colLeft + inset                             // 左半：左下角
                        : colLeft + columnWidth - size - inset;       // 右半：右下角
                int top = Math.max(0, getHeight() - size - inset);
                d.setBounds(left, top, left + size, top + size);
                d.setTint(watermarkPaint.getColor());
                d.setAlpha(WATERMARK_ALPHA);
                d.draw(canvas);
            }

            int x = (getWidth() - dividerWidth) / 2;
            canvas.drawRect(x, dividerTop, x + dividerWidth, dividerBottom, dividerPaint);
        }
    }

    /** 积分卡片：底部弹窗展示（绿底标签卡「可用积分 ｜ 赠送积分」+ 明细/商城/福利入口）。 */
    private void showPointsSheet() {
        android.content.Context context = requireContext();

        // 先用本地缓存渲染（避免闪占位符），网络回来后就地刷新
        com.typheye.wgpro.ui.widget.Points.warm(context);

        // 一整张绿卡：左右两列 + 中间分割线，留白/高度由 PointsCards 自己控制。
        PointsColumnViews balanceColumn = pointsStat(context, R.drawable.ic_points_vector,
                com.typheye.wgpro.ui.widget.Points.display(
                        com.typheye.wgpro.ui.widget.Points.balance), "可用积分");
        PointsColumnViews giftColumn = pointsStat(context, R.drawable.ic_gift_vector,
                com.typheye.wgpro.ui.widget.Points.display(
                        com.typheye.wgpro.ui.widget.Points.gift), "赠送积分");
        PointsCards stats = new PointsCards(context, balanceColumn.view, giftColumn.view);

        // 每次打开都强制拉一次最新积分；回来后就地刷新两个数字（弹窗文本是静态的）
        com.typheye.wgpro.ui.widget.Points.refresh(context, true, ok -> {
            balanceColumn.value.setText(com.typheye.wgpro.ui.widget.Points.display(
                    com.typheye.wgpro.ui.widget.Points.balance));
            giftColumn.value.setText(com.typheye.wgpro.ui.widget.Points.display(
                    com.typheye.wgpro.ui.widget.Points.gift));
        });

        LinearLayout box = new LinearLayout(context);
        box.setOrientation(LinearLayout.VERTICAL);
        // 底部留 6dp：分组卡自己还有 18dp 上边距，合计 24dp
        box.setPadding(0, dp(4), 0, dp(6));
        box.addView(stats, new LinearLayout.LayoutParams(-1, -2));

        // 网页单点令牌：打开积分网页时带上（拿不到就裸开，网页侧会提示重新登录）
        com.typheye.wgpro.ui.widget.Points.refreshWebToken(context);

        new com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder(context)
                .setTitle("积分")
                .setView(box)
                .setItems(new CharSequence[]{"交易明细", "积分商城", "福利优惠"}, (dialog, which) -> {
                    // 三个入口都打开对应网页，链接格式见 _guide/points-notes.md
                    String path = which == 0 ? "ledger" : which == 1 ? "mall" : "welfare";
                    openPointsPage(path);
                })
                .setNegativeButton("关闭", null).show();
    }

    /** 打开积分相关网页（交易明细 / 积分商城 / 福利优惠）。 */
    private void openPointsPage(String path) {
        String uid = new tAccUtils(requireContext().getApplicationContext()).getUid();
        String url = com.typheye.wgpro.utils.tPointsUtils.webUrl(
                path, uid, com.typheye.wgpro.ui.widget.Points.webToken);
        startActivity(new Intent(requireContext(), WebActivity.class).putExtra("URL", url));
    }

    private int dp(float value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    private void setGroupRowTitle(View root, int rowId, String title) {
        View row = root.findViewById(rowId);
        TextView label = row == null ? null : row.findViewById(R.id.account_row_title);
        if (label != null) label.setText(title);
    }

    private void openUserDetail() {
        startActivity(new Intent(requireContext(), UserDetailActivity.class));
    }

    private int numberOf(TextView view) {
        try { return Integer.parseInt(view.getText().toString()); }
        catch (Exception ignored) { return -1; }
    }

    private void openContacts(String mode, int expectedCount) {
        startActivity(new Intent(requireContext(), ContactActivity.class)
                .putExtra(ContactActivity.EXTRA_MODE, mode)
                .putExtra(ContactActivity.EXTRA_EXPECTED_COUNT, expectedCount));
    }

    private void openAccountList(String title, String mode) {
        startActivity(new Intent(requireContext(), AccountListActivity.class)
                .putExtra(AccountListActivity.EXTRA_TITLE, title)
                .putExtra(AccountListActivity.EXTRA_MODE, mode));
    }

    private void showCloudList(String title, String action, @Nullable String targetType) {
        Map<String, String> query = new LinkedHashMap<>();
        query.put("page", "1");
        query.put("size", "20");
        if (targetType != null) query.put("target_type", targetType);
        accUtils.getV2Json(action, query, true, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                mainHandler.post(() -> {
                    if (!isAdded()) return;
                    JSONArray items = json.optJSONArray("items");
                    String message = formatCloudItems(items);
                    new WGProAlertDialogBuilder(requireContext()).setTitle(title).setMessage(message)
                            .setNegativeButton("关闭", null).show();
                });
            }

            @Override public void onError(int code, @NonNull String message) {
                mainHandler.post(() -> {
                    if (!isAdded()) return;
                    new WGProAlertDialogBuilder(requireContext()).setTitle("加载失败")
                            .setMessage(message).setNegativeButton("关闭", null).show();
                });
            }
        });
    }

    private String formatCloudItems(@Nullable JSONArray items) {
        if (items == null || items.length() == 0) return "这里暂时还没有内容。";
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < items.length(); index++) {
            JSONObject item = items.optJSONObject(index);
            if (item == null) continue;
            JSONObject target = item.optJSONObject("target");
            if (target == null) target = item;
            String label = target.optString("title", target.optString("name",
                    target.optString("content", ""))).trim();
            if (label.isEmpty()) {
                String type = item.optString("target_type", "内容");
                String key = item.optString("target_key", "");
                label = key.isEmpty() ? "未命名内容" : type + "  " + key;
            }
            if (label.length() > 44) label = label.substring(0, 44) + "…";
            if (result.length() > 0) result.append("\n\n");
            result.append(label);
            String time = item.optString("created_at", item.optString("last_viewed_at", ""));
            if (!time.isEmpty()) result.append("\n").append(time);
        }
        return result.length() == 0 ? "这里暂时还没有内容。" : result.toString();
    }

    @SuppressLint("SetTextI18n")
    private void updateUI() {
        if (!isAdded() || getView() == null) return;
        accountPageContent.setVisibility(View.VISIBLE);
        if (accUtils.isLogin()) {
            viewServicesVisibility(View.VISIBLE);
            account_card_loginless.setVisibility(View.GONE);
            account_linear_logined.setVisibility(View.VISIBLE);
            account_stats_card.setVisibility(View.VISIBLE);
            account_content.setVisibility(View.VISIBLE);
            account_btn_goto_help.setVisibility(View.VISIBLE);

            String uid = accUtils.getUid();
            String nick = accUtils.getNick();
            String shuo = accUtils.getShuo();

            account_text_usr_uid.setText(uid != null && !uid.isEmpty() ? "UID " + uid : "UID 未知");
            account_text_usr_nick.setText(nick != null && !nick.isEmpty() ? nick : "用户");

            String cleanShuo = shuo != null ? shuo.trim() : "";
            account_text_usr_shuo.setText(cleanShuo.isEmpty() ? "这个人还没有简介呢~" : cleanShuo);

            // ====== 关键修复：直接加载头像，不隐藏头像区域 ======
            loadAvatarFromCache(uid);
            loadPublicProfile();
        } else {
            profileRequestInFlight = false;
            account_card_loginless.setVisibility(View.VISIBLE);
            account_linear_logined.setVisibility(View.GONE);
            account_stats_card.setVisibility(View.GONE);
            account_content.setVisibility(View.GONE);
            account_btn_goto_help.setVisibility(View.GONE);
            viewServicesVisibility(View.GONE);
            account_text_usr_uid.setText("");
            account_text_usr_nick.setText("");
            account_text_usr_shuo.setText("");
            // 未登录状态直接显示文字头像
            account_text_usr_icon.setText("U");
            account_text_usr_icon.setVisibility(View.VISIBLE);
            account_image_usr_icon.setVisibility(View.GONE);
        }
    }

    private void loadPublicProfile() {
        if (profileRequestInFlight || !isAdded()) return;
        String uid = accUtils.getUid();
        if (uid == null || uid.isEmpty()) return;
        profileRequestInFlight = true;
        accUtils.getV2Json("user_profile2",
                Collections.singletonMap("target_uid", uid), true,
                new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        mainHandler.post(() -> {
                            profileRequestInFlight = false;
                            if (!isAdded() || getView() == null || !accUtils.isLogin()) return;
                            JSONObject info = json.optJSONObject("info");
                            if (info == null) return;
                            BadgeFactory.bind(requireContext(), info,
                                    getView().findViewById(R.id.account_avatar_outer),
                                    com.typheye.wgpro.ui.widget.WGProColors.of(requireContext(), R.color.surface_page));
                            activityCount.setText(String.valueOf(info.optInt("dynamic_count", 0)));
                            followingCount.setText(String.valueOf(info.optInt("following_count", 0)));
                            followersCount.setText(String.valueOf(info.optInt("follower_count", 0)));
                            String role = info.optString("role_name", "").trim();
                            accountIdentityValue.setText(role);
                            accountIdentityValue.setVisibility(role.isEmpty() ? View.GONE : View.VISIBLE);
                            // 同一网络时机把积分也拿回来
                            com.typheye.wgpro.ui.widget.Points.refresh(requireContext(), false, null);
                        });
                    }

                    @Override public void onError(int statusCode, @NonNull String message) {
                        mainHandler.post(() -> {
                            profileRequestInFlight = false;
                            if (isAdded() && statusCode == 401) updateUI();
                        });
                    }
                });
    }

    public void refreshAccountUi() {
        refreshAccountUi(false);
    }

    public void refreshAccountUi(boolean refreshAvatar) {
        if (isAdded()) updateUI();
        if (refreshAvatar && isAdded() && accUtils.isLogin()) checkAvatarAndUpdate();
        if (accountRefresh != null) accountRefresh.setRefreshing(false);
    }

    private void onAccountChanged() {
        if (!isAdded()) return;
        updateUI();
        if (accUtils.isLogin()) checkAvatarAndUpdate();
        requireActivity().invalidateOptionsMenu();
    }

    private void viewServicesVisibility(int visibility) {
        if (getView() != null) {
            View services = getView().findViewById(R.id.account_services_container);
            if (services != null) services.setVisibility(visibility);
        }
    }

    // ====== 核心修复：本地头像存在时直接显示，不显示文字头像 ======
    private void loadAvatarFromCache(String uid) {
        if (uid == null || uid.isEmpty()) {
            showDefaultTextAvatar();
            return;
        }

        File cacheDir = appContext.getFilesDir();
        File cacheFile = new File(cacheDir, "avatar_" + uid + ".jpg");

        // 1. 本地头像存在且有效 → 直接显示图片头像
        if (cacheFile.exists() && isValidAvatarFile(cacheFile)) {
            try {
                Bitmap bitmap = BitmapFactory.decodeFile(cacheFile.getAbsolutePath());
                if (bitmap != null && !bitmap.isRecycled()) {
                    new Handler(Looper.getMainLooper()).post(() -> {
                        if (!isAdded() || getView() == null) return;
                        account_image_usr_icon.setImageBitmap(bitmap);
                        account_image_usr_icon.setVisibility(View.VISIBLE);
                        account_text_usr_icon.setVisibility(View.GONE);
                    });
                    return; // 本地头像已加载，直接返回
                }
            } catch (Exception e) {
                // 文件损坏，回退到文字头像
            }
        }

        // 2. 本地头像不存在或无效 → 显示文字头像
        showTextAvatar();
    }

    // 检查头像文件是否有效（非空且非损坏）
    private boolean isValidAvatarFile(File file) {
        return file.exists() && file.length() > 0;
    }

    // 显示文字头像（不隐藏头像区域，直接显示）
    private void showTextAvatar() {
        String nick = accUtils.getNick();
        String initial;
        if (nick != null && !nick.isEmpty()) {
            initial = nick.substring(0, 1).toUpperCase();
        } else {
            initial = "U";
        }

        new Handler(Looper.getMainLooper()).post(() -> {
            if (!isAdded() || getView() == null) return;
            account_text_usr_icon.setText(initial);
            account_text_usr_icon.setVisibility(View.VISIBLE);
            account_image_usr_icon.setVisibility(View.GONE);
        });
    }

    // 未登录状态的默认文字头像
    private void showDefaultTextAvatar() {
        new Handler(Looper.getMainLooper()).post(() -> {
            if (!isAdded() || getView() == null) return;
            account_text_usr_icon.setText("U");
            account_text_usr_icon.setVisibility(View.VISIBLE);
            account_image_usr_icon.setVisibility(View.GONE);
        });
    }

    // 检查服务器头像更新
    private void checkAvatarAndUpdate() {
        if (!isAdded() || getView() == null) return;
        String uid = accUtils.getUid();
        if (uid == null || uid.isEmpty()) return;

        // 1. 检查本地头像是否存在（用于计算MD5）
        File cacheDir = appContext.getFilesDir();
        File cacheFile = new File(cacheDir, "avatar_" + uid + ".jpg");
        String md5Str = cacheFile.exists() ? getMd5OfFile(cacheFile) : "none";

        accUtils.getAvatarInfo(md5Str, new tAccUtils.AvatarInfoCallback() {
            @Override
            public void onSuccess(boolean hasAvatar, boolean shouldDownload, String url) {
                if (!hasAvatar) {
                    if (cacheFile.exists() && !cacheFile.delete()) {
                        android.util.Log.w("AccountFragment", "Unable to remove stale avatar cache");
                    }
                    showTextAvatar();
                } else if (shouldDownload && url != null && !url.isEmpty()) {
                    downloadAvatar(uid, url);
                }
            }

            @Override
            public void onError(String message) {
                android.util.Log.w("AccountFragment", message);
            }
        });
    }

    // 保存头像到缓存
    private void downloadAvatar(String uid, String url) {
        ImageCache.loadBitmap(appContext, url, bitmap -> {
            if (bitmap == null || bitmap.isRecycled()) return;
            tAccUtils.saveAvatarToCache(appContext, uid, bitmap);
            new Handler(Looper.getMainLooper()).post(() -> {
                if (!isAdded() || getView() == null) return;
                account_image_usr_icon.setImageBitmap(bitmap);
                account_image_usr_icon.setVisibility(View.VISIBLE);
                account_text_usr_icon.setVisibility(View.GONE);
            });
        });
    }

    private String getMd5OfFile(File file) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[8192];
                int read;
                while ((read = fis.read(buffer)) != -1) {
                    md.update(buffer, 0, read);
                }
            }
            byte[] hash = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            return "none";
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updataUIShow();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            // 当Fragment变为可见时执行
            updataUIShow();
        }
    }

    private void updataUIShow() {
        updateUI();
        // 进入「我的」标签页直接刷新一次积分（失败也有缓存兜底）
        if (isAdded()) {
            com.typheye.wgpro.ui.widget.Points.refresh(requireContext(), true, null);
        }
    }
}
