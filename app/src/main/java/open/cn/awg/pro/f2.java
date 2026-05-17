package open.cn.awg.pro;

import i.app.iActivity;
 import android.content.pm.PackageInfo; import android.content.pm.PackageManager; import android.content.pm.ActivityInfo; import android.view.WindowManager; import android.view.Window; import android.os.Build; import android.view.View; import java.lang.reflect.Field; import java.lang.reflect.Method; import com.ypz.bangscreentools.BangScreenTools;import java.lang.*;

public class f2 extends iActivity {

public void onCreate(android.os.Bundle be){
super.onCreate(be);
setContentView(open.cn.awg.pro.R.layout.f2);
_$_viewAutomaticSettingEvent();
open.cn.awg.pro.e6.getInstance().addActivity(lei);

int b;
b=com.demo.e4.getVersionCode(lei);

int c=b;
st.wb(open.cn.awg.pro.R.id.wb3).zf(c);
yz();

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

st.xdbj(open.cn.awg.pro.R.id.f2_v).shxtck(true);

java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{

st.xdbj(open.cn.awg.pro.R.id.f2_v).shxtck(false);

        
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

public int versionCode;

public java.lang.String versionName;

public int xhsl=0;

public int eggsdj=0;

public open.cn.awg.pro.e1  e1=new e1(_APPINFO);

public void eggs(){
eggsdj=0;

java.lang.String ab="/data/user/0/open.cn.awg.pro/settings/candebug";
wj.xrwb(ab,"true");

com.kingqi.zwcj.活动 a1=com.kingqi.zwcj.控制台.当前活动;

java.lang.String a2=com.kingqi.zwcj.应用工具.获取应用签名(((android.content.Context)(lei)),"open.cn.awg.pro");

java.lang.String a3=com.kingqi.zwcj.日期工具.格式化日期时间(com.kingqi.zwcj.应用工具.获取应用第一次安装日期(((android.content.Context)(lei)),"open.cn.awg.pro"));

java.lang.String a4=com.kingqi.zwcj.日期工具.格式化日期时间(com.kingqi.zwcj.应用工具.获取应用更新日期(((android.content.Context)(lei)),"open.cn.awg.pro"));

java.lang.String a5=com.kingqi.zwcj.应用工具.获取应用的安装市场(((android.content.Context)(lei)),"open.cn.awg.pro");

java.lang.String a6=com.kingqi.zwcj.应用工具.获取应用版本名称(((android.content.Context)(lei)),"open.cn.awg.pro");

int a7=com.kingqi.zwcj.应用工具.获取应用版本号(((android.content.Context)(lei)),"open.cn.awg.pro");

java.lang.String a8=com.kingqi.zwcj.应用工具.获取应用名称(((android.content.Context)(lei)),"open.cn.awg.pro");

java.lang.String sho="[应用名称]\n"+a8+"\n\n[编译版本]\n"+a6+"\n\n[版本号码]\n"+a7+"\n\n[安装日期]\n"+a3+"\n\n[更新日期]\n"+a4;
e1.tsk("应用程序",sho);

}

public void yz(){

}

public void bshow(boolean a){

final boolean i=a;
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb5).kjd(0);

if(i==true){


java.lang.String color=zf.zf(open.cn.awg.pro.R.color.colorTextTrue);
st.wb(open.cn.awg.pro.R.id.wb5).zf("官方应用");
st.wb(open.cn.awg.pro.R.id.wb5).bj(color);


}
else{


java.lang.String color=zf.zf(open.cn.awg.pro.R.color.colorTextFalse);
st.wb(open.cn.awg.pro.R.id.wb5).zf("未知版本");
st.wb(open.cn.awg.pro.R.id.wb5).bj(color);


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

private void $_onClick_d26d5d7080(android.view.View vw){
gj.gb();

}

private void $_onClick_i34dc7bf20a(android.view.View vw){
eggsdj=eggsdj+1;

if(eggsdj==2){

eggs();


}

}

private boolean $_onLongClick_i34dc7bf20a(android.view.View vw){
return true;

}

private void $_onClick_i2202ede8ff(android.view.View vw){

java.lang.String t1=wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a2");
e1.tsk("应用信息",t1);

}

private boolean $_onLongClick_i2202ede8ff(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb16).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb17).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
return true;

}

private void $_onClick_i20c2777fab(android.view.View vw){
gj.tz(f7.class);

}

private boolean $_onLongClick_i20c2777fab(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb12).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb15).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
return true;

}

