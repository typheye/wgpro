/**
 ******************************************************************************
 * @file    FileOpenOptionItemActivity.java
 * @author  Typheye
 * @brief   Routes file open choices to the proper viewer, player, editor, or installer.
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
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.core.GlobalRuntimeState;
import open.cn.awg.pro.file.FileOpenActivity;
import open.cn.awg.pro.installer.ApkPreviewActivity;
import open.cn.awg.pro.installer.PackageInstallerActivity;
import open.cn.awg.pro.media.VideoPlayerActivity;
import open.cn.awg.pro.media.WearMusicActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.tools.AccessibilityScriptActivity;
import open.cn.awg.pro.tools.ShellExecutorActivity;
import open.cn.awg.pro.viewer.ImageViewerActivity;
import open.cn.awg.pro.viewer.TextEditorActivity;
import open.cn.awg.pro.viewer.TextViewerActivity;

public class FileOpenOptionItemActivity extends BaseAwgActivity {

    public final FileOpenOptionItemActivity lei = this, 类 = this;
    private final View.OnLongClickListener xxbj1LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXxbj1LongClick(vw);
        }

    };
    private final View.OnClickListener xxbj1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj1Click(vw);
        }

    };

    private void onXxbj1Click(View vw) {

        final AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        try {

            final i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) (Object) sj.lb(vw);

            final Object a = lb.lbcfsj(R.id.wb1);

            final Object n = lb.lbcfsj(R.id.wb2);

            final String es = st.wb(R.id.zywblj).zf();

            final String c = String.valueOf(lb.lbcfsj(R.id.wb3));
            gj.xc(new Thread() {

                public void run() {

                    String d1_rootpaths;
                    d1_rootpaths = GlobalRuntimeState.d1_rootpath;

                    if (zf.dy(n, "-1")) {

                        e1.cdx(0, null, null, null);

                    } else if (zf.dy(n, "0")) {

                        e1.cdx(6, null, c, null);

                    } else if (zf.dy(n, "a") || zf.dy(n, "b") || zf.dy(n, "c") || zf.dy(n, "d") || zf.dy(n, "e") || zf.dy(n, "f") || zf.dy(n, "g")) {

                        e1.dkfs(n, c, 0);

                    } else {

                        if (zf.dy(n, "a1")) {

                            final String[] name = new String[]{"url"};

                            final String[] value = new String[]{c};
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    gj.tz(TextViewerActivity.class, name, value);

                                }

                            });

                        } else if (zf.dy(n, "a2")) {

                            final String[] name = new String[]{"url"};

                            final String[] value = new String[]{c};
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    String[] name = new String[]{"url"};

                                    String[] value = new String[]{c};
                                    gj.tz(TextEditorActivity.class, name, value);

                                }

                            });

                        } else if (zf.dy(n, "b1")) {

                            final String[] name = new String[]{"url"};

                            final String[] value = new String[]{c};
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    gj.tz(ImageViewerActivity.class, name, value);

                                }

                            });

                        } else if (zf.dy(n, "c1")) {

                            final String[] name = new String[]{"url"};

                            final String[] value = new String[]{c};
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    gj.tz(WearMusicActivity.class, name, value);

                                }

                            });

                        } else if (zf.dy(n, "c2")) {

                            final String[] name = new String[]{"url"};

                            final String[] value = new String[]{c};
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    gj.tz(VideoPlayerActivity.class, name, value);

                                }

                            });

                        } else if (zf.dy(n, "c3")) {

                            String pa1 = "/HankMi/cache/music/keydata.hmd";
                            pa1 = wj.hqml("%") + pa1;

                            String nr = "hmmedia=" + c;
                            wj.xrwb(pa1, nr);
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    try {

                                        xt.dkyy("com.hankmi.wearmusic");

                                    } catch (Throwable e) {

                                        e1.tsk("提示", "打开异常\n" + e);

                                    }

                                }

                            });

                        } else if (zf.dy(n, "d2")) {

                            final String[] name = new String[]{"url"};

                            final String[] value = new String[]{c};
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    gj.tz(VideoPlayerActivity.class, name, value);

                                }

                            });

                        } else if (zf.dy(n, "d3")) {

                            final String url = c;

                            String[] fhValue = e1.fileinfo(d1_rootpaths, c);

                            String bts = fhValue[3];

                            final String title = bts;

                            try {

                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        boolean fhs = true;

                                        Context context = AwgProApplication.getContext();
                                        fhs = com.demo.e4.wearvideo(context, url, title);

                                        if (!fhs) {

                                            e1.tsk("提示", "打开异常");

                                        }

                                    }

                                });

                            } catch (Throwable e) {

                                e1.tsk("提示", "打开异常\n" + e);

                            }

                        } else if (zf.dy(n, "d4")) {

                            String pa1 = "/HankMi/cache/media/keydata.hmd";
                            pa1 = wj.hqml("%") + pa1;

                            String nr = "hmmedia=" + c;
                            wj.xrwb(pa1, nr);
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    try {

                                        xt.dkyy("com.hankmi.media");

                                    } catch (Throwable e) {

                                        e1.tsk("提示", "打开异常\n" + e);

                                    }

                                }

                            });

                        } else if (zf.dy(n, "e1")) {

                            String[] fhValue = e1.fileinfo(d1_rootpaths, c);

                            final String lj = fhValue[2];

                            final String c1 = c;
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    st.wb(R.id.title_bar).zf("提示");
                                    st.xxbj(R.id.xxbj9).kjd(0);

                                }

                            });

                            if (wj.jy(c1, lj, true) > 0) {

                                e1.tsk("提示", "解压成功");

                            } else {

                                e1.tsk("提示", "解压失败");

                            }
                            e1.sx(c, es);

                        } else if (zf.dy(n, "f1")) {

                            final String[] name = new String[]{"url"};

                            final String[] value = new String[]{c};
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    gj.tz(ApkPreviewActivity.class, name, value);

                                }

                            });

                        } else if (zf.dy(n, "f2")) {

                            String b = wj.hqml("%");

                            String kl = zf.th(c, b, "%");

                            final String[] name = new String[]{"url", "mode"};

                            final String[] value = new String[]{c, "0"};
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    gj.tz(PackageInstallerActivity.class, name, value);

                                }

                            });

                        } else if (zf.dy(n, "f3")) {

                            String b = wj.hqml("%");

                            String kl = zf.th(c, b, "%");

                            final String[] name = new String[]{"url", "mode"};

                            final String[] value = new String[]{c, "1"};
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    gj.tz(PackageInstallerActivity.class, name, value);

                                }

                            });

                        } else if (zf.dy(n, "g1")) {

                            String b = wj.hqml("%");

                            String kl = zf.th(c, b, "%");

                            final String[] name = new String[]{"url"};

                            final String[] value = new String[]{c};
                            gj.jmxc(new Runnable() {

                                public void run() {

                                    gj.tz(FileOpenActivity.class, name, value);

                                }

                            });

                        } else if (zf.dy(n, "g2")) {

                            e1.tsk("提示", "腕管Pro已不再支持应用主题文件");

                        } else if (zf.dy(n, "g3")) {

                            if (wj.dx(c) > 102400) {

                                e1.tsk("提示", "文件过大,无法打开");

                            } else {

                                String nrs = wj.dqwb(c);

                                final String[] name = new String[]{"cmd"};

                                final String[] value = new String[]{nrs};
                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        gj.tz(AccessibilityScriptActivity.class, name, value);

                                    }

                                });

                            }

                        } else if (zf.dy(n, "g4")) {

                            if (wj.dx(c) > 102400) {

                                e1.tsk("提示", "文件过大,无法打开");

                            } else {

                                String nrs = wj.dqwb(c);

                                final String[] name = new String[]{"sh"};

                                final String[] value = new String[]{nrs};
                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        gj.tz(ShellExecutorActivity.class, name, value);

                                    }

                                });

                            }

                        } else if (zf.dy(n, "g5")) {

                            e1.tsk("提示", "请前往 应用设置>关于应用>应用备份 页面恢复应用备份");

                        } else if (zf.dy(n, "h1") || zf.dy(n, "h2") || zf.dy(n, "h3") || zf.dy(n, "h4")) {

                            String choose = AppPaths.appPath("cache/file/choose");
                            wj.sc(choose);
                            wj.xrwb(AppPaths.appPath("cache/chat/upload/path"), c);
                            gj.gb();

                        } else if (zf.dy(n, "i1")) {

                            String choose2 = AppPaths.appPath("cache/file/choose2");
                            wj.sc(choose2);
                            wj.xrwb(AppPaths.appPath("cache/user/upload/path"), c);
                            gj.gb();

                        } else {

                            e1.tsk("提示", "未找到该打开方式");

                        }
                        e1.cdx(0, null, null, null);

                    }

                }

            });

        } catch (Throwable e) {

            e1.upload_error(e, "c6.*");

        }

    }

    private boolean onXxbj1LongClick(View vw) {

        i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) (Object) sj.lb(vw);

        Object a = lb.lbcfsj(R.id.wb1);

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
        e1.tsk("详细信息", a);
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        LinearLayout e1433e26d6 = (LinearLayout) findViewById(ay, vw, R.id.xxbj1);
        e1433e26d6.setOnClickListener(xxbj1ClickListener);
        e1433e26d6.setOnLongClickListener(xxbj1LongClickListener);

        RelativeLayout aaeebbd136 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj1);

        RelativeLayout i9f15e08dfa = (RelativeLayout) findViewById(ay, vw, R.id.file_open_option_item_root);

        __layoutIsLoaded(ay, vw);
    }
}
