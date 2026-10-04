/**
 ******************************************************************************
 * @file    VideoPlayerActivity.java
 * @author  Typheye
 * @brief   Video playback screen and media interaction logic.
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
package open.cn.awg.pro.media;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.widget.NestedScrollView;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import java.util.HashMap;
import java.util.Map;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.download.AppDownloadActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.settings.MediaBrowserSettingsActivity;
import open.cn.awg.pro.ui.main.TextQrDisplayActivity;
import open.cn.awg.pro.util.DateUtil;

import xyz.doikki.videoplayer.ijk.IjkPlayerFactory;
import xyz.doikki.videoplayer.player.VideoView;

public class VideoPlayerActivity extends BaseAwgActivity {

    public final VideoPlayerActivity lei = this, 类 = this;
    public float x1 = ((float) (0));
    public float x2 = ((float) (0));
    public float y1 = ((float) (0));
    public float y2 = ((float) (0));
    public boolean reTabShow = false;
    public boolean isMusic = false;
    public AppRuntimeBridge e1;
    public String hqdz = "";
    public String url;
    public VideoView mPlayer;
    public int screenScaleType = 1;
    public int setRotation = 1;
    public long jdmax = 0;
    public double setVolume = 1.0;
    public boolean play = true;
    public boolean cacheplay = false;
    public int maxVolume;
    public int currentVolume;
    public Uri uri;
    private final View.OnLongClickListener wb8LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onWb8LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj3ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj3Click(vw);
        }

    };
    private final View.OnTouchListener xxbj19TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onXxbj19Touch(vw, me);
        }

    };
    private final View.OnClickListener wb23ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb23Click(vw);
        }

    };
    private final View.OnTouchListener qtgd1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onQtgd1Touch(vw, me);
        }

    };
    private final View.OnClickListener tx6ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx6Click(vw);
        }

    };
    private final View.OnClickListener tx5ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx5Click(vw);
        }

    };
    private final View.OnClickListener tx7ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx7Click(vw);
        }

    };
    private final View.OnClickListener wb33ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb33Click(vw);
        }

    };
    private final View.OnClickListener wb1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb1Click(vw);
        }

    };
    private final View.OnClickListener wb2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb2Click(vw);
        }

    };
    private final View.OnClickListener wb15ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb15Click(vw);
        }

    };
    private final View.OnClickListener wb10ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb10Click(vw);
        }

    };
    private final View.OnClickListener wb17ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb17Click(vw);
        }

    };
    private final View.OnClickListener wb16ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb16Click(vw);
        }

    };
    private final View.OnClickListener wb14ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb14Click(vw);
        }

    };
    private final View.OnClickListener wb13ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb13Click(vw);
        }

    };
    private final View.OnClickListener wb29ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb29Click(vw);
        }

    };
    private final View.OnClickListener wb28ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb28Click(vw);
        }

    };
    private final View.OnClickListener wb27ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb27Click(vw);
        }

    };
    private final View.OnClickListener wb26ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb26Click(vw);
        }

    };
    private final View.OnClickListener wb31ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb31Click(vw);
        }

    };
    private final View.OnClickListener wb24ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb24Click(vw);
        }

    };
    private final View.OnClickListener wb22ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb22Click(vw);
        }

    };
    private final View.OnClickListener wb21ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb21Click(vw);
        }

    };
    private final View.OnClickListener wb20ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb20Click(vw);
        }

    };
    private final View.OnClickListener wb19ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb19Click(vw);
        }

    };
    private final SeekBar.OnSeekBarChangeListener tdt3SeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        public void onProgressChanged(SeekBar vw, int ps, boolean fu) {
            onTdt3ProgressChanged(vw, ps, fu);
        }

        public void onStartTrackingTouch(SeekBar vw) {
            onTdt3StartTrackingTouch(vw);
        }

        public void onStopTrackingTouch(SeekBar vw) {
            onTdt3StopTrackingTouch(vw);
        }

    };
    private final SeekBar.OnSeekBarChangeListener tdt1SeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {

        public void onStopTrackingTouch(SeekBar vw) {
            onTdt1StopTrackingTouch(vw);
        }

        public void onStartTrackingTouch(SeekBar vw) {
            onTdt1StartTrackingTouch(vw);
        }

        public void onProgressChanged(SeekBar vw, int ps, boolean fu) {
            onTdt1ProgressChanged(vw, ps, fu);
        }

    };
    private final View.OnLongClickListener xxbj10LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXxbj10LongClick(vw);
        }

    };
    private final View.OnClickListener xxbj10ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj10Click(vw);
        }

    };
    private final View.OnClickListener tx2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx2Click(vw);
        }

    };
    private final View.OnClickListener wb5ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onWb5Click(vw);
        }

    };
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnClickListener titleBarClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTitleBarClick(vw);
        }

    };
    private final View.OnClickListener xxbj2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXxbj2Click(vw);
        }

    };

    public void onCreate(Bundle be) {
        super.onCreate(be);
        e1 = new AppRuntimeBridge(this);
        setContentView(R.layout.video_player);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

        final String isOpenMap = AppPaths.appPath("settings/f15/set3.inf");

        try {

            cu6();
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    Intent intent = getIntent();
                    Context context = AwgProApplication.getContext();
                    String action = intent.getAction();
                    if (Intent.ACTION_VIEW.equals(action)) {
                        Uri uri = intent.getData();
                        if (uri != null) {
                            try {
                                hqdz = com.demo.e4.getRealPathFromUri(context, uri);
                            } catch (Exception e) {
                                hqdz = com.demo.e4.getFilePathForN(uri, context);
                            }
                        }
                    }

                    hqdz = open.cn.awg.pro.core.TextValues.trim(hqdz);

                    if (!java.util.Objects.equals(hqdz, "") && !java.util.Objects.equals(hqdz, null)) {

                        url = hqdz;

                    } else {

                        url = VideoPlayerActivity.this.getIntent().getStringExtra(String.valueOf("url"));

                    }
                    url = open.cn.awg.pro.core.TextValues.trim(url);

                    if (!java.util.Objects.equals(url, null) && !java.util.Objects.equals(url, "")) {

                        String bt = VideoPlayerActivity.this.getIntent().getStringExtra(String.valueOf("title"));
                        bt = open.cn.awg.pro.core.TextValues.trim(bt);

                        if (!java.util.Objects.equals(bt, null) && !java.util.Objects.equals(bt, "")) {

                        } else {

                            if (open.cn.awg.pro.core.TextValues.startsWith(url, "http")) {

                            } else {

                                AppRuntimeBridge e1 = new AppRuntimeBridge(VideoPlayerActivity.this);

                                String[] fhValue = e1.fileinfo("%", url);

                                String bts = fhValue[3];

                            }

                        }

                        if (open.cn.awg.pro.core.TextValues.startsWith(url, "http")) {

                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj38)).setVisibility(android.view.View.VISIBLE);

                                }

                            });

                        }

                        if (open.cn.awg.pro.core.TextValues.endsWith(url, ".mp3") || open.cn.awg.pro.core.TextValues.endsWith(url, ".flac") || open.cn.awg.pro.core.TextValues.contains(url, ".mp3?") || open.cn.awg.pro.core.TextValues.contains(url, ".flac?")) {

                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    VideoPlayerActivity.this.findViewById(R.id.video_player_view).setVisibility(View.GONE);
                                    isMusic = true;
                                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj13)).setVisibility(android.view.View.GONE);

                                    if (open.cn.awg.pro.core.TextValues.startsWith(url, "http")) {

                                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj38)).setVisibility(android.view.View.VISIBLE);

                                    }

                                }

                            });

                        }
                        mPlayer = lei.findViewById(R.id.video_player_view);

                        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(VideoPlayerActivity.this, (String) (isOpenMap)), "true")) {

                            if (java.util.Objects.equals(VideoPlayerActivity.this.getIntent().getStringExtra(String.valueOf("identity_name")), "腕上哔哩")) {

                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        Map<String, String> map = new HashMap<String, String>();
                                        map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.121 Safari/537.36");
                                        map.put("Referer", "https://www.bilibili.com");
                                        map.put("Origin", "https://www.bilibili.com");
                                        mPlayer.setUrl(url, map);

                                    }

                                });

                            } else {

                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        mPlayer.setUrl(url);

                                    }

                                });

                            }

                        } else {

                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    mPlayer.setUrl(url);

                                }

                            });

                        }
                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                mPlayer.setPlayerFactory(IjkPlayerFactory.create());
                                mPlayer.setLooping(true);
                                mPlayer.start();

                            }

                        });
                        msetspeed();
                        msetScreenScaleType();
                        msetRotation();
                        maudio();
                        mplay();
                        mxh();

                    } else {

                        e1.tsk("提示", "文件打开失败");
                        open.cn.awg.pro.core.NativeActions.finish(VideoPlayerActivity.this);

                    }

                }

            });

        } catch (Throwable e) {

            e1.upload_error(e, "g2()");
            open.cn.awg.pro.core.NativeActions.finish(VideoPlayerActivity.this);

        }

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(VideoPlayerActivity.this, (String) (AppPaths.appPath("settings/set4s.inf"))), "false")) {

            if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(VideoPlayerActivity.this, (String) (AppPaths.appPath("settings/set4.inf"))), "true")) {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            } else {

                getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);

            }

        }
        open.cn.awg.pro.core.NativeDevice.setKeepScreenOn(VideoPlayerActivity.this, !(false));

        final String a2 = AppPaths.appPath("settings/a3.inf");

        String a = open.cn.awg.pro.core.NativeFiles.readText(VideoPlayerActivity.this, (String) (a2));
        applyFullscreenWindow();

        if (java.util.Objects.equals(a, "1")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj8)), 0, "3dp", 0, "30dp");
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj11)), "3dp", 0, "3dp", 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj18)), "3dp", 0, "3dp", 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj16)), "3dp", 0, "3dp", 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj25)), "3dp", 0, "3dp", 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj32)), "3dp", 0, "3dp", 0);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.title_bar));
            open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "15dp", 0, "3dp");
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            android.widget.TextView btab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.backt));
            open.cn.awg.pro.core.NativeViewValues.padding(btab, 0, "15dp", 0, "3dp");
            btab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "2")) {

            androidx.recyclerview.widget.RecyclerView lbx = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.v7lb1));

            String a001 = AppPaths.appPath("settings/dpi.inf");

            if (open.cn.awg.pro.core.NativeFiles.exists(VideoPlayerActivity.this, (String) (a001)) || java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(VideoPlayerActivity.this, (String) (a001)), "true")) {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.title_bar));
                open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "3dp", 0, "3dp");
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                android.widget.TextView btab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.backt));
                open.cn.awg.pro.core.NativeViewValues.padding(btab, 0, "3dp", 0, "3dp");
                btab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            } else {

                android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.title_bar));
                open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "9dp", 0, "9dp");
                wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

                android.widget.TextView btab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.backt));
                open.cn.awg.pro.core.NativeViewValues.padding(btab, 0, "9dp", 0, "9dp");
                btab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            }
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj8)), 0, "3dp", 0, "30dp");
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj11)), "3dp", 0, "3dp", 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj18)), "3dp", 0, "3dp", 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj16)), "3dp", 0, "3dp", 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj25)), "3dp", 0, "3dp", 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj32)), "3dp", 0, "3dp", 0);

        } else if (java.util.Objects.equals(a, "3")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj8)), 0, "3dp", 0, "30dp");
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj11)), "3dp", 0, "3dp", 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj18)), "3dp", 0, "3dp", 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj16)), "3dp", 0, "3dp", 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj25)), "3dp", 0, "3dp", 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj32)), "3dp", 0, "3dp", 0);

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.title_bar));
            open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, "15dp", 0, "3dp");
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

            android.widget.TextView btab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.backt));
            open.cn.awg.pro.core.NativeViewValues.padding(btab, 0, "15dp", 0, "3dp");
            btab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        } else if (java.util.Objects.equals(a, "4")) {

            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj8)), 0, "3dp", 0, "30dp");
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj11)), "3dp", 0, "3dp", 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj18)), "3dp", 0, "3dp", 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj16)), "3dp", 0, "3dp", 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj25)), "3dp", 0, "3dp", 0);
            open.cn.awg.pro.core.NativeViewValues.padding(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj32)), "3dp", 0, "3dp", 0);

            int uih = open.cn.awg.pro.core.DisplayValues.pxToDp(VideoPlayerActivity.this, open.cn.awg.pro.core.DisplayValues.systemBarHeight(VideoPlayerActivity.this, "status_bar_height"));
            uih = uih + 9;

            android.widget.TextView wtab = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.title_bar));
            open.cn.awg.pro.core.NativeViewValues.padding(wtab, 0, uih + "dp", 0, "9dp");
            wtab.setGravity(open.cn.awg.pro.core.NativeViewValues.gravity("center"));

        }

        String set = AppPaths.appPath("settings/set3.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(VideoPlayerActivity.this, (String) (set)), "true")) {

            String te = open.cn.awg.pro.core.NativeFiles.readText(VideoPlayerActivity.this, (String) (AppPaths.appPath("data/assets/a8")));

            String[] name = new String[]{"title", "text"};

            String[] value = new String[]{"新手教程", te};
            open.cn.awg.pro.core.NativeActions.launch(VideoPlayerActivity.this, (Class<?>) (TextQrDisplayActivity.class), (String[]) (name), (Object[]) (value));

        }

    }

    public void cu6() {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.sleep(4000);
                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj42)).setVisibility(android.view.View.VISIBLE);

                    }

                });

            }

        });

    }

    public void maudio() {

        AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                android.widget.SeekBar yin = ((android.widget.SeekBar) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.tdt3));
                yin.setMax(open.cn.awg.pro.core.TextValues.intValue(maxVolume, -1));
                yin.setProgress(open.cn.awg.pro.core.TextValues.intValue(currentVolume, -1));

            }

        });

    }

    public void msetaudio(int i) {

        AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);

    }

    public void mxh() {

        final int outime = 4000;

        final android.widget.LinearLayout xxbj8 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj8));

        final android.widget.LinearLayout xxbj1 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj1));
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                int intime = 0;

                while (play && !Thread.currentThread().isInterrupted()) {

                    open.cn.awg.pro.core.NativeActions.sleep(100);
                    intime = intime + 100;

                    if (reTabShow) {

                        reTabShow = false;
                        intime = 0;

                    }

                    if (xxbj8.getVisibility() == android.view.View.VISIBLE) {

                        intime = 0;

                    } else {

                        if (xxbj1.getVisibility() == android.view.View.VISIBLE && outime - intime < 0) {

                            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                public void run() {

                                    xxbj1.setVisibility(android.view.View.GONE);

                                }

                            });

                        } else {

                        }

                    }

                }

            }

        });

    }

    public void mplay() {

        final android.widget.SeekBar tdt1 = ((android.widget.SeekBar) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.tdt1));
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                while (play) {

                    open.cn.awg.pro.core.NativeActions.sleep(50);
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            maudio();

                            long position = mPlayer.getCurrentPosition();

                            long duration = mPlayer.getDuration();
                            tdt1.setProgress(open.cn.awg.pro.core.TextValues.intValue(position, -1));
                            tdt1.setMax(open.cn.awg.pro.core.TextValues.intValue(duration, -1));
                            jdmax = duration;
                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf(timeChange(position)));
                            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.wb4)).setText(String.valueOf(timeChange(duration)));

                            if (!java.util.Objects.equals(mPlayer, null)) {

                                if (!mPlayer.isPlaying()) {

                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.wb5)).setText(String.valueOf("播放"));
                                    open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.tx2)), R.mipmap.a12);

                                } else {

                                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.wb5)).setText(String.valueOf("暂停"));
                                    open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.tx2)), R.mipmap.a11);
                                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj19)).setVisibility(android.view.View.GONE);

                                    if (isMusic) {

                                        ((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.tx3)).setVisibility(android.view.View.VISIBLE);

                                    }

                                }

                            }

                        }

                    });

                }

            }

        });

    }

    public String timeChange(long position) {

        String back = "00:00:00";

        try {

            back = DateUtil.stringForTime2(position);

        } catch (Throwable __$_e__) {

        }
        return back;

    }

    public void mplaying() {
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                if (mPlayer.isPlaying()) {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            mPlayer.pause();

                        }

                    });

                } else {

                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            mPlayer.start();

                        }

                    });

                }

            }

        });

    }

    public void msetspeed() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                float i = mPlayer.getSpeed();

                String t = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorAccent);

                String f = "#00000000";

                if (i == 0.5) {

                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj17)), R.drawable.dkenbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj21)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj22)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj23)), R.drawable.dkdisbj);

                } else if (i == 1.0) {

                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj17)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj21)), R.drawable.dkenbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj22)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj23)), R.drawable.dkdisbj);

                } else if (i == 1.5) {

                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj17)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj21)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj22)), R.drawable.dkenbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj23)), R.drawable.dkdisbj);

                } else if (i == 2.0) {

                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj17)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj21)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj22)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj23)), R.drawable.dkenbj);

                }

            }

        });

    }

    public void msetScreenScaleType() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                int i = screenScaleType;

                String t = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorAccent);

                String f = "#00000000";

                if (i == 1) {

                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj26)), R.drawable.dkenbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj27)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj28)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj29)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj30)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj37)), R.drawable.dkdisbj);
                    mPlayer.setScreenScaleType(VideoView.SCREEN_SCALE_DEFAULT);

                } else if (i == 2) {

                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj26)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj27)), R.drawable.dkenbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj28)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj29)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj30)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj37)), R.drawable.dkdisbj);
                    mPlayer.setScreenScaleType(VideoView.SCREEN_SCALE_ORIGINAL);

                } else if (i == 3) {

                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj26)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj27)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj28)), R.drawable.dkenbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj29)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj30)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj37)), R.drawable.dkdisbj);
                    mPlayer.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP);

                } else if (i == 4) {

                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj26)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj27)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj28)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj29)), R.drawable.dkenbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj30)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj37)), R.drawable.dkdisbj);
                    mPlayer.setScreenScaleType(VideoView.SCREEN_SCALE_MATCH_PARENT);

                } else if (i == 5) {

                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj26)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj27)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj28)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj29)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj30)), R.drawable.dkenbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj37)), R.drawable.dkdisbj);
                    mPlayer.setScreenScaleType(VideoView.SCREEN_SCALE_4_3);

                } else if (i == 6) {

                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj26)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj27)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj28)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj29)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj30)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj37)), R.drawable.dkenbj);
                    mPlayer.setScreenScaleType(VideoView.SCREEN_SCALE_16_9);

                }

            }

        });

    }

    public void msetRotation() {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                int mr = setRotation;

                String t = open.cn.awg.pro.core.NativeViewValues.colorResourceString(open.cn.awg.pro.app.AwgProApplication.getContext(), R.color.colorAccent);

                String f = "#00000000";

                if (mr == 1) {

                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj33)), R.drawable.dkenbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj34)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj35)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj36)), R.drawable.dkdisbj);

                } else if (mr == 2) {

                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj33)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj34)), R.drawable.dkenbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj35)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj36)), R.drawable.dkdisbj);

                } else if (mr == 3) {

                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj33)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj34)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj35)), R.drawable.dkenbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj36)), R.drawable.dkdisbj);

                } else if (mr == 4) {

                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj33)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj34)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj35)), R.drawable.dkdisbj);
                    open.cn.awg.pro.core.NativeViewValues.background(((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj36)), R.drawable.dkenbj);

                }

            }

        });

    }

    public void onDestroy() {
        super.onDestroy();
        AwgProApplication.getInstance().removeActivity(lei);
        play = false;

        if (!java.util.Objects.equals(mPlayer, null)) {

            mPlayer.release();

        }

    }

    public void onPause() {
        super.onPause();

        String bfset1 = AppPaths.appPath("settings/f15/set2.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(VideoPlayerActivity.this, (String) (bfset1)), "false") && !java.util.Objects.equals(mPlayer, null)) {

            if (mPlayer.isPlaying()) {

                mPlayer.pause();
                cacheplay = true;

            }

        }

    }

    public void onWindowFocusChanged(boolean hs) {
        super.onWindowFocusChanged(hs);
        applyFullscreenWindow();

    }

    public void onRestart() {
        super.onRestart();
        applyFullscreenWindow();

        String bfset1 = AppPaths.appPath("settings/f15/set2.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(VideoPlayerActivity.this, (String) (bfset1)), "false") && cacheplay && !java.util.Objects.equals(mPlayer, null)) {

            mPlayer.start();
            cacheplay = false;

        }

    }

    public void onStart() {
        super.onStart();
        applyFullscreenWindow();

        String bfset1 = AppPaths.appPath("settings/f15/set2.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(VideoPlayerActivity.this, (String) (bfset1)), "false") && cacheplay && !java.util.Objects.equals(mPlayer, null)) {

            mPlayer.start();
            cacheplay = false;

        }

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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
        applyFullscreenWindow();

        String bfset1 = AppPaths.appPath("settings/f15/set2.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(VideoPlayerActivity.this, (String) (bfset1)), "false") && cacheplay && !java.util.Objects.equals(mPlayer, null)) {

            mPlayer.start();
            cacheplay = false;

        }

    }

    public void onStop() {
        super.onStop();

        String bfset1 = AppPaths.appPath("settings/f15/set2.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(VideoPlayerActivity.this, (String) (bfset1)), "false") && !java.util.Objects.equals(mPlayer, null)) {

            if (mPlayer.isPlaying()) {

                mPlayer.pause();
                cacheplay = true;

            }

        }

    }

    public boolean onKeyDown(int kc, KeyEvent ke) {

        String set4 = AppPaths.appPath("settings/f15/set4.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(VideoPlayerActivity.this, (String) (set4)), "false")) {

            if (kc == 4) {

                open.cn.awg.pro.core.NativeActions.finish(VideoPlayerActivity.this);

            }

        } else {

            if (kc == 4) {

            }

        }
        return false;

    }

    private void onXxbj2Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).getVisibility() == android.view.View.VISIBLE) {

            if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj8)).getVisibility() == android.view.View.VISIBLE) {

                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj8)).setVisibility(android.view.View.GONE);
                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.backt)).setVisibility(android.view.View.GONE);
                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setVisibility(android.view.View.VISIBLE);
                open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.tx1)), R.mipmap.a24);

            } else {

                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);

            }

        } else {

            reTabShow = true;
            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);

        }

    }

    private void onTitleBarClick(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(VideoPlayerActivity.this);

    }

    private void onTx1Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj8)).getVisibility() == android.view.View.GONE) {

            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj8)).setVisibility(android.view.View.VISIBLE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setVisibility(android.view.View.GONE);
            open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.tx1)), R.mipmap.a25);

        } else {

            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj8)).setVisibility(android.view.View.GONE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setVisibility(android.view.View.VISIBLE);
            open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.tx1)), R.mipmap.a24);

        }

    }

    private void onWb5Click(View vw) {
        mplaying();

    }

    private void onTx2Click(View vw) {
        reTabShow = true;
        mplaying();

    }

    private void onXxbj10Click(View vw) {
        reTabShow = true;

    }

    private boolean onXxbj10LongClick(View vw) {

        String set2 = AppPaths.appPath("settings/f15/set1.inf");

        if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(VideoPlayerActivity.this, (String) (set2)), "true")) {

            if (((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.backt)).getVisibility() == android.view.View.GONE) {

                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj8)).setVisibility(android.view.View.VISIBLE);
                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setVisibility(android.view.View.GONE);
                open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.tx1)), R.mipmap.a25);
                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.backt)).setVisibility(android.view.View.VISIBLE);
                ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.qtgd1)).requestFocus();

            } else {

                ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj8)).setVisibility(android.view.View.GONE);
                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.backt)).setVisibility(android.view.View.GONE);
                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setVisibility(android.view.View.VISIBLE);
                open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.tx1)), R.mipmap.a24);

            }

        }
        return true;

    }

    private void onTdt1StopTrackingTouch(SeekBar vw) {

        final android.widget.SeekBar tdt1 = ((android.widget.SeekBar) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.tdt1));

        long jds = tdt1.getProgress();
        mPlayer.seekTo(jds);

    }

    private void onTdt1StartTrackingTouch(SeekBar vw) {

    }

    private void onTdt1ProgressChanged(SeekBar vw, int ps, boolean fu) {

    }

    private void onTdt3ProgressChanged(SeekBar vw, int ps, boolean fu) {

        int jd = ((android.widget.SeekBar) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.tdt3)).getProgress();
        msetaudio(jd);

    }

    private void onTdt3StartTrackingTouch(SeekBar vw) {

    }

    private void onTdt3StopTrackingTouch(SeekBar vw) {

    }

    private void onWb19Click(View vw) {
        screenScaleType = 1;
        msetScreenScaleType();

    }

    private void onWb20Click(View vw) {
        screenScaleType = 2;
        msetScreenScaleType();

    }

    private void onWb21Click(View vw) {
        screenScaleType = 3;
        msetScreenScaleType();

    }

    private void onWb22Click(View vw) {
        screenScaleType = 4;
        msetScreenScaleType();

    }

    private void onWb24Click(View vw) {
        screenScaleType = 5;
        msetScreenScaleType();

    }

    private void onWb31Click(View vw) {
        screenScaleType = 6;
        msetScreenScaleType();

    }

    private void onWb26Click(View vw) {
        setRotation = 1;
        mPlayer.setRotation(((float) (0.0)));
        msetScreenScaleType();
        msetRotation();

    }

    private void onWb27Click(View vw) {
        setRotation = 2;
        mPlayer.setRotation(((float) (90.0)));
        msetScreenScaleType();
        msetRotation();

    }

    private void onWb28Click(View vw) {
        setRotation = 3;
        mPlayer.setRotation(((float) (180.0)));
        msetScreenScaleType();
        msetRotation();

    }

    private void onWb29Click(View vw) {
        setRotation = 4;
        mPlayer.setRotation(((float) (270.0)));
        msetRotation();
        msetScreenScaleType();

    }

    private void onWb13Click(View vw) {
        mPlayer.setSpeed(((float) (0.5)));
        msetspeed();

    }

    private void onWb14Click(View vw) {
        mPlayer.setSpeed(((float) (1.0)));
        msetspeed();

    }

    private void onWb16Click(View vw) {
        mPlayer.setSpeed(((float) (1.5)));
        msetspeed();

    }

    private void onWb17Click(View vw) {
        mPlayer.setSpeed(((float) (2.0)));
        msetspeed();

    }

    private void onWb10Click(View vw) {
        new open.cn.awg.pro.core.NativeDevice(VideoPlayerActivity.this).copyToClipboard(url);
        e1.tsk_QR("提示", "已复制链接至剪切板\n\n扫描二维码 快一步访问", url);

    }

    private void onWb15Click(View vw) {
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                String[] iyu = new String[]{"url"};

                String[] val = new String[]{url};
                open.cn.awg.pro.core.NativeActions.launch(VideoPlayerActivity.this, (Class<?>) (AppDownloadActivity.class), (String[]) (iyu), (Object[]) (val));

            }

        });

    }

    private void onWb2Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj8)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj8)).setVisibility(android.view.View.GONE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.backt)).setVisibility(android.view.View.GONE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setVisibility(android.view.View.VISIBLE);
            open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.tx1)), R.mipmap.a24);

        }
        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);
        ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xdbj3)).setVisibility(android.view.View.VISIBLE);

        String ts1 = AppPaths.appPath("settings/f15/ts/ts1.inf");

        if (!open.cn.awg.pro.core.NativeFiles.exists(VideoPlayerActivity.this, (String) (ts1))) {

            open.cn.awg.pro.core.NativeFiles.writeText(VideoPlayerActivity.this, (String) (ts1), (String) ("1"));
            e1.tsk("提示", "长按屏幕正中间位置可退出\"锁定\"");

        }

    }

    private void onWb1Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj8)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj8)).setVisibility(android.view.View.GONE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.backt)).setVisibility(android.view.View.GONE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setVisibility(android.view.View.VISIBLE);
            open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.tx1)), R.mipmap.a24);

        }
        open.cn.awg.pro.core.NativeActions.launch(VideoPlayerActivity.this, (Class<?>) (MediaBrowserSettingsActivity.class));

    }

    private void onWb33Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);

        } else {

            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);

        }
        open.cn.awg.pro.core.NativeActions.finish(VideoPlayerActivity.this);

    }

    private void onTx7Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj8)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj8)).setVisibility(android.view.View.GONE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.backt)).setVisibility(android.view.View.GONE);
            ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setVisibility(android.view.View.VISIBLE);
            open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.tx1)), R.mipmap.a24);

        }
        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);
        ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xdbj3)).setVisibility(android.view.View.VISIBLE);

        String ts1 = AppPaths.appPath("settings/f15/ts/ts1.inf");

        if (!open.cn.awg.pro.core.NativeFiles.exists(VideoPlayerActivity.this, (String) (ts1))) {

            open.cn.awg.pro.core.NativeFiles.writeText(VideoPlayerActivity.this, (String) (ts1), (String) ("1"));
            e1.tsk("提示", "长按屏幕正中间位置可退出\"锁定\"");

        }

    }

    private void onTx5Click(View vw) {
        open.cn.awg.pro.core.NativeActions.launch(VideoPlayerActivity.this, (Class<?>) (MediaBrowserSettingsActivity.class));

    }

    private void onTx6Click(View vw) {

        if (((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).getVisibility() == android.view.View.VISIBLE) {

            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.GONE);

        } else {

            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);

        }
        open.cn.awg.pro.core.NativeActions.finish(VideoPlayerActivity.this);

    }

    private boolean onQtgd1Touch(View vw, MotionEvent me) {
        reTabShow = true;

        if (me.getAction() == MotionEvent.ACTION_UP) {

            x2 = me.getX();
            y2 = me.getY();

            if (y1 - y2 >= 50) {

                String set2 = AppPaths.appPath("settings/f15/set1.inf");

                if (java.util.Objects.equals(open.cn.awg.pro.core.NativeFiles.readText(VideoPlayerActivity.this, (String) (set2)), "false")) {

                    ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj8)).setVisibility(android.view.View.VISIBLE);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.title_bar)).setVisibility(android.view.View.GONE);
                    open.cn.awg.pro.core.NativeViewValues.image(((android.widget.ImageView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.tx1)), R.mipmap.a25);
                    ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.backt)).setVisibility(android.view.View.VISIBLE);
                    ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.qtgd1)).requestFocus();

                }

            } else if (y2 - y1 >= 50) {

            } else if (x1 - x2 >= 50) {

            } else if (x2 - x1 >= 50) {

            }

        }
        return false;

    }

    private void onWb23Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(VideoPlayerActivity.this);

    }

    private boolean onXxbj19Touch(View vw, MotionEvent me) {
        return true;

    }

    private void onXdbj3Click(View vw) {

        int i;

    }

    private boolean onWb8LongClick(View vw) {
        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(android.view.View.VISIBLE);
        ((android.widget.RelativeLayout) open.cn.awg.pro.core.NativeViewValues.findView(VideoPlayerActivity.this.getWindow().getDecorView(), R.id.xdbj3)).setVisibility(android.view.View.GONE);
        reTabShow = true;
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        VideoView b773cd988a = (VideoView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.video_player_view);

        LinearLayout dc0a1b897b = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj2);
        dc0a1b897b.setOnClickListener(xxbj2ClickListener);

        LinearLayout e1433e26d6 = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj1);

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        LinearLayout i9926c2f556 = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj9);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        TextView i2a86c78b06 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb5);
        i2a86c78b06.setOnClickListener(wb5ClickListener);

        ImageView i4d951cdb0e = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx2);
        i4d951cdb0e.setOnClickListener(tx2ClickListener);

        LinearLayout i6c4a6b953f = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj6);

        LinearLayout i464e6cc6fb = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj10);
        i464e6cc6fb.setOnClickListener(xxbj10ClickListener);
        i464e6cc6fb.setOnLongClickListener(xxbj10LongClickListener);

        SeekBar d11169f9c0 = (SeekBar) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tdt1);
        d11169f9c0.setOnSeekBarChangeListener(tdt1SeekBarChangeListener);

        SeekBar i7843ee147b = (SeekBar) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tdt3);
        i7843ee147b.setOnSeekBarChangeListener(tdt3SeekBarChangeListener);

        TextView i16d7dc6512 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb19);
        i16d7dc6512.setOnClickListener(wb19ClickListener);

        TextView i91cc1b9bf5 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb20);
        i91cc1b9bf5.setOnClickListener(wb20ClickListener);

        TextView i58d9590cdf = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb21);
        i58d9590cdf.setOnClickListener(wb21ClickListener);

        TextView i3b389d6120 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb22);
        i3b389d6120.setOnClickListener(wb22ClickListener);

        TextView dc32622ccd = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb24);
        dc32622ccd.setOnClickListener(wb24ClickListener);

        TextView f2c0485bfc = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb31);
        f2c0485bfc.setOnClickListener(wb31ClickListener);

        TextView i90adf494fc = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb26);
        i90adf494fc.setOnClickListener(wb26ClickListener);

        TextView cce8454c0e = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb27);
        cce8454c0e.setOnClickListener(wb27ClickListener);

        TextView i0924d0fe5d = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb28);
        i0924d0fe5d.setOnClickListener(wb28ClickListener);

        TextView i689cfe9338 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb29);
        i689cfe9338.setOnClickListener(wb29ClickListener);

        TextView adf370a235 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb13);
        adf370a235.setOnClickListener(wb13ClickListener);

        TextView i66be67e25d = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb14);
        i66be67e25d.setOnClickListener(wb14ClickListener);

        TextView f3906a9028 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb16);
        f3906a9028.setOnClickListener(wb16ClickListener);

        TextView i2318575919 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb17);
        i2318575919.setOnClickListener(wb17ClickListener);

        TextView i81652f77b6 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb10);
        i81652f77b6.setOnClickListener(wb10ClickListener);

        TextView i263afb26ad = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb15);
        i263afb26ad.setOnClickListener(wb15ClickListener);

        TextView i7c3e3c6b5d = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb2);
        i7c3e3c6b5d.setOnClickListener(wb2ClickListener);

        TextView ff56a8e7f6 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb1);
        ff56a8e7f6.setOnClickListener(wb1ClickListener);

        TextView f71caff030 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb33);
        f71caff030.setOnClickListener(wb33ClickListener);

        ImageView i3621e3f4a1 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx7);
        i3621e3f4a1.setOnClickListener(tx7ClickListener);

        ImageView i4a4248a0f2 = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx5);
        i4a4248a0f2.setOnClickListener(tx5ClickListener);

        ImageView i6f2c7751cf = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx6);
        i6f2c7751cf.setOnClickListener(tx6ClickListener);

        NestedScrollView a6dab51133 = (NestedScrollView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.qtgd1);
        a6dab51133.setOnTouchListener(qtgd1TouchListener);

        RelativeLayout i3167b45174 = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj2);

        TextView i6614d6d216 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb23);
        i6614d6d216.setOnClickListener(wb23ClickListener);

        LinearLayout i28d59c5074 = (LinearLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xxbj19);
        i28d59c5074.setOnTouchListener(xxbj19TouchListener);

        RelativeLayout i20c2777fab = (RelativeLayout) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.xdbj3);
        i20c2777fab.setOnClickListener(xdbj3ClickListener);

        TextView fc1598ee09 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb8);
        fc1598ee09.setOnLongClickListener(wb8LongClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
