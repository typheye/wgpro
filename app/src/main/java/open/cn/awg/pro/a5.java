package open.cn.awg.pro;

import i.app.iActivity;
 import com.tananaev.adblib.AdbConnection; import android.content.Intent; import android.os.Bundle; import android.content.Context; import android.net.Uri; import android.graphics.drawable.Drawable; import android.content.pm.ActivityInfo; import android.view.WindowManager; import android.view.Window; import android.os.Build; import android.view.View; import java.lang.reflect.Field; import java.lang.reflect.Method; import com.ypz.bangscreentools.BangScreenTools;import java.lang.*;

public class a5 extends iActivity {

public void onCreate(android.os.Bundle be){
super.onCreate(be);
setContentView(open.cn.awg.pro.R.layout.a5);
_$_viewAutomaticSettingEvent();
open.cn.awg.pro.e6.getInstance().addActivity(lei);

java.lang.String t=sj.hqtz("url");
t=t.toString();

java.lang.String p=sj.hqtz("path");
p=p.toString();
download(t,p);

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
xt.sbxm(false);

final java.lang.String a2="/data/user/0/open.cn.awg.pro/settings/a3.inf";

java.lang.String a=wj.dqwb(a2);

final int u=Integer.parseInt(wj.dqwb("/data/user/0/open.cn.awg.pro/settings/f17/set1.inf"));

if(zf.dy(a,"4")){

st.xdbj(open.cn.awg.pro.R.id.a5_v).shxtck(true);

java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{

st.xdbj(open.cn.awg.pro.R.id.a5_v).shxtck(false);

        
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

public java.lang.Thread down;

public void download(java.lang.Object lj, java.lang.Object path){

final java.lang.String dl=lj.toString();

final java.lang.String paths=path.toString();
down=gj.xc(new java.lang.Thread(){

public void run(){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.show).zf("加载中");


}

});
gj.zt(1000);
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.show).zf("准备下载");


}

});
gj.zt(250);
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.kp(open.cn.awg.pro.R.id.kp2).kjd(0);
st.wb(open.cn.awg.pro.R.id.show).zf("正在下载");


}

});

int dg=wl.xz(dl,paths,true,null,"utf-8",null,true,null);

java.lang.String bk="";

if(dg==0){

bk="下载成功";


}

else if(dg==1){

bk="已下载";


}

else if(dg==2){

bk="下载失败";


}
else{

bk="未知错误";


}

final java.lang.String df=bk;
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.kp(open.cn.awg.pro.R.id.kp2).kjd(8);
st.wb(open.cn.awg.pro.R.id.show).zf(df);


}

});
gj.zt(250);

if(dg==0||dg==1){

gj.jmxc(new java.lang.Runnable(){

public void run(){

gj.gb();


}

});
e1.tsk("提示","下载成功\n\""+paths+"\"");


}
else{

gj.jmxc(new java.lang.Runnable(){

public void run(){

gj.gb();


}

});
e1.tsk("提示","下载失败");


}


}

});

}

public boolean onKeyDown(int kc, android.view.KeyEvent ke){
return true;

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

private void $_onClick_i4d951cdb0e(android.view.View vw){
gj.xc(new java.lang.Thread(){

public void run(){

gj.jmxc(new java.lang.Runnable(){

public void run(){


java.lang.String[] name=new java.lang.String[]{"Msg"};

java.lang.String[] value=new java.lang.String[]{"中断成功 点击重启"};
gj.tz(a0.class,name,value);


}

});


}

});

}

private void $_onClick_i34dc7bf20a(android.view.View vw){
gj.gb();

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
private android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i34dc7bf20a(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i4d951cdb0e=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i4d951cdb0e(vw);
}

};

public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw){

android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);

android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

android.support.v7.widget.CardView i4746b35274 = (android.support.v7.widget.CardView) findViewById(ay, vw, open.cn.awg.pro.R.id.kp2);

android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

__layoutIsLoaded(ay, vw);
}

    public final a5 lei = this, 类 = this;
}
