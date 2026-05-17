package open.cn.awg.pro;

import i.app.iActivity;
 import android.view.Window; import com.ypz.bangscreentools.BangScreenTools; import java.io.File; import android.view.View;import java.lang.*;

public class l5_a1_fuwuq_d0313_y2022 extends iActivity {

public void onCreate(android.os.Bundle be){
super.onCreate(be);
setContentView(open.cn.awg.pro.R.layout.l5_a1_fuwuq_d0313_y2022);
_$_viewAutomaticSettingEvent();
open.cn.awg.pro.e6.getInstance().addActivity(lei);
show(0);

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

st.xdbj(open.cn.awg.pro.R.id.l5_a1_fuwuq_d0313_y2022_v).shxtck(true);

java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{

st.xdbj(open.cn.awg.pro.R.id.l5_a1_fuwuq_d0313_y2022_v).shxtck(false);

        
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

i.runlibrary.app.v.xlcd xlcd1=st.xlcd(open.cn.awg.pro.R.id.xlcd1);

java.lang.String[] sz=new java.lang.String[]{"GET方式","POST提交数据方式","POST提交JSON数据方式"};
xlcd1.csh(sz);

i.runlibrary.app.v.xlcd xlcd2=st.xlcd(open.cn.awg.pro.R.id.xlcd2);
sz=new java.lang.String[]{"常规模式","高级模式"};
xlcd2.csh(sz);

}

public java.lang.String um="";

public java.io.File f;

public open.cn.awg.pro.e1  e1=new e1(_APPINFO);

public void show(int i){

i.runlibrary.app.v.xxbj x1=st.xxbj(open.cn.awg.pro.R.id.xxbj1);

i.runlibrary.app.v.xxbj x2=st.xxbj(open.cn.awg.pro.R.id.xxbj6);

if(i==0){

x1.kjd(0);
x2.kjd(8);


}

else if(i==1){

x1.kjd(8);
x2.kjd(0);


}

}

public void load(java.lang.Object url, java.lang.Object title){

java.lang.String[] name=new java.lang.String[]{"url","title","code"};

java.lang.Object[] value=new java.lang.Object[]{url,title,"super1"};
gj.tz(d2.class,name,value);

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

private boolean $_onTouch_e45f6690d3(android.view.View vw, android.view.MotionEvent me){

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

private void $_onItemSelected_i004a739c93(android.widget.AdapterView vw, android.view.View view, int pn, long id){

final i.runlibrary.app.v.xxbj xxbj4=st.xxbj(open.cn.awg.pro.R.id.xxbj4);

final i.runlibrary.app.v.xxbj xxbj5=st.xxbj(open.cn.awg.pro.R.id.xxbj5);

if(pn==0){

gj.jmxc(new java.lang.Runnable(){

public void run(){

xxbj4.kjd(8);
xxbj5.kjd(8);


}

});


}

else if(pn==1){

gj.jmxc(new java.lang.Runnable(){

public void run(){

xxbj4.kjd(0);
xxbj5.kjd(8);


}

});


}

else if(pn==2){

gj.jmxc(new java.lang.Runnable(){

public void run(){

xxbj4.kjd(8);
xxbj5.kjd(0);


}

});


}

}

private void $_onNothingSelected_i004a739c93(android.widget.AdapterView vw){

}

private boolean $_onTouch_i8d69eec323(android.view.View vw, android.view.MotionEvent me){

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

private boolean $_onTouch_i956d0f4bd8(android.view.View vw, android.view.MotionEvent me){

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

private void $_onItemSelected_fef1635c8d(android.widget.AdapterView vw, android.view.View view, int pn, long id){

final i.runlibrary.app.v.xxbj xxbj7=st.xxbj(open.cn.awg.pro.R.id.xxbj7);

if(pn==0){

gj.jmxc(new java.lang.Runnable(){

public void run(){

xxbj7.kjd(8);


}

});


}

else if(pn==1){

gj.jmxc(new java.lang.Runnable(){

public void run(){

xxbj7.kjd(0);


}

});


}

}

private void $_onNothingSelected_fef1635c8d(android.widget.AdapterView vw){

}

private void $_onCheckedChanged_i3d918d2dcb(android.widget.CompoundButton vw, boolean ic){

final i.runlibrary.app.v.bjk bjks1=st.bjk(open.cn.awg.pro.R.id.bjks1);

final i.runlibrary.app.v.bjk bjks2=st.bjk(open.cn.awg.pro.R.id.bjks2);

final i.runlibrary.app.v.bjk bjks3=st.bjk(open.cn.awg.pro.R.id.bjks3);

final i.runlibrary.app.v.bjk bjks4=st.bjk(open.cn.awg.pro.R.id.bjks4);

final i.runlibrary.app.v.bjk bjks5=st.bjk(open.cn.awg.pro.R.id.bjks5);

final i.runlibrary.app.v.bjk bjks6=st.bjk(open.cn.awg.pro.R.id.bjks6);

final i.runlibrary.app.v.bjk bjks7=st.bjk(open.cn.awg.pro.R.id.bjks7);

final java.lang.String va1="utf-8";

final java.lang.String va2="name1=value1;name2=value2;";

final java.lang.String va3="true";

final java.lang.String va4="User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept=*/*||accept-language=zh-CN";

final java.lang.String va5="20000";

final java.lang.String va6="20000";

final java.lang.String va7="10.0.0.172:80";

if(ic==true){

gj.jmxc(new java.lang.Runnable(){

public void run(){

bjks1.zf(va1);
bjks2.zf(va2);
bjks3.zf(va3);
bjks4.zf(va4);
bjks5.zf(va5);
bjks6.zf(va6);
bjks7.zf(va7);


}

});


}
else{

gj.jmxc(new java.lang.Runnable(){

public void run(){

bjks1.zf("");
bjks2.zf("");
bjks3.zf("");
bjks4.zf("");
bjks5.zf("");
bjks6.zf("");
bjks7.zf("");


}

});


}

}

private boolean $_onTouch_i2ce2aa6972(android.view.View vw, android.view.MotionEvent me){

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

private boolean $_onTouch_b7e129881e(android.view.View vw, android.view.MotionEvent me){

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

private boolean $_onTouch_d9fcd0aec2(android.view.View vw, android.view.MotionEvent me){

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

private boolean $_onTouch_fc0b80656a(android.view.View vw, android.view.MotionEvent me){

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

private boolean $_onTouch_i8dfdb24277(android.view.View vw, android.view.MotionEvent me){

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

private boolean $_onTouch_i3793cc74e0(android.view.View vw, android.view.MotionEvent me){

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

private boolean $_onTouch_i026fdb816d(android.view.View vw, android.view.MotionEvent me){

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

private void $_onClick_i171ea34f1a(android.view.View vw){

java.lang.String a="";

java.lang.String[] b=new java.lang.String[]{""};

java.lang.String c="utf-8";

java.lang.String d="";

boolean e=true;

java.lang.String f="";

int g=-1;

int h=-1;

java.lang.String i="";

final i.runlibrary.app.v.xlcd xl1=st.xlcd(open.cn.awg.pro.R.id.xlcd1);

final i.runlibrary.app.v.xlcd xl2=st.xlcd(open.cn.awg.pro.R.id.xlcd2);

if(zf.dy(st.bjk(open.cn.awg.pro.R.id.bjkurl).zf(),"")){

e1.tsk("提示","参数:\"服务端链接\"不能为空");


}
else{

a=st.bjk(open.cn.awg.pro.R.id.bjkurl).zf();
a=zf.qctwkg(a);


}

if(xl1.dqzsxmxh()==0){

b=new java.lang.String[]{""};


}

else if(xl1.dqzsxmxh()==1){


if(zf.dy(st.bjk(open.cn.awg.pro.R.id.bjkgp1).zf(),"")){

e1.tsk("提示","参数\"POST提交的数据\"不能为空");


}
else{


java.lang.String fb=st.bjk(open.cn.awg.pro.R.id.bjkgp1).zf();
fb=zf.qctwkg(fb);
b=zf.fg(fb,"\n",true);


}


}

else if(xl1.dqzsxmxh()==2){


if(zf.dy(st.bjk(open.cn.awg.pro.R.id.bjkgp2).zf(),"")){

e1.tsk("提示","参数\"POST提交的JSON数据\"不能为空");


}
else{


java.lang.String fb=st.bjk(open.cn.awg.pro.R.id.bjkgp2).zf();
fb=zf.qctwkg(fb);
b=zf.fg(fb,"\n",true);


}


}

if(xl2.dqzsxmxh()==1){


if(zf.dy(st.bjk(open.cn.awg.pro.R.id.bjks1).zf(),"")){

e1.tsk("提示","参数\"编码\"不能为空");


}
else{

c=st.bjk(open.cn.awg.pro.R.id.bjks1).zf();
c=zf.qctwkg(c);


}
d=st.bjk(open.cn.awg.pro.R.id.bjks2).zf();
d=zf.qctwkg(d);

if(zf.dy(st.bjk(open.cn.awg.pro.R.id.bjks3).zf(),"")){

e1.tsk("提示","参数\"是否自动设置Cookie\"不能为空");


}
else{


java.lang.String fe=st.bjk(open.cn.awg.pro.R.id.bjks3).zf();
fe=zf.qctwkg(fe);

if(zf.dy(fe,"true")){

e=true;


}

else if(zf.dy(fe,"false")){

e=false;


}


}

if(zf.dy(st.bjk(open.cn.awg.pro.R.id.bjks4).zf(),"")){

e1.tsk("提示","参数\"Header头\"不能为空");


}
else{

f=st.bjk(open.cn.awg.pro.R.id.bjks4).zf();
f=zf.qctwkg(f);


}

if(zf.dy(st.bjk(open.cn.awg.pro.R.id.bjks5).zf(),"")){

e1.tsk("提示","参数\"连接超时\"不能为空");


}
else{

g=Integer.parseInt(st.bjk(open.cn.awg.pro.R.id.bjks5).zf());


}

if(zf.dy(st.bjk(open.cn.awg.pro.R.id.bjks6).zf(),"")){

e1.tsk("提示","参数\"接收超时\"不能为空");


}
else{

h=Integer.parseInt(st.bjk(open.cn.awg.pro.R.id.bjks6).zf());


}
i=st.bjk(open.cn.awg.pro.R.id.bjks7).zf();
i=zf.qctwkg(i);


}
else{

d="1";
e=true;
f="1";
g=1;
h=1;
i="1";


}

if(zf.dy(a,"")||zf.dy(c,"")||zf.dy(f,"")||g==-1||h==-1){



}
else{


final java.lang.String s_a=a;

final java.lang.String[] s_b=b;

final java.lang.String s_c=c;

final java.lang.String s_d=d;

final boolean s_e=e;

final java.lang.String s_f=f;

final int s_g=g;

final int s_h=h;

final java.lang.String s_i=i;
gj.xc(new java.lang.Thread(){

public void run(){


java.lang.String back="";
gj.jmxc(new java.lang.Runnable(){

public void run(){

show(1);


}

});

if(xl1.dqzsxmxh()==0){


if(xl2.dqzsxmxh()==0){


try{

back=wl.hq(s_a,null,s_c);


}catch(java.lang.Throwable e){

e1.upload_error(e,"l5_a1_fuwuq_d0313_y2022.*");
gj.jmxc(new java.lang.Runnable(){

public void run(){

show(0);


}

});


}


}

else if(xl2.dqzsxmxh()==1){


try{

back=wl.hq(s_a,null,s_c,s_d,s_e,s_f,s_g,s_h,null);


}catch(java.lang.Throwable e){

e1.upload_error(e,"l5_a1_fuwuq_d0313_y2022.*");
gj.jmxc(new java.lang.Runnable(){

public void run(){

show(0);


}

});


}


}


}

else if(xl1.dqzsxmxh()==1){


if(xl2.dqzsxmxh()==0){


try{

back=wl.hq(s_a,s_b,s_c);


}catch(java.lang.Throwable e){

e1.upload_error(e,"l5_a1_fuwuq_d0313_y2022.*");
gj.jmxc(new java.lang.Runnable(){

public void run(){

show(0);


}

});


}


}

else if(xl2.dqzsxmxh()==1){


try{

back=wl.hq(s_a,s_b,s_c,s_d,s_e,s_f,s_g,s_h,null);


}catch(java.lang.Throwable e){

e1.upload_error(e,"l5_a1_fuwuq_d0313_y2022.*");
gj.jmxc(new java.lang.Runnable(){

public void run(){

show(0);


}

});


}


}


}

else if(xl1.dqzsxmxh()==2){


if(xl2.dqzsxmxh()==0){


try{

back=wl.hqjson(s_a,s_b,s_c);


}catch(java.lang.Throwable e){

e1.upload_error(e,"l5_a1_fuwuq_d0313_y2022.*");
gj.jmxc(new java.lang.Runnable(){

public void run(){

show(0);


}

});


}


}

else if(xl2.dqzsxmxh()==1){


try{

back=wl.hqjson(s_a,s_b,s_c,s_d,s_e,s_f,s_g,s_h,null);


}catch(java.lang.Throwable e){

e1.upload_error(e,"l5_a1_fuwuq_d0313_y2022.*");
gj.jmxc(new java.lang.Runnable(){

public void run(){

show(0);


}

});


}


}


}

final java.lang.String wblj="/data/user/0/open.cn.awg.pro/cache/webbacktext";

final java.lang.String nr=back;
gj.sc(nr);
gj.jmxc(new java.lang.Runnable(){

public void run(){


try{

wj.xrwb(wblj,nr);


}catch(java.lang.Throwable e){

e1.upload_error(e,"l5_a1_fuwuq_d0313_y2022.*");


}
load(wblj,"返回文本");
show(0);


}

});


}

});


}

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
private android.view.View.OnClickListener $_on_setOnClickListener_i171ea34f1a=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i171ea34f1a(vw);
}

};
private android.view.View.OnTouchListener $_on_setOnTouchListener_i026fdb816d=new android.view.View.OnTouchListener(){

public boolean onTouch(android.view.View vw, android.view.MotionEvent me){
return $_onTouch_i026fdb816d(vw, me);
}

};
private android.view.View.OnTouchListener $_on_setOnTouchListener_i3793cc74e0=new android.view.View.OnTouchListener(){

public boolean onTouch(android.view.View vw, android.view.MotionEvent me){
return $_onTouch_i3793cc74e0(vw, me);
}

};
private android.view.View.OnTouchListener $_on_setOnTouchListener_i8dfdb24277=new android.view.View.OnTouchListener(){

public boolean onTouch(android.view.View vw, android.view.MotionEvent me){
return $_onTouch_i8dfdb24277(vw, me);
}

};
private android.view.View.OnTouchListener $_on_setOnTouchListener_fc0b80656a=new android.view.View.OnTouchListener(){

public boolean onTouch(android.view.View vw, android.view.MotionEvent me){
return $_onTouch_fc0b80656a(vw, me);
}

};
private android.view.View.OnTouchListener $_on_setOnTouchListener_d9fcd0aec2=new android.view.View.OnTouchListener(){

public boolean onTouch(android.view.View vw, android.view.MotionEvent me){
return $_onTouch_d9fcd0aec2(vw, me);
}

};
private android.view.View.OnTouchListener $_on_setOnTouchListener_b7e129881e=new android.view.View.OnTouchListener(){

public boolean onTouch(android.view.View vw, android.view.MotionEvent me){
return $_onTouch_b7e129881e(vw, me);
}

};
private android.view.View.OnTouchListener $_on_setOnTouchListener_i2ce2aa6972=new android.view.View.OnTouchListener(){

public boolean onTouch(android.view.View vw, android.view.MotionEvent me){
return $_onTouch_i2ce2aa6972(vw, me);
}

};
private android.widget.CompoundButton.OnCheckedChangeListener $_on_setOnCheckedChangeListener_i3d918d2dcb=new android.widget.CompoundButton.OnCheckedChangeListener(){

public void onCheckedChanged(android.widget.CompoundButton vw, boolean ic){
$_onCheckedChanged_i3d918d2dcb(vw, ic);
}

};
private android.widget.AdapterView.OnItemSelectedListener $_on_setOnItemSelectedListener_fef1635c8d=new android.widget.AdapterView.OnItemSelectedListener(){

public void onItemSelected(android.widget.AdapterView vw, android.view.View view, int pn, long id){
$_onItemSelected_fef1635c8d(vw, view, pn, id);
}

public void onNothingSelected(android.widget.AdapterView vw){
$_onNothingSelected_fef1635c8d(vw);
}

};
private android.view.View.OnTouchListener $_on_setOnTouchListener_i956d0f4bd8=new android.view.View.OnTouchListener(){

public boolean onTouch(android.view.View vw, android.view.MotionEvent me){
return $_onTouch_i956d0f4bd8(vw, me);
}

};
private android.view.View.OnTouchListener $_on_setOnTouchListener_i8d69eec323=new android.view.View.OnTouchListener(){

public boolean onTouch(android.view.View vw, android.view.MotionEvent me){
return $_onTouch_i8d69eec323(vw, me);
}

};
private android.widget.AdapterView.OnItemSelectedListener $_on_setOnItemSelectedListener_i004a739c93=new android.widget.AdapterView.OnItemSelectedListener(){

public void onItemSelected(android.widget.AdapterView vw, android.view.View view, int pn, long id){
$_onItemSelected_i004a739c93(vw, view, pn, id);
}

public void onNothingSelected(android.widget.AdapterView vw){
$_onNothingSelected_i004a739c93(vw);
}

};
private android.view.View.OnTouchListener $_on_setOnTouchListener_e45f6690d3=new android.view.View.OnTouchListener(){

public boolean onTouch(android.view.View vw, android.view.MotionEvent me){
return $_onTouch_e45f6690d3(vw, me);
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

android.widget.EditText e45f6690d3 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjkurl);
e45f6690d3.setOnTouchListener($_on_setOnTouchListener_e45f6690d3);

android.widget.Spinner i004a739c93 = (android.widget.Spinner) findViewById(ay, vw, open.cn.awg.pro.R.id.xlcd1);
i004a739c93.setOnItemSelectedListener($_on_setOnItemSelectedListener_i004a739c93);

android.widget.EditText i8d69eec323 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjkgp1);
i8d69eec323.setOnTouchListener($_on_setOnTouchListener_i8d69eec323);

android.widget.EditText i956d0f4bd8 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjkgp2);
i956d0f4bd8.setOnTouchListener($_on_setOnTouchListener_i956d0f4bd8);

android.widget.Spinner fef1635c8d = (android.widget.Spinner) findViewById(ay, vw, open.cn.awg.pro.R.id.xlcd2);
fef1635c8d.setOnItemSelectedListener($_on_setOnItemSelectedListener_fef1635c8d);

android.widget.CheckBox i3d918d2dcb = (android.widget.CheckBox) findViewById(ay, vw, open.cn.awg.pro.R.id.dx1);
i3d918d2dcb.setOnCheckedChangeListener($_on_setOnCheckedChangeListener_i3d918d2dcb);

android.widget.EditText i2ce2aa6972 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjks1);
i2ce2aa6972.setOnTouchListener($_on_setOnTouchListener_i2ce2aa6972);

android.widget.EditText b7e129881e = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjks2);
b7e129881e.setOnTouchListener($_on_setOnTouchListener_b7e129881e);

android.widget.EditText d9fcd0aec2 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjks3);
d9fcd0aec2.setOnTouchListener($_on_setOnTouchListener_d9fcd0aec2);

android.widget.EditText fc0b80656a = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjks4);
fc0b80656a.setOnTouchListener($_on_setOnTouchListener_fc0b80656a);

android.widget.EditText i8dfdb24277 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjks5);
i8dfdb24277.setOnTouchListener($_on_setOnTouchListener_i8dfdb24277);

android.widget.EditText i3793cc74e0 = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjks6);
i3793cc74e0.setOnTouchListener($_on_setOnTouchListener_i3793cc74e0);

android.widget.EditText i026fdb816d = (android.widget.EditText) findViewById(ay, vw, open.cn.awg.pro.R.id.bjks7);
i026fdb816d.setOnTouchListener($_on_setOnTouchListener_i026fdb816d);

android.widget.Button i171ea34f1a = (android.widget.Button) findViewById(ay, vw, open.cn.awg.pro.R.id.an1);
i171ea34f1a.setOnClickListener($_on_setOnClickListener_i171ea34f1a);

android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

__layoutIsLoaded(ay, vw);
}

    public final l5_a1_fuwuq_d0313_y2022 lei = this, 类 = this;
}
