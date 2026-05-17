package open.cn.awg.pro;

import i.app.iActivity;
 import android.graphics.Bitmap; import java.lang.Integer; import android.content.pm.ActivityInfo; import android.view.WindowManager; import android.view.Window; import android.os.Build; import android.view.View; import java.lang.reflect.Field; import java.lang.reflect.Method; import com.ypz.bangscreentools.BangScreenTools; import android.content.Intent; import android.net.Uri;import java.lang.*;

public class o1 extends iActivity {

public void onCreate(android.os.Bundle be){
super.onCreate(be);
setContentView(open.cn.awg.pro.R.layout.o1);
_$_viewAutomaticSettingEvent();
open.cn.awg.pro.e6.getInstance().addActivity(lei);
cp_name=sj.hqtz("name");
cp_jg=Double.parseDouble(sj.hqtz("jg"));
cp_text=sj.hqtz("text");
cp_id=sj.hqtz("id");
zt(0);
payconf();
show(0);

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

st.xdbj(open.cn.awg.pro.R.id.o1_v).shxtck(true);

java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{

st.xdbj(open.cn.awg.pro.R.id.o1_v).shxtck(false);

        
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

public double cp_jg=((double)(0));

public java.lang.String cp_name="";

public java.lang.String cp_text="";

public java.lang.String cp_time="";

public java.lang.String cp_id="";

public java.lang.String oback="";

public java.lang.String nopay_why2="";

public open.cn.awg.pro.e1  e1=new e1(_APPINFO);

public boolean zfxh=true;

public boolean zfqx=false;

public boolean st_zf0=false;

public boolean st_payconf=false;

public boolean st_zf0_xh=false;

public java.lang.String pay_cache="$cache/pay/pay_id";

public java.lang.String url="https://pay.typheye.cn";

public java.lang.String url2="https://service.typheye.cn";

public void show(int i){

if(i==0){

gj.jmxc(new java.lang.Runnable(){

public void run(){

xt.sbxm(true);
st.wb(open.cn.awg.pro.R.id.Tab).zf("< 产品购买");
st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(0);
st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj5).kjd(8);


}

});


}

else if(i==1){

gj.jmxc(new java.lang.Runnable(){

public void run(){

xt.sbxm(true);
st.wb(open.cn.awg.pro.R.id.Tab).zf("产品购买");
st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(0);
st.xxbj(open.cn.awg.pro.R.id.xxbj5).kjd(8);


}

});


}

else if(i==2){

gj.jmxc(new java.lang.Runnable(){

public void run(){

xt.sbxm(false);
st.wb(open.cn.awg.pro.R.id.Tab).zf("产品购买");
st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj6).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj5).kjd(0);


}

});


}

}

public void payconf(){

final i.runlibrary.app.v.xdbj xdbj7=st.xdbj(open.cn.awg.pro.R.id.xdbj7);

final i.runlibrary.app.v.xdbj xdbj8=st.xdbj(open.cn.awg.pro.R.id.xdbj8);

final i.runlibrary.app.v.wb wb18=st.wb(open.cn.awg.pro.R.id.wb18);

if(st_payconf==false){

st_payconf=true;
gj.xc(new java.lang.Thread(){

public void run(){

gj.jmxc(new java.lang.Runnable(){

public void run(){

xdbj7.kjd(8);
xdbj8.kjd(8);
wb18.kjd(0);


}

});

java.lang.String set2="/data/user/0/open.cn.awg.pro/settings/f7/Enable";

if(e1.yz()==true){


if(zf.dy(cp_name,"")||zf.dy(cp_text,"")){

payconf$error("");


}

else if(cp_jg==0){

gj.zt(1500);
show(0);
zt(1);
e1.zfwc(cp_id);


}
else{


java.lang.String back=wl.hq(url2+"/app/open.cn.awg.pro/pay_config_v3.json",null,"utf-8",null,true,null,20000,20000,null);

if(zf.dy(back,"")||zf.dy(back,null)){

payconf$error("");


}
else{


try{


java.lang.String can_pay=e1.readJson(back,"can_pay");

java.lang.String nopay_why=e1.readJson(back,"nopay_why");

if(zf.dy(can_pay,"1")){

gj.jmxc(new java.lang.Runnable(){

public void run(){

xdbj8.kjd(8);
xdbj7.kjd(0);
wb18.kjd(8);


}

});
st_payconf=false;


}
else{

payconf$error(nopay_why);


}


}catch(java.lang.Throwable __$_e__){

payconf$error("");


}


}


}


}
else{

payconf$error("");


}
st_payconf=false;


}

});


}
else{

e1.tsk("提示","操作过于频繁，请稍后重试");


}

}

