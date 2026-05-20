/**
 ******************************************************************************
 * @file    j9.java
 * @author  Typheye
 * @brief   Location lookup screen logic.
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
package open.cn.awg.pro;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.annotation.NonNull;

import com.ypz.bangscreentools.BangScreenTools;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import i.app.iActivity;

public class j9 extends iActivity {

    public static double latitude;
    public static double longitude;
    public static double altitude;
    public static double speed;
    public static java.lang.String loca;
    public static android.location.LocationManager locationManager;
    public static android.location.LocationListener locationListener;
    public static java.lang.String v1 = "";
    public static java.lang.String v2 = "";
    public static java.lang.String v3 = "";
    public static java.lang.String v4 = "";
    public final j9 lei = this, 类 = this;
    public open.cn.awg.pro.e1 e1 = new e1(_APPINFO);
    public open.cn.awg.pro.e13 e13 = new e13(_APPINFO);
    public open.cn.awg.pro.debug debug = new debug(_APPINFO);
    private final android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i34dc7bf20a(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i3167b45174 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i3167b45174(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i171cedf1eb = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i171cedf1eb(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_c0d3e00d2b = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_c0d3e00d2b(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i2202ede8ff = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i2202ede8ff(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_i20c2777fab = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_i20c2777fab(vw);
        }

    };
    private final android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080 = new android.view.View.OnClickListener() {

        public void onClick(android.view.View vw) {
            $_onClick_d26d5d7080(vw);
        }

    };

    public void onCreate(android.os.Bundle be) {
        super.onCreate(be);
        setContentView(open.cn.awg.pro.R.layout.j9);
        _$_viewAutomaticSettingEvent();
        open.cn.awg.pro.e6.getInstance().addActivity(lei);
        csh();

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

            st.xdbj(open.cn.awg.pro.R.id.j9_v).shxtck(true);

            java.lang.String clr = zf.zf(open.cn.awg.pro.R.color.colorTab);
            xt.pm().ztl(clr, 0);

        } else {

            st.xdbj(open.cn.awg.pro.R.id.j9_v).shxtck(false);

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

    public void upload() {

        String log =
                "awgwl_state:" + e1.awgwl_state() + "\n" +
                        "awgwl_uid:" + e1.awgwl_getuid() + "\n" +
                        "latitude:" + latitude + "\n" +
                        "longitude:" + longitude + "\n" +
                        "altitude:" + altitude + "\n" +
                        "speed:" + speed + "\n" +
                        "loca:" + loca;
        e13.upload_log("j9", log);

    }

    public void load() {

        try {

            android.location.LocationManager locationManager = (android.location.LocationManager) getSystemService(android.content.Context.LOCATION_SERVICE);
            java.util.List<String> providerList = locationManager.getProviders(true);
            if (providerList.contains(android.location.LocationManager.GPS_PROVIDER)) {
            } else {
                e1.tsk("提示", "获取定位信息失败,请开启GPS后重试");
                gj.gb();
                android.content.Intent intent = new android.content.Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(intent, 0);
            }

        } catch (java.lang.Throwable e) {

            e1.upload_error(e, "j9.load()");

        }

    }

    @SuppressLint("MissingPermission")
    public void getZb() {
        load();

        try {

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationListener = new LocationListener() {

                public void onLocationChanged(Location location) {
                    Geocoder geocoder = new Geocoder(lei, Locale.getDefault());

                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    altitude = location.getAltitude();
                    speed = location.getSpeed();
                    try {
                        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                        if (addresses.size() > 0) {
                            Address address = addresses.get(0);
                            loca = address.getCountryName() + address.getAdminArea() + address.getLocality();
                        } else {
                            loca = "未知";
                        }
                    } catch (IOException e) {
                        loca = "未知";
                        e.printStackTrace();
                    }
                    show();

                }

                public void onStatusChanged(String provider, int status, Bundle extras) {
                }

                public void onProviderEnabled(@NonNull String provider) {
                }

                public void onProviderDisabled(@NonNull String provider) {
                }
            };
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        } catch (java.lang.Throwable e) {

            e1.upload_error(e, "j9.getZb()");
            gj.gb();

        }

    }

    public void csh() {
        getZb();

    }

    public void show() {
        v1 = sj.zh().zstring((latitude));
        v2 = sj.zh().zstring((longitude));
        v3 = com.demo.e4.doubleToString(altitude);
        v4 = com.demo.e4.doubleToString(speed * 3.6);
        v1 = v1 + "°";
        v2 = v2 + "°";
        v3 = v3 + "m";
        v4 = v4 + "km/h";
        gj.jmxc(new java.lang.Runnable() {

            public void run() {

                st.wb(open.cn.awg.pro.R.id.wb_v1).zf(v1);
                st.wb(open.cn.awg.pro.R.id.wb_v2).zf(v2);
                st.wb(open.cn.awg.pro.R.id.wb_v3).zf(v3);
                st.wb(open.cn.awg.pro.R.id.wb_v4).zf(v4);
                st.wb(open.cn.awg.pro.R.id.wb_v5).zf(loca);

            }

        });

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
        open.cn.awg.pro.e6.getInstance().removeActivity(lei);

        if (debug.getDebugState("2")) {

            upload();

        }
        locationManager.removeUpdates(locationListener);

    }

    private void $_onClick_d26d5d7080(android.view.View vw) {
        gj.gb();

    }

    private void $_onClick_i20c2777fab(android.view.View vw) {
        e1.tsk("经度", st.wb(open.cn.awg.pro.R.id.wb_v1).zf());

    }

    private void $_onClick_i2202ede8ff(android.view.View vw) {
        e1.tsk("纬度", st.wb(open.cn.awg.pro.R.id.wb_v2).zf());

    }

    private void $_onClick_c0d3e00d2b(android.view.View vw) {
        e1.tsk("海拔", st.wb(open.cn.awg.pro.R.id.wb_v3).zf());

    }

    private void $_onClick_i171cedf1eb(android.view.View vw) {
        e1.tsk("速度", st.wb(open.cn.awg.pro.R.id.wb_v4).zf());

    }

    private void $_onClick_i3167b45174(android.view.View vw) {
        e1.tsk("位置", st.wb(open.cn.awg.pro.R.id.wb_v5).zf());

    }

    private void $_onClick_i34dc7bf20a(android.view.View vw) {
        gj.gb();

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw) {

        android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
        d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

        android.widget.RelativeLayout i20c2777fab = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj3);
        i20c2777fab.setOnClickListener($_on_setOnClickListener_i20c2777fab);

        android.widget.RelativeLayout i2202ede8ff = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj4);
        i2202ede8ff.setOnClickListener($_on_setOnClickListener_i2202ede8ff);

        android.widget.RelativeLayout c0d3e00d2b = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj5);
        c0d3e00d2b.setOnClickListener($_on_setOnClickListener_c0d3e00d2b);

        android.widget.RelativeLayout i171cedf1eb = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj6);
        i171cedf1eb.setOnClickListener($_on_setOnClickListener_i171cedf1eb);

        android.widget.RelativeLayout i3167b45174 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj2);
        i3167b45174.setOnClickListener($_on_setOnClickListener_i3167b45174);

        android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
        i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

        __layoutIsLoaded(ay, vw);
    }
}
