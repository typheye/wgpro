package com.typheye.wgpro.ui.function.community;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.card.MaterialCardView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;
import com.typheye.wgpro.R;
import com.typheye.wgpro.data.MessageDatabase;
import com.typheye.wgpro.ui.function.account.UserDetailActivity;
import com.typheye.wgpro.ui.widget.WGProAlertDialogBuilder;
import com.typheye.wgpro.ui.widget.WGProBottomSheetDialog;
import com.typheye.wgpro.ui.widget.WGProProgressRunner;
import com.typheye.wgpro.utils.InboxNotificationHelper;
import com.typheye.wgpro.utils.tAccUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChatActivity extends BaseSectionActivity {
    public static final String EXTRA_PEER_UID = "peer_uid";
    public static final String EXTRA_PEER_NAME = "peer_name";
    public static final String EXTRA_MODE = "mode";
    public static final String MODE_SYSTEM_MESSAGES = "system_messages";
    @Override protected String screenTitle() {
        if (MODE_SYSTEM_MESSAGES.equals(getIntent().getStringExtra(EXTRA_MODE))) return "系统消息";
        String name = getIntent().getStringExtra(EXTRA_PEER_NAME);
        return name == null || name.trim().isEmpty() ? "私信" : name;
    }

    /**
     * 私信页底部是固定输入栏，必须整体位于系统导航栏上方；
     * 系统消息页没有固定底栏，走滚动视图预留，内容可以延伸到导航条区域。
     */
    @Override protected boolean hasPinnedBottomBar() {
        return !MODE_SYSTEM_MESSAGES.equals(getIntent().getStringExtra(EXTRA_MODE));
    }

    /** 输入栏自带导航栏内边距（沉浸式），容器不再让开系统栏。 */
    @Override protected boolean bottomBarOwnsNavigationInset() {
        return hasPinnedBottomBar();
    }
    @Override protected Fragment createContent() {
        if (MODE_SYSTEM_MESSAGES.equals(getIntent().getStringExtra(EXTRA_MODE))) {
            return new SystemMessageDetailFragment();
        }
        return ChatFragment.newInstance(getIntent().getStringExtra(EXTRA_PEER_UID));
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_toolbar_menu, menu);
        MenuItem more = menu.findItem(R.id.action_chat_more);
        if (more != null && more.getIcon() != null) {
            Drawable icon = DrawableCompat.wrap(more.getIcon()).mutate();
            DrawableCompat.setTint(icon, getColor(R.color.text_primary));
            more.setIcon(icon);
        }
        return true;
    }

    @Override public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_chat_more) {
            showChatMenu();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showChatMenu() {
        Fragment current = getSupportFragmentManager().findFragmentById(R.id.section_container);
        if (current instanceof SystemMessageDetailFragment) {
            ((SystemMessageDetailFragment) current).showMoreMenu();
            return;
        }
        String peerUid = getIntent().getStringExtra(EXTRA_PEER_UID);
        new WGProAlertDialogBuilder(this)
                .setTitle("更多")
                .setItems(new CharSequence[]{"主页", "清空聊天记录"}, (dialog, which) -> {
                    if (which == 0) openPeerProfile(peerUid);
                    else clearChatHistory();
                }).show();
    }

    private void clearChatHistory() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.section_container);
        if (fragment instanceof ChatFragment) {
            ((ChatFragment) fragment).showClearHistoryConfirm();
        }
    }

    private void openPeerProfile(String peerUid) {
        if (peerUid == null || peerUid.trim().isEmpty()) return;
        startActivity(new android.content.Intent(this, UserDetailActivity.class)
                .putExtra(UserDetailActivity.EXTRA_TARGET_UID, peerUid.trim()));
    }

    public static final class ChatFragment extends Fragment {
        private final Handler main = new Handler(Looper.getMainLooper());
        private String peerUid;
        private tAccUtils account;
        private MessageDatabase messageDb;
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
            messageDb = new MessageDatabase(requireContext());
            if (!peerUid.isEmpty()) {
                InboxNotificationHelper.cancelPrivateNotifications(requireContext(), peerUid);
            }
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
            android.widget.ImageView chatBlur = root.findViewById(R.id.chat_bottom_blur);
            com.typheye.wgpro.utils.BarBlurController.registerBar(requireContext(), composer);
            // 与页面容器共用同一个毛玻璃控制器：快照源换成消息滚动区（不含输入栏），
            // 再追加输入栏快照层——一份快照驱动顶栏+底栏，与 MainActivity 一致。
            com.typheye.wgpro.utils.BarBlurController controller =
                    ((BaseSectionActivity) requireActivity()).blurController();
            if (controller != null) {
                controller.rebindSource(refresh);
                controller.addBackdrop(chatBlur);
            } else {
                com.typheye.wgpro.utils.BarBlurController.install(requireActivity(), refresh, chatBlur);
            }
            int baseBottom = composer.getPaddingBottom();
            ViewCompat.setOnApplyWindowInsetsListener(root, (view, insets) -> {
                int ime = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom;
                // 输入栏自己吃导航栏/键盘/小窗把手的内边距（沉浸 + 小窗不重合）
                int bottom = Math.max(com.typheye.wgpro.utils.SystemBars
                        .bottomInsetForView(view, insets), ime);
                composer.setPadding(composer.getPaddingLeft(), composer.getPaddingTop(),
                        composer.getPaddingRight(), baseBottom + bottom);
                if (insets.isVisible(WindowInsetsCompat.Type.ime())) scrollToBottom();
                return insets;
            });
            ViewCompat.requestApplyInsets(root);
            // 消息列表底部留出输入栏高度，最后一条消息也能滚到输入栏上方（内容从毛玻璃下穿过）
            root.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
                int barHeight = composer.getHeight();
                if (barHeight > 0 && scroll.getPaddingBottom() != barHeight) {
                    scroll.setClipToPadding(false);
                    scroll.setPadding(scroll.getPaddingLeft(), scroll.getPaddingTop(),
                            scroll.getPaddingRight(), barHeight);
                }
            });
            refresh.setColorSchemeColors(requireContext().getColor(R.color.brand_primary));
            refresh.setOnRefreshListener(this::load);
            root.findViewById(R.id.chat_send).setOnClickListener(v -> send());
            load(); return root;
        }

        @Override public void onViewCreated(@NonNull View view, @Nullable Bundle state) {
            super.onViewCreated(view, state);
            observeRealtime();
        }

        @Override public void onResume() {
            super.onResume();
            // 用户正在看这个会话：期间不再为它弹 Android 通知
            com.typheye.wgpro.core.state.AppState.get().setActiveChatPeer(peerUid);
            if (loadedOnce) load(true);
        }

        @Override public void onPause() {
            com.typheye.wgpro.core.state.AppState.get().setActiveChatPeer("");
            super.onPause();
        }

        /**
         * 离开页面时如果首次加载还没收尾，必须把加载遮罩收掉，
         * 否则遮罩会留在 Activity 上，看起来像"永远加载中"。
         */
        @Override public void onDestroyView() {
            if (!loadedOnce && isAdded() && requireActivity() instanceof BaseSectionActivity) {
                finishInitialLoading();
            }
            super.onDestroyView();
        }

        private void load() {
            load(false);
        }

        /** 实时事件到达时的静默刷新：不显示加载遮罩，并尽量保持当前阅读位置。 */
        private void load(boolean silent) {
            if (!loadedOnce && !silent) ((BaseSectionActivity) requireActivity()).showContentLoading();
            if (peerUid.isEmpty()) { showError("缺少聊天对象"); return; }
            Map<String, String> query = page(); query.put("peer_uid", peerUid);
            account.getV2Json("messages2", query, true, new tAccUtils.JsonCallback() {
                @Override public void onSuccess(@NonNull JSONObject json) {
                    JSONArray items = json.optJSONArray("items");
                    main.post(() -> render(items, silent));
                }
                @Override public void onError(int code, @NonNull String message) { main.post(() -> showError(message)); }
            });
        }

        /** 订阅推送事件：与当前对端相关的私信到达时立即刷新。 */
        private void observeRealtime() {
            com.typheye.wgpro.core.state.AppState.get().pushEvents().observe(
                    getViewLifecycleOwner(), event -> {
                        if (event == null || !"message".equals(event.type)) return;
                        if (peerUid == null || peerUid.isEmpty()) return;
                        if (!peerUid.equals(event.peerUid)) return;
                        load(true);
                    });
        }

        private void render(@Nullable JSONArray items) {
            render(items, false);
        }

        private void render(@Nullable JSONArray items, boolean silent) {
            if (!isAdded()) return;
            boolean wasAtBottom = true;
            int previousScroll = 0;
            if (silent && scroll != null) {
                previousScroll = scroll.getScrollY();
                View child = scroll.getChildAt(0);
                wasAtBottom = child == null
                        || previousScroll + scroll.getHeight() >= child.getHeight() - dp(48);
            }
            refresh.setRefreshing(false); messages.removeAllViews();
            if (items == null || items.length() == 0) {
                // 空会话（对方还没回过消息、或按 id 过滤后没有可见消息）同样要收尾，
                // 否则首次加载遮罩永远不会消失，整页连同输入框都点不动。
                messages.addView(emptyState("还没有消息", "打个招呼，开始聊天。"));
                scrollToBottomThen(this::finishInitialLoading);
                return;
            }
            long clearedAt = clearedMessageAt();
            String clearedTime = clearedTimeString(clearedAt);
            long previousTime = 0L;
            int added = 0;
            for (int i = items.length() - 1; i >= 0; i--) {
                JSONObject item = items.optJSONObject(i);
                if (item == null) continue;
                if (clearedAt > 0
                        && item.optString("created_at", "").compareTo(clearedTime) <= 0) continue;
                long currentTime = parseTime(item.optString("created_at", ""));
                if (previousTime == 0L || currentTime - previousTime >= 5 * 60_000L) {
                    messages.addView(timeLabel(item.optString("created_at", "")));
                }
                messages.addView(bubble(item));
                previousTime = currentTime;
                added++;
            }
            if (added == 0) {
                messages.addView(emptyState(clearedAt > 0 ? "聊天记录已清空" : "还没有消息",
                        clearedAt > 0 ? "新消息会继续显示在这里。" : "打个招呼，开始聊天。"));
            }
            markRead();
            if (!silent || wasAtBottom) {
                // 先滚动到底部、再撤掉加载遮罩：用户看不到"从顶部跳到末尾"
                scrollToBottomThen(this::finishInitialLoading);
            } else if (scroll != null) {
                int restore = previousScroll;
                scroll.post(() -> scroll.scrollTo(0, restore));
                finishInitialLoading();
            } else {
                finishInitialLoading();
            }
        }

        /** 滚动到底部后执行收尾（例如隐藏加载遮罩），保证遮罩消失时已经在底部。 */
        private void scrollToBottomThen(Runnable after) {
            if (scroll == null) {
                after.run();
                return;
            }
            scroll.post(() -> {
                scroll.fullScroll(View.FOCUS_DOWN);
                scroll.postOnAnimation(after);
            });
        }

        public void showClearHistoryConfirm() {
            if (!isAdded()) return;
            new WGProAlertDialogBuilder(requireContext())
                    .setTitle("清空聊天记录？")
                    .setMessage("仅清除当前设备上的本地聊天记录，不会影响对方。")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("清空", (dialog, which) -> {
                        if (dialog instanceof WGProBottomSheetDialog) {
                            ((WGProBottomSheetDialog) dialog).dismissForReplacement();
                        }
                        new Handler(Looper.getMainLooper()).postDelayed(
                                this::clearLocalHistory, 40L);
                    }).show();
        }

        private void clearLocalHistory() {
            WGProProgressRunner.run(this, "处理中", "正在清空聊天记录...", completion -> {
                long clearedAt = System.currentTimeMillis();
                messageDb.setConversationClearedAt(account.getUid(), peerUid, clearedAt);
                messageDb.upsertConversationPreview(account.getUid(), peerUid, "", "", "");
                completion.success(new JSONObject());
            }, new WGProProgressRunner.Callback() {
                @Override public void success(@NonNull JSONObject json) {
                    load();
                    showResult("已清空", "本地聊天记录已清空。");
                }

                @Override public void error(int code, @NonNull String message) {
                    showResult("清空失败", message);
                }
            });
        }

        private long clearedMessageAt() {
            return messageDb.getConversationClearedAt(account.getUid(), peerUid);
        }

        private String clearedTimeString(long millis) {
            return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss", java.util.Locale.US)
                    .format(new java.util.Date(millis));
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
            boolean recalled = isRecalled(item);
            if (recalled) {
                TextView state = stateText("消息已撤回");
                state.setTextSize(13);
                state.setTextColor(requireContext().getColor(R.color.text_tertiary));
                state.setGravity(Gravity.CENTER);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
                params.topMargin = dp(6);
                params.bottomMargin = dp(10);
                state.setLayoutParams(params);
                state.setOnLongClickListener(v -> {
                    showMessageMenu(item);
                    return true;
                });
                return state;
            }
            MaterialCardView card = new MaterialCardView(requireContext()); card.setRadius(dp(18));
            card.setStrokeWidth(0); card.setCardElevation(0);
            card.setCardBackgroundColor(requireContext().getColor(mine ? R.color.brand_soft : R.color.surface_secondary));
            TextView text = stateText(item.optString("content", "")); text.setTextColor(requireContext().getColor(R.color.text_primary));
            text.setPadding(dp(14), dp(10), dp(14), dp(10)); card.addView(text);
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-2, -2); p.gravity = mine ? Gravity.END : Gravity.START;
            p.bottomMargin = dp(8); p.setMarginStart(mine ? dp(54) : 0); p.setMarginEnd(mine ? 0 : dp(54)); card.setLayoutParams(p);
            card.setLongClickable(true);
            card.setOnLongClickListener(v -> {
                showMessageMenu(item);
                return true;
            });
            return card;
        }

        private void showMessageMenu(JSONObject item) {
            if (!isAdded()) return;
            boolean mine = account.getUid().equals(item.optString("sender_uid"));
            boolean recalled = isRecalled(item);
            boolean canRecall = mine && !recalled
                    && withinRecallWindow(item.optString("created_at", ""));
            java.util.ArrayList<CharSequence> menu = new java.util.ArrayList<>();
            menu.add("消息详情");
            menu.add("复制");
            menu.add("删除");
            if (canRecall) menu.add("撤回");
            CharSequence[] actions = menu.toArray(new CharSequence[0]);
            new WGProAlertDialogBuilder(requireContext())
                    .setTitle("消息操作").setItems(actions, (dialog, which) -> {
                        String action = actions[which].toString();
                        if ("消息详情".equals(action)) showMessageDetail(item);
                        else if ("复制".equals(action)) copyText(item.optString("content", ""));
                        else if ("删除".equals(action)) confirmDeleteMessage(item);
                        else confirmRecallMessage(item);
                    }).show();
        }

        /** 复制消息文本到剪贴板。 */
        private void copyText(String text) {
            if (text == null || text.trim().isEmpty()) return;
            android.content.ClipboardManager clipboard =
                    (android.content.ClipboardManager) requireContext()
                            .getSystemService(android.content.Context.CLIPBOARD_SERVICE);
            if (clipboard == null) return;
            clipboard.setPrimaryClip(android.content.ClipData.newPlainText("text", text));
            android.widget.Toast.makeText(requireContext(), "已复制", android.widget.Toast.LENGTH_SHORT).show();
        }

        private void showMessageDetail(JSONObject item) {
            boolean mine = account.getUid().equals(item.optString("sender_uid"));
            boolean recalled = isRecalled(item);
            StringBuilder detail = new StringBuilder();
            detail.append("发送者：")
                    .append(mine ? "我" : item.optString("sender_nick", "对方"))
                    .append('\n');
            detail.append("发送时间：")
                    .append(item.optString("created_at", "未知")).append('\n');
            detail.append("消息类型：")
                    .append(messageTypeLabel(item.optString("message_type", "text")))
                    .append('\n');
            detail.append("状态：")
                    .append(recalled ? "已撤回"
                            : (mine ? (item.optBoolean("is_read") ? "已读" : "未读") : "已送达"))
                    .append('\n');
            detail.append("消息 ID：").append(item.optString("id", "未知")).append('\n');
            detail.append("内容：")
                    .append(recalled ? "该消息已撤回" : item.optString("content", ""));
            new WGProAlertDialogBuilder(requireContext()).setTitle("消息详情")
                    .setMessage(detail.toString())
                    .setNegativeButton("关闭", null).show();
        }

        private void confirmDeleteMessage(JSONObject item) {
            new WGProAlertDialogBuilder(requireContext())
                    .setTitle("删除消息？")
                    .setMessage("删除后该消息只会从当前账户的聊天记录中移除，不影响对方。")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("删除", (dialog, which) -> {
                        if (dialog instanceof WGProBottomSheetDialog) {
                            ((WGProBottomSheetDialog) dialog).dismissForReplacement();
                        }
                        new Handler(Looper.getMainLooper()).postDelayed(
                                () -> deleteMessage(item), 40L);
                    }).show();
        }

        private void deleteMessage(JSONObject item) {
            Map<String, String> fields = new LinkedHashMap<>();
            fields.put("message_id", item.optString("id", ""));
            fields.put("action", "delete");
            WGProProgressRunner.run(this, "删除中", "正在删除消息...", completion ->
                    account.postV2Json("message_state2", fields, new tAccUtils.JsonCallback() {
                        @Override public void onSuccess(@NonNull JSONObject json) {
                            completion.success(json);
                        }

                        @Override public void onError(int code, @NonNull String message) {
                            completion.error(code, message);
                        }
                    }), new WGProProgressRunner.Callback() {
                @Override public void success(@NonNull JSONObject json) {
                    load();
                    showResult("已删除", "该消息已从当前账户的聊天记录中移除。");
                }

                @Override public void error(int code, @NonNull String message) {
                    showResult("删除失败", message);
                }
            });
        }

        private void confirmRecallMessage(JSONObject item) {
            new WGProAlertDialogBuilder(requireContext())
                    .setTitle("撤回消息？")
                    .setMessage("撤回后双方都将看到“消息已撤回”，且无法恢复。")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("撤回", (dialog, which) -> {
                        if (dialog instanceof WGProBottomSheetDialog) {
                            ((WGProBottomSheetDialog) dialog).dismissForReplacement();
                        }
                        new Handler(Looper.getMainLooper()).postDelayed(
                                () -> recallMessage(item), 40L);
                    }).show();
        }

        private void recallMessage(JSONObject item) {
            Map<String, String> fields = new LinkedHashMap<>();
            fields.put("message_id", item.optString("id", ""));
            fields.put("action", "recall");
            WGProProgressRunner.run(this, "撤回中", "正在撤回...", completion ->
                    account.postV2Json("message_state2", fields, new tAccUtils.JsonCallback() {
                        @Override public void onSuccess(@NonNull JSONObject json) {
                            completion.success(json);
                        }

                        @Override public void onError(int code, @NonNull String message) {
                            completion.error(code, message);
                        }
                    }), new WGProProgressRunner.Callback() {
                @Override public void success(@NonNull JSONObject json) {
                    try {
                        item.put("recalled", true);
                        item.put("recalled_at", json.optString("recalled_at", ""));
                        item.put("content", "");
                    } catch (Exception ignored) { }
                    load();
                    showResult("已撤回", "消息已撤回");
                }

                @Override public void error(int code, @NonNull String message) {
                    showResult("撤回失败", message);
                }
            });
        }

        private boolean withinRecallWindow(String createdAt) {
            long time = parseTime(createdAt);
            return time > 0L && System.currentTimeMillis() - time <= 2 * 60_000L;
        }

        private boolean isRecalled(JSONObject item) {
            return item.optBoolean("recalled")
                    || (!item.isNull("recalled_at")
                    && !item.optString("recalled_at", "").isEmpty());
        }

        private String messageTypeLabel(String type) {
            if ("text".equalsIgnoreCase(type)) return "文本";
            if ("image".equalsIgnoreCase(type)) return "图片";
            if ("video".equalsIgnoreCase(type)) return "视频";
            if ("file".equalsIgnoreCase(type)) return "文件";
            return type == null || type.trim().isEmpty() ? "消息" : type;
        }

        private void showResult(String title, String message) {
            if (!isAdded()) return;
            new WGProAlertDialogBuilder(requireContext()).setTitle(title)
                    .setMessage(message == null || message.trim().isEmpty() ? "操作完成" : message)
                    .setNegativeButton("关闭", null).show();
        }

        private void send() {
            String body = input.getText() == null ? "" : input.getText().toString().trim();
            peerUid = peerUid == null ? "" : peerUid.trim();
            if (body.isEmpty() || peerUid.isEmpty() || !peerUid.matches("\\d+")) {
                showDialog("消息对象无效，请返回后重新打开聊天");
                return;
            }
            Map<String, String> fields = new LinkedHashMap<>(); fields.put("recipient_uid", peerUid);
            fields.put("content", body); fields.put("message_type", "text"); fields.put("metadata", "{}");
            account.postV2Json("message_send2", fields, new tAccUtils.JsonCallback() {
                @Override public void onSuccess(@NonNull JSONObject json) {
                    main.post(() -> {
                        if (!isAdded()) return;
                        String messageId = json.optString("message_id", "");
                        if (!messageId.isEmpty()) {
                            messageDb.setConversationRemoved(account.getUid(), peerUid, false);
                            messageDb.setConversationUnreadOverride(account.getUid(), peerUid, false);
                            messageDb.upsertConversationPreview(account.getUid(), peerUid,
                                    messageId, body, json.optString("created_at", ""));
                        }
                        input.setText("");
                        load();
                    });
                }
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