public void payconf$error(java.lang.Object t){
st_payconf=false;

final i.runlibrary.app.v.wb wb18=st.wb(open.cn.awg.pro.R.id.wb18);

final i.runlibrary.app.v.xdbj xdbj8=st.xdbj(open.cn.awg.pro.R.id.xdbj8);

if(zf.dy(t,"")){

nopay_why2="获取支付方式失败\n网络异常，请稍后重试";


}
else{

nopay_why2=String.valueOf(t);


}
gj.jmxc(new java.lang.Runnable(){

public void run(){

wb18.kjd(8);
xdbj8.kjd(0);


}

});

}

public void sendPayInfo(java.lang.Object a, java.lang.Object b, java.lang.Object c, java.lang.Object d){

final java.lang.Object j1=a;

final java.lang.Object j2=b;

final java.lang.Object j3=c;

final java.lang.Object j4=d;
gj.xc(new java.lang.Thread(){

public void run(){


java.lang.String urls="";

final java.lang.String set2="/data/user/0/open.cn.awg.pro/settings/f11/set2.inf";
urls=e1.urlUnlockString(wj.dqwb(set2));

if(zf.cjw(urls,"/")){



}
else{

urls=urls+"/";


}

java.lang.String ljf=wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a17");

java.lang.String idget=urls+"class/api.php?type=release"+ljf+"id=600";

if(zf.dy(j1,"")||zf.dy(j2,"")||zf.dy(j3,"")||zf.dy(j4,"")){



}
else{


java.lang.String namex0="用户交易";

final java.lang.String namex=namex0;

java.lang.String xxk="[产品标识] "+j1+"\n[产品价格] "+j2+"\n[交易方式] "+j3+"\n[交易标识] "+j4;

final java.lang.String xx=xxk;

final java.lang.String url=idget;

java.lang.String setback="";

java.lang.String[] wlhq1=new java.lang.String[]{"name="+namex,"value="+xx,"code=baseTextUpload"};

java.lang.String back=wl.hq(url,wlhq1,"utf-8",null,true,null,20000,20000,null);

if(zf.dy(back,"")||zf.dy(back,null)){

setback="0";


}
else{


if(zf.dy(setback,"0")){

setback="0";


}
else{

setback=back;


}


}

if(zf.dy(setback,"0")){



}
else{


if(zf.dy(setback,"true")){



}
else{



}


}


}


}

});

}

