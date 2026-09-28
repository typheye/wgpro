package com.typheye.wgpro.ui.function.community;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.imageview.ShapeableImageView;
import com.typheye.wgpro.R;
import com.typheye.wgpro.data.MessageDatabase;
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.BadgeFactory;
import com.typheye.wgpro.ui.widget.UnreadBadgeFactory;
import com.typheye.wgpro.ui.function.account.UserDetailActivity;
import com.typheye.wgpro.utils.ImageCache;
import com.typheye.wgpro.utils.tAccUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class CloudListFragment extends Fragment {
    public static final String MODE_ACTIVITY = "activity";
    public static final String MODE_HISTORY = "history";
    public static final String MODE_COLLECTION_DYNAMIC = "collection_dynamic";
    public static final String MODE_COLLECTION_APP = "collection_app";
    public static final String MODE_COLLECTION_RESOURCE = "collection_resource";
    public static final String MODE_MY_RESOURCES = "my_resources";
    public static final String MODE_NOTIFICATIONS = "notifications";
    public static final String MODE_FOLLOWING = "following";
    public static final String MODE_FOLLOWERS = "followers";
    public static final String MODE_USER_APPS = "user_apps";
    public static final String MODE_USER_RESOURCES = "user_resources";
    public static final String MODE_CONVERSATIONS = "conversations";
    private static final long MIN_LOADING_MS = 300L;

    private final Handler main = new Handler(Looper.getMainLooper());
    private SwipeRefreshLayout refresh;
    private LinearLayout list;
    private View state;
    private TextView stateText;
    private TextView stateDescription;
    private boolean loadedOnce;
    private String mode;
    private String historyType = "dynamic";
    private tAccUtils account;
    private MessageDatabase messageDb;
    private int requestGeneration;
    private boolean skipNextResumeReload;
    private JSONArray renderedItems;
    /** 上次渲染的数据签名：相同就不重建视图，避免刷新时图片闪动。 */
    private String lastRenderSignature;

    /** 关注/粉丝列表：滚动到底自动加载下一页（服务端分页，默认 20/页、上限 50）。 */
    private androidx.core.widget.NestedScrollView listScroll;
    private boolean pagedMode;
    private int pagedPage = 1;
    private boolean pagedHasMore;
    private boolean pagedLoading;

    public static CloudListFragment newInstance(String mode) {
        return newInstance(mode, null);
    }

    public static CloudListFragment newInstance(String mode, @Nullable String targetUid) {
        CloudListFragment fragment = new CloudListFragment();
        Bundle args = new Bundle();
        args.putString("mode", mode);
        if (targetUid != null) args.putString("target_uid", targetUid);
        fragment.setArguments(args);
        return fragment;
    }

    public static CloudListFragment newHistoryPage(String type) {
        CloudListFragment fragment = newInstance(MODE_HISTORY);
        Bundle args = fragment.getArguments();
        if (args != null) args.putString("history_type", type);
        return fragment;
    }

    public static CloudListFragment newContactInstance(String mode, int expectedCount) {
        CloudListFragment fragment = newInstance(mode);
        fragment.requireArguments().putInt("expected_count", expectedCount);
        return fragment;
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater,
                                                  @Nullable ViewGroup container,
                                                  @Nullable Bundle stateBundle) {
        View root = inflater.inflate(R.layout.fragment_cloud_list, container, false);
        // 视图重建后必须重新渲染（签名要清空，否则会被当成“数据没变”而跳过）
        lastRenderSignature = null;
        mode = getArguments() == null ? MODE_ACTIVITY : getArguments().getString("mode", MODE_ACTIVITY);
        if (getArguments() != null) historyType = getArguments().getString("history_type", "dynamic");
        pagedMode = MODE_FOLLOWING.equals(mode) || MODE_FOLLOWERS.equals(mode);
        account = new tAccUtils(requireContext().getApplicationContext());
        messageDb = new MessageDatabase(requireContext());
        refresh = root.findViewById(R.id.cloud_refresh);
        refresh.setColorSchemeColors(requireContext().getColor(R.color.brand_primary));
        list = root.findViewById(R.id.cloud_list);
        state = root.findViewById(R.id.cloud_state);
        stateText = state.findViewById(R.id.stream_empty_title);
        stateDescription = state.findViewById(R.id.stream_empty_description);
        // 空状态是盖在列表上方的覆盖层（不在滚动视图里），要跟着滚动容器的上下留白一起移动，
        // 否则会被应用栏/子标签盖住，看起来像"页面错位/空白"。
        // 注意：这里不再给自己的滚动视图补导航栏底部留白——二级页由宿主 Activity 统一处理，
        // 用户主页的抽屉里补了会让底部露出一条纯色（在深色模式下就是一条黑边）。
        final View stateContainer = (View) state.getParent();
        listScroll = root.findViewById(R.id.cloud_scroll);
        listScroll.getViewTreeObserver().addOnScrollChangedListener(() -> {
            if (!pagedMode || !pagedHasMore || pagedLoading) return;
            View content = listScroll.getChildAt(0);
            if (content == null) return;
            int remaining = content.getHeight() - (listScroll.getScrollY() + listScroll.getHeight());
            if (remaining < dp(160)) {
                loadContactsPage(pagedPage + 1, true,
                        android.os.SystemClock.uptimeMillis(), requestGeneration);
            }
        });
        root.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            int scrollTop = listScroll.getPaddingTop();
            int scrollBottom = listScroll.getPaddingBottom();
            if (stateContainer.getPaddingTop() != scrollTop
                    || stateContainer.getPaddingBottom() != scrollBottom) {
                stateContainer.setPadding(stateContainer.getPaddingLeft(), scrollTop,
                        stateContainer.getPaddingRight(), scrollBottom);
            }
        });
        refresh.setOnRefreshListener(() -> {
            load();
            if (requireActivity() instanceof UserDetailActivity) {
                ((UserDetailActivity) requireActivity()).refreshProfileFromTabs();
            }
        });
        load();
        return root;
    }

    @Override public void onResume() {
        super.onResume();
        if (MODE_NOTIFICATIONS.equals(mode) || MODE_CONVERSATIONS.equals(mode)) {
            refreshConversationPreviewsFromLocal();
        }
        if (loadedOnce && !skipNextResumeReload) load();
        skipNextResumeReload = false;
    }

    private void refreshConversationPreviewsFromLocal() {
        if (renderedItems == null) return;
        boolean changed = false;
        for (int i = 0; i < renderedItems.length(); i++) {
            JSONObject item = renderedItems.optJSONObject(i);
            if (item == null || !"conversation".equals(item.optString("_kind"))) continue;
            String peerUid = item.optString("peer_uid", "");
            if (peerUid.isEmpty()) continue;
            MessageDatabase.ConversationPreview preview =
                    messageDb.getConversationPreview(account.getUid(), peerUid);
            if (preview == null) continue;
            String localMessage = preview.message == null ? "" : preview.message;
            if (!localMessage.equals(item.optString("last_message", ""))) {
                try {
                    item.put("last_message", localMessage);
                    changed = true;
                } catch (Exception ignored) { }
            }
        }
        if (changed) render(renderedItems);
    }

    public void skipNextResumeReload() {
        skipNextResumeReload = true;
    }

    public void markSystemMessagesReadLocally() {
        if (renderedItems == null) return;
        setUnreadOverride("system", false);
        for (int i = 0; i < renderedItems.length(); i++) {
            JSONObject item = renderedItems.optJSONObject(i);
            if (item == null || !"system_messages".equals(item.optString("_kind"))) continue;
            try {
                item.put("unread_count", 0);
                JSONArray messages = item.optJSONArray("_system_items");
                if (messages != null) for (int j = 0; j < messages.length(); j++) {
                    JSONObject message = messages.optJSONObject(j);
                    if (message != null) message.put("is_read", true);
                }
            } catch (Exception ignored) { }
        }
        render(renderedItems);
    }

    public void clearConversations() {
        account.postV2Json("conversations_clear2", new LinkedHashMap<>(),
                new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        main.post(() -> {
                            if (isAdded() && getView() != null) load();
                        });
                    }

                    @Override public void onError(int code, @NonNull String message) { }
                });
    }

    public void clearMessageList() {
        clearAllUnreadOverrides();
        render(new JSONArray());
        java.util.concurrent.atomic.AtomicInteger pending =
                new java.util.concurrent.atomic.AtomicInteger(2);
        tAccUtils.JsonCallback finish = new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                if (pending.decrementAndGet() == 0) main.post(() -> {
                    if (isAdded() && getView() != null) load();
                });
            }

            @Override public void onError(int code, @NonNull String message) {
                if (pending.decrementAndGet() == 0) main.post(() -> {
                    if (isAdded() && getView() != null) load();
                });
            }
        };
        account.postV2Json("conversations_clear2", new LinkedHashMap<>(), finish);
        account.postV2Json("notifications_clear2", new LinkedHashMap<>(), finish);
    }

    private void showNotificationItemActions(JSONObject item) {
        boolean unread = item.optInt("unread_count", 0) > 0;
        String readLabel = unread ? "标为已读" : "标为未读";
        new WGProAlertDialogBuilder(requireContext()).setTitle("消息操作")
                .setItems(new CharSequence[]{"移除", readLabel}, (dialog, which) -> {
                    if (which == 0) confirmRemoveNotificationItem(item);
                    else confirmMarkNotificationItem(item, unread);
                }).show();
    }

    private void confirmRemoveNotificationItem(JSONObject item) {
        new WGProAlertDialogBuilder(requireContext())
                .setTitle("移除消息？")
                .setMessage("移除后该会话将从当前账户的通知列表中删除。")
                .setNegativeButton("取消", null)
                .setPositiveButton("移除", (dialog, which) -> removeNotificationItem(item))
                .show();
    }

    private void removeNotificationItem(JSONObject item) {
        if ("system_messages".equals(item.optString("_kind"))) {
            setUnreadOverride("system", false);
            messageDb.setSystemClearedAt(account.getUid(), System.currentTimeMillis());
            messageDb.setSystemRemoved(account.getUid(), true);
            removeRenderedSystemMessages();
            account.postV2Json("notifications_clear2", new LinkedHashMap<>(),
                    new tAccUtils.JsonCallback() {
                        @Override public void onSuccess(@NonNull JSONObject json) { load(); }
                        @Override public void onError(int code, @NonNull String message) { }
                    });
            return;
        }
        String peerUid = item.optString("peer_uid", "");
        if (peerUid.isEmpty()) return;
        setUnreadOverride("conversation_" + peerUid, false);
        messageDb.setConversationRemoved(account.getUid(), peerUid, true);
        removeRenderedConversation(peerUid);
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("peer_uid", peerUid);
        account.postV2Json("conversation_remove2", fields, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) { load(); }
            @Override public void onError(int code, @NonNull String message) { }
        });
    }

    private void removeRenderedSystemMessages() {
        if (renderedItems == null) return;
        JSONArray next = new JSONArray();
        for (int i = 0; i < renderedItems.length(); i++) {
            JSONObject item = renderedItems.optJSONObject(i);
            if (item == null || "system_messages".equals(item.optString("_kind"))) continue;
            next.put(item);
        }
        render(next);
    }

    private void removeRenderedConversation(String peerUid) {
        if (renderedItems == null) return;
        JSONArray next = new JSONArray();
        for (int i = 0; i < renderedItems.length(); i++) {
            JSONObject item = renderedItems.optJSONObject(i);
            if (item == null) continue;
            if ("conversation".equals(item.optString("_kind"))
                    && peerUid.equals(item.optString("peer_uid"))) continue;
            next.put(item);
        }
        render(next);
    }

    private void confirmMarkNotificationItem(JSONObject item, boolean currentlyUnread) {
        new WGProAlertDialogBuilder(requireContext())
                .setTitle(currentlyUnread ? "标为已读？" : "标为未读？")
                .setMessage(currentlyUnread
                        ? "标记后该消息将不再显示未读徽标。"
                        : "标记后该消息将显示数字 1 的未读徽标。")
                .setNegativeButton("取消", null)
                .setPositiveButton("确认", (dialog, which) -> {
                    String overrideKey = "system_messages".equals(item.optString("_kind"))
                            ? "system" : "conversation_" + item.optString("peer_uid", "");
                    if (currentlyUnread) {
                        setUnreadOverride(overrideKey, false);
                        if ("system_messages".equals(item.optString("_kind"))) {
                            Map<String, String> fields = new LinkedHashMap<>();
                            fields.put("action", "read_all");
                            account.postV2Json("notification_state2", fields,
                                    new tAccUtils.JsonCallback() {
                                        @Override public void onSuccess(@NonNull JSONObject json) {
                                            main.post(() -> {
                                                if (isAdded() && getView() != null) load();
                                            });
                                        }
                                        @Override public void onError(int code, @NonNull String message) { }
                                    });
                        } else {
                            String peerUid = item.optString("peer_uid", "");
                            if (!peerUid.isEmpty()) {
                                Map<String, String> fields = new LinkedHashMap<>();
                                fields.put("peer_uid", peerUid);
                                account.postV2Json("conversation_read2", fields,
                                        new tAccUtils.JsonCallback() {
                                            @Override public void onSuccess(@NonNull JSONObject json) {
                                                main.post(() -> {
                                                    if (isAdded() && getView() != null) load();
                                                });
                                            }
                                            @Override public void onError(int code, @NonNull String message) { }
                                        });
                            }
                        }
                    } else {
                        setUnreadOverride(overrideKey, true);
                        main.post(() -> {
                            if (isAdded() && getView() != null) load();
                        });
                    }
                    try { item.put("unread_count", currentlyUnread ? 0 : 1); }
                    catch (Exception ignored) { }
                    if (renderedItems != null) render(renderedItems);
                }).show();
    }

    private void setupHistoryFilters(View root) {
        root.findViewById(R.id.history_segments_card).setVisibility(View.VISIBLE);
        MaterialButtonToggleGroup group = root.findViewById(R.id.history_segments);
        int[] ids = {R.id.history_segment_dynamic, R.id.history_segment_app, R.id.history_segment_resource};
        String[] modes = {"dynamic", "app", "resource"};
        group.addOnButtonCheckedListener((buttons, checkedId, checked) -> {
            if (!checked) return;
            for (int i = 0; i < ids.length; i++) if (ids[i] == checkedId && !modes[i].equals(historyType)) {
                historyType = modes[i]; load(); break;
            }
        });
        final float[] downX = {0f};
        View scroll = root.findViewById(R.id.cloud_scroll);
        scroll.setOnTouchListener((view, event) -> {
            if (event.getActionMasked() == android.view.MotionEvent.ACTION_DOWN) downX[0] = event.getX();
            if (event.getActionMasked() == android.view.MotionEvent.ACTION_UP) {
                float delta = event.getX() - downX[0];
                int current = "dynamic".equals(historyType) ? 0 : "app".equals(historyType) ? 1 : 2;
                if (Math.abs(delta) > dp(72)) {
                    int next = Math.max(0, Math.min(2, current + (delta < 0 ? 1 : -1)));
                    if (next != current) group.check(ids[next]);
                }
            }
            return false;
        });
    }

    private void showHistoryMenu() {
        boolean enabled = requireContext().getSharedPreferences("history_preferences", 0).getBoolean("enabled", true);
        new WGProAlertDialogBuilder(requireContext()).setTitle("浏览历史")
                .setItems(new CharSequence[]{"清空所有历史", enabled ? "不再记录历史" : "开启记录历史"}, (dialog, which) -> {
                    if (which == 0) confirmClearHistory();
                    else confirmToggleHistory(enabled);
                }).show();
    }

    private void confirmToggleHistory(boolean enabled) {
        String title = enabled ? "不再记录历史？" : "开启记录历史？";
        String message = enabled ? "关闭后，打开动态、应用和资源详情时不会再上传浏览记录。" : "开启后，打开详情页会继续记录浏览历史。";
        new WGProAlertDialogBuilder(requireContext()).setTitle(title).setMessage(message)
                .setNegativeButton("取消", null).setPositiveButton("确认", (d, w) ->
                        requireContext().getSharedPreferences("history_preferences", 0).edit().putBoolean("enabled", !enabled).apply()).show();
    }

    private void confirmClearHistory() {
        new WGProAlertDialogBuilder(requireContext()).setTitle("清空所有历史？").setMessage("这会删除动态、应用和资源的全部浏览记录。")
                .setNegativeButton("取消", null).setPositiveButton("清空", (d, w) -> {
                    account.postV2Json("history_clear2", new LinkedHashMap<>(), new tAccUtils.JsonCallback() {
                        public void onSuccess(JSONObject j) { requireActivity().runOnUiThread(this::reloadAfterHistoryAction); }
                        public void onError(int c, String m) { }
                        private void reloadAfterHistoryAction() { load(); }
                    });
                }).show();
    }

    /**
     * 关注 / 粉丝分页加载。服务端 {@code contacts2} 每页最多 50 条（默认 20），
     * 这里按 20/页 请求，滚动到底再取下一页并追加。
     */
    private void loadContactsPage(final int page, final boolean append, long started, int generation) {
        if (append) {
            pagedLoading = true;
        } else {
            pagedPage = 1;
            pagedHasMore = true;
            pagedLoading = false;
        }
        Map<String, String> query = new LinkedHashMap<>();
        query.put("page", String.valueOf(page));
        query.put("size", "20");
        query.put("kind", MODE_FOLLOWING.equals(mode) ? "following" : "followers");
        account.getV2Json("contacts2", query, true, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                JSONArray items = json.optJSONArray("items");
                JSONArray pageItems = items == null ? new JSONArray() : items;
                int size = Math.max(1, json.optInt("size", 20));
                int total = json.optInt("total", pageItems.length());
                pagedPage = Math.max(1, json.optInt("page", page));
                pagedHasMore = pageItems.length() > 0 && pagedPage * size < total;
                pagedLoading = false;
                if (append && renderedItems != null) {
                    JSONArray merged = new JSONArray();
                    for (int i = 0; i < renderedItems.length(); i++) merged.put(renderedItems.opt(i));
                    for (int i = 0; i < pageItems.length(); i++) merged.put(pageItems.opt(i));
                    completeAfter(started, generation, () -> render(merged));
                } else {
                    completeAfter(started, generation, () -> render(pageItems));
                }
            }
            @Override public void onError(int code, @NonNull String message) {
                pagedLoading = false;
                if (append) {
                    // 加载下一页失败：保留已加载内容，仅恢复底部提示（可再次上拉重试）。
                    render(renderedItems == null ? new JSONArray() : renderedItems);
                    return;
                }
                completeAfter(started, generation, () -> {
                    state.setVisibility(View.GONE);
                    android.widget.Toast.makeText(requireContext(), "加载失败，请稍后重试",
                            android.widget.Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void load() {
        final int generation = ++requestGeneration;
        final long started = android.os.SystemClock.uptimeMillis();
        if (!loadedOnce && !(requireActivity() instanceof UserDetailActivity)
                && requireActivity() instanceof BaseSectionActivity) {
            ((BaseSectionActivity) requireActivity()).showContentLoading();
        }
        Map<String, String> query = new LinkedHashMap<>();
        query.put("page", "1");
        query.put("size", "20");
        String action;
        boolean auth = true;
        if (MODE_USER_APPS.equals(mode)) {
            loadUserApps(started, generation);
            return;
        }
        if (pagedMode) {
            if (getArguments() != null && getArguments().getInt("expected_count", -1) == 0) {
                completeAfter(started, generation, () -> render(new JSONArray()));
                return;
            }
            loadContactsPage(1, false, started, generation);
            return;
        }
        if (MODE_NOTIFICATIONS.equals(mode)) {
            loadInbox(started, generation, query);
            return;
        }
        switch (mode) {
            case MODE_HISTORY: action = "history2"; query.put("target_type", historyType); break;
            case MODE_COLLECTION_DYNAMIC: action = "collections2"; query.put("target_type", "dynamic"); break;
            case MODE_COLLECTION_APP: action = "collections2"; query.put("target_type", "app"); break;
            case MODE_COLLECTION_RESOURCE: action = "collections2"; query.put("target_type", "resource"); break;
            case MODE_MY_RESOURCES: action = "my_resources2"; break;
            case MODE_NOTIFICATIONS: action = "notifications2"; break;
            case MODE_CONVERSATIONS: action = "conversations2"; break;
            case MODE_FOLLOWING: action = "contacts2"; query.put("kind", "following"); break;
            case MODE_FOLLOWERS: action = "contacts2"; query.put("kind", "followers"); break;
            case MODE_USER_RESOURCES: action = "resources2"; auth = false; break;
            default:
                action = "dynamics2";
                String targetUid = getArguments() == null ? account.getUid()
                        : getArguments().getString("target_uid", account.getUid());
                query.put("target_uid", targetUid);
                // 动态是公开内容：未登录也能浏览；已登录时会自动带上查看者身份
                // （is_liked / is_collected 等个性化字段依然可用）。
                auth = false;
                break;
        }
        account.getV2Json(action, query, auth, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                JSONArray items = json.optJSONArray("items");
                JSONArray result = items == null ? new JSONArray() : items;
                if (MODE_USER_RESOURCES.equals(mode)) result = filterByUid(result,
                        getArguments() == null ? "" : getArguments().getString("target_uid", ""));
                JSONArray finalResult = result;
                if (isDynamicTargetList()) hydrateDynamicTargets(finalResult, started, generation);
                else if (isResourceTargetList()) hydrateResourceTargets(finalResult, started, generation);
                else if (isAppTargetList()) hydrateAppTargets(finalResult, started, generation);
                else completeAfter(started, generation, () -> render(finalResult));
            }
            @Override public void onError(int code, @NonNull String message) {
                completeAfter(started, generation, () -> {
                    state.setVisibility(View.GONE);
                    android.widget.Toast.makeText(requireContext(), "加载失败，请稍后重试",
                            android.widget.Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    void reloadFromHistoryAction() { load(); }

    private void loadInbox(long started, int generation, Map<String, String> page) {
        JSONArray[] results = {null, null};
        int[] unreadCounts = {0, 0};
        String[] failure = {null};
        java.util.concurrent.atomic.AtomicInteger pending = new java.util.concurrent.atomic.AtomicInteger(2);
        tAccUtils.JsonCallback notifications = inboxCallback(0, results, unreadCounts, failure, pending, started, generation);
        tAccUtils.JsonCallback conversations = inboxCallback(1, results, unreadCounts, failure, pending, started, generation);
        account.getV2Json("notifications2", new LinkedHashMap<>(page), true, notifications);
        account.getV2Json("conversations2", new LinkedHashMap<>(page), true, conversations);
    }

    private tAccUtils.JsonCallback inboxCallback(int index, JSONArray[] results, int[] unreadCounts,
                                                  String[] failure,
                                                  java.util.concurrent.atomic.AtomicInteger pending,
                                                  long started, int generation) {
        return new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                results[index] = json.optJSONArray("items");
                unreadCounts[index] = index == 0
                        ? json.optInt("unread_count", 0)
                        : json.optInt("unread_total", 0);
                finishInboxIfReady(results, unreadCounts, failure, pending, started, generation);
            }
            @Override public void onError(int code, @NonNull String message) {
                failure[0] = message; results[index] = new JSONArray();
                finishInboxIfReady(results, unreadCounts, failure, pending, started, generation);
            }
        };
    }

    private void finishInboxIfReady(JSONArray[] results, int[] unreadCounts, String[] failure,
                                    java.util.concurrent.atomic.AtomicInteger pending,
                                    long started, int generation) {
        if (pending.decrementAndGet() != 0) return;
        JSONArray combined = new JSONArray();
        appendSystemMessage(combined, results[0], unreadCounts[0]);
        appendInboxItems(combined, results[1]);
        if (combined.length() == 0 && failure[0] != null) {
            completeAfter(started, generation, () -> showState(failure[0]));
        } else {
            completeAfter(started, generation, () -> render(combined));
        }
    }

    private void appendSystemMessage(JSONArray target, @Nullable JSONArray source, int unreadTotal) {
        if (messageDb.isSystemRemoved(account.getUid())) return;
        if (source != null) {
            java.util.List<MessageDatabase.SystemMessage> cache = new java.util.ArrayList<>();
            for (int i = 0; i < source.length(); i++) {
                JSONObject message = source.optJSONObject(i);
                if (message == null) continue;
                MessageDatabase.SystemMessage item = new MessageDatabase.SystemMessage();
                item.id = message.optString("id", "");
                item.title = message.optString("title", "");
                item.content = message.optString("content", "");
                JSONObject metadata = message.optJSONObject("metadata");
                item.metadata = metadata == null ? "{}" : metadata.toString();
                item.createdAt = message.optString("created_at", "");
                item.isRead = message.optBoolean("is_read", false);
                cache.add(item);
            }
            messageDb.replaceSystemMessages(account.getUid(), cache);
        }
        java.util.List<MessageDatabase.SystemMessage> local =
                messageDb.getSystemMessages(account.getUid());
        if (local.isEmpty() && !messageDb.hasSystemMessageHistory(account.getUid())) return;
        JSONObject item = new JSONObject();
        try {
            long clearedAt = systemClearedId();
            String clearedTime = clearedTimeString(clearedAt);
            boolean forcedUnread = hasUnreadOverride("system");
            java.util.List<MessageDatabase.SystemMessage> visible = new java.util.ArrayList<>();
            for (MessageDatabase.SystemMessage message : local) {
                if (clearedAt > 0 && message.createdAt != null
                        && message.createdAt.compareTo(clearedTime) <= 0) continue;
                visible.add(message);
            }
            if (visible.isEmpty()) {
                item.put("_kind", "system_messages");
                item.put("title", "系统消息");
                item.put("content", "暂无消息");
                item.put("message_count", 0);
                item.put("unread_count", forcedUnread ? 1 : 0);
                item.put("_system_items", new JSONArray());
                target.put(item);
                return;
            }
            item.put("_kind", "system_messages");
            item.put("title", "系统消息");
            MessageDatabase.SystemMessage first = visible.get(0);
            int unreadCount = 0;
            JSONArray visibleJson = new JSONArray();
            for (MessageDatabase.SystemMessage message : visible) {
                if (!message.isRead) unreadCount++;
                JSONObject row = new JSONObject();
                row.put("id", message.id);
                row.put("title", message.title);
                row.put("content", message.content);
                try { row.put("metadata", new JSONObject(message.metadata)); }
                catch (Exception ignored) { row.put("metadata", new JSONObject()); }
                row.put("created_at", message.createdAt);
                row.put("is_read", message.isRead);
                visibleJson.put(row);
            }
            if (forcedUnread) unreadCount = 1;
            item.put("content", first.content == null || first.content.isEmpty()
                    ? "暂无消息" : first.content);
            item.put("message_count", visible.size());
            item.put("unread_count", unreadCount);
            item.put("_system_items", visibleJson);
        } catch (Exception ignored) { }
        target.put(item);
    }

    private void appendInboxItems(JSONArray target, @Nullable JSONArray source) {
        if (source == null) return;
        for (int i = 0; i < source.length(); i++) {
            JSONObject item = source.optJSONObject(i); if (item == null) continue;
            try {
                item.put("_kind", "conversation");
                String peerUid = item.optString("peer_uid", "");
                if (!peerUid.isEmpty()
                        && messageDb.isConversationRemoved(account.getUid(), peerUid)) {
                    continue;
                }
                if (item.has("last_message")) {
                    long clearedAt = peerUid.isEmpty() ? 0L
                            : messageDb.getConversationClearedAt(account.getUid(), peerUid);
                    String messageId = item.optString("last_message_id", "");
                    String createdAt = item.optString("created_at", "");
                    boolean clearedByTime = clearedAt > 0 && !createdAt.isEmpty()
                            && createdAt.compareTo(clearedTimeString(clearedAt)) <= 0;
                    if (clearedByTime) {
                        messageDb.upsertConversationPreview(account.getUid(), peerUid,
                                "", "", "");
                    } else {
                        messageDb.upsertConversationPreview(account.getUid(), peerUid,
                                messageId, item.optString("last_message", ""), createdAt);
                    }
                }
                MessageDatabase.ConversationPreview preview =
                        messageDb.getConversationPreview(account.getUid(), peerUid);
                item.put("last_message", preview == null ? "" : preview.message);
                if (!peerUid.isEmpty() && hasUnreadOverride("conversation_" + peerUid)) {
                    item.put("unread_count", 1);
                }
            } catch (Exception ignored) { }
            target.put(item);
        }
    }

    private JSONArray filterByUid(JSONArray source, String uid) {
        JSONArray result = new JSONArray();
        for (int i = 0; i < source.length(); i++) {
            JSONObject item = source.optJSONObject(i);
            if (item != null && uid.equals(item.optString("uid"))) result.put(item);
        }
        return result;
    }

    /**
     * 用户主页「应用」标签：读取公开应用目录并按作者 uid 过滤，
     * 走公开接口，因此未登录状态下同样可以加载。
     */
    private void loadUserApps(long started, int generation) {
        String targetUid = getArguments() == null ? ""
                : getArguments().getString("target_uid", "");
        account.getPublicJsonUrl("https://res.typheye.cn/api.php?type=app&page=1&size=50",
                new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        JSONArray list = json.optJSONArray("info");
                        if (list == null) list = json.optJSONArray("items");
                        if (list == null) list = json.optJSONArray("apps");
                        if (list == null) list = json.optJSONArray("data");
                        JSONArray filtered = filterByAuthorUid(
                                list == null ? new JSONArray() : list, targetUid);
                        completeAfter(started, generation, () -> render(filtered));
                    }

                    @Override public void onError(int code, @NonNull String message) {
                        completeAfter(started, generation, () -> {
                            state.setVisibility(View.GONE);
                            android.widget.Toast.makeText(requireContext(), "加载失败，请稍后重试",
                                    android.widget.Toast.LENGTH_SHORT).show();
                        });
                    }
                });
    }

    private JSONArray filterByAuthorUid(JSONArray source, String uid) {
        JSONArray result = new JSONArray();
        if (uid == null || uid.isEmpty()) return result;
        for (int i = 0; i < source.length(); i++) {
            JSONObject item = source.optJSONObject(i);
            if (item == null) continue;
            if (uid.equals(item.optString("uid"))
                    || uid.equals(item.optString("author_uid"))) result.put(item);
        }
        return result;
    }

    private void completeAfter(long started, int generation, Runnable result) {
        long delay = Math.max(0L, MIN_LOADING_MS - (android.os.SystemClock.uptimeMillis() - started));
        main.postDelayed(() -> {
            if (!isAdded() || getView() == null || generation != requestGeneration) return;
            refresh.setRefreshing(false);
            if (!loadedOnce && requireActivity() instanceof BaseSectionActivity) {
                ((BaseSectionActivity) requireActivity()).hideContentLoading();
            }
            loadedOnce = true;
            result.run();
        }, delay);
    }

    private void render(JSONArray items) {
        String signature = listSignature(items);
        if (signature.equals(lastRenderSignature)) {
            // 数据没变：保留现有视图，避免重建时图片先空白再补上（闪动）
            return;
        }
        lastRenderSignature = signature;
        renderedItems = items;
        list.removeAllViews();
        if (isResourceTargetList() || MODE_MY_RESOURCES.equals(mode) || MODE_USER_RESOURCES.equals(mode)) {
            View masonry = ResourceMasonryFactory.create(requireContext(), items, item -> {
                if (item.optBoolean("_invalid", false)) {
                    new WGProAlertDialogBuilder(requireContext()).setTitle("资源已失效").setMessage("资源已不可见").setNegativeButton("关闭", null).show();
                } else startActivity(new Intent(requireContext(), ResourceDetailActivity.class)
                        .putExtra(ResourceDetailActivity.EXTRA_RESOURCE_JSON, item.toString()));
            }, (item, anchor) -> {
                if (MODE_HISTORY.equals(mode)) showHistoryItemActions(item, anchor);
                else showCatalogActions(item, false, anchor);
            });
            if (items.length() > 0) list.addView(masonry, new LinearLayout.LayoutParams(-1, -2));
            if (items.length() == 0) {
                stateText.setText(emptyText()); stateDescription.setText(emptyDescription());
                state.setVisibility(View.VISIBLE);
            } else state.setVisibility(View.GONE);
            return;
        }
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.optJSONObject(i);
            if (item != null) list.addView(item.has("_section")
                    ? createSectionHeader(item.optString("_section")) : createCard(item));
        }
        if (pagedMode && items.length() > 0 && (pagedLoading || !pagedHasMore)) {
            TextView footer = new TextView(requireContext());
            footer.setGravity(android.view.Gravity.CENTER);
            footer.setPadding(0, dp(12), 0, dp(16));
            footer.setTextSize(13f);
            footer.setTextColor(requireContext().getColor(R.color.text_secondary));
            footer.setText(pagedLoading ? "正在加载…" : "没有更多了");
            list.addView(footer, new LinearLayout.LayoutParams(-1, -2));
        }
        if (list.getChildCount() == 0) showState(emptyText());
        else state.setVisibility(View.GONE);
    }

    /** 列表数据签名：用于判断刷新后是否真的需要重建视图。 */
    private String listSignature(JSONArray items) {
        if (items == null) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.optJSONObject(i);
            if (item == null) continue;
            sb.append(item.optString("id", "")).append('\u0001')
                    .append(item.optString("target_key", "")).append('\u0001')
                    .append(item.optString("target_type", "")).append('\u0001')
                    .append(item.optString("content", "")).append('\u0001')
                    .append(item.optString("title", "")).append('\u0001')
                    .append(item.optString("name", "")).append('\u0001')
                    .append(item.optString("status", "")).append('\u0001')
                    .append(item.optString("like_count", "")).append('\u0001')
                    .append(item.optString("comment_count", "")).append('\u0001')
                    .append(item.optString("collection_count", "")).append('\u0001')
                    .append(item.optString("is_liked", "")).append('\u0001')
                    .append(item.optString("is_favorited", "")).append('\u0001')
                    .append(item.optString("unread_count", "")).append('\n');
        }
        return sb.toString();
    }

    private boolean isDynamicTargetList() {
        return MODE_COLLECTION_DYNAMIC.equals(mode)
                || (MODE_HISTORY.equals(mode) && "dynamic".equals(historyType));
    }

    private boolean isResourceTargetList() {
        return MODE_COLLECTION_RESOURCE.equals(mode)
                || (MODE_HISTORY.equals(mode) && "resource".equals(historyType));
    }

    private boolean isAppTargetList() {
        return MODE_COLLECTION_APP.equals(mode)
                || (MODE_HISTORY.equals(mode) && "app".equals(historyType));
    }

    private void hydrateDynamicTargets(JSONArray targets, long started, int generation) {
        if (targets.length() == 0) { completeAfter(started, generation, () -> render(targets)); return; }
        java.util.concurrent.atomic.AtomicInteger pending = new java.util.concurrent.atomic.AtomicInteger(targets.length());
        for (int i = 0; i < targets.length(); i++) {
            final int index = i;
            JSONObject target = targets.optJSONObject(i);
            if (target == null) { finishHydration(targets, pending, started, generation); continue; }
            normalizeUnavailableIdentity(target, target);
            String id = target.optString("target_key", target.optString("id", ""));
            if (id.isEmpty()) { markInvalid(target); finishHydration(targets, pending, started, generation); continue; }
            Map<String, String> query = new LinkedHashMap<>(); query.put("dynamic_id", id);
            account.getV2Json("dynamic_detail2", query, false, new tAccUtils.JsonCallback() {
                @Override public void onSuccess(@NonNull JSONObject json) {
                    JSONObject info = json.optJSONObject("info");
                    boolean unavailable = info == null || info.optBoolean("deleted", false)
                            || info.optBoolean("is_deleted", false)
                            || info.optBoolean("removed", false)
                            || info.optBoolean("is_removed", false)
                            || "deleted".equalsIgnoreCase(info.optString("status"))
                            || "offline".equalsIgnoreCase(info.optString("status"))
                            || "deleted".equalsIgnoreCase(info.optString("visibility"))
                            || "removed".equalsIgnoreCase(info.optString("visibility"))
                            || "offline".equalsIgnoreCase(info.optString("visibility"));
                    if (info != null && !unavailable) try {
                        copyIdentity(target, info);
                        preserveHistoryId(target, info);
                        targets.put(index, info);
                    } catch (Exception ignored) { }
                    else {
                        if (info != null) normalizeUnavailableIdentity(info, target);
                        markUnavailableAndFinish(target, targets, pending, started, generation);
                        return;
                    }
                    finishHydration(targets, pending, started, generation);
                }
                @Override public void onError(int code, @NonNull String message) {
                    JSONObject cached = account.getCachedV2Json("dynamic_detail2", query, false);
                    if (cached != null) normalizeUnavailableIdentity(cached.optJSONObject("info"), target);
                    markUnavailableAndFinish(target, targets, pending, started, generation);
                }
            });
        }
    }

    private void markUnavailableAndFinish(JSONObject target, JSONArray targets,
                                          java.util.concurrent.atomic.AtomicInteger pending,
                                          long started, int generation) {
        markInvalid(target);
        String uid = target.optString("uid", target.optString("target_uid", ""));
        String nick = target.optString("nick", "");
        if (uid.isEmpty()) {
            try { target.put("nick", "用户已注销"); target.put("avatar_url", ""); } catch (Exception ignored) { }
            finishHydration(targets, pending, started, generation);
            return;
        }
        if (!nick.isEmpty() && !"用户".equals(nick)) {
            finishHydration(targets, pending, started, generation);
            return;
        }
        account.getV2Json("user_profile2", java.util.Collections.singletonMap("target_uid", uid),
                false, new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        JSONObject profile = json.optJSONObject("info");
                        normalizeUnavailableIdentity(profile, target);
                        if (profile == null || target.optString("nick", "").isEmpty()
                                || "用户".equals(target.optString("nick"))) {
                            try { target.put("nick", "用户已注销"); target.put("avatar_url", ""); }
                            catch (Exception ignored) { }
                        }
                        finishHydration(targets, pending, started, generation);
                    }
                    @Override public void onError(int code, @NonNull String message) {
                        try { target.put("nick", "用户已注销"); target.put("avatar_url", ""); } catch (Exception ignored) { }
                        finishHydration(targets, pending, started, generation);
                    }
                });
    }

    private void finishHydration(JSONArray targets, java.util.concurrent.atomic.AtomicInteger pending,
                                 long started, int generation) {
        if (pending.decrementAndGet() == 0) completeAfter(started, generation, () -> render(targets));
    }

    private void markInvalid(JSONObject item) {
        try { item.put("_invalid", true); item.put("content", "该内容已失效"); } catch (Exception ignored) { }
    }

    private void copyIdentity(JSONObject source, JSONObject target) {
        try {
            if (!target.has("uid")) target.put("uid", source.optString("uid", ""));
            if (!target.has("nick")) target.put("nick", source.optString("nick", "用户"));
            if (!target.has("avatar_url")) target.put("avatar_url", source.optString("avatar_url", ""));
            if (!target.has("created_at")) target.put("created_at", source.optString("created_at", ""));
        } catch (Exception ignored) { }
    }

    /**
     * 浏览历史把服务端记录 id（{@code BROWSE_HISTORY.id}）放在历史项自身的 {@code id} 上；
     * 合并动态/应用/资源详情后会替换整个对象，必须先把它搬到 {@code _history_id}，
     * 否则单条删除会拿到内容 id（应用包名/资源哈希/动态 id），删不掉服务端记录。
     */
    private void preserveHistoryId(JSONObject source, JSONObject target) {
        if (!MODE_HISTORY.equals(mode)) return;
        try {
            String historyId = source.optString("id", "").trim();
            if (!historyId.isEmpty()) target.put("_history_id", historyId);
            // 详情对象里没有 target_type/target_key，一并带上做兜底：
            // 即使历史记录 id 丢失，服务端也能按 类型 + 内容标识 精确删除。
            String targetType = source.optString("target_type", "").trim();
            if (!targetType.isEmpty() && target.optString("target_type", "").trim().isEmpty()) {
                target.put("target_type", targetType);
            }
            String targetKey = source.optString("target_key", "").trim();
            if (!targetKey.isEmpty() && target.optString("target_key", "").trim().isEmpty()) {
                target.put("target_key", targetKey);
            }
        } catch (Exception ignored) { }
    }

    private void normalizeUnavailableIdentity(@Nullable JSONObject source, JSONObject target) {
        if (source == null) return;
        JSONObject nested = source.optJSONObject("target");
        if (nested != null) normalizeUnavailableIdentity(nested, target);
        nested = source.optJSONObject("author");
        if (nested != null) normalizeUnavailableIdentity(nested, target);
        nested = source.optJSONObject("user");
        if (nested != null) normalizeUnavailableIdentity(nested, target);
        try {
            putFirst(target, "uid", source, "uid", "author_uid", "target_uid", "user_id");
            putFirst(target, "nick", source, "nick", "nickname", "author_nick", "user_name");
            putFirst(target, "avatar_url", source, "avatar_url", "avatar", "author_avatar_url");
            putFirst(target, "created_at", source, "created_at", "published_at", "publish_time", "created_time");
        } catch (Exception ignored) { }
    }

    private void putFirst(JSONObject target, String targetKey, JSONObject source, String... sourceKeys)
            throws org.json.JSONException {
        String existing = target.optString(targetKey, "");
        if (!existing.isEmpty() && !("nick".equals(targetKey) && "用户".equals(existing))) return;
        for (String key : sourceKeys) {
            String value = source.optString(key, "").trim();
            if (!value.isEmpty()) { target.put(targetKey, value); return; }
        }
    }

    private void hydrateResourceTargets(JSONArray targets, long started, int generation) {
        hydrateTargets(targets, started, generation, "resource_detail2", "resource_id", false);
    }

    private void hydrateTargets(JSONArray targets, long started, int generation, String action,
                                String idField, boolean auth) {
        if (targets.length() == 0) { completeAfter(started, generation, () -> render(targets)); return; }
        java.util.concurrent.atomic.AtomicInteger pending = new java.util.concurrent.atomic.AtomicInteger(targets.length());
        for (int i = 0; i < targets.length(); i++) {
            final int index = i; JSONObject target = targets.optJSONObject(i);
            if (target == null) { finishHydration(targets, pending, started, generation); continue; }
            JSONObject embedded = target.optJSONObject("target");
            if (embedded != null) { preserveHistoryId(target, embedded); try { targets.put(index, embedded); } catch (Exception ignored) { }
                finishHydration(targets, pending, started, generation); continue; }
            String id = target.optString("target_key", target.optString("id", ""));
            Map<String, String> query = new LinkedHashMap<>(); query.put(idField, id);
            account.getV2Json(action, query, auth, new tAccUtils.JsonCallback() {
                @Override public void onSuccess(@NonNull JSONObject json) {
                    JSONObject info = json.optJSONObject("info");
                    if (info != null) try { preserveHistoryId(target, info); targets.put(index, info); } catch (Exception ignored) { }
                    else markInvalid(target);
                    finishHydration(targets, pending, started, generation);
                }
                @Override public void onError(int code, @NonNull String message) {
                    markInvalid(target); finishHydration(targets, pending, started, generation);
                }
            });
        }
    }

    private void hydrateAppTargets(JSONArray targets, long started, int generation) {
        if (targets.length() == 0) { completeAfter(started, generation, () -> render(targets)); return; }
        java.util.concurrent.atomic.AtomicInteger pending = new java.util.concurrent.atomic.AtomicInteger(targets.length());
        for (int i = 0; i < targets.length(); i++) {
            final int index = i; JSONObject target = targets.optJSONObject(i);
            if (target == null) { finishHydration(targets, pending, started, generation); continue; }
            JSONObject embedded = target.optJSONObject("target");
            if (embedded != null) { preserveHistoryId(target, embedded); try { targets.put(index, embedded); } catch (Exception ignored) { }
                finishHydration(targets, pending, started, generation); continue; }
            String key = target.optString("target_key", "");
            String url = "https://res.typheye.cn/api.php?type=app_detail&package=" + Uri.encode(key);
            account.getPublicJsonUrl(url, new tAccUtils.JsonCallback() {
                @Override public void onSuccess(@NonNull JSONObject json) {
                    JSONObject info = json.optJSONObject("info");
                    if (info != null) try { preserveHistoryId(target, info); targets.put(index, info); } catch (Exception ignored) { }
                    else markInvalid(target);
                    finishHydration(targets, pending, started, generation);
                }
                @Override public void onError(int code, @NonNull String message) {
                    markInvalid(target); finishHydration(targets, pending, started, generation);
                }
            });
        }
    }

    private View createCard(JSONObject item) {
        if (MODE_ACTIVITY.equals(mode)) return createDynamicRow(item);
        // 用户主页的「应用」标签使用与应用目录一致的应用卡片。
        if (MODE_USER_APPS.equals(mode) && !item.optBoolean("_invalid", false)) {
            return createCatalogRow(item, true);
        }
        if (isDynamicTargetList() && item.optBoolean("_invalid", false)) {
            return DynamicCardFactory.createUnavailable(requireContext(), item, v -> removeUnavailable(item, v));
        }
        if (isAppTargetList() && item.optBoolean("_invalid", false)) return createInvalidApp(item);
        if (isResourceTargetList() && item.optBoolean("_invalid", false)) return createInvalidResource(item);
        if (isDynamicTargetList() && !item.optBoolean("_invalid", false)) return createDynamicRow(item);
        if ((isResourceTargetList() || isAppTargetList()) && !item.optBoolean("_invalid", false)) {
            View catalog = createCatalogRow(item, isAppTargetList());
            if (MODE_HISTORY.equals(mode)) catalog.setOnLongClickListener(v -> { showHistoryItemActions(item, catalog); return true; });
            return catalog;
        }
        if (MODE_FOLLOWING.equals(mode) || MODE_FOLLOWERS.equals(mode)
                || MODE_CONVERSATIONS.equals(mode) || isConversation(item)) {
            return createContactRow(item);
        }
        if ("system_messages".equals(item.optString("_kind"))) return createSystemMessageRow(item);
        MaterialCardView card = new MaterialCardView(requireContext());
        card.setCardBackgroundColor(requireContext().getColor(R.color.surface_secondary));
        card.setStrokeWidth(0); card.setCardElevation(0); card.setRadius(dp(8));
        card.setClickable(true); card.setFocusable(true);
        card.setRippleColor(ColorStateList.valueOf(requireContext().getColor(R.color.brand_soft)));
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(-1, -2);
        cardParams.bottomMargin = dp(12); card.setLayoutParams(cardParams);
        LinearLayout body = new LinearLayout(requireContext());
        body.setOrientation(LinearLayout.VERTICAL); body.setPadding(dp(16), dp(15), dp(16), dp(15));
        TextView title = label(itemTitle(item), 16, true, R.color.text_primary);
        body.addView(title);
        String detail = itemDetail(item);
        if (!detail.isEmpty()) { TextView sub = label(detail, 13, false, R.color.text_secondary);
            sub.setMaxLines(3); LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2);
            p.topMargin = dp(6); body.addView(sub, p); }
        String time = item.optString("created_at", item.optString("last_viewed_at", ""));
        if (!time.isEmpty()) { LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-1, -2);
            p.topMargin = dp(8); body.addView(label(time, 12, false, R.color.text_secondary), p); }
        card.addView(body);
        card.setOnClickListener(v -> {
            if (MODE_FOLLOWING.equals(mode) || MODE_FOLLOWERS.equals(mode)) {
                String uid = item.optString("uid", item.optString("target_uid", ""));
                if (!uid.isEmpty()) startActivity(new Intent(requireContext(), UserDetailActivity.class)
                        .putExtra(UserDetailActivity.EXTRA_TARGET_UID, uid));
                return;
            }
            if ("system_messages".equals(item.optString("_kind"))) {
                openSystemMessages(item);
                return;
            }
            if (MODE_NOTIFICATIONS.equals(mode) && !item.optBoolean("is_read", false)) {
                markNotificationRead(item.optString("id", ""));
            }
            new WGProAlertDialogBuilder(requireContext())
                    .setTitle(itemTitle(item)).setMessage(detail.isEmpty() ? "暂无更多信息" : detail)
                    .setNegativeButton("关闭", null).show();
        });
        if (MODE_HISTORY.equals(mode)) card.setOnLongClickListener(v -> { showHistoryItemActions(item, card); return true; });
        return card;
    }

    private void showHistoryItemActions(JSONObject item, View card) {
        new WGProAlertDialogBuilder(requireContext()).setTitle("历史记录")
                .setItems(new CharSequence[]{"删除记录", "查看标识"}, (d, which) -> {
                    if (which == 0) new WGProAlertDialogBuilder(requireContext()).setTitle("删除记录？").setMessage("确认删除这条浏览记录吗？")
                            .setNegativeButton("取消", null).setPositiveButton("删除", (x, y) -> deleteHistory(item, card)).show();
                    else new WGProAlertDialogBuilder(requireContext()).setTitle("记录标识")
                            .setMessage("类型：" + historyType + "\n数据编号：" + item.optString("id", item.optString("target_key", "--")) + "\n发布者 UID：" + item.optString("uid", item.optString("target_uid", "--")))
                            .setNegativeButton("关闭", null).show();
                }).show();
    }

    private void deleteHistory(JSONObject item, View card) {
        Map<String, String> fields = new LinkedHashMap<>();
        String historyId = item.optString("_history_id", "").trim();
        if (historyId.isEmpty()) {
            // 失效内容没有合并详情，历史记录 id 仍留在历史项自己的 id 字段（纯数字）。
            String rawId = item.optString("id", "").trim();
            if (rawId.matches("\\d+")) historyId = rawId;
        }
        if (!historyId.isEmpty()) fields.put("history_id", historyId);
        String targetType = item.optString("target_type", "").trim();
        if (targetType.isEmpty() && MODE_HISTORY.equals(mode)) targetType = historyType;
        String targetKey = item.optString("target_key", "").trim();
        if (!targetType.isEmpty() && !targetKey.isEmpty()) {
            // 兜底：即使历史记录 id 缺失，也能按 类型 + 内容标识 精确删除。
            fields.put("target_type", targetType);
            fields.put("target_key", targetKey);
        }
        if (fields.isEmpty()) {
            toast("该记录缺少标识，暂时无法删除");
            return;
        }
        account.postV2Json("history_delete2", fields, new tAccUtils.JsonCallback() {
            public void onSuccess(JSONObject json) { main.post(() -> removeLocallyAndReload(item)); }
            public void onError(int code, String message) {
                main.post(() -> toast(message == null || message.trim().isEmpty()
                        ? "删除失败，请稍后重试" : message));
            }
        });
    }

    /**
     * 操作成功后的统一收尾：先在本地把这条移出列表并重绘（界面立刻响应、不留空位），
     * 再静默走一次和下拉刷新完全相同的加载，保证列表与服务端一致。
     */
    private void removeLocallyAndReload(JSONObject item) {
        if (renderedItems != null) {
            JSONArray next = new JSONArray();
            for (int i = 0; i < renderedItems.length(); i++) {
                JSONObject candidate = renderedItems.optJSONObject(i);
                if (candidate == null || sameItem(candidate, item)) continue;
                next.put(candidate);
            }
            render(next);
        }
        load();
    }

    private boolean sameItem(JSONObject a, JSONObject b) {
        return sameValue(a, b, "_history_id") || sameValue(a, b, "target_key")
                || sameValue(a, b, "id");
    }

    private boolean sameValue(JSONObject a, JSONObject b, String key) {
        String value = a.optString(key, "");
        return !value.isEmpty() && value.equals(b.optString(key, ""));
    }

    private void toast(String message) {
        if (isAdded()) android.widget.Toast.makeText(requireContext(), message,
                android.widget.Toast.LENGTH_SHORT).show();
    }

    private void removeUnavailable(JSONObject item, View card) {
        String key = item.optString("target_key", item.optString("id", ""));
        if (key.isEmpty()) return;
        new WGProAlertDialogBuilder(requireContext()).setTitle("动态操作").setItems(new String[]{"移除", "元数据"}, (d, which) -> {
            if (which == 1) {
                new WGProAlertDialogBuilder(requireContext()).setTitle("动态元数据")
                        .setMessage("数据编号：" + key + "\n发布者 UID：" + item.optString("uid", item.optString("target_uid", "--")) + "\n发布时间：" + item.optString("created_at", "--"))
                        .setNegativeButton("关闭", null).show();
                return;
            }
            new WGProAlertDialogBuilder(requireContext()).setTitle("确认移除该动态？")
                .setMessage("移除后不会再显示在当前列表。")
                .setNegativeButton("取消", null).setPositiveButton("移除", (dialog, which2) -> {
                    if (MODE_HISTORY.equals(mode)) { deleteHistory(item, card); return; }
                    Map<String, String> fields = new LinkedHashMap<>();
                    fields.put("target_type", "dynamic"); fields.put("target_key", key); fields.put("action", "remove");
                    account.postV2Json("collection_action2", fields, new tAccUtils.JsonCallback() {
                        @Override public void onSuccess(@NonNull JSONObject json) { main.post(() -> removeLocallyAndReload(item)); }
                        @Override public void onError(int code, @NonNull String message) { main.post(() -> toast("移除失败，请稍后重试")); }
                    });
                }).show();
        }).show();
    }

    private View createCatalogRow(JSONObject item, boolean app) {
        if (app) return AppListItemFactory.create(requireContext(), item, v -> {
            if (MODE_HISTORY.equals(mode)) showHistoryItemActions(item, v);
            else showCatalogActions(item, true, v);
        });
        MaterialCardView card = new MaterialCardView(requireContext());
        card.setCardBackgroundColor(requireContext().getColor(R.color.surface_secondary));
        card.setCardElevation(0); card.setStrokeWidth(0); card.setRadius(dp(8));
        card.setClickable(true); card.setFocusable(true);
        card.setRippleColor(ColorStateList.valueOf(requireContext().getColor(R.color.brand_soft)));
        LinearLayout body = new LinearLayout(requireContext()); body.setGravity(android.view.Gravity.CENTER_VERTICAL);
        body.setPadding(dp(14), dp(14), dp(14), dp(14));
        String name = item.optString(app ? "name" : "title", app ? "未命名应用" : "未命名资源");
        FrameLayout iconBox = new FrameLayout(requireContext());
        TextView initial = label(contactInitial(new JSONObject(java.util.Collections.singletonMap("nick", name))),
                19, true, R.color.brand_on_soft);
        initial.setGravity(android.view.Gravity.CENTER); initial.setBackgroundResource(R.drawable.bg_app_initial_rounded);
        iconBox.addView(initial, new FrameLayout.LayoutParams(dp(52), dp(52)));
        if (app) {
            ImageView icon = new ImageView(requireContext()); icon.setScaleType(ImageView.ScaleType.CENTER_CROP);
            icon.setVisibility(View.GONE); icon.setBackgroundResource(R.drawable.bg_app_icon_clip); icon.setClipToOutline(true);
            iconBox.addView(icon, new FrameLayout.LayoutParams(dp(52), dp(52)));
            loadCatalogIcon(item.optString("icon_url", ""), icon, initial);
        }
        body.addView(iconBox, new LinearLayout.LayoutParams(dp(52), dp(52)));
        LinearLayout labels = new LinearLayout(requireContext()); labels.setOrientation(LinearLayout.VERTICAL);
        labels.addView(label(name, 16, true, R.color.text_primary));
        TextView detail = label(item.optString("summary", item.optString("description", "暂无介绍")),
                13, false, R.color.text_secondary); detail.setMaxLines(2);
        LinearLayout.LayoutParams detailParams = new LinearLayout.LayoutParams(-1, -2); detailParams.topMargin = dp(4);
        labels.addView(detail, detailParams);
        LinearLayout.LayoutParams labelParams = new LinearLayout.LayoutParams(0, -2, 1f); labelParams.setMarginStart(dp(14));
        body.addView(labels, labelParams); card.addView(body);
        ImageView menu = new ImageView(requireContext()); menu.setImageResource(R.drawable.ic_more_vertical_vector);
        menu.setColorFilter(requireContext().getColor(R.color.text_secondary)); menu.setPadding(dp(6), dp(6), dp(2), dp(6));
        body.addView(menu, new LinearLayout.LayoutParams(dp(34), dp(34)));
        menu.setOnClickListener(v -> showCatalogActions(item, false, v));
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(-1, -2); cardParams.bottomMargin = dp(12);
        card.setLayoutParams(cardParams);
        card.setOnClickListener(v -> {
            if (app) startActivity(new Intent(requireContext(), AppDetailActivity.class)
                    .putExtra(AppDetailActivity.EXTRA_APP_JSON, item.toString()));
            else startActivity(new Intent(requireContext(), ResourceDetailActivity.class)
                    .putExtra(ResourceDetailActivity.EXTRA_RESOURCE_JSON, item.toString()));
        });
        return card;
    }

    private View createInvalidApp(JSONObject item) {
        JSONObject copy = item;
        try { copy.put("name", "未知应用"); copy.put("summary", "应用已不可见"); copy.put("version_name", "--"); } catch (Exception ignored) { }
        View v = AppListItemFactory.create(requireContext(), copy, x -> showCatalogActions(copy, true, x));
        v.setOnClickListener(x -> new WGProAlertDialogBuilder(requireContext()).setTitle("应用已失效").setMessage("应用已不可见").setNegativeButton("关闭", null).show());
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(-1, -2);
        cardParams.bottomMargin = dp(12); v.setLayoutParams(cardParams);
        return v;
    }

    private View createInvalidResource(JSONObject item) {
        JSONArray one = new JSONArray(); try { item.put("_invalid", true); one.put(item); } catch (Exception ignored) { }
        View v = ResourceMasonryFactory.create(requireContext(), one, x -> new WGProAlertDialogBuilder(requireContext()).setTitle("资源已失效").setMessage("资源已不可见").setNegativeButton("关闭", null).show(), (x,a) -> showCatalogActions(x, false, a));
        return v;
    }

    private void showCatalogActions(JSONObject item, boolean app, View anchor) {
        if (item.optBoolean("_invalid", false)) {
            String kind = app ? "应用" : "资源";
            new WGProAlertDialogBuilder(requireContext()).setTitle(kind + "已失效")
                    .setItems(new String[]{"移除", "元数据"}, (d, which) -> {
                        if (which == 0) new WGProAlertDialogBuilder(requireContext()).setTitle("确认移除？").setMessage("移除后不会再显示在当前列表。")
                                .setNegativeButton("取消", null).setPositiveButton("移除", (x,y) -> deleteHistory(item, anchor)).show();
                        else new WGProAlertDialogBuilder(requireContext()).setTitle(kind + "元数据").setMessage("数据编号：" + item.optString("id", item.optString("target_key", "--")) + "\n发布者 UID：" + item.optString("uid", item.optString("target_uid", "--"))).setNegativeButton("关闭", null).show();
                    }).show();
            return;
        }
        java.util.ArrayList<String> actions = new java.util.ArrayList<>();
        String owner = item.optString("uid", item.optString("author_uid", item.optString("publisher_uid", "")));
        boolean mine = !owner.isEmpty() && owner.equals(account.getUid());
        if (mine) actions.add("删除"); else actions.add("举报");
        actions.add("分享");
        if (MODE_HISTORY.equals(mode)) actions.add("删除记录");
        new WGProAlertDialogBuilder(requireContext()).setTitle(app ? "应用操作" : "资源操作")
                .setItems(actions.toArray(new String[0]), (d, which) -> {
                    if (which == 0) {
                        if (mine) confirmCatalogDelete(item, app); else reportCatalog(item, app);
                    } else if (which == 1) {
                        if ("分享".equals(actions.get(1))) {
                            Intent share = new Intent(Intent.ACTION_SEND).setType("text/plain")
                                    .putExtra(Intent.EXTRA_TEXT, (app ? "应用" : "资源") + "：" + item.optString(app ? "name" : "title", "未知"));
                            startActivity(Intent.createChooser(share, "分享"));
                        } else deleteHistory(item, anchor);
                    } else {
                        new WGProAlertDialogBuilder(requireContext()).setTitle("删除记录？").setMessage("确认删除这条浏览记录吗？")
                                .setNegativeButton("取消", null).setPositiveButton("删除", (x,y) -> deleteHistory(item, anchor)).show();
                    }
                }).show();
    }

    private void confirmCatalogDelete(JSONObject item, boolean app) {
        new WGProAlertDialogBuilder(requireContext()).setTitle("确认删除？").setMessage("删除后无法恢复。")
                .setNegativeButton("取消", null).setPositiveButton("删除", (d,w) -> {
                    Map<String,String> fields = new LinkedHashMap<>();
                    fields.put(app ? "package" : "resource_id", item.optString(app ? "package" : "id", item.optString("target_key", "")));
                    account.postV2Json(app ? "app_delete2" : "resource_delete2", fields, new tAccUtils.JsonCallback() {
                        public void onSuccess(JSONObject json) { main.post(() -> {
                            toast("已删除");
                            removeLocallyAndReload(item);
                        }); }
                        public void onError(int code, String msg) { main.post(() -> toast("删除失败：" + msg)); }
                    });
                }).show();
    }

    private void reportCatalog(JSONObject item, boolean app) {
        String key = item.optString(app ? "package" : "id",
                item.optString("target_key", ""));
        String title = item.optString(app ? "name" : "title", app ? "应用" : "资源");
        CommunityWeb.openReport(requireContext(), app ? "app" : "resource", key, title);
    }

    private View createSystemMessageRow(JSONObject item) {
        JSONObject contact = new JSONObject();
        try { contact.put("nick", "系统消息"); contact.put("last_message", item.optString("content", "暂无消息"));
            contact.put("_kind", "system_messages");
            contact.put("unread_count", item.optInt("unread_count", 0));
            contact.put("_system_items", item.optJSONArray("_system_items"));
        } catch (Exception ignored) { }
        return createContactRow(contact);
    }

    private void showSystemMessages(JSONObject systemItem) {
        JSONArray messages = systemItem.optJSONArray("_system_items");
        StringBuilder text = new StringBuilder();
        boolean hasUnread = false;
        if (messages != null) for (int i = 0; i < messages.length(); i++) {
            JSONObject item = messages.optJSONObject(i); if (item == null) continue;
            if (!item.optBoolean("is_read", false)) hasUnread = true;
            String title = item.optString("title", "").trim();
            String content = item.optString("content", item.optString("title", ""));
            if (!content.isEmpty()) {
                if (text.length() > 0) text.append("\n\n");
                if (!title.isEmpty() && !title.equals(content)) text.append(title).append('\n');
                text.append(content);
            }
        }
        if (hasUnread) markAllNotificationsRead();
        new WGProAlertDialogBuilder(requireContext()).setTitle("系统消息")
                .setMessage(text.length() == 0 ? "暂无消息" : text.toString())
                .setNegativeButton("关闭", null).show();
    }

    private void openSystemMessages(JSONObject systemItem) {
        markSystemMessagesReadLocally();
        startActivity(new Intent(requireContext(), ChatActivity.class)
                .putExtra(ChatActivity.EXTRA_MODE, ChatActivity.MODE_SYSTEM_MESSAGES));
    }

    private View createSectionHeader(String title) {
        TextView header = label(title, 18, true, R.color.text_primary);
        header.setPadding(dp(4), dp(12), dp(4), dp(10));
        return header;
    }

    private boolean isConversation(JSONObject item) {
        return "conversation".equals(item.optString("_kind"));
    }

    private View createDynamicRow(JSONObject item) {
        String id = item.optString("id", "");
        android.view.View.OnClickListener more = MODE_HISTORY.equals(mode)
                ? v -> showHistoryItemActions(item, v) : null;
        View card = DynamicCardFactory.create(requireContext(), item, v -> {
            if (!id.isEmpty()) startActivity(new Intent(requireContext(), DynamicDetailActivity.class)
                    .putExtra(DynamicDetailActivity.EXTRA_DYNAMIC_ID, id));
        }, () -> removeLocallyAndReload(item), more);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.bottomMargin = dp(12); card.setLayoutParams(params);
        if (MODE_HISTORY.equals(mode)) {
            card.setOnLongClickListener(v -> { showHistoryItemActions(item, card); return true; });
        }
        return card;
    }

    private View createContactRow(JSONObject item) {
        MaterialCardView row = new MaterialCardView(requireContext());
        row.setCardBackgroundColor(android.graphics.Color.TRANSPARENT);
        row.setStrokeWidth(0); row.setCardElevation(0); row.setRadius(dp(12));
        row.setClickable(true); row.setFocusable(true);
        row.setRippleColor(ColorStateList.valueOf(requireContext().getColor(R.color.brand_soft)));
        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(-1, dp(76));
        rowParams.bottomMargin = dp(4); row.setLayoutParams(rowParams);

        LinearLayout body = new LinearLayout(requireContext());
        body.setGravity(android.view.Gravity.CENTER_VERTICAL);
        body.setPadding(dp(12), dp(8), dp(12), dp(8));
        FrameLayout avatarBox = new FrameLayout(requireContext());
        LinearLayout.LayoutParams avatarParams = new LinearLayout.LayoutParams(dp(52), dp(52));
        TextView initial = label(contactInitial(item), 19, true, R.color.brand_on_soft);
        initial.setGravity(android.view.Gravity.CENTER);
        initial.setBackgroundResource(R.drawable.bg_avatar_placeholder_circle);
        avatarBox.addView(initial, new FrameLayout.LayoutParams(-1, -1));
        ShapeableImageView avatar = new ShapeableImageView(requireContext());
        avatar.setScaleType(ImageView.ScaleType.CENTER_CROP); avatar.setVisibility(View.GONE);
        avatar.setShapeAppearanceModel(avatar.getShapeAppearanceModel().toBuilder()
                .setAllCornerSizes(new com.google.android.material.shape.RelativeCornerSize(0.5f)).build());
        avatarBox.addView(avatar, new FrameLayout.LayoutParams(-1, -1));
        body.addView(avatarBox, avatarParams);
        BadgeFactory.bind(requireContext(), item, avatarBox,
                requireContext().getColor(R.color.surface_page));
        UnreadBadgeFactory.bind(requireContext(), avatarBox, item.optInt("unread_count", 0));

        LinearLayout labels = new LinearLayout(requireContext()); labels.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams labelsParams = new LinearLayout.LayoutParams(0, -2, 1f);
        labelsParams.setMarginStart(dp(14));
        labels.addView(label(contactName(item), 16, true, R.color.text_primary));
        TextView bio = label(contactBio(item), 13, false, R.color.text_secondary);
        bio.setSingleLine(true); bio.setEllipsize(android.text.TextUtils.TruncateAt.END);
        LinearLayout.LayoutParams bioParams = new LinearLayout.LayoutParams(-1, -2); bioParams.topMargin = dp(3);
        labels.addView(bio, bioParams); body.addView(labels, labelsParams); row.addView(body);

        String resolvedUid = (MODE_CONVERSATIONS.equals(mode) || isConversation(item))
                ? item.optString("peer_uid", "")
                : item.optString("uid", item.optString("actor_uid", ""));
        String avatarUrl = contactAvatarUrl(item, resolvedUid);
        final String uid = resolvedUid;
        loadContactAvatar(uid, avatarUrl, avatar, initial);
        row.setOnClickListener(v -> {
            if ("system_messages".equals(item.optString("_kind"))) {
                openSystemMessages(item);
                return;
            }
            if (uid.isEmpty()) return;
            if (MODE_CONVERSATIONS.equals(mode) || isConversation(item)) {
                setUnreadOverride("conversation_" + uid, false);
                startActivity(new Intent(requireContext(), ChatActivity.class)
                        .putExtra(ChatActivity.EXTRA_PEER_UID, uid)
                        .putExtra(ChatActivity.EXTRA_PEER_NAME, contactName(item)));
            } else {
                startActivity(new Intent(requireContext(), UserDetailActivity.class)
                        .putExtra(UserDetailActivity.EXTRA_TARGET_UID, uid));
            }
        });
        if (MODE_NOTIFICATIONS.equals(mode)) {
            row.setOnLongClickListener(v -> {
                showNotificationItemActions(item);
                return true;
            });
        }
        return row;
    }

    private String contactName(JSONObject item) {
        String value = item.optString("remark", "").trim();
        if (value.isEmpty()) value = item.optString("nick", item.optString("actor_nick", "用户"));
        return value.isEmpty() ? "用户" : value;
    }

    private String contactBio(JSONObject item) {
        String bio = item.optString("last_message",
                item.optString("bio", item.optString("shuo", ""))).trim();
        if (bio.isEmpty() && (MODE_NOTIFICATIONS.equals(mode) || isConversation(item))) {
            return "暂无消息";
        }
        return bio.isEmpty() ? "这个人还没有简介呢~" : bio;
    }

    private String contactInitial(JSONObject item) {
        String name = contactName(item).trim();
        return name.isEmpty() ? "用" : name.substring(0, 1).toUpperCase();
    }

    private String contactAvatarUrl(JSONObject item, String uid) {
        String url = item.optString("avatar_url", item.optString("actor_avatar_url", "")).trim();
        if (url.contains("/api.php?type=get_avatar2") && !uid.isEmpty()) {
            return "https://service.typheye.cn/src/pericon/" + uid;
        }
        return url;
    }

    private void loadContactAvatar(String uid, String url, ShapeableImageView avatar, TextView initial) {
        if (uid.isEmpty()) return;
        File cached = new File(requireContext().getFilesDir(), "avatar_" + uid + ".jpg");
        if (cached.isFile()) {
            Bitmap bitmap = BitmapFactory.decodeFile(cached.getAbsolutePath());
            if (bitmap != null) {
                avatar.setImageBitmap(bitmap);
                avatar.setVisibility(View.VISIBLE);
                initial.setVisibility(View.GONE);
            }
        }
        Bitmap memory = ImageCache.getMemory(url);
        if (memory != null) {
            avatar.setImageBitmap(memory);
            avatar.setVisibility(View.VISIBLE);
            initial.setVisibility(View.GONE);
            return;
        }
        if (url.isEmpty()) return;
        ImageCache.load(requireContext(), url, avatar, () -> {
            avatar.setVisibility(View.VISIBLE);
            initial.setVisibility(View.GONE);
        });
    }

    private void loadCatalogIcon(String url, ImageView icon, View placeholder) {
        if (url == null || url.trim().isEmpty()) return;
        ImageCache.load(requireContext(), url, icon, () -> {
            icon.setVisibility(View.VISIBLE);
            placeholder.setVisibility(View.GONE);
        });
    }

    private long systemClearedId() {
        return messageDb.getSystemClearedAt(account.getUid());
    }

    private String clearedTimeString(long millis) {
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.US)
                .format(new java.util.Date(millis));
    }

    private boolean hasUnreadOverride(String key) {
        if ("system".equals(key)) return messageDb.isSystemUnreadOverride(account.getUid());
        String peerUid = key.startsWith("conversation_") ? key.substring(13) : "";
        return !peerUid.isEmpty()
                && messageDb.isConversationUnreadOverride(account.getUid(), peerUid);
    }

    private void setUnreadOverride(String key, boolean enabled) {
        if ("system".equals(key)) {
            messageDb.setSystemUnreadOverride(account.getUid(), enabled);
        } else if (key.startsWith("conversation_")) {
            messageDb.setConversationUnreadOverride(account.getUid(),
                    key.substring(13), enabled);
        }
    }

    private void clearAllUnreadOverrides() {
        messageDb.clearAll(account.getUid());
    }

    private void markNotificationRead(String id) {
        if (id.isEmpty()) return;
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("notification_id", id); fields.put("action", "read");
        account.postV2Json("notification_state2", fields, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) { }
            @Override public void onError(int code, @NonNull String message) { }
        });
    }

    private void markAllNotificationsRead() {
        Map<String, String> fields = new LinkedHashMap<>();
        fields.put("action", "read_all");
        account.postV2Json("notification_state2", fields, new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                main.post(() -> {
                    if (isAdded() && getView() != null) load();
                });
            }

            @Override public void onError(int code, @NonNull String message) { }
        });
    }

    private String itemTitle(JSONObject item) {
        if ("system_messages".equals(item.optString("_kind"))) return "系统消息";
        if (MODE_NOTIFICATIONS.equals(mode)) return item.optString("title", "通知");
        JSONObject target = item.optJSONObject("target");
        if (target != null) item = target;
        String value = item.optString("nick", item.optString("title", item.optString("name", "")));
        if (!value.isEmpty()) return value;
        String type = item.optString("target_type", "动态");
        if (MODE_HISTORY.equals(mode)) type = "dynamic".equals(historyType) ? "动态" : "app".equals(historyType) ? "应用" : "资源";
        else if (MODE_COLLECTION_DYNAMIC.equals(mode) || "dynamic".equals(type)) type = "动态";
        else if (MODE_COLLECTION_APP.equals(mode) || "app".equals(type)) type = "应用";
        else if (MODE_COLLECTION_RESOURCE.equals(mode) || "resource".equals(type)) type = "资源";
        return type;
    }

    private String itemDetail(JSONObject item) {
        if (MODE_NOTIFICATIONS.equals(mode)) return item.optString("content", "");
        return item.optString("content", item.optString("summary", item.optString("description", "")));
    }

    private void showState(String value) {
        if (list.getChildCount() > 0) {
            state.setVisibility(View.GONE);
            return;
        }
        stateText.setText(value); state.setVisibility(View.VISIBLE);
        stateDescription.setText(emptyDescription());
    }

    private String emptyText() {
        if (MODE_HISTORY.equals(mode)) return "还没有浏览记录";
        if (MODE_COLLECTION_DYNAMIC.equals(mode)) return "这里还没有收藏";
        if (MODE_COLLECTION_APP.equals(mode)) return "还没有星标应用";
        if (MODE_COLLECTION_RESOURCE.equals(mode)) return "还没有星标资源";
        if (MODE_MY_RESOURCES.equals(mode)) return "还没有发布资源";
        if (MODE_NOTIFICATIONS.equals(mode)) return "暂无消息";
        if (MODE_CONVERSATIONS.equals(mode)) return "还没有私信";
        if (MODE_FOLLOWING.equals(mode)) return "还没有关注任何人";
        if (MODE_FOLLOWERS.equals(mode)) return "还没有粉丝";
        if (MODE_USER_APPS.equals(mode)) return "还没有发布应用";
        if (MODE_USER_RESOURCES.equals(mode)) return "还没有发布资源";
        return "还没有发布动态";
    }

    private String emptyDescription() {
        if (MODE_HISTORY.equals(mode)) return "浏览过的内容会保存在这里。";
        if (MODE_COLLECTION_DYNAMIC.equals(mode)) return "收藏动态后，可在这里快速找到。";
        if (MODE_COLLECTION_APP.equals(mode)) return "星标应用后，可在这里快速找到。";
        if (MODE_COLLECTION_RESOURCE.equals(mode)) return "星标资源后，可在这里快速找到。";
        if (MODE_FOLLOWING.equals(mode)) return "关注感兴趣的用户后会显示在这里。";
        if (MODE_FOLLOWERS.equals(mode)) return "有用户关注你后会显示在这里。";
        if (MODE_NOTIFICATIONS.equals(mode)) return "新的系统消息和私信会显示在这里。";
        if (MODE_CONVERSATIONS.equals(mode)) return "与其他用户的私信会显示在这里。";
        if (MODE_USER_APPS.equals(mode)) return "发布的应用会显示在这里。";
        if (MODE_USER_RESOURCES.equals(mode) || MODE_MY_RESOURCES.equals(mode)) return "发布的资源会显示在这里。";
        return "发布动态后会显示在这里。";
    }

    private TextView label(String value, int size, boolean bold, int color) {
        TextView text = new TextView(requireContext()); text.setText(value); text.setTextSize(size);
        text.setTextColor(requireContext().getColor(color));
        text.setTypeface(Typeface.DEFAULT, bold ? Typeface.BOLD : Typeface.NORMAL); return text;
    }
    private int dp(int v) { return Math.round(v * getResources().getDisplayMetrics().density); }
}
