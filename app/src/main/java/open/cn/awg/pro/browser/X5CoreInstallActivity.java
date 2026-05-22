/**
 ******************************************************************************
 * @file    X5CoreInstallActivity.java
 * @author  Typheye
 * @brief   Installs or manages the Tencent X5 browser core.
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
package open.cn.awg.pro.browser;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import java.io.File;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.download.DownloadProgressActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.repair.ErrorMessageActivity;
import open.cn.awg.pro.tools.ShellExecutorActivity;

public class X5CoreInstallActivity extends BaseAwgActivity {

    public final X5CoreInstallActivity lei = this, 类 = this;
    public String um = "";
    public String osArch;
    public File f;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnClickListener an4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn4Click(vw);
        }

    };
    private final View.OnClickListener an2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn2Click(vw);
        }

    };
    private final View.OnClickListener an3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn3Click(vw);
        }

    };
    private final View.OnClickListener an1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn1Click(vw);
        }

    };
    private final View.OnTouchListener bjk2TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjk2Touch(vw, me);
        }

    };
    private final AdapterView.OnItemSelectedListener xlcd2ItemSelectedListener = new AdapterView.OnItemSelectedListener() {

        public void onItemSelected(AdapterView vw, View view, int pn, long id) {
            onXlcd2ItemSelected(vw, view, pn, id);
        }

        public void onNothingSelected(AdapterView vw) {
            onXlcd2NothingSelected(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.x5_core_install);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        if (e1.isInstallX5()) {

            show(2);

            String path2 = AppPaths.externalFilesPath("Download/x5install.zip");

            String path1 = "/storage/emulated/0/Download/x5install.zip";

            if (wj.cz(path1)) {

                um = path1;

            } else if (wj.cz(path2)) {

                um = path2;

            }

        } else {

            gj.xc(new Thread() {

                public void run() {

                    show(4);
                    osArch = System.getProperty("os.arch");

                    String path3_32 = "@resource/2c951a41d7c917f0a76ae46ed499d2ff";

                    String path3_64 = "@resource/e0a8f35f2676b288f562b8aaf29de9d1";

                    String path2 = AppPaths.externalFilesPath("Download/x5install.zip");

                    String path1 = "/storage/emulated/0/Download/x5install.zip";

                    if (wj.cz(path1) || wj.cz(path2) || wj.cz(path3_32) || wj.cz(path3_64)) {

                        if (wj.cz(path1)) {

                            um = path1;

                        } else if (wj.cz(path2)) {

                            um = path2;

                        } else {

                            if (zf.cz(osArch, "64")) {

                                wj.fz(path3_64, path2, true);
                                um = path2;

                            } else {

                                wj.fz(path3_32, path2, true);
                                um = path2;

                            }

                        }
                        show(1);

                    } else {

                        i.runlibrary.app.v.xlcd xlcd2 = st.xlcd(R.id.xlcd2);

                        String[] sz1 = new String[]{"源1:X5内核32位[81.1M]", "源2:X5内核32位[81.1M]", "自定义下载源"};

                        String[] sz2 = new String[]{"源1:X5内核64位[92.2M]", "源2:X5内核64位[92.2M]", "自定义下载源"};

                        if (zf.cz(osArch, "64")) {

                            xlcd2.csh(sz2);

                        } else {

                            xlcd2.csh(sz1);

                        }
                        show(0);

                    }

                }

            });

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.x5_core_install_root, R.id.xdbj1, R.id.title_bar);

        i.runlibrary.app.v.xlcd xlcd1 = st.xlcd(R.id.xlcd1);

        String[] sz = new String[]{"使用系统下载器下载", "使用软件下载器下载"};
        xlcd1.csh(sz);

    }

    public void c1() {
        gj.xc(new Thread() {

            public void run() {

                show(4);

                final int y = wj.sc(um);

                if (y == 1) {

                    e1.tsk("提示", "已清除");

                } else {

                    e1.tsk("提示", "内核包不存在");

                }
                show(2);

            }

        });

    }

    public void c2() {
        gj.xc(new Thread() {

            public void run() {

                show(4);

                String xx = "内核删除执行结果如下，应用将在2秒后重启以完成卸载！\n\n";

                String[] d = new String[]{AppPaths.appPath("app_tbs"), AppPaths.appPath("app_tbs_64"), AppPaths.appPath("app_tbs_common_share"), AppPaths.appPath("shared_prefs")};

                for (int i = 0; i < 4; i++) {

                    String m = "rm -rf \"" + d[i] + "\"";

                    String[] aa = new String[]{"", ""};
                    aa = com.demo.e3.cmd(lei, m, false);

                    if (zf.dy(aa[0], "") && !zf.dy(aa[1], "")) {

                        xx = xx + "[文件路径]\n" + d[i] + "\n[执行状态]\n删除失败：" + aa[1] + "\n\n";

                    } else if (!zf.dy(aa[0], "") && zf.dy(aa[1], "")) {

                        xx = xx + "[文件路径]\n" + d[i] + "\n[执行状态]\n删除成功\n\n";

                    } else {

                        xx = xx + "[文件路径]\n" + d[i] + "\n[执行状态]\n删除成功\n\n";

                    }

                }
                gj.zt(2000);
                gj.jmxc(new Runnable() {

                    public void run() {

                        String[] name = new String[]{"Msg"};

                        String[] value = new String[]{"卸载成功 点击重启"};
                        gj.tz(ErrorMessageActivity.class, name, value);

                    }

                });

            }

        });

    }

    public void show(int i_) {

        final int i = i_;
        gj.jmxc(new Runnable() {

            public void run() {

                i.runlibrary.app.v.xxbj x1 = st.xxbj(R.id.xxbj2);

                i.runlibrary.app.v.xxbj x2 = st.xxbj(R.id.xxbj4);

                i.runlibrary.app.v.xxbj x3 = st.xxbj(R.id.xxbj5);

                i.runlibrary.app.v.xxbj x4 = st.xxbj(R.id.xxbj6);

                if (i == 0) {

                    x1.kjd(0);
                    x2.kjd(8);
                    x3.kjd(8);
                    x4.kjd(8);

                } else if (i == 1) {

                    x1.kjd(8);
                    x2.kjd(0);
                    x3.kjd(8);
                    x4.kjd(8);

                } else if (i == 2) {

                    x1.kjd(8);
                    x2.kjd(8);
                    x3.kjd(0);
                    x4.kjd(8);

                } else if (i == 4) {

                    x1.kjd(8);
                    x2.kjd(8);
                    x3.kjd(8);
                    x4.kjd(0);

                }

            }

        });

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

    private void onXlcd2ItemSelected(AdapterView vw, View view, int pn, long id) {

        if (pn == 2) {

            st.bjk(R.id.bjk2).kjd(0);

        } else {

            st.bjk(R.id.bjk2).kjd(8);

        }

    }

    private void onXlcd2NothingSelected(AdapterView vw) {

    }

    private boolean onBjk2Touch(View vw, MotionEvent me) {

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

        String bjk2 = st.bjk(R.id.bjk2).zf();
        bjk2 = zf.qctwkg(bjk2);

        i.runlibrary.app.v.xlcd xlcd1 = st.xlcd(R.id.xlcd1);

        i.runlibrary.app.v.xlcd xlcd2 = st.xlcd(R.id.xlcd2);

        String url = "";

        String path = "";

        String path2 = AppPaths.externalFilesPath("Download/");

        String path1 = "/storage/emulated/0/Download/";

        String add = "x5install.zip";

        int sdk = xt.sbxx().sdk;

        if (sdk < 30) {

            path = path1 + add;

        } else {

            path = path2 + add;

        }

        if (zf.cz(osArch, "64")) {

            if (xlcd2.dqzsxmxh() == 0) {

                url = "https://service.typheye.cn/app/open.cn.awg.pro/download/x5s/x5install_64bit.zip";

            } else if (xlcd2.dqzsxmxh() == 1) {

                url = "https://service.typheye.cn/app/open.cn.awg.pro/download/x5s/x5install_64bit.zip";

            } else if (xlcd2.dqzsxmxh() == 2) {

                url = bjk2;

            }

        } else {

            if (xlcd2.dqzsxmxh() == 0) {

                url = "https://service.typheye.cn/app/open.cn.awg.pro/download/x5s/x5install_32bit.zip";

            } else if (xlcd2.dqzsxmxh() == 1) {

                url = "https://service.typheye.cn/app/open.cn.awg.pro/download/x5s/x5install_32bit.zip";

            } else if (xlcd2.dqzsxmxh() == 2) {

                url = bjk2;

            }

        }

        if (zf.dy(e1.urlLockString(url), "0")) {

            e1.tsk("提示", "下载源无效");

        } else {

            i.runlibrary.app.xt$qxgl a = xt.qxgl();

            if (true) {

                if (xlcd1.dqzsxmxh() == 0) {

                    f = wj.wj(path);
                    gl.xz.download(url, this, 1, f);
                    e1.tsk("提示", "已在后台开始下载\n请在下载完成后重新进入本页面,进行下一步的安装");
                    gj.gb();

                } else if (xlcd1.dqzsxmxh() == 1) {

                    String[] name = new String[]{"url", "path"};

                    String[] value = new String[]{url, path};
                    gj.tz(DownloadProgressActivity.class, name, value);
                    e1.tsk("提示", "已开始下载\n请在下载完成后重新进入本页面,进行下一步的安装");
                    gj.gb();

                }

            } else {

                e1.tsk("提示", "您未授权应用\"修改或删除您共享存储空间中的内容\"，请先前往设置授权后再使用");

            }

        }

    }

    private void onAn3Click(View vw) {
        show(4);
        gj.xc(new Thread() {

            public void run() {

                String nwj = AppPaths.appPath("cache/x5/");
                wj.jy(um, nwj, true);

                String nrs = wj.dqwb(AppPaths.appPath("data/assets/c1"));

                final String[] name = new String[]{"sh", "auto"};

                final String[] value = new String[]{nrs, "enable"};
                gj.jmxc(new Runnable() {

                    public void run() {

                        gj.tz(ShellExecutorActivity.class, name, value);

                    }

                });
                gj.gb();

            }

        });

    }

    private void onAn2Click(View vw) {
        c1();

    }

    private void onAn4Click(View vw) {
        c2();

    }

    private void onTx1Click(View vw) {
        gj.gb();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        Spinner fef1635c8d = (Spinner) findViewById(ay, vw, R.id.xlcd2);
        fef1635c8d.setOnItemSelectedListener(xlcd2ItemSelectedListener);

        EditText i63f06183a1 = (EditText) findViewById(ay, vw, R.id.bjk2);
        i63f06183a1.setOnTouchListener(bjk2TouchListener);

        Spinner i004a739c93 = (Spinner) findViewById(ay, vw, R.id.xlcd1);

        Button i171ea34f1a = (Button) findViewById(ay, vw, R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        Button i8f51a35692 = (Button) findViewById(ay, vw, R.id.an3);
        i8f51a35692.setOnClickListener(an3ClickListener);

        Button i0b66a667b1 = (Button) findViewById(ay, vw, R.id.an2);
        i0b66a667b1.setOnClickListener(an2ClickListener);

        Button i74ce6319f3 = (Button) findViewById(ay, vw, R.id.an4);
        i74ce6319f3.setOnClickListener(an4ClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
