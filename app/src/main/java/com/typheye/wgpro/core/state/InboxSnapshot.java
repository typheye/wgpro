package com.typheye.wgpro.core.state;

/** 通知中心与私信的未读快照。 */
public final class InboxSnapshot {
    public final int unreadTotal;
    public final int notificationUnread;
    public final int messageUnread;
    public final boolean doNotDisturb;
    public final long updatedAt;

    public InboxSnapshot(int unreadTotal, int notificationUnread, int messageUnread,
                         boolean doNotDisturb, long updatedAt) {
        this.unreadTotal = Math.max(0, unreadTotal);
        this.notificationUnread = Math.max(0, notificationUnread);
        this.messageUnread = Math.max(0, messageUnread);
        this.doNotDisturb = doNotDisturb;
        this.updatedAt = updatedAt;
    }

    public static InboxSnapshot empty() {
        return new InboxSnapshot(0, 0, 0, false, 0L);
    }

    /** 工具栏红点：免打扰开启时不显示。 */
    public boolean dotVisible() {
        return !doNotDisturb && unreadTotal > 0;
    }
}
