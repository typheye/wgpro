/**
 ******************************************************************************
 * @file    LocalDatabaseBridge.java
 * @author  Typheye
 * @brief   Local database bridge for chat, files, and plugins.
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
package open.cn.awg.pro.data;

import i.app.iClass;

import open.cn.awg.pro.core.AppPaths;

public class LocalDatabaseBridge extends iClass {

    public static String set17 = AppPaths.appPath("settings/db/talking.db");

    public static i.runlibrary.app.sj$sjk sjk;

    public static String set27 = AppPaths.appPath("settings/db/file.db");

    public static i.runlibrary.app.sj$sjk sjk2;

    public static String set47 = AppPaths.appPath("settings/db/plugin.db");

    public static i.runlibrary.app.sj$sjk sjk3;
    public final LocalDatabaseBridge lei = this, 类 = this;

    public LocalDatabaseBridge(i.runlibrary.app.AppInfo _APPINFO) {
        super(_APPINFO);

    }

    public void setup_talklist() {
        sjk = (i.runlibrary.app.sj$sjk) (Object) sj.sjk(set17);

        if (!sjk.czsjb("talklist")) {

            String table = "_id integer primary key,uid interger, name text,utext text";

            boolean sjk8 = sjk.cjsjb("talklist", table);

        }

    }

    public boolean add_talklist(Object a, Object b, Object c) {

        boolean fhz = false;

        i.runlibrary.app.sj$cxsj back = (i.runlibrary.app.sj$cxsj) (Object) sjk.cxsj("talklist", "uid", "uid=" + a);
        back.dyh();

        String fhs = back.sj(0);

        if (zf.dy(fhs, null)) {

            String table = "uid,name,utext";

            String data = a + ",'" + b + "','" + c + "'";

            boolean sjk11 = sjk.cjsj("talklist", table, data);
            fhz = true;

        } else {

            fhz = false;

        }
        return fhz;

    }

    public boolean update_talklist(Object a, Object b, Object c) {

        boolean fhz = sjk.gxsj("talklist", b + "='" + c + "'", "_id=" + a);
        return fhz;

    }

    public boolean delete_talklist(Object a) {

        boolean fhz = sjk.scsj("talklist", "_id=" + a);
        return fhz;

    }

    public int findid_talklist(Object a) {

        int fhz = 0;

        i.runlibrary.app.sj$cxsj back = (i.runlibrary.app.sj$cxsj) (Object) sjk.cxsj("talklist", "_id,uid", "uid=" + a);
        back.dyh();

        String fhs = back.sj(0);

        if (zf.dy(fhs, null)) {

        } else {

            fhz = Integer.parseInt(fhs);

        }
        return fhz;

    }

    public void print_talklist() {

        String table = "_id,uid,name,utext";

        Object sqlx = null;

        i.runlibrary.app.sj$cxsj sjk15 = (i.runlibrary.app.sj$cxsj) (Object) sjk.cxsj("talklist", table, sqlx);

        while (sjk15.xyh()) {

            gj.sc(":" + sjk15.sj(0) + ", " + sjk15.sj(1) + ", " + sjk15.sj(2) + ", " + sjk15.sj(3));

        }

    }

    public void setup_filelist() {
        sjk2 = (i.runlibrary.app.sj$sjk) (Object) sj.sjk(set27);

        if (!sjk2.czsjb("filelist")) {

            String table = "_id integer primary key, path text,value interger";

            boolean sjk8 = sjk2.cjsjb("filelist", table);

        }

    }

    public boolean update_filelist(Object a, int b) {

        boolean fhz = false;

        i.runlibrary.app.sj$cxsj back = (i.runlibrary.app.sj$cxsj) (Object) sjk2.cxsj("filelist", "path", "path='" + a + "'");
        back.dyh();

        String fhs = back.sj(0);

        if (zf.dy(fhs, null)) {

            String table = "path,value";

            String data = "'" + a + "'," + b;
            fhz = sjk2.cjsj("filelist", table, data);

        } else {

            i.runlibrary.app.sj$cxsj back2 = (i.runlibrary.app.sj$cxsj) (Object) sjk2.cxsj("filelist", "_id,path", "path='" + a + "'");
            back2.dyh();

            int fhx = Integer.parseInt(back2.sj(0));
            fhz = sjk2.gxsj("filelist", "value=" + b, "_id=" + fhx);

        }
        return fhz;

    }

    public int get_filelist(Object a) {

        int v = 0;

        i.runlibrary.app.sj$cxsj back = (i.runlibrary.app.sj$cxsj) (Object) sjk2.cxsj("filelist", "value,path", "path='" + a + "'");
        back.dyh();

        String b = back.sj(0);

        if (zf.dy(b, null)) {

        } else {

            v = Integer.parseInt(b);

        }
        return v;

    }

    public void setup_pluginlist() {
        sjk3 = (i.runlibrary.app.sj$sjk) (Object) sj.sjk(set47);

        if (!sjk3.czsjb("pluginlist")) {

            String table = "_id integer primary key,uid interger, name text,utext text";

            boolean sjk8 = sjk3.cjsjb("pluginlist", table);

        }

    }

    public boolean add_pluginlist(Object a, Object b, Object c) {

        boolean fhz = false;

        i.runlibrary.app.sj$cxsj back = (i.runlibrary.app.sj$cxsj) (Object) sjk3.cxsj("pluginlist", "uid", "uid=" + a);
        back.dyh();

        String fhs = back.sj(0);

        if (zf.dy(fhs, null)) {

            String table = "uid,name,utext";

            String data = a + ",'" + b + "','" + c + "'";

            boolean sjk11 = sjk3.cjsj("pluginlist", table, data);
            fhz = true;

        } else {

            fhz = false;

        }
        return fhz;

    }

    public boolean update_pluginlist(Object a, Object b, Object c) {

        boolean fhz = sjk3.gxsj("pluginlist", b + "='" + c + "'", "_id=" + a);
        return fhz;

    }

    public boolean delete_pluginlist(Object a) {

        boolean fhz = sjk3.scsj("pluginlist", "_id=" + a);
        return fhz;

    }

    public int findid_pluginlist(Object a) {

        int fhz = 0;

        i.runlibrary.app.sj$cxsj back = (i.runlibrary.app.sj$cxsj) (Object) sjk3.cxsj("pluginlist", "_id,uid", "uid=" + a);
        back.dyh();

        String fhs = back.sj(0);

        if (zf.dy(fhs, null)) {

        } else {

            fhz = Integer.parseInt(fhs);

        }
        return fhz;

    }

    public void print_pluginlist() {

        String table = "_id,uid,name,utext";

        Object sqlx = null;

        i.runlibrary.app.sj$cxsj sjk15 = (i.runlibrary.app.sj$cxsj) (Object) sjk3.cxsj("pluginlist", table, sqlx);

        while (sjk15.xyh()) {

            gj.sc(":" + sjk15.sj(0) + ", " + sjk15.sj(1) + ", " + sjk15.sj(2) + ", " + sjk15.sj(3));

        }

    }
}
