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
import com.typheye.wgpro.ui.function.WebActivity;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.function.account.UserDetailActivity;
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
    private int requestGeneration;

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

    public static CloudListFragment newContactInstance(String mode, int expectedCount) {
        CloudListFragment fragment = newInstance(mode);
        fragment.requireArguments().putInt("expected_count", expectedCount);
        return fragment;
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater,
                                                  @Nullable ViewGroup container,
                                                  @Nullable Bundle stateBundle) {
        View root = inflater.inflate(R.layout.fragment_cloud_list, container, false);
        mode = getArguments() == null ? MODE_ACTIVITY : getArguments().getString("mode", MODE_ACTIVITY);
        account = new tAccUtils(requireContext().getApplicationContext());
        refresh = root.findViewById(R.id.cloud_refresh);
        refresh.setColorSchemeColors(requireContext().getColor(R.color.brand_primary));
        list = root.findViewById(R.id.cloud_list);
        state = root.findViewById(R.id.cloud_state);
        stateText = state.findViewById(R.id.stream_empty_title);
        stateDescription = state.findViewById(R.id.stream_empty_description);
        if (MODE_HISTORY.equals(mode)) addHistoryFilters();
        refresh.setOnRefreshListener(this::load);
        load();
        return root;
    }

    private void addHistoryFilters() {
        MaterialButtonToggleGroup group = new MaterialButtonToggleGroup(requireContext());
        group.setSingleSelection(true); group.setSelectionRequired(true);
        String[] labels = {"动态", "应用", "资源"};
        String[] modes = {"dynamic", "app", "resource"};
        for (int i = 0; i < labels.length; i++) {
            MaterialButton button = new MaterialButton(requireContext());
            button.setText(labels[i]); button.setId(View.generateViewId());
            group.addView(button, new MaterialButtonToggleGroup.LayoutParams(0, dp(42), 1f));
            final int index = i; button.setOnClickListener(v -> { historyType = modes[index]; load(); });
            if (modes[i].equals(historyType)) button.setChecked(true);
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, dp(48));
        params.bottomMargin = dp(10); list.addView(group, 0, params);
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
            completeAfter(started, generation, () -> render(new JSONArray()));
            return;
        }
        if ((MODE_FOLLOWING.equals(mode) || MODE_FOLLOWERS.equals(mode))
                && getArguments() != null && getArguments().getInt("expected_count", -1) == 0) {
            completeAfter(started, generation, () -> render(new JSONArray()));
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
            case MODE_FOLLOWING: action = "contacts2"; query.put("relation", "following"); break;
            case MODE_FOLLOWERS: action = "contacts2"; query.put("relation", "followers"); break;
            case MODE_USER_RESOURCES: action = "resources2"; auth = false; break;
            default:
                action = "dynamics2";
                String targetUid = getArguments() == null ? account.getUid()
                        : getArguments().getString("target_uid", account.getUid());
                query.put("target_uid", targetUid);
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

    private void loadInbox(long started, int generation, Map<String, String> page) {
        JSONArray[] results = {null, null};
        String[] failure = {null};
        java.util.concurrent.atomic.AtomicInteger pending = new java.util.concurrent.atomic.AtomicInteger(2);
        tAccUtils.JsonCallback notifications = inboxCallback(0, results, failure, pending, started, generation);
        tAccUtils.JsonCallback conversations = inboxCallback(1, results, failure, pending, started, generation);
        account.getV2Json("notifications2", new LinkedHashMap<>(page), true, notifications);
        account.getV2Json("conversations2", new LinkedHashMap<>(page), true, conversations);
    }

    private tAccUtils.JsonCallback inboxCallback(int index, JSONArray[] results, String[] failure,
                                                  java.util.concurrent.atomic.AtomicInteger pending,
                                                  long started, int generation) {
        return new tAccUtils.JsonCallback() {
            @Override public void onSuccess(@NonNull JSONObject json) {
                results[index] = json.optJSONArray("items");
                finishInboxIfReady(results, failure, pending, started, generation);
            }
            @Override public void onError(int code, @NonNull String message) {
                failure[0] = message; results[index] = new JSONArray();
                finishInboxIfReady(results, failure, pending, started, generation);
            }
        };
    }

    private void finishInboxIfReady(JSONArray[] results, String[] failure,
                                    java.util.concurrent.atomic.AtomicInteger pending,
                                    long started, int generation) {
        if (pending.decrementAndGet() != 0) return;
        JSONArray combined = new JSONArray();
        appendSystemMessage(combined, results[0]);
        appendInboxItems(combined, results[1]);
        if (combined.length() == 0 && failure[0] != null) {
            completeAfter(started, generation, () -> showState(failure[0]));
        } else {
            completeAfter(started, generation, () -> render(combined));
        }
    }

    private void appendSystemMessage(JSONArray target, @Nullable JSONArray source) {
        JSONObject item = new JSONObject();
        try {
            item.put("_kind", "system_messages");
            item.put("title", "系统消息");
            JSONObject first = source == null || source.length() == 0 ? null : source.optJSONObject(0);
            item.put("content", first == null ? "暂无消息" : first.optString("content", "暂无消息"));
            item.put("message_count", source == null ? 0 : source.length());
            item.put("_system_items", source == null ? new JSONArray() : source);
        } catch (Exception ignored) { }
        target.put(item);
    }

    private void appendInboxItems(JSONArray target, @Nullable JSONArray source) {
        if (source == null) return;
        for (int i = 0; i < source.length(); i++) {
            JSONObject item = source.optJSONObject(i); if (item == null) continue;
            try { item.put("_kind", "conversation"); } catch (Exception ignored) { }
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
        list.removeAllViews();
        if (MODE_HISTORY.equals(mode)) addHistoryFilters();
        if (isResourceTargetList() || MODE_MY_RESOURCES.equals(mode) || MODE_USER_RESOURCES.equals(mode)) {
            View masonry = ResourceMasonryFactory.create(requireContext(), items, item -> {
                String url = item.optString("detail_url", item.optString("url", ""));
                if (!url.isEmpty()) {
                    startActivity(new Intent(requireContext(), WebActivity.class).putExtra("URL", url));
                } else {
                    new WGProAlertDialogBuilder(requireContext()).setTitle(item.optString("title", "资源详情"))
                            .setMessage(item.optString("description", item.optString("summary", "暂无介绍")))
                            .setNegativeButton("关闭", null).show();
                }
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
        if (list.getChildCount() == 0) showState(emptyText());
        else state.setVisibility(View.GONE);
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
                            || "deleted".equalsIgnoreCase(info.optString("status"))
                            || "offline".equalsIgnoreCase(info.optString("status"));
                    if (info != null && !unavailable) try {
                        copyIdentity(target, info);
                        if (MODE_HISTORY.equals(mode)) info.put("_history_id", target.optString("id", ""));
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
        if (uid.isEmpty() || (!nick.isEmpty() && !"用户".equals(nick))) {
            finishHydration(targets, pending, started, generation);
            return;
        }
        account.getV2Json("user_profile2", java.util.Collections.singletonMap("target_uid", uid),
                false, new tAccUtils.JsonCallback() {
                    @Override public void onSuccess(@NonNull JSONObject json) {
                        normalizeUnavailableIdentity(json.optJSONObject("info"), target);
                        finishHydration(targets, pending, started, generation);
                    }
                    @Override public void onError(int code, @NonNull String message) {
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
            if (embedded != null) { try { targets.put(index, embedded); } catch (Exception ignored) { }
                finishHydration(targets, pending, started, generation); continue; }
            String id = target.optString("target_key", target.optString("id", ""));
            Map<String, String> query = new LinkedHashMap<>(); query.put(idField, id);
            account.getV2Json(action, query, auth, new tAccUtils.JsonCallback() {
                @Override public void onSuccess(@NonNull JSONObject json) {
                    JSONObject info = json.optJSONObject("info");
                    if (info != null) try { targets.put(index, info); } catch (Exception ignored) { }
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
            if (embedded != null) { try { targets.put(index, embedded); } catch (Exception ignored) { }
                finishHydration(targets, pending, started, generation); continue; }
            String key = target.optString("target_key", "");
            String url = "https://res.typheye.cn/api.php?type=app_detail&package=" + Uri.encode(key);
            account.getPublicJsonUrl(url, new tAccUtils.JsonCallback() {
                @Override public void onSuccess(@NonNull JSONObject json) {
                    JSONObject info = json.optJSONObject("info");
                    if (info != null) try { targets.put(index, info); } catch (Exception ignored) { }
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
        if (isDynamicTargetList() && item.optBoolean("_invalid", false)) {
            return DynamicCardFactory.createUnavailable(requireContext(), item, v -> removeUnavailable(item, v));
        }
        if (isDynamicTargetList() && !item.optBoolean("_invalid", false)) return createDynamicRow(item);
        if ((isResourceTargetList() || isAppTargetList()) && !item.optBoolean("_invalid", false))
            return createCatalogRow(item, isAppTargetList());
        if (MODE_FOLLOWING.equals(mode) || MODE_FOLLOWERS.equals(mode)
                || MODE_CONVERSATIONS.equals(mode) || isConversation(item)) {
            return createContactRow(item);
        }
        if ("system_messages".equals(item.optString("_kind"))) return createSystemMessageRow(item);
        MaterialCardView card = new MaterialCardView(requireContext());
        card.setCardBackgroundColor(requireContext().getColor(R.color.surface_primary));
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
                showSystemMessages(item.optJSONArray("_system_items"));
                return;
            }
            if (MODE_NOTIFICATIONS.equals(mode) && !item.optBoolean("is_read", false)) {
                markNotificationRead(item.optString("id", ""));
            }
            new WGProAlertDialogBuilder(requireContext())
                    .setTitle(itemTitle(item)).setMessage(detail.isEmpty() ? "暂无更多信息" : detail)
                    .setNegativeButton("关闭", null).show();
        });
        return card;
    }

    private void removeUnavailable(JSONObject item, View card) {
        String key = item.optString("target_key", item.optString("id", ""));
        if (key.isEmpty()) return;
        new WGProAlertDialogBuilder(requireContext()).setTitle("确认移除该动态？")
                .setMessage("移除后不会再显示在当前列表。")
                .setNegativeButton("取消", null)
                .setPositiveButton("移除", (dialog, which) -> {
                    Map<String, String> fields = new LinkedHashMap<>();
                    if (MODE_HISTORY.equals(mode)) {
                        fields.put("history_id", item.optString("_history_id", item.optString("id", key)));
                        account.postV2Json("history_delete2", fields, new tAccUtils.JsonCallback() {
                            @Override public void onSuccess(@NonNull JSONObject json) { card.setVisibility(View.GONE); }
                            @Override public void onError(int code, @NonNull String message) { }
                        });
                    } else {
                        fields.put("target_type", "dynamic"); fields.put("target_key", key); fields.put("action", "remove");
                        account.postV2Json("collection_action2", fields, new tAccUtils.JsonCallback() {
                            @Override public void onSuccess(@NonNull JSONObject json) { card.setVisibility(View.GONE); }
                            @Override public void onError(int code, @NonNull String message) { }
                        });
                    }
                }).show();
    }

    private View createCatalogRow(JSONObject item, boolean app) {
        if (app) return AppListItemFactory.create(requireContext(), item);
        MaterialCardView card = new MaterialCardView(requireContext());
        card.setCardBackgroundColor(requireContext().getColor(R.color.surface_primary));
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
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(-1, -2); cardParams.bottomMargin = dp(12);
        card.setLayoutParams(cardParams);
        card.setOnClickListener(v -> {
            if (app) startActivity(new Intent(requireContext(), AppDetailActivity.class)
                    .putExtra(AppDetailActivity.EXTRA_APP_JSON, item.toString()));
            else new WGProAlertDialogBuilder(requireContext()).setTitle(name)
                    .setMessage(item.optString("description", item.optString("summary", "暂无介绍")))
                    .setNegativeButton("关闭", null).show();
        });
        return card;
    }

    private View createSystemMessageRow(JSONObject item) {
        JSONObject contact = new JSONObject();
        try { contact.put("nick", "系统"); contact.put("last_message", item.optString("content", "暂无消息"));
            contact.put("_kind", "system_messages");
            contact.put("_system_items", item.optJSONArray("_system_items"));
        } catch (Exception ignored) { }
        return createContactRow(contact);
    }

    private void showSystemMessages(@Nullable JSONArray messages) {
        StringBuilder text = new StringBuilder();
        if (messages != null) for (int i = 0; i < messages.length(); i++) {
            JSONObject item = messages.optJSONObject(i); if (item == null) continue;
            String content = item.optString("content", item.optString("title", ""));
            if (!content.isEmpty()) { if (text.length() > 0) text.append("\n\n"); text.append(content); }
        }
        new WGProAlertDialogBuilder(requireContext()).setTitle("系统消息")
                .setMessage(text.length() == 0 ? "暂无消息" : text.toString())
                .setNegativeButton("关闭", null).show();
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
        View card = DynamicCardFactory.create(requireContext(), item, v -> {
            if (!id.isEmpty()) startActivity(new Intent(requireContext(), DynamicDetailActivity.class)
                    .putExtra(DynamicDetailActivity.EXTRA_DYNAMIC_ID, id));
        });
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
        params.bottomMargin = dp(12); card.setLayoutParams(params);
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

        LinearLayout labels = new LinearLayout(requireContext()); labels.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams labelsParams = new LinearLayout.LayoutParams(0, -2, 1f);
        labelsParams.setMarginStart(dp(14));
        labels.addView(label(contactName(item), 16, true, R.color.text_primary));
        TextView bio = label(contactBio(item), 13, false, R.color.text_secondary);
        bio.setSingleLine(true); bio.setEllipsize(android.text.TextUtils.TruncateAt.END);
        LinearLayout.LayoutParams bioParams = new LinearLayout.LayoutParams(-1, -2); bioParams.topMargin = dp(3);
        labels.addView(bio, bioParams); body.addView(labels, labelsParams); row.addView(body);

        String resolvedUid = (MODE_CONVERSATIONS.equals(mode) || isConversation(item)) ? item.optString("peer_uid", "")
                : item.optString("uid", item.optString("target_uid", ""));
        String avatarUrl = item.optString("avatar_url", item.optString("peer_avatar_url",
                item.optString("actor_avatar_url", "")));
        if (resolvedUid.isEmpty()) resolvedUid = item.optString("actor_uid", "");
        final String uid = resolvedUid;
        loadContactAvatar(uid, avatarUrl, avatar, initial);
        row.setOnClickListener(v -> {
            if ("system_messages".equals(item.optString("_kind"))) {
                showSystemMessages(item.optJSONArray("_system_items"));
                return;
            }
            if (uid.isEmpty()) return;
            if (MODE_CONVERSATIONS.equals(mode) || isConversation(item)) {
                startActivity(new Intent(requireContext(), ChatActivity.class)
                        .putExtra(ChatActivity.EXTRA_PEER_UID, uid)
                        .putExtra(ChatActivity.EXTRA_PEER_NAME, contactName(item)));
            } else {
                startActivity(new Intent(requireContext(), UserDetailActivity.class)
                        .putExtra(UserDetailActivity.EXTRA_TARGET_UID, uid));
            }
        });
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
        return bio.isEmpty() ? "这个人还没有简介呢~" : bio;
    }

    private String contactInitial(JSONObject item) {
        String name = contactName(item).trim();
        return name.isEmpty() ? "用" : name.substring(0, 1).toUpperCase();
    }

    private void loadContactAvatar(String uid, String url, ShapeableImageView avatar, TextView initial) {
        if (uid.isEmpty()) return;
        File cached = new File(requireContext().getFilesDir(), "avatar_" + uid + ".jpg");
        if (cached.isFile()) {
            Bitmap bitmap = BitmapFactory.decodeFile(cached.getAbsolutePath());
            if (bitmap != null) { avatar.setImageBitmap(bitmap); avatar.setVisibility(View.VISIBLE); initial.setVisibility(View.GONE); }
        }
        if (url.isEmpty()) return;
        if (url.startsWith("/")) url = "https://service.typheye.cn" + url;
        Request request;
        try { request = new Request.Builder().url(url).build(); } catch (Exception ignored) { return; }
        account.getClient().newCall(request).enqueue(new Callback() {
            @Override public void onFailure(@NonNull Call call, @NonNull IOException error) { }
            @Override public void onResponse(@NonNull Call call, @NonNull Response response) {
                try (Response result = response) {
                    if (!result.isSuccessful() || result.body() == null) return;
                    byte[] bytes = result.body().bytes();
                    try (FileOutputStream output = new FileOutputStream(cached)) { output.write(bytes); }
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    main.post(() -> { if (isAdded() && bitmap != null) { avatar.setImageBitmap(bitmap);
                        avatar.setVisibility(View.VISIBLE); initial.setVisibility(View.GONE); } });
                } catch (Exception ignored) { }
            }
        });
    }

    private void loadCatalogIcon(String url, ImageView icon, View placeholder) {
        if (url == null || url.trim().isEmpty()) return;
        if (url.startsWith("/")) url = "https://res.typheye.cn" + url;
        final String source = url;
        Request request;
        try { request = new Request.Builder().url(source).build(); } catch (Exception ignored) { return; }
        account.getClient().newCall(request).enqueue(new Callback() {
            @Override public void onFailure(@NonNull Call call, @NonNull IOException error) { }
            @Override public void onResponse(@NonNull Call call, @NonNull Response response) {
                try (Response body = response) {
                    if (!body.isSuccessful() || body.body() == null) return;
                    Bitmap bitmap = BitmapFactory.decodeStream(body.body().byteStream());
                    if (bitmap == null) return;
                    main.post(() -> { if (isAdded()) { icon.setImageBitmap(bitmap); icon.setVisibility(View.VISIBLE); placeholder.setVisibility(View.GONE); } });
                } catch (Exception ignored) { }
            }
        });
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
        if (mode.startsWith("collection_")) return "这里还没有收藏";
        if (MODE_MY_RESOURCES.equals(mode)) return "还没有发布资源";
        if (MODE_NOTIFICATIONS.equals(mode)) return "暂时没有通知或私信";
        if (MODE_CONVERSATIONS.equals(mode)) return "还没有私信";
        if (MODE_FOLLOWING.equals(mode)) return "还没有关注任何人";
        if (MODE_FOLLOWERS.equals(mode)) return "还没有粉丝";
        if (MODE_USER_APPS.equals(mode)) return "还没有发布应用";
        if (MODE_USER_RESOURCES.equals(mode)) return "还没有发布资源";
        return "还没有发布动态";
    }

    private String emptyDescription() {
        if (MODE_HISTORY.equals(mode)) return "浏览过的内容会保存在这里。";
        if (mode.startsWith("collection_")) return "收藏内容后，可在这里快速找到。";
        if (MODE_FOLLOWING.equals(mode)) return "关注感兴趣的用户后会显示在这里。";
        if (MODE_FOLLOWERS.equals(mode)) return "有用户关注你后会显示在这里。";
        if (MODE_NOTIFICATIONS.equals(mode)) return "新的互动和系统消息会显示在这里。";
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
