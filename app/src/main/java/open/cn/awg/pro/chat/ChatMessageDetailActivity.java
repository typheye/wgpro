/**
 ******************************************************************************
 * @file    ChatMessageDetailActivity.java
 * @author  Typheye
 * @brief   Displays and reports WearChat message details.
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

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class ChatMessageDetailActivity extends BaseAwgActivity {

    public final ChatMessageDetailActivity lei = this, 类 = this;
    public String name = "";
    public String data = "";
    public String time = "";
    public String address = "";
    public String luid = "";
    public AppRuntimeBridge e1;
    public WearChatRuntimeBridge e10;
    private final View.OnClickListener an1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn1Click(vw);
        }

    };
    private final View.OnTouchListener bjk1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjk1Touch(vw, me);
        }

    };
    private final View.OnClickListener an2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn2Click(vw);
        }

    };
    private final View.OnClickListener wb12ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb12Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj3LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj3LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj3Click(vw);
        }

    };
    private final View.OnClickListener wb2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb2Click(vw);
        }

    };
    private final View.OnClickListener wb21ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb21Click(vw);
        }

    };
    private final View.OnClickListener wb7ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb7Click(vw);
        }

    };
    private final View.OnClickListener wb22ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb22Click(vw);
        }

    };
    private final View.OnLongClickListener tx1LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onTx1LongClick(vw);
        }

    };
    private final View.OnClickListener wb3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb3Click(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        e1 = new AppRuntimeBridge(this);
        e10 = new WearChatRuntimeBridge(this);
        setContentView(R.layout.chat_message_detail);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        name = ChatMessageDetailActivity.this.getIntent().getStringExtra(String.valueOf("name"));
        data = ChatMessageDetailActivity.this.getIntent().getStringExtra(String.valueOf("data"));
        time = ChatMessageDetailActivity.this.getIntent().getStringExtra(String.valueOf("time"));
        address = ChatMessageDetailActivity.this.getIntent().getStringExtra(String.valueOf("address"));
        luid = ChatMessageDetailActivity.this.getIntent().getStringExtra(String.valueOf("luid"));
        luid = open.cn.awg.pro.core.TextValues.trim(luid);

        if (time.length() < 19) {

            time = time + ":00";

        }
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.wb2)).setText(String.valueOf(name));

        if (open.cn.awg.pro.core.TextValues.contains(data, "[PHOTO]")) {

            data = open.cn.awg.pro.core.TextValues.slice(data, null, "[PHOTO]");
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.wb12)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

        } else if (open.cn.awg.pro.core.TextValues.contains(data, "[AUDIO]")) {

            data = open.cn.awg.pro.core.TextValues.slice(data, null, "[AUDIO]");
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.wb12)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

        } else if (open.cn.awg.pro.core.TextValues.contains(data, "[VIDEO]")) {

            data = open.cn.awg.pro.core.TextValues.slice(data, null, "[VIDEO]");
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.wb12)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

        } else if (open.cn.awg.pro.core.TextValues.contains(data, "[OTHER]")) {

            data = open.cn.awg.pro.core.TextValues.slice(data, null, "[OTHER]");
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.wb12)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

        }

        if (java.util.Objects.equals(name, "台风眼Typheye") || java.util.Objects.equals(name, "系统消息")) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.wb7)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.wb7)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

        }

        if (java.util.Objects.equals(luid, "w0") || java.util.Objects.equals(luid, "") || java.util.Objects.equals(luid, "0") || java.util.Objects.equals(luid, null) || java.util.Objects.equals(luid, "null")) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.wb22)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.wb22)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

        }

        if (java.util.Objects.equals(address, "未知")) {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.wb21)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

        } else {

            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.wb21)).setText(String.valueOf(address));
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.wb21)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

        }
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.wb1)).setText(String.valueOf(data));
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf(time));

        String wb = open.cn.awg.pro.core.TextValues.slice(name, 0, 1);
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.wb6)).setText(String.valueOf(wb));

        final String set3 = AppPaths.appPath("settings/f11/set3.inf");

        String uid = open.cn.awg.pro.core.NativeFiles.readText(ChatMessageDetailActivity.this, (String) (AppPaths.appPath("data/ltlb/lbx_id")));

        String namer = open.cn.awg.pro.core.NativeFiles.readText(ChatMessageDetailActivity.this, (String) (set3));

        final String userid = AppPaths.appPath("settings/account/user");

        if (!java.util.Objects.equals(luid, "0") && open.cn.awg.pro.core.NativeFiles.exists(ChatMessageDetailActivity.this, (String) (userid)) && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ChatMessageDetailActivity.this, (String) (userid)), "") && java.util.Objects.equals(luid, open.cn.awg.pro.core.NativeFiles.readText(ChatMessageDetailActivity.this, (String) (userid)))) {

            ((androidx.cardview.widget.CardView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.kp1)).setCardBackgroundColor(open.cn.awg.pro.core.NativeViewValues.color(open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorTextTrue2)));
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.wb1)).setTextColor(open.cn.awg.pro.core.NativeViewValues.color(open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorBlack)));

        }

        if (!java.util.Objects.equals(luid, "0") && open.cn.awg.pro.core.NativeFiles.exists(ChatMessageDetailActivity.this, (String) (userid)) && !java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(ChatMessageDetailActivity.this, (String) (userid)), "") && java.util.Objects.equals(luid, open.cn.awg.pro.core.NativeFiles.readText(ChatMessageDetailActivity.this, (String) (userid))) || java.util.Objects.equals(uid, "110") || java.util.Objects.equals(uid, "404")) {

            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.xxbj6)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.chat_message_detail_root, R.id.xdbj1, R.id.title_bar);

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
            applyWindowModeFromSettings();

    }

    public void onRestart() {
        super.onRestart();
            applyWindowModeFromSettings();

    }

    public void onStart() {
        super.onStart();
            applyWindowModeFromSettings();

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.qtgd1));

        qtgd1.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View vw, MotionEvent me) {
                if (me.getAction() == MotionEvent.ACTION_SCROLL && me.isFromSource(InputDeviceCompat.SOURCE_ROTARY_ENCODER)) {

                    float delta = -me.getAxisValue(MotionEventCompat.AXIS_SCROLL) *
                            ViewConfigurationCompat.getScaledVerticalScrollFactor(
                                    ViewConfiguration.get(lei), lei
                            );

                    vw.scrollBy(0, Math.round(delta));
                    return true;
                }
                return false;
            }
        });

        qtgd1.requestFocus();

    }

    public void onResume() {
        super.onResume();
            applyWindowModeFromSettings();

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    private void onTitleBarClick(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(ChatMessageDetailActivity.this);

    }

    private void onWb3Click(View vw) {
        e1.tsk("发布时间", ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.wb3)).getText().toString());

    }

    private boolean onTx1LongClick(View vw) {

        int i;
        return true;

    }

    private void onWb22Click(View vw) {
        e1.tsk("详细信息", "该用户为匿名用户或存在其他风险，交流需谨慎");

    }

    private void onWb7Click(View vw) {
        e1.tsk("详细信息", "该用户为腕上微聊管理员");

    }

    private void onWb21Click(View vw) {
        e1.tsk("IP属地", address);

    }

    private void onWb2Click(View vw) {
        e1.tsk("昵称", ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.wb2)).getText().toString());

    }

    private void onXdbj3Click(View vw) {
        e1.tsk("消息内容", ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.wb1)).getText().toString());

    }

    private boolean onXdbj3LongClick(View vw) {

        int i;
        return true;

    }

    private void onWb12Click(View vw) {
        e1.tsk("提示", "附件暂不支持预览");

    }

    private void onAn2Click(View vw) {
        new open.cn.awg.pro.core.NativeDevice(ChatMessageDetailActivity.this).copyToClipboard(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.wb1)).getText().toString());
        e1.tsk("提示", "已复制到剪切板");

    }

    private boolean onBjk1Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private void onAn1Click(View vw) {
        e10.post_jb(((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(ChatMessageDetailActivity.this.getWindow().getDecorView(), R.id.bjk1)).getText().toString(), name, time, data);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        RelativeLayout i46461af6d7 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.chat_message_detail_root);

        TextView i0d2d4d9236 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb3);
        i0d2d4d9236.setOnClickListener(wb3ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnLongClickListener(tx1LongClickListener);

        LinearLayout e1433e26d6 = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj1);

        TextView i3b389d6120 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb22);
        i3b389d6120.setOnClickListener(wb22ClickListener);

        TextView b8bc22741b = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb7);
        b8bc22741b.setOnClickListener(wb7ClickListener);

        TextView i58d9590cdf = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb21);
        i58d9590cdf.setOnClickListener(wb21ClickListener);

        TextView i7c3e3c6b5d = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb2);
        i7c3e3c6b5d.setOnClickListener(wb2ClickListener);

        TextView e943ebd71e = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb4);

        TextView ff56a8e7f6 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb1);

        RelativeLayout i20c2777fab = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);
        i20c2777fab.setOnLongClickListener(xdbj3LongClickListener);

        TextView i3a177cf5f1 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb12);
        i3a177cf5f1.setOnClickListener(wb12ClickListener);

        Button i0b66a667b1 = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an2);
        i0b66a667b1.setOnClickListener(an2ClickListener);

        EditText i32ee139ba1 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        Button i171ea34f1a = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
