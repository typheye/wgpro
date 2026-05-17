package open.cn.awg.pro;

import i.app.iActivity;
 import android.os.Process; import android.content.pm.ActivityInfo; import android.view.WindowManager; import android.view.Window; import android.os.Build; import android.view.View; import java.lang.reflect.Field; import java.lang.reflect.Method; import com.ypz.bangscreentools.BangScreenTools; import android.content.Intent;import java.lang.*;

public class b0 extends iActivity {

public void onCreate(android.os.Bundle be){
super.onCreate(be);
setContentView(open.cn.awg.pro.R.layout.b0);
_$_viewAutomaticSettingEvent();
open.cn.awg.pro.e6.getInstance().addActivity(lei);

if(wj.cz("/data/user/0/open.cn.awg.pro/settings/a4.inf")){

show(0);


}
else{

gj.jmxc(new java.lang.Runnable(){

public void run(){


      android.content.Intent intent = lei.getPackageManager().getLaunchIntentForPackage(lei.getPackageName());
      intent.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK);
      lei.startActivity(intent);
      open.cn.awg.pro.e6.getInstance().exit();
    


}

});


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

      
  Window window = lei.getWindow();
  BangScreenTools.getBangScreenTools().fullscreen(window, lei);
  BangScreenTools.getBangScreenTools().windowChangeFullscreen(window);


}

public int mode;

public void show(int i_){

final int i=i_;

final i.runlibrary.app.v.xxbj xxbj4=st.xxbj(open.cn.awg.pro.R.id.xxbj4);

final i.runlibrary.app.v.xxbj xxbj1=st.xxbj(open.cn.awg.pro.R.id.xxbj1);
gj.jmxc(new java.lang.Runnable(){

public void run(){


if(i==0){

xxbj1.kjd(0);
xxbj4.kjd(8);


}

else if(i==1){

xxbj1.kjd(8);
xxbj4.kjd(0);


}

else if(i==2){

xxbj1.kjd(8);
xxbj4.kjd(8);


}


}

});

}

public void run(){
gj.xc(new java.lang.Thread(){

public void run(){

show(2);
gj.zt(500);

if(mode==0){


java.lang.String m="rm -rf \"/data/user/0/open.cn.awg.pro/settings\"";
com.demo.e3.cmd(lei,m,false);
gj.zt(500);
gj.jmxc(new java.lang.Runnable(){

public void run(){


          android.content.Intent intent = lei.getPackageManager().getLaunchIntentForPackage(lei.getPackageName());
          intent.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK);
          lei.startActivity(intent);
          open.cn.awg.pro.e6.getInstance().exit();
        


}

});


}

else if(mode==1){


java.lang.String m="pm clear open.cn.awg.pro";
com.demo.e3.cmd(lei,m,false);


}

else if(mode==3){

gj.zt(500);
gj.jmxc(new java.lang.Runnable(){

public void run(){


          android.content.Intent intent = lei.getPackageManager().getLaunchIntentForPackage(lei.getPackageName());
          intent.addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK);
          lei.startActivity(intent);
          open.cn.awg.pro.e6.getInstance().exit();
        


}

});


}

else if(mode==4){

open.cn.awg.pro.e6.getInstance().exit();


}


}

});

}

public boolean onKeyDown(int kc, android.view.KeyEvent ke){
return true;

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

public void onResume(){
super.onResume();

      
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

public void onDestroy(){
super.onDestroy();
open.cn.awg.pro.e6.getInstance().removeActivity(lei);

}

private void $_onClick_i5b34eede7f(android.view.View vw){
mode=3;
show(1);

}

private void $_onClick_i2202ede8ff(android.view.View vw){
mode=0;
show(1);

}

private void $_onClick_c0d3e00d2b(android.view.View vw){
mode=1;
show(1);

}

private void $_onClick_i2a86c78b06(android.view.View vw){
mode=4;
show(1);

}

private void $_onClick_e585d94488(android.view.View vw){
run();

}

private void $_onClick_b8bc22741b(android.view.View vw){
show(0);

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
private android.view.View.OnClickListener $_on_setOnClickListener_b8bc22741b=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_b8bc22741b(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_e585d94488=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_e585d94488(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i2a86c78b06=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i2a86c78b06(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_c0d3e00d2b=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_c0d3e00d2b(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i2202ede8ff=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i2202ede8ff(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i5b34eede7f=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i5b34eede7f(vw);
}

};

public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw){

android.widget.TextView i81652f77b6 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb10);

android.widget.RelativeLayout i5b34eede7f = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj10);
i5b34eede7f.setOnClickListener($_on_setOnClickListener_i5b34eede7f);

android.widget.TextView i0d2d4d9236 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb3);

android.widget.RelativeLayout i2202ede8ff = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj4);
i2202ede8ff.setOnClickListener($_on_setOnClickListener_i2202ede8ff);

android.widget.TextView e943ebd71e = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb4);

android.widget.RelativeLayout c0d3e00d2b = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj5);
c0d3e00d2b.setOnClickListener($_on_setOnClickListener_c0d3e00d2b);

android.widget.TextView i2a86c78b06 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb5);
i2a86c78b06.setOnClickListener($_on_setOnClickListener_i2a86c78b06);

android.widget.TextView f52e07ac8c = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb6);

android.widget.RelativeLayout e585d94488 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj7);
e585d94488.setOnClickListener($_on_setOnClickListener_e585d94488);

android.widget.TextView b8bc22741b = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.wb7);
b8bc22741b.setOnClickListener($_on_setOnClickListener_b8bc22741b);

__layoutIsLoaded(ay, vw);
}

    public final b0 lei = this, 类 = this;
}
