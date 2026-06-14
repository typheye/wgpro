/**
 ******************************************************************************
 * @file    WearChatRuntimeBridge.java
 * @author  Typheye
 * @brief   Wear chat runtime bridge.
 ******************************************************************************
 * @attention
 *
 * Copyright (c) 2021-2026 Typheye. All rights reserved.
 *
 * This software is licensed under terms that can be found in the LICENSE file
 * in the root directory of this software component.
 * If no LICENSE file comes with this software, it is provided AS-IS.
 *
 ******************************************************************************
 */
package open.cn.awg.pro.chat;



import android.annotation.SuppressLint;
import open.cn.awg.pro.core.IappCompat;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.listener.UploadProgressListener;

import i.app.iClass;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import open.cn.awg.pro.account.TypheyeAccountActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.data.LocalDatabaseBridge;
import open.cn.awg.pro.R;
import open.cn.awg.pro.settings.WearChatSettingsActivity;
import open.cn.awg.pro.ui.list.ChatAttachmentItemActivity;
import open.cn.awg.pro.ui.list.ChatRoomListItemActivity;
import open.cn.awg.pro.ui.list.WearChatMenuItemActivity;
import open.cn.awg.pro.ui.widget.XLinearLayoutManager;

@SuppressLint("StaticFieldLeak")
public class WearChatRuntimeBridge extends iClass {

    public static i.runlibrary.app.v.v7lb$UserAdapter wllbcdspq;

    public static i.runlibrary.app.v.v7lb wllbcdlb;

    public static i.runlibrary.app.v.v7lb$UserAdapter spq2;

    public static i.runlibrary.app.v.v7lb lb2;

    public static i.runlibrary.app.v.v7lb$UserAdapter spq3;

    public static i.runlibrary.app.v.v7lb lb3;

    public static Context context;

    public static LinearLayoutManager mLayoutManager;

    public static String set17 = AppPaths.appPath("settings/db/talking.db");

    public static i.runlibrary.app.sj$sjk sjk;

    public static int bjkmode = 0;

    public static boolean csh_once = false;
    public static boolean state_ltsgx1 = false;
    public static boolean state_ltsgx2 = false;
    public static boolean state_ltsgx3 = false;
    public static boolean state_post = false;
    public static boolean state_post_jb = false;
    public static boolean state_upload = false;
    public static boolean state_online = false;
    public final WearChatRuntimeBridge lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public LocalDatabaseBridge e11 = new LocalDatabaseBridge(_APPINFO);
    public String upload_back = "";

    public WearChatRuntimeBridge(i.runlibrary.app.AppInfo _APPINFO) {
        super(_APPINFO);

    }

    public String getTabName(String room_name) {

        String exist = "< ";

        if (zf.cd(room_name) > 5) {

            return exist + zf.qc(room_name, 0, 5) + "..";

        }
        return exist + room_name;

    }

    public boolean part_buildNewRoom(Object name, Object id) {
        gj.zt(500);

        final String set2 = AppPaths.appPath("settings/f11/set2.inf");
        name = zf.qctwkg(name);
        id = zf.qctwkg(id);

        if (zf.dy(name, "") || zf.dy(id, "")) {

            e1.tsk("提示", "提交失败：请填写完整");

        } else {

            try {

                String url = e1.urlUnlockString(wj.dqwb(set2));

                if (zf.cjw(url, "/")) {

                } else {

                    url = url + "/";

                }

                String idget = url + "class/api.php?type=make";

                String[] pt = new String[]{"title=" + name, "id=" + id, "pass=123456"};

                String st5 = wl.hq(idget, pt, "utf-8", null, true, null, 20000, 20000, null);

                if (zf.dy(st5, "null") || zf.dy(st5, "") || zf.dy(st5, null)) {

                    e1.tsk("提示", "提交失败：连接服务器异常");

                } else {

                    st5 = zf.qctwkg(st5);
                    st5 = zf.th(st5, "<br>", "  ");
                    e1.tsk("提示", st5);

                }

            } catch (Throwable __$_e__) {

                e1.tsk("提示", "提交失败：出现异常");

            }

        }
        return true;

    }

