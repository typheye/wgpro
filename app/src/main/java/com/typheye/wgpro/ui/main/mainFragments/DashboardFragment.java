package com.typheye.wgpro.ui.main.mainFragments;

import android.content.Context;
import android.content.ClipData;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.function.ScanQRActivity;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.ui.main.MainActivity;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DashboardFragment extends Fragment {
    private static final String PREFS_NAME = "discover_preferences";
    private static final String KEY_FAVORITES = "favorite_functions";
    private static final String KEY_PENDING_ORDER = "pending_function_order";
    private static final int MAX_FAVORITES = 6;

    private final List<FunctionItem> functions = Arrays.asList(
            new FunctionItem("transfer", "文件传输", "发送应用、图片与资源到腕上设备",
                    R.drawable.bg_function_initial_blue, R.color.banner_blue, 158),
            new FunctionItem("scan", "扫码授权", "扫描登录或设备授权二维码",
                    R.drawable.bg_function_initial_coral, R.color.banner_coral, 140),
            new FunctionItem("guide", "使用指南", "从连接到安装，快速了解主要能力",
                    R.drawable.bg_function_initial_green, R.color.banner_green, 164),
            new FunctionItem("diagnostics", "连接诊断", "检查穿戴通道、设备与权限状态",
                    R.drawable.bg_function_initial_blue, R.color.banner_blue, 146),
            new FunctionItem("logs", "运行日志", "查看当前会话中的关键运行记录",
                    R.drawable.bg_function_initial_amber, R.color.banner_amber, 160),
            new FunctionItem("beta", "内测计划", "抢先体验新功能并参与共创",
                    R.drawable.bg_function_initial_green, R.color.banner_green, 142)
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

    public void showEditor() {
        if (!isAdded()) return;
        LinearLayout editor = new LinearLayout(requireContext());
        editor.setOrientation(LinearLayout.VERTICAL);

        ScrollView scroll = new ScrollView(requireContext());
        scroll.setClipToPadding(false);
        LinearLayout grids = new LinearLayout(requireContext());
        grids.setOrientation(LinearLayout.VERTICAL);
        GridLayout favorites = editorGrid();
        grids.addView(favorites, new LinearLayout.LayoutParams(-1, -2));
        TextView pendingLabel = text("待添加", 14, R.color.text_secondary, true);
        LinearLayout.LayoutParams pendingLabelParams = new LinearLayout.LayoutParams(-1, -2);
        pendingLabelParams.topMargin = dp(16);
        pendingLabelParams.bottomMargin = dp(4);
        grids.addView(pendingLabel, pendingLabelParams);
        GridLayout pending = editorGrid();
        grids.addView(pending, new LinearLayout.LayoutParams(-1, -2));
        scroll.addView(grids, new ScrollView.LayoutParams(-1, -2));
        LinearLayout.LayoutParams scrollParams = new LinearLayout.LayoutParams(-1,
                Math.min(dp(400), Math.round(getResources().getDisplayMetrics().heightPixels * 0.5f)));
        editor.addView(scroll, scrollParams);
        renderEditorGrids(favorites, pending);

        new WGProAlertDialogBuilder(requireContext())
                .setTitle("编辑常用功能")
                .setView(editor)
                .setNegativeButton("关闭", null)
                .show();
    }

    private void renderPage() {
        if (favoritesGrid == null) return;
        List<String> favorites = loadFavorites();
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
        moreContainer.setVisibility(favorites.size() >= functions.size() ? View.GONE : View.VISIBLE);
        LinearLayout.LayoutParams moreParams = (LinearLayout.LayoutParams) moreContainer.getLayoutParams();
        moreParams.topMargin = favorites.isEmpty() ? 0 : dp(14);
        moreContainer.setLayoutParams(moreParams);
        int leftHeight = 0;
        int rightHeight = 0;
        for (FunctionItem item : functions) {
            if (favorites.contains(item.id)) continue;
            LinearLayout target = leftHeight <= rightHeight ? moreLeft : moreRight;
            View card = createMoreCard(item);
            LinearLayout.LayoutParams cardParams = (LinearLayout.LayoutParams) card.getLayoutParams();
            if (target.getChildCount() == 0 && favorites.isEmpty()) cardParams.topMargin = 0;
            card.setLayoutParams(cardParams);
            target.addView(card);
            if (target == moreLeft) leftHeight += item.cardHeightDp;
            else rightHeight += item.cardHeightDp;
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
        params.setMargins(dp(4), dp(4), dp(4), dp(4));
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
        return card;
    }

    private MaterialCardView baseCard() {
        MaterialCardView card = new MaterialCardView(requireContext());
        card.setCardBackgroundColor(requireContext().getColor(R.color.surface_primary));
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

    private void renderEditorGrids(GridLayout favoritesGrid, GridLayout pendingGrid) {
        List<String> favorites = loadFavorites();
        List<String> pending = loadPending(favorites);
        favoritesGrid.removeAllViews();
        pendingGrid.removeAllViews();
        for (int index = 0; index < MAX_FAVORITES; index++) {
            FunctionItem item = index < favorites.size() ? findItem(favorites.get(index)) : null;
            favoritesGrid.addView(createEditorTile(item, true, index, favoritesGrid, pendingGrid),
                    editorTileParams(index));
            FunctionItem pendingItem = index < pending.size() ? findItem(pending.get(index)) : null;
            pendingGrid.addView(createEditorTile(pendingItem, false, index, favoritesGrid, pendingGrid),
                    editorTileParams(index));
        }
    }

    private View createEditorTile(@Nullable FunctionItem item, boolean favoriteZone, int index,
                                  GridLayout favoritesGrid, GridLayout pendingGrid) {
        MaterialCardView card = baseCard();
        card.setRadius(dp(12));
        card.setCardBackgroundColor(requireContext().getColor(R.color.surface_secondary));
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
            card.setOnClickListener(v -> moveEditorItem(item.id, !favoriteZone,
                    favoriteZone ? loadPending(loadFavorites()).size() : loadFavorites().size(),
                    favoritesGrid, pendingGrid));
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
                        moveEditorItem((String) localState, favoriteZone, index,
                                favoritesGrid, pendingGrid);
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
        params.setMargins(dp(4), dp(4), dp(4), dp(4));
        return params;
    }

    private void moveEditorItem(String id, boolean toFavorites, int targetIndex,
                                GridLayout favoritesGrid, GridLayout pendingGrid) {
        List<String> favorites = loadFavorites();
        List<String> pending = loadPending(favorites);
        favorites.remove(id);
        pending.remove(id);
        if (toFavorites) {
            int insertion = Math.max(0, Math.min(targetIndex, favorites.size()));
            favorites.add(insertion, id);
            if (favorites.size() > MAX_FAVORITES) {
                pending.add(0, favorites.remove(favorites.size() - 1));
            }
        } else {
            pending.add(Math.max(0, Math.min(targetIndex, pending.size())), id);
        }
        saveFavorites(favorites);
        savePending(pending);
        renderPage();
        renderEditorGrids(favoritesGrid, pendingGrid);
    }

    private void runFunction(FunctionItem item) {
        switch (item.id) {
            case "transfer": openWeb("https://wgpro.typheye.cn/push"); break;
            case "scan": startActivity(new Intent(requireContext(), ScanQRActivity.class)); break;
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

    private List<String> loadPending(List<String> favorites) {
        String stored = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .getString(KEY_PENDING_ORDER, "");
        List<String> result = new ArrayList<>();
        if (stored != null && !stored.trim().isEmpty()) {
            for (String id : stored.split(",")) {
                if (findItem(id) != null && !favorites.contains(id) && !result.contains(id)) {
                    result.add(id);
                }
            }
        }
        for (FunctionItem item : functions) {
            if (!favorites.contains(item.id) && !result.contains(item.id)) result.add(item.id);
        }
        return result;
    }

    private void savePending(List<String> pending) {
        requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
                .putString(KEY_PENDING_ORDER, android.text.TextUtils.join(",", pending)).apply();
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
        final int cardHeightDp;

        FunctionItem(String id, String title, String subtitle, @DrawableRes int initialBackground,
                     @ColorRes int initialTextColor, int cardHeightDp) {
            this.id = id;
            this.title = title;
            this.subtitle = subtitle;
            this.initialBackground = initialBackground;
            this.initialTextColor = initialTextColor;
            this.cardHeightDp = cardHeightDp;
        }
    }
}
