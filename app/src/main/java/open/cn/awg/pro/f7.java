package open.cn.awg.pro;

import i.app.iActivity;
 import android.graphics.Bitmap; import java.lang.Integer; import android.content.pm.ActivityInfo; import android.view.WindowManager; import android.view.Window; import android.os.Build; import android.view.View; import java.lang.reflect.Field; import java.lang.reflect.Method; import com.ypz.bangscreentools.BangScreenTools;import java.lang.*;

public class f7 extends iActivity {

public void onCreate(android.os.Bundle be){
super.onCreate(be);
setContentView(open.cn.awg.pro.R.layout.f7);
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

st.xdbj(open.cn.awg.pro.R.id.f7_v).shxtck(true);

java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{

st.xdbj(open.cn.awg.pro.R.id.f7_v).shxtck(false);

        
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

public boolean isJh=false;

public open.cn.awg.pro.e1  e1=new e1(_APPINFO);

public void jz(){

final java.lang.String set1="/data/user/0/open.cn.awg.pro/settings/f7/Enable2";
gj.jmxc(new java.lang.Runnable(){

public void run(){


java.lang.String te1=wj.dqwb(set1);

if(zf.dy(wj.dqwb(set1),"")||wj.cz(set1)==false){

st.wb(open.cn.awg.pro.R.id.wb5).zf("请输入15位产品ID");


}
else{

st.wb(open.cn.awg.pro.R.id.wb5).zf(te1);


}


}

});
zh();

}

public void show(int i){

if(i==0){

qh_();
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);
st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(8);


}

});


}

else if(i==1){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(0);


}

});


}

}

public void zh(){
gj.jmxc(new java.lang.Runnable(){

public void run(){


java.lang.String a="/data/user/0/open.cn.awg.pro/settings/f7/Enable2";

if(wj.cz(a)!=false&&!zf.dy(wj.dqwb(a),"")){

st.wb(open.cn.awg.pro.R.id.wb10).zf("已激活");
st.wb(open.cn.awg.pro.R.id.wb11).zf("");
st.wb(open.cn.awg.pro.R.id.wb11).kjd(8);
st.tx(open.cn.awg.pro.R.id.tx4).tx(open.cn.awg.pro.R.mipmap.a28);

java.lang.String color=zf.zf(open.cn.awg.pro.R.color.colorTextTrue);
st.kp(open.cn.awg.pro.R.id.kp4).kpbjys(color);
st.xdbj(open.cn.awg.pro.R.id.xdbj4).kjd(8);
isJh=true;


}
else{

st.wb(open.cn.awg.pro.R.id.wb10).zf("未激活");
st.wb(open.cn.awg.pro.R.id.wb11).zf("一些功能将无法使用");
st.wb(open.cn.awg.pro.R.id.wb11).kjd(0);
st.tx(open.cn.awg.pro.R.id.tx4).tx(open.cn.awg.pro.R.mipmap.a29);

java.lang.String color=zf.zf(open.cn.awg.pro.R.color.colorTextFalse);
st.kp(open.cn.awg.pro.R.id.kp4).kpbjys(color);
st.xdbj(open.cn.awg.pro.R.id.xdbj4).kjd(0);
isJh=false;


}


}

});

}

public void load(java.lang.Object url, java.lang.Object title){

java.lang.String[] name=new java.lang.String[]{"url","title","code"};

java.lang.Object[] value=new java.lang.Object[]{url,title,"super1"};
gj.tz(d2.class,name,value);

}

public void qh(){
gj.jmxc(new java.lang.Runnable(){

public void run(){


java.lang.String set2="/data/user/0/open.cn.awg.pro/settings/f7/Enable2";

if(st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd()==0){


java.lang.String z="";
st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(0);

if(mode==1){

z=st.wb(open.cn.awg.pro.R.id.wb5).zf();
st.wb(open.cn.awg.pro.R.id.Tab).zf("产品ID");
st.bjk(open.cn.awg.pro.R.id.bjk1).tszf("请输入15位产品ID");
st.bjk(open.cn.awg.pro.R.id.bjk1).wblx("number");
st.bjk(open.cn.awg.pro.R.id.bjk1).xzxscd(15);


}
st.bjk(open.cn.awg.pro.R.id.bjk1).zf(wj.dqwb(set2));


}
else{

st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);
st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(8);
st.bjk(open.cn.awg.pro.R.id.bjk1).zf("");
st.wb(open.cn.awg.pro.R.id.Tab).zf("< 应用激活");


}


}

});

}

