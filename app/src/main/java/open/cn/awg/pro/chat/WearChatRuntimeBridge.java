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
import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.View;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.listener.UploadProgressListener;

import android.app.Activity;
import android.content.Context;

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
import open.cn.awg.pro.ui.widget.XLinearLayoutManager;

@SuppressLint("StaticFieldLeak")
public class WearChatRuntimeBridge {
    private final Context context;
    private final Activity owner;

    public static open.cn.awg.pro.ui.list.NativeListAdapter wllbcdspq;

    public static androidx.recyclerview.widget.RecyclerView wllbcdlb;

    public static open.cn.awg.pro.ui.list.NativeListAdapter spq2;

    public static androidx.recyclerview.widget.RecyclerView lb2;

    public static open.cn.awg.pro.ui.list.NativeListAdapter spq3;

    public static androidx.recyclerview.widget.RecyclerView lb3;

public static LinearLayoutManager mLayoutManager;

    public static String set17 = AppPaths.appPath("settings/db/talking.db");

    public static android.database.sqlite.SQLiteDatabase sjk;

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
    public AppRuntimeBridge e1;
    public LocalDatabaseBridge e11;
    public String upload_back = "";

    public WearChatRuntimeBridge(Context context) {
        this.context = java.util.Objects.requireNonNull(context);
        this.owner = context instanceof Activity ? (Activity) context : null;
        e1 = new AppRuntimeBridge(context);
        e11 = new LocalDatabaseBridge(context);
    }

    public String getTabName(String room_name) {

        String exist = "< ";

        if (open.cn.awg.pro.core.TextValues.length(room_name) > 5) {

            return exist + open.cn.awg.pro.core.TextValues.slice(room_name, 0, 5) + "..";

        }
        return exist + room_name;

    }

