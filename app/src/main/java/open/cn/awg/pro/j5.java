package open.cn.awg.pro;

import i.app.iActivity;
 import android.view.Window; import com.ypz.bangscreentools.BangScreenTools; import android.view.View;import java.lang.*;

public class j5 extends iActivity {

public void onCreate(android.os.Bundle be){
super.onCreate(be);
setContentView(open.cn.awg.pro.R.layout.j5);
_$_viewAutomaticSettingEvent();
open.cn.awg.pro.e6.getInstance().addActivity(lei);
csh();
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

st.xdbj(open.cn.awg.pro.R.id.j5_v).shxtck(true);

java.lang.String clr=zf.zf(open.cn.awg.pro.R.color.colorTab);
xt.pm().ztl(clr,0);


}
else{

st.xdbj(open.cn.awg.pro.R.id.j5_v).shxtck(false);

        
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

public open.cn.awg.pro.e1  e1=new e1(_APPINFO);

public open.cn.awg.pro.e12  e12=new e12(_APPINFO);

public void dqgg(){

final java.lang.String def="欢迎使用腕管Pro，这是一款优秀的应用。您可以前往腕管Pro官网：https://www.typheye.cn/awg，提出建议或意见。如果您有任何问题，可以联系开发者台风眼(QQ498978473)，或者通过邮箱(498978473@qq.com)联系我们！";

final java.lang.String h="/data/user/0/open.cn.awg.pro/data/notice/showing";

final java.lang.String cache="/data/user/0/open.cn.awg.pro/data/notice/cache";

if(wj.cz(h)==true&&!zf.dy(wj.dqwb(h),"")){

wj.xrwb(cache,wj.dqwb(h));


}
else{

wj.xrwb(cache,def);


}

final java.lang.String[] name=new java.lang.String[]{"url","title","code"};

final java.lang.String[] value=new java.lang.String[]{cache,"公告","super1"};
gj.jmxc(new java.lang.Runnable(){

public void run(){

gj.tz(d2.class,name,value);


}

});

}

public void show(int i){

final i.runlibrary.app.v.wb tab=st.wb(open.cn.awg.pro.R.id.Tab);

final i.runlibrary.app.v.xxbj x1=st.xxbj(open.cn.awg.pro.R.id.xxbj1);

final i.runlibrary.app.v.xxbj x2=st.xxbj(open.cn.awg.pro.R.id.xxbj6);

final i.runlibrary.app.v.xxbj x3=st.xxbj(open.cn.awg.pro.R.id.xxbj2);

if(i==0){

gj.jmxc(new java.lang.Runnable(){

public void run(){

tab.zf("< 应用公告");
x1.kjd(8);
x2.kjd(8);
x3.kjd(0);


}

});


}

else if(i==1){

gj.jmxc(new java.lang.Runnable(){

public void run(){

tab.zf("< 历史公告");
x1.kjd(0);
x2.kjd(8);
x3.kjd(8);


}

});


}

else if(i==2){

gj.jmxc(new java.lang.Runnable(){

public void run(){

tab.zf("< 应用公告");
x1.kjd(8);
x2.kjd(0);
x3.kjd(8);


}

});


}

}

public i.runlibrary.app.v.v7lb$UserAdapter yygglbspq=null;

public void csh(){

i.runlibrary.app.v.v7lb lb=st.v7lb(open.cn.awg.pro.R.id.yyggv7lb1);
yygglbspq=lb.v7lbspq(c20.class,open.cn.awg.pro.R.layout.c20);

}

public void jzpd(){
gj.xc(new java.lang.Thread(){

public void run(){

show(2);
yygglbspq.sc();

final java.lang.String set33="/data/user/0/open.cn.awg.pro/data/notice/json";

int[] kj=new int[]{open.cn.awg.pro.R.id.wb1,open.cn.awg.pro.R.id.wb3,open.cn.awg.pro.R.id.wb2};

java.lang.String[] jk=new java.lang.String[]{"","",""};

try{


java.lang.String v=wj.dqwb(set33);

i.runlibrary.app.zf$json jo=zf.json(v);

org.json.JSONObject json=jo.json;

org.json.JSONArray list=jo.dxlb(json,"NoticeList");

int i=jo.cd(list);

int size=0;

while(size<i){


org.json.JSONObject dx=jo.dx(list,size);
size++;

java.lang.Object v_title=jo.hq(dx,"title");

java.lang.Object v_data=jo.hq(dx,"data");

java.lang.Object v_date=jo.hq(dx,"date");

java.lang.Object v_writer=jo.hq(dx,"writer");

java.lang.String erji=v_date.toString()+" | "+v_writer.toString()+" 发布";
jk=new java.lang.String[]{v_title.toString(),erji,v_data.toString()};
yygglbspq.j(kj,jk);


}
gj.jmxc(new java.lang.Runnable(){

public void run(){

yygglbspq.sx();
show(1);


}

});


}catch(java.lang.Throwable e){

e1.tsk("提示","当前没有公告");
show(0);


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

public boolean onKeyDown(int kc, android.view.KeyEvent ke){

if(kc==4){


if(zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(),"< 应用公告")){

gj.gb();


}

else if(zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(),"< 公告列表")){

show(0);


}


}
return false;

}

public void onDestroy(){
super.onDestroy();
open.cn.awg.pro.e6.getInstance().removeActivity(lei);

}

private void $_onClick_d26d5d7080(android.view.View vw){

if(zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(),"< 应用公告")){

gj.gb();


}

else if(zf.dy(st.wb(open.cn.awg.pro.R.id.Tab).zf(),"< 历史公告")){

show(0);


}

}

private void $_onClick_i20c2777fab(android.view.View vw){
dqgg();

}

private boolean $_onLongClick_i20c2777fab(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb5).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb6).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
return true;

}

private void $_onClick_i3167b45174(android.view.View vw){
jzpd();

}

private boolean $_onLongClick_i3167b45174(android.view.View vw){

java.lang.String te=st.wb(open.cn.awg.pro.R.id.wb3).zf();

java.lang.String lj=st.wb(open.cn.awg.pro.R.id.wb4).zf();
e1.tsk("详细信息","[名称]\n"+te+"\n\n[简介]\n"+lj);
return true;

}

private void $_onClick_i4d951cdb0e(android.view.View vw){
gj.gb();

}

private void $_onClick_i34dc7bf20a(android.view.View vw){
show(0);

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
private android.view.View.OnClickListener $_on_setOnClickListener_d26d5d7080=new android.view.View.OnClickListener(){

public void onClick(android.view.View vw){
$_onClick_d26d5d7080(vw);
}

};

public void _$_viewAutomaticSettingEvent(android.app.Activity ay, android.view.View vw){

android.widget.TextView d26d5d7080 = (android.widget.TextView) findViewById(ay, vw, open.cn.awg.pro.R.id.Tab);
d26d5d7080.setOnClickListener($_on_setOnClickListener_d26d5d7080);

android.widget.RelativeLayout i20c2777fab = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj3);
i20c2777fab.setOnClickListener($_on_setOnClickListener_i20c2777fab);
i20c2777fab.setOnLongClickListener($_on_setOnLongClickListener_i20c2777fab);

android.widget.RelativeLayout i3167b45174 = (android.widget.RelativeLayout) findViewById(ay, vw, open.cn.awg.pro.R.id.xdbj2);
i3167b45174.setOnClickListener($_on_setOnClickListener_i3167b45174);
i3167b45174.setOnLongClickListener($_on_setOnLongClickListener_i3167b45174);

android.widget.ImageView i4d951cdb0e = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx2);
i4d951cdb0e.setOnClickListener($_on_setOnClickListener_i4d951cdb0e);

android.widget.ImageView i34dc7bf20a = (android.widget.ImageView) findViewById(ay, vw, open.cn.awg.pro.R.id.tx1);
i34dc7bf20a.setOnClickListener($_on_setOnClickListener_i34dc7bf20a);

__layoutIsLoaded(ay, vw);
}

    public final j5 lei = this, 类 = this;
}
