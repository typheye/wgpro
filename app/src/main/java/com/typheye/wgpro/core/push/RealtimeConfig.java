package com.typheye.wgpro.core.push;

import org.json.JSONObject;

/** 服务端实时能力探测结果（realtime_config2）。 */
public final class RealtimeConfig {
    public final boolean enabled;
    public final String url;
    public final int heartbeat;
    public final int pollInterval;

    private RealtimeConfig(boolean enabled, String url, int heartbeat, int pollInterval) {
        this.enabled = enabled;
        this.url = url;
        this.heartbeat = heartbeat;
        this.pollInterval = pollInterval;
    }

    public static RealtimeConfig disabled() {
        return new RealtimeConfig(false, "", 25, 15);
    }

    public static RealtimeConfig from(JSONObject json) {
        if (json == null) return disabled();
        return new RealtimeConfig(
                json.optBoolean("enabled", false),
                json.optString("url", ""),
                json.optInt("heartbeat", 25),
                json.optInt("poll_interval", 15));
    }
}