public void qh_(){
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);
st.xxbj(open.cn.awg.pro.R.id.xxbj4).kjd(8);
st.bjk(open.cn.awg.pro.R.id.bjk1).zf("");
st.wb(open.cn.awg.pro.R.id.Tab).zf("< 应用激活");


}

});

}

public void gx(){
gj.xc(new java.lang.Thread(){

public void run(){

show(1);
gj.zt(500);

java.lang.String set2="/data/user/0/open.cn.awg.pro/settings/f7/Enable2";

java.lang.String b=st.bjk(open.cn.awg.pro.R.id.bjk1).zf();
b=zf.qctwkg(b);

if(mode==1){


if(b.length()!=15){

e1.tsk("提示","产品ID不符合规范");
show(0);


}
else{


if(e1.yz()==true){


java.lang.String urls="/data/user/0/open.cn.awg.pro/settings/f10/set2.inf";
urls=wj.dqwb(urls);
urls=e1.urlUnlockString(urls);

if(zf.cjw(urls,"/")){



}
else{

urls=urls+"/";


}

java.lang.String url=urls+"open.php?token="+b;

java.lang.String back=wl.hq(url,null,"utf-8",null,true,null,20000,20000,null);

if(zf.dy(back,"")||zf.dy(back,null)){

e1.tsk("提示","验证产品ID失败:连接服务器超时(-1)");
show(0);


}
else{


if(zf.dy(back,"true")){

wj.xrwb(set2,b);
show(0);
jz();


}
else{

e1.tsk("提示","验证产品ID失败:该产品ID不存在");
show(0);


}


}


}
else{

e1.tsk("提示","验证产品ID失败:连接服务器超时(-2)");
show(0);


}


}


}


}

});

}

public void ksjh(){
gj.xc(new java.lang.Thread(){

public void run(){

show(1);
gj.zt(500);

java.lang.String urls="/data/user/0/open.cn.awg.pro/settings/f10/set2.inf";
urls=wj.dqwb(urls);
urls=e1.urlUnlockString(urls);

if(zf.cjw(urls,"/")){



}
else{

urls=urls+"/";


}

java.lang.String url=urls+"vip.json";

if(e1.yz()){


java.lang.String back=wl.hq(url,null,"utf-8",null,true,null,20000,20000,null);

if(zf.dy(back,"")||zf.dy(back,null)){

e1.tsk("提示","连接服务器超时");
show(0);


}
else{


try{


java.lang.String oldenable=e1.readJson(back,"OldEnable");

java.lang.String money=e1.readJson(back,"Money");

double money_=sj.zh(money).zdouble(((double)(0)));

java.lang.String h_y=e1.readJson(back,"Act_Y");

int h_y_=sj.zh(h_y).zint(0);

java.lang.String h_m=e1.readJson(back,"Act_M");

int h_m_=sj.zh(h_m).zint(0);

java.lang.String h_d=e1.readJson(back,"Act_D");

int h_d_=sj.zh(h_d).zint(0);

java.lang.String a3=com.kingqi.zwcj.日期工具.格式化日期时间(com.kingqi.zwcj.应用工具.获取应用第一次安装日期(((android.content.Context)(lei)),"open.cn.awg.pro"));

java.lang.String y=zf.qc(a3,0,4);

java.lang.String m=zf.qc(a3,5,7);

java.lang.String d=zf.qc(a3,8,10);

int y_=sj.zh(y).zint(9999);

int m_=sj.zh(m).zint(99);

int d_=sj.zh(d).zint(99);

final java.lang.String[] name=new java.lang.String[]{"id","name","jg","text"};

final java.lang.Object[] value=new java.lang.Object[]{"a1","腕管Pro-产品ID",money_,"用以激活腕管Pro的产品ID，一经售出，概不退换！"};

if(zf.dy(oldenable,"true")){


if(y_<=h_y_&&m_<=h_m_&&d_<=h_d_){

e1.jh();
e1.tsk("提示","检测到您为老用户,已为您激活应用");
gj.zt(1000);
show(0);


}
else{

gj.jmxc(new java.lang.Runnable(){

public void run(){

gj.tz(o1.class,name,value);


}

});
gj.zt(1000);
show(0);


}


}
else{

gj.jmxc(new java.lang.Runnable(){

public void run(){

gj.tz(o1.class,name,value);


}

});
gj.zt(1000);
show(0);


}


}catch(java.lang.Throwable e){

e1.upload_error(e,"f7.ksjh()");
show(0);


}


}


}
else{

e1.tsk("提示","应用验证未通过");
show(0);


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
jz();

}

public void onDestroy(){
super.onDestroy();
open.cn.awg.pro.e6.getInstance().removeActivity(lei);

}

private void $_onClick_d26d5d7080(android.view.View vw){

if(zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(),"< 应用激活")){

gj.gb();


}

}

private void $_onClick_i3f6c9f386b(android.view.View vw){

if(isJh==false){

mode=1;
qh();


}
else{


if(st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd()==0){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.Tab).zf("< 应用激活");
st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);


}

});


}
else{

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.Tab).zf("提示");
st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(0);
st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);


}

});


}


}

}

