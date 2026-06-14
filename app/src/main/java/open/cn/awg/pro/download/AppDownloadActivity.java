/**
 ******************************************************************************
 * @file    AppDownloadActivity.java
 * @author  Typheye
 * @brief   Application download screen.
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
package open.cn.awg.pro.download;


import open.cn.awg.pro.core.IappCompat;
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
import open.cn.awg.pro.file.FileManagerActivity;
import open.cn.awg.pro.R;

public class AppDownloadActivity extends BaseAwgActivity {

    public final AppDownloadActivity lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public File f;
    public String ty = "";
    public String appid = "";
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnClickListener an1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn1Click(vw);
        }

    };
    private final AdapterView.OnItemSelectedListener xlcd1ItemSelectedListener = new AdapterView.OnItemSelectedListener() {

        public void onItemSelected(AdapterView vw, View view, int pn, long id) {
            onXlcd1ItemSelected(vw, view, pn, id);
        }

        public void onNothingSelected(AdapterView vw) {
            onXlcd1NothingSelected(vw);
        }

    };
    private final View.OnTouchListener bjk1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjk1Touch(vw, me);
        }

    };
    private final View.OnClickListener an4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn4Click(vw);
        }

    };
    private final View.OnClickListener an3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn3Click(vw);
        }

    };
    private final View.OnClickListener an2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn2Click(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.app_download);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        String shs = sj.hqtz("url");
        shs = zf.qctwkg(shs);
        ty = sj.hqtz("type");
        appid = sj.hqtz("appid");

        if (!zf.dy(shs, null) && !zf.dy(shs, "")) {

            st.bjk(R.id.bjk1).zf(shs);
            st.bjk(R.id.bjk1).kjd(8);

        } else {

            st.bjk(R.id.bjk1).kjd(0);

        }

        if (zf.dy(ty, "app")) {

            st.wb(R.id.title_bar).zf("< 应用下载");
            st.an(R.id.an2).kjd(8);
            st.an(R.id.an3).kjd(8);

            String path = AppPaths.externalFilesPath("app/download/") + appid + "/base.apk";

            if (wj.cz(path)) {

                st.an(R.id.an4).kjd(0);

            }

        }

        i.runlibrary.app.v.xlcd xlcd1 = st.xlcd(R.id.xlcd1);

        if (zf.dy(ty, "app")) {

            String[] sz = new String[]{"使用系统下载器", "使用软件下载器"};
            xlcd1.csh(sz);

        } else {

            String[] sz = new String[]{"使用系统下载器", "使用系统下载器[自定义]", "使用软件下载器"};
            xlcd1.csh(sz);

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.app_download_root, R.id.xdbj1, R.id.title_bar);

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

    private void onAn2Click(View vw) {

        String ftitle = AppPaths.appPath("cache/file/title");
        wj.xrwb(ftitle, "< 下载目录");

        String path = AppPaths.externalFilesPath("Download/");

        final String[] name = new String[]{"url", "root"};

        final String[] value = new String[]{path, path};
        gj.jmxc(new Runnable() {

            public void run() {

                gj.tz(FileManagerActivity.class, name, value);

            }

        });

    }

    private void onAn3Click(View vw) {

        String ftitle = AppPaths.appPath("cache/file/title");
        wj.xrwb(ftitle, "< 下载目录");

        String path = "/storage/emulated/0/Download/";

        final String[] name = new String[]{"url", "root"};

        final String[] value = new String[]{path, path};
        gj.jmxc(new Runnable() {

            public void run() {

                gj.tz(FileManagerActivity.class, name, value);

            }

        });

    }

    private void onAn4Click(View vw) {

        String ftitle = AppPaths.appPath("cache/file/title");
        wj.xrwb(ftitle, "< 下载目录");

        String path = AppPaths.externalFilesPath("app/download/") + appid + "/";
        wj.xrwb(path + ".nomedia", "");
        wj.sc(path + ".nomedia");

        final String[] name = new String[]{"url", "root"};

        final String[] value = new String[]{path, path};
        gj.jmxc(new Runnable() {

            public void run() {

                gj.tz(FileManagerActivity.class, name, value);

            }

        });

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

    private void onXlcd1ItemSelected(AdapterView vw, View view, int pn, long id) {

        if (zf.dy(ty, "app")) {

            st.bjk(R.id.bjk2).kjd(8);

        } else {

            if (pn == 1 || pn == 2) {

                String path2 = AppPaths.externalFilesPath("Download/");

                String path1 = "/storage/emulated/0/Download/";

                String add = ".file";

                int sdk = IappCompat.sdk(xt.sbxx());

                if (sdk < 30) {

                    st.bjk(R.id.bjk2).zf(path1 + xt.sj(4) + add);

                } else {

                    st.bjk(R.id.bjk2).zf(path2 + xt.sj(4) + add);

                }
                st.bjk(R.id.bjk2).kjd(0);

            } else {

                st.bjk(R.id.bjk2).kjd(8);

            }

        }

    }

    private void onXlcd1NothingSelected(AdapterView vw) {

    }

    private void onAn1Click(View vw) {

        String bjk1 = st.bjk(R.id.bjk1).zf();
        bjk1 = zf.qctwkg(bjk1);

        String bjk2 = st.bjk(R.id.bjk2).zf();
        bjk2 = zf.qctwkg(bjk2);

        i.runlibrary.app.v.xlcd xlcd1 = st.xlcd(R.id.xlcd1);

        if (zf.dy(bjk1, "")) {

            e1.tsk("提示", "文件下载链接不能为空");

        } else {

            if (zf.dy(e1.urlLockString(bjk1), "0")) {

                e1.tsk("提示", "文件下载链接无效");

            } else {

                i.runlibrary.app.xt$qxgl a = (i.runlibrary.app.xt$qxgl) (Object) xt.qxgl();

                if (zf.dy(ty, "app")) {

                    if (xlcd1.dqzsxmxh() == 0) {

                        String path = AppPaths.externalFilesPath("app/download/") + appid + "/base.apk";
                        f = wj.wj(path);
                        gl.xz.download(bjk1, this, 1, f);
                        e1.tsk("提示", "已在后台开始下载");
                        st.xlcd(R.id.xlcd1).kjd(8);
                        st.an(R.id.an1).kjd(8);
                        st.an(R.id.an4).kjd(0);

                    } else if (xlcd1.dqzsxmxh() == 1) {

                        bjk2 = AppPaths.externalFilesPath("app/download/") + appid + "/base.apk";

                        String[] name = new String[]{"url", "path"};

                        String[] value = new String[]{bjk1, bjk2};
                        gj.tz(DownloadProgressActivity.class, name, value);
                        st.xlcd(R.id.xlcd1).kjd(8);
                        st.an(R.id.an1).kjd(8);
                        st.an(R.id.an4).kjd(0);

                    }

                } else {

                    if (xlcd1.dqzsxmxh() == 0) {

                        gl.xz.download(bjk1, this, 0, f);
                        e1.tsk("提示", "已在后台开始下载");
                        gj.gb();

                    } else if (xlcd1.dqzsxmxh() == 1) {

                        if (zf.dy(bjk2, "")) {

                            e1.tsk("提示", "文件保存路径不能为空");

                        } else {

                            f = wj.wj(bjk2);
                            gl.xz.download(bjk1, this, 1, f);
                            e1.tsk("提示", "已在后台开始下载");
                            gj.gb();

                        }

                    } else if (xlcd1.dqzsxmxh() == 2) {

                        if (zf.dy(bjk2, "")) {

                            e1.tsk("提示", "文件保存路径不能为空");

                        } else {

                            String[] name = new String[]{"url", "path"};

                            String[] value = new String[]{bjk1, bjk2};
                            gj.tz(DownloadProgressActivity.class, name, value);
                            gj.gb();

                        }

                    }

                }

            }

        }

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

        Button i0b66a667b1 = (Button) findViewById(ay, vw, R.id.an2);
        i0b66a667b1.setOnClickListener(an2ClickListener);

        Button i8f51a35692 = (Button) findViewById(ay, vw, R.id.an3);
        i8f51a35692.setOnClickListener(an3ClickListener);

        Button i74ce6319f3 = (Button) findViewById(ay, vw, R.id.an4);
        i74ce6319f3.setOnClickListener(an4ClickListener);

        EditText i32ee139ba1 = (EditText) findViewById(ay, vw, R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        Spinner i004a739c93 = (Spinner) findViewById(ay, vw, R.id.xlcd1);
        i004a739c93.setOnItemSelectedListener(xlcd1ItemSelectedListener);

        Button i171ea34f1a = (Button) findViewById(ay, vw, R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
