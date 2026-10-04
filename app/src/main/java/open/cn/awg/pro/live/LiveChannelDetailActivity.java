/**
 ******************************************************************************
 * @file    LiveChannelDetailActivity.java
 * @author  Typheye
 * @brief   Live stream channel detail screen.
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
package open.cn.awg.pro.live;


import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import java.util.ArrayList;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.media.VideoPlayerActivity;
import open.cn.awg.pro.R;

public class LiveChannelDetailActivity extends BaseAwgActivity {

    public final LiveChannelDetailActivity lei = this, 类 = this;
    public AppRuntimeBridge e1;
    public String url = "";
    public String title = "";
    public String text = "";
    public String[] urls = new String[]{""};
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
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        e1 = new AppRuntimeBridge(this);
        setContentView(R.layout.live_channel_detail);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);
        title = LiveChannelDetailActivity.this.getIntent().getStringExtra(String.valueOf("title"));
        text = LiveChannelDetailActivity.this.getIntent().getStringExtra(String.valueOf("text"));
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(LiveChannelDetailActivity.this.getWindow().getDecorView(), R.id.wb1)).setText(String.valueOf("[频道] " + title + "\n[简介] " + text));

        String urls_ = LiveChannelDetailActivity.this.getIntent().getStringExtra(String.valueOf("urls"));

        android.widget.Spinner xlcd1 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(LiveChannelDetailActivity.this.getWindow().getDecorView(), R.id.xlcd1));

        if (java.util.Objects.equals(urls_, "")) {

            String _url = LiveChannelDetailActivity.this.getIntent().getStringExtra(String.valueOf("url"));
            urls = new String[]{_url};

            String[] show = new String[]{"[默认源] " + _url};
            open.cn.awg.pro.core.NativeViewValues.spinner(xlcd1, show);

        } else {

            urls = open.cn.awg.pro.core.TextValues.split(urls_, ",", false);
            url = urls[0];

            ArrayList<String> show = new ArrayList<String>();

            int i = 0;

            for (String j : urls) {

                if (i == 0) {

                    show.add("[默认源] " + j);

                } else {

                    show.add("[备用源" + open.cn.awg.pro.core.TextValues.string(i) + "] " + j);

                }
                i++;

            }
            open.cn.awg.pro.core.NativeViewValues.spinner(xlcd1, show);

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.live_channel_detail_root, R.id.xdbj1, R.id.title_bar);

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(LiveChannelDetailActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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

    private void onTitleBarClick(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(LiveChannelDetailActivity.this);

    }

    private void onXlcd1ItemSelected(AdapterView vw, View view, int pn, long id) {
        url = urls[pn];

    }

    private void onXlcd1NothingSelected(AdapterView vw) {

    }

    private void onAn1Click(View vw) {

        final String[] name = new String[]{"url", "title"};

        final String[] value = new String[]{url, title};
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.launch(LiveChannelDetailActivity.this, (Class<?>) (VideoPlayerActivity.class), (String[]) (name), (Object[]) (value));

            }

        });

    }

    private void onTx1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(LiveChannelDetailActivity.this);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        Spinner i004a739c93 = (Spinner) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xlcd1);
        i004a739c93.setOnItemSelectedListener(xlcd1ItemSelectedListener);

        Button i171ea34f1a = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
