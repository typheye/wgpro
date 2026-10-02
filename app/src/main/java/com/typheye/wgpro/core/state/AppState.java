package com.typheye.wgpro.core.state;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.typheye.wgpro.core.xms.UIParams;

/**
 * 进程内唯一数据源。
 *
 * CoreService 与 PushService 写入，UI 只读订阅；页面不再自己轮询网络。
 * 所有写入都通过 {@code postValue}，可以从任意线程调用。
 */
public final class AppState {
    private static final AppState INSTANCE = new AppState();

    public static AppState get() {
        return INSTANCE;
    }

    /**
     * 日志按来源分通道存放，避免互相污染：
     *
     * <ul>
     *     <li>{@link #deviceLog}：XMS 穿戴设备通信（连接、权限、收发消息、内置页面 Fetch）。
     *         设备卡片里的「设备通信日志」只展示这一条通道。</li>
     *     <li>{@link #pushLog}：实时通道（WebSocket 握手/断开、轮询兜底、会话踢下线）。</li>
     *     <li>{@link #accountLog}：账户/会话状态与应用级系统事件。</li>
     * </ul>
     *
     * <p>{@link #log()} 仍返回设备日志，兼容 {@code MainActivity.logs} 与内置浏览器
     * {@code JSKit.GetLogs}（返回结构不变）。
     */
    private final ServiceLog deviceLog = new ServiceLog(400);
    private final ServiceLog pushLog = new ServiceLog(400);
    private final ServiceLog accountLog = new ServiceLog(200);
    private final MutableLiveData<UIParams> device = new MutableLiveData<>(new UIParams());
    private final MutableLiveData<AccountSnapshot> account =
            new MutableLiveData<>(AccountSnapshot.loggedOut());
    private final MutableLiveData<InboxSnapshot> inbox =
            new MutableLiveData<>(InboxSnapshot.empty());
    private final MutableLiveData<PushEvent> pushEvents = new MutableLiveData<>();
    private final java.util.concurrent.atomic.AtomicReference<String> activeChatPeer =
            new java.util.concurrent.atomic.AtomicReference<>("");
    private final java.util.concurrent.atomic.AtomicBoolean systemMessagesVisible =
            new java.util.concurrent.atomic.AtomicBoolean(false);

    private AppState() {
        deviceLog.addAll("wgpro-android Tool V3", "https://github.com/typheye/wgpro");
    }

    /** 兼容入口：历史上所有日志都写在这里，现在只保留设备日志语义。 */
    public ServiceLog log() {
        return deviceLog;
    }

    /** XMS / 穿戴设备通信日志。 */
    public ServiceLog deviceLog() {
        return deviceLog;
    }

    /** 实时通道（WebSocket / 轮询）日志。 */
    public ServiceLog pushLog() {
        return pushLog;
    }

    /** 账户会话与应用系统日志。 */
    public ServiceLog accountLog() {
        return accountLog;
    }

    /** 写入设备日志（保持历史调用点语义）。 */
    public void addLog(String line) {
        addDeviceLog(line);
    }

    public void addDeviceLog(String line) {
        deviceLog.add(line);
    }

    public void addPushLog(String line) {
        pushLog.add(line);
    }

    public void addAccountLog(String line) {
        accountLog.add(line);
    }

    public LiveData<UIParams> device() {
        return device;
    }

    public LiveData<AccountSnapshot> account() {
        return account;
    }

    public LiveData<InboxSnapshot> inbox() {
        return inbox;
    }

    /** 实时推送事件流：聊天页与系统消息页订阅它做即时刷新。 */
    public LiveData<PushEvent> pushEvents() {
        return pushEvents;
    }

    @NonNull
    public UIParams deviceSnapshot() {
        UIParams value = device.getValue();
        return value == null ? new UIParams() : value;
    }

    @NonNull
    public AccountSnapshot accountSnapshot() {
        AccountSnapshot value = account.getValue();
        return value == null ? AccountSnapshot.loggedOut() : value;
    }

    @NonNull
    public InboxSnapshot inboxSnapshot() {
        InboxSnapshot value = inbox.getValue();
        return value == null ? InboxSnapshot.empty() : value;
    }

    public void publishDevice(@NonNull UIParams value) {
        device.postValue(value);
    }

    public void publishAccount(@NonNull AccountSnapshot value) {
        account.postValue(value);
    }

    public void publishInbox(@NonNull InboxSnapshot value) {
        inbox.postValue(value);
    }

    public void publishPushEvent(@NonNull String type, @NonNull String peerUid, String id) {
        pushEvents.postValue(new PushEvent(type, peerUid, id));
    }

    /** 当前正在查看的私信对端（没有则为空）：用于"用户正在看这个会话就不发通知"。 */
    public void setActiveChatPeer(String peerUid) {
        activeChatPeer.set(peerUid == null ? "" : peerUid.trim());
    }

    public boolean isChatOpenFor(String peerUid) {
        if (peerUid == null || peerUid.isEmpty()) return false;
        return peerUid.equals(activeChatPeer.get());
    }

    /** 当前是否停留在系统消息详情页。 */
    public void setSystemMessagesVisible(boolean visible) {
        systemMessagesVisible.set(visible);
    }

    public boolean isSystemMessagesVisible() {
        return systemMessagesVisible.get();
    }

    /** 一条实时事件。type: message / notification。 */
    public static final class PushEvent {
        public final String type;
        public final String peerUid;
        public final String id;
        public final long at = System.currentTimeMillis();

        PushEvent(String type, String peerUid, String id) {
            this.type = type == null ? "" : type;
            this.peerUid = peerUid == null ? "" : peerUid;
            this.id = id == null ? "" : id;
        }
    }
}