    public void lts_csh() {
        csh_once = true;
        wj.xrwb(AppPaths.appPath("data/ltlb/hdva"), "0");
        lb3 = st.v7lb(R.id.ltsv7lb1);

        context = AwgProApplication.getContext();
        mLayoutManager = new XLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        lb3.st.setLayoutManager(mLayoutManager);
        wj.sc(AppPaths.appPath("data/ltlb/disTouch"));
        spq3 = IappCompat.v7lbAdapter(lb3, ChatAttachmentItemActivity.class, R.layout.chat_attachment_item, new IappCompat.V7lbViewBinder() {

            public void bind(Object ua, int pn, View vw) {

                i.runlibrary.app.sj$lb lbsj = (i.runlibrary.app.sj$lb) (Object) sj.lb(vw);

                final int f = IappCompat.zh(sj.zh(lbsj.lbsj(pn, -1))).zint();

                if (f == -1) {

                    String color0 = zf.zf(R.color.colorTextTrue2);

                    String color1 = zf.zf(R.color.colorBlack);

                    String color2 = zf.zf(R.color.colorTextU1);
                    st.wb(vw, R.id.wb1).zfys(color1);
                    st.wb(vw, R.id.wb2).zfys(color2);
                    st.wb(vw, R.id.wb3).zfys(color2);
                    st.kp(vw, R.id.kp1).kpbjys(color0);
                    st.wb(vw, R.id.wb2).kjd(0);
                    st.xxbj(vw, R.id.xxbj1).dqfs("right");
                    st.xxbj(vw, R.id.xxbjat).kjd(8);
                    st.xdbj(vw, R.id.chat_attachment_item_root).xydj(true);
                    st.xdbj(vw, R.id.chat_attachment_item_root).xyca(true);
                    st.xxbj(vw, R.id.xxbj3).kjd(8);
                    st.wb(vw, R.id.wb21).kjd(8);
                    st.wb(vw, R.id.wb22).kjd(8);
                    st.xxbj(vw, R.id.xxbj24).kjd(8);
                    st.xxbj(vw, R.id.xxbj4k).kjd(8);

                    i.runlibrary.app.v.kp kp2 = st.kp(vw, R.id.kp2);
                    kp2.kjd(0);

                    i.runlibrary.app.v.xdbj$xdbjgz kp2_xdbjgz = (i.runlibrary.app.v.xdbj$xdbjgz) (Object) kp2.xdbjgz();
                    kp2_xdbjgz.dqfzb(false);
                    kp2_xdbjgz.dqfyb(true);

                    String wb = st.wb(vw, R.id.wb2).zf();

                    int isVip = 8;

                    int isVip2 = 0;

                    if (zf.dy(wb, "台风眼Typheye") || zf.dy(wb, "系统消息")) {

                        isVip = 0;
                        isVip2 = 8;

                    }
                    st.wb(vw, R.id.wb7).kjd(isVip);
                    st.wb(vw, R.id.wb21).kjd(isVip2);
                    st.wb(vw, R.id.wb6).zf("我");

                    Object luid = lbsj.lbsj(pn, -3);
                    luid = zf.qctwkg(luid);

                    if (zf.dy(luid, "w0") || zf.dy(luid, "") || zf.dy(luid, null) || zf.dy(luid, "0") || zf.dy(luid, "null")) {

                        st.wb(vw, R.id.wb22).kjd(0);

                    } else {

                        st.wb(vw, R.id.wb22).kjd(8);

                    }

                    if (zf.dy(st.wb(vw, R.id.wb21).zf(), "未知")) {

                        st.wb(vw, R.id.wb21).kjd(8);

                    } else {

                        st.wb(vw, R.id.wb21).kjd(0);

                    }

                    String wb2 = st.wb(vw, R.id.wb1).zf();

                    if (zf.cz(wb2, "[PHOTO]") && zf.cz(wb2, "[P:END]")) {

                        String ur = zf.qc(wb2, "[PHOTO]", "[P:END]");

                        String tx = zf.qc(wb2, null, "[PHOTO]");
                        st.xxbj(vw, R.id.xxbjfj).kjd(0);
                        st.wb(vw, R.id.wb8).zf("[附件]图片");
                        st.wb(vw, R.id.wb9).zf(ur);
                        st.tx(vw, R.id.tx2).tx(R.mipmap.b11);
                        st.wb(vw, R.id.wb1).zf(tx);
                        st.xxbj(vw, R.id.xxbj1).kjd(0);
                        st.wb(vw, R.id.wb10).kjd(8);
                        st.wb(vw, R.id.wb10).zf("");

                    } else if (zf.cz(wb2, "[AUDIO]") && zf.cz(wb2, "[A:END]")) {

                        String ur = zf.qc(wb2, "[AUDIO]", "[A:END]");

                        String tx = zf.qc(wb2, null, "[AUDIO]");
                        st.xxbj(vw, R.id.xxbjfj).kjd(0);
                        st.wb(vw, R.id.wb8).zf("[附件]音频");
                        st.wb(vw, R.id.wb9).zf(ur);
                        st.tx(vw, R.id.tx2).tx(R.mipmap.b9);
                        st.wb(vw, R.id.wb1).zf(tx);
                        st.xxbj(vw, R.id.xxbj1).kjd(0);
                        st.wb(vw, R.id.wb10).kjd(8);
                        st.wb(vw, R.id.wb10).zf("");

                    } else if (zf.cz(wb2, "[VIDEO]") && zf.cz(wb2, "[V:END]")) {

                        String ur = zf.qc(wb2, "[VIDEO]", "[V:END]");

                        String tx = zf.qc(wb2, null, "[VIDEO]");
                        st.xxbj(vw, R.id.xxbjfj).kjd(0);
                        st.wb(vw, R.id.wb8).zf("[附件]视频");
                        st.wb(vw, R.id.wb9).zf(ur);
                        st.tx(vw, R.id.tx2).tx(R.mipmap.b8);
                        st.wb(vw, R.id.wb1).zf(tx);
                        st.xxbj(vw, R.id.xxbj1).kjd(0);
                        st.wb(vw, R.id.wb10).kjd(8);
                        st.wb(vw, R.id.wb10).zf("");

                    } else if (zf.cz(wb2, "[OTHER]") && zf.cz(wb2, "[O:END]")) {

                        String ur = zf.qc(wb2, "[OTHER]", "[O:END]");

                        String tx = zf.qc(wb2, null, "[OTHER]");
                        st.xxbj(vw, R.id.xxbjfj).kjd(0);
                        st.wb(vw, R.id.wb8).zf("[附件]文件");
                        st.wb(vw, R.id.wb9).zf(ur);
                        st.tx(vw, R.id.tx2).tx(R.mipmap.b14);
                        st.wb(vw, R.id.wb1).zf(tx);
                        st.xxbj(vw, R.id.xxbj1).kjd(0);
                        st.wb(vw, R.id.wb10).kjd(8);
                        st.wb(vw, R.id.wb10).zf("");

                    } else if (zf.cz(wb2, "[INFOS]") && zf.cz(wb2, "[I:END]")) {

                        String ur = zf.qc(wb2, "[INFOS]", "[I:END]");

                        String tx = zf.qc(wb2, null, "[INFOS]");
                        st.xxbj(vw, R.id.xxbjfj).kjd(8);
                        st.wb(vw, R.id.wb8).zf("");
                        st.wb(vw, R.id.wb9).zf("");
                        st.tx(vw, R.id.tx2).tx(R.mipmap.a);
                        st.wb(vw, R.id.wb1).zf("");
                        st.xxbj(vw, R.id.xxbj1).kjd(8);
                        st.wb(vw, R.id.wb10).kjd(0);
                        st.wb(vw, R.id.wb10).zf(ur);

                    } else {

                        st.xxbj(vw, R.id.xxbjfj).kjd(8);
                        st.wb(vw, R.id.wb8).zf("");
                        st.wb(vw, R.id.wb9).zf("");
                        st.tx(vw, R.id.tx2).tx(R.mipmap.a);
                        st.wb(vw, R.id.wb1).zf(wb2);
                        st.xxbj(vw, R.id.xxbj1).kjd(0);
                        st.wb(vw, R.id.wb10).kjd(8);
                        st.wb(vw, R.id.wb10).zf("");

                    }

                } else if (f == 0) {

                    String color0 = "#00000000";

                    String color1 = "#00000000";

                    String color2 = "#00000000";
                    st.wb(vw, R.id.wb1).zfys(color1);
                    st.wb(vw, R.id.wb2).zfys(color2);
                    st.wb(vw, R.id.wb3).zfys(color2);
                    st.kp(vw, R.id.kp1).kpbjys(color0);
                    st.wb(vw, R.id.wb2).kjd(0);
                    st.xxbj(vw, R.id.xxbj1).dqfs("left");
                    st.xxbj(vw, R.id.xxbjat).kjd(8);
                    st.xdbj(vw, R.id.chat_attachment_item_root).xydj(false);
                    st.xdbj(vw, R.id.chat_attachment_item_root).xyca(false);
                    st.xxbj(vw, R.id.xxbj3).kjd(0);
                    st.wb(vw, R.id.wb21).kjd(8);
                    st.wb(vw, R.id.wb22).kjd(8);
                    st.xxbj(vw, R.id.xxbj24).kjd(8);
                    st.xxbj(vw, R.id.xxbj4k).kjd(0);

                    i.runlibrary.app.v.kp kp2 = st.kp(vw, R.id.kp2);
                    kp2.kjd(8);

                    i.runlibrary.app.v.xdbj$xdbjgz kp2_xdbjgz = (i.runlibrary.app.v.xdbj$xdbjgz) (Object) kp2.xdbjgz();
                    kp2_xdbjgz.dqfzb(true);
                    kp2_xdbjgz.dqfyb(false);
                    st.wb(vw, R.id.wb7).kjd(8);
                    st.wb(vw, R.id.wb6).zf("");
                    st.xxbj(vw, R.id.xxbjfj).kjd(8);
                    st.wb(vw, R.id.wb8).zf("");
                    st.wb(vw, R.id.wb9).zf("");
                    st.tx(vw, R.id.tx2).tx(R.mipmap.a);
                    st.wb(vw, R.id.wb1).zf("");
                    st.xxbj(vw, R.id.xxbj1).kjd(0);
                    st.wb(vw, R.id.wb10).kjd(8);
                    st.wb(vw, R.id.wb10).zf("");

                } else if (f == 1) {

                    String color0 = zf.zf(R.color.colorWBK);

                    String color1 = zf.zf(R.color.colorTextU1);

                    String color2 = zf.zf(R.color.colorTextU1);
                    st.wb(vw, R.id.wb1).zfys(color1);
                    st.wb(vw, R.id.wb2).zfys(color2);
                    st.wb(vw, R.id.wb3).zfys(color2);
                    st.kp(vw, R.id.kp1).kpbjys(color0);
                    st.wb(vw, R.id.wb2).kjd(0);
                    st.xxbj(vw, R.id.xxbj1).dqfs("left");
                    st.xxbj(vw, R.id.xxbjat).kjd(8);
                    st.xdbj(vw, R.id.chat_attachment_item_root).xydj(true);
                    st.xdbj(vw, R.id.chat_attachment_item_root).xyca(true);
                    st.xxbj(vw, R.id.xxbj3).kjd(8);
                    st.wb(vw, R.id.wb21).kjd(0);
                    st.wb(vw, R.id.wb22).kjd(8);
                    st.xxbj(vw, R.id.xxbj24).kjd(8);
                    st.xxbj(vw, R.id.xxbj4k).kjd(0);

                    i.runlibrary.app.v.kp kp2 = st.kp(vw, R.id.kp2);
                    kp2.kjd(0);

                    i.runlibrary.app.v.xdbj$xdbjgz kp2_xdbjgz = (i.runlibrary.app.v.xdbj$xdbjgz) (Object) kp2.xdbjgz();
                    kp2_xdbjgz.dqfzb(true);
                    kp2_xdbjgz.dqfyb(false);

                    String wb = st.wb(vw, R.id.wb2).zf();

                    int isVip = 8;

                    int isVip2 = 0;

                    if (zf.dy(wb, "台风眼Typheye") || zf.dy(wb, "系统消息")) {

                        isVip = 0;
                        isVip2 = 8;

                    }
                    st.wb(vw, R.id.wb7).kjd(isVip);
                    st.wb(vw, R.id.wb21).kjd(isVip2);

                    Object luid = lbsj.lbsj(pn, -3);
                    luid = zf.qctwkg(luid);

                    if (zf.dy(luid, "w0") || zf.dy(luid, "") || zf.dy(luid, null) || zf.dy(luid, "0") || zf.dy(luid, "null")) {

                        st.wb(vw, R.id.wb22).kjd(0);

                    } else {

                        st.wb(vw, R.id.wb22).kjd(8);

                    }
                    wb = zf.qc(wb, 0, 1);
                    st.wb(vw, R.id.wb6).zf(wb);

                    if (zf.dy(st.wb(vw, R.id.wb21).zf(), "未知")) {

                        st.wb(vw, R.id.wb21).kjd(8);

                    } else {

                        st.wb(vw, R.id.wb21).kjd(0);

                    }

                    String wb2 = st.wb(vw, R.id.wb1).zf();

                    if (zf.cz(wb2, "[PHOTO]") && zf.cz(wb2, "[P:END]")) {

                        String ur = zf.qc(wb2, "[PHOTO]", "[P:END]");

                        String tx = zf.qc(wb2, null, "[PHOTO]");
                        st.xxbj(vw, R.id.xxbjfj).kjd(0);
                        st.wb(vw, R.id.wb8).zf("[附件]图片");
                        st.wb(vw, R.id.wb9).zf(ur);
                        st.tx(vw, R.id.tx2).tx(R.mipmap.b11);
                        st.wb(vw, R.id.wb1).zf(tx);
                        st.xxbj(vw, R.id.xxbj1).kjd(0);
                        st.wb(vw, R.id.wb10).kjd(8);
                        st.wb(vw, R.id.wb10).zf("");

                    } else if (zf.cz(wb2, "[AUDIO]") && zf.cz(wb2, "[A:END]")) {

                        String ur = zf.qc(wb2, "[AUDIO]", "[A:END]");

                        String tx = zf.qc(wb2, null, "[AUDIO]");
                        st.xxbj(vw, R.id.xxbjfj).kjd(0);
                        st.wb(vw, R.id.wb8).zf("[附件]音频");
                        st.wb(vw, R.id.wb9).zf(ur);
                        st.tx(vw, R.id.tx2).tx(R.mipmap.b9);
                        st.wb(vw, R.id.wb1).zf(tx);
                        st.xxbj(vw, R.id.xxbj1).kjd(0);
                        st.wb(vw, R.id.wb10).kjd(8);
                        st.wb(vw, R.id.wb10).zf("");

                    } else if (zf.cz(wb2, "[VIDEO]") && zf.cz(wb2, "[V:END]")) {

                        String ur = zf.qc(wb2, "[VIDEO]", "[V:END]");

                        String tx = zf.qc(wb2, null, "[VIDEO]");
                        st.xxbj(vw, R.id.xxbjfj).kjd(0);
                        st.wb(vw, R.id.wb8).zf("[附件]视频");
                        st.wb(vw, R.id.wb9).zf(ur);
                        st.tx(vw, R.id.tx2).tx(R.mipmap.b8);
                        st.wb(vw, R.id.wb1).zf(tx);
                        st.xxbj(vw, R.id.xxbj1).kjd(0);
                        st.wb(vw, R.id.wb10).kjd(8);
                        st.wb(vw, R.id.wb10).zf("");

                    } else if (zf.cz(wb2, "[OTHER]") && zf.cz(wb2, "[O:END]")) {

                        String ur = zf.qc(wb2, "[OTHER]", "[O:END]");

                        String tx = zf.qc(wb2, null, "[OTHER]");
                        st.xxbj(vw, R.id.xxbjfj).kjd(0);
                        st.wb(vw, R.id.wb8).zf("[附件]文件");
                        st.wb(vw, R.id.wb9).zf(ur);
                        st.tx(vw, R.id.tx2).tx(R.mipmap.b14);
                        st.wb(vw, R.id.wb1).zf(tx);
                        st.xxbj(vw, R.id.xxbj1).kjd(0);
                        st.wb(vw, R.id.wb10).kjd(8);
                        st.wb(vw, R.id.wb10).zf("");

                    } else if (zf.cz(wb2, "[INFOS]") && zf.cz(wb2, "[I:END]")) {

                        String ur = zf.qc(wb2, "[INFOS]", "[I:END]");

                        String tx = zf.qc(wb2, null, "[INFOS]");
                        st.xxbj(vw, R.id.xxbjfj).kjd(8);
                        st.wb(vw, R.id.wb8).zf("");
                        st.wb(vw, R.id.wb9).zf("");
                        st.tx(vw, R.id.tx2).tx(R.mipmap.a);
                        st.wb(vw, R.id.wb1).zf("");
                        st.xxbj(vw, R.id.xxbj1).kjd(8);
                        st.wb(vw, R.id.wb10).kjd(0);
                        st.wb(vw, R.id.wb10).zf(ur);

                    } else {

                        st.xxbj(vw, R.id.xxbjfj).kjd(8);
                        st.wb(vw, R.id.wb8).zf("");
                        st.wb(vw, R.id.wb9).zf("");
                        st.tx(vw, R.id.tx2).tx(R.mipmap.a);
                        st.wb(vw, R.id.wb1).zf(wb2);
                        st.xxbj(vw, R.id.xxbj1).kjd(0);
                        st.wb(vw, R.id.wb10).kjd(8);
                        st.wb(vw, R.id.wb10).zf("");

                    }

                } else if (f == 2) {

                    String color0 = zf.zf(R.color.colorWBK);

                    String color1 = zf.zf(R.color.colorTextU1);

                    String color2 = zf.zf(R.color.colorTextU1);
                    st.wb(vw, R.id.wb1).zfys(color1);
                    st.wb(vw, R.id.wb2).zfys(color2);
                    st.wb(vw, R.id.wb3).zfys(color2);
                    st.kp(vw, R.id.kp1).kpbjys(color0);
                    st.wb(vw, R.id.wb2).kjd(0);
                    st.xxbj(vw, R.id.xxbj1).dqfs("left");
                    st.xxbj(vw, R.id.xxbjat).kjd(0);
                    st.xdbj(vw, R.id.chat_attachment_item_root).xydj(true);
                    st.xdbj(vw, R.id.chat_attachment_item_root).xyca(true);
                    st.xxbj(vw, R.id.xxbj3).kjd(8);
                    st.wb(vw, R.id.wb21).kjd(0);
                    st.wb(vw, R.id.wb22).kjd(8);
                    st.xxbj(vw, R.id.xxbj24).kjd(8);
                    st.xxbj(vw, R.id.xxbj4k).kjd(0);

                    i.runlibrary.app.v.kp kp2 = st.kp(vw, R.id.kp2);
                    kp2.kjd(0);

                    i.runlibrary.app.v.xdbj$xdbjgz kp2_xdbjgz = (i.runlibrary.app.v.xdbj$xdbjgz) (Object) kp2.xdbjgz();
                    kp2_xdbjgz.dqfzb(true);
                    kp2_xdbjgz.dqfyb(false);

                    String wb = st.wb(vw, R.id.wb2).zf();

                    int isVip = 8;

                    int isVip2 = 0;

                    if (zf.dy(wb, "台风眼Typheye") || zf.dy(wb, "系统消息")) {

                        isVip = 0;
                        isVip2 = 8;

                    }
                    st.wb(vw, R.id.wb7).kjd(isVip);
                    st.wb(vw, R.id.wb21).kjd(isVip2);

                    Object luid = lbsj.lbsj(pn, -3);
                    luid = zf.qctwkg(luid);

                    if (zf.dy(luid, "w0") || zf.dy(luid, "") || zf.dy(luid, null) || zf.dy(luid, "0") || zf.dy(luid, "null")) {

                        st.wb(vw, R.id.wb22).kjd(0);

                    } else {

                        st.wb(vw, R.id.wb22).kjd(8);

                    }
                    wb = zf.qc(wb, 0, 1);
                    st.wb(vw, R.id.wb6).zf(wb);

                    if (zf.dy(st.wb(vw, R.id.wb21).zf(), "未知")) {

                        st.wb(vw, R.id.wb21).kjd(8);

                    } else {

                        st.wb(vw, R.id.wb21).kjd(0);

                    }

                    String wb2 = st.wb(vw, R.id.wb1).zf();

                    if (zf.cz(wb2, "[PHOTO]") && zf.cz(wb2, "[P:END]")) {

                        String ur = zf.qc(wb2, "[PHOTO]", "[P:END]");

                        String tx = zf.qc(wb2, null, "[PHOTO]");
                        st.xxbj(vw, R.id.xxbjfj).kjd(0);
                        st.wb(vw, R.id.wb8).zf("[附件]图片");
                        st.wb(vw, R.id.wb9).zf(ur);
                        st.tx(vw, R.id.tx2).tx(R.mipmap.b11);
                        st.wb(vw, R.id.wb1).zf(tx);
                        st.xxbj(vw, R.id.xxbj1).kjd(0);
                        st.wb(vw, R.id.wb10).kjd(8);
                        st.wb(vw, R.id.wb10).zf("");

                    } else if (zf.cz(wb2, "[AUDIO]") && zf.cz(wb2, "[A:END]")) {

                        String ur = zf.qc(wb2, "[AUDIO]", "[A:END]");

                        String tx = zf.qc(wb2, null, "[AUDIO]");
                        st.xxbj(vw, R.id.xxbjfj).kjd(0);
                        st.wb(vw, R.id.wb8).zf("[附件]音频");
                        st.wb(vw, R.id.wb9).zf(ur);
                        st.tx(vw, R.id.tx2).tx(R.mipmap.b9);
                        st.wb(vw, R.id.wb1).zf(tx);
                        st.xxbj(vw, R.id.xxbj1).kjd(0);
                        st.wb(vw, R.id.wb10).kjd(8);
                        st.wb(vw, R.id.wb10).zf("");

                    } else if (zf.cz(wb2, "[VIDEO]") && zf.cz(wb2, "[V:END]")) {

                        String ur = zf.qc(wb2, "[VIDEO]", "[V:END]");

                        String tx = zf.qc(wb2, null, "[VIDEO]");
                        st.xxbj(vw, R.id.xxbjfj).kjd(0);
                        st.wb(vw, R.id.wb8).zf("[附件]视频");
                        st.wb(vw, R.id.wb9).zf(ur);
                        st.tx(vw, R.id.tx2).tx(R.mipmap.b8);
                        st.wb(vw, R.id.wb1).zf(tx);
                        st.xxbj(vw, R.id.xxbj1).kjd(0);
                        st.wb(vw, R.id.wb10).kjd(8);
                        st.wb(vw, R.id.wb10).zf("");

                    } else if (zf.cz(wb2, "[OTHER]") && zf.cz(wb2, "[O:END]")) {

                        String ur = zf.qc(wb2, "[OTHER]", "[O:END]");

                        String tx = zf.qc(wb2, null, "[OTHER]");
                        st.xxbj(vw, R.id.xxbjfj).kjd(0);
                        st.wb(vw, R.id.wb8).zf("[附件]文件");
                        st.wb(vw, R.id.wb9).zf(ur);
                        st.tx(vw, R.id.tx2).tx(R.mipmap.b14);
                        st.wb(vw, R.id.wb1).zf(tx);
                        st.xxbj(vw, R.id.xxbj1).kjd(0);
                        st.wb(vw, R.id.wb10).kjd(8);
                        st.wb(vw, R.id.wb10).zf("");

                    } else if (zf.cz(wb2, "[INFOS]") && zf.cz(wb2, "[I:END]")) {

                        String ur = zf.qc(wb2, "[INFOS]", "[I:END]");

                        String tx = zf.qc(wb2, null, "[INFOS]");
                        st.xxbj(vw, R.id.xxbjfj).kjd(8);
                        st.wb(vw, R.id.wb8).zf("");
                        st.wb(vw, R.id.wb9).zf("");
                        st.tx(vw, R.id.tx2).tx(R.mipmap.a);
                        st.wb(vw, R.id.wb1).zf("");
                        st.xxbj(vw, R.id.xxbj1).kjd(8);
                        st.wb(vw, R.id.wb10).kjd(0);
                        st.wb(vw, R.id.wb10).zf(ur);

                    } else {

                        st.xxbj(vw, R.id.xxbjfj).kjd(8);
                        st.wb(vw, R.id.wb8).zf("");
                        st.wb(vw, R.id.wb9).zf("");
                        st.tx(vw, R.id.tx2).tx(R.mipmap.a);
                        st.wb(vw, R.id.wb1).zf(wb2);
                        st.xxbj(vw, R.id.xxbj1).kjd(0);
                        st.wb(vw, R.id.wb10).kjd(8);
                        st.wb(vw, R.id.wb10).zf("");

                    }

                } else {

                    String color0 = zf.zf(R.color.colorWBK);

                    String color1 = zf.zf(R.color.colorTextU1);

                    String color2 = zf.zf(R.color.colorTextU1);
                    st.wb(vw, R.id.wb1).zfys(color1);
                    st.wb(vw, R.id.wb2).zfys(color2);
                    st.wb(vw, R.id.wb3).zfys(color2);
                    st.kp(vw, R.id.kp1).kpbjys(color0);
                    st.wb(vw, R.id.wb2).kjd(0);
                    st.xxbj(vw, R.id.xxbj1).dqfs("left");
                    st.xxbj(vw, R.id.xxbjat).kjd(8);
                    st.xdbj(vw, R.id.chat_attachment_item_root).xydj(true);
                    st.xdbj(vw, R.id.chat_attachment_item_root).xyca(true);
                    st.xxbj(vw, R.id.xxbj3).kjd(8);
                    st.wb(vw, R.id.wb21).kjd(8);
                    st.wb(vw, R.id.wb22).kjd(8);
                    st.xxbj(vw, R.id.xxbj24).kjd(8);
                    st.xxbj(vw, R.id.xxbj4k).kjd(8);

                    i.runlibrary.app.v.kp kp2 = st.kp(vw, R.id.kp2);
                    kp2.kjd(8);

                    i.runlibrary.app.v.xdbj$xdbjgz kp2_xdbjgz = (i.runlibrary.app.v.xdbj$xdbjgz) (Object) kp2.xdbjgz();
                    kp2_xdbjgz.dqfzb(true);
                    kp2_xdbjgz.dqfyb(false);
                    st.wb(vw, R.id.wb6).zf("");
                    st.wb(vw, R.id.wb7).kjd(8);

                    String wb2 = st.wb(vw, R.id.wb1).zf();

                    if (zf.cz(wb2, "[PHOTO]") && zf.cz(wb2, "[P:END]")) {

                        String ur = zf.qc(wb2, "[PHOTO]", "[P:END]");

                        String tx = zf.qc(wb2, null, "[PHOTO]");
                        st.xxbj(vw, R.id.xxbjfj).kjd(0);
                        st.wb(vw, R.id.wb8).zf("[附件]图片");
                        st.wb(vw, R.id.wb9).zf(ur);
                        st.tx(vw, R.id.tx2).tx(R.mipmap.b11);
                        st.wb(vw, R.id.wb1).zf(tx);
                        st.xxbj(vw, R.id.xxbj1).kjd(0);
                        st.wb(vw, R.id.wb10).kjd(8);
                        st.wb(vw, R.id.wb10).zf("");

                    } else if (zf.cz(wb2, "[AUDIO]") && zf.cz(wb2, "[A:END]")) {

                        String ur = zf.qc(wb2, "[AUDIO]", "[A:END]");

                        String tx = zf.qc(wb2, null, "[AUDIO]");
                        st.xxbj(vw, R.id.xxbjfj).kjd(0);
                        st.wb(vw, R.id.wb8).zf("[附件]音频");
                        st.wb(vw, R.id.wb9).zf(ur);
                        st.tx(vw, R.id.tx2).tx(R.mipmap.b9);
                        st.wb(vw, R.id.wb1).zf(tx);
                        st.xxbj(vw, R.id.xxbj1).kjd(0);
                        st.wb(vw, R.id.wb10).kjd(8);
                        st.wb(vw, R.id.wb10).zf("");

                    } else if (zf.cz(wb2, "[VIDEO]") && zf.cz(wb2, "[V:END]")) {

                        String ur = zf.qc(wb2, "[VIDEO]", "[V:END]");

                        String tx = zf.qc(wb2, null, "[VIDEO]");
                        st.xxbj(vw, R.id.xxbjfj).kjd(0);
                        st.wb(vw, R.id.wb8).zf("[附件]视频");
                        st.wb(vw, R.id.wb9).zf(ur);
                        st.tx(vw, R.id.tx2).tx(R.mipmap.b8);
                        st.wb(vw, R.id.wb1).zf(tx);
                        st.xxbj(vw, R.id.xxbj1).kjd(0);
                        st.wb(vw, R.id.wb10).kjd(8);
                        st.wb(vw, R.id.wb10).zf("");

                    } else if (zf.cz(wb2, "[OTHER]") && zf.cz(wb2, "[O:END]")) {

                        String ur = zf.qc(wb2, "[OTHER]", "[O:END]");

                        String tx = zf.qc(wb2, null, "[OTHER]");
                        st.xxbj(vw, R.id.xxbjfj).kjd(0);
                        st.wb(vw, R.id.wb8).zf("[附件]文件");
                        st.wb(vw, R.id.wb9).zf(ur);
                        st.tx(vw, R.id.tx2).tx(R.mipmap.b14);
                        st.wb(vw, R.id.wb1).zf(tx);
                        st.xxbj(vw, R.id.xxbj1).kjd(0);
                        st.wb(vw, R.id.wb10).kjd(8);
                        st.wb(vw, R.id.wb10).zf("");

                    } else if (zf.cz(wb2, "[INFOS]") && zf.cz(wb2, "[I:END]")) {

                        String ur = zf.qc(wb2, "[INFOS]", "[I:END]");

                        String tx = zf.qc(wb2, null, "[INFOS]");
                        st.xxbj(vw, R.id.xxbjfj).kjd(8);
                        st.wb(vw, R.id.wb8).zf("");
                        st.wb(vw, R.id.wb9).zf("");
                        st.tx(vw, R.id.tx2).tx(R.mipmap.a);
                        st.wb(vw, R.id.wb1).zf("");
                        st.xxbj(vw, R.id.xxbj1).kjd(8);
                        st.wb(vw, R.id.wb10).kjd(0);
                        st.wb(vw, R.id.wb10).zf(ur);

                    } else {

                        st.xxbj(vw, R.id.xxbjfj).kjd(8);
                        st.wb(vw, R.id.wb8).zf("");
                        st.wb(vw, R.id.wb9).zf("");
                        st.tx(vw, R.id.tx2).tx(R.mipmap.a);
                        st.wb(vw, R.id.wb1).zf(wb2);
                        st.xxbj(vw, R.id.xxbj1).kjd(0);
                        st.wb(vw, R.id.wb10).kjd(8);
                        st.wb(vw, R.id.wb10).zf("");

                    }

                }

            }

        });

    }

