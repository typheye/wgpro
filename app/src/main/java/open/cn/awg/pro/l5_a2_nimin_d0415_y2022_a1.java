package open.cn.awg.pro;

import i.app.iActivity;
 import android.view.Window; import com.ypz.bangscreentools.BangScreenTools; import java.io.File; import android.view.View;import java.lang.*;

public class l5_a2_nimin_d0415_y2022_a1 extends iActivity {

public void onCreate(android.os.Bundle be){
super.onCreate(be);
setContentView(open.cn.awg.pro.R.layout.l5_a2_nimin_d0415_y2022_a1);
_$_viewAutomaticSettingEvent();
open.cn.awg.pro.e6.getInstance().addActivity(lei);
e12.csh();
ll=sj.hqtz("name");
pp=Integer.parseInt(sj.hqtz("type"));
search(ll,pp,oo);

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

st.xdbj(open.cn.awg.pro.R.id.l5_a2_nimin_d0415_y2022_a1_v).shxtck(true);

java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{

st.xdbj(open.cn.awg.pro.R.id.l5_a2_nimin_d0415_y2022_a1_v).shxtck(false);

        
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

public java.lang.String um="";

public java.io.File f;

public java.lang.String ll="";

public int pp=0;

public int oo=1;

public open.cn.awg.pro.e1  e1=new e1(_APPINFO);

public open.cn.awg.pro.e12  e12=new e12(_APPINFO);

public void show(int i){

final i.runlibrary.app.v.xxbj x2=st.xxbj(open.cn.awg.pro.R.id.xxbj6);

final i.runlibrary.app.v.xxbj x3=st.xxbj(open.cn.awg.pro.R.id.xxbj4);

if(i==1){

gj.jmxc(new java.lang.Runnable(){

public void run(){

x2.kjd(0);
x3.kjd(8);


}

});


}

else if(i==2){

gj.jmxc(new java.lang.Runnable(){

public void run(){

x2.kjd(8);
x3.kjd(0);


}

});


}

}

public java.lang.String endurl="";

public java.lang.String set35="/data/user/0/open.cn.awg.pro/settings/other/0xO7YX2/url";

public java.lang.String api_search="/cloudsearch";

public java.lang.String api_search2="/user/cloud";

public int[] api_search_type=new int[]{1,10,100,1000,1002,1004,1006,1009,1014,1018,2000};

public void search(java.lang.Object l, int p, int k){

final java.lang.String ljf=wj.dqwb("/data/user/0/open.cn.awg.pro/data/assets/a17");

final java.lang.Object xx=l;

final int type=p;

final int kk=k;
gj.jmxc(new java.lang.Runnable(){

public void run(){


if(type==-1){

st.wb(open.cn.awg.pro.R.id.Tab).zf("< 云盘资源");


}


}

});
gj.xc(new java.lang.Thread(){

public void run(){

show(1);

if(zf.dy(xx,"")){

e1.tsk("提示","搜索不能为空");
gj.gb();


}
else{


if(type==0){


java.lang.String url=zf.qctwkg(wj.dqwb(set35))+api_search;

java.lang.String setback="";

java.lang.String f="User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept-language=zh-CN";

int ot=(kk-1)*20;

java.lang.String whq="?keywords="+xx+ljf+"type="+api_search_type[type]+ljf+"limit=20"+ljf+"offset="+ot;

java.lang.String back=wl.hq(url+whq,null,"utf-8");

if(zf.dy(back,"")||zf.dy(back,null)){

setback="0";


}
else{

setback=back;


}

if(zf.dy(setback,"0")){

e1.tsk("提示","加载失败,请重试");
gj.gb();


}
else{


try{

e12.search_i1(setback,oo);


}catch(java.lang.Throwable e){

e1.upload_error(e,"l5_a2_nimin_d0415_y2022_a1.search(s,int,int)");
gj.gb();


}
show(2);


}


}

else if(type==-1){


java.lang.String whq0="/cookie2.csm";

java.lang.String back0=wl.hq(zf.qctwkg(wj.dqwb(set35))+whq0,null,"utf-8");
back0=zf.qctwkg(back0);

if(!zf.dy(back0,"")||!zf.dy(back0,null)){


try{


int len=15;

if(back0.length()<=len){

back0="";


}
else{

back0=back0;

if(back0.length()<=len){

back0="";


}


}


}catch(java.lang.Throwable e){

back0="";


}
back0=zf.qctwkg(back0);

if(zf.dy(back0,"")||zf.dy(back0,null)){

e1.tsk("提示","加载失败,请重试(-1)");
gj.gb();


}
else{


java.lang.String sjzx=xt.sj(4);

java.lang.String url=zf.qctwkg(wj.dqwb(set35))+api_search2;

java.lang.String setback="";

java.lang.String f="User-Agent=Mozilla/5.0 (iPad; U; CPU OS 6_0 like Mac OS X; zh-CN; iPad2)||accept-language=zh-CN";

int ot=(kk-1)*20;

java.lang.String whq="?limit=20"+ljf+"offset="+ot+ljf+"timestamp="+sjzx+ljf+"cookie="+back0;

java.lang.String back=wl.hq(url+whq,null,"utf-8");

if(zf.dy(back,"")||zf.dy(back,null)){

setback="0";


}
else{

setback=back;


}

if(zf.dy(setback,"0")){

e1.tsk("提示","加载失败,请重试(-2)");
gj.gb();


}
else{


try{

e12.search_i2(setback,oo);


}catch(java.lang.Throwable e){

e1.upload_error(e,"l5_a2_nimin_d0415_y2022_a1.search(s,int,int)");
gj.gb();


}
show(2);


}


}


}
else{

e1.tsk("提示","加载失败,请重试(-3)");
gj.gb();


}


}


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

try{


java.lang.String p=st.wb(open.cn.awg.pro.R.id.myswb).zf();

final java.lang.String max=zf.qc(p,"/","页");

if(oo==1){

e1.tsk("提示","已经在第一页了");


}
else{

oo=oo-1;
search(ll,pp,oo);

final int oog=oo;
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb2).zf("第"+oog+"/"+max+"页");


}

});


}


}catch(java.lang.Throwable e){

e1.upload_error(e,"l5_a2_nimin_d0415_y2022_a1.*");


}

}

private void $_onClick_i8217d42a17(android.view.View vw){

try{


java.lang.String p=st.wb(open.cn.awg.pro.R.id.myswb).zf();
p=zf.qc(p,"/","页");

java.lang.String o=String.valueOf(oo);

if(zf.dy(o,p.toString())){

e1.tsk("提示","已经在最后一页了");


}
else{

oo=oo+1;
search(ll,pp,oo);

final int oog=oo;

final java.lang.String pg=p;
gj.jmxc(new java.lang.Runnable(){

public void run(){

st.wb(open.cn.awg.pro.R.id.wb2).zf("第"+oog+"/"+pg+"页");


}

});


}


}catch(java.lang.Throwable e){

e1.upload_error(e,"l5_a2_nimin_d0415_y2022_a1.*");


}

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
private android.view.View.OnClickListener $_on_setOnClickListener_i8217d42a17=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_i8217d42a17(vw);
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

android.widget.ImageView i8217d42a17 = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx3);
i8217d42a17.setOnClickListener($_on_setOnClickListener_i8217d42a17);

android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

__layoutIsLoaded(ay, vw);
}

    public final l5_a2_nimin_d0415_y2022_a1 lei = this, 类 = this;
}