private void $_onClick_i20c2777fab(android.view.View vw){

if(zf.dy(st.wb(open.cn.awg.pro.R.id.wb5).zf(),"请输入15位产品ID")){

mode=1;
qh();


}
else{

e1.tsk("产品ID",st.wb(open.cn.awg.pro.R.id.wb5).zf());


}

}

private boolean $_onLongClick_i20c2777fab(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb4).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb5).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
return true;

}

private void $_onClick_i3167b45174(android.view.View vw){
load("/data/user/0/open.cn.awg.pro/data/assets/e3","功能帮助");

}

private boolean $_onLongClick_i3167b45174(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb2).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb3).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
return true;

}

private void $_onClick_i2202ede8ff(android.view.View vw){
ksjh();

}

private boolean $_onLongClick_i2202ede8ff(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb7).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb8).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
return true;

}

private void $_onClick_i34dc7bf20a(android.view.View vw){
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

private void $_onClick_i4d951cdb0e(android.view.View vw){
qh();

}

private void $_onClick_i8217d42a17(android.view.View vw){
gx();

}

private void $_onClick_i4a4248a0f2(android.view.View vw){

if(st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd()==0){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.Tab).zf("< 应用激活");
st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);


}

});


}
else{

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.Tab).zf("提示");
st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(0);
st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);


}

});


}

}

private void $_onClick_i6f2c7751cf(android.view.View vw){

java.lang.String a="/data/user/0/open.cn.awg.pro/settings/f7/Enable2";
wj.xrwb(a,"");
jz();

if(st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd()==0){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.Tab).zf("< 应用激活");
st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);


}

});


}
else{

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.Tab).zf("提示");
st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(0);
st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);


}

});


}

}

private void _$_viewAutomaticSettingEvent(){
_$_viewAutomaticSettingEvent(this, null);
}
private android.view.View.OnClickListener $_on_setOnClickListener_i6f2c7751cf=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i6f2c7751cf(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i4a4248a0f2=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i4a4248a0f2(vw);
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
private android.view.View.OnTouchListener $_on_setOnTouchListener_i32ee139ba1=new android.view.View.OnTouchListener(){

public boolean onTouch(android.view.View vw, android.view.MotionEvent me){
return $_onTouch_i32ee139ba1(vw, me);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i34dc7bf20a(vw);
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
private android.view.View.OnClickListener $_on_setOnClickListener_i3f6c9f386b=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i3f6c9f386b(vw);
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

android.widget.ImageView i11aeec1890 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx4);

android.widget.LinearLayout i3f6c9f386b = (android.widget.LinearLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xxbj8);
i3f6c9f386b.setOnClickListener($_on_setOnClickListener_i3f6c9f386b);

android.widget.RelativeLayout i20c2777fab = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj3);
i20c2777fab.setOnClickListener($_on_setOnClickListener_i20c2777fab);
i20c2777fab.setOnLongClickListener($_on_setOnLongClickListener_i20c2777fab);

android.widget.RelativeLayout i3167b45174 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj2);
i3167b45174.setOnClickListener($_on_setOnClickListener_i3167b45174);
i3167b45174.setOnLongClickListener($_on_setOnLongClickListener_i3167b45174);

android.widget.RelativeLayout i2202ede8ff = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj4);
i2202ede8ff.setOnClickListener($_on_setOnClickListener_i2202ede8ff);
i2202ede8ff.setOnLongClickListener($_on_setOnLongClickListener_i2202ede8ff);

android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

android.widget.EditText i32ee139ba1 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjk1);
i32ee139ba1.setOnTouchListener($_on_setOnTouchListener_i32ee139ba1);

android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);
i8217d42a17.setOnClickListener($_on_setOnClickListener_i8217d42a17);

android.widget.ImageView i4a4248a0f2 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx5);
i4a4248a0f2.setOnClickListener($_on_setOnClickListener_i4a4248a0f2);

android.widget.ImageView i6f2c7751cf = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx6);
i6f2c7751cf.setOnClickListener($_on_setOnClickListener_i6f2c7751cf);

__layoutIsLoaded(ay, vw);
}

    public final f7 lei = this, 类 = this;
}