    public void csh() {
        e11.setup_talklist();
        sjk = (i.runlibrary.app.sj$sjk) (Object) sj.sjk(set17);
        wllbcdlb = st.v7lb(R.id.wllbv7lb2);
        wllbcdspq = IappCompat.v7lbAdapter(wllbcdlb, WearChatMenuItemActivity.class, R.layout.wear_chat_menu_item, new IappCompat.V7lbViewBinder() {

            public void bind(Object ua, int pn, View vw) {

                i.runlibrary.app.sj$lb lbsj = (i.runlibrary.app.sj$lb) (Object) sj.lb(vw);

                final int f = IappCompat.zh(sj.zh(lbsj.lbsj(pn, -1))).zint();

                if (f == 1) {

                    String color = zf.zf(R.color.colorTextFalse);
                    st.wb(vw, R.id.wb1).zfys(color);
                    st.xxbj(vw, R.id.xxbj1).kjd(0);
                    st.xxbj(vw, R.id.xxbj1).xydj(true);
                    st.xxbj(vw, R.id.xxbj1).xyca(true);
                    st.wb(vw, R.id.dp20).kjd(8);

                } else if (f == -1) {

                    String color = zf.zf(R.color.colorTextU1);
                    st.wb(vw, R.id.wb1).zfys(color);
                    st.xxbj(vw, R.id.xxbj1).kjd(8);
                    st.xxbj(vw, R.id.xxbj1).xydj(false);
                    st.xxbj(vw, R.id.xxbj1).xyca(false);
                    st.wb(vw, R.id.dp20).kjd(0);

                } else {

                    String color = zf.zf(R.color.colorTextU1);
                    st.wb(vw, R.id.wb1).zfys(color);
                    st.xxbj(vw, R.id.xxbj1).kjd(0);
                    st.xxbj(vw, R.id.xxbj1).xydj(true);
                    st.xxbj(vw, R.id.xxbj1).xyca(true);
                    st.wb(vw, R.id.dp20).kjd(8);

                }

            }

        });
        lb2 = st.v7lb(R.id.wllbv7lb1);
        spq2 = IappCompat.v7lbAdapter(lb2, ChatRoomListItemActivity.class, R.layout.chat_room_list_item, new IappCompat.V7lbViewBinder() {

            public void bind(Object ua, int pn, View vw) {

            }

        });

    }

