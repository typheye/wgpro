package open.cn.awg.pro;

import i.app.iActivity;
 import android.content.Intent;import java.lang.*;

public class c3 extends iActivity {

private void $_onClick_e1433e26d6(android.view.View vw){

open.cn.awg.pro.e1  e1=new e1(_APPINFO);

try{


i.runlibrary.app.sj$lb lb=sj.lb(vw);

java.lang.Object l=lb.lbcfsj(open.cn.awg.pro.R.id.wb2);

if(zf.dy(l,"1")){

gj.tz(d7.class);


}

else if(zf.dy(l,"5")){

gj.tz(l6.class);


}

else if(zf.dy(l,"2")){

gj.tz(f2.class);


}

else if(zf.dy(l,"3")){


i.runlibrary.app.gj$dh dh=gj.dh();

final i.runlibrary.app.gj$Animationx jbtm=dh.jbtm(0,1);
jbtm.cxsc(300);
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.xdbj(open.cn.awg.pro.R.id.TCxdbj).kjd(0);
st.xdbj(open.cn.awg.pro.R.id.TCxdbj).dh(jbtm);


}

});


}

else if(zf.dy(l,"4")){

gj.tz(a3.class);
gj.gb();


}

else if(zf.dy(l,"6")){

gj.tz(c1.class);
gj.gb();


}

else if(zf.dy(l,"8")){

gj.tz(f4.class);


}

else if(zf.dy(l,"9")){


if(e1.awgwl_state()){

gj.tz(i1.class);


}
else{

gj.tz(i4.class);


}


}

else if(zf.dy(l,"10")){

gj.tz(k1.class);


}

else if(zf.dy(l,"11")){

gj.tz(j6.class);


}

else if(zf.dy(l,"12")){

gj.tz(j8.class);


}

else if(zf.dy(l,"13")){

gj.tz(l1.class);


}

else if(zf.dy(l,"14")){

gj.xszm();


}

else if(zf.dy(l,"20")){

gj.tz(m1.class);


}

else if(zf.dy(l,"31")){

gj.jmxc(new java.lang.Runnable(){

public void run(){


try{

st.wb(open.cn.awg.pro.R.id.wb_main_kjfs_gbcd).v.performClick();


}catch(java.lang.Throwable __$_e__){



}


}

});


}
else{

e1.tsk("提示","未找到该选项");


}


}catch(java.lang.Throwable e){

e1.upload_error(e,"c3.*");


}

}

private boolean $_onLongClick_e1433e26d6(android.view.View vw){

final open.cn.awg.pro.e1  e1=new e1(_APPINFO);

i.runlibrary.app.sj$lb lb=sj.lb(vw);

java.lang.Object te=lb.lbcfsj(open.cn.awg.pro.R.id.wb1);
e1.tsk("详细信息",te);
return true;

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_e1433e26d6=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_e1433e26d6(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_e1433e26d6=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_e1433e26d6(vw);
}

};

public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw){

android.widget.LinearLayout e1433e26d6 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj1);
e1433e26d6.setOnClickListener($_on_setOnClickListener_e1433e26d6);
e1433e26d6.setOnLongClickListener($_on_setOnLongClickListener_e1433e26d6);

__layoutIsLoaded(ay, vw);
}

    public final c3 lei = this, 类 = this;
}