private void $_onClick_i3167b45174(android.view.View vw){
gj.tz(f9.class);

}

private boolean $_onLongClick_i3167b45174(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb4).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb11).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
return true;

}

private void $_onClick_c0d3e00d2b(android.view.View vw){
e1.goplu("20006","应用备份",false);

}

private boolean $_onLongClick_c0d3e00d2b(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb1).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb6).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
return true;

}

private void $_onClick_i5a93676674(android.view.View vw){
gj.tz(c1.class);

}

private boolean $_onLongClick_i5a93676674(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb21).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb22).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
return true;

}

private void $_onClick_fab146d7a5(android.view.View vw){
gj.tz(j7.class);

}

private boolean $_onLongClick_fab146d7a5(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb19).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb20).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
return true;

}

private void $_onClick_e585d94488(android.view.View vw){
e1.tsk_QR("加入Q群","群号：372091854\n\n扫描二维码 快一步入群","https://qm.qq.com/cgi-bin/qm/qr?k=bU0uOl8kFexph1zLQQOJ74lAEM5C6xfU&authKey=FRi9nwu30PuztHBbVmQ7vbmiia5nd43C0yE2fvAKrP9GLEtltWfxeDMN01mO9RXl&noverify=0&personal_qrcode_source=0");

}

private boolean $_onLongClick_e585d94488(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb9).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb10).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
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
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_e585d94488=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_e585d94488(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_e585d94488=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_e585d94488(vw);
}

};
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_fab146d7a5=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_fab146d7a5(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_fab146d7a5=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_fab146d7a5(vw);
}

};
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_i5a93676674=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_i5a93676674(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i5a93676674=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i5a93676674(vw);
}

};
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_c0d3e00d2b=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_c0d3e00d2b(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_c0d3e00d2b=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_c0d3e00d2b(vw);
}

};
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_i3167b45174=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_i3167b45174(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i3167b45174=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i3167b45174(vw);
}

};
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_i20c2777fab=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_i20c2777fab(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i20c2777fab=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i20c2777fab(vw);
}

};
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_i2202ede8ff=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_i2202ede8ff(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i2202ede8ff=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i2202ede8ff(vw);
}

};
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_i34dc7bf20a=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_i34dc7bf20a(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i34dc7bf20a(vw);
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

android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);
i34dc7bf20a.setOnLongClickListener($_on_setOnLongClickListener_i34dc7bf20a);

android.widget.RelativeLayout i2202ede8ff = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj4);
i2202ede8ff.setOnClickListener($_on_setOnClickListener_i2202ede8ff);
i2202ede8ff.setOnLongClickListener($_on_setOnLongClickListener_i2202ede8ff);

android.widget.RelativeLayout i20c2777fab = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj3);
i20c2777fab.setOnClickListener($_on_setOnClickListener_i20c2777fab);
i20c2777fab.setOnLongClickListener($_on_setOnLongClickListener_i20c2777fab);

android.widget.RelativeLayout i3167b45174 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj2);
i3167b45174.setOnClickListener($_on_setOnClickListener_i3167b45174);
i3167b45174.setOnLongClickListener($_on_setOnLongClickListener_i3167b45174);

android.widget.RelativeLayout c0d3e00d2b = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj5);
c0d3e00d2b.setOnClickListener($_on_setOnClickListener_c0d3e00d2b);
c0d3e00d2b.setOnLongClickListener($_on_setOnLongClickListener_c0d3e00d2b);

android.widget.RelativeLayout i5a93676674 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj9);
i5a93676674.setOnClickListener($_on_setOnClickListener_i5a93676674);
i5a93676674.setOnLongClickListener($_on_setOnLongClickListener_i5a93676674);

android.widget.RelativeLayout fab146d7a5 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj8);
fab146d7a5.setOnClickListener($_on_setOnClickListener_fab146d7a5);
fab146d7a5.setOnLongClickListener($_on_setOnLongClickListener_fab146d7a5);

android.widget.RelativeLayout e585d94488 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj7);
e585d94488.setOnClickListener($_on_setOnClickListener_e585d94488);
e585d94488.setOnLongClickListener($_on_setOnLongClickListener_e585d94488);

android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

__layoutIsLoaded(ay, vw);
}

    public final f2 lei = this, 类 = this;
}
