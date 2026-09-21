package com.typheye.wgpro.ui.main.mainFragments;

import android.content.Context;
import android.content.ClipData;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.ScanQRActivity;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.ui.main.MainActivity;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProBottomSheetDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 发现：常用功能宫格 + 更多功能瀑布卡。
 * 更多卡长按弹分组菜单（添加到常用 / 移除），编辑弹窗支持拖拽排序与拖入删除区移除，
 * 右上角"+"可把已移除、未在发现页显示的功能重新加回。
 */
public class DashboardFragment extends Fragment {
    private static final String PREFS_NAME = "discover_preferences";
    private static final String KEY_FAVORITES = "favorite_functions";
    private static final String KEY_HIDDEN = "hidden_functions";
    private static final int MAX_FAVORITES = 6;

    private final List<FunctionItem> functions = Arrays.asList(
            new FunctionItem("transfer", "文件传输", "发送应用、图片与资源到腕上设备",
                    R.drawable.bg_function_initial_blue, R.color.banner_blue),
            new FunctionItem("scan", "扫码授权", "扫描登录或设备授权二维码",
                    R.drawable.bg_function_initial_coral, R.color.banner_coral),
            new FunctionItem("guide", "使用指南", "从连接到安装，快速了解主要能力",
                    R.drawable.bg_function_initial_green, R.color.banner_green),
            new FunctionItem("diagnostics", "连接诊断", "检查穿戴通道、设备与权限状态",
                    R.drawable.bg_function_initial_blue, R.color.banner_blue),
            new FunctionItem("logs", "运行日志", "查看当前会话中的关键运行记录",
                    R.drawable.bg_function_initial_amber, R.color.banner_amber),
            new FunctionItem("beta", "内测计划", "抢先体验新功能并参与共创",
                    R.drawable.bg_function_initial_green, R.color.banner_green)
    );

