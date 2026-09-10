package com.typheye.wgpro.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * 全应用唯一的 edge-to-edge / 系统栏适配入口。
 *
 * <p>实现完全遵循 Google 官方文档与 edge-to-edge codelab 的推荐做法：
 * <ul>
 *     <li>{@code WindowCompat.setDecorFitsSystemWindows(window, false)} 让内容延伸到系统栏区域；</li>
 *     <li>{@code Window#setStatusBarColor} / {@code Window#setNavigationBarColor} 使用全透明，</li>
 *     <li>{@code Window#setAttributes(WindowManager.LayoutParams)} 写入
 *     {@code layoutInDisplayCutoutMode}，使内容可以进入挖孔/刘海区域；</li>
 *     <li>{@code WindowInsetsCompat.Type.systemBars() | displayCutout()} 读取系统栏尺寸；</li>
 *     <li>{@code View#setPadding} 叠加系统栏内边距（先保存 XML 原始 padding，避免反复调用后膨胀）。</li>
 * </ul>
 *
 * <p>底部“系统导航小横条”规则：导航栏（手势条或三键栏）打开时，系统 inset 已经提供视觉留白，
 * 不再额外叠加间距；没有导航栏时才补上原来的半个底部按钮高度，避免按钮贴死屏幕底边。
 */
public final class SystemBars {
    /** 底部弹窗基础留白（不含系统导航栏占位）。 */
    private static final int SHEET_BASE_BOTTOM_DP = 12;
    /** 底部按钮高度，用于计算“没有导航栏”时的补偿间距。 */
    private static final int SHEET_BUTTON_HEIGHT_DP = 54;
    /** 多窗口（自由窗）额外补偿，避免小窗贴边。 */
    private static final int FREEFORM_TOP_DP = 18;
    private static final int FREEFORM_SIDE_DP = 8;
    private static final int FREEFORM_BOTTOM_DP = 22;

    /** 已经由本类完成 window 层适配的窗口。 */
    private static final Set<Window> CONFIGURED_WINDOWS =
            Collections.newSetFromMap(new WeakHashMap<Window, Boolean>());
    /** 已经自行处理 insets 的 Activity，安装全应用适配时不再兜底。 */
    private static final Set<Activity> HANDLED_ACTIVITIES =
            Collections.newSetFromMap(new WeakHashMap<Activity, Boolean>());
    /** 已经兜底处理过 insets 的 Activity。 */
    private static final Set<Activity> FALLBACK_ACTIVITIES =
            Collections.newSetFromMap(new WeakHashMap<Activity, Boolean>());
    /** 已经由 {@link #reserveBottomInsetForScroll} 处理过的滚动视图。 */
    private static final Set<View> SCROLL_INSET_APPLIED =
            Collections.newSetFromMap(new WeakHashMap<View, Boolean>());
    private SystemBars() {
    }

    // ------------------------------------------------------------------
    // 1. 窗口层：edge-to-edge
    // ------------------------------------------------------------------

    /**
     * 安装全应用自动适配。
     *
     * <p>不再逐个 Activity 改写窗口代码：这里在 {@code onCreate} 与每次
     * {@code onStart} 都强制走一遍窗口层适配，所以 Manifest 里的每一个 Activity
     * （包括第三方扫码页）都会被覆盖——{@code setDecorFitsSystemWindows(false)}、
     * 系统栏透明、关闭对比度强制。页面若已自行调用
     * {@link #applyScreenInsets} / {@link #applyAppBarInsets} / {@link #applyTopInsets}，
     * 兜底逻辑不会再叠加内边距。
     */
    public static void install(@NonNull Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle state) {
                enableEdgeToEdge(activity);
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                // 页面可能在 onCreate / onResume 里改过系统栏，这里每次回到前台都强制复位。
                enableEdgeToEdge(activity);
                applyFallbackInsets(activity);
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity,
                                                    @NonNull Bundle state) {
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                HANDLED_ACTIVITIES.remove(activity);
                FALLBACK_ACTIVITIES.remove(activity);
            }
        });
    }

    /** 让 Activity 窗口进入 edge-to-edge（幂等）。 */
    public static void enableEdgeToEdge(@NonNull Activity activity) {
        configureWindow(activity.getWindow(), activity);
    }

    /** 让窗口进入 edge-to-edge（幂等）。 */
    public static void configureWindow(@NonNull Window window, @NonNull Context context) {
        // 1. 一次性改写 WindowManager.LayoutParams：允许内容进入挖孔/刘海，
        //    要求窗口自己绘制系统栏背景，并清掉旧的半透明标志。
        //    背景彻底交给内容（真正的透明），而不是给系统栏刷一个同色底。
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            attributes.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            attributes.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        attributes.flags |= WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS;
        attributes.flags &= ~(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.setAttributes(attributes);

        // 2. 内容延伸到系统栏区域（FitsSystemWindows = false）。
        WindowCompat.setDecorFitsSystemWindows(window, false);

        // 3. 系统栏颜色只能通过 Window 的公开接口写回 LayoutParams。
        //    Android 15+ 上这两个方法已经是 no-op（强制 edge-to-edge，系统栏本就透明），
        //    低于 15 的版本由它们真正把状态栏/导航栏设成 ARGB 0x00000000。
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.TRANSPARENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            window.setNavigationBarDividerColor(Color.TRANSPARENT);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.setStatusBarContrastEnforced(false);
            window.setNavigationBarContrastEnforced(false);
        }
        if (CONFIGURED_WINDOWS.add(window)) {
            boolean light = isLightMode(context);
            WindowInsetsControllerCompat controller =
                    WindowCompat.getInsetsController(window, window.getDecorView());
            controller.setAppearanceLightStatusBars(light);
            controller.setAppearanceLightNavigationBars(light);
        }
    }

    /**
     * 设置状态栏/导航栏前景明暗。页面有自定义头图（例如用户详情）时可以覆盖默认值。
     */
    public static void setLightSystemBars(@NonNull Window window, boolean light) {
        WindowInsetsControllerCompat controller =
                WindowCompat.getInsetsController(window, window.getDecorView());
        controller.setAppearanceLightStatusBars(light);
        controller.setAppearanceLightNavigationBars(light);
    }

    // ------------------------------------------------------------------
    // 2. 页面层：把系统栏尺寸变成 View 的内边距
    // ------------------------------------------------------------------

    /** 整页统一适配：内容躲开系统栏，系统栏区域由页面自身背景填充。 */
    public static void applyScreenInsets(@NonNull View root) {
        uncheckedApplyScreenInsets(root, 0, 0);
    }

    /**
     * 只让内容躲开顶部状态栏与挖孔，底部保持完全沉浸。
     * 适用于希望网页/媒体自己铺满到底部的页面。
     */
    public static void applyTopInsets(@NonNull View root) {
        markHandled(root);
        final int initialLeft = root.getPaddingLeft();
        final int initialTop = root.getPaddingTop();
        final int initialRight = root.getPaddingRight();
        final int initialBottom = root.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(root, (view, insets) -> {
            Insets bars = systemBarInsets(insets);
            view.setPadding(initialLeft + bars.left, initialTop + bars.top,
                    initialRight + bars.right, initialBottom);
            return insets;
        });
        ViewCompat.requestApplyInsets(root);
    }

    /**
     * 声明该页面自行处理系统栏（例如沉浸式头图），安装全应用适配时不再兜底。
     */
    public static void markImmersive(@NonNull View root) {
        markHandled(root);
    }

    /**
     * 为某个 View 保留底部系统栏高度（含手势小横条），使其内容不会被系统栏遮挡。
     * 背景仍然铺满到屏幕底部，从而保持沉浸效果。
     */
    public static void reserveBottomInset(@NonNull final View view) {
        markHandled(view);
        final int initialLeft = view.getPaddingLeft();
        final int initialTop = view.getPaddingTop();
        final int initialRight = view.getPaddingRight();
        final int initialBottom = view.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(view, (target, insets) -> {
            int bottom = systemBarInsets(insets).bottom;
            target.setPadding(initialLeft, initialTop, initialRight, initialBottom + bottom);
            return insets;
        });
        ViewCompat.requestApplyInsets(view);
    }

    /**
     * 让页面里"真正滚动的那一层"预留底部系统栏高度，而不是给外层容器加内边距。
     *
     * <p>给外层容器加内边距会把可视区整体缩短，滚动内容会在导航栏上沿被硬切断，
     * 导航栏区域只剩页面底色；把内边距加在滚动视图上并关闭
     * {@code clipToPadding}，内容就能画到手势小横条下面，同时列表末尾仍可完整滚出。
     *
     * @return 找到并处理了滚动视图返回 true；没有滚动视图时退回整页内边距并返回 false。
     */
    public static boolean reserveBottomInsetForScroll(@NonNull View pageRoot) {
        markHandled(pageRoot);
        View scrollable = findScrollableHost(pageRoot);
        if (scrollable == null) {
            reserveBottomInset(pageRoot);
            return false;
        }
        if (!(scrollable instanceof ViewGroup)) {
            reserveBottomInset(pageRoot);
            return false;
        }
        applyScrollBottomInset((ViewGroup) scrollable);
        return true;
    }

    private static void applyScrollBottomInset(@NonNull final ViewGroup scrollable) {
        if (!SCROLL_INSET_APPLIED.add(scrollable)) {
            return;
        }
        final int initialLeft = scrollable.getPaddingLeft();
        final int initialTop = scrollable.getPaddingTop();
        final int initialRight = scrollable.getPaddingRight();
        final int initialBottom = scrollable.getPaddingBottom();
        scrollable.setClipToPadding(false);
        ViewCompat.setOnApplyWindowInsetsListener(scrollable, (view, insets) -> {
            view.setPadding(initialLeft, initialTop, initialRight,
                    initialBottom + systemBarInsets(insets).bottom);
            return insets;
        });
        ViewCompat.requestApplyInsets(scrollable);
    }

    /** 深度优先找出页面里第一个可滚动容器（会穿过 SwipeRefreshLayout 之类的包装层）。 */
    @Nullable
    private static View findScrollableHost(@NonNull View view) {
        if (isScrollableHost(view)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup group = (ViewGroup) view;
        for (int i = 0; i < group.getChildCount(); i++) {
            View found = findScrollableHost(group.getChildAt(i));
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    private static boolean isScrollableHost(@NonNull View view) {
        return view instanceof NestedScrollView
                || view instanceof ScrollView
                || view instanceof RecyclerView
                || view instanceof ViewPager2;
    }

    /**
     * 整页统一适配（双色 chrome）。顶部系统栏区域使用 {@code appBarColor}，
     * 底部系统栏区域使用 {@code pageColor}，使沉浸式页面在两处都保持正确底色。
     */
    public static void applyScreenInsets(@NonNull View root, int appBarColor, int pageColor) {
        uncheckedApplyScreenInsets(root, appBarColor, pageColor);
    }

    private static void uncheckedApplyScreenInsets(@NonNull View root, int appBarColor,
                                                   int pageColor) {
        uncheckedApplyTopAndSideInsets(root, appBarColor, pageColor, true);
    }

    /**
     * 顶部 / 左右系统栏内边距 + 双色 chrome 背景，底部不加任何内边距。
     * 供"底部安全区交给滚动视图"的页面使用。
     */
    private static void uncheckedApplyTopAndSideInsets(@NonNull View root, int appBarColor,
                                                       int pageColor) {
        uncheckedApplyTopAndSideInsets(root, appBarColor, pageColor, false);
    }

    private static void uncheckedApplyTopAndSideInsets(@NonNull View root, int appBarColor,
                                                       int pageColor, boolean includeBottom) {
        markHandled(root);
        final int initialLeft = root.getPaddingLeft();
        final int initialTop = root.getPaddingTop();
        final int initialRight = root.getPaddingRight();
        final int initialBottom = root.getPaddingBottom();
        final ChromeBackgroundDrawable chrome = appBarColor == 0 && pageColor == 0
                ? null : new ChromeBackgroundDrawable(appBarColor, pageColor);
        if (chrome != null) {
            root.setBackground(chrome);
        }
        ViewCompat.setOnApplyWindowInsetsListener(root, (view, insets) -> {
            Insets bars = systemBarInsets(insets);
            if (chrome != null) {
                chrome.setTopHeight(bars.top);
            }
            view.setPadding(initialLeft + bars.left, initialTop + bars.top,
                    initialRight + bars.right,
                    includeBottom ? initialBottom + bars.bottom : initialBottom);
            // 继续向下传递，子 View（聊天输入框等）仍需要 ime / systemBars。
            return insets;
        });
        ViewCompat.requestApplyInsets(root);
    }

    /**
     * 顶部应用栏 + 底部区域的经典适配。顶部叠加 statusBars 与 displayCutout，
     * 底部叠加 navigationBars；多窗口时补偿自由窗的额外空间。
     */
    public static void applyAppBarInsets(@NonNull View appBar, @Nullable View bottomArea) {
        markHandled(appBar);

        final int appBarLeft = appBar.getPaddingLeft();
        final int appBarTop = appBar.getPaddingTop();
        final int appBarRight = appBar.getPaddingRight();
        final int appBarBottom = appBar.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(appBar, (view, insets) -> {
            Insets top = insets.getInsets(WindowInsetsCompat.Type.statusBars()
                    | WindowInsetsCompat.Type.displayCutout());
            boolean freeform = isInMultiWindow(view);
            int extraTop = freeform ? dp(view, FREEFORM_TOP_DP) : 0;
            int extraSide = freeform ? dp(view, FREEFORM_SIDE_DP) : 0;
            view.setPadding(appBarLeft + top.left + extraSide,
                    appBarTop + top.top + extraTop,
                    appBarRight + top.right + extraSide,
                    appBarBottom);
            return insets;
        });

        // bottomArea 为 null 表示底部安全区交给页面内部的滚动视图处理。
        if (bottomArea == null) {
            ViewCompat.requestApplyInsets(appBar);
            return;
        }
        markHandled(bottomArea);
        final int bottomLeft = bottomArea.getPaddingLeft();
        final int bottomTop = bottomArea.getPaddingTop();
        final int bottomRight = bottomArea.getPaddingRight();
        final int bottomBottom = bottomArea.getPaddingBottom();
        ViewCompat.setOnApplyWindowInsetsListener(bottomArea, (view, insets) -> {
            Insets bars = systemBarInsets(insets);
            boolean freeform = isInMultiWindow(view);
            int extraBottom = freeform ? dp(view, FREEFORM_BOTTOM_DP) : 0;
            int extraSide = freeform ? dp(view, FREEFORM_SIDE_DP) : 0;
            view.setPadding(bottomLeft + bars.left + extraSide,
                    bottomTop,
                    bottomRight + bars.right + extraSide,
                    bottomBottom + bars.bottom + extraBottom);
            return insets;
        });
        ViewCompat.requestApplyInsets(appBar);
        ViewCompat.requestApplyInsets(bottomArea);
    }

    /**
     * 整页适配，并把底部系统栏占位记在指定的内容视图上。
     *
     * <p>{@code root} 的顶部系统栏区域使用 {@code appBarColor}，其余区域使用
     * {@code pageColor}；{@code bottomContent} 的底部内边距叠加系统导航栏高度，
     * 保证可滚动内容不会被手势小横条遮挡。
     */
    public static void applyScreenInsets(@NonNull View root, @Nullable View bottomContent,
                                         int appBarColor, int pageColor) {
        if (bottomContent == null) {
            // 底部安全区由页面内部的滚动视图自行预留，这里只处理顶部与左右。
            uncheckedApplyTopAndSideInsets(root, appBarColor, pageColor);
            return;
        }
        markHandled(root);
        final int initialLeft = root.getPaddingLeft();
        final int initialTop = root.getPaddingTop();
        final int initialRight = root.getPaddingRight();
        final int initialBottom = root.getPaddingBottom();
        final int contentLeft = bottomContent.getPaddingLeft();
        final int contentTop = bottomContent.getPaddingTop();
        final int contentRight = bottomContent.getPaddingRight();
        final int contentBottom = bottomContent.getPaddingBottom();
        final ChromeBackgroundDrawable chrome = appBarColor == 0 && pageColor == 0
                ? null : new ChromeBackgroundDrawable(appBarColor, pageColor);
        if (chrome != null) {
            root.setBackground(chrome);
        }
        ViewCompat.setOnApplyWindowInsetsListener(root, (view, insets) -> {
            Insets bars = systemBarInsets(insets);
            if (chrome != null) {
                chrome.setTopHeight(bars.top);
            }
            view.setPadding(initialLeft + bars.left, initialTop + bars.top,
                    initialRight + bars.right, initialBottom);
            bottomContent.setPadding(contentLeft, contentTop,
                    contentRight, contentBottom + bars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });
        ViewCompat.requestApplyInsets(root);
    }

    // ------------------------------------------------------------------
    // 3. 系统栏尺寸读取
    // ------------------------------------------------------------------

    /** statusBars + displayCutout 合成后的四边内边距。 */
    public static Insets systemBarInsets(@NonNull WindowInsetsCompat insets) {
        return insets.getInsets(WindowInsetsCompat.Type.systemBars()
                | WindowInsetsCompat.Type.displayCutout());
    }

    /** 底部系统栏占位：导航栏（手势条/三键）与键盘取较大者。 */
    public static int bottomInset(@NonNull WindowInsetsCompat insets) {
        int navigation = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
        int ime = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom;
        return Math.max(navigation, ime);
    }

    /**
     * 系统导航栏底边高度。Android 15 起三键导航可能只通过 {@code tappableElement()} 上报，
     * 因此这里取两者较大值。
     */
    public static int navigationBarBottom(@NonNull WindowInsetsCompat insets) {
        int navigation = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
        int tappable = insets.getInsets(WindowInsetsCompat.Type.tappableElement()).bottom;
        return Math.max(navigation, tappable);
    }

    /**
     * 通过系统接口判断底部导航栏（手势条或三键栏）是否已经开启。
     *
     * <p>优先使用 {@link WindowInsetsCompat}（WindowInsets.Type.navigationBars / tappableElement）
     * 的实时上报；如果窗口尚未收到 insets，则回退到系统资源
     * {@code android:dimen/navigation_bar_height}。
     */
    public static boolean hasVisibleNavigationBar(@Nullable Context context,
                                                  @Nullable WindowInsetsCompat insets) {
        if (insets != null) {
            boolean visible = insets.isVisible(WindowInsetsCompat.Type.navigationBars())
                    || insets.isVisible(WindowInsetsCompat.Type.tappableElement());
            if (!visible) {
                return false;
            }
            if (navigationBarBottom(insets) > 0) {
                return true;
            }
        }
        return context != null && platformNavigationBarHeight(context) > 0;
    }

    /**
     * 读取系统 {@code navigation_bar_height} 尺寸资源，用于在 insets 尚未上报时判断
     * 导航栏是否存在。没有任何导航栏的设备上该资源为 0。
     */
    public static int platformNavigationBarHeight(@NonNull Context context) {
        int id = context.getResources().getIdentifier(
                "navigation_bar_height", "dimen", "android");
        return id > 0 ? context.getResources().getDimensionPixelSize(id) : 0;
    }

    // ------------------------------------------------------------------
    // 4. 底部弹窗
    // ------------------------------------------------------------------

    /**
     * 底部弹窗的底部内边距。
     *
     * <p>导航栏（含手势小横条）已开启时，系统 inset 本身就是视觉留白，只保留基础留白；
     * 没有导航栏时才额外补上原来“半个底部按钮高度”的间距。
     */
    public static int bottomSheetPadding(@NonNull Context context,
                                         @NonNull WindowInsetsCompat insets) {
        int extra = hasVisibleNavigationBar(context, insets)
                ? 0
                : dp(context, SHEET_BUTTON_HEIGHT_DP / 2);
        return dp(context, SHEET_BASE_BOTTOM_DP) + bottomInset(insets) + extra;
    }

    /**
     * 接管 BottomSheet 自身的 insets 处理。
     *
     * <p>Material 的 {@code BottomSheetBehavior} 会根据
     * {@code paddingBottomSystemWindowInsets} 再给 {@code design_bottom_sheet} 叠加一份导航栏
     * 内边距，与内容视图上的内边距重复。样式里已经把该开关关掉
     * （见 {@code Widget.WGPro.BottomSheet.Modal}），这里再清一次工作表内边距，
     * 保证任何版本/任何 ROM 上系统导航栏占位都只计算一次。
     */
    public static void ownSheetInsets(@NonNull final View sheet) {
        sheet.setFitsSystemWindows(false);
        ViewCompat.setOnApplyWindowInsetsListener(sheet, (view, insets) -> {
            if (view.getPaddingLeft() != 0 || view.getPaddingTop() != 0
                    || view.getPaddingRight() != 0 || view.getPaddingBottom() != 0) {
                view.setPadding(0, 0, 0, 0);
            }
            return insets;
        });
        ViewCompat.requestApplyInsets(sheet);
    }

    // ------------------------------------------------------------------
    // 5. 工具
    // ------------------------------------------------------------------

    /** 判断当前页面是否处于多窗口/自由窗。 */
    public static boolean isInMultiWindow(@NonNull View view) {
        Activity activity = activityOf(view);
        return activity != null && activity.isInMultiWindowMode();
    }

    /** 从任意 View 反查所属 Activity。 */
    @Nullable
    public static Activity activityOf(@NonNull View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            Context base = ((ContextWrapper) context).getBaseContext();
            if (base == null || base == context) {
                break;
            }
            context = base;
        }
        return context instanceof Activity ? (Activity) context : null;
    }

    private static void markHandled(@NonNull View view) {
        Activity activity = activityOf(view);
        if (activity != null) {
            HANDLED_ACTIVITIES.add(activity);
        }
    }

    private static void applyFallbackInsets(@NonNull Activity activity) {
        if (HANDLED_ACTIVITIES.contains(activity) || FALLBACK_ACTIVITIES.contains(activity)) {
            return;
        }
        View content = activity.findViewById(android.R.id.content);
        if (!(content instanceof ViewGroup)) {
            return;
        }
        ViewGroup group = (ViewGroup) content;
        if (group.getChildCount() == 0) {
            return;
        }
        FALLBACK_ACTIVITIES.add(activity);
        applyScreenInsets(group.getChildAt(0));
    }

    private static boolean isLightMode(@NonNull Context context) {
        int nightMode = context.getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        return nightMode != Configuration.UI_MODE_NIGHT_YES;
    }

    private static int dp(@NonNull View view, int value) {
        return Math.round(value * view.getResources().getDisplayMetrics().density);
    }

    private static int dp(@NonNull Context context, int value) {
        return Math.round(value * context.getResources().getDisplayMetrics().density);
    }

    /**
     * 两段式背景：顶部系统栏区域使用应用栏颜色，其余区域使用页面颜色，
     * 让 edge-to-edge 页面的系统栏底色和页面语义色一致。
     */
    private static final class ChromeBackgroundDrawable extends Drawable {
        private final Paint paint = new Paint();
        private final int chromeColor;
        private final int pageColor;
        private int topHeight;

        ChromeBackgroundDrawable(int chromeColor, int pageColor) {
            this.chromeColor = chromeColor;
            this.pageColor = pageColor;
        }

        void setTopHeight(int value) {
            if (topHeight == value) {
                return;
            }
            topHeight = Math.max(0, value);
            invalidateSelf();
        }

        @Override
        public void draw(@NonNull Canvas canvas) {
            canvas.drawColor(pageColor);
            if (topHeight > 0) {
                paint.setColor(chromeColor);
                canvas.drawRect(getBounds().left, getBounds().top,
                        getBounds().right, getBounds().top + topHeight, paint);
            }
        }

        @Override
        public void setAlpha(int alpha) {
            paint.setAlpha(alpha);
        }

        @Override
        public void setColorFilter(@Nullable ColorFilter colorFilter) {
            paint.setColorFilter(colorFilter);
        }

        @Override
        public int getOpacity() {
            return PixelFormat.OPAQUE;
        }
    }
}
