package com.typheye.wgpro.core;

import com.typheye.wgpro.core.xms.UIParams;

/** `.Core` 服务对外（UI 侧）暴露的命令接口。同进程 Binder 调用，无序列化开销。 */
public interface CoreApi {
    boolean isLoggedIn();

    String uid();

    UIParams device();

    /** 立即触发一次账户资料刷新（替代原来 MainActivity.refreshAccountFromUser()）。 */
    void requestAccountRefresh();

    /** 重新探测穿戴设备连接。 */
    void reconnectWearable();

    /** 扫描页发现设备时同步设备状态。 */
    void markDeviceCandidate(String nodeId, String nodeName);

    /** 重新拉取服务端配置/更新信息。 */
    void refreshServerConfig();
}
