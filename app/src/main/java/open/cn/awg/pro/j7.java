package open.cn.awg.pro;

import i.app.iActivity;
 import android.view.Window; import com.ypz.bangscreentools.BangScreenTools; import android.view.View;import java.lang.*;

public class j7 extends iActivity {

public void onCreate(android.os.Bundle be){
super.onCreate(be);
setContentView(open.cn.awg.pro.R.layout.j7);
_$_viewAutomaticSettingEvent();
open.cn.awg.pro.e6.getInstance().addActivity(lei);

java.lang.String a1="/data/user/0/open.cn.awg.pro/settings/a3.inf";

java.lang.String a001="/data/user/0/open.cn.awg.pro/settings/dpi.inf";
p=wj.dqwb(a1);

if(wj.cz(a001)&&zf.dy(wj.dqwb(a001),"true")){

q="true";


}

if(zf.dy(p,"1")){

st.an(open.cn.awg.pro.R.id.an1).kjd(8);


}

else if(zf.dy(p,"2")&&zf.dy(q,"")){

st.an(open.cn.awg.pro.R.id.an2).kjd(8);


}

else if(zf.dy(p,"2")&&zf.dy(q,"true")){

st.an(open.cn.awg.pro.R.id.an2s).kjd(8);


}

else if(zf.dy(p,"3")){

st.an(open.cn.awg.pro.R.id.an3).kjd(8);


}

else if(zf.dy(p,"4")){

st.an(open.cn.awg.pro.R.id.an4).kjd(8);


}

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

st.xdbj(open.cn.awg.pro.R.id.j7_v).shxtck(true);

java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{

st.xdbj(open.cn.awg.pro.R.id.j7_v).shxtck(false);

        
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

public open.cn.awg.pro.e1  e1=new e1(_APPINFO);

public java.lang.String p="";

public java.lang.String q="";

public java.lang.String x="";

public void show(int i_){

final int i=i_;

final i.runlibrary.app.v.xxbj xxbj1=st.xxbj(open.cn.awg.pro.R.id.xxbj1);

final i.runlibrary.app.v.xxbj xxbj2=st.xxbj(open.cn.awg.pro.R.id.xxbj2);

final i.runlibrary.app.v.xxbj xxbj5=st.xxbj(open.cn.awg.pro.R.id.xxbj5);

final i.runlibrary.app.v.wb tab=st.wb(open.cn.awg.pro.R.id.Tab);
gj.jmxc(new java.lang.Runnable(){

public void run(){


if(i==0){

tab.zf("< 适配终端");
xxbj1.kjd(0);
xxbj2.kjd(8);
xxbj5.kjd(8);


}

else if(i==1){

tab.zf("适配终端");
xxbj1.kjd(8);
xxbj2.kjd(0);
xxbj5.kjd(8);


}

else if(i==2){

tab.zf("适配终端");
xxbj1.kjd(8);
xxbj2.kjd(8);
xxbj5.kjd(0);


}


}

});

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

public void onDestroy(){
super.onDestroy();
open.cn.awg.pro.e6.getInstance().removeActivity(lei);

}

public boolean onKeyDown(int kc, android.view.KeyEvent ke){

if(kc==4){


if(zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(),"< 适配终端")){

gj.gb();


}


}
return false;

}

private void $_onClick_d26d5d7080(android.view.View vw){

if(zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(),"< 适配终端")){

gj.gb();


}

}

private void $_onClick_i171ea34f1a(android.view.View vw){
p="1";
show(1);

}

private void $_onClick_i0b66a667b1(android.view.View vw){
p="2";
show(1);

}

private void $_onClick_i0afd0fa55f(android.view.View vw){
p="2";
q="true";
show(1);

}

private void $_onClick_i8f51a35692(android.view.View vw){
p="3";
show(1);

}

private void $_onClick_i74ce6319f3(android.view.View vw){
p="4";
show(1);

}

private void $_onClick_i34dc7bf20a(android.view.View vw){
gj.gb();

}

private void $_onClick_i4d951cdb0e(android.view.View vw){
show(0);

}

private void $_onClick_i8217d42a17(android.view.View vw){
gj.xc(new java.lang.Thread(){

public void run(){

show(2);

java.lang.String a1="/data/user/0/open.cn.awg.pro/settings/a3.inf";

java.lang.String a001="/data/user/0/open.cn.awg.pro/settings/dpi.inf";

if(zf.dy(p,"1")){

wj.xrwb(a1,"1");
wj.sc(a001);


}

else if(zf.dy(p,"2")&&zf.dy(q,"")){

wj.xrwb(a1,"2");
wj.sc(a001);


}

else if(zf.dy(p,"2")&&zf.dy(q,"true")){

wj.xrwb(a1,"2");
wj.xrwb(a001,"true");


}

else if(zf.dy(p,"3")){

wj.xrwb(a1,"3");
wj.sc(a001);


}

else if(zf.dy(p,"4")){

wj.xrwb(a1,"4");
wj.sc(a001);


}
gj.zt(2000);
gj.jmxc(new java.lang.Runnable(){

public void run(){


      android.content.Intent intent = lei.getPackageManager().getLaunchIntentForPackage(lei.getPackageName());
      intent.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK);
      lei.startActivity(intent);
      open.cn.awg.pro.e6.getInstance().exit();
    


}

});


}

});

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
private android.view.View.OnClickListener $_on_setOnClickListener_i8217d42a17=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i8217d42a17(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i4d951cdb0e=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i4d951cdb0e(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i34dc7bf20a(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i74ce6319f3=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i74ce6319f3(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i8f51a35692=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i8f51a35692(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i0afd0fa55f=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i0afd0fa55f(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i0b66a667b1=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i0b66a667b1(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i171ea34f1a=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i171ea34f1a(vw);
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

android.widget.Button i171ea34f1a = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an1);
i171ea34f1a.setOnClickListener($_on_setOnClickListener_i171ea34f1a);

android.widget.Button i0b66a667b1 = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an2);
i0b66a667b1.setOnClickListener($_on_setOnClickListener_i0b66a667b1);

android.widget.Button i0afd0fa55f = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an2s);
i0afd0fa55f.setOnClickListener($_on_setOnClickListener_i0afd0fa55f);

android.widget.Button i8f51a35692 = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an3);
i8f51a35692.setOnClickListener($_on_setOnClickListener_i8f51a35692);

android.widget.Button i74ce6319f3 = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an4);
i74ce6319f3.setOnClickListener($_on_setOnClickListener_i74ce6319f3);

android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);
i8217d42a17.setOnClickListener($_on_setOnClickListener_i8217d42a17);

__layoutIsLoaded(ay, vw);
}

    public final j7 lei = this, 类 = this;
}
