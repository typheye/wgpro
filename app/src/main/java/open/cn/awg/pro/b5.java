package open.cn.awg.pro;

import i.app.iActivity;
 import android.content.pm.ActivityInfo; import android.view.WindowManager; import android.view.Window; import android.os.Build; import android.view.View; import java.lang.reflect.Field; import java.lang.reflect.Method; import com.ypz.bangscreentools.BangScreenTools;import java.lang.*;

public class b5 extends iActivity {

public void onCreate(android.os.Bundle be){
super.onCreate(be);
setContentView(open.cn.awg.pro.R.layout.b5);
_$_viewAutomaticSettingEvent();
open.cn.awg.pro.e6.getInstance().addActivity(lei);
show();
e1.online();

}

public void __layoutIsLoaded(android.app.Activity ay, android.view.View vw){

if(zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4s.inf"),"false")){


if(zf.dy(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/set4.inf"),"true")){

getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES);


}
else{

getDelegate().setLocalNightMode(androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO);


}


}

      
  Window window = lei.getWindow();
  BangScreenTools.getBangScreenTools().fullscreen(window, lei);
  BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);


}

public boolean onKeyDown(int kc, android.view.KeyEvent ke){
return true;

}

public open.cn.awg.pro.e1  e1=new e1(_APPINFO);

public void show(){

final java.lang.String a1="/data/user/0/open.cn.awg.pro/settings/a5.inf";

boolean t;

if(zf.dy(wj.dqwb(a1),"")||wj.cz(a1)==false){

t=false;


}
else{

t=true;


}

final i.runlibrary.app.v.xxbj x1=st.xxbj(open.cn.awg.pro.R.id.xxbj1);

final i.runlibrary.app.v.xxbj x2=st.xxbj(open.cn.awg.pro.R.id.xxbj2);

final i.runlibrary.app.v.wb t2=st.wb(open.cn.awg.pro.R.id.wb3);

if(t==true){

gj.jmxc(new java.lang.Runnable(){

public void run(){

x1.kjd(8);
x2.kjd(0);
t2.zf(wj.dqwb(a1));


}

});


}

else if(t==false){

gj.jmxc(new java.lang.Runnable(){

public void run(){

x1.kjd(0);
x2.kjd(8);


}

});


}

}

public void onWindowFocusChanged(boolean hs){
super.onWindowFocusChanged(hs);

      
  Window window = lei.getWindow();
  BangScreenTools.getBangScreenTools().fullscreen(window, lei);
  BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);


}

public void onRestart(){
super.onRestart();

      
  Window window = lei.getWindow();
  BangScreenTools.getBangScreenTools().fullscreen(window, lei);
  BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);


}

public void onStart(){
super.onStart();

      
  Window window = lei.getWindow();
  BangScreenTools.getBangScreenTools().fullscreen(window, lei);
  BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);


}

public void onResume(){
super.onResume();

      
  Window window = lei.getWindow();
  BangScreenTools.getBangScreenTools().fullscreen(window, lei);
  BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);


}

public void onDestroy(){
super.onDestroy();
open.cn.awg.pro.e6.getInstance().removeActivity(lei);

}

private void $_onClick_i34dc7bf20a(android.view.View vw){

java.lang.String n=st.bjk(open.cn.awg.pro.R.id.bjk1).zf();

final java.lang.String a1="/data/user/0/open.cn.awg.pro/settings/a5.inf";
n=zf.qctwkg(n);

if(zf.dy(n,"")){

e1.tsk("提示","ID不能为空");


}
else{


if(n.length()>4){

wj.xrwb(a1,n);
show();


}
else{

e1.tsk("提示","ID不能小于5位");


}


}

}

private boolean $_onLongClick_i0d2d4d9236(android.view.View vw){
gj.tz(f1.class);
return true;

}

private void $_onClick_i4d951cdb0e(android.view.View vw){
gj.gb();

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
private android.view.View.OnClickListener $_on_setOnClickListener_i4d951cdb0e=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i4d951cdb0e(vw);
}

};
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_i0d2d4d9236=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_i0d2d4d9236(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i34dc7bf20a(vw);
}

};

public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw){

android.widget.TextView i7c3e3c6b5d = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb2);

android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

android.widget.TextView ff56a8e7f6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb1);

android.widget.TextView i0d2d4d9236 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb3);
i0d2d4d9236.setOnLongClickListener($_on_setOnLongClickListener_i0d2d4d9236);

android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

__layoutIsLoaded(ay, vw);
}

    public final b5 lei = this, 类 = this;
}
