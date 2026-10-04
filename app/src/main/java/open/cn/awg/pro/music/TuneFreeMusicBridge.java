/**
 ******************************************************************************
 * @file    TuneFreeMusicBridge.java
 * @author  Typheye
 * @brief   TuneFree music search and playback bridge.
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
package open.cn.awg.pro.music;



import android.content.Context;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONObject;

import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.NativeDevice;
import open.cn.awg.pro.media.VideoPlayerActivity;
import open.cn.awg.pro.R;

public class TuneFreeMusicBridge {
    private final Context context;
    private final Activity owner;

public final TuneFreeMusicBridge lei = this, 类 = this;
    public open.cn.awg.pro.ui.list.NativeListAdapter nmyylbspq = null;
    public String cookiepath = AppPaths.appPath("data/plugin/tunefree/cookie");
    public AppRuntimeBridge e1;
    public String endurl = "";
    public String api = "/song/url";

    public TuneFreeMusicBridge(Context context) {
        this.context = java.util.Objects.requireNonNull(context);
        if (!(context instanceof Activity)) {
            throw new IllegalArgumentException("TuneFreeMusicBridge requires an Activity context");
        }
        this.owner = (Activity) context;
        e1 = new AppRuntimeBridge(context);
    }

    public void csh() {
        

        androidx.recyclerview.widget.RecyclerView lb = ((androidx.recyclerview.widget.RecyclerView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.nmyyv7lb1));
        nmyylbspq = open.cn.awg.pro.ui.list.NativeListAdapter.attach(owner, lb, R.layout.tune_free_song_item, new open.cn.awg.pro.ui.list.TuneFreeSongRowBinder(owner));

        LinearLayoutManager nx = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true);

        RecyclerView v7lbdx = lb;
        v7lbdx.setLayoutManager(nx);

    }

    public String lrcFix(String lr) {

        String lrc = open.cn.awg.pro.core.TextValues.trim(lr);

        String[] lrcH = open.cn.awg.pro.core.TextValues.split(lrc, "\n", true);

        for (String x : lrcH) {

            String x2 = open.cn.awg.pro.core.TextValues.slice(x, 10, 11);

            if (open.cn.awg.pro.core.TextValues.startsWith(x, "[") && java.util.Objects.equals(x2, "]")) {

                String a = open.cn.awg.pro.core.TextValues.slice(x, 1, 10);

                String b = open.cn.awg.pro.core.TextValues.slice(x, 1, 9);
                a = "[" + a + "]";
                b = "[" + b + "]";
                lrc = open.cn.awg.pro.core.TextValues.replace(lrc, a, b);

            }

        }
        return lrc;

    }

    public void search_i1(Object v, int o) {
        nmyylbspq.clearRows();

        final int oo = o;

        int[] kj = new int[]{R.id.wb1, R.id.wb3, R.id.wb2};

        String[] jk = new String[]{"", "", ""};

        org.json.JSONObject jo = (org.json.JSONObject) (Object) open.cn.awg.pro.core.JsonValues.parseObject(v);

        JSONObject json = jo;

        Object v_result = open.cn.awg.pro.core.JsonValues.value(json, "result");

        Object v_code = open.cn.awg.pro.core.JsonValues.value(json, "code");

        if (!java.util.Objects.equals(v_result, "")) {

            org.json.JSONObject jo_z1 = (org.json.JSONObject) (Object) open.cn.awg.pro.core.JsonValues.parseObject(v_result);

            JSONObject json_z1 = jo_z1;

            Object v_songs = open.cn.awg.pro.core.JsonValues.value(json_z1, "songs");

            Object v_songCount = open.cn.awg.pro.core.JsonValues.value(json_z1, "songCount");

            if (!java.util.Objects.equals(v_songs, "")) {

                JSONArray list = open.cn.awg.pro.core.JsonValues.array(json_z1, "songs");

                int size = open.cn.awg.pro.core.JsonValues.length(list);

                while (size > 0) {

                    size--;

                    JSONObject dx = open.cn.awg.pro.core.JsonValues.object(list, size);

                    Object v_l_name = open.cn.awg.pro.core.JsonValues.value(dx, "name");

                    Object v_l_id = open.cn.awg.pro.core.JsonValues.value(dx, "id");

                    Object v_l_ar = open.cn.awg.pro.core.JsonValues.value(dx, "ar");

                    if (!java.util.Objects.equals(v_l_ar, "")) {

                        v_l_ar = "";

                        org.json.JSONObject jo_z2 = (org.json.JSONObject) (Object) open.cn.awg.pro.core.JsonValues.parseObject(dx);

                        JSONObject json_z2 = jo_z2;

                        JSONArray list2 = open.cn.awg.pro.core.JsonValues.array(json_z2, "ar");

                        int size2 = open.cn.awg.pro.core.JsonValues.length(list2);

                        while (size2 > 0) {

                            size2 = size2 - 1;

                            JSONObject dx2 = open.cn.awg.pro.core.JsonValues.object(list2, size2);

                            Object v_l2_id = open.cn.awg.pro.core.JsonValues.value(dx2, "id");

                            Object v_l2_name = open.cn.awg.pro.core.JsonValues.value(dx2, "name");
                            v_l_ar = v_l_ar + String.valueOf(v_l2_name) + ";";

                        }

                    }

                    String _v_l_ar = v_l_ar.toString();
                    v_l_ar = open.cn.awg.pro.core.TextValues.slice(v_l_ar, null, _v_l_ar.length() - 1);
                    jk = new String[]{v_l_name.toString(), v_l_ar.toString(), v_l_id.toString()};
                    nmyylbspq.addRow(kj, jk);

                }

            }

            String nums2 = v_songCount.toString();

            final String ynums = nums2;

            int nums2_ = Integer.parseInt(nums2) % 20;

            if (nums2_ > 0) {

                nums2 = String.valueOf(Integer.parseInt(nums2) - nums2_ + 20);

            }

            final String nums = nums2;
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    int loo = Integer.parseInt(nums) / 20;

                    if (java.util.Objects.equals(ynums, "0")) {

                        nmyylbspq.clearRows();
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("未搜索到相关歌曲/歌手"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.myswb)).setText(String.valueOf("第1/1页"));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.wb3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                    } else {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("共搜索到" + nums + "首歌曲"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.myswb)).setText(String.valueOf("第" + oo + "/" + loo + "页"));

                        if (oo == 1 && loo == 1) {

                            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                        } else {

                            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                        }
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.wb3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                    }

                }

            });

        }
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                nmyylbspq.refreshRows();

            }

        });

    }

    public String getMCU() {

        String cookie = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (cookiepath));

        String[] cf = open.cn.awg.pro.core.TextValues.split(cookie, ";", false);

        String mcu = "";

        for (String line : cf) {

            if (open.cn.awg.pro.core.TextValues.startsWith(line, "MUSIC_U")) {

                mcu = line;

            }

        }
        return mcu;

    }

    public void search_i2(Object v, int o) {
        nmyylbspq.clearRows();

        final int oo = o;

        int[] kj = new int[]{R.id.wb1, R.id.wb3, R.id.wb2};

        String[] jk = new String[]{"", "", ""};

        org.json.JSONObject jo = (org.json.JSONObject) (Object) open.cn.awg.pro.core.JsonValues.parseObject(v);

        JSONObject json = jo;

        JSONArray list = open.cn.awg.pro.core.JsonValues.array(json, "data");

        Object count = open.cn.awg.pro.core.JsonValues.value(json, "count");

        if (!java.util.Objects.equals(list, "")) {

            int size = open.cn.awg.pro.core.JsonValues.length(list);

            while (size > 0) {

                size--;

                JSONObject dx = open.cn.awg.pro.core.JsonValues.object(list, size);

                String v_l_name = open.cn.awg.pro.core.JsonValues.value(dx, "songName").toString();

                String v_l_id = open.cn.awg.pro.core.JsonValues.value(dx, "songId").toString();

                String v_l_ar = open.cn.awg.pro.core.JsonValues.value(dx, "artist").toString();

                if (java.util.Objects.equals(v_l_ar, "")) {

                    if (open.cn.awg.pro.core.TextValues.contains(v_l_name, " - ")) {

                        v_l_ar = open.cn.awg.pro.core.TextValues.slice(v_l_name, " - ", null);
                        v_l_ar = open.cn.awg.pro.core.TextValues.trim(v_l_ar);
                        v_l_name = open.cn.awg.pro.core.TextValues.slice(v_l_name, null, " - ");
                        v_l_name = open.cn.awg.pro.core.TextValues.trim(v_l_name);

                        if (java.util.Objects.equals(v_l_ar, "")) {

                            v_l_ar = "未知歌手";

                        }

                    } else {

                        v_l_ar = "未知歌手";

                    }

                }
                jk = new String[]{v_l_name, v_l_ar, v_l_id};
                nmyylbspq.addRow(kj, jk);

            }

            String nums2 = count.toString();

            final String ynums = nums2;

            int nums2_ = Integer.parseInt(nums2) % 20;

            if (nums2_ > 0) {

                nums2 = String.valueOf(Integer.parseInt(nums2) - nums2_ + 20);

            }

            final String nums = nums2;
            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    int loo = Integer.parseInt(nums) / 20;

                    if (java.util.Objects.equals(ynums, "0")) {

                        nmyylbspq.clearRows();
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("未搜索到相关歌曲/歌手"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.myswb)).setText(String.valueOf("第1/1页"));
                        ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.wb3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                    } else {

                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.wb3)).setText(String.valueOf("共搜索到" + nums + "首歌曲"));
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.myswb)).setText(String.valueOf("第" + oo + "/" + loo + "页"));

                        if (oo == 1 && loo == 1) {

                            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                        } else {

                            ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj1)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                        }
                        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.wb3)).setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                    }

                }

            });

        }
        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

            public void run() {

                nmyylbspq.refreshRows();

            }

        });

    }

    public void loadMusic(Object id, Object title) {

        String set35 = AppPaths.appPath("settings/other/0xO7YX2/url");

        String set36 = AppPaths.appPath("settings/other/0xO7YX2/cookie");

        final String ljf = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (AppPaths.appPath("data/assets/a17")));

        final Object xx = id;

        final String url = open.cn.awg.pro.core.TextValues.trim(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set35))) + api;

        final String url22 = "https://auth.sayqz.com/";

        final String url_ = open.cn.awg.pro.core.TextValues.trim(open.cn.awg.pro.core.NativeFiles.readText(context, (String) (set35)));

        final android.widget.LinearLayout x2 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj6));

        final android.widget.LinearLayout x3 = ((android.widget.LinearLayout) open.cn.awg.pro.core.NativeViewValues.findView(owner.getWindow().getDecorView(), R.id.xxbj4));

        final Object t = title;
        open.cn.awg.pro.core.NativeActions.start(new Thread() {

            public void run() {

                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                    public void run() {

                        x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));
                        x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));

                    }

                });

                if (java.util.Objects.equals(xx, "")) {

                    e1.tsk(owner, "提示", "ID不能为空");
                    open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                        public void run() {

                            x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                            x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                        }

                    });

                } else {

                    String setback = "";

                    String ljf = open.cn.awg.pro.core.NativeFiles.readText(context, (String) (AppPaths.appPath("data/assets/a17")));

                    String sjzx = new NativeDevice(context).formatDate(4);

                    String whq = "?path=song/url" + ljf + "id=" + xx + ljf + "level=standard" + ljf + "cookie=" + getMCU();

                    String back = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url22 + whq), (String[]) (null), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) (null)), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                    if (java.util.Objects.equals(back, "") || java.util.Objects.equals(back, null)) {

                        setback = "0";

                    } else {

                        setback = back;

                    }

                    if (java.util.Objects.equals(setback, "0")) {

                        e1.tsk(owner, "提示", "加载失败,请重试(-1)");
                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                                x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                            }

                        });

                    } else {

                        String whq2 = "/lyric?id=" + xx;

                        String back2 = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url_ + whq2), (String[]) (null), (String) ("utf-8"), null, false, null, 0, 0, null).bodyForHttp200();

                        if (!java.util.Objects.equals(back2, "") || !java.util.Objects.equals(back2, null)) {

                            try {

                                org.json.JSONObject jo_l1 = (org.json.JSONObject) (Object) open.cn.awg.pro.core.JsonValues.parseObject(back2);

                                JSONObject json_l1 = jo_l1;

                                Object lrc = open.cn.awg.pro.core.JsonValues.value(json_l1, "lrc");

                                if (lrc.toString().length() > 4) {

                                    org.json.JSONObject jo_l2 = (org.json.JSONObject) (Object) open.cn.awg.pro.core.JsonValues.parseObject(lrc);

                                    JSONObject json_l2 = jo_l2;
                                    back2 = String.valueOf(open.cn.awg.pro.core.JsonValues.value(json_l2, "lyric"));
                                    back2 = lrcFix(back2);

                                } else {

                                    back2 = "";

                                }

                            } catch (Throwable e) {

                                back2 = "";

                            }

                        } else {

                            back2 = "";

                        }

                        try {

                            org.json.JSONObject jo_z1 = (org.json.JSONObject) (Object) open.cn.awg.pro.core.JsonValues.parseObject(setback);

                            JSONObject json_z1 = jo_z1;

                            JSONArray list = open.cn.awg.pro.core.JsonValues.array(json_z1, "data");

                            int size = open.cn.awg.pro.core.JsonValues.length(list);

                            String url = "";

                            while (size > 0) {

                                size = size - 1;

                                JSONObject dx = open.cn.awg.pro.core.JsonValues.object(list, size);
                                url = String.valueOf(open.cn.awg.pro.core.JsonValues.value(dx, "url"));

                            }

                            if (java.util.Objects.equals(e1.urlLockString(url), "0")) {

                                e1.tsk(owner, "提示", "获取链接失败(-2)");

                            } else {

                                final String[] name = new String[]{"url", "title", "lrc"};

                                final Object[] value = new Object[]{url, t, back2};
                                open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                                    public void run() {

                                        open.cn.awg.pro.core.NativeActions.launch(context, (Class<?>) (VideoPlayerActivity.class), (String[]) (name), (Object[]) (value));

                                    }

                                });

                            }

                        } catch (Throwable e) {

                            e1.tsk(owner, "提示", "获取链接失败(-3)");

                        }
                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                x2.setVisibility(open.cn.awg.pro.core.TextValues.intValue(8, -1));
                                x3.setVisibility(open.cn.awg.pro.core.TextValues.intValue(0, -1));

                            }

                        });

                    }

                }

            }

        });

    }
}
