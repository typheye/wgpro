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
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public WearChatRuntimeBridge e10 = new WearChatRuntimeBridge(_APPINFO);
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
        setContentView(R.layout.chat_message_detail);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        name = sj.hqtz("name");
        data = sj.hqtz("data");
        time = sj.hqtz("time");
        address = sj.hqtz("address");
        luid = sj.hqtz("luid");
        luid = zf.qctwkg(luid);

        if (time.length() < 19) {

            time = time + ":00";

        }
        st.wb(R.id.wb2).zf(name);

        if (zf.cz(data, "[PHOTO]")) {

            data = zf.qc(data, null, "[PHOTO]");
            st.wb(R.id.wb12).kjd(0);

        } else if (zf.cz(data, "[AUDIO]")) {

            data = zf.qc(data, null, "[AUDIO]");
            st.wb(R.id.wb12).kjd(0);

        } else if (zf.cz(data, "[VIDEO]")) {

            data = zf.qc(data, null, "[VIDEO]");
            st.wb(R.id.wb12).kjd(0);

        } else if (zf.cz(data, "[OTHER]")) {

            data = zf.qc(data, null, "[OTHER]");
            st.wb(R.id.wb12).kjd(0);

        }

        if (zf.dy(name, "台风眼Typheye") || zf.dy(name, "系统消息")) {

            st.wb(R.id.wb7).kjd(0);

        } else {

            st.wb(R.id.wb7).kjd(8);

        }

        if (zf.dy(luid, "w0") || zf.dy(luid, "") || zf.dy(luid, "0") || zf.dy(luid, null) || zf.dy(luid, "null")) {

            st.wb(R.id.wb22).kjd(0);

        } else {

            st.wb(R.id.wb22).kjd(8);

        }

        if (zf.dy(address, "未知")) {

            st.wb(R.id.wb21).kjd(8);

        } else {

            st.wb(R.id.wb21).zf(address);
            st.wb(R.id.wb21).kjd(0);

        }
        st.wb(R.id.wb1).zf(data);
        st.wb(R.id.wb3).zf(time);

        String wb = zf.qc(name, 0, 1);
        st.wb(R.id.wb6).zf(wb);

        final String set3 = AppPaths.appPath("settings/f11/set3.inf");

        String uid = wj.dqwb(AppPaths.appPath("data/ltlb/lbx_id"));

        String namer = wj.dqwb(set3);

        final String userid = AppPaths.appPath("settings/account/user");

        if (!zf.dy(luid, "0") && wj.cz(userid) && !zf.dy(wj.dqwb(userid), "") && zf.dy(luid, wj.dqwb(userid))) {

            st.kp(R.id.kp1).kpbjys(zf.zf(R.color.colorTextTrue2));
            st.wb(R.id.wb1).zfys(zf.zf(R.color.colorBlack));

        }

        if (!zf.dy(luid, "0") && wj.cz(userid) && !zf.dy(wj.dqwb(userid), "") && zf.dy(luid, wj.dqwb(userid)) || zf.dy(uid, "110") || zf.dy(uid, "404")) {

            st.xxbj(R.id.xxbj6).kjd(8);

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

        i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(R.id.qtgd1);

        qtgd1.v.setOnGenericMotionListener(new View.OnGenericMotionListener() {
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

        qtgd1.v.requestFocus();

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
        gj.gb();

    }

    private void onWb3Click(View vw) {
        e1.tsk("发布时间", st.wb(R.id.wb3).zf());

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
        e1.tsk("昵称", st.wb(R.id.wb2).zf());

    }

    private void onXdbj3Click(View vw) {
        e1.tsk("消息内容", st.wb(R.id.wb1).zf());

    }

    private boolean onXdbj3LongClick(View vw) {

        int i;
        return true;

    }

    private void onWb12Click(View vw) {
        e1.tsk("提示", "附件暂不支持预览");

    }

    private void onAn2Click(View vw) {
        xt.jqbxr(st.wb(R.id.wb1).zf());
        e1.tsk("提示", "已复制到剪切板");

    }

    private boolean onBjk1Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            gj.jmxc(new Runnable() {

                public void run() {

                    i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(R.id.qtgd1);
                    qtgd1.v.requestFocus();

                }

            });

        }
        return false;

    }

    private void onAn1Click(View vw) {
        e10.post_jb(st.bjk(R.id.bjk1).zf(), name, time, data);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        RelativeLayout i46461af6d7 = (RelativeLayout) findViewById(ay, vw, R.id.chat_message_detail_root);

        TextView i0d2d4d9236 = (TextView) findViewById(ay, vw, R.id.wb3);
        i0d2d4d9236.setOnClickListener(wb3ClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnLongClickListener(tx1LongClickListener);

        LinearLayout e1433e26d6 = (LinearLayout) findViewById(ay, vw, R.id.xxbj1);

        TextView i3b389d6120 = (TextView) findViewById(ay, vw, R.id.wb22);
        i3b389d6120.setOnClickListener(wb22ClickListener);

        TextView b8bc22741b = (TextView) findViewById(ay, vw, R.id.wb7);
        b8bc22741b.setOnClickListener(wb7ClickListener);

        TextView i58d9590cdf = (TextView) findViewById(ay, vw, R.id.wb21);
        i58d9590cdf.setOnClickListener(wb21ClickListener);

        TextView i7c3e3c6b5d = (TextView) findViewById(ay, vw, R.id.wb2);
        i7c3e3c6b5d.setOnClickListener(wb2ClickListener);

        TextView e943ebd71e = (TextView) findViewById(ay, vw, R.id.wb4);

        TextView ff56a8e7f6 = (TextView) findViewById(ay, vw, R.id.wb1);

        RelativeLayout i20c2777fab = (RelativeLayout) findViewById(ay, vw, R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);
        i20c2777fab.setOnLongClickListener(xdbj3LongClickListener);

        TextView i3a177cf5f1 = (TextView) findViewById(ay, vw, R.id.wb12);
        i3a177cf5f1.setOnClickListener(wb12ClickListener);

        Button i0b66a667b1 = (Button) findViewById(ay, vw, R.id.an2);
        i0b66a667b1.setOnClickListener(an2ClickListener);

        EditText i32ee139ba1 = (EditText) findViewById(ay, vw, R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        Button i171ea34f1a = (Button) findViewById(ay, vw, R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
