package open.cn.awg.pro;

import i.app.iActivity;
 import android.view.Window; import com.ypz.bangscreentools.BangScreenTools; import android.widget.TextView; import android.text.method.ScrollingMovementMethod; import android.view.View;import java.lang.*;

public class k4 extends iActivity {

public void onCreate(android.os.Bundle be){
super.onCreate(be);
setContentView(open.cn.awg.pro.R.layout.k4);
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

public open.cn.awg.pro.e1  e1=new e1(_APPINFO);

public void csh(){

final i.runlibrary.app.v.qtgd qtgd=st.qtgd(open.cn.awg.pro.R.id.qtgd1);
cmd=st.wb(open.cn.awg.pro.R.id.wb1);
jzt=st.wb(open.cn.awg.pro.R.id.wb2);
gj.xc(new java.lang.Thread(){

public void run(){

gj.jmxc(new java.lang.Runnable(){

public void run(){

cmd.zf("正在准备中...\n");
jzt.zf("准备中");


}

});
gj.zt(500);
gj.jmxc(new java.lang.Runnable(){

public void run(){

cmd.st.append(((java.lang.CharSequence)("准备完成,开始测试网络请求")));
cmd.st.append(((java.lang.CharSequence)("\n")));
qtgd.st.fullScroll(View.FOCUS_DOWN);
jzt.zf("测试中");


}

});

java.lang.String testurl="/data/user/0/open.cn.awg.pro/settings/f10/set2.inf";
testurl=wj.dqwb(testurl);
testurl=e1.urlUnlockString(testurl);

final java.lang.String st5=wl.hq(testurl,null,"utf-8",null,true,null,20000,20000,null);
gj.jmxc(new java.lang.Runnable(){

public void run(){

cmd.st.append(((java.lang.CharSequence)("请求发送成功,返回结果:\n"+st5)));
cmd.st.append(((java.lang.CharSequence)("\n")));
qtgd.st.fullScroll(View.FOCUS_DOWN);


}

});
gj.zt(500);
gj.jmxc(new java.lang.Runnable(){

public void run(){

cmd.st.append(((java.lang.CharSequence)("测试完成,开始优化")));
cmd.st.append(((java.lang.CharSequence)("\n")));
qtgd.st.fullScroll(View.FOCUS_DOWN);
jzt.zf("优化中");


}

});
gj.zt(500);

int b=0;

while(b<100){

gj.zt(50);
b=b+1;

final int c=b;
gj.jmxc(new java.lang.Runnable(){

public void run(){

cmd.st.append(((java.lang.CharSequence)("优化进度:"+c+"%")));
cmd.st.append(((java.lang.CharSequence)("\n")));
qtgd.st.fullScroll(View.FOCUS_DOWN);


}

});


}
gj.zt(500);
gj.jmxc(new java.lang.Runnable(){

public void run(){

cmd.st.append(((java.lang.CharSequence)("优化完成,结束任务")));
cmd.st.append(((java.lang.CharSequence)("\n")));
qtgd.st.fullScroll(View.FOCUS_DOWN);
jzt.zf("优化完成");


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

    public final k4 lei = this, 类 = this;
}
