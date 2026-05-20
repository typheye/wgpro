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

import android.view.View;
import android.view.Window;

import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;


public class ChatMessageDetailActivity extends iActivity {

    public final ChatMessageDetailActivity lei = this, 类 = this;
    public java.lang.String name = "";
    public java.lang.String data = "";
    public java.lang.String time = "";
    public java.lang.String address = "";
    public java.lang.String luid = "";
    public open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public open.cn.awg.pro.chat.WearChatRuntimeBridge e10 = new WearChatRuntimeBridge(_APPINFO);
    private final android.view.View.OnClickListener $_on_setOnClickListener_i171ea34f1a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i171ea34f1a(vw);
        }

    };
    private final android.view.View.OnTouchListener $_on_setOnTouchListener_i32ee139ba1 = new android.view.View.OnTouchListener() {

        public boolean onTouch(android.view.View vw, android.view.MotionEvent me) {
            return $_onTouch_i32ee139ba1(vw, me);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i0b66a667b1 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i0b66a667b1(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i3a177cf5f1 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i3a177cf5f1(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i20c2777fab = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i20c2777fab(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i20c2777fab = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i20c2777fab(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i7c3e3c6b5d = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i7c3e3c6b5d(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i58d9590cdf = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i58d9590cdf(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_b8bc22741b = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_b8bc22741b(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i3b389d6120 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i3b389d6120(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i34dc7bf20a = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i34dc7bf20a(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i0d2d4d9236 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i0d2d4d9236(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d26d5d7080(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.i3);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.app.AwgProApplication.getInstance().addActivity(lei);
        name = sj.hqtz("name");
        data = sj.hqtz("data");
        time = sj.hqtz("time");
        address = sj.hqtz("address");
        luid = sj.hqtz("luid");
        luid = zf.qctwkg(luid);

        if (time.length() < 19) {

            time = time + ":00";

        }
        st.wb(open.cn.awg.pro.R.id.wb2).zf(name);

        if (zf.cz(data, "[PHOTO]")) {

            data = zf.qc(data, null, "[PHOTO]");
            st.wb(open.cn.awg.pro.R.id.wb12).kjd(0);

        } else if (zf.cz(data, "[AUDIO]")) {

            data = zf.qc(data, null, "[AUDIO]");
            st.wb(open.cn.awg.pro.R.id.wb12).kjd(0);

        } else if (zf.cz(data, "[VIDEO]")) {

            data = zf.qc(data, null, "[VIDEO]");
            st.wb(open.cn.awg.pro.R.id.wb12).kjd(0);

        } else if (zf.cz(data, "[OTHER]")) {

            data = zf.qc(data, null, "[OTHER]");
            st.wb(open.cn.awg.pro.R.id.wb12).kjd(0);

        }

        if (zf.dy(name, "台风眼Typheye") || zf.dy(name, "系统消息")) {

            st.wb(open.cn.awg.pro.R.id.wb7).kjd(0);

        } else {

            st.wb(open.cn.awg.pro.R.id.wb7).kjd(8);

        }

        if (zf.dy(luid, "w0") || zf.dy(luid, "") || zf.dy(luid, "0") || zf.dy(luid, null) || zf.dy(luid, "null")) {

            st.wb(open.cn.awg.pro.R.id.wb22).kjd(0);

        } else {

            st.wb(open.cn.awg.pro.R.id.wb22).kjd(8);

        }

        if (zf.dy(address, "未知")) {

            st.wb(open.cn.awg.pro.R.id.wb21).kjd(8);

        } else {

            st.wb(open.cn.awg.pro.R.id.wb21).zf(address);
            st.wb(open.cn.awg.pro.R.id.wb21).kjd(0);

        }
        st.wb(open.cn.awg.pro.R.id.wb1).zf(data);
        st.wb(open.cn.awg.pro.R.id.wb3).zf(time);

        java.lang.String wb = zf.qc(name, 0, 1);
        st.wb(open.cn.awg.pro.R.id.wb6).zf(wb);

        final java.lang.String set3 = "/data/user/0/open.cn.awg.pro/settings/f11/set3.inf";

        java.lang.String uid = wj.dqwb("/data/user/0/open.cn.awg.pro/data/ltlb/lbx_id");

        java.lang.String namer = wj.dqwb(set3);

        final java.lang.String userid = "/data/user/0/open.cn.awg.pro/settings/account/user";

        if (!zf.dy(luid, "0") && wj.cz(userid) && !zf.dy(wj.dqwb(userid), "") && zf.dy(luid, wj.dqwb(userid))) {

            st.kp(open.cn.awg.pro.R.id.kp1).kpbjys(zf.zf(open.cn.awg.pro.R.color.colorTextTrue2));
            st.wb(open.cn.awg.pro.R.id.wb1).zfys(zf.zf(open.cn.awg.pro.R.color.colorBlack));

        }

        if (!zf.dy(luid, "0") && wj.cz(userid) && !zf.dy(wj.dqwb(userid), "") && zf.dy(luid, wj.dqwb(userid)) || zf.dy(uid, "110") || zf.dy(uid, "404")) {

            st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(8);

        }

    }

    public void __layoutIsLoaded(android.app.Activity ay, android.view.View vw) {

        if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f8/set4.inf"), "true")) {

        }

        i.runlibrary.app.xt$pm pm = xt.pm();

        if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4s.inf"), "false")) {

            if (zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4.inf"), "true")) {

                getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));

        if (zf.dy(a, "4")) {

            st.xdbj(open.cn.awg.pro.R.id.i3_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.i3_v).shxtck(false);

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

        if (zf.dy(a, "1")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "2")) {

            java.lang.String a001 = "/data/user/0/open.cn.awg.pro/settings/dpi.inf";

            if (wj.cz(a001) || zf.dy(wj.dqwb(a001), "true")) {

                i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
                wtab.nbj(0, "3dp", 0, "3dp");
                wtab.dqfs("center");

            } else {

                i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
                wtab.nbj(0, "9dp", 0, "9dp");
                wtab.dqfs("center");

            }
            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0, 0, 0, 0);

        } else if (zf.dy(a, "3")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(open.cn.awg.pro.R.id.Tab);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "4")) {

            st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0, 0, 0, 0);

        }

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

    }

    public void onRestart() {
        super.onRestart();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

    }

    public void onStart() {
        super.onStart();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

        i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(open.cn.awg.pro.R.id.qtgd1);

        qtgd1.v.setOnGenericMotionListener(new View.OnGenericMotionListener() {
            @Override
            public boolean onGenericMotion(View vw, android.view.MotionEvent me) {
                if (me.getAction() == android.view.MotionEvent.ACTION_SCROLL && me.isFromSource(androidx.core.view.InputDeviceCompat.SOURCE_ROTARY_ENCODER)) {

                    float delta = -me.getAxisValue(androidx.core.view.MotionEventCompat.AXIS_SCROLL) *
                            androidx.core.view.ViewConfigurationCompat.getScaledVerticalScrollFactor(
                                    android.view.ViewConfiguration.get(lei), lei
                            );

                    vw.scrollBy(0, java.lang.Math.round(delta));
                    return true;
                }
                return false;
            }
        });

        qtgd1.v.requestFocus();

    }

    public void onResume() {
        super.onResume();

        final java.lang.String a2 = "/data/user/0/open.cn.awg.pro/settings/a3.inf";

        java.lang.String a = wj.dqwb(a2);

        if (zf.dy(a, "4")) {

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            Window window = lei.getWindow();
            BangScreenTools.getBangScreenTools().fullscreen(window, lei);
            BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);

        }

    }

    public void onDestroy() {
        super.onDestroy();
        open.cn.awg.pro.app.AwgProApplication.getInstance().removeActivity(lei);

    }

    private void $_onClick_d26d5d7080(android.view.View vw) {
        gj.gb();

    }

    private void $_onClick_i0d2d4d9236(android.view.View vw) {
        e1.tsk("发布时间", st.wb(open.cn.awg.pro.R.id.wb3).zf());

    }

    private boolean $_onLongClick_i34dc7bf20a(android.view.View vw) {

        int i;
        return true;

    }

    private void $_onClick_i3b389d6120(android.view.View vw) {
        e1.tsk("详细信息", "该用户为匿名用户或存在其他风险，交流需谨慎");

    }

    private void $_onClick_b8bc22741b(android.view.View vw) {
        e1.tsk("详细信息", "该用户为腕上微聊管理员");

    }

    private void $_onClick_i58d9590cdf(android.view.View vw) {
        e1.tsk("IP属地", address);

    }

    private void $_onClick_i7c3e3c6b5d(android.view.View vw) {
        e1.tsk("昵称", st.wb(open.cn.awg.pro.R.id.wb2).zf());

    }

    private void $_onClick_i20c2777fab(android.view.View vw) {
        e1.tsk("消息内容", st.wb(open.cn.awg.pro.R.id.wb1).zf());

    }

    private boolean $_onLongClick_i20c2777fab(android.view.View vw) {

        int i;
        return true;

    }

    private void $_onClick_i3a177cf5f1(android.view.View vw) {
        e1.tsk("提示", "附件暂不支持预览");

    }

    private void $_onClick_i0b66a667b1(android.view.View vw) {
        xt.jqbxr(st.wb(open.cn.awg.pro.R.id.wb1).zf());
        e1.tsk("提示", "已复制到剪切板");

    }

    private boolean $_onTouch_i32ee139ba1(android.view.View vw, android.view.MotionEvent me) {

        if (!vw.isFocused()) {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(open.cn.awg.pro.R.id.qtgd1);
                    qtgd1.v.requestFocus();

                }

            });

        }
        return false;

    }

    private void $_onClick_i171ea34f1a(android.view.View vw) {
        e10.post_jb(st.bjk(open.cn.awg.pro.R.id.bjk1).zf(), name, time, data);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
        d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

        android.widget.RelativeLayout i46461af6d7 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.i3_v);

        android.widget.TextView i0d2d4d9236 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb3);
        i0d2d4d9236.setOnClickListener($_on_setOnClickListener_i0d2d4d9236);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnLongClickListener($_on_setOnLongClickListener_i34dc7bf20a);

        android.widget.LinearLayout e1433e26d6 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj1);

        android.widget.TextView i3b389d6120 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb22);
        i3b389d6120.setOnClickListener($_on_setOnClickListener_i3b389d6120);

        android.widget.TextView b8bc22741b = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb7);
        b8bc22741b.setOnClickListener($_on_setOnClickListener_b8bc22741b);

        android.widget.TextView i58d9590cdf = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb21);
        i58d9590cdf.setOnClickListener($_on_setOnClickListener_i58d9590cdf);

        android.widget.TextView i7c3e3c6b5d = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb2);
        i7c3e3c6b5d.setOnClickListener($_on_setOnClickListener_i7c3e3c6b5d);

        android.widget.TextView e943ebd71e = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb4);

        android.widget.TextView ff56a8e7f6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb1);

        android.widget.RelativeLayout i20c2777fab = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj3);
        i20c2777fab.setOnClickListener($_on_setOnClickListener_i20c2777fab);
        i20c2777fab.setOnLongClickListener($_on_setOnLongClickListener_i20c2777fab);

        android.widget.TextView i3a177cf5f1 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb12);
        i3a177cf5f1.setOnClickListener($_on_setOnClickListener_i3a177cf5f1);

        android.widget.Button i0b66a667b1 = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an2);
        i0b66a667b1.setOnClickListener($_on_setOnClickListener_i0b66a667b1);

        android.widget.EditText i32ee139ba1 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjk1);
        i32ee139ba1.setOnTouchListener($_on_setOnTouchListener_i32ee139ba1);

        android.widget.Button i171ea34f1a = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an1);
        i171ea34f1a.setOnClickListener($_on_setOnClickListener_i171ea34f1a);

        __layoutIsLoaded(ay, vw);
    }
}
