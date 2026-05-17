package open.cn.awg.pro;

import i.app.iActivity;
 import android.view.Window; import com.ypz.bangscreentools.BangScreenTools; import android.widget.TextView; import android.text.method.ScrollingMovementMethod; import android.view.View;import java.lang.*;

public class k2 extends iActivity {

public void onCreate(android.os.Bundle be){
super.onCreate(be);
setContentView(open.cn.awg.pro.R.layout.k2);
_$_viewAutomaticSettingEvent();
open.cn.awg.pro.e6.getInstance().addActivity(lei);
csh();

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

public static i.runlibrary.app.v.wb cmd;

public static i.runlibrary.app.v.wb jzt;

public void csh(){

final i.runlibrary.app.v.qtgd qtgd=st.qtgd(open.cn.awg.pro.R.id.qtgd1);
cmd=st.wb(open.cn.awg.pro.R.id.wb1);
jzt=st.wb(open.cn.awg.pro.R.id.wb2);
gj.xc(new java.lang.Thread(){

public void run(){

gj.jmxc(new java.lang.Runnable(){

public void run(){

cmd.zf("开始扫描垃圾文件...\n");
jzt.zf("扫描中");


}

});

java.lang.String[] gl=new java.lang.String[]{"*.log"};

java.io.File[] lb=wj.czwj("%",gl);

for(java.io.File c: lb){

gj.zt(5);

final java.lang.String f=c.getAbsolutePath();
gj.jmxc(new java.lang.Runnable(){

public void run(){

cmd.st.append(((java.lang.CharSequence)("发现日志文件:"+f)));
cmd.st.append(((java.lang.CharSequence)("\n")));
qtgd.st.fullScroll(View.FOCUS_DOWN);


}

});


}
gj.zt(500);
gj.jmxc(new java.lang.Runnable(){

public void run(){

cmd.st.append(((java.lang.CharSequence)("扫描完成,开始清理")));
cmd.st.append(((java.lang.CharSequence)("\n")));
qtgd.st.fullScroll(View.FOCUS_DOWN);
jzt.zf("清理中");


}

});
gj.zt(500);

for(java.io.File c: lb){

gj.zt(5);

final java.lang.String f=c.getAbsolutePath();
gj.jmxc(new java.lang.Runnable(){

public void run(){

cmd.st.append(((java.lang.CharSequence)("清理日志文件:"+f)));
wj.sc(f);
cmd.st.append(((java.lang.CharSequence)("\n")));
qtgd.st.fullScroll(View.FOCUS_DOWN);


}

});


}
gj.zt(500);
gj.jmxc(new java.lang.Runnable(){

public void run(){

cmd.st.append(((java.lang.CharSequence)("清理完成,结束任务")));
cmd.st.append(((java.lang.CharSequence)("\n")));
qtgd.st.fullScroll(View.FOCUS_DOWN);
jzt.zf("清理完成");


}

});
gj.zt(500);
gj.jmxc(new java.lang.Runnable(){

public void run(){

gj.gb();


}

});


}

});

}

public boolean onKeyDown(int kc, android.view.KeyEvent ke){

if(kc==4){



}
return false;

}

public void onDestroy(){
super.onDestroy();
open.cn.awg.pro.e6.getInstance().removeActivity(lei);

}

private boolean $_onTouch_aaeebbd136(android.view.View vw, android.view.MotionEvent me){
return true;

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
private android.view.View.OnTouchListener $_on_setOnTouchListener_aaeebbd136=new android.view.View.OnTouchListener(){

public boolean onTouch(android.view.View vw, android.view.MotionEvent me){
return $_onTouch_aaeebbd136(vw, me);
}

};

public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw){

android.widget.RelativeLayout aaeebbd136 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj1);
aaeebbd136.setOnTouchListener($_on_setOnTouchListener_aaeebbd136);

__layoutIsLoaded(ay, vw);
}

    public final k2 lei = this, 类 = this;
}
