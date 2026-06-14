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


import open.cn.awg.pro.core.IappCompat;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import com.kongzue.baseokhttp.HttpRequest;
import com.kongzue.baseokhttp.listener.ResponseListener;
import com.kongzue.baseokhttp.listener.UploadProgressListener;

import open.cn.awg.pro.account.TypheyeAccountActivity;
import open.cn.awg.pro.announcement.AnnouncementActivity;
import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.settings.WearChatSettingsActivity;

public class CloudServiceActivity extends BaseAwgActivity {

    public final CloudServiceActivity lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public int mode = 0;
    public boolean state_upload = false;
    public String upload_back = "";
    public String pu = "https://service.typheye.cn/app/open.cn.awg.pro/backup";
    public String fib = AppPaths.appPath("data/backup/yun/last.awgbak.zip");
    public String fic = AppPaths.appPath("data/backup/yun/last.awgbak");
    public String fictmp = AppPaths.appPath("data/backup/yun/.nomedia");
    private final View.OnClickListener tx3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx3Click(vw);
        }

    };
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
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
    private final View.OnLongClickListener xdbj5LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj5LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj5ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj5Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj9LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj9LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj9ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj9Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj8LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj8LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj8ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj8Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj4LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj4LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj4Click(vw);
        }

    };
    private final View.OnLongClickListener xdbj7LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj7LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj7ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj7Click(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.cloud_service);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        jz();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.cloud_service_root, R.id.xdbj1, R.id.title_bar);

    }

    public void jz() {

        String te1 = "";

        String set1 = AppPaths.appPath("settings/f10/set1.inf");

        String set2 = AppPaths.appPath("settings/f10/set2.inf");

        String set3 = AppPaths.appPath("settings/f7/set1.inf");
        updata();

    }

    public void yun_backup() {

        if (e1.islogin()) {

            if (zf.dy(st.wb(R.id.wb11).zf(), "加载中...")) {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            } else {

                mode = 1;
                qh();

            }

        } else {

            gj.jmxc(new Runnable() {

                public void run() {

                    gj.tz(TypheyeAccountActivity.class);
                    e1.tsk("提示", "该操作需要登录Typheye账户");

                }

            });

        }

    }

    public void yun_recovery() {

        if (e1.islogin()) {

            if (zf.dy(st.wb(R.id.wb4).zf(), "加载中...")) {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            } else {

                recovery();

            }

        } else {

            gj.jmxc(new Runnable() {

                public void run() {

                    gj.tz(TypheyeAccountActivity.class);
                    e1.tsk("提示", "该操作需要登录Typheye账户");

                }

            });

        }

    }

    public void qh() {
        gj.jmxc(new Runnable() {

            public void run() {

                if (st.xxbj(R.id.xxbj1).kjd() == 0) {

                    if (mode == 1) {

                        st.xxbj(R.id.xxbj1).kjd(8);
                        st.xxbj(R.id.xxbj4).kjd(0);
                        st.wb(R.id.wb1).zf("您确定要备份数据到云端吗？");
                        st.wb(R.id.title_bar).zf("提示");

                    } else if (mode == 2) {

                        st.xxbj(R.id.xxbj1).kjd(8);
                        st.xxbj(R.id.xxbj4).kjd(0);
                        st.wb(R.id.wb1).zf("已下载备份数据，您确定要恢复吗？");
                        st.wb(R.id.title_bar).zf("提示");

                    }

                } else {

                    st.xxbj(R.id.xxbj1).kjd(0);
                    st.xxbj(R.id.xxbj4).kjd(8);
                    st.wb(R.id.wb1).zf("");
                    st.wb(R.id.title_bar).zf("< 云端服务");

                }

            }

        });

    }

    public void gx() {

        if (mode == 1) {

            gj.xc(new Thread() {

                public void run() {

                    if (e1.app_backup2()) {

                        upload(fib);

                    }

                }

            });

        } else if (mode == 2) {

            gj.xc(new Thread() {

                public void run() {

                    e1.app_recovery(fic);

                }

            });

        }
        jz();
        qh();

    }

    public void recovery() {

        final String a3 = AppPaths.appPath("settings/account/user");
        gj.xc(new Thread() {

            public void run() {

                gj.jmxc(new Runnable() {

                    public void run() {

                        st.wb(R.id.wb4).zf("加载中...");

                    }

                });
                wj.xrwb(fictmp, "");
                wj.sc(fictmp);

                String url = pu + "/user/" + wj.dqwb(a3);

                String saveph = fic;

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
        gj.jmxc(new Runnable() {

            public void run() {

                st.wb(R.id.wb11).zf("立即备份数据至云端");
                st.wb(R.id.wb4).zf("立即从云端恢复数据");

            }

        });
        upu();

    }

    public void upu() {

        final i.runlibrary.app.v.wb wb18 = st.wb(R.id.wb18);

        if (e1.islogin()) {

            final String a3 = AppPaths.appPath("settings/account/user");
            gj.xc(new Thread() {

                public void run() {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            wb18.zf("加载中...");

                        }

                    });

                    String url = pu + "/re/" + wj.dqwb(a3);

                    final String back = wl.hq(url, null, "utf-8", null, true, null, 20000, 20000, null);

                    if (zf.dy(back, "") || zf.dy(back, null)) {

                        gj.jmxc(new Runnable() {

                            public void run() {

                                wb18.zf("未能获取到云端备份信息");

                            }

                        });

                    } else {

                        gj.jmxc(new Runnable() {

                            public void run() {

                                wb18.zf(back);

                            }

                        });

                    }

                }

            });

        } else {

            gj.jmxc(new Runnable() {

                public void run() {

                    wb18.zf("请先登录Typheye账户");

                }

            });

        }

    }

    public void upload(Object a) {

        if (!state_upload) {

            state_upload = true;

            final String a3 = AppPaths.appPath("settings/account/user");

            final String path_ = wj.hqml(a);

            final String[] path = new String[]{path_};

            final i.runlibrary.app.v.wb wb11 = st.wb(R.id.wb11);
            gj.xc(new Thread() {

                public void run() {

                    gj.jmxc(new Runnable() {

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

                            final String url = pu + "/up.php?uid=" + wj.dqwb(a3);

                            String fn__ = "null";

                            String[] fn_ = e1.fileinfo("/", path[0]);

                            if (zf.dy(fn_[0], "true")) {

                                fn__ = fn_[3];

                            }

                            final String file_name = fn__;

                            final long file_size = wj.dx(path_);
                            gj.jmxc(new Runnable() {

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
                                                        wb11.zf("已上传" + IappCompat.zh(sj.zh()).zstring(now) + "%");
                                                    }
                                                })
                                                .setResponseListener(new ResponseListener() {
                                                    @Override
                                                    public void onResponse(String response, Exception error) {
                                                        uploadResult(error, response);
                                                    }
                                                })
                                                .doPost();

                                    } catch (Throwable e) {

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

    public void uploadResult(Exception error, Object upload_back) {

        Object back = upload_back;

        if (!zf.dy(IappCompat.zh(sj.zh()).zstring(error), null)) {

            updata();
            e1.tsk("提示", "上传失败\n" + IappCompat.zh(sj.zh()).zstring(error));

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

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

            if (zf.dy(st.wb(R.id.title_bar).zf(), "< 云端服务")) {

                gj.gb();

            }

        }
        return false;

    }

    private void onTitleBarClick(View vw) {

        if (zf.dy(st.wb(R.id.title_bar).zf(), "< 云端服务")) {

            gj.gb();

        }

    }

    private void onXdbj7Click(View vw) {
        gj.tz(AnnouncementActivity.class);

    }

    private boolean onXdbj7LongClick(View vw) {

        String te = st.wb(R.id.wb14).zf();

        String lj = st.wb(R.id.wb15).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj4Click(View vw) {
        gj.tz(WearChatSettingsActivity.class);

    }

    private boolean onXdbj4LongClick(View vw) {

        String te = st.wb(R.id.wb8).zf();

        String lj = st.wb(R.id.wb9).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj8Click(View vw) {
        e1.goplu("20005", "后台诊断", false);

    }

    private boolean onXdbj8LongClick(View vw) {

        String te = st.wb(R.id.wb5).zf();

        String lj = st.wb(R.id.wb6).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj9Click(View vw) {

        if (zf.dy(st.wb(R.id.wb18).zf(), "加载中...")) {

            e1.tsk("提示", "操作过于频繁，请稍后重试");

        } else {

            String lj = st.wb(R.id.wb18).zf();
            e1.tsk("云端数据", lj);

        }

    }

    private boolean onXdbj9LongClick(View vw) {

        String te = st.wb(R.id.wb17).zf();

        String lj = st.wb(R.id.wb18).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj5Click(View vw) {
        yun_backup();

    }

    private boolean onXdbj5LongClick(View vw) {

        String te = st.wb(R.id.wb10).zf();

        String lj = st.wb(R.id.wb11).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onXdbj3Click(View vw) {
        yun_recovery();

    }

    private boolean onXdbj3LongClick(View vw) {

        String te = st.wb(R.id.wb3).zf();

        String lj = st.wb(R.id.wb4).zf();
        e1.tsk("详细信息", "[名称]\n" + te + "\n\n[简介]\n" + lj);
        return true;

    }

    private void onTx1Click(View vw) {
        gj.gb();

    }

    private void onTx2Click(View vw) {
        qh();

    }

    private void onTx3Click(View vw) {
        gx();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        ImageView i3ab5c5fd62 = (ImageView) findViewById(ay, vw, R.id.tx10);

        RelativeLayout e585d94488 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj7);
        e585d94488.setOnClickListener(xdbj7ClickListener);
        e585d94488.setOnLongClickListener(xdbj7LongClickListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) findViewById(ay, vw, R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);
        i2202ede8ff.setOnLongClickListener(xdbj4LongClickListener);

        RelativeLayout fab146d7a5 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj8);
        fab146d7a5.setOnClickListener(xdbj8ClickListener);
        fab146d7a5.setOnLongClickListener(xdbj8LongClickListener);

        RelativeLayout i5a93676674 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj9);
        i5a93676674.setOnClickListener(xdbj9ClickListener);
        i5a93676674.setOnLongClickListener(xdbj9LongClickListener);

        RelativeLayout c0d3e00d2b = (RelativeLayout) findViewById(ay, vw, R.id.xdbj5);
        c0d3e00d2b.setOnClickListener(xdbj5ClickListener);
        c0d3e00d2b.setOnLongClickListener(xdbj5LongClickListener);

        RelativeLayout i20c2777fab = (RelativeLayout) findViewById(ay, vw, R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);
        i20c2777fab.setOnLongClickListener(xdbj3LongClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
