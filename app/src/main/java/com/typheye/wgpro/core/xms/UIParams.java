package com.typheye.wgpro.core.xms;

public class UIParams {
    public boolean connected = false;
    public String connected_device_name = "未知设备";
    public String connected_device_id = "";
    public long connected_since = 0L;
    public boolean mifitness_connected = false;
    public boolean device_permission = false;
    public String qrcode_key = "";

    /** 快照副本：CoreService 发布给 UI 时使用，避免观察者读到半更新状态。 */
    public UIParams copy() {
        UIParams copy = new UIParams();
        copy.connected = connected;
        copy.connected_device_name = connected_device_name;
        copy.connected_device_id = connected_device_id;
        copy.connected_since = connected_since;
        copy.mifitness_connected = mifitness_connected;
        copy.device_permission = device_permission;
        copy.qrcode_key = qrcode_key;
        return copy;
    }
}