public void zt(int a){

final int i=a;
gj.jmxc(new java.lang.Runnable(){

public void run(){


if(i==0){


java.lang.String cp_jg_;
cp_jg_=com.demo.e4.doubleToString(cp_jg);
st.wb(open.cn.awg.pro.R.id.wb10).zf("¥"+cp_jg_);
st.wb(open.cn.awg.pro.R.id.wb11).zf("待支付");
st.wb(open.cn.awg.pro.R.id.wb11).kjd(0);
st.tx(open.cn.awg.pro.R.id.tx4).tx(open.cn.awg.pro.R.mipmap.a27);

java.lang.String color=zf.zf(open.cn.awg.pro.R.color.colorAccent);
st.kp(open.cn.awg.pro.R.id.kp4).kpbjys(color);
st.xdbj(open.cn.awg.pro.R.id.xdbj6).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj7).kjd(0);


}

else if(i==1){

st.wb(open.cn.awg.pro.R.id.wb10).zf("支付成功");
st.wb(open.cn.awg.pro.R.id.wb11).zf("");
st.wb(open.cn.awg.pro.R.id.wb11).kjd(8);
st.tx(open.cn.awg.pro.R.id.tx4).tx(open.cn.awg.pro.R.mipmap.a28);

java.lang.String color=zf.zf(open.cn.awg.pro.R.color.colorTextTrue);
st.kp(open.cn.awg.pro.R.id.kp4).kpbjys(color);
st.xdbj(open.cn.awg.pro.R.id.xdbj6).kjd(8);
st.xxbj(open.cn.awg.pro.R.id.xxbj7).kjd(8);


}

else if(i==2){

st.wb(open.cn.awg.pro.R.id.wb10).zf("支付失败");
st.wb(open.cn.awg.pro.R.id.wb11).zf("");
st.wb(open.cn.awg.pro.R.id.wb11).kjd(8);
st.tx(open.cn.awg.pro.R.id.tx4).tx(open.cn.awg.pro.R.mipmap.a29);

java.lang.String color=zf.zf(open.cn.awg.pro.R.color.colorTextFalse);
st.kp(open.cn.awg.pro.R.id.kp4).kpbjys(color);
st.xdbj(open.cn.awg.pro.R.id.xdbj6).kjd(0);
st.xxbj(open.cn.awg.pro.R.id.xxbj7).kjd(8);


}


}

});

}

public void zf0(){

if(st_zf0==false){

st_zf0=true;

final java.lang.String type="default";
gj.xc(new java.lang.Thread(){

public void run(){

show(1);
gj.zt(500);

java.lang.String set2="/data/user/0/open.cn.awg.pro/settings/f7/Enable";

if(e1.yz()==true){


if(zf.dy(cp_name,"")||cp_jg==0||zf.dy(cp_text,"")){

oback="支付失败:参数错误";
e1.tsk("提示",oback);
show(0);
zt(2);


}
else{


java.lang.String back=wl.hq(url2+"/app/open.cn.awg.pro/pay_config_v3.json",null,"utf-8",null,true,null,20000,20000,null);

if(zf.dy(back,"")||zf.dy(back,null)){

oback="支付失败:连接服务器超时";
e1.tsk("提示",oback);
show(0);
zt(2);


}
else{


try{


java.lang.String pid="awg";

java.lang.String ljf=wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a17");

java.lang.String cp_jg_;
cp_jg_=com.demo.e4.doubleToString(cp_jg);

final java.lang.String otn="D"+xt.sj(4)+sj.zh().zstring(zf.sjs(10000000,99999999));

java.lang.String lock="money="+cp_jg_+"&name="+cp_name+"&id="+cp_id+"&out_trade_no="+otn+"&pid="+pid+"&type="+type;

java.lang.String get="/api/v3/?"+lock+"&sign="+sj.md5(lock);
back=wl.hq(url+get,null,"utf-8",null,true,null,20000,20000,null);

if(zf.dy(back,"")||zf.dy(back,null)){

oback="支付失败:连接服务器超时";
e1.tsk("提示",oback);
show(0);
zt(2);


}
else{


java.lang.String ce=e1.readJson(back,"code");

if(zf.dy(ce,"200")){


try{


i.runlibrary.app.zf$json jo=zf.json(back);

org.json.JSONObject json=jo.json;

java.lang.Object v_id=jo.hq(json,"trade_no");

java.lang.Object v_v=jo.hq(json,"qrcode");

if(!zf.dy(v_id,"")&&!zf.dy(v_v,"")){

zf0_xh(v_v,v_id,type);


}
else{

oback="支付失败:出现异常";
e1.tsk("提示",oback);
show(0);
zt(2);


}


}catch(java.lang.Throwable e){

oback="支付失败:出现异常";
e1.tsk("提示",oback);
show(0);
zt(2);


}


}
else{

oback="支付失败";
e1.tsk("提示",oback);
show(0);
zt(2);


}


}


}catch(java.lang.Throwable __$_e__){

oback="支付失败:出现异常";
e1.tsk("提示",oback);
show(0);
zt(2);


}


}


}


}
else{

oback="支付失败:支付环境检测结果异常(-1)";
e1.tsk("提示",oback);
show(0);
zt(2);


}
st_zf0=false;


}

});


}
else{

e1.tsk("提示","操作过于频繁，请稍后重试");


}

}

