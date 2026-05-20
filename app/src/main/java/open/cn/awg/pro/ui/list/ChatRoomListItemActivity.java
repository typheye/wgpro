/**
 ******************************************************************************
 * @file    ChatRoomListItemActivity.java
 * @author  Typheye
 * @brief   Opens WearChat rooms from a chat-room list item.
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
package open.cn.awg.pro.ui.list;

import i.app.iActivity;
import open.cn.awg.pro.chat.ChatRoomActivity;
import open.cn.awg.pro.chat.WearChatRuntimeBridge;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.data.LocalDatabaseBridge;


public class ChatRoomListItemActivity extends iActivity {

    public final ChatRoomListItemActivity lei = this, 类 = this;
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_aaeebbd136 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_aaeebbd136(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_aaeebbd136 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_aaeebbd136(vw);
        }

    };

    private void $_onClick_aaeebbd136(android.view.View vw) {

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        java.lang.Object a = lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

        java.lang.Object b = lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

        open.cn.awg.pro.data.LocalDatabaseBridge e11 = new LocalDatabaseBridge(_APPINFO);

        open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
        e11.setup_talklist();

        if (e11.findid_talklist(a) != 0) {

            java.lang.String cachehd = "/data/user/0/open.cn.awg.pro/data/ltlb/lbx_id";
            wj.xrwb(cachehd, a);

            java.lang.String cachehd2 = "/data/user/0/open.cn.awg.pro/data/ltlb/lbx_title";
            wj.xrwb(cachehd2, b);

            java.lang.String[] name = new java.lang.String[]{"uid"};

            java.lang.Object[] value = new java.lang.Object[]{a};
            gj.tz(ChatRoomActivity.class, name, value);

        } else {

            e1.tsk("提示", "该聊天室不存在,请刷新列表后重试");

        }

    }

    private boolean $_onLongClick_aaeebbd136(android.view.View vw) {

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        java.lang.Object a = lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

        java.lang.Object b = lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

        java.lang.Object c = lb.lbcfsj(open.cn.awg.pro.R.id.wb3);

        open.cn.awg.pro.chat.WearChatRuntimeBridge e10 = new WearChatRuntimeBridge(_APPINFO);
        e10.wllbcd(2);

        java.lang.String cachehd = "/data/user/0/open.cn.awg.pro/data/ltlb/lbx_id";
        wj.xrwb(cachehd, a);

        java.lang.String cachehd2 = "/data/user/0/open.cn.awg.pro/data/ltlb/lbx_title";
        wj.xrwb(cachehd2, b);

        java.lang.String cachehd3 = "/data/user/0/open.cn.awg.pro/data/ltlb/lbx_info";
        wj.xrwb(cachehd3, c);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.RelativeLayout i25520aa38a = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.c10_v);

        android.widget.RelativeLayout aaeebbd136 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj1);
        aaeebbd136.setOnClickListener($_on_setOnClickListener_aaeebbd136);
        aaeebbd136.setOnLongClickListener($_on_setOnLongClickListener_aaeebbd136);

        __layoutIsLoaded(ay, vw);
    }
}
