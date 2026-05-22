/**
 ******************************************************************************
 * @file    PackageInstallerActivity.java
 * @author  Typheye
 * @brief   Handles packaged installation tasks.
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
package open.cn.awg.pro.installer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.FileProvider;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import java.io.File;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class PackageInstallerActivity extends BaseAwgActivity {

    public final PackageInstallerActivity lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public String c = "";
    public String mode = "";

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.package_installer);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        c = sj.hqtz("url");
        mode = sj.hqtz("mode");

        if (zf.dy(mode, "0")) {

            install();

        } else if (zf.dy(mode, "1")) {

            install2();

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        i.runlibrary.app.xt$pm pm = xt.pm();

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

            st.xdbj(R.id.package_installer_root).shxtck(true);

            String clr = zf.zf(R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(R.id.package_installer_root).shxtck(false);
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

            String f8set2 = AppPaths.appPath("settings/f8/set3.inf");

            if (zf.dy(wj.dqwb(f8set2), "true")) {

                pm.xnaj(false);

            }
            st.xdbj(R.id.xdbj1).nbj(0, 0, 0, 0);

        }

    }

    public void install() {

        try {

            boolean haveInstallPermission;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                haveInstallPermission = lei.getPackageManager().canRequestPackageInstalls();
                gj.sc(lei.getPackageName());
                if (!haveInstallPermission) {

                    Uri packageURI = Uri.parse("package:" + lei.getPackageName());
                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                    startActivityForResult(intent, 10086);
                } else {
                    File file = new File(c);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Uri uri;
                    String type = "application/vnd.android.package-archive";
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        String authority = "open.cn.awg.pro.myFileProvider";
                        uri = FileProvider.getUriForFile(lei, authority, file);
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    } else {
                        uri = Uri.fromFile(file);
                    }
                    intent.setDataAndType(uri, type);
                    startActivity(intent);

                    gj.gb();
                }
            } else {
                File file = new File(c);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                startActivity(intent);

                gj.gb();
            }

        } catch (Throwable e) {

            e1.upload_error(e, "g5.install()");
            gj.gb();

        }

    }

    public void install2() {

        try {

            File file = new File(c);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri;
            String type = "application/vnd.android.package-archive";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                String authority = "open.cn.awg.pro.myFileProvider";
                uri = FileProvider.getUriForFile(lei, authority, file);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                uri = Uri.fromFile(file);
            }
            intent.setDataAndType(uri, type);
            startActivity(intent);

            gj.gb();

        } catch (Throwable e) {

            e1.upload_error(e, "g5.install2()");
            gj.gb();

        }

    }

    public void onActivityResult(int re, int rc, Intent it) {
        super.onActivityResult(re, rc, it);

        if (zf.dy(mode, "0")) {

            if (rc == RESULT_OK && re == 10086) {

                install();

            } else {

                gj.gb();

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
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        __layoutIsLoaded(ay, vw);
    }
}
