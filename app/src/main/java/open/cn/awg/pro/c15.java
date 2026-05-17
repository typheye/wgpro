package open.cn.awg.pro;

import i.app.iActivity;
import java.lang.*;

public class c15 extends iActivity {

private void $_onClick_aaeebbd136(android.view.View vw){

open.cn.awg.pro.e1  e1=new e1(_APPINFO);

open.cn.awg.pro.e12  e12=new e12(_APPINFO);

i.runlibrary.app.sj$lb lb=sj.lb(vw);

java.lang.Object a1=lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

java.lang.Object a2=lb.lbcfsj(open.cn.awg.pro.R.id.wb3);

java.lang.Object a3=lb.lbcfsj(open.cn.awg.pro.R.id.wb2);
e12.loadMusic(a3,a1+"-"+a2);

}

private boolean $_onLongClick_aaeebbd136(android.view.View vw){

open.cn.awg.pro.e1  e1=new e1(_APPINFO);

i.runlibrary.app.sj$lb lb=sj.lb(vw);

java.lang.Object a1=lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

java.lang.Object a2=lb.lbcfsj(open.cn.awg.pro.R.id.wb3);

java.lang.Object a3=lb.lbcfsj(open.cn.awg.pro.R.id.wb2);
e1.tsk("详细信息","[歌名]\n"+a1+"\n\n[歌手]\n"+a2+"\n\n[歌曲ID]\n"+a3);
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
private android.view.View.OnClickListener $_on_setOnClickListener_aaeebbd136=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_aaeebbd136(vw);
}

};

public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw){

android.widget.RelativeLayout aaeebbd136 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj1);
aaeebbd136.setOnClickListener($_on_setOnClickListener_aaeebbd136);
aaeebbd136.setOnLongClickListener($_on_setOnLongClickListener_aaeebbd136);

android.widget.RelativeLayout i4fe9eaa1ba = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.c15_v);

__layoutIsLoaded(ay, vw);
}

    public final c15 lei = this, 类 = this;
}
