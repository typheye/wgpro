package com.typheye.wgpro.core;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.typheye.wgpro.core.state.AccountSnapshot;
import com.typheye.wgpro.core.state.AppState;
import com.typheye.wgpro.utils.tAccUtils;

/**
 * 账户与会话引擎（原 MainActivity.getAccUtils 的轮询逻辑）。
 *
 * 变化点：
 *  - 从 Activity 生命周期解绑，页面暂停后仍然继续（这是后台能及时登出/更新的前提）；
 *  - 失败时指数退避，避免弱网下每 15 秒硬打一次；
 *  - 会话失效只走"清除本地凭据 + 上报"，不再由 Activity 弹通知。
 */
final class AccountEngine {
    interface Listener {
        void onSessionRevoked();

        void onAccountChanged(AccountSnapshot snapshot, boolean avatarChanged);
    }

    private static final long BASE_INTERVAL_MS = 15_000L;
    private static final long MAX_INTERVAL_MS = 300_000L;
    private static final long IDLE_INTERVAL_MS = 60_000L;

    private final Context context;
    private final Listener listener;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final tAccUtils account;

    private boolean running;
    private boolean inFlight;
    private long interval = BASE_INTERVAL_MS;
    private long lastForegroundAt = System.currentTimeMillis();

    private final Runnable tick = new Runnable() {
        @Override
        public void run() {
            if (!running) return;
            poll();
            handler.postDelayed(this, interval);
        }
    };

    AccountEngine(Context context, Listener listener) {
        this.context = context.getApplicationContext();
        this.listener = listener;
        this.account = new tAccUtils(this.context);
    }

    boolean isLoggedIn() {
        return account.isLogin();
    }

    String uid() {
        return account.getUid();
    }

    AccountSnapshot snapshot(boolean avatarChanged) {
        boolean loggedIn = account.isLogin();
        AccountSnapshot value = loggedIn
                ? new AccountSnapshot(true, account.getUid(), account.getNick(), account.getShuo(),
                        account.isV2Session(), avatarChanged)
                : AccountSnapshot.loggedOut();
        listener.onAccountChanged(value, avatarChanged);
        return value;
    }

    void start() {
        if (running) return;
        running = true;
        interval = BASE_INTERVAL_MS;
        handler.removeCallbacks(tick);
        handler.post(tick);
    }

    void stop() {
        running = false;
        inFlight = false;
        handler.removeCallbacks(tick);
    }

    /** 页面回到前台时调用：立刻刷新一次并恢复 15 秒节奏。 */
    void refreshNow() {
        lastForegroundAt = System.currentTimeMillis();
        interval = BASE_INTERVAL_MS;
        if (!running) return;
        handler.removeCallbacks(tick);
        handler.post(tick);
    }

    /** 长时间无交互时降低频率。 */
    void onScreenOff() {
        if (System.currentTimeMillis() - lastForegroundAt > 60_000L) {
            interval = Math.max(interval, IDLE_INTERVAL_MS);
        }
    }

    private void poll() {
        if (!running || inFlight) return;
        if (!account.isLogin()) {
            snapshot(false);
            interval = BASE_INTERVAL_MS;
            return;
        }
        inFlight = true;
        account.getUserDataUpdateJson(new tAccUtils.UserDataUpdateCallback() {
            @Override
            public void onSuccess(tAccUtils.UserDataUpdateResult result) {
                if (!result.isLoginValid) {
                    inFlight = false;
                    account.logout();
                    snapshot(false);
                    listener.onSessionRevoked();
                    return;
                }
                account.updateUserData(new tAccUtils.SetCallback() {
                    @Override
                    public void onSuccess() {
                        inFlight = false;
                        interval = BASE_INTERVAL_MS;
                        snapshot(result.v3Changed);
                    }

                    @Override
                    public void onError(String message) {
                        inFlight = false;
                        interval = BASE_INTERVAL_MS;
                        snapshot(false);
                    }
                });
            }

            @Override
            public void onError(String message) {
                inFlight = false;
                interval = Math.min(MAX_INTERVAL_MS, interval * 2);
            }
        });
    }
}
