package open.cn.awg.pro;

import i.app.iActivity;
 import android.graphics.Bitmap; import java.lang.Integer; import android.content.pm.ActivityInfo; import android.view.WindowManager; import android.view.Window; import android.os.Build; import android.view.View; import java.lang.reflect.Field; import java.lang.reflect.Method; import com.ypz.bangscreentools.BangScreenTools; import java.io.File; import android.content.Context; import android.content.pm.PackageInfo; import java.lang.ClassLoader; import dalvik.system.DexClassLoader; import android.content.pm.PackageManager; import android.content.Intent; import android.content.ComponentName; import android.os.Environment;import java.lang.*;

public class m2 extends iActivity {

public void onCreate(android.os.Bundle be){
super.onCreate(be);
setContentView(open.cn.awg.pro.R.layout.m2);
_$_viewAutomaticSettingEvent();
open.cn.awg.pro.e6.getInstance().addActivity(lei);
e14m2.jz();

}

public void __layoutIsLoaded(android.app.Activity ay, android.view.View vw){

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

st.xdbj(open.cn.awg.pro.R.id.m2_v).shxtck(true);

java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{

st.xdbj(open.cn.awg.pro.R.id.m2_v).shxtck(false);

        
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


i.runlibrary.app.v.v7lb lbx=st.v7lb(open.cn.awg.pro.R.id.v7lb1);

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

public open.cn.awg.pro.e14  e14=new e14(_APPINFO);

public open.cn.awg.pro.e14m2  e14m2=new e14m2(_APPINFO);

public open.cn.awg.pro.e1  e1=new e1(_APPINFO);

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


if(zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(),e14m2.titleback)){

gj.gb();


}
else{



}


}
return false;

}

private void $_onClick_d26d5d7080(android.view.View vw){

if(zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(),e14m2.titleback)){

gj.gb();


}

}

private boolean $_onTouch_efa568dfe6(android.view.View vw, android.view.MotionEvent me){
return true;

}

private boolean $_onTouch_i32ee139ba1(android.view.View vw, android.view.MotionEvent me){

if(vw.isFocused()==false){

gj.jmxc(new java.lang.Runnable(){

public void run(){


i.runlibrary.app.v.qtgd qtgd1=st.qtgd(open.cn.awg.pro.R.id.qtgd1);
qtgd1.v.requestFocus();


}

});


}
return false;

}

private void $_onClick_i8217d42a17(android.view.View vw){
e14m2.viewshow(0);

}

private void $_onClick_i4d951cdb0e(android.view.View vw){
e14m2.cmd();

}

private boolean $_onTouch_i9068f5d66e(android.view.View vw, android.view.MotionEvent me){
return true;

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
private android.view.View.OnTouchListener $_on_setOnTouchListener_i9068f5d66e=new android.view.View.OnTouchListener(){

public boolean onTouch(android.view.View vw, android.view.MotionEvent me){
return $_onTouch_i9068f5d66e(vw, me);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i4d951cdb0e=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i4d951cdb0e(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i8217d42a17=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i8217d42a17(vw);
}

};
private android.view.View.OnTouchListener $_on_setOnTouchListener_i32ee139ba1=new android.view.View.OnTouchListener(){

public boolean onTouch(android.view.View vw, android.view.MotionEvent me){
return $_onTouch_i32ee139ba1(vw, me);
}

};
private android.view.View.OnTouchListener $_on_setOnTouchListener_efa568dfe6=new android.view.View.OnTouchListener(){

public boolean onTouch(android.view.View vw, android.view.MotionEvent me){
return $_onTouch_efa568dfe6(vw, me);
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

android.widget.LinearLayout efa568dfe6 = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj3);
efa568dfe6.setOnTouchListener($_on_setOnTouchListener_efa568dfe6);

android.widget.EditText i32ee139ba1 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjk1);
i32ee139ba1.setOnTouchListener($_on_setOnTouchListener_i32ee139ba1);

android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);
i8217d42a17.setOnClickListener($_on_setOnClickListener_i8217d42a17);

android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

android.widget.LinearLayout i9068f5d66e = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj4);
i9068f5d66e.setOnTouchListener($_on_setOnTouchListener_i9068f5d66e);

__layoutIsLoaded(ay, vw);
}

    public final m2 lei = this, 类 = this;
}
