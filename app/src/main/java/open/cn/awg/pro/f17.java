package open.cn.awg.pro;

import i.app.iActivity;
 import android.graphics.Bitmap; import java.lang.Integer; import android.content.pm.ActivityInfo; import android.view.WindowManager; import android.view.Window; import android.os.Build; import android.view.View; import java.lang.reflect.Field; import java.lang.reflect.Method; import com.ypz.bangscreentools.BangScreenTools;import java.lang.*;

public class f17 extends iActivity {

public void onCreate(android.os.Bundle be){
super.onCreate(be);
setContentView(open.cn.awg.pro.R.layout.f17);
_$_viewAutomaticSettingEvent();
open.cn.awg.pro.e6.getInstance().addActivity(lei);
jz();

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

public int old_i=0;

public java.lang.String lin="";

public java.lang.String newcolor="";

public int bs=0;

public void jz(){

java.lang.String set2="/data/user/0/open.cn.awg.pro/settings/f17/set1.inf";

int i=Integer.parseInt(wj.dqwb(set2));
old_i=i;

i.runlibrary.app.v.tdt tdt1=st.tdt(open.cn.awg.pro.R.id.tdt1);
tdt1.jdz(i);

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

public boolean onKeyDown(int kc, android.view.KeyEvent ke){
return true;

}

public void onDestroy(){
super.onDestroy();
open.cn.awg.pro.e6.getInstance().removeActivity(lei);

}

private void $_onProgressChanged_d11169f9c0(android.widget.SeekBar vw, int ps, boolean fu){

java.lang.String set="/data/user/0/open.cn.awg.pro/settings/f17/set1.inf";

i.runlibrary.app.v.tdt tdt1=st.tdt(open.cn.awg.pro.R.id.tdt1);

final int i=tdt1.jdz();

final int ii=i/2;
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.xdbj(open.cn.awg.pro.R.id.xdbj3).wbj(i,i,i,i);
st.wb(open.cn.awg.pro.R.id.wb10).zf(ii+"dp");


}

});

java.lang.String sis=sj.zh(i).zstring();
wj.xrwb(set,sis);

}

private void $_onStartTrackingTouch_d11169f9c0(android.widget.SeekBar vw){

}

private void $_onStopTrackingTouch_d11169f9c0(android.widget.SeekBar vw){

}

private void $_onClick_ff56a8e7f6(android.view.View vw){

i.runlibrary.app.v.tdt tdt1=st.tdt(open.cn.awg.pro.R.id.tdt1);

if(tdt1.jdz()==old_i){

gj.gb();


}
else{

gj.xc(new java.lang.Thread(){

public void run(){

gj.jmxc(new java.lang.Runnable(){

public void run(){


java.lang.String[] name=new java.lang.String[]{"Msg"};

java.lang.String[] value=new java.lang.String[]{"应用成功 点击重启"};
gj.tz(a0.class,name,value);


}

});


}

});


}

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
private android.view.View.OnClickListener $_on_setOnClickListener_ff56a8e7f6=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_ff56a8e7f6(vw);
}

};
private android.widget.SeekBar.OnSeekBarChangeListener $_on_setOnSeekBarChangeListener_d11169f9c0=new android.widget.SeekBar.OnSeekBarChangeListener(){

public void onProgressChanged(android.widget.SeekBar vw, int ps, boolean fu){
$_onProgressChanged_d11169f9c0(vw, ps, fu);
}

public void onStartTrackingTouch(android.widget.SeekBar vw){
$_onStartTrackingTouch_d11169f9c0(vw);
}

public void onStopTrackingTouch(android.widget.SeekBar vw){
$_onStopTrackingTouch_d11169f9c0(vw);
}

};

public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw){

android.widget.SeekBar d11169f9c0 = (android.widget.SeekBar) findViewById(ay, vw, open.cn.awg.pro.R.id.tdt1);
d11169f9c0.setOnSeekBarChangeListener($_on_setOnSeekBarChangeListener_d11169f9c0);

android.widget.TextView ff56a8e7f6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb1);
ff56a8e7f6.setOnClickListener($_on_setOnClickListener_ff56a8e7f6);

__layoutIsLoaded(ay, vw);
}

    public final f17 lei = this, 类 = this;
}
