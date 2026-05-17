package open.cn.awg.pro;

import i.app.iActivity;
 import android.graphics.Bitmap; import java.lang.Integer; import android.content.pm.ActivityInfo; import android.view.WindowManager; import android.view.Window; import android.os.Build; import android.view.View; import java.lang.reflect.Field; import java.lang.reflect.Method; import com.ypz.bangscreentools.BangScreenTools; import com.alibaba.fastjson.JSONObject; import android.graphics.drawable.BitmapDrawable;import java.lang.*;

public class n3 extends iActivity {

public void onCreate(android.os.Bundle be){
super.onCreate(be);
setContentView(open.cn.awg.pro.R.layout.n3);
_$_viewAutomaticSettingEvent();
open.cn.awg.pro.e6.getInstance().addActivity(lei);
jz();

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

st.xdbj(open.cn.awg.pro.R.id.n3_v).shxtck(true);

java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{

st.xdbj(open.cn.awg.pro.R.id.n3_v).shxtck(false);

        
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

public int mode=0;

public boolean isAcc=false;

public boolean isExit=false;

public android.graphics.drawable.Drawable dbe;

public java.lang.String android_id;

public open.cn.awg.pro.e1  e1=new e1(_APPINFO);

public open.cn.awg.pro.easy  easy=new easy(_APPINFO);

public void jz(){
gj.xc(new java.lang.Thread(){

public void run(){

show(2);

if(isExit==false){

android_id=android.provider.Settings.System.getString(getContentResolver(),android.provider.Settings.Secure.ANDROID_ID);

java.lang.String urls="/data/user/0/open.cn.awg.pro/settings/f10/set2.inf";
urls=wj.dqwb(urls);
urls=e1.urlUnlockString(urls);

if(zf.cjw(urls,"/")){



}
else{

urls=urls+"/";


}

java.lang.String url=urls+"api.php";

java.lang.String post_="req=openawg_get&token="+android_id;

java.lang.String[] post=zf.fg(post_,"&",false);

if(e1.yz()){


java.lang.String st2=wl.hq(url,post,"utf-8",null,true,null,20000,20000,null);

if(zf.dy(st2,null)){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb3).zf("网络异常");


}

});
show(1);


}
else{


try{


java.lang.String code=e1.readJson(st2,"code");

if(zf.dy(code,"0")){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb3).zf("未经授权的请求");


}

});
show(1);


}

else if(zf.dy(code,"200")){


java.lang.String ap=e1.readJson(st2,"ap");

final java.lang.String an=e1.readJson(st2,"an");

java.lang.String imgurl=urls+"open/icon/"+ap+".png";
dbe =new BitmapDrawable(lei.getResources(),com.demo.e4.getURLimage(imgurl));
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.tx(open.cn.awg.pro.R.id.tx5).tx(dbe);
st.wb(open.cn.awg.pro.R.id.wb16).zf(an);


}

});
zh();


}

else if(zf.dy(code,"20")){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb3).zf("应用未获得授权");


}

});
show(1);


}

else if(zf.dy(code,"10")){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb3).zf("请求超时");


}

});
show(1);


}
else{

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb3).zf("未经授权的请求");


}

});
show(1);


}


}catch(java.lang.Throwable __$_e__){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb3).zf("未经授权的请求");


}

});
show(1);


}


}


}
else{

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb3).zf("应用验证未通过");


}

});
show(1);


}


}


}

});

}

public void sq_true(){
gj.xc(new java.lang.Thread(){

public void run(){

show(2);

java.lang.String a="/data/user/0/open.cn.awg.pro/settings/account/user";

java.lang.String set2="/data/user/0/open.cn.awg.pro/settings/n1/set2.inf";

java.lang.String urls="/data/user/0/open.cn.awg.pro/settings/f10/set2.inf";
urls=wj.dqwb(urls);
urls=e1.urlUnlockString(urls);

if(zf.cjw(urls,"/")){



}
else{

urls=urls+"/";


}

java.lang.String url=urls+"api.php";

java.lang.String post_="req=openawg_ctrl&token="+android_id+"&state=true&uid="+wj.dqwb(a)+"&uname="+wj.dqwb(set2);

java.lang.String[] post=zf.fg(post_,"&",false);

if(e1.yz()){


java.lang.String st2=wl.hq(url,post,"utf-8",null,true,null,20000,20000,null);

if(zf.dy(st2,null)){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb3).zf("网络异常");


}

});
show(1);


}
else{


try{


java.lang.String code=e1.readJson(st2,"code");

if(zf.dy(code,"200")){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb3).zf("已成功授权");


}

});
show(1);


}

else if(zf.dy(code,"10")){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb3).zf("请求超时");


}

});
show(1);


}
else{

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb3).zf("授权失败");


}

});
show(1);


}


}catch(java.lang.Throwable __$_e__){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb3).zf("授权失败");


}

});
show(1);


}


}


}


}

});

}

public void sq_false(){
gj.xc(new java.lang.Thread(){

public void run(){

show(2);

java.lang.String a="/data/user/0/open.cn.awg.pro/settings/account/user";

java.lang.String set2="/data/user/0/open.cn.awg.pro/settings/n1/set2.inf";

java.lang.String urls="/data/user/0/open.cn.awg.pro/settings/f10/set2.inf";
urls=wj.dqwb(urls);
urls=e1.urlUnlockString(urls);

if(zf.cjw(urls,"/")){



}
else{

urls=urls+"/";


}

java.lang.String url=urls+"api.php";

java.lang.String post_="req=openawg_ctrl&token="+android_id+"&state=false";

java.lang.String[] post=zf.fg(post_,"&",false);

if(e1.yz()){


java.lang.String st2=wl.hq(url,post,"utf-8",null,true,null,20000,20000,null);

if(zf.dy(st2,null)){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb3).zf("网络异常");


}

});
show(1);


}
else{


try{


java.lang.String code=e1.readJson(st2,"code");

if(zf.dy(code,"200")){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb3).zf("已拒绝授权");


}

});
show(1);


}

