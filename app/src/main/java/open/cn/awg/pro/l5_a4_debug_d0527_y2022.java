package open.cn.awg.pro;

import i.app.iActivity;
 import android.view.Window; import com.ypz.bangscreentools.BangScreenTools; import java.io.File; import android.view.View;import java.lang.*;

public class l5_a4_debug_d0527_y2022 extends iActivity {

public void onCreate(android.os.Bundle be){
super.onCreate(be);
setContentView(open.cn.awg.pro.R.layout.l5_a4_debug_d0527_y2022);
_$_viewAutomaticSettingEvent();
open.cn.awg.pro.e6.getInstance().addActivity(lei);
csh();
show(0);

}

public void __layoutIsLoaded(android.app.Activity ay, android.view.View vw){

if(zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f8/set4.inf"),"true")){



}

i.runlibrary.app.xt$pm pm=xt.pm();

if(zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4s.inf"),"false")){


if(zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4.inf"),"true")){

getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES);


}
else{

getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO);


}


}

final java.lang.String a2="/data/user/0/open.cn.awg.pro/settings/a3.inf";

java.lang.String a=wj.dqwb(a2);

final int u=Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));

if(zf.dy(a,"4")){

st.xdbj(open.cn.awg.pro.R.id.l5_a4_debug_d0527_y2022_v).shxtck(true);

java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{

st.xdbj(open.cn.awg.pro.R.id.l5_a4_debug_d0527_y2022_v).shxtck(false);

        
    Window window = lei.getWindow();
    BangScreenTools.getBangScreenTools().fullscreen(window, lei);
    BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);
  


}

if(zf.dy(a,"1")){

st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u,0,u,50);

i.runlibrary.app.v.wb wtab=st.wb(open.cn.awg.pro.R.id.Tab);
wtab.nbj(0,"15dp",0,"3dp");
wtab.dqfs("center");


}

else if(zf.dy(a,"2")){


java.lang.String a001="/data/user/0/open.cn.awg.pro/settings/dpi.inf";

if(wj.cz(a001)==true||zf.dy(wj.dqwb(a001),"true")){


i.runlibrary.app.v.wb wtab=st.wb(open.cn.awg.pro.R.id.Tab);
wtab.nbj(0,"3dp",0,"3dp");
wtab.dqfs("center");


}
else{


i.runlibrary.app.v.wb wtab=st.wb(open.cn.awg.pro.R.id.Tab);
wtab.nbj(0,"9dp",0,"9dp");
wtab.dqfs("center");


}
st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0,0,0,0);


}

else if(zf.dy(a,"3")){

st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(u,0,u,50);

i.runlibrary.app.v.wb wtab=st.wb(open.cn.awg.pro.R.id.Tab);
wtab.nbj(0,"15dp",0,"3dp");
wtab.dqfs("center");


}

else if(zf.dy(a,"4")){

st.xdbj(open.cn.awg.pro.R.id.xdbj1).nbj(0,0,0,0);


}

}

public java.lang.String um="";

public java.io.File f;

public open.cn.awg.pro.e1  e1=new e1(_APPINFO);

public open.cn.awg.pro.e12  e12=new e12(_APPINFO);

public void show(int i){

final i.runlibrary.app.v.wb tab=st.wb(open.cn.awg.pro.R.id.Tab);

final i.runlibrary.app.v.xxbj x1=st.xxbj(open.cn.awg.pro.R.id.xxbj1);

final i.runlibrary.app.v.xxbj x2=st.xxbj(open.cn.awg.pro.R.id.xxbj6);

final i.runlibrary.app.v.xxbj x3=st.xxbj(open.cn.awg.pro.R.id.xxbj2);

if(i==0){

gj.jmxc(new java.lang.Runnable(){

public void run(){

tab.zf("< App Debug");
x1.kjd(8);
x2.kjd(8);
x3.kjd(0);


}

});


}

else if(i==1){

gj.jmxc(new java.lang.Runnable(){

public void run(){

tab.zf("< 程序测试");
x1.kjd(0);
x2.kjd(8);
x3.kjd(8);


}

});


}

else if(i==2){

gj.jmxc(new java.lang.Runnable(){

public void run(){

tab.zf("< App Debug");
x1.kjd(8);
x2.kjd(0);
x3.kjd(8);


}

});


}

}

public void csh(){

}

public void jzpd(){

java.lang.String set1="/data/user/0/open.cn.awg.pro/settings/set1.inf";

if(zf.dy(wj.dqwb(set1),"true")){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb7).zf("已开启实验性功能");


}

});


}
else{

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb7).zf("已关闭实验性功能");


}

});


}
show(1);

}

public void onWindowFocusChanged(boolean hs){
super.onWindowFocusChanged(hs);

final java.lang.String a2="/data/user/0/open.cn.awg.pro/settings/a3.inf";

java.lang.String a=wj.dqwb(a2);

if(zf.dy(a,"4")){


java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{


        
    Window window = lei.getWindow();
    BangScreenTools.getBangScreenTools().fullscreen(window, lei);
    BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);
  


}

}

public void onRestart(){
super.onRestart();

final java.lang.String a2="/data/user/0/open.cn.awg.pro/settings/a3.inf";

java.lang.String a=wj.dqwb(a2);

if(zf.dy(a,"4")){


java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{


        
    Window window = lei.getWindow();
    BangScreenTools.getBangScreenTools().fullscreen(window, lei);
    BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);
  


}

}

