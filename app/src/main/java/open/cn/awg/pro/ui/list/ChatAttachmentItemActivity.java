/**
 ******************************************************************************
 * @file    ChatAttachmentItemActivity.java
 * @author  Typheye
 * @brief   Routes chat message attachments to previews, downloads, or detail screens.
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import open.cn.awg.pro.chat.ChatMessageDetailActivity;
import open.cn.awg.pro.chat.WearChatRuntimeBridge;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.download.AppDownloadActivity;
import open.cn.awg.pro.media.VideoPlayerActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.viewer.ImageViewerActivity;

public class ChatAttachmentItemActivity extends BaseAwgActivity {

    public final ChatAttachmentItemActivity lei = this, 类 = this;
    private final View.OnClickListener wb10ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb10Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj1LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj1LongClick(vw);
        }

    };
    private final View.OnClickListener xxbjfjClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbjfjClick(vw);
        }

    };
    private final View.OnClickListener xxbj4kClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj4kClick(vw);
        }

    };
    private final View.OnLongClickListener xxbj4kLongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXxbj4kLongClick(vw);
        }

    };
    private final View.OnLongClickListener tx1LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onTx1LongClick(vw);
        }

    };

    private boolean onTx1LongClick(View vw) {

        WearChatRuntimeBridge e10 = new WearChatRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) sj.lb(vw);

        Object b = lb.lbcfsj(R.id.wb2);
        e10.ltspostat(b);
        st.kp(R.id.kp7).kjd(8);
        return true;

    }

    private boolean onXxbj4kLongClick(View vw) {

        WearChatRuntimeBridge e10 = new WearChatRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) sj.lb(vw);

        Object b = lb.lbcfsj(R.id.wb2);
        e10.ltspostat(b);
        st.kp(R.id.kp7).kjd(8);
        return true;

    }

    private void onXxbj4kClick(View vw) {

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) sj.lb(vw);

        Object a = lb.lbcfsj(R.id.wb2);

        Object b = lb.lbcfsj(-3);

        Object c = lb.lbcfsj(-2);
        b = zf.qctwkg(b);

        if (zf.dy(b, "w0") || zf.dy(b, "") || zf.dy(b, "0") || zf.dy(b, null) || zf.dy(b, "null")) {

            b = "未注册用户";

        }
        c = zf.qctwkg(c);

        if (zf.dy(c, "未知") || zf.dy(c, "0") || zf.dy(c, "") || zf.dy(c, "null") || zf.dy(c, null)) {

            c = "未知IP属地";

        }
        e1.tsk("详细信息", "[用户昵称]\n" + a + "\n\n[用户UID]\n" + b + "\n\n[IP属地]\n" + c);

    }

    private void onXxbjfjClick(View vw) {

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        String a = st.wb(vw, R.id.wb8).zf();

        String b = st.wb(vw, R.id.wb9).zf();

        if (zf.dy(a, "[附件]图片")) {

            if (zf.dy(b, "")) {

                e1.tsk("提示", "链接指向错误");

            } else {

                if (!zf.dy(b, "")) {

                    String c1 = b;

                    try {

                        String[] cc = zf.fg(b, "/");

                        String c2 = cc[cc.length - 1];

                        if (!zf.dy(c1, "") && !zf.dy(c2, "")) {

                            final String[] name = new String[]{"url", "md5"};

                            final String[] value = new String[]{c1, c2};
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    gj.tz(ImageViewerActivity.class, name, value);

                                }

                            });

                        } else {

                            e1.tsk("提示", "链接指向错误");

                        }

                    } catch (Throwable e) {

                        e1.upload_error(e, "c11.*");

                    }

                } else {

                    e1.tsk("提示", "链接指向错误");

                }

            }

        } else if (zf.dy(a, "[附件]音频") || zf.dy(a, "[附件]视频")) {

            if (zf.dy(b, "")) {

                e1.tsk("提示", "链接指向错误");

            } else {

                if (!zf.dy(b, "")) {

                    String c1 = b;

                    try {

                        String[] cc = zf.fg(b, "/");

                        String c2 = cc[cc.length - 1];

                        if (!zf.dy(c1, "") && !zf.dy(c2, "")) {

                            String[] name = new String[]{"url"};

                            String[] value = new String[]{c1};
                            gj.tz(VideoPlayerActivity.class, name, value);

                        } else {

                            e1.tsk("提示", "链接指向错误");

                        }

                    } catch (Throwable e) {

                        e1.upload_error(e, "c11.*");

                    }

                } else {

                    e1.tsk("提示", "链接指向错误");

                }

            }

        } else if (zf.dy(a, "[附件]文件")) {

            if (zf.dy(b, "")) {

                e1.tsk("提示", "链接指向错误");

            } else {

                if (!zf.dy(b, "")) {

                    String c1 = b;

                    try {

                        String[] cc = zf.fg(b, "/");

                        String c2 = cc[cc.length - 1];

                        if (!zf.dy(c1, "") && !zf.dy(c2, "")) {

                            final String[] name = new String[]{"url"};

                            final String[] value = new String[]{c1};
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    gj.tz(AppDownloadActivity.class, name, value);

                                }

                            });

                        } else {

                            e1.tsk("提示", "链接指向错误");

                        }

                    } catch (Throwable e) {

                        e1.upload_error(e, "c11.*");

                    }

                } else {

                    e1.tsk("提示", "链接指向错误");

                }

            }

        } else {

            e1.tsk("提示", "未知类型附件");

        }

    }

    private boolean onXdbj1LongClick(View vw) {

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) sj.lb(vw);

        Object a = lb.lbcfsj(R.id.wb1);

        Object b = lb.lbcfsj(R.id.wb2);

        Object c = lb.lbcfsj(R.id.wb3);

        Object d = lb.lbcfsj(-2);

        Object e = lb.lbcfsj(-3);

        String[] name = new String[]{"name", "time", "data", "address", "luid"};

        Object[] value = new Object[]{b, c, a, d, e};
        gj.tz(ChatMessageDetailActivity.class, name, value);
        return true;

    }

    private void onWb10Click(View vw) {

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        String a = st.wb(vw, R.id.wb10).zf();
        e1.tsk("消息详情", a);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnLongClickListener(tx1LongClickListener);

        TextView i0d2d4d9236 = (TextView) findViewById(ay, vw, R.id.wb3);

        LinearLayout e1433e26d6 = (LinearLayout) findViewById(ay, vw, R.id.xxbj1);

        TextView i7c3e3c6b5d = (TextView) findViewById(ay, vw, R.id.wb2);

        LinearLayout dc4433170e = (LinearLayout) findViewById(ay, vw, R.id.xxbj4k);
        dc4433170e.setOnLongClickListener(xxbj4kLongClickListener);
        dc4433170e.setOnClickListener(xxbj4kClickListener);

        TextView e943ebd71e = (TextView) findViewById(ay, vw, R.id.wb4);

        TextView ff56a8e7f6 = (TextView) findViewById(ay, vw, R.id.wb1);

        LinearLayout bf684e97e0 = (LinearLayout) findViewById(ay, vw, R.id.xxbjfj);
        bf684e97e0.setOnClickListener(xxbjfjClickListener);

        RelativeLayout aaeebbd136 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj1);
        aaeebbd136.setOnLongClickListener(xdbj1LongClickListener);

        RelativeLayout i9a2a79f0f0 = (RelativeLayout) findViewById(ay, vw, R.id.chat_attachment_item_root);

        TextView i81652f77b6 = (TextView) findViewById(ay, vw, R.id.wb10);
        i81652f77b6.setOnClickListener(wb10ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
