package com.typheye.wgpro.ui.function.community;

import android.content.Intent;
import android.content.res.ColorStateList;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;
import com.typheye.wgpro.R;
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
        refresh.setOnRefreshListener(this::load);
        load();
        return root;
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
            case MODE_HISTORY: action = "history2"; break;
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
                completeAfter(started, generation, () -> render(finalResult));
            }
            @Override public void onError(int code, @NonNull String message) {
                completeAfter(started, generation, () -> showState(message));
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
        appendInboxSection(combined, "系统消息", results[0], false);
        appendInboxSection(combined, "私信", results[1], true);
        if (combined.length() == 0 && failure[0] != null) {
            completeAfter(started, generation, () -> showState(failure[0]));
        } else {
            completeAfter(started, generation, () -> render(combined));
        }
    }

    private void appendInboxSection(JSONArray target, String title, @Nullable JSONArray source,
                                    boolean conversation) {
        if (source == null || source.length() == 0) return;
        JSONObject heading = new JSONObject();
        try { heading.put("_section", title); } catch (Exception ignored) { }
        target.put(heading);
        for (int i = 0; i < source.length(); i++) {
            JSONObject item = source.optJSONObject(i); if (item == null) continue;
            if (conversation) try { item.put("_kind", "conversation"); } catch (Exception ignored) { }
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
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.optJSONObject(i);
            if (item != null) list.addView(item.has("_section")
                    ? createSectionHeader(item.optString("_section")) : createCard(item));
        }
        if (list.getChildCount() == 0) showState(emptyText());
        else state.setVisibility(View.GONE);
    }

    private View createCard(JSONObject item) {
        if (MODE_ACTIVITY.equals(mode)) return createDynamicRow(item);
        if (MODE_FOLLOWING.equals(mode) || MODE_FOLLOWERS.equals(mode)
                || MODE_CONVERSATIONS.equals(mode) || isConversation(item)) {
            return createContactRow(item);
        }
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
            if (MODE_NOTIFICATIONS.equals(mode) && !item.optBoolean("is_read", false)) {
                markNotificationRead(item.optString("id", ""));
            }
            new WGProAlertDialogBuilder(requireContext())
                    .setTitle(itemTitle(item)).setMessage(detail.isEmpty() ? "暂无更多信息" : detail)
                    .setNegativeButton("关闭", null).show();
        });
        return card;
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
                .setAllCornerSizes(1000f).build());
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

        String uid = (MODE_CONVERSATIONS.equals(mode) || isConversation(item)) ? item.optString("peer_uid", "")
                : item.optString("uid", item.optString("target_uid", ""));
        loadContactAvatar(uid, item.optString("avatar_url", ""), avatar, initial);
        row.setOnClickListener(v -> {
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
        return item.optString("remark", item.optString("nick", "用户"));
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
            android.graphics.Bitmap bitmap = android.graphics.BitmapFactory.decodeFile(cached.getAbsolutePath());
            if (bitmap != null) { avatar.setImageBitmap(bitmap); avatar.setVisibility(View.VISIBLE); initial.setVisibility(View.GONE); return; }
        }
        if (url.isEmpty()) return;
        Request request;
        try { request = new Request.Builder().url(url).build(); } catch (Exception ignored) { return; }
        account.getClient().newCall(request).enqueue(new Callback() {
            @Override public void onFailure(@NonNull Call call, @NonNull IOException error) { }
            @Override public void onResponse(@NonNull Call call, @NonNull Response response) {
                try (Response result = response) {
                    if (!result.isSuccessful() || result.body() == null) return;
                    byte[] bytes = result.body().bytes();
                    try (FileOutputStream output = new FileOutputStream(cached)) { output.write(bytes); }
                    android.graphics.Bitmap bitmap = android.graphics.BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    main.post(() -> { if (isAdded() && bitmap != null) { avatar.setImageBitmap(bitmap);
                        avatar.setVisibility(View.VISIBLE); initial.setVisibility(View.GONE); } });
                } catch (IOException ignored) { }
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
        if (MODE_NOTIFICATIONS.equals(mode)) return item.optString("title", "通知");
        String value = item.optString("nick", item.optString("title", item.optString("name", "")));
        if (!value.isEmpty()) return value;
        String type = item.optString("target_type", "内容");
        return type + " " + item.optString("target_key", item.optString("id", ""));
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
