package com.typheye.wgpro.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.typheye.wgpro.R;

import java.util.WeakHashMap;

/**
 * 固定应用栏毛玻璃装配，结构与 {@code activity_main.xml} 保持一致：
 *
 * <pre>
 * 布局里显式声明（放在内容之后，保证画在内容之上）：
 *   FrameLayout(app_bar_host)
 *     ├── ImageView(app_bar_blur)   // 毛玻璃快照层，约束/填充到应用栏范围
 *     └── AppBarLayout(surface_bar + 透明 Toolbar)
 * </pre>
 *
 * 本类负责四件事：应用栏吃状态栏内边距、内容里的第一个滚动容器补应用栏高度的顶部留白、
 * 把内容里的下拉刷新指示器压到应用栏下方、把 XML 里的快照层交给 {@link BarBlurController}。
 * 列表滚动时就会从毛玻璃下方穿过。折叠应用栏（CollapsingToolbarLayout）与 WebView 页面不要用。
 */
public final class AppBarBlur {
    private AppBarBlur() { }

    /** 已设置过的下拉刷新指示器位置（键为控件本身）：重复设置会重置指示器、打断下拉手势。 */
    private static final WeakHashMap<View, Integer> REFRESH_OFFSETS = new WeakHashMap<>();

    /** 各滚动视图**原始**的顶部内边距：补应用栏留白时要叠加，不能覆盖（否则列表会贴顶、没 gap）。 */
    private static final WeakHashMap<View, Integer> BASE_TOP_PADDING = new WeakHashMap<>();
    /** 原始底部内边距：同理，补导航栏/底部留白也要叠加。 */
    private static final WeakHashMap<View, Integer> BASE_BOTTOM_PADDING = new WeakHashMap<>();

    /**
     * 给滚动视图补「应用栏高度」的顶部留白，**保留原有 padding 叠加**。
     *
     * <p>注意不能直接 `setPadding(..., top, ...)`：很多列表自己带了顶部 12–16dp 的视觉留白
     * （以前在内容层里，现在移到 RecyclerView 上），覆盖掉就会贴到应用栏下面、没有 gap。
     */
    public static void padScrollableTop(@NonNull View view, int top) {
        Integer base = BASE_TOP_PADDING.get(view);
        if (base == null) {
            base = view.getPaddingTop();
            BASE_TOP_PADDING.put(view, base);
        }
        int desired = base + Math.max(0, top);
        if (view.getPaddingTop() == desired) return;
        view.setPadding(view.getPaddingLeft(), desired, view.getPaddingRight(), view.getPaddingBottom());
    }