    public boolean part_buildNewRoom(Object name, Object id) {
        open.cn.awg.pro.core.NativeActions.sleep(500);

        final String set2 = AppPaths.appPath("settings/f11/set2.inf");
        name = open.cn.awg.pro.core.TextValues.trim(name);
        id = open.cn.awg.pro.core.TextValues.trim(id);

        if (java.util.Objects.equals(name, "") || java.util.Objects.equals(id, "")) {

            e1.tsk("提示", "提交失败：请填写完整");

        } else {

            try {

                String url = e1.urlUnlockString(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)));

                if (open.cn.awg.pro.core.TextValues.endsWith(url, "/")) {

                } else {

                    url = url + "/";

                }

                String idget = url + "class/api.php?type=make";

                String[] pt = new String[]{"title=" + name, "id=" + id, "pass=123456"};

                String st5 = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(idget), (String[]) (pt), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                if (java.util.Objects.equals(st5, "null") || java.util.Objects.equals(st5, "") || java.util.Objects.equals(st5, null)) {

                    e1.tsk("提示", "提交失败：连接服务器异常");

                } else {

                    st5 = open.cn.awg.pro.core.TextValues.trim(st5);
                    st5 = open.cn.awg.pro.core.TextValues.replace(st5, "<br>", "  ");
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
        open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (AppPaths.appPath("data/ltlb/hdva")), (String) ("0"));
        lb3 = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.ltsv7lb1));

        
        mLayoutManager = new XLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

        lb3.setLayoutManager(mLayoutManager);
        open.cn.awg.pro.core.NativeFiles.delete(context, (String) (AppPaths.appPath("data/ltlb/disTouch")), false);
        spq3 = open.cn.awg.pro.ui.list.NativeListAdapter.attach(owner, lb3, R.layout.chat_attachment_item, new open.cn.awg.pro.ui.list.ChatAttachmentRowBinder(owner), new open.cn.awg.pro.ui.list.NativeListAdapter.RowBinder() {

            public void bind(View vw, java.util.Map<Integer, Object> row, int pn) {

                

                final int f = open.cn.awg.pro.core.TextValues.intValue(row.get(-1), -1);

                if (f == -1) {

                    String color0 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextTrue2);

                    String color1 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorBlack);

                    String color2 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb2)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color2));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb3)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color2));
                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.kp1)).setCardBackgroundColor(open.cn.awg.pro.core.NativeViewValues.color(color0));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb2)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("right"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjat)).setVisibility(android.view.View.GONE);
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.chat_attachment_item_root)).setClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.chat_attachment_item_root)).setLongClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj3)).setVisibility(android.view.View.GONE);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb21)).setVisibility(android.view.View.GONE);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb22)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj24)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj4k)).setVisibility(android.view.View.GONE);

                    androidx.cardview.widget.CardView kp2 = ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.kp2));
                    kp2.setVisibility(android.view.View.VISIBLE);

                    android.widget.RelativeLayout.LayoutParams kp2_xdbjgz = ((android.widget.RelativeLayout.LayoutParams) (Object) open.cn.awg.pro.core.NativeViewValues.relativeParams(kp2));
                    open.cn.awg.pro.core.NativeViewValues.relativeRule(kp2, kp2_xdbjgz, android.widget.RelativeLayout.ALIGN_PARENT_LEFT, false);
                    open.cn.awg.pro.core.NativeViewValues.relativeRule(kp2, kp2_xdbjgz, android.widget.RelativeLayout.ALIGN_PARENT_RIGHT, true);

                    String wb = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb2)).getText().toString();

                    int isVip = 8;

                    int isVip2 = 0;

                    if (java.util.Objects.equals(wb, "台风眼Typheye") || java.util.Objects.equals(wb, "系统消息")) {

                        isVip = 0;
                        isVip2 = 8;

                    }
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb7)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(isVip, -1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb21)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(isVip2, -1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb6)).setText(String.valueOf("我"));

                    Object luid = row.get(-3);
                    luid = open.cn.awg.pro.core.TextValues.trim(luid);

                    if (java.util.Objects.equals(luid, "w0") || java.util.Objects.equals(luid, "") || java.util.Objects.equals(luid, null) || java.util.Objects.equals(luid, "0") || java.util.Objects.equals(luid, "null")) {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb22)).setVisibility(android.view.View.VISIBLE);

                    } else {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb22)).setVisibility(android.view.View.GONE);

                    }

                    if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb21)).getText().toString(), "未知")) {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb21)).setVisibility(android.view.View.GONE);

                    } else {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb21)).setVisibility(android.view.View.VISIBLE);

                    }

                    String wb2 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).getText().toString();

                    if (open.cn.awg.pro.core.TextValues.contains(wb2, "[PHOTO]") && open.cn.awg.pro.core.TextValues.contains(wb2, "[P:END]")) {

                        String ur = open.cn.awg.pro.core.TextValues.slice(wb2, "[PHOTO]", "[P:END]");

                        String tx = open.cn.awg.pro.core.TextValues.slice(wb2, null, "[PHOTO]");
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf("[附件]图片"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(ur));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.b11);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(tx));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                    } else if (open.cn.awg.pro.core.TextValues.contains(wb2, "[AUDIO]") && open.cn.awg.pro.core.TextValues.contains(wb2, "[A:END]")) {

                        String ur = open.cn.awg.pro.core.TextValues.slice(wb2, "[AUDIO]", "[A:END]");

                        String tx = open.cn.awg.pro.core.TextValues.slice(wb2, null, "[AUDIO]");
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf("[附件]音频"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(ur));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.b9);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(tx));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                    } else if (open.cn.awg.pro.core.TextValues.contains(wb2, "[VIDEO]") && open.cn.awg.pro.core.TextValues.contains(wb2, "[V:END]")) {

                        String ur = open.cn.awg.pro.core.TextValues.slice(wb2, "[VIDEO]", "[V:END]");

                        String tx = open.cn.awg.pro.core.TextValues.slice(wb2, null, "[VIDEO]");
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf("[附件]视频"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(ur));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.b8);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(tx));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                    } else if (open.cn.awg.pro.core.TextValues.contains(wb2, "[OTHER]") && open.cn.awg.pro.core.TextValues.contains(wb2, "[O:END]")) {

                        String ur = open.cn.awg.pro.core.TextValues.slice(wb2, "[OTHER]", "[O:END]");

                        String tx = open.cn.awg.pro.core.TextValues.slice(wb2, null, "[OTHER]");
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf("[附件]文件"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(ur));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.b14);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(tx));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                    } else if (open.cn.awg.pro.core.TextValues.contains(wb2, "[INFOS]") && open.cn.awg.pro.core.TextValues.contains(wb2, "[I:END]")) {

                        String ur = open.cn.awg.pro.core.TextValues.slice(wb2, "[INFOS]", "[I:END]");

                        String tx = open.cn.awg.pro.core.TextValues.slice(wb2, null, "[INFOS]");
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf(""));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(""));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.a);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(""));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(ur));

                    } else {

                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf(""));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(""));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.a);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(wb2));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                    }

                } else if (f == 0) {

                    String color0 = "#00000000";

                    String color1 = "#00000000";

                    String color2 = "#00000000";
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb2)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color2));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb3)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color2));
                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.kp1)).setCardBackgroundColor(open.cn.awg.pro.core.NativeViewValues.color(color0));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb2)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("left"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjat)).setVisibility(android.view.View.GONE);
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.chat_attachment_item_root)).setClickable(java.lang.Boolean.TRUE.equals(false));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.chat_attachment_item_root)).setLongClickable(java.lang.Boolean.TRUE.equals(false));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj3)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb21)).setVisibility(android.view.View.GONE);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb22)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj24)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj4k)).setVisibility(android.view.View.VISIBLE);

                    androidx.cardview.widget.CardView kp2 = ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.kp2));
                    kp2.setVisibility(android.view.View.GONE);

                    android.widget.RelativeLayout.LayoutParams kp2_xdbjgz = ((android.widget.RelativeLayout.LayoutParams) (Object) open.cn.awg.pro.core.NativeViewValues.relativeParams(kp2));
                    open.cn.awg.pro.core.NativeViewValues.relativeRule(kp2, kp2_xdbjgz, android.widget.RelativeLayout.ALIGN_PARENT_LEFT, true);
                    open.cn.awg.pro.core.NativeViewValues.relativeRule(kp2, kp2_xdbjgz, android.widget.RelativeLayout.ALIGN_PARENT_RIGHT, false);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb7)).setVisibility(android.view.View.GONE);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb6)).setText(String.valueOf(""));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.GONE);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf(""));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(""));
                    open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.a);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(""));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                } else if (f == 1) {

                    String color0 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorWBK);

                    String color1 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);

                    String color2 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb2)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color2));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb3)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color2));
                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.kp1)).setCardBackgroundColor(open.cn.awg.pro.core.NativeViewValues.color(color0));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb2)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("left"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjat)).setVisibility(android.view.View.GONE);
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.chat_attachment_item_root)).setClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.chat_attachment_item_root)).setLongClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj3)).setVisibility(android.view.View.GONE);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb21)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb22)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj24)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj4k)).setVisibility(android.view.View.VISIBLE);

                    androidx.cardview.widget.CardView kp2 = ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.kp2));
                    kp2.setVisibility(android.view.View.VISIBLE);

                    android.widget.RelativeLayout.LayoutParams kp2_xdbjgz = ((android.widget.RelativeLayout.LayoutParams) (Object) open.cn.awg.pro.core.NativeViewValues.relativeParams(kp2));
                    open.cn.awg.pro.core.NativeViewValues.relativeRule(kp2, kp2_xdbjgz, android.widget.RelativeLayout.ALIGN_PARENT_LEFT, true);
                    open.cn.awg.pro.core.NativeViewValues.relativeRule(kp2, kp2_xdbjgz, android.widget.RelativeLayout.ALIGN_PARENT_RIGHT, false);

                    String wb = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb2)).getText().toString();

                    int isVip = 8;

                    int isVip2 = 0;

                    if (java.util.Objects.equals(wb, "台风眼Typheye") || java.util.Objects.equals(wb, "系统消息")) {

                        isVip = 0;
                        isVip2 = 8;

                    }
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb7)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(isVip, -1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb21)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(isVip2, -1));

                    Object luid = row.get(-3);
                    luid = open.cn.awg.pro.core.TextValues.trim(luid);

                    if (java.util.Objects.equals(luid, "w0") || java.util.Objects.equals(luid, "") || java.util.Objects.equals(luid, null) || java.util.Objects.equals(luid, "0") || java.util.Objects.equals(luid, "null")) {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb22)).setVisibility(android.view.View.VISIBLE);

                    } else {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb22)).setVisibility(android.view.View.GONE);

                    }
                    wb = open.cn.awg.pro.core.TextValues.slice(wb, 0, 1);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb6)).setText(String.valueOf(wb));

                    if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb21)).getText().toString(), "未知")) {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb21)).setVisibility(android.view.View.GONE);

                    } else {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb21)).setVisibility(android.view.View.VISIBLE);

                    }

                    String wb2 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).getText().toString();

                    if (open.cn.awg.pro.core.TextValues.contains(wb2, "[PHOTO]") && open.cn.awg.pro.core.TextValues.contains(wb2, "[P:END]")) {

                        String ur = open.cn.awg.pro.core.TextValues.slice(wb2, "[PHOTO]", "[P:END]");

                        String tx = open.cn.awg.pro.core.TextValues.slice(wb2, null, "[PHOTO]");
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf("[附件]图片"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(ur));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.b11);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(tx));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                    } else if (open.cn.awg.pro.core.TextValues.contains(wb2, "[AUDIO]") && open.cn.awg.pro.core.TextValues.contains(wb2, "[A:END]")) {

                        String ur = open.cn.awg.pro.core.TextValues.slice(wb2, "[AUDIO]", "[A:END]");

                        String tx = open.cn.awg.pro.core.TextValues.slice(wb2, null, "[AUDIO]");
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf("[附件]音频"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(ur));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.b9);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(tx));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                    } else if (open.cn.awg.pro.core.TextValues.contains(wb2, "[VIDEO]") && open.cn.awg.pro.core.TextValues.contains(wb2, "[V:END]")) {

                        String ur = open.cn.awg.pro.core.TextValues.slice(wb2, "[VIDEO]", "[V:END]");

                        String tx = open.cn.awg.pro.core.TextValues.slice(wb2, null, "[VIDEO]");
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf("[附件]视频"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(ur));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.b8);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(tx));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                    } else if (open.cn.awg.pro.core.TextValues.contains(wb2, "[OTHER]") && open.cn.awg.pro.core.TextValues.contains(wb2, "[O:END]")) {

                        String ur = open.cn.awg.pro.core.TextValues.slice(wb2, "[OTHER]", "[O:END]");

                        String tx = open.cn.awg.pro.core.TextValues.slice(wb2, null, "[OTHER]");
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf("[附件]文件"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(ur));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.b14);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(tx));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                    } else if (open.cn.awg.pro.core.TextValues.contains(wb2, "[INFOS]") && open.cn.awg.pro.core.TextValues.contains(wb2, "[I:END]")) {

                        String ur = open.cn.awg.pro.core.TextValues.slice(wb2, "[INFOS]", "[I:END]");

                        String tx = open.cn.awg.pro.core.TextValues.slice(wb2, null, "[INFOS]");
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf(""));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(""));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.a);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(""));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(ur));

                    } else {

                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf(""));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(""));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.a);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(wb2));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                    }

                } else if (f == 2) {

                    String color0 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorWBK);

                    String color1 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);

                    String color2 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb2)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color2));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb3)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color2));
                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.kp1)).setCardBackgroundColor(open.cn.awg.pro.core.NativeViewValues.color(color0));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb2)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("left"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjat)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.chat_attachment_item_root)).setClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.chat_attachment_item_root)).setLongClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj3)).setVisibility(android.view.View.GONE);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb21)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb22)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj24)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj4k)).setVisibility(android.view.View.VISIBLE);

                    androidx.cardview.widget.CardView kp2 = ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.kp2));
                    kp2.setVisibility(android.view.View.VISIBLE);

                    android.widget.RelativeLayout.LayoutParams kp2_xdbjgz = ((android.widget.RelativeLayout.LayoutParams) (Object) open.cn.awg.pro.core.NativeViewValues.relativeParams(kp2));
                    open.cn.awg.pro.core.NativeViewValues.relativeRule(kp2, kp2_xdbjgz, android.widget.RelativeLayout.ALIGN_PARENT_LEFT, true);
                    open.cn.awg.pro.core.NativeViewValues.relativeRule(kp2, kp2_xdbjgz, android.widget.RelativeLayout.ALIGN_PARENT_RIGHT, false);

                    String wb = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb2)).getText().toString();

                    int isVip = 8;

                    int isVip2 = 0;

                    if (java.util.Objects.equals(wb, "台风眼Typheye") || java.util.Objects.equals(wb, "系统消息")) {

                        isVip = 0;
                        isVip2 = 8;

                    }
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb7)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(isVip, -1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb21)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(isVip2, -1));

                    Object luid = row.get(-3);
                    luid = open.cn.awg.pro.core.TextValues.trim(luid);

                    if (java.util.Objects.equals(luid, "w0") || java.util.Objects.equals(luid, "") || java.util.Objects.equals(luid, null) || java.util.Objects.equals(luid, "0") || java.util.Objects.equals(luid, "null")) {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb22)).setVisibility(android.view.View.VISIBLE);

                    } else {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb22)).setVisibility(android.view.View.GONE);

                    }
                    wb = open.cn.awg.pro.core.TextValues.slice(wb, 0, 1);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb6)).setText(String.valueOf(wb));

                    if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb21)).getText().toString(), "未知")) {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb21)).setVisibility(android.view.View.GONE);

                    } else {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb21)).setVisibility(android.view.View.VISIBLE);

                    }

                    String wb2 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).getText().toString();

                    if (open.cn.awg.pro.core.TextValues.contains(wb2, "[PHOTO]") && open.cn.awg.pro.core.TextValues.contains(wb2, "[P:END]")) {

                        String ur = open.cn.awg.pro.core.TextValues.slice(wb2, "[PHOTO]", "[P:END]");

                        String tx = open.cn.awg.pro.core.TextValues.slice(wb2, null, "[PHOTO]");
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf("[附件]图片"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(ur));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.b11);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(tx));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                    } else if (open.cn.awg.pro.core.TextValues.contains(wb2, "[AUDIO]") && open.cn.awg.pro.core.TextValues.contains(wb2, "[A:END]")) {

                        String ur = open.cn.awg.pro.core.TextValues.slice(wb2, "[AUDIO]", "[A:END]");

                        String tx = open.cn.awg.pro.core.TextValues.slice(wb2, null, "[AUDIO]");
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf("[附件]音频"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(ur));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.b9);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(tx));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                    } else if (open.cn.awg.pro.core.TextValues.contains(wb2, "[VIDEO]") && open.cn.awg.pro.core.TextValues.contains(wb2, "[V:END]")) {

                        String ur = open.cn.awg.pro.core.TextValues.slice(wb2, "[VIDEO]", "[V:END]");

                        String tx = open.cn.awg.pro.core.TextValues.slice(wb2, null, "[VIDEO]");
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf("[附件]视频"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(ur));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.b8);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(tx));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                    } else if (open.cn.awg.pro.core.TextValues.contains(wb2, "[OTHER]") && open.cn.awg.pro.core.TextValues.contains(wb2, "[O:END]")) {

                        String ur = open.cn.awg.pro.core.TextValues.slice(wb2, "[OTHER]", "[O:END]");

                        String tx = open.cn.awg.pro.core.TextValues.slice(wb2, null, "[OTHER]");
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf("[附件]文件"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(ur));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.b14);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(tx));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                    } else if (open.cn.awg.pro.core.TextValues.contains(wb2, "[INFOS]") && open.cn.awg.pro.core.TextValues.contains(wb2, "[I:END]")) {

                        String ur = open.cn.awg.pro.core.TextValues.slice(wb2, "[INFOS]", "[I:END]");

                        String tx = open.cn.awg.pro.core.TextValues.slice(wb2, null, "[INFOS]");
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf(""));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(""));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.a);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(""));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(ur));

                    } else {

                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf(""));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(""));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.a);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(wb2));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                    }

                } else {

                    String color0 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorWBK);

                    String color1 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);

                    String color2 = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color1));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb2)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color2));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb3)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color2));
                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.kp1)).setCardBackgroundColor(open.cn.awg.pro.core.NativeViewValues.color(color0));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb2)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("left"));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjat)).setVisibility(android.view.View.GONE);
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.chat_attachment_item_root)).setClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.chat_attachment_item_root)).setLongClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj3)).setVisibility(android.view.View.GONE);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb21)).setVisibility(android.view.View.GONE);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb22)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj24)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj4k)).setVisibility(android.view.View.GONE);

                    androidx.cardview.widget.CardView kp2 = ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.kp2));
                    kp2.setVisibility(android.view.View.GONE);

                    android.widget.RelativeLayout.LayoutParams kp2_xdbjgz = ((android.widget.RelativeLayout.LayoutParams) (Object) open.cn.awg.pro.core.NativeViewValues.relativeParams(kp2));
                    open.cn.awg.pro.core.NativeViewValues.relativeRule(kp2, kp2_xdbjgz, android.widget.RelativeLayout.ALIGN_PARENT_LEFT, true);
                    open.cn.awg.pro.core.NativeViewValues.relativeRule(kp2, kp2_xdbjgz, android.widget.RelativeLayout.ALIGN_PARENT_RIGHT, false);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb6)).setText(String.valueOf(""));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb7)).setVisibility(android.view.View.GONE);

                    String wb2 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).getText().toString();

                    if (open.cn.awg.pro.core.TextValues.contains(wb2, "[PHOTO]") && open.cn.awg.pro.core.TextValues.contains(wb2, "[P:END]")) {

                        String ur = open.cn.awg.pro.core.TextValues.slice(wb2, "[PHOTO]", "[P:END]");

                        String tx = open.cn.awg.pro.core.TextValues.slice(wb2, null, "[PHOTO]");
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf("[附件]图片"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(ur));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.b11);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(tx));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                    } else if (open.cn.awg.pro.core.TextValues.contains(wb2, "[AUDIO]") && open.cn.awg.pro.core.TextValues.contains(wb2, "[A:END]")) {

                        String ur = open.cn.awg.pro.core.TextValues.slice(wb2, "[AUDIO]", "[A:END]");

                        String tx = open.cn.awg.pro.core.TextValues.slice(wb2, null, "[AUDIO]");
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf("[附件]音频"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(ur));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.b9);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(tx));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                    } else if (open.cn.awg.pro.core.TextValues.contains(wb2, "[VIDEO]") && open.cn.awg.pro.core.TextValues.contains(wb2, "[V:END]")) {

                        String ur = open.cn.awg.pro.core.TextValues.slice(wb2, "[VIDEO]", "[V:END]");

                        String tx = open.cn.awg.pro.core.TextValues.slice(wb2, null, "[VIDEO]");
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf("[附件]视频"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(ur));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.b8);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(tx));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                    } else if (open.cn.awg.pro.core.TextValues.contains(wb2, "[OTHER]") && open.cn.awg.pro.core.TextValues.contains(wb2, "[O:END]")) {

                        String ur = open.cn.awg.pro.core.TextValues.slice(wb2, "[OTHER]", "[O:END]");

                        String tx = open.cn.awg.pro.core.TextValues.slice(wb2, null, "[OTHER]");
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf("[附件]文件"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(ur));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.b14);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(tx));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                    } else if (open.cn.awg.pro.core.TextValues.contains(wb2, "[INFOS]") && open.cn.awg.pro.core.TextValues.contains(wb2, "[I:END]")) {

                        String ur = open.cn.awg.pro.core.TextValues.slice(wb2, "[INFOS]", "[I:END]");

                        String tx = open.cn.awg.pro.core.TextValues.slice(wb2, null, "[INFOS]");
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf(""));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(""));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.a);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(""));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(ur));

                    } else {

                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbjfj)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb8)).setText(String.valueOf(""));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb9)).setText(String.valueOf(""));
                        open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.tx2)), R.mipmap.a);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setText(String.valueOf(wb2));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setVisibility(android.view.View.GONE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb10)).setText(String.valueOf(""));

                    }

                }

            }

        });

    }

    public void csh() {
        e11.setup_talklist();
        sjk = (android.database.sqlite.SQLiteDatabase) (Object) open.cn.awg.pro.core.NativeDatabase.open(context, String.valueOf(set17));
        wllbcdlb = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.wllbv7lb2));
        wllbcdspq = open.cn.awg.pro.ui.list.NativeListAdapter.attach(owner, wllbcdlb, R.layout.wear_chat_menu_item, new open.cn.awg.pro.ui.list.WearChatMenuRowBinder(owner), new open.cn.awg.pro.ui.list.NativeListAdapter.RowBinder() {

            public void bind(View vw, java.util.Map<Integer, Object> row, int pn) {

                

                final int f = open.cn.awg.pro.core.TextValues.intValue(row.get(-1), -1);

                if (f == 1) {

                    String color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextFalse);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setLongClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.dp20)).setVisibility(android.view.View.GONE);

                } else if (f == -1) {

                    String color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setClickable(java.lang.Boolean.TRUE.equals(false));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setLongClickable(java.lang.Boolean.TRUE.equals(false));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.dp20)).setVisibility(android.view.View.VISIBLE);

                } else {

                    String color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextU1);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(color));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.xxbj1)).setLongClickable(java.lang.Boolean.TRUE.equals(true));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(vw, R.id.dp20)).setVisibility(android.view.View.GONE);

                }

            }

        });
        lb2 = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.wllbv7lb1));
        spq2 = open.cn.awg.pro.ui.list.NativeListAdapter.attach(owner, lb2, R.layout.chat_room_list_item, new open.cn.awg.pro.ui.list.ChatRoomRowBinder(owner), new open.cn.awg.pro.ui.list.NativeListAdapter.RowBinder() {

            public void bind(View vw, java.util.Map<Integer, Object> row, int pn) {

            }

        });

    }

    public void ltsview(int a) {

        final int i = a;

        final android.widget.LinearLayout root = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.i2xxbj1));

        final android.widget.LinearLayout load = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.ltssxxxbj3));

        final android.widget.LinearLayout cd = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbjcd));

        final android.widget.LinearLayout add = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj4));

        final androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.qtgd1));
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                if (i == 0) {

                    root.setVisibility(android.view.View.VISIBLE);
                    load.setVisibility(android.view.View.GONE);
                    cd.setVisibility(android.view.View.GONE);
                    add.setVisibility(android.view.View.GONE);
                    lb3.requestFocus();

                } else if (i == 1) {

                    cd.setVisibility(android.view.View.GONE);
                    add.setVisibility(android.view.View.GONE);

                } else if (i == 2) {

                    root.setVisibility(android.view.View.GONE);
                    load.setVisibility(android.view.View.GONE);
                    cd.setVisibility(android.view.View.VISIBLE);
                    add.setVisibility(android.view.View.GONE);

                } else if (i == 3) {

                    root.setVisibility(android.view.View.GONE);
                    load.setVisibility(android.view.View.GONE);
                    cd.setVisibility(android.view.View.GONE);
                    add.setVisibility(android.view.View.VISIBLE);
                    qtgd1.requestFocus();

                } else if (i == 4) {

                    root.setVisibility(android.view.View.GONE);
                    load.setVisibility(android.view.View.VISIBLE);
                    cd.setVisibility(android.view.View.GONE);
                    add.setVisibility(android.view.View.GONE);

                }

            }

        });

    }

    public void wllbview(int a) {

        final int i = a;

        final android.widget.LinearLayout root = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj1));

        final android.widget.LinearLayout load = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj3));

        final android.widget.LinearLayout cd = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj2));

        final android.widget.LinearLayout add = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj4));

        final androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.qtgd1));
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                if (i == 0) {

                    root.setVisibility(android.view.View.VISIBLE);
                    load.setVisibility(android.view.View.GONE);
                    cd.setVisibility(android.view.View.GONE);
                    add.setVisibility(android.view.View.GONE);

                } else if (i == 1) {

                    root.setVisibility(android.view.View.GONE);
                    load.setVisibility(android.view.View.VISIBLE);
                    cd.setVisibility(android.view.View.GONE);
                    add.setVisibility(android.view.View.GONE);

                } else if (i == 2) {

                    root.setVisibility(android.view.View.GONE);
                    load.setVisibility(android.view.View.GONE);
                    cd.setVisibility(android.view.View.VISIBLE);
                    add.setVisibility(android.view.View.GONE);

                } else if (i == 3) {

                    root.setVisibility(android.view.View.GONE);
                    load.setVisibility(android.view.View.GONE);
                    cd.setVisibility(android.view.View.GONE);
                    add.setVisibility(android.view.View.VISIBLE);

                }
                qtgd1.requestFocus();

            }

        });

    }

    public void online() {

        if (!state_online) {

            state_online = true;
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.wb24)).setText(String.valueOf("Online: Loading..."));

                        }

                    });

                    String text = "";

                    String urls = AppPaths.appPath("settings/f10/set2.inf");
                    urls = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (urls));
                    urls = e1.urlUnlockString(urls);

                    if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

                    } else {

                        urls = urls + "/";

                    }

                    String url = urls + "online.php";

                    String st5 = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();
                    st5 = open.cn.awg.pro.core.TextValues.trim(st5);

                    if (java.util.Objects.equals(st5, "null") || java.util.Objects.equals(st5, "") || java.util.Objects.equals(st5, null)) {

                        text = "Online: unknow";

                    } else {

                        text = st5;

                    }

                    final String text_ = text;
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.wb24)).setText(String.valueOf(text_));

                        }

                    });
                    state_online = false;

                }

            });

        }

    }

    public void wllbcd(int i) {

        if (i == 0) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("< 腕上微聊"));
                    wllbview(0);

                }

            });

        } else if (i == 4) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("添加房间"));
                    ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(""));
                    wllbview(3);
                    bjkmode = 1;

                }

            });

        } else if (i == 5) {

            String cachehd1 = AppPaths.appPath("data/ltlb/lbx_title");

            final String bz = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cachehd1));
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("房间备注"));
                    ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(bz));
                    wllbview(3);
                    bjkmode = 2;

                }

            });

        } else {

            wllbview(2);
            wllbcdspq.clearRows();

            String set64 = AppPaths.appPath("settings/i1/set1.inf");

            int[] kj = new int[]{R.id.wb1, R.id.wb2, R.id.wb3, R.id.wb4, R.id.wb5, -1};

            Object[] jk = new Object[]{"", "", "", "", "", 0};

            if (i == 1) {

                jk = new Object[]{"刷新", "", "", "", "", 0};
                wllbcdspq.addRow(kj, jk);
                jk = new Object[]{"添加房间", "", "", "", "", 0};
                wllbcdspq.addRow(kj, jk);
                jk = new Object[]{"新建房间", "", "", "", "", 0};
                wllbcdspq.addRow(kj, jk);

                if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set64)), "true")) {

                    jk = new Object[]{"关闭Online", "", "", "", "", 0};
                    wllbcdspq.addRow(kj, jk);

                } else {

                    jk = new Object[]{"开启Online", "", "", "", "", 0};
                    wllbcdspq.addRow(kj, jk);

                }
                jk = new Object[]{"拒绝《公约》", "", "", "", "", 0};
                wllbcdspq.addRow(kj, jk);
                jk = new Object[]{"设置", "", "", "", "", 0};
                wllbcdspq.addRow(kj, jk);
                jk = new Object[]{"退出", "", "", "", "", 0};
                wllbcdspq.addRow(kj, jk);

            } else if (i == 2) {

                jk = new Object[]{"房间信息", "", "", "", "", 0};
                wllbcdspq.addRow(kj, jk);
                jk = new Object[]{"备注房间", "", "", "", "", 0};
                wllbcdspq.addRow(kj, jk);
                jk = new Object[]{"移除房间", "", "", "", "", 0};
                wllbcdspq.addRow(kj, jk);
                jk = new Object[]{"清除聊天记录", "", "", "", "", 0};
                wllbcdspq.addRow(kj, jk);

            }
            jk = new Object[]{"返回", "back", "", "", "", 1};
            wllbcdspq.addRow(kj, jk);

            String spxx = AppPaths.appPath("settings/a3.inf");
            jk = new Object[]{"", "", "", "", "", -1};
            wllbcdspq.addRow(kj, jk);

            String axc = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (spxx));

            if (java.util.Objects.equals(axc, "1") || java.util.Objects.equals(axc, "3")) {

                jk = new Object[]{"", "", "", "", "", -1};
                wllbcdspq.addRow(kj, jk);
                jk = new Object[]{"", "", "", "", "", -1};
                wllbcdspq.addRow(kj, jk);

            }
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    wllbcdspq.refreshRows();
                    open.cn.awg.pro.core.NativeViewValues.scrollPosition(((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.wllbv7lb2)), "top");
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("菜单"));

                }

            });

        }

    }

    public void dj_wllbcd(Object a, Object b, Object c, Object d) {

        if (java.util.Objects.equals(a, "返回")) {

            wllbcd(0);

        } else if (java.util.Objects.equals(a, "备注房间")) {

            String cachehd = AppPaths.appPath("data/ltlb/lbx_id");

            String id = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cachehd));
            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(""));
            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setHint(String.valueOf("请输入备注"));
            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setInputType(open.cn.awg.pro.core.NativeViewValues.inputType("text"));
            wllbcd(5);

        } else if (java.util.Objects.equals(a, "房间信息")) {

            String cachehd1 = AppPaths.appPath("data/ltlb/lbx_title");

            String bz = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cachehd1));

            String cachehd = AppPaths.appPath("data/ltlb/lbx_id");

            String id = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cachehd));

            String cachehd3 = AppPaths.appPath("data/ltlb/lbx_info");

            String info = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cachehd3));
            info = open.cn.awg.pro.core.TextValues.replace(info, id + "-", "");
            e1.tsk("房间信息", "[房间备注]\n" + bz + "\n\n" + "[房间名称]\n" + info + "\n\n" + "[房间标识]\n" + id);
            wllbcd(0);

        } else if (java.util.Objects.equals(a, "刷新")) {

            wllbcd(0);
            wllbsx();

        } else if (java.util.Objects.equals(a, "添加房间")) {

            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(""));
            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setHint(String.valueOf("请输入房间ID"));
            ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setInputType(open.cn.awg.pro.core.NativeViewValues.inputType("number"));
            wllbcd(4);

        } else if (java.util.Objects.equals(a, "新建房间")) {

            e1.goplu("20002", "新建房间", false);
            wllbcd(0);

        } else if (java.util.Objects.equals(a, "设置")) {

            wllbcd(0);
            open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (WearChatSettingsActivity.class));

        } else if (java.util.Objects.equals(a, "关闭Online")) {

            String set64 = AppPaths.appPath("settings/i1/set1.inf");
            open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (set64), (String) ("false"));
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.wb24)).setVisibility(android.view.View.GONE);

                }

            });
            wllbcd(0);

        } else if (java.util.Objects.equals(a, "开启Online")) {

            String set64 = AppPaths.appPath("settings/i1/set1.inf");
            open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (set64), (String) ("true"));
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.wb24)).setVisibility(android.view.View.VISIBLE);

                }

            });
            wllbcd(0);

        } else if (java.util.Objects.equals(a, "拒绝《公约》")) {

            wllbcd(0);
            e1.awgwl_false();
            open.cn.awg.pro.core.NativeActions.finish(context);

        } else if (java.util.Objects.equals(a, "移除房间")) {

            wllbcd(0);

            String nr = "";

            String cachehd = AppPaths.appPath("data/ltlb/lbx_id");

            String id = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cachehd));

            String sjhc = AppPaths.appPath("data/ltlb/chat/") + id;

            int u = e11.findid_talklist(id);

            String uss = String.valueOf(u);

            boolean b1 = e11.delete_talklist(uss);
            open.cn.awg.pro.core.NativeFiles.delete(context, (String) (sjhc), false);

            if (b1) {

                nr = "移除成功";

            } else {

                nr = "移除失败";

            }
            wllbcd(0);
            wllbsx();
            e1.tsk("提示", nr);

        } else if (java.util.Objects.equals(a, "清除聊天记录")) {

            wllbcd(0);

            String nr = "";

            String cachehd = AppPaths.appPath("data/ltlb/lbx_id");

            String id = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cachehd));

            String sjhc = AppPaths.appPath("data/ltlb/chat/") + id;

            boolean b1 = (open.cn.awg.pro.core.NativeFiles.delete(context, (String) (sjhc), false) > 0);

            if (b1) {

                nr = "清除成功";

            } else {

                nr = "清除失败";

            }
            e1.tsk("提示", nr);
            wllbcd(0);

        } else if (java.util.Objects.equals(a, "退出")) {

            wllbcd(0);
            open.cn.awg.pro.core.NativeActions.finish(context);

        } else {

        }

    }

    public void wllbsx() {

        final String set4 = AppPaths.appPath("settings/f11/set4.inf");

        final String code = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set4));

        final String set1 = AppPaths.appPath("settings/f11/set1.inf");
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("< 腕上微聊"));
                        wllbview(1);

                    }

                });
                online();

                final int[] kj = new int[]{R.id.wb1, R.id.wb2, R.id.wb3};

                String[] jk = new String[]{"", "", ""};
                spq2.clearRows();

                if (open.cn.awg.pro.core.TextValues.contains(code, "Admin:true")) {

                } else {

                    wllbcd(0);

                    String[] uids = new String[]{"110", "404", "600", "405"};

                    for (String uid : uids) {

                        String sjhc = AppPaths.appPath("data/ltlb/chat/") + uid;
                        open.cn.awg.pro.core.NativeFiles.delete(context, (String) (AppPaths.appPath("data/ltlb/showing")), false);

                        int u = e11.findid_talklist(uid);

                        if (u != 0) {

                            String uss = String.valueOf(u);

                            boolean b1 = e11.delete_talklist(uss);
                            open.cn.awg.pro.core.NativeFiles.delete(context, (String) (sjhc), false);

                        }

                    }

                }

                String table = "_id,uid,name,utext";

                Object sqlx = null;

                try (android.database.Cursor sjk15 = (android.database.Cursor) (Object) sjk.rawQuery("SELECT " + table + " from " + "talklist" + (sqlx == null ? "" : " WHERE " + sqlx), null)) {
                    while (sjk15.moveToNext()) {

                        jk = new String[]{sjk15.getString(open.cn.awg.pro.core.TextValues.intValue(2, -1)), sjk15.getString(open.cn.awg.pro.core.TextValues.intValue(1, -1)), sjk15.getString(open.cn.awg.pro.core.TextValues.intValue(3, -1))};
                        spq2.addRow(kj, jk);

                    }
                }
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        spq2.refreshRows();
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

        final String code = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set4));

        String bjknr = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).getText().toString();
        bjknr = open.cn.awg.pro.core.TextValues.trim(bjknr);

        final String bjknrs = bjknr;

        if (java.util.Objects.equals(bjknrs, "")) {

            if (bjkmode == 2) {

                e1.tsk("提示", "备注不能为空");

            } else if (bjkmode == 1) {

                e1.tsk("提示", "房间ID不能为空");

            }

        } else {

            if (bjkmode == 2) {

                String cachehd = AppPaths.appPath("data/ltlb/lbx_id");

                final String id = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cachehd));
                open.cn.awg.pro.core.NativeActions.start(new Thread() {

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

                open.cn.awg.pro.core.NativeActions.start(new Thread() {

                    public void run() {

                        wllbview(1);

                        String nr = "";

                        java.util.regex.Matcher zz = (java.util.regex.Matcher) (Object) open.cn.awg.pro.core.TextValues.matcher(bjknrs, "[0-9]*$", 0);

                        boolean b2 = zz.matches();

                        if (b2) {

                            try {

                                String url = e1.urlUnlockString(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)));

                                if (open.cn.awg.pro.core.TextValues.endsWith(url, "/")) {

                                } else {

                                    url = url + "/";

                                }

                                String idget = url + "chat/" + bjknrs + ".json";

                                String st5 = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(idget), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                                if (java.util.Objects.equals(st5, null)) {

                                    nr = "添加失败";

                                } else {

                                    if (java.util.Objects.equals(bjknrs, "110") || java.util.Objects.equals(bjknrs, "404") || java.util.Objects.equals(bjknrs, "600") || java.util.Objects.equals(bjknrs, "405")) {

                                        if (open.cn.awg.pro.core.TextValues.contains(code, "Admin:true")) {

                                            String b4 = e1.readJson(st5, "room");

                                            boolean b3 = e11.add_talklist(bjknrs, b4, bjknrs + "-" + b4);

                                            if (b3) {

                                                nr = "添加成功";
                                                open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (ltsjhc + bjknrs), (String) (st5));

                                            } else {

                                                nr = "添加失败";

                                            }

                                        } else {

                                            nr = "添加失败";

                                        }

                                    } else {

                                        String b4 = e1.readJson(st5, "room");

                                        boolean b3 = e11.add_talklist(bjknrs, b4, bjknrs + "-" + b4);

                                        if (b3) {

                                            nr = "添加成功";
                                            open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (ltsjhc + bjknrs), (String) (st5));

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
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                lb3.scrollToPosition(spq3.rowCount() - 1);

            }

        });

    }

    public void ltstotop() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                lb3.scrollToPosition(0);

            }

        });

    }

    public void ltsgx() {

        final String uid = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (AppPaths.appPath("data/ltlb/lbx_id")));

        final String sjhc = AppPaths.appPath("data/ltlb/chat/") + uid;

        if (!open.cn.awg.pro.core.NativeFiles.exists(context, (String) (sjhc)) || java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (sjhc)), "")) {

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

            final String uid = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (AppPaths.appPath("data/ltlb/lbx_id")));

            String cachehd = AppPaths.appPath("data/ltlb/lbx_title");

            final String name = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cachehd));

            final String sjhc = AppPaths.appPath("data/ltlb/chat/") + uid;
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf(getTabName(name)));

                }

            });

            final String set3 = AppPaths.appPath("settings/f11/set3.inf");

            final String namer = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set3));

            String urls = "";

            final String set2 = AppPaths.appPath("settings/f11/set2.inf");
            urls = e1.urlUnlockString(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)));

            if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

            } else {

                urls = urls + "/";

            }

            String idget = urls + "chat/" + uid + ".json";

            final String url = idget;

            final String lable = "";
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

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

                    String back = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (sjhc));

                    try {

                        String spxx = AppPaths.appPath("settings/a3.inf");

                        String axc = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (spxx));

                        org.json.JSONObject jo = (org.json.JSONObject) (Object) open.cn.awg.pro.core.JsonValues.parseObject(back);

                        JSONObject json = jo;

                        JSONArray list = open.cn.awg.pro.core.JsonValues.array(json, "data");

                        int i = open.cn.awg.pro.core.JsonValues.length(list);

                        int size = 0;

                        ArrayList arr = new ArrayList();

                        while (size < i) {

                            JSONObject dx = open.cn.awg.pro.core.JsonValues.object(list, size);
                            size++;
                            data = String.valueOf(open.cn.awg.pro.core.JsonValues.value(dx, "data"));
                            name = String.valueOf(open.cn.awg.pro.core.JsonValues.value(dx, "name"));
                            date = String.valueOf(open.cn.awg.pro.core.JsonValues.value(dx, "date"));
                            luid = String.valueOf(open.cn.awg.pro.core.JsonValues.value(dx, "uid"));
                            address = String.valueOf(open.cn.awg.pro.core.JsonValues.value(dx, "address"));
                            address = open.cn.awg.pro.core.TextValues.trim(address);

                            if (java.util.Objects.equals(address, "null") || java.util.Objects.equals(address, "")) {

                                address = "未知";

                            }

                            String jname = "";

                            String jdata = "";
                            jname = name;
                            jdata = data;

                            if (!java.util.Objects.equals(luid, "0") && open.cn.awg.pro.core.NativeFiles.exists(context, (String) (userid)) && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (userid)), "") && java.util.Objects.equals(luid, open.cn.awg.pro.core.NativeFiles.readText(context, (String) (userid)))) {

                                jk = new Object[]{jdata, jname, date, address, -1, address, luid};

                            } else {

                                String at = "@" + namer + " ";

                                String at_ = open.cn.awg.pro.core.TextValues.trim(at);

                                if (open.cn.awg.pro.core.TextValues.contains(jdata, at)) {

                                    String ats = "@我 ";
                                    jdata = open.cn.awg.pro.core.TextValues.replace(jdata, at, ats);
                                    jk = new Object[]{jdata, jname, date, address, 2, address, luid};

                                } else if (java.util.Objects.equals(jdata, at_)) {

                                    String ats = "@我";
                                    jdata = open.cn.awg.pro.core.TextValues.replace(jdata, at_, ats);
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
                        spq3.replaceRows(arr);

                    } catch (Throwable e) {

                        setback = "0";

                    }

                    if (java.util.Objects.equals(back, "")) {

                        if (!ffs) {

                            ltsview(0);
                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.VISIBLE);

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
                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.VISIBLE);

                                }

                            });

                        }

                        try {

                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    spq3.refreshRows();

                                }

                            });

                        } catch (Throwable e) {

                            e1.upload_error(e, "e10.ltsgx1()");

                        }
                        ltjmshowgb();

                    }
                    open.cn.awg.pro.core.NativeFiles.delete(context, (String) (AppPaths.appPath("data/ltlb/disTouch")), false);
                    state_ltsgx1 = false;

                }

            });

        } else {

        }

    }

    public void ltsgx2() {

        if (!state_ltsgx2) {

            state_ltsgx2 = true;

            final String uid = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (AppPaths.appPath("data/ltlb/lbx_id")));

            final String sjhc = AppPaths.appPath("data/ltlb/chat/") + uid;

            final String set4 = AppPaths.appPath("settings/f11/set4.inf");

            final String code = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set4));

            String urls = "";

            final String set2 = AppPaths.appPath("settings/f11/set2.inf");
            urls = e1.urlUnlockString(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)));

            if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

            } else {

                urls = urls + "/";

            }

            final String urls_ = urls;

            String idget = urls + "chat/" + uid + ".json";

            final String url = idget;

            final String lable = "";
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    boolean dontOff = false;
                    open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (AppPaths.appPath("data/ltlb/disTouch")), (String) ("0"));

                    String db_ = "";

                    if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (sjhc))) {

                        db_ = String.valueOf(open.cn.awg.pro.core.NativeFiles.size(context, (String) (sjhc)));

                    } else {

                        db_ = "0";

                    }

                    String ljf = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (AppPaths.appPath("data/assets/a17")));

                    String url_ = urls_ + "re.php?uid=" + uid + ljf + "token=" + db_;

                    String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url_), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                    if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                        ltjmshow("连接服务器失败,请检查网络连接", false);

                    } else {

                        if (java.util.Objects.equals(back, "error")) {

                            String sjhc = AppPaths.appPath("data/ltlb/chat/") + uid;
                            open.cn.awg.pro.core.NativeFiles.delete(context, (String) (AppPaths.appPath("data/ltlb/showing")), false);

                            int u = e11.findid_talklist(uid);

                            if (u != 0) {

                                String uss = String.valueOf(u);

                                boolean b1 = e11.delete_talklist(uss);
                                open.cn.awg.pro.core.NativeFiles.delete(context, (String) (sjhc), false);
                                e1.tsk("提示", "该房间不存在或已被删除\n已为您移除该房间");
                                open.cn.awg.pro.core.NativeActions.finish(context);

                            }

                        } else {

                            if (!open.cn.awg.pro.core.NativeFiles.exists(context, (String) (sjhc))) {

                                String back2 = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                                if (!java.util.Objects.equals(back2, "") || !java.util.Objects.equals(back2, null)) {

                                    if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj4)).getVisibility() == android.view.View.GONE && ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbjcd)).getVisibility() == android.view.View.GONE) {

                                        open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (sjhc), (String) (back2));
                                        ltsgx1(false);
                                        ltjmshowgb();
                                        dontOff = true;

                                    }

                                } else {

                                    ltjmshow("刷新失败,请重试", false);
                                    ltsview(0);

                                }

                            } else {

                                String db = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (sjhc));

                                if (java.util.Objects.equals(back, db)) {

                                } else {

                                    if (java.util.Objects.equals(back, "true")) {

                                        String back2 = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                                        if (!java.util.Objects.equals(back2, "") || !java.util.Objects.equals(back2, null)) {

                                            if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj4)).getVisibility() == android.view.View.GONE && ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbjcd)).getVisibility() == android.view.View.GONE) {

                                                open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (sjhc), (String) (back2));
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

                    if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.i2xxbj1)).getVisibility() == android.view.View.VISIBLE && ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbjcd)).getVisibility() == android.view.View.GONE && !dontOff) {

                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                ltsview(0);
                                ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.VISIBLE);

                            }

                        });

                    }
                    open.cn.awg.pro.core.NativeFiles.delete(context, (String) (AppPaths.appPath("data/ltlb/disTouch")), false);
                    state_ltsgx2 = false;

                }

            });

        } else {

        }

    }

    public void ltsgx3() {

        if (!state_ltsgx3) {

            state_ltsgx3 = true;

            final String uid = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (AppPaths.appPath("data/ltlb/lbx_id")));

            final String sjhc = AppPaths.appPath("data/ltlb/chat/") + uid;

            final String set4 = AppPaths.appPath("settings/f11/set4.inf");

            final String code = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set4));

            String urls = "";

            final String set2 = AppPaths.appPath("settings/f11/set2.inf");
            urls = e1.urlUnlockString(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)));

            if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

            } else {

                urls = urls + "/";

            }

            final String urls_ = urls;

            String idget = urls + "chat/" + uid + ".json";

            final String url = idget;

            final String lable = "";
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                        }

                    });
                    ltsview(1);
                    open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (AppPaths.appPath("data/ltlb/disTouch")), (String) ("0"));

                    String db_ = "";

                    if (open.cn.awg.pro.core.NativeFiles.exists(context, (String) (sjhc))) {

                        db_ = String.valueOf(open.cn.awg.pro.core.NativeFiles.size(context, (String) (sjhc)));

                    } else {

                        db_ = "0";

                    }

                    String ljf = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (AppPaths.appPath("data/assets/a17")));

                    String url_ = urls_ + "re.php?uid=" + uid + ljf + "token=" + db_;

                    String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url_), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                    if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                        ltjmshow("连接服务器失败,请检查网络连接", false);
                        open.cn.awg.pro.core.NativeActions.sleep(750);
                        ltsview(0);
                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.VISIBLE);

                            }

                        });

                    } else {

                        if (java.util.Objects.equals(back, "error")) {

                            ltjmshow("刷新失败,房间不存在或已被删除", false);
                            ltsview(0);

                        } else {

                            if (!open.cn.awg.pro.core.NativeFiles.exists(context, (String) (sjhc))) {

                                String back2 = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                                if (!java.util.Objects.equals(back2, "") || !java.util.Objects.equals(back2, null)) {

                                    if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj4)).getVisibility() == android.view.View.GONE && ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbjcd)).getVisibility() == android.view.View.GONE) {

                                        open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (sjhc), (String) (back2));
                                        ltsgx1(false);

                                    }

                                } else {

                                    ltjmshow("刷新失败,请重试", false);
                                    ltsgx1(false);

                                }

                            } else {

                                String db = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (sjhc));

                                if (java.util.Objects.equals(back, db)) {

                                } else {

                                    if (java.util.Objects.equals(back, "true")) {

                                        String back2 = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                                        if (!java.util.Objects.equals(back2, "") || !java.util.Objects.equals(back2, null)) {

                                            if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj4)).getVisibility() == android.view.View.GONE && ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbjcd)).getVisibility() == android.view.View.GONE) {

                                                open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (sjhc), (String) (back2));
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

                    if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.ltssxxxbj3)).getVisibility() == android.view.View.GONE && ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj4)).getVisibility() == android.view.View.GONE && ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbjcd)).getVisibility() == android.view.View.GONE) {

                        ltsview(0);
                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.VISIBLE);

                            }

                        });

                    }
                    open.cn.awg.pro.core.NativeFiles.delete(context, (String) (AppPaths.appPath("data/ltlb/disTouch")), false);
                    state_ltsgx3 = false;

                }

            });

        } else {

        }

    }

    public void ltspost(int i) {

        String cachehd = AppPaths.appPath("data/ltlb/lbx_title");

        final String name = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cachehd));

        if (i == 0) {

            ltsview(0);
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf(getTabName(name)));

                }

            });

        } else {

            if (!e1.islogin()) {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (TypheyeAccountActivity.class));
                        e1.tsk("提示", "发布消息需登录Typheye账户");

                    }

                });

            } else {

                ltsview(3);
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        String path = AppPaths.appPath("cache/chat/upload/path");
                        open.cn.awg.pro.core.NativeFiles.delete(context, (String) (path), false);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("发送信息"));
                        ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setHint(String.valueOf("请输入信息"));
                        ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(""));
                        ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.an_upload)).setHint(String.valueOf("选择文件"));
                        ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.an_upload)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.an_disupload)).setVisibility(android.view.View.GONE);
                        ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.ltsup_xlcd1)).setEnabled(true);
                        ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.ltsup_xlcd2)).setEnabled(true);
                        ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.ltsup_xlcd1)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.ltsup_xlcd2)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.CheckBox) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.dx_chat_upload)).setEnabled(true);
                        ((android.widget.CheckBox) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.dx_chat_upload)).setChecked(java.lang.Boolean.TRUE.equals(false));

                    }

                });

            }

        }

    }

    public void ltspostat(Object nam) {

        if (!e1.islogin()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (TypheyeAccountActivity.class));
                    e1.tsk("提示", "发布消息需登录腕管Pro账户");

                }

            });

        } else {

            final String name = "@" + nam + " ";
            ltsview(3);
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    String path = AppPaths.appPath("cache/chat/upload/path");
                    open.cn.awg.pro.core.NativeFiles.delete(context, (String) (path), false);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("发送信息"));
                    ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setHint(String.valueOf("请输入信息"));
                    ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(name));
                    ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).setSelection(name.length());
                    ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.an_upload)).setHint(String.valueOf("选择文件"));
                    ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.an_upload)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.an_disupload)).setVisibility(android.view.View.GONE);
                    ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.ltsup_xlcd1)).setEnabled(true);
                    ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.ltsup_xlcd2)).setEnabled(true);
                    ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.ltsup_xlcd1)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.ltsup_xlcd2)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.CheckBox) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.dx_chat_upload)).setEnabled(true);
                    ((android.widget.CheckBox) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.dx_chat_upload)).setChecked(java.lang.Boolean.TRUE.equals(false));

                }

            });

        }

    }

    public void post() {

        if (!state_post) {

            state_post = true;

            if (!e1.islogin()) {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        open.cn.awg.pro.core.NativeActions.finish(context);
                        open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (TypheyeAccountActivity.class));
                        e1.tsk("提示", "发布消息需登录腕管Pro账户");
                        state_post = false;

                    }

                });

            } else {

                String xxs = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.bjk1)).getText().toString();
                xxs = open.cn.awg.pro.core.TextValues.trim(xxs);

                String urls = "";

                String uid = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (AppPaths.appPath("data/ltlb/lbx_id")));

                final String set2 = AppPaths.appPath("settings/f11/set2.inf");

                final String set3 = AppPaths.appPath("settings/f11/set3.inf");

                final String set4 = AppPaths.appPath("settings/f11/set4.inf");
                urls = e1.urlUnlockString(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)));

                String namer = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set3));

                if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

                } else {

                    urls = urls + "/";

                }

                String ljf = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (AppPaths.appPath("data/assets/a17")));

                String idget = urls + "class/api.php?type=release" + ljf + "id=" + uid;

                final String name = namer;

                final String xx = xxs;

                final String url = idget;

                final String code = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set4));

                final android.widget.CheckBox dx = ((android.widget.CheckBox) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.dx_chat_upload));

                final android.widget.Button an = ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.an_upload));

                String cachehd = AppPaths.appPath("data/ltlb/lbx_title");

                final String title = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cachehd));
                open.cn.awg.pro.core.NativeActions.start(new Thread() {

                    public void run() {

                        boolean ppp = false;

                        if (java.util.Objects.equals(xx, "")) {

                            ltsview(3);
                            e1.tsk("提示", "消息不能为空");

                        } else {

                            if (dx.isChecked()) {

                                if (java.util.Objects.equals(an.getHint().toString(), "上传中")) {

                                    e1.tsk("提示", "请耐心等待文件上传完毕");
                                    ltsview(3);

                                } else if (java.util.Objects.equals(an.getHint().toString(), "选择文件") || java.util.Objects.equals(an.getHint().toString(), "已选择•开始上传")) {

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

                                if (dx.isChecked()) {

                                    if (java.util.Objects.equals(an.getHint().toString(), "上传成功")) {

                                        String u_ = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (AppPaths.appPath("cache/chat/upload/info")));

                                        String t_ = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (AppPaths.appPath("cache/chat/upload/type")));

                                        if (java.util.Objects.equals(t_, "0")) {

                                            _xx_ = _xx_ + "[PHOTO]" + u_ + "[P:END]";

                                        } else if (java.util.Objects.equals(t_, "1")) {

                                            _xx_ = _xx_ + "[AUDIO]" + u_ + "[A:END]";

                                        } else if (java.util.Objects.equals(t_, "2")) {

                                            _xx_ = _xx_ + "[VIDEO]" + u_ + "[V:END]";

                                        } else if (java.util.Objects.equals(t_, "3")) {

                                            _xx_ = _xx_ + "[OTHER]" + u_ + "[O:END]";

                                        }

                                    }

                                }

                                String xx_ = _xx_;
                                ltsview(0);
                                ltjmshow2("正在发送中...");

                                String cachehd = AppPaths.appPath("data/ltlb/lbx_title");

                                final String name = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cachehd));
                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf(getTabName(name)));
                                        ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.VISIBLE);

                                    }

                                });

                                if (e1.yz()) {

                                    String setback = "";

                                    String[] wlhq1 = new String[]{"name=" + name_, "value=" + xx_, "code=" + code, "uid=" + e1.awgwl_getuid()};

                                    String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (wlhq1), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                                    if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                                        setback = "0";

                                    } else {

                                        if (java.util.Objects.equals(setback, "0")) {

                                            setback = "0";

                                        } else {

                                            setback = back;

                                        }

                                    }

                                    if (java.util.Objects.equals(setback, "0")) {

                                        ltsview(0);
                                        ltjmshowgb();
                                        e1.tsk("提示", "发送失败(-1)");
                                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                            public void run() {

                                                ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.VISIBLE);
                                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf(getTabName(title)));

                                            }

                                        });

                                    } else {

                                        ltsview(1);

                                        if (java.util.Objects.equals(setback, "true")) {

                                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                                public void run() {

                                                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.VISIBLE);
                                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf(getTabName(title)));

                                                }

                                            });
                                            ltjmshow("发送成功", true);
                                            ltsview(0);

                                        } else {

                                            e1.tsk("提示", "发送失败\n" + setback);
                                            ltsview(0);
                                            ltjmshowgb();
                                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                                public void run() {

                                                    ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.VISIBLE);
                                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf(getTabName(title)));

                                                }

                                            });

                                        }

                                    }

                                } else {

                                    ltsview(0);
                                    e1.tsk("提示", "发送失败(-2)");
                                    ltjmshowgb();
                                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                        public void run() {

                                            ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.kp7)).setVisibility(android.view.View.VISIBLE);
                                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf(getTabName(title)));

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
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj3)).setVisibility(android.view.View.GONE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj5)).setVisibility(android.view.View.VISIBLE);

                }

            });

            Object xxs = c;
            xxs = open.cn.awg.pro.core.TextValues.trim(xxs);

            String urls = "";

            String uid = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (AppPaths.appPath("data/ltlb/lbx_id")));

            final String set2 = AppPaths.appPath("settings/f11/set2.inf");

            final String set3 = AppPaths.appPath("settings/f11/set3.inf");
            urls = e1.urlUnlockString(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set2)));

            String namer = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set3));

            if (open.cn.awg.pro.core.TextValues.endsWith(urls, "/")) {

            } else {

                urls = urls + "/";

            }

            String ljf = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (AppPaths.appPath("data/assets/a17")));

            String idget = urls + "class/api.php?type=release" + ljf + "id=110";

            String j = uid;

            if (java.util.Objects.equals(xxs, "")) {

                e1.tsk("提示", "举报理由不能为空");
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj3)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj5)).setVisibility(android.view.View.GONE);

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
                open.cn.awg.pro.core.NativeActions.start(new Thread() {

                    public void run() {

                        if (e1.yz()) {

                            String setback = "";

                            String[] wlhq1 = new String[]{"name=" + namex, "value=" + xx, "code=baseTextUpload"};

                            String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (wlhq1), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                            if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                                setback = "0";

                            } else {

                                if (java.util.Objects.equals(setback, "0")) {

                                    setback = "0";

                                } else {

                                    setback = back;

                                }

                            }

                            if (java.util.Objects.equals(setback, "0")) {

                                e1.tsk("提示", "举报失败(-1)");
                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj3)).setVisibility(android.view.View.VISIBLE);
                                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj5)).setVisibility(android.view.View.GONE);

                                    }

                                });

                            } else {

                                if (java.util.Objects.equals(setback, "true")) {

                                    e1.tsk("提示", "举报成功");
                                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                        public void run() {

                                            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj3)).setVisibility(android.view.View.VISIBLE);
                                            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj5)).setVisibility(android.view.View.GONE);

                                        }

                                    });
                                    open.cn.awg.pro.core.NativeActions.finish(context);

                                } else {

                                    e1.tsk("提示", "举报失败\n" + setback);
                                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                        public void run() {

                                            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj3)).setVisibility(android.view.View.VISIBLE);
                                            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj5)).setVisibility(android.view.View.GONE);

                                        }

                                    });

                                }

                            }

                        } else {

                            e1.tsk("提示", "举报失败(-2)");
                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj3)).setVisibility(android.view.View.VISIBLE);
                                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj5)).setVisibility(android.view.View.GONE);

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

            color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextTrue);

        } else {

            color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorTextFalse);

        }

        final String bj = color;
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                android.widget.TextView wb = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.wb3));
                wb.setText(String.valueOf("[提示] " + bt));
                open.cn.awg.pro.core.NativeViewValues.background(wb, bj);
                wb.setVisibility(android.view.View.VISIBLE);

            }

        });

    }

    public void ltjmshow2(Object a) {

        final Object bt = a;

        String color = open.cn.awg.pro.core.NativeViewValues.colorResourceString(context, R.color.colorAccent);

        final String bj = color;
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                android.widget.TextView wb = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.wb3));
                wb.setText(String.valueOf("[提示] " + bt));
                open.cn.awg.pro.core.NativeViewValues.background(wb, bj);
                wb.setVisibility(android.view.View.VISIBLE);

            }

        });

    }

    public void ltjmshowgb() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                android.widget.TextView wb = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.wb3));
                wb.setVisibility(android.view.View.GONE);

            }

        });

    }

    public void upload(Object a, int types) {

        if (!state_upload) {

            state_upload = true;

            final int type = types;

            final String uu = AppPaths.appPath("settings/other/0xO5HJ7/url");

            final String path_ = open.cn.awg.pro.core.NativeFiles.resolvePath(context, (String) (a));

            final String[] path = new String[]{path_};

            final android.widget.CheckBox dx = ((android.widget.CheckBox) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.dx_chat_upload));

            final android.widget.Button an = ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.an_upload));

            final android.widget.ProgressBar jdt2 = ((android.widget.ProgressBar) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.jdt2));

            final android.widget.Spinner ltsup_xlcd1 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.ltsup_xlcd1));

            final android.widget.Spinner ltsup_xlcd2 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.ltsup_xlcd2));

            final android.widget.Button an2 = ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.an_disupload));
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    upload_back = "";
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            jdt2.setProgress(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                            jdt2.setVisibility(android.view.View.GONE);
                            an2.setVisibility(android.view.View.GONE);
                            an.setVisibility(android.view.View.GONE);
                            an.setHint(String.valueOf("上传中"));
                            dx.setEnabled(false);
                            ltsup_xlcd1.setEnabled(false);
                            ltsup_xlcd1.setVisibility(android.view.View.GONE);
                            ltsup_xlcd2.setEnabled(false);
                            ltsup_xlcd2.setVisibility(android.view.View.GONE);

                        }

                    });

                    if (e1.yz()) {

                        if (java.util.Objects.equals(path_, "") || !open.cn.awg.pro.core.NativeFiles.exists(context, (String) (path_))) {

                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    jdt2.setVisibility(android.view.View.GONE);
                                    an.setVisibility(android.view.View.VISIBLE);
                                    an2.setVisibility(android.view.View.GONE);
                                    an.setHint(String.valueOf("选择文件"));
                                    dx.setEnabled(true);
                                    ltsup_xlcd1.setEnabled(true);
                                    ltsup_xlcd1.setVisibility(android.view.View.VISIBLE);
                                    ltsup_xlcd2.setEnabled(true);
                                    ltsup_xlcd2.setVisibility(android.view.View.VISIBLE);

                                }

                            });
                            e1.tsk("提示", "文件路径无效");

                        } else {

                            String urls = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (uu));
                            urls = e1.urlUnlockString(urls);

                            final String url = urls;

                            String fn__ = "null";

                            String[] fn_ = e1.fileinfo("/", path[0]);

                            if (java.util.Objects.equals(fn_[0], "true")) {

                                fn__ = fn_[3];

                            }

                            final String file_name = fn__;

                            final long file_size = open.cn.awg.pro.core.NativeFiles.size(context, (String) (path[0]));

                            String[] p = new String[]{"name=" + fn__, "size=" + open.cn.awg.pro.core.NativeFiles.size(context, (String) (path[0]))};
                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    an.setHint(String.valueOf("上传中"));
                                    an.setVisibility(android.view.View.GONE);
                                    an2.setVisibility(android.view.View.GONE);
                                    dx.setEnabled(false);
                                    ltsup_xlcd1.setEnabled(false);
                                    ltsup_xlcd1.setVisibility(android.view.View.GONE);
                                    ltsup_xlcd2.setEnabled(false);
                                    ltsup_xlcd2.setVisibility(android.view.View.GONE);
                                    jdt2.setProgress(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                                    jdt2.setVisibility(android.view.View.VISIBLE);

                                    try {

                                        HttpRequest.TIME_OUT_DURATION = 300;
                                        HttpRequest.build(context, url)
                                                .addParameter("name", file_name)
                                                .addParameter("size", file_size)
                                                .addParameter("file", open.cn.awg.pro.core.NativeFiles.file(context, (String) (path_)))
                                                .setUploadProgressListener(new UploadProgressListener() {
                                                    @Override
                                                    public void onUpload(float percentage, long current, long total, boolean done) {

                                                        jdt2.setMax(open.cn.awg.pro.core.TextValues.intValue(total, -1));
                                                        jdt2.setProgress(open.cn.awg.pro.core.TextValues.intValue(current, -1));
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

                                        jdt2.setVisibility(android.view.View.GONE);
                                        an.setVisibility(android.view.View.VISIBLE);
                                        an2.setVisibility(android.view.View.GONE);
                                        an.setHint(String.valueOf("选择文件"));
                                        dx.setEnabled(true);
                                        ltsup_xlcd1.setEnabled(true);
                                        ltsup_xlcd1.setVisibility(android.view.View.VISIBLE);
                                        ltsup_xlcd2.setEnabled(true);
                                        ltsup_xlcd2.setVisibility(android.view.View.VISIBLE);
                                        e1.tsk("提示", "上传失败(-1)");

                                    }

                                }

                            });

                        }

                    } else {

                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                jdt2.setVisibility(android.view.View.GONE);
                                an.setVisibility(android.view.View.VISIBLE);
                                an2.setVisibility(android.view.View.GONE);
                                an.setHint(String.valueOf("选择文件"));
                                dx.setEnabled(true);
                                ltsup_xlcd1.setEnabled(true);
                                ltsup_xlcd1.setVisibility(android.view.View.VISIBLE);
                                ltsup_xlcd2.setEnabled(true);
                                ltsup_xlcd2.setVisibility(android.view.View.VISIBLE);

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

        final android.widget.CheckBox dx = ((android.widget.CheckBox) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.dx_chat_upload));

        final android.widget.Button an = ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.an_upload));

        final android.widget.ProgressBar jdt2 = ((android.widget.ProgressBar) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.jdt2));

        final android.widget.Spinner ltsup_xlcd1 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.ltsup_xlcd1));

        final android.widget.Spinner ltsup_xlcd2 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.ltsup_xlcd2));

        final android.widget.Button an2 = ((android.widget.Button) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.an_disupload));

        Object back = upload_back;

        if (!java.util.Objects.equals(open.cn.awg.pro.core.TextValues.string(error), null)) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    jdt2.setVisibility(android.view.View.GONE);
                    an.setHint(String.valueOf("选择文件"));
                    an.setVisibility(android.view.View.VISIBLE);
                    an2.setVisibility(android.view.View.GONE);
                    dx.setEnabled(true);
                    ltsup_xlcd1.setEnabled(true);
                    ltsup_xlcd1.setVisibility(android.view.View.VISIBLE);
                    ltsup_xlcd2.setEnabled(true);
                    ltsup_xlcd2.setVisibility(android.view.View.VISIBLE);

                }

            });
            e1.tsk("提示", "上传失败\n" + open.cn.awg.pro.core.TextValues.string(error));

        } else {

            if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        jdt2.setVisibility(android.view.View.GONE);
                        an.setVisibility(android.view.View.VISIBLE);
                        an.setHint(String.valueOf("选择文件"));
                        an2.setVisibility(android.view.View.GONE);
                        dx.setEnabled(true);
                        ltsup_xlcd1.setEnabled(true);
                        ltsup_xlcd1.setVisibility(android.view.View.VISIBLE);
                        ltsup_xlcd2.setEnabled(true);
                        ltsup_xlcd2.setVisibility(android.view.View.VISIBLE);
                        e1.tsk("提示", "上传失败\n连接服务器超时(-1)");

                    }

                });

            } else {

                if (open.cn.awg.pro.core.TextValues.contains(back, "https://") || open.cn.awg.pro.core.TextValues.contains(back, "http://")) {

                    if (open.cn.awg.pro.core.TextValues.contains(back, "https://")) {

                        back = "https://" + open.cn.awg.pro.core.TextValues.slice(back, "https://", null);
                        back = open.cn.awg.pro.core.TextValues.trim(back);

                    } else if (open.cn.awg.pro.core.TextValues.contains(back, "http://")) {

                        back = "http://" + open.cn.awg.pro.core.TextValues.slice(back, "http://", null);
                        back = open.cn.awg.pro.core.TextValues.trim(back);

                    }
                    open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (AppPaths.appPath("cache/chat/upload/info")), (String) (back));
                    open.cn.awg.pro.core.NativeFiles.writeText(context, (String) (AppPaths.appPath("cache/chat/upload/type")), (String) (open.cn.awg.pro.core.TextValues.string(type)));
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            jdt2.setVisibility(android.view.View.GONE);
                            an.setVisibility(android.view.View.VISIBLE);
                            an.setHint(String.valueOf("上传成功"));
                            an2.setVisibility(android.view.View.VISIBLE);
                            dx.setEnabled(false);
                            ltsup_xlcd1.setEnabled(false);
                            ltsup_xlcd1.setVisibility(android.view.View.GONE);
                            ltsup_xlcd2.setEnabled(false);
                            ltsup_xlcd2.setVisibility(android.view.View.GONE);

                        }

                    });

                } else {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            jdt2.setVisibility(android.view.View.GONE);
                            an.setVisibility(android.view.View.VISIBLE);
                            an2.setVisibility(android.view.View.GONE);
                            an.setHint(String.valueOf("选择文件"));
                            dx.setEnabled(true);
                            ltsup_xlcd1.setEnabled(true);
                            ltsup_xlcd1.setVisibility(android.view.View.VISIBLE);
                            ltsup_xlcd2.setEnabled(true);
                            ltsup_xlcd2.setVisibility(android.view.View.VISIBLE);

                        }

                    });
                    e1.tsk("提示", "上传失败\n" + back);

                }

            }

        }

    }

    public void ltspostat(Activity activity, Object target) { new WearChatRuntimeBridge(activity).ltspostat(target); }
    public void wllbcd(Activity activity, int item) { new WearChatRuntimeBridge(activity).wllbcd(item); }
    public void dj_wllbcd(Activity activity, Object a, Object b, Object c, Object d) { new WearChatRuntimeBridge(activity).dj_wllbcd(a, b, c, d); }
}
