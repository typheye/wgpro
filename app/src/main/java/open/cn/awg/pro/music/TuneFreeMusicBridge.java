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



import open.cn.awg.pro.core.IappCompat;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import i.app.iClass;

import org.json.JSONArray;
import org.json.JSONObject;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.media.VideoPlayerActivity;
import open.cn.awg.pro.R;
import open.cn.awg.pro.ui.list.TuneFreeSongItemActivity;

@SuppressLint("StaticFieldLeak")
public class TuneFreeMusicBridge extends iClass {

    public static Context context;
    public final TuneFreeMusicBridge lei = this, 类 = this;
    public i.runlibrary.app.v.v7lb$UserAdapter nmyylbspq = null;
    public String cookiepath = AppPaths.appPath("data/plugin/tunefree/cookie");
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public String endurl = "";
    public String api = "/song/url";

    public TuneFreeMusicBridge(i.runlibrary.app.AppInfo _APPINFO) {
        super(_APPINFO);

    }

    public void csh() {
        context = AwgProApplication.getContext();

        i.runlibrary.app.v.v7lb lb = st.v7lb(R.id.nmyyv7lb1);
        nmyylbspq = IappCompat.v7lbAdapter(lb, TuneFreeSongItemActivity.class, R.layout.tune_free_song_item);

        LinearLayoutManager nx = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true);

