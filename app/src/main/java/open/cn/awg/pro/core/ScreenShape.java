package open.cn.awg.pro.core;

import android.content.Context;

import open.cn.awg.pro.R;

/**
 * 屏幕形状自适应工具：运行时判定圆屏/方屏，不再依赖开机时手选的设备形状。
 *
 * <p>Wear OS / Android 从 API 23 起提供 {@link android.content.res.Configuration#isScreenRound()}，
 * 真机与模拟器都会带上 {@code round} 配置位，可直接判定。
 */
public final class ScreenShape {

    private ScreenShape() {
    }

    /** 是否为圆形屏幕。优先级：用户手选（SharedPreferences）> 系统自动探测。 */
    public static boolean isRound(Context context) {
        if (context == null) {
            return false;
        }
        int shape = AppPrefs.getScreenShape(context);
        if (shape == AppPrefs.SHAPE_ROUND || shape == AppPrefs.SHAPE_HARMONY) {
            return true;
        }
        if (shape == AppPrefs.SHAPE_SQUARE) {
            return false;
        }
        // SHAPE_AUTO / SHAPE_OTHER：交给系统
        try {
            return context.getResources().getConfiguration().isScreenRound();
        } catch (Throwable ignored) {
            return false;
        }
    }

    /**
     * 圆屏内容的左右安全边距。
     *
     * <p>折中值：固定 {@link #ROUND_INSET_DP} dp（而不是内接正方形的 0.293R，那个太窄）。
     * 内容更宽，代价是最贴圆弧的顶部/底部可能被切一点点。方屏返回 0。
     */
    public static int roundInset(Context context) {
        if (!isRound(context)) {
            return 0;
        }
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(ROUND_INSET_DP * density);
    }

    /** 折中安全边距（dp），想更宽就调小、想更保险就调大。 */
    public static final float ROUND_INSET_DP = 14f;

    /** 顶栏 Action 圆形按钮直径（dp）——图标 18dp + 四周 2dp 边距。 */
    public static final int ACTION_SIZE_DP = 22;

    /** Action 距离"圆屏安全线"再额外留的边距（dp），避免贴着屏边。 */
    public static final int ACTION_EDGE_EXTRA_DP = 8;

    /** Action 与标题之间的间隙（dp）。 */
    public static final int ACTION_GAP_DP = 4;

    /**
     * 给顶栏容器里的「左右 Action / 标题」套上安全区内边距：
     * 圆屏按内接正方形（≈0.293R）收进来，方屏只留 6dp；标题左右各让出 (安全区 + Action 直径 + 间隙)。
     */
    public static void applyAppBarInsets(Context context, android.view.View appBar) {
        if (context == null || appBar == null) {
            return;
        }
        int action = dp(context, ACTION_SIZE_DP);
        int edge;
        if (isRound(context)) {
            // 顶栏这一行的圆心纵坐标（按钮垂直居中于标题行）
            int rowCenterY = dp(context, 35);
            android.view.View row = appBar.findViewById(R.id.title_row);
            if (row != null && row.getHeight() > 0) {
                int[] rl = new int[2];
                int[] al = new int[2];
                row.getLocationInWindow(rl);
                appBar.getLocationInWindow(al);
                rowCenterY = rl[1] - al[1] + row.getHeight() / 2;
            }
            // 圆内套圆：dist(按钮圆心, 屏圆心) + 按钮半径 <= 屏半径
            float radius = context.getResources().getDisplayMetrics().widthPixels / 2f;
            float a = action / 2f;
            float dy = radius - rowCenterY;
            float dx = (float) Math.sqrt(Math.max(0f, (radius - a) * (radius - a) - dy * dy));
            edge = Math.max(dp(context, 6), Math.round(radius - a - dx) + dp(context, ACTION_EDGE_EXTRA_DP));
        } else {
            // 方屏：没有圆弧遮挡，Action 贴近屏幕边缘，固定 8dp 边距
            edge = dp(context, 8);
        }

        setMargin(appBar.findViewById(R.id.appbar_action_left), edge, true);
        setMargin(appBar.findViewById(R.id.appbar_action_right), edge, false);

        int side = edge + dp(context, ACTION_SIZE_DP + ACTION_GAP_DP);
        setMargin(appBar.findViewById(R.id.title_bar), side, true);
        setMargin(appBar.findViewById(R.id.title_bar), side, false);

        // 跑马灯：TextView 只有 selected/focused 时才滚动；必须在布局完成后置位，否则不滚
        android.view.View title = appBar.findViewById(R.id.title_bar);
        if (title instanceof android.widget.TextView) {
            final android.widget.TextView t = (android.widget.TextView) title;
            t.setSingleLine(true);
            t.setHorizontallyScrolling(true);
            t.setEllipsize(android.text.TextUtils.TruncateAt.MARQUEE);
            t.setMarqueeRepeatLimit(-1); // -1 = MARQUEE_FOREVER
            t.setFocusable(true);
            t.setFocusableInTouchMode(true);
            t.post(new Runnable() {
                public void run() {
                    t.setSelected(true);
                    t.requestFocus();
                }
            });
        }
    }

    private static void setMargin(android.view.View view, int value, boolean start) {
        if (view == null) {
            return;
        }
        android.view.ViewGroup.LayoutParams raw = view.getLayoutParams();
        if (!(raw instanceof android.view.ViewGroup.MarginLayoutParams)) {
            return;
        }
        android.view.ViewGroup.MarginLayoutParams lp = (android.view.ViewGroup.MarginLayoutParams) raw;
        if (start) {
            lp.setMarginStart(value);
        } else {
            lp.setMarginEnd(value);
        }
        view.setLayoutParams(lp);
    }

    private static int dp(Context context, float value) {
        return Math.round(value * context.getResources().getDisplayMetrics().density);
    }
}