public void onStart(){
super.onStart();

final java.lang.String a2="/data/user/0/open.cn.awg.pro/settings/a3.inf";

java.lang.String a=wj.dqwb(a2);

if(zf.dy(a,"4")){


java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{


        
    Window window = lei.getWindow();
    BangScreenTools.getBangScreenTools().fullscreen(window, lei);
    BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);
  


}

i.runlibrary.app.v.qtgd qtgd1=st.qtgd(open.cn.awg.pro.R.id.qtgd1);

  qtgd1.v.setOnGenericMotionListener(new View.OnGenericMotionListener() {
    @Override
    public boolean onGenericMotion(View vw, android.view.MotionEvent me) {
      if (me.getAction() == android.view.MotionEvent.ACTION_SCROLL && me.isFromSource(androidx.core.view.InputDeviceCompat.SOURCE_ROTARY_ENCODER)) {
                                         
        float delta = -me.getAxisValue(androidx.core.view.MotionEventCompat.AXIS_SCROLL) *
        androidx.core.view.ViewConfigurationCompat.getScaledVerticalScrollFactor(
        android.view.ViewConfiguration.get(lei), lei
        );
                                                         
        vw.scrollBy(0, java.lang.Math.round(delta));
        return true;
      }
      return false;
    }
  });

qtgd1.v.requestFocus();

}

public void onResume(){
super.onResume();

final java.lang.String a2="/data/user/0/open.cn.awg.pro/settings/a3.inf";

java.lang.String a=wj.dqwb(a2);

if(zf.dy(a,"4")){


java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{


        
    Window window = lei.getWindow();
    BangScreenTools.getBangScreenTools().fullscreen(window, lei);
    BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);
  


}

}

public boolean onKeyDown(int kc, android.view.KeyEvent ke){

if(kc==4){


if(zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(),"< App Debug")){

gj.gb();


}

else if(zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(),"< 程序测试")){

show(0);


}


}
return false;

}

public void onDestroy(){
super.onDestroy();
open.cn.awg.pro.e6.getInstance().removeActivity(lei);

}

private void $_onClick_d26d5d7080(android.view.View vw){

if(zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(),"< App Debug")){

gj.gb();


}

else if(zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(),"< 程序测试")){

show(0);


}

}

private void $_onClick_i3167b45174(android.view.View vw){
jzpd();

}

private boolean $_onLongClick_i3167b45174(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb3).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb4).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
return true;

}

private void $_onClick_i20c2777fab(android.view.View vw){
e1.tsk("功能帮助","App Debug有助于调试应用程序,帮助您解决问题.");

}

private boolean $_onLongClick_i20c2777fab(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb5).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb6).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
return true;

}

private void $_onClick_i4d951cdb0e(android.view.View vw){
gj.gb();

}

private void $_onClick_i2202ede8ff(android.view.View vw){

java.lang.String set1="/data/user/0/open.cn.awg.pro/settings/set1.inf";

if(zf.dy(wj.dqwb(set1),"false")){

wj.xrwb(set1,"true");
e1.tsk("提示","已开启实验性功能");


}
else{

wj.xrwb(set1,"false");
e1.tsk("提示","已关闭实验性功能");


}
jzpd();

}

private boolean $_onLongClick_i2202ede8ff(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb2).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb7).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
return true;

}

private void $_onClick_fab146d7a5(android.view.View vw){
gj.tz(f1.class);

}

private boolean $_onLongClick_fab146d7a5(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb14).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb15).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
return true;

}

private void $_onClick_e585d94488(android.view.View vw){
gj.tz(f12.class);

}

private boolean $_onLongClick_e585d94488(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb12).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb13).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
return true;

}

private void $_onClick_i34dc7bf20a(android.view.View vw){
show(0);

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
private android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i34dc7bf20a(vw);
}

};
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_e585d94488=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_e585d94488(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_e585d94488=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_e585d94488(vw);
}

};
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_fab146d7a5=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_fab146d7a5(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_fab146d7a5=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_fab146d7a5(vw);
}

};
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_i2202ede8ff=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_i2202ede8ff(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i2202ede8ff=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i2202ede8ff(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i4d951cdb0e=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i4d951cdb0e(vw);
}

};
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_i20c2777fab=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_i20c2777fab(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i20c2777fab=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i20c2777fab(vw);
}

};
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_i3167b45174=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_i3167b45174(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i3167b45174=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i3167b45174(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_d26d5d7080(vw);
}

};

public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw){

android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

android.widget.RelativeLayout i3167b45174 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj2);
i3167b45174.setOnClickListener($_on_setOnClickListener_i3167b45174);
i3167b45174.setOnLongClickListener($_on_setOnLongClickListener_i3167b45174);

android.widget.RelativeLayout i20c2777fab = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj3);
i20c2777fab.setOnClickListener($_on_setOnClickListener_i20c2777fab);
i20c2777fab.setOnLongClickListener($_on_setOnLongClickListener_i20c2777fab);

android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

android.widget.RelativeLayout i2202ede8ff = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj4);
i2202ede8ff.setOnClickListener($_on_setOnClickListener_i2202ede8ff);
i2202ede8ff.setOnLongClickListener($_on_setOnLongClickListener_i2202ede8ff);

android.widget.RelativeLayout fab146d7a5 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj8);
fab146d7a5.setOnClickListener($_on_setOnClickListener_fab146d7a5);
fab146d7a5.setOnLongClickListener($_on_setOnLongClickListener_fab146d7a5);

android.widget.TextView adf370a235 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb13);

android.widget.RelativeLayout e585d94488 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj7);
e585d94488.setOnClickListener($_on_setOnClickListener_e585d94488);
e585d94488.setOnLongClickListener($_on_setOnLongClickListener_e585d94488);

android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

__layoutIsLoaded(ay, vw);
}

    public final l5_a4_debug_d0527_y2022 lei = this, 类 = this;
}