public void zf0_xh(java.lang.Object v_v_, java.lang.Object v_id_, java.lang.Object type_){

if(st_zf0_xh==false){

st_zf0_xh=true;

final java.lang.Object v_v=v_v_;

final java.lang.Object v_id=v_id_;

final java.lang.Object type=type_;
gj.xc(new java.lang.Thread(){

public void run(){

zfxh=true;
zfqx=false;
show(2);
zt(0);
ewm(String.valueOf(v_v));
wj.xrwb(pay_cache,v_id);

while(zfxh){

gj.zt(3000);

if(zfqx==true){

zfxh=false;
oback="支付失败:用户取消支付";
e1.tsk("提示",oback);
show(0);
zt(2);
break;


}

java.lang.String ljf=wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a17");

java.lang.String id=wj.dqwb(pay_cache);

java.lang.String send="/api/v3/reback/?trade_no="+id;

java.lang.String back=wl.hq(url+send,"utf-8");

if(zf.dy(back,"")||zf.dy(back,null)){

zfxh=false;
oback="支付失败:连接服务器超时";
e1.tsk("提示",oback);
show(0);
zt(2);
break;


}
else{


java.lang.String ce=e1.readJson(back,"code");

if(zf.dy(ce,"200")){


try{


i.runlibrary.app.zf$json jo=zf.json(back);

org.json.JSONObject json=jo.json;

final java.lang.String v_status=sj.zh().zstring(jo.hq(json,"status"));

if(zf.dy(v_status,"1")){

show(0);
zt(1);
sendPayInfo(cp_id,cp_jg,type,v_id);
zfxh=false;
e1.zfwc(cp_id);
break;


}

else if(zf.dy(v_status,"-1")){

zfxh=false;
oback="支付失败:超时支付";
e1.tsk("提示",oback);
show(0);
zt(2);


}

else if(zf.dy(v_status,"-2")){

zfxh=false;
oback="支付失败:订单已关闭";
e1.tsk("提示",oback);
show(0);
zt(2);
break;


}


}catch(java.lang.Throwable e){

zfxh=false;
oback="支付失败:出现异常";
e1.tsk("提示",oback);
show(0);
zt(2);
break;


}


}
else{



}


}


}
st_zf0_xh=false;


}

});


}
else{

zfxh=false;
oback="支付失败:操作过于频繁，请稍后重试";
e1.tsk("提示",oback);
show(0);
zt(2);


}

}

