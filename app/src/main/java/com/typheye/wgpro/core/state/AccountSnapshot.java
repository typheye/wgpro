package com.typheye.wgpro.core.state;

/** 账户会话的只读快照。 */
public final class AccountSnapshot {
    public final boolean loggedIn;
    public final String uid;
    public final String nick;
    public final String shuo;
    public final boolean v2;
    /** 本轮刷新是否需要重新拉取头像（对应 get_data_update2 的 v3 摘要）。 */
    public final boolean avatarChanged;

    public AccountSnapshot(boolean loggedIn, String uid, String nick, String shuo, boolean v2,
                           boolean avatarChanged) {
        this.loggedIn = loggedIn;
        this.uid = uid == null ? "" : uid;
        this.nick = nick == null ? "" : nick;
        this.shuo = shuo == null ? "" : shuo;
        this.v2 = v2;
        this.avatarChanged = avatarChanged;
    }

    public static AccountSnapshot loggedOut() {
        return new AccountSnapshot(false, "", "", "", false, false);
    }
}
