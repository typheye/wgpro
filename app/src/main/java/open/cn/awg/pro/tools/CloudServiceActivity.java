/**
 ******************************************************************************
 * @file    CloudServiceActivity.java
 * @author  Typheye
 * @brief   Cloud service settings screen.
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
package open.cn.awg.pro.tools;

import android.view.View;
import android.view.Window;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.listener.UploadProgressListener;
import com.ypz.bangscreentools.BangScreenTools;

import i.app.iActivity;
import open.cn.awg.pro.account.TypheyeAccountActivity;
import open.cn.awg.pro.announcement.AnnouncementActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.settings.WearChatSettingsActivity;


public class CloudServiceActivity extends iActivity {

    public final CloudServiceActivity lei = this, 类 = this;
    public open.cn.awg.pro.core.AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public int mode = 0;
    public boolean state_upload = false;
    public java.lang.String upload_back = "";
    public java.lang.String pu = "https://service.typheye.cn/app/open.cn.awg.pro/backup";
    public java.lang.String fib = "/data/user/0/open.cn.awg.pro/data/backup/yun/last.awgbak.zip";
    public java.lang.String fic = "/data/user/0/open.cn.awg.pro/data/backup/yun/last.awgbak";
    public java.lang.String fictmp = "/data/user/0/open.cn.awg.pro/data/backup/yun/.nomedia";
    private final android.view.View.OnClickListener $_on_setOnClickListener_i8217d42a17 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i8217d42a17(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i4d951cdb0e = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i4d951cdb0e(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
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
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_c0d3e00d2b = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_c0d3e00d2b(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_c0d3e00d2b = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_c0d3e00d2b(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i5a93676674 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i5a93676674(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i5a93676674 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i5a93676674(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_fab146d7a5 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_fab146d7a5(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_fab146d7a5 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_fab146d7a5(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_i2202ede8ff = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_i2202ede8ff(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i2202ede8ff = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i2202ede8ff(vw);
        }

    };
    private final android.view.View.OnLongClickListener $_on_setOnLongClickListener_e585d94488 = new android.view.View.OnLongClickListener() {

        public boolean onLongClick(android.view.View vw) {
            return $_onLongClick_e585d94488(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_e585d94488 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_e585d94488(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d26d5d7080(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.f10);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.app.AwgProApplication.getInstance().addActivity(lei);
        jz();

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

            st.xdbj(open.cn.awg.pro.R.id.f10_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.f10_v).shxtck(false);

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

    public void jz() {

        java.lang.String te1 = "";

        java.lang.String set1 = "/data/user/0/open.cn.awg.pro/settings/f10/set1.inf";

        java.lang.String set2 = "/data/user/0/open.cn.awg.pro/settings/f10/set2.inf";

        java.lang.String set3 = "/data/user/0/open.cn.awg.pro/settings/f7/set1.inf";
        updata();

    }

    public void yun_backup() {

        if (e1.islogin()) {

            if (zf.dy(st.wb(open.cn.awg.pro.R.id.wb11).zf(), "加载中...")) {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            } else {

                mode = 1;
                qh();

            }

        } else {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    gj.tz(TypheyeAccountActivity.class);
                    e1.tsk("提示", "该操作需要登录Typheye账户");

                }

            });

        }

    }

    public void yun_recovery() {

        if (e1.islogin()) {

            if (zf.dy(st.wb(open.cn.awg.pro.R.id.wb4).zf(), "加载中...")) {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            } else {

                recovery();

            }

        } else {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    gj.tz(TypheyeAccountActivity.class);
                    e1.tsk("提示", "该操作需要登录Typheye账户");

                }

            });

        }

    }

    public void qh() {
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                if (st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd() == 0) {

                    if (mode == 1) {

                        st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
                        st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(0);
                        st.wb(open.cn.awg.pro.R.id.wb1).zf("您确定要备份数据到云端吗？");
                        st.wb(open.cn.awg.pro.R.id.Tab).zf("提示");

                    } else if (mode == 2) {

                        st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
                        st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(0);
                        st.wb(open.cn.awg.pro.R.id.wb1).zf("已下载备份数据，您确定要恢复吗？");
                        st.wb(open.cn.awg.pro.R.id.Tab).zf("提示");

                    }

                } else {

                    st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);
                    st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(8);
                    st.wb(open.cn.awg.pro.R.id.wb1).zf("");
                    st.wb(open.cn.awg.pro.R.id.Tab).zf("< 云端服务");

                }

            }

        });

    }

    public void gx() {

        if (mode == 1) {

            gj.xc(new java.lang.Thread() {

                public void run() {

                    if (e1.app_backup2()) {

                        upload(fib);

                    }

                }

            });

        } else if (mode == 2) {

            gj.xc(new java.lang.Thread() {

                public void run() {

                    e1.app_recovery(fic);

                }

            });

        }
        jz();
        qh();

    }

    public void recovery() {

        final java.lang.String a3 = "/data/user/0/open.cn.awg.pro/settings/account/user";
        gj.xc(new java.lang.Thread() {

            public void run() {

                gj.jmxc(new java.lang.Runnable() {

                    public void run() {

                        st.wb(open.cn.awg.pro.R.id.wb4).zf("加载中...");

                    }

                });
                wj.xrwb(fictmp, "");
                wj.sc(fictmp);

                java.lang.String url = pu + "/user/" + wj.dqwb(a3);

                java.lang.String saveph = fic;

                int back = wl.xz(url, saveph, true);

                if (back == -1) {

                    updata();
                    e1.tsk("提示", "从云端下载失败");

                } else {

                    updata();
                    mode = 2;
                    qh();

                }

            }

        });

    }

    public void updata() {
        wj.sc(fib);
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.wb(open.cn.awg.pro.R.id.wb11).zf("立即备份数据至云端");
                st.wb(open.cn.awg.pro.R.id.wb4).zf("立即从云端恢复数据");

            }

        });
        upu();

    }

    public void upu() {

        final i.runlibrary.app.v.wb wb18 = st.wb(open.cn.awg.pro.R.id.wb18);

        if (e1.islogin()) {

            final java.lang.String a3 = "/data/user/0/open.cn.awg.pro/settings/account/user";
            gj.xc(new java.lang.Thread() {

                public void run() {

                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            wb18.zf("加载中...");

                        }

                    });

                    java.lang.String url = pu + "/re/" + wj.dqwb(a3);

                    final java.lang.String back = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                    if (zf.dy(back, "") || zf.dy(back, null)) {

                        gj.jmxc(new java.lang.Runnable() {

                            public void run() {

                                wb18.zf("未能获取到云端备份信息");

                            }

                        });

                    } else {

                        gj.jmxc(new java.lang.Runnable() {

                            public void run() {

                                wb18.zf(back);

                            }

                        });

                    }

                }

            });

        } else {

            gj.jmxc(new java.lang.Runnable() {

                public void run() {

                    wb18.zf("请先登录Typheye账户");

                }

            });

        }

    }

    public void upload(java.lang.Object a) {

        if (!state_upload) {

            state_upload = true;

            final java.lang.String a3 = "/data/user/0/open.cn.awg.pro/settings/account/user";

            final java.lang.String path_ = wj.hqml(a);

            final java.lang.String[] path = new java.lang.String[]{path_};

            final i.runlibrary.app.v.wb wb11 = st.wb(open.cn.awg.pro.R.id.wb11);
            gj.xc(new java.lang.Thread() {

                public void run() {

                    gj.jmxc(new java.lang.Runnable() {

                        public void run() {

                            wb11.zf("加载中...");

                        }

                    });
                    upload_back = "";

                    if (e1.yz()) {

                        if (zf.dy(path_, "") || !wj.cz(path_)) {

                            updata();
                            e1.tsk("提示", "文件路径无效");

                        } else {

                            final java.lang.String url = pu + "/up.php?uid=" + wj.dqwb(a3);

                            java.lang.String fn__ = "null";

                            java.lang.String[] fn_ = e1.fileinfo("/", path[0]);

                            if (zf.dy(fn_[0], "true")) {

                                fn__ = fn_[3];

                            }

                            final java.lang.String file_name = fn__;

                            final long file_size = wj.dx(path_);
                            gj.jmxc(new java.lang.Runnable() {

                                public void run() {

                                    try {

                                        HttpRequest.TIME_OUT_DURATION = 300;
                                        HttpRequest.build(lei, url)
                                                .addParameter("name", file_name)
                                                .addParameter("size", file_size)
                                                .addParameter("file", wj.wj(path_))
                                                .setUploadProgressListener(new UploadProgressListener() {
                                                    @Override
                                                    public void onUpload(float percentage, long current, long total, boolean done) {

                                                        long now = current / total * 100;
                                                        wb11.zf("已上传" + sj.zh().zstring(now) + "%");
                                                    }
                                                })
                                                .setResponseListener(new ResponseListener() {
                                                    @Override
                                                    public void onResponse(String response, Exception error) {
                                                        uploadResult(error, response);
                                                    }
                                                })
                                                .doPost();

                                    } catch (java.lang.Throwable e) {

                                        updata();
                                        e1.tsk("提示", "上传失败(-1)");

                                    }

                                }

                            });

                        }

                    } else {

                        updata();
                        e1.tsk("提示", "上传失败\n连接服务器超时(-2)");

                    }
                    state_upload = false;

                }

            });

        } else {

        }

    }

    public void uploadResult(java.lang.Exception error, java.lang.Object upload_back) {

        java.lang.Object back = upload_back;

        if (!zf.dy(sj.zh().zstring(error), null)) {

            updata();
            e1.tsk("提示", "上传失败\n" + sj.zh().zstring(error));

        } else {

            if (zf.dy(back, "") || zf.dy(back, null)) {

                updata();
                e1.tsk("提示", "上传失败\n连接服务器超时(-1)");

            } else {

                updata();
                e1.tsk("提示", back);

            }

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

    public boolean onKeyDown(int kc, android.view.KeyEvent ke) {

        if (kc == 4) {

            if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< 云端服务")) {

                gj.gb();

            }

        }
        return false;

    }

    private void $_onClick_d26d5d7080(android.view.View vw) {

        if (zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(), "< 云端服务")) {

            gj.gb();

        }

    }

    private void $_onClick_e585d94488(android.view.View vw) {
        gj.tz(AnnouncementActivity.class);

    }

    private boolean $_onLongClick_e585d94488(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb14).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb15).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i2202ede8ff(android.view.View vw) {
        gj.tz(WearChatSettingsActivity.class);

    }

    private boolean $_onLongClick_i2202ede8ff(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb8).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb9).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_fab146d7a5(android.view.View vw) {
        e1.goplu("20005", "后台诊断", false);

    }

    private boolean $_onLongClick_fab146d7a5(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb5).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb6).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i5a93676674(android.view.View vw) {

        if (zf.dy(st.wb(open.cn.awg.pro.R.id.wb18).zf(), "加载中...")) {

            e1.tsk("提示", "操作过于频繁，请稍后重试");

        } else {

            java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb18).zf();
            e1.tsk("云端数据", lj);

        }

    }

    private boolean $_onLongClick_i5a93676674(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb17).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb18).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_c0d3e00d2b(android.view.View vw) {
        yun_backup();

    }

    private boolean $_onLongClick_c0d3e00d2b(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb10).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb11).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i20c2777fab(android.view.View vw) {
        yun_recovery();

    }

    private boolean $_onLongClick_i20c2777fab(android.view.View vw) {

        java.lang.String te = st.wb(open.cn.awg.pro.R.id.wb3).zf();

        java.lang.String lj = st.wb(open.cn.awg.pro.R.id.wb4).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void $_onClick_i34dc7bf20a(android.view.View vw) {
        gj.gb();

    }

    private void $_onClick_i4d951cdb0e(android.view.View vw) {
        qh();

    }

    private void $_onClick_i8217d42a17(android.view.View vw) {
        gx();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
        d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

        android.widget.ImageView i3ab5c5fd62 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx10);

        android.widget.RelativeLayout e585d94488 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj7);
        e585d94488.setOnClickListener($_on_setOnClickListener_e585d94488);
        e585d94488.setOnLongClickListener($_on_setOnLongClickListener_e585d94488);

        android.widget.RelativeLayout i2202ede8ff = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj4);
        i2202ede8ff.setOnClickListener($_on_setOnClickListener_i2202ede8ff);
        i2202ede8ff.setOnLongClickListener($_on_setOnLongClickListener_i2202ede8ff);

        android.widget.RelativeLayout fab146d7a5 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj8);
        fab146d7a5.setOnClickListener($_on_setOnClickListener_fab146d7a5);
        fab146d7a5.setOnLongClickListener($_on_setOnLongClickListener_fab146d7a5);

        android.widget.RelativeLayout i5a93676674 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj9);
        i5a93676674.setOnClickListener($_on_setOnClickListener_i5a93676674);
        i5a93676674.setOnLongClickListener($_on_setOnLongClickListener_i5a93676674);

        android.widget.RelativeLayout c0d3e00d2b = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj5);
        c0d3e00d2b.setOnClickListener($_on_setOnClickListener_c0d3e00d2b);
        c0d3e00d2b.setOnLongClickListener($_on_setOnLongClickListener_c0d3e00d2b);

        android.widget.RelativeLayout i20c2777fab = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj3);
        i20c2777fab.setOnClickListener($_on_setOnClickListener_i20c2777fab);
        i20c2777fab.setOnLongClickListener($_on_setOnLongClickListener_i20c2777fab);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
        i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

        android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);
        i8217d42a17.setOnClickListener($_on_setOnClickListener_i8217d42a17);

        __layoutIsLoaded(ay, vw);
    }
}