    private GridLayout favoritesGrid;
    private LinearLayout moreContainer;
    private LinearLayout moreLeft;
    private LinearLayout moreRight;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        favoritesGrid = view.findViewById(R.id.discover_favorites_grid);
        moreContainer = view.findViewById(R.id.discover_more_container);
        moreLeft = view.findViewById(R.id.discover_more_left);
        moreRight = view.findViewById(R.id.discover_more_right);
        renderPage();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        renderPage();
    }

    /** 编辑常用功能：只保留排序与拖入删除区移除。 */
    public void showEditor() {
        if (!isAdded()) return;
        LinearLayout editor = new LinearLayout(requireContext());
        editor.setOrientation(LinearLayout.VERTICAL);

        GridLayout favorites = editorGrid();
        editor.addView(favorites, new LinearLayout.LayoutParams(-1, -2));

        View deleteZone = createDeleteZone(favorites);
        LinearLayout.LayoutParams deleteParams = new LinearLayout.LayoutParams(-1, dp(82));
        deleteParams.topMargin = dp(12);
        editor.addView(deleteZone, deleteParams);

        renderEditorGrid(favorites);

        new WGProAlertDialogBuilder(requireContext())
                .setTitle("编辑常用功能")
                .setView(editor)
                .setNegativeButton("关闭", null)
                .show();
    }

    /** 右上角"+"：列出被移除、未在发现页显示的功能，点击重新显示。 */
    public void showAddSheet() {
        if (!isAdded()) return;
        List<String> hidden = loadHidden();
        List<FunctionItem> addable = new ArrayList<>();
        for (FunctionItem item : functions) {
            if (hidden.contains(item.id)) addable.add(item);
        }
        if (addable.isEmpty()) {
            new WGProAlertDialogBuilder(requireContext())
                    .setTitle("添加功能")
                    .setMessage("所有功能都已在发现页显示。")
                    .setPositiveButton("完成", null)
                    .show();
            return;
        }

        final WGProBottomSheetDialog[] shown = new WGProBottomSheetDialog[1];
        LinearLayout list = new LinearLayout(requireContext());
        list.setOrientation(LinearLayout.VERTICAL);
        for (int index = 0; index < addable.size(); index++) {
            FunctionItem item = addable.get(index);
            LinearLayout row = addableRow(item);
            row.setOnClickListener(v -> {
                if (shown[0] != null) shown[0].dismissForReplacement();
                restoreFunction(item);
            });
            list.addView(row, new LinearLayout.LayoutParams(-1, dp(64)));
            if (index < addable.size() - 1) {
                View divider = new View(requireContext());
                divider.setBackgroundColor(requireContext().getColor(R.color.outline_soft));
                list.addView(divider, new LinearLayout.LayoutParams(-1, dp(1)));
            }
        }
        MaterialCardView group = new MaterialCardView(requireContext());
        group.setRadius(dp(18));
        group.setCardElevation(0f);
        group.setStrokeWidth(0);
        group.setUseCompatPadding(false);
        group.setPreventCornerOverlap(false);
        group.setCardBackgroundColor(requireContext().getColor(R.color.surface_secondary));
        group.setClipToOutline(true);
        group.addView(list, new MaterialCardView.LayoutParams(-1, -2));

        WGProBottomSheetDialog dialog = new WGProAlertDialogBuilder(requireContext())
                .setTitle("添加功能")
                .setView(group)
                .create();
        shown[0] = dialog;
        dialog.show();
    }

    private void renderPage() {
        if (favoritesGrid == null) return;
        List<String> favorites = loadFavorites();
        List<String> hidden = loadHidden();
        favoritesGrid.setVisibility(favorites.isEmpty() ? View.GONE : View.VISIBLE);
        favoritesGrid.removeAllViews();
        if (!favorites.isEmpty()) {
            int rows = (favorites.size() + 2) / 3;
            favoritesGrid.setRowCount(rows);
            for (int index = 0; index < rows * 3; index++) {
                View slot;
                if (index < favorites.size()) {
                    slot = createFavoriteSlot(findItem(favorites.get(index)));
                } else {
                    slot = new View(requireContext());
                    slot.setVisibility(View.INVISIBLE);
                }
                favoritesGrid.addView(slot, favoriteSlotParams(index));
            }
        }

        moreLeft.removeAllViews();
        moreRight.removeAllViews();
        List<FunctionItem> moreItems = new ArrayList<>();
        for (FunctionItem item : functions) {
            if (favorites.contains(item.id) || hidden.contains(item.id)) continue;
            moreItems.add(item);
        }
        moreContainer.setVisibility(moreItems.isEmpty() ? View.GONE : View.VISIBLE);
        LinearLayout.LayoutParams moreParams = (LinearLayout.LayoutParams) moreContainer.getLayoutParams();
        moreParams.topMargin = favorites.isEmpty() ? 0 : dp(14);
        moreContainer.setLayoutParams(moreParams);
        // 行优先两列排布（左、右、左、右……），避免瀑布式高低错落留下的空洞
        for (int index = 0; index < moreItems.size(); index++) {
            FunctionItem item = moreItems.get(index);
            LinearLayout target = index % 2 == 0 ? moreLeft : moreRight;
            View card = createMoreCard(item);
            LinearLayout.LayoutParams cardParams = (LinearLayout.LayoutParams) card.getLayoutParams();
            // 每列第一张卡片不再叠加顶部间距，顶部 gap 由页面 padding / moreContainer 统一控制
            if (target.getChildCount() == 0) cardParams.topMargin = 0;
            card.setLayoutParams(cardParams);
            target.addView(card);
        }
    }

    private View createFavoriteSlot(@Nullable FunctionItem item) {
        MaterialCardView card = baseCard();
        card.setRadius(dp(8));
        if (item == null) {
            card.setCardBackgroundColor(Color.TRANSPARENT);
            LinearLayout empty = new LinearLayout(requireContext());
            empty.setGravity(Gravity.CENTER);
            empty.setBackgroundResource(R.drawable.bg_empty_device);
            TextView label = text("", 13, R.color.text_tertiary, false);
            empty.addView(label);
            empty.setOnClickListener(v -> showEditor());
            card.addView(empty, new MaterialCardView.LayoutParams(-1, -1));
            return card;
        }

        LinearLayout content = new LinearLayout(requireContext());
        content.setOrientation(LinearLayout.VERTICAL);
        content.setGravity(Gravity.CENTER);
        content.setPadding(dp(6), dp(10), dp(6), dp(8));
        content.addView(createInitial(item, 40), new LinearLayout.LayoutParams(dp(40), dp(40)));
        TextView label = text(item.title, 13, R.color.text_primary, true);
        label.setGravity(Gravity.CENTER);
        label.setMaxLines(1);
        LinearLayout.LayoutParams labelParams = new LinearLayout.LayoutParams(-1, -2);
        labelParams.topMargin = dp(8);
        content.addView(label, labelParams);
        card.addView(content, new MaterialCardView.LayoutParams(-1, -1));
        card.setOnClickListener(v -> runFunction(item));
        return card;
    }

    private GridLayout.LayoutParams favoriteSlotParams(int index) {
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = dp(96);
        params.columnSpec = GridLayout.spec(index % 3, 1f);
        params.rowSpec = GridLayout.spec(index / 3);
        // 左右最外侧与下方双列卡片对齐（无外边距），列间距 8dp
        int left = index % 3 == 0 ? 0 : dp(4);
        int right = index % 3 == 2 ? 0 : dp(4);
        params.setMargins(left, dp(4), right, dp(4));
        return params;
    }

    private View createMoreCard(FunctionItem item) {
        MaterialCardView card = baseCard();
        card.setRadius(dp(8));
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(-1, -2);
        cardParams.topMargin = dp(12);
        card.setLayoutParams(cardParams);

        LinearLayout content = new LinearLayout(requireContext());
        content.setOrientation(LinearLayout.VERTICAL);
        content.setGravity(Gravity.TOP);
        content.setPadding(dp(16), dp(16), dp(16), dp(16));
        content.addView(createInitial(item, 46), new LinearLayout.LayoutParams(dp(46), dp(46)));
        TextView title = text(item.title, 16, R.color.text_primary, true);
        LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(-1, -2);
        titleParams.topMargin = dp(14);
        content.addView(title, titleParams);
        TextView subtitle = text(item.subtitle, 13, R.color.text_secondary, false);
        LinearLayout.LayoutParams subtitleParams = new LinearLayout.LayoutParams(-1, -2);
        subtitleParams.topMargin = dp(4);
        content.addView(subtitle, subtitleParams);
        card.addView(content, new MaterialCardView.LayoutParams(-1, -1));
        card.setOnClickListener(v -> runFunction(item));
        card.setOnLongClickListener(v -> {
            showCardActions(item);
            return true;
        });
        return card;
    }

    private MaterialCardView baseCard() {
        MaterialCardView card = new MaterialCardView(requireContext());
        card.setCardBackgroundColor(requireContext().getColor(R.color.surface_secondary));
        card.setCardElevation(0);
        card.setStrokeWidth(0);
        card.setClickable(true);
        card.setFocusable(true);
        card.setRippleColor(ColorStateList.valueOf(requireContext().getColor(R.color.brand_soft)));
        return card;
    }

    private TextView createInitial(FunctionItem item, int sizeDp) {
        TextView initial = text(item.title.substring(0, 1), sizeDp >= 46 ? 20 : 18,
                item.initialTextColor, true);
        initial.setGravity(Gravity.CENTER);
        initial.setBackgroundResource(item.initialBackground);
        return initial;
    }

    private GridLayout editorGrid() {
        GridLayout grid = new GridLayout(requireContext());
        grid.setColumnCount(3);
        grid.setRowCount(2);
        return grid;
    }

    private void renderEditorGrid(GridLayout favoritesGrid) {
        List<String> favorites = loadFavorites();
        favoritesGrid.removeAllViews();
        for (int index = 0; index < MAX_FAVORITES; index++) {
            FunctionItem item = index < favorites.size() ? findItem(favorites.get(index)) : null;
            favoritesGrid.addView(createEditorTile(item, index, favoritesGrid),
                    editorTileParams(index));
        }
    }

    private View createEditorTile(@Nullable FunctionItem item, int index, GridLayout favoritesGrid) {
        MaterialCardView card = baseCard();
        card.setRadius(dp(12));
        card.setCardBackgroundColor(dialogCardColor());
        if (item == null) {
            TextView empty = text("", 12, R.color.text_tertiary, false);
            empty.setGravity(Gravity.CENTER);
            empty.setBackgroundResource(R.drawable.bg_empty_device);
            card.addView(empty, new MaterialCardView.LayoutParams(-1, -1));
        } else {
            LinearLayout content = new LinearLayout(requireContext());
            content.setOrientation(LinearLayout.VERTICAL);
            content.setGravity(Gravity.CENTER);
            content.setPadding(dp(4), dp(8), dp(4), dp(6));
            content.addView(createInitial(item, 36), new LinearLayout.LayoutParams(dp(36), dp(36)));
            TextView title = text(item.title, 12, R.color.text_primary, true);
            title.setGravity(Gravity.CENTER);
            title.setSingleLine(true);
            LinearLayout.LayoutParams titleParams = new LinearLayout.LayoutParams(-1, -2);
            titleParams.topMargin = dp(6);
            content.addView(title, titleParams);
            card.addView(content, new MaterialCardView.LayoutParams(-1, -1));
            card.setOnLongClickListener(v -> {
                ClipData data = ClipData.newPlainText("function_id", item.id);
                v.startDragAndDrop(data, new View.DragShadowBuilder(v), item.id, 0);
                v.animate().alpha(0.45f).setDuration(80).start();
                return true;
            });
        }
        card.setOnDragListener((target, event) -> {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    return event.getClipDescription() != null
                            && event.getClipDescription().hasMimeType("text/plain");
                case DragEvent.ACTION_DRAG_ENTERED:
                    card.setStrokeColor(requireContext().getColor(R.color.brand_primary));
                    card.setStrokeWidth(dp(2));
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    card.setStrokeWidth(0);
                    return true;
                case DragEvent.ACTION_DROP:
                    card.setStrokeWidth(0);
                    Object localState = event.getLocalState();
                    if (localState instanceof String) {
                        moveFavorite((String) localState, index, favoritesGrid);
                    }
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    card.setStrokeWidth(0);
                    card.animate().alpha(1f).setDuration(100).start();
                    return true;
                default:
                    return true;
            }
        });
        return card;
    }

    private GridLayout.LayoutParams editorTileParams(int index) {
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.width = 0;
        params.height = dp(82);
        params.columnSpec = GridLayout.spec(index % 3, 1f);
        params.rowSpec = GridLayout.spec(index / 3);
        // 左右最外侧与删除区/底部按钮对齐（无外边距），列间距 8dp
        int left = index % 3 == 0 ? 0 : dp(4);
        int right = index % 3 == 2 ? 0 : dp(4);
        params.setMargins(left, dp(4), right, dp(4));
        return params;
    }

    private View createDeleteZone(GridLayout favoritesGrid) {
        TextView zone = new TextView(requireContext());
        zone.setText("拖动到此删除");
        zone.setTextSize(14);
        zone.setTypeface(null, android.graphics.Typeface.BOLD);
        zone.setTextColor(deleteZoneStrokeColor());
        zone.setGravity(Gravity.CENTER);
        zone.setBackground(deleteZoneBackground(false));
        zone.setOnDragListener((target, event) -> {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    return event.getClipDescription() != null
                            && event.getClipDescription().hasMimeType("text/plain");
                case DragEvent.ACTION_DRAG_ENTERED:
                    zone.setBackground(deleteZoneBackground(true));
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    zone.setBackground(deleteZoneBackground(false));
                    return true;
                case DragEvent.ACTION_DROP:
                    zone.setBackground(deleteZoneBackground(false));
                    Object localState = event.getLocalState();
                    if (localState instanceof String) {
                        removeFavorite((String) localState, favoritesGrid);
                    }
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    zone.setBackground(deleteZoneBackground(false));
                    return true;
                default:
                    return true;
            }
        });
        return zone;
    }

    /** 深色模式下弹窗表面跟随莫奈取色，卡片用高一档的容器色才不会显得突兀。 */
    private int dialogCardColor() {
        if (!nightMode()) return requireContext().getColor(R.color.surface_secondary);
        return WGProAlertDialogBuilder.resolveThemeColor(requireContext(),
                "colorSurfaceContainerHighest", R.color.surface_secondary);
    }

    private int deleteZoneStrokeColor() {
        if (!nightMode()) return requireContext().getColor(R.color.status_danger_muted);
        return WGProAlertDialogBuilder.resolveThemeColor(requireContext(),
                "colorError", R.color.status_danger_muted);
    }

    private Drawable deleteZoneBackground(boolean active) {
        if (!nightMode()) {
            return requireContext().getDrawable(active
                    ? R.drawable.bg_favorite_delete_active : R.drawable.bg_favorite_delete);
        }
        // 深色：错误色容器做底色，错误色描边/文字，随壁纸取色
        int fill = ColorUtils.setAlphaComponent(WGProAlertDialogBuilder.resolveThemeColor(
                requireContext(), "colorErrorContainer", R.color.status_danger), 0x40);
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(fill);
        shape.setCornerRadius(dp(12));
        shape.setStroke(Math.round((active ? 2.5f : 1.5f)
                        * getResources().getDisplayMetrics().density),
                deleteZoneStrokeColor(), dp(6), dp(4));
        return shape;
    }

    private boolean nightMode() {
        return (getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;
    }

    private void moveFavorite(String id, int targetIndex, GridLayout favoritesGrid) {
        List<String> favorites = loadFavorites();
        if (!favorites.remove(id)) return;
        favorites.add(Math.max(0, Math.min(targetIndex, favorites.size())), id);
        saveFavorites(favorites);
        renderPage();
        renderEditorGrid(favoritesGrid);
    }

    private void removeFavorite(String id, GridLayout favoritesGrid) {
        List<String> favorites = loadFavorites();
        if (!favorites.remove(id)) return;
        saveFavorites(favorites);
        renderPage();
        renderEditorGrid(favoritesGrid);
    }

    private void showCardActions(FunctionItem item) {
        if (!isAdded()) return;
        new WGProAlertDialogBuilder(requireContext())
                .setTitle(item.title)
                .setItems(new CharSequence[]{"添加到常用", "移除"}, (dialog, which) -> {
                    if (which == 0) addToFavorites(item);
                    else removeFromList(item);
                })
                .show();
    }

    private void addToFavorites(FunctionItem item) {
        List<String> favorites = loadFavorites();
        if (favorites.size() >= MAX_FAVORITES) {
            showFavoritesFull();
            return;
        }
        if (!favorites.contains(item.id)) favorites.add(item.id);
        saveFavorites(favorites);
        List<String> hidden = loadHidden();
        if (hidden.remove(item.id)) saveHidden(hidden);
        renderPage();
    }

    private void showFavoritesFull() {
        if (!isAdded()) return;
        new WGProAlertDialogBuilder(requireContext())
                .setTitle("常用功能已达上限")
                .setMessage("最多可添加 " + MAX_FAVORITES + " 个常用功能，请先在编辑中去掉一个。")
                .setPositiveButton("完成", null)
                .show();
    }

    private void removeFromList(FunctionItem item) {
        List<String> hidden = loadHidden();
        if (!hidden.contains(item.id)) hidden.add(item.id);
        saveHidden(hidden);
        renderPage();
    }

    private void restoreFunction(FunctionItem item) {
        List<String> hidden = loadHidden();
        if (hidden.remove(item.id)) saveHidden(hidden);
        renderPage();
    }

    private LinearLayout addableRow(FunctionItem item) {
        LinearLayout row = new LinearLayout(requireContext());
        row.setOrientation(LinearLayout.HORIZONTAL);
        row.setGravity(Gravity.CENTER_VERTICAL);
        row.setPadding(dp(18), 0, dp(16), 0);
        row.setClickable(true);
        row.setFocusable(true);
        row.setBackground(menuRipple());
        row.addView(createInitial(item, 36), new LinearLayout.LayoutParams(dp(36), dp(36)));
        TextView label = text(item.title, 16, R.color.text_primary, true);
        LinearLayout.LayoutParams labelParams = new LinearLayout.LayoutParams(0, -2, 1f);
        labelParams.leftMargin = dp(14);
        row.addView(label, labelParams);
        return row;
    }

    private RippleDrawable menuRipple() {
        int ripple = ColorUtils.setAlphaComponent(WGProAlertDialogBuilder.resolveThemeColor(
                requireContext(), "colorPrimary", R.color.brand_primary), 28);
        return new RippleDrawable(ColorStateList.valueOf(ripple),
                new ColorDrawable(Color.TRANSPARENT), new ColorDrawable(Color.WHITE));
    }

    private void runFunction(FunctionItem item) {
        switch (item.id) {
            case "transfer": openWeb("https://wgpro.typheye.cn/push"); break;
            case "scan":
                // 扫码授权必须带着当前账户去 approve，未登录先给"需要登录"提示。
                if (com.typheye.wgpro.ui.LoginGate.require(requireActivity(), "扫码授权")) {
                    startActivity(new Intent(requireContext(), ScanQRActivity.class));
                }
                break;
            case "guide": openWeb("https://wgpro.typheye.cn/docs"); break;
            case "diagnostics": showDiagnostics(); break;
            case "logs": new WGProAlertDialogBuilder(requireContext())
                    .setTitle("运行日志").setMessage(String.join("\n", MainActivity.logs))
                    .setPositiveButton("完成", null).show(); break;
            case "beta": openWeb("https://www.typheye.cn/?p=beta"); break;
            default: break;
        }
    }

    private List<String> loadFavorites() {
        String stored = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getString(KEY_FAVORITES, "");
        List<String> result = new ArrayList<>();
        if (stored == null || stored.trim().isEmpty()) return result;
        for (String id : stored.split(",")) {
            if (result.size() >= MAX_FAVORITES) break;
            if (findItem(id) != null && !result.contains(id)) result.add(id);
        }
        return result;
    }

    private void saveFavorites(List<String> favorites) {
        requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
                .putString(KEY_FAVORITES, android.text.TextUtils.join(",", favorites)).apply();
    }

    private List<String> loadHidden() {
        String stored = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getString(KEY_HIDDEN, "");
        List<String> result = new ArrayList<>();
        if (stored == null || stored.trim().isEmpty()) return result;
        for (String id : stored.split(",")) {
            if (findItem(id) != null && !result.contains(id)) result.add(id);
        }
        return result;
    }

    private void saveHidden(List<String> hidden) {
        requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
                .putString(KEY_HIDDEN, android.text.TextUtils.join(",", hidden)).apply();
    }

    @Nullable
    private FunctionItem findItem(String id) {
        for (FunctionItem item : functions) if (item.id.equals(id)) return item;
        return null;
    }

    private TextView text(String value, int sp, @ColorRes int color, boolean bold) {
        TextView view = new TextView(requireContext());
        view.setText(value);
        view.setTextSize(sp);
        view.setTextColor(requireContext().getColor(color));
        view.setLetterSpacing(0);
        if (bold) view.setTypeface(null, android.graphics.Typeface.BOLD);
        return view;
    }

    private int dp(int value) {
        return Math.round(value * getResources().getDisplayMetrics().density);
    }

    private void openWeb(String url) {
        Intent intent = new Intent(requireContext(), WebActivity.class);
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    private void showDiagnostics() {
        boolean connected = MainActivity.current_params.connected;
        String device = MainActivity.current_params.connected_device_name;
        String message = connected
                ? "穿戴服务正常\n当前设备：" + (device == null || device.isEmpty() ? "已连接设备" : device)
                    + "\n消息通道：可用"
                : "暂未发现穿戴设备\n请打开小米运动健康，并检查设备管理权限。";
        new WGProAlertDialogBuilder(requireContext())
                .setTitle("连接诊断").setMessage(message).setPositiveButton("完成", null).show();
    }

    private static final class FunctionItem {
        final String id;
        final String title;
        final String subtitle;
        @DrawableRes final int initialBackground;
        @ColorRes final int initialTextColor;

        FunctionItem(String id, String title, String subtitle, @DrawableRes int initialBackground,
                     @ColorRes int initialTextColor) {
            this.id = id;
            this.title = title;
            this.subtitle = subtitle;
            this.initialBackground = initialBackground;
            this.initialTextColor = initialTextColor;
        }
    }
}
