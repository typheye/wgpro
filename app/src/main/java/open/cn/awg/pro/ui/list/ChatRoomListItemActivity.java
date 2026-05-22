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

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;

import open.cn.awg.pro.chat.ChatRoomActivity;
import open.cn.awg.pro.chat.WearChatRuntimeBridge;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.data.LocalDatabaseBridge;
import open.cn.awg.pro.R;

public class ChatRoomListItemActivity extends BaseAwgActivity {

    public final ChatRoomListItemActivity lei = this, 类 = this;
    private final View.OnLongClickListener xdbj1LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj1LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj1Click(vw);
        }

    };

    private void onXdbj1Click(View vw) {

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        Object a = lb.lbcfsj(R.id.wb2);

        Object b = lb.lbcfsj(R.id.wb1);

        LocalDatabaseBridge e11 = new LocalDatabaseBridge(_APPINFO);

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
        e11.setup_talklist();

        if (e11.findid_talklist(a) != 0) {

            String cachehd = AppPaths.appPath("data/ltlb/lbx_id");
            wj.xrwb(cachehd, a);

            String cachehd2 = AppPaths.appPath("data/ltlb/lbx_title");
            wj.xrwb(cachehd2, b);

            String[] name = new String[]{"uid"};

            Object[] value = new Object[]{a};
            gj.tz(ChatRoomActivity.class, name, value);

        } else {

            e1.tsk("提示", "该聊天室不存在,请刷新列表后重试");

        }

    }

    private boolean onXdbj1LongClick(View vw) {

        i.runlibrary.app.sj$lb lb = sj.lb(vw);

        Object a = lb.lbcfsj(R.id.wb2);

        Object b = lb.lbcfsj(R.id.wb1);

        Object c = lb.lbcfsj(R.id.wb3);

        WearChatRuntimeBridge e10 = new WearChatRuntimeBridge(_APPINFO);
        e10.wllbcd(2);

        String cachehd = AppPaths.appPath("data/ltlb/lbx_id");
        wj.xrwb(cachehd, a);

        String cachehd2 = AppPaths.appPath("data/ltlb/lbx_title");
        wj.xrwb(cachehd2, b);

        String cachehd3 = AppPaths.appPath("data/ltlb/lbx_info");
        wj.xrwb(cachehd3, c);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        RelativeLayout i25520aa38a = (RelativeLayout) findViewById(ay, vw, R.id.chat_room_list_item_root);

        RelativeLayout aaeebbd136 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj1);
        aaeebbd136.setOnClickListener(xdbj1ClickListener);
        aaeebbd136.setOnLongClickListener(xdbj1LongClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