    /**
     * 给滚动视图补「底部安全区」留白，同样**保留原有 padding 叠加**。
     * （RecyclerView 的 XML 上常有 28dp 底部留白，直接覆盖会让最后一张卡片贴住屏幕底边。）
     */
    public static void padScrollableBottom(@NonNull View view, int bottom) {
        Integer base = BASE_BOTTOM_PADDING.get(view);
        if (base == null) {
            base = view.getPaddingBottom();
            BASE_BOTTOM_PADDING.put(view, base);
        }
        int desired = base + Math.max(0, bottom);
        if (view.getPaddingBottom() == desired) return;
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), desired);
    }

    /**
     * 单个下拉刷新控件：把转圈指示器的位置压到应用栏下方。
     *
     * <p>内容铺到半透明应用栏下面之后，指示器默认位置会被应用栏盖住——下拉时看不到任何反馈，
     * 用户会以为"没有触发"。只在应用栏高度变化时设置一次（幂等），避免重复调用打断手势。
     */
    public static void offsetRefreshIndicator(@NonNull SwipeRefreshLayout refresh, int top) {
        Integer applied = REFRESH_OFFSETS.get(refresh);
        if (applied != null && applied == top) return;
        refresh.setProgressViewOffset(true, top, top + Math.round(
                64f * refresh.getResources().getDisplayMetrics().density));
        REFRESH_OFFSETS.put(refresh, top);
    }

    /** 递归给内容里所有下拉刷新控件设置指示器位置。 */
    public static void offsetRefreshIndicators(@NonNull View view, int top) {
        if (view instanceof SwipeRefreshLayout) {
            offsetRefreshIndicator((SwipeRefreshLayout) view, top);
        }
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int index = 0; index < group.getChildCount(); index++) {
                offsetRefreshIndicators(group.getChildAt(index), top);
            }
        }
    }

    /**
     * @param appBar  固定应用栏（AppBarLayout，位于 app_bar_host 内）
     * @param content 页面内容容器：滚动视图或承载 Fragment 的容器（铺满整屏）
     */
    @NonNull
    public static BarBlurController install(@NonNull Activity activity,
                                            @NonNull View appBar,
                                            @NonNull View content) {
        View host = (View) appBar.getParent();
        ImageView backdrop = host.findViewById(R.id.app_bar_blur);

        // 应用栏吃状态栏内边距；滚动容器顶部留白 = 应用栏高度（幂等，不反复触发布局）
        // 应用栏登记：模糊关闭时改成与页面一致的不透明底色
        BarBlurController.registerBar(activity, appBar);
        SystemBars.applyAppBarInsets(appBar, null);
        ViewTreeObserver.OnGlobalLayoutListener listener = () -> {
            int height = appBar.getHeight();
            if (height > 0) {
                padFirstScrollable(content, height);
                offsetRefreshIndicators(content, height);
            }
        };
        content.getViewTreeObserver().addOnGlobalLayoutListener(listener);

        BarBlurController controller;
        if (backdrop == null) {
            // 兼容旧布局：没有声明快照层时退化为普通半透明底色
            appBar.setBackgroundColor(activity.getColor(R.color.surface_bar));
            controller = BarBlurController.install(activity, content);
        } else {
            controller = BarBlurController.install(activity, content, backdrop);
        }
        // 金刚键（三键导航）适配：无底栏页面在系统导航条那一条补毛玻璃
        installNavBarStrip(activity);
        return controller;
    }

    /**
     * 金刚键（三键导航）适配：给"只有应用栏、没有底栏"的页面，在系统导航条那一条补毛玻璃。
     *
     * <p><b>只在三键导航下铺</b>（{@link SystemBars#isThreeButtonNav}）；手势导航只有一条小横条、
     * 本就与系统 UI 融为一体，<b>保持原样什么都不加</b>。开关口径与其它栏一致：
     * 开 = {@code surface_bar} 半透明 + 内容快照模糊；关 = 不透明 {@code surface_primary}
     * （见 {@link BarBlurController#registerBar}）。</p>
     *
     * <p>页面若已有别的底部快照层（布局里那些非 {@code app_bar_blur} 的 {@code *_blur}，
     * 例如 {@code dynamic_bottom_blur}），说明底部已被底栏覆盖 → 不再铺这条，避免盖住已有底栏。</p>
     */
    public static void installNavBarStrip(@NonNull Activity activity) {
        ViewGroup root = activity.findViewById(android.R.id.content);
        if (root == null || root.findViewById(R.id.nav_bar_blur) != null) {
            return;
        }
        ViewGroup decor = (ViewGroup) activity.getWindow().getDecorView();
        final android.content.Context context = activity;

        final android.widget.FrameLayout strip = new android.widget.FrameLayout(context);
        final ImageView backdrop = new ImageView(context);
        backdrop.setId(R.id.nav_bar_blur);
        backdrop.setScaleType(ImageView.ScaleType.MATRIX);
        backdrop.setClickable(false);
        backdrop.setFocusable(false);
        backdrop.setVisibility(View.GONE);
        final View scrim = new View(context);
        scrim.setId(R.id.nav_bar_scrim);
        strip.addView(backdrop, new android.widget.FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        strip.addView(scrim, new android.widget.FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        android.widget.FrameLayout.LayoutParams params = new android.widget.FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0);
        params.gravity = android.view.Gravity.BOTTOM;
        // 挂在 decor 上而不是内容根里：这样它不在快照源内部，软绘整屏时不会把自己画进去形成回环
        decor.addView(strip, params);
        strip.setVisibility(View.GONE);

        // 底色登记：设置页开关实时同步（开=surface_bar 半透明 / 关=surface_primary 不透明）
        BarBlurController.registerBar(context, scrim);
        // 单独建一份"整屏内容根"的快照：页面自己的快照源（如用户详情的 detail_pager）可能
        // 根本延伸不到导航条那一条，映射过去是空白 → 表现成"纯色撞色"。
        BarBlurController.install(activity, root, backdrop);

        // 「页面已有底栏」一旦成立就是永久的（底栏不会中途消失），缓存下来省掉每次布局的遍历
        final boolean[] hasBottomBar = {false};
        final Runnable refresh = () -> {
            if (strip.getParent() == null) {
                return;
            }
            // 必须等布局完成（root 有高度）再判：高度为 0 时"底部 1/4"阈值退化成 0，
            // 会把用户详情页顶部的 detail_segments_blur 误判成底栏并永久缓存（踩过）。
            if (!hasBottomBar[0] && root.getHeight() > 0) {
                hasBottomBar[0] = hasOtherBottomBlur(root, backdrop);
            }
            // 导航条可能贴底（竖屏）也可能贴左/右侧（横屏）：统一按"导航条在哪条边"布局
            WindowInsetsCompat insets = ViewCompat.getRootWindowInsets(root);
            int size = 0;
            int gravity = android.view.Gravity.BOTTOM;
            if (SystemBars.shouldAdaptNavBar(root, insets)) {
                SystemBars.NavEdge edge = SystemBars.navBarEdge(insets);
                size = SystemBars.navBarInset(insets, edge);
                gravity = edge == SystemBars.NavEdge.LEFT ? android.view.Gravity.START
                        : edge == SystemBars.NavEdge.RIGHT ? android.view.Gravity.END
                        : android.view.Gravity.BOTTOM;
            }
            placeNavStrip(strip, gravity, size);
            boolean show = size > 0 && !hasBottomBar[0];
            strip.setVisibility(show ? View.VISIBLE : View.GONE);
        };

        // insets 变化（进出小窗 / 三键↔手势切换 / 导航条隐藏）与布局稳定都走这里
        root.getViewTreeObserver().addOnGlobalLayoutListener(refresh::run);
        root.post(refresh);
        root.postDelayed(refresh, 400L);
        root.postDelayed(refresh, 1200L);
        ViewCompat.requestApplyInsets(root);
    }

    /**
     * 页面里是否已有"别的底部快照层"。
     *
     * <p>只认 {@link ImageView} 且 id 以 {@code _blur} 结尾的（布局里的快照层约定），
     * 例如 {@code dynamic_bottom_blur} / {@code chat_bottom_blur}；<b>不能只看 id 后缀</b>——
     * 设置页有个普通行 {@code row_blur}（LinearLayout），会被误判成"已有底栏"。</p>
     */
    private static boolean hasOtherBottomBlur(@NonNull View root, @NonNull View navBackdrop) {
        int[] rootLoc = new int[2];
        root.getLocationInWindow(rootLoc);
        int rootBottom = rootLoc[1] + root.getHeight();
        // 只有"延伸到屏幕底部 1/4"的快照层才算底栏：用户详情的滚动指示器 (detail_segments_blur)
        // 在上半部分，如果只按 id 后缀判定就会被误当成底栏，导致那条永远不铺。
        int threshold = rootBottom - Math.max(1, root.getHeight() / 4);
        return scanBottomBlur(root, navBackdrop, threshold);
    }

    private static boolean scanBottomBlur(@NonNull View view, @NonNull View navBackdrop,
                                          int threshold) {
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup group = (ViewGroup) view;
        int[] loc = new int[2];
        for (int index = 0; index < group.getChildCount(); index++) {
            View child = group.getChildAt(index);
            if (child == navBackdrop || child.getId() == R.id.app_bar_blur) {
                continue;
            }
            if (isBlurLayer(child)) {
                // 高度必须 > 0：页面加载中/未排版时快照层是 0 高但 y 可能已经落在底部区域
                // （用户详情的 detail_segments_blur 就是 y=2316 h=0），那不算底栏。
                child.getLocationInWindow(loc);
                if (child.getHeight() > 0 && loc[1] + child.getHeight() >= threshold) {
                    return true;
                }
                continue;
            }
            if (scanBottomBlur(child, navBackdrop, threshold)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isBlurLayer(@NonNull View view) {
        if (!(view instanceof ImageView)) {
            return false;
        }
        int id = view.getId();
        if (id == View.NO_ID) {
            return false;
        }
        try {
            return view.getResources().getResourceEntryName(id).endsWith("_blur");
        } catch (Throwable ignored) {
            return false;
        }
    }

    /** 按"导航条在哪条边"给这条设置尺寸与对齐（贴底=整宽×高；贴左/右=宽×整高）。 */
    private static void placeNavStrip(@NonNull View strip, int gravity, int size) {
        boolean bottom = gravity == android.view.Gravity.BOTTOM;
        android.widget.FrameLayout.LayoutParams lp =
                (android.widget.FrameLayout.LayoutParams) strip.getLayoutParams();
        int width = bottom ? ViewGroup.LayoutParams.MATCH_PARENT : size;
        int height = bottom ? size : ViewGroup.LayoutParams.MATCH_PARENT;
        if (lp.width != width || lp.height != height || lp.gravity != gravity) {
            lp.width = width;
            lp.height = height;
            lp.gravity = gravity;
            strip.setLayoutParams(lp);
        }
    }

    /**
     * 三键 + 前台全屏时，给"不做毛玻璃"或"没有应用栏"的全屏页垫一条与页面同色的导航条高度，
     * 让金刚键那一条不至于直接透出内容、观感断裂。手势导航、小窗、IME 弹出时不垫。
     *
     * @param color 不透明底色：浏览器=应用栏底色；OOBE / 强制更新页=页面底色
     */
    public static void installNavBarSpacer(@NonNull Activity activity, final int color) {
        installNavBarSpacer(activity, color, null);
    }

    /**
     * @param contentToPad 需要跟着"垫高"的内容视图（如浏览器的 WebView）：三键 + 前台全屏时
     *                     给它加一个导航条高度的底部内边距，让页面底部不被导航栏压住。
     *                     传 null 表示只垫那条底色、不缩内容。
     */
    public static void installNavBarSpacer(@NonNull Activity activity, final int color,
                                           @Nullable final View contentToPad) {
        ViewGroup root = activity.findViewById(android.R.id.content);
        if (root == null || root.findViewById(R.id.nav_bar_scrim) != null) {
            return;
        }
        final View strip = new View(activity);
        strip.setId(R.id.nav_bar_scrim);
        strip.setBackgroundColor(color);

        android.widget.FrameLayout.LayoutParams params = new android.widget.FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0);
        params.gravity = android.view.Gravity.BOTTOM;
        root.addView(strip, params);
        strip.setVisibility(View.GONE);

        // 内容视图**原始**的底部 margin：垫高要叠加在它之上，并且 size 变 0（手势/小窗）时必须还原，
        // 否则小窗里会凭空多出一块灰色间隙（踩过）。
        final int[] baseBottomMargin = {0};
        if (contentToPad != null
                && contentToPad.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            baseBottomMargin[0] =
                    ((ViewGroup.MarginLayoutParams) contentToPad.getLayoutParams()).bottomMargin;
        }
        final int[] baseBottomPadding = {contentToPad == null ? 0 : contentToPad.getPaddingBottom()};
        final Runnable refresh = () -> {
            if (strip.getParent() == null) {
                return;
            }
            WindowInsetsCompat insets = ViewCompat.getRootWindowInsets(root);
            int size = 0;
            int gravity = android.view.Gravity.BOTTOM;
            if (SystemBars.shouldAdaptNavBar(root, insets)) {
                SystemBars.NavEdge edge = SystemBars.navBarEdge(insets);
                size = SystemBars.navBarInset(insets, edge);
                gravity = edge == SystemBars.NavEdge.LEFT ? android.view.Gravity.START
                        : edge == SystemBars.NavEdge.RIGHT ? android.view.Gravity.END
                        : android.view.Gravity.BOTTOM;
            }
            placeNavStrip(strip, gravity, size);
            strip.setVisibility(size > 0 ? View.VISIBLE : View.GONE);
            if (contentToPad != null) {
                // 用底部 margin 让内容真正"垫上去"（WebView 的 setPadding 不一定会缩渲染区）；
                // size 变 0 时还原成原始 margin。
                ViewGroup.LayoutParams lp = contentToPad.getLayoutParams();
                if (lp instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) lp;
                    int wanted = baseBottomMargin[0] + size;
                    if (mlp.bottomMargin != wanted) {
                        mlp.bottomMargin = wanted;
                        contentToPad.setLayoutParams(mlp);
                    }
                } else {
                    int wanted = baseBottomPadding[0] + size;
                    if (contentToPad.getPaddingBottom() != wanted) {
                        contentToPad.setPadding(contentToPad.getPaddingLeft(),
                                contentToPad.getPaddingTop(), contentToPad.getPaddingRight(), wanted);
                    }
                }
            }
        };
        root.getViewTreeObserver().addOnGlobalLayoutListener(refresh::run);
        root.post(refresh);
        ViewCompat.requestApplyInsets(root);
    }

    /**
     * 无应用栏的全屏页（扫码 / 模拟设备 / 预览）：三键 + 前台全屏时直接隐藏系统导航栏，
     * 让内容真正铺满；手势导航、小窗、IME 弹出时保持系统原样（不隐藏）。
     */
    public static void hideNavBarInFullscreen(@NonNull final Activity activity) {
        final View root = activity.findViewById(android.R.id.content);
        if (root == null) {
            return;
        }
        // 判定必须与"导航栏当前是否可见"解耦：隐藏后 navigationBars inset 会变成 0，
        // 若还按 inset 判，就会 hide→inset=0→show→inset 恢复→hide 死循环（表现为导航栏疯狂闪烁）。
        final boolean[] threeButton = {SystemBars.navMode(activity) == 0};
        final Runnable apply = () -> {
            WindowInsetsCompat insets = ViewCompat.getRootWindowInsets(root);
            int navInset = insets == null ? 0
                    : insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
            if (insets != null && navInset > 0) {
                // 只有在导航栏可见时才重新判定一次模式；隐藏后沿用上次结论
                int mode = SystemBars.navMode(root.getContext());
                threeButton[0] = mode >= 0
                        ? mode == 0
                        : insets.getInsets(WindowInsetsCompat.Type.tappableElement()).bottom > 0;
            }
            boolean hide = threeButton[0]
                    && !SystemBars.isFreeformOrSplit(root)
                    && (insets == null
                        || insets.getInsets(WindowInsetsCompat.Type.ime()).bottom == 0);
            WindowInsetsControllerCompat controller =
                    WindowCompat.getInsetsController(activity.getWindow(), root);
            if (hide) {
                controller.setSystemBarsBehavior(
                        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
                controller.hide(WindowInsetsCompat.Type.navigationBars());
            } else {
                controller.show(WindowInsetsCompat.Type.navigationBars());
            }
        };
        ViewCompat.setOnApplyWindowInsetsListener(root, (view, insets) -> {
            apply.run();
            return insets;
        });
        // 弹窗 / 权限框 / 输入法等"另一个窗口"拿到焦点时，系统会把导航栏重新显示出来；
        // 这些子窗口消失、本窗口重新聚焦时再收一次，否则导航栏会一直留在屏幕上。
        root.getViewTreeObserver().addOnWindowFocusChangeListener(hasFocus -> {
            if (hasFocus) {
                root.post(apply);
                root.postDelayed(apply, 300L);
            }
        });
        ViewCompat.requestApplyInsets(root);
    }

    /**
     * 内容结构是「不滚动容器 + 内部滚动视图」（与 {@code SettingsActivity} 一致）时的装配。
     *
     * <p><b>快照源必须是那个不滚动的容器，不能是滚动视图本身</b>：{@code View.drawBackground()}
     * 在视图自身滚动时会按 {@code scrollY} 平移背景，滚动后快照顶部会变成透明，毛玻璃就消失了
     * （表现：内容穿到应用栏下面但一点不糊）。容器自身滚动恒为 0，背景与子内容都能正确画进快照。
     *
     * <p>顶部留白（应用栏高度）与底部留白（系统栏）由同一个 insets 回调加在内部滚动视图上，互不覆盖。
     *
     * @param snapshotContainer 不滚动的内容容器（快照源，需有不透明背景）
     * @param scrollContent     容器内部真正滚动的视图
     */
    @NonNull
    public static BarBlurController installWithScrollContent(@NonNull Activity activity,
                                                             @NonNull View appBar,
                                                             @NonNull View snapshotContainer,
                                                             @NonNull View scrollContent) {
        BarBlurController controller = install(activity, appBar, snapshotContainer);
        if (scrollContent instanceof ViewGroup) {
            ((ViewGroup) scrollContent).setClipToPadding(false);
        }
        final int initialLeft = scrollContent.getPaddingLeft();
        final int initialRight = scrollContent.getPaddingRight();
        final int initialBottom = scrollContent.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(scrollContent, (view, insets) -> {
            int top = appBar.getHeight();
            int bottom = initialBottom + SystemBars.bottomInsetForView(view, insets);
            if (view.getPaddingTop() != top || view.getPaddingBottom() != bottom) {
                view.setPadding(initialLeft, top, initialRight, bottom);
            }
            return insets;
        });
        // 应用栏高度确定后重新派发一次，保证顶部留白正确。
        scrollContent.getViewTreeObserver().addOnGlobalLayoutListener(
                () -> ViewCompat.requestApplyInsets(scrollContent));
        ViewCompat.requestApplyInsets(scrollContent);
        return controller;
    }

    /**
     * 只给遇到的第一个滚动容器补顶部内边距，避免嵌套滚动容器被补两次。
     *
     * <p>遇到 {@link androidx.viewpager2.widget.ViewPager2} 时跳过它自己的内部 RecyclerView，
     * 改去补「每个页面里面」的第一个滚动容器——只补第一个页面的话，其它页面会没有留白、
     * 内容顶到应用栏下面（看起来就是"页面错位"）。
     */
    private static boolean padFirstScrollable(View view, int top) {
        // 隐藏的滚动视图不用补留白（例如关注/粉丝页同时存在 NestedScrollView 与 RecyclerView，
        // 只给当前可见的那个补）。
        if (view.getVisibility() != View.VISIBLE) return false;
        if (view instanceof androidx.viewpager2.widget.ViewPager2) {
            androidx.viewpager2.widget.ViewPager2 pager =
                    (androidx.viewpager2.widget.ViewPager2) view;
            boolean padded = false;
            for (int index = 0; index < pager.getChildCount(); index++) {
                View child = pager.getChildAt(index);
                if (!(child instanceof ViewGroup)) continue;
                ViewGroup holder = (ViewGroup) child;
                for (int page = 0; page < holder.getChildCount(); page++) {
                    // 每一页都尝试一次；某一页还没有内容时不影响其它页
                    if (padFirstScrollable(holder.getChildAt(page), top)) padded = true;
                }
            }
            return padded;
        }
        if (view instanceof NestedScrollView) {
            NestedScrollView scroll = (NestedScrollView) view;
            scroll.setClipToPadding(false);
            padScrollableTop(scroll, top);
            return true;
        }
        if (view instanceof RecyclerView) {
            RecyclerView recycler = (RecyclerView) view;
            recycler.setClipToPadding(false);
            padScrollableTop(recycler, top);
            return true;
        }
        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            for (int index = 0; index < group.getChildCount(); index++) {
                if (padFirstScrollable(group.getChildAt(index), top)) return true;
            }
        }
        return false;
    }
}
