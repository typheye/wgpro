package com.typheye.wgpro.core;

import com.typheye.wgpro.core.state.InboxSnapshot;

/** `.Push` 服务对外（UI 侧）暴露的命令接口。 */
public interface PushApi {
    InboxSnapshot inbox();

    /** 主动校准一次未读与缓存（页面下拉、回到前台时调用）。 */
    void refreshNow();

    void setDoNotDisturb(boolean enabled);

    void cancelPrivateNotifications(String peerUid);

    void cancelSystemNotifications();
}