public void ewm(java.lang.String url){

final java.lang.String qr=url;
gj.jmxc(new java.lang.Runnable(){

public void run(){


try{


        android.widget.ImageView imageView=(android.widget.ImageView)findViewById(R.id.tx3);
        Bitmap bitmap=com.demo.e4.createQRCodeBitmap(qr, 800, 800,"UTF-8","H", "1");
        imageView.setImageBitmap(bitmap);
      
st.tx(open.cn.awg.pro.R.id.tx3).kjd(0);


}catch(java.lang.Throwable e){

st.tx(open.cn.awg.pro.R.id.tx3).kjd(8);


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

}

public boolean onKeyDown(int kc, android.view.KeyEvent ke){

if(kc==4&&st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd()==0){


if(st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd()==0){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.Tab).zf("< 产品购买");
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
return false;

}

public void onDestroy(){
super.onDestroy();
open.cn.awg.pro.e6.getInstance().removeActivity(lei);

}

private void $_onClick_d26d5d7080(android.view.View vw){

if(zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(),"< 产品购买")&&st.xxbj(open.cn.awg.pro.R.id.xxbj1).kjd()==0){


if(st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd()==0){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.Tab).zf("< 产品购买");
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

private void $_onClick_i3f6c9f386b(android.view.View vw){

if(zf.dy(st.wb(open.cn.awg.pro.R.id.wb10).zf(),"支付成功")){

e1.tsk("提示","您已成功完成支付");


}

else if(zf.dy(st.wb(open.cn.awg.pro.R.id.wb10).zf(),"支付失败")){

e1.tsk("提示",oback);


}
else{

e1.tsk("提示","产品待支付");


}

}

private void $_onClick_i20c2777fab(android.view.View vw){

java.lang.String cp_jg_;
cp_jg_=com.demo.e4.doubleToString(cp_jg);

java.lang.String sho="[产品名称]\n"+cp_name+"\n\n[产品价格]\n¥"+cp_jg_+"\n\n[产品说明]\n"+cp_text;
e1.tsk("产品详情",sho);

}

private boolean $_onLongClick_i20c2777fab(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb4).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb5).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
return true;

}

private void $_onClick_i171cedf1eb(android.view.View vw){
payconf();
zt(0);

}

private boolean $_onLongClick_i171cedf1eb(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb15).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb16).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
return true;

}

private void $_onClick_e585d94488(android.view.View vw){
zf0();

}

private boolean $_onLongClick_e585d94488(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb19).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb20).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
return true;

}

private void $_onClick_fab146d7a5(android.view.View vw){
e1.tsk("产品购买",nopay_why2);
gj.gb();

}

private boolean $_onLongClick_fab146d7a5(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb21).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb22).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
return true;

}

private void $_onClick_i34dc7bf20a(android.view.View vw){
gj.gb();

}

private void $_onClick_i171ea34f1a(android.view.View vw){
zfqx=true;
show(1);

}

private void $_onClick_i4d951cdb0e(android.view.View vw){
gj.gb();

}

private void $_onClick_i4a4248a0f2(android.view.View vw){

if(st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd()==0){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.Tab).zf("< 产品购买");
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

if(st.xxbj(open.cn.awg.pro.R.id.xxbj9).kjd()==0){

gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.Tab).zf("< 产品购买");
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
gj.gb();

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
private android.view.View.OnClickListener $_on_setOnClickListener_i4d951cdb0e=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i4d951cdb0e(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i171ea34f1a=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i171ea34f1a(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i34dc7bf20a=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i34dc7bf20a(vw);
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
private android.view.View.OnLongClickListener $_on_setOnLongClickListener_i171cedf1eb=new android.view.View.OnLongClickListener(){

public boolean onLongClick(android.view.View vw){
return $_onLongClick_i171cedf1eb(vw);
}

};
private android.view.View.OnClickListener $_on_setOnClickListener_i171cedf1eb=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i171cedf1eb(vw);
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

android.widget.RelativeLayout i171cedf1eb = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj6);
i171cedf1eb.setOnClickListener($_on_setOnClickListener_i171cedf1eb);
i171cedf1eb.setOnLongClickListener($_on_setOnLongClickListener_i171cedf1eb);

android.widget.RelativeLayout e585d94488 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj7);
e585d94488.setOnClickListener($_on_setOnClickListener_e585d94488);
e585d94488.setOnLongClickListener($_on_setOnLongClickListener_e585d94488);

android.widget.RelativeLayout fab146d7a5 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj8);
fab146d7a5.setOnClickListener($_on_setOnClickListener_fab146d7a5);
fab146d7a5.setOnLongClickListener($_on_setOnLongClickListener_fab146d7a5);

android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

android.widget.Button i171ea34f1a = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an1);
i171ea34f1a.setOnClickListener($_on_setOnClickListener_i171ea34f1a);

android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

android.widget.ImageView i4a4248a0f2 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx5);
i4a4248a0f2.setOnClickListener($_on_setOnClickListener_i4a4248a0f2);

android.widget.ImageView i6f2c7751cf = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx6);
i6f2c7751cf.setOnClickListener($_on_setOnClickListener_i6f2c7751cf);

__layoutIsLoaded(ay, vw);
}

    public final o1 lei = this, 类 = this;
}
