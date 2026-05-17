package open.cn.awg.pro;

import i.app.iActivity;
import java.lang.*;

public class c19 extends iActivity {

private void $_onClick_aaeebbd136(android.view.View vw){

i.runlibrary.app.sj$lb lb=sj.lb(vw);

java.lang.Object a=lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

java.lang.Object b=lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

open.cn.awg.pro.e11  e11=new e11(_APPINFO);

open.cn.awg.pro.e1  e1=new e1(_APPINFO);
e11.setup_pluginlist();

if(e11.findid_pluginlist(a)!=0){


java.lang.String cachehd="/data/user/0/open.cn.awg.pro/data/cjlb/lbx_id";
wj.xrwb(cachehd,a);

java.lang.String cachehd2="/data/user/0/open.cn.awg.pro/data/cjlb/lbx_title";
wj.xrwb(cachehd2,b);

open.cn.awg.pro.e14  e14=new e14(_APPINFO);

java.lang.String set1="/data/user/0/open.cn.awg.pro/settings/f19/set1.inf";

if(zf.dy(wj.dqwb(set1),"true")){


if(e14.newG(Integer.parseInt(String.valueOf(a)))==true){

gj.tz(m2.class);


}


}
else{

gj.tz(m2.class);


}


}
else{

e1.tsk("提示","该扩展插件不存在,请刷新列表后重试");


}

}

private boolean $_onLongClick_aaeebbd136(android.view.View vw){

i.runlibrary.app.sj$lb lb=sj.lb(vw);

java.lang.Object a=lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

java.lang.Object b=lb.lbcfsj(open.cn.awg.pro.R.id.wb1);

open.cn.awg.pro.e14  e14=new e14(_APPINFO);
e14.cjlbcd(2);

java.lang.String cachehd="/data/user/0/open.cn.awg.pro/data/cjlb/lbx_id";
wj.xrwb(cachehd,a);

java.lang.String cachehd2="/data/user/0/open.cn.awg.pro/data/cjlb/lbx_title";
wj.xrwb(cachehd2,b);
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

android.widget.RelativeLayout df0f5f2ce0 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.c19_v);

android.widget.LinearLayout e1433e26d6 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj1);

android.widget.RelativeLayout aaeebbd136 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj1);
aaeebbd136.setOnClickListener($_on_setOnClickListener_aaeebbd136);
aaeebbd136.setOnLongClickListener($_on_setOnLongClickListener_aaeebbd136);

__layoutIsLoaded(ay, vw);
}

    public final c19 lei = this, 类 = this;
}
