package com.typheye.wgpro.ui.function.community;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.card.MaterialCardView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;
import com.typheye.wgpro.R;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.utils.tAccUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChatActivity extends BaseSectionActivity {
    public static final String EXTRA_PEER_UID = "peer_uid";
    public static final String EXTRA_PEER_NAME = "peer_name";
    @Override protected String screenTitle() {
        String name = getIntent().getStringExtra(EXTRA_PEER_NAME);
        return name == null || name.trim().isEmpty() ? "私信" : name;
    }
    @Override protected Fragment createContent() {
        return ChatFragment.newInstance(getIntent().getStringExtra(EXTRA_PEER_UID));
    }

    public static final class ChatFragment extends Fragment {
        private final Handler main = new Handler(Looper.getMainLooper());
        private String peerUid;
        private tAccUtils account;
        private LinearLayout messages;
        private SwipeRefreshLayout refresh;
        private AppCompatEditText input;
        private NestedScrollView scroll;
        private boolean loadedOnce;

        static ChatFragment newInstance(String peerUid) {
            ChatFragment fragment = new ChatFragment(); Bundle args = new Bundle();
            args.putString("peer_uid", peerUid == null ? "" : peerUid); fragment.setArguments(args); return fragment;
        }

        @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater,
                                                      @Nullable ViewGroup container,
                                                      @Nullable Bundle state) {
            View root = inflater.inflate(R.layout.fragment_chat, container, false);
            peerUid = getArguments() == null ? "" : getArguments().getString("peer_uid", "");
            account = new tAccUtils(requireContext().getApplicationContext());
            messages = root.findViewById(R.id.chat_messages); refresh = root.findViewById(R.id.chat_refresh);
            scroll = root.findViewById(R.id.chat_scroll);
            input = root.findViewById(R.id.chat_input);
            View sendButton = root.findViewById(R.id.chat_send);
            input.addTextChangedListener(new android.text.TextWatcher() {
                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
                @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                    sendButton.setEnabled(s != null && !s.toString().trim().isEmpty());
                }
                @Override public void afterTextChanged(android.text.Editable s) { }
            });
            View composer = root.findViewById(R.id.chat_composer);
            int baseBottom = composer.getPaddingBottom();
            ViewCompat.setOnApplyWindowInsetsListener(root, (view, insets) -> {
                Insets ime = insets.getInsets(WindowInsetsCompat.Type.ime());
                Insets nav = insets.getInsets(WindowInsetsCompat.Type.navigationBars());
                composer.setPadding(composer.getPaddingLeft(), composer.getPaddingTop(),
                        composer.getPaddingRight(), baseBottom + Math.max(0, ime.bottom - nav.bottom));
                if (insets.isVisible(WindowInsetsCompat.Type.ime())) scrollToBottom();
                return insets;
            });
            ViewCompat.requestApplyInsets(root);
            refresh.setColorSchemeColors(requireContext().getColor(R.color.brand_primary));
            refresh.setOnRefreshListener(this::load);
            root.findViewById(R.id.chat_send).setOnClickListener(v -> send());
            load(); return root;
        }

        private void load() {
            if (!loadedOnce) ((BaseSectionActivity) requireActivity()).showContentLoading();
            if (peerUid.isEmpty()) { showError("缺少聊天对象"); return; }
            Map<String, String> query = page(); query.put("peer_uid", peerUid);
            account.getV2Json("messages2", query, true, new tAccUtils.JsonCallback() {
                @Override public void onSuccess(@NonNull JSONObject json) {
                    JSONArray items = json.optJSONArray("items"); main.post(() -> render(items));
                }
                @Override public void onError(int code, @NonNull String message) { main.post(() -> showError(message)); }
            });
        }

        private void render(@Nullable JSONArray items) {
            if (!isAdded()) return;
            finishInitialLoading(); refresh.setRefreshing(false); messages.removeAllViews();
            if (items == null || items.length() == 0) { messages.addView(emptyState("还没有消息", "打个招呼，开始聊天。")); return; }
            long previousTime = 0L;
            for (int i = items.length() - 1; i >= 0; i--) {
                JSONObject item = items.optJSONObject(i);
                if (item == null) continue;
                long currentTime = parseTime(item.optString("created_at", ""));
                if (previousTime == 0L || currentTime - previousTime >= 5 * 60_000L) {
                    messages.addView(timeLabel(item.optString("created_at", "")));
                }
                messages.addView(bubble(item));
                previousTime = currentTime;
            }
            markRead();
            scrollToBottom();
        }

        private View timeLabel(String raw) {
            TextView label = stateText(shortTime(raw));
            label.setGravity(Gravity.CENTER); label.setTextSize(12);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
            params.topMargin = dp(7); params.bottomMargin = dp(7); label.setLayoutParams(params);
            return label;
        }

        private long parseTime(String value) {
            try { return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.getDefault())
                    .parse(value).getTime(); } catch (Exception ignored) { return 0L; }
        }

        private String shortTime(String value) {
            try {
                java.util.Date time = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                        java.util.Locale.getDefault()).parse(value);
                java.text.SimpleDateFormat day = new java.text.SimpleDateFormat("yyyy-MM-dd",
                        java.util.Locale.getDefault());
                String pattern = day.format(time).equals(day.format(new java.util.Date())) ? "HH:mm" : "MM-dd HH:mm";
                return new java.text.SimpleDateFormat(pattern, java.util.Locale.getDefault()).format(time);
            } catch (Exception ignored) { return value; }
        }

        private View bubble(JSONObject item) {
            boolean mine = account.getUid().equals(item.optString("sender_uid"));
            MaterialCardView card = new MaterialCardView(requireContext()); card.setRadius(dp(18));
            card.setStrokeWidth(0); card.setCardElevation(0);
            card.setCardBackgroundColor(requireContext().getColor(mine ? R.color.brand_soft : R.color.surface_primary));
            TextView text = stateText(item.optString("content", "")); text.setTextColor(requireContext().getColor(R.color.text_primary));
            text.setPadding(dp(14), dp(10), dp(14), dp(10)); card.addView(text);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-2, -2); p.gravity = mine ? Gravity.END : Gravity.START;
            p.bottomMargin = dp(8); p.setMarginStart(mine ? dp(54) : 0); p.setMarginEnd(mine ? 0 : dp(54)); card.setLayoutParams(p);
            return card;
        }

        private void send() {
            String body = input.getText() == null ? "" : input.getText().toString().trim();
            if (body.isEmpty() || peerUid.isEmpty()) return;
            Map<String, String> fields = new LinkedHashMap<>(); fields.put("recipient_uid", peerUid);
            fields.put("content", body); fields.put("message_type", "text"); fields.put("metadata", "{}");
            account.postV2Json("message_send2", fields, new tAccUtils.JsonCallback() {
                @Override public void onSuccess(@NonNull JSONObject json) { main.post(() -> { if (!isAdded()) return; input.setText(""); load(); }); }
                @Override public void onError(int code, @NonNull String message) { main.post(() -> showDialog(message)); }
            });
        }

        private void markRead() {
            Map<String, String> fields = new LinkedHashMap<>(); fields.put("peer_uid", peerUid);
            account.postV2Json("conversation_read2", fields, new tAccUtils.JsonCallback() {
                @Override public void onSuccess(@NonNull JSONObject json) { }
                @Override public void onError(int code, @NonNull String message) { }
            });
        }

        private void showError(String value) { if (!isAdded()) return; finishInitialLoading(); refresh.setRefreshing(false); messages.removeAllViews(); messages.addView(emptyState("暂时无法加载消息", value)); }
        private void scrollToBottom() {
            if (scroll != null) scroll.post(() -> scroll.fullScroll(View.FOCUS_DOWN));
        }
        private void finishInitialLoading() {
            if (loadedOnce) return;
            loadedOnce = true;
            ((BaseSectionActivity) requireActivity()).hideContentLoading();
        }
        private View emptyState(String title, String description) {
            View state = getLayoutInflater().inflate(R.layout.view_stream_empty, messages, false);
            ((TextView) state.findViewById(R.id.stream_empty_title)).setText(title);
            ((TextView) state.findViewById(R.id.stream_empty_description)).setText(description);
            state.setVisibility(View.VISIBLE);
            return state;
        }
        private void showDialog(String value) { if (isAdded()) new WGProAlertDialogBuilder(requireContext()).setTitle("发送失败").setMessage(value).setNegativeButton("关闭", null).show(); }
        private TextView stateText(String value) { TextView text = new TextView(requireContext()); text.setText(value); text.setTextSize(14); text.setTextColor(requireContext().getColor(R.color.text_secondary)); text.setTypeface(Typeface.DEFAULT); text.setPadding(dp(8), dp(12), dp(8), dp(12)); return text; }
        private Map<String, String> page() { Map<String, String> q = new LinkedHashMap<>(); q.put("page", "1"); q.put("size", "50"); return q; }
        private int dp(int v) { return Math.round(v * getResources().getDisplayMetrics().density); }
    }
}