        RecyclerView v7lbdx = lb.st;
        v7lbdx.setLayoutManager(nx);

    }

    public String lrcFix(String lr) {

        String lrc = zf.qctwkg(lr);

        String[] lrcH = zf.fg(lrc, "\n", true);

        for (String x : lrcH) {

            String x2 = zf.qc(x, 10, 11);

            if (zf.ckt(x, "[") && zf.dy(x2, "]")) {

                String a = zf.qc(x, 1, 10);

                String b = zf.qc(x, 1, 9);
                a = "[" + a + "]";
                b = "[" + b + "]";
                lrc = zf.th(lrc, a, b);

            }

        }
        return lrc;

    }

    public void search_i1(Object v, int o) {
        nmyylbspq.sc();

        final int oo = o;

        int[] kj = new int[]{R.id.wb1, R.id.wb3, R.id.wb2};

        String[] jk = new String[]{"", "", ""};

        i.runlibrary.app.zf$json jo = (i.runlibrary.app.zf$json) (Object) zf.json(v);

        JSONObject json = jo.json;

        Object v_result = jo.hq(json, "result");

        Object v_code = jo.hq(json, "code");

        if (!zf.dy(v_result, "")) {

            i.runlibrary.app.zf$json jo_z1 = (i.runlibrary.app.zf$json) (Object) zf.json(v_result);

            JSONObject json_z1 = jo_z1.json;

            Object v_songs = jo_z1.hq(json_z1, "songs");

            Object v_songCount = jo_z1.hq(json_z1, "songCount");

            if (!zf.dy(v_songs, "")) {

                JSONArray list = jo_z1.dxlb(json_z1, "songs");

                int size = jo_z1.cd(list);

                while (size > 0) {

                    size--;

                    JSONObject dx = jo_z1.dx(list, size);

                    Object v_l_name = jo_z1.hq(dx, "name");

                    Object v_l_id = jo_z1.hq(dx, "id");

                    Object v_l_ar = jo_z1.hq(dx, "ar");

                    if (!zf.dy(v_l_ar, "")) {

                        v_l_ar = "";

                        i.runlibrary.app.zf$json jo_z2 = (i.runlibrary.app.zf$json) (Object) zf.json(dx);

                        JSONObject json_z2 = jo_z2.json;

                        JSONArray list2 = jo_z2.dxlb(json_z2, "ar");

                        int size2 = jo_z2.cd(list2);

                        while (size2 > 0) {

                            size2 = size2 - 1;

                            JSONObject dx2 = jo_z2.dx(list2, size2);

                            Object v_l2_id = jo_z2.hq(dx2, "id");

                            Object v_l2_name = jo_z2.hq(dx2, "name");
                            v_l_ar = v_l_ar + String.valueOf(v_l2_name) + ";";

                        }

                    }

                    String _v_l_ar = v_l_ar.toString();
                    v_l_ar = zf.qc(v_l_ar, null, _v_l_ar.length() - 1);
                    jk = new String[]{v_l_name.toString(), v_l_ar.toString(), v_l_id.toString()};
                    nmyylbspq.j(kj, jk);

                }

            }

            String nums2 = v_songCount.toString();

            final String ynums = nums2;

            int nums2_ = Integer.parseInt(nums2) % 20;

            if (nums2_ > 0) {

                nums2 = String.valueOf(Integer.parseInt(nums2) - nums2_ + 20);

            }

            final String nums = nums2;
            gj.jmxc(new Runnable() {

                public void run() {

                    int loo = Integer.parseInt(nums) / 20;

                    if (zf.dy(ynums, "0")) {

                        nmyylbspq.sc();
                        st.wb(R.id.wb3).zf("未搜索到相关歌曲/歌手");
                        st.wb(R.id.myswb).zf("第1/1页");
                        st.xxbj(R.id.xxbj1).kjd(8);
                        st.wb(R.id.wb3).kjd(0);

                    } else {

                        st.wb(R.id.wb3).zf("共搜索到" + nums + "首歌曲");
                        st.wb(R.id.myswb).zf("第" + oo + "/" + loo + "页");

                        if (oo == 1 && loo == 1) {

                            st.xxbj(R.id.xxbj1).kjd(8);

                        } else {

                            st.xxbj(R.id.xxbj1).kjd(0);

                        }
                        st.wb(R.id.wb3).kjd(8);

                    }

                }

            });

        }
        gj.jmxc(new Runnable() {

            public void run() {

                nmyylbspq.sx();

            }

        });

    }

    public String getMCU() {

        String cookie = wj.dqwb(cookiepath);

        String[] cf = zf.fg(cookie, ";", false);

        String mcu = "";

        for (String line : cf) {

            if (zf.ckt(line, "MUSIC_U")) {

                mcu = line;

            }

        }
        return mcu;

    }

    public void search_i2(Object v, int o) {
        nmyylbspq.sc();

        final int oo = o;

        int[] kj = new int[]{R.id.wb1, R.id.wb3, R.id.wb2};

        String[] jk = new String[]{"", "", ""};

        i.runlibrary.app.zf$json jo = (i.runlibrary.app.zf$json) (Object) zf.json(v);

        JSONObject json = jo.json;

        JSONArray list = jo.dxlb(json, "data");

        Object count = jo.hq(json, "count");

        if (!zf.dy(list, "")) {

            int size = jo.cd(list);

            while (size > 0) {

                size--;

                JSONObject dx = jo.dx(list, size);

                String v_l_name = jo.hq(dx, "songName").toString();

                String v_l_id = jo.hq(dx, "songId").toString();

                String v_l_ar = jo.hq(dx, "artist").toString();

                if (zf.dy(v_l_ar, "")) {

                    if (zf.cz(v_l_name, " - ")) {

                        v_l_ar = zf.qc(v_l_name, " - ", null);
                        v_l_ar = zf.qctwkg(v_l_ar);
                        v_l_name = zf.qc(v_l_name, null, " - ");
                        v_l_name = zf.qctwkg(v_l_name);

                        if (zf.dy(v_l_ar, "")) {

                            v_l_ar = "未知歌手";

                        }

                    } else {

                        v_l_ar = "未知歌手";

                    }

                }
                jk = new String[]{v_l_name, v_l_ar, v_l_id};
                nmyylbspq.j(kj, jk);

            }

            String nums2 = count.toString();

            final String ynums = nums2;

            int nums2_ = Integer.parseInt(nums2) % 20;

            if (nums2_ > 0) {

                nums2 = String.valueOf(Integer.parseInt(nums2) - nums2_ + 20);

            }

            final String nums = nums2;
            gj.jmxc(new Runnable() {

                public void run() {

                    int loo = Integer.parseInt(nums) / 20;

                    if (zf.dy(ynums, "0")) {

                        nmyylbspq.sc();
                        st.wb(R.id.wb3).zf("未搜索到相关歌曲/歌手");
                        st.wb(R.id.myswb).zf("第1/1页");
                        st.xxbj(R.id.xxbj1).kjd(8);
                        st.wb(R.id.wb3).kjd(0);

                    } else {

                        st.wb(R.id.wb3).zf("共搜索到" + nums + "首歌曲");
                        st.wb(R.id.myswb).zf("第" + oo + "/" + loo + "页");

                        if (oo == 1 && loo == 1) {

                            st.xxbj(R.id.xxbj1).kjd(8);

                        } else {

                            st.xxbj(R.id.xxbj1).kjd(0);

                        }
                        st.wb(R.id.wb3).kjd(8);

                    }

                }

            });

        }
        gj.jmxc(new Runnable() {

            public void run() {

                nmyylbspq.sx();

            }

        });

    }

    public void loadMusic(Object id, Object title) {

        String set35 = AppPaths.appPath("settings/other/0xO7YX2/url");

        String set36 = AppPaths.appPath("settings/other/0xO7YX2/cookie");

        final String ljf = wj.dqwb(AppPaths.appPath("data/assets/a17"));

        final Object xx = id;

        final String url = zf.qctwkg(wj.dqwb(set35)) + api;

        final String url22 = "https://auth.sayqz.com/";

        final String url_ = zf.qctwkg(wj.dqwb(set35));

        final i.runlibrary.app.v.xxbj x2 = st.xxbj(R.id.xxbj6);

        final i.runlibrary.app.v.xxbj x3 = st.xxbj(R.id.xxbj4);

        final Object t = title;
        gj.xc(new Thread() {

            public void run() {

                gj.jmxc(new Runnable() {

                    public void run() {

                        x2.kjd(0);
                        x3.kjd(8);

                    }

                });

                if (zf.dy(xx, "")) {

                    e1.tsk("提示", "ID不能为空");
                    gj.jmxc(new Runnable() {

                        public void run() {

                            x2.kjd(8);
                            x3.kjd(0);

                        }

                    });

                } else {

                    String setback = "";

                    String ljf = wj.dqwb(AppPaths.appPath("data/assets/a17"));

                    String sjzx = xt.sj(4);

                    String whq = "?path=song/url" + ljf + "id=" + xx + ljf + "level=standard" + ljf + "cookie=" + getMCU();

                    String back = wl.hq(url22 + whq, null, "utf-8", null, true, null, 20000, 20000, null);

                    if (zf.dy(back, "") || zf.dy(back, null)) {

                        setback = "0";

                    } else {

                        setback = back;

                    }

                    if (zf.dy(setback, "0")) {

                        e1.tsk("提示", "加载失败,请重试(-1)");
                        gj.jmxc(new Runnable() {

                            public void run() {

                                x2.kjd(8);
                                x3.kjd(0);

                            }

                        });

                    } else {

                        String whq2 = "/lyric?id=" + xx;

                        String back2 = wl.hq(url_ + whq2, null, "utf-8");

                        if (!zf.dy(back2, "") || !zf.dy(back2, null)) {

                            try {

                                i.runlibrary.app.zf$json jo_l1 = (i.runlibrary.app.zf$json) (Object) zf.json(back2);

                                JSONObject json_l1 = jo_l1.json;

                                Object lrc = jo_l1.hq(json_l1, "lrc");

                                if (lrc.toString().length() > 4) {

                                    i.runlibrary.app.zf$json jo_l2 = (i.runlibrary.app.zf$json) (Object) zf.json(lrc);

                                    JSONObject json_l2 = jo_l2.json;
                                    back2 = String.valueOf(jo_l2.hq(json_l2, "lyric"));
                                    back2 = back2;
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

                            i.runlibrary.app.zf$json jo_z1 = (i.runlibrary.app.zf$json) (Object) zf.json(setback);

                            JSONObject json_z1 = jo_z1.json;

                            JSONArray list = jo_z1.dxlb(json_z1, "data");

                            int size = jo_z1.cd(list);

                            String url = "";

                            while (size > 0) {

                                size = size - 1;

                                JSONObject dx = jo_z1.dx(list, size);
                                url = String.valueOf(jo_z1.hq(dx, "url"));

                            }

                            if (zf.dy(e1.urlLockString(url), "0")) {

                                e1.tsk("提示", "获取链接失败(-2)");

                            } else {

                                final String[] name = new String[]{"url", "title", "lrc"};

                                final Object[] value = new Object[]{url, t, back2};
                                gj.jmxc(new Runnable() {

                                    public void run() {

                                        gj.tz(VideoPlayerActivity.class, name, value);

                                    }

                                });

                            }

                        } catch (Throwable e) {

                            e1.tsk("提示", "获取链接失败(-3)");

                        }
                        gj.jmxc(new Runnable() {

                            public void run() {

                                x2.kjd(8);
                                x3.kjd(0);

                            }

                        });

                    }

                }

            }

        });

    }
}
