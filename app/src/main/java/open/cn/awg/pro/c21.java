package open.cn.awg.pro;

import i.app.iActivity;
import java.lang.*;

public class c21 extends iActivity {

private boolean $_onLongClick_aaeebbd136(android.view.View vw){

open.cn.awg.pro.e1  e1=new e1(_APPINFO);

i.runlibrary.app.sj$lb lb=sj.lb(vw);

java.lang.Object a=lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

java.lang.Object b=lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

java.lang.Object c=lb.lbcfsj(open.cn.awg.pro.R.id.wb3);
e1.tsk("详细信息","[时间]\n"+c+"\n\n[消息]\n"+a);
return true;

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_aaeebbd136=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_aaeebbd136(vw);
}

};

public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw){

android.widget.LinearLayout e1433e26d6 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj1);

android.widget.TextView i0d2d4d9236 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb3);

android.widget.TextView ff56a8e7f6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb1);

android.widget.RelativeLayout aaeebbd136 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj1);
aaeebbd136.setOnLongClickListener($_on_setOnLongClickListener_aaeebbd136);

android.widget.RelativeLayout c93275c379 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.c21_v);

__layoutIsLoaded(ay, vw);
}

    public final c21 lei = this, 类 = this;
}