    public void ltsview(int a) {

        final int i = a;

        final i.runlibrary.app.v.xxbj root = st.xxbj(R.id.i2xxbj1);

        final i.runlibrary.app.v.xxbj load = st.xxbj(R.id.ltssxxxbj3);

        final i.runlibrary.app.v.xxbj cd = st.xxbj(R.id.xxbjcd);

        final i.runlibrary.app.v.xxbj add = st.xxbj(R.id.xxbj4);

        final i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(R.id.qtgd1);
        gj.jmxc(new Runnable() {

            public void run() {

                if (i == 0) {

                    root.kjd(0);
                    load.kjd(8);
                    cd.kjd(8);
                    add.kjd(8);
                    lb3.v.requestFocus();

                } else if (i == 1) {

                    cd.kjd(8);
                    add.kjd(8);

                } else if (i == 2) {

                    root.kjd(8);
                    load.kjd(8);
                    cd.kjd(0);
                    add.kjd(8);

                } else if (i == 3) {

                    root.kjd(8);
                    load.kjd(8);
                    cd.kjd(8);
                    add.kjd(0);
                    qtgd1.v.requestFocus();

                } else if (i == 4) {

                    root.kjd(8);
                    load.kjd(0);
                    cd.kjd(8);
                    add.kjd(8);

                }

            }

        });

    }

    public void wllbview(int a) {

        final int i = a;

        final i.runlibrary.app.v.xxbj root = st.xxbj(R.id.xxbj1);

        final i.runlibrary.app.v.xxbj load = st.xxbj(R.id.xxbj3);

        final i.runlibrary.app.v.xxbj cd = st.xxbj(R.id.xxbj2);

        final i.runlibrary.app.v.xxbj add = st.xxbj(R.id.xxbj4);

        final i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(R.id.qtgd1);
        gj.jmxc(new Runnable() {

            public void run() {

                if (i == 0) {

                    root.kjd(0);
                    load.kjd(8);
                    cd.kjd(8);
                    add.kjd(8);

                } else if (i == 1) {

                    root.kjd(8);
                    load.kjd(0);
                    cd.kjd(8);
                    add.kjd(8);

                } else if (i == 2) {

                    root.kjd(8);
                    load.kjd(8);
                    cd.kjd(0);
                    add.kjd(8);

                } else if (i == 3) {

                    root.kjd(8);
                    load.kjd(8);
                    cd.kjd(8);
                    add.kjd(0);

                }
                qtgd1.v.requestFocus();

            }

        });

    }

