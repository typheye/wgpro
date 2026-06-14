/**
 ******************************************************************************
 * @file    FileListItemActivity.java
 * @author  Typheye
 * @brief   Handles file-list item click and long-press operations.
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
package open.cn.awg.pro.ui.list;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;

import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class FileListItemActivity extends BaseAwgActivity {

    public final FileListItemActivity lei = this, 类 = this;
    private final View.OnLongClickListener xdbj1LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onXdbj1LongClick(vw);
        }

    };
    private final View.OnClickListener xdbj1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onXdbj1Click(vw);
        }

    };

    private void onXdbj1Click(View vw) {

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) sj.lb(vw);

        Object lj = lb.lbcfsj(R.id.wb2);

        Object text = lb.lbcfsj(R.id.wb3);
        e1.fileTouch(text, lj);

    }

    private boolean onXdbj1LongClick(View vw) {

        AppRuntimeBridge e1 = new AppRuntimeBridge(_APPINFO);

        try {

            i.runlibrary.app.sj$lb lb = (i.runlibrary.app.sj$lb) sj.lb(vw);

            Object bts = lb.lbcfsj(R.id.wb1);

            Object lj = lb.lbcfsj(R.id.wb2);

            String[] fhValue = e1.fileinfo("%", lj);

            String ljs = fhValue[2];

            Object text = lb.lbcfsj(R.id.wb3);

            if (zf.dy(text, "返回上层目录")) {

                e1.cdx(3, String.valueOf(bts), String.valueOf(lj), ljs);

            } else {

                int ifs = wj.lx(lj);

                if (ifs == 1) {

                    e1.cdx(2, String.valueOf(bts), String.valueOf(lj), ljs);

                } else if (ifs == 2) {

                    e1.cdx(1, String.valueOf(bts), String.valueOf(lj), ljs);

                }

            }

        } catch (Throwable e) {

            e1.upload_error(e, "c2.*");

        }
        return true;

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        RelativeLayout aaeebbd136 = (RelativeLayout) findViewById(ay, vw, R.id.xdbj1);
        aaeebbd136.setOnClickListener(xdbj1ClickListener);
        aaeebbd136.setOnLongClickListener(xdbj1LongClickListener);

        RelativeLayout i9538cb665d = (RelativeLayout) findViewById(ay, vw, R.id.file_list_item_root);

        __layoutIsLoaded(ay, vw);
    }
}
