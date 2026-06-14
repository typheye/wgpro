/**
 ******************************************************************************
 * @file    ExtensionPluginConfigBridge.java
 * @author  Typheye
 * @brief   Extension plugin configuration bridge.
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
package open.cn.awg.pro.plugin;

import android.view.View;

import i.app.iClass;

import open.cn.awg.pro.account.TypheyeServiceBridge;
import open.cn.awg.pro.chat.WearChatRuntimeBridge;
import open.cn.awg.pro.core.AppPaths;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.DebugStateStore;
import open.cn.awg.pro.R;
import open.cn.awg.pro.tools.CloudServiceActivity;

public class ExtensionPluginConfigBridge extends iClass {

    public static String titleback = "";

    public static String titleback2 = "";

    public static String id = "";

    public static String title = "";
    public final ExtensionPluginConfigBridge lei = this, 类 = this;
    public DebugStateStore debug = new DebugStateStore(_APPINFO);
    public AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
    public ExtensionPluginBridge e14 = new ExtensionPluginBridge(_APPINFO);
    public boolean state_login_base = false;

    public ExtensionPluginConfigBridge(i.runlibrary.app.AppInfo _APPINFO) {
        super(_APPINFO);

    }

    public void jz() {

        String cachehd = AppPaths.appPath("data/cjlb/lbx_id");
        id = wj.dqwb(cachehd);
        cachehd = AppPaths.appPath("data/cjlb/lbx_title");
        title = wj.dqwb(cachehd);
        titleback = "< " + title;
        titleback2 = title;
        gj.xc(new Thread() {

            public void run() {

                viewshow(1);
                gj.zt(500);

                String cachehd = AppPaths.appPath("data/cjlb/lbx_id");

                final String id = wj.dqwb(cachehd);

                if (e14.newG(Integer.parseInt(id))) {

                    try {

                        if (zf.dy(id, "20000")) {

                            m20000();

                        } else if (zf.dy(id, "20001")) {

                            m20001();

                        } else if (zf.dy(id, "20002")) {

                            m20002();

                        } else if (zf.dy(id, "20003")) {

                            m20003();

                        } else if (zf.dy(id, "20004")) {

                            m20004();

                        } else if (zf.dy(id, "20005")) {

                            m20005();

                        } else if (zf.dy(id, "20006")) {

                            m20006();

                        } else if (zf.dy(id, "20007")) {

                            m20007();

                        } else {

                            csh();

                        }

                    } catch (Throwable e) {

                        e1.upload_error(e, "m2.jz()");
                        gj.gb();

                    }

                } else {

                    gj.gb();

                }

            }

        });

    }

    public void m20000() {
        gj.jmxc(new Runnable() {

            public void run() {

                String color = "";

                final i.runlibrary.app.v.xdbj root = st.xdbj(R.id.plugin_runtime_view);
                root.scqb();

                i.runlibrary.app.v.xxbj xxbja = st.xxbj();
                xxbja.kg(-1, -2);
                xxbja.fx(1);
                xxbja.dqfs("center");

                i.runlibrary.app.v.an an1 = st.an();
                an1.kg(-1, -2);
                an1.zf("本地激活应用");
                an1.sj.dj(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        e1.jh();

                        final String msg = "已执行激活操作";
                        e1.tsk("提示", msg);

                    }

                });
                xxbja.j(an1);
                an1.wbj("3dp", "3dp", "3dp", "0dp");

                i.runlibrary.app.v.an an2 = st.an();
                an2.kg(-1, -2);
                an2.zf("取消激活应用");
                an2.sj.dj(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        String a = AppPaths.appPath("settings/f7/Enable2");
                        wj.xrwb(a, "");

                        final String msg = "已执行取消激活";
                        e1.tsk("提示", msg);

                    }

                });
                xxbja.j(an2);
                an2.wbj("3dp", "3dp", "3dp", "0dp");

                i.runlibrary.app.v.wb wba = st.wb();
                wba.kg(-2, -2);
                wba.nbj("3dp", -2, "3dp", -2);
                wba.zf("");
                wba.szzx("bold");
                wba.ztdx(9);
                color = zf.zf(R.color.colorTextU2);
                wba.zfys(color);
                xxbja.j(wba);
                root.j(xxbja);

            }

        });
        viewshow(0);

    }

    public void m20001() {
        gj.jmxc(new Runnable() {

            public void run() {

                String color = "";

                final i.runlibrary.app.v.xdbj root = st.xdbj(R.id.plugin_runtime_view);
                root.scqb();

                i.runlibrary.app.v.xxbj xxbja = st.xxbj();
                xxbja.kg(-1, -2);
                xxbja.fx(1);
                xxbja.dqfs("center");

                final i.runlibrary.app.v.bjk bjk1 = st.bjk();
                bjk1.kg(-1, -2);
                bjk1.tszf("请输入您的邮箱");
                color = zf.zf(R.color.colorTextU1);
                bjk1.zfys(color);
                color = zf.zf(R.color.colorTextU2);
                bjk1.tszfys(color);
                bjk1.ztdx(13);
                bjk1.nbj("8dp", "8dp", "8dp", "8dp");
                bjk1.xstyh(true);
                xxbja.j(bjk1);
                bjk1.wbj("3dp", "3dp", "3dp", "0dp");

                i.runlibrary.app.v.xxbj xxbjb = st.xxbj();
                xxbjb.kg(-1, -2);
                xxbjb.fx(0);
                xxbjb.dqfs("center");

                final i.runlibrary.app.v.bjk bjk2 = st.bjk();
                bjk2.kg(-1, -2);
                bjk2.tszf("请输入验证码");
                color = zf.zf(R.color.colorTextU1);
                bjk2.zfys(color);
                color = zf.zf(R.color.colorTextU2);
                bjk2.tszfys(color);
                bjk2.ztdx(13);
                bjk2.nbj("8dp", "8dp", "8dp", "8dp");
                bjk2.xstyh(true);
                xxbjb.j(bjk2);

                i.runlibrary.app.v.xxbj$xxbjgz bjk2_xxbjgz = (i.runlibrary.app.v.xxbj$xxbjgz) (Object) bjk2.xxbjgz();
                bjk2_xxbjgz.bjqz(1);

                final i.runlibrary.app.v.an an0 = st.an();
                an0.kg(-2, -2);
                an0.zf("获取");
                an0.sj.dj(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        if (zf.dy(an0.zf(), "获取")) {

                            gj.xc(new Thread() {

                                public void run() {

                                    TypheyeServiceBridge easy = new TypheyeServiceBridge(_APPINFO);
                                    easy.register$request$token(bjk1.zf(), an0);

                                }

                            });

                        } else if (zf.dy(an0.zf(), "已发送")) {

                            e1.tsk("提示", "发送验证码成功,请在邮箱查收");

                        }

                    }

                });
                xxbjb.j(an0);
                an0.wbj("3dp", "0dp", "0dp", "0dp");
                xxbja.j(xxbjb);
                xxbjb.wbj("3dp", "3dp", "3dp", "0dp");

                final i.runlibrary.app.v.bjk bjk3 = st.bjk();
                bjk3.kg(-1, -2);
                bjk3.tszf("请输入您的密码");
                color = zf.zf(R.color.colorTextU1);
                bjk3.zfys(color);
                color = zf.zf(R.color.colorTextU2);
                bjk3.tszfys(color);
                bjk3.wblx("textpassword");
                bjk3.ztdx(13);
                bjk3.nbj("8dp", "8dp", "8dp", "8dp");
                bjk3.xstyh(true);
                xxbja.j(bjk3);
                bjk3.wbj("3dp", "3dp", "3dp", "0dp");

                final i.runlibrary.app.v.bjk bjk4 = st.bjk();
                bjk4.kg(-1, -2);
                bjk4.tszf("请确认您的密码");
                color = zf.zf(R.color.colorTextU1);
                bjk4.zfys(color);
                color = zf.zf(R.color.colorTextU2);
                bjk4.tszfys(color);
                bjk4.wblx("textpassword");
                bjk4.ztdx(13);
                bjk4.nbj("8dp", "8dp", "8dp", "8dp");
                bjk4.xstyh(true);
                xxbja.j(bjk4);
                bjk4.wbj("3dp", "3dp", "3dp", "0dp");

                i.runlibrary.app.v.an an1 = st.an();
                an1.kg(-1, -2);
                an1.zf("提交");
                an1.sj.dj(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        gj.xc(new Thread() {

                            public void run() {

                                viewshow(1);

                                TypheyeServiceBridge easy = new TypheyeServiceBridge(_APPINFO);

                                if (easy.register$request(bjk1.zf(), bjk2.zf(), bjk3.zf(), bjk4.zf())) {

                                    viewshow(0);

                                }

                            }

                        });

                    }

                });
                xxbja.j(an1);
                an1.wbj("3dp", "3dp", "3dp", "0dp");

                i.runlibrary.app.v.wb wba = st.wb();
                wba.kg(-2, -2);
                wba.nbj("3dp", -2, "3dp", -2);
                wba.zf("");
                wba.szzx("bold");
                wba.ztdx(9);
                color = zf.zf(R.color.colorTextU2);
                wba.zfys(color);
                xxbja.j(wba);
                root.j(xxbja);

            }

        });
        viewshow(0);

    }

    public void m20002() {
        gj.jmxc(new Runnable() {

            public void run() {

                String color = "";

                final i.runlibrary.app.v.xdbj root = st.xdbj(R.id.plugin_runtime_view);
                root.scqb();

                i.runlibrary.app.v.xxbj xxbja = st.xxbj();
                xxbja.kg(-1, -2);
                xxbja.fx(1);
                xxbja.dqfs("center");

                final i.runlibrary.app.v.bjk bjk1 = st.bjk();
                bjk1.kg(-1, -2);
                bjk1.tszf("请输入房间名");
                color = zf.zf(R.color.colorTextU1);
                bjk1.zfys(color);
                color = zf.zf(R.color.colorTextU2);
                bjk1.tszfys(color);
                bjk1.ztdx(13);
                bjk1.nbj("8dp", "8dp", "8dp", "8dp");
                bjk1.xstyh(true);
                xxbja.j(bjk1);
                bjk1.wbj("3dp", "3dp", "3dp", "0dp");

                final i.runlibrary.app.v.bjk bjk2 = st.bjk();
                bjk2.kg(-1, -2);
                bjk2.tszf("请输入房间ID");
                bjk2.wblx("number");
                color = zf.zf(R.color.colorTextU1);
                bjk2.zfys(color);
                color = zf.zf(R.color.colorTextU2);
                bjk2.tszfys(color);
                bjk2.ztdx(13);
                bjk2.nbj("8dp", "8dp", "8dp", "8dp");
                bjk2.xstyh(true);
                xxbja.j(bjk2);
                bjk2.wbj("3dp", "3dp", "3dp", "0dp");

                i.runlibrary.app.v.an an1 = st.an();
                an1.kg(-1, -2);
                an1.zf("提交");
                an1.sj.dj(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        gj.xc(new Thread() {

                            public void run() {

                                viewshow(1);

                                WearChatRuntimeBridge e10 = new WearChatRuntimeBridge(_APPINFO);

                                if (e10.part_buildNewRoom(bjk1.zf(), bjk2.zf())) {

                                    viewshow(0);

                                }

                            }

                        });

                    }

                });
                xxbja.j(an1);
                an1.wbj("3dp", "3dp", "3dp", "0dp");

                i.runlibrary.app.v.wb wba = st.wb();
                wba.kg(-2, -2);
                wba.nbj("3dp", -2, "3dp", -2);
                wba.zf("");
                wba.szzx("bold");
                wba.ztdx(9);
                color = zf.zf(R.color.colorTextU2);
                wba.zfys(color);
                xxbja.j(wba);
                root.j(xxbja);

            }

        });
        viewshow(0);

    }

    public void m20003() {
        gj.jmxc(new Runnable() {

            public void run() {

                String color = "";

                final i.runlibrary.app.v.xdbj root = st.xdbj(R.id.plugin_runtime_view);
                root.scqb();

                i.runlibrary.app.v.xxbj xxbja = st.xxbj();
                xxbja.kg(-1, -2);
                xxbja.fx(1);
                xxbja.dqfs("center");

                final i.runlibrary.app.v.bjk bjk1 = st.bjk();
                bjk1.kg(-1, -2);
                bjk1.tszf("请输入扩展名");
                color = zf.zf(R.color.colorTextU1);
                bjk1.zfys(color);
                color = zf.zf(R.color.colorTextU2);
                bjk1.tszfys(color);
                bjk1.ztdx(13);
                bjk1.nbj("8dp", "8dp", "8dp", "8dp");
                bjk1.xstyh(true);
                xxbja.j(bjk1);
                bjk1.wbj("3dp", "3dp", "3dp", "0dp");

                final i.runlibrary.app.v.bjk bjk2 = st.bjk();
                bjk2.kg(-1, -2);
                bjk2.tszf("请输入扩展ID");
                color = zf.zf(R.color.colorTextU1);
                bjk2.zfys(color);
                color = zf.zf(R.color.colorTextU2);
                bjk2.tszfys(color);
                bjk2.ztdx(13);
                bjk2.nbj("8dp", "8dp", "8dp", "8dp");
                bjk2.xstyh(true);
                xxbja.j(bjk2);
                bjk2.wbj("3dp", "3dp", "3dp", "0dp");

                final i.runlibrary.app.v.bjk bjk3 = st.bjk();
                bjk3.kg(-1, -2);
                bjk3.tszf("请输入授权码");
                bjk3.wblx("textpassword");
                color = zf.zf(R.color.colorTextU1);
                bjk3.zfys(color);
                color = zf.zf(R.color.colorTextU2);
                bjk3.tszfys(color);
                bjk3.ztdx(13);
                bjk3.nbj("8dp", "8dp", "8dp", "8dp");
                bjk3.xstyh(true);
                xxbja.j(bjk3);
                bjk3.wbj("3dp", "3dp", "3dp", "0dp");

                i.runlibrary.app.v.an an1 = st.an();
                an1.kg(-1, -2);
                an1.zf("提交");
                an1.sj.dj(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        gj.xc(new Thread() {

                            public void run() {

                                viewshow(1);

                                ExtensionPluginBridge e14 = new ExtensionPluginBridge(_APPINFO);

                                if (e14.part_buildNewPlu(bjk1.zf(), bjk2.zf(), bjk3.zf())) {

                                    viewshow(0);

                                }

                            }

                        });

                    }

                });
                xxbja.j(an1);
                an1.wbj("3dp", "3dp", "3dp", "0dp");

                i.runlibrary.app.v.wb wba = st.wb();
                wba.kg(-2, -2);
                wba.nbj("3dp", -2, "3dp", -2);
                wba.zf("");
                wba.szzx("bold");
                wba.ztdx(9);
                color = zf.zf(R.color.colorTextU2);
                wba.zfys(color);
                xxbja.j(wba);
                root.j(xxbja);

            }

        });
        viewshow(0);

    }

    public void m20004() {
        gj.jmxc(new Runnable() {

            public void run() {

                String color = "";

                final i.runlibrary.app.v.xdbj root = st.xdbj(R.id.plugin_runtime_view);
                root.scqb();

                i.runlibrary.app.v.xxbj xxbja = st.xxbj();
                xxbja.kg(-1, -2);
                xxbja.fx(1);
                xxbja.dqfs("center");

                final i.runlibrary.app.v.bjk bjk1 = st.bjk();
                bjk1.kg(-1, -2);
                bjk1.tszf("请输入您的邮箱");
                color = zf.zf(R.color.colorTextU1);
                bjk1.zfys(color);
                color = zf.zf(R.color.colorTextU2);
                bjk1.tszfys(color);
                bjk1.ztdx(13);
                bjk1.nbj("8dp", "8dp", "8dp", "8dp");
                bjk1.xstyh(true);
                xxbja.j(bjk1);
                bjk1.wbj("3dp", "3dp", "3dp", "0dp");

                i.runlibrary.app.v.xxbj xxbjb = st.xxbj();
                xxbjb.kg(-1, -2);
                xxbjb.fx(0);
                xxbjb.dqfs("center");

                final i.runlibrary.app.v.bjk bjk2 = st.bjk();
                bjk2.kg(-1, -2);
                bjk2.tszf("请输入验证码");
                color = zf.zf(R.color.colorTextU1);
                bjk2.zfys(color);
                color = zf.zf(R.color.colorTextU2);
                bjk2.tszfys(color);
                bjk2.ztdx(13);
                bjk2.nbj("8dp", "8dp", "8dp", "8dp");
                bjk2.xstyh(true);
                xxbjb.j(bjk2);

                i.runlibrary.app.v.xxbj$xxbjgz bjk2_xxbjgz = (i.runlibrary.app.v.xxbj$xxbjgz) (Object) bjk2.xxbjgz();
                bjk2_xxbjgz.bjqz(1);

                final i.runlibrary.app.v.an an0 = st.an();
                an0.kg(-2, -2);
                an0.zf("获取");
                an0.sj.dj(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        if (zf.dy(an0.zf(), "获取")) {

                            gj.xc(new Thread() {

                                public void run() {

                                    TypheyeServiceBridge easy = new TypheyeServiceBridge(_APPINFO);
                                    easy.register$request$token(bjk1.zf(), an0);

                                }

                            });

                        } else if (zf.dy(an0.zf(), "已发送")) {

                            e1.tsk("提示", "发送验证码成功,请在邮箱查收");

                        }

                    }

                });
                xxbjb.j(an0);
                an0.wbj("3dp", "0dp", "0dp", "0dp");
                xxbja.j(xxbjb);
                xxbjb.wbj("3dp", "3dp", "3dp", "0dp");

                final i.runlibrary.app.v.bjk bjk3 = st.bjk();
                bjk3.kg(-1, -2);
                bjk3.tszf("请输入新密码");
                color = zf.zf(R.color.colorTextU1);
                bjk3.zfys(color);
                color = zf.zf(R.color.colorTextU2);
                bjk3.tszfys(color);
                bjk3.wblx("textpassword");
                bjk3.ztdx(13);
                bjk3.nbj("8dp", "8dp", "8dp", "8dp");
                bjk3.xstyh(true);
                xxbja.j(bjk3);
                bjk3.wbj("3dp", "3dp", "3dp", "0dp");

                final i.runlibrary.app.v.bjk bjk4 = st.bjk();
                bjk4.kg(-1, -2);
                bjk4.tszf("请确认新密码");
                color = zf.zf(R.color.colorTextU1);
                bjk4.zfys(color);
                color = zf.zf(R.color.colorTextU2);
                bjk4.tszfys(color);
                bjk4.wblx("textpassword");
                bjk4.ztdx(13);
                bjk4.nbj("8dp", "8dp", "8dp", "8dp");
                bjk4.xstyh(true);
                xxbja.j(bjk4);
                bjk4.wbj("3dp", "3dp", "3dp", "0dp");

                i.runlibrary.app.v.an an1 = st.an();
                an1.kg(-1, -2);
                an1.zf("提交");
                an1.sj.dj(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        gj.xc(new Thread() {

                            public void run() {

                                viewshow(1);

                                TypheyeServiceBridge easy = new TypheyeServiceBridge(_APPINFO);

                                if (easy.findpwd(bjk1.zf(), bjk2.zf(), bjk3.zf(), bjk4.zf())) {

                                    viewshow(0);

                                }

                            }

                        });

                    }

                });
                xxbja.j(an1);
                an1.wbj("3dp", "3dp", "3dp", "0dp");

                i.runlibrary.app.v.wb wba = st.wb();
                wba.kg(-2, -2);
                wba.nbj("3dp", -2, "3dp", -2);
                wba.zf("");
                wba.szzx("bold");
                wba.ztdx(9);
                color = zf.zf(R.color.colorTextU2);
                wba.zfys(color);
                xxbja.j(wba);
                root.j(xxbja);

            }

        });
        viewshow(0);

    }

    public void m20005() {
        gj.jmxc(new Runnable() {

            public void run() {

                String color = "";

                final i.runlibrary.app.v.xdbj root = st.xdbj(R.id.plugin_runtime_view);
                root.scqb();

                i.runlibrary.app.v.xxbj xxbja = st.xxbj();
                xxbja.kg(-1, -2);
                xxbja.fx(1);
                xxbja.dqfs("center");

                i.runlibrary.app.v.wb wb0 = st.wb();
                wb0.kg(-1, -2);
                wb0.nbj("8dp", "8dp", "8dp", "8dp");
                wb0.zf("发送诊断数据，有助于我们更好的为您解决问题，提升更新质量。\n您是否同意腕管Pro发送以下诊断数据？");
                wb0.szzx("bold");
                wb0.ztdx(13);
                color = zf.zf(R.color.colorTextU1);
                wb0.zfys(color);
                xxbja.j(wb0);
                wb0.wbj("3dp", "3dp", "3dp", "0dp");

                final i.runlibrary.app.v.dx dx1 = st.dx();
                dx1.kg(-1, -2);
                dx1.nbj("8dp", "8dp", "8dp", "8dp");
                dx1.zf("允许发送腕管Pro于运行期间产生的日志数据\n*将用于提升用户体验");
                dx1.bj(R.drawable.dxxbj);
                xxbja.j(dx1);
                dx1.wbj("3dp", "3dp", "3dp", "0dp");

                final i.runlibrary.app.v.dx dx2 = st.dx();
                dx2.kg(-1, -2);
                dx2.nbj("8dp", "8dp", "8dp", "8dp");
                dx2.zf("允许发送位置信息服务运行期间产生的定位数据\n*将用于提高定位服务精确度");
                dx2.bj(R.drawable.dxxbj);
                xxbja.j(dx2);
                dx2.wbj("3dp", "3dp", "3dp", "0dp");

                final i.runlibrary.app.v.dx dx3 = st.dx();
                dx3.kg(-1, -2);
                dx3.nbj("8dp", "8dp", "8dp", "8dp");
                dx3.zf("允许发送用户部分隐私数据(包括但不限定于IP地址、IMEI及GPS定位信息等)\n*将用于改善用户体验");
                dx3.bj(R.drawable.dxxbj);
                xxbja.j(dx3);
                dx3.wbj("3dp", "3dp", "3dp", "0dp");

                if (!debug.isShowed()) {

                    dx1.xzzt(true);
                    dx2.xzzt(true);
                    dx3.xzzt(false);

                } else {

                    if (debug.getDebugState("1")) {

                        dx1.xzzt(true);

                    }

                    if (debug.getDebugState("2")) {

                        dx2.xzzt(true);

                    }

                    if (debug.getDebugState("3")) {

                        dx3.xzzt(true);

                    }

                }

                i.runlibrary.app.v.an an0 = st.an();
                an0.kg(-1, -2);
                an0.zf("同意发送已选诊断数据");
                an0.sj.dj(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        String allowed = "";

                        boolean dx_1 = dx1.xzzt();

                        boolean dx_2 = dx2.xzzt();

                        boolean dx_3 = dx3.xzzt();

                        if (dx_1 || dx_2 || dx_3) {

                            if (dx_1) {

                                debug.setDebugState("1", true);
                                allowed = allowed + "\n*腕管Pro于运行期间产生的日志数据";

                            }

                            if (dx_2) {

                                debug.setDebugState("2", true);
                                allowed = allowed + "\n*位置信息服务运行期间产生的定位数据";

                            }

                            if (dx_3) {

                                debug.setDebugState("3", true);
                                allowed = allowed + "\n*用户部分隐私数据";

                            }

                            if (!dx_1) {

                                debug.setDebugState("1", false);

                            }

                            if (!dx_2) {

                                debug.setDebugState("2", false);

                            }

                            if (!dx_3) {

                                debug.setDebugState("3", false);

                            }

                        } else {

                            debug.setDebugState("1", false);
                            debug.setDebugState("2", false);
                            debug.setDebugState("3", false);
                            allowed = "\n（未允许发送任何诊断数据）";

                        }
                        debug.showed();
                        e1.tsk("提示", "已允许发送以下诊断数据：" + allowed);
                        gj.gb();

                    }

                });
                xxbja.j(an0);
                an0.wbj("3dp", "3dp", "3dp", "0dp");

                i.runlibrary.app.v.an an1 = st.an();
                an1.kg(-1, -2);
                an1.zf("拒绝发送所有诊断数据");
                an1.sj.dj(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        debug.setDebugState("1", false);
                        debug.setDebugState("2", false);
                        debug.setDebugState("3", false);
                        debug.showed();
                        e1.tsk("提示", "已拒绝发送所有诊断数据");
                        gj.gb();

                    }

                });
                xxbja.j(an1);
                an1.wbj("3dp", "3dp", "3dp", "0dp");

                i.runlibrary.app.v.wb wba = st.wb();
                wba.kg(-2, -2);
                wba.nbj("3dp", -2, "3dp", -2);
                wba.zf("");
                wba.szzx("bold");
                wba.ztdx(9);
                color = zf.zf(R.color.colorTextU2);
                wba.zfys(color);
                xxbja.j(wba);
                root.j(xxbja);

            }

        });
        viewshow(0);

    }

    public void m20006() {
        gj.jmxc(new Runnable() {

            public void run() {

                String color = "";

                final i.runlibrary.app.v.xdbj root = st.xdbj(R.id.plugin_runtime_view);
                root.scqb();

                i.runlibrary.app.v.xxbj xxbja = st.xxbj();
                xxbja.kg(-1, -2);
                xxbja.fx(1);
                xxbja.dqfs("center");

                i.runlibrary.app.v.wb wb0 = st.wb();
                wb0.kg(-1, -2);
                wb0.nbj("8dp", "8dp", "8dp", "8dp");
                wb0.zf("腕管Pro - 配置工具\n快捷备份/恢复应用配置\n\n*重要说明\n1.使用此功能需要登录腕管Pro账户；\n2.同一备份文件仅限在登录同账户下的腕管Pro使用；\n3.若设备存储存在多个备份文件，将导入最新的备份文件完成备份恢复。");
                wb0.szzx("bold");
                wb0.ztdx(13);
                color = zf.zf(R.color.colorTextU1);
                wb0.zfys(color);
                xxbja.j(wb0);
                wb0.wbj("3dp", "3dp", "3dp", "0dp");

                i.runlibrary.app.v.an an0 = st.an();
                an0.kg(-1, -2);
                an0.zf("导出备份设置");
                an0.sj.dj(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        gj.xc(new Thread() {

                            public void run() {

                                viewshow(1);
                                gj.zt(500);

                                AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);
                                e1.app_backup();
                                viewshow(0);

                            }

                        });

                    }

                });
                xxbja.j(an0);
                an0.wbj("3dp", "3dp", "3dp", "0dp");

                i.runlibrary.app.v.an an1 = st.an();
                an1.kg(-1, -2);
                an1.zf("导入备份设置");
                an1.sj.dj(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        gj.xc(new Thread() {

                            public void run() {

                                viewshow(1);
                                gj.zt(500);

                                AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

                                if (!e1.app_gorecovery()) {

                                    viewshow(0);

                                }

                            }

                        });

                    }

                });
                xxbja.j(an1);
                an1.wbj("3dp", "3dp", "3dp", "0dp");

                i.runlibrary.app.v.an an2 = st.an();
                an2.kg(-1, -2);
                an2.zf("使用云备份");
                an2.sj.dj(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        gj.tz(CloudServiceActivity.class);
                        gj.gb();

                    }

                });
                xxbja.j(an2);
                an2.wbj("3dp", "3dp", "3dp", "0dp");

                i.runlibrary.app.v.wb wba = st.wb();
                wba.kg(-2, -2);
                wba.nbj("3dp", -2, "3dp", -2);
                wba.zf("");
                wba.szzx("bold");
                wba.ztdx(9);
                color = zf.zf(R.color.colorTextU2);
                wba.zfys(color);
                xxbja.j(wba);
                root.j(xxbja);

            }

        });
        viewshow(0);

    }

    public void m20007() {
        gj.jmxc(new Runnable() {

            public void run() {

                String color = "";

                final i.runlibrary.app.v.xdbj root = st.xdbj(R.id.plugin_runtime_view);
                root.scqb();

                i.runlibrary.app.v.xxbj xxbja = st.xxbj();
                xxbja.kg(-1, -2);
                xxbja.fx(1);
                xxbja.dqfs("center");

                final i.runlibrary.app.v.bjk bjk1 = st.bjk();
                bjk1.kg(-1, -2);
                bjk1.tszf("请输入授权码");
                color = zf.zf(R.color.colorTextU1);
                bjk1.zfys(color);
                color = zf.zf(R.color.colorTextU2);
                bjk1.tszfys(color);
                bjk1.ztdx(13);
                bjk1.nbj("8dp", "8dp", "8dp", "8dp");
                bjk1.xstyh(true);
                xxbja.j(bjk1);
                bjk1.wbj("3dp", "3dp", "3dp", "0dp");

                i.runlibrary.app.v.an an1 = st.an();
                an1.kg(-1, -2);
                an1.zf("登录");
                an1.sj.dj(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        final String authurl = "https://auth.sayqz.com";

                        final String cookiepath = AppPaths.appPath("data/plugin/tunefree/cookie");

                        if (!state_login_base) {

                            state_login_base = true;

                            final String code2 = bjk1.zf();
                            gj.xc(new Thread() {

                                public void run() {

                                    viewshow(1);

                                    String code = "";

                                    String hqto = authurl + "/?path=info&code=" + code2;

                                    String back = wl.hq(hqto, null, "utf-8", null, true, null, 5000, 5000, null);

                                    if (zf.dy(back, "") || zf.dy(back, null)) {

                                    } else {

                                        code = e1.readJson(back, "code");

                                    }

                                    if (zf.dy(back, "") || zf.dy(back, null)) {

                                        final String msg = "请求异常，请检查网络连接";
                                        e1.tsk("提示", msg);
                                        viewshow(0);

                                    } else {

                                        if (zf.dy(code, "200")) {

                                            String mcu = e1.readJson(back, "data");
                                            mcu = e1.readJson(mcu, "netease_cookie");
                                            mcu = mcu + ";";
                                            wj.xrwb(cookiepath, mcu);

                                            final String msg = "登录成功";
                                            e1.tsk("提示", msg);
                                            gj.gb();

                                        } else if (zf.dy(code, "400")) {

                                            final String msg = e1.readJson(back, "message");
                                            e1.tsk("提示", msg);
                                            viewshow(0);

                                        } else {

                                            final String msg = "请求异常，请检查网络连接";
                                            e1.tsk("提示", msg);
                                            viewshow(0);

                                        }

                                    }
                                    state_login_base = false;

                                }

                            });

                        } else {

                            final String msg = "操作过于频繁，请稍后重试";
                            e1.tsk("提示", msg);

                        }

                    }

                });
                xxbja.j(an1);
                an1.wbj("3dp", "3dp", "3dp", "0dp");

                i.runlibrary.app.v.wb wba = st.wb();
                wba.kg(-2, -2);
                wba.nbj("3dp", -2, "3dp", -2);
                wba.zf("");
                wba.szzx("bold");
                wba.ztdx(9);
                color = zf.zf(R.color.colorTextU2);
                wba.zfys(color);
                xxbja.j(wba);
                root.j(xxbja);

            }

        });
        viewshow(0);

    }

    public void csh() {
        gj.jmxc(new Runnable() {

            public void run() {

                String color = "";

                final i.runlibrary.app.v.xdbj root = st.xdbj(R.id.plugin_runtime_view);
                root.scqb();

                i.runlibrary.app.v.xxbj xxbja = st.xxbj();
                xxbja.kg(-1, -2);
                xxbja.fx(1);
                xxbja.dqfs("center");

                i.runlibrary.app.v.tx tx1 = st.tx();
                tx1.kg(-2, -2);
                tx1.nbj(0, 20, 0, 0);
                tx1.tx(R.mipmap.i);
                xxbja.j(tx1);

                i.runlibrary.app.v.wb wba = st.wb();
                wba.kg(-2, -2);
                wba.nbj(20, 20, 20, 10);
                wba.zf(title);
                wba.ztdx(16);
                color = zf.zf(R.color.colorTextU1);
                wba.zfys(color);
                xxbja.j(wba);

                i.runlibrary.app.v.wb wbb = st.wb();
                wbb.kg(-2, -2);
                wbb.nbj(20, 10, 20, 20);
                wbb.zf("扩展插件ID: " + id);
                wbb.ztdx(13);
                color = zf.zf(R.color.colorTextU2);
                wbb.zfys(color);
                xxbja.j(wbb);

                i.runlibrary.app.v.an an1 = st.an();
                an1.kg(-2, -2);
                an1.zf("打开指令框");
                an1.sj.dj(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        viewshow(2);

                    }

                });
                xxbja.j(an1);
                root.j(xxbja);

            }

        });
        viewshow(0);

    }

    public void csh_e() {
        gj.jmxc(new Runnable() {

            public void run() {

                String color = "";

                final i.runlibrary.app.v.xdbj root = st.xdbj(R.id.plugin_runtime_view);
                root.scqb();

                i.runlibrary.app.v.xxbj xxbja = st.xxbj();
                xxbja.kg(-1, -2);
                xxbja.fx(1);
                xxbja.dqfs("center");

                i.runlibrary.app.v.tx tx1 = st.tx();
                tx1.kg(-2, -2);
                tx1.nbj(0, 20, 0, 0);
                tx1.tx(R.mipmap.i);
                xxbja.j(tx1);

                i.runlibrary.app.v.wb wba = st.wb();
                wba.kg(-2, -2);
                wba.nbj(20, 20, 20, 20);
                wba.zf("欢迎使用AwgRunable");
                wba.ztdx(16);
                color = zf.zf(R.color.colorTextU1);
                wba.zfys(color);
                xxbja.j(wba);

                i.runlibrary.app.v.an an0 = st.an();
                an0.kg(-2, -2);
                an0.zf("查看帮助文档");
                an0.sj.dj(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        e1.tsk("提示", "暂未开放");

                    }

                });
                xxbja.j(an0);

                i.runlibrary.app.v.wb wbb = st.wb();
                wbb.kg(-2, -2);
                wbb.nbj(20, 10, 20, 20);
                wbb.zf("示例控件");
                wbb.ztdx(13);
                color = zf.zf(R.color.colorTextTrue);
                wbb.zfys(color);
                xxbja.j(wbb);

                i.runlibrary.app.v.an an1 = st.an();
                an1.kg(-2, -2);
                an1.zf("打开指令框");
                an1.sj.dj(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        viewshow(2);

                    }

                });
                xxbja.j(an1);

                i.runlibrary.app.v.an an2 = st.an();
                an2.kg(-2, -2);
                an2.zf("显隐标题栏");
                an2.sj.dj(new View.OnClickListener() {

                    public void onClick(View _vw) {

                        if (st.wb(R.id.title_bar).kjd() == 0) {

                            gj.jmxc(new Runnable() {

                                public void run() {

                                    st.wb(R.id.title_bar).kjd(8);

                                }

                            });

                        } else {

                            gj.jmxc(new Runnable() {

                                public void run() {

                                    st.wb(R.id.title_bar).kjd(0);

                                }

                            });

                        }

                    }

                });
                xxbja.j(an2);
                root.j(xxbja);

            }

        });
        viewshow(0);

    }

    public void viewshow(int a) {

        final int i = a;

        final i.runlibrary.app.v.xdbj root = st.xdbj(R.id.plugin_runtime_view);

        final i.runlibrary.app.v.xxbj load = st.xxbj(R.id.xxbj3);

        final i.runlibrary.app.v.xxbj add = st.xxbj(R.id.xxbj4);

        final i.runlibrary.app.v.wb tab = st.wb(R.id.title_bar);

        final i.runlibrary.app.v.bjk cmd = st.bjk(R.id.bjk1);

        final i.runlibrary.app.v.qtgd qtgd1 = st.qtgd(R.id.qtgd1);
        gj.jmxc(new Runnable() {

            public void run() {

                if (i == 0) {

                    root.kjd(0);
                    load.kjd(8);
                    add.kjd(8);
                    tab.zf(titleback);
                    tab.kjd(0);
                    cmd.zf("");

                } else if (i == 1) {

                    root.kjd(8);
                    load.kjd(0);
                    add.kjd(8);
                    tab.zf(titleback2);
                    tab.kjd(0);
                    cmd.zf("");

                } else if (i == 2) {

                    root.kjd(8);
                    load.kjd(8);
                    add.kjd(0);
                    tab.zf("指令框");
                    tab.kjd(0);
                    cmd.zf("");

                }
                qtgd1.v.requestFocus();

            }

        });

    }

    public void cmd() {

        String c = st.bjk(R.id.bjk1).zf();
        c = zf.qctwkg(c);

        if (zf.dy(c, "")) {

            e1.tsk("提示", "指令不能为空");

        } else {

            if (zf.dy(c, "exit")) {

                viewshow(0);
                gj.gb();

            } else if (zf.dy(c, "help")) {

                viewshow(1);
                csh_e();

            } else if (zf.dy(c, "run")) {

                jz();

            } else {

                e1.tsk("提示", "未找到该指令");

            }

        }

    }
}
