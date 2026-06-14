/**
 ******************************************************************************
 * @file    SystemPermissionActivity.java
 * @author  Typheye
 * @brief   Manages device-policy and privileged system permission actions.
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.browser.X5BrowserActivity;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.core.DevicePolicyBridge;
import open.cn.awg.pro.file.FileManagerActivity;
import open.cn.awg.pro.media.VideoPlayerActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.repair.ErrorMessageActivity;
import open.cn.awg.pro.store.ProductPurchaseActivity;
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;
import open.cn.awg.pro.viewer.TextViewerActivity;

public class SystemPermissionActivity extends BaseAwgActivity {

    public final SystemPermissionActivity lei = this, 类 = this;
    public String lin;
    public String ujback;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public DevicePolicyBridge carton = new DevicePolicyBridge(_APPINFO);
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnLongClickListener backsLongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onBacksLongClick(vw);
        }

    };
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
    private final View.OnTouchListener bjk1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjk1Touch(vw, me);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.system_permission);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        i.runlibrary.app.xt$pm pm = (i.runlibrary.app.xt$pm) (Object) xt.pm();

        if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4s.inf")), "false")) {

            if (zf.dy(wj.dqwb(AppPaths.appPath("settings/set4.inf")), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = wj.dqwb(a2);

        final int u = Integer.parseInt(wj.dqwb(AppPaths.appPath("settings/f17/set1.inf")));

        if (zf.dy(a, "4")) {

            st.xdbj(R.id.system_permission_root).shxtck(true);

            String clr = zf.zf(R.color.colorTab);
            setStatusBarColor(clr);

        } else {

            st.xdbj(R.id.system_permission_root).shxtck(false);
        applyFullscreenWindow();

        }

        if (zf.dy(a, "1")) {

            st.xdbj(R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "2")) {

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (wj.cz(a001) || zf.dy(wj.dqwb(a001), "true")) {

                i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
                wtab.nbj(0, "3dp", 0, "3dp");
                wtab.dqfs("center");

            } else {

                i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
                wtab.nbj(0, "9dp", 0, "9dp");
                wtab.dqfs("center");

            }
            st.xdbj(R.id.xdbj1).nbj(0, 0, 0, 0);

        } else if (zf.dy(a, "3")) {

            st.xdbj(R.id.xdbj1).nbj(u, 0, u, 50);

            i.runlibrary.app.v.wb wtab = st.wb(R.id.title_bar);
            wtab.nbj(0, "15dp", 0, "3dp");
            wtab.dqfs("center");

        } else if (zf.dy(a, "4")) {

            st.xdbj(R.id.xdbj1).nbj(0, 0, 0, 0);

        }

        i.runlibrary.app.v.dx dx1 = st.dx(R.id.dx1);
        dx1.xzzt(true);

        String set = AppPaths.appPath("settings/set3.inf");

        if (zf.dy(wj.dqwb(set), "true")) {

            String te = wj.dqwb(AppPaths.appPath("data/assets/a9"));

            String[] name = new String[]{"title", "text"};

            String[] value = new String[]{"新手教程", te};
            gj.tz(TextQrDisplayActivity.class, name, value);

        }

    }

    public void run(boolean i) {

        final i.runlibrary.app.v.wb backinfo = st.wb(R.id.backs);

        final i.runlibrary.app.v.xxbj cmdlin = st.xxbj(R.id.xxbj1);

        final i.runlibrary.app.v.xxbj cmdbac = st.xxbj(R.id.xxbj2);
        lin = st.bjk(R.id.bjk1).zf();
        lin = zf.qctwkg(lin);

        if (zf.dy(lin, "")) {

            e1.tsk("提示", "命令行不能为空");

        } else {

            try {

                if (zf.ckt(lin, "dkplayer load ")) {

                    String url = zf.qc(lin, "dkplayer load ", null);

                    String[] name = new String[]{"url"};

                    String[] value = new String[]{url};
                    gj.tz(VideoPlayerActivity.class, name, value);
                    bak(true);

                } else if (zf.ckt(lin, "x5web load ")) {

                    String url = zf.qc(lin, "x5web load ", null);

                    final String[] name = new String[]{"url"};

                    final String[] value = new String[]{url};
                    gj.jmxc(new Runnable() {

                        public void run() {

                            gj.tz(X5BrowserActivity.class, name, value);

                        }

                    });
                    bak(true);

                } else if (zf.ckt(lin, "paytest -p ")) {

                    String _money = zf.qc(lin, "paytest -p ", null);

                    final String[] name = new String[]{"id", "name", "jg", "text"};

                    final String[] value = new String[]{"a2", "腕管Pro-PayTest", _money, "腕管Pro：PayTest。"};
                    gj.jmxc(new Runnable() {

                        public void run() {

                            gj.tz(ProductPurchaseActivity.class, name, value);

                        }

                    });
                    bak(true);

                } else if (zf.ckt(lin, "file2 load ")) {

                    String url = zf.qc(lin, "file2 load ", " -rp ");

                    String root = zf.qc(lin, " -rp ", null);
                    url = zf.qctwkg(url);
                    root = zf.qctwkg(root);

                    String in = AppPaths.appPath("");

                    if (zf.ckt(url, in)) {

                        gj.jmxc(new Runnable() {

                            public void run() {

                                String po = AppPaths.appPath("");

                                String[] name = new String[]{"url", "root"};

                                String[] value = new String[]{po, po};
                                gj.tz(FileManagerActivity.class, name, value);

                            }

                        });

                    } else {

                        final String[] name = new String[]{"url", "root"};

                        final String[] value = new String[]{url, root};
                        gj.jmxc(new Runnable() {

                            public void run() {

                                gj.tz(FileManagerActivity.class, name, value);

                            }

                        });

                    }
                    bak(true);

                } else if (zf.dy(lin, "awg open beta")) {

                    String set1 = AppPaths.appPath("settings/set1.inf");
                    wj.xrwb(set1, "true");
                    bak_s("成功开启实验性功能", true);

                } else if (zf.dy(lin, "awg close beta")) {

                    String set1 = AppPaths.appPath("settings/set1.inf");
                    wj.xrwb(set1, "false");
                    bak_s("成功关闭实验性功能", true);

                } else if (zf.dy(lin, "help")) {

                    final String[] name = new String[]{"url", "title", "code"};

                    final String[] value = new String[]{AppPaths.appPath("data/assets/b2"), "功能帮助", "super1"};
                    gj.jmxc(new Runnable() {

                        public void run() {

                            gj.tz(TextViewerActivity.class, name, value);

                        }

                    });
                    bak(true);

                } else if (zf.dy(lin, "edit end")) {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            gj.tz(TerminalCompatibilityActivity.class);

                        }

                    });
                    bak(true);

                } else if (zf.dy(lin, "awg activate")) {

                    if (e1.isJh()) {

                        bak(false);

                    } else {

                        e1.jh();
                        bak(true);

                    }

                } else if (zf.dy(lin, "error")) {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            String[] name = new String[]{"Error"};

                            String[] value = new String[]{"0xffff"};
                            gj.tz(ErrorMessageActivity.class, name, value);

                        }

                    });
                    bak(true);

                } else if (zf.ckt(lin, "deviceManger ")) {

                    cartoncmd(lin);

                } else if (zf.dy(lin, "su") || zf.dy(lin, "sh") || zf.dy(lin, "shell")) {

                    gj.jmxc(new Runnable() {

                        public void run() {

                            gj.tz(ShellExecutorActivity.class);

                        }

                    });
                    bak(true);

                } else {

                    bak_s("未找到该指令", false);

                }

            } catch (Throwable e) {

                bak_s(e, false);

            }

        }

    }

    public void urljson(Object lables, Object set2e) {

        final Object url = set2e;

        final Object lable = lables;
        gj.xc(new Thread() {

            public void run() {

                String setback = "";

                String data = "";

                String[] wlhq1 = new String[]{"name1=value1", "name2=value2"};

                String back = wl.hq(url, wlhq1, "utf-8", "uid=123;name=123;", true, "User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept=*/*||accept-language=zh-CN", 20000, 20000, null);

                if (zf.dy(back, "") || zf.dy(back, null)) {

                    setback = "0";

                } else {

                    if (zf.dy(setback, "0")) {

                        setback = "0";

                    } else {

                        try {

                            i.runlibrary.app.zf$json jo = (i.runlibrary.app.zf$json) (Object) zf.json(back);

                            JSONObject json = jo.json;

                            JSONArray list = jo.dxlb(json, "data");

                            int size = jo.cd(list);

                            while (size > 0) {

                                size = size - 1;

                                JSONObject dx = jo.dx(list, size);
                                data = String.valueOf(jo.hq(dx, lable));

                            }

                        } catch (Throwable e) {

                            data = "0";

                        }
                        setback = data;

                    }

                }
                ujback = setback;
                bak_s(setback, true);

            }

        });

    }

    public void cartoncmd(Object i) {

        if (zf.dy(i, "deviceManger get")) {

            carton.gets(lei);
            bak(true);

        } else if (zf.ckt(i, "deviceManger setCameraDisabled")) {

            if (zf.dy(i, "deviceManger setCameraDisabled true")) {

                carton.setCameraDisabled(lei, true);
                bak(true);

            } else if (zf.dy(i, "deviceManger setCameraDisabled false")) {

                carton.setCameraDisabled(lei, false);
                bak(true);

            } else {

                bak(false);

            }

        } else if (zf.ckt(i, "deviceManger setStatusBarDisabled")) {

            if (zf.dy(i, "deviceManger setStatusBarDisabled true")) {

                carton.setStatusBarDisabled(lei, true);
                bak(true);

            } else if (zf.dy(i, "deviceManger setStatusBarDisabled false")) {

                carton.setStatusBarDisabled(lei, false);
                bak(true);

            } else {

                bak(false);

            }

        } else if (zf.dy(i, "deviceManger lockNow")) {

            carton.lockNow(lei);
            bak(true);

        } else if (zf.dy(i, "deviceManger wipeData")) {

            carton.wipeData(lei);
            bak(true);

        } else if (zf.dy(i, "deviceManger reboot")) {

            carton.reboot(lei);
            bak(true);

        } else if (zf.dy(i, "deviceManger isDOA")) {

            boolean e0 = carton.isDeviceOwnerApp(lei);

            boolean e = e0;
            bak_s(e, true);

        } else if (zf.ckt(i, "deviceManger setUninstallBlocked")) {

            if (zf.cjw(i, " true")) {

                if (zf.ckt(i, "deviceManger setUninstallBlocked \"") && zf.cjw(i, "\" true")) {

                    String apk = zf.qc(i, "setUninstallBlocked \"", "\" true");
                    carton.setUninstallBlocked(lei, apk, true);
                    bak(true);

                } else {

                    bak(false);

                }

            } else if (zf.cjw(i, "false")) {

                if (zf.ckt(i, "deviceManger setUninstallBlocked \"") && zf.cjw(i, "\" false")) {

                    String apk = zf.qc(i, "setUninstallBlocked \"", "\" false");
                    carton.setUninstallBlocked(lei, apk, false);
                    bak(true);

                } else {

                    bak(false);

                }

            } else {

                bak(false);

            }

        } else if (zf.ckt(i, "deviceManger setDeviceOwnerLockScreenInfo")) {

            if (zf.ckt(i, "deviceManger setDeviceOwnerLockScreenInfo \"") && zf.cjw(i, "\"")) {

                String text = zf.qc(i, "setDeviceOwnerLockScreenInfo \"", "\"");
                carton.setDeviceOwnerLockScreenInfo(lei, text);
                bak(true);

            } else {

                bak(false);

            }

        } else if (zf.ckt(i, "deviceManger setOrganizationName")) {

            if (zf.ckt(i, "deviceManger setOrganizationName \"") && zf.cjw(i, "\"")) {

                String text = zf.qc(i, "setOrganizationName \"", "\"");
                carton.setOrganizationName(lei, text);
                bak(true);

            } else {

                bak(false);

            }

        } else {

            bak_s("未找到该指令", false);

        }

    }

    public void bak(boolean i) {

        final i.runlibrary.app.v.wb backinfo = st.wb(R.id.backs);

        final i.runlibrary.app.v.xxbj cmdlin = st.xxbj(R.id.xxbj1);

        final i.runlibrary.app.v.xxbj cmdbac = st.xxbj(R.id.xxbj2);

        String color = "";

        String z = "";

        if (i) {

            z = "运行成功";
            color = zf.zf(R.color.colorTextTrue);

        } else {

            z = "运行失败";
            color = zf.zf(R.color.colorTextFalse);

        }

        final String zs = z;

        final String colors = color;
        gj.jmxc(new Runnable() {

            public void run() {

                backinfo.zfys(colors);
                backinfo.zf(zs);
                cmdbac.kjd(0);
                cmdlin.kjd(8);

            }

        });

    }

    public void bak_s(Object z, boolean i) {

        final Object zs = z;

        final i.runlibrary.app.v.wb backinfo = st.wb(R.id.backs);

        final i.runlibrary.app.v.xxbj cmdlin = st.xxbj(R.id.xxbj1);

        final i.runlibrary.app.v.xxbj cmdbac = st.xxbj(R.id.xxbj2);

        String color = "";

        if (i) {

            color = zf.zf(R.color.colorTextTrue);

        } else {

            color = zf.zf(R.color.colorTextFalse);

        }

        final String colors = color;
        gj.jmxc(new Runnable() {

            public void run() {

                backinfo.zfys(colors);
                backinfo.zf(zs);
                cmdbac.kjd(0);
                cmdlin.kjd(8);

            }

        });

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

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
            applyWindowModeFromSettings();

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        if (kc == 4) {

        }
        return false;

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

    private void onTx2Click(View vw) {
        gj.gb();

    }

    private void onTx3Click(View vw) {

        i.runlibrary.app.v.dx dx1 = st.dx(R.id.dx1);

        run(!dx1.xzzt());

    }

    private boolean onBacksLongClick(View vw) {

        String wb1 = st.wb(R.id.backs).zf();
        e1.tsk("详细信息", wb1);
        return true;

    }

    private void onTx1Click(View vw) {

        final i.runlibrary.app.v.xxbj cmdlin = st.xxbj(R.id.xxbj1);

        final i.runlibrary.app.v.xxbj cmdbac = st.xxbj(R.id.xxbj2);
        cmdbac.kjd(8);
        cmdlin.kjd(0);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) findViewById(ay, vw, R.id.title_bar);

        EditText i32ee139ba1 = (EditText) findViewById(ay, vw, R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        CheckBox i3d918d2dcb = (CheckBox) findViewById(ay, vw, R.id.dx1);

        ImageView i4d951cdb0e = (ImageView) findViewById(ay, vw, R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        ImageView i8217d42a17 = (ImageView) findViewById(ay, vw, R.id.tx3);
        i8217d42a17.setOnClickListener(tx3ClickListener);

        TextView i50b5d2ce90 = (TextView) findViewById(ay, vw, R.id.backs);
        i50b5d2ce90.setOnLongClickListener(backsLongClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
