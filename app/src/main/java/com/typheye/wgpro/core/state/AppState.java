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

    private final ServiceLog log = new ServiceLog(500);
    private final MutableLiveData<UIParams> device = new MutableLiveData<>(new UIParams());
    private final MutableLiveData<AccountSnapshot> account =
            new MutableLiveData<>(AccountSnapshot.loggedOut());
    private final MutableLiveData<InboxSnapshot> inbox =
            new MutableLiveData<>(InboxSnapshot.empty());
    private final MutableLiveData<PushEvent> pushEvents = new MutableLiveData<>();

    private AppState() {
        log.addAll("wgpro-android Tool V3", "https://github.com/typheye/wgpro");
    }

    public ServiceLog log() {
        return log;
    }

    public void addLog(String line) {
        log.add(line);
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

    public void publishPushEvent(@NonNull String type, @NonNull String peerUid, long id) {
        pushEvents.postValue(new PushEvent(type, peerUid, id));
    }

    /** 一条实时事件。type: message / notification。 */
    public static final class PushEvent {
        public final String type;
        public final String peerUid;
        public final long id;
        public final long at = System.currentTimeMillis();

        PushEvent(String type, String peerUid, long id) {
            this.type = type == null ? "" : type;
            this.peerUid = peerUid == null ? "" : peerUid;
            this.id = id;
        }
    }
}
