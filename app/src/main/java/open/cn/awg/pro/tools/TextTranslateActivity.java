/**
 ******************************************************************************
 * @file    TextTranslateActivity.java
 * @author  Typheye
 * @brief   Provides text translation utilities.
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

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.InputDeviceCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewConfigurationCompat;

import open.cn.awg.pro.app.AwgProApplication;
import open.cn.awg.pro.core.AppRuntimeBridge;
import open.cn.awg.pro.core.BaseAwgActivity;
import open.cn.awg.pro.R;

public class TextTranslateActivity extends BaseAwgActivity {

    public final TextTranslateActivity lei = this, 类 = this;
    public AppRuntimeBridge e1;
    private final View.OnClickListener tx1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onTx1Click(vw);
        }

    };
    private final View.OnClickListener an2ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn2Click(vw);
        }

    };
    private final View.OnLongClickListener wb1LongClickListener = new View.OnLongClickListener() {

        public boolean onLongClick(View vw) {
            return onWb1LongClick(vw);
        }

    };
    private final View.OnClickListener an1ClickListener = new View.OnClickListener() {

        public void onClick(View vw) {
            onAn1Click(vw);
        }

    };
    private final View.OnTouchListener bjk1TouchListener = new View.OnTouchListener() {

        public boolean onTouch(View vw, MotionEvent me) {
            return onBjk1Touch(vw, me);
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
        setContentView(R.layout.text_translate);
        _$_viewAutomaticSettingEvent();
        AwgProApplication.getInstance().addActivity(lei);

    }

    public void __layoutIsLoaded(Activity ay, View vw) {

        applyStandardScreenMode(R.id.text_translate_root, R.id.xdbj1, R.id.title_bar);

        android.widget.Spinner xlcd1 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(TextTranslateActivity.this.getWindow().getDecorView(), R.id.xlcd1));

        String[] sz = new String[]{"自动检测", "中文翻译英语", "中文翻译日语", "中文翻译韩语", "中文翻译法语", "中文翻译俄语", "中文翻译西班牙语", "英语翻译中文", "日语翻译中文", "韩语翻译中文", "法语翻译中文", "俄语翻译中文", "西班牙语翻译中文"};
        open.cn.awg.pro.core.NativeViewValues.spinner(xlcd1, sz);

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

        androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(TextTranslateActivity.this.getWindow().getDecorView(), R.id.qtgd1));

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
        open.cn.awg.pro.core.NativeActions.finish(TextTranslateActivity.this);

    }

    private boolean onBjk1Touch(View vw, MotionEvent me) {

        if (!vw.isFocused()) {

            open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                public void run() {

                    androidx.core.widget.NestedScrollView qtgd1 = ((androidx.core.widget.NestedScrollView) open.cn.awg.pro.core.NativeViewValues.findView(TextTranslateActivity.this.getWindow().getDecorView(), R.id.qtgd1));
                    qtgd1.requestFocus();

                }

            });

        }
        return false;

    }

    private void onAn1Click(View vw) {

        android.widget.Spinner xlcd1 = ((android.widget.Spinner) open.cn.awg.pro.core.NativeViewValues.findView(TextTranslateActivity.this.getWindow().getDecorView(), R.id.xlcd1));

        String bjk1 = ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(TextTranslateActivity.this.getWindow().getDecorView(), R.id.bjk1)).getText().toString();
        bjk1 = open.cn.awg.pro.core.TextValues.trim(bjk1);

        if (java.util.Objects.equals(bjk1, "")) {

            e1.tsk("提示", "翻译内容不能为空");

        } else {

            Object xl = xlcd1.getSelectedItem();

            String yy = "";

            if (java.util.Objects.equals(xl, "自动检测")) {

                yy = "AUTO";

            } else if (java.util.Objects.equals(xl, "中文翻译英语")) {

                yy = "ZH_CN2EN";

            } else if (java.util.Objects.equals(xl, "中文翻译日语")) {

                yy = "ZH_CN2JA";

            } else if (java.util.Objects.equals(xl, "中文翻译韩语")) {

                yy = "ZH_CN2KR";

            } else if (java.util.Objects.equals(xl, "中文翻译法语")) {

                yy = "ZH_CNFR";

            } else if (java.util.Objects.equals(xl, "中文翻译俄语")) {

                yy = "ZH_CN2RU";

            } else if (java.util.Objects.equals(xl, "中文翻译西班牙语")) {

                yy = "ZH_CN2SP";

            } else if (java.util.Objects.equals(xl, "英语翻译中文")) {

                yy = "EN2ZH_CN";

            } else if (java.util.Objects.equals(xl, "日语翻译中文")) {

                yy = "JA2ZH_CN";

            } else if (java.util.Objects.equals(xl, "韩语翻译中文")) {

                yy = "KR2ZH_CN";

            } else if (java.util.Objects.equals(xl, "法语翻译中文")) {

                yy = "FR2ZH_CN";

            } else if (java.util.Objects.equals(xl, "俄语翻译中文")) {

                yy = "RU2ZH_CN";

            } else if (java.util.Objects.equals(xl, "西班牙语翻译中文")) {

                yy = "SP2ZH_CN";

            }

            final String[] post = new String[]{"inputtext=" + bjk1, "type=" + yy};
            open.cn.awg.pro.core.NativeActions.start(new Thread() {

                public void run() {

                    String url = "http://m.youdao.com/translate";

                    String ok = open.cn.awg.pro.core.NativeHttp.postForm(String.valueOf(url), (String[]) (post), (String) ("utf-8"), (String) (null), java.lang.Boolean.TRUE.equals(true), open.cn.awg.pro.core.NativeHttp.parseHeaderBlock((String) ("User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept=*/*||accept-language=zh-CN")), open.cn.awg.pro.core.TextValues.intValue(20000, -1), open.cn.awg.pro.core.TextValues.intValue(20000, -1), (String) (null)).bodyForHttp200();

                    if (java.util.Objects.equals(ok, null)) {

                        e1.tsk("提示", "翻译失败,请检查网络");

                    } else {

                        String o = open.cn.awg.pro.core.TextValues.slice(ok, "译文", "</ul>");

                        final String oo = open.cn.awg.pro.core.TextValues.slice(o, "<li>", "</li>");
                        open.cn.awg.pro.core.NativeActions.onMain(new Runnable() {

                            public void run() {

                                ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextTranslateActivity.this.getWindow().getDecorView(), R.id.wb1)).setText(String.valueOf("翻译结果:\n" + oo));

                            }

                        });

                    }

                }

            });

        }

    }

    private boolean onWb1LongClick(View vw) {

        android.widget.TextView wb = ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextTranslateActivity.this.getWindow().getDecorView(), R.id.wb1));

        String wb1 = wb.getText().toString();
        wb1 = open.cn.awg.pro.core.TextValues.slice(wb1, "翻译结果:\n", null);
        e1.tsk("详细信息", wb1);
        return true;

    }

    private void onAn2Click(View vw) {
        ((android.widget.TextView) open.cn.awg.pro.core.NativeViewValues.findView(TextTranslateActivity.this.getWindow().getDecorView(), R.id.wb1)).setText(String.valueOf("翻译结果:\n"));
        ((android.widget.EditText) open.cn.awg.pro.core.NativeViewValues.findView(TextTranslateActivity.this.getWindow().getDecorView(), R.id.bjk1)).setText(String.valueOf(""));

    }

    private void onTx1Click(View vw) {
        open.cn.awg.pro.core.NativeActions.finish(TextTranslateActivity.this);

    }

    private void _$_viewAutomaticSettingEvent() {
        _$_viewAutomaticSettingEvent(this, null);
    }

    public void _$_viewAutomaticSettingEvent(Activity ay, View vw) {

        TextView d26d5d7080 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.title_bar);
        d26d5d7080.setOnClickListener(titleBarClickListener);

        EditText i32ee139ba1 = (EditText) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.bjk1);
        i32ee139ba1.setOnTouchListener(bjk1TouchListener);

        Button i171ea34f1a = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an1);
        i171ea34f1a.setOnClickListener(an1ClickListener);

        TextView ff56a8e7f6 = (TextView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.wb1);
        ff56a8e7f6.setOnLongClickListener(wb1LongClickListener);

        Button i0b66a667b1 = (Button) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.an2);
        i0b66a667b1.setOnClickListener(an2ClickListener);

        ImageView i34dc7bf20a = (ImageView) (vw == null ? ay.getWindow().getDecorView() : vw).findViewById(R.id.tx1);
        i34dc7bf20a.setOnClickListener(tx1ClickListener);

        __layoutIsLoaded(ay, vw);
    }
}
