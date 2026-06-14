/**
 ******************************************************************************
 * @file    LocationActivity.java
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
package open.cn.awg.pro.tools;


import open.cn.awg.pro.core.IappCompat;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.core.CoreRuntimeBootstrap;
import open.cn.awg.pro.core.DebugStateStore;
import open.cn.awg.pro.R;

public class LocationActivity extends BaseAwgActivity {

    public static double latitude;
    public static double longitude;
    public static double altitude;
    public static double speed;
    public static String loca;
    public static LocationManager locationManager;
    public static LocationListener locationListener;
    public static String v1 = "";
    public static String v2 = "";
    public static String v3 = "";
    public static String v4 = "";
    public final LocationActivity lei = this, 类 = this;
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public CoreRuntimeBootstrap e13 = new CoreRuntimeBootstrap(_APPINFO);
    public DebugStateStore debug = new DebugStateStore(_APPINFO);
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnClickListener xdbj2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj2Click(vw);
        }

    };
    private final View.OnClickListener xdbj6ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj6Click(vw);
        }

    };
    private final View.OnClickListener xdbj5ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj5Click(vw);
        }

    };
    private final View.OnClickListener xdbj4ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj4Click(vw);
        }

    };
    private final View.OnClickListener xdbj3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj3Click(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        setContentView(R.layout.location);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        csh();

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.location_root, R.id.xdbj1, R.id.title_bar);

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

            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            List<String> providerList = locationManager.getProviders(true);
            if (providerList.contains(LocationManager.GPS_PROVIDER)) {
            } else {
                e1.tsk("提示", "获取定位信息失败,请开启GPS后重试");
                gj.gb();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(intent, 0);
            }

        } catch (Throwable e) {

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

        } catch (Throwable e) {

            e1.upload_error(e, "j9.getZb()");
            gj.gb();

        }

    }

    public void csh() {
        getZb();

    }

    public void show() {
        v1 = IappCompat.zh(sj.zh()).zstring((latitude));
        v2 = IappCompat.zh(sj.zh()).zstring((longitude));
        v3 = com.demo.e4.doubleToString(altitude);
        v4 = com.demo.e4.doubleToString(speed * 3.6);
        v1 = v1 + "°";
        v2 = v2 + "°";
        v3 = v3 + "m";
        v4 = v4 + "km/h";
        gj.jmxc(new Runnable() {

            public void run() {

                st.wb(R.id.location_longitude_text).zf(v1);
                st.wb(R.id.location_latitude_text).zf(v2);
                st.wb(R.id.location_altitude_text).zf(v3);
                st.wb(R.id.location_speed_text).zf(v4);
                st.wb(R.id.location_address_text).zf(loca);

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

        if (debug.getDebugState("2")) {

            upload();

        }
        locationManager.removeUpdates(locationListener);

    }

    private void onTitleBarClick(View vw) {
        gj.gb();

    }

    private void onXdbj3Click(View vw) {
        e1.tsk("经度", st.wb(R.id.location_longitude_text).zf());

    }

    private void onXdbj4Click(View vw) {
        e1.tsk("纬度", st.wb(R.id.location_latitude_text).zf());

    }

    private void onXdbj5Click(View vw) {
        e1.tsk("海拔", st.wb(R.id.location_altitude_text).zf());

    }

    private void onXdbj6Click(View vw) {
        e1.tsk("速度", st.wb(R.id.location_speed_text).zf());

    }

    private void onXdbj2Click(View vw) {
        e1.tsk("位置", st.wb(R.id.location_address_text).zf());

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

        RelativeLayout i20c2777fab = (RelativeLayout) findViewById(ay, vw, R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);

        RelativeLayout i2202ede8ff = (RelativeLayout) findViewById(ay, vw, R.id.xdbj4);
        i2202ede8ff.setOnClickListener(xdbj4ClickListener);

        RelativeLayout c0d3e00d2b = (RelativeLayout) findViewById(ay, vw, R.id.xdbj5);
        c0d3e00d2b.setOnClickListener(xdbj5ClickListener);

        RelativeLayout i171cedf1eb = (RelativeLayout) findViewById(ay, vw, R.id.xdbj6);
        i171cedf1eb.setOnClickListener(xdbj6ClickListener);

        RelativeLayout i3167b45174 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj2);
        i3167b45174.setOnClickListener(xdbj2ClickListener);

        ImageView i34dc7bf20a = (ImageView) findViewById(ay, vw, R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