    public void online() {

        if (!state_online) {

            state_online = true;
            gj.xc(new Thread() {

                public void run() {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            st.wb(R.id.wb24).zf("Online: Loading...");

                        }

                    });

                    String text = "";

                    String urls = AppPaths.appPath("settings/f10/set2.inf");
                    urls = wj.dqwb(urls);
                    urls = e1.urlUnlockString(urls);

                    if (zf.cjw(urls, "/")) {

                    } else {

                        urls = urls + "/";

                    }

                    String url = urls + "online.php";

                    String st5 = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);
                    st5 = zf.qctwkg(st5);

                    if (zf.dy(st5, "null") || zf.dy(st5, "") || zf.dy(st5, null)) {

                        text = "Online: unknow";

                    } else {

                        text = st5;

                    }

                    final String text_ = text;
                    gj.jmxc(new Runnable() {

                        public void run() {

                            st.wb(R.id.wb24).zf(text_);

                        }

                    });
                    state_online = false;

                }

            });

        }

    }

    public void wllbcd(int i) {

        if (i == 0) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf("< 腕上微聊");
                    wllbview(0);

                }

            });

        } else if (i == 4) {

            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf("添加房间");
                    st.bjk(R.id.bjk1).zf("");
                    wllbview(3);
                    bjkmode = 1;

                }

            });

        } else if (i == 5) {

            String cachehd1 = AppPaths.appPath("data/ltlb/lbx_title");

            final String bz = wj.dqwb(cachehd1);
            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf("房间备注");
                    st.bjk(R.id.bjk1).zf(bz);
                    wllbview(3);
                    bjkmode = 2;

                }

            });

        } else {

            wllbview(2);
            wllbcdspq.sc();

            String set64 = AppPaths.appPath("settings/i1/set1.inf");

            int[] kj = new int[]{R.id.wb1, R.id.wb2, R.id.wb3, R.id.wb4, R.id.wb5, -1};

            Object[] jk = new Object[]{"", "", "", "", "", 0};

            if (i == 1) {

                jk = new Object[]{"刷新", "", "", "", "", 0};
                wllbcdspq.j(kj, jk);
                jk = new Object[]{"添加房间", "", "", "", "", 0};
                wllbcdspq.j(kj, jk);
                jk = new Object[]{"新建房间", "", "", "", "", 0};
                wllbcdspq.j(kj, jk);

                if (zf.dy(wj.dqwb(set64), "true")) {

                    jk = new Object[]{"关闭Online", "", "", "", "", 0};
                    wllbcdspq.j(kj, jk);

                } else {

                    jk = new Object[]{"开启Online", "", "", "", "", 0};
                    wllbcdspq.j(kj, jk);

                }
                jk = new Object[]{"拒绝《公约》", "", "", "", "", 0};
                wllbcdspq.j(kj, jk);
                jk = new Object[]{"设置", "", "", "", "", 0};
                wllbcdspq.j(kj, jk);
                jk = new Object[]{"退出", "", "", "", "", 0};
                wllbcdspq.j(kj, jk);

            } else if (i == 2) {

                jk = new Object[]{"房间信息", "", "", "", "", 0};
                wllbcdspq.j(kj, jk);
                jk = new Object[]{"备注房间", "", "", "", "", 0};
                wllbcdspq.j(kj, jk);
                jk = new Object[]{"移除房间", "", "", "", "", 0};
                wllbcdspq.j(kj, jk);
                jk = new Object[]{"清除聊天记录", "", "", "", "", 0};
                wllbcdspq.j(kj, jk);

            }
            jk = new Object[]{"返回", "back", "", "", "", 1};
            wllbcdspq.j(kj, jk);

            String spxx = AppPaths.appPath("settings/a3.inf");
            jk = new Object[]{"", "", "", "", "", -1};
            wllbcdspq.j(kj, jk);

            String axc = wj.dqwb(spxx);

            if (zf.dy(axc, "1") || zf.dy(axc, "3")) {

                jk = new Object[]{"", "", "", "", "", -1};
                wllbcdspq.j(kj, jk);
                jk = new Object[]{"", "", "", "", "", -1};
                wllbcdspq.j(kj, jk);

            }
            gj.jmxc(new Runnable() {

                public void run() {

                    wllbcdspq.sx();
                    st.v7lb(R.id.wllbv7lb2).xzwz("top");
                    st.wb(R.id.title_bar).zf("菜单");

                }

            });

        }

    }

    public void dj_wllbcd(Object a, Object b, Object c, Object d) {

        if (zf.dy(a, "返回")) {

            wllbcd(0);

        } else if (zf.dy(a, "备注房间")) {

            String cachehd = AppPaths.appPath("data/ltlb/lbx_id");

            String id = wj.dqwb(cachehd);
            st.bjk(R.id.bjk1).zf("");
            st.bjk(R.id.bjk1).tszf("请输入备注");
            st.bjk(R.id.bjk1).wblx("text");
            wllbcd(5);

        } else if (zf.dy(a, "房间信息")) {

            String cachehd1 = AppPaths.appPath("data/ltlb/lbx_title");

            String bz = wj.dqwb(cachehd1);

            String cachehd = AppPaths.appPath("data/ltlb/lbx_id");

            String id = wj.dqwb(cachehd);

            String cachehd3 = AppPaths.appPath("data/ltlb/lbx_info");

            String info = wj.dqwb(cachehd3);
            info = zf.th(info, id + "-", "");
            e1.tsk("房间信息", "[房间备注]\n" + bz + "\n\n" + "[房间名称]\n" + info + "\n\n" + "[房间标识]\n" + id);
            wllbcd(0);

        } else if (zf.dy(a, "刷新")) {

            wllbcd(0);
            wllbsx();

        } else if (zf.dy(a, "添加房间")) {

            st.bjk(R.id.bjk1).zf("");
            st.bjk(R.id.bjk1).tszf("请输入房间ID");
            st.bjk(R.id.bjk1).wblx("number");
            wllbcd(4);

        } else if (zf.dy(a, "新建房间")) {

            e1.goplu("20002", "新建房间", false);
            wllbcd(0);

        } else if (zf.dy(a, "设置")) {

            wllbcd(0);
            gj.tz(WearChatSettingsActivity.class);

        } else if (zf.dy(a, "关闭Online")) {

            String set64 = AppPaths.appPath("settings/i1/set1.inf");
            wj.xrwb(set64, "false");
            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.wb24).kjd(8);

                }

            });
            wllbcd(0);

        } else if (zf.dy(a, "开启Online")) {

            String set64 = AppPaths.appPath("settings/i1/set1.inf");
            wj.xrwb(set64, "true");
            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.wb24).kjd(0);

                }

            });
            wllbcd(0);

        } else if (zf.dy(a, "拒绝《公约》")) {

            wllbcd(0);
            e1.awgwl_false();
            gj.gb();

        } else if (zf.dy(a, "移除房间")) {

            wllbcd(0);

            String nr = "";

            String cachehd = AppPaths.appPath("data/ltlb/lbx_id");

            String id = wj.dqwb(cachehd);

            String sjhc = AppPaths.appPath("data/ltlb/chat/") + id;

            int u = e11.findid_talklist(id);

            String uss = String.valueOf(u);

            boolean b1 = e11.delete_talklist(uss);
            wj.sc(sjhc);

            if (b1) {

                nr = "移除成功";

            } else {

                nr = "移除失败";

            }
            wllbcd(0);
            wllbsx();
            e1.tsk("提示", nr);

        } else if (zf.dy(a, "清除聊天记录")) {

            wllbcd(0);

            String nr = "";

            String cachehd = AppPaths.appPath("data/ltlb/lbx_id");

            String id = wj.dqwb(cachehd);

            String sjhc = AppPaths.appPath("data/ltlb/chat/") + id;

            boolean b1 = (wj.sc(sjhc) > 0);

            if (b1) {

                nr = "清除成功";

            } else {

                nr = "清除失败";

            }
            e1.tsk("提示", nr);
            wllbcd(0);

        } else if (zf.dy(a, "退出")) {

            wllbcd(0);
            gj.gb();

        } else {

        }

    }

    public void wllbsx() {

        final String set4 = AppPaths.appPath("settings/f11/set4.inf");

        final String code = wj.dqwb(set4);

        final String set1 = AppPaths.appPath("settings/f11/set1.inf");
        gj.xc(new Thread() {

            public void run() {

                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.title_bar).zf("< 腕上微聊");
                        wllbview(1);

                    }

                });
                online();

                final int[] kj = new int[]{R.id.wb1, R.id.wb2, R.id.wb3};

                String[] jk = new String[]{"", "", ""};
                spq2.sc();

                if (zf.cz(code, "Admin:true")) {

                } else {

                    wllbcd(0);

                    String[] uids = new String[]{"110", "404", "600", "405"};

                    for (String uid : uids) {

                        String sjhc = AppPaths.appPath("data/ltlb/chat/") + uid;
                        wj.sc(AppPaths.appPath("data/ltlb/showing"));

                        int u = e11.findid_talklist(uid);

                        if (u != 0) {

                            String uss = String.valueOf(u);

                            boolean b1 = e11.delete_talklist(uss);
                            wj.sc(sjhc);

                        }

                    }

                }

                String table = "_id,uid,name,utext";

                Object sqlx = null;

                i.runlibrary.app.sj$cxsj sjk15 = (i.runlibrary.app.sj$cxsj) (Object) sjk.cxsj("talklist", table, sqlx);

                while (sjk15.xyh()) {

                    jk = new String[]{sjk15.sj(2), sjk15.sj(1), sjk15.sj(3)};
                    spq2.j(kj, jk);

                }
                gj.jmxc(new Runnable() {

                    public void run() {

                        spq2.sx();
                        wllbview(0);

                    }

                });

            }

        });

    }

    public void bjsj() {

        final String ltsjhc = AppPaths.appPath("data/ltlb/chat/");

        final String set2 = AppPaths.appPath("settings/f11/set2.inf");

        final String set4 = AppPaths.appPath("settings/f11/set4.inf");

        final String code = wj.dqwb(set4);

        String bjknr = st.bjk(R.id.bjk1).zf();
        bjknr = zf.qctwkg(bjknr);

        final String bjknrs = bjknr;

        if (zf.dy(bjknrs, "")) {

            if (bjkmode == 2) {

                e1.tsk("提示", "备注不能为空");

            } else if (bjkmode == 1) {

                e1.tsk("提示", "房间ID不能为空");

            }

        } else {

            if (bjkmode == 2) {

                String cachehd = AppPaths.appPath("data/ltlb/lbx_id");

                final String id = wj.dqwb(cachehd);
                gj.xc(new Thread() {

                    public void run() {

                        String nr = "";
                        wllbview(1);

                        int u = e11.findid_talklist(id);

                        String uss = String.valueOf(u);

                        boolean b1 = e11.update_talklist(uss, "name", bjknrs);

                        if (b1) {

                            nr = "备注修改成功";

                        } else {

                            nr = "备注修改失败";

                        }
                        wllbsx();
                        e1.tsk("提示", nr);

                    }

                });

            } else if (bjkmode == 1) {

                gj.xc(new Thread() {

                    public void run() {

                        wllbview(1);

                        String nr = "";

                        i.runlibrary.app.zf$zzbds zz = (i.runlibrary.app.zf$zzbds) (Object) zf.zzbds(bjknrs, "[0-9]*$", 0);

                        boolean b2 = zz.sfppcg();

                        if (b2) {

                            try {

                                String url = e1.urlUnlockString(wj.dqwb(set2));

                                if (zf.cjw(url, "/")) {

                                } else {

                                    url = url + "/";

                                }

                                String idget = url + "chat/" + bjknrs + ".json";

                                String st5 = wl.hq(idget, null, "utf-8", null, true, null, 20000, 20000, null);

                                if (zf.dy(st5, null)) {

                                    nr = "添加失败";

                                } else {

                                    if (zf.dy(bjknrs, "110") || zf.dy(bjknrs, "404") || zf.dy(bjknrs, "600") || zf.dy(bjknrs, "405")) {

                                        if (zf.cz(code, "Admin:true")) {

                                            String b4 = e1.readJson(st5, "room");
                                            b4 = b4;

                                            boolean b3 = e11.add_talklist(bjknrs, b4, bjknrs + "-" + b4);

                                            if (b3) {

                                                nr = "添加成功";
                                                wj.xrwb(ltsjhc + bjknrs, st5);

                                            } else {

                                                nr = "添加失败";

                                            }

                                        } else {

                                            nr = "添加失败";

                                        }

                                    } else {

                                        String b4 = e1.readJson(st5, "room");
                                        b4 = b4;

                                        boolean b3 = e11.add_talklist(bjknrs, b4, bjknrs + "-" + b4);

                                        if (b3) {

                                            nr = "添加成功";
                                            wj.xrwb(ltsjhc + bjknrs, st5);

                                        } else {

                                            nr = "添加失败";

                                        }

                                    }

                                }

                            } catch (Throwable e) {

                                nr = "添加失败";

                            }
                            wllbview(0);
                            wllbsx();
                            e1.tsk("提示", nr);

                        } else {

                            wllbview(3);
                            e1.tsk("提示", "房间ID仅为纯数字");

                        }

                    }

                });

            } else {

                wllbcd(0);

            }

        }

    }

    public void ltstobottom() {
        gj.jmxc(new Runnable() {

            public void run() {

                lb3.st.scrollToPosition(spq3.zs() - 1);

            }

        });

    }

    public void ltstotop() {
        gj.jmxc(new Runnable() {

            public void run() {

                lb3.st.scrollToPosition(0);

            }

        });

    }

    public void ltsgx() {

        final String uid = wj.dqwb(AppPaths.appPath("data/ltlb/lbx_id"));

        final String sjhc = AppPaths.appPath("data/ltlb/chat/") + uid;

        if (!wj.cz(sjhc) || zf.dy(wj.dqwb(sjhc), "")) {

            ltsgx2();

        } else {

            ltsgx1(false);

        }

    }

    public void ltsgx1(boolean f_) {

        if (!state_ltsgx1) {

            state_ltsgx1 = true;

            final boolean ffs = f_;

            final String userid = AppPaths.appPath("settings/account/user");

            final String uid = wj.dqwb(AppPaths.appPath("data/ltlb/lbx_id"));

            String cachehd = AppPaths.appPath("data/ltlb/lbx_title");

            final String name = wj.dqwb(cachehd);

            final String sjhc = AppPaths.appPath("data/ltlb/chat/") + uid;
            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf(getTabName(name));

                }

            });

            final String set3 = AppPaths.appPath("settings/f11/set3.inf");

            final String namer = wj.dqwb(set3);

            String urls = "";

            final String set2 = AppPaths.appPath("settings/f11/set2.inf");
            urls = e1.urlUnlockString(wj.dqwb(set2));

            if (zf.cjw(urls, "/")) {

            } else {

                urls = urls + "/";

            }

            String idget = urls + "chat/" + uid + ".json";

            final String url = idget;

            final String lable = "";
            gj.xc(new Thread() {

                public void run() {

                    gj.jmxc(new Runnable() {

                        public void run() {

                        }

                    });

                    if (!ffs) {

                        ltsview(1);

                    }

                    int[] kj = new int[]{R.id.wb1, R.id.wb2, R.id.wb3, R.id.wb21, -1, -2, -3};

                    Object[] jk = new Object[]{"", "", "", "", 0, "", ""};

                    String setback = "";

                    String data = "";

                    String name = "";

                    String date = "";

                    String luid = "";

                    String address = "";

                    String back = wj.dqwb(sjhc);

                    try {

                        String spxx = AppPaths.appPath("settings/a3.inf");

                        String axc = wj.dqwb(spxx);

                        i.runlibrary.app.zf$json jo = (i.runlibrary.app.zf$json) (Object) zf.json(back);

                        JSONObject json = jo.json;

                        JSONArray list = jo.dxlb(json, "data");

                        int i = jo.cd(list);

                        int size = 0;

                        ArrayList arr = new ArrayList();

                        while (size < i) {

                            JSONObject dx = jo.dx(list, size);
                            size++;
                            data = String.valueOf(jo.hq(dx, "data"));
                            name = String.valueOf(jo.hq(dx, "name"));
                            date = String.valueOf(jo.hq(dx, "date"));
                            luid = String.valueOf(jo.hq(dx, "uid"));
                            address = String.valueOf(jo.hq(dx, "address"));
                            address = zf.qctwkg(address);

                            if (zf.dy(address, "null") || zf.dy(address, "")) {

                                address = "未知";

                            }

                            String jname = "";

                            String jdata = "";
                            jname = name;
                            jdata = data;

                            if (!zf.dy(luid, "0") && wj.cz(userid) && !zf.dy(wj.dqwb(userid), "") && zf.dy(luid, wj.dqwb(userid))) {

                                jk = new Object[]{jdata, jname, date, address, -1, address, luid};

                            } else {

                                String at = "@" + namer + " ";

                                String at_ = zf.qctwkg(at);

                                if (zf.cz(jdata, at)) {

                                    String ats = "@我 ";
                                    jdata = zf.th(jdata, at, ats);
                                    jk = new Object[]{jdata, jname, date, address, 2, address, luid};

                                } else if (zf.dy(jdata, at_)) {

                                    String ats = "@我";
                                    jdata = zf.th(jdata, at_, ats);
                                    jk = new Object[]{jdata, jname, date, address, 2, address, luid};

                                } else {

                                    jk = new Object[]{jdata, jname, date, address, 1, address, luid};

                                }

                            }

                            HashMap hs = new HashMap();

                            for (int ii = 0; ii < kj.length; ii++) {

                                hs.put(kj[ii], jk[ii]);

                            }
                            arr.add(hs);

                        }

                        HashMap hs = new HashMap();
                        hs.put(kj[0], "");
                        hs.put(kj[1], "");
                        hs.put(kj[2], "");
                        hs.put(kj[3], "");
                        hs.put(kj[4], 0);
                        hs.put(kj[5], "");
                        hs.put(kj[6], "");
                        arr.add(hs);
                        arr.add(hs);
                        spq3.list_S = arr;

                    } catch (Throwable e) {

                        setback = "0";

                    }

                    if (zf.dy(back, "")) {

                        if (!ffs) {

                            ltsview(0);
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    st.kp(R.id.kp7).kjd(0);

                                }

                            });

                        }
                        e1.tsk("提示", "加载失败");
                        ltjmshow("加载失败,请重试", false);

                    } else {

                        if (csh_once) {

                            csh_once = false;

                            if (!ffs) {

                                ltstobottom();

                            }

                        }

                        if (!ffs) {

                            ltsview(0);
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    st.kp(R.id.kp7).kjd(0);

                                }

                            });

                        }

                        try {

                            gj.jmxc(new Runnable() {

                                public void run() {

                                    spq3.sx();

                                }

                            });

                        } catch (Throwable e) {

                            e1.upload_error(e, "e10.ltsgx1()");

                        }
                        ltjmshowgb();

                    }
                    wj.sc(AppPaths.appPath("data/ltlb/disTouch"));
                    state_ltsgx1 = false;

                }

            });

        } else {

        }

    }

    public void ltsgx2() {

        if (!state_ltsgx2) {

            state_ltsgx2 = true;

            final String uid = wj.dqwb(AppPaths.appPath("data/ltlb/lbx_id"));

            final String sjhc = AppPaths.appPath("data/ltlb/chat/") + uid;

            final String set4 = AppPaths.appPath("settings/f11/set4.inf");

            final String code = wj.dqwb(set4);

            String urls = "";

            final String set2 = AppPaths.appPath("settings/f11/set2.inf");
            urls = e1.urlUnlockString(wj.dqwb(set2));

            if (zf.cjw(urls, "/")) {

            } else {

                urls = urls + "/";

            }

            final String urls_ = urls;

            String idget = urls + "chat/" + uid + ".json";

            final String url = idget;

            final String lable = "";
            gj.xc(new Thread() {

                public void run() {

                    boolean dontOff = false;
                    wj.xrwb(AppPaths.appPath("data/ltlb/disTouch"), "0");

                    String db_ = "";

                    if (wj.cz(sjhc)) {

                        db_ = String.valueOf(wj.dx(sjhc));

                    } else {

                        db_ = "0";

                    }

                    String ljf = wj.dqwb(AppPaths.appPath("data/assets/a17"));

                    String url_ = urls_ + "re.php?uid=" + uid + ljf + "token=" + db_;

                    String back = wl.hq(url_, null, "utf-8", null, true, null, 20000, 20000, null);

                    if (zf.dy(back, "") || zf.dy(back, null)) {

                        ltjmshow("连接服务器失败,请检查网络连接", false);

                    } else {

                        if (zf.dy(back, "error")) {

                            String sjhc = AppPaths.appPath("data/ltlb/chat/") + uid;
                            wj.sc(AppPaths.appPath("data/ltlb/showing"));

                            int u = e11.findid_talklist(uid);

                            if (u != 0) {

                                String uss = String.valueOf(u);

                                boolean b1 = e11.delete_talklist(uss);
                                wj.sc(sjhc);
                                e1.tsk("提示", "该房间不存在或已被删除\n已为您移除该房间");
                                gj.gb();

                            }

                        } else {

                            if (!wj.cz(sjhc)) {

                                String back2 = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                                if (!zf.dy(back2, "") || !zf.dy(back2, null)) {

                                    if (st.xxbj(R.id.xxbj4).kjd() == 8 && st.xxbj(R.id.xxbjcd).kjd() == 8) {

                                        wj.xrwb(sjhc, back2);
                                        ltsgx1(false);
                                        ltjmshowgb();
                                        dontOff = true;

                                    }

                                } else {

                                    ltjmshow("刷新失败,请重试", false);
                                    ltsview(0);

                                }

                            } else {

                                String db = wj.dqwb(sjhc);

                                if (zf.dy(back, db)) {

                                } else {

                                    if (zf.dy(back, "true")) {

                                        String back2 = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                                        if (!zf.dy(back2, "") || !zf.dy(back2, null)) {

                                            if (st.xxbj(R.id.xxbj4).kjd() == 8 && st.xxbj(R.id.xxbjcd).kjd() == 8) {

                                                wj.xrwb(sjhc, back2);
                                                ltsgx1(false);
                                                ltjmshowgb();
                                                dontOff = true;

                                            }

                                        } else {

                                            ltjmshow("刷新失败,请重试", false);
                                            ltsview(0);

                                        }

                                    } else {

                                        ltjmshowgb();

                                    }

                                }

                            }

                        }

                    }

                    if (st.xxbj(R.id.i2xxbj1).kjd() == 0 && st.xxbj(R.id.xxbjcd).kjd() == 8 && !dontOff) {

                        gj.jmxc(new Runnable() {

                            public void run() {

                                ltsview(0);
                                st.kp(R.id.kp7).kjd(0);

                            }

                        });

                    }
                    wj.sc(AppPaths.appPath("data/ltlb/disTouch"));
                    state_ltsgx2 = false;

                }

            });

        } else {

        }

    }

    public void ltsgx3() {

        if (!state_ltsgx3) {

            state_ltsgx3 = true;

            final String uid = wj.dqwb(AppPaths.appPath("data/ltlb/lbx_id"));

            final String sjhc = AppPaths.appPath("data/ltlb/chat/") + uid;

            final String set4 = AppPaths.appPath("settings/f11/set4.inf");

            final String code = wj.dqwb(set4);

            String urls = "";

            final String set2 = AppPaths.appPath("settings/f11/set2.inf");
            urls = e1.urlUnlockString(wj.dqwb(set2));

            if (zf.cjw(urls, "/")) {

            } else {

                urls = urls + "/";

            }

            final String urls_ = urls;

            String idget = urls + "chat/" + uid + ".json";

            final String url = idget;

            final String lable = "";
            gj.xc(new Thread() {

                public void run() {

                    gj.jmxc(new Runnable() {

                        public void run() {

                        }

                    });
                    ltsview(1);
                    wj.xrwb(AppPaths.appPath("data/ltlb/disTouch"), "0");

                    String db_ = "";

                    if (wj.cz(sjhc)) {

                        db_ = String.valueOf(wj.dx(sjhc));

                    } else {

                        db_ = "0";

                    }

                    String ljf = wj.dqwb(AppPaths.appPath("data/assets/a17"));

                    String url_ = urls_ + "re.php?uid=" + uid + ljf + "token=" + db_;

                    String back = wl.hq(url_, null, "utf-8", null, true, null, 20000, 20000, null);

                    if (zf.dy(back, "") || zf.dy(back, null)) {

                        ltjmshow("连接服务器失败,请检查网络连接", false);
                        gj.zt(750);
                        ltsview(0);
                        gj.jmxc(new Runnable() {

                            public void run() {

                                st.kp(R.id.kp7).kjd(0);

                            }

                        });

                    } else {

                        if (zf.dy(back, "error")) {

                            ltjmshow("刷新失败,房间不存在或已被删除", false);
                            ltsview(0);

                        } else {

                            if (!wj.cz(sjhc)) {

                                String back2 = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                                if (!zf.dy(back2, "") || !zf.dy(back2, null)) {

                                    if (st.xxbj(R.id.xxbj4).kjd() == 8 && st.xxbj(R.id.xxbjcd).kjd() == 8) {

                                        wj.xrwb(sjhc, back2);
                                        ltsgx1(false);

                                    }

                                } else {

                                    ltjmshow("刷新失败,请重试", false);
                                    ltsgx1(false);

                                }

                            } else {

                                String db = wj.dqwb(sjhc);

                                if (zf.dy(back, db)) {

                                } else {

                                    if (zf.dy(back, "true")) {

                                        String back2 = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                                        if (!zf.dy(back2, "") || !zf.dy(back2, null)) {

                                            if (st.xxbj(R.id.xxbj4).kjd() == 8 && st.xxbj(R.id.xxbjcd).kjd() == 8) {

                                                wj.xrwb(sjhc, back2);
                                                ltsgx1(false);

                                            }

                                        } else {

                                            ltjmshow("刷新失败,请重试", false);
                                            ltsgx1(false);

                                        }

                                    } else {

                                        ltjmshowgb();
                                        ltsgx1(false);

                                    }

                                }

                            }

                        }

                    }

                    if (st.xxbj(R.id.ltssxxxbj3).kjd() == 8 && st.xxbj(R.id.xxbj4).kjd() == 8 && st.xxbj(R.id.xxbjcd).kjd() == 8) {

                        ltsview(0);
                        gj.jmxc(new Runnable() {

                            public void run() {

                                st.kp(R.id.kp7).kjd(0);

                            }

                        });

                    }
                    wj.sc(AppPaths.appPath("data/ltlb/disTouch"));
                    state_ltsgx3 = false;

                }

            });

        } else {

        }

    }

    public void ltspost(int i) {

        String cachehd = AppPaths.appPath("data/ltlb/lbx_title");

        final String name = wj.dqwb(cachehd);

        if (i == 0) {

            ltsview(0);
            gj.jmxc(new Runnable() {

                public void run() {

                    st.wb(R.id.title_bar).zf(getTabName(name));

                }

            });

        } else {

            if (!e1.islogin()) {

                gj.jmxc(new Runnable() {

                    public void run() {

                        gj.tz(TypheyeAccountActivity.class);
                        e1.tsk("提示", "发布消息需登录Typheye账户");

                    }

                });

            } else {

                ltsview(3);
                gj.jmxc(new Runnable() {

                    public void run() {

                        String path = AppPaths.appPath("cache/chat/upload/path");
                        wj.sc(path);
                        st.wb(R.id.title_bar).zf("发送信息");
                        st.bjk(R.id.bjk1).tszf("请输入信息");
                        st.bjk(R.id.bjk1).zf("");
                        st.an(R.id.an_upload).tszf("选择文件");
                        st.an(R.id.an_upload).kjd(0);
                        st.an(R.id.an_disupload).kjd(8);
                        st.xlcd(R.id.ltsup_xlcd1).st.setEnabled(true);
                        st.xlcd(R.id.ltsup_xlcd2).st.setEnabled(true);
                        st.xlcd(R.id.ltsup_xlcd1).kjd(0);
                        st.xlcd(R.id.ltsup_xlcd2).kjd(0);
                        st.dx(R.id.dx_chat_upload).st.setEnabled(true);
                        st.dx(R.id.dx_chat_upload).xzzt(false);

                    }

                });

            }

        }

    }

    public void ltspostat(Object nam) {

        if (!e1.islogin()) {

            gj.jmxc(new Runnable() {

                public void run() {

                    gj.tz(TypheyeAccountActivity.class);
                    e1.tsk("提示", "发布消息需登录腕管Pro账户");

                }

            });

        } else {

            final String name = "@" + nam + " ";
            ltsview(3);
            gj.jmxc(new Runnable() {

                public void run() {

                    String path = AppPaths.appPath("cache/chat/upload/path");
                    wj.sc(path);
                    st.wb(R.id.title_bar).zf("发送信息");
                    st.bjk(R.id.bjk1).tszf("请输入信息");
                    st.bjk(R.id.bjk1).zf(name);
                    st.bjk(R.id.bjk1).st.setSelection(name.length());
                    st.an(R.id.an_upload).tszf("选择文件");
                    st.an(R.id.an_upload).kjd(0);
                    st.an(R.id.an_disupload).kjd(8);
                    st.xlcd(R.id.ltsup_xlcd1).st.setEnabled(true);
                    st.xlcd(R.id.ltsup_xlcd2).st.setEnabled(true);
                    st.xlcd(R.id.ltsup_xlcd1).kjd(0);
                    st.xlcd(R.id.ltsup_xlcd2).kjd(0);
                    st.dx(R.id.dx_chat_upload).st.setEnabled(true);
                    st.dx(R.id.dx_chat_upload).xzzt(false);

                }

            });

        }

    }

    public void post() {

        if (!state_post) {

            state_post = true;

            if (!e1.islogin()) {

                gj.jmxc(new Runnable() {

                    public void run() {

                        gj.gb();
                        gj.tz(TypheyeAccountActivity.class);
                        e1.tsk("提示", "发布消息需登录腕管Pro账户");
                        state_post = false;

                    }

                });

            } else {

                String xxs = st.bjk(R.id.bjk1).zf();
                xxs = zf.qctwkg(xxs);

                String urls = "";

                String uid = wj.dqwb(AppPaths.appPath("data/ltlb/lbx_id"));

                final String set2 = AppPaths.appPath("settings/f11/set2.inf");

                final String set3 = AppPaths.appPath("settings/f11/set3.inf");

                final String set4 = AppPaths.appPath("settings/f11/set4.inf");
                urls = e1.urlUnlockString(wj.dqwb(set2));

                String namer = wj.dqwb(set3);

                if (zf.cjw(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                String ljf = wj.dqwb(AppPaths.appPath("data/assets/a17"));

                String idget = urls + "class/api.php?type=release" + ljf + "id=" + uid;

                final String name = namer;

                final String xx = xxs;

                final String url = idget;

                final String code = wj.dqwb(set4);

                final i.runlibrary.app.v.dx dx = st.dx(R.id.dx_chat_upload);

                final i.runlibrary.app.v.an an = st.an(R.id.an_upload);

                String cachehd = AppPaths.appPath("data/ltlb/lbx_title");

                final String title = wj.dqwb(cachehd);
                gj.xc(new Thread() {

                    public void run() {

                        boolean ppp = false;

                        if (zf.dy(xx, "")) {

                            ltsview(3);
                            e1.tsk("提示", "消息不能为空");

                        } else {

                            if (dx.xzzt()) {

                                if (zf.dy(an.tszf(), "上传中")) {

                                    e1.tsk("提示", "请耐心等待文件上传完毕");
                                    ltsview(3);

                                } else if (zf.dy(an.tszf(), "选择文件") || zf.dy(an.tszf(), "已选择•开始上传")) {

                                    e1.tsk("提示", "请先上传文件");
                                    ltsview(3);

                                } else {

                                    ppp = true;

                                }

                            } else {

                                ppp = true;

                            }

                            if (ppp) {

                                String name_ = name;

                                String _xx_ = xx;

                                if (dx.xzzt()) {

                                    if (zf.dy(an.tszf(), "上传成功")) {

                                        String u_ = wj.dqwb(AppPaths.appPath("cache/chat/upload/info"));

                                        String t_ = wj.dqwb(AppPaths.appPath("cache/chat/upload/type"));

                                        if (zf.dy(t_, "0")) {

                                            _xx_ = _xx_ + "[PHOTO]" + u_ + "[P:END]";

                                        } else if (zf.dy(t_, "1")) {

                                            _xx_ = _xx_ + "[AUDIO]" + u_ + "[A:END]";

                                        } else if (zf.dy(t_, "2")) {

                                            _xx_ = _xx_ + "[VIDEO]" + u_ + "[V:END]";

                                        } else if (zf.dy(t_, "3")) {

                                            _xx_ = _xx_ + "[OTHER]" + u_ + "[O:END]";

                                        }

                                    }

                                }

                                String xx_ = _xx_;
                                ltsview(0);
                                ltjmshow2("正在发送中...");

                                String cachehd = AppPaths.appPath("data/ltlb/lbx_title");

                                final String name = wj.dqwb(cachehd);
                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        st.wb(R.id.title_bar).zf(getTabName(name));
                                        st.kp(R.id.kp7).kjd(0);

                                    }

                                });

                                if (e1.yz()) {

                                    String setback = "";

                                    String[] wlhq1 = new String[]{"name=" + name_, "value=" + xx_, "code=" + code, "uid=" + e1.awgwl_getuid()};

                                    String back = wl.hq(url, wlhq1, "utf-8", null, true, null, 20000, 20000, null);

                                    if (zf.dy(back, "") || zf.dy(back, null)) {

                                        setback = "0";

                                    } else {

                                        if (zf.dy(setback, "0")) {

                                            setback = "0";

                                        } else {

                                            setback = back;

                                        }

                                    }

                                    if (zf.dy(setback, "0")) {

                                        ltsview(0);
                                        ltjmshowgb();
                                        e1.tsk("提示", "发送失败(-1)");
                                        gj.jmxc(new Runnable() {

                                            public void run() {

                                                st.kp(R.id.kp7).kjd(0);
                                                st.wb(R.id.title_bar).zf(getTabName(title));

                                            }

                                        });

                                    } else {

                                        ltsview(1);

                                        if (zf.dy(setback, "true")) {

                                            gj.jmxc(new Runnable() {

                                                public void run() {

                                                    st.kp(R.id.kp7).kjd(0);
                                                    st.wb(R.id.title_bar).zf(getTabName(title));

                                                }

                                            });
                                            ltjmshow("发送成功", true);
                                            ltsview(0);

                                        } else {

                                            e1.tsk("提示", "发送失败\n" + setback);
                                            ltsview(0);
                                            ltjmshowgb();
                                            gj.jmxc(new Runnable() {

                                                public void run() {

                                                    st.kp(R.id.kp7).kjd(0);
                                                    st.wb(R.id.title_bar).zf(getTabName(title));

                                                }

                                            });

                                        }

                                    }

                                } else {

                                    ltsview(0);
                                    e1.tsk("提示", "发送失败(-2)");
                                    ltjmshowgb();
                                    gj.jmxc(new Runnable() {

                                        public void run() {

                                            st.kp(R.id.kp7).kjd(0);
                                            st.wb(R.id.title_bar).zf(getTabName(title));

                                        }

                                    });

                                }

                            }

                        }
                        state_post = false;

                    }

                });

            }

        } else {

        }

    }

    public void post_jb(Object c, Object d, Object e, Object f) {

        if (!state_post_jb) {

            state_post_jb = true;
            gj.jmxc(new Runnable() {

                public void run() {

                    st.xxbj(R.id.xxbj3).kjd(8);
                    st.xxbj(R.id.xxbj5).kjd(0);

                }

            });

            Object xxs = c;
            xxs = zf.qctwkg(xxs);

            String urls = "";

            String uid = wj.dqwb(AppPaths.appPath("data/ltlb/lbx_id"));

            final String set2 = AppPaths.appPath("settings/f11/set2.inf");

            final String set3 = AppPaths.appPath("settings/f11/set3.inf");
            urls = e1.urlUnlockString(wj.dqwb(set2));

            String namer = wj.dqwb(set3);

            if (zf.cjw(urls, "/")) {

            } else {

                urls = urls + "/";

            }

            String ljf = wj.dqwb(AppPaths.appPath("data/assets/a17"));

            String idget = urls + "class/api.php?type=release" + ljf + "id=110";

            String j = uid;

            if (zf.dy(xxs, "")) {

                e1.tsk("提示", "举报理由不能为空");
                gj.jmxc(new Runnable() {

                    public void run() {

                        st.xxbj(R.id.xxbj3).kjd(0);
                        st.xxbj(R.id.xxbj5).kjd(8);

                    }

                });
                state_post_jb = false;

            } else {

                String namex0 = "用户举报";

                final String namex = namex0;

                String xxk = "【举报人】\n" + namer + "\n【举报理由】\n" + xxs + "\n\n【被举报人昵称】\n" + d + "\n【被举报消息内容】\n" + f + "\n【被消息发出时间】\n" + e + "\n【被举报消息所在房间】\n" + j;

                final String xx = xxk;

                final String name = namer;

                final String url = idget;
                gj.xc(new Thread() {

                    public void run() {

                        if (e1.yz()) {

                            String setback = "";

                            String[] wlhq1 = new String[]{"name=" + namex, "value=" + xx, "code=baseTextUpload"};

                            String back = wl.hq(url, wlhq1, "utf-8", null, true, null, 20000, 20000, null);

                            if (zf.dy(back, "") || zf.dy(back, null)) {

                                setback = "0";

                            } else {

                                if (zf.dy(setback, "0")) {

                                    setback = "0";

                                } else {

                                    setback = back;

                                }

                            }

                            if (zf.dy(setback, "0")) {

                                e1.tsk("提示", "举报失败(-1)");
                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        st.xxbj(R.id.xxbj3).kjd(0);
                                        st.xxbj(R.id.xxbj5).kjd(8);

                                    }

                                });

                            } else {

                                if (zf.dy(setback, "true")) {

                                    e1.tsk("提示", "举报成功");
                                    gj.jmxc(new Runnable() {

                                        public void run() {

                                            st.xxbj(R.id.xxbj3).kjd(0);
                                            st.xxbj(R.id.xxbj5).kjd(8);

                                        }

                                    });
                                    gj.gb();

                                } else {

                                    e1.tsk("提示", "举报失败\n" + setback);
                                    gj.jmxc(new Runnable() {

                                        public void run() {

                                            st.xxbj(R.id.xxbj3).kjd(0);
                                            st.xxbj(R.id.xxbj5).kjd(8);

                                        }

                                    });

                                }

                            }

                        } else {

                            e1.tsk("提示", "举报失败(-2)");
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    st.xxbj(R.id.xxbj3).kjd(0);
                                    st.xxbj(R.id.xxbj5).kjd(8);

                                }

                            });

                        }
                        state_post_jb = false;

                    }

                });

            }

        } else {

        }

    }

    public void ltjmshow(Object a, boolean b) {

        final Object bt = a;

        String color = "";

        if (b) {

            color = zf.zf(R.color.colorTextTrue);

        } else {

            color = zf.zf(R.color.colorTextFalse);

        }

        final String bj = color;
        gj.jmxc(new Runnable() {

            public void run() {

                i.runlibrary.app.v.wb wb = st.wb(R.id.wb3);
                wb.zf("[提示] " + bt);
                wb.bj(bj);
                wb.kjd(0);

            }

        });

    }

    public void ltjmshow2(Object a) {

        final Object bt = a;

        String color = zf.zf(R.color.colorAccent);

        final String bj = color;
        gj.jmxc(new Runnable() {

            public void run() {

                i.runlibrary.app.v.wb wb = st.wb(R.id.wb3);
                wb.zf("[提示] " + bt);
                wb.bj(bj);
                wb.kjd(0);

            }

        });

    }

    public void ltjmshowgb() {
        gj.jmxc(new Runnable() {

            public void run() {

                i.runlibrary.app.v.wb wb = st.wb(R.id.wb3);
                wb.kjd(8);

            }

        });

    }

    public void upload(Object a, int types) {

        if (!state_upload) {

            state_upload = true;

            final int type = types;

            final String uu = AppPaths.appPath("settings/other/0xO5HJ7/url");

            final String path_ = wj.hqml(a);

            final String[] path = new String[]{path_};

            final i.runlibrary.app.v.dx dx = st.dx(R.id.dx_chat_upload);

            final i.runlibrary.app.v.an an = st.an(R.id.an_upload);

            final i.runlibrary.app.v.jdt jdt2 = st.jdt(R.id.jdt2);

            final i.runlibrary.app.v.xlcd ltsup_xlcd1 = st.xlcd(R.id.ltsup_xlcd1);

            final i.runlibrary.app.v.xlcd ltsup_xlcd2 = st.xlcd(R.id.ltsup_xlcd2);

            final i.runlibrary.app.v.an an2 = st.an(R.id.an_disupload);
            gj.xc(new Thread() {

                public void run() {

                    upload_back = "";
                    gj.jmxc(new Runnable() {

                        public void run() {

                            jdt2.jdz(0);
                            jdt2.kjd(8);
                            an2.kjd(8);
                            an.kjd(8);
                            an.tszf("上传中");
                            dx.st.setEnabled(false);
                            ltsup_xlcd1.st.setEnabled(false);
                            ltsup_xlcd1.kjd(8);
                            ltsup_xlcd2.st.setEnabled(false);
                            ltsup_xlcd2.kjd(8);

                        }

                    });

                    if (e1.yz()) {

                        if (zf.dy(path_, "") || !wj.cz(path_)) {

                            gj.jmxc(new Runnable() {

                                public void run() {

                                    jdt2.kjd(8);
                                    an.kjd(0);
                                    an2.kjd(8);
                                    an.tszf("选择文件");
                                    dx.st.setEnabled(true);
                                    ltsup_xlcd1.st.setEnabled(true);
                                    ltsup_xlcd1.kjd(0);
                                    ltsup_xlcd2.st.setEnabled(true);
                                    ltsup_xlcd2.kjd(0);

                                }

                            });
                            e1.tsk("提示", "文件路径无效");

                        } else {

                            String urls = wj.dqwb(uu);
                            urls = e1.urlUnlockString(urls);

                            final String url = urls;

                            String fn__ = "null";

                            String[] fn_ = e1.fileinfo("/", path[0]);

                            if (zf.dy(fn_[0], "true")) {

                                fn__ = fn_[3];

                            }

                            final String file_name = fn__;

                            final long file_size = wj.dx(path[0]);

                            String[] p = new String[]{"name=" + fn__, "size=" + wj.dx(path[0])};
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    an.tszf("上传中");
                                    an.kjd(8);
                                    an2.kjd(8);
                                    dx.st.setEnabled(false);
                                    ltsup_xlcd1.st.setEnabled(false);
                                    ltsup_xlcd1.kjd(8);
                                    ltsup_xlcd2.st.setEnabled(false);
                                    ltsup_xlcd2.kjd(8);
                                    jdt2.jdz(0);
                                    jdt2.kjd(0);

                                    try {

                                        HttpRequest.TIME_OUT_DURATION = 300;
                                        HttpRequest.build(context, url)
                                                .addParameter("name", file_name)
                                                .addParameter("size", file_size)
                                                .addParameter("file", wj.wj(path_))
                                                .setUploadProgressListener(new UploadProgressListener() {
                                                    @Override
                                                    public void onUpload(float percentage, long current, long total, boolean done) {

                                                        jdt2.sxz(total);
                                                        jdt2.jdz(current);
                                                    }
                                                })
                                                .setResponseListener(new ResponseListener() {
                                                    @Override
                                                    public void onResponse(String response, Exception error) {
                                                        uploadResult(error, response, type);
                                                    }
                                                })
                                                .doPost();

                                    } catch (Throwable e) {

                                        jdt2.kjd(8);
                                        an.kjd(0);
                                        an2.kjd(8);
                                        an.tszf("选择文件");
                                        dx.st.setEnabled(true);
                                        ltsup_xlcd1.st.setEnabled(true);
                                        ltsup_xlcd1.kjd(0);
                                        ltsup_xlcd2.st.setEnabled(true);
                                        ltsup_xlcd2.kjd(0);
                                        e1.tsk("提示", "上传失败(-1)");

                                    }

                                }

                            });

                        }

                    } else {

                        gj.jmxc(new Runnable() {

                            public void run() {

                                jdt2.kjd(8);
                                an.kjd(0);
                                an2.kjd(8);
                                an.tszf("选择文件");
                                dx.st.setEnabled(true);
                                ltsup_xlcd1.st.setEnabled(true);
                                ltsup_xlcd1.kjd(0);
                                ltsup_xlcd2.st.setEnabled(true);
                                ltsup_xlcd2.kjd(0);

                            }

                        });
                        e1.tsk("提示", "上传失败\n连接服务器超时(-2)");

                    }
                    state_upload = false;

                }

            });

        } else {

        }

    }

    public void uploadResult(Exception error, Object upload_back, int type) {

        final i.runlibrary.app.v.dx dx = st.dx(R.id.dx_chat_upload);

        final i.runlibrary.app.v.an an = st.an(R.id.an_upload);

        final i.runlibrary.app.v.jdt jdt2 = st.jdt(R.id.jdt2);

        final i.runlibrary.app.v.xlcd ltsup_xlcd1 = st.xlcd(R.id.ltsup_xlcd1);

        final i.runlibrary.app.v.xlcd ltsup_xlcd2 = st.xlcd(R.id.ltsup_xlcd2);

        final i.runlibrary.app.v.an an2 = st.an(R.id.an_disupload);

        Object back = upload_back;

        if (!zf.dy(IappCompat.zh(sj.zh()).zstring(error), null)) {

            gj.jmxc(new Runnable() {

                public void run() {

                    jdt2.kjd(8);
                    an.tszf("选择文件");
                    an.kjd(0);
                    an2.kjd(8);
                    dx.st.setEnabled(true);
                    ltsup_xlcd1.st.setEnabled(true);
                    ltsup_xlcd1.kjd(0);
                    ltsup_xlcd2.st.setEnabled(true);
                    ltsup_xlcd2.kjd(0);

                }

            });
            e1.tsk("提示", "上传失败\n" + IappCompat.zh(sj.zh()).zstring(error));

        } else {

            if (zf.dy(back, "") || zf.dy(back, null)) {

                gj.jmxc(new Runnable() {

                    public void run() {

                        jdt2.kjd(8);
                        an.kjd(0);
                        an.tszf("选择文件");
                        an2.kjd(8);
                        dx.st.setEnabled(true);
                        ltsup_xlcd1.st.setEnabled(true);
                        ltsup_xlcd1.kjd(0);
                        ltsup_xlcd2.st.setEnabled(true);
                        ltsup_xlcd2.kjd(0);
                        e1.tsk("提示", "上传失败\n连接服务器超时(-1)");

                    }

                });

            } else {

                if (zf.cz(back, "https://") || zf.cz(back, "http://")) {

                    if (zf.cz(back, "https://")) {

                        back = "https://" + zf.qc(back, "https://", null);
                        back = zf.qctwkg(back);

                    } else if (zf.cz(back, "http://")) {

                        back = "http://" + zf.qc(back, "http://", null);
                        back = zf.qctwkg(back);

                    }
                    wj.xrwb(AppPaths.appPath("cache/chat/upload/info"), back);
                    wj.xrwb(AppPaths.appPath("cache/chat/upload/type"), IappCompat.zh(sj.zh()).zstring(type));
                    gj.jmxc(new Runnable() {

                        public void run() {

                            jdt2.kjd(8);
                            an.kjd(0);
                            an.tszf("上传成功");
                            an2.kjd(0);
                            dx.st.setEnabled(false);
                            ltsup_xlcd1.st.setEnabled(false);
                            ltsup_xlcd1.kjd(8);
                            ltsup_xlcd2.st.setEnabled(false);
                            ltsup_xlcd2.kjd(8);

                        }

                    });

                } else {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            jdt2.kjd(8);
                            an.kjd(0);
                            an2.kjd(8);
                            an.tszf("选择文件");
                            dx.st.setEnabled(true);
                            ltsup_xlcd1.st.setEnabled(true);
                            ltsup_xlcd1.kjd(0);
                            ltsup_xlcd2.st.setEnabled(true);
                            ltsup_xlcd2.kjd(0);

                        }

                    });
                    e1.tsk("提示", "上传失败\n" + back);

                }

            }

        }

    }
}
