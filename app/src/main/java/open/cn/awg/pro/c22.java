package open.cn.awg.pro;

import i.app.iActivity;
import java.lang.*;

public class c22 extends iActivity {

private void $_onClick_i2b32d98dc8(android.view.View vw){

open.cn.awg.pro.e1  e1=new e1(_APPINFO);

i.runlibrary.app.sj$lb lb=sj.lb(vw);

final java.lang.Object a=lb.lbcfsj(-1);

final java.lang.Object b=lb.lbcfsj(-2);

try{

gj.jmxc(new java.lang.Runnable(){

public void run(){

xt.dkyy(a,b);


}

});


}catch(java.lang.Throwable e){

e1.tsk("提示","没有可执行该操作的应用");


}

}

private boolean $_onLongClick_i2b32d98dc8(android.view.View vw){

open.cn.awg.pro.e1  e1=new e1(_APPINFO);

i.runlibrary.app.sj$lb lb=sj.lb(vw);

java.lang.Object l1=lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

java.lang.Object l2=lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

java.lang.Object l=lb.lbcfsj(-1);
e1.tsk("详细信息","[功能名称]\n"+l1+"\n\n[功能简介]\n"+l2);
return true;

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_i2b32d98dc8=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_i2b32d98dc8(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i2b32d98dc8=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i2b32d98dc8(vw);
}

};

public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw){

android.widget.RelativeLayout i2b32d98dc8 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.c22_v);
i2b32d98dc8.setOnClickListener($_on_setOnClickListener_i2b32d98dc8);
i2b32d98dc8.setOnLongClickListener($_on_setOnLongClickListener_i2b32d98dc8);

__layoutIsLoaded(ay, vw);
}

    public final c22 lei = this, 类 = this;
}
