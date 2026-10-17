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
    public AppRuntimeBridge e1;
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
;
    private final View.OnClickListener xdbj3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj3Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj5ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj5Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj9ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj9Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj8ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj8Click(vw);
        }

    };
;
    private final View.OnClickListener xdbj4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj4Click(vw);
        }

    };
;
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
        e1 = new AppRuntimeBridge(this);
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

            if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.wb11)).getText().toString(), "加载中...")) {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            } else {

                mode = 1;
                qh();

            }

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(CloudServiceActivity.this, (Class<?>) (TypheyeAccountActivity.class));
                    e1.tsk("提示", "该操作需要登录Typheye账户");

                }

            });

        }

    }

    public void yun_recovery() {

        if (e1.islogin()) {

            if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.wb4)).getText().toString(), "加载中...")) {

                e1.tsk("提示", "操作过于频繁，请稍后重试");

            } else {

                recovery();

            }

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.launch(CloudServiceActivity.this, (Class<?>) (TypheyeAccountActivity.class));
                    e1.tsk("提示", "该操作需要登录Typheye账户");

                }

            });

        }

    }

    public void qh() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.xxbj1)).getVisibility() == android.view.View.VISIBLE) {

                    if (mode == 1) {

                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.wb1)).setText(String.valueOf("您确定要备份数据到云端吗？"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("提示"));

                    } else if (mode == 2) {

                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(android.view.View.VISIBLE);
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.wb1)).setText(String.valueOf("已下载备份数据，您确定要恢复吗？"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("提示"));

                    }

                } else {

                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.xxbj4)).setVisibility(android.view.View.GONE);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.wb1)).setText(String.valueOf(""));
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.title_bar)).setText(String.valueOf("云端服务"));

                }

            }

        });

    }

    public void gx() {

        if (mode == 1) {

            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    if (e1.app_backup2()) {

                        upload(fib);

                    }

                }

            });

        } else if (mode == 2) {

            open.cn.awg.pro.core.NativeActions.start(new Thread() {

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
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.wb4)).setText(String.valueOf("加载中..."));

                    }

                });
                open.cn.awg.pro.core.NativeFiles.writeText(CloudServiceActivity.this, (String) (fictmp), (String) (""));
                open.cn.awg.pro.core.NativeFiles.delete(CloudServiceActivity.this, (String) (fictmp), false);

                String url = pu + "/user/" + open.cn.awg.pro.core.NativeFiles.readText(CloudServiceActivity.this, (String) (a3));

                String saveph = fic;

                int back = open.cn.awg.pro.core.NativeHttp.download(CloudServiceActivity.this, String.valueOf(url), (String) (saveph), java.lang.Boolean.TRUE.equals(true), null, "UTF-8", null, false, null, 0, 0, null);

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
        open.cn.awg.pro.core.NativeFiles.delete(CloudServiceActivity.this, (String) (fib), false);
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.wb11)).setText(String.valueOf("立即备份数据至云端"));
                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.wb4)).setText(String.valueOf("立即从云端恢复数据"));

            }

        });
        upu();

    }

    public void upu() {

        final android.widget.TextView wb18 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.wb18));

        if (e1.islogin()) {

            final String a3 = AppPaths.appPath("settings/account/user");
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            wb18.setText(String.valueOf("加载中..."));

                        }

                    });

                    String url = pu + "/re/" + open.cn.awg.pro.core.NativeFiles.readText(CloudServiceActivity.this, (String) (a3));

                    final String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                    if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                wb18.setText(String.valueOf("未能获取到云端备份信息"));

                            }

                        });

                    } else {

                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                wb18.setText(String.valueOf(back));

                            }

                        });

                    }

                }

            });

        } else {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    wb18.setText(String.valueOf("请先登录Typheye账户"));

                }

            });

        }

    }

    public void upload(Object a) {

        if (!state_upload) {

            state_upload = true;

            final String a3 = AppPaths.appPath("settings/account/user");

            final String path_ = open.cn.awg.pro.core.NativeFiles.resolvePath(CloudServiceActivity.this, (String) (a));

            final String[] path = new String[]{path_};

            final android.widget.TextView wb11 = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.wb11));
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            wb11.setText(String.valueOf("加载中..."));

                        }

                    });
                    upload_back = "";

                    if (e1.yz()) {

                        if (java.util.Objects.equals(path_, "") || !open.cn.awg.pro.core.NativeFiles.exists(CloudServiceActivity.this, (String) (path_))) {

                            updata();
                            e1.tsk("提示", "文件路径无效");

                        } else {

                            final String url = pu + "/up.php?uid=" + open.cn.awg.pro.core.NativeFiles.readText(CloudServiceActivity.this, (String) (a3));

                            String fn__ = "null";

                            String[] fn_ = e1.fileinfo("/", path[0]);

                            if (java.util.Objects.equals(fn_[0], "true")) {

                                fn__ = fn_[3];

                            }

                            final String file_name = fn__;

                            final long file_size = open.cn.awg.pro.core.NativeFiles.size(CloudServiceActivity.this, (String) (path_));
                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    try {

                                        HttpRequest.TIME_OUT_DURATION = 300;
                                        HttpRequest.build(lei, url)
                                                .addParameter("name", file_name)
                                                .addParameter("size", file_size)
                                                .addParameter("file", open.cn.awg.pro.core.NativeFiles.file(CloudServiceActivity.this, (String) (path_)))
                                                .setUploadProgressListener(new UploadProgressListener() {
                                                    @Override
                                                    public void onUpload(float percentage, long current, long total, boolean done) {

                                                        long now = current / total * 100;
                                                        wb11.setText(String.valueOf("已上传" + open.cn.awg.pro.core.TextValues.string(now) + "%"));
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

        if (!java.util.Objects.equals(open.cn.awg.pro.core.TextValues.string(error), null)) {

            updata();
            e1.tsk("提示", "上传失败\n" + open.cn.awg.pro.core.TextValues.string(error));

        } else {

            if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

            if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "云端服务")) {

                open.cn.awg.pro.core.NativeActions.finish(CloudServiceActivity.this);

            }

        }
        return false;

    }

    private void onTitleBarClick(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.title_bar)).getText().toString(), "云端服务")) {

            open.cn.awg.pro.core.NativeActions.finish(CloudServiceActivity.this);

        }

    }

    private void onXdbj7Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(CloudServiceActivity.this, (Class<?>) (AnnouncementActivity.class));

    }



    private void onXdbj4Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(CloudServiceActivity.this, (Class<?>) (WearChatSettingsActivity.class));

    }



    private void onXdbj8Click(View vw) {
        e1.goplu("20005", "后台诊断", false);

    }



    private void onXdbj9Click(View vw) {

        if (java.util.Objects.equals(((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.wb18)).getText().toString(), "加载中...")) {

            e1.tsk("提示", "操作过于频繁，请稍后重试");

        } else {

            String lj = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(CloudServiceActivity.this.getWindow().getDecorView(), R.id.wb18)).getText().toString();
            e1.tsk("云端数据", lj);

        }

    }



    private void onXdbj5Click(View vw) {
        yun_backup();

    }



    private void onXdbj3Click(View vw) {
        yun_recovery();

    }



    private void onTx1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(CloudServiceActivity.this);

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

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        ImageView i3ab5c5fd62 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx10);

        RelativeLayout e585d94488 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj7);
        e585d94488.setOnClickListener(xdbj7ClickListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);

        RelativeLayout fab146d7a5 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj8);
        fab146d7a5.setOnClickListener(xdbj8ClickListener);

        RelativeLayout i5a93676674 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj9);
        i5a93676674.setOnClickListener(xdbj9ClickListener);

        RelativeLayout c0d3e00d2b = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj5);
        c0d3e00d2b.setOnClickListener(xdbj5ClickListener);

        RelativeLayout i20c2777fab = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