else if(zf.dy(code,"10")){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb3).zf("请求超时");


}

});
show(1);


}
else{

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb3).zf("授权失败");


}

});
show(1);


}


}catch(java.lang.Throwable __$_e__){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb3).zf("授权失败");


}

});
show(1);


}


}


}


}

});

}

public void show(int i_){

final int i=i_;

final i.runlibrary.app.v.xxbj xxbj1=st.xxbj(open.cn.awg.pro.R.id.xxbj1);

final i.runlibrary.app.v.xxbj xxbj4=st.xxbj(open.cn.awg.pro.R.id.xxbj4);

final i.runlibrary.app.v.xxbj xxbj5=st.xxbj(open.cn.awg.pro.R.id.xxbj5);
gj.jmxc(new java.lang.Runnable(){

public void run(){


if(i==0){

xxbj1.kjd(0);
xxbj4.kjd(8);
xxbj5.kjd(8);


}

else if(i==1){

xxbj1.kjd(8);
xxbj4.kjd(0);
xxbj5.kjd(8);


}

else if(i==2){

xxbj1.kjd(8);
xxbj4.kjd(8);
xxbj5.kjd(0);


}


}

});

}

public void zh(){
gj.jmxc(new java.lang.Runnable(){

public void run(){


java.lang.String te1="";

java.lang.String te2="";

java.lang.String te3="";

java.lang.String a="/data/user/0/open.cn.awg.pro/settings/account/user";

java.lang.String b="/data/user/0/open.cn.awg.pro/settings/account/pass";

java.lang.String c="/data/user/0/open.cn.awg.pro/settings/account/isAcc";

java.lang.String set2="/data/user/0/open.cn.awg.pro/settings/n1/set2.inf";

java.lang.String set3="/data/user/0/open.cn.awg.pro/settings/n1/set3.inf";

java.lang.String set4="/data/user/0/open.cn.awg.pro/settings/n1/set4.inf";

if(wj.cz(a)==false||wj.cz(b)==false){

st.wb(open.cn.awg.pro.R.id.wb10).zf("未登录");
st.wb(open.cn.awg.pro.R.id.wb11).zf("");
st.wb(open.cn.awg.pro.R.id.wb6).zf("");
st.wb(open.cn.awg.pro.R.id.wb6).kjd(8);
st.wb(open.cn.awg.pro.R.id.wb11).kjd(8);
st.tx(open.cn.awg.pro.R.id.tx4).kjd(0);
st.tx(open.cn.awg.pro.R.id.tx7).kjd(8);
st.wb(open.cn.awg.pro.R.id.wb3).zf("请先登录您的账户后再操作");
show(1);


}
else{

te1=wj.dqwb(set2);
st.wb(open.cn.awg.pro.R.id.wb10).zf(te1);
st.wb(open.cn.awg.pro.R.id.wb11).zf("UID:"+wj.dqwb(a));
st.wb(open.cn.awg.pro.R.id.wb6).zf("正");
st.wb(open.cn.awg.pro.R.id.wb6).kjd(8);
st.wb(open.cn.awg.pro.R.id.wb11).kjd(0);
st.tx(open.cn.awg.pro.R.id.tx4).kjd(0);

if(!zf.dy(easy.getPerIcon(wj.dqwb(a)),"null")){

st.tx(open.cn.awg.pro.R.id.tx7).tx(easy.getPerIconPath(wj.dqwb(a)));
st.tx(open.cn.awg.pro.R.id.tx7).kjd(0);


}

java.lang.String set1="/data/user/0/open.cn.awg.pro/settings/n1/set1.inf";

if(zf.dy(wj.dqwb(set1),"true")){

show(0);


}
else{

st.wb(open.cn.awg.pro.R.id.wb3).zf("此账户不允许授权至第三方应用");
show(1);


}


}


}

});

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

public boolean onKeyDown(int kc, android.view.KeyEvent ke){

if(kc==4){



}
return false;

}

public void onDestroy(){
super.onDestroy();
open.cn.awg.pro.e6.getInstance().removeActivity(lei);

}

private void $_onClick_i4d951cdb0e(android.view.View vw){
sq_false();

}

private void $_onClick_i8217d42a17(android.view.View vw){
sq_true();

}

private void $_onClick_i34dc7bf20a(android.view.View vw){
gj.gb();

}

private void $_onClick_i6f2c7751cf(android.view.View vw){
gj.xc(new java.lang.Thread(){

public void run(){


final java.lang.String ca="/data/user/0/open.cn.awg.pro/cache/req/exit";
wj.xrwb(ca,"true");
isExit=true;
show(2);
gj.zt(500);
gj.gb();


}

});

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
private android.view.View.OnClickListener $_on_setOnClickListener_i6f2c7751cf=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i6f2c7751cf(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i34dc7bf20a(vw);
}

};
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

public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw){

android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);

android.widget.ImageView i11aeec1890 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx4);

android.widget.ImageView i3621e3f4a1 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx7);

android.widget.LinearLayout i3f6c9f386b = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj8);

android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);
i8217d42a17.setOnClickListener($_on_setOnClickListener_i8217d42a17);

android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

android.widget.ImageView i6f2c7751cf = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx6);
i6f2c7751cf.setOnClickListener($_on_setOnClickListener_i6f2c7751cf);

__layoutIsLoaded(ay, vw);
}

    public final n3 lei = this, 类 = this;
}
