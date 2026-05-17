package open.cn.awg.pro;

import i.app.iActivity;
 import android.content.pm.ActivityInfo; import android.view.WindowManager; import android.view.Window; import android.os.Build; import android.view.View; import java.lang.reflect.Field; import java.lang.reflect.Method; import com.ypz.bangscreentools.BangScreenTools;import java.lang.*;

public class d5 extends iActivity {

public void onCreate(android.os.Bundle be){
super.onCreate(be);
setContentView(open.cn.awg.pro.R.layout.d5);
_$_viewAutomaticSettingEvent();
open.cn.awg.pro.e6.getInstance().addActivity(lei);

open.cn.awg.pro.e1  e1=new e1(_APPINFO);

try{

url=sj.hqtz("url");

java.lang.String d1_rootpaths;
d1_rootpaths=open.cn.awg.pro.abc.d1_rootpath;

java.lang.String[] fhValue=e1.fileinfo(d1_rootpaths,url);

java.lang.String bts="";

if(zf.dy(fhValue[3],"")){

bts="文本编辑器";


}
else{

bts=fhValue[3];


}

if(wj.cz(url)==true){


if(wj.dx(url)>102400){

e1.tsk("提示","文本长度过大,无法进行编辑");
gj.gb();


}
else{


java.lang.String t=wj.dqwb(url);

i.runlibrary.app.v.bjk text=st.bjk(open.cn.awg.pro.R.id.bjk1);
text.szzt("@resource/a7e484743f6796f092cd859819cb5fc8");
text.zf(t);


}


}
else{

e1.tsk("提示","文件不存在");
gj.gb();


}


}catch(java.lang.Throwable e){

e1.upload_error(e,"d5.*");
gj.gb();


}

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

st.xdbj(open.cn.awg.pro.R.id.d5_v).shxtck(true);

java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{

st.xdbj(open.cn.awg.pro.R.id.d5_v).shxtck(false);

        
    Window window = lei.getWindow();
    BangScreenTools.getBangScreenTools().fullscreen(window, lei);
    BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);
  


}

if(zf.dy(a,"1")){


i.runlibrary.app.v.wb wtab=st.wb(open.cn.awg.pro.R.id.Tab);
wtab.nbj(0,"15dp",0,"3dp");
wtab.dqfs("center");
st.xxbj(open.cn.awg.pro.R.id.xxbj2).nbj(u,0,u,50);


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
st.xxbj(open.cn.awg.pro.R.id.xxbj2).nbj(0,0,0,0);


}

else if(zf.dy(a,"3")){


i.runlibrary.app.v.wb wtab=st.wb(open.cn.awg.pro.R.id.Tab);
wtab.nbj(0,"15dp",0,"3dp");
wtab.dqfs("center");
st.xxbj(open.cn.awg.pro.R.id.xxbj2).nbj(u,0,u,50);


}

else if(zf.dy(a,"4")){

st.xxbj(open.cn.awg.pro.R.id.xxbj2).nbj(0,0,0,0);


}

java.lang.String set2="/data/user/0/open.cn.awg.pro/settings/d2/set2.inf";

int iss=Integer.parseInt(wj.dqwb(set2));

i.runlibrary.app.v.bjk text=st.bjk(open.cn.awg.pro.R.id.bjk1);

java.lang.String set3="/data/user/0/open.cn.awg.pro/settings/d2/set3.inf";

java.lang.String set4="/data/user/0/open.cn.awg.pro/settings/d2/set4.inf";

final java.lang.String color1=wj.dqwb(set3);

final java.lang.String color2=wj.dqwb(set4);

java.lang.String set="/data/user/0/open.cn.awg.pro/settings/set3.inf";

if(zf.dy(wj.dqwb(set),"true")){


java.lang.String te=wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a15");

java.lang.String[] name=new java.lang.String[]{"title","text"};

java.lang.String[] value=new java.lang.String[]{"新手教程",te};
gj.tz(a3.class,name,value);


}

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

public open.cn.awg.pro.e1  e1=new e1(_APPINFO);

public java.lang.String url="";

public void onDestroy(){
super.onDestroy();
open.cn.awg.pro.e6.getInstance().removeActivity(lei);

}

public boolean onKeyDown(int kc, android.view.KeyEvent ke){

if(kc==4){



}
return false;

}

private void $_onClick_d26d5d7080(android.view.View vw){
gj.gb();

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

java.lang.String te=st.bjk(open.cn.awg.pro.R.id.bjk1).zf();

if(wj.xrwb(url,te)==true){

e1.tsk("提示","保存成功");


}
else{

e1.tsk("提示","保存失败");


}

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
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
private android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_d26d5d7080(vw);
}

};

public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw){

android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

android.widget.EditText i32ee139ba1 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjk1);
i32ee139ba1.setOnTouchListener($_on_setOnTouchListener_i32ee139ba1);

android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);
i8217d42a17.setOnClickListener($_on_setOnClickListener_i8217d42a17);

__layoutIsLoaded(ay, vw);
}

    public final d5 lei = this, 类 = this;
}
