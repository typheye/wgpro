package com.typheye.wgpro.ui.function.device;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/** 云端机型的字段（api.php?type=vela_devices）。 */
public final class VelaModel {

    @SerializedName("id")
    public String id = "";

    @SerializedName("name")
    public String name = "";

    @SerializedName("code")
    public String code = "";

    @SerializedName("type")
    public String type = "";

    @SerializedName("adapted")
    public boolean adapted;

    @SerializedName("imageUrl")
    public String imageUrl = "";

    @SerializedName("sizeLabel")
    public String sizeLabel = "";

    @SerializedName("note")
    public String note = "";

    /** 详情页字段：版本 / 更新时间 / 1:1 封面 / 简介 / 多节点镜像。 */
    @SerializedName("version")
    public String version = "";

    @SerializedName("updatedAt")
    public String updatedAt = "";

    @SerializedName("coverUrl")
    public String coverUrl = "";

    @SerializedName("description")
    public String description = "";

    /** 介绍数据（服务端已按镜像 config.ini 下发，客户端不再必须自己抓 ini）。 */
    @SerializedName("author")
    public String author = "";

    @SerializedName("source")
    public String source = "";

    @SerializedName("downloadCount")
    public int downloadCount;

    @SerializedName("download_mirrors")
    public java.util.List<Mirror> downloadMirrors;

    /** 下载镜像节点（id 与 res.typheye.cn/api.php?action=nodes 的 nodes[].id 对应）。 */
    public static class Mirror {
        @SerializedName("id")
        public String id;
        @SerializedName("url")
        public String url;
    }

    /** 卡片副标题：机型标识 + 状态。 */
    @NonNull
    public String subtitle() {
        StringBuilder sb = new StringBuilder();
        if (code != null && !code.isEmpty()) {
            sb.append(code);
        }
        if (note != null && !note.isEmpty()) {
            if (sb.length() > 0) {
                sb.append(" · ");
            }
            sb.append(note);
        }
        return sb.toString();
    }

    /** 详情弹窗里的说明。 */
    @NonNull
    public String detail() {
        StringBuilder sb = new StringBuilder();
        sb.append("机型：").append(name);
        if (code != null && !code.isEmpty()) {
            sb.append("\n标识：").append(code);
        }
        if (sizeLabel != null && !sizeLabel.isEmpty()) {
            sb.append("\n需要下载：").append(sizeLabel);
        }
        sb.append("\n\n创建后可直接在手机里运行该设备的系统。");
        return sb.toString();
    }
}
